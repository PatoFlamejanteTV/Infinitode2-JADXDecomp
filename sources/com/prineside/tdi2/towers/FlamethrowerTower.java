package com.prineside.tdi2.towers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.GameValueProvider;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.buffs.BurnBuff;
import com.prineside.tdi2.buffs.FreezingBuff;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.GeneralizedTowerStatType;
import com.prineside.tdi2.enums.SpecialDamageType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.enums.TowerStatType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.utils.DrawUtils;
import com.prineside.tdi2.utils.FrameAccumulatorForPerformance;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.ObjectPair;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.Quad;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.TLog;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/towers/FlamethrowerTower.class */
public final class FlamethrowerTower extends Tower {
    private static final TLog e = TLog.forClass(FlamethrowerTower.class);
    private static final float[] f = {1.0f, 0.6f, 0.1f};
    private static final float[] g = {0.1f, 0.6f, 1.0f};
    public static final String[] ABILITY_ALIASES = {"PLASMA_IGNITION", "NAPALM", "COLD_FIRE"};
    private boolean h;
    private boolean i;
    public int instaKillPapersAccumulator;
    private boolean j;

    @FrameAccumulatorForPerformance
    private byte k;

    @FrameAccumulatorForPerformance
    private byte l;
    private float m;

    @NAGS
    private ParticleEffectPool.PooledEffect n;

    /* synthetic */ FlamethrowerTower(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Building, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeBoolean(this.h);
        output.writeBoolean(this.i);
        output.writeVarInt(this.instaKillPapersAccumulator, true);
        output.writeBoolean(this.j);
        output.writeByte(this.k);
        output.writeByte(this.l);
        output.writeFloat(this.m);
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Building, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.h = input.readBoolean();
        this.i = input.readBoolean();
        this.instaKillPapersAccumulator = input.readVarInt(true);
        this.j = input.readBoolean();
        this.k = input.readByte();
        this.l = input.readByte();
        this.m = input.readFloat();
    }

    private FlamethrowerTower() {
        super(TowerType.FLAMETHROWER);
        this.h = false;
    }

    @Override // com.prineside.tdi2.Tower
    public final Quad getWeaponTextures() {
        if (isAbilityInstalled(0)) {
            return Game.i.towerManager.F.FLAMETHROWER.getAbilityTextures(0);
        }
        return Game.i.towerManager.F.FLAMETHROWER.getWeaponTexture();
    }

