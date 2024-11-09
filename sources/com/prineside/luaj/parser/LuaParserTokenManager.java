package com.prineside.luaj.parser;

import java.io.IOException;
import java.io.PrintStream;
import org.lwjgl.system.linux.liburing.LibIOURing;

/* loaded from: infinitode-2.jar:com/prineside/luaj/parser/LuaParserTokenManager.class */
public class LuaParserTokenManager implements LuaParserConstants {
    public PrintStream debugStream;

    /* renamed from: a, reason: collision with root package name */
    private static long[] f1643a = {-2, -1, -1, -1};

    /* renamed from: b, reason: collision with root package name */
    private static long[] f1644b = {0, 0, -1, -1};
    private static int[] c = {62, 63, 65, 32, 49, 50, 51, 36, 37, 38, 26, 27, 29, 22, 36, 37, 38, 46, 36, 47, 37, 38, 49, 50, 51, 59, 49, 60, 50, 51, 20, 25, 23, 24, 33, 34, 39, 40, 45, 52, 53, 58, 0, 1, 3};
    public static final String[] jjstrLiteralImages = {"", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, "and", "break", "do", "else", "elseif", "end", "false", "for", "function", "goto", "if", "in", "local", "nil", "not", "or", "return", "repeat", "then", "true", "until", "while", null, null, null, null, null, null, null, null, null, null, null, null, null, null, "::", null, null, null, "#", ";", "=", ",", ".", ":", "(", ")", "[", "]", "...", "{", "}", "+", "-", "*", "/", "^", "%", "..", "<", "<=", ">", ">=", "==", "~="};
    public static final String[] lexStateNames = {"DEFAULT", "IN_COMMENT", "IN_LC0", "IN_LC1", "IN_LC2", "IN_LC3", "IN_LCN", "IN_LS0", "IN_LS1", "IN_LS2", "IN_LS3", "IN_LSN"};
    public static final int[] jjnewLexState = {-1, -1, -1, -1, -1, -1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
    private static long[] d = {6926536226618998785L, 2147483618};
    private static long[] e = {8257598, 0};
    private static long[] f = {8257536, 0};
    private SimpleCharStream g;
    private final int[] h;
    private final int[] i;
    private final StringBuffer j;
    private StringBuffer k;
    private int l;
    private char m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;

    public void setDebugStream(PrintStream printStream) {
        this.debugStream = printStream;
    }

    private int a(int i, int i2) {
        this.s = i2;
        this.r = i;
        return i + 1;
    }

    private int a() {
        switch (this.m) {
            case ']':
                return a(262144L);
            default:
                return 1;
        }
    }

    private int a(long j) {
        try {
            this.m = this.g.readChar();
            switch (this.m) {
                case ']':
                    if (262144 != 0) {
                        return a(1, 18);
                    }
                    return 2;
                default:
                    return 2;
            }
        } catch (IOException unused) {
            return 1;
        }
    }

    private int b() {
        return b(6, 0);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block (already processed)
        	at jadx.core.dex.visitors.regions.RegionMaker.calcSwitchOut(RegionMaker.java:923)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:797)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:157)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMaker.processLoop(RegionMaker.java:242)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:135)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeEndlessLoop(RegionMaker.java:411)
        	at jadx.core.dex.visitors.regions.RegionMaker.processLoop(RegionMaker.java:201)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:135)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    private int b(int r7, int r8) {
        /*
            Method dump skipped, instructions count: 374
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.luaj.parser.LuaParserTokenManager.b(int, int):int");
    }

    private int c() {
        switch (this.m) {
            case ']':
                return b(67108864L);
            default:
                return 1;
        }
    }

    private int b(long j) {
        try {
            this.m = this.g.readChar();
            switch (this.m) {
                case '=':
                    return a(67108864L, 67108864L);
                default:
                    return 2;
            }
        } catch (IOException unused) {
            return 1;
        }
    }

    private int a(long j, long j2) {
        long j3 = 67108864 & j;
        if (j3 == 0) {
            return 2;
        }
        try {
            this.m = this.g.readChar();
            switch (this.m) {
                case '=':
                    return b(j3, 67108864L);
                default:
                    return 3;
            }
        } catch (IOException unused) {
            return 2;
        }
    }

    private int b(long j, long j2) {
        long j3 = 67108864 & j;
        if (j3 == 0) {
            return 3;
        }
        try {
            this.m = this.g.readChar();
            switch (this.m) {
                case '=':
                    return c(j3, 67108864L);
                default:
                    return 4;
            }
        } catch (IOException unused) {
            return 3;
        }
    }

    private int c(long j, long j2) {
        long j3 = 67108864 & j;
        if (j3 == 0) {
            return 4;
        }
        try {
            this.m = this.g.readChar();
            switch (this.m) {
                case ']':
                    if ((j3 & 67108864) != 0) {
                        return a(4, 26);
                    }
                    return 5;
                default:
                    return 5;
            }
        } catch (IOException unused) {
            return 4;
        }
    }

    private int d() {
        switch (this.m) {
            case ']':
                return c(33554432L);
            default:
                return 1;
        }
    }

    private int c(long j) {
        try {
            this.m = this.g.readChar();
            switch (this.m) {
                case '=':
                    return d(33554432L, 33554432L);
                default:
                    return 2;
            }
        } catch (IOException unused) {
            return 1;
        }
    }

    private int d(long j, long j2) {
        long j3 = 33554432 & j;
        if (j3 == 0) {
            return 2;
        }
        try {
            this.m = this.g.readChar();
            switch (this.m) {
                case '=':
                    return e(j3, 33554432L);
                default:
                    return 3;
            }
        } catch (IOException unused) {
            return 2;
        }
    }

    private int e(long j, long j2) {
        long j3 = 33554432 & j;
        if (j3 == 0) {
            return 3;
        }
        try {
            this.m = this.g.readChar();
            switch (this.m) {
                case ']':
                    if ((j3 & 33554432) != 0) {
                        return a(3, 25);
                    }
                    return 4;
                default:
                    return 4;
            }
        } catch (IOException unused) {
            return 3;
        }
    }

    private int e() {
        switch (this.m) {
            case ']':
                return d(16777216L);
            default:
                return 1;
        }
    }

    private int d(long j) {
        try {
            this.m = this.g.readChar();
            switch (this.m) {
                case '=':
                    return f(16777216L, 16777216L);
                default:
                    return 2;
            }
        } catch (IOException unused) {
            return 1;
        }
    }

    private int f(long j, long j2) {
        long j3 = 16777216 & j;
        if (j3 == 0) {
            return 2;
        }
        try {
            this.m = this.g.readChar();
            switch (this.m) {
                case ']':
                    if ((j3 & 16777216) != 0) {
                        return a(2, 24);
                    }
                    return 3;
                default:
                    return 3;
            }
        } catch (IOException unused) {
            return 2;
        }
    }

    private int f() {
        switch (this.m) {
            case ']':
                return e(8388608L);
            default:
                return 1;
        }
    }

    private int e(long j) {
        try {
            this.m = this.g.readChar();
            switch (this.m) {
                case ']':
                    if (8388608 != 0) {
                        return a(1, 23);
                    }
                    return 2;
                default:
                    return 2;
            }
        } catch (IOException unused) {
            return 1;
        }
    }

    private final int a(int i, long j, long j2) {
        switch (i) {
            case 0:
                if ((j & 30720) != 0 || (j2 & 8192) != 0) {
                    return 14;
                }
                if ((j2 & 16810496) != 0) {
                    return 31;
                }
                if ((j & 2251799276814336L) != 0) {
                    this.s = 51;
                    return 17;
                }
                if ((j & 66496) != 0 || (j2 & 524288) != 0) {
                    return 7;
                }
                return -1;
            case 1:
                if ((j & 66496) != 0) {
                    return 6;
                }
                if ((j & 28672) != 0) {
                    return 13;
                }
                if ((j & 19243600969728L) != 0) {
                    return 17;
                }
                if ((j & 2232555675844608L) != 0) {
                    if (this.r != 1) {
                        this.s = 51;
                        this.r = 1;
                        return 17;
                    }
                    return 17;
                }
                return -1;
            case 2:
                if ((j & 2219275100094464L) != 0) {
                    this.s = 51;
                    this.r = 2;
                    return 17;
                }
                if ((j & 24576) != 0) {
                    return 12;
                }
                if ((j & 960) != 0) {
                    return 5;
                }
                if ((j & 13280575750144L) != 0) {
                    return 17;
                }
                return -1;
            case 3:
                if ((j & 896) != 0) {
                    return 4;
                }
                if ((j & 1796774872219648L) != 0) {
                    if (this.r != 3) {
                        this.s = 51;
                        this.r = 3;
                        return 17;
                    }
                    return 17;
                }
                if ((j & 422500227874816L) != 0) {
                    return 17;
                }
                if ((j & 16384) != 0) {
                    return 9;
                }
                return -1;
            case 4:
                if ((j & 105699145154560L) != 0) {
                    this.s = 51;
                    this.r = 4;
                    return 17;
                }
                if ((j & 768) != 0) {
                    return 3;
                }
                if ((j & 1691084316999680L) != 0) {
                    return 17;
                }
                return -1;
            case 5:
                if ((j & 512) != 0) {
                    return 0;
                }
                if ((j & 105561706201088L) != 0) {
                    return 17;
                }
                if ((j & 137438953472L) != 0) {
                    this.s = 51;
                    this.r = 5;
                    return 17;
                }
                return -1;
            case 6:
                if ((j & 137438953472L) != 0) {
                    this.s = 51;
                    this.r = 6;
                    return 17;
                }
                return -1;
            default:
                return -1;
        }
    }

    private final int b(int i, long j, long j2) {
        return c(a(i, j, j2), i + 1);
    }

    private int g() {
        switch (this.m) {
            case '#':
                return a(0, 69);
            case '$':
            case '&':
            case '\'':
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
            case '?':
            case '@':
            case 'A':
            case 'B':
            case 'C':
            case 'D':
            case 'E':
            case 'F':
            case 'G':
            case 'H':
            case 'I':
            case 'J':
            case 'K':
            case 'L':
            case 'M':
            case 'N':
            case 'O':
            case 'P':
            case 'Q':
            case 'R':
            case 'S':
            case 'T':
            case 'U':
            case 'V':
            case 'W':
            case 'X':
            case 'Y':
            case 'Z':
            case '\\':
            case '_':
            case '`':
            case 'c':
            case 'h':
            case 'j':
            case 'k':
            case 'm':
            case 'p':
            case 'q':
            case 's':
            case 'v':
            case 'x':
            case 'y':
            case 'z':
            case '|':
            default:
                return c(8, 0);
            case '%':
                return a(0, 87);
            case '(':
                return a(0, 75);
            case ')':
                return a(0, 76);
            case '*':
                return a(0, 84);
            case '+':
                return a(0, 82);
            case ',':
                return a(0, 72);
            case '-':
                this.s = 83;
                return g(66496L, 0L);
            case '.':
                this.s = 73;
                return g(0L, 16809984L);
            case '/':
                return a(0, 85);
            case ':':
                this.s = 74;
                return g(0L, 2L);
            case ';':
                return a(0, 70);
            case '<':
                this.s = 89;
                return g(0L, 67108864L);
            case '=':
                this.s = 71;
                return g(0L, 536870912L);
            case '>':
                this.s = 91;
                return g(0L, LibIOURing.IORING_OFF_SQES);
            case '[':
                this.s = 77;
                return g(30720L, 0L);
            case ']':
                return a(0, 78);
            case '^':
                return a(0, 86);
            case 'a':
                return g(536870912L, 0L);
            case 'b':
                return g(1073741824L, 0L);
            case 'd':
                return g(LibIOURing.IORING_OFF_PBUF_RING, 0L);
            case 'e':
                return g(30064771072L, 0L);
            case 'f':
                return g(240518168576L, 0L);
            case 'g':
                return g(274877906944L, 0L);
            case 'i':
                return g(1649267441664L, 0L);
            case 'l':
                return g(2199023255552L, 0L);
            case 'n':
                return g(13194139533312L, 0L);
            case 'o':
                return g(17592186044416L, 0L);
            case 'r':
                return g(105553116266496L, 0L);
            case 't':
                return g(422212465065984L, 0L);
            case 'u':
                return g(562949953421312L, 0L);
            case 'w':
                return g(1125899906842624L, 0L);
            case '{':
                return a(0, 80);
            case '}':
                return a(0, 81);
            case '~':
                return g(0L, 1073741824L);
        }
    }

    private int g(long j, long j2) {
        try {
            this.m = this.g.readChar();
            switch (this.m) {
                case '-':
                    if ((j & 65536) != 0) {
                        this.s = 16;
                        this.r = 1;
                    }
                    return a(j, 960L, j2, 0L);
                case '.':
                    if ((j2 & 16777216) != 0) {
                        this.s = 88;
                        this.r = 1;
                    }
                    return a(j, 0L, j2, 32768L);
                case ':':
                    if ((j2 & 2) != 0) {
                        return a(1, 65);
                    }
                    break;
                case '=':
                    if ((j2 & 67108864) != 0) {
                        return a(1, 90);
                    }
                    if ((j2 & LibIOURing.IORING_OFF_SQES) != 0) {
                        return a(1, 92);
                    }
                    if ((j2 & 536870912) != 0) {
                        return a(1, 93);
                    }
                    if ((j2 & 1073741824) != 0) {
                        return a(1, 94);
                    }
                    return a(j, 28672L, j2, 0L);
                case '[':
                    if ((j & 2048) != 0) {
                        return a(1, 11);
                    }
                    break;
                case 'a':
                    return a(j, 34359738368L, j2, 0L);
                case 'e':
                    return a(j, 105553116266496L, j2, 0L);
                case 'f':
                    if ((j & 549755813888L) != 0) {
                        return a(1, 39, 17);
                    }
                    break;
                case 'h':
                    return a(j, 1266637395197952L, j2, 0L);
                case 'i':
                    return a(j, 4398046511104L, j2, 0L);
                case 'l':
                    return a(j, 12884901888L, j2, 0L);
                case 'n':
                    if ((j & 1099511627776L) != 0) {
                        return a(1, 40, 17);
                    }
                    return a(j, 562967670161408L, j2, 0L);
                case 'o':
                    if ((j & LibIOURing.IORING_OFF_PBUF_RING) != 0) {
                        return a(1, 31, 17);
                    }
                    return a(j, 11338713661440L, j2, 0L);
                case 'r':
                    if ((j & 17592186044416L) != 0) {
                        return a(1, 44, 17);
                    }
                    return a(j, 281476050452480L, j2, 0L);
                case 'u':
                    return a(j, 137438953472L, j2, 0L);
            }
            return b(0, j, j2);
        } catch (IOException unused) {
            a(0, j, j2);
            return 1;
        }
    }

    private int a(long j, long j2, long j3, long j4) {
        long j5 = j2 & j;
        if ((j5 | (j4 & j3)) == 0) {
            return b(0, j, j3);
        }
        try {
            this.m = this.g.readChar();
            switch (this.m) {
                case '.':
                    if ((j5 & 32768) != 0) {
                        return a(2, 79);
                    }
                    break;
                case '=':
                    return b(j5, 24576L, j5, 0L);
                case '[':
                    if ((j5 & 4096) != 0) {
                        return a(2, 12);
                    }
                    return b(j5, 960L, j5, 0L);
                case 'c':
                    return b(j5, 2199023255552L, j5, 0L);
                case 'd':
                    if ((j5 & 536870912) != 0) {
                        return a(2, 29, 17);
                    }
                    if ((j5 & 17179869184L) != 0) {
                        return a(2, 34, 17);
                    }
                    break;
                case 'e':
                    return b(j5, 140738562097152L, j5, 0L);
                case 'i':
                    return b(j5, 1125899906842624L, j5, 0L);
                case 'l':
                    if ((j5 & 4398046511104L) != 0) {
                        return a(2, 42, 17);
                    }
                    return b(j5, 34359738368L, j5, 0L);
                case 'n':
                    return b(j5, 137438953472L, j5, 0L);
                case 'p':
                    return b(j5, 70368744177664L, j5, 0L);
                case 'r':
                    if ((j5 & 68719476736L) != 0) {
                        return a(2, 36, 17);
                    }
                    break;
                case 's':
                    return b(j5, 12884901888L, j5, 0L);
                case 't':
                    if ((j5 & 8796093022208L) != 0) {
                        return a(2, 43, 17);
                    }
                    return b(j5, 598409203417088L, j5, 0L);
                case 'u':
                    return b(j5, 281474976710656L, j5, 0L);
            }
            return b(1, j5, j5);
        } catch (IOException unused) {
            a(1, j5, j5);
            return 2;
        }
    }

    private int b(long j, long j2, long j3, long j4) {
        long j5 = j2 & j;
        if ((j5 | (0 & j3)) == 0) {
            return b(1, j, j3);
        }
        try {
            this.m = this.g.readChar();
            switch (this.m) {
                case '=':
                    return h(j5, 17280L);
                case '[':
                    if ((j5 & 64) != 0) {
                        return a(3, 6);
                    }
                    if ((j5 & 8192) != 0) {
                        return a(3, 13);
                    }
                    break;
                case 'a':
                    return h(j5, 2200096997376L);
                case 'c':
                    return h(j5, 137438953472L);
                case 'e':
                    if ((j5 & 4294967296L) != 0) {
                        this.s = 32;
                        this.r = 3;
                    } else if ((j5 & 281474976710656L) != 0) {
                        return a(3, 48, 17);
                    }
                    return h(j5, 70377334112256L);
                case 'i':
                    return h(j5, 562949953421312L);
                case 'l':
                    return h(j5, 1125899906842624L);
                case 'n':
                    if ((j5 & 140737488355328L) != 0) {
                        return a(3, 47, 17);
                    }
                    break;
                case 'o':
                    if ((j5 & 274877906944L) != 0) {
                        return a(3, 38, 17);
                    }
                    break;
                case 's':
                    return h(j5, 34359738368L);
                case 'u':
                    return h(j5, 35184372088832L);
            }
            return b(2, j5, 0L);
        } catch (IOException unused) {
            a(2, j5, 0L);
            return 3;
        }
    }

    private int h(long j, long j2) {
        long j3 = j2 & j;
        if (j3 == 0) {
            return b(2, j, 0L);
        }
        try {
            this.m = this.g.readChar();
            switch (this.m) {
                case '=':
                    return i(j3, 768L);
                case '[':
                    if ((j3 & 128) != 0) {
                        return a(4, 7);
                    }
                    if ((j3 & 16384) != 0) {
                        return a(4, 14);
                    }
                    break;
                case 'a':
                    return i(j3, 70368744177664L);
                case 'e':
                    if ((j3 & 34359738368L) != 0) {
                        return a(4, 35, 17);
                    }
                    if ((j3 & 1125899906842624L) != 0) {
                        return a(4, 50, 17);
                    }
                    break;
                case 'i':
                    return i(j3, 8589934592L);
                case 'k':
                    if ((j3 & 1073741824) != 0) {
                        return a(4, 30, 17);
                    }
                    break;
                case 'l':
                    if ((j3 & 2199023255552L) != 0) {
                        return a(4, 41, 17);
                    }
                    if ((j3 & 562949953421312L) != 0) {
                        return a(4, 49, 17);
                    }
                    break;
                case 'r':
                    return i(j3, 35184372088832L);
                case 't':
                    return i(j3, 137438953472L);
            }
            return b(3, j3, 0L);
        } catch (IOException unused) {
            a(3, j3, 0L);
            return 4;
        }
    }

    private int i(long j, long j2) {
        long j3 = j2 & j;
        if (j3 == 0) {
            return b(3, j, 0L);
        }
        try {
            this.m = this.g.readChar();
            switch (this.m) {
                case '=':
                    return j(j3, 512L);
                case '[':
                    if ((j3 & 256) != 0) {
                        return a(5, 8);
                    }
                    break;
                case 'f':
                    if ((j3 & 8589934592L) != 0) {
                        return a(5, 33, 17);
                    }
                    break;
                case 'i':
                    return j(j3, 137438953472L);
                case 'n':
                    if ((j3 & 35184372088832L) != 0) {
                        return a(5, 45, 17);
                    }
                    break;
                case 't':
                    if ((j3 & 70368744177664L) != 0) {
                        return a(5, 46, 17);
                    }
                    break;
            }
            return b(4, j3, 0L);
        } catch (IOException unused) {
            a(4, j3, 0L);
            return 5;
        }
    }

    private int j(long j, long j2) {
        long j3 = j2 & j;
        if (j3 == 0) {
            return b(4, j, 0L);
        }
        try {
            this.m = this.g.readChar();
            switch (this.m) {
                case '[':
                    if ((j3 & 512) != 0) {
                        return a(6, 9);
                    }
                    break;
                case 'o':
                    return k(j3, 137438953472L);
            }
            return b(5, j3, 0L);
        } catch (IOException unused) {
            a(5, j3, 0L);
            return 6;
        }
    }

    private int k(long j, long j2) {
        long j3 = 137438953472L & j;
        if (j3 == 0) {
            return b(5, j, 0L);
        }
        try {
            this.m = this.g.readChar();
            switch (this.m) {
                case 'n':
                    if ((j3 & 137438953472L) != 0) {
                        return a(7, 37, 17);
                    }
                    break;
            }
            return b(6, j3, 0L);
        } catch (IOException unused) {
            a(6, j3, 0L);
            return 7;
        }
    }

    private int a(int i, int i2, int i3) {
        this.s = i2;
        this.r = i;
        try {
            this.m = this.g.readChar();
            return c(17, i + 1);
        } catch (IOException unused) {
            return i + 1;
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block (already processed)
        	at jadx.core.dex.visitors.regions.RegionMaker.calcSwitchOut(RegionMaker.java:923)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:797)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:157)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMaker.processLoop(RegionMaker.java:242)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:135)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:735)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:152)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeEndlessLoop(RegionMaker.java:411)
        	at jadx.core.dex.visitors.regions.RegionMaker.processLoop(RegionMaker.java:201)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:135)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    private int c(int r9, int r10) {
        /*
            Method dump skipped, instructions count: 3058
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.luaj.parser.LuaParserTokenManager.c(int, int):int");
    }

    private int h() {
        return d(4, 0);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block (already processed)
        	at jadx.core.dex.visitors.regions.RegionMaker.calcSwitchOut(RegionMaker.java:923)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:797)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:157)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMaker.processLoop(RegionMaker.java:242)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:135)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:735)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:152)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeEndlessLoop(RegionMaker.java:411)
        	at jadx.core.dex.visitors.regions.RegionMaker.processLoop(RegionMaker.java:201)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:135)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    private int d(int r9, int r10) {
        /*
            Method dump skipped, instructions count: 543
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.luaj.parser.LuaParserTokenManager.d(int, int):int");
    }

    private int i() {
        return e(6, 0);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block (already processed)
        	at jadx.core.dex.visitors.regions.RegionMaker.calcSwitchOut(RegionMaker.java:923)
        	at jadx.core.dex.visitors.regions.RegionMaker.processSwitch(RegionMaker.java:797)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:157)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMaker.processLoop(RegionMaker.java:242)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:135)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeEndlessLoop(RegionMaker.java:411)
        	at jadx.core.dex.visitors.regions.RegionMaker.processLoop(RegionMaker.java:201)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:135)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:91)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    private int e(int r7, int r8) {
        /*
            Method dump skipped, instructions count: 374
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.luaj.parser.LuaParserTokenManager.e(int, int):int");
    }

    private int j() {
        switch (this.m) {
            case ']':
                return f(2097152L);
            default:
                return 1;
        }
    }

    private int f(long j) {
        try {
            this.m = this.g.readChar();
            switch (this.m) {
                case '=':
                    return l(2097152L, 2097152L);
                default:
                    return 2;
            }
        } catch (IOException unused) {
            return 1;
        }
    }

    private int l(long j, long j2) {
        long j3 = 2097152 & j;
        if (j3 == 0) {
            return 2;
        }
        try {
            this.m = this.g.readChar();
            switch (this.m) {
                case '=':
                    return m(j3, 2097152L);
                default:
                    return 3;
            }
        } catch (IOException unused) {
            return 2;
        }
    }

    private int m(long j, long j2) {
        long j3 = 2097152 & j;
        if (j3 == 0) {
            return 3;
        }
        try {
            this.m = this.g.readChar();
            switch (this.m) {
                case '=':
                    return n(j3, 2097152L);
                default:
                    return 4;
            }
        } catch (IOException unused) {
            return 3;
        }
    }

    private int n(long j, long j2) {
        long j3 = 2097152 & j;
        if (j3 == 0) {
            return 4;
        }
        try {
            this.m = this.g.readChar();
            switch (this.m) {
                case ']':
                    if ((j3 & 2097152) != 0) {
                        return a(4, 21);
                    }
                    return 5;
                default:
                    return 5;
            }
        } catch (IOException unused) {
            return 4;
        }
    }

    private int k() {
        switch (this.m) {
            case ']':
                return g(1048576L);
            default:
                return 1;
        }
    }

    private int g(long j) {
        try {
            this.m = this.g.readChar();
            switch (this.m) {
                case '=':
                    return o(1048576L, 1048576L);
                default:
                    return 2;
            }
        } catch (IOException unused) {
            return 1;
        }
    }

    private int o(long j, long j2) {
        long j3 = 1048576 & j;
        if (j3 == 0) {
            return 2;
        }
        try {
            this.m = this.g.readChar();
            switch (this.m) {
                case '=':
                    return p(j3, 1048576L);
                default:
                    return 3;
            }
        } catch (IOException unused) {
            return 2;
        }
    }

    private int p(long j, long j2) {
        long j3 = 1048576 & j;
        if (j3 == 0) {
            return 3;
        }
        try {
            this.m = this.g.readChar();
            switch (this.m) {
                case ']':
                    if ((j3 & 1048576) != 0) {
                        return a(3, 20);
                    }
                    return 4;
                default:
                    return 4;
            }
        } catch (IOException unused) {
            return 3;
        }
    }

    private int l() {
        switch (this.m) {
            case ']':
                return h(524288L);
            default:
                return 1;
        }
    }

    private int h(long j) {
        try {
            this.m = this.g.readChar();
            switch (this.m) {
                case '=':
                    return q(524288L, 524288L);
                default:
                    return 2;
            }
        } catch (IOException unused) {
            return 1;
        }
    }

    private int q(long j, long j2) {
        long j3 = 524288 & j;
        if (j3 == 0) {
            return 2;
        }
        try {
            this.m = this.g.readChar();
            switch (this.m) {
                case ']':
                    if ((j3 & 524288) != 0) {
                        return a(2, 19);
                    }
                    return 3;
                default:
                    return 3;
            }
        } catch (IOException unused) {
            return 2;
        }
    }

    private static final boolean a(int i, int i2, int i3, long j, long j2) {
        switch (i) {
            case 0:
                return (f1644b[i3] & j2) != 0;
            default:
                if ((f1643a[i2] & j) != 0) {
                    return true;
                }
                return false;
        }
    }

    public LuaParserTokenManager(SimpleCharStream simpleCharStream) {
        this.debugStream = System.out;
        this.h = new int[66];
        this.i = new int[132];
        this.j = new StringBuffer();
        this.k = this.j;
        this.n = 0;
        this.o = 0;
        this.g = simpleCharStream;
    }

    public LuaParserTokenManager(SimpleCharStream simpleCharStream, int i) {
        this(simpleCharStream);
        SwitchTo(i);
    }

    public void ReInit(SimpleCharStream simpleCharStream) {
        this.p = 0;
        this.r = 0;
        this.n = 0;
        this.g = simpleCharStream;
        m();
    }

    private void m() {
        this.q = -2147483647;
        int i = 66;
        while (true) {
            int i2 = i;
            i--;
            if (i2 > 0) {
                this.h[i] = Integer.MIN_VALUE;
            } else {
                return;
            }
        }
    }

    public void ReInit(SimpleCharStream simpleCharStream, int i) {
        ReInit(simpleCharStream);
        SwitchTo(i);
    }

    public void SwitchTo(int i) {
        if (i >= 12 || i < 0) {
            throw new TokenMgrError("Error: Ignoring invalid lexical state : " + i + ". State unchanged.", 2);
        }
        this.n = i;
    }

    private Token n() {
        String GetImage;
        int beginLine;
        int beginColumn;
        int endLine;
        int endColumn;
        if (this.r < 0) {
            if (this.k == null) {
                GetImage = "";
            } else {
                GetImage = this.k.toString();
            }
            int beginLine2 = this.g.getBeginLine();
            endLine = beginLine2;
            beginLine = beginLine2;
            int beginColumn2 = this.g.getBeginColumn();
            endColumn = beginColumn2;
            beginColumn = beginColumn2;
        } else {
            String str = jjstrLiteralImages[this.s];
            GetImage = str == null ? this.g.GetImage() : str;
            beginLine = this.g.getBeginLine();
            beginColumn = this.g.getBeginColumn();
            endLine = this.g.getEndLine();
            endColumn = this.g.getEndColumn();
        }
        Token newToken = Token.newToken(this.s, GetImage);
        newToken.beginLine = beginLine;
        newToken.endLine = endLine;
        newToken.beginColumn = beginColumn;
        newToken.endColumn = endColumn;
        return newToken;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:6:0x003d. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0375 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0277  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.prineside.luaj.parser.Token getNextToken() {
        /*
            Method dump skipped, instructions count: 1033
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.luaj.parser.LuaParserTokenManager.getNextToken():com.prineside.luaj.parser.Token");
    }

    private void a(int i) {
        if (this.h[i] != this.q) {
            int[] iArr = this.i;
            int i2 = this.p;
            this.p = i2 + 1;
            iArr[i2] = i;
            this.h[i] = this.q;
        }
    }

    private void f(int i, int i2) {
        int i3;
        do {
            int[] iArr = this.i;
            int i4 = this.p;
            this.p = i4 + 1;
            iArr[i4] = c[i];
            i3 = i;
            i++;
        } while (i3 != i2);
    }

    private void g(int i, int i2) {
        a(i);
        a(i2);
    }

    private void h(int i, int i2) {
        int i3;
        do {
            a(c[i]);
            i3 = i;
            i++;
        } while (i3 != i2);
    }
}
