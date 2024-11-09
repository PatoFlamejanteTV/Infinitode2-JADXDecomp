package com.prineside.tdi2;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.enums.ItemSortingType;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.REGS;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import java.util.Comparator;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/ItemStack.class */
public class ItemStack implements KryoSerializable {
    public static final int MAX_COUNT = 999999999;
    public static final byte FLAG_COVERED = 1;
    public static final byte FLAG_DOUBLED = 2;
    public static final byte FLAG_DAILY_LOOT = 4;
    public static final byte FLAG_RANDOM_ENCOUNTER = 8;

    /* renamed from: a, reason: collision with root package name */
    private Item f1731a;

    /* renamed from: b, reason: collision with root package name */
    private int f1732b;
    public byte flags;
    public static final Comparator<ItemStack> SORT_COMPARATOR_KIND = (itemStack, itemStack2) -> {
        return Integer.compare(itemStack.getItem().getSortingScore(ItemSortingType.KIND), itemStack2.getItem().getSortingScore(ItemSortingType.KIND));
    };
    public static final Comparator<ItemStack> SORT_COMPARATOR_RARITY_ASC = (itemStack, itemStack2) -> {
        return Integer.compare(itemStack.getItem().getSortingScore(ItemSortingType.RARITY), itemStack2.getItem().getSortingScore(ItemSortingType.RARITY));
    };
    public static final Comparator<ItemStack> SORT_COMPARATOR_RARITY_DESC = (itemStack, itemStack2) -> {
        return Integer.compare(itemStack2.getItem().getSortingScore(ItemSortingType.RARITY), itemStack.getItem().getSortingScore(ItemSortingType.RARITY));
    };

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        kryo.writeClassAndObject(output, this.f1731a);
        output.writeVarInt(this.f1732b, true);
        output.writeByte(this.flags);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        this.f1731a = (Item) kryo.readClassAndObject(input);
        this.f1732b = input.readVarInt(true);
        this.flags = input.readByte();
    }

    private ItemStack() {
        this.f1732b = 1;
    }

    public ItemStack(ItemStack itemStack) {
        this(itemStack.f1731a, itemStack.f1732b);
        this.flags = itemStack.flags;
    }

    public ItemStack(Item item, int i) {
        this.f1732b = 1;
        if (item == null) {
            throw new IllegalArgumentException("Item is null");
        }
        if (i <= 0) {
            throw new IllegalArgumentException("Count is < 1 (" + i + ")");
        }
        this.f1731a = item;
        this.f1732b = i;
    }

    public byte getFlags() {
        return this.flags;
    }

    public boolean isCovered() {
        return (this.flags & 1) != 0;
    }

    public void setCovered(boolean z) {
        if (z) {
            this.flags = (byte) (this.flags | 1);
        } else {
            this.flags = (byte) (this.flags & (-2));
        }
    }

    public boolean isDoubled() {
        return (this.flags & 2) != 0;
    }

    public void markDoubled(boolean z) {
        if (z) {
            this.flags = (byte) (this.flags | 2);
        } else {
            this.flags = (byte) (this.flags & (-3));
        }
    }

    public boolean isFromDailyLoot() {
        return (this.flags & 4) != 0;
    }

    public void markFromDailyLoot(boolean z) {
        if (z) {
            this.flags = (byte) (this.flags | 4);
        } else {
            this.flags = (byte) (this.flags & (-5));
        }
    }

    public boolean isFromRandomEncounter() {
        return (this.flags & 8) != 0;
    }

    public void markFromRandomEncounter(boolean z) {
        if (z) {
            this.flags = (byte) (this.flags | 8);
        } else {
            this.flags = (byte) (this.flags & (-9));
        }
    }

    public int getCount() {
        return this.f1732b;
    }

    public void setCount(int i) {
        this.f1732b = MathUtils.clamp(i, 0, MAX_COUNT);
    }

    public void add(int i) {
        this.f1732b = PMath.addWithoutOverflow(this.f1732b, i);
    }

    public void setItem(Item item) {
        this.f1731a = item;
    }

    public ItemStack setItemAndCount(Item item, int i) {
        this.f1731a = item;
        setCount(i);
        return this;
    }

    public Item getItem() {
        return this.f1731a;
    }

    public ItemStack cpy() {
        return new ItemStack(this);
    }

    public void toJson(Json json) {
        json.writeObjectStart(FlexmarkHtmlConverter.I_NODE);
        this.f1731a.toJson(json);
        json.writeObjectEnd();
        if (this.f1732b > 1) {
            json.writeValue("c", Integer.valueOf(this.f1732b));
        }
    }

    public String toString() {
        return getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + " (" + this.f1731a.toString() + " x" + this.f1732b + ")";
    }

    @Null
    public static ItemStack fromJsonOrNull(@Null JsonValue jsonValue) {
        if (jsonValue == null) {
            return null;
        }
        return fromJson(jsonValue);
    }

    public static ItemStack fromJson(JsonValue jsonValue) {
        Preconditions.checkNotNull(jsonValue, "jsonValue can not be null");
        try {
            JsonValue jsonValue2 = jsonValue.get(FlexmarkHtmlConverter.I_NODE);
            JsonValue jsonValue3 = jsonValue2;
            if (jsonValue2 == null) {
                jsonValue3 = jsonValue.get("item");
            }
            Item fromJson = Item.fromJson(jsonValue3);
            int i = jsonValue.getInt("c", -1);
            int i2 = i;
            if (i == -1) {
                i2 = jsonValue.getInt("count", -1);
            }
            if (i2 == -1) {
                i2 = 1;
            }
            return new ItemStack(fromJson, i2);
        } catch (Exception e) {
            throw new RuntimeException("Unable to create ItemStack from json: " + jsonValue.toString(), e);
        }
    }
}
