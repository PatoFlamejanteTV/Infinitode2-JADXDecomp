package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.minlog.Log;
import com.esotericsoftware.reflectasm.MethodAccess;
import com.vladsch.flexmark.util.html.Attribute;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/BeanSerializer.class */
public class BeanSerializer<T> extends Serializer<T> {
    static final Object[] noArgs = new Object[0];
    private CachedProperty[] properties;
    Object access;

    public BeanSerializer(Kryo kryo, Class cls) {
        try {
            PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(cls).getPropertyDescriptors();
            Arrays.sort(propertyDescriptors, new Comparator<PropertyDescriptor>() { // from class: com.esotericsoftware.kryo.serializers.BeanSerializer.1
                @Override // java.util.Comparator
                public int compare(PropertyDescriptor propertyDescriptor, PropertyDescriptor propertyDescriptor2) {
                    return propertyDescriptor.getName().compareTo(propertyDescriptor2.getName());
                }
            });
            ArrayList arrayList = new ArrayList(propertyDescriptors.length);
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                String name = propertyDescriptor.getName();
                if (!name.equals(Attribute.CLASS_ATTR)) {
                    Method readMethod = propertyDescriptor.getReadMethod();
                    Method writeMethod = propertyDescriptor.getWriteMethod();
                    if (readMethod != null && writeMethod != null) {
                        Serializer serializer = null;
                        Class<?> returnType = readMethod.getReturnType();
                        serializer = kryo.isFinal(returnType) ? kryo.getRegistration(returnType).getSerializer() : serializer;
                        CachedProperty cachedProperty = new CachedProperty();
                        cachedProperty.name = name;
                        cachedProperty.getMethod = readMethod;
                        cachedProperty.setMethod = writeMethod;
                        cachedProperty.serializer = serializer;
                        cachedProperty.setMethodType = writeMethod.getParameterTypes()[0];
                        arrayList.add(cachedProperty);
                    }
                }
            }
            this.properties = (CachedProperty[]) arrayList.toArray(new CachedProperty[arrayList.size()]);
            try {
                this.access = MethodAccess.get(cls);
                int length = this.properties.length;
                for (int i = 0; i < length; i++) {
                    CachedProperty cachedProperty2 = this.properties[i];
                    cachedProperty2.getterAccessIndex = ((MethodAccess) this.access).getIndex(cachedProperty2.getMethod.getName(), cachedProperty2.getMethod.getParameterTypes());
                    cachedProperty2.setterAccessIndex = ((MethodAccess) this.access).getIndex(cachedProperty2.setMethod.getName(), cachedProperty2.setMethod.getParameterTypes());
                }
            } catch (Throwable unused) {
            }
        } catch (IntrospectionException e) {
            throw new KryoException("Error getting bean info.", e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v14, types: [com.esotericsoftware.kryo.KryoException] */
    /* JADX WARN: Type inference failed for: r0v16, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v8, types: [com.esotericsoftware.kryo.serializers.BeanSerializer$CachedProperty[]] */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.lang.Object, com.esotericsoftware.kryo.serializers.BeanSerializer$CachedProperty] */
    /* JADX WARN: Type inference failed for: r1v13, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r1v23, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r2v13, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r2v7, types: [java.lang.StringBuilder] */
    @Override // com.esotericsoftware.kryo.Serializer
    public void write(Kryo kryo, Output output, T t) {
        Class<?> cls = t.getClass();
        int length = this.properties.length;
        for (int i = 0; i < length; i++) {
            ?? r0 = this.properties[i];
            try {
                r0 = Log.TRACE;
                if (r0 != 0) {
                    Log.trace("kryo", "Write property: " + r0 + " (" + cls.getName() + ")");
                }
                Object obj = r0.get(t);
                Serializer serializer = r0.serializer;
                if (serializer != null) {
                    kryo.writeObjectOrNull(output, obj, serializer);
                } else {
                    kryo.writeClassAndObject(output, obj);
                }
            } catch (KryoException e) {
                r0.addTrace(r0 + " (" + cls.getName() + ")");
                throw e;
            } catch (IllegalAccessException e2) {
                throw new KryoException("Error accessing getter method: " + r0 + " (" + cls.getName() + ")", e2);
            } catch (InvocationTargetException e3) {
                throw new KryoException("Error invoking getter method: " + r0 + " (" + cls.getName() + ")", e3);
            } catch (Throwable th) {
                KryoException kryoException = new KryoException(th);
                kryoException.addTrace(r0 + " (" + cls.getName() + ")");
                throw kryoException;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10, types: [com.esotericsoftware.kryo.serializers.BeanSerializer$CachedProperty[]] */
    /* JADX WARN: Type inference failed for: r0v11, types: [java.lang.Object, com.esotericsoftware.kryo.serializers.BeanSerializer$CachedProperty] */
    /* JADX WARN: Type inference failed for: r0v16, types: [com.esotericsoftware.kryo.KryoException] */
    /* JADX WARN: Type inference failed for: r0v18, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v15, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r1v25, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r2v14, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r2v8, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.esotericsoftware.kryo.Kryo] */
    @Override // com.esotericsoftware.kryo.Serializer
    /* renamed from: read */
    public T read2(Kryo kryo, Input input, Class<? extends T> cls) {
        Object readClassAndObject;
        T t = (T) kryo.newInstance(cls);
        kryo.reference(t);
        int length = this.properties.length;
        for (int i = 0; i < length; i++) {
            ?? r0 = this.properties[i];
            try {
                r0 = Log.TRACE;
                if (r0 != 0) {
                    Log.trace("kryo", "Read property: " + r0 + " (" + t.getClass() + ")");
                }
                Serializer serializer = r0.serializer;
                if (serializer != null) {
                    readClassAndObject = kryo.readObjectOrNull(input, r0.setMethodType, serializer);
                } else {
                    readClassAndObject = kryo.readClassAndObject(input);
                }
                r0.set(t, readClassAndObject);
            } catch (KryoException e) {
                r0.addTrace(r0 + " (" + t.getClass().getName() + ")");
                throw e;
            } catch (IllegalAccessException e2) {
                throw new KryoException("Error accessing setter method: " + r0 + " (" + t.getClass().getName() + ")", e2);
            } catch (InvocationTargetException e3) {
                throw new KryoException("Error invoking setter method: " + r0 + " (" + t.getClass().getName() + ")", e3);
            } catch (Throwable th) {
                KryoException kryoException = new KryoException(th);
                kryoException.addTrace(r0 + " (" + t.getClass().getName() + ")");
                throw kryoException;
            }
        }
        return t;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10, types: [java.lang.Object, com.esotericsoftware.kryo.serializers.BeanSerializer$CachedProperty] */
    /* JADX WARN: Type inference failed for: r0v14, types: [com.esotericsoftware.kryo.KryoException] */
    /* JADX WARN: Type inference failed for: r0v18, types: [com.esotericsoftware.kryo.serializers.BeanSerializer$CachedProperty] */
    /* JADX WARN: Type inference failed for: r0v9, types: [com.esotericsoftware.kryo.serializers.BeanSerializer$CachedProperty[]] */
    /* JADX WARN: Type inference failed for: r1v14, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r2v8, types: [java.lang.StringBuilder] */
    @Override // com.esotericsoftware.kryo.Serializer
    public T copy(Kryo kryo, T t) {
        T t2 = (T) kryo.newInstance(t.getClass());
        int length = this.properties.length;
        for (int i = 0; i < length; i++) {
            ?? r0 = this.properties[i];
            try {
                Object obj = r0.get(t);
                r0 = r0;
                r0.set(t2, obj);
            } catch (KryoException e) {
                r0.addTrace(r0 + " (" + t2.getClass().getName() + ")");
                throw e;
            } catch (Exception e2) {
                throw new KryoException("Error copying bean property: " + r0 + " (" + t2.getClass().getName() + ")", e2);
            } catch (Throwable th) {
                KryoException kryoException = new KryoException(th);
                kryoException.addTrace(r0 + " (" + t2.getClass().getName() + ")");
                throw kryoException;
            }
        }
        return t2;
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/BeanSerializer$CachedProperty.class */
    class CachedProperty<X> {
        String name;
        Method getMethod;
        Method setMethod;
        Class setMethodType;
        Serializer serializer;
        int getterAccessIndex;
        int setterAccessIndex;

        CachedProperty() {
        }

        public String toString() {
            return this.name;
        }

        Object get(Object obj) {
            return BeanSerializer.this.access != null ? ((MethodAccess) BeanSerializer.this.access).invoke(obj, this.getterAccessIndex, new Object[0]) : this.getMethod.invoke(obj, BeanSerializer.noArgs);
        }

        void set(Object obj, Object obj2) {
            if (BeanSerializer.this.access != null) {
                ((MethodAccess) BeanSerializer.this.access).invoke(obj, this.setterAccessIndex, obj2);
            } else {
                this.setMethod.invoke(obj, obj2);
            }
        }
    }
}
