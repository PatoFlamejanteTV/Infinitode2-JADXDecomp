package com.prineside.tdi2.projectiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Projectile;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.buffs.BonusCoinsBuff;
import com.prineside.tdi2.enums.AchievementType;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ProjectileType;
import com.prineside.tdi2.enums.ShapeType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.shapes.MultiLine;
import com.prineside.tdi2.towers.LaserTower;
import com.prineside.tdi2.utils.FrameAccumulatorForPerformance;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/projectiles/LaserProjectile.class */
public final class LaserProjectile extends Projectile {

    /* renamed from: a, reason: collision with root package name */
    @NAGS
    private MultiLine f2626a;

    /* renamed from: b, reason: collision with root package name */
    @NAGS
    private MultiLine f2627b;

    @NAGS
    private ParticleEffectPool.PooledEffect d;

    @NAGS
    private boolean e;
    private Tower f;
    private float g;
    private float h;
    private float i;
    private float j;
    private float k;
    private float l;
    private float m;
    private float n;
    public int penetrationCount;

    @FrameAccumulatorForPerformance
    private byte o;

    /* synthetic */ LaserProjectile(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Projectile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeClassAndObject(output, this.f);
        output.writeFloat(this.g);
        output.writeFloat(this.h);
        output.writeFloat(this.i);
        output.writeFloat(this.j);
        output.writeVarInt(this.penetrationCount, true);
        output.writeFloat(this.k);
        output.writeFloat(this.l);
        output.writeFloat(this.m);
        output.writeFloat(this.n);
        output.writeByte(this.o);
    }

