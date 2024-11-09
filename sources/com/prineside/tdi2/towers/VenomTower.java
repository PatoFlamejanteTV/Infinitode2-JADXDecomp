package com.prineside.tdi2.towers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.buffs.PoisonBuff;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.GeneralizedTowerStatType;
import com.prineside.tdi2.enums.ShapeType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.enums.TowerStatType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.projectiles.VenomProjectile;
import com.prineside.tdi2.shapes.Circle;
import com.prineside.tdi2.systems.MapRenderingSystem;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.Quad;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/towers/VenomTower.class */
public final class VenomTower extends Tower {
    public static final int ABILITY_FAST_SHELLS = 2;
    public static final String[] ABILITY_ALIASES = {"CONCENTRATED_POISON", "HARD_SHELLS", "FAST_SHELLS"};

    @NAGS
    private Circle e;

    @NAGS
    private ParticleEffectPool.PooledEffect f;
    private float g;
    private boolean h;
    private float i;

    /* synthetic */ VenomTower(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Building, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.g);
        output.writeBoolean(this.h);
        output.writeFloat(this.i);
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Building, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.g = input.readFloat();
        this.h = input.readBoolean();
        this.i = input.readFloat();
    }

    private VenomTower() {
        super(TowerType.VENOM);
        this.h = false;
    }

    @Override // com.prineside.tdi2.Tower
    public final Quad getWeaponTextures() {
        if (isAbilityInstalled(0)) {
            return Game.i.towerManager.F.VENOM.getAbilityTextures(0);
        }
        return Game.i.towerManager.F.VENOM.getWeaponTexture();
    }

    @Override // com.prineside.tdi2.Tower
    public final boolean canAim() {
        return true;
    }

    @Override // com.prineside.tdi2.Tower
    public final boolean shouldSearchForTarget() {
        return true;
    }

    @Override // com.prineside.tdi2.Tower
    public final void attack(int i) {
        if (getTarget() == null) {
            return;
        }
        Vector2 vector2 = new Vector2();
        PMath.getPointByAngleFromPoint(getTile().center.x, getTile().center.y, this.angle, 12.0f, vector2);
        VenomProjectile obtain = this.S.projectile.F.VENOM.obtain();
        this.S.projectile.register(obtain);
        PoisonBuff poisonBuff = new PoisonBuff();
        poisonBuff.setup(this, getStat(TowerStatType.U_POISON_DURATION), getStat(TowerStatType.U_POISON_DURATION) * 10.0f, this.i * i, getStat(TowerStatType.DAMAGE), null);
        obtain.setup(this, getTarget(), poisonBuff, vector2, getStat(TowerStatType.PROJECTILE_SPEED));
        setTarget(null);
        this.shotCount += i;
        if (this.S._sound != null) {
            this.S._sound.playShotSound(StaticSoundType.SHOT_VENOM, this);
        }
    }

    public final float getUltimateChance() {
        float upgradeLevel = getUpgradeLevel() / 10.0f;
        float percentValueAsMultiplier = (float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_VENOM_A_CHAIN_CHANCE_MIN);
        return percentValueAsMultiplier + ((((float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_VENOM_A_CHAIN_CHANCE_MAX)) - percentValueAsMultiplier) * upgradeLevel);
    }

    public final float getPoisonousCloudRange() {
        return this.S.gameValue.getFloatValue(GameValueType.TOWER_VENOM_A_CLOUD_RANGE);
    }

    @Override // com.prineside.tdi2.Tower
    public final float calculateStat(TowerStatType towerStatType) {
        float calculateStat = super.calculateStat(towerStatType);
        if (towerStatType == TowerStatType.DAMAGE && isAbilityInstalled(0)) {
            calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_VENOM_A_CONCENTRATE_DAMAGE));
        }
        if (towerStatType == TowerStatType.PROJECTILE_SPEED && isAbilityInstalled(2)) {
            calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_VENOM_A_FAST_SPEED));
        }
        return calculateStat;
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Building
    public final void updateCache() {
        super.updateCache();
        this.i = getStat(TowerStatType.DAMAGE);
        if (isAbilityInstalled(1)) {
            this.i *= (float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_VENOM_A_HARD_DAMAGE);
        }
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Building
    public final void placedOnMap() {
        super.placedOnMap();
        this.h = true;
    }

    private void a() {
        if (this.h && isRegistered() && this.S._particle != null && isAbilityInstalled(3) && !isOutOfOrder() && Game.i.settingsManager.isParticlesDrawing()) {
            if (this.f == null && !this.S._particle.willParticleBeSkipped()) {
                this.f = Game.i.towerManager.F.VENOM.c.obtain();
                this.S._particle.addParticle(this.f, true);
                this.f.setPosition(getTile().center.x, getTile().center.y);
                return;
            }
            return;
        }
        if (this.f != null) {
            this.f.allowCompletion();
            this.f = null;
        }
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Building
    public final void removedFromMap() {
        super.removedFromMap();
        if (this.f != null) {
            this.f.allowCompletion();
            this.f = null;
        }
        if (this.e != null) {
            this.e.free();
            this.e = null;
        }
        this.h = false;
    }

    @Override // com.prineside.tdi2.Tower
    public final void update(float f) {
        if (isOutOfOrder()) {
            super.update(f);
            return;
        }
        a(f, getStat(TowerStatType.ROTATION_SPEED));
        float poisonousCloudRange = getPoisonousCloudRange() * 128.0f;
        this.g += f;
        if (this.g > 0.33f) {
            if (isAbilityInstalled(3)) {
                float stat = this.g * getStat(TowerStatType.DAMAGE) * getStat(TowerStatType.ATTACK_SPEED) * ((float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_VENOM_A_CLOUD_DAMAGE_COEFF));
                this.S.map.getEnemiesInCircleV(getTile().center, poisonousCloudRange, (enemyReference, f2, f3, f4) -> {
                    Enemy enemy = enemyReference.enemy;
                    if (enemy == null || !enemy.isVulnerableTo(DamageType.POISON)) {
                        return true;
                    }
                    this.S.damage.queueDamage(this.S.damage.obtainRecord().setup(enemy, stat, DamageType.POISON).setTower(this));
                    return true;
                });
            }
            this.g = 0.0f;
        }
        super.update(f);
    }

    @Override // com.prineside.tdi2.Tower
    public final void drawGlitch(Batch batch) {
        super.drawGlitch(batch);
        a();
    }

    @Override // com.prineside.tdi2.Tower
    public final void drawBatch(Batch batch, float f) {
        super.drawBatch(batch, f);
        a();
        if (this.S._mapRendering.getDrawMode() == MapRenderingSystem.DrawMode.DETAILED) {
            if (isAbilityInstalled(3) && this.e == null && Game.i.shapeManager != null) {
                float poisonousCloudRange = getPoisonousCloudRange() * 128.0f;
                this.e = (Circle) Game.i.shapeManager.getFactory(ShapeType.CIRCLE).obtain();
                Color cpy = MaterialColor.LIGHT_GREEN.P500.cpy();
                cpy.f889a = 0.0f;
                Color cpy2 = MaterialColor.LIGHT_GREEN.P500.cpy();
                cpy2.f889a = 0.07f;
                this.e.setup(getTile().center.x, getTile().center.y, poisonousCloudRange * 0.67f, poisonousCloudRange, 32, cpy.toFloatBits(), cpy2.toFloatBits());
            }
            if (this.e != null) {
                this.e.draw(batch);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.prineside.tdi2.Tower
    public final int getEnemyPriority(Enemy enemy) {
        if (enemy.hasBuffsByType(BuffType.POISON)) {
            DelayedRemovalArray delayedRemovalArray = enemy.buffsByType[BuffType.POISON.ordinal()];
            boolean z = false;
            int intValue = isAbilityInstalled(2) ? this.S.gameValue.getIntValue(GameValueType.TOWER_VENOM_A_FAST_MAX_DEBUFFS) : 1;
            int i = 0;
            while (true) {
                if (i >= delayedRemovalArray.size) {
                    break;
                }
                PoisonBuff poisonBuff = (PoisonBuff) delayedRemovalArray.get(i);
                if (poisonBuff.tower != this) {
                    i++;
                } else if (intValue <= 1 || poisonBuff.fastShellsStackCount >= intValue) {
                    z = true;
                }
            }
            if (z) {
                return enemy.lowAimPriority.isTrue() ? -5 : 5;
            }
            return super.getEnemyPriority(enemy);
        }
        return super.getEnemyPriority(enemy);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/towers/VenomTower$VenomTowerFactory.class */
    public static class VenomTowerFactory extends Tower.Factory<VenomTower> {
        ParticleEffectPool c;

        public VenomTowerFactory() {
            super("tower-venom", TowerType.VENOM);
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public void setup() {
            super.setup();
            this.f1784b[0].descriptionArgs = new String[]{""};
            this.f1784b[1].descriptionArgs = new String[]{""};
            this.f1784b[2].descriptionArgs = new String[]{"", "", ""};
            this.f1784b[3].descriptionArgs = new String[]{"", ""};
            this.f1784b[4].descriptionArgs = new String[]{"", "", "", ""};
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public Tower.AbilityConfig[] getAbilityConfigs(GameSystemProvider gameSystemProvider, Tower tower) {
            Tower.AbilityConfig[] abilityConfigs = super.getAbilityConfigs(gameSystemProvider, tower);
            abilityConfigs[0].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_VENOM_A_CONCENTRATE_DAMAGE), 2, true).toString();
            abilityConfigs[1].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_VENOM_A_HARD_DAMAGE), 2, true).toString();
            abilityConfigs[2].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_VENOM_A_FAST_SPEED), 2, true).toString();
            abilityConfigs[2].descriptionArgs[1] = StringFormatter.compactNumber(gameSystemProvider.gameValue.getIntValue(GameValueType.TOWER_VENOM_A_FAST_MAX_DEBUFFS), false).toString();
            abilityConfigs[2].descriptionArgs[2] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_VENOM_A_FAST_DAMAGE_PER_STACK), 2, true).toString();
            abilityConfigs[3].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_VENOM_A_CLOUD_RANGE), 2, true).toString();
            abilityConfigs[3].descriptionArgs[1] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_VENOM_A_CLOUD_DAMAGE_COEFF), 2, true).toString();
            abilityConfigs[4].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_VENOM_A_CHAIN_DURATION), 2, true).toString();
            abilityConfigs[4].descriptionArgs[1] = StringFormatter.compactNumberWithPrecisionTrimZeros(((VenomTower) tower).getUltimateChance() * 100.0f, 2, true).toString();
            abilityConfigs[4].descriptionArgs[2] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_VENOM_A_CHAIN_RANGE), 2, true).toString();
            abilityConfigs[4].descriptionArgs[3] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_VENOM_A_CHAIN_PROLONG), 2, true).toString();
            return abilityConfigs;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public boolean shouldDrawAbilityToCache(int i) {
            if (i == 0) {
                return false;
            }
            return true;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public void setupAssets() {
            this.c = Game.i.assetManager.getParticleEffectPool("poison-cloud.prt");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Tower.Factory
        public VenomTower create() {
            return new VenomTower((byte) 0);
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public Color getColor() {
            return MaterialColor.LIGHT_GREEN.P500;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public int getGeneralizedStat(GeneralizedTowerStatType generalizedTowerStatType) {
            switch (generalizedTowerStatType) {
                case RANGE:
                    return 3;
                case ATTACK_SPEED:
                    return 2;
                case DAMAGE:
                    return 3;
                case CROWD_DAMAGE:
                    return 4;
                case AGILITY:
                    return 2;
                default:
                    return 0;
            }
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public String[] getAbilityAliases() {
            return VenomTower.ABILITY_ALIASES;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public int getBuildHotKey() {
            return 29;
        }
    }
}
