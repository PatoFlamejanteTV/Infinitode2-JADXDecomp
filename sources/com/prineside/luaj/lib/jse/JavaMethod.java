package com.prineside.luaj.lib.jse;

import com.badlogic.gdx.utils.StringBuilder;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.luaj.LuaError;
import com.prineside.luaj.LuaFunction;
import com.prineside.luaj.LuaValue;
import com.prineside.luaj.Varargs;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.ReflectionUtils;
import com.prineside.tdi2.utils.logging.TLog;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

@REGS(serializer = Serializer.class, arrayLevels = 1)
/* loaded from: infinitode-2.jar:com/prineside/luaj/lib/jse/JavaMethod.class */
public final class JavaMethod extends JavaMember {

    /* renamed from: b, reason: collision with root package name */
    private static Map<Method, JavaMethod> f1624b;

    /* renamed from: a, reason: collision with root package name */
    final Method f1625a;

    static {
        SyncChecker.SYNC_SHAREABLE_CLASSES.add(JavaMethod.class);
        TLog.forClass(JavaMethod.class);
        f1624b = Collections.synchronizedMap(new HashMap());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static JavaMethod a(Method method) {
        JavaMethod javaMethod = f1624b.get(method);
        JavaMethod javaMethod2 = javaMethod;
        if (javaMethod == null) {
            Map<Method, JavaMethod> map = f1624b;
            JavaMethod javaMethod3 = new JavaMethod(method);
            javaMethod2 = javaMethod3;
            map.put(method, javaMethod3);
        }
        return javaMethod2;
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/jse/JavaMethod$Serializer.class */
    public static class Serializer extends com.esotericsoftware.kryo.Serializer<JavaMethod> {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, JavaMethod javaMethod) {
            Method method = javaMethod.f1625a;
            output.writeString(method.getDeclaringClass().getName());
            output.writeString(method.getName());
            Class<?>[] parameterTypes = method.getParameterTypes();
            output.writeVarInt(parameterTypes.length, true);
            for (Class<?> cls : parameterTypes) {
                output.writeString(cls.getName());
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public JavaMethod read2(Kryo kryo, Input input, Class<? extends JavaMethod> cls) {
            int i;
            String readString = input.readString();
            Class<?> classByName = ReflectionUtils.getClassByName(readString);
            if (classByName == null) {
                throw new IllegalArgumentException("Failed to find class " + readString);
            }
            String readString2 = input.readString();
            int readVarInt = input.readVarInt(true);
            Class<?>[] clsArr = new Class[readVarInt];
            for (int i2 = 0; i2 < readVarInt; i2++) {
                String readString3 = input.readString();
                Class<?> classByName2 = ReflectionUtils.getClassByName(readString3);
                if (classByName2 == null) {
                    throw new IllegalArgumentException("Failed to find parameter class " + readString3);
                }
                clsArr[i2] = classByName2;
            }
            for (Method method : classByName.getMethods()) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == readVarInt) {
                    for (0; i < readVarInt; i + 1) {
                        i = parameterTypes[i] == clsArr[i] ? i + 1 : 0;
                    }
                    return JavaMethod.a(method);
                }
            }
            throw new IllegalArgumentException("Failed to find method with signature " + readString + "#" + readString2 + "(" + Arrays.toString(clsArr) + ")");
        }
    }

    private JavaMethod(Method method) {
        super(method.getParameterTypes(), method.getModifiers());
        this.f1625a = method;
        try {
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }
        } catch (SecurityException unused) {
        }
    }

    public final Method getJavaMethod() {
        return this.f1625a;
    }

    @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
    public final LuaValue call() {
        return error("method cannot be called without instance");
    }

    @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
    public final LuaValue call(LuaValue luaValue) {
        return a(luaValue.checkuserdata(), LuaValue.NONE);
    }

    @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
    public final LuaValue call(LuaValue luaValue, LuaValue luaValue2) {
        return a(luaValue.checkuserdata(), luaValue2);
    }

    @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
    public final LuaValue call(LuaValue luaValue, LuaValue luaValue2, LuaValue luaValue3) {
        return a(luaValue.checkuserdata(), varargsOf(luaValue2, luaValue3));
    }

    @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
    public final Varargs invoke(Varargs varargs) {
        return a(varargs.checkuserdata(1), varargs.subargs(2));
    }

    final LuaValue a(Object obj, Varargs varargs) {
        Object[] b2 = b(varargs);
        try {
            return CoerceJavaToLua.coerce(this.f1625a.invoke(obj, b2));
        } catch (Exception e) {
            LuaError luaError = new LuaError("Failed to invoke method '" + this.f1625a + "' with arguments: " + varargs + "\nCoerced args: " + Arrays.toString(b2));
            luaError.setCause(e);
            throw luaError;
        }
    }

    @Override // com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaFunction, com.prineside.luaj.LuaValue, com.prineside.luaj.Varargs
    public final String tojstring() {
        return "function: JavaMethod (" + (Modifier.isStatic(this.f1625a.getModifiers()) ? "static " : "") + this.f1625a.getDeclaringClass().getSimpleName() + "#" + this.f1625a.getName() + ")";
    }

    @REGS(serializer = Serializer.class)
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/jse/JavaMethod$Overload.class */
    public static final class Overload extends LuaFunction {
        public final JavaMethod[] methods;

        /* renamed from: a, reason: collision with root package name */
        private static final Comparator<JavaMethod> f1626a;

