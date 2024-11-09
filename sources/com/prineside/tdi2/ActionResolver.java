package com.prineside.tdi2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.AudioDevice;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.pay.PurchaseManager;
import com.badlogic.gdx.pay.Transaction;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectMap;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.enums.AchievementType;
import com.prineside.tdi2.managers.MusicManager;
import com.prineside.tdi2.managers.PurchaseManager;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.scene2d.ui.TextField;
import com.prineside.tdi2.scene2d.utils.FocusListener;
import com.prineside.tdi2.ui.shared.Notifications;
import com.prineside.tdi2.utils.FileChooser;
import com.prineside.tdi2.utils.IntPair;
import com.prineside.tdi2.utils.ObjectConsumer;
import com.prineside.tdi2.utils.logging.PlatformLogger;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ActionResolver.class */
public interface ActionResolver {
    FileHandle getLogFile();

    PlatformLogger createPlatformLogger();

    boolean isAppModified();

    @Null
    String getAppModifiedInfo();

    ObjectMap<String, String> getDeviceInfo();

    String getShortDeviceInfo();

    PurchaseManager getPurchaseManager();

    @Null
    IntPair getBestScreenResolution();

    AudioDevice newAudioDevice(int i, boolean z);

    MusicManager getCachedMusicManager();

    void onExit();

    int[] getScreenSafeAreaInsets();

    void parseHtml(String str);

    int getWindowsGraphicsScale();

    void setFpsLimit(int i);

    InitConfigManager getInitConfigManager();

    HashSet<Class<?>> getClasses(String str);

    void getMobilePasswordInput(Input.TextInputListener textInputListener, String str, String str2, String str3);

    boolean rewardAdsAvailable();

    boolean canShowRewardAd();

    int getSecondsTillCanShowRewardAd();

    void showRewardAd(ObjectConsumer<Boolean> objectConsumer, PurchaseManager.RewardingAdsType rewardingAdsType);

    void showInterstitialAd(ObjectConsumer<Boolean> objectConsumer);

    boolean hasGoogleAuth();

    boolean isSignedInWithGoogle();

    void requestGoogleAuth(ObjectConsumer<String> objectConsumer);

    void signOutGoogle();

    void requestLogin();

    void logCurrencyReceived(String str, String str2, int i);

    void logCurrencySpent(String str, String str2, int i);

    void logLogined(String str);

    void logSignedUp(String str);

    void logCustomEvent(String str, String[] strArr);

    void logIAP(Config.ProductId productId, Transaction transaction);

    void logRewardedVideoViewed(PurchaseManager.RewardingAdsType rewardingAdsType);

    void logShopOfferPurchased(String str, int i, String str2, int i2);

    void logShopOffersSkipped(int i);

    void logScreenChange(String str);

    boolean hasNotifications();

    void addNotification(int i, String str, String str2, long j);

    void clearNotification(int i);

    void unlockAchievement(AchievementType achievementType);

    void openHandbook();

    void openSupportPage();

    boolean doubleGainEnabledBySteamGamePurchase();

    void handleThreadExceptions(Thread thread);

    void generateDeviceReport(Json json);

    String glGetStringi(int i, int i2);

    ClassLoadingStrategy getByteBuddyClassLoadingStrategy();

    void handleTextFieldFocus(FocusListener.FocusEvent focusEvent, TextField textField, boolean z);

    void requestSteamAuthTicket(ObjectConsumer<String> objectConsumer);

    String getDefaultLocale();

    boolean personalizedAdsSupported();

    boolean personalizedAdsEnabled();

    void setPersonalizedAds(boolean z);

    @Null
    FileChooser getFileChooser();

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ActionResolver$ActionResolverAdapter.class */
    public static abstract class ActionResolverAdapter implements ActionResolver {

        /* renamed from: a, reason: collision with root package name */
        private InitConfigManager f1662a;

        @Override // com.prineside.tdi2.ActionResolver
        public com.badlogic.gdx.pay.PurchaseManager getPurchaseManager() {
            return null;
        }

        @Override // com.prineside.tdi2.ActionResolver
        public int[] getScreenSafeAreaInsets() {
            return new int[4];
        }

        @Override // com.prineside.tdi2.ActionResolver
        @Null
        public IntPair getBestScreenResolution() {
            return null;
        }

        @Override // com.prineside.tdi2.ActionResolver
        public void setFpsLimit(int i) {
        }

        @Override // com.prineside.tdi2.ActionResolver
        public int getWindowsGraphicsScale() {
            return -1;
        }

        @Override // com.prineside.tdi2.ActionResolver
        public void parseHtml(String str) {
            throw new RuntimeException("Not available on this platform");
        }

        @Override // com.prineside.tdi2.ActionResolver
        public MusicManager getCachedMusicManager() {
            throw new UnsupportedOperationException("Not implemented");
        }

        @Override // com.prineside.tdi2.ActionResolver
        public boolean hasNotifications() {
            return false;
        }

        @Override // com.prineside.tdi2.ActionResolver
        public void addNotification(int i, String str, String str2, long j) {
        }

        @Override // com.prineside.tdi2.ActionResolver
        public void clearNotification(int i) {
        }

