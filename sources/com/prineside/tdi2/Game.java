package com.prineside.tdi2;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.StringBuilder;
import com.badlogic.gdx.utils.TimeUtils;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.events.EventDispatcher;
import com.prineside.tdi2.events.global.GameDispose;
import com.prineside.tdi2.events.global.GameLoad;
import com.prineside.tdi2.events.global.GameStartLoad;
import com.prineside.tdi2.events.global.PostRender;
import com.prineside.tdi2.events.global.PreRender;
import com.prineside.tdi2.events.global.Render;
import com.prineside.tdi2.events.global.ScreenResize;
import com.prineside.tdi2.events.global.StartRender;
import com.prineside.tdi2.events.global.VisibleDisplayFrameChange;
import com.prineside.tdi2.managers.AbilityManager;
import com.prineside.tdi2.managers.AchievementManager;
import com.prineside.tdi2.managers.AnalyticsManager;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.AuthManager;
import com.prineside.tdi2.managers.BasicLevelManager;
import com.prineside.tdi2.managers.BuffManager;
import com.prineside.tdi2.managers.CursorGraphicsManager;
import com.prineside.tdi2.managers.DailyQuestManager;
import com.prineside.tdi2.managers.DebugManager;
import com.prineside.tdi2.managers.EnemyManager;
import com.prineside.tdi2.managers.GameValueManager;
import com.prineside.tdi2.managers.GateManager;
import com.prineside.tdi2.managers.HttpManager;
import com.prineside.tdi2.managers.ItemManager;
import com.prineside.tdi2.managers.LeaderBoardManager;
import com.prineside.tdi2.managers.LocaleManager;
import com.prineside.tdi2.managers.MapManager;
import com.prineside.tdi2.managers.MessageManager;
import com.prineside.tdi2.managers.MinerManager;
import com.prineside.tdi2.managers.ModifierManager;
import com.prineside.tdi2.managers.MusicManager;
import com.prineside.tdi2.managers.NetworkManager;
import com.prineside.tdi2.managers.PreferencesManager;
import com.prineside.tdi2.managers.ProgressManager;
import com.prineside.tdi2.managers.PurchaseManager;
import com.prineside.tdi2.managers.RatingManager;
import com.prineside.tdi2.managers.RenderingManager;
import com.prineside.tdi2.managers.ReplayManager;
import com.prineside.tdi2.managers.ResearchManager;
import com.prineside.tdi2.managers.ResourceManager;
import com.prineside.tdi2.managers.ScreenManager;
import com.prineside.tdi2.managers.ScriptManager;
import com.prineside.tdi2.managers.SecretCodeManager;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.managers.ShapeManager;
import com.prineside.tdi2.managers.SoundManager;
import com.prineside.tdi2.managers.StatisticsManager;
import com.prineside.tdi2.managers.TileManager;
import com.prineside.tdi2.managers.TowerManager;
import com.prineside.tdi2.managers.TowerStatManager;
import com.prineside.tdi2.managers.TriggeredActionManager;
import com.prineside.tdi2.managers.TrophyManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.managers.UnitManager;
import com.prineside.tdi2.managers.UserMapManager;
import com.prineside.tdi2.ui.shared.DeveloperConsole;
import com.prineside.tdi2.utils.GameSyncLoader;
import com.prineside.tdi2.utils.IntRectangle;
import com.prineside.tdi2.utils.errorhandling.CrashHandler;
import com.prineside.tdi2.utils.logging.Logger;
import com.prineside.tdi2.utils.logging.TLog;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.Properties;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/Game.class */
public abstract class Game extends com.badlogic.gdx.Game {
    public static Game i;
    public PreferencesManager preferencesManager;
    public ScreenManager screenManager;
    public AbilityManager abilityManager;
    public AchievementManager achievementManager;
    public AnalyticsManager analyticsManager;
    public AuthManager authManager;
    public BasicLevelManager basicLevelManager;
    public BuffManager buffManager;
    public CursorGraphicsManager cursorGraphics;
    public DailyQuestManager dailyQuestManager;
    public DebugManager debugManager;
    public EnemyManager enemyManager;
    public GameValueManager gameValueManager;
    public GateManager gateManager;
    public HttpManager httpManager;
    public ResearchManager researchManager;
    public ItemManager itemManager;
    public LocaleManager localeManager;
    public LeaderBoardManager leaderBoardManager;
    public MapManager mapManager;
    public MessageManager messageManager;
    public MinerManager minerManager;
    public ModifierManager modifierManager;
    public NetworkManager networkManager;
    public ProgressManager progressManager;
    public PurchaseManager purchaseManager;
    public RatingManager ratingManager;
    public RenderingManager renderingManager;
    public ReplayManager replayManager;
    public ResourceManager resourceManager;
    public AssetManager assetManager;
    public ScriptManager scriptManager;
    public ShapeManager shapeManager;
    public SoundManager soundManager;
    public StatisticsManager statisticsManager;
    public SecretCodeManager secretCodeManager;
    public SettingsManager settingsManager;
    public TileManager tileManager;
    public TowerManager towerManager;
    public TowerStatManager towerStatManager;
    public TriggeredActionManager triggeredActionManager;
    public TrophyManager trophyManager;
    public UnitManager unitManager;
    public UiManager uiManager;
    public UserMapManager userMapManager;
    public MusicManager musicManager;
    private boolean c;
    public BitmapFont defaultSmallFuturaFont;

