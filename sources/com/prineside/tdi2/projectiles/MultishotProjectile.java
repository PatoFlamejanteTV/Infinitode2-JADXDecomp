package com.prineside.tdi2.projectiles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.IntIntMap;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.CollidingProjectile;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Projectile;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.buffs.BonusCoinsBuff;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ProjectileType;
import com.prineside.tdi2.enums.SpecialDamageType;
import com.prineside.tdi2.enums.TowerType;
import com.prineside.tdi2.shapes.TrailMultiLine;
import com.prineside.tdi2.towers.MultishotTower;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/projectiles/MultishotProjectile.class */
public final class MultishotProjectile extends CollidingProjectile {
    private static final Color d = Color.WHITE.cpy();
    private static final Color e = new Color(MaterialColor.YELLOW.P500.r, MaterialColor.YELLOW.P500.g, MaterialColor.YELLOW.P500.f888b, 0.4f);

    @NAGS
    private TrailMultiLine f;

    @NAGS
    public float drawScale;
    private Tower g;
    private boolean h;
    private boolean i;
    private boolean j;
    private boolean k;
    public boolean flyingBack;

    /* synthetic */ MultishotProjectile(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.CollidingProjectile, com.prineside.tdi2.Projectile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeClassAndObject(output, this.g);
        output.writeBoolean(this.h);
        output.writeBoolean(this.i);
        output.writeBoolean(this.j);
        output.writeBoolean(this.k);
        output.writeBoolean(this.flyingBack);
    }

