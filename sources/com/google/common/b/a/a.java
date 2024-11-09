package com.google.common.b.a;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: infinitode-2.jar:com/google/common/b/a/a.class */
public class a extends Number implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    private transient AtomicLong f1461a;

    public a(double d) {
        this.f1461a = new AtomicLong(Double.doubleToRawLongBits(0.0d));
    }

    public a() {
        this(0.0d);
    }

    public final double a() {
        return Double.longBitsToDouble(this.f1461a.get());
    }

    public final void a(double d) {
        this.f1461a.set(Double.doubleToRawLongBits(d));
    }

    public String toString() {
        return Double.toString(a());
    }

    @Override // java.lang.Number
    public int intValue() {
        return (int) a();
    }

    @Override // java.lang.Number
    public long longValue() {
        return (long) a();
    }

    @Override // java.lang.Number
    public float floatValue() {
        return (float) a();
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return a();
    }
}
