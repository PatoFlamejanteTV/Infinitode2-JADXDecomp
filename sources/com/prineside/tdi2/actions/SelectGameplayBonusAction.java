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
/* loaded from: infinitode-2.jar:com/prineside/tdi2/actions/SelectGameplayBonusAction.class */
public class SelectGameplayBonusAction extends Action {
    public int stage;
    public int bonusIdx;

    private SelectGameplayBonusAction() {
    }

    public SelectGameplayBonusAction(int i, int i2) {
        this.stage = i;
        this.bonusIdx = i2;
    }

    public SelectGameplayBonusAction(JsonValue jsonValue) {
        this(jsonValue.getInt("s"), jsonValue.getInt("bi"));
    }

    @Override // com.prineside.tdi2.Action
    public ActionType getType() {
        return ActionType.SGB;
    }

    @Override // com.prineside.tdi2.Action
    public void toJson(Json json) {
        json.writeValue("s", Integer.valueOf(this.stage));
        json.writeValue("bi", Integer.valueOf(this.bonusIdx));
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        output.writeVarInt(this.stage, true);
        output.writeVarInt(this.bonusIdx, true);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        this.stage = input.readVarInt(true);
        this.bonusIdx = input.readVarInt(true);
    }

    public String toString() {
        return "SelectGameplayBonus " + this.stage + SequenceUtils.SPACE + this.bonusIdx;
    }
}
