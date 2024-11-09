package com.prineside.luaj.compiler;

import com.prineside.luaj.LocVars;
import com.prineside.luaj.Lua;
import com.prineside.luaj.LuaError;
import com.prineside.luaj.LuaNumber;
import com.prineside.luaj.LuaString;
import com.prineside.luaj.LuaValue;
import com.prineside.luaj.Prototype;
import com.prineside.luaj.compiler.FuncState;
import com.prineside.luaj.compiler.LuaC;
import com.prineside.luaj.lib.MathLib;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:com/prineside/luaj/compiler/LexState.class */
public class LexState extends Constants {
    private static String[] e = {"(for control)", "(for generator)", "(for index)", "(for limit)", "(for state)", "(for step)"};
    private static final Hashtable f = new Hashtable();
    private int g;
    private int h;

    /* renamed from: a, reason: collision with root package name */
    int f1535a;

    /* renamed from: b, reason: collision with root package name */
    FuncState f1536b;
    LuaC.CompileState c;
    private InputStream k;
    private int m;
    private LuaString n;
    private LuaString o;
    private static String[] p;
    private static Hashtable q;
    private static Priority[] r;
    private Token i = new Token(0);
    private Token j = new Token(0);
    Dyndata d = new Dyndata();
    private char[] l = new char[32];

    static {
        for (int i = 0; i < e.length; i++) {
            f.put(e[i], Boolean.TRUE);
        }
        p = new String[]{"and", "break", "do", "else", "elseif", "end", "false", "for", "function", "goto", "if", "in", "local", "nil", "not", "or", "repeat", "return", "then", "true", "until", "while", "..", "...", "==", ">=", "<=", "~=", "::", "<eos>", "<number>", "<name>", "<string>", "<eof>"};
        q = new Hashtable();
        for (int i2 = 0; i2 < 22; i2++) {
            q.put(LuaValue.valueOf(p[i2]), Integer.valueOf(i2 + 257));
        }
        r = new Priority[]{new Priority(6, 6), new Priority(6, 6), new Priority(7, 7), new Priority(7, 7), new Priority(7, 7), new Priority(10, 9), new Priority(5, 4), new Priority(3, 3), new Priority(3, 3), new Priority(3, 3), new Priority(3, 3), new Priority(3, 3), new Priority(3, 3), new Priority(2, 2), new Priority(1, 1)};
    }

    private static final String c(String str) {
        return "'" + str + "'";
    }

    private static final String a(Object obj) {
        return c(String.valueOf(obj));
    }

