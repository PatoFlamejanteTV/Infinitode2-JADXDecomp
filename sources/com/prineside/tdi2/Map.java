package com.prineside.tdi2;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.IdentityMap;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectSet;
import com.badlogic.gdx.utils.Predicate;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.Gate;
import com.prineside.tdi2.Tile;
import com.prineside.tdi2.enums.AbilityType;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.enums.TileType;
import com.prineside.tdi2.gates.TeleportGate;
import com.prineside.tdi2.managers.MapManager;
import com.prineside.tdi2.tiles.BossTile;
import com.prineside.tdi2.tiles.GameValueTile;
import com.prineside.tdi2.tiles.SourceTile;
import com.prineside.tdi2.tiles.SpawnTile;
import com.prineside.tdi2.tiles.TargetTile;
import com.prineside.tdi2.tiles.XmMusicTrackTile;
import com.prineside.tdi2.ui.shared.AbilitySelectionOverlay;
import com.prineside.tdi2.utils.IntPair;
import com.prineside.tdi2.utils.IntRectangle;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.WaveBossSupplier;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.Iterator;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/Map.class */
public final class Map implements KryoSerializable {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f1736a = TLog.forClass(Map.class);
    public static final int MAX_TECHNICAL_SIZE = 48;
    public static final int MAX_SIZE = 24;
    public static final float WALK_COST_1 = 1.0f;
    public static final float WALK_COST_2 = 512.0f;
    public static final float WALK_COST_3 = 262144.0f;
    public static final float WALK_COST_4 = 1.3421773E8f;
    public static final float WALK_COST_MAX = 6.871948E10f;
    public static final float VOID_WALK_COST = 6.871948E10f;
    public static final float BARRIER_WALK_COST = 6.871948E10f;
    public static final float GATE_HIT_AREA_WIDTH = 18.199999f;

    /* renamed from: b, reason: collision with root package name */
    private Tile[][] f1737b;
    private Gate[][][] c;
    private int d;
    private int e;

    @NAGS
    private boolean f;

    @NAGS
    private final DelayedRemovalArray<Tile> g;

    @NAGS
    private final DelayedRemovalArray<Gate> h;

    @NAGS
    private final IdentityMap<Class<? extends Tile>, Array<? extends Tile>> i;

    @NAGS
    private final IdentityMap<Class<? extends Gate>, Array<? extends Gate>> j;

    @NAGS
    private final Array<EnemyType> k;

    @NAGS
    private final ObjectSet<EnemyType> l;

    @NAGS
    private MapManager.MapPreview m;

    @NAGS
    private final IntPair n;

    @NAGS
    private final IntRectangle o;

