package com.prineside.tdi2.systems;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.ai.pfa.Heuristic;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ByteArray;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.IntSet;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystem;
import com.prineside.tdi2.Gate;
import com.prineside.tdi2.GateBarrier;
import com.prineside.tdi2.Map;
import com.prineside.tdi2.ResourcePack;
import com.prineside.tdi2.SerializableListener;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.configs.GameRenderingOrder;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.BuildingRemove;
import com.prineside.tdi2.events.game.GameValuesRecalculate;
import com.prineside.tdi2.events.game.GateChange;
import com.prineside.tdi2.events.game.ModifierPlace;
import com.prineside.tdi2.events.game.PathfindingRebuild;
import com.prineside.tdi2.events.game.TileChange;
import com.prineside.tdi2.events.game.TowerPlace;
import com.prineside.tdi2.gates.TeleportGate;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.pathfinding.HeavyPathNode;
import com.prineside.tdi2.pathfinding.Path;
import com.prineside.tdi2.pathfinding.PathConnection;
import com.prineside.tdi2.systems.RenderSystem;
import com.prineside.tdi2.tiles.SpawnTile;
import com.prineside.tdi2.tiles.TargetTile;
import com.prineside.tdi2.utils.DrawUtils;
import com.prineside.tdi2.utils.MovingAverageFloat;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.HashMap;
import java.util.Iterator;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/PathfindingSystem.class */
public final class PathfindingSystem extends GameSystem {

    @NAGS
    private volatile boolean f;
    private HeavyPathNode[][] g;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3022a = TLog.forClass(PathfindingSystem.class);

    /* renamed from: b, reason: collision with root package name */
    private static final Color f3023b = new Color(0.0f, 1.0f, 0.0f, 0.28f);
    private static final Color c = new Color(1.0f, 1.0f, 0.0f, 0.28f);
    private static final Heuristic<HeavyPathNode> k = new Heuristic<HeavyPathNode>() { // from class: com.prineside.tdi2.systems.PathfindingSystem.1
        @Override // com.badlogic.gdx.ai.pfa.Heuristic
        public float estimate(HeavyPathNode heavyPathNode, HeavyPathNode heavyPathNode2) {
            return StrictMath.abs(heavyPathNode2.x - heavyPathNode.x) + StrictMath.abs(heavyPathNode2.y - heavyPathNode.y);
        }
    };

    @NAGS
    public boolean throwExceptionOnMissingPath = true;
    private boolean d = true;
    private boolean e = false;
    public Array<HeavyPathNode> pathfindingNodes = new Array<>(true, 1, HeavyPathNode.class);
    private IntMap<IntMap<Path>> h = new IntMap<>();
    private int i = -1;

    @NAGS
    private final IntMap<IndexedGraph<HeavyPathNode>> j = new IntMap<>();

    @NAGS
    private final HashMap<PathfindingParameterizedConfig, Path> l = new HashMap<>();

    @NAGS
    private final HashMap<SharedPathfindingConfig, Path> m = new HashMap<>();

    @NAGS
    private final Array<Map.EnemyTypeSpawnPair> n = new Array<>();

    @NAGS
    private final IntSet o = new IntSet();

    @NAGS
    private final IntMap<Array<TeleportGate>> p = new IntMap<>();

