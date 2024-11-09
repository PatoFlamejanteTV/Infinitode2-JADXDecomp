package com.prineside.tdi2.ui.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.math.Rectangle;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.events.game.CoreTileLevelUp;
import com.prineside.tdi2.events.game.CoreTileUpgradeInstall;
import com.prineside.tdi2.events.game.MapElementSelect;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.tiles.CoreTile;
import com.prineside.tdi2.ui.actors.ExpLine;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.ParticlesCanvas;
import com.prineside.tdi2.ui.actors.QuadActor;
import com.prineside.tdi2.ui.actors.SideMenu;
import com.prineside.tdi2.utils.StringFormatter;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/CoreMenu.class */
public class CoreMenu {

    /* renamed from: a, reason: collision with root package name */
    private static final Rectangle f3278a;

    /* renamed from: b, reason: collision with root package name */
    private final SideMenu f3279b;
    private final SideMenu.SideMenuContainer c;
    private boolean d;
    private QuadActor e;
    private Table f;
    private Group g;
    private Label h;
    private Label i;
    private Label j;
    private Label k;
    private Label l;
    private Label m;
    private Label n;
    private Group o;
    private ParticlesCanvas p;
    private ParticleEffect q;
    private ExpLine r;
    private GameSystemProvider v;
    private static final StringBuilder x;
    private static final Color y;
    private static final Color z;
    private int s = -1;
    private int t = -1;
    private int u = -1;
    private final _SideMenuListener w = new _SideMenuListener(this, 0);
    private final Runnable A = this::b;

    static {
        new Color(623191551);
        new Color(724249599);
        f3278a = new Rectangle(40.0f, 954.0f, 520.0f, 66.0f);
        x = new StringBuilder();
        y = new Color();
        z = new Color();
        new Color();
    }

