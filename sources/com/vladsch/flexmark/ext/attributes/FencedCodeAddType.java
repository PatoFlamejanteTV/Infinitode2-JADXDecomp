package com.vladsch.flexmark.ext.attributes;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/attributes/FencedCodeAddType.class */
public enum FencedCodeAddType {
    ADD_TO_PRE_CODE(true, true),
    ADD_TO_PRE(true, false),
    ADD_TO_CODE(false, true);

    public final boolean addToPre;
    public final boolean addToCode;

    FencedCodeAddType(boolean z, boolean z2) {
        this.addToPre = z;
        this.addToCode = z2;
    }
}
