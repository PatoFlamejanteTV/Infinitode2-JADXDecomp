package com.prineside.tdi2.explosions;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Explosion;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.abilities.FireballAbility;
import com.prineside.tdi2.buffs.BurnBuff;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.ExplosionType;
import com.prineside.tdi2.enums.LimitedParticleType;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/explosions/FireballExplosion.class */
public final class FireballExplosion extends Explosion {
    private static final Color d = MaterialColor.DEEP_ORANGE.P400;
    private float e;
    private float f;

    /* synthetic */ FireballExplosion(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Explosion, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.e);
        output.writeFloat(this.f);
    }

    @Override // com.prineside.tdi2.Explosion, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.e = input.readFloat();
        this.f = input.readFloat();
    }

    private FireballExplosion() {
        super(ExplosionType.FIREBALL);
    }

    public final float getDamage() {
        return this.e;
    }

    public final float getFireDamage() {
        return this.f;
    }

    public final void setup(float f, float f2, float f3, float f4, float f5, FireballAbility fireballAbility) {
        this.e = f3;
        this.f = f4;
        super.a(null, f, f2, 0.0f, f5, 0.25f + (f5 * 0.08f), fireballAbility);
    }

    @Override // com.prineside.tdi2.Explosion
    public final void explode() {
        super.explode();
        if (this.S._particle != null && Game.i.settingsManager.isExplosionsDrawing()) {
            ParticleEffectPool.PooledEffect obtain = Game.i.assetManager.getParticleEffectPool("explosion.prt").obtain();
            Array<ParticleEmitter> emitters = obtain.getEmitters();
            float f = (this.f1702b * 2.0f) / 128.0f;
            ParticleEmitter particleEmitter = emitters.get(1);
            ParticleEmitter.GradientColorValue tint = particleEmitter.getTint();
            float[] colors = tint.getColors();
            colors[0] = d.r;
            colors[1] = d.g;
            colors[2] = d.f888b;
            tint.setColors(colors);
            particleEmitter.getXScale().setHigh(16.0f * f);
            particleEmitter.getYScale().setHigh(16.0f * f);
            particleEmitter.getVelocity().setHigh(f * 50.0f, f * 150.0f);
            ParticleEmitter particleEmitter2 = emitters.get(2);
            particleEmitter2.getXScale().setLow(4.0f * f);
            particleEmitter2.getYScale().setLow(4.0f * f);
            particleEmitter2.getXScale().setHigh(8.0f * f, 32.0f * f);
            particleEmitter2.getYScale().setHigh(8.0f * f, 32.0f * f);
            particleEmitter2.getVelocity().setHigh(20.0f, 70.0f * f);
            obtain.setPosition(this.position.x, this.position.y);
            ParticleEmitter particleEmitter3 = obtain.getEmitters().get(0);
            ParticleEmitter.GradientColorValue tint2 = particleEmitter3.getTint();
            float[] colors2 = tint2.getColors();
            colors2[0] = d.r;
            colors2[1] = d.g;
            colors2[2] = d.f888b;
            tint2.setColors(colors2);
            particleEmitter3.getXScale().setHigh(this.f1702b * 2.0f);
            particleEmitter3.getYScale().setHigh(this.f1702b * 2.0f);
            particleEmitter3.getLife().setHigh(this.c * 1000.0f);
            particleEmitter3.getLife().setLow(this.c * 1500.0f);
            this.S._particle.addLimitedParticle(obtain, LimitedParticleType.EXPLOSION_FIREBALL, this.position.x, this.position.y);
        }
        if (this.S._sound != null) {
            this.S._sound.playExplosionSound(this.position.x);
        }
    }

    @Override // com.prineside.tdi2.Explosion
    public final void enemyAffected(Enemy enemy, float f, float f2) {
        float abilityVulnerability = enemy.getAbilityVulnerability(AbilityType.FIREBALL);
        if (this.f * abilityVulnerability > 0.0f) {
            BurnBuff burnBuff = new BurnBuff();
            burnBuff.setup(null, 15.0f, 150.0f, this.f * abilityVulnerability, this.fromAbility);
            this.S.buff.P_BURN.addBuff(enemy, burnBuff);
        }
        if (this.e * abilityVulnerability > 0.0f) {
            Tower tower = getTower();
            Tower tower2 = tower;
            if (tower != null && !tower2.isRegistered()) {
                tower2 = null;
            }
            this.S.damage.queueDamage(this.S.damage.obtainRecord().setup(enemy, this.e * abilityVulnerability, DamageType.FIRE).setTower(tower2).setExplosion(this).setAbility(this.fromAbility));
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/explosions/FireballExplosion$FireballExplosionFactory.class */
    public static class FireballExplosionFactory extends Explosion.Factory<FireballExplosion> {
        @Override // com.prineside.tdi2.Explosion.Factory
        protected final /* synthetic */ FireballExplosion a() {
            return b();
        }

        @Override // com.prineside.tdi2.Explosion.Factory
        public void setupAssets() {
            Game.i.assetManager.getParticleEffectPool("fireball-explosion.prt");
        }

        private static FireballExplosion b() {
            return new FireballExplosion((byte) 0);
        }
    }
}
