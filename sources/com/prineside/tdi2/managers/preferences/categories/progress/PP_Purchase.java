package com.prineside.tdi2.managers.preferences.categories.progress;

import com.badlogic.gdx.pay.Transaction;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.ObjectSet;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.managers.PurchaseManager;
import com.prineside.tdi2.managers.preferences.PrefMap;
import com.prineside.tdi2.managers.preferences.PrefSubcategory;
import com.prineside.tdi2.utils.logging.TLog;
import java.io.StringWriter;
import java.util.Date;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/preferences/categories/progress/PP_Purchase.class */
public final class PP_Purchase implements PrefSubcategory {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2535a = TLog.forClass(PP_Purchase.class);

    /* renamed from: b, reason: collision with root package name */
    private final Array<Transaction> f2536b = new Array<>();
    private final Array<String> c = new Array<>(false, 8, String.class);
    private final IntArray[] d = new IntArray[PurchaseManager.RewardingAdsType.values.length];

    public PP_Purchase() {
        for (PurchaseManager.RewardingAdsType rewardingAdsType : PurchaseManager.RewardingAdsType.values) {
            this.d[rewardingAdsType.ordinal()] = new IntArray();
        }
    }

    public final IntArray getRewardingAdWatchTimestamps(PurchaseManager.RewardingAdsType rewardingAdsType) {
        return this.d[rewardingAdsType.ordinal()];
    }

    public final synchronized void addRewardingAdWatchTimestamp(PurchaseManager.RewardingAdsType rewardingAdsType, int i) {
        IntArray intArray = this.d[rewardingAdsType.ordinal()];
        intArray.add(Game.getTimestampSeconds());
        if (intArray.size > i) {
            intArray.removeIndex(0);
        }
    }

    public final synchronized void addValidatedTransaction(String str) {
        if (this.c.contains(str, false)) {
            this.c.add(str);
        }
    }

    public final Array<String> getValidatedTransactions() {
        return this.c;
    }

    public final boolean hasAnyTransaction() {
        return this.f2536b.size != 0;
    }

    public final Array<Transaction> getTransactions() {
        return this.f2536b;
    }

    public final synchronized void addTransaction(Transaction transaction) {
        this.f2536b.add(transaction);
    }

    @Override // com.prineside.tdi2.managers.preferences.PrefSubcategory
    public final void load(PrefMap prefMap) {
        this.f2536b.clear();
        this.c.clear();
        for (PurchaseManager.RewardingAdsType rewardingAdsType : PurchaseManager.RewardingAdsType.values) {
            this.d[rewardingAdsType.ordinal()].clear();
        }
        String str = prefMap.get("purchaseTransactions", null);
        if (str != null) {
            try {
                Iterator<JsonValue> iterator2 = new JsonReader().parse(str).iterator2();
                while (iterator2.hasNext()) {
                    JsonValue next = iterator2.next();
                    Transaction transaction = new Transaction();
                    transaction.setIdentifier(next.getString("identifier"));
                    transaction.setStoreName(next.getString("storeName"));
                    transaction.setOrderId(next.getString("orderId"));
                    transaction.setRequestId(next.getString("requestId"));
                    transaction.setUserId(next.getString("userId"));
                    transaction.setPurchaseTime(new Date(next.getLong("purchaseTime")));
                    transaction.setPurchaseText(next.getString("purchaseText"));
                    transaction.setPurchaseCost(next.getInt("purchaseCost"));
                    transaction.setPurchaseCostCurrency(next.getString("purchaseCostCurrency"));
                    transaction.setReversalTime(new Date(next.getLong("reversalTime")));
                    transaction.setReversalText(next.getString("reversalText"));
                    transaction.setTransactionData(next.getString("transactionData"));
                    transaction.setTransactionDataSignature(next.getString("transactionDataSignature"));
                    this.f2536b.add(transaction);
                }
            } catch (Exception e) {
                f2535a.e("failed to load transactions", e);
            }
        }
        String str2 = prefMap.get("purchaseValidatedTransactions", null);
        if (str2 != null) {
            try {
                Iterator<JsonValue> iterator22 = new JsonReader().parse(str2).iterator2();
                while (iterator22.hasNext()) {
                    this.c.add(iterator22.next().asString());
                }
            } catch (Exception e2) {
                f2535a.e("failed to load validated transactions", e2);
            }
        }
        for (IntArray intArray : this.d) {
            intArray.clear();
        }
        String str3 = prefMap.get("rewardingAdsWatchTimestamps", null);
        if (str3 != null) {
            try {
                int i = 0;
                Iterator<JsonValue> iterator23 = new JsonReader().parse(str3).iterator2();
                while (iterator23.hasNext()) {
                    JsonValue next2 = iterator23.next();
                    if (i < this.d.length) {
                        int i2 = i;
                        i++;
                        IntArray intArray2 = this.d[i2];
                        Iterator<JsonValue> iterator24 = next2.iterator2();
                        while (iterator24.hasNext()) {
                            intArray2.add(iterator24.next().asInt());
                        }
                    } else {
                        return;
                    }
                }
            } catch (Exception e3) {
                f2535a.e("failed to load rewardingAdsWatchTimestamps", e3);
            }
        }
    }

