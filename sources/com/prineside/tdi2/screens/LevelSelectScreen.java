package com.prineside.tdi2.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.IntSet;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.BasicLevel;
import com.prineside.tdi2.BasicLevelQuestConfig;
import com.prineside.tdi2.BasicLevelStage;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.Map;
import com.prineside.tdi2.Requirement;
import com.prineside.tdi2.Research;
import com.prineside.tdi2.Resource;
import com.prineside.tdi2.Screen;
import com.prineside.tdi2.enums.BasicLevelLootBonusType;
import com.prineside.tdi2.enums.BossType;
import com.prineside.tdi2.enums.DifficultyMode;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ItemType;
import com.prineside.tdi2.enums.RequirementType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.items.StarItem;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.LeaderBoardManager;
import com.prineside.tdi2.managers.ReplayManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.managers.preferences.categories.ProgressPrefs;
import com.prineside.tdi2.managers.preferences.categories.SettingsPrefs;
import com.prineside.tdi2.managers.preferences.categories.progress.PP_BasicLevel;
import com.prineside.tdi2.managers.preferences.categories.settings.SP_Auth;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Cell;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.ScrollPane;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.systems.GameStateSystem;
import com.prineside.tdi2.tiles.SourceTile;
import com.prineside.tdi2.ui.actors.ComplexButton;
import com.prineside.tdi2.ui.actors.FancyButton;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.LimitedWidthLabel;
import com.prineside.tdi2.ui.actors.OverlayContinueButton;
import com.prineside.tdi2.ui.actors.PaddedImageButton;
import com.prineside.tdi2.ui.actors.ParticlesCanvas;
import com.prineside.tdi2.ui.actors.QuadActor;
import com.prineside.tdi2.ui.shared.AbilitySelectionOverlay;
import com.prineside.tdi2.ui.shared.BackButton;
import com.prineside.tdi2.ui.shared.DarkOverlay;
import com.prineside.tdi2.ui.shared.Dialog;
import com.prineside.tdi2.ui.shared.InventoryOverlay;
import com.prineside.tdi2.ui.shared.ItemDescriptionDialog;
import com.prineside.tdi2.ui.shared.LeaderboardsOverlay;
import com.prineside.tdi2.ui.shared.LevelConfigurationEditor;
import com.prineside.tdi2.ui.shared.LevelStagesEditor;
import com.prineside.tdi2.ui.shared.QuestPrestigeOverlay;
import com.prineside.tdi2.ui.shared.ResourcesAndMoney;
import com.prineside.tdi2.ui.shared.ScreenTitle;
import com.prineside.tdi2.ui.shared.TooltipsOverlay;
import com.prineside.tdi2.ui.shared.WavesTimelineOverlay;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.ObjectConsumer;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.UiUtils;
import com.prineside.tdi2.utils.WaveBossSupplier;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/LevelSelectScreen.class */
public class LevelSelectScreen extends Screen {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2765a = TLog.forClass(LevelSelectScreen.class);

    /* renamed from: b, reason: collision with root package name */
    private static final Color f2766b = MaterialColor.GREY.P800;
    private static final Color c = MaterialColor.GREY.P900;
    private static final Color d = MaterialColor.GREY.P700;
    private static final Color e = MaterialColor.GREEN.P600;
    private static final Color f = MaterialColor.GREEN.P900;
    private static final Color g = MaterialColor.BLUE_GREY.P500;
    private static final Color h = MaterialColor.BLUE_GREY.P600;
    private static final Color i = MaterialColor.BLUE_GREY.P400;
    private static final Color j = MaterialColor.YELLOW.P500;
    private static final Color k = MaterialColor.YELLOW.P600;
    private static final Color l = MaterialColor.YELLOW.P400;
    private Label.LabelStyle m;
    private Label.LabelStyle n;
    private Label.LabelStyle o;
    private Label.LabelStyle p;
    private Group q;
    private ScrollPane r;
    private ParticleEffectPool s;
    private LevelMenuOverlay t;
    private ObjectMap<String, LevelElement> u;
    private boolean v;
    private final UiManager.UiLayer w;
    private final UiManager.UiLayer x;
    private final UiManager.UiLayer y;
    private final ObjectConsumer<LeaderBoardManager.BasicLevelsTopLeaderboards> z;