        static {
            SyncChecker.SYNC_SHAREABLE_CLASSES.add(Overload.class);
            f1626a = (javaMethod, javaMethod2) -> {
                Method method = javaMethod.f1625a;
                Method method2 = javaMethod2.f1625a;
                int compareTo = method.getName().compareTo(method2.getName());
                if (compareTo == 0) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    Class<?>[] parameterTypes2 = method2.getParameterTypes();
                    int compare = Integer.compare(parameterTypes.length, parameterTypes2.length);
                    if (compare == 0) {
                        for (int i = 0; i < parameterTypes.length; i++) {
                            Class<?> cls = parameterTypes[i];
                            Class<?> cls2 = parameterTypes2[i];
                            String simpleName = cls.getSimpleName();
                            String simpleName2 = cls2.getSimpleName();
                            int compare2 = Integer.compare(simpleName.length(), simpleName2.length());
                            if (compare2 != 0) {
                                return compare2;
                            }
                            for (int i2 = 0; i2 < simpleName.length(); i2++) {
                                int compare3 = Integer.compare(simpleName.charAt(i2), simpleName2.charAt(i2));
                                if (compare3 != 0) {
                                    return compare3;
                                }
                            }
                        }
                        return 0;
                    }
                    return compare;
                }
                return compareTo;
            };
        }

        /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/jse/JavaMethod$Overload$Serializer.class */
        public static class Serializer extends com.esotericsoftware.kryo.Serializer<Overload> {
            @Override // com.esotericsoftware.kryo.Serializer
            public void write(Kryo kryo, Output output, Overload overload) {
                kryo.writeObject(output, overload.methods);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.esotericsoftware.kryo.Serializer
            /* renamed from: read */
            public Overload read2(Kryo kryo, Input input, Class<? extends Overload> cls) {
                return new Overload((JavaMethod[]) kryo.readObject(input, JavaMethod[].class));
            }
        }

        public Overload(JavaMethod[] javaMethodArr) {
            this.methods = javaMethodArr;
            Arrays.sort(this.methods, f1626a);
        }

        @Override // com.prineside.luaj.LuaValue
        public final LuaValue call() {
            return error("method cannot be called without instance");
        }

        private void a(LuaError luaError, Varargs varargs) {
            luaError.appendExtraMessage("trying to invoke one of " + this.methods.length + " methods for parameters " + varargs + ": ");
            for (JavaMethod javaMethod : this.methods) {
                luaError.appendExtraMessage("- " + javaMethod.f1625a.getName() + "(" + Arrays.toString(javaMethod.f1625a.getParameterTypes()) + ") of class " + javaMethod.f1625a.getDeclaringClass());
            }
        }

        @Override // com.prineside.luaj.LuaValue
        public final LuaValue call(LuaValue luaValue) {
            try {
                return invokeBestMethod(luaValue.checkuserdata(), LuaValue.NONE);
            } catch (LuaError e) {
                a(e, luaValue);
                throw e;
            }
        }

        @Override // com.prineside.luaj.LuaValue
        public final LuaValue call(LuaValue luaValue, LuaValue luaValue2) {
            try {
                return invokeBestMethod(luaValue.checkuserdata(), luaValue2);
            } catch (LuaError e) {
                a(e, varargsOf(luaValue, luaValue2));
                throw e;
            }
        }

        @Override // com.prineside.luaj.LuaValue
        public final LuaValue call(LuaValue luaValue, LuaValue luaValue2, LuaValue luaValue3) {
            try {
                return invokeBestMethod(luaValue.checkuserdata(), varargsOf(luaValue2, luaValue3));
            } catch (LuaError e) {
                a(e, varargsOf(luaValue, luaValue2, luaValue3));
                throw e;
            }
        }

        @Override // com.prineside.luaj.LuaValue
        public final Varargs invoke(Varargs varargs) {
            try {
                return invokeBestMethod(varargs.checkuserdata(1), varargs.subargs(2));
            } catch (LuaError e) {
                a(e, varargs);
                throw e;
            }
        }

        public final LuaValue invokeBestMethod(Object obj, Varargs varargs) {
            JavaMethod javaMethod = null;
            long j = CoerceLuaToJava.c;
            for (JavaMethod javaMethod2 : this.methods) {
                long a2 = javaMethod2.a(varargs);
                if (a2 < j) {
                    j = a2;
                    javaMethod = javaMethod2;
                    if (j == 0) {
                        break;
                    }
                }
            }
            if (javaMethod == null) {
                long j2 = CoerceLuaToJava.c;
                for (JavaMethod javaMethod3 : this.methods) {
                    long a3 = javaMethod3.a(varargs);
                    if (a3 < j2) {
                        j2 = a3;
                        javaMethod = javaMethod3;
                        if (j2 == 0) {
                            break;
                        }
                    }
                }
                StringBuilder stringBuilder = new StringBuilder();
                long j3 = CoerceLuaToJava.c;
                stringBuilder.append("- Starting score ").append(String.valueOf(j3)).append(", args: ").append(varargs.toString()).append(SequenceUtils.EOL);
                for (JavaMethod javaMethod4 : this.methods) {
                    long a4 = javaMethod4.a(varargs);
                    stringBuilder.append("- Score of ").append(String.valueOf(javaMethod4.f1625a)).append(": ").append(a4).append(SequenceUtils.EOL);
                    if (a4 < j3) {
                        j3 = a4;
                        javaMethod = javaMethod4;
                        if (j3 == 0) {
                            break;
                        }
                    }
                }
                LuaValue.error("no coercible public method in JavaMethod.Overload\n" + ((Object) stringBuilder));
            }
            return javaMethod.a(obj, varargs);
        }
    }
}
