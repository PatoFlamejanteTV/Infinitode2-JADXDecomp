package com.prineside.tdi2.actions;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Action;
import com.prineside.tdi2.enums.ActionType;
import com.prineside.tdi2.utils.REGS;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/actions/UpgradeTowerAction.class */
public class UpgradeTowerAction extends Action {
    public int x;
    public int y;

    private UpgradeTowerAction() {
    }

    public UpgradeTowerAction(int i, int i2) {
        this.x = i;
        this.y = i2;
    }

    public UpgradeTowerAction(JsonValue jsonValue) {
        this(jsonValue.getInt("x"), jsonValue.getInt("y"));
    }

    @Override // com.prineside.tdi2.Action
    public ActionType getType() {
        return ActionType.UT;
    }

    @Override // com.prineside.tdi2.Action
    public void toJson(Json json) {
        json.writeValue("x", Integer.valueOf(this.x));
        json.writeValue("y", Integer.valueOf(this.y));
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        output.writeByte(this.x);
        output.writeByte(this.y);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        this.x = input.readByte();
        this.y = input.readByte();
    }

    public String toString() {
        return "UpgradeTower " + this.x + SequenceUtils.SPACE + this.y;
    }
}
