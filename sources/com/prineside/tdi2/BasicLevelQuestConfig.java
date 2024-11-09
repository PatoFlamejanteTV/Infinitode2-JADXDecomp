package com.prineside.tdi2;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ItemType;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.items.StarItem;
import com.prineside.tdi2.managers.GameValueManager;
import com.prineside.tdi2.managers.preferences.categories.ProgressPrefs;
import com.prineside.tdi2.systems.QuestSystem;
import com.vladsch.flexmark.util.html.Attribute;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.Iterator;
import java.util.Locale;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/BasicLevelQuestConfig.class */
public class BasicLevelQuestConfig {
    public String id;
    public boolean scripted;

    @Null
    public StatisticsType statisticsType;
    public long requiredValue;
    public BasicLevel level;
    public Array<ItemStack> prizes = new Array<>(ItemStack.class);
    public String scriptedTitle;
    public boolean duringGame;

    /* renamed from: a, reason: collision with root package name */
    private static final StringBuilder f1667a = new StringBuilder();

    public BasicLevelQuestConfig(String str, boolean z, @Null StatisticsType statisticsType, long j, boolean z2, BasicLevel basicLevel) {
        this.id = str;
        this.scripted = z;
        this.statisticsType = statisticsType;
        this.requiredValue = j;
        this.duringGame = z2;
        this.level = basicLevel;
    }

    public long getSavedValue() {
        return ProgressPrefs.i().basicLevel.getQuestSavedValue(this.id);
    }

    public void setSavedValue(long j) {
        ProgressPrefs i = ProgressPrefs.i();
        i.basicLevel.setQuestSavedValue(this.id, j);
        i.requireSave();
    }

    public int getExtraDustInEndless(GameValueProvider gameValueProvider) {
        int i = 0;
        for (int i2 = 0; i2 < this.level.quests.size; i2++) {
            i = i2;
            if (this.level.quests.items[i2] == this) {
                break;
            }
        }
        double d = 1.0d + (i / (this.level.quests.size - 1));
        double percentValueAsMultiplier = gameValueProvider.getPercentValueAsMultiplier(GameValueType.BIT_DUST_DROP_RATE);
        if (this.duringGame) {
            return (int) (d * 2.0d * percentValueAsMultiplier);
        }
        return (int) (percentValueAsMultiplier * 1.0d);
    }

    public void toJson(Json json) {
        json.writeValue(Attribute.ID_ATTR, this.id);
        if (this.scripted) {
            json.writeValue("scripted", Boolean.TRUE);
            json.writeValue(Attribute.TITLE_ATTR, this.scriptedTitle);
        } else {
            json.writeValue("statisticsType", this.statisticsType.name());
        }
        if (this.requiredValue != 0) {
            json.writeValue("value", Long.valueOf(this.requiredValue));
        }
        if (this.duringGame) {
            json.writeValue("oneGame", Boolean.TRUE);
        }
        json.writeArrayStart("prizes");
        for (int i = 0; i < this.prizes.size; i++) {
            json.writeObjectStart();
            this.prizes.items[i].toJson(json);
            json.writeObjectEnd();
        }
        json.writeArrayEnd();
    }

    public static BasicLevelQuestConfig fromJson(JsonValue jsonValue, BasicLevel basicLevel) {
        String string = jsonValue.getString(Attribute.ID_ATTR);
        boolean z = jsonValue.getBoolean("scripted", false);
        StatisticsType statisticsType = null;
        if (!z) {
            statisticsType = StatisticsType.valueOf(jsonValue.getString("statisticsType"));
        }
        BasicLevelQuestConfig basicLevelQuestConfig = new BasicLevelQuestConfig(string, z, statisticsType, jsonValue.getLong("value", 0L), jsonValue.getBoolean("oneGame", false), basicLevel);
        if (z) {
            basicLevelQuestConfig.scriptedTitle = jsonValue.getString(Attribute.TITLE_ATTR);
        }
        Iterator<JsonValue> iterator2 = jsonValue.get("prizes").iterator2();
        while (iterator2.hasNext()) {
            basicLevelQuestConfig.prizes.add(ItemStack.fromJson(iterator2.next()));
        }
        return basicLevelQuestConfig;
    }

    @Null
    public QuestSystem.Quest createIngameQuest(GameSystemProvider gameSystemProvider) {
        if (this.scripted) {
            return null;
        }
        return new QuestSystem.BasicLevelQuest(this.level, this, gameSystemProvider);
    }

    public String getId() {
        return this.id;
    }

