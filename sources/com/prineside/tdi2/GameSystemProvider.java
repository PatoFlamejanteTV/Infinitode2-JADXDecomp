package com.prineside.tdi2;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.Sort;
import com.badlogic.gdx.utils.StringBuilder;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.base.Strings;
import com.prineside.tdi2.events.EventDispatcher;
import com.prineside.tdi2.events.game.SystemsDispose;
import com.prineside.tdi2.events.game.SystemsPostSetup;
import com.prineside.tdi2.events.game.SystemsSetup;
import com.prineside.tdi2.events.game.SystemsStateRestore;
import com.prineside.tdi2.systems.AbilitySystem;
import com.prineside.tdi2.systems.AchievementSystem;
import com.prineside.tdi2.systems.BonusSystem;
import com.prineside.tdi2.systems.BuffSystem;
import com.prineside.tdi2.systems.CachedRenderingSystem;
import com.prineside.tdi2.systems.DamageSystem;
import com.prineside.tdi2.systems.EnemySystem;
import com.prineside.tdi2.systems.ExperienceSystem;
import com.prineside.tdi2.systems.ExplosionSystem;
import com.prineside.tdi2.systems.GameMapSelectionSystem;
import com.prineside.tdi2.systems.GameStateSystem;
import com.prineside.tdi2.systems.GameUiSystem;
import com.prineside.tdi2.systems.GameValueSystem;
import com.prineside.tdi2.systems.GameplayModSystem;
import com.prineside.tdi2.systems.HotKeySystem;
import com.prineside.tdi2.systems.InputSystem;
import com.prineside.tdi2.systems.InventorySystem;
import com.prineside.tdi2.systems.LootSystem;
import com.prineside.tdi2.systems.MapEditorSystem;
import com.prineside.tdi2.systems.MapEditorUiSystem;
import com.prineside.tdi2.systems.MapRenderingSystem;
import com.prineside.tdi2.systems.MapSystem;
import com.prineside.tdi2.systems.MinerSystem;
import com.prineside.tdi2.systems.ModifierSystem;
import com.prineside.tdi2.systems.ParticleSystem;
import com.prineside.tdi2.systems.PathRenderingSystem;
import com.prineside.tdi2.systems.PathfindingSystem;
import com.prineside.tdi2.systems.ProjectileSystem;
import com.prineside.tdi2.systems.ProjectileTrailSystem;
import com.prineside.tdi2.systems.QuestSystem;
import com.prineside.tdi2.systems.RandomEncounterSystem;
import com.prineside.tdi2.systems.RenderSystem;
import com.prineside.tdi2.systems.ScriptSystem;
import com.prineside.tdi2.systems.SoundSystem;
import com.prineside.tdi2.systems.StateSystem;
import com.prineside.tdi2.systems.StatisticsSystem;
import com.prineside.tdi2.systems.TowerSystem;
import com.prineside.tdi2.systems.UnitSystem;
import com.prineside.tdi2.systems.WaveSystem;
import com.prineside.tdi2.utils.FloatSorter;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparator;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparisonConfig;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.lwjgl.opengl.CGL;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/GameSystemProvider.class */
public final class GameSystemProvider implements KryoSerializable {

    @NAGS
    public boolean syncChecking;

    @NAGS
    public Array<String> syncCheckLog;
    public SystemsConfig CFG;
    private final Array<GameSystem> c;

    @NAGS
    private final ObjectMap<Class<?>, GameSystem> d;

    @NAGS
    private final long[] e;

    @NAGS
    public SoundSystem _sound;

    @NAGS
    public CachedRenderingSystem _cachedRendering;

    @NAGS
    public GameMapSelectionSystem _gameMapSelection;

    @NAGS
    public AchievementSystem achievement;

    @NAGS
    public InputSystem _input;

    @NAGS
    public HotKeySystem _hotKey;

    @NAGS
    public GameUiSystem _gameUi;

    @NAGS
    public MapEditorUiSystem _mapEditorUi;

    @NAGS
    public RenderSystem _render;

    @NAGS
    public MapRenderingSystem _mapRendering;

    @NAGS
    public PathRenderingSystem _pathRendering;

    @NAGS
    public ProjectileTrailSystem _projectileTrail;

    @NAGS
    public InventorySystem _inventory;