    @Null
    private final Runnable f;
    public final ActionResolver actionResolver;
    public GameSyncLoader gameSyncLoader;
    private long h;
    private Thread j;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f1703a = TLog.forClass(Game.class);

    /* renamed from: b, reason: collision with root package name */
    private static final long f1704b = TimeUtils.nanoTime() / 1000;
    public static final EventDispatcher EVENTS = new EventDispatcher();
    public final Array<Manager> managers = new Array<>(true, 1);
    private boolean d = false;
    private final IntRectangle e = new IntRectangle();
    private boolean g = false;
    private int k = 0;
    private long l = -1;

    static /* synthetic */ boolean a(Game game, boolean z) {
        game.g = true;
        return true;
    }

    public Game(ActionResolver actionResolver, @Null Runnable runnable) {
        System.out.println("bootstrapping...");
        Preconditions.checkNotNull(actionResolver, "actionResolver can not be null");
        i = this;
        SyncChecker.addSyncShareableObject(this);
        this.actionResolver = actionResolver;
        this.f = () -> {
            EVENTS.reset(false);
            CrashHandler.setActionResolver(actionResolver);
            Logger.init(actionResolver);
            if (runnable != null) {
                runnable.run();
            }
        };
    }

    public static void checkConfiguredForProduction() {
        new StringBuilder().append("                  Production checklist:\n");
    }

    public static void exit() {
        if (Gdx.app != null) {
            Gdx.app.exit();
        } else {
            System.exit(0);
        }
    }

    public static boolean isLoaded() {
        return i != null && i.g;
    }

    public void runWhenGameIsLoaded(Runnable runnable) {
        if (this.c) {
            f1703a.w("runWhenGameIsLoaded skipped - game is disposed", new Throwable());
        } else if (this.g) {
            Threads.i().runOnMainThread(runnable);
        } else {
            Throwable th = new Throwable();
            EVENTS.getListeners(GameLoad.class).add(gameLoad -> {
                try {
                    runnable.run();
                } catch (Throwable th2) {
                    f1703a.e("runWhenGameIsLoaded origin", th);
                    throw new IllegalStateException("failed to perform runWhenGameIsLoaded with " + runnable, th2);
                }
            });
        }
    }

    public boolean isInMainThread() {
        if (this.j == Thread.currentThread()) {
            return true;
        }
        if (this.j == null) {
            f1703a.w("isInMainThread - libgdxThread is null", new Object[0]);
            return true;
        }
        if ("LibGDX rendering thread".equals(Thread.currentThread().getName())) {
            f1703a.w("isInMainThread - current thread has correct name but libgdxThread not equals current thread (libgdxThread name:\"%s\"). Overwriting libgdxThread with current", this.j.getName());
            this.j = Thread.currentThread();
            return true;
        }
        return false;
    }

