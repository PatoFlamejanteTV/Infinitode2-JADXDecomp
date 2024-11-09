package com.prineside.tdi2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.enums.UnitType;
import com.prineside.tdi2.pathfinding.Path;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/Unit.class */
public abstract class Unit extends Registrable {
    public static final int LAYER_TILES = 0;
    public static final int LAYER_GROUNDED = 1;
    public static final int LAYER_FLYING = 2;
    public int id;

    @NAGS
    public float drawAngle;
    public UnitType type;
    public Tile startingTile;
    public Tile targetTile;
    public boolean staticPosition;
    public int sideShiftIndex;
    public float passedTiles;
    public Path graphPath;
    public boolean spawned;
    public Vector2 position = new Vector2();
    public float angle = 0.0f;

    @NAGS
    public Vector2 drawPosition = new Vector2();
    public float speed = 1.0f;

    public abstract void drawBatch(Batch batch, float f);

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeVarInt(this.id, true);
        kryo.writeObject(output, this.position);
        output.writeFloat(this.angle);
        kryo.writeObject(output, this.type);
        output.writeFloat(this.speed);
        kryo.writeClassAndObject(output, this.startingTile);
        kryo.writeClassAndObject(output, this.targetTile);
        output.writeBoolean(this.staticPosition);
        output.writeInt(this.sideShiftIndex);
        output.writeFloat(this.passedTiles);
        kryo.writeObjectOrNull(output, this.graphPath, Path.class);
        output.writeBoolean(this.spawned);
    }

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.id = input.readVarInt(true);
        this.position = (Vector2) kryo.readObject(input, Vector2.class);
        this.angle = input.readFloat();
        this.type = (UnitType) kryo.readObject(input, UnitType.class);
        this.speed = input.readFloat();
        this.startingTile = (Tile) kryo.readClassAndObject(input);
        this.targetTile = (Tile) kryo.readClassAndObject(input);
        this.staticPosition = input.readBoolean();
        this.sideShiftIndex = input.readInt();
        this.passedTiles = input.readFloat();
        this.graphPath = (Path) kryo.readObjectOrNull(input, Path.class);
        this.spawned = input.readBoolean();
    }

    private Unit() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Unit(UnitType unitType) {
        this.type = unitType;
    }

    public int getDrawLayer() {
        return 1;
    }

    public float getSize() {
        return 25.6f;
    }

    public void update(float f) {
    }

    public void applyDrawInterpolation(float f) {
        if (f != 0.0f && !this.staticPosition) {
            float passedTilesDelta = this.passedTiles + getPassedTilesDelta(f);
            this.graphPath.getPosition(passedTilesDelta, this.sideShiftIndex, this.drawPosition);
            this.drawAngle = this.graphPath.getRotation(passedTilesDelta, this.sideShiftIndex);
        } else {
            this.drawAngle = this.angle;
            this.drawPosition.set(this.position);
        }
    }

    public final float getPassedTilesDelta(float f) {
        if (this.staticPosition) {
            throw new IllegalStateException("Unit is static");
        }
        return this.graphPath.getPassedTilesDelta(f, this.passedTiles, this.sideShiftIndex, this.speed);
    }

    public void onSpawned() {
    }

    public void onDespawned() {
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/Unit$Factory.class */
    public interface Factory<T extends Unit> extends Disposable, EntityFactory {
        void setup();

        T create();

        Color getColor();

        ParticleEffectPool.PooledEffect getBreakParticle();

        @Override // com.badlogic.gdx.utils.Disposable
        void dispose();

        UnitType getUnitType();

        /* loaded from: infinitode-2.jar:com/prineside/tdi2/Unit$Factory$BasicAbstractFactory.class */
        public static abstract class BasicAbstractFactory<T extends Unit> implements Factory<T> {

            /* renamed from: a, reason: collision with root package name */
            private ParticleEffectPool f1785a;

            public BasicAbstractFactory() {
                if (Game.i.assetManager != null) {
                    ParticleEffect particleEffect = new ParticleEffect();
                    particleEffect.load(Gdx.files.internal("particles/break.prt"), Game.i.assetManager.getTextureRegion("particle-triangle").getAtlas());
                    particleEffect.setEmittersCleanUpBlendFunction(false);
                    particleEffect.getEmitters().get(0).getTint().setColors(new float[]{getColor().r, getColor().g, getColor().f888b});
                    this.f1785a = Game.i.assetManager.getParticleEffectPoolWithTemplate("break.prt@unitType:" + getUnitType().name(), particleEffect);
                }
            }

            @Override // com.prineside.tdi2.Unit.Factory
            public void setup() {
                if (Game.i.assetManager != null) {
                    setupAssets();
                }
            }

            public void setupAssets() {
            }

            @Override // com.prineside.tdi2.Unit.Factory, com.badlogic.gdx.utils.Disposable
            public void dispose() {
            }

            @Override // com.prineside.tdi2.Unit.Factory
            public ParticleEffectPool.PooledEffect getBreakParticle() {
                return this.f1785a.obtain();
            }
        }
    }
}