    protected final float a() {
        float percentValueAsMultiplier = (float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_FLAMETHROWER_A_INSTAKILL_HP_MIN);
        return percentValueAsMultiplier + ((((float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_FLAMETHROWER_A_INSTAKILL_HP_MAX)) - percentValueAsMultiplier) * (getUpgradeLevel() / 10.0f));
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
    public final float getAttackDelay() {
        return 0.0f;
    }

    @Override // com.prineside.tdi2.Tower
    public final boolean canAttack() {
        return false;
    }

    @Override // com.prineside.tdi2.Tower
    public final float calculateStat(TowerStatType towerStatType) {
        float calculateStat = super.calculateStat(towerStatType);
        if (towerStatType == TowerStatType.U_DIRECT_FIRE_DAMAGE && isAbilityInstalled(0)) {
            calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_FLAMETHROWER_A_PLASMA_DAMAGE));
        }
        if (towerStatType == TowerStatType.U_DIRECT_FIRE_DAMAGE && isAbilityInstalled(1)) {
            calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_FLAMETHROWER_A_NAPALM_DIRECT_DAMAGE));
        }
        if (towerStatType == TowerStatType.DAMAGE && isAbilityInstalled(2)) {
            calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_FLAMETHROWER_A_COLD_DAMAGE));
        }
        if (towerStatType == TowerStatType.DAMAGE && isAbilityInstalled(1)) {
            calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_FLAMETHROWER_A_NAPALM_DAMAGE));
        }
        if (towerStatType == TowerStatType.U_BURNING_TIME && isAbilityInstalled(1)) {
            calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_FLAMETHROWER_A_NAPALM_DURATION));
        }
        if (towerStatType == TowerStatType.RANGE && isAbilityInstalled(3)) {
            calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_FLAMETHROWER_A_SUPPLY_RANGE));
        }
        return calculateStat;
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Building
    public final void updateCache() {
        super.updateCache();
        this.m = 20.0f;
        if (isAbilityInstalled(3)) {
            this.m = (float) (this.m * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_FLAMETHROWER_A_SUPPLY_ARC));
        }
        if (this.n != null) {
            this.n.allowCompletion();
            this.n = null;
        }
    }

    private void a(Array<Enemy> array) {
        Vector2 vector2 = getTile().center;
        Vector2 vector22 = new Vector2();
        this.S.map.getEnemiesInCircleFiltered(vector2.x, vector2.y, this.rangeInPixels, (f2, f3, f4) -> {
            float angleBetweenPoints = PMath.getAngleBetweenPoints(vector2.x, vector2.y, f2, f3);
            if (StrictMath.abs(PMath.getDistanceBetweenAngles(this.angle, angleBetweenPoints)) >= this.m) {
                return false;
            }
            if (StrictMath.abs(PMath.getDistanceBetweenAngles(this.angle, angleBetweenPoints)) < this.m * 0.5f) {
                return true;
            }
            PMath.getPointByAngleFromPoint(f2, f3, angleBetweenPoints - 90.0f, f4, vector22);
            if (StrictMath.abs(PMath.getDistanceBetweenAngles(this.angle, PMath.getAngleBetweenPoints(vector2, vector22))) < this.m * 0.5f) {
                return true;
            }
            PMath.getPointByAngleFromPoint(f2, f3, angleBetweenPoints + 90.0f, f4, vector22);
            return StrictMath.abs(PMath.getDistanceBetweenAngles(this.angle, PMath.getAngleBetweenPoints(vector2, vector22))) < this.m * 0.5f;
        }, (enemyReference, f5, f6, f7) -> {
            Enemy enemy = enemyReference.enemy;
            if (enemy == null || !canAttackEnemy(enemy)) {
                return true;
            }
            array.add(enemyReference.enemy);
            return true;
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void a(Array<Enemy> array, Array<Enemy> array2) {
        if (array.size != 0) {
            float f2 = (this.angle - (this.m * 0.5f)) + 360.0f;
            float f3 = this.angle + (this.m * 0.5f) + 360.0f;
            Vector2 vector2 = getTile().center;
            this.S.TSH.sort.sort(array, (enemy, enemy2) -> {
                return Float.compare(enemy.getPosition().dst2(vector2), enemy2.getPosition().dst2(vector2));
            });
            Array array3 = new Array(true, 1, Vector2.class);
            for (int i = 0; i < array.size; i++) {
                Enemy enemy3 = array.items[i];
                float angleBetweenPoints = PMath.getAngleBetweenPoints(vector2, enemy3.getPosition());
                Vector2 vector22 = new Vector2();
                PMath.getPointByAngleFromPoint(enemy3.getPosition().x, enemy3.getPosition().y, angleBetweenPoints - 90.0f, enemy3.getSize(), vector22);
                Vector2 vector23 = new Vector2();
                PMath.getPointByAngleFromPoint(enemy3.getPosition().x, enemy3.getPosition().y, angleBetweenPoints + 90.0f, enemy3.getSize(), vector23);
                float angleBetweenPoints2 = PMath.getAngleBetweenPoints(vector2, vector22);
                float angleBetweenPoints3 = PMath.getAngleBetweenPoints(vector2, vector23);
                float f4 = angleBetweenPoints2 + 360.0f;
                float f5 = angleBetweenPoints3 + 360.0f;
                float max = Math.max(f2, f4);
                float min = Math.min(f3, f5);
                boolean z = false;
                int i2 = 0;
                while (true) {
                    if (i2 >= array3.size) {
                        break;
                    }
                    Vector2 vector24 = ((Vector2[]) array3.items)[i2];
                    float f6 = vector24.x;
                    float f7 = vector24.y;
                    if (f6 > max || f7 < min) {
                        i2++;
                    } else {
                        z = true;
                        break;
                    }
                }
                if (!z) {
                    array3.add(new Vector2(f4, f5));
                    array2.add(enemy3);
                }
            }
            array3.clear();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void a(float f2) {
        this.h = false;
        float f3 = -1.0f;
        if (isAbilityInstalled(4)) {
            f3 = a();
        }
        float stat = getStat(TowerStatType.DAMAGE) * f2;
        boolean isAbilityInstalled = isAbilityInstalled(2);
        float stat2 = getStat(TowerStatType.U_DIRECT_FIRE_DAMAGE) * 0.01f;
        float f4 = f3;
        Array<Enemy> enemyArray = this.S.TSH.getEnemyArray();
        a(enemyArray);
        Array<Enemy> enemyArray2 = this.S.TSH.getEnemyArray();
        if (isAbilityInstalled(2)) {
            enemyArray2.addAll(enemyArray);
        } else {
            a(enemyArray, enemyArray2);
        }
        this.S.TSH.freeEnemyArray(enemyArray);
        this.h = enemyArray2.size != 0;
        if (this.h) {
            for (int i = 0; i < enemyArray2.size; i++) {
                Enemy enemy = enemyArray2.items[i];
                if (enemy.isRegistered()) {
                    float f5 = stat * stat2;
                    if (!enemy.isVulnerableToSpecial(SpecialDamageType.INSTAKILL) || enemy.getHealth() / enemy.maxHealth >= f4) {
                        float stat3 = getStat(TowerStatType.U_BURNING_TIME);
                        if (isAbilityInstalled) {
                            float floatValue = this.S.gameValue.getFloatValue(GameValueType.TOWER_FLAMETHROWER_A_COLD_FREEZING);
                            if (isAbilityInstalled(1)) {
                                floatValue += this.S.gameValue.getFloatValue(GameValueType.TOWER_FLAMETHROWER_A_NAPALM_FREEZING);
                            }
                            boolean z = false;
                            DelayedRemovalArray buffsByTypeOrNull = enemy.getBuffsByTypeOrNull(BuffType.FREEZING);
                            if (buffsByTypeOrNull != null && buffsByTypeOrNull.size != 0) {
                                int i2 = 0;
                                while (true) {
                                    if (i2 >= buffsByTypeOrNull.size) {
                                        break;
                                    }
                                    FreezingBuff freezingBuff = (FreezingBuff) buffsByTypeOrNull.get(i2);
                                    if (freezingBuff.tower != this) {
                                        i2++;
                                    } else {
                                        freezingBuff.speed = 10.0f;
                                        freezingBuff.maxPercent = floatValue;
                                        freezingBuff.duration = stat3;
                                        freezingBuff.maxDuration = stat3 * 10.0f;
                                        z = true;
                                        break;
                                    }
                                }
                            }
                            if (!z) {
                                FreezingBuff freezingBuff2 = new FreezingBuff();
                                freezingBuff2.setup(this, 10.0f, floatValue, stat3, stat3 * 10.0f, 0.0f, 0.0f);
                                this.S.buff.P_FREEZING.addBuff(enemy, freezingBuff2);
                            }
                            if (enemy.hasBuffsByType(BuffType.BURN)) {
                                float percentValueAsMultiplier = (float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_FLAMETHROWER_A_COLD_DAMAGE_TO_BURNING);
                                if (isAbilityInstalled(1)) {
                                    percentValueAsMultiplier = (float) (percentValueAsMultiplier + this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_FLAMETHROWER_A_NAPALM_COLD_FIRE_DMG));
                                }
                                f5 *= percentValueAsMultiplier + 1.0f;
                            }
                        } else {
                            enemy.ignitionProgress += f2 / this.S.gameValue.getFloatValue(GameValueType.TOWER_FLAMETHROWER_TIME_TO_IGNITE);
                            enemy.ignitionIncreasedLastFrame = this.S.gameState.updateNumber;
                            if (enemy.ignitionProgress >= 1.0f) {
                                enemy.ignitionProgress = 1.0f;
                                BurnBuff burnBuff = new BurnBuff();
                                burnBuff.setup(this, stat3, stat3 * 10.0f, getStat(TowerStatType.DAMAGE), null);
                                if (isAbilityInstalled(4)) {
                                    burnBuff.bonusDamagePerEnemyNearby = (float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_FLAMETHROWER_A_ULTIMATE_CROWD_BONUS);
                                }
                                this.S.buff.P_BURN.addBuff(enemy, burnBuff);
                            }
                        }
                        if (f5 > 0.0f) {
                            this.S.damage.queueDamage(this.S.damage.obtainRecord().setup(enemy, f5, DamageType.FIRE).setTower(this).setEfficiency(4));
                        }
                    } else if (enemy.getBuffedDamageMultiplier(TowerType.FLAMETHROWER, DamageType.FIRE) > 0.0f) {
                        this.S.damage.queueDamage(this.S.damage.obtainRecord().setup(enemy, enemy.getHealth() + 1.0f, DamageType.FIRE).setTower(this).setCleanForDps(false).setLethal(true).setEfficiency(8).setIgnoreTowerEfficiency(true));
                    }
                }
            }
        }
        this.S.TSH.freeEnemyArray(enemyArray2);
        if (!this.i && this.h) {
            this.shotCount++;
            if (this.S._sound != null) {
                this.S._sound.playShotSound(StaticSoundType.SHOT_FLAMETHROWER, this);
            }
        }
        this.i = this.h;
        if (this.h) {
            this.c = 0.0f;
        }
    }

    @Override // com.prineside.tdi2.Tower
    public final void setAimStrategy(Tower.AimStrategy aimStrategy) {
        super.setAimStrategy(aimStrategy);
        this.j = true;
        b();
    }

    private void b() {
        if (this.n != null) {
            this.n.allowCompletion();
            this.n = null;
        }
    }

    private void c() {
        Array<ParticleEmitter> emitters = this.n.getEmitters();
        float range = getRange() * 2.0f * 0.75f;
        ParticleEmitter particleEmitter = emitters.get(0);
        particleEmitter.getXScale().setHigh(48.0f * range);
        particleEmitter.getYScale().setHigh(48.0f * range);
        particleEmitter.getXScale().setLow(4.0f * range);
        particleEmitter.getYScale().setLow(4.0f * range);
        ParticleEmitter particleEmitter2 = emitters.get(1);
        particleEmitter2.getXScale().setHigh(20.0f * range);
        particleEmitter2.getYScale().setHigh(20.0f * range);
        particleEmitter2.getXScale().setLow(3.0f * range);
        particleEmitter2.getYScale().setLow(3.0f * range);
        particleEmitter.getVelocity().setHigh(170.0f * range, 230.0f * range);
        particleEmitter.getVelocity().setLow(60.0f * range);
        particleEmitter2.getVelocity().setHigh(120.0f * range, 150.0f * range);
        if (isAbilityInstalled(2)) {
            particleEmitter.getTint().setColors(g);
        } else {
            particleEmitter.getTint().setColors(f);
        }
    }

    @Override // com.prineside.tdi2.Tower
    public final void update(float f2) {
        super.update(f2);
        if (isOutOfOrder()) {
            b();
            return;
        }
        a(f2, getStat(TowerStatType.ROTATION_SPEED));
        boolean z = this.S._particle != null && Game.i.settingsManager.isParticlesDrawing() && !this.S._particle.willParticleBeSkipped() && Game.i.settingsManager.isProjectilesDrawing();
        boolean z2 = z;
        if (!z) {
            b();
        }
        this.l = (byte) (this.l + 1);
        if (this.l == 5) {
            this.l = (byte) 0;
            if (this.j) {
                this.j = false;
            } else {
                a(f2 * 5.0f);
                if (this.h && !isOutOfOrder()) {
                    if (this.n == null && this.S._particle != null && z2) {
                        this.n = Game.i.towerManager.F.FLAMETHROWER.c.obtain();
                        c();
                        this.S._particle.addParticle(this.n, true);
                    }
                }
            }
            b();
        }
        if (this.f1776a != 0) {
            this.k = (byte) (this.k + 1);
            if (this.k == 15) {
                this.k = (byte) 0;
                setTarget(findTarget());
            }
        }
        if (this.n != null) {
            Vector2 add = new Vector2(42.0f, 0.0f).rotateDeg(this.angle + 90.0f).add(getTile().center);
            this.n.setPosition(add.x, add.y);
            Array<ParticleEmitter> emitters = this.n.getEmitters();
            for (int i = 0; i < emitters.size; i++) {
                emitters.get(i).getAngle().setHigh(((-this.m) * 0.5f) + this.angle + 90.0f, (this.m * 0.5f) + this.angle + 90.0f);
            }
        }
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Building
    public final void removedFromMap() {
        super.removedFromMap();
        b();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.prineside.tdi2.Tower
    public final void drawBatch(Batch batch, float f2) {
        super.drawBatch(batch, f2);
        if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DBG_DRAW_BUILDING_INFO) != 0.0d && this.S.map.aabbGenerated()) {
            float f3 = (this.angle - (this.m * 0.5f)) + 360.0f;
            float f4 = this.angle + (this.m * 0.5f) + 360.0f;
            Game.i.assetManager.getSmallDebugFont().setColor(MaterialColor.CYAN.P300);
            Vector2 vector2 = new Vector2();
            PMath.getPointByAngleFromPoint(getTile().center.x, getTile().center.y, f3, this.rangeInPixels, vector2);
            DrawUtils.texturedLineC(batch, Game.i.assetManager.getBlankWhiteTextureRegion(), getTile().center.x, getTile().center.y, vector2.x, vector2.y, 2.0f, MaterialColor.CYAN.P900.toFloatBits(), MaterialColor.CYAN.P900.toFloatBits());
            Game.i.assetManager.getSmallDebugFont().draw(batch, ((int) f3) + "deg", vector2.x, vector2.y, 1.0f, 1, false);
            PMath.getPointByAngleFromPoint(getTile().center.x, getTile().center.y, f4, this.rangeInPixels, vector2);
            DrawUtils.texturedLineC(batch, Game.i.assetManager.getBlankWhiteTextureRegion(), getTile().center.x, getTile().center.y, vector2.x, vector2.y, 2.0f, MaterialColor.CYAN.P900.toFloatBits(), MaterialColor.CYAN.P900.toFloatBits());
            Game.i.assetManager.getSmallDebugFont().draw(batch, ((int) f4) + "deg", vector2.x, vector2.y, 1.0f, 1, false);
            Game.i.assetManager.getSmallDebugFont().setColor(Color.WHITE);
            Array<Enemy> array = new Array<>(true, 1, Enemy.class);
            a(array);
            if (array.size != 0) {
                Vector2 vector22 = getTile().center;
                array.sort((enemy, enemy2) -> {
                    return Float.compare(enemy.getPosition().dst2(vector22), enemy2.getPosition().dst2(vector22));
                });
                for (int i = 0; i < array.size; i++) {
                    Enemy enemy3 = array.items[i];
                    Game.i.assetManager.getSmallDebugFont().draw(batch, "#" + (i + 1), enemy3.getPosition().x, enemy3.getPosition().y, 1.0f, 1, false);
                }
                Array array2 = new Array(true, 1, ObjectPair.class);
                for (int i2 = 0; i2 < array.size; i2++) {
                    Enemy enemy4 = array.items[i2];
                    float angleBetweenPoints = PMath.getAngleBetweenPoints(vector22, enemy4.getPosition());
                    Vector2 vector23 = new Vector2();
                    PMath.getPointByAngleFromPoint(enemy4.getPosition().x, enemy4.getPosition().y, angleBetweenPoints - 90.0f, enemy4.getSize(), vector23);
                    batch.setColor(Color.RED);
                    batch.draw(AssetManager.TextureRegions.i().smallCircle, vector23.x - 2.0f, vector23.y - 2.0f, 4.0f, 4.0f);
                    Vector2 vector24 = new Vector2();
                    PMath.getPointByAngleFromPoint(enemy4.getPosition().x, enemy4.getPosition().y, angleBetweenPoints + 90.0f, enemy4.getSize(), vector24);
                    batch.setColor(Color.GREEN);
                    batch.draw(AssetManager.TextureRegions.i().smallCircle, vector24.x - 2.0f, vector24.y - 2.0f, 4.0f, 4.0f);
                    float angleBetweenPoints2 = PMath.getAngleBetweenPoints(vector22, vector23);
                    float angleBetweenPoints3 = PMath.getAngleBetweenPoints(vector22, vector24);
                    float f5 = angleBetweenPoints2 + 360.0f;
                    float f6 = angleBetweenPoints3 + 360.0f;
                    Game.i.assetManager.getSmallDebugFont().setColor(Color.RED);
                    Game.i.assetManager.getSmallDebugFont().draw(batch, ((int) f5) + "deg", vector23.x, vector23.y + 20.0f, 1.0f, 1, false);
                    Game.i.assetManager.getSmallDebugFont().setColor(Color.GREEN);
                    Game.i.assetManager.getSmallDebugFont().draw(batch, ((int) f6) + "deg", vector24.x, vector24.y + 20.0f, 1.0f, 1, false);
                    Game.i.assetManager.getSmallDebugFont().setColor(Color.WHITE);
                    float max = Math.max(f3, f5);
                    float min = Math.min(f4, f6);
                    boolean z = false;
                    int i3 = 0;
                    while (true) {
                        if (i3 >= array2.size) {
                            break;
                        }
                        ObjectPair objectPair = ((ObjectPair[]) array2.items)[i3];
                        float f7 = ((Vector2) objectPair.first).x;
                        float f8 = ((Vector2) objectPair.first).y;
                        if (f7 > max || f8 < min) {
                            i3++;
                        } else {
                            z = true;
                            break;
                        }
                    }
                    if (!z) {
                        array2.add(new ObjectPair(new Vector2(f5, f6), enemy4));
                        Game.i.assetManager.getSmallDebugFont().setColor(MaterialColor.ORANGE.P500);
                        Game.i.assetManager.getSmallDebugFont().draw(batch, "HIT", enemy4.getPosition().x, enemy4.getPosition().y + 20.0f, 1.0f, 1, false);
                        Game.i.assetManager.getSmallDebugFont().setColor(Color.WHITE);
                    }
                }
            }
            array.clear();
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/towers/FlamethrowerTower$FlamethrowerTowerFactory.class */
    public static class FlamethrowerTowerFactory extends Tower.Factory<FlamethrowerTower> {
        ParticleEffectPool c;

        public FlamethrowerTowerFactory() {
            super("tower-flamethrower", TowerType.FLAMETHROWER);
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public void setup() {
            super.setup();
            this.f1784b[0].descriptionArgs = new String[]{""};
            this.f1784b[1].descriptionArgs = new String[]{"", "", "", "", ""};
            this.f1784b[2].descriptionArgs = new String[]{"", "", ""};
            this.f1784b[3].descriptionArgs = new String[]{"", ""};
            this.f1784b[4].descriptionArgs = new String[]{"", ""};
        }

        @Override // com.prineside.tdi2.Tower.Factory
        @Null
        public CharSequence getStatMoreInfo(TowerStatType towerStatType, GameValueProvider gameValueProvider, Tower tower) {
            if (towerStatType == TowerStatType.U_BURNING_TIME) {
                return Game.i.localeManager.i18n.format("tower_stat_more_info_FLAMETHROWER_U_BURNING_TIME", Float.valueOf(gameValueProvider.getFloatValue(GameValueType.TOWER_FLAMETHROWER_TIME_TO_IGNITE)));
            }
            if (towerStatType == TowerStatType.U_DIRECT_FIRE_DAMAGE) {
                return Game.i.localeManager.i18n.get("tower_stat_more_info_FLAMETHROWER_U_DIRECT_FIRE_DAMAGE");
            }
            return null;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public Tower.AbilityConfig[] getAbilityConfigs(GameSystemProvider gameSystemProvider, Tower tower) {
            Tower.AbilityConfig[] abilityConfigs = super.getAbilityConfigs(gameSystemProvider, tower);
            abilityConfigs[0].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_FLAMETHROWER_A_PLASMA_DAMAGE), 2, true).toString();
            abilityConfigs[1].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_FLAMETHROWER_A_NAPALM_DURATION), 2, true).toString();
            abilityConfigs[1].descriptionArgs[1] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_FLAMETHROWER_A_NAPALM_DAMAGE), 2, true).toString();
            abilityConfigs[1].descriptionArgs[2] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_FLAMETHROWER_A_NAPALM_DIRECT_DAMAGE), 2, true).toString();
            abilityConfigs[1].descriptionArgs[3] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_FLAMETHROWER_A_NAPALM_FREEZING), 2, true).toString();
            abilityConfigs[1].descriptionArgs[4] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_FLAMETHROWER_A_NAPALM_COLD_FIRE_DMG), 2, true).toString();
            abilityConfigs[2].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_FLAMETHROWER_A_COLD_DAMAGE), 2, true).toString();
            abilityConfigs[2].descriptionArgs[1] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_FLAMETHROWER_A_COLD_FREEZING), 2, true).toString();
            abilityConfigs[2].descriptionArgs[2] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_FLAMETHROWER_A_COLD_DAMAGE_TO_BURNING), 2, true).toString();
            abilityConfigs[3].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_FLAMETHROWER_A_SUPPLY_RANGE), 2, true).toString();
            abilityConfigs[3].descriptionArgs[1] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_FLAMETHROWER_A_SUPPLY_ARC), 2, true).toString();
            abilityConfigs[4].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(((FlamethrowerTower) tower).a() * 100.0f, 2, true).toString();
            abilityConfigs[4].descriptionArgs[1] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_FLAMETHROWER_A_ULTIMATE_CROWD_BONUS), 2, true).toString();
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
            this.c = Game.i.assetManager.getParticleEffectPool("flamethrower.prt");
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public int getBuildHotKey() {
            return 48;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public Color getColor() {
            return MaterialColor.ORANGE.P500;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public int getGeneralizedStat(GeneralizedTowerStatType generalizedTowerStatType) {
            switch (generalizedTowerStatType) {
                case RANGE:
                    return 2;
                case ATTACK_SPEED:
                    return 5;
                case DAMAGE:
                    return 4;
                case CROWD_DAMAGE:
                    return 5;
                case AGILITY:
                    return 2;
                default:
                    return 0;
            }
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public String[] getAbilityAliases() {
            return FlamethrowerTower.ABILITY_ALIASES;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Tower.Factory
        public FlamethrowerTower create() {
            return new FlamethrowerTower((byte) 0);
        }
    }
}
