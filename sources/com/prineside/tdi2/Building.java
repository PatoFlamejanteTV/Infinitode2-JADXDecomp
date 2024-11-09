package com.prineside.tdi2;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.enums.BuildingType;
import com.prineside.tdi2.shapes.RangeCircle;
import com.prineside.tdi2.systems.MapRenderingSystem;
import com.prineside.tdi2.tiles.PlatformTile;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/Building.class */
public abstract class Building extends Registrable {
    public BuildingType buildingType;

    /* renamed from: a, reason: collision with root package name */
    private PlatformTile f1670a;

    public abstract Building cloneBuilding();

    public abstract void updateCache();

    public abstract float getWalkCost();

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObjectOrNull(output, this.buildingType, BuildingType.class);
        kryo.writeObjectOrNull(output, this.f1670a, PlatformTile.class);
    }

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.buildingType = (BuildingType) kryo.readObjectOrNull(input, BuildingType.class);
        this.f1670a = (PlatformTile) kryo.readObjectOrNull(input, PlatformTile.class);
    }

    public Building(BuildingType buildingType) {
        this.buildingType = buildingType;
    }

    public boolean sameAs(Building building) {
        return building != null && this.buildingType == building.buildingType;
    }

    public PlatformTile getTile() {
        return this.f1670a;
    }

    public void setTile(PlatformTile platformTile) {
        this.f1670a = platformTile;
    }

    public void drawBase(Batch batch, float f, float f2, float f3, float f4, MapRenderingSystem.DrawMode drawMode) {
    }

    public void toJson(Json json) {
        json.writeValue("bType", this.buildingType.name());
    }

    public void placedOnMap() {
    }

    public void removedFromMap() {
    }

    public void drawSelectedRange(Batch batch, RangeCircle rangeCircle) {
    }

    public void drawHoveredRange(Batch batch, RangeCircle rangeCircle) {
    }

    public static Building fromJson(JsonValue jsonValue) {
        BuildingType valueOf = BuildingType.valueOf(jsonValue.getString("bType"));
        switch (valueOf) {
            case TOWER:
                return Game.i.towerManager.fromJson(jsonValue);
            case MODIFIER:
                return Game.i.modifierManager.fromJson(jsonValue);
            default:
                throw new RuntimeException("Not implemented: " + valueOf.name());
        }
    }
}
