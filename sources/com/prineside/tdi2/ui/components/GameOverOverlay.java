package com.prineside.tdi2.ui.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Timer;
import com.prineside.tdi2.BasicLevel;
import com.prineside.tdi2.BasicLevelQuestConfig;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.IssuedItems;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.MapPrestigeConfig;
import com.prineside.tdi2.enums.DifficultyMode;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ItemType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.items.ResourceItem;
import com.prineside.tdi2.items.StarItem;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.ProgressManager;
import com.prineside.tdi2.managers.PurchaseManager;
import com.prineside.tdi2.managers.ReplayManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.managers.preferences.categories.ProgressPrefs;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.ScrollPane;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.systems.GameStateSystem;
import com.prineside.tdi2.systems.GameUiSystem;
import com.prineside.tdi2.ui.actors.ComplexButton;
import com.prineside.tdi2.ui.actors.ItemCell;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.ParticlesCanvas;
import com.prineside.tdi2.ui.actors.QuadActor;
import com.prineside.tdi2.ui.components.MapPrestigeOverlay;
import com.prineside.tdi2.ui.shared.Dialog;
import com.prineside.tdi2.ui.shared.ItemDescriptionDialog;
import com.prineside.tdi2.ui.shared.Notifications;
import com.prineside.tdi2.utils.FastRandom;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.UiUtils;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/GameOverOverlay.class */
public class GameOverOverlay implements Disposable {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3290a = TLog.forClass(GameOverOverlay.class);
    public static final String[] HINT_ALIASES = new String[72];
    private MapPrestigeOverlay d;
    private final Group e;
    private final Table f;
    private final Label g;
    private final Label h;
    private final Label i;
    private final Label j;
    private final Label k;
    private final Image l;
    private final Group m;
    private final Label n;
    private final Label o;
    private final Label p;
    private final Label q;
    private final Label r;
    private final Label s;
    private Group t;
    private Group u;
    private ComplexButton v;
    private ComplexButton w;
    private ComplexButton x;
    private ComplexButton y;
    private Label z;
    private ComplexButton A;
    private Group B;
    private Label C;
    private Label D;
    private final GameSystemProvider F;
    private boolean G;
    private Array<ItemStack> H;
    private boolean I;

