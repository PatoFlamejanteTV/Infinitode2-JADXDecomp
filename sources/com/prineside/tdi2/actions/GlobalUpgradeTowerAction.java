package com.prineside.tdi2.actions;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Action;
import com.prineside.tdi2.enums.ActionType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/actions/GlobalUpgradeTowerAction.class */
public class GlobalUpgradeTowerAction extends Action {
    public TowerType towerType;

    private GlobalUpgradeTowerAction() {
    }

    public GlobalUpgradeTowerAction(TowerType towerType) {
        this.towerType = towerType;
    }

    public GlobalUpgradeTowerAction(JsonValue jsonValue) {
        this(TowerType.valueOf(jsonValue.getString("tt")));
    }

    @Override // com.prineside.tdi2.Action
    public ActionType getType() {
        return ActionType.GUT;
    }

    @Override // com.prineside.tdi2.Action
    public void toJson(Json json) {
        json.writeValue("tt", this.towerType.name());
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        kryo.writeObject(output, this.towerType);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        this.towerType = (TowerType) kryo.readObject(input, TowerType.class);
    }

    public String toString() {
        return "GlobalUpgradeTower " + this.towerType.name();
    }
}
