package com.prineside.tdi2;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pool;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.ExplosionType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.LimitedParticleType;
import com.prineside.tdi2.systems.ExplosionSystem;
import com.prineside.tdi2.systems.MapRenderingSystem;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.REGS;
import java.util.Arrays;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/Explosion.class */
public abstract class Explosion extends Registrable implements Pool.Poolable {
    public static final float EXPLOSION_RANGE_ENEMY_SEARCH_SPACING = 32.0f;
    public static final int RAY_COUNT = 20;

    @Null
    private Tower d;
    public ExplosionType type;
    public float damage;

    /* renamed from: a, reason: collision with root package name */
    protected float f1701a;

    /* renamed from: b, reason: collision with root package name */
    protected float f1702b;
    protected float c;
    public Ability fromAbility;
    public float piercingMultiplier;
    public Vector2 position = new Vector2();
    private boolean e = false;
    private float f = 0.0f;
    private float[] g = new float[20];
    private DelayedRemovalArray<Enemy.EnemyReference> h = new DelayedRemovalArray<>(false, 8, Enemy.EnemyReference.class);

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeClassAndObject(output, this.d);
        kryo.writeObjectOrNull(output, this.type, ExplosionType.class);
        kryo.writeObject(output, this.position);
        output.writeFloat(this.damage);
        output.writeFloat(this.f1701a);
        output.writeFloat(this.f1702b);
        output.writeFloat(this.c);
        kryo.writeClassAndObject(output, this.fromAbility);
        output.writeBoolean(this.e);
        output.writeFloat(this.f);
        output.writeFloat(this.piercingMultiplier);
        kryo.writeObject(output, this.g);
        kryo.writeObject(output, this.h);
    }

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.d = (Tower) kryo.readClassAndObject(input);
        this.type = (ExplosionType) kryo.readObjectOrNull(input, ExplosionType.class);
        this.position = (Vector2) kryo.readObject(input, Vector2.class);
        this.damage = input.readFloat();
        this.f1701a = input.readFloat();
        this.f1702b = input.readFloat();
        this.c = input.readFloat();
        this.fromAbility = (Ability) kryo.readClassAndObject(input);
        this.e = input.readBoolean();
        this.f = input.readFloat();
        this.piercingMultiplier = input.readFloat();
        this.g = (float[]) kryo.readObject(input, float[].class);
        this.h = (DelayedRemovalArray) kryo.readObject(input, DelayedRemovalArray.class);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Explosion(ExplosionType explosionType) {
        this.type = explosionType;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(@Null Tower tower, float f, float f2, float f3, float f4, float f5, Ability ability) {
        this.d = tower;
        this.position.set(f, f2);
        this.damage = f3;
        this.f1701a = f4;
        this.f1702b = f4 * 128.0f;
        this.c = f5;
        this.fromAbility = ability;
        this.piercingMultiplier = 1.0f;
        Arrays.fill(this.g, 1.0f);
    }

    @Null
    public Tower getTower() {
        return this.d;
    }

    public Explosion cpy() {
        return null;
    }

    @Override // com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
        this.d = null;
        this.f = 0.0f;
        this.e = false;
        this.h.clear();
        this.fromAbility = null;
    }

    public void multiplyDamage(float f) {
        this.damage *= f;
    }

    public void explode() {
        if (this.e) {
            throw new IllegalStateException("Explosion is already triggered");
        }
        this.e = true;
        if (this.f1702b <= 0.0f) {
            this.h.clear();
        } else {
            this.S.map.getEnemiesInCircle(this.position.x, this.position.y, this.f1702b + 32.0f, (enemyReference, f, f2, f3) -> {
                this.h.add(enemyReference);
                return true;
            });
        }
    }

    public static float calculateDamage(float f, float f2, float f3) {
        return f * (0.2f + (f2 * 0.8f)) * f3;
    }

    public static int getRayIndex(Vector2 vector2, Vector2 vector22) {
        return MathUtils.round(PMath.normalizeAngle(PMath.getAngleBetweenPoints(vector2, vector22)) / 18.947369f);
    }

    public void update(float f) {
        if (this.e) {
            this.f += f;
            if (this.f > this.c) {
                this.f = this.c;
            }
            if (this.f1702b > 0.0f) {
                float f2 = this.f / this.c;
                float f3 = f2 * this.f1702b * f2 * this.f1702b;
                float percentValueAsMultiplier = ((float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.EXPLOSIONS_PIERCING)) * this.piercingMultiplier;
                float f4 = percentValueAsMultiplier;
                if (percentValueAsMultiplier > 0.999f) {
                    f4 = 0.999f;
                }
                if (f4 < 0.05f) {
                    f4 = 0.05f;
                }
                float f5 = 1.0f - ((1.0f - f4) * 0.5f);
                this.h.begin();
                int i = this.h.size;
                for (int i2 = 0; i2 < i; i2++) {
                    Enemy enemy = this.h.items[i2].enemy;
                    if (enemy != null && this.position.dst2(enemy.getPosition()) < f3) {
                        int rayIndex = getRayIndex(this.position, enemy.getPosition());
                        this.h.removeIndex(i2);
                        if (this.g[rayIndex] > 0.05f) {
                            enemyAffected(enemy, 1.0f - f2, this.g[rayIndex]);
                        }
                        float[] fArr = this.g;
                        fArr[rayIndex] = fArr[rayIndex] * f4;
                        float[] fArr2 = this.g;
                        int i3 = (rayIndex + 1) % 20;
                        fArr2[i3] = fArr2[i3] * f5;
                        int i4 = rayIndex - 1;
                        int i5 = i4;
                        if (i4 == -1) {
                            i5 = 19;
                        }
                        float[] fArr3 = this.g;
                        int i6 = i5;
                        fArr3[i6] = fArr3[i6] * f5;
                    }
                }
                this.h.end();
            }
        }
    }

    public void addExplosionParticle(Color color, LimitedParticleType limitedParticleType) {
        if (this.S._particle != null && Game.i.settingsManager.isExplosionsDrawing()) {
            boolean z = this.S._mapRendering.getDrawMode() == MapRenderingSystem.DrawMode.DETAILED || !Game.i.settingsManager.isParticlesDrawing();
            ParticleEffectPool.PooledEffect obtain = Game.i.assetManager.getParticleEffectPool("explosion.prt").obtain();
            Array<ParticleEmitter> emitters = obtain.getEmitters();
            float f = (this.f1702b * 2.0f) / 128.0f;
            float f2 = 16.0f + (8.0f * f);
            float f3 = f * 75.0f;
            float f4 = f * 300.0f;
            ParticleEmitter particleEmitter = emitters.get(1);
            ParticleEmitter.GradientColorValue tint = particleEmitter.getTint();
            float[] colors = tint.getColors();
            colors[0] = color.r;
            colors[1] = color.g;
            colors[2] = color.f888b;
            tint.setColors(colors);
            particleEmitter.getXScale().setHigh(f2);
            particleEmitter.getYScale().setHigh(f2);
            particleEmitter.getVelocity().setHigh(f3, f4);
            ParticleEmitter particleEmitter2 = emitters.get(2);
            if (z) {
                particleEmitter2.setMinParticleCount(0);
            } else {
                particleEmitter2.setMinParticleCount(3);
                particleEmitter2.getXScale().setHigh(120.0f * f);
                particleEmitter2.getYScale().setHigh(120.0f * f);
                particleEmitter2.getVelocity().setHigh(15.0f * f, 90.0f * f);
                ParticleEmitter.GradientColorValue tint2 = particleEmitter2.getTint();
                float[] colors2 = tint2.getColors();
                colors2[0] = color.r;
                colors2[1] = color.g;
                colors2[2] = color.f888b;
                tint2.setColors(colors2);
            }
            obtain.setPosition(this.position.x, this.position.y);
            ParticleEmitter particleEmitter3 = obtain.getEmitters().get(0);
            ParticleEmitter.GradientColorValue tint3 = particleEmitter3.getTint();
            float[] colors3 = tint3.getColors();
            colors3[0] = color.r;
            colors3[1] = color.g;
            colors3[2] = color.f888b;
            if (z) {
                colors3[0] = colors3[0] * 0.4f;
                colors3[1] = colors3[1] * 0.4f;
                colors3[2] = colors3[2] * 0.4f;
            }
            tint3.setColors(colors3);
            particleEmitter3.getXScale().setHigh(this.f1702b * 2.0f);
            particleEmitter3.getYScale().setHigh(this.f1702b * 2.0f);
            particleEmitter3.getLife().setHigh(this.c * 1000.0f);
            this.S._particle.addLimitedParticle(obtain, limitedParticleType, this.position.x, this.position.y);
        }
    }

    protected void enemyAffected(Enemy enemy, float f, float f2) {
        this.S.damage.queueDamage(this.S.damage.obtainRecord().setup(enemy, calculateDamage(this.damage, f, f2), DamageType.EXPLOSION).setTower(this.d).setAbility(this.fromAbility));
    }

    public boolean isDone() {
        return this.e && this.f >= this.c;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/Explosion$Factory.class */
    public static abstract class Factory<T extends Explosion> implements Disposable, EntityFactory {
        protected abstract T a();

        public void setup(ExplosionSystem explosionSystem) {
            if (Game.i.assetManager != null) {
                setupAssets();
            }
        }

        public void setupAssets() {
        }

        public final T obtain() {
            return a();
        }

        public final void free(Explosion explosion) {
        }

        @Override // com.badlogic.gdx.utils.Disposable
        public void dispose() {
        }
    }
}
