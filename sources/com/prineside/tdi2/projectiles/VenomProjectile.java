package com.prineside.tdi2.projectiles;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.EnemyFollowingProjectile;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Projectile;
import com.prineside.tdi2.buffs.ChainReactionBuff;
import com.prineside.tdi2.buffs.PoisonBuff;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ProjectileType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.towers.VenomTower;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/projectiles/VenomProjectile.class */
public final class VenomProjectile extends EnemyFollowingProjectile {

    @NAGS
    private ParticleEffectPool.PooledEffect d;
    private VenomTower e;
    private PoisonBuff f;

    /* synthetic */ VenomProjectile(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.EnemyFollowingProjectile, com.prineside.tdi2.Projectile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObjectOrNull(output, this.e, VenomTower.class);
        kryo.writeObjectOrNull(output, this.f, PoisonBuff.class);
    }

    @Override // com.prineside.tdi2.EnemyFollowingProjectile, com.prineside.tdi2.Projectile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.e = (VenomTower) kryo.readObjectOrNull(input, VenomTower.class);
        this.f = (PoisonBuff) kryo.readObjectOrNull(input, PoisonBuff.class);
    }

    private VenomProjectile() {
        super(ProjectileType.VENOM);
    }

    public final void setup(VenomTower venomTower, Enemy enemy, PoisonBuff poisonBuff, Vector2 vector2, float f) {
        super.setup(vector2, enemy, f);
        this.e = venomTower;
        this.f = poisonBuff;
        if (this.S._particle != null && this.S._projectileTrail != null && this.S._projectileTrail.isEnabled()) {
            this.d = this.S.projectile.F.VENOM.f2635b.obtain();
            this.d.setPosition(vector2.x, vector2.y);
            this.S._particle.addParticle(this.d, true);
        }
    }

    @Override // com.prineside.tdi2.Projectile
    public final void multiplyDamage(float f) {
        super.multiplyDamage(f);
        this.f.hitDamage *= f;
        this.f.poisonDamage *= f;
    }

    @Override // com.prineside.tdi2.EnemyFollowingProjectile, com.prineside.tdi2.Projectile, com.badlogic.gdx.utils.Pool.Poolable
    public final void reset() {
        super.reset();
        this.f = null;
        this.e = null;
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
            this.S.buff.P_POISON.addBuff(target, this.f);
            if (this.e != null && this.e.isRegistered() && this.e.type == TowerType.VENOM && this.e.isAbilityInstalled(4)) {
                VenomTower venomTower = this.e;
                if (!target.hasBuffsByType(BuffType.CHAIN_REACTION)) {
                    ChainReactionBuff chainReactionBuff = new ChainReactionBuff();
                    float floatValue = this.S.gameValue.getFloatValue(GameValueType.TOWER_VENOM_A_CHAIN_DURATION);
                    chainReactionBuff.setup(floatValue, floatValue * 10.0f, venomTower.getUltimateChance(), this.S.gameValue.getFloatValue(GameValueType.TOWER_VENOM_A_CHAIN_RANGE), 1.0f + ((float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_VENOM_A_CHAIN_PROLONG)));
                    this.S.buff.P_CHAIN_REACTION.addBuff(target, chainReactionBuff);
                }
            }
            this.S.damage.queueDamage(this.S.damage.obtainRecord().setup(target, this.f.hitDamage, DamageType.POISON).setTower(this.f.tower).setProjectile(this));
            if (this.f.tower != null && this.f.tower.getTarget() == target) {
                this.f.tower.setTarget(null);
            }
        }
    }

    @Override // com.prineside.tdi2.EnemyFollowingProjectile, com.prineside.tdi2.Projectile
    public final void draw(Batch batch, float f) {
        batch.draw(this.S.projectile.F.VENOM.f2634a, this.drawPosition.x - 9.0f, this.drawPosition.y - 9.0f, 9.0f, 9.0f, 18.0f, 18.0f, 1.0f, 1.0f, this.drawAngle);
        if (this.d != null) {
            this.d.setPosition(this.drawPosition.x, this.drawPosition.y);
        }
        super.draw(batch, f);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/projectiles/VenomProjectile$VenomProjectileFactory.class */
    public static class VenomProjectileFactory extends Projectile.Factory<VenomProjectile> {

        /* renamed from: a, reason: collision with root package name */
        TextureRegion f2634a;

        /* renamed from: b, reason: collision with root package name */
        ParticleEffectPool f2635b;

        @Override // com.prineside.tdi2.Projectile.Factory
        protected final /* synthetic */ VenomProjectile a() {
            return b();
        }

        @Override // com.prineside.tdi2.Projectile.Factory
        public void setupAssets() {
            this.f2634a = Game.i.assetManager.getTextureRegion("projectile-venom");
            Game.i.assetManager.getTextureRegion("bullet-trace-thin");
            this.f2635b = Game.i.assetManager.getParticleEffectPool("venom-projectile.prt");
        }

        private static VenomProjectile b() {
            return new VenomProjectile((byte) 0);
        }
    }
}
