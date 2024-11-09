package org.a.c.h.a.b.a;

/* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/h.class */
public final class h {

    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/h$a.class */
    public static abstract class a implements c {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/h$b.class */
    public enum b {
        NEWLINE,
        WHITESPACE,
        COMMENT,
        TOKEN
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/h$c.class */
    public interface c {
        void b(CharSequence charSequence);
    }

    private h() {
    }

    public static void a(CharSequence charSequence, c cVar) {
        new d(charSequence, cVar, (byte) 0).f();
    }

    /* loaded from: infinitode-2.jar:org/a/c/h/a/b/a/h$d.class */
    static final class d {

        /* renamed from: a, reason: collision with root package name */
        private final CharSequence f4472a;

        /* renamed from: b, reason: collision with root package name */
        private int f4473b;
        private final c c;
        private b d;
        private final StringBuilder e;
        private static /* synthetic */ boolean f;

        /* synthetic */ d(CharSequence charSequence, c cVar, byte b2) {
            this(charSequence, cVar);
        }

        static {
            f = !h.class.desiredAssertionStatus();
        }

        private d(CharSequence charSequence, c cVar) {
            this.d = b.WHITESPACE;
            this.e = new StringBuilder();
            this.f4472a = charSequence;
            this.c = cVar;
        }

        private boolean a() {
            return this.f4473b < this.f4472a.length();
        }

        private char b() {
            return this.f4472a.charAt(this.f4473b);
        }

        private char c() {
            this.f4473b++;
            if (!a()) {
                return (char) 4;
            }
            return b();
        }

        private char d() {
            if (this.f4473b < this.f4472a.length() - 1) {
                return this.f4472a.charAt(this.f4473b + 1);
            }
            return (char) 4;
        }

        private b e() {
            switch (b()) {
                case 0:
                case '\t':
                case ' ':
                    this.d = b.WHITESPACE;
                    break;
                case '\n':
                case '\f':
                case '\r':
                    this.d = b.NEWLINE;
                    break;
                case '%':
                    this.d = b.COMMENT;
                    break;
                default:
                    this.d = b.TOKEN;
                    break;
            }
            return this.d;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void f() {
            while (a()) {
                this.e.setLength(0);
                e();
                switch (i.f4474a[this.d.ordinal()]) {
                    case 1:
                        g();
                        break;
                    case 2:
                        h();
                        break;
                    case 3:
                        i();
                        break;
                    default:
                        j();
                        break;
                }
            }
        }

        private void g() {
            if (!f && this.d != b.NEWLINE) {
                throw new AssertionError();
            }
            char b2 = b();
            this.e.append(b2);
            if (b2 == '\r' && d() == '\n') {
                this.e.append(c());
            }
            c();
        }

        private void h() {
            if (!f && this.d != b.WHITESPACE) {
                throw new AssertionError();
            }
            this.e.append(b());
            while (a()) {
                char c = c();
                switch (c) {
                    case 0:
                    case '\t':
                    case ' ':
                        this.e.append(c);
                    default:
                        return;
                }
            }
        }

        private void i() {
            if (!f && this.d != b.COMMENT) {
                throw new AssertionError();
            }
            this.e.append(b());
            while (a()) {
                char c = c();
                switch (c) {
                    case '\n':
                    case '\f':
                    case '\r':
                        return;
                    case 11:
                    default:
                        this.e.append(c);
                }
            }
        }

        private void j() {
            if (!f && this.d != b.TOKEN) {
                throw new AssertionError();
            }
            char b2 = b();
            this.e.append(b2);
            switch (b2) {
                case '{':
                case '}':
                    this.c.b(this.e);
                    c();
                    return;
            }
            while (a()) {
                char c = c();
                switch (c) {
                    case 0:
                    case 4:
                    case '\t':
                    case '\n':
                    case '\f':
                    case '\r':
                    case ' ':
                    case '{':
                    case '}':
                        this.c.b(this.e);
                    default:
                        this.e.append(c);
                }
            }
            this.c.b(this.e);
        }
    }
}
