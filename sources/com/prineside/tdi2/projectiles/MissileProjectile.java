package com.prineside.tdi2.projectiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.EnemyFollowingExplosiveProjectile;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Projectile;
import com.prineside.tdi2.enums.ProjectileType;
import com.prineside.tdi2.explosions.MissileExplosion;
import com.prineside.tdi2.shapes.TrailMultiLine;
import com.prineside.tdi2.tiles.SpawnTile;
import com.prineside.tdi2.towers.MissileTower;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/projectiles/MissileProjectile.class */
public final class MissileProjectile extends EnemyFollowingExplosiveProjectile {
    private static final TLog d = TLog.forClass(MissileProjectile.class);
    private static final Color e = new Color(MaterialColor.RED.P500.r, MaterialColor.RED.P500.g, MaterialColor.RED.P500.f888b, 0.56f);

    @NAGS
    private TrailMultiLine f;

    @NAGS
    private float g;
    private MissileTower h;
    private MissileExplosion i;
    private boolean j;
    private float k;
    private float l;

    @Override // com.prineside.tdi2.EnemyFollowingExplosiveProjectile, com.prineside.tdi2.EnemyFollowingProjectile, com.prineside.tdi2.Projectile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObjectOrNull(output, this.h, MissileTower.class);
        kryo.writeObjectOrNull(output, this.i, MissileExplosion.class);
        output.writeBoolean(this.j);
        output.writeFloat(this.k);
        output.writeFloat(this.l);
    }

    @Override // com.prineside.tdi2.EnemyFollowingExplosiveProjectile, com.prineside.tdi2.EnemyFollowingProjectile, com.prineside.tdi2.Projectile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.h = (MissileTower) kryo.readObjectOrNull(input, MissileTower.class);
        this.i = (MissileExplosion) kryo.readObjectOrNull(input, MissileExplosion.class);
        this.j = input.readBoolean();
        this.k = input.readFloat();
        this.l = input.readFloat();
    }

    protected MissileProjectile() {
        super(ProjectileType.MISSILE);
        this.g = 1.0f;
        this.j = false;
        this.l = Float.MAX_VALUE;
    }

    public final void setup(MissileTower missileTower, Enemy enemy, float f, float f2, Vector2 vector2, float f3, float f4, float f5, float f6) {
        this.i = this.S.explosion.F.MISSILE.obtain();
        this.i.setup(missileTower, enemy.getPosition().x, enemy.getPosition().y, f, f2);
        this.h = missileTower;
        this.g = f6;
        this.l = Float.MAX_VALUE;
        if (this.S._projectileTrail != null && this.S._projectileTrail.isEnabled()) {
            this.f = Game.i.shapeManager.F.TRAIL_MULTI_LINE.obtain();
            this.f.setup(e, 15.0f * f6, 0.6f, 0.0f);
            this.f.setStartPoint(vector2.x, vector2.y);
            this.S._projectileTrail.addTrail(this.f);
        }
        super.a(vector2, enemy, f5, f3, this.i, f4, f4 * 0.05f);
    }

    @Override // com.prineside.tdi2.EnemyFollowingExplosiveProjectile, com.prineside.tdi2.EnemyFollowingProjectile, com.prineside.tdi2.Registrable
    public final void setUnregistered() {
        super.setUnregistered();
        if (this.f != null) {
            this.f.allowCompletion();
            this.f = null;
        }
    }

    @Override // com.prineside.tdi2.EnemyFollowingExplosiveProjectile, com.prineside.tdi2.EnemyFollowingProjectile, com.prineside.tdi2.Projectile, com.badlogic.gdx.utils.Pool.Poolable
    public final void reset() {
        super.reset();
        this.j = false;
        this.i = null;
        this.k = 0.0f;
        this.h = null;
        this.f = null;
        this.l = Float.MAX_VALUE;
    }

    public final MissileTower getTower() {
        return this.h;
    }

    @Override // com.prineside.tdi2.EnemyFollowingProjectile
    public final void setTarget(Enemy enemy) {
        this.j = false;
        super.setTarget(enemy);
    }

    @Override // com.prineside.tdi2.EnemyFollowingProjectile, com.prineside.tdi2.Projectile
    public final void update(float f) {
        Enemy target = getTarget();
        if (this.h == null || !this.h.isRegistered()) {
            this.f1697a = true;
            return;
        }
        if (!this.j && target == null) {
            this.j = true;
            if (this.S.map.spawnedEnemies.size != 0) {
                Enemy enemy = this.S.map.spawnedEnemies.items[this.S.gameState.randomInt(this.S.map.spawnedEnemies.size)].enemy;
                if (enemy == null) {
                    d.i("null enemy", new Object[0]);
                }
                if (enemy != null && this.h.canAttackEnemy(enemy)) {
                    setTarget(enemy);
                }
            } else {
                Array tilesByType = this.S.map.getMap().getTilesByType(SpawnTile.class);
                this.f1698b.set(((SpawnTile) tilesByType.get(this.S.gameState.randomInt(tilesByType.size))).center);
                return;
            }
        }
        super.update(f);
        this.k += f;
        if (this.k > 20.0f) {
            this.f1697a = true;
        }
    }

    @Override // com.prineside.tdi2.EnemyFollowingProjectile, com.prineside.tdi2.Projectile
    public final void draw(Batch batch, float f) {
        if (this.f != null) {
            Vector2 add = new Vector2(9.0f, 0.0f).rotateDeg(this.drawAngle - 90.0f).add(this.drawPosition);
            this.f.setHeadPosition(add.x, add.y);
        }
        batch.draw(this.S.projectile.F.MISSILE.f2630a, this.drawPosition.x - (10.5f * this.g), this.drawPosition.y - (21.0f * this.g), 10.5f * this.g, 21.0f * this.g, 21.0f * this.g, 42.0f * this.g, 1.0f, 1.0f, this.drawAngle);
        super.draw(batch, f);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/projectiles/MissileProjectile$MissileProjectileFactory.class */
    public static class MissileProjectileFactory extends Projectile.Factory<MissileProjectile> {

        /* renamed from: a, reason: collision with root package name */
        TextureRegion f2630a;

        @Override // com.prineside.tdi2.Projectile.Factory
        protected final /* synthetic */ MissileProjectile a() {
            return b();
        }

        @Override // com.prineside.tdi2.Projectile.Factory
        public void setupAssets() {
            this.f2630a = Game.i.assetManager.getTextureRegion("projectile-missile");
            Game.i.assetManager.getTextureRegion("bullet-trace-thin");
        }

        private static MissileProjectile b() {
            return new MissileProjectile();
        }
    }
}
