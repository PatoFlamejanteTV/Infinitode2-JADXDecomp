package com.prineside.tdi2.utils.simulation;

import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.Array;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.systems.BonusSystem;
import com.prineside.tdi2.tiles.PlatformTile;
import com.prineside.tdi2.towers.GaussTower;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/simulation/BuildTowerScenario.class */
public final class BuildTowerScenario implements Scenario {

    /* renamed from: a, reason: collision with root package name */
    private GameSystemProvider f3959a;

    /* renamed from: b, reason: collision with root package name */
    private final int f3960b;
    private final TowerType c;
    private final Tower.AimStrategy d;
    private final int[] e;
    private final float f;
    private final int g;
    private final int h;
    private final RandomXS128 i;

    public BuildTowerScenario(int i, TowerType towerType, int[] iArr, Tower.AimStrategy aimStrategy, float f, int i2, int i3) {
        this.f3960b = i;
        this.c = towerType;
        this.d = aimStrategy;
        this.e = iArr;
        this.f = f;
        this.g = i2;
        this.h = i3;
        this.i = new RandomXS128(i3);
    }

    @Override // com.prineside.tdi2.utils.simulation.Scenario
    public final void start(GameSystemProvider gameSystemProvider) {
        this.f3959a = gameSystemProvider;
        gameSystemProvider.wave.startNextWave();
        gameSystemProvider.gameState.addMoney(1000000.0f, false);
        Array tilesByType = gameSystemProvider.map.getMap().getTilesByType(PlatformTile.class);
        if (tilesByType.size != 0) {
            PlatformTile platformTile = (PlatformTile) tilesByType.first();
            Tower buildTower = gameSystemProvider.tower.buildTower(this.c, this.d, platformTile.getX(), platformTile.getY());
            if (buildTower != null) {
                if (this.c == TowerType.GAUSS) {
                    ((GaussTower) buildTower).setTargetAngle(this.f);
                }
                buildTower.angle = this.f;
                for (int i : this.e) {
                    gameSystemProvider.tower.setAbilityInstalled(buildTower, i, true);
                }
                for (int i2 = 0; i2 < this.g; i2++) {
                    gameSystemProvider.tower.upgradeTower(buildTower);
                }
            }
        }
    }

    @Override // com.prineside.tdi2.utils.simulation.Scenario
    public final void setGSP(GameSystemProvider gameSystemProvider) {
        this.f3959a = gameSystemProvider;
    }

    @Override // com.prineside.tdi2.utils.simulation.Scenario
    public final boolean isFinished() {
        return this.f3959a.gameState.updateNumber >= this.f3960b || this.f3959a.gameState.isGameOver();
    }

    @Override // com.prineside.tdi2.utils.simulation.Scenario
    public final float getProgress() {
        return this.f3959a.gameState.updateNumber / this.f3960b;
    }

    @Override // com.prineside.tdi2.utils.simulation.Scenario
    public final void onUpdate() {
        BonusSystem.BonusStage stageToChooseBonusFor;
        if (this.f3959a.bonus.isEnabled() && this.f3959a.bonus.bonusSelectionAvailable() && (stageToChooseBonusFor = this.f3959a.bonus.getStageToChooseBonusFor()) != null) {
            int nextInt = this.i.nextInt(stageToChooseBonusFor.getBonusesToChooseFrom().size);
            if (stageToChooseBonusFor.getBonusesToChooseFrom().get(nextInt).getNotSatisfiedPreconditions(this.f3959a) == null) {
                this.f3959a.bonus.selectBonusAction(nextInt);
            }
        }
    }

    @Override // com.prineside.tdi2.utils.simulation.Scenario
    public final Scenario cpy() {
        return new BuildTowerScenario(this.f3960b, this.c, this.e, this.d, this.f, this.g, this.h);
    }
}
