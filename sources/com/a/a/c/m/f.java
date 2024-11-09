package com.a.a.c.m;

import com.a.a.a.s;
import java.lang.reflect.Array;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/* loaded from: infinitode-2.jar:com/a/a/c/m/f.class */
public class f {

    /* renamed from: a, reason: collision with root package name */
    private p<Object[]> f719a;

    /* renamed from: b, reason: collision with root package name */
    private p<Object[]> f720b;
    private int c;
    private Object[] d;

    public static Object a(com.a.a.c.j jVar) {
        Class<?> b2 = jVar.b();
        Class<?> j = i.j(b2);
        if (j != null) {
            return i.h(j);
        }
        if (jVar.n() || jVar.F()) {
            return s.a.NON_EMPTY;
        }
        if (b2 == String.class) {
            return "";
        }
        if (jVar.b(Date.class)) {
            return new Date(0L);
        }
        if (jVar.b(Calendar.class)) {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTimeInMillis(0L);
            return gregorianCalendar;
        }
        return null;
    }

    public static String b(com.a.a.c.j jVar) {
        Object obj;
        Object obj2;
        String name = jVar.b().getName();
        if (a(name)) {
            if (name.indexOf(46, 10) >= 0) {
                return null;
            }
            obj = "Java 8 date/time";
            obj2 = "com.fasterxml.jackson.datatype:jackson-datatype-jsr310";
        } else if (b(name)) {
            obj = "Joda date/time";
            obj2 = "com.fasterxml.jackson.datatype:jackson-datatype-joda";
        } else {
            return null;
        }
        return String.format("%s type %s not supported by default: add Module \"%s\" to enable handling", obj, i.b(jVar), obj2);
    }

    private static boolean a(String str) {
        return str.startsWith("java.time.");
    }

    private static boolean b(String str) {
        return str.startsWith("org.joda.time.");
    }

    public Object[] a() {
        d();
        if (this.d == null) {
            Object[] objArr = new Object[12];
            this.d = objArr;
            return objArr;
        }
        return this.d;
    }

    public Object[] a(Object[] objArr, int i) {
        d();
        if (this.d == null || this.d.length < i) {
            this.d = new Object[Math.max(12, i)];
        }
        System.arraycopy(objArr, 0, this.d, 0, i);
        return this.d;
    }

    public Object[] a(Object[] objArr) {
        p<Object[]> pVar = new p<>(objArr, null);
        if (this.f719a == null) {
            this.f720b = pVar;
            this.f719a = pVar;
        } else {
            this.f720b.a(pVar);
            this.f720b = pVar;
        }
        int length = objArr.length;
        this.c += length;
        if (length < 16384) {
            length += length;
        } else if (length < 262144) {
            length += length >> 2;
        }
        return new Object[length];
    }

    public Object[] b(Object[] objArr, int i) {
        int i2 = i + this.c;
        Object[] objArr2 = new Object[i2];
        a(objArr2, i2, objArr, i);
        d();
        return objArr2;
    }

    public <T> T[] a(Object[] objArr, int i, Class<T> cls) {
        int i2 = i + this.c;
        T[] tArr = (T[]) ((Object[]) Array.newInstance((Class<?>) cls, i2));
        a(tArr, i2, objArr, i);
        d();
        return tArr;
    }

    public void a(Object[] objArr, int i, List<Object> list) {
        p<Object[]> pVar = this.f719a;
        while (true) {
            p<Object[]> pVar2 = pVar;
            if (pVar2 == null) {
                break;
            }
            for (Object obj : pVar2.b()) {
                list.add(obj);
            }
            pVar = pVar2.a();
        }
        for (int i2 = 0; i2 < i; i2++) {
            list.add(objArr[i2]);
        }
        d();
    }

    public int b() {
        if (this.d == null) {
            return 0;
        }
        return this.d.length;
    }

    public int c() {
        return this.c;
    }

    protected void d() {
        if (this.f720b != null) {
            this.d = this.f720b.b();
        }
        this.f720b = null;
        this.f719a = null;
        this.c = 0;
    }

    protected void a(Object obj, int i, Object[] objArr, int i2) {
        int i3 = 0;
        p<Object[]> pVar = this.f719a;
        while (true) {
            p<Object[]> pVar2 = pVar;
            if (pVar2 == null) {
                break;
            }
            Object[] b2 = pVar2.b();
            int length = b2.length;
            System.arraycopy(b2, 0, obj, i3, length);
            i3 += length;
            pVar = pVar2.a();
        }
        System.arraycopy(objArr, 0, obj, i3, i2);
        int i4 = i3 + i2;
        if (i4 != i) {
            throw new IllegalStateException("Should have gotten " + i + " entries, got " + i4);
        }
    }
}
