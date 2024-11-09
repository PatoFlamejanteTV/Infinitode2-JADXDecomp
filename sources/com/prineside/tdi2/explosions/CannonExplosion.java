package com.prineside.tdi2.explosions;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.DamageRecord;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Explosion;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.buffs.ThrowBackBuff;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.ExplosionType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.LimitedParticleType;
import com.prineside.tdi2.projectiles.SplinterProjectile;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/explosions/CannonExplosion.class */
public final class CannonExplosion extends Explosion {
    private static final Color d = MaterialColor.RED.P400;
    private int e;
    private float f;
    private float g;
    public float throwBackDistance;

    /* synthetic */ CannonExplosion(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Explosion, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeVarInt(this.e, true);
        output.writeFloat(this.f);
        output.writeFloat(this.g);
        output.writeFloat(this.throwBackDistance);
    }

    @Override // com.prineside.tdi2.Explosion, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.e = input.readVarInt(true);
        this.f = input.readFloat();
        this.g = input.readFloat();
        this.throwBackDistance = input.readFloat();
    }

    private CannonExplosion() {
        super(ExplosionType.CANNON);
    }

    public final void setup(Tower tower, float f, float f2, float f3, float f4, int i, float f5, float f6) {
        super.a(tower, f, f2, f3, f4, 0.25f + (f4 * 0.08f), null);
        this.e = i;
        this.f = f5;
        this.g = f6;
    }

    @Override // com.prineside.tdi2.Explosion
    public final void explode() {
        super.explode();
        addExplosionParticle(d, LimitedParticleType.EXPLOSION_CANNON);
        if (this.S._sound != null) {
            this.S._sound.playExplosionSound(this.position.x);
        }
    }

    @Override // com.prineside.tdi2.Explosion
    public final void enemyAffected(Enemy enemy, float f, float f2) {
        Tower tower = getTower();
        if (tower != null && tower.isRegistered() && tower.canAttackEnemy(enemy)) {
            DamageRecord ability = this.S.damage.obtainRecord().setup(enemy, 1.0f, DamageType.EXPLOSION).setTower(tower).setExplosion(this).setAbility(this.fromAbility);
            if (tower.isAbilityInstalled(3) && enemy.getHealth() / enemy.maxHealth < this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_CANNON_A_PRESSURE_HEALTH)) {
                ability.setDamage(calculateDamage(this.damage * (((float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_CANNON_A_PRESSURE_DAMAGE)) + 1.0f), f, f2));
            } else {
                ability.setDamage(calculateDamage(this.damage, f, f2));
            }
            this.S.damage.queueDamage(ability);
            if (this.throwBackDistance != 0.0f) {
                ThrowBackBuff throwBackBuff = new ThrowBackBuff();
                throwBackBuff.setup(tower.id, this.throwBackDistance, 0.5f, 10.0f);
                this.S.buff.P_THROW_BACK.addBuff(enemy, throwBackBuff);
            }
        }
    }

    @Override // com.prineside.tdi2.Explosion
    public final boolean isDone() {
        return super.isDone() && this.e == 0;
    }

    @Override // com.prineside.tdi2.Explosion
    public final void update(float f) {
        Tower tower;
        super.update(f);
        if (this.e > 0 && (tower = getTower()) != null && tower.isRegistered()) {
            SplinterProjectile obtain = this.S.projectile.F.SPLINTER.obtain();
            this.S.projectile.register(obtain);
            float randomFloat = this.S.gameState.randomFloat() * 6.2831855f;
            Vector2 vector2 = new Vector2();
            Vector2 vector22 = new Vector2();
            Vector2 vector23 = new Vector2();
            vector23.set(PMath.cos(randomFloat), PMath.sin(randomFloat));
            vector23.scl(32.0f);
            vector2.set(vector23).add(this.position);
            vector23.scl((this.g + 32.0f) / 32.0f);
            vector22.set(vector23).add(this.position);
            obtain.setup(tower, this.damage * this.f, vector2, vector22, 2.0f, null);
            this.e--;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/explosions/CannonExplosion$CannonExplosionFactory.class */
    public static final class CannonExplosionFactory extends Explosion.Factory<CannonExplosion> {
        @Override // com.prineside.tdi2.Explosion.Factory
        protected final /* synthetic */ CannonExplosion a() {
            return b();
        }

        private static CannonExplosion b() {
            return new CannonExplosion((byte) 0);
        }
    }
}
