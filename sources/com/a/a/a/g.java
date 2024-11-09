package com.a.a.a;

import com.a.a.a.f;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:com/a/a/a/g.class */
public /* synthetic */ class g {

    /* renamed from: a, reason: collision with root package name */
    static final /* synthetic */ int[] f61a;

    /* renamed from: b, reason: collision with root package name */
    private static /* synthetic */ int[] f62b = new int[ap.values().length];

    static {
        try {
            f62b[ap.CREATOR.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f62b[ap.FIELD.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            f62b[ap.GETTER.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            f62b[ap.IS_GETTER.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            f62b[ap.NONE.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            f62b[ap.SETTER.ordinal()] = 6;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            f62b[ap.ALL.ordinal()] = 7;
        } catch (NoSuchFieldError unused7) {
        }
        f61a = new int[f.b.values().length];
        try {
            f61a[f.b.ANY.ordinal()] = 1;
        } catch (NoSuchFieldError unused8) {
        }
        try {
            f61a[f.b.NONE.ordinal()] = 2;
        } catch (NoSuchFieldError unused9) {
        }
        try {
            f61a[f.b.NON_PRIVATE.ordinal()] = 3;
        } catch (NoSuchFieldError unused10) {
        }
        try {
            f61a[f.b.PROTECTED_AND_PUBLIC.ordinal()] = 4;
        } catch (NoSuchFieldError unused11) {
        }
        try {
            f61a[f.b.PUBLIC_ONLY.ordinal()] = 5;
        } catch (NoSuchFieldError unused12) {
        }
    }
}
