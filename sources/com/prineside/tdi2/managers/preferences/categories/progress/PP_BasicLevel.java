package com.prineside.tdi2.managers.preferences.categories.progress;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectSet;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.enums.BasicLevelLootBonusType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.managers.preferences.PrefMap;
import com.prineside.tdi2.managers.preferences.PrefSubcategory;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import java.io.StringWriter;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/preferences/categories/progress/PP_BasicLevel.class */
public final class PP_BasicLevel implements PrefSubcategory {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2520a = TLog.forClass(PP_BasicLevel.class);

    /* renamed from: b, reason: collision with root package name */
    private final ObjectSet<String> f2521b = new ObjectSet<>();
    private final ObjectSet<String> c = new ObjectSet<>();
    private final ObjectMap<String, Long> d = new ObjectMap<>();
    private final ObjectMap<String, LevelStats> e = new ObjectMap<>();
    private final ObjectMap<String, LevelLootBonus> f = new ObjectMap<>();
    private int g;

    public final int getPlayTimeSinceLevelLootBonusUpdate() {
        return this.g;
    }

    public final synchronized void setPlayTimeSinceLevelLootBonusUpdate(int i) {
        this.g = i;
    }

    @Null
    public final LevelLootBonus getLevelLootBonus(String str) {
        return this.f.get(str, null);
    }

    public final synchronized void setLevelLootBonuses(ObjectMap<String, LevelLootBonus> objectMap) {
        this.f.clear();
        this.f.putAll(objectMap);
    }

    public final long getQuestSavedValue(String str) {
        return this.d.get(str, 0L).longValue();
    }

    public final synchronized void setQuestSavedValue(String str, long j) {
        this.d.put(str, Long.valueOf(j));
    }

    public final synchronized void removeQuestSavedValue(String str) {
        this.d.remove(str);
    }

    public final boolean isQuestCompleted(String str) {
        return this.f2521b.contains(str);
    }

    public final synchronized void setQuestCompleted(String str, boolean z) {
        if (z) {
            this.f2521b.add(str);
        } else {
            this.f2521b.remove(str);
        }
    }

    public final boolean isQuestEverCompleted(String str) {
        return this.c.contains(str);
    }

    public final synchronized void setQuestEverCompleted(String str, boolean z) {
        if (z) {
            this.c.add(str);
        } else {
            this.c.remove(str);
        }
    }

    public final boolean isLevelPurchased(String str) {
        return a(str).purchased;
    }

    public final boolean isLevelPurchasedOrPlayed(String str) {
        LevelStats a2 = a(str);
        return a2.purchased || a2.maxPlayingTime != 0;
    }

    public final synchronized void setLevelPurchased(String str, boolean z) {
        a(str).purchased = z;
    }

    public final int getLevelStartsCount(String str) {
        return a(str).gameStartsCount;
    }

    public final int getLevelMaxReachedWave(String str) {
        return a(str).maxReachedWave;
    }

    public final synchronized void setLevelMaxReachedWave(String str, int i) {
        a(str).maxReachedWave = i;
    }

    public final int getLevelMaxPlayingTime(String str) {
        return a(str).maxPlayingTime;
    }

    public final synchronized void setLevelMaxPlayingTime(String str, int i) {
        a(str).maxPlayingTime = i;
    }

    public final long getLevelMaxScore(String str) {
        return a(str).maxScore;
    }

    public final synchronized void setLevelMaxScore(String str, long j) {
        a(str).maxScore = j;
    }

    public final synchronized void addLevelGameStartsCount(String str, int i) {
        a(str).gameStartsCount += i;
    }

