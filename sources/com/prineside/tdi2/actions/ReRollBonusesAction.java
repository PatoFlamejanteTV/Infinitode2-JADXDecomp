package com.prineside.tdi2.actions;

import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Action;
import com.prineside.tdi2.enums.ActionType;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/actions/ReRollBonusesAction.class */
public class ReRollBonusesAction extends Action {
    public ReRollBonusesAction() {
    }

    public ReRollBonusesAction(JsonValue jsonValue) {
    }

    @Override // com.prineside.tdi2.Action
    public ActionType getType() {
        return ActionType.RRB;
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
    }

    public String toString() {
        return "ReRollBonuses";
    }
}