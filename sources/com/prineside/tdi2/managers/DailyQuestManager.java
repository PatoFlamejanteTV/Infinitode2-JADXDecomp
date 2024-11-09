package com.prineside.tdi2.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.net.HttpParametersUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.StringBuilder;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.BasicLevel;
import com.prineside.tdi2.BasicLevelQuestConfig;
import com.prineside.tdi2.BasicLevelStage;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.IssuedItems;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.Manager;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.managers.LeaderBoardManager;
import com.prineside.tdi2.managers.preferences.categories.ProgressPrefs;
import com.prineside.tdi2.managers.preferences.categories.progress.PP_DailyQuest;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.ui.shared.Notifications;
import com.prineside.tdi2.utils.FastRandom;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.ObjectConsumer;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.html.Attribute;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/DailyQuestManager.class */
public class DailyQuestManager extends Manager.ManagerAdapter {
    public static final String LEVEL_NAME_PREFIX = "DQ";
    public static final String RESET_QUESTS_QUEST_ID = "_resetQuests";

    /* renamed from: b, reason: collision with root package name */
    private DailyQuestLevel f2319b;
    private static final SimpleDateFormat e;
    public int dailyLootMinBonusPerMonth;
    public int dailyLootMaxBonusPerMonth;
    private static final StringBuilder g;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2318a = TLog.forClass(DailyQuestManager.class);
    private static final Calendar d = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
    private final Date c = new Date();
    private final DelayedRemovalArray<DailyQuestLeaderboards> f = new DelayedRemovalArray<>(DailyQuestLeaderboards.class);
    public Array<DailyLootDayConfig> dailyLootDayConfigs = new Array<>(DailyLootDayConfig.class);

    static {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        e = simpleDateFormat;
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        g = new StringBuilder();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/DailyQuestManager$Serializer.class */
    public static class Serializer extends SingletonSerializer<DailyQuestManager> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public DailyQuestManager read() {
            return Game.i.dailyQuestManager;
        }
    }

    public int getDailyLootCurrentDayIndex() {
        d();
        return ProgressPrefs.i().dailyQuest.getDailyLootCurrentDayIndex();
    }

    public int getDailyLootCurrentMonthIndex() {
        return ProgressPrefs.i().dailyQuest.getDailyLootCurrentDayIndex() / this.dailyLootDayConfigs.size;
    }

    private void d() {
        String currentDayDate = getCurrentDayDate();
        boolean z = false;
        PP_DailyQuest pP_DailyQuest = ProgressPrefs.i().dailyQuest;
        if (pP_DailyQuest.getDailyLootCurrentDay() != null) {
            if (!pP_DailyQuest.getDailyLootCurrentDay().equals(currentDayDate)) {
                if (pP_DailyQuest.getDailyLootLastCompletedDay() != null && pP_DailyQuest.getDailyLootLastCompletedDay().equals(pP_DailyQuest.getDailyLootCurrentDay())) {
                    pP_DailyQuest.setDailyLootCurrentDayIndex(pP_DailyQuest.getDailyLootCurrentDayIndex() + 1);
                    ProgressPrefs.i().requireSave();
                }
            }
            if (!z || pP_DailyQuest.getDailyLootCurrentQuest() == null) {
                pP_DailyQuest.setDailyLootCurrentDay(currentDayDate);
                pP_DailyQuest.setDailyLootCurrentQuest(e());
                ProgressPrefs.i().requireSave();
            }
            return;
        }
        z = true;
        if (!z) {
        }
        pP_DailyQuest.setDailyLootCurrentDay(currentDayDate);
        pP_DailyQuest.setDailyLootCurrentQuest(e());
        ProgressPrefs.i().requireSave();
    }

    public String getDailyLootCurrentQuest() {
        d();
        PP_DailyQuest pP_DailyQuest = ProgressPrefs.i().dailyQuest;
        if (pP_DailyQuest.getDailyLootCurrentQuest() == null) {
            pP_DailyQuest.setDailyLootCurrentQuest(e());
            ProgressPrefs.i().requireSave();
            Preconditions.checkNotNull(pP_DailyQuest.getDailyLootCurrentQuest(), "getDailyLootRandomQuest() returned null");
        }
        return pP_DailyQuest.getDailyLootCurrentQuest();
    }

    @Deprecated
    public int getDailyLootDaysInRow() {
        d();
        return ProgressPrefs.i().dailyQuest.getDailyLootDaysInRow();
    }

    public int getSecondsTillNextDailyLoot() {
        d();
        long currentTimeMillis = System.currentTimeMillis();
        d.setTimeInMillis(currentTimeMillis);
        d.set(11, 0);
        d.set(12, 0);
        d.set(13, 0);
        d.set(14, 0);
        return 86400 - ((int) ((currentTimeMillis - d.getTimeInMillis()) / 1000));
    }

    public boolean isTodayDailyLootQuestCompleted() {
        d();
        return getCurrentDayDate().equals(ProgressPrefs.i().dailyQuest.getDailyLootLastCompletedDay());
    }

