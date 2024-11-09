package com.prineside.tdi2.towers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.GameValueProvider;
import com.prineside.tdi2.ResourcePack;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.abilities.BallLightningAbility;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.GeneralizedTowerStatType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.enums.TowerStatType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.projectiles.ChainLightningProjectile;
import com.prineside.tdi2.shapes.Circle;
import com.prineside.tdi2.systems.MapRenderingSystem;
import com.prineside.tdi2.units.BallLightningUnit;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.Quad;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/towers/TeslaTower.class */
public final class TeslaTower extends Tower {
    public static final float SPECIAL_ABILITY_RANGE = 3.5f;
    public static final String[] ABILITY_ALIASES = {"HIGH_CURRENT", "LARGE_BATTERIES", "INCREASED_VOLTAGE"};
    private boolean e;
    private float f;
    private byte g;

    @NAGS
    private Circle h;

    /* synthetic */ TeslaTower(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Building, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeBoolean(this.e);
        output.writeFloat(this.f);
        output.writeByte(this.g);
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Building, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.e = input.readBoolean();
        this.f = input.readFloat();
        this.g = input.readByte();
    }

    private TeslaTower() {
        super(TowerType.TESLA);
    }

    @Override // com.prineside.tdi2.Tower
    public final Quad getWeaponTextures() {
        if (isAbilityInstalled(0)) {
            return Game.i.towerManager.F.TESLA.getAbilityTextures(0);
        }
        return Game.i.towerManager.F.TESLA.getWeaponTexture();
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
        float percentValueAsMultiplier;
        BallLightningAbility ballLightningAbility;
        Enemy target = getTarget();
        if (target == null) {
            return;
        }
        float stat = getStat(TowerStatType.DAMAGE);
        float stat2 = getStat(TowerStatType.U_CHAIN_LIGHTNING_LENGTH);
        Vector2 vector2 = new Vector2();
        PMath.getPointByAngleFromPoint(getTile().center.x, getTile().center.y, this.angle, 36.0f, vector2);
        ChainLightningProjectile obtain = this.S.projectile.F.CHAIN_LIGHTNING.obtain();
        this.S.projectile.register(obtain);
        if (isAbilityInstalled(2)) {
            percentValueAsMultiplier = (float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_TESLA_A_VOLTAGE_MIN_DAMAGE);
        } else {
            percentValueAsMultiplier = (float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_TESLA_CHAIN_MIN_DAMAGE);
        }
        float f = target.getPosition().x;
        float f2 = target.getPosition().y;
        obtain.setup(this, target, stat * i, stat * percentValueAsMultiplier * i, getStat(TowerStatType.CHAIN_LIGHTNING_DAMAGE) * 0.01f, stat2, vector2);
        this.shotCount += i;
        if (isAbilityInstalled(4)) {
            if (this.shotCount % this.S.gameValue.getIntValue(GameValueType.TOWER_TESLA_A_ULTIMATE_SHOT_INTERVAL) == 0 && (ballLightningAbility = (BallLightningAbility) this.S.ability.registerConfigureAndStartAbility(AbilityType.BALL_LIGHTNING, (int) f, (int) f2, this.S.damage.getTowersMaxDps())) != null) {
                ballLightningAbility.damage = stat * ((float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_TESLA_A_ULTIMATE_DAMAGE));
                ballLightningAbility.attackInterval = getAttackDelay();
                ballLightningAbility.duration = this.S.gameValue.getFloatValue(GameValueType.TOWER_TESLA_A_ULTIMATE_DURATION);
                ballLightningAbility.launchedByTower = this;
            }
        }
        if (this.S._sound != null) {
            this.S._sound.playShotSound(StaticSoundType.SHOT_TESLA, this);
        }
    }

