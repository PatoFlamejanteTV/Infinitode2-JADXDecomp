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
/* loaded from: infinitode-2.jar:com/prineside/tdi2/actions/CustomAction.class */
public class CustomAction extends Action {
    public int i1;
    public int i2;
    public int i3;
    public int i4;
    public double d1;
    public double d2;
    public String s;

    private CustomAction() {
    }

    public CustomAction(JsonValue jsonValue) {
        this.i1 = jsonValue.getInt("i1", 0);
        this.i2 = jsonValue.getInt("i2", 0);
        this.i3 = jsonValue.getInt("i3", 0);
        this.i4 = jsonValue.getInt("i4", 0);
        this.d1 = jsonValue.getInt("d1", 0);
        this.d2 = jsonValue.getInt("d2", 0);
        this.s = jsonValue.getString("s", null);
    }

    @Override // com.prineside.tdi2.Action
    public ActionType getType() {
        return ActionType.C;
    }

    @Override // com.prineside.tdi2.Action
    public void toJson(Json json) {
        if (this.i1 != 0) {
            json.writeValue("i1", Integer.valueOf(this.i1));
        }
        if (this.i2 != 0) {
            json.writeValue("i2", Integer.valueOf(this.i2));
        }
        if (this.i3 != 0) {
            json.writeValue("i3", Integer.valueOf(this.i3));
        }
        if (this.i4 != 0) {
            json.writeValue("i4", Integer.valueOf(this.i4));
        }
        if (this.d1 != 0.0d) {
            json.writeValue("d1", Double.valueOf(this.d1));
        }
        if (this.d2 != 0.0d) {
            json.writeValue("d2", Double.valueOf(this.d2));
        }
        if (this.s != null) {
            json.writeValue("s", this.s);
        }
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        output.writeVarInt(this.i1, true);
        output.writeVarInt(this.i2, true);
        output.writeVarInt(this.i3, true);
        output.writeVarInt(this.i4, true);
        output.writeDouble(this.d1);
        output.writeDouble(this.d2);
        output.writeString(this.s);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        this.i1 = input.readVarInt(true);
        this.i2 = input.readVarInt(true);
        this.i3 = input.readVarInt(true);
        this.i4 = input.readVarInt(true);
        this.d1 = input.readDouble();
        this.d2 = input.readDouble();
        this.s = input.readString();
    }

    public String toString() {
        return "Custom " + this.i1 + SequenceUtils.SPACE + this.i2 + SequenceUtils.SPACE + this.i3 + SequenceUtils.SPACE + this.i4 + SequenceUtils.SPACE + this.d1 + SequenceUtils.SPACE + this.d2 + SequenceUtils.SPACE + this.s;
    }
}
