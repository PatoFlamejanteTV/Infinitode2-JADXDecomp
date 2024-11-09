package org.a.c.h.a.b.a;

import java.util.Stack;

/* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/a.class */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private final org.a.c.h.a.b.a.g f4463a;

    /* renamed from: b, reason: collision with root package name */
    private final Stack<Object> f4464b = new Stack<>();

    /* renamed from: org.a.c.h.a.b.a.a$a, reason: collision with other inner class name */
    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/a$a.class */
    static class C0046a implements org.a.c.h.a.b.a.f {
        @Override // org.a.c.h.a.b.a.f
        public final void a(a aVar) {
            Number c = aVar.c();
            if (c instanceof Integer) {
                aVar.a().push(Integer.valueOf(Math.abs(c.intValue())));
            } else {
                aVar.a().push(Float.valueOf(Math.abs(c.floatValue())));
            }
        }
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/a$b.class */
    static class b implements org.a.c.h.a.b.a.f {
        @Override // org.a.c.h.a.b.a.f
        public final void a(a aVar) {
            Number c = aVar.c();
            Number c2 = aVar.c();
            if ((c2 instanceof Integer) && (c instanceof Integer)) {
                long longValue = c2.longValue() + c.longValue();
                if (longValue < -2147483648L || longValue > 2147483647L) {
                    aVar.a().push(Float.valueOf((float) longValue));
                    return;
                } else {
                    aVar.a().push(Integer.valueOf((int) longValue));
                    return;
                }
            }
            aVar.a().push(Float.valueOf(c2.floatValue() + c.floatValue()));
        }
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/a$c.class */
    static class c implements org.a.c.h.a.b.a.f {
        @Override // org.a.c.h.a.b.a.f
        public final void a(a aVar) {
            float degrees = ((float) Math.toDegrees((float) Math.atan2(aVar.e(), aVar.e()))) % 360.0f;
            float f = degrees;
            if (degrees < 0.0f) {
                f += 360.0f;
            }
            aVar.a().push(Float.valueOf(f));
        }
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/a$d.class */
    static class d implements org.a.c.h.a.b.a.f {
        @Override // org.a.c.h.a.b.a.f
        public final void a(a aVar) {
            Number c = aVar.c();
            if (c instanceof Integer) {
                aVar.a().push(c);
            } else {
                aVar.a().push(Float.valueOf((float) Math.ceil(c.doubleValue())));
            }
        }
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/a$e.class */
    static class e implements org.a.c.h.a.b.a.f {
        @Override // org.a.c.h.a.b.a.f
        public final void a(a aVar) {
            aVar.a().push(Float.valueOf((float) Math.cos(Math.toRadians(aVar.e()))));
        }
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/a$f.class */
    static class f implements org.a.c.h.a.b.a.f {
        @Override // org.a.c.h.a.b.a.f
        public final void a(a aVar) {
            aVar.a().push(Integer.valueOf(aVar.c().intValue()));
        }
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/a$g.class */
    static class g implements org.a.c.h.a.b.a.f {
        @Override // org.a.c.h.a.b.a.f
        public final void a(a aVar) {
            aVar.a().push(Float.valueOf(aVar.c().floatValue()));
        }
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/a$h.class */
    static class h implements org.a.c.h.a.b.a.f {
        @Override // org.a.c.h.a.b.a.f
        public final void a(a aVar) {
            Number c = aVar.c();
            aVar.a().push(Float.valueOf(aVar.c().floatValue() / c.floatValue()));
        }
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/a$i.class */
    static class i implements org.a.c.h.a.b.a.f {
        @Override // org.a.c.h.a.b.a.f
        public final void a(a aVar) {
            aVar.a().push(Float.valueOf((float) Math.pow(aVar.c().doubleValue(), aVar.c().doubleValue())));
        }
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/a$j.class */
    static class j implements org.a.c.h.a.b.a.f {
        @Override // org.a.c.h.a.b.a.f
        public final void a(a aVar) {
            Number c = aVar.c();
            if (c instanceof Integer) {
                aVar.a().push(c);
            } else {
                aVar.a().push(Float.valueOf((float) Math.floor(c.doubleValue())));
            }
        }
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/a$k.class */
    static class k implements org.a.c.h.a.b.a.f {
        @Override // org.a.c.h.a.b.a.f
        public final void a(a aVar) {
            int d = aVar.d();
            aVar.a().push(Integer.valueOf(aVar.d() / d));
        }
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/a$l.class */
    static class l implements org.a.c.h.a.b.a.f {
        @Override // org.a.c.h.a.b.a.f
        public final void a(a aVar) {
            aVar.a().push(Float.valueOf((float) Math.log(aVar.c().doubleValue())));
        }
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/a$m.class */
    static class m implements org.a.c.h.a.b.a.f {
        @Override // org.a.c.h.a.b.a.f
        public final void a(a aVar) {
            aVar.a().push(Float.valueOf((float) Math.log10(aVar.c().doubleValue())));
        }
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/a$n.class */
    static class n implements org.a.c.h.a.b.a.f {
        @Override // org.a.c.h.a.b.a.f
        public final void a(a aVar) {
            int d = aVar.d();
            aVar.a().push(Integer.valueOf(aVar.d() % d));
        }
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/a$o.class */
    static class o implements org.a.c.h.a.b.a.f {
        @Override // org.a.c.h.a.b.a.f
        public final void a(a aVar) {
            Number c = aVar.c();
            Number c2 = aVar.c();
            if ((c2 instanceof Integer) && (c instanceof Integer)) {
                long longValue = c2.longValue() * c.longValue();
                if (longValue < -2147483648L || longValue > 2147483647L) {
                    aVar.a().push(Float.valueOf((float) longValue));
                    return;
                } else {
                    aVar.a().push(Integer.valueOf((int) longValue));
                    return;
                }
            }
            aVar.a().push(Float.valueOf((float) (c2.doubleValue() * c.doubleValue())));
        }
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/a$p.class */
    static class p implements org.a.c.h.a.b.a.f {
        @Override // org.a.c.h.a.b.a.f
        public final void a(a aVar) {
            Number c = aVar.c();
            if (!(c instanceof Integer)) {
                aVar.a().push(Float.valueOf(-c.floatValue()));
            } else if (c.intValue() == Integer.MIN_VALUE) {
                aVar.a().push(Float.valueOf(-c.floatValue()));
            } else {
                aVar.a().push(Integer.valueOf(-c.intValue()));
            }
        }
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/a$q.class */
    static class q implements org.a.c.h.a.b.a.f {
        @Override // org.a.c.h.a.b.a.f
        public final void a(a aVar) {
            Number c = aVar.c();
            if (c instanceof Integer) {
                aVar.a().push(Integer.valueOf(c.intValue()));
            } else {
                aVar.a().push(Float.valueOf((float) Math.round(c.doubleValue())));
            }
        }
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/a$r.class */
    static class r implements org.a.c.h.a.b.a.f {
        @Override // org.a.c.h.a.b.a.f
        public final void a(a aVar) {
            aVar.a().push(Float.valueOf((float) Math.sin(Math.toRadians(aVar.e()))));
        }
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/a$s.class */
    static class s implements org.a.c.h.a.b.a.f {
        @Override // org.a.c.h.a.b.a.f
        public final void a(a aVar) {
            float e = aVar.e();
            if (e >= 0.0f) {
                aVar.a().push(Float.valueOf((float) Math.sqrt(e)));
                return;
            }
            throw new IllegalArgumentException("argument must be nonnegative");
        }
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/a$t.class */
    static class t implements org.a.c.h.a.b.a.f {
        @Override // org.a.c.h.a.b.a.f
        public final void a(a aVar) {
            Stack<Object> a2 = aVar.a();
            Number c = aVar.c();
            Number c2 = aVar.c();
            if ((c2 instanceof Integer) && (c instanceof Integer)) {
                long longValue = c2.longValue() - c.longValue();
                if (longValue < -2147483648L || longValue > 2147483647L) {
                    a2.push(Float.valueOf((float) longValue));
                    return;
                } else {
                    a2.push(Integer.valueOf((int) longValue));
                    return;
                }
            }
            a2.push(Float.valueOf(c2.floatValue() - c.floatValue()));
        }
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/a$u.class */
    static class u implements org.a.c.h.a.b.a.f {
        @Override // org.a.c.h.a.b.a.f
        public final void a(a aVar) {
            Number c = aVar.c();
            if (c instanceof Integer) {
                aVar.a().push(Integer.valueOf(c.intValue()));
            } else {
                aVar.a().push(Float.valueOf((int) c.floatValue()));
            }
        }
    }

    public a(org.a.c.h.a.b.a.g gVar) {
        this.f4463a = gVar;
    }

    public Stack<Object> a() {
        return this.f4464b;
    }

    public org.a.c.h.a.b.a.g b() {
        return this.f4463a;
    }

    public Number c() {
        return (Number) this.f4464b.pop();
    }

    public int d() {
        return ((Integer) this.f4464b.pop()).intValue();
    }

    public float e() {
        return ((Number) this.f4464b.pop()).floatValue();
    }
}
