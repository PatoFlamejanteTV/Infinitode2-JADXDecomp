package com.prineside.tdi2.units;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ObjectSet;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.Unit;
import com.prineside.tdi2.buffs.SlippingBuff;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.enums.UnitType;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/units/IceFieldUnit.class */
public final class IceFieldUnit extends Unit {
    public float lifetimeLeft;
    public int touchesLeft;
    public ObjectSet<Enemy.EnemyReference> affectedEnemies;

    /* synthetic */ IceFieldUnit(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Unit, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.lifetimeLeft);
        output.writeVarInt(this.touchesLeft, true);
        kryo.writeObject(output, this.affectedEnemies);
    }

    @Override // com.prineside.tdi2.Unit, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.lifetimeLeft = input.readFloat();
        this.touchesLeft = input.readVarInt(true);
        this.affectedEnemies = (ObjectSet) kryo.readObject(input, ObjectSet.class);
    }

    private IceFieldUnit() {
        super(UnitType.ICE_FIELD);
        this.affectedEnemies = new ObjectSet<>();
    }

    public final void setup(float f, float f2, float f3, int i) {
        this.staticPosition = true;
        this.touchesLeft = i;
        this.position.set(f, f2);
        this.lifetimeLeft = f3;
    }

    @Override // com.prineside.tdi2.Unit
    public final int getDrawLayer() {
        return 0;
    }

    @Override // com.prineside.tdi2.Unit
    public final void drawBatch(Batch batch, float f) {
        batch.setColor(Color.WHITE);
        batch.draw(Game.i.unitManager.F.ICE_FIELD.f3795a, this.position.x - 75.0f, this.position.y - 75.0f, 75.0f, 75.0f, 150.0f, 150.0f, 1.0f, 1.0f, 0.0f);
    }

    @Override // com.prineside.tdi2.Unit
    public final void update(float f) {
        this.lifetimeLeft -= f;
        if (this.lifetimeLeft <= 0.0f) {
            this.S.unit.killUnit(this, null);
            return;
        }
        Tile tileByMapPos = this.S.map.getMap().getTileByMapPos(this.position.x, this.position.y);
        if (tileByMapPos != null && tileByMapPos.enemyCount != 0) {
            this.S.map.getEnemiesInRect(this.position.x - 64.0f, this.position.y - 64.0f, this.position.x + 64.0f, this.position.y + 64.0f, (enemyReference, f2, f3, f4) -> {
                if (enemyReference.enemy == null) {
                    return true;
                }
                Enemy enemy = enemyReference.enemy;
                if (!enemy.isAir()) {
                    if (!enemy.hasBuffsByType(BuffType.SLIPPING)) {
                        SlippingBuff slippingBuff = new SlippingBuff();
                        slippingBuff.setup(0.21f, 1.0f);
                        this.S.buff.P_SLIPPING.addBuff(enemy, slippingBuff);
                    }
                    if (this.touchesLeft > 0 && !this.affectedEnemies.contains(enemyReference)) {
                        this.affectedEnemies.add(enemyReference);
                        this.touchesLeft--;
                        if (this.touchesLeft == 0) {
                            this.lifetimeLeft = Math.min(this.lifetimeLeft, 5.0f);
                            return false;
                        }
                        return true;
                    }
                    return true;
                }
                return true;
            });
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/units/IceFieldUnit$IceFieldUnitFactory.class */
    public static class IceFieldUnitFactory extends Unit.Factory.BasicAbstractFactory<IceFieldUnit> {

        /* renamed from: a, reason: collision with root package name */
        private TextureRegion f3795a;

        @Override // com.prineside.tdi2.Unit.Factory.BasicAbstractFactory
        public void setupAssets() {
            this.f3795a = Game.i.assetManager.getTextureRegion("tile-ice-overlay");
        }

        @Override // com.prineside.tdi2.Unit.Factory
        public IceFieldUnit create() {
            return new IceFieldUnit((byte) 0);
        }

        @Override // com.prineside.tdi2.Unit.Factory
        public Color getColor() {
            return Color.WHITE;
        }

        @Override // com.prineside.tdi2.Unit.Factory
        public UnitType getUnitType() {
            return UnitType.ICE_FIELD;
        }
    }
}
