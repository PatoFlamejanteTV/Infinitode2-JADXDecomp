package com.prineside.luaj.lib;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.luaj.Globals;
import com.prineside.luaj.Lua;
import com.prineside.luaj.LuaBoolean;
import com.prineside.luaj.LuaError;
import com.prineside.luaj.LuaString;
import com.prineside.luaj.LuaTable;
import com.prineside.luaj.LuaValue;
import com.prineside.luaj.Varargs;
import com.prineside.luaj.debug.CallStack;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;
import java.io.InputStream;

/* loaded from: infinitode-2.jar:com/prineside/luaj/lib/BaseLib.class */
public abstract class BaseLib extends TwoArgFunction implements KryoSerializable, ResourceFinder {

    /* renamed from: b, reason: collision with root package name */
    private static final TLog f1564b = TLog.forClass(BaseLib.class);

    /* renamed from: a, reason: collision with root package name */
    Globals f1565a;

    @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        kryo.writeObjectOrNull(output, this.f1565a, Globals.class);
    }

    @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        this.f1565a = (Globals) kryo.readObjectOrNull(input, Globals.class);
    }

    @Override // com.prineside.luaj.lib.TwoArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
    public LuaValue call(LuaValue luaValue, LuaValue luaValue2) {
        this.f1565a = luaValue2.checkglobals();
        this.f1565a.finder = this;
        this.f1565a.baselib = this;
        luaValue2.set("_G", luaValue2);
        luaValue2.set("_VERSION", Lua._VERSION);
        luaValue2.set("_LUAJ_VERSION", Lua._LUAJ_VERSION);
        luaValue2.set("assert", new _assert());
        luaValue2.set("collectgarbage", new collectgarbage());
        luaValue2.set("dofile", new dofile(this, (byte) 0));
        luaValue2.set("error", new error());
        luaValue2.set("getmetatable", new getmetatable());
        luaValue2.set("load", new load(this, (byte) 0));
        luaValue2.set("loadfile", new loadfile(this, (byte) 0));
        luaValue2.set("pcall", new pcall(this, (byte) 0));
        luaValue2.set("print", new print(this));
        luaValue2.set("rawequal", new rawequal());
        luaValue2.set("rawget", new rawget());
        luaValue2.set("rawlen", new rawlen());
        luaValue2.set("rawset", new rawset());
        luaValue2.set("select", new select());
        luaValue2.set("setmetatable", new setmetatable());
        luaValue2.set("tonumber", new tonumber());
        luaValue2.set("tostring", new tostring());
        luaValue2.set("type", new type());
        luaValue2.set("xpcall", new xpcall(this, (byte) 0));
        next nextVar = new next();
        luaValue2.set("next", nextVar);
        luaValue2.set("pairs", new pairs(nextVar));
        luaValue2.set("ipairs", new ipairs());
        return luaValue2;
    }

    @Override // com.prineside.luaj.lib.ResourceFinder
    public InputStream findResource(String str) {
        if (str.contains("..")) {
            f1564b.e("filename should not contain ../", new Object[0]);
            return null;
        }
        FileHandle local = Gdx.files.local(str);
        FileHandle fileHandle = local;
        if (!local.exists()) {
            fileHandle = Gdx.files.internal(str);
        }
        if (!fileHandle.exists()) {
            return null;
        }
        if (fileHandle.isDirectory()) {
            f1564b.e("Cannot open a stream to a directory: " + str, new Object[0]);
            return null;
        }
        return fileHandle.read();
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/BaseLib$_assert.class */
    public static final class _assert extends VarArgFunction {
        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final Varargs invoke(Varargs varargs) {
            if (!varargs.arg1().toboolean()) {
                error(varargs.narg() > 1 ? varargs.optjstring(2, "assertion failed!") : "assertion failed!");
            }
            return varargs;
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/BaseLib$collectgarbage.class */
    public static final class collectgarbage extends VarArgFunction {
        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final Varargs invoke(Varargs varargs) {
            String optjstring = varargs.optjstring(1, "collect");
            if ("collect".equals(optjstring)) {
                System.gc();
                return ZERO;
            }
            if ("count".equals(optjstring)) {
                Runtime runtime = Runtime.getRuntime();
                return varargsOf(valueOf((runtime.totalMemory() - runtime.freeMemory()) / 1024.0d), valueOf(r0 % 1024));
            }
            if ("step".equals(optjstring)) {
                System.gc();
                return LuaValue.TRUE;
            }
            if ("stop".equals(optjstring) || "restart".equals(optjstring) || "setpause".equals(optjstring) || "setstepmul".equals(optjstring)) {
                BaseLib.f1564b.i("collectgarbage option " + optjstring + " is ignored - Java's GC is used. Only 'collect' (System.gc()) and 'count' (total - free memory of Java) options are available", new Object[0]);
                return LuaValue.TRUE;
            }
            argerror(1, "invalid option '" + optjstring + "'");
            return NIL;
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/BaseLib$dofile.class */
    public static final class dofile extends VarArgFunction implements KryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private BaseLib f1568a;

        /* synthetic */ dofile(BaseLib baseLib, byte b2) {
            this(baseLib);
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
        public final void write(Kryo kryo, Output output) {
            kryo.writeClassAndObject(output, this.f1568a);
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
        public final void read(Kryo kryo, Input input) {
            this.f1568a = (BaseLib) kryo.readClassAndObject(input);
        }

        private dofile() {
        }

        private dofile(BaseLib baseLib) {
            if (baseLib == null) {
                throw new IllegalArgumentException("baseLib cannot be null");
            }
            this.f1568a = baseLib;
        }

        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final Varargs invoke(Varargs varargs) {
            Varargs loadFile;
            varargs.argcheck(varargs.isstring(1) || varargs.isnil(1), 1, "filename must be string or nil");
            if ((varargs.isstring(1) ? varargs.tojstring(1) : null) == null) {
                loadFile = this.f1568a.loadStream(this.f1568a.f1565a.STDIN, "=stdin", "bt", this.f1568a.f1565a);
            } else {
                loadFile = this.f1568a.loadFile(varargs.checkjstring(1), "bt", this.f1568a.f1565a);
            }
            Varargs varargs2 = loadFile;
            return loadFile.isnil(1) ? error(varargs2.tojstring(2)) : varargs2.arg1().invoke();
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/BaseLib$error.class */
    public static final class error extends TwoArgFunction {
        @Override // com.prineside.luaj.lib.TwoArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final LuaValue call(LuaValue luaValue, LuaValue luaValue2) {
            if (luaValue.isnil()) {
                throw new LuaError(NIL);
            }
            if (!luaValue.isstring() || luaValue2.optint(1) == 0) {
                throw new LuaError(luaValue);
            }
            throw new LuaError(luaValue.tojstring(), luaValue2.optint(1));
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/BaseLib$getmetatable.class */
    public static final class getmetatable extends LibFunction {
        @Override // com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final LuaValue call() {
            return argerror(1, "value expected");
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final LuaValue call(LuaValue luaValue) {
            LuaValue luaValue2 = luaValue.getmetatable();
            return luaValue2 != null ? luaValue2.rawget(METATABLE).optvalue(luaValue2) : NIL;
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/BaseLib$load.class */
    public static final class load extends VarArgFunction implements KryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private BaseLib f1570a;

        /* synthetic */ load(BaseLib baseLib, byte b2) {
            this(baseLib);
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
        public final void write(Kryo kryo, Output output) {
            kryo.writeClassAndObject(output, this.f1570a);
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
        public final void read(Kryo kryo, Input input) {
            this.f1570a = (BaseLib) kryo.readClassAndObject(input);
        }

        private load() {
        }

        private load(BaseLib baseLib) {
            if (baseLib == null) {
                throw new IllegalArgumentException("baseLib cannot be null");
            }
            this.f1570a = baseLib;
        }

        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final Varargs invoke(Varargs varargs) {
            InputStream stringInputStream;
            LuaValue arg1 = varargs.arg1();
            if (!arg1.isstring() && !arg1.isfunction()) {
                throw new LuaError("bad argument #1 to 'load' (string or function expected, got " + arg1.typename() + ")");
            }
            String optjstring = varargs.optjstring(2, arg1.isstring() ? arg1.tojstring() : "=(load)");
            String optjstring2 = varargs.optjstring(3, "bt");
            LuaValue optvalue = varargs.optvalue(4, this.f1570a.f1565a);
            BaseLib baseLib = this.f1570a;
            if (arg1.isstring()) {
                stringInputStream = arg1.strvalue().toInputStream();
            } else {
                stringInputStream = new StringInputStream(arg1.checkfunction());
            }
            return baseLib.loadStream(stringInputStream, optjstring, optjstring2, optvalue);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/BaseLib$loadfile.class */
    public static final class loadfile extends VarArgFunction implements KryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private BaseLib f1571a;

        /* synthetic */ loadfile(BaseLib baseLib, byte b2) {
            this(baseLib);
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
        public final void write(Kryo kryo, Output output) {
            kryo.writeClassAndObject(output, this.f1571a);
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
        public final void read(Kryo kryo, Input input) {
            this.f1571a = (BaseLib) kryo.readClassAndObject(input);
        }

        private loadfile() {
        }

        private loadfile(BaseLib baseLib) {
            this.f1571a = baseLib;
        }

        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final Varargs invoke(Varargs varargs) {
            varargs.argcheck(varargs.isstring(1) || varargs.isnil(1), 1, "filename must be string or nil");
            String str = varargs.isstring(1) ? varargs.tojstring(1) : null;
            String optjstring = varargs.optjstring(2, "bt");
            LuaValue optvalue = varargs.optvalue(3, this.f1571a.f1565a);
            if (str == null) {
                return this.f1571a.loadStream(this.f1571a.f1565a.STDIN, "=stdin", optjstring, optvalue);
            }
            return this.f1571a.loadFile(str, optjstring, optvalue);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/BaseLib$pcall.class */
    public static final class pcall extends VarArgFunction implements KryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private BaseLib f1573a;

        /* synthetic */ pcall(BaseLib baseLib, byte b2) {
            this(baseLib);
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
        public final void write(Kryo kryo, Output output) {
            kryo.writeClassAndObject(output, this.f1573a);
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
        public final void read(Kryo kryo, Input input) {
            this.f1573a = (BaseLib) kryo.readClassAndObject(input);
        }

        private pcall() {
        }

        private pcall(BaseLib baseLib) {
            this.f1573a = baseLib;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final Varargs invoke(Varargs varargs) {
            LuaValue checkvalue = varargs.checkvalue(1);
            CallStack callStack = CallStack.DUMMY;
            Globals globals = this.f1573a.f1565a;
            LuaError luaError = globals;
            if (globals != null) {
                CallStack callstack = this.f1573a.f1565a.getCallstack();
                callStack = callstack;
                callstack.onCall(this);
                luaError = callstack;
            }
            try {
                try {
                    Varargs invoke = checkvalue.invoke(varargs.subargs(2));
                    if (invoke.narg() == 0) {
                        LuaBoolean luaBoolean = TRUE;
                        callStack.onReturn();
                        return luaBoolean;
                    }
                    Varargs varargsOf = varargsOf(TRUE, invoke);
                    callStack.onReturn();
                    return varargsOf;
                } catch (LuaError e) {
                    LuaValue messageObject = luaError.getMessageObject();
                    Varargs varargsOf2 = varargsOf(FALSE, messageObject != null ? messageObject : NIL);
                    callStack.onReturn();
                    return varargsOf2;
                } catch (Exception e2) {
                    String message = luaError.getMessage();
                    Varargs varargsOf3 = varargsOf(FALSE, valueOf(message != null ? message : e2.toString()));
                    callStack.onReturn();
                    return varargsOf3;
                }
            } catch (Throwable th) {
                callStack.onReturn();
                throw th;
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/BaseLib$print.class */
    public static final class print extends VarArgFunction implements KryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private BaseLib f1574a;

        @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
        public final void write(Kryo kryo, Output output) {
            kryo.writeClassAndObject(output, this.f1574a);
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
        public final void read(Kryo kryo, Input input) {
            this.f1574a = (BaseLib) kryo.readClassAndObject(input);
        }

        private print() {
        }

        print(BaseLib baseLib) {
            this.f1574a = baseLib;
        }

        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final Varargs invoke(Varargs varargs) {
            StringBuilder sb = new StringBuilder();
            LuaValue luaValue = this.f1574a.f1565a.get("tostring");
            int narg = varargs.narg();
            for (int i = 1; i <= narg; i++) {
                if (i > 1) {
                    sb.append("\t");
                }
                sb.append(luaValue.call(varargs.arg(i)).strvalue().tojstring());
            }
            TLog.forTag("Script_Print").i(sb.toString(), new Object[0]);
            return NONE;
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/BaseLib$rawequal.class */
    public static final class rawequal extends LibFunction {
        @Override // com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final LuaValue call() {
            return argerror(1, "value expected");
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final LuaValue call(LuaValue luaValue) {
            return argerror(2, "value expected");
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final LuaValue call(LuaValue luaValue, LuaValue luaValue2) {
            return valueOf(luaValue.raweq(luaValue2));
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/BaseLib$rawget.class */
    public static final class rawget extends TableLibFunction {
        @Override // com.prineside.luaj.lib.TableLibFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final /* bridge */ /* synthetic */ LuaValue call() {
            return super.call();
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final LuaValue call(LuaValue luaValue) {
            return argerror(2, "value expected");
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final LuaValue call(LuaValue luaValue, LuaValue luaValue2) {
            return luaValue.checktable().rawget(luaValue2);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/BaseLib$rawlen.class */
    public static final class rawlen extends LibFunction {
        @Override // com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final LuaValue call(LuaValue luaValue) {
            return valueOf(luaValue.rawlen());
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/BaseLib$rawset.class */
    public static final class rawset extends TableLibFunction {
        @Override // com.prineside.luaj.lib.TableLibFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final /* bridge */ /* synthetic */ LuaValue call() {
            return super.call();
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final LuaValue call(LuaValue luaValue) {
            return argerror(2, "value expected");
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final LuaValue call(LuaValue luaValue, LuaValue luaValue2) {
            return argerror(3, "value expected");
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final LuaValue call(LuaValue luaValue, LuaValue luaValue2, LuaValue luaValue3) {
            LuaTable checktable = luaValue.checktable();
            if (!luaValue2.isvalidkey()) {
                argerror(2, "table index is nil");
            }
            checktable.rawset(luaValue2, luaValue3);
            return checktable;
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/BaseLib$select.class */
    public static final class select extends VarArgFunction {
        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final Varargs invoke(Varargs varargs) {
            int narg = varargs.narg() - 1;
            if (varargs.arg1().equals(valueOf("#"))) {
                return valueOf(narg);
            }
            int checkint = varargs.checkint(1);
            if (checkint == 0 || checkint < (-narg)) {
                argerror(1, "index out of range");
            }
            return varargs.subargs(checkint < 0 ? narg + checkint + 2 : checkint + 1);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/BaseLib$setmetatable.class */
    public static final class setmetatable extends TableLibFunction {
        @Override // com.prineside.luaj.lib.TableLibFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final /* bridge */ /* synthetic */ LuaValue call() {
            return super.call();
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final LuaValue call(LuaValue luaValue) {
            return argerror(2, "nil or table expected");
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final LuaValue call(LuaValue luaValue, LuaValue luaValue2) {
            LuaValue luaValue3 = luaValue.checktable().getmetatable();
            if (luaValue3 != null && !luaValue3.rawget(METATABLE).isnil()) {
                error("cannot change a protected metatable");
            }
            return luaValue.setmetatable(luaValue2.isnil() ? null : luaValue2.checktable());
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/BaseLib$tonumber.class */
    public static final class tonumber extends LibFunction {
        @Override // com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final LuaValue call(LuaValue luaValue) {
            return luaValue.tonumber();
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final LuaValue call(LuaValue luaValue, LuaValue luaValue2) {
            if (luaValue2.isnil()) {
                return luaValue.tonumber();
            }
            int checkint = luaValue2.checkint();
            if (checkint < 2 || checkint > 36) {
                argerror(2, "base out of range");
            }
            return luaValue.checkstring().tonumber(checkint);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/BaseLib$tostring.class */
    public static final class tostring extends LibFunction {
        @Override // com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final LuaValue call(LuaValue luaValue) {
            LuaValue metatag = luaValue.metatag(TOSTRING);
            if (!metatag.isnil()) {
                return metatag.call(luaValue);
            }
            LuaValue luaValue2 = luaValue.tostring();
            if (!luaValue2.isnil()) {
                return luaValue2;
            }
            return valueOf(luaValue.tojstring());
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/BaseLib$type.class */
    public static final class type extends LibFunction {
        @Override // com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final LuaValue call(LuaValue luaValue) {
            return valueOf(luaValue.typename());
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/BaseLib$xpcall.class */
    public static final class xpcall extends VarArgFunction implements KryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private BaseLib f1575a;

        /* synthetic */ xpcall(BaseLib baseLib, byte b2) {
            this(baseLib);
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
        public final void write(Kryo kryo, Output output) {
            kryo.writeClassAndObject(output, this.f1575a);
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
        public final void read(Kryo kryo, Input input) {
            this.f1575a = (BaseLib) kryo.readClassAndObject(input);
        }

        private xpcall() {
        }

        private xpcall(BaseLib baseLib) {
            this.f1575a = baseLib;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final Varargs invoke(Varargs varargs) {
            LuaValue errorFunc = this.f1575a.f1565a.getErrorFunc();
            this.f1575a.f1565a.setErrorFunc(varargs.checkvalue(2));
            try {
                CallStack callStack = CallStack.DUMMY;
                Globals globals = this.f1575a.f1565a;
                LuaError luaError = globals;
                if (globals != null) {
                    CallStack callstack = this.f1575a.f1565a.getCallstack();
                    callStack = callstack;
                    callstack.onCall(this);
                    luaError = callstack;
                }
                try {
                    try {
                        Varargs varargsOf = varargsOf(TRUE, varargs.arg1().invoke(varargs.subargs(3)));
                        callStack.onReturn();
                        if (this.f1575a.f1565a != null) {
                            this.f1575a.f1565a.setErrorFunc(errorFunc);
                        }
                        return varargsOf;
                    } catch (LuaError e) {
                        LuaValue messageObject = luaError.getMessageObject();
                        Varargs varargsOf2 = varargsOf(FALSE, messageObject != null ? messageObject : NIL);
                        callStack.onReturn();
                        if (this.f1575a.f1565a != null) {
                            this.f1575a.f1565a.setErrorFunc(errorFunc);
                        }
                        return varargsOf2;
                    } catch (Exception e2) {
                        String message = luaError.getMessage();
                        Varargs varargsOf3 = varargsOf(FALSE, valueOf(message != null ? message : e2.toString()));
                        callStack.onReturn();
                        if (this.f1575a.f1565a != null) {
                            this.f1575a.f1565a.setErrorFunc(errorFunc);
                        }
                        return varargsOf3;
                    }
                } catch (Throwable th) {
                    callStack.onReturn();
                    throw th;
                }
            } catch (Throwable th2) {
                if (this.f1575a.f1565a != null) {
                    this.f1575a.f1565a.setErrorFunc(errorFunc);
                }
                throw th2;
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/BaseLib$pairs.class */
    public static final class pairs extends VarArgFunction implements KryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private next f1572a;

        @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
        public final void write(Kryo kryo, Output output) {
            kryo.writeClassAndObject(output, this.f1572a);
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
        public final void read(Kryo kryo, Input input) {
            this.f1572a = (next) kryo.readClassAndObject(input);
        }

        private pairs() {
        }

        pairs(next nextVar) {
            this.f1572a = nextVar;
        }

        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final Varargs invoke(Varargs varargs) {
            LuaValue luaValue = varargs.arg1().getmetatable();
            if (luaValue != null) {
                LuaValue luaValue2 = luaValue.get(LuaValue.PAIRS);
                if (luaValue2.isfunction()) {
                    return luaValue2.invoke(varargs);
                }
            }
            return varargsOf(this.f1572a, varargs.checktable(1), NIL);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/BaseLib$ipairs.class */
    public static final class ipairs extends VarArgFunction {

        /* renamed from: a, reason: collision with root package name */
        private inext f1569a = new inext();

        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final Varargs invoke(Varargs varargs) {
            LuaValue luaValue = varargs.arg1().getmetatable();
            if (luaValue != null) {
                LuaValue luaValue2 = luaValue.get(LuaValue.IPAIRS);
                if (luaValue2.isfunction()) {
                    return luaValue2.invoke(varargs);
                }
            }
            return varargsOf(this.f1569a, varargs.checktable(1), ZERO);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/BaseLib$next.class */
    public static final class next extends VarArgFunction {
        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final Varargs invoke(Varargs varargs) {
            return varargs.checktable(1).next(varargs.arg(2));
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/BaseLib$inext.class */
    public static final class inext extends VarArgFunction {
        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final Varargs invoke(Varargs varargs) {
            return varargs.checktable(1).inext(varargs.arg(2));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Exception, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.Exception] */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r0v9, types: [com.prineside.luaj.Varargs] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.prineside.luaj.lib.BaseLib] */
    public Varargs loadFile(String str, String str2, LuaValue luaValue) {
        ?? findResource = this.f1565a.finder.findResource(str);
        if (findResource == 0) {
            return varargsOf(NIL, valueOf("cannot open " + str + ": No such file or directory"));
        }
        try {
            findResource = loadStream(findResource, "@" + str, str2, luaValue);
            try {
                findResource.close();
            } catch (Exception e) {
                findResource.printStackTrace();
            }
            return findResource;
        } catch (Throwable th) {
            try {
                findResource = findResource;
                findResource.close();
            } catch (Exception e2) {
                findResource.printStackTrace();
            }
            throw th;
        }
    }

    public Varargs loadStream(InputStream inputStream, String str, String str2, LuaValue luaValue) {
        try {
            if (inputStream == null) {
                return varargsOf(NIL, valueOf("not found: " + str));
            }
            return this.f1565a.load(inputStream, str, str2, luaValue);
        } catch (Exception e) {
            return varargsOf(NIL, valueOf(e.getMessage()));
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/BaseLib$StringInputStream.class */
    private static class StringInputStream extends InputStream {

        /* renamed from: a, reason: collision with root package name */
        private LuaValue f1566a;

        /* renamed from: b, reason: collision with root package name */
        private byte[] f1567b;
        private int c;
        private int d = 0;

        StringInputStream(LuaValue luaValue) {
            this.f1566a = luaValue;
        }

        @Override // java.io.InputStream
        public int read() {
            if (this.d < 0) {
                return -1;
            }
            if (this.d == 0) {
                LuaValue call = this.f1566a.call();
                if (call.isnil()) {
                    this.d = -1;
                    return -1;
                }
                LuaString strvalue = call.strvalue();
                this.f1567b = strvalue.m_bytes;
                this.c = strvalue.m_offset;
                this.d = strvalue.m_length;
                if (this.d <= 0) {
                    return -1;
                }
            }
            this.d--;
            byte[] bArr = this.f1567b;
            int i = this.c;
            this.c = i + 1;
            return 255 & bArr[i];
        }
    }
}
