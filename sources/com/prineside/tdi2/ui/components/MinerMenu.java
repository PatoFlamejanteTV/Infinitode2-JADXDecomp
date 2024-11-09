package com.prineside.tdi2.ui.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.Miner;
import com.prineside.tdi2.Resource;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.events.game.CoinsChange;
import com.prineside.tdi2.events.game.MapElementSelect;
import com.prineside.tdi2.events.game.MinerMineItem;
import com.prineside.tdi2.events.game.MinerPlace;
import com.prineside.tdi2.events.game.MinerRemove;
import com.prineside.tdi2.events.game.MinerResourceChange;
import com.prineside.tdi2.events.game.MinerUpgrade;
import com.prineside.tdi2.events.game.NextWaveForce;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.modifiers.MiningSpeedModifier;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.ui.Cell;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.ScrollPane;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.systems.GameStateSystem;
import com.prineside.tdi2.tiles.SourceTile;
import com.prineside.tdi2.ui.actors.ExpLine;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.SideMenu;
import com.prineside.tdi2.ui.components.UpgradeSubmenu;
import com.prineside.tdi2.ui.shared.Dialog;
import com.prineside.tdi2.ui.shared.TooltipsOverlay;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.UiUtils;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.Locale;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/MinerMenu.class */
public class MinerMenu implements Disposable {

    /* renamed from: a, reason: collision with root package name */
    private final SideMenu.SideMenuContainer f3354a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f3355b;
    private final Label c;
    private final Label d;
    private final ExpLine e;
    private final TileResources f;
    private final Group g;
    private final Image h;
    private final Image i;
    private final Label j;
    private final Label k;
    private final Label l;
    private final Label m;
    private final ScrollPane n;
    private final Table o;
    private final Label w;
    private final Label x;
    private final Label y;
    private final Image A;
    private final Label B;
    private final Image D;
    private final Image E;
    private final Image F;
    private final Image G;
    private final Image H;
    private final Label I;
    private final Label J;
    private final Label K;
    private final Label L;
    private Table M;
    private final Label N;
    private final Label O;
    private final UpgradeSubmenu P;
    private final SellButton Q;
    private final GameSystemProvider R;
    private static final StringBuilder W = new StringBuilder();
    private final Group[] p = new Group[ResourceType.values.length];
    private final Image[] q = new Image[ResourceType.values.length];
    private final Label[] r = new Label[ResourceType.values.length];
    private final Image[] s = new Image[ResourceType.values.length];
    private final Label[] t = new Label[ResourceType.values.length];
    private final Label[] u = new Label[ResourceType.values.length];
    private final Label[] v = new Label[ResourceType.values.length];
    private final Array<Actor> z = new Array<>(true, 1, Actor.class);
    private final Array<Actor> C = new Array<>(true, 1, Actor.class);
    private final _SideMenuListener S = new _SideMenuListener(this, 0);
    private final Runnable T = this::d;
    private final Runnable U = this::c;
    private final Runnable V = () -> {
        c();
        a(true);
    };

