package com.vladsch.flexmark.ext.attributes;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/attributes/AttributeImplicitName.class */
public enum AttributeImplicitName {
    AS_IS,
    IMPLICIT_PREFERRED,
    EXPLICIT_PREFERRED;

    public final boolean isNoChange() {
        return this == AS_IS;
    }

    public final boolean isImplicit() {
        return this == IMPLICIT_PREFERRED;
    }

    public final boolean isExplicit() {
        return this == EXPLICIT_PREFERRED;
    }
}
