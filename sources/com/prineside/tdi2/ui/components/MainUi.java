package com.prineside.tdi2.ui.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.StringBuilder;
import com.badlogic.gdx.utils.Timer;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.MapPrestigeConfig;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.BaseHealthChange;
import com.prineside.tdi2.events.game.BonusPointsUpdate;
import com.prineside.tdi2.events.game.BonusSelect;
import com.prineside.tdi2.events.game.BonusStagesConfigSet;
import com.prineside.tdi2.events.game.CoinsChange;
import com.prineside.tdi2.events.game.ForceWaveAvailabilityChange;
import com.prineside.tdi2.events.game.GameSpeedChange;
import com.prineside.tdi2.events.game.MapDrawModeChange;
import com.prineside.tdi2.events.game.MdpsUpdate;
import com.prineside.tdi2.events.game.RewardingAdBecameAvailable;
import com.prineside.tdi2.events.game.RewardingAdRegistered;
import com.prineside.tdi2.events.game.ScoreChange;
import com.prineside.tdi2.events.game.WaveStatusChange;
import com.prineside.tdi2.events.global.ScreenResize;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.InputListener;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Cell;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.systems.BonusSystem;
import com.prineside.tdi2.systems.GameStateSystem;
import com.prineside.tdi2.systems.GameUiSystem;
import com.prineside.tdi2.systems.WaveSystem;
import com.prineside.tdi2.ui.actors.ButtonHoldHint;
import com.prineside.tdi2.ui.actors.GameplayBonusSummary;
import com.prineside.tdi2.ui.actors.HotKeyHintLabel;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.PaddedImageButton;
import com.prineside.tdi2.ui.actors.ParticlesCanvas;
import com.prineside.tdi2.ui.shared.TooltipsOverlay;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.TLog;
import java.util.Objects;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/MainUi.class */
public class MainUi implements Disposable {
    private GameSystemProvider o;
    public ParticlesCanvas particlesCanvas;
    public Group customElementsContainer;
    private final Label p;
    private final Label q;
    private final Label r;
    private final Label s;
    private final Group t;
    private final Group u;
    private final Label v;
    private final Image w;
    private final Label x;
    private final Table y;
    private final Actor z;
    private int A;
    private final PaddedImageButton B;
    private boolean C;
    private final Image D;
    private final Image E;
    private final Label F;
    private final Label G;
    private final Image H;
    private final Image I;
    private final Image J;
    private final PaddedImageButton K;
    private final PaddedImageButton L;
    private int M;
    private int N;
    private final PaddedImageButton P;
    private final Group Q;
    private final Group R;
    private final Group S;
    private final Group T;
    private final Group U;
    private final Group V;
    private final Group W;
    private final PaddedImageButton X;
    private final Image Y;
    public GameplayBonusSummary gameplayBonusGroup;
    public Table bossHpLinesTable;
    private final Image Z;
    private final ParticlesCanvas aa;
    private final ParticleEffect ab;
    private final Drawable ac;
    private final Drawable ad;
    private final Drawable ae;
    private final Drawable af;
    private boolean ag;
    private float ah;
    private boolean ai;
    private float aj;
    private final Listener<ScreenResize> al;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3323a = TLog.forClass(MainUi.class);

