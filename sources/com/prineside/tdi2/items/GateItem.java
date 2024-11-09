package com.prineside.tdi2.items;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Gate;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.enums.GateType;
import com.prineside.tdi2.enums.ItemCategoryType;
import com.prineside.tdi2.enums.ItemSortingType;
import com.prineside.tdi2.enums.ItemSubcategoryType;
import com.prineside.tdi2.enums.ItemType;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.utils.REGS;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/items/GateItem.class */
public class GateItem extends Item {
    public Gate gate;

    /* synthetic */ GateItem(Gate gate, byte b2) {
        this(gate);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/GateItem$Serializer.class */
    public static class Serializer extends com.esotericsoftware.kryo.Serializer<GateItem> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, GateItem gateItem) {
            kryo.writeClassAndObject(output, gateItem.gate);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public GateItem read2(Kryo kryo, Input input, Class<? extends GateItem> cls) {
            return Item.D.F_GATE.create((Gate) kryo.readClassAndObject(input));
        }
    }

    private GateItem(Gate gate) {
        if (gate == null) {
            throw new IllegalArgumentException("Gate is null");
        }
        this.gate = gate;
    }

    @Override // com.prineside.tdi2.Item
    public Item from(Item item) {
        this.gate = ((GateItem) item).gate.cloneGate();
        return this;
    }

    @Override // com.prineside.tdi2.Item
    public Item cpy() {
        return Item.D.F_GATE.create(this.gate.cloneGate());
    }

    @Override // com.prineside.tdi2.Item
    public ItemSubcategoryType getSubcategory() {
        return ItemSubcategoryType.ME_ROADS;
    }

    @Override // com.prineside.tdi2.Item
    public ItemType getType() {
        return ItemType.GATE;
    }

    @Override // com.prineside.tdi2.Item
    public ItemCategoryType getCategory() {
        return ItemCategoryType.MAP_EDITOR;
    }

    @Override // com.prineside.tdi2.Item
    public CharSequence getTitle() {
        return Game.i.gateManager.getFactory(this.gate.getType()).getTitle(this.gate);
    }

    @Override // com.prineside.tdi2.Item
    public CharSequence getDescription() {
        return Game.i.gateManager.getFactory(this.gate.getType()).getDescription(this.gate);
    }

    @Override // com.prineside.tdi2.Item
    public int getSortingScore(ItemSortingType itemSortingType) {
        return this.gate.getSortingScore(itemSortingType);
    }

    @Override // com.prineside.tdi2.Item
    public String getAnalyticName() {
        return "gate_" + this.gate.getType();
    }

    @Override // com.prineside.tdi2.Item
    public boolean canBeSold() {
        return true;
    }

    @Override // com.prineside.tdi2.Item
    public void addSellItems(Array<ItemStack> array) {
        this.gate.addSellItems(array);
    }

    @Override // com.prineside.tdi2.Item
    public RarityType getRarity() {
        return this.gate.getRarity();
    }

    @Override // com.prineside.tdi2.Item
    public boolean isCountable() {
        return true;
    }

    @Override // com.prineside.tdi2.Item
    public boolean sameAs(Item item) {
        if (super.sameAs(item)) {
            return this.gate.sameAs(((GateItem) item).gate);
        }
        return false;
    }

    @Override // com.prineside.tdi2.Item
    public void toJson(Json json) {
        super.toJson(json);
        json.writeObjectStart("gate");
        this.gate.toJson(json);
        json.writeObjectEnd();
    }

    @Override // com.prineside.tdi2.Item
    public Actor generateIcon(float f, boolean z) {
        return this.gate.generateIcon(f, z);
    }

    public String toString() {
        return getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + " (" + this.gate.toString() + ")";
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/GateItem$GateItemFactory.class */
    public static class GateItemFactory implements Item.Factory<GateItem> {
        @Override // com.prineside.tdi2.Item.Factory
        public void setup() {
        }

        public GateItem create(Gate gate) {
            return new GateItem(gate, (byte) 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public GateItem fromJson(JsonValue jsonValue) {
            return create(Game.i.gateManager.createGateFromJson(jsonValue.get("gate")));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public GateItem createDefault() {
            return create(Game.i.gateManager.getFactory(GateType.values[0]).create());
        }
    }
}