    public CoreMenu(SideMenu sideMenu, GameSystemProvider gameSystemProvider) {
        this.v = gameSystemProvider;
        this.f3279b = sideMenu;
        this.c = sideMenu.createContainer("CoreMenu");
        int scaledViewportHeight = Game.i.settingsManager.getScaledViewportHeight() - Config.VIEWPORT_HEIGHT;
        this.e = new QuadActor(new float[]{0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f}, Color.WHITE);
        this.e.setSize(600.0f, 156.0f);
        this.e.setPosition(0.0f, 924.0f + scaledViewportHeight);
        this.c.addActor(this.e);
        this.f = new Table();
        this.f.setPosition(40.0f, 994.0f + scaledViewportHeight);
        this.f.setSize(520.0f, 26.0f);
        this.c.addActor(this.f);
        this.g = new Group();
        this.g.setName("core_menu_experience");
        this.g.setTransform(false);
        this.g.setPosition(f3278a.x, f3278a.y + scaledViewportHeight);
        this.g.setSize(f3278a.width, f3278a.height);
        this.g.setOrigin(f3278a.width / 2.0f, f3278a.height / 2.0f);
        this.c.addActor(this.g);
        this.k = new Label("", new Label.LabelStyle(Game.i.assetManager.getFont(21), Color.WHITE));
        this.k.setColor(0.0f, 0.0f, 0.0f, 0.28f);
        this.k.setSize(159.0f, 24.0f);
        this.k.setPosition(50.0f, (954.0f + scaledViewportHeight) - 2.0f);
        this.k.setAlignment(8);
        this.c.addActor(this.k);
        this.j = new Label("", new Label.LabelStyle(Game.i.assetManager.getFont(21), Color.WHITE));
        this.j.setColor(Color.WHITE);
        this.j.setSize(159.0f, 24.0f);
        this.j.setPosition(48.0f, 954.0f + scaledViewportHeight);
        this.j.setAlignment(8);
        this.c.addActor(this.j);
        this.h = new Label("L10", new Label.LabelStyle(Game.i.assetManager.getFont(36), Color.WHITE));
        this.h.setSize(40.0f, 26.0f);
        this.h.setPosition(480.0f, 40.0f);
        this.h.setAlignment(16);
        this.g.addActor(this.h);
        this.r = new ExpLine();
        this.r.setPosition(0.0f, 0.0f);
        this.g.addActor(this.r);
        this.i = new Label("53 / 90 XP", new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE));
        this.i.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        this.i.setPosition(0.0f, 0.0f);
        this.i.setSize(520.0f, 24.0f);
        this.i.setAlignment(16);
        this.g.addActor(this.i);
        Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image.setSize(600.0f, 52.0f);
        image.setColor(0.0f, 0.0f, 0.0f, 0.28f);
        image.setPosition(0.0f, 872.0f + scaledViewportHeight);
        this.c.addActor(image);
        Label label = new Label(Game.i.localeManager.i18n.get("tower_stat_EXPERIENCE_GENERATION"), Game.i.assetManager.getLabelStyle(24));
        label.setPosition(40.0f, 872.0f + scaledViewportHeight);
        label.setSize(100.0f, 52.0f);
        this.c.addActor(label);
        Label label2 = new Label("/s", Game.i.assetManager.getLabelStyle(24));
        label2.setPosition(0.0f, 872.0f + scaledViewportHeight);
        label2.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        label2.setAlignment(16);
        label2.setSize(560.0f, 52.0f);
        this.c.addActor(label2);
        this.l = new Label("2.5", Game.i.assetManager.getLabelStyle(30));
        this.l.setPosition(0.0f, 872.0f + scaledViewportHeight);
        this.l.setAlignment(16);
        this.l.setSize(528.0f, 52.0f);
        this.c.addActor(this.l);
        this.o = new Group();
        this.o.setTransform(false);
        this.o.setPosition(0.0f, 0.0f);
        this.o.setSize(600.0f, Game.i.settingsManager.getScaledViewportHeight() - 200.0f);
        this.c.addActor(this.o);
        this.n = new Label("", Game.i.assetManager.getLabelStyle(60));
        this.n.setPosition(0.0f, 142.0f);
        this.n.setSize(600.0f, 44.0f);
        this.n.setAlignment(1);
        this.c.addActor(this.n);
        this.m = new Label(Game.i.localeManager.i18n.get("upgrade_points").toUpperCase(), Game.i.assetManager.getLabelStyle(24));
        this.m.setPosition(0.0f, 104.0f);
        this.m.setSize(600.0f, 20.0f);
        this.m.setAlignment(1);
        this.c.addActor(this.m);
        this.q = new ParticleEffect();
        this.q.load(Gdx.files.internal("particles/core-menu-upgrade-points-highlight.prt"), Game.i.assetManager.getTextureRegion("particle-triangle").getAtlas());
        this.q.setEmittersCleanUpBlendFunction(false);
        this.p = new ParticlesCanvas();
        this.p.setSize(600.0f, 64.0f);
        this.p.setPosition(0.0f, 132.0f);
        this.c.addActor(this.p);
        this.p.addParticle(this.q, 300.0f, 32.0f);
        this.p.setVisible(false);
        sideMenu.addListener(this.w);
        gameSystemProvider.events.getListeners(CoreTileLevelUp.class).add(coreTileLevelUp -> {
            if (this.d && gameSystemProvider._gameMapSelection.getSelectedTile() == coreTileLevelUp.getCoreTile() && this.u != a()) {
                Game.i.uiManager.runOnStageActOnce(this.A);
            }
        });
        gameSystemProvider.events.getListeners(CoreTileUpgradeInstall.class).add(coreTileUpgradeInstall -> {
            if (this.d && gameSystemProvider._gameMapSelection.getSelectedTile() == coreTileUpgradeInstall.getCoreTile()) {
                Game.i.uiManager.runOnStageActOnce(this.A);
            }
        });
        gameSystemProvider.events.getListeners(MapElementSelect.class).add(mapElementSelect -> {
            Tile selectedTile = gameSystemProvider._gameMapSelection.getSelectedTile();
            if (selectedTile != null && selectedTile.type == TileType.CORE) {
                if (this.u != a()) {
                    this.s = -1;
                    this.t = -1;
                    b();
                }
                a(true);
                return;
            }
            a(false);
        });
        this.c.hide();
    }

    private void a(boolean z2) {
        Tile selectedTile;
        if (this.d != z2) {
            this.d = z2;
            if (z2) {
                this.c.show();
                b();
                if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.CORE_HINT_SHOWN) == 0.0d && (selectedTile = this.v._gameMapSelection.getSelectedTile()) != null && selectedTile.type == TileType.CORE) {
                    Group generateUiIcon = ((CoreTile) selectedTile).generateUiIcon(96.0f);
                    generateUiIcon.setOrigin(48.0f, 48.0f);
                    this.v._gameUi.uiElementsEmphasizer.show(generateUiIcon, new Rectangle(-48.0f, -48.0f, 96.0f, 96.0f), Game.i.localeManager.i18n.get("tile_name_CORE"), Game.i.localeManager.i18n.get("tile_description_CORE"), null);
                    Game.i.settingsManager.setCustomValue(SettingsManager.CustomValueType.CORE_HINT_SHOWN, 1.0d);
                    return;
                }
                return;
            }
            this.c.hide();
            this.s = -1;
            this.t = -1;
            this.f3279b.hideSideTooltip();
        }
    }

    private int a() {
        int i = 1;
        Tile selectedTile = this.v._gameMapSelection.getSelectedTile();
        if (selectedTile != null && selectedTile.type == TileType.CORE) {
            CoreTile coreTile = (CoreTile) selectedTile;
            int upgradeCols = coreTile.getUpgradeCols();
            int upgradeRows = coreTile.getUpgradeRows();
            int freeUpgradePoints = coreTile.getFreeUpgradePoints();
            i = freeUpgradePoints + 31;
            int money = this.v.gameState.getMoney();
            for (int i2 = 0; i2 < upgradeRows; i2++) {
                for (int i3 = 0; i3 < upgradeCols; i3++) {
                    CoreTile.Upgrade upgrade = coreTile.getUpgrade(i3, i2);
                    if (upgrade != null) {
                        i = (((i * 31) + (upgrade.isAction ? 1 : -1)) * 31) + (upgrade.isAction ? upgrade.getActionType().ordinal() : upgrade.getGameValueType().ordinal());
                        int upgradeInstallLevel = coreTile.getUpgradeInstallLevel(i3, i2);
                        if (upgradeInstallLevel < upgrade.upgradeLevels.size) {
                            CoreTile.Upgrade.UpgradeLevel upgradeLevel = upgrade.upgradeLevels.items[upgradeInstallLevel];
                            if (upgrade.costsCoins) {
                                i = (i * 31) + (upgradeLevel.price <= money ? 1 : 0);
                            } else {
                                i = (i * 31) + (upgradeLevel.price <= freeUpgradePoints ? 1 : 0);
                            }
                        }
                    } else {
                        i = (i * 31) - 131;
                    }
                }
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x07a5, code lost:            if (r0.getUpgradeInstallLevel(r18, r17) <= 0) goto L96;     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x07a8, code lost:            r0.setColor(com.prineside.tdi2.ui.components.CoreMenu.y);     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x07bd, code lost:            r0.addActor(r0);     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x07b3, code lost:            r0.setColor(0.0f, 0.0f, 0.0f, 0.28f);     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void b() {
        /*
            Method dump skipped, instructions count: 3647
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.ui.components.CoreMenu.b():void");
    }

    private void c() {
        Tile selectedTile = this.v._gameMapSelection.getSelectedTile();
        if (selectedTile != null && selectedTile.type == TileType.CORE) {
            CoreTile coreTile = (CoreTile) selectedTile;
            x.setLength(0);
            x.append("L").append(coreTile.getLevel());
            this.h.setText(x);
            this.r.setColor(Game.i.tileManager.F.CORE.getTierColor(coreTile.getTier()));
            this.r.setCoeff(coreTile.getCurrentLevelExperience() / coreTile.getNextLevelExperience());
            this.i.setText(StrictMath.round(coreTile.getCurrentLevelExperience()) + " / " + StrictMath.round(coreTile.getNextLevelExperience()) + " XP");
            if (coreTile.doubleSpeedTimeLeft > 0.0f) {
                x.setLength(0);
                x.append("x2: ");
                x.append((CharSequence) StringFormatter.digestTime(StrictMath.round(coreTile.doubleSpeedTimeLeft)));
                this.j.setText(x);
                this.k.setText(x);
                this.j.setVisible(true);
                this.k.setVisible(true);
                return;
            }
            this.j.setVisible(false);
            this.k.setVisible(false);
        }
    }

    public void draw(float f) {
        if (this.d) {
            c();
            if (this.u != a()) {
                b();
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/CoreMenu$_SideMenuListener.class */
    private class _SideMenuListener extends SideMenu.SideMenuListener.SideMenuListenerAdapter {
        private _SideMenuListener() {
        }

        /* synthetic */ _SideMenuListener(CoreMenu coreMenu, byte b2) {
            this();
        }

        @Override // com.prineside.tdi2.ui.actors.SideMenu.SideMenuListener.SideMenuListenerAdapter, com.prineside.tdi2.ui.actors.SideMenu.SideMenuListener
        public void offscreenChanged() {
            CoreMenu.this.s = -1;
            CoreMenu.this.t = -1;
            CoreMenu.this.b();
        }
    }
}
