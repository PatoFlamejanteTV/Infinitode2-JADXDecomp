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
/* loaded from: infinitode-2.jar:com/prineside/tdi2/actions/SelectGlobalTowerAbilityAction.class */
public class SelectGlobalTowerAbilityAction extends Action {
    public int x;
    public int y;
    public int abilityIndex;

    private SelectGlobalTowerAbilityAction() {
    }

    public SelectGlobalTowerAbilityAction(int i, int i2, int i3) {
        this.abilityIndex = i;
        this.x = i2;
        this.y = i3;
    }

    public SelectGlobalTowerAbilityAction(JsonValue jsonValue) {
        this(jsonValue.getInt("ai"), jsonValue.getInt("x"), jsonValue.getInt("y"));
    }

    @Override // com.prineside.tdi2.Action
    public ActionType getType() {
        return ActionType.SGTA;
    }

    @Override // com.prineside.tdi2.Action
    public void toJson(Json json) {
        json.writeValue("ai", Integer.valueOf(this.abilityIndex));
        json.writeValue("x", Integer.valueOf(this.x));
        json.writeValue("y", Integer.valueOf(this.y));
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        output.writeByte(this.abilityIndex);
        output.writeByte(this.x);
        output.writeByte(this.y);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        this.abilityIndex = input.readByte();
        this.x = input.readByte();
        this.y = input.readByte();
    }

    public String toString() {
        return "SelectGlobalTowerAbility " + this.x + SequenceUtils.SPACE + this.y + SequenceUtils.SPACE + this.abilityIndex;
    }
}
