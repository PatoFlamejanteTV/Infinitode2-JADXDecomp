package com.prineside.tdi2.actions;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Action;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.enums.ActionType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.utils.REGS;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/actions/BuildTowerAction.class */
public class BuildTowerAction extends Action {
    public TowerType towerType;
    public Tower.AimStrategy aimStrategy;
    public int x;
    public int y;

    private BuildTowerAction() {
    }

    public BuildTowerAction(TowerType towerType, Tower.AimStrategy aimStrategy, int i, int i2) {
        this.towerType = towerType;
        this.aimStrategy = aimStrategy;
        this.x = i;
        this.y = i2;
    }

    public BuildTowerAction(JsonValue jsonValue) {
        this(TowerType.valueOf(jsonValue.getString("tt")), Tower.AimStrategy.valueOf(jsonValue.getString("aim")), jsonValue.getInt("x"), jsonValue.getInt("y"));
    }

    @Override // com.prineside.tdi2.Action
    public ActionType getType() {
        return ActionType.BT;
    }

    @Override // com.prineside.tdi2.Action
    public void toJson(Json json) {
        json.writeValue("tt", this.towerType.name());
        json.writeValue("aim", this.aimStrategy.name());
        json.writeValue("x", Integer.valueOf(this.x));
        json.writeValue("y", Integer.valueOf(this.y));
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        kryo.writeObject(output, this.towerType);
        kryo.writeObject(output, this.aimStrategy);
        output.writeByte(this.x);
        output.writeByte(this.y);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        this.towerType = (TowerType) kryo.readObject(input, TowerType.class);
        this.aimStrategy = (Tower.AimStrategy) kryo.readObject(input, Tower.AimStrategy.class);
        this.x = input.readByte();
        this.y = input.readByte();
    }

    public String toString() {
        return "BuildTower " + this.towerType.name() + SequenceUtils.SPACE + this.x + SequenceUtils.SPACE + this.y + SequenceUtils.SPACE + this.aimStrategy.name();
    }
}