    public LevelSelectScreen() {
        this.u = new ObjectMap<>();
        this.w = Game.i.uiManager.addLayerIgnoreSafeArea(UiManager.MainUiLayer.SCREEN, 100, "LevelSelectScreen main", true);
        this.x = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 101, "LevelSelectScreen scroll gradients");
        this.y = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 103, "LevelSelectScreen level menu overlay");
        this.z = basicLevelsTopLeaderboards -> {
            if (basicLevelsTopLeaderboards.success) {
                ObjectMap.Entries<String, Array<LeaderBoardManager.LeaderboardsEntry>> it = basicLevelsTopLeaderboards.leaderboards.iterator();
                while (it.hasNext()) {
                    ObjectMap.Entry next = it.next();
                    if (this.u.containsKey((String) next.key)) {
                        this.u.get((String) next.key).setTopLeaderboards((Array) next.value);
                    }
                }
            }
        };
        Game.i.uiManager.hideAllComponents();
        ResourcesAndMoney.i().setVisible(true);
        InventoryOverlay.i().hideWithToggleButton(true);
        ScreenTitle.i().setText(Game.i.localeManager.i18n.get("level_select_title")).setIcon(Game.i.assetManager.getDrawable("icon-joystick")).setVisible(true);
        BackButton.i().setVisible(true).setText(null).setClickHandler(() -> {
            o();
        });
        Game.i.musicManager.continuePlayingMenuMusicTrack();
        TooltipsOverlay.i().markTagShown(MainMenuScreen.TT_FIRST_LAUNCH_LEVEL_SELECT);
        this.s = Game.i.assetManager.getParticleEffectPool("dust-n-sparks-bg.prt");
        this.m = new Label.LabelStyle(Game.i.assetManager.getFont(36), Color.WHITE);
        this.n = new Label.LabelStyle(Game.i.assetManager.getFont(24), new Color(1.0f, 1.0f, 1.0f, 0.28f));
        this.o = new Label.LabelStyle(Game.i.assetManager.getFont(36), Color.WHITE);
        this.p = new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE);
        this.q = new Group();
        this.q.setTransform(false);
        this.r = new ScrollPane(this.q);
        UiUtils.enableMouseMoveScrollFocus(this.r);
        this.r.setScrollingDisabled(true, false);
        this.w.getTable().add((Table) this.r).expand().fill();
        this.x.getTable().setTouchable(Touchable.disabled);
        Image image = new Image(Game.i.assetManager.getDrawable("gradient-top"));
        image.setColor(Config.BACKGROUND_COLOR);
        this.x.getTable().add((Table) image).fillX().height(128.0f).row();
        this.x.getTable().add().expand();
        this.t = new LevelMenuOverlay(this, (byte) 0);
        Game.i.progressManager.checkSpecialTrophiesGiven();
        Game.i.progressManager.showNewlyIssuedPrizesPopup();
    }

    public LevelSelectScreen(BasicLevel basicLevel) {
        this();
        this.t.a(basicLevel);
        this.t.a(true, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        Game.i.uiManager.setAsInputHandler();
        Game.i.uiManager.stage.setScrollFocus(this.r);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        this.u.clear();
        float scrollY = this.r.getScrollY();
        this.q.clear();
        int i2 = 0;
        float regularLayerWidth = Game.i.uiManager.getRegularLayerWidth();
        float screenSafeMargin = regularLayerWidth + (Game.i.uiManager.getScreenSafeMargin() * 2.0f);
        float height = this.w.getTable().getHeight();
        float f2 = 0.0f;
        while (true) {
            float f3 = f2;
            if (f3 + 320.0f > regularLayerWidth - 80.0f) {
                break;
            }
            i2++;
            f2 = f3 + 340.0f;
        }
        float f4 = (i2 * 320.0f) + (20.0f * (i2 - 1));
        float screenSafeMargin2 = ((regularLayerWidth - f4) / 2.0f) + Game.i.uiManager.getScreenSafeMargin();
        float f5 = 128.0f;
        if (Game.i.progressManager.isDeveloperModeEnabled()) {
            FancyButton fancyButton = new FancyButton("regularButton.a", () -> {
                LevelStagesEditor.i().show();
            });
            fancyButton.setSize(192.0f, 48.0f);
            fancyButton.add((FancyButton) new Label("Stage editor", Game.i.assetManager.getLabelStyle(21)));
            fancyButton.setPosition(screenSafeMargin2, 128.0f);
            this.q.addActor(fancyButton);
            f5 = 176.0f;
        }
        for (int i3 = 0; i3 < Game.i.basicLevelManager.stagesOrdered.size; i3++) {
            BasicLevelStage basicLevelStage = Game.i.basicLevelManager.stagesOrdered.get(i3);
            if (Game.i.basicLevelManager.isStageVisible(basicLevelStage)) {
                boolean equals = basicLevelStage.name.equals("A");
                float f6 = f5;
                if (equals) {
                    f5 += 16.0f;
                }
                Group group = new Group();
                group.setTransform(false);
                this.q.addActor(group);
                StageHeader stageHeader = new StageHeader(this, f4, basicLevelStage);
                stageHeader.setPosition(screenSafeMargin2, f5);
                this.q.addActor(stageHeader);
                f5 += stageHeader.getHeight();
                int i4 = 0;
                for (int i5 = 0; i5 < basicLevelStage.levels.size; i5++) {
                    BasicLevel basicLevel = basicLevelStage.levels.get(i5);
                    if (Game.i.basicLevelManager.isLevelVisible(basicLevel)) {
                        LevelElement levelElement = new LevelElement(basicLevel.name, false);
                        this.u.put(basicLevel.name, levelElement);
                        levelElement.setPosition(screenSafeMargin2 + (i4 * 340.0f), f5);
                        this.q.addActor(levelElement);
                        i4++;
                        if (i4 == i2) {
                            i4 = 0;
                            f5 += 260.0f;
                        }
                    }
                }
                if (basicLevelStage.name.equals("0")) {
                    boolean z = false;
                    Array<BasicLevel> array = Game.i.basicLevelManager.getStage("0").levels;
                    int i6 = 0;
                    while (true) {
                        if (i6 >= array.size) {
                            break;
                        }
                        BasicLevel basicLevel2 = array.get(i6);
                        boolean z2 = (basicLevel2.quests.size == 0 || basicLevel2.quests.get(0).isCompleted()) ? false : true;
                        if (!Game.i.basicLevelManager.isLevelVisible(basicLevel2) || !z2) {
                            i6++;
                        } else {
                            z = true;
                            break;
                        }
                    }
                    if (z) {
                        Group group2 = new Group();
                        group2.setTransform(false);
                        group2.setTouchable(Touchable.enabled);
                        group2.setPosition(screenSafeMargin2 + (i4 * 340.0f), f5);
                        group2.setSize(320.0f, 240.0f);
                        group2.setName("level_select_skip_tutorials_button");
                        this.q.addActor(group2);
                        Table table = new Table();
                        table.setSize(320.0f, 240.0f);
                        table.setBackground(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(new Color(0.0f, 0.0f, 0.0f, 0.28f)));
                        group2.addActor(table);
                        Group group3 = new Group();
                        group3.setTransform(false);
                        table.add((Table) group3).size(64.0f, 64.0f).row();
                        for (int i7 = 0; i7 < 3; i7++) {
                            Image image = new Image(this, Game.i.assetManager.getDrawable("icon-rewind")) { // from class: com.prineside.tdi2.screens.LevelSelectScreen.1
                                @Override // com.prineside.tdi2.scene2d.Actor
                                public void act(float f7) {
                                    super.act(0.011111111f);
                                }
                            };
                            image.setColor(1.0f, 1.0f, 1.0f, 0.0f);
                            image.setSize(64.0f, 64.0f);
                            image.setScale(0.5f);
                            image.setOrigin(32.0f, 32.0f);
                            image.addAction(Actions.sequence(Actions.delay(i7 * 2.0f * 0.3333f), Actions.forever(Actions.sequence(Actions.moveTo(-96.0f, 0.0f), Actions.alpha(0.0f), Actions.scaleTo(0.5f, 0.5f), Actions.parallel(Actions.moveTo(96.0f, 0.0f, 2.0f), Actions.sequence(Actions.parallel(Actions.alpha(0.28f, 1.0f), Actions.scaleTo(1.0f, 1.0f, 1.0f)), Actions.parallel(Actions.alpha(0.0f, 1.0f), Actions.scaleTo(0.5f, 0.5f, 1.0f))))))));
                            group3.addActor(image);
                        }
                        Image image2 = new Image(Game.i.assetManager.getDrawable("icon-rewind"));
                        image2.setColor(MaterialColor.LIGHT_BLUE.P500);
                        image2.setSize(64.0f, 64.0f);
                        group3.addActor(image2);
                        Label label = new Label(Game.i.localeManager.i18n.get("skip_tutorials"), Game.i.assetManager.getLabelStyle(24));
                        label.setColor(MaterialColor.LIGHT_BLUE.P500);
                        table.add((Table) label).padTop(16.0f).padLeft(40.0f).padRight(40.0f);
                        group2.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.screens.LevelSelectScreen.2
                            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                            public void clicked(InputEvent inputEvent, float f7, float f8) {
                                Dialog.i().showConfirm(Game.i.localeManager.i18n.get("skip_tutorials_confirm"), () -> {
                                    Array<BasicLevel> array2 = Game.i.basicLevelManager.getStage("0").levels;
                                    for (int i8 = 0; i8 < array2.size; i8++) {
                                        BasicLevel basicLevel3 = array2.get(i8);
                                        if (Game.i.basicLevelManager.isLevelVisible(basicLevel3)) {
                                            Game.i.basicLevelManager.setPurchased(basicLevel3);
                                            for (int i9 = 0; i9 < basicLevel3.quests.size; i9++) {
                                                basicLevel3.quests.get(i9).setCompleted(true);
                                            }
                                        }
                                    }
                                    Game.i.screenManager.goToLevelSelectScreen();
                                });
                            }
                        });
                        i4++;
                        if (i4 == i2) {
                            i4 = 0;
                            f5 += 260.0f;
                        }
                    }
                }
                if (i4 != 0) {
                    f5 += 260.0f;
                }
                if (equals) {
                    float f7 = f5 + 16.0f;
                    f5 = f7;
                    float f8 = f7 - f6;
                    group.setPosition(0.0f, f6);
                    group.setSize(screenSafeMargin, f8);
                    Image image3 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                    image3.setColor(0.05f, 0.02f, 0.0f, 0.4f);
                    image3.setSize(screenSafeMargin, f5 - f6);
                    group.addActor(image3);
                    ParticlesCanvas particlesCanvas = new ParticlesCanvas();
                    particlesCanvas.setSize(screenSafeMargin, f8);
                    particlesCanvas.scissors = true;
                    ParticleEffectPool.PooledEffect obtain = this.s.obtain();
                    Array<ParticleEmitter> emitters = obtain.getEmitters();
                    for (int i8 = 0; i8 < emitters.size; i8++) {
                        ParticleEmitter particleEmitter = emitters.get(i8);
                        particleEmitter.getSpawnWidth().setHigh(screenSafeMargin);
                        particleEmitter.getXOffsetValue().setLow((-screenSafeMargin) * 0.5f);
                        particleEmitter.getEmission().scale(screenSafeMargin / 512.0f);
                    }
                    particlesCanvas.addParticle(obtain, screenSafeMargin * 0.5f, 0.0f);
                    group.addActor(particlesCanvas);
                    QuadActor quadActor = new QuadActor(new Color(0.0f, 0.0f, 0.0f, 0.14f), new float[]{0.0f, 10.0f, 0.0f, 24.0f, screenSafeMargin, 24.0f, screenSafeMargin, 4.0f});
                    quadActor.setPosition(0.0f, (f8 - 15.0f) - 8.0f);
                    group.addActor(quadActor);
                    QuadActor quadActor2 = new QuadActor(Config.BACKGROUND_COLOR, new float[]{0.0f, 16.0f, 0.0f, 16.0f, screenSafeMargin, 16.0f, screenSafeMargin, 0.0f});
                    quadActor2.setPosition(0.0f, f8 - 15.0f);
                    group.addActor(quadActor2);
                    QuadActor quadActor3 = new QuadActor(Config.BACKGROUND_COLOR, new float[]{0.0f, 0.0f, 0.0f, 0.0f, screenSafeMargin, 16.0f, screenSafeMargin, 0.0f});
                    quadActor3.setPosition(0.0f, -1.0f);
                    group.addActor(quadActor3);
                    this.q.addActor(group);
                } else {
                    group.remove();
                }
            }
        }
        float f9 = f5 + 128.0f;
        float f10 = f9;
        if (f9 < height) {
            f10 = height;
        }
        this.q.setSize(screenSafeMargin, f10);
        Array.ArrayIterator<Actor> it = this.q.getChildren().iterator();
        while (it.hasNext()) {
            Actor next = it.next();
            next.setPosition(next.getX(), (f10 - next.getY()) - next.getHeight());
        }
        this.r.layout();
        this.r.setScrollY(scrollY);
        if (Game.i.progressManager.getDifficultyMode() == DifficultyMode.NORMAL || DifficultyMode.isEndless(Game.i.progressManager.getDifficultyMode())) {
            Game.i.leaderBoardManager.getBasicLevelsTopLeaderboards(this.z);
        }
    }

    @Override // com.prineside.tdi2.Screen, com.badlogic.gdx.Screen
    public void show() {
        super.show();
        m();
    }

    private boolean o() {
        if (AbilitySelectionOverlay.i().isVisible()) {
            AbilitySelectionOverlay.i().hide();
            return false;
        }
        if (!this.t.a()) {
            Game.i.screenManager.goToMainMenu();
            return true;
        }
        this.t.a(false, false);
        return false;
    }

    @Override // com.prineside.tdi2.Screen
    public void draw(float f2) {
        Color color = Game.i.assetManager.getColor("menu_background");
        Gdx.gl.glClearColor(color.r, color.g, color.f888b, color.f889a);
        Gdx.gl.glClear(16640);
        if (!Game.i.settingsManager.isEscButtonJustPressed() || o()) {
        }
    }

    @Override // com.prineside.tdi2.Screen, com.badlogic.gdx.Screen
    public void resize(int i2, int i3) {
        super.resize(i2, i3);
        if (i2 > 0 && i3 > 0) {
            n();
        }
    }

    @Override // com.prineside.tdi2.Screen, com.badlogic.gdx.Screen, com.badlogic.gdx.utils.Disposable
    public void dispose() {
        this.v = true;
        Game.i.uiManager.removeLayer(this.w);
        Game.i.uiManager.removeLayer(this.x);
        Game.i.uiManager.removeLayer(this.y);
        Game.i.leaderBoardManager.removeBasicLevelsTopLeaderboardsRetriever(this.z);
        this.t.dispose();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/LevelSelectScreen$StageHeader.class */
    public class StageHeader extends Group {
        StageHeader(LevelSelectScreen levelSelectScreen, float f, BasicLevelStage basicLevelStage) {
            setTransform(false);
            setSize(f, 100.0f);
            Table table = new Table();
            table.setTouchable(Touchable.disabled);
            table.setFillParent(true);
            addActor(table);
            Label label = new Label(Game.i.localeManager.i18n.get("level_stage") + SequenceUtils.SPACE + basicLevelStage.name, levelSelectScreen.m);
            label.setColor(basicLevelStage.color);
            table.add((Table) label).bottom().left().padBottom(30.0f);
            table.add((Table) new Label(basicLevelStage.getTitle(), levelSelectScreen.n)).expand().bottom().left().padLeft(16.0f).padBottom(33.0f);
            Label label2 = new Label(String.valueOf(Game.i.basicLevelManager.getGainedStarsOnStage(basicLevelStage)), levelSelectScreen.o);
            label2.setColor(basicLevelStage.color);
            table.add((Table) label2).bottom().right().padBottom(30.0f);
            Label label3 = new Label(" / " + Game.i.basicLevelManager.getMaxStars(basicLevelStage, true), levelSelectScreen.p);
            label3.setColor(basicLevelStage.color);
            label3.getColor().f889a = 0.56f;
            table.add((Table) label3).bottom().right().padBottom(33.0f);
            Image image = new Image(Game.i.assetManager.getDrawable("icon-star-stack"));
            image.setColor(basicLevelStage.color);
            table.add((Table) image).bottom().right().padLeft(16.0f).padBottom(23.0f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/LevelSelectScreen$LevelElement.class */
    public class LevelElement extends Group {
        private String l;
        private boolean m = false;
        private boolean n = false;
        private Image o;
        private Image p;
        private Group q;

        LevelElement(String str, boolean z) {
            ParticleEffectPool.PooledEffect obtain;
            setTransform(false);
            BasicLevel level = Game.i.basicLevelManager.getLevel(str);
            BasicLevelStage stage = Game.i.basicLevelManager.getStage(level.stageName);
            this.l = str;
            setSize(320.0f, 240.0f);
            boolean isOpened = Game.i.basicLevelManager.isOpened(level);
            if (!z) {
                setName("level_select_level_" + level.name);
            }
            if (z) {
                Actor image = new Image(Game.i.assetManager.getDrawable("ui-level-selection-thumb-shadow-right"));
                image.setSize(14.0f, 216.0f);
                image.setPosition(320.0f, -14.0f);
                addActor(image);
                Actor image2 = new Image(Game.i.assetManager.getDrawable("ui-level-selection-thumb-shadow-bottom"));
                image2.setSize(284.0f, 14.0f);
                image2.setPosition(36.0f, -14.0f);
                addActor(image2);
            } else {
                Actor image3 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                image3.setColor(0.0f, 0.0f, 0.0f, 0.56f);
                image3.setSize(320.0f, 240.0f);
                image3.setPosition(10.0f, -10.0f);
                addActor(image3);
            }
            this.o = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
            this.o.setSize(320.0f, 240.0f);
            addActor(this.o);
            Actor image4 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
            image4.setSize(310.0f, 230.0f);
            image4.setColor(Config.BACKGROUND_COLOR);
            image4.setPosition(5.0f, 5.0f);
            addActor(image4);
            Actor image5 = new Image(level.getPreview());
            image5.setSize(310.0f, 230.0f);
            image5.setPosition(5.0f, 5.0f);
            addActor(image5);
            Actor image6 = new Image(Game.i.assetManager.getDrawable("ui-level-selection-thumb-inner-shadow"));
            image6.setSize(310.0f, 185.0f);
            image6.setPosition(5.0f, 5.0f);
            addActor(image6);
            if (!isOpened) {
                Actor image7 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                image7.setSize(310.0f, 230.0f);
                image7.setPosition(5.0f, 5.0f);
                image7.setColor(Config.BACKGROUND_COLOR);
                image7.getColor().f889a = 0.9f;
                addActor(image7);
            }
            if (isOpened && !Game.i.basicLevelManager.playedBefore(level)) {
                Actor image8 = new Image(Game.i.assetManager.getDrawable("ui-level-selection-new-level-tag"));
                image8.setSize(92.0f, 92.0f);
                image8.setPosition(228.0f, 148.0f);
                addActor(image8);
            }
            PP_BasicLevel.LevelLootBonus levelLootBonus = ProgressPrefs.i().basicLevel.getLevelLootBonus(str);
            if (levelLootBonus != null) {
                if (levelLootBonus.multiplier >= 3.4f) {
                    ParticlesCanvas particlesCanvas = new ParticlesCanvas();
                    particlesCanvas.setSize(320.0f, 240.0f);
                    addActor(particlesCanvas);
                    if (levelLootBonus.type == BasicLevelLootBonusType.GREEN_PAPERS) {
                        obtain = Game.i.assetManager.getParticleEffectPool("level-preview-loot-papers.p").obtain();
                    } else {
                        obtain = Game.i.assetManager.getParticleEffectPool("level-preview-loot.p").obtain();
                        switch (levelLootBonus.type) {
                            case RESOURCE_SCALAR:
                                obtain.getEmitters().first().setSprites(new Array<>(new Sprite[]{new Sprite(Game.i.assetManager.getTextureRegion("resource-orb-scalar"))}));
                                break;
                            case RESOURCE_VECTOR:
                                obtain.getEmitters().first().setSprites(new Array<>(new Sprite[]{new Sprite(Game.i.assetManager.getTextureRegion("resource-orb-vector"))}));
                                break;
                            case RESOURCE_MATRIX:
                                obtain.getEmitters().first().setSprites(new Array<>(new Sprite[]{new Sprite(Game.i.assetManager.getTextureRegion("resource-orb-matrix"))}));
                                break;
                            case RESOURCE_TENSOR:
                                obtain.getEmitters().first().setSprites(new Array<>(new Sprite[]{new Sprite(Game.i.assetManager.getTextureRegion("resource-orb-tensor"))}));
                                break;
                            case RESOURCE_INFIAR:
                                obtain.getEmitters().first().setSprites(new Array<>(new Sprite[]{new Sprite(Game.i.assetManager.getTextureRegion("resource-orb-infiar"))}));
                                break;
                            case BIT_DUST:
                                obtain.getEmitters().first().setSprites(new Array<>(new Sprite[]{new Sprite(Game.i.assetManager.getTextureRegion("dust-item"))}));
                                break;
                        }
                    }
                    if (obtain != null) {
                        particlesCanvas.addParticle(obtain, 0.0f, 240.0f);
                    }
                }
                Actor image9 = new Image(Game.i.assetManager.getQuad("screen.levelSelect.lootBoostOverlay"));
                image9.setPosition(203.0f, 186.0f);
                addActor(image9);
                Actor image10 = new Image(levelLootBonus.getIconDrawable());
                image10.setSize(32.0f, 32.0f);
                image10.setPosition(227.0f, 199.0f);
                addActor(image10);
                Label label = new Label("x" + ((Object) StringFormatter.compactNumberWithPrecisionTrimZeros(levelLootBonus.multiplier, 1, true)), Game.i.assetManager.getLabelStyle(24));
                label.setPosition(255.0f, 204.0f);
                label.setSize(56.0f, 20.0f);
                label.setAlignment(1);
                label.setColor(MaterialColor.LIGHT_GREEN.P400);
                addActor(label);
            }
            Table table = new Table();
            table.setPosition(16.0f, 18.0f);
            table.setSize(304.0f, 26.0f);
            addActor(table);
            Label label2 = new Label(level.name, new Label.LabelStyle(Game.i.assetManager.getFont(36), Color.WHITE));
            table.add((Table) label2).bottom().left();
            if (level.isBonus) {
                table.add((Table) new Label("bonus", new Label.LabelStyle(Game.i.assetManager.getFont(21), MaterialColor.AMBER.P500))).bottom().left().padLeft(8.0f).padBottom(4.0f);
            }
            table.add().expandX().fillX();
            if (!isOpened) {
                label2.setColor(1.0f, 1.0f, 1.0f, 0.2f);
            }
            if (isOpened) {
                int gainedStarsOnLevel = Game.i.basicLevelManager.getGainedStarsOnLevel(level);
                Actor image11 = new Image(Game.i.assetManager.getDrawable("icon-star"));
                image11.setSize(32.0f, 32.0f);
                image11.setPosition(202.0f, 21.0f);
                if (gainedStarsOnLevel >= 3) {
                    image11.setColor(stage.color);
                } else {
                    image11.setColor(1.0f, 1.0f, 1.0f, 0.14f);
                }
                addActor(image11);
                Actor image12 = new Image(Game.i.assetManager.getDrawable("icon-star"));
                image12.setSize(32.0f, 32.0f);
                image12.setPosition(236.0f, 21.0f);
                if (gainedStarsOnLevel >= 2) {
                    image12.setColor(stage.color);
                } else {
                    image12.setColor(1.0f, 1.0f, 1.0f, 0.14f);
                }
                addActor(image12);
                Actor image13 = new Image(Game.i.assetManager.getDrawable("icon-star"));
                image13.setSize(32.0f, 32.0f);
                image13.setPosition(270.0f, 21.0f);
                if (gainedStarsOnLevel > 0) {
                    image13.setColor(stage.color);
                } else {
                    image13.setColor(1.0f, 1.0f, 1.0f, 0.14f);
                }
                addActor(image13);
                Label label3 = new Label(StringFormatter.commaSeparatedNumber(ProgressPrefs.i().basicLevel.getLevelMaxScore(str)), new Label.LabelStyle(Game.i.assetManager.getFont(21), Color.WHITE));
                label3.setSize(100.0f, 20.0f);
                label3.setPosition(200.0f, 90.0f);
                label3.setAlignment(20);
                addActor(label3);
                Label label4 = new Label(String.valueOf(ProgressPrefs.i().basicLevel.getLevelMaxReachedWave(str)), new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
                label4.setSize(62.0f, 20.0f);
                label4.setPosition(200.0f, 56.0f);
                label4.setAlignment(20);
                addActor(label4);
                Actor image14 = new Image(Game.i.assetManager.getDrawable("icon-wave"));
                image14.setSize(32.0f, 32.0f);
                image14.setPosition(270.0f, 55.0f);
                addActor(image14);
                this.q = new Group();
                this.q.setSize(160.0f, 240.0f);
                this.q.setTransform(false);
                this.q.setVisible(false);
                addActor(this.q);
            } else {
                Table table2 = new Table();
                table2.setFillParent(true);
                addActor(table2);
                for (int i = 0; i < level.openRequirements.size; i++) {
                    Requirement requirement = level.openRequirements.get(i);
                    if (requirement.type == RequirementType.STAGE_STARS) {
                        Table table3 = new Table();
                        BasicLevelStage stage2 = Game.i.basicLevelManager.getStage(requirement.stageName);
                        int gainedStarsOnStage = Game.i.basicLevelManager.getGainedStarsOnStage(stage2);
                        Label label5 = new Label(gainedStarsOnStage + " / ", new Label.LabelStyle(Game.i.assetManager.getFont(36), Color.WHITE));
                        if (requirement.stageStars > gainedStarsOnStage) {
                            label5.setColor(1.0f, 1.0f, 1.0f, 0.28f);
                        } else {
                            label5.setColor(stage2.color);
                        }
                        table3.add((Table) label5);
                        table3.add((Table) new Label(String.valueOf(requirement.stageStars), new Label.LabelStyle(Game.i.assetManager.getFont(36), stage2.color)));
                        Image image15 = new Image(Game.i.assetManager.getDrawable("icon-star-stack"));
                        image15.setColor(stage2.color);
                        table3.add((Table) image15).size(48.0f).padLeft(8.0f);
                        table2.add(table3).padBottom(8.0f).row();
                    }
                }
                Table table4 = new Table();
                for (int i2 = 0; i2 < level.openRequirements.size; i2++) {
                    Requirement requirement2 = level.openRequirements.get(i2);
                    if (requirement2.type != RequirementType.STAGE_STARS) {
                        Image image16 = new Image(Game.i.assetManager.getDrawable(requirement2.getIconTextureName()));
                        table4.add((Table) image16).size(32.0f).padLeft(4.0f).padRight(4.0f);
                        if (requirement2.isSatisfied()) {
                            image16.setColor(stage.color);
                        } else {
                            image16.setColor(1.0f, 1.0f, 1.0f, 0.28f);
                        }
                    }
                }
                if (table4.hasChildren()) {
                    table2.add(table4).padBottom(8.0f).row();
                }
                Table table5 = new Table();
                for (int i3 = 0; i3 < level.priceInResources.length; i3++) {
                    if (level.priceInResources[i3] > 0) {
                        Image image17 = new Image(Game.i.assetManager.getDrawable(Resource.TEXTURE_REGION_NAMES[i3]));
                        table5.add((Table) image17).size(32.0f).padLeft(4.0f).padRight(4.0f);
                        if (level.priceInResources[i3] > Game.i.progressManager.getResources(ResourceType.values[i3])) {
                            image17.setColor(1.0f, 1.0f, 1.0f, 0.28f);
                        } else {
                            image17.setColor(stage.color);
                        }
                    }
                }
                if (level.priceInMoney > 0) {
                    Image image18 = new Image(Game.i.assetManager.getDrawable("icon-money"));
                    table5.add((Table) image18).size(32.0f).padLeft(4.0f).padRight(4.0f);
                    if (level.priceInMoney > Game.i.progressManager.getGreenPapers()) {
                        image18.setColor(1.0f, 1.0f, 1.0f, 0.28f);
                    } else {
                        image18.setColor(stage.color);
                    }
                }
                table2.add(table5);
                Array<Research> instances = Game.i.researchManager.getInstances();
                int i4 = 0;
                while (true) {
                    if (i4 < instances.size) {
                        Research research = instances.items[i4];
                        if (research.isUnlocksTower() && research.getInstalledLevel() == 0) {
                            for (Requirement requirement3 : research.levels[0].requirements) {
                                if (requirement3.type == RequirementType.OPENED_LEVEL && requirement3.openedLevelName.equals(level.name)) {
                                    Actor image19 = new Image(Game.i.towerManager.getFactory(research.relatedToTowerType).getShadowTextures());
                                    image19.setPosition(238.0f, -14.0f);
                                    image19.setSize(96.0f, 96.0f);
                                    image19.setColor(0.0f, 0.0f, 0.0f, 0.28f);
                                    addActor(image19);
                                    Actor image20 = new Image(Game.i.towerManager.getFactory(research.relatedToTowerType).getIconTexture());
                                    image20.setSize(96.0f, 96.0f);
                                    image20.setPosition(238.0f, -14.0f);
                                    addActor(image20);
                                }
                            }
                        }
                        i4++;
                    }
                }
                Actor image21 = new Image(Game.i.assetManager.getDrawable("icon-lock-vertical"));
                image21.setSize(48.0f, 48.0f);
                image21.setPosition(141.0f, -17.0f);
                image21.setColor(0.0f, 0.0f, 0.0f, 0.56f);
                addActor(image21);
                this.p = new Image(Game.i.assetManager.getDrawable("icon-lock-vertical"));
                this.p.setSize(48.0f, 48.0f);
                this.p.setPosition(136.0f, -12.0f);
                addActor(this.p);
            }
            if (!z) {
                boolean z2 = false;
                SP_Auth.SessionData sessionData = SettingsPrefs.i().auth.sessionData;
                int i5 = 0;
                while (true) {
                    if (i5 < sessionData.xpPlayedLevels.size) {
                        if (!sessionData.xpPlayedLevels.items[i5].equals(level.name)) {
                            i5++;
                        } else {
                            z2 = true;
                        }
                    }
                }
                int i6 = 0;
                while (true) {
                    if (i6 < Game.i.authManager.localXpPlayedLevels.size) {
                        if (!Game.i.authManager.localXpPlayedLevels.items[i6].equals(level.name)) {
                            i6++;
                        } else {
                            z2 = true;
                        }
                    }
                }
                boolean equals = level.name.equals(sessionData.bonusXpLevel);
                if (z2) {
                    Actor quadActor = new QuadActor(MaterialColor.ORANGE.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.28f), new float[]{0.0f, 0.0f, 0.0f, 32.0f, 100.0f, 32.0f, 92.0f, 0.0f});
                    quadActor.setPosition(5.0f, 58.0f);
                    addActor(quadActor);
                    Actor label6 = new Label("-" + MathUtils.round((1.0f - sessionData.playedLevelXpCoeff) * 100.0f) + "% XP", Game.i.assetManager.getLabelStyle(21));
                    label6.setColor(MaterialColor.ORANGE.P500);
                    label6.setPosition(16.0f, 58.0f);
                    label6.setSize(90.0f, 32.0f);
                    addActor(label6);
                } else if (equals) {
                    Actor quadActor2 = new QuadActor(MaterialColor.LIGHT_GREEN.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.4f), new float[]{0.0f, 0.0f, 0.0f, 32.0f, 100.0f, 32.0f, 92.0f, 0.0f});
                    quadActor2.setPosition(5.0f, 58.0f);
                    addActor(quadActor2);
                    Actor label7 = new Label("+" + MathUtils.round((sessionData.bonusLevelXpCoeff - 1.0f) * 100.0f) + "% XP", Game.i.assetManager.getLabelStyle(21));
                    label7.setColor(Color.WHITE);
                    label7.setPosition(16.0f, 58.0f);
                    label7.setSize(90.0f, 32.0f);
                    label7.addAction(Actions.forever(Actions.sequence(Actions.color(Color.WHITE, 0.6f), Actions.color(MaterialColor.LIGHT_GREEN.P500, 0.6f))));
                    addActor(label7);
                }
            }
            if (!z) {
                setTouchable(Touchable.enabled);
                addListener(new ClickListener(LevelSelectScreen.this, level) { // from class: com.prineside.tdi2.screens.LevelSelectScreen.LevelElement.1

                    /* renamed from: a, reason: collision with root package name */
                    private /* synthetic */ BasicLevel f2768a;

                    {
                        this.f2768a = level;
                    }

                    @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                    public void clicked(InputEvent inputEvent, float f, float f2) {
                        Game.i.soundManager.playStatic(StaticSoundType.BUTTON);
                        LevelSelectScreen.this.t.a(this.f2768a);
                        LevelSelectScreen.this.t.a(true, false);
                    }

                    @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
                    public boolean touchDown(InputEvent inputEvent, float f, float f2, int i7, int i8) {
                        LevelElement.this.m = true;
                        LevelElement.this.e();
                        return super.touchDown(inputEvent, f, f2, i7, i8);
                    }

                    @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
                    public void touchUp(InputEvent inputEvent, float f, float f2, int i7, int i8) {
                        LevelElement.this.m = false;
                        LevelElement.this.e();
                        super.touchUp(inputEvent, f, f2, i7, i8);
                    }

                    @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
                    public void enter(InputEvent inputEvent, float f, float f2, int i7, Actor actor) {
                        if (i7 == -1) {
                            LevelElement.this.n = true;
                            LevelElement.this.e();
                        }
                        super.enter(inputEvent, f, f2, i7, actor);
                    }

                    @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
                    public void exit(InputEvent inputEvent, float f, float f2, int i7, Actor actor) {
                        if (i7 == -1) {
                            LevelElement.this.n = false;
                            LevelElement.this.e();
                        }
                        super.exit(inputEvent, f, f2, i7, actor);
                    }
                });
            }
            e();
        }

        public void setTopLeaderboards(Array<LeaderBoardManager.LeaderboardsEntry> array) {
            if (this.q != null && array.size != 0) {
                this.q.setVisible(true);
                this.q.clearChildren();
                Image image = new Image(Game.i.assetManager.getDrawable("icon-crown"));
                image.setSize(32.0f, 32.0f);
                image.setPosition(16.0f, 196.0f);
                this.q.addActor(image);
                for (int i = 0; i < array.size; i++) {
                    LeaderBoardManager.LeaderboardsEntry leaderboardsEntry = array.get(i);
                    float f = 1.0f - (i * 0.25f);
                    LimitedWidthLabel limitedWidthLabel = new LimitedWidthLabel(leaderboardsEntry.nickname, 21, 21, 240.0f);
                    limitedWidthLabel.setColor(0.0f, 0.0f, 0.0f, 0.56f * f);
                    limitedWidthLabel.setPosition(59.0f, (198.0f - (i * 26.0f)) - 2.0f);
                    this.q.addActor(limitedWidthLabel);
                    LimitedWidthLabel limitedWidthLabel2 = new LimitedWidthLabel(leaderboardsEntry.nickname, 21, 21, 240.0f);
                    limitedWidthLabel2.setPosition(57.0f, 198.0f - (i * 26.0f));
                    limitedWidthLabel2.setColor(1.0f, 1.0f, 1.0f, f);
                    this.q.addActor(limitedWidthLabel2);
                }
            }
        }

        private BasicLevel d() {
            return Game.i.basicLevelManager.getLevel(this.l);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void e() {
            this.o.clearActions();
            BasicLevel d = d();
            if (Game.i.basicLevelManager.isOpened(d)) {
                if (Game.i.basicLevelManager.isMastered(d)) {
                    if (this.m) {
                        this.o.setColor(LevelSelectScreen.k);
                        return;
                    } else if (this.n) {
                        this.o.setColor(LevelSelectScreen.l);
                        return;
                    } else {
                        this.o.setColor(LevelSelectScreen.j);
                        return;
                    }
                }
                if (this.m) {
                    this.o.setColor(LevelSelectScreen.h);
                    return;
                } else if (this.n) {
                    this.o.setColor(LevelSelectScreen.i);
                    return;
                } else {
                    this.o.setColor(LevelSelectScreen.g);
                    return;
                }
            }
            if (this.m) {
                this.o.setColor(LevelSelectScreen.c);
                return;
            }
            if (this.n) {
                this.o.setColor(LevelSelectScreen.d);
            } else if (!Game.i.basicLevelManager.canBePurchased(d)) {
                this.o.setColor(LevelSelectScreen.f2766b);
                this.p.setColor(LevelSelectScreen.f2766b);
            } else {
                this.o.addAction(Actions.forever(Actions.sequence(Actions.color(LevelSelectScreen.e, 0.5f), Actions.color(LevelSelectScreen.f, 0.5f))));
                this.p.addAction(Actions.forever(Actions.sequence(Actions.color(LevelSelectScreen.e, 0.5f), Actions.color(LevelSelectScreen.f, 0.5f))));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/LevelSelectScreen$LevelMenuOverlay.class */
    public class LevelMenuOverlay implements Disposable {

        /* renamed from: b, reason: collision with root package name */
        private boolean f2770b;

        @Null
        private GameSystemProvider c;
        private Group d;

        /* synthetic */ LevelMenuOverlay(LevelSelectScreen levelSelectScreen, byte b2) {
            this();
        }

        private LevelMenuOverlay() {
            Group group = new Group();
            group.setTransform(false);
            group.setOrigin(600.0f, 380.0f);
            LevelSelectScreen.this.y.getTable().add((Table) group).size(1200.0f, 760.0f);
            this.d = new Group();
            this.d.setTransform(true);
            this.d.setOrigin(600.0f, 380.0f);
            this.d.setSize(1200.0f, 760.0f);
            group.addActor(this.d);
            LevelSelectScreen.this.y.getTable().setVisible(false);
            this.f2770b = false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        public void a(BasicLevel basicLevel) {
            OverlayContinueButton overlayContinueButton;
            Image image;
            OverlayContinueButton overlayContinueButton2;
            BasicLevelStage stage = Game.i.basicLevelManager.getStage(basicLevel.stageName);
            this.d.clearChildren();
            try {
                this.c = new GameSystemProvider(new GameSystemProvider.SystemsConfig(GameSystemProvider.SystemsConfig.Setup.GAME, true).disableScripts());
                this.c.createSystems();
                GameScreen.configureSystemsBeforeSetup(this.c, null, false, false, false, 0L, basicLevel, null, Game.i.progressManager.getDifficultyMode(), Game.i.progressManager.getDifficultyModeDiffMultiplier(Game.i.progressManager.getDifficultyMode()), GameStateSystem.GameMode.BASIC_LEVELS, null, Game.i.progressManager.createProgressSnapshotForState(), Game.i.progressManager.getInventoryStatistics(), null);
                this.c.setupSystems();
            } catch (Exception e) {
                this.c = null;
                LevelSelectScreen.f2765a.e("failed to create GSP for level menu", e);
            }
            this.d.addActor(new QuadActor(new Color(791621631), new float[]{0.0f, 22.0f, 10.0f, 748.0f, 1200.0f, 760.0f, 1200.0f, 0.0f}));
            LevelElement levelElement = new LevelElement(basicLevel.name, true);
            levelElement.setPosition(-29.0f, 549.0f);
            this.d.addActor(levelElement);
            Label label = new Label(Game.i.localeManager.i18n.get("level") + SequenceUtils.SPACE + basicLevel.name, new Label.LabelStyle(Game.i.assetManager.getFont(36), Color.WHITE));
            label.setPosition(330.0f, 662.0f);
            label.setColor(stage.color);
            this.d.addActor(label);
            if (Game.i.basicLevelManager.mapEditingAvailable()) {
                PaddedImageButton paddedImageButton = new PaddedImageButton(Game.i.assetManager.getDrawable("icon-tools"), () -> {
                    LevelConfigurationEditor.i().showForLevel(basicLevel);
                    a(false, false);
                }, MaterialColor.PURPLE.P700, MaterialColor.PURPLE.P600, MaterialColor.PURPLE.P800);
                paddedImageButton.setSize(64.0f, 64.0f);
                paddedImageButton.setIconSize(48.0f, 48.0f);
                paddedImageButton.setIconPosition(8.0f, 8.0f);
                paddedImageButton.setPosition(1020.0f, 646.0f);
                this.d.addActor(paddedImageButton);
                PaddedImageButton paddedImageButton2 = new PaddedImageButton(Game.i.assetManager.getDrawable("icon-edit"), () -> {
                    Game.i.screenManager.goToMapEditorScreenBasicLevel(basicLevel);
                }, MaterialColor.LIGHT_BLUE.P800, MaterialColor.LIGHT_BLUE.P700, MaterialColor.LIGHT_BLUE.P900);
                paddedImageButton2.setSize(64.0f, 64.0f);
                paddedImageButton2.setIconSize(48.0f, 48.0f);
                paddedImageButton2.setIconPosition(8.0f, 8.0f);
                paddedImageButton2.setPosition(1100.0f, 646.0f);
                this.d.addActor(paddedImageButton2);
            }
            if (Game.i.basicLevelManager.isOpened(basicLevel)) {
                int gainedStarsOnLevel = Game.i.basicLevelManager.getGainedStarsOnLevel(basicLevel);
                int[] starMilestoneWaves = basicLevel.getStarMilestoneWaves();
                for (int i = 0; i < 3; i++) {
                    Image image2 = new Image(Game.i.assetManager.getDrawable("icon-star"));
                    image2.setPosition(50.0f + (66.0f * i), 460.0f);
                    image2.setSize(64.0f, 64.0f);
                    if (gainedStarsOnLevel >= 3 - i) {
                        image2.setColor(stage.color);
                    } else {
                        image2.setColor(new Color(488447487));
                    }
                    this.d.addActor(image2);
                    if (gainedStarsOnLevel < 3 - i && starMilestoneWaves[2 - i] != 0) {
                        Label label2 = new Label(String.valueOf(starMilestoneWaves[2 - i]), new Label.LabelStyle(Game.i.assetManager.getFont(21), Color.WHITE));
                        label2.setSize(64.0f, 64.0f);
                        label2.setPosition(50.0f + (66.0f * i), 457.0f);
                        label2.setAlignment(1);
                        this.d.addActor(label2);
                    }
                }
                Table table = new Table();
                table.setPosition(0.0f, 400.0f);
                table.setSize(300.0f, 48.0f);
                this.d.addActor(table);
                table.add((Table) new Image(Game.i.assetManager.getDrawable("icon-wave"))).size(48.0f).padRight(12.0f);
                table.add((Table) new Label(String.valueOf(ProgressPrefs.i().basicLevel.getLevelMaxReachedWave(basicLevel.name)), new Label.LabelStyle(Game.i.assetManager.getFont(36), Color.WHITE)));
                Table table2 = new Table();
                table2.setPosition(0.0f, 354.0f);
                table2.setSize(300.0f, 48.0f);
                this.d.addActor(table2);
                table2.add((Table) new Image(Game.i.assetManager.getDrawable("icon-trophy"))).size(32.0f).padRight(12.0f);
                table2.add((Table) new Label(StringFormatter.commaSeparatedNumber(ProgressPrefs.i().basicLevel.getLevelMaxScore(basicLevel.name)), new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE)));
                if (Game.i.minerManager.minersAndEnergyAvailable()) {
                    Label label3 = new Label(Game.i.localeManager.i18n.get("resources").toUpperCase(), new Label.LabelStyle(Game.i.assetManager.getFont(21), Color.WHITE));
                    label3.setPosition(0.0f, 318.0f);
                    label3.setSize(300.0f, 20.0f);
                    label3.setAlignment(4);
                    label3.setColor(1.0f, 1.0f, 1.0f, 0.28f);
                    this.d.addActor(label3);
                    Map map = basicLevel.getMap();
                    int i2 = 0;
                    Array array = new Array(true, 1, SourceTile.class);
                    array.addAll(map.getTilesByType(SourceTile.class));
                    array.sort((sourceTile, sourceTile2) -> {
                        int i3 = 0;
                        int i4 = 0;
                        for (int i5 = 0; i5 < ResourceType.values.length; i5++) {
                            i3 += sourceTile.getResourcesCount(ResourceType.values[i5]);
                            i4 += sourceTile2.getResourcesCount(ResourceType.values[i5]);
                        }
                        if (i3 == 0) {
                            i3 = 1;
                        }
                        if (i4 == 0) {
                            i4 = 1;
                        }
                        float f = 0.0f;
                        float f2 = 0.0f;
                        for (int i6 = 0; i6 < ResourceType.values.length; i6++) {
                            f += (sourceTile.getResourcesCount(ResourceType.values[i6]) / i3) * ((i6 << 1) + 1);
                            f2 += (sourceTile2.getResourcesCount(ResourceType.values[i6]) / i4) * ((i6 << 1) + 1);
                        }
                        return Float.compare(f2 * sourceTile2.getResourceDensity(), f * sourceTile.getResourceDensity());
                    });
                    for (int i3 = 0; i3 < array.size; i3++) {
                        SourceTile sourceTile3 = ((SourceTile[]) array.items)[i3];
                        float resourceDensity = sourceTile3.getResourceDensity();
                        int i4 = 0;
                        for (ResourceType resourceType : ResourceType.values) {
                            i4 += sourceTile3.getResourcesCount(resourceType);
                        }
                        Image image3 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                        image3.setPosition(40.0f, 293.0f - (i2 * 10.0f));
                        image3.setSize(220.0f, 8.0f);
                        image3.setColor(0.0f, 0.0f, 0.0f, 0.28f);
                        this.d.addActor(image3);
                        float f = 0.0f;
                        for (ResourceType resourceType2 : ResourceType.values) {
                            int resourcesCount = sourceTile3.getResourcesCount(resourceType2);
                            if (resourcesCount > 0) {
                                float f2 = resourcesCount / i4;
                                Image image4 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                                image4.setSize(resourceDensity * f2 * 220.0f, 8.0f);
                                image4.setPosition(f + 40.0f, 293.0f - (i2 * 10.0f));
                                image4.setColor(Game.i.resourceManager.getColor(resourceType2));
                                this.d.addActor(image4);
                                if (f > 0.0f) {
                                    Image image5 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                                    image5.setSize(2.0f, 8.0f);
                                    image5.setPosition((int) (f + 40.0f), 293.0f - (i2 * 10.0f));
                                    image5.setColor(new Color(791621631));
                                    this.d.addActor(image5);
                                }
                                f += f2 * resourceDensity * 220.0f;
                            }
                        }
                        i2++;
                        if (i2 == 16) {
                            break;
                        }
                    }
                }
                if (basicLevel.hasLeaderboards && ((Game.i.progressManager.getDifficultyMode() == DifficultyMode.NORMAL || DifficultyMode.isEndless(Game.i.progressManager.getDifficultyMode())) && Game.i.authManager.isSignedIn())) {
                    Table table3 = new Table();
                    table3.setPosition(0.0f, 104.0f);
                    table3.setSize(300.0f, 24.0f);
                    this.d.addActor(table3);
                    Label label4 = new Label(Game.i.localeManager.i18n.get("level_selection_overlay_xp_gain").toUpperCase(), new Label.LabelStyle(Game.i.assetManager.getFont(21), Color.WHITE));
                    label4.setAlignment(1);
                    label4.setColor(1.0f, 1.0f, 1.0f, 0.28f);
                    table3.add((Table) label4).height(24.0f);
                    final Image image6 = new Image(Game.i.assetManager.getDrawable("icon-info-circle"));
                    image6.setColor(1.0f, 1.0f, 1.0f, 0.28f);
                    table3.add((Table) image6).size(24.0f).padLeft(6.0f);
                    boolean z = false;
                    SP_Auth.SessionData sessionData = SettingsPrefs.i().auth.sessionData;
                    int i5 = 0;
                    while (true) {
                        if (i5 >= sessionData.xpPlayedLevels.size) {
                            break;
                        }
                        if (!sessionData.xpPlayedLevels.items[i5].equals(basicLevel.name)) {
                            i5++;
                        } else {
                            z = true;
                            break;
                        }
                    }
                    int i6 = 0;
                    while (true) {
                        if (i6 >= Game.i.authManager.localXpPlayedLevels.size) {
                            break;
                        }
                        if (!Game.i.authManager.localXpPlayedLevels.items[i6].equals(basicLevel.name)) {
                            i6++;
                        } else {
                            z = true;
                            break;
                        }
                    }
                    final boolean equals = basicLevel.name.equals(sessionData.bonusXpLevel);
                    Color color = Color.WHITE;
                    int i7 = 100;
                    if (z) {
                        i7 = MathUtils.round(sessionData.playedLevelXpCoeff * 100.0f);
                        color = MaterialColor.ORANGE.P500;
                    } else if (equals) {
                        i7 = MathUtils.round(sessionData.bonusLevelXpCoeff * 100.0f);
                        color = MaterialColor.LIGHT_GREEN.P500;
                    }
                    if (DifficultyMode.isEndless(Game.i.progressManager.getDifficultyMode())) {
                        i7 = MathUtils.round(i7 * 0.5f);
                    }
                    Label label5 = new Label(i7 + "% XP", Game.i.assetManager.getLabelStyle(36));
                    label5.setColor(color);
                    label5.setTouchable(Touchable.disabled);
                    label5.setAlignment(4);
                    label5.setPosition(0.0f, 64.0f);
                    label5.setSize(300.0f, 20.0f);
                    this.d.addActor(label5);
                    Group group = new Group();
                    group.setTransform(false);
                    group.setTouchable(Touchable.enabled);
                    final boolean z2 = z;
                    group.addListener(new ClickListener() { // from class: com.prineside.tdi2.screens.LevelSelectScreen.LevelMenuOverlay.1
                        @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                        public void clicked(InputEvent inputEvent, float f3, float f4) {
                            String str = Game.i.localeManager.i18n.get("level_selection_overlay_xp_tooltip");
                            if (DifficultyMode.isEndless(Game.i.progressManager.getDifficultyMode())) {
                                str = ((Object) str) + SequenceUtils.EOL + Game.i.localeManager.i18n.format("level_selection_overlay_xp_tooltip_endless", StringFormatter.timePassed(MathUtils.round(2700.0f), false, false));
                            }
                            if (z2) {
                                str = ((Object) str) + "\n[#FFB74D]" + Game.i.localeManager.i18n.get("level_selection_overlay_xp_status_played") + "[]";
                            } else if (equals) {
                                str = ((Object) str) + "\n[#AED581]" + Game.i.localeManager.i18n.get("level_selection_overlay_xp_status_bonus") + "[]";
                            }
                            TooltipsOverlay.i().showText(TooltipsOverlay.TAG_GENERIC_TOOLTIP, image6, str, LevelSelectScreen.this.y.mainUiLayer, LevelSelectScreen.this.y.zIndex + 1, 2);
                        }
                    });
                    group.setPosition(30.0f, 30.0f);
                    group.setSize(240.0f, 120.0f);
                    this.d.addActor(group);
                }
                if (basicLevel.waveQuests.size != 0) {
                    WaveQuestsLine waveQuestsLine = new WaveQuestsLine(this, basicLevel, (byte) 0);
                    waveQuestsLine.setPosition(330.0f, 476.0f);
                    this.d.addActor(waveQuestsLine);
                } else {
                    Label label6 = new Label(Game.i.localeManager.i18n.get("level_has_no_wave_milestones"), Game.i.assetManager.getLabelStyle(24));
                    label6.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                    label6.setSize(870.0f, 176.0f);
                    label6.setPosition(330.0f, 476.0f);
                    label6.setAlignment(1);
                    this.d.addActor(label6);
                }
                Label label7 = new Label(Game.i.localeManager.i18n.get("enemies").toUpperCase(), new Label.LabelStyle(Game.i.assetManager.getFont(21), Color.WHITE));
                label7.setColor(1.0f, 1.0f, 1.0f, 0.28f);
                label7.setPosition(330.0f, 440.0f);
                this.d.addActor(label7);
                for (int i8 = 0; i8 < basicLevel.getAllowedEnemies().size; i8++) {
                    Image image7 = new Image(Game.i.enemyManager.getFactory(basicLevel.getAllowedEnemies().get(i8)).getTexture());
                    image7.setSize(40.0f, 40.0f);
                    image7.setPosition(327.0f + (i8 * 54.0f), 386.0f);
                    this.d.addActor(image7);
                }
                DifficultyMode difficultyMode = Game.i.progressManager.getDifficultyMode();
                if (basicLevel.forcedDifficulty != null) {
                    difficultyMode = basicLevel.forcedDifficulty;
                }
                DifficultyMode difficultyMode2 = difficultyMode;
                ComplexButton complexButton = new ComplexButton(Game.i.localeManager.i18n.get("waves"), Game.i.assetManager.getLabelStyle(24), () -> {
                    Map cpy = basicLevel.getMap().cpy();
                    cpy.multiplyPortalsDifficulty(Game.i.progressManager.getDifficultyModeDiffMultiplier(difficultyMode2) * 0.01f);
                    try {
                        WavesTimelineOverlay.i().setConfiguration(this.c.wave.generateWavesTimelineConfigurationBasicLevel(basicLevel, cpy, 1));
                        WavesTimelineOverlay.i().setVisible(true);
                    } catch (Exception e2) {
                        throw new IllegalStateException("Failed to show wavesTimelineOverlay for level " + basicLevel.name, e2);
                    }
                });
                complexButton.setBackground(Game.i.assetManager.getDrawable("ui-level-selection-waves-timeline-button"), 0.0f, 0.0f, 193.0f, 76.0f);
                complexButton.setSize(193.0f, 76.0f);
                complexButton.setIconPositioned(Game.i.assetManager.getDrawable("icon-wave"), 20.0f, 28.0f, 32.0f, 32.0f);
                complexButton.setLabel(68.0f, 34.0f, 100.0f, 19.0f, 8);
                complexButton.setPosition(1027.0f, 370.0f);
                if (basicLevel.customWaves) {
                    complexButton.setVisible(false);
                }
                if (this.c != null) {
                    this.d.addActor(complexButton);
                }
                if (basicLevel.quests.size != 0) {
                    Label label8 = new Label(Game.i.localeManager.i18n.get("quests").toUpperCase(), new Label.LabelStyle(Game.i.assetManager.getFont(21), Color.WHITE));
                    label8.setColor(1.0f, 1.0f, 1.0f, 0.28f);
                    label8.setPosition(330.0f, 348.0f);
                    label8.setSize(100.0f, 21.0f);
                    this.d.addActor(label8);
                    int i9 = 0;
                    for (int i10 = 0; i10 < basicLevel.quests.size; i10++) {
                        if (basicLevel.quests.get(i10).isCompleted()) {
                            i9++;
                        }
                    }
                    Label label9 = new Label(Game.i.localeManager.i18n.get("completed").toUpperCase() + ": " + i9 + " / " + basicLevel.quests.size, new Label.LabelStyle(Game.i.assetManager.getFont(21), Color.WHITE));
                    label9.setColor(1.0f, 1.0f, 1.0f, 0.28f);
                    label9.setPosition(1060.0f, 348.0f);
                    label9.setSize(100.0f, 21.0f);
                    label9.setAlignment(16);
                    this.d.addActor(label9);
                    QuestsList questsList = new QuestsList(this, basicLevel, (byte) 0);
                    questsList.setPosition(330.0f, 116.0f);
                    this.d.addActor(questsList);
                    Image image8 = new Image(Game.i.assetManager.getDrawable("ui-level-selection-vertical-scroll-hint"));
                    image8.setColor(1.0f, 1.0f, 1.0f, 0.28f);
                    image8.setSize(20.0f, 136.0f);
                    image8.setPosition(1168.0f, 160.0f);
                    image8.setTouchable(Touchable.disabled);
                    this.d.addActor(image8);
                }
                if (basicLevel.getMap().getTargetTile(false) != null) {
                    if (basicLevel.hasLeaderboards && (Game.i.progressManager.getDifficultyMode() == DifficultyMode.NORMAL || DifficultyMode.isEndless(Game.i.progressManager.getDifficultyMode()))) {
                        if (Game.i.authManager.isSignedIn()) {
                            QuadActor quadActor = new QuadActor(new Color[]{new Color(-272617728), new Color(-272617728), new Color(-272617657), new Color(-272617657)}, new float[]{0.0f, 11.0f, 0.0f, 77.0f, 518.0f, 82.0f, 489.0f, 0.0f});
                            quadActor.setSize(518.0f, 82.0f);
                            quadActor.setPosition(331.0f, 20.0f);
                            this.d.addActor(quadActor);
                            Label label10 = new Label("", Game.i.assetManager.getLabelStyle(24));
                            label10.setPosition(733.0f, 65.0f);
                            label10.setSize(73.0f, 21.0f);
                            label10.setAlignment(16);
                            this.d.addActor(label10);
                            Label label11 = new Label("", Game.i.assetManager.getLabelStyle(21));
                            label11.setPosition(733.0f, 39.0f);
                            label11.setSize(73.0f, 21.0f);
                            label11.setAlignment(16);
                            label11.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                            this.d.addActor(label11);
                            Game.i.leaderBoardManager.getLeaderboardsRank(GameStateSystem.GameMode.BASIC_LEVELS, difficultyMode, basicLevel.name, ReplayManager.LeaderboardsMode.score, leaderboardsRankResult -> {
                                if (leaderboardsRankResult.success) {
                                    if (leaderboardsRankResult.rank <= 0) {
                                        label11.setText("? / " + leaderboardsRankResult.total);
                                        label10.setText(Game.i.localeManager.i18n.get("play_to_get_ranked"));
                                        return;
                                    }
                                    label11.setText(leaderboardsRankResult.rank + " / " + leaderboardsRankResult.total);
                                    if (leaderboardsRankResult.rank == 1) {
                                        label10.setText(Game.i.localeManager.i18n.get("leader") + "!");
                                    } else {
                                        label10.setText(Game.i.localeManager.i18n.format("top_percent", Integer.valueOf(MathUtils.ceil((leaderboardsRankResult.rank / leaderboardsRankResult.total) * 100.0f))));
                                    }
                                }
                            });
                        }
                        ComplexButton complexButton2 = new ComplexButton(Game.i.localeManager.i18n.get("leaderboards"), Game.i.assetManager.getLabelStyle(24), () -> {
                            LeaderboardsOverlay.i().show(basicLevel, ReplayManager.LeaderboardsMode.score);
                        });
                        complexButton2.setSize(480.0f, 82.0f);
                        complexButton2.setIconPositioned(Game.i.assetManager.getDrawable("icon-crown"), 24.0f, 20.0f, 48.0f, 48.0f);
                        Color color2 = MaterialColor.AMBER.P500;
                        complexButton2.setIconLabelColors(color2, color2, MaterialColor.AMBER.P500, Color.WHITE);
                        complexButton2.setLabel(86.0f, 20.0f, 200.0f, 48.0f, 8);
                        complexButton2.setPosition(331.0f, 20.0f);
                        this.d.addActor(complexButton2);
                    }
                    if (basicLevel.getMap().getTargetTileOrThrow().isDisableAbilities() || !Game.i.abilityManager.isAnyAbilityOpened()) {
                        overlayContinueButton2 = new OverlayContinueButton(Game.i.localeManager.i18n.get("play"), "icon-triangle-right", MaterialColor.LIGHT_BLUE.P800, MaterialColor.LIGHT_BLUE.P900, MaterialColor.LIGHT_BLUE.P700, () -> {
                            Game.i.soundManager.playStatic(StaticSoundType.BUTTON);
                            Runnable runnable = () -> {
                                Game.i.soundManager.playStatic(StaticSoundType.BUTTON);
                                if (GameStateSystem.savedGameExists()) {
                                    Dialog.i().showConfirm(Game.i.localeManager.i18n.get("saved_game_will_be_lost_confirm"), () -> {
                                        GameStateSystem.deleteSavedGame();
                                        Game.i.screenManager.startNewBasicLevel(basicLevel, null);
                                    });
                                } else {
                                    Game.i.screenManager.startNewBasicLevel(basicLevel, null);
                                }
                            };
                            runnable.run();
                        });
                    } else {
                        overlayContinueButton2 = new OverlayContinueButton(Game.i.localeManager.i18n.get("continue"), "icon-triangle-right", MaterialColor.LIGHT_BLUE.P800, MaterialColor.LIGHT_BLUE.P900, MaterialColor.LIGHT_BLUE.P700, () -> {
                            Game.i.soundManager.playStatic(StaticSoundType.BUTTON);
                            Runnable runnable = () -> {
                                Game.i.soundManager.playStatic(StaticSoundType.BUTTON);
                                LevelSelectScreen.this.t.a(false, true);
                                AbilitySelectionOverlay.i().show(() -> {
                                    LevelSelectScreen.this.t.a(true, true);
                                }, selectedAbilitiesConfiguration -> {
                                    Game.i.screenManager.startNewBasicLevel(basicLevel, selectedAbilitiesConfiguration);
                                });
                            };
                            Runnable runnable2 = () -> {
                                Game.i.soundManager.playStatic(StaticSoundType.BUTTON);
                                if (GameStateSystem.savedGameExists()) {
                                    Dialog.i().showConfirm(Game.i.localeManager.i18n.get("saved_game_will_be_lost_confirm"), () -> {
                                        GameStateSystem.deleteSavedGame();
                                        runnable.run();
                                    });
                                } else {
                                    runnable.run();
                                }
                            };
                            runnable2.run();
                        });
                    }
                    overlayContinueButton2.setName("level_select_overlay_continue_button");
                    overlayContinueButton2.setPosition(812.0f, -13.0f);
                    this.d.addActor(overlayContinueButton2);
                }
            } else {
                float f3 = 610.0f;
                if (basicLevel.openRequirements.size != 0) {
                    Label label12 = new Label(Game.i.localeManager.i18n.get("requirements").toUpperCase(), new Label.LabelStyle(Game.i.assetManager.getFont(21), Color.WHITE));
                    label12.setColor(1.0f, 1.0f, 1.0f, 0.28f);
                    label12.setPosition(330.0f, 610.0f);
                    this.d.addActor(label12);
                    f3 = 540.0f;
                    for (int i11 = 0; i11 < basicLevel.openRequirements.size; i11++) {
                        Requirement requirement = basicLevel.openRequirements.get(i11);
                        Image image9 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                        image9.setSize(870.0f, 52.0f);
                        image9.setPosition(330.0f, f3);
                        image9.setColor(new Color(0.0f, 0.0f, 0.0f, 0.14f));
                        this.d.addActor(image9);
                        Image image10 = new Image(Game.i.assetManager.getDrawable(requirement.getIconTextureName()));
                        image10.setSize(32.0f, 32.0f);
                        image10.setPosition(340.0f, f3 + 10.0f);
                        this.d.addActor(image10);
                        Label label13 = new Label(requirement.getTitle(true), new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
                        label13.setSize(100.0f, 52.0f);
                        label13.setPosition(382.0f, f3);
                        this.d.addActor(label13);
                        StringBuilder formattedValue = requirement.getFormattedValue();
                        if (formattedValue.length() > 0) {
                            Label label14 = new Label(formattedValue, new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
                            label14.setPosition(944.0f, f3);
                            label14.setSize(160.0f, 52.0f);
                            label14.setAlignment(16);
                            this.d.addActor(label14);
                        }
                        if (requirement.isSatisfied()) {
                            Image image11 = new Image(Game.i.assetManager.getDrawable("icon-check"));
                            image = image11;
                            image11.setColor(MaterialColor.GREEN.P500);
                        } else {
                            Image image12 = new Image(Game.i.assetManager.getDrawable("icon-times"));
                            image = image12;
                            image12.setColor(MaterialColor.ORANGE.P500);
                        }
                        image.setSize(32.0f, 32.0f);
                        image.setPosition(1120.0f, f3 + 10.0f);
                        this.d.addActor(image);
                        if (requirement.type == RequirementType.STARS || requirement.type == RequirementType.OPENED_LEVEL || requirement.type == RequirementType.RESEARCH) {
                            EyeButton eyeButton = new EyeButton(this, () -> {
                                BasicLevel level;
                                if (requirement.type == RequirementType.RESEARCH) {
                                    Game.i.screenManager.goToResearchesScreenFocusOnResearch(requirement.researchType);
                                    return;
                                }
                                if (requirement.type == RequirementType.STARS) {
                                    BasicLevel level2 = Game.i.basicLevelManager.getLevel(requirement.levelName);
                                    if (level2 != null) {
                                        LevelSelectScreen.this.t.a(level2);
                                        LevelSelectScreen.this.t.a(true, false);
                                        return;
                                    }
                                    return;
                                }
                                if (requirement.type == RequirementType.OPENED_LEVEL && (level = Game.i.basicLevelManager.getLevel(requirement.openedLevelName)) != null) {
                                    LevelSelectScreen.this.t.a(level);
                                    LevelSelectScreen.this.t.a(true, false);
                                }
                            }, (byte) 0);
                            eyeButton.setPosition(1200.0f, f3);
                            this.d.addActor(eyeButton);
                        }
                        f3 -= 56.0f;
                    }
                }
                boolean z3 = false;
                if (basicLevel.priceInMoney > 0) {
                    z3 = true;
                } else {
                    int i12 = 0;
                    while (true) {
                        if (i12 >= basicLevel.priceInResources.length) {
                            break;
                        }
                        if (basicLevel.priceInResources[i12] <= 0) {
                            i12++;
                        } else {
                            z3 = true;
                            break;
                        }
                    }
                }
                if (z3) {
                    Label label15 = new Label(Game.i.localeManager.i18n.get("price").toUpperCase(), new Label.LabelStyle(Game.i.assetManager.getFont(21), Color.WHITE));
                    label15.setColor(1.0f, 1.0f, 1.0f, 0.28f);
                    label15.setPosition(330.0f, f3);
                    this.d.addActor(label15);
                    float f4 = f3 - 70.0f;
                    boolean z4 = true;
                    for (int i13 = 0; i13 < basicLevel.priceInResources.length; i13++) {
                        if (basicLevel.priceInResources[i13] > 0) {
                            float f5 = z4 ? 330.0f : 767.0f;
                            Image image13 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                            image13.setSize(433.0f, 52.0f);
                            image13.setPosition(f5, f4);
                            image13.setColor(new Color(0.0f, 0.0f, 0.0f, 0.14f));
                            this.d.addActor(image13);
                            Image image14 = new Image(Game.i.assetManager.getDrawable(Resource.TEXTURE_REGION_NAMES[i13]));
                            image14.setColor(Game.i.resourceManager.getColor(ResourceType.values[i13]));
                            image14.setSize(32.0f, 32.0f);
                            image14.setPosition(f5 + 10.0f, f4 + 10.0f);
                            this.d.addActor(image14);
                            Label label16 = new Label(Game.i.resourceManager.getName(ResourceType.values[i13]), new Label.LabelStyle(Game.i.assetManager.getFont(21), Game.i.resourceManager.getColor(ResourceType.values[i13])));
                            label16.setPosition(f5 + 52.0f, f4);
                            label16.setSize(100.0f, 52.0f);
                            this.d.addActor(label16);
                            int resources = Game.i.progressManager.getResources(ResourceType.values[i13]);
                            int i14 = resources;
                            if (resources > basicLevel.priceInResources[i13]) {
                                i14 = basicLevel.priceInResources[i13];
                            }
                            Label label17 = new Label(((Object) StringFormatter.commaSeparatedNumber(i14)) + " / " + ((Object) StringFormatter.commaSeparatedNumber(basicLevel.priceInResources[i13])), new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
                            label17.setSize(100.0f, 52.0f);
                            label17.setAlignment(16);
                            if (z4) {
                                label17.setPosition(f5 + 320.0f, f4);
                            } else {
                                label17.setPosition((f5 + 320.0f) - 28.0f, f4);
                            }
                            if (Game.i.progressManager.getResources(ResourceType.values[i13]) < basicLevel.priceInResources[i13]) {
                                label17.setColor(MaterialColor.ORANGE.P500);
                            } else {
                                label17.setColor(Color.WHITE);
                            }
                            this.d.addActor(label17);
                            boolean z5 = !z4;
                            z4 = z5;
                            if (z5) {
                                f4 -= 56.0f;
                            }
                        }
                    }
                    if (basicLevel.priceInMoney > 0) {
                        float f6 = z4 ? 330.0f : 767.0f;
                        Image image15 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                        image15.setSize(433.0f, 52.0f);
                        image15.setPosition(f6, f4);
                        image15.setColor(new Color(0.0f, 0.0f, 0.0f, 0.14f));
                        this.d.addActor(image15);
                        Image image16 = new Image(Game.i.assetManager.getDrawable("icon-money"));
                        image16.setSize(32.0f, 32.0f);
                        image16.setPosition(f6 + 10.0f, f4 + 10.0f);
                        this.d.addActor(image16);
                        Label label18 = new Label(Game.i.localeManager.i18n.get("green_papers_short"), new Label.LabelStyle(Game.i.assetManager.getFont(21), Color.WHITE));
                        label18.setPosition(f6 + 52.0f, f4);
                        label18.setSize(100.0f, 52.0f);
                        this.d.addActor(label18);
                        int greenPapers = Game.i.progressManager.getGreenPapers();
                        int i15 = greenPapers;
                        if (greenPapers > basicLevel.priceInMoney) {
                            i15 = basicLevel.priceInMoney;
                        }
                        Label label19 = new Label(((Object) StringFormatter.commaSeparatedNumber(i15)) + " / " + ((Object) StringFormatter.commaSeparatedNumber(basicLevel.priceInMoney)), new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
                        label19.setSize(100.0f, 52.0f);
                        label19.setAlignment(16);
                        if (z4) {
                            label19.setPosition(f6 + 320.0f, f4);
                        } else {
                            label19.setPosition((f6 + 320.0f) - 28.0f, f4);
                        }
                        if (Game.i.progressManager.getGreenPapers() < basicLevel.priceInMoney) {
                            label19.setColor(MaterialColor.ORANGE.P500);
                        } else {
                            label19.setColor(Color.WHITE);
                        }
                        this.d.addActor(label19);
                    }
                }
                if (Game.i.basicLevelManager.canBePurchased(basicLevel)) {
                    overlayContinueButton = new OverlayContinueButton(Game.i.localeManager.i18n.get("unlock"), "icon-lock", MaterialColor.LIGHT_BLUE.P800, MaterialColor.LIGHT_BLUE.P900, MaterialColor.LIGHT_BLUE.P700, () -> {
                        if (basicLevel.priceInMoney > 0) {
                            Game.i.progressManager.removeGreenPapers(basicLevel.priceInMoney);
                        }
                        for (int i16 = 0; i16 < basicLevel.priceInResources.length; i16++) {
                            if (basicLevel.priceInResources[i16] > 0) {
                                Game.i.progressManager.removeResources(ResourceType.values[i16], basicLevel.priceInResources[i16]);
                            }
                        }
                        Game.i.basicLevelManager.setPurchased(basicLevel);
                        LevelSelectScreen.this.n();
                        LevelSelectScreen.this.t.a(basicLevel);
                        LevelSelectScreen.this.t.a(true, false);
                        Game.i.soundManager.playStatic(StaticSoundType.SUCCESS);
                    });
                } else {
                    overlayContinueButton = new OverlayContinueButton(Game.i.localeManager.i18n.get("unlock"), "icon-lock", MaterialColor.ORANGE.P800, MaterialColor.ORANGE.P900, MaterialColor.ORANGE.P700, () -> {
                        Game.i.soundManager.playStatic(StaticSoundType.FAIL);
                        for (int i16 = 0; i16 < basicLevel.openRequirements.size; i16++) {
                            if (!basicLevel.openRequirements.get(i16).isSatisfied()) {
                                Dialog.i().showAlert(Game.i.localeManager.i18n.get("not_all_conditions_are_met"));
                                return;
                            } else {
                                if (Game.i.progressManager.getGreenPapers() < basicLevel.priceInMoney) {
                                    Dialog.i().showAlert(Game.i.localeManager.i18n.get("not_enough_green_papers"));
                                    return;
                                }
                                Dialog.i().showAlert(Game.i.localeManager.i18n.get("not_enough_resources"));
                            }
                        }
                    });
                }
                overlayContinueButton.setPosition(812.0f, -13.0f);
                this.d.addActor(overlayContinueButton);
            }
            if (Game.i.gameValueManager.getSnapshot().getBooleanValue(GameValueType.PRESTIGE_MODE)) {
                ComplexButton complexButton3 = new ComplexButton("", Game.i.assetManager.getLabelStyle(21), () -> {
                    QuestPrestigeOverlay.i().show();
                });
                complexButton3.setBackground(Game.i.assetManager.getDrawable("ui-game-over-prestige-button"), 0.0f, 0.0f, 113.0f, 110.0f);
                complexButton3.setIconPositioned(Game.i.assetManager.getDrawable("icon-crown"), 31.0f, 21.0f, 64.0f, 64.0f);
                complexButton3.setSize(113.0f, 100.0f);
                complexButton3.setPosition(1200.0f, 230.0f);
                this.d.addActor(complexButton3);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(boolean z, boolean z2) {
            if (LevelSelectScreen.this.v) {
                return;
            }
            float f = Game.i.settingsManager.isUiAnimationsEnabled() ? 1.0f : 0.0f;
            if (z2) {
                if (z) {
                    DarkOverlay.i().addCaller("LevelSelectScreen levelMenu", UiManager.MainUiLayer.SCREEN, 102, () -> {
                        a(false, false);
                        return true;
                    });
                    LevelSelectScreen.this.y.getTable().setVisible(true);
                    LevelSelectScreen.this.y.getTable().setTouchable(Touchable.childrenOnly);
                    this.d.clearActions();
                    this.d.addAction(Actions.sequence(Actions.scaleTo(1.0f, 1.0f), Actions.moveTo((-Game.i.settingsManager.getScaledViewportHeight()) * 2.0f, 0.0f), Actions.moveTo(0.0f, 0.0f, 0.2f * f)));
                } else {
                    DarkOverlay.i().removeCaller("LevelSelectScreen levelMenu");
                    LevelSelectScreen.this.y.getTable().setTouchable(Touchable.disabled);
                    this.d.clearActions();
                    this.d.addAction(Actions.sequence(Actions.moveTo((-Game.i.settingsManager.getScaledViewportHeight()) * 2.0f, 0.0f, 0.2f * f), Actions.run(() -> {
                        LevelSelectScreen.this.y.getTable().setVisible(false);
                    })));
                }
            } else if (z) {
                DarkOverlay.i().addCaller("LevelSelectScreen levelMenu", UiManager.MainUiLayer.SCREEN, 102, () -> {
                    a(false, false);
                    return true;
                });
                LevelSelectScreen.this.y.getTable().setVisible(true);
                LevelSelectScreen.this.y.getTable().setTouchable(Touchable.childrenOnly);
                this.d.clearActions();
                this.d.addAction(Actions.sequence(Actions.scaleTo(0.0f, 0.0f), Actions.moveTo(0.0f, 0.0f), Actions.parallel(Actions.sequence(Actions.delay(0.1f), Actions.scaleBy(1.0f, 0.0f, 0.3f * f, Interpolation.swingOut)), Actions.scaleBy(0.0f, 1.0f, 0.3f * f, Interpolation.swingOut))));
            } else {
                DarkOverlay.i().removeCaller("LevelSelectScreen levelMenu");
                LevelSelectScreen.this.y.getTable().setTouchable(Touchable.disabled);
                this.d.clearActions();
                this.d.addAction(Actions.sequence(Actions.parallel(Actions.sequence(Actions.delay(0.07f * f), Actions.scaleBy(0.0f, -this.d.getScaleY(), 0.3f * f, Interpolation.swingIn)), Actions.scaleBy(-this.d.getScaleX(), 0.0f, 0.3f * f, Interpolation.swingIn)), Actions.run(() -> {
                    LevelSelectScreen.this.y.getTable().setVisible(false);
                })));
            }
            this.f2770b = z;
            if (!z) {
                LevelSelectScreen.this.m();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean a() {
            return this.f2770b;
        }

        @Override // com.badlogic.gdx.utils.Disposable
        public void dispose() {
            DarkOverlay.i().removeCaller("LevelSelectScreen levelMenu");
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/LevelSelectScreen$LevelMenuOverlay$EyeButton.class */
        public class EyeButton extends Group {
            private boolean k;
            private boolean l;
            private QuadActor m;

            /* synthetic */ EyeButton(LevelMenuOverlay levelMenuOverlay, Runnable runnable, byte b2) {
                this(levelMenuOverlay, runnable);
            }

            private EyeButton(LevelMenuOverlay levelMenuOverlay, Runnable runnable) {
                setTransform(false);
                setSize(92.0f, 52.0f);
                Color cpy = MaterialColor.LIGHT_BLUE.P900.cpy();
                cpy.f889a = 0.56f;
                addActor(new QuadActor(cpy, new float[]{0.0f, -9.0f, 0.0f, 38.0f, 84.0f, 52.0f, 72.0f, 0.0f}));
                this.m = new QuadActor(Color.WHITE, new float[]{0.0f, 0.0f, 0.0f, 52.0f, 84.0f, 52.0f, 72.0f, 0.0f});
                this.m.setPosition(8.0f, 0.0f);
                addActor(this.m);
                Image image = new Image(Game.i.assetManager.getDrawable("icon-eye"));
                image.setSize(32.0f, 32.0f);
                image.setPosition(30.0f, 10.0f);
                addActor(image);
                setTouchable(Touchable.enabled);
                addListener(new ClickListener(levelMenuOverlay, runnable) { // from class: com.prineside.tdi2.screens.LevelSelectScreen.LevelMenuOverlay.EyeButton.1

                    /* renamed from: a, reason: collision with root package name */
                    private /* synthetic */ Runnable f2774a;

                    {
                        this.f2774a = runnable;
                    }

                    @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                    public void clicked(InputEvent inputEvent, float f, float f2) {
                        this.f2774a.run();
                        Game.i.soundManager.playStatic(StaticSoundType.BUTTON);
                    }

                    @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
                    public boolean touchDown(InputEvent inputEvent, float f, float f2, int i, int i2) {
                        EyeButton.this.k = true;
                        EyeButton.this.d();
                        return super.touchDown(inputEvent, f, f2, i, i2);
                    }

                    @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
                    public void touchUp(InputEvent inputEvent, float f, float f2, int i, int i2) {
                        EyeButton.this.k = false;
                        EyeButton.this.d();
                        super.touchUp(inputEvent, f, f2, i, i2);
                    }

                    @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
                    public void enter(InputEvent inputEvent, float f, float f2, int i, Actor actor) {
                        if (i == -1) {
                            EyeButton.this.l = true;
                            EyeButton.this.d();
                        }
                        super.enter(inputEvent, f, f2, i, actor);
                    }

                    @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
                    public void exit(InputEvent inputEvent, float f, float f2, int i, Actor actor) {
                        if (i == -1) {
                            EyeButton.this.l = false;
                            EyeButton.this.d();
                        }
                        super.exit(inputEvent, f, f2, i, actor);
                    }
                });
                d();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void d() {
                if (this.k) {
                    this.m.setColor(MaterialColor.LIGHT_BLUE.P900);
                } else if (this.l) {
                    this.m.setColor(MaterialColor.LIGHT_BLUE.P700);
                } else {
                    this.m.setColor(MaterialColor.LIGHT_BLUE.P800);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/LevelSelectScreen$LevelMenuOverlay$WaveQuestsLine.class */
        public class WaveQuestsLine extends Group {
            /* synthetic */ WaveQuestsLine(LevelMenuOverlay levelMenuOverlay, BasicLevel basicLevel, byte b2) {
                this(levelMenuOverlay, basicLevel);
            }

            private WaveQuestsLine(LevelMenuOverlay levelMenuOverlay, BasicLevel basicLevel) {
                setTransform(false);
                BasicLevelStage stage = Game.i.basicLevelManager.getStage(basicLevel.stageName);
                setSize(870.0f, 176.0f);
                Array array = new Array();
                for (int i = 0; i < basicLevel.waveQuests.size; i++) {
                    BasicLevel.WaveQuest waveQuest = basicLevel.waveQuests.get(i);
                    WaveMilestoneConfig waveMilestoneConfig = new WaveMilestoneConfig(this, (byte) 0);
                    waveMilestoneConfig.f2780a = waveQuest.waves;
                    waveMilestoneConfig.f2781b = waveQuest;
                    array.add(waveMilestoneConfig);
                }
                WaveBossSupplier bossWaves = basicLevel.getMap().getBossWaves();
                if (bossWaves != null) {
                    LevelSelectScreen.f2765a.i("bossWaves exists " + bossWaves, new Object[0]);
                    for (int i2 = 0; i2 < 200; i2++) {
                        BossType waveBoss = bossWaves.getWaveBoss(i2);
                        if (waveBoss != null) {
                            LevelSelectScreen.f2765a.i("- " + i2 + SequenceUtils.SPACE + waveBoss, new Object[0]);
                        }
                    }
                    IntSet intSet = new IntSet();
                    for (int i3 = 0; i3 < array.size; i3++) {
                        WaveMilestoneConfig waveMilestoneConfig2 = (WaveMilestoneConfig) array.get(i3);
                        BossType waveBoss2 = bossWaves.getWaveBoss(waveMilestoneConfig2.f2780a);
                        if (waveBoss2 != null) {
                            waveMilestoneConfig2.c = waveBoss2;
                            intSet.add(waveMilestoneConfig2.f2780a);
                        }
                    }
                    for (int i4 = 0; i4 < 1000; i4++) {
                        BossType waveBoss3 = bossWaves.getWaveBoss(i4);
                        if (waveBoss3 != null && !intSet.contains(i4)) {
                            WaveMilestoneConfig waveMilestoneConfig3 = new WaveMilestoneConfig(this, (byte) 0);
                            waveMilestoneConfig3.f2780a = i4;
                            waveMilestoneConfig3.c = waveBoss3;
                            array.add(waveMilestoneConfig3);
                        }
                    }
                }
                array.sort((waveMilestoneConfig4, waveMilestoneConfig5) -> {
                    return Integer.compare(waveMilestoneConfig4.f2780a, waveMilestoneConfig5.f2780a);
                });
                Group group = new Group();
                group.setTransform(false);
                ScrollPane scrollPane = new ScrollPane(group);
                UiUtils.enableMouseMoveScrollFocus(scrollPane);
                scrollPane.setSize(868.0f, 176.0f);
                scrollPane.setPosition(1.0f, 0.0f);
                addActor(scrollPane);
                Actor image = new Image(Game.i.assetManager.getDrawable("gradient-left"));
                image.setColor(new Color(791621631));
                image.setSize(80.0f, 176.0f);
                image.setTouchable(Touchable.disabled);
                addActor(image);
                int i5 = ((WaveMilestoneConfig) array.get(array.size - 1)).f2780a;
                int i6 = 0;
                int i7 = 9999;
                for (int i8 = 0; i8 < array.size; i8++) {
                    int i9 = ((WaveMilestoneConfig) array.get(i8)).f2780a - i6;
                    i6 = ((WaveMilestoneConfig) array.get(i8)).f2780a;
                    if (i9 < i7) {
                        i7 = i9;
                    }
                }
                float ceil = (float) StrictMath.ceil(50.0f / (i7 > 10 ? 10 : i7));
                float f = i5 * ceil;
                Image image2 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                image2.setSize(f, 5.0f);
                image2.setColor(new Color(488447487));
                image2.setPosition(80.0f, 133.0f);
                group.addActor(image2);
                int levelMaxReachedWave = ProgressPrefs.i().basicLevel.getLevelMaxReachedWave(basicLevel.name);
                int i10 = levelMaxReachedWave - i6 > 14 ? i6 + 14 : levelMaxReachedWave;
                if (i10 != 0) {
                    Image image3 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                    image3.setSize(i10 * ceil, 5.0f);
                    image3.setColor(stage.color);
                    image3.setPosition(80.0f, 133.0f);
                    group.addActor(image3);
                    if (i10 != ProgressPrefs.i().basicLevel.getLevelMaxReachedWave(basicLevel.name)) {
                        float f2 = (80.0f + ((i6 + 7) * ceil)) - 17.0f;
                        Image image4 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                        image4.setSize(45.0f, 5.0f);
                        image4.setColor(new Color(791621631));
                        image4.setPosition(f2 - 5.0f, 133.0f);
                        group.addActor(image4);
                        for (int i11 = 0; i11 < 3; i11++) {
                            Image image5 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                            image5.setSize(5.0f, 5.0f);
                            image5.setColor(stage.color);
                            image5.setPosition(f2 + 5.0f + (i11 * 10), 133.0f);
                            group.addActor(image5);
                        }
                    }
                }
                float f3 = 80.0f + (i10 * ceil);
                Image image6 = new Image(Game.i.assetManager.getDrawable("small-triangle-mark-bottom"));
                image6.setSize(10.0f, 5.0f);
                image6.setPosition(f3 - 5.0f, 140.0f);
                image6.setColor(stage.color);
                group.addActor(image6);
                Table table = new Table();
                table.setSize(200.0f, 24.0f);
                table.setPosition(f3 - 100.0f, 150.0f);
                group.addActor(table);
                Image image7 = new Image(Game.i.assetManager.getDrawable("icon-wave"));
                image7.setColor(stage.color);
                table.add((Table) image7).size(24.0f).padRight(8.0f);
                Label label = new Label(String.valueOf(ProgressPrefs.i().basicLevel.getLevelMaxReachedWave(basicLevel.name)), new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
                label.setColor(stage.color);
                table.add((Table) label);
                for (int i12 = 0; i12 < array.size; i12++) {
                    WaveMilestoneConfig waveMilestoneConfig6 = (WaveMilestoneConfig) array.get(i12);
                    float f4 = 80.0f + (ceil * waveMilestoneConfig6.f2780a);
                    Image image8 = new Image(Game.i.assetManager.getDrawable("ui-level-selection-milestone-top"));
                    if (waveMilestoneConfig6.f2781b != null && waveMilestoneConfig6.f2781b.isCompleted()) {
                        image8.setColor(stage.color);
                        image8.getColor().lerp(new Color(488447487), 0.44f);
                    } else {
                        image8.setColor(new Color(488447487));
                    }
                    image8.setPosition(f4 - 24.0f, 114.0f);
                    image8.setSize(48.0f, 13.0f);
                    group.addActor(image8);
                    Image image9 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                    image9.setColor(new Color(488447487));
                    float f5 = 36.0f;
                    if (waveMilestoneConfig6.f2781b != null) {
                        int i13 = waveMilestoneConfig6.f2781b.prizes.size;
                        int i14 = 0;
                        while (true) {
                            if (i14 >= waveMilestoneConfig6.f2781b.prizes.size) {
                                break;
                            }
                            if (waveMilestoneConfig6.f2781b.prizes.items[i14].getItem() instanceof StarItem) {
                                i13--;
                                break;
                            }
                            i14++;
                        }
                        f5 = 36.0f + (i13 * 26.0f);
                    }
                    f5 = waveMilestoneConfig6.c != null ? f5 + 58.0f : f5;
                    image9.setPosition(f4 - 24.0f, 114.0f - f5);
                    image9.setSize(48.0f, f5);
                    group.addActor(image9);
                    if (waveMilestoneConfig6.f2781b != null && waveMilestoneConfig6.f2781b.isCompleted()) {
                        Image image10 = new Image(Game.i.assetManager.getDrawable("gradient-top"));
                        image10.setColor(image8.getColor());
                        image10.setPosition(f4 - 24.0f, 78.0f);
                        image10.setSize(48.0f, 36.0f);
                        group.addActor(image10);
                    }
                    Label label2 = new Label(String.valueOf(waveMilestoneConfig6.f2780a), new Label.LabelStyle(Game.i.assetManager.getFont(21), Color.WHITE));
                    label2.setSize(48.0f, 10.0f);
                    label2.setAlignment(4);
                    label2.setPosition(f4 - 24.0f, 90.0f);
                    group.addActor(label2);
                    if (waveMilestoneConfig6.c != null) {
                        Image image11 = new Image(Game.i.assetManager.getDrawable("level-select-wave-marker-boss-frame"));
                        image11.setSize(67.0f, 58.0f);
                        image11.setPosition(f4 - 28.0f, 38.0f);
                        group.addActor(image11);
                        Image image12 = new Image(levelMenuOverlay.c == null ? Game.i.assetManager.getTextureRegion("icon-question") : levelMenuOverlay.c.wave.getWaveProcessorFactory(waveMilestoneConfig6.c).getIconTexture());
                        image12.setSize(32.0f, 32.0f);
                        image12.setPosition(f4 - 16.0f, 48.0f);
                        group.addActor(image12);
                    }
                    if (waveMilestoneConfig6.f2781b != null) {
                        float f6 = waveMilestoneConfig6.c != null ? 8.0f : 62.0f;
                        int i15 = 0;
                        for (int i16 = 0; i16 < waveMilestoneConfig6.f2781b.prizes.size; i16++) {
                            ItemStack itemStack = waveMilestoneConfig6.f2781b.prizes.get(i16);
                            if (itemStack.getItem() instanceof StarItem) {
                                Actor generateIcon = itemStack.getItem().generateIcon(24.0f, false);
                                generateIcon.setPosition(f4 - 17.0f, 125.0f);
                                generateIcon.setColor(new Color(791621631));
                                group.addActor(generateIcon);
                                Actor generateIcon2 = itemStack.getItem().generateIcon(24.0f, false);
                                generateIcon2.setPosition(f4 - 7.0f, 125.0f);
                                generateIcon2.setColor(new Color(791621631));
                                group.addActor(generateIcon2);
                                Actor generateIcon3 = itemStack.getItem().generateIcon(24.0f, false);
                                generateIcon3.setPosition(f4 - 12.0f, 125.0f);
                                if (waveMilestoneConfig6.f2781b.isCompleted()) {
                                    generateIcon3.setColor(stage.color);
                                }
                                group.addActor(generateIcon3);
                            } else {
                                Actor generateIcon4 = itemStack.getItem().generateIcon(24.0f, true);
                                generateIcon4.setPosition(f4 - 12.0f, f6 - (i15 * 26.0f));
                                group.addActor(generateIcon4);
                                generateIcon4.setTouchable(Touchable.enabled);
                                generateIcon4.addListener(new ClickListener(this, levelMenuOverlay, waveMilestoneConfig6, itemStack) { // from class: com.prineside.tdi2.screens.LevelSelectScreen.LevelMenuOverlay.WaveQuestsLine.1

                                    /* renamed from: a, reason: collision with root package name */
                                    private /* synthetic */ WaveMilestoneConfig f2778a;

                                    /* renamed from: b, reason: collision with root package name */
                                    private /* synthetic */ ItemStack f2779b;

                                    {
                                        this.f2778a = waveMilestoneConfig6;
                                        this.f2779b = itemStack;
                                    }

                                    @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                                    public void clicked(InputEvent inputEvent, float f7, float f8) {
                                        if (this.f2778a.f2781b.isCompleted()) {
                                            ItemDescriptionDialog.i().showWithCount(this.f2779b.getItem(), this.f2779b.getCount());
                                        } else {
                                            ItemDescriptionDialog.i().show(this.f2779b.getItem());
                                        }
                                        Game.i.soundManager.playStatic(StaticSoundType.BUTTON);
                                    }
                                });
                                i15++;
                            }
                        }
                    }
                }
                SnapshotArray<Actor> children = group.getChildren();
                float f7 = 0.0f;
                for (int i17 = 0; i17 < children.size; i17++) {
                    float x = children.get(i17).getX() + children.get(i17).getWidth();
                    if (x > f7) {
                        f7 = x;
                    }
                }
                group.setSize(f7 + 40.0f, 176.0f);
                scrollPane.layout();
                float f8 = f3 - 435.0f;
                if (f8 > 0.0f) {
                    scrollPane.setScrollX(f8);
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/LevelSelectScreen$LevelMenuOverlay$WaveQuestsLine$WaveMilestoneConfig.class */
            public class WaveMilestoneConfig {

                /* renamed from: a, reason: collision with root package name */
                int f2780a;

                /* renamed from: b, reason: collision with root package name */
                BasicLevel.WaveQuest f2781b;
                BossType c;

                private WaveMilestoneConfig(WaveQuestsLine waveQuestsLine) {
                }

                /* synthetic */ WaveMilestoneConfig(WaveQuestsLine waveQuestsLine, byte b2) {
                    this(waveQuestsLine);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/LevelSelectScreen$LevelMenuOverlay$QuestsList.class */
        public class QuestsList extends Group {
            /* synthetic */ QuestsList(LevelMenuOverlay levelMenuOverlay, BasicLevel basicLevel, byte b2) {
                this(basicLevel);
            }

            /* JADX WARN: Multi-variable type inference failed */
            private QuestsList(BasicLevel basicLevel) {
                Cell add;
                Actor generateIcon;
                float f;
                float f2;
                setTransform(false);
                setSize(870.0f, 222.0f);
                float f3 = (basicLevel.quests.size * 54.0f) + 52.0f;
                float f4 = f3 < 220.0f ? 220.0f : f3;
                Group group = new Group();
                group.setTransform(false);
                group.setSize(870.0f, f4);
                ScrollPane scrollPane = new ScrollPane(group);
                UiUtils.enableMouseMoveScrollFocus(scrollPane);
                scrollPane.setPosition(0.0f, 1.0f);
                scrollPane.setSize(870.0f, 220.0f);
                addActor(scrollPane);
                Actor image = new Image(Game.i.assetManager.getDrawable("gradient-bottom"));
                image.setColor(new Color(791621631));
                image.setSize(870.0f, 52.0f);
                image.setTouchable(Touchable.disabled);
                addActor(image);
                int i = 0;
                int intValue = Game.i.gameValueManager.getSnapshot().getIntValue(GameValueType.REGULAR_QUESTS_SLOTS);
                boolean z = true;
                int i2 = 0;
                while (true) {
                    if (i2 >= basicLevel.waveQuests.size) {
                        break;
                    }
                    if (basicLevel.waveQuests.items[i2].isCompleted()) {
                        i2++;
                    } else {
                        z = false;
                        break;
                    }
                }
                intValue = z ? intValue + 1 : intValue;
                float f5 = 0.0f;
                Array array = new Array(ItemStack.class);
                for (int i3 = 0; i3 < basicLevel.quests.size; i3++) {
                    BasicLevelQuestConfig basicLevelQuestConfig = basicLevel.quests.get(i3);
                    float f6 = (f4 - (i3 * 54.0f)) - 52.0f;
                    Image image2 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                    image2.setColor(new Color(0.0f, 0.0f, 0.0f, 0.14f));
                    image2.setSize(870.0f, 52.0f);
                    image2.setPosition(0.0f, f6);
                    group.addActor(image2);
                    if (basicLevelQuestConfig.isCompleted()) {
                        Color cpy = MaterialColor.GREEN.P500.cpy();
                        cpy.f889a = 0.07f;
                        image2.setColor(cpy);
                        Image image3 = new Image(Game.i.assetManager.getDrawable("gradient-right"));
                        image3.setColor(MaterialColor.GREEN.P500);
                        image3.getColor().f889a = 0.28f;
                        image3.setSize(870.0f, 52.0f);
                        image3.setPosition(0.0f, f6);
                        group.addActor(image3);
                    } else if (f5 == 0.0f) {
                        f5 = f6;
                    }
                    Table table = new Table();
                    table.setSize(870.0f, 52.0f);
                    table.setPosition(0.0f, f6);
                    group.addActor(table);
                    boolean z2 = i >= intValue && !basicLevelQuestConfig.isScripted();
                    table.add((Table) new Label(z2 ? "[#777777]???????[]" : basicLevelQuestConfig.getTitle(true, true), new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE))).padLeft(12.0f).padRight(12.0f);
                    array.clear();
                    array.addAll(basicLevelQuestConfig.getPrizes(Game.i.gameValueManager.getSnapshot()));
                    int extraDustInEndless = basicLevelQuestConfig.getExtraDustInEndless(Game.i.gameValueManager.getSnapshot());
                    if (DifficultyMode.isEndless(Game.i.progressManager.getDifficultyMode()) && extraDustInEndless > 0) {
                        array.add(new ItemStack(Item.D.BIT_DUST, extraDustInEndless));
                    }
                    for (int i4 = 0; i4 < array.size; i4++) {
                        ItemStack itemStack = ((ItemStack[]) array.items)[i4];
                        if ((itemStack.getItem() instanceof StarItem) || itemStack.getItem().getType() == ItemType.GREEN_PAPER || itemStack.getItem().getType() == ItemType.ACCELERATOR) {
                            generateIcon = itemStack.getItem().generateIcon(32.0f, true);
                            f = 32.0f;
                            f2 = 0.0f;
                            generateIcon.setTouchable(Touchable.enabled);
                            generateIcon.addListener(new ClickListener(this, LevelMenuOverlay.this, basicLevelQuestConfig, itemStack) { // from class: com.prineside.tdi2.screens.LevelSelectScreen.LevelMenuOverlay.QuestsList.1

                                /* renamed from: a, reason: collision with root package name */
                                private /* synthetic */ BasicLevelQuestConfig f2776a;

                                /* renamed from: b, reason: collision with root package name */
                                private /* synthetic */ ItemStack f2777b;

                                {
                                    this.f2776a = basicLevelQuestConfig;
                                    this.f2777b = itemStack;
                                }

                                @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                                public void clicked(InputEvent inputEvent, float f7, float f8) {
                                    if (this.f2776a.isCompleted()) {
                                        ItemDescriptionDialog.i().showWithCount(this.f2777b.getItem(), this.f2777b.getCount());
                                    } else {
                                        ItemDescriptionDialog.i().show(this.f2777b.getItem());
                                    }
                                    Game.i.soundManager.playStatic(StaticSoundType.BUTTON);
                                }
                            });
                        } else {
                            generateIcon = new Image(Game.i.uiManager.getItemCellRarityCoat(itemStack.getItem().getRarity(), i4 % 2));
                            f = 29.0f;
                            f2 = 2.0f;
                        }
                        generateIcon.setSize(f, 32.0f);
                        Group group2 = new Group();
                        group2.setTransform(false);
                        group2.addActor(generateIcon);
                        table.add((Table) group2).size(f, 32.0f).padLeft(f2).padRight(f2 + 2.0f);
                    }
                    table.add().expand();
                    int questSkipPrice = Game.i.basicLevelManager.getQuestSkipPrice(basicLevelQuestConfig);
                    if (questSkipPrice > 0 && !Game.i.dailyQuestManager.getDailyLootCurrentQuest().equals(basicLevelQuestConfig.id)) {
                        PaddedImageButton paddedImageButton = new PaddedImageButton(Game.i.assetManager.getDrawable("icon-double-triangle-right"), () -> {
                            Dialog.i().showConfirm(Game.i.localeManager.i18n.format("regular_quest_skip_confirm", Integer.valueOf(questSkipPrice)), () -> {
                                Game.i.basicLevelManager.skipQuest(basicLevelQuestConfig);
                                Dialog.i().hide();
                                LevelSelectScreen.this.t.a(basicLevel);
                            });
                        }, MaterialColor.LIGHT_BLUE.P500, MaterialColor.LIGHT_BLUE.P400, MaterialColor.LIGHT_BLUE.P600);
                        paddedImageButton.setIconSize(32.0f, 32.0f);
                        paddedImageButton.setIconPosition(16.0f, 10.0f);
                        table.add((Table) paddedImageButton).size(64.0f, 52.0f).padRight(15.0f);
                    }
                    table.add((Table) new Label(z2 ? "[#777777]??? / ???[]" : basicLevelQuestConfig.formatValueForUiWithRequiredValue(basicLevelQuestConfig.getCurrentValue(Game.i.gameValueManager.getSnapshot()), basicLevelQuestConfig.getRequiredValue(Game.i.gameValueManager.getSnapshot()), true), new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE)));
                    if (basicLevelQuestConfig.isCompleted()) {
                        Image image4 = new Image(Game.i.assetManager.getDrawable("icon-check"));
                        image4.setColor(MaterialColor.GREEN.P500);
                        add = table.add((Table) image4);
                    } else {
                        add = table.add();
                    }
                    add.size(32.0f).padRight(40.0f).padLeft(12.0f);
                    if (!basicLevelQuestConfig.isCompleted()) {
                        i++;
                    }
                }
                if (f5 != 0.0f) {
                    scrollPane.scrollTo(0.0f, f5, 1.0f, 1.0f, false, true);
                }
            }
        }
    }
}
