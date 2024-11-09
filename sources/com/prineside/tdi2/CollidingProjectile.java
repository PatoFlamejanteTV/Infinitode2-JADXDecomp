package com.prineside.tdi2;

import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.enums.ProjectileType;
import com.prineside.tdi2.utils.BitVector;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.ObjectFilter;
import com.prineside.tdi2.utils.logging.TLog;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/CollidingProjectile.class */
public abstract class CollidingProjectile extends Projectile {
    public static final float RAYCAST_INTERVAL_MIN = 32.0f;
    public static final float RAYCAST_INTERVAL_MIN_SQR = 1024.0f;
    public float flyTime;
    public float totalFlyTime;

    /* renamed from: a, reason: collision with root package name */
    protected Vector2 f1682a;
    private float d;

    /* renamed from: b, reason: collision with root package name */
    @NAGS
    protected float f1683b;
    private Vector2 e;
    private float f;
    private BitVector g;

    @NAGS
    public Vector2 drawPosition;

    @NAGS
    public float drawAngle;

    @NAGS
    private final RayCastHandler h;

    protected abstract void a(Enemy enemy);

    static {
        TLog.forClass(CollidingProjectile.class);
    }

    @Override // com.prineside.tdi2.Projectile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.flyTime);
        output.writeFloat(this.totalFlyTime);
        kryo.writeObject(output, this.f1682a);
        output.writeFloat(this.d);
        kryo.writeObject(output, this.e);
        output.writeFloat(this.f);
        kryo.writeObject(output, this.g);
    }

    @Override // com.prineside.tdi2.Projectile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.flyTime = input.readFloat();
        this.totalFlyTime = input.readFloat();
        this.f1682a = (Vector2) kryo.readObject(input, Vector2.class);
        this.d = input.readFloat();
        this.e = (Vector2) kryo.readObject(input, Vector2.class);
        this.f = input.readFloat();
        this.g = (BitVector) kryo.readObject(input, BitVector.class);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CollidingProjectile(ProjectileType projectileType) {
        super(projectileType);
        this.flyTime = 0.0f;
        this.f1682a = new Vector2();
        this.f1683b = -741.84f;
        this.e = new Vector2();
        this.g = new BitVector();
        this.drawPosition = new Vector2();
        this.h = new RayCastHandler(this, (byte) 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(Vector2 vector2, float f, Vector2 vector22) {
        float dst = (vector2.dst(vector22) / f) / 128.0f;
        Vector2 vector23 = new Vector2(vector22);
        vector23.sub(vector2).nor().scl(f);
        a(vector2, vector23, dst);
    }

    private void a(Vector2 vector2, Vector2 vector22, float f) {
        this.f = 0.0f;
        this.position.set(vector2);
        this.d = vector22.len() * 128.0f;
        this.f1682a.set(vector22).nor();
        this.totalFlyTime = f;
        this.e.set(vector2);
    }

    @Override // com.prineside.tdi2.Projectile
    public void applyDrawInterpolation(float f) {
        if (f == 0.0f) {
            this.drawPosition.set(this.position);
            this.drawAngle = b();
            return;
        }
        this.drawPosition.set(this.position);
        this.drawPosition.x += this.f1682a.x * this.d * f;
        this.drawPosition.y += this.f1682a.y * this.d * f;
        this.drawAngle = b();
    }

    @Override // com.prineside.tdi2.Projectile
    public void flyOnEnemy(Enemy enemy) {
        this.f1682a.set(enemy.getPosition());
        this.f1682a.sub(this.position).nor();
        this.f1683b = -741.84f;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final float a() {
        float f = this.totalFlyTime - this.flyTime;
        float f2 = f;
        if (f < 0.0f) {
            f2 = 0.0f;
        }
        return f2;
    }

    private float b() {
        if (this.f1683b == -741.84f) {
            this.f1683b = this.f1682a.angleDeg() - 90.0f;
        }
        return this.f1683b;
    }

    @Override // com.prineside.tdi2.Projectile
    public boolean isDone() {
        return this.flyTime >= this.totalFlyTime;
    }

    @Override // com.prineside.tdi2.Projectile
    public boolean hasReachedTarget() {
        return false;
    }

    @Override // com.prineside.tdi2.Projectile
    public void update(float f) {
        if (isDone()) {
            return;
        }
        if (this.flyTime + f >= this.totalFlyTime) {
            float f2 = this.totalFlyTime - this.flyTime;
            this.flyTime = this.totalFlyTime;
            this.f = 64.0f;
            a(f2);
            return;
        }
        this.flyTime += f;
        a(f);
    }

    private void a(float f) {
        this.position.x += this.f1682a.x * this.d * f;
        this.position.y += this.f1682a.y * this.d * f;
        this.f += f * this.d;
        if (this.f > 32.0f) {
            this.f = 0.0f;
            this.S.map.rayCastEnemiesSorted(this.e.x, this.e.y, this.position.x, this.position.y, 1.0f, this.h);
            this.e.set(this.position);
        }
    }

    @Override // com.prineside.tdi2.Projectile, com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
        this.f = 0.0f;
        this.flyTime = 0.0f;
        this.totalFlyTime = 0.0f;
        this.drawAngle = 0.0f;
        this.f1682a.setZero();
        this.f1683b = -741.84f;
        this.e.setZero();
        this.drawPosition.setZero();
        this.g.clear();
        super.reset();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/CollidingProjectile$RayCastHandler.class */
    public final class RayCastHandler implements ObjectFilter<Enemy.EnemyReference> {
        private RayCastHandler() {
        }

        /* synthetic */ RayCastHandler(CollidingProjectile collidingProjectile, byte b2) {
            this();
        }

        @Override // com.prineside.tdi2.utils.ObjectFilter
        public final boolean test(Enemy.EnemyReference enemyReference) {
            if (CollidingProjectile.this.isDone()) {
                return false;
            }
            Enemy enemy = enemyReference.enemy;
            if (enemy != null && !CollidingProjectile.this.g.get(enemy.id)) {
                CollidingProjectile.this.g.set(enemy.id);
                CollidingProjectile.this.a(enemy);
                return true;
            }
            return true;
        }
    }
}
