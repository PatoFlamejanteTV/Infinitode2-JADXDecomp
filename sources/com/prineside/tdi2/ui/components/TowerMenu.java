package com.prineside.tdi2.ui.components;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.SpaceTileBonus;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.enums.BuildingType;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.enums.TowerStatType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.TowerManager;
import com.prineside.tdi2.managers.TowerStatManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.systems.TowerSystem;
import com.prineside.tdi2.tiles.PlatformTile;
import com.prineside.tdi2.ui.actors.AimStrategySelector;
import com.prineside.tdi2.ui.actors.ComplexButton;
import com.prineside.tdi2.ui.actors.EffectTooltip;
import com.prineside.tdi2.ui.actors.ExpLine;
import com.prineside.tdi2.ui.actors.HotKeyHintLabel;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.LabelToggleButton;
import com.prineside.tdi2.ui.actors.SideMenu;
import com.prineside.tdi2.ui.shared.Dialog;
import com.prineside.tdi2.ui.shared.TooltipsOverlay;
import com.prineside.tdi2.utils.IntPair;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/TowerMenu.class */
public class TowerMenu implements Disposable {
    private final SideMenu d;
    private final SideMenu.SideMenuContainer e;
    private final LabelToggleButton f;
    private HotKeyHintLabel g;
    private final Label h;
    private final Label i;
    private final Label j;
    private final Label k;
    private final Label l;
    private final Table m;
    private final Label n;
    private final ExpLine o;
    private final Label p;
    private final UpgradeSubmenu s;
    private final AimStrategySelector t;
    private final ComplexButton u;
    private final SellButton v;
    private final Group w;
    private final Group x;
    private final Group y;
    private Table z;
    private Label A;
    private Label B;
    private Image C;
    private Label D;
    private Image E;
    private Label F;
    private Table G;
    private float H;
    private float I;
    private float J;
    private float K;
    private int L;
    private boolean M;
    private boolean N;
    private boolean O;
    private boolean R;
    private float T;
    private boolean U;
    private Group X;
    private InputProcessor Y;
    private Group Z;
    private final GameSystemProvider ab;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3429a = TLog.forClass(TowerMenu.class);

    /* renamed from: b, reason: collision with root package name */
    private static final Rectangle f3430b = new Rectangle(40.0f, 209.0f, 520.0f, 66.0f);
    private static final Rectangle c = new Rectangle(40.0f, 319.0f, 520.0f, 151.0f);
    private static final StringBuilder ac = new StringBuilder();
    private static final Vector2 ad = new Vector2();
    private final CharacteristicCell[] q = new CharacteristicCell[8];
    private final TowerAbilityButton[] r = new TowerAbilityButton[6];
    private int P = -1;
    private float Q = 0.0f;
    private short S = -1;
    private boolean V = false;
    private ObjectMap<String, Object> W = new ObjectMap<>();
    private final Runnable aa = this::d;

    static /* synthetic */ boolean a(TowerMenu towerMenu, boolean z) {
        towerMenu.M = true;
        return true;
    }

