package com.prineside.tdi2.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Action;
import com.prineside.tdi2.Building;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystem;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Gate;
import com.prineside.tdi2.Map;
import com.prineside.tdi2.Miner;
import com.prineside.tdi2.Modifier;
import com.prineside.tdi2.SerializableListener;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.Unit;
import com.prineside.tdi2.Wave;
import com.prineside.tdi2.actions.CoreUpgradeAction;
import com.prineside.tdi2.configs.GameRenderingOrder;
import com.prineside.tdi2.enums.ActionType;
import com.prineside.tdi2.enums.BuildingType;
import com.prineside.tdi2.enums.ShapeType;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.BuildingRemove;
import com.prineside.tdi2.events.game.CoreTileLevelUp;
import com.prineside.tdi2.events.game.CoreTileUpgradeInstall;
import com.prineside.tdi2.events.game.EnemyDespawn;
import com.prineside.tdi2.events.game.EnemySpawn;
import com.prineside.tdi2.events.game.GameValuesRecalculate;
import com.prineside.tdi2.events.game.GateChange;
import com.prineside.tdi2.events.game.MapSizeChange;
import com.prineside.tdi2.events.game.MinerPlace;
import com.prineside.tdi2.events.game.MinerRemove;
import com.prineside.tdi2.events.game.ModifierPlace;
import com.prineside.tdi2.events.game.NextWaveForce;
import com.prineside.tdi2.events.game.TileChange;
import com.prineside.tdi2.events.game.TowerPlace;
import com.prineside.tdi2.events.game.UnitDespawn;
import com.prineside.tdi2.events.game.UnitSpawn;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.shapes.RangeCircle;
import com.prineside.tdi2.systems.RenderSystem;
import com.prineside.tdi2.systems.StateSystem;
import com.prineside.tdi2.tiles.BossTile;
import com.prineside.tdi2.tiles.CoreTile;
import com.prineside.tdi2.tiles.PlatformTile;
import com.prineside.tdi2.tiles.SourceTile;
import com.prineside.tdi2.tiles.SpawnTile;
import com.prineside.tdi2.utils.AABB;
import com.prineside.tdi2.utils.AABBCounter;
import com.prineside.tdi2.utils.DrawUtils;
import com.prineside.tdi2.utils.EntityUtils;
import com.prineside.tdi2.utils.FloatSorter;
import com.prineside.tdi2.utils.Intersector;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.ObjectFilter;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.WaveBossSupplier;
import com.prineside.tdi2.utils.logging.TLog;
import java.util.Arrays;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/MapSystem.class */
public final class MapSystem extends GameSystem {

    @NAGS
    private float e;

    @NAGS
    private RangeCircle f;

    @NAGS
    private float h;
    private Map i;
    private boolean j;
    private boolean k;
    private boolean[][] l;
    private short[][] m;
    private boolean n;

    @NAGS
    private AABB<Enemy.EnemyReference> p;

    @NAGS
    private AABBCounter q;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2989a = TLog.forClass(MapSystem.class);

    /* renamed from: b, reason: collision with root package name */
    private static final Color f2990b = new Color(1286557730);
    public static final Color TOWER_RANGE_SELECTED_COLOR = new Color(12375074);
    public static final Color TOWER_RANGE_HOVER_COLOR = new Color(-239);
    private static final Vector2 w = new Vector2();
    private static final Vector2 x = new Vector2();
    private static final Vector2 y = new Vector2(128.0f, 128.0f);

    @NAGS
    private final Array<Tile> c = new Array<>();

    @NAGS
    private final Array<Gate> d = new Array<>();

    @NAGS
    public boolean drawPathTraces = true;

    @NAGS
    private boolean g = false;
    public DelayedRemovalArray<Enemy.EnemyReference> spawnedEnemies = new DelayedRemovalArray<>(false, 8, Enemy.EnemyReference.class);
    public DelayedRemovalArray<Unit> spawnedUnits = new DelayedRemovalArray<>(false, 8, Unit.class);

    @NAGS
    private final AABB.Factory<Enemy.EnemyReference> o = new AABB.Factory<>(Enemy.EnemyReference.class, 102.4f);

    @NAGS
    private final RayCastSortedFilter r = new RayCastSortedFilter(0);

    @NAGS
    private final RayCastSortedRetriever s = new RayCastSortedRetriever(new FloatSorter(), 0);

    @NAGS
    private final DelayedRemovalArray<RCD> t = new DelayedRemovalArray<>(true, 1, RCD.class);

    @NAGS
    private final DelayedRemovalArray<COLD_Circle> u = new DelayedRemovalArray<>(true, 1, COLD_Circle.class);