    @Override // com.prineside.tdi2.CollidingProjectile, com.prineside.tdi2.Projectile, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.g = (Tower) kryo.readClassAndObject(input);
        this.h = input.readBoolean();
        this.i = input.readBoolean();
        this.j = input.readBoolean();
        this.k = input.readBoolean();
        this.flyingBack = input.readBoolean();
    }

    private MultishotProjectile() {
        super(ProjectileType.MULTISHOT);
        this.drawScale = 1.0f;
    }

    public final void setup(Tower tower, float f, Vector2 vector2, Vector2 vector22, float f2, boolean z, boolean z2, float f3) {
        super.a(vector2, f2, vector22);
        this.g = tower;
        this.c = f;
        this.i = z;
        this.j = z2;
        if (this.S._projectileTrail != null && this.S._projectileTrail.isEnabled()) {
            this.f = Game.i.shapeManager.F.TRAIL_MULTI_LINE.obtain();
            this.f.setup(e, 12.0f * f3, 0.4f, 0.0f);
            this.f.setStartPoint(vector2.x, vector2.y);
            this.S._projectileTrail.addTrail(this.f);
        }
    }

    @Override // com.prineside.tdi2.Registrable
    public final void setUnregistered() {
        super.setUnregistered();
        if (this.f != null) {
            this.f.allowCompletion();
            this.f = null;
        }
    }

    @Override // com.prineside.tdi2.CollidingProjectile, com.prineside.tdi2.Projectile, com.badlogic.gdx.utils.Pool.Poolable
    public final void reset() {
        super.reset();
        this.drawScale = 1.0f;
        this.flyingBack = false;
        this.k = false;
        this.h = false;
        this.i = false;
        this.j = false;
        this.g = null;
        this.f = null;
    }

    @Override // com.prineside.tdi2.CollidingProjectile
    protected final void a(Enemy enemy) {
        float f = this.c;
        if (this.j) {
            f *= 1.0f + (((float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_MULTISHOT_A_COUNTER_DAMAGE)) * enemy.otherEnemiesNearby);
        }
        if (!enemy.isVulnerableToSpecial(SpecialDamageType.PIERCING)) {
            this.h = true;
        }
        if ((this.g instanceof MultishotTower) && this.g.isRegistered()) {
            boolean z = this.g.isAbilityInstalled(2) && enemy.getTowerDamageMultiplier(TowerType.MULTISHOT) > 0.0f;
            boolean z2 = z;
            if (z && enemy.multishotTowerHits != null) {
                f *= (((float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_MULTISHOT_A_COMPACT_DAMAGE_PER_HIT)) * enemy.multishotTowerHits.get(this.g.id, 0)) + 1.0f;
            }
            if (this.g.isAbilityInstalled(1)) {
                f = (float) (f * (1.0d + ((this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_MULTISHOT_A_BUCKSHOT_DAMAGE) - 1.0d) * (1.0f - (PMath.getDistanceBetweenPoints(this.position, this.g.getTile().center) / this.g.rangeInPixels)))));
                BonusCoinsBuff bonusCoinsBuff = new BonusCoinsBuff();
                float floatValue = this.S.gameValue.getFloatValue(GameValueType.TOWER_MULTISHOT_A_BUCKSHOT_COINS_DURATION);
                bonusCoinsBuff.setup(floatValue, floatValue * 10.0f, ((MultishotTower) this.g).getBuckshotCoinBonusMult(), this.g);
                this.S.buff.P_BONUS_COINS.addBuff(enemy, bonusCoinsBuff);
            }
            this.S.damage.queueDamage(this.S.damage.obtainRecord().setup(enemy, f, DamageType.BULLET).setTower(this.g).setProjectile(this));
            if (z2) {
                if (enemy.multishotTowerHits == null) {
                    enemy.multishotTowerHits = new IntIntMap();
                }
                int i = enemy.multishotTowerHits.get(this.g.id, 0);
                if (i < this.S.gameValue.getIntValue(GameValueType.TOWER_MULTISHOT_A_COMPACT_MAX_HIT_COUNT)) {
                    enemy.multishotTowerHits.put(this.g.id, i + 1);
                }
            }
        }
        if (!this.i || this.k) {
            this.h = true;
            this.k = true;
        } else {
            this.c = (float) (this.c * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_MULTISHOT_A_PENETRATING_DAMAGE));
            this.k = true;
        }
        if (this.flyingBack && this.g != null && (this.g instanceof MultishotTower)) {
            int i2 = ((MultishotTower) this.g).notHitBackProjectileCount + 1;
            ((MultishotTower) this.g).notHitBackProjectileCount = 0;
            this.c *= i2;
        }
    }

    @Override // com.prineside.tdi2.CollidingProjectile, com.prineside.tdi2.Projectile
    public final boolean isDone() {
        return this.h || super.isDone();
    }

    @Override // com.prineside.tdi2.Projectile
    public final void onDone() {
        if (this.flyingBack && !this.k && this.g != null && (this.g instanceof MultishotTower) && ((MultishotTower) this.g).notHitBackProjectileCount < this.S.gameValue.getIntValue(GameValueType.TOWER_MULTISHOT_A_BACK_MAX_STACK)) {
            ((MultishotTower) this.g).notHitBackProjectileCount++;
        }
    }

    @Override // com.prineside.tdi2.Projectile
    public final void draw(Batch batch, float f) {
        if (this.f != null) {
            Vector2 vector2 = new Vector2();
            vector2.set(-this.f1682a.x, -this.f1682a.y).scl(9.0f).add(this.drawPosition);
            this.f.setHeadPosition(vector2.x, vector2.y);
        }
        if (a() < 0.2f) {
            d.f889a = a() / 0.2f;
            batch.setColor(d);
        }
        batch.draw(this.S.projectile.F.MULTISHOT.f2631a, this.drawPosition.x - (5.1749997f * this.drawScale), this.drawPosition.y - (13.799999f * this.drawScale), 5.1749997f * this.drawScale, 13.799999f * this.drawScale, 10.349999f * this.drawScale, 27.599998f * this.drawScale, 1.0f, 1.0f, this.drawAngle);
        batch.setColor(Color.WHITE);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/projectiles/MultishotProjectile$MultishotProjectileFactory.class */
    public static class MultishotProjectileFactory extends Projectile.Factory<MultishotProjectile> {

        /* renamed from: a, reason: collision with root package name */
        TextureRegion f2631a;

        @Override // com.prineside.tdi2.Projectile.Factory
        protected final /* synthetic */ MultishotProjectile a() {
            return b();
        }

        @Override // com.prineside.tdi2.Projectile.Factory
        public void setupAssets() {
            this.f2631a = Game.i.assetManager.getTextureRegion("projectile-multishot");
            Game.i.assetManager.getTextureRegion("bullet-trace-thin");
        }

        private static MultishotProjectile b() {
            return new MultishotProjectile((byte) 0);
        }
    }
}
