package com.prineside.tdi2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.WaveTemplates;
import com.prineside.tdi2.enums.DifficultyMode;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.gameplayMods.BonusStagesConfig;
import com.prineside.tdi2.items.StarItem;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.preferences.categories.ProgressPrefs;
import com.prineside.tdi2.screens.GameScreen;
import com.prineside.tdi2.systems.QuestSystem;
import com.prineside.tdi2.utils.FastRandom;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.html.Attribute;
import java.io.StringWriter;
import java.lang.ref.SoftReference;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/BasicLevel.class */
public class BasicLevel {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f1665a = TLog.forClass(BasicLevel.class);
    public String name;
    public String stageName;
    public int stagePosition;
    public int seed;
    public boolean hasLeaderboards;
    public boolean notAffectsStatistics;
    public boolean canNotBeRestarted;
    public boolean achievementsDisabled;
    public boolean customWaves;
    public boolean fixedQuests;
    public boolean isBonus;
    public boolean dailyQuest;
    public int priceInMoney;
    public WaveTemplates.PredefinedWaveTemplate[] enemyWaves;

    @Null
    public BonusStagesConfig bonusStagesConfig;
    public DifficultyMode forcedDifficulty = null;
    public int fastForwardFrame = 0;
    public final Array<Requirement> openRequirements = new Array<>(Requirement.class);
    public final Array<Requirement> showRequirements = new Array<>(Requirement.class);
    public final int[] priceInResources = new int[ResourceType.values.length];
    public final Array<BasicLevelQuestConfig> quests = new Array<>(BasicLevelQuestConfig.class);
    public final Array<WaveQuest> waveQuests = new Array<>(WaveQuest.class);
    public float difficultyExpectedPlaytime = 1.0f;

    /* renamed from: b, reason: collision with root package name */
    private SoftReference<Map> f1666b = null;

    static {
        new StringBuilder();
    }

    private BasicLevel() {
    }

    public boolean isPurchased() {
        return ProgressPrefs.i().basicLevel.isLevelPurchased(this.name);
    }

    public boolean isPurchasedOrPlayed() {
        return ProgressPrefs.i().basicLevel.isLevelPurchasedOrPlayed(this.name);
    }

    public BasicLevel clone(String str) {
        Json json = new Json(JsonWriter.OutputType.json);
        StringWriter stringWriter = new StringWriter();
        json.setWriter(stringWriter);
        json.writeObjectStart();
        toJson(json);
        json.writeObjectEnd();
        BasicLevel fromJson = fromJson(new JsonReader().parse(stringWriter.toString()));
        fromJson.name = str;
        fromJson.f1666b = null;
        if (this.bonusStagesConfig != null) {
            fromJson.bonusStagesConfig = this.bonusStagesConfig.cpy();
        }
        AssetManager.localOrInternalFile("levels/maps/" + this.name + ".json").copyTo(Gdx.files.local("levels/maps/" + str + ".json"));
        fromJson.saveToDisk();
        return fromJson;
    }

    public static BasicLevel createNew(String str) {
        BasicLevel basicLevel = new BasicLevel();
        basicLevel.name = str;
        basicLevel.stageName = "-1";
        basicLevel.seed = FastRandom.getFairInt(8999) + 1000;
        return basicLevel;
    }

