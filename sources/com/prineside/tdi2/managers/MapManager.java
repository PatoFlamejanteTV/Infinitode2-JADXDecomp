package com.prineside.tdi2.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.CameraController;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Gate;
import com.prineside.tdi2.Manager;
import com.prineside.tdi2.Map;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.enums.BuildingType;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.systems.MapRenderingSystem;
import com.prineside.tdi2.tiles.PlatformTile;
import com.prineside.tdi2.tiles.SourceTile;
import com.prineside.tdi2.utils.IntRectangle;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;
import java.lang.ref.WeakReference;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/MapManager.class */
public class MapManager extends Manager.ManagerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2371a = TLog.forClass(MapManager.class);
    public static final int PREVIEW_WIDTH = 310;
    public static final int PREVIEW_HEIGHT = 230;

    /* renamed from: b, reason: collision with root package name */
    private OrthographicCamera f2372b;
    private CameraController c;
    private FrameBuffer d;
    private Pixmap e;
    public ParticleEffectPool highlightParticlesPool;
    public ParticleEffectPool coreHighlightParticlesPool;
    public ParticleEffectPool tileWarningParticlePool;
    private final Array<WeakReference<MapPreview>> f = new Array<>(false, 1, WeakReference.class);
    private final Array<MapPreview> g = new Array<>(MapPreview.class);

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/MapManager$Serializer.class */
    public static class Serializer extends SingletonSerializer<MapManager> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public MapManager read() {
            return Game.i.mapManager;
        }
    }

    public MapManager() {
        if (Game.i.assetManager != null) {
            this.highlightParticlesPool = Game.i.assetManager.getParticleEffectPool("highlight.prt");
            this.coreHighlightParticlesPool = Game.i.assetManager.getParticleEffectPool("core-highlight.prt");
            this.tileWarningParticlePool = Game.i.assetManager.getParticleEffectPool("tile-warning.prt");
        }
    }

    public MapPreview generatePreview(Map map) {
        Preconditions.checkNotNull(map, "Map can not be null");
        MapPreview mapPreview = new MapPreview(this, map, (byte) 0);
        this.g.add(mapPreview);
        return mapPreview;
    }

    public void unloadMapPreviews() {
        this.g.clear();
        for (int i = 0; i < this.f.size; i++) {
            MapPreview mapPreview = this.f.get(i).get();
            if (mapPreview != null) {
                mapPreview.dispose();
            }
        }
        this.f.clear();
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void postRender(float f) {
        if (this.g.size != 0) {
            MapPreview removeIndex = this.g.removeIndex(0);
            try {
                removeIndex.generate();
            } catch (Exception e) {
                f2371a.e("failed to generate map preview", e);
            }
            this.f.add(new WeakReference<>(removeIndex));
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/MapManager$MapPreview.class */
    public class MapPreview implements Disposable {

        /* renamed from: a, reason: collision with root package name */
        private final TextureRegion f2373a;

        /* renamed from: b, reason: collision with root package name */
        private Texture f2374b;
        private Map c;
        private boolean d;

        /* synthetic */ MapPreview(MapManager mapManager, Map map, byte b2) {
            this(map);
        }

        static /* synthetic */ boolean a(MapPreview mapPreview, boolean z) {
            mapPreview.d = true;
            return true;
        }

        private MapPreview(Map map) {
            Preconditions.checkNotNull(map, "Map can not be null");
            this.c = map;
            this.f2373a = new TextureRegion(Game.i.assetManager.getTextureRegion("map-preview-placeholder"));
        }

        public boolean isDisposed() {
            return this.d;
        }

        public TextureRegion getTextureRegion() {
            return this.f2373a;
        }

        public void generate() {
            if (this.c == null) {
                return;
            }
            long realTickCount = Game.getRealTickCount();
            if (MapManager.this.d == null) {
                MapManager.this.d = new FrameBuffer(Pixmap.Format.RGBA8888, 310, 230, false);
            }
            IntRectangle trimBounds = this.c.getTrimBounds();
            int i = (trimBounds.maxX - trimBounds.minX) + 1;
            int i2 = (trimBounds.maxY - trimBounds.minY) + 1;
            if (MapManager.this.f2372b == null) {
                MapManager.this.f2372b = new OrthographicCamera(310.0f, 230.0f);
                MapManager.this.c = new CameraController(MapManager.this.f2372b, 1, 1);
            }
            SpriteBatch spriteBatch = Game.i.renderingManager.batch;
            MapManager.this.c.setScreenSize(310, 230);
            MapManager.this.c.setMapSize(i << 7, i2 << 7);
            MapManager.this.c.setZoomBoundaries(0.001f, 1000.0f);
            MapManager.this.c.fitMapToScreen(10.0f);
            MapManager.this.d.begin();
            Gdx.gl.glBlendFunc(770, 771);
            Gdx.gl.glClearColor(Config.BACKGROUND_COLOR.r, Config.BACKGROUND_COLOR.g, Config.BACKGROUND_COLOR.f888b, Config.BACKGROUND_COLOR.f889a);
            Gdx.gl.glClear(16384);
            Gdx.gl.glEnable(3042);
            spriteBatch.begin();
            spriteBatch.setProjectionMatrix(MapManager.this.f2372b.combined);
            Array.ArrayIterator<Tile> it = this.c.getAllTiles().iterator();
            while (it.hasNext()) {
                Tile next = it.next();
                spriteBatch.setColor(Color.WHITE);
                next.drawStatic(spriteBatch, (next.getX() - trimBounds.minX) << 7, (next.getY() - trimBounds.minY) << 7, 128.0f, 128.0f, this.c, MapRenderingSystem.DrawMode.DETAILED);
                spriteBatch.setColor(Color.WHITE);
                next.drawExtras(spriteBatch, (next.getX() - trimBounds.minX) << 7, (next.getY() - trimBounds.minY) << 7, 128.0f, 128.0f, MapRenderingSystem.DrawMode.DETAILED);
                if (next.type == TileType.PLATFORM) {
                    PlatformTile platformTile = (PlatformTile) next;
                    if (platformTile.building != null) {
                        spriteBatch.setColor(Color.WHITE);
                        platformTile.building.drawBase(spriteBatch, (next.getX() - trimBounds.minX) << 7, (next.getY() - trimBounds.minY) << 7, 128.0f, 128.0f, MapRenderingSystem.DrawMode.DEFAULT);
                    }
                }
            }
            for (int i3 = 0; i3 <= this.c.getHeight(); i3++) {
                for (int i4 = 0; i4 <= this.c.getWidth(); i4++) {
                    boolean[] zArr = {false, true};
                    for (int i5 = 0; i5 < 2; i5++) {
                        Gate gate = this.c.getGate(i4, i3, zArr[i5]);
                        if (gate != null) {
                            try {
                                spriteBatch.setColor(Color.WHITE);
                                gate.drawStatic(spriteBatch, (i4 - trimBounds.minX) << 7, (i3 - trimBounds.minY) << 7, 128.0f, 128.0f);
                            } catch (Exception e) {
                                MapManager.f2371a.e("stopped preview generation", e);
                            }
                        }
                    }
                }
            }
            Array.ArrayIterator<Tile> it2 = this.c.getAllTiles().iterator();
            while (it2.hasNext()) {
                Tile next2 = it2.next();
                spriteBatch.setColor(Color.WHITE);
                next2.drawBatch(spriteBatch, 0.0f, (next2.getX() - trimBounds.minX) << 7, (next2.getY() - trimBounds.minY) << 7, 128.0f, 128.0f, MapRenderingSystem.DrawMode.DETAILED);
                next2.postDrawBatch(spriteBatch, 0.0f, (next2.getX() - trimBounds.minX) << 7, (next2.getY() - trimBounds.minY) << 7, 128.0f, 128.0f, MapRenderingSystem.DrawMode.DETAILED);
                if (next2.type == TileType.PLATFORM) {
                    PlatformTile platformTile2 = (PlatformTile) next2;
                    if (platformTile2.building != null && platformTile2.building.buildingType == BuildingType.TOWER) {
                        ((Tower) platformTile2.building).drawWeapon(spriteBatch, (next2.getX() - trimBounds.minX) << 7, (next2.getY() - trimBounds.minY) << 7, 128.0f, 0.0f);
                    }
                } else if (next2.type == TileType.SOURCE) {
                    SourceTile sourceTile = (SourceTile) next2;
                    if (sourceTile.miner != null) {
                        sourceTile.miner.drawBatch(spriteBatch, (next2.getX() - trimBounds.minX) << 7, (next2.getY() - trimBounds.minY) << 7, 128.0f, 0.0f, MapRenderingSystem.DrawMode.DEFAULT);
                    }
                }
            }
            for (int i6 = 0; i6 <= this.c.getHeight(); i6++) {
                for (int i7 = 0; i7 <= this.c.getWidth(); i7++) {
                    boolean[] zArr2 = {false, true};
                    for (int i8 = 0; i8 < 2; i8++) {
                        Gate gate2 = this.c.getGate(i7, i6, zArr2[i8]);
                        if (gate2 != null) {
                            gate2.drawBatch(spriteBatch, 0.0f, (i7 - trimBounds.minX) << 7, (i6 - trimBounds.minY) << 7, 128.0f, 128.0f);
                        }
                    }
                }
            }
            spriteBatch.end();
            this.c.hashCode();
            this.c = null;
            try {
                Gdx.gl.glPixelStorei(3333, 1);
                if (MapManager.this.e == null) {
                    MapManager.this.e = new Pixmap(310, 230, Pixmap.Format.RGBA8888);
                }
                Gdx.gl.glReadPixels(0, 0, 310, 230, 6408, 5121, MapManager.this.e.getPixels());
            } catch (Exception unused) {
            }
            MapManager.this.d.end();
            if (MapManager.this.e != null) {
                if (this.f2374b != null) {
                    this.f2374b.dispose();
                }
                this.f2374b = new Texture(MapManager.this.e) { // from class: com.prineside.tdi2.managers.MapManager.MapPreview.1

                    /* renamed from: a, reason: collision with root package name */
                    private boolean f2375a = false;

                    public void finalize() {
                        super.finalize();
                        MapPreview.a(MapPreview.this, true);
                        if (this.f2375a) {
                            return;
                        }
                        try {
                            if (Game.i.isInMainThread()) {
                                MapPreview.this.f2374b.dispose();
                                MapPreview.this.f2374b = null;
                            } else {
                                this.f2375a = true;
                                MapPreview.this.f2374b = null;
                                Gdx.app.postRunnable(() -> {
                                    try {
                                        this.dispose();
                                    } catch (Exception e2) {
                                        MapManager.f2371a.e("failed to dispose texture in runnable", e2);
                                    }
                                });
                            }
                            MapPreview.this.f2373a.setRegion(Game.i.assetManager.getTextureRegion("map-preview-placeholder"));
                        } catch (Exception e2) {
                            MapManager.f2371a.e("failed to finalize map preview texture", e2);
                        }
                    }
                };
                Texture texture = this.f2374b;
                Texture.TextureFilter textureFilter = Texture.TextureFilter.Linear;
                texture.setFilter(textureFilter, textureFilter);
                this.f2373a.setU(0.002f);
                this.f2373a.setV(0.998f);
                this.f2373a.setU2(0.998f);
                this.f2373a.setV2(0.002f);
                this.f2373a.setTexture(this.f2374b);
                if (Game.i.debugManager != null) {
                    Game.i.debugManager.registerFrameJob("MapManager-generatePreview", Game.getRealTickCount() - realTickCount);
                }
            }
        }

        @Override // com.badlogic.gdx.utils.Disposable
        public void dispose() {
            if (this.f2374b != null) {
                this.f2374b.dispose();
                this.f2374b = null;
            }
            this.d = true;
        }
    }
}