    static {
        new MovingAverageFloat(100);
    }

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeBoolean(this.d);
        output.writeBoolean(this.e);
        kryo.writeObject(output, this.pathfindingNodes);
        kryo.writeObjectOrNull(output, this.g, HeavyPathNode[][].class);
        kryo.writeObject(output, this.h);
        output.writeInt(this.i);
    }

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.d = input.readBoolean();
        this.e = input.readBoolean();
        this.pathfindingNodes = (Array) kryo.readObject(input, Array.class);
        this.g = (HeavyPathNode[][]) kryo.readObjectOrNull(input, HeavyPathNode[][].class);
        this.h = (IntMap) kryo.readObject(input, IntMap.class);
        this.i = input.readInt();
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void setup() {
        this.S.events.getListeners(GateChange.class).addStateAffecting(new OnGateChange(this, (byte) 0)).setDescription("PathfindingSystem");
        this.S.events.getListeners(TileChange.class).addStateAffecting(new OnTileChange(this, (byte) 0)).setDescription("PathfindingSystem");
        this.S.events.getListeners(TowerPlace.class).addStateAffecting(new OnTowerPlace(this, (byte) 0)).setDescription("PathfindingSystem");
        this.S.events.getListeners(ModifierPlace.class).addStateAffecting(new OnModifierPlace(this, (byte) 0)).setDescription("PathfindingSystem");
        this.S.events.getListeners(BuildingRemove.class).addStateAffecting(new OnBuildingRemove(this, (byte) 0)).setDescription("PathfindingSystem");
        this.S.events.getListeners(GameValuesRecalculate.class).addStateAffecting(new OnGameValuesRecalculate(this)).setDescription("PathfindingSystem");
    }

    private void a() {
        if (this.S.CFG.headless) {
            return;
        }
        this.S._render.addLayer(new RenderSystem.Layer(GameRenderingOrder.MAP_RENDERING_POST_DRAW + 10, false, (batch, f, f2, f3) -> {
            drawDebug(batch);
        }));
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void postSetup() {
        b();
        a();
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void postStateRestore() {
        a();
    }

    public final boolean isWalkablePlatforms() {
        return this.e;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        boolean booleanValue = this.S.gameValue.getBooleanValue(GameValueType.ENEMIES_WALK_ON_PLATFORMS);
        if (booleanValue != this.e) {
            this.e = booleanValue;
            forcePathfindingRebuild();
        }
    }

    public final boolean canAllEnemiesFindPath() {
        return getEnemyTypesThatCantFindPath().size == 0;
    }

    public final void forcePathfindingRebuild() {
        this.d = true;
    }

    @Null
    public final Path findPathBetweenXY(int i, int i2, int i3, int i4, @Null EnemyType enemyType) {
        Path path;
        Path path2;
        rebuildPathfindingIfNeeded();
        if (enemyType != null) {
            enemyType = EnemyType.getMainEnemyType(enemyType);
        }
        PathfindingParameterizedConfig pathfindingParameterizedConfig = new PathfindingParameterizedConfig((byte) 0);
        pathfindingParameterizedConfig.f3029a = enemyType;
        pathfindingParameterizedConfig.f3030b = i;
        pathfindingParameterizedConfig.c = i2;
        pathfindingParameterizedConfig.d = i3;
        pathfindingParameterizedConfig.e = i4;
        synchronized (this.l) {
            path = this.l.get(pathfindingParameterizedConfig);
        }
        if (path != null) {
            if (Path.EMPTY == path) {
                path2 = null;
            } else {
                path2 = path;
            }
        } else {
            HeavyPathNode heavyPathNode = this.g[pathfindingParameterizedConfig.c][pathfindingParameterizedConfig.f3030b];
            HeavyPathNode heavyPathNode2 = this.g[pathfindingParameterizedConfig.e][pathfindingParameterizedConfig.d];
            DefaultGraphPath defaultGraphPath = new DefaultGraphPath();
            if (new IndexedAStarPathFinder(a(enemyType)).searchNodePath(heavyPathNode, heavyPathNode2, k, defaultGraphPath)) {
                SharedPathfindingConfig sharedPathfindingConfig = new SharedPathfindingConfig((byte) 0);
                sharedPathfindingConfig.set(defaultGraphPath);
                Path path3 = this.m.get(sharedPathfindingConfig);
                if (path3 == null) {
                    path2 = new Path((DefaultGraphPath<HeavyPathNode>) defaultGraphPath);
                    this.m.put(sharedPathfindingConfig, path2);
                } else {
                    path2 = path3;
                }
                synchronized (this.l) {
                    this.l.put(new PathfindingParameterizedConfig(pathfindingParameterizedConfig, (byte) 0), path2);
                }
            } else {
                synchronized (this.l) {
                    this.l.put(new PathfindingParameterizedConfig(pathfindingParameterizedConfig, (byte) 0), Path.EMPTY);
                }
                path2 = null;
            }
        }
        return path2;
    }

    @Null
    public final Path findPathBetweenTiles(Tile tile, Tile tile2) {
        if (tile == null) {
            throw new IllegalArgumentException("startTile is null");
        }
        if (tile2 == null) {
            throw new IllegalArgumentException("targetTile is null");
        }
        return findPathBetweenXY(tile.getX(), tile.getY(), tile2.getX(), tile2.getY(), null);
    }

    @Null
    public final Path findPathToTargetTile(Tile tile, @Null EnemyType enemyType) {
        TargetTile targetTileOrThrow = this.S.map.getMap().getTargetTileOrThrow();
        if (tile == null) {
            throw new IllegalArgumentException("startTile is null");
        }
        if (targetTileOrThrow == null) {
            throw new IllegalArgumentException("targetTile is null");
        }
        return findPathBetweenXY(tile.getX(), tile.getY(), targetTileOrThrow.getX(), targetTileOrThrow.getY(), enemyType);
    }

    private static int a(Tile tile) {
        return (tile.getY() << 11) + tile.getX();
    }

    public final Path getDefaultPathWithoutStateChanges(EnemyType enemyType, SpawnTile spawnTile) {
        if (spawnTile == null) {
            throw new IllegalArgumentException("spawnTile is null");
        }
        EnemyType mainEnemyType = EnemyType.getMainEnemyType(enemyType);
        int a2 = a(spawnTile);
        if (!this.h.containsKey(a2)) {
            return null;
        }
        IntMap<Path> intMap = this.h.get(a2);
        if (!intMap.containsKey(mainEnemyType.ordinal())) {
            return null;
        }
        return intMap.get(mainEnemyType.ordinal());
    }

    private IndexedGraph<HeavyPathNode> a(@Null EnemyType enemyType) {
        int ordinal = enemyType == null ? -1 : enemyType.ordinal();
        IndexedGraph<HeavyPathNode> indexedGraph = this.j.get(ordinal);
        IndexedGraph<HeavyPathNode> indexedGraph2 = indexedGraph;
        if (indexedGraph == null) {
            if (enemyType == null) {
                indexedGraph2 = new NoEnemyMapGraph(this.pathfindingNodes, (byte) 0);
            } else {
                indexedGraph2 = new EnemyTypeMapGraph(this.pathfindingNodes, this.S.map.getMap(), enemyType, (byte) 0);
            }
            this.j.put(ordinal, indexedGraph2);
        }
        return indexedGraph2;
    }

    public final void rebuildPathfindingIfNeeded() {
        if (this.d) {
            rebuildPathfinding();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void rebuildPathfinding() {
        int i;
        int i2;
        HeavyPathNode heavyPathNode;
        HeavyPathNode heavyPathNode2;
        HeavyPathNode heavyPathNode3;
        HeavyPathNode heavyPathNode4;
        if (this.f) {
            throw new IllegalStateException("already rebuilding");
        }
        this.f = true;
        this.j.clear();
        synchronized (this.l) {
            this.l.clear();
        }
        this.m.clear();
        int i3 = 0;
        this.pathfindingNodes.clear();
        Map map = this.S.map.getMap();
        if (this.g == null || this.g.length != map.getHeight() || this.g[0].length != map.getWidth()) {
            this.g = new HeavyPathNode[map.getHeight()][map.getWidth()];
        }
        boolean isWalkablePlatforms = isWalkablePlatforms();
        short s = 0;
        while (true) {
            short s2 = s;
            if (s2 >= map.getHeight()) {
                break;
            }
            short s3 = 0;
            while (true) {
                short s4 = s3;
                if (s4 < map.getWidth()) {
                    Tile tile = map.getTile(s4, s2);
                    HeavyPathNode heavyPathNode5 = new HeavyPathNode();
                    heavyPathNode5.setup(i3, s4, s2, tile == null ? 6.871948E10f : tile.getWalkCost(isWalkablePlatforms));
                    this.g[s2][s4] = heavyPathNode5;
                    this.pathfindingNodes.add(heavyPathNode5);
                    i3++;
                    s3 = (short) (s4 + 1);
                }
            }
            s = (short) (s2 + 1);
        }
        this.o.clear();
        for (int i4 = 0; i4 < this.pathfindingNodes.size; i4++) {
            HeavyPathNode heavyPathNode6 = this.pathfindingNodes.items[i4];
            heavyPathNode6.connections.clear();
            if (heavyPathNode6.x > 0 && (heavyPathNode4 = this.g[heavyPathNode6.y][heavyPathNode6.x - 1]) != null) {
                a(heavyPathNode6, heavyPathNode4, false);
            }
            if (heavyPathNode6.y > 0 && (heavyPathNode3 = this.g[heavyPathNode6.y - 1][heavyPathNode6.x]) != null) {
                a(heavyPathNode6, heavyPathNode3, false);
            }
        }
        map.getTeleportGates(this.p);
        Iterator<IntMap.Entry<Array<TeleportGate>>> it = this.p.iterator();
        while (it.hasNext()) {
            IntMap.Entry<Array<TeleportGate>> next = it.next();
            if (next.value.size > 1) {
                Array<TeleportGate> array = next.value;
                for (int i5 = 0; i5 < array.size; i5++) {
                    TeleportGate teleportGate = array.items[i5];
                    HeavyPathNode heavyPathNode7 = (teleportGate.getX() == map.getWidth() || teleportGate.getY() == map.getHeight()) ? null : this.g[teleportGate.getY()][teleportGate.getX()];
                    if (!teleportGate.isLeftSide()) {
                        heavyPathNode = (teleportGate.getX() == map.getWidth() || teleportGate.getY() == 0) ? null : this.g[teleportGate.getY() - 1][teleportGate.getX()];
                    } else {
                        heavyPathNode = (teleportGate.getX() == 0 || teleportGate.getY() == map.getHeight()) ? null : this.g[teleportGate.getY()][teleportGate.getX() - 1];
                    }
                    for (int i6 = 0; i6 < array.size; i6++) {
                        if (i6 != i5) {
                            TeleportGate teleportGate2 = array.items[i6];
                            HeavyPathNode heavyPathNode8 = (teleportGate2.getX() == map.getWidth() || teleportGate2.getY() == map.getHeight()) ? null : this.g[teleportGate2.getY()][teleportGate2.getX()];
                            if (!teleportGate2.isLeftSide()) {
                                heavyPathNode2 = (teleportGate2.getX() == map.getWidth() || teleportGate2.getY() == 0) ? null : this.g[teleportGate2.getY() - 1][teleportGate2.getX()];
                            } else {
                                heavyPathNode2 = (teleportGate2.getX() == 0 || teleportGate2.getY() == map.getHeight()) ? null : this.g[teleportGate2.getY()][teleportGate2.getX() - 1];
                            }
                            if (heavyPathNode7 != null) {
                                if (heavyPathNode8 != null) {
                                    a(heavyPathNode7, heavyPathNode8, true);
                                }
                                if (heavyPathNode2 != null) {
                                    a(heavyPathNode7, heavyPathNode2, true);
                                }
                            }
                            if (heavyPathNode != null) {
                                if (heavyPathNode8 != null) {
                                    a(heavyPathNode, heavyPathNode8, true);
                                }
                                if (heavyPathNode2 != null) {
                                    a(heavyPathNode, heavyPathNode2, true);
                                }
                            }
                        }
                    }
                    if (heavyPathNode7 != null) {
                        if (teleportGate.isLeftSide()) {
                            heavyPathNode7.teleportIndices[0] = teleportGate.index;
                        } else {
                            heavyPathNode7.teleportIndices[3] = teleportGate.index;
                        }
                    }
                    if (heavyPathNode != null) {
                        if (teleportGate.isLeftSide()) {
                            heavyPathNode.teleportIndices[1] = teleportGate.index;
                        } else {
                            heavyPathNode.teleportIndices[2] = teleportGate.index;
                        }
                    }
                }
            }
        }
        this.d = false;
        this.f = false;
        this.h.clear();
        Array tilesByType = map.getTilesByType(SpawnTile.class);
        IntMap<? extends IntMap<Path>> intMap = new IntMap<>();
        Threads.i().concurrentLoop(tilesByType, (i7, spawnTile) -> {
            IntMap intMap2 = new IntMap();
            int a2 = a(spawnTile);
            synchronized (intMap) {
                intMap.put(a2, intMap2);
            }
            Array<SpawnTile.AllowedEnemyConfig> allowedEnemies = spawnTile.getAllowedEnemies();
            for (int i7 = 0; i7 < allowedEnemies.size; i7++) {
                EnemyType enemyType = allowedEnemies.items[i7].enemyType;
                try {
                    Path findPathToTargetTile = findPathToTargetTile(spawnTile, enemyType);
                    if (findPathToTargetTile != null) {
                        intMap2.put(enemyType.ordinal(), findPathToTargetTile);
                    }
                } catch (Exception e) {
                    if (this.throwExceptionOnMissingPath) {
                        throw new Map.PathNotFoundForEnemyTypeException(enemyType, e);
                    }
                    intMap2.put(enemyType.ordinal(), null);
                }
            }
        });
        this.h.putAll(intMap);
        int i8 = 1;
        for (int i9 = 0; i9 < tilesByType.size; i9++) {
            SpawnTile spawnTile2 = ((SpawnTile[]) tilesByType.items)[i9];
            IntMap<Path> intMap2 = intMap.get(a(spawnTile2));
            Array<SpawnTile.AllowedEnemyConfig> allowedEnemies = spawnTile2.getAllowedEnemies();
            for (int i10 = 0; i10 < allowedEnemies.size; i10++) {
                Path path = intMap2.get(allowedEnemies.items[i10].enemyType.ordinal());
                if (path != null) {
                    i = i8 * 31;
                    i2 = path.hashCode();
                } else {
                    i = i8 * 31;
                    i2 = 9001;
                }
                i8 = i + i2;
            }
        }
        boolean z = this.i != i8;
        this.i = i8;
        this.S.events.trigger(new PathfindingRebuild(z));
    }

    private static int a(int i, int i2, int i3, int i4) {
        return i + (i2 << 7) + ((i3 << 7) << 7) + (((i4 << 7) << 7) << 7);
    }

    private void a(HeavyPathNode heavyPathNode, HeavyPathNode heavyPathNode2, boolean z) {
        int min = StrictMath.min(a(heavyPathNode.x, heavyPathNode.y, heavyPathNode2.x, heavyPathNode2.y), a(heavyPathNode2.x, heavyPathNode2.y, heavyPathNode.x, heavyPathNode.y));
        if (z) {
            min = -min;
        }
        if (!this.o.contains(min)) {
            this.o.add(min);
            heavyPathNode.connections.add(new PathConnection(this.pathfindingNodes, heavyPathNode.index, heavyPathNode2.index, z, heavyPathNode2.cost));
            heavyPathNode2.connections.add(new PathConnection(this.pathfindingNodes, heavyPathNode2.index, heavyPathNode.index, z, heavyPathNode.cost));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Array<Map.EnemyTypeSpawnPair> getEnemyTypesThatCantFindPath() {
        this.n.clear();
        rebuildPathfindingIfNeeded();
        Array tilesByType = this.S.map.getMap().getTilesByType(SpawnTile.class);
        for (int i = 0; i < tilesByType.size; i++) {
            SpawnTile spawnTile = ((SpawnTile[]) tilesByType.items)[i];
            int a2 = a(spawnTile);
            Array<SpawnTile.AllowedEnemyConfig> allowedEnemies = spawnTile.getAllowedEnemies();
            for (int i2 = 0; i2 < allowedEnemies.size; i2++) {
                if (!this.h.containsKey(a2) || !this.h.get(a2).containsKey(allowedEnemies.get(i2).enemyType.ordinal())) {
                    this.n.add(new Map.EnemyTypeSpawnPair(allowedEnemies.get(i2).enemyType, spawnTile));
                }
            }
        }
        return this.n;
    }

    public final Path getDefaultPath(EnemyType enemyType, SpawnTile spawnTile) {
        if (spawnTile == null) {
            throw new IllegalArgumentException("spawnTile is null");
        }
        rebuildPathfindingIfNeeded();
        int a2 = a(spawnTile);
        if (!this.h.containsKey(a2)) {
            if (this.throwExceptionOnMissingPath) {
                f3022a.e(this.h.size + " total paths:", new Object[0]);
                Iterator<IntMap.Entry<IntMap<Path>>> it = this.h.iterator();
                while (it.hasNext()) {
                    IntMap.Entry<IntMap<Path>> next = it.next();
                    f3022a.e(" - " + next.key + SequenceUtils.SPACE + next.value, new Object[0]);
                }
                throw new IllegalArgumentException("No paths from " + spawnTile);
            }
            return null;
        }
        EnemyType mainEnemyType = EnemyType.getMainEnemyType(enemyType);
        IntMap<Path> intMap = this.h.get(a2);
        if (!intMap.containsKey(mainEnemyType.ordinal())) {
            if (this.throwExceptionOnMissingPath) {
                throw new IllegalArgumentException("No paths from " + spawnTile + " for enemy type " + mainEnemyType.name());
            }
            return null;
        }
        return intMap.get(mainEnemyType.ordinal());
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void update(float f) {
        rebuildPathfindingIfNeeded();
    }

    public final void drawDebug(Batch batch) {
        if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DBG_DRAW_PATHFINDING) != 0.0d) {
            ResourcePack.AtlasTextureRegion blankWhiteTextureRegion = Game.i.assetManager.getBlankWhiteTextureRegion();
            BitmapFont debugFont = Game.i.assetManager.getDebugFont(false);
            debugFont.setColor(1.0f, 0.3f, 1.0f, 0.56f);
            for (int i = 0; i < this.pathfindingNodes.size; i++) {
                HeavyPathNode heavyPathNode = this.pathfindingNodes.items[i];
                for (int i2 = 0; i2 < heavyPathNode.connections.size; i2++) {
                    Connection<HeavyPathNode> connection = heavyPathNode.connections.items[i2];
                    HeavyPathNode fromNode = connection.getFromNode();
                    HeavyPathNode toNode = connection.getToNode();
                    DrawUtils.texturedLineD(batch, blankWhiteTextureRegion, (fromNode.x << 7) + 64, (fromNode.y << 7) + 64, (toNode.x << 7) + 64, (toNode.y << 7) + 64, 6.0f, 1.0f, f3023b, c);
                }
                debugFont.draw(batch, String.valueOf(heavyPathNode.index), heavyPathNode.x << 7, (heavyPathNode.y << 7) + 30.0f + 64.0f, 128.0f, 1, false);
            }
            debugFont.setColor(Color.WHITE);
        }
    }

    @Override // com.prineside.tdi2.GameSystem
    public final boolean affectsGameState() {
        return true;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final String getSystemName() {
        return "Pathfinding";
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/PathfindingSystem$PathfindingParameterizedConfig.class */
    public static final class PathfindingParameterizedConfig {

        /* renamed from: a, reason: collision with root package name */
        private EnemyType f3029a;

        /* renamed from: b, reason: collision with root package name */
        private int f3030b;
        private int c;
        private int d;
        private int e;

        /* synthetic */ PathfindingParameterizedConfig(byte b2) {
            this();
        }

        /* synthetic */ PathfindingParameterizedConfig(PathfindingParameterizedConfig pathfindingParameterizedConfig, byte b2) {
            this(pathfindingParameterizedConfig);
        }

        private PathfindingParameterizedConfig() {
        }

        private PathfindingParameterizedConfig(PathfindingParameterizedConfig pathfindingParameterizedConfig) {
            this.f3029a = pathfindingParameterizedConfig.f3029a;
            this.f3030b = pathfindingParameterizedConfig.f3030b;
            this.c = pathfindingParameterizedConfig.c;
            this.d = pathfindingParameterizedConfig.d;
            this.e = pathfindingParameterizedConfig.e;
        }

        public final int hashCode() {
            return ((((((((31 + (this.f3029a == null ? -1 : this.f3029a.ordinal())) * 31) + this.f3030b) * 31) + this.c) * 31) + this.d) * 31) + this.e;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof PathfindingParameterizedConfig)) {
                return false;
            }
            PathfindingParameterizedConfig pathfindingParameterizedConfig = (PathfindingParameterizedConfig) obj;
            return pathfindingParameterizedConfig.f3029a == this.f3029a && pathfindingParameterizedConfig.f3030b == this.f3030b && pathfindingParameterizedConfig.c == this.c && pathfindingParameterizedConfig.d == this.d && pathfindingParameterizedConfig.e == this.e;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/PathfindingSystem$SharedPathfindingConfig.class */
    public static final class SharedPathfindingConfig {

        /* renamed from: a, reason: collision with root package name */
        private final ByteArray f3031a;

        /* synthetic */ SharedPathfindingConfig(byte b2) {
            this();
        }

        private SharedPathfindingConfig() {
            this.f3031a = new ByteArray();
        }

        public final void set(GraphPath<HeavyPathNode> graphPath) {
            this.f3031a.clear();
            int count = graphPath.getCount();
            for (int i = 0; i < count; i++) {
                HeavyPathNode heavyPathNode = graphPath.get(i);
                this.f3031a.add((byte) heavyPathNode.x, (byte) heavyPathNode.y);
            }
        }

        public final int hashCode() {
            int i = 1;
            int i2 = this.f3031a.size;
            for (int i3 = 0; i3 < i2; i3++) {
                i = (i * 31) + this.f3031a.items[i3];
            }
            return i;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof SharedPathfindingConfig)) {
                return false;
            }
            SharedPathfindingConfig sharedPathfindingConfig = (SharedPathfindingConfig) obj;
            if (sharedPathfindingConfig.f3031a.size != this.f3031a.size) {
                return false;
            }
            for (int i = 0; i < this.f3031a.size; i++) {
                if (sharedPathfindingConfig.f3031a.items[i] != this.f3031a.items[i]) {
                    return false;
                }
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/PathfindingSystem$NoEnemyMapGraph.class */
    public static final class NoEnemyMapGraph implements IndexedGraph<HeavyPathNode> {

        /* renamed from: a, reason: collision with root package name */
        private final Array<HeavyPathNode> f3028a;

        /* synthetic */ NoEnemyMapGraph(Array array, byte b2) {
            this(array);
        }

        private NoEnemyMapGraph(Array<HeavyPathNode> array) {
            this.f3028a = array;
        }

        @Override // com.badlogic.gdx.ai.pfa.Graph
        public final Array<Connection<HeavyPathNode>> getConnections(HeavyPathNode heavyPathNode) {
            return heavyPathNode.connections;
        }

        @Override // com.badlogic.gdx.ai.pfa.indexed.IndexedGraph
        public final int getIndex(HeavyPathNode heavyPathNode) {
            return heavyPathNode.index;
        }

        @Override // com.badlogic.gdx.ai.pfa.indexed.IndexedGraph
        public final int getNodeCount() {
            return this.f3028a.size;
        }
    }

    @Deprecated
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/PathfindingSystem$EnemyTypeMapGraph2.class */
    private static final class EnemyTypeMapGraph2 implements IndexedGraph<HeavyPathNode> {

        /* renamed from: a, reason: collision with root package name */
        private final Array<HeavyPathNode> f3026a;

        /* renamed from: b, reason: collision with root package name */
        private final Array<Array<Connection<HeavyPathNode>>> f3027b;

        @Override // com.badlogic.gdx.ai.pfa.Graph
        public final Array<Connection<HeavyPathNode>> getConnections(HeavyPathNode heavyPathNode) {
            return this.f3027b.items[heavyPathNode.index];
        }

        @Override // com.badlogic.gdx.ai.pfa.indexed.IndexedGraph
        public final int getIndex(HeavyPathNode heavyPathNode) {
            return heavyPathNode.index;
        }

        @Override // com.badlogic.gdx.ai.pfa.indexed.IndexedGraph
        public final int getNodeCount() {
            return this.f3026a.size;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/PathfindingSystem$EnemyTypeMapGraph.class */
    public static final class EnemyTypeMapGraph implements IndexedGraph<HeavyPathNode> {

        /* renamed from: a, reason: collision with root package name */
        private final Array<HeavyPathNode> f3024a;

        /* renamed from: b, reason: collision with root package name */
        private final Map f3025b;
        private final EnemyType c;
        private final Array<Connection<HeavyPathNode>>[] d;

        /* synthetic */ EnemyTypeMapGraph(Array array, Map map, EnemyType enemyType, byte b2) {
            this(array, map, enemyType);
        }

        private EnemyTypeMapGraph(Array<HeavyPathNode> array, Map map, EnemyType enemyType) {
            this.f3024a = array;
            this.f3025b = map;
            this.c = enemyType;
            this.d = new Array[array.size];
        }

        @Override // com.badlogic.gdx.ai.pfa.Graph
        public final Array<Connection<HeavyPathNode>> getConnections(HeavyPathNode heavyPathNode) {
            Array<Connection<HeavyPathNode>> array = this.d[heavyPathNode.index];
            Array<Connection<HeavyPathNode>> array2 = array;
            if (array == null) {
                array2 = new Array<>(true, 4, Connection.class);
                int i = heavyPathNode.connections.size;
                for (int i2 = 0; i2 < i; i2++) {
                    if (((PathConnection) heavyPathNode.connections.items[i2]).isTeleport) {
                        array2.add(heavyPathNode.connections.items[i2]);
                    } else {
                        Connection<HeavyPathNode> connection = heavyPathNode.connections.items[i2];
                        HeavyPathNode toNode = connection.getToNode();
                        Gate gate = null;
                        if (heavyPathNode.y == toNode.y) {
                            if (heavyPathNode.x + 1 == toNode.x) {
                                gate = this.f3025b.getGate(toNode.x, toNode.y, true);
                            } else if (toNode.x + 1 == heavyPathNode.x) {
                                gate = this.f3025b.getGate(heavyPathNode.x, heavyPathNode.y, true);
                            }
                        } else if (heavyPathNode.x == toNode.x) {
                            if (heavyPathNode.y + 1 == toNode.y) {
                                gate = this.f3025b.getGate(toNode.x, toNode.y, false);
                            } else if (heavyPathNode.y == toNode.y + 1) {
                                gate = this.f3025b.getGate(heavyPathNode.x, heavyPathNode.y, false);
                            }
                        }
                        if (!(gate instanceof GateBarrier) || ((GateBarrier) gate).canEnemyPass(this.c)) {
                            array2.add(connection);
                        } else {
                            array2.add(new PathConnection(this.f3024a, connection.getFromNode().index, connection.getToNode().index, false, 6.871948E10f));
                        }
                    }
                }
                this.d[heavyPathNode.index] = array2;
            }
            return array2;
        }

        @Override // com.badlogic.gdx.ai.pfa.indexed.IndexedGraph
        public final int getIndex(HeavyPathNode heavyPathNode) {
            return heavyPathNode.index;
        }

        @Override // com.badlogic.gdx.ai.pfa.indexed.IndexedGraph
        public final int getNodeCount() {
            return this.f3024a.size;
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/PathfindingSystem$OnGateChange.class */
    public static final class OnGateChange extends SerializableListener<PathfindingSystem> implements Listener<GateChange> {
        /* synthetic */ OnGateChange(PathfindingSystem pathfindingSystem, byte b2) {
            this(pathfindingSystem);
        }

        private OnGateChange() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        private OnGateChange(PathfindingSystem pathfindingSystem) {
            this.f1759a = pathfindingSystem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(GateChange gateChange) {
            ((PathfindingSystem) this.f1759a).forcePathfindingRebuild();
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/PathfindingSystem$OnTileChange.class */
    public static final class OnTileChange extends SerializableListener<PathfindingSystem> implements Listener<TileChange> {
        /* synthetic */ OnTileChange(PathfindingSystem pathfindingSystem, byte b2) {
            this(pathfindingSystem);
        }

        private OnTileChange() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        private OnTileChange(PathfindingSystem pathfindingSystem) {
            this.f1759a = pathfindingSystem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(TileChange tileChange) {
            ((PathfindingSystem) this.f1759a).forcePathfindingRebuild();
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/PathfindingSystem$OnTowerPlace.class */
    public static final class OnTowerPlace extends SerializableListener<PathfindingSystem> implements Listener<TowerPlace> {
        /* synthetic */ OnTowerPlace(PathfindingSystem pathfindingSystem, byte b2) {
            this(pathfindingSystem);
        }

        private OnTowerPlace() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        private OnTowerPlace(PathfindingSystem pathfindingSystem) {
            this.f1759a = pathfindingSystem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(TowerPlace towerPlace) {
            if (((PathfindingSystem) this.f1759a).isWalkablePlatforms()) {
                ((PathfindingSystem) this.f1759a).forcePathfindingRebuild();
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/PathfindingSystem$OnModifierPlace.class */
    public static final class OnModifierPlace extends SerializableListener<PathfindingSystem> implements Listener<ModifierPlace> {
        /* synthetic */ OnModifierPlace(PathfindingSystem pathfindingSystem, byte b2) {
            this(pathfindingSystem);
        }

        private OnModifierPlace() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        private OnModifierPlace(PathfindingSystem pathfindingSystem) {
            this.f1759a = pathfindingSystem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(ModifierPlace modifierPlace) {
            if (((PathfindingSystem) this.f1759a).isWalkablePlatforms()) {
                ((PathfindingSystem) this.f1759a).forcePathfindingRebuild();
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/PathfindingSystem$OnBuildingRemove.class */
    public static final class OnBuildingRemove extends SerializableListener<PathfindingSystem> implements Listener<BuildingRemove> {
        /* synthetic */ OnBuildingRemove(PathfindingSystem pathfindingSystem, byte b2) {
            this(pathfindingSystem);
        }

        private OnBuildingRemove() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        private OnBuildingRemove(PathfindingSystem pathfindingSystem) {
            this.f1759a = pathfindingSystem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(BuildingRemove buildingRemove) {
            if (((PathfindingSystem) this.f1759a).isWalkablePlatforms()) {
                ((PathfindingSystem) this.f1759a).forcePathfindingRebuild();
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/PathfindingSystem$OnGameValuesRecalculate.class */
    public static final class OnGameValuesRecalculate extends SerializableListener<PathfindingSystem> implements Listener<GameValuesRecalculate> {
        private OnGameValuesRecalculate() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public OnGameValuesRecalculate(PathfindingSystem pathfindingSystem) {
            this.f1759a = pathfindingSystem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(GameValuesRecalculate gameValuesRecalculate) {
            if (((PathfindingSystem) this.f1759a).S.gameValue.getBooleanValue(GameValueType.ENEMIES_WALK_ON_PLATFORMS) != ((PathfindingSystem) this.f1759a).e) {
                ((PathfindingSystem) this.f1759a).b();
            }
        }
    }
}
