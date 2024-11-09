package com.prineside.tdi2.towers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
import com.prineside.tdi2.ResourcePack;
import com.prineside.tdi2.SerializableListener;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.enums.AchievementType;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.GeneralizedTowerStatType;
import com.prineside.tdi2.enums.SpecialDamageType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.enums.TowerStatType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.EnemyDie;
import com.prineside.tdi2.explosions.GenericExplosion;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.shapes.BulletSmokeMultiLine;
import com.prineside.tdi2.utils.FastRandom;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.Quad;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/towers/SniperTower.class */
public final class SniperTower extends Tower {
    public static final float MIN_AIM_SPEED_MULTIPLIER_IN_CROWD = 0.15f;
    public static final float AIM_SPEED_MULTIPLIER_PER_ENEMY_IN_CROWD = 0.04f;
    private Enemy.EnemyReference f;
    private float g;
    private int h;
    public static final String[] ABILITY_ALIASES = {"PENETRATING_BULLETS", "HEAVY_WEAPONS", "SHORT_BARREL"};
    private static final Color e = Color.WHITE.cpy();
    private static final Color i = new Color();

    static /* synthetic */ int a(SniperTower sniperTower) {
        int i2 = sniperTower.h;
        sniperTower.h = i2 + 1;
        return i2;
    }

    /* synthetic */ SniperTower(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Building, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObject(output, this.f);
        output.writeFloat(this.g);
        output.writeVarInt(this.h, true);
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Building, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f = (Enemy.EnemyReference) kryo.readObject(input, Enemy.EnemyReference.class);
        this.g = input.readFloat();
        this.h = input.readVarInt(true);
    }

