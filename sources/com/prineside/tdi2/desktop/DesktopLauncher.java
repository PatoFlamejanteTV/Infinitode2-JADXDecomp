package com.prineside.tdi2.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3FileHandle;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.pay.PurchaseManager;
import com.badlogic.gdx.utils.ObjectMap;
import com.codedisaster.steamworks.SteamAPI;
import com.codedisaster.steamworks.SteamApps;
import com.codedisaster.steamworks.SteamAuth;
import com.codedisaster.steamworks.SteamAuthTicket;
import com.codedisaster.steamworks.SteamException;
import com.codedisaster.steamworks.SteamFriends;
import com.codedisaster.steamworks.SteamFriendsCallback;
import com.codedisaster.steamworks.SteamID;
import com.codedisaster.steamworks.SteamLeaderboardEntriesHandle;
import com.codedisaster.steamworks.SteamLeaderboardHandle;
import com.codedisaster.steamworks.SteamResult;
import com.codedisaster.steamworks.SteamUser;
import com.codedisaster.steamworks.SteamUserCallback;
import com.codedisaster.steamworks.SteamUserStats;
import com.codedisaster.steamworks.SteamUserStatsCallback;
import com.codedisaster.steamworks.SteamUtils;
import com.codedisaster.steamworks.SteamUtilsCallback;
import com.prineside.tdi2.ActionResolver;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.NormalGame;
import com.prineside.tdi2.Screen;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.desktop.luadef.javadoc.ParserVariant;
import com.prineside.tdi2.enums.AchievementType;
import com.prineside.tdi2.events.global.Render;
import com.prineside.tdi2.managers.PurchaseManager;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.scene2d.ui.TextField;
import com.prineside.tdi2.scene2d.utils.FocusListener;
import com.prineside.tdi2.screens.GameScreen;
import com.prineside.tdi2.utils.FileChooser;
import com.prineside.tdi2.utils.IntPair;
import com.prineside.tdi2.utils.ObjectConsumer;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.Logger;
import com.prineside.tdi2.utils.logging.PlatformLogger;
import com.prineside.tdi2.utils.logging.SystemOutPlatformLogger;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.html.Attribute;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Iterator;
import javax.swing.JOptionPane;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.description.type.TypeDescription;
import org.lwjgl.opengl.GL30;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/desktop/DesktopLauncher.class */
public class DesktopLauncher {

