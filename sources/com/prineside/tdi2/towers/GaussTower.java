package com.prineside.tdi2.towers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.NumberUtils;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.Pools;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.GameValueProvider;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.Miner;
import com.prineside.tdi2.SerializableListener;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.buffs.DeathExplosionBuff;
import com.prineside.tdi2.buffs.StunBuff;
import com.prineside.tdi2.enums.AchievementType;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.GeneralizedTowerStatType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.enums.ShapeType;
import com.prineside.tdi2.enums.SpecialDamageType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.enums.TowerStatType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.EnemyDie;
import com.prineside.tdi2.explosions.GenericExplosion;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.shapes.BulletSmokeMultiLine;
import com.prineside.tdi2.shapes.MultiLine;
import com.prineside.tdi2.shapes.PieChart;
import com.prineside.tdi2.tiles.SourceTile;
import com.prineside.tdi2.ui.actors.ComplexButton;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.PieChartActor;
import com.prineside.tdi2.utils.FastRandom;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.Quad;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.Locale;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/towers/GaussTower.class */
public final class GaussTower extends Tower {
    private float f;
    private boolean g;
    private float h;
    private float i;
    private int j;
    private float k;
    private float l;
    private boolean m;
    private float n;
    private int o;

    @NAGS
    private float p;

    @NAGS
    private final Vector2 q;

    @NAGS
    private MultiLine r;

    @NAGS
    private float s;

    @NAGS
    private MultiLine t;

    @NAGS
    private final DelayedRemovalArray<Trail> v;
    private static final TLog e = TLog.forClass(GaussTower.class);
    public static final String[] ABILITY_ALIASES = {"NANOPARTICLES", "SELF_IMPROVEMENT", "SUPERCONDUCTORS"};
    private static final Color u = new Color();

