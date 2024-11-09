package org.a.c.h.g.e;

import java.util.List;
import org.a.c.h.g.e.s;

/* loaded from: infinitode-2.jar:org/a/c/h/g/e/t.class */
final class t {

    /* renamed from: a, reason: collision with root package name */
    private final org.a.c.h.g.e.b f4635a;

    /* renamed from: b, reason: collision with root package name */
    private final boolean f4636b;
    private final float c;
    private final org.a.c.h.g d;
    private final s e;
    private final b f;
    private float g;
    private float h;

    /* synthetic */ t(a aVar, byte b2) {
        this(aVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/c/h/g/e/t$b.class */
    public enum b {
        LEFT(0),
        CENTER(1),
        RIGHT(2),
        JUSTIFY(4);

        private final int e;

        b(int i) {
            this.e = i;
        }

        private int a() {
            return this.e;
        }

        public static b a(int i) {
            for (b bVar : values()) {
                if (bVar.a() == i) {
                    return bVar;
                }
            }
            return LEFT;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/c/h/g/e/t$a.class */
    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private org.a.c.h.g f4637a;

        /* renamed from: b, reason: collision with root package name */
        private org.a.c.h.g.e.b f4638b;
        private s e;
        private boolean c = false;
        private float d = 0.0f;
        private b f = b.LEFT;
        private float g = 0.0f;
        private float h = 0.0f;

        /* JADX INFO: Access modifiers changed from: package-private */
        public a(org.a.c.h.g gVar) {
            this.f4637a = gVar;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final a a(org.a.c.h.g.e.b bVar) {
            this.f4638b = bVar;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final a a(boolean z) {
            this.c = z;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final a a(float f) {
            this.d = f;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final a a(int i) {
            this.f = b.a(i);
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final a a(s sVar) {
            this.e = sVar;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final a a(float f, float f2) {
            this.g = f;
            this.h = f2;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final t a() {
            return new t(this, (byte) 0);
        }
    }

    private t(a aVar) {
        this.f4635a = aVar.f4638b;
        this.f4636b = aVar.c;
        this.c = aVar.d;
        this.d = aVar.f4637a;
        this.e = aVar.e;
        this.f = aVar.f;
        this.g = aVar.g;
        this.h = aVar.h;
    }

    public final void a() {
        if (this.e != null && !this.e.a().isEmpty()) {
            boolean z = true;
            for (s.b bVar : this.e.a()) {
                if (this.f4636b) {
                    a(bVar.a(this.f4635a.a(), this.f4635a.b(), this.c), z);
                    z = false;
                } else {
                    float f = 0.0f;
                    float b2 = (this.f4635a.a().b(bVar.a()) * this.f4635a.b()) / 1000.0f;
                    if (b2 < this.c) {
                        switch (this.f) {
                            case CENTER:
                                f = (this.c - b2) / 2.0f;
                                break;
                            case RIGHT:
                                f = this.c - b2;
                                break;
                            default:
                                f = 0.0f;
                                break;
                        }
                    }
                    this.d.a(this.g + f, this.h);
                    this.d.a(bVar.a());
                }
            }
        }
    }

    private void a(List<s.a> list, boolean z) {
        float f = 0.0f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        for (s.a aVar : list) {
            switch (this.f) {
                case CENTER:
                    f2 = (this.c - aVar.a()) / 2.0f;
                    break;
                case RIGHT:
                    f2 = this.c - aVar.a();
                    break;
                case JUSTIFY:
                    if (list.indexOf(aVar) != list.size() - 1) {
                        f3 = aVar.b(this.c);
                        break;
                    }
                    break;
                default:
                    f2 = 0.0f;
                    break;
            }
            float f4 = (-f) + f2 + this.g;
            if (list.indexOf(aVar) == 0 && z) {
                this.d.a(f4, this.h);
            } else {
                this.h -= this.f4635a.c();
                this.d.a(f4, -this.f4635a.c());
            }
            f += f4;
            List<s.d> b2 = aVar.b();
            for (s.d dVar : b2) {
                this.d.a(dVar.a());
                float floatValue = ((Float) dVar.b().getIterator().getAttribute(s.c.f4632a)).floatValue();
                if (b2.indexOf(dVar) != b2.size() - 1) {
                    this.d.a(floatValue + f3, 0.0f);
                    f = f + floatValue + f3;
                }
            }
        }
        this.g -= f;
    }
}