    public void saveToDisk() {
        Json json = new Json(JsonWriter.OutputType.json);
        StringWriter stringWriter = new StringWriter();
        json.setWriter(stringWriter);
        json.writeObjectStart();
        toJson(json);
        json.writeObjectEnd();
        Gdx.files.local("levels/" + this.name + ".json").writeString(new JsonReader().parse(stringWriter.toString()).prettyPrint(JsonWriter.OutputType.json, 2), false, "UTF-8");
        if (!Gdx.files.internal("levels/maps/" + this.name + ".json").exists() && !Gdx.files.local("levels/maps/" + this.name + ".json").exists()) {
            Map map = new Map(3, 3);
            Json json2 = new Json(JsonWriter.OutputType.json);
            StringWriter stringWriter2 = new StringWriter();
            json2.setWriter(stringWriter2);
            json2.writeObjectStart();
            map.toJson(json2);
            json2.writeObjectEnd();
            Gdx.files.local("levels/maps/" + this.name + ".json").writeString(new JsonReader().parse(new JsonReader().parse(stringWriter2.toString()).prettyPrint(JsonWriter.OutputType.json, 2)).prettyPrint(JsonWriter.OutputType.json, 2), false, "UTF-8");
            f1665a.i("created dummy map for level " + this.name, new Object[0]);
        }
        f1665a.i("level " + this.name + " saved on disk", new Object[0]);
    }

    public String toJsonStringEverything() {
        Json json = new Json(JsonWriter.OutputType.json);
        StringWriter stringWriter = new StringWriter();
        json.setWriter(stringWriter);
        json.writeObjectStart();
        json.writeValue("_type", "BASIC_LEVEL_FULL");
        json.writeObjectStart("configuration");
        toJson(json);
        json.writeObjectEnd();
        json.writeObjectStart("map");
        getMap().toJson(json);
        json.writeObjectEnd();
        json.writeObjectEnd();
        return stringWriter.toString();
    }

    public void toJson(Json json) {
        json.writeValue(Attribute.NAME_ATTR, this.name);
        json.writeValue("stage", this.stageName);
        if (this.stagePosition != 0) {
            json.writeValue("stagePosition", Integer.valueOf(this.stagePosition));
        }
        json.writeValue("seed", Integer.valueOf(this.seed));
        if (this.hasLeaderboards) {
            json.writeValue("hasLeaderboards", Boolean.TRUE);
        }
        if (this.notAffectsStatistics) {
            json.writeValue("notAffectsStatistics", Boolean.TRUE);
        }
        if (this.canNotBeRestarted) {
            json.writeValue("canNotBeRestarted", Boolean.TRUE);
        }
        if (this.achievementsDisabled) {
            json.writeValue("achievementsDisabled", Boolean.TRUE);
        }
        if (this.dailyQuest) {
            json.writeValue("dailyQuest", Boolean.TRUE);
        }
        if (this.forcedDifficulty != null) {
            json.writeValue("forcedDifficulty", this.forcedDifficulty.name());
        }
        if (this.fixedQuests) {
            json.writeValue("fixedQuests", Boolean.TRUE);
        }
        if (this.isBonus) {
            json.writeValue("isBonus", Boolean.TRUE);
        }
        if (this.customWaves) {
            json.writeValue("customWaves", Boolean.TRUE);
        }
        if (this.fastForwardFrame > 0) {
            json.writeValue("fastForwardFrame", Integer.valueOf(this.fastForwardFrame));
        }
        if (this.openRequirements.size != 0) {
            json.writeArrayStart("openRequirements");
            for (int i = 0; i < this.openRequirements.size; i++) {
                json.writeObjectStart();
                this.openRequirements.items[i].toJson(json);
                json.writeObjectEnd();
            }
            json.writeArrayEnd();
        }
        if (this.showRequirements.size != 0) {
            json.writeArrayStart("showRequirements");
            for (int i2 = 0; i2 < this.showRequirements.size; i2++) {
                json.writeObjectStart();
                this.showRequirements.items[i2].toJson(json);
                json.writeObjectEnd();
            }
            json.writeArrayEnd();
        }
        boolean z = false;
        if (this.priceInMoney > 0) {
            z = true;
        }
        int[] iArr = this.priceInResources;
        int length = iArr.length;
        int i3 = 0;
        while (true) {
            if (i3 >= length) {
                break;
            }
            if (iArr[i3] <= 0) {
                i3++;
            } else {
                z = true;
                break;
            }
        }
        if (z) {
            json.writeArrayStart("price");
            if (this.priceInMoney > 0) {
                json.writeObjectStart();
                json.writeValue("type", "MONEY");
                json.writeValue("amount", Integer.valueOf(this.priceInMoney));
                json.writeObjectEnd();
            }
            for (int i4 = 0; i4 < this.priceInResources.length; i4++) {
                if (this.priceInResources[i4] > 0) {
                    json.writeObjectStart();
                    json.writeValue("type", "RESOURCE");
                    json.writeValue(Attribute.NAME_ATTR, ResourceType.values[i4].name());
                    json.writeValue("amount", Integer.valueOf(this.priceInResources[i4]));
                    json.writeObjectEnd();
                }
            }
            json.writeArrayEnd();
        }
        if (this.quests.size != 0) {
            json.writeArrayStart("quests");
            for (int i5 = 0; i5 < this.quests.size; i5++) {
                json.writeObjectStart();
                this.quests.items[i5].toJson(json);
                json.writeObjectEnd();
            }
            json.writeArrayEnd();
        }
        if (this.waveQuests.size != 0) {
            json.writeArrayStart("waveQuests");
            for (int i6 = 0; i6 < this.waveQuests.size; i6++) {
                json.writeObjectStart();
                this.waveQuests.items[i6].toJson(json);
                json.writeObjectEnd();
            }
            json.writeArrayEnd();
        }
        json.writeValue("difficultyExpectedPlaytime", Float.valueOf(this.difficultyExpectedPlaytime));
        if (this.enemyWaves != null && this.enemyWaves.length != 0) {
            json.writeArrayStart("enemyWaves");
            for (int i7 = 0; i7 < this.enemyWaves.length; i7++) {
                json.writeObjectStart();
                this.enemyWaves[i7].toJson(json);
                json.writeObjectEnd();
            }
            json.writeArrayEnd();
        }
        if (this.bonusStagesConfig != null) {
            json.writeObjectStart("bonusStagesConfig");
            this.bonusStagesConfig.toJson(json);
            json.writeObjectEnd();
        }
    }

