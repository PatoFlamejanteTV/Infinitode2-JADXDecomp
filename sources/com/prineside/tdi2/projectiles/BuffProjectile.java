package com.prineside.tdi2.projectiles;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Buff;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.EnemyFollowingProjectile;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Projectile;
import com.prineside.tdi2.enums.ProjectileType;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/projectiles/BuffProjectile.class */
public final class BuffProjectile extends EnemyFollowingProjectile {
    public Array<Buff> buffs;

    @NAGS
    private ParticleEffectPool.PooledEffect d;

    /* synthetic */ BuffProjectile(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.EnemyFollowingProjectile, com.prineside.tdi2.Projectile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObject(output, this.buffs);
    }

    @Override // com.prineside.tdi2.EnemyFollowingProjectile, com.prineside.tdi2.Projectile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.buffs = (Array) kryo.readObject(input, Array.class);
    }

    private BuffProjectile() {
        super(ProjectileType.BUFF);
        this.buffs = new Array<>(Buff.class);
    }

    public final void setup(Enemy enemy, Array<Buff> array, Vector2 vector2, float f) {
        this.c = 0.0f;
        if (array != this.buffs) {
            this.buffs.addAll(array);
        }
        if (this.S._particle != null && this.S._projectileTrail.isEnabled()) {
            this.d = this.S.projectile.F.BUFF.f2620b.obtain();
            this.d.setPosition(vector2.x, vector2.y);
            this.S._particle.addParticle(this.d, true);
        }
        super.setup(vector2, enemy, f);
    }

    @Override // com.prineside.tdi2.EnemyFollowingProjectile, com.prineside.tdi2.Projectile, com.badlogic.gdx.utils.Pool.Poolable
    public final void reset() {
        super.reset();
        this.buffs.clear();
    }

    @Override // com.prineside.tdi2.EnemyFollowingProjectile, com.prineside.tdi2.Registrable
    public final void setUnregistered() {
        super.setUnregistered();
        if (this.d != null) {
            this.d.allowCompletion();
            this.d = null;
        }
    }

    @Override // com.prineside.tdi2.Projectile
    public final void hit() {
        super.hit();
        Enemy target = getTarget();
        if (target != null) {
            for (int i = 0; i < this.buffs.size; i++) {
                Buff buff = this.buffs.items[i];
                this.S.buff.getProcessor(buff.getType()).addBuff(target, buff);
            }
        }
        this.buffs.clear();
    }

    @Override // com.prineside.tdi2.EnemyFollowingProjectile, com.prineside.tdi2.Projectile
    public final void draw(Batch batch, float f) {
        batch.draw(this.S.projectile.F.BUFF.f2619a, this.drawPosition.x - 9.0f, this.drawPosition.y - 9.0f, 18.0f, 18.0f);
        if (this.d != null) {
            this.d.setPosition(this.drawPosition.x, this.drawPosition.y);
        }
        super.draw(batch, f);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/projectiles/BuffProjectile$BuffProjectileFactory.class */
    public static class BuffProjectileFactory extends Projectile.Factory<BuffProjectile> {

        /* renamed from: a, reason: collision with root package name */
        TextureRegion f2619a;

        /* renamed from: b, reason: collision with root package name */
        ParticleEffectPool f2620b;

        @Override // com.prineside.tdi2.Projectile.Factory
        protected final /* synthetic */ BuffProjectile a() {
            return b();
        }

        @Override // com.prineside.tdi2.Projectile.Factory
        public void setupAssets() {
            this.f2619a = Game.i.assetManager.getTextureRegion("projectile-buff");
            this.f2620b = Game.i.assetManager.getParticleEffectPool("buff-projectile.prt");
        }

        private static BuffProjectile b() {
            return new BuffProjectile((byte) 0);
        }
    }
}
