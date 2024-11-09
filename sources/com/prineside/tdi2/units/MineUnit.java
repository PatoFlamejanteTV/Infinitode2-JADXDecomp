package com.prineside.tdi2.units;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Explosion;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.Unit;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.enums.UnitType;
import com.prineside.tdi2.tiles.PlatformTile;
import com.prineside.tdi2.utils.FastRandom;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/units/MineUnit.class */
public final class MineUnit extends Unit {
    public Tower owner;
    public Vector2 startPos;
    public Vector2 targetPos;

    @NAGS
    public float rotation;
    public float existsTime;
    public Explosion explosion;

    /* renamed from: a, reason: collision with root package name */
    @NAGS
    private Color f3800a;

    /* synthetic */ MineUnit(byte b2) {
        this();
    }

    @Override // com.prineside.tdi2.Unit, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeClassAndObject(output, this.owner);
        kryo.writeObject(output, this.startPos);
        kryo.writeObject(output, this.targetPos);
        output.writeFloat(this.rotation);
        output.writeFloat(this.existsTime);
        kryo.writeClassAndObject(output, this.explosion);
        kryo.writeObject(output, this.f3800a);
    }

    @Override // com.prineside.tdi2.Unit, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.owner = (Tower) kryo.readClassAndObject(input);
        this.startPos = (Vector2) kryo.readObject(input, Vector2.class);
        this.targetPos = (Vector2) kryo.readObject(input, Vector2.class);
        this.rotation = input.readFloat();
        this.existsTime = input.readFloat();
        this.explosion = (Explosion) kryo.readClassAndObject(input);
        this.f3800a = (Color) kryo.readObject(input, Color.class);
    }

    private MineUnit() {
        super(UnitType.MINE);
        this.startPos = new Vector2();
        this.targetPos = new Vector2();
        this.rotation = Float.MAX_VALUE;
        this.f3800a = new Color();
    }

    public final void setup(Tower tower, float f, float f2, float f3, float f4, Explosion explosion, Color color) {
        this.staticPosition = true;
        this.owner = tower;
        this.position.set(f, f2);
        this.startPos.set(f, f2);
        this.targetPos.set(f3, f4);
        this.explosion = explosion;
        this.f3800a.set(color);
    }

    @Override // com.prineside.tdi2.Unit
    public final void drawBatch(Batch batch, float f) {
        float f2 = 0.5f + (this.existsTime * 2.0f);
        float f3 = f2;
        if (f2 > 1.0f) {
            f3 = 1.0f;
        }
        if (this.rotation == Float.MAX_VALUE) {
            this.rotation = (-20.0f) + (FastRandom.getFloat() * 20.0f);
        }
        batch.setColor(this.f3800a);
        float f4 = f3;
        batch.draw(Game.i.unitManager.F.MINE.f3801a, this.position.x - 24.0f, this.position.y - 24.0f, 24.0f, 24.0f, 48.0f, 48.0f, f4, f4, this.rotation);
        batch.setColor(Color.WHITE);
    }

    private void a() {
        if (this.S == null) {
            throw new IllegalStateException("Unit " + this + " is unregistered");
        }
        this.explosion.position.set(this.position);
        this.S.explosion.register(this.explosion);
        this.explosion.explode();
        this.S.unit.killUnit(this, null);
    }

    @Override // com.prineside.tdi2.Unit
    public final void update(float f) {
        if (this.owner != null && !this.owner.isRegistered()) {
            a();
            return;
        }
        this.existsTime += f;
        if (this.existsTime < 1.0f) {
            this.position.set(this.startPos);
            this.position.lerp(this.targetPos, Interpolation.pow5Out.apply(this.existsTime));
        } else {
            this.position.set(this.targetPos);
        }
        Tile tileByMapPos = this.S.map.getMap().getTileByMapPos(this.position.x, this.position.y);
        if (this.existsTime > 1.0f) {
            if (tileByMapPos == null || (tileByMapPos.type == TileType.PLATFORM && ((PlatformTile) tileByMapPos).building != null)) {
                a();
            } else if (tileByMapPos.enemyCount != 0) {
                this.S.map.getEnemiesInCircleV(this.position, 32.0f, (enemyReference, f2, f3, f4) -> {
                    if (enemyReference.enemy != null && !enemyReference.enemy.isAir()) {
                        a();
                        return false;
                    }
                    return true;
                });
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/units/MineUnit$MineUnitFactory.class */
    public static class MineUnitFactory extends Unit.Factory.BasicAbstractFactory<MineUnit> {

        /* renamed from: a, reason: collision with root package name */
        private TextureRegion f3801a;

        @Override // com.prineside.tdi2.Unit.Factory.BasicAbstractFactory
        public void setupAssets() {
            this.f3801a = Game.i.assetManager.getTextureRegion("mine");
        }

        @Override // com.prineside.tdi2.Unit.Factory
        public MineUnit create() {
            return new MineUnit((byte) 0);
        }

        @Override // com.prineside.tdi2.Unit.Factory
        public Color getColor() {
            return Color.WHITE;
        }

        @Override // com.prineside.tdi2.Unit.Factory
        public UnitType getUnitType() {
            return UnitType.MINE;
        }
    }
}
