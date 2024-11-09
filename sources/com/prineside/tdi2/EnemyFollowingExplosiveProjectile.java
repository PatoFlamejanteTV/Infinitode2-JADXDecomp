package com.prineside.tdi2;

import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.enums.ProjectileType;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/EnemyFollowingExplosiveProjectile.class */
public abstract class EnemyFollowingExplosiveProjectile extends EnemyFollowingProjectile {
    private Explosion d;

    @Override // com.prineside.tdi2.EnemyFollowingProjectile, com.prineside.tdi2.Projectile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeClassAndObject(output, this.d);
    }

    @Override // com.prineside.tdi2.EnemyFollowingProjectile, com.prineside.tdi2.Projectile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.d = (Explosion) kryo.readClassAndObject(input);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public EnemyFollowingExplosiveProjectile(ProjectileType projectileType) {
        super(projectileType);
    }

    @Override // com.prineside.tdi2.EnemyFollowingProjectile, com.prineside.tdi2.Projectile, com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
        super.reset();
        this.d = null;
    }

    @Override // com.prineside.tdi2.EnemyFollowingProjectile, com.prineside.tdi2.Registrable
    public void setUnregistered() {
        this.d = null;
        super.setUnregistered();
    }

    @Override // com.prineside.tdi2.Projectile
    public void multiplyDamage(float f) {
        super.multiplyDamage(f);
        this.d.multiplyDamage(f);
    }

    @Override // com.prineside.tdi2.EnemyFollowingProjectile
    @Deprecated
    public void setup(Vector2 vector2, Enemy enemy, float f) {
        throw new RuntimeException("Use method with Explosion");
    }

    @Override // com.prineside.tdi2.EnemyFollowingProjectile
    @Deprecated
    public void setup(Vector2 vector2, Enemy enemy, float f, float f2, float f3, float f4) {
        throw new RuntimeException("Use method with Explosion");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(Vector2 vector2, Enemy enemy, float f, float f2, Explosion explosion, float f3, float f4) {
        super.setup(vector2, enemy, f, f2, f3, f4);
        this.d = explosion;
    }

    @Override // com.prineside.tdi2.Projectile
    public void hit() {
        super.hit();
        this.d.position.set(getPosition());
        this.S.explosion.register(this.d);
        this.d.explode();
    }
}
