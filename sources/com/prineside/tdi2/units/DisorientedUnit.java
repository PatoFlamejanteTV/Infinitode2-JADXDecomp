package com.prineside.tdi2.units;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.Unit;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.enums.UnitType;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/units/DisorientedUnit.class */
public final class DisorientedUnit extends Unit {

    /* renamed from: a, reason: collision with root package name */
    @Null
    private Tower f3793a;

    /* renamed from: b, reason: collision with root package name */
    private float f3794b;
    private float c;
    public float coinsPerTilePassed;
    public int maxSumCoins;
    private int d;
    private float e;
    private float f;
    private float g;
    private EnemyType h;

    @NAGS
    private TextureRegion i;

    /* synthetic */ DisorientedUnit(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Unit, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeClassAndObject(output, this.f3793a);
        output.writeFloat(this.f3794b);
        output.writeFloat(this.c);
        output.writeFloat(this.coinsPerTilePassed);
        output.writeVarInt(this.maxSumCoins, true);
        output.writeVarInt(this.d, false);
        output.writeFloat(this.e);
        output.writeFloat(this.f);
        output.writeFloat(this.g);
        kryo.writeObject(output, this.h);
    }

    @Override // com.prineside.tdi2.Unit, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f3793a = (Tower) kryo.readClassAndObject(input);
        this.f3794b = input.readFloat();
        this.c = input.readFloat();
        this.coinsPerTilePassed = input.readFloat();
        this.maxSumCoins = input.readVarInt(true);
        this.d = input.readVarInt(false);
        this.e = input.readFloat();
        this.f = input.readFloat();
        this.g = input.readFloat();
        this.h = (EnemyType) kryo.readObject(input, EnemyType.class);
    }

    private DisorientedUnit() {
        super(UnitType.DISORIENTED);
        this.d = -1;
    }

    @Null
    public final Tower getSpawnedByTower() {
        return this.f3793a;
    }

    public final void setup(Tower tower, EnemyType enemyType, float f, float f2) {
        if (f <= 0.0f) {
            throw new IllegalArgumentException("health is " + f);
        }
        if (f2 <= 0.0f) {
            throw new IllegalArgumentException("maxHealth is " + f2);
        }
        this.f3793a = tower;
        this.h = enemyType;
        this.f3794b = f;
        this.c = f2;
    }

    private void a(Batch batch) {
        batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
        float f = this.f3794b / this.c;
        batch.setColor(MaterialColor.PURPLE.P900);
        batch.draw(Game.i.assetManager.getBlankWhiteTextureRegion(), this.drawPosition.x - 28.0f, this.drawPosition.y + 52.0f, 56.0f, 8.0f);
        batch.setColor(MaterialColor.PURPLE.P300);
        batch.draw(Game.i.assetManager.getBlankWhiteTextureRegion(), this.drawPosition.x - 26.0f, this.drawPosition.y + 54.0f, (int) (52.0f * f), 4.0f);
        batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
    }

    @Override // com.prineside.tdi2.Unit
    public final void drawBatch(Batch batch, float f) {
        if (this.i == null) {
            this.i = this.S.enemy.getTexture(this.h);
        }
        float regionWidth = this.i.getRegionWidth();
        batch.setColor(MaterialColor.PURPLE.P100);
        batch.draw(this.i, this.drawPosition.x - (regionWidth * 0.5f), this.drawPosition.y - (regionWidth * 0.5f), regionWidth * 0.5f, regionWidth * 0.5f, regionWidth, regionWidth, 1.0f, 1.0f, this.drawAngle);
        a(batch);
    }

    public final void setCoinsPerTilePassed(float f, int i) {
        this.coinsPerTilePassed = f;
        this.maxSumCoins = i;
        int lengthInTiles = this.graphPath.getLengthInTiles();
        this.e = 0.5f;
        for (float f2 = 0.5f; f2 < 0.98f; f2 += 0.01f) {
            int i2 = 0;
            for (int i3 = 0; i3 < lengthInTiles; i3++) {
                int pow = (int) (f * ((float) Math.pow(this.e, i3)));
                int i4 = pow;
                if (pow <= 0) {
                    i4 = 1;
                }
                i2 += i4;
            }
            if (i2 <= i) {
                this.e = f2;
            } else {
                return;
            }
        }
    }

    @Override // com.prineside.tdi2.Unit
    public final void update(float f) {
        if (this.coinsPerTilePassed != 0.0f) {
            int i = (int) this.passedTiles;
            if (this.d != i) {
                float f2 = 1.0f;
                for (int i2 = 0; i2 < i; i2++) {
                    f2 *= this.e;
                }
                int i3 = (int) (this.coinsPerTilePassed * f2);
                int i4 = i3;
                if (i3 <= 0) {
                    i4 = 1;
                }
                this.f = 1.0f / i4;
                this.g = i;
                this.d = i;
            }
            int i5 = 0;
            while (this.passedTiles >= this.g) {
                this.S.gameState.addMoney(1.0f, true);
                this.S.statistics.addStatistic(StatisticsType.CG_U, 1.0d);
                if (this.f3793a != null) {
                    this.f3793a.bonusCoinsBrought += 1.0f;
                }
                i5++;
                this.g += this.f;
            }
            if (i5 > 0 && this.S._particle != null) {
                this.S._particle.addCoinParticle(this.drawPosition.x, this.drawPosition.y, i5);
            }
        }
        this.S.map.getEnemiesInCircleV(this.position, 32.0f, (enemyReference, f3, f4, f5) -> {
            Enemy enemy = enemyReference.enemy;
            if (enemy == null || enemy.disabled.isTrue()) {
                return true;
            }
            if (this.f3793a != null && !enemy.canBeAttackedBy(this.f3793a)) {
                return true;
            }
            float buffedDamageMultiplier = enemy.getBuffedDamageMultiplier(this.f3793a == null ? null : this.f3793a.type, DamageType.BULLET);
            if (buffedDamageMultiplier <= 0.0f) {
                return true;
            }
            float health = enemy.getHealth();
            if (this.f3794b * buffedDamageMultiplier <= health) {
                if (this.f3794b >= 0.0f) {
                    this.S.damage.queueDamage(this.S.damage.obtainRecord().setup(enemy, this.f3794b, DamageType.BULLET).setTower(this.f3793a).setCleanForDps(false));
                    this.S.unit.killUnit(this, enemy);
                    return false;
                }
                throw new IllegalArgumentException("health is " + this.f3794b);
            }
            this.S.damage.queueDamage(this.S.damage.obtainRecord().setup(enemy, health + 0.001f, DamageType.BULLET).setTower(this.f3793a).setCleanForDps(false).setLethal(true).setIgnoreTowerEfficiency(true).setUnit(this));
            this.f3794b -= (health / buffedDamageMultiplier) + 2.0f;
            if (this.f3794b <= 0.0f) {
                this.S.unit.killUnit(this, enemy);
                return false;
            }
            return false;
        });
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/units/DisorientedUnit$DisorientedUnitFactory.class */
    public static class DisorientedUnitFactory extends Unit.Factory.BasicAbstractFactory<DisorientedUnit> {
        @Override // com.prineside.tdi2.Unit.Factory.BasicAbstractFactory
        public void setupAssets() {
        }

        @Override // com.prineside.tdi2.Unit.Factory
        public DisorientedUnit create() {
            return new DisorientedUnit((byte) 0);
        }

        @Override // com.prineside.tdi2.Unit.Factory
        public Color getColor() {
            return Color.WHITE;
        }

        @Override // com.prineside.tdi2.Unit.Factory
        public UnitType getUnitType() {
            return UnitType.DISORIENTED;
        }
    }
}
