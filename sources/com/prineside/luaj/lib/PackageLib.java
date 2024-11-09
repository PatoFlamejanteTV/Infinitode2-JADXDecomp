package com.prineside.luaj.lib;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.luaj.Globals;
import com.prineside.luaj.LuaBoolean;
import com.prineside.luaj.LuaString;
import com.prineside.luaj.LuaTable;
import com.prineside.luaj.LuaValue;
import com.prineside.luaj.Varargs;
import com.prineside.tdi2.utils.REGS;
import java.io.IOException;
import java.io.InputStream;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/luaj/lib/PackageLib.class */
public class PackageLib extends TwoArgFunction implements KryoSerializable {
    public static final String DEFAULT_LUA_PATH = "?.lua";
    Globals h;
    LuaTable i;
    public preload_searcher preload_searcher;
    public lua_searcher lua_searcher;

    /* renamed from: a, reason: collision with root package name */
    static final LuaString f1589a = valueOf("loaded");
    private static final LuaString j = valueOf("loadlib");

    /* renamed from: b, reason: collision with root package name */
    static final LuaString f1590b = valueOf("preload");
    static final LuaString e = valueOf("path");
    static final LuaString f = valueOf("searchpath");
    static final LuaString g = valueOf("searchers");
    private static final LuaString k = valueOf("\u0001");
    private static final String l = System.getProperty("file.separator");

