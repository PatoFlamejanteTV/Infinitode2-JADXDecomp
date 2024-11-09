package com.badlogic.gdx.pay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/pay/PurchaseManagerConfig.class */
public class PurchaseManagerConfig {
    public static final String STORE_NAME_ANDROID_GOOGLE = "GooglePlay";
    public static final String STORE_NAME_ANDROID_AMAZON = "Amazon";
    public static final String STORE_NAME_ANDROID_HUAWEI = "Huawei";
    public static final String STORE_NAME_ANDROID_SAMSUNG = "Samsung";
    public static final String STORE_NAME_ANDROID_NOKIA = "Nokia";
    public static final String STORE_NAME_ANDROID_SLIDEME = "SlideME";
    public static final String STORE_NAME_ANDROID_APTOIDE = "Aptoide";
    public static final String STORE_NAME_ANDROID_APPLAND = "Appland";
    public static final String STORE_NAME_ANDROID_YANDEX = "Yandex";
    public static final String STORE_NAME_IOS_APPLE = "AppleiOS";
    public static final String STORE_NAME_DESKTOP_APPLE = "AppleMac";
    public static final String STORE_NAME_DESKTOP_STEAM = "Steam";
    public static final String STORE_NAME_DESKTOP_WINDOWS = "Windows";
    public static final String STORE_NAME_GWT_GOOGLEWALLET = "GwtGoogleWallet";
    private List<Offer> offers = new ArrayList(16);
    private Map<String, Object> storeParams = new HashMap(16);

    public synchronized void addOffer(Offer offer) {
        this.offers.add(offer);
    }

    public synchronized Offer getOffer(String str) {
        for (int i = 0; i < this.offers.size(); i++) {
            if (this.offers.get(i).getIdentifier().equals(str)) {
                return this.offers.get(i);
            }
        }
        return null;
    }

    public synchronized boolean hasAnyOfferWithType(OfferType offerType) {
        Iterator<Offer> it = this.offers.iterator();
        while (it.hasNext()) {
            if (it.next().getType() == offerType) {
                return true;
            }
        }
        return false;
    }

    public synchronized Offer getOfferForStore(String str, String str2) {
        for (int i = 0; i < this.offers.size(); i++) {
            if (this.offers.get(i).getIdentifierForStore(str).equals(str2)) {
                return this.offers.get(i);
            }
        }
        return null;
    }

    public synchronized Offer getOffer(int i) {
        return this.offers.get(i);
    }

    public synchronized int getOfferCount() {
        return this.offers.size();
    }

    public synchronized void addStoreParam(String str, Object obj) {
        this.storeParams.put(str, obj);
    }

    public synchronized Object getStoreParam(String str) {
        return this.storeParams.get(str);
    }
}
