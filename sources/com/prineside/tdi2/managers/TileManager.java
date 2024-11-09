package com.prineside.tdi2.managers;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Manager;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.managers.ProgressManager;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.tiles.BossTile;
import com.prineside.tdi2.tiles.CoreTile;
import com.prineside.tdi2.tiles.DummyTile;
import com.prineside.tdi2.tiles.EqualizerTile;
import com.prineside.tdi2.tiles.GameValueTile;
import com.prineside.tdi2.tiles.PlatformTile;
import com.prineside.tdi2.tiles.QuadTile;
import com.prineside.tdi2.tiles.RoadTile;
import com.prineside.tdi2.tiles.ScriptTile;
import com.prineside.tdi2.tiles.SourceTile;
import com.prineside.tdi2.tiles.SpawnTile;
import com.prineside.tdi2.tiles.TargetTile;
import com.prineside.tdi2.tiles.XmMusicTrackTile;
import com.prineside.tdi2.utils.REGS;
import java.util.Iterator;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/TileManager.class */
public class TileManager extends Manager.ManagerAdapter {
    public final Factories F = new Factories();

    /* renamed from: a, reason: collision with root package name */
    private final Tile.Factory[] f2471a = new Tile.Factory[TileType.values.length];

    /* renamed from: b, reason: collision with root package name */
    private final TextureRegion[] f2472b = new TextureRegion[16];

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/TileManager$Factories.class */
    public static class Factories {
        public RoadTile.RoadTileFactory ROAD;
        public PlatformTile.SpaceTileFactory PLATFORM;
        public SpawnTile.SpawnTileFactory SPAWN;
        public TargetTile.TargetTileFactory TARGET;
        public SourceTile.SourceTileFactory SOURCE;
        public XmMusicTrackTile.XmMusicTrackTileFactory XM_MUSIC_TRACK;
        public CoreTile.CoreTileFactory CORE;
        public GameValueTile.GameValueTileFactory GAME_VALUE;
        public ScriptTile.ScriptTileFactory SCRIPT;
        public BossTile.BossTileFactory BOSS;
        public DummyTile.DummyTileFactory DUMMY;
        public QuadTile.QuadTileFactory QUAD;
        public EqualizerTile.EqualizerTileFactory EQUALIZER;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/TileManager$Serializer.class */
    public static class Serializer extends SingletonSerializer<TileManager> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public TileManager read() {
            return Game.i.tileManager;
        }
    }

