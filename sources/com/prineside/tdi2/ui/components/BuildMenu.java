package com.prineside.tdi2.ui.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectSet;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Miner;
import com.prineside.tdi2.Modifier;
import com.prineside.tdi2.SpaceTileBonus;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.GeneralizedTowerStatType;
import com.prineside.tdi2.enums.MinerType;
import com.prineside.tdi2.enums.ModifierType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.enums.SpaceTileBonusType;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.InputListener;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.tiles.PlatformTile;
import com.prineside.tdi2.tiles.SourceTile;
import com.prineside.tdi2.ui.actors.AimStrategySelector;
import com.prineside.tdi2.ui.actors.EffectTooltip;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.PaddedImageButton;
import com.prineside.tdi2.ui.actors.SideMenu;
import com.prineside.tdi2.ui.shared.TooltipsOverlay;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.StringFormatter;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/BuildMenu.class */
public class BuildMenu implements Disposable {
    private final SideMenu h;
    private TabType i;
    private boolean j;
    private final Label k;
    private final Label l;
    private final Table m;
    private final SideMenu.SideMenuContainer n;
    private final Group o;
    private final Group p;
    private final Group q;
    private final Image r;
    private final Image s;
    private PaddedImageButton t;
    private final AimStrategySelector u;
    private final TileResources v;
    private final Label w;
    private final Group x;
    private final Group y;
    private final Group z;
    private Group A;
    private Group B;
    private BuildButton C;
    private boolean D;
    private boolean E;
    private Label F;
    private Label G;
    private Group H;
    private Group I;
    private Group J;
    private BuildButton Q;
    private boolean R;
    private final GameSystemProvider V;

    /* renamed from: a, reason: collision with root package name */
    private static final Color f3262a = new Color(1.0f, 1.0f, 1.0f, 0.07f);

