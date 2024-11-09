package com.prineside.luaj.compiler;

import com.prineside.luaj.LocVars;
import com.prineside.luaj.Lua;
import com.prineside.luaj.LuaError;
import com.prineside.luaj.LuaValue;
import com.prineside.luaj.Prototype;
import com.prineside.luaj.Upvaldesc;
import com.prineside.luaj.compiler.LexState;

/* loaded from: infinitode-2.jar:com/prineside/luaj/compiler/Constants.class */
public class Constants extends Lua {
    public static final int MAXSTACK = 150;

    /* JADX INFO: Access modifiers changed from: protected */
    public static void a(boolean z) {
        if (!z) {
            throw new LuaError("compiler assert failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(InstructionPtr instructionPtr, int i) {
        instructionPtr.a((instructionPtr.a() & (-64)) | 30);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(int[] iArr, int i, int i2) {
        iArr[i] = (iArr[i] & Lua.MASK_NOT_A) | ((i2 << 6) & Lua.MASK_A);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(InstructionPtr instructionPtr, int i) {
        instructionPtr.a((instructionPtr.a() & Lua.MASK_NOT_A) | ((i << 6) & Lua.MASK_A));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void c(InstructionPtr instructionPtr, int i) {
        instructionPtr.a((instructionPtr.a() & Lua.MASK_NOT_B) | ((i << 23) & Lua.MASK_B));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void d(InstructionPtr instructionPtr, int i) {
        instructionPtr.a((instructionPtr.a() & Lua.MASK_NOT_C) | ((i << 14) & Lua.MASK_C));
    }

    private static void f(InstructionPtr instructionPtr, int i) {
        instructionPtr.a((instructionPtr.a() & Lua.MASK_NOT_Bx) | ((i << 14) & Lua.MASK_Bx));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void e(InstructionPtr instructionPtr, int i) {
        f(instructionPtr, i + Lua.MAXARG_sBx);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(int i, int i2, int i3, int i4) {
        return (i & 63) | ((i2 << 6) & Lua.MASK_A) | ((i3 << 23) & Lua.MASK_B) | ((i4 << 14) & Lua.MASK_C);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(int i, int i2, int i3) {
        return (i & 63) | ((i2 << 6) & Lua.MASK_A) | ((i3 << 14) & Lua.MASK_Bx);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(int i, int i2) {
        return 39 | ((i2 << 6) & (-64));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static LuaValue[] a(LuaValue[] luaValueArr, int i) {
        LuaValue[] luaValueArr2 = new LuaValue[i];
        if (luaValueArr != null) {
            System.arraycopy(luaValueArr, 0, luaValueArr2, 0, Math.min(luaValueArr.length, i));
        }
        return luaValueArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Prototype[] a(Prototype[] prototypeArr, int i) {
        Prototype[] prototypeArr2 = new Prototype[i];
        if (prototypeArr != null) {
            System.arraycopy(prototypeArr, 0, prototypeArr2, 0, Math.min(prototypeArr.length, i));
        }
        return prototypeArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static LocVars[] a(LocVars[] locVarsArr, int i) {
        LocVars[] locVarsArr2 = new LocVars[i];
        if (locVarsArr != null) {
            System.arraycopy(locVarsArr, 0, locVarsArr2, 0, Math.min(locVarsArr.length, i));
        }
        return locVarsArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Upvaldesc[] a(Upvaldesc[] upvaldescArr, int i) {
        Upvaldesc[] upvaldescArr2 = new Upvaldesc[i];
        if (upvaldescArr != null) {
            System.arraycopy(upvaldescArr, 0, upvaldescArr2, 0, Math.min(upvaldescArr.length, i));
        }
        return upvaldescArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static LexState.Vardesc[] a(LexState.Vardesc[] vardescArr, int i) {
        LexState.Vardesc[] vardescArr2 = new LexState.Vardesc[i];
        if (vardescArr != null) {
            System.arraycopy(vardescArr, 0, vardescArr2, 0, Math.min(vardescArr.length, i));
        }
        return vardescArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static LexState.Labeldesc[] a(LexState.Labeldesc[] labeldescArr, int i) {
        return labeldescArr == null ? new LexState.Labeldesc[2] : labeldescArr.length < i ? b(labeldescArr, labeldescArr.length << 1) : labeldescArr;
    }

    private static LexState.Labeldesc[] b(LexState.Labeldesc[] labeldescArr, int i) {
        LexState.Labeldesc[] labeldescArr2 = new LexState.Labeldesc[i];
        if (labeldescArr != null) {
            System.arraycopy(labeldescArr, 0, labeldescArr2, 0, Math.min(labeldescArr.length, i));
        }
        return labeldescArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int[] a(int[] iArr, int i) {
        int[] iArr2 = new int[i];
        if (iArr != null) {
            System.arraycopy(iArr, 0, iArr2, 0, Math.min(iArr.length, i));
        }
        return iArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static char[] a(char[] cArr, int i) {
        char[] cArr2 = new char[i];
        if (cArr != null) {
            System.arraycopy(cArr, 0, cArr2, 0, Math.min(cArr.length, i));
        }
        return cArr2;
    }
}
