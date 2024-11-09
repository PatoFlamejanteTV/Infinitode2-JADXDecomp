package com.prineside.tdi2.items;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.enums.ItemCategoryType;
import com.prineside.tdi2.enums.ItemSubcategoryType;
import com.prineside.tdi2.enums.ItemType;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.ui.shared.ItemCreationOverlay;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/items/DoubleGainShardItem.class */
public class DoubleGainShardItem extends Item implements Item.UsableItem {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2235a = TLog.forClass(DoubleGainShardItem.class);
    public static final int DEFAULT_DURATION = 1209600;
    public int duration;

    /* synthetic */ DoubleGainShardItem(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Item, com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        output.writeVarInt(this.duration, true);
    }

    @Override // com.prineside.tdi2.Item, com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        this.duration = input.readVarInt(true);
    }

    private DoubleGainShardItem() {
        this.duration = DEFAULT_DURATION;
    }

    @Override // com.prineside.tdi2.Item
    public String getAnalyticName() {
        return "double_gain_shard";
    }

    @Override // com.prineside.tdi2.Item
    public Item cpy() {
        DoubleGainShardItem create = Item.D.F_DOUBLE_GAIN_SHARD.create();
        create.duration = this.duration;
        return create;
    }

    @Override // com.prineside.tdi2.Item
    public Item from(Item item) {
        this.duration = ((DoubleGainShardItem) item).duration;
        return this;
    }

    @Override // com.prineside.tdi2.Item
    public boolean sameAs(Item item) {
        if (!super.sameAs(item)) {
            return false;
        }
        f2235a.i(((DoubleGainShardItem) item).duration + SequenceUtils.SPACE + this.duration, new Object[0]);
        return ((DoubleGainShardItem) item).duration == this.duration;
    }

    @Override // com.prineside.tdi2.Item
    public boolean canBeSold() {
        return Game.i.progressManager.hasPermanentDoubleGain();
    }

    public static int getAcceleratorsForDuration(int i) {
        int round = (int) StrictMath.round((i / 1209600.0d) * 200.0d);
        int i2 = round;
        if (round <= 0) {
            i2 = 1;
        }
        return i2;
    }

    @Override // com.prineside.tdi2.Item
    public void addSellItems(Array<ItemStack> array) {
        array.add(new ItemStack(Item.D.ACCELERATOR, getAcceleratorsForDuration(this.duration)));
    }

    @Override // com.prineside.tdi2.Item
    public ItemType getType() {
        return ItemType.DOUBLE_GAIN_SHARD;
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
        return Game.i.localeManager.i18n.get("item_title_DOUBLE_GAIN_SHARD");
    }

    @Override // com.prineside.tdi2.Item
    public CharSequence getDescription() {
        return Game.i.localeManager.i18n.get("item_description_DOUBLE_GAIN_SHARD") + " (" + StringFormatter.timePassed(this.duration, false, true) + ")";
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
        if (z) {
            Group group = new Group();
            group.setTransform(false);
            group.setSize(f, f);
            Image image = new Image(Game.i.assetManager.getDrawable("double-gain-shard"));
            image.setSize(f, f);
            image.setPosition(a(f), -a(f));
            image.setColor(0.0f, 0.0f, 0.0f, 0.28f);
            group.addActor(image);
            Image image2 = new Image(Game.i.assetManager.getDrawable("double-gain-shard"));
            image2.setSize(f, f);
            group.addActor(image2);
            return group;
        }
        Image image3 = new Image(Game.i.assetManager.getDrawable("double-gain-shard"));
        image3.setSize(f, f);
        return image3;
    }

    @Override // com.prineside.tdi2.Item
    public void fillItemCreationForm(ItemCreationOverlay itemCreationOverlay) {
        itemCreationOverlay.label("Duration (s)");
        itemCreationOverlay.textField(PMath.toString(this.duration), str -> {
            try {
                ((DoubleGainShardItem) itemCreationOverlay.currentItem).duration = Integer.valueOf(str).intValue();
                itemCreationOverlay.updateItemIcon();
            } catch (Exception unused) {
                f2235a.e("fillItemCreationForm - bad value: " + str, new Object[0]);
            }
        });
    }

    @Override // com.prineside.tdi2.Item.UsableItem
    public boolean autoUseWhenAdded() {
        return false;
    }

    @Override // com.prineside.tdi2.Item.UsableItem
    public boolean useItem() {
        if (!Game.i.progressManager.hasPermanentDoubleGain() && Game.i.progressManager.removeItems(this, 1)) {
            Game.i.analyticsManager.logCurrencySpent("used", getAnalyticName(), 1);
            return Game.i.progressManager.enableDoubleGainTemporary(this.duration);
        }
        return false;
    }

    @Override // com.prineside.tdi2.Item
    public void toJson(Json json) {
        super.toJson(json);
        json.writeValue("duration", Integer.valueOf(this.duration));
    }

    @Override // com.prineside.tdi2.Item.UsableItem
    public boolean useItemNeedsConfirmation() {
        return false;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/items/DoubleGainShardItem$DoubleGainShardItemFactory.class */
    public static class DoubleGainShardItemFactory implements Item.Factory<DoubleGainShardItem> {
        @Override // com.prineside.tdi2.Item.Factory
        public void setup() {
        }

        public DoubleGainShardItem create() {
            return new DoubleGainShardItem((byte) 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public DoubleGainShardItem fromJson(JsonValue jsonValue) {
            DoubleGainShardItem create = create();
            create.duration = jsonValue.getInt("duration", DoubleGainShardItem.DEFAULT_DURATION);
            return create;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Item.Factory
        public DoubleGainShardItem createDefault() {
            return create();
        }
    }
}