    public IssuedItems setDailyLootQuestCompleted() {
        String currentDayDate = getCurrentDayDate();
        PP_DailyQuest pP_DailyQuest = ProgressPrefs.i().dailyQuest;
        if (pP_DailyQuest.getDailyLootLastCompletedDay() != null && pP_DailyQuest.getDailyLootLastCompletedDay().equals(currentDayDate)) {
            return null;
        }
        pP_DailyQuest.setDailyLootLastCompletedDay(currentDayDate);
        ProgressPrefs.i().requireSave();
        DailyLootDayConfig dailyLootDayConfig = this.dailyLootDayConfigs.items[pP_DailyQuest.getDailyLootCurrentDayIndex() % this.dailyLootDayConfigs.size];
        int count = dailyLootDayConfig.getCount(pP_DailyQuest.getDailyLootCurrentDayIndex() / this.dailyLootDayConfigs.size);
        IssuedItems issuedItems = new IssuedItems(IssuedItems.IssueReason.DAILY_LOOT, Game.getTimestampSeconds());
        issuedItems.dailyLootDate = currentDayDate;
        issuedItems.items.add(new ItemStack(dailyLootDayConfig.item, count));
        Game.i.progressManager.addIssuedPrizes(issuedItems, true);
        Game.i.progressManager.addItemArray(issuedItems.items, "daily_loot");
        return issuedItems;
    }

    private static String e() {
        Array array = new Array();
        for (int i = 0; i < Game.i.basicLevelManager.stagesOrdered.size; i++) {
            BasicLevelStage basicLevelStage = Game.i.basicLevelManager.stagesOrdered.items[i];
            if (basicLevelStage.regular) {
                for (int i2 = 0; i2 < basicLevelStage.levels.size; i2++) {
                    BasicLevel basicLevel = basicLevelStage.levels.items[i2];
                    if (!basicLevel.fixedQuests && Game.i.basicLevelManager.isOpened(basicLevel) && Game.i.basicLevelManager.isLevelVisible(basicLevel)) {
                        int i3 = 0;
                        while (true) {
                            if (i3 >= basicLevel.quests.size) {
                                break;
                            }
                            if (basicLevel.quests.items[i3].isCompleted()) {
                                i3++;
                            } else {
                                array.add(basicLevel.quests.items[i3].id);
                                break;
                            }
                        }
                    }
                }
            }
        }
        if (array.size == 0) {
            for (int i4 = 0; i4 < Game.i.basicLevelManager.stagesOrdered.size; i4++) {
                BasicLevelStage basicLevelStage2 = Game.i.basicLevelManager.stagesOrdered.items[i4];
                if (basicLevelStage2.regular) {
                    for (int i5 = 0; i5 < basicLevelStage2.levels.size; i5++) {
                        BasicLevel basicLevel2 = basicLevelStage2.levels.items[i5];
                        for (int i6 = 0; i6 < basicLevel2.quests.size; i6++) {
                            if (!basicLevel2.quests.items[i6].isCompleted()) {
                                return basicLevel2.quests.items[i6].id;
                            }
                        }
                    }
                }
            }
            return RESET_QUESTS_QUEST_ID;
        }
        if (array.size > 3) {
            return (String) array.get(FastRandom.getFairInt((array.size / 2) - 1));
        }
        return (String) array.random();
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void setup() {
        JsonValue parse = new JsonReader().parse(Gdx.files.internal("res/daily-loot.json"));
        this.dailyLootMinBonusPerMonth = parse.getInt("minBonusPerMonth");
        this.dailyLootMaxBonusPerMonth = parse.getInt("maxBonusPerMonth");
        this.dailyLootDayConfigs.clear();
        Iterator<JsonValue> iterator2 = parse.get("days").iterator2();
        while (iterator2.hasNext()) {
            this.dailyLootDayConfigs.add(DailyLootDayConfig.fromJson(iterator2.next()));
        }
    }

    private DailyQuestLevel f() {
        int parseInt;
        String currentDayDate = getCurrentDayDate();
        PP_DailyQuest pP_DailyQuest = ProgressPrefs.i().dailyQuest;
        if (pP_DailyQuest.getLastLoadedQuestDate() != null && pP_DailyQuest.getLastLoadedQuestDate().equals(currentDayDate)) {
            parseInt = pP_DailyQuest.getLastLoadedQuestId();
        } else {
            Array array = new Array();
            Array.ArrayIterator<BasicLevel> it = Game.i.basicLevelManager.levelsOrdered.iterator();
            while (it.hasNext()) {
                BasicLevel next = it.next();
                if (next.dailyQuest) {
                    array.add(next.name);
                }
            }
            String str = (String) array.random();
            parseInt = Integer.parseInt(str.substring(2));
            if (pP_DailyQuest.getLastLoadedQuestDate() != null && pP_DailyQuest.getLastLoadedQuestId() == parseInt && array.size > 1) {
                String str2 = (String) array.random();
                parseInt = Integer.parseInt(str2.substring(2));
                f2318a.i("quest " + str + " was selected for the last time, fallback to " + str2, new Object[0]);
            }
            f2318a.i("selected quest " + parseInt + " out of " + array.size, new Object[0]);
        }
        DailyQuestLevel dailyQuestLevel = new DailyQuestLevel();
        dailyQuestLevel.setForDate(currentDayDate);
        dailyQuestLevel.questId = parseInt;
        try {
            dailyQuestLevel.endTimestamp = (int) (e.parse(getNextDayDate()).getTime() / 1000);
        } catch (ParseException unused) {
        }
        dailyQuestLevel.f2327a = LEVEL_NAME_PREFIX + parseInt;
        dailyQuestLevel.isLocalFallback = true;
        pP_DailyQuest.setLastLoadedQuestDate(currentDayDate);
        pP_DailyQuest.setLastLoadedQuestId(parseInt);
        ProgressPrefs.i().requireSave();
        return dailyQuestLevel;
    }

    public String getCurrentDayDate() {
        this.c.setTime(Game.getTimestampMillis());
        return e.format(this.c);
    }

    public String getNextDayDate() {
        this.c.setTime(Game.getTimestampMillis() + 86400000);
        return e.format(this.c);
    }

    public DailyQuestLevel getDailyQuestLevelCache() {
        String currentDayDate = getCurrentDayDate();
        if (this.f2319b != null && !this.f2319b.getForDate().equals(currentDayDate)) {
            this.f2319b = null;
        }
        if (this.f2319b != null && !this.f2319b.isLocalFallback) {
            return this.f2319b;
        }
        this.f2319b = f();
        return this.f2319b;
    }

    public void getDailyQuestHashFromServer(int i, final ObjectConsumer<String> objectConsumer) {
        Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
        httpRequest.setUrl(Config.GET_DAILY_QUEST_HASH_URL);
        HashMap hashMap = new HashMap();
        hashMap.put(Attribute.ID_ATTR, new StringBuilder().append(i).toString());
        httpRequest.setContent(HttpParametersUtils.convertHttpParameters(hashMap));
        Gdx.f881net.sendHttpRequest(httpRequest, new Net.HttpResponseListener(this) { // from class: com.prineside.tdi2.managers.DailyQuestManager.1
            @Override // com.badlogic.gdx.Net.HttpResponseListener
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                String resultAsString = httpResponse.getResultAsString();
                try {
                    JsonValue parse = new JsonReader().parse(resultAsString);
                    if (!parse.getString("status").equals("success")) {
                        DailyQuestManager.f2318a.e("failed to get daily quest hash: " + resultAsString, new Object[0]);
                        Threads i2 = Threads.i();
                        ObjectConsumer objectConsumer2 = objectConsumer;
                        i2.runOnMainThread(() -> {
                            objectConsumer2.accept(null);
                        });
                        return;
                    }
                    String string = parse.getString("hash");
                    Threads i3 = Threads.i();
                    ObjectConsumer objectConsumer3 = objectConsumer;
                    i3.runOnMainThread(() -> {
                        objectConsumer3.accept(string);
                    });
                } catch (Exception e2) {
                    DailyQuestManager.f2318a.e("failed to get daily quest hash: " + resultAsString, e2);
                    Threads i4 = Threads.i();
                    ObjectConsumer objectConsumer4 = objectConsumer;
                    i4.runOnMainThread(() -> {
                        objectConsumer4.accept(null);
                    });
                }
            }

            @Override // com.badlogic.gdx.Net.HttpResponseListener
            public void failed(Throwable th) {
                DailyQuestManager.f2318a.e("Error while getting daily quest hash", th);
                Threads i2 = Threads.i();
                ObjectConsumer objectConsumer2 = objectConsumer;
                i2.runOnMainThread(() -> {
                    objectConsumer2.accept(null);
                });
            }

            @Override // com.badlogic.gdx.Net.HttpResponseListener
            public void cancelled() {
                DailyQuestManager.f2318a.e("Timeout while getting daily quest", new Object[0]);
                Threads i2 = Threads.i();
                ObjectConsumer objectConsumer2 = objectConsumer;
                i2.runOnMainThread(() -> {
                    objectConsumer2.accept(null);
                });
            }
        });
    }

