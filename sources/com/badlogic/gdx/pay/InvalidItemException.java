package com.badlogic.gdx.pay;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/pay/InvalidItemException.class */
public class InvalidItemException extends GdxPayException {
    public InvalidItemException() {
        this("");
    }

    public InvalidItemException(String str) {
        super("Purchase failed, invalid product identifier " + str);
    }
}
