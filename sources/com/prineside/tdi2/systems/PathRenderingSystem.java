package com.prineside.tdi2.systems;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.EnemyGroup;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystem;
import com.prineside.tdi2.Map;
import com.prineside.tdi2.configs.GameRenderingOrder;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.events.game.GameResumed;
import com.prineside.tdi2.events.game.PathfindingRebuild;
import com.prineside.tdi2.events.game.WaveStatusChange;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.pathfinding.Path;
import com.prineside.tdi2.pathfinding.PathSegmentForRendering;
import com.prineside.tdi2.pathfinding.SideShift;
import com.prineside.tdi2.systems.RenderSystem;
import com.prineside.tdi2.systems.WaveSystem;
import com.prineside.tdi2.tiles.SpawnTile;
import com.prineside.tdi2.utils.FastRandom;
import com.prineside.tdi2.utils.NAGS;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

@NAGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/PathRenderingSystem.class */
public final class PathRenderingSystem extends GameSystem {

    /* renamed from: a, reason: collision with root package name */
    private float f3018a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f3019b;
    private static final Vector2 h = new Vector2();
    public boolean dontDrawOverPlatforms = false;
    private final Array<PathConfig> c = new Array<>(PathConfig.class);
    private final Pool<PathConfig> d = new Pool<PathConfig>(this, 1, 512) { // from class: com.prineside.tdi2.systems.PathRenderingSystem.1
        {
            super(1, 512);
        }

        @Override // com.badlogic.gdx.utils.Pool
        protected /* synthetic */ PathConfig newObject() {
            return a();
        }

        private static PathConfig a() {
            return new PathConfig((byte) 0);
        }
    };
    private boolean e = false;
    private boolean f = false;
    private final TextureRegion[] g = new TextureRegion[4];

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/PathRenderingSystem$PathEnemyPair.class */
    public static class PathEnemyPair {
        public Path path;
        public EnemyType enemyType;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final boolean affectsGameState() {
        return false;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void setup() {
        this.g[PathSegmentForRendering.Direction.TOP.ordinal()] = Game.i.assetManager.getTextureRegion("path-arrow-up");
        this.g[PathSegmentForRendering.Direction.LEFT.ordinal()] = Game.i.assetManager.getTextureRegion("path-arrow-left");
        this.g[PathSegmentForRendering.Direction.RIGHT.ordinal()] = Game.i.assetManager.getTextureRegion("path-arrow-right");
        this.g[PathSegmentForRendering.Direction.BOTTOM.ordinal()] = Game.i.assetManager.getTextureRegion("path-arrow-bottom");
        this.S._render.addLayer(new RenderSystem.Layer(GameRenderingOrder.PATH_RENDERING_DRAW, false, (batch, f, f2, f3) -> {
            draw(batch, f2);
        }).setName("PathRendering-draw"));
        this.S.events.getListeners(GameResumed.class).add(gameResumed -> {
            this.e = false;
        }).setDescription("PathRenderingSystem - updates path traces rendering");
        this.S.events.getListeners(WaveStatusChange.class).add(waveStatusChange -> {
            this.e = false;
        }).setDescription("PathRenderingSystem - updates path traces rendering");
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void postSetup() {
        this.S.events.getListeners(PathfindingRebuild.class).add(pathfindingRebuild -> {
            if (pathfindingRebuild.isDefaultPathsAffected()) {
                this.e = false;
            }
        }).setDescription("PathRenderingSystem");
    }

    @Override // com.prineside.tdi2.GameSystem
    public final boolean profileUpdate() {
        return false;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final String getSystemName() {
        return "PathRendering";
    }

    public final void showAllPathTraces(boolean z) {
        this.f = z;
        this.e = false;
    }

    public final void draw(Batch batch, float f) {
        if (!this.e) {
            updatePathTracesRendering();
        }
        if (this.c.size == 0 || Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DBG_DISABLE_PATH_RENDERING) == 1.0d) {
            this.f3018a = 0.0f;
            return;
        }
        if (this.f3019b) {
            this.f3018a -= f;
            if (this.f3018a <= 0.0f) {
                this.f3019b = false;
                this.f3018a = 0.0f;
                for (int i = 0; i < this.c.size; i++) {
                    this.d.free(this.c.items[i]);
                }
                this.c.clear();
                return;
            }
        } else {
            this.f3018a += f;
            if (this.f3018a > 0.66f) {
                this.f3018a = 0.66f;
            }
        }
        batch.setColor(1.0f, 1.0f, 1.0f, this.f3018a);
        boolean z = false;
        for (int i2 = 0; i2 < this.c.size; i2++) {
            PathConfig pathConfig = this.c.items[i2];
            if (pathConfig.e != null) {
                if (!z) {
                    pathConfig.f = pathConfig.e.getPositionSimpleSegmentsForGraphics(pathConfig.d, pathConfig.g);
                    pathConfig.h = pathConfig.e.passesThroughTileType(this.S.map.getMap(), TileType.PLATFORM);
                    pathConfig.e = null;
                    z = true;
                }
            }
            if (!this.dontDrawOverPlatforms || !pathConfig.h) {
                pathConfig.f3020a += f;
                if (pathConfig.f3020a >= 1.0f) {
                    pathConfig.f3020a -= 1.0f;
                    pathConfig.f3021b++;
                }
                float f2 = pathConfig.f3020a * 32.0f;
                int i3 = 0;
                int i4 = pathConfig.f3021b;
                while (f2 < pathConfig.f) {
                    PathSegmentForRendering pathSegmentForRendering = pathConfig.g.items[0];
                    int i5 = i3;
                    while (true) {
                        if (i5 >= pathConfig.g.size) {
                            break;
                        }
                        pathSegmentForRendering = pathConfig.g.items[i5];
                        float f3 = f2 - pathSegmentForRendering.distanceFromStart;
                        if (f3 < pathSegmentForRendering.length) {
                            float f4 = f3 / pathSegmentForRendering.length;
                            h.x = pathSegmentForRendering.x1 + ((pathSegmentForRendering.x2 - pathSegmentForRendering.x1) * f4);
                            h.y = pathSegmentForRendering.y1 + ((pathSegmentForRendering.y2 - pathSegmentForRendering.y1) * f4);
                            break;
                        }
                        i3 = i5;
                        i5++;
                    }
                    if (i4 % 4 == 0) {
                        batch.draw(pathConfig.c, h.x - 11.2f, h.y - 11.2f, 22.4f, 22.4f);
                    } else {
                        batch.draw(this.g[pathSegmentForRendering.direction.ordinal()], h.x - 6.0f, h.y - 6.0f, 12.0f, 12.0f);
                    }
                    f2 += 32.0f;
                    i4--;
                }
            }
        }
        batch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
    }

    public final void setPaths(Array<PathEnemyPair> array) {
        if (array.size == 0) {
            removePaths();
            return;
        }
        this.f3019b = false;
        for (int i = 0; i < this.c.size; i++) {
            this.d.free(this.c.items[i]);
        }
        this.c.clear();
        int min = StrictMath.min(array.size, 11);
        int[] iArr = SideShift.SIDE_SHIFT_BY_COUNT[min];
        for (int i2 = 0; i2 < min; i2++) {
            PathEnemyPair pathEnemyPair = array.items[i2];
            PathConfig obtain = this.d.obtain();
            obtain.f3021b = FastRandom.getInt(4);
            obtain.f3020a = i2 / array.size;
            obtain.c = this.S.enemy == null ? Game.i.enemyManager.getFactory(pathEnemyPair.enemyType).getTexture() : this.S.enemy.getTexture(pathEnemyPair.enemyType);
            obtain.e = pathEnemyPair.path;
            obtain.d = iArr[i2];
            this.c.add(obtain);
        }
    }

    public final void updatePathTracesRendering() {
        if (this.S.gameState == null || !this.S.gameState.canSkipMediaUpdate()) {
            removePaths();
            if (this.f) {
                Map map = this.S.map.getMap();
                Array<PathEnemyPair> array = new Array<>(PathEnemyPair.class);
                Array tilesByType = map.getTilesByType(SpawnTile.class);
                for (int i = 0; i < tilesByType.size; i++) {
                    SpawnTile spawnTile = (SpawnTile) tilesByType.get(i);
                    for (int i2 = 0; i2 < spawnTile.getAllowedEnemies().size; i2++) {
                        EnemyType enemyType = spawnTile.getAllowedEnemies().get(i2).enemyType;
                        Path defaultPathWithoutStateChanges = this.S.pathfinding.getDefaultPathWithoutStateChanges(enemyType, spawnTile);
                        if (defaultPathWithoutStateChanges != null) {
                            PathEnemyPair pathEnemyPair = new PathEnemyPair();
                            pathEnemyPair.path = defaultPathWithoutStateChanges;
                            pathEnemyPair.enemyType = enemyType;
                            array.add(pathEnemyPair);
                        }
                    }
                }
                setPaths(array);
            } else if (this.S.wave != null && (this.S.wave.status == WaveSystem.Status.SPAWNED || this.S.wave.status == WaveSystem.Status.NOT_STARTED)) {
                Array<PathEnemyPair> array2 = new Array<>(PathEnemyPair.class);
                Array tilesByType2 = this.S.map.getMap().getTilesByType(SpawnTile.class);
                for (int i3 = 0; i3 < tilesByType2.size; i3++) {
                    SpawnTile spawnTile2 = (SpawnTile) tilesByType2.get(i3);
                    Array<EnemyGroup.SpawnEnemyGroup> array3 = spawnTile2.enemySpawnQueues.get(1);
                    if (array3.size != 0) {
                        for (int i4 = 0; i4 < array3.size; i4++) {
                            EnemyGroup.SpawnEnemyGroup spawnEnemyGroup = array3.get(i4);
                            Path defaultPath = this.S.pathfinding.getDefaultPath(spawnEnemyGroup.getEnemyType(), spawnTile2);
                            if (defaultPath == null) {
                                throw new RuntimeException("Path is null for " + spawnEnemyGroup.getEnemyType().name() + SequenceUtils.SPACE + spawnTile2.getX() + ":" + spawnTile2.getY());
                            }
                            PathEnemyPair pathEnemyPair2 = new PathEnemyPair();
                            pathEnemyPair2.path = defaultPath;
                            pathEnemyPair2.enemyType = spawnEnemyGroup.getEnemyType();
                            array2.add(pathEnemyPair2);
                        }
                    }
                }
                setPaths(array2);
            }
            this.e = true;
        }
    }

    public final void removePaths() {
        if (this.c.size != 0) {
            this.f3019b = true;
        }
    }

    @Override // com.prineside.tdi2.GameSystem, com.badlogic.gdx.utils.Disposable
    public final void dispose() {
        this.c.clear();
        this.d.clear();
        super.dispose();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/PathRenderingSystem$PathConfig.class */
    public static class PathConfig implements Pool.Poolable {

        /* renamed from: a, reason: collision with root package name */
        float f3020a;

        /* renamed from: b, reason: collision with root package name */
        int f3021b;
        TextureRegion c;
        int d;
        Path e;
        float f;
        Array<PathSegmentForRendering> g;
        boolean h;

        private PathConfig() {
            this.g = new Array<>(PathSegmentForRendering.class);
        }

        /* synthetic */ PathConfig(byte b2) {
            this();
        }

        @Override // com.badlogic.gdx.utils.Pool.Poolable
        public void reset() {
            this.e = null;
            this.h = false;
            this.d = 0;
            this.f3021b = 0;
            this.c = null;
            this.f3020a = 0.0f;
            this.g.clear();
        }
    }
}
