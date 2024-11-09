package com.a.a.c.k.b;

import com.a.a.a.l;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: infinitode-2.jar:com/a/a/c/k/b/m.class */
public abstract class m<T> extends an<T> implements com.a.a.c.k.k {

    /* renamed from: a, reason: collision with root package name */
    private Boolean f623a;

    /* renamed from: b, reason: collision with root package name */
    private DateFormat f624b;
    private AtomicReference<DateFormat> c;

    public abstract m<T> a(Boolean bool, DateFormat dateFormat);

    /* JADX INFO: Access modifiers changed from: protected */
    public m(Class<T> cls, Boolean bool, DateFormat dateFormat) {
        super(cls);
        this.f623a = bool;
        this.f624b = dateFormat;
        this.c = dateFormat == null ? null : new AtomicReference<>();
    }

    @Override // com.a.a.c.k.k
    public final com.a.a.c.o<?> a(com.a.a.c.aa aaVar, com.a.a.c.c cVar) {
        SimpleDateFormat simpleDateFormat;
        Locale g;
        TimeZone h;
        l.d a2 = a(aaVar, cVar, (Class<?>) a());
        if (a2 == null) {
            return this;
        }
        l.c c = a2.c();
        if (c.a()) {
            return a(Boolean.TRUE, (DateFormat) null);
        }
        if (a2.h()) {
            if (a2.i()) {
                g = a2.d();
            } else {
                g = aaVar.g();
            }
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(a2.b(), g);
            if (a2.j()) {
                h = a2.f();
            } else {
                h = aaVar.h();
            }
            simpleDateFormat2.setTimeZone(h);
            return a(Boolean.FALSE, (DateFormat) simpleDateFormat2);
        }
        boolean i = a2.i();
        boolean j = a2.j();
        boolean z = c == l.c.STRING;
        if (!i && !j && !z) {
            return this;
        }
        DateFormat s = aaVar.a().s();
        if (s instanceof com.a.a.c.m.ab) {
            com.a.a.c.m.ab abVar = (com.a.a.c.m.ab) s;
            if (a2.i()) {
                abVar = abVar.a(a2.d());
            }
            if (a2.j()) {
                abVar = abVar.a(a2.f());
            }
            return a(Boolean.FALSE, (DateFormat) abVar);
        }
        if (!(s instanceof SimpleDateFormat)) {
            aaVar.a((Class<?>) a(), String.format("Configured `DateFormat` (%s) not a `SimpleDateFormat`; cannot configure `Locale` or `TimeZone`", s.getClass().getName()));
        }
        SimpleDateFormat simpleDateFormat3 = (SimpleDateFormat) s;
        if (i) {
            simpleDateFormat = new SimpleDateFormat(simpleDateFormat3.toPattern(), a2.d());
        } else {
            simpleDateFormat = (SimpleDateFormat) simpleDateFormat3.clone();
        }
        TimeZone f = a2.f();
        if ((f == null || f.equals(simpleDateFormat.getTimeZone())) ? false : true) {
            simpleDateFormat.setTimeZone(f);
        }
        return a(Boolean.FALSE, (DateFormat) simpleDateFormat);
    }

    @Override // com.a.a.c.o
    public final boolean a(com.a.a.c.aa aaVar, T t) {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean a(com.a.a.c.aa aaVar) {
        if (this.f623a != null) {
            return this.f623a.booleanValue();
        }
        if (this.f624b == null) {
            if (aaVar != null) {
                return aaVar.a(com.a.a.c.z.WRITE_DATES_AS_TIMESTAMPS);
            }
            throw new IllegalArgumentException("Null SerializerProvider passed for " + a().getName());
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(Date date, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
        if (this.f624b == null) {
            aaVar.a(date, hVar);
            return;
        }
        DateFormat andSet = this.c.getAndSet(null);
        DateFormat dateFormat = andSet;
        if (andSet == null) {
            dateFormat = (DateFormat) this.f624b.clone();
        }
        hVar.b(dateFormat.format(date));
        this.c.compareAndSet(null, dateFormat);
    }
}
