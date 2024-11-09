package com.prineside.luaj.lib.jse;

import com.prineside.luaj.LuaError;
import com.prineside.luaj.Varargs;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: infinitode-2.jar:com/prineside/luaj/lib/jse/JavaConstructor.class */
public final class JavaConstructor extends JavaMember {

    /* renamed from: a, reason: collision with root package name */
    private static final ConcurrentHashMap<Constructor<?>, JavaConstructor> f1619a = new ConcurrentHashMap<>();

    /* renamed from: b, reason: collision with root package name */
    private Constructor<?> f1620b;

    public static JavaConstructor forConstructor(Constructor<?> constructor) {
        JavaConstructor javaConstructor = f1619a.get(constructor);
        JavaConstructor javaConstructor2 = javaConstructor;
        if (javaConstructor == null) {
            ConcurrentHashMap<Constructor<?>, JavaConstructor> concurrentHashMap = f1619a;
            JavaConstructor javaConstructor3 = new JavaConstructor(constructor);
            javaConstructor2 = javaConstructor3;
            concurrentHashMap.put(constructor, javaConstructor3);
        }
        return javaConstructor2;
    }

    private JavaConstructor(Constructor<?> constructor) {
        super(constructor.getParameterTypes(), constructor.getModifiers());
        this.f1620b = constructor;
    }

    public final Constructor<?> getConstructor() {
        return this.f1620b;
    }

    @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
    public final Varargs invoke(Varargs varargs) {
        Object[] b2 = b(varargs);
        try {
            return CoerceJavaToLua.coerce(this.f1620b.newInstance(b2));
        } catch (Exception e) {
            LuaError luaError = new LuaError("Failed to invoke constructor '" + this.f1620b + "' with arguments: " + varargs + "\nCoerced args: " + Arrays.toString(b2));
            luaError.setCause(e);
            throw luaError;
        }
    }
}
