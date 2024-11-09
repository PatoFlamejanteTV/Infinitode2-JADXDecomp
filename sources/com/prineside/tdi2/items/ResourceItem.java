package com.prineside.tdi2.items;

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
import com.prineside.tdi2.Resource;
import com.prineside.tdi2.enums.BlueprintType;
import com.prineside.tdi2.enums.ItemCategoryType;
import com.prineside.tdi2.enums.ItemDataType;
import com.prineside.tdi2.enums.ItemSubcategoryType;
import com.prineside.tdi2.enums.ItemType;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.utils.REGS;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/items/ResourceItem.class */
public class ResourceItem extends Item {
    public final ResourceType resourceType;

    /* synthetic */ ResourceItem(ResourceType resourceType, byte b2) {
        this(resourceType);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/ResourceItem$Serializer.class */
    public static class Serializer extends SingletonSerializer<ResourceItem> {
        @Override // com.prineside.tdi2.serializers.SingletonSerializer, com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public /* bridge */ /* synthetic */ Object read2(Kryo kryo, Input input, Class cls) {
            return read2(kryo, input, (Class<? extends ResourceItem>) cls);
        }

        @Override // com.prineside.tdi2.serializers.SingletonSerializer, com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, ResourceItem resourceItem) {
            kryo.writeObject(output, resourceItem.resourceType);
        }

