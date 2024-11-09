package com.prineside.tdi2;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.enums.BlueprintType;
import com.prineside.tdi2.enums.CaseType;
import com.prineside.tdi2.enums.ItemCategoryType;
import com.prineside.tdi2.enums.ItemDataType;
import com.prineside.tdi2.enums.ItemSortingType;
import com.prineside.tdi2.enums.ItemSubcategoryType;
import com.prineside.tdi2.enums.ItemType;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.items.AbilityItem;
import com.prineside.tdi2.items.AbilityTokenItem;
import com.prineside.tdi2.items.AcceleratorItem;
import com.prineside.tdi2.items.BitDustItem;
import com.prineside.tdi2.items.BlueprintItem;
import com.prineside.tdi2.items.CaseItem;
import com.prineside.tdi2.items.CaseKeyItem;
import com.prineside.tdi2.items.DatPaperItem;
import com.prineside.tdi2.items.DoubleGainShardItem;
import com.prineside.tdi2.items.GameValueGlobalItem;
import com.prineside.tdi2.items.GameValueLevelItem;
import com.prineside.tdi2.items.GateItem;
import com.prineside.tdi2.items.GreenPaperItem;
import com.prineside.tdi2.items.LootBoostItem;
import com.prineside.tdi2.items.LuckyShotTokenItem;
import com.prineside.tdi2.items.OpenedResearchItem;
import com.prineside.tdi2.items.PrestigeDustItem;
import com.prineside.tdi2.items.PrestigeTokenItem;
import com.prineside.tdi2.items.RandomBarrierItem;
import com.prineside.tdi2.items.RandomTeleportItem;
import com.prineside.tdi2.items.RandomTileItem;
import com.prineside.tdi2.items.RarityBoostItem;
import com.prineside.tdi2.items.ResearchTokenItem;
import com.prineside.tdi2.items.ResearchTokenUsedItem;
import com.prineside.tdi2.items.ResourceItem;
import com.prineside.tdi2.items.SkillPointItem;
import com.prineside.tdi2.items.StarItem;
import com.prineside.tdi2.items.TileItem;
import com.prineside.tdi2.items.TrophyItem;
import com.prineside.tdi2.managers.ItemManager;
import com.prineside.tdi2.managers.ProgressManager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.ui.shared.ItemCreationOverlay;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.PMath;
import java.io.StringWriter;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/Item.class */
public abstract class Item implements KryoSerializable {

    /* renamed from: a, reason: collision with root package name */
    private static final ThreadLocal<Array<ItemStack>> f1729a = new ThreadLocal<Array<ItemStack>>() { // from class: com.prineside.tdi2.Item.1
        @Override // java.lang.ThreadLocal
        protected /* synthetic */ Array<ItemStack> initialValue() {
            return a();
        }

        private static Array<ItemStack> a() {
            return new Array<>(false, 1, ItemStack.class);
        }
    };

    /* renamed from: b, reason: collision with root package name */
    @NAGS
    private IntArray f1730b = new IntArray();

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/Item$UsableItem.class */
    public interface UsableItem {
        boolean autoUseWhenAdded();

        boolean useItem();

        boolean useItemNeedsConfirmation();
    }

    public abstract ItemType getType();

    public abstract ItemCategoryType getCategory();

    public abstract ItemSubcategoryType getSubcategory();

    public abstract CharSequence getTitle();

    public abstract CharSequence getDescription();

    public abstract RarityType getRarity();

    public abstract Actor generateIcon(float f, boolean z);