        @Override // com.prineside.tdi2.ActionResolver
        public void requestLogin() {
        }

        @Override // com.prineside.tdi2.ActionResolver
        public void generateDeviceReport(Json json) {
        }

        @Override // com.prineside.tdi2.ActionResolver
        public InitConfigManager getInitConfigManager() {
            if (this.f1662a == null) {
                this.f1662a = new InitConfigManager();
            }
            return this.f1662a;
        }

        @Override // com.prineside.tdi2.ActionResolver
        public void getMobilePasswordInput(Input.TextInputListener textInputListener, String str, String str2, String str3) {
            throw new RuntimeException("Only for mobile devices");
        }

        @Override // com.prineside.tdi2.ActionResolver
        public boolean rewardAdsAvailable() {
            return false;
        }

        @Override // com.prineside.tdi2.ActionResolver
        public boolean canShowRewardAd() {
            return false;
        }

        @Override // com.prineside.tdi2.ActionResolver
        public boolean hasGoogleAuth() {
            return false;
        }

        @Override // com.prineside.tdi2.ActionResolver
        public boolean isSignedInWithGoogle() {
            return false;
        }

        @Override // com.prineside.tdi2.ActionResolver
        public void requestGoogleAuth(ObjectConsumer<String> objectConsumer) {
        }

        @Override // com.prineside.tdi2.ActionResolver
        public void signOutGoogle() {
        }

        @Override // com.prineside.tdi2.ActionResolver
        public void logCurrencyReceived(String str, String str2, int i) {
        }

        @Override // com.prineside.tdi2.ActionResolver
        public void logCurrencySpent(String str, String str2, int i) {
        }

        @Override // com.prineside.tdi2.ActionResolver
        public void logLogined(String str) {
        }

        @Override // com.prineside.tdi2.ActionResolver
        public void logSignedUp(String str) {
        }

        @Override // com.prineside.tdi2.ActionResolver
        public void logShopOfferPurchased(String str, int i, String str2, int i2) {
        }

        @Override // com.prineside.tdi2.ActionResolver
        public void logShopOffersSkipped(int i) {
        }

        @Override // com.prineside.tdi2.ActionResolver
        public void logScreenChange(String str) {
        }

        @Override // com.prineside.tdi2.ActionResolver
        public int getSecondsTillCanShowRewardAd() {
            return -1;
        }

        @Override // com.prineside.tdi2.ActionResolver
        public void showRewardAd(ObjectConsumer<Boolean> objectConsumer, PurchaseManager.RewardingAdsType rewardingAdsType) {
        }

        @Override // com.prineside.tdi2.ActionResolver
        public void showInterstitialAd(ObjectConsumer<Boolean> objectConsumer) {
        }

        @Override // com.prineside.tdi2.ActionResolver
        public boolean isAppModified() {
            return false;
        }

        @Override // com.prineside.tdi2.ActionResolver
        public String getAppModifiedInfo() {
            return null;
        }

        @Override // com.prineside.tdi2.ActionResolver
        public void logCustomEvent(String str, String[] strArr) {
        }

        @Override // com.prineside.tdi2.ActionResolver
        public void logIAP(Config.ProductId productId, Transaction transaction) {
        }

        @Override // com.prineside.tdi2.ActionResolver
        public void logRewardedVideoViewed(PurchaseManager.RewardingAdsType rewardingAdsType) {
        }

        @Override // com.prineside.tdi2.ActionResolver
        public HashSet<Class<?>> getClasses(String str) {
            throw new RuntimeException("Not implemented on this platform");
        }

        @Override // com.prineside.tdi2.ActionResolver
        public void onExit() {
        }

        @Override // com.prineside.tdi2.ActionResolver
        public AudioDevice newAudioDevice(int i, boolean z) {
            return Gdx.audio.newAudioDevice(i, z);
        }

        @Override // com.prineside.tdi2.ActionResolver
        public void unlockAchievement(AchievementType achievementType) {
        }

        @Override // com.prineside.tdi2.ActionResolver
        public void openHandbook() {
            Gdx.f881net.openURI(Game.i.settingsManager.getDynamicSetting(SettingsManager.DynamicSetting.WIKI_URL));
        }

        @Override // com.prineside.tdi2.ActionResolver
        public boolean doubleGainEnabledBySteamGamePurchase() {
            return false;
        }

        @Override // com.prineside.tdi2.ActionResolver
        public void openSupportPage() {
            Gdx.f881net.openURI("https://prineside.com/support.html");
            Gdx.app.getClipboard().setContents(Config.FEEDBACK_EMAIL);
            Notifications.i().addSuccess("Contact us: sup.prineside@gmail.com");
        }

        @Override // com.prineside.tdi2.ActionResolver
        public void handleThreadExceptions(Thread thread) {
        }

        @Override // com.prineside.tdi2.ActionResolver
        public ClassLoadingStrategy getByteBuddyClassLoadingStrategy() {
            return null;
        }

        @Override // com.prineside.tdi2.ActionResolver
        public void handleTextFieldFocus(FocusListener.FocusEvent focusEvent, TextField textField, boolean z) {
        }

