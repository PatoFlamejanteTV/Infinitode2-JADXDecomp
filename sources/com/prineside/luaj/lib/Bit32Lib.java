package com.prineside.luaj.lib;

import com.prineside.luaj.LuaTable;
import com.prineside.luaj.LuaValue;
import com.prineside.luaj.Varargs;
import com.prineside.tdi2.utils.REGS;

/* loaded from: infinitode-2.jar:com/prineside/luaj/lib/Bit32Lib.class */
public class Bit32Lib extends TwoArgFunction {
    @Override // com.prineside.luaj.lib.TwoArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
    public LuaValue call(LuaValue luaValue, LuaValue luaValue2) {
        LuaTable luaTable = new LuaTable();
        a(luaTable, Bit32LibV.class, new String[]{"band", "bnot", "bor", "btest", "bxor", "extract", "replace"});
        a(luaTable, Bit32Lib2.class, new String[]{"arshift", "lrotate", "lshift", "rrotate", "rshift"});
        luaValue2.set("bit32", luaTable);
        if (!luaValue2.get("package").isnil()) {
            luaValue2.get("package").get("loaded").set("bit32", luaTable);
        }
        return luaTable;
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/Bit32Lib$Bit32LibV.class */
    public static final class Bit32LibV extends VarArgFunction {
        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final Varargs invoke(Varargs varargs) {
            switch (this.c) {
                case 0:
                    return Bit32Lib.a(varargs);
                case 1:
                    return Bit32Lib.b(varargs);
                case 2:
                    return Bit32Lib.c(varargs);
                case 3:
                    return Bit32Lib.d(varargs);
                case 4:
                    return Bit32Lib.e(varargs);
                case 5:
                    return Bit32Lib.a(varargs.checkint(1), varargs.checkint(2), varargs.optint(3, 1));
                case 6:
                    return Bit32Lib.a(varargs.checkint(1), varargs.checkint(2), varargs.checkint(3), varargs.optint(4, 1));
                default:
                    return NIL;
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/Bit32Lib$Bit32Lib2.class */
    public static final class Bit32Lib2 extends TwoArgFunction {
        @Override // com.prineside.luaj.lib.TwoArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final LuaValue call(LuaValue luaValue, LuaValue luaValue2) {
            switch (this.c) {
                case 0:
                    return Bit32Lib.a(luaValue.checkint(), luaValue2.checkint());
                case 1:
                    return Bit32Lib.d(luaValue.checkint(), luaValue2.checkint());
                case 2:
                    return Bit32Lib.c(luaValue.checkint(), luaValue2.checkint());
                case 3:
                    return Bit32Lib.e(luaValue.checkint(), luaValue2.checkint());
                case 4:
                    return Bit32Lib.b(luaValue.checkint(), luaValue2.checkint());
                default:
                    return NIL;
            }
        }
    }

    static LuaValue a(int i, int i2) {
        if (i2 >= 0) {
            return a(i >> i2);
        }
        return a(i << (-i2));
    }

    static LuaValue b(int i, int i2) {
        if (i2 >= 32 || i2 <= -32) {
            return ZERO;
        }
        if (i2 >= 0) {
            return a(i >>> i2);
        }
        return a(i << (-i2));
    }

    static LuaValue c(int i, int i2) {
        if (i2 >= 32 || i2 <= -32) {
            return ZERO;
        }
        if (i2 >= 0) {
            return a(i << i2);
        }
        return a(i >>> (-i2));
    }

    static Varargs a(Varargs varargs) {
        int i = -1;
        for (int i2 = 1; i2 <= varargs.narg(); i2++) {
            i &= varargs.checkint(i2);
        }
        return a(i);
    }

    static Varargs b(Varargs varargs) {
        return a(varargs.checkint(1) ^ (-1));
    }

    static Varargs c(Varargs varargs) {
        int i = 0;
        for (int i2 = 1; i2 <= varargs.narg(); i2++) {
            i |= varargs.checkint(i2);
        }
        return a(i);
    }

    static Varargs d(Varargs varargs) {
        int i = -1;
        for (int i2 = 1; i2 <= varargs.narg(); i2++) {
            i &= varargs.checkint(i2);
        }
        return valueOf(i != 0);
    }

    static Varargs e(Varargs varargs) {
        int i = 0;
        for (int i2 = 1; i2 <= varargs.narg(); i2++) {
            i ^= varargs.checkint(i2);
        }
        return a(i);
    }

    static LuaValue d(int i, int i2) {
        if (i2 < 0) {
            return e(i, -i2);
        }
        int i3 = i2 & 31;
        return a((i << i3) | (i >>> (32 - i3)));
    }

    static LuaValue e(int i, int i2) {
        if (i2 < 0) {
            return d(i, -i2);
        }
        int i3 = i2 & 31;
        return a((i >>> i3) | (i << (32 - i3)));
    }

    static LuaValue a(int i, int i2, int i3) {
        if (i2 < 0) {
            argerror(2, "field cannot be negative");
        }
        if (i3 < 0) {
            argerror(3, "width must be postive");
        }
        if (i2 + i3 > 32) {
            error("trying to access non-existent bits");
        }
        return a((i >>> i2) & ((-1) >>> (32 - i3)));
    }

    static LuaValue a(int i, int i2, int i3, int i4) {
        if (i3 < 0) {
            argerror(3, "field cannot be negative");
        }
        if (i4 < 0) {
            argerror(4, "width must be postive");
        }
        if (i3 + i4 > 32) {
            error("trying to access non-existent bits");
        }
        int i5 = ((-1) >>> (32 - i4)) << i3;
        return a((i & (i5 ^ (-1))) | ((i2 << i3) & i5));
    }

    private static LuaValue a(int i) {
        return i < 0 ? valueOf(i & 4294967295L) : valueOf(i);
    }
}