    /* renamed from: b, reason: collision with root package name */
    private static final Color f3263b = Color.WHITE;
    private static final Color c = MaterialColor.RED.P400;
    private static final Color d = MaterialColor.LIGHT_BLUE.P800;
    private static final Color e = MaterialColor.LIGHT_BLUE.P700;
    private static final Color f = MaterialColor.LIGHT_BLUE.P900;
    private static final Color g = MaterialColor.RED.P800;
    private static final StringBuilder P = new StringBuilder();
    private static final ObjectSet<SpaceTileBonusType> Y = new ObjectSet<>();
    private Group[] K = new Group[ResourceType.values.length];
    private Label[] L = new Label[ResourceType.values.length];
    private Image[][] M = new Image[GeneralizedTowerStatType.values.length][5];
    private Label[] N = new Label[EnemyType.mainEnemyTypes.length];
    private Enemy[] O = new Enemy[EnemyType.mainEnemyTypes.length];
    private final ObjectMap<TowerType, BuildButton> S = new ObjectMap<>();
    private final ObjectMap<ModifierType, BuildButton> T = new ObjectMap<>();
    private final ObjectMap<MinerType, BuildButton> U = new ObjectMap<>();
    private final _SideMenuListener W = new _SideMenuListener(this, 0);
    private final Runnable X = this::m;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/BuildMenu$TabType.class */
    public enum TabType {
        TOWERS,
        MODIFIERS,
        MINERS
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x170d, code lost:            r0.addActor(new com.prineside.tdi2.ui.actors.HotKeyHintLabel(com.prineside.tdi2.Game.i.settingsManager.getHotKey(r23), 12.0f, 102.0f, 8));     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x11cd, code lost:            r0.addActor(new com.prineside.tdi2.ui.actors.HotKeyHintLabel(com.prineside.tdi2.Game.i.settingsManager.getHotKey(r28), 12.0f, 102.0f, 8));     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x1459, code lost:            r0.addActor(new com.prineside.tdi2.ui.actors.HotKeyHintLabel(com.prineside.tdi2.Game.i.settingsManager.getHotKey(r17), 12.0f, 102.0f, 8));     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public BuildMenu(com.prineside.tdi2.ui.actors.SideMenu r14, final com.prineside.tdi2.GameSystemProvider r15) {
        /*
            Method dump skipped, instructions count: 6238
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.ui.components.BuildMenu.<init>(com.prineside.tdi2.ui.actors.SideMenu, com.prineside.tdi2.GameSystemProvider):void");
    }

    public void postSetup() {
        m();
        a(TabType.TOWERS);
        n();
    }

    private void i() {
        Tile selectedTile = this.V._gameMapSelection.getSelectedTile();
        if (selectedTile != null) {
            boolean minersAndEnergyAvailable = Game.i.minerManager.minersAndEnergyAvailable();
            if (this.V.gameValue.getIntValue(GameValueType.MINER_COUNT_SCALAR) != 0 || this.V.gameValue.getIntValue(GameValueType.MINER_COUNT_VECTOR) != 0 || this.V.gameValue.getIntValue(GameValueType.MINER_COUNT_MATRIX) != 0 || this.V.gameValue.getIntValue(GameValueType.MINER_COUNT_TENSOR) != 0 || this.V.gameValue.getIntValue(GameValueType.MINER_COUNT_INFIAR) != 0) {
                minersAndEnergyAvailable = true;
            }
            if (selectedTile.type != TileType.SOURCE || minersAndEnergyAvailable) {
                this.k.setText(StringFormatter.toUpperCase(selectedTile.getTitle()));
                this.l.setText(selectedTile.getDescription());
                if (selectedTile.type != TileType.PLATFORM) {
                    if (selectedTile.type == TileType.SOURCE) {
                        SourceTile sourceTile = (SourceTile) selectedTile;
                        if (this.i != TabType.MINERS) {
                            a(TabType.MINERS);
                        }
                        this.m.setVisible(false);
                        this.v.setVisible(true);
                        this.v.setTile(sourceTile);
                        k();
                        return;
                    }
                    return;
                }
                PlatformTile platformTile = (PlatformTile) selectedTile;
                if (this.i != TabType.TOWERS && this.i != TabType.MODIFIERS) {
                    a(TabType.TOWERS);
                }
                this.v.setVisible(false);
                this.m.setVisible(true);
                this.m.clearChildren();
                if (platformTile.bonusType != null && platformTile.bonusLevel > 0) {
                    EffectTooltip effectTooltip = new EffectTooltip(Game.i.assetManager.getDrawable(SpaceTileBonus.getIconName(platformTile.bonusType)), SpaceTileBonus.getDetailedName(platformTile.bonusType, platformTile.bonusLevel));
                    effectTooltip.setHint(Game.i.localeManager.i18n.get("tile_effect"));
                    effectTooltip.setColor(SpaceTileBonus.getBrightColor(platformTile.bonusType));
                    this.m.add(effectTooltip);
                }
                k();
                return;
            }
            this.k.setText(Game.i.localeManager.i18n.get("unknown").toUpperCase());
            this.l.setText(Game.i.localeManager.i18n.get("description_not_available"));
            a((TabType) null);
            this.m.setVisible(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(BuildButton buildButton) {
        if (this.Q != null) {
            this.Q.a(false);
        }
        this.Q = buildButton;
        if (buildButton != null) {
            buildButton.a(true);
        }
        k();
        if (buildButton != null) {
            b(this.Q);
            a(true);
        } else {
            a(false);
        }
    }

    private void j() {
        this.B.clearActions();
        this.A.clearActions();
        boolean z = this.D && this.R;
        boolean z2 = z;
        if (!z || this.E) {
            this.B.setTouchable(Touchable.disabled);
            if (Game.i.settingsManager.isUiAnimationsEnabled()) {
                this.B.addAction(Actions.sequence(Actions.moveTo(0.0f, 150.0f, 0.1f), Actions.hide()));
            } else {
                this.B.setPosition(0.0f, 150.0f);
                this.B.setVisible(false);
            }
        } else {
            this.B.setTouchable(Touchable.childrenOnly);
            this.B.setVisible(true);
            if (Game.i.settingsManager.isUiAnimationsEnabled()) {
                this.B.addAction(Actions.moveTo(-140.0f, 168.0f, 0.1f));
            } else {
                this.B.setPosition(-140.0f, 168.0f);
            }
        }
        if (z2 && this.E) {
            this.A.setTouchable(Touchable.enabled);
            this.A.setVisible(true);
            if (Game.i.settingsManager.isUiAnimationsEnabled()) {
                this.A.addAction(Actions.moveTo(-472.0f, 214.0f, 0.15f));
                return;
            } else {
                this.A.setPosition(-472.0f, 214.0f);
                return;
            }
        }
        this.A.setTouchable(Touchable.disabled);
        if (Game.i.settingsManager.isUiAnimationsEnabled()) {
            this.A.addAction(Actions.sequence(Actions.moveTo(0.0f, 214.0f, 0.15f), Actions.hide()));
        } else {
            this.A.setPosition(0.0f, 214.0f);
            this.A.setVisible(false);
        }
    }

    private void a(boolean z) {
        if (this.D != z) {
            this.D = z;
            j();
        }
    }

    private void b(boolean z) {
        if (this.E != z) {
            this.E = z;
            j();
        }
    }

    private void b(BuildButton buildButton) {
        if (buildButton != null && this.C != buildButton) {
            this.H.setVisible(false);
            this.I.setVisible(false);
            this.J.setVisible(false);
            if (buildButton.k == TabType.TOWERS) {
                this.H.setVisible(true);
                Tower.Factory<? extends Tower> factory = Game.i.towerManager.getFactory(buildButton.l);
                this.F.setText(factory.getTitle());
                this.G.setText(factory.getDescription());
                this.H.setVisible(true);
                for (GeneralizedTowerStatType generalizedTowerStatType : GeneralizedTowerStatType.values) {
                    int generalizedStat = factory.getGeneralizedStat(generalizedTowerStatType);
                    for (int i = 0; i < 5; i++) {
                        this.M[generalizedTowerStatType.ordinal()][i].setVisible(i + 1 <= generalizedStat);
                    }
                }
                int i2 = 0;
                int length = EnemyType.mainEnemyTypes.length;
                for (int i3 = 0; i3 < length; i3++) {
                    int towerDamageMultiplier = (int) (this.O[i2].getTowerDamageMultiplier(buildButton.l) * 100.0f);
                    P.setLength(0);
                    P.append(towerDamageMultiplier);
                    this.N[i2].setText(P);
                    if (towerDamageMultiplier <= 0) {
                        this.N[i2].setColor(MaterialColor.RED.P500);
                    } else if (towerDamageMultiplier < 100) {
                        this.N[i2].setColor(MaterialColor.ORANGE.P600);
                    } else if (towerDamageMultiplier > 100) {
                        this.N[i2].setColor(MaterialColor.GREEN.P500);
                    } else {
                        this.N[i2].setColor(MaterialColor.YELLOW.P500);
                    }
                    i2++;
                }
            } else if (buildButton.k == TabType.MINERS) {
                Miner.Factory<? extends Miner> factory2 = Game.i.minerManager.getFactory(buildButton.m);
                this.F.setText(factory2.getTitle());
                this.G.setText(factory2.getDescription());
                this.I.setVisible(true);
                for (ResourceType resourceType : ResourceType.values) {
                    if (factory2.canMineResource(resourceType)) {
                        this.K[resourceType.ordinal()].setVisible(true);
                        this.L[resourceType.ordinal()].setText(StringFormatter.compactNumber(factory2.getBaseMiningSpeed(this.V.gameValue), true));
                    } else {
                        this.K[resourceType.ordinal()].setVisible(false);
                    }
                }
            } else if (buildButton.k == TabType.MODIFIERS) {
                Modifier.Factory<? extends Modifier> factory3 = Game.i.modifierManager.getFactory(buildButton.n);
                this.F.setText(factory3.getTitle());
                this.G.setText(factory3.getDescription(this.V.gameValue));
                this.J.setVisible(true);
            }
            this.C = buildButton;
        }
    }

    private void k() {
        this.V.map.hideTowerRangeHint();
        Y.clear();
        if (this.R && this.Q != null && this.Q.k == TabType.TOWERS) {
            Tile selectedTile = this.V._gameMapSelection.getSelectedTile();
            Tower create = Game.i.towerManager.getFactory(this.Q.l).create();
            create.setRegistered(this.V);
            if (selectedTile.type == TileType.PLATFORM && ((PlatformTile) selectedTile).building == null) {
                create.setTile((PlatformTile) selectedTile);
                create.updateCache();
                if (create.rangeInPixels != 0.0f) {
                    this.V.map.showTowerRangeHint(selectedTile.center.x, selectedTile.center.y, create.minRangeInPixels, create.rangeInPixels);
                }
                create.setTile(null);
            }
            for (SpaceTileBonusType spaceTileBonusType : SpaceTileBonusType.values) {
                if (Game.i.towerManager.getFactory(create.type).receivesSpaceTileBonus(spaceTileBonusType)) {
                    Y.add(spaceTileBonusType);
                }
            }
            create.setUnregistered();
        }
        boolean z = false;
        ObjectSet.ObjectSetIterator<SpaceTileBonusType> it = Y.iterator();
        while (it.hasNext()) {
            SpaceTileBonusType next = it.next();
            if (!this.V._mapRendering.spaceTileBonusesToDrawColoredOnFreeTiles.contains(next)) {
                this.V._mapRendering.spaceTileBonusesToDrawColoredOnFreeTiles.add(next);
                z = true;
            }
        }
        ObjectSet.ObjectSetIterator<SpaceTileBonusType> it2 = this.V._mapRendering.spaceTileBonusesToDrawColoredOnFreeTiles.iterator();
        ObjectSet.ObjectSetIterator<SpaceTileBonusType> it3 = it2.iterator();
        while (it3.hasNext()) {
            if (!Y.contains(it3.next())) {
                it2.remove();
                z = true;
            }
        }
        if (z) {
            this.V._mapRendering.forceTilesRedraw(false);
        }
        j();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        this.R = !this.h.isOffscreen() && this.j;
        k();
    }

    private void a(TabType tabType) {
        this.o.setVisible(false);
        this.p.setVisible(false);
        this.q.setVisible(false);
        this.x.setVisible(false);
        this.y.setVisible(false);
        this.z.setVisible(false);
        if (tabType == TabType.TOWERS) {
            this.o.setVisible(true);
            this.x.setVisible(true);
        } else if (tabType == TabType.MODIFIERS) {
            this.p.setVisible(true);
            this.y.setVisible(true);
            TooltipsOverlay.i().markTagShown("BuildMenu.modifiersTab");
            TooltipsOverlay.i().hideEntry("BuildMenu.modifiersTab");
        } else if (tabType == TabType.MINERS) {
            this.q.setVisible(true);
            this.z.setVisible(true);
        }
        this.r.setVisible(tabType != null);
        this.s.setVisible(tabType != null);
        this.i = tabType;
        a((BuildButton) null);
        m();
    }

    private void m() {
        int money = this.V.gameState.getMoney();
        if (this.i == TabType.TOWERS) {
            boolean areModifiersOpened = Game.i.progressManager.areModifiersOpened();
            boolean z = areModifiersOpened;
            if (!areModifiersOpened) {
                ModifierType[] modifierTypeArr = ModifierType.values;
                int length = modifierTypeArr.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    if (this.V.gameValue.getIntValue(Game.i.modifierManager.getCountGameValue(modifierTypeArr[i])) <= 0) {
                        i++;
                    } else {
                        z = true;
                        break;
                    }
                }
            }
            if (z) {
                this.t.setVisible(true);
                if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.TT_MODIFIER_TAB_SHOWN) == 0.0d) {
                    if (!TooltipsOverlay.i().isTagShown("BuildMenu.modifiersTab") && !TooltipsOverlay.i().isVisible("BuildMenu.modifiersTab")) {
                        TooltipsOverlay.i().showText("BuildMenu.modifiersTab", this.t, Game.i.localeManager.i18n.get("tooltip_modifiers_tab"), this.h.uiLayer.mainUiLayer, this.h.uiLayer.zIndex + 1, 4);
                    }
                } else {
                    this.t.updateColors();
                }
            } else {
                this.t.setVisible(false);
            }
            for (TowerType towerType : TowerType.values) {
                int buildPrice = Game.i.towerManager.getFactory(towerType).getBuildPrice(this.V);
                BuildButton buildButton = this.S.get(towerType);
                if (buildButton != null) {
                    buildButton.b(buildPrice);
                    if (money < buildPrice) {
                        buildButton.b(false);
                    } else {
                        buildButton.b(true);
                    }
                    buildButton.c(Game.i.towerManager.getFactory(towerType).isAvailable(this.V.gameValue));
                }
            }
            return;
        }
        if (this.i == TabType.MINERS) {
            for (MinerType minerType : MinerType.values) {
                int buildPrice2 = this.V.miner.getBuildPrice(minerType);
                BuildButton buildButton2 = this.U.get(minerType);
                if (buildButton2 != null) {
                    buildButton2.b(buildPrice2);
                    buildButton2.a(this.V.miner.getBuildableMinersCount(minerType));
                    buildButton2.c(this.V.miner.getMaxMinersCount(minerType) > 0);
                    if (money < buildPrice2 || buildButton2.u == 0) {
                        buildButton2.b(false);
                    } else {
                        buildButton2.b(true);
                    }
                    if (Game.i.minerManager.isMinerOpened(minerType, this.V.gameValue)) {
                        buildButton2.setVisible(true);
                    } else {
                        buildButton2.setVisible(false);
                    }
                }
            }
            return;
        }
        if (this.i == TabType.MODIFIERS) {
            for (ModifierType modifierType : ModifierType.values) {
                int buildPrice3 = this.V.modifier.getBuildPrice(modifierType);
                BuildButton buildButton3 = this.T.get(modifierType);
                if (buildButton3 != null) {
                    buildButton3.b(buildPrice3);
                    buildButton3.a(this.V.modifier.getBuildableModifiersCount(modifierType));
                    buildButton3.c(this.V.modifier.getMaxModifiersCount(modifierType) > 0);
                    if (money < buildPrice3 || buildButton3.u == 0) {
                        buildButton3.b(false);
                    } else {
                        buildButton3.b(true);
                    }
                    buildButton3.c(Game.i.modifierManager.getFactory(modifierType).isAvailable(this.V.gameValue));
                }
            }
        }
    }

    private void n() {
        this.w.setText(Game.i.towerManager.getAimStrategyName(this.V.tower.getDefaultAimStrategy()).toUpperCase());
        this.u.setStrategy(this.V.tower.getDefaultAimStrategy(), true, false);
    }

    private void c(boolean z) {
        if (this.j != z) {
            this.j = z;
            if (z) {
                m();
                this.n.show();
            } else {
                this.n.hide();
            }
            l();
        }
        this.n.setLabelOverTitleTilePos(this.V._gameMapSelection.getSelectedTile());
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/BuildMenu$BuildButton.class */
    public class BuildButton extends Group {
        private boolean o;
        private boolean p;
        private boolean q;
        private boolean r;
        private boolean s;
        private int t = -1;
        private int u = -1;
        TabType k;
        TowerType l;
        MinerType m;
        ModifierType n;
        private final Image v;
        private final Actor w;
        private final Image x;
        private final Image y;
        private final Image z;
        private final Label A;
        private final Label B;
        private final Drawable C;
        private final Drawable D;

        BuildButton(BuildMenu buildMenu, Actor actor) {
            setTransform(false);
            setTouchable(Touchable.enabled);
            this.C = Game.i.assetManager.getDrawable("build-selection");
            this.D = Game.i.assetManager.getDrawable("build-selection-count");
            setSize(127.0f, 127.0f);
            this.v = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
            this.v.setSize(127.0f, 127.0f);
            this.v.setVisible(false);
            this.v.setColor(BuildMenu.f3262a);
            addActor(this.v);
            this.w = actor;
            actor.setSize(128.0f, 128.0f);
            actor.setTouchable(Touchable.disabled);
            addActor(actor);
            this.x = new Image(Game.i.assetManager.getDrawable("build-selection-count-overlay"));
            this.x.setSize(141.0f, 141.0f);
            this.x.setVisible(false);
            addActor(this.x);
            this.z = new Image(Game.i.assetManager.getDrawable("build-selection-hover"));
            this.z.setSize(127.0f, 127.0f);
            this.z.setVisible(false);
            addActor(this.z);
            this.y = new Image();
            this.y.setSize(141.0f, 141.0f);
            this.y.setVisible(false);
            this.y.setPosition(-3.0f, -11.0f);
            addActor(this.y);
            this.B = new Label("", new Label.LabelStyle(Game.i.assetManager.getFont(24), MaterialColor.YELLOW.P500));
            this.B.setSize(120.0f, 32.0f);
            this.B.setAlignment(8);
            this.B.setTouchable(Touchable.disabled);
            addActor(this.B);
            this.A = new Label("", new Label.LabelStyle(Game.i.assetManager.getFont(30), Color.WHITE));
            this.A.setSize(120.0f, 32.0f);
            this.A.setAlignment(16);
            this.A.setTouchable(Touchable.disabled);
            addActor(this.A);
            addListener(new InputListener(buildMenu) { // from class: com.prineside.tdi2.ui.components.BuildMenu.BuildButton.1
                @Override // com.prineside.tdi2.scene2d.InputListener
                public boolean touchDown(InputEvent inputEvent, float f, float f2, int i, int i2) {
                    BuildButton.this.s = true;
                    BuildButton.this.d();
                    return true;
                }

                @Override // com.prineside.tdi2.scene2d.InputListener
                public void touchUp(InputEvent inputEvent, float f, float f2, int i, int i2) {
                    BuildButton.this.s = false;
                    BuildButton.this.d();
                }

                @Override // com.prineside.tdi2.scene2d.InputListener
                public void enter(InputEvent inputEvent, float f, float f2, int i, Actor actor2) {
                    BuildButton.this.r = true;
                    BuildButton.this.d();
                }

                @Override // com.prineside.tdi2.scene2d.InputListener
                public void exit(InputEvent inputEvent, float f, float f2, int i, Actor actor2) {
                    BuildButton.this.r = false;
                    BuildButton.this.d();
                }
            });
            d();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d() {
            if (this.u < 0) {
                this.y.setDrawable(this.C);
                this.x.setVisible(false);
                this.B.setVisible(false);
                this.x.setVisible(false);
            } else {
                this.y.setDrawable(this.D);
                BuildMenu.P.setLength(0);
                BuildMenu.P.append('x').append(this.u);
                this.B.setText(BuildMenu.P);
                if (this.u == 0) {
                    this.B.setColor(MaterialColor.RED.P500);
                } else {
                    this.B.setColor(MaterialColor.YELLOW.P500);
                }
                this.B.setVisible(true);
                this.x.setVisible(true);
            }
            if (!this.p || !this.o) {
                this.y.setColor(BuildMenu.g);
            } else if (this.s) {
                this.y.setColor(BuildMenu.f);
            } else if (this.r) {
                this.y.setColor(BuildMenu.e);
            } else {
                this.y.setColor(BuildMenu.d);
            }
            if (this.p) {
                this.A.setVisible(true);
                if (this.o) {
                    this.v.setVisible(true);
                    this.A.setColor(BuildMenu.f3263b);
                } else {
                    this.v.setVisible(false);
                    this.A.setColor(BuildMenu.c);
                }
            } else {
                this.v.setVisible(false);
                this.A.setVisible(false);
            }
            if (this.p && this.o) {
                this.w.setColor(Color.WHITE);
            } else {
                this.w.setColor(0.0f, 0.0f, 0.0f, 0.28f);
            }
            if (this.q) {
                this.y.setVisible(true);
                setZIndex(99);
                this.A.setPosition(4.0f, -2.0f);
                this.B.setPosition(6.0f, 0.0f);
                this.z.setVisible(false);
                this.A.setColor(Color.WHITE);
                this.x.setPosition(-3.0f, -11.0f);
                return;
            }
            this.y.setVisible(false);
            this.A.setPosition(0.0f, 2.0f);
            this.B.setPosition(10.0f, 4.0f);
            this.z.setVisible(this.r);
            this.x.setPosition(0.0f, -8.0f);
        }

        final void a(int i) {
            this.u = i;
            d();
        }

        final void a(boolean z) {
            this.q = z;
            d();
        }

        final void b(boolean z) {
            this.o = z;
            d();
        }

        final void c(boolean z) {
            this.p = z;
            setTouchable(z ? Touchable.enabled : Touchable.disabled);
            d();
        }

        final void b(int i) {
            if (this.t != i) {
                BuildMenu.P.setLength(0);
                BuildMenu.P.append(i);
                this.A.setText(BuildMenu.P);
                this.t = i;
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/BuildMenu$_SideMenuListener.class */
    private class _SideMenuListener extends SideMenu.SideMenuListener.SideMenuListenerAdapter {
        private _SideMenuListener() {
        }

        /* synthetic */ _SideMenuListener(BuildMenu buildMenu, byte b2) {
            this();
        }

        @Override // com.prineside.tdi2.ui.actors.SideMenu.SideMenuListener.SideMenuListenerAdapter, com.prineside.tdi2.ui.actors.SideMenu.SideMenuListener
        public void offscreenChanged() {
            BuildMenu.this.l();
        }
    }
}
