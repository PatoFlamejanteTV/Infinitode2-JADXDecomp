package com.prineside.tdi2.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectSet;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.BasicLevel;
import com.prineside.tdi2.BasicLevelQuestConfig;
import com.prineside.tdi2.BasicLevelStage;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.IssuedItems;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.Manager;
import com.prineside.tdi2.Map;
import com.prineside.tdi2.enums.AchievementType;
import com.prineside.tdi2.enums.BasicLevelLootBonusType;
import com.prineside.tdi2.enums.DifficultyMode;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.MinerType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.items.BitDustItem;
import com.prineside.tdi2.items.GreenPaperItem;
import com.prineside.tdi2.items.ResourceItem;
import com.prineside.tdi2.items.StarItem;
import com.prineside.tdi2.managers.preferences.categories.ProgressPrefs;
import com.prineside.tdi2.managers.preferences.categories.progress.PP_BasicLevel;
import com.prineside.tdi2.screens.LevelSelectScreen;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.tiles.PlatformTile;
import com.prineside.tdi2.tiles.SourceTile;
import com.prineside.tdi2.ui.shared.Notifications;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.ObjectPair;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.html.Attribute;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import nonapi.io.github.classgraph.fastzipfilereader.NestedJarHandler;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/BasicLevelManager.class */
public class BasicLevelManager extends Manager.ManagerAdapter {
    public static final int PREVIEW_WIDTH = 310;
    public static final int PREVIEW_HEIGHT = 230;
    public ObjectMap<String, float[]> resourceBonusLevelMultipliers = new ObjectMap<>();
    public final Array<BasicLevelStage> stagesOrdered;

    /* renamed from: b, reason: collision with root package name */
    private final ObjectMap<String, BasicLevelStage> f2307b;
    public final Array<String> defaultLevelNames;
    public final Array<BasicLevel> levelsOrdered;
    private final ObjectMap<String, BasicLevel> c;
    private final ObjectMap<String, BasicLevelQuestConfig> d;
    private int e;
    private final DelayedRemovalArray<BasicLevelManagerListener> f;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2306a = TLog.forClass(BasicLevelManager.class);
    private static final Array<BasicLevel> g = new Array<>(BasicLevel.class);

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/BasicLevelManager$BasicLevelManagerListener.class */
    public interface BasicLevelManagerListener {
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/BasicLevelManager$Serializer.class */
    public static class Serializer extends SingletonSerializer<BasicLevelManager> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public BasicLevelManager read() {
            return Game.i.basicLevelManager;
        }
    }

    public BasicLevelManager() {
        ObjectMap<String, float[]> objectMap = this.resourceBonusLevelMultipliers;
        objectMap.put("1.1", new float[]{3.5f, 0.0f, 0.0f, 0.0f, 0.0f});
        objectMap.put("1.2", new float[]{3.0f, 0.0f, 0.0f, 3.0f, 0.0f});
        objectMap.put("1.3", new float[]{3.5f, 3.5f, 0.0f, 0.0f, 3.0f});
        objectMap.put("1.4", new float[]{0.0f, 0.0f, 0.0f, 3.5f, 3.5f});
        objectMap.put("1.5", new float[]{3.5f, 0.0f, 0.0f, 3.5f, 0.0f});
        objectMap.put("1.6", new float[]{2.5f, 3.0f, 0.0f, 0.0f, 0.0f});
        objectMap.put("1.7", new float[]{3.0f, 3.0f, 0.0f, 0.0f, 0.0f});
        objectMap.put("1.8", new float[]{3.0f, 3.5f, 0.0f, 0.0f, 0.0f});
        objectMap.put("2.1", new float[]{3.0f, 3.5f, 3.5f, 0.0f, 0.0f});
        objectMap.put("2.2", new float[]{0.0f, 0.0f, 3.0f, 0.0f, 0.0f});
        objectMap.put("2.3", new float[]{3.5f, 3.0f, 3.5f, 0.0f, 0.0f});
        objectMap.put("2.4", new float[]{3.0f, 2.5f, 3.0f, 0.0f, 0.0f});
        objectMap.put("2.5", new float[]{3.0f, 2.5f, 3.0f, 0.0f, 0.0f});
        objectMap.put("2.6", new float[]{0.0f, 3.0f, 0.0f, 3.0f, 3.5f});
        objectMap.put("2.7", new float[]{3.0f, 3.0f, 3.0f, 0.0f, 3.5f});
        objectMap.put("2.8", new float[]{3.5f, 3.0f, 3.0f, 0.0f, 0.0f});
        objectMap.put("3.1", new float[]{3.5f, 3.0f, 3.5f, 0.0f, 3.0f});
        objectMap.put("3.2", new float[]{0.0f, 2.5f, 0.0f, 0.0f, 0.0f});
        objectMap.put("3.3", new float[]{3.0f, 3.5f, 0.0f, 3.0f, 0.0f});
        objectMap.put("3.4", new float[]{3.5f, 2.5f, 3.5f, 0.0f, 0.0f});
        objectMap.put("3.5", new float[]{3.0f, 3.5f, 0.0f, 0.0f, 0.0f});
        objectMap.put("3.6", new float[]{3.0f, 0.0f, 0.0f, 0.0f, 0.0f});
        objectMap.put("3.7", new float[]{3.5f, 0.0f, 2.5f, 0.0f, 0.0f});
        objectMap.put("3.8", new float[]{3.0f, 0.0f, 3.0f, 3.5f, 0.0f});
        objectMap.put("4.1", new float[]{3.0f, 3.0f, 2.0f, 3.5f, 3.5f});
        objectMap.put("4.2", new float[]{3.0f, 3.5f, 2.5f, 2.5f, 3.0f});
        objectMap.put("4.3", new float[]{3.5f, 2.5f, 0.0f, 3.0f, 3.0f});
        objectMap.put("4.4", new float[]{3.0f, 3.0f, 3.5f, 3.5f, 3.5f});
        objectMap.put("4.5", new float[]{3.0f, 3.0f, 3.0f, 3.0f, 0.0f});
        objectMap.put("4.6", new float[]{3.0f, 3.5f, 2.5f, 3.0f, 3.0f});
        objectMap.put("4.7", new float[]{0.0f, 3.0f, 3.0f, 3.5f, 3.5f});
        objectMap.put("4.8", new float[]{3.5f, 3.5f, 3.5f, 2.5f, 3.0f});
        objectMap.put("5.1", new float[]{3.0f, 3.0f, 3.0f, 3.0f, 2.5f});
        objectMap.put("5.2", new float[]{3.5f, 3.0f, 2.5f, 2.5f, 3.0f});
        objectMap.put("5.3", new float[]{2.5f, 2.5f, 2.0f, 3.0f, 2.5f});
        objectMap.put("5.4", new float[]{0.0f, 3.0f, 2.5f, 3.0f, 2.5f});
        objectMap.put("5.5", new float[]{2.0f, 2.0f, 3.0f, 3.0f, 3.0f});
        objectMap.put("5.6", new float[]{3.5f, 0.0f, 2.5f, 2.0f, 3.0f});
        objectMap.put("5.7", new float[]{0.0f, 3.5f, 3.0f, 2.5f, 3.0f});
        objectMap.put("5.8", new float[]{0.0f, 2.0f, 2.0f, 3.0f, 3.0f});
        objectMap.put("6.1", new float[]{3.5f, 0.0f, 0.0f, 3.5f, 3.5f});
        objectMap.put("6.2", new float[]{3.0f, 0.0f, 0.0f, 0.0f, 3.5f});
        objectMap.put("6.3", new float[]{3.5f, 3.5f, 2.5f, 2.5f, 2.5f});
        objectMap.put("6.4", new float[]{3.5f, 0.0f, 3.0f, 2.5f, 2.5f});
        objectMap.put("6.5", new float[]{0.0f, 3.0f, 0.0f, 0.0f, 2.0f});
        objectMap.put("6.6", new float[]{2.5f, 3.0f, 3.0f, 3.0f, 3.5f});
        this.stagesOrdered = new Array<>(BasicLevelStage.class);
        this.f2307b = new ObjectMap<>();
        this.defaultLevelNames = new Array<>(String.class);
        this.levelsOrdered = new Array<>(BasicLevel.class);
        this.c = new ObjectMap<>();
        this.d = new ObjectMap<>();
        this.f = new DelayedRemovalArray<>(false, 1);
        Iterator<JsonValue> iterator2 = new JsonReader().parse(AssetManager.localOrInternalFile("res/basic-level-stages.json")).iterator2();
        while (iterator2.hasNext()) {
            BasicLevelStage fromJson = BasicLevelStage.fromJson(iterator2.next());
            if (this.f2307b.containsKey(fromJson.name)) {
                throw new RuntimeException("Stage with name " + fromJson.name + " already exists");
            }
            this.f2307b.put(fromJson.name, fromJson);
            this.stagesOrdered.add(fromJson);
        }
        this.defaultLevelNames.clear();
        for (FileHandle fileHandle : Gdx.files.internal("levels").list(".json")) {
            this.defaultLevelNames.add(fileHandle.nameWithoutExtension());
        }
        for (FileHandle fileHandle2 : Gdx.files.local("levels").list(".json")) {
            if (!this.defaultLevelNames.contains(fileHandle2.nameWithoutExtension(), false)) {
                this.defaultLevelNames.add(fileHandle2.nameWithoutExtension());
            }
        }
        this.defaultLevelNames.sort((v0, v1) -> {
            return v0.compareTo(v1);
        });
        Array.ArrayIterator<String> it = this.defaultLevelNames.iterator();
        while (it.hasNext()) {
            String next = it.next();
            String readString = AssetManager.localOrInternalFile("levels/" + next + ".json").readString("UTF-8");
            try {
                loadAndRegisterLevelFromJson(new JsonReader().parse(readString));
            } catch (Throwable th) {
                f2306a.e("failed to load level %s, file contents: %s", next, readString, th);
            }
        }
        for (int i = 0; i < this.stagesOrdered.size; i++) {
            this.stagesOrdered.items[i].sortLevels();
        }
        ObjectSet objectSet = new ObjectSet();
        for (int i2 = 0; i2 < this.levelsOrdered.size; i2++) {
            BasicLevel basicLevel = this.levelsOrdered.get(i2);
            for (int i3 = 0; i3 < basicLevel.quests.size; i3++) {
                BasicLevelQuestConfig basicLevelQuestConfig = basicLevel.quests.get(i3);
                if (objectSet.contains(basicLevelQuestConfig.getId())) {
                    throw new RuntimeException("Duplicate quest id " + basicLevelQuestConfig.getId());
                }
                objectSet.add(basicLevelQuestConfig.getId());
            }
            for (int i4 = 0; i4 < basicLevel.waveQuests.size; i4++) {
                BasicLevel.WaveQuest waveQuest = basicLevel.waveQuests.get(i4);
                if (objectSet.contains(waveQuest.id)) {
                    throw new RuntimeException("Duplicate quest id " + waveQuest.id);
                }
                objectSet.add(waveQuest.id);
            }
        }
        this.e = 0;
        for (int i5 = 0; i5 < this.levelsOrdered.size; i5++) {
            BasicLevel basicLevel2 = this.levelsOrdered.items[i5];
            for (int i6 = 0; i6 < basicLevel2.quests.size; i6++) {
                for (int i7 = 0; i7 < basicLevel2.quests.items[i6].prizes.size; i7++) {
                    ItemStack itemStack = basicLevel2.quests.items[i6].prizes.items[i7];
                    if (itemStack.getItem() instanceof StarItem) {
                        this.e += itemStack.getCount();
                    }
                }
            }
            for (int i8 = 0; i8 < basicLevel2.waveQuests.size; i8++) {
                for (int i9 = 0; i9 < basicLevel2.waveQuests.items[i8].prizes.size; i9++) {
                    ItemStack itemStack2 = basicLevel2.waveQuests.items[i8].prizes.items[i9];
                    if (itemStack2.getItem() instanceof StarItem) {
                        this.e += itemStack2.getCount();
                    }
                }
            }
        }
    }

