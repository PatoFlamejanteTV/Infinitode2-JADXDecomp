package com.prineside.tdi2.items;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
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
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.utils.REGS;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/items/RandomTeleportItem.class */
public class RandomTeleportItem extends Item {
    /* synthetic */ RandomTeleportItem(byte b2) {
        this();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/RandomTeleportItem$Serializer.class */
    public static class Serializer extends SingletonSerializer<RandomTeleportItem> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public RandomTeleportItem read() {
            return Item.D.RANDOM_TELEPORT;
        }
    }

    private RandomTeleportItem() {
    }

    @Override // com.prineside.tdi2.Item
    public ItemType getType() {
        return ItemType.RANDOM_TELEPORT;
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
        return "random_teleport";
    }

    @Override // com.prineside.tdi2.Item
    public boolean canBeUnpacked() {
        return true;
    }

    @Override // com.prineside.tdi2.Item
    public Array<ItemStack> openPack(ProgressManager.InventoryStatistics inventoryStatistics) {
        Array<ItemStack> array = new Array<>(false, 1, ItemStack.class);
        array.add(new ItemStack(Item.D.F_GATE.create(Game.i.gateManager.createRandomGate(GateType.TELEPORT, 1.0f, ProgressPrefs.i().progress.getOtherItemsRandom())), 1));
        ProgressPrefs.i().requireSave();
        return array;
    }

    @Override // com.prineside.tdi2.Item
    public CharSequence getTitle() {
        return Game.i.localeManager.i18n.get("random_teleport");
    }

    @Override // com.prineside.tdi2.Item
    public CharSequence getDescription() {
        return "";
    }

    @Override // com.prineside.tdi2.Item
    public RarityType getRarity() {
        return RarityType.VERY_RARE;
    }

    @Override // com.prineside.tdi2.Item
    public boolean isCountable() {
        return true;
    }

    @Override // com.prineside.tdi2.Item
    public Actor generateIcon(float f, boolean z) {
        Image image = new Image(Game.i.assetManager.getDrawable("random-teleport"));
        image.setSize(f, f);
        return image;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/RandomTeleportItem$RandomTeleportItemFactory.class */
    public static class RandomTeleportItemFactory implements Item.Factory<RandomTeleportItem> {

        /* renamed from: a, reason: collision with root package name */
        private RandomTeleportItem f2254a;

        @Override // com.prineside.tdi2.Item.Factory
        public void setup() {
        }

        public RandomTeleportItem create() {
            if (this.f2254a == null) {
                this.f2254a = new RandomTeleportItem((byte) 0);
            }
            return this.f2254a;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public RandomTeleportItem fromJson(JsonValue jsonValue) {
            return create();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public RandomTeleportItem createDefault() {
            return create();
        }
    }
}
