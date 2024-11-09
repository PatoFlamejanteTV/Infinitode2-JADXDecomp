package com.prineside.tdi2.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.StringBuilder;
import com.badlogic.gdx.utils.Timer;
import com.prineside.tdi2.BasicLevelQuestConfig;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Screen;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.enums.DifficultyMode;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ResearchType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.global.ScreenResize;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.DailyQuestManager;
import com.prineside.tdi2.managers.MessageManager;
import com.prineside.tdi2.managers.ReplayManager;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.systems.GameStateSystem;
import com.prineside.tdi2.ui.actors.ComplexButton;
import com.prineside.tdi2.ui.actors.FancyButton;
import com.prineside.tdi2.ui.actors.HorizontalSlider;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.LimitedWidthLabel;
import com.prineside.tdi2.ui.actors.QuadActor;
import com.prineside.tdi2.ui.actors.TableButton;
import com.prineside.tdi2.ui.actors.TextFieldXPlatform;
import com.prineside.tdi2.ui.components.RatingForm;
import com.prineside.tdi2.ui.shared.DailyLootOverlay;
import com.prineside.tdi2.ui.shared.DailyQuestOverlay;
import com.prineside.tdi2.ui.shared.Dialog;
import com.prineside.tdi2.ui.shared.DifficultyModeOverlay;
import com.prineside.tdi2.ui.shared.InventoryOverlay;
import com.prineside.tdi2.ui.shared.MainMenuUiScene;
import com.prineside.tdi2.ui.shared.MessagesOverlay;
import com.prineside.tdi2.ui.shared.MusicListOverlay;
import com.prineside.tdi2.ui.shared.Notifications;
import com.prineside.tdi2.ui.shared.ProfileSummary;
import com.prineside.tdi2.ui.shared.ResourcesAndMoney;
import com.prineside.tdi2.ui.shared.TooltipsOverlay;
import com.prineside.tdi2.ui.shared.WebBrowser;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.QuadDrawable;
import com.prineside.tdi2.utils.QuadDrawableStack;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/MainMenuScreen.class */
public class MainMenuScreen extends Screen {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2786a = TLog.forClass(MainMenuScreen.class);
    public static final String TT_FIRST_LAUNCH_LEVEL_SELECT = "MainMenuScreen.firstLaunchHint";
    public static final String TT_ENDLESS_DIFFICULTY_BUTTON = "MainMenu.endlessModeHint";

    /* renamed from: b, reason: collision with root package name */
    private final UiManager.UiLayer f2787b;
    private final UiManager.UiLayer c;
    private final UiManager.UiLayer d;

    @Null
    private UiManager.UiLayer e;
    private ComplexButton f;
    private Group g;
    private Table h;
    private Label i;
    private Label j;
    private ComplexButton k;
    private ComplexButton l;
    private Image m;
    private Label n;
    private Image o;
    private Image p;
    private Label q;
    private ComplexButton r;
    private Label s;
    private Label t;
    private float u;
    private DailyQuestManager.DailyQuestLevel v;
    private Table w;
    private int x;
    private final Vector2 y;
    private final MessageManager.MessageManagerListener z;
    private final Listener<ScreenResize> A;

    private void a() {
        this.f2787b.getTable().clearChildren();
        MainMenuUiScene.i().rebuildIfNeeded();
        this.f2787b.getTable().clear();
        this.f2787b.getTable().add((Table) MainMenuUiScene.i().getContents()).expand().fill().width(Game.i.uiManager.stage.getWidth());
    }

    public MainMenuScreen() {
        this(false);
    }

