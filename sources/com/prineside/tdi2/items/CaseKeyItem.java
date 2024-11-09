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
import com.prineside.tdi2.Screen;
import com.prineside.tdi2.enums.CaseType;
import com.prineside.tdi2.enums.ItemCategoryType;
import com.prineside.tdi2.enums.ItemDataType;
import com.prineside.tdi2.enums.ItemSubcategoryType;
import com.prineside.tdi2.enums.ItemType;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.screens.MoneyScreen;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/items/CaseKeyItem.class */
public class CaseKeyItem extends Item implements Item.UsableItem {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2231a = TLog.forClass(CaseKeyItem.class);
    public CaseType caseType;

    /* synthetic */ CaseKeyItem(CaseType caseType, byte b2) {
        this(caseType);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/CaseKeyItem$Serializer.class */
    public static class Serializer extends SingletonSerializer<CaseKeyItem> {
        @Override // com.prineside.tdi2.serializers.SingletonSerializer, com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public /* bridge */ /* synthetic */ Object read2(Kryo kryo, Input input, Class cls) {
            return read2(kryo, input, (Class<? extends CaseKeyItem>) cls);
        }

        @Override // com.prineside.tdi2.serializers.SingletonSerializer, com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, CaseKeyItem caseKeyItem) {
            kryo.writeObject(output, caseKeyItem.caseType);
        }

        @Override // com.prineside.tdi2.serializers.SingletonSerializer, com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public CaseKeyItem read2(Kryo kryo, Input input, Class<? extends CaseKeyItem> cls) {
            return Item.D.F_CASE_KEY.create((CaseType) kryo.readObject(input, CaseType.class));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public CaseKeyItem read() {
            throw new IllegalStateException("Do not use");
        }
    }

    private CaseKeyItem(CaseType caseType) {
        if (caseType == null) {
            throw new IllegalArgumentException("caseType is null");
        }
        this.caseType = caseType;
    }

    @Override // com.prineside.tdi2.Item
    public boolean isAffectedByDoubleGain() {
        return true;
    }

    @Override // com.prineside.tdi2.Item
    public Item from(Item item) {
        return Item.D.F_CASE_KEY.create(((CaseKeyItem) item).caseType);
    }

    @Override // com.prineside.tdi2.Item
    public boolean canBeSold() {
        return true;
    }

    @Override // com.prineside.tdi2.Item
    public String getAnalyticName() {
        return "case_key_" + this.caseType;
    }

    @Override // com.prineside.tdi2.Item
    public void addSellItems(Array<ItemStack> array) {
        int i = 1;
        switch (this.caseType) {
            case GREEN:
                i = 300;
                break;
            case BLUE:
                i = 500;
                break;
            case PURPLE:
                i = 800;
                break;
            case ORANGE:
                i = 1200;
                break;
            case CYAN:
                i = 2000;
                break;
            case BLUEPRINT:
                i = 300;
                break;
        }
        array.add(new ItemStack(Item.D.GREEN_PAPER, i));
    }

    @Override // com.prineside.tdi2.Item
    public IntArray getData() {
        IntArray data = super.getData();
        data.add(ItemDataType.TYPE.ordinal(), this.caseType.ordinal());
        return data;
    }

    @Override // com.prineside.tdi2.Item
    public ItemType getType() {
        return ItemType.CASE_KEY;
    }

    @Override // com.prineside.tdi2.Item
    public ItemCategoryType getCategory() {
        return ItemCategoryType.PACKS;
    }

    @Override // com.prineside.tdi2.Item
    public ItemSubcategoryType getSubcategory() {
        return ItemSubcategoryType.P_DECRYPTED;
    }

    @Override // com.prineside.tdi2.Item
    public CharSequence getTitle() {
        return Game.i.localeManager.i18n.get("case_key_title_" + this.caseType.name());
    }

    @Override // com.prineside.tdi2.Item
    public CharSequence getDescription() {
        return Game.i.localeManager.i18n.get("item_description_CASE_KEY");
    }

    @Override // com.prineside.tdi2.Item
    public boolean sameAs(Item item) {
        return super.sameAs(item) && this.caseType == ((CaseKeyItem) item).caseType;
    }

    @Override // com.prineside.tdi2.Item
    public RarityType getRarity() {
        return Item.D.F_CASE.create(this.caseType, true).getRarity();
    }

    @Override // com.prineside.tdi2.Item
    public void toJson(Json json) {
        super.toJson(json);
        json.writeValue("caseType", this.caseType.name());
    }

    @Override // com.prineside.tdi2.Item
    public Actor generateIcon(float f, boolean z) {
        Image image = new Image(Game.i.assetManager.getDrawable("icon-key"));
        switch (this.caseType) {
            case GREEN:
                image.setColor(MaterialColor.GREEN.P300);
                break;
            case BLUE:
                image.setColor(MaterialColor.INDIGO.P300);
                break;
            case PURPLE:
                image.setColor(MaterialColor.PURPLE.P300);
                break;
            case ORANGE:
                image.setColor(MaterialColor.ORANGE.P300);
                break;
            case CYAN:
                image.setColor(MaterialColor.CYAN.P300);
                break;
        }
        image.setSize(f, f);
        if (z) {
            Group group = new Group();
            group.setTransform(false);
            group.setSize(f, f);
            Image image2 = new Image(Game.i.assetManager.getDrawable("icon-key"));
            image2.setColor(0.0f, 0.0f, 0.0f, 0.28f);
            image2.setSize(f, f);
            image2.setPosition(a(f), -a(f));
            group.addActor(image2);
            group.addActor(image);
            return group;
        }
        return image;
    }

    @Override // com.prineside.tdi2.Item
    public boolean isCountable() {
        return true;
    }

    @Override // com.prineside.tdi2.Item.UsableItem
    public boolean autoUseWhenAdded() {
        return false;
    }

    @Override // com.prineside.tdi2.Item.UsableItem
    public boolean useItem() {
        Game.i.screenManager.goToMoneyScreen();
        String str = "spend_keys_" + this.caseType.name();
        Game.i.uiManager.runOnStageActOnce(() -> {
            Screen currentScreen = Game.i.screenManager.getCurrentScreen();
            if (currentScreen instanceof MoneyScreen) {
                ((MoneyScreen) currentScreen).scrollToActor(str);
            } else {
                f2231a.i("warning: current screen is not a shop", new Object[0]);
            }
        });
        return true;
    }

    @Override // com.prineside.tdi2.Item.UsableItem
    public boolean useItemNeedsConfirmation() {
        return false;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/CaseKeyItem$CaseKeyItemFactory.class */
    public static class CaseKeyItemFactory implements Item.Factory<CaseKeyItem> {

        /* renamed from: a, reason: collision with root package name */
        private final CaseKeyItem[] f2233a = new CaseKeyItem[CaseType.values.length];

        public CaseKeyItemFactory() {
            for (CaseType caseType : CaseType.values) {
                this.f2233a[caseType.ordinal()] = new CaseKeyItem(caseType, (byte) 0);
            }
        }

        @Override // com.prineside.tdi2.Item.Factory
        public void setup() {
        }

        public CaseKeyItem create(CaseType caseType) {
            return this.f2233a[caseType.ordinal()];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public CaseKeyItem fromJson(JsonValue jsonValue) {
            return create(CaseType.valueOf(jsonValue.getString("caseType")));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public CaseKeyItem createDefault() {
            return create(CaseType.BLUE);
        }
    }
}
