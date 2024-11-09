package com.a.a.c.k.b;

import java.text.DateFormat;
import java.util.Date;

@com.a.a.c.a.a
/* loaded from: infinitode-2.jar:com/a/a/c/k/b/l.class */
public final class l extends m<Date> {

    /* renamed from: a, reason: collision with root package name */
    public static final l f622a = new l();

    @Override // com.a.a.c.k.b.m
    public final /* synthetic */ m<Date> a(Boolean bool, DateFormat dateFormat) {
        return b(bool, dateFormat);
    }

    public l() {
        this(null, null);
    }

    private l(Boolean bool, DateFormat dateFormat) {
        super(Date.class, bool, dateFormat);
    }

    private static l b(Boolean bool, DateFormat dateFormat) {
        return new l(bool, dateFormat);
    }

    private static long a(Date date) {
        if (date == null) {
            return 0L;
        }
        return date.getTime();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k.b.ao, com.a.a.c.o
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void a(Date date, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
        if (a(aaVar)) {
            hVar.b(a(date));
        } else {
            a(date, hVar, aaVar);
        }
    }
}