    public void assertInMainThread() {
        if (!isInMainThread()) {
            throw new IllegalStateException("Must be called in a main thread, called from " + Thread.currentThread().getName() + " instead");
        }
    }

    @Override // com.badlogic.gdx.ApplicationListener
    public void create() {
        this.h = getTimestampMillis();
        if (this.f != null) {
            this.f.run();
        }
        checkConfiguredForProduction();
        final long timestampMillis = getTimestampMillis();
        Gdx.app.getType();
        Application.ApplicationType applicationType = Application.ApplicationType.Desktop;
        this.j = Thread.currentThread();
        this.j.setName("LibGDX rendering thread");
        CrashHandler.handleThreadExceptions(this.j);
        if (!Config.isHeadless()) {
            this.defaultSmallFuturaFont = new BitmapFont(Gdx.files.internal("resourcepacks/default/futura.fnt"), new TextureRegion(new Texture(Gdx.files.internal("resourcepacks/default/futura.png"), Pixmap.Format.RGBA4444, false)));
            Texture texture = this.defaultSmallFuturaFont.getRegion().getTexture();
            Texture.TextureFilter textureFilter = Texture.TextureFilter.Linear;
            texture.setFilter(textureFilter, textureFilter);
            this.defaultSmallFuturaFont.getData().setScale(0.75f);
        }
        Gdx.input.setCatchKey(4, true);
        int i2 = 0;
        if (!Config.isHeadless()) {
            this.preferencesManager = new PreferencesManager();
            this.managers.add(this.preferencesManager);
            this.authManager = new AuthManager();
            this.managers.add(this.authManager);
            this.screenManager = new ScreenManager();
            this.managers.add(this.screenManager);
            this.renderingManager = new RenderingManager();
            this.managers.add(this.renderingManager);
            i2 = 0 + 1 + 1 + 1 + 1;
        }
        this.gameSyncLoader = new GameSyncLoader();
        this.gameSyncLoader.addTask(new GameSyncLoader.Task("Initialization", () -> {
            f1703a.i("======== INFO ========", new Object[0]);
            f1703a.i("|- Configuration", new Object[0]);
            f1703a.i("|  |- version: R.1.9.2", new Object[0]);
            f1703a.i("|  |- build: 208", new Object[0]);
            f1703a.i("|  |- protocol: 208", new Object[0]);
            f1703a.i("|  |- debug mode: disabled", new Object[0]);
            f1703a.i("|  |- GDX app version: " + Gdx.app.getVersion(), new Object[0]);
            f1703a.i("|  |- GDX version: 1.12.1", new Object[0]);
            f1703a.i("`- Device", new Object[0]);
            f1703a.i("   |- charset: " + Charset.defaultCharset(), new Object[0]);
            ObjectMap<String, String> deviceInfo = this.actionResolver.getDeviceInfo();
            ObjectMap.Keys<String> it = deviceInfo.keys().iterator();
            while (it.hasNext()) {
                String next = it.next();
                f1703a.i("   |- " + next + ": " + deviceInfo.get(next), new Object[0]);
            }
            Runtime runtime = Runtime.getRuntime();
            f1703a.i("   |- system", new Object[0]);
            Properties properties = System.getProperties();
            Enumeration<?> propertyNames = properties.propertyNames();
            while (propertyNames.hasMoreElements()) {
                Object nextElement = propertyNames.nextElement();
                Object obj = properties.get(nextElement);
                if (obj != null) {
                    f1703a.i("   |  " + (propertyNames.hasMoreElements() ? "|" : "`") + "- " + nextElement + ": " + ((String) obj).trim(), new Object[0]);
                }
            }
            f1703a.i("   |- runtime", new Object[0]);
            f1703a.i("   |  |- available processors: " + runtime.availableProcessors(), new Object[0]);
            f1703a.i("   |  |- free memory: " + runtime.freeMemory(), new Object[0]);
            f1703a.i("   |  |- max memory: " + runtime.maxMemory(), new Object[0]);
            f1703a.i("   |  `- total memory: " + runtime.totalMemory(), new Object[0]);
            f1703a.i("   `- graphics", new Object[0]);
            f1703a.i("      |- type: " + Gdx.graphics.getGLVersion().getType(), new Object[0]);
            f1703a.i("      |- version: " + Gdx.graphics.getGLVersion().getMajorVersion() + "." + Gdx.graphics.getGLVersion().getMinorVersion() + "." + Gdx.graphics.getGLVersion().getReleaseVersion(), new Object[0]);
            f1703a.i("      |- renderer: " + Gdx.graphics.getGLVersion().getRendererString(), new Object[0]);
            f1703a.i("      |- vendor: " + Gdx.graphics.getGLVersion().getVendorString(), new Object[0]);
            f1703a.i("      |- back buffer size: " + Gdx.graphics.getBackBufferWidth() + "x" + Gdx.graphics.getBackBufferHeight(), new Object[0]);
            f1703a.i("      |- density: " + Gdx.graphics.getDensity(), new Object[0]);
            f1703a.i("      `- max texture size: " + Config.getMaxTextureSize(), new Object[0]);
            f1703a.i("======================", new Object[0]);
        }));
        if (!Config.isHeadless()) {
            this.gameSyncLoader.addTask(new GameSyncLoader.Task("Settings", () -> {
                this.settingsManager = new SettingsManager();
                this.managers.add(this.settingsManager);
            }));
            i2++;
        }
        if (!Config.isHeadless()) {
            this.gameSyncLoader.addTask(new GameSyncLoader.Task("Assets", () -> {
                this.assetManager = new AssetManager();
                this.managers.add(this.assetManager);
            }));
            this.gameSyncLoader.addTask(new GameSyncLoader.Task("Cursor graphics", () -> {
                this.cursorGraphics = new CursorGraphicsManager();
                this.managers.add(this.cursorGraphics);
            }));
            i2 = i2 + 1 + 1;
        }
        if (!Config.isHeadless()) {
            this.gameSyncLoader.addTask(new GameSyncLoader.Task("XM music", () -> {
                this.musicManager = MusicManager.createSelfSetuppingDummy();
                this.managers.add(this.musicManager);
            }));
            i2++;
        }
        this.gameSyncLoader.addTask(new GameSyncLoader.Task("Game values", () -> {
            this.gameValueManager = new GameValueManager();
            this.managers.add(this.gameValueManager);
        }));
        int i3 = i2 + 1;
        if (!Config.isHeadless()) {
            this.gameSyncLoader.addTask(new GameSyncLoader.Task("Achievements", () -> {
                this.achievementManager = new AchievementManager();
                this.managers.add(this.achievementManager);
            }));
            this.gameSyncLoader.addTask(new GameSyncLoader.Task("Analytics", () -> {
                this.analyticsManager = new AnalyticsManager();
                this.managers.add(this.analyticsManager);
            }));
            i3 = i3 + 1 + 1;
        }
        this.gameSyncLoader.addTask(new GameSyncLoader.Task("Abilities", () -> {
            this.abilityManager = new AbilityManager();
            this.managers.add(this.abilityManager);
        }));
        int i4 = i3 + 1;
        if (!Config.isHeadless()) {
            this.gameSyncLoader.addTask(new GameSyncLoader.Task("Statistics", () -> {
                this.statisticsManager = new StatisticsManager();
                this.managers.add(this.statisticsManager);
            }));
            this.gameSyncLoader.addTask(new GameSyncLoader.Task("Progress", () -> {
                this.progressManager = new ProgressManager();
                this.managers.add(this.progressManager);
            }));
            this.gameSyncLoader.addTask(new GameSyncLoader.Task("Replays", () -> {
                this.replayManager = new ReplayManager();
                this.managers.add(this.replayManager);
            }));
            i4 = i4 + 1 + 1 + 1;
        }
        if (!Config.isHeadless()) {
            this.gameSyncLoader.addTask(new GameSyncLoader.Task("Leader boards", () -> {
                this.leaderBoardManager = new LeaderBoardManager();
                this.managers.add(this.leaderBoardManager);
            }));
            i4++;
        }
        this.gameSyncLoader.addTask(new GameSyncLoader.Task("Maps", () -> {
            this.mapManager = new MapManager();
            this.managers.add(this.mapManager);
        }));
        this.gameSyncLoader.addTask(new GameSyncLoader.Task("Enemies", () -> {
            this.enemyManager = new EnemyManager();
            this.managers.add(this.enemyManager);
        }));
        this.gameSyncLoader.addTask(new GameSyncLoader.Task("Units", () -> {
            this.unitManager = new UnitManager();
            this.managers.add(this.unitManager);
        }));
        int i5 = i4 + 1 + 1 + 1;
        if (!Config.isHeadless()) {
            this.gameSyncLoader.addTask(new GameSyncLoader.Task("Sounds", () -> {
                this.soundManager = new SoundManager();
                this.managers.add(this.soundManager);
            }));
            i5++;
        }
        if (!Config.isHeadless()) {
            this.gameSyncLoader.addTask(new GameSyncLoader.Task("Shapes", () -> {
                this.shapeManager = new ShapeManager();
                this.managers.add(this.shapeManager);
            }));
            i5++;
        }
        this.gameSyncLoader.addTask(new GameSyncLoader.Task("Towers", () -> {
            this.towerManager = new TowerManager();
            this.managers.add(this.towerManager);
        }));
        this.gameSyncLoader.addTask(new GameSyncLoader.Task("Miners", () -> {
            this.minerManager = new MinerManager();
            this.managers.add(this.minerManager);
        }));
        this.gameSyncLoader.addTask(new GameSyncLoader.Task("Modifiers", () -> {
            this.modifierManager = new ModifierManager();
            this.managers.add(this.modifierManager);
        }));
        this.gameSyncLoader.addTask(new GameSyncLoader.Task("Network", () -> {
            this.networkManager = new NetworkManager();
            this.managers.add(this.networkManager);
        }));
        this.gameSyncLoader.addTask(new GameSyncLoader.Task("Tiles", () -> {
            this.tileManager = new TileManager();
            this.managers.add(this.tileManager);
        }));
        this.gameSyncLoader.addTask(new GameSyncLoader.Task("Gates", () -> {
            this.gateManager = new GateManager();
            this.managers.add(this.gateManager);
        }));
        this.gameSyncLoader.addTask(new GameSyncLoader.Task("Http", () -> {
            this.httpManager = new HttpManager();
            this.managers.add(this.httpManager);
        }));
        int i6 = i5 + 1 + 1 + 1 + 1 + 1 + 1 + 1;
        if (!Config.isHeadless()) {
            this.gameSyncLoader.addTask(new GameSyncLoader.Task("Locales", () -> {
                this.localeManager = new LocaleManager();
                this.managers.add(this.localeManager);
            }));
            i6++;
        }
        if (!Config.isHeadless()) {
            this.gameSyncLoader.addTask(new GameSyncLoader.Task("Payments", () -> {
                this.purchaseManager = new PurchaseManager();
                this.managers.add(this.purchaseManager);
            }));
            i6++;
        }
        this.gameSyncLoader.addTask(new GameSyncLoader.Task("Items", () -> {
            this.itemManager = new ItemManager();
            this.managers.add(this.itemManager);
        }));
        this.gameSyncLoader.addTask(new GameSyncLoader.Task("Basic levels", () -> {
            this.basicLevelManager = new BasicLevelManager();
            this.managers.add(this.basicLevelManager);
        }));
        this.gameSyncLoader.addTask(new GameSyncLoader.Task("Buffs", () -> {
            this.buffManager = new BuffManager();
            this.managers.add(this.buffManager);
        }));
        int i7 = i6 + 1 + 1 + 1;
        if (!Config.isHeadless()) {
            this.gameSyncLoader.addTask(new GameSyncLoader.Task("Custom maps", () -> {
                this.userMapManager = new UserMapManager();
                this.managers.add(this.userMapManager);
            }));
            i7++;
        }
        this.gameSyncLoader.addTask(new GameSyncLoader.Task("Researches", () -> {
            this.researchManager = new ResearchManager();
            this.managers.add(this.researchManager);
        }));
        this.gameSyncLoader.addTask(new GameSyncLoader.Task("Triggered actions", () -> {
            this.triggeredActionManager = new TriggeredActionManager();
            this.managers.add(this.triggeredActionManager);
        }));
        this.gameSyncLoader.addTask(new GameSyncLoader.Task("Trophies", () -> {
            this.trophyManager = new TrophyManager();
            this.managers.add(this.trophyManager);
        }));
        this.gameSyncLoader.addTask(new GameSyncLoader.Task("Daily quests", () -> {
            this.dailyQuestManager = new DailyQuestManager();
            this.managers.add(this.dailyQuestManager);
        }));
        this.gameSyncLoader.addTask(new GameSyncLoader.Task("Resources", () -> {
            this.resourceManager = new ResourceManager();
            this.managers.add(this.resourceManager);
        }));
        int i8 = i7 + 1 + 1 + 1 + 1 + 1;
        if (!Config.isHeadless()) {
            this.gameSyncLoader.addTask(new GameSyncLoader.Task("Secrets", () -> {
                this.secretCodeManager = new SecretCodeManager();
                this.managers.add(this.secretCodeManager);
            }));
            this.gameSyncLoader.addTask(new GameSyncLoader.Task("Ratings", () -> {
                this.ratingManager = new RatingManager();
                this.managers.add(this.ratingManager);
            }));
            i8 = i8 + 1 + 1;
        }
        this.gameSyncLoader.addTask(new GameSyncLoader.Task("Tower stats", () -> {
            this.towerStatManager = new TowerStatManager();
            this.managers.add(this.towerStatManager);
        }));
        int i9 = i8 + 1;
        if (!Config.isHeadless()) {
            this.gameSyncLoader.addTask(new GameSyncLoader.Task("UI", () -> {
                this.uiManager = new UiManager();
                this.managers.add(this.uiManager);
            }));
            i9++;
        }
        if (!Config.isHeadless()) {
            this.gameSyncLoader.addTask(new GameSyncLoader.Task("Debug", () -> {
                this.debugManager = new DebugManager();
                this.managers.add(this.debugManager);
            }));
            i9++;
        }
        this.gameSyncLoader.addTask(new GameSyncLoader.Task("Scripts", () -> {
            this.scriptManager = new ScriptManager();
            this.managers.add(this.scriptManager);
        }));
        int i10 = i9 + 1;
        if (!Config.isHeadless()) {
            this.gameSyncLoader.addTask(new GameSyncLoader.Task("Messages", () -> {
                this.messageManager = new MessageManager();
                this.managers.add(this.messageManager);
            }));
            i10++;
        }
        for (int i11 = 0; i11 < i10; i11++) {
            int i12 = i11;
            this.gameSyncLoader.addTask(new GameSyncLoader.Task("Setting up managers (" + (i11 + 1) + "/" + i10 + ")", () -> {
                f1703a.i("setting up manager " + (i12 + 1) + "/" + this.managers.size + " (" + this.managers.get(i12).getClass().getSimpleName() + ")", new Object[0]);
                this.managers.get(i12).setup();
                SyncChecker.addSyncShareableObject(this.managers.get(i12));
            }));
        }
        this.gameSyncLoader.addListener(new GameSyncLoader.SyncExecutionListener() { // from class: com.prineside.tdi2.Game.1
            @Override // com.prineside.tdi2.utils.GameSyncLoader.SyncExecutionListener
            public void startedTask(GameSyncLoader.Task task, GameSyncLoader.Task task2) {
                Game.f1703a.i("loading: " + task.title, new Object[0]);
            }

            @Override // com.prineside.tdi2.utils.GameSyncLoader.SyncExecutionListener
            public void done() {
                Game.a(Game.this, true);
                Game.EVENTS.trigger(new GameLoad());
                Game.f1703a.d("game loaded in " + (((float) (Game.getTimestampMillis() - timestampMillis)) * 0.001f) + "s", new Object[0]);
            }
        });
        EVENTS.trigger(new GameStartLoad());
    }

