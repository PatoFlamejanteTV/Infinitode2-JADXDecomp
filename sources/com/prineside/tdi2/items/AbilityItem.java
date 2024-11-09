package com.prineside.tdi2.items;

import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Ability;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.enums.ItemCategoryType;
import com.prineside.tdi2.enums.ItemDataType;
import com.prineside.tdi2.enums.ItemSubcategoryType;
import com.prineside.tdi2.enums.ItemType;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.utils.REGS;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/items/AbilityItem.class */
public class AbilityItem extends Item {
    public AbilityType abilityType;

    /* synthetic */ AbilityItem(AbilityType abilityType, byte b2) {
        this(abilityType);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/AbilityItem$Serializer.class */
    public static class Serializer extends SingletonSerializer<AbilityItem> {
        @Override // com.prineside.tdi2.serializers.SingletonSerializer, com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public /* bridge */ /* synthetic */ Object read2(Kryo kryo, Input input, Class cls) {
            return read2(kryo, input, (Class<? extends AbilityItem>) cls);
        }

        @Override // com.prineside.tdi2.serializers.SingletonSerializer, com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, AbilityItem abilityItem) {
            kryo.writeObject(output, abilityItem.abilityType);
        }

        @Override // com.prineside.tdi2.serializers.SingletonSerializer, com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public AbilityItem read2(Kryo kryo, Input input, Class<? extends AbilityItem> cls) {
            return Item.D.F_ABILITY.create((AbilityType) kryo.readObject(input, AbilityType.class));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public AbilityItem read() {
            throw new IllegalStateException("Do not use");
        }
    }

    private AbilityItem(AbilityType abilityType) {
        if (abilityType == null) {
            throw new IllegalArgumentException("AbilityType is null");
        }
        this.abilityType = abilityType;
    }

    @Override // com.prineside.tdi2.Item
    public Item from(Item item) {
        return Item.D.F_ABILITY.create(((AbilityItem) item).abilityType);
    }

    @Override // com.prineside.tdi2.Item
    public IntArray getData() {
        IntArray data = super.getData();
        data.add(ItemDataType.TYPE.ordinal(), this.abilityType.ordinal());
        return data;
    }

    @Override // com.prineside.tdi2.Item
    public void toJson(Json json) {
        super.toJson(json);
        json.writeValue("abilityType", this.abilityType.name());
    }

    @Override // com.prineside.tdi2.Item
    public ItemType getType() {
        return ItemType.ABILITY;
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
        return Game.i.abilityManager.getFactory(this.abilityType).getTitle();
    }

    @Override // com.prineside.tdi2.Item
    public CharSequence getDescription() {
        return Game.i.abilityManager.getFactory(this.abilityType).getDescription(Game.i.gameValueManager.getSnapshot());
    }

    @Override // com.prineside.tdi2.Item
    public RarityType getRarity() {
        return RarityType.COMMON;
    }

    @Override // com.prineside.tdi2.Item
    public String getAnalyticName() {
        return "ability_" + this.abilityType;
    }

    @Override // com.prineside.tdi2.Item
    public boolean isCountable() {
        return true;
    }

    @Override // com.prineside.tdi2.Item
    public boolean sameAs(Item item) {
        return super.sameAs(item) && ((AbilityItem) item).abilityType == this.abilityType;
    }

    @Override // com.prineside.tdi2.Item
    public Actor generateIcon(float f, boolean z) {
        Ability.Factory<? extends Ability> factory = Game.i.abilityManager.getFactory(this.abilityType);
        if (z) {
            Group group = new Group();
            group.setTransform(false);
            group.setSize(f, f);
            Image image = new Image(factory.getIconDrawable());
            image.setSize(f, f);
            image.setColor(0.0f, 0.0f, 0.0f, 0.28f);
            image.setPosition(a(f), -a(f));
            group.addActor(image);
            Image image2 = new Image(factory.getIconDrawable());
            image2.setSize(f, f);
            image2.setColor(factory.getColor());
            group.addActor(image2);
            return group;
        }
        Image image3 = new Image(factory.getIconDrawable());
        image3.setSize(f, f);
        image3.setColor(factory.getColor());
        return image3;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/AbilityItem$AbilityItemFactory.class */
    public static class AbilityItemFactory implements Item.Factory<AbilityItem> {

        /* renamed from: a, reason: collision with root package name */
        private final AbilityItem[] f2214a = new AbilityItem[AbilityType.values.length];

        public AbilityItemFactory() {
            for (AbilityType abilityType : AbilityType.values) {
                this.f2214a[abilityType.ordinal()] = new AbilityItem(abilityType, (byte) 0);
            }
        }

        @Override // com.prineside.tdi2.Item.Factory
        public void setup() {
        }

        public AbilityItem create(AbilityType abilityType) {
            return this.f2214a[abilityType.ordinal()];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public AbilityItem fromJson(JsonValue jsonValue) {
            return create(AbilityType.valueOf(jsonValue.getString("abilityType")));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public AbilityItem createDefault() {
            return create(AbilityType.values[0]);
        }
    }
}
