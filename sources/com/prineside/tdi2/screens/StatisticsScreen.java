package com.prineside.tdi2.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntFloatMap;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.IssuedItems;
import com.prineside.tdi2.Screen;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.enums.AchievementType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.MinerType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.ReplayManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.ScrollPane;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.shapes.PieChart;
import com.prineside.tdi2.systems.GameStateSystem;
import com.prineside.tdi2.ui.actors.ComplexButton;
import com.prineside.tdi2.ui.actors.FancyButton;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.LabelButton;
import com.prineside.tdi2.ui.actors.LimitedWidthLabel;
import com.prineside.tdi2.ui.actors.PieChartActor;
import com.prineside.tdi2.ui.actors.QuadActor;
import com.prineside.tdi2.ui.actors.RectButton;
import com.prineside.tdi2.ui.shared.BackButton;
import com.prineside.tdi2.ui.shared.Notifications;
import com.prineside.tdi2.ui.shared.ScreenTitle;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.QuadDrawable;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.UiUtils;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/StatisticsScreen.class */
public class StatisticsScreen extends Screen {
    private ScrollPane c;
    private RectButton d;
    private RectButton e;
    private RectButton f;
    private RectButton g;
    private RectButton h;
    private Table i;
    private Image j;
    private Label.LabelStyle m;
    private Drawable n;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2838a = TLog.forClass(StatisticsScreen.class);
    private static final StringBuilder o = new StringBuilder();

