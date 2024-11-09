package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Registration;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.SerializerFactory;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/CollectionSerializer.class */
public class CollectionSerializer<T extends Collection> extends Serializer<T> {
    private boolean elementsCanBeNull = true;
    private Serializer elementSerializer;
    private Class elementClass;

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/CollectionSerializer$BindCollection.class */
    public @interface BindCollection {
        Class elementClass() default Object.class;

        Class<? extends Serializer> elementSerializer() default Serializer.class;

        Class<? extends SerializerFactory> elementSerializerFactory() default SerializerFactory.class;

        boolean elementsCanBeNull() default true;
    }

    public CollectionSerializer() {
        setAcceptsNull(true);
    }

    public void setElementsCanBeNull(boolean z) {
        this.elementsCanBeNull = z;
    }

    public void setElementClass(Class cls) {
        this.elementClass = cls;
    }

    public Class getElementClass() {
        return this.elementClass;
    }

    public void setElementClass(Class cls, Serializer serializer) {
        this.elementClass = cls;
        this.elementSerializer = serializer;
    }

    public void setElementSerializer(Serializer serializer) {
        this.elementSerializer = serializer;
    }

    public Serializer getElementSerializer() {
        return this.elementSerializer;
    }

    @Override // com.esotericsoftware.kryo.Serializer
    public void write(Kryo kryo, Output output, T t) {
        Class nextGenericClass;
        if (t == null) {
            output.writeByte((byte) 0);
            return;
        }
        int size = t.size();
        if (size == 0) {
            output.writeByte(1);
            writeHeader(kryo, output, t);
            return;
        }
        boolean z = this.elementsCanBeNull;
        Serializer serializer = this.elementSerializer;
        Serializer serializer2 = serializer;
        if (serializer == null && (nextGenericClass = kryo.getGenerics().nextGenericClass()) != null && kryo.isFinal(nextGenericClass)) {
            serializer2 = kryo.getSerializer(nextGenericClass);
        }
        try {
            if (serializer2 != null) {
                if (z) {
                    Iterator it = t.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            output.writeVarIntFlag(false, size + 1, true);
                            z = false;
                            break;
                        } else if (it.next() == null) {
                            output.writeVarIntFlag(true, size + 1, true);
                            break;
                        }
                    }
                } else {
                    output.writeVarInt(size + 1, true);
                }
                writeHeader(kryo, output, t);
            } else {
                Class<?> cls = null;
                boolean z2 = false;
                Iterator it2 = t.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        Object next = it2.next();
                        if (next == null) {
                            z2 = true;
                        } else if (cls == null) {
                            cls = next.getClass();
                        } else if (next.getClass() != cls) {
                            output.writeVarIntFlag(false, size + 1, true);
                            writeHeader(kryo, output, t);
                            break;
                        }
                    } else {
                        output.writeVarIntFlag(true, size + 1, true);
                        writeHeader(kryo, output, t);
                        if (cls == null) {
                            output.writeByte((byte) 0);
                            return;
                        }
                        kryo.writeClass(output, cls);
                        serializer2 = kryo.getSerializer(cls);
                        if (z) {
                            output.writeBoolean(z2);
                            z = z2;
                        }
                    }
                }
            }
            if (serializer2 != null) {
                if (z) {
                    Iterator it3 = t.iterator();
                    while (it3.hasNext()) {
                        kryo.writeObjectOrNull(output, it3.next(), serializer2);
                    }
                } else {
                    Iterator it4 = t.iterator();
                    while (it4.hasNext()) {
                        kryo.writeObject(output, it4.next(), serializer2);
                    }
                }
            } else {
                Iterator it5 = t.iterator();
                while (it5.hasNext()) {
                    kryo.writeClassAndObject(output, it5.next());
                }
            }
        } finally {
            kryo.getGenerics().popGenericType();
        }
    }

    protected void writeHeader(Kryo kryo, Output output, T t) {
    }

    /* renamed from: create */
    protected T create2(Kryo kryo, Input input, Class<? extends T> cls, int i) {
        if (cls == ArrayList.class) {
            return new ArrayList(i);
        }
        if (cls == HashSet.class) {
            return new HashSet(Math.max(((int) (i / 0.75f)) + 1, 16));
        }
        T t = (T) kryo.newInstance(cls);
        if (t instanceof ArrayList) {
            ((ArrayList) t).ensureCapacity(i);
        }
        return t;
    }

    @Override // com.esotericsoftware.kryo.Serializer
    /* renamed from: read */
    public T read2(Kryo kryo, Input input, Class<? extends T> cls) {
        int i;
        T create2;
        int readVarInt;
        Class nextGenericClass;
        Class cls2 = this.elementClass;
        Serializer serializer = this.elementSerializer;
        Serializer serializer2 = serializer;
        if (serializer == null && (nextGenericClass = kryo.getGenerics().nextGenericClass()) != null && kryo.isFinal(nextGenericClass)) {
            serializer2 = kryo.getSerializer(nextGenericClass);
            cls2 = nextGenericClass;
        }
        try {
            boolean z = this.elementsCanBeNull;
            if (serializer2 != null) {
                if (z) {
                    z = input.readVarIntFlag();
                    readVarInt = input.readVarIntFlag(true);
                } else {
                    readVarInt = input.readVarInt(true);
                }
                if (readVarInt == 0) {
                    return null;
                }
                i = readVarInt - 1;
                create2 = create2(kryo, input, cls, i);
                kryo.reference(create2);
                if (i == 0) {
                    return create2;
                }
            } else {
                boolean readVarIntFlag = input.readVarIntFlag();
                int readVarIntFlag2 = input.readVarIntFlag(true);
                if (readVarIntFlag2 == 0) {
                    return null;
                }
                i = readVarIntFlag2 - 1;
                create2 = create2(kryo, input, cls, i);
                kryo.reference(create2);
                if (i == 0) {
                    return create2;
                }
                if (readVarIntFlag) {
                    Registration readClass = kryo.readClass(input);
                    if (readClass == null) {
                        for (int i2 = 0; i2 < i; i2++) {
                            create2.add(null);
                        }
                        kryo.getGenerics().popGenericType();
                        return create2;
                    }
                    cls2 = readClass.getType();
                    serializer2 = kryo.getSerializer(cls2);
                    if (z) {
                        z = input.readBoolean();
                    }
                }
            }
            if (serializer2 == null) {
                for (int i3 = 0; i3 < i; i3++) {
                    create2.add(kryo.readClassAndObject(input));
                }
            } else if (z) {
                for (int i4 = 0; i4 < i; i4++) {
                    create2.add(kryo.readObjectOrNull(input, cls2, serializer2));
                }
            } else {
                for (int i5 = 0; i5 < i; i5++) {
                    create2.add(kryo.readObject(input, cls2, serializer2));
                }
            }
            return create2;
        } finally {
            kryo.getGenerics().popGenericType();
        }
    }

    protected T createCopy(Kryo kryo, T t) {
        return (T) kryo.newInstance(t.getClass());
    }

    @Override // com.esotericsoftware.kryo.Serializer
    public T copy(Kryo kryo, T t) {
        T createCopy = createCopy(kryo, t);
        kryo.reference(createCopy);
        Iterator it = t.iterator();
        while (it.hasNext()) {
            createCopy.add(kryo.copy(it.next()));
        }
        return createCopy;
    }
}
