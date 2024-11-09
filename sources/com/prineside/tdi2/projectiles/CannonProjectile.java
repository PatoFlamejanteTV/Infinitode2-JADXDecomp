package com.prineside.tdi2.projectiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.EnemyFollowingExplosiveProjectile;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Projectile;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ProjectileType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.explosions.CannonExplosion;
import com.prineside.tdi2.shapes.TrailMultiLine;
import com.prineside.tdi2.towers.CannonTower;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/projectiles/CannonProjectile.class */
public final class CannonProjectile extends EnemyFollowingExplosiveProjectile {
    private static final Color d = new Color(MaterialColor.RED.P500.r, MaterialColor.RED.P500.g, MaterialColor.RED.P500.f888b, 0.56f);

    @NAGS
    private TrailMultiLine e;
    private CannonExplosion f;

    /* synthetic */ CannonProjectile(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.EnemyFollowingExplosiveProjectile, com.prineside.tdi2.EnemyFollowingProjectile, com.prineside.tdi2.Projectile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeClassAndObject(output, this.f);
    }

    @Override // com.prineside.tdi2.EnemyFollowingExplosiveProjectile, com.prineside.tdi2.EnemyFollowingProjectile, com.prineside.tdi2.Projectile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f = (CannonExplosion) kryo.readClassAndObject(input);
    }

    private CannonProjectile() {
        super(ProjectileType.CANNON);
    }

    public final void setup(Tower tower, Enemy enemy, float f, float f2, Vector2 vector2, float f3, int i, float f4, float f5) {
        this.f = this.S.explosion.F.CANNON.obtain();
        this.f.setup(tower, enemy.getPosition().x, enemy.getPosition().y, f, f2, i, f4, f5);
        if (tower.isRegistered() && tower.type == TowerType.CANNON && ((CannonTower) tower).isAbilityInstalled(2)) {
            this.f.piercingMultiplier = (float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_CANNON_A_FOUNDATION_PIERCING);
        }
        if (this.S._projectileTrail != null && this.S._projectileTrail.isEnabled()) {
            this.e = Game.i.shapeManager.F.TRAIL_MULTI_LINE.obtain();
            this.e.setup(d, 15.0f, 0.4f, 0.0f);
            this.e.setStartPoint(vector2.x, vector2.y);
            this.S._projectileTrail.addTrail(this.e);
        }
        super.a(vector2, enemy, tower.angle, f3, this.f, 0.0f, 120.0f);
    }

    @Override // com.prineside.tdi2.EnemyFollowingExplosiveProjectile, com.prineside.tdi2.EnemyFollowingProjectile, com.prineside.tdi2.Registrable
    public final void setUnregistered() {
        super.setUnregistered();
        if (this.e != null) {
            this.e.allowCompletion();
            this.e = null;
        }
    }

    @Override // com.prineside.tdi2.EnemyFollowingExplosiveProjectile, com.prineside.tdi2.EnemyFollowingProjectile, com.prineside.tdi2.Projectile, com.badlogic.gdx.utils.Pool.Poolable
    public final void reset() {
        super.reset();
        this.e = null;
        this.f = null;
    }

    @Override // com.prineside.tdi2.EnemyFollowingProjectile, com.prineside.tdi2.Projectile
    public final void draw(Batch batch, float f) {
        if (this.e != null) {
            this.e.setHeadPosition(this.drawPosition.x, this.drawPosition.y);
        }
        batch.draw(this.S.projectile.F.CANNON.f2622a, this.drawPosition.x - 8.0f, this.drawPosition.y - 8.0f, 16.0f, 16.0f);
        super.draw(batch, f);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/projectiles/CannonProjectile$CannonProjectileFactory.class */
    public static class CannonProjectileFactory extends Projectile.Factory<CannonProjectile> {

        /* renamed from: a, reason: collision with root package name */
        TextureRegion f2622a;

        @Override // com.prineside.tdi2.Projectile.Factory
        protected final /* synthetic */ CannonProjectile a() {
            return b();
        }

        @Override // com.prineside.tdi2.Projectile.Factory
        public void setupAssets() {
            this.f2622a = Game.i.assetManager.getTextureRegion("projectile-cannon");
            Game.i.assetManager.getTextureRegion("bullet-trace-thin");
        }

        private static CannonProjectile b() {
            return new CannonProjectile((byte) 0);
        }
    }
}
