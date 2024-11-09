package com.a.a.c.c.b;

import com.a.a.a.l;
import java.lang.reflect.Constructor;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.TimeZone;

/* loaded from: infinitode-2.jar:com/a/a/c/c/b/j.class */
public final class j {

    /* renamed from: a, reason: collision with root package name */
    private static final HashSet<String> f345a;

    static {
        HashSet<String> hashSet = new HashSet<>();
        f345a = hashSet;
        hashSet.add("java.util.Calendar");
        f345a.add("java.util.GregorianCalendar");
        f345a.add("java.util.Date");
    }

    public static com.a.a.c.k<?> a(Class<?> cls, String str) {
        if (f345a.contains(str)) {
            if (cls == Calendar.class) {
                return new a();
            }
            if (cls == Date.class) {
                return c.f349a;
            }
            if (cls == GregorianCalendar.class) {
                return new a(GregorianCalendar.class);
            }
            return null;
        }
        return null;
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/c/b/j$b.class */
    public static abstract class b<T> extends ai<T> implements com.a.a.c.c.k {

        /* renamed from: a, reason: collision with root package name */
        private DateFormat f347a;

        /* renamed from: b, reason: collision with root package name */
        private String f348b;

        protected abstract b<T> a(DateFormat dateFormat, String str);

        protected b(Class<?> cls) {
            super(cls);
            this.f347a = null;
            this.f348b = null;
        }

        protected b(b<T> bVar, DateFormat dateFormat, String str) {
            super(bVar.s);
            this.f347a = dateFormat;
            this.f348b = str;
        }

        @Override // com.a.a.c.c.b.ai, com.a.a.c.k
        public com.a.a.c.l.f b() {
            return com.a.a.c.l.f.DateTime;
        }

        public com.a.a.c.k<?> a(com.a.a.c.g gVar, com.a.a.c.c cVar) {
            DateFormat dateFormat;
            DateFormat dateFormat2;
            l.d a2 = a(gVar, cVar, a());
            if (a2 != null) {
                TimeZone f = a2.f();
                Boolean e = a2.e();
                if (a2.h()) {
                    String b2 = a2.b();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(b2, a2.i() ? a2.d() : gVar.g());
                    if (f == null) {
                        f = gVar.h();
                    }
                    simpleDateFormat.setTimeZone(f);
                    if (e != null) {
                        simpleDateFormat.setLenient(e.booleanValue());
                    }
                    return a(simpleDateFormat, b2);
                }
                if (f != null) {
                    DateFormat s = gVar.a().s();
                    if (s.getClass() == com.a.a.c.m.ab.class) {
                        com.a.a.c.m.ab a3 = ((com.a.a.c.m.ab) s).a(f).a(a2.i() ? a2.d() : gVar.g());
                        if (e != null) {
                            a3 = a3.a(e);
                        }
                        dateFormat2 = a3;
                    } else {
                        DateFormat dateFormat3 = (DateFormat) s.clone();
                        dateFormat2 = dateFormat3;
                        dateFormat3.setTimeZone(f);
                        if (e != null) {
                            dateFormat2.setLenient(e.booleanValue());
                        }
                    }
                    return a(dateFormat2, this.f348b);
                }
                if (e != null) {
                    DateFormat s2 = gVar.a().s();
                    String str = this.f348b;
                    if (s2.getClass() == com.a.a.c.m.ab.class) {
                        com.a.a.c.m.ab a4 = ((com.a.a.c.m.ab) s2).a(e);
                        dateFormat = a4;
                        str = a4.a();
                    } else {
                        DateFormat dateFormat4 = (DateFormat) s2.clone();
                        dateFormat = dateFormat4;
                        dateFormat4.setLenient(e.booleanValue());
                        if (dateFormat instanceof SimpleDateFormat) {
                            ((SimpleDateFormat) dateFormat).toPattern();
                        }
                    }
                    if (str == null) {
                        str = "[unknown]";
                    }
                    return a(dateFormat, str);
                }
            }
            return this;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.a.a.c.c.b.ae
        public final Date a_(com.a.a.b.l lVar, com.a.a.c.g gVar) {
            Date parse;
            if (this.f347a != null && lVar.a(com.a.a.b.o.VALUE_STRING)) {
                String trim = lVar.w().trim();
                if (trim.isEmpty()) {
                    switch (a(gVar, trim)) {
                        case AsEmpty:
                            return new Date(0L);
                        default:
                            return null;
                    }
                }
                synchronized (this.f347a) {
                    try {
                        parse = this.f347a.parse(trim);
                    } catch (ParseException unused) {
                        return (Date) gVar.b(a(), trim, "expected format \"%s\"", this.f348b);
                    }
                }
                return parse;
            }
            return super.a_(lVar, gVar);
        }
    }

    @com.a.a.c.a.a
    /* loaded from: infinitode-2.jar:com/a/a/c/c/b/j$a.class */
    public static class a extends b<Calendar> {

        /* renamed from: a, reason: collision with root package name */
        private Constructor<Calendar> f346a;

        @Override // com.a.a.c.c.b.j.b, com.a.a.c.c.k
        public final /* bridge */ /* synthetic */ com.a.a.c.k a(com.a.a.c.g gVar, com.a.a.c.c cVar) {
            return super.a(gVar, cVar);
        }

        @Override // com.a.a.c.c.b.j.b, com.a.a.c.c.b.ai, com.a.a.c.k
        public final /* bridge */ /* synthetic */ com.a.a.c.l.f b() {
            return super.b();
        }

        public a() {
            super(Calendar.class);
            this.f346a = null;
        }

        public a(Class<? extends Calendar> cls) {
            super(cls);
            this.f346a = com.a.a.c.m.i.c(cls, false);
        }

        private a(a aVar, DateFormat dateFormat, String str) {
            super(aVar, dateFormat, str);
            this.f346a = aVar.f346a;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.c.b.j.b
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public a a(DateFormat dateFormat, String str) {
            return new a(this, dateFormat, str);
        }

        @Override // com.a.a.c.k
        public final Object c(com.a.a.c.g gVar) {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTimeInMillis(0L);
            return gregorianCalendar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.k
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public Calendar a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
            Date a_ = a_(lVar, gVar);
            if (a_ == null) {
                return null;
            }
            if (this.f346a == null) {
                return gVar.a(a_);
            }
            try {
                Calendar newInstance = this.f346a.newInstance(new Object[0]);
                newInstance.setTimeInMillis(a_.getTime());
                TimeZone h = gVar.h();
                if (h != null) {
                    newInstance.setTimeZone(h);
                }
                return newInstance;
            } catch (Exception e) {
                return (Calendar) gVar.a(a(), a_, e);
            }
        }
    }

    @com.a.a.c.a.a
    /* loaded from: infinitode-2.jar:com/a/a/c/c/b/j$c.class */
    public static class c extends b<Date> {

        /* renamed from: a, reason: collision with root package name */
        public static final c f349a = new c();

        @Override // com.a.a.c.c.b.j.b, com.a.a.c.c.k
        public final /* bridge */ /* synthetic */ com.a.a.c.k a(com.a.a.c.g gVar, com.a.a.c.c cVar) {
            return super.a(gVar, cVar);
        }

        @Override // com.a.a.c.c.b.j.b, com.a.a.c.c.b.ai, com.a.a.c.k
        public final /* bridge */ /* synthetic */ com.a.a.c.l.f b() {
            return super.b();
        }

        public c() {
            super(Date.class);
        }

        private c(c cVar, DateFormat dateFormat, String str) {
            super(cVar, dateFormat, str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.c.b.j.b
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public c a(DateFormat dateFormat, String str) {
            return new c(this, dateFormat, str);
        }

        @Override // com.a.a.c.k
        public final Object c(com.a.a.c.g gVar) {
            return new Date(0L);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.a.a.c.k
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public Date a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
            return a_(lVar, gVar);
        }
    }
}
