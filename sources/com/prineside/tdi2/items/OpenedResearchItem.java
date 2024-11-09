package com.prineside.tdi2.items;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Config;
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
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.REGS;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/items/OpenedResearchItem.class */
public class OpenedResearchItem extends Item {
    public final ResearchType researchType;

    /* synthetic */ OpenedResearchItem(ResearchType researchType, byte b2) {
        this(researchType);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/OpenedResearchItem$Serializer.class */
    public static class Serializer extends com.esotericsoftware.kryo.Serializer<OpenedResearchItem> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, OpenedResearchItem openedResearchItem) {
            kryo.writeObject(output, openedResearchItem.researchType);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public OpenedResearchItem read2(Kryo kryo, Input input, Class<? extends OpenedResearchItem> cls) {
            return Item.D.F_OPENED_RESEARCH.create((ResearchType) kryo.readObject(input, ResearchType.class));
        }
    }

    private OpenedResearchItem(ResearchType researchType) {
        if (researchType == null) {
            throw new IllegalArgumentException("ResearchType is null");
        }
        this.researchType = researchType;
    }

    @Override // com.prineside.tdi2.Item
    public Item from(Item item) {
        return Item.D.F_OPENED_RESEARCH.create(((OpenedResearchItem) item).researchType);
    }

    @Override // com.prineside.tdi2.Item
    public Item cpy() {
        return Item.D.F_OPENED_RESEARCH.create(this.researchType);
    }

    @Override // com.prineside.tdi2.Item
    public void toJson(Json json) {
        super.toJson(json);
        json.writeValue("researchType", this.researchType.name());
    }

    @Override // com.prineside.tdi2.Item
    public CharSequence getTitle() {
        return Game.i.researchManager.getResearchInstance(this.researchType).getTitle();
    }

    @Override // com.prineside.tdi2.Item
    public CharSequence getDescription() {
        return Game.i.localeManager.i18n.get("item_description_OPENED_RESEARCH");
    }

    @Override // com.prineside.tdi2.Item
    public RarityType getRarity() {
        return RarityType.LEGENDARY;
    }

    @Override // com.prineside.tdi2.Item
    public String getAnalyticName() {
        return "opened_research";
    }

    @Override // com.prineside.tdi2.Item
    public boolean isCountable() {
        return false;
    }

    @Override // com.prineside.tdi2.Item
    public ItemType getType() {
        return ItemType.OPENED_RESEARCH;
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
    public boolean sameAs(Item item) {
        return super.sameAs(item) && ((OpenedResearchItem) item).researchType == this.researchType;
    }

    @Override // com.prineside.tdi2.Item
    public Actor generateIcon(float f, boolean z) {
        Group group = new Group();
        group.setTransform(false);
        group.setSize(f, f);
        Image image = new Image(Game.i.researchManager.getResearchInstance(this.researchType).category.getIcon());
        image.setSize(f, f);
        group.addActor(image);
        Image image2 = new Image(Game.i.assetManager.getDrawable("icon-research"));
        image2.setSize(f * 0.4f, f * 0.4f);
        image2.setPosition(5.0f, (f * 0.6f) - 5.0f);
        image2.setColor(Config.BACKGROUND_COLOR);
        group.addActor(image2);
        Image image3 = new Image(Game.i.assetManager.getDrawable("icon-research"));
        image3.setSize(f * 0.4f, f * 0.4f);
        image3.setPosition(0.0f, f * 0.6f);
        image3.setColor(MaterialColor.CYAN.P500);
        group.addActor(image3);
        return group;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/OpenedResearchItem$OpenedResearchItemFactory.class */
    public static class OpenedResearchItemFactory implements Item.Factory<OpenedResearchItem> {
        @Override // com.prineside.tdi2.Item.Factory
        public void setup() {
        }

        public OpenedResearchItem create(ResearchType researchType) {
            return new OpenedResearchItem(researchType, (byte) 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public OpenedResearchItem fromJson(JsonValue jsonValue) {
            return create(ResearchType.valueOf(jsonValue.getString("researchType")));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public OpenedResearchItem createDefault() {
            return create(ResearchType.values[0]);
        }
    }
}
