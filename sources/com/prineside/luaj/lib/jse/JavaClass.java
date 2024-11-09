package com.prineside.luaj.lib.jse;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectMap;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.base.Preconditions;
import com.prineside.luaj.LuaError;
import com.prineside.luaj.LuaNumber;
import com.prineside.luaj.LuaString;
import com.prineside.luaj.LuaValue;
import com.prineside.luaj.Varargs;
import com.prineside.luaj.lib.VarArgFunction;
import com.prineside.luaj.lib.jse.CoerceJavaToLua;
import com.prineside.luaj.lib.jse.JavaMethod;
import com.prineside.luaj.lib.jse.LuajavaLib;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.managers.script.Whitelist;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.ReflectionUtils;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.bytebuddy.utility.JavaConstant;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/luaj/lib/jse/JavaClass.class */
public final class JavaClass extends JavaInstance implements CoerceJavaToLua.Coercion {

    /* renamed from: b, reason: collision with root package name */
    private static LuaString f1616b = valueOf("new");
    private static LuaString c = valueOf("_findMethod");
    private static final _findMethod d = new _findMethod();
    private static final ConcurrentHashMap<Class<?>, JavaClass> e = new ConcurrentHashMap<>();

    @NAGS
    private LuaString f;

    @NAGS
    private Map<LuaString, LuaValue> g;

    @NAGS
    private Map<LuaString, LuaValue> h;

    @NAGS
    private ObjectMap<LuaString, Field> i;

    @NAGS
    private ObjectMap<LuaString, Field> j;

