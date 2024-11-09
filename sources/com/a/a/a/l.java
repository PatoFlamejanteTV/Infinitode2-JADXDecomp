package com.a.a.a;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Locale;
import java.util.TimeZone;

@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: infinitode-2.jar:com/a/a/a/l.class */
public @interface l {

    /* loaded from: infinitode-2.jar:com/a/a/a/l$a.class */
    public enum a {
        ACCEPT_SINGLE_VALUE_AS_ARRAY,
        ACCEPT_CASE_INSENSITIVE_PROPERTIES,
        ACCEPT_CASE_INSENSITIVE_VALUES,
        WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS,
        WRITE_DATES_WITH_ZONE_ID,
        WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED,
        WRITE_SORTED_MAP_ENTRIES,
        ADJUST_DATES_TO_CONTEXT_TIME_ZONE
    }

    String a() default "";

    c b() default c.ANY;

    String c() default "##default";

    String d() default "##default";

    ao e() default ao.DEFAULT;

    a[] f() default {};

    a[] g() default {};

    /* loaded from: infinitode-2.jar:com/a/a/a/l$c.class */
    public enum c {
        ANY,
        NATURAL,
        SCALAR,
        ARRAY,
        OBJECT,
        NUMBER,
        NUMBER_FLOAT,
        NUMBER_INT,
        STRING,
        BOOLEAN,
        BINARY;

