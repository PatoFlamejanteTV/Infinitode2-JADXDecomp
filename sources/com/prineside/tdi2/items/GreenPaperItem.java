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
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.REGS;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/items/GreenPaperItem.class */
public class GreenPaperItem extends Item {
    /* synthetic */ GreenPaperItem(byte b2) {
        this();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/GreenPaperItem$Serializer.class */
    public static class Serializer extends SingletonSerializer<GreenPaperItem> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public GreenPaperItem read() {
            return Item.D.GREEN_PAPER;
        }
    }

    private GreenPaperItem() {
    }

    @Override // com.prineside.tdi2.Item
    public boolean isAffectedByDoubleGain() {
        return true;
    }

    @Override // com.prineside.tdi2.Item
    public ItemType getType() {
        return ItemType.GREEN_PAPER;
    }

    @Override // com.prineside.tdi2.Item
    public ItemCategoryType getCategory() {
        return ItemCategoryType.MATERIALS;
    }

    @Override // com.prineside.tdi2.Item
    public ItemSubcategoryType getSubcategory() {
        return ItemSubcategoryType.M_CURRENCY;
    }

    @Override // com.prineside.tdi2.Item
    public CharSequence getTitle() {
        return Game.i.localeManager.i18n.get("item_title_GREEN_PAPER");
    }

    @Override // com.prineside.tdi2.Item
    public CharSequence getDescription() {
        return Game.i.localeManager.i18n.get("item_description_GREEN_PAPER");
    }

    @Override // com.prineside.tdi2.Item
    public String getAnalyticName() {
        return "green_paper";
    }

    @Override // com.prineside.tdi2.Item
    public double getPriceInAcceleratorsForResearchReset(int i) {
        return Math.sqrt(i * 5.0E-4d);
    }

    @Override // com.prineside.tdi2.Item
    public RarityType getRarity() {
        return RarityType.COMMON;
    }

    @Override // com.prineside.tdi2.Item
    public boolean isCountable() {
        return true;
    }

    @Override // com.prineside.tdi2.Item
    public Drawable getIconDrawable() {
        return Item.D.F_GREEN_PAPER.f2245b;
    }

    @Override // com.prineside.tdi2.Item
    public Actor generateIcon(float f, boolean z) {
        if (z) {
            Group group = new Group();
            group.setTransform(false);
            group.setSize(f, f);
            Image image = new Image(Game.i.assetManager.getDrawable("icon-money"));
            image.setSize(f, f);
            image.setColor(0.0f, 0.0f, 0.0f, 0.28f);
            image.setPosition(a(f), -a(f));
            group.addActor(image);
            Image image2 = new Image(Game.i.assetManager.getDrawable("icon-money"));
            image2.setSize(f, f);
            image2.setColor(MaterialColor.GREEN.P500);
            group.addActor(image2);
            return group;
        }
        Image image3 = new Image(Game.i.assetManager.getDrawable("icon-money"));
        image3.setSize(f, f);
        image3.setColor(MaterialColor.GREEN.P500);
        return image3;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/GreenPaperItem$GreenPaperItemFactory.class */
    public static class GreenPaperItemFactory implements Item.Factory<GreenPaperItem> {

        /* renamed from: a, reason: collision with root package name */
        private final GreenPaperItem f2244a = new GreenPaperItem(0);

        /* renamed from: b, reason: collision with root package name */
        private Drawable f2245b;

        @Override // com.prineside.tdi2.Item.Factory
        public void setup() {
            if (Game.i.assetManager != null) {
                this.f2245b = Game.i.assetManager.getDrawable("icon-money").tint(MaterialColor.LIGHT_GREEN.P500);
            }
        }

        public GreenPaperItem create() {
            return this.f2244a;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public GreenPaperItem fromJson(JsonValue jsonValue) {
            return create();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public GreenPaperItem createDefault() {
            return create();
        }
    }
}