    @Override // com.prineside.tdi2.Projectile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f = (Tower) kryo.readClassAndObject(input);
        this.g = input.readFloat();
        this.h = input.readFloat();
        this.i = input.readFloat();
        this.j = input.readFloat();
        this.penetrationCount = input.readVarInt(true);
        this.k = input.readFloat();
        this.l = input.readFloat();
        this.m = input.readFloat();
        this.n = input.readFloat();
        this.o = input.readByte();
    }

    private LaserProjectile() {
        super(ProjectileType.LASER);
    }

    public final void setup(Tower tower, float f, float f2, float f3, float f4, float f5, float f6, int i) {
        super.setup();
        this.f = tower;
        this.c = f2;
        this.g = f3;
        this.h = f4;
        this.i = f5;
        this.j = f6;
        this.penetrationCount = i;
        this.k = f;
        this.e = true;
    }

    public final float getStartX() {
        return this.g;
    }

    public final float getStartY() {
        return this.h;
    }

    public final float getEndX() {
        return this.i;
    }

    public final float getEndY() {
        return this.j;
    }

    @Override // com.prineside.tdi2.Registrable
    public final void setUnregistered() {
        super.setUnregistered();
        if (this.f2626a != null) {
            ((MultiLine.MultiLineFactory) Game.i.shapeManager.getFactory(ShapeType.MULTI_LINE)).free(this.f2626a);
            this.f2626a = null;
        }
        if (this.f2627b != null) {
            ((MultiLine.MultiLineFactory) Game.i.shapeManager.getFactory(ShapeType.MULTI_LINE)).free(this.f2627b);
            this.f2627b = null;
        }
        if (this.d != null) {
            this.d.allowCompletion();
            this.d = null;
        }
    }

    @Override // com.prineside.tdi2.Projectile, com.badlogic.gdx.utils.Pool.Poolable
    public final void reset() {
        super.reset();
        this.f = null;
        this.n = 0.0f;
        this.l = 0.0f;
        this.m = 0.0f;
        this.g = 0.0f;
        this.h = 0.0f;
        this.i = 0.0f;
        this.j = 0.0f;
        this.k = 0.0f;
        this.penetrationCount = 0;
        this.e = true;
    }

    public final void stop() {
        this.n = this.k;
    }

    private boolean a() {
        return this.l == 0.0f && this.m == 0.0f;
    }

    public final void handleCollisions(float f) {
        if (this.S == null) {
            return;
        }
        if (this.f != null && !this.f.isRegistered()) {
            this.f = null;
        }
        LaserTower laserTower = (this.f != null && this.f.type == TowerType.LASER && this.f.isAbilityInstalled(4)) ? (LaserTower) this.f : null;
        float ultDamageMultiplier = laserTower == null ? 1.0f : laserTower.getUltDamageMultiplier();
        this.S.achievement.setProgress(AchievementType.DOUBLE_LASER_DAMAGE, (int) ((ultDamageMultiplier - 1.0f) * 100.0f));
        Array<Enemy> enemyArray = this.S.TSH.getEnemyArray();
        this.S.map.rayCastEnemiesSorted(this.g, this.h, this.i, this.j, 0.0f, enemyReference -> {
            if (enemyReference.enemy != null) {
                enemyArray.add(enemyReference.enemy);
                return true;
            }
            return true;
        });
        float f2 = 0.0f;
        float f3 = 0.0f;
        if (enemyArray.size > 0) {
            int i = this.penetrationCount;
            boolean z = false;
            float f4 = 0.0f;
            int i2 = 0;
            while (true) {
                if (i2 >= enemyArray.size) {
                    break;
                }
                Enemy enemy = enemyArray.items[i2];
                if (enemy != null) {
                    Vector2 position = enemy.getPosition();
                    f2 = position.x;
                    f3 = position.y;
                    f4 = enemy.getSquaredSize();
                    if (f != 0.0f) {
                        this.S.damage.queueDamage(this.S.damage.obtainRecord().setup(enemy, this.c * ultDamageMultiplier * f, DamageType.LASER).setTower(this.f).setProjectile(this));
                    }
                    if ((this.f instanceof LaserTower) && this.f.isAbilityInstalled(3)) {
                        BonusCoinsBuff bonusCoinsBuff = new BonusCoinsBuff();
                        float floatValue = this.S.gameValue.getFloatValue(GameValueType.TOWER_LASER_A_IONIZATION_COINS_DURATION);
                        bonusCoinsBuff.setup(floatValue, floatValue * 10.0f, (float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_LASER_A_IONIZATION_COINS), this.f);
                        this.S.buff.P_BONUS_COINS.addBuff(enemy, bonusCoinsBuff);
                    }
                    i--;
                    if (i == 0) {
                        z = true;
                        break;
                    }
                }
                i2++;
            }
            if (z) {
                Vector2 vector2 = new Vector2();
                if (PMath.getLineCircleIntersectionFloats(this.g, this.h, this.i, this.j, f2, f3, f4, vector2)) {
                    this.l = vector2.x;
                    this.m = vector2.y;
                    this.e = true;
                }
            } else {
                if (this.l != this.i) {
                    this.e = true;
                }
                this.l = this.i;
                this.m = this.j;
            }
        } else if (this.i != this.l || this.j != this.m) {
            this.l = this.i;
            this.m = this.j;
            this.e = true;
        }
        this.S.TSH.freeEnemyArray(enemyArray);
    }

    private void b() {
        float floatBits;
        float f;
        if (a()) {
            throw new IllegalStateException("Collision point is not calculated");
        }
        if (this.f2626a == null) {
            this.f2626a = (MultiLine) Game.i.shapeManager.getFactory(ShapeType.MULTI_LINE).obtain();
            this.f2627b = (MultiLine) Game.i.shapeManager.getFactory(ShapeType.MULTI_LINE).obtain();
        }
        if (this.penetrationCount == 1) {
            floatBits = MaterialColor.RED.P500.toFloatBits();
        } else {
            floatBits = MaterialColor.LIGHT_BLUE.P500.toFloatBits();
        }
        this.f2627b.reset();
        this.f2627b.setTextureRegion(this.S.projectile.F.LASER.f2629b, false, false);
        Vector2 vector2 = new Vector2();
        vector2.set(this.i - this.g, this.j - this.h);
        vector2.nor().scl(this.S.projectile.F.LASER.f2629b.getRegionWidth());
        this.f2627b.appendNode(this.g, this.h, 48.0f, floatBits, false);
        this.f2627b.appendNode(this.g + vector2.x, this.h + vector2.y, 48.0f, floatBits, false);
        this.f2627b.updateAllNodes();
        this.f2626a.reset();
        this.f2626a.setTextureRegion(this.S.projectile.F.LASER.f2628a, false, false);
        float f2 = this.g + vector2.x;
        float f3 = this.h + vector2.y;
        this.f2626a.appendNode(f2, f3, 48.0f, floatBits, false);
        for (float distanceBetweenPoints = PMath.getDistanceBetweenPoints(this.g, this.h, this.l, this.m) / this.S.projectile.F.LASER.f2628a.getRegionWidth(); distanceBetweenPoints > 0.0f; distanceBetweenPoints -= 1.0f) {
            if (distanceBetweenPoints > 1.0f) {
                f2 += vector2.x;
                f = f3 + vector2.y;
            } else {
                f2 = this.l;
                f = this.m;
            }
            f3 = f;
            this.f2626a.appendNode(f2, f3, 48.0f, floatBits, false);
        }
        this.f2626a.updateAllNodes();
        if (this.S._particle != null && Game.i.settingsManager.isParticlesDrawing()) {
            if (this.l != 0.0f && this.m != 0.0f) {
                if (this.d == null && !this.S._particle.willParticleBeSkipped()) {
                    this.d = this.S.projectile.F.LASER.c.obtain();
                    float angleBetweenPoints = PMath.getAngleBetweenPoints(this.g, this.h, this.i, this.j) - 90.0f;
                    this.d.getEmitters().first().getAngle().setHigh(angleBetweenPoints - 60.0f, angleBetweenPoints + 60.0f);
                    this.S._particle.addParticle(this.d, true);
                }
                this.d.setPosition(this.l, this.m);
            } else if (this.d != null) {
                this.d.allowCompletion();
                this.d = null;
            }
        }
        this.e = false;
    }

    public final void setStartPos(float f, float f2) {
        this.g = f;
        this.h = f2;
        this.l = 0.0f;
        this.m = 0.0f;
        this.e = true;
    }

    public final void setEndPos(float f, float f2) {
        this.i = f;
        this.j = f2;
        this.l = 0.0f;
        this.m = 0.0f;
        this.e = true;
    }

    public final float getDuration() {
        return this.k;
    }

    public final void setDuration(float f) {
        this.k = f;
    }

    @Override // com.prineside.tdi2.Projectile
    public final boolean isDone() {
        return this.n >= this.k;
    }

    @Override // com.prineside.tdi2.Projectile
    public final boolean hasReachedTarget() {
        return this.n >= this.k;
    }

    @Override // com.prineside.tdi2.Projectile
    public final void applyDrawInterpolation(float f) {
    }

    @Override // com.prineside.tdi2.Projectile
    public final void update(float f) {
        if (isDone()) {
            return;
        }
        this.n += f;
        this.o = (byte) (this.o + 1);
        if (this.o == 2) {
            this.o = (byte) 0;
            handleCollisions(f * 2.0f);
        }
    }

    @Override // com.prineside.tdi2.Projectile
    public final void draw(Batch batch, float f) {
        if (a()) {
            return;
        }
        if (this.e || this.f2626a == null) {
            b();
        }
        float f2 = this.n / 0.15f;
        if (this.k - this.n < 0.15f) {
            f2 = (this.k - this.n) / 0.15f;
        }
        if (f2 > 1.0f) {
            f2 = 1.0f;
        }
        this.f2627b.setTintWithAlpha(Color.WHITE, f2);
        this.f2627b.draw(batch);
        this.f2626a.setTintWithAlpha(Color.WHITE, f2);
        this.f2626a.draw(batch);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/projectiles/LaserProjectile$LaserProjectileFactory.class */
    public static class LaserProjectileFactory extends Projectile.Factory<LaserProjectile> {

        /* renamed from: a, reason: collision with root package name */
        TextureRegion f2628a;

        /* renamed from: b, reason: collision with root package name */
        TextureRegion f2629b;
        ParticleEffectPool c;

        @Override // com.prineside.tdi2.Projectile.Factory
        protected final /* synthetic */ LaserProjectile a() {
            return b();
        }

        @Override // com.prineside.tdi2.Projectile.Factory
        public void setupAssets() {
            this.f2628a = Game.i.assetManager.getTextureRegion("laser");
            this.f2629b = Game.i.assetManager.getTextureRegion("laser-cap");
            this.c = Game.i.assetManager.getParticleEffectPool("sparkles.prt");
        }

        private static LaserProjectile b() {
            return new LaserProjectile((byte) 0);
        }
    }
}
