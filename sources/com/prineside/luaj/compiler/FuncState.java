package com.prineside.luaj.compiler;

import com.prineside.luaj.LocVars;
import com.prineside.luaj.Lua;
import com.prineside.luaj.LuaNumber;
import com.prineside.luaj.LuaString;
import com.prineside.luaj.LuaValue;
import com.prineside.luaj.Prototype;
import com.prineside.luaj.Upvaldesc;
import com.prineside.luaj.compiler.LexState;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.Hashtable;

/* loaded from: infinitode-2.jar:com/prineside/luaj/compiler/FuncState.class */
public class FuncState extends Constants {

    /* renamed from: a, reason: collision with root package name */
    Prototype f1528a;
    private Hashtable o;

    /* renamed from: b, reason: collision with root package name */
    FuncState f1529b;
    LexState c;
    BlockCnt d;
    int e;
    int f;
    IntPtr g;
    int h;
    int i;
    int j;
    short k;
    short l;
    short m;
    short n;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/prineside/luaj/compiler/FuncState$BlockCnt.class */
    public static class BlockCnt {

        /* renamed from: a, reason: collision with root package name */
        BlockCnt f1530a;

        /* renamed from: b, reason: collision with root package name */
        short f1531b;
        short c;
        short d;
        boolean e;
        boolean f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final InstructionPtr a(LexState.expdesc expdescVar) {
        return new InstructionPtr(this.f1528a.code, expdescVar.f1553b.d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int b(LexState.expdesc expdescVar) {
        return this.f1528a.code[expdescVar.f1553b.d];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int b(int i, int i2, int i3) {
        return c(i, i2, 131070);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void c(LexState.expdesc expdescVar) {
        a(expdescVar, -1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(LexState.Labeldesc[] labeldescArr, int i, LuaString luaString) {
        for (int i2 = this.d.f1531b; i2 < i; i2++) {
            if (luaString.eq_b(labeldescArr[i2].f1543a)) {
                this.c.b(this.c.c.pushfstring("label '" + luaString + " already defined on line " + labeldescArr[i2].c));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(int i, int i2, String str) {
        if (i > i2) {
            a(i2, str);
        }
    }

    private void a(int i, String str) {
        String pushfstring;
        if (this.f1528a.linedefined == 0) {
            pushfstring = this.c.c.pushfstring("main function has more than " + i + SequenceUtils.SPACE + str);
        } else {
            pushfstring = this.c.c.pushfstring("function at line " + this.f1528a.linedefined + " has more than " + i + SequenceUtils.SPACE + str);
        }
        this.c.a(pushfstring, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final LocVars a(int i) {
        short s = this.c.d.f1539a[this.j + i].f1551a;
        a(s < this.k);
        return this.f1528a.locvars[s];
    }

    private void f(int i) {
        this.c.d.f1540b -= this.l - i;
        while (this.l > i) {
            short s = (short) (this.l - 1);
            this.l = s;
            a(s).endpc = this.e;
        }
    }

    private int b(LuaString luaString) {
        Upvaldesc[] upvaldescArr = this.f1528a.upvalues;
        for (int i = 0; i < this.m; i++) {
            if (upvaldescArr[i].name.eq_b(luaString)) {
                return i;
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a(LuaString luaString, LexState.expdesc expdescVar) {
        a(this.m + 1, 255, "upvalues");
        if (this.f1528a.upvalues == null || this.m + 1 > this.f1528a.upvalues.length) {
            this.f1528a.upvalues = a(this.f1528a.upvalues, this.m > 0 ? this.m << 1 : 1);
        }
        this.f1528a.upvalues[this.m] = new Upvaldesc(luaString, expdescVar.f1552a == 7, expdescVar.f1553b.d);
        short s = this.m;
        this.m = (short) (s + 1);
        return s;
    }

    private int c(LuaString luaString) {
        for (int i = this.l - 1; i >= 0; i--) {
            if (luaString.eq_b(a(i).varname)) {
                return i;
            }
        }
        return -1;
    }

    private void g(int i) {
        BlockCnt blockCnt = this.d;
        while (true) {
            BlockCnt blockCnt2 = blockCnt;
            if (blockCnt2.d > i) {
                blockCnt = blockCnt2.f1530a;
            } else {
                blockCnt2.e = true;
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(FuncState funcState, LuaString luaString, LexState.expdesc expdescVar, int i) {
        if (funcState == null) {
            return 0;
        }
        int c = funcState.c(luaString);
        if (c >= 0) {
            expdescVar.a(7, c);
            if (i == 0) {
                funcState.g(c);
                return 7;
            }
            return 7;
        }
        int b2 = funcState.b(luaString);
        int i2 = b2;
        if (b2 < 0) {
            if (a(funcState.f1529b, luaString, expdescVar, 0) == 0) {
                return 0;
            }
            i2 = funcState.a(luaString, expdescVar);
        }
        expdescVar.a(8, i2);
        return 8;
    }

    private void a(BlockCnt blockCnt) {
        int i = blockCnt.c;
        LexState.Labeldesc[] labeldescArr = this.c.d.c;
        while (i < this.c.d.d) {
            LexState.Labeldesc labeldesc = labeldescArr[i];
            if (labeldesc.d > blockCnt.d) {
                if (blockCnt.e) {
                    e(labeldesc.f1544b, blockCnt.d);
                }
                labeldesc.d = blockCnt.d;
            }
            if (!this.c.a(i)) {
                i++;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(BlockCnt blockCnt, boolean z) {
        blockCnt.f = z;
        blockCnt.d = this.l;
        blockCnt.f1531b = (short) this.c.d.f;
        blockCnt.c = (short) this.c.d.d;
        blockCnt.e = false;
        blockCnt.f1530a = this.d;
        this.d = blockCnt;
        a(this.n == this.l);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a() {
        BlockCnt blockCnt = this.d;
        if (blockCnt.f1530a != null && blockCnt.e) {
            int b2 = b();
            e(b2, blockCnt.d);
            b(b2);
        }
        if (blockCnt.f) {
            this.c.a();
        }
        this.d = blockCnt.f1530a;
        f(blockCnt.d);
        a(blockCnt.d == this.l);
        this.n = this.l;
        this.c.d.f = blockCnt.f1531b;
        if (blockCnt.f1530a != null) {
            a(blockCnt);
        } else if (blockCnt.c < this.c.d.d) {
            this.c.a(this.c.d.c[blockCnt.c]);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(LexState.ConsControl consControl) {
        if (consControl.f1537a.f1552a == 0) {
            return;
        }
        f(consControl.f1537a);
        consControl.f1537a.f1552a = 0;
        if (consControl.e == 50) {
            e(consControl.f1538b.f1553b.d, consControl.d, consControl.e);
            consControl.e = 0;
        }
    }

    private static boolean h(int i) {
        return i == 12 || i == 13;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void b(LexState.ConsControl consControl) {
        if (consControl.e == 0) {
            return;
        }
        if (h(consControl.f1537a.f1552a)) {
            c(consControl.f1537a);
            e(consControl.f1538b.f1553b.d, consControl.d, -1);
            consControl.d--;
        } else {
            if (consControl.f1537a.f1552a != 0) {
                f(consControl.f1537a);
            }
            e(consControl.f1538b.f1553b.d, consControl.d, consControl.e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void b(int i, int i2) {
        int i3 = (i + i2) - 1;
        if (this.e > this.f && this.e > 0) {
            int i4 = this.f1528a.code[this.e - 1];
            if (GET_OPCODE(i4) == 4) {
                int GETARG_A = GETARG_A(i4);
                int GETARG_B = GETARG_A + GETARG_B(i4);
                if ((GETARG_A <= i && i <= GETARG_B + 1) || (i <= GETARG_A && GETARG_A <= i3 + 1)) {
                    if (GETARG_A < i) {
                        i = GETARG_A;
                    }
                    if (GETARG_B > i3) {
                        i3 = GETARG_B;
                    }
                    InstructionPtr instructionPtr = new InstructionPtr(this.f1528a.code, this.e - 1);
                    b(instructionPtr, i);
                    c(instructionPtr, i3 - i);
                    return;
                }
            }
        }
        b(4, i, i2 - 1, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int b() {
        int i = this.g.f1534a;
        this.g.f1534a = -1;
        IntPtr intPtr = new IntPtr(b(23, 0, -1));
        a(intPtr, i);
        return intPtr.f1534a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void c(int i, int i2) {
        b(31, i, i2 + 1, 0);
    }

    private int c(int i, int i2, int i3, int i4) {
        b(i, i2, i3, i4);
        return b();
    }

    private void g(int i, int i2) {
        InstructionPtr instructionPtr = new InstructionPtr(this.f1528a.code, i);
        int i3 = i2 - (i + 1);
        a(i2 != -1);
        if (Math.abs(i3) > 131071) {
            this.c.a("control structure too long");
        }
        e(instructionPtr, i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int c() {
        this.f = this.e;
        return this.e;
    }

    private int i(int i) {
        int GETARG_sBx = GETARG_sBx(this.f1528a.code[i]);
        if (GETARG_sBx != -1) {
            return i + 1 + GETARG_sBx;
        }
        return -1;
    }

    private InstructionPtr j(int i) {
        InstructionPtr instructionPtr = new InstructionPtr(this.f1528a.code, i);
        if (i > 0 && testTMode(GET_OPCODE(instructionPtr.f1532a[instructionPtr.f1533b - 1]))) {
            return new InstructionPtr(instructionPtr.f1532a, instructionPtr.f1533b - 1);
        }
        return instructionPtr;
    }

    private boolean k(int i) {
        while (i != -1) {
            if (GET_OPCODE(j(i).a()) == 28) {
                i = i(i);
            } else {
                return true;
            }
        }
        return false;
    }

    private boolean h(int i, int i2) {
        InstructionPtr j = j(i);
        if (GET_OPCODE(j.a()) != 28) {
            return false;
        }
        if (i2 != 255 && i2 != GETARG_B(j.a())) {
            b(j, i2);
            return true;
        }
        j.a(a(27, GETARG_B(j.a()), 0, Lua.GETARG_C(j.a())));
        return true;
    }

    private void l(int i) {
        while (i != -1) {
            h(i, 255);
            i = i(i);
        }
    }

    private void d(int i, int i2, int i3, int i4) {
        while (i != -1) {
            int i5 = i(i);
            if (h(i, i3)) {
                g(i, i2);
            } else {
                g(i, i4);
            }
            i = i5;
        }
    }

    private void d() {
        d(this.g.f1534a, this.e, 255, this.e);
        this.g.f1534a = -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void d(int i, int i2) {
        if (i2 == this.e) {
            b(i);
        } else {
            a(i2 < this.e);
            d(i, i2, 255, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void e(int i, int i2) {
        int i3 = i2 + 1;
        while (i != -1) {
            int i4 = i(i);
            a(GET_OPCODE(this.f1528a.code[i]) == 23 && (GETARG_A(this.f1528a.code[i]) == 0 || GETARG_A(this.f1528a.code[i]) >= i3));
            a(this.f1528a.code, i, i3);
            i = i4;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void b(int i) {
        c();
        a(this.g, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(IntPtr intPtr, int i) {
        if (i == -1) {
            return;
        }
        if (intPtr.f1534a == -1) {
            intPtr.f1534a = i;
            return;
        }
        int i2 = intPtr.f1534a;
        while (true) {
            int i3 = i2;
            int i4 = i(i3);
            if (i4 != -1) {
                i2 = i4;
            } else {
                g(i3, i);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void c(int i) {
        int i2 = this.n + i;
        if (i2 > this.f1528a.maxstacksize) {
            if (i2 >= 150) {
                this.c.a("function or expression too complex");
            }
            this.f1528a.maxstacksize = i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void d(int i) {
        c(i);
        this.n = (short) (this.n + i);
    }

    private void m(int i) {
        if (!ISK(i) && i >= this.l) {
            this.n = (short) (this.n - 1);
            a(i == this.n);
        }
    }

    private void m(LexState.expdesc expdescVar) {
        if (expdescVar.f1552a == 6) {
            m(expdescVar.f1553b.d);
        }
    }

    private int b(LuaValue luaValue) {
        if (this.o == null) {
            this.o = new Hashtable();
        } else if (this.o.containsKey(luaValue)) {
            return ((Integer) this.o.get(luaValue)).intValue();
        }
        int i = this.h;
        this.o.put(luaValue, Integer.valueOf(i));
        Prototype prototype = this.f1528a;
        if (prototype.k == null || this.h + 1 >= prototype.k.length) {
            prototype.k = a(prototype.k, (this.h << 1) + 1);
        }
        LuaValue[] luaValueArr = prototype.k;
        int i2 = this.h;
        this.h = i2 + 1;
        luaValueArr[i2] = luaValue;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a(LuaString luaString) {
        return b((LuaValue) luaString);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a(LuaValue luaValue) {
        if (luaValue instanceof LuaNumber) {
            double d = luaValue.todouble();
            int i = (int) d;
            if (d == i) {
                luaValue = LuaNumber.valueOf(i);
            }
        }
        return b(luaValue);
    }

    private int b(boolean z) {
        return b(z ? LuaValue.TRUE : LuaValue.FALSE);
    }

    private int e() {
        return b(LuaValue.NIL);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(LexState.expdesc expdescVar, int i) {
        if (expdescVar.f1552a == 12) {
            d(a(expdescVar), i + 1);
        } else if (expdescVar.f1552a == 13) {
            c(a(expdescVar), i + 1);
            b(a(expdescVar), this.n);
            d(1);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void d(LexState.expdesc expdescVar) {
        if (expdescVar.f1552a == 12) {
            expdescVar.f1552a = 6;
            expdescVar.f1553b.d = GETARG_A(b(expdescVar));
        } else if (expdescVar.f1552a == 13) {
            c(a(expdescVar), 2);
            expdescVar.f1552a = 11;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void e(LexState.expdesc expdescVar) {
        switch (expdescVar.f1552a) {
            case 7:
                expdescVar.f1552a = 6;
                return;
            case 8:
                expdescVar.f1553b.d = b(5, 0, expdescVar.f1553b.d, 0);
                expdescVar.f1552a = 11;
                return;
            case 9:
                int i = 6;
                m(expdescVar.f1553b.f1554a);
                if (expdescVar.f1553b.c == 7) {
                    m(expdescVar.f1553b.f1555b);
                    i = 7;
                }
                expdescVar.f1553b.d = b(i, 0, expdescVar.f1553b.f1555b, expdescVar.f1553b.f1554a);
                expdescVar.f1552a = 11;
                return;
            case 10:
            case 11:
            default:
                return;
            case 12:
            case 13:
                d(expdescVar);
                return;
        }
    }

    private int d(int i, int i2, int i3) {
        c();
        return b(3, i, i2, i3);
    }

    private void b(LexState.expdesc expdescVar, int i) {
        e(expdescVar);
        switch (expdescVar.f1552a) {
            case 1:
                b(i, 1);
                break;
            case 2:
            case 3:
                b(3, i, expdescVar.f1552a == 2 ? 1 : 0, 0);
                break;
            case 4:
                f(i, expdescVar.f1553b.d);
                break;
            case 5:
                f(i, a(expdescVar.f1553b.nval()));
                break;
            case 6:
                if (i != expdescVar.f1553b.d) {
                    b(0, i, expdescVar.f1553b.d, 0);
                    break;
                }
                break;
            case 7:
            case 8:
            case 9:
            case 10:
            default:
                a(expdescVar.f1552a == 0 || expdescVar.f1552a == 10);
                return;
            case 11:
                b(a(expdescVar), i);
                break;
        }
        expdescVar.f1553b.d = i;
        expdescVar.f1552a = 6;
    }

    private void n(LexState.expdesc expdescVar) {
        if (expdescVar.f1552a != 6) {
            d(1);
            b(expdescVar, this.n - 1);
        }
    }

    private void c(LexState.expdesc expdescVar, int i) {
        b(expdescVar, i);
        if (expdescVar.f1552a == 10) {
            a(expdescVar.c, expdescVar.f1553b.d);
        }
        if (expdescVar.a()) {
            int i2 = -1;
            int i3 = -1;
            if (k(expdescVar.c.f1534a) || k(expdescVar.d.f1534a)) {
                int b2 = expdescVar.f1552a == 10 ? -1 : b();
                i2 = d(i, 0, 1);
                i3 = d(i, 1, 0);
                b(b2);
            }
            int c = c();
            d(expdescVar.d.f1534a, c, i, i2);
            d(expdescVar.c.f1534a, c, i, i3);
        }
        IntPtr intPtr = expdescVar.d;
        expdescVar.c.f1534a = -1;
        intPtr.f1534a = -1;
        expdescVar.f1553b.d = i;
        expdescVar.f1552a = 6;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void f(LexState.expdesc expdescVar) {
        e(expdescVar);
        m(expdescVar);
        d(1);
        c(expdescVar, this.n - 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int g(LexState.expdesc expdescVar) {
        e(expdescVar);
        if (expdescVar.f1552a == 6) {
            if (!expdescVar.a()) {
                return expdescVar.f1553b.d;
            }
            if (expdescVar.f1553b.d >= this.l) {
                c(expdescVar, expdescVar.f1553b.d);
                return expdescVar.f1553b.d;
            }
        }
        f(expdescVar);
        return expdescVar.f1553b.d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void h(LexState.expdesc expdescVar) {
        if (expdescVar.f1552a != 8 || expdescVar.a()) {
            g(expdescVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void i(LexState.expdesc expdescVar) {
        if (expdescVar.a()) {
            g(expdescVar);
        } else {
            e(expdescVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0009. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0091  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int j(com.prineside.luaj.compiler.LexState.expdesc r6) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r0.i(r1)
            r0 = r6
            int r0 = r0.f1552a
            switch(r0) {
                case 1: goto L2c;
                case 2: goto L2c;
                case 3: goto L2c;
                case 4: goto L84;
                case 5: goto L6d;
                default: goto L9c;
            }
        L2c:
            r0 = r5
            int r0 = r0.h
            r1 = 255(0xff, float:3.57E-43)
            if (r0 > r1) goto L9c
            r0 = r6
            com.prineside.luaj.compiler.LexState$expdesc$U r0 = r0.f1553b
            r1 = r6
            int r1 = r1.f1552a
            r2 = 1
            if (r1 != r2) goto L49
            r1 = r5
            int r1 = r1.e()
            goto L5a
        L49:
            r1 = r5
            r2 = r6
            int r2 = r2.f1552a
            r3 = 2
            if (r2 != r3) goto L56
            r2 = 1
            goto L57
        L56:
            r2 = 0
        L57:
            int r1 = r1.b(r2)
        L5a:
            r0.d = r1
            r0 = r6
            r1 = 4
            r0.f1552a = r1
            r0 = r6
            com.prineside.luaj.compiler.LexState$expdesc$U r0 = r0.f1553b
            int r0 = r0.d
            int r0 = RKASK(r0)
            return r0
        L6d:
            r0 = r6
            com.prineside.luaj.compiler.LexState$expdesc$U r0 = r0.f1553b
            r1 = r5
            r2 = r6
            com.prineside.luaj.compiler.LexState$expdesc$U r2 = r2.f1553b
            com.prineside.luaj.LuaValue r2 = r2.nval()
            int r1 = r1.a(r2)
            r0.d = r1
            r0 = r6
            r1 = 4
            r0.f1552a = r1
        L84:
            r0 = r6
            com.prineside.luaj.compiler.LexState$expdesc$U r0 = r0.f1553b
            int r0 = r0.d
            r1 = 255(0xff, float:3.57E-43)
            if (r0 > r1) goto L9c
            r0 = r6
            com.prineside.luaj.compiler.LexState$expdesc$U r0 = r0.f1553b
            int r0 = r0.d
            int r0 = RKASK(r0)
            return r0
        L9c:
            r0 = r5
            r1 = r6
            int r0 = r0.g(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.luaj.compiler.FuncState.j(com.prineside.luaj.compiler.LexState$expdesc):int");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(LexState.expdesc expdescVar, LexState.expdesc expdescVar2) {
        switch (expdescVar.f1552a) {
            case 7:
                m(expdescVar2);
                c(expdescVar2, expdescVar.f1553b.d);
                return;
            case 8:
                b(9, g(expdescVar2), expdescVar.f1553b.d, 0);
                break;
            case 9:
                b(expdescVar.f1553b.c == 7 ? 10 : 8, expdescVar.f1553b.f1555b, expdescVar.f1553b.f1554a, j(expdescVar2));
                break;
            default:
                a(false);
                break;
        }
        m(expdescVar2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void b(LexState.expdesc expdescVar, LexState.expdesc expdescVar2) {
        g(expdescVar);
        m(expdescVar);
        short s = this.n;
        d(2);
        b(12, s, expdescVar.f1553b.d, j(expdescVar2));
        m(expdescVar2);
        expdescVar.f1553b.d = s;
        expdescVar.f1552a = 6;
    }

    private void o(LexState.expdesc expdescVar) {
        InstructionPtr j = j(expdescVar.f1553b.d);
        a((!testTMode(GET_OPCODE(j.a())) || GET_OPCODE(j.a()) == 28 || Lua.GET_OPCODE(j.a()) == 27) ? false : true);
        b(j, GETARG_A(j.a()) != 0 ? 0 : 1);
    }

    private int d(LexState.expdesc expdescVar, int i) {
        if (expdescVar.f1552a == 11) {
            int b2 = b(expdescVar);
            if (GET_OPCODE(b2) == 20) {
                this.e--;
                return c(27, GETARG_B(b2), 0, i != 0 ? 0 : 1);
            }
        }
        n(expdescVar);
        m(expdescVar);
        return c(28, 255, expdescVar.f1553b.d, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void k(LexState.expdesc expdescVar) {
        int d;
        e(expdescVar);
        switch (expdescVar.f1552a) {
            case 2:
            case 4:
            case 5:
                d = -1;
                break;
            case 3:
            case 6:
            case 7:
            case 8:
            case 9:
            default:
                d = d(expdescVar, 0);
                break;
            case 10:
                o(expdescVar);
                d = expdescVar.f1553b.d;
                break;
        }
        a(expdescVar.d, d);
        b(expdescVar.c.f1534a);
        expdescVar.c.f1534a = -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void l(LexState.expdesc expdescVar) {
        int d;
        e(expdescVar);
        switch (expdescVar.f1552a) {
            case 1:
            case 3:
                d = -1;
                break;
            case 10:
                d = expdescVar.f1553b.d;
                break;
            default:
                d = d(expdescVar, 1);
                break;
        }
        a(expdescVar.c, d);
        b(expdescVar.d.f1534a);
        expdescVar.d.f1534a = -1;
    }

    private void p(LexState.expdesc expdescVar) {
        e(expdescVar);
        switch (expdescVar.f1552a) {
            case 1:
            case 3:
                expdescVar.f1552a = 2;
                break;
            case 2:
            case 4:
            case 5:
                expdescVar.f1552a = 3;
                break;
            case 6:
            case 11:
                n(expdescVar);
                m(expdescVar);
                expdescVar.f1553b.d = b(20, 0, expdescVar.f1553b.d, 0);
                expdescVar.f1552a = 11;
                break;
            case 7:
            case 8:
            case 9:
            default:
                a(false);
                break;
            case 10:
                o(expdescVar);
                break;
        }
        int i = expdescVar.d.f1534a;
        expdescVar.d.f1534a = expdescVar.c.f1534a;
        expdescVar.c.f1534a = i;
        l(expdescVar.d.f1534a);
        l(expdescVar.c.f1534a);
    }

    private static boolean n(int i) {
        return i == 6 || i == 7;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void c(LexState.expdesc expdescVar, LexState.expdesc expdescVar2) {
        expdescVar.f1553b.f1555b = (short) expdescVar.f1553b.d;
        expdescVar.f1553b.f1554a = (short) j(expdescVar2);
        LuaC.a(expdescVar.f1552a == 8 || n(expdescVar.f1552a));
        expdescVar.f1553b.c = (short) (expdescVar.f1552a == 8 ? 8 : 7);
        expdescVar.f1552a = 9;
    }

    private static boolean a(int i, LexState.expdesc expdescVar, LexState.expdesc expdescVar2) {
        LuaValue luaValue;
        if (!expdescVar.b() || !expdescVar2.b()) {
            return false;
        }
        if ((i == 16 || i == 17) && expdescVar2.f1553b.nval().eq_b(LuaValue.ZERO)) {
            return false;
        }
        LuaValue nval = expdescVar.f1553b.nval();
        LuaValue nval2 = expdescVar2.f1553b.nval();
        switch (i) {
            case 13:
                luaValue = nval.add(nval2);
                break;
            case 14:
                luaValue = nval.sub(nval2);
                break;
            case 15:
                luaValue = nval.mul(nval2);
                break;
            case 16:
                luaValue = nval.div(nval2);
                break;
            case 17:
                luaValue = nval.mod(nval2);
                break;
            case 18:
                luaValue = nval.pow(nval2);
                break;
            case 19:
                luaValue = nval.neg();
                break;
            case 20:
            default:
                a(false);
                luaValue = null;
                break;
            case 21:
                return false;
        }
        if (Double.isNaN(luaValue.todouble())) {
            return false;
        }
        expdescVar.f1553b.setNval(luaValue);
        return true;
    }

    private void b(int i, LexState.expdesc expdescVar, LexState.expdesc expdescVar2, int i2) {
        if (a(i, expdescVar, expdescVar2)) {
            return;
        }
        int j = (i == 19 || i == 21) ? 0 : j(expdescVar2);
        int j2 = j(expdescVar);
        if (j2 > j) {
            m(expdescVar);
            m(expdescVar2);
        } else {
            m(expdescVar2);
            m(expdescVar);
        }
        expdescVar.f1553b.d = b(i, 0, j2, j);
        expdescVar.f1552a = 11;
        e(i2);
    }

    private void a(int i, int i2, LexState.expdesc expdescVar, LexState.expdesc expdescVar2) {
        int j = j(expdescVar);
        int j2 = j(expdescVar2);
        m(expdescVar2);
        m(expdescVar);
        if (i2 == 0 && i != 24) {
            j = j2;
            j2 = j;
            i2 = 1;
        }
        expdescVar.f1553b.d = c(i, i2, j, j2);
        expdescVar.f1552a = 10;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(int i, LexState.expdesc expdescVar, int i2) {
        LexState.expdesc expdescVar2 = new LexState.expdesc();
        expdescVar2.a(5, 0);
        switch (i) {
            case 0:
                if (expdescVar.b()) {
                    expdescVar.f1553b.setNval(expdescVar.f1553b.nval().neg());
                    return;
                } else {
                    g(expdescVar);
                    b(19, expdescVar, expdescVar2, i2);
                    return;
                }
            case 1:
                p(expdescVar);
                return;
            case 2:
                g(expdescVar);
                b(21, expdescVar, expdescVar2, i2);
                return;
            default:
                a(false);
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(int i, LexState.expdesc expdescVar) {
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                if (!expdescVar.b()) {
                    j(expdescVar);
                    return;
                }
                return;
            case 6:
                f(expdescVar);
                return;
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            default:
                j(expdescVar);
                return;
            case 13:
                k(expdescVar);
                return;
            case 14:
                l(expdescVar);
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(int i, LexState.expdesc expdescVar, LexState.expdesc expdescVar2, int i2) {
        switch (i) {
            case 0:
                b(13, expdescVar, expdescVar2, i2);
                return;
            case 1:
                b(14, expdescVar, expdescVar2, i2);
                return;
            case 2:
                b(15, expdescVar, expdescVar2, i2);
                return;
            case 3:
                b(16, expdescVar, expdescVar2, i2);
                return;
            case 4:
                b(17, expdescVar, expdescVar2, i2);
                return;
            case 5:
                b(18, expdescVar, expdescVar2, i2);
                return;
            case 6:
                i(expdescVar2);
                if (expdescVar2.f1552a == 11 && GET_OPCODE(b(expdescVar2)) == 22) {
                    a(expdescVar.f1553b.d == GETARG_B(b(expdescVar2)) - 1);
                    m(expdescVar);
                    c(a(expdescVar2), expdescVar.f1553b.d);
                    expdescVar.f1552a = 11;
                    expdescVar.f1553b.d = expdescVar2.f1553b.d;
                    return;
                }
                f(expdescVar2);
                b(22, expdescVar, expdescVar2, i2);
                return;
            case 7:
                a(24, 0, expdescVar, expdescVar2);
                return;
            case 8:
                a(24, 1, expdescVar, expdescVar2);
                return;
            case 9:
                a(25, 1, expdescVar, expdescVar2);
                return;
            case 10:
                a(26, 1, expdescVar, expdescVar2);
                return;
            case 11:
                a(25, 0, expdescVar, expdescVar2);
                return;
            case 12:
                a(26, 0, expdescVar, expdescVar2);
                return;
            case 13:
                a(expdescVar.c.f1534a == -1);
                e(expdescVar2);
                a(expdescVar2.d, expdescVar.d.f1534a);
                expdescVar.setvalue(expdescVar2);
                return;
            case 14:
                a(expdescVar.d.f1534a == -1);
                e(expdescVar2);
                a(expdescVar2.c, expdescVar.c.f1534a);
                expdescVar.setvalue(expdescVar2);
                return;
            default:
                a(false);
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void e(int i) {
        this.f1528a.lineinfo[this.e - 1] = i;
    }

    private int i(int i, int i2) {
        Prototype prototype = this.f1528a;
        d();
        if (prototype.code == null || this.e + 1 > prototype.code.length) {
            prototype.code = LuaC.a(prototype.code, (this.e << 1) + 1);
        }
        prototype.code[this.e] = i;
        if (prototype.lineinfo == null || this.e + 1 > prototype.lineinfo.length) {
            prototype.lineinfo = LuaC.a(prototype.lineinfo, (this.e << 1) + 1);
        }
        prototype.lineinfo[this.e] = i2;
        int i3 = this.e;
        this.e = i3 + 1;
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int b(int i, int i2, int i3, int i4) {
        a(getOpMode(i) == 0);
        a(getBMode(i) != 0 || i3 == 0);
        a(getCMode(i) != 0 || i4 == 0);
        return i(a(i, i2, i3, i4), this.c.f1535a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int c(int i, int i2, int i3) {
        a(getOpMode(i) == 1 || getOpMode(i) == 2);
        a(getCMode(i) == 0);
        a(i3 >= 0 && i3 <= 262143);
        return i(a(i, i2, i3), this.c.f1535a);
    }

    private int o(int i) {
        a(i <= 67108863);
        return i(a(39, i), this.c.f1535a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int f(int i, int i2) {
        if (i2 <= 262143) {
            return c(1, i, i2);
        }
        int c = c(2, i, 0);
        o(i2);
        return c;
    }

    private void e(int i, int i2, int i3) {
        int i4 = ((i2 - 1) / 50) + 1;
        int i5 = i3 == -1 ? 0 : i3;
        a(i3 != 0);
        if (i4 <= 511) {
            b(36, i, i5, i4);
        } else {
            b(36, i, i5, 0);
            i(i4, this.c.f1535a);
        }
        this.n = (short) (i + 1);
    }
}