    public boolean isScripted() {
        return this.scripted;
    }

    public double getCurrentValue(GameValueManager.GameValuesSnapshot gameValuesSnapshot) {
        if (isCompleted()) {
            return getRequiredValue(gameValuesSnapshot);
        }
        if (!this.scripted) {
            return getSavedValue();
        }
        return 0.0d;
    }

    public long getRequiredValue(GameValueManager.GameValuesSnapshot gameValuesSnapshot) {
        if (!this.level.fixedQuests && Game.i.basicLevelManager.getStage(this.level.stageName).regular) {
            double percentValueAsMultiplier = gameValuesSnapshot.getPercentValueAsMultiplier(GameValueType.REGULAR_QUESTS_DIFFICULTY);
            if (percentValueAsMultiplier <= 1.0d) {
                return this.requiredValue;
            }
            long j = (long) (percentValueAsMultiplier * this.requiredValue);
            if (j < 100) {
                return j;
            }
            if (j < 500) {
                return j - (j % 5);
            }
            if (j < 1000) {
                return j - (j % 10);
            }
            if (j < 5000) {
                return j - (j % 50);
            }
            return j - (j % 100);
        }
        return this.requiredValue;
    }

    public Array<ItemStack> getPrizes(GameValueManager.GameValuesSnapshot gameValuesSnapshot) {
        if (this.level.fixedQuests || !Game.i.basicLevelManager.getStage(this.level.stageName).regular) {
            return this.prizes;
        }
        double percentValueAsMultiplier = gameValuesSnapshot.getPercentValueAsMultiplier(GameValueType.REGULAR_QUESTS_PRIZE_MULTIPLIER);
        if (percentValueAsMultiplier <= 1.0d) {
            return this.prizes;
        }
        Array<ItemStack> array = new Array<>(ItemStack.class);
        for (int i = 0; i < this.prizes.size; i++) {
            ItemStack itemStack = this.prizes.items[i];
            if ((itemStack.getItem() instanceof StarItem) || itemStack.getItem().getType() == ItemType.GAME_VALUE_LEVEL || itemStack.getItem().getType() == ItemType.GAME_VALUE_GLOBAL) {
                array.add(itemStack);
            } else {
                array.add(new ItemStack(itemStack.getItem(), (int) ((itemStack.getCount() * percentValueAsMultiplier) + 1.0E-4d)));
            }
        }
        return array;
    }

    public CharSequence formatValueForUi(double d) {
        if (this.scripted) {
            f1667a.setLength(0);
            return f1667a;
        }
        return Game.i.statisticsManager.formatStatisticsValue(this.statisticsType, d);
    }

    public CharSequence formatValueForUiWithRequiredValue(double d, double d2, boolean z) {
        f1667a.setLength(0);
        if (d2 <= 0.0d) {
            return f1667a;
        }
        double min = StrictMath.min(d2, d);
        if (z) {
            f1667a.append("[#90A4AE]");
        }
        f1667a.append(formatValueForUi(min));
        f1667a.append(" / ");
        if (z) {
            f1667a.append("[][#ffffff]");
        }
        f1667a.append(formatValueForUi(d2));
        if (z) {
            f1667a.append("[]");
        }
        return f1667a;
    }

    public boolean isDuringGame() {
        return this.duringGame;
    }

    public boolean isCompleted() {
        return Game.i.basicLevelManager.isQuestCompleted(this.id);
    }

    public boolean wasEverCompleted() {
        return Game.i.basicLevelManager.isQuestEverCompleted(this.id);
    }

    public void setCompleted(boolean z) {
        if (!z) {
            ProgressPrefs i = ProgressPrefs.i();
            i.basicLevel.removeQuestSavedValue(this.id);
            i.requireSave();
        }
        Game.i.basicLevelManager.setQuestCompleted(this.id, z);
    }

    public CharSequence getTitle(boolean z, boolean z2) {
        f1667a.setLength(0);
        if (this.scripted) {
            f1667a.append(Game.i.localeManager.i18n.get(this.scriptedTitle));
        } else {
            f1667a.append(Game.i.statisticsManager.getStatisticsTitle(this.statisticsType));
        }
        if (z2 && this.duringGame) {
            if (z) {
                f1667a.append("[#90A4AE]");
            }
            f1667a.append(SequenceUtils.SPACE).append(Game.i.localeManager.i18n.get("during_one_game").toLowerCase(Locale.ENGLISH));
            if (z) {
                f1667a.append("[]");
            }
        }
        return f1667a;
    }
}
