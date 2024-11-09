package com.prineside.tdi2.items;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.enums.ItemCategoryType;
import com.prineside.tdi2.enums.ItemDataType;
import com.prineside.tdi2.enums.ItemSortingType;
import com.prineside.tdi2.enums.ItemSubcategoryType;
import com.prineside.tdi2.enums.ItemType;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.ui.SelectBox;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ChangeListener;
import com.prineside.tdi2.ui.shared.ItemCreationOverlay;
import com.prineside.tdi2.utils.REGS;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/items/TileItem.class */
public class TileItem extends Item {
    public final Tile tile;

    /* synthetic */ TileItem(Tile tile, byte b2) {
        this(tile);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/TileItem$Serializer.class */
    public static class Serializer extends com.esotericsoftware.kryo.Serializer<TileItem> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, TileItem tileItem) {
            kryo.writeClassAndObject(output, tileItem.tile);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public TileItem read2(Kryo kryo, Input input, Class<? extends TileItem> cls) {
            return Item.D.F_TILE.create((Tile) kryo.readClassAndObject(input));
        }
    }

    private TileItem(Tile tile) {
        if (tile == null) {
            throw new IllegalArgumentException("Tile is null");
        }
        this.tile = tile;
    }

    @Override // com.prineside.tdi2.Item
    public IntArray getData() {
        IntArray data = super.getData();
        data.add(ItemDataType.TYPE.ordinal(), this.tile.type.ordinal());
        this.tile.getData(data);
        return data;
    }

    @Override // com.prineside.tdi2.Item
    public String getAnalyticName() {
        return "tile_" + this.tile.type;
    }

    @Override // com.prineside.tdi2.Item
    public boolean affectedByLuckyWheelMultiplier() {
        return this.tile.affectedByLuckyWheelMultiplier();
    }

    @Override // com.prineside.tdi2.Item
    public void fillWithInfo(Table table, float f) {
        this.tile.fillInventoryWithInfo(table, f);
    }

    @Override // com.prineside.tdi2.Item
    public Item from(Item item) {
        this.tile.from(((TileItem) item).tile);
        return this;
    }

    @Override // com.prineside.tdi2.Item
    public Item cpy() {
        return Item.D.F_TILE.create(this.tile.cloneTile());
    }

    @Override // com.prineside.tdi2.Item
    public final ItemCategoryType getCategory() {
        return ItemCategoryType.MAP_EDITOR;
    }

    @Override // com.prineside.tdi2.Item
    public ItemSubcategoryType getSubcategory() {
        return this.tile.getInventorySubcategory();
    }

    @Override // com.prineside.tdi2.Item
    public ItemType getType() {
        return ItemType.TILE;
    }

    @Override // com.prineside.tdi2.Item
    public boolean canBeSold() {
        return this.tile.canBeSold();
    }

    @Override // com.prineside.tdi2.Item
    public RarityType getRarity() {
        return this.tile.getRarity();
    }

    @Override // com.prineside.tdi2.Item
    public void addSellItems(Array<ItemStack> array) {
        this.tile.addSellItems(array);
    }

    @Override // com.prineside.tdi2.Item
    public int getSortingScore(ItemSortingType itemSortingType) {
        return this.tile.getSortingScore(itemSortingType);
    }

    @Override // com.prineside.tdi2.Item
    public CharSequence getTitle() {
        return this.tile.getTitle();
    }

    @Override // com.prineside.tdi2.Item
    public CharSequence getDescription() {
        return this.tile.getDescription();
    }

    @Override // com.prineside.tdi2.Item
    public boolean isCountable() {
        return true;
    }

    @Override // com.prineside.tdi2.Item
    public boolean sameAs(Item item) {
        if (super.sameAs(item)) {
            return ((TileItem) item).tile.sameAs(this.tile);
        }
        return false;
    }

    @Override // com.prineside.tdi2.Item
    public void toJson(Json json) {
        super.toJson(json);
        json.writeObjectStart("tile");
        this.tile.toJson(json);
        json.writeObjectEnd();
    }

    @Override // com.prineside.tdi2.Item
    public Actor generateIcon(float f, boolean z) {
        return this.tile.generateUiIcon(f);
    }

    @Override // com.prineside.tdi2.Item
    public void fillItemCreationForm(final ItemCreationOverlay itemCreationOverlay) {
        itemCreationOverlay.label("Tile type");
        final SelectBox selectBox = new SelectBox(itemCreationOverlay.selectBoxStyle);
        selectBox.setItems(TileType.values);
        selectBox.setSelected(this.tile.type);
        selectBox.addListener(new ChangeListener(this) { // from class: com.prineside.tdi2.items.TileItem.1
            @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
            public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                itemCreationOverlay.currentItem = Item.D.F_TILE.create(Game.i.tileManager.getFactory((TileType) selectBox.getSelected()).create());
                itemCreationOverlay.updateForm();
            }
        });
        itemCreationOverlay.selectBox(selectBox);
        this.tile.fillItemCreationForm(itemCreationOverlay);
    }

    public String toString() {
        return getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + " (tile: " + String.valueOf(this.tile) + ")";
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/TileItem$TileItemFactory.class */
    public static class TileItemFactory implements Item.Factory<TileItem> {
        @Override // com.prineside.tdi2.Item.Factory
        public void setup() {
        }

        public TileItem create(Tile tile) {
            return new TileItem(tile, (byte) 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public TileItem fromJson(JsonValue jsonValue) {
            return create(Game.i.tileManager.createTileFromJson(jsonValue.get("tile")));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public TileItem createDefault() {
            return create(Game.i.tileManager.getFactory(TileType.values[0]).create());
        }
    }
}
