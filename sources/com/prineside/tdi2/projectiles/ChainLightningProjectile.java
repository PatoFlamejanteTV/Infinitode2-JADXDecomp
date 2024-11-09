package com.prineside.tdi2.projectiles;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.IntSet;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Projectile;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.ProjectileType;
import com.prineside.tdi2.enums.SpecialDamageType;
import com.prineside.tdi2.shapes.ChainLightning;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/projectiles/ChainLightningProjectile.class */
public final class ChainLightningProjectile extends Projectile {

    /* renamed from: a, reason: collision with root package name */
    private Tower f2623a;

    /* renamed from: b, reason: collision with root package name */
    private float f2624b;
    private float d;
    private float e;
    private float f;
    private IntSet g;
    private Vector2 h;
    private float i;

    /* synthetic */ ChainLightningProjectile(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Projectile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeClassAndObject(output, this.f2623a);
        output.writeFloat(this.f2624b);
        output.writeFloat(this.d);
        output.writeFloat(this.e);
        output.writeFloat(this.f);
        kryo.writeObject(output, this.g);
        kryo.writeObject(output, this.h);
        output.writeFloat(this.i);
    }

    @Override // com.prineside.tdi2.Projectile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f2623a = (Tower) kryo.readClassAndObject(input);
        this.f2624b = input.readFloat();
        this.d = input.readFloat();
        this.e = input.readFloat();
        this.f = input.readFloat();
        this.g = (IntSet) kryo.readObject(input, IntSet.class);
        this.h = (Vector2) kryo.readObject(input, Vector2.class);
        this.i = input.readFloat();
    }

    private ChainLightningProjectile() {
        super(ProjectileType.CHAIN_LIGHTNING);
        this.g = new IntSet();
        this.h = new Vector2();
    }

    public final void setup(Tower tower, Enemy enemy, float f, float f2, float f3, float f4, Vector2 vector2) {
        if (f4 < 0.0f) {
            throw new IllegalArgumentException("chainLength is " + f4);
        }
        Preconditions.checkArgument(f > 0.0f && PMath.isFinite(f), "Invalid damage: %s", Float.valueOf(f));
        Preconditions.checkArgument(f2 >= 0.0f && PMath.isFinite(f2), "Invalid minDamage: %s", Float.valueOf(f2));
        this.h.set(vector2);
        this.f2623a = tower;
        this.f2624b = f;
        this.d = f2;
        this.e = f3;
        this.f = f4;
        this.i = 0.0f;
        a(enemy);
    }

    @Override // com.prineside.tdi2.Projectile, com.badlogic.gdx.utils.Pool.Poolable
    public final void reset() {
        super.reset();
        this.f2624b = 0.0f;
        this.d = 0.0f;
        this.e = 0.0f;
        this.f = 0.0f;
        this.i = 0.0f;
        this.f2623a = null;
        this.g.clear();
        this.h.setZero();
    }

    private void a(Enemy enemy) {
        float randomFloat = enemy.getPosition().x + ((this.S.gameState.randomFloat() - 0.5f) * enemy.getSize() * 0.8f);
        float randomFloat2 = enemy.getPosition().y + ((this.S.gameState.randomFloat() - 0.5f) * enemy.getSize() * 0.8f);
        if (this.S._particle != null && !this.S.gameState.canSkipMediaUpdate() && Game.i.settingsManager.isProjectilesDrawing()) {
            ChainLightning obtain = Game.i.shapeManager.F.CHAIN_LIGHTNING.obtain();
            obtain.setTexture(this.S.projectile.F.CHAIN_LIGHTNING.f2625a, true, true);
            obtain.setColor(MaterialColor.BLUE.P300);
            obtain.setup(this.h.x, this.h.y, randomFloat, randomFloat2, 0.5f, 0.25f, true, 13.440001f, 67.2f, 16.0f);
            this.S._particle.addChainLightning(obtain);
        }
        this.h.set(randomFloat, randomFloat2);
        if (!enemy.isVulnerableToSpecial(SpecialDamageType.CHAINING)) {
            this.f = 0.0f;
        }
        this.g.add(enemy.id);
        this.S.damage.queueDamage(this.S.damage.obtainRecord().setup(enemy, this.f2624b, DamageType.ELECTRICITY).setTower(this.f2623a).setProjectile(this));
        this.f2624b *= this.e;
        if (this.f2624b < this.d) {
            this.f2624b = this.d;
        }
    }

    @Override // com.prineside.tdi2.Projectile
    public final boolean isDone() {
        return hasReachedTarget();
    }

    @Override // com.prineside.tdi2.Projectile
    public final boolean hasReachedTarget() {
        return this.f <= 0.0f && this.i > 0.2f;
    }

    @Override // com.prineside.tdi2.Projectile
    public final void applyDrawInterpolation(float f) {
    }

    private boolean a(float f) {
        if (this.f <= 0.0f) {
            this.i += f;
            return false;
        }
        if (this.f < 1.0f && this.S.gameState.randomFloat() > this.f) {
            this.f = 0.0f;
            return true;
        }
        float[] fArr = {Float.MAX_VALUE};
        Enemy[] enemyArr = {null};
        this.S.map.getEnemiesInCircle(this.h.x, this.h.y, 192.0f, (enemyReference, f2, f3, f4) -> {
            Enemy enemy;
            float dst2 = this.h.dst2(f2, f3);
            if (dst2 >= fArr[0] || (enemy = enemyReference.enemy) == null || this.g.contains(enemy.id)) {
                return true;
            }
            if ((this.f2623a != null && !this.f2623a.canAttackEnemy(enemy)) || !enemy.isVulnerableTo(DamageType.ELECTRICITY)) {
                return true;
            }
            enemyArr[0] = enemy;
            fArr[0] = dst2;
            return true;
        });
        if (enemyArr[0] == null) {
            this.f = 0.0f;
            return true;
        }
        this.f -= 1.0f / ((enemyArr[0].buffFreezingLightningLengthBonus + 100.0f) * 0.01f);
        if (this.f < 0.0f) {
            this.f = 0.0f;
        }
        Enemy enemy = enemyArr[0];
        enemyArr[0] = null;
        a(enemy);
        return false;
    }

    @Override // com.prineside.tdi2.Projectile
    public final void update(float f) {
        if (this.f2623a == null || !this.f2623a.isRegistered()) {
            this.f = 0.0f;
            this.i += f;
            return;
        }
        int ceil = MathUtils.ceil(f / 0.03448276f);
        if (ceil == 1) {
            a(f);
            return;
        }
        float f2 = f / ceil;
        for (int i = 0; i < ceil && !a(f2); i++) {
        }
    }

    @Override // com.prineside.tdi2.Projectile
    public final void draw(Batch batch, float f) {
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/projectiles/ChainLightningProjectile$ChainLightningProjectileFactory.class */
    public static class ChainLightningProjectileFactory extends Projectile.Factory<ChainLightningProjectile> {

        /* renamed from: a, reason: collision with root package name */
        TextureRegion f2625a;

        @Override // com.prineside.tdi2.Projectile.Factory
        protected final /* synthetic */ ChainLightningProjectile a() {
            return b();
        }

        @Override // com.prineside.tdi2.Projectile.Factory
        public void setupAssets() {
            this.f2625a = Game.i.assetManager.getTextureRegion("chain-lightning");
        }

        private static ChainLightningProjectile b() {
            return new ChainLightningProjectile((byte) 0);
        }
    }
}