    @NAGS
    public MapEditorSystem _mapEditor;

    @NAGS
    public ParticleSystem _particle;

    @NAGS
    public BonusSystem bonus;

    @NAGS
    public GameplayModSystem gameplayMod;

    @NAGS
    public GameValueSystem gameValue;

    @NAGS
    public StateSystem state;

    @NAGS
    public GameStateSystem gameState;

    @NAGS
    public QuestSystem _quest;

    @NAGS
    public BuffSystem buff;

    @NAGS
    public LootSystem loot;

    @NAGS
    public EnemySystem enemy;

    @NAGS
    public UnitSystem unit;

    @NAGS
    public AbilitySystem ability;

    @NAGS
    public MapSystem map;

    @NAGS
    public ProjectileSystem projectile;

    @NAGS
    public ExplosionSystem explosion;

    @NAGS
    public TowerSystem tower;

    @NAGS
    public ExperienceSystem experience;

    @NAGS
    public MinerSystem miner;

    @NAGS
    public ModifierSystem modifier;

    @NAGS
    public PathfindingSystem pathfinding;

    @NAGS
    public DamageSystem damage;

    @NAGS
    public WaveSystem wave;

    @NAGS
    public StatisticsSystem statistics;

    @NAGS
    public RandomEncounterSystem randomEncounter;

    @NAGS
    public ScriptSystem script;
    public EventDispatcher events;

    @NAGS
    public final ThreadSafeSharedHelpers TSH;
    private static final Output f;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f1707a = TLog.forClass(GameSystemProvider.class);