    @NAGS
    private final Gate p;

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        kryo.writeObjectOrNull(output, this.f1737b, Tile[][].class);
        kryo.writeObjectOrNull(output, this.c, Gate[][][].class);
        output.writeVarInt(this.d, true);
        output.writeVarInt(this.e, true);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        this.f1737b = (Tile[][]) kryo.readObjectOrNull(input, Tile[][].class);
        this.c = (Gate[][][]) kryo.readObjectOrNull(input, Gate[][][].class);
        this.d = input.readVarInt(true);
        this.e = input.readVarInt(true);
        this.f = true;
    }

    private Map() {
        this.f = true;
        this.g = new DelayedRemovalArray<>(true, 1, Tile.class);
        this.h = new DelayedRemovalArray<>(true, 1, Gate.class);
        this.i = new IdentityMap<>();
        this.j = new IdentityMap<>();
        this.k = new Array<>(EnemyType.class);
        this.l = new ObjectSet<>();
        this.n = new IntPair();
        this.o = new IntRectangle();
        this.p = Game.i.gateManager.F.BARRIER_TYPE.create();
    }

    public Map(int i, int i2) {
        this(new Tile[i2][i], new Gate[i2 + 1][i + 1][2]);
    }

    public Map(Tile[][] tileArr, Gate[][][] gateArr) {
        this.f = true;
        this.g = new DelayedRemovalArray<>(true, 1, Tile.class);
        this.h = new DelayedRemovalArray<>(true, 1, Gate.class);
        this.i = new IdentityMap<>();
        this.j = new IdentityMap<>();
        this.k = new Array<>(EnemyType.class);
        this.l = new ObjectSet<>();
        this.n = new IntPair();
        this.o = new IntRectangle();
        this.p = Game.i.gateManager.F.BARRIER_TYPE.create();
        this.f1737b = tileArr;
        this.c = gateArr;
        this.e = tileArr.length;
        this.d = tileArr[0].length;
        for (int i = 0; i < this.e; i++) {
            for (int i2 = 0; i2 < this.d; i2++) {
                if (tileArr[i][i2] != null) {
                    tileArr[i][i2].setPos(i2, i);
                }
            }
        }
        for (int i3 = 0; i3 <= this.e; i3++) {
            for (int i4 = 0; i4 <= this.d; i4++) {
                int i5 = 0;
                while (i5 < 2) {
                    if (gateArr[i3][i4][i5] != null) {
                        gateArr[i3][i4][i5].setPosition(i4, i3, i5 == 0);
                    }
                    i5++;
                }
            }
        }
        this.f = true;
    }

    public static int positionToCoordinate(float f) {
        if (f < 0.0f) {
            return -1;
        }
        return (int) (f * 0.0078125f);
    }

    public static int getTileIndex(float f) {
        if (f < 0.0f) {
            return -1;
        }
        return (int) (f * 0.0078125f);
    }

    public final TargetTile getTargetTileOrThrow() {
        return getTargetTile(true);
    }

    public final TargetTile getTargetTile(boolean z) {
        Array tilesByType = getTilesByType(TargetTile.class);
        if (tilesByType.size == 0) {
            if (z) {
                throw new IllegalStateException("No target tile on map");
            }
            return null;
        }
        return (TargetTile) tilesByType.first();
    }

    public final void traverseNeighborTiles(int i, int i2, Predicate<Tile> predicate) {
        Tile tile;
        for (int i3 = i - 1; i3 <= i + 1; i3++) {
            for (int i4 = i2 - 1; i4 <= i2 + 1; i4++) {
                if ((i3 != i || i4 != i2) && (tile = getTile(i3, i4)) != null && !predicate.evaluate(tile)) {
                    return;
                }
            }
        }
    }

    public final void getNeighbourTiles(Array<Tile> array, int i, int i2) {
        traverseNeighborTiles(i, i2, tile -> {
            array.add(tile);
            return true;
        });
    }

    public final AbilitySelectionOverlay.SelectedAbilitiesConfiguration getMaxedAbilitiesConfiguration() {
        a();
        ObjectMap objectMap = new ObjectMap();
        for (AbilityType abilityType : AbilityType.values) {
            objectMap.put(Game.i.abilityManager.getMaxPerGameGameValueType(abilityType), abilityType);
        }
        Array array = new Array(GameValueConfig.class);
        array.addAll(getTargetTileOrThrow().getGameValues());
        for (int i = 0; i < this.g.size; i++) {
            Tile tile = this.g.items[i];
            if (tile.type == TileType.GAME_VALUE) {
                GameValueTile gameValueTile = (GameValueTile) tile;
                array.add(new GameValueConfig(gameValueTile.getGameValueType(), gameValueTile.getDelta(), false, true));
            }
        }
        AbilitySelectionOverlay.SelectedAbilitiesConfiguration selectedAbilitiesConfiguration = new AbilitySelectionOverlay.SelectedAbilitiesConfiguration();
        int i2 = 0;
        for (int i3 = 0; i3 < array.size; i3++) {
            GameValueConfig gameValueConfig = (GameValueConfig) array.get(i3);
            if (objectMap.containsKey(gameValueConfig.getType())) {
                boolean z = false;
                int i4 = 0;
                while (true) {
                    if (i4 >= 6) {
                        break;
                    }
                    if (selectedAbilitiesConfiguration.slots[i4] != objectMap.get(gameValueConfig.getType())) {
                        i4++;
                    } else {
                        int[] iArr = selectedAbilitiesConfiguration.counts;
                        int i5 = i4;
                        iArr[i5] = iArr[i5] + MathUtils.round((float) gameValueConfig.getValue());
                        z = true;
                        break;
                    }
                }
                if (!z) {
                    selectedAbilitiesConfiguration.slots[i2] = (AbilityType) objectMap.get(gameValueConfig.getType());
                    selectedAbilitiesConfiguration.counts[i2] = MathUtils.round((float) gameValueConfig.getValue());
                    i2++;
                    if (i2 == 6) {
                        break;
                    }
                } else {
                    continue;
                }
            }
        }
        return selectedAbilitiesConfiguration;
    }

    public static int getTileIdByPos(Tile tile) {
        return (tile.f1771b << 11) + tile.f1770a;
    }

    public final double getPrestigeScore() {
        a();
        double d = 0.0d;
        Array.ArrayIterator<Tile> it = this.g.iterator();
        while (it.hasNext()) {
            d += it.next().getPrestigeScore();
        }
        Array.ArrayIterator<Gate> it2 = this.h.iterator();
        while (it2.hasNext()) {
            d += it2.next().getPrestigeScore();
        }
        return d;
    }

    public final <T extends Tile> Array<T> getTilesByType(Class<T> cls) {
        Preconditions.checkNotNull(cls, "Class can not be null");
        a();
        Array<? extends Tile> array = this.i.get(cls);
        Array<? extends Tile> array2 = array;
        if (array == null) {
            array2 = new Array<>(true, 1, cls);
            this.i.put(cls, array2);
        }
        return (Array<T>) array2;
    }

    public final <T extends Gate> Array<T> getGatesByType(Class<T> cls) {
        Preconditions.checkNotNull(cls, "Class can not be null");
        a();
        Array<? extends Gate> array = this.j.get(cls);
        Array<? extends Gate> array2 = array;
        if (array == null) {
            array2 = new Array<>(true, 1, cls);
        }
        return (Array<T>) array2;
    }

    public final void getTeleportGates(IntMap<Array<TeleportGate>> intMap) {
        intMap.clear();
        Array gatesByType = getGatesByType(TeleportGate.class);
        int i = gatesByType.size;
        for (int i2 = 0; i2 < i; i2++) {
            TeleportGate teleportGate = (TeleportGate) gatesByType.get(i2);
            if (!intMap.containsKey(teleportGate.index)) {
                intMap.put(teleportGate.index, new Array<>(true, 1, TeleportGate.class));
            }
            intMap.get(teleportGate.index).add(teleportGate);
        }
    }

    public final void multiplyPortalsDifficulty(float f) {
        a();
        Array tilesByType = getTilesByType(SpawnTile.class);
        int i = tilesByType.size;
        for (int i2 = 0; i2 < i; i2++) {
            ((SpawnTile) tilesByType.get(i2)).difficulty = MathUtils.round(r0.difficulty * f);
        }
        this.f = true;
    }

    public final int getAverageDifficulty() {
        Array tilesByType = getTilesByType(SpawnTile.class);
        if (tilesByType.size == 0) {
            throw new IllegalStateException("No spawn tiles on map");
        }
        int i = 0;
        int i2 = tilesByType.size;
        for (int i3 = 0; i3 < i2; i3++) {
            i += ((SpawnTile) tilesByType.get(i3)).difficulty;
        }
        return i / tilesByType.size;
    }

    public final float getDifficultyExpectedPlaytime() {
        float f = 0.5f - ((this.g.size / 576.0f) * 0.45f);
        f1736a.i("getDifficultyExpectedPlaytime: " + f, new Object[0]);
        return f;
    }

    public final void setSize(int i, int i2) {
        if (this.d == i && this.e == i2) {
            return;
        }
        if (i <= 0 || i > 48) {
            throw new IllegalArgumentException("Invalid width: " + i);
        }
        if (i2 <= 0 || i2 > 48) {
            throw new IllegalArgumentException("Invalid height: " + i2);
        }
        Tile[][] tileArr = new Tile[i2][i];
        Gate[][][] gateArr = new Gate[i2 + 1][i + 1][2];
        for (int i3 = 0; i3 < i2; i3++) {
            for (int i4 = 0; i4 < i; i4++) {
                tileArr[i3][i4] = getTile(i4, i3);
            }
        }
        for (int i5 = 0; i5 <= i2; i5++) {
            for (int i6 = 0; i6 <= i; i6++) {
                int i7 = 0;
                while (i7 < 2) {
                    gateArr[i5][i6][i7] = getGate(i6, i5, i7 == 0);
                    i7++;
                }
            }
        }
        this.f1737b = tileArr;
        this.c = gateArr;
        this.e = tileArr.length;
        this.d = tileArr[0].length;
        this.f = true;
    }

    public final int[] getResourcesCount() {
        a();
        int[] iArr = new int[ResourceType.values.length + 1];
        int i = this.g.size;
        for (int i2 = 0; i2 < i; i2++) {
            Tile tile = this.g.items[i2];
            if (tile instanceof SourceTile) {
                SourceTile sourceTile = (SourceTile) tile;
                int i3 = 0;
                for (ResourceType resourceType : ResourceType.values) {
                    int resourcesCount = sourceTile.getResourcesCount(resourceType);
                    int ordinal = resourceType.ordinal();
                    iArr[ordinal] = iArr[ordinal] + resourcesCount;
                    i3 += resourcesCount;
                }
                int length = ResourceType.values.length;
                iArr[length] = iArr[length] + StrictMath.round((1.0f - sourceTile.getResourceDensity()) * i3);
            }
        }
        return iArr;
    }

    public final void unloadPreview() {
        if (this.m != null) {
            this.m.dispose();
            this.m = null;
        }
    }

    public final void regeneratePreview() {
        unloadPreview();
        this.m = Game.i.mapManager.generatePreview(this);
    }

    public final MapManager.MapPreview getPreview() {
        if (this.m == null || this.m.isDisposed()) {
            regeneratePreview();
        }
        return this.m;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Map fromJson(JsonValue jsonValue) {
        Tile cloneTile;
        if (!jsonValue.isObject()) {
            throw new IllegalArgumentException("JsonValue must be an object");
        }
        int i = jsonValue.getInt("v", 1);
        switch (i) {
            case 1:
                int i2 = jsonValue.getInt("width");
                int i3 = jsonValue.getInt("height");
                Tile[][] tileArr = new Tile[i3][i2];
                Iterator<JsonValue> iterator2 = jsonValue.get("tiles").iterator2();
                while (iterator2.hasNext()) {
                    JsonValue next = iterator2.next();
                    Tile createTileFromJson = Game.i.tileManager.createTileFromJson(next);
                    if (tileArr[createTileFromJson.f1771b][createTileFromJson.f1770a] == null) {
                        tileArr[createTileFromJson.f1771b][createTileFromJson.f1770a] = Game.i.tileManager.createTileFromJson(next);
                    } else {
                        throw new IllegalArgumentException("Duplicate tile " + createTileFromJson.f1770a + ":" + createTileFromJson.f1771b);
                    }
                }
                Gate[][][] gateArr = new Gate[i3 + 1][i2 + 1][2];
                JsonValue jsonValue2 = jsonValue.get("gates");
                if (jsonValue2 != null) {
                    Iterator<JsonValue> iterator22 = jsonValue2.iterator2();
                    while (iterator22.hasNext()) {
                        Gate createGateFromJson = Game.i.gateManager.createGateFromJson(iterator22.next());
                        if (gateArr[createGateFromJson.getY()][createGateFromJson.getX()][createGateFromJson.isLeftSide() ? (char) 0 : (char) 1] == null) {
                            gateArr[createGateFromJson.getY()][createGateFromJson.getX()][createGateFromJson.isLeftSide() ? (char) 0 : (char) 1] = createGateFromJson;
                        } else {
                            throw new IllegalArgumentException("Duplicate gate " + createGateFromJson.getX() + ":" + createGateFromJson.getY() + SequenceUtils.SPACE + createGateFromJson.isLeftSide());
                        }
                    }
                }
                return new Map(tileArr, gateArr);
            case 2:
                int i4 = jsonValue.getInt("w");
                int i5 = jsonValue.getInt("h");
                Tile[][] tileArr2 = new Tile[i5][i4];
                Gate[][][] gateArr2 = new Gate[i5 + 1][i4 + 1][2];
                JsonValue jsonValue3 = jsonValue.get("tt");
                Array array = new Array(true, 8, Tile.class);
                Iterator<JsonValue> iterator23 = jsonValue3.iterator2();
                while (iterator23.hasNext()) {
                    JsonValue next2 = iterator23.next();
                    Tile tile = null;
                    try {
                        tile = Game.i.tileManager.createTileFromJson(next2);
                    } catch (Exception unused) {
                        f1736a.e("failed to load tile from json: " + next2.toJson(JsonWriter.OutputType.json), new Object[0]);
                    }
                    array.add(tile);
                }
                int[] asIntArray = jsonValue.get("t").asIntArray();
                for (int i6 = 0; i6 < asIntArray.length; i6 += 3) {
                    int i7 = asIntArray[i6];
                    int i8 = asIntArray[i6 + 1];
                    int i9 = asIntArray[i6 + 2];
                    Tile tile2 = ((Tile[]) array.items)[i7];
                    if (tile2 == null) {
                        cloneTile = Game.i.tileManager.F.DUMMY.create();
                    } else {
                        cloneTile = tile2.cloneTile();
                    }
                    Tile tile3 = cloneTile;
                    tile3.f1770a = i8;
                    tile3.f1771b = i9;
                    tileArr2[i9][i8] = tile3;
                }
                JsonValue jsonValue4 = jsonValue.get("gt");
                if (jsonValue4 != null) {
                    Array array2 = new Array(true, 8, Gate.class);
                    Iterator<JsonValue> iterator24 = jsonValue4.iterator2();
                    while (iterator24.hasNext()) {
                        JsonValue next3 = iterator24.next();
                        Gate gate = null;
                        try {
                            gate = Game.i.gateManager.createGateFromJson(next3);
                        } catch (Exception unused2) {
                            f1736a.e("failed to load gate from json: " + next3.toJson(JsonWriter.OutputType.json), new Object[0]);
                        }
                        array2.add(gate);
                    }
                    int[] asIntArray2 = jsonValue.get("g").asIntArray();
                    for (int i10 = 0; i10 < asIntArray2.length; i10 += 4) {
                        int i11 = asIntArray2[i10];
                        int i12 = asIntArray2[i10 + 1];
                        int i13 = asIntArray2[i10 + 2];
                        boolean z = asIntArray2[i10 + 3] == 1;
                        try {
                            if (i11 >= array2.size) {
                                throw new IllegalArgumentException("Template index " + i11 + " out of bounds, there's only " + array2.size + " templates");
                            }
                            Gate gate2 = ((Gate[]) array2.items)[i11];
                            if (gate2 != null) {
                                Gate cloneGate = gate2.cloneGate();
                                cloneGate.setPosition(i12, i13, z);
                                gateArr2[i13][i12][z ? (char) 0 : (char) 1] = cloneGate;
                            }
                        } catch (Exception e) {
                            throw new IllegalArgumentException("failed to load gate " + i11 + ":" + i12 + ":" + i13, e);
                        }
                    }
                }
                return new Map(tileArr2, gateArr2);
            default:
                throw new IllegalArgumentException("Unrecognized map version: " + i);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void toJson(Json json) {
        a();
        json.writeValue("v", (Object) 2);
        json.writeValue("w", Integer.valueOf(this.d));
        json.writeValue("h", Integer.valueOf(this.e));
        Array array = new Array(true, 1, Tile.class);
        IntArray intArray = new IntArray();
        for (int i = 0; i < this.g.size; i++) {
            Tile tile = this.g.get(i);
            int i2 = -1;
            int i3 = 0;
            while (true) {
                if (i3 >= array.size) {
                    break;
                }
                if (!((Tile) array.get(i3)).sameAsWithExtras(tile)) {
                    i3++;
                } else {
                    i2 = i3;
                    break;
                }
            }
            if (i2 == -1) {
                i2 = array.size;
                array.add(tile);
            }
            intArray.add(i2, tile.f1770a, tile.f1771b);
        }
        Array array2 = new Array(true, 1, Gate.class);
        IntArray intArray2 = new IntArray();
        Array.ArrayIterator<Gate> it = this.h.iterator();
        while (it.hasNext()) {
            Gate next = it.next();
            int i4 = -1;
            int i5 = 0;
            while (true) {
                if (i5 >= array2.size) {
                    break;
                }
                if (!((Gate) array2.get(i5)).sameAs(next)) {
                    i5++;
                } else {
                    i4 = i5;
                    break;
                }
            }
            if (i4 == -1) {
                i4 = array2.size;
                array2.add(next);
            }
            intArray2.add(i4, next.getX(), next.getY(), next.isLeftSide() ? 1 : 0);
        }
        json.writeArrayStart("tt");
        Array.ArrayIterator it2 = array.iterator();
        while (it2.hasNext()) {
            Tile tile2 = (Tile) it2.next();
            json.writeObjectStart();
            int i6 = tile2.f1770a;
            int i7 = tile2.f1771b;
            tile2.f1771b = 0;
            tile2.f1770a = 0;
            tile2.toJson(json);
            tile2.f1770a = i6;
            tile2.f1771b = i7;
            json.writeObjectEnd();
        }
        json.writeArrayEnd();
        if (array2.size != 0) {
            json.writeArrayStart("gt");
            Array.ArrayIterator it3 = array2.iterator();
            while (it3.hasNext()) {
                Gate gate = (Gate) it3.next();
                json.writeObjectStart();
                int x = gate.getX();
                int y = gate.getY();
                boolean isLeftSide = gate.isLeftSide();
                gate.setPosition(0, 0, false);
                gate.toJson(json);
                gate.setPosition(x, y, isLeftSide);
                json.writeObjectEnd();
            }
            json.writeArrayEnd();
        }
        json.writeArrayStart("t");
        for (int i8 = 0; i8 < intArray.size; i8++) {
            json.writeValue(Integer.valueOf(intArray.items[i8]));
        }
        json.writeArrayEnd();
        if (array2.size != 0) {
            json.writeArrayStart("g");
            for (int i9 = 0; i9 < intArray2.size; i9++) {
                json.writeValue(Integer.valueOf(intArray2.items[i9]));
            }
            json.writeArrayEnd();
        }
    }

    public final int generateSeed() {
        a();
        int i = 1;
        Array.ArrayIterator<Tile> it = this.g.iterator();
        while (it.hasNext()) {
            Tile next = it.next();
            int generateSeedSalt = next.generateSeedSalt();
            if (generateSeedSalt != 0) {
                i = (i * 19) + generateSeedSalt;
            }
            i = (((((i * 23) + next.type.ordinal()) * 29) + next.f1770a) * 31) + next.f1771b;
        }
        return i;
    }

    public final boolean hasTileThatAllowsWalkablePlatforms() {
        a();
        for (int i = 0; i < this.g.size; i++) {
            if (this.g.items[i].type == TileType.TARGET) {
                Array<GameValueConfig> gameValues = ((TargetTile) this.g.items[i]).getGameValues();
                for (int i2 = 0; i2 < gameValues.size; i2++) {
                    GameValueConfig gameValueConfig = gameValues.items[i2];
                    if (gameValueConfig.getType() == GameValueType.ENEMIES_WALK_ON_PLATFORMS && gameValueConfig.getValue() >= 1.0d) {
                        return true;
                    }
                }
            } else if (this.g.items[i].type == TileType.GAME_VALUE) {
                GameValueTile gameValueTile = (GameValueTile) this.g.items[i];
                if (gameValueTile.getGameValueType() == GameValueType.ENEMIES_WALK_ON_PLATFORMS && gameValueTile.getDelta() >= 1.0d) {
                    return true;
                }
            } else {
                continue;
            }
        }
        return false;
    }

    private void a() {
        if (this.f) {
            b();
        }
    }

    private void b() {
        this.f = false;
        this.h.clear();
        this.g.clear();
        ObjectMap.Values<Array<? extends Tile>> it = this.i.values().iterator();
        while (it.hasNext()) {
            it.next().clear();
        }
        ObjectMap.Values<Array<? extends Gate>> it2 = this.j.values().iterator();
        while (it2.hasNext()) {
            it2.next().clear();
        }
        this.l.clear();
        this.k.clear();
        for (int i = 0; i < this.e; i++) {
            for (int i2 = 0; i2 < this.d; i2++) {
                Tile tile = this.f1737b[i][i2];
                if (tile != null) {
                    this.g.add(tile);
                    Class<?> cls = tile.getClass();
                    Array<? extends Tile> array = this.i.get(cls);
                    Array<? extends Tile> array2 = array;
                    if (array == null) {
                        array2 = new Array<>(true, 1, cls);
                        this.i.put(cls, array2);
                    }
                    array2.add(tile);
                }
            }
        }
        for (int i3 = 0; i3 < this.e + 1; i3++) {
            for (int i4 = 0; i4 < this.d + 1; i4++) {
                for (int i5 = 0; i5 < 2; i5++) {
                    Gate gate = this.c[i3][i4][i5];
                    if (gate != null) {
                        this.h.add(gate);
                        Class<?> cls2 = gate.getClass();
                        Array<? extends Gate> array3 = this.j.get(cls2);
                        Array<? extends Gate> array4 = array3;
                        if (array3 == null) {
                            array4 = new Array<>(true, 1, cls2);
                            this.j.put(cls2, array4);
                        }
                        array4.add(gate);
                    }
                }
            }
        }
        Array tilesByType = getTilesByType(SpawnTile.class);
        int i6 = tilesByType.size;
        for (int i7 = 0; i7 < i6; i7++) {
            Array<SpawnTile.AllowedEnemyConfig> allowedEnemies = ((SpawnTile) tilesByType.get(i7)).getAllowedEnemies();
            int i8 = allowedEnemies.size;
            for (int i9 = 0; i9 < i8; i9++) {
                EnemyType enemyType = allowedEnemies.items[i9].enemyType;
                if (!this.l.contains(enemyType)) {
                    this.k.add(enemyType);
                    this.l.add(enemyType);
                }
            }
        }
        this.m = null;
    }

    @Null
    public final Gate getGate(int i, int i2, boolean z) {
        if (i < 0 || i > this.d || i2 < 0 || i2 > this.e) {
            return null;
        }
        return this.c[i2][i][z ? (char) 0 : (char) 1];
    }

    public final boolean fitGateToMapPos(float f, float f2, Gate gate) {
        int i = (int) (f / 128.0f);
        int i2 = (int) (f2 / 128.0f);
        float f3 = f % 128.0f;
        float f4 = f2 % 128.0f;
        float squareDistanceBetweenPoints = PMath.getSquareDistanceBetweenPoints(f3, f4, 64.0f, 128.0f);
        float squareDistanceBetweenPoints2 = PMath.getSquareDistanceBetweenPoints(f3, f4, 64.0f, 0.0f);
        float squareDistanceBetweenPoints3 = PMath.getSquareDistanceBetweenPoints(f3, f4, 0.0f, 64.0f);
        float squareDistanceBetweenPoints4 = PMath.getSquareDistanceBetweenPoints(f3, f4, 128.0f, 64.0f);
        if (squareDistanceBetweenPoints <= squareDistanceBetweenPoints2 && squareDistanceBetweenPoints <= squareDistanceBetweenPoints3 && squareDistanceBetweenPoints <= squareDistanceBetweenPoints4) {
            if (128.0f - f4 > 18.199999f || i < 0 || i >= this.d || i2 < -1 || i2 >= this.e) {
                return false;
            }
            gate.setPosition(i, i2 + 1, false);
            return true;
        }
        if (squareDistanceBetweenPoints2 <= squareDistanceBetweenPoints && squareDistanceBetweenPoints2 <= squareDistanceBetweenPoints4 && squareDistanceBetweenPoints2 <= squareDistanceBetweenPoints3) {
            if (f4 > 18.199999f || f4 < -18.199999f || i < 0 || i >= this.d || i2 < 0 || i2 > this.e) {
                return false;
            }
            gate.setPosition(i, i2, false);
            return true;
        }
        if (squareDistanceBetweenPoints3 <= squareDistanceBetweenPoints && squareDistanceBetweenPoints3 <= squareDistanceBetweenPoints2 && squareDistanceBetweenPoints3 <= squareDistanceBetweenPoints4) {
            if (f3 > 18.199999f || f3 < -18.199999f || i2 < 0 || i2 >= this.e || i < 0 || i > this.d) {
                return false;
            }
            gate.setPosition(i, i2, true);
            return true;
        }
        if (128.0f - f3 > 18.199999f || i2 < 0 || i2 >= this.e || i < -1 || i >= this.d) {
            return false;
        }
        gate.setPosition(i + 1, i2, true);
        return true;
    }

    @Null
    public final Gate getGateByMapPos(float f, float f2) {
        if (fitGateToMapPos(f, f2, this.p)) {
            return getGate(this.p.getX(), this.p.getY(), this.p.isLeftSide());
        }
        return null;
    }

    public final void setGate(int i, int i2, boolean z, @Null Gate gate) {
        if (i < 0 || i > this.d) {
            throw new IllegalArgumentException("x is out of range: " + i + " (max " + (this.d + 1) + ")");
        }
        if (i2 < 0 || i2 > this.e) {
            throw new IllegalArgumentException("y is out of range: " + i2 + " (max " + (this.e + 1) + ")");
        }
        Gate gate2 = getGate(i, i2, z);
        if (gate2 == null && gate == null) {
            return;
        }
        if (gate2 != null) {
            gate2.setPosition(0, 0, true);
        }
        this.c[i2][i][z ? (char) 0 : (char) 1] = gate;
        if (gate != null) {
            gate.setPosition(i, i2, z);
        }
        this.f = true;
    }

    @Null
    public final Tile getTileAtPos(Tile.Pos pos) {
        Preconditions.checkNotNull(pos);
        return getTile(pos.getX(), pos.getY());
    }

    @Null
    public final Gate getGateAtPos(Gate.Pos pos) {
        Preconditions.checkNotNull(pos);
        return getGate(pos.getX(), pos.getY(), pos.isLeft());
    }

    @Null
    public final Tile getTile(int i, int i2) {
        if (i2 >= 0 && i2 < this.e && i >= 0 && i < this.d) {
            return this.f1737b[i2][i];
        }
        return null;
    }

    public final Tile[][] getTilesRaw() {
        return this.f1737b;
    }

    public final Gate[][][] getGatesRaw() {
        return this.c;
    }

    public final DelayedRemovalArray<Tile> getAllTiles() {
        a();
        return this.g;
    }

    public final DelayedRemovalArray<Gate> getAllGates() {
        a();
        return this.h;
    }

    public final Tile getTileByMapPosV(Vector2 vector2) {
        return getTileByMapPos(vector2.x, vector2.y);
    }

    @Null
    public final IntPair getTileCoordinatesByMapPos(float f, float f2) {
        if (f < 0.0f || f2 < 0.0f) {
            return null;
        }
        int i = (int) (f * 0.0078125f);
        int i2 = (int) (f2 * 0.0078125f);
        if (i >= this.d || i2 >= this.e) {
            return null;
        }
        this.n.f3850a = i;
        this.n.f3851b = i2;
        return this.n;
    }

    public final Tile getTileByMapPos(float f, float f2) {
        if (f < 0.0f || f2 < 0.0f) {
            return null;
        }
        return getTile((int) (f * 0.0078125f), (int) (f2 * 0.0078125f));
    }

    public final void setTile(int i, int i2, @Null Tile tile) {
        if (i < 0 || i >= this.d) {
            throw new IllegalArgumentException("x is out of range: " + i + " (max " + this.d + ")");
        }
        if (i2 < 0 || i2 >= this.e) {
            throw new IllegalArgumentException("y is out of range: " + i2 + " (max " + this.e + ")");
        }
        Tile tile2 = getTile(i, i2);
        if (tile2 == tile) {
            return;
        }
        if (tile2 != null) {
            tile2.setPos(0, 0);
        }
        this.f1737b[i2][i] = tile;
        if (tile != null) {
            this.f1737b[i2][i].setPos(i, i2);
        }
        this.f = true;
    }

    public final Map cpy() {
        Tile[][] tileArr = new Tile[this.f1737b.length][this.f1737b[0].length];
        for (int i = 0; i < this.f1737b.length; i++) {
            for (int i2 = 0; i2 < this.f1737b[i].length; i2++) {
                if (this.f1737b[i][i2] != null) {
                    tileArr[i][i2] = this.f1737b[i][i2].cloneTile();
                }
            }
        }
        Gate[][][] gateArr = new Gate[this.f1737b.length + 1][this.f1737b[0].length + 1][2];
        for (int i3 = 0; i3 <= this.e; i3++) {
            for (int i4 = 0; i4 <= this.d; i4++) {
                for (int i5 = 0; i5 < 2; i5++) {
                    if (this.c[i3][i4][i5] != null) {
                        gateArr[i3][i4][i5] = this.c[i3][i4][i5].cloneGate();
                    }
                }
            }
        }
        return new Map(tileArr, gateArr);
    }

    public final IntRectangle getTrimBounds() {
        a();
        int i = Integer.MAX_VALUE;
        int i2 = Integer.MAX_VALUE;
        int i3 = Integer.MIN_VALUE;
        int i4 = Integer.MIN_VALUE;
        Array.ArrayIterator<Tile> it = this.g.iterator();
        while (it.hasNext()) {
            Tile next = it.next();
            int x = next.getX();
            int y = next.getY();
            if (x < i) {
                i = x;
            }
            if (x > i3) {
                i3 = x;
            }
            if (y < i2) {
                i2 = y;
            }
            if (y > i4) {
                i4 = y;
            }
        }
        Array.ArrayIterator<Gate> it2 = this.h.iterator();
        while (it2.hasNext()) {
            Gate next2 = it2.next();
            int x2 = next2.getX();
            int y2 = next2.getY();
            if (x2 < i) {
                i = x2;
            }
            if (x2 > i3) {
                i3 = x2;
            }
            if (y2 < i2) {
                i2 = y2;
            }
            if (y2 > i4) {
                i4 = y2;
            }
        }
        this.o.set(i, i3, i2, i4);
        return this.o;
    }

    public final int getWidth() {
        return this.d;
    }

    public final int getHeight() {
        return this.e;
    }

    public final Map cpyTrimmed() {
        IntRectangle trimBounds = getTrimBounds();
        if (trimBounds.minX != Integer.MAX_VALUE) {
            Tile[][] tileArr = new Tile[(trimBounds.maxY - trimBounds.minY) + 1][(trimBounds.maxX - trimBounds.minX) + 1];
            for (int i = 0; i < this.f1737b.length; i++) {
                for (int i2 = 0; i2 < this.f1737b[i].length; i2++) {
                    if (this.f1737b[i][i2] != null) {
                        tileArr[i - trimBounds.minY][i2 - trimBounds.minX] = this.f1737b[i][i2].cloneTile();
                    }
                }
            }
            Gate[][][] gateArr = new Gate[tileArr.length + 1][tileArr[0].length + 1][2];
            for (int i3 = 0; i3 <= this.e; i3++) {
                for (int i4 = 0; i4 <= this.d; i4++) {
                    for (int i5 = 0; i5 < 2; i5++) {
                        if (this.c[i3][i4][i5] != null) {
                            gateArr[i3 - trimBounds.minY][i4 - trimBounds.minX][i5] = this.c[i3][i4][i5].cloneGate();
                        }
                    }
                }
            }
            return new Map(tileArr, gateArr);
        }
        return new Map(1, 1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Null
    public final WaveBossSupplier getBossWaves() {
        a();
        boolean z = false;
        Array tilesByType = getTilesByType(SpawnTile.class);
        int i = 0;
        while (true) {
            if (i >= tilesByType.size) {
                break;
            }
            if (!((SpawnTile[]) tilesByType.items)[i].getAllowedEnemiesSet().contains(EnemyType.BOSS)) {
                i++;
            } else {
                z = true;
                break;
            }
        }
        if (z) {
            Array tilesByType2 = getTilesByType(BossTile.class);
            if (tilesByType2.size != 0) {
                return ((BossTile) tilesByType2.first()).getBossWaveMap();
            }
            return null;
        }
        return null;
    }

    public static int posToCell(float f) {
        return ((int) ((f + 32768.0f) * 0.0078125f)) - 256;
    }

    public final Array<EnemyType> getAllowedEnemies() {
        a();
        return this.k;
    }

    public final ObjectSet<EnemyType> getAllowedEnemiesSet() {
        a();
        return this.l;
    }

    public final XmMusicTrackTile getMusicTile() {
        Array tilesByType = getTilesByType(XmMusicTrackTile.class);
        if (tilesByType.size == 0) {
            return null;
        }
        return (XmMusicTrackTile) tilesByType.first();
    }

    public final void validate() {
        a();
        boolean z = false;
        boolean z2 = false;
        Array.ArrayIterator<Tile> it = this.g.iterator();
        while (it.hasNext()) {
            Tile next = it.next();
            if (next.type == TileType.SPAWN) {
                z2 = true;
            } else if (next.type != TileType.TARGET) {
                continue;
            } else {
                if (z) {
                    Array array = new Array();
                    Array.ArrayIterator<Tile> it2 = this.g.iterator();
                    while (it2.hasNext()) {
                        Tile next2 = it2.next();
                        if (next2.type == TileType.TARGET) {
                            array.add(next2);
                        }
                    }
                    throw new InvalidMapException(InvalidMapException.Reason.MULTIPLE_TARGETS, array);
                }
                z = true;
            }
        }
        boolean z3 = false;
        boolean z4 = false;
        int i = 0;
        boolean z5 = false;
        for (int i2 = 0; i2 < this.e; i2++) {
            for (int i3 = 0; i3 < this.d; i3++) {
                Tile tile = this.f1737b[i2][i3];
                if (tile != null && tile.type == TileType.XM_MUSIC_TRACK) {
                    if (z3) {
                        Array array2 = new Array();
                        for (int i4 = 0; i4 < this.e; i4++) {
                            for (int i5 = 0; i5 < this.d; i5++) {
                                Tile tile2 = this.f1737b[i4][i5];
                                if (tile2 != null && tile2.type == TileType.XM_MUSIC_TRACK) {
                                    array2.add(tile2);
                                }
                            }
                        }
                        throw new InvalidMapException(InvalidMapException.Reason.MULTIPLE_SOUND_TRACKS, array2);
                    }
                    z3 = true;
                } else if (tile != null && tile.type == TileType.CORE) {
                    if (z4) {
                        Array array3 = new Array();
                        for (int i6 = 0; i6 < this.e; i6++) {
                            for (int i7 = 0; i7 < this.d; i7++) {
                                Tile tile3 = this.f1737b[i6][i7];
                                if (tile3 != null && tile3.type == TileType.CORE) {
                                    array3.add(tile3);
                                }
                            }
                        }
                        throw new InvalidMapException(InvalidMapException.Reason.MULTIPLE_CORES, array3);
                    }
                    z4 = true;
                } else if (tile != null && tile.type == TileType.BOSS) {
                    if (z5) {
                        Array array4 = new Array();
                        for (int i8 = 0; i8 < this.e; i8++) {
                            for (int i9 = 0; i9 < this.d; i9++) {
                                Tile tile4 = this.f1737b[i8][i9];
                                if (tile4 != null && tile4.type == TileType.BOSS) {
                                    array4.add(tile4);
                                }
                            }
                        }
                        throw new InvalidMapException(InvalidMapException.Reason.MULTIPLE_BOSS_TILES, array4);
                    }
                    z5 = true;
                } else if (tile != null && tile.type == TileType.SPAWN) {
                    i++;
                    if (i > 8) {
                        Array array5 = new Array();
                        for (int i10 = 0; i10 < this.e; i10++) {
                            for (int i11 = 0; i11 < this.d; i11++) {
                                Tile tile5 = this.f1737b[i10][i11];
                                if (tile5 != null && tile5.type == TileType.SPAWN) {
                                    array5.add(tile5);
                                }
                            }
                        }
                        throw new InvalidMapException(InvalidMapException.Reason.TOO_MANY_PORTALS, array5);
                    }
                }
            }
        }
        if (!z2) {
            throw new InvalidMapException(InvalidMapException.Reason.SPAWN_NOT_FOUND, new Array());
        }
        if (!z) {
            throw new InvalidMapException(InvalidMapException.Reason.TARGET_NOT_FOUND, new Array());
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/Map$InvalidMapException.class */
    public static class InvalidMapException extends RuntimeException {
        public final Array<Tile> invalidTiles = new Array<>();
        public final Reason reason;

        /* loaded from: infinitode-2.jar:com/prineside/tdi2/Map$InvalidMapException$Reason.class */
        public enum Reason {
            MULTIPLE_TARGETS,
            MULTIPLE_SOUND_TRACKS,
            SPAWN_NOT_FOUND,
            TARGET_NOT_FOUND,
            TOO_MANY_PORTALS,
            MULTIPLE_CORES,
            MULTIPLE_BOSS_TILES
        }

        public InvalidMapException(Reason reason, Array<Tile> array) {
            this.reason = reason;
            if (array != null) {
                this.invalidTiles.addAll(array);
            }
        }

        public String getFixHintMessage() {
            return Game.i.localeManager.i18n.get("invalid_map_format_hint_" + this.reason.name());
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/Map$EnemyTypeSpawnPair.class */
    public static class EnemyTypeSpawnPair {
        public EnemyType enemyType;
        public SpawnTile spawnTile;

        public EnemyTypeSpawnPair(EnemyType enemyType, SpawnTile spawnTile) {
            this.enemyType = enemyType;
            this.spawnTile = spawnTile;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/Map$PathNotFoundForEnemyTypeException.class */
    public static class PathNotFoundForEnemyTypeException extends RuntimeException {
        public EnemyType enemyType;

        public PathNotFoundForEnemyTypeException(EnemyType enemyType, Throwable th) {
            super("Path not found for enemy type: " + enemyType, th);
            this.enemyType = enemyType;
        }
    }
}
