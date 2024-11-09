package com.a.a.c.k.b;

import com.a.a.a.l;
import com.a.a.b.h;
import java.math.BigDecimal;
import java.math.BigInteger;

@com.a.a.c.a.a
/* loaded from: infinitode-2.jar:com/a/a/c/k/b/y.class */
public final class y extends an<Number> implements com.a.a.c.k.k {

    /* renamed from: a, reason: collision with root package name */
    public static final y f637a = new y(Number.class);

    @Override // com.a.a.c.k.b.ao, com.a.a.c.o
    public final /* bridge */ /* synthetic */ void a(Object obj, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
        a((Number) obj, hVar);
    }

    public y(Class<? extends Number> cls) {
        super(cls, (byte) 0);
    }

    @Override // com.a.a.c.k.k
    public final com.a.a.c.o<?> a(com.a.a.c.aa aaVar, com.a.a.c.c cVar) {
        l.d a2 = a(aaVar, cVar, (Class<?>) a());
        if (a2 != null) {
            switch (z.f639a[a2.c().ordinal()]) {
                case 1:
                    if (a() == BigDecimal.class) {
                        return d();
                    }
                    return as.f611a;
            }
        }
        return this;
    }

    private static void a(Number number, com.a.a.b.h hVar) {
        if (number instanceof BigDecimal) {
            hVar.a((BigDecimal) number);
            return;
        }
        if (number instanceof BigInteger) {
            hVar.a((BigInteger) number);
            return;
        }
        if (number instanceof Long) {
            hVar.b(number.longValue());
            return;
        }
        if (number instanceof Double) {
            hVar.a(number.doubleValue());
            return;
        }
        if (number instanceof Float) {
            hVar.a(number.floatValue());
        } else if ((number instanceof Integer) || (number instanceof Byte) || (number instanceof Short)) {
            hVar.c(number.intValue());
        } else {
            hVar.e(number.toString());
        }
    }

    public static com.a.a.c.o<?> d() {
        return a.f638a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/a/a/c/k/b/y$a.class */
    public static final class a extends at {

        /* renamed from: a, reason: collision with root package name */
        static final a f638a = new a();

        public a() {
            super(BigDecimal.class);
        }

        @Override // com.a.a.c.k.b.at, com.a.a.c.o
        public final boolean a(com.a.a.c.aa aaVar, Object obj) {
            return false;
        }

        @Override // com.a.a.c.k.b.at, com.a.a.c.k.b.ao, com.a.a.c.o
        public final void a(Object obj, com.a.a.b.h hVar, com.a.a.c.aa aaVar) {
            String obj2;
            if (hVar.b(h.a.WRITE_BIGDECIMAL_AS_PLAIN)) {
                BigDecimal bigDecimal = (BigDecimal) obj;
                if (!a(bigDecimal)) {
                    aaVar.b(String.format("Attempt to write plain `java.math.BigDecimal` (see JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN) with illegal scale (%d): needs to be between [-%d, %d]", Integer.valueOf(bigDecimal.scale()), 9999, 9999), new Object[0]);
                }
                obj2 = bigDecimal.toPlainString();
            } else {
                obj2 = obj.toString();
            }
            hVar.b(obj2);
        }

        @Override // com.a.a.c.k.b.at
        public final String a(Object obj) {
            throw new IllegalStateException();
        }

        private static boolean a(BigDecimal bigDecimal) {
            int scale = bigDecimal.scale();
            return scale >= -9999 && scale <= 9999;
        }
    }
}
