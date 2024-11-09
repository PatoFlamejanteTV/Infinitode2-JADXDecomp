package com.prineside.tdi2.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.pay.Transaction;
import com.badlogic.gdx.utils.Array;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Manager;
import com.prineside.tdi2.managers.PurchaseManager;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.utils.FastRandom;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import net.bytebuddy.utility.JavaConstant;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/AnalyticsManager.class */
public final class AnalyticsManager extends Manager.ManagerAdapter {
    public static final boolean TIME_SERIES_DB_ENABLED = false;
    public static final int MAX_QUEUE_SIZE = 500;
    public static final int HEARTBEAT_INTERVAL = 50;
    public static final int FLUSH_INTERVAL = 60;
    public static final int FLUSH_CHECK_INTERVAL = 10;

    /* renamed from: a, reason: collision with root package name */
    private final boolean f2274a;

    /* renamed from: b, reason: collision with root package name */
    private int f2275b;

    static {
        TLog.forClass(AnalyticsManager.class);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/AnalyticsManager$Serializer.class */
    public static class Serializer extends SingletonSerializer<AnalyticsManager> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public AnalyticsManager read() {
            return Game.i.analyticsManager;
        }
    }

    public AnalyticsManager() {
        new Array(true, 1, Event.class);
        Game.getTimestampSeconds();
        this.f2274a = (Game.i.actionResolver.isAppModified() || Config.isHeadless()) ? false : true;
        FastRandom.getLongUUID();
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public final void setup() {
        Event event = new Event("game_start", (byte) 0);
        event.a("os", Gdx.app.getType().name());
        event.a("locale", Game.i.actionResolver.getDefaultLocale());
        event.a("osv", new StringBuilder().append(Gdx.app.getVersion()).toString());
        event.a("loading_time", Long.valueOf(Game.getRealTickCount()));
    }

    /* renamed from: com.prineside.tdi2.managers.AnalyticsManager$1, reason: invalid class name */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/AnalyticsManager$1.class */
    class AnonymousClass1 implements Net.HttpResponseListener {
        @Override // com.badlogic.gdx.Net.HttpResponseListener
        public void handleHttpResponse(Net.HttpResponse httpResponse) {
            httpResponse.getStatus().getStatusCode();
        }

        @Override // com.badlogic.gdx.Net.HttpResponseListener
        public void failed(Throwable th) {
        }

        @Override // com.badlogic.gdx.Net.HttpResponseListener
        public void cancelled() {
        }
    }

    public final void logLevelStarted(String str, String str2) {
        if (this.f2274a) {
            Event event = new Event("levelStarted", (byte) 0);
            event.a("type", str);
            event.a("levelName", str2);
        }
    }

    public final void logLevelFinished(String str, String str2, int i, int i2) {
        if (this.f2274a) {
            Event event = new Event("levelFinished", (byte) 0);
            event.a("type", str);
            event.a("levelName", str2);
            event.a("realTime", Integer.valueOf(i));
            event.a("inGameTime", Integer.valueOf(i2));
        }
    }

    public final void logCurrencyReceived(String str, String str2, int i) {
        if (this.f2274a) {
            Event event = new Event("currencyReceived", (byte) 0);
            event.a("currencyName", str);
            event.a("source", str2);
            event.a("amount", Integer.valueOf(i));
            Game.i.actionResolver.logCurrencyReceived(str, str2, i);
        }
    }

    public final void logCurrencySpent(String str, String str2, int i) {
        if (this.f2274a) {
            Event event = new Event("currencySpent", (byte) 0);
            event.a("onItem", str);
            event.a("currencyName", str2);
            event.a("amount", Integer.valueOf(i));
            Game.i.actionResolver.logCurrencySpent(str, str2, i);
        }
    }

    public final void logShopOfferPurchased(String str, int i, String str2, int i2) {
        if (this.f2274a) {
            Game.i.actionResolver.logShopOfferPurchased(str, i, str2, i2);
        }
    }

    public final void logSignedIn(String str) {
        if (this.f2274a) {
            new Event("signedIn", (byte) 0).a("method", str);
            Game.i.actionResolver.logLogined(str);
        }
    }

    public final void logSignedUp(String str) {
        if (this.f2274a) {
            new Event("signedUp", (byte) 0).a("method", str);
            Game.i.actionResolver.logSignedUp(str);
        }
    }

    public final void logCustomEvent(String str, String[] strArr, Object[] objArr) {
        if (this.f2274a) {
            Event event = new Event(str, (byte) 0);
            for (int i = 0; i < strArr.length; i += 2) {
                event.a(strArr[i], strArr[i + 1]);
            }
            for (int i2 = 0; i2 < objArr.length; i2 += 2) {
                event.a((String) objArr[i2], objArr[i2 + 1]);
            }
            Game.i.actionResolver.logCustomEvent(str, strArr);
        }
    }

    public final void logIAP(Config.ProductId productId, Transaction transaction) {
        if (this.f2274a) {
            Event event = new Event("iap", (byte) 0);
            event.a("product", productId.name());
            event.a("order", (Object) transaction.getOrderId());
            Game.i.actionResolver.logIAP(productId, transaction);
        }
    }

    public final void logRewardedVideoViewed(PurchaseManager.RewardingAdsType rewardingAdsType) {
        if (this.f2274a) {
            Event event = new Event("videoWatched", (byte) 0);
            event.a("type", rewardingAdsType.name());
            event.a("c", (Object) 1);
            Game.i.actionResolver.logRewardedVideoViewed(rewardingAdsType);
        }
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public final void preRender(float f) {
        if (!this.f2274a || Game.getTimestampSeconds() - this.f2275b < 50) {
            return;
        }
        new Event("player_online", (byte) 0).a("screen", Game.i.screenManager.getCurrentScreen().getClass().getSimpleName());
        this.f2275b = Game.getTimestampSeconds();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/AnalyticsManager$Event.class */
    public static final class Event {

        /* renamed from: a, reason: collision with root package name */
        private final Array<Object> f2276a;

        /* renamed from: b, reason: collision with root package name */
        private final Array<String> f2277b;

        /* synthetic */ Event(String str, byte b2) {
            this(str);
        }

        private Event(String str) {
            this.f2276a = new Array<>(true, 2);
            this.f2277b = new Array<>(true, 2, String.class);
            if (str.startsWith(JavaConstant.Dynamic.DEFAULT_NAME)) {
                throw new IllegalArgumentException("measurement name can not start with _");
            }
            Game.getTimestampSeconds();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(String str, String str2) {
            if (str.startsWith(JavaConstant.Dynamic.DEFAULT_NAME)) {
                throw new IllegalArgumentException("key can not start with _");
            }
            if (str2 == null) {
                return;
            }
            String replaceAll = str.replaceAll(SequenceUtils.SPACE, "").replaceAll(SequenceUtils.EOL, "");
            String replaceAll2 = str2.replaceAll(SequenceUtils.EOL, SequenceUtils.SPACE).replaceAll("'", "").replaceAll("\"", "");
            this.f2277b.add(replaceAll);
            this.f2277b.add(replaceAll2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(String str, Object obj) {
            if (str.startsWith(JavaConstant.Dynamic.DEFAULT_NAME)) {
                throw new IllegalArgumentException("key can not start with _");
            }
            if (obj == null) {
                return;
            }
            String replaceAll = str.replaceAll(SequenceUtils.SPACE, "").replaceAll(SequenceUtils.EOL, "");
            if (obj instanceof String) {
                obj = ((String) obj).replaceAll(SequenceUtils.EOL, SequenceUtils.SPACE).replaceAll("'", "").replaceAll("\"", "");
            }
            this.f2276a.add(replaceAll);
            this.f2276a.add(obj);
        }
    }
}