    /* renamed from: b, reason: collision with root package name */
    private static final Class[] f1708b = {SoundSystem.class, AchievementSystem.class, InputSystem.class, HotKeySystem.class, MapSystem.class, GameUiSystem.class, MapEditorUiSystem.class, GameValueSystem.class, GameStateSystem.class, BonusSystem.class, GameplayModSystem.class, QuestSystem.class, BuffSystem.class, LootSystem.class, PathfindingSystem.class, EnemySystem.class, UnitSystem.class, AbilitySystem.class, MapRenderingSystem.class, PathRenderingSystem.class, ProjectileSystem.class, ExplosionSystem.class, TowerSystem.class, DamageSystem.class, ExperienceSystem.class, MinerSystem.class, ModifierSystem.class, ProjectileTrailSystem.class, ParticleSystem.class, WaveSystem.class, StatisticsSystem.class, RandomEncounterSystem.class, ScriptSystem.class, RenderSystem.class};
    public static DeepClassComparator<GameSystemProvider> CLASS_COMPARATOR = new DeepClassComparator<GameSystemProvider>() { // from class: com.prineside.tdi2.GameSystemProvider.1
        @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
        public Class<GameSystemProvider> forClass() {
            return GameSystemProvider.class;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
        public void compare(GameSystemProvider gameSystemProvider, GameSystemProvider gameSystemProvider2, DeepClassComparisonConfig deepClassComparisonConfig) {
            int i = 0;
            int i2 = 0;
            for (int i3 = 0; i3 < gameSystemProvider.c.size; i3++) {
                if (((GameSystem[]) gameSystemProvider.c.items)[i3].affectsGameState()) {
                    i++;
                }
            }
            for (int i4 = 0; i4 < gameSystemProvider2.c.size; i4++) {
                if (((GameSystem[]) gameSystemProvider2.c.items)[i4].affectsGameState()) {
                    i2++;
                }
            }
            if (i != i2) {
                deepClassComparisonConfig.appendPrefix().append(": system count differ (").append(PMath.toString(i)).append(", ").append(String.valueOf(i2)).append(")\n");
            }
            for (int i5 = 0; i5 < gameSystemProvider.c.size; i5++) {
                GameSystem gameSystem = ((GameSystem[]) gameSystemProvider.c.items)[i5];
                if (gameSystem.affectsGameState()) {
                    GameSystem a2 = gameSystemProvider2.a((Class<GameSystem>) gameSystem.getClass());
                    deepClassComparisonConfig.addPrefix("[", PMath.toString(i5), SequenceUtils.SPACE, a2.getClass().getName(), "]");
                    SyncChecker.compareObjects(gameSystem, a2, deepClassComparisonConfig);
                    deepClassComparisonConfig.popPrefix(5);
                }
            }
            deepClassComparisonConfig.addPrefix(".E");
            SyncChecker.compareObjects(gameSystemProvider.events, gameSystemProvider2.events, deepClassComparisonConfig);
            deepClassComparisonConfig.popPrefix(1);
        }
    };

    static {
        SyncChecker.CLASS_COMPARATORS.add(CLASS_COMPARATOR);
        f = new Output(65536, -1);
    }

    public final void syncLog(String str, Object... objArr) {
        if (this.syncChecking) {
            this.syncCheckLog.add(Strings.lenientFormat(str, objArr));
        }
    }

    public final void syncLogTrace() {
        if (this.syncChecking) {
            StringWriter stringWriter = new StringWriter();
            new Throwable().printStackTrace(new PrintWriter(stringWriter));
            this.syncCheckLog.add(stringWriter.toString());
        }
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        kryo.writeObject(output, this.CFG);
        int i = 0;
        for (int i2 = 0; i2 < this.c.size; i2++) {
            if (this.c.items[i2].affectsGameState()) {
                i++;
            }
        }
        output.writeVarInt(i, true);
        for (int i3 = 0; i3 < this.c.size; i3++) {
            if (this.c.items[i3].affectsGameState()) {
                kryo.writeClassAndObject(output, this.c.items[i3]);
            }
        }
        kryo.writeObject(output, this.events);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        this.CFG = (SystemsConfig) kryo.readObject(input, SystemsConfig.class);
        int readVarInt = input.readVarInt(true);
        for (int i = 0; i < readVarInt; i++) {
            addSystem((GameSystem) kryo.readClassAndObject(input));
        }
        this.events = (EventDispatcher) kryo.readObject(input, EventDispatcher.class);
    }

    private GameSystemProvider() {
        this.syncChecking = false;
        this.syncCheckLog = new Array<>(true, 1, String.class);
        this.c = new Array<>(true, 1, GameSystem.class);
        this.d = new ObjectMap<>();
        this.e = new long[64];
        this.events = new EventDispatcher();
        this.TSH = new ThreadSafeSharedHelpers();
    }

    public GameSystemProvider(SystemsConfig systemsConfig) {
        this.syncChecking = false;
        this.syncCheckLog = new Array<>(true, 1, String.class);
        this.c = new Array<>(true, 1, GameSystem.class);
        this.d = new ObjectMap<>();
        this.e = new long[64];
        this.events = new EventDispatcher();
        this.TSH = new ThreadSafeSharedHelpers();
        this.CFG = systemsConfig;
    }

    private void b() {
        this.TSH.sort.sort(this.c, (gameSystem, gameSystem2) -> {
            int i = 0;
            int i2 = 0;
            for (int i3 = 0; i3 < f1708b.length; i3++) {
                if (f1708b[i3] == gameSystem.getClass()) {
                    i = i3;
                } else if (f1708b[i3] == gameSystem2.getClass()) {
                    i2 = i3;
                }
            }
            return Integer.compare(i, i2);
        });
    }

    public final void createSystems() {
        if (this.CFG.setup == SystemsConfig.Setup.GAME) {
            if (!this.CFG.headless) {
                addSystem(new SoundSystem());
            }
            addSystem(new AchievementSystem());
            if (!this.CFG.headless) {
                addSystem(new InputSystem());
            }
            if (!this.CFG.headless) {
                addSystem(new HotKeySystem());
            }
            if (!this.CFG.headless) {
                addSystem(new GameUiSystem());
            }
            if (!this.CFG.headless) {
                addSystem(new RenderSystem());
            }
            addSystem(new GameValueSystem());
            addSystem(new ExperienceSystem());
            addSystem(new GameStateSystem());
            addSystem(new BonusSystem());
            addSystem(new GameplayModSystem());
            addSystem(new DamageSystem());
            if (!this.CFG.headless) {
                addSystem(new QuestSystem());
            }
            addSystem(new BuffSystem());
            addSystem(new LootSystem());
            addSystem(new EnemySystem());
            addSystem(new UnitSystem());
            addSystem(new AbilitySystem());
            addSystem(new MapSystem());
            if (!this.CFG.headless) {
                addSystem(new MapRenderingSystem());
            }
            if (!this.CFG.headless) {
                addSystem(new PathRenderingSystem());
            }
            addSystem(new ProjectileSystem());
            addSystem(new ExplosionSystem());
            addSystem(new TowerSystem());
            addSystem(new MinerSystem());
            addSystem(new ModifierSystem());
            addSystem(new PathfindingSystem());
            if (!this.CFG.headless) {
                addSystem(new ProjectileTrailSystem());
            }
            if (!this.CFG.headless) {
                addSystem(new ParticleSystem());
            }
            if (!this.CFG.headless) {
                addSystem(new GameMapSelectionSystem());
            }
            addSystem(new WaveSystem());
            addSystem(new StatisticsSystem());
            addSystem(new RandomEncounterSystem());
            if (!this.CFG.noScripts) {
                addSystem(new ScriptSystem());
            }
            addSystem(new CachedRenderingSystem());
        } else if (this.CFG.setup == SystemsConfig.Setup.MAP_EDITOR) {
            addSystem(new StateSystem());
            addSystem(new InventorySystem());
            addSystem(new MapEditorSystem());
            addSystem(new MapSystem());
            addSystem(new PathfindingSystem());
            addSystem(new MapRenderingSystem());
            addSystem(new PathRenderingSystem());
            addSystem(new ParticleSystem());
            addSystem(new RenderSystem());
            addSystem(new InputSystem());
            addSystem(new GameValueSystem());
            addSystem(new MapEditorUiSystem());
            addSystem(new CachedRenderingSystem());
            if (!this.CFG.noScripts) {
                addSystem(new ScriptSystem());
            }
        }
        b();
        int i = this.c.size;
        for (int i2 = 0; i2 < i; i2++) {
            this.c.items[i2].setRegistered(this);
        }
    }

    public final synchronized GameSystemProvider deepCopy() {
        f.setPosition(0);
        serialize(f);
        return unserialize(new Input(f.getBuffer(), 0, f.position()));
    }

    public final void serialize(Output output) {
        Game.i.networkManager.getFullKryo().writeObject(output, this);
    }

    public static GameSystemProvider unserialize(Input input) {
        return (GameSystemProvider) Game.i.networkManager.getFullKryo().readObject(input, GameSystemProvider.class);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void createAndSetupNonStateAffectingSystemsAfterDeserialization() {
        Array array = new Array(GameSystem.class);
        array.add(new SoundSystem());
        array.add(new InputSystem());
        array.add(new HotKeySystem());
        array.add(new GameUiSystem());
        array.add(new RenderSystem());
        array.add(new MapRenderingSystem());
        array.add(new PathRenderingSystem());
        array.add(new ProjectileTrailSystem());
        array.add(new ParticleSystem());
        array.add(new QuestSystem());
        array.add(new CachedRenderingSystem());
        array.add(new GameMapSelectionSystem());
        Array.ArrayIterator it = array.iterator();
        while (it.hasNext()) {
            addSystem((GameSystem) it.next());
        }
        b();
        Array.ArrayIterator it2 = array.iterator();
        while (it2.hasNext()) {
            ((GameSystem) it2.next()).setRegistered(this);
        }
        Array.ArrayIterator it3 = array.iterator();
        while (it3.hasNext()) {
            ((GameSystem) it3.next()).setup();
        }
        Array.ArrayIterator it4 = array.iterator();
        while (it4.hasNext()) {
            ((GameSystem) it4.next()).postSetup();
        }
        Array.ArrayIterator<GameSystem> it5 = this.c.iterator();
        while (it5.hasNext()) {
            it5.next().postStateRestore();
        }
        this.events.getListeners(SystemsStateRestore.class).trigger(new SystemsStateRestore());
    }

    public final void setupSystems() {
        int i = this.c.size;
        for (int i2 = 0; i2 < i; i2++) {
            this.c.items[i2].setup();
        }
        this.events.getListeners(SystemsSetup.class).trigger(new SystemsSetup());
    }

    public final void postSetupSystems() {
        int i = this.c.size;
        for (int i2 = 0; i2 < i; i2++) {
            this.c.items[i2].postSetup();
        }
        this.events.getListeners(SystemsPostSetup.class).trigger(new SystemsPostSetup());
    }

    public final void compareSync(GameSystemProvider gameSystemProvider, StringBuilder stringBuilder, boolean z) {
        DeepClassComparisonConfig deepClassComparisonConfig = new DeepClassComparisonConfig();
        deepClassComparisonConfig.depth = 12;
        deepClassComparisonConfig.debug = z;
        deepClassComparisonConfig.addPrefix("S");
        SyncChecker.compareObjects(this, gameSystemProvider, deepClassComparisonConfig);
        stringBuilder.append(deepClassComparisonConfig.sb);
    }

    public final void resetSystemsFrameProfiling() {
        for (int i = 0; i < this.c.size; i++) {
            this.e[i] = 0;
        }
    }

    public final void flushSystemsFrameProfiling() {
        int i = this.c.size;
        for (int i2 = 0; i2 < i; i2++) {
            long j = this.e[i2];
            if (this.c.items[i2].profileUpdate()) {
                Game.i.debugManager.registerFrameJob(this.c.items[i2].getSystemName(), j);
            }
        }
    }

    public final void updateSystems() {
        if (this.gameState != null) {
            this.gameState.updateNumber++;
            this.gameState.inUpdateStage = true;
            if (Config.isHeadless() && this.gameState.updateNumber % CGL.kCGLBadAttribute == 0 && this.gameState.headlessValidatedReplayRecord != null) {
                ((HeadlessReplayValidationGame) Game.i).writeServerStatus("validating|" + this.gameState.headlessValidatedReplayRecord.id + "|" + this.gameState.updateNumber + "|" + this.gameState.validationLastUpdateNumber + "|" + ((int) (this.gameState.updateNumber / (((float) (Game.getTimestampMillis() - this.gameState.validationStartTimestamp)) * 0.001f))));
                if (this.gameState.updateNumber % 20000 == 0) {
                    f1707a.i((this.gameState.updateNumber / 1000) + "k", new Object[0]);
                }
            }
        }
        float tickRateDeltaTime = this.gameValue == null ? Config.UPDATE_DELTA_TIME : this.gameValue.getTickRateDeltaTime();
        int i = 0;
        int i2 = this.c.size;
        for (int i3 = 0; i3 < i2; i3++) {
            long realTickCount = Game.getRealTickCount();
            this.c.items[i3].update(tickRateDeltaTime);
            long[] jArr = this.e;
            int i4 = i;
            jArr[i4] = jArr[i4] + (Game.getRealTickCount() - realTickCount);
            i++;
        }
        this.map.updateDirtyTiles();
        if (this.gameState != null) {
            this.gameState.inUpdateStage = false;
        }
    }

    public final Array<GameSystem> getSystemsOrdered() {
        return this.c;
    }

    public final void addSystem(GameSystem gameSystem) {
        if (gameSystem == null) {
            throw new IllegalArgumentException("System is null");
        }
        this.d.put(gameSystem.getClass(), gameSystem);
        this.c.add(gameSystem);
        if (gameSystem instanceof InventorySystem) {
            this._inventory = (InventorySystem) gameSystem;
        }
        if (gameSystem instanceof MapEditorSystem) {
            this._mapEditor = (MapEditorSystem) gameSystem;
        }
        if (gameSystem instanceof StateSystem) {
            this.state = (StateSystem) gameSystem;
        }
        if (gameSystem instanceof GameStateSystem) {
            this.gameState = (GameStateSystem) gameSystem;
        }
        if (gameSystem instanceof SoundSystem) {
            this._sound = (SoundSystem) gameSystem;
        }
        if (gameSystem instanceof AchievementSystem) {
            this.achievement = (AchievementSystem) gameSystem;
        }
        if (gameSystem instanceof InputSystem) {
            this._input = (InputSystem) gameSystem;
        }
        if (gameSystem instanceof HotKeySystem) {
            this._hotKey = (HotKeySystem) gameSystem;
        }
        if (gameSystem instanceof GameUiSystem) {
            this._gameUi = (GameUiSystem) gameSystem;
        }
        if (gameSystem instanceof MapEditorUiSystem) {
            this._mapEditorUi = (MapEditorUiSystem) gameSystem;
        }
        if (gameSystem instanceof MapRenderingSystem) {
            this._mapRendering = (MapRenderingSystem) gameSystem;
        }
        if (gameSystem instanceof PathRenderingSystem) {
            this._pathRendering = (PathRenderingSystem) gameSystem;
        }
        if (gameSystem instanceof ProjectileTrailSystem) {
            this._projectileTrail = (ProjectileTrailSystem) gameSystem;
        }
        if (gameSystem instanceof ParticleSystem) {
            this._particle = (ParticleSystem) gameSystem;
        }
        if (gameSystem instanceof GameValueSystem) {
            this.gameValue = (GameValueSystem) gameSystem;
        }
        if (gameSystem instanceof ExperienceSystem) {
            this.experience = (ExperienceSystem) gameSystem;
        }
        if (gameSystem instanceof QuestSystem) {
            this._quest = (QuestSystem) gameSystem;
        }
        if (gameSystem instanceof BuffSystem) {
            this.buff = (BuffSystem) gameSystem;
        }
        if (gameSystem instanceof LootSystem) {
            this.loot = (LootSystem) gameSystem;
        }
        if (gameSystem instanceof EnemySystem) {
            this.enemy = (EnemySystem) gameSystem;
        }
        if (gameSystem instanceof UnitSystem) {
            this.unit = (UnitSystem) gameSystem;
        }
        if (gameSystem instanceof AbilitySystem) {
            this.ability = (AbilitySystem) gameSystem;
        }
        if (gameSystem instanceof MapSystem) {
            this.map = (MapSystem) gameSystem;
        }
        if (gameSystem instanceof ProjectileSystem) {
            this.projectile = (ProjectileSystem) gameSystem;
        }
        if (gameSystem instanceof ExplosionSystem) {
            this.explosion = (ExplosionSystem) gameSystem;
        }
        if (gameSystem instanceof TowerSystem) {
            this.tower = (TowerSystem) gameSystem;
        }
        if (gameSystem instanceof MinerSystem) {
            this.miner = (MinerSystem) gameSystem;
        }
        if (gameSystem instanceof ModifierSystem) {
            this.modifier = (ModifierSystem) gameSystem;
        }
        if (gameSystem instanceof PathfindingSystem) {
            this.pathfinding = (PathfindingSystem) gameSystem;
        }
        if (gameSystem instanceof WaveSystem) {
            this.wave = (WaveSystem) gameSystem;
        }
        if (gameSystem instanceof StatisticsSystem) {
            this.statistics = (StatisticsSystem) gameSystem;
        }
        if (gameSystem instanceof RandomEncounterSystem) {
            this.randomEncounter = (RandomEncounterSystem) gameSystem;
        }
        if (gameSystem instanceof ScriptSystem) {
            this.script = (ScriptSystem) gameSystem;
        }
        if (gameSystem instanceof BonusSystem) {
            this.bonus = (BonusSystem) gameSystem;
        }
        if (gameSystem instanceof GameplayModSystem) {
            this.gameplayMod = (GameplayModSystem) gameSystem;
        }
        if (gameSystem instanceof DamageSystem) {
            this.damage = (DamageSystem) gameSystem;
        }
        if (gameSystem instanceof RenderSystem) {
            this._render = (RenderSystem) gameSystem;
        }
        if (gameSystem instanceof CachedRenderingSystem) {
            this._cachedRendering = (CachedRenderingSystem) gameSystem;
        }
        if (gameSystem instanceof GameMapSelectionSystem) {
            this._gameMapSelection = (GameMapSelectionSystem) gameSystem;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T extends GameSystem> T a(Class<T> cls) {
        T t = (T) this.d.get(cls, null);
        if (t == null) {
            for (int i = 0; i < this.c.size; i++) {
                if (cls.isInstance(this.c.items[i])) {
                    return (T) this.c.items[i];
                }
            }
            throw new IllegalArgumentException("System " + cls.getName() + " is not registered");
        }
        return t;
    }

    public final void dispose() {
        this.events.getListeners(SystemsDispose.class).trigger(new SystemsDispose());
        int i = this.c.size;
        for (int i2 = 0; i2 < i; i2++) {
            this.c.get(i2).dispose();
            this.c.get(i2).setUnregistered();
        }
        this.c.clear();
        this.d.clear();
        this._inventory = null;
        this._mapEditor = null;
        this._sound = null;
        this._input = null;
        this._gameUi = null;
        this._mapRendering = null;
        this._pathRendering = null;
        this._projectileTrail = null;
        this._particle = null;
        this.gameValue = null;
        this.experience = null;
        this.damage = null;
        this.state = null;
        this.gameState = null;
        this._quest = null;
        this.buff = null;
        this.loot = null;
        this.enemy = null;
        this.unit = null;
        this.ability = null;
        this.map = null;
        this.projectile = null;
        this.explosion = null;
        this.tower = null;
        this.miner = null;
        this.modifier = null;
        this.wave = null;
        this.statistics = null;
        this.script = null;
        this.bonus = null;
        this.events = null;
    }

    public final boolean isDisposed() {
        return this.events == null;
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/GameSystemProvider$SystemsConfig.class */
    public static class SystemsConfig implements KryoSerializable {
        public boolean headless;
        public boolean noScripts;
        public Setup setup;

        @REGS
        /* loaded from: infinitode-2.jar:com/prineside/tdi2/GameSystemProvider$SystemsConfig$Setup.class */
        public enum Setup {
            GAME,
            MAP_EDITOR
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void write(Kryo kryo, Output output) {
            output.writeBoolean(this.headless);
            output.writeBoolean(this.noScripts);
            kryo.writeObject(output, this.setup);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void read(Kryo kryo, Input input) {
            this.headless = input.readBoolean();
            this.noScripts = input.readBoolean();
            this.setup = (Setup) kryo.readObject(input, Setup.class);
        }

        private SystemsConfig() {
        }

        public SystemsConfig(Setup setup, boolean z) {
            this.setup = setup;
            this.headless = z;
        }

        public SystemsConfig disableScripts() {
            this.noScripts = true;
            return this;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/GameSystemProvider$ThreadSafeSharedHelpers.class */
    public static final class ThreadSafeSharedHelpers {
        public static final int MAX_ENEMY_ARRAYS = 8;
        public static final int MAX_TOWER_ARRAYS = 8;
        public static final int MAX_MINER_ARRAYS = 8;
        public static final int MAX_TILE_ARRAYS = 8;
        public final Sort sort = new Sort();
        public final FloatSorter floatSorter = new FloatSorter(this.sort);

        /* renamed from: a, reason: collision with root package name */
        private final Array<Array<Enemy>> f1710a = new Array<>(true, 1, Array.class);

        /* renamed from: b, reason: collision with root package name */
        private final Array<Array<Tower>> f1711b = new Array<>(true, 1, Array.class);
        private final Array<Array<Tile>> c = new Array<>(true, 1, Array.class);
        private final Array<Array<Miner>> d = new Array<>(true, 1, Array.class);

        public final Array<Enemy> getEnemyArray() {
            if (this.f1710a.size == 0) {
                return new Array<>(true, 1, Enemy.class);
            }
            return this.f1710a.pop();
        }

        public final void freeEnemyArray(Array<Enemy> array) {
            if (this.f1710a.size == 8) {
                GameSystemProvider.f1707a.i("disposing enemy array (max arrays reached)", new Throwable());
            } else {
                array.clear();
                this.f1710a.add(array);
            }
        }

        public final Array<Tower> getTowerArray() {
            if (this.f1711b.size == 0) {
                return new Array<>(true, 1, Tower.class);
            }
            return this.f1711b.pop();
        }

        public final void freeTowerArray(Array<Tower> array) {
            if (this.f1711b.size == 8) {
                GameSystemProvider.f1707a.i("disposing tower array (max arrays reached)", new Object[0]);
            } else {
                array.clear();
                this.f1711b.add(array);
            }
        }

        public final Array<Miner> getMinerArray() {
            if (this.d.size == 0) {
                return new Array<>(true, 1, Miner.class);
            }
            return this.d.pop();
        }

        public final void freeMinerArray(Array<Miner> array) {
            if (this.d.size == 8) {
                GameSystemProvider.f1707a.i("disposing miner array (max arrays reached)", new Object[0]);
            } else {
                array.clear();
                this.d.add(array);
            }
        }

        public final Array<Tile> getTileArray() {
            if (this.c.size == 0) {
                return new Array<>(true, 1, Tile.class);
            }
            return this.c.pop();
        }

        public final void freeTileArray(Array<Tile> array) {
            if (this.c.size == 8) {
                GameSystemProvider.f1707a.i("disposing tile array (max arrays reached)", new Object[0]);
            } else {
                array.clear();
                this.c.add(array);
            }
        }
    }
}
