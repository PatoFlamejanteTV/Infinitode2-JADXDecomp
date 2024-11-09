package com.prineside.tdi2.actions;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Action;
import com.prineside.tdi2.enums.ActionType;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/actions/EncounterBirdClickAction.class */
public class EncounterBirdClickAction extends Action {
    public int birdIdx;

    private EncounterBirdClickAction() {
    }

    public EncounterBirdClickAction(int i) {
        this.birdIdx = i;
    }

    public EncounterBirdClickAction(JsonValue jsonValue) {
        this(jsonValue.getInt("idx"));
    }

    @Override // com.prineside.tdi2.Action
    public ActionType getType() {
        return ActionType.EBC;
    }

    @Override // com.prineside.tdi2.Action
    public void toJson(Json json) {
        json.writeValue("idx", Integer.valueOf(this.birdIdx));
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        output.writeVarInt(this.birdIdx, true);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        this.birdIdx = input.readVarInt(true);
    }

    public String toString() {
        return "RandomEncounterClick " + this.birdIdx;
    }
}