    private SniperTower() {
        super(TowerType.SNIPER);
        this.f = Enemy.EnemyReference.NULL;
        this.g = 0.0f;
        this.h = 0;
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Registrable
    public final void setUnregistered() {
        super.setUnregistered();
        this.f = Enemy.EnemyReference.NULL;
    }

    @Override // com.prineside.tdi2.Tower
    public final Quad getWeaponTextures() {
        if (isAbilityInstalled(0)) {
            if (isAbilityInstalled(2)) {
                return Game.i.towerManager.F.SNIPER.c;
            }
            return Game.i.towerManager.F.SNIPER.getAbilityTextures(0);
        }
        if (isAbilityInstalled(2)) {
            return Game.i.towerManager.F.SNIPER.getAbilityTextures(2);
        }
        return Game.i.towerManager.F.SNIPER.getWeaponTexture();
    }

    private boolean a() {
        Enemy target = getTarget();
        if (target == null) {
            return false;
        }
        return StrictMath.abs(PMath.getDistanceBetweenAngles(this.angle, PMath.getAngleBetweenPoints(getTile().center, target.getPosition()))) < 3.0f && this.f.enemy == target;
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
    public final boolean canAttack() {
        return a() && !this.attackDisabled && this.g >= 1.0f;
    }

    @Override // com.prineside.tdi2.Tower
    public final void attack(int i2) {
        ResourcePack.AtlasTextureRegion atlasTextureRegion;
        Enemy target = getTarget();
        Enemy enemy = target;
        if (target == null) {
            return;
        }
        Vector2 vector2 = new Vector2();
        vector2.set(enemy.getPosition());
        if (enemy.getColor() != null) {
            i.set(enemy.getColor());
        }
        if (this.S._particle != null && Game.i.settingsManager.isParticlesDrawing()) {
            Vector2 vector22 = new Vector2();
            vector22.set(getTile().center);
            if (!isAbilityInstalled(2)) {
                if (FastRandom.getFloat() < 0.5f) {
                    atlasTextureRegion = AssetManager.TextureRegions.i().muzzleFlashCompensator1;
                } else {
                    atlasTextureRegion = AssetManager.TextureRegions.i().muzzleFlashCompensator2;
                }
                PMath.shiftPointByAngle(vector22, this.angle, 43.0f);
                this.S._particle.addFlashParticle(atlasTextureRegion, vector22.x, vector22.y, 28.35f, 13.5f, 56.7f, 56.7f, this.angle);
            } else {
                ResourcePack.AtlasTextureRegion atlasTextureRegion2 = AssetManager.TextureRegions.i().muzzleFlash1;
                PMath.shiftPointByAngle(vector22, this.angle, 28.0f);
                this.S._particle.addFlashParticle(atlasTextureRegion2, vector22.x, vector22.y, 21.6f, 2.7f, 43.2f, 43.2f, this.angle);
            }
        }
        boolean z = false;
        boolean z2 = false;
        float stat = getStat(TowerStatType.DAMAGE) * i2;
        float percentValueAsMultiplier = (float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_SNIPER_A_KILLSHOT_HP);
        int intValue = this.S.gameValue.getIntValue(GameValueType.TOWER_SNIPER_A_KILLSHOT_INTERVAL);
        float buffedDamageMultiplier = enemy.getBuffedDamageMultiplier(TowerType.SNIPER, DamageType.BULLET);
        if (isAbilityInstalled(3) && buffedDamageMultiplier != 0.0f && enemy.isVulnerableToSpecial(SpecialDamageType.KILLSHOT) && enemy.getHealth() < enemy.maxHealth * percentValueAsMultiplier && this.h >= intValue) {
            vector2.set(enemy.getPosition());
            z = true;
            this.S.damage.queueDamage(this.S.damage.obtainRecord().setup(enemy, enemy.getHealth() + 0.001f, DamageType.BULLET).setTower(this).setCleanForDps(false).setLethal(true).setEfficiency(8).setIgnoreTowerEfficiency(true));
            enemy = null;
            if (this.S._particle != null && Game.i.settingsManager.isParticlesDrawing()) {
                ParticleEffectPool.PooledEffect obtain = Game.i.towerManager.F.SNIPER.g.obtain();
                obtain.setPosition(vector2.x, vector2.y);
                this.S._particle.addParticle(obtain, true);
            }
            this.h = 0;
        } else {
            if (isAbilityInstalled(4)) {
                float distanceBetweenPoints = (PMath.getDistanceBetweenPoints(getTile().center, enemy.getPosition()) / this.rangeInPixels) * (((float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_SNIPER_A_ULTIMATE_DAMAGE)) - 1.0f);
                if (distanceBetweenPoints > 0.0f) {
                    stat *= distanceBetweenPoints + 1.0f;
                }
            }
            if (this.S.gameState.randomFloat() < getStat(TowerStatType.U_CRIT_CHANCE) * 0.01f) {
                z2 = true;
                stat *= getStat(TowerStatType.U_CRIT_MULTIPLIER) * 0.01f;
                this.S.damage.queueDamage(this.S.damage.obtainRecord().setup(enemy, stat, DamageType.BULLET).setTower(this).setEfficiency(1));
                if (this.S._particle != null && Game.i.settingsManager.isParticlesDrawing()) {
                    ParticleEffectPool.PooledEffect obtain2 = Game.i.towerManager.F.SNIPER.f.obtain();
                    obtain2.setPosition(vector2.x, vector2.y);
                    ParticleEmitter.ScaledNumericValue angle = obtain2.getEmitters().first().getAngle();
                    float f = this.angle - 270.0f;
                    angle.setHigh(f - 15.0f, f + 15.0f);
                    ParticleEmitter.GradientColorValue tint = obtain2.getEmitters().first().getTint();
                    float[] colors = tint.getColors();
                    colors[0] = i.r;
                    colors[1] = i.g;
                    colors[2] = i.f888b;
                    tint.setColors(colors);
                    this.S._particle.addParticle(obtain2, true);
                }
            } else {
                this.S.damage.queueDamage(this.S.damage.obtainRecord().setup(enemy, stat, DamageType.BULLET).setTower(this));
            }
        }
        if (isAbilityInstalled(0)) {
            Vector2 vector23 = new Vector2();
            PMath.getPointByAngleFromPoint(getTile().center.x, getTile().center.y, this.angle, this.rangeInPixels, vector23);
            Enemy enemy2 = enemy;
            float f2 = stat;
            this.S.map.rayCastEnemiesSorted(vector2.x, vector2.y, vector23.x, vector23.y, 0.0f, enemyReference -> {
                Enemy enemy3 = enemyReference.enemy;
                if (enemy3 != enemy2) {
                    float percentValueAsMultiplier2 = f2 * ((float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_SNIPER_A_PENETRATION_DAMAGE));
                    if (isAbilityInstalled(4)) {
                        float distanceBetweenPoints2 = (1.0f - (PMath.getDistanceBetweenPoints(getTile().center, enemy3.getPosition()) / this.rangeInPixels)) * 0.75f;
                        if (distanceBetweenPoints2 > 0.0f) {
                            percentValueAsMultiplier2 *= distanceBetweenPoints2 + 1.0f;
                        }
                    }
                    Vector2 vector24 = new Vector2(enemy3.getPosition());
                    this.S.damage.queueDamage(this.S.damage.obtainRecord().setup(enemy3, percentValueAsMultiplier2, DamageType.BULLET).setTower(this));
                    if (this.S._projectileTrail != null && this.S._projectileTrail.isEnabled()) {
                        BulletSmokeMultiLine obtain3 = Game.i.shapeManager.F.BULLET_SMOKE_MULTI_LINE.obtain();
                        obtain3.maxAlpha = 1.0f;
                        obtain3.setTexture(Game.i.towerManager.F.SNIPER.e, false, FastRandom.getFloat() < 0.5f);
                        obtain3.setColor(MaterialColor.RED.P500);
                        obtain3.setup(vector2.x, vector2.y, vector24.x, vector24.y);
                        this.S._projectileTrail.addTrail(obtain3);
                        return false;
                    }
                    return false;
                }
                return true;
            });
        }
        if (this.S._projectileTrail != null && this.S._projectileTrail.isEnabled()) {
            BulletSmokeMultiLine obtain3 = Game.i.shapeManager.F.BULLET_SMOKE_MULTI_LINE.obtain();
            Vector2 vector24 = new Vector2();
            PMath.getPointByAngleFromPoint(getTile().center.x, getTile().center.y, this.angle, 30.0f, vector24);
            obtain3.setTexture(Game.i.towerManager.F.SNIPER.e, false, FastRandom.getFloat() < 0.5f);
            if (z) {
                obtain3.maxAlpha = 0.56f;
                obtain3.setColor(MaterialColor.PURPLE.P500);
            } else if (z2) {
                obtain3.maxAlpha = 0.28f;
                obtain3.setColor(MaterialColor.DEEP_ORANGE.P500);
            } else {
                obtain3.setColor(Color.WHITE);
            }
            obtain3.setup(vector24.x, vector24.y, vector2.x, vector2.y);
            this.S._projectileTrail.addTrail(obtain3);
        }
        this.shotCount += i2;
        if (this.S._sound != null) {
            this.S._sound.playShotSound(StaticSoundType.SHOT_SNIPER, this);
        }
    }

    @Override // com.prineside.tdi2.Tower
    public final float calculateStat(TowerStatType towerStatType) {
        float calculateStat = super.calculateStat(towerStatType);
        if (towerStatType == TowerStatType.RANGE && isAbilityInstalled(2)) {
            calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_SNIPER_A_SHORT_RANGE));
        }
        if (towerStatType == TowerStatType.ROTATION_SPEED && isAbilityInstalled(2)) {
            calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_SNIPER_A_SHORT_ROTATION_SPEED));
        }
        if (towerStatType == TowerStatType.U_CRIT_CHANCE && isAbilityInstalled(2)) {
            calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_SNIPER_A_SHORT_CRIT_MULTIPLIER));
        }
        if (towerStatType == TowerStatType.DAMAGE && isAbilityInstalled(1)) {
            calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_SNIPER_A_HEAVY_DAMAGE));
        }
        return calculateStat;
    }

    @Override // com.prineside.tdi2.Tower
    public final void update(float f) {
        Enemy target = getTarget();
        if (this.f.enemy != target) {
            this.f = this.S.enemy.getReference(target);
            this.g = 0.0f;
        }
        a(f, getStat(TowerStatType.ROTATION_SPEED));
        if (target != null) {
            if (a()) {
                float f2 = 1.0f - (target.otherEnemiesNearby * 0.04f);
                float f3 = f2;
                if (f2 < 0.15f) {
                    f3 = 0.15f;
                }
                this.g += f * getStat(TowerStatType.AIM_SPEED) * 0.01f * f3;
                if (this.g > 1.0f) {
                    this.g = 1.0f;
                }
            } else {
                this.g = 0.0f;
            }
        } else {
            this.g = 0.0f;
        }
        super.update(f);
    }

    @Override // com.prineside.tdi2.Tower
    public final void drawBatch(Batch batch, float f) {
        if (getTile().visibleOnScreen && getTarget() != null && a()) {
            float f2 = (120.0f * (1.0f - this.g)) + 4.0f;
            e.f889a = this.g;
            batch.setColor(e);
            batch.draw(Game.i.towerManager.F.SNIPER.d, getTile().center.x - (f2 / 2.0f), getTile().center.y, f2 / 2.0f, 0.0f, f2, 128.0f, 1.0f, 1.0f, this.angle);
            batch.setColor(Color.WHITE);
        }
        super.drawBatch(batch, f);
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/towers/SniperTower$OnEnemyDie.class */
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
            if (tower instanceof SniperTower) {
                SniperTower sniperTower = (SniperTower) tower;
                SniperTower.a(sniperTower);
                if (DamageType.Efficiency.isCritical(lastDamage.getEfficiency()) && tower.isAbilityInstalled(4)) {
                    Enemy enemy = lastDamage.getEnemy();
                    Vector2 position = enemy.getPosition();
                    float dst2 = sniperTower.getTile().center.dst2(position);
                    float floatValue = ((GameSystemProvider) this.f1759a).gameValue.getFloatValue(GameValueType.TOWER_SNIPER_A_ULTIMATE_EXPL_RANGE);
                    if (dst2 < floatValue * 128.0f * floatValue * 128.0f) {
                        GenericExplosion obtain = ((GameSystemProvider) this.f1759a).explosion.F.GENERIC.obtain();
                        obtain.setup(sniperTower, position.x, position.y, lastDamage.getEnemy().maxHealth * ((float) ((GameSystemProvider) this.f1759a).gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_SNIPER_A_ULTIMATE_EXPL_DAMAGE)), 1.2f, 0, 0.0f, 0.0f, enemy.getColor(), enemy.getColor());
                        ((GameSystemProvider) this.f1759a).explosion.register(obtain);
                        obtain.explode();
                        ((GameSystemProvider) this.f1759a).achievement.registerDelta(AchievementType.EXPLODE_ENEMY_WITH_BULLET, 1);
                    }
                }
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/towers/SniperTower$SniperTowerFactory.class */
    public static class SniperTowerFactory extends Tower.Factory<SniperTower> {
        Quad c;
        TextureRegion d;
        TextureRegion e;
        ParticleEffectPool f;
        ParticleEffectPool g;

        public SniperTowerFactory() {
            super("tower-sniper", TowerType.SNIPER);
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public void setup() {
            super.setup();
            this.f1784b[0].descriptionArgs = new String[]{""};
            this.f1784b[1].descriptionArgs = new String[]{""};
            this.f1784b[2].descriptionArgs = new String[]{"", "", ""};
            this.f1784b[3].descriptionArgs = new String[]{"", ""};
            this.f1784b[4].descriptionArgs = new String[]{"", "", ""};
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public void configureSystems(GameSystemProvider gameSystemProvider) {
            gameSystemProvider.events.getListeners(EnemyDie.class).addStateAffecting(new OnEnemyDie(gameSystemProvider));
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public void setupAssets() {
            this.c = Game.i.assetManager.getQuad("towers." + TowerType.SNIPER.name() + ".weaponShortPenetrating");
            this.e = Game.i.assetManager.getTextureRegion("bullet-trace-smoke");
            this.d = Game.i.assetManager.getTextureRegion("tower-aim");
            this.f = Game.i.assetManager.getParticleEffectPool("crit-hit.prt");
            this.g = Game.i.assetManager.getParticleEffectPool("killshot.prt");
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public boolean shouldDrawAbilityToCache(int i) {
            if (i == 0 || i == 2) {
                return false;
            }
            return true;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        @Null
        public CharSequence getStatMoreInfo(TowerStatType towerStatType, GameValueProvider gameValueProvider, Tower tower) {
            if (towerStatType == TowerStatType.AIM_SPEED) {
                return Game.i.localeManager.i18n.format("tower_stat_more_info_SNIPER_AIM_SPEED", StringFormatter.compactNumberWithPrecisionTrimZeros(4.0d, 1, true).toString(), StringFormatter.compactNumberWithPrecisionTrimZeros(15.000000953674316d, 1, true).toString());
            }
            return null;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public Tower.AbilityConfig[] getAbilityConfigs(GameSystemProvider gameSystemProvider, Tower tower) {
            Tower.AbilityConfig[] abilityConfigs = super.getAbilityConfigs(gameSystemProvider, tower);
            abilityConfigs[0].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_SNIPER_A_PENETRATION_DAMAGE), 2, true).toString();
            abilityConfigs[1].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_SNIPER_A_HEAVY_DAMAGE), 2, true).toString();
            abilityConfigs[2].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_SNIPER_A_SHORT_RANGE), 2, true).toString();
            abilityConfigs[2].descriptionArgs[1] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_SNIPER_A_SHORT_ROTATION_SPEED), 2, true).toString();
            abilityConfigs[2].descriptionArgs[2] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_SNIPER_A_SHORT_CRIT_MULTIPLIER), 2, true).toString();
            double floatValue = gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_SNIPER_A_KILLSHOT_HP);
            int intValue = gameSystemProvider.gameValue.getIntValue(GameValueType.TOWER_SNIPER_A_KILLSHOT_INTERVAL);
            abilityConfigs[3].descriptionArgs[0] = StringFormatter.compactNumber(floatValue, false).toString();
            abilityConfigs[3].descriptionArgs[1] = Integer.toString(intValue);
            abilityConfigs[4].descriptionArgs[0] = StringFormatter.compactNumber(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_SNIPER_A_ULTIMATE_DAMAGE), false).toString();
            abilityConfigs[4].descriptionArgs[1] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_SNIPER_A_ULTIMATE_EXPL_DAMAGE), 2, true).toString();
            abilityConfigs[4].descriptionArgs[2] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_SNIPER_A_ULTIMATE_EXPL_RANGE), 2, true).toString();
            return abilityConfigs;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public Color getColor() {
            return MaterialColor.GREEN.P500;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public int getGeneralizedStat(GeneralizedTowerStatType generalizedTowerStatType) {
            switch (generalizedTowerStatType) {
                case RANGE:
                case DAMAGE:
                    return 5;
                case ATTACK_SPEED:
                case CROWD_DAMAGE:
                case AGILITY:
                    return 1;
                default:
                    return 0;
            }
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public String[] getAbilityAliases() {
            return SniperTower.ABILITY_ALIASES;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public int getBuildHotKey() {
            return 51;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Tower.Factory
        public SniperTower create() {
            return new SniperTower((byte) 0);
        }
    }
}