    public abstract boolean isCountable();

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/Item$D.class */
    public static class D {
        public static GreenPaperItem GREEN_PAPER;
        public static ResourceItem RESOURCE_SCALAR;
        public static ResourceItem RESOURCE_VECTOR;
        public static ResourceItem RESOURCE_MATRIX;
        public static ResourceItem RESOURCE_TENSOR;
        public static ResourceItem RESOURCE_INFIAR;
        public static BitDustItem BIT_DUST;
        public static PrestigeDustItem PRESTIGE_DUST;
        public static DatPaperItem DAT_PAPER;
        public static BlueprintItem BLUEPRINT_AGILITY;
        public static BlueprintItem BLUEPRINT_EXPERIENCE;
        public static BlueprintItem BLUEPRINT_POWER;
        public static BlueprintItem BLUEPRINT_SPECIAL_I;
        public static BlueprintItem BLUEPRINT_SPECIAL_II;
        public static BlueprintItem BLUEPRINT_SPECIAL_III;
        public static BlueprintItem BLUEPRINT_SPECIAL_IV;
        public static CaseKeyItem CASE_KEY_BLUE;
        public static CaseKeyItem CASE_KEY_PURPLE;
        public static CaseKeyItem CASE_KEY_ORANGE;
        public static CaseKeyItem CASE_KEY_CYAN;
        public static RarityBoostItem RARITY_BOOST;
        public static AbilityTokenItem ABILITY_TOKEN;
        public static AcceleratorItem ACCELERATOR;
        public static LootBoostItem LOOT_BOOST;
        public static PrestigeTokenItem PRESTIGE_TOKEN;
        public static ResearchTokenItem RESEARCH_TOKEN;
        public static ResearchTokenUsedItem RESEARCH_TOKEN_USED;
        public static LuckyShotTokenItem LUCKY_SHOT_TOKEN;
        public static SkillPointItem SKILL_POINT;
        public static RandomTeleportItem RANDOM_TELEPORT;
        public static StarItem STAR;
        public static BlueprintItem.BlueprintItemFactory F_BLUEPRINT;
        public static AcceleratorItem.AcceleratorItemFactory F_ACCELERATOR;
        public static AbilityTokenItem.AbilityTokenItemFactory F_ABILITY_TOKEN;
        public static DoubleGainShardItem.DoubleGainShardItemFactory F_DOUBLE_GAIN_SHARD;
        public static CaseItem.CaseItemFactory F_CASE;
        public static GateItem.GateItemFactory F_GATE;
        public static TileItem.TileItemFactory F_TILE;
        public static ResourceItem.ResourceItemFactory F_RESOURCE;
        public static RandomTileItem.RandomTileItemFactory F_RANDOM_TILE;
        public static CaseKeyItem.CaseKeyItemFactory F_CASE_KEY;
        public static AbilityItem.AbilityItemFactory F_ABILITY;
        public static TrophyItem.TrophyItemFactory F_TROPHY;
        public static GameValueGlobalItem.GameValueGlobalItemFactory F_GAME_VALUE_GLOBAL;
        public static GameValueLevelItem.GameValueLevelItemFactory F_GAME_VALUE_LEVEL;
        public static RandomBarrierItem.RandomBarrierItemFactory F_RANDOM_BARRIER;
        public static PrestigeTokenItem.PrestigeTokenItemFactory F_PRESTIGE_TOKEN;
        public static ResearchTokenItem.ResearchTokenItemFactory F_RESEARCH_TOKEN;
        public static ResearchTokenUsedItem.ResearchTokenUsedItemFactory F_RESEARCH_TOKEN_USED;
        public static LuckyShotTokenItem.LuckyShotTokenItemFactory F_LUCKY_SHOT_TOKEN;
        public static OpenedResearchItem.OpenedResearchItemFactory F_OPENED_RESEARCH;
        public static BitDustItem.BitDustItemFactory F_BIT_DUST;
        public static GreenPaperItem.GreenPaperItemFactory F_GREEN_PAPER;

