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
import com.prineside.tdi2.utils.REGS;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/items/DatPaperItem.class */
public class DatPaperItem extends Item {
    /* synthetic */ DatPaperItem(byte b2) {
        this();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/DatPaperItem$Serializer.class */
    public static class Serializer extends SingletonSerializer<DatPaperItem> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public DatPaperItem read() {
            return Item.D.DAT_PAPER;
        }
    }

    private DatPaperItem() {
    }

    @Override // com.prineside.tdi2.Item
    public ItemType getType() {
        return ItemType.DAT_PAPER;
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
        return Game.i.localeManager.i18n.get("item_title_DAT_PAPER");
    }

    @Override // com.prineside.tdi2.Item
    public CharSequence getDescription() {
        return Game.i.localeManager.i18n.get("item_description_DAT_PAPER");
    }

    @Override // com.prineside.tdi2.Item
    public String getAnalyticName() {
        return "dat_paper";
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
            Image image = new Image(Game.i.assetManager.getDrawable("icon-dat-paper"));
            image.setSize(f, f);
            image.setColor(0.0f, 0.0f, 0.0f, 0.28f);
            image.setPosition(a(f), -a(f));
            group.addActor(image);
            Image image2 = new Image(Game.i.assetManager.getDrawable("icon-dat-paper"));
            image2.setSize(f, f);
            group.addActor(image2);
            return group;
        }
        Image image3 = new Image(Game.i.assetManager.getDrawable("icon-dat-paper"));
        image3.setSize(f, f);
        return image3;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/DatPaperItem$DatPaperItemFactory.class */
    public static class DatPaperItemFactory implements Item.Factory<DatPaperItem> {

        /* renamed from: a, reason: collision with root package name */
        private final DatPaperItem f2234a = new DatPaperItem(0);

        @Override // com.prineside.tdi2.Item.Factory
        public void setup() {
        }

        public DatPaperItem create() {
            return this.f2234a;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public DatPaperItem fromJson(JsonValue jsonValue) {
            return create();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public DatPaperItem createDefault() {
            return create();
        }
    }
}
