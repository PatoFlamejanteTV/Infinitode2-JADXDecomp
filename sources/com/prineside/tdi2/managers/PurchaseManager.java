package com.prineside.tdi2.managers;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.net.HttpParametersUtils;
import com.badlogic.gdx.pay.Information;
import com.badlogic.gdx.pay.Offer;
import com.badlogic.gdx.pay.OfferType;
import com.badlogic.gdx.pay.PurchaseManagerConfig;
import com.badlogic.gdx.pay.PurchaseObserver;
import com.badlogic.gdx.pay.Transaction;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.Null;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.IssuedItems;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.Manager;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.events.global.GameLoad;
import com.prineside.tdi2.items.DoubleGainShardItem;
import com.prineside.tdi2.managers.PreferencesManager;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.managers.preferences.categories.ProgressPrefs;
import com.prineside.tdi2.managers.preferences.categories.progress.PP_Purchase;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.ui.shared.Dialog;
import com.prineside.tdi2.utils.ObjectConsumer;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import net.bytebuddy.utility.JavaConstant;
import org.lwjgl.opengl.CGL;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/PurchaseManager.class */
public class PurchaseManager extends Manager.ManagerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2413a = TLog.forClass(PurchaseManager.class);
    public com.badlogic.gdx.pay.PurchaseManager purchaseManager;

    /* renamed from: b, reason: collision with root package name */
    private IapOffersConfig f2414b;
    private long c;
    public final RewardingAdConfig[] rewardingAdConfigs = new RewardingAdConfig[RewardingAdsType.values.length];
    private final DelayedRemovalArray<PurchaseManagerListener> d = new DelayedRemovalArray<>(false, 1);

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/PurchaseManager$RewardingAdsType.class */
    public enum RewardingAdsType {
        END_GAME,
        REGULAR,
        LOOT_MULTIPLIER;

        public static final RewardingAdsType[] values = values();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/PurchaseManager$Serializer.class */
    public static class Serializer extends SingletonSerializer<PurchaseManager> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public PurchaseManager read() {
            return Game.i.purchaseManager;
        }
    }

    public String getPurchaseIdentifier(Config.ProductId productId) {
        if (Gdx.app.getType() == Application.ApplicationType.iOS) {
            switch (productId) {
                case DOUBLE_GAIN:
                    return "double_gain_infinitode2";
                case PACK_TINY:
                    return "pack_tiny_infinitode2";
                case PACK_SMALL:
                    return "pack_small_infinitode2";
                case PACK_MEDIUM:
                    return "pack_medium_infinitode2";
                case PACK_LARGE:
                    return "pack_large_infinitode2";
                case PACK_HUGE:
                    return "pack_huge_infinitode2";
                case ACCELERATOR_PACK_TINY:
                    return "accelerator_pack_tiny_infinitode2";
                case ACCELERATOR_PACK_SMALL:
                    return "accelerator_pack_small_infinitode2";
                case ACCELERATOR_PACK_MEDIUM:
                    return "accelerator_pack_medium_infinitode2";
                case ACCELERATOR_PACK_LARGE:
                    return "accelerator_pack_large_infinitode2";
                case ACCELERATOR_PACK_HUGE:
                    return "accelerator_pack_huge2_infinitode2";
                case SPECIAL_OFFER_C3:
                    return "special_offer_c3_infinitode2";
                default:
                    return null;
            }
        }
        switch (productId) {
            case DOUBLE_GAIN:
                return Config.PROFILE_STATUS_DOUBLE_GAIN;
            case PACK_TINY:
                return "pack_tiny";
            case PACK_SMALL:
                return "pack_small";
            case PACK_MEDIUM:
                return "pack_medium";
            case PACK_LARGE:
                return "pack_large";
            case PACK_HUGE:
                return "pack_huge";
            case ACCELERATOR_PACK_TINY:
                return "accelerator_pack_tiny";
            case ACCELERATOR_PACK_SMALL:
                return "accelerator_pack_small";
            case ACCELERATOR_PACK_MEDIUM:
                return "accelerator_pack_medium";
            case ACCELERATOR_PACK_LARGE:
                return "accelerator_pack_large";
            case ACCELERATOR_PACK_HUGE:
                return "accelerator_pack_huge";
            case SPECIAL_OFFER_C3:
                return "special_offer_c3";
            default:
                return null;
        }
    }

    public Config.ProductId getProductId(String str) {
        if (Gdx.app.getType() == Application.ApplicationType.iOS) {
            boolean z = -1;
            switch (str.hashCode()) {
                case -1861621399:
                    if (str.equals("accelerator_pack_small_infinitode2")) {
                        z = 7;
                        break;
                    }
                    break;
                case -1640721723:
                    if (str.equals("pack_huge_infinitode2")) {
                        z = 5;
                        break;
                    }
                    break;
                case -1589080773:
                    if (str.equals("accelerator_pack_medium_infinitode2")) {
                        z = 8;
                        break;
                    }
                    break;
                case -1584337591:
                    if (str.equals("pack_large_infinitode2")) {
                        z = 4;
                        break;
                    }
                    break;
                case -1119914225:
                    if (str.equals("pack_medium_infinitode2")) {
                        z = 3;
                        break;
                    }
                    break;
                case -904985702:
                    if (str.equals("pack_tiny_infinitode2")) {
                        z = true;
                        break;
                    }
                    break;
                case -768188003:
                    if (str.equals("accelerator_pack_large_infinitode2")) {
                        z = 9;
                        break;
                    }
                    break;
                case -211696927:
                    if (str.equals("double_gain_infinitode2")) {
                        z = false;
                        break;
                    }
                    break;
                case 179359561:
                    if (str.equals("accelerator_pack_huge2_infinitode2")) {
                        z = 10;
                        break;
                    }
                    break;
                case 930260845:
                    if (str.equals("special_offer_c3_infinitode2")) {
                        z = 11;
                        break;
                    }
                    break;
                case 1199551686:
                    if (str.equals("accelerator_pack_tiny_infinitode2")) {
                        z = 6;
                        break;
                    }
                    break;
                case 1617196309:
                    if (str.equals("pack_small_infinitode2")) {
                        z = 2;
                        break;
                    }
                    break;
            }
            switch (z) {
                case false:
                    return Config.ProductId.DOUBLE_GAIN;
                case true:
                    return Config.ProductId.PACK_TINY;
                case true:
                    return Config.ProductId.PACK_SMALL;
                case true:
                    return Config.ProductId.PACK_MEDIUM;
                case true:
                    return Config.ProductId.PACK_LARGE;
                case true:
                    return Config.ProductId.PACK_HUGE;
                case true:
                    return Config.ProductId.ACCELERATOR_PACK_TINY;
                case true:
                    return Config.ProductId.ACCELERATOR_PACK_SMALL;
                case true:
                    return Config.ProductId.ACCELERATOR_PACK_MEDIUM;
                case true:
                    return Config.ProductId.ACCELERATOR_PACK_LARGE;
                case true:
                    return Config.ProductId.ACCELERATOR_PACK_HUGE;
                case true:
                    return Config.ProductId.SPECIAL_OFFER_C3;
                default:
                    return null;
            }
        }
        boolean z2 = -1;
        switch (str.hashCode()) {
            case -1618132179:
                if (str.equals(Config.PROFILE_STATUS_DOUBLE_GAIN)) {
                    z2 = false;
                    break;
                }
                break;
            case -1470282617:
                if (str.equals("accelerator_pack_medium")) {
                    z2 = 8;
                    break;
                }
                break;
            case -693810085:
                if (str.equals("pack_medium")) {
                    z2 = 3;
                    break;
                }
                break;
            case -658645955:
                if (str.equals("accelerator_pack_huge")) {
                    z2 = 10;
                    break;
                }
                break;
            case -658299758:
                if (str.equals("accelerator_pack_tiny")) {
                    z2 = 6;
                    break;
                }
                break;
            case -23410283:
                if (str.equals("pack_large")) {
                    z2 = 4;
                    break;
                }
                break;
            case -16604319:
                if (str.equals("pack_small")) {
                    z2 = 2;
                    break;
                }
                break;
            case 1059920873:
                if (str.equals("accelerator_pack_large")) {
                    z2 = 9;
                    break;
                }
                break;
            case 1066726837:
                if (str.equals("accelerator_pack_small")) {
                    z2 = 7;
                    break;
                }
                break;
            case 1800259857:
                if (str.equals("pack_huge")) {
                    z2 = 5;
                    break;
                }
                break;
            case 1800606054:
                if (str.equals("pack_tiny")) {
                    z2 = true;
                    break;
                }
                break;
            case 1992792505:
                if (str.equals("special_offer_c3")) {
                    z2 = 11;
                    break;
                }
                break;
        }
        switch (z2) {
            case false:
                return Config.ProductId.DOUBLE_GAIN;
            case true:
                return Config.ProductId.PACK_TINY;
            case true:
                return Config.ProductId.PACK_SMALL;
            case true:
                return Config.ProductId.PACK_MEDIUM;
            case true:
                return Config.ProductId.PACK_LARGE;
            case true:
                return Config.ProductId.PACK_HUGE;
            case true:
                return Config.ProductId.ACCELERATOR_PACK_TINY;
            case true:
                return Config.ProductId.ACCELERATOR_PACK_SMALL;
            case true:
                return Config.ProductId.ACCELERATOR_PACK_MEDIUM;
            case true:
                return Config.ProductId.ACCELERATOR_PACK_LARGE;
            case true:
                return Config.ProductId.ACCELERATOR_PACK_HUGE;
            case true:
                return Config.ProductId.SPECIAL_OFFER_C3;
            default:
                return null;
        }
    }

    public PurchaseManager() {
        this.rewardingAdConfigs[RewardingAdsType.END_GAME.ordinal()] = new RewardingAdConfig(90, 1, 90);
        this.rewardingAdConfigs[RewardingAdsType.REGULAR.ordinal()] = new RewardingAdConfig(14400, 10, 300);
        this.rewardingAdConfigs[RewardingAdsType.LOOT_MULTIPLIER.ordinal()] = new RewardingAdConfig(88, 1, 89);
        this.purchaseManager = Game.i.actionResolver.getPurchaseManager();
    }

    /* renamed from: com.prineside.tdi2.managers.PurchaseManager$1, reason: invalid class name */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/PurchaseManager$1.class */
    class AnonymousClass1 implements com.badlogic.gdx.pay.PurchaseManager {

        /* renamed from: a, reason: collision with root package name */
        private PurchaseObserver f2415a;

        @Override // com.badlogic.gdx.pay.PurchaseManager
        public String storeName() {
            return PurchaseManagerConfig.STORE_NAME_DESKTOP_WINDOWS;
        }

        @Override // com.badlogic.gdx.pay.PurchaseManager
        public void install(PurchaseObserver purchaseObserver, PurchaseManagerConfig purchaseManagerConfig, boolean z) {
            this.f2415a = purchaseObserver;
            purchaseObserver.handleInstall();
        }

        @Override // com.badlogic.gdx.pay.PurchaseManager
        public boolean installed() {
            return true;
        }

        @Override // com.badlogic.gdx.pay.PurchaseManager
        public void dispose() {
        }

        @Override // com.badlogic.gdx.pay.PurchaseManager
        public void purchase(final String str) {
            Dialog.i().showConfirmWithCallbacks("Confirm purchase?", new Runnable() { // from class: com.prineside.tdi2.managers.PurchaseManager.1.1
                @Override // java.lang.Runnable
                public void run() {
                    Transaction transaction = new Transaction();
                    transaction.setIdentifier(str);
                    transaction.setOrderId(Math.random() + JavaConstant.Dynamic.DEFAULT_NAME + str);
                    transaction.setRequestId("abcd" + Math.random());
                    transaction.setStoreName(PurchaseManagerConfig.STORE_NAME_DESKTOP_WINDOWS);
                    transaction.setPurchaseTime(new Date());
                    transaction.setPurchaseText("Purchased: " + str);
                    transaction.setReversalTime(null);
                    transaction.setReversalText(null);
                    transaction.setTransactionData("{}");
                    transaction.setTransactionDataSignature(new StringBuilder().append(Math.random()).toString());
                    AnonymousClass1.this.f2415a.handlePurchase(transaction);
                }
            }, new Runnable() { // from class: com.prineside.tdi2.managers.PurchaseManager.1.2
                @Override // java.lang.Runnable
                public void run() {
                    AnonymousClass1.this.f2415a.handlePurchaseError(new Throwable());
                }
            });
        }

        @Override // com.badlogic.gdx.pay.PurchaseManager
        public void purchaseRestore() {
        }

        @Override // com.badlogic.gdx.pay.InformationFinder
        public Information getInformation(String str) {
            return Information.newBuilder().localName(str).localDescription(str + " description").localPricing("19,99 USD").priceCurrencyCode("USD").build();
        }
    }

    public boolean rewardingAdsAvailable() {
        if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.ENABLE_REWARDING_ADS) != 0.0d && "true".equals(Game.i.settingsManager.getDynamicSetting(SettingsManager.DynamicSetting.ADS_ENABLED))) {
            return Game.i.actionResolver.rewardAdsAvailable() || Game.i.progressManager.isPremiumStatusActive();
        }
        return false;
    }

    public boolean canShowRewardingAd(RewardingAdsType rewardingAdsType) {
        if (rewardingAdsAvailable()) {
            return (Game.i.actionResolver.canShowRewardAd() || Game.i.progressManager.isPremiumStatusActive()) && getSecondsTillAdIsReady(rewardingAdsType) == 0;
        }
        return false;
    }

    public boolean noIAPAbility() {
        return "true".equals(Game.i.settingsManager.getDynamicSetting(SettingsManager.DynamicSetting.IAP_NOT_AVAILABLE_IN_COUNTRY));
    }

    public int getSecondsTillAdIsReady(RewardingAdsType rewardingAdsType) {
        RewardingAdConfig rewardingAdConfig = this.rewardingAdConfigs[rewardingAdsType.ordinal()];
        IntArray rewardingAdWatchTimestamps = ProgressPrefs.i().purchase.getRewardingAdWatchTimestamps(rewardingAdsType);
        int timestampSeconds = Game.getTimestampSeconds();
        int secondsTillCanShowRewardAd = Game.i.actionResolver.getSecondsTillCanShowRewardAd();
        int i = 0;
        int i2 = 0;
        if (rewardingAdWatchTimestamps.size == rewardingAdConfig.maxViewsPerPeriod) {
            int i3 = (rewardingAdWatchTimestamps.items[0] + rewardingAdConfig.periodLength) - timestampSeconds;
            i = i3;
            if (i3 < 0) {
                i = 0;
            }
        }
        if (rewardingAdWatchTimestamps.size != 0) {
            int i4 = (rewardingAdWatchTimestamps.items[rewardingAdWatchTimestamps.size - 1] + rewardingAdConfig.minViewDelay) - timestampSeconds;
            i2 = i4;
            if (i4 < 0) {
                i2 = 0;
            }
        }
        return StrictMath.max(secondsTillCanShowRewardAd, StrictMath.max(i, i2));
    }

    private void a(RewardingAdsType rewardingAdsType) {
        ProgressPrefs.i().purchase.addRewardingAdWatchTimestamp(rewardingAdsType, this.rewardingAdConfigs[rewardingAdsType.ordinal()].maxViewsPerPeriod);
        ProgressPrefs.i().requireSave();
    }

    public void showRewardingAd(RewardingAdsType rewardingAdsType, ObjectConsumer<Boolean> objectConsumer) {
        if (canShowRewardingAd(rewardingAdsType)) {
            if (Game.i.progressManager.isPremiumStatusActive()) {
                Game.i.statisticsManager.registerDelta(StatisticsType.RVW, 1.0d);
                ProgressPrefs.i().progress.registerVideoWatched();
                ProgressPrefs.i().requireSave();
                objectConsumer.accept(Boolean.TRUE);
                a(rewardingAdsType);
                return;
            }
            Runnable runnable = () -> {
                Game.i.statisticsManager.registerDelta(StatisticsType.RVW, 1.0d);
                ProgressPrefs.i().progress.registerVideoWatched();
                Game.i.analyticsManager.logRewardedVideoViewed(rewardingAdsType);
                objectConsumer.accept(Boolean.TRUE);
                ProgressPrefs.i().requireSave();
            };
            Game.i.actionResolver.showRewardAd(bool -> {
                bool.booleanValue();
            }, rewardingAdsType);
            runnable.run();
            a(rewardingAdsType);
            return;
        }
        f2413a.i(getSecondsTillAdIsReady(rewardingAdsType) + " seconds till ad is ready " + rewardingAdsAvailable() + SequenceUtils.SPACE + Game.i.actionResolver.canShowRewardAd() + SequenceUtils.SPACE + getSecondsTillAdIsReady(rewardingAdsType), new Object[0]);
        objectConsumer.accept(Boolean.FALSE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        Runnable runnable = () -> {
            Array<Transaction> transactions = ProgressPrefs.i().purchase.getTransactions();
            Array<String> validatedTransactions = ProgressPrefs.i().purchase.getValidatedTransactions();
            for (int i = 0; i < transactions.size; i++) {
                Transaction transaction = transactions.get(i);
                if (!validatedTransactions.contains(transaction.getStoreName() + ";" + (Game.i.authManager.getSessionId() == null ? "g" : FlexmarkHtmlConverter.U_NODE) + ";" + transaction.getOrderId(), false)) {
                    a(transaction);
                }
            }
        };
        if (Game.isLoaded()) {
            Threads.i().postRunnable(runnable);
        } else {
            Game.EVENTS.getListeners(GameLoad.class).add(gameLoad -> {
                Threads.i().postRunnable(runnable);
            });
        }
    }

    public int getPapersHourBasePrice(int i, float f) {
        int i2;
        long allTime = (long) Game.i.statisticsManager.getAllTime(StatisticsType.GPG);
        float allTime2 = (float) (((Game.i.statisticsManager.getAllTime(StatisticsType.PT) / 4.0d) / 60.0d) / 60.0d);
        int i3 = 0;
        if (allTime2 > 0.0f) {
            i3 = (int) (((float) allTime) / allTime2);
        }
        if (i3 < 0) {
            i3 = 0;
        } else if (i3 > i) {
            i3 = i;
        }
        int i4 = 10000;
        if (10000 < i3) {
            i4 = i3;
        }
        int round = MathUtils.round(i4 * f);
        if (round > 100000000) {
            i2 = round - (round % 10000000);
        } else if (round > 10000000) {
            i2 = round - (round % 1000000);
        } else if (round > 1000000) {
            i2 = round - (round % 100000);
        } else if (round > 100000) {
            i2 = round - (round % CGL.kCGLBadAttribute);
        } else {
            i2 = round - (round % 1000);
        }
        return i2;
    }

    public int getPapersHourBasePrice() {
        int i = 200000;
        try {
            i = MathUtils.clamp(Integer.parseInt(Game.i.settingsManager.getDynamicSetting(SettingsManager.DynamicSetting.IAP_GREEN_PAPER_MAX_PER_HOUR)), 100000, 5000000);
        } catch (Exception e) {
            f2413a.e("failed to parse dynamic setting", e);
        }
        return getPapersHourBasePrice(i, 1.0f + ((float) Game.i.gameValueManager.getSnapshot().getPercentValueAsMultiplier(GameValueType.SHOP_PURCHASE_BONUS)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Transaction transaction) {
        if (!Game.i.authManager.isSignedIn() || Game.i.authManager.getSessionId() == null) {
            return;
        }
        double percentValueAsMultiplier = 1.0f + ((float) Game.i.gameValueManager.getSnapshot().getPercentValueAsMultiplier(GameValueType.SHOP_PURCHASE_BONUS));
        int papersHourBasePrice = getPapersHourBasePrice();
        Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
        httpRequest.setUrl(Config.IAP_VALIDATION_URL);
        Json json = new Json(JsonWriter.OutputType.json);
        StringWriter stringWriter = new StringWriter();
        json.setWriter(stringWriter);
        final String str = transaction.getStoreName() + ";" + (Game.i.authManager.getSessionId() == null ? "g" : FlexmarkHtmlConverter.U_NODE) + ";" + transaction.getOrderId();
        f2413a.i("validating " + str, new Object[0]);
        json.writeObjectStart();
        json.writeValue("identifier", transaction.getIdentifier());
        json.writeValue("purchaseCost", Integer.valueOf(transaction.getPurchaseCost()));
        json.writeValue("storeName", transaction.getStoreName());
        json.writeValue("orderId", transaction.getOrderId());
        json.writeValue("requestId", transaction.getRequestId());
        json.writeValue("userId", transaction.getUserId());
        json.writeValue("purchaseTime", transaction.getPurchaseTime() == null ? null : Integer.valueOf((int) (transaction.getPurchaseTime().getTime() / 1000)));
        json.writeValue("purchaseText", transaction.getPurchaseText());
        json.writeValue("purchaseCostCurrency", transaction.getPurchaseCostCurrency());
        json.writeValue("reversalTime", transaction.getReversalTime() == null ? null : Integer.valueOf((int) (transaction.getReversalTime().getTime() / 1000)));
        json.writeValue("reversalText", transaction.getReversalText());
        json.writeValue("transactionData", transaction.getTransactionData());
        json.writeValue("transactionDataSignature", transaction.getTransactionDataSignature());
        json.writeObjectEnd();
        HashMap hashMap = new HashMap();
        hashMap.put("transaction", stringWriter.toString());
        hashMap.put("purchaseMultiplier", String.valueOf(percentValueAsMultiplier));
        hashMap.put("papersPerHour", String.valueOf(papersHourBasePrice));
        hashMap.put("sessionid", Game.i.authManager.getSessionId());
        httpRequest.setContent(HttpParametersUtils.convertHttpParameters(hashMap));
        Gdx.f881net.sendHttpRequest(httpRequest, new Net.HttpResponseListener(this) { // from class: com.prineside.tdi2.managers.PurchaseManager.2
            @Override // com.badlogic.gdx.Net.HttpResponseListener
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                try {
                    String resultAsString = httpResponse.getResultAsString();
                    PurchaseManager.f2413a.i(resultAsString, new Object[0]);
                    if (!new JsonReader().parse(resultAsString).getString("status", "error").equals("success")) {
                        PurchaseManager.f2413a.e("Log IAP Error: " + resultAsString, new Object[0]);
                        return;
                    }
                    Threads i = Threads.i();
                    String str2 = str;
                    i.runOnMainThread(() -> {
                        PurchaseManager.f2413a.i("Log IAP Success: " + resultAsString, new Object[0]);
                        ProgressPrefs.i().purchase.addValidatedTransaction(str2);
                        ProgressPrefs.i().requireSave();
                    });
                } catch (Exception e) {
                    PurchaseManager.f2413a.e("Log IAP Exception: " + e.getMessage(), e);
                }
            }

            @Override // com.badlogic.gdx.Net.HttpResponseListener
            public void failed(Throwable th) {
                PurchaseManager.f2413a.e("Log IAP Failed", th);
            }

            @Override // com.badlogic.gdx.Net.HttpResponseListener
            public void cancelled() {
                PurchaseManager.f2413a.e("Log IAP Cancelled", new Object[0]);
            }
        });
    }

    public IapOffersConfig getIapOfferConfig() {
        if (this.f2414b != null && Game.getTimestampMillis() - this.c < 60000) {
            return this.f2414b;
        }
        IapOffersConfig iapOffersConfig = new IapOffersConfig();
        try {
            String dynamicSetting = Game.i.settingsManager.getDynamicSetting(SettingsManager.DynamicSetting.IAP_OFFERS);
            if (dynamicSetting != null && dynamicSetting.length() != 0) {
                iapOffersConfig.readFromJson(dynamicSetting);
            }
        } catch (Throwable unused) {
            f2413a.w("failed to load IAP_OFFERS dynamic setting, using default values", new Object[0]);
        }
        this.f2414b = iapOffersConfig;
        this.c = Game.getTimestampMillis();
        return iapOffersConfig;
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void setup() {
        if (this.purchaseManager != null) {
            f2413a.i("Has manager", new Object[0]);
            PurchaseManagerConfig purchaseManagerConfig = new PurchaseManagerConfig();
            purchaseManagerConfig.addOffer(new Offer().setType(OfferType.ENTITLEMENT).setIdentifier(getPurchaseIdentifier(Config.ProductId.DOUBLE_GAIN)));
            purchaseManagerConfig.addOffer(new Offer().setType(OfferType.CONSUMABLE).setIdentifier(getPurchaseIdentifier(Config.ProductId.PACK_TINY)));
            purchaseManagerConfig.addOffer(new Offer().setType(OfferType.CONSUMABLE).setIdentifier(getPurchaseIdentifier(Config.ProductId.PACK_SMALL)));
            purchaseManagerConfig.addOffer(new Offer().setType(OfferType.CONSUMABLE).setIdentifier(getPurchaseIdentifier(Config.ProductId.PACK_MEDIUM)));
            purchaseManagerConfig.addOffer(new Offer().setType(OfferType.CONSUMABLE).setIdentifier(getPurchaseIdentifier(Config.ProductId.PACK_LARGE)));
            purchaseManagerConfig.addOffer(new Offer().setType(OfferType.CONSUMABLE).setIdentifier(getPurchaseIdentifier(Config.ProductId.PACK_HUGE)));
            purchaseManagerConfig.addOffer(new Offer().setType(OfferType.CONSUMABLE).setIdentifier(getPurchaseIdentifier(Config.ProductId.ACCELERATOR_PACK_TINY)));
            purchaseManagerConfig.addOffer(new Offer().setType(OfferType.CONSUMABLE).setIdentifier(getPurchaseIdentifier(Config.ProductId.ACCELERATOR_PACK_MEDIUM)));
            purchaseManagerConfig.addOffer(new Offer().setType(OfferType.CONSUMABLE).setIdentifier(getPurchaseIdentifier(Config.ProductId.ACCELERATOR_PACK_SMALL)));
            purchaseManagerConfig.addOffer(new Offer().setType(OfferType.CONSUMABLE).setIdentifier(getPurchaseIdentifier(Config.ProductId.ACCELERATOR_PACK_LARGE)));
            purchaseManagerConfig.addOffer(new Offer().setType(OfferType.CONSUMABLE).setIdentifier(getPurchaseIdentifier(Config.ProductId.ACCELERATOR_PACK_HUGE)));
            purchaseManagerConfig.addOffer(new Offer().setType(OfferType.CONSUMABLE).setIdentifier(getPurchaseIdentifier(Config.ProductId.SPECIAL_OFFER_C3)));
            purchaseManagerConfig.addStoreParam(PurchaseManagerConfig.STORE_NAME_ANDROID_GOOGLE, Config.PAYMENTS_STORE_PARAM_ANDROID_GOOGLE);
            this.purchaseManager.install(new PurchaseObserver() { // from class: com.prineside.tdi2.managers.PurchaseManager.3
                @Override // com.badlogic.gdx.pay.PurchaseObserver
                public void handleInstall() {
                    PurchaseManager.f2413a.i("handleInstall", new Object[0]);
                }

                @Override // com.badlogic.gdx.pay.PurchaseObserver
                public void handleInstallError(Throwable th) {
                    PurchaseManager.f2413a.i("handleInstallError", new Object[0]);
                    th.printStackTrace();
                }

                @Override // com.badlogic.gdx.pay.PurchaseObserver
                public void handleRestore(Transaction[] transactionArr) {
                    PurchaseManager.f2413a.i("handleRestore - " + transactionArr.length + " transactions", new Object[0]);
                    for (Transaction transaction : transactionArr) {
                        PurchaseManager.f2413a.i(transaction.getIdentifier(), new Object[0]);
                    }
                    Threads.i().runOnMainThread(() -> {
                        PurchaseManager.this.d.begin();
                        int i = PurchaseManager.this.d.size;
                        for (int i2 = 0; i2 < i; i2++) {
                            for (Transaction transaction2 : transactionArr) {
                                ((PurchaseManagerListener) PurchaseManager.this.d.get(i2)).purchased(transaction2);
                            }
                        }
                        PurchaseManager.this.d.end();
                        PurchaseManager.this.d.begin();
                        int i3 = PurchaseManager.this.d.size;
                        for (int i4 = 0; i4 < i3; i4++) {
                            ((PurchaseManagerListener) PurchaseManager.this.d.get(i4)).gotResponse("handleRestore", transactionArr);
                        }
                        PurchaseManager.this.d.end();
                    });
                }

                @Override // com.badlogic.gdx.pay.PurchaseObserver
                public void handleRestoreError(Throwable th) {
                    PurchaseManager.f2413a.e("handleRestoreError", th);
                    Threads.i().runOnMainThread(() -> {
                        PurchaseManager.this.d.begin();
                        int i = PurchaseManager.this.d.size;
                        for (int i2 = 0; i2 < i; i2++) {
                            ((PurchaseManagerListener) PurchaseManager.this.d.get(i2)).gotResponse("handleRestoreError", th);
                        }
                        PurchaseManager.this.d.end();
                    });
                }

                @Override // com.badlogic.gdx.pay.PurchaseObserver
                public void handlePurchase(Transaction transaction) {
                    PurchaseManager.f2413a.i("handlePurchase " + transaction.getIdentifier(), new Object[0]);
                    Threads.i().runOnMainThread(() -> {
                        PurchaseManager.this.d.begin();
                        int i = PurchaseManager.this.d.size;
                        for (int i2 = 0; i2 < i; i2++) {
                            ((PurchaseManagerListener) PurchaseManager.this.d.get(i2)).purchased(transaction);
                        }
                        PurchaseManager.this.d.end();
                        PurchaseManager.this.d.begin();
                        int i3 = PurchaseManager.this.d.size;
                        for (int i4 = 0; i4 < i3; i4++) {
                            ((PurchaseManagerListener) PurchaseManager.this.d.get(i4)).gotResponse("handlePurchase", transaction);
                        }
                        PurchaseManager.this.d.end();
                    });
                }

                @Override // com.badlogic.gdx.pay.PurchaseObserver
                public void handlePurchaseError(Throwable th) {
                    PurchaseManager.f2413a.e("handlePurchaseError", th);
                    Threads.i().runOnMainThread(() -> {
                        PurchaseManager.this.d.begin();
                        int i = PurchaseManager.this.d.size;
                        for (int i2 = 0; i2 < i; i2++) {
                            ((PurchaseManagerListener) PurchaseManager.this.d.get(i2)).gotResponse("handlePurchaseError", th);
                        }
                        PurchaseManager.this.d.end();
                    });
                }

                @Override // com.badlogic.gdx.pay.PurchaseObserver
                public void handlePurchaseCanceled() {
                    PurchaseManager.f2413a.i("handlePurchaseCanceled", new Object[0]);
                    Threads.i().runOnMainThread(() -> {
                        PurchaseManager.this.d.begin();
                        int i = PurchaseManager.this.d.size;
                        for (int i2 = 0; i2 < i; i2++) {
                            ((PurchaseManagerListener) PurchaseManager.this.d.get(i2)).gotResponse("handlePurchaseCanceled", null);
                        }
                        PurchaseManager.this.d.end();
                    });
                }
            }, purchaseManagerConfig, true);
        } else {
            f2413a.i("Has no manager :(", new Object[0]);
        }
        Game.i.preferencesManager.addListener(new PreferencesManager.PreferencesManagerListener.PreferencesManagerListenerAdapter() { // from class: com.prineside.tdi2.managers.PurchaseManager.4
            @Override // com.prineside.tdi2.managers.PreferencesManager.PreferencesManagerListener.PreferencesManagerListenerAdapter, com.prineside.tdi2.managers.PreferencesManager.PreferencesManagerListener
            public void reloaded() {
                PurchaseManager.this.b();
            }
        });
        b();
        addListener(new PurchaseManagerListener() { // from class: com.prineside.tdi2.managers.PurchaseManager.5
            @Override // com.prineside.tdi2.managers.PurchaseManager.PurchaseManagerListener
            public void purchased(Transaction transaction) {
                if (Gdx.app.getType() != Application.ApplicationType.Android || transaction.getOrderId().startsWith("GPA.")) {
                    try {
                        PurchaseManager.this.a(transaction);
                    } catch (Exception e) {
                        PurchaseManager.f2413a.e("failed to send IAP for validation", e);
                    }
                    IapOffersConfig iapOfferConfig = PurchaseManager.this.getIapOfferConfig();
                    Config.ProductId productId = PurchaseManager.this.getProductId(transaction.getIdentifier());
                    switch (AnonymousClass6.f2423a[productId.ordinal()]) {
                        case 1:
                            if (Game.i.progressManager.hasTemporaryDoubleGain()) {
                                Game.i.progressManager.addItems(Item.D.ACCELERATOR, DoubleGainShardItem.getAcceleratorsForDuration(Game.i.progressManager.getTempDoubleGainDurationLeft()), "double_gain_iap_temp_refund");
                            }
                            Game.i.authManager.addProfileStatusLocal("transaction|" + transaction.getStoreName().toLowerCase(Locale.US) + "|" + transaction.getOrderId().toLowerCase(Locale.US), Config.PROFILE_STATUS_DOUBLE_GAIN, -1);
                            Game.i.progressManager.enableDoubleGainPermanently();
                            break;
                        case 2:
                            int purchaseBaseAmount = iapOfferConfig.getPurchaseBaseAmount(productId) + iapOfferConfig.getBonusPurchaseAmount(productId);
                            Game.i.progressManager.addGreenPapers(purchaseBaseAmount, "iap_pack_tiny");
                            IssuedItems issuedItems = new IssuedItems(IssuedItems.IssueReason.PURCHASE, Game.getTimestampSeconds());
                            issuedItems.items.add(new ItemStack(Item.D.GREEN_PAPER, purchaseBaseAmount));
                            ItemStack additionalItem = iapOfferConfig.getAdditionalItem(productId);
                            if (additionalItem != null) {
                                issuedItems.items.add(new ItemStack(additionalItem));
                                Game.i.progressManager.addItemStack(additionalItem, "iap_pack_tiny");
                            }
                            Game.i.progressManager.addIssuedPrizes(issuedItems, true);
                            Game.i.progressManager.showNewlyIssuedPrizesPopup();
                            break;
                        case 3:
                            int purchaseBaseAmount2 = iapOfferConfig.getPurchaseBaseAmount(productId) + iapOfferConfig.getBonusPurchaseAmount(productId);
                            Game.i.progressManager.addGreenPapers(purchaseBaseAmount2, "iap_pack_small");
                            IssuedItems issuedItems2 = new IssuedItems(IssuedItems.IssueReason.PURCHASE, Game.getTimestampSeconds());
                            issuedItems2.items.add(new ItemStack(Item.D.GREEN_PAPER, purchaseBaseAmount2));
                            ItemStack additionalItem2 = iapOfferConfig.getAdditionalItem(productId);
                            if (additionalItem2 != null) {
                                issuedItems2.items.add(new ItemStack(additionalItem2));
                                Game.i.progressManager.addItemStack(additionalItem2, "iap_pack_small");
                            }
                            Game.i.progressManager.addIssuedPrizes(issuedItems2, true);
                            Game.i.progressManager.showNewlyIssuedPrizesPopup();
                            break;
                        case 4:
                            int purchaseBaseAmount3 = iapOfferConfig.getPurchaseBaseAmount(productId) + iapOfferConfig.getBonusPurchaseAmount(productId);
                            Game.i.progressManager.addGreenPapers(purchaseBaseAmount3, "iap_pack_medium");
                            IssuedItems issuedItems3 = new IssuedItems(IssuedItems.IssueReason.PURCHASE, Game.getTimestampSeconds());
                            issuedItems3.items.add(new ItemStack(Item.D.GREEN_PAPER, purchaseBaseAmount3));
                            ItemStack additionalItem3 = iapOfferConfig.getAdditionalItem(productId);
                            if (additionalItem3 != null) {
                                issuedItems3.items.add(new ItemStack(additionalItem3));
                                Game.i.progressManager.addItemStack(additionalItem3, "iap_pack_medium");
                            }
                            Game.i.progressManager.addIssuedPrizes(issuedItems3, true);
                            Game.i.progressManager.showNewlyIssuedPrizesPopup();
                            break;
                        case 5:
                            int purchaseBaseAmount4 = iapOfferConfig.getPurchaseBaseAmount(productId) + iapOfferConfig.getBonusPurchaseAmount(productId);
                            Game.i.progressManager.addGreenPapers(purchaseBaseAmount4, "iap_pack_large");
                            IssuedItems issuedItems4 = new IssuedItems(IssuedItems.IssueReason.PURCHASE, Game.getTimestampSeconds());
                            issuedItems4.items.add(new ItemStack(Item.D.GREEN_PAPER, purchaseBaseAmount4));
                            ItemStack additionalItem4 = iapOfferConfig.getAdditionalItem(productId);
                            if (additionalItem4 != null) {
                                issuedItems4.items.add(new ItemStack(additionalItem4));
                                Game.i.progressManager.addItemStack(additionalItem4, "iap_pack_large");
                            }
                            Game.i.progressManager.addIssuedPrizes(issuedItems4, true);
                            Game.i.progressManager.showNewlyIssuedPrizesPopup();
                            break;
                        case 6:
                            int purchaseBaseAmount5 = iapOfferConfig.getPurchaseBaseAmount(productId) + iapOfferConfig.getBonusPurchaseAmount(productId);
                            Game.i.progressManager.addGreenPapers(purchaseBaseAmount5, "iap_pack_huge");
                            IssuedItems issuedItems5 = new IssuedItems(IssuedItems.IssueReason.PURCHASE, Game.getTimestampSeconds());
                            issuedItems5.items.add(new ItemStack(Item.D.GREEN_PAPER, purchaseBaseAmount5));
                            ItemStack additionalItem5 = iapOfferConfig.getAdditionalItem(productId);
                            if (additionalItem5 != null) {
                                issuedItems5.items.add(new ItemStack(additionalItem5));
                                Game.i.progressManager.addItemStack(additionalItem5, "iap_pack_huge");
                            }
                            Game.i.progressManager.addIssuedPrizes(issuedItems5, true);
                            Game.i.progressManager.showNewlyIssuedPrizesPopup();
                            break;
                        case 7:
                            int purchaseBaseAmount6 = iapOfferConfig.getPurchaseBaseAmount(productId) + iapOfferConfig.getBonusPurchaseAmount(productId);
                            Game.i.progressManager.addAccelerators(purchaseBaseAmount6, "iap_acc_tiny");
                            IssuedItems issuedItems6 = new IssuedItems(IssuedItems.IssueReason.PURCHASE, Game.getTimestampSeconds());
                            issuedItems6.items.add(new ItemStack(Item.D.ACCELERATOR, purchaseBaseAmount6));
                            ItemStack additionalItem6 = iapOfferConfig.getAdditionalItem(productId);
                            if (additionalItem6 != null) {
                                issuedItems6.items.add(new ItemStack(additionalItem6));
                                Game.i.progressManager.addItemStack(additionalItem6, "iap_acc_tiny");
                            }
                            Game.i.progressManager.addIssuedPrizes(issuedItems6, true);
                            Game.i.progressManager.showNewlyIssuedPrizesPopup();
                            break;
                        case 8:
                            int purchaseBaseAmount7 = iapOfferConfig.getPurchaseBaseAmount(productId) + iapOfferConfig.getBonusPurchaseAmount(productId);
                            Game.i.progressManager.addAccelerators(purchaseBaseAmount7, "iap_acc_small");
                            IssuedItems issuedItems7 = new IssuedItems(IssuedItems.IssueReason.PURCHASE, Game.getTimestampSeconds());
                            issuedItems7.items.add(new ItemStack(Item.D.ACCELERATOR, purchaseBaseAmount7));
                            ItemStack additionalItem7 = iapOfferConfig.getAdditionalItem(productId);
                            if (additionalItem7 != null) {
                                issuedItems7.items.add(new ItemStack(additionalItem7));
                                Game.i.progressManager.addItemStack(additionalItem7, "iap_acc_small");
                            }
                            Game.i.progressManager.addIssuedPrizes(issuedItems7, true);
                            Game.i.progressManager.showNewlyIssuedPrizesPopup();
                            break;
                        case 9:
                            int purchaseBaseAmount8 = iapOfferConfig.getPurchaseBaseAmount(productId) + iapOfferConfig.getBonusPurchaseAmount(productId);
                            Game.i.progressManager.addAccelerators(purchaseBaseAmount8, "iap_acc_medium");
                            IssuedItems issuedItems8 = new IssuedItems(IssuedItems.IssueReason.PURCHASE, Game.getTimestampSeconds());
                            issuedItems8.items.add(new ItemStack(Item.D.ACCELERATOR, purchaseBaseAmount8));
                            ItemStack additionalItem8 = iapOfferConfig.getAdditionalItem(productId);
                            if (additionalItem8 != null) {
                                issuedItems8.items.add(new ItemStack(additionalItem8));
                                Game.i.progressManager.addItemStack(additionalItem8, "iap_acc_medium");
                            }
                            Game.i.progressManager.addIssuedPrizes(issuedItems8, true);
                            Game.i.progressManager.showNewlyIssuedPrizesPopup();
                            break;
                        case 10:
                            int purchaseBaseAmount9 = iapOfferConfig.getPurchaseBaseAmount(productId) + iapOfferConfig.getBonusPurchaseAmount(productId);
                            Game.i.progressManager.addAccelerators(purchaseBaseAmount9, "iap_acc_large");
                            IssuedItems issuedItems9 = new IssuedItems(IssuedItems.IssueReason.PURCHASE, Game.getTimestampSeconds());
                            issuedItems9.items.add(new ItemStack(Item.D.ACCELERATOR, purchaseBaseAmount9));
                            ItemStack additionalItem9 = iapOfferConfig.getAdditionalItem(productId);
                            if (additionalItem9 != null) {
                                issuedItems9.items.add(new ItemStack(additionalItem9));
                                Game.i.progressManager.addItemStack(additionalItem9, "iap_acc_large");
                            }
                            Game.i.progressManager.addIssuedPrizes(issuedItems9, true);
                            Game.i.progressManager.showNewlyIssuedPrizesPopup();
                            break;
                        case 11:
                            int purchaseBaseAmount10 = iapOfferConfig.getPurchaseBaseAmount(productId) + iapOfferConfig.getBonusPurchaseAmount(productId);
                            Game.i.progressManager.addAccelerators(purchaseBaseAmount10, "iap_acc_huge");
                            IssuedItems issuedItems10 = new IssuedItems(IssuedItems.IssueReason.PURCHASE, Game.getTimestampSeconds());
                            issuedItems10.items.add(new ItemStack(Item.D.ACCELERATOR, purchaseBaseAmount10));
                            ItemStack additionalItem10 = iapOfferConfig.getAdditionalItem(productId);
                            if (additionalItem10 != null) {
                                issuedItems10.items.add(new ItemStack(additionalItem10));
                                Game.i.progressManager.addItemStack(additionalItem10, "iap_acc_huge");
                            }
                            Game.i.progressManager.addIssuedPrizes(issuedItems10, true);
                            Game.i.progressManager.showNewlyIssuedPrizesPopup();
                            break;
                    }
                    boolean z = false;
                    PP_Purchase pP_Purchase = ProgressPrefs.i().purchase;
                    Array<Transaction> transactions = pP_Purchase.getTransactions();
                    int i = 0;
                    while (true) {
                        if (i < transactions.size) {
                            Transaction transaction2 = transactions.get(i);
                            if (!transaction2.getStoreName().equals(transaction.getStoreName()) || !transaction2.getOrderId().equals(transaction.getOrderId())) {
                                i++;
                            } else {
                                z = true;
                                PurchaseManager.f2413a.i("skipped storing transaction " + transaction + " - already stored", new Object[0]);
                            }
                        }
                    }
                    if (!z) {
                        if (PurchaseManagerConfig.STORE_NAME_ANDROID_GOOGLE.equals(transaction.getStoreName())) {
                            if (!transaction.getOrderId().startsWith("GPA.")) {
                                PurchaseManager.f2413a.e("invalid GooglePlay order id: %s, account may be banned", transaction.getOrderId());
                                pP_Purchase.addTransaction(transaction);
                                ProgressPrefs.i().requireSave();
                            } else {
                                PurchaseManager.f2413a.d("GooglePlay order ID looks valid", new Object[0]);
                            }
                        }
                        pP_Purchase.addTransaction(transaction);
                        ProgressPrefs.i().requireSave();
                    }
                    Game.i.analyticsManager.logIAP(productId, transaction);
                    return;
                }
                PurchaseManager.f2413a.e("invalid getOrderId: " + transaction.getOrderId(), new Object[0]);
            }

            @Override // com.prineside.tdi2.managers.PurchaseManager.PurchaseManagerListener
            public void gotResponse(String str, Object obj) {
                if (str.equals("handlePurchaseError")) {
                    PurchaseManager.f2413a.e("handlePurchaseError - trying restore", new Object[0]);
                    PurchaseManager.this.purchaseManager.purchaseRestore();
                }
            }
        });
    }

    public boolean isPurchasesEnabled() {
        return this.purchaseManager != null && this.purchaseManager.installed();
    }

    public void addListener(PurchaseManagerListener purchaseManagerListener) {
        if (purchaseManagerListener == null) {
            throw new IllegalArgumentException("listener is null");
        }
        if (!this.d.contains(purchaseManagerListener, true)) {
            this.d.add(purchaseManagerListener);
        }
    }

    public void removeListener(PurchaseManagerListener purchaseManagerListener) {
        if (purchaseManagerListener == null) {
            throw new IllegalArgumentException("listener is null");
        }
        this.d.removeValue(purchaseManagerListener, true);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/PurchaseManager$RewardingAdConfig.class */
    public static final class RewardingAdConfig {
        public int periodLength;
        public int maxViewsPerPeriod;
        public int minViewDelay;

        public RewardingAdConfig(int i, int i2, int i3) {
            if (i2 <= 0) {
                throw new IllegalArgumentException("maxViewsPerPeriod must be > 0: " + i2);
            }
            this.periodLength = i;
            this.maxViewsPerPeriod = i2;
            this.minViewDelay = i3;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/PurchaseManager$PurchaseManagerListener.class */
    public interface PurchaseManagerListener {
        void purchased(Transaction transaction);

        void gotResponse(String str, Object obj);

        /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/PurchaseManager$PurchaseManagerListener$PurchaseManagerListenerAdapter.class */
        public static abstract class PurchaseManagerListenerAdapter implements PurchaseManagerListener {
            @Override // com.prineside.tdi2.managers.PurchaseManager.PurchaseManagerListener
            public void purchased(Transaction transaction) {
            }

            @Override // com.prineside.tdi2.managers.PurchaseManager.PurchaseManagerListener
            public void gotResponse(String str, Object obj) {
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/PurchaseManager$IapOffersConfig.class */
    public static final class IapOffersConfig {
        public float paperTinyAmount = 1.0f;
        public float paperSmallAmount = 3.0f;
        public float paperMediumAmount = 10.0f;
        public float paperLargeAmount = 25.0f;
        public float paperHugeAmount = 50.0f;
        public int paperTinyBonus = 0;
        public int paperSmallBonus = 15;
        public int paperMediumBonus = 35;
        public int paperLargeBonus = 50;
        public int paperHugeBonus = 100;
        public int accTinyAmount = 100;
        public int accSmallAmount = 300;
        public int accMediumAmount = 1000;
        public int accLargeAmount = 2500;
        public int accHugeAmount = 5000;
        public int accTinyBonus = 0;
        public int accSmallBonus = 15;
        public int accMediumBonus = 35;
        public int accLargeBonus = 50;
        public int accHugeBonus = 100;
        public ItemStack paperTinyItem = null;
        public ItemStack paperSmallItem = new ItemStack(Item.D.RARITY_BOOST, 1);
        public ItemStack paperMediumItem = new ItemStack(Item.D.RARITY_BOOST, 3);
        public ItemStack paperLargeItem = new ItemStack(Item.D.RARITY_BOOST, 10);
        public ItemStack paperHugeItem = new ItemStack(Item.D.RARITY_BOOST, 25);
        public ItemStack accTinyItem = null;
        public ItemStack accSmallItem = null;
        public ItemStack accMediumItem = null;
        public ItemStack accLargeItem = null;
        public ItemStack accHugeItem = null;

        public final void readFromJson(String str) {
            try {
                JsonValue parse = new JsonReader().parse(str);
                this.paperTinyAmount = parse.getFloat("paperTinyAmount", this.paperTinyAmount);
                this.paperSmallAmount = parse.getFloat("paperSmallAmount", this.paperSmallAmount);
                this.paperMediumAmount = parse.getFloat("paperMediumAmount", this.paperMediumAmount);
                this.paperLargeAmount = parse.getFloat("paperLargeAmount", this.paperLargeAmount);
                this.paperHugeAmount = parse.getFloat("paperHugeAmount", this.paperHugeAmount);
                this.paperTinyBonus = parse.getInt("paperTinyBonus", this.paperTinyBonus);
                this.paperSmallBonus = parse.getInt("paperSmallBonus", this.paperSmallBonus);
                this.paperMediumBonus = parse.getInt("paperMediumBonus", this.paperMediumBonus);
                this.paperLargeBonus = parse.getInt("paperLargeBonus", this.paperLargeBonus);
                this.paperHugeBonus = parse.getInt("paperHugeBonus", this.paperHugeBonus);
                this.accTinyAmount = parse.getInt("accTinyAmount", this.accTinyAmount);
                this.accSmallAmount = parse.getInt("accSmallAmount", this.accSmallAmount);
                this.accMediumAmount = parse.getInt("accMediumAmount", this.accMediumAmount);
                this.accLargeAmount = parse.getInt("accLargeAmount", this.accLargeAmount);
                this.accHugeAmount = parse.getInt("accHugeAmount", this.accHugeAmount);
                this.accTinyBonus = parse.getInt("accTinyBonus", this.accTinyBonus);
                this.accSmallBonus = parse.getInt("accSmallBonus", this.accSmallBonus);
                this.accMediumBonus = parse.getInt("accMediumBonus", this.accMediumBonus);
                this.accLargeBonus = parse.getInt("accLargeBonus", this.accLargeBonus);
                this.accHugeBonus = parse.getInt("accHugeBonus", this.accHugeBonus);
                if (parse.get("paperTinyItem") != null) {
                    this.paperTinyItem = ItemStack.fromJsonOrNull(parse.get("paperTinyItem"));
                }
                if (parse.get("paperSmallItem") != null) {
                    this.paperSmallItem = ItemStack.fromJsonOrNull(parse.get("paperSmallItem"));
                }
                if (parse.get("paperMediumItem") != null) {
                    this.paperMediumItem = ItemStack.fromJsonOrNull(parse.get("paperMediumItem"));
                }
                if (parse.get("paperLargeItem") != null) {
                    this.paperLargeItem = ItemStack.fromJsonOrNull(parse.get("paperLargeItem"));
                }
                if (parse.get("paperHugeItem") != null) {
                    this.paperHugeItem = ItemStack.fromJsonOrNull(parse.get("paperHugeItem"));
                }
                if (parse.get("accTinyItem") != null) {
                    this.accTinyItem = ItemStack.fromJsonOrNull(parse.get("accTinyItem"));
                }
                if (parse.get("accSmallItem") != null) {
                    this.accSmallItem = ItemStack.fromJsonOrNull(parse.get("accSmallItem"));
                }
                if (parse.get("accMediumItem") != null) {
                    this.accMediumItem = ItemStack.fromJsonOrNull(parse.get("accMediumItem"));
                }
                if (parse.get("accLargeItem") != null) {
                    this.accLargeItem = ItemStack.fromJsonOrNull(parse.get("accLargeItem"));
                }
                if (parse.get("accHugeItem") != null) {
                    this.accHugeItem = ItemStack.fromJsonOrNull(parse.get("accHugeItem"));
                }
            } catch (Exception e) {
                PurchaseManager.f2413a.e("failed to parse IapOffersConfig json, using default values", e);
            }
        }

        public final int getPurchaseBonus(Config.ProductId productId) {
            switch (productId) {
                case PACK_TINY:
                    return this.paperTinyBonus;
                case PACK_SMALL:
                    return this.paperSmallBonus;
                case PACK_MEDIUM:
                    return this.paperMediumBonus;
                case PACK_LARGE:
                    return this.paperLargeBonus;
                case PACK_HUGE:
                    return this.paperHugeBonus;
                case ACCELERATOR_PACK_TINY:
                    return this.accTinyBonus;
                case ACCELERATOR_PACK_SMALL:
                    return this.accSmallBonus;
                case ACCELERATOR_PACK_MEDIUM:
                    return this.accMediumBonus;
                case ACCELERATOR_PACK_LARGE:
                    return this.accLargeBonus;
                case ACCELERATOR_PACK_HUGE:
                    return this.accHugeBonus;
                default:
                    return 0;
            }
        }

        public final int getPurchaseBaseAmount(Config.ProductId productId) {
            int papersHourBasePrice = Game.i.purchaseManager.getPapersHourBasePrice();
            switch (productId) {
                case PACK_TINY:
                    return MathUtils.round(papersHourBasePrice * this.paperTinyAmount);
                case PACK_SMALL:
                    return MathUtils.round(papersHourBasePrice * this.paperSmallAmount);
                case PACK_MEDIUM:
                    return MathUtils.round(papersHourBasePrice * this.paperMediumAmount);
                case PACK_LARGE:
                    return MathUtils.round(papersHourBasePrice * this.paperLargeAmount);
                case PACK_HUGE:
                    return MathUtils.round(papersHourBasePrice * this.paperHugeAmount);
                case ACCELERATOR_PACK_TINY:
                    return this.accTinyAmount;
                case ACCELERATOR_PACK_SMALL:
                    return this.accSmallAmount;
                case ACCELERATOR_PACK_MEDIUM:
                    return this.accMediumAmount;
                case ACCELERATOR_PACK_LARGE:
                    return this.accLargeAmount;
                case ACCELERATOR_PACK_HUGE:
                    return this.accHugeAmount;
                default:
                    throw new IllegalArgumentException("Invalid product id: " + productId);
            }
        }

        public final int getBonusPurchaseAmount(Config.ProductId productId) {
            return MathUtils.round(getPurchaseBaseAmount(productId) * getPurchaseBonus(productId) * 0.01f);
        }

        @Null
        public final ItemStack getAdditionalItem(Config.ProductId productId) {
            switch (productId) {
                case PACK_TINY:
                    return this.paperTinyItem;
                case PACK_SMALL:
                    return this.paperSmallItem;
                case PACK_MEDIUM:
                    return this.paperMediumItem;
                case PACK_LARGE:
                    return this.paperLargeItem;
                case PACK_HUGE:
                    return this.paperHugeItem;
                case ACCELERATOR_PACK_TINY:
                    return this.accTinyItem;
                case ACCELERATOR_PACK_SMALL:
                    return this.accSmallItem;
                case ACCELERATOR_PACK_MEDIUM:
                    return this.accMediumItem;
                case ACCELERATOR_PACK_LARGE:
                    return this.accLargeItem;
                case ACCELERATOR_PACK_HUGE:
                    return this.accHugeItem;
                default:
                    return null;
            }
        }
    }
}