        public static void setup() {
            ItemManager itemManager = Game.i.itemManager;
            F_ABILITY_TOKEN = (AbilityTokenItem.AbilityTokenItemFactory) itemManager.getFactory(ItemType.ABILITY_TOKEN);
            F_ACCELERATOR = (AcceleratorItem.AcceleratorItemFactory) itemManager.getFactory(ItemType.ACCELERATOR);
            F_BLUEPRINT = (BlueprintItem.BlueprintItemFactory) itemManager.getFactory(ItemType.BLUEPRINT);
            F_CASE = (CaseItem.CaseItemFactory) itemManager.getFactory(ItemType.CASE);
            F_GATE = (GateItem.GateItemFactory) itemManager.getFactory(ItemType.GATE);
            F_TILE = (TileItem.TileItemFactory) itemManager.getFactory(ItemType.TILE);
            F_RESOURCE = (ResourceItem.ResourceItemFactory) itemManager.getFactory(ItemType.RESOURCE);
            F_RANDOM_TILE = (RandomTileItem.RandomTileItemFactory) itemManager.getFactory(ItemType.RANDOM_TILE);
            F_CASE_KEY = (CaseKeyItem.CaseKeyItemFactory) itemManager.getFactory(ItemType.CASE_KEY);
            F_ABILITY = (AbilityItem.AbilityItemFactory) itemManager.getFactory(ItemType.ABILITY);
            F_TROPHY = (TrophyItem.TrophyItemFactory) itemManager.getFactory(ItemType.TROPHY);
            F_RANDOM_BARRIER = (RandomBarrierItem.RandomBarrierItemFactory) itemManager.getFactory(ItemType.RANDOM_BARRIER);
            F_DOUBLE_GAIN_SHARD = (DoubleGainShardItem.DoubleGainShardItemFactory) itemManager.getFactory(ItemType.DOUBLE_GAIN_SHARD);
            F_GAME_VALUE_GLOBAL = (GameValueGlobalItem.GameValueGlobalItemFactory) itemManager.getFactory(ItemType.GAME_VALUE_GLOBAL);
            F_GAME_VALUE_LEVEL = (GameValueLevelItem.GameValueLevelItemFactory) itemManager.getFactory(ItemType.GAME_VALUE_LEVEL);
            F_PRESTIGE_TOKEN = (PrestigeTokenItem.PrestigeTokenItemFactory) itemManager.getFactory(ItemType.PRESTIGE_TOKEN);
            F_RESEARCH_TOKEN = (ResearchTokenItem.ResearchTokenItemFactory) itemManager.getFactory(ItemType.RESEARCH_TOKEN);
            F_RESEARCH_TOKEN_USED = (ResearchTokenUsedItem.ResearchTokenUsedItemFactory) itemManager.getFactory(ItemType.RESEARCH_TOKEN_USED);
            F_LUCKY_SHOT_TOKEN = (LuckyShotTokenItem.LuckyShotTokenItemFactory) itemManager.getFactory(ItemType.LUCKY_SHOT_TOKEN);
            F_OPENED_RESEARCH = (OpenedResearchItem.OpenedResearchItemFactory) itemManager.getFactory(ItemType.OPENED_RESEARCH);
            F_BIT_DUST = (BitDustItem.BitDustItemFactory) itemManager.getFactory(ItemType.BIT_DUST);
            F_GREEN_PAPER = (GreenPaperItem.GreenPaperItemFactory) itemManager.getFactory(ItemType.GREEN_PAPER);
            GREEN_PAPER = (GreenPaperItem) itemManager.getFactory(ItemType.GREEN_PAPER).createDefault();
            RESOURCE_SCALAR = F_RESOURCE.create(ResourceType.SCALAR);
            RESOURCE_VECTOR = F_RESOURCE.create(ResourceType.VECTOR);
            RESOURCE_MATRIX = F_RESOURCE.create(ResourceType.MATRIX);
            RESOURCE_TENSOR = F_RESOURCE.create(ResourceType.TENSOR);
            RESOURCE_INFIAR = F_RESOURCE.create(ResourceType.INFIAR);
            BIT_DUST = (BitDustItem) itemManager.getFactory(ItemType.BIT_DUST).createDefault();
            PRESTIGE_DUST = (PrestigeDustItem) itemManager.getFactory(ItemType.PRESTIGE_DUST).createDefault();
            DAT_PAPER = (DatPaperItem) itemManager.getFactory(ItemType.DAT_PAPER).createDefault();
            BLUEPRINT_AGILITY = F_BLUEPRINT.create(BlueprintType.AGILITY);
            BLUEPRINT_EXPERIENCE = F_BLUEPRINT.create(BlueprintType.EXPERIENCE);
            BLUEPRINT_POWER = F_BLUEPRINT.create(BlueprintType.POWER);
            BLUEPRINT_SPECIAL_I = F_BLUEPRINT.create(BlueprintType.SPECIAL_I);
            BLUEPRINT_SPECIAL_II = F_BLUEPRINT.create(BlueprintType.SPECIAL_II);
            BLUEPRINT_SPECIAL_III = F_BLUEPRINT.create(BlueprintType.SPECIAL_III);
            BLUEPRINT_SPECIAL_IV = F_BLUEPRINT.create(BlueprintType.SPECIAL_IV);
            CASE_KEY_BLUE = F_CASE_KEY.create(CaseType.BLUE);
            CASE_KEY_PURPLE = F_CASE_KEY.create(CaseType.PURPLE);
            CASE_KEY_ORANGE = F_CASE_KEY.create(CaseType.ORANGE);
            CASE_KEY_CYAN = F_CASE_KEY.create(CaseType.CYAN);
            RARITY_BOOST = (RarityBoostItem) itemManager.getFactory(ItemType.RARITY_BOOST).createDefault();
            ABILITY_TOKEN = (AbilityTokenItem) itemManager.getFactory(ItemType.ABILITY_TOKEN).createDefault();
            ACCELERATOR = (AcceleratorItem) itemManager.getFactory(ItemType.ACCELERATOR).createDefault();
            LOOT_BOOST = (LootBoostItem) itemManager.getFactory(ItemType.LOOT_BOOST).createDefault();
            PRESTIGE_TOKEN = (PrestigeTokenItem) itemManager.getFactory(ItemType.PRESTIGE_TOKEN).createDefault();
            RESEARCH_TOKEN = (ResearchTokenItem) itemManager.getFactory(ItemType.RESEARCH_TOKEN).createDefault();
            RESEARCH_TOKEN_USED = (ResearchTokenUsedItem) itemManager.getFactory(ItemType.RESEARCH_TOKEN_USED).createDefault();
            LUCKY_SHOT_TOKEN = (LuckyShotTokenItem) itemManager.getFactory(ItemType.LUCKY_SHOT_TOKEN).createDefault();
            SKILL_POINT = (SkillPointItem) itemManager.getFactory(ItemType.SKILL_POINT).createDefault();
            RANDOM_TELEPORT = (RandomTeleportItem) itemManager.getFactory(ItemType.RANDOM_TELEPORT).createDefault();
            STAR = (StarItem) itemManager.getFactory(ItemType.STAR).createDefault();
        }
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
    }

