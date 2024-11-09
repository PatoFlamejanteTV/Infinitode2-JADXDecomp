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
/* loaded from: infinitode-2.jar:com/prineside/tdi2/items/ResearchTokenUsedItem.class */
public class ResearchTokenUsedItem extends Item {
    /* synthetic */ ResearchTokenUsedItem(byte b2) {
        this();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/ResearchTokenUsedItem$Serializer.class */
    public static class Serializer extends SingletonSerializer<ResearchTokenUsedItem> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public ResearchTokenUsedItem read() {
            return Item.D.RESEARCH_TOKEN_USED;
        }
    }

    private ResearchTokenUsedItem() {
    }

    @Override // com.prineside.tdi2.Item
    public boolean isAffectedByDoubleGain() {
        return false;
    }

    @Override // com.prineside.tdi2.Item
    public ItemType getType() {
        return ItemType.RESEARCH_TOKEN_USED;
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
        return Game.i.localeManager.i18n.get("item_title_RESEARCH_TOKEN_USED");
    }

    @Override // com.prineside.tdi2.Item
    public CharSequence getDescription() {
        return Game.i.localeManager.i18n.get("item_description_RESEARCH_TOKEN_USED");
    }

    @Override // com.prineside.tdi2.Item
    public RarityType getRarity() {
        return RarityType.LEGENDARY;
    }

    @Override // com.prineside.tdi2.Item
    public String getAnalyticName() {
        return "research_token_used";
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
            Image image = new Image(Item.D.F_RESEARCH_TOKEN_USED.f2259b);
            image.setSize(f, f);
            image.setColor(0.0f, 0.0f, 0.0f, 0.28f);
            image.setPosition(a(f), -a(f));
            group.addActor(image);
            Image image2 = new Image(Item.D.F_RESEARCH_TOKEN_USED.f2259b);
            image2.setSize(f, f);
            group.addActor(image2);
            return group;
        }
        Image image3 = new Image(Item.D.F_RESEARCH_TOKEN_USED.f2259b);
        image3.setSize(f, f);
        return image3;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/ResearchTokenUsedItem$ResearchTokenUsedItemFactory.class */
    public static class ResearchTokenUsedItemFactory implements Item.Factory<ResearchTokenUsedItem> {

        /* renamed from: a, reason: collision with root package name */
        private final ResearchTokenUsedItem f2258a = new ResearchTokenUsedItem(0);

        /* renamed from: b, reason: collision with root package name */
        private Drawable f2259b;

        @Override // com.prineside.tdi2.Item.Factory
        public void setup() {
            if (Game.i.assetManager != null) {
                this.f2259b = Game.i.assetManager.getDrawable("research-token-used");
            }
        }

        public ResearchTokenUsedItem create() {
            return this.f2258a;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public ResearchTokenUsedItem fromJson(JsonValue jsonValue) {
            return create();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public ResearchTokenUsedItem createDefault() {
            return create();
        }
    }
}