    @Override // com.badlogic.gdx.Game, com.badlogic.gdx.ApplicationListener
    public void pause() {
        super.pause();
        f1703a.i("Paused", new Object[0]);
    }

    @Override // com.badlogic.gdx.Game, com.badlogic.gdx.ApplicationListener
    public void resume() {
        super.resume();
        f1703a.i("Resumed", new Object[0]);
    }

    public boolean isDisposed() {
        return this.c;
    }

    @Override // com.badlogic.gdx.Game, com.badlogic.gdx.ApplicationListener
    public void dispose() {
        f1703a.i("Dispose", new Object[0]);
        if (this.c) {
            f1703a.i("skip dispose - already disposed", new Object[0]);
            return;
        }
        if (i != null && i.actionResolver != null) {
            i.actionResolver.onExit();
        }
        this.c = true;
        super.dispose();
        EVENTS.trigger(new GameDispose());
        if (isLoaded()) {
            Array.ArrayIterator<Manager> it = this.managers.iterator();
            while (it.hasNext()) {
                it.next().dispose();
            }
        }
    }

    public static long getRealTickCount() {
        return (TimeUtils.nanoTime() / 1000) - f1704b;
    }

    public static int getTimestampSeconds() {
        return (int) (TimeUtils.millis() / 1000);
    }

