package com.b.a.c;

import com.a.a.a.am;
import com.b.a.a.az;
import com.b.a.a.ba;
import com.b.a.a.q;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/* loaded from: infinitode-2.jar:com/b/a/c/i.class */
public class i extends d implements com.b.a.d.a<i>, Comparable<i>, Iterable<String> {

    /* renamed from: b, reason: collision with root package name */
    private int f871b;
    private int[] c;
    private int[] d;
    private int[] e;

    /* renamed from: a, reason: collision with root package name */
    TreeSet<String> f872a;
    private String f;
    private volatile com.b.a.a.a g;
    private volatile az h;
    private static /* synthetic */ boolean i;

    /* loaded from: infinitode-2.jar:com/b/a/c/i$a.class */
    public enum a {
        SHORTER_FIRST,
        LEXICOGRAPHIC,
        LONGER_FIRST
    }

    /* loaded from: infinitode-2.jar:com/b/a/c/i$b.class */
    public enum b {
        NOT_CONTAINED,
        CONTAINED,
        SIMPLE,
        CONDITION_COUNT
    }

    static {
        i = !i.class.desiredAssertionStatus();
        new i().a();
        new i(0, 1114111).a();
        com.b.a.d.d.a(0, 0, 0, 0);
    }

    public i() {
        this.f872a = new TreeSet<>();
        this.f = null;
        this.c = new int[17];
        int[] iArr = this.c;
        int i2 = this.f871b;
        this.f871b = i2 + 1;
        iArr[i2] = 1114112;
    }

    private i(i iVar) {
        this.f872a = new TreeSet<>();
        this.f = null;
        d(iVar);
    }

    public i(int i2, int i3) {
        this();
        a(0, 1114111);
    }

    public Object clone() {
        if (e()) {
            return this;
        }
        i iVar = new i(this);
        iVar.g = this.g;
        iVar.h = this.h;
        return iVar;
    }

    private i d(i iVar) {
        f();
        this.c = (int[]) iVar.c.clone();
        this.f871b = iVar.f871b;
        this.f = iVar.f;
        this.f872a = new TreeSet<>((SortedSet) iVar.f872a);
        return this;
    }

    private static void a(Appendable appendable, int i2) {
        if (!i && (i2 < 0 || i2 > 1114111)) {
            throw new AssertionError();
        }
        try {
            if (i2 <= 65535) {
                appendable.append((char) i2);
            } else {
                appendable.append(am.b(i2)).append(am.c(i2));
            }
        } catch (IOException e) {
            throw new com.b.a.d.c(e);
        }
    }

