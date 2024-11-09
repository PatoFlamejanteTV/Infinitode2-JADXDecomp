package com.prineside.tdi2.systems;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectSet;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.BasicLevel;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.EnemyGroup;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystem;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.Map;
import com.prineside.tdi2.SerializableListener;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.UserMap;
import com.prineside.tdi2.Wave;
import com.prineside.tdi2.WaveProcessor;
import com.prineside.tdi2.WaveTemplates;
import com.prineside.tdi2.actions.CallWaveAction;
import com.prineside.tdi2.configs.GameRenderingOrder;
import com.prineside.tdi2.enums.BossType;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.DifficultyMode;
import com.prineside.tdi2.enums.EnemyType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.EnemyDie;
import com.prineside.tdi2.events.game.EnemyReachTarget;
import com.prineside.tdi2.events.game.EnemyTakeDamage;
import com.prineside.tdi2.events.game.WaveStart;
import com.prineside.tdi2.events.game.WaveStatusChange;
import com.prineside.tdi2.systems.RenderSystem;
import com.prineside.tdi2.tiles.SpawnTile;
import com.prineside.tdi2.tiles.TargetTile;
import com.prineside.tdi2.ui.shared.WavesTimelineOverlay;
import com.prineside.tdi2.utils.IgnoreMethodOverloadLuaDefWarning;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.NoFieldKryoSerializable;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.WaveBossSupplier;
import com.prineside.tdi2.utils.WaveDifficultyProvider;
import com.prineside.tdi2.utils.logging.TLog;
import com.prineside.tdi2.waves.processors.BrootBossWaveProcessor;
import com.prineside.tdi2.waves.processors.ConstructorBossWaveProcessor;
import com.prineside.tdi2.waves.processors.MetaphorBossWaveProcessor;
import com.prineside.tdi2.waves.processors.MobchainBossWaveProcessor;
import com.prineside.tdi2.waves.processors.SnakeBossWaveProcessor;
import java.util.Arrays;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/WaveSystem.class */
public final class WaveSystem extends GameSystem {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3073a = TLog.forClass(WaveSystem.class);
    public static final int NEXT_WAVES_CACHE_SIZE = 10;
    public static final float ULTRA_DIFFICULT_MILESTONE_MULTIPLIER = 1.75f;
    public static final int WAVES_TIMELINE_MAX_WAVE = 100;
    public static final float ENEMY_INTERVAL_DENSITY_HIGH = 0.25f;
    public static final float ENEMY_INTERVAL_DENSITY_LOW = 1.0f;
    public Status status;
    public Wave wave;
    private float d;
    private float e;
    private float f;
    private boolean h;
    private boolean i;
    private int j;
    private float k;
    private float l;
    public WaveTemplates.PredefinedWaveTemplate[] predefinedWaveTemplates;
    public WaveBossSupplier bossWaves;
    private WaveGenerator o;

    @NAGS
    private final RandomXS128 u;

    @NAGS
    private long v;

    @NAGS
    private int w;

    /* renamed from: b, reason: collision with root package name */
    private WaveTemplates.WaveTemplate f3074b = null;
    public Mode mode = Mode.ENDLESS;
    private DelayedRemovalArray<WaveProcessor> c = new DelayedRemovalArray<>(WaveProcessor.class);

    @NAGS
    public boolean autoForceWaveEnabled = false;

    @NAGS
    public boolean instantWaveCallsEnabled = false;
    public WaveCache[] nextWavesCache = new WaveCache[10];
    public DelayedRemovalArray<Wave> wavesToNotifyAboutCompletion = new DelayedRemovalArray<>(true, 1, Wave.class);
    private int g = 0;
    private float m = 1.0f;

    @NAGS
    private WaveDifficultyProvider n = null;
    private IntMap<WaveCache> p = new IntMap<>();

    @NAGS
    private final ObjectSet<EnemyType> q = new ObjectSet<>();

    @NAGS
    private final Array<SpawnTile> r = new Array<>(true, 1, SpawnTile.class);

    @NAGS
    private final DelayedRemovalArray<Wave> s = new DelayedRemovalArray<>(true, 1, Wave.class);

    @NAGS
    private final ObjectMap<BossType, WaveProcessor.WaveProcessorFactory<?>> t = new ObjectMap<>();

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/WaveSystem$Mode.class */
    public enum Mode {
        ENDLESS,
        PREDEFINED
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/WaveSystem$Status.class */
    public enum Status {
        NOT_STARTED,
        SPAWNING,
        SPAWNED,
        ENDED
    }

    @REGS(classOnly = true)
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/WaveSystem$WaveGenerator.class */
    public interface WaveGenerator {
        Wave generate(int i, Wave wave, GameSystemProvider gameSystemProvider, int i2);
    }

