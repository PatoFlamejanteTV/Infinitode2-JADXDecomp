package com.prineside.luaj.lib;

import com.prineside.luaj.LuaTable;
import com.prineside.luaj.LuaValue;
import com.prineside.luaj.Varargs;
import com.prineside.tdi2.utils.REGS;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;

/* loaded from: infinitode-2.jar:com/prineside/luaj/lib/TableLib.class */
public class TableLib extends TwoArgFunction {
    @Override // com.prineside.luaj.lib.TwoArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
    public LuaValue call(LuaValue luaValue, LuaValue luaValue2) {
        LuaTable luaTable = new LuaTable();
        luaTable.set("concat", new concat());
        luaTable.set("insert", new insert());
        luaTable.set("pack", new pack());
        luaTable.set("remove", new remove());
        luaTable.set("sort", new sort());
        luaTable.set("unpack", new unpack());
        luaValue2.set(FlexmarkHtmlConverter.TABLE_NODE, luaTable);
        if (!luaValue2.get("package").isnil()) {
            luaValue2.get("package").get("loaded").set(FlexmarkHtmlConverter.TABLE_NODE, luaTable);
        }
        return NIL;
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/TableLib$concat.class */
    public static class concat extends TableLibFunction {
        @Override // com.prineside.luaj.lib.TableLibFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public /* bridge */ /* synthetic */ LuaValue call() {
            return super.call();
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public LuaValue call(LuaValue luaValue) {
            return luaValue.checktable().concat(EMPTYSTRING, 1, luaValue.length());
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public LuaValue call(LuaValue luaValue, LuaValue luaValue2) {
            return luaValue.checktable().concat(luaValue2.checkstring(), 1, luaValue.length());
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public LuaValue call(LuaValue luaValue, LuaValue luaValue2, LuaValue luaValue3) {
            return luaValue.checktable().concat(luaValue2.checkstring(), luaValue3.checkint(), luaValue.length());
        }

        @Override // com.prineside.luaj.lib.LibFunction
        public LuaValue call(LuaValue luaValue, LuaValue luaValue2, LuaValue luaValue3, LuaValue luaValue4) {
            return luaValue.checktable().concat(luaValue2.checkstring(), luaValue3.checkint(), luaValue4.checkint());
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/TableLib$insert.class */
    public static class insert extends VarArgFunction {
        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public Varargs invoke(Varargs varargs) {
            switch (varargs.narg()) {
                case 2:
                    LuaTable checktable = varargs.checktable(1);
                    checktable.insert(checktable.length() + 1, varargs.arg(2));
                    return NONE;
                case 3:
                    LuaTable checktable2 = varargs.checktable(1);
                    int checkint = varargs.checkint(2);
                    int length = checktable2.length() + 1;
                    if (checkint <= 0 || checkint > length) {
                        argerror(2, "position out of bounds: " + checkint + " not between 1 and " + length);
                    }
                    checktable2.insert(checkint, varargs.arg(3));
                    return NONE;
                default:
                    return error("wrong number of arguments to 'table.insert': " + varargs.narg() + " (must be 2 or 3)");
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/TableLib$pack.class */
    public static class pack extends VarArgFunction {
        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public Varargs invoke(Varargs varargs) {
            LuaTable tableOf = tableOf(varargs, 1);
            tableOf.set("n", varargs.narg());
            return tableOf;
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/TableLib$remove.class */
    public static class remove extends VarArgFunction {
        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public Varargs invoke(Varargs varargs) {
            LuaTable checktable = varargs.checktable(1);
            int length = checktable.length();
            int optint = varargs.optint(2, length);
            if (optint != length && (optint <= 0 || optint > length + 1)) {
                argerror(2, "position out of bounds: " + optint + " not between 1 and " + (length + 1));
            }
            return checktable.remove(optint);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/TableLib$sort.class */
    public static class sort extends VarArgFunction {
        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public Varargs invoke(Varargs varargs) {
            varargs.checktable(1).sort(varargs.isnil(2) ? NIL : varargs.checkfunction(2));
            return NONE;
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/TableLib$unpack.class */
    public static class unpack extends VarArgFunction {
        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public Varargs invoke(Varargs varargs) {
            LuaTable checktable = varargs.checktable(1);
            return checktable.unpack(varargs.optint(2, 1), varargs.optint(3, varargs.arg(3).isnil() ? checktable.length() : 0));
        }
    }
}
