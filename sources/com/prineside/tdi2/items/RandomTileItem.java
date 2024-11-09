package com.prineside.tdi2.items;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ItemCategoryType;
import com.prineside.tdi2.enums.ItemSubcategoryType;
import com.prineside.tdi2.enums.ItemType;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.managers.ProgressManager;
import com.prineside.tdi2.managers.preferences.categories.ProgressPrefs;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.utils.REGS;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/items/RandomTileItem.class */
public class RandomTileItem extends Item {
    public float quality;

    /* synthetic */ RandomTileItem(float f, byte b2) {
        this(f);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/RandomTileItem$Serializer.class */
    public static class Serializer extends com.esotericsoftware.kryo.Serializer<RandomTileItem> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, RandomTileItem randomTileItem) {
            output.writeFloat(randomTileItem.quality);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public RandomTileItem read2(Kryo kryo, Input input, Class<? extends RandomTileItem> cls) {
            return Item.D.F_RANDOM_TILE.create(input.readFloat());
        }
    }

    private RandomTileItem(float f) {
        this.quality = MathUtils.round(((f < 0.0f ? 0.0f : f) > 1.0f ? 1.0f : r5) * 100.0f) * 0.01f;
    }

    @Override // com.prineside.tdi2.Item
    public Item from(Item item) {
        return Item.D.F_RANDOM_TILE.create(((RandomTileItem) item).quality);
    }

    @Override // com.prineside.tdi2.Item
    public String getAnalyticName() {
        return "random_tile_" + (MathUtils.round(this.quality * 10.0f) * 10);
    }

    @Override // com.prineside.tdi2.Item
    public boolean canBeSold() {
        return true;
    }

    @Override // com.prineside.tdi2.Item
    public void addSellItems(Array<ItemStack> array) {
        array.add(new ItemStack(Item.D.PRESTIGE_DUST, MathUtils.ceil(((199.0f * this.quality) + 1.0f) * ((float) Game.i.gameValueManager.getSnapshot().getPercentValueAsMultiplier(GameValueType.PRESTIGE_DUST_DROP_RATE)))));
    }

    @Override // com.prineside.tdi2.Item
    public Item cpy() {
        return Item.D.F_RANDOM_TILE.create(this.quality);
    }

    @Override // com.prineside.tdi2.Item
    public ItemType getType() {
        return ItemType.RANDOM_TILE;
    }

    @Override // com.prineside.tdi2.Item
    public ItemCategoryType getCategory() {
        return ItemCategoryType.PACKS;
    }

    @Override // com.prineside.tdi2.Item
    public ItemSubcategoryType getSubcategory() {
        return ItemSubcategoryType.P_DECRYPTED;
    }

    @Override // com.prineside.tdi2.Item
    public boolean canBeUnpacked() {
        return true;
    }

    @Override // com.prineside.tdi2.Item
    public Array<ItemStack> openPack(ProgressManager.InventoryStatistics inventoryStatistics) {
        Array<ItemStack> array = new Array<>(false, 1, ItemStack.class);
        TileItem create = Item.D.F_TILE.create(Game.i.tileManager.createRandomTile(this.quality, ProgressPrefs.i().progress.getOtherItemsRandom(), inventoryStatistics));
        ProgressPrefs.i().requireSave();
        array.add(new ItemStack(create, 1));
        return array;
    }

    @Override // com.prineside.tdi2.Item
    public CharSequence getTitle() {
        return Game.i.localeManager.i18n.get("random_tile");
    }

    @Override // com.prineside.tdi2.Item
    public CharSequence getDescription() {
        return Game.i.localeManager.i18n.get("quality") + ": " + MathUtils.round(this.quality * 100.0f) + "/100";
    }

    @Override // com.prineside.tdi2.Item
    public RarityType getRarity() {
        return ProgressManager.getRarityFromQuality(this.quality);
    }

    @Override // com.prineside.tdi2.Item
    public boolean isCountable() {
        return true;
    }

    @Override // com.prineside.tdi2.Item
    public boolean sameAs(Item item) {
        return super.sameAs(item) && ((RandomTileItem) item).quality == this.quality;
    }

    @Override // com.prineside.tdi2.Item
    public void toJson(Json json) {
        super.toJson(json);
        json.writeValue("quality", Float.valueOf(this.quality));
    }

    @Override // com.prineside.tdi2.Item
    public Actor generateIcon(float f, boolean z) {
        float f2 = f / 128.0f;
        Group group = new Group();
        group.setTransform(false);
        Image image = new Image(Game.i.assetManager.getDrawable("random-tile"));
        image.setSize(f, f);
        group.addActor(image);
        Label label = new Label(MathUtils.round(this.quality * 100.0f) + "%", Game.i.assetManager.getLabelStyle(MathUtils.round(21.0f * f2)));
        label.setPosition(34.0f * f2, 73.0f * f2);
        group.addActor(label);
        return group;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/RandomTileItem$RandomTileItemFactory.class */
    public static class RandomTileItemFactory implements Item.Factory<RandomTileItem> {
        @Override // com.prineside.tdi2.Item.Factory
        public void setup() {
        }

        public RandomTileItem create(float f) {
            return new RandomTileItem(f, (byte) 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public RandomTileItem fromJson(JsonValue jsonValue) {
            return create(jsonValue.getFloat("quality"));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public RandomTileItem createDefault() {
            return create(0.0f);
        }
    }
}
