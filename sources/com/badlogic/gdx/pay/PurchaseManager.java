package com.badlogic.gdx.pay;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/pay/PurchaseManager.class */
public interface PurchaseManager extends InformationFinder {
    String storeName();

    void install(PurchaseObserver purchaseObserver, PurchaseManagerConfig purchaseManagerConfig, boolean z);

    boolean installed();

    void dispose();

    void purchase(String str);

    void purchaseRestore();
}