    private static <T extends Appendable> T a(T t, String str, boolean z) {
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 < str.length()) {
                int codePointAt = str.codePointAt(i3);
                a(t, codePointAt, z);
                i2 = i3 + Character.charCount(codePointAt);
            } else {
                return t;
            }
        }
    }

    private static <T extends Appendable> T a(T t, int i2, boolean z) {
        if (z) {
            try {
                if (ba.a(i2) && ba.a(t, i2)) {
                    return t;
                }
            } catch (IOException e) {
                throw new com.b.a.d.c(e);
            }
        }
        switch (i2) {
            case 36:
            case 38:
            case 45:
            case 58:
            case 91:
            case 92:
            case 93:
            case 94:
            case 123:
            case 125:
                t.append('\\');
                break;
            default:
                if (q.a(i2)) {
                    t.append('\\');
                    break;
                }
                break;
        }
        a(t, i2);
        return t;
    }

    private String a(boolean z) {
        return ((StringBuilder) a((i) new StringBuilder(), true)).toString();
    }

    private <T extends Appendable> T a(T t, boolean z) {
        if (this.f == null) {
            return (T) a((i) t, z, true);
        }
        try {
            if (!z) {
                t.append(this.f);
                return t;
            }
            boolean z2 = false;
            int i2 = 0;
            while (i2 < this.f.length()) {
                int codePointAt = this.f.codePointAt(i2);
                i2 += Character.charCount(codePointAt);
                if (ba.a(codePointAt)) {
                    ba.a(t, codePointAt);
                    z2 = false;
                } else if (!z2 && codePointAt == 92) {
                    z2 = true;
                } else {
                    if (z2) {
                        t.append('\\');
                    }
                    a(t, codePointAt);
                    z2 = false;
                }
            }
            if (z2) {
                t.append('\\');
            }
            return t;
        } catch (IOException e) {
            throw new com.b.a.d.c(e);
        }
    }

    private <T extends Appendable> T a(T t, boolean z, boolean z2) {
        try {
            t.append('[');
            int d2 = d();
            if (d2 > 1 && e(0) == 0 && f(d2 - 1) == 1114111) {
                t.append('^');
                for (int i2 = 1; i2 < d2; i2++) {
                    int f = f(i2 - 1) + 1;
                    int e = e(i2) - 1;
                    a(t, f, z);
                    if (f != e) {
                        if (f + 1 != e) {
                            t.append('-');
                        }
                        a(t, e, z);
                    }
                }
            } else {
                for (int i3 = 0; i3 < d2; i3++) {
                    int e2 = e(i3);
                    int f2 = f(i3);
                    a(t, e2, z);
                    if (e2 != f2) {
                        if (e2 + 1 != f2) {
                            t.append('-');
                        }
                        a(t, f2, z);
                    }
                }
            }
            if (this.f872a.size() > 0) {
                Iterator<String> it = this.f872a.iterator();
                while (it.hasNext()) {
                    String next = it.next();
                    t.append('{');
                    a(t, next, z);
                    t.append('}');
                }
            }
            t.append(']');
            return t;
        } catch (IOException e3) {
            throw new com.b.a.d.c(e3);
        }
    }

    private int c() {
        int i2 = 0;
        int d2 = d();
        for (int i3 = 0; i3 < d2; i3++) {
            i2 += (f(i3) - e(i3)) + 1;
        }
        return i2 + this.f872a.size();
    }

    /* loaded from: infinitode-2.jar:com/b/a/c/i$d.class */
    public static abstract class d {
        @Deprecated
        protected d() {
        }
    }

    public final i a(int i2) {
        f();
        return c(i2);
    }

    private final i c(int i2) {
        if (i2 < 0 || i2 > 1114111) {
            throw new IllegalArgumentException("Invalid code point U+" + ba.a(i2, 6));
        }
        int d2 = d(i2);
        if ((d2 & 1) != 0) {
            return this;
        }
        if (i2 == this.c[d2] - 1) {
            this.c[d2] = i2;
            if (i2 == 1114111) {
                g(this.f871b + 1);
                int[] iArr = this.c;
                int i3 = this.f871b;
                this.f871b = i3 + 1;
                iArr[i3] = 1114112;
            }
            if (d2 > 0 && i2 == this.c[d2 - 1]) {
                System.arraycopy(this.c, d2 + 1, this.c, d2 - 1, (this.f871b - d2) - 1);
                this.f871b -= 2;
            }
        } else if (d2 > 0 && i2 == this.c[d2 - 1]) {
            int[] iArr2 = this.c;
            int i4 = d2 - 1;
            iArr2[i4] = iArr2[i4] + 1;
        } else {
            if (this.f871b + 2 > this.c.length) {
                int[] iArr3 = new int[this.f871b + 2 + 16];
                if (d2 != 0) {
                    System.arraycopy(this.c, 0, iArr3, 0, d2);
                }
                System.arraycopy(this.c, d2, iArr3, d2 + 2, this.f871b - d2);
                this.c = iArr3;
            } else {
                int[] iArr4 = this.c;
                System.arraycopy(iArr4, d2, iArr4, d2 + 2, this.f871b - d2);
            }
            this.c[d2] = i2;
            this.c[d2 + 1] = i2 + 1;
            this.f871b += 2;
        }
        this.f = null;
        return this;
    }

    private i a(int i2, int i3) {
        f();
        if (i2 < 0 || i2 > 1114111) {
            throw new IllegalArgumentException("Invalid code point U+" + ba.a(i2, 6));
        }
        if (i3 < 0 || i3 > 1114111) {
            throw new IllegalArgumentException("Invalid code point U+" + ba.a(i3, 6));
        }
        if (i2 <= i3) {
            a(b(i2, i3), 2, 0);
        }
        this.f = null;
        return this;
    }

    public final boolean b(int i2) {
        if (i2 < 0 || i2 > 1114111) {
            throw new IllegalArgumentException("Invalid code point U+" + ba.a(i2, 6));
        }
        if (this.g != null) {
            return this.g.a(i2);
        }
        if (this.h != null) {
            return this.h.a(i2);
        }
        return (d(i2) & 1) != 0;
    }

    private final int d(int i2) {
        if (i2 < this.c[0]) {
            return 0;
        }
        if (this.f871b >= 2 && i2 >= this.c[this.f871b - 2]) {
            return this.f871b - 1;
        }
        int i3 = 0;
        int i4 = this.f871b - 1;
        while (true) {
            int i5 = (i3 + i4) >>> 1;
            if (i5 == i3) {
                return i4;
            }
            if (i2 < this.c[i5]) {
                i4 = i5;
            } else {
                i3 = i5;
            }
        }
    }

    public final i a(i iVar) {
        f();
        b(iVar.c, iVar.f871b, 0);
        this.f872a.retainAll(iVar.f872a);
        return this;
    }

    private int d() {
        return this.f871b / 2;
    }

    private int e(int i2) {
        return this.c[i2 << 1];
    }

    private int f(int i2) {
        return this.c[(i2 << 1) + 1] - 1;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        try {
            i iVar = (i) obj;
            if (this.f871b != iVar.f871b) {
                return false;
            }
            for (int i2 = 0; i2 < this.f871b; i2++) {
                if (this.c[i2] != iVar.c[i2]) {
                    return false;
                }
            }
            return this.f872a.equals(iVar.f872a);
        } catch (Exception unused) {
            return false;
        }
    }

    public int hashCode() {
        int i2 = this.f871b;
        for (int i3 = 0; i3 < this.f871b; i3++) {
            i2 = (i2 * 1000003) + this.c[i3];
        }
        return i2;
    }

    public String toString() {
        return a(true);
    }

    private void g(int i2) {
        if (i2 <= this.c.length) {
            return;
        }
        int[] iArr = new int[i2 + 16];
        System.arraycopy(this.c, 0, iArr, 0, this.f871b);
        this.c = iArr;
    }

    private void h(int i2) {
        if (this.e == null || i2 > this.e.length) {
            this.e = new int[i2 + 16];
        }
    }

    private int[] b(int i2, int i3) {
        if (this.d == null) {
            this.d = new int[]{i2, i3 + 1, 1114112};
        } else {
            this.d[0] = i2;
            this.d[1] = i3 + 1;
        }
        return this.d;
    }

    private i a(int[] iArr, int i2, int i3) {
        h(this.f871b + 2);
        int i4 = 0;
        int i5 = 0 + 1;
        int i6 = this.c[0];
        int i7 = 0 + 1;
        int i8 = iArr[0];
        while (true) {
            if (i6 < i8) {
                int i9 = i4;
                i4++;
                this.e[i9] = i6;
                int i10 = i5;
                i5++;
                i6 = this.c[i10];
            } else if (i8 < i6) {
                int i11 = i4;
                i4++;
                this.e[i11] = i8;
                int i12 = i7;
                i7++;
                i8 = iArr[i12];
            } else if (i6 != 1114112) {
                int i13 = i5;
                i5++;
                i6 = this.c[i13];
                int i14 = i7;
                i7++;
                i8 = iArr[i14];
            } else {
                this.e[i4] = 1114112;
                this.f871b = i4 + 1;
                int[] iArr2 = this.c;
                this.c = this.e;
                this.e = iArr2;
                this.f = null;
                return this;
            }
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x0026. Please report as an issue. */
    private i b(int[] iArr, int i2, int i3) {
        h(this.f871b + i2);
        int i4 = 0;
        int i5 = 0 + 1;
        int i6 = this.c[0];
        int i7 = 0 + 1;
        int i8 = iArr[0];
        while (true) {
            switch (i3) {
                case 0:
                    if (i6 < i8) {
                        int i9 = i5;
                        i5++;
                        i6 = this.c[i9];
                        i3 ^= 1;
                    } else if (i8 < i6) {
                        int i10 = i7;
                        i7++;
                        i8 = iArr[i10];
                        i3 ^= 2;
                    } else if (i6 == 1114112) {
                        break;
                    } else {
                        int i11 = i4;
                        i4++;
                        this.e[i11] = i6;
                        int i12 = i5;
                        i5++;
                        i6 = this.c[i12];
                        int i13 = i7;
                        i7++;
                        i8 = iArr[i13];
                        i3 = (i3 ^ 1) ^ 2;
                    }
                case 1:
                    if (i6 < i8) {
                        int i14 = i5;
                        i5++;
                        i6 = this.c[i14];
                        i3 ^= 1;
                    } else if (i8 < i6) {
                        int i15 = i4;
                        i4++;
                        this.e[i15] = i8;
                        int i16 = i7;
                        i7++;
                        i8 = iArr[i16];
                        i3 ^= 2;
                    } else if (i6 == 1114112) {
                        break;
                    } else {
                        int i17 = i5;
                        i5++;
                        i6 = this.c[i17];
                        int i18 = i7;
                        i7++;
                        i8 = iArr[i18];
                        i3 = (i3 ^ 1) ^ 2;
                    }
                case 2:
                    if (i8 < i6) {
                        int i19 = i7;
                        i7++;
                        i8 = iArr[i19];
                        i3 ^= 2;
                    } else if (i6 < i8) {
                        int i20 = i4;
                        i4++;
                        this.e[i20] = i6;
                        int i21 = i5;
                        i5++;
                        i6 = this.c[i21];
                        i3 ^= 1;
                    } else {
                        if (i6 == 1114112) {
                            break;
                        }
                        int i122 = i5;
                        i5++;
                        i6 = this.c[i122];
                        int i132 = i7;
                        i7++;
                        i8 = iArr[i132];
                        i3 = (i3 ^ 1) ^ 2;
                    }
                case 3:
                    if (i6 < i8) {
                        int i22 = i4;
                        i4++;
                        this.e[i22] = i6;
                        int i23 = i5;
                        i5++;
                        i6 = this.c[i23];
                        i3 ^= 1;
                    } else if (i8 < i6) {
                        int i24 = i4;
                        i4++;
                        this.e[i24] = i8;
                        int i25 = i7;
                        i7++;
                        i8 = iArr[i25];
                        i3 ^= 2;
                    } else if (i6 == 1114112) {
                        break;
                    } else {
                        int i26 = i4;
                        i4++;
                        this.e[i26] = i6;
                        int i1222 = i5;
                        i5++;
                        i6 = this.c[i1222];
                        int i1322 = i7;
                        i7++;
                        i8 = iArr[i1322];
                        i3 = (i3 ^ 1) ^ 2;
                    }
            }
        }
        this.e[i4] = 1114112;
        this.f871b = i4 + 1;
        int[] iArr2 = this.c;
        this.c = this.e;
        this.e = iArr2;
        this.f = null;
        return this;
    }

    private boolean e() {
        return (this.g == null && this.h == null) ? false : true;
    }

    public final i a() {
        if (!e()) {
            this.e = null;
            if (this.c.length > this.f871b + 16) {
                int i2 = this.f871b == 0 ? 1 : this.f871b;
                int[] iArr = this.c;
                this.c = new int[i2];
                int i3 = i2;
                while (true) {
                    int i4 = i3;
                    i3--;
                    if (i4 <= 0) {
                        break;
                    }
                    this.c[i3] = iArr[i3];
                }
            }
            if (!this.f872a.isEmpty()) {
                this.h = new az(this, new ArrayList(this.f872a), 127);
            }
            if (this.h == null || !this.h.a()) {
                this.g = new com.b.a.a.a(this.c, this.f871b);
            }
        }
        return this;
    }

    public final int a(CharSequence charSequence, b bVar) {
        return a(charSequence, 0, bVar);
    }

    public final int a(CharSequence charSequence, int i2, b bVar) {
        int length = charSequence.length();
        if (i2 < 0) {
            i2 = 0;
        } else if (i2 >= length) {
            return length;
        }
        if (this.g != null) {
            return this.g.a(charSequence, i2, bVar, null);
        }
        if (this.h != null) {
            return this.h.a(charSequence, i2, bVar);
        }
        if (!this.f872a.isEmpty()) {
            az azVar = new az(this, new ArrayList(this.f872a), bVar == b.NOT_CONTAINED ? 33 : 34);
            if (azVar.a()) {
                return azVar.a(charSequence, i2, bVar);
            }
        }
        return b(charSequence, i2, bVar, null);
    }

    @Deprecated
    public final int a(CharSequence charSequence, int i2, b bVar, am amVar) {
        if (amVar == null) {
            throw new IllegalArgumentException("outCount must not be null");
        }
        int length = charSequence.length();
        if (i2 < 0) {
            i2 = 0;
        } else if (i2 >= length) {
            return length;
        }
        if (this.h != null) {
            return this.h.a(charSequence, i2, bVar, amVar);
        }
        if (this.g != null) {
            return this.g.a(charSequence, i2, bVar, amVar);
        }
        if (!this.f872a.isEmpty()) {
            return new az(this, new ArrayList(this.f872a), (bVar == b.NOT_CONTAINED ? 33 : 34) | 64).a(charSequence, i2, bVar, amVar);
        }
        return b(charSequence, i2, bVar, amVar);
    }

    private int b(CharSequence charSequence, int i2, b bVar, am amVar) {
        int charCount;
        boolean z = bVar != b.NOT_CONTAINED;
        int i3 = i2;
        int length = charSequence.length();
        int i4 = 0;
        do {
            int codePointAt = Character.codePointAt(charSequence, i3);
            if (z != b(codePointAt)) {
                break;
            }
            i4++;
            charCount = i3 + Character.charCount(codePointAt);
            i3 = charCount;
        } while (charCount < length);
        if (amVar != null) {
            amVar.f47a = i4;
        }
        return i3;
    }

    public final int b(CharSequence charSequence, int i2, b bVar) {
        int charCount;
        if (i2 <= 0) {
            return 0;
        }
        if (i2 > charSequence.length()) {
            i2 = charSequence.length();
        }
        if (this.g != null) {
            return this.g.a(charSequence, i2, bVar);
        }
        if (this.h != null) {
            return this.h.b(charSequence, i2, bVar);
        }
        if (!this.f872a.isEmpty()) {
            az azVar = new az(this, new ArrayList(this.f872a), bVar == b.NOT_CONTAINED ? 17 : 18);
            if (azVar.a()) {
                return azVar.b(charSequence, i2, bVar);
            }
        }
        boolean z = bVar != b.NOT_CONTAINED;
        int i3 = i2;
        do {
            int codePointBefore = Character.codePointBefore(charSequence, i3);
            if (z != b(codePointBefore)) {
                break;
            }
            charCount = i3 - Character.charCount(codePointBefore);
            i3 = charCount;
        } while (charCount > 0);
        return i3;
    }

    public final i b() {
        i iVar = new i(this);
        if (i || !iVar.e()) {
            return iVar;
        }
        throw new AssertionError();
    }

    private void f() {
        if (e()) {
            throw new UnsupportedOperationException("Attempt to modify frozen object");
        }
    }

    @Override // java.lang.Iterable
    public Iterator<String> iterator() {
        return new c(this);
    }

    /* loaded from: infinitode-2.jar:com/b/a/c/i$c.class */
    static class c implements Iterator<String> {

        /* renamed from: a, reason: collision with root package name */
        private int[] f877a;

        /* renamed from: b, reason: collision with root package name */
        private int f878b;
        private int c;
        private int d;
        private int e;
        private TreeSet<String> f;
        private Iterator<String> g;
        private char[] h;

        c(i iVar) {
            this.f878b = iVar.f871b - 1;
            if (this.f878b > 0) {
                this.f = iVar.f872a;
                this.f877a = iVar.c;
                int[] iArr = this.f877a;
                int i = this.c;
                this.c = i + 1;
                this.d = iArr[i];
                int[] iArr2 = this.f877a;
                int i2 = this.c;
                this.c = i2 + 1;
                this.e = iArr2[i2];
                return;
            }
            this.g = iVar.f872a.iterator();
            this.f877a = null;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.f877a != null || this.g.hasNext();
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // java.util.Iterator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public String next() {
            if (this.f877a == null) {
                return this.g.next();
            }
            int i = this.d;
            this.d = i + 1;
            if (this.d >= this.e) {
                if (this.c >= this.f878b) {
                    this.g = this.f.iterator();
                    this.f877a = null;
                } else {
                    int[] iArr = this.f877a;
                    int i2 = this.c;
                    this.c = i2 + 1;
                    this.d = iArr[i2];
                    int[] iArr2 = this.f877a;
                    int i3 = this.c;
                    this.c = i3 + 1;
                    this.e = iArr2[i3];
                }
            }
            if (i <= 65535) {
                return String.valueOf((char) i);
            }
            if (this.h == null) {
                this.h = new char[2];
            }
            int i4 = i - 65536;
            this.h[0] = (char) ((i4 >>> 10) + 55296);
            this.h[1] = (char) ((i4 & 1023) + 56320);
            return String.valueOf(this.h);
        }

        @Override // java.util.Iterator
        public final void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.lang.Comparable
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public int compareTo(i iVar) {
        return a(iVar, a.SHORTER_FIRST);
    }

    private int a(i iVar, a aVar) {
        int a2;
        int c2;
        if (aVar != a.LEXICOGRAPHIC && (c2 = c() - iVar.c()) != 0) {
            return (c2 < 0) == (aVar == a.SHORTER_FIRST) ? -1 : 1;
        }
        int i2 = 0;
        while (true) {
            int i3 = this.c[i2] - iVar.c[i2];
            if (0 != i3) {
                if (this.c[i2] == 1114112) {
                    if (this.f872a.isEmpty()) {
                        return 1;
                    }
                    return a(this.f872a.first(), iVar.c[i2]);
                }
                if (iVar.c[i2] != 1114112) {
                    return (i2 & 1) == 0 ? i3 : -i3;
                }
                if (!iVar.f872a.isEmpty() && (a2 = a(iVar.f872a.first(), this.c[i2])) <= 0) {
                    return a2 < 0 ? 1 : 0;
                }
                return -1;
            }
            if (this.c[i2] != 1114112) {
                i2++;
            } else {
                return a(this.f872a, iVar.f872a);
            }
        }
    }

    private static int a(CharSequence charSequence, int i2) {
        return am.a(charSequence, i2);
    }

    private static <T extends Comparable<T>> int a(Iterable<T> iterable, Iterable<T> iterable2) {
        return a(iterable.iterator(), iterable2.iterator());
    }

    @Deprecated
    private static <T extends Comparable<T>> int a(Iterator<T> it, Iterator<T> it2) {
        while (it.hasNext()) {
            if (!it2.hasNext()) {
                return 1;
            }
            int compareTo = it.next().compareTo(it2.next());
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return it2.hasNext() ? -1 : 0;
    }
}
