package com.prineside.tdi2.utils.simulation;

import com.badlogic.gdx.utils.Array;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.GameValueConfig;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.SpaceTileBonusType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.tiles.PlatformTile;
import com.prineside.tdi2.towers.GaussTower;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/simulation/TowersBenchmarkScenario.class */
public class TowersBenchmarkScenario implements Scenario {
    public GameSystemProvider S;
    public final int waves;
    public final TowerType towerType;
    public final Tower.AimStrategy aimStrategy;
    public final int[] abilities;
    public final float angle;
    public final int xpLevel;
    public final int upgradeLevel;
    public final ExtraTowers extraTowers;

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/simulation/TowersBenchmarkScenario$ExtraTowers.class */
    public enum ExtraTowers {
        NONE,
        FREEZING,
        BLAST,
        FREEZING_BLAST
    }

    public TowersBenchmarkScenario(int i, TowerType towerType, int[] iArr, Tower.AimStrategy aimStrategy, float f, int i2, int i3, ExtraTowers extraTowers) {
        this.waves = i;
        this.towerType = towerType;
        this.aimStrategy = aimStrategy;
        this.abilities = iArr;
        this.angle = f;
        this.xpLevel = i2;
        this.upgradeLevel = i3;
        this.extraTowers = extraTowers;
    }

    @Override // com.prineside.tdi2.utils.simulation.Scenario
    public void setGSP(GameSystemProvider gameSystemProvider) {
        this.S = gameSystemProvider;
    }

    @Override // com.prineside.tdi2.utils.simulation.Scenario
    public void start(GameSystemProvider gameSystemProvider) {
        this.S = gameSystemProvider;
        gameSystemProvider.wave.startNextWave();
        gameSystemProvider.gameValue.addCustomGameValue(new GameValueConfig(GameValueType.TOWERS_STARTING_LEVEL, this.xpLevel, true, false));
        gameSystemProvider.gameValue.addCustomGameValue(new GameValueConfig(GameValueType.TOWERS_MAX_EXP_LEVEL, this.xpLevel, true, false));
        gameSystemProvider.gameValue.addCustomGameValue(new GameValueConfig(Game.i.towerManager.getMaxExpLevelGameValueType(this.towerType), 0.0d, true, false));
        gameSystemProvider.gameValue.recalculate();
        Array tilesByType = gameSystemProvider.map.getMap().getTilesByType(PlatformTile.class);
        for (int i = 0; i < tilesByType.size; i++) {
            PlatformTile platformTile = (PlatformTile) tilesByType.get(i);
            if (platformTile.bonusLevel != 0) {
                if (platformTile.bonusType != SpaceTileBonusType.BONUS_EXPERIENCE || (this.extraTowers != ExtraTowers.FREEZING && this.extraTowers != ExtraTowers.FREEZING_BLAST)) {
                    if (platformTile.bonusType == SpaceTileBonusType.SELL_REFUND && (this.extraTowers == ExtraTowers.BLAST || this.extraTowers == ExtraTowers.FREEZING_BLAST)) {
                        Tower buildTower = gameSystemProvider.tower.buildTower(TowerType.BLAST, Tower.AimStrategy.FIRST, platformTile.getX(), platformTile.getY());
                        for (int i2 = 0; i2 < 6; i2++) {
                            gameSystemProvider.tower.setAbilityInstalled(buildTower, i2, true);
                        }
                        for (int i3 = 0; i3 < 10; i3++) {
                            gameSystemProvider.tower.upgradeTower(buildTower);
                        }
                        buildTower.moneySpentOn = 0;
                    }
                } else {
                    Tower buildTower2 = gameSystemProvider.tower.buildTower(TowerType.FREEZING, Tower.AimStrategy.FIRST, platformTile.getX(), platformTile.getY());
                    for (int i4 = 0; i4 < 6; i4++) {
                        gameSystemProvider.tower.setAbilityInstalled(buildTower2, i4, true);
                    }
                    for (int i5 = 0; i5 < 10; i5++) {
                        gameSystemProvider.tower.upgradeTower(buildTower2);
                    }
                    buildTower2.moneySpentOn = 0;
                }
            } else if (platformTile.building == null) {
                Tower buildTower3 = gameSystemProvider.tower.buildTower(this.towerType, this.aimStrategy, platformTile.getX(), platformTile.getY());
                if (this.towerType == TowerType.GAUSS) {
                    ((GaussTower) buildTower3).setTargetAngle(this.angle);
                }
                buildTower3.angle = this.angle;
                for (int i6 : this.abilities) {
                    gameSystemProvider.tower.setAbilityInstalled(buildTower3, i6, true);
                }
                for (int i7 = 0; i7 < this.upgradeLevel; i7++) {
                    gameSystemProvider.tower.upgradeTower(buildTower3);
                }
            }
        }
    }

    @Override // com.prineside.tdi2.utils.simulation.Scenario
    public boolean isFinished() {
        return this.S.wave.getCompletedWavesCount() >= this.waves;
    }

    @Override // com.prineside.tdi2.utils.simulation.Scenario
    public float getProgress() {
        return this.S.wave.getCompletedWavesCount() / this.waves;
    }

    @Override // com.prineside.tdi2.utils.simulation.Scenario
    public void onUpdate() {
    }

    @Override // com.prineside.tdi2.utils.simulation.Scenario
    public TowersBenchmarkScenario cpy() {
        return new TowersBenchmarkScenario(this.waves, this.towerType, this.abilities, this.aimStrategy, this.angle, this.xpLevel, this.upgradeLevel, this.extraTowers);
    }
}
