package com.prineside.tdi2.items;

import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.enums.ItemCategoryType;
import com.prineside.tdi2.enums.ItemDataType;
import com.prineside.tdi2.enums.ItemSubcategoryType;
import com.prineside.tdi2.enums.ItemType;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.enums.TrophyType;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.SelectBox;
import com.prineside.tdi2.scene2d.utils.ChangeListener;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.ui.shared.ItemCreationOverlay;
import com.prineside.tdi2.utils.REGS;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/items/TrophyItem.class */
public class TrophyItem extends Item {
    public final TrophyType trophyType;

    /* synthetic */ TrophyItem(TrophyType trophyType, byte b2) {
        this(trophyType);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/TrophyItem$Serializer.class */
    public static class Serializer extends SingletonSerializer<TrophyItem> {
        @Override // com.prineside.tdi2.serializers.SingletonSerializer, com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public /* bridge */ /* synthetic */ Object read2(Kryo kryo, Input input, Class cls) {
            return read2(kryo, input, (Class<? extends TrophyItem>) cls);
        }

        @Override // com.prineside.tdi2.serializers.SingletonSerializer, com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, TrophyItem trophyItem) {
            kryo.writeObject(output, trophyItem.trophyType);
        }

        @Override // com.prineside.tdi2.serializers.SingletonSerializer, com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public TrophyItem read2(Kryo kryo, Input input, Class<? extends TrophyItem> cls) {
            return Item.D.F_TROPHY.create((TrophyType) kryo.readObject(input, TrophyType.class));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public TrophyItem read() {
            throw new IllegalStateException("Do not use");
        }
    }

    private TrophyItem(TrophyType trophyType) {
        if (trophyType == null) {
            throw new IllegalArgumentException("TrophyType is null");
        }
        this.trophyType = trophyType;
    }

    @Override // com.prineside.tdi2.Item
    public Item from(Item item) {
        return Item.D.F_TROPHY.create(((TrophyItem) item).trophyType);
    }

    @Override // com.prineside.tdi2.Item
    public IntArray getData() {
        IntArray data = super.getData();
        data.add(ItemDataType.TYPE.ordinal(), this.trophyType.ordinal());
        return data;
    }

    @Override // com.prineside.tdi2.Item
    public Item cpy() {
        return Item.D.F_TROPHY.create(this.trophyType);
    }

    @Override // com.prineside.tdi2.Item
    public ItemType getType() {
        return ItemType.TROPHY;
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
        return Game.i.localeManager.i18n.get("item_title_TROPHY");
    }

    @Override // com.prineside.tdi2.Item
    public CharSequence getDescription() {
        return Game.i.trophyManager.getConfig(this.trophyType).getTitle();
    }

    @Override // com.prineside.tdi2.Item
    public RarityType getRarity() {
        return RarityType.EPIC;
    }

    @Override // com.prineside.tdi2.Item
    public String getAnalyticName() {
        return "trophy";
    }

    @Override // com.prineside.tdi2.Item
    public boolean isCountable() {
        return false;
    }

    @Override // com.prineside.tdi2.Item
    public boolean sameAs(Item item) {
        return super.sameAs(item) && ((TrophyItem) item).trophyType == this.trophyType;
    }

    @Override // com.prineside.tdi2.Item
    public void toJson(Json json) {
        super.toJson(json);
        json.writeValue("trophyType", this.trophyType);
    }

    @Override // com.prineside.tdi2.Item
    public Actor generateIcon(float f, boolean z) {
        if (Game.i.trophyManager.getConfig(this.trophyType).isReceived()) {
            Image image = new Image(Game.i.trophyManager.getConfig(this.trophyType).getIconTexture());
            image.setSize(f, f);
            return image;
        }
        Group group = new Group();
        group.setTransform(false);
        group.setSize(f, f);
        Image image2 = new Image(Game.i.assetManager.getDrawable("icon-question"));
        image2.setSize(f, f);
        group.addActor(image2);
        return group;
    }

    @Override // com.prineside.tdi2.Item
    public void fillItemCreationForm(final ItemCreationOverlay itemCreationOverlay) {
        itemCreationOverlay.label("Trophy type");
        final SelectBox selectBox = new SelectBox(itemCreationOverlay.selectBoxStyle);
        selectBox.setItems(TrophyType.values);
        selectBox.setSelected(this.trophyType);
        selectBox.addListener(new ChangeListener(this) { // from class: com.prineside.tdi2.items.TrophyItem.1
            @Override // com.prineside.tdi2.scene2d.utils.ChangeListener
            public void changed(ChangeListener.ChangeEvent changeEvent, Actor actor) {
                itemCreationOverlay.currentItem = Item.D.F_TROPHY.create((TrophyType) selectBox.getSelected());
                itemCreationOverlay.updateForm();
            }
        });
        itemCreationOverlay.selectBox(selectBox);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/TrophyItem$TrophyItemFactory.class */
    public static class TrophyItemFactory implements Item.Factory<TrophyItem> {

        /* renamed from: a, reason: collision with root package name */
        private final TrophyItem[] f2269a = new TrophyItem[TrophyType.values.length];

        public TrophyItemFactory() {
            for (TrophyType trophyType : TrophyType.values) {
                this.f2269a[trophyType.ordinal()] = new TrophyItem(trophyType, (byte) 0);
            }
        }

        @Override // com.prineside.tdi2.Item.Factory
        public void setup() {
        }

        public TrophyItem create(TrophyType trophyType) {
            return this.f2269a[trophyType.ordinal()];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public TrophyItem fromJson(JsonValue jsonValue) {
            return create(TrophyType.valueOf(jsonValue.getString("trophyType")));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public TrophyItem createDefault() {
            return create(TrophyType.values[0]);
        }
    }
}
