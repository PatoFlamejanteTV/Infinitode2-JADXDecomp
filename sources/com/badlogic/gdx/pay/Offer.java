package com.badlogic.gdx.pay;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/pay/Offer.class */
public class Offer {
    private OfferType type;
    private String identifier;
    private Map<String, String> identifierForStores = new HashMap(16);

    public synchronized OfferType getType() {
        return this.type;
    }

    public synchronized Offer setType(OfferType offerType) {
        this.type = offerType;
        return this;
    }

    public synchronized String getIdentifier() {
        return this.identifier;
    }

    public synchronized Offer setIdentifier(String str) {
        this.identifier = str;
        return this;
    }

    public synchronized String getIdentifierForStore(String str) {
        String str2 = this.identifierForStores.get(str);
        if (str2 != null) {
            return str2;
        }
        return this.identifier;
    }

    public synchronized Set<Map.Entry<String, String>> getIdentifierForStores() {
        return this.identifierForStores.entrySet();
    }

    public synchronized Offer putIdentifierForStore(String str, String str2) {
        this.identifierForStores.put(str, str2);
        return this;
    }

    public String toString() {
        return "Offer{type=" + this.type + ", identifier='" + this.identifier + "', identifierForStores=" + this.identifierForStores + '}';
    }
}
