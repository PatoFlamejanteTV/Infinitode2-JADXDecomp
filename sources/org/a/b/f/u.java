package org.a.b.f;

import java.io.IOException;
import java.util.Comparator;

/* loaded from: infinitode-2.jar:org/a/b/f/u.class */
public class u {

    /* renamed from: a, reason: collision with root package name */
    private static final org.a.a.a.a f4329a = org.a.a.a.c.a(u.class);

    /* renamed from: b, reason: collision with root package name */
    private a f4330b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/b/f/u$a.class */
    public interface a {
        void a(ak akVar);
    }

    public final void a(ak akVar, int i) {
        if (i == 0) {
            a(akVar);
        } else if (i != 1) {
            throw new IllegalStateException();
        }
    }

    private void a(ak akVar) {
        int c = akVar.c();
        if (c != 0) {
            new StringBuilder("Unsupported kerning sub-table version: ").append(c);
            return;
        }
        int c2 = akVar.c();
        if (c2 < 6) {
            throw new IOException("Kerning sub-table too short, got " + c2 + " bytes, expect 6 or more.");
        }
        int a2 = a(akVar.c(), 65280, 8);
        if (a2 == 0) {
            b(akVar);
        } else if (a2 != 2) {
            new StringBuilder("Skipped kerning subtable due to an unsupported kerning subtable version: ").append(a2);
        }
    }

    private void b(ak akVar) {
        this.f4330b = new b((byte) 0);
        this.f4330b.a(akVar);
    }

    private static int a(int i, int i2, int i3) {
        return (i & i2) >> i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/b/f/u$b.class */
    public static class b implements Comparator<int[]>, a {

        /* renamed from: a, reason: collision with root package name */
        private int[][] f4331a;

        /* renamed from: b, reason: collision with root package name */
        private static /* synthetic */ boolean f4332b;

        private b() {
        }

        @Override // java.util.Comparator
        public final /* synthetic */ int compare(int[] iArr, int[] iArr2) {
            return a(iArr, iArr2);
        }

        /* synthetic */ b(byte b2) {
            this();
        }

        static {
            f4332b = !u.class.desiredAssertionStatus();
        }

        @Override // org.a.b.f.u.a
        public final void a(ak akVar) {
            int c = akVar.c();
            akVar.c();
            akVar.c();
            akVar.c();
            this.f4331a = new int[c][3];
            for (int i = 0; i < c; i++) {
                int c2 = akVar.c();
                int c3 = akVar.c();
                short d = akVar.d();
                this.f4331a[i][0] = c2;
                this.f4331a[i][1] = c3;
                this.f4331a[i][2] = d;
            }
        }

        private static int a(int[] iArr, int[] iArr2) {
            if (!f4332b && iArr == null) {
                throw new AssertionError();
            }
            if (!f4332b && iArr.length < 2) {
                throw new AssertionError();
            }
            if (!f4332b && iArr2 == null) {
                throw new AssertionError();
            }
            if (!f4332b && iArr2.length < 2) {
                throw new AssertionError();
            }
            int i = iArr[0];
            int i2 = iArr2[0];
            if (i < i2) {
                return -1;
            }
            if (i > i2) {
                return 1;
            }
            int i3 = iArr[1];
            int i4 = iArr2[1];
            if (i3 < i4) {
                return -1;
            }
            if (i3 > i4) {
                return 1;
            }
            return 0;
        }
    }
}
