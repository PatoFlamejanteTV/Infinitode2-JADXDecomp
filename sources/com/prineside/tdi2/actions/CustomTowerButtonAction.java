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
/* loaded from: infinitode-2.jar:com/prineside/tdi2/actions/CustomTowerButtonAction.class */
public class CustomTowerButtonAction extends Action {
    public int x;
    public int y;
    public int mapX;
    public int mapY;

    private CustomTowerButtonAction() {
    }

    public CustomTowerButtonAction(int i, int i2, int i3, int i4) {
        this.x = i;
        this.y = i2;
        this.mapX = i3;
        this.mapY = i4;
    }

    public CustomTowerButtonAction(JsonValue jsonValue) {
        this(jsonValue.getInt("x"), jsonValue.getInt("y"), jsonValue.getInt("mx"), jsonValue.getInt("my"));
    }

    @Override // com.prineside.tdi2.Action
    public ActionType getType() {
        return ActionType.CTB;
    }

    @Override // com.prineside.tdi2.Action
    public void toJson(Json json) {
        json.writeValue("x", Integer.valueOf(this.x));
        json.writeValue("y", Integer.valueOf(this.y));
        json.writeValue("mx", Integer.valueOf(this.mapX));
        json.writeValue("my", Integer.valueOf(this.mapY));
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        output.writeByte(this.x);
        output.writeByte(this.y);
        output.writeInt(this.mapX);
        output.writeInt(this.mapY);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        this.x = input.readByte();
        this.y = input.readByte();
        this.mapX = input.readInt();
        this.mapY = input.readInt();
    }

    public String toString() {
        return "CustomTowerButton " + this.x + SequenceUtils.SPACE + this.y + SequenceUtils.SPACE + this.mapX + SequenceUtils.SPACE + this.mapY;
    }
}
