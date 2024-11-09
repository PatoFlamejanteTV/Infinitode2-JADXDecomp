package com.b.a.c;

import com.a.a.a.am;
import org.lwjgl.system.linux.FCNTL;

/* loaded from: infinitode-2.jar:com/b/a/c/d.class */
/* synthetic */ class d {
    private static boolean a(int i) {
        return ((1 << i) & FCNTL.S_IRWXU) != 0;
    }

    private static String b(String str, int i) {
        switch (i & 10) {
            case 0:
                return str;
            case 2:
                StringBuffer stringBuffer = new StringBuffer(str.length());
                int i2 = 0;
                do {
                    int a2 = am.a(str, i2);
                    i2 += am.a(a2);
                    am.a(stringBuffer, com.b.a.b.a.c(a2));
                } while (i2 < str.length());
                return stringBuffer.toString();
            case 8:
                StringBuilder sb = new StringBuilder(str.length());
                int i3 = 0;
                do {
                    int i4 = i3;
                    i3++;
                    char charAt = str.charAt(i4);
                    if (!c.a(charAt)) {
                        sb.append(charAt);
                    }
                } while (i3 < str.length());
                return sb.toString();
            default:
                StringBuffer stringBuffer2 = new StringBuffer(str.length());
                int i5 = 0;
                do {
                    int a3 = am.a(str, i5);
                    i5 += am.a(a3);
                    if (!c.a(a3)) {
                        am.a(stringBuffer2, com.b.a.b.a.c(a3));
                    }
                } while (i5 < str.length());
                return stringBuffer2.toString();
        }
    }

    private static String a(char[] cArr, int i, int i2, int i3) {
        return b(new String(cArr, i, i2 - i), i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(String str, int i) {
        int a2;
        StringBuffer stringBuffer = new StringBuffer(str.length());
        switch (i & 11) {
            case 0:
                int length = str.length();
                do {
                    int i2 = length;
                    length -= am.a(am.a(str, length - 1));
                    stringBuffer.append(str.substring(length, i2));
                } while (length > 0);
            case 1:
                int length2 = str.length();
                do {
                    int i3 = length2;
                    do {
                        a2 = am.a(str, length2 - 1);
                        int a3 = length2 - am.a(a2);
                        length2 = a3;
                        if (a3 > 0) {
                        }
                        stringBuffer.append(str.substring(length2, i3));
                    } while (a(com.b.a.b.a.a(a2)));
                    stringBuffer.append(str.substring(length2, i3));
                } while (length2 > 0);
            default:
                int length3 = str.length();
                do {
                    int i4 = length3;
                    int a4 = am.a(str, length3 - 1);
                    length3 -= am.a(a4);
                    if ((i & 1) != 0) {
                        while (length3 > 0 && a(com.b.a.b.a.a(a4))) {
                            a4 = am.a(str, length3 - 1);
                            length3 -= am.a(a4);
                        }
                    }
                    if ((i & 8) == 0 || !c.a(a4)) {
                        int i5 = length3;
                        if ((i & 2) != 0) {
                            int c = com.b.a.b.a.c(a4);
                            am.a(stringBuffer, c);
                            i5 += am.a(c);
                        }
                        stringBuffer.append(str.substring(i5, i4));
                    }
                } while (length3 > 0);
                break;
        }
        return stringBuffer.toString();
    }

    private static String b(char[] cArr, int i, int i2, int i3) {
        return a(new String(cArr, i, i2 - i), i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(c cVar, int i) {
        char c;
        char c2;
        char c3;
        char c4;
        char[] cArr = cVar.f847a;
        int b2 = cVar.b();
        if ((cVar.g & 1) != 0) {
            i = (i | 4) & (-9);
        }
        if ((cVar.g & 2) != 0) {
            i = (i | 8) & (-5);
        }
        if (cVar.f != 4 && cVar.f != 5 && cVar.f != 6 && cVar.f != 3) {
            i &= -5;
        }
        StringBuilder sb = new StringBuilder((i & 4) != 0 ? cVar.f848b << 1 : cVar.f848b);
        if ((i & 16) == 0) {
            if ((i & 4) == 0) {
                for (int i2 = 0; i2 < b2; i2++) {
                    f c5 = cVar.c(i2);
                    if (c5.c()) {
                        sb.append(a(cArr, c5.f865a, c5.f866b, i & (-3)));
                    } else {
                        sb.append(b(cArr, c5.f865a, c5.f866b, i));
                    }
                }
            } else {
                byte[] bArr = cVar.d;
                for (int i3 = 0; i3 < b2; i3++) {
                    f c6 = cVar.c(i3);
                    int i4 = cVar.m[i3].c;
                    int i5 = i4;
                    if (i4 < 0) {
                        i5 = 0;
                    }
                    if (c6.c()) {
                        if (cVar.a() && bArr[c6.f865a] != 0) {
                            i5 |= 1;
                        }
                        if ((i5 & 1) != 0) {
                            c3 = 8206;
                        } else if ((i5 & 4) != 0) {
                            c3 = 8207;
                        } else {
                            c3 = 0;
                        }
                        if (c3 != 0) {
                            sb.append(c3);
                        }
                        sb.append(a(cArr, c6.f865a, c6.f866b, i & (-3)));
                        if (cVar.a() && bArr[c6.f866b - 1] != 0) {
                            i5 |= 2;
                        }
                        if ((i5 & 2) != 0) {
                            c4 = 8206;
                        } else if ((i5 & 8) != 0) {
                            c4 = 8207;
                        } else {
                            c4 = 0;
                        }
                        if (c4 != 0) {
                            sb.append(c4);
                        }
                    } else {
                        if (cVar.a() && !cVar.a(8194, c6.f866b - 1)) {
                            i5 |= 4;
                        }
                        if ((i5 & 1) != 0) {
                            c = 8206;
                        } else if ((i5 & 4) != 0) {
                            c = 8207;
                        } else {
                            c = 0;
                        }
                        if (c != 0) {
                            sb.append(c);
                        }
                        sb.append(b(cArr, c6.f865a, c6.f866b, i));
                        if (cVar.a() && (8194 & c.a(bArr[c6.f865a])) == 0) {
                            i5 |= 8;
                        }
                        if ((i5 & 2) != 0) {
                            c2 = 8206;
                        } else if ((i5 & 8) != 0) {
                            c2 = 8207;
                        } else {
                            c2 = 0;
                        }
                        if (c2 != 0) {
                            sb.append(c2);
                        }
                    }
                }
            }
        } else if ((i & 4) == 0) {
            int i6 = b2;
            while (true) {
                i6--;
                if (i6 < 0) {
                    break;
                }
                f c7 = cVar.c(i6);
                if (c7.c()) {
                    sb.append(b(cArr, c7.f865a, c7.f866b, i & (-3)));
                } else {
                    sb.append(a(cArr, c7.f865a, c7.f866b, i));
                }
            }
        } else {
            byte[] bArr2 = cVar.d;
            int i7 = b2;
            while (true) {
                i7--;
                if (i7 < 0) {
                    break;
                }
                f c8 = cVar.c(i7);
                if (c8.c()) {
                    if (bArr2[c8.f866b - 1] != 0) {
                        sb.append((char) 8206);
                    }
                    sb.append(b(cArr, c8.f865a, c8.f866b, i & (-3)));
                    if (bArr2[c8.f865a] != 0) {
                        sb.append((char) 8206);
                    }
                } else {
                    if ((8194 & c.a(bArr2[c8.f865a])) == 0) {
                        sb.append((char) 8207);
                    }
                    sb.append(a(cArr, c8.f865a, c8.f866b, i));
                    if ((8194 & c.a(bArr2[c8.f866b - 1])) == 0) {
                        sb.append((char) 8207);
                    }
                }
            }
        }
        return sb.toString();
    }
}
