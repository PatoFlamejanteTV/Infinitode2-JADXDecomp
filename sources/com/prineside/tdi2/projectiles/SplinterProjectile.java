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
import com.prineside.tdi2.DamageRecord;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Projectile;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.ProjectileType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.shapes.TrailMultiLine;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/projectiles/SplinterProjectile.class */
public final class SplinterProjectile extends CollidingProjectile {
    private static final Color d = Color.WHITE.cpy();
    private static final Color e = new Color(MaterialColor.RED.P500.r, MaterialColor.RED.P500.g, MaterialColor.RED.P500.f888b, 0.56f);
    private Tower f;
    private boolean g;

    @NAGS
    private TrailMultiLine h;

    /* synthetic */ SplinterProjectile(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.CollidingProjectile, com.prineside.tdi2.Projectile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeClassAndObject(output, this.f);
        output.writeBoolean(this.g);
    }

    @Override // com.prineside.tdi2.CollidingProjectile, com.prineside.tdi2.Projectile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f = (Tower) kryo.readClassAndObject(input);
        this.g = input.readBoolean();
    }

    private SplinterProjectile() {
        super(ProjectileType.SPLINTER);
        this.g = false;
    }

    public final void setup(Tower tower, float f, Vector2 vector2, Vector2 vector22, float f2, Color color) {
        this.f = tower;
        this.c = f;
        if (this.S._projectileTrail != null && this.S._projectileTrail.isEnabled()) {
            this.h = Game.i.shapeManager.F.TRAIL_MULTI_LINE.obtain();
            this.h.setup(color == null ? e : color, 11.0f, 0.4f, 0.0f);
            this.h.setStartPoint(vector2.x, vector2.y);
            this.S._projectileTrail.addTrail(this.h);
        }
        super.a(vector2, f2, vector22);
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
        this.f = null;
        this.h = null;
        this.g = false;
    }

    @Override // com.prineside.tdi2.CollidingProjectile
    protected final void a(Enemy enemy) {
        if (this.g) {
            return;
        }
        if (this.f == null || this.f.isRegistered()) {
            if (this.f == null || this.f.canAttackEnemy(enemy)) {
                DamageRecord projectile = this.S.damage.obtainRecord().setup(enemy, this.c, DamageType.BULLET).setTower(this.f).setProjectile(this);
                if (this.f != null && this.f.type == TowerType.CANNON && this.f.isAbilityInstalled(3) && enemy.getHealth() / enemy.maxHealth < 0.25f) {
                    projectile.setDamage(this.c * 1.25f);
                }
                this.S.damage.queueDamage(projectile);
                this.g = true;
            }
        }
    }

    @Override // com.prineside.tdi2.CollidingProjectile, com.prineside.tdi2.Projectile
    public final boolean isDone() {
        return this.g || super.isDone();
    }

    @Override // com.prineside.tdi2.Projectile
    public final void draw(Batch batch, float f) {
        if (this.h != null) {
            Vector2 vector2 = new Vector2();
            vector2.set(-this.f1682a.x, -this.f1682a.y).scl(6.0f).add(this.drawPosition);
            this.h.setHeadPosition(vector2.x, vector2.y);
        }
        if (a() < 0.1f) {
            d.f889a = a() / 0.1f;
            batch.setColor(d);
        }
        batch.draw(this.S.projectile.F.SPLINTER.f2633a, this.drawPosition.x - 4.5f, this.drawPosition.y - 9.0f, 4.5f, 9.0f, 9.0f, 18.0f, 1.0f, 1.0f, this.drawAngle);
        batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/projectiles/SplinterProjectile$SplinterProjectileFactory.class */
    public static class SplinterProjectileFactory extends Projectile.Factory<SplinterProjectile> {

        /* renamed from: a, reason: collision with root package name */
        TextureRegion f2633a;

        @Override // com.prineside.tdi2.Projectile.Factory
        protected final /* synthetic */ SplinterProjectile a() {
            return b();
        }

        @Override // com.prineside.tdi2.Projectile.Factory
        public void setupAssets() {
            this.f2633a = Game.i.assetManager.getTextureRegion("projectile-cannon-splinter");
            Game.i.assetManager.getTextureRegion("bullet-trace-thin");
        }

        private static SplinterProjectile b() {
            return new SplinterProjectile((byte) 0);
        }
    }
}