        public final boolean a() {
            return this == NUMBER || this == NUMBER_INT || this == NUMBER_FLOAT;
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/a/l$b.class */
    public static class b {

        /* renamed from: a, reason: collision with root package name */
        private final int f67a;

        /* renamed from: b, reason: collision with root package name */
        private final int f68b;
        private static final b c = new b(0, 0);

        private b(int i, int i2) {
            this.f67a = i;
            this.f68b = i2;
        }

        public static b a() {
            return c;
        }

        public static b a(l lVar) {
            return a(lVar.f(), lVar.g());
        }

        private static b a(a[] aVarArr, a[] aVarArr2) {
            int i = 0;
            for (a aVar : aVarArr) {
                i |= 1 << aVar.ordinal();
            }
            int i2 = 0;
            for (a aVar2 : aVarArr2) {
                i2 |= 1 << aVar2.ordinal();
            }
            return new b(i, i2);
        }

        public final b a(b bVar) {
            if (bVar == null) {
                return this;
            }
            int i = bVar.f68b;
            int i2 = bVar.f67a;
            if (i == 0 && i2 == 0) {
                return this;
            }
            if (this.f67a == 0 && this.f68b == 0) {
                return bVar;
            }
            int i3 = (this.f67a & (i ^ (-1))) | i2;
            int i4 = (this.f68b & (i2 ^ (-1))) | i;
            if (i3 == this.f67a && i4 == this.f68b) {
                return this;
            }
            return new b(i3, i4);
        }

        public final Boolean a(a aVar) {
            int ordinal = 1 << aVar.ordinal();
            if ((this.f68b & ordinal) != 0) {
                return Boolean.FALSE;
            }
            if ((this.f67a & ordinal) != 0) {
                return Boolean.TRUE;
            }
            return null;
        }

        public final String toString() {
            if (this == c) {
                return "EMPTY";
            }
            return String.format("(enabled=0x%x,disabled=0x%x)", Integer.valueOf(this.f67a), Integer.valueOf(this.f68b));
        }

        public final int hashCode() {
            return this.f68b + this.f67a;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj == null || obj.getClass() != getClass()) {
                return false;
            }
            b bVar = (b) obj;
            return bVar.f67a == this.f67a && bVar.f68b == this.f68b;
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/a/l$d.class */
    public static class d implements Serializable {

        /* renamed from: a, reason: collision with root package name */
        private static final d f71a = new d();

        /* renamed from: b, reason: collision with root package name */
        private final String f72b;
        private final c c;
        private final Locale d;
        private final String e;
        private final Boolean f;
        private final b g;
        private transient TimeZone h;

        public d() {
            this("", c.ANY, "", "", b.a(), null);
        }

        private d(l lVar) {
            this(lVar.a(), lVar.b(), lVar.c(), lVar.d(), b.a(lVar), lVar.e().a());
        }

        private d(String str, c cVar, String str2, String str3, b bVar, Boolean bool) {
            this(str, cVar, (str2 == null || str2.length() == 0 || "##default".equals(str2)) ? null : new Locale(str2), (str3 == null || str3.length() == 0 || "##default".equals(str3)) ? null : str3, null, bVar, bool);
        }

        private d(String str, c cVar, Locale locale, String str2, TimeZone timeZone, b bVar, Boolean bool) {
            this.f72b = str == null ? "" : str;
            this.c = cVar == null ? c.ANY : cVar;
            this.d = locale;
            this.h = timeZone;
            this.e = str2;
            this.g = bVar == null ? b.a() : bVar;
            this.f = bool;
        }

        public static final d a() {
            return f71a;
        }

        public static d a(d dVar, d dVar2) {
            return dVar == null ? dVar2 : dVar.a(dVar2);
        }

        public static final d a(l lVar) {
            return lVar == null ? f71a : new d(lVar);
        }

        public final d a(d dVar) {
            b a2;
            TimeZone timeZone;
            if (dVar == null || dVar == f71a || dVar == this) {
                return this;
            }
            if (this == f71a) {
                return dVar;
            }
            String str = dVar.f72b;
            String str2 = str;
            if (str == null || str2.isEmpty()) {
                str2 = this.f72b;
            }
            c cVar = dVar.c;
            c cVar2 = cVar;
            if (cVar == c.ANY) {
                cVar2 = this.c;
            }
            Locale locale = dVar.d;
            Locale locale2 = locale;
            if (locale == null) {
                locale2 = this.d;
            }
            b bVar = this.g;
            if (bVar == null) {
                a2 = dVar.g;
            } else {
                a2 = bVar.a(dVar.g);
            }
            Boolean bool = dVar.f;
            Boolean bool2 = bool;
            if (bool == null) {
                bool2 = this.f;
            }
            String str3 = dVar.e;
            String str4 = str3;
            if (str3 == null || str4.isEmpty()) {
                str4 = this.e;
                timeZone = this.h;
            } else {
                timeZone = dVar.h;
            }
            return new d(str2, cVar2, locale2, str4, timeZone, a2, bool2);
        }

        public static d a(boolean z) {
            return new d("", null, null, null, null, b.a(), Boolean.valueOf(z));
        }

        public final d a(Boolean bool) {
            if (bool == this.f) {
                return this;
            }
            return new d(this.f72b, this.c, this.d, this.e, this.h, this.g, bool);
        }

        public final String b() {
            return this.f72b;
        }

        public final c c() {
            return this.c;
        }

        public final Locale d() {
            return this.d;
        }

        public final Boolean e() {
            return this.f;
        }

        public final TimeZone f() {
            TimeZone timeZone = this.h;
            TimeZone timeZone2 = timeZone;
            if (timeZone == null) {
                if (this.e == null) {
                    return null;
                }
                timeZone2 = TimeZone.getTimeZone(this.e);
                this.h = timeZone2;
            }
            return timeZone2;
        }

        public final boolean g() {
            return this.c != c.ANY;
        }

        public final boolean h() {
            return this.f72b != null && this.f72b.length() > 0;
        }

        public final boolean i() {
            return this.d != null;
        }

        public final boolean j() {
            if (this.h == null) {
                return (this.e == null || this.e.isEmpty()) ? false : true;
            }
            return true;
        }

        public final boolean k() {
            return this.f != null;
        }

        public final Boolean a(a aVar) {
            return this.g.a(aVar);
        }

        public final String toString() {
            return String.format("JsonFormat.Value(pattern=%s,shape=%s,lenient=%s,locale=%s,timezone=%s,features=%s)", this.f72b, this.c, this.f, this.d, this.e, this.g);
        }

        public final int hashCode() {
            int hashCode = this.e == null ? 1 : this.e.hashCode();
            if (this.f72b != null) {
                hashCode ^= this.f72b.hashCode();
            }
            int hashCode2 = hashCode + this.c.hashCode();
            if (this.f != null) {
                hashCode2 ^= this.f.hashCode();
            }
            if (this.d != null) {
                hashCode2 += this.d.hashCode();
            }
            return hashCode2 ^ this.g.hashCode();
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj == null || obj.getClass() != getClass()) {
                return false;
            }
            d dVar = (d) obj;
            return this.c == dVar.c && this.g.equals(dVar.g) && a(this.f, dVar.f) && a(this.e, dVar.e) && a(this.f72b, dVar.f72b) && a(this.h, dVar.h) && a(this.d, dVar.d);
        }

        private static <T> boolean a(T t, T t2) {
            if (t == null) {
                return t2 == null;
            }
            if (t2 == null) {
                return false;
            }
            return t.equals(t2);
        }
    }
}
