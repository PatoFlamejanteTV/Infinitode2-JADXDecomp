package com.prineside.tdi2.systems;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Action;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.DamageRecord;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystem;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.SerializableListener;
import com.prineside.tdi2.SpaceTileBonus;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.actions.BuildTowerAction;
import com.prineside.tdi2.actions.ChangeTowerAimStrategyAction;
import com.prineside.tdi2.actions.CustomTowerButtonAction;
import com.prineside.tdi2.actions.GlobalUpgradeTowerAction;
import com.prineside.tdi2.actions.SelectGlobalTowerAbilityAction;
import com.prineside.tdi2.actions.SelectTowerAbilityAction;
import com.prineside.tdi2.actions.SellTowerAction;
import com.prineside.tdi2.actions.ToggleTowerEnabledAction;
import com.prineside.tdi2.actions.UpgradeTowerAction;
import com.prineside.tdi2.configs.GameRenderingOrder;
import com.prineside.tdi2.enums.ActionType;
import com.prineside.tdi2.enums.BuildingType;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ShapeType;
import com.prineside.tdi2.enums.SpaceTileBonusType;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.enums.TowerStatType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.BuildingRemove;
import com.prineside.tdi2.events.game.EnemyDie;
import com.prineside.tdi2.events.game.EnemyTakeDamage;
import com.prineside.tdi2.events.game.GameStateTick;
import com.prineside.tdi2.events.game.TowerAbilityChange;
import com.prineside.tdi2.events.game.TowerAimStrategyChange;
import com.prineside.tdi2.events.game.TowerBuild;
import com.prineside.tdi2.events.game.TowerCustomButtonPress;
import com.prineside.tdi2.events.game.TowerPlace;
import com.prineside.tdi2.events.game.TowerPreSell;
import com.prineside.tdi2.events.game.TowerSell;
import com.prineside.tdi2.events.game.TowerUpgrade;
import com.prineside.tdi2.events.game.TowersDefaultAimStrategyChange;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.shapes.RangeCircle;
import com.prineside.tdi2.systems.MapRenderingSystem;
import com.prineside.tdi2.systems.RenderSystem;
import com.prineside.tdi2.systems.StateSystem;
import com.prineside.tdi2.tiles.PlatformTile;
import com.prineside.tdi2.towers.FlamethrowerTower;
import com.prineside.tdi2.utils.DrawUtils;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.ObjectFilter;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;
import java.util.Arrays;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/TowerSystem.class */
public final class TowerSystem extends GameSystem {
    public static final String TOWER_OUT_OF_ORDER_REASON_MANUAL = "ManuallyDisabled";
    public TowerAbilityCategoryRule[] towerAbilityCategoryRules = {new TowerAbilityCategoryRule(0, new int[]{4, 7}, false), new TowerAbilityCategoryRule(1, new int[]{10}, true), new TowerAbilityCategoryRule(2, new int[]{20}, false)};
    public int[] towerAbilityIdxToCategory = {0, 0, 0, 1, 2, 2};
    private int e = 1;
    public boolean[][] canTowerAttackEnemy = new boolean[EnemyType.values.length][TowerType.values.length];
    public float[][] towerEnemyDamageMultiplier = new float[EnemyType.values.length][TowerType.values.length];
    public DelayedRemovalArray<Tower> towers = new DelayedRemovalArray<>(false, 8, Tower.class);
    private float[] f = new float[TowerStatType.values.length];

    @NAGS
    private Tower.AimStrategy g = Tower.AimStrategy.FIRST;

    @NAGS
    private RangeCircle h;

    @NAGS
    private RangeCircle i;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3068a = TLog.forClass(TowerSystem.class);

    /* renamed from: b, reason: collision with root package name */
    private static final Color f3069b = new Color(MaterialColor.RED.P500).mul(1.0f, 1.0f, 1.0f, 0.14f);
    private static final Color c = new Color(MaterialColor.RED.P500).mul(1.0f, 1.0f, 1.0f, 0.78f);
    private static final boolean[][] d = new boolean[6][6];

