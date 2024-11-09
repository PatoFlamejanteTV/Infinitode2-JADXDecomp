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
/* loaded from: infinitode-2.jar:com/prineside/tdi2/items/PrestigeDustItem.class */
public class PrestigeDustItem extends Item {
    public static final double RAW_PRESTIGE_COUNT_MULTIPLIER = 0.25d;

    /* synthetic */ PrestigeDustItem(byte b2) {
        this();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/PrestigeDustItem$Serializer.class */
    public static class Serializer extends SingletonSerializer<PrestigeDustItem> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public PrestigeDustItem read() {
            return Item.D.PRESTIGE_DUST;
        }
    }

    private PrestigeDustItem() {
    }

    @Override // com.prineside.tdi2.Item
    public ItemType getType() {
        return ItemType.PRESTIGE_DUST;
    }

    @Override // com.prineside.tdi2.Item
    public ItemCategoryType getCategory() {
        return ItemCategoryType.MATERIALS;
    }

    @Override // com.prineside.tdi2.Item
    public ItemSubcategoryType getSubcategory() {
        return ItemSubcategoryType.M_DUST;
    }

    @Override // com.prineside.tdi2.Item
    public CharSequence getTitle() {
        return Game.i.localeManager.i18n.get("item_title_PRESTIGE_DUST");
    }

    @Override // com.prineside.tdi2.Item
    public CharSequence getDescription() {
        return Game.i.localeManager.i18n.get("item_description_PRESTIGE_DUST");
    }

    @Override // com.prineside.tdi2.Item
    public RarityType getRarity() {
        return RarityType.EPIC;
    }

    @Override // com.prineside.tdi2.Item
    public String getAnalyticName() {
        return "prestige_dust";
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
            Image image = new Image(Game.i.assetManager.getDrawable("dust-item-prestige"));
            image.setSize(f, f);
            image.setPosition(a(f), -a(f));
            image.setColor(0.0f, 0.0f, 0.0f, 0.28f);
            group.addActor(image);
            Image image2 = new Image(Game.i.assetManager.getDrawable("dust-item-prestige"));
            image2.setSize(f, f);
            group.addActor(image2);
            return group;
        }
        Image image3 = new Image(Game.i.assetManager.getDrawable("dust-item-prestige"));
        image3.setSize(f, f);
        return image3;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/PrestigeDustItem$PrestigeDustItemFactory.class */
    public static class PrestigeDustItemFactory implements Item.Factory<PrestigeDustItem> {

        /* renamed from: a, reason: collision with root package name */
        private final PrestigeDustItem f2250a = new PrestigeDustItem(0);

        @Override // com.prineside.tdi2.Item.Factory
        public void setup() {
        }

        public PrestigeDustItem create() {
            return this.f2250a;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public PrestigeDustItem fromJson(JsonValue jsonValue) {
            return create();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public PrestigeDustItem createDefault() {
            return create();
        }
    }
}
