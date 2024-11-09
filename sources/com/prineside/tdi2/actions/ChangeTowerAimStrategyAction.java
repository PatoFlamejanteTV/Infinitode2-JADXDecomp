package com.prineside.tdi2.actions;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Action;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.enums.ActionType;
import com.prineside.tdi2.utils.REGS;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/actions/ChangeTowerAimStrategyAction.class */
public class ChangeTowerAimStrategyAction extends Action {
    public int x;
    public int y;
    public Tower.AimStrategy aimStrategy;

    private ChangeTowerAimStrategyAction() {
    }

    public ChangeTowerAimStrategyAction(int i, int i2, Tower.AimStrategy aimStrategy) {
        this.x = i;
        this.y = i2;
        this.aimStrategy = aimStrategy;
    }

    public ChangeTowerAimStrategyAction(JsonValue jsonValue) {
        this(jsonValue.getInt("x"), jsonValue.getInt("y"), Tower.AimStrategy.valueOf(jsonValue.getString("aim")));
    }

    @Override // com.prineside.tdi2.Action
    public ActionType getType() {
        return ActionType.CTAS;
    }

    @Override // com.prineside.tdi2.Action
    public void toJson(Json json) {
        json.writeValue("x", Integer.valueOf(this.x));
        json.writeValue("y", Integer.valueOf(this.y));
        json.writeValue("aim", this.aimStrategy.name());
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        kryo.writeObject(output, this.aimStrategy);
        output.writeByte(this.x);
        output.writeByte(this.y);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        this.aimStrategy = (Tower.AimStrategy) kryo.readObject(input, Tower.AimStrategy.class);
        this.x = input.readByte();
        this.y = input.readByte();
    }

    public String toString() {
        return "ChangeTowerAimStrategy " + this.aimStrategy.name() + SequenceUtils.SPACE + this.x + SequenceUtils.SPACE + this.y;
    }
}