    public MinerMenu(final SideMenu sideMenu, final GameSystemProvider gameSystemProvider) {
        this.R = gameSystemProvider;
        sideMenu.addListener(this.S);
        gameSystemProvider.events.getListeners(MinerUpgrade.class).add(minerUpgrade -> {
            Game.i.uiManager.runOnStageActOnce(this.U);
        });
        gameSystemProvider.events.getListeners(MinerMineItem.class).add(this::a).setDescription("MinerMenu - updates menu if currently selected");
        gameSystemProvider.events.getListeners(MinerResourceChange.class).add(this::a).setDescription("MinerMenu - updates menu if currently selected");
        gameSystemProvider.events.getListeners(NextWaveForce.class).add(this::a).setDescription("MinerMenu - updates menu if currently selected");
        gameSystemProvider.events.getListeners(CoinsChange.class).add(coinsChange -> {
            Game.i.uiManager.runOnStageActOnce(this.T);
        });
        this.f3354a = sideMenu.createContainer("MinerMenu");
        this.f3354a.setName("miner_menu_container");
        int scaledViewportHeight = Game.i.settingsManager.getScaledViewportHeight() - Config.VIEWPORT_HEIGHT;
        this.M = new Table();
        this.M.setSize(366.0f, 32.0f);
        this.M.setPosition(40.0f, 994.0f + scaledViewportHeight + 32.0f);
        this.f3354a.addActor(this.M);
        this.c = new Label("VCTR", new Label.LabelStyle(Game.i.assetManager.getFont(36), Color.WHITE));
        this.c.setSize(520.0f, 26.0f);
        this.c.setPosition(40.0f, 994.0f + scaledViewportHeight);
        this.f3354a.addActor(this.c);
        this.e = new ExpLine();
        this.e.setPosition(40.0f, 954.0f + scaledViewportHeight);
        this.f3354a.addActor(this.e);
        Image image = new Image(Game.i.assetManager.getDrawable("ui-exp-line-cap"));
        image.setSize(72.0f, 48.0f);
        image.setPosition(395.0f, 954.0f + scaledViewportHeight);
        image.setColor(new Color(623191551));
        this.f3354a.addActor(image);
        this.h = new Image(Game.i.assetManager.getDrawable("icon-tools"));
        this.h.setSize(32.0f, 32.0f);
        this.h.setPosition(415.0f, 962.0f + scaledViewportHeight);
        this.f3354a.addActor(this.h);
        this.i = new Image(Game.i.assetManager.getDrawable("resource-vector"));
        this.i.setSize(32.0f, 32.0f);
        this.i.setPosition(415.0f, 962.0f + scaledViewportHeight);
        this.f3354a.addActor(this.i);
        this.j = new Label(Game.i.localeManager.i18n.get("miner_menu_status_installing").toUpperCase(), new Label.LabelStyle(Game.i.assetManager.getFont(21), Color.WHITE));
        this.j.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        this.j.setSize(159.0f, 16.0f);
        this.j.setPosition(233.0f, 985.0f + scaledViewportHeight);
        this.j.setAlignment(16);
        this.f3354a.addActor(this.j);
        this.k = new Label(Game.i.localeManager.i18n.get("miner_menu_status_mining").toUpperCase(), new Label.LabelStyle(Game.i.assetManager.getFont(21), Color.WHITE));
        this.k.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        this.k.setSize(159.0f, 16.0f);
        this.k.setPosition(233.0f, 985.0f + scaledViewportHeight);
        this.k.setAlignment(16);
        this.f3354a.addActor(this.k);
        this.m = new Label("", new Label.LabelStyle(Game.i.assetManager.getFont(18), Color.WHITE));
        this.m.setColor(0.0f, 0.0f, 0.0f, 0.28f);
        this.m.setSize(159.0f, 24.0f);
        this.m.setPosition(50.0f, (954.0f + scaledViewportHeight) - 2.0f);
        this.m.setAlignment(8);
        this.f3354a.addActor(this.m);
        this.l = new Label("", new Label.LabelStyle(Game.i.assetManager.getFont(18), Color.WHITE));
        this.l.setColor(Color.WHITE);
        this.l.setSize(159.0f, 24.0f);
        this.l.setPosition(48.0f, 954.0f + scaledViewportHeight);
        this.l.setAlignment(8);
        this.f3354a.addActor(this.l);
        this.d = new Label("99%", new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
        this.d.setAlignment(1);
        this.d.setSize(71.0f, 48.0f);
        this.d.setPosition(489.0f, 954.0f + scaledViewportHeight);
        this.f3354a.addActor(this.d);
        this.f = new TileResources(600.0f);
        this.f.setPosition(0.0f, 848.0f + scaledViewportHeight);
        this.f3354a.addActor(this.f);
        this.g = new Group();
        this.g.setTransform(false);
        this.g.setPosition(0.0f, 802.0f + scaledViewportHeight);
        this.g.setSize(600.0f, 40.0f);
        this.g.setVisible(false);
        this.g.setTouchable(Touchable.enabled);
        this.f3354a.addActor(this.g);
        Image image2 = new Image(Game.i.assetManager.getDrawable("gradient-horizontal"));
        image2.setColor(MaterialColor.ORANGE.P300.cpy().mul(1.0f, 1.0f, 1.0f, 0.07f));
        image2.setSize(600.0f, 40.0f);
        this.g.addActor(image2);
        Table table = new Table();
        table.setSize(600.0f, 40.0f);
        this.g.addActor(table);
        Image image3 = new Image(Game.i.assetManager.getDrawable("icon-pickaxe"));
        image3.setColor(MaterialColor.ORANGE.P300);
        table.add((Table) image3).size(32.0f).padRight(8.0f);
        Label label = new Label(Game.i.localeManager.i18n.get("source_tile_depleted"), Game.i.assetManager.getLabelStyle(24));
        label.setColor(MaterialColor.ORANGE.P300);
        table.add((Table) label);
        final Image image4 = new Image(Game.i.assetManager.getDrawable("icon-info-circle"));
        image4.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        table.add((Table) image4).size(24.0f).padLeft(8.0f);
        this.g.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.ui.components.MinerMenu.1
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                TooltipsOverlay.i().showText(TooltipsOverlay.TAG_GENERIC_TOOLTIP, image4, Game.i.localeManager.i18n.get("source_tile_depleted_description"), sideMenu.uiLayer.mainUiLayer, sideMenu.uiLayer.zIndex + 1, 4);
            }
        });
        Group group = new Group();
        group.setTransform(false);
        group.setSize(600.0f, 531.0f);
        group.setPosition(0.0f, scaledViewportHeight + 271);
        this.f3354a.addActor(group);
        Label.LabelStyle labelStyle = new Label.LabelStyle(Game.i.assetManager.getFont(21), new Color(1.0f, 1.0f, 1.0f, 0.56f));
        Label label2 = new Label(Game.i.localeManager.i18n.get("resource_item").toUpperCase(), labelStyle);
        label2.setPosition(40.0f, 492.0f);
        label2.setSize(100.0f, 40.0f);
        group.addActor(label2);
        Label label3 = new Label(Game.i.localeManager.i18n.get("mined").toUpperCase(), labelStyle);
        label3.setPosition(178.0f, 492.0f);
        label3.setSize(106.0f, 40.0f);
        label3.setAlignment(16);
        group.addActor(label3);
        Label label4 = new Label(Game.i.localeManager.i18n.get("mined_items").toUpperCase(), labelStyle);
        label4.setPosition(342.0f, 492.0f);
        label4.setSize(218.0f, 40.0f);
        label4.setAlignment(1);
        group.addActor(label4);
        Color color = new Color(808464639);
        Label.LabelStyle labelStyle2 = new Label.LabelStyle(Game.i.assetManager.getFont(21), Color.WHITE);
        Label.LabelStyle labelStyle3 = new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE);
        for (int i = 0; i < ResourceType.values.length; i++) {
            Group group2 = new Group();
            this.p[i] = group2;
            group2.setTransform(false);
            group2.setTouchable(Touchable.disabled);
            group2.setSize(300.0f, 55.0f);
            group2.setPosition(0.0f, 437.0f - (i * 59.0f));
            group.addActor(group2);
            Image image5 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
            image5.setSize(174.0f, 55.0f);
            image5.setColor(color);
            group2.addActor(image5);
            Image image6 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
            image6.setSize(122.0f, 55.0f);
            image6.setX(178.0f);
            image6.setColor(color);
            group2.addActor(image6);
            Image image7 = new Image(Game.i.assetManager.getDrawable(Resource.TEXTURE_REGION_NAMES[i]));
            this.q[i] = image7;
            image7.setSize(32.0f, 32.0f);
            image7.setPosition(40.0f, 12.0f);
            group2.addActor(image7);
            Label label5 = new Label(Game.i.resourceManager.getName(ResourceType.values[i]), labelStyle2);
            this.r[i] = label5;
            label5.setSize(88.0f, 21.0f);
            label5.setPosition(86.0f, 23.0f);
            group2.addActor(label5);
            Image image8 = new Image(Game.i.assetManager.getDrawable("icon-star"));
            image8.setSize(16.0f, 16.0f);
            image8.setPosition(84.0f, 7.0f);
            group2.addActor(image8);
            this.s[i] = image8;
            Label label6 = new Label("10", Game.i.assetManager.getLabelStyle(16));
            label6.setPosition(102.0f, 6.0f);
            label6.setSize(50.0f, 16.0f);
            group2.addActor(label6);
            this.t[i] = label6;
            this.u[i] = new Label("123", labelStyle3);
            this.u[i].setPosition(178.0f, 0.0f);
            this.u[i].setSize(106.0f, 55.0f);
            this.u[i].setAlignment(16);
            group2.addActor(this.u[i]);
            this.v[i] = new Label("", Game.i.assetManager.getLabelStyle(18));
            this.v[i].setColor(MaterialColor.AMBER.P500);
            this.v[i].setPosition(178.0f, 7.0f);
            this.v[i].setSize(106.0f, 17.0f);
            this.v[i].setAlignment(16);
            group2.addActor(this.v[i]);
        }
        this.o = new Table();
        this.n = new ScrollPane(this.o, Game.i.assetManager.getScrollPaneStyle(0.0f));
        UiUtils.enableMouseMoveScrollFocus(this.n);
        this.n.setSize(238.0f, scaledViewportHeight + 311);
        this.n.setPosition(332.0f, 191 - scaledViewportHeight);
        this.n.setScrollingDisabled(true, false);
        this.n.setOverscroll(false, false);
        UiUtils.enableMouseMoveScrollFocus(this.n);
        group.addActor(this.n);
        Image image9 = new Image(Game.i.assetManager.getDrawable("gradient-top"));
        image9.setSize(238.0f, 10.0f);
        image9.setPosition(332.0f, 492.0f);
        image9.setColor(new Color(724249599));
        group.addActor(image9);
        Image image10 = new Image(Game.i.assetManager.getDrawable("gradient-bottom"));
        image10.setSize(238.0f, 10.0f);
        image10.setPosition(332.0f, 191.0f);
        image10.setColor(new Color(724249599));
        group.addActor(image10);
        Image image11 = new Image(Game.i.assetManager.getDrawable("icon-star"));
        image11.setPosition(40.0f, 145.0f);
        image11.setSize(32.0f, 32.0f);
        group.addActor(image11);
        Table table2 = new Table();
        table2.setPosition(86.0f, 159.0f);
        table2.setSize(214.0f, 21.0f);
        group.addActor(table2);
        this.w = new Label("2.81k", Game.i.assetManager.getLabelStyle(21));
        table2.add((Table) this.w);
        Label label7 = new Label(Game.i.localeManager.i18n.get("points_per_minute_short"), Game.i.assetManager.getLabelStyle(21));
        label7.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        table2.add((Table) label7).padLeft(8.0f);
        table2.add().height(1.0f).growX();
        Table table3 = new Table();
        table3.setPosition(86.0f, 138.0f);
        table3.setSize(214.0f, 17.0f);
        group.addActor(table3);
        this.x = new Label("44.8k", Game.i.assetManager.getLabelStyle(18));
        table3.add((Table) this.x);
        Label label8 = new Label(Game.i.localeManager.i18n.get("score_gained_in_total_suffix"), Game.i.assetManager.getLabelStyle(18));
        label8.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        table3.add((Table) label8).padLeft(6.0f);
        table3.add().height(1.0f).growX();
        this.L = new Label(Game.i.localeManager.i18n.get("miner_speed_hint_no_modifiers_or_double_speed"), Game.i.assetManager.getLabelStyle(21));
        this.L.setPosition(167.0f, 0.0f);
        this.L.setSize(274.0f, 80.0f);
        this.L.setWrap(true);
        this.L.setAlignment(1);
        this.L.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        group.addActor(this.L);
        Label label9 = new Label(Game.i.localeManager.i18n.get("mining_speed").toUpperCase(Locale.US), labelStyle);
        label9.setPosition(40.0f, 91.0f);
        label9.setSize(218.0f, 17.0f);
        label9.setAlignment(8);
        group.addActor(label9);
        Image image12 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image12.setSize(600.0f, 80.0f);
        image12.setColor(0.0f, 0.0f, 0.0f, 0.28f);
        group.addActor(image12);
        this.A = new Image(Game.i.assetManager.getQuad("ui.minerMenu.speedCellBg"));
        this.A.setColor(new Color(6904974));
        this.A.setPosition(138.0f, 0.0f);
        this.A.setSize(178.0f, 80.0f);
        group.addActor(this.A);
        this.z.add(this.A);
        this.D = new Image(Game.i.assetManager.getQuad("ui.minerMenu.speedCellBg"));
        this.D.setColor(new Color(-7405466));
        this.D.setPosition(288.0f, 0.0f);
        this.D.setSize(178.0f, 80.0f);
        group.addActor(this.D);
        this.C.add(this.D);
        Image image13 = new Image(Game.i.assetManager.getDrawable("icon-pickaxe"));
        image13.setPosition(40.0f, 36.0f);
        image13.setSize(30.0f, 30.0f);
        group.addActor(image13);
        Label label10 = new Label(Game.i.localeManager.i18n.get("miner_menu_base_speed_hint").toUpperCase(Locale.US), Game.i.assetManager.getLabelStyle(18));
        label10.setPosition(40.0f, 13.0f);
        label10.setSize(100.0f, 17.0f);
        label10.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        group.addActor(label10);
        this.y = new Label("5.61", Game.i.assetManager.getLabelStyle(24));
        this.y.setPosition(76.0f, 36.0f);
        this.y.setSize(100.0f, 30.0f);
        group.addActor(this.y);
        Image image14 = new Image(Game.i.assetManager.getDrawable("icon-modifier-mining-speed-research"));
        image14.setPosition(176.0f, 34.0f);
        image14.setColor(0.0f, 0.0f, 0.0f, 0.56f);
        image14.setSize(30.0f, 30.0f);
        group.addActor(image14);
        this.z.add(image14);
        Image image15 = new Image(Game.i.assetManager.getDrawable("icon-modifier-mining-speed-research"));
        image15.setPosition(176.0f, 36.0f);
        image15.setColor(new Color(1303817471));
        image15.setSize(30.0f, 30.0f);
        group.addActor(image15);
        this.z.add(image15);
        Label label11 = new Label(Game.i.localeManager.i18n.get("modifiers").toUpperCase(Locale.US), labelStyle);
        label11.setPosition(143.0f, 13.0f);
        label11.setSize(150.0f, 17.0f);
        label11.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        label11.setAlignment(1);
        group.addActor(label11);
        this.z.add(label11);
        this.B = new Label("+200%", Game.i.assetManager.getLabelStyle(24));
        this.B.setSize(100.0f, 30.0f);
        this.B.setPosition(213.0f, 36.0f);
        group.addActor(this.B);
        this.z.add(this.B);
        Image image16 = new Image(Game.i.assetManager.getDrawable("icon-info-circle"));
        image16.setSize(20.0f, 20.0f);
        image16.setPosition(301.0f, 64.0f);
        image16.setColor(0.0f, 0.0f, 0.0f, 0.56f);
        group.addActor(image16);
        this.z.add(image16);
        Image image17 = new Image(Game.i.assetManager.getDrawable("icon-info-circle"));
        image17.setSize(20.0f, 20.0f);
        image17.setPosition(301.0f, 66.0f);
        image17.setColor(new Color(9865471));
        group.addActor(image17);
        this.z.add(image17);
        final Actor actor = new Actor();
        actor.setPosition(138.0f, 0.0f);
        actor.setSize(165.0f, 80.0f);
        actor.setTouchable(Touchable.enabled);
        group.addActor(actor);
        this.z.add(actor);
        actor.addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.components.MinerMenu.2
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                Miner b2 = MinerMenu.this.b();
                if (b2 != null) {
                    int miningSpeedModifierCount = gameSystemProvider.miner.getMiningSpeedModifierCount(b2);
                    MinerMenu.W.setLength(0);
                    MinerMenu.W.append(Game.i.localeManager.i18n.format("miner_is_connected_to_mining_speed_mods", Integer.valueOf(miningSpeedModifierCount))).append(SequenceUtils.EOL);
                    MinerMenu.W.append(Game.i.localeManager.i18n.get("mining_speed_mods_tooltip")).append(SequenceUtils.EOL);
                    Table table4 = new Table();
                    Label label12 = new Label(MinerMenu.W, Game.i.assetManager.getLabelStyle(21));
                    label12.setWrap(true);
                    label12.setAlignment(1);
                    table4.add((Table) label12).minWidth(300.0f).growX().row();
                    table4.add(MiningSpeedModifier.createEfficiencyTable(gameSystemProvider, miningSpeedModifierCount)).growX().row();
                    TooltipsOverlay.i().showActor("miner_menu_modifiers_speed_hint", actor, table4, UiManager.MainUiLayer.SCREEN, 106, 2);
                }
            }
        });
        Image image18 = new Image(Game.i.assetManager.getDrawable("icon-info-circle"));
        image18.setSize(20.0f, 20.0f);
        image18.setPosition(451.0f, 64.0f);
        image18.setColor(0.0f, 0.0f, 0.0f, 0.56f);
        group.addActor(image18);
        this.C.add(image18);
        Image image19 = new Image(Game.i.assetManager.getDrawable("icon-info-circle"));
        image19.setSize(20.0f, 20.0f);
        image19.setPosition(451.0f, 66.0f);
        image19.setColor(new Color(-4126721));
        group.addActor(image19);
        this.C.add(image19);
        this.C.add(this.D);
        this.F = new Image(Game.i.assetManager.getDrawable("icon-x2"));
        this.F.setPosition(323.0f, 17.0f);
        this.F.setSize(38.0f, 38.0f);
        this.F.setColor(0.0f, 0.0f, 0.0f, 0.56f);
        group.addActor(this.F);
        this.C.add(this.F);
        this.E = new Image(Game.i.assetManager.getDrawable("icon-x2"));
        this.E.setPosition(323.0f, 19.0f);
        this.E.setSize(38.0f, 38.0f);
        group.addActor(this.E);
        this.C.add(this.E);
        this.I = new Label("05:31", Game.i.assetManager.getLabelStyle(24));
        this.I.setPosition(372.0f, 29.0f);
        this.I.setSize(67.0f, 20.0f);
        group.addActor(this.I);
        this.C.add(this.I);
        this.H = new Image(Game.i.assetManager.getDrawable("icon-x2"));
        this.H.setPosition(318.0f, 1.0f);
        this.H.setSize(38.0f, 38.0f);
        this.H.setColor(0.0f, 0.0f, 0.0f, 0.56f);
        group.addActor(this.H);
        this.C.add(this.H);
        this.G = new Image(Game.i.assetManager.getDrawable("icon-x2"));
        this.G.setPosition(318.0f, 3.0f);
        this.G.setSize(38.0f, 38.0f);
        group.addActor(this.G);
        this.C.add(this.G);
        this.J = new Label("05:31", Game.i.assetManager.getLabelStyle(24));
        this.J.setPosition(366.0f, 14.0f);
        this.J.setSize(67.0f, 20.0f);
        group.addActor(this.J);
        this.C.add(this.J);
        final Actor actor2 = new Actor();
        actor2.setPosition(302.0f, 0.0f);
        actor2.setSize(165.0f, 80.0f);
        actor2.setTouchable(Touchable.enabled);
        group.addActor(actor2);
        this.C.add(actor2);
        actor2.addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.components.MinerMenu.3
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                Miner b2 = MinerMenu.this.b();
                if (b2 != null) {
                    MinerMenu.W.setLength(0);
                    if (b2.doubleSpeedTimeLeft > 0.0f) {
                        MinerMenu.W.append(Game.i.localeManager.i18n.get("miner_double_speed_hint_individual")).append(SequenceUtils.EOL);
                    }
                    if (gameSystemProvider.miner.bonusDoubleMiningSpeedTimeLeft > 0.0f) {
                        MinerMenu.W.append(Game.i.localeManager.i18n.get("miner_double_speed_hint_global")).append(SequenceUtils.EOL);
                    }
                    TooltipsOverlay.i().showText("miner_menu_double_speed_hint", actor2, MinerMenu.W, UiManager.MainUiLayer.SCREEN, 106, 2);
                }
            }
        });
        this.K = new Label("[#888888]=[] 5.61", Game.i.assetManager.getLabelStyle(30));
        this.K.setAlignment(16);
        this.K.setSize(103.0f, 25.0f);
        this.K.setPosition(457.0f, 33.0f);
        group.addActor(this.K);
        Label label12 = new Label("/" + Game.i.localeManager.i18n.get("TIME_CHAR_MINUTE"), Game.i.assetManager.getLabelStyle(18));
        label12.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        label12.setAlignment(16);
        label12.setSize(54.0f, 16.0f);
        label12.setPosition(506.0f, 15.0f);
        group.addActor(label12);
        this.N = new Label("+1.2", Game.i.assetManager.getLabelStyle(21));
        this.N.setColor(MaterialColor.LIGHT_GREEN.P500);
        this.N.setPosition(76.0f, 66.0f);
        this.N.setSize(100.0f, 21.0f);
        group.addActor(this.N);
        this.O = new Label("+1.2", Game.i.assetManager.getLabelStyle(21));
        this.O.setColor(MaterialColor.LIGHT_GREEN.P500);
        this.O.setPosition(478.0f, 66.0f);
        this.O.setSize(82.0f, 21.0f);
        this.O.setAlignment(16);
        group.addActor(this.O);
        this.P = new UpgradeSubmenu();
        this.P.addListener(new UpgradeSubmenu.UpgradeSubmenuListener() { // from class: com.prineside.tdi2.ui.components.MinerMenu.4
            @Override // com.prineside.tdi2.ui.components.UpgradeSubmenu.UpgradeSubmenuListener
            public void upgradeButtonStateChanged(boolean z) {
                MinerMenu.this.c();
            }

            @Override // com.prineside.tdi2.ui.components.UpgradeSubmenu.UpgradeSubmenuListener
            public void upgradeButtonConfirmed() {
                Miner b2 = MinerMenu.this.b();
                if (b2 != null) {
                    gameSystemProvider.miner.upgradeMinerAction(b2);
                    MinerMenu.this.c();
                }
            }

            @Override // com.prineside.tdi2.ui.components.UpgradeSubmenu.UpgradeSubmenuListener
            public void globalUpgradeButtonConfirmed() {
                Miner b2 = MinerMenu.this.b();
                if (b2 != null) {
                    String str = Game.i.localeManager.i18n.get("upgrade_all_miners_by_type_confirm") + " <@game-ui-coin-icon>[#FDD835]" + ((Object) StringFormatter.commaSeparatedNumber(gameSystemProvider.miner.getGlobalUpgradePrice(b2.type))) + "[]";
                    Dialog i2 = Dialog.i();
                    CharSequence replaceRegionAliasesWithChars = Game.i.assetManager.replaceRegionAliasesWithChars(str);
                    GameSystemProvider gameSystemProvider2 = gameSystemProvider;
                    i2.showConfirm(replaceRegionAliasesWithChars, () -> {
                        gameSystemProvider2.miner.globalUpgradeMinerAction(b2.type);
                        MinerMenu.this.c();
                    });
                }
            }
        });
        this.P.setPosition(0.0f, 132.0f);
        this.f3354a.addActor(this.P);
        this.Q = new SellButton(() -> {
            Miner b2 = b();
            if (b2 != null) {
                gameSystemProvider.miner.sellMinerAction(b2);
            }
        });
        this.Q.setPosition(368.0f, 40.0f);
        this.f3354a.addActor(this.Q);
        gameSystemProvider.events.getListeners(MapElementSelect.class).add(mapElementSelect -> {
            Tile selectedTile = gameSystemProvider._gameMapSelection.getSelectedTile();
            if (selectedTile != null && selectedTile.type == TileType.SOURCE && ((SourceTile) selectedTile).miner != null) {
                this.P.setButtonSelected(false);
                c();
                a(true);
            } else {
                a(false);
            }
            e();
        });
        gameSystemProvider.events.getListeners(MinerPlace.class).add(minerPlace -> {
            if (minerPlace.getMiner().getTile() == gameSystemProvider._gameMapSelection.getSelectedTile()) {
                a(true);
                e();
                Game.i.uiManager.runOnStageActOnce(this.V);
            }
        });
        gameSystemProvider.events.getListeners(MinerRemove.class).add(minerRemove -> {
            if (minerRemove.getOldTile() == gameSystemProvider._gameMapSelection.getSelectedTile()) {
                a(false);
                e();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Miner b() {
        Tile selectedTile = this.R._gameMapSelection.getSelectedTile();
        if (selectedTile != null && selectedTile.type == TileType.SOURCE) {
            return ((SourceTile) selectedTile).miner;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (this.R == null || this.R.map == null) {
            return;
        }
        this.M.clear();
        Tile selectedTile = this.R._gameMapSelection.getSelectedTile();
        if (selectedTile != null && selectedTile.type == TileType.SOURCE && ((SourceTile) selectedTile).miner != null) {
            this.f3354a.setLabelOverTitleTilePos(selectedTile);
            SourceTile sourceTile = (SourceTile) selectedTile;
            Miner miner = sourceTile.miner;
            this.g.setVisible(sourceTile.isDepleted());
            this.x.setText(StringFormatter.compactNumber(miner.totalScoreGained, false));
            this.w.setText(StringFormatter.compactNumber(this.R.miner.calculateScorePerMinute(miner), true));
            if (miner.affectedByLoopAbility != null) {
                Table table = new Table();
                this.M.add(table).padRight(15.0f);
                Image image = new Image(Game.i.assetManager.getDrawable("icon-loop"));
                image.setColor(MaterialColor.GREEN.P500);
                table.add((Table) image).size(24.0f).padRight(6.0f);
                Label label = new Label(StringFormatter.compactNumber(miner.loopAbilityResourceBuffer, false), Game.i.assetManager.getLabelStyle(21));
                label.setColor(MaterialColor.GREEN.P500);
                table.add((Table) label);
                Image image2 = new Image(Game.i.assetManager.getDrawable("icon-info-circle"));
                image2.setColor(1.0f, 1.0f, 1.0f, 0.28f);
                table.add((Table) image2).size(24.0f).padLeft(6.0f);
                table.setTouchable(Touchable.enabled);
                table.addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.components.MinerMenu.5
                    @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                    public void clicked(InputEvent inputEvent, float f, float f2) {
                        TooltipsOverlay.i().showText("loop_ability_miner_menu_hint", MinerMenu.this.M, Game.i.localeManager.i18n.get("loop_ability_miner_menu_hint"), UiManager.MainUiLayer.SCREEN, 106, 4);
                    }
                });
            }
            this.M.add().height(1.0f).growX();
            Miner.Factory<? extends Miner> factory = Game.i.minerManager.getFactory(miner.type);
            this.c.setText(factory.getTitle());
            this.f.setTile(sourceTile);
            for (int i = 0; i < ResourceType.values.length; i++) {
                ResourceType resourceType = ResourceType.values[i];
                if (factory.canMineResource(resourceType)) {
                    this.p[i].setVisible(true);
                    this.t[i].setText(StringFormatter.compactNumber(this.R.gameState.calculateFinalScore(this.R.miner.getResourceMinedRawScore(resourceType), StatisticsType.SG_RM), false));
                    int i2 = miner.getTile().minedResources[i];
                    int i3 = 0;
                    if (this.R.gameState.gameMode == GameStateSystem.GameMode.USER_MAPS) {
                        int resourcesCount = i2 - miner.getTile().getResourcesCount(resourceType);
                        i3 = resourcesCount;
                        if (resourcesCount < 0) {
                            i3 = 0;
                        } else {
                            i2 -= i3;
                        }
                    }
                    if (i3 > 0) {
                        W.setLength(0);
                        W.append('+').append(StringFormatter.compactNumber(i3, false));
                        this.v[i].setText(W);
                        this.v[i].setVisible(true);
                        this.u[i].setY(24.0f);
                        this.u[i].setHeight(22.0f);
                    } else {
                        this.v[i].setVisible(false);
                        this.u[i].setY(0.0f);
                        this.u[i].setHeight(55.0f);
                    }
                    this.u[i].setText(StringFormatter.compactNumber(i2, false));
                    if (sourceTile.getResourcesCount(resourceType) > 0 || miner.getTile().minedResources[i] > 0) {
                        this.t[i].setColor(1.0f, 1.0f, 1.0f, 0.56f);
                        this.s[i].setColor(1.0f, 1.0f, 1.0f, 0.56f);
                        this.u[i].setColor(Color.WHITE);
                        this.q[i].setColor(Game.i.resourceManager.getColor(resourceType));
                        this.r[i].setColor(Game.i.resourceManager.getColor(resourceType));
                    } else {
                        this.t[i].setColor(0.14f, 0.14f, 0.14f, 1.0f);
                        this.s[i].setColor(0.14f, 0.14f, 0.14f, 1.0f);
                        this.u[i].setColor(0.14f, 0.14f, 0.14f, 1.0f);
                        this.q[i].setColor(0.14f, 0.14f, 0.14f, 1.0f);
                        this.r[i].setColor(0.14f, 0.14f, 0.14f, 1.0f);
                    }
                } else {
                    this.p[i].setVisible(false);
                }
            }
            if (this.P.isButtonSelected() && miner.getUpgradeLevel() < this.R.miner.getMaxUpgradeLevel(miner.type)) {
                float miningSpeed = this.R.miner.getMiningSpeed(miner, miner.getUpgradeLevel() + 1) - this.R.miner.getMiningSpeed(miner, miner.getUpgradeLevel());
                float f = miningSpeed;
                if (miningSpeed >= 0.0f) {
                    if (miner.doubleSpeedTimeLeft > 0.0f) {
                        f *= 2.0f;
                    }
                    W.setLength(0);
                    W.append('+');
                    W.append(StringFormatter.compactNumber(f * 60.0f, true));
                    this.O.setText(W);
                    float baseMiningSpeed = this.R.miner.getBaseMiningSpeed(miner, miner.getUpgradeLevel() + 1) - this.R.miner.getBaseMiningSpeed(miner, miner.getUpgradeLevel());
                    W.setLength(0);
                    W.append('+');
                    W.append(StringFormatter.compactNumber(baseMiningSpeed * 60.0f, true));
                    this.N.setText(W);
                    this.N.setVisible(true);
                    this.O.setVisible(true);
                } else {
                    this.N.setVisible(false);
                    this.O.setVisible(false);
                }
            } else {
                this.N.setVisible(false);
                this.O.setVisible(false);
            }
            this.o.clearChildren();
            this.o.add().width(1.0f).height(10.0f).row();
            int lootSlots = this.R.loot.getLootSlots(miner.type);
            Array<ItemStack> sourceMinedItems = this.R.loot.getSourceMinedItems(miner.getTile().getX(), miner.getTile().getY());
            for (int i4 = 0; i4 < lootSlots; i4++) {
                Group group = new Group();
                group.setTransform(false);
                Image image3 = new Image(Game.i.assetManager.getQuad(i4 % 2 == 0 ? "ui.minerMenu.itemCell.a" : "ui.minerMenu.itemCell.b"));
                image3.setSize(70.0f, 70.0f);
                group.addActor(image3);
                if (sourceMinedItems != null && sourceMinedItems.size > i4) {
                    image3.setColor(new Color(808464639));
                    Image image4 = new Image(Game.i.assetManager.getQuad(i4 % 2 == 0 ? "ui.minerMenu.itemCell.aRarityTint" : "ui.minerMenu.itemCell.bRarityTint"));
                    image4.setSize(70.0f, 70.0f);
                    image4.setColor(Game.i.progressManager.getRarityColor(sourceMinedItems.get(i4).getItem().getRarity()));
                    group.addActor(image4);
                    Actor generateIcon = sourceMinedItems.get(i4).getItem().generateIcon(48.0f, true);
                    generateIcon.setPosition(11.0f, 11.0f);
                    group.addActor(generateIcon);
                    Label label2 = new Label(StringFormatter.compactNumber(sourceMinedItems.get(i4).getCount(), false), Game.i.assetManager.getLabelStyle(18));
                    label2.setPosition(37.0f, 5.0f);
                    label2.setSize(27.0f, 16.0f);
                    label2.setAlignment(16);
                    group.addActor(label2);
                } else {
                    image3.setColor(0.0f, 0.0f, 0.0f, 0.28f);
                }
                Cell padBottom = this.o.add((Table) group).size(70.0f).padBottom(4.0f);
                if (i4 % 3 != 2) {
                    padBottom.padRight(4.0f);
                }
                if ((i4 + 1) % 3 == 0) {
                    this.o.row();
                }
            }
            this.o.row();
            this.o.add().width(1.0f).minHeight(10.0f).growY();
            this.P.a(miner.getUpgradeLevel(), this.R.miner.getMaxUpgradeLevel(miner.type));
            if (miner.getUpgradeLevel() < this.R.miner.getMaxUpgradeLevel(miner.type)) {
                this.P.a(this.R.miner.getUpgradePrice(miner));
            } else {
                this.P.a(-1);
            }
            d();
            this.Q.setPrice(miner.getSellPrice());
        }
    }

    private void d() {
        Miner b2 = b();
        if (b2 != null) {
            this.P.a(this.R.gameState.getMoney() >= this.R.miner.getUpgradePrice(b2) && b2.getUpgradeLevel() < this.R.miner.getMaxUpgradeLevel(b2.type));
        }
    }

    public void draw(float f) {
        if (this.f3355b) {
            Miner b2 = b();
            if (b2 != null) {
                float f2 = 0.0f;
                if (b2.isPrepared()) {
                    if (b2.nextMinedResourceType != null) {
                        f2 = b2.getVisualMiningProgress();
                        this.e.setColor(Game.i.resourceManager.getColor(b2.nextMinedResourceType));
                        this.i.setColor(Game.i.resourceManager.getColor(b2.nextMinedResourceType));
                        this.i.setDrawable(Game.i.assetManager.getDrawable(Resource.TEXTURE_REGION_NAMES[b2.nextMinedResourceType.ordinal()]));
                    }
                    this.i.setVisible(true);
                    this.h.setVisible(false);
                    this.k.setVisible(true);
                    this.j.setVisible(false);
                    W.setLength(0);
                    W.append((int) (f2 * 100.0f));
                    W.append('%');
                    this.d.setText(W);
                } else {
                    float preparationProgress = b2.getPreparationProgress();
                    f2 = preparationProgress;
                    if (preparationProgress < 0.0f || f2 > 1.0f) {
                        throw new IllegalStateException("Preparation progress " + f2);
                    }
                    this.e.setColor(MaterialColor.GREY.P500);
                    this.i.setVisible(false);
                    this.h.setVisible(true);
                    this.k.setVisible(false);
                    this.j.setVisible(true);
                    this.d.setText(StringFormatter.digestTime((int) b2.getInstallTimeLeft()));
                }
                this.e.setCoeff(f2);
                if (b2.doubleSpeedTimeLeft > 0.0f || this.R.miner.bonusDoubleMiningSpeedTimeLeft > 0.0f) {
                    W.setLength(0);
                    if (b2.doubleSpeedTimeLeft > 0.0f) {
                        W.append("x2: ");
                        W.append(StringFormatter.digestTime(StrictMath.round(b2.doubleSpeedTimeLeft)));
                    }
                    if (this.R.miner.bonusDoubleMiningSpeedTimeLeft > 0.0f) {
                        if (W.length != 0) {
                            W.append(" / ");
                        }
                        W.append("Bonus x2: ");
                        W.append(StringFormatter.digestTime(StrictMath.round(this.R.miner.bonusDoubleMiningSpeedTimeLeft)));
                    }
                    this.l.setText(W);
                    this.m.setText(W);
                    this.l.setVisible(true);
                    this.m.setVisible(true);
                } else {
                    this.l.setVisible(false);
                    this.m.setVisible(false);
                }
                this.y.setText(StringFormatter.compactNumber(this.R.miner.getBaseMiningSpeed(b2, b2.getUpgradeLevel()) * 60.0f, true));
                float miningSpeed = this.R.miner.getMiningSpeed(b2, b2.getUpgradeLevel()) * 60.0f;
                if (b2.doubleSpeedTimeLeft > 0.0f) {
                    miningSpeed *= 2.0f;
                }
                W.setLength(0);
                W.append("[#888888]=[] ").append(StringFormatter.compactNumber(miningSpeed, true));
                this.K.setText(W);
                if (this.R.miner.getMiningSpeedModifierCount(b2) != 0) {
                    float miningSpeedModifierMultiplier = this.R.miner.getMiningSpeedModifierMultiplier(b2);
                    for (int i = 0; i < this.z.size; i++) {
                        this.z.get(i).setVisible(true);
                    }
                    W.setLength(0);
                    W.append('+').append(MathUtils.round((miningSpeedModifierMultiplier - 1.0f) * 100.0f)).append('%');
                    this.B.setText(W);
                } else {
                    for (int i2 = 0; i2 < this.z.size; i2++) {
                        this.z.get(i2).setVisible(false);
                    }
                }
                int i3 = 0;
                if (b2.doubleSpeedTimeLeft > 0.0f) {
                    i3 = 0 + 1;
                }
                if (this.R.miner.bonusDoubleMiningSpeedTimeLeft > 0.0f) {
                    i3++;
                }
                if (i3 == 0) {
                    for (int i4 = 0; i4 < this.C.size; i4++) {
                        this.C.get(i4).setVisible(false);
                    }
                } else {
                    for (int i5 = 0; i5 < this.C.size; i5++) {
                        this.C.get(i5).setVisible(true);
                    }
                    if (i3 == 1) {
                        this.I.setPosition(372.0f, 29.0f);
                        this.F.setPosition(323.0f, 17.0f);
                        this.E.setPosition(323.0f, 19.0f);
                        this.G.setVisible(false);
                        this.H.setVisible(false);
                        this.J.setVisible(false);
                        if (b2.doubleSpeedTimeLeft > 0.0f) {
                            this.I.setText(StringFormatter.digestTime((int) b2.doubleSpeedTimeLeft));
                        } else {
                            this.I.setText(StringFormatter.digestTime((int) this.R.miner.bonusDoubleMiningSpeedTimeLeft));
                        }
                    } else {
                        this.I.setPosition(378.0f, 44.0f);
                        this.F.setPosition(329.0f, 32.0f);
                        this.E.setPosition(329.0f, 34.0f);
                        this.I.setText(StringFormatter.digestTime((int) b2.doubleSpeedTimeLeft));
                        this.J.setText(StringFormatter.digestTime((int) this.R.miner.bonusDoubleMiningSpeedTimeLeft));
                    }
                }
                this.L.setVisible(this.R.miner.getMiningSpeedModifierCount(b2) == 0 && i3 == 0);
                return;
            }
            this.e.setCoeff(0.0f);
        }
    }

    private static void e() {
        TooltipsOverlay.i().hideEntry("miner_menu_modifiers_speed_hint");
        TooltipsOverlay.i().hideEntry("miner_menu_double_speed_hint");
    }

    private void a(boolean z) {
        if (this.f3355b != z) {
            this.f3355b = z;
            if (z) {
                this.f3354a.show();
                c();
            } else {
                if (Game.i.uiManager.stage.getScrollFocus() == this.n) {
                    Game.i.uiManager.stage.setScrollFocus(null);
                }
                this.f3354a.hide();
            }
            e();
        }
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
    }

    private void a(MinerResourceChange minerResourceChange) {
        if (this.f3355b && b() == minerResourceChange.getMiner()) {
            Game.i.uiManager.runOnStageActOnce(this.U);
        }
    }

    private void a(NextWaveForce nextWaveForce) {
        if (this.f3355b) {
            Game.i.uiManager.runOnStageActOnce(this.U);
        }
    }

    private void a(MinerMineItem minerMineItem) {
        if (this.f3355b && b() == minerMineItem.getMiner()) {
            Game.i.uiManager.runOnStageActOnce(this.U);
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/MinerMenu$_SideMenuListener.class */
    private class _SideMenuListener extends SideMenu.SideMenuListener.SideMenuListenerAdapter {
        private _SideMenuListener() {
        }

        /* synthetic */ _SideMenuListener(MinerMenu minerMenu, byte b2) {
            this();
        }

        @Override // com.prineside.tdi2.ui.actors.SideMenu.SideMenuListener.SideMenuListenerAdapter, com.prineside.tdi2.ui.actors.SideMenu.SideMenuListener
        public void offscreenChanged() {
            MinerMenu.this.c();
        }
    }
}