    @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        kryo.writeClassAndObject(output, this.h);
        kryo.writeClassAndObject(output, this.i);
        kryo.writeClassAndObject(output, this.preload_searcher);
        kryo.writeClassAndObject(output, this.lua_searcher);
    }

    @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        this.h = (Globals) kryo.readClassAndObject(input);
        this.i = (LuaTable) kryo.readClassAndObject(input);
        this.preload_searcher = (preload_searcher) kryo.readClassAndObject(input);
        this.lua_searcher = (lua_searcher) kryo.readClassAndObject(input);
    }

    @Override // com.prineside.luaj.lib.TwoArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
    public LuaValue call(LuaValue luaValue, LuaValue luaValue2) {
        this.h = luaValue2.checkglobals();
        this.h.set("require", new require(this, (byte) 0));
        this.i = new LuaTable();
        this.i.set(f1589a, new LuaTable());
        this.i.set(f1590b, new LuaTable());
        this.i.set(e, LuaValue.valueOf(DEFAULT_LUA_PATH));
        this.i.set(j, new loadlib());
        this.i.set(f, new searchpath(this, (byte) 0));
        LuaValue luaTable = new LuaTable();
        preload_searcher preload_searcherVar = new preload_searcher(this, (byte) 0);
        this.preload_searcher = preload_searcherVar;
        luaTable.set(1, preload_searcherVar);
        lua_searcher lua_searcherVar = new lua_searcher(this, (byte) 0);
        this.lua_searcher = lua_searcherVar;
        luaTable.set(2, lua_searcherVar);
        this.i.set(g, luaTable);
        this.i.set("config", l + "\n;\n?\n!\n-\n");
        this.i.get(f1589a).set("package", this.i);
        luaValue2.set("package", this.i);
        this.h.package_ = this;
        return luaValue2;
    }

    public void setIsLoaded(String str, LuaTable luaTable) {
        this.i.get(f1589a).set(str, luaTable);
    }

    public void setLuaPath(String str) {
        this.i.set(e, LuaValue.valueOf(str));
    }

    @Override // com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaFunction, com.prineside.luaj.LuaValue, com.prineside.luaj.Varargs
    public final String tojstring() {
        return "package";
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/PackageLib$require.class */
    public static class require extends OneArgFunction implements KryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private PackageLib f1593a;

        /* synthetic */ require(PackageLib packageLib, byte b2) {
            this(packageLib);
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
        public void write(Kryo kryo, Output output) {
            kryo.writeObject(output, this.f1593a);
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
        public void read(Kryo kryo, Input input) {
            this.f1593a = (PackageLib) kryo.readObject(input, PackageLib.class);
        }

        private require() {
        }

        private require(PackageLib packageLib) {
            this.f1593a = packageLib;
        }

        @Override // com.prineside.luaj.lib.OneArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public LuaValue call(LuaValue luaValue) {
            Varargs invoke;
            LuaString checkstring = luaValue.checkstring();
            LuaValue luaValue2 = this.f1593a.i.get(PackageLib.f1589a);
            LuaValue luaValue3 = luaValue2.get(checkstring);
            if (luaValue3.toboolean()) {
                if (luaValue3 == PackageLib.k) {
                    error("loop or previous error loading module '" + checkstring + "'");
                }
                return luaValue3;
            }
            LuaTable checktable = this.f1593a.i.get(PackageLib.g).checktable();
            StringBuilder sb = new StringBuilder();
            int i = 1;
            while (true) {
                LuaValue luaValue4 = checktable.get(i);
                if (luaValue4.isnil()) {
                    error("module '" + checkstring + "' not found: " + checkstring + ((Object) sb));
                }
                invoke = luaValue4.invoke(checkstring);
                if (invoke.isfunction(1)) {
                    break;
                }
                if (invoke.isstring(1)) {
                    sb.append(invoke.tojstring(1));
                }
                i++;
            }
            luaValue2.set(checkstring, PackageLib.k);
            LuaValue call = invoke.arg1().call(checkstring, invoke.arg(2));
            LuaValue luaValue5 = call;
            if (!call.isnil()) {
                luaValue2.set(checkstring, luaValue5);
            } else {
                LuaValue luaValue6 = luaValue2.get(checkstring);
                luaValue5 = luaValue6;
                if (luaValue6 == PackageLib.k) {
                    LuaBoolean luaBoolean = LuaValue.TRUE;
                    luaValue5 = luaBoolean;
                    luaValue2.set(checkstring, luaBoolean);
                }
            }
            return luaValue5;
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/PackageLib$loadlib.class */
    public static class loadlib extends VarArgFunction {
        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public Varargs invoke(Varargs varargs) {
            varargs.checkstring(1);
            return LuaValue.varargsOf(NIL, valueOf("dynamic libraries not enabled"), valueOf("absent"));
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/PackageLib$preload_searcher.class */
    public static class preload_searcher extends VarArgFunction implements KryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private PackageLib f1592a;

        /* synthetic */ preload_searcher(PackageLib packageLib, byte b2) {
            this(packageLib);
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
        public void write(Kryo kryo, Output output) {
            kryo.writeObject(output, this.f1592a);
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
        public void read(Kryo kryo, Input input) {
            this.f1592a = (PackageLib) kryo.readObject(input, PackageLib.class);
        }

        private preload_searcher() {
        }

        private preload_searcher(PackageLib packageLib) {
            this.f1592a = packageLib;
        }

        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public Varargs invoke(Varargs varargs) {
            LuaString checkstring = varargs.checkstring(1);
            LuaValue luaValue = this.f1592a.i.get(PackageLib.f1590b).get(checkstring);
            if (luaValue.isnil()) {
                return valueOf("\n\tno field package.preload['" + checkstring + "']");
            }
            return luaValue;
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/PackageLib$lua_searcher.class */
    public static class lua_searcher extends VarArgFunction implements KryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private PackageLib f1591a;

        /* synthetic */ lua_searcher(PackageLib packageLib, byte b2) {
            this(packageLib);
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
        public void write(Kryo kryo, Output output) {
            kryo.writeObject(output, this.f1591a);
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
        public void read(Kryo kryo, Input input) {
            this.f1591a = (PackageLib) kryo.readObject(input, PackageLib.class);
        }

        private lua_searcher() {
        }

        private lua_searcher(PackageLib packageLib) {
            this.f1591a = packageLib;
        }

        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public Varargs invoke(Varargs varargs) {
            LuaString checkstring = varargs.checkstring(1);
            LuaValue luaValue = this.f1591a.i.get(PackageLib.e);
            if (luaValue.isstring()) {
                Varargs invoke = this.f1591a.i.get(PackageLib.f).invoke(varargsOf(checkstring, luaValue));
                if (!invoke.isstring(1)) {
                    return invoke.arg(2).tostring();
                }
                LuaString strvalue = invoke.arg1().strvalue();
                LuaValue loadfile = this.f1591a.h.loadfile(strvalue.tojstring());
                if (!loadfile.arg1().isfunction()) {
                    return varargsOf(NIL, valueOf("'" + strvalue + "': " + loadfile.arg(2).tojstring()));
                }
                return varargsOf(loadfile.arg1(), strvalue);
            }
            return valueOf("package.path is not a string");
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/PackageLib$searchpath.class */
    public static class searchpath extends VarArgFunction implements KryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private PackageLib f1594a;

        /* synthetic */ searchpath(PackageLib packageLib, byte b2) {
            this(packageLib);
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
        public void write(Kryo kryo, Output output) {
            kryo.writeObject(output, this.f1594a);
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
        public void read(Kryo kryo, Input input) {
            this.f1594a = (PackageLib) kryo.readObject(input, PackageLib.class);
        }

        private searchpath() {
        }

        private searchpath(PackageLib packageLib) {
            this.f1594a = packageLib;
        }

        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public Varargs invoke(Varargs varargs) {
            String checkjstring = varargs.checkjstring(1);
            String checkjstring2 = varargs.checkjstring(2);
            String optjstring = varargs.optjstring(3, ".");
            String optjstring2 = varargs.optjstring(4, PackageLib.l);
            int i = -1;
            int length = checkjstring2.length();
            StringBuffer stringBuffer = null;
            String replace = checkjstring.replace(optjstring.charAt(0), optjstring2.charAt(0));
            while (i < length) {
                int i2 = i + 1;
                int indexOf = checkjstring2.indexOf(59, i2);
                i = indexOf;
                if (indexOf < 0) {
                    i = checkjstring2.length();
                }
                String substring = checkjstring2.substring(i2, i);
                int indexOf2 = substring.indexOf(63);
                String str = substring;
                if (indexOf2 >= 0) {
                    str = substring.substring(0, indexOf2) + replace + substring.substring(indexOf2 + 1);
                }
                InputStream findResource = this.f1594a.h.finder.findResource(str);
                if (findResource != null) {
                    try {
                        findResource.close();
                    } catch (IOException unused) {
                    }
                    return valueOf(str);
                }
                if (stringBuffer == null) {
                    stringBuffer = new StringBuffer();
                }
                stringBuffer.append("\n\t" + str);
            }
            return varargsOf(NIL, valueOf(stringBuffer.toString()));
        }
    }
}
