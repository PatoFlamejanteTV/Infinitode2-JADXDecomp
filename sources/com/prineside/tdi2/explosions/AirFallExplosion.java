package com.prineside.tdi2.explosions;

import com.badlogic.gdx.graphics.Color;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Explosion;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.ExplosionType;
import com.prineside.tdi2.enums.LimitedParticleType;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/explosions/AirFallExplosion.class */
public final class AirFallExplosion extends Explosion {
    private static final Color d = MaterialColor.LIGHT_BLUE.P400;

    /* synthetic */ AirFallExplosion(byte b2) {
        this();
    }

    private AirFallExplosion() {
        super(ExplosionType.AIR_FALL);
    }

    public final void setup(Tower tower, float f, float f2, float f3, float f4) {
        super.a(tower, f, f2, f3, f4, 0.25f + (f4 * 0.08f), null);
    }

    @Override // com.prineside.tdi2.Explosion
    public final void explode() {
        super.explode();
        addExplosionParticle(d, LimitedParticleType.EXPLOSION_AIR_FALL);
        if (this.S._sound != null) {
            this.S._sound.playExplosionSound(this.position.x);
        }
    }

    @Override // com.prineside.tdi2.Explosion
    public final void enemyAffected(Enemy enemy, float f, float f2) {
        this.S.damage.queueDamage(this.S.damage.obtainRecord().setup(enemy, calculateDamage(this.damage, f, f2), DamageType.EXPLOSION).setTower(getTower()).setAbility(this.fromAbility).setIgnoreTowerEfficiency(true).setCleanForDps(false).setExplosion(this));
    }

    @Override // com.prineside.tdi2.Explosion
    public final boolean isDone() {
        return super.isDone();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/explosions/AirFallExplosion$AirFallExplosionFactory.class */
    public static class AirFallExplosionFactory extends Explosion.Factory<AirFallExplosion> {
        @Override // com.prineside.tdi2.Explosion.Factory
        protected final /* synthetic */ AirFallExplosion a() {
            return b();
        }

        private static AirFallExplosion b() {
            return new AirFallExplosion((byte) 0);
        }
    }
}
