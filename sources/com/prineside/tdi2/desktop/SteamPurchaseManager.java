package com.prineside.tdi2.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpParametersUtils;
import com.badlogic.gdx.pay.Information;
import com.badlogic.gdx.pay.InvalidItemException;
import com.badlogic.gdx.pay.PurchaseManager;
import com.badlogic.gdx.pay.PurchaseManagerConfig;
import com.badlogic.gdx.pay.PurchaseObserver;
import com.badlogic.gdx.pay.Transaction;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.codedisaster.steamworks.SteamID;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.managers.AuthManager;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.html.Attribute;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/desktop/SteamPurchaseManager.class */
public class SteamPurchaseManager implements PurchaseManager {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f1838a = TLog.forClass(SteamPurchaseManager.class);

    /* renamed from: b, reason: collision with root package name */
    private PurchaseObserver f1839b;
    private boolean c;
    private final Map<String, SteamProductInfo> d = new ConcurrentHashMap();

    public SteamPurchaseManager() {
        f1838a.i("construct", new Object[0]);
    }

    @Override // com.badlogic.gdx.pay.PurchaseManager
    public String storeName() {
        return PurchaseManagerConfig.STORE_NAME_DESKTOP_STEAM;
    }

    @Override // com.badlogic.gdx.pay.PurchaseManager
    public void install(PurchaseObserver purchaseObserver, PurchaseManagerConfig purchaseManagerConfig, boolean z) {
        f1838a.i("install", new Object[0]);
        this.f1839b = purchaseObserver;
        this.c = false;
        Game.i.authManager.addListener(new AuthManager.AuthManagerListener.AuthManagerListenerAdapter() { // from class: com.prineside.tdi2.desktop.SteamPurchaseManager.1
            @Override // com.prineside.tdi2.managers.AuthManager.AuthManagerListener.AuthManagerListenerAdapter, com.prineside.tdi2.managers.AuthManager.AuthManagerListener
            public void signInStatusUpdated() {
                Gdx.app.postRunnable(() -> {
                    SteamPurchaseManager.this.c();
                });
            }

            @Override // com.prineside.tdi2.managers.AuthManager.AuthManagerListener.AuthManagerListenerAdapter, com.prineside.tdi2.managers.AuthManager.AuthManagerListener
            public void stateUpdated() {
                Gdx.app.postRunnable(() -> {
                    SteamPurchaseManager.this.c();
                });
            }
        });
        c();
    }