    /* renamed from: b, reason: collision with root package name */
    private static Lwjgl3ApplicationConfiguration f1832b;
    public static SteamUser steamUser;
    public static SteamUserStats steamUserStats;
    public static SteamUtils steamUtils;
    public static SteamApps steamApps;
    public static SteamFriends steamFriends;
    public static SteamPurchaseManager purchaseManager;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f1831a = TLog.forTag("DesktopLauncher");
    private static final ObjectMap<String, String> c = new ObjectMap<>();
    private static final SteamUserCallback d = new SteamUserCallback() { // from class: com.prineside.tdi2.desktop.DesktopLauncher.1
        @Override // com.codedisaster.steamworks.SteamUserCallback
        public void onAuthSessionTicket(SteamAuthTicket steamAuthTicket, SteamResult steamResult) {
            DesktopLauncher.f1831a.i("SteamUser.onAuthSessionTicket " + steamAuthTicket + SequenceUtils.SPACE + steamResult, new Object[0]);
        }

        @Override // com.codedisaster.steamworks.SteamUserCallback
        public void onValidateAuthTicket(SteamID steamID, SteamAuth.AuthSessionResponse authSessionResponse, SteamID steamID2) {
            DesktopLauncher.f1831a.i("SteamUser.onValidateAuthTicket " + steamID + SequenceUtils.SPACE + authSessionResponse + SequenceUtils.SPACE + steamID2, new Object[0]);
        }

        @Override // com.codedisaster.steamworks.SteamUserCallback
        public void onMicroTxnAuthorization(int i2, long j, boolean z) {
            DesktopLauncher.f1831a.i("SteamUser.onMicroTxnAuthorization " + i2 + SequenceUtils.SPACE + j + SequenceUtils.SPACE + z, new Object[0]);
            DesktopLauncher.purchaseManager.onMicroTxnAuthorization(i2, j, z);
        }

        @Override // com.codedisaster.steamworks.SteamUserCallback
        public void onEncryptedAppTicket(SteamResult steamResult) {
            DesktopLauncher.f1831a.i("SteamUser.onEncryptedAppTicket " + steamResult, new Object[0]);
        }
    };
    private static final SteamUserStatsCallback e = new SteamUserStatsCallback() { // from class: com.prineside.tdi2.desktop.DesktopLauncher.2
        @Override // com.codedisaster.steamworks.SteamUserStatsCallback
        public void onUserStatsReceived(long j, SteamID steamID, SteamResult steamResult) {
            DesktopLauncher.f1831a.i("SteamUserStats.onUserStatsReceived " + j + SequenceUtils.SPACE + steamID + SequenceUtils.SPACE + steamResult, new Object[0]);
            DesktopLauncher.a(true);
        }

        @Override // com.codedisaster.steamworks.SteamUserStatsCallback
        public void onUserStatsStored(long j, SteamResult steamResult) {
            DesktopLauncher.f1831a.i("SteamUserStats.onUserStatsStored " + j + SequenceUtils.SPACE + steamResult, new Object[0]);
        }

        @Override // com.codedisaster.steamworks.SteamUserStatsCallback
        public void onUserStatsUnloaded(SteamID steamID) {
            DesktopLauncher.f1831a.i("SteamUserStats.onUserStatsUnloaded " + steamID, new Object[0]);
        }

        @Override // com.codedisaster.steamworks.SteamUserStatsCallback
        public void onUserAchievementStored(long j, boolean z, String str, int i2, int i3) {
            DesktopLauncher.f1831a.i("SteamUserStats.onUserAchievementStored " + j + SequenceUtils.SPACE + z + SequenceUtils.SPACE + str + SequenceUtils.SPACE + i2 + SequenceUtils.SPACE + i3, new Object[0]);
        }

        @Override // com.codedisaster.steamworks.SteamUserStatsCallback
        public void onLeaderboardFindResult(SteamLeaderboardHandle steamLeaderboardHandle, boolean z) {
            DesktopLauncher.f1831a.i("SteamUserStats.onLeaderboardFindResult " + steamLeaderboardHandle + SequenceUtils.SPACE + z, new Object[0]);
        }

        @Override // com.codedisaster.steamworks.SteamUserStatsCallback
        public void onLeaderboardScoresDownloaded(SteamLeaderboardHandle steamLeaderboardHandle, SteamLeaderboardEntriesHandle steamLeaderboardEntriesHandle, int i2) {
            DesktopLauncher.f1831a.i("SteamUserStats.onLeaderboardScoresDownloaded " + steamLeaderboardHandle + SequenceUtils.SPACE + steamLeaderboardEntriesHandle + SequenceUtils.SPACE + i2, new Object[0]);
        }

        @Override // com.codedisaster.steamworks.SteamUserStatsCallback
        public void onLeaderboardScoreUploaded(boolean z, SteamLeaderboardHandle steamLeaderboardHandle, int i2, boolean z2, int i3, int i4) {
            DesktopLauncher.f1831a.i("SteamUserStats.onLeaderboardScoreUploaded " + z + SequenceUtils.SPACE + steamLeaderboardHandle + SequenceUtils.SPACE + i2 + SequenceUtils.SPACE + z2 + SequenceUtils.SPACE + i3 + SequenceUtils.SPACE + i4, new Object[0]);
        }

        @Override // com.codedisaster.steamworks.SteamUserStatsCallback
        public void onNumberOfCurrentPlayersReceived(boolean z, int i2) {
            DesktopLauncher.f1831a.i("SteamUserStats.onNumberOfCurrentPlayersReceived " + z + SequenceUtils.SPACE + i2, new Object[0]);
        }

        @Override // com.codedisaster.steamworks.SteamUserStatsCallback
        public void onGlobalStatsReceived(long j, SteamResult steamResult) {
            DesktopLauncher.f1831a.i("SteamUserStats.onGlobalStatsReceived " + j + SequenceUtils.SPACE + steamResult, new Object[0]);
        }
    };
    private static final SteamUtilsCallback f = new SteamUtilsCallback() { // from class: com.prineside.tdi2.desktop.DesktopLauncher.3
        @Override // com.codedisaster.steamworks.SteamUtilsCallback
        public void onSteamShutdown() {
            DesktopLauncher.f1831a.i("SteamUtils.onSteamShutdown", new Object[0]);
        }
    };
    private static final SteamFriendsCallback g = new SteamFriendsCallback() { // from class: com.prineside.tdi2.desktop.DesktopLauncher.4
        @Override // com.codedisaster.steamworks.SteamFriendsCallback
        public void onSetPersonaNameResponse(boolean z, boolean z2, SteamResult steamResult) {
        }

        @Override // com.codedisaster.steamworks.SteamFriendsCallback
        public void onPersonaStateChange(SteamID steamID, SteamFriends.PersonaChange personaChange) {
        }

        @Override // com.codedisaster.steamworks.SteamFriendsCallback
        public void onGameOverlayActivated(boolean z) {
            DesktopLauncher.f1831a.i("SteamFriendsCallback.onGameOverlayActivated " + z, new Object[0]);
            if (Gdx.app != null) {
                Threads.i().postRunnable(() -> {
                    try {
                        Screen currentScreen = Game.i.screenManager.getCurrentScreen();
                        if (currentScreen instanceof GameScreen) {
                            GameScreen gameScreen = (GameScreen) currentScreen;
                            if (gameScreen.S != null && gameScreen.S.gameState != null && !gameScreen.S.gameState.isGameOver()) {
                                gameScreen.S.gameState.pauseGame();
                            }
                        }
                    } catch (Exception e2) {
                        DesktopLauncher.f1831a.e("Failed to handle onGameOverlayActivated", e2);
                    }
                });
            }
        }

        @Override // com.codedisaster.steamworks.SteamFriendsCallback
        public void onGameLobbyJoinRequested(SteamID steamID, SteamID steamID2) {
        }

        @Override // com.codedisaster.steamworks.SteamFriendsCallback
        public void onAvatarImageLoaded(SteamID steamID, int i2, int i3, int i4) {
        }

        @Override // com.codedisaster.steamworks.SteamFriendsCallback
        public void onFriendRichPresenceUpdate(SteamID steamID, int i2) {
        }

        @Override // com.codedisaster.steamworks.SteamFriendsCallback
        public void onGameRichPresenceJoinRequested(SteamID steamID, String str) {
        }

        @Override // com.codedisaster.steamworks.SteamFriendsCallback
        public void onGameServerChangeRequested(String str, String str2) {
        }
    };
    private static boolean h = false;
    private static boolean i = false;