    @Override // com.prineside.tdi2.managers.preferences.PrefSubcategory
    public final synchronized void save(PrefMap prefMap) {
        Json json = new Json(JsonWriter.OutputType.minimal);
        StringWriter stringWriter = new StringWriter();
        json.setWriter(stringWriter);
        ObjectSet objectSet = new ObjectSet();
        json.writeArrayStart();
        for (int i = 0; i < this.f2536b.size; i++) {
            Transaction transaction = this.f2536b.get(i);
            String str = transaction.getStoreName() + "|" + transaction.getOrderId();
            if (!objectSet.contains(str)) {
                objectSet.add(str);
                json.writeObjectStart();
                json.writeValue("identifier", transaction.getIdentifier());
                json.writeValue("storeName", transaction.getStoreName());
                json.writeValue("orderId", transaction.getOrderId());
                json.writeValue("requestId", transaction.getRequestId());
                json.writeValue("userId", transaction.getUserId());
                json.writeValue("purchaseTime", Long.valueOf(transaction.getPurchaseTime() != null ? transaction.getPurchaseTime().getTime() : 0L));
                json.writeValue("purchaseText", transaction.getPurchaseText());
                json.writeValue("purchaseCost", Integer.valueOf(transaction.getPurchaseCost()));
                json.writeValue("purchaseCostCurrency", transaction.getPurchaseCostCurrency());
                json.writeValue("reversalTime", Long.valueOf(transaction.getReversalTime() != null ? transaction.getReversalTime().getTime() : 0L));
                json.writeValue("reversalText", transaction.getReversalText());
                json.writeValue("transactionData", transaction.getTransactionData());
                json.writeValue("transactionDataSignature", transaction.getTransactionDataSignature());
                json.writeObjectEnd();
            }
        }
        json.writeArrayEnd();
        prefMap.set("purchaseTransactions", stringWriter.toString());
        Json json2 = new Json(JsonWriter.OutputType.minimal);
        StringWriter stringWriter2 = new StringWriter();
        json2.setWriter(stringWriter2);
        json2.writeArrayStart();
        for (int i2 = 0; i2 < this.c.size; i2++) {
            json2.writeValue(this.c.get(i2));
        }
        json2.writeArrayEnd();
        prefMap.set("purchaseValidatedTransactions", stringWriter2.toString());
        Json json3 = new Json(JsonWriter.OutputType.minimal);
        StringWriter stringWriter3 = new StringWriter();
        json3.setWriter(stringWriter3);
        json3.writeArrayStart();
        for (IntArray intArray : this.d) {
            json3.writeArrayStart();
            for (int i3 = 0; i3 < intArray.size; i3++) {
                json3.writeValue(Integer.valueOf(intArray.items[i3]));
            }
            json3.writeArrayEnd();
        }
        json3.writeArrayEnd();
        prefMap.set("rewardingAdsWatchTimestamps", stringWriter3.toString());
    }
}