    public static long getTimestampMillis() {
        return TimeUtils.millis();
    }

    public long getMillisTillGameStart() {
        return getTimestampMillis() - this.h;
    }

    public void notifyVisibleDisplayFrameChanged(int i2, int i3, int i4, int i5) {
        f1703a.d("notifyVisibleDisplayFrameChanged %s %s %s %s", Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5));
        this.d = true;
        this.e.set(i2, i3, i5, i4);
        EVENTS.trigger(new VisibleDisplayFrameChange());
    }

    public IntRectangle getVisibleDisplayFrame() {
        if (this.d) {
            return this.e;
        }
        this.e.set(0, 0, 0, 0);
        return this.e;
    }

    @Override // com.badlogic.gdx.Game, com.badlogic.gdx.ApplicationListener
    public void render() {
        if (Config.isHeadless()) {
            return;
        }
        long timestampMillis = getTimestampMillis();
        StartRender deltaTime = new StartRender().setDeltaTime(Gdx.graphics.getDeltaTime());
        EVENTS.trigger(deltaTime);
        float deltaTime2 = deltaTime.getDeltaTime();
        if (i.settingsManager != null && Gdx.app.getType() == Application.ApplicationType.Desktop && Gdx.input.isKeyJustPressed(141)) {
            if (Gdx.input.isKeyPressed(59)) {
                Gdx.graphics.setUndecorated(false);
                if (Gdx.input.isKeyPressed(129)) {
                    Gdx.graphics.setWindowedMode(1440, Config.VIEWPORT_HEIGHT);
                } else {
                    Gdx.graphics.setWindowedMode(960, 540);
                }
            } else if (Gdx.input.isKeyPressed(129)) {
                Gdx.graphics.setUndecorated(false);
                Gdx.graphics.setWindowedMode(1920, Config.VIEWPORT_HEIGHT);
            } else if (i.actionResolver.getInitConfigManager().get(SettingsManager.InitConfig.GRAPHICS_FS_ENABLED) == 0) {
                Graphics.DisplayMode bestFullscreenMode = SettingsManager.getBestFullscreenMode(i.actionResolver.getInitConfigManager().get(SettingsManager.InitConfig.GRAPHICS_FS_WIDTH), i.actionResolver.getInitConfigManager().get(SettingsManager.InitConfig.GRAPHICS_FS_HEIGHT));
                if (bestFullscreenMode != null) {
                    if (i.actionResolver.getInitConfigManager().get(SettingsManager.InitConfig.GRAPHICS_FS_BORDERLESS) == 0) {
                        try {
                            Gdx.graphics.setUndecorated(false);
                            Gdx.graphics.setFullscreenMode(bestFullscreenMode);
                        } catch (Throwable th) {
                            f1703a.e("failed to switch to full screen mode %s", bestFullscreenMode, th);
                        }
                    } else {
                        try {
                            Gdx.graphics.setUndecorated(true);
                            Gdx.graphics.setWindowedMode(bestFullscreenMode.width, bestFullscreenMode.height);
                        } catch (Throwable th2) {
                            f1703a.e("failed to switch to windowed screen mode %s", bestFullscreenMode, th2);
                        }
                    }
                    try {
                        if (i.actionResolver.getInitConfigManager().get(SettingsManager.InitConfig.GRAPHICS_FS_ENABLED) == 0) {
                            i.actionResolver.getInitConfigManager().set(SettingsManager.InitConfig.GRAPHICS_FS_ENABLED, 1);
                            i.actionResolver.getInitConfigManager().saveIfRequired();
                        }
                    } catch (Exception unused) {
                    }
                }
            } else {
                try {
                    Gdx.graphics.setUndecorated(false);
                    Gdx.graphics.setWindowedMode(Config.DISPLAY_WIDTH, 900);
                    if (i.actionResolver.getInitConfigManager().get(SettingsManager.InitConfig.GRAPHICS_FS_ENABLED) == 1) {
                        i.actionResolver.getInitConfigManager().set(SettingsManager.InitConfig.GRAPHICS_FS_ENABLED, 0);
                        i.actionResolver.getInitConfigManager().saveIfRequired();
                    }
                } catch (Throwable th3) {
                    f1703a.e("failed to switch to default windowed screen mode", th3);
                }
            }
        }
        if (isLoaded() && i.uiManager != null && i.uiManager.isComponentInit(DeveloperConsole.class) && Gdx.input.isKeyJustPressed(68)) {
            DeveloperConsole.i().toggleVisible();
        }
        if (this.g) {
            EVENTS.trigger(new PreRender(deltaTime2));
            Array.ArrayIterator<Manager> it = this.managers.iterator();
            while (it.hasNext()) {
                it.next().preRender(deltaTime2);
            }
        }
        super.render();
        EVENTS.trigger(new Render(deltaTime2));
        if (this.g) {
            this.uiManager.render(deltaTime2);
            Array.ArrayIterator<Manager> it2 = this.managers.iterator();
            while (it2.hasNext()) {
                it2.next().postRender(deltaTime2);
            }
            EVENTS.trigger(new PostRender(deltaTime2));
        }
        Threads.i().a();
        if (this.k < 1000) {
            this.k++;
            long timestampMillis2 = getTimestampMillis();
            long j = timestampMillis2 - timestampMillis;
            long j2 = 0;
            if (this.l != -1) {
                j2 = timestampMillis2 - this.l;
            }
            if (j > 50 || j2 > 50) {
                f1703a.d("profiling - frame " + this.k + " took " + j + "ms, " + j2 + "ms since last frame", new Object[0]);
            }
            this.l = getTimestampMillis();
        }
    }

    @Override // com.badlogic.gdx.Game, com.badlogic.gdx.ApplicationListener
    public void resize(int i2, int i3) {
        if (i2 > 0 && i3 > 0) {
            EVENTS.trigger(new ScreenResize(i2, i3));
        }
        super.resize(i2, i3);
    }

    public static long getFreeHeapSpaceSize() {
        return Runtime.getRuntime().maxMemory() - (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
    }
}