    public static BasicLevel createNewFromFullJson(String str) {
        try {
            JsonValue parse = new JsonReader().parse(str);
            if ("BASIC_LEVEL_FULL".equals(parse.getString("_type"))) {
                BasicLevel fromJson = fromJson(parse.get("configuration"));
                fromJson.setMap(Map.fromJson(parse.get("map")));
                return fromJson;
            }
            throw new IllegalArgumentException("Invalid json - must be of type BASIC_LEVEL_FULL");
        } catch (Exception e) {
            f1665a.e("failed to create level from json:", new Object[0]);
            f1665a.e(str, new Object[0]);
            throw new IllegalArgumentException("Invalid json string", e);
        }
    }

    public static BasicLevel fromJson(JsonValue jsonValue) {
        BasicLevel basicLevel = new BasicLevel();
        basicLevel.name = jsonValue.getString(Attribute.NAME_ATTR);
        basicLevel.stageName = jsonValue.getString("stage");
        basicLevel.stagePosition = jsonValue.getInt("stagePosition", 0);
        basicLevel.seed = jsonValue.getInt("seed");
        basicLevel.hasLeaderboards = jsonValue.getBoolean("hasLeaderboards", false);
        basicLevel.achievementsDisabled = jsonValue.getBoolean("achievementsDisabled", false);
        basicLevel.notAffectsStatistics = jsonValue.getBoolean("notAffectsStatistics", false);
        basicLevel.canNotBeRestarted = jsonValue.getBoolean("canNotBeRestarted", false);
        basicLevel.dailyQuest = jsonValue.getBoolean("dailyQuest", false);
        if (jsonValue.has("forcedDifficulty") && jsonValue.getString("forcedDifficulty").length() > 0) {
            basicLevel.forcedDifficulty = DifficultyMode.valueOf(jsonValue.getString("forcedDifficulty"));
        }
        basicLevel.fixedQuests = jsonValue.getBoolean("fixedQuests", false);
        basicLevel.isBonus = jsonValue.getBoolean("isBonus", false);
        basicLevel.customWaves = jsonValue.getBoolean("customWaves", false);
        basicLevel.fastForwardFrame = jsonValue.getInt("fastForwardFrame", 0);
        JsonValue jsonValue2 = jsonValue.get("openRequirements");
        if (jsonValue2 != null) {
            Iterator<JsonValue> iterator2 = jsonValue2.iterator2();
            while (iterator2.hasNext()) {
                basicLevel.openRequirements.add(Requirement.fromJson(iterator2.next()));
            }
        }
        JsonValue jsonValue3 = jsonValue.get("showRequirements");
        if (jsonValue3 != null) {
            Iterator<JsonValue> iterator22 = jsonValue3.iterator2();
            while (iterator22.hasNext()) {
                basicLevel.showRequirements.add(Requirement.fromJson(iterator22.next()));
            }
        }
        JsonValue jsonValue4 = jsonValue.get("price");
        if (jsonValue4 != null) {
            Iterator<JsonValue> iterator23 = jsonValue4.iterator2();
            while (iterator23.hasNext()) {
                JsonValue next = iterator23.next();
                String string = next.getString("type");
                if (string.equals("MONEY")) {
                    basicLevel.priceInMoney = next.getInt("amount");
                } else if (string.equals("RESOURCE")) {
                    basicLevel.priceInResources[ResourceType.valueOf(next.getString(Attribute.NAME_ATTR)).ordinal()] = next.getInt("amount");
                }
            }
        }
        JsonValue jsonValue5 = jsonValue.get("quests");
        if (jsonValue5 != null) {
            Iterator<JsonValue> iterator24 = jsonValue5.iterator2();
            while (iterator24.hasNext()) {
                basicLevel.quests.add(BasicLevelQuestConfig.fromJson(iterator24.next(), basicLevel));
            }
        }
        JsonValue jsonValue6 = jsonValue.get("waveQuests");
        if (jsonValue6 != null) {
            Iterator<JsonValue> iterator25 = jsonValue6.iterator2();
            while (iterator25.hasNext()) {
                basicLevel.waveQuests.add(WaveQuest.fromJson(basicLevel, iterator25.next()));
            }
        }
        basicLevel.difficultyExpectedPlaytime = jsonValue.getFloat("difficultyExpectedPlaytime", 1.0f);
        JsonValue jsonValue7 = jsonValue.get("enemyWaves");
        if (jsonValue7 != null && jsonValue7.size != 0) {
            basicLevel.enemyWaves = new WaveTemplates.PredefinedWaveTemplate[jsonValue7.size];
            int i = 0;
            Iterator<JsonValue> iterator26 = jsonValue7.iterator2();
            while (iterator26.hasNext()) {
                basicLevel.enemyWaves[i] = WaveTemplates.PredefinedWaveTemplate.fromJson(iterator26.next());
                i++;
            }
        }
        JsonValue jsonValue8 = jsonValue.get("bonusStagesConfig");
        if (jsonValue8 != null) {
            basicLevel.bonusStagesConfig = BonusStagesConfig.fromJson(jsonValue8);
        }
        return basicLevel;
    }

