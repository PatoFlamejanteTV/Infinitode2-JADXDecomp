package com.prineside.tdi2.projectiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.CollidingProjectile;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Projectile;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.enums.ProjectileType;
import com.prineside.tdi2.shapes.TrailMultiLine;
import com.prineside.tdi2.utils.EntityUtils;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/projectiles/BulletWallProjectile.class */
public final class BulletWallProjectile extends CollidingProjectile {
    private static final Color d = Color.WHITE.cpy();
    private static final Color e = new Color(MaterialColor.TEAL.P500.r, MaterialColor.TEAL.P500.g, MaterialColor.TEAL.P500.f888b, 0.56f);
    private float f;
    private float g;
    private boolean h;

    @NAGS
    private TrailMultiLine i;

    /* synthetic */ BulletWallProjectile(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.CollidingProjectile, com.prineside.tdi2.Projectile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.f);
        output.writeFloat(this.g);
        output.writeBoolean(this.h);
    }

    @Override // com.prineside.tdi2.CollidingProjectile, com.prineside.tdi2.Projectile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f = input.readFloat();
        this.g = input.readFloat();
        this.h = input.readBoolean();
    }

    private BulletWallProjectile() {
        super(ProjectileType.BULLET_WALL);
    }

    public final void setup(float f, Vector2 vector2, Vector2 vector22, float f2) {
        super.a(vector2, f2, vector22);
        this.c = f;
        this.f = this.totalFlyTime;
        this.g = 0.0f;
        this.totalFlyTime *= 3.0f;
        if (this.S._projectileTrail != null && this.S._projectileTrail.isEnabled()) {
            this.i = Game.i.shapeManager.F.TRAIL_MULTI_LINE.obtain();
            this.i.setup(e, 15.0f, 0.5f, 0.0f);
            this.i.setStartPoint(vector2.x, vector2.y);
            this.S._projectileTrail.addTrail(this.i);
        }
    }

    @Override // com.prineside.tdi2.Registrable
    public final void setUnregistered() {
        super.setUnregistered();
        if (this.i != null) {
            this.i.allowCompletion();
            this.i = null;
        }
    }

    @Override // com.prineside.tdi2.CollidingProjectile, com.prineside.tdi2.Projectile
    public final void update(float f) {
        super.update(f);
        this.g += f;
        if (this.g >= this.f) {
            EntityUtils.removeNullRefs(this.S.map.spawnedEnemies);
            if (this.S.map.spawnedEnemies.size == 0) {
                this.f1682a.scl(-1.0f);
                if (this.f1683b != -741.84f) {
                    this.f1683b += 180.0f;
                }
            } else {
                flyOnEnemy(this.S.map.spawnedEnemies.get(this.S.gameState.randomInt(this.S.map.spawnedEnemies.size)).enemy);
            }
            this.g = 0.0f;
        }
    }

    @Override // com.prineside.tdi2.CollidingProjectile, com.prineside.tdi2.Projectile, com.badlogic.gdx.utils.Pool.Poolable
    public final void reset() {
        super.reset();
        this.f = 0.0f;
        this.g = 0.0f;
        this.h = false;
        this.i = null;
    }

    @Override // com.prineside.tdi2.CollidingProjectile
    protected final void a(Enemy enemy) {
        float f = this.c;
        if (EnemyType.isBoss(enemy.type)) {
            f *= 0.1f;
        }
        float abilityVulnerability = f * enemy.getAbilityVulnerability(AbilityType.BULLET_WALL);
        if (abilityVulnerability > 0.0f) {
            this.S.damage.queueDamage(this.S.damage.obtainRecord().setup(enemy, abilityVulnerability, DamageType.BULLET).setProjectile(this).setCleanForDps(false));
        }
        this.h = true;
    }

    @Override // com.prineside.tdi2.CollidingProjectile, com.prineside.tdi2.Projectile
    public final boolean isDone() {
        return this.h || super.isDone();
    }

    @Override // com.prineside.tdi2.Projectile
    public final void draw(Batch batch, float f) {
        if (this.i != null) {
            Vector2 add = new Vector2(-this.f1682a.x, -this.f1682a.y).scl(6.0f).add(this.drawPosition);
            this.i.setHeadPosition(add.x, add.y);
        }
        if (a() < 0.2f) {
            d.f889a = a() / 0.2f;
            batch.setColor(d);
        }
        batch.draw(this.S.projectile.F.BULLET_WALL.f2621a, this.drawPosition.x - 7.5f, this.drawPosition.y - 15.0f, 7.5f, 15.0f, 15.0f, 30.0f, 1.0f, 1.0f, this.drawAngle);
        batch.setColor(Color.WHITE);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/projectiles/BulletWallProjectile$MultishotProjectileFactory.class */
    public static class MultishotProjectileFactory extends Projectile.Factory<BulletWallProjectile> {

        /* renamed from: a, reason: collision with root package name */
        TextureRegion f2621a;

        @Override // com.prineside.tdi2.Projectile.Factory
        protected final /* synthetic */ BulletWallProjectile a() {
            return b();
        }

        @Override // com.prineside.tdi2.Projectile.Factory
        public void setupAssets() {
            this.f2621a = Game.i.assetManager.getTextureRegion("projectile-bullet-wall");
            Game.i.assetManager.getTextureRegion("bullet-trace-thin");
        }

        private static BulletWallProjectile b() {
            return new BulletWallProjectile((byte) 0);
        }
    }
}
