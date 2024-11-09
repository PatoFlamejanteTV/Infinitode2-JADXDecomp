package com.prineside.tdi2.systems;

import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystem;
import com.prineside.tdi2.configs.GameRenderingOrder;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.systems.RenderSystem;
import com.prineside.tdi2.ui.actors.PanZoomTooltip;
import com.prineside.tdi2.ui.actors.ScreenBorderGradient;
import com.prineside.tdi2.ui.actors.SideMenu;
import com.prineside.tdi2.ui.components.AbilityMenu;
import com.prineside.tdi2.ui.components.BossTileMenu;
import com.prineside.tdi2.ui.components.BuildMenu;
import com.prineside.tdi2.ui.components.CoreMenu;
import com.prineside.tdi2.ui.components.FlyingItemsOverlay;
import com.prineside.tdi2.ui.components.GameOverOverlay;
import com.prineside.tdi2.ui.components.GameStateEditor;
import com.prineside.tdi2.ui.components.GameValueMenu;
import com.prineside.tdi2.ui.components.GameplayBonusesOverlay;
import com.prineside.tdi2.ui.components.GateMenu;
import com.prineside.tdi2.ui.components.LiveLeaderboard;
import com.prineside.tdi2.ui.components.MainUi;
import com.prineside.tdi2.ui.components.MinerMenu;
import com.prineside.tdi2.ui.components.ModifierMenu;
import com.prineside.tdi2.ui.components.NewEnemyOverlay;
import com.prineside.tdi2.ui.components.PauseMenu;
import com.prineside.tdi2.ui.components.QuestList;
import com.prineside.tdi2.ui.components.RoadMenu;
import com.prineside.tdi2.ui.components.SpawnMenu;
import com.prineside.tdi2.ui.components.StatisticsChart;
import com.prineside.tdi2.ui.components.StorylineMessages;
import com.prineside.tdi2.ui.components.Subtitles;
import com.prineside.tdi2.ui.components.TargetMenu;
import com.prineside.tdi2.ui.components.Tooltip;
import com.prineside.tdi2.ui.components.TowerMenu;
import com.prineside.tdi2.ui.components.UiElementsEmphasizer;
import com.prineside.tdi2.ui.components.XmMusicTrackMenu;
import com.prineside.tdi2.utils.NAGS;

@NAGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/GameUiSystem.class */
public final class GameUiSystem extends GameSystem {
    public ScreenBorderGradient screenBorderGradient;
    public PauseMenu pauseMenu;
    public MainUi mainUi;
    public QuestList questList;
    public StatisticsChart _statisticsChart;
    public GameStateEditor _stateEditor;
    public LiveLeaderboard liveLeaderboard;
    public AbilityMenu abilityMenu;
    public SideMenu sideMenu;
    public TowerMenu towerMenu;
    public RoadMenu roadMenu;
    public ModifierMenu modifierMenu;
    public MinerMenu minerMenu;
    public BuildMenu buildMenu;
    public SpawnMenu spawnMenu;
    public TargetMenu targetMenu;
    public BossTileMenu bossTileMenu;
    public CoreMenu coreMenu;
    public GameValueMenu gameValueMenu;
    public XmMusicTrackMenu xmMusicTrackMenu;
    public GateMenu gateMenu;
    public StorylineMessages storylineMessages;
    public Subtitles subtitles;
    public PanZoomTooltip panZoomTooltip;
    public UiElementsEmphasizer uiElementsEmphasizer;
    public GameplayBonusesOverlay gameplayBonusesOverlay;
    public NewEnemyOverlay newEnemyOverlay;
    public Tooltip tooltip;
    public GameOverOverlay gameOverOverlay;
    public FlyingItemsOverlay flyingItemsOverlay;

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/GameUiSystem$ScreenshotModeConfig.class */
    public static class ScreenshotModeConfig {
    }