    public void getCurrentDayLevel(final ObjectConsumer<DailyQuestLevel> objectConsumer) {
        String currentDayDate = getCurrentDayDate();
        if (this.f2319b != null && !this.f2319b.getForDate().equals(currentDayDate)) {
            this.f2319b = null;
        }
        if (this.f2319b != null && !this.f2319b.isLocalFallback) {
            objectConsumer.accept(this.f2319b);
            return;
        }
        f2318a.i("requesting from server", new Object[0]);
        this.f2319b = f();
        try {
            Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
            httpRequest.setUrl(Config.GET_DAILY_QUEST_INFO_URL);
            Gdx.f881net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() { // from class: com.prineside.tdi2.managers.DailyQuestManager.2
                @Override // com.badlogic.gdx.Net.HttpResponseListener
                public void handleHttpResponse(Net.HttpResponse httpResponse) {
                    try {
                        String resultAsString = httpResponse.getResultAsString();
                        JsonValue parse = new JsonReader().parse(resultAsString);
                        if (!parse.getString("status").equals("success")) {
                            DailyQuestManager.f2318a.e("can't retrieve daily quest: " + resultAsString, new Object[0]);
                            Threads i = Threads.i();
                            ObjectConsumer objectConsumer2 = objectConsumer;
                            i.runOnMainThread(() -> {
                                objectConsumer2.accept(DailyQuestManager.this.f2319b);
                            });
                            return;
                        }
                        Threads i2 = Threads.i();
                        ObjectConsumer objectConsumer3 = objectConsumer;
                        i2.runOnMainThread(() -> {
                            try {
                                JsonValue jsonValue = parse.get("data");
                                if (jsonValue.getInt("min_build") > 208) {
                                    Notifications.i().add(Game.i.localeManager.i18n.get("game_needs_update_for_daily_quests"), null, MaterialColor.ORANGE.P800, null);
                                    objectConsumer3.accept(DailyQuestManager.this.f2319b);
                                    return;
                                }
                                DailyQuestLevel dailyQuestLevel = new DailyQuestLevel();
                                dailyQuestLevel.setForDate(jsonValue.getString("date"));
                                dailyQuestLevel.endTimestamp = jsonValue.getInt("end_timestamp");
                                if (Game.getTimestampSeconds() - 60 > dailyQuestLevel.endTimestamp || Game.getTimestampSeconds() + 60 < dailyQuestLevel.endTimestamp - 86400) {
                                    Notifications.i().add("Error: incorrect device time", null, MaterialColor.ORANGE.P800, null);
                                    objectConsumer3.accept(DailyQuestManager.this.f2319b);
                                    return;
                                }
                                Iterator<JsonValue> iterator2 = jsonValue.get("prize_first_place").iterator2();
                                while (iterator2.hasNext()) {
                                    dailyQuestLevel.prizesFirstPlace.add(ItemStack.fromJson(iterator2.next()));
                                }
                                Iterator<JsonValue> iterator22 = jsonValue.get("prize_second_place").iterator2();
                                while (iterator22.hasNext()) {
                                    dailyQuestLevel.prizesSecondPlace.add(ItemStack.fromJson(iterator22.next()));
                                }
                                Iterator<JsonValue> iterator23 = jsonValue.get("prize_third_place").iterator2();
                                while (iterator23.hasNext()) {
                                    dailyQuestLevel.prizesThirdPlace.add(ItemStack.fromJson(iterator23.next()));
                                }
                                Iterator<JsonValue> iterator24 = jsonValue.get("prize_top_3_percent").iterator2();
                                while (iterator24.hasNext()) {
                                    dailyQuestLevel.prizesTop3Percent.add(ItemStack.fromJson(iterator24.next()));
                                }
                                Iterator<JsonValue> iterator25 = jsonValue.get("prize_top_10_percent").iterator2();
                                while (iterator25.hasNext()) {
                                    dailyQuestLevel.prizesTop10Percent.add(ItemStack.fromJson(iterator25.next()));
                                }
                                Iterator<JsonValue> iterator26 = jsonValue.get("prize_top_30_percent").iterator2();
                                while (iterator26.hasNext()) {
                                    dailyQuestLevel.prizesTop30Percent.add(ItemStack.fromJson(iterator26.next()));
                                }
                                int i3 = jsonValue.getInt("daily_quest");
                                dailyQuestLevel.questId = i3;
                                if (!jsonValue.getString("data_hash").equals(DailyQuestManager.this.getDailyQuestHash(i3))) {
                                    DailyQuestManager.f2318a.i("loaded DQ " + i3 + " hash differ, loading from server", new Object[0]);
                                    DailyQuestManager.this.loadAndStoreDailyQuestFromServer(i3, basicLevel -> {
                                        if (basicLevel == null) {
                                            objectConsumer3.accept(DailyQuestManager.this.f2319b);
                                            return;
                                        }
                                        dailyQuestLevel.f2327a = basicLevel.name;
                                        DailyQuestManager.this.f2319b = dailyQuestLevel;
                                        PP_DailyQuest pP_DailyQuest = ProgressPrefs.i().dailyQuest;
                                        pP_DailyQuest.setLastLoadedQuestDate(dailyQuestLevel.getForDate());
                                        pP_DailyQuest.setLastLoadedQuestId(i3);
                                        ProgressPrefs.i().requireSave();
                                        objectConsumer3.accept(dailyQuestLevel);
                                    });
                                } else {
                                    dailyQuestLevel.f2327a = Game.i.basicLevelManager.getLevel(DailyQuestManager.LEVEL_NAME_PREFIX + i3).name;
                                    DailyQuestManager.this.f2319b = dailyQuestLevel;
                                    objectConsumer3.accept(dailyQuestLevel);
                                }
                            } catch (Exception e2) {
                                DailyQuestManager.f2318a.e("failed to parse daily quest map", e2);
                                objectConsumer3.accept(DailyQuestManager.this.f2319b);
                            }
                        });
                    } catch (Exception e2) {
                        DailyQuestManager.f2318a.e("Failed to parse response", e2);
                        Threads i3 = Threads.i();
                        ObjectConsumer objectConsumer4 = objectConsumer;
                        i3.runOnMainThread(() -> {
                            objectConsumer4.accept(DailyQuestManager.this.f2319b);
                        });
                    }
                }

                @Override // com.badlogic.gdx.Net.HttpResponseListener
                public void failed(Throwable th) {
                    DailyQuestManager.f2318a.e("Error while getting daily quest", th);
                    Threads i = Threads.i();
                    ObjectConsumer objectConsumer2 = objectConsumer;
                    i.runOnMainThread(() -> {
                        objectConsumer2.accept(DailyQuestManager.this.f2319b);
                    });
                }

                @Override // com.badlogic.gdx.Net.HttpResponseListener
                public void cancelled() {
                    DailyQuestManager.f2318a.e("Timeout while getting daily quest", new Object[0]);
                    Threads i = Threads.i();
                    ObjectConsumer objectConsumer2 = objectConsumer;
                    i.runOnMainThread(() -> {
                        objectConsumer2.accept(DailyQuestManager.this.f2319b);
                    });
                }
            });
        } catch (Exception e2) {
            f2318a.e("Failed to get daily quest", e2);
            Threads.i().runOnMainThread(() -> {
                objectConsumer.accept(this.f2319b);
            });
        }
    }

    public String getDailyQuestHash(int i) {
        FileHandle local = Gdx.files.local("levels/DQ" + i + ".json");
        FileHandle fileHandle = local;
        if (!local.exists()) {
            fileHandle = Gdx.files.internal("levels/DQ" + i + ".json");
        }
        if (fileHandle.exists()) {
            String str = "";
            FileHandle local2 = Gdx.files.local("levels/maps/DQ" + i + ".json");
            if (local2.exists()) {
                str = local2.readString("UTF-8");
            }
            return StringFormatter.md5Hash("{\"configuration\":" + fileHandle.readString("UTF-8") + ",\"map\":" + str + "}");
        }
        return null;
    }

    public void removeLoadedDailyQuestMapIfMd5HashDiffers(int i, String str) {
        FileHandle local = Gdx.files.local("levels/DQ" + i + ".json");
        if (local.exists()) {
            String str2 = "";
            FileHandle local2 = Gdx.files.local("levels/maps/DQ" + i + ".json");
            if (local2.exists()) {
                str2 = local2.readString("UTF-8");
            }
            if (!StringFormatter.md5Hash("{\"configuration\":" + local.readString("UTF-8") + ",\"map\":" + str2 + "}").equals(str)) {
                f2318a.i("hash mismatch for quest level " + i + " on disk, removing", new Object[0]);
                local.delete();
                if (local2.exists()) {
                    local2.delete();
                }
                Game.i.basicLevelManager.unloadLevel(LEVEL_NAME_PREFIX + i);
                return;
            }
            f2318a.i("hash match for quest level " + i + " on disk", new Object[0]);
            return;
        }
        f2318a.i("local DQ config not exists", new Object[0]);
    }

    public void loadAndStoreDailyQuestFromServer(final int i, final ObjectConsumer<BasicLevel> objectConsumer) {
        Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
        httpRequest.setUrl(Config.GET_DAILY_QUEST_MAP_URL);
        HashMap hashMap = new HashMap();
        hashMap.put(Attribute.ID_ATTR, String.valueOf(i));
        httpRequest.setContent(HttpParametersUtils.convertHttpParameters(hashMap));
        Gdx.f881net.sendHttpRequest(httpRequest, new Net.HttpResponseListener(this) { // from class: com.prineside.tdi2.managers.DailyQuestManager.3
            @Override // com.badlogic.gdx.Net.HttpResponseListener
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                try {
                    String resultAsString = httpResponse.getResultAsString();
                    JsonValue parse = new JsonReader().parse(resultAsString);
                    if (!parse.getString("status").equals("success")) {
                        DailyQuestManager.f2318a.e("failed to get daily quest map: " + resultAsString, new Object[0]);
                        Threads i2 = Threads.i();
                        ObjectConsumer objectConsumer2 = objectConsumer;
                        i2.runOnMainThread(() -> {
                            objectConsumer2.accept(null);
                        });
                        return;
                    }
                    Threads i3 = Threads.i();
                    ObjectConsumer objectConsumer3 = objectConsumer;
                    int i4 = i;
                    i3.runOnMainThread(() -> {
                        long realTickCount = Game.getRealTickCount();
                        try {
                            JsonValue jsonValue = parse.get("data");
                            String string = jsonValue.get("configuration").getString(Attribute.NAME_ATTR);
                            Gdx.files.local("levels/" + string + ".json").writeString(jsonValue.get("configuration").toJson(JsonWriter.OutputType.json), false, "UTF-8");
                            Gdx.files.local("levels/maps/" + string + ".json").writeString(jsonValue.get("map").toJson(JsonWriter.OutputType.json), false, "UTF-8");
                            Game.i.basicLevelManager.loadAndRegisterLevelFromJson(jsonValue.get("configuration"));
                            objectConsumer3.accept(Game.i.basicLevelManager.getLevel(DailyQuestManager.LEVEL_NAME_PREFIX + i4));
                        } catch (Exception e2) {
                            DailyQuestManager.f2318a.e("failed to store daily quest map", e2);
                            objectConsumer3.accept(null);
                        }
                        if (Game.i.debugManager != null) {
                            Game.i.debugManager.registerFrameJob("DailyQuestManager-storeDQ", Game.getRealTickCount() - realTickCount);
                        }
                    });
                } catch (Exception e2) {
                    DailyQuestManager.f2318a.e("failed to parse daily quest map", e2);
                    Threads i5 = Threads.i();
                    ObjectConsumer objectConsumer4 = objectConsumer;
                    i5.runOnMainThread(() -> {
                        objectConsumer4.accept(null);
                    });
                }
            }

            @Override // com.badlogic.gdx.Net.HttpResponseListener
            public void failed(Throwable th) {
                DailyQuestManager.f2318a.e("failed to get daily quest map", th);
                Threads i2 = Threads.i();
                ObjectConsumer objectConsumer2 = objectConsumer;
                i2.runOnMainThread(() -> {
                    objectConsumer2.accept(null);
                });
            }

            @Override // com.badlogic.gdx.Net.HttpResponseListener
            public void cancelled() {
                DailyQuestManager.f2318a.e("failed (cancelled) to get daily quest map", new Object[0]);
                Threads i2 = Threads.i();
                ObjectConsumer objectConsumer2 = objectConsumer;
                i2.runOnMainThread(() -> {
                    objectConsumer2.accept(null);
                });
            }
        });
    }

    public void setLastCompletedDailyQuestTimestamp(int i) {
        ProgressPrefs.i().dailyQuest.setLastCompletedDailyQuestTimestamp(i);
        ProgressPrefs.i().requireSave();
    }

    public void startDailyLevel() {
        getCurrentDayLevel(this::a);
    }

    private void a(DailyQuestLevel dailyQuestLevel) {
        if (!dailyQuestLevel.wasCompleted()) {
            BasicLevel level = Game.i.basicLevelManager.getLevel(dailyQuestLevel.getLevelName());
            for (int i = 0; i < level.quests.size; i++) {
                BasicLevelQuestConfig basicLevelQuestConfig = level.quests.get(i);
                if (basicLevelQuestConfig.isCompleted()) {
                    basicLevelQuestConfig.setCompleted(false);
                }
            }
            for (int i2 = 0; i2 < level.waveQuests.size; i2++) {
                BasicLevel.WaveQuest waveQuest = level.waveQuests.get(i2);
                if (waveQuest.isCompleted()) {
                    waveQuest.setCompleted(false);
                }
            }
        }
        Game.i.screenManager.startNewDailyLevel(dailyQuestLevel);
    }

    public void getLeaderboards(final String str, final ObjectConsumer<DailyQuestLeaderboards> objectConsumer) {
        final DailyQuestLeaderboards dailyQuestLeaderboards = new DailyQuestLeaderboards(false, str, Game.i.authManager.getPlayerId());
        synchronized (this.f) {
            this.f.begin();
            for (int i = 0; i < this.f.size; i++) {
                DailyQuestLeaderboards dailyQuestLeaderboards2 = this.f.items[i];
                if (Game.getTimestampSeconds() - dailyQuestLeaderboards2.requestTimestamp > 30) {
                    this.f.removeIndex(i);
                } else if (dailyQuestLeaderboards2.date.equals(str) && ((!Game.i.authManager.isSignedIn() && dailyQuestLeaderboards2.playerId == null) || (Game.i.authManager.isSignedIn() && dailyQuestLeaderboards2.playerId != null && dailyQuestLeaderboards2.playerId.equals(Game.i.authManager.getPlayerId())))) {
                    objectConsumer.accept(dailyQuestLeaderboards2);
                    return;
                }
            }
            this.f.end();
            try {
                Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
                httpRequest.setUrl(Config.GET_DAILY_QUEST_LEADERBOARDS_URL);
                HashMap hashMap = new HashMap();
                hashMap.put("date", str);
                String str2 = null;
                if (Game.i.authManager.isSignedIn()) {
                    String playerId = Game.i.authManager.getPlayerId();
                    str2 = playerId;
                    if (playerId != null) {
                        hashMap.put("playerid", str2);
                    }
                }
                final String str3 = str2;
                httpRequest.setContent(HttpParametersUtils.convertHttpParameters(hashMap));
                Gdx.f881net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() { // from class: com.prineside.tdi2.managers.DailyQuestManager.4
                    @Override // com.badlogic.gdx.Net.HttpResponseListener
                    public void handleHttpResponse(Net.HttpResponse httpResponse) {
                        try {
                            String resultAsString = httpResponse.getResultAsString();
                            JsonValue parse = new JsonReader().parse(resultAsString);
                            if (!parse.getString("status").equals("success")) {
                                DailyQuestManager.f2318a.e("can't retrieve leaderboards: " + resultAsString, new Object[0]);
                                Threads i2 = Threads.i();
                                ObjectConsumer objectConsumer2 = objectConsumer;
                                DailyQuestLeaderboards dailyQuestLeaderboards3 = dailyQuestLeaderboards;
                                i2.runOnMainThread(() -> {
                                    objectConsumer2.accept(dailyQuestLeaderboards3);
                                });
                                return;
                            }
                            DailyQuestLeaderboards dailyQuestLeaderboards4 = new DailyQuestLeaderboards(true, str, str3);
                            if (parse.has("player")) {
                                JsonValue jsonValue = parse.get("player");
                                dailyQuestLeaderboards4.player = new LeaderboardsRank(true, false, str, str3, (byte) 0);
                                dailyQuestLeaderboards4.player.total = jsonValue.getInt("total");
                                dailyQuestLeaderboards4.player.rank = jsonValue.getInt("rank");
                                dailyQuestLeaderboards4.player.score = jsonValue.getLong("score");
                            }
                            int i3 = 1;
                            Iterator<JsonValue> iterator2 = parse.get("leaderboards").iterator2();
                            while (iterator2.hasNext()) {
                                JsonValue next = iterator2.next();
                                int i4 = i3;
                                i3++;
                                dailyQuestLeaderboards4.entries.add(new LeaderBoardManager.LeaderboardsEntry(next.getString("playerid"), next.getString("nickname"), next.getLong("score"), i4, next.getBoolean("hasPfp", false), next.getInt("level", 1)));
                            }
                            synchronized (DailyQuestManager.this.f) {
                                DailyQuestManager.this.f.add(dailyQuestLeaderboards4);
                            }
                            DailyQuestManager.f2318a.i("lb cache - stored " + dailyQuestLeaderboards4.date + SequenceUtils.SPACE + dailyQuestLeaderboards4.playerId + SequenceUtils.SPACE, new Object[0]);
                            Threads i5 = Threads.i();
                            ObjectConsumer objectConsumer3 = objectConsumer;
                            i5.runOnMainThread(() -> {
                                objectConsumer3.accept(dailyQuestLeaderboards4);
                            });
                        } catch (Exception e2) {
                            DailyQuestManager.f2318a.e("Failed to parse response", e2);
                            Threads i6 = Threads.i();
                            ObjectConsumer objectConsumer4 = objectConsumer;
                            DailyQuestLeaderboards dailyQuestLeaderboards5 = dailyQuestLeaderboards;
                            i6.runOnMainThread(() -> {
                                objectConsumer4.accept(dailyQuestLeaderboards5);
                            });
                        }
                    }

                    @Override // com.badlogic.gdx.Net.HttpResponseListener
                    public void failed(Throwable th) {
                        DailyQuestManager.f2318a.e("Error while getting leaderboards", th);
                        Threads i2 = Threads.i();
                        ObjectConsumer objectConsumer2 = objectConsumer;
                        DailyQuestLeaderboards dailyQuestLeaderboards3 = dailyQuestLeaderboards;
                        i2.runOnMainThread(() -> {
                            objectConsumer2.accept(dailyQuestLeaderboards3);
                        });
                    }

                    @Override // com.badlogic.gdx.Net.HttpResponseListener
                    public void cancelled() {
                        DailyQuestManager.f2318a.e("Timeout while getting leaderboards", new Object[0]);
                        Threads i2 = Threads.i();
                        ObjectConsumer objectConsumer2 = objectConsumer;
                        DailyQuestLeaderboards dailyQuestLeaderboards3 = dailyQuestLeaderboards;
                        i2.runOnMainThread(() -> {
                            objectConsumer2.accept(dailyQuestLeaderboards3);
                        });
                    }
                });
            } catch (Exception e2) {
                f2318a.e("Failed to get leaderboards", e2);
                objectConsumer.accept(dailyQuestLeaderboards);
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/DailyQuestManager$DailyQuestLevel.class */
    public static class DailyQuestLevel implements KryoSerializable {
        public boolean isLocalFallback;
        public int questId;

        /* renamed from: a, reason: collision with root package name */
        private String f2327a;
        public String forDate;
        public int forDateTimestamp;
        public int endTimestamp;
        public Array<ItemStack> prizesFirstPlace = new Array<>(ItemStack.class);
        public Array<ItemStack> prizesSecondPlace = new Array<>(ItemStack.class);
        public Array<ItemStack> prizesThirdPlace = new Array<>(ItemStack.class);
        public Array<ItemStack> prizesTop3Percent = new Array<>(ItemStack.class);
        public Array<ItemStack> prizesTop10Percent = new Array<>(ItemStack.class);
        public Array<ItemStack> prizesTop30Percent = new Array<>(ItemStack.class);

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void write(Kryo kryo, Output output) {
            output.writeBoolean(this.isLocalFallback);
            output.writeInt(this.questId);
            kryo.writeObjectOrNull(output, this.f2327a, String.class);
            kryo.writeObjectOrNull(output, this.forDate, String.class);
            output.writeInt(this.forDateTimestamp);
            output.writeInt(this.endTimestamp);
            kryo.writeObject(output, this.prizesFirstPlace);
            kryo.writeObject(output, this.prizesSecondPlace);
            kryo.writeObject(output, this.prizesThirdPlace);
            kryo.writeObject(output, this.prizesTop3Percent);
            kryo.writeObject(output, this.prizesTop10Percent);
            kryo.writeObject(output, this.prizesTop30Percent);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void read(Kryo kryo, Input input) {
            this.isLocalFallback = input.readBoolean();
            this.questId = input.readInt();
            this.f2327a = (String) kryo.readObjectOrNull(input, String.class);
            this.forDate = (String) kryo.readObjectOrNull(input, String.class);
            this.forDateTimestamp = input.readInt();
            this.endTimestamp = input.readInt();
            this.prizesFirstPlace = (Array) kryo.readObject(input, Array.class);
            this.prizesSecondPlace = (Array) kryo.readObject(input, Array.class);
            this.prizesThirdPlace = (Array) kryo.readObject(input, Array.class);
            this.prizesTop3Percent = (Array) kryo.readObject(input, Array.class);
            this.prizesTop10Percent = (Array) kryo.readObject(input, Array.class);
            this.prizesTop30Percent = (Array) kryo.readObject(input, Array.class);
        }

        public String getLevelName() {
            return this.f2327a;
        }

        public boolean wasCompleted() {
            return this.forDateTimestamp <= ProgressPrefs.i().dailyQuest.getLastCompletedDailyQuestTimestamp();
        }

        public void setCompleted() {
            Game.i.dailyQuestManager.setLastCompletedDailyQuestTimestamp(this.forDateTimestamp);
        }

        public void validate() {
            BasicLevel level = Game.i.basicLevelManager.getLevel(this.f2327a);
            if (!this.f2327a.equals(DailyQuestManager.LEVEL_NAME_PREFIX + this.questId)) {
                throw new IllegalStateException("Name must be DQ" + this.questId + ", got " + this.f2327a);
            }
            if (!level.dailyQuest) {
                throw new IllegalStateException("BasicLevel.dailyQuest must be true");
            }
            boolean z = false;
            if (level.quests.size != 0) {
                if (level.quests.size != 1) {
                    throw new IllegalStateException("level must contain only one quest, " + level.quests.size + " found");
                }
                if (!level.quests.get(0).id.startsWith("Q:" + this.f2327a + ":")) {
                    throw new IllegalStateException("quest name must start with Q:" + this.f2327a + ":");
                }
                z = true;
            }
            if (level.waveQuests.size != 0) {
                if (z) {
                    throw new IllegalStateException("level must contain only one quest but it contains regular & wave quests");
                }
                if (level.waveQuests.size != 1) {
                    throw new IllegalStateException("level must contain only one quest, " + level.waveQuests.size + " found");
                }
                if (!level.waveQuests.get(0).id.startsWith("WQ:" + this.f2327a + ":")) {
                    throw new IllegalStateException("quest name must start with WQ:" + this.f2327a + ":");
                }
                z = true;
            }
            if (!z) {
                throw new IllegalStateException("no quests found");
            }
            if (!level.getMap().getTargetTileOrThrow().isUseStockGameValues()) {
                throw new IllegalStateException("base must disable researches & trophies");
            }
        }

        public int getForDateTimestamp() {
            return this.forDateTimestamp;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v0, types: [com.prineside.tdi2.managers.DailyQuestManager$DailyQuestLevel] */
        /* JADX WARN: Type inference failed for: r0v1, types: [java.text.ParseException] */
        /* JADX WARN: Type inference failed for: r0v2, types: [com.prineside.tdi2.managers.DailyQuestManager$DailyQuestLevel] */
        public void setForDate(String str) {
            ?? r0 = this;
            r0.forDate = str;
            try {
                r0 = this;
                r0.forDateTimestamp = (int) (DailyQuestManager.e.parse(str).getTime() / 1000);
            } catch (ParseException e) {
                r0.printStackTrace();
            }
        }

        public String getForDate() {
            return this.forDate;
        }

        public CharSequence getQuestName() {
            BasicLevel level = Game.i.basicLevelManager.getLevel(getLevelName());
            if (level.quests.size != 0) {
                BasicLevelQuestConfig basicLevelQuestConfig = level.quests.get(0);
                DailyQuestManager.g.setLength(0);
                DailyQuestManager.g.append(basicLevelQuestConfig.getTitle(false, false)).append(": ").append(basicLevelQuestConfig.formatValueForUi(basicLevelQuestConfig.requiredValue));
                return DailyQuestManager.g;
            }
            BasicLevel.WaveQuest waveQuest = level.waveQuests.get(0);
            DailyQuestManager.g.setLength(0);
            DailyQuestManager.g.append(Game.i.localeManager.i18n.get("defeat_waves")).append(": ").append(waveQuest.waves);
            return DailyQuestManager.g;
        }

        public Array<ItemStack> getQuestRewards() {
            BasicLevel level = Game.i.basicLevelManager.getLevel(getLevelName());
            if (level.quests.size != 0) {
                return level.quests.get(0).prizes;
            }
            return level.waveQuests.get(0).prizes;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/DailyQuestManager$DailyQuestLeaderboards.class */
    public static class DailyQuestLeaderboards {
        public boolean success;
        public String date;
        public String playerId;
        public LeaderboardsRank player = null;
        public Array<LeaderBoardManager.LeaderboardsEntry> entries = new Array<>();
        public int requestTimestamp = Game.getTimestampSeconds();

        public DailyQuestLeaderboards(boolean z, String str, String str2) {
            this.success = z;
            this.date = str;
            this.playerId = str2;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/DailyQuestManager$LeaderboardsRank.class */
    public static class LeaderboardsRank {
        public boolean success;
        public int requestTimestamp;
        public boolean isAdvance;
        public String boardDate;
        public String playerId;
        public int rank;
        public long score;
        public int total;

        /* synthetic */ LeaderboardsRank(boolean z, boolean z2, String str, String str2, byte b2) {
            this(true, false, str, str2);
        }

        private LeaderboardsRank(boolean z, boolean z2, String str, String str2) {
            this.isAdvance = z2;
            this.playerId = str2;
            this.success = z;
            this.boardDate = str;
            this.requestTimestamp = Game.getTimestampSeconds();
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/DailyQuestManager$DailyLootDayConfig.class */
    public static class DailyLootDayConfig {
        public Item item;
        public int count;
        public double monthlyBonus;

        public int getCount(int i) {
            int i2 = this.count + ((int) ((this.monthlyBonus * i) + 1.0E-4d));
            if (Game.i.progressManager.isDoubleGainEnabled()) {
                i2 <<= 1;
            }
            return i2;
        }

        public static DailyLootDayConfig fromJson(JsonValue jsonValue) {
            DailyLootDayConfig dailyLootDayConfig = new DailyLootDayConfig();
            dailyLootDayConfig.item = Item.fromJson(jsonValue.get("item"));
            dailyLootDayConfig.count = jsonValue.getInt("count");
            dailyLootDayConfig.monthlyBonus = jsonValue.getInt("monthlyBonus");
            return dailyLootDayConfig;
        }
    }
}
