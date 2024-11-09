package com.a.a.c.k.b;

import java.text.DateFormat;
import java.util.Calendar;

@com.a.a.c.a.a
/* loaded from: infinitode-2.jar:com/a/a/c/k/b/i.class */
public final class i extends m<Calendar> {

    /* renamed from: a, reason: collision with root package name */
    public static final i f621a = new i();

    @Override // com.a.a.c.k.b.m
    public final /* synthetic */ m<Calendar> a(Boolean bool, DateFormat dateFormat) {
        return b(bool, dateFormat);
    }

    public i() {
        this(null, null);
    }

    private i(Boolean bool, DateFormat dateFormat) {
        super(Calendar.class, bool, dateFormat);
    }

    private static i b(Boolean bool, DateFormat dateFormat) {
        return new i(bool, dateFormat);
    }

    private static long a(Calendar calendar) {
        if (calendar == null) {
            return 0L;
        }
        return calendar.getTimeInMillis();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k.b.ao, com.a.a.c.o
    public void a(Calendar calendar, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
        if (a(aaVar)) {
            hVar.b(a(calendar));
        } else {
            a(calendar.getTime(), hVar, aaVar);
        }
    }
}
