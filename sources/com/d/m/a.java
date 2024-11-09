package com.d.m;

import java.util.Arrays;
import java.util.stream.Collectors;

/* loaded from: infinitode-2.jar:com/d/m/a.class */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private final com.d.c.d.j f1413a;

    /* renamed from: b, reason: collision with root package name */
    private final com.d.c.d.j f1414b;

    public static String[] a(String[] strArr) {
        return strArr == null ? c.f1417a : (String[]) strArr.clone();
    }

    public static int[] a(int[] iArr) {
        return iArr == null ? c.f1418b : (int[]) iArr.clone();
    }

    @SafeVarargs
    public static <T> boolean a(T t, T... tArr) {
        for (T t2 : tArr) {
            if (t2 == null && t == null) {
                return true;
            }
            if (t != null && t.equals(t2)) {
                return true;
            }
        }
        return false;
    }

    public static String a(String[] strArr, String str) {
        return (String) Arrays.stream(strArr).collect(Collectors.joining(str));
    }

    public a(com.d.c.d.j jVar, com.d.c.d.j jVar2) {
        this.f1413a = jVar;
        this.f1414b = jVar2;
    }

    public com.d.c.d.j a() {
        return this.f1413a;
    }

    public com.d.c.d.j b() {
        return this.f1414b;
    }
}
