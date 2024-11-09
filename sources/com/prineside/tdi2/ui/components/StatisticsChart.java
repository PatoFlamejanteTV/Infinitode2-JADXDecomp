package com.prineside.tdi2.ui.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.StringBuilder;
import com.badlogic.gdx.utils.Timer;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.events.game.BestReplayLoadFromServer;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.ReplayManager;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.ui.actors.ComplexButton;
import com.prineside.tdi2.ui.actors.HotKeyHintLabel;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.QuadActor;
import com.prineside.tdi2.ui.actors.SideMenu;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.QuadDrawable;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.TLog;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/StatisticsChart.class */
public class StatisticsChart implements Disposable {
    private TabType c;
    private Group d;
    private Group e;
    private Label f;
    private Group g;
    private boolean h;
    private boolean i;
    private GameSystemProvider n;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3405a = TLog.forClass(StatisticsChart.class);
    private static final StringBuilder o = new StringBuilder();

    /* renamed from: b, reason: collision with root package name */
    private final UiManager.UiLayer f3406b = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 104, "StatisticsChart");
    private Label[] j = new Label[5];
    private Label[] k = new Label[5];
    private Label[] l = new Label[5];
    private Image[] m = new Image[5];

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/StatisticsChart$TabType.class */
    public enum TabType {
        SCORE,
        COINS,
        INFO;

        public static final TabType[] values = values();
    }

    static /* synthetic */ boolean a(StatisticsChart statisticsChart, boolean z) {
        statisticsChart.i = z;
        return z;
    }

    static /* synthetic */ TLog a() {
        return f3405a;
    }

    static /* synthetic */ boolean a(StatisticsChart statisticsChart) {
        return b();
    }

    static /* synthetic */ boolean b(StatisticsChart statisticsChart) {
        return statisticsChart.i;
    }

    public StatisticsChart(final GameSystemProvider gameSystemProvider) {
        this.n = gameSystemProvider;
        Group group = new Group();
        group.setTransform(false);
        group.setTouchable(Touchable.childrenOnly);
        this.d = new Group();
        this.d.setTransform(false);
        this.d.setSize(87.0f, 96.0f);
        this.d.setPosition(64.0f, 178.0f);
        group.addActor(this.d);
        QuadActor quadActor = new QuadActor(new Color(522133503), new float[]{0.0f, 0.0f, 3.0f, 22.0f, 45.0f, 22.0f, 48.0f, 0.0f});
        quadActor.setPosition(17.0f, 74.0f);
        this.d.addActor(quadActor);
        this.d.addActor(new QuadActor(new Color(724249599), new float[]{6.0f, 3.0f, 0.0f, 78.0f, 87.0f, 80.0f, 80.0f, 0.0f}));
        Image image = new Image(Game.i.assetManager.getDrawable("icon-statistics"));
        image.setPosition(18.0f, 16.0f);
        image.setSize(48.0f, 48.0f);
        this.d.addActor(image);
        this.d.addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.components.StatisticsChart.1
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                StatisticsChart.this.setVisible(true);
                StatisticsChart.this.i = false;
            }
        });
        if (HotKeyHintLabel.isEnabled()) {
            this.d.addActor(new HotKeyHintLabel(Game.i.settingsManager.getHotKey(SettingsManager.HotkeyAction.TOGGLE_STATS_PANE), 18.0f, 54.0f));
        }
        this.e = new Group();
        this.e.setTransform(false);
        this.e.setTouchable(Touchable.childrenOnly);
        group.addActor(this.e);
        this.f3406b.getTable().add((Table) group).padLeft(824.0f).size(584.0f, 274.0f).expand().top().left();
        QuadActor quadActor2 = new QuadActor(new Color(505290495), new float[]{0.0f, 0.0f, 6.0f, 24.0f, 378.0f, 24.0f, 385.0f, 0.0f});
        quadActor2.setPosition(62.0f, 250.0f);
        this.e.addActor(quadActor2);
        QuadActor quadActor3 = new QuadActor(new Color(724249599), new float[]{9.0f, 0.0f, 0.0f, 205.0f, 514.0f, 201.0f, 505.0f, 5.0f});
        quadActor3.setPosition(0.0f, 48.0f);
        this.e.addActor(quadActor3);
        QuadActor quadActor4 = new QuadActor(new Color(0.0f, 0.0f, 0.0f, 0.14f), new float[]{2.0f, 0.0f, 0.0f, 36.0f, 480.0f, 36.0f, 480.0f, 0.0f});
        quadActor4.setTextureRegion(Game.i.assetManager.getTextureRegion("gradient-left"));
        quadActor4.setPosition(2.0f, 168.0f);
        this.e.addActor(quadActor4);
        QuadActor quadActor5 = new QuadActor(new Color(0.0f, 0.0f, 0.0f, 0.14f), new float[]{2.0f, 0.0f, 0.0f, 36.0f, 480.0f, 36.0f, 480.0f, 0.0f});
        quadActor5.setTextureRegion(Game.i.assetManager.getTextureRegion("gradient-left"));
        quadActor5.setPosition(5.0f, 96.0f);
        this.e.addActor(quadActor5);
        Table table = new Table();
        table.setPosition(286.0f, 8.0f);
        table.setSize(108.0f, 32.0f);
        this.e.addActor(table);
        table.add().expandX().fillX();
        Image image2 = new Image(Game.i.assetManager.getDrawable("icon-clock"));
        image2.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        table.add((Table) image2).size(32.0f);
        this.f = new Label("", Game.i.assetManager.getLabelStyle(24));
        this.f.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        table.add((Table) this.f).height(32.0f).padLeft(6.0f);
        Group group2 = new Group();
        group2.setPosition(36.0f, 60.0f);
        group2.setTransform(false);
        this.e.addActor(group2);
        for (int i = 0; i < 5; i++) {
            Table table2 = new Table();
            table2.setSize(445.0f, 36.0f);
            table2.setPosition(0.0f, 144.0f - (i * 36.0f));
            group2.addActor(table2);
            this.j[i] = new Label("Title", Game.i.assetManager.getLabelStyle(21));
            table2.add((Table) this.j[i]).height(36.0f);
            table2.add().expandX().fillX();
            this.l[i] = new Label("1,23K", Game.i.assetManager.getLabelStyle(21));
            table2.add((Table) this.l[i]).height(36.0f);
            this.m[i] = new Image(Game.i.assetManager.getDrawable("icon-triangle-top"));
            table2.add((Table) this.m[i]).size(21.0f).padLeft(3.0f);
            this.k[i] = new Label("999K", Game.i.assetManager.getLabelStyle(24));
            this.k[i].setAlignment(16);
            table2.add((Table) this.k[i]).height(36.0f).minWidth(75.0f).padLeft(16.0f);
        }
        ComplexButton complexButton = new ComplexButton("", Game.i.assetManager.getLabelStyle(21), () -> {
            setVisible(false);
            this.i = false;
        });
        complexButton.setBackground(new QuadDrawable(new QuadActor(Color.WHITE, new float[]{5.0f, 4.0f, 0.0f, 43.0f, 88.0f, 44.0f, 86.0f, 0.0f})), 23.0f, 13.0f, 88.0f, 44.0f);
        complexButton.setBackgroundColors(new Color(508719359), new Color(406936063), new Color(627345663), Color.WHITE);
        complexButton.setIconPositioned(Game.i.assetManager.getDrawable("icon-triangle-top"), 53.0f, 20.0f, 32.0f, 32.0f);
        complexButton.setSize(120.0f, 60.0f);
        complexButton.setPosition(394.0f, -13.0f);
        this.e.addActor(complexButton);
        if (HotKeyHintLabel.isEnabled()) {
            complexButton.addActor(new HotKeyHintLabel(Game.i.settingsManager.getHotKey(SettingsManager.HotkeyAction.TOGGLE_STATS_PANE), 132.0f, 29.0f));
        }
        this.g = new Group();
        this.g.setTransform(false);
        this.g.setSize(74.0f, 206.0f);
        this.g.setPosition(510.0f, 47.0f);
        this.e.addActor(this.g);
        if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.UI_STATISTICS_CHART_VISIBLE) == 0.0d) {
            this.e.setPosition(0.0f, 276.0f);
            this.e.setVisible(false);
        } else {
            this.h = true;
        }
        setTabType(TabType.SCORE);
        update();
        gameSystemProvider._gameUi.sideMenu.addListener(new SideMenu.SideMenuListener.SideMenuListenerAdapter() { // from class: com.prineside.tdi2.ui.components.StatisticsChart.2
            private final Timer.Task c = new Timer.Task() { // from class: com.prineside.tdi2.ui.components.StatisticsChart.2.1
                /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
                    jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: com.prineside.tdi2.ui.components.StatisticsChart.a(com.prineside.tdi2.ui.components.StatisticsChart):boolean
                    	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:74)
                    	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:49)
                    Caused by: java.lang.IndexOutOfBoundsException: Index: 0
                    	at java.base/java.util.Collections$EmptyList.get(Unknown Source)
                    	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:103)
                    	at jadx.core.dex.visitors.MarkMethodsForInline.isSyntheticAccessPattern(MarkMethodsForInline.java:117)
                    	at jadx.core.dex.visitors.MarkMethodsForInline.inlineMth(MarkMethodsForInline.java:86)
                    	at jadx.core.dex.visitors.MarkMethodsForInline.process(MarkMethodsForInline.java:53)
                    	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:63)
                    	... 1 more
                    */
                @Override // com.badlogic.gdx.utils.Timer.Task, java.lang.Runnable
                public void run() {
                    /*
                        r7 = this;
                        r0 = r7
                        com.prineside.tdi2.ui.components.StatisticsChart$2 r0 = com.prineside.tdi2.ui.components.StatisticsChart.AnonymousClass2.this     // Catch: java.lang.Exception -> L9d
                        com.prineside.tdi2.GameSystemProvider r0 = r7     // Catch: java.lang.Exception -> L9d
                        com.prineside.tdi2.systems.GameUiSystem r0 = r0._gameUi     // Catch: java.lang.Exception -> L9d
                        if (r0 == 0) goto L1d
                        r0 = r7
                        com.prineside.tdi2.ui.components.StatisticsChart$2 r0 = com.prineside.tdi2.ui.components.StatisticsChart.AnonymousClass2.this     // Catch: java.lang.Exception -> L9d
                        com.prineside.tdi2.GameSystemProvider r0 = r7     // Catch: java.lang.Exception -> L9d
                        com.prineside.tdi2.systems.GameUiSystem r0 = r0._gameUi     // Catch: java.lang.Exception -> L9d
                        com.prineside.tdi2.ui.actors.SideMenu r0 = r0.sideMenu     // Catch: java.lang.Exception -> L9d
                        if (r0 != 0) goto L2a
                    L1d:
                        com.prineside.tdi2.utils.logging.TLog r0 = com.prineside.tdi2.ui.components.StatisticsChart.a()     // Catch: java.lang.Exception -> L9d
                        java.lang.String r1 = "skip delayedHandler - already disposed?"
                        r2 = 0
                        java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.Exception -> L9d
                        r0.w(r1, r2)     // Catch: java.lang.Exception -> L9d
                        return
                    L2a:
                        r0 = r7
                        com.prineside.tdi2.ui.components.StatisticsChart$2 r0 = com.prineside.tdi2.ui.components.StatisticsChart.AnonymousClass2.this     // Catch: java.lang.Exception -> L9d
                        com.prineside.tdi2.ui.components.StatisticsChart r0 = com.prineside.tdi2.ui.components.StatisticsChart.this     // Catch: java.lang.Exception -> L9d
                        boolean r0 = com.prineside.tdi2.ui.components.StatisticsChart.a(r0)     // Catch: java.lang.Exception -> L9d
                        if (r0 != 0) goto L9c
                        r0 = r7
                        com.prineside.tdi2.ui.components.StatisticsChart$2 r0 = com.prineside.tdi2.ui.components.StatisticsChart.AnonymousClass2.this     // Catch: java.lang.Exception -> L9d
                        com.prineside.tdi2.GameSystemProvider r0 = r7     // Catch: java.lang.Exception -> L9d
                        com.prineside.tdi2.systems.GameUiSystem r0 = r0._gameUi     // Catch: java.lang.Exception -> L9d
                        com.prineside.tdi2.ui.actors.SideMenu r0 = r0.sideMenu     // Catch: java.lang.Exception -> L9d
                        boolean r0 = r0.isOffscreen()     // Catch: java.lang.Exception -> L9d
                        if (r0 != 0) goto L5d
                        r0 = r7
                        com.prineside.tdi2.ui.components.StatisticsChart$2 r0 = com.prineside.tdi2.ui.components.StatisticsChart.AnonymousClass2.this     // Catch: java.lang.Exception -> L9d
                        com.prineside.tdi2.GameSystemProvider r0 = r7     // Catch: java.lang.Exception -> L9d
                        com.prineside.tdi2.systems.GameUiSystem r0 = r0._gameUi     // Catch: java.lang.Exception -> L9d
                        com.prineside.tdi2.ui.actors.SideMenu r0 = r0.sideMenu     // Catch: java.lang.Exception -> L9d
                        boolean r0 = r0.isVisible()     // Catch: java.lang.Exception -> L9d
                        if (r0 != 0) goto L78
                    L5d:
                        r0 = r7
                        com.prineside.tdi2.ui.components.StatisticsChart$2 r0 = com.prineside.tdi2.ui.components.StatisticsChart.AnonymousClass2.this     // Catch: java.lang.Exception -> L9d
                        com.prineside.tdi2.ui.components.StatisticsChart r0 = com.prineside.tdi2.ui.components.StatisticsChart.this     // Catch: java.lang.Exception -> L9d
                        boolean r0 = com.prineside.tdi2.ui.components.StatisticsChart.b(r0)     // Catch: java.lang.Exception -> L9d
                        if (r0 == 0) goto L9c
                        r0 = r7
                        com.prineside.tdi2.ui.components.StatisticsChart$2 r0 = com.prineside.tdi2.ui.components.StatisticsChart.AnonymousClass2.this     // Catch: java.lang.Exception -> L9d
                        com.prineside.tdi2.ui.components.StatisticsChart r0 = com.prineside.tdi2.ui.components.StatisticsChart.this     // Catch: java.lang.Exception -> L9d
                        r1 = 1
                        r0.setVisible(r1)     // Catch: java.lang.Exception -> L9d
                        goto Lae
                    L78:
                        r0 = r7
                        com.prineside.tdi2.ui.components.StatisticsChart$2 r0 = com.prineside.tdi2.ui.components.StatisticsChart.AnonymousClass2.this     // Catch: java.lang.Exception -> L9d
                        com.prineside.tdi2.ui.components.StatisticsChart r0 = com.prineside.tdi2.ui.components.StatisticsChart.this     // Catch: java.lang.Exception -> L9d
                        boolean r0 = r0.isVisible()     // Catch: java.lang.Exception -> L9d
                        if (r0 == 0) goto L9c
                        r0 = r7
                        com.prineside.tdi2.ui.components.StatisticsChart$2 r0 = com.prineside.tdi2.ui.components.StatisticsChart.AnonymousClass2.this     // Catch: java.lang.Exception -> L9d
                        com.prineside.tdi2.ui.components.StatisticsChart r0 = com.prineside.tdi2.ui.components.StatisticsChart.this     // Catch: java.lang.Exception -> L9d
                        r1 = 0
                        r0.setVisible(r1)     // Catch: java.lang.Exception -> L9d
                        r0 = r7
                        com.prineside.tdi2.ui.components.StatisticsChart$2 r0 = com.prineside.tdi2.ui.components.StatisticsChart.AnonymousClass2.this     // Catch: java.lang.Exception -> L9d
                        com.prineside.tdi2.ui.components.StatisticsChart r0 = com.prineside.tdi2.ui.components.StatisticsChart.this     // Catch: java.lang.Exception -> L9d
                        r1 = 1
                        boolean r0 = com.prineside.tdi2.ui.components.StatisticsChart.a(r0, r1)     // Catch: java.lang.Exception -> L9d
                    L9c:
                        return
                    L9d:
                        r8 = move-exception
                        com.prineside.tdi2.utils.logging.TLog r0 = com.prineside.tdi2.ui.components.StatisticsChart.a()
                        java.lang.String r1 = "delayedHandler failed"
                        r2 = 1
                        java.lang.Object[] r2 = new java.lang.Object[r2]
                        r3 = r2
                        r4 = 0
                        r5 = r8
                        r3[r4] = r5
                        r0.w(r1, r2)
                    Lae:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.ui.components.StatisticsChart.AnonymousClass2.AnonymousClass1.run():void");
                }
            };

            @Override // com.prineside.tdi2.ui.actors.SideMenu.SideMenuListener.SideMenuListenerAdapter, com.prineside.tdi2.ui.actors.SideMenu.SideMenuListener
            public void offscreenStartingToChange() {
                if (!this.c.isScheduled()) {
                    Timer.schedule(this.c, 0.05f);
                }
            }

            @Override // com.prineside.tdi2.ui.actors.SideMenu.SideMenuListener.SideMenuListenerAdapter, com.prineside.tdi2.ui.actors.SideMenu.SideMenuListener
            public void visibilityChanged() {
                if (!this.c.isScheduled()) {
                    Timer.schedule(this.c, 0.05f);
                }
            }
        });
        gameSystemProvider.events.getListeners(BestReplayLoadFromServer.class).add(bestReplayLoadFromServer -> {
            this.c = null;
            if (gameSystemProvider.statistics != null) {
                setTabType(TabType.SCORE);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b() {
        return (((float) Game.i.settingsManager.getScaledViewportHeight()) / 1080.0f) * (((float) Game.i.uiManager.getScreenWidth()) / (((float) Game.i.uiManager.getScreenHeight()) + 1.0f)) >= 2.25f;
    }

    public boolean isVisible() {
        return this.h;
    }

    public void setVisible(boolean z) {
        Game.i.settingsManager.setCustomValue(SettingsManager.CustomValueType.UI_STATISTICS_CHART_VISIBLE, z ? 1.0d : 0.0d);
        float f = Game.i.settingsManager.isUiAnimationsEnabled() ? 1.0f : 0.0f;
        if (z) {
            this.e.setVisible(true);
            this.e.clearActions();
            this.e.addAction(Actions.moveTo(0.0f, 0.0f, 0.15f * f));
            this.d.clearActions();
            this.d.addAction(Actions.moveTo(64.0f, 274.0f, 0.15f * f));
            if (!b()) {
                this.n._gameUi.sideMenu.setOffscreen(true);
            }
        } else {
            this.e.clearActions();
            this.e.addAction(Actions.sequence(Actions.moveTo(0.0f, 276.0f, 0.15f * f), Actions.hide()));
            this.d.clearActions();
            this.d.addAction(Actions.moveTo(64.0f, 178.0f, 0.15f * f));
        }
        this.h = z;
    }

    public void setTabType(TabType tabType) {
        QuadActor quadActor;
        Image image;
        for (int i = 0; i < 5; i++) {
            this.j[i].setColor(Color.WHITE);
        }
        if (this.c != tabType) {
            switch (tabType) {
                case SCORE:
                    this.j[0].setText(Game.i.localeManager.i18n.get("stats_chart_score"));
                    this.j[0].setStyle(Game.i.assetManager.getLabelStyle(24));
                    this.j[1].setText(Game.i.localeManager.i18n.get("stats_chart_killed_enemies"));
                    this.j[2].setText(Game.i.localeManager.i18n.get("stats_chart_mining"));
                    this.j[3].setText(Game.i.localeManager.i18n.get("stats_chart_wave_calls"));
                    this.j[4].setText(Game.i.localeManager.i18n.get("stats_chart_waves_cleared"));
                    break;
                case COINS:
                    this.j[0].setText(Game.i.localeManager.i18n.get("stats_chart_coins"));
                    this.j[0].setStyle(Game.i.assetManager.getLabelStyle(24));
                    this.j[1].setText(Game.i.localeManager.i18n.get("stats_chart_killed_enemies"));
                    this.j[2].setText(Game.i.localeManager.i18n.get("stats_chart_bounties"));
                    this.j[3].setText(Game.i.localeManager.i18n.get("stats_chart_wave_calls"));
                    this.j[4].setText(Game.i.localeManager.i18n.get("stats_chart_other"));
                    break;
                case INFO:
                    ReplayManager.ReplayRecord bestReplay = this.n.statistics.getBestReplay();
                    this.j[0].setText(Game.i.localeManager.i18n.get("stats_chart_comparison_hint"));
                    this.j[0].setStyle(Game.i.assetManager.getLabelStyle(21));
                    if (bestReplay != null) {
                        this.j[1].setText(new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.US).format(new Date(bestReplay.startTimestamp)));
                        this.j[1].setColor(1.0f, 1.0f, 1.0f, 0.56f);
                    }
                    this.j[2].setText(Game.i.localeManager.i18n.get("stats_chart_waves"));
                    this.j[3].setText(Game.i.localeManager.i18n.get("stats_chart_score"));
                    this.j[4].setText(Game.i.localeManager.i18n.get("stats_chart_playing_time"));
                    break;
            }
            this.g.clear();
            ReplayManager.ReplayRecord bestReplay2 = this.n.statistics.getBestReplay();
            for (int i2 = 0; i2 < 3; i2++) {
                final TabType tabType2 = TabType.values[i2];
                if (bestReplay2 != null || tabType2 != TabType.INFO) {
                    if (tabType == tabType2) {
                        QuadActor quadActor2 = new QuadActor(new Color(724249599), new float[]{0.0f, 0.0f, 0.0f, 60.0f, 74.0f, 60.0f, 72.0f, 0.0f});
                        quadActor = quadActor2;
                        quadActor2.setPosition(((-i2) * 2.0f) - 8.0f, 142.0f - (i2 * 68.0f));
                    } else {
                        QuadActor quadActor3 = new QuadActor(new Color(0.0f, 0.0f, 0.0f, 0.21f), new float[]{0.0f, 0.0f, 2.0f, 60.0f, 64.0f, 60.0f, 62.0f, 0.0f});
                        quadActor = quadActor3;
                        quadActor3.setPosition(10.0f - (i2 * 2.0f), 142.0f - (i2 * 68.0f));
                    }
                    this.g.addActor(quadActor);
                    quadActor.setTouchable(Touchable.enabled);
                    quadActor.addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.components.StatisticsChart.3
                        @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                        public void clicked(InputEvent inputEvent, float f, float f2) {
                            StatisticsChart.this.setTabType(tabType2);
                        }
                    });
                    switch (tabType2) {
                        case SCORE:
                            image = new Image(Game.i.assetManager.getDrawable("icon-star"));
                            break;
                        case COINS:
                            image = new Image(Game.i.assetManager.getDrawable("icon-coin"));
                            break;
                        case INFO:
                            image = new Image(Game.i.assetManager.getDrawable("icon-info"));
                            break;
                        default:
                            image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                            break;
                    }
                    image.setSize(48.0f, 48.0f);
                    if (tabType == tabType2) {
                        image.setPosition((18.0f - (i2 * 2.0f)) - 10.0f, 148.0f - (i2 * 68.0f));
                    } else {
                        image.setPosition(18.0f - (i2 * 2.0f), 148.0f - (i2 * 68.0f));
                        image.setColor(new Color(508719359));
                    }
                    image.setTouchable(Touchable.disabled);
                    this.g.addActor(image);
                }
            }
            this.c = tabType;
        }
        update();
    }

    public void draw(float f) {
        update();
    }

    private void a(int i, CharSequence charSequence, boolean z) {
        this.l[4].setVisible(true);
        this.m[4].setVisible(true);
        if (z) {
            this.l[4].setColor(MaterialColor.LIGHT_GREEN.P500);
            this.m[4].setColor(MaterialColor.LIGHT_GREEN.P500);
            this.m[4].setDrawable(Game.i.assetManager.getDrawable("icon-triangle-top"));
        } else {
            this.l[4].setColor(MaterialColor.ORANGE.P500);
            this.m[4].setColor(MaterialColor.ORANGE.P500);
            this.m[4].setDrawable(Game.i.assetManager.getDrawable("icon-triangle-bottom"));
        }
        this.l[4].setText(charSequence);
    }

    private void a(int i, long j) {
        if (j != 0) {
            this.l[i].setVisible(true);
            this.m[i].setVisible(true);
            if (j > 0) {
                this.l[i].setColor(MaterialColor.LIGHT_GREEN.P500);
                this.m[i].setColor(MaterialColor.LIGHT_GREEN.P500);
                this.m[i].setDrawable(Game.i.assetManager.getDrawable("icon-triangle-top"));
            } else {
                this.l[i].setColor(MaterialColor.ORANGE.P500);
                this.m[i].setColor(MaterialColor.ORANGE.P500);
                this.m[i].setDrawable(Game.i.assetManager.getDrawable("icon-triangle-bottom"));
            }
            this.l[i].setText(StringFormatter.compactNumber(j, false));
        }
    }

    public void update() {
        ReplayManager.ReplayRecord bestReplay;
        int i;
        int i2;
        if (this.e.isVisible()) {
            ReplayManager.ReplayRecord.ChartFrames currentReplayChart = this.n.statistics.getCurrentReplayChart();
            for (int i3 = 0; i3 < 5; i3++) {
                this.l[i3].setVisible(false);
                this.m[i3].setVisible(false);
            }
            if (this.c == TabType.SCORE) {
                ReplayManager.ReplayRecord.ChartFrames.FrameValues currentReplayChartValues = this.n.statistics.getCurrentReplayChartValues();
                long j = currentReplayChartValues.sKilledEnemies + currentReplayChartValues.sMining + currentReplayChartValues.sWaveCalls + currentReplayChartValues.sWavesCleared;
                for (int i4 = 0; i4 < 5; i4++) {
                    this.k[i4].setVisible(true);
                }
                this.k[0].setText(StringFormatter.compactNumber(j, false));
                this.k[1].setText(StringFormatter.compactNumber(currentReplayChartValues.sKilledEnemies, false));
                this.k[2].setText(StringFormatter.compactNumber(currentReplayChartValues.sMining, false));
                this.k[3].setText(StringFormatter.compactNumber(currentReplayChartValues.sWaveCalls, false));
                this.k[4].setText(StringFormatter.compactNumber(currentReplayChartValues.sWavesCleared, false));
                ReplayManager.ReplayRecord bestReplay2 = this.n.statistics.getBestReplay();
                if (bestReplay2 != null && (i2 = currentReplayChart.frames.size - 1) >= 0) {
                    ReplayManager.ReplayRecord.ChartFrames chartFrames = bestReplay2.chartFrames;
                    ReplayManager.ReplayRecord.ChartFrames.FrameValues frameValues = currentReplayChart.frames.items[i2];
                    ReplayManager.ReplayRecord.ChartFrames.FrameValues frameValues2 = chartFrames.frames.size > i2 ? chartFrames.frames.items[i2] : chartFrames.frames.items[chartFrames.frames.size - 1];
                    a(0, (((frameValues.sKilledEnemies + frameValues.sMining) + frameValues.sWaveCalls) + frameValues.sWavesCleared) - (((frameValues2.sKilledEnemies + frameValues2.sMining) + frameValues2.sWaveCalls) + frameValues2.sWavesCleared));
                    a(1, frameValues.sKilledEnemies - frameValues2.sKilledEnemies);
                    a(2, frameValues.sMining - frameValues2.sMining);
                    a(3, frameValues.sWaveCalls - frameValues2.sWaveCalls);
                    a(4, frameValues.sWavesCleared - frameValues2.sWavesCleared);
                }
            } else if (this.c == TabType.COINS) {
                ReplayManager.ReplayRecord.ChartFrames.FrameValues currentReplayChartValues2 = this.n.statistics.getCurrentReplayChartValues();
                int i5 = currentReplayChartValues2.cBounties + currentReplayChartValues2.cKilledEnemies + currentReplayChartValues2.cOther + currentReplayChartValues2.cWaveCalls;
                for (int i6 = 0; i6 < 5; i6++) {
                    this.k[i6].setVisible(true);
                }
                this.k[0].setText(StringFormatter.compactNumber(i5, false));
                this.k[1].setText(StringFormatter.compactNumber(currentReplayChartValues2.cKilledEnemies, false));
                this.k[2].setText(StringFormatter.compactNumber(currentReplayChartValues2.cBounties, false));
                this.k[3].setText(StringFormatter.compactNumber(currentReplayChartValues2.cWaveCalls, false));
                this.k[4].setText(StringFormatter.compactNumber(currentReplayChartValues2.cOther, false));
                ReplayManager.ReplayRecord bestReplay3 = this.n.statistics.getBestReplay();
                if (bestReplay3 != null && (i = currentReplayChart.frames.size - 1) >= 0) {
                    ReplayManager.ReplayRecord.ChartFrames chartFrames2 = bestReplay3.chartFrames;
                    ReplayManager.ReplayRecord.ChartFrames.FrameValues frameValues3 = currentReplayChart.frames.items[i];
                    ReplayManager.ReplayRecord.ChartFrames.FrameValues frameValues4 = chartFrames2.frames.size > i ? chartFrames2.frames.items[i] : chartFrames2.frames.items[chartFrames2.frames.size - 1];
                    a(0, (((frameValues3.cKilledEnemies + frameValues3.cWaveCalls) + frameValues3.cBounties) + frameValues3.cOther) - (((frameValues4.cKilledEnemies + frameValues4.cWaveCalls) + frameValues4.cBounties) + frameValues4.cOther));
                    a(1, frameValues3.cKilledEnemies - frameValues4.cKilledEnemies);
                    a(2, frameValues3.cBounties - frameValues4.cBounties);
                    a(3, frameValues3.cWaveCalls - frameValues4.cWaveCalls);
                    a(4, frameValues3.cOther - frameValues4.cOther);
                }
            } else if (this.c == TabType.INFO && (bestReplay = this.n.statistics.getBestReplay()) != null) {
                this.j[2].setText(Game.i.localeManager.i18n.get("stats_chart_waves"));
                this.j[3].setText(Game.i.localeManager.i18n.get("stats_chart_score"));
                this.j[4].setText(Game.i.localeManager.i18n.get("stats_chart_playing_time"));
                int tickRate = (int) ((bestReplay.chartFrames.frames.size * Config.REPLAY_CHARTS_INTERVAL) / this.n.gameValue.getTickRate());
                this.k[2].setTextFromInt(bestReplay.defeatedWaves);
                this.k[3].setText(StringFormatter.commaSeparatedNumber(bestReplay.score));
                this.k[4].setText(StringFormatter.digestTime(tickRate));
                this.k[0].setVisible(false);
                this.k[1].setVisible(false);
                a(2, this.n.wave.getCompletedWavesCount() - bestReplay.defeatedWaves);
                a(3, this.n.gameState.getScore() - bestReplay.score);
                o.setLength(0);
                int tickRate2 = ((int) (this.n.statistics.replayChartFrameCounter / this.n.gameValue.getTickRate())) - tickRate;
                if (tickRate2 >= 0) {
                    o.append(StringFormatter.digestTime(tickRate2));
                    a(4, o, true);
                } else {
                    o.append('-').append(StringFormatter.digestTime(-tickRate2));
                    a(4, o, false);
                }
            }
            this.f.setText(StringFormatter.digestTimeWithZeroHours(this.n.statistics.replayChartFrameCounter / this.n.gameValue.getTickRate(), false));
        }
    }

    public void finalFadeOut() {
        float f = Game.i.settingsManager.isUiAnimationsEnabled() ? 1.0f : 0.0f;
        this.f3406b.getTable().setTouchable(Touchable.disabled);
        this.f3406b.getTable().clearActions();
        this.f3406b.getTable().addAction(Actions.alpha(0.0f, f * 1.0f));
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        Game.i.uiManager.removeLayer(this.f3406b);
    }
}