    public int[] getStarMilestoneWaves() {
        int[] iArr = new int[3];
        int i = 0;
        loop0: for (int i2 = 0; i2 < this.waveQuests.size; i2++) {
            WaveQuest waveQuest = this.waveQuests.get(i2);
            for (int i3 = 0; i3 < waveQuest.prizes.size; i3++) {
                if (waveQuest.prizes.get(i3).getItem() instanceof StarItem) {
                    iArr[i] = waveQuest.waves;
                    i++;
                    if (i == 3) {
                        break loop0;
                    }
                }
            }
        }
        return iArr;
    }

    public float getDifficultyExpectedPlaytime() {
        return this.difficultyExpectedPlaytime;
    }

    public synchronized Map reloadMap() {
        if (Game.i.screenManager != null && (Game.i.screenManager.getCurrentScreen() instanceof GameScreen)) {
            f1665a.i("reloadMap " + this.name, new Object[0]);
        }
        try {
            String str = "levels/maps/" + this.name + ".json";
            FileHandle local = Gdx.files.local(str);
            FileHandle fileHandle = local;
            if (!local.exists()) {
                fileHandle = Gdx.files.internal(str);
            }
            Map fromJson = Map.fromJson(new JsonReader().parse(fileHandle));
            this.f1666b = new SoftReference<>(fromJson);
            return fromJson;
        } catch (Exception e) {
            throw new RuntimeException("Failed to load map for level " + this.name, e);
        }
    }

