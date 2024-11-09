package com.prineside.tdi2.managers;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.IntIntMap;
import com.badlogic.gdx.utils.IntSet;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectFloatMap;
import com.badlogic.gdx.utils.ObjectIntMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectSet;
import com.badlogic.gdx.utils.Timer;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.base.Preconditions;
import com.prineside.kryo.FixedInput;
import com.prineside.kryo.FixedOutput;
import com.prineside.tdi2.BasicLevel;
import com.prineside.tdi2.BasicLevelQuestConfig;
import com.prineside.tdi2.BasicLevelStage;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.CraftRecipe;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.GameValueProvider;
import com.prineside.tdi2.Gate;
import com.prineside.tdi2.IssuedItems;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.Manager;
import com.prineside.tdi2.Research;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.configs.ShopOfferValues;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.enums.CaseType;
import com.prineside.tdi2.enums.DifficultyMode;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ItemCategoryType;
import com.prineside.tdi2.enums.ItemSubcategoryType;
import com.prineside.tdi2.enums.ItemType;
import com.prineside.tdi2.enums.ModifierType;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.enums.ResearchType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.enums.TrophyType;
import com.prineside.tdi2.events.global.GameLoad;
import com.prineside.tdi2.items.AcceleratorItem;
import com.prineside.tdi2.items.BlueprintItem;
import com.prineside.tdi2.items.CaseItem;
import com.prineside.tdi2.items.RandomBarrierItem;
import com.prineside.tdi2.items.RandomTileItem;
import com.prineside.tdi2.items.ResourceItem;
import com.prineside.tdi2.items.TileItem;
import com.prineside.tdi2.items.TrophyItem;
import com.prineside.tdi2.managers.AuthManager;
import com.prineside.tdi2.managers.GameValueManager;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.managers.preferences.categories.ProgressPrefs;
import com.prineside.tdi2.managers.preferences.categories.progress.PP_Inventory;
import com.prineside.tdi2.managers.preferences.categories.progress.PP_Progress;
import com.prineside.tdi2.managers.preferences.categories.progress.PP_Research;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.ui.shared.IssuedPrizesOverlay;
import com.prineside.tdi2.ui.shared.LuckyWheelOverlay;
import com.prineside.tdi2.ui.shared.Notifications;
import com.prineside.tdi2.ui.shared.OpenedPackOverlay;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import org.lwjgl.opengl.CGL;
import org.lwjgl.system.windows.User32;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/ProgressManager.class */
public class ProgressManager extends Manager.ManagerAdapter {
    public static final int VIDEO_WATCHES_FOR_DOUBLE_GAIN = 500;
    public static final int VIDEO_WATCHES_FOR_LUCKY_SHOT = 20;
    public static final int DIFFICULTY_MULTIPLIER_EASY = 80;
    public static final int DIFFICULTY_MULTIPLIER_NORMAL = 100;

    /* renamed from: b, reason: collision with root package name */
    private Array<IssuedItems> f2404b;
    private boolean c;
    public VideoAdViewBonus[] videoAdViewBonuses;
    private final DelayedRemovalArray<ProgressManagerListener> j = new DelayedRemovalArray<>(false, 1);
    private final _GameValueManagerListener k = new _GameValueManagerListener(this, 0);
    private ItemStack[] l;
    public static final int VIDEO_AD_BONUSES_CYCLE_LENGTH = 300;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2403a = TLog.forClass(ProgressManager.class);
    public static final ConditionalCompensation[] CONDITIONAL_COMPENSATIONS = {new ConditionalCompensation(10177) { // from class: com.prineside.tdi2.managers.ProgressManager.1
        {
            super(10177);
        }

        @Override // com.prineside.tdi2.managers.ProgressManager.ConditionalCompensation
        public boolean handle() {
            if (Game.i.statisticsManager.getAllTime(StatisticsType.PQR) >= 3.0d) {
                IssuedItems issuedItems = new IssuedItems(IssuedItems.IssueReason.FAILURE_COMPENSATION, Game.getTimestampSeconds());
                issuedItems.failureCompensationDescription = "For 3+ Prestige quest resets before update 1.8.5";
                issuedItems.items.add(new ItemStack(Item.D.PRESTIGE_TOKEN, 2450));
                issuedItems.items.add(new ItemStack(Item.D.BIT_DUST, 500));
                issuedItems.items.add(new ItemStack(Item.D.RESEARCH_TOKEN, 3));
                Game.i.progressManager.addItemArray(issuedItems.items, "compensation");
                Game.i.progressManager.addIssuedPrizes(issuedItems, true);
                Game.i.progressManager.showNewlyIssuedPrizesPopup();
                return true;
            }
            return false;
        }
    }, new ConditionalCompensation(10202) { // from class: com.prineside.tdi2.managers.ProgressManager.2
        {
            super(10202);
        }

        @Override // com.prineside.tdi2.managers.ProgressManager.ConditionalCompensation
        public boolean handle() {
            int dailyLootDaysInRow = ProgressPrefs.i().dailyQuest.getDailyLootDaysInRow();
            ProgressManager.f2403a.i("compensating " + dailyLootDaysInRow + " DQ days in row", new Object[0]);
            if (dailyLootDaysInRow >= 3) {
                IssuedItems issuedItems = new IssuedItems(IssuedItems.IssueReason.FAILURE_COMPENSATION, Game.getTimestampSeconds());
                issuedItems.failureCompensationDescription = "For " + dailyLootDaysInRow + " days streak of daily quests (this feature has been removed in 1.9.0)";
                issuedItems.items.add(new ItemStack(Item.D.GREEN_PAPER, dailyLootDaysInRow * 1000));
                issuedItems.items.add(new ItemStack(Item.D.ACCELERATOR, dailyLootDaysInRow << 1));
                if (dailyLootDaysInRow > 365) {
                    issuedItems.items.add(new ItemStack(Item.D.RESEARCH_TOKEN, 4));
                    issuedItems.items.add(new ItemStack(Item.D.BIT_DUST, 500));
                    issuedItems.items.add(new ItemStack(Item.D.PRESTIGE_TOKEN, 500));
                }
                if (dailyLootDaysInRow > 180) {
                    issuedItems.items.add(new ItemStack(Item.D.RESEARCH_TOKEN, 3));
                    issuedItems.items.add(new ItemStack(Item.D.BIT_DUST, 300));
                    issuedItems.items.add(new ItemStack(Item.D.PRESTIGE_TOKEN, 300));
                }
                if (dailyLootDaysInRow > 90) {
                    issuedItems.items.add(new ItemStack(Item.D.RESEARCH_TOKEN, 2));
                    issuedItems.items.add(new ItemStack(Item.D.BIT_DUST, 200));
                    issuedItems.items.add(new ItemStack(Item.D.PRESTIGE_TOKEN, 200));
                }
                if (dailyLootDaysInRow > 45) {
                    issuedItems.items.add(new ItemStack(Item.D.RESEARCH_TOKEN, 1));
                    issuedItems.items.add(new ItemStack(Item.D.BIT_DUST, 100));
                    issuedItems.items.add(new ItemStack(Item.D.PRESTIGE_TOKEN, 100));
                }
                if (dailyLootDaysInRow > 21) {
                    issuedItems.items.add(new ItemStack(Item.D.BIT_DUST, 50));
                    issuedItems.items.add(new ItemStack(Item.D.PRESTIGE_TOKEN, 50));
                }
                issuedItems.items.add(new ItemStack(Item.D.F_RANDOM_TILE.create(1.0f), dailyLootDaysInRow / 2));
                int i2 = dailyLootDaysInRow;
                while (i2 > 0) {
                    if (i2 >= 16) {
                        issuedItems.items.add(new ItemStack(Item.D.F_CASE.create(CaseType.CYAN, false), 1));
                        i2 -= 16;
                    } else if (i2 >= 8) {
                        issuedItems.items.add(new ItemStack(Item.D.F_CASE.create(CaseType.ORANGE, false), 1));
                        i2 -= 8;
                    } else if (i2 >= 4) {
                        issuedItems.items.add(new ItemStack(Item.D.F_CASE.create(CaseType.PURPLE, false), 1));
                        i2 -= 4;
                    } else if (i2 >= 2) {
                        issuedItems.items.add(new ItemStack(Item.D.F_CASE.create(CaseType.BLUE, false), 1));
                        i2 -= 2;
                    } else {
                        issuedItems.items.add(new ItemStack(Item.D.F_CASE.create(CaseType.GREEN, false), 1));
                        i2--;
                    }
                }
                ProgressManager.compressStacksArray(issuedItems.items);
                Game.i.progressManager.addItemArray(issuedItems.items, "compensation");
                Game.i.progressManager.addIssuedPrizes(issuedItems, true);
                Game.i.progressManager.showNewlyIssuedPrizesPopup();
                return true;
            }
            return false;
        }
    }};
    public static final Comparator<? super ItemStack> ITEM_RARITY_COMPARATOR = (itemStack, itemStack2) -> {
        return Integer.compare(itemStack2.getItem().getRarity().ordinal(), itemStack.getItem().getRarity().ordinal());
    };
    private static final Color[] d = {new Color(1013530111), new Color(1247523071), new Color(-1874878721), new Color(-814213633), new Color(413710591)};
    private static final Color[] e = {MaterialColor.GREEN.P400, MaterialColor.INDIGO.P300, MaterialColor.PURPLE.P300, MaterialColor.ORANGE.P500, MaterialColor.CYAN.P500};
    private static final Color[] f = {new Color(779956991), new Color(1247523071), new Color(-1038590977).lerp(0.2f, 0.3f, 0.3f, 1.0f, 0.25f), new Color(-814213633), new Color(413710591)};
    private static final Color[] g = {MaterialColor.GREEN.P400, MaterialColor.INDIGO.P300, MaterialColor.PINK.P400.cpy().lerp(0.2f, 0.3f, 0.3f, 1.0f, 0.25f), MaterialColor.ORANGE.P500, MaterialColor.CYAN.P500};
    private static final String[] h = {"rarity_COMMON", "rarity_RARE", "rarity_VERY_RARE", "rarity_EPIC", "rarity_LEGENDARY"};
    private static final String[] i = {"icon-star", "icon-two-stars", "icon-three-stars", "icon-four-stars", "icon-five-stars"};
    private static final Array<ItemStack> m = new Array<>(ItemStack.class);

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/ProgressManager$Serializer.class */
    public static class Serializer extends SingletonSerializer<ProgressManager> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public ProgressManager read() {
            return Game.i.progressManager;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/ProgressManager$ConditionalCompensation.class */
    public static abstract class ConditionalCompensation {
        public int id;

        public abstract boolean handle();

        public ConditionalCompensation(int i) {
            this.id = i;
        }
    }

    public ProgressManager() {
        if (!Config.isHeadless()) {
            Game.EVENTS.getListeners(GameLoad.class).add(gameLoad -> {
                Timer.schedule(new Timer.Task() { // from class: com.prineside.tdi2.managers.ProgressManager.3
                    @Override // com.badlogic.gdx.utils.Timer.Task, java.lang.Runnable
                    public void run() {
                        ProgressManager progressManager = ProgressManager.this;
                        ProgressManager.b();
                    }
                }, 2.0f);
            });
        }
    }

    public void removeIssuedItemsLog() {
        this.f2404b.clear();
        this.c = false;
        Gdx.files.local(PreferencesManager.getIssuedItemsFilePath()).delete();
    }

