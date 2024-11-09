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
import com.prineside.tdi2.EnemyFollowingProjectile;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Projectile;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ProjectileType;
import com.prineside.tdi2.enums.SpecialDamageType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.shapes.TrailMultiLine;
import com.prineside.tdi2.towers.BasicTower;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/projectiles/BasicProjectile.class */
public final class BasicProjectile extends EnemyFollowingProjectile {
    private static final Color d = new Color(MaterialColor.TEAL.P500.r, MaterialColor.TEAL.P500.g, MaterialColor.TEAL.P500.f888b, 0.56f);
    private Tower e;

    @NAGS
    private TrailMultiLine f;

    /* synthetic */ BasicProjectile(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.EnemyFollowingProjectile, com.prineside.tdi2.Projectile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeClassAndObject(output, this.e);
    }

    @Override // com.prineside.tdi2.EnemyFollowingProjectile, com.prineside.tdi2.Projectile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.e = (Tower) kryo.readClassAndObject(input);
    }

    private BasicProjectile() {
        super(ProjectileType.BASIC);
    }

    public final void setup(Tower tower, Enemy enemy, float f, Vector2 vector2, float f2) {
        this.e = tower;
        this.c = f;
        if (this.f == null && this.S._projectileTrail != null && this.S._projectileTrail.isEnabled()) {
            this.f = Game.i.shapeManager.F.TRAIL_MULTI_LINE.obtain();
            this.f.setup(d, 15.0f, 0.4f, 0.0f);
            this.f.setStartPoint(vector2.x, vector2.y);
            this.S._projectileTrail.addTrail(this.f);
        }
        super.setup(vector2, enemy, f2);
    }

    @Override // com.prineside.tdi2.EnemyFollowingProjectile, com.prineside.tdi2.Registrable
    public final void setUnregistered() {
        super.setUnregistered();
        if (this.f != null) {
            this.f.allowCompletion();
            this.f = null;
        }
    }

    @Override // com.prineside.tdi2.EnemyFollowingProjectile, com.prineside.tdi2.Projectile, com.badlogic.gdx.utils.Pool.Poolable
    public final void reset() {
        super.reset();
        this.e = null;
        this.f = null;
    }

    @Override // com.prineside.tdi2.Projectile
    public final void hit() {
        super.hit();
        if (this.e == null || !this.e.isRegistered()) {
            return;
        }
        Enemy target = getTarget();
        boolean z = false;
        if (target != null) {
            z = !target.isVulnerableToSpecial(SpecialDamageType.CHAINING);
            this.S.damage.queueDamage(this.S.damage.obtainRecord().setup(getTarget(), this.c, DamageType.BULLET).setTower(this.e).setProjectile(this));
        }
        if (this.e.type == TowerType.BASIC) {
            BasicTower basicTower = (BasicTower) this.e;
            if (!z && basicTower.isAbilityInstalled(2)) {
                float percentValueAsMultiplier = (float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_BASIC_A_FOUNDATION_RICOCHET_CHANCE);
                float f = percentValueAsMultiplier;
                if (percentValueAsMultiplier > 0.99d) {
                    f = 0.99f;
                }
                if (this.S.gameState.randomFloat() < f) {
                    this.speed = (float) (this.speed * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_BASIC_A_FOUNDATION_RICOCHET_SPEED));
                    if (this.speed > 192.0f) {
                        Array<Enemy> enemyArray = this.S.TSH.getEnemyArray();
                        this.S.map.getEnemiesInCircle(getPosition().x, getPosition().y, 256.0f, (enemyReference, f2, f3, f4) -> {
                            Enemy enemy = enemyReference.enemy;
                            if (enemy == null || enemy == target || !this.e.canAttackEnemy(enemy)) {
                                return true;
                            }
                            enemyArray.add(enemy);
                            return true;
                        });
                        if (enemyArray.size != 0) {
                            setup(this.e, enemyArray.items[this.S.gameState.randomInt(enemyArray.size)], this.c, getPosition(), this.speed / 128.0f);
                            this.maxRotationSpeed = 0.0f;
                            this.maxRotationSpeedDynamic = 0.0f;
                        }
                        this.S.TSH.freeEnemyArray(enemyArray);
                    }
                }
            }
        }
    }

    @Override // com.prineside.tdi2.EnemyFollowingProjectile, com.prineside.tdi2.Projectile
    public final void draw(Batch batch, float f) {
        if (this.f != null) {
            this.f.setHeadPosition(this.drawPosition.x, this.drawPosition.y);
        }
        batch.draw(this.S.projectile.F.BASIC.f2618a, this.drawPosition.x - 9.0f, this.drawPosition.y - 9.0f, 18.0f, 18.0f);
        super.draw(batch, f);
    }

    @Override // com.prineside.tdi2.EnemyFollowingProjectile, com.prineside.tdi2.Projectile
    public final String toString() {
        return super.toString() + " (tower: " + this.e + ")";
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/projectiles/BasicProjectile$BasicProjectileFactory.class */
    public static class BasicProjectileFactory extends Projectile.Factory<BasicProjectile> {

        /* renamed from: a, reason: collision with root package name */
        TextureRegion f2618a;

        @Override // com.prineside.tdi2.Projectile.Factory
        protected final /* synthetic */ BasicProjectile a() {
            return b();
        }

        @Override // com.prineside.tdi2.Projectile.Factory
        public void setupAssets() {
            this.f2618a = Game.i.assetManager.getTextureRegion("projectile-basic");
            Game.i.assetManager.getTextureRegion("bullet-trace-thin");
        }

        private static BasicProjectile b() {
            return new BasicProjectile((byte) 0);
        }
    }
}
