package com.prineside.tdi2.explosions;

import com.badlogic.gdx.graphics.Color;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Explosion;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.ExplosionType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.LimitedParticleType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/explosions/MissileExplosion.class */
public final class MissileExplosion extends Explosion {
    private static final Color d = MaterialColor.RED.P400;

    /* synthetic */ MissileExplosion(byte b2) {
        this();
    }

    private MissileExplosion() {
        super(ExplosionType.MISSILE);
    }

    public final void setup(Tower tower, float f, float f2, float f3, float f4) {
        super.a(tower, f, f2, f3, f4, 0.25f + (f4 * 0.08f), null);
    }

    @Override // com.prineside.tdi2.Explosion
    public final void explode() {
        super.explode();
        addExplosionParticle(d, LimitedParticleType.EXPLOSION_MISSILE);
        if (this.S._sound != null) {
            this.S._sound.playExplosionSound(this.position.x);
        }
    }

    @Override // com.prineside.tdi2.Explosion
    public final void enemyAffected(Enemy enemy, float f, float f2) {
        Tower tower = getTower();
        if (tower != null && tower.isRegistered() && tower.canAttackEnemy(enemy)) {
            float f3 = this.damage;
            if (tower.type == TowerType.MISSILE && tower.isAbilityInstalled(3) && enemy.getHealth() / enemy.maxHealth > this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_MISSILE_A_OVERWEIGHT_HP)) {
                double percentValueAsMultiplier = this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_MISSILE_A_OVERWEIGHT_DAMAGE);
                if (tower.isAbilityInstalled(4)) {
                    percentValueAsMultiplier += this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_MISSILE_A_ULTIMATE_DAMAGE);
                }
                f3 = (float) (f3 * (percentValueAsMultiplier + 1.0d));
            }
            this.S.damage.queueDamage(this.S.damage.obtainRecord().setup(enemy, calculateDamage(f3, f, f2), DamageType.EXPLOSION).setTower(tower).setExplosion(this).setAbility(this.fromAbility));
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/explosions/MissileExplosion$MissileExplosionFactory.class */
    public static class MissileExplosionFactory extends Explosion.Factory<MissileExplosion> {
        @Override // com.prineside.tdi2.Explosion.Factory
        protected final /* synthetic */ MissileExplosion a() {
            return b();
        }

        private static MissileExplosion b() {
            return new MissileExplosion((byte) 0);
        }
    }
}