    /* renamed from: b, reason: collision with root package name */
    private final UiManager.UiLayer f3291b = Game.i.uiManager.addLayerIgnoreSafeArea(UiManager.MainUiLayer.SCREEN, 170, "GameOverOverlay overlay", true);
    private final UiManager.UiLayer c = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 171, "GameOverOverlay main");
    private int E = -1;
    private Array<ItemCell> J = new Array<>();

    static {
        for (int i = 0; i < HINT_ALIASES.length; i++) {
            HINT_ALIASES[i] = "hint_msg_" + (i + 1);
        }
    }

    public GameOverOverlay(GameSystemProvider gameSystemProvider) {
        this.F = gameSystemProvider;
        Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image.setColor(Config.BACKGROUND_COLOR);
        image.getColor().f889a = 0.78f;
        this.f3291b.getTable().add((Table) image).expand().fill();
        Group group = new Group();
        group.setOrigin(600.0f, 380.0f);
        this.c.getTable().add((Table) group).padBottom(80.0f).size(1200.0f, 700.0f);
        this.e = new Group();
        this.e.setOrigin(600.0f, 350.0f);
        this.e.setSize(1200.0f, 700.0f);
        group.addActor(this.e);
        this.e.addActor(new QuadActor(new Color(791621631), new float[]{8.0f, 0.0f, 0.0f, 700.0f, 1200.0f, 688.0f, 1192.0f, 0.0f}));
        this.f = new Table();
        this.f.setWidth(1200.0f);
        ScrollPane scrollPane = new ScrollPane(this.f);
        UiUtils.enableMouseMoveScrollFocus(scrollPane);
        scrollPane.setSize(1198.0f, 428.0f);
        scrollPane.setPosition(1.0f, 21.0f);
        scrollPane.setCullingArea(null);
        this.e.addActor(scrollPane);
        Image image2 = new Image(Game.i.assetManager.getDrawable("gradient-top"));
        image2.setSize(1150.0f, 32.0f);
        image2.setColor(new Color(791621631));
        image2.setPosition(25.0f, 418.0f);
        image2.setTouchable(Touchable.disabled);
        this.e.addActor(image2);
        Image image3 = new Image(Game.i.assetManager.getDrawable("gradient-bottom"));
        image3.setSize(1150.0f, 32.0f);
        image3.setColor(new Color(791621631));
        image3.setPosition(25.0f, 20.0f);
        image3.setTouchable(Touchable.disabled);
        this.e.addActor(image3);
        Label label = new Label(Game.i.localeManager.i18n.get("game_over"), new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
        label.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        label.setPosition(80.0f, 618.0f);
        label.setSize(100.0f, 32.0f);
        this.e.addActor(label);
        this.g = new Label("", new Label.LabelStyle(Game.i.assetManager.getFont(36), Color.WHITE));
        this.g.setPosition(80.0f, 576.0f);
        this.g.setSize(100.0f, 32.0f);
        this.e.addActor(this.g);
        Label label2 = new Label(Game.i.progressManager.getDifficultyName(gameSystemProvider.gameState.difficultyMode), new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
        label2.setColor(Game.i.progressManager.getDifficultyModeColor(gameSystemProvider.gameState.difficultyMode));
        label2.setPosition(80.0f, 618.0f);
        label2.setSize(1020.0f, 32.0f);
        label2.setAlignment(16);
        this.e.addActor(label2);
        this.h = new Label("", new Label.LabelStyle(Game.i.assetManager.getFont(36), Color.WHITE));
        this.h.setPosition(80.0f, 576.0f);
        this.h.setSize(1020.0f, 32.0f);
        this.h.setAlignment(16);
        this.e.addActor(this.h);
        Label label3 = new Label(Game.i.localeManager.i18n.get("game_over_defeated_waves_hint"), new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
        label3.setPosition(250.0f, 480.0f);
        label3.setSize(100.0f, 15.0f);
        label3.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        label3.setAlignment(1);
        this.e.addActor(label3);
        this.q = new Label(Game.i.localeManager.i18n.get("new_record") + "!", new Label.LabelStyle(Game.i.assetManager.getFont(21), Color.WHITE));
        this.q.setPosition(250.0f, 454.0f);
        this.q.setSize(100.0f, 15.0f);
        this.q.setColor(MaterialColor.AMBER.P500);
        this.q.setAlignment(1);
        this.q.setVisible(false);
        this.e.addActor(this.q);
        Label label4 = new Label(Game.i.localeManager.i18n.get("score"), new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
        label4.setPosition(550.0f, 473.0f);
        label4.setSize(100.0f, 15.0f);
        label4.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        label4.setAlignment(1);
        this.e.addActor(label4);
        this.r = new Label(Game.i.localeManager.i18n.get("new_record") + "!", new Label.LabelStyle(Game.i.assetManager.getFont(21), Color.WHITE));
        this.r.setPosition(550.0f, 447.0f);
        this.r.setSize(100.0f, 15.0f);
        this.r.setColor(MaterialColor.AMBER.P500);
        this.r.setAlignment(1);
        this.r.setVisible(false);
        this.e.addActor(this.r);
        Label label5 = new Label(Game.i.localeManager.i18n.get("duration"), new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
        label5.setPosition(850.0f, 480.0f);
        label5.setSize(100.0f, 15.0f);
        label5.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        label5.setAlignment(1);
        this.e.addActor(label5);
        this.s = new Label(Game.i.localeManager.i18n.get("new_record") + "!", new Label.LabelStyle(Game.i.assetManager.getFont(21), Color.WHITE));
        this.s.setPosition(850.0f, 454.0f);
        this.s.setSize(100.0f, 15.0f);
        this.s.setColor(MaterialColor.AMBER.P500);
        this.s.setAlignment(1);
        this.s.setVisible(false);
        this.e.addActor(this.s);
        this.i = new Label("15,918", new Label.LabelStyle(Game.i.assetManager.getFont(60), Color.WHITE));
        this.i.setPosition(550.0f, 503.0f);
        this.i.setSize(100.0f, 48.0f);
        this.i.setAlignment(1);
        this.e.addActor(this.i);
        this.j = new Label("57", new Label.LabelStyle(Game.i.assetManager.getFont(36), Color.WHITE));
        this.j.setPosition(250.0f, 509.0f);
        this.j.setSize(100.0f, 26.0f);
        this.j.setAlignment(1);
        this.e.addActor(this.j);
        this.k = new Label("17m 56s", new Label.LabelStyle(Game.i.assetManager.getFont(36), Color.WHITE));
        this.k.setPosition(850.0f, 509.0f);
        this.k.setSize(100.0f, 26.0f);
        this.k.setAlignment(1);
        this.e.addActor(this.k);
        this.l = new Image(Game.i.assetManager.getDrawable("loading-icon"));
        this.l.setPosition(587.0f, 638.0f);
        this.l.setSize(32.0f, 32.0f);
        this.l.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        this.l.setOrigin(1);
        this.l.addAction(Actions.forever(Actions.rotateBy(90.0f, 1.0f)));
        this.e.addActor(this.l);
        this.m = new Group();
        this.m.setSize(286.0f, 144.0f);
        this.m.setPosition(457.0f, 635.0f);
        this.m.setOrigin(1);
        this.m.setVisible(false);
        this.e.addActor(this.m);
        Image image4 = new Image(Game.i.assetManager.getDrawable("ui-game-over-leaderboards-rank"));
        image4.setSize(286.0f, 144.0f);
        this.m.addActor(image4);
        Table table = new Table();
        table.setPosition(0.0f, 35.0f);
        table.setSize(286.0f, 90.0f);
        this.m.addActor(table);
        Table table2 = new Table();
        table.add(table2).row();
        Image image5 = new Image(Game.i.assetManager.getDrawable("icon-crown"));
        image5.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        table2.add((Table) image5).size(32.0f, 32.0f);
        Label label6 = new Label(Game.i.localeManager.i18n.get("leaderboard"), Game.i.assetManager.getLabelStyle(21));
        label6.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        table2.add((Table) label6).height(32.0f).padLeft(8.0f).padRight(8.0f);
        Table table3 = new Table();
        table.add(table3).row();
        this.n = new Label("1234", Game.i.assetManager.getLabelStyle(30));
        table3.add((Table) this.n);
        this.o = new Label(" / 9876", Game.i.assetManager.getLabelStyle(24));
        table3.add((Table) this.o);
        this.p = new Label("", Game.i.assetManager.getLabelStyle(21));
        this.p.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        table.add((Table) this.p).row();
        this.t = new Group();
        this.t.setTransform(false);
        this.t.setSize(113.0f, 415.0f);
        this.t.setPosition(1193.0f, 114.0f);
        this.e.addActor(this.t);
        this.u = new Group();
        this.u.setVisible(false);
        this.u.setTransform(false);
        this.u.setSize(127.0f, 420.0f);
        this.u.setPosition(-120.0f, 114.0f);
        this.e.addActor(this.u);
        Image image6 = new Image(Game.i.assetManager.getQuad("ui.gameOverOverlay.adsRayBg"));
        image6.setSize(126.0f, 326.0f);
        image6.setPosition(-8.0f, 92.0f);
        this.u.addActor(image6);
        this.v = new ComplexButton("", new Label.LabelStyle(Game.i.assetManager.getFont(30), Color.WHITE), () -> {
            d();
        }).setBackground(Game.i.assetManager.getQuad("ui.gameOverOverlay.adsButton"), 0.0f, 0.0f, 117.0f, 100.0f).setIconPositioned(Game.i.assetManager.getDrawable("rewarding-ad"), 19.0f, 20.0f, 64.0f, 64.0f);
        this.v.setPosition(10.0f, 0.0f);
        this.v.setSize(117.0f, 100.0f);
        this.v.setBackgroundColors(MaterialColor.PURPLE.P700, MaterialColor.PURPLE.P800, MaterialColor.PURPLE.P500, Color.GRAY);
        this.u.addActor(this.v);
        Label label7 = new Label("+25%", Game.i.assetManager.getLabelStyle(18));
        label7.setPosition(21.0f, 31.0f);
        label7.setSize(64.0f, 13.0f);
        label7.setColor(MaterialColor.PURPLE.P700);
        label7.setAlignment(1);
        this.v.addActor(label7);
        Image image7 = new Image(Game.i.assetManager.getDrawable("game-over-ad-button-glow"));
        image7.setColor(1.0f, 1.0f, 1.0f, 0.0f);
        image7.setPosition(-22.0f, -16.0f);
        image7.setSize(143.0f, 132.0f);
        image7.setTouchable(Touchable.disabled);
        image7.addAction(Actions.forever(Actions.sequence(Actions.alpha(0.0f, 0.5f), Actions.alpha(1.0f, 0.5f))));
        this.v.addActor(image7);
        image7.setZIndex(this.v.background.getZIndex() + 1);
        String str = Game.i.localeManager.i18n.get("back_to_menu");
        ComplexButton iconPositioned = new ComplexButton(str, Game.i.assetManager.getLabelStyle(str.length() > 12 ? 18 : 21), () -> {
            Runnable runnable = () -> {
                try {
                    if (GameStateSystem.GameMode.isBasicLevel(gameSystemProvider.gameState.gameMode)) {
                        Game.i.screenManager.goToLevelSelectScreen();
                    } else {
                        Game.i.screenManager.goToCustomMapSelectScreen();
                    }
                } catch (Throwable th) {
                    f3290a.w("failed to check if game mode is basic level, returning to the list of levels", th);
                    Game.i.screenManager.goToLevelSelectScreen();
                }
            };
            if (gameSystemProvider.randomEncounter.isReceivedBonusLevelPass()) {
                Dialog.i().showConfirm(Game.i.localeManager.i18n.get("skip_bonus_level_confirm"), runnable);
                Dialog.i().makeConfirmButtonDisabled(1);
            } else {
                runnable.run();
            }
        }).setBackground(Game.i.assetManager.getQuad("ui.gameOverOverlay.btnHome"), 0.0f, 0.0f, 157.0f, 125.0f).setIconPositioned(Game.i.assetManager.getDrawable("icon-house"), 46.0f, 43.0f, 64.0f, 64.0f);
        iconPositioned.setBackgroundColors(MaterialColor.LIGHT_BLUE.P700, MaterialColor.LIGHT_BLUE.P900, MaterialColor.LIGHT_BLUE.P600, MaterialColor.GREY.P800);
        iconPositioned.setLabel(46.0f, 18.0f, 64.0f, 16.0f, 1);
        iconPositioned.setPosition(183.0f, -27.0f);
        iconPositioned.setSize(157.0f, 125.0f);
        this.e.addActor(iconPositioned);
        String str2 = Game.i.localeManager.i18n.get("researches");
        this.A = new ComplexButton(str2, Game.i.assetManager.getLabelStyle(str2.length() > 12 ? 18 : 21), () -> {
            if (gameSystemProvider.randomEncounter.isReceivedBonusLevelPass()) {
                Dialog.i().showConfirm(Game.i.localeManager.i18n.get("skip_bonus_level_confirm"), () -> {
                    Game.i.screenManager.goToResearchesScreen();
                });
                Dialog.i().makeConfirmButtonDisabled(1);
            } else {
                Game.i.screenManager.goToResearchesScreen();
            }
        }).setBackground(Game.i.assetManager.getQuad("ui.gameOverOverlay.btnResearch"), 0.0f, 0.0f, 157.0f, 125.0f).setIconPositioned(Game.i.assetManager.getDrawable("icon-research"), 46.0f, 43.0f, 64.0f, 64.0f);
        this.A.setBackgroundColors(MaterialColor.LIGHT_BLUE.P700, MaterialColor.LIGHT_BLUE.P900, MaterialColor.LIGHT_BLUE.P600, MaterialColor.GREY.P800);
        this.A.setLabel(46.0f, 18.0f, 64.0f, 16.0f, 1);
        this.A.setPosition(360.0f, -27.0f);
        this.A.setSize(157.0f, 125.0f);
        this.e.addActor(this.A);
        String str3 = Game.i.localeManager.i18n.get("restart");
        this.w = new ComplexButton(str3, Game.i.assetManager.getLabelStyle(str3.length() > 12 ? 18 : 21), () -> {
            Runnable runnable = () -> {
                if (gameSystemProvider.gameState.basicLevelName != null && Game.i.basicLevelManager.getLevel(gameSystemProvider.gameState.basicLevelName).canNotBeRestarted) {
                    Notifications.i().addFailure(Game.i.localeManager.i18n.get("level_can_not_be_restarted"));
                } else {
                    gameSystemProvider.gameState.restartGame(false);
                }
            };
            if (gameSystemProvider.randomEncounter.isReceivedBonusLevelPass()) {
                Dialog.i().showConfirm(Game.i.localeManager.i18n.get("skip_bonus_level_confirm"), runnable);
                Dialog.i().makeConfirmButtonDisabled(1);
            } else {
                runnable.run();
            }
        }).setBackground(Game.i.assetManager.getQuad("ui.gameOverOverlay.btnRestart"), 0.0f, 0.0f, 157.0f, 125.0f).setIconPositioned(Game.i.assetManager.getDrawable("icon-restart"), 51.0f, 43.0f, 64.0f, 64.0f);
        this.w.setBackgroundColors(new Color(-1454365441), new Color(-1756946177), new Color(-378654209), MaterialColor.GREY.P800);
        this.w.setLabel(51.0f, 18.0f, 64.0f, 16.0f, 1);
        this.w.setPosition(683.0f, -27.0f);
        this.w.setSize(157.0f, 125.0f);
        this.e.addActor(this.w);
        String str4 = Game.i.localeManager.i18n.get("next_level_button");
        this.y = new ComplexButton(str4, Game.i.assetManager.getLabelStyle(str4.length() > 12 ? 18 : 21), () -> {
            BasicLevel a2 = a();
            if (a2 != null && Game.i.basicLevelManager.isOpened(a2)) {
                if (gameSystemProvider.randomEncounter.isReceivedBonusLevelPass()) {
                    Dialog.i().showConfirm(Game.i.localeManager.i18n.get("skip_bonus_level_confirm"), () -> {
                        Game.i.screenManager.startNewLevelWithAbilitySelection(GameStateSystem.GameMode.BASIC_LEVELS, a2.name);
                    });
                    Dialog.i().makeConfirmButtonDisabled(1);
                } else {
                    Game.i.screenManager.startNewLevelWithAbilitySelection(GameStateSystem.GameMode.BASIC_LEVELS, a2.name);
                }
            }
        }).setBackground(Game.i.assetManager.getQuad("ui.gameOverOverlay.btnNextLevel"), 0.0f, 0.0f, 157.0f, 125.0f).setIconPositioned(Game.i.assetManager.getDrawable("icon-rewind"), 51.0f, 43.0f, 64.0f, 64.0f);
        this.y.setBackgroundColors(MaterialColor.LIGHT_GREEN.P700, MaterialColor.LIGHT_GREEN.P900, MaterialColor.LIGHT_GREEN.P600, MaterialColor.GREY.P800);
        this.y.setLabel(51.0f, 18.0f, 64.0f, 16.0f, 1);
        this.y.setPosition(860.0f, -27.0f);
        this.y.setSize(157.0f, 125.0f);
        this.e.addActor(this.y);
        this.z = new Label("", Game.i.assetManager.getLabelStyle(18));
        this.z.setAlignment(1);
        this.z.setPosition(865.0f, -54.0f);
        this.z.setSize(152.0f, 16.0f);
        this.z.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        this.e.addActor(this.z);
        String str5 = Game.i.localeManager.i18n.get("secret_level");
        this.x = new ComplexButton(str5, Game.i.assetManager.getLabelStyle(str5.length() > 12 ? 18 : 21), () -> {
            Game.i.screenManager.startRandomSecretLevel();
        }).setBackground(Game.i.assetManager.getQuad("ui.gameOverOverlay.btnRestart"), 0.0f, 0.0f, 157.0f, 125.0f).setIconPositioned(Game.i.assetManager.getDrawable("icon-star"), 51.0f, 43.0f, 64.0f, 64.0f);
        this.x.setBackgroundColors(MaterialColor.AMBER.P800, MaterialColor.AMBER.P900, MaterialColor.AMBER.P700, MaterialColor.GREY.P800);
        this.x.setLabel(51.0f, 18.0f, 64.0f, 16.0f, 1);
        this.x.setPosition(1057.0f, -27.0f);
        this.x.setSize(157.0f, 125.0f);
        this.x.background.addAction(Actions.forever(Actions.sequence(Actions.alpha(1.0f, 0.2f), Actions.alpha(0.78f, 0.2f))));
        this.e.addActor(this.x);
        this.B = new Group();
        this.B.setTransform(false);
        this.B.setSize(1200.0f, 95.0f);
        this.B.setPosition(0.0f, -180.0f);
        this.e.addActor(this.B);
        Table table4 = new Table();
        table4.setSize(1200.0f, 15.0f);
        table4.setPosition(0.0f, 80.0f);
        this.B.addActor(table4);
        Image image8 = new Image(Game.i.assetManager.getDrawable("gradient-right"));
        image8.setColor(new Color(1669871871));
        table4.add((Table) image8).size(360.0f, 2.0f);
        Label label8 = new Label(Game.i.localeManager.i18n.get("hint_box_title"), Game.i.assetManager.getLabelStyle(21));
        label8.setColor(new Color(-1983886849));
        table4.add((Table) label8).height(15.0f).padLeft(8.0f).padRight(8.0f);
        Image image9 = new Image(Game.i.assetManager.getDrawable("gradient-left"));
        image9.setColor(new Color(1669871871));
        table4.add((Table) image9).size(360.0f, 2.0f);
        Image image10 = new Image(Game.i.assetManager.getDrawable("gradient-radial-bottom"));
        image10.setColor(0.0f, 0.0f, 0.0f, 0.56f);
        image10.setSize(720.0f, 82.0f);
        image10.setPosition(220.0f, 1.0f);
        this.B.addActor(image10);
        this.C = new Label("", Game.i.assetManager.getLabelStyle(24));
        this.C.setAlignment(1);
        this.C.setSize(900.0f, 84.0f);
        this.C.setPosition(150.0f, 2.0f);
        this.C.setWrap(true);
        this.C.setColor(new Color(-588396289));
        this.B.addActor(this.C);
        Image image11 = new Image(Game.i.assetManager.getDrawable("gradient-horizontal"));
        image11.setColor(new Color(1669871871));
        image11.setSize(720.0f, 2.0f);
        image11.setPosition(220.0f, 0.0f);
        this.B.addActor(image11);
        this.D = new Label("", Game.i.assetManager.getLabelStyle(18));
        this.D.setPosition(0.0f, -24.0f);
        this.D.setSize(1200.0f, 15.0f);
        this.D.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        this.D.setAlignment(1);
        this.B.addActor(this.D);
        this.B.setTouchable(Touchable.enabled);
        this.B.addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.components.GameOverOverlay.1
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                GameOverOverlay.this.e();
            }
        });
        this.f3291b.getTable().setColor(1.0f, 1.0f, 1.0f, 0.0f);
        this.f3291b.getTable().setVisible(false);
        this.e.setScale(0.0f);
        this.c.getTable().setVisible(false);
    }

    private BasicLevel a() {
        if (this.F.gameState.basicLevelName != null && this.F.gameState.dailyQuestLevel == null) {
            BasicLevel level = Game.i.basicLevelManager.getLevel(this.F.gameState.basicLevelName);
            if (!level.dailyQuest) {
                return Game.i.basicLevelManager.getNextVisibleLevel(level);
            }
            return null;
        }
        return null;
    }

    private static int a(int i) {
        return StrictMath.round(i * 0.2501f);
    }

    private IssuedItems b() {
        int a2;
        IssuedItems issuedItems = new IssuedItems(Game.i.progressManager.isPremiumStatusActive() ? IssuedItems.IssueReason.PREMIUM_REWARD_VIDEO : IssuedItems.IssueReason.REWARD_VIDEO, Game.getTimestampSeconds());
        for (int i = 0; i < this.H.size; i++) {
            ItemStack itemStack = this.H.get(i);
            if ((itemStack.getItem().getType() == ItemType.GREEN_PAPER || itemStack.getItem().getType() == ItemType.RESOURCE || itemStack.getItem().getType() == ItemType.BIT_DUST) && (a2 = a(itemStack.getCount())) > 0) {
                issuedItems.items.add(new ItemStack(itemStack.getItem(), a2));
            }
        }
        ProgressManager.compressStacksArray(issuedItems.items);
        return issuedItems;
    }

    private void c() {
        this.I = true;
        IssuedItems b2 = b();
        for (int i = 0; i < b2.items.size; i++) {
            Game.i.progressManager.addItemStack(b2.items.get(i), "game_over_ad_reward");
        }
        Game.i.progressManager.addIssuedPrizes(b2, true);
        for (int i2 = 0; i2 < b2.items.size; i2++) {
            ItemStack itemStack = b2.items.get(i2);
            for (int i3 = 0; i3 < this.J.size; i3++) {
                ItemCell itemCell = this.J.get(i3);
                if (itemCell.getItem() == itemStack.getItem()) {
                    itemCell.setCount(itemCell.getCount() + a(itemCell.getCount()));
                    Label label = new Label("+25%", Game.i.assetManager.getLabelStyle(21));
                    label.addAction(Actions.forever(Actions.sequence(Actions.alpha(0.78f, 0.3f), Actions.alpha(1.0f, 0.3f))));
                    label.setAlignment(16);
                    label.setPosition(96.0f, 108.0f);
                    label.setSize(20.0f, 20.0f);
                    itemCell.overlay.addActor(label);
                }
            }
        }
    }

    private void d() {
        if (this.H != null && !this.I) {
            this.I = true;
            this.u.setVisible(false);
            Game.i.purchaseManager.showRewardingAd(PurchaseManager.RewardingAdsType.END_GAME, bool -> {
                if (bool.booleanValue()) {
                    c();
                } else {
                    Dialog.i().showAlert(Game.i.localeManager.i18n.get("something_wrong_try_later"));
                }
            });
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void show(Array<ItemStack> array, MapPrestigeConfig mapPrestigeConfig) {
        this.g.setText(Game.i.localeManager.i18n.get("game_over_reason_" + this.F.gameState.gameOverReason.name()));
        if (this.F.gameState.gameMode == GameStateSystem.GameMode.BASIC_LEVELS) {
            this.h.setText(Game.i.localeManager.i18n.get("level") + SequenceUtils.SPACE + this.F.gameState.basicLevelName);
            this.h.setColor(Game.i.basicLevelManager.getLevelStage(this.F.gameState.basicLevelName).color);
        } else if (this.F.gameState.gameMode == GameStateSystem.GameMode.USER_MAPS) {
            this.h.setText(Game.i.localeManager.i18n.get("custom_map"));
            this.h.setColor(Color.WHITE);
        }
        int completedWavesCount = this.F.wave.getCompletedWavesCount();
        long score = this.F.gameState.getScore();
        int currentGameStatistic = (int) this.F.statistics.getCurrentGameStatistic(StatisticsType.PRT);
        this.i.setText(StringFormatter.commaSeparatedNumber(score));
        this.j.setText(StringFormatter.commaSeparatedNumber(completedWavesCount));
        this.k.setText(StringFormatter.timePassed(currentGameStatistic, true, false));
        if (this.F.gameState.gameMode == GameStateSystem.GameMode.BASIC_LEVELS) {
            BasicLevel level = Game.i.basicLevelManager.getLevel(this.F.gameState.basicLevelName);
            this.q.setVisible(ProgressPrefs.i().basicLevel.getLevelMaxReachedWave(level.name) < completedWavesCount);
            this.s.setVisible(ProgressPrefs.i().basicLevel.getLevelMaxPlayingTime(level.name) < currentGameStatistic);
            this.r.setVisible(ProgressPrefs.i().basicLevel.getLevelMaxScore(level.name) < score);
            int[] starMilestoneWaves = level.getStarMilestoneWaves();
            int i = 0;
            for (int i2 = 0; i2 < starMilestoneWaves.length; i2++) {
                if (starMilestoneWaves[i2] != 0 && this.F.wave.getCompletedWavesCount() >= starMilestoneWaves[i2]) {
                    i++;
                }
            }
            for (int i3 = 0; i3 < level.quests.size; i3++) {
                BasicLevelQuestConfig basicLevelQuestConfig = level.quests.items[i3];
                if (basicLevelQuestConfig.isCompleted()) {
                    for (int i4 = 0; i4 < basicLevelQuestConfig.prizes.size; i4++) {
                        ItemStack itemStack = basicLevelQuestConfig.prizes.items[i4];
                        if (itemStack.getItem() instanceof StarItem) {
                            i += itemStack.getCount();
                        }
                    }
                }
            }
            f3290a.i(i + " stars", new Object[0]);
            final ParticlesCanvas particlesCanvas = new ParticlesCanvas();
            particlesCanvas.setSize(3120.0f, 64.0f);
            particlesCanvas.setPosition(520.0f, 626.0f);
            this.e.addActor(particlesCanvas);
            Image image = new Image(Game.i.assetManager.getDrawable("icon-star"));
            image.setSize(48.0f, 48.0f);
            image.setPosition(520.0f, 566.0f);
            if (i <= 0) {
                image.setColor(0.0f, 0.0f, 0.0f, 0.28f);
            } else {
                image.setColor(MaterialColor.AMBER.P400);
                image.setOrigin(24.0f, 24.0f);
                image.addAction(Actions.sequence(Actions.scaleTo(0.0f, 0.0f), Actions.scaleTo(1.0f, 1.0f, 0.3f, Interpolation.swingOut)));
            }
            this.e.addActor(image);
            Image image2 = new Image(Game.i.assetManager.getDrawable("icon-star"));
            image2.setSize(64.0f, 64.0f);
            image2.setPosition(568.0f, 566.0f);
            if (i < 2) {
                image2.setColor(0.0f, 0.0f, 0.0f, 0.28f);
            } else {
                image2.setColor(MaterialColor.AMBER.P400);
                image2.setOrigin(32.0f, 32.0f);
                image2.addAction(Actions.sequence(Actions.scaleTo(0.0f, 0.0f), Actions.delay(0.6f), Actions.scaleTo(1.0f, 1.0f, 0.3f, Interpolation.swingOut)));
            }
            this.e.addActor(image2);
            Image image3 = new Image(Game.i.assetManager.getDrawable("icon-star"));
            image3.setSize(48.0f, 48.0f);
            image3.setPosition(632.0f, 566.0f);
            if (i < 3) {
                image3.setColor(0.0f, 0.0f, 0.0f, 0.28f);
            } else {
                image3.setColor(MaterialColor.AMBER.P400);
                image3.setOrigin(24.0f, 24.0f);
                image3.addAction(Actions.sequence(Actions.scaleTo(0.0f, 0.0f), Actions.delay(1.2f), Actions.scaleTo(1.0f, 1.0f, 0.3f, Interpolation.swingOut)));
            }
            this.e.addActor(image3);
            if (i > 0) {
                final ParticleEffectPool particleEffectPool = Game.i.assetManager.getParticleEffectPool("game-over-stars.prt");
                ParticleEffectPool.PooledEffect obtain = particleEffectPool.obtain();
                obtain.getEmitters().first().setMinParticleCount(8);
                obtain.getEmitters().first().setMaxParticleCount(8);
                particlesCanvas.addParticle(obtain, 24.0f, 24.0f);
                if (i >= 2) {
                    Timer.schedule(new Timer.Task(this) { // from class: com.prineside.tdi2.ui.components.GameOverOverlay.2
                        @Override // com.badlogic.gdx.utils.Timer.Task, java.lang.Runnable
                        public void run() {
                            ParticleEffectPool.PooledEffect obtain2 = particleEffectPool.obtain();
                            obtain2.getEmitters().first().setMinParticleCount(16);
                            obtain2.getEmitters().first().setMaxParticleCount(16);
                            particlesCanvas.addParticle(obtain2, 80.0f, 32.0f);
                        }
                    }, 0.6f);
                }
                if (i >= 3) {
                    Timer.schedule(new Timer.Task(this) { // from class: com.prineside.tdi2.ui.components.GameOverOverlay.3
                        @Override // com.badlogic.gdx.utils.Timer.Task, java.lang.Runnable
                        public void run() {
                            ParticleEffectPool.PooledEffect obtain2 = particleEffectPool.obtain();
                            obtain2.getEmitters().first().setMinParticleCount(24);
                            obtain2.getEmitters().first().setMaxParticleCount(24);
                            particlesCanvas.addParticle(obtain2, 136.0f, 24.0f);
                        }
                    }, 1.2f);
                }
            }
        }
        Game.i.researchManager.updateAfforableResearchesCount();
        int afforableResearchesCount = Game.i.researchManager.getAfforableResearchesCount();
        if (afforableResearchesCount > 0) {
            Image image4 = new Image(Game.i.assetManager.getDrawable("count-bubble"));
            image4.setPosition(129.0f, 98.0f);
            image4.setSize(32.25f, 36.75f);
            this.A.addActor(image4);
            Label label = new Label(new StringBuilder().append(afforableResearchesCount).toString(), Game.i.assetManager.getLabelStyle(18));
            label.setAlignment(1);
            label.setSize(23.0f, 13.0f);
            label.setPosition(133.0f, 110.0f);
            this.A.addActor(label);
        }
        BasicLevel a2 = a();
        if (a2 != null) {
            this.z.setText(Game.i.localeManager.i18n.get("level") + SequenceUtils.SPACE + a2.name);
            this.y.setEnabled(Game.i.basicLevelManager.isOpened(a2));
        } else {
            this.y.setEnabled(false);
        }
        this.x.setVisible(this.F.randomEncounter.isReceivedBonusLevelPass());
        this.l.setVisible(false);
        this.m.setVisible(false);
        if (this.F.statistics.getCurrentGameStatistic(StatisticsType.PT) > 10.0d && ((this.F.gameState.difficultyMode == DifficultyMode.NORMAL || DifficultyMode.isEndless(this.F.gameState.difficultyMode)) && Game.i.authManager.isSignedIn() && this.F.gameState.gameMode == GameStateSystem.GameMode.BASIC_LEVELS && !Game.i.basicLevelManager.getLevel(this.F.gameState.basicLevelName).dailyQuest)) {
            this.l.setVisible(true);
            Game.i.leaderBoardManager.getLeaderboardsAdvanceRank(GameStateSystem.GameMode.BASIC_LEVELS, this.F.gameState.difficultyMode, this.F.gameState.basicLevelName, ReplayManager.LeaderboardsMode.score, this.F.gameState.getScore(), leaderboardsRankResult -> {
                float f = Game.i.settingsManager.isUiAnimationsEnabled() ? 1.0f : 0.0f;
                this.n.setText(String.valueOf(leaderboardsRankResult.rank));
                if (leaderboardsRankResult.success) {
                    this.o.setText(" / " + leaderboardsRankResult.total);
                    if (leaderboardsRankResult.rank == 1) {
                        this.p.setText(Game.i.localeManager.i18n.get("leader") + "!");
                    } else {
                        int ceil = MathUtils.ceil((leaderboardsRankResult.rank / leaderboardsRankResult.total) * 100.0f);
                        int i5 = ceil;
                        if (ceil <= 0) {
                            i5 = 1;
                        }
                        if (i5 > 100) {
                            i5 = 100;
                        }
                        this.p.setText(Game.i.localeManager.i18n.format("top_percent", Integer.valueOf(i5)));
                    }
                    this.l.setVisible(false);
                    this.m.clearActions();
                    this.m.setVisible(true);
                    this.m.setTransform(true);
                    this.m.addAction(Actions.sequence(Actions.scaleTo(0.0f, 0.0f), Actions.parallel(Actions.sequence(Actions.delay(0.1f * f), Actions.scaleBy(1.0f, 0.0f, 0.3f * f, Interpolation.swingOut)), Actions.scaleBy(0.0f, 1.0f, 0.3f * f, Interpolation.swingOut)), Actions.run(() -> {
                        this.m.setTransform(false);
                    })));
                    return;
                }
                this.l.setVisible(false);
                this.m.setVisible(false);
            });
        }
        this.f.clear();
        if (array.size != 0) {
            float percentValueAsMultiplier = 1.0f + ((float) this.F.gameValue.getPercentValueAsMultiplier(GameValueType.GREEN_PAPERS_BONUS));
            float f = 1.0f;
            if (Game.i.progressManager.isDoubleGainEnabled()) {
                percentValueAsMultiplier *= 2.0f;
                f = 2.0f;
            }
            if (this.F.gameState.isDailyQuestAndNotCompleted()) {
                percentValueAsMultiplier *= 0.1f;
                f *= 0.1f;
            }
            String upperCase = Game.i.localeManager.i18n.get("received_items").toUpperCase();
            if (percentValueAsMultiplier != 1.0f || f != 1.0f) {
                String str = upperCase + " ( ";
                if (percentValueAsMultiplier != 1.0f) {
                    str = str + "[#66BB6A]<@icon-money>x" + (StrictMath.round(percentValueAsMultiplier * 100.0f) / 100.0f) + "[]";
                }
                if (f != 1.0f) {
                    str = str + " [#29B6F6]<@icon-cubes-stacked>x" + (StrictMath.round(f * 100.0f) / 100.0f) + "[]";
                }
                upperCase = str + " )";
            }
            Label label2 = new Label(Game.i.assetManager.replaceRegionAliasesWithChars(upperCase), new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
            label2.setAlignment(1);
            this.f.add((Table) label2).padBottom(16.0f).padTop(24.0f).row();
            Table table = new Table();
            this.f.add(table);
            int i5 = 0;
            int i6 = 0;
            Array array2 = new Array(array);
            array2.sort(ProgressManager.ITEM_RARITY_COMPARATOR);
            float f2 = 0.25f;
            int i7 = 0;
            for (int i8 = 0; i8 < array2.size; i8++) {
                final ItemStack itemStack2 = (ItemStack) array2.get(i8);
                ItemCell itemCell = new ItemCell();
                this.J.add(itemCell);
                itemCell.setColRow(i5, i6);
                itemCell.setItemStack(itemStack2);
                itemCell.setCovered(itemStack2.isCovered());
                itemCell.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.ui.components.GameOverOverlay.4
                    @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                    public void clicked(InputEvent inputEvent, float f3, float f4) {
                        ItemDescriptionDialog.i().showWithCount(itemStack2.getItem(), itemStack2.getCount());
                    }
                });
                if (itemStack2.isFromDailyLoot() || itemStack2.isDoubled() || itemStack2.isFromRandomEncounter()) {
                    if (itemStack2.isFromDailyLoot() || itemStack2.isFromRandomEncounter()) {
                        itemCell.setSelected(true);
                    }
                    if (itemStack2.isFromDailyLoot()) {
                        itemCell.setCornerText(Game.i.assetManager.replaceRegionAliasesWithChars("<@icon-calendar>"));
                    } else if (itemStack2.isFromRandomEncounter()) {
                        itemCell.setCornerText(Game.i.assetManager.replaceRegionAliasesWithChars("<@icon-star>"));
                    } else {
                        itemCell.setCornerText("x2");
                    }
                }
                table.add((Table) itemCell);
                i5++;
                if (i5 == 8) {
                    i5 = 0;
                    table.row();
                    i6++;
                }
                if (itemStack2.isCovered()) {
                    itemCell.addAction(Actions.sequence(Actions.delay(f2), Actions.run(() -> {
                        itemCell.reveal();
                    })));
                    float f3 = 0.6f - (i7 * 0.05f);
                    float f4 = f3;
                    if (f3 < 0.05f) {
                        f4 = 0.05f;
                    }
                    f2 += f4;
                    i7++;
                } else {
                    itemCell.shine(true, false);
                }
            }
        }
        this.f.row();
        this.f.add().height(100.0f).width(1.0f);
        this.H = array;
        IssuedItems b2 = b();
        if (b2.items.size > 0) {
            if (Game.i.progressManager.isPremiumStatusActive()) {
                this.u.setVisible(false);
                ProgressPrefs.i().progress.registerVideoWatched();
                ProgressPrefs.i().requireSave();
                c();
                Notifications.i().add(Game.i.localeManager.i18n.get("end_game_auto_reward_received"), Game.i.assetManager.getDrawable("icon-crown"), MaterialColor.LIGHT_GREEN.P800, StaticSoundType.SUCCESS);
            } else if (!this.I && this.F.statistics.getCurrentGameStatistic(StatisticsType.PRT) > 120.0d && Game.i.purchaseManager.canShowRewardingAd(PurchaseManager.RewardingAdsType.END_GAME)) {
                this.u.setVisible(true);
            } else {
                this.u.setVisible(false);
            }
            Table table2 = new Table();
            table2.setSize(91.0f, 320.0f);
            table2.setPosition(12.0f, 110.0f);
            this.u.addActor(table2);
            table2.add().width(1.0f).growY().row();
            for (int i9 = b2.items.size - 1; i9 >= 0; i9--) {
                ItemStack itemStack3 = b2.items.get(i9);
                Actor generateIcon = itemStack3.getItem().generateIcon(32.0f, false);
                Label label3 = new Label(StringFormatter.compactNumber(itemStack3.getCount(), false), Game.i.assetManager.getLabelStyle(18));
                switch (itemStack3.getItem().getType()) {
                    case GREEN_PAPER:
                        label3.setColor(MaterialColor.GREEN.P500);
                        break;
                    case RESOURCE:
                        label3.setColor(Game.i.resourceManager.getColor(((ResourceItem) itemStack3.getItem()).resourceType));
                        break;
                }
                label3.setAlignment(16);
                table2.add((Table) label3).growX().height(32.0f).padBottom(16.0f).padRight(8.0f);
                table2.add((Table) generateIcon).size(32.0f).padBottom(14.0f).row();
            }
        }
        this.t.clear();
        if (mapPrestigeConfig != null) {
            ComplexButton complexButton = new ComplexButton("", Game.i.assetManager.getLabelStyle(21), () -> {
                if (this.d == null) {
                    this.d = new MapPrestigeOverlay();
                    this.d.listeners.add(new MapPrestigeOverlay.MapPrestigeOverlayListener() { // from class: com.prineside.tdi2.ui.components.GameOverOverlay.5
                        @Override // com.prineside.tdi2.ui.components.MapPrestigeOverlay.MapPrestigeOverlayListener
                        public void prestigeConfirmed() {
                            GameOverOverlay.this.w.setEnabled(false);
                        }

                        @Override // com.prineside.tdi2.GameListener
                        public boolean affectsGameState() {
                            return false;
                        }

                        @Override // com.prineside.tdi2.GameListener
                        public int getConstantId() {
                            return 0;
                        }
                    });
                }
                this.d.show(mapPrestigeConfig);
            });
            complexButton.setBackground(Game.i.assetManager.getQuad("ui.gameOverOverlay.prestigeButton"), 0.0f, 0.0f, 117.0f, 100.0f);
            complexButton.setIconPositioned(Game.i.assetManager.getDrawable("icon-dollar"), 34.0f, 16.0f, 64.0f, 64.0f);
            complexButton.setSize(117.0f, 100.0f);
            this.t.addActor(complexButton);
            Label label4 = new Label(mapPrestigeConfig.getTotalBonus() + "%", Game.i.assetManager.getLabelStyle(21));
            label4.setAlignment(1);
            label4.setColor(MaterialColor.LIGHT_BLUE.P300);
            label4.setSize(100.0f, 20.0f);
            label4.setPosition(11.0f, 124.0f);
            this.t.addActor(label4);
            int crownsCount = mapPrestigeConfig.getCrownsCount();
            int[] iArr = {new int[]{45, 162, 32}, new int[]{41, 200, 40}, new int[]{37, User32.VK_OEM_BACKTAB, 48}, new int[]{33, 295, 56}, new int[]{29, 351, 64}};
            for (int i10 = 0; i10 < 5; i10++) {
                int i11 = i10 + 1;
                Image image5 = new Image(Game.i.assetManager.getDrawable("icon-crown"));
                image5.setPosition(iArr[i10][0], iArr[i10][1]);
                image5.setSize(iArr[i10][2], iArr[i10][2]);
                if (crownsCount >= i11) {
                    image5.setColor(MaterialColor.LIGHT_BLUE.P500);
                } else {
                    image5.setColor(1.0f, 1.0f, 1.0f, 0.14f);
                }
                this.t.addActor(image5);
            }
        }
        this.B.setColor(1.0f, 1.0f, 1.0f, 0.0f);
        this.B.addAction(Actions.sequence(Actions.delay(0.5f), Actions.alpha(1.0f, 0.75f)));
        e();
        a(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        int i;
        if (this.E == -1) {
            i = FastRandom.getFairInt(HINT_ALIASES.length);
        } else {
            int i2 = this.E + 1;
            i = i2;
            if (i2 >= HINT_ALIASES.length) {
                i = 0;
            }
        }
        this.E = i;
        this.C.clearActions();
        int i3 = i;
        this.C.addAction(Actions.sequence(Actions.fadeOut(0.3f), Actions.run(() -> {
            CharSequence replaceRegionAliasesWithChars = Game.i.assetManager.replaceRegionAliasesWithChars(Game.i.localeManager.i18n.get(HINT_ALIASES[i3]));
            this.C.setSize(900.0f, 84.0f);
            this.C.setPosition(150.0f, 2.0f);
            if (StringFormatter.calculateWidth(replaceRegionAliasesWithChars, Game.i.assetManager.getFont(24)) < 1620.0d) {
                this.C.setStyle(Game.i.assetManager.getLabelStyle(24));
            } else {
                this.C.setStyle(Game.i.assetManager.getLabelStyle(21));
                if (StringFormatter.calculateWidth(replaceRegionAliasesWithChars, Game.i.assetManager.getFont(21)) > 1620.0d) {
                    this.C.setSize(1200.0f, 84.0f);
                    this.C.setPosition(0.0f, 2.0f);
                }
            }
            this.C.setText(replaceRegionAliasesWithChars);
            this.D.setText(Game.i.localeManager.i18n.format("hint_box_stats", (i3 + 1) + "/" + HINT_ALIASES.length));
        }), Actions.fadeIn(0.5f)));
    }

    private void a(boolean z) {
        if (z) {
            UiUtils.bouncyShowOverlay(this.f3291b.getTable(), this.c.getTable(), this.e);
        } else {
            UiUtils.bouncyHideOverlay(this.f3291b.getTable(), this.c.getTable(), this.e);
        }
    }

    public void minimize() {
        this.F._input.enableAllInput();
        this.F._gameUi.setUiScreenshotMode(new GameUiSystem.ScreenshotModeConfig());
        a(false);
    }

    public void maximize() {
        a(true);
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        this.G = true;
        if (this.d != null) {
            this.d.dispose();
        }
        Game.i.uiManager.removeLayer(this.f3291b);
        Game.i.uiManager.removeLayer(this.c);
    }

    public boolean isDisposed() {
        return this.G;
    }
}