    public int getSortingScore(ItemSortingType itemSortingType) {
        if (itemSortingType == ItemSortingType.RARITY) {
            return getRarity().ordinal() * 1000;
        }
        return getType().ordinal() * 1000;
    }

    public IntArray getData() {
        this.f1730b.clear();
        return this.f1730b;
    }

    public String getAnalyticName() {
        return "unknown";
    }

    public double getPriceInAcceleratorsForResearchReset(int i) {
        return 0.0d;
    }

    public final int getDataOfType(ItemDataType itemDataType) {
        int ordinal = itemDataType.ordinal();
        IntArray data = getData();
        for (int i = 0; i < data.size; i += 2) {
            if (data.items[i] == ordinal) {
                return data.items[i + 1];
            }
        }
        return Integer.MIN_VALUE;
    }

    public Item from(Item item) {
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static float a(float f) {
        float f2 = f * 0.05f;
        float f3 = f2;
        if (f2 < 2.0f) {
            f3 = 2.0f;
        }
        return f3;
    }

    @Null
    public Drawable getIconDrawable() {
        return null;
    }

    public boolean canBeSold() {
        return false;
    }

    public void addSellItems(Array<ItemStack> array) {
    }

    public void addSellItemsMultiplied(Array<ItemStack> array, int i) {
        Array<ItemStack> array2 = f1729a.get();
        array2.clear();
        addSellItems(array2);
        for (int i2 = 0; i2 < array2.size; i2++) {
            array2.items[i2].setCount(PMath.multiplyWithoutOverflow(array2.items[i2].getCount(), i));
        }
        array.addAll(array2);
        array2.clear();
    }

    public void fillItemCreationForm(ItemCreationOverlay itemCreationOverlay) {
    }

    public void toJson(Json json) {
        json.writeValue("type", getType().name());
    }

    public String toJsonString() {
        Json json = new Json(JsonWriter.OutputType.json);
        StringWriter stringWriter = new StringWriter();
        json.setWriter(stringWriter);
        json.writeObjectStart();
        toJson(json);
        json.writeObjectEnd();
        return stringWriter.toString();
    }

    public boolean affectedByLuckyWheelMultiplier() {
        return true;
    }

    public Item cpy() {
        return this;
    }

    public boolean canBeUnpacked() {
        return false;
    }

    public Array<ItemStack> openPack(ProgressManager.InventoryStatistics inventoryStatistics) {
        if (canBeUnpacked()) {
            throw new RuntimeException("Not implemented");
        }
        throw new RuntimeException("Is not pack");
    }

    public boolean isAffectedByDoubleGain() {
        return false;
    }

    public boolean sameAs(Item item) {
        return this == item || getType() == item.getType();
    }

    public static Item fromJson(JsonValue jsonValue) {
        return Game.i.itemManager.getFactory(ItemType.valueOf(jsonValue.getString("type"))).fromJson(jsonValue);
    }

    public void fillWithInfo(Table table, float f) {
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/Item$Factory.class */
    public interface Factory<T extends Item> extends EntityFactory {
        void setup();

        T fromJson(JsonValue jsonValue);

        T createDefault();

        /* loaded from: infinitode-2.jar:com/prineside/tdi2/Item$Factory$AbstractFactory.class */
        public static abstract class AbstractFactory<T extends Item> implements Factory<T> {
            @Override // com.prineside.tdi2.Item.Factory
            public void setup() {
            }
        }
    }
}