    public MainMenuScreen(boolean z) {
        this.f2787b = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 101, "MainMenuScreen trophies");
        this.c = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 102, "MainMenuScreen main");
        this.d = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 103, "MainMenuScreen launchFade");
        this.x = 0;
        this.y = new Vector2();
        this.z = new MessageManager.MessageManagerListener.Adapter() { // from class: com.prineside.tdi2.screens.MainMenuScreen.1
            @Override // com.prineside.tdi2.managers.MessageManager.MessageManagerListener.Adapter, com.prineside.tdi2.managers.MessageManager.MessageManagerListener
            public void messagesUpdated() {
                Threads i = Threads.i();
                MainMenuScreen mainMenuScreen = MainMenuScreen.this;
                i.runOnMainThread(() -> {
                    mainMenuScreen.c();
                });
            }
        };
        this.A = screenResize -> {
            b();
        };
        if (Game.i.actionResolver.isAppModified() || Config.isModdingMode()) {
            f2786a.i("app is modified", new Object[0]);
        } else {
            f2786a.i("app is not modified", new Object[0]);
        }
        Game.i.musicManager.continuePlayingMenuMusicTrack();
        Game.i.researchManager.updateAndValidateStarBranch();
        Game.i.uiManager.hideAllComponents();
        Game.i.uiManager.setAsInputHandler();
        ResourcesAndMoney.i().setVisible(true);
        InventoryOverlay.i().hideWithToggleButton(true);
        ProfileSummary.i().setVisibleClickEnabled(true, true);
        b();
        Game.i.progressManager.givePendingBonuses();
        Game.i.progressManager.checkSpecialTrophiesGiven();
        Game.i.progressManager.showNewlyIssuedPrizesPopup();
        Game.EVENTS.getListeners(ScreenResize.class).add(this.A);
        if (Game.getTimestampSeconds() - Game.i.authManager.lastStateUpdateTimestamp > 30) {
            Game.i.authManager.loadStateFromServer(null, null);
        }
        Game.i.achievementManager.updateGlobal();
        Timer.schedule(new Timer.Task(this) { // from class: com.prineside.tdi2.screens.MainMenuScreen.2
            @Override // com.badlogic.gdx.utils.Timer.Task, java.lang.Runnable
            public void run() {
                Game.i.progressManager.giveDoubleGainIfNecessary();
            }
        }, 3.0f);
        if (Game.i.statisticsManager.getAllTime(StatisticsType.PRT) > 7200.0d && Game.i.authManager.getProgressOwnerPlayerId() == null && !TooltipsOverlay.i().isTagShown("MainMenu.signInSuggestionWithProgress")) {
            TooltipsOverlay.i().showText("MainMenu.signInSuggestionWithProgress", ProfileSummary.i().hintLabel, Game.i.localeManager.i18n.get("tooltip_has_progress_sign_in_suggestion"), ProfileSummary.i().uiLayer.mainUiLayer, ProfileSummary.i().uiLayer.zIndex + 1, 4);
        }
        this.d.getTable().setTouchable(Touchable.disabled);
        if (z) {
            this.d.getTable().setVisible(true);
            Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
            image.setColor(Config.BACKGROUND_COLOR);
            this.d.getTable().add((Table) image).grow();
            this.d.getTable().addAction(Actions.fadeOut(0.8f));
            return;
        }
        this.d.getTable().setVisible(false);
    }

    private void b() {
        String str;
        int round;
        long timestampMillis = Game.getTimestampMillis();
        Table table = this.c.getTable();
        table.clearChildren(true);
        table.add().size(300.0f, 128.0f).colspan(3).top().left().row();
        table.add().expand().fill().colspan(3).row();
        this.w = new Table();
        this.w.setName("MM-layout-bottomLeft");
        table.add(this.w).bottom().left().expandX();
        this.l = new ComplexButton(Game.i.localeManager.i18n.get("mailbox"), Game.i.assetManager.getLabelStyle(24), () -> {
            MessagesOverlay.i().show();
        });
        this.l.setName("main_menu_messages_button");
        this.l.setIconPositioned(Game.i.assetManager.getDrawable("icon-letter"), 21.0f, 14.0f, 64.0f, 64.0f);
        this.l.setLabel(109.0f, 50.0f, 100.0f, 20.0f, 8);
        this.m = new Image(Game.i.assetManager.getDrawable("icon-letter"));
        this.m.setPosition(21.0f, 14.0f);
        this.m.setSize(64.0f, 64.0f);
        this.m.setOrigin(32.0f, 32.0f);
        this.l.addActor(this.m);
        this.n = new Label("No new messages", Game.i.assetManager.getLabelStyle(21));
        this.n.setPosition(109.0f, 20.0f);
        this.n.setSize(100.0f, 20.0f);
        this.n.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        this.l.addActor(this.n);
        this.o = new Image(Game.i.assetManager.getDrawable("count-bubble"));
        this.o.setPosition(72.0f, 52.0f);
        this.o.setSize(21.5f, 24.5f);
        this.o.setVisible(false);
        this.l.addActor(this.o);
        this.p = new Image(Game.i.assetManager.getDrawable("circle"));
        this.p.setPosition(65.0f, 6.0f);
        this.p.setSize(28.0f, 28.0f);
        this.p.setColor(new Color(18357120));
        this.p.setVisible(false);
        this.l.addActor(this.p);
        this.q = new Label("0", Game.i.assetManager.getLabelStyle(18));
        this.q.setPosition(65.0f, 6.0f);
        this.q.setSize(28.0f, 28.0f);
        this.q.setAlignment(1);
        this.l.addActor(this.q);
        this.w.add((Table) this.l).size(300.0f, 92.0f).padBottom(11.0f).padLeft(36.0f).left().row();
        this.k = new ComplexButton("", Game.i.assetManager.getLabelStyle(24), () -> {
            Game.i.authManager.getNews(newsResponse -> {
                WebBrowser.i().webView.loadUrl(Net.HttpMethods.GET, Config.XDX_VIEW_NEWS_URL + newsResponse.id, null);
                WebBrowser.i().setVisible(true);
            });
        });
        this.k.setName("main_menu_news_button");
        this.k.setBackground(new QuadDrawable(new QuadActor(new Color[]{new Color(1.0f, 1.0f, 1.0f, 1.0f), new Color(1.0f, 1.0f, 1.0f, 1.0f), new Color(1.0f, 1.0f, 1.0f, 0.0f), new Color(1.0f, 1.0f, 1.0f, 0.0f)}, new float[]{4.0f, 0.0f, 0.0f, 92.0f, 192.0f, 88.0f, 192.0f, 0.0f})), 0.0f, 0.0f, 192.0f, 92.0f);
        this.k.setBackgroundColors(MaterialColor.LIGHT_BLUE.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.56f), MaterialColor.LIGHT_BLUE.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.4f), MaterialColor.LIGHT_BLUE.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.78f), new Color(741092556));
        this.k.setIconPositioned(Game.i.assetManager.getDrawable("icon-book-opened"), 21.0f, 14.0f, 64.0f, 64.0f);
        this.k.setLabel(109.0f, 50.0f, 100.0f, 20.0f, 8);
        this.t = new Label("", Game.i.assetManager.getLabelStyle(21));
        this.t.setPosition(109.0f, 20.0f);
        this.t.setSize(100.0f, 20.0f);
        this.t.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        this.k.addActor(this.t);
        this.w.add((Table) this.k).size(300.0f, 92.0f).padBottom(11.0f).padLeft(36.0f).left().row();
        this.r = new ComplexButton("", Game.i.assetManager.getLabelStyle(24), () -> {
            WebBrowser.i().webView.loadUrl(Net.HttpMethods.GET, Config.XDX_VIEW_SEASONAL_LEADERBOARD_URL, null);
            WebBrowser.i().setVisible(true);
        });
        this.r.setName("main_menu_season_button");
        this.r.setBackground(new QuadDrawable(new QuadActor(new Color[]{new Color(1.0f, 1.0f, 1.0f, 1.0f), new Color(1.0f, 1.0f, 1.0f, 1.0f), new Color(1.0f, 1.0f, 1.0f, 0.0f), new Color(1.0f, 1.0f, 1.0f, 0.0f)}, new float[]{0.0f, 0.0f, 4.0f, 92.0f, 192.0f, 92.0f, 192.0f, 4.0f})), 0.0f, 0.0f, 192.0f, 92.0f);
        this.r.setBackgroundColors(MaterialColor.LIGHT_BLUE.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.56f), MaterialColor.LIGHT_BLUE.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.4f), MaterialColor.LIGHT_BLUE.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.78f), new Color(741092556));
        this.r.setIconPositioned(Game.i.assetManager.getDrawable("icon-leaf"), 21.0f, 14.0f, 64.0f, 64.0f);
        this.r.setLabel(109.0f, 50.0f, 100.0f, 20.0f, 8);
        this.s = new Label("", Game.i.assetManager.getLabelStyle(21));
        this.s.setPosition(109.0f, 20.0f);
        this.s.setSize(100.0f, 20.0f);
        this.s.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        this.r.addActor(this.s);
        this.w.add((Table) this.r).size(300.0f, 92.0f).padBottom(64.0f).padLeft(36.0f).left().row();
        d();
        Table table2 = new Table();
        this.w.add(table2).padLeft(36.0f).padBottom(36.0f);
        MenuButton menuButton = new MenuButton(false, Game.i.assetManager.getDrawable("icon-music-player"), Game.i.localeManager.i18n.get("mainMenu_musicPlayerButton"), () -> {
            MusicListOverlay.i().show();
        });
        menuButton.setName("main_menu_music_player_button");
        table2.add(menuButton).size(134.0f);
        if (!Game.i.ratingManager.shouldRateButtonBeVisible()) {
            table2.add();
            table2.add();
        } else {
            ComplexButton complexButton = new ComplexButton(StringFormatter.fitToWidth(Game.i.localeManager.i18n.get("rate_this_game"), 256.0f, Game.i.assetManager.getFont(24), "."), Game.i.assetManager.getLabelStyle(24), () -> {
                RatingForm.i().showRatePrompt();
            });
            complexButton.setIconPositioned(Game.i.assetManager.getDrawable("icon-star"), 102.0f, 46.0f, 64.0f, 64.0f);
            complexButton.setLabel(0.0f, 19.0f, 268.0f, 18.0f, 1);
            complexButton.setBackground(new QuadDrawableStack((Array<QuadDrawableStack.QuadActorConfig>) new Array(new QuadDrawableStack.QuadActorConfig[]{new QuadDrawableStack.QuadActorConfig(new QuadActor(Color.WHITE, new float[]{0.0f, 4.0f, 4.0f, 124.0f, 131.0f, 126.0f, 131.0f, 0.0f}), 0.0f, 0.0f, 131.0f, 126.0f), new QuadDrawableStack.QuadActorConfig(new QuadActor(Color.WHITE, new float[]{0.0f, 0.0f, 0.0f, 126.0f, 131.0f, 128.0f, 127.0f, 4.0f}), 131.0f, 0.0f, 131.0f, 128.0f)})), 3.0f, 3.0f, 262.0f, 128.0f);
            complexButton.setBackgroundColors(MaterialColor.LIGHT_GREEN.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.56f), MaterialColor.LIGHT_GREEN.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.4f), MaterialColor.LIGHT_GREEN.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.78f), new Color(741092556));
            table2.add((Table) complexButton).size(268.0f, 134.0f).colspan(2).row();
        }
        table2.row();
        table2.add(new MenuButton(true, Game.i.assetManager.getDrawable("icon-tools"), Game.i.localeManager.i18n.get("mainMenu_settingsButton"), () -> {
            Game.i.screenManager.goToSettingsScreen();
        })).size(134.0f);
        table2.add(new MenuButton(false, Game.i.assetManager.getDrawable("icon-book-closed"), Game.i.localeManager.i18n.get("mainMenu_handbookButton"), () -> {
            Game.i.actionResolver.openHandbook();
        })).size(134.0f);
        table2.add(new MenuButton(true, Game.i.assetManager.getDrawable("icon-info-square"), Game.i.localeManager.i18n.get("mainMenu_aboutButton"), () -> {
            Game.i.screenManager.goToAboutScreen();
        })).size(134.0f);
        Table table3 = new Table();
        table3.setName("MM-layout-bottomCenter");
        table.add(table3).width(342.0f).bottom();
        table3.add().expand().fill().row();
        if (Game.i.progressManager.getDifficultyMode() == DifficultyMode.ENDLESS_I && (round = MathUtils.round(((float) Game.i.gameValueManager.getSnapshot().getPercentValueAsMultiplier(GameValueType.ENDLESS_MODE_DIFFICULTY)) * 100.0f)) != 150) {
            Group group = new Group();
            group.setTransform(false);
            group.setSize(342.0f, 100.0f);
            table3.add((Table) group).bottom().center().padBottom(20.0f).size(342.0f, 120.0f).row();
            Label label = new Label(Game.i.localeManager.i18n.get("difficulty") + ": " + Game.i.progressManager.getDifficultyModeDiffMultiplier(DifficultyMode.ENDLESS_I) + "%", Game.i.assetManager.getLabelStyle(24));
            label.setAlignment(1);
            label.setPosition(2.0f, 70.0f);
            label.setSize(342.0f, 20.0f);
            label.setColor(0.0f, 0.0f, 0.0f, 0.56f);
            group.addActor(label);
            Label label2 = new Label(Game.i.localeManager.i18n.get("difficulty") + ": " + Game.i.progressManager.getDifficultyModeDiffMultiplier(DifficultyMode.ENDLESS_I) + "%", Game.i.assetManager.getLabelStyle(24));
            label2.setAlignment(1);
            label2.setPosition(0.0f, 72.0f);
            label2.setSize(342.0f, 20.0f);
            group.addActor(label2);
            Label label3 = new Label(new StringBuilder("150").toString(), Game.i.assetManager.getLabelStyle(21));
            label3.setColor(1.0f, 1.0f, 1.0f, 0.56f);
            label3.setPosition(4.0f, 36.0f);
            label3.setSize(100.0f, 20.0f);
            group.addActor(label3);
            Label label4 = new Label(new StringBuilder().append(round).toString(), Game.i.assetManager.getLabelStyle(21));
            label4.setColor(1.0f, 1.0f, 1.0f, 0.56f);
            label4.setPosition(238.0f, 36.0f);
            label4.setSize(100.0f, 20.0f);
            label4.setAlignment(16);
            group.addActor(label4);
            HorizontalSlider horizontalSlider = new HorizontalSlider(342.0f, Game.i.progressManager.getDifficultyModeDiffMultiplier(DifficultyMode.ENDLESS_I) * 0.01f, 1.5d, Game.i.gameValueManager.getSnapshot().getPercentValueAsMultiplier(GameValueType.ENDLESS_MODE_DIFFICULTY), d -> {
                int round2 = MathUtils.round(((float) d.doubleValue()) * 20.0f) * 5;
                label2.setText(Game.i.localeManager.i18n.get("difficulty") + ": " + round2 + "%");
                label.setText(label2.getText());
                Game.i.settingsManager.setCustomValue(SettingsManager.CustomValueType.ENDLESS_MODE_DIFFICULTY, round2 / 100.0d);
            });
            horizontalSlider.setNotifyOnDrag(true);
            horizontalSlider.setSize(342.0f, 80.0f);
            group.addActor(horizontalSlider);
        }
        ComplexButton complexButton2 = new ComplexButton(Game.i.progressManager.getDifficultyName(Game.i.progressManager.getDifficultyMode()).toUpperCase(), Game.i.assetManager.getLabelStyle(36), () -> {
            DifficultyModeOverlay.i().setVisible(true);
        });
        complexButton2.setLabel(0.0f, 53.0f, 342.0f, 27.0f, 1);
        table3.add((Table) complexButton2).bottom().center().padBottom(40.0f).size(342.0f, 120.0f);
        if (Game.i.progressManager.difficultyModeAvailable(DifficultyMode.ENDLESS_I) && !TooltipsOverlay.i().isTagShown(TT_ENDLESS_DIFFICULTY_BUTTON)) {
            TooltipsOverlay.i().showText(TT_ENDLESS_DIFFICULTY_BUTTON, complexButton2, Game.i.localeManager.i18n.get("tooltip_difficulty_mode_button"), this.c.mainUiLayer, this.c.zIndex + 1, 2);
        }
        Label label5 = new Label(Game.i.localeManager.i18n.get("game_mode"), Game.i.assetManager.getLabelStyle(21));
        label5.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        label5.setPosition(0.0f, 24.0f);
        label5.setSize(342.0f, 21.0f);
        label5.setAlignment(1);
        complexButton2.addActor(label5);
        switch (Game.i.progressManager.getDifficultyMode()) {
            case EASY:
                complexButton2.setBackgroundColors(MaterialColor.LIGHT_GREEN.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.56f), MaterialColor.LIGHT_GREEN.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.4f), MaterialColor.LIGHT_GREEN.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.78f), Color.WHITE);
                complexButton2.setBackground(new QuadDrawableStack((Array<QuadDrawableStack.QuadActorConfig>) new Array(new QuadDrawableStack.QuadActorConfig[]{new QuadDrawableStack.QuadActorConfig(new QuadActor(Color.WHITE, new float[]{4.0f, 12.0f, 0.0f, 117.0f, 131.0f, 105.0f, 131.0f, 0.0f}), 0.0f, 0.0f, 131.0f, 117.0f), new QuadDrawableStack.QuadActorConfig(new QuadActor(Color.WHITE, new float[]{0.0f, 0.0f, 0.0f, 105.0f, 131.0f, 117.0f, 127.0f, 12.0f}), 131.0f, 0.0f, 131.0f, 117.0f)})), 40.0f, 0.0f, 262.0f, 117.0f);
                break;
            case ENDLESS_I:
                complexButton2.setBackgroundColors(MaterialColor.ORANGE.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.56f), MaterialColor.ORANGE.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.4f), MaterialColor.ORANGE.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.78f), Color.WHITE);
                complexButton2.setBackground(new QuadDrawableStack((Array<QuadDrawableStack.QuadActorConfig>) new Array(new QuadDrawableStack.QuadActorConfig[]{new QuadDrawableStack.QuadActorConfig(new QuadActor(Color.WHITE, new float[]{4.0f, 2.0f, 0.0f, 106.0f, 15.0f, 105.0f, 19.0f, 0.0f}), 0.0f, 14.0f, 19.0f, 106.0f), new QuadDrawableStack.QuadActorConfig(new QuadActor(Color.WHITE, new float[]{4.0f, 2.0f, 0.0f, 106.0f, 15.0f, 105.0f, 19.0f, 0.0f}), 20.0f, 12.0f, 19.0f, 106.0f), new QuadDrawableStack.QuadActorConfig(new QuadActor(Color.WHITE, new float[]{4.0f, 12.0f, 0.0f, 117.0f, 131.0f, 105.0f, 131.0f, 0.0f}), 40.0f, 0.0f, 131.0f, 117.0f), new QuadDrawableStack.QuadActorConfig(new QuadActor(Color.WHITE, new float[]{0.0f, 0.0f, 0.0f, 105.0f, 131.0f, 117.0f, 127.0f, 12.0f}), 171.0f, 0.0f, 131.0f, 117.0f), new QuadDrawableStack.QuadActorConfig(new QuadActor(Color.WHITE, new float[]{0.0f, 0.0f, 4.0f, 105.0f, 19.0f, 106.0f, 15.0f, 2.0f}), 303.0f, 12.0f, 19.0f, 106.0f), new QuadDrawableStack.QuadActorConfig(new QuadActor(Color.WHITE, new float[]{0.0f, 0.0f, 4.0f, 105.0f, 19.0f, 106.0f, 15.0f, 2.0f}), 323.0f, 14.0f, 19.0f, 106.0f)})), 0.0f, 0.0f, 342.0f, 120.0f);
                break;
            default:
                complexButton2.setBackgroundColors(MaterialColor.LIGHT_BLUE.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.56f), MaterialColor.LIGHT_BLUE.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.4f), MaterialColor.LIGHT_BLUE.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.78f), Color.WHITE);
                complexButton2.setBackground(new QuadDrawableStack((Array<QuadDrawableStack.QuadActorConfig>) new Array(new QuadDrawableStack.QuadActorConfig[]{new QuadDrawableStack.QuadActorConfig(new QuadActor(Color.WHITE, new float[]{4.0f, 2.0f, 0.0f, 106.0f, 15.0f, 105.0f, 19.0f, 0.0f}), 0.0f, 12.0f, 19.0f, 106.0f), new QuadDrawableStack.QuadActorConfig(new QuadActor(Color.WHITE, new float[]{4.0f, 12.0f, 0.0f, 117.0f, 131.0f, 105.0f, 131.0f, 0.0f}), 20.0f, 0.0f, 131.0f, 117.0f), new QuadDrawableStack.QuadActorConfig(new QuadActor(Color.WHITE, new float[]{0.0f, 0.0f, 0.0f, 105.0f, 131.0f, 117.0f, 127.0f, 12.0f}), 151.0f, 0.0f, 131.0f, 117.0f), new QuadDrawableStack.QuadActorConfig(new QuadActor(Color.WHITE, new float[]{0.0f, 0.0f, 4.0f, 105.0f, 19.0f, 106.0f, 15.0f, 2.0f}), 283.0f, 12.0f, 19.0f, 106.0f)})), 20.0f, 0.0f, 302.0f, 118.0f);
                break;
        }
        Table table4 = new Table();
        table4.setName("MM-layout-bottomRight");
        table.add(table4).bottom().right().expandX();
        this.f = new ComplexButton(Game.i.localeManager.i18n.get("daily_quest"), Game.i.assetManager.getLabelStyle(24), () -> {
            if (this.v != null) {
                DailyQuestOverlay.i().show(this.v);
            }
        });
        this.f.setBackground(new QuadDrawable(new QuadActor(new Color[]{new Color(1.0f, 1.0f, 1.0f, 0.0f), new Color(1.0f, 1.0f, 1.0f, 0.0f), new Color(1.0f, 1.0f, 1.0f, 1.0f), new Color(1.0f, 1.0f, 1.0f, 1.0f)}, new float[]{0.0f, 0.0f, 0.0f, 88.0f, 192.0f, 92.0f, 188.0f, 0.0f})), 108.0f, 0.0f, 192.0f, 92.0f);
        this.f.setBackgroundColors(MaterialColor.LIGHT_GREEN.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.56f), MaterialColor.LIGHT_GREEN.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.4f), MaterialColor.LIGHT_GREEN.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.78f), new Color(741092556));
        this.f.setIconPositioned(Game.i.assetManager.getDrawable("icon-clock"), 214.0f, 14.0f, 64.0f, 64.0f);
        this.f.setLabel(90.0f, 50.0f, 100.0f, 20.0f, 16);
        this.h = new Table();
        this.h.setSize(400.0f, 20.0f);
        this.h.setPosition(-210.0f, 20.0f);
        this.f.addActor(this.h);
        this.g = new Group();
        this.g.setTransform(false);
        this.g.setSize(300.0f, 92.0f);
        this.f.addActor(this.g);
        e();
        table4.add((Table) this.f).size(300.0f, 92.0f).padBottom(10.0f).padRight(36.0f).right().row();
        ComplexButton complexButton3 = new ComplexButton(Game.i.localeManager.i18n.get("daily_loot"), Game.i.assetManager.getLabelStyle(24), () -> {
            DailyLootOverlay.i().show();
        });
        complexButton3.setBackground(new QuadDrawable(new QuadActor(new Color[]{new Color(1.0f, 1.0f, 1.0f, 0.0f), new Color(1.0f, 1.0f, 1.0f, 0.0f), new Color(1.0f, 1.0f, 1.0f, 1.0f), new Color(1.0f, 1.0f, 1.0f, 1.0f)}, new float[]{0.0f, 4.0f, 0.0f, 92.0f, 188.0f, 92.0f, 192.0f, 0.0f})), 108.0f, 0.0f, 192.0f, 92.0f);
        complexButton3.setBackgroundColors(MaterialColor.LIGHT_GREEN.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.56f), MaterialColor.LIGHT_GREEN.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.4f), MaterialColor.LIGHT_GREEN.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.78f), new Color(741092556));
        complexButton3.setIconPositioned(Game.i.assetManager.getDrawable("icon-calendar"), 214.0f, 14.0f, 64.0f, 64.0f);
        complexButton3.setLabel(90.0f, 50.0f, 100.0f, 20.0f, 16);
        String dailyLootCurrentQuest = Game.i.dailyQuestManager.getDailyLootCurrentQuest();
        if (!dailyLootCurrentQuest.equals(DailyQuestManager.RESET_QUESTS_QUEST_ID)) {
            BasicLevelQuestConfig regularQuestById = Game.i.basicLevelManager.getRegularQuestById(dailyLootCurrentQuest);
            if (regularQuestById == null) {
                str = "Quest " + dailyLootCurrentQuest + " not found";
            } else {
                str = ((Object) regularQuestById.getTitle(false, true)) + " - " + Game.i.localeManager.i18n.get("level") + SequenceUtils.SPACE + regularQuestById.level.name;
            }
        } else {
            str = Game.i.localeManager.i18n.get("daily_loot_quest_reset_quests");
        }
        Label label6 = new Label(str, Game.i.assetManager.getLabelStyle(21));
        label6.setSize(100.0f, 20.0f);
        label6.setPosition(90.0f, 20.0f);
        label6.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        label6.setAlignment(16);
        complexButton3.addActor(label6);
        if (Game.i.dailyQuestManager.isTodayDailyLootQuestCompleted()) {
            Image image = new Image(Game.i.assetManager.getDrawable("main-menu-check-outline"));
            image.setSize(48.0f, 48.0f);
            image.setPosition(248.0f, 14.0f);
            complexButton3.addActor(image);
        } else {
            Image image2 = new Image(Game.i.assetManager.getDrawable("count-bubble"));
            image2.setSize(32.25f, 36.75f);
            image2.setPosition(273.0f, 63.0f);
            complexButton3.addActor(image2);
            image2.addAction(Actions.forever(Actions.sequence(Actions.alpha(0.8f, 0.5f), Actions.alpha(1.0f, 0.5f))));
        }
        table4.add((Table) complexButton3).size(300.0f, 92.0f).padBottom(64.0f).padRight(36.0f).right().row();
        Table table5 = new Table();
        table4.add(table5).padRight(36.0f).padBottom(36.0f);
        MenuButton menuButton2 = new MenuButton(false, Game.i.assetManager.getDrawable("icon-triangle-right"), Game.i.localeManager.i18n.get("mainMenu_continueButton"), () -> {
            GameStateSystem.ContinueGameStatus continueSavedGame;
            if (GameStateSystem.savedGameExists() && (continueSavedGame = GameStateSystem.continueSavedGame()) != GameStateSystem.ContinueGameStatus.SUCCESS) {
                GameStateSystem.deleteSavedGame();
                Game.i.screenManager.goToMainMenu();
                Dialog.i().showAlert(Game.i.localeManager.i18n.get("continue_game_status_" + continueSavedGame.name()));
            }
        });
        ReplayManager.ReplayHeader savedGameInfo = GameStateSystem.getSavedGameInfo();
        if (!GameStateSystem.savedGameExists() || savedGameInfo == null) {
            menuButton2.setEnabled(false);
        } else {
            menuButton2.setEnabled(true);
            menuButton2.flavor = 1;
            menuButton2.setupBackgroundDrawables();
            try {
                TextureRegion textureRegion = null;
                if (savedGameInfo.gameMode == GameStateSystem.GameMode.BASIC_LEVELS) {
                    textureRegion = Game.i.basicLevelManager.getLevel(savedGameInfo.basicLevelName).getPreview();
                } else if (savedGameInfo.gameMode == GameStateSystem.GameMode.USER_MAPS) {
                    textureRegion = Game.i.userMapManager.getUserMap(savedGameInfo.userMapId).map.getPreview().getTextureRegion();
                }
                if (textureRegion != null) {
                    Group group2 = new Group();
                    group2.setTransform(false);
                    group2.setSize(174.0f, 128.0f);
                    group2.setPosition(-184.0f, 2.0f);
                    menuButton2.addActor(group2);
                    Image image3 = new Image(Game.i.assetManager.getQuad("ui.mainMenu.continueButtonLevelInfo"));
                    image3.setSize(174.0f, 128.0f);
                    group2.addActor(image3);
                    Image image4 = new Image(textureRegion);
                    image4.setPosition(9.0f, 6.0f);
                    image4.setSize(155.0f, 115.0f);
                    group2.addActor(image4);
                    Image image5 = new Image(Game.i.assetManager.getDrawable("gradient-corner-top-right"));
                    image5.setSize(139.2f, 102.4f);
                    image5.setColor(Config.BACKGROUND_COLOR.cpy().mul(1.0f, 1.0f, 1.0f, 0.78f));
                    image5.setPosition(image4.getX() - 1.0f, image4.getY() - 1.0f);
                    group2.addActor(image5);
                    Label label7 = new Label("", Game.i.assetManager.getLabelStyle(18));
                    if (savedGameInfo.gameMode == GameStateSystem.GameMode.BASIC_LEVELS) {
                        label7.setText(savedGameInfo.basicLevelName);
                    } else {
                        label7.setText(Game.i.userMapManager.getUserMap(savedGameInfo.userMapId).name);
                    }
                    label7.setColor(1.0f, 1.0f, 1.0f, 1.0f);
                    label7.setPosition(10.0f, 52.0f);
                    group2.addActor(label7);
                    Label label8 = new Label("", Game.i.assetManager.getLabelStyle(18));
                    if (savedGameInfo.gameMode == GameStateSystem.GameMode.BASIC_LEVELS) {
                        label8.setText(Game.i.localeManager.i18n.get("basic_level"));
                    } else {
                        label8.setText(Game.i.localeManager.i18n.get("custom_map"));
                    }
                    label8.setColor(1.0f, 1.0f, 1.0f, 0.78f);
                    label8.setPosition(10.0f, 32.0f);
                    group2.addActor(label8);
                    Label label9 = new Label("", Game.i.assetManager.getLabelStyle(18));
                    label9.setText(Game.i.progressManager.getDifficultyName(savedGameInfo.difficultyMode));
                    label9.setColor(Game.i.progressManager.getDifficultyModeColor(savedGameInfo.difficultyMode));
                    label9.setPosition(10.0f, 12.0f);
                    group2.addActor(label9);
                    Label label10 = new Label("", Game.i.assetManager.getLabelStyle(18));
                    label10.setText(StringFormatter.digestTime((int) savedGameInfo.playRealTime));
                    label10.setColor(1.0f, 1.0f, 1.0f, 1.0f);
                    label10.setPosition(10.0f, 100.0f);
                    label10.setSize(154.0f, 15.0f);
                    label10.setAlignment(16);
                    group2.addActor(label10);
                }
            } catch (Exception e) {
                f2786a.e("failed to show continued game info", e);
            }
        }
        table5.add(menuButton2).size(134.0f);
        ComplexButton complexButton4 = new ComplexButton(StringFormatter.fitToWidth(Game.i.localeManager.i18n.get("mainMenu_newGameButton"), 256.0f, Game.i.assetManager.getFont(24), "."), Game.i.assetManager.getLabelStyle(24), () -> {
            Game.i.screenManager.goToLevelSelectScreen();
        });
        complexButton4.setIconPositioned(Game.i.assetManager.getDrawable("icon-joystick"), 102.0f, 46.0f, 64.0f, 64.0f);
        complexButton4.setLabel(0.0f, 19.0f, 268.0f, 18.0f, 1);
        complexButton4.setBackground(new QuadDrawableStack((Array<QuadDrawableStack.QuadActorConfig>) new Array(new QuadDrawableStack.QuadActorConfig[]{new QuadDrawableStack.QuadActorConfig(new QuadActor(Color.WHITE, new float[]{0.0f, 4.0f, 4.0f, 124.0f, 131.0f, 126.0f, 131.0f, 0.0f}), 0.0f, 0.0f, 131.0f, 126.0f), new QuadDrawableStack.QuadActorConfig(new QuadActor(Color.WHITE, new float[]{0.0f, 0.0f, 0.0f, 126.0f, 131.0f, 128.0f, 127.0f, 4.0f}), 131.0f, 0.0f, 131.0f, 128.0f)})), 3.0f, 3.0f, 262.0f, 128.0f);
        if (!GameStateSystem.savedGameExists()) {
            complexButton4.setBackgroundColors(MaterialColor.LIGHT_GREEN.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.56f), MaterialColor.LIGHT_GREEN.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.4f), MaterialColor.LIGHT_GREEN.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.78f), new Color(741092556));
        } else {
            complexButton4.setBackgroundColors(MaterialColor.LIGHT_BLUE.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.56f), MaterialColor.LIGHT_BLUE.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.4f), MaterialColor.LIGHT_BLUE.P800.cpy().mul(1.0f, 1.0f, 1.0f, 0.78f), new Color(741092556));
        }
        complexButton4.setName("main_menu_new_game_button");
        table5.add((Table) complexButton4).size(268.0f, 134.0f).colspan(2).row();
        if (!Game.i.progressManager.existsAnyProgress() && !TooltipsOverlay.i().isTagShown(TT_FIRST_LAUNCH_LEVEL_SELECT)) {
            TooltipsOverlay.i().showText(TT_FIRST_LAUNCH_LEVEL_SELECT, complexButton4, Game.i.localeManager.i18n.get("tooltip_first_start_button_highlight"), this.c.mainUiLayer, this.c.zIndex + 1, 2);
        }
        MenuButton menuButton3 = new MenuButton(true, Game.i.assetManager.getDrawable("icon-statistics"), Game.i.localeManager.i18n.get("mainMenu_statisticsButton"), () -> {
            Game.i.screenManager.goToStatisticsScreen();
        });
        table5.add(menuButton3).size(134.0f);
        int countAchievementsToRedeem = Game.i.achievementManager.countAchievementsToRedeem();
        if (countAchievementsToRedeem > 0) {
            Image image6 = new Image(Game.i.assetManager.getDrawable("count-bubble"));
            image6.setSize(43.0f, 49.0f);
            image6.setPosition(97.0f, 91.0f);
            menuButton3.addActor(image6);
            image6.addAction(Actions.forever(Actions.sequence(Actions.alpha(0.8f, 0.5f), Actions.alpha(1.0f, 0.5f))));
            Label label11 = new Label(String.valueOf(countAchievementsToRedeem), Game.i.assetManager.getLabelStyle(21));
            label11.setSize(43.0f, 49.0f);
            label11.setPosition(97.0f, 93.0f);
            label11.setAlignment(1);
            menuButton3.addActor(label11);
        }
        MenuButton menuButton4 = new MenuButton(false, Game.i.assetManager.getDrawable("icon-research"), Game.i.localeManager.i18n.get("mainMenu_researchesButton"), () -> {
            Game.i.screenManager.goToResearchesScreen();
        });
        table5.add(menuButton4).size(134.0f);
        Game.i.researchManager.updateAfforableResearchesCount();
        int afforableResearchesCount = Game.i.researchManager.getAfforableResearchesCount();
        if (afforableResearchesCount > 0) {
            Image image7 = new Image(Game.i.assetManager.getDrawable("count-bubble"));
            image7.setSize(43.0f, 49.0f);
            image7.setPosition(97.0f, 91.0f);
            menuButton4.addActor(image7);
            image7.addAction(Actions.forever(Actions.sequence(Actions.alpha(0.8f, 0.5f), Actions.alpha(1.0f, 0.5f))));
            Label label12 = new Label(String.valueOf(afforableResearchesCount), Game.i.assetManager.getLabelStyle(21));
            label12.setSize(43.0f, 49.0f);
            label12.setPosition(97.0f, 93.0f);
            label12.setAlignment(1);
            menuButton4.addActor(label12);
        }
        MenuButton menuButton5 = new MenuButton(true, Game.i.assetManager.getDrawable("icon-edit"), Game.i.localeManager.i18n.get("mainMenu_mapEditorButton"), null);
        table5.add(menuButton5).size(134.0f);
        if (Game.i.userMapManager.isMapEditorAvailable()) {
            menuButton5.setClickHandler(() -> {
                Game.i.screenManager.goToCustomMapSelectScreen();
            });
        } else {
            menuButton5.setClickHandler(() -> {
                Dialog.i().showAlert(Game.i.localeManager.i18n.get("unlock_feature_by_installing_according_research"));
                Game.i.screenManager.goToResearchesScreenFocusOnResearch(ResearchType.STORYLINE_BUBBLE_SORT);
            });
            menuButton5.flavor = 2;
            menuButton5.setupBackgroundDrawables();
        }
        if (Config.isModdingMode()) {
            this.k.setVisible(false);
            this.f.setVisible(false);
            this.g.setVisible(false);
            this.h.setVisible(false);
            this.i.setVisible(false);
            this.j.setVisible(false);
            this.r.setVisible(false);
            this.s.setVisible(false);
            this.t.setVisible(false);
            complexButton3.setVisible(false);
        }
        a();
        c();
        Game.i.messageManager.addListener(this.z);
        if (this.e != null) {
            this.e.getTable().clear();
            Table table6 = new Table();
            table6.background(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(new Color(0.0f, 0.0f, 0.0f, 0.78f)));
            this.e.getTable().add(table6).row();
            Table table7 = new Table();
            table6.add(table7).pad(10.0f).row();
            table7.add((Table) new Label("Connected to the Beta server", Game.i.assetManager.getLabelStyle(30))).row();
            table7.add((Table) new Label("Anything you do here won't affect your main account nor its progress.", Game.i.assetManager.getLabelStyle(24))).row();
            if (Game.i.authManager.isSignedIn()) {
                Table table8 = new Table();
                table7.add(table8).row();
                table8.add((Table) new Label("Loading...", Game.i.assetManager.getLabelStyle(24))).row();
                Game.i.httpManager.post(Config.GET_BETA_ACCOUNT_LINK_STATUS_URL).param("sessionid", Game.i.authManager.getSessionId()).listener((z, httpResponse, z2, th) -> {
                    String resultAsString = httpResponse.getResultAsString();
                    Threads.i().runOnMainThread(() -> {
                        f2786a.i(resultAsString, new Object[0]);
                        table8.clear();
                        if (z) {
                            try {
                                JsonValue parse = new JsonReader().parse(resultAsString);
                                if (parse.getString("status").equals("success")) {
                                    if (parse.getBoolean("linked")) {
                                        Label label13 = new Label("Linked to main account: " + parse.getString("nickname"), Game.i.assetManager.getLabelStyle(24));
                                        label13.setColor(MaterialColor.LIGHT_GREEN.P500);
                                        table8.add((Table) label13).row();
                                        return;
                                    }
                                    table8.add((Table) new Label("Please sign in with your main account to link it to this beta account:", Game.i.assetManager.getLabelStyle(24))).row();
                                    TextFieldXPlatform textFieldXPlatform = new TextFieldXPlatform("", Game.i.assetManager.getTextFieldStyle(21));
                                    textFieldXPlatform.setMessageText("Nickname");
                                    table8.add((Table) textFieldXPlatform).size(320.0f, 48.0f).padTop(10.0f).row();
                                    TextFieldXPlatform textFieldXPlatform2 = new TextFieldXPlatform("", Game.i.assetManager.getTextFieldStyleWithVariant(21, false));
                                    textFieldXPlatform2.setMessageText("Password");
                                    textFieldXPlatform2.setPasswordMode(true);
                                    textFieldXPlatform2.setPasswordCharacter('*');
                                    table8.add((Table) textFieldXPlatform2).size(320.0f, 48.0f).padTop(10.0f).row();
                                    table8.add(new FancyButton("regularButton.a", () -> {
                                        table8.clear();
                                        table8.add((Table) new Label("Linking...", Game.i.assetManager.getLabelStyle(24))).row();
                                        f2786a.i("login: " + textFieldXPlatform.getText(), new Object[0]);
                                        f2786a.i("password: " + textFieldXPlatform2.getText(), new Object[0]);
                                        Game.i.httpManager.post(Config.LINK_BETA_ACCOUNT_URL).param("sessionid", Game.i.authManager.getSessionId()).param("nickname", textFieldXPlatform.getText()).param("password", textFieldXPlatform2.getText()).listener((z, httpResponse, z2, th) -> {
                                            String resultAsString2 = httpResponse.getResultAsString();
                                            f2786a.i(resultAsString2, new Object[0]);
                                            Threads.i().runOnMainThread(() -> {
                                                try {
                                                    JsonValue parse2 = new JsonReader().parse(resultAsString2);
                                                    if (parse2.getString("status").equals("success")) {
                                                        b();
                                                        return;
                                                    }
                                                    table8.clear();
                                                    Label label14 = new Label("Error: " + parse2.getString("error"), Game.i.assetManager.getLabelStyle(24));
                                                    label14.setColor(MaterialColor.RED.P500);
                                                    table8.add((Table) label14).row();
                                                    f2786a.e(resultAsString, new Object[0]);
                                                } catch (Exception e2) {
                                                    table8.clear();
                                                    Label label15 = new Label("Failed to parse response", Game.i.assetManager.getLabelStyle(24));
                                                    label15.setColor(MaterialColor.RED.P500);
                                                    table8.add((Table) label15).row();
                                                    f2786a.e(resultAsString, e2);
                                                }
                                            });
                                        }).send();
                                    }).withLabel(21, "Link my main account")).size(320.0f, 48.0f).padTop(10.0f).row();
                                    return;
                                }
                                Label label14 = new Label("Error: " + parse.getString("error"), Game.i.assetManager.getLabelStyle(24));
                                label14.setColor(MaterialColor.RED.P500);
                                table8.add((Table) label14).row();
                                f2786a.e(resultAsString, new Object[0]);
                                return;
                            } catch (Exception e2) {
                                Label label15 = new Label("Error parsing a request", Game.i.assetManager.getLabelStyle(24));
                                label15.setColor(MaterialColor.RED.P500);
                                table8.add((Table) label15).row();
                                f2786a.e("GET_BETA_ACCOUNT_LINK_STATUS failed", e2);
                                f2786a.e(resultAsString, new Object[0]);
                                return;
                            }
                        }
                        Label label16 = new Label("Error sending a request", Game.i.assetManager.getLabelStyle(24));
                        label16.setColor(MaterialColor.RED.P500);
                        table8.add((Table) label16).row();
                        f2786a.e("GET_BETA_ACCOUNT_LINK_STATUS failed", th);
                        f2786a.e(resultAsString, new Object[0]);
                    });
                }).send();
            } else {
                table7.add((Table) new Label("You can create a new account on this Beta server (or multiple, for testing purposes).", Game.i.assetManager.getLabelStyle(24))).row();
                table7.add((Table) new Label("If you want to receive a Beta tester badge on your main account:", Game.i.assetManager.getLabelStyle(24))).row();
                table7.add((Table) new Label("1. Create a beta account and sign in", Game.i.assetManager.getLabelStyle(24))).row();
                table7.add((Table) new Label("2. A form will appear here, asking you to link your main account - fill it in", Game.i.assetManager.getLabelStyle(24))).row();
                table7.add((Table) new Label("Use a single account to report issues and progress through this Beta version of the game.", Game.i.assetManager.getLabelStyle(24))).row();
                table7.add((Table) new Label("Most active Beta testers will receive the Beta tester badge at the end of testing if they have linked their main account", Game.i.assetManager.getLabelStyle(24))).row();
            }
        }
        f2786a.d("rebuild took " + (Game.getTimestampMillis() - timestampMillis) + "ms", new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        Game.i.messageManager.processLocalMessages();
        MessageManager messageManager = Game.i.messageManager;
        int unreadMessageCount = messageManager.getUnreadMessageCount();
        if (unreadMessageCount > 0) {
            this.l.setIconLabelColors(Color.WHITE, MaterialColor.LIGHT_BLUE.P500, MaterialColor.LIGHT_BLUE.P700, Color.GRAY);
            this.n.setColor(1.0f, 1.0f, 1.0f, 0.56f);
            this.o.setVisible(true);
            this.n.setText(Game.i.localeManager.i18n.get("unread_message_count") + SequenceUtils.SPACE + unreadMessageCount);
            this.m.setVisible(true);
            this.m.clearActions();
            this.m.addAction(Actions.forever(Actions.sequence(Actions.scaleTo(1.0f, 1.0f), Actions.alpha(0.56f), Actions.parallel(Actions.scaleTo(1.7f, 1.7f, 0.8f), Actions.alpha(0.0f, 0.8f)), Actions.delay(0.5f))));
        } else {
            this.l.setIconLabelColors(new Color(1.0f, 1.0f, 1.0f, 0.56f), MaterialColor.LIGHT_BLUE.P500, MaterialColor.LIGHT_BLUE.P700, Color.GRAY);
            this.n.setColor(1.0f, 1.0f, 1.0f, 0.28f);
            this.n.setText(Game.i.localeManager.i18n.get("no_new_messages"));
            this.m.setVisible(false);
            this.o.setVisible(false);
        }
        if (messageManager.getTotalMessageCount() == 0) {
            this.p.setVisible(false);
            this.q.setVisible(false);
        } else {
            this.p.setVisible(true);
            this.q.setVisible(true);
            this.q.setTextFromInt(messageManager.getTotalMessageCount());
        }
    }

    private void d() {
        this.t.setText("");
        this.k.clearActions();
        this.k.addAction(Actions.sequence(Actions.fadeIn(0.5f)));
        this.k.setText(Game.i.localeManager.i18n.get("loading..."));
        this.r.setText(Game.i.localeManager.i18n.get("loading..."));
        Game.i.authManager.getNews(newsResponse -> {
            if (newsResponse == null) {
                this.k.clearActions();
                this.k.addAction(Actions.fadeOut(0.5f));
                this.r.clearActions();
                this.r.addAction(Actions.fadeOut(0.5f));
                return;
            }
            try {
                if (!Game.i.authManager.gameUpdateNotificationShown) {
                    Game.i.authManager.gameUpdateNotificationShown = true;
                    if (208 < newsResponse.networkRequiredVersion) {
                        Notifications.i().add(Game.i.localeManager.i18n.get("update_game_for_leaderboards"), Game.i.assetManager.getDrawable("icon-exclamation-triangle"), MaterialColor.ORANGE.P800, StaticSoundType.NOTIFICATION);
                    } else if (208 < newsResponse.lastVersion) {
                        Notifications.i().add(Game.i.localeManager.i18n.get("game_update_available"), Game.i.assetManager.getDrawable("icon-info-square"), MaterialColor.GREEN.P800, StaticSoundType.NOTIFICATION);
                    }
                }
                String str = newsResponse.title;
                String str2 = newsResponse.body;
                if (str.length() > 24) {
                    str = str.substring(0, 24) + "...";
                }
                this.k.setText(str);
                StringBuilder stringBuilder = new StringBuilder(str2.trim().split(SequenceUtils.EOL)[0]);
                StringBuilder stringBuilder2 = stringBuilder;
                if (stringBuilder.length() > 48) {
                    String[] split = stringBuilder2.toString().split(SequenceUtils.SPACE);
                    stringBuilder2 = new StringBuilder();
                    for (String str3 : split) {
                        if (stringBuilder2.length() + str3.length() > 48) {
                            break;
                        }
                        if (stringBuilder2.length() != 0) {
                            stringBuilder2.append(' ');
                        }
                        stringBuilder2.append(str3);
                    }
                    stringBuilder2.append("...");
                }
                this.t.setText(stringBuilder2.toString());
                this.r.setText(Game.i.localeManager.i18n.format("season_formatted", Integer.valueOf(newsResponse.seasonNumber)));
                if (newsResponse.seasonPosition > 0 && newsResponse.seasonPlayerCount > 0) {
                    StringBuilder stringBuilder3 = new StringBuilder();
                    stringBuilder3.append(StringFormatter.commaSeparatedNumber(newsResponse.seasonPosition));
                    stringBuilder3.append(" / ");
                    stringBuilder3.append(StringFormatter.commaSeparatedNumber(newsResponse.seasonPlayerCount));
                    stringBuilder3.append(" - ");
                    stringBuilder3.append(Game.i.localeManager.i18n.format("top_percent", Integer.valueOf(MathUtils.ceil((newsResponse.seasonPosition / newsResponse.seasonPlayerCount) * 100.0f))));
                    this.s.setText(stringBuilder3);
                } else if (Game.i.authManager.isSignedIn()) {
                    this.s.setText(Game.i.localeManager.i18n.get("not_ranked"));
                } else {
                    this.s.setText(Game.i.localeManager.i18n.get("sign_in_to_get_ranked"));
                }
                if (Game.i.progressManager.existsAnyProgress() && ((int) Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.LAST_AUTO_SHOWN_NEWS_ID)) < newsResponse.id) {
                    Game.i.settingsManager.setCustomValue(SettingsManager.CustomValueType.LAST_AUTO_SHOWN_NEWS_ID, newsResponse.id);
                    WebBrowser.i().webView.loadUrl(Net.HttpMethods.GET, Config.XDX_VIEW_NEWS_URL + newsResponse.id, null);
                    WebBrowser.i().setVisible(true);
                }
            } catch (Exception e) {
                f2786a.e("failed to set last news", e);
                this.k.clearActions();
                this.k.addAction(Actions.fadeOut(0.5f));
                this.r.clearActions();
                this.r.addAction(Actions.fadeOut(0.5f));
            }
        });
    }

    private void e() {
        this.f.setEnabled(false);
        this.h.clear();
        this.g.clear();
        this.h.add().height(1.0f).expandX().fillX();
        Label label = new Label(Game.i.localeManager.i18n.get("time_left") + ": ", Game.i.assetManager.getLabelStyle(21));
        label.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        this.h.add((Table) label);
        this.j = new Label(Game.i.localeManager.i18n.get("loading..."), Game.i.assetManager.getLabelStyle(21));
        this.j.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        this.h.add((Table) this.j);
        this.i = new Label("", Game.i.assetManager.getLabelStyle(21));
        this.i.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        this.h.add((Table) this.i);
        Game.i.dailyQuestManager.getCurrentDayLevel(dailyQuestLevel -> {
            Game.i.runWhenGameIsLoaded(() -> {
                dailyQuestLevel.validate();
                this.v = dailyQuestLevel;
                if (Game.i.basicLevelManager.getLevel(dailyQuestLevel.getLevelName()) == null) {
                    throw new IllegalStateException("Basic level " + dailyQuestLevel.getLevelName() + " not found");
                }
                this.f.setEnabled(true);
                if (dailyQuestLevel.wasCompleted()) {
                    Image image = new Image(Game.i.assetManager.getDrawable("main-menu-check-outline"));
                    image.setSize(48.0f, 48.0f);
                    image.setPosition(248.0f, 14.0f);
                    this.g.addActor(image);
                } else {
                    Image image2 = new Image(Game.i.assetManager.getDrawable("count-bubble"));
                    image2.setSize(32.25f, 36.75f);
                    image2.setPosition(273.0f, 63.0f);
                    this.g.addActor(image2);
                    image2.addAction(Actions.forever(Actions.sequence(Actions.alpha(0.8f, 0.5f), Actions.alpha(1.0f, 0.5f))));
                }
                Image image3 = new Image(Game.i.assetManager.getDrawable("ui-main-menu-dq-preview"));
                image3.setPosition(130.0f, 99.0f);
                image3.setSize(170.0f, 138.0f);
                this.g.addActor(image3);
                Image image4 = new Image(Game.i.basicLevelManager.getLevel(dailyQuestLevel.getLevelName()).getMap().getPreview().getTextureRegion());
                image4.setSize(155.0f, 115.0f);
                image4.setPosition(139.0f, 113.0f);
                this.g.addActor(image4);
                Game.i.dailyQuestManager.getLeaderboards(Game.i.dailyQuestManager.getCurrentDayDate(), dailyQuestLeaderboards -> {
                    String format;
                    if (dailyQuestLeaderboards.success && dailyQuestLeaderboards.player != null) {
                        if (dailyQuestLeaderboards.player.rank == 1) {
                            format = Game.i.localeManager.i18n.get("leader") + "!";
                        } else {
                            int ceil = MathUtils.ceil((dailyQuestLeaderboards.player.rank / dailyQuestLeaderboards.player.total) * 100.0f);
                            int i = ceil;
                            if (ceil < 0) {
                                i = 0;
                            }
                            if (i > 100) {
                                i = 100;
                            }
                            format = Game.i.localeManager.i18n.format("top_percent", Integer.valueOf(i));
                        }
                        this.i.setText(" - " + format);
                        return;
                    }
                    this.i.setText("");
                });
            });
        });
    }

    @Override // com.prineside.tdi2.Screen
    public void draw(float f) {
        this.y.setZero();
        Vector2 localToStageCoordinates = this.w.localToStageCoordinates(this.y);
        if (this.x < 3 && (localToStageCoordinates.x < -150.0f || localToStageCoordinates.x > 150.0f || localToStageCoordinates.y < -150.0f || localToStageCoordinates.y > 150.0f)) {
            this.x++;
            f2786a.e("broken layout, fixing (" + this.x + "): " + localToStageCoordinates.x + ", " + localToStageCoordinates.y, new Object[0]);
            b();
        }
        Color color = Game.i.assetManager.getColor("menu_background");
        Gdx.gl.glClearColor(color.r, color.g, color.f888b, color.f889a);
        Gdx.gl.glClear(16640);
        if (Game.isLoaded() && Game.i.settingsManager.isEscButtonJustPressed() && !Dialog.i().isVisible()) {
            Dialog.i().showConfirm(Game.i.localeManager.i18n.get("exit_game_confirm"), Game::exit);
            Dialog.i().ignoreEscForFrame = true;
        }
        if (this.v != null && this.j != null) {
            this.u += f;
            if (this.u >= 1.0f) {
                this.u -= 1.0f;
                int timestampSeconds = this.v.endTimestamp - Game.getTimestampSeconds();
                if (timestampSeconds > 0) {
                    this.j.setText(StringFormatter.digestTime(timestampSeconds));
                } else {
                    e();
                }
            }
        }
    }

    @Override // com.prineside.tdi2.Screen, com.badlogic.gdx.Screen, com.badlogic.gdx.utils.Disposable
    public void dispose() {
        Game.i.uiManager.removeLayer(this.c);
        Game.i.uiManager.removeLayer(this.f2787b);
        Game.i.uiManager.removeLayer(this.d);
        if (this.e != null) {
            Game.i.uiManager.removeLayer(this.e);
        }
        Game.EVENTS.getListeners(ScreenResize.class).remove(this.A);
        Game.i.messageManager.removeListener(this.z);
        f2786a.i("disposed", new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/MainMenuScreen$MenuButton.class */
    public static class MenuButton extends TableButton {
        public static final float SIZE = 134.0f;
        public static final int FLAVOR_NORMAL = 0;
        public static final int FLAVOR_GREEN = 1;
        public static final int FLAVOR_GREY = 2;
        public int flavor;
        private boolean k;

        public MenuButton(boolean z, Drawable drawable, CharSequence charSequence, Runnable runnable) {
            super(runnable);
            this.flavor = 0;
            this.k = z;
            setupBackgroundDrawables();
            Actor image = new Image(drawable);
            image.setPosition(35.0f, 46.0f);
            image.setSize(64.0f, 64.0f);
            addActor(image);
            LimitedWidthLabel limitedWidthLabel = new LimitedWidthLabel(charSequence, 21, 18, 110.0f);
            limitedWidthLabel.setPosition(0.0f, 18.0f);
            limitedWidthLabel.setSize(134.0f, 18.0f);
            limitedWidthLabel.setAlignment(1);
            addActor(limitedWidthLabel);
        }

        public void setupBackgroundDrawables() {
            String str = this.k ? FlexmarkHtmlConverter.A_NODE : FlexmarkHtmlConverter.B_NODE;
            switch (this.flavor) {
                case 1:
                    str = str + "Green";
                    break;
                case 2:
                    str = str + "Grey";
                    break;
            }
            setBackgroundDrawables(Game.i.assetManager.getQuad("screen.mainMenu.squareButton." + str + ".normal"), Game.i.assetManager.getQuad("screen.mainMenu.squareButton." + str + ".active"), Game.i.assetManager.getQuad("screen.mainMenu.squareButton." + str + ".hover"), Game.i.assetManager.getQuad("screen.mainMenu.squareButton." + str + ".disabled"));
        }
    }
}