        @Override // com.prineside.tdi2.ActionResolver
        public void requestSteamAuthTicket(ObjectConsumer<String> objectConsumer) {
        }

        @Override // com.prineside.tdi2.ActionResolver
        public String getDefaultLocale() {
            return Locale.getDefault().toString();
        }

        @Override // com.prineside.tdi2.ActionResolver
        public boolean personalizedAdsSupported() {
            return false;
        }

        @Override // com.prineside.tdi2.ActionResolver
        public boolean personalizedAdsEnabled() {
            return false;
        }

        @Override // com.prineside.tdi2.ActionResolver
        public void setPersonalizedAds(boolean z) {
        }
    }

    static ActionResolver createDummy(final FileHandle fileHandle, final PlatformLogger platformLogger) {
        Preconditions.checkNotNull(fileHandle, "logFile can not be null");
        Preconditions.checkNotNull(platformLogger, "logger can not be null");
        return new ActionResolverAdapter() { // from class: com.prineside.tdi2.ActionResolver.1
            @Override // com.prineside.tdi2.ActionResolver
            public FileHandle getLogFile() {
                return FileHandle.this;
            }

            @Override // com.prineside.tdi2.ActionResolver
            public PlatformLogger createPlatformLogger() {
                return platformLogger;
            }

            @Override // com.prineside.tdi2.ActionResolver
            public ObjectMap<String, String> getDeviceInfo() {
                return new ObjectMap<>();
            }

            @Override // com.prineside.tdi2.ActionResolver
            public String getShortDeviceInfo() {
                return "dummy";
            }

            @Override // com.prineside.tdi2.ActionResolver
            public String glGetStringi(int i, int i2) {
                return "";
            }

            @Override // com.prineside.tdi2.ActionResolver
            public FileChooser getFileChooser() {
                return null;
            }
        };
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ActionResolver$InitConfigManager.class */
    public static class InitConfigManager {
        public static final String INIT_CONFIG_FILE = "i2-config.json";

        /* renamed from: a, reason: collision with root package name */
        private int[] f1663a;

        /* renamed from: b, reason: collision with root package name */
        private boolean f1664b;

        private static String a() {
            try {
                File file = new File(INIT_CONFIG_FILE);
                if (!file.exists()) {
                    return "{}";
                }
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
                StringBuilder sb = new StringBuilder();
                for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                    sb.append(readLine).append(SequenceUtils.EOL);
                }
                return sb.toString();
            } catch (Exception e) {
                TLog.forClass(ActionResolver.class).e("loadConfigsJsonFromFile failed", e);
                return "{}";
            }
        }

        private static void a(String str) {
            try {
                FileWriter fileWriter = new FileWriter(new File(INIT_CONFIG_FILE));
                fileWriter.write(str);
                fileWriter.close();
            } catch (Exception e) {
                TLog.forClass(ActionResolver.class).e("failed to save init configs", e);
            }
        }

        public void saveIfRequired() {
            if (this.f1664b) {
                b();
            }
        }

        private void b() {
            this.f1664b = false;
            Json json = new Json(JsonWriter.OutputType.json);
            StringWriter stringWriter = new StringWriter();
            json.setWriter(stringWriter);
            json.writeObjectStart();
            int[] c = c();
            for (SettingsManager.InitConfig initConfig : SettingsManager.InitConfig.values) {
                int i = c[initConfig.ordinal()];
                if (i != getDefault(initConfig)) {
                    json.writeValue(initConfig.name(), Integer.valueOf(i));
                }
            }
            json.writeObjectEnd();
            a(stringWriter.toString());
            TLog.forClass(ActionResolver.class).i("saved init configs", new Object[0]);
        }

        private int[] c() {
            if (this.f1663a == null) {
                int[] iArr = new int[SettingsManager.InitConfig.values.length];
                for (SettingsManager.InitConfig initConfig : SettingsManager.InitConfig.values) {
                    iArr[initConfig.ordinal()] = getDefault(initConfig);
                }
                JsonValue parse = new JsonReader().parse(a());
                if (parse != null) {
                    Iterator<JsonValue> iterator2 = parse.iterator2();
                    while (iterator2.hasNext()) {
                        JsonValue next = iterator2.next();
                        try {
                            iArr[SettingsManager.InitConfig.valueOf(next.name).ordinal()] = next.asInt();
                        } catch (Exception unused) {
                            TLog.forClass(ActionResolver.class).e("unknown init config key or value is invalid: " + next.name, new Object[0]);
                        }
                    }
                }
                this.f1663a = iArr;
            }
            return this.f1663a;
        }

        public boolean isAvailable(SettingsManager.InitConfig initConfig) {
            return false;
        }

        public int get(SettingsManager.InitConfig initConfig) {
            return c()[initConfig.ordinal()];
        }

        public void set(SettingsManager.InitConfig initConfig, int i) {
            if (c()[initConfig.ordinal()] != i) {
                c()[initConfig.ordinal()] = i;
                this.f1664b = true;
            }
        }

        public int getDefault(SettingsManager.InitConfig initConfig) {
            return 0;
        }
    }
}
