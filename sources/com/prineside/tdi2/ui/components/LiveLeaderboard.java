package com.prineside.tdi2.ui.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.net.HttpParametersUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.enums.DifficultyMode;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.Render;
import com.prineside.tdi2.events.game.ScoreChange;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.ui.actors.HotKeyHintLabel;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.LimitedWidthLabel;
import com.prineside.tdi2.ui.actors.SideMenu;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.errorhandling.CrashHandler;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/LiveLeaderboard.class */
public class LiveLeaderboard implements Disposable {
    private boolean c;
    private int d;
    private Group f;
    private Label g;
    private Label h;
    private Label i;
    private Label j;
    private Label k;
    private Label l;
    private Label m;
    private Image n;
    private Group o;
    private Group p;
    private Group q;
    private Group r;
    private PlaceConfig t;
    private GameSystemProvider y;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3315a = TLog.forClass(LiveLeaderboard.class);
    private static final StringBuilder A = new StringBuilder();

    /* renamed from: b, reason: collision with root package name */
    private final UiManager.UiLayer f3316b = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 101, "LiveLeaderboard");
    private Array<PlaceConfig> e = new Array<>(PlaceConfig.class);
    private Pool<ListItem> s = new Pool<ListItem>(1, 512) { // from class: com.prineside.tdi2.ui.components.LiveLeaderboard.1
        {
            super(1, 512);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.badlogic.gdx.utils.Pool
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ListItem newObject() {
            return new ListItem(LiveLeaderboard.this, false);
        }
    };
    private int u = -1;
    private int v = -1;
    private Array<PlaceConfig> w = new Array<>(PlaceConfig.class);
    private Array<ListItem> x = new Array<>(ListItem.class);
    private final Runnable z = this::d;

    public LiveLeaderboard(final GameSystemProvider gameSystemProvider) {
        this.y = gameSystemProvider;
        final Group group = new Group();
        group.setTransform(false);
        group.setTouchable(Touchable.childrenOnly);
        this.f3316b.getTable().add((Table) group).expand().top().right().size(320.0f, 336.0f).padRight(40.0f);
        this.f3316b.getTable().add().expandY().fillY().row();
        this.r = new Group();
        this.r.setTransform(false);
        this.r.setSize(320.0f, 336.0f);
        this.r.setPosition(0.0f, 336.0f);
        this.r.setTouchable(Touchable.enabled);
        this.r.addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.components.LiveLeaderboard.2
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                LiveLeaderboard.this.toggleVisible();
            }
        });
        group.addActor(this.r);
        this.f = new Group();
        this.f.setTransform(false);
        this.f.setPosition(0.0f, 86.0f);
        this.r.addActor(this.f);
        gameSystemProvider.events.getListeners(Render.class).add(new Listener<Render>() { // from class: com.prineside.tdi2.ui.components.LiveLeaderboard.3
            @Override // com.prineside.tdi2.events.Listener
            public void handleEvent(Render render) {
                LiveLeaderboard.this.b();
            }
        });
        String playerId = Game.i.authManager.getPlayerId();
        if (gameSystemProvider.gameState.basicLevelName != null && Game.i.basicLevelManager.getLevel(gameSystemProvider.gameState.basicLevelName).hasLeaderboards && playerId != null && Game.i.authManager.isSignedIn() && ((gameSystemProvider.gameState.difficultyMode == DifficultyMode.NORMAL || DifficultyMode.isEndless(gameSystemProvider.gameState.difficultyMode)) && Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.LIVE_LEADERBOARDS) != 0.0d)) {
            Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
            httpRequest.setUrl(Config.GET_RUNTIME_LEADERBOARDS_URL);
            HashMap hashMap = new HashMap();
            hashMap.put("gamemode", gameSystemProvider.gameState.gameMode.name());
            hashMap.put("difficulty", gameSystemProvider.gameState.difficultyMode.name());
            hashMap.put("mapname", gameSystemProvider.gameState.basicLevelName);
            hashMap.put("playerid", playerId);
            httpRequest.setContent(HttpParametersUtils.convertHttpParameters(hashMap));
            Gdx.f881net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() { // from class: com.prineside.tdi2.ui.components.LiveLeaderboard.4
                @Override // com.badlogic.gdx.Net.HttpResponseListener
                public void handleHttpResponse(Net.HttpResponse httpResponse) {
                    try {
                        String resultAsString = httpResponse.getResultAsString();
                        LiveLeaderboard.f3315a.i("getRuntimeLeaderboards result for map name " + gameSystemProvider.gameState.basicLevelName, new Object[0]);
                        JsonValue parse = new JsonReader().parse(resultAsString);
                        if (parse.getString("status").equals("success")) {
                            Threads i = Threads.i();
                            GameSystemProvider gameSystemProvider2 = gameSystemProvider;
                            Group group2 = group;
                            i.runOnMainThread(() -> {
                                if (gameSystemProvider2 == null || gameSystemProvider2.gameState == null) {
                                    return;
                                }
                                LiveLeaderboard.this.d = parse.get("player").getInt("total");
                                if (LiveLeaderboard.this.d == 0) {
                                    return;
                                }
                                long j = parse.get("player").getLong("score");
                                Iterator<JsonValue> iterator2 = parse.get("leaderboards").iterator2();
                                while (iterator2.hasNext()) {
                                    JsonValue next = iterator2.next();
                                    try {
                                        PlaceConfig placeConfig = new PlaceConfig(LiveLeaderboard.this, (byte) 0);
                                        placeConfig.nickname = next.getString("nickname");
                                        placeConfig.score = next.getLong("score");
                                        placeConfig.place = next.getInt("position");
                                        placeConfig.hasPfp = next.getBoolean("hasPfp", false);
                                        placeConfig.playerId = next.getString("playerid");
                                        LiveLeaderboard.this.e.add(placeConfig);
                                    } catch (Exception e) {
                                        String str = "failed to parse live leaderboards place result: " + next.toJson(JsonWriter.OutputType.json);
                                        LiveLeaderboard.f3315a.e(str, e);
                                        CrashHandler.report(str, e);
                                    }
                                }
                                LiveLeaderboard.this.t = new PlaceConfig(LiveLeaderboard.this, (byte) 0);
                                LiveLeaderboard.this.t.listItem = new ListItem(LiveLeaderboard.this, true);
                                LiveLeaderboard.this.t.listItem.setup(Game.i.authManager.getNickname(), 0L, 0, new TextureRegionDrawable(Game.i.authManager.getAvatar(64)));
                                LiveLeaderboard.this.g = new Label(Game.i.localeManager.i18n.get("live_leaderboard_previous_record") + ": " + ((Object) StringFormatter.commaSeparatedNumber(j)), Game.i.assetManager.getLabelStyle(21));
                                LiveLeaderboard.this.g.setAlignment(1);
                                LiveLeaderboard.this.g.setPosition(0.0f, 280.0f);
                                LiveLeaderboard.this.g.setSize(320.0f, 16.0f);
                                LiveLeaderboard.this.g.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                                LiveLeaderboard.this.r.addActor(LiveLeaderboard.this.g);
                                LiveLeaderboard.this.o = new Group();
                                LiveLeaderboard.this.o.setTransform(false);
                                LiveLeaderboard.this.o.setSize(30.0f, 6.0f);
                                LiveLeaderboard.this.o.setPosition(145.0f, 221.0f);
                                for (int i2 = 0; i2 < 3; i2++) {
                                    Image image = new Image(Game.i.assetManager.getDrawable("small-circle"));
                                    image.setSize(6.0f, 6.0f);
                                    image.setPosition(i2 * 12.0f, 0.0f);
                                    image.setColor(1.0f, 1.0f, 1.0f, 0.28f);
                                    LiveLeaderboard.this.o.addActor(image);
                                }
                                LiveLeaderboard.this.r.addActor(LiveLeaderboard.this.o);
                                LiveLeaderboard.this.q = new Group();
                                LiveLeaderboard.this.q.setTransform(false);
                                LiveLeaderboard.this.q.setSize(320.0f, 100.0f);
                                LiveLeaderboard.this.q.setPosition(0.0f, 22.0f);
                                LiveLeaderboard.this.r.addActor(LiveLeaderboard.this.q);
                                LiveLeaderboard.this.p = new Group();
                                LiveLeaderboard.this.p.setTransform(false);
                                LiveLeaderboard.this.p.setSize(30.0f, 6.0f);
                                LiveLeaderboard.this.p.setPosition(145.0f, 49.0f);
                                for (int i3 = 0; i3 < 3; i3++) {
                                    Image image2 = new Image(Game.i.assetManager.getDrawable("small-circle"));
                                    image2.setSize(6.0f, 6.0f);
                                    image2.setPosition(i3 * 12.0f, 0.0f);
                                    image2.setColor(1.0f, 1.0f, 1.0f, 0.28f);
                                    LiveLeaderboard.this.p.addActor(image2);
                                }
                                LiveLeaderboard.this.q.addActor(LiveLeaderboard.this.p);
                                LiveLeaderboard.this.n = new Image(Game.i.assetManager.getDrawable("gradient-radial-top"));
                                LiveLeaderboard.this.n.setSize(320.0f, 40.0f);
                                LiveLeaderboard.this.n.setColor(MaterialColor.LIGHT_BLUE.P800.r, MaterialColor.LIGHT_BLUE.P800.g, MaterialColor.LIGHT_BLUE.P800.f888b, 0.4f);
                                LiveLeaderboard.this.q.addActor(LiveLeaderboard.this.n);
                                Table table = new Table();
                                table.setPosition(0.0f, 12.0f);
                                table.setSize(320.0f, 18.0f);
                                LiveLeaderboard.this.q.addActor(table);
                                LiveLeaderboard.this.h = new Label("~ 123,456 / 567,890", Game.i.assetManager.getLabelStyle(24));
                                table.add((Table) LiveLeaderboard.this.h);
                                LiveLeaderboard.this.i = new Label("/ " + ((Object) StringFormatter.commaSeparatedNumber(LiveLeaderboard.this.d + 1)), Game.i.assetManager.getLabelStyle(21));
                                LiveLeaderboard.this.i.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                                table.add((Table) LiveLeaderboard.this.i).padLeft(5.0f);
                                Table table2 = new Table();
                                table2.setPosition(0.0f, -22.0f);
                                table2.setSize(320.0f, 32.0f);
                                LiveLeaderboard.this.q.addActor(table2);
                                if (HotKeyHintLabel.isEnabled()) {
                                    LiveLeaderboard.this.q.addActor(new HotKeyHintLabel(Game.i.settingsManager.getHotKey(SettingsManager.HotkeyAction.TOGGLE_LEADERBOARD), 300.0f, 0.0f));
                                }
                                LiveLeaderboard.this.j = new Label("Top 13%", Game.i.assetManager.getLabelStyle(21));
                                LiveLeaderboard.this.j.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                                table2.add((Table) LiveLeaderboard.this.j);
                                Image image3 = new Image(Game.i.assetManager.getDrawable("icon-star"));
                                image3.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                                table2.add((Table) image3).size(20.0f).padLeft(20.0f);
                                LiveLeaderboard.this.m = new Label("14,551", Game.i.assetManager.getLabelStyle(21));
                                LiveLeaderboard.this.m.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                                table2.add((Table) LiveLeaderboard.this.m).padLeft(5.0f);
                                Image image4 = new Image(Game.i.assetManager.getDrawable("icon-double-arrow-up"));
                                image4.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                                table2.add((Table) image4).size(20.0f).padLeft(5.0f);
                                LiveLeaderboard.this.l = new Label(Game.i.localeManager.i18n.get("time_limit_reached") + " (" + StringFormatter.timePassed(MathUtils.round(2700.0f), false, false) + ")", Game.i.assetManager.getLabelStyle(21));
                                LiveLeaderboard.this.l.setPosition(0.0f, -46.0f);
                                LiveLeaderboard.this.l.setSize(320.0f, 32.0f);
                                LiveLeaderboard.this.l.setAlignment(1);
                                LiveLeaderboard.this.l.setColor(MaterialColor.AMBER.P500.cpy().mul(1.0f, 1.0f, 1.0f, 0.56f));
                                LiveLeaderboard.this.l.setVisible(false);
                                LiveLeaderboard.this.q.addActor(LiveLeaderboard.this.l);
                                LiveLeaderboard.this.k = new Label("", Game.i.assetManager.getLabelStyle(24));
                                LiveLeaderboard.this.k.setPosition(0.0f, -50.0f);
                                LiveLeaderboard.this.k.setSize(320.0f, 32.0f);
                                LiveLeaderboard.this.k.setAlignment(1);
                                LiveLeaderboard.this.k.setColor(MaterialColor.LIGHT_GREEN.P500.cpy());
                                LiveLeaderboard.this.k.setVisible(false);
                                LiveLeaderboard.this.q.addActor(LiveLeaderboard.this.k);
                                gameSystemProvider2.events.getListeners(ScoreChange.class).add(scoreChange -> {
                                    Game.i.uiManager.runOnStageActOnce(LiveLeaderboard.this.z);
                                }).setDescription("LiveLeaderboard - rebuilds a list");
                                gameSystemProvider2._gameUi.sideMenu.addListener(new SideMenu.SideMenuListener.SideMenuListenerAdapter() { // from class: com.prineside.tdi2.ui.components.LiveLeaderboard.4.1
                                    private void a() {
                                        LiveLeaderboard.this.c();
                                    }

                                    @Override // com.prineside.tdi2.ui.actors.SideMenu.SideMenuListener.SideMenuListenerAdapter, com.prineside.tdi2.ui.actors.SideMenu.SideMenuListener
                                    public void offscreenStartingToChange() {
                                        a();
                                    }

                                    @Override // com.prineside.tdi2.ui.actors.SideMenu.SideMenuListener.SideMenuListenerAdapter, com.prineside.tdi2.ui.actors.SideMenu.SideMenuListener
                                    public void visibilityChanged() {
                                        a();
                                    }
                                });
                                LiveLeaderboard.this.c = Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.UI_LIVE_LEADERBOARDS_VISIBLE) == 1.0d;
                                LiveLeaderboard.this.d();
                            });
                        }
                    } catch (Exception e) {
                        LiveLeaderboard.f3315a.e("failed to load live leaderboards", e);
                    }
                }

                @Override // com.badlogic.gdx.Net.HttpResponseListener
                public void failed(Throwable th) {
                    LiveLeaderboard.f3315a.e("request failed", th);
                }

                @Override // com.badlogic.gdx.Net.HttpResponseListener
                public void cancelled() {
                    LiveLeaderboard.f3315a.e("request canceled", new Object[0]);
                }
            });
            return;
        }
        f3315a.i("cancel, level: " + gameSystemProvider.gameState.basicLevelName + ", is signed in: " + Game.i.authManager.isSignedIn() + ", difficulty: " + gameSystemProvider.gameState.difficultyMode.name(), new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (DifficultyMode.isEndless(this.y.gameState.difficultyMode) && this.k != null) {
            A.setLength(0);
            int statistic = (int) (2700.0d - this.y.statistics.getStatistic(StatisticsType.PT));
            int i = statistic;
            if (statistic < 0) {
                i = 0;
            }
            A.append("<@icon-clock>");
            A.append(StringFormatter.digestTime(i));
            this.k.setText(Game.i.assetManager.replaceRegionAliasesWithChars(A));
        }
    }

    public void finalFadeOut() {
        float f = Game.i.settingsManager.isUiAnimationsEnabled() ? 1.0f : 0.0f;
        this.f3316b.getTable().setTouchable(Touchable.disabled);
        this.f3316b.getTable().clearActions();
        this.f3316b.getTable().addAction(Actions.alpha(0.0f, f * 1.0f));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        boolean z = this.y._gameUi.sideMenu.isVisible() && !this.y._gameUi.sideMenu.isOffscreen();
        float f = 0.0f;
        if ((Game.i.uiManager.getScreenWidth() / Game.i.uiManager.getScreenHeight()) * (Game.i.settingsManager.getScaledViewportHeight() / 1080.0f) > 1.7d && z) {
            f = -580.0f;
        }
        int i = ((31 + ((int) f)) * 31) + (this.c ? 1 : 0);
        if (this.u != i) {
            this.u = i;
            if (this.c) {
                this.f.setVisible(true);
                this.r.clearActions();
                this.r.addAction(Actions.parallel(Actions.moveTo(f, 0.0f, 0.2f, Interpolation.exp5Out)));
                this.p.clearActions();
                this.p.addAction(Actions.fadeIn(0.1f));
                this.n.clearActions();
                this.n.addAction(Actions.alpha(0.4f, 0.1f));
                return;
            }
            this.r.clearActions();
            this.r.addAction(Actions.sequence(Actions.moveTo(f, 249.0f, 0.2f, Interpolation.exp5Out), Actions.run(() -> {
                this.f.setVisible(false);
            })));
            this.p.clearActions();
            this.p.addAction(Actions.fadeOut(0.1f));
            this.n.clearActions();
            this.n.addAction(Actions.fadeOut(0.1f));
        }
    }

    public void toggleVisible() {
        this.c = !this.c;
        Game.i.settingsManager.setCustomValue(SettingsManager.CustomValueType.UI_LIVE_LEADERBOARDS_VISIBLE, this.c ? 1.0d : 0.0d);
        d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        boolean z;
        long j;
        int i;
        int round;
        try {
            if (this.v == this.y.gameState.scoreWithEndlessTimeLimit) {
                return;
            }
            if (this.t == null) {
                f3315a.i("canceled rebuildList - no localPlayerPlaceConfig", new Object[0]);
                return;
            }
            long j2 = this.y.gameState.scoreWithEndlessTimeLimit;
            if (DifficultyMode.isEndless(this.y.gameState.difficultyMode)) {
                if (this.y.gameState.isMaxEndlessReplayTimeReached()) {
                    this.l.setVisible(true);
                    this.k.setVisible(false);
                } else {
                    this.l.setVisible(false);
                    this.k.setVisible(true);
                }
            }
            this.w.clear();
            int i2 = -1;
            for (int i3 = 0; i3 < this.e.size && j2 <= this.e.items[i3].score; i3++) {
                i2 = i3;
            }
            if (i2 == -1) {
                z = false;
                this.w.add(this.t);
                for (int i4 = 0; i4 < 4; i4++) {
                    if (this.e.size > i4) {
                        this.w.add(this.e.items[i4]);
                    }
                }
            } else {
                z = i2 >= 3 && this.e.size > 4;
                this.w.add(this.e.items[0]);
                if (this.e.size > 1) {
                    if (i2 == 0) {
                        this.w.add(this.t);
                    }
                    int i5 = i2 - 1;
                    int i6 = i5;
                    int i7 = i5 + 2;
                    if (i6 <= 0) {
                        int i8 = 3;
                        if (3 >= this.e.size) {
                            i8 = this.e.size - 1;
                        }
                        for (int i9 = 1; i9 <= i8; i9++) {
                            this.w.add(this.e.items[i9]);
                            if (i2 == i9) {
                                this.w.add(this.t);
                            }
                        }
                    } else {
                        if (i7 >= this.e.size) {
                            int i10 = this.e.size - 1;
                            i7 = i10;
                            i6 = i10 - 2;
                        }
                        if (i6 <= 0) {
                            i6 = 1;
                        }
                        for (int i11 = i6; i11 <= i7; i11++) {
                            this.w.add(this.e.items[i11]);
                            if (i2 == i11) {
                                this.w.add(this.t);
                            }
                        }
                    }
                } else {
                    this.w.add(this.t);
                }
            }
            int i12 = 1;
            if (i2 >= 0) {
                if (this.e.items[i2] == null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i13 = 0; i13 < this.e.items.length; i13++) {
                        PlaceConfig placeConfig = this.e.items[i13];
                        stringBuilder.append("- ").append(String.valueOf(i13)).append(": ").append(String.valueOf(placeConfig == null ? "null" : Integer.valueOf(placeConfig.place))).append(SequenceUtils.EOL);
                    }
                    throw new IllegalArgumentException("'standingBefore' index " + i2 + " out of " + this.e.items.length + " items in array of size " + this.e.size + " is null:\n" + ((Object) stringBuilder));
                }
                int i14 = this.e.items[i2].place;
                if (i14 < 200) {
                    i12 = i14 + 1;
                } else if (i14 == 200) {
                    if (this.e.size > i2 + 1) {
                        try {
                            i12 = this.e.items[i2 + 1].place;
                        } catch (Exception e) {
                            throw new IllegalArgumentException("failed to get first percentage place after 200: current place is 200, placeConfigs[" + (i2 + 1) + "] is null", e);
                        }
                    } else {
                        i12 = 100000000;
                    }
                } else {
                    i12 = i14 + 1000000;
                }
            }
            if (this.c) {
                this.x.clear();
                Array.ArrayIterator<Actor> it = this.f.getChildren().iterator();
                while (it.hasNext()) {
                    this.x.add((ListItem) it.next());
                }
                if (this.t == null) {
                    throw new IllegalStateException("localPlayerPlaceConfig is null");
                }
                if (this.t.listItem == null) {
                    throw new IllegalStateException("localPlayerPlaceConfig.listItem is null");
                }
                this.t.listItem.setup(null, j2, i12, null);
                float f = 150.0f;
                boolean z2 = false;
                for (int i15 = 0; i15 < this.w.size; i15++) {
                    if (i15 == 1 && z) {
                        f -= 24.0f;
                    }
                    PlaceConfig placeConfig2 = this.w.items[i15];
                    if (placeConfig2 == this.t) {
                        z2 = true;
                    }
                    if (placeConfig2.listItem == null) {
                        placeConfig2.listItem = this.s.obtain();
                        placeConfig2.listItem.p = placeConfig2;
                        placeConfig2.listItem.setPosition(0.0f, 128.0f);
                        placeConfig2.listItem.setColor(1.0f, 1.0f, 1.0f, 0.0f);
                        placeConfig2.listItem.setup(placeConfig2.nickname, placeConfig2.score, placeConfig2.place, placeConfig2.hasPfp ? new TextureRegionDrawable(Game.i.assetManager.loadWebTexture(Config.AVATAR_WEB_TEXTURES_URL + placeConfig2.playerId.toLowerCase(Locale.US) + "-32.png")) : null);
                    }
                    if (z2 && placeConfig2 != this.t) {
                        if (placeConfig2.place <= 200) {
                            placeConfig2.listItem.setup(null, -1L, placeConfig2.place + 1, null);
                        } else {
                            placeConfig2.listItem.setup(null, -1L, placeConfig2.place + 1000000, null);
                        }
                    }
                    placeConfig2.listItem.clearActions();
                    placeConfig2.listItem.addAction(Actions.parallel(Actions.fadeIn(0.3f), Actions.moveTo(0.0f, f, 0.3f)));
                    this.f.addActor(placeConfig2.listItem);
                    f -= 32.0f;
                    this.x.removeValue(placeConfig2.listItem, true);
                }
                for (int i16 = 0; i16 < this.x.size; i16++) {
                    ListItem listItem = this.x.items[i16];
                    if (listItem.p == this.t) {
                        throw new IllegalStateException("trying to remove localPlayerPlaceConfig");
                    }
                    listItem.clearActions();
                    listItem.addAction(Actions.sequence(Actions.parallel(Actions.moveTo(0.0f, (-32.0f) + (z ? 0 : 24), 0.3f), Actions.fadeOut(0.3f)), Actions.run(() -> {
                        listItem.remove();
                        listItem.p.listItem = null;
                        listItem.k.setDrawable(Game.i.assetManager.getDrawable("icon-user"));
                        this.s.free(listItem);
                    })));
                }
                this.x.clear();
            }
            if (z) {
                this.o.clearActions();
                this.o.addAction(Actions.fadeIn(0.3f));
            } else {
                this.o.clearActions();
                this.o.addAction(Actions.fadeOut(0.3f));
            }
            boolean z3 = false;
            if (i2 == -1) {
                round = 1;
            } else {
                PlaceConfig placeConfig3 = this.e.items[i2];
                if (placeConfig3.place < 200) {
                    round = placeConfig3.place + 1;
                } else {
                    z3 = true;
                    long j3 = placeConfig3.score;
                    int round2 = placeConfig3.place == 200 ? placeConfig3.place : MathUtils.round((placeConfig3.place / 1000000.0f) * 0.01f * this.d);
                    int i17 = round2;
                    if (round2 < 200) {
                        i17 = 200;
                    }
                    PlaceConfig placeConfig4 = this.e.size > i2 + 1 ? this.e.items[i2 + 1] : null;
                    PlaceConfig placeConfig5 = placeConfig4;
                    if (placeConfig4 == null) {
                        j = 0;
                        i = this.d;
                    } else {
                        j = placeConfig5.score;
                        int round3 = MathUtils.round((placeConfig5.place / 1000000.0f) * 0.01f * this.d);
                        i = round3;
                        if (round3 < 201) {
                            i = 201;
                        }
                    }
                    long j4 = j3 - j;
                    long j5 = j4;
                    if (j4 < 1) {
                        j5 = 1;
                    }
                    round = MathUtils.round((i17 - i) * (((float) (j2 - j)) / ((float) j5))) + i + 1;
                }
            }
            A.setLength(0);
            if (z3) {
                A.append("~");
            }
            A.append(StringFormatter.commaSeparatedNumber(round));
            this.h.setText(A);
            this.t.listItem.setZIndex(Tower.MAX_LEVEL);
            if (i2 == -1) {
                this.j.setText(Game.i.localeManager.i18n.get("leader"));
                this.m.setText("-");
            } else {
                A.setLength(0);
                A.append(Game.i.localeManager.i18n.get("top_leaderboard_top")).append(' ');
                if (i12 < 1000000) {
                    float f2 = (i12 / (this.d + 1.0f)) * 100.0f;
                    float f3 = f2;
                    if (f2 < 0.1f) {
                        f3 = 0.1f;
                    }
                    int floor = MathUtils.floor(f3 * 10.0f);
                    if (floor <= 9) {
                        A.append("0.").append(floor).append('%');
                    } else {
                        A.append(MathUtils.floor(f3)).append('%');
                    }
                } else {
                    A.append(i12 / 1000000).append('%');
                }
                this.j.setText(A);
                this.m.setText(StringFormatter.commaSeparatedNumber(this.e.items[i2].score - j2));
            }
            c();
        } catch (Exception e2) {
            f3315a.e("LiveLeaderboard#rebuildList failed", e2);
        }
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        Game.i.uiManager.removeLayer(this.f3316b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/LiveLeaderboard$PlaceConfig.class */
    public class PlaceConfig {
        public long score;
        public String nickname;
        public String playerId;
        public boolean hasPfp;
        public int place;
        public ListItem listItem;

        private PlaceConfig(LiveLeaderboard liveLeaderboard) {
        }

        /* synthetic */ PlaceConfig(LiveLeaderboard liveLeaderboard, byte b2) {
            this(liveLeaderboard);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/LiveLeaderboard$ListItem.class */
    public class ListItem extends Group {
        private Image k;
        private Label l;
        private Label m;
        private Label n;
        private boolean o;
        private PlaceConfig p;

        public ListItem(LiveLeaderboard liveLeaderboard, boolean z) {
            this.o = z;
            setTransform(false);
            setTouchable(Touchable.disabled);
            setSize(320.0f, 30.0f);
            Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
            image.setPosition(128.0f, 0.0f);
            image.setSize(64.0f, 30.0f);
            image.setColor(0.0f, 0.0f, 0.0f, 0.28f);
            addActor(image);
            Image image2 = new Image(Game.i.assetManager.getDrawable("gradient-right"));
            image2.setSize(128.0f, 30.0f);
            image2.setPosition(-32.0f, 0.0f);
            image2.setColor(MaterialColor.LIGHT_BLUE.P800.r, MaterialColor.LIGHT_BLUE.P800.g, MaterialColor.LIGHT_BLUE.P800.f888b, 0.4f);
            addActor(image2);
            Image image3 = new Image(Game.i.assetManager.getDrawable("gradient-left"));
            image3.setSize(128.0f, 30.0f);
            image3.setPosition(192.0f, 0.0f);
            image3.setColor(MaterialColor.LIGHT_BLUE.P800.r, MaterialColor.LIGHT_BLUE.P800.g, MaterialColor.LIGHT_BLUE.P800.f888b, 0.4f);
            addActor(image3);
            this.k = new Image(Game.i.assetManager.getDrawable("icon-user"));
            this.k.setPosition(98.0f, 0.0f);
            this.k.setSize(30.0f, 30.0f);
            this.k.setColor(0.0f, 0.0f, 0.0f, 0.28f);
            addActor(this.k);
            this.l = new LimitedWidthLabel("", 24, 21, 200.0f);
            this.l.setAlignment(16);
            this.l.setSize(118.0f, 30.0f);
            this.l.setPosition(-32.0f, 0.0f);
            addActor(this.l);
            this.m = new Label("", Game.i.assetManager.getLabelStyle(24));
            this.m.setSize(118.0f, 30.0f);
            this.m.setPosition(202.0f, 0.0f);
            addActor(this.m);
            this.n = new Label("", Game.i.assetManager.getLabelStyle(24));
            this.n.setSize(64.0f, 30.0f);
            this.n.setPosition(128.0f, 0.0f);
            this.n.setAlignment(1);
            addActor(this.n);
            if (z) {
                image2.setPosition(-64.0f, 0.0f);
                image2.setSize(160.0f, 30.0f);
                image2.setColor(MaterialColor.GREEN.P800.r, MaterialColor.GREEN.P800.g, MaterialColor.GREEN.P800.f888b, 0.78f);
                image3.setSize(160.0f, 30.0f);
                image3.setColor(MaterialColor.GREEN.P800.r, MaterialColor.GREEN.P800.g, MaterialColor.GREEN.P800.f888b, 0.78f);
            }
        }

        public void setup(String str, long j, int i, Drawable drawable) {
            if (str != null) {
                this.l.setText(str);
            }
            if (j >= 0) {
                this.m.setText(StringFormatter.commaSeparatedNumber(j));
            }
            if (i >= 1000000) {
                this.n.setText((i / 1000000) + "%");
            } else {
                this.n.setTextFromInt(i);
            }
            if (this.o) {
                this.n.setColor(Color.WHITE);
            } else if (i <= 200) {
                this.n.setColor(MaterialColor.AMBER.P300);
            } else {
                this.n.setColor(MaterialColor.LIGHT_BLUE.P300);
            }
            if (drawable != null) {
                this.k.setDrawable(drawable);
                this.k.setColor(1.0f, 1.0f, 1.0f, 1.0f);
            }
        }
    }
}