    @NAGS
    private Map<LuaString, Class<?>> k;
    private final HashMap<LuaValue, NamedClassEntry> l;
    private final HashMap<LuaValue, NamedClassEntry> m;

    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/jse/JavaClass$NamedClassEntry.class */
    public static class NamedClassEntry {
        public static final NamedClassEntry NULL = new NamedClassEntry();
        public static final byte FIELD_TYPE_INT = 0;
        public static final byte FIELD_TYPE_FLOAT = 1;
        public static final byte FIELD_TYPE_DOUBLE = 2;
        public static final byte FIELD_TYPE_BOOLEAN = 3;
        public static final byte FIELD_TYPE_BYTE = 4;
        public static final byte FIELD_TYPE_SHORT = 5;
        public static final byte FIELD_TYPE_CHAR = 6;
        public static final byte FIELD_TYPE_LONG = 7;
        public static final byte FIELD_TYPE_OTHER = 8;
        public boolean isField;
        public Object entry;
        public byte fieldType;
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/jse/JavaClass$Serializer.class */
    public static class Serializer extends com.esotericsoftware.kryo.Serializer<JavaClass> {
        public Serializer() {
            setImmutable(true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, JavaClass javaClass) {
            output.writeString(((Class) javaClass.m_instance).getName());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public JavaClass read2(Kryo kryo, Input input, Class<? extends JavaClass> cls) {
            String readString = input.readString();
            try {
                JavaClass forClass = JavaClass.forClass(LuajavaLib.classForName(readString));
                kryo.reference(forClass);
                return forClass;
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Failed to deserialize JavaClass for " + readString, e);
            }
        }
    }

    public static JavaClass forClass(Class<?> cls) {
        JavaClass javaClass = e.get(cls);
        JavaClass javaClass2 = javaClass;
        if (javaClass == null) {
            ConcurrentHashMap<Class<?>, JavaClass> concurrentHashMap = e;
            JavaClass javaClass3 = new JavaClass(cls);
            javaClass2 = javaClass3;
            concurrentHashMap.put(cls, javaClass3);
        }
        return javaClass2;
    }

    private JavaClass(Class<?> cls) {
        super(cls);
        this.l = new HashMap<>();
        this.m = new HashMap<>();
        if (cls == Class.class) {
            this.f1621a = null;
        } else {
            this.f1621a = forClass(Class.class);
        }
        if (cls.isInterface() && cls.getDeclaredMethods().length == 1) {
            this.f = LuaString.valueOf(cls.getDeclaredMethods()[0].getName());
        }
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue call() {
        throw new LuaError("Class can not be called directly, use '.new*' method if you want to create an instance of a class");
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue call(LuaValue luaValue) {
        LuajavaLib.ProxyInvocationHandler proxyInvocationHandler;
        Class<?> cls = (Class) this.m_instance;
        if (cls.isInterface()) {
            if (Game.i == null || Game.i.scriptManager.getWhitelist().isInterfaceProxyWhiteListed(cls)) {
                if (this.f != null) {
                    if (luaValue.istable()) {
                        LuaValue luaValue2 = luaValue.get(this.f);
                        if (luaValue2.isnil()) {
                            throw new LuaError("Interface proxy is instantiated with table but has no field called after method '" + this.f + "'");
                        }
                        proxyInvocationHandler = new LuajavaLib.ProxyInvocationHandler(this.f.toString(), luaValue2.checkfunction());
                    } else if (luaValue.isfunction()) {
                        proxyInvocationHandler = new LuajavaLib.ProxyInvocationHandler(this.f.toString(), luaValue.checkfunction());
                    } else {
                        throw new LuaError("Table or function expected, got " + luaValue.typename() + SequenceUtils.SPACE + luaValue);
                    }
                } else {
                    proxyInvocationHandler = new LuajavaLib.ProxyInvocationHandler(luaValue.checktable());
                }
                return CoerceJavaToLua.coerce(Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{cls}, proxyInvocationHandler));
            }
            throw new LuaError(cls + " (interface) is not on the white list and can not be proxied with Lua");
        }
        return call();
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue call(LuaValue luaValue, LuaValue luaValue2) {
        return call();
    }

    @Override // com.prineside.luaj.LuaValue
    public final LuaValue call(LuaValue luaValue, LuaValue luaValue2, LuaValue luaValue3) {
        return call();
    }

    @Override // com.prineside.luaj.lib.jse.JavaInstance, com.prineside.luaj.LuaUserdata, com.prineside.luaj.LuaValue
    public final LuaValue get(LuaValue luaValue) {
        if (!(luaValue instanceof LuaString)) {
            return super.get(luaValue);
        }
        if (c.raweq((LuaString) luaValue)) {
            return d;
        }
        NamedClassEntry f = f(luaValue);
        if (f != null) {
            if (f.isField) {
                Field field = (Field) f.entry;
                try {
                    switch (f.fieldType) {
                        case 0:
                            return LuaNumber.valueOf(field.getInt(this.m_instance));
                        case 1:
                            return LuaNumber.valueOf(field.getFloat(this.m_instance));
                        case 2:
                            return LuaNumber.valueOf(field.getDouble(this.m_instance));
                        case 3:
                            return field.getBoolean(this.m_instance) ? LuaValue.TRUE : LuaValue.FALSE;
                        case 4:
                            return LuaNumber.valueOf((int) field.getByte(this.m_instance));
                        case 5:
                            return LuaNumber.valueOf((int) field.getShort(this.m_instance));
                        case 6:
                            return LuaNumber.valueOf((int) field.getChar(this.m_instance));
                        case 7:
                            return LuaNumber.valueOf(field.getLong(this.m_instance));
                        default:
                            return CoerceJavaToLua.coerce(field.get(this.m_instance));
                    }
                } catch (Exception e2) {
                    throw new LuaError("Failed to access field " + field, 1, e2);
                }
            }
            return (LuaValue) f.entry;
        }
        if (this.m_metatable == null) {
            throw new LuaError("Field / method / inner class '" + luaValue.typename() + SequenceUtils.SPACE + luaValue + "' not found in " + this.m_instance.getClass() + SequenceUtils.SPACE + this + " and no metatable is set");
        }
        return super.get(luaValue);
    }

    @Override // com.prineside.luaj.lib.jse.JavaInstance, com.prineside.luaj.LuaUserdata, com.prineside.luaj.LuaValue
    public final void set(LuaValue luaValue, LuaValue luaValue2) {
        if (!(luaValue instanceof LuaString)) {
            super.set(luaValue, luaValue2);
            return;
        }
        Field field = getClassFields().get((LuaString) luaValue);
        if (field != null) {
            if (Modifier.isFinal(field.getModifiers())) {
                throw new LuaError("Final field " + field.getName() + " of class " + field.getDeclaringClass() + " can not be changed");
            }
            try {
                Class<?> type = field.getType();
                if (type == Integer.TYPE) {
                    field.setInt(this.m_instance, luaValue2.toint());
                    return;
                }
                if (type == Float.TYPE) {
                    field.setFloat(this.m_instance, luaValue2.tofloat());
                    return;
                }
                if (type == Double.TYPE) {
                    field.setDouble(this.m_instance, luaValue2.todouble());
                    return;
                }
                if (type == Boolean.TYPE) {
                    field.setBoolean(this.m_instance, luaValue2.toboolean());
                    return;
                }
                if (type == Byte.TYPE) {
                    field.setByte(this.m_instance, luaValue2.tobyte());
                    return;
                }
                if (type == Short.TYPE) {
                    field.setShort(this.m_instance, luaValue2.toshort());
                    return;
                }
                if (type == Character.TYPE) {
                    field.setChar(this.m_instance, luaValue2.tochar());
                    return;
                } else if (type == Long.TYPE) {
                    field.setLong(this.m_instance, luaValue2.tolong());
                    return;
                } else {
                    field.set(this.m_instance, CoerceLuaToJava.coerce(luaValue2, type));
                    return;
                }
            } catch (Exception e2) {
                throw new LuaError(e2);
            }
        }
        super.set(luaValue, luaValue2);
    }

    @Override // com.prineside.luaj.LuaValue
    public final Varargs invoke(Varargs varargs) {
        if (((Class) this.m_instance).isInterface()) {
            if (varargs.narg() != 1) {
                throw new LuaError("Interface must be called with one argument only (table of methods or a single function if interface has one method), " + varargs.narg() + " arguments passed");
            }
            return call(varargs.arg(1));
        }
        return call();
    }

    @Override // com.prineside.luaj.lib.jse.CoerceJavaToLua.Coercion
    public final LuaValue coerce(Object obj) {
        return this;
    }

    public final ObjectMap<LuaString, Field> getInstanceFields() {
        if (this.i == null) {
            ObjectMap<LuaString, Field> objectMap = new ObjectMap<>();
            for (Field field : ((Class) this.m_instance).getFields()) {
                if (!Modifier.isStatic(field.getModifiers()) && (Game.i == null || Game.i.scriptManager.getWhitelist().isFieldWhiteListed(field))) {
                    objectMap.put(LuaString.valueOf(field.getName()), field);
                }
            }
            this.i = objectMap;
        }
        return this.i;
    }

    public final ObjectMap<LuaString, Field> getClassFields() {
        if (this.j == null) {
            ObjectMap<LuaString, Field> objectMap = new ObjectMap<>();
            for (Field field : ((Class) this.m_instance).getFields()) {
                if (Modifier.isStatic(field.getModifiers()) && (Game.i == null || Game.i.scriptManager.getWhitelist().isFieldWhiteListed(field))) {
                    objectMap.put(LuaString.valueOf(field.getName()), field);
                }
            }
            this.j = objectMap;
        }
        return this.j;
    }

    @Null
    private NamedClassEntry a(LuaValue luaValue) {
        Field field = getInstanceFields().get((LuaString) luaValue);
        if (field != null) {
            NamedClassEntry namedClassEntry = new NamedClassEntry();
            namedClassEntry.isField = true;
            namedClassEntry.entry = field;
            Class<?> type = field.getType();
            if (type == Integer.TYPE) {
                namedClassEntry.fieldType = (byte) 0;
            } else if (type == Float.TYPE) {
                namedClassEntry.fieldType = (byte) 1;
            } else if (type == Double.TYPE) {
                namedClassEntry.fieldType = (byte) 2;
            } else if (type == Boolean.TYPE) {
                namedClassEntry.fieldType = (byte) 3;
            } else if (type == Byte.TYPE) {
                namedClassEntry.fieldType = (byte) 4;
            } else if (type == Short.TYPE) {
                namedClassEntry.fieldType = (byte) 5;
            } else if (type == Character.TYPE) {
                namedClassEntry.fieldType = (byte) 6;
            } else if (type == Long.TYPE) {
                namedClassEntry.fieldType = (byte) 7;
            } else {
                namedClassEntry.fieldType = (byte) 8;
            }
            return namedClassEntry;
        }
        return null;
    }

    @Null
    private NamedClassEntry c(LuaValue luaValue) {
        Field field = getClassFields().get((LuaString) luaValue);
        if (field != null) {
            NamedClassEntry namedClassEntry = new NamedClassEntry();
            namedClassEntry.isField = true;
            namedClassEntry.entry = field;
            Class<?> type = field.getType();
            if (type == Integer.TYPE) {
                namedClassEntry.fieldType = (byte) 0;
            } else if (type == Float.TYPE) {
                namedClassEntry.fieldType = (byte) 1;
            } else if (type == Double.TYPE) {
                namedClassEntry.fieldType = (byte) 2;
            } else if (type == Boolean.TYPE) {
                namedClassEntry.fieldType = (byte) 3;
            } else if (type == Byte.TYPE) {
                namedClassEntry.fieldType = (byte) 4;
            } else if (type == Short.TYPE) {
                namedClassEntry.fieldType = (byte) 5;
            } else if (type == Character.TYPE) {
                namedClassEntry.fieldType = (byte) 6;
            } else if (type == Long.TYPE) {
                namedClassEntry.fieldType = (byte) 7;
            } else {
                namedClassEntry.fieldType = (byte) 8;
            }
            return namedClassEntry;
        }
        return null;
    }

    @Null
    private NamedClassEntry d(LuaValue luaValue) {
        LuaValue luaValue2 = getInstanceMethods().get((LuaString) luaValue);
        if (luaValue2 != null) {
            NamedClassEntry namedClassEntry = new NamedClassEntry();
            namedClassEntry.entry = luaValue2;
            return namedClassEntry;
        }
        return null;
    }

    @Null
    private NamedClassEntry e(LuaValue luaValue) {
        LuaValue luaValue2 = getClassMethods().get((LuaString) luaValue);
        if (luaValue2 != null) {
            NamedClassEntry namedClassEntry = new NamedClassEntry();
            namedClassEntry.entry = luaValue2;
            return namedClassEntry;
        }
        return null;
    }

    private NamedClassEntry f(LuaValue luaValue) {
        Class<?> h;
        NamedClassEntry namedClassEntry = this.l.get(luaValue);
        if (namedClassEntry != null) {
            if (namedClassEntry == NamedClassEntry.NULL) {
                return null;
            }
            return namedClassEntry;
        }
        if (luaValue.isstring()) {
            String str = luaValue.tojstring();
            if (str.startsWith("_M_")) {
                LuaValue g = g(LuaString.valueOf(str.substring(3)));
                if (g != null) {
                    NamedClassEntry namedClassEntry2 = new NamedClassEntry();
                    namedClassEntry2.entry = g;
                    this.l.put(luaValue, namedClassEntry2);
                    return namedClassEntry2;
                }
                this.l.put(luaValue, NamedClassEntry.NULL);
                return null;
            }
            if (str.startsWith("_F_")) {
                NamedClassEntry c2 = c(LuaString.valueOf(str.substring(3)));
                if (c2 != null) {
                    this.l.put(luaValue, c2);
                    return c2;
                }
                this.l.put(luaValue, NamedClassEntry.NULL);
                return null;
            }
        }
        NamedClassEntry c3 = c(luaValue);
        NamedClassEntry namedClassEntry3 = c3;
        if (c3 == null) {
            namedClassEntry3 = e(luaValue);
        }
        if (namedClassEntry3 == null && (h = h(luaValue)) != null) {
            NamedClassEntry namedClassEntry4 = new NamedClassEntry();
            namedClassEntry3 = namedClassEntry4;
            namedClassEntry4.entry = forClass(h);
        }
        if (namedClassEntry3 == null) {
            this.l.put(luaValue, NamedClassEntry.NULL);
            return null;
        }
        this.l.put(luaValue, namedClassEntry3);
        return namedClassEntry3;
    }

    public final NamedClassEntry getObjectFieldOrMethod(LuaValue luaValue) {
        NamedClassEntry namedClassEntry = this.m.get(luaValue);
        if (namedClassEntry != null) {
            if (namedClassEntry == NamedClassEntry.NULL) {
                return null;
            }
            return namedClassEntry;
        }
        if (luaValue.isstring()) {
            String str = luaValue.tojstring();
            if (str.startsWith("_M_")) {
                NamedClassEntry d2 = d(LuaString.valueOf(str.substring(3)));
                if (d2 != null) {
                    this.m.put(luaValue, d2);
                    return d2;
                }
                this.m.put(luaValue, NamedClassEntry.NULL);
                return null;
            }
            if (str.startsWith("_F_")) {
                NamedClassEntry a2 = a(LuaString.valueOf(str.substring(3)));
                if (a2 != null) {
                    this.m.put(luaValue, a2);
                    return a2;
                }
                this.m.put(luaValue, NamedClassEntry.NULL);
                return null;
            }
        }
        NamedClassEntry a3 = a(luaValue);
        NamedClassEntry namedClassEntry2 = a3;
        if (a3 == null) {
            namedClassEntry2 = d(luaValue);
        }
        if (namedClassEntry2 == null) {
            this.m.put(luaValue, NamedClassEntry.NULL);
            return null;
        }
        this.m.put(luaValue, namedClassEntry2);
        return namedClassEntry2;
    }

    public final Map<LuaString, LuaValue> getInstanceMethods() {
        if (this.g == null) {
            Whitelist whitelist = Game.i == null ? null : Game.i.scriptManager.getWhitelist();
            HashMap hashMap = new HashMap();
            for (Method method : ((Class) this.m_instance).getMethods()) {
                if (Modifier.isPublic(method.getModifiers()) && !Modifier.isStatic(method.getModifiers()) && (whitelist == null || whitelist.isMethodWhiteListed(method))) {
                    String name = method.getName();
                    if (this.m_instance == Class.class) {
                        name = JavaConstant.Dynamic.DEFAULT_NAME + name;
                    }
                    Array array = (Array) hashMap.get(name);
                    Array array2 = array;
                    if (array == null) {
                        Array array3 = new Array(true, 1, Method.class);
                        array2 = array3;
                        hashMap.put(name, array3);
                    }
                    array2.add(method);
                }
            }
            HashMap hashMap2 = new HashMap();
            for (Map.Entry entry : hashMap.entrySet()) {
                String str = (String) entry.getKey();
                Array array4 = (Array) entry.getValue();
                if (array4.size != 0) {
                    if (array4.size == 1) {
                        hashMap2.put(LuaValue.valueOf(str), JavaMethod.a((Method) array4.get(0)));
                    } else {
                        JavaMethod[] javaMethodArr = new JavaMethod[array4.size];
                        for (int i = 0; i < array4.size; i++) {
                            javaMethodArr[i] = JavaMethod.a((Method) array4.get(i));
                        }
                        hashMap2.put(LuaValue.valueOf(str), new JavaMethod.Overload(javaMethodArr));
                    }
                }
            }
            this.g = hashMap2;
        }
        return this.g;
    }

    public final Map<LuaString, LuaValue> getClassMethods() {
        if (this.h == null) {
            HashMap hashMap = new HashMap(forClass(Class.class).getInstanceMethods());
            if (((Class) this.m_instance).isInterface()) {
                return hashMap;
            }
            Whitelist whitelist = Game.i == null ? null : Game.i.scriptManager.getWhitelist();
            HashMap hashMap2 = new HashMap();
            for (Method method : ((Class) this.m_instance).getMethods()) {
                if (Modifier.isPublic(method.getModifiers()) && Modifier.isStatic(method.getModifiers()) && (whitelist == null || whitelist.isMethodWhiteListed(method))) {
                    String name = method.getName();
                    Array array = (Array) hashMap2.get(name);
                    Array array2 = array;
                    if (array == null) {
                        Array array3 = new Array(true, 1, Method.class);
                        array2 = array3;
                        hashMap2.put(name, array3);
                    }
                    array2.add(method);
                }
            }
            for (Map.Entry entry : hashMap2.entrySet()) {
                String str = (String) entry.getKey();
                Array array4 = (Array) entry.getValue();
                if (array4.size != 0) {
                    if (array4.size == 1) {
                        hashMap.put(LuaValue.valueOf(str), JavaMethod.a((Method) array4.get(0)));
                    } else {
                        Array array5 = (Array) entry.getValue();
                        Array<String> generateOverloadSuffixForMethods = ReflectionUtils.LuaRelated.generateOverloadSuffixForMethods(array5);
                        for (int i = 0; i < array5.size; i++) {
                            hashMap.put(LuaValue.valueOf(str + generateOverloadSuffixForMethods.get(i)), JavaMethod.a((Method) array5.get(i)));
                        }
                    }
                }
            }
            Constructor<?>[] constructors = ((Class) this.m_instance).getConstructors();
            Array array6 = new Array(true, 1, Constructor.class);
            for (Constructor<?> constructor : constructors) {
                if (Modifier.isPublic(constructor.getModifiers()) && (whitelist == null || whitelist.isConstructorWhiteListed(constructor))) {
                    array6.add(constructor);
                }
            }
            switch (array6.size) {
                case 0:
                    break;
                case 1:
                    hashMap.put(f1616b, JavaConstructor.forConstructor((Constructor) array6.get(0)));
                    break;
                default:
                    Array<String> generateOverloadSuffixForConstructors = ReflectionUtils.LuaRelated.generateOverloadSuffixForConstructors(array6);
                    for (int i2 = 0; i2 < array6.size; i2++) {
                        hashMap.put(LuaString.valueOf("new" + generateOverloadSuffixForConstructors.get(i2)), JavaConstructor.forConstructor((Constructor) array6.get(i2)));
                    }
                    break;
            }
            this.h = hashMap;
        }
        return this.h;
    }

    private LuaValue g(LuaValue luaValue) {
        return getClassMethods().get(luaValue);
    }

    private Class<?> h(LuaValue luaValue) {
        if (this.k == null) {
            HashMap hashMap = new HashMap();
            for (Class<?> cls : ((Class) this.m_instance).getClasses()) {
                String name = cls.getName();
                hashMap.put(LuaValue.valueOf(name.substring(Math.max(name.lastIndexOf(36), name.lastIndexOf(46)) + 1)), cls);
            }
            this.k = hashMap;
        }
        return this.k.get(luaValue);
    }

    public final LuaValue getConstructor() {
        return g(f1616b);
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/jse/JavaClass$LuaMethodHandle.class */
    public static final class LuaMethodHandle extends VarArgFunction implements KryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private static Map<Method, LuaMethodHandle> f1617a;

        /* renamed from: b, reason: collision with root package name */
        private JavaMethod f1618b;

        static {
            SyncChecker.SYNC_SHAREABLE_CLASSES.add(LuaMethodHandle.class);
            f1617a = Collections.synchronizedMap(new HashMap());
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
        public final void write(Kryo kryo, Output output) {
            super.write(kryo, output);
            kryo.writeObject(output, this.f1618b);
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.esotericsoftware.kryo.KryoSerializable
        public final void read(Kryo kryo, Input input) {
            super.read(kryo, input);
            this.f1618b = (JavaMethod) kryo.readObject(input, JavaMethod.class);
        }

        public static LuaMethodHandle forMethod(Method method) {
            LuaMethodHandle luaMethodHandle = f1617a.get(method);
            LuaMethodHandle luaMethodHandle2 = luaMethodHandle;
            if (luaMethodHandle == null) {
                Map<Method, LuaMethodHandle> map = f1617a;
                LuaMethodHandle luaMethodHandle3 = new LuaMethodHandle(JavaMethod.a(method));
                luaMethodHandle2 = luaMethodHandle3;
                map.put(method, luaMethodHandle3);
            }
            return luaMethodHandle2;
        }

        private LuaMethodHandle() {
        }

        private LuaMethodHandle(JavaMethod javaMethod) {
            Preconditions.checkNotNull(javaMethod, "javaMethod can not be null");
            this.f1618b = javaMethod;
        }

        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final Varargs invoke(Varargs varargs) {
            return this.f1618b.invoke(varargs);
        }

        @Override // com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaFunction, com.prineside.luaj.LuaValue, com.prineside.luaj.Varargs
        public final String tojstring() {
            return super.tojstring() + " (" + this.f1618b.f1625a + ")";
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/jse/JavaClass$_findMethod.class */
    public static final class _findMethod extends VarArgFunction {
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.luaj.lib.VarArgFunction, com.prineside.luaj.lib.LibFunction, com.prineside.luaj.LuaValue
        public final Varargs invoke(Varargs varargs) {
            int i;
            Class cls = (Class) varargs.checkuserdata(1);
            JavaClass forClass = JavaClass.forClass(cls);
            String str = varargs.checkstring(2).tojstring();
            Varargs subargs = varargs.subargs(3);
            LuaString valueOf = LuaString.valueOf(str);
            Array array = new Array();
            LuaValue luaValue = forClass.getInstanceMethods().get(valueOf);
            if (luaValue instanceof JavaMethod.Overload) {
                array.addAll(((JavaMethod.Overload) luaValue).methods);
            } else {
                array.add((JavaMethod) luaValue);
            }
            for (Class<?> cls2 : cls.getInterfaces()) {
                LuaValue luaValue2 = JavaClass.forClass(cls2).getInstanceMethods().get(valueOf);
                if (luaValue2 instanceof JavaMethod.Overload) {
                    array.addAll(((JavaMethod.Overload) luaValue2).methods);
                } else {
                    array.add((JavaMethod) luaValue2);
                }
            }
            if (array.size == 0) {
                return null;
            }
            if (array.size == 1) {
                return LuaMethodHandle.forMethod(((JavaMethod) array.first()).f1625a);
            }
            Array.ArrayIterator it = array.iterator();
            while (it.hasNext()) {
                JavaMethod javaMethod = (JavaMethod) it.next();
                Class<?>[] parameterTypes = javaMethod.getJavaMethod().getParameterTypes();
                if (parameterTypes.length == subargs.narg()) {
                    for (0; i < subargs.narg(); i + 1) {
                        i = parameterTypes[i] == ((Class) subargs.arg(i + 1).checkuserdata(Class.class)) ? i + 1 : 0;
                    }
                    return LuaMethodHandle.forMethod(javaMethod.f1625a);
                }
            }
            return null;
        }
    }
}
