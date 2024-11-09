package com.a.a.a;

/* loaded from: infinitode-2.jar:com/a/a/a/ao.class */
public enum ao {
    TRUE,
    FALSE,
    DEFAULT;

    public final Boolean a() {
        if (this == DEFAULT) {
            return null;
        }
        return this == TRUE ? Boolean.TRUE : Boolean.FALSE;
    }

    public static boolean a(Boolean bool, Boolean bool2) {
        if (bool == null) {
            return bool2 == null;
        }
        return bool.equals(bool2);
    }
}
