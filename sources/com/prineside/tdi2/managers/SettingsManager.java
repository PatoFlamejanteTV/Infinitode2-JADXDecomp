package com.prineside.tdi2.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.net.HttpParametersUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ByteArray;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Timer;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Manager;
import com.prineside.tdi2.managers.preferences.categories.SettingsPrefs;
import com.prineside.tdi2.managers.preferences.categories.settings.SP_Settings;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.ui.shared.Dialog;
import com.prineside.tdi2.ui.shared.TextInputOverlay;
import com.prineside.tdi2.utils.IntPair;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.Arrays;
import java.util.HashMap;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/SettingsManager.class */
public class SettingsManager extends Manager.ManagerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2454a;

    /* renamed from: b, reason: collision with root package name */
    private final String[] f2455b = new String[DynamicSetting.values().length];
    private final String[] c = new String[DynamicSetting.values().length];
    private final String[] d;
    private int e;
    private int[] f;
    private IntArray g;
    private IntArray h;
    public static final int[][] DEFAULT_HOT_KEYS;
    private static final Array<HotkeyAction> i;
    private final DelayedRemovalArray<SettingsManagerListener> j;
    private static /* synthetic */ boolean k;

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/SettingsManager$CustomValueType.class */
    public enum CustomValueType {
        CORE_HINT_SHOWN,
        UI_SCALE,
        MUSIC_CACHE_MAX_SIZE,
        SHOOTING_SOUNDS_VOLUME,
        CAMERA_TOOLS_ENABLED,
        LAST_AUTO_SHOWN_NEWS_ID,
        UNLOCK_ALL_LOCALES,
        TOUCHES_STOP_CAMERA_SCENARIOS,
        SEND_NOTIFICATIONS,
        ENABLE_REWARDING_ADS,
        ENABLE_PAUSE_AD_ICON,
        IGNORE_MAP_MUSIC,
        DRAW_TOWER_TARGET,
        SOUND_VOLUME,
        MUSIC_VOLUME,
        PERSONALIZED_ADS,
        PERSONALIZED_ADS_CONSENT_CONFIRMED,
        SMOOTH_MUSIC,
        LIVE_LEADERBOARDS,
        DPS_CHART_ENABLED,
        SLOW_MOTION_PAUSE,
        COLORBLIND_MODE,
        GRAPHICS_INTERPOLATION_ENABLED,
        CAMERA_SHAKE_ENABLED,
        STATISTICS_CHART_ENABLED,
        STATE_EDITOR_ENABLED,
        LOOT_ICONS_ENABLED,
        BACKGROUND_ENABLED,
        STATE_AUTO_SAVE_INTERVAL,
        BETA_ITEMS_GIVEN_1_9_0,
        ENDLESS_MODE_DIFFICULTY,
        ENDLESS_LEADERBOARD_HINT_SHOWN,
        INSTANT_HOLD_BUTTON_ON_RMB,
        DOUBLE_GAIN_DISABLED_MANUALLY,
        PREMIUM_STATUS_DISABLED_MANUALLY,
        UI_QUEST_LIST_VISIBLE,
        UI_LIVE_LEADERBOARDS_VISIBLE,
        UI_DETAILED_MODE_ENABLED,
        UI_STATISTICS_CHART_VISIBLE,
        UI_HOT_KEY_HINTS,
        MULTITHREADING,
        POSTPROCESSING,
        PP_GRAPHICS_SCALE,
        PP_CLEAN_DETAILED_MODE,
        PP_EFFECTS_SCALE,
        PARTICLE_COUNT,
        SHOW_BONUS_SELECTION_IMMEDIATELY,
        TT_MODIFIER_TAB_SHOWN,
        GL3ASWN,
        DAMAGE_PARTICLES_ENABLED,
        MUSIC_SPECTRUM_ENABLED,
        DAMAGE_PARTICLES_MORE,
        DBG_DISABLE_PATH_RENDERING,
        DBG_SHOW_TILE_COORDINATES_IN_MENU,
        DBG_DISABLE_TILE_HOVERING,
        DBG_ALWAYS_DRAW_TILE_EXTRAS,
        DBG_CONSOLE_DISABLED,
        DBG_CONSOLE_LINE_COUNT,
        DBG_DRAW_TOWER_XP,
        DBG_DRAW_ENEMIES_INFO,
        DBG_DRAW_UNITS_BBOX,
        DBG_DRAW_ENEMIES_AABB,
        DBG_DRAW_PROBLEMATIC_PROJECTILES,
        DBG_DRAW_ENEMIES_COUNT_AABB,
        DBG_DRAW_CURSOR_POS,
        DBG_DRAW_TILE_POS,
        DBG_DRAW_PATHFINDING,
        DBG_DRAW_TILE_INFO,
        DBG_DRAW_BUILDING_INFO,
        DBG_DRAW_ENEMY_PATHS,
        DBG_VIEWPORT_CULLING,
        DBG_DISABLE_UI_TOUCH_LOG,
        DBG_SYNC_VALIDATION,
        DBG_SHOW_FPS,
        DBG_PERFORMANCE_SURVEY_REQUESTED,
        DBG_PERFORMANCE_SURVEY_SUBMITTED,
        DBG_RAYCASTS,
        DBG_DIRTY_TILES,
        DBG_COLLISIONS,
        DBG_DISABLE_ADDITIVE_BLENDING,
        DBG_PRINT_FULL_LUA_STACKTRACES,
        DBG_SIMULATE_VISIBLE_DISPLAY_FRAME,
        DBG_BETA_ITEMS_ISSUE_TS,
        BUG_REPORTS_ENABLED,
        EXPLOSIONS_DRAWING,
        PROJECTILES_DRAWING,
        PROJECTILE_TRAILS_DRAWING,
        PARTICLES_DRAWING,
        UI_ANIMATIONS_ENABLED,
        FLYING_COINS_ENABLED,
        LARGE_FONTS_ENABLED,
        DEBUG_MODE,
        DEBUG_DETAILED_MODE,
        INSTANT_AUTO_WAVE_CALL,
        STAINS_ENABLED,
        THREE_DEE_MODELS_ENABLED;

        public static final CustomValueType[] values = values();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/SettingsManager$DynamicSetting.class */
    public enum DynamicSetting {
        IAP_DOUBLE_GAIN_ENABLED,
        IAP_GREEN_PAPER_ENABLED,
        IAP_ACCELERATOR_ENABLED,
        IAP_OFFERS,
        IAP_SPECIAL_OFFER,
        ADS_ENABLED,
        IOS_OKJOY_SECRET_CODE_ENABLED,
        WIKI_URL,
        IAP_GREEN_PAPER_MAX_PER_HOUR,
        MULTIPLAYER_TEST_ENDPOINT,
        REWARDING_ADS_MODE,
        IAP_NOT_AVAILABLE_IN_COUNTRY,
        ANALYTICS_HOST,
        ANALYTICS_TOKEN,
        ANALYTICS_BUCKET,
        ANALYTICS_ORG
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/SettingsManager$HotkeyAction.class */
    public enum HotkeyAction {
        CALL_WAVE,
        TOGGLE_AUTO_WAVE_CALL,
        PAUSE_GAME,
        SPEED_UP,
        SPEED_DOWN,
        SWITCH_DRAW_MODE,
        TOGGLE_QUEST_LIST,
        TOGGLE_TILE_MENU,
        TOGGLE_STATS_PANE,
        TOGGLE_LEADERBOARD,
        ZOOM_1X,
        ZOOM_FIT_MAP,
        PANIC,
        ABILITY_1,
        ABILITY_2,
        ABILITY_3,
        ABILITY_4,
        ABILITY_5,
        ABILITY_6,
        UPGRADE_BUILDING,
        UPGRADE_ALL_BUILDINGS,
        TOGGLE_TOWER_ENABLED,
        SELL_BUILDING,
        AIM_MODE_SWITCH_NEXT,
        AIM_MODE_SWITCH_PREVIOUS,
        BUILD_TOWER_BASIC,
        BUILD_TOWER_SNIPER,
        BUILD_TOWER_CANNON,
        BUILD_TOWER_FREEZING,
        BUILD_TOWER_AIR,
        BUILD_TOWER_SPLASH,
        BUILD_TOWER_BLAST,
        BUILD_TOWER_MULTISHOT,
        BUILD_TOWER_MINIGUN,
        BUILD_TOWER_VENOM,
        BUILD_TOWER_TESLA,
        BUILD_TOWER_MISSILE,
        BUILD_TOWER_FLAMETHROWER,
        BUILD_TOWER_LASER,
        BUILD_TOWER_GAUSS,
        BUILD_TOWER_CRUSHER,
        TOWER_ABILITY_1,
        TOWER_ABILITY_2,
        TOWER_ABILITY_3,
        TOWER_ABILITY_4,
        TOWER_ABILITY_5,
        TOWER_ABILITY_6,
        TOWER_ABILITY_ALL_1,
        TOWER_ABILITY_ALL_2,
        TOWER_ABILITY_ALL_3,
        TOWER_ABILITY_ALL_4,
        TOWER_ABILITY_ALL_5,
        TOWER_ABILITY_ALL_6,
        BUILD_MODIFIER_BALANCE,
        BUILD_MODIFIER_SEARCH,
        BUILD_MODIFIER_POWER,
        BUILD_MODIFIER_DAMAGE,
        BUILD_MODIFIER_ATTACK_SPEED,
        BUILD_MODIFIER_MINING_SPEED,
        BUILD_MODIFIER_BOUNTY,
        BUILD_MODIFIER_EXPERIENCE,
        BUILD_MINER_SCALAR,
        BUILD_MINER_VECTOR,
        BUILD_MINER_MATRIX,
        BUILD_MINER_TENSOR,
        BUILD_MINER_INFIAR;

        public static HotkeyAction[] values = values();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/SettingsManager$InitConfig.class */
    public enum InitConfig {
        GRAPHICS_VSYNC,
        GRAPHICS_FPS_LIMIT,
        GRAPHICS_AA_LEVELS,
        GRAPHICS_FS_ENABLED,
        GRAPHICS_FS_BORDERLESS,
        GRAPHICS_FS_WIDTH,
        GRAPHICS_FS_HEIGHT,
        GRAPHICS_PAUSE_ON_MIN,
        GRAPHICS_PAUSE_ON_BACK,
        GRAPHICS_ALLOW_SOFTWARE,
        GRAPHICS_SAFE_AREA,
        GRAPHICS_BASIS_TEXTURES,
        AUDIO_BUFFER_SIZE,
        AUDIO_BUFFER_COUNT,
        AUDIO_SIM_SOURCES;

        public static final InitConfig[] values = values();
    }

    /* JADX WARN: Type inference failed for: r0v8, types: [int[], int[][]] */
    static {
        k = !SettingsManager.class.desiredAssertionStatus();
        f2454a = TLog.forClass(SettingsManager.class);
        ?? r0 = new int[HotkeyAction.values.length];
        DEFAULT_HOT_KEYS = r0;
        int ordinal = HotkeyAction.CALL_WAVE.ordinal();
        int[] iArr = new int[1];
        iArr[0] = 62;
        r0[ordinal] = iArr;
        int[][] iArr2 = DEFAULT_HOT_KEYS;
        int ordinal2 = HotkeyAction.TOGGLE_AUTO_WAVE_CALL.ordinal();
        int[] iArr3 = new int[2];
        iArr3[0] = 129;
        iArr3[1] = 62;
        iArr2[ordinal2] = iArr3;
        int[][] iArr4 = DEFAULT_HOT_KEYS;
        int ordinal3 = HotkeyAction.PAUSE_GAME.ordinal();
        int[] iArr5 = new int[1];
        iArr5[0] = 44;
        iArr4[ordinal3] = iArr5;
        int[][] iArr6 = DEFAULT_HOT_KEYS;
        int ordinal4 = HotkeyAction.SPEED_UP.ordinal();
        int[] iArr7 = new int[1];
        iArr7[0] = 72;
        iArr6[ordinal4] = iArr7;
        int[][] iArr8 = DEFAULT_HOT_KEYS;
        int ordinal5 = HotkeyAction.SPEED_DOWN.ordinal();
        int[] iArr9 = new int[1];
        iArr9[0] = 71;
        iArr8[ordinal5] = iArr9;
        int[][] iArr10 = DEFAULT_HOT_KEYS;
        int ordinal6 = HotkeyAction.SWITCH_DRAW_MODE.ordinal();
        int[] iArr11 = new int[1];
        iArr11[0] = 41;
        iArr10[ordinal6] = iArr11;
        int[][] iArr12 = DEFAULT_HOT_KEYS;
        int ordinal7 = HotkeyAction.TOGGLE_TILE_MENU.ordinal();
        int[] iArr13 = new int[1];
        iArr13[0] = 61;
        iArr12[ordinal7] = iArr13;
        int[][] iArr14 = DEFAULT_HOT_KEYS;
        int ordinal8 = HotkeyAction.TOGGLE_QUEST_LIST.ordinal();
        int[] iArr15 = new int[1];
        iArr15[0] = 7;
        iArr14[ordinal8] = iArr15;
        int[][] iArr16 = DEFAULT_HOT_KEYS;
        int ordinal9 = HotkeyAction.TOGGLE_STATS_PANE.ordinal();
        int[] iArr17 = new int[1];
        iArr17[0] = 69;
        iArr16[ordinal9] = iArr17;
        int[][] iArr18 = DEFAULT_HOT_KEYS;
        int ordinal10 = HotkeyAction.TOGGLE_LEADERBOARD.ordinal();
        int[] iArr19 = new int[1];
        iArr19[0] = 40;
        iArr18[ordinal10] = iArr19;
        int[][] iArr20 = DEFAULT_HOT_KEYS;
        int ordinal11 = HotkeyAction.ZOOM_1X.ordinal();
        int[] iArr21 = new int[1];
        iArr21[0] = 131;
        iArr20[ordinal11] = iArr21;
        int[][] iArr22 = DEFAULT_HOT_KEYS;
        int ordinal12 = HotkeyAction.ZOOM_FIT_MAP.ordinal();
        int[] iArr23 = new int[1];
        iArr23[0] = 132;
        iArr22[ordinal12] = iArr23;
        int[][] iArr24 = DEFAULT_HOT_KEYS;
        int ordinal13 = HotkeyAction.PANIC.ordinal();
        int[] iArr25 = new int[1];
        iArr25[0] = 121;
        iArr24[ordinal13] = iArr25;
        int[][] iArr26 = DEFAULT_HOT_KEYS;
        int ordinal14 = HotkeyAction.ABILITY_1.ordinal();
        int[] iArr27 = new int[1];
        iArr27[0] = 54;
        iArr26[ordinal14] = iArr27;
        int[][] iArr28 = DEFAULT_HOT_KEYS;
        int ordinal15 = HotkeyAction.ABILITY_2.ordinal();
        int[] iArr29 = new int[1];
        iArr29[0] = 52;
        iArr28[ordinal15] = iArr29;
        int[][] iArr30 = DEFAULT_HOT_KEYS;
        int ordinal16 = HotkeyAction.ABILITY_3.ordinal();
        int[] iArr31 = new int[1];
        iArr31[0] = 29;
        iArr30[ordinal16] = iArr31;
        int[][] iArr32 = DEFAULT_HOT_KEYS;
        int ordinal17 = HotkeyAction.ABILITY_4.ordinal();
        int[] iArr33 = new int[1];
        iArr33[0] = 47;
        iArr32[ordinal17] = iArr33;
        int[][] iArr34 = DEFAULT_HOT_KEYS;
        int ordinal18 = HotkeyAction.ABILITY_5.ordinal();
        int[] iArr35 = new int[1];
        iArr35[0] = 45;
        iArr34[ordinal18] = iArr35;
        int[][] iArr36 = DEFAULT_HOT_KEYS;
        int ordinal19 = HotkeyAction.ABILITY_6.ordinal();
        int[] iArr37 = new int[1];
        iArr37[0] = 51;
        iArr36[ordinal19] = iArr37;
        int[][] iArr38 = DEFAULT_HOT_KEYS;
        int ordinal20 = HotkeyAction.UPGRADE_BUILDING.ordinal();
        int[] iArr39 = new int[1];
        iArr39[0] = 59;
        iArr38[ordinal20] = iArr39;
        int[][] iArr40 = DEFAULT_HOT_KEYS;
        int ordinal21 = HotkeyAction.TOGGLE_TOWER_ENABLED.ordinal();
        int[] iArr41 = new int[1];
        iArr41[0] = 123;
        iArr40[ordinal21] = iArr41;
        int[][] iArr42 = DEFAULT_HOT_KEYS;
        int ordinal22 = HotkeyAction.UPGRADE_ALL_BUILDINGS.ordinal();
        int[] iArr43 = new int[2];
        iArr43[0] = 129;
        iArr43[1] = 59;
        iArr42[ordinal22] = iArr43;
        int[][] iArr44 = DEFAULT_HOT_KEYS;
        int ordinal23 = HotkeyAction.SELL_BUILDING.ordinal();
        int[] iArr45 = new int[1];
        iArr45[0] = 67;
        iArr44[ordinal23] = iArr45;
        int[][] iArr46 = DEFAULT_HOT_KEYS;
        int ordinal24 = HotkeyAction.AIM_MODE_SWITCH_PREVIOUS.ordinal();
        int[] iArr47 = new int[1];
        iArr47[0] = 55;
        iArr46[ordinal24] = iArr47;
        int[][] iArr48 = DEFAULT_HOT_KEYS;
        int ordinal25 = HotkeyAction.AIM_MODE_SWITCH_NEXT.ordinal();
        int[] iArr49 = new int[1];
        iArr49[0] = 56;
        iArr48[ordinal25] = iArr49;
        int[][] iArr50 = DEFAULT_HOT_KEYS;
        int ordinal26 = HotkeyAction.TOWER_ABILITY_1.ordinal();
        int[] iArr51 = new int[1];
        iArr51[0] = 145;
        iArr50[ordinal26] = iArr51;
        int[][] iArr52 = DEFAULT_HOT_KEYS;
        int ordinal27 = HotkeyAction.TOWER_ABILITY_2.ordinal();
        int[] iArr53 = new int[1];
        iArr53[0] = 146;
        iArr52[ordinal27] = iArr53;
        int[][] iArr54 = DEFAULT_HOT_KEYS;
        int ordinal28 = HotkeyAction.TOWER_ABILITY_3.ordinal();
        int[] iArr55 = new int[1];
        iArr55[0] = 147;
        iArr54[ordinal28] = iArr55;
        int[][] iArr56 = DEFAULT_HOT_KEYS;
        int ordinal29 = HotkeyAction.TOWER_ABILITY_4.ordinal();
        int[] iArr57 = new int[1];
        iArr57[0] = 148;
        iArr56[ordinal29] = iArr57;
        int[][] iArr58 = DEFAULT_HOT_KEYS;
        int ordinal30 = HotkeyAction.TOWER_ABILITY_5.ordinal();
        int[] iArr59 = new int[1];
        iArr59[0] = 149;
        iArr58[ordinal30] = iArr59;
        int[][] iArr60 = DEFAULT_HOT_KEYS;
        int ordinal31 = HotkeyAction.TOWER_ABILITY_6.ordinal();
        int[] iArr61 = new int[1];
        iArr61[0] = 150;
        iArr60[ordinal31] = iArr61;
        int[][] iArr62 = DEFAULT_HOT_KEYS;
        int ordinal32 = HotkeyAction.TOWER_ABILITY_ALL_1.ordinal();
        int[] iArr63 = new int[2];
        iArr63[0] = 129;
        iArr63[1] = 145;
        iArr62[ordinal32] = iArr63;
        int[][] iArr64 = DEFAULT_HOT_KEYS;
        int ordinal33 = HotkeyAction.TOWER_ABILITY_ALL_2.ordinal();
        int[] iArr65 = new int[2];
        iArr65[0] = 129;
        iArr65[1] = 146;
        iArr64[ordinal33] = iArr65;
        int[][] iArr66 = DEFAULT_HOT_KEYS;
        int ordinal34 = HotkeyAction.TOWER_ABILITY_ALL_3.ordinal();
        int[] iArr67 = new int[2];
        iArr67[0] = 129;
        iArr67[1] = 147;
        iArr66[ordinal34] = iArr67;
        int[][] iArr68 = DEFAULT_HOT_KEYS;
        int ordinal35 = HotkeyAction.TOWER_ABILITY_ALL_4.ordinal();
        int[] iArr69 = new int[2];
        iArr69[0] = 129;
        iArr69[1] = 148;
        iArr68[ordinal35] = iArr69;
        int[][] iArr70 = DEFAULT_HOT_KEYS;
        int ordinal36 = HotkeyAction.TOWER_ABILITY_ALL_5.ordinal();
        int[] iArr71 = new int[2];
        iArr71[0] = 129;
        iArr71[1] = 149;
        iArr70[ordinal36] = iArr71;
        int[][] iArr72 = DEFAULT_HOT_KEYS;
        int ordinal37 = HotkeyAction.TOWER_ABILITY_ALL_6.ordinal();
        int[] iArr73 = new int[2];
        iArr73[0] = 129;
        iArr73[1] = 150;
        iArr72[ordinal37] = iArr73;
        int[][] iArr74 = DEFAULT_HOT_KEYS;
        int ordinal38 = HotkeyAction.BUILD_TOWER_BASIC.ordinal();
        int[] iArr75 = new int[1];
        iArr75[0] = 10;
        iArr74[ordinal38] = iArr75;
        int[][] iArr76 = DEFAULT_HOT_KEYS;
        int ordinal39 = HotkeyAction.BUILD_TOWER_SNIPER.ordinal();
        int[] iArr77 = new int[1];
        iArr77[0] = 11;
        iArr76[ordinal39] = iArr77;
        int[][] iArr78 = DEFAULT_HOT_KEYS;
        int ordinal40 = HotkeyAction.BUILD_TOWER_CANNON.ordinal();
        int[] iArr79 = new int[1];
        iArr79[0] = 12;
        iArr78[ordinal40] = iArr79;
        int[][] iArr80 = DEFAULT_HOT_KEYS;
        int ordinal41 = HotkeyAction.BUILD_TOWER_FREEZING.ordinal();
        int[] iArr81 = new int[1];
        iArr81[0] = 13;
        iArr80[ordinal41] = iArr81;
        int[][] iArr82 = DEFAULT_HOT_KEYS;
        int ordinal42 = HotkeyAction.BUILD_TOWER_AIR.ordinal();
        int[] iArr83 = new int[1];
        iArr83[0] = 33;
        iArr82[ordinal42] = iArr83;
        int[][] iArr84 = DEFAULT_HOT_KEYS;
        int ordinal43 = HotkeyAction.BUILD_TOWER_SPLASH.ordinal();
        int[] iArr85 = new int[1];
        iArr85[0] = 46;
        iArr84[ordinal43] = iArr85;
        int[][] iArr86 = DEFAULT_HOT_KEYS;
        int ordinal44 = HotkeyAction.BUILD_TOWER_BLAST.ordinal();
        int[] iArr87 = new int[1];
        iArr87[0] = 48;
        iArr86[ordinal44] = iArr87;
        int[][] iArr88 = DEFAULT_HOT_KEYS;
        int ordinal45 = HotkeyAction.BUILD_TOWER_MULTISHOT.ordinal();
        int[] iArr89 = new int[1];
        iArr89[0] = 53;
        iArr88[ordinal45] = iArr89;
        int[][] iArr90 = DEFAULT_HOT_KEYS;
        int ordinal46 = HotkeyAction.BUILD_TOWER_MINIGUN.ordinal();
        int[] iArr91 = new int[1];
        iArr91[0] = 32;
        iArr90[ordinal46] = iArr91;
        int[][] iArr92 = DEFAULT_HOT_KEYS;
        int ordinal47 = HotkeyAction.BUILD_TOWER_VENOM.ordinal();
        int[] iArr93 = new int[1];
        iArr93[0] = 34;
        iArr92[ordinal47] = iArr93;
        int[][] iArr94 = DEFAULT_HOT_KEYS;
        int ordinal48 = HotkeyAction.BUILD_TOWER_TESLA.ordinal();
        int[] iArr95 = new int[1];
        iArr95[0] = 35;
        iArr94[ordinal48] = iArr95;
        int[][] iArr96 = DEFAULT_HOT_KEYS;
        int ordinal49 = HotkeyAction.BUILD_TOWER_MISSILE.ordinal();
        int[] iArr97 = new int[1];
        iArr97[0] = 36;
        iArr96[ordinal49] = iArr97;
        int[][] iArr98 = DEFAULT_HOT_KEYS;
        int ordinal50 = HotkeyAction.BUILD_TOWER_FLAMETHROWER.ordinal();
        int[] iArr99 = new int[1];
        iArr99[0] = 31;
        iArr98[ordinal50] = iArr99;
        int[][] iArr100 = DEFAULT_HOT_KEYS;
        int ordinal51 = HotkeyAction.BUILD_TOWER_LASER.ordinal();
        int[] iArr101 = new int[1];
        iArr101[0] = 50;
        iArr100[ordinal51] = iArr101;
        int[][] iArr102 = DEFAULT_HOT_KEYS;
        int ordinal52 = HotkeyAction.BUILD_TOWER_GAUSS.ordinal();
        int[] iArr103 = new int[1];
        iArr103[0] = 30;
        iArr102[ordinal52] = iArr103;
        int[][] iArr104 = DEFAULT_HOT_KEYS;
        int ordinal53 = HotkeyAction.BUILD_TOWER_CRUSHER.ordinal();
        int[] iArr105 = new int[1];
        iArr105[0] = 42;
        iArr104[ordinal53] = iArr105;
        int[][] iArr106 = DEFAULT_HOT_KEYS;
        int ordinal54 = HotkeyAction.BUILD_MODIFIER_BALANCE.ordinal();
        int[] iArr107 = new int[2];
        iArr107[0] = 129;
        iArr107[1] = 10;
        iArr106[ordinal54] = iArr107;
        int[][] iArr108 = DEFAULT_HOT_KEYS;
        int ordinal55 = HotkeyAction.BUILD_MODIFIER_SEARCH.ordinal();
        int[] iArr109 = new int[2];
        iArr109[0] = 129;
        iArr109[1] = 11;
        iArr108[ordinal55] = iArr109;
        int[][] iArr110 = DEFAULT_HOT_KEYS;
        int ordinal56 = HotkeyAction.BUILD_MODIFIER_POWER.ordinal();
        int[] iArr111 = new int[2];
        iArr111[0] = 129;
        iArr111[1] = 12;
        iArr110[ordinal56] = iArr111;
        int[][] iArr112 = DEFAULT_HOT_KEYS;
        int ordinal57 = HotkeyAction.BUILD_MODIFIER_DAMAGE.ordinal();
        int[] iArr113 = new int[2];
        iArr113[0] = 129;
        iArr113[1] = 13;
        iArr112[ordinal57] = iArr113;
        int[][] iArr114 = DEFAULT_HOT_KEYS;
        int ordinal58 = HotkeyAction.BUILD_MODIFIER_ATTACK_SPEED.ordinal();
        int[] iArr115 = new int[2];
        iArr115[0] = 129;
        iArr115[1] = 33;
        iArr114[ordinal58] = iArr115;
        int[][] iArr116 = DEFAULT_HOT_KEYS;
        int ordinal59 = HotkeyAction.BUILD_MODIFIER_MINING_SPEED.ordinal();
        int[] iArr117 = new int[2];
        iArr117[0] = 129;
        iArr117[1] = 46;
        iArr116[ordinal59] = iArr117;
        int[][] iArr118 = DEFAULT_HOT_KEYS;
        int ordinal60 = HotkeyAction.BUILD_MODIFIER_BOUNTY.ordinal();
        int[] iArr119 = new int[2];
        iArr119[0] = 129;
        iArr119[1] = 48;
        iArr118[ordinal60] = iArr119;
        int[][] iArr120 = DEFAULT_HOT_KEYS;
        int ordinal61 = HotkeyAction.BUILD_MODIFIER_EXPERIENCE.ordinal();
        int[] iArr121 = new int[2];
        iArr121[0] = 129;
        iArr121[1] = 53;
        iArr120[ordinal61] = iArr121;
        int[][] iArr122 = DEFAULT_HOT_KEYS;
        int ordinal62 = HotkeyAction.BUILD_MINER_SCALAR.ordinal();
        int[] iArr123 = new int[1];
        iArr123[0] = 10;
        iArr122[ordinal62] = iArr123;
        int[][] iArr124 = DEFAULT_HOT_KEYS;
        int ordinal63 = HotkeyAction.BUILD_MINER_VECTOR.ordinal();
        int[] iArr125 = new int[1];
        iArr125[0] = 11;
        iArr124[ordinal63] = iArr125;
        int[][] iArr126 = DEFAULT_HOT_KEYS;
        int ordinal64 = HotkeyAction.BUILD_MINER_MATRIX.ordinal();
        int[] iArr127 = new int[1];
        iArr127[0] = 12;
        iArr126[ordinal64] = iArr127;
        int[][] iArr128 = DEFAULT_HOT_KEYS;
        int ordinal65 = HotkeyAction.BUILD_MINER_TENSOR.ordinal();
        int[] iArr129 = new int[1];
        iArr129[0] = 13;
        iArr128[ordinal65] = iArr129;
        int[][] iArr130 = DEFAULT_HOT_KEYS;
        int ordinal66 = HotkeyAction.BUILD_MINER_INFIAR.ordinal();
        int[] iArr131 = new int[1];
        iArr131[0] = 33;
        iArr130[ordinal66] = iArr131;
        i = new Array<>(HotkeyAction.class);
    }

    public SettingsManager() {
        this.c[DynamicSetting.IAP_DOUBLE_GAIN_ENABLED.ordinal()] = "true";
        this.c[DynamicSetting.IAP_GREEN_PAPER_ENABLED.ordinal()] = "true";
        this.c[DynamicSetting.IAP_ACCELERATOR_ENABLED.ordinal()] = "true";
        this.c[DynamicSetting.IAP_OFFERS.ordinal()] = "";
        this.c[DynamicSetting.IOS_OKJOY_SECRET_CODE_ENABLED.ordinal()] = "false";
        this.c[DynamicSetting.ADS_ENABLED.ordinal()] = "true";
        this.c[DynamicSetting.WIKI_URL.ordinal()] = Config.WIKI_URL;
        this.c[DynamicSetting.IAP_GREEN_PAPER_MAX_PER_HOUR.ordinal()] = "1000000";
        this.c[DynamicSetting.MULTIPLAYER_TEST_ENDPOINT.ordinal()] = "null";
        this.c[DynamicSetting.REWARDING_ADS_MODE.ordinal()] = "admob,appodeal";
        System.arraycopy(this.c, 0, this.f2455b, 0, this.f2455b.length);
        this.d = new String[DynamicSetting.values().length];
        this.e = -1;
        this.g = new IntArray();
        this.h = new IntArray();
        this.j = new DelayedRemovalArray<>(false, 1);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/SettingsManager$Serializer.class */
    public static class Serializer extends SingletonSerializer<SettingsManager> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public SettingsManager read() {
            return Game.i.settingsManager;
        }
    }

    public boolean isEscButtonJustPressed() {
        if (Dialog.i().isVisible() || TextInputOverlay.i().isVisible()) {
            return false;
        }
        return Gdx.input.isKeyJustPressed(4) || Gdx.input.isKeyJustPressed(111);
    }

    public boolean isSecretCodesEnabled() {
        return true;
    }

    @Null
    public String getDynamicSetting(DynamicSetting dynamicSetting) {
        return this.f2455b[dynamicSetting.ordinal()];
    }

    public int getScaledViewportHeight() {
        double customValue = getCustomValue(CustomValueType.UI_SCALE);
        double d = customValue;
        if (customValue < 0.5d) {
            d = 0.5d;
        }
        if (d > 1.0d) {
            d = 1.0d;
        }
        return MathUtils.round(1080.0f * ((float) (1.0d / d)));
    }

    public int[] getHotKey(HotkeyAction hotkeyAction) {
        if (SettingsPrefs.i().settings.hotKeys[hotkeyAction.ordinal()] != null) {
            return SettingsPrefs.i().settings.hotKeys[hotkeyAction.ordinal()];
        }
        return getDefaultHotKey(hotkeyAction);
    }

    public int[] getDefaultHotKey(HotkeyAction hotkeyAction) {
        return DEFAULT_HOT_KEYS[hotkeyAction.ordinal()];
    }

    public void setHotKey(HotkeyAction hotkeyAction, int[] iArr) {
        int[] defaultHotKey = getDefaultHotKey(hotkeyAction);
        if (iArr == null) {
            iArr = defaultHotKey;
        }
        if (a(defaultHotKey, iArr) && SettingsPrefs.i().settings.hotKeys[hotkeyAction.ordinal()] != null) {
            SettingsPrefs.i().settings.hotKeys[hotkeyAction.ordinal()] = null;
            SettingsPrefs.i().requireSave();
            this.f = null;
            this.g.clear();
            this.h.clear();
            return;
        }
        if (SettingsPrefs.i().settings.hotKeys[hotkeyAction.ordinal()] != null && a(iArr, SettingsPrefs.i().settings.hotKeys[hotkeyAction.ordinal()])) {
            return;
        }
        SettingsPrefs.i().settings.hotKeys[hotkeyAction.ordinal()] = iArr;
        this.f = null;
        this.g.clear();
        this.h.clear();
        SettingsPrefs.i().requireSave();
    }

    private static boolean a(int[] iArr, int[] iArr2) {
        if (iArr.length != iArr2.length) {
            return false;
        }
        Arrays.sort(iArr);
        Arrays.sort(iArr2);
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (iArr[i2] != iArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public boolean isDefaultHotKey(HotkeyAction hotkeyAction) {
        return SettingsPrefs.i().settings.hotKeys[hotkeyAction.ordinal()] == null;
    }

    private int[] b() {
        if (this.f == null) {
            IntArray intArray = new IntArray();
            for (HotkeyAction hotkeyAction : HotkeyAction.values) {
                int[] hotKey = getHotKey(hotkeyAction);
                if (hotKey != null) {
                    for (int i2 : hotKey) {
                        if (!intArray.contains(i2)) {
                            intArray.add(i2);
                        }
                    }
                }
            }
            intArray.sort();
            this.f = intArray.toArray();
        }
        return this.f;
    }

    public Array<HotkeyAction> getHotKeyActions(IntArray intArray) {
        int i2;
        i.clear();
        for (HotkeyAction hotkeyAction : HotkeyAction.values) {
            int[] hotKey = getHotKey(hotkeyAction);
            if (hotKey != null && intArray.size == hotKey.length) {
                int length = hotKey.length;
                while (true) {
                    if (i2 < length) {
                        int i3 = hotKey[i2];
                        boolean z = false;
                        int i4 = 0;
                        while (true) {
                            if (i4 >= intArray.size) {
                                break;
                            }
                            if (intArray.items[i4] != i3) {
                                i4++;
                            } else {
                                z = true;
                                break;
                            }
                        }
                        i2 = z ? i2 + 1 : 0;
                    } else {
                        i.add(hotkeyAction);
                        break;
                    }
                }
            }
        }
        return i;
    }

    public Array<HotkeyAction> getHotkeysJustPressed() {
        int i2;
        i.clear();
        if (this.g.size == 0) {
            return i;
        }
        for (HotkeyAction hotkeyAction : HotkeyAction.values) {
            int[] hotKey = getHotKey(hotkeyAction);
            if (hotKey != null && this.g.size == hotKey.length) {
                int length = hotKey.length;
                while (true) {
                    if (i2 < length) {
                        i2 = this.g.contains(hotKey[i2]) ? i2 + 1 : 0;
                    } else {
                        i.add(hotkeyAction);
                        break;
                    }
                }
            }
        }
        return i;
    }

    public boolean isHotkeyJustPressed(HotkeyAction hotkeyAction) {
        int[] iArr = DEFAULT_HOT_KEYS[hotkeyAction.ordinal()];
        if (this.g.size == iArr.length) {
            for (int i2 : iArr) {
                if (!this.g.contains(i2)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public String getHotKeyName(HotkeyAction hotkeyAction) {
        return Game.i.localeManager.i18n.get("hotkey_" + hotkeyAction.name());
    }

    public String getHotkeyGroupTitle(HotkeyAction hotkeyAction) {
        switch (hotkeyAction) {
            case CALL_WAVE:
                return Game.i.localeManager.i18n.get("hotkey_group_general");
            case ABILITY_1:
                return Game.i.localeManager.i18n.get("hotkey_group_abilities");
            case UPGRADE_BUILDING:
                return Game.i.localeManager.i18n.get("hotkey_group_buildings");
            case BUILD_TOWER_BASIC:
                return Game.i.localeManager.i18n.get("hotkey_group_towers");
            case TOWER_ABILITY_1:
                return Game.i.localeManager.i18n.get("hotkey_group_tower_abilities");
            case BUILD_MODIFIER_BALANCE:
                return Game.i.localeManager.i18n.get("hotkey_group_modifiers");
            case BUILD_MINER_SCALAR:
                return Game.i.localeManager.i18n.get("hotkey_group_miners");
            default:
                return null;
        }
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void setup() {
        if (!Config.isHeadless()) {
            c();
            d();
        }
        Timer.schedule(new Timer.Task() { // from class: com.prineside.tdi2.managers.SettingsManager.1
            @Override // com.badlogic.gdx.utils.Timer.Task, java.lang.Runnable
            public void run() {
                SettingsManager.this.d();
            }
        }, 300.0f, 300.0f);
        if (isThreeDeeModelsEnabled()) {
            f2454a.i("preloading main menu scene", new Object[0]);
            Game.i.assetManager.getSceneModel(model -> {
                f2454a.i("main menu scene preloaded", new Object[0]);
            });
        }
    }

    public static Graphics.DisplayMode getBestFullscreenModeWithPrefDimensions(int i2, int i3, int i4, int i5) {
        f2454a.i("getBestFullscreenMode " + i2 + SequenceUtils.SPACE + i3 + SequenceUtils.SPACE + i4 + SequenceUtils.SPACE + i5, new Object[0]);
        int i6 = i2 > 1 ? i2 : i4;
        int i7 = i3 > 1 ? i3 : i5;
        Graphics.DisplayMode displayMode = null;
        Graphics.DisplayMode[] displayModes = Gdx.graphics.getDisplayModes();
        int i8 = 0;
        for (Graphics.DisplayMode displayMode2 : displayModes) {
            if (displayMode2.bitsPerPixel > i8) {
                i8 = displayMode2.bitsPerPixel;
            }
        }
        if (i6 > 1 && i7 > 1) {
            for (Graphics.DisplayMode displayMode3 : displayModes) {
                if (displayMode3.width == i6 && displayMode3.height == i7 && displayMode3.bitsPerPixel >= i8 && (displayMode == null || displayMode.refreshRate < displayMode3.refreshRate)) {
                    displayMode = displayMode3;
                }
            }
        }
        if (displayMode == null) {
            for (Graphics.DisplayMode displayMode4 : displayModes) {
                if (displayMode4.bitsPerPixel >= i8 && (displayMode == null || ((displayMode.width < displayMode4.width || displayMode.height < displayMode4.height) && displayMode.refreshRate <= displayMode4.refreshRate))) {
                    displayMode = displayMode4;
                }
            }
        }
        f2454a.i("best fullscreen mode: " + displayMode, new Object[0]);
        return displayMode;
    }

    public static Graphics.DisplayMode getBestFullscreenMode(int i2, int i3) {
        IntPair bestScreenResolution = Game.i.actionResolver.getBestScreenResolution();
        IntPair intPair = bestScreenResolution;
        if (bestScreenResolution == null) {
            intPair = new IntPair(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }
        return getBestFullscreenModeWithPrefDimensions(i2, i3, intPair.f3850a, intPair.f3851b);
    }

    private boolean c() {
        FileHandle local = Gdx.files.local("cache/dynamic-settings.json");
        if (local.exists()) {
            try {
                a(new JsonReader().parse(local.readString("UTF-8")));
                return true;
            } catch (Exception e) {
                f2454a.e("failed to load local dynamic settings, clearing", e);
                try {
                    local.delete();
                    return false;
                } catch (Exception unused) {
                    return false;
                }
            }
        }
        return false;
    }

    public boolean cvdFriendlyColors() {
        return Game.i.settingsManager.getCustomValue(CustomValueType.COLORBLIND_MODE) != 0.0d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
        httpRequest.setUrl(Config.DYNAMIC_SETTINGS_URL);
        HashMap hashMap = new HashMap();
        hashMap.put("os", Gdx.app.getType().name());
        httpRequest.setContent(HttpParametersUtils.convertHttpParameters(hashMap));
        Gdx.f881net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() { // from class: com.prineside.tdi2.managers.SettingsManager.2
            @Override // com.badlogic.gdx.Net.HttpResponseListener
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                try {
                    String resultAsString = httpResponse.getResultAsString();
                    try {
                        Gdx.files.local("cache/dynamic-settings.json").writeString(resultAsString, false, "UTF-8");
                    } catch (Exception e) {
                        SettingsManager.f2454a.e("failed to cache dynamic settings locally", e);
                    }
                    try {
                        SettingsManager.this.a(new JsonReader().parse(resultAsString));
                    } catch (Exception e2) {
                        SettingsManager.f2454a.e("failed to load server's dynamic settings after response", e2);
                    }
                } catch (Exception e3) {
                    SettingsManager.f2454a.e("failed to load server's dynamic settings", e3);
                }
            }

            @Override // com.badlogic.gdx.Net.HttpResponseListener
            public void failed(Throwable th) {
                SettingsManager.f2454a.i("failed to load dynamic settings from server", th);
            }

            @Override // com.badlogic.gdx.Net.HttpResponseListener
            public void cancelled() {
                SettingsManager.f2454a.i("canceled loading dynamic settings from server", new Object[0]);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x0192, code lost:            r8 = true;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void a(com.badlogic.gdx.utils.JsonValue r8) {
        /*
            Method dump skipped, instructions count: 444
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.managers.SettingsManager.a(com.badlogic.gdx.utils.JsonValue):void");
    }

    public boolean getBoolCustomValue(CustomValueType customValueType) {
        if (Config.isHeadless()) {
            throw new IllegalStateException("Not available in headless");
        }
        double d = SettingsPrefs.i().settings.customValues[customValueType.ordinal()];
        double d2 = d;
        if (d == -9740019.0d) {
            d2 = SP_Settings.getDefaultCustomValue(customValueType);
        }
        return d2 != 0.0d;
    }

    public double getCustomValue(CustomValueType customValueType) {
        if (Config.isHeadless()) {
            throw new IllegalStateException("Not available in headless");
        }
        double d = SettingsPrefs.i().settings.customValues[customValueType.ordinal()];
        double d2 = d;
        if (d == -9740019.0d) {
            d2 = SP_Settings.getDefaultCustomValue(customValueType);
        }
        return d2;
    }

    public boolean setBoolCustomValue(CustomValueType customValueType, boolean z) {
        return setCustomValue(customValueType, z ? 1.0d : 0.0d);
    }

    public boolean unsetCustomValue(CustomValueType customValueType) {
        return setCustomValue(customValueType, -9740019.0d);
    }

    public boolean setCustomValue(CustomValueType customValueType, double d) {
        double d2 = SettingsPrefs.i().settings.customValues[customValueType.ordinal()];
        if (d2 == d) {
            return false;
        }
        SettingsPrefs.i().settings.customValues[customValueType.ordinal()] = d;
        SettingsPrefs.i().requireSave();
        this.j.begin();
        for (int i2 = 0; i2 < this.j.size; i2++) {
            this.j.get(i2).customValueChanged(customValueType, d2);
        }
        this.j.end();
        e();
        return true;
    }

    public static String getGameSessionFingerprint(String str, String str2) {
        char[] cArr = new char[25];
        int i2 = 0 + 1;
        cArr[0] = str.charAt(0);
        int i3 = 0 + 1 + 1;
        for (int i4 = 0; i4 < 4; i4++) {
            int i5 = i2;
            i2++;
            int i6 = i3;
            i3++;
            cArr[i5] = str.charAt(i6);
        }
        int i7 = i3 + 1;
        for (int i8 = 0; i8 < 4; i8++) {
            int i9 = i2;
            i2++;
            int i10 = i7;
            i7++;
            cArr[i9] = str.charAt(i10);
        }
        int i11 = i7 + 1;
        for (int i12 = 0; i12 < 6; i12++) {
            int i13 = i2;
            i2++;
            int i14 = i11;
            i11++;
            cArr[i13] = str.charAt(i14);
        }
        while (str2.length() < 10) {
            str2 = str2 + 'Z';
        }
        for (int i15 = 0; i15 < 10; i15++) {
            int i16 = i2;
            i2++;
            cArr[i16] = str2.charAt(i15);
        }
        byte[] bArr = new byte[25];
        for (int i17 = 0; i17 < 25; i17++) {
            bArr[i17] = (byte) StringFormatter.DIST_STRING_CHAR_TO_IDX.get(cArr[i17], 0);
        }
        ByteArray byteArray = new ByteArray();
        byte b2 = 0;
        int i18 = 0;
        for (int i19 = 0; i19 < 25; i19++) {
            byte b3 = bArr[i19];
            for (int i20 = 0; i20 < 5; i20++) {
                if (((b3 >> i20) & 1) == 1) {
                    b2 = (byte) (b2 | (1 << i18));
                }
                i18++;
                if (i18 == 8) {
                    byteArray.add(b2);
                    b2 = 0;
                    i18 = 0;
                }
            }
        }
        if (i18 != 0) {
            byteArray.add(b2);
        }
        int[] iArr = {2, 7, 13, 8, 14, 3, 0, 10, 5, 6, 9, 11, 15, 12, 1, 4};
        if (!k && byteArray.size != 16) {
            throw new AssertionError();
        }
        for (int i21 = 0; i21 < 16; i21++) {
            byteArray.swap(i21, iArr[i21]);
        }
        byte[] array = byteArray.toArray();
        return StringFormatter.toBase64(array, 0, array.length);
    }

    public void addListener(SettingsManagerListener settingsManagerListener) {
        if (settingsManagerListener == null) {
            throw new IllegalArgumentException("listener is null");
        }
        if (!this.j.contains(settingsManagerListener, true)) {
            this.j.add(settingsManagerListener);
        }
    }

    public void removeListener(SettingsManagerListener settingsManagerListener) {
        if (settingsManagerListener == null) {
            throw new IllegalArgumentException("listener is null");
        }
        this.j.removeValue(settingsManagerListener, true);
    }

    public void setSoundVoulme(double d) {
        if (d < 0.0d) {
            d = 0.0d;
        }
        if (d > 1.0d) {
            d = 1.0d;
        }
        setCustomValue(CustomValueType.SOUND_VOLUME, d);
    }

    public boolean isSoundEnabled() {
        return getCustomValue(CustomValueType.SOUND_VOLUME) > 0.0d;
    }

    public boolean isMusicEnabled() {
        return getCustomValue(CustomValueType.MUSIC_VOLUME) > 0.0d;
    }

    public void setMusicVolume(double d) {
        if (d < 0.0d) {
            d = 0.0d;
        }
        if (d > 1.0d) {
            d = 1.0d;
        }
        setCustomValue(CustomValueType.MUSIC_VOLUME, d);
    }

    public boolean isBugReportsEnabled() {
        return getBoolCustomValue(CustomValueType.BUG_REPORTS_ENABLED);
    }

    public void setBugReportsEnabled(boolean z) {
        setBoolCustomValue(CustomValueType.BUG_REPORTS_ENABLED, z);
    }

    public void setExplosionsDrawing(boolean z) {
        setBoolCustomValue(CustomValueType.EXPLOSIONS_DRAWING, z);
    }

    public boolean isExplosionsDrawing() {
        return getBoolCustomValue(CustomValueType.EXPLOSIONS_DRAWING);
    }

    public void setProjectilesDrawing(boolean z) {
        setBoolCustomValue(CustomValueType.PROJECTILES_DRAWING, z);
    }

    public boolean isProjectilesDrawing() {
        return getBoolCustomValue(CustomValueType.PROJECTILES_DRAWING);
    }

    public void setProjectileTrailsDrawing(boolean z) {
        setBoolCustomValue(CustomValueType.PROJECTILE_TRAILS_DRAWING, z);
    }

    public boolean isProjectileTrailsDrawing() {
        return getBoolCustomValue(CustomValueType.PROJECTILE_TRAILS_DRAWING);
    }

    public void setParticlesDrawing(boolean z) {
        setBoolCustomValue(CustomValueType.PARTICLES_DRAWING, z);
    }

    public boolean isParticlesDrawing() {
        return getBoolCustomValue(CustomValueType.PARTICLES_DRAWING);
    }

    public void setUiAnimationsEnabled(boolean z) {
        setBoolCustomValue(CustomValueType.UI_ANIMATIONS_ENABLED, z);
    }

    public boolean isUiAnimationsEnabled() {
        return getBoolCustomValue(CustomValueType.UI_ANIMATIONS_ENABLED);
    }

    public void setFlyingCoinsEnabled(boolean z) {
        setBoolCustomValue(CustomValueType.FLYING_COINS_ENABLED, z);
    }

    public boolean isFlyingCoinsEnabled() {
        return getBoolCustomValue(CustomValueType.FLYING_COINS_ENABLED);
    }

    public void setInstantAutoWaveCallEnabled(boolean z) {
        setBoolCustomValue(CustomValueType.INSTANT_AUTO_WAVE_CALL, z);
    }

    public boolean isInstantAutoWaveCallEnabled() {
        return getBoolCustomValue(CustomValueType.INSTANT_AUTO_WAVE_CALL);
    }

    public void setStainsEnabled(boolean z) {
        setBoolCustomValue(CustomValueType.STAINS_ENABLED, z);
    }

    public boolean isStainsEnabled() {
        return getBoolCustomValue(CustomValueType.STAINS_ENABLED);
    }

    public boolean setThreeDeeModelsEnabled(boolean z) {
        setBoolCustomValue(CustomValueType.THREE_DEE_MODELS_ENABLED, z);
        return true;
    }

    public boolean isThreeDeeModelsEnabled() {
        return getBoolCustomValue(CustomValueType.THREE_DEE_MODELS_ENABLED);
    }

    public void setLargeFontsEnabled(boolean z) {
        setBoolCustomValue(CustomValueType.LARGE_FONTS_ENABLED, z);
    }

    public boolean isLargeFontsEnabled() {
        return getBoolCustomValue(CustomValueType.LARGE_FONTS_ENABLED);
    }

    public void setDebugMode(boolean z) {
        setBoolCustomValue(CustomValueType.DEBUG_MODE, z);
    }

    public boolean isInDebugMode() {
        return getBoolCustomValue(CustomValueType.DEBUG_MODE);
    }

    public void setDebugDetailedMode(boolean z) {
        setBoolCustomValue(CustomValueType.DEBUG_DETAILED_MODE, z);
    }

    public boolean isInDebugDetailedMode() {
        return getBoolCustomValue(CustomValueType.DEBUG_DETAILED_MODE);
    }

    private void e() {
        this.j.begin();
        int i2 = this.j.size;
        for (int i3 = 0; i3 < i2; i3++) {
            this.j.get(i3).settingsChanged();
        }
        this.j.end();
    }

    public IntArray getHoldingHotKeys() {
        return this.h;
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void preRender(float f) {
        super.preRender(f);
        this.h.clear();
        int i2 = 1;
        for (int i3 : b()) {
            if (Gdx.input.isKeyPressed(i3)) {
                this.h.add(i3);
                i2 = (i2 * 31) + i3;
            }
        }
        this.g.clear();
        if (this.e != i2) {
            this.e = i2;
            this.g.addAll(this.h);
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/SettingsManager$SettingsManagerListener.class */
    public interface SettingsManagerListener {
        void settingsChanged();

        void customValueChanged(CustomValueType customValueType, double d);

        void dynamicSettingsChanged();

        /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/SettingsManager$SettingsManagerListener$SettingsManagerListenerAdapter.class */
        public static abstract class SettingsManagerListenerAdapter implements SettingsManagerListener {
            @Override // com.prineside.tdi2.managers.SettingsManager.SettingsManagerListener
            public void settingsChanged() {
            }

            @Override // com.prineside.tdi2.managers.SettingsManager.SettingsManagerListener
            public void customValueChanged(CustomValueType customValueType, double d) {
            }

            @Override // com.prineside.tdi2.managers.SettingsManager.SettingsManagerListener
            public void dynamicSettingsChanged() {
            }
        }
    }
}
