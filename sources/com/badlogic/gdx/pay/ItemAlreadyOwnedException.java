package com.badlogic.gdx.pay;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/pay/ItemAlreadyOwnedException.class */
public class ItemAlreadyOwnedException extends GdxPayException {
    public ItemAlreadyOwnedException() {
        super("Purchase failed: Item is already owned.");
    }
}
