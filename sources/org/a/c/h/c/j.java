package org.a.c.h.c;

import java.nio.CharBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;

/* loaded from: infinitode-2.jar:org/a/c/h/c/j.class */
public class j {

    /* renamed from: a, reason: collision with root package name */
    private int f4504a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f4505b = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(String str) {
        return a(str, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String b(String str) {
        return a(str, false);
    }

    private static String a(String str, boolean z) {
        char[] charArray = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            if (b(str.charAt(i))) {
                charArray[i] = ' ';
            }
        }
        int i2 = 0;
        for (int i3 = 0; i3 < str.length(); i3++) {
            char c = charArray[i3];
            if (!c(c)) {
                int i4 = i2;
                i2++;
                charArray[i4] = c;
            }
        }
        String normalize = Normalizer.normalize(CharBuffer.wrap(charArray, 0, i2), Normalizer.Form.NFKC);
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        int i5 = 0;
        while (i5 < normalize.length()) {
            int codePointAt = normalize.codePointAt(i5);
            if (a(codePointAt)) {
                throw new IllegalArgumentException("Prohibited character " + codePointAt + " at position " + i5);
            }
            byte directionality = Character.getDirectionality(codePointAt);
            boolean z5 = directionality == 1 || directionality == 2;
            z2 |= z5;
            z3 |= directionality == 0;
            z4 |= i5 == 0 && z5;
            if (z || Character.isDefined(codePointAt)) {
                i5 += Character.charCount(codePointAt);
                if (z4 && i5 >= normalize.length() && !z5) {
                    throw new IllegalArgumentException("First character is RandALCat, but last character is not");
                }
            } else {
                throw new IllegalArgumentException("Character at position " + i5 + " is unassigned");
            }
        }
        if (z2 && z3) {
            throw new IllegalArgumentException("Contains both RandALCat characters and LCat characters");
        }
        return normalize;
    }

    private static boolean a(int i) {
        return b((char) i) || a((char) i) || i(i) || h(i) || g(i) || f(i) || e(i) || d(i) || c(i) || b(i);
    }

    private static boolean b(int i) {
        if (i != 917505) {
            return 917536 <= i && i <= 917631;
        }
        return true;
    }

    private static boolean c(int i) {
        return i == 832 || i == 833 || i == 8206 || i == 8207 || i == 8234 || i == 8235 || i == 8236 || i == 8237 || i == 8238 || i == 8298 || i == 8299 || i == 8300 || i == 8301 || i == 8302 || i == 8303;
    }

    private static boolean d(int i) {
        return 12272 <= i && i <= 12283;
    }

    private static boolean e(int i) {
        return i == 65529 || i == 65530 || i == 65531 || i == 65532 || i == 65533;
    }

    private static boolean f(int i) {
        return 55296 <= i && i <= 57343;
    }

    private static boolean g(int i) {
        if (64976 <= i && i <= 65007) {
            return true;
        }
        if (65534 <= i && i <= 65535) {
            return true;
        }
        if (131070 <= i && i <= 131071) {
            return true;
        }
        if (196606 <= i && i <= 196607) {
            return true;
        }
        if (262142 <= i && i <= 262143) {
            return true;
        }
        if (327678 <= i && i <= 327679) {
            return true;
        }
        if (393214 <= i && i <= 393215) {
            return true;
        }
        if (458750 <= i && i <= 458751) {
            return true;
        }
        if (524286 <= i && i <= 524287) {
            return true;
        }
        if (589822 <= i && i <= 589823) {
            return true;
        }
        if (655358 <= i && i <= 655359) {
            return true;
        }
        if (720894 <= i && i <= 720895) {
            return true;
        }
        if (786430 <= i && i <= 786431) {
            return true;
        }
        if (851966 <= i && i <= 851967) {
            return true;
        }
        if (917502 <= i && i <= 917503) {
            return true;
        }
        if (983038 <= i && i <= 983039) {
            return true;
        }
        if (1048574 > i || i > 1048575) {
            return 1114110 <= i && i <= 1114111;
        }
        return true;
    }

    private static boolean h(int i) {
        if (57344 <= i && i <= 63743) {
            return true;
        }
        if (61440 > i || i > 1048573) {
            return 1048576 <= i && i <= 1114109;
        }
        return true;
    }

    private static boolean i(int i) {
        if ((128 <= i && i <= 159) || i == 1757 || i == 1807 || i == 6158 || i == 8204 || i == 8205 || i == 8232 || i == 8233 || i == 8288 || i == 8289 || i == 8290 || i == 8291) {
            return true;
        }
        if ((8298 <= i && i <= 8303) || i == 65279) {
            return true;
        }
        if (65529 > i || i > 65532) {
            return 119155 <= i && i <= 119162;
        }
        return true;
    }

    private static boolean a(char c) {
        return (c >= 0 && c <= 31) || c == 127;
    }

    private static boolean b(char c) {
        if (c == 160 || c == 5760) {
            return true;
        }
        return (8192 <= c && c <= 8203) || c == 8239 || c == 8287 || c == 12288;
    }

    private static boolean c(char c) {
        if (c == 173 || c == 847 || c == 6150 || c == 6155 || c == 6156 || c == 6157 || c == 8203 || c == 8204 || c == 8205 || c == 8288) {
            return true;
        }
        return (65024 <= c && c <= 65039) || c == 65279;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static MessageDigest a() {
        try {
            return MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static MessageDigest b() {
        try {
            return MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static MessageDigest c() {
        try {
            return MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public j() {
        this.f4504a = -4;
        this.f4504a = -4;
    }

    public j(byte[] bArr) {
        this.f4504a = -4;
        this.f4504a = 0;
        this.f4504a |= bArr[0] & 255;
        this.f4504a <<= 8;
        this.f4504a |= bArr[1] & 255;
        this.f4504a <<= 8;
        this.f4504a |= bArr[2] & 255;
        this.f4504a <<= 8;
        this.f4504a |= bArr[3] & 255;
    }

    public j(int i) {
        this.f4504a = -4;
        this.f4504a = i;
    }

    private boolean j(int i) {
        return (this.f4504a & (1 << (i - 1))) != 0;
    }

    private boolean a(int i, boolean z) {
        int i2;
        int i3 = this.f4504a;
        if (z) {
            i2 = i3 | (1 << (i - 1));
        } else {
            i2 = i3 & ((1 << (i - 1)) ^ (-1));
        }
        this.f4504a = i2;
        return (this.f4504a & (1 << (i - 1))) != 0;
    }

    public static j d() {
        j jVar = new j();
        jVar.g(true);
        jVar.c(true);
        jVar.f(true);
        jVar.e(true);
        jVar.b(true);
        jVar.d(true);
        jVar.a(true);
        jVar.h(true);
        return jVar;
    }

    public int e() {
        a(1, true);
        a(7, false);
        a(8, false);
        for (int i = 13; i <= 32; i++) {
            a(i, false);
        }
        return this.f4504a;
    }

    public int f() {
        return this.f4504a;
    }

    public void a(boolean z) {
        if (!this.f4505b) {
            a(3, true);
        }
    }

    public void b(boolean z) {
        if (!this.f4505b) {
            a(4, true);
        }
    }

    public void c(boolean z) {
        if (!this.f4505b) {
            a(5, true);
        }
    }

    public void d(boolean z) {
        if (!this.f4505b) {
            a(6, true);
        }
    }

    public boolean g() {
        return j(9);
    }

    public void e(boolean z) {
        if (!this.f4505b) {
            a(9, true);
        }
    }

    public boolean h() {
        return j(10);
    }

    public void f(boolean z) {
        if (!this.f4505b) {
            a(10, true);
        }
    }

    public boolean i() {
        return j(11);
    }

    public void g(boolean z) {
        if (!this.f4505b) {
            a(11, true);
        }
    }

    public boolean j() {
        return j(12);
    }

    public void h(boolean z) {
        if (!this.f4505b) {
            a(12, true);
        }
    }

    public void k() {
        this.f4505b = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean l() {
        if (g() || h() || i()) {
            return true;
        }
        return j();
    }
}