    public Map getMap() {
        Map map = null;
        if (this.f1666b != null) {
            map = this.f1666b.get();
        }
        return map != null ? map : reloadMap();
    }

    public void setMap(Map map) {
        this.f1666b = new SoftReference<>(map);
    }

    public Array<EnemyType> getAllowedEnemies() {
        return getMap().getAllowedEnemies();
    }

    public TextureRegion getPreview() {
        return getMap().getPreview().getTextureRegion();
    }

    public BasicLevelQuestConfig getQuest(String str) {
        for (int i = 0; i < this.quests.size; i++) {
            if (this.quests.get(i).getId().equals(str)) {
                return this.quests.get(i);
            }
        }
        throw new IllegalArgumentException("Quest " + str + " not found");
    }

    public String toString() {
        return "BasicLevel@" + Integer.toHexString(hashCode()) + " (name: " + this.name + ")";
    }

    public WaveQuest getWaveQuest(String str) {
        for (int i = 0; i < this.waveQuests.size; i++) {
            if (this.waveQuests.get(i).id.equals(str)) {
                return this.waveQuests.get(i);
            }
        }
        throw new IllegalArgumentException("Quest " + str + " not found");
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/BasicLevel$WaveQuest.class */
    public static class WaveQuest {
        public BasicLevel basicLevel;
        public String id;
        public int waves;
        public Array<ItemStack> prizes = new Array<>(ItemStack.class);

        public WaveQuest(BasicLevel basicLevel, String str, int i) {
            this.basicLevel = basicLevel;
            this.id = str;
            this.waves = i;
        }

        public void toJson(Json json) {
            json.writeValue(Attribute.ID_ATTR, this.id);
            json.writeValue("waves", Integer.valueOf(this.waves));
            json.writeArrayStart("prizes");
            for (int i = 0; i < this.prizes.size; i++) {
                json.writeObjectStart();
                this.prizes.items[i].toJson(json);
                json.writeObjectEnd();
            }
            json.writeArrayEnd();
        }

        public static WaveQuest fromJson(BasicLevel basicLevel, JsonValue jsonValue) {
            WaveQuest waveQuest = new WaveQuest(basicLevel, jsonValue.getString(Attribute.ID_ATTR), jsonValue.getInt("waves"));
            Iterator<JsonValue> iterator2 = jsonValue.get("prizes").iterator2();
            while (iterator2.hasNext()) {
                waveQuest.prizes.add(ItemStack.fromJson(iterator2.next()));
            }
            return waveQuest;
        }

        public QuestSystem.BasicLevelWaveQuest createIngameQuest(GameSystemProvider gameSystemProvider) {
            return new QuestSystem.BasicLevelWaveQuest(this.basicLevel, this, gameSystemProvider);
        }

        public boolean isCompleted() {
            return Game.i.basicLevelManager.isQuestCompleted(this.id);
        }

        public void setCompleted(boolean z) {
            Game.i.basicLevelManager.setQuestCompleted(this.id, z);
        }
    }
}