    public TileManager() {
        Tile.Factory[] factoryArr = this.f2471a;
        int ordinal = TileType.ROAD.ordinal();
        Factories factories = this.F;
        RoadTile.RoadTileFactory roadTileFactory = new RoadTile.RoadTileFactory();
        factories.ROAD = roadTileFactory;
        factoryArr[ordinal] = roadTileFactory;
        Tile.Factory[] factoryArr2 = this.f2471a;
        int ordinal2 = TileType.PLATFORM.ordinal();
        Factories factories2 = this.F;
        PlatformTile.SpaceTileFactory spaceTileFactory = new PlatformTile.SpaceTileFactory();
        factories2.PLATFORM = spaceTileFactory;
        factoryArr2[ordinal2] = spaceTileFactory;
        Tile.Factory[] factoryArr3 = this.f2471a;
        int ordinal3 = TileType.SPAWN.ordinal();
        Factories factories3 = this.F;
        SpawnTile.SpawnTileFactory spawnTileFactory = new SpawnTile.SpawnTileFactory();
        factories3.SPAWN = spawnTileFactory;
        factoryArr3[ordinal3] = spawnTileFactory;
        Tile.Factory[] factoryArr4 = this.f2471a;
        int ordinal4 = TileType.TARGET.ordinal();
        Factories factories4 = this.F;
        TargetTile.TargetTileFactory targetTileFactory = new TargetTile.TargetTileFactory();
        factories4.TARGET = targetTileFactory;
        factoryArr4[ordinal4] = targetTileFactory;
        Tile.Factory[] factoryArr5 = this.f2471a;
        int ordinal5 = TileType.SOURCE.ordinal();
        Factories factories5 = this.F;
        SourceTile.SourceTileFactory sourceTileFactory = new SourceTile.SourceTileFactory();
        factories5.SOURCE = sourceTileFactory;
        factoryArr5[ordinal5] = sourceTileFactory;
        Tile.Factory[] factoryArr6 = this.f2471a;
        int ordinal6 = TileType.XM_MUSIC_TRACK.ordinal();
        Factories factories6 = this.F;
        XmMusicTrackTile.XmMusicTrackTileFactory xmMusicTrackTileFactory = new XmMusicTrackTile.XmMusicTrackTileFactory();
        factories6.XM_MUSIC_TRACK = xmMusicTrackTileFactory;
        factoryArr6[ordinal6] = xmMusicTrackTileFactory;
        Tile.Factory[] factoryArr7 = this.f2471a;
        int ordinal7 = TileType.CORE.ordinal();
        Factories factories7 = this.F;
        CoreTile.CoreTileFactory coreTileFactory = new CoreTile.CoreTileFactory();
        factories7.CORE = coreTileFactory;
        factoryArr7[ordinal7] = coreTileFactory;
        Tile.Factory[] factoryArr8 = this.f2471a;
        int ordinal8 = TileType.GAME_VALUE.ordinal();
        Factories factories8 = this.F;
        GameValueTile.GameValueTileFactory gameValueTileFactory = new GameValueTile.GameValueTileFactory();
        factories8.GAME_VALUE = gameValueTileFactory;
        factoryArr8[ordinal8] = gameValueTileFactory;
        Tile.Factory[] factoryArr9 = this.f2471a;
        int ordinal9 = TileType.BOSS.ordinal();
        Factories factories9 = this.F;
        BossTile.BossTileFactory bossTileFactory = new BossTile.BossTileFactory();
        factories9.BOSS = bossTileFactory;
        factoryArr9[ordinal9] = bossTileFactory;
        Tile.Factory[] factoryArr10 = this.f2471a;
        int ordinal10 = TileType.SCRIPT.ordinal();
        Factories factories10 = this.F;
        ScriptTile.ScriptTileFactory scriptTileFactory = new ScriptTile.ScriptTileFactory();
        factories10.SCRIPT = scriptTileFactory;
        factoryArr10[ordinal10] = scriptTileFactory;
        Tile.Factory[] factoryArr11 = this.f2471a;
        int ordinal11 = TileType.DUMMY.ordinal();
        Factories factories11 = this.F;
        DummyTile.DummyTileFactory dummyTileFactory = new DummyTile.DummyTileFactory();
        factories11.DUMMY = dummyTileFactory;
        factoryArr11[ordinal11] = dummyTileFactory;
        Tile.Factory[] factoryArr12 = this.f2471a;
        int ordinal12 = TileType.QUAD.ordinal();
        Factories factories12 = this.F;
        QuadTile.QuadTileFactory quadTileFactory = new QuadTile.QuadTileFactory();
        factories12.QUAD = quadTileFactory;
        factoryArr12[ordinal12] = quadTileFactory;
        Tile.Factory[] factoryArr13 = this.f2471a;
        int ordinal13 = TileType.EQUALIZER.ordinal();
        Factories factories13 = this.F;
        EqualizerTile.EqualizerTileFactory equalizerTileFactory = new EqualizerTile.EqualizerTileFactory();
        factories13.EQUALIZER = equalizerTileFactory;
        factoryArr13[ordinal13] = equalizerTileFactory;
        for (TileType tileType : TileType.values) {
            if (this.f2471a[tileType.ordinal()] == null) {
                throw new RuntimeException("Not all tile factories were created");
            }
        }
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void setup() {
        if (Game.i.assetManager != null) {
            for (int i = 0; i < 16; i++) {
                this.f2472b[i] = Game.i.assetManager.getTextureRegion("tile-type-road-" + (((("" + ((i & 8) != 0 ? "x" : "o")) + ((i & 4) != 0 ? "x" : "o")) + ((i & 2) != 0 ? "x" : "o")) + ((i & 1) != 0 ? "x" : "o")));
            }
        }
        for (Tile.Factory factory : this.f2471a) {
            factory.setup();
        }
    }

    public Tile.Factory<? extends Tile> getFactory(TileType tileType) {
        return this.f2471a[tileType.ordinal()];
    }

    public Tile createTileFromJsonString(String str) {
        return createTileFromJson(new JsonReader().parse(str));
    }

    public Tile createTileFromJson(JsonValue jsonValue) {
        if (!jsonValue.isObject()) {
            throw new IllegalArgumentException("JsonValue must be an object");
        }
        return getFactory(TileType.valueOf(jsonValue.getString("type"))).fromJson(jsonValue);
    }

    @Deprecated
    public Tile[][] createTileArrayFromJson(JsonValue jsonValue, boolean z) {
        if (!jsonValue.isObject()) {
            throw new IllegalArgumentException("JsonValue must be an object");
        }
        int i = jsonValue.getInt("width");
        int i2 = jsonValue.getInt("height");
        Tile[][] tileArr = new Tile[i2][i];
        Iterator<JsonValue> iterator2 = jsonValue.get("tiles").iterator2();
        while (iterator2.hasNext()) {
            JsonValue next = iterator2.next();
            int i3 = next.getInt("x");
            int i4 = next.getInt("y");
            if (z) {
                i4 = (i2 - i4) - 1;
            }
            if (tileArr[i4][i3] == null) {
                tileArr[i4][i3] = createTileFromJson(next);
            } else {
                throw new IllegalArgumentException("Duplicate tile " + i3 + ":" + i4);
            }
        }
        return tileArr;
    }

    public TextureRegion getRoadTexture(Tile tile, Tile tile2, Tile tile3, Tile tile4) {
        int i = 0;
        if (tile3 != null && tile3.isRoadType()) {
            i = 0 + 8;
        }
        if (tile2 != null && tile2.isRoadType()) {
            i += 4;
        }
        if (tile4 != null && tile4.isRoadType()) {
            i += 2;
        }
        if (tile != null && tile.isRoadType()) {
            i++;
        }
        return this.f2472b[i];
    }

    public Tile createRandomTile(float f, RandomXS128 randomXS128, ProgressManager.InventoryStatistics inventoryStatistics) {
        if (f < 0.0f) {
            f = 0.0f;
        } else if (f > 1.0f) {
            f = 1.0f;
        }
        int i = 0;
        for (Tile.Factory factory : this.f2471a) {
            i += factory.getProbabilityForPrize(f, inventoryStatistics);
        }
        int nextInt = randomXS128.nextInt(i + 1);
        for (Tile.Factory factory2 : this.f2471a) {
            int probabilityForPrize = factory2.getProbabilityForPrize(f, inventoryStatistics);
            int i2 = nextInt - probabilityForPrize;
            nextInt = i2;
            if (i2 <= 0 && probabilityForPrize > 0) {
                Tile createRandom = factory2.createRandom(f, randomXS128);
                if (createRandom != null) {
                    return createRandom;
                }
                return createRandomTile(f, randomXS128, inventoryStatistics);
            }
        }
        return null;
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.badlogic.gdx.utils.Disposable
    public void dispose() {
    }
}
