package com.prineside.tdi2.actions;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Action;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.enums.ActionType;
import com.prineside.tdi2.utils.REGS;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/actions/UseAbilityAction.class */
public class UseAbilityAction extends Action {
    public AbilityType abilityType;
    public int x;
    public int y;

    private UseAbilityAction() {
    }

    public UseAbilityAction(AbilityType abilityType, int i, int i2) {
        this.abilityType = abilityType;
        this.x = i;
        this.y = i2;
    }

    public UseAbilityAction(JsonValue jsonValue) {
        this(AbilityType.valueOf(jsonValue.getString("at")), jsonValue.getInt("x"), jsonValue.getInt("y"));
    }

    @Override // com.prineside.tdi2.Action
    public ActionType getType() {
        return ActionType.UA;
    }

    @Override // com.prineside.tdi2.Action
    public void toJson(Json json) {
        json.writeValue("at", this.abilityType.name());
        json.writeValue("x", Integer.valueOf(this.x));
        json.writeValue("y", Integer.valueOf(this.y));
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        kryo.writeObject(output, this.abilityType);
        output.writeInt(this.x);
        output.writeInt(this.y);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        this.abilityType = (AbilityType) kryo.readObject(input, AbilityType.class);
        this.x = input.readInt();
        this.y = input.readInt();
    }

    public String toString() {
        return "UseAbility " + this.abilityType.name() + SequenceUtils.SPACE + this.x + SequenceUtils.SPACE + this.y;
    }
}
