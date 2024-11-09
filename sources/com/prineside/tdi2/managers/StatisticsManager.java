package com.prineside.tdi2.managers;

import com.prineside.tdi2.Game;
import com.prineside.tdi2.Manager;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.managers.preferences.categories.ProgressPrefs;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/StatisticsManager.class */
public class StatisticsManager extends Manager.ManagerAdapter {

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/StatisticsManager$Serializer.class */
    public static class Serializer extends SingletonSerializer<StatisticsManager> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public StatisticsManager read() {
            return Game.i.statisticsManager;
        }
    }

    public int getTimeSpentInGameSinceStart() {
        return (int) getAllTime(StatisticsType.PRT);
    }

    public CharSequence getStatisticsTitle(StatisticsType statisticsType) {
        return Game.i.localeManager.i18n.get("statistics_" + statisticsType.name());
    }

    public CharSequence formatStatisticsValue(StatisticsType statisticsType, double d) {
        if (statisticsType == StatisticsType.PT || statisticsType == StatisticsType.PRT || statisticsType == StatisticsType.PTEMWD || statisticsType == StatisticsType.WCST) {
            return StringFormatter.digestTime((int) d);
        }
        return StringFormatter.compactNumber(d, false);
    }

    public double getAllTime(StatisticsType statisticsType) {
        return ProgressPrefs.i().statistics.getAllTime(statisticsType);
    }

    public double getMaxOneGame(StatisticsType statisticsType) {
        return ProgressPrefs.i().statistics.getMaxOneGame(statisticsType);
    }

    public void registerDelta(StatisticsType statisticsType, double d) {
        ProgressPrefs.i().statistics.addAllTime(statisticsType, d);
        ProgressPrefs.i().requireSave();
        if (statisticsType == StatisticsType.PT && ProgressPrefs.i().progress.getLootBoostTimeLeft() > 0.0f) {
            float lootBoostTimeLeft = ProgressPrefs.i().progress.getLootBoostTimeLeft() - ((float) d);
            float f = lootBoostTimeLeft;
            if (lootBoostTimeLeft < 0.0f) {
                f = 0.0f;
            }
            ProgressPrefs.i().progress.setLootBoostTimeLeft(f);
            ProgressPrefs.i().requireSave();
        }
    }

    public void registerValue(StatisticsType statisticsType, double d) {
        ProgressPrefs.i().statistics.setAllTime(statisticsType, d);
        ProgressPrefs.i().requireSave();
    }

    public void registerMaxOneGame(StatisticsType statisticsType, double d) {
        if (ProgressPrefs.i().statistics.getMaxOneGame(statisticsType) < d) {
            ProgressPrefs.i().statistics.setMaxOneGame(statisticsType, d);
            ProgressPrefs.i().requireSave();
        }
    }
}