    /* renamed from: b, reason: collision with root package name */
    private static final Rectangle f3324b = new Rectangle(160.0f, 16.0f, 128.0f, 144.0f);
    private static final Rectangle c = new Rectangle(480.0f, 79.0f, 160.0f, 48.0f);
    private static final Rectangle d = new Rectangle(640.0f, 79.0f, 160.0f, 48.0f);
    private static final Rectangle e = new Rectangle(324.0f, 35.0f, 156.0f, 40.0f);
    private static final Rectangle f = new Rectangle(324.0f, -6.0f, 156.0f, 40.0f);
    private static final Rectangle g = new Rectangle(0.0f, 0.0f, 192.0f, 192.0f);
    private static final Rectangle h = new Rectangle(270.0f, 100.0f, 112.0f, 112.0f);
    private static final Rectangle i = new Rectangle(320.0f, 79.0f, 160.0f, 48.0f);
    private static final Rectangle j = new Rectangle(192.0f, 0.0f, 128.0f, 128.0f);
    private static final StringBuilder O = new StringBuilder();
    private final UiManager.UiLayer k = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 101, "MainUi particles");
    private final UiManager.UiLayer l = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 101, "MainUi custom elements");
    private final UiManager.UiLayer m = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 101, "MainUi");
    private final UiManager.UiLayer n = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 102, "MainUi boss hp lines");
    public Array<BossHpBar> bossHpBars = new Array<>(true, 1, BossHpBar.class);
    private int ak = -1;
    private final Runnable am = this::updateMdps;
    private final Runnable an = this::updateScore;
    private final Runnable ao = this::updateMoney;
    private final Runnable ap = this::updateHealth;
    private final Runnable aq = this::updateWave;
    private final Runnable ar = this::updateGameSpeedButton;
    private final Runnable as = this::updateForceWaveButton;
    private final Runnable at = this::a;

    public MainUi(final GameSystemProvider gameSystemProvider) {
        this.o = gameSystemProvider;
        this.ai = TooltipsOverlay.i().isTagShown("MainUi.detailedMode");
        if (gameSystemProvider.gameState.basicLevelName != null && gameSystemProvider.gameState.basicLevelName.startsWith("0.")) {
            this.ai = true;
        }
        f3323a.i("detailedModeTooltipDisabled " + this.ai, new Object[0]);
        this.particlesCanvas = new ParticlesCanvas();
        this.k.getTable().add((Table) this.particlesCanvas).expand().fill();
        this.k.getTable().setTouchable(Touchable.disabled);
        this.customElementsContainer = new Group();
        this.customElementsContainer.setTransform(false);
        this.l.getTable().add((Table) this.customElementsContainer).expand().fill();
        this.l.getTable().setTouchable(Touchable.disabled);
        this.al = screenResize -> {
            this.particlesCanvas.setSize(Game.i.uiManager.viewport.getWorldWidth(), Game.i.uiManager.viewport.getWorldHeight());
            this.customElementsContainer.setSize(Game.i.uiManager.viewport.getWorldWidth(), Game.i.uiManager.viewport.getWorldHeight());
        };
        Game.EVENTS.getListeners(ScreenResize.class).add(this.al);
        Table table = this.m.getTable();
        table.setName("main_game_ui");
        Group group = new Group();
        group.setTransform(false);
        group.setSize(840.0f, 160.0f);
        group.setTouchable(Touchable.childrenOnly);
        table.add((Table) group).expand().top().left().row();
        PaddedImageButton iconSize = new PaddedImageButton(Game.i.assetManager.getDrawable("icon-pause"), () -> {
            if (gameSystemProvider.gameState == null) {
                f3323a.e("pauseButton click skipped - S does not have gameState system (may be disposed)", new Object[0]);
            } else {
                gameSystemProvider.gameState.pauseGame();
            }
        }, Color.WHITE, MaterialColor.LIGHT_BLUE.P500, MaterialColor.LIGHT_BLUE.P600).setIconPosition(32.0f, 32.0f).setIconSize(96.0f, 96.0f);
        iconSize.setName("game_pause_button");
        iconSize.setSize(160.0f, 160.0f);
        group.addActor(iconSize);
        this.aa = new ParticlesCanvas();
        this.aa.setPosition(117.0f, 46.0f);
        this.aa.setSize(1.0f, 1.0f);
        this.aa.setVisible(false);
        iconSize.addActor(this.aa);
        this.ab = new ParticleEffect();
        this.ab.load(Gdx.files.internal("particles/pause-menu-ad-icon.prt"), Game.i.assetManager.getTextureRegion("particle-snowflake").getAtlas());
        this.ab.setEmittersCleanUpBlendFunction(false);
        this.Z = new Image(Game.i.assetManager.getDrawable("ui-pause-button-video-ad-icon"));
        this.Z.setSize(48.0f, 48.0f);
        this.Z.setOrigin(24.0f, 24.0f);
        this.Z.setPosition(93.0f, 38.0f);
        this.Z.setTouchable(Touchable.disabled);
        this.Z.setVisible(false);
        iconSize.addActor(this.Z);
        this.P = new PaddedImageButton(Game.i.assetManager.getDrawable("icon-easel"), () -> {
            gameSystemProvider._mapRendering.switchMapDrawMode();
        }, Color.WHITE, MaterialColor.LIGHT_BLUE.P500, MaterialColor.LIGHT_BLUE.P600).setIconPosition(16.0f, 16.0f).setIconSize(96.0f, 96.0f);
        this.P.setName("map_draw_mode_button");
        this.P.setOrigin(f3324b.width / 2.0f, f3324b.height / 2.0f);
        this.P.setSize(f3324b.width, f3324b.height);
        this.P.setPosition(f3324b.x, f3324b.y);
        gameSystemProvider.events.getListeners(MapDrawModeChange.class).add(mapDrawModeChange -> {
            if (gameSystemProvider.gameState.basicLevelName == null || !gameSystemProvider.gameState.basicLevelName.startsWith("0.")) {
                TooltipsOverlay.i().markTagShown("MainUi.detailedMode");
            }
            TooltipsOverlay.i().hideEntry("MainUi.detailedMode");
            this.ai = true;
        }).setDescription("MainUi - hides detailed mode tooltip");
        if (HotKeyHintLabel.isEnabled()) {
            this.P.addActor(new HotKeyHintLabel(Game.i.settingsManager.getHotKey(SettingsManager.HotkeyAction.SWITCH_DRAW_MODE), 64.0f, 112.0f));
        }
        group.addActor(this.P);
        this.T = new Group();
        this.T.setTransform(false);
        this.T.setPosition(e.x, e.y);
        this.T.setSize(e.width, e.height);
        this.T.setOrigin(e.width / 2.0f, e.height / 2.0f);
        group.addActor(this.T);
        this.Y = new Image(Game.i.assetManager.getDrawable("icon-star-hollow"));
        this.Y.setPosition(0.0f, 0.0f);
        this.Y.setSize(40.0f, 40.0f);
        this.Y.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        this.T.addActor(this.Y);
        Table table2 = new Table();
        table2.setSize(400.0f, 40.0f);
        this.T.addActor(table2);
        this.p = new Label("000", new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
        this.p.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        table2.add((Table) this.p).top().left().padLeft(56.0f).height(40.0f);
        if (gameSystemProvider.gameState.gameMode == GameStateSystem.GameMode.USER_MAPS && Game.i.gameValueManager.getSnapshot().getBooleanValue(GameValueType.PRESTIGE_MODE)) {
            Image image = new Image(Game.i.assetManager.getDrawable("icon-crown"));
            image.setColor(MaterialColor.AMBER.P500);
            table2.add((Table) image).size(40.0f).padLeft(32.0f);
            Label label = new Label(StringFormatter.commaSeparatedNumber(MapPrestigeConfig.getMaxPrestigeScore(gameSystemProvider.gameState.averageDifficulty, gameSystemProvider.map.getMap().getTargetTileOrThrow().isUseStockGameValues())), new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
            label.setColor(MaterialColor.AMBER.P500);
            table2.add((Table) label).top().left().padLeft(16.0f).height(40.0f);
        }
        table2.add().height(40.0f).expandX().fillX();
        this.U = new Group();
        this.U.setTransform(false);
        this.U.setPosition(f.x, f.y);
        this.U.setSize(f.width, f.height);
        this.U.setOrigin(f.width / 2.0f, f.height / 2.0f);
        group.addActor(this.U);
        Image image2 = new Image(Game.i.assetManager.getDrawable("icon-mdps"));
        image2.setPosition(0.0f, 0.0f);
        image2.setSize(40.0f, 40.0f);
        image2.setColor(MaterialColor.PURPLE.P300);
        this.U.addActor(image2);
        this.q = new Label("0", new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
        this.q.setColor(MaterialColor.PURPLE.P300);
        this.q.setPosition(56.0f, 0.0f);
        this.q.setSize(100.0f, 40.0f);
        this.U.addActor(this.q);
        this.V = new Group();
        this.V.setTransform(false);
        this.V.setPosition(i.x, i.y);
        this.V.setSize(i.width, i.height);
        this.V.setOrigin(i.width / 2.0f, i.height / 2.0f);
        group.addActor(this.V);
        Image image3 = new Image(Game.i.assetManager.getDrawable("icon-wave"));
        image3.setPosition(0.0f, 0.0f);
        image3.setSize(48.0f, 48.0f);
        image3.setColor(Color.WHITE);
        this.V.addActor(image3);
        this.r = new Label("123", new Label.LabelStyle(Game.i.assetManager.getFont(36), Color.WHITE));
        this.r.setPosition(60.0f, 0.0f);
        this.r.setSize(100.0f, 48.0f);
        this.V.addActor(this.r);
        this.Q = new Group();
        this.Q.setTransform(false);
        this.Q.setPosition(c.x, c.y);
        this.Q.setSize(c.width, c.height);
        this.Q.setOrigin(c.width / 2.0f, c.height / 2.0f);
        group.addActor(this.Q);
        Image image4 = new Image(Game.i.assetManager.getDrawable("game-ui-health-icon"));
        image4.setPosition(0.0f, 0.0f);
        image4.setSize(48.0f, 48.0f);
        this.Q.addActor(image4);
        this.t = new Group();
        this.t.setTransform(false);
        this.t.setPosition(60.0f, 0.0f);
        this.t.setSize(100.0f, 48.0f);
        this.t.setOrigin(0.0f, 24.0f);
        this.Q.addActor(this.t);
        this.u = new Group();
        this.u.setTransform(false);
        this.u.setPosition(60.0f, 0.0f);
        this.u.setSize(100.0f, 48.0f);
        this.Q.addActor(this.u);
        this.s = new Label("456", new Label.LabelStyle(Game.i.assetManager.getFont(36), Color.WHITE));
        this.s.setSize(100.0f, 48.0f);
        this.t.addActor(this.s);
        this.R = new Group();
        this.R.setTransform(false);
        this.R.setPosition(d.x, d.y);
        this.R.setSize(d.width, d.height);
        this.R.setOrigin(d.width / 2.0f, d.height / 2.0f);
        group.addActor(this.R);
        Image image5 = new Image(Game.i.assetManager.getDrawable("game-ui-coin-icon"));
        image5.setPosition(0.0f, 0.0f);
        image5.setSize(48.0f, 48.0f);
        this.R.addActor(image5);
        this.v = new Label("789", new Label.LabelStyle(Game.i.assetManager.getFont(36), Color.WHITE));
        this.v.setPosition(60.0f, 0.0f);
        this.v.setSize(100.0f, 48.0f);
        this.R.addActor(this.v);
        this.w = new Image(Game.i.assetManager.getDrawable("icon-x2"));
        this.w.setSize(40.0f, 40.0f);
        this.w.setColor(MaterialColor.LIGHT_GREEN.P500);
        this.w.setPosition(648.0f, 35.0f);
        this.w.setVisible(false);
        group.addActor(this.w);
        this.y = new Table();
        this.y.setVisible(false);
        this.y.setPosition(700.0f, 35.0f);
        this.y.setSize(150.0f, 40.0f);
        group.addActor(this.y);
        this.x = new Label("789", new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
        this.x.setColor(MaterialColor.LIGHT_GREEN.P500.cpy().mul(1.0f, 1.0f, 1.0f, 0.56f));
        this.x.setAlignment(8);
        this.y.add((Table) this.x).height(40.0f);
        final Image image6 = new Image(Game.i.assetManager.getDrawable("icon-info-circle"));
        image6.setColor(MaterialColor.LIGHT_GREEN.P300.cpy().mul(1.0f, 1.0f, 1.0f, 0.21f));
        this.y.add((Table) image6).size(20.0f).padLeft(4.0f);
        this.y.add().height(1.0f).growX();
        this.z = new Actor();
        this.z.setTouchable(Touchable.enabled);
        this.z.addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.components.MainUi.1
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f2, float f3) {
                TooltipsOverlay.i().showText(TooltipsOverlay.TAG_GENERIC_TOOLTIP, image6, Game.i.localeManager.i18n.get("double_multiplier_timer_hint"), MainUi.this.m.mainUiLayer, MainUi.this.m.zIndex + 1, 4);
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public void enter(InputEvent inputEvent, float f2, float f3, int i2, Actor actor) {
                super.enter(inputEvent, f2, f3, i2, actor);
                MainUi.this.w.setColor(MaterialColor.LIGHT_GREEN.P300);
                MainUi.this.x.getColor().f889a = 1.0f;
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public void exit(InputEvent inputEvent, float f2, float f3, int i2, Actor actor) {
                super.exit(inputEvent, f2, f3, i2, actor);
                MainUi.this.w.setColor(MaterialColor.LIGHT_GREEN.P500);
                MainUi.this.x.getColor().f889a = 0.56f;
            }
        });
        this.z.setSize(200.0f, 72.0f);
        this.z.setPosition(624.0f, 19.0f);
        group.addActor(this.z);
        Group group2 = new Group();
        group2.setTransform(false);
        group2.setSize(320.0f, 621.0f);
        group2.setTouchable(Touchable.childrenOnly);
        table.add((Table) group2).expand().bottom().left();
        this.S = new Group();
        this.S.setTransform(false);
        this.S.setPosition(g.x, g.y);
        this.S.setSize(g.width, g.height);
        this.S.setOrigin(g.width / 2.0f, g.height / 2.0f);
        group2.addActor(this.S);
        this.D = new Image(Game.i.assetManager.getDrawable("icon-stopwatch"));
        this.D.setPosition(32.0f, 32.0f);
        this.D.setSize(128.0f, 128.0f);
        this.D.setTouchable(Touchable.disabled);
        this.S.addActor(this.D);
        TextureRegionDrawable drawable = Game.i.assetManager.getDrawable("icon-stopwatch");
        Color color = Color.WHITE;
        this.B = new PaddedImageButton(drawable, null, color, color, Color.WHITE);
        this.B.setName("next_wave_call_button");
        this.B.addListener(new InputListener() { // from class: com.prineside.tdi2.ui.components.MainUi.2
            private final Timer.Task c = new Timer.Task() { // from class: com.prineside.tdi2.ui.components.MainUi.2.1
                @Override // com.badlogic.gdx.utils.Timer.Task, java.lang.Runnable
                public void run() {
                    gameSystemProvider.wave.setAutoForceWaveEnabled(true);
                    MainUi.this.updateForceWaveButton();
                    if (!gameSystemProvider.gameState.isFastForwarding()) {
                        gameSystemProvider._sound.playStatic(StaticSoundType.AUTO_FORCE_WAVE);
                    }
                    if (AnonymousClass2.this.d != null) {
                        AnonymousClass2.this.d.disappearing = true;
                        AnonymousClass2.this.d = null;
                    }
                }
            };
            private ButtonHoldHint d;

            @Override // com.prineside.tdi2.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
                if (i3 == 0) {
                    if (this.c.isScheduled()) {
                        this.c.cancel();
                    }
                    Timer.schedule(this.c, 0.35f);
                    this.d = new ButtonHoldHint(f2, f3, 0.35f);
                    MainUi.this.B.addActor(this.d);
                    return true;
                }
                if (i3 == 1 && Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.INSTANT_HOLD_BUTTON_ON_RMB) == 1.0d) {
                    if (this.c.isScheduled()) {
                        this.c.cancel();
                    }
                    this.c.run();
                    return true;
                }
                return true;
            }

            @Override // com.prineside.tdi2.scene2d.InputListener
            public void touchUp(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
                if (this.c.isScheduled()) {
                    if (MainUi.this.C && gameSystemProvider.wave.isForceWaveAvailable()) {
                        gameSystemProvider.wave.forceNextWaveAction();
                    }
                    gameSystemProvider.wave.setAutoForceWaveEnabled(false);
                    MainUi.this.updateForceWaveButton();
                }
                this.c.cancel();
                if (this.d != null) {
                    ButtonHoldHint buttonHoldHint = this.d;
                    Threads i4 = Threads.i();
                    Objects.requireNonNull(buttonHoldHint);
                    i4.postRunnable(buttonHoldHint::remove);
                    this.d = null;
                }
            }
        });
        this.B.setSize(192.0f, 192.0f);
        this.B.setIconSize(128.0f, 128.0f);
        this.B.setIconPosition(32.0f, 32.0f);
        this.B.setDisabledColor(new Color(1.0f, 1.0f, 1.0f, 0.28f));
        Image image7 = new Image(Game.i.assetManager.getDrawable("button-hold-mark-white"));
        image7.setSize(20.0f, 20.0f);
        image7.setPosition(32.0f, 32.0f);
        this.B.addActor(image7);
        this.S.addActor(this.B);
        if (HotKeyHintLabel.isEnabled()) {
            HotKeyHintLabel hotKeyHintLabel = new HotKeyHintLabel(Game.i.settingsManager.getHotKey(SettingsManager.HotkeyAction.CALL_WAVE), 96.0f, 12.0f);
            hotKeyHintLabel.addVariant(Game.i.settingsManager.getHotKey(SettingsManager.HotkeyAction.TOGGLE_AUTO_WAVE_CALL));
            this.B.addActor(hotKeyHintLabel);
        }
        this.E = new Image(Game.i.assetManager.getDrawable("ui-stopwatch-timer-background"));
        this.E.setPosition(119.0f, 32.0f);
        this.E.setSize(42.0f, 42.0f);
        this.E.setTouchable(Touchable.disabled);
        this.S.addActor(this.E);
        this.F = new Label("", new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
        this.F.setSize(50.0f, 24.0f);
        this.F.setPosition(128.0f, 42.0f);
        this.F.setTouchable(Touchable.disabled);
        this.S.addActor(this.F);
        this.I = new Image(Game.i.assetManager.getDrawable("ui-auto-force-wave-overlay"));
        this.I.setTouchable(Touchable.disabled);
        this.I.setPosition(32.0f, 32.0f);
        this.I.setSize(137.0f, 93.0f);
        this.S.addActor(this.I);
        this.G = new Label("", new Label.LabelStyle(Game.i.assetManager.getFont(24), MaterialColor.YELLOW.P500));
        this.G.setPosition(0.0f, 166.0f);
        this.G.setSize(113.0f, 32.0f);
        this.G.setTouchable(Touchable.disabled);
        this.G.setAlignment(16);
        this.S.addActor(this.G);
        this.H = new Image(Game.i.assetManager.getDrawable("game-ui-coin-icon"));
        this.H.setPosition(120.0f, 169.0f);
        this.H.setSize(24.0f, 24.0f);
        this.H.setTouchable(Touchable.disabled);
        this.S.addActor(this.H);
        this.ac = Game.i.assetManager.getDrawable("icon-speed-pause");
        this.ad = Game.i.assetManager.getDrawable("icon-speed-low");
        this.ae = Game.i.assetManager.getDrawable("icon-speed-medium");
        this.af = Game.i.assetManager.getDrawable("icon-speed-high");
        this.W = new Group();
        this.W.setName("game_speed_toggle_button");
        this.W.setTransform(false);
        this.W.setPosition(j.x, j.y);
        this.W.setSize(j.width, j.height);
        this.W.setOrigin(j.width / 2.0f, j.height / 2.0f);
        group2.addActor(this.W);
        this.J = new Image(Game.i.assetManager.getDrawable("icon-speed-pause"));
        this.J.setPosition(32.0f, 32.0f);
        this.J.setSize(96.0f, 96.0f);
        this.J.setTouchable(Touchable.disabled);
        this.W.addActor(this.J);
        this.K = new PaddedImageButton(this.ad, null, Color.WHITE, MaterialColor.LIGHT_BLUE.P500, MaterialColor.LIGHT_BLUE.P600);
        this.K.addListener(new InputListener() { // from class: com.prineside.tdi2.ui.components.MainUi.3

            /* renamed from: b, reason: collision with root package name */
            private final Timer.Task f3330b = new Timer.Task() { // from class: com.prineside.tdi2.ui.components.MainUi.3.1
                @Override // com.badlogic.gdx.utils.Timer.Task, java.lang.Runnable
                public void run() {
                    gameSystemProvider.gameState.setGameSpeed(Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.SLOW_MOTION_PAUSE) == 0.0d ? 0.0f : 0.0667f);
                    if (AnonymousClass3.this.c != null) {
                        AnonymousClass3.this.c.disappearing = true;
                        AnonymousClass3.this.c = null;
                    }
                }
            };
            private ButtonHoldHint c;

            @Override // com.prineside.tdi2.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
                if (i3 == 0) {
                    if (this.f3330b.isScheduled()) {
                        this.f3330b.cancel();
                    }
                    Timer.schedule(this.f3330b, 0.25f);
                    this.c = new ButtonHoldHint(f2, f3, 0.25f);
                    MainUi.this.K.addActor(this.c);
                    return true;
                }
                if (i3 == 1 && Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.INSTANT_HOLD_BUTTON_ON_RMB) == 1.0d) {
                    if (this.f3330b.isScheduled()) {
                        this.f3330b.cancel();
                    }
                    this.f3330b.run();
                    return true;
                }
                return true;
            }

            @Override // com.prineside.tdi2.scene2d.InputListener
            public void touchUp(InputEvent inputEvent, float f2, float f3, int i2, int i3) {
                if (this.f3330b.isScheduled()) {
                    gameSystemProvider.gameState.switchGameSpeed();
                }
                this.f3330b.cancel();
                if (this.c != null) {
                    ButtonHoldHint buttonHoldHint = this.c;
                    Threads i4 = Threads.i();
                    Objects.requireNonNull(buttonHoldHint);
                    i4.postRunnable(buttonHoldHint::remove);
                    this.c = null;
                }
            }
        });
        Image image8 = new Image(Game.i.assetManager.getDrawable("button-hold-mark-white"));
        image8.setSize(20.0f, 20.0f);
        image8.setPosition(7.0f, 32.0f);
        this.K.addActor(image8);
        this.K.setSize(128.0f, 128.0f);
        this.K.setIconSize(96.0f, 96.0f);
        this.K.setIconPosition(32.0f, 32.0f);
        this.K.setPosition(0.0f, 0.0f);
        this.W.addActor(this.K);
        if (HotKeyHintLabel.isEnabled()) {
            HotKeyHintLabel hotKeyHintLabel2 = new HotKeyHintLabel(Game.i.settingsManager.getHotKey(SettingsManager.HotkeyAction.PAUSE_GAME), 80.0f, 12.0f);
            hotKeyHintLabel2.addVariant(Game.i.settingsManager.getHotKey(SettingsManager.HotkeyAction.SPEED_DOWN));
            hotKeyHintLabel2.addVariant(Game.i.settingsManager.getHotKey(SettingsManager.HotkeyAction.SPEED_UP));
            this.K.addActor(hotKeyHintLabel2);
        }
        this.L = new PaddedImageButton(Game.i.assetManager.getDrawable("icon-step-forward"), () -> {
            gameSystemProvider.gameState.requireUpdate();
        }, Color.WHITE, MaterialColor.LIGHT_BLUE.P500, MaterialColor.LIGHT_BLUE.P600);
        this.L.setSize(112.0f, 92.0f);
        this.L.setIconSize(40.0f, 40.0f);
        this.L.setIconPosition(36.0f, 26.0f);
        this.L.setPosition(270.0f, 120.0f);
        this.L.setVisible(false);
        group2.addActor(this.L);
        this.X = new PaddedImageButton(Game.i.assetManager.getDrawable("icon-letter"), () -> {
            gameSystemProvider._gameUi.storylineMessages.show();
        }, Color.WHITE, MaterialColor.LIGHT_BLUE.P500, MaterialColor.LIGHT_BLUE.P700);
        this.X.setName("story_line_messages_button");
        this.X.setIconPosition(24.0f, 32.0f);
        this.X.setIconSize(64.0f, 64.0f);
        this.X.setSize(h.width, h.height);
        this.X.setPosition(h.x, h.y);
        this.X.setOrigin(h.width / 2.0f, h.height / 2.0f);
        this.X.setVisible(false);
        group2.addActor(this.X);
        this.gameplayBonusGroup = new GameplayBonusSummary(gameSystemProvider);
        this.gameplayBonusGroup.setPosition(360.0f, 40.0f);
        this.gameplayBonusGroup.setVisible(false);
        group2.addActor(this.gameplayBonusGroup);
        this.bossHpLinesTable = new Table();
        this.bossHpLinesTable.setTouchable(Touchable.disabled);
        this.n.getTable().add(this.bossHpLinesTable).padTop(120.0f).height(240.0f).width(660.0f).row();
        this.n.getTable().add().width(1.0f).growY();
        a(false);
        updateAll();
        gameSystemProvider.events.getListeners(MdpsUpdate.class).add(mdpsUpdate -> {
            Game.i.uiManager.runOnStageActOnce(this.am);
        }).setDescription("MainUi - updates MDPS in UI");
        gameSystemProvider.events.getListeners(BonusPointsUpdate.class).add(this::a).setDescription("MainUi - updates bonus menu and shows the overlay if needed");
        gameSystemProvider.events.getListeners(BonusSelect.class).add(bonusSelect -> {
            updateGameplayBonus();
        }).setDescription("MainUi - updates bonus menu");
        gameSystemProvider.events.getListeners(BonusStagesConfigSet.class).add(bonusStagesConfigSet -> {
            updateGameplayBonus();
        }).setDescription("MainUi - updates bonus menu");
        gameSystemProvider.events.getListeners(ScoreChange.class).add(scoreChange -> {
            Game.i.uiManager.runOnStageActOnce(this.an);
        }).setDescription("MainUi - updates score in UI");
        gameSystemProvider.events.getListeners(CoinsChange.class).add(coinsChange -> {
            Game.i.uiManager.runOnStageActOnce(this.ao);
        }).setDescription("MainUi - updates coins in UI");
        gameSystemProvider.events.getListeners(WaveStatusChange.class).add(waveStatusChange -> {
            Game.i.uiManager.runOnStageActOnce(this.aq);
        });
        gameSystemProvider.events.getListeners(ForceWaveAvailabilityChange.class).add(forceWaveAvailabilityChange -> {
            Game.i.uiManager.runOnStageActOnce(this.as);
        });
        gameSystemProvider.events.getListeners(BaseHealthChange.class).add(baseHealthChange -> {
            Game.i.uiManager.runOnStageActOnce(this.ap);
        }).setDescription("MainUi - updates HP in UI");
        gameSystemProvider.events.getListeners(GameSpeedChange.class).add(gameSpeedChange -> {
            Game.i.uiManager.runOnStageActOnce(this.ar);
        }).setDescription("MainUi - updates game speed button");
        gameSystemProvider.events.getListeners(RewardingAdBecameAvailable.class).add(rewardingAdBecameAvailable -> {
            Game.i.uiManager.runOnStageActOnce(this.at);
        }).setDescription("MainUi - updates ad icon");
        gameSystemProvider.events.getListeners(RewardingAdRegistered.class).add(rewardingAdRegistered -> {
            Game.i.uiManager.runOnStageActOnce(this.at);
        }).setDescription("MainUi - updates ad icon");
    }

    private void a(BonusPointsUpdate bonusPointsUpdate) {
        BonusSystem.BonusStage stageToChooseBonusFor;
        BonusSystem.BonusStage stageToChooseBonusFor2;
        updateGameplayBonus();
        if (this.o.gameState.replayMode) {
            return;
        }
        if ((Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.SHOW_BONUS_SELECTION_IMMEDIATELY) != 0.0d || this.o.bonus.getStagesConfig().forceImmediateSelection) && (stageToChooseBonusFor = this.o.bonus.getStageToChooseBonusFor()) != null && this.ak != stageToChooseBonusFor.getNumber()) {
            this.ak = stageToChooseBonusFor.getNumber();
            boolean z = false;
            if (this.o.bonus.isAutoSelectionOnSingleBonus() && (stageToChooseBonusFor2 = this.o.bonus.getStageToChooseBonusFor()) != null && stageToChooseBonusFor2.getBonusesToChooseFrom().size == 1 && stageToChooseBonusFor2.getBonusesToChooseFrom().first().getNotSatisfiedPreconditions(this.o) == null) {
                this.o.bonus.selectBonusAction(0);
                z = true;
            }
            if (!z && !this.o._gameUi.gameplayBonusesOverlay.isVisible()) {
                this.o._gameUi.gameplayBonusesOverlay.show();
            }
        }
    }

    public void addBossHpBar(BossHpBar bossHpBar) {
        if (!this.bossHpBars.contains(bossHpBar, true)) {
            this.bossHpBars.add(bossHpBar);
            layoutBossHpBars();
        }
    }

    public void removeBossHpBar(BossHpBar bossHpBar) {
        if (this.bossHpBars.removeValue(bossHpBar, true)) {
            layoutBossHpBars();
        }
    }

    public void layoutBossHpBars() {
        this.bossHpLinesTable.clear();
        int i2 = 0;
        Cell cell = null;
        for (int i3 = 0; i3 < this.bossHpBars.size; i3++) {
            cell = this.bossHpLinesTable.add((Table) this.bossHpBars.get(i3));
            i2++;
            if (i2 % 2 == 0) {
                cell.padLeft(36.0f).row();
                this.bossHpLinesTable.add().width(1.0f).height(8.0f).row();
            }
        }
        if (i2 % 2 == 1) {
            cell.colspan(2);
        }
        this.bossHpLinesTable.row();
        this.bossHpLinesTable.add().width(1.0f).growY();
    }

    public void showHealthDelta(int i2) {
        this.t.clearActions();
        this.t.setTransform(true);
        this.t.addAction(Actions.sequence(Actions.scaleTo(1.3f, 1.3f), Actions.scaleTo(1.0f, 1.0f, 0.25f), Actions.run(() -> {
            this.t.setTransform(false);
        })));
        Label label = new Label("0", Game.i.assetManager.getLabelStyle(30));
        if (i2 <= 0) {
            label.setColor(MaterialColor.GREY.P500);
        } else {
            label.setText("-" + i2);
            label.setColor(MaterialColor.RED.P300);
        }
        label.setPosition(0.0f, -18.0f);
        label.addAction(Actions.sequence(Actions.parallel(Actions.moveBy(0.0f, -32.0f, 0.35f), Actions.sequence(Actions.delay(0.1f), Actions.fadeOut(0.25f), Actions.removeActor()))));
        this.u.addActor(label);
    }

    private void a() {
        if (Game.i.progressManager.isPremiumStatusActive()) {
            this.Z.setVisible(false);
            return;
        }
        if (this.o.loot.isRewardingAdAvailableInReality() && Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.ENABLE_PAUSE_AD_ICON) != 0.0d) {
            if (!this.Z.isVisible()) {
                this.Z.setVisible(true);
                this.Z.clearActions();
                this.Z.addAction(Actions.parallel(Actions.sequence(Actions.scaleTo(0.0f, 0.0f), Actions.scaleTo(1.0f, 1.0f, 0.2f, Interpolation.swingOut)), Actions.forever(Actions.sequence(Actions.color(MaterialColor.LIGHT_GREEN.P800, 0.6f), Actions.color(MaterialColor.LIGHT_GREEN.P300, 0.4f)))));
                this.aa.setVisible(true);
                this.aa.clearActions();
                this.aa.addAction(Actions.sequence(Actions.delay(3.0f), Actions.fadeOut(1.5f)));
                this.aa.clearParticles();
                this.aa.addParticle(this.ab, 0.0f, 0.0f);
                return;
            }
            return;
        }
        this.Z.clearActions();
        this.Z.setVisible(false);
        this.aa.clearActions();
        this.aa.setVisible(false);
    }

    public void postSetup() {
        if (!this.o.gameValue.getBooleanValue(GameValueType.MDPS_COUNTER)) {
            this.U.setVisible(false);
        }
    }

    public void setLevelStarsIcon(int i2) {
        this.Y.addAction(Actions.sequence(Actions.parallel(Actions.moveTo(-12.0f, -12.0f, 0.25f, Interpolation.exp5In), Actions.sizeTo(64.0f, 64.0f, 0.25f, Interpolation.exp5In), Actions.color(MaterialColor.YELLOW.P500, 0.25f)), Actions.parallel(Actions.moveTo(0.0f, 0.0f, 0.5f, Interpolation.exp5In), Actions.sizeTo(40.0f, 40.0f, 0.5f, Interpolation.exp5In), Actions.color(new Color(1.0f, 1.0f, 1.0f, 0.56f), 1.0f))));
        switch (i2) {
            case 0:
                this.Y.setDrawable(Game.i.assetManager.getDrawable("icon-star-hollow"));
                break;
            case 1:
                this.Y.setDrawable(Game.i.assetManager.getDrawable("icon-star"));
                break;
            case 2:
                this.Y.setDrawable(Game.i.assetManager.getDrawable("icon-two-stars"));
                break;
            case 3:
                this.Y.setDrawable(Game.i.assetManager.getDrawable("icon-three-stars"));
                break;
        }
        if (this.o.gameState.basicLevelName != null && !this.o.gameState.basicLevelName.startsWith("0.")) {
            if (i2 == 1 && !TooltipsOverlay.i().isTagShown("MainUi.oneStar")) {
                TooltipsOverlay.i().showText("MainUi.oneStar", this.Y, Game.i.localeManager.i18n.get("tooltip_level_one_star"), this.m.mainUiLayer, this.m.zIndex + 1, 4);
            }
            if (i2 == 3 && !TooltipsOverlay.i().isTagShown("MainUi.threeStars")) {
                TooltipsOverlay.i().hideEntry("MainUi.oneStar");
                TooltipsOverlay.i().showText("MainUi.threeStars", this.Y, Game.i.localeManager.i18n.get("tooltip_level_three_stars"), this.m.mainUiLayer, this.m.zIndex + 1, 4);
            }
        }
    }

    public void finalFadeOut() {
        this.m.getTable().setTouchable(Touchable.disabled);
        this.m.getTable().clearActions();
        this.m.getTable().addAction(Actions.alpha(0.0f, 1.0f));
        this.ag = true;
        TooltipsOverlay.i().hideEntry("MainUi.detailedMode");
        TooltipsOverlay.i().hideEntry("MainUi.oneStar");
        TooltipsOverlay.i().hideEntry("MainUi.threeStars");
        this.ai = true;
    }

    public void setUiScreenshotMode(GameUiSystem.ScreenshotModeConfig screenshotModeConfig) {
    }

    public boolean gameSpeedButtonVisible() {
        if (this.ag) {
            return false;
        }
        return this.W.isVisible();
    }

    public boolean nextWaveButtonVisible() {
        if (this.ag) {
            return false;
        }
        return this.S.isVisible();
    }

    private void a(boolean z) {
        this.C = z;
        if (z) {
            this.B.setColors(Color.WHITE, MaterialColor.LIGHT_BLUE.P500, MaterialColor.LIGHT_BLUE.P600);
        } else if (this.o.wave.isAutoForceWaveEnabled()) {
            this.B.setColors(Color.WHITE, MaterialColor.GREY.P400, MaterialColor.GREY.P500);
        } else {
            Color disabledColor = this.B.getDisabledColor();
            this.B.setColors(disabledColor, disabledColor, disabledColor);
        }
    }

    public void hideGameSpeedButton() {
        this.W.clearActions();
        this.W.setVisible(false);
    }

    public void showGameSpeedButton(boolean z, @Null Runnable runnable) {
        this.W.clearActions();
        this.W.setVisible(true);
        this.W.setTransform(false);
        if (z) {
            this.o._gameUi.uiElementsEmphasizer.show(this.W, j, Game.i.localeManager.i18n.get("main_ui_game_speed_button_title"), Game.i.localeManager.i18n.get("main_ui_game_speed_button_description"), () -> {
                updateGameSpeedButton();
                if (runnable != null) {
                    runnable.run();
                }
            });
            return;
        }
        if (runnable != null) {
            runnable.run();
        }
        updateGameSpeedButton();
    }

    public void hideCoins() {
        this.R.clearActions();
        this.R.setVisible(false);
    }

    public void showCoins(boolean z, @Null Runnable runnable) {
        this.R.clearActions();
        this.R.setVisible(true);
        this.R.setTransform(false);
        if (z) {
            this.o._gameUi.uiElementsEmphasizer.show(this.R, d, Game.i.localeManager.i18n.get("coins"), Game.i.localeManager.i18n.get("main_ui_coins_description"), runnable);
        } else if (runnable != null) {
            runnable.run();
        }
    }

    public void hideMessagesButton() {
        this.X.clearActions();
        this.X.setVisible(false);
    }

    public void showMessagesButton(boolean z, @Null Runnable runnable) {
        this.X.clearActions();
        this.X.setVisible(true);
        this.X.setTransform(false);
        if (z) {
            this.o._gameUi.uiElementsEmphasizer.show(this.X, h, Game.i.localeManager.i18n.get("messages"), Game.i.localeManager.i18n.get("main_ui_messages_button_description"), runnable);
        } else if (runnable != null) {
            runnable.run();
        }
        this.L.setVisible(false);
    }

    public void hideScore() {
        this.T.clearActions();
        this.T.setVisible(false);
    }

    public void showScore(boolean z, @Null Runnable runnable) {
        this.T.clearActions();
        this.T.setVisible(true);
        this.T.setTransform(false);
        if (z) {
            this.o._gameUi.uiElementsEmphasizer.show(this.T, e, Game.i.localeManager.i18n.get("score"), Game.i.localeManager.i18n.get("main_ui_score_description"), runnable);
        } else if (runnable != null) {
            runnable.run();
        }
    }

    public void hideWaveNumber() {
        this.V.clearActions();
        this.V.setVisible(false);
    }

    public void showWaveNumber(boolean z, @Null Runnable runnable) {
        this.V.clearActions();
        this.V.setVisible(true);
        this.V.setTransform(false);
        if (z) {
            this.o._gameUi.uiElementsEmphasizer.show(this.V, i, Game.i.localeManager.i18n.get("main_ui_wave_title"), Game.i.localeManager.i18n.get("main_ui_wave_description"), runnable);
        } else if (runnable != null) {
            runnable.run();
        }
    }

    public void hideHealth() {
        this.Q.clearActions();
        this.Q.setVisible(false);
    }

    public void showHealth(boolean z, @Null Runnable runnable) {
        this.Q.clearActions();
        this.Q.setVisible(true);
        this.Q.setTransform(false);
        if (z) {
            this.o._gameUi.uiElementsEmphasizer.show(this.Q, c, Game.i.localeManager.i18n.get("main_ui_health_title"), Game.i.localeManager.i18n.get("main_ui_health_description"), runnable);
        } else if (runnable != null) {
            runnable.run();
        }
    }

    public void hideDrawModeButton() {
        this.P.clearActions();
        this.P.setVisible(false);
    }

    public void showDrawModeButton(boolean z, @Null Runnable runnable) {
        this.P.clearActions();
        this.P.setVisible(true);
        this.P.setTransform(false);
        if (z) {
            this.o._gameUi.uiElementsEmphasizer.show(this.P, f3324b, Game.i.localeManager.i18n.get("main_ui_drawing_mode_title"), Game.i.localeManager.i18n.get("main_ui_drawing_mode_description"), runnable);
        } else if (runnable != null) {
            runnable.run();
        }
    }

    public void hideNextWaveButton() {
        this.S.clearActions();
        this.S.setVisible(false);
        this.o.wave.setAutoForceWaveEnabled(false);
    }

    public void hideMdps() {
        this.U.clearActions();
        this.U.setVisible(false);
    }

    public void showMdps(boolean z, @Null Runnable runnable) {
        this.U.clearActions();
        this.U.setVisible(true);
        this.U.setTransform(false);
        if (z) {
            this.o._gameUi.uiElementsEmphasizer.show(this.U, f, Game.i.localeManager.i18n.get("main_ui_mdps_title"), Game.i.localeManager.i18n.get("main_ui_mdps_description"), runnable);
        } else if (runnable != null) {
            runnable.run();
        }
    }

    public void showNextWaveButton(boolean z, @Null Runnable runnable) {
        this.S.clearActions();
        this.S.setVisible(true);
        this.S.setTransform(false);
        if (z) {
            this.o._gameUi.uiElementsEmphasizer.show(this.S, g, Game.i.localeManager.i18n.get("main_ui_wave_call_title"), Game.i.localeManager.i18n.get("main_ui_wave_call_description"), runnable);
        } else if (runnable != null) {
            runnable.run();
        }
    }

    public void updateGameSpeedButton() {
        float nonAnimatedGameSpeed = this.o.gameState.getNonAnimatedGameSpeed();
        if (nonAnimatedGameSpeed <= 0.0667f) {
            this.K.setIcon(this.ac);
            if (this.K.isVisible()) {
                this.J.clearActions();
                this.J.addAction(Actions.show());
                this.J.addAction(Actions.forever(Actions.parallel(Actions.sequence(Actions.alpha(0.78f), Actions.alpha(0.0f, 1.0f)), Actions.sequence(Actions.moveTo(32.0f, 32.0f), Actions.moveTo(8.0f, 8.0f, 1.0f)), Actions.sequence(Actions.sizeTo(96.0f, 96.0f), Actions.sizeTo(144.0f, 144.0f, 1.0f)))));
            } else {
                this.J.setVisible(false);
            }
        } else {
            if (nonAnimatedGameSpeed <= 1.0f) {
                this.K.setIcon(this.ad);
            } else if (nonAnimatedGameSpeed <= 2.0f) {
                this.K.setIcon(this.ae);
            } else {
                this.K.setIcon(this.af);
            }
            this.J.clearActions();
            this.J.addAction(Actions.sequence(Actions.alpha(0.0f, 0.2f), Actions.hide()));
        }
        this.L.setVisible(Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.SLOW_MOTION_PAUSE) == 0.0d && this.o.gameState.getGameSpeed() == 0.0f && !this.X.isVisible());
    }

    public void updateForceWaveButton() {
        if (this.o.wave.isForceWaveAvailable()) {
            a(true);
            this.D.clearActions();
            this.D.addAction(Actions.show());
            this.D.addAction(Actions.forever(Actions.parallel(Actions.sequence(Actions.alpha(0.78f), Actions.alpha(0.0f, 1.0f)), Actions.sequence(Actions.moveTo(32.0f, 32.0f), Actions.moveTo(0.0f, 0.0f, 1.0f)), Actions.sequence(Actions.sizeTo(128.0f, 128.0f), Actions.sizeTo(192.0f, 192.0f, 1.0f)))));
        } else {
            a(false);
            this.D.clearActions();
            this.D.addAction(Actions.sequence(Actions.alpha(0.0f, 0.2f), Actions.hide()));
        }
        if (this.o.wave.isAutoForceWaveEnabled()) {
            this.I.setVisible(true);
        } else {
            this.I.setVisible(false);
        }
    }

    public void updateScore() {
        this.p.setText(StringFormatter.commaSeparatedNumber(this.o.gameState.getScore()));
    }

    public void updateMoney() {
        this.v.setText(StringFormatter.commaSeparatedNumber(this.o.gameState.getMoney()));
    }

    public void updateHealth() {
        int health = this.o.gameState.getHealth();
        int i2 = health;
        if (health < 0) {
            i2 = 0;
        }
        O.setLength(0);
        O.append(i2);
        this.s.setText(O);
    }

    public void updateWave() {
        O.setLength(0);
        O.append(this.o.wave.wave == null ? 1 : this.o.wave.wave.waveNumber);
        this.r.setText(O);
    }

    public void updateMdps() {
        this.q.setText(StringFormatter.compactNumber(this.o.damage.getTowersMaxDps(), false));
    }

    public void updateGameplayBonus() {
        if (this.o.bonus.isEnabled()) {
            this.gameplayBonusGroup.setVisible(true);
            this.gameplayBonusGroup.update();
        } else {
            this.gameplayBonusGroup.setVisible(false);
        }
    }

    public void updateAll() {
        updateScore();
        updateMoney();
        updateWave();
        updateHealth();
        updateMdps();
        updateForceWaveButton();
        updateGameSpeedButton();
        updateGameplayBonus();
    }

    public void draw(float f2) {
        if (this.o.wave.status != WaveSystem.Status.NOT_STARTED) {
            int timeToNextWave = (int) this.o.wave.getTimeToNextWave();
            if (timeToNextWave == 0) {
                this.E.setVisible(false);
                this.F.setVisible(false);
            } else if (timeToNextWave != this.M) {
                O.setLength(0);
                O.append(timeToNextWave);
                this.F.setText(O);
                this.F.setVisible(true);
                this.E.setVisible(true);
            }
            this.M = timeToNextWave;
            int forceWaveBonus = this.o.wave.getForceWaveBonus();
            if (forceWaveBonus == 0) {
                this.H.setVisible(false);
                this.G.setVisible(false);
            } else if (forceWaveBonus != this.N) {
                O.setLength(0);
                O.append('+');
                O.append(forceWaveBonus);
                if (this.o.wave.isForceWaveDoubleBonus()) {
                    O.append(" (x2)");
                }
                this.G.setText(O);
                this.H.setVisible(true);
                this.G.setVisible(true);
            }
            this.N = forceWaveBonus;
        } else {
            this.E.setVisible(false);
            this.F.setVisible(false);
            this.H.setVisible(false);
            this.G.setVisible(false);
        }
        this.aj += f2;
        if (this.aj > 1.0f) {
            a();
            this.aj = 0.0f;
        }
        int doubleSpeedTimeLeft = (int) this.o.gameState.getDoubleSpeedTimeLeft();
        if (doubleSpeedTimeLeft > 0) {
            if (!this.w.isVisible()) {
                this.w.setVisible(true);
                this.y.setVisible(true);
                this.z.setVisible(true);
            }
            if (doubleSpeedTimeLeft != this.A) {
                this.A = doubleSpeedTimeLeft;
                this.x.setText(StringFormatter.digestTime(doubleSpeedTimeLeft));
            }
        } else {
            this.w.setVisible(false);
            this.y.setVisible(false);
            this.z.setVisible(false);
        }
        if (!this.ai && this.o.gameState.isGameRealTimePasses()) {
            this.ah += f2;
            if (this.ah > 180.0f) {
                TooltipsOverlay.i().showText("MainUi.detailedMode", this.P, Game.i.localeManager.i18n.get("tooltip_detailed_mode"), this.m.mainUiLayer, this.m.zIndex + 1, 4);
                this.ai = true;
            }
        }
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        Game.EVENTS.getListeners(ScreenResize.class).remove(this.al);
        Game.i.uiManager.removeLayer(this.m);
        Game.i.uiManager.removeLayer(this.k);
        Game.i.uiManager.removeLayer(this.l);
        Game.i.uiManager.removeLayer(this.n);
        this.o = null;
    }
}
