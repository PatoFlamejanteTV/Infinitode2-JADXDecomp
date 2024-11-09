package com.prineside.tdi2.items;

import com.badlogic.gdx.utils.JsonValue;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.enums.ItemCategoryType;
import com.prineside.tdi2.enums.ItemSubcategoryType;
import com.prineside.tdi2.enums.ItemType;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.enums.ResearchType;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.utils.REGS;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/items/PrestigeTokenItem.class */
public class PrestigeTokenItem extends Item implements Item.UsableItem {
    /* synthetic */ PrestigeTokenItem(byte b2) {
        this();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/PrestigeTokenItem$Serializer.class */
    public static class Serializer extends SingletonSerializer<PrestigeTokenItem> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public PrestigeTokenItem read() {
            return Item.D.PRESTIGE_TOKEN;
        }
    }

    private PrestigeTokenItem() {
    }

    @Override // com.prineside.tdi2.Item
    public ItemType getType() {
        return ItemType.PRESTIGE_TOKEN;
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
        return Game.i.localeManager.i18n.get("item_title_PRESTIGE_TOKEN");
    }

    @Override // com.prineside.tdi2.Item
    public CharSequence getDescription() {
        return Game.i.localeManager.i18n.get("item_description_PRESTIGE_TOKEN");
    }

    @Override // com.prineside.tdi2.Item
    public String getAnalyticName() {
        return "prestige_token";
    }

    @Override // com.prineside.tdi2.Item
    public double getPriceInAcceleratorsForResearchReset(int i) {
        return Math.pow(i * 0.01d, 0.95d);
    }

    @Override // com.prineside.tdi2.Item
    public RarityType getRarity() {
        return RarityType.LEGENDARY;
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
            Image image = new Image(Item.D.F_PRESTIGE_TOKEN.f2252b);
            image.setSize(f, f);
            image.setColor(0.0f, 0.0f, 0.0f, 0.28f);
            image.setPosition(a(f), -a(f));
            group.addActor(image);
            Image image2 = new Image(Item.D.F_PRESTIGE_TOKEN.f2252b);
            image2.setSize(f, f);
            group.addActor(image2);
            return group;
        }
        Image image3 = new Image(Item.D.F_PRESTIGE_TOKEN.f2252b);
        image3.setSize(f, f);
        return image3;
    }

    @Override // com.prineside.tdi2.Item.UsableItem
    public boolean autoUseWhenAdded() {
        return false;
    }

    @Override // com.prineside.tdi2.Item.UsableItem
    public boolean useItem() {
        Game.i.screenManager.goToResearchesScreenFocusOnResearch(ResearchType.PRESTIGE);
        return false;
    }

    @Override // com.prineside.tdi2.Item.UsableItem
    public boolean useItemNeedsConfirmation() {
        return false;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/PrestigeTokenItem$PrestigeTokenItemFactory.class */
    public static class PrestigeTokenItemFactory implements Item.Factory<PrestigeTokenItem> {

        /* renamed from: a, reason: collision with root package name */
        private final PrestigeTokenItem f2251a = new PrestigeTokenItem(0);

        /* renamed from: b, reason: collision with root package name */
        private Drawable f2252b;

        @Override // com.prineside.tdi2.Item.Factory
        public void setup() {
            if (Game.i.assetManager != null) {
                this.f2252b = Game.i.assetManager.getDrawable("prestige-token");
            }
        }

        public PrestigeTokenItem create() {
            return this.f2251a;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public PrestigeTokenItem fromJson(JsonValue jsonValue) {
            return create();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public PrestigeTokenItem createDefault() {
            return create();
        }
    }
}
