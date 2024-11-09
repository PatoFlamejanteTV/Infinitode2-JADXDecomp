package com.prineside.tdi2.ui.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Disposable;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameListener;
import com.prineside.tdi2.ListenerGroup;
import com.prineside.tdi2.MapPrestigeConfig;
import com.prineside.tdi2.UserMap;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.ui.actors.ComplexButton;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.QuadActor;
import com.prineside.tdi2.ui.shared.Dialog;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.UiUtils;
import com.prineside.tdi2.utils.logging.TLog;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/MapPrestigeOverlay.class */
public class MapPrestigeOverlay implements Disposable {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3346a = TLog.forClass(MapPrestigeOverlay.class);
    private final Group d;
    private Image e;
    private Label g;
    private Table h;
    private Label i;
    private Table j;
    private Label k;
    private Table l;
    private Label m;
    private Table n;
    private Label o;
    private Table p;
    private Label q;
    private Table r;
    private Label s;
    private Table t;
    private Table u;
    private Label v;
    private Label w;
    private QuadActor x;
    private Table y;
    private ComplexButton z;
    private MapPrestigeConfig A;

    /* renamed from: b, reason: collision with root package name */
    private final UiManager.UiLayer f3347b = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 175, "MapPrestigeOverlay overlay");
    private final UiManager.UiLayer c = Game.i.uiManager.addLayer(UiManager.MainUiLayer.SCREEN, 176, "MapPrestigeOverlay main");
    public ListenerGroup<MapPrestigeOverlayListener> listeners = new ListenerGroup<>(MapPrestigeOverlayListener.class);
    private Image[] f = new Image[5];

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/MapPrestigeOverlay$MapPrestigeOverlayListener.class */
    public interface MapPrestigeOverlayListener extends GameListener {
        void prestigeConfirmed();
    }

    public MapPrestigeOverlay() {
        Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image.setColor(Config.BACKGROUND_COLOR);
        image.getColor().f889a = 0.78f;
        this.f3347b.getTable().add((Table) image).expand().fill();
        this.f3347b.getTable().setTouchable(Touchable.enabled);
        this.f3347b.getTable().addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.components.MapPrestigeOverlay.1
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                MapPrestigeOverlay.this.a(false);
            }
        });
        Group group = new Group();
        group.setOrigin(320.0f, 428.5f);
        this.c.getTable().add((Table) group).size(640.0f, 857.0f);
        this.d = new Group();
        this.d.setOrigin(320.0f, 428.5f);
        this.d.setSize(640.0f, 857.0f);
        group.addActor(this.d);
        this.d.addActor(new QuadActor(new Color(724249599), new float[]{0.0f, 0.0f, 0.0f, 857.0f, 640.0f, 844.0f, 640.0f, 10.0f}));
        QuadActor quadActor = new QuadActor(new Color(724249599), new float[]{0.0f, 3.0f, 0.0f, 187.0f, 252.0f, 190.0f, 249.0f, 0.0f});
        quadActor.setPosition(438.0f, 709.0f);
        this.d.addActor(quadActor);
        QuadActor quadActor2 = new QuadActor(new Color(387389439), new float[]{0.0f, 3.0f, 0.0f, 176.0f, 239.0f, 179.0f, 236.0f, 0.0f});
        quadActor2.setPosition(444.0f, 714.0f);
        this.d.addActor(quadActor2);
        this.e = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        this.e.setSize(236.0f, 173.0f);
        this.e.setPosition(444.0f, 717.0f);
        this.d.addActor(this.e);
        Label label = new Label(Game.i.localeManager.i18n.get("gv_title_PRESTIGE_MODE"), Game.i.assetManager.getLabelStyle(36));
        label.setPosition(40.0f, 785.0f);
        label.setSize(100.0f, 27.0f);
        this.d.addActor(label);
        Label label2 = new Label(Game.i.localeManager.i18n.get("map_prestige_description"), Game.i.assetManager.getLabelStyle(21));
        label2.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        label2.setSize(100.0f, 16.0f);
        label2.setPosition(40.0f, 759.0f);
        this.d.addActor(label2);
        for (int i = 0; i < 5; i++) {
            this.f[i] = new Image(Game.i.assetManager.getDrawable("icon-crown"));
            this.f[i].setSize(32.0f, 32.0f);
            this.f[i].setPosition(40.0f + (36.0f * i), 712.0f);
            this.d.addActor(this.f[i]);
        }
        this.g = new Label("", Game.i.assetManager.getLabelStyle(24));
        this.g.setSize(100.0f, 18.0f);
        this.g.setPosition(227.0f, 719.0f);
        this.g.setColor(MaterialColor.LIGHT_BLUE.P300);
        this.d.addActor(this.g);
        Image image2 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image2.setColor(new Color(623191551));
        image2.setSize(640.0f, 493.0f);
        image2.setPosition(0.0f, 157.0f);
        this.d.addActor(image2);
        Image image3 = new Image(Game.i.assetManager.getDrawable("gradient-top"));
        image3.setSize(640.0f, 16.0f);
        image3.setPosition(0.0f, 634.0f);
        image3.setColor(0.0f, 0.0f, 0.0f, 0.14f);
        this.d.addActor(image3);
        Image image4 = new Image(Game.i.assetManager.getDrawable("gradient-bottom"));
        image4.setSize(640.0f, 16.0f);
        image4.setPosition(0.0f, 157.0f);
        image4.setColor(0.0f, 0.0f, 0.0f, 0.14f);
        this.d.addActor(image4);
        Label label3 = new Label(Game.i.localeManager.i18n.get("map_price"), Game.i.assetManager.getLabelStyle(24));
        label3.setSize(100.0f, 56.0f);
        label3.setPosition(40.0f, 650.0f);
        this.d.addActor(label3);
        this.h = new Table();
        this.h.setPosition(300.0f, 650.0f);
        this.h.setSize(300.0f, 56.0f);
        this.d.addActor(this.h);
        Image image5 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image5.setColor(0.0f, 0.0f, 0.0f, 0.07f);
        image5.setSize(640.0f, 56.0f);
        image5.setPosition(0.0f, 593.0f);
        this.d.addActor(image5);
        Label label4 = new Label(Game.i.localeManager.i18n.get("map_prestige_base_bonus"), Game.i.assetManager.getLabelStyle(24));
        label4.setSize(100.0f, 56.0f);
        label4.setPosition(40.0f, 593.0f);
        label4.setColor(MaterialColor.LIGHT_BLUE.P300);
        this.d.addActor(label4);
        Table table = new Table();
        table.setSize(300.0f, 56.0f);
        table.setPosition(300.0f, 593.0f);
        this.d.addActor(table);
        table.add().height(1.0f).expandX().fillX();
        Label label5 = new Label("50%", Game.i.assetManager.getLabelStyle(24));
        label5.setColor(MaterialColor.LIGHT_BLUE.P300);
        table.add((Table) label5);
        this.i = new Label(Game.i.localeManager.i18n.get("map_prestige_difficulty_bonus"), Game.i.assetManager.getLabelStyle(24));
        this.i.setSize(100.0f, 56.0f);
        this.i.setPosition(40.0f, 537.0f);
        this.d.addActor(this.i);
        this.j = new Table();
        this.j.setSize(300.0f, 56.0f);
        this.j.setPosition(300.0f, 537.0f);
        this.d.addActor(this.j);
        Image image6 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image6.setColor(0.0f, 0.0f, 0.0f, 0.07f);
        image6.setSize(640.0f, 56.0f);
        image6.setPosition(0.0f, 481.0f);
        this.d.addActor(image6);
        this.k = new Label(Game.i.localeManager.i18n.get("map_prestige_no_abilities"), Game.i.assetManager.getLabelStyle(24));
        this.k.setSize(100.0f, 56.0f);
        this.k.setPosition(40.0f, 481.0f);
        this.d.addActor(this.k);
        this.l = new Table();
        this.l.setSize(300.0f, 56.0f);
        this.l.setPosition(300.0f, 481.0f);
        this.d.addActor(this.l);
        this.m = new Label(Game.i.localeManager.i18n.get("map_prestige_no_research"), Game.i.assetManager.getLabelStyle(24));
        this.m.setSize(100.0f, 56.0f);
        this.m.setPosition(40.0f, 425.0f);
        this.d.addActor(this.m);
        this.n = new Table();
        this.n.setSize(300.0f, 56.0f);
        this.n.setPosition(300.0f, 425.0f);
        this.d.addActor(this.n);
        Image image7 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image7.setColor(0.0f, 0.0f, 0.0f, 0.07f);
        image7.setSize(640.0f, 56.0f);
        image7.setPosition(0.0f, 369.0f);
        this.d.addActor(image7);
        this.o = new Label(Game.i.localeManager.i18n.get("map_prestige_walkable_platforms"), Game.i.assetManager.getLabelStyle(24));
        this.o.setSize(100.0f, 56.0f);
        this.o.setPosition(40.0f, 369.0f);
        this.d.addActor(this.o);
        this.p = new Table();
        this.p.setSize(300.0f, 56.0f);
        this.p.setPosition(300.0f, 369.0f);
        this.d.addActor(this.p);
        this.q = new Label(Game.i.localeManager.i18n.get("map_prestige_no_bounty"), Game.i.assetManager.getLabelStyle(24));
        this.q.setSize(100.0f, 56.0f);
        this.q.setPosition(40.0f, 313.0f);
        this.d.addActor(this.q);
        this.r = new Table();
        this.r.setSize(300.0f, 56.0f);
        this.r.setPosition(300.0f, 313.0f);
        this.d.addActor(this.r);
        Image image8 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image8.setColor(0.0f, 0.0f, 0.0f, 0.07f);
        image8.setSize(640.0f, 56.0f);
        image8.setPosition(0.0f, 257.0f);
        this.d.addActor(image8);
        this.s = new Label(Game.i.localeManager.i18n.get("map_prestige_no_miners"), Game.i.assetManager.getLabelStyle(24));
        this.s.setSize(100.0f, 56.0f);
        this.s.setPosition(40.0f, 257.0f);
        this.d.addActor(this.s);
        this.t = new Table();
        this.t.setSize(300.0f, 56.0f);
        this.t.setPosition(300.0f, 257.0f);
        this.d.addActor(this.t);
        Label label6 = new Label(Game.i.localeManager.i18n.get("score"), Game.i.assetManager.getLabelStyle(24));
        label6.setPosition(40.0f, 215.0f);
        label6.setSize(100.0f, 18.0f);
        this.d.addActor(label6);
        this.u = new Table();
        this.u.setSize(250.0f, 34.0f);
        this.u.setPosition(231.0f, 207.0f);
        this.d.addActor(this.u);
        this.v = new Label("", Game.i.assetManager.getLabelStyle(36));
        this.v.setSize(71.0f, 27.0f);
        this.v.setPosition(529.0f, 208.0f);
        this.v.setAlignment(16);
        this.d.addActor(this.v);
        this.w = new Label("", Game.i.assetManager.getLabelStyle(24));
        this.w.setSize(71.0f, 19.0f);
        this.w.setAlignment(16);
        this.w.setPosition(529.0f, 178.0f);
        this.w.setColor(MaterialColor.LIGHT_BLUE.P300);
        this.d.addActor(this.w);
        QuadActor quadActor3 = new QuadActor(new Color(0.0f, 0.0f, 0.0f, 0.28f), new float[]{0.0f, 0.0f, 0.0f, 20.0f, 442.0f, 20.0f, 439.0f, 0.0f});
        quadActor3.setPosition(39.0f, 178.0f);
        this.d.addActor(quadActor3);
        this.x = new QuadActor(MaterialColor.LIGHT_BLUE.P500, new float[]{0.0f, 0.0f, 0.0f, 20.0f, 442.0f, 20.0f, 439.0f, 0.0f});
        this.x.setPosition(39.0f, 178.0f);
        this.d.addActor(this.x);
        Label label7 = new Label(Game.i.localeManager.i18n.get("map_prestige_total"), Game.i.assetManager.getLabelStyle(30));
        label7.setSize(71.0f, 22.0f);
        label7.setPosition(40.0f, 105.0f);
        this.d.addActor(label7);
        this.y = new Table();
        this.y.setPosition(228.0f, 95.0f);
        this.y.setSize(372.0f, 45.0f);
        this.d.addActor(this.y);
        ComplexButton complexButton = new ComplexButton(Game.i.localeManager.i18n.get("back"), Game.i.assetManager.getLabelStyle(30), () -> {
            a(false);
        });
        complexButton.setSize(255.0f, 93.0f);
        complexButton.setBackground(Game.i.assetManager.getDrawable("ui-map-prestige-button-left"), 0.0f, 0.0f, 255.0f, 93.0f);
        complexButton.setIconPositioned(Game.i.assetManager.getDrawable("icon-triangle-left"), 18.0f, 19.0f, 48.0f, 48.0f);
        complexButton.setLabel(77.0f, 30.0f, 100.0f, 23.0f, 8);
        complexButton.setPosition(-13.0f, -9.0f);
        this.d.addActor(complexButton);
        this.z = new ComplexButton(Game.i.localeManager.i18n.get("sell_button"), Game.i.assetManager.getLabelStyle(30), () -> {
            Dialog.i().showConfirm(Game.i.localeManager.i18n.format("map_prestige_confirm", Integer.valueOf(this.A.getFinalPrestigeTokens())), () -> {
                Game.i.userMapManager.prestigeSellMap(this.A);
                show(this.A);
                this.listeners.begin();
                for (int i2 = 0; i2 < this.listeners.size(); i2++) {
                    this.listeners.get(i2).prestigeConfirmed();
                }
                this.listeners.end();
            });
            Dialog.i().makeConfirmButtonDisabled(2);
        });
        this.z.setSize(247.0f, 93.0f);
        this.z.setBackground(Game.i.assetManager.getDrawable("ui-map-prestige-button-right"), 0.0f, 0.0f, 247.0f, 93.0f);
        this.z.setIconPositioned(Game.i.assetManager.getDrawable("icon-dollar"), 21.0f, 19.0f, 48.0f, 48.0f);
        this.z.setLabel(80.0f, 30.0f, 100.0f, 23.0f, 8);
        this.z.setBackgroundColors(MaterialColor.GREEN.P800, MaterialColor.GREEN.P900, MaterialColor.GREEN.P700, MaterialColor.GREY.P800);
        this.z.setPosition(407.0f, -9.0f);
        this.d.addActor(this.z);
    }

    public void show(MapPrestigeConfig mapPrestigeConfig) {
        f3346a.i(mapPrestigeConfig.describe(), new Object[0]);
        this.A = mapPrestigeConfig;
        UserMap userMap = Game.i.userMapManager.getUserMap(mapPrestigeConfig.userMapId);
        int totalBonus = mapPrestigeConfig.getTotalBonus();
        int crownsCount = mapPrestigeConfig.getCrownsCount();
        if (userMap != null) {
            this.e.setVisible(true);
            this.e.setDrawable(new TextureRegionDrawable(userMap.map.getPreview().getTextureRegion()));
        } else {
            this.e.setVisible(false);
            f3346a.e("user map " + mapPrestigeConfig.userMapId + " not found for preview", new Object[0]);
        }
        for (int i = 0; i < this.f.length; i++) {
            if (crownsCount >= i + 1) {
                this.f[i].setColor(MaterialColor.LIGHT_BLUE.P500);
            } else {
                this.f[i].setColor(0.0f, 0.0f, 0.0f, 0.56f);
            }
        }
        this.g.setText(totalBonus + "%");
        this.h.clear();
        this.h.add().height(1.0f).expandX().fillX();
        this.h.add((Table) new Image(Game.i.assetManager.getDrawable("icon-crown"))).size(32.0f).padRight(12.0f);
        this.h.add((Table) new Label(new StringBuilder().append((int) mapPrestigeConfig.mapPrice).toString(), Game.i.assetManager.getLabelStyle(24)));
        Label label = new Label("." + ((int) StrictMath.round((mapPrestigeConfig.mapPrice % 1.0d) * 1000.0d)), Game.i.assetManager.getLabelStyle(21));
        label.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        this.h.add((Table) label).padLeft(4.0f);
        this.i.setText(Game.i.localeManager.i18n.get("map_prestige_difficulty_bonus") + " (" + mapPrestigeConfig.averageDifficulty + "%)");
        if (mapPrestigeConfig.getDifficultyBonus() == 0) {
            this.i.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        } else {
            this.i.setColor(MaterialColor.LIGHT_BLUE.P300);
        }
        this.j.clear();
        this.j.add().height(1.0f).expandX().fillX();
        Label label2 = new Label(mapPrestigeConfig.getDifficultyBonus() + "%", Game.i.assetManager.getLabelStyle(24));
        label2.setColor(MaterialColor.LIGHT_BLUE.P500);
        this.j.add((Table) label2);
        if (mapPrestigeConfig.getDifficultyBonus() < 50) {
            Label label3 = new Label("/ 50%", Game.i.assetManager.getLabelStyle(21));
            label3.setColor(1.0f, 1.0f, 1.0f, 0.28f);
            this.j.add((Table) label3).padLeft(6.0f);
        }
        this.l.clear();
        this.l.add().height(1.0f).expandX().fillX();
        Label label4 = new Label("10%", Game.i.assetManager.getLabelStyle(24));
        this.l.add((Table) label4);
        if (mapPrestigeConfig.noAbilities) {
            this.k.setColor(MaterialColor.LIGHT_BLUE.P300);
            label4.setColor(MaterialColor.LIGHT_BLUE.P300);
        } else {
            this.k.setColor(1.0f, 1.0f, 1.0f, 0.28f);
            label4.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        }
        this.n.clear();
        this.n.add().height(1.0f).expandX().fillX();
        Label label5 = new Label("40%", Game.i.assetManager.getLabelStyle(24));
        this.n.add((Table) label5);
        if (mapPrestigeConfig.noResearch) {
            this.m.setColor(MaterialColor.LIGHT_BLUE.P300);
            label5.setColor(MaterialColor.LIGHT_BLUE.P300);
        } else {
            this.m.setColor(1.0f, 1.0f, 1.0f, 0.28f);
            label5.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        }
        this.p.clear();
        this.p.add().height(1.0f).expandX().fillX();
        Label label6 = new Label("10%", Game.i.assetManager.getLabelStyle(24));
        this.p.add((Table) label6);
        if (mapPrestigeConfig.walkablePlatforms) {
            this.o.setColor(MaterialColor.LIGHT_BLUE.P300);
            label6.setColor(MaterialColor.LIGHT_BLUE.P300);
        } else {
            this.o.setColor(1.0f, 1.0f, 1.0f, 0.28f);
            label6.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        }
        this.r.clear();
        this.r.add().height(1.0f).expandX().fillX();
        Label label7 = new Label("20%", Game.i.assetManager.getLabelStyle(24));
        this.r.add((Table) label7);
        if (mapPrestigeConfig.noBounty) {
            this.q.setColor(MaterialColor.LIGHT_BLUE.P300);
            label7.setColor(MaterialColor.LIGHT_BLUE.P300);
        } else {
            this.q.setColor(1.0f, 1.0f, 1.0f, 0.28f);
            label7.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        }
        this.t.clear();
        this.t.add().height(1.0f).expandX().fillX();
        Label label8 = new Label("20%", Game.i.assetManager.getLabelStyle(24));
        this.t.add((Table) label8);
        if (mapPrestigeConfig.noMiners) {
            this.s.setColor(MaterialColor.LIGHT_BLUE.P300);
            label8.setColor(MaterialColor.LIGHT_BLUE.P300);
        } else {
            this.s.setColor(1.0f, 1.0f, 1.0f, 0.28f);
            label8.setColor(1.0f, 1.0f, 1.0f, 0.28f);
        }
        float maxPrestigeScore = ((float) mapPrestigeConfig.score) / mapPrestigeConfig.getMaxPrestigeScore();
        float f = maxPrestigeScore;
        if (maxPrestigeScore > 1.0f) {
            f = 1.0f;
        }
        this.x.setVertexPositions(new float[]{0.0f, 0.0f, 0.0f, 20.0f, (439.0f * f) + 3.0f, 20.0f, 439.0f * f, 0.0f});
        int round = StrictMath.round(f * 100.0f);
        this.v.setText("x" + (round / 100) + "." + ((round % 100) / 10) + (round % 10));
        this.w.setText("=" + totalBonus + "%");
        this.u.clear();
        this.u.add().height(1.0f).expandX().fillX();
        Label label9 = new Label(StringFormatter.commaSeparatedNumber(mapPrestigeConfig.score), Game.i.assetManager.getLabelStyle(24));
        label9.setColor(MaterialColor.LIGHT_BLUE.P300);
        this.u.add((Table) label9);
        this.u.add((Table) new Label("/ " + ((Object) StringFormatter.commaSeparatedNumber(mapPrestigeConfig.getMaxPrestigeScore())), Game.i.assetManager.getLabelStyle(21))).padLeft(12.0f);
        this.y.clear();
        this.y.add().height(1.0f).expandX().fillX();
        Label label10 = new Label(((int) mapPrestigeConfig.mapPrice) + "." + StrictMath.round((mapPrestigeConfig.mapPrice % 1.0d) * 1000.0d) + " + " + totalBonus + "% = ", Game.i.assetManager.getLabelStyle(24));
        label10.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        this.y.add((Table) label10);
        this.y.add((Table) new Image(Game.i.assetManager.getDrawable("prestige-token"))).size(48.0f);
        Label label11 = new Label(new StringBuilder().append(mapPrestigeConfig.getFinalPrestigeTokens()).toString(), Game.i.assetManager.getLabelStyle(36));
        label11.setColor(MaterialColor.LIGHT_BLUE.P300);
        this.y.add((Table) label11).padLeft(8.0f);
        this.z.setEnabled(mapPrestigeConfig.getFinalPrestigeTokens() > 0 && Game.i.userMapManager.getUserMap(mapPrestigeConfig.userMapId) != null);
        a(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        if (z) {
            UiUtils.bouncyShowOverlay(this.f3347b.getTable(), this.c.getTable(), this.d);
        } else {
            UiUtils.bouncyHideOverlay(this.f3347b.getTable(), this.c.getTable(), this.d);
        }
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        Game.i.uiManager.removeLayer(this.f3347b);
        Game.i.uiManager.removeLayer(this.c);
    }
}
