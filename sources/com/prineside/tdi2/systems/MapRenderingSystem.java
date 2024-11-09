package com.prineside.tdi2.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.ObjectSet;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystem;
import com.prineside.tdi2.Gate;
import com.prineside.tdi2.Map;
import com.prineside.tdi2.ResourcePack;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.configs.GameRenderingOrder;
import com.prineside.tdi2.enums.SpaceTileBonusType;
import com.prineside.tdi2.events.game.BuildingRemove;
import com.prineside.tdi2.events.game.EnemyDie;
import com.prineside.tdi2.events.game.MapDrawModeChange;
import com.prineside.tdi2.events.game.MapSizeChange;
import com.prineside.tdi2.events.game.MinerPlace;
import com.prineside.tdi2.events.game.MinerRemove;
import com.prineside.tdi2.events.game.ModifierPlace;
import com.prineside.tdi2.events.game.TileChange;
import com.prineside.tdi2.events.game.TowerAbilityChange;
import com.prineside.tdi2.events.game.TowerAimStrategyChange;
import com.prineside.tdi2.events.game.TowerLevelUp;
import com.prineside.tdi2.events.game.TowerPlace;
import com.prineside.tdi2.events.game.TowerUpgrade;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.systems.RenderSystem;
import com.prineside.tdi2.tiles.PlatformTile;
import com.prineside.tdi2.tiles.SourceTile;
import com.prineside.tdi2.utils.FastRandom;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.SpriteCacheExtended;
import com.prineside.tdi2.utils.logging.TLog;

@NAGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/MapRenderingSystem.class */
public final class MapRenderingSystem extends GameSystem {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2984a = TLog.forClass(MapRenderingSystem.class);
    public static final String CACHED_GFX_LAYER_TILES = "MapRenderingSystem_Tiles";
    public static final String CACHED_GFX_LAYER_TILE_EXTRAS = "MapRenderingSystem_TileExtras";
    public static final String CACHED_GFX_LAYER_BUILDINGS = "MapRenderingSystem_Buildings";

    /* renamed from: b, reason: collision with root package name */
    private boolean f2985b;
    private DrawMode c;
    private TextureRegion[] h;
    private int i;
    public final ObjectSet<SpaceTileBonusType> spaceTileBonusesToDrawColoredOnFreeTiles = new ObjectSet<>();
    public boolean drawMapGrid = false;
    private SplatConfig[] d = new SplatConfig[1024];
    private SplatConfig[] e = new SplatConfig[256];
    private int f = 0;
    private int g = 0;
    private Color j = new Color();

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/MapRenderingSystem$DrawMode.class */
    public enum DrawMode {
        DEFAULT,
        DETAILED,
        MAP_EDITOR,
        FULL;

        public static final DrawMode[] values = values();
    }

