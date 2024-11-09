package com.prineside.tdi2.units;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.Unit;
import com.prineside.tdi2.buffs.SnowballBuff;
import com.prineside.tdi2.buffs.processors.SnowballBuffProcessor;
import com.prineside.tdi2.enums.UnitType;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/units/SnowballUnit.class */
public final class SnowballUnit extends Unit {

    /* renamed from: a, reason: collision with root package name */
    private float f3802a;

    /* renamed from: b, reason: collision with root package name */
    private Tower f3803b;

    @NAGS
    private ParticleEffectPool.PooledEffect c;

    /* synthetic */ SnowballUnit(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Unit, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.f3802a);
        kryo.writeClassAndObject(output, this.f3803b);
    }

    @Override // com.prineside.tdi2.Unit, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f3802a = input.readFloat();
        this.f3803b = (Tower) kryo.readClassAndObject(input);
    }

    private SnowballUnit() {
        super(UnitType.SNOWBALL);
    }

    public final void setup(Tower tower, float f) {
        this.f3803b = tower;
        this.f3802a = f;
    }

    @Override // com.prineside.tdi2.Unit
    public final int getDrawLayer() {
        return 2;
    }

    @Override // com.prineside.tdi2.Unit
    public final void onSpawned() {
        super.onSpawned();
        if (this.S._particle != null && Game.i.settingsManager.isParticlesDrawing()) {
            this.c = Game.i.unitManager.F.SNOWBALL.f3805b.obtain();
            this.c.setPosition(this.position.x, this.position.y);
            this.S._particle.addParticle(this.c, false);
        }
    }

    @Override // com.prineside.tdi2.Registrable
    public final void setUnregistered() {
        if (this.c != null) {
            this.c.allowCompletion();
            this.c = null;
        }
    }

    @Override // com.prineside.tdi2.Unit
    public final void drawBatch(Batch batch, float f) {
        if (this.c != null) {
            this.c.setPosition(this.drawPosition.x, this.drawPosition.y);
        }
        batch.draw(Game.i.unitManager.F.SNOWBALL.f3804a, this.drawPosition.x - 32.0f, this.drawPosition.y - 32.0f, 32.0f, 32.0f, 64.0f, 64.0f, 1.0f, 1.0f, 0.0f);
    }

    @Override // com.prineside.tdi2.Unit
    public final void update(float f) {
        this.S.map.getEnemiesInRect(this.position.x - 16.0f, this.position.y - 16.0f, this.position.x + 16.0f, this.position.y + 16.0f, (enemyReference, f2, f3, f4) -> {
            Enemy enemy = enemyReference.enemy;
            if (enemy != null && enemy.buffSnowballHits < SnowballBuffProcessor.MAX_HITS_ONE_ENEMY && PMath.circleIntersectsCircle(enemy.getPosition().x, enemy.getPosition().y, enemy.getSize(), this.position.x, this.position.y, 32.0f)) {
                SnowballBuff snowballBuff = new SnowballBuff();
                float f2 = this.f3802a * SnowballBuffProcessor.STUN_DURATION_BY_STUN_COUNT[enemy.buffSnowballHits];
                snowballBuff.setup(f2, f2 * 10.0f);
                if (this.S.buff.P_SNOWBALL.addBuff(enemy, snowballBuff)) {
                    this.S.unit.killUnit(this, enemy);
                    return false;
                }
                return true;
            }
            return true;
        });
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/units/SnowballUnit$SnowballUnitFactory.class */
    public static class SnowballUnitFactory extends Unit.Factory.BasicAbstractFactory<SnowballUnit> {

        /* renamed from: a, reason: collision with root package name */
        TextureRegion f3804a;

        /* renamed from: b, reason: collision with root package name */
        ParticleEffectPool f3805b;

        @Override // com.prineside.tdi2.Unit.Factory.BasicAbstractFactory
        public void setupAssets() {
            this.f3804a = Game.i.assetManager.getTextureRegion("unit-type-snowball");
            this.f3805b = Game.i.assetManager.getParticleEffectPool("snowflakes-trace.prt");
        }

        @Override // com.prineside.tdi2.Unit.Factory
        public SnowballUnit create() {
            return new SnowballUnit((byte) 0);
        }

        @Override // com.prineside.tdi2.Unit.Factory
        public Color getColor() {
            return MaterialColor.LIGHT_BLUE.P500;
        }

        @Override // com.prineside.tdi2.Unit.Factory
        public UnitType getUnitType() {
            return UnitType.SNOWBALL;
        }
    }
}