    static {
        int i = 0;
        while (i < 6) {
            for (int i2 = 0; i2 < 6; i2++) {
                int i3 = i2;
                d[i][i3] = i3 != i;
            }
            i++;
        }
    }

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObject(output, this.towerAbilityCategoryRules);
        kryo.writeObject(output, this.towerAbilityIdxToCategory);
        output.writeVarInt(this.e, true);
        kryo.writeObject(output, this.canTowerAttackEnemy);
        kryo.writeObject(output, this.towerEnemyDamageMultiplier);
        kryo.writeObject(output, this.towers);
        kryo.writeObject(output, this.f);
    }

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.towerAbilityCategoryRules = (TowerAbilityCategoryRule[]) kryo.readObject(input, TowerAbilityCategoryRule[].class);
        this.towerAbilityIdxToCategory = (int[]) kryo.readObject(input, int[].class);
        this.e = input.readVarInt(true);
        this.canTowerAttackEnemy = (boolean[][]) kryo.readObject(input, boolean[][].class);
        this.towerEnemyDamageMultiplier = (float[][]) kryo.readObject(input, float[][].class);
        this.towers = (DelayedRemovalArray) kryo.readObject(input, DelayedRemovalArray.class);
        this.f = (float[]) kryo.readObject(input, float[].class);
    }

    @Override // com.prineside.tdi2.GameSystem
    public final boolean affectsGameState() {
        return true;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void setup() {
        for (int i = 0; i < Game.i.towerManager.canTowerAttackEnemy.length; i++) {
            boolean[] zArr = Game.i.towerManager.canTowerAttackEnemy[i];
            System.arraycopy(zArr, 0, this.canTowerAttackEnemy[i], 0, zArr.length);
        }
        for (int i2 = 0; i2 < Game.i.towerManager.towerEnemyDamageMultiplier.length; i2++) {
            float[] fArr = Game.i.towerManager.towerEnemyDamageMultiplier[i2];
            System.arraycopy(fArr, 0, this.towerEnemyDamageMultiplier[i2], 0, fArr.length);
        }
        for (TowerType towerType : TowerType.values) {
            Game.i.towerManager.getFactory(towerType).configureSystems(this.S);
        }
        this.S.events.getListeners(EnemyDie.class).addStateAffecting(new OnEnemyDie(this)).setDescription("TowerSystem - adds XP to the tower and increases Tower.enemiesKilled");
        this.S.events.getListeners(EnemyDie.class).addStateAffecting(new OnEnemyDieFlamethrowerPapers(this)).setDescription("TowerSystem - Flamethrower - gives papers for ultimate kills");
        this.S.events.getListeners(TowerPlace.class).addStateAffecting(new OnTowerPlace(this));
        this.S.events.getListeners(BuildingRemove.class).addStateAffecting(new OnBuildingRemove(this));
        this.S.events.getListeners(EnemyTakeDamage.class).addStateAffecting(new OnEnemyTakeDamage(this));
        this.S.events.getListeners(GameStateTick.class).addStateAffecting(new OnTickDisableTowersUnderEnemies(this)).setDescription("TowerSystem - Disables towers who have enemies on top of them");
        if (!this.S.CFG.headless) {
            a();
        }
    }

    private void a() {
        this.S._render.addLayer(new RenderSystem.Layer(GameRenderingOrder.TOWER_APPLY_INTERPOLATION, false, (batch, f, f2, f3) -> {
            applyDrawInterpolation(f3);
        }).setName("Tower-applyDrawInterpolation"));
        this.S._render.addLayer(new RenderSystem.Layer(GameRenderingOrder.TOWER_DRAW_WEAPONS, false, (batch2, f4, f5, f6) -> {
            drawWeapons(batch2, f5);
        }).setName("Tower-drawWeapons"));
        this.S._render.addLayer(new RenderSystem.Layer(GameRenderingOrder.TOWER_DRAW_BATCH, false, (batch3, f7, f8, f9) -> {
            a(batch3, f8);
        }).setName("Tower-drawBatch"));
        this.S._render.addLayer(new RenderSystem.Layer(GameRenderingOrder.TOWER_DRAW_BATCH_ADDITIVE, true, (batch4, f10, f11, f12) -> {
            drawBatchAdditive(batch4, f11);
        }).setName("Tower-drawBatchAdditive"));
        this.S._render.addLayer(new RenderSystem.Layer(GameRenderingOrder.TOWER_DRAW_RANGES, true, (batch5, f13, f14, f15) -> {
            drawRanges(batch5);
        }).setName("Tower-drawBatchAdditive"));
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void postSetup() {
        Arrays.fill(this.f, Float.MIN_VALUE);
        if (this.S == null) {
            throw new IllegalStateException("System is not registered");
        }
        for (TowerStatType towerStatType : TowerStatType.values) {
            float f = 0.0f;
            for (TowerType towerType : TowerType.values) {
                if (Game.i.towerManager.hasStat(towerType, towerStatType)) {
                    Tower create = Game.i.towerManager.getFactory(towerType).create();
                    create.setRegistered(this.S);
                    PlatformTile platformTile = (PlatformTile) Game.i.tileManager.getFactory(TileType.PLATFORM).create();
                    for (SpaceTileBonusType spaceTileBonusType : SpaceTileBonusType.values) {
                        platformTile.bonusType = spaceTileBonusType;
                        create.setTile(platformTile);
                        create.updateCache();
                        float stat = create.getStat(towerStatType);
                        if (stat > f) {
                            f = stat;
                        }
                    }
                    int[] iArr = Tower.LEVEL_EXPERIENCE_MILESTONES;
                    create.setExperience(iArr[iArr.length - 1] + 1.0f);
                    create.calculateXpLevel(false);
                    create.upgradeToLevel((byte) 10);
                    platformTile.bonusLevel = 5;
                    for (SpaceTileBonusType spaceTileBonusType2 : SpaceTileBonusType.values) {
                        platformTile.bonusType = spaceTileBonusType2;
                        create.setTile(platformTile);
                        for (boolean[] zArr : d) {
                            System.arraycopy(zArr, 0, create.installedAbilities, 0, zArr.length);
                            create.updateCache();
                            float stat2 = create.getStat(towerStatType);
                            if (stat2 > f) {
                                f = stat2;
                            }
                        }
                    }
                    create.setTile(null);
                    create.setUnregistered();
                }
            }
            this.f[towerStatType.ordinal()] = f;
        }
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void postStateRestore() {
        a();
    }

    public final boolean isRegistered(Tower tower) {
        return tower.isRegistered();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Tower tower) {
        this.S.gameState.checkGameplayUpdateAllowed();
        if (tower.isRegistered()) {
            throw new IllegalArgumentException("Tower is already registered");
        }
        int i = this.e;
        this.e = i + 1;
        tower.id = i;
        tower.setRegistered(this.S);
        this.towers.add(tower);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Tower tower) {
        this.S.gameState.checkGameplayUpdateAllowed();
        if (!tower.isRegistered()) {
            throw new IllegalArgumentException("Tower is not registered");
        }
        tower.setUnregistered();
        this.towers.removeValue(tower, true);
        updateAbilityAvailableParticleEffect(tower);
    }

    public final float getMaxPossibleStat(TowerStatType towerStatType) {
        return this.f[towerStatType.ordinal()];
    }

    public final Tower.AimStrategy getDefaultAimStrategy() {
        return this.g;
    }

    public final void setDefaultAimStrategy(Tower.AimStrategy aimStrategy) {
        this.g = aimStrategy;
        this.S.events.trigger(new TowersDefaultAimStrategyChange());
    }

    public final void selectTowerAbilityAction(Tower tower, int i) {
        selectTowerAbilityActionAt(tower.getTile().getX(), tower.getTile().getY(), i);
    }

    public final void selectGlobalTowerAbilityAction(Tower tower, int i) {
        selectGlobalTowerAbilityActionAt(tower.getTile().getX(), tower.getTile().getY(), i);
    }

    public final void customTowerButtonAction(Tower tower, int i, int i2) {
        this.S.gameState.pushActionNextUpdate(new CustomTowerButtonAction(tower.getTile().getX(), tower.getTile().getY(), i, i2));
    }

    public final void customTowerButtonActionAt(int i, int i2, int i3, int i4) {
        this.S.gameState.pushActionNextUpdate(new CustomTowerButtonAction(i, i2, i3, i4));
    }

    public final void selectTowerAbilityActionAt(int i, int i2, int i3) {
        this.S.gameState.pushActionNextUpdate(new SelectTowerAbilityAction(i3, i, i2));
    }

    public final void selectGlobalTowerAbilityActionAt(int i, int i2, int i3) {
        this.S.gameState.pushActionNextUpdate(new SelectGlobalTowerAbilityAction(i3, i, i2));
    }

    public final void setAbilityInstalled(Tower tower, int i, boolean z) {
        this.S.gameState.checkGameplayUpdateAllowed();
        if (tower.installedAbilities[i] == z) {
            return;
        }
        tower.installedAbilities[i] = z;
        this.S.map.markTilesDirtyNearTile(tower.getTile(), 1);
        this.S.events.trigger(new TowerAbilityChange(tower, i, z));
        tower.onAbilitySet(i, z);
        if (this.S._particle != null && Game.i.settingsManager.isParticlesDrawing()) {
            ParticleEffectPool.PooledEffect obtain = Game.i.towerManager.upgradeParticles.obtain();
            obtain.setPosition(tower.getTile().center.x, tower.getTile().center.y);
            this.S._particle.addParticle(obtain, true);
            ParticleEffectPool.PooledEffect obtain2 = Game.i.towerManager.highlightParticles[tower.type.ordinal()].obtain();
            obtain2.setPosition(tower.getTile().center.x, tower.getTile().center.y);
            this.S._particle.addParticle(obtain2, true);
        }
    }

    public final void buildTowerActionOnSelectedTile(TowerType towerType) {
        Tile selectedTile = this.S._gameMapSelection.getSelectedTile();
        if (selectedTile != null) {
            buildTowerAction(towerType, this.g, selectedTile.getX(), selectedTile.getY());
        }
    }

    public final boolean canTowersBeManuallyDisabled() {
        return this.S.gameValue.getBooleanValue(GameValueType.TOWERS_CAN_BE_MANUALLY_DISABLED);
    }

    public final void toggleTowerEnabledAction() {
        Tile selectedTile;
        if (canTowersBeManuallyDisabled() && (selectedTile = this.S._gameMapSelection.getSelectedTile()) != null && selectedTile.type == TileType.PLATFORM && (((PlatformTile) selectedTile).building instanceof Tower)) {
            this.S.gameState.pushActionNextUpdate(new ToggleTowerEnabledAction(selectedTile.getX(), selectedTile.getY()));
        }
    }

    public final void buildTowerActionWithAimStrategy(TowerType towerType, Tower.AimStrategy aimStrategy) {
        Tile selectedTile = this.S._gameMapSelection.getSelectedTile();
        if (selectedTile != null) {
            buildTowerAction(towerType, aimStrategy, selectedTile.getX(), selectedTile.getY());
        }
    }

    public final void buildTowerAction(TowerType towerType, Tower.AimStrategy aimStrategy, int i, int i2) {
        Tile tile;
        if (Game.i.towerManager.getFactory(towerType).isAvailable(this.S.gameValue) && (tile = this.S.map.getMap().getTile(i, i2)) != null && tile.type == TileType.PLATFORM && ((PlatformTile) tile).building == null) {
            if (this.S.gameState.getMoney() >= Game.i.towerManager.getFactory(towerType).getBuildPrice(this.S)) {
                this.S.gameState.pushActionNextUpdate(new BuildTowerAction(towerType, aimStrategy, i, i2));
            }
        }
    }

    public final Tower buildTower(TowerType towerType, @Null Tower.AimStrategy aimStrategy, int i, int i2) {
        return buildTowerIgnorePrice(towerType, aimStrategy, i, i2, false);
    }

    public final Tower buildTowerIgnorePrice(TowerType towerType, @Null Tower.AimStrategy aimStrategy, int i, int i2, boolean z) {
        this.S.gameState.checkGameplayUpdateAllowed();
        if (!z && !Game.i.towerManager.getFactory(towerType).isAvailable(this.S.gameValue)) {
            f3068a.e("buildTower - tower type " + towerType.name() + " is not available", new Object[0]);
            return null;
        }
        Tile tile = this.S.map.getMap().getTile(i, i2);
        if (tile == null) {
            f3068a.e("buildTower - no tile at " + i + ":" + i2, new Object[0]);
            return null;
        }
        if (aimStrategy == null) {
            aimStrategy = Tower.AimStrategy.FIRST;
        }
        if (tile.type != TileType.PLATFORM) {
            f3068a.e("buildTower - tile type is " + tile.type.name(), new Object[0]);
            return null;
        }
        if (((PlatformTile) tile).building == null) {
            int buildPrice = z ? 0 : Game.i.towerManager.getFactory(towerType).getBuildPrice(this.S);
            if (this.S.gameState.removeMoney(buildPrice)) {
                Tower create = Game.i.towerManager.getFactory(towerType).create();
                create.moneySpentOn = buildPrice;
                create.aimStrategy = aimStrategy;
                for (int i3 = 0; i3 < create.dpsDamage.length; i3++) {
                    create.dpsDamage[i3] = 0.0f;
                    int i4 = i3;
                    create.dpsTime[i4] = (i4 / 10.0f) * 10.0f;
                }
                this.S.map.setTower(tile.getX(), tile.getY(), create);
                create.experience = Tower.getLevelExperienceMilestone(Tower.getStartingLevel(towerType, this.S.gameValue));
                this.S.map.updateDirtyTiles();
                this.S.events.trigger(new TowerBuild(create, buildPrice));
                if (this.S._particle != null && Game.i.settingsManager.isParticlesDrawing()) {
                    ParticleEffectPool.PooledEffect obtain = Game.i.towerManager.highlightParticles[create.type.ordinal()].obtain();
                    obtain.setPosition(create.getTile().center.x, create.getTile().center.y);
                    this.S._particle.addParticle(obtain, true);
                }
                return create;
            }
            f3068a.e("buildTower - not enough money", new Object[0]);
            return null;
        }
        f3068a.e("buildTower - tile " + i + ":" + i2 + " already has a tower", new Object[0]);
        return null;
    }

    public final void upgradeTowerAction(Tower tower) {
        upgradeTowerActionAt(tower.getTile().getX(), tower.getTile().getY());
    }

    public final void upgradeTowerActionAt(int i, int i2) {
        Tile tile = this.S.map.getMap().getTile(i, i2);
        if (tile != null && tile.type == TileType.PLATFORM) {
            PlatformTile platformTile = (PlatformTile) tile;
            if (platformTile.building != null && platformTile.building.buildingType == BuildingType.TOWER && c((Tower) platformTile.building)) {
                this.S.gameState.pushActionNextUpdate(new UpgradeTowerAction(i, i2));
            }
        }
    }

    public final void globalUpgradeTowerAction(TowerType towerType) {
        this.S.gameState.pushActionNextUpdate(new GlobalUpgradeTowerAction(towerType));
    }

    private boolean c(Tower tower) {
        if (tower.getUpgradeLevel() >= tower.getMaxUpgradeLevel()) {
            return false;
        }
        return this.S.gameState.getMoney() >= getUpgradePrice(tower);
    }

    public final boolean upgradeTower(Tower tower) {
        return upgradeTowerTakeCoins(tower, true);
    }

    public final boolean upgradeTowerTakeCoins(Tower tower, boolean z) {
        boolean z2;
        this.S.gameState.checkGameplayUpdateAllowed();
        if (tower.getUpgradeLevel() >= tower.getMaxUpgradeLevel()) {
            return false;
        }
        int upgradePrice = getUpgradePrice(tower);
        if (z) {
            z2 = this.S.gameState.removeMoney(upgradePrice);
        } else {
            z2 = true;
        }
        if (z2) {
            if (z) {
                tower.moneySpentOn += upgradePrice;
            } else {
                upgradePrice = 0;
            }
            tower.upgrade();
            this.S.events.trigger(new TowerUpgrade(tower, upgradePrice));
            if (this.S._particle != null && Game.i.settingsManager.isParticlesDrawing()) {
                ParticleEffectPool.PooledEffect obtain = Game.i.towerManager.upgradeParticles.obtain();
                obtain.setPosition(tower.getTile().center.x, tower.getTile().center.y);
                this.S._particle.addParticle(obtain, true);
                ParticleEffectPool.PooledEffect obtain2 = Game.i.towerManager.highlightParticles[tower.type.ordinal()].obtain();
                obtain2.setPosition(tower.getTile().center.x, tower.getTile().center.y);
                this.S._particle.addParticle(obtain2, true);
                return true;
            }
            return true;
        }
        return false;
    }

    public final void sellTowerAction(Tower tower) {
        if (tower.isOutOfOrder()) {
            return;
        }
        this.S.gameState.pushActionNextUpdate(new SellTowerAction(tower.getTile().getX(), tower.getTile().getY()));
    }

    public final boolean sellTower(Tower tower) {
        this.S.gameState.checkGameplayUpdateAllowed();
        tower.onPreSell();
        int sellPrice = tower.getSellPrice();
        this.S.events.trigger(new TowerPreSell(tower, sellPrice));
        this.S.gameState.addMoney(sellPrice, false);
        this.S.map.removeBuilding(tower);
        this.S.events.trigger(new TowerSell(tower, sellPrice));
        return true;
    }

    public final void setTowerAimStrategyAction(Tower tower, Tower.AimStrategy aimStrategy) {
        this.S.gameState.pushActionNextUpdate(new ChangeTowerAimStrategyAction(tower.getTile().getX(), tower.getTile().getY(), aimStrategy));
    }

    public final void setTowerAimStrategy(Tower tower, Tower.AimStrategy aimStrategy) {
        this.S.gameState.checkGameplayUpdateAllowed();
        tower.setAimStrategy(aimStrategy);
        tower.setTarget(null);
        this.S.events.trigger(new TowerAimStrategyChange(tower));
    }

    public final int getUpgradePrice(Tower tower) {
        int upgradeLevel = tower.getUpgradeLevel() + 1;
        if (upgradeLevel <= tower.getMaxUpgradeLevel()) {
            int upgradePrice = Game.i.towerManager.getUpgradePrice(tower.type, upgradeLevel, this.S.gameValue);
            float upgradePriceMultiplier = Game.i.towerManager.getUpgradePriceMultiplier(tower.type);
            if (upgradePriceMultiplier != 1.0f) {
                float f = 1.0f;
                for (int i = 0; i < this.towers.size; i++) {
                    if (this.towers.items[i].type == tower.type && this.towers.items[i].getUpgradeLevel() >= upgradeLevel) {
                        f *= upgradePriceMultiplier;
                    }
                }
                upgradePrice = (int) (upgradePrice * f);
            }
            if (tower.getTile() != null && tower.getTile().bonusType == SpaceTileBonusType.UPGRADE_DISCOUNT && tower.getTile().bonusLevel > 0) {
                upgradePrice = (int) (upgradePrice * SpaceTileBonus.getEffect(SpaceTileBonusType.UPGRADE_DISCOUNT, tower.getTile().bonusLevel));
            }
            return upgradePrice;
        }
        return 0;
    }

    public final int getBaseUpgradePrice(Tower tower, int i) {
        if (i > tower.getMaxUpgradeLevel()) {
            return 0;
        }
        return Game.i.towerManager.getUpgradePrice(tower.type, i, this.S.gameValue);
    }

    public final int getGlobalUpgradePrice(TowerType towerType) {
        float upgradePriceMultiplier = Game.i.towerManager.getUpgradePriceMultiplier(towerType);
        if (upgradePriceMultiplier == 1.0f) {
            int i = 0;
            for (int i2 = 0; i2 < this.towers.size; i2++) {
                Tower tower = this.towers.get(i2);
                if (tower.type == towerType && tower.getUpgradeLevel() < tower.getMaxUpgradeLevel()) {
                    i += getUpgradePrice(tower);
                }
            }
            return i;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.towers.size; i4++) {
            Tower tower2 = this.towers.items[i4];
            if (tower2.type == towerType && tower2.getUpgradeLevel() < tower2.getMaxUpgradeLevel()) {
                int upgradeLevel = tower2.getUpgradeLevel() + 1;
                float f = 1.0f;
                for (int i5 = 0; i5 < this.towers.size; i5++) {
                    Tower tower3 = this.towers.items[i5];
                    if (tower3.type == towerType) {
                        int upgradeLevel2 = tower3.getUpgradeLevel();
                        if (i5 < i4 && upgradeLevel2 < tower3.getMaxUpgradeLevel()) {
                            upgradeLevel2++;
                        }
                        if (upgradeLevel2 >= upgradeLevel) {
                            f *= upgradePriceMultiplier;
                        }
                    }
                }
                int baseUpgradePrice = (int) (getBaseUpgradePrice(tower2, upgradeLevel) * f);
                if (tower2.getTile() != null && tower2.getTile().bonusType == SpaceTileBonusType.UPGRADE_DISCOUNT && tower2.getTile().bonusLevel > 0) {
                    baseUpgradePrice = (int) (baseUpgradePrice * SpaceTileBonus.getEffect(SpaceTileBonusType.UPGRADE_DISCOUNT, tower2.getTile().bonusLevel));
                }
                i3 += baseUpgradePrice;
            }
        }
        return i3;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void update(float f) {
        StateSystem.ActionsArray currentUpdateActions = this.S.gameState.getCurrentUpdateActions();
        if (currentUpdateActions != null) {
            for (int i = 0; i < currentUpdateActions.size; i++) {
                Action action = currentUpdateActions.actions[i];
                if (action.getType() != ActionType.BT) {
                    if (action.getType() != ActionType.UT) {
                        if (action.getType() == ActionType.TTE) {
                            if (canTowersBeManuallyDisabled()) {
                                ToggleTowerEnabledAction toggleTowerEnabledAction = (ToggleTowerEnabledAction) action;
                                Tile tile = this.S.map.getMap().getTile(toggleTowerEnabledAction.x, toggleTowerEnabledAction.y);
                                if (tile != null && tile.type == TileType.PLATFORM) {
                                    PlatformTile platformTile = (PlatformTile) tile;
                                    if (platformTile.building != null && platformTile.building.buildingType == BuildingType.TOWER) {
                                        Tower tower = (Tower) platformTile.building;
                                        if (tower.outOfOrder.hasReason(TOWER_OUT_OF_ORDER_REASON_MANUAL)) {
                                            tower.outOfOrder.removeReason(TOWER_OUT_OF_ORDER_REASON_MANUAL);
                                        } else {
                                            tower.outOfOrder.addReason(TOWER_OUT_OF_ORDER_REASON_MANUAL);
                                        }
                                    }
                                }
                            }
                        } else if (action.getType() != ActionType.GUT) {
                            if (action.getType() != ActionType.ST) {
                                if (action.getType() != ActionType.CTAS) {
                                    if (action.getType() != ActionType.STA) {
                                        if (action.getType() != ActionType.SGTA) {
                                            if (action.getType() == ActionType.CTB) {
                                                CustomTowerButtonAction customTowerButtonAction = (CustomTowerButtonAction) action;
                                                Tile tile2 = this.S.map.getMap().getTile(customTowerButtonAction.x, customTowerButtonAction.y);
                                                if (tile2 != null && tile2.type == TileType.PLATFORM) {
                                                    PlatformTile platformTile2 = (PlatformTile) tile2;
                                                    if (platformTile2.building != null && platformTile2.building.buildingType == BuildingType.TOWER) {
                                                        Tower tower2 = (Tower) platformTile2.building;
                                                        if (tower2.hasCustomButton()) {
                                                            tower2.customButtonAction(customTowerButtonAction.mapX, customTowerButtonAction.mapY);
                                                            this.S.events.trigger(new TowerCustomButtonPress(tower2));
                                                        }
                                                    }
                                                }
                                            }
                                        } else {
                                            SelectGlobalTowerAbilityAction selectGlobalTowerAbilityAction = (SelectGlobalTowerAbilityAction) action;
                                            Tile tile3 = this.S.map.getMap().getTile(selectGlobalTowerAbilityAction.x, selectGlobalTowerAbilityAction.y);
                                            if (tile3 != null && tile3.type == TileType.PLATFORM) {
                                                PlatformTile platformTile3 = (PlatformTile) tile3;
                                                if (platformTile3.building != null && platformTile3.building.buildingType == BuildingType.TOWER) {
                                                    Tower tower3 = (Tower) platformTile3.building;
                                                    for (int i2 = 0; i2 < this.towers.size; i2++) {
                                                        Tower tower4 = this.towers.get(i2);
                                                        if (tower4.type == tower3.type && tower4.canAbilityBeInstalled(selectGlobalTowerAbilityAction.abilityIndex)) {
                                                            setAbilityInstalled(tower4, selectGlobalTowerAbilityAction.abilityIndex, true);
                                                            if (!tower4.isSellFullRefundStillActive() && tower4.getUpgradeLevel() > 0) {
                                                                this.S.gameState.registerPlayerActivity();
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        SelectTowerAbilityAction selectTowerAbilityAction = (SelectTowerAbilityAction) action;
                                        Tile tile4 = this.S.map.getMap().getTile(selectTowerAbilityAction.x, selectTowerAbilityAction.y);
                                        if (tile4 != null && tile4.type == TileType.PLATFORM) {
                                            PlatformTile platformTile4 = (PlatformTile) tile4;
                                            if (platformTile4.building != null && platformTile4.building.buildingType == BuildingType.TOWER) {
                                                Tower tower5 = (Tower) platformTile4.building;
                                                if (tower5.canAbilityBeInstalled(selectTowerAbilityAction.abilityIndex)) {
                                                    setAbilityInstalled(tower5, selectTowerAbilityAction.abilityIndex, true);
                                                    if (!tower5.isSellFullRefundStillActive() && tower5.getUpgradeLevel() > 0) {
                                                        this.S.gameState.registerPlayerActivity();
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    ChangeTowerAimStrategyAction changeTowerAimStrategyAction = (ChangeTowerAimStrategyAction) action;
                                    Tile tile5 = this.S.map.getMap().getTile(changeTowerAimStrategyAction.x, changeTowerAimStrategyAction.y);
                                    if (tile5 != null && tile5.type == TileType.PLATFORM) {
                                        PlatformTile platformTile5 = (PlatformTile) tile5;
                                        if (platformTile5.building != null && platformTile5.building.buildingType == BuildingType.TOWER) {
                                            setTowerAimStrategy((Tower) platformTile5.building, changeTowerAimStrategyAction.aimStrategy);
                                        }
                                    }
                                }
                            } else {
                                SellTowerAction sellTowerAction = (SellTowerAction) action;
                                Tile tile6 = this.S.map.getMap().getTile(sellTowerAction.x, sellTowerAction.y);
                                if (tile6 != null && tile6.type == TileType.PLATFORM) {
                                    PlatformTile platformTile6 = (PlatformTile) tile6;
                                    if (platformTile6.building != null && platformTile6.building.buildingType == BuildingType.TOWER) {
                                        Tower tower6 = (Tower) platformTile6.building;
                                        if (!tower6.isSellFullRefundStillActive() && tower6.getUpgradeLevel() > 0) {
                                            this.S.gameState.registerPlayerActivity();
                                        }
                                        sellTower(tower6);
                                    }
                                }
                            }
                        } else {
                            GlobalUpgradeTowerAction globalUpgradeTowerAction = (GlobalUpgradeTowerAction) action;
                            boolean z = false;
                            for (int i3 = 0; i3 < this.towers.size; i3++) {
                                Tower tower7 = this.towers.get(i3);
                                if (tower7.type == globalUpgradeTowerAction.towerType && upgradeTower(tower7)) {
                                    z = true;
                                }
                            }
                            if (z) {
                                this.S.gameState.registerPlayerActivity();
                            }
                        }
                    } else {
                        UpgradeTowerAction upgradeTowerAction = (UpgradeTowerAction) action;
                        Tile tile7 = this.S.map.getMap().getTile(upgradeTowerAction.x, upgradeTowerAction.y);
                        if (tile7 != null && tile7.type == TileType.PLATFORM) {
                            PlatformTile platformTile7 = (PlatformTile) tile7;
                            if (platformTile7.building != null && platformTile7.building.buildingType == BuildingType.TOWER && upgradeTower((Tower) platformTile7.building)) {
                                this.S.gameState.registerPlayerActivity();
                            }
                        }
                    }
                } else {
                    BuildTowerAction buildTowerAction = (BuildTowerAction) action;
                    if (buildTower(buildTowerAction.towerType, buildTowerAction.aimStrategy, buildTowerAction.x, buildTowerAction.y) != null) {
                        this.S.gameState.registerPlayerActivity();
                    }
                }
            }
        }
        this.towers.begin();
        int i4 = this.towers.size;
        for (int i5 = 0; i5 < i4; i5++) {
            Tower tower8 = this.towers.items[i5];
            tower8.update(f);
            a(tower8, f);
        }
        this.towers.end();
    }

    private static void a(Tower tower, float f) {
        for (int i = 0; i < tower.dpsTime.length; i++) {
            float[] fArr = tower.dpsTime;
            int i2 = i;
            fArr[i2] = fArr[i2] + f;
            if (tower.dpsTime[i] >= 10.0f) {
                tower.dpsTime[i] = 0.0f;
                tower.dpsDamage[i] = 0.0f;
            }
        }
    }

    @Override // com.prineside.tdi2.GameSystem
    public final String getSystemName() {
        return "Tower";
    }

    private void a(Batch batch, float f) {
        this.towers.begin();
        int i = this.towers.size;
        for (int i2 = 0; i2 < i; i2++) {
            Tower tower = this.towers.items[i2];
            if (!tower.isOutOfOrder()) {
                tower.drawBatch(batch, f);
            }
            updateAbilityAvailableParticleEffect(tower);
        }
        this.towers.end();
        this.towers.begin();
        int i3 = this.towers.size;
        for (int i4 = 0; i4 < i3; i4++) {
            if (this.towers.items[i4].isOutOfOrder()) {
                this.towers.items[i4].drawGlitch(batch);
            }
        }
        this.towers.end();
        batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
    }

    public final void drawBatchAdditive(Batch batch, float f) {
        this.towers.begin();
        int i = this.towers.size;
        for (int i2 = 0; i2 < i; i2++) {
            this.towers.items[i2].drawBatchAdditive(batch, f);
        }
        this.towers.end();
    }

    public final void drawRanges(Batch batch) {
        Enemy target;
        Tile selectedTile = this.S._gameMapSelection.getSelectedTile();
        Tile hoveredTile = this.S._gameMapSelection.getHoveredTile();
        if (selectedTile != null) {
            if (this.h == null) {
                this.h = (RangeCircle) Game.i.shapeManager.getFactory(ShapeType.RANGE_CIRCLE).obtain();
            }
            selectedTile.drawSelectedRange(batch, this.h);
        }
        if (hoveredTile != null) {
            if (this.i == null) {
                this.i = (RangeCircle) Game.i.shapeManager.getFactory(ShapeType.RANGE_CIRCLE).obtain();
            }
            hoveredTile.drawHoveredRange(batch, this.i);
        }
        Vector2 vector2 = null;
        if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DRAW_TOWER_TARGET) != 0.0d && selectedTile != null && selectedTile.type == TileType.PLATFORM) {
            PlatformTile platformTile = (PlatformTile) selectedTile;
            if (platformTile.building != null && platformTile.building.buildingType == BuildingType.TOWER && (target = ((Tower) platformTile.building).getTarget()) != null) {
                DrawUtils.texturedLineD(batch, Game.i.assetManager.getBlankWhiteTextureRegion(), platformTile.center.x, platformTile.center.y, target.getPosition().x, target.getPosition().y, 1.0f, 5.0f, f3069b, c);
                vector2 = target.getPosition();
            }
        }
        this.S._render.prepareBatch(batch, false);
        if (vector2 != null) {
            batch.draw(AssetManager.TextureRegions.i().crosshairSmall, vector2.x - 12.0f, vector2.y - 12.0f, 24.0f, 24.0f);
        }
    }

    public final void applyDrawInterpolation(float f) {
        if (f < 0.0f) {
            f = 0.0f;
        }
        if (f > 1.0f) {
            f = 1.0f;
        }
        int i = this.towers.size;
        for (int i2 = 0; i2 < i; i2++) {
            this.towers.items[i2].applyDrawInterpolation(f);
        }
    }

    public final void drawWeapons(Batch batch, float f) {
        if (this.S._mapRendering.getDrawMode() == MapRenderingSystem.DrawMode.DETAILED) {
            batch.setColor(0.0f, 0.0f, 0.0f, 0.21f);
        }
        this.towers.begin();
        int i = this.towers.size;
        for (int i2 = 0; i2 < i; i2++) {
            this.towers.items[i2].drawWeapon(batch, r0.getTile().boundingBox.minX, r0.getTile().boundingBox.minY, 128.0f, f);
        }
        this.towers.end();
    }

    public final void traverseTilesInRange(Tower tower, ObjectFilter<Tile> objectFilter) {
        Vector2 vector2 = tower.getTile().center;
        float f = tower.rangeInPixels;
        DelayedRemovalArray<Tile> allTiles = this.S.map.getMap().getAllTiles();
        for (int i = 0; i < allTiles.size; i++) {
            Tile tile = allTiles.items[i];
            if (PMath.circleIntersectsRect(vector2.x, vector2.y, f, tile.center.x - 64.0f, tile.center.y - 64.0f, 128.0f, 128.0f) && !objectFilter.test(tile)) {
                return;
            }
        }
    }

    public final void updateAbilityAvailableParticleEffect(Tower tower) {
        if (this.S._particle == null || tower == null) {
            return;
        }
        if (!tower.isRegistered()) {
            if (tower.abilityAvailableParticleEffect == null) {
                return;
            }
        } else {
            if (tower.abilityAvailableParticleEffect == null) {
                if (tower.canNewAbilityBeInstalled() && tower.getTile() != null) {
                    tower.abilityAvailableParticleEffect = Game.i.towerManager.abilityAvailableParticleEffectPool.obtain();
                    tower.abilityAvailableParticleEffect.setPosition(tower.getTile().center.x + 32.0f, tower.getTile().center.y - 42.24f);
                    this.S._particle.addParticle(tower.abilityAvailableParticleEffect, false);
                    return;
                }
                return;
            }
            if (tower.canNewAbilityBeInstalled() && tower.getTile() != null) {
                return;
            }
        }
        tower.abilityAvailableParticleEffect.allowCompletion();
        tower.abilityAvailableParticleEffect = null;
    }

    @Override // com.prineside.tdi2.GameSystem, com.badlogic.gdx.utils.Disposable
    public final void dispose() {
        this.towers.begin();
        for (int i = 0; i < this.towers.size; i++) {
            this.towers.get(i).setUnregistered();
        }
        this.towers.end();
        this.towers.clear();
        super.dispose();
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/TowerSystem$OnEnemyDie.class */
    public static final class OnEnemyDie extends SerializableListener<TowerSystem> implements Listener<EnemyDie> {
        private OnEnemyDie() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public OnEnemyDie(TowerSystem towerSystem) {
            this.f1759a = towerSystem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(EnemyDie enemyDie) {
            Tower tower = enemyDie.getLastDamage().getTower();
            if (tower != null) {
                ((TowerSystem) this.f1759a).S.statistics.addStatistic(StatisticsType.XPG_EK, ((TowerSystem) this.f1759a).S.experience.addExperienceBuffed(tower, enemyDie.getLastDamage().getEnemy().getKillExp()));
                tower.enemiesKilled++;
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/TowerSystem$OnEnemyDieFlamethrowerPapers.class */
    public static final class OnEnemyDieFlamethrowerPapers extends SerializableListener<TowerSystem> implements Listener<EnemyDie> {

        /* renamed from: b, reason: collision with root package name */
        @NAGS
        private final Vector2 f3070b = new Vector2();

        private OnEnemyDieFlamethrowerPapers() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public OnEnemyDieFlamethrowerPapers(TowerSystem towerSystem) {
            this.f1759a = towerSystem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(EnemyDie enemyDie) {
            Tower tower = enemyDie.getLastDamage().getTower();
            if (tower instanceof FlamethrowerTower) {
                FlamethrowerTower flamethrowerTower = (FlamethrowerTower) tower;
                if (flamethrowerTower.isAbilityInstalled(4)) {
                    this.f3070b.set(enemyDie.getLastDamage().getEnemy().getPosition());
                    int papersFromFlamethrowerUltBank = ((TowerSystem) this.f1759a).S.loot.getPapersFromFlamethrowerUltBank((int) (((int) ((((TowerSystem) this.f1759a).S.gameState.calculatePrizeGreenPapers() / ((TowerSystem) this.f1759a).S.statistics.getStatistic(StatisticsType.PT)) * 60.0d)) / 30.0f));
                    if (papersFromFlamethrowerUltBank > 0) {
                        flamethrowerTower.instaKillPapersAccumulator += papersFromFlamethrowerUltBank;
                        if (flamethrowerTower.instaKillPapersAccumulator > 5) {
                            int i = flamethrowerTower.instaKillPapersAccumulator % 5;
                            ItemStack itemStack = new ItemStack(Item.D.GREEN_PAPER, flamethrowerTower.instaKillPapersAccumulator - i);
                            if (((TowerSystem) this.f1759a).S._input != null) {
                                ((TowerSystem) this.f1759a).S._input.cameraController.mapToStage(this.f3070b);
                            }
                            ((TowerSystem) this.f1759a).S.gameState.addLootIssuedPrizes(itemStack, this.f3070b.x, this.f3070b.y, 2);
                            flamethrowerTower.instaKillPapersAccumulator = i;
                        }
                    }
                }
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/TowerSystem$OnEnemyTakeDamage.class */
    public static final class OnEnemyTakeDamage extends SerializableListener<TowerSystem> implements Listener<EnemyTakeDamage> {
        private OnEnemyTakeDamage() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public OnEnemyTakeDamage(TowerSystem towerSystem) {
            this.f1759a = towerSystem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(EnemyTakeDamage enemyTakeDamage) {
            DamageRecord record = enemyTakeDamage.getRecord();
            Tower tower = record.getTower();
            float factDamage = record.getFactDamage();
            if (tower != null && factDamage > 0.0f) {
                Enemy enemy = record.getEnemy();
                ((TowerSystem) this.f1759a).S.statistics.addStatistic(StatisticsType.XPG_EK, ((TowerSystem) this.f1759a).S.experience.addExperienceBuffed(tower, (factDamage / enemy.maxHealth) * enemy.getKillExp() * 2.0f));
                if (record.isCleanForDps()) {
                    for (int i = 0; i < tower.dpsDamage.length; i++) {
                        float[] fArr = tower.dpsDamage;
                        int i2 = i;
                        fArr[i2] = fArr[i2] + factDamage;
                        float f = tower.dpsDamage[i] / 10.0f;
                        if (f > tower.mdps) {
                            tower.mdps = f;
                        }
                    }
                }
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/TowerSystem$OnTickDisableTowersUnderEnemies.class */
    public static final class OnTickDisableTowersUnderEnemies extends SerializableListener<TowerSystem> implements Listener<GameStateTick> {
        private OnTickDisableTowersUnderEnemies() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public OnTickDisableTowersUnderEnemies(TowerSystem towerSystem) {
            this.f1759a = towerSystem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(GameStateTick gameStateTick) {
            for (int i = 0; i < ((TowerSystem) this.f1759a).towers.size; i++) {
                Tower tower = ((TowerSystem) this.f1759a).towers.items[i];
                if (tower != null) {
                    PlatformTile tile = tower.getTile();
                    if (tile != null && tile.towerDisablingEnemyCount != 0) {
                        tower.outOfOrder.addReason("EnemyOnTop");
                    } else {
                        tower.outOfOrder.removeReason("EnemyOnTop");
                    }
                }
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/TowerSystem$OnTowerPlace.class */
    public static final class OnTowerPlace extends SerializableListener<TowerSystem> implements Listener<TowerPlace> {
        private OnTowerPlace() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public OnTowerPlace(TowerSystem towerSystem) {
            this.f1759a = towerSystem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(TowerPlace towerPlace) {
            ((TowerSystem) this.f1759a).a(towerPlace.getTower());
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/TowerSystem$OnBuildingRemove.class */
    public static final class OnBuildingRemove extends SerializableListener<TowerSystem> implements Listener<BuildingRemove> {
        private OnBuildingRemove() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public OnBuildingRemove(TowerSystem towerSystem) {
            this.f1759a = towerSystem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(BuildingRemove buildingRemove) {
            if (buildingRemove.getBuilding().buildingType == BuildingType.TOWER) {
                ((TowerSystem) this.f1759a).b((Tower) buildingRemove.getBuilding());
            }
        }
    }

    @REGS(arrayLevels = 1)
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/TowerSystem$TowerAbilityCategoryRule.class */
    public static final class TowerAbilityCategoryRule implements KryoSerializable {
        public int categoryId;
        public IntArray requiredXpLevels = new IntArray();
        public boolean autoInstallSingleVariant;

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public final void write(Kryo kryo, Output output) {
            output.writeInt(this.categoryId);
            kryo.writeObject(output, this.requiredXpLevels);
            output.writeBoolean(this.autoInstallSingleVariant);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public final void read(Kryo kryo, Input input) {
            this.categoryId = input.readInt();
            this.requiredXpLevels = (IntArray) kryo.readObject(input, IntArray.class);
            this.autoInstallSingleVariant = input.readBoolean();
        }

        private TowerAbilityCategoryRule() {
        }

        public TowerAbilityCategoryRule(int i, int[] iArr, boolean z) {
            this.categoryId = i;
            this.requiredXpLevels.addAll(iArr);
            this.autoInstallSingleVariant = z;
        }
    }
}
