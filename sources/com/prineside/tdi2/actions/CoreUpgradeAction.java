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
/* loaded from: infinitode-2.jar:com/prineside/tdi2/actions/CoreUpgradeAction.class */
public class CoreUpgradeAction extends Action {
    public int x;
    public int y;
    public int col;
    public int row;

    private CoreUpgradeAction() {
    }

    public CoreUpgradeAction(int i, int i2, int i3, int i4) {
        this.x = i;
        this.y = i2;
        this.col = i3;
        this.row = i4;
    }

    public CoreUpgradeAction(JsonValue jsonValue) {
        this(jsonValue.getInt("x"), jsonValue.getInt("y"), jsonValue.getInt("col"), jsonValue.getInt("row"));
    }

    @Override // com.prineside.tdi2.Action
    public ActionType getType() {
        return ActionType.CU;
    }

    @Override // com.prineside.tdi2.Action
    public void toJson(Json json) {
        json.writeValue("x", Integer.valueOf(this.x));
        json.writeValue("y", Integer.valueOf(this.y));
        json.writeValue("col", Integer.valueOf(this.col));
        json.writeValue("row", Integer.valueOf(this.row));
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        output.writeInt(this.x);
        output.writeInt(this.y);
        output.writeInt(this.col);
        output.writeInt(this.row);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        this.x = input.readInt();
        this.y = input.readInt();
        this.col = input.readInt();
        this.row = input.readInt();
    }

    public String toString() {
        return "CoreUpgrade " + this.x + SequenceUtils.SPACE + this.y + SequenceUtils.SPACE + this.col + SequenceUtils.SPACE + this.row;
    }
}
