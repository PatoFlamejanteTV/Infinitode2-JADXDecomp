package com.prineside.tdi2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.BufferUtils;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.IntBuffer;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/Config.class */
public class Config {
    public static final int BUILD = 208;
    public static final int BUILD_PROTOCOL = 208;
    public static final String VERSION = "R.1.9.2";
    public static final boolean DEBUG_MODE = false;
    public static final boolean PUBLIC_BETA_MODE = false;
    public static final boolean DEBUG_GENERATE_LOCALE_STUFF = true;
    public static final boolean DEBUG_GENERATE_RESOURCES_JSON = true;
    public static final boolean DEBUG_GENERATE_KRYO_REGISTRY = true;
    public static final boolean DEBUG_VALIDATE_LOCALES = false;
    public static final boolean AD_DEBUG_MODE = false;
    public static final boolean DEVELOPER_MODE_AVAILABLE = true;
    public static final int DISPLAY_HEIGHT = 900;
    public static final int DISPLAY_WIDTH = 1600;
    public static final int SYNC_CHECK_MAX_DEPTH = 12;
    public static final boolean SYNC_CHECK_COLD_START_ON_CONTINUE = true;
    public static final float DEBUG_ITEM_DROP_RATE = 1.0f;
    public static final boolean DEBUG_TEST_MANAGERS = false;
    public static final int VIEWPORT_HEIGHT = 1080;
    public static final int TILE_SIZE = 128;
    public static final float TILE_SIZE_INV = 0.0078125f;
    public static final int TILE_HALF_SIZE = 64;
    public static final float TILE_HALF_SIZE_INV = 0.015625f;
    public static final int FONT_SIZE_HUGE = 60;
    public static final int FONT_SIZE_LARGE = 36;
    public static final int FONT_SIZE_MEDIUM = 30;
    public static final int FONT_SIZE_SMALL = 24;
    public static final int FONT_SIZE_X_SMALL = 21;
    public static final int FONT_SIZE_XX_SMALL = 18;
    public static final long MAX_UPDATES_DURATION = 33333;
    public static final float MAX_UPDATES_TIME_ACCUMULATOR = 2.0f;
    public static final int REPLAY_CHARTS_VERSION = 2;
    public static final float GIVEN_DAMAGE_EXP_COEFF = 2.0f;
    public static final float ENEMY_DIE_SCORE_MULTIPLIER = 1.75f;
    public static final int PLAYER_XP_ISSUE_INTERVAL = 2700;
    public static final int PLAYER_XP_ISSUE_INTERVAL_ENDLESS = 5400;
    public static final int PLAYER_XP_INACTIVITY_DURATION = 13500;
    public static final int PLAYER_XP_MAX_PER_GAME = 1333;
    public static final int PRESTIGE_MAP_SELL_STAT_MIN_PRICE = 50;
    public static final float ENDLESS_MAX_REPLAY_DURATION = 2700.0f;
    public static final String RESOURCES_DIR = "res/";
    public static final String PLAY_STORE_URI = "https://play.google.com/store/apps/details?id=com.prineside.tdi2";
    public static final String APP_STORE_URI = "https://apps.apple.com/us/app/infinitode-2/id1480178308";
    public static final int STEAM_APP_ID = 937310;
    public static final String WIKI_URL = "https://infinitode-2.wikia.com";
    public static final String OPTIONAL_WEB_TEXTURES_URL = "https://files.prineside.com/static/infinitode_website/optional/";
    public static final String I18N_SUGGEST_FIX_URL = "https://infinitode.prineside.com/?m=translate";
    public static final String DAILY_QUEST_RULES_WIKI_URL = "https://infinitode-2.fandom.com/wiki/Daily_quests";
    public static final String SITE_SHARED_GET_PART = "&v=208";
    public static final String SERVER_TIMESTAMP_FALLBACK_URL = "https://dev.prineside.com/timestamp";
    public static final String ANALYTICS_FOR_ACTIONS_URL = "https://dev.prineside.com/tdi2_player_actions_analytics/?action=submit";
    public static final String DEVELOPER_DOCUMENTATION_URL = "https://infinitode.prineside.com/modding/";
    public static final String FEEDBACK_EMAIL = "sup.prineside@gmail.com";
    public static final String PROFILE_STATUS_DOUBLE_GAIN = "double_gain";
    public static final String PROFILE_STATUS_PREMIUM = "premium";
    public static final String PAYMENTS_STORE_PARAM_ANDROID_GOOGLE = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA/U+ICp4sQUINhFRq+JaoetZReLuOOb1m1b5qPlxqeSRhGdu0HruaniHqz/96r81gxS14nCWMsBcV6qHQMj54rgPAAUVwMOY9tnf4ET5ObjwxgSpSsa0219FG9r6SbJyyNNOcR7O+4wefwtLItFwt8ItW3IOasyxyEb4frqK6PLiQNs6hTHtANYULlv4UrvTNoijvhLBGL8N2GO5pNMIwwI7GNp+VecmSG/xKD+4E7kZR1F0ZxSew03Zrz0UiVikk3Lgks4WoEwevwNi44OU/P7oqYFDDoHDh81xf+hK8MQ3ijPa8u3EBARBFYN0mc3Hl9w0lrpiKx19PE5yZ48IoUQIDAQAB";

