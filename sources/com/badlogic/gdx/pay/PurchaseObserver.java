package com.badlogic.gdx.pay;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/pay/PurchaseObserver.class */
public interface PurchaseObserver {
    void handleInstall();

    void handleInstallError(Throwable th);

    void handleRestore(Transaction[] transactionArr);

    void handleRestoreError(Throwable th);

    void handlePurchase(Transaction transaction);

    void handlePurchaseError(Throwable th);

    void handlePurchaseCanceled();
}
