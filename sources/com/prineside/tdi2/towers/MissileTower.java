package com.prineside.tdi2.towers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.GameValueProvider;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.GeneralizedTowerStatType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.enums.TowerStatType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.projectiles.MissileProjectile;
import com.prineside.tdi2.utils.DrawUtils;
import com.prineside.tdi2.utils.FrameAccumulatorForPerformance;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.Quad;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.Arrays;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/towers/MissileTower.class */
public final class MissileTower extends Tower {
    private static final TLog e = TLog.forClass(MissileTower.class);
    public static final String[] ABILITY_ALIASES = {"VERTICAL_LAUNCH", "COMPACT_MISSILES", "ANTI_AIR_SYSTEM"};
    private float f;
    private Enemy.EnemyReference g;
    private Enemy.EnemyReference[] h;

    @FrameAccumulatorForPerformance
    private byte i;

    /* synthetic */ MissileTower(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Building, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.f);
        kryo.writeObject(output, this.g);
        kryo.writeObjectOrNull(output, this.h, Enemy.EnemyReference[].class);
        output.writeByte(this.i);
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Building, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f = input.readFloat();
        this.g = (Enemy.EnemyReference) kryo.readObject(input, Enemy.EnemyReference.class);
        this.h = (Enemy.EnemyReference[]) kryo.readObjectOrNull(input, Enemy.EnemyReference[].class);
        this.i = input.readByte();
    }

    private MissileTower() {
        super(TowerType.MISSILE);
        this.g = Enemy.EnemyReference.NULL;
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Registrable
    public final void setUnregistered() {
        this.g = Enemy.EnemyReference.NULL;
        super.setUnregistered();
    }

    @Override // com.prineside.tdi2.Tower
    public final Quad getWeaponTextures() {
        if (isAbilityInstalled(1)) {
            return Game.i.towerManager.F.MISSILE.getAbilityTextures(1);
        }
        return Game.i.towerManager.F.MISSILE.getWeaponTexture();
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
        return (isOutOfOrder() || this.attackDisabled || getTarget() == null || getTarget().getPosition().dst2(getTile().center) <= this.minRangeInPixels * this.minRangeInPixels) ? false : true;
    }

    @Override // com.prineside.tdi2.Tower
    public final boolean canAttackEnemy(Enemy enemy) {
        if (super.canAttackEnemy(enemy)) {
            return !enemy.isAir() || isAbilityInstalled(2);
        }
        return false;
    }

    @Override // com.prineside.tdi2.Tower
    public final void attack(int i) {
        b(getStat(TowerStatType.DAMAGE), 1.0f);
    }

    private void b(float f, float f2) {
        if (getTarget() == null) {
            e.i("Enemy is not valid", new Object[0]);
            return;
        }
        Vector2 vector2 = new Vector2();
        Vector2 vector22 = new Vector2();
        float stat = getStat(TowerStatType.U_EXPLOSION_RANGE);
        float stat2 = getStat(TowerStatType.PROJECTILE_SPEED);
        if (isAbilityInstalled(1) && this.S.gameValue.getIntValue(GameValueType.TOWER_MISSILE_A_COMPACT_COUNT) > 1) {
            int intValue = this.S.gameValue.getIntValue(GameValueType.TOWER_MISSILE_A_COMPACT_COUNT);
            float f3 = 60.0f;
            if (isAbilityInstalled(0)) {
                float percentValueAsMultiplier = 1.0f + ((float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_MISSILE_A_VERTICAL_ROTATION_BONUS));
                float f4 = percentValueAsMultiplier;
                if (percentValueAsMultiplier < 0.01f) {
                    f4 = 0.01f;
                }
                f3 = 60.0f * f4;
            }
            float f5 = 0.0f;
            float f6 = 1.0f / (intValue - 1);
            for (int i = 0; i < intValue; i++) {
                Enemy target = getTarget();
                if (i > 0 && this.h != null && this.h.length > i && this.h[i].enemy != null) {
                    target = this.h[i].enemy;
                }
                float f7 = this.angle + (-37.5f) + (f5 * 75.0f);
                vector2.set(0.0f, 20.0f);
                vector2.rotateDeg(f7);
                vector2.add(getTile().center);
                vector22.set(0.0f, 18.0f);
                vector22.rotateDeg(this.angle + 180.0f);
                vector2.add(vector22);
                MissileProjectile obtain = this.S.projectile.F.MISSILE.obtain();
                this.S.projectile.register(obtain);
                obtain.setup(this, target, f, stat, vector2, stat2, f3, f7, f2 * 0.75f);
                f5 += f6;
            }
        } else {
            MissileProjectile obtain2 = this.S.projectile.F.MISSILE.obtain();
            this.S.projectile.register(obtain2);
            obtain2.setup(this, getTarget(), f, stat, getTile().center, stat2, 90.0f, this.angle, f2);
        }
        this.shotCount++;
        if (this.S._sound != null) {
            this.S._sound.playShotSound(StaticSoundType.SHOT_MISSILE, this);
        }
    }

    @Override // com.prineside.tdi2.Tower
    public final float calculateStat(TowerStatType towerStatType) {
        float calculateStat = super.calculateStat(towerStatType);
        if (towerStatType == TowerStatType.RANGE && isAbilityInstalled(0)) {
            calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_MISSILE_A_VERTICAL_MAX_RANGE));
        }
        if (towerStatType == TowerStatType.MIN_RANGE && isAbilityInstalled(2)) {
            calculateStat = 0.0f;
        }
        if (towerStatType == TowerStatType.DAMAGE && isAbilityInstalled(1)) {
            calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_MISSILE_A_COMPACT_DAMAGE));
        }
        return calculateStat;
    }

    @Override // com.prineside.tdi2.Tower
    public final void drawWeapon(Batch batch, float f, float f2, float f3, float f4) {
        float f5 = f3 / 128.0f;
        if (getTile().visibleOnScreen && !isOutOfOrder()) {
            getWeaponTextures().draw(batch, f, f2, f3, f3, 1.0f, 1.0f, this.angle);
            float attackDelay = getAttackDelay();
            if (this.c > attackDelay / 2.0f) {
                float f6 = (this.c - (attackDelay / 2.0f)) / (attackDelay / 2.0f);
                float f7 = f6;
                if (f6 > 1.0f) {
                    f7 = 1.0f;
                }
                if (isAbilityInstalled(1) && this.S.gameValue.getIntValue(GameValueType.TOWER_MISSILE_A_COMPACT_COUNT) > 1) {
                    float f8 = f7 * 0.75f;
                    int intValue = this.S.gameValue.getIntValue(GameValueType.TOWER_MISSILE_A_COMPACT_COUNT);
                    float f9 = 0.0f;
                    float f10 = 1.0f / (intValue - 1);
                    for (int i = 0; i < intValue; i++) {
                        float f11 = this.angle + (-37.5f) + (f9 * 75.0f);
                        Vector2 vector2 = new Vector2();
                        Vector2 vector22 = new Vector2();
                        vector2.set(0.0f, 20.0f);
                        vector2.rotateDeg(f11);
                        vector2.add(getTile().center);
                        vector22.set(0.0f, 18.0f);
                        vector22.rotateDeg(this.angle + 180.0f);
                        vector2.add(vector22);
                        batch.draw(Game.i.towerManager.F.MISSILE.c, vector2.x - (7.0f * f8), vector2.y - (14.0f * f8), 7.0f * f8, 14.0f * f8, 14.0f * f8, 28.0f * f8, 1.0f, 1.0f, f11);
                        f9 += f10;
                    }
                    return;
                }
                batch.draw(Game.i.towerManager.F.MISSILE.c, (f + (f3 * 0.5f)) - ((7.0f * f7) * f5), (f2 + (f3 * 0.5f)) - ((14.0f * f7) * f5), 7.0f * f7 * f5, 14.0f * f7 * f5, 14.0f * f7 * f5, 28.0f * f7 * f5, 1.0f, 1.0f, this.angle);
            }
        }
    }

    @Override // com.prineside.tdi2.Tower
    public final void update(float f) {
        int intValue;
        if (isOutOfOrder()) {
            super.update(f);
            return;
        }
        a(f, getStat(TowerStatType.ROTATION_SPEED));
        this.i = (byte) (this.i + 1);
        if (this.i == 3) {
            float f2 = f * 3.0f;
            this.i = (byte) 0;
            boolean z = getTarget() == null;
            if (isAbilityInstalled(0)) {
                z = true;
            }
            if (getTarget() != null && isAbilityInstalled(4) && (intValue = this.S.gameValue.getIntValue(GameValueType.TOWER_MISSILE_A_COMPACT_COUNT) - 1) > 0) {
                if (this.h == null || this.h.length != intValue) {
                    this.h = new Enemy.EnemyReference[intValue];
                    Arrays.fill(this.h, Enemy.EnemyReference.NULL);
                }
                for (int i = 0; i < this.h.length; i++) {
                    Enemy enemy = this.h[i].enemy;
                    if (enemy == null || enemy == getTarget()) {
                        this.h[i] = Enemy.EnemyReference.NULL;
                        Enemy findTargetFiltered = findTargetFiltered(enemy2 -> {
                            for (Enemy.EnemyReference enemyReference : this.h) {
                                if (enemyReference.enemy == enemy2) {
                                    return false;
                                }
                            }
                            return enemy2 != getTarget();
                        });
                        if (findTargetFiltered != null) {
                            this.h[i] = this.S.enemy.getReference(findTargetFiltered);
                        } else {
                            this.h[i] = this.S.enemy.getReference(getTarget());
                        }
                    }
                }
            }
            if (z) {
                if (this.g.enemy == null && this.S.map.spawnedEnemies.size != 0) {
                    int i2 = 0;
                    while (true) {
                        if (i2 >= 10) {
                            break;
                        }
                        Enemy.EnemyReference enemyReference = this.S.map.spawnedEnemies.get(this.S.gameState.randomInt(this.S.map.spawnedEnemies.size));
                        if (enemyReference.enemy == null || !canAttackEnemy(enemyReference.enemy)) {
                            i2++;
                        } else {
                            this.g = enemyReference;
                            break;
                        }
                    }
                }
                if (this.g.enemy != null) {
                    if (!isOutOfOrder()) {
                        float stat = getStat(TowerStatType.U_LRM_AIM_SPEED);
                        if (isAbilityInstalled(0)) {
                            stat = (float) (stat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_MISSILE_A_VERTICAL_LRM_RATE));
                        }
                        this.f += f2 * stat;
                        if (this.f >= 100.0f) {
                            if (!this.attackDisabled) {
                                setTarget(this.g.enemy);
                                float percentValueAsMultiplier = (float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_MISSILE_LRM_DAMAGE);
                                if (isAbilityInstalled(2)) {
                                    percentValueAsMultiplier = (float) (percentValueAsMultiplier + this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_MISSILE_A_ANTI_AIR_LRM_DAMAGE));
                                }
                                b(getStat(TowerStatType.DAMAGE) * percentValueAsMultiplier, 1.5f);
                                setTarget(null);
                            }
                            this.f = 0.0f;
                            this.g = Enemy.EnemyReference.NULL;
                        }
                    }
                } else {
                    this.f = 0.0f;
                }
            } else {
                this.f = 0.0f;
            }
        }
        super.update(f);
    }

    @Override // com.prineside.tdi2.Tower
    public final void drawBatch(Batch batch, float f) {
        super.drawBatch(batch, f);
        if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DBG_DRAW_BUILDING_INFO) != 0.0d) {
            Game.i.assetManager.getDebugFont(false).draw(batch, (this.g.enemy == null ? "-" : "+") + SequenceUtils.SPACE + ((int) this.f), getTile().center.x - 50.0f, getTile().center.y - 30.0f, 100.0f, 1, false);
            if (this.g.enemy != null) {
                DrawUtils.texturedLineC(batch, Game.i.assetManager.getBlankWhiteTextureRegion(), getTile().center.x, getTile().center.y, this.g.enemy.getPosition().x, this.g.enemy.getPosition().y, 4.0f, MaterialColor.PINK.P600.toFloatBits(), MaterialColor.PINK.P400.toFloatBits());
                if (this.h != null) {
                    for (Enemy.EnemyReference enemyReference : this.h) {
                        if (enemyReference.enemy != null) {
                            DrawUtils.texturedLineC(batch, Game.i.assetManager.getBlankWhiteTextureRegion(), getTile().center.x, getTile().center.y, enemyReference.enemy.getPosition().x, enemyReference.enemy.getPosition().y, 3.0f, MaterialColor.PURPLE.P600.toFloatBits(), MaterialColor.PURPLE.P400.toFloatBits());
                        }
                    }
                }
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/towers/MissileTower$MissileTowerFactory.class */
    public static class MissileTowerFactory extends Tower.Factory<MissileTower> {
        TextureRegion c;

        public MissileTowerFactory() {
            super("tower-missile", TowerType.MISSILE);
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public void setup() {
            super.setup();
            this.f1784b[0].descriptionArgs = new String[]{"", "", ""};
            this.f1784b[1].descriptionArgs = new String[]{"", ""};
            this.f1784b[2].descriptionArgs = new String[]{""};
            this.f1784b[3].descriptionArgs = new String[]{"", ""};
            this.f1784b[4].descriptionArgs = new String[]{""};
        }

        @Override // com.prineside.tdi2.Tower.Factory
        @Null
        public CharSequence getStatMoreInfo(TowerStatType towerStatType, GameValueProvider gameValueProvider, Tower tower) {
            if (towerStatType == TowerStatType.U_LRM_AIM_SPEED) {
                float floatValue = gameValueProvider.getFloatValue(GameValueType.TOWER_MISSILE_LRM_DAMAGE);
                if (tower.isAbilityInstalled(2)) {
                    floatValue += gameValueProvider.getFloatValue(GameValueType.TOWER_MISSILE_A_ANTI_AIR_LRM_DAMAGE);
                }
                return Game.i.localeManager.i18n.format("tower_stat_more_info_MISSILE_U_LRM_AIM_SPEED", StringFormatter.compactNumberWithPrecisionTrimZeros(floatValue, 1, true).toString());
            }
            return null;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public Tower.AbilityConfig[] getAbilityConfigs(GameSystemProvider gameSystemProvider, Tower tower) {
            Tower.AbilityConfig[] abilityConfigs = super.getAbilityConfigs(gameSystemProvider, tower);
            abilityConfigs[0].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_MISSILE_A_VERTICAL_MAX_RANGE), 2, true).toString();
            abilityConfigs[0].descriptionArgs[1] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_MISSILE_A_VERTICAL_ROTATION_BONUS), 2, true).toString();
            abilityConfigs[0].descriptionArgs[2] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_MISSILE_A_VERTICAL_LRM_RATE), 2, true).toString();
            abilityConfigs[2].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_MISSILE_A_ANTI_AIR_LRM_DAMAGE), 2, true).toString();
            abilityConfigs[1].descriptionArgs[0] = StringFormatter.compactNumber(gameSystemProvider.gameValue.getIntValue(GameValueType.TOWER_MISSILE_A_COMPACT_COUNT), false).toString();
            abilityConfigs[1].descriptionArgs[1] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_MISSILE_A_COMPACT_DAMAGE), 2, true).toString();
            abilityConfigs[3].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_MISSILE_A_OVERWEIGHT_HP), 2, true).toString();
            float floatValue = gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_MISSILE_A_OVERWEIGHT_DAMAGE);
            if (tower.isAbilityInstalled(4)) {
                floatValue += gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_MISSILE_A_ULTIMATE_DAMAGE);
            }
            abilityConfigs[3].descriptionArgs[1] = StringFormatter.compactNumberWithPrecisionTrimZeros(floatValue, 2, true).toString();
            abilityConfigs[4].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_MISSILE_A_ULTIMATE_DAMAGE), 2, true).toString();
            return abilityConfigs;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public boolean shouldDrawAbilityToCache(int i) {
            if (i == 1) {
                return false;
            }
            return true;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public int getGeneralizedStat(GeneralizedTowerStatType generalizedTowerStatType) {
            switch (generalizedTowerStatType) {
                case RANGE:
                    return 5;
                case ATTACK_SPEED:
                    return 1;
                case DAMAGE:
                    return 5;
                case CROWD_DAMAGE:
                    return 5;
                case AGILITY:
                    return 2;
                default:
                    return 0;
            }
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public int getBuildHotKey() {
            return 50;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public Color getColor() {
            return MaterialColor.PINK.P500;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public void setupAssets() {
            this.c = Game.i.assetManager.getTextureRegion("projectile-missile");
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public String[] getAbilityAliases() {
            return MissileTower.ABILITY_ALIASES;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Tower.Factory
        public MissileTower create() {
            return new MissileTower((byte) 0);
        }
    }
}