    public void onMicroTxnAuthorization(int i, long j, boolean z) {
        if (!z) {
            Gdx.app.postRunnable(() -> {
                this.f1839b.handlePurchaseCanceled();
            });
            return;
        }
        Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
        httpRequest.setUrl(Config.STEAM_TXN_FINALIZE_URL);
        HashMap hashMap = new HashMap();
        hashMap.put("orderID", new StringBuilder().append(j).toString());
        hashMap.put("locale", Game.i.localeManager.getLocale());
        hashMap.put("steamLanguage", DesktopLauncher.steamApps.getCurrentGameLanguage());
        hashMap.put("steamAccountID", new StringBuilder().append(SteamID.getNativeHandle(DesktopLauncher.steamUser.getSteamID())).toString());
        hashMap.put("sessionid", Game.i.authManager.getSessionId());
        httpRequest.setContent(HttpParametersUtils.convertHttpParameters(hashMap));
        Gdx.f881net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() { // from class: com.prineside.tdi2.desktop.SteamPurchaseManager.2
            @Override // com.badlogic.gdx.Net.HttpResponseListener
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                String resultAsString = httpResponse.getResultAsString();
                SteamPurchaseManager.f1838a.i("Finalize transaction response:", new Object[0]);
                SteamPurchaseManager.f1838a.i(resultAsString, new Object[0]);
                try {
                    JsonValue parse = new JsonReader().parse(resultAsString);
                    if (!"success".equals(parse.getString("status"))) {
                        SteamPurchaseManager.f1838a.e("not successful request for purchase finalize: " + resultAsString, new Object[0]);
                        Gdx.app.postRunnable(() -> {
                            SteamPurchaseManager.this.f1839b.handlePurchaseError(new RuntimeException("Can't finalize purchase: " + parse.getString("message")));
                        });
                        return;
                    }
                    Transaction transaction = new Transaction();
                    transaction.setStoreName(SteamPurchaseManager.this.storeName());
                    transaction.setIdentifier(parse.get("order").getString("product_type"));
                    transaction.setOrderId(parse.get("order").getString(Attribute.ID_ATTR));
                    transaction.setPurchaseCost(parse.get("order").getInt("price", 0));
                    transaction.setPurchaseTime(new Date(parse.get("order").getInt("created_at", (int) (new Date().getTime() / 1000)) * 1000));
                    transaction.setPurchaseCostCurrency(parse.get("order").getString("currency", "USD"));
                    transaction.setPurchaseText(parse.get("order").getString("description", ""));
                    Gdx.app.postRunnable(() -> {
                        SteamPurchaseManager.this.f1839b.handlePurchase(transaction);
                    });
                } catch (Exception e) {
                    SteamPurchaseManager.f1838a.e("failed to parse purchase finalization response: " + resultAsString, e);
                    Gdx.app.postRunnable(() -> {
                        SteamPurchaseManager.this.f1839b.handlePurchaseError(new RuntimeException("Failed to parse response: " + resultAsString, e));
                    });
                }
            }

            @Override // com.badlogic.gdx.Net.HttpResponseListener
            public void failed(Throwable th) {
                SteamPurchaseManager.f1838a.e("failed to send finalization request", th);
                Gdx.app.postRunnable(() -> {
                    SteamPurchaseManager.this.f1839b.handlePurchaseError(th);
                });
            }

            @Override // com.badlogic.gdx.Net.HttpResponseListener
            public void cancelled() {
                SteamPurchaseManager.f1838a.e("cancelled finalization request", new Object[0]);
                Gdx.app.postRunnable(() -> {
                    SteamPurchaseManager.this.f1839b.handlePurchaseError(new RuntimeException("Finalization request cancelled"));
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        this.c = false;
        this.d.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        Gdx.app.postRunnable(() -> {
            if (!Game.i.authManager.isSignedIn() || Game.i.authManager.getSessionId() == null) {
                b();
                return;
            }
            if (Game.i.authManager.getSteamAccountId() == null) {
                b();
                return;
            }
            Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
            httpRequest.setUrl(Config.STEAM_TXN_PRODUCT_LIST_URL);
            HashMap hashMap = new HashMap();
            hashMap.put("locale", Game.i.localeManager.getLocale());
            hashMap.put("steamLanguage", DesktopLauncher.steamApps.getCurrentGameLanguage());
            hashMap.put("steamAccountID", new StringBuilder().append(SteamID.getNativeHandle(DesktopLauncher.steamUser.getSteamID())).toString());
            hashMap.put("sessionid", Game.i.authManager.getSessionId());
            httpRequest.setContent(HttpParametersUtils.convertHttpParameters(hashMap));
            Gdx.f881net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() { // from class: com.prineside.tdi2.desktop.SteamPurchaseManager.3
                @Override // com.badlogic.gdx.Net.HttpResponseListener
                public void handleHttpResponse(Net.HttpResponse httpResponse) {
                    String resultAsString = httpResponse.getResultAsString();
                    SteamPurchaseManager.this.d.clear();
                    try {
                        JsonValue parse = new JsonReader().parse(resultAsString);
                        if (!"success".equals(parse.getString("status"))) {
                            SteamPurchaseManager.f1838a.e("not successful request for product list: " + resultAsString, new Object[0]);
                            SteamPurchaseManager.this.b();
                            return;
                        }
                        Iterator<JsonValue> iterator2 = parse.get("products").iterator2();
                        while (iterator2.hasNext()) {
                            JsonValue next = iterator2.next();
                            SteamPurchaseManager.this.d.put(next.getString("gameProductId"), new SteamProductInfo(next.getInt(Attribute.ID_ATTR), next.getString("localPricing")));
                        }
                        SteamPurchaseManager.this.d();
                    } catch (Exception e) {
                        SteamPurchaseManager.f1838a.e("failed to retrieve product list: " + resultAsString, e);
                        SteamPurchaseManager.this.b();
                    }
                }

                @Override // com.badlogic.gdx.Net.HttpResponseListener
                public void failed(Throwable th) {
                    SteamPurchaseManager.f1838a.e("request product list failed", th);
                }

                @Override // com.badlogic.gdx.Net.HttpResponseListener
                public void cancelled() {
                    SteamPurchaseManager.f1838a.e("request product list cancelled", new Object[0]);
                }
            });
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (!this.c) {
            this.c = true;
            this.f1839b.handleInstall();
        }
    }

    @Override // com.badlogic.gdx.pay.PurchaseManager
    public boolean installed() {
        return this.c;
    }

    @Override // com.badlogic.gdx.pay.PurchaseManager
    public void dispose() {
        if (this.f1839b != null) {
            this.f1839b = null;
            Gdx.app.debug("SteamPurchaseManager", "disposed observer and config");
        }
        this.c = false;
    }

    @Override // com.badlogic.gdx.pay.PurchaseManager
    public void purchase(String str) {
        SteamProductInfo steamProductInfo = this.d.get(str);
        if (steamProductInfo == null) {
            this.f1839b.handlePurchaseError(new InvalidItemException(str));
            return;
        }
        Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
        httpRequest.setUrl(Config.STEAM_TXN_START_URL);
        HashMap hashMap = new HashMap();
        hashMap.put("locale", Game.i.localeManager.getLocale());
        hashMap.put("productIdentifier", str);
        hashMap.put("dbId", new StringBuilder().append(steamProductInfo.productId).toString());
        hashMap.put("steamLanguage", DesktopLauncher.steamApps.getCurrentGameLanguage());
        hashMap.put("steamAccountID", new StringBuilder().append(SteamID.getNativeHandle(DesktopLauncher.steamUser.getSteamID())).toString());
        hashMap.put("sessionid", Game.i.authManager.getSessionId());
        httpRequest.setContent(HttpParametersUtils.convertHttpParameters(hashMap));
        Gdx.f881net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() { // from class: com.prineside.tdi2.desktop.SteamPurchaseManager.4
            @Override // com.badlogic.gdx.Net.HttpResponseListener
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                String resultAsString = httpResponse.getResultAsString();
                try {
                    JsonValue parse = new JsonReader().parse(resultAsString);
                    if (!"success".equals(parse.getString("status"))) {
                        SteamPurchaseManager.f1838a.e("not successful request for purchase start: " + resultAsString, new Object[0]);
                        SteamPurchaseManager.this.f1839b.handlePurchaseError(new RuntimeException("Can't start purchase: " + parse.getString("message")));
                    }
                } catch (Exception e) {
                    SteamPurchaseManager.f1838a.e("failed to parse purchase start response: " + resultAsString, e);
                    SteamPurchaseManager.this.f1839b.handlePurchaseError(new RuntimeException("Failed to parse response: " + resultAsString, e));
                }
            }

            @Override // com.badlogic.gdx.Net.HttpResponseListener
            public void failed(Throwable th) {
                SteamPurchaseManager.f1838a.e("purchase start failed", th);
                SteamPurchaseManager.this.f1839b.handlePurchaseError(new RuntimeException("Request failed", th));
            }

            @Override // com.badlogic.gdx.Net.HttpResponseListener
            public void cancelled() {
                SteamPurchaseManager.f1838a.e("purchase start cancelled", new Object[0]);
                SteamPurchaseManager.this.f1839b.handlePurchaseError(new RuntimeException("Request cancelled"));
            }
        });
    }

    @Override // com.badlogic.gdx.pay.PurchaseManager
    public void purchaseRestore() {
    }

    @Override // com.badlogic.gdx.pay.InformationFinder
    public Information getInformation(String str) {
        SteamProductInfo steamProductInfo = this.d.get(str);
        return steamProductInfo == null ? Information.UNAVAILABLE : steamProductInfo.information;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/desktop/SteamPurchaseManager$SteamProductInfo.class */
    public static final class SteamProductInfo {
        public Information information;
        public int productId;

        public SteamProductInfo(int i, String str) {
            this.information = new Information("", "", str);
            this.productId = i;
        }
    }
}