    /* renamed from: b, reason: collision with root package name */
    private static int f1686b;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f1685a = TLog.forClass(Class.class);
    public static String PACKAGE = "com.prineside.tdi2";
    public static final Color WHITE_COLOR_CACHED_FLOAT_BITS = new Color(1.0f, 1.0f, 1.0f, 1.0f) { // from class: com.prineside.tdi2.Config.1
        {
            super(1.0f, 1.0f, 1.0f, 1.0f);
        }

        @Override // com.badlogic.gdx.graphics.Color
        public float toFloatBits() {
            return Color.WHITE_FLOAT_BITS;
        }
    };
    public static final Color BACKGROUND_COLOR = new Color(387389439);
    public static int GAME_STATE_UPDATE_RATE = 30;
    public static float UPDATE_DELTA_TIME = 1.0f / GAME_STATE_UPDATE_RATE;
    public static final int REPLAY_CHARTS_INTERVAL = GAME_STATE_UPDATE_RATE * 5;
    public static String LEGACY_PREFERENCES_NAME_PREFIX = "com.prineside.tdi2._prefV1.";
    public static final String LEGACY_PREFERENCES_NAME_PROGRESS = LEGACY_PREFERENCES_NAME_PREFIX + "Progress";
    public static final String LEGACY_PREFERENCES_NAME_SETTINGS = LEGACY_PREFERENCES_NAME_PREFIX + "Settings";
    public static final String LEGACY_PREFERENCES_NAME_STATISTICS = LEGACY_PREFERENCES_NAME_PREFIX + "Statistics";
    public static final String LEGACY_PREFERENCES_NAME_USER_MAPS = LEGACY_PREFERENCES_NAME_PREFIX + "UserMaps";
    public static final String[] LEGACY_PREFERENCES_NAMES = {LEGACY_PREFERENCES_NAME_PROGRESS, LEGACY_PREFERENCES_NAME_SETTINGS, LEGACY_PREFERENCES_NAME_STATISTICS, LEGACY_PREFERENCES_NAME_USER_MAPS};
    public static String SITE_URL = "https://infinitode.prineside.com";
    public static String AVATAR_WEB_TEXTURES_URL = "https://storage.prineside.com/files/i2/avatars/";
    public static final String NEWS_URI = SITE_URL + "/?m=news";
    public static String PRIVACY_POLICY_URL = "https://infinitode.prineside.com/?m=game_privacy_policy";
    public static String TERMS_AND_CONDITIONS_URL = "https://infinitode.prineside.com/?m=game_terms_and_conditions";
    public static String WHY_ACCOUNT_URL = SITE_URL + "/?m=why_account";
    public static final String SERVER_TIMESTAMP_URL = SITE_URL + "/?m=api&a=getTimestamp&v=208";
    public static final String GAME_REPLAY_REPORT_URL = SITE_URL + "/?m=api&a=submitGameReplay&v=208";
    public static final String IAP_VALIDATION_URL = SITE_URL + "/?m=api&a=validateIAP&v=208";
    public static final String GET_BEST_GAME_REPLAY_URL = SITE_URL + "/?m=api&a=getBestGameReplay&v=208";
    public static final String SECRET_CODE_APPLICATION_URL = SITE_URL + "/?m=api&a=applySecretCode&v=208";
    public static final String LOGGER_REPORT_URL = SITE_URL + "/?m=api&a=submitLoggerReport&v=208";
    public static final String GAME_START_LOG_URL = SITE_URL + "/?m=api&a=logGameStart&v=208";
    public static final String DYNAMIC_SETTINGS_URL = SITE_URL + "/?m=api&a=getDynamicSettings&v=208";
    public static final String AUTH_GET_SESSION_INFO_URL = SITE_URL + "/?m=api&a=getPlayerSessionInfo&v=208";
    public static final String AUTH_SAVE_GAME_URL = SITE_URL + "/?m=api&a=saveGame&v=208";
    public static final String AUTH_PASTEBIN_URL = SITE_URL + "/?m=api&a=createPasteBin&v=208";
    public static final String AUTH_BACKUP_PROGRESS_URL = SITE_URL + "/?m=api&a=addProgressRestorePoint&v=208";
    public static final String AUTH_LOAD_GAME_URL = SITE_URL + "/?m=api&a=loadGame&v=208";
    public static final String AUTH_DELETE_GAME_URL = SITE_URL + "/?m=api&a=deleteGame&v=208";
    public static final String AUTH_SIGN_IN_URL = SITE_URL + "/?m=api&a=signIn&v=208";
    public static final String AUTH_SIGN_IN_CONFIRM_OTP_URL = SITE_URL + "/?m=api&a=signInConfirmOTP&v=208";
    public static final String AUTH_SIGN_IN_GOOGLE_URL = SITE_URL + "/?m=api&a=signInWithGoogle&v=208";
    public static final String AUTH_SIGN_IN_STEAM_URL = SITE_URL + "/?m=api&a=signInOrUpWithSteam&v=208";
    public static final String AUTH_SIGN_IN_OKJOY_URL = SITE_URL + "/?m=api&a=signInOrUpWithApptutti&v=208";
    public static final String AUTH_PASSWORD_RESET_URL = SITE_URL + "/?m=api&a=resetPassword&v=208";
    public static final String AUTH_PASSWORD_SET_URL = SITE_URL + "/?m=api&a=setPassword&v=208";
    public static final String AUTH_CONFIRM_EMAIL_URL = SITE_URL + "/?m=api&a=confirmEmail&v=208";
    public static final String AUTH_LINK_ACCOUNT_STATUS_URL = SITE_URL + "/?m=api&a=linkAccountStatus&v=208";
    public static final String AUTH_LINK_STEAM_URL = SITE_URL + "/?m=api&a=linkSteamAccount&v=208";
    public static final String AUTH_NICKNAME_CHANGE_URL = SITE_URL + "/?m=api&a=changeNickname&v=208";
    public static final String AUTH_SIGN_OUT_URL = SITE_URL + "/?m=api&a=signOut&v=208";
    public static final String AUTH_SIGN_UP_URL = SITE_URL + "/?m=api&a=signUp&v=208";
    public static final String AUTH_SIGN_UP_STEAM_URL = SITE_URL + "/?m=api&a=signUpSteam&v=208";
    public static final String AUTH_SIGN_UP_GOOGLE_URL = SITE_URL + "/?m=api&a=signUpGoogle&v=208";
    public static final String REVIEW_URL = SITE_URL + "/?m=api&a=submitReview&v=208";
    public static final String GET_SAVED_GAMES_LIST_URL = SITE_URL + "/?m=api&a=getSavedGamesList&v=208";
    public static final String SET_CLOUD_SAVE_NOTE = SITE_URL + "/?m=api&a=setSavedGameNote&v=208";
    public static final String REQUEST_OF_MERIT_BADGE = SITE_URL + "/?m=api&a=requestOfMeritBadge&v=208";
    public static final String GET_LAST_REPLAYS_TO_RESTORE_PREFERENCES_URL = SITE_URL + "/?m=api&a=getLastReplaysToRestorePreferences&v=208";
    public static final String GET_BACKUPS_TO_RESTORE_PREFERENCES_URL = SITE_URL + "/?m=api&a=getProgressRestorePoints&v=208";
    public static final String GET_BASIC_LEVELS_TOP_LEADERBOARDS_URL = SITE_URL + "/?m=api&a=getBasicLevelsTopLeaderboards&v=208";
    public static final String GET_LEADERBOARDS_URL = SITE_URL + "/?m=api&a=getLeaderboards&v=208";
    public static final String GET_SKILL_POINT_LEADER_BOARD_URL = SITE_URL + "/?m=api&a=getSkillPointLeaderboard&v=208";
    public static final String GET_LEADERBOARDS_RANK_URL = SITE_URL + "/?m=api&a=getLeaderboardsRank&v=208";
    public static final String GET_DAILY_QUEST_LEADERBOARDS_URL = SITE_URL + "/?m=api&a=getDailyQuestLeaderboards&v=208";
    public static final String GET_LEADERBOARDS_ADVANCE_RANK_URL = SITE_URL + "/?m=api&a=getAdvanceLeaderboardsRank&v=208";
    public static final String GET_DAILY_QUEST_LEADERBOARDS_ADVANCE_RANK_URL = SITE_URL + "/?m=api&a=getDailyQuestAdvanceLeaderboardsRank&v=208";
    public static final String GET_LATEST_NEWS_URL = SITE_URL + "/?m=api&a=getLatestNews&v=208";
    public static final String RECEIVE_ISSUED_ITEMS_URL = SITE_URL + "/?m=api&a=receiveIssuedItems&v=208";
    public static final String GET_DAILY_QUEST_INFO_URL = SITE_URL + "/?m=api&a=getDailyQuestInfo&v=208";
    public static final String GET_DAILY_QUEST_HASH_URL = SITE_URL + "/?m=api&a=getDailyQuestHash&v=208";
    public static final String GET_DAILY_QUEST_MAP_URL = SITE_URL + "/?m=api&a=getDailyQuestMap&v=208";
    public static final String GET_RUNTIME_LEADERBOARDS_URL = SITE_URL + "/?m=api&a=getRuntimeLeaderboards&v=208";
    public static final String GET_COMMUNITY_TRANSLATORS_URL = SITE_URL + "/?m=api&a=getCommunityTranslators&v=208";
    public static final String GET_MESSAGES_URL = SITE_URL + "/?m=api&a=getPlayerMessages&v=208";
    public static final String GET_BETA_ACCOUNT_LINK_STATUS_URL = SITE_URL + "/?m=api&a=getBetaAccountLinkStatus&v=208";
    public static final String LINK_BETA_ACCOUNT_URL = SITE_URL + "/?m=api&a=linkBetaAccount&v=208";
    public static final String MARK_MESSAGE_URL = SITE_URL + "/?m=api&a=markMessage&v=208";
    public static final String GET_ACCOUNT_SETTINGS_URL = SITE_URL + "/?m=api&a=getAccountSettings&v=208";
    public static final String VOTE_MUSIC_URL = SITE_URL + "/?m=api&a=voteMusic&v=208";
    public static final String SUBMIT_MUSIC_URL = SITE_URL + "/?m=api&a=submitMusic&v=208";
    public static final String GET_STEAM_AUTH_TICKET_INFO_URL = SITE_URL + "/?m=api&a=getSteamAuthTicketInfo&v=208";
    public static final String XDX_ROOT_URL = SITE_URL + "/xdx/?url=";
    public static final String XDX_VIEW_NEWS_URL = SITE_URL + "/xdx/?url=news/view&id=";
    public static final String XDX_VIEW_SEASONAL_LEADERBOARD_URL = SITE_URL + "/xdx/?url=seasonal_leaderboard";
    public static final String XDX_VIEW_PLAYER_PROFILE_URL = SITE_URL + "/xdx/?url=profile/view&id=";
    public static final String XDX_VIEW_PLAYER_PROFILE_BY_NICKNAME_URL = SITE_URL + "/xdx/?url=profile/view&nickname=";
    public static final String STEAM_TXN_PRODUCT_LIST_URL = SITE_URL + "/?m=api&a=steamTxnGetProducts&v=208";
    public static final String STEAM_TXN_START_URL = SITE_URL + "/?m=api&a=steamTxnStart&v=208";
    public static final String STEAM_TXN_FINALIZE_URL = SITE_URL + "/?m=api&a=steamTxnFinalize&v=208";
    public static String ANDROID_REWARDED_VIDEOS_ID = "ca-app-pub-1337541681212211/4322427738";
    public static String ANDROID_REWARDED_INTERSTITIAL_AD_ID = "ca-app-pub-1337541681212211/6253548041";
    public static String ANDROID_INTERSTITIAL_AD_ID = "ca-app-pub-1337541681212211/5100070865";
    public static String IOS_REWARDED_VIDEOS_ID = "ca-app-pub-1337541681212211/2711212026";
    public static boolean IS_HEADLESS = false;
    private static String c = null;

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/Config$ProductId.class */
    public enum ProductId {
        DOUBLE_GAIN,
        PACK_TINY,
        PACK_SMALL,
        PACK_MEDIUM,
        PACK_LARGE,
        PACK_HUGE,
        ACCELERATOR_PACK_TINY,
        ACCELERATOR_PACK_SMALL,
        ACCELERATOR_PACK_MEDIUM,
        ACCELERATOR_PACK_LARGE,
        ACCELERATOR_PACK_HUGE,
        SPECIAL_OFFER_C3;