    /* synthetic */ GaussTower(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Building, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.f);
        output.writeBoolean(this.g);
        output.writeFloat(this.h);
        output.writeFloat(this.i);
        output.writeVarInt(this.j, true);
        output.writeFloat(this.k);
        output.writeFloat(this.l);
        output.writeBoolean(this.m);
        output.writeFloat(this.n);
        output.writeVarInt(this.o, true);
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Building, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f = input.readFloat();
        this.g = input.readBoolean();
        this.h = input.readFloat();
        this.i = input.readFloat();
        this.j = input.readVarInt(true);
        this.k = input.readFloat();
        this.l = input.readFloat();
        this.m = input.readBoolean();
        this.n = input.readFloat();
        this.o = input.readVarInt(true);
    }

    private GaussTower() {
        super(TowerType.GAUSS);
        this.q = new Vector2();
        this.s = -1000.0f;
        this.v = new DelayedRemovalArray<>(Trail.class);
    }

    @Override // com.prineside.tdi2.Tower, com.prineside.tdi2.Registrable
    public final void setUnregistered() {
        super.setUnregistered();
    }

    @Override // com.prineside.tdi2.Tower
    public final Quad getWeaponTextures() {
        if (isAbilityInstalled(0)) {
            return Game.i.towerManager.F.GAUSS.getAbilityTextures(0);
        }
        return Game.i.towerManager.F.GAUSS.getWeaponTexture();
    }

    @Override // com.prineside.tdi2.Tower
    public final boolean canAim() {
        return false;
    }

    @Override // com.prineside.tdi2.Tower
    public final boolean shouldSearchForTarget() {
        return false;
    }

    @Override // com.prineside.tdi2.Tower
    public final boolean canAttack() {
        return false;
    }

    @Override // com.prineside.tdi2.Tower
    public final float getAttackDelay() {
        return 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Enemy enemy) {
        if (enemy != null && enemy.gaveMiningSpeedForGauss) {
            return;
        }
        float floatValue = this.S.gameValue.getFloatValue(GameValueType.TOWER_GAUSS_A_ULTIMATE_MINING_TIME);
        this.S.map.traverseNeighborTilesAroundTile(getTile(), tile -> {
            if (tile.type == TileType.SOURCE) {
                SourceTile sourceTile = (SourceTile) tile;
                if (sourceTile.miner != null) {
                    sourceTile.miner.doubleSpeedTimeLeft += floatValue;
                    return true;
                }
                return true;
            }
            return true;
        });
        if (enemy != null) {
            enemy.gaveMiningSpeedForGauss = true;
        }
    }

    private void f() {
        Vector2 add = new Vector2(0.0f, 1.0f).rotateDeg(this.angle).scl(7680.0f).add(getTile().center);
        float[] fArr = {5296.0f};
        boolean[] zArr = {false};
        boolean z = false;
        int intValue = this.S.gameValue.getIntValue(GameValueType.TOWER_GAUSS_A_OVERLOAD_SHOTS);
        int i = intValue;
        if (intValue <= 0) {
            i = 1;
        }
        if (this.o % i == 0 && isAbilityInstalled(3)) {
            zArr[0] = true;
            z = true;
        }
        float[] fArr2 = {getStat(TowerStatType.DAMAGE)};
        int[] iArr = {0};
        float floatValue = this.S.gameValue.getFloatValue(GameValueType.TOWER_GAUSS_ATTACK_RAY_WIDTH);
        this.S.map.rayCastEnemiesSorted(getTile().center.x, getTile().center.y, add.x, add.y, 128.0f * floatValue, enemyReference -> {
            Enemy enemy = enemyReference.enemy;
            if (enemy != null && canAttackEnemy(enemy)) {
                float buffedDamageMultiplier = enemy.getBuffedDamageMultiplier(TowerType.GAUSS, DamageType.BULLET);
                if (buffedDamageMultiplier == 0.0f) {
                    return true;
                }
                float f = -0.001f;
                if (this.installedAbilities[0] && enemy.isVulnerableToSpecial(SpecialDamageType.NANOPARTICLES)) {
                    f = (enemy.maxHealth / buffedDamageMultiplier) * ((float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_GAUSS_A_NANO_HP));
                }
                float health = (enemy.getHealth() / buffedDamageMultiplier) - f;
                if (health <= 0.0f) {
                    return true;
                }
                if (Float.isNaN(health)) {
                    e.e("canReceiveDamage " + health + SequenceUtils.SPACE + enemy.getHealth() + SequenceUtils.SPACE + buffedDamageMultiplier + SequenceUtils.SPACE + f + SequenceUtils.SPACE + enemy, new Object[0]);
                    return true;
                }
                if (zArr[0]) {
                    DeathExplosionBuff deathExplosionBuff = new DeathExplosionBuff();
                    GenericExplosion obtain = this.S.explosion.F.GENERIC.obtain();
                    obtain.setup(this, 0.0f, 0.0f, enemy.maxHealth * ((float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_GAUSS_A_OVERLOAD_DAMAGE)), 1.0f, 0, 0.0f, 0.0f, MaterialColor.PURPLE.P400, null);
                    deathExplosionBuff.setup(120.0f, 1200.0f, obtain);
                    this.S.buff.P_DEATH_EXPLOSION.addBuff(enemy, deathExplosionBuff);
                    zArr[0] = false;
                }
                boolean z2 = false;
                if (fArr2[0] > health) {
                    this.S.damage.queueDamage(this.S.damage.obtainRecord().setup(enemy, health, DamageType.BULLET).setTower(this));
                    Enemy enemy2 = enemyReference.enemy;
                    fArr2[0] = fArr2[0] - health;
                    if (isAbilityInstalled(4)) {
                        a(enemy2);
                    }
                } else {
                    fArr[0] = getTile().center.dst(enemy.getPosition()) - 80.0f;
                    this.S.damage.queueDamage(this.S.damage.obtainRecord().setup(enemy, fArr2[0], DamageType.BULLET).setTower(this));
                    z2 = true;
                }
                return !z2;
            }
            return true;
        });
        if (z) {
            this.S.map.rayCastEnemies(getTile().center.x, getTile().center.y, add.x, add.y, 128.0f * floatValue, enemyReference2 -> {
                Enemy enemy = enemyReference2.enemy;
                if (enemy != null && enemy.getHealth() > 0.0f && canAttackEnemy(enemy) && !enemy.wasStunnedByGauss && enemy.getBuffedDamageMultiplier(TowerType.GAUSS, DamageType.BULLET) != 0.0f && enemy.canBeBuffed(BuffType.STUN)) {
                    StunBuff stunBuff = new StunBuff();
                    stunBuff.copyDisabled = true;
                    enemy.wasStunnedByGauss = true;
                    float i2 = i();
                    stunBuff.setup(i2, i2 * 10.0f, this.id);
                    this.S.buff.P_STUN.addBuff(enemy, stunBuff);
                    iArr[0] = iArr[0] + 1;
                    this.S.achievement.setProgress(AchievementType.MASS_STUN_ENEMIES_ONE_SHOT, iArr[0]);
                    return true;
                }
                return true;
            });
        }
        this.shotCount++;
        if (!this.S.gameState.canSkipMediaUpdate()) {
            if (this.S._particle != null && Game.i.settingsManager.isParticlesDrawing()) {
                add.set(getTile().center);
                ParticleEffectPool.PooledEffect obtain = Game.i.towerManager.F.GAUSS.g.obtain();
                PMath.shiftPointByAngle(add, this.angle, 80.0f);
                obtain.setPosition(add.x, add.y);
                obtain.getEmitters().get(0).getAngle().setHigh(this.angle + 90.0f);
                obtain.getEmitters().get(1).getAngle().setHigh((this.angle - 15.0f) + 90.0f, this.angle + 15.0f + 90.0f);
                obtain.getEmitters().get(1).getAngle().setLow(this.angle + 90.0f);
                obtain.getEmitters().get(0).getRotation().setHigh(this.angle);
                this.S._particle.addParticle(obtain, true);
                Trail trail = (Trail) Pools.obtain(Trail.class);
                Vector2 vector2 = new Vector2();
                vector2.set(0.0f, 1.0f).rotateDeg(this.angle).scl(fArr[0]).add(add);
                trail.a(add.x, add.y, vector2.x, vector2.y);
                this.v.add(trail);
            }
            if (this.S._projectileTrail != null && this.S._projectileTrail.isEnabled()) {
                BulletSmokeMultiLine obtain2 = Game.i.shapeManager.F.BULLET_SMOKE_MULTI_LINE.obtain();
                PMath.getPointByAngleFromPoint(getTile().center.x, getTile().center.y, this.angle, 80.0f, add);
                obtain2.setTexture(Game.i.towerManager.F.GAUSS.e, false, FastRandom.getFloat() < 0.5f);
                obtain2.maxSegmentWidth = 23.04f;
                obtain2.maxAlpha = 1.0f;
                obtain2.setColor(MaterialColor.PURPLE.P500);
                Vector2 vector22 = new Vector2();
                PMath.getPointByAngleFromPoint(add.x, add.y, this.angle, fArr[0], vector22);
                obtain2.setup(add.x, add.y, vector22.x, vector22.y);
                this.S._projectileTrail.addTrail(obtain2);
            }
            this.q.set(0.0f, 1.0f).scl(15.36f).rotateDeg(this.angle + 180.0f);
            this.p = 1.0f;
            if (this.S._sound != null) {
                this.S._sound.playShotSound(StaticSoundType.SHOT_GAUSS, this);
            }
        }
        this.f = 0.0f;
        this.k -= getStat(TowerStatType.RESOURCE_CONSUMPTION);
        if (this.k < 0.0f) {
            this.k = 0.0f;
        }
        this.l = 0.0f;
        this.m = false;
        this.g = true;
        this.o++;
    }

    @Override // com.prineside.tdi2.Tower
    public final void attack(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            f();
        }
    }

    private float g() {
        return this.S.gameValue.getFloatValue(GameValueType.TOWER_GAUSS_A_IMPROVEMENT_XP) + (this.j * this.S.gameValue.getFloatValue(GameValueType.TOWER_GAUSS_A_IMPROVEMENT_XP_PER_LEVEL));
    }

    @Override // com.prineside.tdi2.Tower
    public final void addExperience(float f) {
        if (isAbilityInstalled(1)) {
            float g = g();
            float percentValueAsMultiplier = f * ((float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_GAUSS_A_IMPROVEMENT_BURN));
            this.i += percentValueAsMultiplier;
            while (this.i >= g) {
                this.j++;
                this.i -= g;
                this.S.map.markTilesDirtyNearTile(getTile(), 1);
            }
            super.addExperience(f - percentValueAsMultiplier);
            return;
        }
        super.addExperience(f);
    }

    @Override // com.prineside.tdi2.Tower
    public final float calculateStat(TowerStatType towerStatType) {
        float calculateStat = super.calculateStat(towerStatType);
        if (towerStatType == TowerStatType.DAMAGE && isAbilityInstalled(0)) {
            calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_GAUSS_A_NANO_DAMAGE));
        }
        if (towerStatType == TowerStatType.DAMAGE && isAbilityInstalled(1)) {
            calculateStat = (float) (calculateStat * (1.0d + (this.j * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_GAUSS_A_IMPROVEMENT_DAMAGE))));
        }
        if (towerStatType == TowerStatType.RESOURCE_CONSUMPTION) {
            double percentValueAsMultiplier = this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_GAUSS_UPGRADE_RESOURCE_CONSUMPTION);
            double statFromConfig = Game.i.towerManager.getStatFromConfig(this.type, towerStatType, 0, getLevel(), this.S.gameValue) * this.S.gameValue.getGlobalStatMultiplier(towerStatType);
            calculateStat = (float) (statFromConfig + ((calculateStat - statFromConfig) * percentValueAsMultiplier));
            if (isAbilityInstalled(2)) {
                calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_GAUSS_A_CONDUCTORS_RESOURCE_CONSUMPTION));
            }
        }
        if (towerStatType == TowerStatType.CHARGING_SPEED && isAbilityInstalled(2)) {
            calculateStat = (float) (calculateStat * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_GAUSS_A_CONDUCTORS_CHARGING_SPEED));
        }
        return calculateStat;
    }

    private void h() {
        this.r.reset();
        this.r.setTextureRegion(Game.i.towerManager.F.GAUSS.d, false, false);
        u.set(1.0f, 1.0f, 1.0f, 0.0f);
        Vector2 vector2 = new Vector2();
        Vector2 vector22 = new Vector2();
        vector2.set(getTile().center);
        vector22.set(0.0f, 64.0f);
        vector22.rotateDeg(this.angle);
        this.r.appendNode(vector2.x, vector2.y, 4.0f, u.toFloatBits(), false);
        for (int i = 0; i < 16; i++) {
            u.f889a = (1.0f - ((i + 1) / 16.0f)) * 0.28f;
            vector2.add(vector22);
            this.r.appendNode(vector2.x, vector2.y, 4.0f, u.toFloatBits(), false);
        }
        this.r.updateAllNodes();
        if (this.s != this.h) {
            this.t.reset();
            this.t.setTextureRegion(Game.i.towerManager.F.GAUSS.d, false, false);
            u.set(MaterialColor.LIGHT_GREEN.P500);
            u.f889a = 0.0f;
            vector2.set(getTile().center);
            vector22.set(0.0f, 64.0f);
            vector22.rotateDeg(this.h);
            this.t.appendNode(vector2.x, vector2.y, 4.0f, u.toFloatBits(), false);
            for (int i2 = 0; i2 < 16; i2++) {
                u.f889a = (1.0f - ((i2 + 1) / 16.0f)) * 0.28f;
                vector2.add(vector22);
                this.t.appendNode(vector2.x, vector2.y, 4.0f, u.toFloatBits(), false);
            }
            this.t.updateAllNodes();
            this.s = this.h;
        }
    }

    @Override // com.prineside.tdi2.Tower
    public final void drawWeapon(Batch batch, float f, float f2, float f3, float f4) {
        float f5 = f3 / 128.0f;
        float floatBits = batch.getColor().toFloatBits();
        if (!isOutOfOrder()) {
            Vector2 vector2 = new Vector2();
            if (this.p > 0.0f) {
                vector2.set(this.q).scl(this.p);
                this.p -= f4;
            }
            getWeaponTextures().draw(batch, f + (vector2.x * f5), f2 + (vector2.y * f5), 128.0f * f5, 128.0f * f5, 1.0f, 1.0f, this.angle);
            int i = isAbilityInstalled(0) ? 5 : 4;
            float f6 = this.f / 100.0f;
            for (int i2 = 0; i2 < i; i2++) {
                float f7 = (1.0f / i) * i2;
                if (f6 > f7) {
                    float f8 = (f6 - f7) * i;
                    float f9 = f8;
                    if (f8 > 0.0f) {
                        if (f9 > 1.0f) {
                            f9 = 1.0f;
                        }
                        batch.setColor(1.0f, 1.0f, 1.0f, f9);
                        batch.draw(Game.i.towerManager.F.GAUSS.c, (f + (f3 * 0.5f)) - ((18.0f + vector2.x) * f5), f2 + (f3 * 0.5f) + ((15 + (i2 * 12) + vector2.y) * f5), 18.0f * f5, ((-15) - (i2 * 12)) * f5, 36.0f * f5, 24.0f * f5, 1.0f, 1.0f, this.angle);
                    }
                }
            }
            batch.setPackedColor(floatBits);
        }
    }

    public static double getResourceChargeValue(ResourceType resourceType) {
        return 1.0d + (resourceType.ordinal() * 0.25d);
    }

    @Override // com.prineside.tdi2.Tower
    public final void update(float f) {
        if (!isOutOfOrder()) {
            float stat = getStat(TowerStatType.RESOURCE_CONSUMPTION);
            float stat2 = getStat(TowerStatType.ROTATION_SPEED);
            if (this.f == 0.0f && (this.k == 0.0f || stat == 0.0f || this.g)) {
                if (this.angle != this.h) {
                    rotateToAngle(this.h, f, stat2);
                }
                this.g = this.angle != this.h;
            } else {
                this.g = false;
            }
            if (!this.g && this.k < stat) {
                double[] dArr = {stat - this.k};
                this.S.map.traverseNeighborTilesAroundTile(getTile(), tile -> {
                    if (tile.type == TileType.SOURCE && ((SourceTile) tile).miner != null) {
                        Miner miner = ((SourceTile) tile).miner;
                        for (ResourceType resourceType : ResourceType.values) {
                            int i = miner.minedResources[resourceType.ordinal()];
                            if (i > 0) {
                                double resourceChargeValue = getResourceChargeValue(resourceType);
                                int ceil = MathUtils.ceil((float) (dArr[0] / resourceChargeValue));
                                if (i < ceil) {
                                    ceil = i;
                                }
                                this.S.miner.removeResources(miner, resourceType, ceil);
                                this.S.gameState.addLootIssuedPrizes(new ItemStack(Item.D.F_RESOURCE.create(resourceType), ceil), 0.0f, 0.0f, -1);
                                this.k = (float) (this.k + (ceil * resourceChargeValue));
                                dArr[0] = dArr[0] - (ceil * resourceChargeValue);
                                if (this.S._particle != null && Game.i.settingsManager.isParticlesDrawing()) {
                                    int i2 = ceil;
                                    int i3 = i2;
                                    if (i2 > 3) {
                                        i3 = 3;
                                    }
                                    for (int i4 = 0; i4 < i3; i4++) {
                                        this.S._particle.addOrbParticle(Game.i.towerManager.F.GAUSS.f[resourceType.ordinal()], 18.0f, miner.getTile().getX(), miner.getTile().getY(), getTile().getX(), getTile().getY());
                                    }
                                }
                                if (dArr[0] <= 0.0d) {
                                    return false;
                                }
                            }
                        }
                        return true;
                    }
                    return true;
                });
            }
            if (!this.g) {
                if (this.k >= stat) {
                    this.f += getStat(TowerStatType.CHARGING_SPEED) * f;
                    if (this.f >= 100.0f) {
                        this.f = 100.0f;
                    }
                } else {
                    this.f = 0.0f;
                }
            }
            if (this.f == 100.0f) {
                if (!this.g) {
                    if (this.m || this.angle != this.h) {
                        this.l += f;
                        if (this.l >= this.S.gameValue.getFloatValue(GameValueType.TOWER_GAUSS_SHOT_DELAY) && !this.attackDisabled) {
                            attack(1);
                            this.f1777b = true;
                        }
                    } else {
                        this.n += f;
                        if (this.n > 0.08f) {
                            Vector2 add = new Vector2(0.0f, 1.0f).rotateDeg(this.angle).scl(7680.0f).add(getTile().center);
                            this.S.map.rayCastEnemies(getTile().center.x, getTile().center.y, add.x, add.y, 128.0f * this.S.gameValue.getFloatValue(GameValueType.TOWER_GAUSS_ENEMY_DETECT_RAY_WIDTH), enemyReference -> {
                                Enemy enemy = enemyReference.enemy;
                                if (enemy != null && canAttackEnemy(enemy)) {
                                    this.m = true;
                                    if (this.S._sound != null && this.S.gameState.getGameSpeed() < 2.1d) {
                                        this.S._sound.playShotSound(StaticSoundType.SHOT_GAUSS_CHARGE, this);
                                        return false;
                                    }
                                    return false;
                                }
                                return true;
                            });
                            this.n = 0.0f;
                        }
                    }
                }
            } else {
                this.m = false;
                this.l = 0.0f;
            }
        }
        super.update(f);
    }

    @Override // com.prineside.tdi2.Tower
    public final void drawBatch(Batch batch, float f) {
        super.drawBatch(batch, f);
    }

    @Override // com.prineside.tdi2.Tower
    public final void drawBatchAdditive(Batch batch, float f) {
        if (this.r == null) {
            this.r = (MultiLine) Game.i.shapeManager.getFactory(ShapeType.MULTI_LINE).obtain();
            this.t = (MultiLine) Game.i.shapeManager.getFactory(ShapeType.MULTI_LINE).obtain();
        }
        h();
        this.r.draw(batch);
        if (this.angle != this.h) {
            this.t.draw(batch);
        }
        this.v.begin();
        for (int i = 0; i < this.v.size; i++) {
            Trail trail = this.v.items[i];
            trail.a(batch, f * this.S.gameState.getGameSpeed());
            if (trail.a()) {
                this.v.removeIndex(i);
                Pools.free(trail);
            }
        }
        this.v.end();
    }

    @Override // com.prineside.tdi2.Tower
    public final boolean hasCustomButton() {
        return true;
    }

    @Override // com.prineside.tdi2.Tower
    public final boolean isCustomButtonNeedMapPoint() {
        return true;
    }

    @Override // com.prineside.tdi2.Tower
    public final void customButtonAction(int i, int i2) {
        this.h = PMath.getAngleBetweenPoints(getTile().center.x, getTile().center.y, i, i2);
    }

    public final float getTargetAngle() {
        return this.h;
    }

    public final void setTargetAngle(float f) {
        this.h = f;
    }

    @Override // com.prineside.tdi2.Tower
    public final void updateCustomButton(ComplexButton complexButton, boolean z) {
        complexButton.setIcon(Game.i.assetManager.getDrawable("icon-crosshair"));
        if (z) {
            complexButton.setBackgroundColors(MaterialColor.GREEN.P800, MaterialColor.GREEN.P900, MaterialColor.GREEN.P700, Color.WHITE);
            complexButton.setText(Game.i.localeManager.i18n.get("tap_on_map"));
        } else {
            complexButton.setBackgroundColors(MaterialColor.LIGHT_BLUE.P800, MaterialColor.LIGHT_BLUE.P900, MaterialColor.LIGHT_BLUE.P700, Color.WHITE);
            complexButton.setText(Game.i.localeManager.i18n.get("rotate_button"));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float i() {
        return this.S.gameValue.getFloatValue(GameValueType.TOWER_GAUSS_A_OVERLOAD_DURATION) * (((getUpgradeLevel() / 10.0f) * 0.7f) + 0.3f);
    }

    @Override // com.prineside.tdi2.Tower
    public final void fillTowerMenu(Group group, ObjectMap<String, Object> objectMap) {
        int i;
        int i2;
        float f;
        float stat = getStat(TowerStatType.RESOURCE_CONSUMPTION);
        int i3 = 1;
        if (isAbilityInstalled(1)) {
            i3 = 32;
        }
        if (this.f == 100.0f) {
            i = (i3 * 31) + 1;
        } else {
            i = (i3 * 31) + 2;
        }
        if (this.k >= stat) {
            i2 = (i * 31) + 1;
        } else {
            i2 = (i * 31) + 2;
        }
        if (objectMap.size == 0 || !objectMap.get("state", -1).equals(Integer.valueOf(i2))) {
            group.clear();
            PieChartActor pieChartActor = new PieChartActor();
            Array<PieChart.ChartEntryConfig> array = new Array<>(PieChart.ChartEntryConfig.class);
            array.add(new PieChart.ChartEntryConfig(this.f == 100.0f ? MaterialColor.LIGHT_GREEN.P500 : MaterialColor.BLUE.P500, 50.0f, 0.0f));
            array.add(new PieChart.ChartEntryConfig(this.f == 100.0f ? MaterialColor.LIGHT_GREEN.P500 : MaterialColor.CYAN.P500, 20.0f, 0.0f));
            array.add(new PieChart.ChartEntryConfig(new Color(0.0f, 0.0f, 0.0f, 0.28f), 30.0f, 0.0f));
            pieChartActor.setPosition(40.0f, 40.0f);
            pieChartActor.setSize(64.0f, 64.0f);
            pieChartActor.setSegmentCount(200);
            pieChartActor.setConfigs(array);
            group.addActor(pieChartActor);
            Actor image = new Image(Game.i.assetManager.getDrawable("circle"));
            image.setColor(new Color(724249599));
            image.setSize(36.0f, 36.0f);
            image.setPosition(54.0f, 54.0f);
            group.addActor(image);
            Image image2 = new Image(Game.i.assetManager.getDrawable("icon-cubes-stacked-flame"));
            image2.setSize(24.0f, 24.0f);
            image2.setPosition(60.0f, 60.0f);
            group.addActor(image2);
            Actor label = new Label("", Game.i.assetManager.getLabelStyle(24));
            label.setColor(MaterialColor.BLUE.P500);
            label.setPosition(120.0f, 78.0f);
            label.setSize(100.0f, 18.0f);
            group.addActor(label);
            Label label2 = new Label("", Game.i.assetManager.getLabelStyle(21));
            label2.setColor(new Color(1.0f, 1.0f, 1.0f, 0.56f));
            label2.setPosition(120.0f, 52.0f);
            label2.setSize(100.0f, 16.0f);
            group.addActor(label2);
            if (this.k < stat) {
                label2.setText("Loading resources");
                image2.setDrawable(Game.i.assetManager.getDrawable("icon-cubes-stacked-flame"));
            } else if (this.f == 100.0f) {
                label2.setText("Waiting for target");
                image2.setDrawable(Game.i.assetManager.getDrawable("icon-crosshair"));
            } else {
                label2.setText("Recharging");
                image2.setDrawable(Game.i.assetManager.getDrawable("icon-battery"));
            }
            if (isAbilityInstalled(1)) {
                PieChartActor pieChartActor2 = new PieChartActor();
                Array<PieChart.ChartEntryConfig> array2 = new Array<>(PieChart.ChartEntryConfig.class);
                array2.add(new PieChart.ChartEntryConfig(MaterialColor.AMBER.P500, 20.0f, 0.0f));
                array2.add(new PieChart.ChartEntryConfig(new Color(0.0f, 0.0f, 0.0f, 0.28f), 30.0f, 0.0f));
                pieChartActor2.setPosition(302.0f, 40.0f);
                pieChartActor2.setSize(64.0f, 64.0f);
                pieChartActor2.setSegmentCount(200);
                pieChartActor2.setConfigs(array2);
                group.addActor(pieChartActor2);
                Actor image3 = new Image(Game.i.assetManager.getDrawable("circle"));
                image3.setColor(new Color(724249599));
                image3.setSize(36.0f, 36.0f);
                image3.setPosition(316.0f, 54.0f);
                group.addActor(image3);
                Label label3 = new Label("XP", Game.i.assetManager.getLabelStyle(21));
                label3.setSize(36.0f, 36.0f);
                label3.setAlignment(1);
                label3.setPosition(316.0f, 54.0f);
                group.addActor(label3);
                Actor label4 = new Label("", Game.i.assetManager.getLabelStyle(24));
                label4.setColor(MaterialColor.RED.P500);
                label4.setPosition(382.0f, 78.0f);
                label4.setSize(100.0f, 18.0f);
                group.addActor(label4);
                Actor label5 = new Label(Game.i.localeManager.i18n.get("bonus_damage"), Game.i.assetManager.getLabelStyle(21));
                label5.setColor(new Color(1.0f, 1.0f, 1.0f, 0.56f));
                label5.setPosition(382.0f, 52.0f);
                label5.setSize(100.0f, 16.0f);
                group.addActor(label5);
                objectMap.put("xpChart", pieChartActor2);
                objectMap.put("xpTitle", label4);
            }
            objectMap.put("state", Integer.valueOf(i2));
            objectMap.put("chargingChart", pieChartActor);
            objectMap.put("chargingTitle", label);
        }
        Label label6 = (Label) objectMap.get("chargingTitle");
        if (this.f == 0.0f) {
            int i4 = (int) this.k;
            int i5 = (int) ((this.k % 1.0f) * 10.0f);
            d.setLength(0);
            d.append(i4).append('.').append(i5).append(" / ").append(stat);
            label6.setText(d);
            label6.setColor(MaterialColor.BLUE.P500);
        } else if (this.f == 100.0f) {
            label6.setText(Game.i.localeManager.i18n.get("tower_status_charged"));
            label6.setColor(MaterialColor.LIGHT_GREEN.P500);
        } else {
            float stat2 = (100.0f - this.f) / getStat(TowerStatType.CHARGING_SPEED);
            d.setLength(0);
            d.append((int) stat2).append('.').append((int) ((stat2 % 1.0f) * 10.0f)).append(Game.i.localeManager.i18n.get("TIME_CHAR_SECOND"));
            label6.setText(d);
            label6.setColor(MaterialColor.CYAN.P500);
        }
        if (stat <= 0.0f) {
            f = 1.0f;
        } else {
            float f2 = this.k / stat;
            f = f2;
            if (f2 > 1.0f) {
                f = 1.0f;
            }
        }
        float f3 = this.f / 100.0f;
        float f4 = f3;
        if (f3 > 1.0f) {
            f4 = 1.0f;
        }
        PieChartActor pieChartActor3 = (PieChartActor) objectMap.get("chargingChart");
        Array<PieChart.ChartEntryConfig> configs = pieChartActor3.getConfigs();
        configs.get(0).setValue(f * 50.0f);
        configs.get(1).setValue(f4 * 50.0f);
        configs.get(2).setValue((100.0f - configs.get(0).getValue()) - configs.get(1).getValue());
        pieChartActor3.setConfigs(configs);
        if (isAbilityInstalled(1)) {
            PieChartActor pieChartActor4 = (PieChartActor) objectMap.get("xpChart");
            Array<PieChart.ChartEntryConfig> configs2 = pieChartActor4.getConfigs();
            configs2.get(0).setValue(this.i / g());
            if (configs2.get(0).getValue() > 1.0f) {
                configs2.get(0).setValue(1.0f);
            }
            configs2.get(1).setValue(1.0f - configs2.get(0).getValue());
            pieChartActor4.setConfigs(configs2);
            Label label7 = (Label) objectMap.get("xpTitle");
            d.setLength(0);
            d.append(MathUtils.round(this.j * this.S.gameValue.getFloatValue(GameValueType.TOWER_GAUSS_A_IMPROVEMENT_DAMAGE))).append("%");
            label7.setText(d);
        }
        super.fillTowerMenu(group, objectMap);
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/towers/GaussTower$OnEnemyDie.class */
    public static final class OnEnemyDie extends SerializableListener<GameSystemProvider> implements Listener<EnemyDie> {
        private OnEnemyDie() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public OnEnemyDie(GameSystemProvider gameSystemProvider) {
            this.f1759a = gameSystemProvider;
        }

        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(EnemyDie enemyDie) {
            Tower tower = enemyDie.getLastDamage().getTower();
            if ((tower instanceof GaussTower) && tower.isAbilityInstalled(4)) {
                ((GaussTower) tower).a(enemyDie.getLastDamage().getEnemy());
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/towers/GaussTower$GaussTowerFactory.class */
    public static class GaussTowerFactory extends Tower.Factory<GaussTower> {
        TextureRegion c;
        TextureRegion d;
        TextureRegion e;
        TextureRegion[] f;
        ParticleEffectPool g;

        public GaussTowerFactory() {
            super("tower-gauss", TowerType.GAUSS);
            this.f = new TextureRegion[ResourceType.values.length];
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public void setup() {
            super.setup();
            this.f1784b[0].descriptionArgs = new String[]{"2", "10"};
            this.f1784b[1].descriptionArgs = new String[]{"", "", "", ""};
            this.f1784b[2].descriptionArgs = new String[]{"50", "5"};
            this.f1784b[3].descriptionArgs = new String[]{"5", "10", "10"};
            this.f1784b[4].descriptionArgs = new String[]{""};
        }

        @Override // com.prineside.tdi2.Tower.Factory
        @Null
        public CharSequence getStatMoreInfo(TowerStatType towerStatType, GameValueProvider gameValueProvider, Tower tower) {
            if (towerStatType == TowerStatType.RESOURCE_CONSUMPTION) {
                GaussTower.d.setLength(0);
                GaussTower.d.append(Game.i.localeManager.i18n.get("tower_stat_more_info_GAUSS_RESOURCE_CONSUMPTION")).append(SequenceUtils.EOL);
                for (ResourceType resourceType : ResourceType.values) {
                    GaussTower.d.append("[#").append(Game.i.resourceManager.getColor(resourceType).toString()).append("]").append(Game.i.resourceManager.getName(resourceType)).append("[]").append(": ").append(StringFormatter.compactNumber(GaussTower.getResourceChargeValue(resourceType), true)).append(SequenceUtils.EOL);
                }
                return GaussTower.d;
            }
            return null;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public void configureSystems(GameSystemProvider gameSystemProvider) {
            gameSystemProvider.events.getListeners(EnemyDie.class).addStateAffecting(new OnEnemyDie(gameSystemProvider));
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public Tower.AbilityConfig[] getAbilityConfigs(GameSystemProvider gameSystemProvider, Tower tower) {
            Tower.AbilityConfig[] abilityConfigs = super.getAbilityConfigs(gameSystemProvider, tower);
            abilityConfigs[0].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_GAUSS_A_NANO_DAMAGE), 2, true).toString();
            abilityConfigs[0].descriptionArgs[1] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_GAUSS_A_NANO_HP), 2, true).toString();
            abilityConfigs[1].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_GAUSS_A_IMPROVEMENT_BURN), 2, true).toString();
            abilityConfigs[1].descriptionArgs[1] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_GAUSS_A_IMPROVEMENT_DAMAGE), 2, true).toString();
            abilityConfigs[1].descriptionArgs[2] = StringFormatter.commaSeparatedNumber(gameSystemProvider.gameValue.getIntValue(GameValueType.TOWER_GAUSS_A_IMPROVEMENT_XP)).toString();
            abilityConfigs[1].descriptionArgs[3] = StringFormatter.commaSeparatedNumber(gameSystemProvider.gameValue.getIntValue(GameValueType.TOWER_GAUSS_A_IMPROVEMENT_XP_PER_LEVEL)).toString();
            abilityConfigs[2].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros((1.0d - gameSystemProvider.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_GAUSS_A_CONDUCTORS_RESOURCE_CONSUMPTION)) * 100.0d, 2, true).toString();
            abilityConfigs[2].descriptionArgs[1] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_GAUSS_A_CONDUCTORS_CHARGING_SPEED) - 100.0f, 2, true).toString();
            abilityConfigs[3].descriptionArgs[0] = StringFormatter.compactNumber(gameSystemProvider.gameValue.getIntValue(GameValueType.TOWER_GAUSS_A_OVERLOAD_SHOTS), false).toString();
            abilityConfigs[3].descriptionArgs[1] = StringFormatter.compactNumberWithPrecisionTrimZeros(((GaussTower) tower).i(), 1, true).toString();
            abilityConfigs[3].descriptionArgs[2] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_GAUSS_A_OVERLOAD_DAMAGE), 2, true).toString();
            abilityConfigs[4].descriptionArgs[0] = StringFormatter.compactNumberWithPrecisionTrimZeros(gameSystemProvider.gameValue.getFloatValue(GameValueType.TOWER_GAUSS_A_ULTIMATE_MINING_TIME), 2, true).toString();
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
            this.c = Game.i.assetManager.getTextureRegion("tower-gauss-weapon-glow");
            this.d = Game.i.assetManager.getTextureRegion("dashed-line");
            this.e = Game.i.assetManager.getTextureRegion("bullet-trace-smoke");
            ParticleEffect particleEffect = new ParticleEffect();
            particleEffect.load(Gdx.files.internal("particles/gauss-shot.prt"), Game.i.assetManager.getTextureRegion("particle-default").getAtlas());
            particleEffect.setEmittersCleanUpBlendFunction(false);
            this.g = Game.i.assetManager.getParticleEffectPool("gauss-shot.prt");
            for (ResourceType resourceType : ResourceType.values) {
                this.f[resourceType.ordinal()] = Game.i.assetManager.getTextureRegion("resource-orb-" + resourceType.name().toLowerCase(Locale.ENGLISH));
            }
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public Color getColor() {
            return MaterialColor.AMBER.P500;
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
                    return 4;
                case AGILITY:
                    return 1;
                default:
                    return 0;
            }
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public String[] getAbilityAliases() {
            return GaussTower.ABILITY_ALIASES;
        }

        @Override // com.prineside.tdi2.Tower.Factory
        public int getBuildHotKey() {
            return 51;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.Tower.Factory
        public GaussTower create() {
            return new GaussTower((byte) 0);
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/towers/GaussTower$Trail.class */
    public static class Trail {

        /* renamed from: a, reason: collision with root package name */
        private float f3202a;

        /* renamed from: b, reason: collision with root package name */
        private float f3203b;
        private float c;
        private TextureRegion g;
        private int i;
        private int j;
        private Vector2 d = new Vector2();
        private Vector2 e = new Vector2();
        private Vector2 f = new Vector2();
        private float[] h = new float[0];
        private float[] k = new float[0];
        private float[] l = new float[0];

        /* JADX INFO: Access modifiers changed from: private */
        public void a(float f, float f2, float f3, float f4) {
            this.d.set(f3, f4).sub(f, f2).nor();
            this.e.set(f3, f4).sub(f, f2);
            this.f.set(this.d).rotate90(-1).scl(4.0f);
            this.f3202a = PMath.getAngleBetweenPoints(f, f2, f3, f4);
            this.c = 0.0f;
            this.g = Game.i.assetManager.getTextureRegion("particle-default-long");
            float cosDeg = MathUtils.cosDeg(this.f3202a);
            float sinDeg = MathUtils.sinDeg(this.f3202a);
            float f5 = (cosDeg * (-4.8f)) - (sinDeg * (-19.2f));
            float f6 = (sinDeg * (-4.8f)) + (cosDeg * (-19.2f));
            float f7 = (cosDeg * (-4.8f)) - (sinDeg * 19.2f);
            float f8 = (sinDeg * (-4.8f)) + (cosDeg * 19.2f);
            float f9 = (cosDeg * 4.8f) - (sinDeg * 19.2f);
            float f10 = (sinDeg * 4.8f) + (cosDeg * 19.2f);
            float f11 = f5 + (f9 - f7);
            float f12 = f10 - (f8 - f6);
            float f13 = f5 + 4.8f;
            float f14 = f7 + 4.8f;
            float f15 = f9 + 4.8f;
            float f16 = f11 + 4.8f;
            float u = this.g.getU();
            float v2 = this.g.getV2();
            float u2 = this.g.getU2();
            float v = this.g.getV();
            float floatBits = Color.ORANGE.toFloatBits();
            float[] fArr = {f13, f6, floatBits, u, v2, f14, f8, floatBits, u, v, f15, f10, floatBits, u2, v, f16, f12, floatBits, u2, v2};
            this.f3203b = PMath.getDistanceBetweenPoints(f, f2, f3, f4);
            this.j = MathUtils.ceil((this.f3203b / 128.0f) * 18.0f);
            this.i = this.j * 20;
            if (this.h.length < this.i) {
                this.h = new float[MathUtils.nextPowerOfTwo(this.i)];
            }
            if (this.k.length < this.j) {
                this.k = new float[MathUtils.nextPowerOfTwo(this.j)];
                this.l = new float[MathUtils.nextPowerOfTwo(this.j)];
            }
            int i = 0;
            for (int i2 = 0; i2 < this.j; i2++) {
                float f17 = FastRandom.getFloat();
                GaussTower.u.set(-12582657).lerp(0.2f, 0.0f, 1.0f, 1.0f, f17);
                float floatBits2 = GaussTower.u.toFloatBits();
                float f18 = (FastRandom.getFloat() * 2.0f) - 1.0f;
                float f19 = this.f.x * f18;
                float f20 = this.f.y * f18;
                float f21 = f + (this.e.x * f17) + f19;
                float f22 = f2 + (this.e.y * f17) + f20;
                int i3 = 0;
                for (int i4 = 0; i4 < 4; i4++) {
                    int i5 = i;
                    int i6 = i + 1;
                    int i7 = i3;
                    int i8 = i3 + 1;
                    this.h[i5] = fArr[i7] + f21;
                    int i9 = i6 + 1;
                    this.h[i6] = fArr[i8] + f22;
                    int i10 = i9 + 1;
                    this.h[i9] = floatBits2;
                    int i11 = i8 + 1 + 1;
                    int i12 = i10 + 1;
                    int i13 = i11 + 1;
                    this.h[i10] = fArr[i11];
                    i = i12 + 1;
                    i3 = i13 + 1;
                    this.h[i12] = fArr[i13];
                }
                this.k[i2] = 8000.0f * ((FastRandom.getFloat() * 0.9f) + 0.1f);
                this.l[i2] = f17 * this.f3203b;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(Batch batch, float f) {
            float f2;
            float f3 = this.c / 0.6f;
            if (f3 < 0.04f) {
                f2 = f3 / 0.04f;
            } else {
                f2 = 1.0f - (f3 - 0.041666668f);
            }
            for (int i = 0; i < this.j; i++) {
                float f4 = this.d.x * f * this.k[i];
                float f5 = this.d.y * f * this.k[i];
                float[] fArr = this.l;
                int i2 = i;
                fArr[i2] = fArr[i2] + (f * this.k[i]);
                float f6 = 0.0f;
                if (this.l[i] < this.f3203b) {
                    GaussTower.u.set(NumberUtils.floatToIntColor(this.h[(i * 20) + 2]));
                    GaussTower.u.f889a = f2;
                    f6 = GaussTower.u.toFloatBits();
                }
                for (int i3 = 0; i3 < 4; i3++) {
                    int i4 = (i * 20) + (i3 * 5);
                    float[] fArr2 = this.h;
                    fArr2[i4] = fArr2[i4] + f4;
                    float[] fArr3 = this.h;
                    int i5 = i4 + 1;
                    fArr3[i5] = fArr3[i5] + f5;
                    this.h[i4 + 2] = f6;
                }
            }
            batch.draw(this.g.getTexture(), this.h, 0, this.i);
            this.c += f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean a() {
            return this.c >= 0.6f;
        }
    }
}