    public Array<IssuedItems> getIssuedItems() {
        if (this.f2404b == null) {
            this.f2404b = new Array<>(true, 1, IssuedItems.class);
            try {
                FileHandle local = Gdx.files.local(PreferencesManager.getIssuedItemsFilePath());
                if (local.exists()) {
                    Iterator<JsonValue> iterator2 = new JsonReader().parse(local.readString("UTF-8")).iterator2();
                    while (iterator2.hasNext()) {
                        try {
                            this.f2404b.add(IssuedItems.fromJson(iterator2.next()));
                        } catch (Exception e2) {
                            f2403a.i("failed to load issued prizes log", e2);
                            this.c = true;
                        }
                    }
                }
            } catch (Exception e3) {
                f2403a.i("failed to load issued prizes log", e3);
                this.c = true;
            }
        }
        return this.f2404b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b() {
        PP_Progress pP_Progress = ProgressPrefs.i().progress;
        for (ConditionalCompensation conditionalCompensation : CONDITIONAL_COMPENSATIONS) {
            if (!pP_Progress.isConditionalCompensationHandled(conditionalCompensation.id)) {
                f2403a.i("handling conditional compensation #" + conditionalCompensation.id, new Object[0]);
                f2403a.i("  #" + conditionalCompensation.id + " - " + (conditionalCompensation.handle() ? "compensation given" : "conditions not met"), new Object[0]);
                pP_Progress.addHandledConditionalCompensation(conditionalCompensation.id);
                ProgressPrefs.i().requireSave();
            }
        }
    }

    private static int a(int i2) {
        int i3;
        if (i2 < 100) {
            i3 = i2 - (i2 % 10);
        } else if (i2 < 500) {
            i3 = i2 - (i2 % 50);
        } else if (i2 < 1000) {
            i3 = i2 - (i2 % 100);
        } else if (i2 < 5000) {
            i3 = i2 - (i2 % 500);
        } else if (i2 < 10000) {
            i3 = i2 - (i2 % 1000);
        } else {
            i3 = i2 - (i2 % 5000);
        }
        return i3;
    }

    public void handleGameOverShopOffersRotation(GameSystemProvider gameSystemProvider) {
        PP_Progress pP_Progress = ProgressPrefs.i().progress;
        pP_Progress.setPlayTimeUntilShopOffersUpdate(pP_Progress.getPlayTimeUntilShopOffersUpdate() - ((int) gameSystemProvider.statistics.getStatistic(StatisticsType.PT)));
        ProgressPrefs.i().requireSave();
    }

    public Array<ShopOffer> getShopOffers() {
        PP_Progress pP_Progress = ProgressPrefs.i().progress;
        if (pP_Progress.getShopOffers() == null || pP_Progress.getShopOffers().size == 0 || pP_Progress.getPlayTimeUntilShopOffersUpdate() <= 0) {
            generateNewShopOffers();
        }
        return pP_Progress.getShopOffers();
    }

    public int getSecondsPlayedCorrectedForShop() {
        int i2;
        int allTime = (int) Game.i.statisticsManager.getAllTime(StatisticsType.PRT);
        int allTime2 = (int) (Game.i.statisticsManager.getAllTime(StatisticsType.PT) / 3.0999999046325684d);
        if (allTime < allTime2 / 2 || Game.i.statisticsManager.getAllTime(StatisticsType.PRT) > 5.4E7d || Game.i.statisticsManager.getAllTime(StatisticsType.PRT) <= 0.0d) {
            f2403a.i("=========== Broken PRT or no progress, falling back to PT (PRT: " + allTime + " / PT: " + allTime2 + ")", new Object[0]);
            allTime = allTime2;
        }
        f2403a.i("=========== secondsPlayed: " + allTime + ", according to in-game time: " + ((int) (Game.i.statisticsManager.getAllTime(StatisticsType.PT) / 3.0999999046325684d)) + ", diff: " + (allTime / ((float) (Game.i.statisticsManager.getAllTime(StatisticsType.PT) / 3.0999999046325684d))), new Object[0]);
        int i3 = 0;
        Array<Research> instances = Game.i.researchManager.getInstances();
        for (int i4 = 0; i4 < instances.size; i4++) {
            Research research = instances.get(i4);
            if (research.priceInStars == 0 && research.type != ResearchType.DEVELOPER_MODE) {
                i3 += research.getInstalledLevel();
            }
        }
        int i5 = (int) (5597.885740693629d + ((-1399506.3291685237d) / (((allTime / 60.0d) / 60.0d) + 252.32019149803534d)));
        if (i3 > 5500) {
            i2 = (allTime / 60) / 60;
        } else {
            int round = MathUtils.round((float) ((((-1399506.32d) + ((-252.32d) * i3)) + 1412457.08d) / (i3 - 5597.88d)));
            i2 = round;
            if (round < 0) {
                i2 = 0;
            }
        }
        f2403a.i("=========== installed research: " + i3 + ", expected compared to other players: " + i5, new Object[0]);
        f2403a.i("=========== playtime: " + ((allTime / 60.0f) / 60.0f) + ", expected compared to other players: " + i2, new Object[0]);
        if (allTime <= 0) {
            allTime = 1;
        }
        if (i3 < 4800) {
            float f2 = (float) ((((-146468.59d) + ((-29.76d) * i3)) + 145673.11d) / (i3 - 4894.93d));
            f2403a.i("=========== dev playtime according to research: " + ((Object) StringFormatter.compactNumberWithPrecision(f2, 1)) + "h, raw multiplier: " + ((Object) StringFormatter.compactNumberWithPrecision(((f2 * 60.0f) * 60.0f) / allTime, 2)), new Object[0]);
            if (f2 >= 1.0f) {
                float f3 = ((f2 * 60.0f) * 60.0f) / allTime;
                if (f3 < 1.0f) {
                    float f4 = (f3 + 0.2f) / 1.2f;
                    int i6 = (int) (allTime * f4);
                    allTime = i6;
                    if (i6 <= 0) {
                        allTime = 1;
                    }
                    f2403a.i("=========== adjusting playtime: " + f4 + ", raw difference: " + f3 + ", final: " + ((allTime / 60.0f) / 60.0f), new Object[0]);
                }
            }
        }
        return allTime;
    }

    public void generateNewShopOffers() {
        ShopOffer a2;
        ShopOffer a3;
        ShopOffer a4;
        ShopOffer a5;
        float f2;
        int i2;
        float f3;
        float f4;
        float f5;
        Array<ItemStack> price;
        PP_Progress pP_Progress = ProgressPrefs.i().progress;
        Array<ShopOffer> array = new Array<>(true, 1, ShopOffer.class);
        pP_Progress.setPlayTimeUntilShopOffersUpdate(3600);
        pP_Progress.setCurrentShopOffersAreAfterSkip(false);
        RandomXS128 otherItemsRandom = pP_Progress.getOtherItemsRandom();
        int secondsPlayedCorrectedForShop = getSecondsPlayedCorrectedForShop();
        ObjectIntMap objectIntMap = new ObjectIntMap();
        ObjectMap objectMap = new ObjectMap();
        Array<Research> instances = Game.i.researchManager.getInstances();
        int itemsCount = getItemsCount(Item.D.BIT_DUST);
        for (int i3 = 0; i3 < instances.size; i3++) {
            Research research = instances.get(i3);
            if (!Game.i.researchManager.canStartResearching(research, false) && !research.isMaxEndlessLevel()) {
                boolean z = true;
                if (research.priceInStars > 0) {
                    boolean z2 = false;
                    int i4 = 0;
                    while (true) {
                        if (i4 >= research.linksToParents.size) {
                            break;
                        }
                        if (research.linksToParents.get(i4).parent.getInstalledLevel() <= 0) {
                            i4++;
                        } else {
                            z2 = true;
                            break;
                        }
                    }
                    int i5 = 0;
                    while (true) {
                        if (i5 >= research.linksToChildren.size) {
                            break;
                        }
                        if (research.linksToChildren.get(i5).child.getInstalledLevel() <= 0) {
                            i5++;
                        } else {
                            z2 = true;
                            break;
                        }
                    }
                    if (!z2) {
                        z = false;
                    }
                    if (Game.i.researchManager.getUnusedStarsCount() < research.priceInStars) {
                        z = false;
                    }
                } else {
                    int i6 = 0;
                    while (true) {
                        if (i6 >= research.linksToParents.size) {
                            break;
                        }
                        Research.ResearchLink researchLink = research.linksToParents.get(i6);
                        if (researchLink.requiredLevels <= researchLink.parent.getInstalledLevel()) {
                            i6++;
                        } else {
                            z = false;
                            break;
                        }
                    }
                }
                if (z) {
                    if (research.levels.length > research.getInstalledLevel()) {
                        price = research.levels[research.getInstalledLevel()].price;
                    } else {
                        price = research.endlessLevel.getPrice(research.getInstalledLevel() + 1);
                    }
                    for (int i7 = 0; i7 < price.size; i7++) {
                        ItemStack itemStack = price.items[i7];
                        if (itemStack.getItem().getType() != ItemType.RESOURCE) {
                            if (itemStack.getItem().getType() != ItemType.BIT_DUST) {
                                if (itemStack.getCount() > Game.i.progressManager.getItemsCount(itemStack.getItem())) {
                                    objectIntMap.getAndIncrement(itemStack.getItem(), 0, itemStack.getCount());
                                }
                            } else if (itemStack.getCount() <= itemsCount) {
                                itemsCount -= itemStack.getCount();
                            } else {
                                objectIntMap.getAndIncrement(itemStack.getItem(), 0, itemStack.getCount());
                            }
                        } else {
                            ResourceItem resourceItem = (ResourceItem) itemStack.getItem();
                            if (itemStack.getCount() > Game.i.progressManager.getResources(resourceItem.resourceType)) {
                                objectIntMap.getAndIncrement(resourceItem, 0, itemStack.getCount());
                            }
                        }
                    }
                }
            }
            if (research.priceInStars == 0) {
                Array<ItemStack> cumulativePrice = research.getCumulativePrice(research.getInstalledLevel(), research.maxEndlessLevel, false);
                for (int i8 = 0; i8 < cumulativePrice.size; i8++) {
                    ItemStack itemStack2 = cumulativePrice.get(i8);
                    objectMap.put(itemStack2.getItem(), Long.valueOf(((Long) objectMap.get(itemStack2.getItem(), 0L)).longValue() + itemStack2.getCount()));
                }
            }
        }
        ObjectFloatMap<Item> objectFloatMap = new ObjectFloatMap<>();
        ObjectFloatMap<Item> objectFloatMap2 = new ObjectFloatMap<>();
        Item[] itemArr = {Item.D.BLUEPRINT_AGILITY, Item.D.BLUEPRINT_EXPERIENCE, Item.D.BLUEPRINT_POWER};
        for (int i9 = 0; i9 < 3; i9++) {
            Item item = itemArr[i9];
            float calculate = ShopOfferValues.REGULAR_BLUEPRINT_VALUE.calculate(secondsPlayedCorrectedForShop);
            float calculate2 = ShopOfferValues.REGULAR_BLUEPRINT_QUANTITY.calculate(secondsPlayedCorrectedForShop);
            objectFloatMap.put(item, calculate);
            objectFloatMap2.put(item, calculate2);
        }
        BlueprintItem[] blueprintItemArr = {Item.D.BLUEPRINT_SPECIAL_I, Item.D.BLUEPRINT_SPECIAL_II, Item.D.BLUEPRINT_SPECIAL_III, Item.D.BLUEPRINT_SPECIAL_IV};
        for (int i10 = 0; i10 < 4; i10++) {
            BlueprintItem blueprintItem = blueprintItemArr[i10];
            switch (blueprintItem.getBlueprintType()) {
                case SPECIAL_I:
                    f4 = 1.0f;
                    f5 = 7.0f;
                    break;
                case SPECIAL_II:
                    f4 = 3.6f;
                    f5 = 4.0f;
                    break;
                case SPECIAL_III:
                    f4 = 12.24f;
                    f5 = 2.0f;
                    break;
                case SPECIAL_IV:
                    f4 = 36.72f;
                    f5 = 1.0f;
                    break;
                default:
                    f4 = 1.0f;
                    f5 = 0.5f;
                    break;
            }
            objectFloatMap.put(blueprintItem, ShopOfferValues.SPECIAL_BLUEPRINT_VALUE.calculate(secondsPlayedCorrectedForShop) * f4 * 0.8f);
            objectFloatMap2.put(blueprintItem, ShopOfferValues.SPECIAL_BLUEPRINT_QUANTITY.calculate(secondsPlayedCorrectedForShop) * f5);
        }
        objectFloatMap.put(Item.D.ACCELERATOR, ShopOfferValues.ACCELERATOR_VALUE.calculate(secondsPlayedCorrectedForShop) * 0.90000004f);
        objectFloatMap.put(Item.D.GREEN_PAPER, ShopOfferValues.GREEN_PAPERS_VALUE.calculate(secondsPlayedCorrectedForShop));
        objectFloatMap2.put(Item.D.GREEN_PAPER, ShopOfferValues.GREEN_PAPERS_QUANTITY.calculate(secondsPlayedCorrectedForShop));
        ResourceItem[] resourceItemArr = {Item.D.RESOURCE_SCALAR, Item.D.RESOURCE_VECTOR, Item.D.RESOURCE_MATRIX, Item.D.RESOURCE_TENSOR, Item.D.RESOURCE_INFIAR};
        for (int i11 = 0; i11 < 5; i11++) {
            ResourceItem resourceItem2 = resourceItemArr[i11];
            switch (resourceItem2.resourceType) {
                case SCALAR:
                    f3 = 1.0f;
                    break;
                case VECTOR:
                    f3 = 1.3f;
                    break;
                case MATRIX:
                    f3 = 1.6f;
                    break;
                case TENSOR:
                    f3 = 1.9f;
                    break;
                case INFIAR:
                    f3 = 2.2f;
                    break;
                default:
                    f3 = 1.0f;
                    break;
            }
            objectFloatMap.put(resourceItem2, ShopOfferValues.RESOURCE_VALUE.calculate(secondsPlayedCorrectedForShop) * f3 * 0.2f);
        }
        float percentValueAsMultiplier = ((((float) Game.i.gameValueManager.getSnapshot().getPercentValueAsMultiplier(GameValueType.BIT_DUST_DROP_RATE)) - 1.0f) * 0.65f) + 1.0f;
        f2403a.i("bdRate " + percentValueAsMultiplier, new Object[0]);
        objectFloatMap.put(Item.D.BIT_DUST, (ShopOfferValues.BIT_DUST_VALUE.calculate(secondsPlayedCorrectedForShop) / percentValueAsMultiplier) * 0.8f);
        objectFloatMap2.put(Item.D.BIT_DUST, ShopOfferValues.BIT_DUST_QUANTITY.calculate(secondsPlayedCorrectedForShop) * percentValueAsMultiplier * 0.8f);
        CaseItem[] caseItemArr = {Item.D.F_CASE.create(CaseType.GREEN, false), Item.D.F_CASE.create(CaseType.BLUE, false), Item.D.F_CASE.create(CaseType.PURPLE, false), Item.D.F_CASE.create(CaseType.ORANGE, false), Item.D.F_CASE.create(CaseType.CYAN, false)};
        for (int i12 = 0; i12 < 5; i12++) {
            CaseItem caseItem = caseItemArr[i12];
            switch (caseItem.caseType) {
                case GREEN:
                    f2 = 3.0f;
                    i2 = 5;
                    break;
                case BLUE:
                    f2 = 8.0f;
                    i2 = 4;
                    break;
                case PURPLE:
                    f2 = 21.0f;
                    i2 = 3;
                    break;
                case ORANGE:
                    f2 = 55.0f;
                    i2 = 2;
                    break;
                case CYAN:
                    f2 = 150.0f;
                    i2 = 1;
                    break;
                default:
                    f2 = 1.0f;
                    i2 = 1;
                    break;
            }
            objectFloatMap.put(caseItem, ShopOfferValues.CASE_VALUE.calculate(secondsPlayedCorrectedForShop) * 0.0033333334f * f2);
            objectFloatMap2.put(caseItem, ShopOfferValues.CASE_QUANTITY.calculate(secondsPlayedCorrectedForShop) * i2);
        }
        Array array2 = new Array(ShopOffer.class);
        Array array3 = new Array(ShopOffer.class);
        for (int i13 = 0; i13 < 3; i13++) {
            Item[] itemArr2 = {Item.D.BLUEPRINT_AGILITY, Item.D.BLUEPRINT_EXPERIENCE, Item.D.BLUEPRINT_POWER, Item.D.BLUEPRINT_SPECIAL_I, Item.D.BLUEPRINT_SPECIAL_II, Item.D.BLUEPRINT_SPECIAL_III, Item.D.BLUEPRINT_SPECIAL_IV};
            for (int i14 = 0; i14 < 7; i14++) {
                Item item2 = itemArr2[i14];
                int i15 = objectIntMap.get(item2, 0);
                long longValue = ((Long) objectMap.get(item2, 0L)).longValue();
                if (i15 != 0) {
                    ShopOffer a6 = a(item2, otherItemsRandom, objectFloatMap, objectFloatMap2, true);
                    if (a6 != null) {
                        array3.add(a6);
                    }
                    if (i15 > 40 && (a5 = a(item2, otherItemsRandom, objectFloatMap, objectFloatMap2, true)) != null) {
                        array3.add(a5);
                    }
                }
                if (longValue != 0 && (a4 = a(item2, otherItemsRandom, objectFloatMap, objectFloatMap2, false)) != null) {
                    array2.add(a4);
                }
            }
            for (int i16 = 0; i16 < 2; i16++) {
                Item[] itemArr3 = {Item.D.F_CASE.create(CaseType.GREEN, false), Item.D.F_CASE.create(CaseType.BLUE, false), Item.D.F_CASE.create(CaseType.PURPLE, false), Item.D.F_CASE.create(CaseType.ORANGE, false), Item.D.F_CASE.create(CaseType.CYAN, false)};
                for (int i17 = 0; i17 < 5; i17++) {
                    ShopOffer a7 = a(itemArr3[i17], otherItemsRandom, objectFloatMap, objectFloatMap2, false);
                    if (a7 != null) {
                        array2.add(a7);
                    }
                }
            }
            Item[] itemArr4 = {Item.D.F_CASE.create(CaseType.ORANGE, false), Item.D.F_CASE.create(CaseType.CYAN, false)};
            for (int i18 = 0; i18 < 2; i18++) {
                ShopOffer a8 = a(itemArr4[i18], otherItemsRandom, objectFloatMap, objectFloatMap2, false);
                if (a8 != null) {
                    array3.add(a8);
                }
            }
            if (Game.i.progressManager.getItemsCount(Item.D.GREEN_PAPER) < 950000000) {
                for (int i19 = 0; i19 < 2; i19++) {
                    ShopOffer a9 = a(Item.D.GREEN_PAPER, otherItemsRandom, objectFloatMap, objectFloatMap2, false);
                    if (a9 != null) {
                        array2.add(a9);
                    }
                }
            }
            if (Game.i.progressManager.difficultyModeAvailable(DifficultyMode.ENDLESS_I)) {
                int i20 = objectIntMap.get(Item.D.BIT_DUST, 0);
                long longValue2 = ((Long) objectMap.get(Item.D.BIT_DUST, 0L)).longValue();
                if (i20 != 0) {
                    ShopOffer a10 = a(Item.D.BIT_DUST, otherItemsRandom, objectFloatMap, objectFloatMap2, true);
                    if (a10 != null) {
                        array3.add(a10);
                    }
                    if (i20 > 50 && (a3 = a(Item.D.BIT_DUST, otherItemsRandom, objectFloatMap, objectFloatMap2, true)) != null) {
                        array3.add(a3);
                    }
                }
                if (longValue2 > 10 && (a2 = a(Item.D.BIT_DUST, otherItemsRandom, objectFloatMap, objectFloatMap2, false)) != null) {
                    array2.add(a2);
                }
            }
        }
        for (int i21 = 0; i21 < array2.size; i21++) {
            array2.swap(i21, otherItemsRandom.nextInt(array2.size));
        }
        for (int i22 = 0; i22 < array3.size; i22++) {
            array3.swap(i22, otherItemsRandom.nextInt(array3.size));
        }
        int i23 = 0;
        for (int i24 = 0; i24 < 200 && array.size < 6; i24++) {
            if (array2.size != 0 && otherItemsRandom.nextFloat() > 0.7f) {
                ShopOffer shopOffer = (ShopOffer) array2.removeIndex(0);
                if (shopOffer.price.getItem() != Item.D.ACCELERATOR || i23 < 3) {
                    boolean z3 = false;
                    int i25 = 0;
                    while (true) {
                        if (i25 < array.size) {
                            ShopOffer shopOffer2 = array.get(i25);
                            if (!shopOffer2.item.getItem().sameAs(shopOffer.item.getItem()) || !shopOffer2.price.getItem().sameAs(shopOffer.price.getItem())) {
                                i25++;
                            } else {
                                z3 = true;
                            }
                        }
                    }
                    if (!z3) {
                        array.add(shopOffer);
                        if (shopOffer.price.getItem() == Item.D.ACCELERATOR) {
                            i23++;
                        }
                    }
                }
            }
            if (array.size < 6) {
                if (array3.size != 0) {
                    ShopOffer shopOffer3 = (ShopOffer) array3.removeIndex(0);
                    if (shopOffer3.price.getItem() != Item.D.ACCELERATOR || i23 < 3) {
                        boolean z4 = false;
                        int i26 = 0;
                        while (true) {
                            if (i26 < array.size) {
                                ShopOffer shopOffer4 = array.get(i26);
                                if (!shopOffer4.item.getItem().sameAs(shopOffer3.item.getItem()) || !shopOffer4.price.getItem().sameAs(shopOffer3.price.getItem())) {
                                    i26++;
                                } else {
                                    z4 = true;
                                }
                            }
                        }
                        if (!z4) {
                            array.add(shopOffer3);
                            if (shopOffer3.price.getItem() == Item.D.ACCELERATOR) {
                                i23++;
                            }
                        }
                    }
                }
                if (array2.size != 0 || array3.size != 0) {
                }
            }
        }
        pP_Progress.setShopOffers(array);
        ProgressPrefs.i().requireSave();
    }

    @Null
    private ShopOffer a(Item item, RandomXS128 randomXS128, ObjectFloatMap<Item> objectFloatMap, ObjectFloatMap<Item> objectFloatMap2, boolean z) {
        int i2;
        int i3;
        int max;
        float f2 = objectFloatMap2.get(item, 0.0f);
        if (f2 > 0.0f) {
            float nextFloat = 0.8f + (randomXS128.nextFloat() * 0.4f);
            for (int i4 = 0; i4 < 5 && randomXS128.nextFloat() > 0.4f; i4++) {
                nextFloat += randomXS128.nextFloat() * 0.6f;
            }
            int round = MathUtils.round(f2 * nextFloat);
            Array array = new Array(true, 1, Item.class);
            for (int i5 = 0; i5 < 6; i5++) {
                array.add(Item.D.GREEN_PAPER);
            }
            boolean z2 = Gdx.app.getType() == Application.ApplicationType.Desktop;
            int i6 = 9;
            if (z2) {
                int accelerators = getAccelerators();
                if (accelerators < 10) {
                    i6 = 1;
                } else if (accelerators < 30) {
                    i6 = 2;
                } else if (accelerators < 80) {
                    i6 = 3;
                } else if (accelerators < 150) {
                    i6 = 4;
                } else if (accelerators < 220) {
                    i6 = 5;
                } else if (accelerators < 340) {
                    i6 = 6;
                } else if (accelerators < 500) {
                    i6 = 7;
                } else if (accelerators < 700) {
                    i6 = 8;
                }
            }
            int allTime = (int) Game.i.statisticsManager.getAllTime(StatisticsType.SOP);
            if (allTime < 40) {
                int round2 = MathUtils.round(i6 * (allTime / 40.0f));
                i6 = round2;
                if (round2 < 2) {
                    i6 = 2;
                } else if (i6 > 9) {
                    i6 = 9;
                }
            }
            for (int i7 = 0; i7 < i6; i7++) {
                array.add(Item.D.ACCELERATOR);
                if (z && randomXS128.nextFloat() > 0.4f) {
                    array.add(Item.D.ACCELERATOR);
                }
            }
            if (isResourceOpened(ResourceType.SCALAR)) {
                for (int i8 = 0; i8 < 5; i8++) {
                    array.add(Item.D.RESOURCE_SCALAR);
                }
            } else {
                array.add(Item.D.GREEN_PAPER);
            }
            if (isResourceOpened(ResourceType.VECTOR)) {
                for (int i9 = 0; i9 < 4; i9++) {
                    array.add(Item.D.RESOURCE_VECTOR);
                }
            } else {
                array.add(Item.D.GREEN_PAPER);
            }
            if (isResourceOpened(ResourceType.MATRIX)) {
                for (int i10 = 0; i10 < 3; i10++) {
                    array.add(Item.D.RESOURCE_MATRIX);
                }
            } else {
                array.add(Item.D.GREEN_PAPER);
            }
            if (isResourceOpened(ResourceType.TENSOR)) {
                for (int i11 = 0; i11 < 2; i11++) {
                    array.add(Item.D.RESOURCE_TENSOR);
                }
            } else {
                array.add(Item.D.GREEN_PAPER);
            }
            if (isResourceOpened(ResourceType.INFIAR)) {
                for (int i12 = 0; i12 < 2; i12++) {
                    array.add(Item.D.RESOURCE_INFIAR);
                }
            } else {
                array.add(Item.D.GREEN_PAPER);
            }
            Object obj = array.get(randomXS128.nextInt(array.size));
            while (true) {
                Item item2 = (Item) obj;
                if (item2.sameAs(item)) {
                    if (array.size == 0) {
                        return null;
                    }
                    obj = array.removeIndex(randomXS128.nextInt(array.size));
                } else {
                    if (item2 instanceof AcceleratorItem) {
                        nextFloat *= 1.5f;
                        round = MathUtils.round(round * 1.5f);
                    }
                    float f3 = (float) (objectFloatMap.get(item, 0.0f) * round * 2.2d);
                    float f4 = objectFloatMap.get(item2, 0.0f);
                    if (f4 > 0.0f && f3 > 0.0f) {
                        int round3 = MathUtils.round((nextFloat - 1.0f) / 0.5f);
                        if (round3 <= 0) {
                            i2 = 0;
                        } else if (round3 == 1 || round3 == 2) {
                            i2 = 5;
                        } else if (round3 == 3 || round3 == 4) {
                            i2 = 10;
                        } else if (round3 == 5 || round3 == 6) {
                            i2 = 15;
                        } else if (round3 == 7 || round3 == 8) {
                            i2 = 20;
                        } else {
                            i2 = 25;
                        }
                        if (randomXS128.nextFloat() < 0.05f) {
                            i2 += 25 + (randomXS128.nextInt(3) * 5);
                            if (randomXS128.nextFloat() < 0.1f) {
                                i2 += 5 + (randomXS128.nextInt(2) * 5);
                            }
                        }
                        if (i2 > 75) {
                            i2 = 75;
                        }
                        if (Math.round(f3 / f4) > 500000000) {
                            return null;
                        }
                        int i13 = (int) Item.class;
                        int i14 = i13;
                        int i15 = i13;
                        if (i2 != 0) {
                            i14 = MathUtils.ceil(i14 * (100 - i2) * 0.01f);
                        }
                        if (item2 == Item.D.ACCELERATOR) {
                            i14 = 1 + MathUtils.ceil((int) Math.pow(i14, 0.92d));
                        }
                        int itemsCount = Game.i.progressManager.getItemsCount(item2);
                        if (item2 == Item.D.GREEN_PAPER) {
                            max = Math.max(Game.i.purchaseManager.getPapersHourBasePrice(Integer.MAX_VALUE, 1.0f), itemsCount);
                        } else if (item2 == Item.D.ACCELERATOR) {
                            max = Math.max(itemsCount, 40);
                        } else {
                            double allTime2 = Game.i.statisticsManager.getAllTime(StatisticsType.RG);
                            if (allTime2 < 3000.0d) {
                                i3 = 500;
                            } else if (allTime2 < 20000.0d) {
                                i3 = 700;
                            } else if (allTime2 < 50000.0d) {
                                i3 = 1000;
                            } else if (allTime2 < 120000.0d) {
                                i3 = 2000;
                            } else if (allTime2 < 250000.0d) {
                                i3 = 3000;
                            } else if (allTime2 < 500000.0d) {
                                i3 = 4000;
                            } else if (allTime2 < 1000000.0d) {
                                i3 = 5000;
                            } else {
                                i3 = 6000;
                            }
                            max = Math.max(itemsCount, i3);
                        }
                        float f5 = 0.0f;
                        if (i14 > max * 1.35f) {
                            int round4 = MathUtils.round(round * (max / i14) * (0.75f + (randomXS128.nextFloat() * 0.6f)));
                            if (round4 <= 0) {
                                return null;
                            }
                            float f6 = round4 / round;
                            int round5 = MathUtils.round(i14 * f6);
                            if (round5 <= 0) {
                                return null;
                            }
                            f5 = f6;
                            i15 = MathUtils.round(i15 * f6);
                            i14 = round5;
                            round = round4;
                        }
                        int b2 = b(i14);
                        int b3 = b(round);
                        int ceil = 100 - MathUtils.ceil((1.0f / (i15 / b2)) * 100.0f);
                        int i16 = ceil;
                        if (ceil <= 2) {
                            i16 = 0;
                        }
                        if (item == Item.D.GREEN_PAPER) {
                            if (item2 == Item.D.ACCELERATOR) {
                                int papersHourBasePrice = (int) ((Game.i.purchaseManager.getPapersHourBasePrice() / 30) * b2);
                                if (b3 < papersHourBasePrice) {
                                    b3 = b((int) (papersHourBasePrice * (1.0f + (randomXS128.nextFloat() * 0.4f))));
                                    f2403a.i("  " + b3, new Object[0]);
                                }
                            } else if (item2 instanceof ResourceItem) {
                                Array<ItemStack> array2 = new Array<>(true, 1, ItemStack.class);
                                item2.addSellItems(array2);
                                int i17 = 0;
                                while (true) {
                                    if (i17 >= array2.size) {
                                        break;
                                    }
                                    ItemStack itemStack = array2.get(i17);
                                    if (itemStack.getItem() != Item.D.GREEN_PAPER) {
                                        i17++;
                                    } else {
                                        int count = itemStack.getCount() * b2;
                                        if (b3 < count) {
                                            b3 = b((int) (count * (1.2f + (randomXS128.nextFloat() * 0.6f))));
                                            f2403a.i("  " + b3, new Object[0]);
                                        }
                                    }
                                }
                            }
                        }
                        if (z2 && item2 == Item.D.ACCELERATOR && getAccelerators() < b2) {
                            return null;
                        }
                        float f7 = 0.0f;
                        if (max > 0) {
                            float f8 = b2 / max;
                            if (f8 < 0.03f) {
                                float f9 = 0.03f / f8;
                                if (item2 == Item.D.ACCELERATOR) {
                                    f9 = Math.min(f9, 2.0f);
                                }
                                if (f9 > 4.0f) {
                                    f9 = 4.0f;
                                }
                                float nextFloat2 = f9 + randomXS128.nextFloat();
                                f7 = nextFloat2;
                                int round6 = MathUtils.round(b2 * nextFloat2);
                                int round7 = MathUtils.round(b3 * nextFloat2);
                                b2 = b(round6);
                                b3 = b(round7);
                            }
                        }
                        ShopOffer shopOffer = new ShopOffer(new ItemStack(item2, b2), new ItemStack(item, b3), i16);
                        shopOffer.reducedDueToLargePrice = f5;
                        shopOffer.increasedDueToLowPrice = f7;
                        return shopOffer;
                    }
                    return null;
                }
            }
        } else {
            return null;
        }
    }

    private static int b(int i2) {
        if (i2 < 100) {
            return i2;
        }
        if (i2 < 1000) {
            return MathUtils.round(i2 * 0.1f) * 10;
        }
        if (i2 < 10000) {
            return MathUtils.round(i2 * 0.01f) * 100;
        }
        if (i2 < 100000) {
            return MathUtils.round(i2 * 0.001f) * 1000;
        }
        if (i2 < 1000000) {
            return MathUtils.round(i2 * 1.0E-4f) * CGL.kCGLBadAttribute;
        }
        return MathUtils.round(i2 * 1.0E-5f) * 100000;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0c0a  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x0c46  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x0c5d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0c5d A[ADDED_TO_REGION, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void generateNewLuckyWheel() {
        /*
            Method dump skipped, instructions count: 4415
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.managers.ProgressManager.generateNewLuckyWheel():void");
    }

    public Array<LuckyWheelOverlay.WheelOption> getLuckyWheelOptions() {
        if (ProgressPrefs.i().progress.getLuckyWheelOptions() == null || ProgressPrefs.i().progress.getLuckyWheelOptions().size == 0) {
            generateNewLuckyWheel();
        }
        return ProgressPrefs.i().progress.getLuckyWheelOptions();
    }

    public boolean isLuckyWheelSpinAvailable() {
        return ProgressPrefs.i().progress.isLuckyWheelSpinAvailable();
    }

    public int getSecondsTillLuckyWheelSpinAvailable() {
        return Math.max(45 - ((int) ((Game.getTimestampMillis() - Game.i.authManager.getLastLoadFromCloudTimestamp()) / 1000)), 0);
    }

    public int getLuckyWheelRespinPriceTokens() {
        if (ProgressPrefs.i().progress.isLuckyWheelSpinAvailable()) {
            return 0;
        }
        Array<LuckyWheelOverlay.WheelOption> luckyWheelOptions = getLuckyWheelOptions();
        if (!c() && ProgressPrefs.i().progress.getLuckyWheelCurrentMultiplier() < 4 && luckyWheelOptions.size >= 6) {
            return 2;
        }
        return 0;
    }

    private boolean c() {
        Array<LuckyWheelOverlay.WheelOption> luckyWheelOptions = getLuckyWheelOptions();
        int i2 = 0;
        for (int i3 = 0; i3 < luckyWheelOptions.size; i3++) {
            if (luckyWheelOptions.get(i3).item != null) {
                i2++;
            }
        }
        if (i2 == 0) {
            return true;
        }
        if (i2 == 1) {
            for (int i4 = 0; i4 < luckyWheelOptions.size; i4++) {
                if (luckyWheelOptions.get(i4).item != null && luckyWheelOptions.get(i4).item.getItem().getType() == ItemType.GREEN_PAPER && luckyWheelOptions.get(i4).item.getCount() < 500) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public int getLuckyWheelRespinPriceAccelerators() {
        int i2;
        if (ProgressPrefs.i().progress.isLuckyWheelSpinAvailable()) {
            return 0;
        }
        Array<LuckyWheelOverlay.WheelOption> luckyWheelOptions = getLuckyWheelOptions();
        if (c()) {
            return 0;
        }
        if (luckyWheelOptions.size >= 7) {
            i2 = 10;
        } else if (luckyWheelOptions.size == 6) {
            i2 = 20;
        } else if (luckyWheelOptions.size == 5) {
            i2 = 30;
        } else if (luckyWheelOptions.size == 4) {
            i2 = 40;
        } else if (luckyWheelOptions.size == 3) {
            i2 = 50;
        } else {
            i2 = 0;
        }
        if (i2 != 0) {
            int i3 = 0;
            for (int i4 = 0; i4 < luckyWheelOptions.size; i4++) {
                if (luckyWheelOptions.get(i4).item == null) {
                    i3++;
                }
            }
            if (i3 >= 3) {
                i2 += (i3 - 3) * 10;
            }
            if (ProgressPrefs.i().progress.getLuckyWheelCurrentMultiplier() > 1) {
                f2403a.i("respin mult " + ProgressPrefs.i().progress.getLuckyWheelCurrentMultiplier(), new Object[0]);
                i2 += (ProgressPrefs.i().progress.getLuckyWheelCurrentMultiplier() - 1) * 5;
            }
        }
        return i2;
    }

    public VideoAdViewBonus[] getVideoAdViewBonuses() {
        if (this.videoAdViewBonuses == null) {
            this.videoAdViewBonuses = new VideoAdViewBonus[]{new VideoAdViewBonus(30, new ItemStack(Item.D.LOOT_BOOST, 1)), new VideoAdViewBonus(70, new ItemStack(Item.D.F_CASE.create(CaseType.CYAN, false), 1)), new VideoAdViewBonus(110, new ItemStack(Item.D.ACCELERATOR, 30)), new VideoAdViewBonus(150, new ItemStack(Item.D.RESEARCH_TOKEN, 1)), new VideoAdViewBonus(200, new ItemStack(Item.D.ACCELERATOR, 50)), new VideoAdViewBonus(User32.VK_PLAY, new ItemStack(Item.D.F_CASE.create(CaseType.CYAN, false), 2)), new VideoAdViewBonus(300, new ItemStack(Item.D.ACCELERATOR, 300), true)};
        }
        return this.videoAdViewBonuses;
    }

    public float getLootBoostTimeLeft() {
        return ProgressPrefs.i().progress.getLootBoostTimeLeft();
    }

    public int getMaxCraftQueueSize() {
        return StrictMath.min(Game.i.gameValueManager.getSnapshot().getIntValue(GameValueType.CRAFTING_QUEUE_MAX_SIZE), 6);
    }

    public void startCrafting(CraftRecipe craftRecipe, Array<Item> array, int i2) {
        if (craftRecipe == null) {
            throw new IllegalArgumentException("recipe is null");
        }
        if (craftRecipe.ingredients.size != array.size) {
            throw new IllegalArgumentException("ingredients.size != recipe.ingredients.size (" + array.size + ", " + craftRecipe.ingredients.size + ")");
        }
        if (i2 < 0 || i2 > craftRecipe.getMaxQueueStackWithGVs()) {
            throw new IllegalArgumentException("count must be 1 <=> " + craftRecipe.getMaxQueueStackWithGVs() + ", " + i2 + " given");
        }
        for (int i3 = 0; i3 < craftRecipe.ingredients.size; i3++) {
            CraftRecipe.Ingredient ingredient = craftRecipe.ingredients.items[i3];
            if (array.items[i3] == null) {
                throw new IllegalArgumentException("ingredient " + i3 + " is null");
            }
            if (ingredient.fits(array.items[i3])) {
                int countWithGVs = ingredient.getCountWithGVs() * i2;
                int itemsCount = Game.i.progressManager.getItemsCount(array.items[i3]);
                if (itemsCount < countWithGVs) {
                    throw new IllegalStateException("not enough of " + String.valueOf(array.items[i3]) + " to craft " + i2 + " items (" + itemsCount + "/" + countWithGVs + ")");
                }
            } else {
                throw new IllegalArgumentException("ingredient " + i3 + " doesn't fit recipe (" + String.valueOf(array.items[i3]) + ")");
            }
        }
        for (int i4 = 0; i4 < craftRecipe.ingredients.size; i4++) {
            removeItems(array.items[i4], craftRecipe.ingredients.items[i4].getCountWithGVs() * i2);
        }
        addItems(craftRecipe.result.getItem(), craftRecipe.result.getCount() * i2, "crafted");
    }

    public void removeAllItems() {
        ProgressPrefs.i().inventory.removeAllItems();
    }

    public int getAcceleratorsRequiredToShortenTime(float f2) {
        return MathUtils.ceil(f2 / 600.0f);
    }

    public static RarityType getRarityFromQuality(float f2) {
        return f2 >= 1.0f ? RarityType.LEGENDARY : f2 < 0.0f ? RarityType.COMMON : RarityType.values[MathUtils.floor(f2 * RarityType.values.length)];
    }

    public static float getMinQuality(RarityType rarityType) {
        return rarityType.ordinal() * 0.2f;
    }

    public static float getMaxQuality(RarityType rarityType) {
        return (rarityType.ordinal() * 0.2f) + 0.2f;
    }

    public static float globalQualityToRarityQualuty(float f2) {
        return MathUtils.clamp((f2 % 0.2f) * 5.0f, 0.0f, 1.0f);
    }

    public Color[] getRarityColors() {
        if (Game.i.settingsManager.cvdFriendlyColors()) {
            return f;
        }
        return d;
    }

    public Color[] getRarityBrightColors() {
        if (Game.i.settingsManager.cvdFriendlyColors()) {
            return g;
        }
        return e;
    }

    public Color getRarityColor(RarityType rarityType) {
        return getRarityColors()[rarityType.ordinal()];
    }

    public String getRarityIcon(RarityType rarityType) {
        return i[rarityType.ordinal()];
    }

    public Color getRarityBrightColor(RarityType rarityType) {
        return getRarityBrightColors()[rarityType.ordinal()];
    }

    public String getRarityName(RarityType rarityType) {
        return Game.i.localeManager.i18n.get(h[rarityType.ordinal()]);
    }

    public boolean difficultyModeAvailable(DifficultyMode difficultyMode) {
        if (DifficultyMode.isEndless(difficultyMode) && !Game.i.gameValueManager.getSnapshot().getBooleanValue(GameValueType.ENDLESS_MODE)) {
            return false;
        }
        return true;
    }

    public DifficultyMode getDifficultyMode() {
        return ProgressPrefs.i().progress.getDifficultyMode();
    }

    public void setDifficultyMode(DifficultyMode difficultyMode) {
        if (ProgressPrefs.i().progress.getDifficultyMode() != difficultyMode) {
            ProgressPrefs.i().progress.setDifficultyMode(difficultyMode);
            ProgressPrefs.i().requireSave();
            Game.i.gameValueManager.requireRecalculation();
        }
    }

    public Color getDifficultyModeColor(DifficultyMode difficultyMode) {
        switch (difficultyMode) {
            case EASY:
                return MaterialColor.LIGHT_GREEN.P500;
            case NORMAL:
                return MaterialColor.LIGHT_BLUE.P500;
            case ENDLESS_I:
                return MaterialColor.AMBER.P500;
            default:
                return Color.WHITE;
        }
    }

    public String getDifficultyName(DifficultyMode difficultyMode) {
        return Game.i.localeManager.i18n.get("difficulty_mode_" + difficultyMode);
    }

    public static int clampModeDifficulty(DifficultyMode difficultyMode, int i2, @Null BasicLevel basicLevel, boolean z, boolean z2, ProgressSnapshotForState progressSnapshotForState) {
        switch (difficultyMode) {
            case EASY:
                return 80;
            case NORMAL:
                return 100;
            default:
                return clampModeDifficultyGVP(difficultyMode, i2, GameValueManager.createSnapshot(null, difficultyMode, false, basicLevel, z, z2, progressSnapshotForState));
        }
    }

    public static int clampModeDifficultyGVP(DifficultyMode difficultyMode, int i2, GameValueProvider gameValueProvider) {
        switch (difficultyMode) {
            case EASY:
                return 80;
            case NORMAL:
                return 100;
            default:
                return MathUtils.clamp(i2, 150, MathUtils.round(((float) gameValueProvider.getPercentValueAsMultiplier(GameValueType.ENDLESS_MODE_DIFFICULTY)) * 100.0f));
        }
    }

    public int getDifficultyModeDiffMultiplier(DifficultyMode difficultyMode) {
        int i2 = 100;
        switch (difficultyMode) {
            case EASY:
                i2 = 87;
                break;
            case ENDLESS_I:
                i2 = MathUtils.round(((float) Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.ENDLESS_MODE_DIFFICULTY)) * 100.0f);
                break;
        }
        return clampModeDifficultyGVP(difficultyMode, i2, Game.i.gameValueManager.getSnapshot());
    }

    public static float getDifficultyModePrizeMultiplier(DifficultyMode difficultyMode) {
        switch (difficultyMode) {
            case EASY:
                return 0.75f;
            case NORMAL:
                return 1.0f;
            case ENDLESS_I:
                return 1.5f;
            default:
                return 1.0f;
        }
    }

    public boolean openPack(Item item, int i2, boolean z, boolean z2) {
        IssuedItems issuedItems;
        if (i2 <= 0) {
            throw new IllegalArgumentException("incorrect count " + i2);
        }
        if (!item.canBeUnpacked()) {
            throw new IllegalArgumentException("Item can't be unpacked: " + item.getType());
        }
        if (removeItems(item, i2)) {
            if (item.getType() == ItemType.RANDOM_TILE) {
                IssuedItems issuedItems2 = new IssuedItems(IssuedItems.IssueReason.RANDOM_TILE_PACK, Game.getTimestampSeconds());
                issuedItems = issuedItems2;
                issuedItems2.randomTilePackQuality = ((RandomTileItem) item).quality;
            } else if (item.getType() == ItemType.CASE) {
                IssuedItems issuedItems3 = new IssuedItems(IssuedItems.IssueReason.CASE, Game.getTimestampSeconds());
                issuedItems = issuedItems3;
                issuedItems3.caseType = ((CaseItem) item).caseType;
            } else if (item.getType() == ItemType.RANDOM_TELEPORT) {
                issuedItems = new IssuedItems(IssuedItems.IssueReason.RANDOM_TELEPORT_PACK, Game.getTimestampSeconds());
            } else if (item.getType() == ItemType.RANDOM_BARRIER) {
                IssuedItems issuedItems4 = new IssuedItems(IssuedItems.IssueReason.RANDOM_BARRIER_PACK, Game.getTimestampSeconds());
                issuedItems = issuedItems4;
                issuedItems4.randomBarrierPackQuality = ((RandomBarrierItem) item).quality;
            } else {
                throw new IllegalStateException("Not implemented for unpacking: " + item.getType().name());
            }
            issuedItems.massUnpackCount = i2;
            InventoryStatistics cpy = Game.i.progressManager.getInventoryStatistics().cpy();
            for (int i3 = 0; i3 < i2; i3++) {
                Array<ItemStack> openPack = item.openPack(cpy);
                issuedItems.items.addAll(openPack);
                for (int i4 = 0; i4 < openPack.size; i4++) {
                    ItemStack itemStack = openPack.items[i4];
                    cpy.countItem(itemStack.getItem(), itemStack.getCount());
                }
            }
            boolean z3 = issuedItems.items.size > 50;
            boolean z4 = z3;
            if (z3) {
                compressStacksArray(issuedItems.items);
            }
            issuedItems.items.sort(ItemStack.SORT_COMPARATOR_RARITY_ASC);
            Game.i.progressManager.addItemArray(issuedItems.items, "pack_open_" + item.getAnalyticName());
            Game.i.progressManager.addIssuedPrizes(issuedItems, z2);
            if (z) {
                OpenedPackOverlay.i().show(issuedItems.items, z4);
            }
            return z4;
        }
        Notifications.i().add(Game.i.localeManager.i18n.get("not_enough_items"), Game.i.assetManager.getDrawable("icon-times"), MaterialColor.ORANGE.P800, StaticSoundType.FAIL);
        return false;
    }

    public long calculateProgressHash() {
        long accelerators = ((31 + getAccelerators()) * 31) + getGreenPapers();
        for (int i2 = 0; i2 < ResourceType.values.length; i2++) {
            accelerators = (accelerators * 31) + getResources(r0[i2]);
        }
        return accelerators;
    }

    public boolean existsAnyProgress() {
        return (getGreenPapers() == 0 && Game.i.statisticsManager.getAllTime(StatisticsType.PT) == 0.0d && !ProgressPrefs.i().purchase.hasAnyTransaction()) ? false : true;
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void setup() {
        if (!Config.isHeadless()) {
            Game.i.gameValueManager.addListener(this.k);
            Game.i.authManager.addListener(new AuthManager.AuthManagerListener.AuthManagerListenerAdapter() { // from class: com.prineside.tdi2.managers.ProgressManager.4
                @Override // com.prineside.tdi2.managers.AuthManager.AuthManagerListener.AuthManagerListenerAdapter, com.prineside.tdi2.managers.AuthManager.AuthManagerListener
                public void signInStatusUpdated() {
                    if (Game.i.authManager.isProfileStatusActive(Config.PROFILE_STATUS_DOUBLE_GAIN)) {
                        ProgressManager.this.enableDoubleGainPermanently();
                    }
                }
            });
        }
    }

    public void addListener(ProgressManagerListener progressManagerListener) {
        if (progressManagerListener == null) {
            throw new IllegalArgumentException("listener is null");
        }
        if (!this.j.contains(progressManagerListener, true)) {
            this.j.add(progressManagerListener);
        }
    }

    public void removeListener(ProgressManagerListener progressManagerListener) {
        if (progressManagerListener == null) {
            throw new IllegalArgumentException("listener is null");
        }
        this.j.removeValue(progressManagerListener, true);
    }

    public boolean areModifiersOpened() {
        for (ModifierType modifierType : ModifierType.values) {
            if (Game.i.gameValueManager.getSnapshot().getIntValue(Game.i.modifierManager.getCountGameValue(modifierType)) > 0) {
                return true;
            }
        }
        return false;
    }

    public void setCustomValue(String str, @Null String str2) {
        ProgressPrefs.i().custom.setValue(str, str2);
    }

    @Null
    public String getCustomValue(String str, @Null String str2) {
        return ProgressPrefs.i().custom.getValue(str, str2);
    }

    public String[] getCustomKeys() {
        return ProgressPrefs.i().custom.getKeys();
    }

    public boolean isResourceOpened(ResourceType resourceType) {
        switch (resourceType) {
            case SCALAR:
                return Game.i.researchManager.getResearchInstance(ResearchType.MINER_TYPE_SCALAR).getInstalledLevel() > 0;
            case VECTOR:
                return Game.i.researchManager.getResearchInstance(ResearchType.MINER_TYPE_VECTOR).getInstalledLevel() > 0;
            case MATRIX:
                return Game.i.researchManager.getResearchInstance(ResearchType.MINER_TYPE_MATRIX).getInstalledLevel() > 0;
            case TENSOR:
                return Game.i.researchManager.getResearchInstance(ResearchType.MINER_TYPE_TENSOR).getInstalledLevel() > 0;
            case INFIAR:
                return Game.i.researchManager.getResearchInstance(ResearchType.MINER_TYPE_INFIAR).getInstalledLevel() > 0;
            default:
                return false;
        }
    }

    public int getExtraDecryptingSlotsCount() {
        int intValue = Game.i.gameValueManager.getSnapshot().getIntValue(GameValueType.DECRYPTING_QUEUE_MAX_SIZE);
        if (intValue < 0) {
            return 0;
        }
        if (intValue > 2) {
            return 2;
        }
        return intValue;
    }

    public boolean areAbilitiesOpened() {
        return Game.i.gameValueManager.getSnapshot().getIntValue(GameValueType.ABILITY_FIREBALL_MAX_PER_GAME) != 0;
    }

    public static ItemStack addItemToStacksArray(Array<ItemStack> array, Item item, int i2) {
        for (int i3 = 0; i3 < array.size; i3++) {
            ItemStack itemStack = array.get(i3);
            if (itemStack.getItem().sameAs(item)) {
                itemStack.setCount(PMath.addWithoutOverflow(itemStack.getCount(), i2));
                return itemStack;
            }
        }
        ItemStack itemStack2 = new ItemStack(item, i2);
        array.add(itemStack2);
        return itemStack2;
    }

    public static ItemStack removeItemFromStacksArray(Array<ItemStack> array, Item item, int i2) {
        Preconditions.checkArgument(i2 > 0, "Count must be > 0, %s given", i2);
        ItemStack itemStackFromArray = getItemStackFromArray(array, item);
        if (itemStackFromArray != null && itemStackFromArray.getCount() >= i2) {
            itemStackFromArray.setCount(itemStackFromArray.getCount() - i2);
            return itemStackFromArray;
        }
        return null;
    }

    public static ItemStack getItemStackFromArray(Array<ItemStack> array, Item item) {
        for (int i2 = 0; i2 < array.size; i2++) {
            ItemStack itemStack = array.get(i2);
            if (itemStack.getItem().sameAs(item)) {
                return itemStack;
            }
        }
        return null;
    }

    public static void compressStacksArray(Array<ItemStack> array) {
        for (int i2 = array.size - 1; i2 >= 0; i2--) {
            boolean z = false;
            int i3 = 0;
            while (true) {
                if (i3 >= i2) {
                    break;
                }
                ItemStack itemStack = array.get(i2);
                ItemStack itemStack2 = array.get(i3);
                if (!itemStack.getItem().sameAs(itemStack2.getItem())) {
                    i3++;
                } else {
                    itemStack2.setCount(PMath.addWithoutOverflow(itemStack.getCount(), itemStack2.getCount()));
                    z = true;
                    break;
                }
            }
            if (z) {
                array.removeIndex(i2);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void addItems(Item item, int i2, @Null String str) {
        int itemsCount = getItemsCount(item);
        ProgressPrefs.i().inventory.addItems(item, i2);
        ProgressPrefs.i().requireSave();
        this.j.begin();
        for (int i3 = 0; i3 < this.j.size; i3++) {
            this.j.get(i3).itemsChanged(item, itemsCount, i2);
        }
        this.j.end();
        if ((item instanceof Item.UsableItem) && ((Item.UsableItem) item).autoUseWhenAdded()) {
            for (int i4 = 0; i4 < i2; i4++) {
                ((Item.UsableItem) item).useItem();
                Notifications.i().add(Game.i.localeManager.i18n.get("item_used_notification") + SequenceUtils.SPACE + ((Object) item.getTitle()), null, null, null);
            }
        }
        Game.i.authManager.notifyNeedCloudSave(true);
        if (str != null) {
            Game.i.analyticsManager.logCurrencyReceived(item.getAnalyticName(), str, i2);
        }
    }

    public void addItemStack(ItemStack itemStack, @Null String str) {
        addItems(itemStack.getItem(), itemStack.getCount(), str);
    }

    public void addItemArray(Array<ItemStack> array, String str) {
        for (int i2 = 0; i2 < array.size; i2++) {
            addItemStack(array.get(i2), str);
        }
    }

    public int getItemsCount(Item item) {
        return ProgressPrefs.i().inventory.getItemsCount(item);
    }

    private ItemStack a(Item item) {
        DelayedRemovalArray<ItemStack> itemsByType = getItemsByType(item.getType());
        for (int i2 = 0; i2 < itemsByType.size; i2++) {
            if (itemsByType.get(i2).getItem().sameAs(item)) {
                return itemsByType.get(i2);
            }
        }
        return null;
    }

    public boolean removeItems(Item item, int i2) {
        PP_Inventory.ItemRemoveResult removeItems = ProgressPrefs.i().inventory.removeItems(item, i2);
        if (removeItems.removedRequiredAmount) {
            if (removeItems.remainingCount == 0) {
                setStarred(item, false);
            }
            int i3 = removeItems.remainingCount + i2;
            this.j.begin();
            for (int i4 = 0; i4 < this.j.size; i4++) {
                this.j.get(i4).itemsChanged(item, i3, -i2);
            }
            this.j.end();
        }
        return removeItems.removedRequiredAmount;
    }

    public boolean sellItems(Item item, int i2) {
        ItemStack a2;
        if (item == null || (a2 = a(item)) == null || a2.getCount() < i2 || !item.canBeSold() || isStarred(item)) {
            return false;
        }
        synchronized (m) {
            m.clear();
            item.addSellItems(m);
            for (int i3 = 0; i3 < m.size; i3++) {
                ItemStack itemStack = m.items[i3];
                itemStack.setCount(PMath.multiplyWithoutOverflow(itemStack.getCount(), i2));
            }
            addItemArray(m, "sell");
            m.clear();
        }
        removeItems(item, i2);
        return true;
    }

    public boolean hasAnyItem() {
        return ProgressPrefs.i().inventory.hasAnyItem();
    }

    public boolean isStarred(Item item) {
        return ProgressPrefs.i().inventory.isStarred(item);
    }

    public void setStarred(Item item, boolean z) {
        if (ProgressPrefs.i().inventory.setStarred(item, z)) {
            ProgressPrefs.i().requireSave();
        }
    }

    public Item getItem(Item item) {
        DelayedRemovalArray<ItemStack> itemsByType = getItemsByType(item.getType());
        for (int i2 = 0; i2 < itemsByType.size; i2++) {
            if (itemsByType.items[i2].getItem().sameAs(item)) {
                return itemsByType.items[i2].getItem();
            }
        }
        return null;
    }

    public DelayedRemovalArray<ItemStack> getItemsByType(ItemType itemType) {
        return ProgressPrefs.i().inventory.getItemsByType(itemType);
    }

    public DelayedRemovalArray<ItemStack> getItemsByCategory(ItemCategoryType itemCategoryType) {
        return ProgressPrefs.i().inventory.getItemsByCategory(itemCategoryType);
    }

    public DelayedRemovalArray<ItemStack> getItemsBySubcategory(ItemSubcategoryType itemSubcategoryType) {
        return ProgressPrefs.i().inventory.getItemsBySubcategory(itemSubcategoryType);
    }

    public void addResources(ResourceType resourceType, int i2, String str) {
        addItems(Item.D.F_RESOURCE.create(resourceType), i2, str);
    }

    @Deprecated
    public void setResources(ResourceType resourceType, int i2) {
        int resources = getResources(resourceType);
        if (i2 < resources) {
            removeResources(resourceType, resources - i2);
        } else if (i2 > resources) {
            addResources(resourceType, i2 - resources, null);
        }
    }

    public boolean removeResources(ResourceType resourceType, int i2) {
        if (i2 == 0) {
            return true;
        }
        if (!removeItems(Item.D.F_RESOURCE.create(resourceType), i2)) {
            return false;
        }
        Game.i.statisticsManager.registerDelta(StatisticsType.RS, i2);
        switch (resourceType) {
            case SCALAR:
                Game.i.statisticsManager.registerDelta(StatisticsType.RS_S, i2);
                return true;
            case VECTOR:
                Game.i.statisticsManager.registerDelta(StatisticsType.RS_V, i2);
                return true;
            case MATRIX:
                Game.i.statisticsManager.registerDelta(StatisticsType.RS_M, i2);
                return true;
            case TENSOR:
                Game.i.statisticsManager.registerDelta(StatisticsType.RS_T, i2);
                return true;
            case INFIAR:
                Game.i.statisticsManager.registerDelta(StatisticsType.RS_I, i2);
                return true;
            default:
                return true;
        }
    }

    public int getResources(ResourceType resourceType) {
        return getItemsCount(Item.D.F_RESOURCE.create(resourceType));
    }

    @Deprecated
    public void addTiles(Tile tile, int i2) {
        addItems(Item.D.F_TILE.create(tile), i2, null);
    }

    @Deprecated
    public void addGates(Gate gate, int i2) {
        addItems(Item.D.F_GATE.create(gate), i2, null);
    }

    @Deprecated
    public void addAbilities(AbilityType abilityType, int i2) {
        addItems(Item.D.F_ABILITY.create(abilityType), i2, null);
    }

    public void setAbilities(AbilityType abilityType, int i2) {
        int abilities = getAbilities(abilityType);
        if (i2 < abilities) {
            removeAbilities(abilityType, abilities - i2);
        } else if (i2 > abilities) {
            addAbilities(abilityType, i2 - abilities);
        }
    }

    public boolean removeAbilities(AbilityType abilityType, int i2) {
        if (i2 == 0) {
            return true;
        }
        int min = Math.min(i2, getItemsCount(Item.D.F_ABILITY.create(abilityType)));
        if (min == 0) {
            return false;
        }
        return removeItems(Item.D.F_ABILITY.create(abilityType), min);
    }

    public int getAbilities(AbilityType abilityType) {
        return getItemsCount(Item.D.F_ABILITY.create(abilityType));
    }

    @Deprecated
    public void setMoney(int i2) {
        int greenPapers = getGreenPapers();
        if (i2 < greenPapers) {
            removeGreenPapers(greenPapers - i2);
        } else if (i2 > greenPapers) {
            addGreenPapers(i2 - greenPapers, null);
        }
    }

    public int getGreenPapers() {
        return getItemsCount(Item.D.GREEN_PAPER);
    }

    public void addGreenPapers(int i2, String str) {
        addItems(Item.D.GREEN_PAPER, i2, str);
    }

    public boolean removeGreenPapers(int i2) {
        if (i2 == 0) {
            return true;
        }
        boolean removeItems = removeItems(Item.D.GREEN_PAPER, i2);
        if (removeItems) {
            Game.i.statisticsManager.registerDelta(StatisticsType.GPS, i2);
        }
        return removeItems;
    }

    @Deprecated
    public void setAccelerators(int i2) {
        int greenPapers = getGreenPapers();
        if (i2 < greenPapers) {
            removeAccelerators(greenPapers - i2);
        } else if (i2 > greenPapers) {
            addAccelerators(i2 - greenPapers, null);
        }
    }

    public int getAccelerators() {
        return getItemsCount(Item.D.ACCELERATOR);
    }

    public void addAccelerators(int i2, String str) {
        addItems(Item.D.ACCELERATOR, i2, str);
    }

    public boolean removeAccelerators(int i2) {
        if (i2 == 0) {
            return true;
        }
        return removeItems(Item.D.ACCELERATOR, i2);
    }

    public void addIssuedPrizes(IssuedItems issuedItems, boolean z) {
        if (!z) {
            issuedItems.shown = true;
        }
        issuedItems.items.sort(ITEM_RARITY_COMPARATOR);
        Array<IssuedItems> issuedItems2 = getIssuedItems();
        issuedItems2.insert(0, issuedItems);
        if (issuedItems2.size > 50) {
            issuedItems2.setSize(50);
        }
        this.c = true;
    }

    public Array<IssuedItems> getIssuedPrizes() {
        return getIssuedItems();
    }

    public void showNewlyIssuedPrizesPopup() {
        boolean z = false;
        Array<IssuedItems> issuedItems = getIssuedItems();
        int i2 = 0;
        while (true) {
            if (i2 >= issuedItems.size) {
                break;
            }
            if (issuedItems.get(i2).shown) {
                i2++;
            } else {
                z = true;
                break;
            }
        }
        if (z) {
            Array<IssuedItems> array = new Array<>();
            for (int i3 = 0; i3 < issuedItems.size; i3++) {
                if (!issuedItems.get(i3).shown) {
                    issuedItems.get(i3).shown = true;
                    array.add(issuedItems.get(i3));
                }
            }
            IssuedPrizesOverlay.i().show(array);
            this.c = true;
        }
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void postRender(float f2) {
        if (this.c) {
            this.c = false;
            Array array = new Array(getIssuedItems());
            Threads.i().runAsync(() -> {
                Json json = new Json(JsonWriter.OutputType.minimal);
                StringWriter stringWriter = new StringWriter();
                json.setWriter(stringWriter);
                json.writeArrayStart();
                for (int i2 = 0; i2 < array.size; i2++) {
                    json.writeObjectStart();
                    ((IssuedItems) array.get(i2)).toJson(json);
                    json.writeObjectEnd();
                }
                json.writeArrayEnd();
                synchronized (this) {
                    Gdx.files.local(PreferencesManager.getIssuedItemsFilePath()).writeString(stringWriter.toString(), false);
                }
            });
        }
    }

    public int countAcceleratorsNeeded(int i2) {
        return (int) StrictMath.ceil(StrictMath.pow((i2 / 60.0f) / 5.0f, 0.75d));
    }

    public void givePendingBonuses() {
        if (!ProgressPrefs.i().progress.isBonusGivenForRegByInvite() && Game.i.authManager.getInvitedById() != null && Game.i.basicLevelManager.isOpened(Game.i.basicLevelManager.getLevel("2.1"))) {
            addItems(Item.D.F_CASE.create(CaseType.PURPLE, true), 1, "been_invited");
            IssuedItems issuedItems = new IssuedItems(IssuedItems.IssueReason.SIGNED_UP_BY_INVITE, Game.getTimestampSeconds());
            issuedItems.items.add(new ItemStack(Item.D.F_CASE.create(CaseType.PURPLE, true), 1));
            addIssuedPrizes(issuedItems, true);
            ProgressPrefs.i().progress.setBonusGivenForRegByInvite(true);
            ProgressPrefs.i().requireSave();
        }
    }

    public ProgressSnapshotForState createProgressSnapshotForState() {
        ProgressSnapshotForState progressSnapshotForState = new ProgressSnapshotForState();
        PP_Research pP_Research = ProgressPrefs.i().research;
        for (ResearchType researchType : ResearchType.values) {
            int installedLevel = pP_Research.getInstalledLevel(researchType);
            if (installedLevel > 0) {
                progressSnapshotForState.f2409a.put(researchType.ordinal(), installedLevel);
            }
        }
        Array<TrophyType> receivedTrophies = Game.i.trophyManager.getReceivedTrophies();
        for (int i2 = 0; i2 < receivedTrophies.size; i2++) {
            progressSnapshotForState.f2410b.add(receivedTrophies.get(i2).ordinal());
        }
        for (int i3 = 0; i3 < Game.i.basicLevelManager.levelsOrdered.size; i3++) {
            BasicLevel basicLevel = Game.i.basicLevelManager.levelsOrdered.items[i3];
            for (int i4 = 0; i4 < basicLevel.quests.size; i4++) {
                BasicLevelQuestConfig basicLevelQuestConfig = basicLevel.quests.items[i4];
                if (basicLevelQuestConfig.wasEverCompleted()) {
                    for (int i5 = 0; i5 < basicLevelQuestConfig.prizes.size; i5++) {
                        ItemStack itemStack = basicLevelQuestConfig.prizes.items[i5];
                        if (itemStack.getItem().getType() == ItemType.GAME_VALUE_GLOBAL || itemStack.getItem().getType() == ItemType.GAME_VALUE_LEVEL) {
                            progressSnapshotForState.c.add(basicLevelQuestConfig.id);
                            break;
                        }
                    }
                }
            }
            for (int i6 = 0; i6 < basicLevel.waveQuests.size; i6++) {
                BasicLevel.WaveQuest waveQuest = basicLevel.waveQuests.get(i6);
                if (waveQuest.isCompleted()) {
                    for (int i7 = 0; i7 < waveQuest.prizes.size; i7++) {
                        ItemStack itemStack2 = waveQuest.prizes.items[i7];
                        if (itemStack2.getItem().getType() == ItemType.GAME_VALUE_GLOBAL || itemStack2.getItem().getType() == ItemType.GAME_VALUE_LEVEL) {
                            progressSnapshotForState.c.add(waveQuest.id);
                            break;
                        }
                    }
                }
            }
        }
        progressSnapshotForState.statsPlayTimeCasesLoot = MathUtils.round((float) Game.i.statisticsManager.getAllTime(StatisticsType.PTCL));
        progressSnapshotForState.statsPlayRealTime = MathUtils.round((float) Game.i.statisticsManager.getAllTime(StatisticsType.PRT));
        progressSnapshotForState.statsQueuedCasesGiven = MathUtils.round((float) Game.i.statisticsManager.getAllTime(StatisticsType.EQCG));
        return progressSnapshotForState;
    }

    public void disableDoubleGainTemp() {
        ProgressPrefs.i().progress.setDoubleGainEnabled(false);
        ProgressPrefs.i().requireSave();
    }

    public void enableDoubleGainPermanently() {
        if (!ProgressPrefs.i().progress.isDoubleGainEnabled()) {
            ProgressPrefs.i().progress.setDoubleGainEnabled(true);
            ProgressPrefs.i().requireSave();
            this.j.begin();
            int i2 = this.j.size;
            for (int i3 = 0; i3 < i2; i3++) {
                this.j.get(i3).doubleGainEnabled();
            }
            this.j.end();
            Notifications.i().add(Game.i.localeManager.i18n.get("double_gain_enabled_permanently"), Game.i.assetManager.getDrawable("money-pack-double-gain"), MaterialColor.GREEN.P800, StaticSoundType.SUCCESS);
        }
    }

    public void giveDoubleGainIfNecessary() {
        if (Gdx.app.getType() == Application.ApplicationType.Desktop && Game.i.actionResolver.doubleGainEnabledBySteamGamePurchase() && !ProgressPrefs.i().progress.isSteamRewardReceived()) {
            f2403a.i("eligible for free DG, reward not received", new Object[0]);
            if (hasPermanentDoubleGain()) {
                IssuedItems issuedItems = new IssuedItems(IssuedItems.IssueReason.PURCHASE, Game.getTimestampSeconds());
                issuedItems.items.add(new ItemStack(Item.D.GREEN_PAPER, 30000));
                issuedItems.items.add(new ItemStack(Item.D.ACCELERATOR, 300));
                issuedItems.items.add(new ItemStack(Item.D.RARITY_BOOST, 10));
                issuedItems.items.add(new ItemStack(Item.D.LUCKY_SHOT_TOKEN, 10));
                addIssuedPrizes(issuedItems, true);
                addItemArray(issuedItems.items, "steam_game_purchase_reward");
                showNewlyIssuedPrizesPopup();
                Notifications.i().add("Double Gain was already enabled, please take these items instead. Thank you for playing Infinitode 2 on Steam!", Game.i.assetManager.getDrawable("icon-check"), MaterialColor.LIGHT_GREEN.P800, StaticSoundType.SUCCESS);
            } else {
                enableDoubleGainPermanently();
                Notifications.i().add("Double Gain enabled, thank you for playing Infinitode 2 on Steam!", Game.i.assetManager.getDrawable("icon-check"), MaterialColor.LIGHT_GREEN.P800, StaticSoundType.SUCCESS);
            }
            ProgressPrefs.i().progress.setSteamRewardReceived(true);
            ProgressPrefs.i().requireSave();
        }
    }

    public int getTempDoubleGainDurationLeft() {
        int tempDoubleGainEndDate = ProgressPrefs.i().progress.getTempDoubleGainEndDate() - Game.getTimestampSeconds();
        int i2 = tempDoubleGainEndDate;
        if (tempDoubleGainEndDate < 0) {
            i2 = 0;
        }
        return i2;
    }

    public boolean enableDoubleGainTemporary(int i2) {
        PP_Progress pP_Progress = ProgressPrefs.i().progress;
        if (!pP_Progress.isDoubleGainEnabled()) {
            if (hasTemporaryDoubleGain()) {
                pP_Progress.setTempDoubleGainEndDate(pP_Progress.getTempDoubleGainEndDate() + i2);
            } else {
                pP_Progress.setTempDoubleGainStartDate(Game.getTimestampSeconds());
                pP_Progress.setTempDoubleGainEndDate(Game.getTimestampSeconds() + i2);
            }
            ProgressPrefs.i().requireSave();
            this.j.begin();
            int i3 = this.j.size;
            for (int i4 = 0; i4 < i3; i4++) {
                this.j.get(i4).doubleGainEnabled();
            }
            this.j.end();
            Notifications.i().add(Game.i.localeManager.i18n.format("double_gain_enabled_timed", StringFormatter.timePassed(i2, false, true)), Game.i.assetManager.getDrawable("money-pack-double-gain"), MaterialColor.GREEN.P800, StaticSoundType.SUCCESS);
            return true;
        }
        Notifications.i().add(Game.i.localeManager.i18n.get("double_gain_enabled_permanently"), Game.i.assetManager.getDrawable("icon-times"), MaterialColor.ORANGE.P800, StaticSoundType.FAIL);
        return false;
    }

    public boolean hasPermanentDoubleGain() {
        return ProgressPrefs.i().progress.isDoubleGainEnabled();
    }

    public boolean hasTemporaryDoubleGain() {
        int timestampSeconds = Game.getTimestampSeconds();
        return timestampSeconds >= ProgressPrefs.i().progress.getTempDoubleGainStartDate() && timestampSeconds <= ProgressPrefs.i().progress.getTempDoubleGainEndDate();
    }

    public boolean isDoubleGainEnabled() {
        if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DOUBLE_GAIN_DISABLED_MANUALLY) != 0.0d) {
            return false;
        }
        return hasPermanentDoubleGain() || hasTemporaryDoubleGain();
    }

    public boolean isPremiumStatusActive() {
        if (Config.isHeadless() || Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.PREMIUM_STATUS_DISABLED_MANUALLY) != 0.0d) {
            return false;
        }
        return Game.i.authManager.isProfileStatusActive(Config.PROFILE_STATUS_PREMIUM);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0951, code lost:            r0.items.add(new com.prineside.tdi2.ItemStack(r17, r0));     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.prineside.tdi2.IssuedItems getRegularRewardingAdItems(int r10) {
        /*
            Method dump skipped, instructions count: 2413
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.managers.ProgressManager.getRegularRewardingAdItems(int):com.prineside.tdi2.IssuedItems");
    }

    public int getGameStartGameVersion() {
        return ProgressPrefs.i().progress.getGameStartGameVersion();
    }

    public int getGameStartTimestamp() {
        return ProgressPrefs.i().progress.getGameStartTimestamp();
    }

    public String getGameStartHash() {
        return ProgressPrefs.i().progress.getGameStartHash();
    }

    public void enableDeveloperMode() {
        if (!isDeveloperModeEnabled()) {
            Game.i.researchManager.setInstalledLevel(ResearchType.DEVELOPER_MODE, 1, true);
        }
    }

    public boolean isDeveloperModeEnabled() {
        if (Config.isModdingMode()) {
            return true;
        }
        return Game.i.gameValueManager != null && Game.i.gameValueManager.getSnapshot().getBooleanValue(GameValueType.DEVELOPER_MODE);
    }

    public void checkSpecialTrophiesGiven() {
        IssuedItems issuedItems = new IssuedItems(IssuedItems.IssueReason.REGULAR_REWARD, Game.getTimestampSeconds());
        DelayedRemovalArray<ItemStack> itemsByType = getItemsByType(ItemType.TROPHY);
        Array.ArrayIterator<BasicLevel> it = Game.i.basicLevelManager.levelsOrdered.iterator();
        while (it.hasNext()) {
            Array.ArrayIterator<BasicLevel.WaveQuest> it2 = it.next().waveQuests.iterator();
            while (it2.hasNext()) {
                BasicLevel.WaveQuest next = it2.next();
                if (next.isCompleted()) {
                    Array.ArrayIterator<ItemStack> it3 = next.prizes.iterator();
                    while (it3.hasNext()) {
                        ItemStack next2 = it3.next();
                        if (next2.getItem().getType() == ItemType.TROPHY) {
                            TrophyItem trophyItem = (TrophyItem) next2.getItem();
                            boolean z = false;
                            int i2 = 0;
                            while (true) {
                                if (i2 >= itemsByType.size) {
                                    break;
                                }
                                if (trophyItem.trophyType != ((TrophyItem) itemsByType.items[i2].getItem()).trophyType) {
                                    i2++;
                                } else {
                                    z = true;
                                    break;
                                }
                            }
                            if (!z) {
                                f2403a.i("restoring trophy for completed quest " + next + SequenceUtils.SPACE + trophyItem.trophyType, new Object[0]);
                                issuedItems.items.add(new ItemStack(Item.D.F_TROPHY.create(trophyItem.trophyType), 1));
                            }
                        }
                    }
                }
            }
        }
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        for (int i3 = 0; i3 < itemsByType.size; i3++) {
            TrophyItem trophyItem2 = (TrophyItem) itemsByType.items[i3].getItem();
            if (trophyItem2.trophyType == TrophyType.SPECIAL_DEVELOPER) {
                z2 = true;
            } else if (trophyItem2.trophyType == TrophyType.SPECIAL_MASTER) {
                z3 = true;
            } else if (trophyItem2.trophyType == TrophyType.SPECIAL_MILLION) {
                z4 = true;
            } else if (trophyItem2.trophyType == TrophyType.SPECIAL_STORYLINE) {
                z5 = true;
            }
        }
        if (!z2 && (isDeveloperModeEnabled() || Game.i.researchManager.canStartResearching(Game.i.researchManager.getInstance(ResearchType.DEVELOPER_MODE), true))) {
            issuedItems.items.add(new ItemStack(Item.D.F_TROPHY.create(TrophyType.SPECIAL_DEVELOPER), 1));
        }
        if (!z3) {
            boolean z6 = true;
            for (int i4 = 0; i4 < Game.i.basicLevelManager.stagesOrdered.size; i4++) {
                BasicLevelStage basicLevelStage = Game.i.basicLevelManager.stagesOrdered.items[i4];
                if (basicLevelStage.name.equals("1") || basicLevelStage.name.equals("2") || basicLevelStage.name.equals("3") || basicLevelStage.name.equals("4") || basicLevelStage.name.equals("5")) {
                    int i5 = 0;
                    while (true) {
                        if (i5 < basicLevelStage.levels.size) {
                            BasicLevel basicLevel = basicLevelStage.levels.items[i5];
                            if (i5 >= 8 || Game.i.basicLevelManager.isMastered(basicLevel)) {
                                i5++;
                            } else {
                                z6 = false;
                                break;
                            }
                        }
                    }
                }
            }
            if (z6) {
                issuedItems.items.add(new ItemStack(Item.D.F_TROPHY.create(TrophyType.SPECIAL_MASTER), 1));
            }
        }
        if (!z4 && Game.i.statisticsManager.getMaxOneGame(StatisticsType.SG) >= 1000000.0d) {
            issuedItems.items.add(new ItemStack(Item.D.F_TROPHY.create(TrophyType.SPECIAL_MILLION), 1));
        }
        if (!z5 && Game.i.basicLevelManager.getGainedStarsOnLevel(Game.i.basicLevelManager.getLevel("5.8")) >= 3) {
            issuedItems.items.add(new ItemStack(Item.D.F_TROPHY.create(TrophyType.SPECIAL_STORYLINE), 1));
        }
        if (issuedItems.items.size != 0) {
            addIssuedPrizes(issuedItems, true);
            Game.i.progressManager.addItemArray(issuedItems.items, "trophy");
        }
    }

    public InventoryStatistics getInventoryStatistics() {
        InventoryStatistics inventoryStatistics = new InventoryStatistics();
        inventoryStatistics.lootBoostTimeLeft = ProgressPrefs.i().progress.getLootBoostTimeLeft();
        inventoryStatistics.papersPerHourEstimate = Game.i.purchaseManager.getPapersHourBasePrice(1000000, 1.0f);
        Array<ItemStack> allItems = ProgressPrefs.i().inventory.getAllItems();
        for (int i2 = 0; i2 < allItems.size; i2++) {
            ItemStack itemStack = allItems.items[i2];
            inventoryStatistics.countItem(itemStack.getItem(), itemStack.getCount());
        }
        return inventoryStatistics;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/ProgressManager$ProgressManagerListener.class */
    public interface ProgressManagerListener {
        void itemsChanged(Item item, int i, int i2);

        void doubleGainEnabled();

        void developerConsoleEnabled();

        /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/ProgressManager$ProgressManagerListener$ProgressManagerListenerAdapter.class */
        public static abstract class ProgressManagerListenerAdapter implements ProgressManagerListener {
            @Override // com.prineside.tdi2.managers.ProgressManager.ProgressManagerListener
            public void itemsChanged(Item item, int i, int i2) {
            }

            @Override // com.prineside.tdi2.managers.ProgressManager.ProgressManagerListener
            public void doubleGainEnabled() {
            }

            @Override // com.prineside.tdi2.managers.ProgressManager.ProgressManagerListener
            public void developerConsoleEnabled() {
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/ProgressManager$_GameValueManagerListener.class */
    private class _GameValueManagerListener implements GameValueManager.GameValueManagerListener {

        /* renamed from: a, reason: collision with root package name */
        private boolean f2411a;

        private _GameValueManagerListener() {
            this.f2411a = false;
        }

        /* synthetic */ _GameValueManagerListener(ProgressManager progressManager, byte b2) {
            this();
        }

        @Override // com.prineside.tdi2.managers.GameValueManager.GameValueManagerListener
        public void gameValuesRecalculated() {
            if (Game.i.gameValueManager.getSnapshot().getBooleanValue(GameValueType.DEVELOPER_MODE)) {
                if (!this.f2411a) {
                    this.f2411a = true;
                    ProgressManager.this.j.begin();
                    int i = ProgressManager.this.j.size;
                    for (int i2 = 0; i2 < i; i2++) {
                        ((ProgressManagerListener) ProgressManager.this.j.get(i2)).developerConsoleEnabled();
                    }
                    ProgressManager.this.j.end();
                    ProgressManager.this.checkSpecialTrophiesGiven();
                    return;
                }
                return;
            }
            if (this.f2411a) {
                this.f2411a = false;
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/ProgressManager$CraftingQueueItem.class */
    public static class CraftingQueueItem {
        public ItemStack result;
        public Array<ItemStack> ingredients = new Array<>(ItemStack.class);
        public int count;
        public float duration;
        public float timePassed;

        public float getTimePassed() {
            return this.timePassed;
        }

        public float getTotalTime() {
            return this.duration * this.count;
        }

        public int getCraftedCount() {
            if (getTimeLeft() == 0.0f) {
                return this.count;
            }
            return (int) (this.timePassed / this.duration);
        }

        public float getTimeLeft() {
            float totalTime = getTotalTime();
            if (this.timePassed >= totalTime) {
                return 0.0f;
            }
            return totalTime - this.timePassed;
        }

        public static CraftingQueueItem fromJson(JsonValue jsonValue) {
            CraftingQueueItem craftingQueueItem = new CraftingQueueItem();
            craftingQueueItem.result = ItemStack.fromJson(jsonValue.get("result"));
            Iterator<JsonValue> iterator2 = jsonValue.get("ingredients").iterator2();
            while (iterator2.hasNext()) {
                craftingQueueItem.ingredients.add(ItemStack.fromJson(iterator2.next()));
            }
            craftingQueueItem.count = jsonValue.getInt("count");
            craftingQueueItem.duration = jsonValue.getInt("duration");
            craftingQueueItem.timePassed = jsonValue.getInt("timePassed");
            return craftingQueueItem;
        }

        public void toJson(Json json) {
            json.writeObjectStart("result");
            this.result.toJson(json);
            json.writeObjectEnd();
            json.writeArrayStart("ingredients");
            for (int i = 0; i < this.ingredients.size; i++) {
                json.writeObjectStart();
                this.ingredients.items[i].toJson(json);
                json.writeObjectEnd();
            }
            json.writeArrayEnd();
            json.writeValue("count", Integer.valueOf(this.count));
            json.writeValue("duration", Float.valueOf(this.duration));
            json.writeValue("timePassed", Float.valueOf(this.timePassed));
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/ProgressManager$VideoAdViewBonus.class */
    public static class VideoAdViewBonus {
        public int views;
        public ItemStack item;
        public boolean doubleGain;

        public VideoAdViewBonus(int i, ItemStack itemStack) {
            this.views = i;
            this.item = itemStack;
        }

        public VideoAdViewBonus(int i, ItemStack itemStack, boolean z) {
            this(i, itemStack);
            this.doubleGain = z;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/ProgressManager$ShopOffer.class */
    public static class ShopOffer {
        public ItemStack price;
        public ItemStack item;
        public int discountPercent;
        public boolean purchased;
        public float reducedDueToLargePrice;
        public float increasedDueToLowPrice;

        public ShopOffer() {
        }

        public ShopOffer(ItemStack itemStack, ItemStack itemStack2) {
            this.price = itemStack;
            this.item = itemStack2;
        }

        public ShopOffer(ItemStack itemStack, ItemStack itemStack2, int i) {
            this.price = itemStack;
            this.item = itemStack2;
            this.discountPercent = i;
        }

        public static ShopOffer fromJson(JsonValue jsonValue) {
            ShopOffer shopOffer = new ShopOffer();
            shopOffer.price = ItemStack.fromJson(jsonValue.get("price"));
            shopOffer.item = ItemStack.fromJson(jsonValue.get("item"));
            shopOffer.discountPercent = jsonValue.getInt("discountPercent", 0);
            shopOffer.purchased = jsonValue.getBoolean("purchased", false);
            return shopOffer;
        }

        public void toJson(Json json) {
            json.writeObjectStart("price");
            this.price.toJson(json);
            json.writeObjectEnd();
            json.writeObjectStart("item");
            this.item.toJson(json);
            json.writeObjectEnd();
            if (this.purchased) {
                json.writeValue("purchased", Boolean.TRUE);
            }
            if (this.discountPercent != 0) {
                json.writeValue("discountPercent", Integer.valueOf(this.discountPercent));
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/ProgressManager$InventoryStatistics.class */
    public static class InventoryStatistics implements KryoSerializable {
        public int[] byTileType = new int[TileType.values.length];
        public int[] byTileTypeCount = new int[TileType.values.length];
        public float lootBoostTimeLeft;
        public int rarityBoostsCount;
        public int papersPerHourEstimate;

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void write(Kryo kryo, Output output) {
            kryo.writeObject(output, this.byTileType);
            kryo.writeObject(output, this.byTileTypeCount);
            output.writeFloat(this.lootBoostTimeLeft);
            output.writeVarInt(this.rarityBoostsCount, true);
            output.writeVarInt(this.papersPerHourEstimate, true);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void read(Kryo kryo, Input input) {
            this.byTileType = (int[]) kryo.readObject(input, int[].class);
            this.byTileTypeCount = (int[]) kryo.readObject(input, int[].class);
            this.lootBoostTimeLeft = input.readFloat();
            this.rarityBoostsCount = input.readVarInt(true);
            this.papersPerHourEstimate = input.readVarInt(true);
        }

        public InventoryStatistics cpy() {
            InventoryStatistics inventoryStatistics = new InventoryStatistics();
            System.arraycopy(this.byTileType, 0, inventoryStatistics.byTileType, 0, this.byTileType.length);
            System.arraycopy(this.byTileTypeCount, 0, inventoryStatistics.byTileTypeCount, 0, this.byTileTypeCount.length);
            inventoryStatistics.lootBoostTimeLeft = this.lootBoostTimeLeft;
            inventoryStatistics.rarityBoostsCount = this.rarityBoostsCount;
            return inventoryStatistics;
        }

        public void countItem(Item item, int i) {
            if (i <= 0) {
                throw new IllegalArgumentException("Invalid count: " + i);
            }
            switch (item.getType()) {
                case TILE:
                    a((TileItem) item, i);
                    return;
                case RARITY_BOOST:
                    this.rarityBoostsCount += i;
                    return;
                case LOOT_BOOST:
                    this.lootBoostTimeLeft += 7200.0f * i;
                    return;
                default:
                    return;
            }
        }

        private void a(TileItem tileItem, int i) {
            int[] iArr = this.byTileType;
            int ordinal = tileItem.tile.type.ordinal();
            iArr[ordinal] = iArr[ordinal] + 1;
            int[] iArr2 = this.byTileTypeCount;
            int ordinal2 = tileItem.tile.type.ordinal();
            iArr2[ordinal2] = iArr2[ordinal2] + i;
        }

        public void reset() {
            Arrays.fill(this.byTileType, 0);
            Arrays.fill(this.byTileTypeCount, 0);
            this.lootBoostTimeLeft = 0.0f;
            this.rarityBoostsCount = 0;
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/ProgressManager$ProgressSnapshotForState.class */
    public static final class ProgressSnapshotForState implements KryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private IntIntMap f2409a = new IntIntMap();

        /* renamed from: b, reason: collision with root package name */
        private IntSet f2410b = new IntSet();
        private ObjectSet<String> c = new ObjectSet<>();
        public int statsPlayTimeCasesLoot;
        public int statsPlayRealTime;
        public int statsQueuedCasesGiven;

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public final void write(Kryo kryo, Output output) {
            kryo.writeObject(output, this.f2409a);
            kryo.writeObject(output, this.f2410b);
            kryo.writeObject(output, this.c);
            output.writeVarInt(this.statsPlayTimeCasesLoot, true);
            output.writeVarInt(this.statsPlayRealTime, true);
            output.writeVarInt(this.statsQueuedCasesGiven, true);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public final void read(Kryo kryo, Input input) {
            this.f2409a = (IntIntMap) kryo.readObject(input, IntIntMap.class);
            this.f2410b = (IntSet) kryo.readObject(input, IntSet.class);
            this.c = (ObjectSet) kryo.readObject(input, ObjectSet.class);
            this.statsPlayTimeCasesLoot = input.readVarInt(true);
            this.statsPlayRealTime = input.readVarInt(true);
            this.statsQueuedCasesGiven = input.readVarInt(true);
        }

        public final int getResearchCount() {
            return this.f2409a.size;
        }

        public final int getResearchLevelsCount() {
            int i = 0;
            Iterator<IntIntMap.Entry> it = this.f2409a.iterator();
            while (it.hasNext()) {
                i += it.next().value;
            }
            return i;
        }

        public final void validate() {
            int i = 0;
            Array<Research> instances = Game.i.researchManager.getInstances();
            for (int i2 = 0; i2 < instances.size; i2++) {
                Research research = instances.get(i2);
                if (research.priceInStars != 0 && this.f2409a.get(research.type.ordinal(), 0) != 0) {
                    i += research.priceInStars;
                }
            }
            if (i > Game.i.basicLevelManager.getMaxStarsCount()) {
                throw new IllegalStateException("Installed research exceeds max possible star count " + i + " / " + Game.i.basicLevelManager.getMaxStarsCount());
            }
            ObjectSet objectSet = new ObjectSet();
            boolean z = true;
            while (z) {
                z = false;
                for (int i3 = 0; i3 < instances.size; i3++) {
                    Research research2 = instances.items[i3];
                    if (research2.priceInStars > 0 && this.f2409a.get(research2.type.ordinal(), 0) > 0) {
                        objectSet.clear();
                        if (!ResearchManager.isLinkedToRoot(research2, this.f2409a, objectSet)) {
                            throw new IllegalStateException("Installed research " + research2.type + " is not linked to the root of the tree");
                        }
                    }
                }
            }
        }

        public final boolean sameAs(ProgressSnapshotForState progressSnapshotForState) {
            if (this.f2409a.size != progressSnapshotForState.f2409a.size || this.f2410b.size != progressSnapshotForState.f2410b.size || this.c.size != progressSnapshotForState.c.size || this.statsPlayTimeCasesLoot != progressSnapshotForState.statsPlayTimeCasesLoot || this.statsPlayRealTime != progressSnapshotForState.statsPlayRealTime || this.statsQueuedCasesGiven != progressSnapshotForState.statsQueuedCasesGiven) {
                return false;
            }
            Iterator<IntIntMap.Entry> it = this.f2409a.iterator();
            while (it.hasNext()) {
                IntIntMap.Entry next = it.next();
                if (this.f2409a.get(next.key, -1) != progressSnapshotForState.f2409a.get(next.key, -1)) {
                    return false;
                }
            }
            IntSet.IntSetIterator it2 = this.f2410b.iterator();
            while (it2.hasNext) {
                if (!progressSnapshotForState.f2410b.contains(it2.next())) {
                    return false;
                }
            }
            ObjectSet.ObjectSetIterator<String> it3 = this.c.iterator();
            while (it3.hasNext) {
                if (!progressSnapshotForState.c.contains(it3.next())) {
                    return false;
                }
            }
            return true;
        }

        public final int getResearchInstalledLevel(ResearchType researchType) {
            return this.f2409a.get(researchType.ordinal(), 0);
        }

        public final boolean isTrophyReceived(TrophyType trophyType) {
            return this.f2410b.contains(trophyType.ordinal());
        }

        public final boolean isQuestEverCompleted(String str) {
            return this.c.contains(str);
        }

        public final void fromBytes(byte[] bArr, int i, int i2) {
            this.f2409a.clear();
            this.f2410b.clear();
            this.c.clear();
            FixedInput fixedInput = new FixedInput(bArr, i, i2);
            fixedInput.readByte();
            int readVarInt = fixedInput.readVarInt(true);
            for (int i3 = 0; i3 < readVarInt; i3++) {
                this.f2409a.put(fixedInput.readVarInt(true), fixedInput.readByte());
            }
            int readVarInt2 = fixedInput.readVarInt(true);
            for (int i4 = 0; i4 < readVarInt2; i4++) {
                this.f2410b.add(fixedInput.readVarInt(true));
            }
            int readVarInt3 = fixedInput.readVarInt(true);
            for (int i5 = 0; i5 < readVarInt3; i5++) {
                this.c.add(fixedInput.readString());
            }
            this.statsPlayTimeCasesLoot = fixedInput.readVarInt(true);
            this.statsPlayRealTime = fixedInput.readVarInt(true);
            this.statsQueuedCasesGiven = fixedInput.readVarInt(true);
        }

        public final byte[] toBytes() {
            FixedOutput fixedOutput = new FixedOutput(256, -1);
            fixedOutput.writeByte(1);
            fixedOutput.writeVarInt(this.f2409a.size, true);
            Iterator<IntIntMap.Entry> it = this.f2409a.iterator();
            while (it.hasNext()) {
                IntIntMap.Entry next = it.next();
                fixedOutput.writeVarInt(next.key, true);
                fixedOutput.writeByte(next.value);
            }
            fixedOutput.writeVarInt(this.f2410b.size, true);
            IntSet.IntSetIterator it2 = this.f2410b.iterator();
            while (it2.hasNext) {
                fixedOutput.writeVarInt(it2.next(), true);
            }
            fixedOutput.writeVarInt(this.c.size, true);
            ObjectSet.ObjectSetIterator<String> it3 = this.c.iterator();
            while (it3.hasNext) {
                fixedOutput.writeString(it3.next());
            }
            fixedOutput.writeVarInt(this.statsPlayTimeCasesLoot, true);
            fixedOutput.writeVarInt(this.statsPlayRealTime, true);
            fixedOutput.writeVarInt(this.statsQueuedCasesGiven, true);
            return fixedOutput.toBytes();
        }
    }
}
