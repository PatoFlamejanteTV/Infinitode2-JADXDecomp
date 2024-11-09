package com.prineside.tdi2.items;

import com.badlogic.gdx.utils.JsonValue;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.enums.ItemCategoryType;
import com.prineside.tdi2.enums.ItemSubcategoryType;
import com.prineside.tdi2.enums.ItemType;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.ui.shared.Dialog;
import com.prineside.tdi2.utils.REGS;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/items/RarityBoostItem.class */
public class RarityBoostItem extends Item implements Item.UsableItem {
    public static final int PERCENTAGE_BONUS = 50;

    /* synthetic */ RarityBoostItem(byte b2) {
        this();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/RarityBoostItem$Serializer.class */
    public static class Serializer extends SingletonSerializer<RarityBoostItem> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public RarityBoostItem read() {
            return Item.D.RARITY_BOOST;
        }
    }

    private RarityBoostItem() {
    }

    @Override // com.prineside.tdi2.Item
    public boolean isAffectedByDoubleGain() {
        return true;
    }

    @Override // com.prineside.tdi2.Item
    public ItemType getType() {
        return ItemType.RARITY_BOOST;
    }

    @Override // com.prineside.tdi2.Item
    public ItemCategoryType getCategory() {
        return ItemCategoryType.MATERIALS;
    }

    @Override // com.prineside.tdi2.Item
    public ItemSubcategoryType getSubcategory() {
        return ItemSubcategoryType.M_TOKENS;
    }

    @Override // com.prineside.tdi2.Item
    public CharSequence getTitle() {
        return Game.i.localeManager.i18n.get("item_title_RARITY_BOOST");
    }

    @Override // com.prineside.tdi2.Item
    public CharSequence getDescription() {
        return Game.i.localeManager.i18n.format("item_description_RARITY_BOOST", 50);
    }

    @Override // com.prineside.tdi2.Item
    public RarityType getRarity() {
        return RarityType.LEGENDARY;
    }

    @Override // com.prineside.tdi2.Item
    public String getAnalyticName() {
        return "rarity_boost";
    }

    @Override // com.prineside.tdi2.Item
    public boolean isCountable() {
        return true;
    }

    @Override // com.prineside.tdi2.Item
    public Actor generateIcon(float f, boolean z) {
        if (z) {
            Group group = new Group();
            group.setTransform(false);
            group.setSize(f, f);
            Image image = new Image(Game.i.assetManager.getDrawable("rarity-token"));
            image.setSize(f, f);
            image.setColor(0.0f, 0.0f, 0.0f, 0.28f);
            image.setPosition(a(f), -a(f));
            group.addActor(image);
            Image image2 = new Image(Game.i.assetManager.getDrawable("rarity-token"));
            image2.setSize(f, f);
            group.addActor(image2);
            return group;
        }
        Image image3 = new Image(Game.i.assetManager.getDrawable("rarity-token"));
        image3.setSize(f, f);
        return image3;
    }

    @Override // com.prineside.tdi2.Item.UsableItem
    public boolean autoUseWhenAdded() {
        return false;
    }

    @Override // com.prineside.tdi2.Item.UsableItem
    public boolean useItem() {
        Dialog.i().showAlert(Game.i.localeManager.i18n.get("rarity_boost_use_hint"));
        return false;
    }

    @Override // com.prineside.tdi2.Item.UsableItem
    public boolean useItemNeedsConfirmation() {
        return false;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/RarityBoostItem$RarityBoostItemFactory.class */
    public static class RarityBoostItemFactory implements Item.Factory<RarityBoostItem> {

        /* renamed from: a, reason: collision with root package name */
        private final RarityBoostItem f2255a = new RarityBoostItem(0);

        @Override // com.prineside.tdi2.Item.Factory
        public void setup() {
        }

        public RarityBoostItem create() {
            return this.f2255a;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public RarityBoostItem fromJson(JsonValue jsonValue) {
            return create();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public RarityBoostItem createDefault() {
            return create();
        }
    }
}