    public static boolean isReservedKeyword(String str) {
        return f.containsKey(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/luaj/compiler/LexState$SemInfo.class */
    public static class SemInfo {

        /* renamed from: a, reason: collision with root package name */
        LuaValue f1547a;

        /* renamed from: b, reason: collision with root package name */
        LuaString f1548b;

        private SemInfo() {
        }

        /* synthetic */ SemInfo(byte b2) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/luaj/compiler/LexState$Token.class */
    public static class Token {

        /* renamed from: a, reason: collision with root package name */
        int f1549a;

        /* renamed from: b, reason: collision with root package name */
        final SemInfo f1550b;

        private Token() {
            this.f1550b = new SemInfo((byte) 0);
        }

        /* synthetic */ Token(byte b2) {
            this();
        }

        public void set(Token token) {
            this.f1549a = token.f1549a;
            this.f1550b.f1547a = token.f1550b.f1547a;
            this.f1550b.f1548b = token.f1550b.f1548b;
        }
    }

    private static boolean b(int i) {
        if (i >= 48 && i <= 57) {
            return true;
        }
        if (i < 97 || i > 122) {
            return (i >= 65 && i <= 90) || i == 95;
        }
        return true;
    }

    private static boolean c(int i) {
        if (i < 97 || i > 122) {
            return i >= 65 && i <= 90;
        }
        return true;
    }

    private static boolean d(int i) {
        return i >= 48 && i <= 57;
    }

    private static boolean e(int i) {
        if (i >= 48 && i <= 57) {
            return true;
        }
        if (i < 97 || i > 102) {
            return i >= 65 && i <= 70;
        }
        return true;
    }

    private static boolean f(int i) {
        return i >= 0 && i <= 32;
    }

    public LexState(LuaC.CompileState compileState, InputStream inputStream) {
        this.k = inputStream;
        this.c = compileState;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void b() {
        try {
            this.g = this.k.read();
        } catch (IOException e2) {
            printStackTrace();
            this.g = -1;
        }
    }

    private boolean c() {
        return this.g == 10 || this.g == 13;
    }

    private void d() {
        g(this.g);
        b();
    }

    private void g(int i) {
        if (this.l == null || this.m + 1 > this.l.length) {
            this.l = a(this.l, (this.m << 1) + 1);
        }
        char[] cArr = this.l;
        int i2 = this.m;
        this.m = i2 + 1;
        cArr[i2] = (char) i;
    }

    private String h(int i) {
        if (i < 257) {
            if (i(i)) {
                return this.c.pushfstring("char(" + i + ")");
            }
            return this.c.pushfstring(String.valueOf((char) i));
        }
        return p[i - 257];
    }

    private static boolean i(int i) {
        return i < 32;
    }

    private String j(int i) {
        switch (i) {
            case User32.WM_MENUSELECT /* 287 */:
            case User32.WM_MENUCHAR /* 288 */:
            case User32.WM_ENTERIDLE /* 289 */:
                return new String(this.l, 0, this.m);
            default:
                return h(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(String str, int i) {
        String chunkid = Lua.chunkid(this.n.tojstring());
        this.c.pushfstring(chunkid + ":" + this.h + ": " + str);
        if (i != 0) {
            this.c.pushfstring("syntax error: " + str + " near " + j(i));
        }
        throw new LuaError(chunkid + ":" + this.h + ": " + str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(String str) {
        a(str, this.i.f1549a);
    }

    private LuaString d(String str) {
        return this.c.newTString(str);
    }

    private LuaString a(char[] cArr, int i, int i2) {
        return this.c.newTString(new String(cArr, 0, i2));
    }

    private void e() {
        int i = this.g;
        a(c());
        b();
        if (c() && this.g != i) {
            b();
        }
        int i2 = this.h + 1;
        this.h = i2;
        if (i2 >= 2147483645) {
            a("chunk has too many lines");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(LuaC.CompileState compileState, int i, InputStream inputStream, LuaString luaString) {
        this.c = compileState;
        this.j.f1549a = 286;
        this.k = inputStream;
        this.f1536b = null;
        this.h = 1;
        this.f1535a = 1;
        this.n = luaString;
        this.o = LuaValue.ENV;
        this.m = 0;
        this.g = i;
        f();
    }

    private void f() {
        if (this.g == 35) {
            while (!c() && this.g != -1) {
                b();
            }
        }
    }

    private boolean e(String str) {
        if (str.indexOf(this.g) < 0) {
            return false;
        }
        d();
        return true;
    }

    private LuaValue f(String str) {
        char[] charArray = str.toCharArray();
        int i = 0;
        while (i < charArray.length && f(charArray[i])) {
            i++;
        }
        double d = 1.0d;
        if (i < charArray.length && charArray[i] == '-') {
            d = -1.0d;
            i++;
        }
        if (i + 2 >= charArray.length) {
            return LuaValue.ZERO;
        }
        int i2 = i;
        int i3 = i + 1;
        if (charArray[i2] != '0') {
            return LuaValue.ZERO;
        }
        if (charArray[i3] != 'x' && charArray[i3] != 'X') {
            return LuaValue.ZERO;
        }
        int i4 = i3 + 1;
        double d2 = 0.0d;
        int i5 = 0;
        while (i4 < charArray.length && e(charArray[i4])) {
            int i6 = i4;
            i4++;
            d2 = (d2 * 16.0d) + k(charArray[i6]);
        }
        if (i4 < charArray.length && charArray[i4] == '.') {
            i4++;
            while (i4 < charArray.length && e(charArray[i4])) {
                int i7 = i4;
                i4++;
                d2 = (d2 * 16.0d) + k(charArray[i7]);
                i5 -= 4;
            }
        }
        if (i4 < charArray.length && (charArray[i4] == 'p' || charArray[i4] == 'P')) {
            int i8 = i4 + 1;
            int i9 = 0;
            boolean z = false;
            if (i8 < charArray.length && charArray[i8] == '-') {
                z = true;
                i8++;
            }
            while (i8 < charArray.length && d(charArray[i8])) {
                int i10 = i8;
                i8++;
                i9 = ((i9 * 10) + charArray[i10]) - 48;
            }
            if (z) {
                i9 = -i9;
            }
            i5 += i9;
        }
        return LuaValue.valueOf(d * d2 * MathLib.dpow_d(2.0d, i5));
    }

    private boolean a(String str, SemInfo semInfo) {
        if (str.indexOf(110) >= 0 || str.indexOf(78) >= 0) {
            semInfo.f1547a = LuaValue.ZERO;
            return true;
        }
        if (str.indexOf(120) >= 0 || str.indexOf(88) >= 0) {
            semInfo.f1547a = f(str);
            return true;
        }
        try {
            semInfo.f1547a = LuaValue.valueOf(Double.parseDouble(str.trim()));
            return true;
        } catch (NumberFormatException e2) {
            a("malformed number (" + e2.getMessage() + ")", User32.WM_MENUSELECT);
            return true;
        }
    }

    private void a(SemInfo semInfo) {
        String str = "Ee";
        int i = this.g;
        a(d(this.g));
        d();
        if (i == 48 && e("Xx")) {
            str = "Pp";
        }
        while (true) {
            if (e(str)) {
                e("+-");
            }
            if (e(this.g) || this.g == 46) {
                d();
            } else {
                a(new String(this.l, 0, this.m), semInfo);
                return;
            }
        }
    }

    private int g() {
        int i = 0;
        int i2 = this.g;
        a(i2 == 91 || i2 == 93);
        d();
        while (this.g == 61) {
            d();
            i++;
        }
        return this.g == i2 ? i : (-i) - 1;
    }

    private void a(SemInfo semInfo, int i) {
        d();
        if (c()) {
            e();
        }
        boolean z = false;
        while (!z) {
            switch (this.g) {
                case -1:
                    a(semInfo != null ? "unfinished long string" : "unfinished long comment", 286);
                    break;
                case 10:
                case 13:
                    g(10);
                    e();
                    if (semInfo != null) {
                        break;
                    } else {
                        this.m = 0;
                        break;
                    }
                case 91:
                    if (g() != i) {
                        break;
                    } else {
                        d();
                        if (i != 0) {
                            break;
                        } else {
                            a("nesting of [[...]] is deprecated", 91);
                            break;
                        }
                    }
                case 93:
                    if (g() != i) {
                        break;
                    } else {
                        d();
                        z = true;
                        break;
                    }
                default:
                    if (semInfo != null) {
                        d();
                        break;
                    } else {
                        b();
                        break;
                    }
            }
        }
        if (semInfo != null) {
            semInfo.f1548b = this.c.newTString(LuaString.valueOf(this.l, i + 2, this.m - (2 * (i + 2))));
        }
    }

    private static int k(int i) {
        return i <= 57 ? i - 48 : i <= 70 ? (i + 10) - 65 : (i + 10) - 97;
    }

    private int h() {
        b();
        int i = this.g;
        b();
        int i2 = this.g;
        if (!e(i) || !e(i2)) {
            a("hexadecimal digit expected 'x" + ((char) i) + ((char) i2), User32.WM_ENTERIDLE);
        }
        return (k(i) << 4) + k(i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:60:0x016e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(int r8, com.prineside.luaj.compiler.LexState.SemInfo r9) {
        /*
            Method dump skipped, instructions count: 432
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.luaj.compiler.LexState.a(int, com.prineside.luaj.compiler.LexState$SemInfo):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x016d, code lost:            b();     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0177, code lost:            if (r5.g == 61) goto L34;     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x017a, code lost:            return 61;     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x017d, code lost:            b();     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0184, code lost:            return 281;     */
    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x0009. Please report as an issue. */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int b(com.prineside.luaj.compiler.LexState.SemInfo r6) {
        /*
            Method dump skipped, instructions count: 654
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.luaj.compiler.LexState.b(com.prineside.luaj.compiler.LexState$SemInfo):int");
    }

    private void i() {
        this.f1535a = this.h;
        if (this.j.f1549a != 286) {
            this.i.set(this.j);
            this.j.f1549a = 286;
        } else {
            this.i.f1549a = b(this.i.f1550b);
        }
    }

    private void j() {
        a(this.j.f1549a == 286);
        this.j.f1549a = b(this.j.f1550b);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/prineside/luaj/compiler/LexState$expdesc.class */
    public static class expdesc {

        /* renamed from: a, reason: collision with root package name */
        int f1552a;

        /* renamed from: b, reason: collision with root package name */
        final U f1553b = new U();
        final IntPtr c = new IntPtr();
        final IntPtr d = new IntPtr();

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: infinitode-2.jar:com/prineside/luaj/compiler/LexState$expdesc$U.class */
        public static class U {

            /* renamed from: a, reason: collision with root package name */
            short f1554a;

            /* renamed from: b, reason: collision with root package name */
            short f1555b;
            short c;
            private LuaValue e;
            int d;

            U() {
            }

            public void setNval(LuaValue luaValue) {
                this.e = luaValue;
            }

            public LuaValue nval() {
                return this.e == null ? LuaNumber.valueOf(this.d) : this.e;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final void a(int i, int i2) {
            this.d.f1534a = -1;
            this.c.f1534a = -1;
            this.f1552a = i;
            this.f1553b.d = i2;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final boolean a() {
            return this.c.f1534a != this.d.f1534a;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final boolean b() {
            return this.f1552a == 5 && this.c.f1534a == -1 && this.d.f1534a == -1;
        }

        public void setvalue(expdesc expdescVar) {
            this.d.f1534a = expdescVar.d.f1534a;
            this.f1552a = expdescVar.f1552a;
            this.c.f1534a = expdescVar.c.f1534a;
            this.f1553b.e = expdescVar.f1553b.e;
            this.f1553b.f1554a = expdescVar.f1553b.f1554a;
            this.f1553b.f1555b = expdescVar.f1553b.f1555b;
            this.f1553b.c = expdescVar.f1553b.c;
            this.f1553b.d = expdescVar.f1553b.d;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/prineside/luaj/compiler/LexState$Vardesc.class */
    public static class Vardesc {

        /* renamed from: a, reason: collision with root package name */
        final short f1551a;

        Vardesc(int i) {
            this.f1551a = (short) i;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/prineside/luaj/compiler/LexState$Labeldesc.class */
    public static class Labeldesc {

        /* renamed from: a, reason: collision with root package name */
        LuaString f1543a;

        /* renamed from: b, reason: collision with root package name */
        int f1544b;
        int c;
        short d;

        public Labeldesc(LuaString luaString, int i, int i2, short s) {
            this.f1543a = luaString;
            this.f1544b = i;
            this.c = i2;
            this.d = s;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/prineside/luaj/compiler/LexState$Dyndata.class */
    public static class Dyndata {

        /* renamed from: a, reason: collision with root package name */
        Vardesc[] f1539a;
        Labeldesc[] c;
        Labeldesc[] e;

        /* renamed from: b, reason: collision with root package name */
        int f1540b = 0;
        int d = 0;
        int f = 0;

        Dyndata() {
        }
    }

    private static boolean l(int i) {
        return i == 12 || i == 13;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void b(String str) {
        this.i.f1549a = 0;
        a(str);
    }

    private void m(int i) {
        a(this.c.pushfstring(c(h(i)) + " expected"));
    }

    private boolean n(int i) {
        if (this.i.f1549a == i) {
            i();
            return true;
        }
        return false;
    }

    private void o(int i) {
        if (this.i.f1549a != i) {
            m(i);
        }
    }

    private void p(int i) {
        o(i);
        i();
    }

    private void a(boolean z, String str) {
        if (!z) {
            a(str);
        }
    }

    private void b(int i, int i2, int i3) {
        if (!n(i)) {
            if (i3 == this.h) {
                m(i);
            } else {
                a(this.c.pushfstring(c(h(i)) + " expected (to close " + c(h(i2)) + " at line " + i3 + ")"));
            }
        }
    }

    private LuaString k() {
        o(User32.WM_MENUCHAR);
        LuaString luaString = this.i.f1550b.f1548b;
        i();
        return luaString;
    }

    private void a(expdesc expdescVar, LuaString luaString) {
        expdescVar.a(4, this.f1536b.a(luaString));
    }

    private void a(expdesc expdescVar) {
        a(expdescVar, k());
    }

    private int a(LuaString luaString) {
        FuncState funcState = this.f1536b;
        Prototype prototype = funcState.f1528a;
        if (prototype.locvars == null || funcState.k + 1 > prototype.locvars.length) {
            prototype.locvars = a(prototype.locvars, (funcState.k << 1) + 1);
        }
        prototype.locvars[funcState.k] = new LocVars(luaString, 0, 0);
        short s = funcState.k;
        funcState.k = (short) (s + 1);
        return s;
    }

    private void b(LuaString luaString) {
        int a2 = a(luaString);
        this.f1536b.a(this.d.f1540b + 1, 200, "local variables");
        if (this.d.f1539a == null || this.d.f1540b + 1 > this.d.f1539a.length) {
            this.d.f1539a = a(this.d.f1539a, Math.max(1, this.d.f1540b << 1));
        }
        Vardesc[] vardescArr = this.d.f1539a;
        Dyndata dyndata = this.d;
        int i = dyndata.f1540b;
        dyndata.f1540b = i + 1;
        vardescArr[i] = new Vardesc(a2);
    }

    private void g(String str) {
        b(d(str));
    }

    private void q(int i) {
        FuncState funcState = this.f1536b;
        funcState.l = (short) (funcState.l + i);
        while (i > 0) {
            funcState.a(funcState.l - i).startpc = funcState.e;
            i--;
        }
    }

    private void b(expdesc expdescVar) {
        LuaString k = k();
        FuncState funcState = this.f1536b;
        if (FuncState.a(funcState, k, expdescVar, 1) == 0) {
            expdesc expdescVar2 = new expdesc();
            FuncState.a(funcState, this.o, expdescVar, 1);
            a(expdescVar.f1552a == 7 || expdescVar.f1552a == 8);
            a(expdescVar2, k);
            funcState.c(expdescVar, expdescVar2);
        }
    }

    private void a(int i, int i2, expdesc expdescVar) {
        FuncState funcState = this.f1536b;
        int i3 = i - i2;
        if (l(expdescVar.f1552a)) {
            int i4 = i3 + 1;
            if (i4 < 0) {
                i4 = 0;
            }
            funcState.a(expdescVar, i4);
            if (i4 > 1) {
                funcState.d(i4 - 1);
                return;
            }
            return;
        }
        if (expdescVar.f1552a != 0) {
            funcState.f(expdescVar);
        }
        if (i3 > 0) {
            short s = funcState.n;
            funcState.d(i3);
            funcState.b(s, i3);
        }
    }

    private void l() {
        LuaC.CompileState compileState = this.c;
        int i = compileState.f1556a + 1;
        compileState.f1556a = i;
        if (i > 200) {
            a("chunk has too many syntax levels", 0);
        }
    }

    private void m() {
        this.c.f1556a--;
    }

    private void a(int i, Labeldesc labeldesc) {
        FuncState funcState = this.f1536b;
        Labeldesc[] labeldescArr = this.d.c;
        Labeldesc labeldesc2 = labeldescArr[i];
        a(labeldesc2.f1543a.eq_b(labeldesc.f1543a));
        if (labeldesc2.d < labeldesc.d) {
            b(this.c.pushfstring("<goto " + labeldesc2.f1543a + "> at line " + labeldesc2.c + " jumps into the scope of local '" + funcState.a(labeldesc2.d).varname.tojstring() + "'"));
        }
        funcState.d(labeldesc2.f1544b, labeldesc.f1544b);
        System.arraycopy(labeldescArr, i + 1, labeldescArr, i, (this.d.d - i) - 1);
        Dyndata dyndata = this.d;
        int i2 = dyndata.d - 1;
        dyndata.d = i2;
        labeldescArr[i2] = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean a(int i) {
        FuncState.BlockCnt blockCnt = this.f1536b.d;
        Dyndata dyndata = this.d;
        Labeldesc labeldesc = dyndata.c[i];
        for (int i2 = blockCnt.f1531b; i2 < dyndata.f; i2++) {
            Labeldesc labeldesc2 = dyndata.e[i2];
            if (labeldesc2.f1543a.eq_b(labeldesc.f1543a)) {
                if (labeldesc.d > labeldesc2.d && (blockCnt.e || dyndata.f > blockCnt.f1531b)) {
                    this.f1536b.e(labeldesc.f1544b, labeldesc2.d);
                }
                a(i, labeldesc2);
                return true;
            }
        }
        return false;
    }

    private int a(Labeldesc[] labeldescArr, int i, LuaString luaString, int i2, int i3) {
        labeldescArr[i] = new Labeldesc(luaString, i3, i2, this.f1536b.l);
        return i;
    }

    private void b(Labeldesc labeldesc) {
        Labeldesc[] labeldescArr = this.d.c;
        int i = this.f1536b.d.c;
        while (i < this.d.d) {
            if (labeldescArr[i].f1543a.eq_b(labeldesc.f1543a)) {
                a(i, labeldesc);
            } else {
                i++;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a() {
        LuaString valueOf = LuaString.valueOf("break");
        Dyndata dyndata = this.d;
        Labeldesc[] a2 = a(this.d.e, this.d.f + 1);
        dyndata.e = a2;
        Dyndata dyndata2 = this.d;
        int i = dyndata2.f;
        dyndata2.f = i + 1;
        b(this.d.e[a(a2, i, valueOf, 0, this.f1536b.e)]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(Labeldesc labeldesc) {
        String str;
        LuaC.CompileState compileState = this.c;
        if (isReservedKeyword(labeldesc.f1543a.tojstring())) {
            str = "<" + labeldesc.f1543a + "> at line " + labeldesc.c + " not inside a loop";
        } else {
            str = "no visible label '" + labeldesc.f1543a + "' for <goto> at line " + labeldesc.c;
        }
        b(compileState.pushfstring(str));
    }

    private Prototype n() {
        Prototype prototype = this.f1536b.f1528a;
        if (prototype.p == null || this.f1536b.i >= prototype.p.length) {
            prototype.p = a(prototype.p, Math.max(1, this.f1536b.i << 1));
        }
        Prototype[] prototypeArr = prototype.p;
        FuncState funcState = this.f1536b;
        int i = funcState.i;
        funcState.i = i + 1;
        Prototype prototype2 = new Prototype();
        prototypeArr[i] = prototype2;
        return prototype2;
    }

    private void c(expdesc expdescVar) {
        FuncState funcState = this.f1536b.f1529b;
        expdescVar.a(11, funcState.c(37, 0, funcState.i - 1));
        funcState.f(expdescVar);
    }

    private void a(FuncState funcState, FuncState.BlockCnt blockCnt) {
        funcState.f1529b = this.f1536b;
        funcState.c = this;
        this.f1536b = funcState;
        funcState.e = 0;
        funcState.f = -1;
        funcState.g = new IntPtr(-1);
        funcState.n = (short) 0;
        funcState.h = 0;
        funcState.i = 0;
        funcState.m = (short) 0;
        funcState.k = (short) 0;
        funcState.l = (short) 0;
        funcState.j = this.d.f1540b;
        funcState.d = null;
        funcState.f1528a.source = this.n;
        funcState.f1528a.maxstacksize = 2;
        funcState.a(blockCnt, false);
    }

    private void o() {
        FuncState funcState = this.f1536b;
        Prototype prototype = funcState.f1528a;
        funcState.c(0, 0);
        funcState.a();
        prototype.code = a(prototype.code, funcState.e);
        prototype.lineinfo = a(prototype.lineinfo, funcState.e);
        prototype.k = a(prototype.k, funcState.h);
        prototype.p = a(prototype.p, funcState.i);
        prototype.locvars = a(prototype.locvars, funcState.k);
        prototype.upvalues = a(prototype.upvalues, funcState.m);
        a(funcState.d == null);
        this.f1536b = funcState.f1529b;
    }

    private void d(expdesc expdescVar) {
        FuncState funcState = this.f1536b;
        expdesc expdescVar2 = new expdesc();
        funcState.h(expdescVar);
        i();
        a(expdescVar2);
        funcState.c(expdescVar, expdescVar2);
    }

    private void e(expdesc expdescVar) {
        i();
        k(expdescVar);
        this.f1536b.i(expdescVar);
        p(93);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/prineside/luaj/compiler/LexState$ConsControl.class */
    public static class ConsControl {

        /* renamed from: a, reason: collision with root package name */
        expdesc f1537a = new expdesc();

        /* renamed from: b, reason: collision with root package name */
        expdesc f1538b;
        int c;
        int d;
        int e;

        ConsControl() {
        }
    }

    private void a(ConsControl consControl) {
        FuncState funcState = this.f1536b;
        short s = this.f1536b.n;
        expdesc expdescVar = new expdesc();
        expdesc expdescVar2 = new expdesc();
        if (this.i.f1549a == 288) {
            funcState.a(consControl.c, 2147483645, "items in a constructor");
            a(expdescVar);
        } else {
            e(expdescVar);
        }
        consControl.c++;
        p(61);
        int j = funcState.j(expdescVar);
        k(expdescVar2);
        funcState.b(10, consControl.f1538b.f1553b.d, j, funcState.j(expdescVar2));
        funcState.n = s;
    }

    private void b(ConsControl consControl) {
        k(consControl.f1537a);
        this.f1536b.a(consControl.d, 2147483645, "items in a constructor");
        consControl.d++;
        consControl.e++;
    }

    private void f(expdesc expdescVar) {
        FuncState funcState = this.f1536b;
        int i = this.h;
        int b2 = funcState.b(11, 0, 0, 0);
        ConsControl consControl = new ConsControl();
        consControl.e = 0;
        consControl.c = 0;
        consControl.d = 0;
        consControl.f1538b = expdescVar;
        expdescVar.a(11, b2);
        consControl.f1537a.a(0, 0);
        funcState.f(expdescVar);
        p(123);
        while (true) {
            a(consControl.f1537a.f1552a == 0 || consControl.e > 0);
            if (this.i.f1549a != 125) {
                funcState.a(consControl);
                switch (this.i.f1549a) {
                    case 91:
                        a(consControl);
                        break;
                    case User32.WM_MENUCHAR /* 288 */:
                        j();
                        if (this.j.f1549a == 61) {
                            a(consControl);
                            break;
                        }
                        break;
                }
                b(consControl);
                if (n(44) || n(59)) {
                }
            }
        }
        b(125, 123, i);
        funcState.b(consControl);
        InstructionPtr instructionPtr = new InstructionPtr(funcState.f1528a.code, b2);
        c(instructionPtr, r(consControl.d));
        d(instructionPtr, r(consControl.c));
    }

    private static int r(int i) {
        int i2 = 0;
        while (i >= 16) {
            i = (i + 1) >> 1;
            i2++;
        }
        return i < 8 ? i : ((i2 + 1) << 3) | (i - 8);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x004e, code lost:            i();        r0.is_vararg = 1;     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x005a, code lost:            a("<name> or " + a((java.lang.Object) "...") + " expected");     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0087, code lost:            q(r8);        r0.numparams = r0.l;        r0.d(r0.l);     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x009c, code lost:            return;     */
    /* JADX WARN: Code restructure failed: missing block: B:2:0x001a, code lost:            if (r5.i.f1549a != 41) goto L4;     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0024, code lost:            switch(r5.i.f1549a) {            case 280: goto L7;            case 288: goto L6;            default: goto L8;        };     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0040, code lost:            b(k());        r8 = r8 + 1;     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x007b, code lost:            if (r0.is_vararg != 0) goto L16;     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0084, code lost:            if (n(44) != false) goto L17;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void p() {
        /*
            r5 = this;
            r0 = r5
            com.prineside.luaj.compiler.FuncState r0 = r0.f1536b
            r1 = r0
            r6 = r1
            com.prineside.luaj.Prototype r0 = r0.f1528a
            r7 = r0
            r0 = 0
            r8 = r0
            r0 = r7
            r1 = 0
            r0.is_vararg = r1
            r0 = r5
            com.prineside.luaj.compiler.LexState$Token r0 = r0.i
            int r0 = r0.f1549a
            r1 = 41
            if (r0 == r1) goto L87
        L1d:
            r0 = r5
            com.prineside.luaj.compiler.LexState$Token r0 = r0.i
            int r0 = r0.f1549a
            switch(r0) {
                case 280: goto L4e;
                case 288: goto L40;
                default: goto L5a;
            }
        L40:
            r0 = r5
            r1 = r0
            com.prineside.luaj.LuaString r1 = r1.k()
            r0.b(r1)
            int r8 = r8 + 1
            goto L77
        L4e:
            r0 = r5
            r0.i()
            r0 = r7
            r1 = 1
            r0.is_vararg = r1
            goto L77
        L5a:
            r0 = r5
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r2 = r1
            java.lang.String r3 = "<name> or "
            r2.<init>(r3)
            java.lang.String r2 = "..."
            java.lang.String r2 = a(r2)
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r2 = " expected"
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.a(r1)
        L77:
            r0 = r7
            int r0 = r0.is_vararg
            if (r0 != 0) goto L87
            r0 = r5
            r1 = 44
            boolean r0 = r0.n(r1)
            if (r0 != 0) goto L1d
        L87:
            r0 = r5
            r1 = r8
            r0.q(r1)
            r0 = r7
            r1 = r6
            short r1 = r1.l
            r0.numparams = r1
            r0 = r6
            r1 = r0
            short r1 = r1.l
            r0.d(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.luaj.compiler.LexState.p():void");
    }

    private void a(expdesc expdescVar, boolean z, int i) {
        FuncState funcState = new FuncState();
        FuncState.BlockCnt blockCnt = new FuncState.BlockCnt();
        funcState.f1528a = n();
        funcState.f1528a.linedefined = i;
        a(funcState, blockCnt);
        p(40);
        if (z) {
            g("self");
            q(1);
        }
        p();
        p(41);
        z();
        funcState.f1528a.lastlinedefined = this.h;
        b(262, 265, i);
        c(expdescVar);
        o();
    }

    private int g(expdesc expdescVar) {
        int i = 1;
        k(expdescVar);
        while (n(44)) {
            this.f1536b.f(expdescVar);
            k(expdescVar);
            i++;
        }
        return i;
    }

    private void a(expdesc expdescVar, int i) {
        int i2;
        FuncState funcState = this.f1536b;
        expdesc expdescVar2 = new expdesc();
        switch (this.i.f1549a) {
            case 40:
                i();
                if (this.i.f1549a == 41) {
                    expdescVar2.f1552a = 0;
                } else {
                    g(expdescVar2);
                    funcState.c(expdescVar2);
                }
                b(41, 40, i);
                break;
            case 123:
                f(expdescVar2);
                break;
            case User32.WM_ENTERIDLE /* 289 */:
                a(expdescVar2, this.i.f1550b.f1548b);
                i();
                break;
            default:
                a("function arguments expected");
                return;
        }
        a(expdescVar.f1552a == 6);
        int i3 = expdescVar.f1553b.d;
        if (l(expdescVar2.f1552a)) {
            i2 = -1;
        } else {
            if (expdescVar2.f1552a != 0) {
                funcState.f(expdescVar2);
            }
            i2 = funcState.n - (i3 + 1);
        }
        expdescVar.a(12, funcState.b(29, i3, i2 + 1, 2));
        funcState.e(i);
        funcState.n = (short) (i3 + 1);
    }

    private void h(expdesc expdescVar) {
        switch (this.i.f1549a) {
            case 40:
                int i = this.h;
                i();
                k(expdescVar);
                b(41, 40, i);
                this.f1536b.e(expdescVar);
                return;
            case User32.WM_MENUCHAR /* 288 */:
                b(expdescVar);
                return;
            default:
                a("unexpected symbol " + this.i.f1549a + " (" + ((char) this.i.f1549a) + ")");
                return;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x00a9, code lost:            return;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void i(com.prineside.luaj.compiler.LexState.expdesc r5) {
        /*
            r4 = this;
            r0 = r4
            int r0 = r0.h
            r6 = r0
            r0 = r4
            r1 = r5
            r0.h(r1)
        La:
            r0 = r4
            com.prineside.luaj.compiler.LexState$Token r0 = r0.i
            int r0 = r0.f1549a
            switch(r0) {
                case 40: goto L98;
                case 46: goto L4c;
                case 58: goto L75;
                case 91: goto L54;
                case 123: goto L98;
                case 289: goto L98;
                default: goto La9;
            }
        L4c:
            r0 = r4
            r1 = r5
            r0.d(r1)
            goto La
        L54:
            com.prineside.luaj.compiler.LexState$expdesc r0 = new com.prineside.luaj.compiler.LexState$expdesc
            r1 = r0
            r1.<init>()
            r7 = r0
            r0 = r4
            com.prineside.luaj.compiler.FuncState r0 = r0.f1536b
            r1 = r5
            r0.h(r1)
            r0 = r4
            r1 = r7
            r0.e(r1)
            r0 = r4
            com.prineside.luaj.compiler.FuncState r0 = r0.f1536b
            r1 = r5
            r2 = r7
            r0.c(r1, r2)
            goto La
        L75:
            com.prineside.luaj.compiler.LexState$expdesc r0 = new com.prineside.luaj.compiler.LexState$expdesc
            r1 = r0
            r1.<init>()
            r7 = r0
            r0 = r4
            r0.i()
            r0 = r4
            r1 = r7
            r0.a(r1)
            r0 = r4
            com.prineside.luaj.compiler.FuncState r0 = r0.f1536b
            r1 = r5
            r2 = r7
            r0.b(r1, r2)
            r0 = r4
            r1 = r5
            r2 = r6
            r0.a(r1, r2)
            goto La
        L98:
            r0 = r4
            com.prineside.luaj.compiler.FuncState r0 = r0.f1536b
            r1 = r5
            r0.f(r1)
            r0 = r4
            r1 = r5
            r2 = r6
            r0.a(r1, r2)
            goto La
        La9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.luaj.compiler.LexState.i(com.prineside.luaj.compiler.LexState$expdesc):void");
    }

    private void j(expdesc expdescVar) {
        switch (this.i.f1549a) {
            case 123:
                f(expdescVar);
                return;
            case 263:
                expdescVar.a(3, 0);
                break;
            case 265:
                i();
                a(expdescVar, false, this.h);
                return;
            case User32.WM_IME_ENDCOMPOSITION /* 270 */:
                expdescVar.a(1, 0);
                break;
            case User32.WM_HSCROLL /* 276 */:
                expdescVar.a(2, 0);
                break;
            case GLFW.GLFW_KEY_CAPS_LOCK /* 280 */:
                FuncState funcState = this.f1536b;
                a(funcState.f1528a.is_vararg != 0, "cannot use " + a((Object) "...") + " outside a vararg function");
                expdescVar.a(13, funcState.b(38, 0, 1, 0));
                break;
            case User32.WM_MENUSELECT /* 287 */:
                expdescVar.a(5, 0);
                expdescVar.f1553b.setNval(this.i.f1550b.f1547a);
                break;
            case User32.WM_ENTERIDLE /* 289 */:
                a(expdescVar, this.i.f1550b.f1548b);
                break;
            default:
                i(expdescVar);
                return;
        }
        i();
    }

    private static int s(int i) {
        switch (i) {
            case 35:
                return 2;
            case 45:
                return 0;
            case 271:
                return 1;
            default:
                return 3;
        }
    }

    private static int t(int i) {
        switch (i) {
            case 37:
                return 4;
            case 42:
                return 2;
            case 43:
                return 0;
            case 45:
                return 1;
            case 47:
                return 3;
            case 60:
                return 9;
            case 62:
                return 11;
            case 94:
                return 5;
            case 257:
                return 13;
            case 272:
                return 14;
            case User32.WM_INITMENUPOPUP /* 279 */:
                return 6;
            case 281:
                return 8;
            case 282:
                return 12;
            case GLFW.GLFW_KEY_PRINT_SCREEN /* 283 */:
                return 10;
            case GLFW.GLFW_KEY_PAUSE /* 284 */:
                return 7;
            default:
                return 15;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/prineside/luaj/compiler/LexState$Priority.class */
    public static class Priority {

        /* renamed from: a, reason: collision with root package name */
        final byte f1545a;

        /* renamed from: b, reason: collision with root package name */
        final byte f1546b;

        public Priority(int i, int i2) {
            this.f1545a = (byte) i;
            this.f1546b = (byte) i2;
        }
    }

    private int b(expdesc expdescVar, int i) {
        int i2;
        l();
        int s = s(this.i.f1549a);
        if (s != 3) {
            int i3 = this.h;
            i();
            b(expdescVar, 8);
            this.f1536b.a(s, expdescVar, i3);
        } else {
            j(expdescVar);
        }
        int t = t(this.i.f1549a);
        while (true) {
            i2 = t;
            if (i2 == 15 || r[i2].f1545a <= i) {
                break;
            }
            expdesc expdescVar2 = new expdesc();
            int i4 = this.h;
            i();
            this.f1536b.a(i2, expdescVar);
            int b2 = b(expdescVar2, r[i2].f1546b);
            this.f1536b.a(i2, expdescVar, expdescVar2, i4);
            t = b2;
        }
        m();
        return i2;
    }

    private void k(expdesc expdescVar) {
        b(expdescVar, 0);
    }

    private boolean b(boolean z) {
        switch (this.i.f1549a) {
            case 260:
            case 261:
            case 262:
            case 286:
                return true;
            case User32.WM_VSCROLL /* 277 */:
                return z;
            default:
                return false;
        }
    }

    private void q() {
        FuncState funcState = this.f1536b;
        funcState.a(new FuncState.BlockCnt(), false);
        z();
        funcState.a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/prineside/luaj/compiler/LexState$LHS_assign.class */
    public static class LHS_assign {

        /* renamed from: a, reason: collision with root package name */
        LHS_assign f1541a;

        /* renamed from: b, reason: collision with root package name */
        expdesc f1542b = new expdesc();

        LHS_assign() {
        }
    }

    private void a(LHS_assign lHS_assign, expdesc expdescVar) {
        FuncState funcState = this.f1536b;
        short s = funcState.n;
        boolean z = false;
        while (lHS_assign != null) {
            if (lHS_assign.f1542b.f1552a == 9) {
                if (lHS_assign.f1542b.f1553b.c == expdescVar.f1552a && lHS_assign.f1542b.f1553b.f1555b == expdescVar.f1553b.d) {
                    z = true;
                    lHS_assign.f1542b.f1553b.c = (short) 7;
                    lHS_assign.f1542b.f1553b.f1555b = s;
                }
                if (expdescVar.f1552a == 7 && lHS_assign.f1542b.f1553b.f1554a == expdescVar.f1553b.d) {
                    z = true;
                    lHS_assign.f1542b.f1553b.f1554a = s;
                }
            }
            lHS_assign = lHS_assign.f1541a;
        }
        if (z) {
            funcState.b(expdescVar.f1552a == 7 ? 0 : 5, s, expdescVar.f1553b.d, 0);
            funcState.d(1);
        }
    }

    private void a(LHS_assign lHS_assign, int i) {
        expdesc expdescVar = new expdesc();
        a(7 <= lHS_assign.f1542b.f1552a && lHS_assign.f1542b.f1552a <= 9, "syntax error");
        if (n(44)) {
            LHS_assign lHS_assign2 = new LHS_assign();
            lHS_assign2.f1541a = lHS_assign;
            i(lHS_assign2.f1542b);
            if (lHS_assign2.f1542b.f1552a != 9) {
                a(lHS_assign, lHS_assign2.f1542b);
            }
            a(lHS_assign2, i + 1);
        } else {
            p(61);
            int g = g(expdescVar);
            if (g != i) {
                a(i, g, expdescVar);
                if (g > i) {
                    FuncState funcState = this.f1536b;
                    funcState.n = (short) (funcState.n - (g - i));
                }
            } else {
                this.f1536b.d(expdescVar);
                this.f1536b.a(lHS_assign.f1542b, expdescVar);
                return;
            }
        }
        expdescVar.a(6, this.f1536b.n - 1);
        this.f1536b.a(lHS_assign.f1542b, expdescVar);
    }

    private int r() {
        expdesc expdescVar = new expdesc();
        k(expdescVar);
        if (expdescVar.f1552a == 1) {
            expdescVar.f1552a = 3;
        }
        this.f1536b.k(expdescVar);
        return expdescVar.d.f1534a;
    }

    private void u(int i) {
        LuaString valueOf;
        int i2 = this.h;
        if (n(GLFW.GLFW_KEY_PAGE_UP)) {
            valueOf = k();
        } else {
            i();
            valueOf = LuaString.valueOf("break");
        }
        Dyndata dyndata = this.d;
        Labeldesc[] a2 = a(this.d.c, this.d.d + 1);
        dyndata.c = a2;
        Dyndata dyndata2 = this.d;
        int i3 = dyndata2.d;
        dyndata2.d = i3 + 1;
        a(a(a2, i3, valueOf, i2, i));
    }

    private void s() {
        while (true) {
            if (this.i.f1549a == 59 || this.i.f1549a == 285) {
                y();
            } else {
                return;
            }
        }
    }

    private void a(LuaString luaString, int i) {
        this.f1536b.a(this.d.e, this.d.f, luaString);
        p(285);
        Dyndata dyndata = this.d;
        Labeldesc[] a2 = a(this.d.e, this.d.f + 1);
        dyndata.e = a2;
        Dyndata dyndata2 = this.d;
        int i2 = dyndata2.f;
        dyndata2.f = i2 + 1;
        int a3 = a(a2, i2, luaString, i, this.f1536b.c());
        s();
        if (b(false)) {
            this.d.e[a3].d = this.f1536b.d.d;
        }
        b(this.d.e[a3]);
    }

    private void v(int i) {
        FuncState funcState = this.f1536b;
        FuncState.BlockCnt blockCnt = new FuncState.BlockCnt();
        i();
        int c = funcState.c();
        int r2 = r();
        funcState.a(blockCnt, true);
        p(259);
        q();
        funcState.d(funcState.b(), c);
        b(262, User32.WM_INITMENU, i);
        funcState.a();
        funcState.b(r2);
    }

    private void w(int i) {
        FuncState funcState = this.f1536b;
        int c = funcState.c();
        FuncState.BlockCnt blockCnt = new FuncState.BlockCnt();
        FuncState.BlockCnt blockCnt2 = new FuncState.BlockCnt();
        funcState.a(blockCnt, true);
        funcState.a(blockCnt2, false);
        i();
        z();
        b(User32.WM_VSCROLL, 273, i);
        int r2 = r();
        if (blockCnt2.e) {
            funcState.e(r2, blockCnt2.d);
        }
        funcState.a();
        funcState.d(r2, c);
        funcState.a();
    }

    private int t() {
        expdesc expdescVar = new expdesc();
        k(expdescVar);
        int i = expdescVar.f1552a;
        this.f1536b.f(expdescVar);
        return i;
    }

    private void a(int i, int i2, int i3, boolean z) {
        int b2;
        FuncState.BlockCnt blockCnt = new FuncState.BlockCnt();
        FuncState funcState = this.f1536b;
        q(3);
        p(259);
        int b3 = z ? funcState.b(33, i, -1) : funcState.b();
        funcState.a(blockCnt, false);
        q(i3);
        funcState.d(i3);
        q();
        funcState.a();
        funcState.b(b3);
        if (z) {
            b2 = funcState.b(32, i, -1);
        } else {
            funcState.b(34, i, 0, i3);
            funcState.e(i2);
            b2 = funcState.b(35, i + 2, -1);
        }
        funcState.d(b2, b3 + 1);
        funcState.e(i2);
    }

    private void b(LuaString luaString, int i) {
        FuncState funcState = this.f1536b;
        short s = funcState.n;
        g("(for index)");
        g("(for limit)");
        g("(for step)");
        b(luaString);
        p(61);
        t();
        p(44);
        t();
        if (n(44)) {
            t();
        } else {
            funcState.f(funcState.n, funcState.a(LuaNumber.valueOf(1)));
            funcState.d(1);
        }
        a((int) s, i, 1, true);
    }

    private void c(LuaString luaString) {
        FuncState funcState = this.f1536b;
        expdesc expdescVar = new expdesc();
        int i = 4;
        short s = funcState.n;
        g("(for generator)");
        g("(for state)");
        g("(for control)");
        b(luaString);
        while (n(44)) {
            b(k());
            i++;
        }
        p(GLFW.GLFW_KEY_HOME);
        int i2 = this.h;
        a(3, g(expdescVar), expdescVar);
        funcState.c(3);
        a((int) s, i2, i - 3, false);
    }

    private void x(int i) {
        FuncState funcState = this.f1536b;
        funcState.a(new FuncState.BlockCnt(), true);
        i();
        LuaString k = k();
        switch (this.i.f1549a) {
            case 44:
            case GLFW.GLFW_KEY_HOME /* 268 */:
                c(k);
                break;
            case 61:
                b(k, i);
                break;
            default:
                a(a((Object) "=") + " or " + a((Object) "in") + " expected");
                break;
        }
        b(262, GLFW.GLFW_KEY_DOWN, i);
        funcState.a();
    }

    private void a(IntPtr intPtr) {
        int b2;
        expdesc expdescVar = new expdesc();
        FuncState.BlockCnt blockCnt = new FuncState.BlockCnt();
        i();
        k(expdescVar);
        p(User32.WM_TIMER);
        if (this.i.f1549a == 266 || this.i.f1549a == 258) {
            this.f1536b.l(expdescVar);
            this.f1536b.a(blockCnt, false);
            u(expdescVar.c.f1534a);
            s();
            if (b(false)) {
                this.f1536b.a();
                return;
            }
            b2 = this.f1536b.b();
        } else {
            this.f1536b.k(expdescVar);
            this.f1536b.a(blockCnt, false);
            b2 = expdescVar.d.f1534a;
        }
        z();
        this.f1536b.a();
        if (this.i.f1549a == 260 || this.i.f1549a == 261) {
            this.f1536b.a(intPtr, this.f1536b.b());
        }
        this.f1536b.b(b2);
    }

    private void y(int i) {
        IntPtr intPtr = new IntPtr(-1);
        a(intPtr);
        while (this.i.f1549a == 261) {
            a(intPtr);
        }
        if (n(260)) {
            q();
        }
        b(262, GLFW.GLFW_KEY_PAGE_DOWN, i);
        this.f1536b.b(intPtr.f1534a);
    }

    private void u() {
        expdesc expdescVar = new expdesc();
        FuncState funcState = this.f1536b;
        b(k());
        q(1);
        a(expdescVar, false, this.h);
        funcState.a(funcState.l - 1).startpc = funcState.e;
    }

    private void v() {
        int i;
        int i2 = 0;
        expdesc expdescVar = new expdesc();
        do {
            b(k());
            i2++;
        } while (n(44));
        if (n(61)) {
            i = g(expdescVar);
        } else {
            expdescVar.f1552a = 0;
            i = 0;
        }
        a(i2, i, expdescVar);
        q(i2);
    }

    private boolean l(expdesc expdescVar) {
        boolean z = false;
        b(expdescVar);
        while (this.i.f1549a == 46) {
            d(expdescVar);
        }
        if (this.i.f1549a == 58) {
            z = true;
            d(expdescVar);
        }
        return z;
    }

    private void z(int i) {
        expdesc expdescVar = new expdesc();
        expdesc expdescVar2 = new expdesc();
        i();
        a(expdescVar2, l(expdescVar), i);
        this.f1536b.a(expdescVar, expdescVar2);
        this.f1536b.e(i);
    }

    private void w() {
        FuncState funcState = this.f1536b;
        LHS_assign lHS_assign = new LHS_assign();
        i(lHS_assign.f1542b);
        if (this.i.f1549a == 61 || this.i.f1549a == 44) {
            lHS_assign.f1541a = null;
            a(lHS_assign, 1);
        } else {
            a(lHS_assign.f1542b.f1552a == 12, "syntax error");
            d(funcState.a(lHS_assign.f1542b), 1);
        }
    }

    private void x() {
        int i;
        int i2;
        FuncState funcState = this.f1536b;
        expdesc expdescVar = new expdesc();
        if (b(true) || this.i.f1549a == 59) {
            i = 0;
            i2 = 0;
        } else {
            i = g(expdescVar);
            if (l(expdescVar.f1552a)) {
                funcState.c(expdescVar);
                if (expdescVar.f1552a == 12 && i == 1) {
                    a(funcState.a(expdescVar), 30);
                    a(Lua.GETARG_A(funcState.b(expdescVar)) == funcState.l);
                }
                i2 = funcState.l;
                i = -1;
            } else if (i == 1) {
                i2 = funcState.g(expdescVar);
            } else {
                funcState.f(expdescVar);
                i2 = funcState.l;
                a(i == funcState.n - i2);
            }
        }
        funcState.c(i2, i);
        n(59);
    }

    private void y() {
        int i = this.h;
        l();
        switch (this.i.f1549a) {
            case 59:
                i();
                break;
            case 258:
            case GLFW.GLFW_KEY_PAGE_UP /* 266 */:
                u(this.f1536b.b());
                break;
            case 259:
                i();
                q();
                b(262, 259, i);
                break;
            case GLFW.GLFW_KEY_DOWN /* 264 */:
                x(i);
                break;
            case 265:
                z(i);
                break;
            case GLFW.GLFW_KEY_PAGE_DOWN /* 267 */:
                y(i);
                break;
            case 269:
                i();
                if (n(265)) {
                    u();
                    break;
                } else {
                    v();
                    break;
                }
            case 273:
                w(i);
                break;
            case User32.WM_SYSCOMMAND /* 274 */:
                i();
                x();
                break;
            case User32.WM_INITMENU /* 278 */:
                v(i);
                break;
            case 285:
                i();
                a(k(), i);
                break;
            default:
                w();
                break;
        }
        a(this.f1536b.f1528a.maxstacksize >= this.f1536b.n && this.f1536b.n >= this.f1536b.l);
        this.f1536b.n = this.f1536b.l;
        m();
    }

    private void z() {
        while (!b(true)) {
            if (this.i.f1549a == 274) {
                y();
                return;
            }
            y();
        }
    }

    public void mainfunc(FuncState funcState) {
        a(funcState, new FuncState.BlockCnt());
        this.f1536b.f1528a.is_vararg = 1;
        expdesc expdescVar = new expdesc();
        expdescVar.a(7, 0);
        this.f1536b.a(this.o, expdescVar);
        i();
        z();
        o(286);
        o();
    }
}
