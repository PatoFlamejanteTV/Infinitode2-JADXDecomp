package com.prineside.tdi2.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.enums.BlueprintType;
import com.prineside.tdi2.enums.ItemCategoryType;
import com.prineside.tdi2.enums.ItemDataType;
import com.prineside.tdi2.enums.ItemSubcategoryType;
import com.prineside.tdi2.enums.ItemType;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.utils.REGS;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/items/BlueprintItem.class */
public class BlueprintItem extends Item {

    /* renamed from: a, reason: collision with root package name */
    private BlueprintType f2221a;

    /* synthetic */ BlueprintItem(BlueprintType blueprintType, byte b2) {
        this(blueprintType);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/BlueprintItem$Serializer.class */
    public static class Serializer extends SingletonSerializer<BlueprintItem> {
        @Override // com.prineside.tdi2.serializers.SingletonSerializer, com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public /* bridge */ /* synthetic */ Object read2(Kryo kryo, Input input, Class cls) {
            return read2(kryo, input, (Class<? extends BlueprintItem>) cls);
        }

        @Override // com.prineside.tdi2.serializers.SingletonSerializer, com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, BlueprintItem blueprintItem) {
            kryo.writeObject(output, blueprintItem.f2221a);
        }

        @Override // com.prineside.tdi2.serializers.SingletonSerializer, com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public BlueprintItem read2(Kryo kryo, Input input, Class<? extends BlueprintItem> cls) {
            return Item.D.F_BLUEPRINT.create((BlueprintType) kryo.readObject(input, BlueprintType.class));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public BlueprintItem read() {
            throw new IllegalStateException("Do not use");
        }
    }

    private BlueprintItem(BlueprintType blueprintType) {
        this.f2221a = blueprintType;
    }

    @Override // com.prineside.tdi2.Item
    public boolean isAffectedByDoubleGain() {
        return true;
    }

    @Override // com.prineside.tdi2.Item
    public IntArray getData() {
        IntArray data = super.getData();
        data.add(ItemDataType.TYPE.ordinal(), this.f2221a.ordinal());
        return data;
    }

    @Override // com.prineside.tdi2.Item
    public boolean canBeSold() {
        return true;
    }

    @Override // com.prineside.tdi2.Item
    public Item from(Item item) {
        return Item.D.F_BLUEPRINT.create(((BlueprintItem) item).f2221a);
    }

    @Override // com.prineside.tdi2.Item
    public void addSellItems(Array<ItemStack> array) {
        int i = 1;
        switch (this.f2221a) {
            case POWER:
            case AGILITY:
            case EXPERIENCE:
                i = 300;
                break;
            case SPECIAL_I:
                i = 1000;
                break;
            case SPECIAL_II:
                i = 2500;
                break;
            case SPECIAL_III:
                i = 6500;
                break;
            case SPECIAL_IV:
                i = 15000;
                break;
        }
        array.add(new ItemStack(Item.D.GREEN_PAPER, i));
    }

    @Override // com.prineside.tdi2.Item
    public double getPriceInAcceleratorsForResearchReset(int i) {
        int i2 = i;
        switch (this.f2221a) {
            case POWER:
            case AGILITY:
            case EXPERIENCE:
                i2 = i * 300;
                break;
            case SPECIAL_I:
                i2 = i * 1000;
                break;
            case SPECIAL_II:
                i2 = i * 2500;
                break;
            case SPECIAL_III:
                i2 = i * 6500;
                break;
            case SPECIAL_IV:
                i2 = i * 15000;
                break;
        }
        return Item.D.GREEN_PAPER.getPriceInAcceleratorsForResearchReset(i2);
    }

    @Override // com.prineside.tdi2.Item
    public CharSequence getTitle() {
        switch (this.f2221a) {
            case POWER:
                return Game.i.localeManager.i18n.get("item_title_BLUEPRINT_POWER");
            case AGILITY:
                return Game.i.localeManager.i18n.get("item_title_BLUEPRINT_AGILITY");
            case EXPERIENCE:
                return Game.i.localeManager.i18n.get("item_title_BLUEPRINT_EXPERIENCE");
            case SPECIAL_I:
                return Game.i.localeManager.i18n.get("item_title_BLUEPRINT_SPECIAL_I");
            case SPECIAL_II:
                return Game.i.localeManager.i18n.get("item_title_BLUEPRINT_SPECIAL_II");
            case SPECIAL_III:
                return Game.i.localeManager.i18n.get("item_title_BLUEPRINT_SPECIAL_III");
            case SPECIAL_IV:
                return Game.i.localeManager.i18n.get("item_title_BLUEPRINT_SPECIAL_IV");
            default:
                return null;
        }
    }

    @Override // com.prineside.tdi2.Item
    public CharSequence getDescription() {
        return Game.i.localeManager.i18n.get("item_description_BLUEPRINT");
    }

    public BlueprintType getBlueprintType() {
        return this.f2221a;
    }

    @Override // com.prineside.tdi2.Item
    public RarityType getRarity() {
        switch (this.f2221a) {
            case POWER:
            case AGILITY:
            case EXPERIENCE:
                return RarityType.COMMON;
            case SPECIAL_I:
                return RarityType.RARE;
            case SPECIAL_II:
                return RarityType.VERY_RARE;
            case SPECIAL_III:
                return RarityType.EPIC;
            case SPECIAL_IV:
                return RarityType.LEGENDARY;
            default:
                return null;
        }
    }

    @Override // com.prineside.tdi2.Item
    public boolean isCountable() {
        return true;
    }

    @Override // com.prineside.tdi2.Item
    public void toJson(Json json) {
        super.toJson(json);
        json.writeValue("blueprintType", this.f2221a.name());
    }

    public String toString() {
        return "BlueprintItem@" + Integer.toHexString(hashCode()) + " (type: " + this.f2221a.name() + ")";
    }

    @Override // com.prineside.tdi2.Item
    public ItemType getType() {
        return ItemType.BLUEPRINT;
    }

    @Override // com.prineside.tdi2.Item
    public ItemCategoryType getCategory() {
        return ItemCategoryType.MATERIALS;
    }

    @Override // com.prineside.tdi2.Item
    public ItemSubcategoryType getSubcategory() {
        return ItemSubcategoryType.M_BLUEPRINT;
    }

    @Override // com.prineside.tdi2.Item
    public String getAnalyticName() {
        return "blueprint_" + this.f2221a;
    }

    @Override // com.prineside.tdi2.Item
    public Drawable getIconDrawable() {
        return Item.D.F_BLUEPRINT.c[this.f2221a.ordinal()];
    }

    @Override // com.prineside.tdi2.Item
    public Actor generateIcon(float f, boolean z) {
        if (z) {
            Group group = new Group();
            group.setTransform(false);
            group.setSize(f, f);
            Image image = new Image(Item.D.F_BLUEPRINT.f2224b[this.f2221a.ordinal()]);
            image.setSize(f, f);
            image.setPosition(a(f), -a(f));
            image.setColor(0.0f, 0.0f, 0.0f, 0.28f);
            group.addActor(image);
            Image image2 = new Image(Item.D.F_BLUEPRINT.f2224b[this.f2221a.ordinal()]);
            image2.setSize(f, f);
            group.addActor(image2);
            return group;
        }
        Image image3 = new Image(Item.D.F_BLUEPRINT.f2224b[this.f2221a.ordinal()]);
        image3.setSize(f, f);
        return image3;
    }

    @Override // com.prineside.tdi2.Item
    public boolean sameAs(Item item) {
        return super.sameAs(item) && ((BlueprintItem) item).f2221a == this.f2221a;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/BlueprintItem$BlueprintItemFactory.class */
    public static class BlueprintItemFactory implements Item.Factory<BlueprintItem> {

        /* renamed from: a, reason: collision with root package name */
        private BlueprintItem[] f2223a = new BlueprintItem[BlueprintType.values.length];

        /* renamed from: b, reason: collision with root package name */
        private TextureRegion[] f2224b = new TextureRegion[BlueprintType.values.length];
        private Drawable[] c = new Drawable[BlueprintType.values.length];

        public BlueprintItemFactory() {
            for (BlueprintType blueprintType : BlueprintType.values) {
                this.f2223a[blueprintType.ordinal()] = new BlueprintItem(blueprintType, (byte) 0);
            }
        }

        @Override // com.prineside.tdi2.Item.Factory
        public void setup() {
            if (Game.i.assetManager != null) {
                for (BlueprintType blueprintType : BlueprintType.values) {
                    this.f2224b[blueprintType.ordinal()] = Game.i.assetManager.getTextureRegion("blueprint-" + blueprintType.name());
                    this.c[blueprintType.ordinal()] = new TextureRegionDrawable(this.f2224b[blueprintType.ordinal()]);
                }
            }
        }

        public BlueprintItem create(BlueprintType blueprintType) {
            return this.f2223a[blueprintType.ordinal()];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public BlueprintItem fromJson(JsonValue jsonValue) {
            return create(BlueprintType.valueOf(jsonValue.getString("blueprintType")));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public BlueprintItem createDefault() {
            return create(BlueprintType.values[0]);
        }
    }
}