    static /* synthetic */ boolean a(boolean z) {
        i = true;
        return true;
    }

    public static void main(String[] strArr) {
        if (strArr.length != 0 && "generate-lua-defs".equals(strArr[0])) {
            Logger.init(ActionResolver.createDummy(new Lwjgl3FileHandle("luadef-gen-log.txt", Files.FileType.Local), new SystemOutPlatformLogger(true, true)));
            f1831a.i("Starting Lua definitions generator", new Object[0]);
            try {
                boolean z = false;
                int length = strArr.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    }
                    if (!"-v".equals(strArr[i2])) {
                        i2++;
                    } else {
                        z = true;
                        break;
                    }
                }
                LuaDefinitionsGenerator.verbose = z;
                ParserVariant.verbose = z;
                ParserVariant.verboseGenerics = z;
                ParserVariant.verboseParamMatch = z;
                new LuaDefinitionsGenerator().runEverything();
                return;
            } catch (IOException e2) {
                throw new RuntimeException(e2);
            }
        }
        ActionResolverDesktop actionResolverDesktop = new ActionResolverDesktop();
        Lwjgl3ApplicationConfiguration lwjgl3ApplicationConfiguration = new Lwjgl3ApplicationConfiguration();
        f1832b = lwjgl3ApplicationConfiguration;
        lwjgl3ApplicationConfiguration.setTitle("Infinitode 2");
        f1832b.setWindowIcon("res/logo-32.png");
        ActionResolver.InitConfigManager initConfigManager = actionResolverDesktop.getInitConfigManager();
        f1832b.setAudioConfig(initConfigManager.get(SettingsManager.InitConfig.AUDIO_SIM_SOURCES), initConfigManager.get(SettingsManager.InitConfig.AUDIO_BUFFER_SIZE), initConfigManager.get(SettingsManager.InitConfig.AUDIO_BUFFER_COUNT));
        f1832b.setBackBufferConfig(8, 8, 8, 8, 16, 0, initConfigManager.get(SettingsManager.InitConfig.GRAPHICS_AA_LEVELS));
        f1832b.useVsync(initConfigManager.get(SettingsManager.InitConfig.GRAPHICS_VSYNC) != 0);
        f1832b.setIdleFPS(initConfigManager.get(SettingsManager.InitConfig.GRAPHICS_FPS_LIMIT));
        f1832b.setForegroundFPS(initConfigManager.get(SettingsManager.InitConfig.GRAPHICS_FPS_LIMIT));
        f1832b.setWindowedMode(Config.DISPLAY_WIDTH, 900);
        new Lwjgl3Application(new NormalGame(actionResolverDesktop, () -> {
            try {
                try {
                    SteamAPI.loadLibraries();
                } catch (Throwable th) {
                    f1831a.w("failed to execute SteamAPI.loadLibraries() - probably the user has unicode characters in their OS user name, trying manually", th);
                    try {
                        System.load(new File("bin/steam_api64.dll").getAbsolutePath());
                        System.load(new File("bin/steamworks4j64.dll").getAbsolutePath());
                        Field declaredField = SteamAPI.class.getDeclaredField("isNativeAPILoaded");
                        declaredField.setAccessible(true);
                        declaredField.setBoolean(null, true);
                    } catch (Throwable th2) {
                        f1831a.e("failed to load steam dlls manually", th2);
                    }
                }
                if (!SteamAPI.init()) {
                    f1831a.e("SteamAPI failed to init", new Object[0]);
                    SteamAPI.printDebugInfo(System.out);
                    boolean restartAppIfNecessary = SteamAPI.restartAppIfNecessary(Config.STEAM_APP_ID);
                    f1831a.i("restartAppIfNecessary = " + restartAppIfNecessary, new Object[0]);
                    if (!restartAppIfNecessary) {
                        JOptionPane.showMessageDialog((Component) null, "SteamAPI failed to init, make sure Steam is running");
                    }
                    System.exit(0);
                } else {
                    f1831a.i("SteamAPI loaded", new Object[0]);
                    e();
                }
            } catch (Throwable th3) {
                f1831a.e("SteamAPI failed to init with exception", th3);
                JOptionPane.showMessageDialog((Component) null, "SteamAPI failed to init with error: " + th3.getMessage());
                System.exit(0);
            }
            if (initConfigManager.get(SettingsManager.InitConfig.GRAPHICS_FS_ENABLED) != 0) {
                f1831a.d("full screen mode enabled through config, applying", new Object[0]);
                int i3 = initConfigManager.get(SettingsManager.InitConfig.GRAPHICS_FS_WIDTH);
                int i4 = initConfigManager.get(SettingsManager.InitConfig.GRAPHICS_FS_HEIGHT);
                if (initConfigManager.get(SettingsManager.InitConfig.GRAPHICS_FS_BORDERLESS) != 0) {
                    Graphics.DisplayMode displayMode = Lwjgl3ApplicationConfiguration.getDisplayMode();
                    Gdx.graphics.setUndecorated(true);
                    Gdx.graphics.setWindowedMode(displayMode.width, displayMode.height);
                    f1831a.i("full screen windowed borderless", new Object[0]);
                } else if (i3 <= 0 || i4 <= 0) {
                    Gdx.graphics.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
                    f1831a.i("full screen mode set to values retrieved from LWJGL", new Object[0]);
                } else {
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    Gdx.graphics.setFullscreenMode(SettingsManager.getBestFullscreenModeWithPrefDimensions(i3, i4, screenSize.width, screenSize.height));
                    f1831a.i("full screen mode set to values specified in config", new Object[0]);
                }
            } else {
                f1831a.d("full screen mode not enabled through config", new Object[0]);
                try {
                    int i5 = 0;
                    int i6 = 0;
                    for (Graphics.DisplayMode displayMode2 : Gdx.graphics.getDisplayModes()) {
                        i5 = Math.max(i5, displayMode2.width);
                        i6 = Math.max(i6, displayMode2.height);
                    }
                    if (i5 <= 1440.0f || i6 <= 810.0f) {
                        f1831a.i("display is too small, falling back to windowed 1280x720", new Object[0]);
                        Gdx.app.postRunnable(() -> {
                            try {
                                Gdx.graphics.setWindowedMode(1280, 720);
                            } catch (Exception unused) {
                            }
                        });
                    }
                } catch (Exception unused) {
                }
            }
            Game.EVENTS.getListeners(Render.class).add(render -> {
                if (SteamAPI.isSteamRunning()) {
                    if (steamUserStats != null && !h) {
                        h = true;
                        steamUserStats.requestCurrentStats();
                    }
                    SteamAPI.runCallbacks();
                }
            });
        }), f1832b);
    }

    private static void e() {
        f1831a.d("Steam: Register user...", new Object[0]);
        steamUser = new SteamUser(d);
        f1831a.d("Steam: Register user stats callback...", new Object[0]);
        steamUserStats = new SteamUserStats(e);
        f1831a.d("Steam: Register Utils...", new Object[0]);
        steamUtils = new SteamUtils(f);
        f1831a.d("Steam: Register Apps...", new Object[0]);
        steamApps = new SteamApps();
        f1831a.d("Steam: Register Friends...", new Object[0]);
        System.out.println("Register Friends ...");
        steamFriends = new SteamFriends(g);
        f1831a.d("Steam: Local user account ID: " + steamUser.getSteamID().getAccountID(), new Object[0]);
        f1831a.d("Steam: Local user steam ID: " + SteamID.getNativeHandle(steamUser.getSteamID()), new Object[0]);
        f1831a.d("Steam: App ID: " + steamUtils.getAppID(), new Object[0]);
        f1831a.d("Steam: App build ID: " + steamApps.getAppBuildId(), new Object[0]);
        f1831a.d("Steam: App owner: " + steamApps.getAppOwner().getAccountID(), new Object[0]);
        f1831a.d("Steam: App purchase time: " + steamApps.getEarliestPurchaseUnixTime(Config.STEAM_APP_ID), new Object[0]);
        f1831a.d("Steam: App subscribed from free weekend: " + steamApps.isSubscribedFromFreeWeekend(), new Object[0]);
        f1831a.d("Steam: App subscribed: " + steamApps.isSubscribedApp(Config.STEAM_APP_ID), new Object[0]);
        f1831a.d("Steam: Friends persona name: " + steamFriends.getPersonaName(), new Object[0]);
        c.put("steam.account_id", new StringBuilder().append(steamUser.getSteamID().getAccountID()).toString());
        c.put("steam.user_id", new StringBuilder().append(SteamID.getNativeHandle(steamUser.getSteamID())).toString());
        c.put("steam.app_id", new StringBuilder().append(steamUtils.getAppID()).toString());
        c.put("steam.app_build_id", new StringBuilder().append(steamApps.getAppBuildId()).toString());
        c.put("steam.owner_id", new StringBuilder().append(steamApps.getAppOwner().getAccountID()).toString());
        c.put("steam.purchase_time", new StringBuilder().append(steamApps.getEarliestPurchaseUnixTime(Config.STEAM_APP_ID)).toString());
        c.put("steam.purchased_from_free_weekend", new StringBuilder().append(steamApps.isSubscribedFromFreeWeekend()).toString());
        c.put("steam.app_subscribed", new StringBuilder().append(steamApps.isSubscribedApp(Config.STEAM_APP_ID)).toString());
        c.put("steam.persona_name", steamFriends.getPersonaName());
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/desktop/DesktopLauncher$ActionResolverDesktop.class */
    public static class ActionResolverDesktop extends ActionResolver.ActionResolverAdapter {

        /* renamed from: a, reason: collision with root package name */
        private ActionResolver.InitConfigManager f1834a;

        /* renamed from: b, reason: collision with root package name */
        private DesktopFileChooser f1835b;
        private boolean c;
        private boolean d;

        @Override // com.prineside.tdi2.ActionResolver.ActionResolverAdapter, com.prineside.tdi2.ActionResolver
        public PurchaseManager getPurchaseManager() {
            if (DesktopLauncher.steamApps == null) {
                return null;
            }
            if (DesktopLauncher.purchaseManager == null) {
                DesktopLauncher.purchaseManager = new SteamPurchaseManager();
            }
            return DesktopLauncher.purchaseManager;
        }

        @Override // com.prineside.tdi2.ActionResolver.ActionResolverAdapter, com.prineside.tdi2.ActionResolver
        public HashSet<Class<?>> getClasses(String str) {
            HashSet<Class<?>> hashSet = new HashSet<>();
            hashSet.add(Object.class);
            ScanResult scan = new ClassGraph().removeTemporaryFilesAfterScan().enableSystemJarsAndModules().ignoreClassVisibility().acceptPackages(str).scan();
            Iterator it = scan.getAllClasses().iterator();
            while (it.hasNext()) {
                try {
                    hashSet.add(Class.forName(((ClassInfo) it.next()).getName()));
                } catch (Throwable unused) {
                }
            }
            scan.close();
            return hashSet;
        }

        @Override // com.prineside.tdi2.ActionResolver.ActionResolverAdapter, com.prineside.tdi2.ActionResolver
        public boolean personalizedAdsSupported() {
            return false;
        }

        @Override // com.prineside.tdi2.ActionResolver.ActionResolverAdapter, com.prineside.tdi2.ActionResolver
        public boolean personalizedAdsEnabled() {
            return this.c;
        }

        @Override // com.prineside.tdi2.ActionResolver.ActionResolverAdapter, com.prineside.tdi2.ActionResolver
        public void setPersonalizedAds(boolean z) {
            this.c = z;
        }

        @Override // com.prineside.tdi2.ActionResolver
        public FileChooser getFileChooser() {
            if (this.f1835b == null) {
                this.f1835b = new DesktopFileChooser();
            }
            return this.f1835b;
        }

        @Override // com.prineside.tdi2.ActionResolver.ActionResolverAdapter, com.prineside.tdi2.ActionResolver
        public void requestSteamAuthTicket(ObjectConsumer<String> objectConsumer) {
            if (DesktopLauncher.steamUser != null) {
                int[] iArr = {0};
                ByteBuffer allocateDirect = ByteBuffer.allocateDirect(1024);
                try {
                    DesktopLauncher.steamUser.getAuthSessionTicket(allocateDirect, iArr);
                    byte[] bArr = new byte[iArr[0]];
                    allocateDirect.get(bArr, 0, bArr.length);
                    objectConsumer.accept(StringFormatter.bytesToHex(bArr));
                    return;
                } catch (SteamException e) {
                    DesktopLauncher.f1831a.e("getAuthSessionTicket failed", e);
                    objectConsumer.accept(null);
                    return;
                }
            }
            objectConsumer.accept(null);
        }

        @Override // com.prineside.tdi2.ActionResolver.ActionResolverAdapter, com.prineside.tdi2.ActionResolver
        public ActionResolver.InitConfigManager getInitConfigManager() {
            if (this.f1834a == null) {
                this.f1834a = new ActionResolver.InitConfigManager(this) { // from class: com.prineside.tdi2.desktop.DesktopLauncher.ActionResolverDesktop.1
                    @Override // com.prineside.tdi2.ActionResolver.InitConfigManager
                    public boolean isAvailable(SettingsManager.InitConfig initConfig) {
                        switch (initConfig) {
                            case GRAPHICS_VSYNC:
                            case AUDIO_BUFFER_SIZE:
                            case GRAPHICS_FS_WIDTH:
                            case AUDIO_BUFFER_COUNT:
                            case AUDIO_SIM_SOURCES:
                            case GRAPHICS_AA_LEVELS:
                            case GRAPHICS_FPS_LIMIT:
                            case GRAPHICS_FS_HEIGHT:
                            case GRAPHICS_FS_ENABLED:
                            case GRAPHICS_FS_BORDERLESS:
                            case GRAPHICS_PAUSE_ON_MIN:
                            case GRAPHICS_PAUSE_ON_BACK:
                            case GRAPHICS_BASIS_TEXTURES:
                            case GRAPHICS_ALLOW_SOFTWARE:
                                return true;
                            default:
                                return false;
                        }
                    }

                    @Override // com.prineside.tdi2.ActionResolver.InitConfigManager
                    public int getDefault(SettingsManager.InitConfig initConfig) {
                        switch (initConfig) {
                            case GRAPHICS_VSYNC:
                                return 1;
                            case AUDIO_BUFFER_SIZE:
                                return 1024;
                            case GRAPHICS_FS_WIDTH:
                                return -1;
                            case AUDIO_BUFFER_COUNT:
                                return 18;
                            case AUDIO_SIM_SOURCES:
                                return 56;
                            case GRAPHICS_AA_LEVELS:
                                return 4;
                            case GRAPHICS_FPS_LIMIT:
                                return 0;
                            case GRAPHICS_FS_HEIGHT:
                                return -1;
                            case GRAPHICS_FS_ENABLED:
                                return 1;
                            case GRAPHICS_FS_BORDERLESS:
                                return 0;
                            case GRAPHICS_PAUSE_ON_MIN:
                                return 1;
                            case GRAPHICS_PAUSE_ON_BACK:
                                return 0;
                            case GRAPHICS_BASIS_TEXTURES:
                            default:
                                return 0;
                            case GRAPHICS_ALLOW_SOFTWARE:
                                return 0;
                        }
                    }
                };
            }
            return this.f1834a;
        }

        @Override // com.prineside.tdi2.ActionResolver
        public ObjectMap<String, String> getDeviceInfo() {
            DesktopLauncher.c.put(Attribute.ID_ATTR, "Desktop");
            try {
                DesktopLauncher.c.put("screen-res", new StringBuilder().append(Toolkit.getDefaultToolkit().getScreenResolution()).toString());
            } catch (Exception unused) {
            }
            DesktopLauncher.c.put("os.name", System.getProperty("os.name", TypeDescription.Generic.OfWildcardType.SYMBOL));
            DesktopLauncher.c.put("os.arch", System.getProperty("os.arch", TypeDescription.Generic.OfWildcardType.SYMBOL));
            DesktopLauncher.c.put(ClassFileVersion.VersionLocator.JAVA_VERSION, System.getProperty(ClassFileVersion.VersionLocator.JAVA_VERSION, TypeDescription.Generic.OfWildcardType.SYMBOL));
            return DesktopLauncher.c;
        }

        @Override // com.prineside.tdi2.ActionResolver.ActionResolverAdapter, com.prineside.tdi2.ActionResolver
        public boolean doubleGainEnabledBySteamGamePurchase() {
            if (!this.d) {
                DesktopLauncher.f1831a.i("eligibleForFreeDoubleGain called", new Object[0]);
            }
            if (DesktopLauncher.steamApps != null) {
                if (!this.d) {
                    DesktopLauncher.f1831a.i("steamApps not null", new Object[0]);
                }
                int earliestPurchaseUnixTime = DesktopLauncher.steamApps.getEarliestPurchaseUnixTime(Config.STEAM_APP_ID);
                if (!this.d) {
                    DesktopLauncher.f1831a.i("getEarliestPurchaseUnixTime " + earliestPurchaseUnixTime, new Object[0]);
                }
                DesktopLauncher.c.put("steam.earliestPurchaseUnixTime", String.valueOf(earliestPurchaseUnixTime));
                this.d = true;
                return earliestPurchaseUnixTime < 1634178509;
            }
            if (!this.d) {
                DesktopLauncher.f1831a.i("steamApps is null", new Object[0]);
            }
            this.d = true;
            DesktopLauncher.c.put("steam.earliestPurchaseUnixTime", "no steamApps");
            return false;
        }

        @Override // com.prineside.tdi2.ActionResolver
        public String glGetStringi(int i, int i2) {
            return GL30.glGetStringi(i, i2);
        }

        @Override // com.prineside.tdi2.ActionResolver.ActionResolverAdapter, com.prineside.tdi2.ActionResolver
        public int getWindowsGraphicsScale() {
            try {
                return Toolkit.getDefaultToolkit().getScreenResolution();
            } catch (Exception e) {
                DesktopLauncher.f1831a.e("failed to call java.awt.Toolkit.getDefaultToolkit().getScreenResolution()", e);
                return 120;
            }
        }

        @Override // com.prineside.tdi2.ActionResolver.ActionResolverAdapter, com.prineside.tdi2.ActionResolver
        public void unlockAchievement(AchievementType achievementType) {
            if (!DesktopLauncher.i || DesktopLauncher.steamUserStats == null) {
                DesktopLauncher.f1831a.e("steam user stats are not loaded yet", new Object[0]);
                return;
            }
            try {
                DesktopLauncher.steamUserStats.setAchievement("ACHIEVEMENT_" + achievementType.name());
                DesktopLauncher.steamUserStats.storeStats();
            } catch (Exception e) {
                DesktopLauncher.f1831a.e("failed to unlock achievement " + achievementType, e);
            }
        }

        @Override // com.prineside.tdi2.ActionResolver.ActionResolverAdapter, com.prineside.tdi2.ActionResolver
        public void setFpsLimit(int i) {
            DesktopLauncher.f1832b.setForegroundFPS(i);
            DesktopLauncher.f1832b.setIdleFPS(i);
            Gdx.graphics.setForegroundFPS(i);
        }

        @Override // com.prineside.tdi2.ActionResolver
        public String getShortDeviceInfo() {
            return System.getProperty("os.name", TypeDescription.Generic.OfWildcardType.SYMBOL) + " / " + System.getProperty("os.arch", TypeDescription.Generic.OfWildcardType.SYMBOL) + " / " + System.getProperty(ClassFileVersion.VersionLocator.JAVA_VERSION, TypeDescription.Generic.OfWildcardType.SYMBOL);
        }

        @Override // com.prineside.tdi2.ActionResolver.ActionResolverAdapter, com.prineside.tdi2.ActionResolver
        public IntPair getBestScreenResolution() {
            try {
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                return new IntPair(screenSize.width, screenSize.height);
            } catch (Exception e) {
                DesktopLauncher.f1831a.e("failed to get best screen resolution", e);
                return null;
            }
        }

        private static long a(long j) {
            String name = ManagementFactory.getRuntimeMXBean().getName();
            int indexOf = name.indexOf(64);
            if (indexOf <= 0) {
                return -1L;
            }
            try {
                return Long.parseLong(name.substring(0, indexOf));
            } catch (Exception unused) {
                return -1L;
            }
        }

        @Override // com.prineside.tdi2.ActionResolver.ActionResolverAdapter, com.prineside.tdi2.ActionResolver
        public void onExit() {
            DesktopLauncher.f1831a.i("onExit", new Object[0]);
            try {
                if (DesktopLauncher.steamUser != null) {
                    DesktopLauncher.steamUser.dispose();
                    DesktopLauncher.steamUser = null;
                }
                if (DesktopLauncher.steamUserStats != null) {
                    DesktopLauncher.steamUserStats.dispose();
                    DesktopLauncher.steamUserStats = null;
                }
                if (DesktopLauncher.steamUtils != null) {
                    DesktopLauncher.steamUtils.dispose();
                    DesktopLauncher.steamUtils = null;
                }
                if (DesktopLauncher.steamApps != null) {
                    DesktopLauncher.steamApps.dispose();
                    DesktopLauncher.steamApps = null;
                }
                if (DesktopLauncher.steamFriends != null) {
                    DesktopLauncher.steamFriends.dispose();
                    DesktopLauncher.steamFriends = null;
                }
                SteamAPI.shutdown();
                DesktopLauncher.f1831a.i("SteamAPI shutdown", new Object[0]);
            } catch (Exception e) {
                DesktopLauncher.f1831a.e("failed to stop steam", e);
            }
            Thread thread = new Thread(() -> {
                ?? r0;
                System.out.println("Started killer thread");
                for (int i = 0; i < 10; i++) {
                    try {
                        System.out.println("Killer is sleeping " + (i + 1) + "/10");
                        Thread.sleep(100L);
                    } catch (Exception e2) {
                        System.out.println("Interrupted the killer thread");
                        e2.printStackTrace();
                        return;
                    }
                }
                System.out.println("Game is still running, trying to kill");
                long a2 = a(-1L);
                ?? r02 = (a2 > (-1L) ? 1 : (a2 == (-1L) ? 0 : -1));
                if (r02 != 0) {
                    try {
                        System.out.println("Killing process id " + a2);
                        r02 = Runtime.getRuntime().exec("taskkill /PID " + a2 + " /F").waitFor();
                        r0 = r02;
                    } catch (Throwable th) {
                        r02.printStackTrace();
                        r0 = r02;
                    }
                } else {
                    try {
                        System.out.println("Process id not found, killing by name");
                        r02 = Runtime.getRuntime().exec("taskkill /F /IM infinitode-2.exe").waitFor();
                        r0 = r02;
                    } catch (Throwable th2) {
                        r02.printStackTrace();
                        r0 = r02;
                    }
                }
                try {
                    r0 = Runtime.getRuntime();
                    r0.halt(0);
                } catch (Throwable th3) {
                    r0.printStackTrace();
                }
            }, "Game killer");
            thread.setDaemon(true);
            thread.start();
        }

        @Override // com.prineside.tdi2.ActionResolver.ActionResolverAdapter, com.prineside.tdi2.ActionResolver
        public void getMobilePasswordInput(Input.TextInputListener textInputListener, String str, String str2, String str3) {
            throw new RuntimeException("Only for mobile devices");
        }

        @Override // com.prineside.tdi2.ActionResolver.ActionResolverAdapter, com.prineside.tdi2.ActionResolver
        public boolean rewardAdsAvailable() {
            return false;
        }

        @Override // com.prineside.tdi2.ActionResolver.ActionResolverAdapter, com.prineside.tdi2.ActionResolver
        public boolean canShowRewardAd() {
            return true;
        }

        @Override // com.prineside.tdi2.ActionResolver.ActionResolverAdapter, com.prineside.tdi2.ActionResolver
        public boolean hasGoogleAuth() {
            return false;
        }

        @Override // com.prineside.tdi2.ActionResolver.ActionResolverAdapter, com.prineside.tdi2.ActionResolver
        public boolean isSignedInWithGoogle() {
            return false;
        }

        @Override // com.prineside.tdi2.ActionResolver.ActionResolverAdapter, com.prineside.tdi2.ActionResolver
        public void requestGoogleAuth(ObjectConsumer<String> objectConsumer) {
        }

        @Override // com.prineside.tdi2.ActionResolver.ActionResolverAdapter, com.prineside.tdi2.ActionResolver
        public void signOutGoogle() {
        }

        @Override // com.prineside.tdi2.ActionResolver.ActionResolverAdapter, com.prineside.tdi2.ActionResolver
        public int getSecondsTillCanShowRewardAd() {
            return 0;
        }

        @Override // com.prineside.tdi2.ActionResolver.ActionResolverAdapter, com.prineside.tdi2.ActionResolver
        public void showRewardAd(ObjectConsumer<Boolean> objectConsumer, PurchaseManager.RewardingAdsType rewardingAdsType) {
            objectConsumer.accept(Boolean.TRUE);
        }

        @Override // com.prineside.tdi2.ActionResolver
        public FileHandle getLogFile() {
            return new Lwjgl3FileHandle(Logger.DEFAULT_LOG_FILE_NAME, Files.FileType.Local);
        }

        @Override // com.prineside.tdi2.ActionResolver
        public PlatformLogger createPlatformLogger() {
            return new SystemOutPlatformLogger(true, true);
        }

        @Override // com.prineside.tdi2.ActionResolver.ActionResolverAdapter, com.prineside.tdi2.ActionResolver
        public boolean isAppModified() {
            return false;
        }

        @Override // com.prineside.tdi2.ActionResolver.ActionResolverAdapter, com.prineside.tdi2.ActionResolver
        public void handleTextFieldFocus(FocusListener.FocusEvent focusEvent, TextField textField, boolean z) {
            try {
                if (DesktopLauncher.steamUtils == null) {
                    return;
                }
                if (z) {
                    Vector2 localToScreenCoordinates = textField.localToScreenCoordinates(new Vector2());
                    Vector2 localToScreenCoordinates2 = textField.localToScreenCoordinates(new Vector2(textField.getWidth(), textField.getHeight()));
                    DesktopLauncher.steamUtils.showFloatingGamepadTextInput(SteamUtils.FloatingGamepadTextInputMode.ModeSingleLine, (int) localToScreenCoordinates.x, (int) localToScreenCoordinates.y, (int) (localToScreenCoordinates2.x - localToScreenCoordinates.x), (int) (localToScreenCoordinates2.y - localToScreenCoordinates.y));
                    return;
                }
                DesktopLauncher.steamUtils.dismissFloatingGamepadTextInput();
            } catch (Exception e) {
                DesktopLauncher.f1831a.e("failed to show gamepad input", e);
            }
        }
    }
}