    public WaveSystem() {
        this.t.put(BossType.SNAKE, new SnakeBossWaveProcessor.SnakeBossWaveProcessorFactory());
        this.t.put(BossType.BROOT, new BrootBossWaveProcessor.BrootBossWaveProcessorFactory());
        this.t.put(BossType.CONSTRUCTOR, new ConstructorBossWaveProcessor.ConstructorBossWaveProcessorFactory());
        this.t.put(BossType.MOBCHAIN, new MobchainBossWaveProcessor.MobchainBossWaveProcessorFactory());
        this.t.put(BossType.METAPHOR, new MetaphorBossWaveProcessor.MetaphorBossWaveProcessorFactory());
        for (BossType bossType : BossType.values) {
            if (!this.t.containsKey(bossType)) {
                throw new RuntimeException("Not all wave processor factories created");
            }
        }
        for (BossType bossType2 : BossType.values) {
            this.t.get(bossType2).setup();
        }
        this.u = new RandomXS128(1337L);
        this.v = Long.MIN_VALUE;
        this.w = -1;
    }

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeClassAndObject(output, this.f3074b);
        kryo.writeObject(output, this.mode);
        kryo.writeObjectOrNull(output, this.status, Status.class);
        kryo.writeClassAndObject(output, this.wave);
        kryo.writeObject(output, this.c);
        output.writeFloat(this.d);
        output.writeFloat(this.e);
        output.writeFloat(this.f);
        kryo.writeObject(output, this.nextWavesCache);
        kryo.writeObject(output, this.wavesToNotifyAboutCompletion);
        output.writeVarInt(this.g, true);
        output.writeBoolean(this.h);
        output.writeBoolean(this.i);
        output.writeVarInt(this.j, true);
        output.writeFloat(this.k);
        output.writeFloat(this.l);
        output.writeFloat(this.m);
        kryo.writeClassAndObject(output, this.predefinedWaveTemplates);
        kryo.writeClassAndObject(output, this.bossWaves);
        kryo.writeClassAndObject(output, this.o);
        kryo.writeClassAndObject(output, this.p);
    }

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f3074b = (WaveTemplates.WaveTemplate) kryo.readClassAndObject(input);
        this.mode = (Mode) kryo.readObject(input, Mode.class);
        this.status = (Status) kryo.readObjectOrNull(input, Status.class);
        this.wave = (Wave) kryo.readClassAndObject(input);
        this.c = (DelayedRemovalArray) kryo.readObject(input, DelayedRemovalArray.class);
        this.d = input.readFloat();
        this.e = input.readFloat();
        this.f = input.readFloat();
        this.nextWavesCache = (WaveCache[]) kryo.readObject(input, WaveCache[].class);
        this.wavesToNotifyAboutCompletion = (DelayedRemovalArray) kryo.readObject(input, DelayedRemovalArray.class);
        this.g = input.readVarInt(true);
        this.h = input.readBoolean();
        this.i = input.readBoolean();
        this.j = input.readVarInt(true);
        this.k = input.readFloat();
        this.l = input.readFloat();
        this.m = input.readFloat();
        this.predefinedWaveTemplates = (WaveTemplates.PredefinedWaveTemplate[]) kryo.readClassAndObject(input);
        this.bossWaves = (WaveBossSupplier) kryo.readClassAndObject(input);
        this.o = (WaveGenerator) kryo.readClassAndObject(input);
        this.p = (IntMap) kryo.readClassAndObject(input);
    }

    @Override // com.prineside.tdi2.GameSystem
    public final boolean affectsGameState() {
        return true;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void setup() {
        this.S.events.getListeners(EnemyDie.class).addStateAffecting(new OnEnemyDie(this));
        this.S.events.getListeners(EnemyTakeDamage.class).addStateAffecting(new OnEnemyTakeDamage());
        this.S.events.getListeners(EnemyReachTarget.class).addStateAffecting(new OnEnemyReachTarget(this));
        if (!this.S.CFG.headless) {
            a();
        }
    }

    private void a() {
        this.S._render.addLayer(new RenderSystem.Layer(GameRenderingOrder.WAVE_DRAW, false, (batch, f, f2, f3) -> {
            draw(batch, f2);
        }).setName("Wave-draw"));
    }

    public final void draw(Batch batch, float f) {
        for (int i = 0; i < this.c.size; i++) {
            this.c.items[i].draw(batch, f);
        }
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [com.prineside.tdi2.WaveProcessor] */
    public final WaveProcessor createBossWaveProcessor(BossType bossType) {
        return this.t.get(bossType).create();
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [com.prineside.tdi2.WaveProcessor] */
    public final Wave generateBossWaveWithProcessor(BossType bossType, int i, int i2) {
        return this.t.get(bossType).create().setup(this.S, i, i2);
    }

    public final WaveProcessor.WaveProcessorFactory<?> getWaveProcessorFactory(BossType bossType) {
        return this.t.get(bossType);
    }

    public final float getWaveStartInterval() {
        return this.S.gameValue.getFloatValue(GameValueType.WAVE_INTERVAL);
    }

    public final void setWaveGenerator(WaveGenerator waveGenerator) {
        this.o = waveGenerator;
        resetNextWavesCache();
        b();
    }

    public final WaveGenerator getWaveGenerator() {
        return this.o;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void postSetup() {
        setStatus(Status.NOT_STARTED);
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void postStateRestore() {
        this.instantWaveCallsEnabled = Game.i.settingsManager.isInstantAutoWaveCallEnabled();
        a();
    }

    public final void setForcedTemplate(String str) {
        WaveTemplates.WaveTemplate waveTemplate = null;
        WaveTemplates.WaveTemplate[] waveTemplateArr = WaveTemplates.WAVE_TEMPLATES;
        int length = waveTemplateArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            WaveTemplates.WaveTemplate waveTemplate2 = waveTemplateArr[i];
            if (!waveTemplate2.getWaveName().equals(str)) {
                i++;
            } else {
                waveTemplate = waveTemplate2;
                break;
            }
        }
        if (waveTemplate == null) {
            throw new IllegalArgumentException("Forced wave template '" + this.f3074b + "' not found");
        }
        this.f3074b = waveTemplate;
        resetNextWavesCache();
        b();
    }

    public final void overrideWave(int i, Wave wave) {
        this.S.gameState.checkGameplayUpdateAllowed();
        if (this.wave != null && this.wave.waveNumber >= i) {
            f3073a.e("failed to override wave - wave " + i + " already spawned", new Object[0]);
            return;
        }
        this.p.put(i, new WaveCache(wave, i));
        f3073a.i("overriding wave " + i + " with wave " + wave, new Object[0]);
        resetNextWavesCache();
        b();
    }

    public final int getNextOverridableWaveNumber() {
        if (this.wave == null) {
            return 1;
        }
        if (d() == null) {
            return this.wave.waveNumber + 1;
        }
        for (int i = this.wave.waveNumber + 1; i < this.wave.waveNumber + 51; i++) {
            if (!this.p.containsKey(i)) {
                f3073a.i("getNextOverridableWaveNumber in loop, current " + this.wave.waveNumber + ", return " + i, new Object[0]);
                return i;
            }
        }
        return -1;
    }

    public final void setBossWaves(WaveBossSupplier waveBossSupplier) {
        this.bossWaves = waveBossSupplier;
    }

    public final void resetNextWavesCache() {
        this.S.gameState.checkGameplayUpdateAllowed();
        Arrays.fill(this.nextWavesCache, (Object) null);
    }

    public final int getForceWaveBonus() {
        return this.i ? this.j << 1 : this.j;
    }

    public final int getCompletedWavesCount() {
        return this.g;
    }

    public final boolean isForceWaveAvailable() {
        return this.h;
    }

    public final void forceNextWaveAction() {
        if (isForceWaveAvailable()) {
            this.S.gameState.pushActionNextUpdate(new CallWaveAction());
        }
    }

    public final void freezeTimeToNextWave(float f) {
        if (f < 0.0f) {
            this.f = 0.0f;
        } else {
            this.f += f;
        }
    }

    public final void setStatus(Status status) {
        this.S.gameState.checkGameplayUpdateAllowed();
        Status status2 = this.status;
        this.status = status;
        if (status == Status.SPAWNED) {
            this.e = 0.0f;
        }
        b();
        this.S.events.trigger(new WaveStatusChange(status2));
    }

    public final void setDifficultyExpectedPlaytime(float f) {
        this.m = f;
        this.n = null;
    }

    @Deprecated
    public static float getDifficultWavesMultiplierOld(int i, int[] iArr) {
        float f = 1.0f;
        for (int i2 = 1; i2 <= i; i2++) {
            int i3 = 3;
            while (true) {
                if (i3 < 0) {
                    break;
                }
                if (iArr[i3] == 0 || i2 <= iArr[i3]) {
                    i3--;
                } else if (i3 == 0) {
                    f = (float) (f + 0.01d);
                } else if (i3 == 1) {
                    f = (float) (f + 0.02d);
                } else if (i3 == 2) {
                    f = (float) (f + 0.03d + (StrictMath.pow(i2 - iArr[2], 1.15d) * 0.004d));
                } else {
                    f = (float) (f + 0.04d + (StrictMath.pow(i2 - iArr[2], 1.15d) * 0.005d));
                }
            }
        }
        float f2 = 0.04f;
        int i4 = 3;
        while (true) {
            if (i4 < 0) {
                break;
            }
            if (iArr[i4] == 0 || i <= iArr[i4]) {
                i4--;
            } else if (i4 == 0) {
                f2 = 0.04f;
            } else if (i4 == 1) {
                f2 = 0.035f;
            } else if (i4 == 2) {
                f2 = 0.025f;
            } else {
                f2 = 0.015f;
            }
        }
        float sin = PMath.sin(i + 90) * f2;
        if (sin < 0.0f) {
            float f3 = f;
            f = f3 + (f3 * sin);
        }
        return f;
    }

    public final WaveDifficultyProvider getWaveDifficultyProvider() {
        if (this.n == null) {
            this.n = new WaveDifficultyProvider((int) this.S.gameState.getSeed(), this.S.gameState.averageDifficulty, this.m);
        }
        return this.n;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @IgnoreMethodOverloadLuaDefWarning
    private WaveCache a(int i) {
        this.S.syncLog("WaveSystem generateWave D", Integer.valueOf(i));
        this.S.gameState.checkGameplayUpdateAllowed();
        Wave wave = null;
        int difficultWavesMultiplier = getWaveDifficultyProvider().getDifficultWavesMultiplier(i);
        if (this.mode == Mode.ENDLESS) {
            if (this.bossWaves != null && this.bossWaves.getWaveBoss(i) != null) {
                wave = generateBossWaveWithProcessor(this.bossWaves.getWaveBoss(i), i, difficultWavesMultiplier);
            } else if (this.f3074b != null) {
                wave = generateWave(this.f3074b, i, difficultWavesMultiplier);
            } else {
                this.q.clear();
                Array tilesByType = this.S.map.getMap().getTilesByType(SpawnTile.class);
                for (int i2 = 0; i2 < this.S.map.getMap().getAllowedEnemies().size; i2++) {
                    EnemyType enemyType = this.S.map.getMap().getAllowedEnemies().items[i2];
                    boolean z = false;
                    int i3 = 0;
                    while (true) {
                        if (i3 >= tilesByType.size) {
                            break;
                        }
                        if (!((SpawnTile[]) tilesByType.items)[i3].isEnemyAllowedOnWave(enemyType, i)) {
                            i3++;
                        } else {
                            z = true;
                            break;
                        }
                    }
                    if (z) {
                        this.q.add(enemyType);
                    }
                }
                if (this.q.size != 0) {
                    wave = generateWave(i, difficultWavesMultiplier, this.S.gameState.getSeed(), this.q);
                }
                this.q.clear();
            }
        } else if (this.bossWaves != null && this.bossWaves.getWaveBoss(i) != null) {
            wave = generateBossWaveWithProcessor(this.bossWaves.getWaveBoss(i), i, this.S.gameState.averageDifficulty);
        } else {
            int i4 = i - 1;
            if (i4 < this.predefinedWaveTemplates.length) {
                wave = generateWave(i, this.S.gameState.averageDifficulty, this.predefinedWaveTemplates[i4]);
            }
        }
        WaveCache waveCache = new WaveCache();
        waveCache.waveNumber = i;
        if (this.o == null) {
            waveCache.wave = wave;
        } else {
            waveCache.wave = this.o.generate(i, wave, this.S, difficultWavesMultiplier);
        }
        return waveCache;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Null
    public final Array<EnemyGroup> generateEnemyGroups(int i, Mode mode, WaveDifficultyProvider waveDifficultyProvider, int i2, WaveBossSupplier waveBossSupplier, Map map, long j, WaveTemplates.PredefinedWaveTemplate[] predefinedWaveTemplateArr) {
        if (mode == Mode.ENDLESS) {
            int difficultWavesMultiplier = waveDifficultyProvider.getDifficultWavesMultiplier(i);
            Array tilesByType = this.S.map.getMap().getTilesByType(SpawnTile.class);
            if (waveBossSupplier != null && waveBossSupplier.getWaveBoss(i) != null) {
                boolean z = false;
                int i3 = 0;
                while (true) {
                    if (i3 >= tilesByType.size) {
                        break;
                    }
                    if (!((SpawnTile) tilesByType.get(i3)).isEnemyAllowedOnWave(EnemyType.BOSS, i)) {
                        i3++;
                    } else {
                        z = true;
                        break;
                    }
                }
                if (!z) {
                    return null;
                }
                return createBossWaveProcessor(waveBossSupplier.getWaveBoss(i)).generateEnemyGroups(i, difficultWavesMultiplier);
            }
            ObjectSet<EnemyType> objectSet = new ObjectSet<>();
            for (int i4 = 0; i4 < map.getAllowedEnemies().size; i4++) {
                EnemyType enemyType = map.getAllowedEnemies().items[i4];
                boolean z2 = false;
                int i5 = 0;
                while (true) {
                    if (i5 >= tilesByType.size) {
                        break;
                    }
                    if (!((SpawnTile[]) tilesByType.items)[i5].isEnemyAllowedOnWave(enemyType, i)) {
                        i5++;
                    } else {
                        z2 = true;
                        break;
                    }
                }
                if (z2) {
                    objectSet.add(enemyType);
                }
            }
            Wave generateWave = generateWave(i, difficultWavesMultiplier, j, objectSet);
            if (generateWave == null) {
                return null;
            }
            return generateWave.enemyGroups;
        }
        if (waveBossSupplier != null && waveBossSupplier.getWaveBoss(i) != null) {
            return createBossWaveProcessor(waveBossSupplier.getWaveBoss(i)).generateEnemyGroups(i, i2);
        }
        int i6 = i - 1;
        if (i6 < predefinedWaveTemplateArr.length) {
            return generateWave(i, i2, predefinedWaveTemplateArr[i6]).enemyGroups;
        }
        return null;
    }

    private float a(int i, long j) {
        if (i <= 0) {
            throw new IllegalArgumentException("Wave number can't be < 1");
        }
        this.S.syncLog("WaveSystem generateSeedMultiplier", Integer.valueOf(i), Long.valueOf(j));
        if (this.v == j && i == this.w) {
            this.w++;
            float nextFloat = this.u.nextFloat();
            this.S.syncLog("WaveSystem generateSeedMultiplier result instant ", Float.valueOf(nextFloat));
            return nextFloat;
        }
        this.u.setSeed(j);
        for (int i2 = 0; i2 < i; i2++) {
            this.u.nextFloat();
        }
        this.v = j;
        this.w = i + 1;
        float nextFloat2 = this.u.nextFloat();
        this.S.syncLog("WaveSystem generateSeedMultiplier result", Float.valueOf(nextFloat2));
        return nextFloat2;
    }

    @IgnoreMethodOverloadLuaDefWarning
    public final Wave generateWave(int i, int i2, WaveTemplates.PredefinedWaveTemplate predefinedWaveTemplate) {
        this.S.syncLog("WaveSystem generateWave A", Integer.valueOf(i), Integer.valueOf(i2), predefinedWaveTemplate);
        Array array = new Array();
        for (EnemyGroup enemyGroup : predefinedWaveTemplate.enemyGroups) {
            array.add(enemyGroup.cpy());
        }
        Wave wave = new Wave(i, i2, array);
        wave.waveMessage = predefinedWaveTemplate.waveMessage;
        return wave;
    }

    public static float getWaveValue(int i, int i2) {
        return i * i2 * 0.01f;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @IgnoreMethodOverloadLuaDefWarning
    @Null
    public final Wave generateWave(int i, int i2, long j, ObjectSet<EnemyType> objectSet) {
        this.S.syncLog("WaveSystem generateWave B", Integer.valueOf(i), Integer.valueOf(i2), Long.valueOf(j), objectSet);
        float waveValue = getWaveValue(i, i2);
        float a2 = a(i, j);
        int i3 = 0;
        IntArray intArray = new IntArray(WaveTemplates.WAVE_TEMPLATES.length);
        Array array = new Array();
        for (WaveTemplates.WaveTemplate waveTemplate : WaveTemplates.WAVE_TEMPLATES) {
            boolean z = true;
            WaveTemplates.EnemyGroupConfig[] enemyGroupConfigs = waveTemplate.getEnemyGroupConfigs();
            int length = enemyGroupConfigs.length;
            int i4 = 0;
            while (true) {
                if (i4 >= length) {
                    break;
                }
                if (objectSet.contains(enemyGroupConfigs[i4].getEnemyType())) {
                    i4++;
                } else {
                    z = false;
                    break;
                }
            }
            if (z) {
                int probability = waveTemplate.getProbability(i, i2, waveValue);
                intArray.add(probability);
                i3 += probability;
                if (probability >= 100) {
                    array.add(waveTemplate);
                }
            } else {
                intArray.add(0);
            }
        }
        WaveTemplates.WaveTemplate waveTemplate2 = null;
        int i5 = -1;
        int i6 = (int) (a2 * i3);
        if (array.size != 0) {
            int i7 = 0;
            Array.ArrayIterator it = array.iterator();
            while (it.hasNext()) {
                WaveTemplates.WaveTemplate waveTemplate3 = (WaveTemplates.WaveTemplate) it.next();
                if (waveTemplate2 == null) {
                    waveTemplate2 = waveTemplate3;
                    i5 = i7;
                } else if (intArray.items[i7] > intArray.items[i5]) {
                    waveTemplate2 = waveTemplate3;
                    i5 = i7;
                }
                i7++;
            }
        } else {
            int i8 = i6;
            int i9 = 0;
            WaveTemplates.WaveTemplate[] waveTemplateArr = WaveTemplates.WAVE_TEMPLATES;
            int length2 = waveTemplateArr.length;
            int i10 = 0;
            while (true) {
                if (i10 >= length2) {
                    break;
                }
                WaveTemplates.WaveTemplate waveTemplate4 = waveTemplateArr[i10];
                int i11 = i8 - intArray.items[i9];
                i8 = i11;
                if (i11 < 0) {
                    waveTemplate2 = waveTemplate4;
                    break;
                }
                i9++;
                i10++;
            }
        }
        if (waveTemplate2 == null) {
            return null;
        }
        return generateWave(waveTemplate2, i, i2);
    }

    @IgnoreMethodOverloadLuaDefWarning
    public final Wave generateWave(WaveTemplates.WaveTemplate waveTemplate, int i, int i2) {
        this.S.syncLog("WaveSystem generateWave C", waveTemplate, Integer.valueOf(i), Integer.valueOf(i2));
        float waveValue = getWaveValue(i, i2);
        Array array = new Array();
        for (WaveTemplates.EnemyGroupConfig enemyGroupConfig : waveTemplate.getEnemyGroupConfigs()) {
            array.add(new EnemyGroup(enemyGroupConfig.getEnemyType(), enemyGroupConfig.getSpeed(i, i2, waveValue), enemyGroupConfig.getHealth(i, i2, waveValue), enemyGroupConfig.getCount(i, i2, waveValue), enemyGroupConfig.getDelay(i, i2, waveValue), enemyGroupConfig.getInterval(i, i2, waveValue), enemyGroupConfig.getBounty(i, i2, waveValue), enemyGroupConfig.getKillExp(i, i2, waveValue), enemyGroupConfig.getKillScore(i, i2, waveValue)));
        }
        Wave wave = new Wave(i, i2, array);
        wave.waveMessage = waveTemplate.getWaveMessage();
        return wave;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final WavesTimelineOverlay.WavesConfiguration generateWavesTimelineConfigurationBasicLevel(BasicLevel basicLevel, Map map, int i) {
        if (i <= 0) {
            i = 1;
        }
        if (i > 10000) {
            i = 10000;
        }
        WavesTimelineOverlay.WavesConfiguration wavesConfiguration = new WavesTimelineOverlay.WavesConfiguration();
        wavesConfiguration.startWave = i;
        Array tilesByType = map.getTilesByType(SpawnTile.class);
        for (int i2 = 0; i2 < tilesByType.size; i2++) {
            Array<SpawnTile.AllowedEnemyConfig> allowedEnemies = ((SpawnTile[]) tilesByType.items)[i2].getAllowedEnemies();
            for (int i3 = 0; i3 < allowedEnemies.size; i3++) {
                SpawnTile.AllowedEnemyConfig allowedEnemyConfig = allowedEnemies.items[i3];
                boolean z = false;
                int i4 = 0;
                while (true) {
                    if (i4 >= wavesConfiguration.enemyConfigs.size) {
                        break;
                    }
                    SpawnTile.AllowedEnemyConfig allowedEnemyConfig2 = wavesConfiguration.enemyConfigs.items[i4];
                    if (allowedEnemyConfig2.enemyType != allowedEnemyConfig.enemyType) {
                        i4++;
                    } else {
                        z = true;
                        if (allowedEnemyConfig.firstWave < allowedEnemyConfig2.firstWave) {
                            allowedEnemyConfig2.firstWave = allowedEnemyConfig.firstWave;
                        }
                        if (allowedEnemyConfig2.lastWave > 0 && (allowedEnemyConfig.lastWave > allowedEnemyConfig2.lastWave || allowedEnemyConfig.lastWave <= 0)) {
                            allowedEnemyConfig2.lastWave = allowedEnemyConfig.lastWave;
                        }
                    }
                }
                if (!z) {
                    wavesConfiguration.enemyConfigs.add(new SpawnTile.AllowedEnemyConfig(allowedEnemyConfig.enemyType, allowedEnemyConfig.firstWave, allowedEnemyConfig.lastWave));
                }
            }
        }
        Mode mode = basicLevel.enemyWaves == null ? Mode.ENDLESS : Mode.PREDEFINED;
        int averageDifficulty = map.getAverageDifficulty();
        wavesConfiguration.enemyGroupsByWave.add(new Array<>());
        WaveDifficultyProvider waveDifficultyProvider = new WaveDifficultyProvider(basicLevel.seed, averageDifficulty, basicLevel.getDifficultyExpectedPlaytime());
        for (int i5 = i; i5 < i + 100; i5++) {
            Array<EnemyGroup> generateEnemyGroups = generateEnemyGroups(i5, mode, waveDifficultyProvider, averageDifficulty, map.getBossWaves(), map, basicLevel.seed, basicLevel.enemyWaves);
            if (generateEnemyGroups != null) {
                wavesConfiguration.enemyGroupsByWave.add(generateEnemyGroups);
            }
        }
        return wavesConfiguration;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final WavesTimelineOverlay.WavesConfiguration generateWavesTimelineConfigurationUserMap(UserMap userMap, Map map, int i, WaveBossSupplier waveBossSupplier) {
        if (i <= 0) {
            i = 1;
        }
        if (i > 10000) {
            i = 10000;
        }
        WavesTimelineOverlay.WavesConfiguration wavesConfiguration = new WavesTimelineOverlay.WavesConfiguration();
        wavesConfiguration.startWave = i;
        Array tilesByType = map.getTilesByType(SpawnTile.class);
        for (int i2 = 0; i2 < tilesByType.size; i2++) {
            Array<SpawnTile.AllowedEnemyConfig> allowedEnemies = ((SpawnTile[]) tilesByType.items)[i2].getAllowedEnemies();
            for (int i3 = 0; i3 < allowedEnemies.size; i3++) {
                SpawnTile.AllowedEnemyConfig allowedEnemyConfig = allowedEnemies.items[i3];
                boolean z = false;
                int i4 = 0;
                while (true) {
                    if (i4 >= wavesConfiguration.enemyConfigs.size) {
                        break;
                    }
                    SpawnTile.AllowedEnemyConfig allowedEnemyConfig2 = wavesConfiguration.enemyConfigs.items[i4];
                    if (allowedEnemyConfig2.enemyType != allowedEnemyConfig.enemyType) {
                        i4++;
                    } else {
                        z = true;
                        if (allowedEnemyConfig.firstWave < allowedEnemyConfig2.firstWave) {
                            allowedEnemyConfig2.firstWave = allowedEnemyConfig.firstWave;
                        }
                        if (allowedEnemyConfig2.lastWave > 0 && (allowedEnemyConfig.lastWave > allowedEnemyConfig2.lastWave || allowedEnemyConfig.lastWave <= 0)) {
                            allowedEnemyConfig2.lastWave = allowedEnemyConfig.lastWave;
                        }
                    }
                }
                if (!z) {
                    wavesConfiguration.enemyConfigs.add(new SpawnTile.AllowedEnemyConfig(allowedEnemyConfig.enemyType, allowedEnemyConfig.firstWave, allowedEnemyConfig.lastWave));
                }
            }
        }
        Mode mode = Mode.ENDLESS;
        int averageDifficulty = map.getAverageDifficulty();
        wavesConfiguration.enemyGroupsByWave.add(new Array<>());
        int generateSeed = map.generateSeed();
        WaveDifficultyProvider waveDifficultyProvider = new WaveDifficultyProvider(generateSeed, averageDifficulty, map.getDifficultyExpectedPlaytime());
        for (int i5 = i; i5 < i + 100; i5++) {
            Array<EnemyGroup> generateEnemyGroups = generateEnemyGroups(i5, mode, waveDifficultyProvider, averageDifficulty, waveBossSupplier, map, generateSeed, null);
            if (generateEnemyGroups != null) {
                wavesConfiguration.enemyGroupsByWave.add(generateEnemyGroups);
            }
        }
        return wavesConfiguration;
    }

    private void b() {
        int i;
        this.S.syncLog("WaveSystem updateNextWavesCache", new Object[0]);
        this.S.gameState.checkGameplayUpdateAllowed();
        boolean z = false;
        for (int i2 = 0; i2 < 10; i2++) {
            WaveCache waveCache = this.nextWavesCache[i2];
            if (this.wave != null) {
                i = this.wave.waveNumber + i2 + 1;
            } else {
                i = i2 + 1;
            }
            WaveCache waveCache2 = this.p.get(i);
            if (waveCache2 != null) {
                if (this.nextWavesCache[i2] != waveCache2) {
                    this.nextWavesCache[i2] = waveCache2;
                    z = true;
                }
            } else if (this.mode == Mode.PREDEFINED && i > this.predefinedWaveTemplates.length) {
                if (this.nextWavesCache[i2] == null || this.nextWavesCache[i2].waveNumber != i || this.nextWavesCache[i2].wave != null) {
                    this.nextWavesCache[i2] = new WaveCache();
                    this.nextWavesCache[i2].waveNumber = i;
                    z = true;
                }
            } else if (waveCache == null || waveCache.waveNumber != i) {
                if (i2 != 9) {
                    WaveCache waveCache3 = null;
                    int i3 = i2 + 1;
                    while (true) {
                        if (i3 >= this.nextWavesCache.length) {
                            break;
                        }
                        WaveCache waveCache4 = this.nextWavesCache[i3];
                        if (waveCache4 == null || waveCache4.waveNumber != i) {
                            i3++;
                        } else {
                            waveCache3 = waveCache4;
                            break;
                        }
                    }
                    if (waveCache3 != null) {
                        this.nextWavesCache[i2] = waveCache3;
                    } else {
                        this.nextWavesCache[i2] = a(i);
                    }
                } else {
                    this.nextWavesCache[i2] = a(i);
                }
                z = true;
            }
        }
        if (z) {
            c();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void c() {
        Wave wave;
        if (this.S.map.getMap() != null) {
            int i = 0;
            if (this.wave != null) {
                i = this.wave.waveNumber;
            }
            Array tilesByType = this.S.map.getMap().getTilesByType(SpawnTile.class);
            for (int i2 = 0; i2 <= 10; i2++) {
                Array.ArrayIterator it = tilesByType.iterator();
                while (it.hasNext()) {
                    ((SpawnTile) it.next()).enemySpawnQueues.get(i2).clear();
                }
                if (i + i2 != 0) {
                    if (i2 == 0) {
                        wave = this.wave;
                    } else {
                        WaveCache waveCache = this.nextWavesCache[i2 - 1];
                        wave = waveCache == null ? null : waveCache.wave;
                    }
                    Wave wave2 = wave;
                    if (wave != null) {
                        Array.ArrayIterator<EnemyGroup> it2 = wave2.enemyGroups.iterator();
                        while (it2.hasNext()) {
                            EnemyGroup next = it2.next();
                            EnemyType mainEnemyType = EnemyType.getMainEnemyType(next.getEnemyType());
                            this.r.clear();
                            if (wave2.enemiesCanBeSplitBetweenSpawns) {
                                Array.ArrayIterator it3 = tilesByType.iterator();
                                while (it3.hasNext()) {
                                    SpawnTile spawnTile = (SpawnTile) it3.next();
                                    if (spawnTile.isEnemyAllowedOnWave(mainEnemyType, wave2.waveNumber)) {
                                        this.r.add(spawnTile);
                                    }
                                }
                            } else {
                                Array.ArrayIterator it4 = tilesByType.iterator();
                                while (true) {
                                    if (!it4.hasNext()) {
                                        break;
                                    }
                                    SpawnTile spawnTile2 = (SpawnTile) it4.next();
                                    if (spawnTile2.isEnemyAllowedOnWave(mainEnemyType, wave2.waveNumber)) {
                                        this.r.add(spawnTile2);
                                        break;
                                    }
                                }
                            }
                            if (this.r.size == 0) {
                                f3073a.w("nowhere to spawn enemy group of type %s, wave %s", next.getEnemyType().name(), Integer.valueOf(wave2.waveNumber));
                            } else {
                                int i3 = next.count / this.r.size;
                                int i4 = i3;
                                if (i3 <= 0) {
                                    i4 = 1;
                                }
                                int i5 = 0;
                                for (int i6 = 0; i6 < this.r.size; i6++) {
                                    if (i6 == this.r.size - 1) {
                                        i4 += next.count % this.r.size;
                                    }
                                    if (i4 != 0) {
                                        SpawnTile spawnTile3 = this.r.items[i6];
                                        EnemyGroup.SpawnEnemyGroup createSpawnPortion = next.createSpawnPortion(i4);
                                        createSpawnPortion.health *= spawnTile3.difficulty * 0.01f;
                                        spawnTile3.enemySpawnQueues.get(i2).add(createSpawnPortion);
                                        int i7 = i5 + i4;
                                        i5 = i7;
                                        if (i7 >= next.count) {
                                            break;
                                        }
                                    }
                                }
                                if (i5 != next.count) {
                                    f3073a.e("placed " + i5 + "/" + next.count + " enemies to spawns", new Object[0]);
                                }
                            }
                        }
                    }
                }
            }
            this.r.clear();
        }
    }

    private Wave d() {
        if (this.status == null) {
            return null;
        }
        b();
        return this.nextWavesCache[0].wave;
    }

    public final boolean allWavesSpawned() {
        return this.status == Status.ENDED;
    }

    public final void startNextWave() {
        this.S.gameState.checkGameplayUpdateAllowed();
        Wave d = d();
        if (d != null) {
            this.wavesToNotifyAboutCompletion.add(d);
            this.wave = d;
            this.wave.started = true;
            this.d = 0.0f;
            setStatus(Status.SPAWNING);
            if (this.wave.waveProcessor != null) {
                this.c.add(this.wave.waveProcessor);
            }
            this.S.events.getListeners(WaveStart.class).trigger(new WaveStart(this.wave));
            return;
        }
        throw new IllegalStateException("There's no next wave, current status is " + this.status.name());
    }

    public final float getTimeToNextWave() {
        return this.l;
    }

    public static Array<Enemy> getEnemiesToSpawn(float f, Array<EnemyGroup.SpawnEnemyGroup> array) {
        Array<Enemy> array2 = new Array<>(true, 16, Enemy.class);
        int i = array.size;
        for (int i2 = 0; i2 < i; i2++) {
            EnemyGroup.SpawnEnemyGroup spawnEnemyGroup = array.items[i2];
            int spawnCountByTime = spawnEnemyGroup.getSpawnCountByTime(f);
            if (spawnCountByTime > spawnEnemyGroup.getSpawnedCount()) {
                for (int i3 = 0; i3 < spawnCountByTime - spawnEnemyGroup.getSpawnedCount(); i3++) {
                    Enemy obtain = Game.i.enemyManager.getFactory(spawnEnemyGroup.getEnemyType()).obtain();
                    obtain.setMaxHealth(spawnEnemyGroup.health);
                    obtain.bounty = spawnEnemyGroup.bounty;
                    obtain.setKillExp(spawnEnemyGroup.killExp);
                    obtain.killScore = spawnEnemyGroup.killScore;
                    obtain.setSpeed(spawnEnemyGroup.speed);
                    obtain.setHealth(spawnEnemyGroup.health);
                    if (EnemyType.isBoss(obtain.type)) {
                        obtain.ignorePathfinding = true;
                    }
                    array2.add(obtain);
                }
                spawnEnemyGroup.addSpawnedCount(spawnCountByTime - spawnEnemyGroup.getSpawnedCount());
            }
        }
        return array2;
    }

    public final void stopSpawningCurrentWave(Wave wave, Tower tower, DamageType damageType) {
        this.S.gameState.checkGameplayUpdateAllowed();
        if (this.status == Status.SPAWNING && wave == this.wave) {
            Array tilesByType = this.S.map.getMap().getTilesByType(SpawnTile.class);
            for (int i = 0; i < tilesByType.size; i++) {
                SpawnTile spawnTile = (SpawnTile) tilesByType.get(i);
                for (int i2 = 0; i2 < spawnTile.enemySpawnQueues.get(0).size; i2++) {
                    Array.ArrayIterator<Enemy> it = getEnemiesToSpawn(9001.0f, spawnTile.enemySpawnQueues.get(0)).iterator();
                    while (it.hasNext()) {
                        Enemy next = it.next();
                        next.spawnTile = spawnTile;
                        next.wave = this.wave;
                        if (!this.wave.enemiesCanHaveRandomSideShifts || !next.canHaveRandomSideShift()) {
                            this.S.enemy.registerWithSideShift(next, 5);
                        } else {
                            this.S.enemy.registerWithRandomSideShift(next);
                        }
                        this.S.map.spawnEnemy(next);
                        next.graphPath.getPosition(next.passedTiles, next.sideShiftIndex, next.getPosition());
                        this.S.damage.queueEnemyKill(this.S.damage.obtainRecord().setup(next, 1.0f, damageType).setTower(tower));
                    }
                }
            }
        }
    }

    public final void setForceWaveDoubleBonus(boolean z) {
        this.i = z;
    }

    public final boolean isForceWaveDoubleBonus() {
        return this.i;
    }

    @Null
    public final Wave getWave(int i) {
        if (this.wave != null && this.wave.waveNumber == i) {
            return this.wave;
        }
        for (WaveCache waveCache : this.nextWavesCache) {
            if (waveCache.wave != null && waveCache.waveNumber == i) {
                return waveCache.wave;
            }
        }
        for (int i2 = 0; i2 < this.wavesToNotifyAboutCompletion.size; i2++) {
            Wave wave = this.wavesToNotifyAboutCompletion.get(i2);
            if (wave.waveNumber == i) {
                return wave;
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:133:0x052a  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x05cb  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x060b  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0606 A[EDGE_INSN: B:165:0x0606->B:158:0x0606 BREAK  A[LOOP:9: B:148:0x05b9->B:162:0x0600], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0617  */
    /* JADX WARN: Removed duplicated region for block: B:178:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:180:0x058f  */
    @Override // com.prineside.tdi2.GameSystem
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void update(float r8) {
        /*
            Method dump skipped, instructions count: 1603
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.systems.WaveSystem.update(float):void");
    }

    @Override // com.prineside.tdi2.GameSystem
    public final String getSystemName() {
        return "Wave";
    }

    public final boolean isAutoForceWaveEnabled() {
        return this.autoForceWaveEnabled;
    }

    public final void setAutoForceWaveEnabled(boolean z) {
        if (this.S.gameValue.getBooleanValue(GameValueType.AUTO_WAVE_CALL) && this.autoForceWaveEnabled != z) {
            this.autoForceWaveEnabled = z;
            if (z && this.h) {
                forceNextWaveAction();
            }
        }
    }

    @Override // com.prineside.tdi2.GameSystem, com.badlogic.gdx.utils.Disposable
    public final void dispose() {
        this.f3074b = null;
        this.wave = null;
        this.c.clear();
        Arrays.fill(this.nextWavesCache, (Object) null);
        this.predefinedWaveTemplates = null;
        super.dispose();
    }

    @REGS(arrayLevels = 1)
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/WaveSystem$WaveCache.class */
    public static class WaveCache implements KryoSerializable {
        public Wave wave;
        public int waveNumber;

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void write(Kryo kryo, Output output) {
            kryo.writeClassAndObject(output, this.wave);
            output.writeVarInt(this.waveNumber, true);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void read(Kryo kryo, Input input) {
            this.wave = (Wave) kryo.readClassAndObject(input);
            this.waveNumber = input.readVarInt(true);
        }

        public WaveCache() {
        }

        public WaveCache(Wave wave, int i) {
            this.wave = wave;
            this.waveNumber = i;
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/WaveSystem$OnEnemyDie.class */
    public static final class OnEnemyDie extends SerializableListener<WaveSystem> implements Listener<EnemyDie> {
        private OnEnemyDie() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public OnEnemyDie(WaveSystem waveSystem) {
            this.f1759a = waveSystem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(EnemyDie enemyDie) {
            Enemy enemy = enemyDie.getLastDamage().getEnemy();
            if (enemy.wave != null && !enemy.notAffectsWaveKillCounter.isTrue()) {
                enemy.wave.killedEnemiesCount++;
                enemy.wave.killedEnemiesBountySum = (int) (r0.killedEnemiesBountySum + enemy.bounty);
                if (enemy.wave.killedEnemiesCount == enemy.wave.totalEnemiesCount && enemy.wave.waveNumber > 0) {
                    ((WaveSystem) this.f1759a).S.gameState.addScore(100 + (enemy.wave.waveNumber * 10) + ((int) (enemy.wave.killedEnemiesBountySum * 0.1f)), StatisticsType.SG_WCL);
                }
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/WaveSystem$OnEnemyTakeDamage.class */
    public static final class OnEnemyTakeDamage implements Listener<EnemyTakeDamage>, NoFieldKryoSerializable {
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(EnemyTakeDamage enemyTakeDamage) {
            Enemy enemy = enemyTakeDamage.getRecord().getEnemy();
            if (enemy.wave != null) {
                enemy.wave.enemiesTookDamage += enemyTakeDamage.getRecord().getFactDamage();
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/WaveSystem$OnEnemyReachTarget.class */
    public static final class OnEnemyReachTarget implements KryoSerializable, Listener<EnemyReachTarget> {

        /* renamed from: a, reason: collision with root package name */
        private WaveSystem f3076a;

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public final void write(Kryo kryo, Output output) {
            kryo.writeObject(output, this.f3076a);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public final void read(Kryo kryo, Input input) {
            this.f3076a = (WaveSystem) kryo.readObject(input, WaveSystem.class);
        }

        private OnEnemyReachTarget() {
        }

        public OnEnemyReachTarget(WaveSystem waveSystem) {
            this.f3076a = waveSystem;
        }

        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(EnemyReachTarget enemyReachTarget) {
            if (enemyReachTarget.getEnemy().getCurrentTile() instanceof TargetTile) {
                if (enemyReachTarget.getEnemy().wave != null) {
                    enemyReachTarget.getEnemy().wave.passedEnemiesCount++;
                }
                if (this.f3076a.isAutoForceWaveEnabled() && this.f3076a.S.gameState.difficultyMode == DifficultyMode.EASY) {
                    this.f3076a.setAutoForceWaveEnabled(false);
                }
            }
        }
    }
}
