package com.prineside.tdi2.managers;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Manager;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.managers.preferences.categories.SettingsPrefs;
import com.prineside.tdi2.screens.LevelSelectScreen;
import com.prineside.tdi2.screens.MainMenuScreen;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.ui.components.RatingForm;
import com.prineside.tdi2.utils.REGS;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/RatingManager.class */
public class RatingManager extends Manager.ManagerAdapter {

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/RatingManager$Serializer.class */
    public static class Serializer extends SingletonSerializer<RatingManager> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public RatingManager read() {
            return Game.i.ratingManager;
        }
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void setup() {
        Game.i.screenManager.addListener(() -> {
            if (SettingsPrefs.i().rating.lastResponse == 1 || SettingsPrefs.i().rating.lastResponse == 3) {
                return;
            }
            if (((Game.i.screenManager.getCurrentScreen() instanceof MainMenuScreen) || (Game.i.screenManager.getCurrentScreen() instanceof LevelSelectScreen)) && shouldWeAskForReview()) {
                RatingForm.i().showRatePrompt();
            }
        });
    }

    public boolean isMadeReview() {
        return SettingsPrefs.i().rating.lastResponse == 3;
    }

    public boolean shouldWeAskForReview() {
        if (SettingsPrefs.i().rating.lastResponse == 3 || SettingsPrefs.i().rating.lastResponse == 1 || ((int) Game.i.statisticsManager.getAllTime(StatisticsType.PT)) > 62208000 || Gdx.app.getType() == Application.ApplicationType.Desktop) {
            return false;
        }
        if (SettingsPrefs.i().rating.lastResponse == 0) {
            return Game.i.basicLevelManager.isOpened(Game.i.basicLevelManager.getLevel("2.6"));
        }
        return SettingsPrefs.i().rating.lastResponse == 2 && Game.getTimestampMillis() - SettingsPrefs.i().rating.responseTimestamp > 172800000;
    }

    public boolean shouldRateButtonBeVisible() {
        if (SettingsPrefs.i().rating.lastResponse == 3 || SettingsPrefs.i().rating.lastResponse == 1 || ((int) Game.i.statisticsManager.getAllTime(StatisticsType.PT)) > 62208000 || Gdx.app.getType() == Application.ApplicationType.Desktop) {
            return false;
        }
        if (SettingsPrefs.i().rating.lastResponse == 0) {
            return Game.i.basicLevelManager.isOpened(Game.i.basicLevelManager.getLevel("2.6"));
        }
        if (SettingsPrefs.i().rating.lastResponse == 2) {
            return true;
        }
        return false;
    }

    public void madeReview(int i, String str) {
        SettingsPrefs.i().rating.lastResponse = 3;
        SettingsPrefs.i().rating.responseTimestamp = Game.getTimestampMillis();
        SettingsPrefs.i().requireSave();
        Game.i.analyticsManager.logCustomEvent("made_review", new String[]{"stars", String.valueOf(i)}, new String[0]);
    }

    public void promptAnsweredLater() {
        SettingsPrefs.i().rating.lastResponse = 2;
        SettingsPrefs.i().rating.responseTimestamp = Game.getTimestampMillis();
        SettingsPrefs.i().requireSave();
    }

    public void promptAnsweredNever() {
        SettingsPrefs.i().rating.lastResponse = 1;
        SettingsPrefs.i().rating.responseTimestamp = Game.getTimestampMillis();
        SettingsPrefs.i().requireSave();
    }
}
