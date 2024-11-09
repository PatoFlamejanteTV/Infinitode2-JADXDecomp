package com.badlogic.gdx.pay;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/pay/FetchItemInformationException.class */
public class FetchItemInformationException extends GdxPayException {
    public FetchItemInformationException() {
        super("Failed to fetch item list - check your connection");
    }

    public FetchItemInformationException(String str) {
        super("Failed to fetch item list - check your connection (" + str + ")");
    }
}
