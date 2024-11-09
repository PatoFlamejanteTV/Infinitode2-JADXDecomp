package com.prineside.tdi2.explosions;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Explosion;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.ExplosionType;
import com.prineside.tdi2.enums.LimitedParticleType;
import com.prineside.tdi2.projectiles.SplinterProjectile;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/explosions/GenericExplosion.class */
public final class GenericExplosion extends Explosion {
    private int d;
    private float e;
    private float f;

    @NAGS
    private final Color g;

    @NAGS
    private Color h;

    /* synthetic */ GenericExplosion(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Explosion, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeVarInt(this.d, true);
        output.writeFloat(this.e);
        output.writeFloat(this.f);
    }

    @Override // com.prineside.tdi2.Explosion, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.d = input.readVarInt(true);
        this.e = input.readFloat();
        this.f = input.readFloat();
    }

    @Override // com.prineside.tdi2.Explosion
    public final GenericExplosion cpy() {
        GenericExplosion obtain = this.S.explosion.F.GENERIC.obtain();
        obtain.setup(getTower(), this.position.x, this.position.y, this.damage, this.f1701a, this.d, this.e, this.f, this.g, this.h);
        return obtain;
    }

    private GenericExplosion() {
        super(ExplosionType.GENERIC);
        this.g = new Color(MaterialColor.RED.P400);
        this.h = null;
    }

    public final void setup(Tower tower, float f, float f2, float f3, float f4, int i, float f5, float f6, Color color, Color color2) {
        super.a(tower, f, f2, f3, f4, 0.2f + (f4 * 0.08f), null);
        this.d = i;
        this.h = color2;
        this.e = f5;
        this.f = f6;
        if (color == null) {
            this.g.set(MaterialColor.RED.P400);
        } else {
            this.g.set(color);
        }
    }

    @Override // com.prineside.tdi2.Explosion
    public final void explode() {
        super.explode();
        if (this.f1702b > 0.0f) {
            addExplosionParticle(this.g, LimitedParticleType.EXPLOSION_CANNON);
            if (this.S._sound != null) {
                this.S._sound.playExplosionSound(this.position.x);
            }
        }
    }

    @Override // com.prineside.tdi2.Explosion
    public final void enemyAffected(Enemy enemy, float f, float f2) {
        this.S.damage.queueDamage(this.S.damage.obtainRecord().setup(enemy, calculateDamage(this.damage, f, f2), DamageType.EXPLOSION).setTower(getTower()).setAbility(this.fromAbility).setExplosion(this));
    }

    @Override // com.prineside.tdi2.Explosion
    public final boolean isDone() {
        return super.isDone() && this.d == 0;
    }

    @Override // com.prineside.tdi2.Explosion
    public final void update(float f) {
        Tower tower;
        super.update(f);
        if (this.d != 0 && (tower = getTower()) != null && tower.isRegistered()) {
            SplinterProjectile obtain = this.S.projectile.F.SPLINTER.obtain();
            this.S.projectile.register(obtain);
            float randomFloat = this.S.gameState.randomFloat() * 6.2831855f;
            Vector2 vector2 = new Vector2();
            Vector2 vector22 = new Vector2();
            Vector2 vector23 = new Vector2();
            vector23.set(PMath.cos(randomFloat), PMath.sin(randomFloat));
            vector23.scl(32.0f);
            vector2.set(vector23).add(this.position.x, this.position.y);
            vector23.scl(((this.f * 128.0f) + 32.0f) / 32.0f);
            vector22.set(vector23).add(this.position.x, this.position.y);
            obtain.setup(tower, this.e, vector2, vector22, 2.0f, this.h);
            this.d--;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/explosions/GenericExplosion$GenericExplosionFactory.class */
    public static final class GenericExplosionFactory extends Explosion.Factory<GenericExplosion> {
        @Override // com.prineside.tdi2.Explosion.Factory
        protected final /* synthetic */ GenericExplosion a() {
            return b();
        }

        private static GenericExplosion b() {
            return new GenericExplosion((byte) 0);
        }
    }
}