    static /* synthetic */ int b(TowerMenu towerMenu, int i) {
        towerMenu.P = -1;
        return -1;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0925, code lost:            r11.r[r14].addActor(new com.prineside.tdi2.ui.actors.HotKeyHintLabel(com.prineside.tdi2.Game.i.settingsManager.getHotKey(r16), 40.0f, 90.0f));     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public TowerMenu(com.prineside.tdi2.ui.actors.SideMenu r12, final com.prineside.tdi2.GameSystemProvider r13) {
        /*
            Method dump skipped, instructions count: 3051
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.ui.components.TowerMenu.<init>(com.prineside.tdi2.ui.actors.SideMenu, com.prineside.tdi2.GameSystemProvider):void");
    }

    public void startUsingCustomButton() {
        if (this.U) {
            f3429a.e("been using custom button, canceling", new Object[0]);
            cancelUsingCustomButton();
        }
        this.U = true;
        this.ab._input.setupInputMultiplexer(true, true, false).addProcessor(this.Y);
        e();
    }

    public void cancelUsingCustomButton() {
        this.ab._input.enableAllInput();
        this.U = false;
        e();
    }

    public boolean isVisible() {
        return this.e.isVisible();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        if (this.P != i) {
            this.P = i;
            this.M = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Tower c() {
        Tile selectedTile = this.ab._gameMapSelection.getSelectedTile();
        if (selectedTile != null && selectedTile.type == TileType.PLATFORM) {
            PlatformTile platformTile = (PlatformTile) selectedTile;
            if (platformTile.building != null && platformTile.building.buildingType == BuildingType.TOWER) {
                return (Tower) platformTile.building;
            }
            return null;
        }
        return null;
    }

    private void d() {
        UpgradeSubmenu upgradeSubmenu;
        boolean z;
        Tower c2 = c();
        if (c2 != null) {
            if (c2.getUpgradeLevel() >= c2.getMaxUpgradeLevel()) {
                upgradeSubmenu = this.s;
            } else {
                upgradeSubmenu = this.s;
                if (this.ab.gameState.getMoney() >= this.ab.tower.getUpgradePrice(c2)) {
                    z = true;
                    upgradeSubmenu.a(z);
                }
            }
            z = false;
            upgradeSubmenu.a(z);
        }
    }

    private void e() {
        Tower c2 = c();
        this.u.setVisible(false);
        if (c2 != null) {
            if (!c2.canAim()) {
                this.t.setVisible(false);
                if (c2.hasCustomButton()) {
                    this.u.setVisible(true);
                    c2.updateCustomButton(this.u, this.U);
                    return;
                }
                return;
            }
            this.t.setVisible(true);
            this.t.setStrategy(c2.aimStrategy, true, false);
        }
    }

    public void hideTowerExperience() {
        this.w.clearActions();
        this.w.setVisible(false);
    }

    public void showTowerExperience(boolean z, Runnable runnable) {
        this.w.clearActions();
        this.w.setVisible(true);
        this.w.setTransform(false);
        if (z) {
            this.ab._gameUi.uiElementsEmphasizer.show(this.w, f3430b, Game.i.localeManager.i18n.get("tower_menu_ui_experience_title"), Game.i.localeManager.i18n.get("tower_menu_ui_experience_description"), runnable);
        } else if (runnable != null) {
            runnable.run();
        }
    }

    public void hideTowerAbilities() {
        this.x.clearActions();
        this.x.setVisible(false);
    }

    public void showTowerAbilities(boolean z, Runnable runnable) {
        this.x.clearActions();
        this.x.setVisible(true);
        this.x.setTransform(false);
        if (z) {
            this.ab._gameUi.uiElementsEmphasizer.show(this.x, c, Game.i.localeManager.i18n.get("tower_menu_ui_abilities_title"), Game.i.localeManager.i18n.get("tower_menu_ui_abilities_description"), runnable);
        } else if (runnable != null) {
            runnable.run();
        }
    }

    public void draw(float f) {
        if (isVisible()) {
            Tower c2 = c();
            if (c2 != null) {
                if (this.L != c2.getSellPrice()) {
                    this.M = true;
                    if (Dialog.i().isVisible() && Dialog.i().getLastConfirmId().equals("sellButton")) {
                        Dialog.i().hide();
                    }
                }
                this.f.setEnabled(!c2.outOfOrder.hasReason(TowerSystem.TOWER_OUT_OF_ORDER_REASON_MANUAL));
                this.Q += f;
                if (this.Q > 0.5f) {
                    this.Q = 0.0f;
                    this.O = true;
                }
                short dirtyTileGeneration = this.ab.map.getDirtyTileGeneration(c2.getTile());
                if (this.S != dirtyTileGeneration) {
                    this.M = true;
                    this.S = dirtyTileGeneration;
                }
                if (this.T != c2.loopAbilityDamageBuffer) {
                    this.T = c2.loopAbilityDamageBuffer;
                    this.M = true;
                }
            }
            if (this.M) {
                h();
            }
            if (this.V != this.ab.gameState.isDoubleSpeedActive()) {
                this.V = !this.V;
                this.N = true;
            }
            if (this.N) {
                g();
            }
            if (this.O) {
                f();
            }
            if (c2 != null) {
                c2.fillTowerMenu(this.X, this.W);
            }
        }
    }

    private void f() {
        Tower c2;
        if (this.z.isVisible() && (c2 = c()) != null && (this.H != c2.mdps || this.I != c2.enemiesKilled || this.K != c2.damageGiven || this.J != ((int) c2.bonusCoinsBrought))) {
            this.H = c2.mdps;
            this.I = c2.enemiesKilled;
            this.J = (int) c2.bonusCoinsBrought;
            this.K = c2.damageGiven;
            this.A.setText(StringFormatter.compactNumber(c2.mdps, false));
            this.F.setText(StringFormatter.compactNumber(c2.damageGiven, false));
            if (this.J == 0.0f) {
                this.D.setVisible(false);
                this.C.setVisible(false);
            } else {
                ac.setLength(0);
                if (c2.bonusCoinsBrought > 0.0f) {
                    ac.append('+').append(StringFormatter.compactNumber((int) c2.bonusCoinsBrought, false));
                } else {
                    ac.append(StringFormatter.compactNumber((int) c2.bonusCoinsBrought, false));
                }
                this.D.setText(ac);
                this.D.setVisible(true);
                this.C.setVisible(true);
            }
            this.B.setText(StringFormatter.compactNumber(c2.enemiesKilled, false));
        }
        this.O = false;
    }

    private void g() {
        Tower c2 = c();
        if (c2 != null) {
            ac.setLength(0);
            ac.append('L').append((int) c2.getLevel());
            this.h.setText(ac);
            if (c2.nextLevelExperience != 0.0f) {
                this.o.setCoeff(c2.currentLevelExperience / c2.nextLevelExperience);
            } else {
                this.o.setCoeff(1.0f);
            }
            ac.setLength(0);
            if (c2.nextLevelExperience != 0.0f) {
                ac.append((int) c2.currentLevelExperience).append(" / ").append((int) c2.nextLevelExperience).append(" XP");
                this.k.setText(ac);
                this.i.setVisible(false);
                this.j.setVisible(false);
            } else {
                this.k.setText("MAX XP");
                ac.setLength(0);
                ac.append("+");
                long j = ((int) c2.experience) - Tower.LEVEL_EXPERIENCE_MILESTONES[c2.getLevel()];
                if (j <= 1000000000) {
                    ac.append(StringFormatter.commaSeparatedNumber(j));
                } else {
                    ac.append("bazillion");
                }
                this.i.setText(ac);
                this.j.setText(ac);
                this.i.setVisible(true);
                this.j.setVisible(true);
            }
            if (c2.experienceGeneration == 0.0f) {
                this.l.setVisible(false);
            } else {
                ac.setLength(0);
                ac.append('+');
                ac.append(StringFormatter.compactNumber(c2.experienceGeneration, true));
                if (this.ab.gameState.isDoubleSpeedActive()) {
                    ac.append(" [#BBBBBB]x2[]");
                }
                ac.append(" XP/s");
                this.l.setText(ac);
                this.l.setVisible(true);
            }
        }
        this.N = false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void h() {
        this.W.clear();
        this.X.clearChildren();
        this.G.clear();
        Tower c2 = c();
        if (c2 != null) {
            this.e.setLabelOverTitleTilePos(this.ab._gameMapSelection.getSelectedTile());
            Tower.Factory<? extends Tower> factory = Game.i.towerManager.getFactory(c2.type);
            PlatformTile tile = c2.getTile();
            TowerStatType[] statTypes = Game.i.towerManager.getStatTypes(c2.type);
            float range = c2.getRange();
            float minRange = c2.getMinRange();
            float powerCombinedMultiplier = c2.getPowerCombinedMultiplier();
            float[] fArr = new float[statTypes.length];
            int i = 0;
            for (TowerStatType towerStatType : statTypes) {
                int i2 = i;
                i++;
                fArr[i2] = c2.getStat(towerStatType);
            }
            if (c2.affectedByLoopAbility != null) {
                Table table = new Table();
                this.G.add(table).padRight(15.0f);
                Image image = new Image(Game.i.assetManager.getDrawable("icon-loop"));
                image.setColor(MaterialColor.GREEN.P500);
                table.add((Table) image).size(24.0f).padRight(6.0f);
                Label label = new Label(StringFormatter.compactNumber(c2.loopAbilityDamageBuffer, false), Game.i.assetManager.getLabelStyle(21));
                label.setColor(MaterialColor.GREEN.P500);
                table.add((Table) label);
                Image image2 = new Image(Game.i.assetManager.getDrawable("icon-info-circle"));
                image2.setColor(1.0f, 1.0f, 1.0f, 0.28f);
                table.add((Table) image2).size(24.0f).padLeft(6.0f);
                table.setTouchable(Touchable.enabled);
                table.addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.components.TowerMenu.5
                    @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                    public void clicked(InputEvent inputEvent, float f, float f2) {
                        TooltipsOverlay.i().showText("loop_ability_tower_menu_hint", TowerMenu.this.G, Game.i.localeManager.i18n.get("loop_ability_tower_menu_hint"), UiManager.MainUiLayer.SCREEN, 106, 4);
                    }
                });
            }
            this.G.add().height(1.0f).growX();
            this.f.setText(StringFormatter.toUpperCase(factory.getTitle()));
            this.f.setEnabled(!c2.outOfOrder.hasReason(TowerSystem.TOWER_OUT_OF_ORDER_REASON_MANUAL));
            this.f.toggleImage.setVisible(this.ab.tower.canTowersBeManuallyDisabled());
            if (this.g != null) {
                this.g.setVisible(this.ab.tower.canTowersBeManuallyDisabled());
            }
            g();
            this.m.clearChildren();
            if (tile.bonusType != null && tile.bonusLevel > 0) {
                EffectTooltip effectTooltip = new EffectTooltip(Game.i.assetManager.getDrawable(SpaceTileBonus.getIconName(tile.bonusType)), SpaceTileBonus.getDetailedName(tile.bonusType, tile.bonusLevel));
                effectTooltip.setHint(Game.i.localeManager.i18n.get("tile_effect"));
                effectTooltip.setColor(SpaceTileBonus.getBrightColor(tile.bonusType));
                this.m.add(effectTooltip);
                this.n.setVisible(false);
            } else {
                this.n.setVisible(true);
            }
            int i3 = 0;
            for (TowerStatType towerStatType2 : statTypes) {
                if (Game.i.towerStatManager.getInstance(towerStatType2).isVisible()) {
                    this.q[i3].a(c2.type, towerStatType2, c2.getStat(towerStatType2));
                    this.q[i3].setVisible(true);
                    i3++;
                }
            }
            for (int i4 = i3; i4 < 8; i4++) {
                this.q[i4].setVisible(false);
            }
            int scaledViewportHeight = Game.i.settingsManager.getScaledViewportHeight() - Config.VIEWPORT_HEIGHT;
            if (this.Z != null) {
                this.Z.remove();
                this.Z = null;
            }
            if (scaledViewportHeight > 100) {
                this.Z = new Group();
                this.Z.setTransform(false);
                this.Z.setPosition(0.0f, (((725.0f - (((i3 + 1) / 2) * 68.0f)) + scaledViewportHeight) - 32.0f) - 20.0f);
                this.Z.setSize(600.0f, 100.0f);
                this.e.addActor(this.Z);
                Table table2 = new Table();
                table2.setSize(600.0f, 100.0f);
                this.Z.addActor(table2);
                for (EnemyType enemyType : EnemyType.mainEnemyTypes) {
                    Group group = new Group();
                    group.setTransform(false);
                    table2.add((Table) group).size(42.0f, 100.0f);
                    Image image3 = new Image(this.ab.enemy.getTexture(enemyType));
                    image3.setPosition(5.0f, 47.0f);
                    image3.setSize(32.0f, 32.0f);
                    group.addActor(image3);
                    int round = MathUtils.round(this.ab.tower.towerEnemyDamageMultiplier[enemyType.ordinal()][c2.type.ordinal()] * 100.0f);
                    ac.setLength(0);
                    ac.append(round);
                    Label label2 = new Label(ac, Game.i.assetManager.getLabelStyle(18));
                    label2.setAlignment(1);
                    label2.setSize(42.0f, 24.0f);
                    label2.setPosition(0.0f, 12.0f);
                    group.addActor(label2);
                    if (round <= 0) {
                        label2.setColor(MaterialColor.RED.P500);
                    } else if (round < 100) {
                        label2.setColor(MaterialColor.ORANGE.P600);
                    } else if (round > 100) {
                        label2.setColor(MaterialColor.GREEN.P500);
                    } else {
                        label2.setColor(MaterialColor.YELLOW.P500);
                    }
                }
            }
            Tower.AbilityConfig[] abilityConfigs = factory.getAbilityConfigs(this.ab, c2);
            for (int i5 = 0; i5 < 6; i5++) {
                TowerAbilityButton towerAbilityButton = this.r[i5];
                towerAbilityButton.b(c2.isAbilityInstalled(i5));
                towerAbilityButton.a(factory.getShadowTextures(), factory.getBaseTextures(), factory.getAbilityTextures(i5));
                if (i5 != 3) {
                    towerAbilityButton.a(c2.canAbilityBeInstalled(i5));
                } else {
                    towerAbilityButton.a(false);
                }
            }
            for (int i6 = 0; i6 < 6; i6++) {
                this.r[i6].setSelected(false);
            }
            this.y.clear();
            Array array = new Array();
            int i7 = this.ab.tower.towerAbilityIdxToCategory[0];
            int i8 = 0;
            for (int i9 = 1; i9 < 6; i9++) {
                int i10 = this.ab.tower.towerAbilityIdxToCategory[i9];
                if (i10 != i7) {
                    array.add(new IntPair(i8, i9 - 1));
                    i7 = i10;
                    i8 = i9;
                }
            }
            array.add(new IntPair(i8, 5));
            Array.ArrayIterator it = array.iterator();
            while (it.hasNext()) {
                IntPair intPair = (IntPair) it.next();
                int i11 = intPair.f3850a;
                int i12 = intPair.f3851b;
                TowerSystem.TowerAbilityCategoryRule towerAbilityCategoryRule = this.ab.tower.towerAbilityCategoryRules[this.ab.tower.towerAbilityIdxToCategory[i11]];
                float f = i11 * 88;
                float f2 = (((i12 - i11) + 1) * 80) + ((i12 - i11) << 3);
                Image image4 = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                image4.setSize(f2, 4.0f);
                image4.setColor(1.0f, 1.0f, 1.0f, 0.28f);
                image4.setPosition(f, 26.0f);
                this.y.addActor(image4);
                ac.setLength(0);
                for (int i13 = 0; i13 < towerAbilityCategoryRule.requiredXpLevels.size; i13++) {
                    int i14 = towerAbilityCategoryRule.requiredXpLevels.get(i13);
                    if (ac.length != 0) {
                        ac.append(" / ");
                    }
                    ac.append('L').append(i14);
                }
                Label label3 = new Label(ac, Game.i.assetManager.getLabelStyle(24));
                label3.setSize(f2, 17.0f);
                label3.setColor(1.0f, 1.0f, 1.0f, 0.28f);
                label3.setAlignment(1);
                label3.setPosition(f, 0.0f);
                this.y.addActor(label3);
            }
            if (this.P != -1) {
                ac.setLength(0);
                ac.append("[#FFC107]").append(abilityConfigs[this.P].getName()).append("[]\n");
                ac.append(abilityConfigs[this.P].getDescription());
                if (this.P == 3) {
                    ac.append(SequenceUtils.EOL).append("[#FFC107]").append(Game.i.localeManager.i18n.format("unlocks_at_lvl", 10)).append("[]");
                }
                this.d.showSideTooltip(ac, this.x.getY() + this.r[this.P].getY() + (this.r[this.P].getHeight() * 0.5f));
                this.r[this.P].setSelected(true);
            } else {
                this.d.hideSideTooltip();
            }
            if (this.P != -1 && !c2.isAbilityInstalled(this.P)) {
                c2.installedAbilities[this.P] = true;
                c2.updateCache();
                range = c2.getRange();
                minRange = c2.getMinRange();
                powerCombinedMultiplier = c2.getPowerCombinedMultiplier();
                int i15 = 0;
                for (TowerStatType towerStatType3 : statTypes) {
                    int i16 = i15;
                    i15++;
                    fArr[i16] = c2.getStat(towerStatType3);
                }
                c2.installedAbilities[this.P] = false;
                c2.updateCache();
            }
            byte maxUpgradeLevel = c2.getMaxUpgradeLevel();
            this.s.a(c2.getUpgradeLevel(), maxUpgradeLevel);
            if (c2.getUpgradeLevel() < maxUpgradeLevel) {
                this.s.a(this.ab.tower.getUpgradePrice(c2));
            } else {
                this.s.a(-1);
            }
            if (this.s.isButtonSelected() && c2.getUpgradeLevel() < c2.getMaxUpgradeLevel()) {
                byte upgradeLevel = c2.getUpgradeLevel();
                c2.setUpgradeLevel((byte) (upgradeLevel + 1));
                c2.updateCache();
                range = c2.getRange();
                minRange = c2.getMinRange();
                powerCombinedMultiplier = c2.getPowerCombinedMultiplier();
                int i17 = 0;
                for (TowerStatType towerStatType4 : statTypes) {
                    int i18 = i17;
                    i17++;
                    fArr[i18] = c2.getStat(towerStatType4);
                }
                c2.setUpgradeLevel(upgradeLevel);
                c2.updateCache();
            } else {
                for (CharacteristicCell characteristicCell : this.q) {
                    characteristicCell.d();
                }
            }
            d();
            if (c2.getPowerCombinedMultiplier() != powerCombinedMultiplier) {
                ac.setLength(0);
                ac.append("+").append(StringFormatter.compactNumberWithPrecisionTrimZeros((c2.getPowerCombinedMultiplier() - 1.0f) * 100.0f, 1, false)).append("[#4CAF50] +").append(StringFormatter.compactNumberWithPrecisionTrimZeros((powerCombinedMultiplier - c2.getPowerCombinedMultiplier()) * 100.0f, 1, false)).append("[]%");
                this.p.setText(ac);
            } else {
                ac.setLength(0);
                ac.append("+").append(StringFormatter.compactNumberWithPrecisionTrimZeros((c2.getPowerCombinedMultiplier() - 1.0f) * 100.0f, 1, false)).append("%");
                this.p.setText(ac);
            }
            int i19 = 0;
            for (TowerStatType towerStatType5 : statTypes) {
                for (CharacteristicCell characteristicCell2 : this.q) {
                    if (characteristicCell2.t == towerStatType5) {
                        if (fArr[i19] != c2.getStat(towerStatType5)) {
                            characteristicCell2.a(fArr[i19]);
                        } else {
                            characteristicCell2.d();
                        }
                    }
                }
                i19++;
            }
            if (range != c2.getRange() || minRange != c2.getMinRange()) {
                this.ab.map.showTowerRangeHint(c2.getTile().center.x, c2.getTile().center.y, minRange * 128.0f, range * 128.0f);
            } else {
                this.ab.map.hideTowerRangeHint();
            }
            e();
            if (c2.isSellFullRefundStillActive()) {
                this.v.setColors(MaterialColor.GREEN.P800, MaterialColor.GREEN.P900, MaterialColor.GREEN.P700);
            } else {
                this.v.setColors(MaterialColor.RED.P800, MaterialColor.RED.P900, MaterialColor.RED.P700);
            }
            this.v.setPrice(c2.getSellPrice());
            this.L = c2.getSellPrice();
            f();
        }
        this.M = false;
    }

    private void a(boolean z) {
        if (this.R != z) {
            this.R = z;
            if (z) {
                this.e.show();
                this.M = true;
                this.z.setVisible(true);
                return;
            }
            this.e.hide();
            this.s.setButtonSelected(false);
            this.d.hideSideTooltip();
            a(-1);
            this.ab.map.hideTowerRangeHint();
            this.W.clear();
            if (this.U) {
                cancelUsingCustomButton();
            }
        }
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/TowerMenu$_SideMenuListener.class */
    private class _SideMenuListener extends SideMenu.SideMenuListener.SideMenuListenerAdapter {
        private _SideMenuListener() {
        }

        /* synthetic */ _SideMenuListener(TowerMenu towerMenu, byte b2) {
            this();
        }

        @Override // com.prineside.tdi2.ui.actors.SideMenu.SideMenuListener.SideMenuListenerAdapter, com.prineside.tdi2.ui.actors.SideMenu.SideMenuListener
        public void offscreenChanged() {
            if (TowerMenu.this.d.isOffscreen()) {
                TowerMenu.this.a(-1);
            }
            TowerMenu.a(TowerMenu.this, true);
        }

        @Override // com.prineside.tdi2.ui.actors.SideMenu.SideMenuListener.SideMenuListenerAdapter, com.prineside.tdi2.ui.actors.SideMenu.SideMenuListener
        public void sideTooltipHidden() {
            TowerMenu.b(TowerMenu.this, -1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/components/TowerMenu$CharacteristicCell.class */
    public class CharacteristicCell extends Group {
        private final Image l;
        private final Image m;
        private final Image n;
        private final Label o;
        private final Label p;
        private final Label q;
        private final Label r;
        private final Image s;
        private TowerStatType t;
        private float u;
        private float v;
        private final GlyphLayout w;
        private final ClickListener x = new ClickListener() { // from class: com.prineside.tdi2.ui.components.TowerMenu.CharacteristicCell.1
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public void enter(InputEvent inputEvent, float f, float f2, int i, Actor actor) {
                super.enter(inputEvent, f, f2, i, actor);
                if (!isOver() || CharacteristicCell.this.t == null) {
                    return;
                }
                TowerStatManager.TowerStat towerStatManager = Game.i.towerStatManager.getInstance(CharacteristicCell.this.t);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("[#4DB6AC]");
                stringBuilder.append(towerStatManager.getName());
                stringBuilder.append("[]");
                Tower c = TowerMenu.this.c();
                if (c != null) {
                    CharSequence statMoreInfo = Game.i.towerManager.getFactory(c.type).getStatMoreInfo(CharacteristicCell.this.t, TowerMenu.this.ab.gameValue, c);
                    if (statMoreInfo != null) {
                        stringBuilder.append(SequenceUtils.EOL);
                        stringBuilder.append(statMoreInfo);
                    }
                }
                TowerMenu.this.d.showSideTooltip(stringBuilder, CharacteristicCell.this.getY() + (CharacteristicCell.this.getHeight() * 0.5f));
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public void exit(InputEvent inputEvent, float f, float f2, int i, Actor actor) {
                super.exit(inputEvent, f, f2, i, actor);
                TowerMenu.this.d.hideSideTooltip();
            }
        };

        CharacteristicCell() {
            setSize(258.0f, 64.0f);
            setTransform(false);
            setTouchable(Touchable.enabled);
            Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
            image.setSize(258.0f, 64.0f);
            image.setColor(0.0f, 0.0f, 0.0f, 0.14f);
            addActor(image);
            this.l = new Image(Game.i.assetManager.getDrawable("tower-menu-stat-line"));
            this.l.setHeight(64.0f);
            addActor(this.l);
            this.m = new Image(Game.i.assetManager.getDrawable("tower-menu-stat-line"));
            this.m.setHeight(64.0f);
            this.m.setVisible(false);
            this.m.setColor(Color.WHITE);
            addActor(this.m);
            this.n = new Image();
            this.n.setSize(48.0f, 48.0f);
            this.n.setPosition(8.0f, 8.0f);
            addActor(this.n);
            this.w = new GlyphLayout(Game.i.assetManager.getFont(21), "", Color.WHITE, 120.0f, 8, true);
            this.o = new Label("", Game.i.assetManager.getLabelStyle(21));
            this.o.setPosition(64.0f, 0.0f);
            this.o.setSize(120.0f, 64.0f);
            this.o.setWrap(true);
            addActor(this.o);
            this.s = new Image(Game.i.assetManager.getDrawable("icon-info-circle"));
            this.s.setSize(20.0f, 20.0f);
            this.s.setColor(MaterialColor.TEAL.P500);
            this.s.setPosition(-10.0f, 24.0f);
            this.s.setVisible(false);
            addActor(this.s);
            this.p = new Label("1.23", Game.i.assetManager.getLabelStyle(24));
            this.p.setPosition(194.0f, 0.0f);
            this.p.setSize(48.0f, 64.0f);
            this.p.setAlignment(16);
            addActor(this.p);
            this.q = new Label("tiles/s", Game.i.assetManager.getLabelStyle(18));
            this.q.setPosition(194.0f, 3.0f);
            this.q.setSize(48.0f, 16.0f);
            this.q.setColor(1.0f, 1.0f, 1.0f, 0.28f);
            this.q.setAlignment(16);
            addActor(this.q);
            this.r = new Label("+1.12", Game.i.assetManager.getLabelStyle(21));
            this.r.setPosition(194.0f, 32.0f);
            this.r.setSize(48.0f, 32.0f);
            this.r.setAlignment(16);
            this.r.setVisible(false);
            addActor(this.r);
        }

        final void a(TowerType towerType, TowerStatType towerStatType, float f) {
            this.t = towerStatType;
            this.u = f;
            TowerManager.TowerStatConfig statConfig = Game.i.towerManager.getStatConfig(towerType, towerStatType);
            TowerStatManager.TowerStat towerStatManager = Game.i.towerStatManager.getInstance(towerStatType);
            this.n.setDrawable(Game.i.assetManager.getDrawable(towerStatManager.getIconDrawableAlias()));
            String name = towerStatManager.getName();
            this.w.setText(Game.i.assetManager.getFont(21), name, Color.WHITE, 120.0f, 8, true);
            clearListeners();
            boolean z = false;
            if (this.w.height > 60.0f) {
                TowerMenu.ac.setLength(0);
                TowerMenu.ac.append(name).append("...");
                while (this.w.height > 60.0f) {
                    TowerMenu.ac.setLength(TowerMenu.ac.length - 5);
                    TowerMenu.ac.append("...");
                    this.w.setText(Game.i.assetManager.getFont(21), TowerMenu.ac, Color.WHITE, 120.0f, 8, true);
                }
                this.o.setText(TowerMenu.ac);
                z = true;
            } else {
                this.o.setText(name);
            }
            Tower c = TowerMenu.this.c();
            if (c != null && Game.i.towerManager.getFactory(towerType).getStatMoreInfo(this.t, TowerMenu.this.ab.gameValue, c) != null) {
                z = true;
            }
            if (z) {
                this.s.setVisible(true);
                addListener(this.x);
            } else {
                this.s.setVisible(false);
            }
            if (statConfig.rounding == 0) {
                this.p.setText(StringFormatter.compactNumber(f, true));
            } else {
                this.p.setText(StringFormatter.compactNumber(f, false));
            }
            if (towerStatManager.unitsAlias != null) {
                this.q.setText(Game.i.localeManager.i18n.get(towerStatManager.unitsAlias));
                this.q.setVisible(true);
            } else {
                this.q.setText("");
            }
            if (!statConfig.unique) {
                this.n.setColor(Color.WHITE);
                this.o.setColor(1.0f, 1.0f, 1.0f, 0.56f);
            } else {
                this.n.setColor(MaterialColor.AMBER.P600);
                this.o.setColor(MaterialColor.AMBER.P600);
            }
            this.p.setColor(Color.WHITE);
            this.l.setColor(towerStatManager.getColor());
            float statBarCoeff = Game.i.towerManager.getStatBarCoeff(towerStatType, f, TowerMenu.this.ab.tower.getMaxPossibleStat(towerStatType)) * 258.0f;
            float f2 = statBarCoeff;
            if (statBarCoeff > 258.0f) {
                f2 = 258.0f;
            }
            this.l.setWidth(f2);
        }

        final void a(float f) {
            if (f == this.u) {
                d();
                return;
            }
            this.v = f;
            float statBarCoeff = Game.i.towerManager.getStatBarCoeff(this.t, this.u, TowerMenu.this.ab.tower.getMaxPossibleStat(this.t)) * 258.0f;
            float statBarCoeff2 = (Game.i.towerManager.getStatBarCoeff(this.t, f, TowerMenu.this.ab.tower.getMaxPossibleStat(this.t)) * 258.0f) - statBarCoeff;
            if (statBarCoeff < 258.0f) {
                if (statBarCoeff + statBarCoeff2 > 258.0f) {
                    statBarCoeff2 = 258.0f - statBarCoeff;
                }
                this.m.clearActions();
                this.m.setVisible(true);
                this.m.addAction(Actions.alpha(1.0f, 0.3f));
                this.m.setPosition(statBarCoeff, 0.0f);
                this.m.setWidth(statBarCoeff2);
            } else {
                this.m.clearActions();
                this.m.setVisible(false);
            }
            this.p.setY(-8.0f);
            this.q.setVisible(false);
            TowerMenu.ac.setLength(0);
            if (this.v > this.u) {
                TowerMenu.ac.append('+');
            }
            TowerMenu.ac.append(StringFormatter.compactNumber(this.v - this.u, true));
            this.r.setVisible(true);
            this.r.setText(TowerMenu.ac);
            if (this.v < this.u) {
                this.r.setColor(MaterialColor.RED.P500);
            } else {
                this.r.setColor(MaterialColor.GREEN.P500);
            }
        }

        final void d() {
            this.m.clearActions();
            this.m.addAction(Actions.sequence(Actions.alpha(0.0f, 0.3f), Actions.hide()));
            this.p.setY(0.0f);
            this.q.setVisible(true);
            this.r.setVisible(false);
        }
    }
}