    @NAGS
    private final DelayedRemovalArray<DBG_DirtyTile> v = new DelayedRemovalArray<>(true, 1, DBG_DirtyTile.class);

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObject(output, this.spawnedEnemies);
        kryo.writeObject(output, this.spawnedUnits);
        kryo.writeObjectOrNull(output, this.i, Map.class);
        output.writeBoolean(this.j);
        output.writeBoolean(this.k);
        kryo.writeObject(output, this.l);
        kryo.writeObject(output, this.m);
        output.writeBoolean(this.n);
    }

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.spawnedEnemies = (DelayedRemovalArray) kryo.readObject(input, DelayedRemovalArray.class);
        this.spawnedUnits = (DelayedRemovalArray) kryo.readObject(input, DelayedRemovalArray.class);
        this.i = (Map) kryo.readObjectOrNull(input, Map.class);
        this.j = input.readBoolean();
        this.k = input.readBoolean();
        this.l = (boolean[][]) kryo.readObject(input, boolean[][].class);
        this.m = (short[][]) kryo.readObject(input, short[][].class);
        this.n = input.readBoolean();
    }

    @Override // com.prineside.tdi2.GameSystem
    public final boolean affectsGameState() {
        return true;
    }

    public final boolean aabbGenerated() {
        return this.p != null;
    }

    private void a() {
        if (this.i == null) {
            return;
        }
        if (this.S == null) {
            throw new IllegalStateException("System is not registered yet");
        }
        if (this.S.gameState != null) {
            this.S.gameState.checkGameplayUpdateAllowed();
        }
        DelayedRemovalArray<Tile> allTiles = this.i.getAllTiles();
        int i = allTiles.size;
        for (int i2 = 0; i2 < i; i2++) {
            allTiles.items[i2].setRegistered(this.S);
        }
        DelayedRemovalArray<Gate> allGates = this.i.getAllGates();
        for (int i3 = 0; i3 < allGates.size; i3++) {
            allGates.items[i3].setRegistered(this.S);
        }
    }

    private void b() {
        if (this.i == null) {
            return;
        }
        DelayedRemovalArray<Tile> allTiles = this.i.getAllTiles();
        int i = allTiles.size;
        for (int i2 = 0; i2 < i; i2++) {
            allTiles.items[i2].setUnregistered();
        }
        DelayedRemovalArray<Gate> allGates = this.i.getAllGates();
        for (int i3 = 0; i3 < allGates.size; i3++) {
            allGates.items[i3].setUnregistered();
        }
    }

    public final Map getMap() {
        return this.i;
    }

    public final void getTileAndNeighbours(int i, int i2, Array<Tile> array) {
        for (int i3 = -1; i3 <= 1; i3++) {
            for (int i4 = -1; i4 <= 1; i4++) {
                Tile tile = this.i.getTile(i + i3, i2 + i4);
                if (tile != null) {
                    array.add(tile);
                }
            }
        }
    }

    public final void setMap(Map map) {
        if (this.i != null) {
            for (int i = 0; i < map.getHeight(); i++) {
                for (int i2 = 0; i2 < map.getWidth(); i2++) {
                    Tile tile = map.getTile(i2, i);
                    if (tile != null && tile.isRegistered()) {
                        tile.setUnregistered();
                    }
                }
            }
        }
        this.i = map;
        this.l = new boolean[map.getHeight()][map.getWidth()];
        this.m = new short[map.getHeight()][map.getWidth()];
        a();
        if (this.j) {
            d();
        } else {
            this.k = true;
        }
        markAllTilesDirty();
    }

    public final void setMapSize(int i, int i2) {
        if (this.i.getWidth() == i && this.i.getHeight() == i2) {
            return;
        }
        this.i.setSize(i, i2);
        this.l = new boolean[this.i.getHeight()][this.i.getWidth()];
        this.m = new short[this.i.getHeight()][this.i.getWidth()];
        markAllTilesDirty();
        this.S.events.trigger(new MapSizeChange());
    }

    public final short getDirtyTileGeneration(Tile tile) {
        return getDirtyTileGenerationAt(tile.getX(), tile.getY());
    }

    public final short getDirtyTileGenerationAt(int i, int i2) {
        if (i < 0 || i2 < 0 || i >= this.i.getWidth() || i2 >= this.i.getHeight()) {
            return (short) 0;
        }
        return this.m[i2][i];
    }

    public final void updateDirtyTiles() {
        if (this.l.length != this.i.getHeight() || this.l[0].length != this.i.getWidth()) {
            f2989a.i("map size changed, updating all tiles", new Object[0]);
            this.l = new boolean[this.i.getHeight()][this.i.getWidth()];
            this.m = new short[this.i.getHeight()][this.i.getWidth()];
            markAllTilesDirty();
        }
        boolean z = !Config.isHeadless() && g();
        for (int i = 0; i < this.i.getHeight(); i++) {
            for (int i2 = 0; i2 < this.i.getWidth(); i2++) {
                if (this.l[i][i2]) {
                    Tile tile = this.i.getTile(i2, i);
                    this.l[i][i2] = false;
                    short[] sArr = this.m[i];
                    int i3 = i2;
                    sArr[i3] = (short) (sArr[i3] + 1);
                    if (tile != null) {
                        tile.updateCache();
                        if (z) {
                            DBG_DirtyTile dBG_DirtyTile = new DBG_DirtyTile((byte) 0);
                            dBG_DirtyTile.f2995b = tile.getX();
                            dBG_DirtyTile.c = tile.getY();
                            this.v.add(dBG_DirtyTile);
                        }
                    }
                }
            }
        }
    }

    public final void markAllTilesDirty() {
        if (this.S.gameState != null) {
            this.S.gameState.checkGameplayUpdateAllowed();
            for (boolean[] zArr : this.l) {
                Arrays.fill(zArr, true);
            }
        }
    }

    public final void markTilesDirtyNearTile(Tile tile, int i) {
        markTilesDirty(tile.getX(), tile.getY(), i);
    }

    public final void markTilesDirty(int i, int i2, int i3) {
        for (int i4 = i2 - i3; i4 <= i2 + i3; i4++) {
            for (int i5 = i - i3; i5 <= i + i3; i5++) {
                if (this.i.getTile(i5, i4) != null) {
                    this.l[i4][i5] = true;
                }
            }
        }
    }

    private void c() {
        Tile tileByMapPos;
        if (this.q == null) {
            this.q = new AABBCounter(42.24f);
        }
        DelayedRemovalArray<Tile> allTiles = this.i.getAllTiles();
        for (int i = 0; i < allTiles.size; i++) {
            allTiles.get(i).enemyCount = 0;
            allTiles.get(i).towerDisablingEnemyCount = 0;
        }
        this.o.reset();
        this.q.reset();
        int i2 = this.spawnedEnemies.size;
        for (int i3 = 0; i3 < i2; i3++) {
            Enemy.EnemyReference enemyReference = this.spawnedEnemies.items[i3];
            Enemy enemy = enemyReference.enemy;
            if (enemy != null) {
                Vector2 position = enemy.getPosition();
                this.o.add(enemyReference, position.x, position.y, enemy.getSize());
                this.q.add(position.x, position.y, 64.0f);
                if (!enemy.disabled.isTrue() && (tileByMapPos = this.i.getTileByMapPos(position.x, position.y)) != null) {
                    tileByMapPos.enemyCount++;
                    if (!enemy.doesNotDisableTowers) {
                        tileByMapPos.towerDisablingEnemyCount++;
                    }
                }
            }
        }
        this.p = this.o.bake(this.p);
        this.q.bake();
        int i4 = this.spawnedEnemies.size;
        for (int i5 = 0; i5 < i4; i5++) {
            Enemy enemy2 = this.spawnedEnemies.items[i5].enemy;
            if (enemy2 != null) {
                enemy2.otherEnemiesNearby = this.q.getEntityCount(enemy2.getPosition().x, enemy2.getPosition().y);
                enemy2.otherEnemiesNearby--;
                if (enemy2.otherEnemiesNearby < 0) {
                    enemy2.otherEnemiesNearby = 0;
                }
            }
        }
    }

    public final void getEnemiesInRect(float f, float f2, float f3, float f4, AABB.EntryRetriever<Enemy.EnemyReference> entryRetriever) {
        if (!this.p.entriesExistInRect(f, f2, f3, f4)) {
            return;
        }
        this.p.traverseEntriesInRect(f, f2, f3, f4, (f5, f6, f7) -> {
            return f5 + f7 > f && f6 + f7 > f2 && f5 - f7 < f3 && f6 - f7 < f4;
        }, entryRetriever);
    }

    public final void getEnemiesInRectFiltered(float f, float f2, float f3, float f4, AABB.EntryFilter entryFilter, AABB.EntryRetriever<Enemy.EnemyReference> entryRetriever) {
        if (!this.p.entriesExistInRect(f, f2, f3, f4)) {
            return;
        }
        this.p.traverseEntriesInRect(f, f2, f3, f4, (f5, f6, f7) -> {
            return f5 + f7 > f && f6 + f7 > f2 && f5 - f7 < f3 && f6 - f7 < f4 && entryFilter.test(f5, f6, f7);
        }, entryRetriever);
    }

    public final void getEnemiesInRectV(Vector2 vector2, Vector2 vector22, AABB.EntryRetriever<Enemy.EnemyReference> entryRetriever) {
        getEnemiesInRect(vector2.x, vector2.y, vector22.x, vector22.y, entryRetriever);
    }

    public final void getEnemiesInCircleFiltered(float f, float f2, float f3, AABB.EntryFilter entryFilter, AABB.EntryRetriever<Enemy.EnemyReference> entryRetriever) {
        float f4 = f - f3;
        float f5 = f2 - f3;
        float f6 = f + f3;
        float f7 = f2 + f3;
        if (!this.p.entriesExistInRect(f4, f5, f6, f7)) {
            return;
        }
        if (h()) {
            COLD_Circle cOLD_Circle = new COLD_Circle((byte) 0);
            cOLD_Circle.f2993b = f;
            cOLD_Circle.c = f2;
            cOLD_Circle.d = f3;
            cOLD_Circle.e = MaterialColor.CYAN.P500;
            this.u.add(cOLD_Circle);
            this.p.traverseEntriesInRect(f4, f5, f6, f7, (f8, f9, f10) -> {
                return PMath.circleIntersectsCircle(f, f2, f3, f8, f9, f10) && entryFilter.test(f8, f9, f10);
            }, (enemyReference, f11, f12, f13) -> {
                cOLD_Circle.f.add(f11, f12, f13);
                return entryRetriever.retrieve(enemyReference, f11, f12, f13);
            });
            return;
        }
        this.p.traverseEntriesInRect(f4, f5, f6, f7, (f14, f15, f16) -> {
            return PMath.circleIntersectsCircle(f, f2, f3, f14, f15, f16) && entryFilter.test(f14, f15, f16);
        }, entryRetriever);
    }

    public final void getEnemiesInCircle(float f, float f2, float f3, AABB.EntryRetriever<Enemy.EnemyReference> entryRetriever) {
        float f4 = f - f3;
        float f5 = f2 - f3;
        float f6 = f + f3;
        float f7 = f2 + f3;
        if (!this.p.entriesExistInRect(f4, f5, f6, f7)) {
            return;
        }
        if (h()) {
            COLD_Circle cOLD_Circle = new COLD_Circle((byte) 0);
            cOLD_Circle.f2993b = f;
            cOLD_Circle.c = f2;
            cOLD_Circle.d = f3;
            cOLD_Circle.e = MaterialColor.CYAN.P500;
            this.u.add(cOLD_Circle);
            this.p.traverseEntriesInRect(f4, f5, f6, f7, (f8, f9, f10) -> {
                return PMath.circleIntersectsCircle(f, f2, f3, f8, f9, f10);
            }, (enemyReference, f11, f12, f13) -> {
                cOLD_Circle.f.add(f11, f12, f13);
                return entryRetriever.retrieve(enemyReference, f11, f12, f13);
            });
            return;
        }
        this.p.traverseEntriesInRect(f4, f5, f6, f7, (f14, f15, f16) -> {
            return PMath.circleIntersectsCircle(f, f2, f3, f14, f15, f16);
        }, entryRetriever);
    }

    public final void getEnemiesInCircleV(Vector2 vector2, float f, AABB.EntryRetriever<Enemy.EnemyReference> entryRetriever) {
        getEnemiesInCircle(vector2.x, vector2.y, f, entryRetriever);
    }

    private void d() {
        for (int i = 0; i < this.i.getHeight(); i++) {
            for (int i2 = 0; i2 < this.i.getWidth(); i2++) {
                Tile tile = this.i.getTile(i2, i);
                if (!(tile instanceof PlatformTile)) {
                    if (tile instanceof SourceTile) {
                        SourceTile sourceTile = (SourceTile) tile;
                        if (sourceTile.miner != null) {
                            Miner miner = sourceTile.miner;
                            miner.setTile(null);
                            sourceTile.miner = null;
                            this.S.miner.register(miner);
                            miner.setInstallTime(0.001f);
                            setMiner(sourceTile.getX(), sourceTile.getY(), miner);
                        }
                    }
                } else {
                    PlatformTile platformTile = (PlatformTile) tile;
                    if (platformTile.building != null && platformTile.building.buildingType == BuildingType.TOWER) {
                        Tower tower = (Tower) platformTile.building;
                        platformTile.building.setTile(null);
                        platformTile.building = null;
                        setTower(platformTile.getX(), platformTile.getY(), tower);
                    }
                }
            }
        }
        for (int i3 = 0; i3 < this.i.getHeight(); i3++) {
            for (int i4 = 0; i4 < this.i.getWidth(); i4++) {
                Tile tile2 = this.i.getTile(i4, i3);
                if (tile2 instanceof PlatformTile) {
                    PlatformTile platformTile2 = (PlatformTile) tile2;
                    if (platformTile2.building != null && platformTile2.building.buildingType == BuildingType.MODIFIER) {
                        Modifier modifier = (Modifier) platformTile2.building;
                        platformTile2.building.setTile(null);
                        platformTile2.building = null;
                        setModifier(platformTile2.getX(), platformTile2.getY(), modifier);
                    }
                }
            }
        }
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void setup() {
        this.S.events.getListeners(NextWaveForce.class).addStateAffecting(new OnNextWaveForce(this, (byte) 0)).setDescription("MapSystem - increases cores double speed");
        this.S.events.getListeners(GameValuesRecalculate.class).addStateAffecting(new OnGameValuesRecalculate(this)).setDescription("MapSystem - updates walkablePlatforms setting and marks all tiles dirty");
        a();
        if (!this.S.CFG.headless) {
            e();
        }
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void postStateRestore() {
        e();
    }

    private void e() {
        this.S._render.addLayer(new RenderSystem.Layer(GameRenderingOrder.MAP_DRAW, false, (batch, f, f2, f3) -> {
            drawBatch(batch, f2);
        }).setName("Map-draw"));
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void postSetup() {
        this.j = true;
        if (this.k && this.S.gameState != null) {
            d();
        }
        markAllTilesDirty();
    }

    public final void showTileWarningParticle(int i, int i2) {
        if (this.S._particle != null) {
            ParticleEffectPool.PooledEffect obtain = Game.i.mapManager.tileWarningParticlePool.obtain();
            obtain.setPosition((i << 7) + 64, (i2 << 7) + 64);
            this.S._particle.addParticle(obtain, false);
        }
    }

    public final void removeHighlights() {
        for (int i = 0; i < this.c.size; i++) {
            Tile tile = this.c.get(i);
            tile.highlightParticleA.allowCompletion();
            tile.highlightParticleA = null;
            tile.highlightParticleB.allowCompletion();
            tile.highlightParticleB = null;
        }
        this.c.clear();
        for (int i2 = 0; i2 < this.d.size; i2++) {
            Gate gate = this.d.get(i2);
            gate.highlightParticleA.allowCompletion();
            gate.highlightParticleA = null;
            gate.highlightParticleB.allowCompletion();
            gate.highlightParticleB = null;
        }
        this.d.clear();
    }

    public final void highlightTile(Tile tile) {
        tile.highlightParticleA = Game.i.mapManager.highlightParticlesPool.obtain();
        tile.highlightParticleB = Game.i.mapManager.highlightParticlesPool.obtain();
        this.S._particle.addParticle(tile.highlightParticleA, false);
        this.S._particle.addParticle(tile.highlightParticleB, false);
        this.c.add(tile);
    }

    public final void highlightGate(Gate gate) {
        gate.highlightParticleA = Game.i.mapManager.highlightParticlesPool.obtain();
        gate.highlightParticleB = Game.i.mapManager.highlightParticlesPool.obtain();
        this.S._particle.addParticle(gate.highlightParticleA, false);
        this.S._particle.addParticle(gate.highlightParticleB, false);
        this.d.add(gate);
    }

    public final boolean isPointWithinTile(Tile tile, int i, int i2) {
        return tile.boundingBox.contains(i, i2);
    }

    public final boolean isVisible(Tile tile, OrthographicCamera orthographicCamera) {
        int i = (int) (orthographicCamera.position.x - (orthographicCamera.viewportWidth / 2.0f));
        int i2 = (int) (orthographicCamera.position.x + (orthographicCamera.viewportWidth / 2.0f));
        return tile.boundingBox.overlaps(i, (int) (orthographicCamera.position.y - (orthographicCamera.viewportHeight / 2.0f)), i2, (int) (orthographicCamera.position.y + (orthographicCamera.viewportHeight / 2.0f)));
    }

    public final void setGate(int i, int i2, boolean z, @Null Gate gate) {
        Gate gate2 = this.i.getGate(i, i2, z);
        if (gate2 != null) {
            gate2.setUnregistered();
        }
        this.i.setGate(i, i2, z, gate);
        if (gate != null) {
            gate.setRegistered(this.S);
        }
        if (this.S._mapRendering != null) {
            this.S._mapRendering.forceTilesRedraw(true);
        }
        this.S.events.trigger(new GateChange(i, i2, z, gate2, gate));
    }

    public final void setTile(int i, int i2, @Null Tile tile) {
        Tile tile2 = this.i.getTile(i, i2);
        if (tile2 == null && tile == null) {
            return;
        }
        if ((tile2 instanceof SpawnTile) && this.S.CFG.setup == GameSystemProvider.SystemsConfig.Setup.GAME) {
            f2989a.e("can't remove spawn tile", new Object[0]);
            return;
        }
        if (tile2 != null) {
            if (tile2 instanceof PlatformTile) {
                PlatformTile platformTile = (PlatformTile) tile2;
                if (platformTile.building != null) {
                    if (platformTile.building.buildingType == BuildingType.TOWER) {
                        if (this.S.tower != null) {
                            this.S.tower.sellTower((Tower) platformTile.building);
                        }
                    } else if (platformTile.building.buildingType == BuildingType.MODIFIER && this.S.modifier != null) {
                        this.S.modifier.sellModifier((Modifier) platformTile.building);
                    }
                }
            }
            if ((tile2 instanceof SourceTile) && ((SourceTile) tile2).miner != null && this.S.miner != null) {
                this.S.miner.sellMiner(tile2.getX(), tile2.getY());
            }
            tile2.setUnregistered();
        }
        this.i.setTile(i, i2, tile);
        if (tile != null) {
            tile.setRegistered(this.S);
        }
        if ((tile instanceof BossTile) || (tile2 instanceof BossTile) || (tile instanceof SpawnTile)) {
            this.n = true;
            if (this.S.wave != null) {
                this.S.wave.resetNextWavesCache();
            }
        }
        markTilesDirty(i, i2, 1);
        this.S.events.trigger(new TileChange(i, i2, tile2, tile));
    }

    public final void registerTile(Tile tile) {
        tile.setRegistered(this.S);
    }

    public final void unregisterTile(Tile tile) {
        if (tile.isRegistered()) {
            tile.setUnregistered();
        }
    }

    public final void registerGate(Gate gate) {
        gate.setRegistered(this.S);
    }

    public final void unregisterGate(Gate gate) {
        if (gate.isRegistered()) {
            gate.setUnregistered();
        }
    }

    public final void setTower(int i, int i2, Tower tower) {
        if (this.S.gameState != null) {
            this.S.gameState.checkGameplayUpdateAllowed();
        }
        Tile tile = this.i.getTile(i, i2);
        if (tile.type != TileType.PLATFORM) {
            throw new IllegalArgumentException("Tile at " + i + ":" + i2 + " is " + tile.type.name() + ", can't place tower here");
        }
        PlatformTile platformTile = (PlatformTile) tile;
        if (platformTile.building != null) {
            removeBuilding(platformTile.building);
        }
        platformTile.building = tower;
        tower.setTile(platformTile);
        this.S.events.trigger(new TowerPlace(tower));
        markTilesDirtyNearTile(platformTile, 1);
        tower.placedOnMap();
    }

    public final void setModifier(int i, int i2, Modifier modifier) {
        if (this.S.gameState != null) {
            this.S.gameState.checkGameplayUpdateAllowed();
        }
        Tile tile = this.i.getTile(i, i2);
        if (tile.type != TileType.PLATFORM) {
            throw new IllegalArgumentException("Tile at " + i + ":" + i2 + " is " + tile.type.name() + ", can't place modifier here");
        }
        PlatformTile platformTile = (PlatformTile) tile;
        if (platformTile.building != null) {
            removeBuilding(platformTile.building);
        }
        platformTile.building = modifier;
        modifier.setTile(platformTile);
        this.S.events.trigger(new ModifierPlace(modifier));
        modifier.placedOnMap();
        markTilesDirtyNearTile(tile, 1);
    }

    public final void removeBuilding(Building building) {
        if (this.S.gameState != null) {
            this.S.gameState.checkGameplayUpdateAllowed();
        }
        PlatformTile tile = building.getTile();
        building.setTile(null);
        tile.building = null;
        building.removedFromMap();
        this.S.events.trigger(new BuildingRemove(building, tile));
        markTilesDirtyNearTile(tile, 1);
    }

    public final void setMiner(int i, int i2, Miner miner) {
        if (this.S.gameState != null) {
            this.S.gameState.checkGameplayUpdateAllowed();
        }
        Tile tile = this.i.getTile(i, i2);
        if (tile.type != TileType.SOURCE) {
            throw new IllegalArgumentException("Tile at " + i + ":" + i2 + " is " + tile.type.name() + ", can't place miner here");
        }
        SourceTile sourceTile = (SourceTile) tile;
        if (sourceTile.miner != null) {
            removeMiner(sourceTile.miner);
        }
        sourceTile.miner = miner;
        miner.setTile(sourceTile);
        miner.placedOnMap();
        markTilesDirtyNearTile(tile, 1);
        this.S.events.trigger(new MinerPlace(miner));
    }

    public final void removeMiner(Miner miner) {
        if (this.S.gameState != null) {
            this.S.gameState.checkGameplayUpdateAllowed();
        }
        SourceTile tile = miner.getTile();
        miner.setTile(null);
        tile.miner = null;
        miner.removedFromMap();
        markTilesDirtyNearTile(tile, 1);
        this.S.events.trigger(new MinerRemove(miner, tile));
    }

    public final boolean isSpawned(Unit unit) {
        return unit.spawned;
    }

    public final void spawnEnemy(Enemy enemy) {
        if (this.S.gameState != null) {
            this.S.gameState.checkGameplayUpdateAllowed();
        }
        if (!enemy.isRegistered()) {
            throw new IllegalArgumentException("Enemy is not registered " + enemy);
        }
        Enemy.EnemyReference reference = this.S.enemy.getReference(enemy);
        if (this.spawnedEnemies.contains(reference, true)) {
            throw new IllegalStateException("Enemy " + enemy + " is already spawned");
        }
        this.spawnedEnemies.add(reference);
        this.S.events.trigger(new EnemySpawn(enemy));
        enemy.onSpawned();
    }

    public final void despawnUnit(Unit unit) {
        if (this.S.gameState != null) {
            this.S.gameState.checkGameplayUpdateAllowed();
        }
        if (isSpawned(unit)) {
            this.spawnedUnits.removeValue(unit, true);
            unit.spawned = false;
            this.S.events.trigger(new UnitDespawn(unit));
            unit.onDespawned();
            return;
        }
        f2989a.e("Unit is not spawned", new Object[0]);
    }

    public final void spawnUnit(Unit unit) {
        if (this.S.gameState != null) {
            this.S.gameState.checkGameplayUpdateAllowed();
        }
        if (!isSpawned(unit)) {
            this.spawnedUnits.add(unit);
            unit.spawned = true;
            this.S.events.trigger(new UnitSpawn(unit));
            unit.onSpawned();
            return;
        }
        f2989a.e("Unit is already spawned", new Object[0]);
    }

    public final void despawnEnemy(Enemy enemy) {
        if (this.S.gameState != null) {
            this.S.gameState.checkGameplayUpdateAllowed();
        }
        if (!EntityUtils.removeByValue(this.spawnedEnemies, enemy)) {
            throw new IllegalArgumentException("Enemy is not in spawnedEnemies: " + enemy);
        }
        this.S.events.trigger(new EnemyDespawn(enemy));
    }

    public final boolean lineCanHitEntry(float f, float f2, float f3, float f4) {
        return this.p.lineCanHitEntry(f, f2, f3, f4);
    }

    private static boolean f() {
        return (Config.isHeadless() || Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DBG_RAYCASTS) == 0.0d) ? false : true;
    }

    private static boolean g() {
        return (Config.isHeadless() || Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DBG_DIRTY_TILES) == 0.0d) ? false : true;
    }

    private static boolean h() {
        return (Config.isHeadless() || Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DBG_COLLISIONS) == 0.0d) ? false : true;
    }

    public final boolean rayCastEnemiesSorted(float f, float f2, float f3, float f4, float f5, ObjectFilter<Enemy.EnemyReference> objectFilter) {
        if (f5 < 0.0f) {
            f5 = 0.0f;
        }
        if (!this.p.entriesExistInRect(Math.min(f, f3) - f5, Math.min(f2, f4) - f5, Math.max(f, f3) + f5, Math.max(f2, f4) + f5)) {
            return false;
        }
        FloatSorter floatSorter = this.s.f3000a;
        floatSorter.begin();
        this.p.traverseEntriesInLine(f, f2, f3, f4, this.r.setup(f, f2, f3, f4, f5), this.s.setup(f, f2));
        RCD rcd = null;
        if (f()) {
            RCD rcd2 = new RCD((byte) 0);
            rcd = rcd2;
            rcd2.f2997b = f;
            rcd.c = f2;
            rcd.d = f3;
            rcd.e = f4;
            rcd.g = MaterialColor.PINK.P500;
            rcd.f = Math.max(1.0f, f5);
            this.t.add(rcd);
        }
        Array<FloatSorter.Entity> sort = floatSorter.sort();
        for (int i = 0; i < sort.size; i++) {
            if (rcd != null) {
                Enemy enemy = ((Enemy.EnemyReference) sort.items[i].object).enemy;
                Vector2 position = enemy.getPosition();
                rcd.h.add(position.x, position.y, enemy.getSize());
            }
            if (!objectFilter.test((Enemy.EnemyReference) sort.items[i].object)) {
                break;
            }
        }
        boolean z = sort.size != 0;
        floatSorter.end();
        return z;
    }

    public final boolean rayCastEnemies(float f, float f2, float f3, float f4, float f5, ObjectFilter<Enemy.EnemyReference> objectFilter) {
        if (f5 < 0.0f) {
            f5 = 0.0f;
        }
        if (!this.p.entriesExistInRect(Math.min(f, f3) - f5, Math.min(f2, f4) - f5, Math.max(f, f3) + f5, Math.max(f2, f4) + f5)) {
            return false;
        }
        boolean[] zArr = {false};
        RCD rcd = null;
        if (f()) {
            RCD rcd2 = new RCD((byte) 0);
            rcd = rcd2;
            rcd2.f2997b = f;
            rcd.c = f2;
            rcd.d = f3;
            rcd.e = f4;
            rcd.g = MaterialColor.CYAN.P500;
            rcd.f = Math.max(1.0f, f5);
            this.t.add(rcd);
        }
        RCD rcd3 = rcd;
        float f6 = f5;
        this.p.traverseEntriesInLine(f, f2, f3, f4, (f7, f8, f9) -> {
            return Intersector.intersectSegmentCircle(f, f2, f3, f4, f7, f8, (f9 + f6) * (f9 + f6));
        }, (enemyReference, f10, f11, f12) -> {
            if (enemyReference.enemy == null) {
                return true;
            }
            if (rcd3 != null) {
                rcd3.h.add(f10, f11, f12);
            }
            zArr[0] = true;
            return objectFilter.test(enemyReference);
        });
        return zArr[0];
    }

    public final int getSpawnedEnemiesCountByWave(Wave wave) {
        int i = 0;
        int i2 = this.spawnedEnemies.size;
        for (int i3 = 0; i3 < i2; i3++) {
            Enemy enemy = this.spawnedEnemies.items[i3].enemy;
            if (enemy != null && enemy.wave != null && enemy.wave == wave) {
                i++;
            }
        }
        return i;
    }

    public final void showTowerRangeHint(float f, float f2, float f3, float f4) {
        if (this.f == null) {
            this.f = (RangeCircle) Game.i.shapeManager.getFactory(ShapeType.RANGE_CIRCLE).obtain();
        }
        this.g = true;
        this.f.setup(f, f2, f3, f4, f2990b);
    }

    public final void hideTowerRangeHint() {
        this.g = false;
    }

    public final void upgradeCoreAction(CoreTile coreTile, int i, int i2) {
        if (coreTile.getUpgradeInstallLevel(i, i2) >= coreTile.getUpgrade(i, i2).upgradeLevels.size) {
            f2989a.e("Upgrade " + i2 + ":" + i + " is already on max level", new Object[0]);
        } else if (coreTile.canUpgradeBeInstalled(i, i2)) {
            this.S.gameState.pushActionNextUpdate(new CoreUpgradeAction(coreTile.getX(), coreTile.getY(), i, i2));
        } else {
            f2989a.e("upgrade can't be installed", new Object[0]);
        }
    }

    public final void upgradeCoreActionAt(int i, int i2, int i3, int i4) {
        Tile tile = this.i.getTile(i, i2);
        if (tile != null && tile.type == TileType.CORE) {
            upgradeCoreAction((CoreTile) tile, i3, i4);
        } else {
            f2989a.e("there's no core tile on " + i + ":" + i2, new Object[0]);
        }
    }

    public final boolean upgradeCoreAt(int i, int i2, int i3, int i4) {
        Tile tile = this.i.getTile(i, i2);
        if (tile != null && tile.type == TileType.CORE) {
            return upgradeCore((CoreTile) tile, i3, i4);
        }
        f2989a.e("there's no core tile on " + i + ":" + i2, new Object[0]);
        return false;
    }

    public final boolean upgradeCore(CoreTile coreTile, int i, int i2) {
        CoreTile.Upgrade upgrade = coreTile.getUpgrade(i, i2);
        if (upgrade != null) {
            if (coreTile.getUpgradeInstallLevel(i, i2) >= upgrade.upgradeLevels.size) {
                f2989a.e("Upgrade " + i2 + ":" + i + " is already on max level", new Object[0]);
                return false;
            }
            if (coreTile.canUpgradeBeInstalled(i, i2)) {
                int money = this.S.gameState.getMoney();
                int freeUpgradePoints = coreTile.getFreeUpgradePoints();
                int upgradeInstallLevel = coreTile.getUpgradeInstallLevel(i, i2);
                CoreTile.Upgrade.UpgradeLevel upgradeLevel = upgrade.upgradeLevels.items[upgradeInstallLevel];
                if (upgrade.costsCoins ? money >= upgradeLevel.price : freeUpgradePoints >= upgradeLevel.price) {
                    if (upgrade.costsCoins) {
                        this.S.gameState.removeMoney(upgradeLevel.price);
                    }
                    coreTile.setUpgradeInstallLevel(i, i2, upgradeInstallLevel + 1);
                    if (upgrade.isAction) {
                        Game.i.triggeredActionManager.perform(this.S, upgrade.getActionType(), upgradeLevel.delta);
                    } else {
                        this.S.gameValue.recalculate();
                    }
                    i();
                    this.S.events.trigger(new CoreTileUpgradeInstall(coreTile, i, i2));
                    if (this.S._particle != null && Game.i.settingsManager.isParticlesDrawing()) {
                        ParticleEffectPool.PooledEffect obtain = Game.i.mapManager.coreHighlightParticlesPool.obtain();
                        Color color = null;
                        switch (coreTile.getTier()) {
                            case REGULAR:
                                color = MaterialColor.LIGHT_BLUE.P500;
                                break;
                            case RARE:
                                color = MaterialColor.PURPLE.P400;
                                break;
                            case LEGENDARY:
                                color = MaterialColor.ORANGE.P500;
                                break;
                        }
                        obtain.getEmitters().first().getTint().setColors(new float[]{color.r, color.g, color.f888b});
                        obtain.setPosition(coreTile.center.x, coreTile.center.y);
                        this.S._particle.addParticle(obtain, true);
                        return true;
                    }
                    return true;
                }
                f2989a.e("not enough points to make an upgrade", new Object[0]);
                return false;
            }
            f2989a.e("upgrade can't be installed", new Object[0]);
            return false;
        }
        f2989a.e("There's no upgrade in core at " + i2 + ":" + i, new Object[0]);
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.prineside.tdi2.GameSystem
    public final void update(float f) {
        StateSystem.ActionsArray currentUpdateActions;
        if (this.i != null && this.n && this.S.wave != null) {
            f2989a.i("changing boss waves configuration", new Object[0]);
            WaveBossSupplier bossWaves = this.i.getBossWaves();
            if (bossWaves == null) {
                f2989a.i("map has no bosses", new Object[0]);
                this.S.wave.setBossWaves(null);
            } else {
                f2989a.i("map has bosses", new Object[0]);
                this.S.wave.setBossWaves(bossWaves.cpy());
            }
            this.n = false;
        }
        if (this.S.gameState != null && (currentUpdateActions = this.S.gameState.getCurrentUpdateActions()) != null) {
            for (int i = 0; i < currentUpdateActions.size; i++) {
                Action action = currentUpdateActions.actions[i];
                if (action.getType() == ActionType.CU) {
                    CoreUpgradeAction coreUpgradeAction = (CoreUpgradeAction) action;
                    if (upgradeCoreAt(coreUpgradeAction.x, coreUpgradeAction.y, coreUpgradeAction.col, coreUpgradeAction.row)) {
                        this.S.gameState.registerPlayerActivity();
                    }
                }
            }
        }
        if (this.S.gameState != null && this.S.gameValue != null && this.S.gameState.isGameRealTimePasses()) {
            boolean z = false;
            this.h += f;
            if (this.h > 2.0f) {
                this.h -= 2.0f;
                z = true;
            }
            Array tilesByType = this.i.getTilesByType(CoreTile.class);
            for (int i2 = 0; i2 < tilesByType.size; i2++) {
                CoreTile coreTile = ((CoreTile[]) tilesByType.items)[i2];
                float tickRateDeltaTime = this.S.gameValue.getTickRateDeltaTime();
                if (coreTile.doubleSpeedTimeLeft > 0.0f) {
                    tickRateDeltaTime = this.S.gameValue.getTickRateDeltaTime() * 2.0f;
                    coreTile.doubleSpeedTimeLeft -= this.S.gameValue.getTickRateDeltaTime();
                    if (coreTile.doubleSpeedTimeLeft <= 0.0f) {
                        coreTile.doubleSpeedTimeLeft = 0.0f;
                    }
                }
                float experienceGeneration = Game.i.tileManager.F.CORE.getExperienceGeneration(coreTile, this.S.gameValue) * tickRateDeltaTime;
                int level = coreTile.getLevel();
                coreTile.setExperience(coreTile.getExperience() + experienceGeneration);
                if (coreTile.getLevel() > level) {
                    this.S.events.trigger(new CoreTileLevelUp(coreTile));
                    z = true;
                }
            }
            if (z) {
                i();
            }
        }
        c();
        updateDirtyTiles();
        if (Game.i.debugManager != null && Game.i.debugManager.isEnabled()) {
            Game.i.debugManager.registerValue("Spawned enemies").append(this.spawnedEnemies.size);
            Game.i.debugManager.registerValue("Spawned units").append(this.spawnedUnits.size);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void i() {
        Array tilesByType = this.i.getTilesByType(CoreTile.class);
        if (this.S._particle != null && tilesByType.size != 0) {
            for (int i = 0; i < tilesByType.size; i++) {
                CoreTile coreTile = ((CoreTile[]) tilesByType.items)[i];
                if (coreTile.upgradeAvailableParticleEffect == null) {
                    if (coreTile.hasSomethingToUpgrade()) {
                        coreTile.upgradeAvailableParticleEffect = Game.i.towerManager.abilityAvailableParticleEffectPool.obtain();
                        coreTile.upgradeAvailableParticleEffect.setPosition(coreTile.center.x + 32.0f, coreTile.center.y - 42.24f);
                        this.S._particle.addParticle(coreTile.upgradeAvailableParticleEffect, false);
                    }
                } else if (!coreTile.hasSomethingToUpgrade()) {
                    coreTile.upgradeAvailableParticleEffect.allowCompletion();
                    coreTile.upgradeAvailableParticleEffect = null;
                }
            }
        }
    }

    @Override // com.prineside.tdi2.GameSystem
    public final boolean profileUpdate() {
        return false;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final String getSystemName() {
        return "Map";
    }

    private static void a(float f, Vector2 vector2, boolean z, boolean z2) {
        float f2;
        float f3;
        float f4;
        float f5;
        if (z) {
            f2 = -64.0f;
            f4 = -64.0f;
            f3 = 64.0f;
            f5 = 64.0f;
        } else if (z2) {
            f4 = -78.0f;
            f5 = -50.0f;
            f2 = -64.0f;
            f3 = 64.0f;
        } else {
            f2 = -78.0f;
            f3 = -50.0f;
            f4 = -64.0f;
            f5 = 64.0f;
        }
        if (f < 0.25f) {
            vector2.x = f4;
            vector2.y = ((f / 0.25f) * (f3 - f2)) + f2;
        } else if (f < 0.5f) {
            vector2.x = (((f - 0.25f) / 0.25f) * (f5 - f4)) + f4;
            vector2.y = f3;
        } else if (f < 0.75f) {
            vector2.x = f5;
            vector2.y = f3 - (((f - 0.5f) / 0.25f) * (f3 - f2));
        } else {
            vector2.x = f5 - (((f - 0.75f) / 0.25f) * (f5 - f4));
            vector2.y = f2;
        }
    }

    public final void setPathTracesDrawing(boolean z) {
        this.drawPathTraces = z;
    }

    public final void traverseNeighborTilesAroundPos(int i, int i2, ObjectFilter<Tile> objectFilter) {
        Tile tile;
        Tile[][] tilesRaw = this.i.getTilesRaw();
        int max = Math.max(i2 - 1, 0);
        int min = Math.min(i2 + 1, this.i.getHeight() - 1) + 1;
        int min2 = Math.min(i + 1, this.i.getWidth() - 1);
        for (int max2 = Math.max(i - 1, 0); max2 <= min2; max2++) {
            for (int i3 = max; i3 < min; i3++) {
                if ((max2 != i || i3 != i2) && (tile = tilesRaw[i3][max2]) != null && !objectFilter.test(tile)) {
                    return;
                }
            }
        }
    }

    public final void traverseNeighborTilesAroundTile(Tile tile, ObjectFilter<Tile> objectFilter) {
        traverseNeighborTilesAroundPos(tile.getX(), tile.getY(), objectFilter);
    }

    public final void traverseNeighborTilesIncludingPos(int i, int i2, ObjectFilter<Tile> objectFilter) {
        Tile[][] tilesRaw = this.i.getTilesRaw();
        int max = Math.max(i2 - 1, 0);
        int min = Math.min(i2 + 1, this.i.getHeight() - 1) + 1;
        int min2 = Math.min(i + 1, this.i.getWidth() - 1);
        for (int max2 = Math.max(i - 1, 0); max2 <= min2; max2++) {
            for (int i3 = max; i3 < min; i3++) {
                Tile tile = tilesRaw[i3][max2];
                if (tile != null && !objectFilter.test(tile)) {
                    return;
                }
            }
        }
    }

    public final void traverseNeighborTilesIncludingTile(Tile tile, ObjectFilter<Tile> objectFilter) {
        traverseNeighborTilesIncludingPos(tile.getX(), tile.getY(), objectFilter);
    }

    public final void drawBatch(Batch batch, float f) {
        this.S._render.prepareBatch(batch, true);
        if (this.g) {
            this.f.draw(batch);
        }
        if (this.c.size != 0) {
            this.e += f * 0.5f;
            if (this.e > 1.0f) {
                this.e %= 1.0f;
            }
            Vector2 vector2 = new Vector2();
            Vector2 vector22 = new Vector2();
            a(this.e, vector2, true, false);
            this.e += 0.5f;
            if (this.e > 1.0f) {
                this.e %= 1.0f;
            }
            a(this.e, vector22, true, false);
            for (int i = 0; i < this.c.size; i++) {
                Tile tile = this.c.get(i);
                tile.highlightParticleA.setPosition((tile.getX() << 7) + 64 + vector2.x, (tile.getY() << 7) + 64 + vector2.y);
                tile.highlightParticleB.setPosition((tile.getX() << 7) + 64 + vector22.x, (tile.getY() << 7) + 64 + vector22.y);
            }
        }
        if (this.d.size != 0) {
            Vector2 vector23 = new Vector2();
            Vector2 vector24 = new Vector2();
            for (int i2 = 0; i2 < this.d.size; i2++) {
                Gate gate = this.d.get(i2);
                this.e += f * 0.5f;
                if (this.e > 1.0f) {
                    this.e %= 1.0f;
                }
                a(this.e, vector23, false, gate.isLeftSide());
                this.e += 0.5f;
                if (this.e > 1.0f) {
                    this.e %= 1.0f;
                }
                a(this.e, vector24, false, gate.isLeftSide());
                gate.highlightParticleA.setPosition((gate.getX() << 7) + 64 + vector23.x, (gate.getY() << 7) + 64 + vector23.y);
                gate.highlightParticleB.setPosition((gate.getX() << 7) + 64 + vector24.x, (gate.getY() << 7) + 64 + vector24.y);
            }
        }
        if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DBG_DRAW_TILE_INFO) != 0.0d) {
            BitmapFont debugFont = Game.i.assetManager.getDebugFont(false);
            debugFont.setColor(0.0f, 1.0f, 1.0f, 0.56f);
            for (int i3 = 0; i3 < this.i.getHeight(); i3++) {
                for (int i4 = 0; i4 < this.i.getWidth(); i4++) {
                    Tile tile2 = this.i.getTile(i4, i3);
                    if (tile2 != null) {
                        debugFont.draw(batch, "WC: " + String.valueOf(this.S.pathfinding != null ? (int) tile2.getWalkCost(this.S.pathfinding.isWalkablePlatforms()) : 0).length() + " E: " + tile2.enemyCount + " TDE: " + tile2.towerDisablingEnemyCount, i4 << 7, ((i3 << 7) - 10.0f) + 64.0f, 128.0f, 1, false);
                    }
                }
            }
            debugFont.setColor(Color.WHITE);
        }
        if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DBG_DRAW_ENEMIES_AABB) != 0.0d && this.p != null) {
            DrawUtils.texturedLineC(batch, Game.i.assetManager.getBlankWhiteTextureRegion(), this.p.getMinX(), this.p.getMinY(), this.p.getMinX(), this.p.getMaxY(), 2.0f, MaterialColor.PURPLE.P500.toFloatBits(), MaterialColor.PURPLE.P500.toFloatBits());
            DrawUtils.texturedLineC(batch, Game.i.assetManager.getBlankWhiteTextureRegion(), this.p.getMinX(), this.p.getMinY(), this.p.getMaxX(), this.p.getMinY(), 2.0f, MaterialColor.PURPLE.P500.toFloatBits(), MaterialColor.PURPLE.P500.toFloatBits());
            DrawUtils.texturedLineC(batch, Game.i.assetManager.getBlankWhiteTextureRegion(), this.p.getMaxX(), this.p.getMinY(), this.p.getMaxX(), this.p.getMaxY(), 2.0f, MaterialColor.PURPLE.P500.toFloatBits(), MaterialColor.PURPLE.P500.toFloatBits());
            DrawUtils.texturedLineC(batch, Game.i.assetManager.getBlankWhiteTextureRegion(), this.p.getMinX(), this.p.getMaxY(), this.p.getMaxX(), this.p.getMaxY(), 2.0f, MaterialColor.PURPLE.P500.toFloatBits(), MaterialColor.PURPLE.P500.toFloatBits());
            float cellSizeXInv = 1.0f / this.p.getCellSizeXInv();
            float cellSizeYInv = 1.0f / this.p.getCellSizeYInv();
            float floatBits = new Color(0.0f, 1.0f, 0.0f, 0.56f).toFloatBits();
            for (int i5 = 0; i5 <= this.p.getCols(); i5++) {
                float minX = (i5 * cellSizeXInv) + this.p.getMinX();
                DrawUtils.texturedLineC(batch, Game.i.assetManager.getBlankWhiteTextureRegion(), minX, this.p.getMinY(), minX, (this.p.getRows() * cellSizeYInv) + this.p.getMinY(), 1.0f, floatBits, floatBits);
            }
            for (int i6 = 0; i6 <= this.p.getRows(); i6++) {
                float minY = (i6 * cellSizeYInv) + this.p.getMinY();
                DrawUtils.texturedLineC(batch, Game.i.assetManager.getBlankWhiteTextureRegion(), this.p.getMinX(), minY, (this.p.getCols() * cellSizeXInv) + this.p.getMinX(), minY, 1.0f, floatBits, floatBits);
            }
            for (int i7 = 0; i7 < this.p.getRows(); i7++) {
                for (int i8 = 0; i8 < this.p.getCols(); i8++) {
                    Game.i.assetManager.getSmallDebugFont().draw(batch, new StringBuilder().append(this.p.getEntityCount((i7 * this.p.getCols()) + i8)).toString(), (i8 * cellSizeXInv) + 4.0f + this.p.getMinX(), (i7 * cellSizeYInv) + 14.0f + this.p.getMinY());
                }
            }
            this.p.debugBatch = batch;
            if (Gdx.input.isKeyPressed(43)) {
                w.set(Gdx.input.getX(), Gdx.input.getY());
                this.S._input.cameraController.screenToMap(w);
            }
            if (Gdx.input.isKeyPressed(40)) {
                x.set(Gdx.input.getX(), Gdx.input.getY());
                this.S._input.cameraController.screenToMap(x);
            }
            if (Gdx.input.isKeyPressed(39)) {
                y.set(Gdx.input.getX(), Gdx.input.getY());
                this.S._input.cameraController.screenToMap(y);
            }
            batch.setColor(0.0f, 0.0f, 1.0f, 0.28f);
            batch.draw(Game.i.assetManager.getTextureRegion("circle"), w.x - 384.0f, w.y - 384.0f, 768.0f, 768.0f);
            DrawUtils.texturedLineC(batch, Game.i.assetManager.getBlankWhiteTextureRegion(), x.x, x.y, y.x, y.y, 3.0f, MaterialColor.CYAN.P500.toFloatBits(), MaterialColor.CYAN.P500.toFloatBits());
            batch.setColor(1.0f, 0.0f, 0.0f, 0.28f);
            getEnemiesInCircle(w.x, w.y, 384.0f, (enemyReference, f2, f3, f4) -> {
                return true;
            });
            rayCastEnemiesSorted(x.x, x.y, y.x, y.y, 0.0f, enemyReference2 -> {
                return true;
            });
            batch.setColor(Color.WHITE);
            this.p.debugBatch = null;
        }
        if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DBG_DRAW_ENEMIES_COUNT_AABB) != 0.0d && this.q != null) {
            DrawUtils.texturedLineC(batch, Game.i.assetManager.getBlankWhiteTextureRegion(), this.q.getMinX(), this.q.getMinY(), this.q.getMinX(), this.q.getMaxY(), 2.0f, MaterialColor.PURPLE.P500.toFloatBits(), MaterialColor.PURPLE.P500.toFloatBits());
            DrawUtils.texturedLineC(batch, Game.i.assetManager.getBlankWhiteTextureRegion(), this.q.getMinX(), this.q.getMinY(), this.q.getMaxX(), this.q.getMinY(), 2.0f, MaterialColor.PURPLE.P500.toFloatBits(), MaterialColor.PURPLE.P500.toFloatBits());
            DrawUtils.texturedLineC(batch, Game.i.assetManager.getBlankWhiteTextureRegion(), this.q.getMaxX(), this.q.getMinY(), this.q.getMaxX(), this.q.getMaxY(), 2.0f, MaterialColor.PURPLE.P500.toFloatBits(), MaterialColor.PURPLE.P500.toFloatBits());
            DrawUtils.texturedLineC(batch, Game.i.assetManager.getBlankWhiteTextureRegion(), this.q.getMinX(), this.q.getMaxY(), this.q.getMaxX(), this.q.getMaxY(), 2.0f, MaterialColor.PURPLE.P500.toFloatBits(), MaterialColor.PURPLE.P500.toFloatBits());
            float cellSize = this.q.getCellSize();
            float floatBits2 = new Color(0.0f, 1.0f, 0.0f, 0.56f).toFloatBits();
            for (int i9 = 0; i9 <= this.q.getCols(); i9++) {
                float minX2 = (i9 * cellSize) + this.q.getMinX();
                DrawUtils.texturedLineC(batch, Game.i.assetManager.getBlankWhiteTextureRegion(), minX2, this.q.getMinY(), minX2, (this.q.getRows() * cellSize) + this.q.getMinY(), 1.0f, floatBits2, floatBits2);
            }
            for (int i10 = 0; i10 <= this.q.getRows(); i10++) {
                float minY2 = (i10 * cellSize) + this.q.getMinY();
                DrawUtils.texturedLineC(batch, Game.i.assetManager.getBlankWhiteTextureRegion(), this.q.getMinX(), minY2, (this.q.getCols() * cellSize) + this.q.getMinX(), minY2, 1.0f, floatBits2, floatBits2);
            }
            for (int i11 = 0; i11 < this.q.getRows(); i11++) {
                for (int i12 = 0; i12 < this.q.getCols(); i12++) {
                    int cols = (i11 * this.q.getCols()) + i12;
                    float minX3 = (i12 * cellSize) + this.q.getMinX();
                    float minY3 = (i11 * cellSize) + this.q.getMinY();
                    batch.setColor(0.0f, 1.0f, 0.0f, MathUtils.clamp(0.14f * this.q.getEntityCountByCellIdx(cols), 0.0f, 1.0f));
                    batch.draw(Game.i.assetManager.getBlankWhiteTextureRegion(), minX3, minY3, cellSize, cellSize);
                    batch.setColor(Color.WHITE);
                    Game.i.assetManager.getSmallDebugFont().draw(batch, new StringBuilder().append(this.q.getEntityCountByCellIdx(cols)).toString(), (i12 * cellSize) + 4.0f + this.q.getMinX(), (i11 * cellSize) + 14.0f + this.q.getMinY());
                }
            }
        }
        if (this.t.size != 0 || this.u.size != 0 || this.v.size != 0) {
            batch.end();
            ShapeRenderer shapeRenderer = Game.i.renderingManager.shapeRenderer;
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            Gdx.gl.glEnable(3042);
            Gdx.gl.glBlendFunc(770, 771);
            shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
            this.t.begin();
            for (int i13 = 0; i13 < this.t.size; i13++) {
                RCD rcd = this.t.items[i13];
                rcd.f2996a += f;
                float f5 = 1.0f - (rcd.f2996a * 2.0f);
                if (f5 > 0.0f) {
                    Color mul = rcd.g.cpy().mul(1.0f, 1.0f, 1.0f, f5);
                    shapeRenderer.setColor(mul);
                    shapeRenderer.rectLine(rcd.f2997b, rcd.c, rcd.d, rcd.e, rcd.f);
                    mul.mul(1.0f, 1.0f, 1.0f, 0.56f);
                    shapeRenderer.setColor(mul);
                    for (int i14 = 0; i14 < rcd.h.size; i14 += 3) {
                        shapeRenderer.circle(rcd.h.items[i14], rcd.h.items[i14 + 1], rcd.h.items[i14 + 2]);
                    }
                } else {
                    this.t.removeIndex(i13);
                }
            }
            this.t.end();
            this.u.begin();
            for (int i15 = 0; i15 < this.u.size; i15++) {
                COLD_Circle cOLD_Circle = this.u.items[i15];
                cOLD_Circle.f2992a += f;
                float f6 = 1.0f - (cOLD_Circle.f2992a * 2.0f);
                if (f6 > 0.0f) {
                    Color mul2 = cOLD_Circle.e.cpy().mul(1.0f, 1.0f, 1.0f, f6);
                    shapeRenderer.setColor(mul2);
                    shapeRenderer.circle(cOLD_Circle.f2993b, cOLD_Circle.c, cOLD_Circle.d);
                    mul2.mul(1.0f, 1.0f, 1.0f, 0.56f);
                    shapeRenderer.setColor(mul2);
                    for (int i16 = 0; i16 < cOLD_Circle.f.size; i16 += 3) {
                        shapeRenderer.circle(cOLD_Circle.f.items[i16], cOLD_Circle.f.items[i16 + 1], cOLD_Circle.f.items[i16 + 2]);
                    }
                } else {
                    this.u.removeIndex(i15);
                }
            }
            this.u.end();
            this.v.begin();
            for (int i17 = 0; i17 < this.v.size; i17++) {
                DBG_DirtyTile dBG_DirtyTile = this.v.items[i17];
                dBG_DirtyTile.f2994a += f;
                float f7 = 1.0f - dBG_DirtyTile.f2994a;
                if (f7 <= 0.0f) {
                    this.v.removeIndex(i17);
                } else {
                    shapeRenderer.setColor(MaterialColor.YELLOW.P500.cpy().mul(1.0f, 1.0f, 1.0f, f7 * 0.56f));
                    shapeRenderer.rect((dBG_DirtyTile.f2995b << 7) + 2, (dBG_DirtyTile.c << 7) + 2, 124.0f, 124.0f);
                }
            }
            this.v.end();
            shapeRenderer.end();
            batch.begin();
        }
        if (g()) {
            BitmapFont smallDebugFont = Game.i.assetManager.getSmallDebugFont();
            for (int i18 = 0; i18 < this.m.length; i18++) {
                short[] sArr = this.m[i18];
                for (int i19 = 0; i19 < sArr.length; i19++) {
                    smallDebugFont.draw(batch, new StringBuilder().append((int) sArr[i19]).toString(), (i19 << 7) + 64, ((i18 << 7) + 128) - 20.0f, 1.0f, 1, false);
                }
            }
        }
    }

    @Override // com.prineside.tdi2.GameSystem, com.badlogic.gdx.utils.Disposable
    public final void dispose() {
        Game.i.debugManager.unregisterValue("Spawned enemies");
        this.c.clear();
        this.d.clear();
        this.spawnedEnemies.clear();
        this.spawnedUnits.clear();
        if (this.i != null) {
            b();
        }
        this.i = null;
        this.f = null;
        super.dispose();
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/MapSystem$OnNextWaveForce.class */
    public static final class OnNextWaveForce extends SerializableListener<MapSystem> implements Listener<NextWaveForce> {
        /* synthetic */ OnNextWaveForce(MapSystem mapSystem, byte b2) {
            this(mapSystem);
        }

        private OnNextWaveForce() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        private OnNextWaveForce(MapSystem mapSystem) {
            this.f1759a = mapSystem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(NextWaveForce nextWaveForce) {
            if (nextWaveForce.getTime() > 0.0f) {
                Array tilesByType = ((MapSystem) this.f1759a).i.getTilesByType(CoreTile.class);
                for (int i = 0; i < tilesByType.size; i++) {
                    ((CoreTile[]) tilesByType.items)[i].doubleSpeedTimeLeft += nextWaveForce.getTime();
                }
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/MapSystem$OnGameValuesRecalculate.class */
    public static final class OnGameValuesRecalculate extends SerializableListener<MapSystem> implements Listener<GameValuesRecalculate> {
        private OnGameValuesRecalculate() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public OnGameValuesRecalculate(MapSystem mapSystem) {
            this.f1759a = mapSystem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(GameValuesRecalculate gameValuesRecalculate) {
            ((MapSystem) this.f1759a).markAllTilesDirty();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/MapSystem$RCD.class */
    public static final class RCD {

        /* renamed from: a, reason: collision with root package name */
        float f2996a;

        /* renamed from: b, reason: collision with root package name */
        float f2997b;
        float c;
        float d;
        float e;
        float f;
        Color g;
        FloatArray h;

        private RCD() {
            this.g = MaterialColor.GREEN.P500;
            this.h = new FloatArray();
        }

        /* synthetic */ RCD(byte b2) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/MapSystem$COLD_Circle.class */
    public static final class COLD_Circle {

        /* renamed from: a, reason: collision with root package name */
        float f2992a;

        /* renamed from: b, reason: collision with root package name */
        float f2993b;
        float c;
        float d;
        Color e;
        FloatArray f;

        private COLD_Circle() {
            this.e = MaterialColor.GREEN.P500;
            this.f = new FloatArray();
        }

        /* synthetic */ COLD_Circle(byte b2) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/MapSystem$DBG_DirtyTile.class */
    public static final class DBG_DirtyTile {

        /* renamed from: a, reason: collision with root package name */
        float f2994a;

        /* renamed from: b, reason: collision with root package name */
        int f2995b;
        int c;

        private DBG_DirtyTile() {
        }

        /* synthetic */ DBG_DirtyTile(byte b2) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/MapSystem$RayCastSortedFilter.class */
    public static final class RayCastSortedFilter implements AABB.EntryFilter {

        /* renamed from: a, reason: collision with root package name */
        private float f2998a;

        /* renamed from: b, reason: collision with root package name */
        private float f2999b;
        private float c;
        private float d;
        private float e;

        private RayCastSortedFilter() {
        }

        /* synthetic */ RayCastSortedFilter(byte b2) {
            this();
        }

        public final RayCastSortedFilter setup(float f, float f2, float f3, float f4, float f5) {
            this.f2998a = f;
            this.f2999b = f2;
            this.c = f3;
            this.d = f4;
            this.e = f5;
            return this;
        }

        @Override // com.prineside.tdi2.utils.AABB.EntryFilter
        public final boolean test(float f, float f2, float f3) {
            return Intersector.intersectSegmentCircle(this.f2998a, this.f2999b, this.c, this.d, f, f2, (f3 + this.e) * (f3 + this.e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/MapSystem$RayCastSortedRetriever.class */
    public static final class RayCastSortedRetriever implements AABB.EntryRetriever<Enemy.EnemyReference> {

        /* renamed from: a, reason: collision with root package name */
        private final FloatSorter f3000a;

        /* renamed from: b, reason: collision with root package name */
        private float f3001b;
        private float c;

        /* synthetic */ RayCastSortedRetriever(FloatSorter floatSorter, byte b2) {
            this(floatSorter);
        }

        private RayCastSortedRetriever(FloatSorter floatSorter) {
            this.f3000a = floatSorter;
        }

        public final RayCastSortedRetriever setup(float f, float f2) {
            this.f3001b = f;
            this.c = f2;
            return this;
        }

        @Override // com.prineside.tdi2.utils.AABB.EntryRetriever
        public final boolean retrieve(Enemy.EnemyReference enemyReference, float f, float f2, float f3) {
            if (enemyReference.enemy == null) {
                return true;
            }
            this.f3000a.add(enemyReference, PMath.getSquareDistanceBetweenPoints(f, f2, this.f3001b, this.c));
            return true;
        }
    }
}