    @Override // com.prineside.tdi2.GameSystem
    public final boolean affectsGameState() {
        return false;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final boolean profileUpdate() {
        return false;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final String getSystemName() {
        return "GameUi";
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void setup() {
        this.pauseMenu = new PauseMenu(this.S);
        this.mainUi = new MainUi(this.S);
        this.screenBorderGradient = new ScreenBorderGradient();
        this.sideMenu = new SideMenu(600.0f);
        this.roadMenu = new RoadMenu(this.sideMenu, this.S);
        this.towerMenu = new TowerMenu(this.sideMenu, this.S);
        this.modifierMenu = new ModifierMenu(this.sideMenu, this.S);
        this.minerMenu = new MinerMenu(this.sideMenu, this.S);
        this.buildMenu = new BuildMenu(this.sideMenu, this.S);
        this.spawnMenu = new SpawnMenu(this.sideMenu, this.S);
        this.targetMenu = new TargetMenu(this.sideMenu, this.S);
        this.bossTileMenu = new BossTileMenu(this.sideMenu, this.S);
        this.coreMenu = new CoreMenu(this.sideMenu, this.S);
        this.gameValueMenu = new GameValueMenu(this.sideMenu, this.S);
        this.xmMusicTrackMenu = new XmMusicTrackMenu(this.sideMenu, this.S);
        this.gateMenu = new GateMenu(this.sideMenu, this.S);
        Image image = new Image(Game.i.assetManager.getDrawable("ui-tile-menu-background"));
        image.setSize(600.0f, Game.i.settingsManager.getScaledViewportHeight());
        image.setTouchable(Touchable.disabled);
        this.sideMenu.getBackgroundContainer().addActor(image);
        this.sideMenu.addOffscreenBackground();
        this.questList = new QuestList();
        if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.STATISTICS_CHART_ENABLED) != 0.0d) {
            this._statisticsChart = new StatisticsChart(this.S);
        }
        if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.STATE_EDITOR_ENABLED) != 0.0d) {
            this._stateEditor = new GameStateEditor(this.S);
        }
        this.liveLeaderboard = new LiveLeaderboard(this.S);
        this.abilityMenu = new AbilityMenu(this.S);
        this.storylineMessages = new StorylineMessages(this.S);
        this.subtitles = new Subtitles();
        this.panZoomTooltip = new PanZoomTooltip();
        this.flyingItemsOverlay = new FlyingItemsOverlay(this.S);
        this.uiElementsEmphasizer = new UiElementsEmphasizer(this.S);
        this.gameplayBonusesOverlay = new GameplayBonusesOverlay(this.S);
        this.newEnemyOverlay = new NewEnemyOverlay(this.S);
        this.tooltip = new Tooltip();
        this.gameOverOverlay = new GameOverOverlay(this.S);
        this.S._render.addLayer(new RenderSystem.Layer(GameRenderingOrder.GAME_UI_DRAW, false, (batch, f, f2, f3) -> {
            draw(f);
        }).setName("Graphics-draw"));
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void postSetup() {
        this.mainUi.postSetup();
        this.buildMenu.postSetup();
    }

    public final MainUi getMainUi() {
        return this.mainUi;
    }

    public final void fadeOutUi() {
        this.mainUi.finalFadeOut();
        this.questList.finalFadeOut();
        this.liveLeaderboard.finalFadeOut();
        this.sideMenu.finalFadeOut();
        this.abilityMenu.finalFadeOut();
        this.subtitles.finalFadeOut();
        this.gameplayBonusesOverlay.hide();
        if (this._statisticsChart != null) {
            this._statisticsChart.finalFadeOut();
        }
    }

    public final void setUiScreenshotMode(ScreenshotModeConfig screenshotModeConfig) {
        this.mainUi.setUiScreenshotMode(screenshotModeConfig);
    }

    public final void draw(float f) {
        if (this.towerMenu.isVisible()) {
            this.towerMenu.draw(f);
        }
        if (this.modifierMenu.isVisible()) {
            this.modifierMenu.draw(f);
        }
        this.minerMenu.draw(f);
        this.mainUi.draw(f);
        this.abilityMenu.draw(f);
        this.targetMenu.draw(f);
        this.coreMenu.draw(f);
        this.subtitles.draw(f);
        if (this._statisticsChart != null) {
            this._statisticsChart.draw(f);
        }
        if (this._stateEditor != null) {
            this._stateEditor.draw(f);
        }
    }

    @Override // com.prineside.tdi2.GameSystem, com.badlogic.gdx.utils.Disposable
    public final void dispose() {
        this.screenBorderGradient.dispose();
        this.pauseMenu.dispose();
        this.mainUi.dispose();
        this.sideMenu.dispose();
        this.abilityMenu.dispose();
        this.towerMenu.dispose();
        this.roadMenu.dispose();
        this.modifierMenu.dispose();
        this.minerMenu.dispose();
        this.buildMenu.dispose();
        this.spawnMenu.dispose();
        this.xmMusicTrackMenu.dispose();
        this.gateMenu.dispose();
        if (this._statisticsChart != null) {
            this._statisticsChart.dispose();
        }
        if (this._stateEditor != null) {
            this._stateEditor.dispose();
        }
        this.questList.dispose();
        this.liveLeaderboard.dispose();
        this.storylineMessages.dispose();
        this.subtitles.dispose();
        this.panZoomTooltip.dispose();
        this.flyingItemsOverlay.dispose();
        this.uiElementsEmphasizer.dispose();
        this.newEnemyOverlay.dispose();
        this.tooltip.dispose();
        this.gameOverOverlay.dispose();
        this.gameplayBonusesOverlay.dispose();
        this.screenBorderGradient = null;
        this.pauseMenu = null;
        this.mainUi = null;
        this.questList = null;
        this._statisticsChart = null;
        this._stateEditor = null;
        this.liveLeaderboard = null;
        this.gameplayBonusesOverlay = null;
        this.abilityMenu = null;
        this.sideMenu = null;
        this.towerMenu = null;
        this.roadMenu = null;
        this.modifierMenu = null;
        this.minerMenu = null;
        this.buildMenu = null;
        this.spawnMenu = null;
        this.targetMenu = null;
        this.bossTileMenu = null;
        this.gameValueMenu = null;
        this.coreMenu = null;
        this.xmMusicTrackMenu = null;
        this.gateMenu = null;
        this.storylineMessages = null;
        this.subtitles = null;
        this.panZoomTooltip = null;
        this.flyingItemsOverlay = null;
        this.uiElementsEmphasizer = null;
        this.newEnemyOverlay = null;
        this.tooltip = null;
        this.gameOverOverlay = null;
        super.dispose();
    }
}
