package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.SerializerFactory;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.util.Generics;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/MapSerializer.class */
public class MapSerializer<T extends Map> extends Serializer<T> {
    private Class keyClass;
    private Class valueClass;
    private Serializer keySerializer;
    private Serializer valueSerializer;
    private boolean keysCanBeNull = true;
    private boolean valuesCanBeNull = true;

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/MapSerializer$BindMap.class */
    public @interface BindMap {
        Class keyClass() default Object.class;

        Class<? extends Serializer> keySerializer() default Serializer.class;

        Class<? extends SerializerFactory> keySerializerFactory() default SerializerFactory.class;

        Class valueClass() default Object.class;

        Class<? extends Serializer> valueSerializer() default Serializer.class;

        Class<? extends SerializerFactory> valueSerializerFactory() default SerializerFactory.class;

        boolean keysCanBeNull() default true;

        boolean valuesCanBeNull() default true;
    }

    public MapSerializer() {
        setAcceptsNull(true);
    }

    public void setKeysCanBeNull(boolean z) {
        this.keysCanBeNull = z;
    }

    public void setKeyClass(Class cls) {
        this.keyClass = cls;
    }

    public Class getKeyClass() {
        return this.keyClass;
    }

    public void setKeyClass(Class cls, Serializer serializer) {
        this.keyClass = cls;
        this.keySerializer = serializer;
    }

    public void setKeySerializer(Serializer serializer) {
        this.keySerializer = serializer;
    }

    public Serializer getKeySerializer() {
        return this.keySerializer;
    }

    public void setValueClass(Class cls) {
        this.valueClass = cls;
    }

    public Class getValueClass() {
        return this.valueClass;
    }

    public void setValueClass(Class cls, Serializer serializer) {
        this.valueClass = cls;
        this.valueSerializer = serializer;
    }

    public void setValueSerializer(Serializer serializer) {
        this.valueSerializer = serializer;
    }

    public Serializer getValueSerializer() {
        return this.valueSerializer;
    }

    public void setValuesCanBeNull(boolean z) {
        this.valuesCanBeNull = z;
    }

    @Override // com.esotericsoftware.kryo.Serializer
    public void write(Kryo kryo, Output output, T t) {
        Class resolve;
        Class resolve2;
        if (t == null) {
            output.writeByte(0);
            return;
        }
        int size = t.size();
        if (size == 0) {
            output.writeByte(1);
            writeHeader(kryo, output, t);
            return;
        }
        output.writeVarInt(size + 1, true);
        writeHeader(kryo, output, t);
        Serializer serializer = this.keySerializer;
        Serializer serializer2 = this.valueSerializer;
        Generics.GenericType[] nextGenericTypes = kryo.getGenerics().nextGenericTypes();
        if (nextGenericTypes != null) {
            if (serializer == null && (resolve2 = nextGenericTypes[0].resolve(kryo.getGenerics())) != null && kryo.isFinal(resolve2)) {
                serializer = kryo.getSerializer(resolve2);
            }
            if (serializer2 == null && (resolve = nextGenericTypes[1].resolve(kryo.getGenerics())) != null && kryo.isFinal(resolve)) {
                serializer2 = kryo.getSerializer(resolve);
            }
        }
        for (Map.Entry entry : t.entrySet()) {
            if (nextGenericTypes != null) {
                kryo.getGenerics().pushGenericType(nextGenericTypes[0]);
            }
            if (serializer != null) {
                if (this.keysCanBeNull) {
                    kryo.writeObjectOrNull(output, entry.getKey(), serializer);
                } else {
                    kryo.writeObject(output, entry.getKey(), serializer);
                }
            } else {
                kryo.writeClassAndObject(output, entry.getKey());
            }
            if (nextGenericTypes != null) {
                kryo.getGenerics().popGenericType();
            }
            if (serializer2 != null) {
                if (this.valuesCanBeNull) {
                    kryo.writeObjectOrNull(output, entry.getValue(), serializer2);
                } else {
                    kryo.writeObject(output, entry.getValue(), serializer2);
                }
            } else {
                kryo.writeClassAndObject(output, entry.getValue());
            }
        }
        kryo.getGenerics().popGenericType();
    }

    protected void writeHeader(Kryo kryo, Output output, T t) {
    }

    protected T create(Kryo kryo, Input input, Class<? extends T> cls, int i) {
        if (cls == HashMap.class) {
            if (i < 3) {
                i++;
            } else if (i < 1073741824) {
                i = (int) ((i / 0.75f) + 1.0f);
            }
            return new HashMap(i);
        }
        return (T) kryo.newInstance(cls);
    }

    @Override // com.esotericsoftware.kryo.Serializer
    /* renamed from: read */
    public T read2(Kryo kryo, Input input, Class<? extends T> cls) {
        Object readClassAndObject;
        Object readClassAndObject2;
        Class resolve;
        Class resolve2;
        int readVarInt = input.readVarInt(true);
        if (readVarInt == 0) {
            return null;
        }
        int i = readVarInt - 1;
        T create = create(kryo, input, cls, i);
        kryo.reference(create);
        if (i == 0) {
            return create;
        }
        Class cls2 = this.keyClass;
        Class cls3 = this.valueClass;
        Serializer serializer = this.keySerializer;
        Serializer serializer2 = this.valueSerializer;
        Generics.GenericType[] nextGenericTypes = kryo.getGenerics().nextGenericTypes();
        if (nextGenericTypes != null) {
            if (serializer == null && (resolve2 = nextGenericTypes[0].resolve(kryo.getGenerics())) != null && kryo.isFinal(resolve2)) {
                serializer = kryo.getSerializer(resolve2);
                cls2 = resolve2;
            }
            if (serializer2 == null && (resolve = nextGenericTypes[1].resolve(kryo.getGenerics())) != null && kryo.isFinal(resolve)) {
                serializer2 = kryo.getSerializer(resolve);
                cls3 = resolve;
            }
        }
        for (int i2 = 0; i2 < i; i2++) {
            if (nextGenericTypes != null) {
                kryo.getGenerics().pushGenericType(nextGenericTypes[0]);
            }
            if (serializer != null) {
                if (this.keysCanBeNull) {
                    readClassAndObject = kryo.readObjectOrNull(input, cls2, serializer);
                } else {
                    readClassAndObject = kryo.readObject(input, cls2, serializer);
                }
            } else {
                readClassAndObject = kryo.readClassAndObject(input);
            }
            if (nextGenericTypes != null) {
                kryo.getGenerics().popGenericType();
            }
            if (serializer2 != null) {
                if (this.valuesCanBeNull) {
                    readClassAndObject2 = kryo.readObjectOrNull(input, cls3, serializer2);
                } else {
                    readClassAndObject2 = kryo.readObject(input, cls3, serializer2);
                }
            } else {
                readClassAndObject2 = kryo.readClassAndObject(input);
            }
            create.put(readClassAndObject, readClassAndObject2);
        }
        kryo.getGenerics().popGenericType();
        return create;
    }

    protected T createCopy(Kryo kryo, T t) {
        return (T) kryo.newInstance(t.getClass());
    }

    @Override // com.esotericsoftware.kryo.Serializer
    public T copy(Kryo kryo, T t) {
        T createCopy = createCopy(kryo, t);
        for (Map.Entry entry : t.entrySet()) {
            createCopy.put(kryo.copy(entry.getKey()), kryo.copy(entry.getValue()));
        }
        return createCopy;
    }
}