        @Override // com.prineside.tdi2.serializers.SingletonSerializer, com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public ResourceItem read2(Kryo kryo, Input input, Class<? extends ResourceItem> cls) {
            return Item.D.F_RESOURCE.create((ResourceType) kryo.readObject(input, ResourceType.class));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public ResourceItem read() {
            throw new IllegalStateException("Do not use");
        }
    }

    private ResourceItem(ResourceType resourceType) {
        if (resourceType == null) {
            throw new IllegalArgumentException("ResourceType is null");
        }
        this.resourceType = resourceType;
    }

    @Override // com.prineside.tdi2.Item
    public boolean isAffectedByDoubleGain() {
        return true;
    }

    @Override // com.prineside.tdi2.Item
    public Item from(Item item) {
        return Item.D.F_RESOURCE.create(((ResourceItem) item).resourceType);
    }

    @Override // com.prineside.tdi2.Item
    public IntArray getData() {
        IntArray data = super.getData();
        data.add(ItemDataType.TYPE.ordinal(), this.resourceType.ordinal());
        return data;
    }

    @Override // com.prineside.tdi2.Item
    public Item cpy() {
        return Item.D.F_RESOURCE.create(this.resourceType);
    }

    @Override // com.prineside.tdi2.Item
    public void toJson(Json json) {
        super.toJson(json);
        json.writeValue("resourceType", this.resourceType.name());
    }

    public String toString() {
        return getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + " (type: " + this.resourceType.name() + ")";
    }

    @Override // com.prineside.tdi2.Item
    public String getAnalyticName() {
        return "resource_" + this.resourceType;
    }

    @Override // com.prineside.tdi2.Item
    public boolean canBeSold() {
        return true;
    }

    @Override // com.prineside.tdi2.Item
    public void addSellItems(Array<ItemStack> array) {
        int i = 1;
        switch (this.resourceType) {
            case SCALAR:
                i = 4;
                break;
            case VECTOR:
                i = 6;
                break;
            case MATRIX:
                i = 8;
                break;
            case TENSOR:
                i = 10;
                break;
            case INFIAR:
                i = 12;
                break;
        }
        array.add(new ItemStack(Item.D.GREEN_PAPER, i));
    }

    @Override // com.prineside.tdi2.Item
    public double getPriceInAcceleratorsForResearchReset(int i) {
        int i2 = i;
        switch (this.resourceType) {
            case SCALAR:
                i2 = 4 * i;
                break;
            case VECTOR:
                i2 = i * 6;
                break;
            case MATRIX:
                i2 = i * 8;
                break;
            case TENSOR:
                i2 = i * 10;
                break;
            case INFIAR:
                i2 = i * 12;
                break;
        }
        return Item.D.GREEN_PAPER.getPriceInAcceleratorsForResearchReset(i2);
    }

    @Override // com.prineside.tdi2.Item
    public ItemType getType() {
        return ItemType.RESOURCE;
    }

    @Override // com.prineside.tdi2.Item
    public ItemCategoryType getCategory() {
        return ItemCategoryType.MATERIALS;
    }

    @Override // com.prineside.tdi2.Item
    public ItemSubcategoryType getSubcategory() {
        return ItemSubcategoryType.M_RESOURCE;
    }

    @Override // com.prineside.tdi2.Item
    public CharSequence getTitle() {
        return Game.i.resourceManager.getName(this.resourceType);
    }

    @Override // com.prineside.tdi2.Item
    public CharSequence getDescription() {
        return Game.i.localeManager.i18n.get("item_description_RESOURCE");
    }

    @Override // com.prineside.tdi2.Item
    public RarityType getRarity() {
        switch (this.resourceType) {
            case SCALAR:
                return RarityType.COMMON;
            case VECTOR:
                return RarityType.RARE;
            case MATRIX:
                return RarityType.VERY_RARE;
            case TENSOR:
                return RarityType.EPIC;
            case INFIAR:
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
    public boolean sameAs(Item item) {
        return super.sameAs(item) && ((ResourceItem) item).resourceType == this.resourceType;
    }

    @Override // com.prineside.tdi2.Item
    public Drawable getIconDrawable() {
        return Item.D.F_RESOURCE.f2262b[this.resourceType.ordinal()];
    }

    @Override // com.prineside.tdi2.Item
    public Actor generateIcon(float f, boolean z) {
        if (z) {
            Group group = new Group();
            group.setTransform(false);
            group.setSize(f, f);
            Image image = new Image(Game.i.assetManager.getDrawable(Resource.TEXTURE_REGION_NAMES[this.resourceType.ordinal()]));
            image.setSize(f, f);
            image.setColor(0.0f, 0.0f, 0.0f, 0.28f);
            image.setPosition(a(f), -a(f));
            group.addActor(image);
            Image image2 = new Image(Game.i.assetManager.getDrawable(Resource.TEXTURE_REGION_NAMES[this.resourceType.ordinal()]));
            image2.setSize(f, f);
            image2.setColor(Game.i.resourceManager.getColor(this.resourceType));
            group.addActor(image2);
            return group;
        }
        Image image3 = new Image(Game.i.assetManager.getDrawable(Resource.TEXTURE_REGION_NAMES[this.resourceType.ordinal()]));
        image3.setSize(f, f);
        image3.setColor(Game.i.resourceManager.getColor(this.resourceType));
        return image3;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/ResourceItem$ResourceItemFactory.class */
    public static class ResourceItemFactory implements Item.Factory<ResourceItem> {

        /* renamed from: a, reason: collision with root package name */
        private final ResourceItem[] f2261a = new ResourceItem[ResourceType.values.length];

        /* renamed from: b, reason: collision with root package name */
        private Drawable[] f2262b = new Drawable[BlueprintType.values.length];

        public ResourceItemFactory() {
            for (ResourceType resourceType : ResourceType.values) {
                this.f2261a[resourceType.ordinal()] = new ResourceItem(resourceType, (byte) 0);
            }
        }

        @Override // com.prineside.tdi2.Item.Factory
        public void setup() {
            if (Game.i.assetManager != null) {
                for (ResourceType resourceType : ResourceType.values) {
                    this.f2262b[resourceType.ordinal()] = Game.i.assetManager.getDrawable(Resource.TEXTURE_REGION_NAMES[resourceType.ordinal()]).tint(Game.i.resourceManager.getColor(resourceType));
                }
            }
        }

        public ResourceItem create(ResourceType resourceType) {
            return this.f2261a[resourceType.ordinal()];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public ResourceItem fromJson(JsonValue jsonValue) {
            return create(ResourceType.valueOf(jsonValue.getString("resourceType")));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public ResourceItem createDefault() {
            return create(ResourceType.values[0]);
        }
    }
}
