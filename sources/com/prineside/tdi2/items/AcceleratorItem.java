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
import com.prineside.tdi2.utils.REGS;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/items/AcceleratorItem.class */
public class AcceleratorItem extends Item {
    /* synthetic */ AcceleratorItem(byte b2) {
        this();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/AcceleratorItem$Serializer.class */
    public static class Serializer extends SingletonSerializer<AcceleratorItem> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public AcceleratorItem read() {
            return Item.D.ACCELERATOR;
        }
    }

    private AcceleratorItem() {
    }

    @Override // com.prineside.tdi2.Item
    public ItemType getType() {
        return ItemType.ACCELERATOR;
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
    public Actor generateIcon(float f, boolean z) {
        if (z) {
            Group group = new Group();
            group.setTransform(false);
            group.setSize(f, f);
            Image image = new Image(Item.D.F_ACCELERATOR.f2218a);
            image.setSize(f, f);
            image.setPosition(a(f), -a(f));
            image.setColor(0.0f, 0.0f, 0.0f, 0.28f);
            group.addActor(image);
            Image image2 = new Image(Item.D.F_ACCELERATOR.f2218a);
            image2.setSize(f, f);
            group.addActor(image2);
            return group;
        }
        Image image3 = new Image(Item.D.F_ACCELERATOR.f2218a);
        image3.setSize(f, f);
        return image3;
    }

    @Override // com.prineside.tdi2.Item
    public CharSequence getTitle() {
        return Game.i.localeManager.i18n.get("item_title_ACCELERATOR");
    }

    @Override // com.prineside.tdi2.Item
    public CharSequence getDescription() {
        return Game.i.localeManager.i18n.get("item_description_ACCELERATOR");
    }

    @Override // com.prineside.tdi2.Item
    public RarityType getRarity() {
        return RarityType.VERY_RARE;
    }

    @Override // com.prineside.tdi2.Item
    public String getAnalyticName() {
        return "accelerator";
    }

    @Override // com.prineside.tdi2.Item
    public boolean isCountable() {
        return true;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/AcceleratorItem$AcceleratorItemFactory.class */
    public static class AcceleratorItemFactory implements Item.Factory<AcceleratorItem> {

        /* renamed from: b, reason: collision with root package name */
        private final AcceleratorItem f2217b = new AcceleratorItem(0);

        /* renamed from: a, reason: collision with root package name */
        Drawable f2218a;

        @Override // com.prineside.tdi2.Item.Factory
        public void setup() {
            if (Game.i.assetManager != null) {
                this.f2218a = Game.i.assetManager.getDrawable("time-accelerator");
            }
        }

        public AcceleratorItem create() {
            return this.f2217b;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public AcceleratorItem fromJson(JsonValue jsonValue) {
            return create();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public AcceleratorItem createDefault() {
            return create();
        }
    }
}
