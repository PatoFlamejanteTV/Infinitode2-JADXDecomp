package com.prineside.tdi2.projectiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.EnemyFollowingProjectile;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Projectile;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.ProjectileType;
import com.prineside.tdi2.shapes.TrailMultiLine;
import com.prineside.tdi2.towers.AirTower;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/projectiles/AirProjectile.class */
public final class AirProjectile extends EnemyFollowingProjectile {
    private static final Color d = new Color(MaterialColor.CYAN.P500.r, MaterialColor.CYAN.P500.g, MaterialColor.CYAN.P500.f888b, 0.56f);
    private AirTower e;
    private float f;
    private float g;
    private float h;

    @NAGS
    private TrailMultiLine i;

    /* synthetic */ AirProjectile(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.EnemyFollowingProjectile, com.prineside.tdi2.Projectile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeClassAndObject(output, this.e);
        output.writeFloat(this.f);
        output.writeFloat(this.g);
        output.writeFloat(this.h);
    }

    @Override // com.prineside.tdi2.EnemyFollowingProjectile, com.prineside.tdi2.Projectile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.e = (AirTower) kryo.readClassAndObject(input);
        this.f = input.readFloat();
        this.g = input.readFloat();
        this.h = input.readFloat();
    }

    private AirProjectile() {
        super(ProjectileType.AIR);
    }

    public final void setup(AirTower airTower, Enemy enemy, float f, Vector2 vector2, float f2, float f3, float f4, float f5) {
        this.e = airTower;
        this.c = f;
        this.f = f3;
        this.g = f4;
        this.h = f5;
        if (this.S._projectileTrail != null && this.S._projectileTrail.isEnabled()) {
            this.i = Game.i.shapeManager.F.TRAIL_MULTI_LINE.obtain();
            this.i.setup(d, 15.0f, 0.4f, 0.0f);
            this.i.setStartPoint(vector2.x, vector2.y);
            this.S._projectileTrail.addTrail(this.i);
        }
        super.setup(vector2, enemy, f2);
    }

    public final float getBurnChance() {
        return this.f;
    }

    public final float getBurningTime() {
        return this.h;
    }

    public final float getBurnDamage() {
        return this.g;
    }

    @Override // com.prineside.tdi2.EnemyFollowingProjectile, com.prineside.tdi2.Registrable
    public final void setUnregistered() {
        super.setUnregistered();
        if (this.i != null) {
            this.i.allowCompletion();
            this.i = null;
        }
    }

    @Override // com.prineside.tdi2.EnemyFollowingProjectile, com.prineside.tdi2.Projectile, com.badlogic.gdx.utils.Pool.Poolable
    public final void reset() {
        super.reset();
        this.e = null;
        this.i = null;
        this.f = 0.0f;
        this.g = 0.0f;
        this.h = 0.0f;
    }

    @Override // com.prineside.tdi2.Projectile
    public final void hit() {
        super.hit();
        Enemy target = getTarget();
        if (target != null) {
            this.S.damage.queueDamage(this.S.damage.obtainRecord().setup(target, this.c, DamageType.BULLET).setTower(this.e).setProjectile(this));
        }
    }

    @Override // com.prineside.tdi2.EnemyFollowingProjectile, com.prineside.tdi2.Projectile
    public final void draw(Batch batch, float f) {
        if (this.i != null) {
            this.i.setHeadPosition(this.drawPosition.x, this.drawPosition.y);
        }
        batch.draw(this.S.projectile.F.AIR.f2617a, this.drawPosition.x - 9.0f, this.drawPosition.y - 9.0f, 18.0f, 18.0f);
        super.draw(batch, f);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/projectiles/AirProjectile$AirProjectileFactory.class */
    public static class AirProjectileFactory extends Projectile.Factory<AirProjectile> {

        /* renamed from: a, reason: collision with root package name */
        TextureRegion f2617a;

        @Override // com.prineside.tdi2.Projectile.Factory
        protected final /* synthetic */ AirProjectile a() {
            return b();
        }

        @Override // com.prineside.tdi2.Projectile.Factory
        public void setupAssets() {
            this.f2617a = Game.i.assetManager.getTextureRegion("projectile-air");
            Game.i.assetManager.getTextureRegion("bullet-trace-thin");
        }

        private static AirProjectile b() {
            return new AirProjectile((byte) 0);
        }
    }
}