    @Override // com.prineside.tdi2.Tower
    public final float calculateStat(TowerStatType towerStatType) {
        float calculateStat = super.calculateStat(towerStatType);
        if (towerStatType == TowerStatType.U_CHAIN_LIGHTNING_LENGTH && isAbilityInstalled(2)) {
            calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_TESLA_A_VOLTAGE_LENGTH));
        }
        if (towerStatType == TowerStatType.DAMAGE && isAbilityInstalled(0)) {
            calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_TESLA_A_CURRENT_DAMAGE));
        }
        if (towerStatType == TowerStatType.DAMAGE && isAbilityInstalled(1)) {
            calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_TESLA_A_BATTERIES_DAMAGE));
        }
        if (towerStatType == TowerStatType.ATTACK_SPEED && isAbilityInstalled(1)) {
            calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_TESLA_A_BATTERIES_SPEED));
        }
        return calculateStat;
    }

    @Override // com.prineside.tdi2.Tower
    public final void update(float f) {
        if (isOutOfOrder()) {
            super.update(f);
            return;
        }
        a(f, getStat(TowerStatType.ROTATION_SPEED));
        if (isAbilityInstalled(3)) {
            this.g = (byte) (this.g + 1);
            if (this.g == 7) {
                this.g = (byte) 0;
                this.e = true;
                this.S.map.getEnemiesInCircle(getTile().center.x, getTile().center.y, 448.0f, (enemyReference, f2, f3, f4) -> {
                    if (enemyReference.enemy == null) {
                        return true;
                    }
                    this.e = false;
                    return false;
                });
            }
        } else {
            this.e = false;
        }
        if (this.e) {
            this.f += f;
            if (this.f >= this.S.unit.getBallLightningAccumulationTime()) {
                BallLightningUnit create = Game.i.unitManager.F.BALL_LIGHTNING.create();
                create.setup(this, getStat(TowerStatType.DAMAGE) * (1.0f / getAttackDelay()) * ((float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_TESLA_A_BALL_DAMAGE)));
                if (this.S.unit.preparePathToRandomSpawn(create, getTile())) {
                    this.S.unit.register(create);
                    this.S.map.spawnUnit(create);
                }
                this.f = 0.0f;
            }
        } else {
            if (this.S._particle != null && this.f > this.S.unit.getBallLightningAccumulationTime() * 0.25f && Game.i.settingsManager.isParticlesDrawing()) {
                ParticleEffectPool.PooledEffect breakParticle = Game.i.unitManager.F.BALL_LIGHTNING.getBreakParticle();
                breakParticle.setPosition(getTile().center.x, getTile().center.y);
                this.S._particle.addParticle(breakParticle, true);
            }
            this.f = 0.0f;
        }
        super.update(f);
    }

    @Override // com.prineside.tdi2.Tower
    public final void drawBatch(Batch batch, float f) {
        if (this.f > 0.0f) {
            float ballLightningAccumulationTime = this.f / this.S.unit.getBallLightningAccumulationTime();
            float regionWidth = r0.getRegionWidth() * ballLightningAccumulationTime;
            batch.draw(Game.i.towerManager.F.TESLA.c.getKeyFrame(this.f, true), getTile().center.x - (regionWidth * 0.5f), getTile().center.y - (regionWidth * 0.5f), regionWidth, regionWidth);
        }
        if (this.S._mapRendering.getDrawMode() == MapRenderingSystem.DrawMode.DETAILED && this.S._gameMapSelection.getSelectedTile() == getTile() && isAbilityInstalled(3)) {
            if (this.h == null && Game.i.shapeManager != null) {
                this.h = Game.i.shapeManager.F.CIRCLE.obtain();
                float floatBits = new Color(1.0f, 1.0f, 1.0f, 0.07f).toFloatBits();
                this.h.setup(getTile().center.x, getTile().center.y, 444.0f, 448.0f, 112, floatBits, floatBits);
                this.h.setSkipOddSegments(true);
            }
            if (this.h != null) {
                this.h.draw(batch);
            }
        } else if (this.h != null) {
            Game.i.shapeManager.F.CIRCLE.free(this.h);
            this.h = null;
        }
        super.drawBatch(batch, f);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/towers/TeslaTower$TeslaTowerFactory.class */
    public static class TeslaTowerFactory extends Tower.Factory<TeslaTower> {
        Animation<ResourcePack.AtlasTextureRegion> c;

        public TeslaTowerFactory() {
            super("tower-tesla", TowerType.TESLA);
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public void setup() {
            super.setup();
            this.f1784b[0].descriptionArgs = new String[]{""};
            this.f1784b[1].descriptionArgs = new String[]{"", ""};
            this.f1784b[2].descriptionArgs = new String[]{"", ""};
            this.f1784b[3].descriptionArgs = new String[]{"", "", ""};
            this.f1784b[4].descriptionArgs = new String[]{"", "", ""};
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public void setupAssets() {
            this.c = Game.i.unitManager.F.BALL_LIGHTNING.getBallAnimation();
        }

        @Override // com.prineside.tdi2.Tower.Factory
        @Null
        public CharSequence getStatMoreInfo(TowerStatType towerStatType, GameValueProvider gameValueProvider, Tower tower) {
            float percentValueAsMultiplier;
            if (towerStatType == TowerStatType.CHAIN_LIGHTNING_DAMAGE) {
                if (tower.isAbilityInstalled(2)) {
                    percentValueAsMultiplier = (float) gameValueProvider.getPercentValueAsMultiplier(GameValueType.TOWER_TESLA_A_VOLTAGE_MIN_DAMAGE);
                } else {
                    percentValueAsMultiplier = (float) gameValueProvider.getPercentValueAsMultiplier(GameValueType.TOWER_TESLA_CHAIN_MIN_DAMAGE);
                }
                return Game.i.localeManager.i18n.format("tower_stat_more_info_TESLA_CHAIN_LIGHTNING_DAMAGE", StringFormatter.compactNumberWithPrecisionTrimZeros(percentValueAsMultiplier * 100.0f, 1, true).toString());
            }
            return null;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public Tower.AbilityConfig[] getAbilityConfigs(GameSystemProvider gameSystemProvider, Tower tower) {
            Tower.AbilityConfig[] abilityConfigs = super.getAbilityConfigs(gameSystemProvider, tower);
            abilityConfigs[0].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_TESLA_A_CURRENT_DAMAGE), 2, true).toString();
            abilityConfigs[1].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_TESLA_A_BATTERIES_SPEED), 2, true).toString();
            abilityConfigs[1].descriptionArgs[1] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_TESLA_A_BATTERIES_DAMAGE), 2, true).toString();
            abilityConfigs[2].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_TESLA_A_VOLTAGE_LENGTH), 2, true).toString();
            abilityConfigs[2].descriptionArgs[1] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_TESLA_A_VOLTAGE_MIN_DAMAGE), 2, true).toString();
            abilityConfigs[3].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(3.5d, 1, true).toString();
            abilityConfigs[3].descriptionArgs[1] = StringFormatter.compactNumber(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_TESLA_A_BALL_DAMAGE), false).toString();
            abilityConfigs[3].descriptionArgs[2] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_TESLA_A_PER_BALL_PENALTY), 1, true).toString();
            abilityConfigs[4].descriptionArgs[0] = StringFormatter.compactNumber(gameSystemProvider.gameValue.getIntValue(GameValueType.TOWER_TESLA_A_ULTIMATE_SHOT_INTERVAL), false).toString();
            abilityConfigs[4].descriptionArgs[1] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_TESLA_A_ULTIMATE_DAMAGE), 2, true).toString();
            abilityConfigs[4].descriptionArgs[2] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_TESLA_A_ULTIMATE_DURATION), 2, true).toString();
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
        public Color getColor() {
            return MaterialColor.INDIGO.P500;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public int getGeneralizedStat(GeneralizedTowerStatType generalizedTowerStatType) {
            switch (generalizedTowerStatType) {
                case RANGE:
                    return 4;
                case ATTACK_SPEED:
                    return 3;
                case DAMAGE:
                    return 2;
                case CROWD_DAMAGE:
                    return 4;
                case AGILITY:
                    return 3;
                default:
                    return 0;
            }
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public String[] getAbilityAliases() {
            return TeslaTower.ABILITY_ALIASES;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public int getBuildHotKey() {
            return 31;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Tower.Factory
        public TeslaTower create() {
            return new TeslaTower((byte) 0);
        }
    }
}
