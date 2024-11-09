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
/* loaded from: infinitode-2.jar:com/prineside/tdi2/items/AbilityTokenItem.class */
public class AbilityTokenItem extends Item {
    /* synthetic */ AbilityTokenItem(byte b2) {
        this();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/AbilityTokenItem$Serializer.class */
    public static class Serializer extends SingletonSerializer<AbilityTokenItem> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public AbilityTokenItem read() {
            return Item.D.ABILITY_TOKEN;
        }
    }

    private AbilityTokenItem() {
    }

    @Override // com.prineside.tdi2.Item
    public boolean isAffectedByDoubleGain() {
        return true;
    }

    @Override // com.prineside.tdi2.Item
    public ItemType getType() {
        return ItemType.ABILITY_TOKEN;
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
        return Game.i.localeManager.i18n.get("item_title_ABILITY_TOKEN");
    }

    @Override // com.prineside.tdi2.Item
    public CharSequence getDescription() {
        return Game.i.localeManager.i18n.get("item_description_ABILITY_TOKEN");
    }

    @Override // com.prineside.tdi2.Item
    public RarityType getRarity() {
        return RarityType.EPIC;
    }

    @Override // com.prineside.tdi2.Item
    public String getAnalyticName() {
        return "ability_token";
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
            Image image = new Image(Item.D.F_ABILITY_TOKEN.f2216b);
            image.setSize(f, f);
            image.setColor(0.0f, 0.0f, 0.0f, 0.28f);
            image.setPosition(a(f), -a(f));
            group.addActor(image);
            Image image2 = new Image(Item.D.F_ABILITY_TOKEN.f2216b);
            image2.setSize(f, f);
            group.addActor(image2);
            return group;
        }
        Image image3 = new Image(Item.D.F_ABILITY_TOKEN.f2216b);
        image3.setSize(f, f);
        return image3;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/AbilityTokenItem$AbilityTokenItemFactory.class */
    public static class AbilityTokenItemFactory implements Item.Factory<AbilityTokenItem> {

        /* renamed from: a, reason: collision with root package name */
        private final AbilityTokenItem f2215a = new AbilityTokenItem(0);

        /* renamed from: b, reason: collision with root package name */
        private Drawable f2216b;

        @Override // com.prineside.tdi2.Item.Factory
        public void setup() {
            if (Game.i.assetManager != null) {
                this.f2216b = Game.i.assetManager.getDrawable("ability-token");
            }
        }

        public AbilityTokenItem create() {
            return this.f2215a;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public AbilityTokenItem fromJson(JsonValue jsonValue) {
            return create();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public AbilityTokenItem createDefault() {
            return create();
        }
    }
}
