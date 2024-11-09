package com.badlogic.gdx.pay;

import java.util.Date;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/pay/Transaction.class */
public final class Transaction {
    public static final String REVERSAL_TEXT_CANCELLED = "Cancelled";
    public static final String REVERSAL_TEXT_REFUNDED = "Refunded";
    private String identifier;
    private String storeName;
    private String orderId;
    private String requestId = null;
    private String userId = null;
    private Date purchaseTime;
    private String purchaseText;
    private int purchaseCost;
    private String purchaseCostCurrency;
    private Date reversalTime;
    private String reversalText;
    private String transactionData;
    private String transactionDataSignature;

    public final String getIdentifier() {
        return this.identifier;
    }

    public final void setIdentifier(String str) {
        this.identifier = str;
    }

    public final String getStoreName() {
        return this.storeName;
    }

    public final void setStoreName(String str) {
        this.storeName = str;
    }

    public final String getOrderId() {
        return this.orderId;
    }

    public final void setOrderId(String str) {
        this.orderId = str;
    }

    public final String getRequestId() {
        return this.requestId;
    }

    public final void setRequestId(String str) {
        this.requestId = str;
    }

    public final String getUserId() {
        return this.userId;
    }

    public final void setUserId(String str) {
        this.userId = str;
    }

    public final boolean isPurchased() {
        return this.reversalTime == null;
    }

    public final Date getPurchaseTime() {
        return this.purchaseTime;
    }

    public final void setPurchaseTime(Date date) {
        this.purchaseTime = date;
    }

    public final String getPurchaseText() {
        return this.purchaseText;
    }

    public final void setPurchaseText(String str) {
        this.purchaseText = str;
    }

    public final int getPurchaseCost() {
        return this.purchaseCost;
    }

    public final void setPurchaseCost(int i) {
        this.purchaseCost = i;
    }

    public final String getPurchaseCostCurrency() {
        return this.purchaseCostCurrency;
    }

    public final void setPurchaseCostCurrency(String str) {
        this.purchaseCostCurrency = str;
    }

    public final Date getReversalTime() {
        return this.reversalTime;
    }

    public final void setReversalTime(Date date) {
        this.reversalTime = date;
    }

    public final String getReversalText() {
        return this.reversalText;
    }

    public final void setReversalText(String str) {
        this.reversalText = str;
    }

    public final String getTransactionData() {
        return this.transactionData;
    }

    public final void setTransactionData(String str) {
        this.transactionData = str;
    }

    public final String getTransactionDataSignature() {
        return this.transactionDataSignature;
    }

    public final void setTransactionDataSignature(String str) {
        this.transactionDataSignature = str;
    }

    public final String toString() {
        return "Transaction{identifier='" + this.identifier + "', storeName='" + this.storeName + "', orderId='" + this.orderId + "', requestId='" + this.requestId + "', userId='" + this.userId + "', purchaseTime=" + this.purchaseTime + ", purchaseText='" + this.purchaseText + "', purchaseCost=" + this.purchaseCost + ", purchaseCostCurrency='" + this.purchaseCostCurrency + "', reversalTime=" + this.reversalTime + ", reversalText='" + this.reversalText + "', transactionData='" + this.transactionData + "', transactionDataSignature='" + this.transactionDataSignature + "'}";
    }
}