    /* renamed from: b, reason: collision with root package name */
    private final UiManager.UiLayer f2839b = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 100, "StatisticsScreen main");
    private MainTabType k = MainTabType.ALL_TIME;
    private ReplayManager.ReplayRecord l = null;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/screens/StatisticsScreen$MainTabType.class */
    public enum MainTabType {
        ALL_TIME,
        BY_GAME,
        MAX_ONE_GAME,
        ACHIEVEMENTS,
        EARNINGS
    }

    public StatisticsScreen() {
        Game.i.musicManager.continuePlayingMenuMusicTrack();
        Game.i.uiManager.hideAllComponents();
        Game.i.uiManager.setAsInputHandler();
        ScreenTitle.i().setIcon(Game.i.assetManager.getDrawable("icon-statistics")).setText(Game.i.localeManager.i18n.get("statistics_title")).setVisible(true);
        BackButton.i().setVisible(true).setText(null).setClickHandler(() -> {
            Game.i.screenManager.goToMainMenu();
        });
        this.m = new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE);
        this.n = Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(new Color(0.0f, 0.0f, 0.0f, 0.28f));
        Table table = this.f2839b.getTable();
        Table table2 = new Table();
        table.add(table2).padLeft(40.0f).padRight(40.0f).padTop(160.0f).width(320.0f).minHeight(100.0f).expandY().fillY();
        this.d = new RectButton(Game.i.localeManager.i18n.get("statistics_subtitle_all_time"), new Label.LabelStyle(Game.i.assetManager.getFont(30), Color.WHITE), () -> {
            this.k = MainTabType.ALL_TIME;
            a();
        });
        this.d.setSize(320.0f, 64.0f);
        this.d.setBackground(new QuadDrawable(new QuadActor(new float[]{0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f}, Color.WHITE)), 0.0f, 0.0f, 320.0f, 64.0f);
        this.d.setBackgroundColors(new Color(0.0f, 0.0f, 0.0f, 0.0f), new Color(0.0f, 0.0f, 0.0f, 0.07f), new Color(1.0f, 1.0f, 1.0f, 0.14f), new Color(0.0f, 0.0f, 0.0f, 0.28f));
        RectButton rectButton = this.d;
        Color color = MaterialColor.LIGHT_BLUE.P500;
        rectButton.setIconLabelColors(color, color, MaterialColor.LIGHT_BLUE.P500, Color.WHITE);
        table2.add((Table) this.d).top().row();
        this.e = new RectButton(Game.i.localeManager.i18n.get("statistics_subtitle_by_game"), new Label.LabelStyle(Game.i.assetManager.getFont(30), Color.WHITE), () -> {
            this.l = null;
            this.k = MainTabType.BY_GAME;
            a();
        });
        this.e.setSize(320.0f, 64.0f);
        this.e.setBackground(new QuadDrawable(new QuadActor(new float[]{0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f}, Color.WHITE)), 0.0f, 0.0f, 320.0f, 64.0f);
        this.e.setBackgroundColors(new Color(0.0f, 0.0f, 0.0f, 0.0f), new Color(0.0f, 0.0f, 0.0f, 0.07f), new Color(1.0f, 1.0f, 1.0f, 0.14f), new Color(0.0f, 0.0f, 0.0f, 0.28f));
        RectButton rectButton2 = this.e;
        Color color2 = MaterialColor.LIGHT_BLUE.P500;
        rectButton2.setIconLabelColors(color2, color2, MaterialColor.LIGHT_BLUE.P500, Color.WHITE);
        table2.add((Table) this.e).top().padTop(4.0f).row();
        this.f = new RectButton(Game.i.localeManager.i18n.get("statistics_subtitle_max_per_game"), new Label.LabelStyle(Game.i.assetManager.getFont(30), Color.WHITE), () -> {
            this.k = MainTabType.MAX_ONE_GAME;
            a();
        });
        this.f.setSize(320.0f, 64.0f);
        this.f.setBackground(new QuadDrawable(new QuadActor(new float[]{0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f}, Color.WHITE)), 0.0f, 0.0f, 320.0f, 64.0f);
        this.f.setBackgroundColors(new Color(0.0f, 0.0f, 0.0f, 0.0f), new Color(0.0f, 0.0f, 0.0f, 0.07f), new Color(1.0f, 1.0f, 1.0f, 0.14f), new Color(0.0f, 0.0f, 0.0f, 0.28f));
        RectButton rectButton3 = this.f;
        Color color3 = MaterialColor.LIGHT_BLUE.P500;
        rectButton3.setIconLabelColors(color3, color3, MaterialColor.LIGHT_BLUE.P500, Color.WHITE);
        table2.add((Table) this.f).top().padTop(4.0f).row();
        this.g = new RectButton(Game.i.localeManager.i18n.get("statistics_subtitle_achievements"), new Label.LabelStyle(Game.i.assetManager.getFont(30), Color.WHITE), () -> {
            this.k = MainTabType.ACHIEVEMENTS;
            a();
        });
        this.g.setSize(320.0f, 64.0f);
        this.g.setBackground(new QuadDrawable(new QuadActor(new float[]{0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f}, Color.WHITE)), 0.0f, 0.0f, 320.0f, 64.0f);
        this.g.setBackgroundColors(new Color(0.0f, 0.0f, 0.0f, 0.0f), new Color(0.0f, 0.0f, 0.0f, 0.07f), new Color(1.0f, 1.0f, 1.0f, 0.14f), new Color(0.0f, 0.0f, 0.0f, 0.28f));
        RectButton rectButton4 = this.g;
        Color color4 = MaterialColor.LIGHT_BLUE.P500;
        rectButton4.setIconLabelColors(color4, color4, MaterialColor.LIGHT_BLUE.P500, Color.WHITE);
        table2.add((Table) this.g).top().padTop(4.0f).row();
        this.j = new Image(Game.i.assetManager.getDrawable("count-bubble"));
        this.j.setSize(32.25f, 36.75f);
        this.j.setPosition(277.75f, 11.0f);
        if (Game.i.achievementManager.countAchievementsToRedeem() == 0) {
            this.j.setVisible(false);
        }
        this.g.addActor(this.j);
        this.h = new RectButton(Game.i.localeManager.i18n.get("statistics_subtitle_earned_items"), new Label.LabelStyle(Game.i.assetManager.getFont(30), Color.WHITE), () -> {
            Array<IssuedItems> issuedPrizes = Game.i.progressManager.getIssuedPrizes();
            for (int i = 0; i < issuedPrizes.size && i != 50; i++) {
                issuedPrizes.get(i).shown = false;
            }
            Game.i.progressManager.showNewlyIssuedPrizesPopup();
        });
        this.h.setSize(320.0f, 64.0f);
        this.h.setBackground(new QuadDrawable(new QuadActor(new float[]{0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f}, Color.WHITE)), 0.0f, 0.0f, 320.0f, 64.0f);
        this.h.setBackgroundColors(new Color(0.0f, 0.0f, 0.0f, 0.0f), new Color(0.0f, 0.0f, 0.0f, 0.07f), new Color(1.0f, 1.0f, 1.0f, 0.14f), new Color(0.0f, 0.0f, 0.0f, 0.28f));
        RectButton rectButton5 = this.h;
        Color color5 = MaterialColor.LIGHT_BLUE.P500;
        rectButton5.setIconLabelColors(color5, color5, MaterialColor.LIGHT_BLUE.P500, Color.WHITE);
        table2.add((Table) this.h).expandY().top().padTop(4.0f).row();
        Table table3 = new Table();
        this.c = new ScrollPane(table3);
        UiUtils.enableMouseMoveScrollFocus(this.c);
        table.add((Table) this.c).padRight(40.0f).fill().expand();
        table3.add().top().left().height(160.0f).fillX().expandX().row();
        this.i = new Table();
        table3.add(this.i).expandX().fillX().padBottom(160.0f).row();
        table3.add().fill().expand();
        a();
    }

    @Override // com.prineside.tdi2.Screen, com.badlogic.gdx.Screen
    public void show() {
        super.show();
        Game.i.uiManager.stage.setScrollFocus(this.c);
    }

    private void a(Table table, CharSequence charSequence, CharSequence charSequence2) {
        Table table2 = new Table();
        table2.setBackground(this.n);
        table2.add((Table) new LimitedWidthLabel(charSequence, 24, 21, 240.0f)).maxWidth(240.0f).padLeft(16.0f);
        Label label = new Label(charSequence2, this.m);
        label.setAlignment(16);
        table2.add((Table) label).padRight(16.0f).expandX().fillX();
        table.add(table2).fillX().expandX().height(48.0f).padBottom(4.0f).row();
    }

    private void a(Table table, StatisticsType statisticsType, IntFloatMap intFloatMap) {
        if (intFloatMap.containsKey(statisticsType.ordinal())) {
            a(table, Game.i.statisticsManager.getStatisticsTitle(statisticsType), StringFormatter.commaSeparatedNumber(intFloatMap.get(statisticsType.ordinal(), 0.0f)));
        }
    }

    private void b(Table table, StatisticsType statisticsType, IntFloatMap intFloatMap) {
        if (intFloatMap.containsKey(statisticsType.ordinal())) {
            a(table, Game.i.statisticsManager.getStatisticsTitle(statisticsType), StringFormatter.digestTime((int) intFloatMap.get(statisticsType.ordinal(), 0.0f)));
        }
    }

    private void a(Table table, IntFloatMap intFloatMap) {
        a(table, StatisticsType.CG, intFloatMap);
        a(table, StatisticsType.EK, intFloatMap);
        a(table, StatisticsType.EP, intFloatMap);
        a(table, StatisticsType.GPG, intFloatMap);
        a(table, StatisticsType.WD, intFloatMap);
        a(table, StatisticsType.SG, intFloatMap);
        if (Game.i.minerManager.isMinerOpened(MinerType.SCALAR, Game.i.gameValueManager.getSnapshot())) {
            a(table, StatisticsType.RG, intFloatMap);
        }
        if (Game.i.minerManager.isMinerOpened(MinerType.SCALAR, Game.i.gameValueManager.getSnapshot())) {
            a(table, StatisticsType.RG_S, intFloatMap);
        }
        if (Game.i.minerManager.isMinerOpened(MinerType.VECTOR, Game.i.gameValueManager.getSnapshot())) {
            a(table, StatisticsType.RG_V, intFloatMap);
        }
        if (Game.i.minerManager.isMinerOpened(MinerType.MATRIX, Game.i.gameValueManager.getSnapshot())) {
            a(table, StatisticsType.RG_M, intFloatMap);
        }
        if (Game.i.minerManager.isMinerOpened(MinerType.TENSOR, Game.i.gameValueManager.getSnapshot())) {
            a(table, StatisticsType.RG_T, intFloatMap);
        }
        if (Game.i.minerManager.isMinerOpened(MinerType.INFIAR, Game.i.gameValueManager.getSnapshot())) {
            a(table, StatisticsType.RG_I, intFloatMap);
        }
        a(table, StatisticsType.TB, intFloatMap);
        a(table, StatisticsType.TS, intFloatMap);
        a(table, StatisticsType.TU, intFloatMap);
        a(table, StatisticsType.TMS, intFloatMap);
        a(table, StatisticsType.TDD, intFloatMap);
        a(table, StatisticsType.SDD, intFloatMap);
        a(table, StatisticsType.GS, intFloatMap);
        b(table, StatisticsType.PT, intFloatMap);
        b(table, StatisticsType.PRT, intFloatMap);
        a(table, StatisticsType.WC, intFloatMap);
        b(table, StatisticsType.WCST, intFloatMap);
        a(table, StatisticsType.WCGC, intFloatMap);
    }

    private void b(Table table, IntFloatMap intFloatMap) {
        Table table2;
        Table table3 = new Table();
        for (TowerType towerType : TowerType.values) {
            if (intFloatMap.get(Game.i.towerManager.getMoneySpentStatisticType(towerType).ordinal(), 0.0f) != 0.0f) {
                Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                image.setColor(Game.i.towerManager.getFactory(towerType).getColor());
                table3.add((Table) image).size(24.0f, 24.0f).padRight(16.0f);
                table3.add((Table) new Label(Game.i.towerManager.getFactory(towerType).getTitle(), this.m)).expandX().fillX().row();
            }
        }
        boolean z = ((float) Game.i.uiManager.getScreenWidth()) / ((float) Game.i.uiManager.getScreenHeight()) < 1.5f;
        boolean z2 = z;
        if (z) {
            table.add(table3).fillX().expandX().padBottom(64.0f).row();
        }
        float f = z2 ? 220.0f : 300.0f;
        if (z2) {
            table2 = table;
        } else {
            table2 = new Table();
            table.add(table2);
            table.add(table3).padLeft(80.0f);
        }
        Label label = new Label(Game.i.localeManager.i18n.get("statistics_TB"), this.m);
        label.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        table2.add((Table) label).height(64.0f).padBottom(16.0f).row();
        PieChartActor pieChartActor = new PieChartActor();
        Array<PieChart.ChartEntryConfig> array = new Array<>(PieChart.ChartEntryConfig.class);
        float f2 = 0.0f;
        for (TowerType towerType2 : TowerType.values) {
            float f3 = intFloatMap.get(Game.i.towerManager.getBuiltStatisticType(towerType2).ordinal(), 0.0f);
            if (f3 > 0.0d) {
                f2 += f3;
                array.add(new PieChart.ChartEntryConfig(Game.i.towerManager.getFactory(towerType2).getColor(), f3, 0.0f));
            }
        }
        if (f2 == 0.0f) {
            array.add(new PieChart.ChartEntryConfig(new Color(0.0f, 0.0f, 0.0f, 0.28f), 100.0f, 0.0f));
        }
        pieChartActor.setConfigs(array);
        table2.add((Table) pieChartActor).size(f, f).row();
        Label label2 = new Label(Game.i.localeManager.i18n.get("damage_by_tower"), this.m);
        label2.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        table2.add((Table) label2).height(64.0f).padBottom(16.0f).padTop(64.0f).row();
        PieChartActor pieChartActor2 = new PieChartActor();
        Array<PieChart.ChartEntryConfig> array2 = new Array<>(PieChart.ChartEntryConfig.class);
        float f4 = 0.0f;
        for (TowerType towerType3 : TowerType.values) {
            float f5 = intFloatMap.get(Game.i.towerManager.getDamageDealtStatisticType(towerType3).ordinal(), 0.0f);
            if (f5 > 0.0f) {
                f4 += f5;
                array2.add(new PieChart.ChartEntryConfig(Game.i.towerManager.getFactory(towerType3).getColor(), f5, 0.0f));
            }
        }
        if (f4 == 0.0f) {
            array2.add(new PieChart.ChartEntryConfig(new Color(0.0f, 0.0f, 0.0f, 0.28f), 100.0f, 0.0f));
        }
        pieChartActor2.setConfigs(array2);
        table2.add((Table) pieChartActor2).size(f, f).row();
    }

    private static void a(Table table) {
        Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image.setColor(1.0f, 1.0f, 1.0f, 0.07f);
        table.add((Table) image).width(2.0f).fillY().padLeft(16.0f).padRight(16.0f);
    }

    private void c(Table table, IntFloatMap intFloatMap) {
        boolean booleanValue = Game.i.gameValueManager.getSnapshot().getBooleanValue(GameValueType.EXTENDED_STATISTICS);
        Label label = new Label(Game.i.localeManager.i18n.get("towers"), Game.i.assetManager.getLabelStyle(36));
        label.setAlignment(8);
        label.setColor(MaterialColor.AMBER.P500);
        table.add((Table) label).height(48.0f).colspan(2).fillX();
        if (booleanValue) {
            Image image = new Image(Game.i.assetManager.getDrawable("gradient-bottom"));
            image.setColor(1.0f, 1.0f, 1.0f, 0.07f);
            table.add((Table) image).width(2.0f).fillY().padLeft(16.0f).padRight(16.0f);
            Label label2 = new Label(Game.i.assetManager.replaceRegionAliasesWithChars("<@icon-damage>/<@icon-coin>"), Game.i.assetManager.getLabelStyle(24));
            label2.setAlignment(16);
            table.add((Table) label2).height(48.0f).right();
        }
        Image image2 = new Image(Game.i.assetManager.getDrawable("gradient-bottom"));
        image2.setColor(1.0f, 1.0f, 1.0f, 0.07f);
        table.add((Table) image2).width(2.0f).fillY().padLeft(16.0f).padRight(16.0f);
        Label label3 = new Label(Game.i.localeManager.i18n.get("built"), Game.i.assetManager.getLabelStyle(24));
        label3.setAlignment(16);
        table.add((Table) label3).height(48.0f).right();
        Image image3 = new Image(Game.i.assetManager.getDrawable("gradient-bottom"));
        image3.setColor(1.0f, 1.0f, 1.0f, 0.07f);
        table.add((Table) image3).width(2.0f).fillY().padLeft(16.0f).padRight(16.0f);
        Label label4 = new Label(Game.i.localeManager.i18n.get("statistics_money_spent"), Game.i.assetManager.getLabelStyle(24));
        label4.setAlignment(16);
        table.add((Table) label4).height(48.0f).right();
        Image image4 = new Image(Game.i.assetManager.getDrawable("gradient-bottom"));
        image4.setColor(1.0f, 1.0f, 1.0f, 0.07f);
        table.add((Table) image4).width(2.0f).fillY().padLeft(16.0f).padRight(16.0f);
        Label label5 = new Label(Game.i.localeManager.i18n.get("damage"), Game.i.assetManager.getLabelStyle(24));
        label5.setAlignment(16);
        table.add((Table) label5).height(48.0f).right();
        Image image5 = new Image(Game.i.assetManager.getDrawable("gradient-bottom"));
        image5.setColor(1.0f, 1.0f, 1.0f, 0.07f);
        table.add((Table) image5).width(2.0f).fillY().padLeft(16.0f).padRight(16.0f);
        Label label6 = new Label(Game.i.localeManager.i18n.get("kills"), Game.i.assetManager.getLabelStyle(24));
        label6.setAlignment(16);
        table.add((Table) label6).height(48.0f).padRight(16.0f).right();
        table.row();
        for (TowerType towerType : TowerType.values) {
            if (intFloatMap.get(Game.i.towerManager.getMoneySpentStatisticType(towerType).ordinal(), 0.0f) != 0.0f) {
                Image image6 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                image6.setColor(1.0f, 1.0f, 1.0f, 0.07f);
                table.add((Table) image6).fillX().height(2.0f).colspan(booleanValue ? 12 : 10).row();
                StatisticsType builtStatisticType = Game.i.towerManager.getBuiltStatisticType(towerType);
                StatisticsType damageDealtStatisticType = Game.i.towerManager.getDamageDealtStatisticType(towerType);
                StatisticsType enemiesKilledStatisticsType = Game.i.towerManager.getEnemiesKilledStatisticsType(towerType);
                Tower.Factory<? extends Tower> factory = Game.i.towerManager.getFactory(towerType);
                table.add((Table) Game.i.towerManager.getFactory(towerType).createIconActor(48.0f)).height(48.0f).width(48.0f);
                Color lerp = factory.getColor().cpy().lerp(Color.WHITE, 0.28f);
                Label label7 = new Label(Game.i.assetManager.replaceRegionAliasesWithChars(factory.getTitle()), this.m);
                label7.setColor(lerp);
                table.add((Table) label7).height(48.0f).growX().padLeft(8.0f);
                a(table);
                if (booleanValue) {
                    Label label8 = new Label(StringFormatter.commaSeparatedNumber(intFloatMap.get(damageDealtStatisticType.ordinal(), 0.0f) / intFloatMap.get(r0.ordinal(), 0.0f)), this.m);
                    label8.setColor(lerp);
                    label8.setAlignment(16);
                    table.add((Table) label8).height(48.0f).growX().right();
                    a(table);
                }
                Label label9 = new Label(StringFormatter.commaSeparatedNumber(intFloatMap.get(builtStatisticType.ordinal(), 0.0f)), this.m);
                label9.setColor(lerp);
                label9.setAlignment(16);
                table.add((Table) label9).height(48.0f).growX().right();
                a(table);
                Label label10 = new Label(StringFormatter.commaSeparatedNumber(intFloatMap.get(r0.ordinal(), 0.0f)), this.m);
                label10.setColor(lerp);
                label10.setAlignment(16);
                table.add((Table) label10).height(48.0f).growX().right();
                a(table);
                Label label11 = new Label(StringFormatter.commaSeparatedNumber(intFloatMap.get(damageDealtStatisticType.ordinal(), 0.0f)), this.m);
                label11.setColor(lerp);
                label11.setAlignment(16);
                table.add((Table) label11).height(48.0f).growX().right();
                a(table);
                Label label12 = new Label(StringFormatter.commaSeparatedNumber(intFloatMap.get(enemiesKilledStatisticsType.ordinal(), 0.0f)), this.m);
                label12.setColor(lerp);
                label12.setAlignment(16);
                table.add((Table) label12).height(48.0f).growX().right().padRight(16.0f);
                table.row();
            }
        }
    }

    private void a() {
        this.i.clear();
        this.d.setEnabled(true);
        this.e.setEnabled(true);
        this.f.setEnabled(true);
        this.h.setEnabled(true);
        this.g.setEnabled(true);
        switch (this.k) {
            case ALL_TIME:
                this.d.setEnabled(false);
                Table table = new Table();
                this.i.add(table).width(600.0f).padRight(40.0f);
                IntFloatMap intFloatMap = new IntFloatMap();
                for (StatisticsType statisticsType : StatisticsType.values) {
                    intFloatMap.put(statisticsType.ordinal(), (float) Game.i.statisticsManager.getAllTime(statisticsType));
                }
                a(table, intFloatMap);
                Table table2 = new Table();
                this.i.add(table2).top().fillX().expandX().minHeight(100.0f).row();
                b(table2, intFloatMap);
                Table table3 = new Table();
                this.i.add(table3).expandX().fillX().colspan(2).padTop(8.0f).row();
                c(table3, intFloatMap);
                return;
            case BY_GAME:
                this.e.setEnabled(false);
                if (this.l == null) {
                    Array<String> allRecordIds = Game.i.replayManager.getAllRecordIds();
                    if (allRecordIds.size == 0) {
                        this.i.add((Table) new Label(Game.i.localeManager.i18n.get("no_replays_found"), Game.i.assetManager.getLabelStyle(30))).row();
                        return;
                    }
                    Array array = new Array();
                    for (int i = 0; i < allRecordIds.size; i++) {
                        ReplayManager.ReplayRecord record = Game.i.replayManager.getRecord(allRecordIds.get(i));
                        if (record != null) {
                            array.add(record);
                        }
                    }
                    array.sort((replayRecord, replayRecord2) -> {
                        if (replayRecord.startTimestamp == replayRecord2.startTimestamp) {
                            return 0;
                        }
                        return replayRecord.startTimestamp < replayRecord2.startTimestamp ? 1 : -1;
                    });
                    for (int i2 = 0; i2 < array.size; i2++) {
                        ReplayManager.ReplayRecord replayRecord3 = (ReplayManager.ReplayRecord) array.get(i2);
                        Table table4 = new Table();
                        table4.setBackground(this.n);
                        o.setLength(0);
                        if (GameStateSystem.GameMode.isBasicLevel(replayRecord3.gameMode)) {
                            o.append(Game.i.localeManager.i18n.get("level")).append(SequenceUtils.SPACE).append(replayRecord3.levelName);
                        } else {
                            o.append(Game.i.localeManager.i18n.get("custom_map"));
                        }
                        o.append(", ").append(Game.i.localeManager.i18n.get("defeated").toLowerCase(Locale.ENGLISH)).append(SequenceUtils.SPACE).append(replayRecord3.defeatedWaves).append(SequenceUtils.SPACE).append(Game.i.localeManager.i18n.get("waves"));
                        o.append(" [#888888FF]- ").append(Game.i.progressManager.getDifficultyName(replayRecord3.difficultyMode)).append("[]");
                        table4.add((Table) new LabelButton(o, this.m, () -> {
                            this.l = replayRecord3;
                            a();
                        })).padLeft(16.0f);
                        Label label = new Label(new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.US).format(new Date(replayRecord3.startTimestamp)), this.m);
                        label.setAlignment(16);
                        table4.add((Table) label).padRight(16.0f).expandX().fillX();
                        this.i.add(table4).fillX().expandX().height(64.0f).padBottom(4.0f).row();
                    }
                    return;
                }
                Table table5 = new Table();
                this.i.add(table5).left().fillX().padBottom(4.0f).row();
                table5.add(new FancyButton("regularButton.a", () -> {
                    this.l = null;
                    a();
                }).withLabel(24, Game.i.localeManager.i18n.get("back_to_replays_list"))).width(256.0f).colspan(2).height(56.0f).left().row();
                Runnable runnable = null;
                if (Game.i.progressManager.isDeveloperModeEnabled()) {
                    runnable = () -> {
                        try {
                            GameStateSystem.startReplayAsRealRun(this.l, true);
                        } catch (Exception e) {
                            f2838a.e("parsing failed", e);
                            Threads.i().runOnMainThread(() -> {
                                Notifications.i().add("Failed to load the replay", Game.i.assetManager.getDrawable("icon-times"), MaterialColor.RED.P800, StaticSoundType.FAIL);
                            });
                        }
                    };
                }
                Label label2 = new Label(this.l.id, Game.i.assetManager.getLabelStyle(24));
                label2.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                table5.add((Table) label2).growX();
                label2.setTouchable(Touchable.enabled);
                label2.addListener(new ClickListener() { // from class: com.prineside.tdi2.screens.StatisticsScreen.1
                    @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                    public void clicked(InputEvent inputEvent, float f, float f2) {
                        Gdx.app.getClipboard().setContents(StatisticsScreen.this.l.id);
                        Notifications.i().add(Game.i.localeManager.i18n.get("copied_to_clipboard"), null, null, null);
                    }
                });
                table5.add(new FancyButton("regularButton.b", () -> {
                    try {
                        GameStateSystem.startReplay(this.l);
                    } catch (Exception e) {
                        f2838a.e("parsing failed", e);
                        Threads.i().runOnMainThread(() -> {
                            Notifications.i().add("Failed to load the replay", Game.i.assetManager.getDrawable("icon-times"), MaterialColor.RED.P800, StaticSoundType.FAIL);
                        });
                    }
                }, runnable).withLabel(24, "Replay")).width(200.0f).height(56.0f).padRight(40.0f).padLeft(16.0f);
                Table table6 = new Table();
                this.i.add(table6).width(600.0f).padRight(40.0f);
                a(table6, this.l.statistics);
                Table table7 = new Table();
                this.i.add(table7).top().fillX().expandX().minHeight(100.0f).row();
                b(table7, this.l.statistics);
                Table table8 = new Table();
                this.i.add(table8).expandX().fillX().colspan(2).padTop(8.0f).row();
                c(table8, this.l.statistics);
                return;
            case MAX_ONE_GAME:
                this.f.setEnabled(false);
                IntFloatMap intFloatMap2 = new IntFloatMap();
                for (StatisticsType statisticsType2 : StatisticsType.values) {
                    intFloatMap2.put(statisticsType2.ordinal(), (float) Game.i.statisticsManager.getMaxOneGame(statisticsType2));
                }
                Table table9 = new Table();
                this.i.add(table9).width(600.0f).padRight(40.0f);
                a(table9, intFloatMap2);
                Table table10 = new Table();
                this.i.add(table10).top().fillX().expandX().minHeight(100.0f).row();
                b(table10, intFloatMap2);
                Table table11 = new Table();
                this.i.add(table11).expandX().fillX().colspan(2).padTop(8.0f).row();
                c(table11, intFloatMap2);
                return;
            case ACHIEVEMENTS:
                this.g.setEnabled(false);
                int i3 = 0;
                for (AchievementType achievementType : AchievementType.values) {
                    if (Game.i.achievementManager.isRequirementMet(achievementType)) {
                        i3++;
                    }
                }
                Table table12 = new Table();
                Table table13 = new Table();
                table12.add(table13).expandX().fillX().top().left();
                table13.add((Table) new Label(Game.i.localeManager.i18n.get("achievements") + " [#8BC34A]" + i3 + " / " + AchievementType.values.length + "[]", Game.i.assetManager.getLabelStyle(36))).top().left().expandX().row();
                Label label3 = new Label(Game.i.localeManager.i18n.get("achievements_screen_hint"), Game.i.assetManager.getLabelStyle(24));
                label3.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                table13.add((Table) label3).top().left().expandX().padBottom(15.0f).row();
                ComplexButton complexButton = new ComplexButton(Game.i.localeManager.i18n.get("do_synchronize"), Game.i.assetManager.getLabelStyle(30), () -> {
                    Game.i.achievementManager.syncAchievementsWithPlatform();
                });
                complexButton.setIconPositioned(Game.i.assetManager.getDrawable("icon-restart"), 128.0f, 8.0f, 48.0f, 48.0f);
                complexButton.setLabel(0.0f, 0.0f, 120.0f, 64.0f, 16);
                complexButton.setIconLabelColors(MaterialColor.LIGHT_GREEN.P500, MaterialColor.LIGHT_GREEN.P300, MaterialColor.LIGHT_GREEN.P700, MaterialColor.GREY.P500);
                table12.add((Table) complexButton).top().right().width(192.0f).height(64.0f);
                this.i.add(table12).expandX().fillX().row();
                Drawable tint = Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(new Color(0.0f, 0.0f, 0.0f, 0.28f));
                Drawable tint2 = Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(MaterialColor.LIGHT_GREEN.P500.cpy().mul(1.0f, 1.0f, 1.0f, 0.1f));
                Color color = new Color(1.0f, 1.0f, 1.0f, 0.14f);
                for (AchievementType achievementType2 : AchievementType.values) {
                    Table table14 = new Table();
                    if (Game.i.achievementManager.isRequirementMet(achievementType2)) {
                        table14.setBackground(tint2);
                    } else {
                        table14.setBackground(tint);
                    }
                    ComplexButton complexButton2 = new ComplexButton("", Game.i.assetManager.getLabelStyle(24), () -> {
                        if (!Game.i.achievementManager.isRedeemed(achievementType2)) {
                            Game.i.achievementManager.redeem(achievementType2);
                            this.j.setVisible(Game.i.achievementManager.countAchievementsToRedeem() > 0);
                            a();
                        }
                    });
                    complexButton2.setBackground(new QuadDrawable(new QuadActor(Color.WHITE, new float[]{0.0f, 0.0f, 0.0f, 80.0f, 90.0f, 80.0f, 80.0f, 0.0f})), 0.0f, 0.0f, 90.0f, 80.0f);
                    complexButton2.setBackgroundColors(MaterialColor.LIGHT_GREEN.P800, MaterialColor.LIGHT_GREEN.P900, MaterialColor.LIGHT_GREEN.P700, color);
                    complexButton2.setIconPositioned(Game.i.assetManager.getDrawable("icon-gift"), 20.0f, 16.0f, 48.0f, 48.0f);
                    if (!Game.i.achievementManager.isRequirementMet(achievementType2)) {
                        complexButton2.setVisible(false);
                    } else if (Game.i.achievementManager.isRedeemed(achievementType2)) {
                        complexButton2.setIconPositioned(Game.i.assetManager.getDrawable("icon-check"), 20.0f, 16.0f, 48.0f, 48.0f);
                        complexButton2.setEnabled(false);
                    }
                    table14.add((Table) complexButton2).size(90.0f, 80.0f);
                    Table table15 = new Table();
                    Label label4 = new Label(Game.i.achievementManager.getName(achievementType2), Game.i.assetManager.getLabelStyle(24));
                    if (Game.i.achievementManager.isRequirementMet(achievementType2)) {
                        label4.setColor(MaterialColor.LIGHT_GREEN.P500);
                    }
                    table15.add((Table) label4).top().left().height(25.0f).expandX().padTop(15.0f).row();
                    Label label5 = new Label("?????", Game.i.assetManager.getLabelStyle(21));
                    if (Game.i.achievementManager.isRequirementMet(achievementType2) || !Game.i.achievementManager.configs[achievementType2.ordinal()].hidden) {
                        label5.setText(Game.i.achievementManager.getDescription(achievementType2));
                    }
                    label5.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                    table15.add((Table) label5).padTop(5.0f).height(20.0f).top().left().padBottom(15.0f).row();
                    table14.add(table15).height(80.0f).padLeft(15.0f).expandX().fillX();
                    if (Game.i.achievementManager.configs[achievementType2.ordinal()].required > 1) {
                        Label label6 = new Label(((Object) StringFormatter.commaSeparatedNumber(Game.i.achievementManager.getCurrentProgress(achievementType2))) + " / " + ((Object) StringFormatter.commaSeparatedNumber(Game.i.achievementManager.configs[achievementType2.ordinal()].required)), Game.i.assetManager.getLabelStyle(24));
                        label6.setAlignment(16);
                        table14.add((Table) label6).width(200.0f).padRight(15.0f);
                    } else {
                        table14.add().height(1.0f).width(200.0f).padRight(15.0f);
                    }
                    this.i.add(table14).expandX().fillX().padBottom(5.0f).row();
                }
                return;
            default:
                return;
        }
    }

    @Override // com.prineside.tdi2.Screen
    public void draw(float f) {
        Color color = Game.i.assetManager.getColor("menu_background");
        Gdx.gl.glClearColor(color.r, color.g, color.f888b, color.f889a);
        Gdx.gl.glClear(16640);
        if (Game.i.settingsManager.isEscButtonJustPressed()) {
            Game.i.screenManager.goToMainMenu();
        }
    }

    @Override // com.prineside.tdi2.Screen, com.badlogic.gdx.Screen
    public void resize(int i, int i2) {
        if (i > 0 && i2 > 0) {
            a();
        }
    }

    @Override // com.prineside.tdi2.Screen, com.badlogic.gdx.Screen, com.badlogic.gdx.utils.Disposable
    public void dispose() {
        Game.i.uiManager.removeLayer(this.f2839b);
    }
}
