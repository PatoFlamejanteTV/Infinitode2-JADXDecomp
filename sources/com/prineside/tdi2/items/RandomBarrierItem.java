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
import com.prineside.tdi2.enums.GateType;
import com.prineside.tdi2.enums.ItemCategoryType;
import com.prineside.tdi2.enums.ItemSubcategoryType;
import com.prineside.tdi2.enums.ItemType;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.managers.ProgressManager;
import com.prineside.tdi2.managers.preferences.categories.ProgressPrefs;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.ui.shared.ItemCreationOverlay;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/items/RandomBarrierItem.class */
public class RandomBarrierItem extends Item {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2253a = TLog.forClass(RandomBarrierItem.class);
    public float quality;

    /* synthetic */ RandomBarrierItem(float f, byte b2) {
        this(f);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/RandomBarrierItem$Serializer.class */
    public static class Serializer extends com.esotericsoftware.kryo.Serializer<RandomBarrierItem> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, RandomBarrierItem randomBarrierItem) {
            output.writeFloat(randomBarrierItem.quality);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public RandomBarrierItem read2(Kryo kryo, Input input, Class<? extends RandomBarrierItem> cls) {
            return Item.D.F_RANDOM_BARRIER.create(input.readFloat());
        }
    }

    private RandomBarrierItem(float f) {
        if (f < 0.0f || f > 1.0f) {
            throw new IllegalArgumentException("Quality is " + f);
        }
        this.quality = f;
    }

    @Override // com.prineside.tdi2.Item
    public Item cpy() {
        return Item.D.F_RANDOM_BARRIER.create(this.quality);
    }

    @Override // com.prineside.tdi2.Item
    public Item from(Item item) {
        return Item.D.F_RANDOM_BARRIER.create(((RandomBarrierItem) item).quality);
    }

    @Override // com.prineside.tdi2.Item
    public ItemType getType() {
        return ItemType.RANDOM_BARRIER;
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
    public String getAnalyticName() {
        return "random_barrier";
    }

    @Override // com.prineside.tdi2.Item
    public boolean canBeUnpacked() {
        return true;
    }

    @Override // com.prineside.tdi2.Item
    public Array<ItemStack> openPack(ProgressManager.InventoryStatistics inventoryStatistics) {
        Array<ItemStack> array = new Array<>(false, 1, ItemStack.class);
        GateItem create = Item.D.F_GATE.create(Game.i.gateManager.createRandomGate(GateType.BARRIER_TYPE, this.quality, ProgressPrefs.i().progress.getOtherItemsRandom()));
        ProgressPrefs.i().requireSave();
        array.add(new ItemStack(create, 1));
        return array;
    }

    @Override // com.prineside.tdi2.Item
    public CharSequence getTitle() {
        return Game.i.localeManager.i18n.get("item_title_RANDOM_BARRIER");
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
        return super.sameAs(item) && ((RandomBarrierItem) item).quality == this.quality;
    }

    @Override // com.prineside.tdi2.Item
    public void toJson(Json json) {
        super.toJson(json);
        json.writeValue("quality", Float.valueOf(this.quality));
    }

    @Override // com.prineside.tdi2.Item
    public Actor generateIcon(float f, boolean z) {
        Image image = new Image(Game.i.assetManager.getDrawable("random-barrier"));
        image.setSize(f, f);
        return image;
    }

    @Override // com.prineside.tdi2.Item
    public void fillItemCreationForm(ItemCreationOverlay itemCreationOverlay) {
        itemCreationOverlay.label("Quality");
        itemCreationOverlay.textField(String.valueOf(this.quality), str -> {
            try {
                itemCreationOverlay.currentItem = Item.D.F_RANDOM_BARRIER.create(Float.parseFloat(str));
                itemCreationOverlay.updateItemIcon();
            } catch (Exception unused) {
                f2253a.e("fillItemCreationForm - bad value: " + str, new Object[0]);
            }
        });
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/RandomBarrierItem$RandomBarrierItemFactory.class */
    public static class RandomBarrierItemFactory implements Item.Factory<RandomBarrierItem> {
        @Override // com.prineside.tdi2.Item.Factory
        public void setup() {
        }

        public RandomBarrierItem create(float f) {
            return new RandomBarrierItem(f, (byte) 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public RandomBarrierItem fromJson(JsonValue jsonValue) {
            return create(jsonValue.getFloat("quality"));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public RandomBarrierItem createDefault() {
            return create(0.0f);
        }
    }
}