        public static ProductId[] values = values();
    }

    public static boolean isCompatibleWithBuild(int i) {
        return i <= 208 && i >= 208;
    }

    public static int getMaxTextureSize() {
        if (f1686b == 0) {
            try {
                IntBuffer newIntBuffer = BufferUtils.newIntBuffer(16);
                Gdx.gl20.glGetIntegerv(3379, newIntBuffer);
                f1686b = newIntBuffer.get();
            } catch (Exception unused) {
                f1686b = 2048;
                f1685a.e("Failed to get max texture size, falling back to 2048", new Object[0]);
            }
        }
        return f1686b;
    }

    public static boolean isHeadless() {
        return IS_HEADLESS;
    }

    public static String getModId() {
        if (c == null) {
            c = "";
            try {
                File file = new File("mod_id.txt");
                if (file.exists() && file.isFile()) {
                    String replaceAll = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8")).readLine().trim().replaceAll("[^a-zA-Z0-9]", "");
                    if (replaceAll.length() > 0 && replaceAll.length() <= 32) {
                        c = replaceAll;
                        f1685a.i("Using mod id: " + c, new Object[0]);
                    } else {
                        f1685a.e("Invalid or empty mod id (should be a-zA-Z0-9, 1-32 chars in length)", new Object[0]);
                    }
                }
            } catch (Exception unused) {
            }
        }
        if ("".equals(c)) {
            return null;
        }
        return c;
    }

    public static boolean isModdingMode() {
        return getModId() != null;
    }

    public static boolean preferencesEncryptionEnabled() {
        return (isHeadless() || isModdingMode()) ? false : true;
    }
}
