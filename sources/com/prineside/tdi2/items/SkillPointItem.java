package com.prineside.tdi2.items;

import com.badlogic.gdx.utils.JsonValue;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.enums.ItemCategoryType;
import com.prineside.tdi2.enums.ItemSubcategoryType;
import com.prineside.tdi2.enums.ItemType;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.REGS;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/items/SkillPointItem.class */
public class SkillPointItem extends Item {
    /* synthetic */ SkillPointItem(byte b2) {
        this();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/SkillPointItem$Serializer.class */
    public static class Serializer extends SingletonSerializer<SkillPointItem> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public SkillPointItem read() {
            return Item.D.SKILL_POINT;
        }
    }

    private SkillPointItem() {
    }

    @Override // com.prineside.tdi2.Item
    public ItemType getType() {
        return ItemType.SKILL_POINT;
    }

    @Override // com.prineside.tdi2.Item
    public ItemCategoryType getCategory() {
        return ItemCategoryType.OTHER;
    }

    @Override // com.prineside.tdi2.Item
    public ItemSubcategoryType getSubcategory() {
        return ItemSubcategoryType.O_OTHER;
    }

    @Override // com.prineside.tdi2.Item
    public CharSequence getTitle() {
        return Game.i.localeManager.i18n.get("item_title_SKILL_POINT");
    }

    @Override // com.prineside.tdi2.Item
    public CharSequence getDescription() {
        return Game.i.localeManager.i18n.get("item_description_SKILL_POINT");
    }

    @Override // com.prineside.tdi2.Item
    public String getAnalyticName() {
        return "skill_point";
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
        Image image = new Image(Game.i.assetManager.getDrawable("icon-skill-point"));
        image.setSize(f, f);
        image.setColor(MaterialColor.CYAN.P500);
        return image;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/SkillPointItem$SkillPointItemFactory.class */
    public static class SkillPointItemFactory implements Item.Factory<SkillPointItem> {

        /* renamed from: a, reason: collision with root package name */
        private final SkillPointItem f2263a = new SkillPointItem(0);

        @Override // com.prineside.tdi2.Item.Factory
        public void setup() {
        }

        public SkillPointItem create() {
            return this.f2263a;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public SkillPointItem fromJson(JsonValue jsonValue) {
            return create();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public SkillPointItem createDefault() {
            return create();
        }
    }
}
