package com.b.a.b;

import com.b.a.a.ab;
import com.b.a.a.c;
import com.b.a.a.z;
import com.b.a.c.g;
import java.util.ArrayList;
import java.util.List;
import org.a.c.c.t;
import org.lwjgl.openal.AL10;

/* loaded from: infinitode-2.jar:com/b/a/b/a.class */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private List<t> f842a = new ArrayList();

    public static int a(int i) {
        return ab.f780a.b(i);
    }

    public static boolean b(int i) {
        return a(i) == 9;
    }

    private static int g(int i) {
        return z.f841a.a(i);
    }

    public static int c(int i) {
        return z.f841a.c(i);
    }

    public static int d(int i) {
        return z.f841a.i(i);
    }

    private static String a(String str, StringBuilder sb, g gVar) {
        if (!gVar.c()) {
            return str;
        }
        StringBuilder sb2 = new StringBuilder(str.length() + gVar.b());
        g.a d = gVar.d();
        while (d.a()) {
            if (d.b()) {
                int f = d.f();
                sb2.append((CharSequence) sb, f, f + d.d());
            } else {
                int e = d.e();
                sb2.append((CharSequence) str, e, e + d.c());
            }
        }
        return sb2.toString();
    }

    public static String a(String str, boolean z) {
        return a(str, 0);
    }

    private static String a(String str, int i) {
        if (str.length() <= 100) {
            if (str.isEmpty()) {
                return str;
            }
            g gVar = new g();
            return a(str, (StringBuilder) c.a(i | 16384, str, new StringBuilder(), gVar), gVar);
        }
        return ((StringBuilder) c.a(i, str, new StringBuilder(str.length()), null)).toString();
    }

    private static boolean b(int i, int i2) {
        return ab.f780a.b(i, 0);
    }

    public static boolean e(int i) {
        return b(i, 0);
    }

    public static int a(int i, int i2) {
        return ab.f780a.c(i, AL10.AL_BUFFERS_QUEUED);
    }

    private static boolean a(char c) {
        return Character.isHighSurrogate(c);
    }

    private static boolean b(char c) {
        return Character.isLowSurrogate(c);
    }

    private static int a(char c, char c2) {
        return Character.toCodePoint(c, c2);
    }

    public static final int a(CharSequence charSequence, int i) {
        int i2 = i + 1;
        char charAt = charSequence.charAt(i);
        if (a(charAt) && i2 < charSequence.length()) {
            char charAt2 = charSequence.charAt(i2);
            if (b(charAt2)) {
                return a(charAt, charAt2);
            }
        }
        return charAt;
    }

    public static byte f(int i) {
        return (byte) g(i);
    }

    public static int a(CharSequence charSequence, int i, int i2) {
        if (i < 0 || i > charSequence.length()) {
            throw new IndexOutOfBoundsException("index ( " + i + ") out of range 0, " + charSequence.length());
        }
        int length = charSequence.length();
        while (true) {
            i2--;
            if (i2 >= 0) {
                int i3 = i;
                i++;
                char charAt = charSequence.charAt(i3);
                while (charAt >= 55296 && charAt <= 56319 && i < length) {
                    int i4 = i;
                    i++;
                    char charAt2 = charSequence.charAt(i4);
                    charAt = charAt2;
                    if (charAt2 < 56320 || charAt > 57343) {
                        i2--;
                        if (i2 < 0) {
                            return i - 1;
                        }
                    }
                }
            } else {
                return i;
            }
        }
    }

    public void a(t tVar) {
        this.f842a.add(tVar);
    }
}