    public void addStage(BasicLevelStage basicLevelStage) {
        if (this.f2307b.containsKey(basicLevelStage.name)) {
            throw new RuntimeException("Stage with name " + basicLevelStage.name + " already exists");
        }
        this.f2307b.put(basicLevelStage.name, basicLevelStage);
        this.stagesOrdered.add(basicLevelStage);
    }

    public void removeStage(BasicLevelStage basicLevelStage) {
        if (this.stagesOrdered.contains(basicLevelStage, true)) {
            if (this.stagesOrdered.size == 1) {
                throw new IllegalStateException("Can not delete the last stage");
            }
            this.stagesOrdered.removeValue(basicLevelStage, true);
            this.f2307b.remove(basicLevelStage.name);
            return;
        }
        throw new IllegalArgumentException("Stage not registered");
    }

    /* renamed from: com.prineside.tdi2.managers.BasicLevelManager$1SourceInfo, reason: invalid class name */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/BasicLevelManager$1SourceInfo.class */
    class C1SourceInfo {

        /* renamed from: a, reason: collision with root package name */
        float f2311a;

        /* renamed from: b, reason: collision with root package name */
        float f2312b;

        C1SourceInfo(BasicLevelManager basicLevelManager, float f, int i) {
            this.f2311a = f;
            this.f2312b = f * (1.0f + (i * 0.35f));
        }

