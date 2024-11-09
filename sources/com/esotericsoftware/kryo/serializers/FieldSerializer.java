package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.SerializerFactory;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.util.Generics;
import com.esotericsoftware.kryo.util.Util;
import com.esotericsoftware.minlog.Log;
import com.esotericsoftware.reflectasm.FieldAccess;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/FieldSerializer.class */
public class FieldSerializer<T> extends Serializer<T> {
    final Kryo kryo;
    final Class type;
    final FieldSerializerConfig config;
    final CachedFields cachedFields;
    private final Generics.GenericsHierarchy genericsHierarchy;

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/FieldSerializer$Bind.class */
    public @interface Bind {
        Class valueClass() default Object.class;

        Class<? extends Serializer> serializer() default Serializer.class;

        Class<? extends SerializerFactory> serializerFactory() default SerializerFactory.class;

        boolean canBeNull() default true;

        boolean variableLengthEncoding() default true;

        boolean optimizePositive() default false;
    }

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/FieldSerializer$NotNull.class */
    public @interface NotNull {
    }

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/FieldSerializer$Optional.class */
    public @interface Optional {
        String value();
    }

    public FieldSerializer(Kryo kryo, Class cls) {
        this(kryo, cls, new FieldSerializerConfig());
    }

    public FieldSerializer(Kryo kryo, Class cls, FieldSerializerConfig fieldSerializerConfig) {
        if (cls == null) {
            throw new IllegalArgumentException("type cannot be null.");
        }
        if (cls.isPrimitive()) {
            throw new IllegalArgumentException("type cannot be a primitive class: " + cls);
        }
        if (fieldSerializerConfig == null) {
            throw new IllegalArgumentException("config cannot be null.");
        }
        this.kryo = kryo;
        this.type = cls;
        this.config = fieldSerializerConfig;
        this.genericsHierarchy = new Generics.GenericsHierarchy(cls);
        this.cachedFields = new CachedFields(this);
        this.cachedFields.rebuild();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void initializeCachedFields() {
    }

    public FieldSerializerConfig getFieldSerializerConfig() {
        return this.config;
    }

    public void updateFields() {
        if (Log.TRACE) {
            Log.trace("kryo", "Update fields: " + Util.className(this.type));
        }
        this.cachedFields.rebuild();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v13, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v15, types: [com.esotericsoftware.kryo.serializers.FieldSerializer$CachedField] */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v4, types: [com.esotericsoftware.kryo.serializers.FieldSerializer$CachedField[]] */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r2v7, types: [com.esotericsoftware.kryo.serializers.FieldSerializer$CachedField] */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.lang.Object] */
    @Override // com.esotericsoftware.kryo.Serializer
    public void write(Kryo kryo, Output output, T t) {
        int pushTypeVariables = pushTypeVariables();
        ?? r0 = this.cachedFields.fields;
        int length = r0.length;
        for (int i = 0; i < length; i++) {
            boolean z = Log.TRACE;
            ?? r02 = z;
            if (z) {
                FieldSerializer<T> fieldSerializer = this;
                fieldSerializer.log("Write", r0[i], output.position());
                r02 = fieldSerializer;
            }
            try {
                r02 = r0[i];
                r02.write(output, t);
            } catch (KryoException e) {
                throw r02;
            } catch (Exception | OutOfMemoryError e2) {
                throw new KryoException("Error writing " + r0[i] + " at position " + output.position(), e2);
            }
        }
        popTypeVariables(pushTypeVariables);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v17, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v19, types: [com.esotericsoftware.kryo.serializers.FieldSerializer$CachedField] */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v22 */
    /* JADX WARN: Type inference failed for: r0v7, types: [com.esotericsoftware.kryo.serializers.FieldSerializer$CachedField[]] */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r2v8, types: [com.esotericsoftware.kryo.serializers.FieldSerializer$CachedField] */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.lang.Object] */
    @Override // com.esotericsoftware.kryo.Serializer
    /* renamed from: read */
    public T read2(Kryo kryo, Input input, Class<? extends T> cls) {
        int pushTypeVariables = pushTypeVariables();
        T create = create(kryo, input, cls);
        kryo.reference(create);
        ?? r0 = this.cachedFields.fields;
        int length = r0.length;
        for (int i = 0; i < length; i++) {
            boolean z = Log.TRACE;
            ?? r02 = z;
            if (z) {
                FieldSerializer<T> fieldSerializer = this;
                fieldSerializer.log("Read", r0[i], input.position());
                r02 = fieldSerializer;
            }
            try {
                r02 = r0[i];
                r02.read(input, create);
            } catch (KryoException e) {
                throw r02;
            } catch (Exception | OutOfMemoryError e2) {
                throw new KryoException("Error reading " + r0[i] + " at position " + input.position(), e2);
            }
        }
        popTypeVariables(pushTypeVariables);
        return create;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int pushTypeVariables() {
        Generics.GenericType[] nextGenericTypes = this.kryo.getGenerics().nextGenericTypes();
        if (nextGenericTypes == null) {
            return 0;
        }
        int pushTypeVariables = this.kryo.getGenerics().pushTypeVariables(this.genericsHierarchy, nextGenericTypes);
        if (Log.TRACE && pushTypeVariables > 0) {
            Log.trace("kryo", "Generics: " + this.kryo.getGenerics());
        }
        return pushTypeVariables;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void popTypeVariables(int i) {
        Generics generics = this.kryo.getGenerics();
        if (i > 0) {
            generics.popTypeVariables(i);
        }
        generics.popGenericType();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public T create(Kryo kryo, Input input, Class<? extends T> cls) {
        return (T) kryo.newInstance(cls);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void log(String str, CachedField cachedField, int i) {
        String simpleName;
        if (cachedField instanceof ReflectField) {
            ReflectField reflectField = (ReflectField) cachedField;
            Class<?> resolveFieldClass = reflectField.resolveFieldClass();
            Class<?> cls = resolveFieldClass;
            if (resolveFieldClass == null) {
                cls = cachedField.field.getType();
            }
            simpleName = Util.simpleName(cls, reflectField.genericType);
        } else if (cachedField.valueClass != null) {
            simpleName = cachedField.valueClass.getSimpleName();
        } else {
            simpleName = cachedField.field.getType().getSimpleName();
        }
        Log.trace("kryo", str + " field " + simpleName + ": " + cachedField.name + " (" + Util.className(cachedField.field.getDeclaringClass()) + ')' + Util.pos(i));
    }

    public CachedField getField(String str) {
        for (CachedField cachedField : this.cachedFields.fields) {
            if (cachedField.name.equals(str)) {
                return cachedField;
            }
        }
        throw new IllegalArgumentException("Field \"" + str + "\" not found on class: " + this.type.getName());
    }

    public void removeField(String str) {
        this.cachedFields.removeField(str);
    }

    public void removeField(CachedField cachedField) {
        this.cachedFields.removeField(cachedField);
    }

    public CachedField[] getFields() {
        return this.cachedFields.fields;
    }

    public CachedField[] getCopyFields() {
        return this.cachedFields.copyFields;
    }

    public Class getType() {
        return this.type;
    }

    public Kryo getKryo() {
        return this.kryo;
    }

    protected T createCopy(Kryo kryo, T t) {
        return (T) kryo.newInstance(t.getClass());
    }

    @Override // com.esotericsoftware.kryo.Serializer
    public T copy(Kryo kryo, T t) {
        T createCopy = createCopy(kryo, t);
        kryo.reference(createCopy);
        int length = this.cachedFields.copyFields.length;
        for (int i = 0; i < length; i++) {
            this.cachedFields.copyFields[i].copy(t, createCopy);
        }
        return createCopy;
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/FieldSerializer$CachedField.class */
    public static abstract class CachedField {
        final Field field;
        String name;
        Class valueClass;
        Serializer serializer;
        boolean canBeNull;
        boolean optimizePositive;
        FieldAccess access;
        long offset;
        int tag;
        boolean varEncoding = true;
        boolean reuseSerializer = true;
        int accessIndex = -1;

        public abstract void write(Output output, Object obj);

        public abstract void read(Input input, Object obj);

        public abstract void copy(Object obj, Object obj2);

        public CachedField(Field field) {
            this.field = field;
        }

        public void setValueClass(Class cls) {
            this.valueClass = cls;
        }

        public Class getValueClass() {
            return this.valueClass;
        }

        public void setValueClass(Class cls, Serializer serializer) {
            this.valueClass = cls;
            this.serializer = serializer;
        }

        public void setSerializer(Serializer serializer) {
            this.serializer = serializer;
        }

        public Serializer getSerializer() {
            return this.serializer;
        }

        public void setCanBeNull(boolean z) {
            this.canBeNull = z;
        }

        public boolean getCanBeNull() {
            return this.canBeNull;
        }

        public void setVariableLengthEncoding(boolean z) {
            this.varEncoding = z;
        }

        public boolean getVariableLengthEncoding() {
            return this.varEncoding;
        }

        public void setOptimizePositive(boolean z) {
            this.optimizePositive = z;
        }

        public boolean getOptimizePositive() {
            return this.optimizePositive;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void setReuseSerializer(boolean z) {
            this.reuseSerializer = z;
        }

        boolean getReuseSerializer() {
            return this.reuseSerializer;
        }

        public String getName() {
            return this.name;
        }

        public Field getField() {
            return this.field;
        }

        public String toString() {
            return this.name;
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/FieldSerializer$FieldSerializerConfig.class */
    public static class FieldSerializerConfig implements Cloneable {
        boolean fixedFieldTypes;
        boolean serializeTransient;
        boolean extendedFieldNames;
        boolean fieldsCanBeNull = true;
        boolean setFieldsAsAccessible = true;
        boolean ignoreSyntheticFields = true;
        boolean copyTransient = true;
        boolean varEncoding = true;

        @Override // 
        /* renamed from: clone, reason: merged with bridge method [inline-methods] */
        public FieldSerializerConfig mo707clone() {
            try {
                return (FieldSerializerConfig) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new KryoException(e);
            }
        }

        public void setFieldsCanBeNull(boolean z) {
            this.fieldsCanBeNull = z;
            if (Log.TRACE) {
                Log.trace("kryo", "FieldSerializerConfig fieldsCanBeNull: " + z);
            }
        }

        public boolean getFieldsCanBeNull() {
            return this.fieldsCanBeNull;
        }

        public void setFieldsAsAccessible(boolean z) {
            this.setFieldsAsAccessible = z;
            if (Log.TRACE) {
                Log.trace("kryo", "FieldSerializerConfig setFieldsAsAccessible: " + z);
            }
        }

        public boolean getSetFieldsAsAccessible() {
            return this.setFieldsAsAccessible;
        }

        public void setIgnoreSyntheticFields(boolean z) {
            this.ignoreSyntheticFields = z;
            if (Log.TRACE) {
                Log.trace("kryo", "FieldSerializerConfig ignoreSyntheticFields: " + z);
            }
        }

        public boolean getIgnoreSyntheticFields() {
            return this.ignoreSyntheticFields;
        }

        public void setFixedFieldTypes(boolean z) {
            this.fixedFieldTypes = z;
            if (Log.TRACE) {
                Log.trace("kryo", "FieldSerializerConfig fixedFieldTypes: " + z);
            }
        }

        public boolean getFixedFieldTypes() {
            return this.fixedFieldTypes;
        }

        public void setCopyTransient(boolean z) {
            this.copyTransient = z;
            if (Log.TRACE) {
                Log.trace("kryo", "FieldSerializerConfig copyTransient: " + z);
            }
        }

        public boolean getCopyTransient() {
            return this.copyTransient;
        }

        public void setSerializeTransient(boolean z) {
            this.serializeTransient = z;
            if (Log.TRACE) {
                Log.trace("kryo", "FieldSerializerConfig serializeTransient: " + z);
            }
        }

        public boolean getSerializeTransient() {
            return this.serializeTransient;
        }

        public void setVariableLengthEncoding(boolean z) {
            this.varEncoding = z;
            if (Log.TRACE) {
                Log.trace("kryo", "FieldSerializerConfig variable length encoding: " + z);
            }
        }

        public boolean getVariableLengthEncoding() {
            return this.varEncoding;
        }

        public void setExtendedFieldNames(boolean z) {
            this.extendedFieldNames = z;
            if (Log.TRACE) {
                Log.trace("kryo", "FieldSerializerConfig extendedFieldNames: " + z);
            }
        }

        public boolean getExtendedFieldNames() {
            return this.extendedFieldNames;
        }
    }
}
