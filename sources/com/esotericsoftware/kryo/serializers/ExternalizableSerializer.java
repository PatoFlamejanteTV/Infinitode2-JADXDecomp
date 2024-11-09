package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.KryoObjectInput;
import com.esotericsoftware.kryo.io.KryoObjectOutput;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.util.ObjectMap;
import java.io.Externalizable;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Method;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/ExternalizableSerializer.class */
public class ExternalizableSerializer extends Serializer {
    private ObjectMap<Class, JavaSerializer> javaSerializerByType;
    private KryoObjectInput objectInput = null;
    private KryoObjectOutput objectOutput = null;

    @Override // com.esotericsoftware.kryo.Serializer
    public void write(Kryo kryo, Output output, Object obj) {
        JavaSerializer javaSerializerIfRequired = getJavaSerializerIfRequired(obj.getClass());
        if (javaSerializerIfRequired == null) {
            writeExternal(kryo, output, obj);
        } else {
            javaSerializerIfRequired.write(kryo, output, obj);
        }
    }

    @Override // com.esotericsoftware.kryo.Serializer
    /* renamed from: read */
    public Object read2(Kryo kryo, Input input, Class cls) {
        JavaSerializer javaSerializerIfRequired = getJavaSerializerIfRequired(cls);
        return javaSerializerIfRequired == null ? readExternal(kryo, input, cls) : javaSerializerIfRequired.read2(kryo, input, cls);
    }

    private void writeExternal(Kryo kryo, Output output, Object obj) {
        try {
            ((Externalizable) obj).writeExternal(getObjectOutput(kryo, output));
        } catch (Exception e) {
            throw new KryoException(e);
        }
    }

    private Object readExternal(Kryo kryo, Input input, Class cls) {
        try {
            Externalizable externalizable = (Externalizable) kryo.newInstance(cls);
            externalizable.readExternal(getObjectInput(kryo, input));
            return externalizable;
        } catch (Exception e) {
            throw new KryoException(e);
        }
    }

    private ObjectOutput getObjectOutput(Kryo kryo, Output output) {
        if (this.objectOutput == null) {
            this.objectOutput = new KryoObjectOutput(kryo, output);
        } else {
            this.objectOutput.setOutput(output);
        }
        return this.objectOutput;
    }

    private ObjectInput getObjectInput(Kryo kryo, Input input) {
        if (this.objectInput == null) {
            this.objectInput = new KryoObjectInput(kryo, input);
        } else {
            this.objectInput.setInput(input);
        }
        return this.objectInput;
    }

    private JavaSerializer getJavaSerializerIfRequired(Class cls) {
        JavaSerializer cachedSerializer = getCachedSerializer(cls);
        JavaSerializer javaSerializer = cachedSerializer;
        if (cachedSerializer == null && isJavaSerializerRequired(cls)) {
            javaSerializer = new JavaSerializer();
        }
        return javaSerializer;
    }

    private JavaSerializer getCachedSerializer(Class cls) {
        if (this.javaSerializerByType == null) {
            this.javaSerializerByType = new ObjectMap<>();
            return null;
        }
        return this.javaSerializerByType.get(cls);
    }

    private boolean isJavaSerializerRequired(Class cls) {
        return hasInheritableReplaceMethod(cls, "writeReplace") || hasInheritableReplaceMethod(cls, "readResolve");
    }

    private static boolean hasInheritableReplaceMethod(Class cls, String str) {
        Method method = null;
        Class cls2 = cls;
        while (true) {
            Class cls3 = cls2;
            if (cls3 == null) {
                break;
            }
            try {
                method = cls3.getDeclaredMethod(str, new Class[0]);
                break;
            } catch (NoSuchMethodException unused) {
                cls2 = cls3.getSuperclass();
            }
        }
        return method != null && method.getReturnType() == Object.class;
    }
}
