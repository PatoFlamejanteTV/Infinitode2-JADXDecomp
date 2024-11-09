package com.b.a.c;

import com.b.a.a.z;
import org.lwjgl.opengl.CGL;
import org.lwjgl.opengl.GLXEXTContextPriority;
import org.lwjgl.opengl.NVTextureShader;

/* loaded from: infinitode-2.jar:com/b/a/c/a.class */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    private final int f845a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f846b;
    private boolean c;
    private char d;
    private static final int[] e = {0, 2, 4, 6, 8, 10, 12, 14};
    private static final int[] f = {1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1};
    private static final int[] g = {0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1};
    private static final char[] h = {65263, 65264};
    private static final char[] i = {1570, 1571, 1573, 1575};
    private static final int[] j = {4385, 4897, 5377, 5921, 6403, 7457, 7939, 8961, 9475, 10499, 11523, GLXEXTContextPriority.GLX_CONTEXT_PRIORITY_LOW_EXT, 13571, 14593, 15105, 15617, 16129, 16643, 17667, 18691, 19715, 20739, 21763, 22787, 23811, 0, 0, 0, 0, 0, 3, 24835, 25859, 26883, 27923, 28931, 29955, 30979, 32001, 32513, 33027, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 34049, NVTextureShader.GL_SIGNED_LUMINANCE_NV, 35073, 35585, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 33, 33, 0, 33, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 3, 3, 3, 3, 1, 1};
    private static final int[] k = {3, 3, 3, 0, 3, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 32, 33, 32, 33, 0, 1, 32, 33, 0, 2, 3, 1, 32, 33, 0, 2, 3, 1, 0, 1, 0, 2, 3, 1, 0, 2, 3, 1, 0, 2, 3, 1, 0, 2, 3, 1, 0, 2, 3, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 2, 3, 1, 0, 2, 3, 1, 0, 2, 3, 1, 0, 2, 3, 1, 0, 2, 3, 1, 0, 2, 3, 1, 0, 2, 3, 1, 0, 2, 3, 1, 0, 2, 3, 1, 0, 2, 3, 1, 0, 2, 3, 1, 16, 18, 19, 17, 0, 2, 3, 1, 0, 2, 3, 1, 0, 2, 3, 1, 0, 1, 0, 1, 0, 2, 3, 1, 0, 1, 0, 1, 0, 1, 0, 1};
    private static int[] l = {1611, 1611, 1612, 1612, 1613, 1613, 1614, 1614, 1615, 1615, 1616, 1616, 1617, 1617, 1618, 1618, 1569, 1570, 1570, 1571, 1571, 1572, 1572, 1573, 1573, 1574, 1574, 1574, 1574, 1575, 1575, 1576, 1576, 1576, 1576, 1577, 1577, 1578, 1578, 1578, 1578, 1579, 1579, 1579, 1579, 1580, 1580, 1580, 1580, 1581, 1581, 1581, 1581, 1582, 1582, 1582, 1582, 1583, 1583, 1584, 1584, 1585, 1585, 1586, 1586, 1587, 1587, 1587, 1587, 1588, 1588, 1588, 1588, 1589, 1589, 1589, 1589, 1590, 1590, 1590, 1590, 1591, 1591, 1591, 1591, 1592, 1592, 1592, 1592, 1593, 1593, 1593, 1593, 1594, 1594, 1594, 1594, 1601, 1601, 1601, 1601, 1602, 1602, 1602, 1602, 1603, 1603, 1603, 1603, 1604, 1604, 1604, 1604, 1605, 1605, 1605, 1605, 1606, 1606, 1606, 1606, 1607, 1607, 1607, 1607, 1608, 1608, 1609, 1609, 1610, 1610, 1610, 1610, 1628, 1628, 1629, 1629, 1630, 1630, 1631, 1631};
    private static final int[][][] m = {new int[]{new int[]{0, 0, 0, 0}, new int[]{0, 0, 0, 0}, new int[]{0, 1, 0, 3}, new int[]{0, 1, 0, 1}}, new int[]{new int[]{0, 0, 2, 2}, new int[]{0, 0, 1, 2}, new int[]{0, 1, 1, 2}, new int[]{0, 1, 1, 3}}, new int[]{new int[]{0, 0, 0, 0}, new int[]{0, 0, 0, 0}, new int[]{0, 1, 0, 3}, new int[]{0, 1, 0, 3}}, new int[]{new int[]{0, 0, 1, 2}, new int[]{0, 0, 1, 2}, new int[]{0, 1, 1, 2}, new int[]{0, 1, 1, 3}}};

    private int a(char[] cArr, int i2, int i3, char[] cArr2, int i4, int i5) {
        if (cArr == null) {
            throw new IllegalArgumentException("source can not be null");
        }
        if (i3 < 0 || i3 + 0 > cArr.length) {
            throw new IllegalArgumentException("bad source start (0) or length (" + i3 + ") for buffer of length " + cArr.length);
        }
        if (cArr2 == null && i5 != 0) {
            throw new IllegalArgumentException("null dest requires destSize == 0");
        }
        if (i5 != 0 && (i5 < 0 || i5 + 0 > cArr2.length)) {
            throw new IllegalArgumentException("bad dest start (0) or size (" + i5 + ") for buffer of length " + cArr2.length);
        }
        if ((this.f845a & 917504) != 0 && (this.f845a & 917504) != 262144 && (this.f845a & 917504) != 393216 && (this.f845a & 917504) != 524288 && (this.f845a & 917504) != 786432) {
            throw new IllegalArgumentException("Wrong Tashkeel argument");
        }
        if ((this.f845a & 65539) != 0 && (this.f845a & 65539) != 3 && (this.f845a & 65539) != 2 && (this.f845a & 65539) != 0 && (this.f845a & 65539) != 65536 && (this.f845a & 65539) != 1) {
            throw new IllegalArgumentException("Wrong Lam Alef argument");
        }
        if ((this.f845a & 917504) != 0 && (this.f845a & 24) == 16) {
            throw new IllegalArgumentException("Tashkeel replacement should not be enabled in deshaping mode ");
        }
        return b(cArr, 0, i3, cArr2, 0, i5);
    }

    public final String a(String str) {
        char[] charArray = str.toCharArray();
        char[] cArr = charArray;
        if ((this.f845a & 65539) == 0 && (this.f845a & 24) == 16) {
            cArr = new char[charArray.length << 1];
        }
        return new String(cArr, 0, a(charArray, 0, charArray.length, cArr, 0, cArr.length));
    }

    public a(int i2) {
        this.f845a = i2;
        if ((i2 & CGL.kCGLCPDispatchTableSize) > 128) {
            throw new IllegalArgumentException("bad DIGITS options");
        }
        this.f846b = (i2 & 4) == 0;
        this.c = (i2 & 67108864) == 67108864;
        if ((i2 & 134217728) == 134217728) {
            this.d = (char) 65139;
        } else {
            this.d = (char) 8203;
        }
    }

    public final boolean equals(Object obj) {
        return obj != null && obj.getClass() == a.class && this.f845a == ((a) obj).f845a;
    }

    public final int hashCode() {
        return this.f845a;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append('[');
        switch (this.f845a & 65539) {
            case 0:
                sb.append("LamAlef resize");
                break;
            case 1:
                sb.append("LamAlef spaces at near");
                break;
            case 2:
                sb.append("LamAlef spaces at end");
                break;
            case 3:
                sb.append("LamAlef spaces at begin");
                break;
            case 65536:
                sb.append("lamAlef auto");
                break;
        }
        switch (this.f845a & 4) {
            case 0:
                sb.append(", logical");
                break;
            case 4:
                sb.append(", visual");
                break;
        }
        switch (this.f845a & 24) {
            case 0:
                sb.append(", no letter shaping");
                break;
            case 8:
                sb.append(", shape letters");
                break;
            case 16:
                sb.append(", unshape letters");
                break;
            case 24:
                sb.append(", shape letters tashkeel isolated");
                break;
        }
        switch (this.f845a & 7340032) {
            case 2097152:
                sb.append(", Seen at near");
                break;
        }
        switch (this.f845a & 58720256) {
            case 16777216:
                sb.append(", Yeh Hamza at near");
                break;
        }
        switch (this.f845a & 917504) {
            case 262144:
                sb.append(", Tashkeel at begin");
                break;
            case 393216:
                sb.append(", Tashkeel at end");
                break;
            case 524288:
                sb.append(", Tashkeel resize");
                break;
            case 786432:
                sb.append(", Tashkeel replace with tatweel");
                break;
        }
        switch (this.f845a & CGL.kCGLCPDispatchTableSize) {
            case 0:
                sb.append(", no digit shaping");
                break;
            case 32:
                sb.append(", shape digits to AN");
                break;
            case 64:
                sb.append(", shape digits to EN");
                break;
            case 96:
                sb.append(", shape digits to AN contextually: default EN");
                break;
            case 128:
                sb.append(", shape digits to AN contextually: default AL");
                break;
        }
        switch (this.f845a & 256) {
            case 0:
                sb.append(", standard Arabic-Indic digits");
                break;
            case 256:
                sb.append(", extended Arabic-Indic digits");
                break;
        }
        sb.append("]");
        return sb.toString();
    }

    private static void a(char[] cArr, int i2, int i3, char c, boolean z) {
        z zVar = z.f841a;
        char c2 = (char) (c - '0');
        int i4 = i3 + 0;
        while (true) {
            i4--;
            if (i4 >= 0) {
                char c3 = cArr[i4];
                switch (zVar.a(c3)) {
                    case 0:
                    case 1:
                        z = false;
                        break;
                    case 2:
                        if (z && c3 <= '9') {
                            cArr[i4] = (char) (c3 + c2);
                            break;
                        }
                        break;
                    case 13:
                        z = true;
                        break;
                }
            } else {
                return;
            }
        }
    }

    private static void a(char[] cArr, int i2, int i3) {
        int i4 = 0;
        for (int i5 = (i3 + 0) - 1; i4 < i5; i5--) {
            char c = cArr[i4];
            cArr[i4] = cArr[i5];
            cArr[i5] = c;
            i4++;
        }
    }

    private static char a(char c) {
        switch (c) {
            case 1570:
                return (char) 1628;
            case 1571:
                return (char) 1629;
            case 1572:
            case 1574:
            default:
                return (char) 0;
            case 1573:
                return (char) 1630;
            case 1575:
                return (char) 1631;
        }
    }

    private static int b(char c) {
        if ((c > 1569 && c < 1574) || c == 1575) {
            return 1;
        }
        if (c > 1582 && c < 1587) {
            return 1;
        }
        if ((c > 1607 && c < 1610) || c == 1577) {
            return 1;
        }
        if (c >= 1611 && c <= 1618) {
            return 2;
        }
        if ((c >= 1619 && c <= 1621) || c == 1648) {
            return 3;
        }
        if (c >= 65136 && c <= 65151) {
            return 3;
        }
        return 0;
    }

    private static int c(char c) {
        if (c >= 1570 && c <= 1747) {
            return j[c - 1570];
        }
        if (c == 8205) {
            return 3;
        }
        if (c >= 8301 && c <= 8303) {
            return 4;
        }
        if (c >= 65136 && c <= 65276) {
            return k[c - 65136];
        }
        return 0;
    }

    private static int b(char[] cArr, int i2, int i3) {
        int i4 = i2 + i3;
        for (int i5 = i2; i5 < i4; i5++) {
            if (cArr[i5] != ' ') {
                return i5 - i2;
            }
        }
        return i3;
    }

    private static int c(char[] cArr, int i2, int i3) {
        int i4 = i2 + i3;
        do {
            i4--;
            if (i4 < i2) {
                return i3;
            }
        } while (cArr[i4] == ' ');
        return ((i2 + i3) - 1) - i4;
    }

    private static boolean d(char c) {
        return c >= 1611 && c <= 1618;
    }

    private static int e(char c) {
        if (c >= 65201 && c < 65215) {
            return f[c - 65201];
        }
        return 0;
    }

    private static int f(char c) {
        if (c >= 1587 && c <= 1590) {
            return 1;
        }
        return 0;
    }

    private static boolean g(char c) {
        if (c == 8203 || c == 65139) {
            return true;
        }
        return false;
    }

    private static boolean h(char c) {
        return c == 65263 || c == 65264 || c == 1609;
    }

    private static boolean i(char c) {
        if (c == 65161 || c == 65162) {
            return true;
        }
        return false;
    }

    private static boolean j(char c) {
        return c != 65141 && c >= 65136 && c <= 65151;
    }

    private static int k(char c) {
        if (c >= 65136 && c <= 65151 && c != 65139 && c != 65141 && c != 65149) {
            return g[c - 65136];
        }
        if ((c >= 64754 && c <= 64756) || c == 65149) {
            return 2;
        }
        return 0;
    }

    private static int l(char c) {
        if (c >= 65136 && c <= 65151 && c != 65139 && c != 65141) {
            return 1 - g[c - 65136];
        }
        if (c >= 64606 && c <= 64611) {
            return 1;
        }
        return 0;
    }

    private static boolean m(char c) {
        return c == 1570 || c == 1571 || c == 1573 || c == 1575;
    }

    private static boolean n(char c) {
        return c >= 65269 && c <= 65276;
    }

    private static boolean o(char c) {
        return c >= 1628 && c <= 1631;
    }

    private int d(char[] cArr, int i2, int i3) {
        int i4 = i3;
        switch (this.f845a & 24) {
            case 8:
            case 24:
                if (this.f846b) {
                    int i5 = (i2 + i3) - 1;
                    for (int i6 = i2; i6 < i5; i6++) {
                        if ((cArr[i6] == 1604 && m(cArr[i6 + 1])) || j(cArr[i6])) {
                            i4--;
                        }
                    }
                    break;
                } else {
                    int i7 = i2 + i3;
                    for (int i8 = i2 + 1; i8 < i7; i8++) {
                        if ((cArr[i8] == 1604 && m(cArr[i8 - 1])) || j(cArr[i8])) {
                            i4--;
                        }
                    }
                    break;
                }
                break;
            case 16:
                int i9 = i2 + i3;
                for (int i10 = i2; i10 < i9; i10++) {
                    if (n(cArr[i10])) {
                        i4++;
                    }
                }
                break;
        }
        return i4;
    }

    private static int a(char[] cArr, int i2, char c) {
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            if (cArr[i4] == c) {
                i3++;
            }
        }
        return i3;
    }

    private static void a(char[] cArr, int i2, int i3, char c) {
        int i4 = i3;
        int i5 = i3;
        while (true) {
            i5--;
            if (i5 >= i2) {
                char c2 = cArr[i5];
                if (c2 != c) {
                    i4--;
                    if (i4 != i5) {
                        cArr[i4] = c2;
                    }
                }
            } else {
                return;
            }
        }
    }

    private static int a(char[] cArr, int i2, int i3, int i4) {
        int i5;
        if (i4 > i2) {
            int i6 = i4;
            i5 = i2;
            while (i6 < i3) {
                int i7 = i5;
                i5++;
                int i8 = i6;
                i6++;
                cArr[i7] = cArr[i8];
            }
        } else {
            i5 = i3;
        }
        return i5;
    }

    private static int a(char[] cArr, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            if (k(cArr[i3]) == 1) {
                cArr[i3] = 1600;
            } else if (k(cArr[i3]) == 2) {
                cArr[i3] = 65149;
            } else if (l(cArr[i3]) == 1 && cArr[i3] != 65148) {
                cArr[i3] = ' ';
            }
        }
        return i2;
    }

    private int e(char[] cArr, int i2, int i3) {
        int i4 = this.f845a & 65539;
        int i5 = this.f845a & 917504;
        boolean z = false;
        boolean z2 = false;
        if ((!this.f846b) & (!this.c)) {
            switch (i4) {
                case 2:
                    i4 = 3;
                    break;
                case 3:
                    i4 = 2;
                    break;
            }
            switch (i5) {
                case 262144:
                    i5 = 393216;
                    break;
                case 393216:
                    i5 = 262144;
                    break;
            }
        }
        if (i4 == 1) {
            int i6 = i2 + i3;
            for (int i7 = i2; i7 < i6; i7++) {
                if (cArr[i7] == 65535) {
                    cArr[i7] = ' ';
                }
            }
        } else {
            int i8 = i2 + i3;
            int a2 = a(cArr, i3, (char) 65535);
            int a3 = a(cArr, i3, (char) 65534);
            if (i4 == 2) {
                z = true;
            }
            if (i5 == 393216) {
                z2 = true;
            }
            if (z && i4 == 2) {
                a(cArr, i2, i8, (char) 65535);
                while (a2 > i2) {
                    a2--;
                    cArr[a2] = ' ';
                }
            }
            if (z2 && i5 == 393216) {
                a(cArr, i2, i8, (char) 65534);
                while (a3 > i2) {
                    a3--;
                    cArr[a3] = ' ';
                }
            }
            boolean z3 = false;
            boolean z4 = false;
            if (i4 == 0) {
                z3 = true;
            }
            if (i5 == 524288) {
                z4 = true;
            }
            if (z3 && i4 == 0) {
                a(cArr, i2, i8, (char) 65535);
                int a4 = a(cArr, i2, i8, a2);
                a2 = a4;
                i3 = a4 - i2;
            }
            if (z4 && i5 == 524288) {
                a(cArr, i2, i8, (char) 65534);
                int a5 = a(cArr, i2, i8, a3);
                a3 = a5;
                i3 = a5 - i2;
            }
            boolean z5 = false;
            boolean z6 = false;
            if (i4 == 3 || i4 == 65536) {
                z5 = true;
            }
            if (i5 == 262144) {
                z6 = true;
            }
            if (z5 && (i4 == 3 || i4 == 65536)) {
                a(cArr, i2, i8, (char) 65535);
                int a6 = a(cArr, i2, i8, a2);
                while (a6 < i8) {
                    int i9 = a6;
                    a6++;
                    cArr[i9] = ' ';
                }
            }
            if (z6 && i5 == 262144) {
                a(cArr, i2, i8, (char) 65534);
                int a7 = a(cArr, i2, i8, a3);
                while (a7 < i8) {
                    int i10 = a7;
                    a7++;
                    cArr[i10] = ' ';
                }
            }
        }
        return i3;
    }

    private static boolean b(char[] cArr, int i2, int i3, int i4) {
        if (i4 > c(cArr, i2, i3)) {
            return true;
        }
        int i5 = (i2 + i3) - i4;
        int i6 = i2 + i3;
        while (true) {
            i5--;
            if (i5 >= i2) {
                char c = cArr[i5];
                if (o(c)) {
                    int i7 = i6 - 1;
                    cArr[i7] = 1604;
                    i6 = i7 - 1;
                    cArr[i6] = i[c - 1628];
                } else {
                    i6--;
                    cArr[i6] = c;
                }
            } else {
                return false;
            }
        }
    }

    private static boolean c(char[] cArr, int i2, int i3, int i4) {
        if (i4 > b(cArr, i2, i3)) {
            return true;
        }
        int i5 = i2;
        int i6 = i2 + i3;
        for (int i7 = i2 + i4; i7 < i6; i7++) {
            char c = cArr[i7];
            if (o(c)) {
                int i8 = i5;
                int i9 = i5 + 1;
                cArr[i8] = i[c - 1628];
                i5 = i9 + 1;
                cArr[i9] = 1604;
            } else {
                int i10 = i5;
                i5++;
                cArr[i10] = c;
            }
        }
        return false;
    }

    private boolean a(char[] cArr, int i2, int i3, int i4, int i5, int i6) {
        if (o(cArr[i2])) {
            return true;
        }
        int i7 = i2 + i3;
        while (true) {
            i7--;
            if (i7 >= i2) {
                char c = cArr[i7];
                if (i6 == 1 && o(c)) {
                    if (i7 > i2 && cArr[i7 - 1] == ' ') {
                        cArr[i7] = 1604;
                        i7--;
                        cArr[i7] = i[c - 1628];
                    } else {
                        return true;
                    }
                } else if (i5 == 1 && e(c) == 1) {
                    if (i7 > i2 && cArr[i7 - 1] == ' ') {
                        cArr[i7 - 1] = this.d;
                    } else {
                        return true;
                    }
                } else if (i4 == 1 && i(c)) {
                    if (i7 > i2 && cArr[i7 - 1] == ' ') {
                        cArr[i7] = h[c - 65161];
                        cArr[i7 - 1] = 65152;
                    } else {
                        return true;
                    }
                }
            } else {
                return false;
            }
        }
    }

    private int a(char[] cArr, int i2, int i3, int i4, int i5) {
        int i6 = this.f845a & 65539;
        int i7 = this.f845a & 7340032;
        int i8 = this.f845a & 58720256;
        if (!this.f846b && !this.c) {
            switch (i6) {
                case 2:
                    i6 = 3;
                    break;
                case 3:
                    i6 = 2;
                    break;
            }
        }
        if (i5 == 1) {
            if (i6 == 65536) {
                if (this.f846b) {
                    boolean c = c(cArr, i2, i3, i4);
                    boolean z = c;
                    if (c) {
                        z = b(cArr, i2, i3, i4);
                    }
                    if (z) {
                        z = a(cArr, i2, i3, 0, 0, 1);
                    }
                    if (z) {
                        throw new b("No spacefor lamalef");
                    }
                } else {
                    boolean b2 = b(cArr, i2, i3, i4);
                    boolean z2 = b2;
                    if (b2) {
                        z2 = c(cArr, i2, i3, i4);
                    }
                    if (z2) {
                        z2 = a(cArr, i2, i3, 0, 0, 1);
                    }
                    if (z2) {
                        throw new b("No spacefor lamalef");
                    }
                }
            } else if (i6 == 2) {
                if (c(cArr, i2, i3, i4)) {
                    throw new b("No spacefor lamalef");
                }
            } else if (i6 == 3) {
                if (b(cArr, i2, i3, i4)) {
                    throw new b("No spacefor lamalef");
                }
            } else if (i6 == 1) {
                if (a(cArr, i2, i3, 0, 0, 1)) {
                    throw new b("No spacefor lamalef");
                }
            } else if (i6 == 0) {
                int i9 = i2 + i3;
                int i10 = i9;
                int i11 = i9 + i4;
                while (true) {
                    i10--;
                    if (i10 >= i2) {
                        char c2 = cArr[i10];
                        if (o(c2)) {
                            int i12 = i11 - 1;
                            cArr[i12] = 1604;
                            i11 = i12 - 1;
                            cArr[i11] = i[c2 - 1628];
                        } else {
                            i11--;
                            cArr[i11] = c2;
                        }
                    } else {
                        i3 += i4;
                    }
                }
            }
        } else {
            if (i7 == 2097152 && a(cArr, i2, i3, 0, 1, 0)) {
                throw new b("No space for Seen tail expansion");
            }
            if (i8 == 16777216 && a(cArr, i2, i3, 1, 0, 0)) {
                throw new b("No space for YehHamza expansion");
            }
        }
        return i3;
    }

    private static int f(char[] cArr, int i2, int i3) {
        int i4 = 0;
        int i5 = i2 + i3;
        for (int i6 = i2; i6 < i5; i6++) {
            char c = cArr[i6];
            if (c >= 65136 && c <= 65276) {
                if (n(c)) {
                    i4++;
                }
                cArr[i6] = (char) l[c - 65136];
            }
        }
        return i4;
    }

    private int g(char[] cArr, int i2, int i3) {
        int i4 = 0;
        boolean z = (this.f845a & 58720256) == 16777216;
        boolean z2 = (this.f845a & 7340032) == 2097152;
        int i5 = i2 + i3;
        for (int i6 = i2; i6 < i5; i6++) {
            char c = cArr[i6];
            if (z && ((c == 1569 || c == 65152) && i6 < i3 - 1 && h(cArr[i6 + 1]))) {
                cArr[i6] = ' ';
                cArr[i6 + 1] = 1574;
            } else if (z2 && g(c) && i6 < i3 - 1 && e(cArr[i6 + 1]) == 1) {
                cArr[i6] = ' ';
            } else if (c >= 65136 && c <= 65276) {
                if (n(c)) {
                    i4++;
                }
                cArr[i6] = (char) l[c - 65136];
            }
        }
        return i4;
    }

    private int d(char[] cArr, int i2, int i3, int i4) {
        int f2 = f(cArr, 0, i3);
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        int i5 = (i3 + 0) - 1;
        int c = c(cArr[i5]);
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = i5;
        int i10 = -2;
        while (i5 >= 0) {
            if ((c & 65280) != 0 || d(cArr[i5])) {
                int i11 = i5 - 1;
                i10 = -2;
                while (i10 < 0) {
                    if (i11 == -1) {
                        i6 = 0;
                        i10 = Integer.MAX_VALUE;
                    } else {
                        int c2 = c(cArr[i11]);
                        i6 = c2;
                        if ((c2 & 4) == 0) {
                            i10 = i11;
                        } else {
                            i11--;
                        }
                    }
                }
                if ((c & 32) > 0 && (i8 & 16) > 0) {
                    z = true;
                    char a2 = a(cArr[i5]);
                    if (a2 != 0) {
                        cArr[i5] = 65535;
                        cArr[i9] = a2;
                        i5 = i9;
                    }
                    i8 = i7;
                    c = c(a2);
                }
                if (i5 > 0 && cArr[i5 - 1] == ' ') {
                    if (f(cArr[i5]) == 1) {
                        z2 = true;
                    } else if (cArr[i5] == 1574) {
                        z3 = true;
                    }
                } else if (i5 == 0) {
                    if (f(cArr[i5]) == 1) {
                        z2 = true;
                    } else if (cArr[i5] == 1574) {
                        z3 = true;
                    }
                }
                int b2 = b(cArr[i5]);
                int i12 = m[i6 & 3][i8 & 3][c & 3];
                if (b2 == 1) {
                    i12 &= 1;
                } else if (b2 == 2) {
                    if (i4 == 0 && (i8 & 2) != 0 && (i6 & 1) != 0 && cArr[i5] != 1612 && cArr[i5] != 1613 && ((i6 & 32) != 32 || (i8 & 16) != 16)) {
                        i12 = 1;
                    } else if (i4 == 2 && cArr[i5] == 1617) {
                        i12 = 1;
                    } else {
                        i12 = 0;
                    }
                }
                if (b2 == 2) {
                    if (i4 == 2 && cArr[i5] != 1617) {
                        cArr[i5] = 65534;
                        z4 = true;
                    } else {
                        cArr[i5] = (char) (65136 + e[cArr[i5] - 1611] + i12);
                    }
                } else {
                    cArr[i5] = (char) (65136 + (c >> 8) + i12);
                }
            }
            if ((c & 4) == 0) {
                i7 = i8;
                i8 = c;
                i9 = i5;
            }
            i5--;
            if (i5 == i10) {
                c = i6;
                i10 = -2;
            } else if (i5 != -1) {
                c = c(cArr[i5]);
            }
        }
        int i13 = i3;
        if (z || z4) {
            i13 = e(cArr, 0, i3);
        }
        if (z2 || z3) {
            i13 = a(cArr, 0, i13, f2, 0);
        }
        return i13;
    }

    private int h(char[] cArr, int i2, int i3) {
        int i4;
        int g2 = g(cArr, 0, i3);
        if (g2 != 0) {
            i4 = a(cArr, 0, i3, g2, 1);
        } else {
            i4 = i3;
        }
        return i4;
    }

    private int b(char[] cArr, int i2, int i3, char[] cArr2, int i4, int i5) {
        if (i3 == 0) {
            return 0;
        }
        if (i5 == 0) {
            if ((this.f845a & 24) != 0 && (this.f845a & 65539) == 0) {
                return d(cArr, i2, i3);
            }
            return i3;
        }
        char[] cArr3 = new char[i3 << 1];
        System.arraycopy(cArr, i2, cArr3, 0, i3);
        if (this.f846b) {
            a(cArr3, 0, i3);
        }
        int i6 = i3;
        switch (this.f845a & 24) {
            case 8:
                if ((this.f845a & 917504) != 0 && (this.f845a & 917504) != 786432) {
                    i6 = d(cArr3, 0, i3, 2);
                    break;
                } else {
                    i6 = d(cArr3, 0, i3, 0);
                    if ((this.f845a & 917504) == 786432) {
                        i6 = a(cArr3, i3);
                        break;
                    }
                }
                break;
            case 16:
                i6 = h(cArr3, 0, i3);
                break;
            case 24:
                i6 = d(cArr3, 0, i3, 1);
                break;
        }
        if (i6 > i5) {
            throw new b("not enough room for result data");
        }
        if ((this.f845a & CGL.kCGLCPDispatchTableSize) != 0) {
            char c = '0';
            switch (this.f845a & 256) {
                case 0:
                    c = 1632;
                    break;
                case 256:
                    c = 1776;
                    break;
            }
            switch (this.f845a & CGL.kCGLCPDispatchTableSize) {
                case 32:
                    int i7 = c - '0';
                    for (int i8 = 0; i8 < i6; i8++) {
                        char c2 = cArr3[i8];
                        if (c2 <= '9' && c2 >= '0') {
                            int i9 = i8;
                            cArr3[i9] = (char) (cArr3[i9] + i7);
                        }
                    }
                    break;
                case 64:
                    char c3 = (char) (c + '\t');
                    int i10 = '0' - c;
                    for (int i11 = 0; i11 < i6; i11++) {
                        char c4 = cArr3[i11];
                        if (c4 <= c3 && c4 >= c) {
                            int i12 = i11;
                            cArr3[i12] = (char) (cArr3[i12] + i10);
                        }
                    }
                    break;
                case 96:
                    a(cArr3, 0, i6, c, false);
                    break;
                case 128:
                    a(cArr3, 0, i6, c, true);
                    break;
            }
        }
        if (this.f846b) {
            a(cArr3, 0, i6);
        }
        System.arraycopy(cArr3, 0, cArr2, i4, i6);
        return i6;
    }
}
