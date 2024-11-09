package com.prineside.tdi2.projectiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.CollidingProjectile;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Projectile;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.buffs.BonusXpBuff;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ProjectileType;
import com.prineside.tdi2.enums.SpecialDamageType;
import com.prineside.tdi2.enums.TowerStatType;
import com.prineside.tdi2.shapes.TrailMultiLine;
import com.prineside.tdi2.towers.SplashTower;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/projectiles/SplashProjectile.class */
public final class SplashProjectile extends CollidingProjectile {
    private static final Color d = Color.WHITE.cpy();
    private static final Color e = new Color(MaterialColor.DEEP_ORANGE.P500.r, MaterialColor.DEEP_ORANGE.P500.g, MaterialColor.DEEP_ORANGE.P500.f888b, 0.56f);
    public int chainKillGeneration;
    private Tower f;
    public byte hitCount;
    private boolean g;

    @NAGS
    private TrailMultiLine h;

    /* synthetic */ SplashProjectile(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.CollidingProjectile, com.prineside.tdi2.Projectile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeVarInt(this.chainKillGeneration, true);
        kryo.writeClassAndObject(output, this.f);
        output.writeByte(this.hitCount);
        output.writeBoolean(this.g);
    }

    @Override // com.prineside.tdi2.CollidingProjectile, com.prineside.tdi2.Projectile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.chainKillGeneration = input.readVarInt(true);
        this.f = (Tower) kryo.readClassAndObject(input);
        this.hitCount = input.readByte();
        this.g = input.readBoolean();
    }

    private SplashProjectile() {
        super(ProjectileType.SPLASH);
        this.chainKillGeneration = 0;
    }

    public final void setup(Tower tower, float f, Vector2 vector2, Vector2 vector22, float f2) {
        super.a(vector2, f2, vector22);
        this.f = tower;
        this.c = f;
        this.hitCount = (byte) 0;
        this.g = false;
        if (this.S._projectileTrail != null && this.S._projectileTrail.isEnabled()) {
            this.h = Game.i.shapeManager.F.TRAIL_MULTI_LINE.obtain();
            this.h.setup(e, 13.0f, 0.4f, 0.0f);
            this.h.setStartPoint(vector2.x, vector2.y);
            this.S._projectileTrail.addTrail(this.h);
        }
    }

    @Override // com.prineside.tdi2.Registrable
    public final void setUnregistered() {
        super.setUnregistered();
        if (this.h != null) {
            this.h.allowCompletion();
            this.h = null;
        }
    }

    @Override // com.prineside.tdi2.CollidingProjectile, com.prineside.tdi2.Projectile, com.badlogic.gdx.utils.Pool.Poolable
    public final void reset() {
        super.reset();
        this.chainKillGeneration = 0;
        this.f = null;
        this.h = null;
        this.hitCount = (byte) 0;
        this.g = false;
    }

    @Override // com.prineside.tdi2.Projectile
    public final void draw(Batch batch, float f) {
        if (this.h != null) {
            Vector2 vector2 = new Vector2();
            vector2.set(-this.f1682a.x, -this.f1682a.y).scl(8.0f).add(this.drawPosition);
            this.h.setHeadPosition(vector2.x, vector2.y);
        }
        if (a() < 0.2f) {
            d.f889a = a() / 0.2f;
            batch.setColor(d);
        }
        batch.draw(this.S.projectile.F.SPLASH.f2632a, this.drawPosition.x - 5.625f, this.drawPosition.y - 11.25f, 5.625f, 11.25f, 11.25f, 22.5f, 1.0f, 1.0f, this.drawAngle);
        batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
    }

    @Override // com.prineside.tdi2.CollidingProjectile
    protected final void a(Enemy enemy) {
        if (this.f != null && this.f.isRegistered() && this.f.canAttackEnemy(enemy)) {
            float f = this.c;
            if (this.f.isAbilityInstalled(3) && enemy.getBuffedSpeed() < this.S.gameValue.getFloatValue(GameValueType.TOWER_SPLASH_A_RIFFLED_SPEED_MARK)) {
                f = (float) (f * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_SPLASH_A_RIFFLED_DAMAGE));
            }
            if (!enemy.isVulnerableToSpecial(SpecialDamageType.PIERCING)) {
                this.g = true;
            }
            this.S.damage.queueDamage(this.S.damage.obtainRecord().setup(enemy, f, DamageType.BULLET).setTower(this.f).setProjectile(this));
            if ((this.f instanceof SplashTower) && this.S.gameState.randomFloat() < this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_SPLASH_A_ULTIMATE_ON_HIT_CHANCE)) {
                SplashTower.triggerChainReaction(this.S, (SplashTower) this.f, this);
            }
            if (this.f.isAbilityInstalled(2)) {
                float floatValue = this.S.gameValue.getFloatValue(GameValueType.TOWER_SPLASH_A_FAST_BULLETS_BONUS_XP);
                float floatValue2 = this.S.gameValue.getFloatValue(GameValueType.TOWER_SPLASH_A_FAST_BULLETS_BONUS_XP_DURATION);
                BonusXpBuff bonusXpBuff = new BonusXpBuff();
                bonusXpBuff.setup(floatValue2, floatValue2 * 10.0f, floatValue * 0.01f, this.f);
                this.S.buff.P_BONUS_XP.addBuff(enemy, bonusXpBuff);
            }
            this.hitCount = (byte) (this.hitCount + 1);
            this.c *= this.f.getStat(TowerStatType.U_PIERCING) * 0.01f;
        }
    }

    @Override // com.prineside.tdi2.CollidingProjectile, com.prineside.tdi2.Projectile
    public final boolean isDone() {
        return super.isDone() || this.g || this.c < 0.01f || this.hitCount > 10;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/projectiles/SplashProjectile$SplashProjectileFactory.class */
    public static class SplashProjectileFactory extends Projectile.Factory<SplashProjectile> {

        /* renamed from: a, reason: collision with root package name */
        TextureRegion f2632a;

        @Override // com.prineside.tdi2.Projectile.Factory
        protected final /* synthetic */ SplashProjectile a() {
            return b();
        }

        @Override // com.prineside.tdi2.Projectile.Factory
        public void setupAssets() {
            this.f2632a = Game.i.assetManager.getTextureRegion("projectile-splash");
            Game.i.assetManager.getTextureRegion("bullet-trace-thin");
        }

        private static SplashProjectile b() {
            return new SplashProjectile((byte) 0);
        }
    }
}
