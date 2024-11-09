package com.prineside.tdi2.systems;

import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.GameSystem;
import com.prineside.tdi2.gameplayMods.GameplayMod;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/GameplayModSystem.class */
public final class GameplayModSystem extends GameSystem {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2956a = TLog.forClass(GameplayModSystem.class);

    /* renamed from: b, reason: collision with root package name */
    private DelayedRemovalArray<ActiveMod> f2957b = new DelayedRemovalArray<>(true, 1, ActiveMod.class);
    private RandomXS128 c = new RandomXS128(1234);

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObject(output, this.f2957b);
        kryo.writeObject(output, this.c);
    }

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f2957b = (DelayedRemovalArray) kryo.readObject(input, DelayedRemovalArray.class);
        this.c = (RandomXS128) kryo.readObject(input, RandomXS128.class);
    }

    @Override // com.prineside.tdi2.GameSystem
    public final boolean affectsGameState() {
        return true;
    }

    public final RandomXS128 getModRandom(int i) {
        this.c.setSeed((this.S.gameState.getSeed() * 31) + i);
        return this.c;
    }

    public final DelayedRemovalArray<ActiveMod> getActiveMods() {
        return this.f2957b;
    }

    @Null
    public final <T extends GameplayMod> ActiveMod getActiveModFromSource(Class<T> cls, String str) {
        for (int i = 0; i < this.f2957b.size; i++) {
            ActiveMod activeMod = this.f2957b.items[i];
            if (activeMod.f2958a.getClass() == cls && activeMod.getSource().equals(str)) {
                return activeMod;
            }
        }
        return null;
    }

    @Null
    public final <T extends GameplayMod> ActiveMod getActiveMod(Class<T> cls) {
        for (int i = 0; i < this.f2957b.size; i++) {
            ActiveMod activeMod = this.f2957b.items[i];
            if (activeMod.f2958a.getClass() == cls) {
                return activeMod;
            }
        }
        return null;
    }

    public final void activateMod(GameplayMod gameplayMod, String str) {
        if (gameplayMod.register(this.S, str)) {
            f2956a.i("adding " + gameplayMod + " to activeMods", new Object[0]);
            ActiveMod activeMod = new ActiveMod((byte) 0);
            activeMod.f2958a = gameplayMod;
            activeMod.f2959b = str;
            this.f2957b.add(activeMod);
        }
    }

    @Override // com.prineside.tdi2.GameSystem
    public final String getSystemName() {
        return "GameplayMod";
    }

    @Override // com.prineside.tdi2.GameSystem, com.badlogic.gdx.utils.Disposable
    public final void dispose() {
        super.dispose();
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/GameplayModSystem$ActiveMod.class */
    public static final class ActiveMod implements KryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private GameplayMod f2958a;

        /* renamed from: b, reason: collision with root package name */
        private String f2959b;

        /* synthetic */ ActiveMod(byte b2) {
            this();
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public final void write(Kryo kryo, Output output) {
            kryo.writeClassAndObject(output, this.f2958a);
            kryo.writeObject(output, this.f2959b);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public final void read(Kryo kryo, Input input) {
            this.f2958a = (GameplayMod) kryo.readClassAndObject(input);
            this.f2959b = (String) kryo.readObject(input, String.class);
        }

        private ActiveMod() {
        }

        public final GameplayMod getMod() {
            return this.f2958a;
        }

        public final void setMod(GameplayMod gameplayMod) {
            Preconditions.checkNotNull(gameplayMod, "mod can not be null");
            this.f2958a = gameplayMod;
        }

        public final String getSource() {
            return this.f2959b;
        }

        public final void setSource(String str) {
            Preconditions.checkNotNull(str, "source can not be null");
            this.f2959b = str;
        }
    }
}
