package com.prineside.tdi2.units;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.ResourcePack;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.Unit;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.UnitType;
import com.prineside.tdi2.projectiles.ChainLightningProjectile;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/units/BallLightningUnit.class */
public final class BallLightningUnit extends Unit {

    /* renamed from: a, reason: collision with root package name */
    private float f3790a;

    /* renamed from: b, reason: collision with root package name */
    private Tower f3791b;
    private float c;

    /* synthetic */ BallLightningUnit(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Unit, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.f3790a);
        kryo.writeClassAndObject(output, this.f3791b);
        output.writeFloat(this.c);
    }

    @Override // com.prineside.tdi2.Unit, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f3790a = input.readFloat();
        this.f3791b = (Tower) kryo.readClassAndObject(input);
        this.c = input.readFloat();
    }

    private BallLightningUnit() {
        super(UnitType.BALL_LIGHTNING);
    }

    public final void setup(Tower tower, float f) {
        this.f3791b = tower;
        this.c = f;
    }

    @Override // com.prineside.tdi2.Unit
    public final int getDrawLayer() {
        return 2;
    }

    @Override // com.prineside.tdi2.Unit
    public final void drawBatch(Batch batch, float f) {
        batch.draw(Game.i.unitManager.F.BALL_LIGHTNING.f3792a.getKeyFrame(this.f3790a, true), this.drawPosition.x - 32.0f, this.drawPosition.y - 32.0f, 32.0f, 32.0f, 64.0f, 64.0f, 1.0f, 1.0f, 0.0f);
    }

    @Override // com.prineside.tdi2.Unit
    public final void update(float f) {
        this.f3790a += f;
        this.S.map.getEnemiesInCircleV(this.position, 32.0f, (enemyReference, f2, f3, f4) -> {
            Enemy enemy = enemyReference.enemy;
            if (enemy == null || !enemy.isVulnerableTo(DamageType.ELECTRICITY)) {
                return true;
            }
            ChainLightningProjectile obtain = this.S.projectile.F.CHAIN_LIGHTNING.obtain();
            this.S.projectile.register(obtain);
            obtain.setup(this.f3791b, enemy, this.c, this.c * 0.1f, 0.9f, 16.0f, this.position);
            this.S.unit.killUnit(this, enemy);
            return false;
        });
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/units/BallLightningUnit$BallLightningUnitFactory.class */
    public static class BallLightningUnitFactory extends Unit.Factory.BasicAbstractFactory<BallLightningUnit> {

        /* renamed from: a, reason: collision with root package name */
        Animation<ResourcePack.AtlasTextureRegion> f3792a;

        @Override // com.prineside.tdi2.Unit.Factory.BasicAbstractFactory
        public void setupAssets() {
            this.f3792a = new Animation<>(0.05f, Game.i.assetManager.getTextureRegions("unit-type-ball-lightning"));
        }

        public Animation<ResourcePack.AtlasTextureRegion> getBallAnimation() {
            return this.f3792a;
        }

        @Override // com.prineside.tdi2.Unit.Factory
        public BallLightningUnit create() {
            return new BallLightningUnit((byte) 0);
        }

        @Override // com.prineside.tdi2.Unit.Factory
        public Color getColor() {
            return Color.WHITE;
        }

        @Override // com.prineside.tdi2.Unit.Factory
        public UnitType getUnitType() {
            return UnitType.BALL_LIGHTNING;
        }
    }
}
