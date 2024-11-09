package com.prineside.luaj.lib.jse;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.luaj.LuaError;
import com.prineside.luaj.LuaFunction;
import com.prineside.luaj.LuaNumber;
import com.prineside.luaj.LuaTable;
import com.prineside.luaj.LuaValue;
import com.prineside.luaj.Varargs;
import com.prineside.luaj.lib.VarArgFunction;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.managers.script.Whitelist;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.ReflectionUtils;
import com.prineside.tdi2.utils.logging.TLog;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/luaj/lib/jse/LuajavaLib.class */
public final class LuajavaLib extends VarArgFunction {

    /* renamed from: a, reason: collision with root package name */
    private static String[] f1636a;

    static {
        TLog.forClass(LuajavaLib.class);
        f1636a = new String[]{"bindClass", "createProxy", "ofClass", "hashCode"};
    }

    /* JADX WARN: Not initialized variable reg: 0, insn: 0x01e5: THROW (r0 I:java.lang.Throwable), block:B:65:0x01e5 */
    @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
    public final Varargs invoke(Varargs varargs) {
        Throwable th;
        Class<?> classForName;
        try {
            switch (this.c) {
                case 0:
                    LuaValue arg = varargs.arg(2);
                    LuaTable luaTable = new LuaTable();
                    a(luaTable, getClass(), f1636a, 1);
                    arg.set("luajava", luaTable);
                    if (!arg.get("package").isnil()) {
                        arg.get("package").get("loaded").set("luajava", luaTable);
                    }
                    return luaTable;
                case 1:
                    return JavaClass.forClass(classForName(varargs.checkjstring(1)));
                case 2:
                    int narg = varargs.narg() - 1;
                    if (narg <= 0) {
                        throw new LuaError("no interfaces");
                    }
                    LuaTable checktable = varargs.checktable(narg + 1);
                    Class<?>[] clsArr = new Class[narg];
                    Whitelist whitelist = Game.i == null ? null : Game.i.scriptManager.getWhitelist();
                    for (int i = 0; i < narg; i++) {
                        LuaValue arg2 = varargs.arg(i + 1);
                        if (arg2.isstring()) {
                            clsArr[i] = classForName(arg2.checkjstring());
                        } else {
                            clsArr[i] = (Class) arg2.checkuserdata(Class.class);
                        }
                        if (!clsArr[i].isInterface()) {
                            throw new LuaError(clsArr[i] + " is not an interface");
                        }
                        if (whitelist != null && !whitelist.isInterfaceProxyWhiteListed(clsArr[i])) {
                            throw new LuaError(clsArr[i] + " (interface) is not on the white list and can not be proxied with Lua");
                        }
                    }
                    return CoerceJavaToLua.coerce(Proxy.newProxyInstance(getClass().getClassLoader(), clsArr, new ProxyInvocationHandler(checktable)));
                case 3:
                    if (varargs.arg(1).isnil()) {
                        return LuaValue.FALSE;
                    }
                    Object checkuserdata = varargs.checkuserdata(1);
                    LuaValue checkvalue = varargs.checkvalue(2);
                    if (checkvalue instanceof JavaClass) {
                        classForName = (Class) ((JavaClass) checkvalue).m_instance;
                    } else {
                        classForName = classForName(checkvalue.tojstring());
                    }
                    return LuaValue.valueOf(classForName.isAssignableFrom(checkuserdata.getClass()));
                case 4:
                    LuaValue arg1 = varargs.arg1();
                    return LuaValue.valueOf(arg1 instanceof LuaNumber ? ((LuaNumber) arg1).originalHashCode() : arg1.hashCode());
                default:
                    throw new LuaError("not supported: " + this);
            }
        } catch (LuaError e) {
            throw th;
        } catch (Exception e2) {
            throw new LuaError(e2);
        }
    }

    public static Class<?> classForName(String str) {
        if (str.startsWith("class ") || str.startsWith("interface ")) {
            throw new ClassNotFoundException("maybe Class object passed instead of its name (class name looks like Class#toString()): '" + str + "'");
        }
        boolean z = -1;
        switch (str.hashCode()) {
            case -1325958191:
                if (str.equals("double")) {
                    z = 2;
                    break;
                }
                break;
            case 104431:
                if (str.equals("int")) {
                    z = true;
                    break;
                }
                break;
            case 3039496:
                if (str.equals("byte")) {
                    z = 4;
                    break;
                }
                break;
            case 3052374:
                if (str.equals("char")) {
                    z = 7;
                    break;
                }
                break;
            case 3327612:
                if (str.equals("long")) {
                    z = 3;
                    break;
                }
                break;
            case 64711720:
                if (str.equals("boolean")) {
                    z = 6;
                    break;
                }
                break;
            case 97526364:
                if (str.equals("float")) {
                    z = false;
                    break;
                }
                break;
            case 109413500:
                if (str.equals("short")) {
                    z = 5;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
                return Float.TYPE;
            case true:
                return Integer.TYPE;
            case true:
                return Double.TYPE;
            case true:
                return Long.TYPE;
            case true:
                return Byte.TYPE;
            case true:
                return Short.TYPE;
            case true:
                return Boolean.TYPE;
            case true:
                return Character.TYPE;
            default:
                return ReflectionUtils.getClassByName(str);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/jse/LuajavaLib$ProxyInvocationHandler.class */
    public static class ProxyInvocationHandler implements KryoSerializable, InvocationHandler {

        /* renamed from: a, reason: collision with root package name */
        private LuaTable f1637a;

        /* renamed from: b, reason: collision with root package name */
        private boolean f1638b;

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void write(Kryo kryo, Output output) {
            kryo.writeObject(output, this.f1637a);
            output.writeBoolean(this.f1638b);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void read(Kryo kryo, Input input) {
            this.f1637a = (LuaTable) kryo.readObject(input, LuaTable.class);
            this.f1638b = input.readBoolean();
        }

        private ProxyInvocationHandler() {
        }

        public ProxyInvocationHandler(LuaTable luaTable) {
            this.f1637a = luaTable;
            this.f1638b = false;
        }

        public ProxyInvocationHandler(String str, LuaFunction luaFunction) {
            this.f1637a = new LuaTable();
            this.f1637a.set(str, luaFunction);
            this.f1638b = true;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:56:0x01c1 A[Catch: LuaError -> 0x021a, Exception -> 0x0243, TryCatch #2 {LuaError -> 0x021a, Exception -> 0x0243, blocks: (B:54:0x01ba, B:56:0x01c1, B:58:0x01dd), top: B:53:0x01ba }] */
        /* JADX WARN: Removed duplicated region for block: B:58:0x01dd A[Catch: LuaError -> 0x021a, Exception -> 0x0243, TRY_ENTER, TryCatch #2 {LuaError -> 0x021a, Exception -> 0x0243, blocks: (B:54:0x01ba, B:56:0x01c1, B:58:0x01dd), top: B:53:0x01ba }] */
        @Override // java.lang.reflect.InvocationHandler
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.Object invoke(java.lang.Object r7, java.lang.reflect.Method r8, java.lang.Object[] r9) {
            /*
                Method dump skipped, instructions count: 631
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.prineside.luaj.lib.jse.LuajavaLib.ProxyInvocationHandler.invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[]):java.lang.Object");
        }
    }
}