        public String toString() {
            return StringFormatter.compactNumberWithPrecisionTrimZeros(this.f2312b, 1, true).toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.prineside.tdi2.managers.BasicLevelManager$1MapInfo, reason: invalid class name */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/BasicLevelManager$1MapInfo.class */
    public class C1MapInfo {

        /* renamed from: a, reason: collision with root package name */
        BasicLevel f2309a;

        /* renamed from: b, reason: collision with root package name */
        int f2310b;
        Array<C1SourceInfo>[] c = new Array[5];
        float d;
        float e;
        float[] f;
        float[] g;
        float h;

        C1MapInfo(BasicLevelManager basicLevelManager, BasicLevel basicLevel) {
            for (int i = 0; i < this.c.length; i++) {
                this.c[i] = new Array<>(true, 1, C1SourceInfo.class);
            }
            this.f = new float[5];
            this.g = new float[5];
            this.f2309a = basicLevel;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void levelsResourceReport() {
        C1MapInfo c1MapInfo;
        C1MapInfo c1MapInfo2;
        String str;
        String str2;
        Array array = new Array(true, 1, BasicLevel.class);
        ObjectMap objectMap = new ObjectMap();
        Array array2 = new Array(true, 1, C1MapInfo.class);
        for (int i = 0; i < this.levelsOrdered.size; i++) {
            BasicLevel basicLevel = this.levelsOrdered.get(i);
            if (!basicLevel.isBonus && (basicLevel.stageName.equals("1") || basicLevel.stageName.equals("2") || basicLevel.stageName.equals("3") || basicLevel.stageName.equals("4") || basicLevel.stageName.equals("5") || basicLevel.stageName.equals("6"))) {
                array.add(basicLevel);
                objectMap.put(basicLevel.name, basicLevel);
                Map map = basicLevel.getMap();
                Array tilesByType = map.getTilesByType(SourceTile.class);
                C1MapInfo c1MapInfo3 = new C1MapInfo(this, basicLevel);
                array2.add(c1MapInfo3);
                for (int i2 = 0; i2 < tilesByType.size; i2++) {
                    SourceTile sourceTile = (SourceTile) tilesByType.get(i2);
                    for (ResourceType resourceType : ResourceType.values) {
                        int resourcesCount = sourceTile.getResourcesCount(resourceType);
                        if (resourcesCount > 0) {
                            float resourceDensity = sourceTile.getResourceDensity();
                            int i3 = 0;
                            for (int i4 = 0; i4 < resourceType.ordinal() - 1; i4++) {
                                i3 += sourceTile.getResourcesCount(ResourceType.values[i4]);
                            }
                            float f = 1.0f;
                            if (i3 != 0) {
                                f = resourcesCount / (i3 + resourcesCount);
                            }
                            AtomicInteger atomicInteger = new AtomicInteger();
                            map.traverseNeighborTiles(sourceTile.getX(), sourceTile.getY(), tile -> {
                                if (tile instanceof PlatformTile) {
                                    atomicInteger.getAndIncrement();
                                    return true;
                                }
                                return true;
                            });
                            c1MapInfo3.c[resourceType.ordinal()].add(new C1SourceInfo(this, resourceDensity * f, atomicInteger.get()));
                        }
                    }
                }
                Array tilesByType2 = map.getTilesByType(PlatformTile.class);
                for (int i5 = 0; i5 < tilesByType2.size; i5++) {
                    PlatformTile platformTile = (PlatformTile) tilesByType2.get(i5);
                    map.traverseNeighborTiles(platformTile.getX(), platformTile.getY(), tile2 -> {
                        if (tile2 instanceof SourceTile) {
                            c1MapInfo3.f2310b++;
                            return true;
                        }
                        return true;
                    });
                }
            }
        }
        for (int i6 = 0; i6 < array2.size; i6++) {
            C1MapInfo c1MapInfo4 = (C1MapInfo) array2.get(i6);
            for (ResourceType resourceType2 : ResourceType.values) {
                Array<C1SourceInfo> array3 = c1MapInfo4.c[resourceType2.ordinal()];
                for (int i7 = 0; i7 < array3.size; i7++) {
                    C1SourceInfo c1SourceInfo = array3.get(i7);
                    c1MapInfo4.d += c1SourceInfo.f2311a;
                    c1MapInfo4.e += c1SourceInfo.f2312b;
                    float[] fArr = c1MapInfo4.f;
                    int ordinal = resourceType2.ordinal();
                    fArr[ordinal] = fArr[ordinal] + c1SourceInfo.f2311a;
                    float[] fArr2 = c1MapInfo4.g;
                    int ordinal2 = resourceType2.ordinal();
                    fArr2[ordinal2] = fArr2[ordinal2] + c1SourceInfo.f2312b;
                }
            }
        }
        float f2 = 0.0f;
        for (int i8 = 0; i8 < array2.size; i8++) {
            f2 += ((C1MapInfo) array2.get(i8)).e;
        }
        float f3 = f2 / array2.size;
        for (int i9 = 0; i9 < array2.size; i9++) {
            C1MapInfo c1MapInfo5 = (C1MapInfo) array2.get(i9);
            c1MapInfo5.h = c1MapInfo5.e / f3;
        }
        Array array4 = new Array(true, 1, C1MapInfo.class);
        for (int i10 = 0; i10 < array2.size; i10++) {
            C1MapInfo c1MapInfo6 = (C1MapInfo) array2.get(i10);
            if (c1MapInfo6.h < 0.7f || c1MapInfo6.h > 1.4f) {
                array4.add(c1MapInfo6);
            }
        }
        array4.sort((c1MapInfo7, c1MapInfo8) -> {
            return Float.compare(c1MapInfo7.h, c1MapInfo8.h);
        });
        float[] fArr3 = new float[ResourceType.values.length];
        for (int i11 = 0; i11 < array2.size; i11++) {
            C1MapInfo c1MapInfo9 = (C1MapInfo) array2.get(i11);
            for (int i12 = 0; i12 < ResourceType.values.length; i12++) {
                int i13 = i12;
                fArr3[i13] = fArr3[i13] + c1MapInfo9.g[i12];
            }
        }
        C1MapInfo[][] c1MapInfoArr = new C1MapInfo[ResourceType.values.length][8];
        float f4 = 0.0f;
        int i14 = 0;
        for (ResourceType resourceType3 : ResourceType.values) {
            Array array5 = new Array(true, 1, ObjectPair.class);
            for (int i15 = 0; i15 < array2.size; i15++) {
                C1MapInfo c1MapInfo10 = (C1MapInfo) array2.get(i15);
                float f5 = c1MapInfo10.g[resourceType3.ordinal()];
                if (f5 != 0.0f) {
                    array5.add(new ObjectPair(c1MapInfo10, Float.valueOf(f5)));
                }
            }
            array5.sort((objectPair, objectPair2) -> {
                return Float.compare(((Float) objectPair2.second).floatValue(), ((Float) objectPair.second).floatValue());
            });
            int min = Math.min(array5.size, c1MapInfoArr[0].length);
            for (int i16 = 0; i16 < min; i16++) {
                c1MapInfoArr[resourceType3.ordinal()][i16] = (C1MapInfo) ((ObjectPair) array5.get(i16)).first;
                f4 += ((Float) ((ObjectPair) array5.get(i16)).second).floatValue();
                i14++;
            }
        }
        float f6 = f4 / i14;
        f2306a.i("Global amount of resources:", new Object[0]);
        for (ResourceType resourceType4 : ResourceType.values) {
            f2306a.i("- " + resourceType4.name() + ": " + fArr3[resourceType4.ordinal()], new Object[0]);
        }
        f2306a.i("", new Object[0]);
        f2306a.i("Sum resource density outliers:", new Object[0]);
        for (int i17 = 0; i17 < array4.size; i17++) {
            C1MapInfo c1MapInfo11 = (C1MapInfo) array4.get(i17);
            if (c1MapInfo11.h < 1.0f) {
                str = "-" + StringFormatter.compactNumberWithPrecisionTrimZeros((1.0f - c1MapInfo11.h) * 100.0f, 1, false).toString();
                if (c1MapInfo11.h < 0.2f) {
                    str2 = "------";
                } else if (c1MapInfo11.h < 0.3f) {
                    str2 = "-----";
                } else if (c1MapInfo11.h < 0.4f) {
                    str2 = "----";
                } else if (c1MapInfo11.h < 0.5f) {
                    str2 = NestedJarHandler.TEMP_FILENAME_LEAF_SEPARATOR;
                } else if (c1MapInfo11.h < 0.6f) {
                    str2 = "--";
                } else {
                    str2 = "-";
                }
            } else {
                str = "+" + StringFormatter.compactNumberWithPrecisionTrimZeros((c1MapInfo11.h - 1.0f) * 100.0f, 1, false).toString();
                if (c1MapInfo11.h > 2.6d) {
                    str2 = "++++++";
                } else if (c1MapInfo11.h > 2.3d) {
                    str2 = "+++++";
                } else if (c1MapInfo11.h > 2.0f) {
                    str2 = "++++";
                } else if (c1MapInfo11.h > 1.8d) {
                    str2 = "+++";
                } else if (c1MapInfo11.h > 1.5d) {
                    str2 = "++";
                } else {
                    str2 = "+";
                }
            }
            f2306a.i("- " + c1MapInfo11.f2309a.name + ": " + str2 + " / " + str + "%", new Object[0]);
        }
        f2306a.i("", new Object[0]);
        f2306a.i("Best levels to farm individual resources (avg meta density: " + ((Object) StringFormatter.compactNumberWithPrecisionTrimZeros(f6, 1, true)) + "):", new Object[0]);
        float f7 = f6 * 0.8f;
        for (ResourceType resourceType5 : ResourceType.values) {
            C1MapInfo[] c1MapInfoArr2 = c1MapInfoArr[resourceType5.ordinal()];
            float f8 = 0.0f;
            int length = c1MapInfoArr2.length;
            for (int i18 = 0; i18 < length && (c1MapInfo2 = c1MapInfoArr2[i18]) != null; i18++) {
                f8 += c1MapInfo2.g[resourceType5.ordinal()];
            }
            f2306a.i("=== " + resourceType5.name() + " (sum: " + ((Object) StringFormatter.compactNumberWithPrecisionTrimZeros(f8, 1, false)) + ") ===", new Object[0]);
            int i19 = 0;
            int length2 = c1MapInfoArr2.length;
            for (int i20 = 0; i20 < length2 && (c1MapInfo = c1MapInfoArr2[i20]) != null; i20++) {
                float f9 = c1MapInfo.g[resourceType5.ordinal()];
                f2306a.i("- " + c1MapInfo.f2309a.name + ": " + ((Object) StringFormatter.compactNumberWithPrecisionTrimZeros(f9, 2, false)) + " / x" + ((Object) StringFormatter.compactNumberWithPrecisionTrimZeros(f9 / f6, 2, false)) + (f9 > 12.0f ? " (!!! Too HIGH)" : "") + ((i19 >= 3 || f9 >= f7) ? "" : " (!!! Too LOW)"), new Object[0]);
                i19++;
            }
        }
        f2306a.i("", new Object[0]);
        f2306a.i("Levels which have multiple best farming resource sources (some resources have to be moved to other non-meta maps):", new Object[0]);
        ObjectMap objectMap2 = new ObjectMap();
        for (ResourceType resourceType6 : ResourceType.values) {
            for (C1MapInfo c1MapInfo12 : c1MapInfoArr[resourceType6.ordinal()]) {
                if (objectMap2.get(c1MapInfo12) == null) {
                    objectMap2.put(c1MapInfo12, new Array(true, 1, ResourceType.class));
                }
                ((Array) objectMap2.get(c1MapInfo12)).add(resourceType6);
            }
        }
        for (int i21 = 0; i21 < array2.size; i21++) {
            C1MapInfo c1MapInfo13 = (C1MapInfo) array2.get(i21);
            Array array6 = (Array) objectMap2.get(c1MapInfo13);
            if (array6 != null && array6.size >= 2) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i22 = 0; i22 < array6.size; i22++) {
                    ResourceType resourceType7 = (ResourceType) array6.get(i22);
                    float f10 = c1MapInfo13.g[resourceType7.ordinal()];
                    if (i22 != 0) {
                        stringBuilder.append(", ");
                    }
                    stringBuilder.append(resourceType7.name()).append(" (x").append(String.valueOf(StringFormatter.compactNumberWithPrecisionTrimZeros(f10 / f6, 2, false))).append(")");
                }
                f2306a.i("!!! " + c1MapInfo13.f2309a.name + ": " + ((Object) stringBuilder), new Object[0]);
            }
        }
        f2306a.i("", new Object[0]);
        f2306a.i("Vacant levels to become good farming spots (not listed in top spots to farm any resource ATM):", new Object[0]);
        for (int i23 = 0; i23 < array2.size; i23++) {
            C1MapInfo c1MapInfo14 = (C1MapInfo) array2.get(i23);
            if (objectMap2.get(c1MapInfo14) == null) {
                f2306a.i("- " + c1MapInfo14.f2309a.name, new Object[0]);
            }
        }
        f2306a.i("", new Object[0]);
        f2306a.i("Levels with a too small density of some resource:", new Object[0]);
        for (int i24 = 0; i24 < array2.size; i24++) {
            C1MapInfo c1MapInfo15 = (C1MapInfo) array2.get(i24);
            for (ResourceType resourceType8 : ResourceType.values) {
                float f11 = c1MapInfo15.f[resourceType8.ordinal()];
                if (f11 != 0.0f && f11 < 1.0f) {
                    f2306a.i("- " + c1MapInfo15.f2309a.name + " / " + resourceType8.name() + " / " + ((Object) StringFormatter.compactNumberWithPrecisionTrimZeros(f11, 1, false)), new Object[0]);
                }
            }
        }
        f2306a.i("", new Object[0]);
        f2306a.i("Individual levels that may suck (check manually for scripted levels):", new Object[0]);
        for (int i25 = 0; i25 < array2.size; i25++) {
            C1MapInfo c1MapInfo16 = (C1MapInfo) array2.get(i25);
            boolean containsKey = objectMap2.containsKey(c1MapInfo16);
            boolean z = c1MapInfo16.h >= 0.85f;
            if (!containsKey && !z) {
                f2306a.i("- " + c1MapInfo16.f2309a.name, new Object[0]);
            }
        }
        StringBuilder stringBuilder2 = new StringBuilder(SequenceUtils.EOL);
        for (int i26 = 0; i26 < array2.size; i26++) {
            C1MapInfo c1MapInfo17 = (C1MapInfo) array2.get(i26);
            stringBuilder2.append("m.put(\"").append(c1MapInfo17.f2309a.name).append("\", new float[]{ ");
            for (int i27 = 0; i27 < ResourceType.values.length; i27++) {
                if (i27 != 0) {
                    stringBuilder2.append(", ");
                }
                float f12 = c1MapInfo17.g[i27];
                if (f12 < 2.5d) {
                    stringBuilder2.append("0f");
                } else if (f12 <= 4.0f) {
                    stringBuilder2.append("3.5f");
                } else if (f12 <= 6.0f) {
                    stringBuilder2.append("3f");
                } else if (f12 <= 8.0f) {
                    stringBuilder2.append("2.5f");
                } else {
                    stringBuilder2.append("2f");
                }
            }
            stringBuilder2.append(" });\n");
        }
        f2306a.i(stringBuilder2.toString(), new Object[0]);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void handleGameOverBonusLoot(GameSystemProvider gameSystemProvider, String str, Array<IssuedItems> array) {
        PP_BasicLevel pP_BasicLevel = ProgressPrefs.i().basicLevel;
        f2306a.i(array.toString(","), new Object[0]);
        PP_BasicLevel.LevelLootBonus levelLootBonus = pP_BasicLevel.getLevelLootBonus(str);
        if (levelLootBonus != null) {
            f2306a.i("giving loot bonus " + levelLootBonus + " for level " + str, new Object[0]);
            int i = 0;
            while (true) {
                if (i >= array.size) {
                    break;
                }
                IssuedItems issuedItems = array.get(i);
                if (issuedItems.reason != IssuedItems.IssueReason.GAME_OVER_BASIC_LEVEL) {
                    i++;
                } else {
                    f2306a.i("found issued items", new Object[0]);
                    IssuedItems issuedItems2 = new IssuedItems(IssuedItems.IssueReason.BASIC_LEVEL_BONUS_ITEMS, Game.getTimestampSeconds());
                    switch (levelLootBonus.type) {
                        case RESOURCE_SCALAR:
                            for (int i2 = 0; i2 < issuedItems.items.size; i2++) {
                                ItemStack itemStack = issuedItems.items.get(i2);
                                if ((itemStack.getItem() instanceof ResourceItem) && ((ResourceItem) itemStack.getItem()).resourceType == ResourceType.SCALAR) {
                                    issuedItems2.items.add(new ItemStack(itemStack.getItem(), (int) (itemStack.getCount() * (levelLootBonus.multiplier - 1.0f))));
                                }
                            }
                            break;
                        case RESOURCE_VECTOR:
                            for (int i3 = 0; i3 < issuedItems.items.size; i3++) {
                                ItemStack itemStack2 = issuedItems.items.get(i3);
                                if ((itemStack2.getItem() instanceof ResourceItem) && ((ResourceItem) itemStack2.getItem()).resourceType == ResourceType.VECTOR) {
                                    issuedItems2.items.add(new ItemStack(itemStack2.getItem(), (int) (itemStack2.getCount() * (levelLootBonus.multiplier - 1.0f))));
                                }
                            }
                            break;
                        case RESOURCE_MATRIX:
                            for (int i4 = 0; i4 < issuedItems.items.size; i4++) {
                                ItemStack itemStack3 = issuedItems.items.get(i4);
                                if ((itemStack3.getItem() instanceof ResourceItem) && ((ResourceItem) itemStack3.getItem()).resourceType == ResourceType.MATRIX) {
                                    issuedItems2.items.add(new ItemStack(itemStack3.getItem(), (int) (itemStack3.getCount() * (levelLootBonus.multiplier - 1.0f))));
                                }
                            }
                            break;
                        case RESOURCE_TENSOR:
                            for (int i5 = 0; i5 < issuedItems.items.size; i5++) {
                                ItemStack itemStack4 = issuedItems.items.get(i5);
                                if ((itemStack4.getItem() instanceof ResourceItem) && ((ResourceItem) itemStack4.getItem()).resourceType == ResourceType.TENSOR) {
                                    issuedItems2.items.add(new ItemStack(itemStack4.getItem(), (int) (itemStack4.getCount() * (levelLootBonus.multiplier - 1.0f))));
                                }
                            }
                            break;
                        case RESOURCE_INFIAR:
                            for (int i6 = 0; i6 < issuedItems.items.size; i6++) {
                                ItemStack itemStack5 = issuedItems.items.get(i6);
                                if ((itemStack5.getItem() instanceof ResourceItem) && ((ResourceItem) itemStack5.getItem()).resourceType == ResourceType.INFIAR) {
                                    issuedItems2.items.add(new ItemStack(itemStack5.getItem(), (int) (itemStack5.getCount() * (levelLootBonus.multiplier - 1.0f))));
                                }
                            }
                            break;
                        case GREEN_PAPERS:
                            for (int i7 = 0; i7 < issuedItems.items.size; i7++) {
                                ItemStack itemStack6 = issuedItems.items.get(i7);
                                if (itemStack6.getItem() instanceof GreenPaperItem) {
                                    issuedItems2.items.add(new ItemStack(itemStack6.getItem(), (int) (itemStack6.getCount() * (levelLootBonus.multiplier - 1.0f))));
                                }
                            }
                            break;
                        case BIT_DUST:
                            for (int i8 = 0; i8 < issuedItems.items.size; i8++) {
                                ItemStack itemStack7 = issuedItems.items.get(i8);
                                if (itemStack7.getItem() instanceof BitDustItem) {
                                    issuedItems2.items.add(new ItemStack(itemStack7.getItem(), (int) (itemStack7.getCount() * (levelLootBonus.multiplier - 1.0f))));
                                }
                            }
                            break;
                    }
                    if (issuedItems2.items.size != 0) {
                        array.add(issuedItems2);
                    }
                }
            }
        }
        pP_BasicLevel.setPlayTimeSinceLevelLootBonusUpdate(pP_BasicLevel.getPlayTimeSinceLevelLootBonusUpdate() + ((int) gameSystemProvider.statistics.getStatistic(StatisticsType.PT)));
        ProgressPrefs.i().requireSave();
        if (pP_BasicLevel.getPlayTimeSinceLevelLootBonusUpdate() > 1200) {
            f2306a.i("generating new loot bonus levels", new Object[0]);
            ObjectMap<String, PP_BasicLevel.LevelLootBonus> objectMap = new ObjectMap<>();
            if (Game.i.minerManager.isMinerOpened(MinerType.MATRIX, Game.i.gameValueManager.getSnapshot())) {
                Array<BasicLevel> array2 = new Array<>(true, 1, BasicLevel.class);
                for (int i9 = 0; i9 < this.levelsOrdered.size; i9++) {
                    BasicLevel basicLevel = this.levelsOrdered.get(i9);
                    if (isOpened(basicLevel) && !basicLevel.isBonus && (basicLevel.stageName.equals("1") || basicLevel.stageName.equals("2") || basicLevel.stageName.equals("3") || basicLevel.stageName.equals("4") || basicLevel.stageName.equals("5") || basicLevel.stageName.equals("6"))) {
                        array2.add(basicLevel);
                        f2306a.i("- opened level: " + basicLevel.name, new Object[0]);
                    }
                }
                RandomXS128 otherItemsRandom = ProgressPrefs.i().progress.getOtherItemsRandom();
                for (BasicLevelLootBonusType basicLevelLootBonusType : BasicLevelLootBonusType.values) {
                    switch (basicLevelLootBonusType) {
                        case RESOURCE_SCALAR:
                            selectLootBonusLevelForResource(BasicLevelLootBonusType.RESOURCE_SCALAR, ResourceType.SCALAR, array2, otherItemsRandom, objectMap);
                            break;
                        case RESOURCE_VECTOR:
                            selectLootBonusLevelForResource(BasicLevelLootBonusType.RESOURCE_VECTOR, ResourceType.VECTOR, array2, otherItemsRandom, objectMap);
                            break;
                        case RESOURCE_MATRIX:
                            selectLootBonusLevelForResource(BasicLevelLootBonusType.RESOURCE_MATRIX, ResourceType.MATRIX, array2, otherItemsRandom, objectMap);
                            break;
                        case RESOURCE_TENSOR:
                            if (Game.i.minerManager.isMinerOpened(MinerType.TENSOR, Game.i.gameValueManager.getSnapshot())) {
                                selectLootBonusLevelForResource(BasicLevelLootBonusType.RESOURCE_TENSOR, ResourceType.TENSOR, array2, otherItemsRandom, objectMap);
                                break;
                            } else {
                                f2306a.i("skipped RESOURCE_TENSOR loot bonus - miner not unlocked", new Object[0]);
                                break;
                            }
                        case RESOURCE_INFIAR:
                            if (Game.i.minerManager.isMinerOpened(MinerType.INFIAR, Game.i.gameValueManager.getSnapshot())) {
                                selectLootBonusLevelForResource(BasicLevelLootBonusType.RESOURCE_INFIAR, ResourceType.INFIAR, array2, otherItemsRandom, objectMap);
                                break;
                            } else {
                                f2306a.i("skipped RESOURCE_INFIAR loot bonus - miner not unlocked", new Object[0]);
                                break;
                            }
                        case GREEN_PAPERS:
                            if (array2.size != 0) {
                                objectMap.put(array2.removeIndex(otherItemsRandom.nextInt(array2.size)).name, new PP_BasicLevel.LevelLootBonus(basicLevelLootBonusType, 2.5f));
                                break;
                            } else {
                                break;
                            }
                        case BIT_DUST:
                            if (Game.i.progressManager.difficultyModeAvailable(DifficultyMode.ENDLESS_I) && array2.size != 0) {
                                objectMap.put(array2.removeIndex(otherItemsRandom.nextInt(array2.size)).name, new PP_BasicLevel.LevelLootBonus(basicLevelLootBonusType, 2.5f));
                                break;
                            }
                            break;
                    }
                }
                boolean z = otherItemsRandom.nextFloat() < 0.07f;
                ObjectMap.Entries<String, PP_BasicLevel.LevelLootBonus> it = objectMap.iterator();
                while (it.hasNext()) {
                    ObjectMap.Entry next = it.next();
                    if (otherItemsRandom.nextFloat() < 0.15f) {
                        ((PP_BasicLevel.LevelLootBonus) next.value).multiplier += 0.5f;
                    }
                    if (z) {
                        ((PP_BasicLevel.LevelLootBonus) next.value).multiplier += 1.0f;
                    } else if (otherItemsRandom.nextFloat() < 0.15f) {
                        ((PP_BasicLevel.LevelLootBonus) next.value).multiplier += 1.0f;
                        if (otherItemsRandom.nextFloat() < 0.5f) {
                            ((PP_BasicLevel.LevelLootBonus) next.value).multiplier += 0.5f;
                        }
                    }
                }
                ProgressPrefs.i().requireSave();
            } else {
                f2306a.i("skipped level loot bonus assignation - Matrix not unlocked", new Object[0]);
            }
            pP_BasicLevel.setLevelLootBonuses(objectMap);
            pP_BasicLevel.setPlayTimeSinceLevelLootBonusUpdate(0);
            ProgressPrefs.i().requireSave();
        }
    }

    public void saveStagesConfigOnDisk() {
        StringWriter stringWriter = new StringWriter();
        Json json = new Json(JsonWriter.OutputType.json);
        json.setWriter(stringWriter);
        json.writeArrayStart();
        for (int i = 0; i < this.stagesOrdered.size; i++) {
            BasicLevelStage basicLevelStage = this.stagesOrdered.get(i);
            json.writeObjectStart();
            basicLevelStage.toJson(json);
            json.writeObjectEnd();
        }
        json.writeArrayEnd();
        Gdx.files.local("res/basic-level-stages.json").writeString(json.prettyPrint(stringWriter.toString()), false, "UTF-8");
    }

    public void selectLootBonusLevelForResource(BasicLevelLootBonusType basicLevelLootBonusType, ResourceType resourceType, Array<BasicLevel> array, RandomXS128 randomXS128, ObjectMap<String, PP_BasicLevel.LevelLootBonus> objectMap) {
        f2306a.i("selectLootBonusLevelForResource " + resourceType, new Object[0]);
        if (array.size == 0) {
            return;
        }
        Array array2 = new Array(true, 1, BasicLevel.class);
        for (int i = 0; i < array.size; i++) {
            BasicLevel basicLevel = array.get(i);
            if (this.resourceBonusLevelMultipliers.containsKey(basicLevel.name) && this.resourceBonusLevelMultipliers.get(basicLevel.name)[resourceType.ordinal()] != 0.0f) {
                array2.add(basicLevel);
            }
        }
        if (array2.size != 0) {
            BasicLevel basicLevel2 = (BasicLevel) array2.get(randomXS128.nextInt(array2.size));
            f2306a.i("selected level: " + basicLevel2, new Object[0]);
            float f = this.resourceBonusLevelMultipliers.get(basicLevel2.name)[resourceType.ordinal()];
            array.removeValue(basicLevel2, true);
            objectMap.put(basicLevel2.name, new PP_BasicLevel.LevelLootBonus(basicLevelLootBonusType, f));
        }
    }

    @Null
    public BasicLevel getNextVisibleLevel(BasicLevel basicLevel) {
        BasicLevel basicLevel2;
        BasicLevel nextLevel = getNextLevel(basicLevel);
        while (true) {
            basicLevel2 = nextLevel;
            if (basicLevel2 == null || isLevelVisible(basicLevel2)) {
                break;
            }
            nextLevel = getNextLevel(basicLevel2);
        }
        return basicLevel2;
    }

    @Null
    public BasicLevel getNextLevel(BasicLevel basicLevel) {
        BasicLevelStage stage = getStage(basicLevel.stageName);
        for (int i = 0; i < stage.levels.size; i++) {
            if (stage.levels.get(i).name.equals(basicLevel.name)) {
                BasicLevel basicLevel2 = stage.levels.size > i + 1 ? stage.levels.get(i + 1) : null;
                BasicLevel basicLevel3 = basicLevel2;
                if (basicLevel2 != null) {
                    return basicLevel3;
                }
                for (int i2 = 0; i2 < this.stagesOrdered.size; i2++) {
                    if (this.stagesOrdered.get(i2).name.equals(stage.name)) {
                        BasicLevelStage basicLevelStage = this.stagesOrdered.size > i2 + 1 ? this.stagesOrdered.get(i2 + 1) : null;
                        BasicLevelStage basicLevelStage2 = basicLevelStage;
                        if (basicLevelStage != null && basicLevelStage2.levels.size != 0) {
                            BasicLevel first = basicLevelStage2.levels.first();
                            if (first.dailyQuest == basicLevel.dailyQuest) {
                                return first;
                            }
                            return null;
                        }
                        return null;
                    }
                }
                return null;
            }
        }
        return null;
    }

    public QuestsPrestigeMilestone[] getQuestsPrestigeMilestones() {
        float percentValueAsMultiplier = (float) Game.i.gameValueManager.getSnapshot().getPercentValueAsMultiplier(GameValueType.PRESTIGE_DUST_DROP_RATE);
        return new QuestsPrestigeMilestone[]{new QuestsPrestigeMilestone(20, MathUtils.round(100.0f * percentValueAsMultiplier)), new QuestsPrestigeMilestone(50, MathUtils.round(300.0f * percentValueAsMultiplier)), new QuestsPrestigeMilestone(75, MathUtils.round(700.0f * percentValueAsMultiplier)), new QuestsPrestigeMilestone(100, MathUtils.round(1200.0f * percentValueAsMultiplier))};
    }

    @Null
    public BasicLevelQuestConfig getRegularQuestById(String str) {
        return this.d.get(str, null);
    }

    public int getPrestigeMaxCompletedQuests() {
        int i = 0;
        for (int i2 = 0; i2 < this.stagesOrdered.size; i2++) {
            BasicLevelStage basicLevelStage = this.stagesOrdered.items[i2];
            if (basicLevelStage.regular) {
                for (int i3 = 0; i3 < basicLevelStage.levels.size; i3++) {
                    i += basicLevelStage.levels.items[i3].quests.size;
                }
            }
        }
        return i;
    }

    public int getPrestigeCompletedQuests() {
        int i = 0;
        for (int i2 = 0; i2 < this.stagesOrdered.size; i2++) {
            BasicLevelStage basicLevelStage = this.stagesOrdered.items[i2];
            if (basicLevelStage.regular) {
                for (int i3 = 0; i3 < basicLevelStage.levels.size; i3++) {
                    BasicLevel basicLevel = basicLevelStage.levels.items[i3];
                    for (int i4 = 0; i4 < basicLevel.quests.size; i4++) {
                        if (basicLevel.quests.items[i4].isCompleted()) {
                            i++;
                        }
                    }
                }
            }
        }
        return i;
    }

    public void resetQuestsForPrestige() {
        int i;
        int prestigeCompletedQuests = (int) (((getPrestigeCompletedQuests() / getPrestigeMaxCompletedQuests()) * 100.0d) + 1.0E-4d);
        QuestsPrestigeMilestone[] questsPrestigeMilestones = getQuestsPrestigeMilestones();
        int length = questsPrestigeMilestones.length - 1;
        while (true) {
            if (length < 0) {
                break;
            }
            if (prestigeCompletedQuests < questsPrestigeMilestones[length].percentage) {
                length--;
            } else {
                int i2 = questsPrestigeMilestones[length].tokensCount;
                Game.i.progressManager.addItems(Item.D.PRESTIGE_TOKEN, i2, "quests_prestige");
                IssuedItems issuedItems = new IssuedItems(IssuedItems.IssueReason.QUESTS_PRESTIGE, Game.getTimestampSeconds());
                issuedItems.items.add(new ItemStack(Item.D.PRESTIGE_TOKEN, i2));
                Game.i.progressManager.addIssuedPrizes(issuedItems, true);
                Game.i.statisticsManager.registerDelta(StatisticsType.PQR, 1.0d);
                Game.i.statisticsManager.registerDelta(StatisticsType.PPG, i2);
                for (int i3 = 0; i3 < this.stagesOrdered.size; i3++) {
                    BasicLevelStage basicLevelStage = this.stagesOrdered.items[i3];
                    if (basicLevelStage.regular) {
                        for (int i4 = 0; i4 < basicLevelStage.levels.size; i4++) {
                            BasicLevel basicLevel = basicLevelStage.levels.items[i4];
                            for (int i5 = 0; i5 < basicLevel.quests.size; i5++) {
                                BasicLevelQuestConfig basicLevelQuestConfig = basicLevel.quests.items[i5];
                                for (0; i < basicLevelQuestConfig.prizes.size; i + 1) {
                                    i = basicLevelQuestConfig.prizes.items[i].getItem() instanceof StarItem ? 0 : i + 1;
                                }
                                if (basicLevelQuestConfig.isCompleted()) {
                                    basicLevelQuestConfig.setSavedValue(0L);
                                    basicLevelQuestConfig.setCompleted(false);
                                }
                            }
                        }
                    }
                }
                Game.i.dailyQuestManager.setDailyLootQuestCompleted();
                Game.i.progressManager.showNewlyIssuedPrizesPopup();
                Game.i.achievementManager.setProgress(AchievementType.PRESTIGE, 1);
            }
        }
        f2306a.e("no milestone reached, can't prestige quests", new Object[0]);
    }

    public void addListener(BasicLevelManagerListener basicLevelManagerListener) {
        if (!this.f.contains(basicLevelManagerListener, true)) {
            this.f.add(basicLevelManagerListener);
        }
    }

    public void removeListener(BasicLevelManagerListener basicLevelManagerListener) {
        this.f.removeValue(basicLevelManagerListener, true);
    }

    public void unloadLevel(String str) {
        if (this.c.containsKey(str)) {
            for (int i = 0; i < this.stagesOrdered.size; i++) {
                BasicLevelStage basicLevelStage = this.stagesOrdered.get(i);
                int i2 = 0;
                while (true) {
                    if (i2 >= basicLevelStage.levels.size) {
                        break;
                    }
                    if (!basicLevelStage.levels.get(i2).name.equals(str)) {
                        i2++;
                    } else {
                        basicLevelStage.levels.removeIndex(i2);
                        break;
                    }
                }
            }
            int i3 = 0;
            while (true) {
                if (i3 >= this.levelsOrdered.size) {
                    break;
                }
                if (!this.levelsOrdered.get(i3).name.equals(str)) {
                    i3++;
                } else {
                    this.levelsOrdered.removeIndex(i3);
                    break;
                }
            }
            this.c.remove(str);
            try {
                if (Game.i.screenManager != null && (Game.i.screenManager.getCurrentScreen() instanceof LevelSelectScreen)) {
                    Game.i.screenManager.goToLevelSelectScreen();
                }
            } catch (Throwable unused) {
            }
        }
    }

    public void registerLevel(BasicLevel basicLevel) {
        for (int i = 0; i < basicLevel.quests.size; i++) {
            BasicLevelQuestConfig basicLevelQuestConfig = basicLevel.quests.items[i];
            this.d.put(basicLevelQuestConfig.id, basicLevelQuestConfig);
        }
        unloadLevel(basicLevel.name);
        BasicLevelStage stage = getStage(basicLevel.stageName);
        BasicLevelStage basicLevelStage = stage;
        if (stage == null) {
            basicLevelStage = getStage("-1");
            f2306a.e("Stage " + basicLevel.stageName + " not found for level " + basicLevel.name + ", falling back to stage -1", new Object[0]);
        }
        basicLevelStage.levels.add(basicLevel);
        this.levelsOrdered.add(basicLevel);
        this.c.put(basicLevel.name, basicLevel);
    }

    public BasicLevel loadAndRegisterLevelFromJson(JsonValue jsonValue) {
        BasicLevel fromJson = BasicLevel.fromJson(jsonValue);
        registerLevel(fromJson);
        return fromJson;
    }

    @Deprecated
    public String generateLevelsJson() {
        Json json = new Json(JsonWriter.OutputType.json);
        StringWriter stringWriter = new StringWriter();
        json.setWriter(stringWriter);
        json.writeObjectStart();
        json.writeArrayStart("stages");
        json.writeArrayEnd();
        json.writeArrayStart("levels");
        json.writeArrayEnd();
        json.writeObjectEnd();
        return stringWriter.toString();
    }

    public boolean mapEditingAvailable() {
        return Game.i.progressManager.isDeveloperModeEnabled();
    }

    @Null
    public BasicLevelStage getStage(String str) {
        return this.f2307b.get(str);
    }

    @Null
    public BasicLevelStage getLevelStage(String str) {
        return getStage(getLevel(str).stageName);
    }

    public BasicLevel getLevel(String str) {
        return this.c.get(str, null);
    }

    public int getQuestSkipPrice(BasicLevelQuestConfig basicLevelQuestConfig) {
        if (basicLevelQuestConfig.scripted || basicLevelQuestConfig.isCompleted() || !getStage(basicLevelQuestConfig.level.stageName).regular) {
            return 0;
        }
        for (int i = 0; i < basicLevelQuestConfig.prizes.size; i++) {
            if (basicLevelQuestConfig.prizes.items[i].getItem() instanceof StarItem) {
                return 0;
            }
        }
        int i2 = 0;
        for (int i3 = 0; i3 < basicLevelQuestConfig.level.quests.size; i3++) {
            i2 = i3;
            if (basicLevelQuestConfig.level.quests.items[i3] == basicLevelQuestConfig) {
                break;
            }
        }
        return i2 + 10;
    }

    public void skipQuest(BasicLevelQuestConfig basicLevelQuestConfig) {
        int questSkipPrice = getQuestSkipPrice(basicLevelQuestConfig);
        if (Game.i.progressManager.removeItems(Item.D.ACCELERATOR, questSkipPrice)) {
            Game.i.analyticsManager.logCurrencySpent("quest_skip", "accelerator", questSkipPrice);
            basicLevelQuestConfig.setCompleted(true);
            if (Game.i.dailyQuestManager.getDailyLootCurrentQuest().equals(basicLevelQuestConfig.id)) {
                Game.i.dailyQuestManager.setDailyLootQuestCompleted();
                return;
            }
            return;
        }
        Notifications.i().add(Game.i.localeManager.i18n.get("not_enough_accelerators"), Game.i.assetManager.getDrawable("icon-times"), MaterialColor.RED.P800, StaticSoundType.FAIL);
    }

    public boolean isQuestCompleted(String str) {
        return ProgressPrefs.i().basicLevel.isQuestCompleted(str);
    }

    public boolean isQuestEverCompleted(String str) {
        return ProgressPrefs.i().basicLevel.isQuestEverCompleted(str);
    }

    public void setQuestCompleted(String str, boolean z) {
        if (z) {
            if (!isQuestCompleted(str)) {
                ProgressPrefs i = ProgressPrefs.i();
                i.basicLevel.setQuestCompleted(str, true);
                i.basicLevel.setQuestEverCompleted(str, true);
                i.requireSave();
                Game.i.analyticsManager.logCustomEvent("basic_level_quest_completed", new String[]{Attribute.ID_ATTR, str}, new String[0]);
                Game.i.progressManager.checkSpecialTrophiesGiven();
                return;
            }
            return;
        }
        if (isQuestCompleted(str)) {
            f2306a.i("cleared completed quest: " + str, new Object[0]);
            ProgressPrefs i2 = ProgressPrefs.i();
            i2.basicLevel.setQuestCompleted(str, false);
            i2.requireSave();
        }
    }

    public int getGainedStarsOnLevel(BasicLevel basicLevel) {
        int i = 0;
        for (int i2 = 0; i2 < basicLevel.waveQuests.size; i2++) {
            BasicLevel.WaveQuest waveQuest = basicLevel.waveQuests.get(i2);
            if (waveQuest.isCompleted()) {
                for (int i3 = 0; i3 < waveQuest.prizes.size; i3++) {
                    ItemStack itemStack = waveQuest.prizes.get(i3);
                    if (itemStack.getItem() instanceof StarItem) {
                        i += itemStack.getCount();
                    }
                }
            }
        }
        for (int i4 = 0; i4 < basicLevel.quests.size; i4++) {
            BasicLevelQuestConfig basicLevelQuestConfig = basicLevel.quests.get(i4);
            if (basicLevelQuestConfig.isCompleted()) {
                for (int i5 = 0; i5 < basicLevelQuestConfig.prizes.size; i5++) {
                    ItemStack itemStack2 = basicLevelQuestConfig.prizes.get(i5);
                    if (itemStack2.getItem() instanceof StarItem) {
                        i += itemStack2.getCount();
                    }
                }
            }
        }
        return i;
    }

    public int getGainedStarsOnStage(BasicLevelStage basicLevelStage) {
        int i = 0;
        for (int i2 = 0; i2 < basicLevelStage.levels.size; i2++) {
            if (!basicLevelStage.levels.items[i2].dailyQuest) {
                i += getGainedStarsOnLevel(basicLevelStage.levels.items[i2]);
            }
        }
        return i;
    }

    public int getMaxStars(BasicLevelStage basicLevelStage, boolean z) {
        if (z) {
            int i = 0;
            for (int i2 = 0; i2 < basicLevelStage.levels.size; i2++) {
                if (Game.i.basicLevelManager.isLevelVisible(basicLevelStage.levels.get(i2))) {
                    i += 3;
                }
            }
            return i;
        }
        return basicLevelStage.levels.size * 3;
    }

    public boolean isStageVisible(BasicLevelStage basicLevelStage) {
        if (Config.isModdingMode() || Game.i.progressManager.isDeveloperModeEnabled()) {
            return true;
        }
        for (int i = 0; i < basicLevelStage.showRequirements.size; i++) {
            if (!basicLevelStage.showRequirements.get(i).isSatisfied()) {
                return false;
            }
        }
        return true;
    }

    public boolean isLevelVisible(BasicLevel basicLevel) {
        if (!isStageVisible(getStage(basicLevel.stageName))) {
            return false;
        }
        for (int i = 0; i < basicLevel.showRequirements.size; i++) {
            if (!basicLevel.showRequirements.get(i).isSatisfied()) {
                return false;
            }
        }
        return true;
    }

    public boolean isOpened(BasicLevel basicLevel) {
        if (basicLevel.isPurchasedOrPlayed()) {
            return true;
        }
        if (basicLevel.priceInMoney > 0) {
            return false;
        }
        for (int i = 0; i < basicLevel.priceInResources.length; i++) {
            if (basicLevel.priceInResources[i] > 0) {
                return false;
            }
        }
        for (int i2 = 0; i2 < basicLevel.openRequirements.size; i2++) {
            if (!basicLevel.openRequirements.get(i2).isSatisfied()) {
                return false;
            }
        }
        return true;
    }

    public boolean canBePurchased(BasicLevel basicLevel) {
        for (int i = 0; i < basicLevel.openRequirements.size; i++) {
            if (!basicLevel.openRequirements.get(i).isSatisfied()) {
                return false;
            }
        }
        if (basicLevel.priceInMoney > Game.i.progressManager.getGreenPapers()) {
            return false;
        }
        for (int i2 = 0; i2 < basicLevel.priceInResources.length; i2++) {
            if (basicLevel.priceInResources[i2] > Game.i.progressManager.getResources(ResourceType.values[i2])) {
                return false;
            }
        }
        return true;
    }

    public boolean isMastered(BasicLevel basicLevel) {
        for (int i = 0; i < basicLevel.quests.size; i++) {
            if (!basicLevel.quests.get(i).isCompleted()) {
                return false;
            }
        }
        for (int i2 = 0; i2 < basicLevel.waveQuests.size; i2++) {
            if (!basicLevel.waveQuests.get(i2).isCompleted()) {
                return false;
            }
        }
        return true;
    }

    public Array<BasicLevel> getMasteredLevels() {
        BasicLevelStage stage = getStage("0");
        g.clear();
        for (int i = 0; i < this.levelsOrdered.size; i++) {
            BasicLevel basicLevel = this.levelsOrdered.items[i];
            if (getStage(basicLevel.stageName) != stage && isMastered(basicLevel)) {
                g.add(basicLevel);
            }
        }
        return g;
    }

    public boolean playedBefore(BasicLevel basicLevel) {
        return ProgressPrefs.i().basicLevel.getLevelStartsCount(basicLevel.name) > 0;
    }

    public int getMaxStarsCount() {
        return this.e;
    }

    public int getMaxReachedWave(String str) {
        if (Config.isHeadless()) {
            return 0;
        }
        return ProgressPrefs.i().basicLevel.getLevelMaxReachedWave(str);
    }

    public int getGainedStars() {
        int i = 0;
        for (int i2 = 0; i2 < this.stagesOrdered.size; i2++) {
            i += getGainedStarsOnStage(this.stagesOrdered.items[i2]);
        }
        return i;
    }

    public void setMap(BasicLevel basicLevel, Map map) {
        if (mapEditingAvailable()) {
            try {
                Json json = new Json(JsonWriter.OutputType.json);
                StringWriter stringWriter = new StringWriter();
                json.setWriter(stringWriter);
                json.writeObjectStart();
                map.toJson(json);
                json.writeObjectEnd();
                Gdx.files.local("levels/maps/" + basicLevel.name + ".json").writeString(new JsonReader().parse(stringWriter.toString()).prettyPrint(JsonWriter.OutputType.json, 2), false, "UTF-8");
                basicLevel.setMap(map);
                f2306a.i("map saved", new Object[0]);
                return;
            } catch (Exception e) {
                f2306a.e("unable to write map", e);
                return;
            }
        }
        f2306a.e("unable to set basic level map on this OS or not in developer mode", new Object[0]);
    }

    public void setPurchased(BasicLevel basicLevel) {
        if (!ProgressPrefs.i().basicLevel.isLevelPurchased(basicLevel.name)) {
            ProgressPrefs.i().basicLevel.setLevelPurchased(basicLevel.name, true);
            ProgressPrefs.i().requireSave();
            Game.i.analyticsManager.logCustomEvent("basic_level_purchased", new String[]{Attribute.NAME_ATTR, basicLevel.name}, new String[0]);
        }
        Game.i.researchManager.checkResearchesStatus(true);
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void test() {
        for (int i = 0; i < this.levelsOrdered.size; i++) {
            BasicLevel basicLevel = this.levelsOrdered.get(i);
            try {
                basicLevel.getPreview();
            } catch (Exception e) {
                f2306a.e("Test: failed BasicLevel#getPreview() for level " + basicLevel.name, new Object[0]);
                throw e;
            }
        }
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.badlogic.gdx.utils.Disposable
    public void dispose() {
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/BasicLevelManager$QuestsPrestigeMilestone.class */
    public static class QuestsPrestigeMilestone {
        public int percentage;
        public int tokensCount;

        public QuestsPrestigeMilestone(int i, int i2) {
            this.percentage = i;
            this.tokensCount = i2;
        }
    }
}
