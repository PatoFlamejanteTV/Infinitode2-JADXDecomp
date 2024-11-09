package com.prineside.tdi2.managers;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Null;
import com.prineside.tdi2.BasicLevel;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Manager;
import com.prineside.tdi2.Screen;
import com.prineside.tdi2.UserMap;
import com.prineside.tdi2.enums.DifficultyMode;
import com.prineside.tdi2.enums.ResearchType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.managers.DailyQuestManager;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.managers.preferences.categories.SettingsPrefs;
import com.prineside.tdi2.screens.AboutScreen;
import com.prineside.tdi2.screens.AccountSettingsScreen;
import com.prineside.tdi2.screens.CrashReportScreen;
import com.prineside.tdi2.screens.CustomMapSelectScreen;
import com.prineside.tdi2.screens.GameScreen;
import com.prineside.tdi2.screens.HotkeyScreen;
import com.prineside.tdi2.screens.LanguageSelectScreen;
import com.prineside.tdi2.screens.LevelSelectScreen;
import com.prineside.tdi2.screens.LoadingScreen;
import com.prineside.tdi2.screens.MainMenuScreen;
import com.prineside.tdi2.screens.MapEditorScreen;
import com.prineside.tdi2.screens.MoneyScreen;
import com.prineside.tdi2.screens.ResearchesScreen;
import com.prineside.tdi2.screens.SettingsScreen;
import com.prineside.tdi2.screens.SimulationScreen;
import com.prineside.tdi2.screens.StatisticsScreen;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.systems.GameStateSystem;
import com.prineside.tdi2.ui.shared.AbilitySelectionOverlay;
import com.prineside.tdi2.ui.shared.Dialog;
import com.prineside.tdi2.ui.shared.Notifications;
import com.prineside.tdi2.utils.GameSyncLoader;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/ScreenManager.class */
public class ScreenManager extends Manager.ManagerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2446a = TLog.forClass(ScreenManager.class);

    /* renamed from: b, reason: collision with root package name */
    private Screen f2447b;
    private final DelayedRemovalArray<ScreenManagerListener> c = new DelayedRemovalArray<>(false, 1);
    private long d = Game.getTimestampMillis();

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/ScreenManager$ScreenManagerListener.class */
    public interface ScreenManagerListener {
        void screenChanged();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/ScreenManager$Serializer.class */
    public static class Serializer extends SingletonSerializer<ScreenManager> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public ScreenManager read() {
            return Game.i.screenManager;
        }
    }

    private void a() {
        long realTickCount = Game.getRealTickCount();
        if (((getCurrentScreen() instanceof GameScreen) || (getCurrentScreen() instanceof MapEditorScreen)) && Game.i.purchaseManager.noIAPAbility()) {
            f2446a.i("noIAPAbility true, dyn setting: " + Game.i.settingsManager.getDynamicSetting(SettingsManager.DynamicSetting.IAP_NOT_AVAILABLE_IN_COUNTRY), new Object[0]);
            if (Game.getTimestampMillis() - this.d > 180000) {
                this.d = Game.getTimestampMillis();
                f2446a.i("interstitial show start", new Object[0]);
                Game.i.actionResolver.showInterstitialAd(bool -> {
                    f2446a.i("interstitial show " + bool, new Object[0]);
                });
            }
        }
        if (this.f2447b != null) {
            this.f2447b.dispose();
            this.f2447b = null;
        }
        if (Game.i.debugManager != null) {
            Game.i.debugManager.registerFrameJob("ScreenManager-disposeScreen", Game.getRealTickCount() - realTickCount);
        }
        Game.i.actionResolver.getInitConfigManager().saveIfRequired();
    }

    public void setNoScreen() {
        a();
        setScreen(null);
    }

    public Screen getCurrentScreen() {
        return this.f2447b;
    }

    public void addListener(ScreenManagerListener screenManagerListener) {
        if (screenManagerListener == null) {
            throw new IllegalArgumentException("listener is null");
        }
        if (!this.c.contains(screenManagerListener, true)) {
            this.c.add(screenManagerListener);
        }
    }

    public void setScreen(Screen screen) {
        a();
        Game.i.setScreen(screen);
        this.f2447b = screen;
        if (!Config.isHeadless()) {
            f2446a.i("setting screen: " + screen.getClass().getSimpleName(), new Object[0]);
        }
        this.c.begin();
        Array.ArrayIterator<ScreenManagerListener> it = this.c.iterator();
        while (it.hasNext()) {
            it.next().screenChanged();
        }
        this.c.end();
        Game.i.actionResolver.logScreenChange(screen.getClass().getSimpleName());
    }

    public void startNewBasicLevel(BasicLevel basicLevel, @Null AbilitySelectionOverlay.SelectedAbilitiesConfiguration selectedAbilitiesConfiguration) {
        a();
        DifficultyMode difficultyMode = Game.i.progressManager.getDifficultyMode();
        setScreen(new GameScreen(basicLevel, difficultyMode, Game.i.progressManager.getDifficultyModeDiffMultiplier(difficultyMode), selectedAbilitiesConfiguration, -1L, null));
    }

    public void startRandomSecretLevel() {
        AbilitySelectionOverlay.i().show(() -> {
        }, selectedAbilitiesConfiguration -> {
            startNewBasicLevel(Game.i.basicLevelManager.getLevel("S1"), selectedAbilitiesConfiguration);
        });
    }

    public void startNewDailyLevel(DailyQuestManager.DailyQuestLevel dailyQuestLevel) {
        a();
        setScreen(new GameScreen(dailyQuestLevel));
    }

    public void startNewUserLevel(UserMap userMap, AbilitySelectionOverlay.SelectedAbilitiesConfiguration selectedAbilitiesConfiguration) {
        a();
        DifficultyMode difficultyMode = Game.i.progressManager.getDifficultyMode();
        setScreen(new GameScreen(userMap, difficultyMode, Game.i.progressManager.getDifficultyModeDiffMultiplier(difficultyMode), selectedAbilitiesConfiguration, -1L, Game.i.userMapManager.getDefaultBosses(), null, null));
    }

    public void goToCrashReportScreen(String str, String str2, String str3, String str4) {
        a();
        setScreen(new CrashReportScreen(str, str2, str3, str4));
    }

    public void goToAccountSettingsScreen() {
        if (Config.isModdingMode()) {
            Notifications.i().add("Not available in modding mode", null, null, StaticSoundType.FAIL);
        } else {
            a();
            setScreen(new AccountSettingsScreen());
        }
    }

    public void goToMoneyScreen() {
        if (Config.isModdingMode()) {
            Notifications.i().add("Not available in modding mode", null, null, StaticSoundType.FAIL);
        } else {
            setScreen(new MoneyScreen(this.f2447b));
        }
    }

    public void goToCustomMapSelectScreen() {
        a();
        setScreen(new CustomMapSelectScreen());
    }

    public void goToMapEditorScreenUserMap(UserMap userMap) {
        a();
        setScreen(new MapEditorScreen(userMap));
    }

    public void goToMapEditorScreenBasicLevel(BasicLevel basicLevel) {
        a();
        if (Game.i.basicLevelManager.mapEditingAvailable()) {
            setScreen(new MapEditorScreen(basicLevel));
        } else {
            Dialog.i().showAlert("Not available on this OS or you are not in developer mode");
        }
    }

    public void goToAboutScreen() {
        a();
        setScreen(new AboutScreen());
    }

    public void goToSettingsScreen() {
        a();
        setScreen(new SettingsScreen());
    }

    public void goToSettingsScreenAndScroll(float f) {
        a();
        setScreen(new SettingsScreen(f));
    }

    public void goToHotkeyEditorScreen() {
        a();
        setScreen(new HotkeyScreen());
    }

    public void goToLoadingScreen(GameSyncLoader gameSyncLoader) {
        a();
        setScreen(new LoadingScreen(gameSyncLoader));
    }

    public void goToStatisticsScreen() {
        a();
        setScreen(new StatisticsScreen());
    }

    public void goToMainMenu() {
        goToMainMenuJustLaunched(false);
    }

    public void goToMainMenuJustLaunched(boolean z) {
        Game.i.assertInMainThread();
        a();
        long timestampMillis = Game.getTimestampMillis();
        TLog tLog = f2446a;
        StringBuilder sb = new StringBuilder("isLocaleSet ");
        Game game = Game.i;
        tLog.i(sb.append(LocaleManager.a()).append(SequenceUtils.SPACE).append(SettingsPrefs.i().locale.localeName).toString(), new Object[0]);
        Game game2 = Game.i;
        if (!LocaleManager.a()) {
            a();
            setScreen(new LanguageSelectScreen(z));
        } else {
            setScreen(new MainMenuScreen(z));
        }
        f2446a.d("goToMainMenu took " + (Game.getTimestampMillis() - timestampMillis) + "ms", new Object[0]);
    }

    public void goToSimulationScreen() {
        a();
        setScreen(new SimulationScreen());
    }

    public void goToLanguageSelectScreen() {
        a();
        setScreen(new LanguageSelectScreen());
    }

    public void goToResearchesScreen() {
        a();
        setScreen(new ResearchesScreen());
    }

    public void goToResearchesScreenFocusOnResearch(ResearchType researchType) {
        a();
        setScreen(new ResearchesScreen(researchType));
    }

    public void goToLevelSelectScreen() {
        a();
        setScreen(new LevelSelectScreen());
    }

    public void goToLevelSelectScreenShowLevel(BasicLevel basicLevel) {
        a();
        setScreen(new LevelSelectScreen(basicLevel));
    }

    public void startNewLevelWithAbilitySelection(GameStateSystem.GameMode gameMode, String str) {
        if (gameMode == GameStateSystem.GameMode.BASIC_LEVELS) {
            if (Game.i.basicLevelManager.getLevel(str).getMap().getTargetTileOrThrow().isDisableAbilities() || !Game.i.abilityManager.isAnyAbilityOpened()) {
                Game.i.screenManager.startNewBasicLevel(Game.i.basicLevelManager.getLevel(str), null);
                return;
            } else {
                AbilitySelectionOverlay.i().show(() -> {
                }, selectedAbilitiesConfiguration -> {
                    Game.i.screenManager.startNewBasicLevel(Game.i.basicLevelManager.getLevel(str), selectedAbilitiesConfiguration);
                });
                return;
            }
        }
        if (gameMode == GameStateSystem.GameMode.USER_MAPS) {
            if (Game.i.userMapManager.getUserMap(str).map.getTargetTileOrThrow().isDisableAbilities() || !Game.i.abilityManager.isAnyAbilityOpened()) {
                Game.i.screenManager.startNewUserLevel(Game.i.userMapManager.getUserMap(str), null);
            } else {
                AbilitySelectionOverlay.i().show(() -> {
                }, selectedAbilitiesConfiguration2 -> {
                    Game.i.screenManager.startNewUserLevel(Game.i.userMapManager.getUserMap(str), selectedAbilitiesConfiguration2);
                });
            }
        }
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.badlogic.gdx.utils.Disposable
    public void dispose() {
    }
}