    @Override // com.prineside.tdi2.GameSystem
    public final boolean affectsGameState() {
        return false;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final boolean profileUpdate() {
        return false;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final String getSystemName() {
        return "MapRendering";
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void setup() {
        this.S.events.getListeners(EnemyDie.class).add(enemyDie -> {
            if (Game.i.settingsManager.isStainsEnabled()) {
                SplatConfig splatConfig = this.d[this.g];
                SplatConfig splatConfig2 = splatConfig;
                if (splatConfig == null) {
                    splatConfig2 = new SplatConfig(this, (byte) 0);
                    this.d[this.g] = splatConfig2;
                }
                Enemy enemy = enemyDie.getLastDamage().getEnemy();
                this.j.set(enemy.getColor());
                this.j.lerp(0.0f, 0.0f, 0.0f, 1.0f, FastRandom.getFloat() * 0.14f);
                this.j.f889a = 0.5f;
                splatConfig2.f2988b = this.j.toFloatBits();
                splatConfig2.c = enemy.getPosition().x;
                splatConfig2.d = enemy.getPosition().y;
                TextureRegion[] textureRegionArr = this.h;
                int i = this.i;
                this.i = i + 1;
                splatConfig2.f2987a = textureRegionArr[i];
                if (this.i == this.h.length) {
                    this.i = 0;
                }
                this.g++;
                if (this.g == 1024) {
                    if (1024 - this.f >= 0) {
                        System.arraycopy(this.d, 0, this.e, 0, this.e.length);
                        System.arraycopy(this.d, this.f + 1, this.d, 0, 768);
                        System.arraycopy(this.e, 0, this.d, 768, this.e.length);
                    }
                    this.g = 768;
                    this.f = 0;
                }
                if (this.g - this.f == 769) {
                    this.f++;
                }
            }
        });
        if (Game.i.assetManager != null) {
            this.h = new TextureRegion[3];
            this.h[0] = Game.i.assetManager.getTextureRegion("splatter-1");
            this.h[1] = Game.i.assetManager.getTextureRegion("splatter-2");
            this.h[2] = Game.i.assetManager.getTextureRegion("splatter-3");
        }
        this.c = Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.UI_DETAILED_MODE_ENABLED) == 0.0d ? DrawMode.DEFAULT : DrawMode.DETAILED;
        f2984a.i("setting draw mode to " + this.c, new Object[0]);
        this.S._render.addLayer(new RenderSystem.Layer(GameRenderingOrder.MAP_DRAW_TILES, false, (batch, f, f2, f3) -> {
            drawTiles(this.S._render.getCamera());
        }).setName("MapRendering-drawTiles"));
        this.S._render.addLayer(new RenderSystem.Layer(GameRenderingOrder.MAP_DRAW_BUILDINGS_CACHE, false, (batch2, f4, f5, f6) -> {
            drawBuildings(this.S._render.getCamera());
        }).setName("MapRendering-drawBuildings"));
        this.S._render.addLayer(new RenderSystem.Layer(GameRenderingOrder.MAP_DRAW_STAINS, false, (batch3, f7, f8, f9) -> {
            if (Game.i.settingsManager.isStainsEnabled()) {
                drawStains(batch3);
            }
        }).setName("MapRendering-drawStains"));
        this.S._render.addLayer(new RenderSystem.Layer(GameRenderingOrder.MAP_DRAW_TILE_EXTRAS, false, (batch4, f10, f11, f12) -> {
            drawTilesExtras(this.S._render.getCamera());
        }).setName("MapRendering-drawTilesExtras"));
        this.S._render.addLayer(new RenderSystem.Layer(GameRenderingOrder.MAP_DRAW_BATCH, false, (batch5, f13, f14, f15) -> {
            drawBatch(batch5, f14);
        }).setName("MapRendering-drawBatch"));
        this.S._render.addLayer(new RenderSystem.Layer(GameRenderingOrder.MAP_RENDERING_POST_DRAW, false, (batch6, f16, f17, f18) -> {
            postDrawBatch(batch6, f17);
        }).setName("MapRendering-postDrawBatch"));
        this.S.events.getListeners(TowerPlace.class).add(towerPlace -> {
            forceTilesRedraw(false);
            forceBuildingsRedraw();
        }).setDescription("MapRenderingSystem - forces tiles redraw");
        this.S.events.getListeners(TowerAimStrategyChange.class).add(towerAimStrategyChange -> {
            forceBuildingsRedraw();
        }).setDescription("MapRenderingSystem - forces towers redraw");
        this.S.events.getListeners(ModifierPlace.class).add(modifierPlace -> {
            forceTilesRedraw(false);
            forceBuildingsRedraw();
        }).setDescription("MapRenderingSystem - forces tiles redraw");
        this.S.events.getListeners(MinerPlace.class).add(minerPlace -> {
            forceTilesRedraw(false);
            forceBuildingsRedraw();
        }).setDescription("MapRenderingSystem - forces tiles redraw");
        this.S.events.getListeners(MinerRemove.class).add(minerRemove -> {
            forceTilesRedraw(false);
            forceBuildingsRedraw();
        }).setDescription("MapRenderingSystem - forces tiles redraw");
        this.S.events.getListeners(BuildingRemove.class).add(buildingRemove -> {
            forceTilesRedraw(false);
            forceBuildingsRedraw();
        }).setDescription("MapRenderingSystem - forces tiles redraw");
        this.S.events.getListeners(TileChange.class).add(tileChange -> {
            forceTilesRedraw(true);
            forceBuildingsRedraw();
        }).setDescription("MapRenderingSystem - forces redraw");
        this.S.events.getListeners(MapSizeChange.class).add(mapSizeChange -> {
            forceTilesRedraw(true);
            forceBuildingsRedraw();
        }).setDescription("MapRenderingSystem - forces redraw");
        this.S.events.getListeners(TowerUpgrade.class).add(towerUpgrade -> {
            forceBuildingsRedraw();
        }).setDescription("MapRenderingSystem");
        this.S.events.getListeners(TowerLevelUp.class).add(towerLevelUp -> {
            forceBuildingsRedraw();
        }).setDescription("MapRenderingSystem");
        this.S.events.getListeners(TowerAbilityChange.class).add(towerAbilityChange -> {
            forceBuildingsRedraw();
        }).setDescription("MapRenderingSystem");
    }

    public final void switchMapDrawMode() {
        if (this.c == DrawMode.DEFAULT) {
            setDrawMode(DrawMode.DETAILED);
        } else {
            setDrawMode(DrawMode.DEFAULT);
        }
        Game.i.settingsManager.setCustomValue(SettingsManager.CustomValueType.UI_DETAILED_MODE_ENABLED, this.c == DrawMode.DETAILED ? 1.0d : 0.0d);
    }

    public final DrawMode getDrawMode() {
        return this.c;
    }

    public final void forceBuildingsRedraw() {
        this.S._cachedRendering.setLayerDirty(CACHED_GFX_LAYER_BUILDINGS);
    }

    public final void forceTilesRedraw(boolean z) {
        if (z) {
            this.S._cachedRendering.setLayerDirty(CACHED_GFX_LAYER_TILES);
        }
        this.S._cachedRendering.setLayerDirty(CACHED_GFX_LAYER_TILE_EXTRAS);
    }

    public final void setDrawMode(DrawMode drawMode) {
        this.c = drawMode;
        if (this.S._cachedRendering != null) {
            forceTilesRedraw(true);
            forceBuildingsRedraw();
        }
        this.S.events.trigger(new MapDrawModeChange());
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void a() {
        SpriteCacheExtended.CacheArray orAddLayer = this.S._cachedRendering.getOrAddLayer(CACHED_GFX_LAYER_BUILDINGS, 8191, null, true);
        SpriteCacheExtended start = orAddLayer.start();
        Map map = this.S.map.getMap();
        Array.ArrayIterator it = map.getTilesByType(PlatformTile.class).iterator();
        while (it.hasNext()) {
            PlatformTile platformTile = (PlatformTile) it.next();
            if (platformTile.building != null) {
                platformTile.building.drawBase(start, platformTile.getX() << 7, platformTile.getY() << 7, 128.0f, 128.0f, this.c);
                start = orAddLayer.swapCachesIfFull();
            }
        }
        Array.ArrayIterator it2 = map.getTilesByType(SourceTile.class).iterator();
        while (it2.hasNext()) {
            SourceTile sourceTile = (SourceTile) it2.next();
            if (sourceTile.miner != null) {
                sourceTile.miner.drawBase(start, sourceTile.getX() << 7, sourceTile.getY() << 7, 128.0f, 128.0f, this.c);
                start = orAddLayer.swapCachesIfFull();
            }
        }
        orAddLayer.end();
        orAddLayer.dirty = false;
    }

    private void b() {
        long realTickCount = Game.getRealTickCount();
        SpriteCacheExtended.CacheArray orAddLayer = this.S._cachedRendering.getOrAddLayer(CACHED_GFX_LAYER_TILES, 8191, null, true);
        SpriteCacheExtended start = orAddLayer.start();
        Map map = this.S.map.getMap();
        if (this.drawMapGrid) {
            ResourcePack.AtlasTextureRegion atlasTextureRegion = AssetManager.TextureRegions.i().blank;
            int height = map.getHeight();
            for (int i = 1; i < height; i++) {
                if (i % 10 == 0) {
                    start.setColor(MaterialColor.BLUE_GREY.P800);
                } else {
                    start.setColor(MaterialColor.BLUE_GREY.P900);
                }
                start.draw(atlasTextureRegion, 0.0f, (i << 7) - 2.0f, map.getWidth() << 7, 4.0f);
            }
            int width = map.getWidth();
            for (int i2 = 1; i2 < width; i2++) {
                if (i2 % 10 == 0) {
                    start.setColor(MaterialColor.BLUE_GREY.P800);
                } else {
                    start.setColor(MaterialColor.BLUE_GREY.P900);
                }
                start.draw(atlasTextureRegion, (i2 << 7) - 2.0f, 0.0f, 4.0f, map.getHeight() << 7);
            }
            start.setColor(0.0f, 0.0f, 0.0f, 0.14f);
            start.draw(atlasTextureRegion, -16.0f, -16.0f, 16.0f, (map.getHeight() << 7) + 32.0f);
            start.draw(atlasTextureRegion, map.getWidth() << 7, -16.0f, 16.0f, (map.getHeight() << 7) + 32.0f);
            start.draw(atlasTextureRegion, 0.0f, -16.0f, map.getWidth() << 7, 16.0f);
            start.draw(atlasTextureRegion, 0.0f, map.getHeight() << 7, map.getWidth() << 7, 16.0f);
            start.setColor(MaterialColor.BLUE_GREY.P800);
            start.draw(atlasTextureRegion, -8.0f, -8.0f, 8.0f, (map.getHeight() << 7) + 16.0f);
            start.draw(atlasTextureRegion, map.getWidth() << 7, -8.0f, 8.0f, (map.getHeight() << 7) + 16.0f);
            start.draw(atlasTextureRegion, 0.0f, -8.0f, map.getWidth() << 7, 8.0f);
            start.draw(atlasTextureRegion, 0.0f, map.getHeight() << 7, map.getWidth() << 7, 8.0f);
            start.setColor(Color.WHITE);
            Game.i.assetManager.getFont(24).draw(start, map.getWidth() + " x " + map.getHeight(), (map.getWidth() << 7) + 16.0f, (map.getHeight() << 7) + 32.0f, 100.0f, 12, false);
        }
        SpriteCacheExtended swapCachesIfFull = orAddLayer.swapCachesIfFull();
        Array.ArrayIterator<Tile> it = map.getAllTiles().iterator();
        while (it.hasNext()) {
            it.next().drawStatic(swapCachesIfFull, r0.getX() << 7, r0.getY() << 7, 128.0f, 128.0f, map, this.c);
            swapCachesIfFull = orAddLayer.swapCachesIfFull();
        }
        orAddLayer.end();
        orAddLayer.dirty = false;
        if (Game.i.debugManager != null) {
            Game.i.debugManager.registerFrameJob("MapRendering-updateTilesCachedLayer", Game.getRealTickCount() - realTickCount);
        }
    }

    public final void drawTiles(OrthographicCamera orthographicCamera) {
        if (this.f2985b) {
            throw new IllegalStateException("System is already disposed");
        }
        Game.i.renderingManager.stopAnyBatchDrawing();
        if (this.S._cachedRendering.isDirty(CACHED_GFX_LAYER_TILES)) {
            b();
        }
        SpriteCacheExtended.CacheArray layer = this.S._cachedRendering.getLayer(CACHED_GFX_LAYER_TILES);
        if (layer != null) {
            Array.ArrayIterator<SpriteCacheExtended> it = layer.getPreparedCaches().iterator();
            while (it.hasNext()) {
                SpriteCacheExtended next = it.next();
                next.setProjectionMatrix(orthographicCamera.combined);
                next.begin();
                Gdx.gl.glEnable(3042);
                next.draw(next.lastCacheId);
                next.end();
            }
        }
    }

    public final void drawBuildings(OrthographicCamera orthographicCamera) {
        if (this.f2985b) {
            throw new IllegalStateException("System is already disposed");
        }
        Game.i.renderingManager.stopAnyBatchDrawing();
        if (this.S._cachedRendering.isDirty(CACHED_GFX_LAYER_BUILDINGS)) {
            a();
        }
        SpriteCacheExtended.CacheArray layer = this.S._cachedRendering.getLayer(CACHED_GFX_LAYER_BUILDINGS);
        if (layer != null) {
            Array.ArrayIterator<SpriteCacheExtended> it = layer.getPreparedCaches().iterator();
            while (it.hasNext()) {
                SpriteCacheExtended next = it.next();
                next.setProjectionMatrix(orthographicCamera.combined);
                next.begin();
                Gdx.gl.glEnable(3042);
                next.draw(next.lastCacheId);
                next.end();
            }
        }
    }

    public final void drawStains(Batch batch) {
        for (int i = this.f; i < this.g; i++) {
            SplatConfig splatConfig = this.d[i];
            batch.setPackedColor(splatConfig.f2988b);
            batch.draw(splatConfig.f2987a, splatConfig.c - 20.0f, splatConfig.d - 20.0f, 40.0f, 40.0f);
        }
    }

    public final void drawBatch(Batch batch, float f) {
        if (this.f2985b) {
            throw new IllegalStateException("System is already disposed");
        }
        if (f < 0.0f) {
            throw new IllegalArgumentException("deltaTime is " + f);
        }
        Map map = this.S.map.getMap();
        DelayedRemovalArray<Tile> allTiles = map.getAllTiles();
        DelayedRemovalArray<Gate> allGates = map.getAllGates();
        int i = allTiles.size;
        for (int i2 = 0; i2 < i; i2++) {
            allTiles.items[i2].drawBatch(batch, f, r0.getX() << 7, r0.getY() << 7, 128.0f, 128.0f, this.c);
        }
        for (int i3 = 0; i3 < allGates.size; i3++) {
            allGates.items[i3].drawBatch(batch, f, r0.getX() << 7, r0.getY() << 7, 128.0f, 128.0f);
        }
        if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DBG_DRAW_TILE_POS) != 0.0d) {
            BitmapFont debugFont = Game.i.assetManager.getDebugFont(false);
            debugFont.setColor(1.0f, 1.0f, 1.0f, 0.56f);
            for (int i4 = 0; i4 < map.getHeight(); i4++) {
                for (int i5 = 0; i5 < map.getWidth(); i5++) {
                    Tile tile = map.getTile(i5, i4);
                    if (tile != null) {
                        debugFont.draw(batch, tile.type.name(), i5 << 7, (i4 << 7) + 20.0f + 64.0f, 128.0f, 1, false);
                    }
                    debugFont.draw(batch, "xy " + i5 + ":" + i4, i5 << 7, (i4 << 7) + 10.0f + 64.0f, 128.0f, 1, false);
                }
            }
        }
    }

    public final void postDrawBatch(Batch batch, float f) {
        if (this.f2985b) {
            throw new IllegalStateException("System is already disposed");
        }
        DelayedRemovalArray<Tile> allTiles = this.S.map.getMap().getAllTiles();
        int i = allTiles.size;
        for (int i2 = 0; i2 < i; i2++) {
            allTiles.items[i2].postDrawBatch(batch, f, r0.getX() << 7, r0.getY() << 7, 128.0f, 128.0f, this.c);
        }
    }

    private void c() {
        Map map = this.S.map.getMap();
        SpriteCacheExtended.CacheArray orAddLayer = this.S._cachedRendering.getOrAddLayer(CACHED_GFX_LAYER_TILE_EXTRAS, 8191, null, true);
        SpriteCacheExtended start = orAddLayer.start();
        Array.ArrayIterator<Tile> it = map.getAllTiles().iterator();
        while (it.hasNext()) {
            it.next().drawExtras(start, r0.getX() << 7, r0.getY() << 7, 128.0f, 128.0f, this.c);
            start = orAddLayer.swapCachesIfFull();
        }
        for (int i = 0; i < map.getHeight() + 1; i++) {
            for (int i2 = 0; i2 < map.getWidth() + 1; i2++) {
                Gate gate = map.getGate(i2, i, true);
                if (gate != null) {
                    gate.drawStatic(start, i2 << 7, i << 7, 128.0f, 128.0f);
                }
                Gate gate2 = map.getGate(i2, i, false);
                if (gate2 != null) {
                    gate2.drawStatic(start, i2 << 7, i << 7, 128.0f, 128.0f);
                }
                start = orAddLayer.swapCachesIfFull();
            }
        }
        orAddLayer.end();
        orAddLayer.dirty = false;
    }

    public final void drawTilesExtras(OrthographicCamera orthographicCamera) {
        if (this.f2985b) {
            throw new IllegalStateException("System is already disposed");
        }
        Game.i.renderingManager.stopAnyBatchDrawing();
        if (this.S._cachedRendering.isDirty(CACHED_GFX_LAYER_TILE_EXTRAS)) {
            c();
        }
        SpriteCacheExtended.CacheArray layer = this.S._cachedRendering.getLayer(CACHED_GFX_LAYER_TILE_EXTRAS);
        if (layer != null) {
            Array.ArrayIterator<SpriteCacheExtended> it = layer.getPreparedCaches().iterator();
            while (it.hasNext()) {
                SpriteCacheExtended next = it.next();
                next.setProjectionMatrix(orthographicCamera.combined);
                next.begin();
                Gdx.gl.glEnable(3042);
                next.draw(next.lastCacheId);
                next.end();
            }
        }
    }

    @Override // com.prineside.tdi2.GameSystem, com.badlogic.gdx.utils.Disposable
    public final void dispose() {
        this.f2985b = true;
        Game.i.debugManager.unregisterValue("Tiles bake time");
        Game.i.debugManager.unregisterValue("Towers bake time");
        super.dispose();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/MapRenderingSystem$SplatConfig.class */
    public class SplatConfig {

        /* renamed from: a, reason: collision with root package name */
        private TextureRegion f2987a;

        /* renamed from: b, reason: collision with root package name */
        private float f2988b;
        private float c;
        private float d;

        private SplatConfig(MapRenderingSystem mapRenderingSystem) {
        }

        /* synthetic */ SplatConfig(MapRenderingSystem mapRenderingSystem, byte b2) {
            this(mapRenderingSystem);
        }
    }
}
