package com.prineside.tdi2.towers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.DamageRecord;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.GameValueProvider;
import com.prineside.tdi2.Projectile;
import com.prineside.tdi2.SerializableListener;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.buffs.BurnBuff;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.GeneralizedTowerStatType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.enums.TowerStatType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.EnemyDie;
import com.prineside.tdi2.events.game.EnemyTakeDamage;
import com.prineside.tdi2.explosions.AirFallExplosion;
import com.prineside.tdi2.projectiles.AirProjectile;
import com.prineside.tdi2.units.MineUnit;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.Quad;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/towers/AirTower.class */
public final class AirTower extends Tower {
    public static final String[] ABILITY_ALIASES = {"HEAVY_WEAPONS", "FAST_MECHANISM", "FOUNDATION"};
    public static final float MAX_BURN_DAMAGE_ENEMY_HP_PERCENT = 3.0f;
    private float e;
    private Vector2 f;
    public boolean currentTargetIgnited;

    /* synthetic */ AirTower(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Building, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.e);
        kryo.writeObject(output, this.f);
        output.writeBoolean(this.currentTargetIgnited);
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Building, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.e = input.readFloat();
        this.f = (Vector2) kryo.readObject(input, Vector2.class);
        this.currentTargetIgnited = input.readBoolean();
    }

    private AirTower() {
        super(TowerType.AIR);
        this.e = 90.0f;
        this.f = new Vector2();
    }

    @Override // com.prineside.tdi2.Tower
    public final Quad getWeaponTextures() {
        if (isAbilityInstalled(0)) {
            return Game.i.towerManager.F.AIR.getAbilityTextures(0);
        }
        return Game.i.towerManager.F.AIR.getWeaponTexture();
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
    public final int getEnemyPriority(Enemy enemy) {
        if (enemy.hasBuffsByType(BuffType.BURN)) {
            return enemy.lowAimPriority.isTrue() ? -5 : 5;
        }
        return super.getEnemyPriority(enemy);
    }

    @Override // com.prineside.tdi2.Tower
    public final void attack(int i) {
        if (getTarget() == null) {
            return;
        }
        this.e = -this.e;
        this.f.set(getTile().center);
        PMath.shiftPointByAngle(this.f, this.angle, 24.0f);
        PMath.shiftPointByAngle(this.f, this.angle + this.e, 5.0f);
        AirProjectile obtain = this.S.projectile.F.AIR.obtain();
        this.S.projectile.register(obtain);
        obtain.setup(this, getTarget(), getStat(TowerStatType.DAMAGE) * i, this.f, getStat(TowerStatType.PROJECTILE_SPEED), getStat(TowerStatType.U_BURN_CHANCE) * 0.01f, getStat(TowerStatType.U_BURN_DAMAGE) * 0.01f, getStat(TowerStatType.U_BURNING_TIME));
        this.shotCount += i;
        if (this.S._sound != null) {
            this.S._sound.playShotSound(StaticSoundType.SHOT_AIR, this);
        }
    }

    @Override // com.prineside.tdi2.Tower
    public final float calculateStat(TowerStatType towerStatType) {
        float calculateStat = super.calculateStat(towerStatType);
        switch (towerStatType) {
            case DAMAGE:
                if (isAbilityInstalled(0)) {
                    calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_AIR_A_HEAVY_WEAPONS_DAMAGE));
                    break;
                }
                break;
            case ATTACK_SPEED:
                if (isAbilityInstalled(1)) {
                    calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_AIR_A_FAST_MECHANISM_SPEED));
                    break;
                }
                break;
            case U_BURN_DAMAGE:
                if (isAbilityInstalled(2)) {
                    calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_AIR_A_FOUNDATION_BURN_DAMAGE));
                    break;
                }
                break;
            case U_BURN_CHANCE:
                if (isAbilityInstalled(1)) {
                    calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_AIR_A_FAST_MECHANISM_IGNITION_CHANCE));
                    break;
                }
                break;
            case PROJECTILE_SPEED:
            case ROTATION_SPEED:
                if (isAbilityInstalled(2)) {
                    calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_AIR_A_FOUNDATION_SPEED));
                    break;
                }
                break;
        }
        return calculateStat;
    }

    public final AirFallExplosion createSpecialAbilityExplosion(float f, float f2, float f3) {
        double percentValueAsMultiplier = this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_AIR_A_AIMED_DROP_DAMAGE);
        if (isRegistered() && isAbilityInstalled(4)) {
            percentValueAsMultiplier += this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_AIR_A_ULTIMATE_DAMAGE);
        }
        AirFallExplosion obtain = this.S.explosion.F.AIR_FALL.obtain();
        obtain.setup(this, f, f2, f3 * ((float) percentValueAsMultiplier), this.S.gameValue.getFloatValue(GameValueType.TOWER_AIR_A_ULTIMATE_EXPL_RANGE));
        obtain.piercingMultiplier = 1.6f;
        return obtain;
    }

    @Override // com.prineside.tdi2.Tower
    public final void update(float f) {
        if (isOutOfOrder()) {
            super.update(f);
            return;
        }
        a(f, getStat(TowerStatType.ROTATION_SPEED));
        if (this.currentTargetIgnited) {
            this.currentTargetIgnited = false;
            Enemy findTarget = findTarget();
            if (getTarget() != findTarget) {
                setTarget(findTarget);
            }
        }
        super.update(f);
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/towers/AirTower$OnEnemyDie.class */
    public static final class OnEnemyDie extends SerializableListener<GameSystemProvider> implements Listener<EnemyDie> {
        private OnEnemyDie() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public OnEnemyDie(GameSystemProvider gameSystemProvider) {
            this.f1759a = gameSystemProvider;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(EnemyDie enemyDie) {
            DamageRecord lastDamage = enemyDie.getLastDamage();
            Tower tower = lastDamage.getTower();
            if ((tower instanceof AirTower) && tower.isAbilityInstalled(3) && lastDamage.getEnemy().isAir()) {
                AirTower airTower = (AirTower) tower;
                Enemy enemy = lastDamage.getEnemy();
                Vector2 position = enemy.getPosition();
                AirFallExplosion createSpecialAbilityExplosion = airTower.createSpecialAbilityExplosion(position.x, position.y, enemy.maxHealth);
                if (tower.isAbilityInstalled(4)) {
                    MineUnit create = Game.i.unitManager.F.MINE.create();
                    create.setup(tower, position.x, position.y, position.x, position.y, createSpecialAbilityExplosion, MaterialColor.CYAN.P500);
                    ((GameSystemProvider) this.f1759a).unit.register(create);
                    ((GameSystemProvider) this.f1759a).map.spawnUnit(create);
                    return;
                }
                ((GameSystemProvider) this.f1759a).explosion.register(createSpecialAbilityExplosion);
                createSpecialAbilityExplosion.explode();
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/towers/AirTower$OnEnemyTakeDamage.class */
    public static final class OnEnemyTakeDamage extends SerializableListener<GameSystemProvider> implements Listener<EnemyTakeDamage> {
        private OnEnemyTakeDamage() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public OnEnemyTakeDamage(GameSystemProvider gameSystemProvider) {
            this.f1759a = gameSystemProvider;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(EnemyTakeDamage enemyTakeDamage) {
            DamageRecord record = enemyTakeDamage.getRecord();
            Projectile projectile = record.getProjectile();
            if ((projectile instanceof AirProjectile) && record.getDamageType() == DamageType.BULLET) {
                AirProjectile airProjectile = (AirProjectile) projectile;
                if (((GameSystemProvider) this.f1759a).gameState.randomFloat() < airProjectile.getBurnChance()) {
                    Tower tower = record.getTower();
                    Enemy enemy = record.getEnemy();
                    BurnBuff burnBuff = new BurnBuff();
                    float damage = airProjectile.getDamage() * airProjectile.getBurnDamage();
                    float f = damage;
                    if (damage > enemy.maxHealth * 3.0f) {
                        f = enemy.maxHealth * 3.0f;
                    }
                    burnBuff.setup(tower, airProjectile.getBurningTime(), airProjectile.getBurningTime() * 10.0f, f, null);
                    ((GameSystemProvider) this.f1759a).buff.P_BURN.addBuff(enemy, burnBuff);
                    if ((tower instanceof AirTower) && tower.getTarget() == enemy) {
                        ((AirTower) tower).currentTargetIgnited = true;
                    }
                }
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/towers/AirTower$AirTowerFactory.class */
    public static final class AirTowerFactory extends Tower.Factory<AirTower> {
        public AirTowerFactory() {
            super("tower-air", TowerType.AIR);
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public final void setup() {
            super.setup();
            this.f1784b[0].descriptionArgs = new String[]{""};
            this.f1784b[1].descriptionArgs = new String[]{"", ""};
            this.f1784b[2].descriptionArgs = new String[]{"", ""};
            this.f1784b[3].descriptionArgs = new String[]{""};
            this.f1784b[4].descriptionArgs = new String[]{""};
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public final void configureSystems(GameSystemProvider gameSystemProvider) {
            gameSystemProvider.events.getListeners(EnemyDie.class).addStateAffecting(new OnEnemyDie(gameSystemProvider));
            gameSystemProvider.events.getListeners(EnemyTakeDamage.class).addStateAffecting(new OnEnemyTakeDamage(gameSystemProvider));
        }

        @Override // com.prineside.tdi2.Tower.Factory
        @Null
        public final CharSequence getStatMoreInfo(TowerStatType towerStatType, GameValueProvider gameValueProvider, Tower tower) {
            if (towerStatType == TowerStatType.U_BURN_DAMAGE) {
                return Game.i.localeManager.i18n.format("tower_stat_more_info_AIR_U_BURN_CHANCE", StringFormatter.compactNumberWithPrecisionTrimZeros(3.0d, 1, true).toString());
            }
            return null;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public final Tower.AbilityConfig[] getAbilityConfigs(GameSystemProvider gameSystemProvider, Tower tower) {
            Tower.AbilityConfig[] abilityConfigs = super.getAbilityConfigs(gameSystemProvider, tower);
            abilityConfigs[0].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_AIR_A_HEAVY_WEAPONS_DAMAGE), 2, true).toString();
            abilityConfigs[1].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_AIR_A_FAST_MECHANISM_SPEED), 2, true).toString();
            abilityConfigs[1].descriptionArgs[1] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_AIR_A_FAST_MECHANISM_IGNITION_CHANCE), 2, true).toString();
            abilityConfigs[2].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_AIR_A_FOUNDATION_SPEED), 2, true).toString();
            abilityConfigs[2].descriptionArgs[1] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_AIR_A_FOUNDATION_BURN_DAMAGE), 2, true).toString();
            abilityConfigs[3].descriptionArgs[0] = StringFormatter.compactNumber(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_AIR_A_AIMED_DROP_DAMAGE) * 100.0d, false).toString();
            abilityConfigs[4].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_AIR_A_ULTIMATE_DAMAGE), 2, true).toString();
            return abilityConfigs;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public final boolean shouldDrawAbilityToCache(int i) {
            if (i == 0) {
                return false;
            }
            return true;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public final int getGeneralizedStat(GeneralizedTowerStatType generalizedTowerStatType) {
            switch (generalizedTowerStatType) {
                case RANGE:
                    return 5;
                case ATTACK_SPEED:
                    return 5;
                case DAMAGE:
                    return 4;
                case CROWD_DAMAGE:
                    return 1;
                case AGILITY:
                    return 3;
                default:
                    return 0;
            }
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public final int getBuildHotKey() {
            return 52;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public final String[] getAbilityAliases() {
            return AirTower.ABILITY_ALIASES;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public final Color getColor() {
            return MaterialColor.CYAN.P500;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Tower.Factory
        public final AirTower create() {
            return new AirTower((byte) 0);
        }
    }
}