    @Override // com.prineside.tdi2.managers.preferences.PrefSubcategory
    public final synchronized void load(PrefMap prefMap) {
        this.f2521b.clear();
        this.c.clear();
        this.d.clear();
        this.e.clear();
        this.f.clear();
        this.g = 0;
        String str = prefMap.get("playTimeSinceLevelLootBonusUpdate", null);
        if (str != null) {
            try {
                this.g = Integer.parseInt(str);
                if (this.g < 0) {
                    this.g = 0;
                }
            } catch (Exception e) {
                f2520a.e("failed to parse playTimeSinceLevelLootBonusUpdate: " + str, e);
            }
        }
        String str2 = prefMap.get("basicLevelLootBonuses", null);
        if (str2 != null) {
            try {
                Iterator<JsonValue> iterator2 = new JsonReader().parse(str2).iterator2();
                while (iterator2.hasNext()) {
                    JsonValue next = iterator2.next();
                    try {
                        this.f.put(next.name, LevelLootBonus.fromJson(next));
                    } catch (Exception e2) {
                        f2520a.e("failed to parse LevelLootBonus: " + next.asString(), e2);
                    }
                }
            } catch (Exception e3) {
                f2520a.e("failed to parse json: " + str2, e3);
            }
        }
        String str3 = prefMap.get("basicLevelCompletedQuests", null);
        if (str3 != null) {
            try {
                Iterator<JsonValue> iterator22 = new JsonReader().parse(str3).iterator2();
                while (iterator22.hasNext()) {
                    this.f2521b.add(iterator22.next().asString());
                }
            } catch (Exception e4) {
                f2520a.e("failed to parse json: " + str3, e4);
            }
        }
        String str4 = prefMap.get("basicLevelEverCompletedQuests", null);
        if (str4 != null) {
            try {
                Iterator<JsonValue> iterator23 = new JsonReader().parse(str4).iterator2();
                while (iterator23.hasNext()) {
                    this.c.add(iterator23.next().asString());
                }
            } catch (Exception e5) {
                f2520a.e("failed to parse json: " + str4, e5);
            }
        }
        ObjectSet.ObjectSetIterator<String> it = this.f2521b.iterator();
        while (it.hasNext()) {
            String next2 = it.next();
            if (!this.c.contains(next2)) {
                this.c.add(next2);
            }
        }
        String str5 = prefMap.get("basicLevels", null);
        if (str5 != null) {
            try {
                Iterator<JsonValue> iterator24 = new JsonReader().parse(str5).iterator2();
                while (iterator24.hasNext()) {
                    JsonValue next3 = iterator24.next();
                    LevelStats a2 = a(next3.getString("n"));
                    a2.maxScore = next3.getLong("ms", 0L);
                    a2.maxReachedWave = next3.getInt("mrw", 0);
                    a2.maxPlayingTime = next3.getInt("mpt", 0);
                    a2.purchased = next3.getBoolean(FlexmarkHtmlConverter.P_NODE, false);
                    a2.gameStartsCount = next3.getInt("gsc", 0);
                    JsonValue jsonValue = next3.get("sv");
                    if (jsonValue != null) {
                        Iterator<JsonValue> iterator25 = jsonValue.iterator2();
                        while (iterator25.hasNext()) {
                            JsonValue next4 = iterator25.next();
                            try {
                                this.d.put(next4.name, Long.valueOf(next4.asLong()));
                            } catch (Exception e6) {
                                f2520a.e("failed to load quest '" + next4.name + "' savedValue", e6);
                            }
                        }
                    }
                }
            } catch (Exception e7) {
                f2520a.e("failed to parse json: " + str5, e7);
            }
        }
        String str6 = prefMap.get("basicLevelsQuestSavedValues", null);
        if (str6 != null) {
            try {
                Iterator<JsonValue> iterator26 = new JsonReader().parse(str6).iterator2();
                while (iterator26.hasNext()) {
                    JsonValue next5 = iterator26.next();
                    this.d.put(next5.name, Long.valueOf(next5.asLong()));
                }
            } catch (Exception e8) {
                f2520a.e("failed to parse json: " + str6, e8);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.prineside.tdi2.managers.preferences.PrefSubcategory
    public final synchronized void save(PrefMap prefMap) {
        if (Config.isHeadless()) {
            return;
        }
        prefMap.set("playTimeSinceLevelLootBonusUpdate", Integer.toString(this.g));
        if (this.f.size != 0) {
            Json json = new Json(JsonWriter.OutputType.minimal);
            StringWriter stringWriter = new StringWriter();
            json.setWriter(stringWriter);
            json.writeObjectStart();
            ObjectMap.Entries<String, LevelLootBonus> it = this.f.iterator();
            while (it.hasNext()) {
                ObjectMap.Entry next = it.next();
                json.writeObjectStart((String) next.key);
                ((LevelLootBonus) next.value).toJson(json);
                json.writeObjectEnd();
            }
            json.writeObjectEnd();
            prefMap.set("basicLevelLootBonuses", stringWriter.toString());
        }
        if (this.f2521b.size != 0) {
            Json json2 = new Json(JsonWriter.OutputType.minimal);
            StringWriter stringWriter2 = new StringWriter();
            json2.setWriter(stringWriter2);
            json2.writeArrayStart();
            ObjectSet.ObjectSetIterator<String> it2 = this.f2521b.iterator();
            while (it2.hasNext()) {
                json2.writeValue(it2.next());
            }
            json2.writeArrayEnd();
            prefMap.set("basicLevelCompletedQuests", stringWriter2.toString());
        }
        if (this.c.size != 0) {
            Json json3 = new Json(JsonWriter.OutputType.minimal);
            StringWriter stringWriter3 = new StringWriter();
            json3.setWriter(stringWriter3);
            json3.writeArrayStart();
            ObjectSet.ObjectSetIterator<String> it3 = this.c.iterator();
            while (it3.hasNext()) {
                json3.writeValue(it3.next());
            }
            json3.writeArrayEnd();
            prefMap.set("basicLevelEverCompletedQuests", stringWriter3.toString());
        }
        Json json4 = new Json(JsonWriter.OutputType.minimal);
        StringWriter stringWriter4 = new StringWriter();
        json4.setWriter(stringWriter4);
        json4.writeArrayStart();
        Array<String> array = this.e.keys().toArray();
        array.sort();
        for (int i = 0; i < array.size; i++) {
            String str = array.get(i);
            LevelStats levelStats = this.e.get(str);
            if (levelStats != null && !levelStats.isEmpty()) {
                json4.writeObjectStart();
                json4.writeValue("n", str);
                json4.writeValue(FlexmarkHtmlConverter.P_NODE, Boolean.valueOf(levelStats.purchased));
                json4.writeValue("gsc", Integer.valueOf(levelStats.gameStartsCount));
                json4.writeValue("mrw", Integer.valueOf(levelStats.maxReachedWave));
                json4.writeValue("mpt", Integer.valueOf(levelStats.maxPlayingTime));
                json4.writeValue("ms", Long.valueOf(levelStats.maxScore));
                json4.writeObjectEnd();
            }
        }
        json4.writeArrayEnd();
        prefMap.set("basicLevels", stringWriter4.toString());
        Json json5 = new Json(JsonWriter.OutputType.minimal);
        StringWriter stringWriter5 = new StringWriter();
        json5.setWriter(stringWriter5);
        json5.writeObjectStart();
        Array<String> array2 = this.d.keys().toArray();
        array2.sort();
        for (int i2 = 0; i2 < array2.size; i2++) {
            String str2 = array2.get(i2);
            Long l = this.d.get(str2, null);
            if (l != null && l.longValue() != 0) {
                json5.writeValue(str2, l);
            }
        }
        json5.writeObjectEnd();
        prefMap.set("basicLevelsQuestSavedValues", stringWriter5.toString());
    }

    private LevelStats a(String str) {
        LevelStats levelStats = this.e.get(str, null);
        LevelStats levelStats2 = levelStats;
        if (levelStats == null) {
            levelStats2 = new LevelStats((byte) 0);
            this.e.put(str, levelStats2);
        }
        return levelStats2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/preferences/categories/progress/PP_BasicLevel$LevelStats.class */
    public static final class LevelStats {
        public boolean purchased;
        public int gameStartsCount;
        public int maxReachedWave;
        public int maxPlayingTime;
        public long maxScore;

        private LevelStats() {
        }

        /* synthetic */ LevelStats(byte b2) {
            this();
        }

        public final boolean isEmpty() {
            return !this.purchased && this.gameStartsCount == 0 && this.maxReachedWave == 0 && this.maxPlayingTime == 0 && this.maxScore == 0;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/preferences/categories/progress/PP_BasicLevel$LevelLootBonus.class */
    public static class LevelLootBonus {
        public BasicLevelLootBonusType type;
        public float multiplier;

        public LevelLootBonus() {
        }

        public LevelLootBonus(BasicLevelLootBonusType basicLevelLootBonusType, float f) {
            this.type = basicLevelLootBonusType;
            this.multiplier = f;
        }

        public Drawable getIconDrawable() {
            switch (this.type) {
                case BIT_DUST:
                    return Game.i.assetManager.getDrawable("dust-item");
                case RESOURCE_SCALAR:
                    return Game.i.assetManager.getDrawable("resource-scalar").tint(Game.i.resourceManager.getColor(ResourceType.SCALAR));
                case RESOURCE_VECTOR:
                    return Game.i.assetManager.getDrawable("resource-vector").tint(Game.i.resourceManager.getColor(ResourceType.VECTOR));
                case RESOURCE_MATRIX:
                    return Game.i.assetManager.getDrawable("resource-matrix").tint(Game.i.resourceManager.getColor(ResourceType.MATRIX));
                case RESOURCE_TENSOR:
                    return Game.i.assetManager.getDrawable("resource-tensor").tint(Game.i.resourceManager.getColor(ResourceType.TENSOR));
                case RESOURCE_INFIAR:
                    return Game.i.assetManager.getDrawable("resource-infiar").tint(Game.i.resourceManager.getColor(ResourceType.INFIAR));
                case GREEN_PAPERS:
                    return Game.i.assetManager.getDrawable("icon-money").tint(MaterialColor.GREEN.P500);
                default:
                    return Game.i.assetManager.getDrawable("icon-question");
            }
        }

        public static LevelLootBonus fromJson(JsonValue jsonValue) {
            LevelLootBonus levelLootBonus = new LevelLootBonus();
            levelLootBonus.type = BasicLevelLootBonusType.valueOf(jsonValue.getString("type"));
            levelLootBonus.multiplier = jsonValue.getFloat("multiplier", 2.0f);
            return levelLootBonus;
        }

        public void toJson(Json json) {
            json.writeValue("type", this.type.name());
            json.writeValue("multiplier", Float.valueOf(this.multiplier));
        }

        public String toString() {
            return LevelLootBonus.class.getSimpleName() + " (type: " + this.type.name() + ", multiplier: " + this.multiplier + ")";
        }
    }
}
