package com.prineside.tdi2.systems;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystem;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.Unit;
import com.prineside.tdi2.configs.GameRenderingOrder;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.LimitedParticleType;
import com.prineside.tdi2.events.game.UnitDie;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.systems.RenderSystem;
import com.prineside.tdi2.tiles.SpawnTile;
import com.prineside.tdi2.units.BallLightningUnit;
import com.prineside.tdi2.units.SnowballUnit;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/UnitSystem.class */
public final class UnitSystem extends GameSystem {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3071a = TLog.forClass(UnitSystem.class);

    /* renamed from: b, reason: collision with root package name */
    private int f3072b = 1;
    public int spawnedSnowballs;
    public int spawnedBallLightnings;
    private float c;

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeVarInt(this.f3072b, true);
        output.writeInt(this.spawnedSnowballs);
        output.writeInt(this.spawnedBallLightnings);
        output.writeFloat(this.c);
    }

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f3072b = input.readVarInt(true);
        this.spawnedSnowballs = input.readInt();
        this.spawnedBallLightnings = input.readInt();
        this.c = input.readFloat();
    }

    @Override // com.prineside.tdi2.GameSystem
    public final boolean affectsGameState() {
        return true;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void setup() {
        if (!this.S.CFG.headless) {
            a();
        }
    }

    public final float getBallLightningAccumulationTime() {
        return this.c;
    }

    private void a() {
        this.S._render.addLayer(new RenderSystem.Layer(GameRenderingOrder.UNIT_DRAW_TILE_LAYER, false, (batch, f, f2, f3) -> {
            drawTileLayer(batch, f2, f3);
        }).setName("Unit-drawTileLayer"));
        this.S._render.addLayer(new RenderSystem.Layer(GameRenderingOrder.UNIT_DRAW_GROUNDED, false, (batch2, f4, f5, f6) -> {
            drawGrounded(batch2, f5, f6);
        }).setName("Unit-drawGrounded"));
        this.S._render.addLayer(new RenderSystem.Layer(GameRenderingOrder.UNIT_DRAW_FLYING, false, (batch3, f7, f8, f9) -> {
            drawFlying(batch3, f8, f9);
        }).setName("Unit-drawFlying"));
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void postStateRestore() {
        a();
    }

    public final boolean preparePathToRandomSpawn(Unit unit, Tile tile) {
        this.S.gameState.checkGameplayUpdateAllowed();
        Array<Tile> tileArray = this.S.TSH.getTileArray();
        tileArray.addAll(this.S.map.getMap().getTilesByType(SpawnTile.class));
        for (int i = tileArray.size - 1; i >= 0; i--) {
            int randomInt = this.S.gameState.randomInt(i + 1);
            SpawnTile spawnTile = (SpawnTile) tileArray.get(i);
            tileArray.set(i, tileArray.get(randomInt));
            tileArray.set(randomInt, spawnTile);
        }
        for (int i2 = 0; i2 < tileArray.size; i2++) {
            Tile tile2 = tileArray.get(i2);
            unit.graphPath = this.S.pathfinding.findPathBetweenTiles(tile, tile2);
            if (unit.graphPath != null) {
                unit.startingTile = tile;
                unit.targetTile = tile2;
                unit.sideShiftIndex = 5;
                this.S.TSH.freeTileArray(tileArray);
                unit.angle = unit.graphPath.getRotation(unit.passedTiles, unit.sideShiftIndex);
                unit.graphPath.getPosition(unit.passedTiles, unit.sideShiftIndex, unit.position);
                unit.applyDrawInterpolation(0.0f);
                return true;
            }
        }
        this.S.TSH.freeTileArray(tileArray);
        return false;
    }

    public final void register(Unit unit) {
        this.S.gameState.checkGameplayUpdateAllowed();
        unit.id = this.f3072b;
        this.f3072b++;
        unit.setRegistered(this.S);
        if (unit instanceof SnowballUnit) {
            this.spawnedSnowballs++;
        } else if (unit instanceof BallLightningUnit) {
            this.spawnedBallLightnings++;
        }
    }

    private void a(Unit unit) {
        this.S.gameState.checkGameplayUpdateAllowed();
        unit.setUnregistered();
        if (unit instanceof SnowballUnit) {
            this.spawnedSnowballs--;
        } else if (unit instanceof BallLightningUnit) {
            this.spawnedBallLightnings--;
        }
    }

    public final void killUnit(Unit unit, Enemy enemy) {
        this.S.gameState.checkGameplayUpdateAllowed();
        if (this.S._particle != null && Game.i.settingsManager.isParticlesDrawing()) {
            ParticleEffectPool.PooledEffect breakParticle = Game.i.unitManager.getFactory(unit.type).getBreakParticle();
            breakParticle.setPosition(unit.position.x, unit.position.y);
            this.S._particle.addLimitedParticle(breakParticle, LimitedParticleType.UNIT_DEAD, unit.position.x, unit.position.y);
        }
        this.S.events.trigger(new UnitDie(unit, enemy));
        this.S.map.despawnUnit(unit);
        a(unit);
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void update(float f) {
        this.S.map.spawnedUnits.begin();
        int i = this.S.map.spawnedUnits.size;
        for (int i2 = 0; i2 < i; i2++) {
            Unit unit = this.S.map.spawnedUnits.items[i2];
            if (unit.staticPosition) {
                unit.update(f);
            } else if (unit.graphPath != null) {
                unit.passedTiles += unit.getPassedTilesDelta(f);
                if (unit.passedTiles >= unit.graphPath.getLengthInTiles()) {
                    killUnit(unit, null);
                } else if (unit.passedTiles < -0.5f) {
                    f3071a.e(unit.passedTiles + " passed tiles", new Object[0]);
                    this.S.map.despawnUnit(unit);
                } else {
                    unit.angle = unit.graphPath.getRotation(unit.passedTiles, unit.sideShiftIndex);
                    unit.graphPath.getPosition(unit.passedTiles, unit.sideShiftIndex, unit.position);
                    unit.update(f);
                }
            }
        }
        this.S.map.spawnedUnits.end();
        this.c = 10.0f * ((float) Math.pow(1.0f + ((float) this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_TESLA_A_PER_BALL_PENALTY)), this.spawnedBallLightnings));
    }

    @Override // com.prineside.tdi2.GameSystem
    public final String getSystemName() {
        return "Unit";
    }

    public final void drawGrounded(Batch batch, float f, float f2) {
        this.S.map.spawnedUnits.begin();
        int i = this.S.map.spawnedUnits.size;
        for (int i2 = 0; i2 < i; i2++) {
            Unit unit = this.S.map.spawnedUnits.items[i2];
            if (unit.getDrawLayer() == 1) {
                unit.applyDrawInterpolation(f2);
                unit.drawBatch(batch, f);
            }
        }
        this.S.map.spawnedUnits.end();
    }

    public final void drawTileLayer(Batch batch, float f, float f2) {
        this.S.map.spawnedUnits.begin();
        int i = this.S.map.spawnedUnits.size;
        for (int i2 = 0; i2 < i; i2++) {
            Unit unit = this.S.map.spawnedUnits.items[i2];
            if (unit.getDrawLayer() == 0) {
                unit.applyDrawInterpolation(f2);
                unit.drawBatch(batch, f);
            }
        }
        this.S.map.spawnedUnits.end();
    }

    public final void drawFlying(Batch batch, float f, float f2) {
        this.S.map.spawnedUnits.begin();
        int i = this.S.map.spawnedUnits.size;
        for (int i2 = 0; i2 < i; i2++) {
            Unit unit = this.S.map.spawnedUnits.items[i2];
            if (unit.getDrawLayer() == 2) {
                unit.applyDrawInterpolation(f2);
                unit.drawBatch(batch, f);
            }
        }
        this.S.map.spawnedUnits.end();
        if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DBG_DRAW_UNITS_BBOX) != 0.0d) {
            batch.end();
            Game.i.renderingManager.shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
            Game.i.renderingManager.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            Game.i.renderingManager.shapeRenderer.setColor(MaterialColor.CYAN.P500.cpy());
            Game.i.renderingManager.shapeRenderer.getColor().f889a = 0.5f;
            int i3 = this.S.map.spawnedUnits.size;
            for (int i4 = 0; i4 < i3; i4++) {
                Unit unit2 = this.S.map.spawnedUnits.items[i4];
                Game.i.renderingManager.shapeRenderer.circle(unit2.position.x, unit2.position.y, unit2.getSize());
            }
            Game.i.renderingManager.shapeRenderer.end();
            batch.begin();
        }
        batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
    }
}
