package com.prineside.tdi2.systems;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Action;
import com.prineside.tdi2.GameSystem;
import com.prineside.tdi2.managers.ReplayManager;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/StateSystem.class */
public class StateSystem extends GameSystem {
    private static final TLog c = TLog.forClass(StateSystem.class);

    @NAGS
    public boolean updateRequired;

    @NAGS
    private int e;

    @NAGS
    public ReplayManager.ReplayRecord replayRecord;

    @NAGS
    public StateSystem duplicateActionsTo;
    public int updateNumber;

    @NAGS
    public boolean replayMode;

    @NAGS
    public long replayFrameCount;

    @NAGS
    private boolean d = false;

    @NAGS
    public boolean inUpdateStage = false;

    /* renamed from: a, reason: collision with root package name */
    protected IntMap<ActionsArray> f3061a = new IntMap<>();

    /* renamed from: b, reason: collision with root package name */
    protected Array<ActionUpdatePair> f3062b = new Array<>(ActionUpdatePair.class);

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeInt(this.updateNumber);
        kryo.writeObject(output, this.f3061a);
        kryo.writeObject(output, this.f3062b);
    }

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.updateNumber = input.readInt();
        this.f3061a = (IntMap) kryo.readObject(input, IntMap.class);
        this.f3062b = (Array) kryo.readObject(input, Array.class);
    }

    @Override // com.prineside.tdi2.GameSystem
    public boolean affectsGameState() {
        return true;
    }

    @Override // com.prineside.tdi2.GameSystem
    public String getSystemName() {
        return "State";
    }

    public boolean isFastForwarding() {
        return this.d;
    }

    public int getFastForwardUpdateNumber() {
        return this.e;
    }

    public void startFastForwarding(int i) {
        this.d = true;
        this.e = i;
    }

    public void stopFastForwarding() {
        if (this.d) {
            c.i("stopped fast forwarding on frame " + this.updateNumber + " / " + this.e, new Object[0]);
            this.d = false;
        }
    }

    public void checkGameplayUpdateAllowed() {
        if (!this.inUpdateStage) {
            throw new IllegalStateException("Game updates are not allowed now");
        }
    }

    public boolean canSkipMediaUpdate() {
        return this.d && this.e - this.updateNumber > 60;
    }

    public void pushActionNextUpdate(Action action) {
        pushAction(action, this.updateNumber + 1);
    }

    public void pushAction(Action action, int i) {
        if (this.replayMode) {
            return;
        }
        if (!this.f3061a.containsKey(i)) {
            this.f3061a.put(i, new ActionsArray());
        }
        this.f3061a.get(i).add(action);
        ActionUpdatePair actionUpdatePair = new ActionUpdatePair();
        actionUpdatePair.action = action;
        actionUpdatePair.update = i;
        this.f3062b.add(actionUpdatePair);
        this.updateRequired = true;
        if (this.duplicateActionsTo != null) {
            this.duplicateActionsTo.pushAction(action, i);
        }
    }

    public void requireUpdate() {
        this.updateRequired = true;
    }

    @Override // com.prineside.tdi2.GameSystem, com.badlogic.gdx.utils.Disposable
    public void dispose() {
        this.f3061a.clear();
        this.f3062b.clear();
        this.replayRecord = null;
        this.duplicateActionsTo = null;
        super.dispose();
    }

    @Null
    public ActionsArray getCurrentUpdateActions() {
        checkGameplayUpdateAllowed();
        return this.f3061a.get(this.updateNumber);
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/StateSystem$ActionUpdatePair.class */
    public static class ActionUpdatePair implements KryoSerializable {
        public Action action;
        public int update;

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void write(Kryo kryo, Output output) {
            kryo.writeClassAndObject(output, this.action);
            output.writeVarInt(this.update, true);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void read(Kryo kryo, Input input) {
            this.action = (Action) kryo.readClassAndObject(input);
            this.update = input.readVarInt(true);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/StateSystem$ActionsArray.class */
    public static class ActionsArray implements KryoSerializable {
        public Action[] actions = new Action[1];
        public int size;

        public void add(Action action) {
            if (this.size == this.actions.length) {
                a();
            }
            Action[] actionArr = this.actions;
            int i = this.size;
            this.size = i + 1;
            actionArr[i] = action;
        }

        private void a() {
            Action[] actionArr = new Action[this.size + this.size];
            System.arraycopy(this.actions, 0, actionArr, 0, this.size);
            this.actions = actionArr;
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void write(Kryo kryo, Output output) {
            kryo.writeObject(output, this.actions);
            output.writeVarInt(this.size, true);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void read(Kryo kryo, Input input) {
            this.actions = (Action[]) kryo.readObject(input, Action[].class);
            this.size = input.readVarInt(true);
        }
    }
}
