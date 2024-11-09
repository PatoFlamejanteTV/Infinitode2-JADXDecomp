package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.minlog.Log;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/RecordSerializer.class */
public class RecordSerializer<T> extends ImmutableSerializer<T> {
    private static final Method IS_RECORD;
    private static final Method GET_RECORD_COMPONENTS;
    private static final Method GET_NAME;
    private static final Method GET_TYPE;
    private static final ClassValue<Constructor<?>> CONSTRUCTOR;
    private static final ClassValue<RecordComponent[]> RECORD_COMPONENTS;
    private boolean fixedFieldTypes = false;

    static {
        Method method;
        Method method2;
        Method method3;
        Method method4;
        try {
            Class<?> cls = Class.forName("java.lang.reflect.RecordComponent");
            method = Class.class.getDeclaredMethod("isRecord", new Class[0]);
            method2 = Class.class.getMethod("getRecordComponents", new Class[0]);
            method3 = cls.getMethod("getName", new Class[0]);
            method4 = cls.getMethod("getType", new Class[0]);
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
            method = null;
            method2 = null;
            method3 = null;
            method4 = null;
        }
        IS_RECORD = method;
        GET_RECORD_COMPONENTS = method2;
        GET_NAME = method3;
        GET_TYPE = method4;
        CONSTRUCTOR = new ClassValue<Constructor<?>>() { // from class: com.esotericsoftware.kryo.serializers.RecordSerializer.1
            @Override // java.lang.ClassValue
            protected /* bridge */ /* synthetic */ Constructor<?> computeValue(Class cls2) {
                return computeValue((Class<?>) cls2);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.lang.ClassValue
            protected Constructor<?> computeValue(Class<?> cls2) {
                return RecordSerializer.getCanonicalConstructor(cls2, RecordSerializer.recordComponents(cls2, Comparator.comparing((v0) -> {
                    return v0.index();
                })));
            }
        };
        RECORD_COMPONENTS = new ClassValue<RecordComponent[]>() { // from class: com.esotericsoftware.kryo.serializers.RecordSerializer.2
            @Override // java.lang.ClassValue
            protected /* bridge */ /* synthetic */ RecordComponent[] computeValue(Class cls2) {
                return computeValue((Class<?>) cls2);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.lang.ClassValue
            protected RecordComponent[] computeValue(Class<?> cls2) {
                return RecordSerializer.recordComponents(cls2, Comparator.comparing((v0) -> {
                    return v0.name();
                }));
            }
        };
    }

    @Deprecated(forRemoval = true)
    public RecordSerializer() {
    }

    public RecordSerializer(Class<T> cls) {
        if (!isRecord(cls)) {
            throw new KryoException(cls + " is not a record");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v13, types: [com.esotericsoftware.kryo.KryoException] */
    /* JADX WARN: Type inference failed for: r0v15, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v15, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r1v25, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r1v8, types: [java.lang.StringBuilder] */
    @Override // com.esotericsoftware.kryo.Serializer
    public void write(Kryo kryo, Output output, T t) {
        for (RecordComponent recordComponent : RECORD_COMPONENTS.get(t.getClass())) {
            Class<?> type = recordComponent.type();
            ?? name = recordComponent.name();
            try {
                name = Log.TRACE;
                if (name != 0) {
                    Log.trace("kryo", "Write property: " + name + " (" + type.getName() + ")");
                }
                if (type.isPrimitive()) {
                    kryo.writeObject(output, recordComponent.getValue(t));
                } else if (this.fixedFieldTypes || kryo.isFinal(type)) {
                    kryo.writeObjectOrNull(output, recordComponent.getValue(t), type);
                } else {
                    kryo.writeClassAndObject(output, recordComponent.getValue(t));
                }
            } catch (KryoException e) {
                name.addTrace(name + " (" + type.getName() + ")");
                throw e;
            } catch (Throwable th) {
                KryoException kryoException = new KryoException(th);
                kryoException.addTrace(name + " (" + type.getName() + ")");
                throw kryoException;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v13, types: [java.lang.Class] */
    /* JADX WARN: Type inference failed for: r0v16, types: [com.esotericsoftware.kryo.KryoException] */
    /* JADX WARN: Type inference failed for: r0v18, types: [boolean] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.esotericsoftware.kryo.Kryo] */
    @Override // com.esotericsoftware.kryo.Serializer
    /* renamed from: read */
    public T read2(Kryo kryo, Input input, Class<? extends T> cls) {
        RecordComponent[] recordComponentArr = RECORD_COMPONENTS.get(cls);
        Object[] objArr = new Object[recordComponentArr.length];
        for (RecordComponent recordComponent : recordComponentArr) {
            String name = recordComponent.name();
            ?? type = recordComponent.type();
            try {
                type = Log.TRACE;
                if (type != 0) {
                    Log.trace("kryo", "Read property: " + name + " (" + cls.getName() + ")");
                }
                if (type.isPrimitive()) {
                    objArr[recordComponent.index()] = kryo.readObject(input, type);
                } else if (this.fixedFieldTypes || kryo.isFinal(type)) {
                    objArr[recordComponent.index()] = kryo.readObjectOrNull(input, type);
                } else {
                    objArr[recordComponent.index()] = kryo.readClassAndObject(input);
                }
            } catch (KryoException e) {
                type.addTrace(name + " (" + cls.getName() + ")");
                throw e;
            } catch (Throwable th) {
                KryoException kryoException = new KryoException(th);
                kryoException.addTrace(name + " (" + cls.getName() + ")");
                throw kryoException;
            }
        }
        return invokeCanonicalConstructor(cls, objArr);
    }

    private boolean isRecord(Class<?> cls) {
        return ((Boolean) IS_RECORD.invoke(cls, new Object[0])).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/RecordSerializer$RecordComponent.class */
    public static final class RecordComponent {
        private final Class<?> recordType;
        private final String name;
        private final Class<?> type;
        private final int index;
        private final Method getter;

        RecordComponent(Class<?> cls, String str, Class<?> cls2, int i) {
            this.recordType = cls;
            this.name = str;
            this.type = cls2;
            this.index = i;
            try {
                this.getter = cls.getDeclaredMethod(str, new Class[0]);
                if (!this.getter.isAccessible()) {
                    this.getter.setAccessible(true);
                }
            } catch (Exception e) {
                KryoException kryoException = new KryoException(e);
                kryoException.addTrace("Could not retrieve record component getter (" + cls.getName() + ")");
                throw kryoException;
            }
        }

        final String name() {
            return this.name;
        }

        final Class<?> type() {
            return this.type;
        }

        final int index() {
            return this.index;
        }

        final Object getValue(Object obj) {
            try {
                return this.getter.invoke(obj, new Object[0]);
            } catch (Exception e) {
                KryoException kryoException = new KryoException(e);
                kryoException.addTrace("Could not retrieve record component value (" + this.recordType.getName() + ")");
                throw kryoException;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> RecordComponent[] recordComponents(Class<T> cls, Comparator<RecordComponent> comparator) {
        try {
            Object[] objArr = (Object[]) GET_RECORD_COMPONENTS.invoke(cls, new Object[0]);
            RecordComponent[] recordComponentArr = new RecordComponent[objArr.length];
            for (int i = 0; i < objArr.length; i++) {
                Object obj = objArr[i];
                recordComponentArr[i] = new RecordComponent(cls, (String) GET_NAME.invoke(obj, new Object[0]), (Class) GET_TYPE.invoke(obj, new Object[0]), i);
            }
            if (comparator != null) {
                Arrays.sort(recordComponentArr, comparator);
            }
            return recordComponentArr;
        } catch (Throwable th) {
            KryoException kryoException = new KryoException(th);
            kryoException.addTrace("Could not retrieve record components (" + cls.getName() + ")");
            throw kryoException;
        }
    }

    private T invokeCanonicalConstructor(Class<? extends T> cls, Object[] objArr) {
        try {
            return (T) CONSTRUCTOR.get(cls).newInstance(objArr);
        } catch (Throwable th) {
            KryoException kryoException = new KryoException(th);
            kryoException.addTrace("Could not construct type (" + cls.getName() + ")");
            throw kryoException;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> Constructor<T> getCanonicalConstructor(Class<T> cls, RecordComponent[] recordComponentArr) {
        try {
            return getCanonicalConstructor(cls, (Class<?>[]) Arrays.stream(recordComponentArr).map((v0) -> {
                return v0.type();
            }).toArray(i -> {
                return new Class[i];
            }));
        } catch (Throwable th) {
            KryoException kryoException = new KryoException(th);
            kryoException.addTrace("Could not retrieve record canonical constructor (" + cls.getName() + ")");
            throw kryoException;
        }
    }

    private static <T> Constructor<T> getCanonicalConstructor(Class<T> cls, Class<?>[] clsArr) {
        Constructor<T> constructor;
        try {
            Constructor<T> constructor2 = cls.getConstructor(clsArr);
            constructor = constructor2;
            if (!constructor2.canAccess(null)) {
                constructor.setAccessible(true);
            }
        } catch (Exception unused) {
            Constructor<T> declaredConstructor = cls.getDeclaredConstructor(clsArr);
            constructor = declaredConstructor;
            declaredConstructor.setAccessible(true);
        }
        return constructor;
    }

    public void setFixedFieldTypes(boolean z) {
        this.fixedFieldTypes = z;
    }
}
