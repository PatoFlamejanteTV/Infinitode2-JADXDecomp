package com.a.a.c.m;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/a/a/c/m/j.class */
public final class j implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    private static final j f729a = new j(1, 0, new Object[4]);

    /* renamed from: b, reason: collision with root package name */
    private final int f730b;
    private final int c;
    private final Object[] d;

    private j(int i, int i2, Object[] objArr) {
        this.f730b = i;
        this.c = i2;
        this.d = objArr;
    }

    public static <T> j a(Map<String, T> map) {
        if (map.isEmpty()) {
            return f729a;
        }
        int a2 = a(map.size());
        int i = a2 - 1;
        Object[] objArr = new Object[(a2 + (a2 >> 1)) << 1];
        int i2 = 0;
        for (Map.Entry<String, T> entry : map.entrySet()) {
            String key = entry.getKey();
            if (key != null) {
                int hashCode = key.hashCode() & i;
                int i3 = hashCode + hashCode;
                if (objArr[i3] != null) {
                    i3 = (a2 + (hashCode >> 1)) << 1;
                    if (objArr[i3] != null) {
                        i3 = ((a2 + (a2 >> 1)) << 1) + i2;
                        i2 += 2;
                        if (i3 >= objArr.length) {
                            Object[] objArr2 = objArr;
                            objArr = Arrays.copyOf(objArr2, objArr2.length + 4);
                        }
                    }
                }
                objArr[i3] = key;
                objArr[i3 + 1] = entry.getValue();
            }
        }
        return new j(i, i2, objArr);
    }

    private static final int a(int i) {
        if (i <= 5) {
            return 8;
        }
        if (i <= 12) {
            return 16;
        }
        int i2 = 32;
        while (true) {
            int i3 = i2;
            if (i3 < i + (i >> 2)) {
                i2 = i3 + i3;
            } else {
                return i3;
            }
        }
    }

    public final Object a(String str) {
        int hashCode = str.hashCode() & this.f730b;
        int i = hashCode << 1;
        Object obj = this.d[i];
        if (obj == str || str.equals(obj)) {
            return this.d[i + 1];
        }
        return a(str, hashCode, obj);
    }

    private final Object a(String str, int i, Object obj) {
        if (obj == null) {
            return null;
        }
        int i2 = this.f730b + 1;
        int i3 = (i2 + (i >> 1)) << 1;
        Object obj2 = this.d[i3];
        if (str.equals(obj2)) {
            return this.d[i3 + 1];
        }
        if (obj2 != null) {
            int i4 = (i2 + (i2 >> 1)) << 1;
            int i5 = i4 + this.c;
            for (int i6 = i4; i6 < i5; i6 += 2) {
                Object obj3 = this.d[i6];
                if (obj3 == str || str.equals(obj3)) {
                    return this.d[i6 + 1];
                }
            }
            return null;
        }
        return null;
    }

    public final Object b(String str) {
        int length = this.d.length;
        for (int i = 0; i < length; i += 2) {
            Object obj = this.d[i];
            if (obj != null && ((String) obj).equalsIgnoreCase(str)) {
                return this.d[i + 1];
            }
        }
        return null;
    }

    public final List<String> a() {
        int length = this.d.length;
        ArrayList arrayList = new ArrayList(length >> 2);
        for (int i = 0; i < length; i += 2) {
            Object obj = this.d[i];
            if (obj != null) {
                arrayList.add((String) obj);
            }
        }
        return arrayList;
    }
}
