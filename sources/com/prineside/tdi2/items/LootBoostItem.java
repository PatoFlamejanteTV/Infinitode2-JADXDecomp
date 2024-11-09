package com.prineside.tdi2.items;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.JsonValue;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.enums.ItemCategoryType;
import com.prineside.tdi2.enums.ItemSubcategoryType;
import com.prineside.tdi2.enums.ItemType;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.managers.preferences.categories.ProgressPrefs;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.ui.shared.ResourcesAndMoney;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.TLog;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/items/LootBoostItem.class */
public class LootBoostItem extends Item implements Item.UsableItem {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2246a = TLog.forClass(LootBoostItem.class);
    public static final float EFFECT_DURATION = 7200.0f;
    public static final int PERCENTAGE_BONUS = 50;

    /* synthetic */ LootBoostItem(byte b2) {
        this();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/LootBoostItem$Serializer.class */
    public static class Serializer extends SingletonSerializer<LootBoostItem> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public LootBoostItem read() {
            return Item.D.LOOT_BOOST;
        }
    }

    private LootBoostItem() {
    }

    @Override // com.prineside.tdi2.Item
    public boolean isAffectedByDoubleGain() {
        return true;
    }

    @Override // com.prineside.tdi2.Item
    public ItemType getType() {
        return ItemType.LOOT_BOOST;
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
        return Game.i.localeManager.i18n.get("item_title_LOOT_BOOST");
    }

    @Override // com.prineside.tdi2.Item
    public CharSequence getDescription() {
        return Game.i.localeManager.i18n.format("item_description_LOOT_BOOST", 50, StringFormatter.timePassed(MathUtils.round(7200.0f), false, true));
    }

    @Override // com.prineside.tdi2.Item
    public String getAnalyticName() {
        return "loot_boost";
    }

    @Override // com.prineside.tdi2.Item.UsableItem
    public boolean autoUseWhenAdded() {
        return true;
    }

    @Override // com.prineside.tdi2.Item.UsableItem
    public boolean useItem() {
        if (Game.i.progressManager.removeItems(this, 1)) {
            Game.i.analyticsManager.logCurrencySpent("used", "loot_boost", 1);
            ProgressPrefs.i().progress.setLootBoostTimeLeft(ProgressPrefs.i().progress.getLootBoostTimeLeft() + 7200.0f);
            ProgressPrefs.i().requireSave();
            if (Game.i.uiManager != null) {
                ResourcesAndMoney.i().updateBoosts();
                return true;
            }
            return true;
        }
        f2246a.e("failed to remove item on usage", new Object[0]);
        return false;
    }

    @Override // com.prineside.tdi2.Item.UsableItem
    public boolean useItemNeedsConfirmation() {
        return false;
    }

    @Override // com.prineside.tdi2.Item
    public RarityType getRarity() {
        return RarityType.EPIC;
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
            Image image = new Image(Game.i.assetManager.getDrawable("loot-token"));
            image.setSize(f, f);
            image.setColor(0.0f, 0.0f, 0.0f, 0.28f);
            image.setPosition(a(f), -a(f));
            group.addActor(image);
            Image image2 = new Image(Game.i.assetManager.getDrawable("loot-token"));
            image2.setSize(f, f);
            group.addActor(image2);
            return group;
        }
        Image image3 = new Image(Game.i.assetManager.getDrawable("loot-token"));
        image3.setSize(f, f);
        return image3;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/LootBoostItem$LootBoostItemFactory.class */
    public static class LootBoostItemFactory implements Item.Factory<LootBoostItem> {

        /* renamed from: a, reason: collision with root package name */
        private final LootBoostItem f2247a = new LootBoostItem(0);

        @Override // com.prineside.tdi2.Item.Factory
        public void setup() {
        }

        public LootBoostItem create() {
            return this.f2247a;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public LootBoostItem fromJson(JsonValue jsonValue) {
            return create();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public LootBoostItem createDefault() {
            return create();
        }
    }
}
