package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.SerializerFactory;
import com.esotericsoftware.kryo.serializers.AsmField;
import com.esotericsoftware.kryo.serializers.CollectionSerializer;
import com.esotericsoftware.kryo.serializers.FieldSerializer;
import com.esotericsoftware.kryo.serializers.MapSerializer;
import com.esotericsoftware.kryo.serializers.ReflectField;
import com.esotericsoftware.kryo.serializers.UnsafeField;
import com.esotericsoftware.kryo.util.Generics;
import com.esotericsoftware.kryo.util.Util;
import com.esotericsoftware.minlog.Log;
import com.esotericsoftware.reflectasm.FieldAccess;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/CachedFields.class */
class CachedFields implements Comparator<FieldSerializer.CachedField> {
    static final FieldSerializer.CachedField[] emptyCachedFields = new FieldSerializer.CachedField[0];
    private final FieldSerializer serializer;
    FieldSerializer.CachedField[] fields = new FieldSerializer.CachedField[0];
    FieldSerializer.CachedField[] copyFields = new FieldSerializer.CachedField[0];
    private final ArrayList<Field> removedFields = new ArrayList<>();
    private Object access;

    public CachedFields(FieldSerializer fieldSerializer) {
        this.serializer = fieldSerializer;
    }

    public void rebuild() {
        if (this.serializer.type.isInterface()) {
            this.fields = emptyCachedFields;
            this.copyFields = emptyCachedFields;
            this.serializer.initializeCachedFields();
            return;
        }
        ArrayList<FieldSerializer.CachedField> arrayList = new ArrayList<>();
        ArrayList<FieldSerializer.CachedField> arrayList2 = new ArrayList<>();
        boolean z = (Util.unsafe || Util.isAndroid || !Modifier.isPublic(this.serializer.type.getModifiers())) ? false : true;
        Class cls = this.serializer.type;
        while (true) {
            Class cls2 = cls;
            if (cls2 == Object.class) {
                break;
            }
            for (Field field : cls2.getDeclaredFields()) {
                addField(field, z, arrayList, arrayList2);
            }
            cls = cls2.getSuperclass();
        }
        if (this.fields.length != arrayList.size()) {
            this.fields = new FieldSerializer.CachedField[arrayList.size()];
        }
        arrayList.toArray(this.fields);
        Arrays.sort(this.fields, this);
        if (this.copyFields.length != arrayList2.size()) {
            this.copyFields = new FieldSerializer.CachedField[arrayList2.size()];
        }
        arrayList2.toArray(this.copyFields);
        Arrays.sort(this.copyFields, this);
        this.serializer.initializeCachedFields();
    }

    private void addField(Field field, boolean z, ArrayList<FieldSerializer.CachedField> arrayList, ArrayList<FieldSerializer.CachedField> arrayList2) {
        FieldSerializer.CachedField newReflectField;
        int modifiers = field.getModifiers();
        if (Modifier.isStatic(modifiers)) {
            return;
        }
        FieldSerializer.FieldSerializerConfig fieldSerializerConfig = this.serializer.config;
        if (field.isSynthetic() && fieldSerializerConfig.ignoreSyntheticFields) {
            return;
        }
        if (!field.isAccessible()) {
            if (!fieldSerializerConfig.setFieldsAsAccessible) {
                return;
            }
            try {
                field.setAccessible(true);
            } catch (AccessControlException unused) {
                if (Log.DEBUG) {
                    Log.debug("kryo", "Unable to set field as accessible: " + field);
                    return;
                }
                return;
            }
        }
        FieldSerializer.Optional optional = (FieldSerializer.Optional) field.getAnnotation(FieldSerializer.Optional.class);
        if ((optional == null || this.serializer.kryo.getContext().containsKey(optional.value())) && !this.removedFields.contains(field)) {
            boolean isTransient = Modifier.isTransient(modifiers);
            if (!isTransient || fieldSerializerConfig.serializeTransient || fieldSerializerConfig.copyTransient) {
                Class<?> declaringClass = field.getDeclaringClass();
                Generics.GenericType genericType = new Generics.GenericType(declaringClass, this.serializer.type, field.getGenericType());
                Class<?> type = genericType.getType() instanceof Class ? (Class) genericType.getType() : field.getType();
                int i = -1;
                if (z && !Modifier.isFinal(modifiers) && Modifier.isPublic(modifiers) && Modifier.isPublic(type.getModifiers())) {
                    try {
                        if (this.access == null) {
                            this.access = FieldAccess.get(this.serializer.type);
                        }
                        i = ((FieldAccess) this.access).getIndex(field);
                    } catch (LinkageError | RuntimeException e) {
                        if (Log.DEBUG) {
                            Log.debug("kryo", "Unable to use ReflectASM.", e);
                        }
                    }
                }
                if (Util.unsafe) {
                    newReflectField = newUnsafeField(field, type, genericType);
                } else if (i != -1) {
                    FieldSerializer.CachedField newAsmField = newAsmField(field, type, genericType);
                    newReflectField = newAsmField;
                    newAsmField.access = (FieldAccess) this.access;
                    newReflectField.accessIndex = i;
                } else {
                    newReflectField = newReflectField(field, type, genericType);
                }
                newReflectField.varEncoding = fieldSerializerConfig.varEncoding;
                if (fieldSerializerConfig.extendedFieldNames) {
                    newReflectField.name = declaringClass.getSimpleName() + "." + field.getName();
                } else {
                    newReflectField.name = field.getName();
                }
                if (newReflectField instanceof ReflectField) {
                    newReflectField.canBeNull = fieldSerializerConfig.fieldsCanBeNull && !field.isAnnotationPresent(FieldSerializer.NotNull.class);
                    if (this.serializer.kryo.isFinal(type) || fieldSerializerConfig.fixedFieldTypes) {
                        newReflectField.valueClass = type;
                    }
                    if (Log.TRACE) {
                        Log.trace("kryo", "Cached " + type.getSimpleName() + " field: " + field.getName() + " (" + Util.className(declaringClass) + ")");
                    }
                } else {
                    newReflectField.canBeNull = type == String.class && fieldSerializerConfig.fieldsCanBeNull;
                    newReflectField.valueClass = type;
                    if (Log.TRACE) {
                        Log.trace("kryo", "Cached " + type.getSimpleName() + " field: " + field.getName() + " (" + Util.className(declaringClass) + ")");
                    }
                }
                applyAnnotations(newReflectField);
                if (isTransient) {
                    if (fieldSerializerConfig.serializeTransient) {
                        arrayList.add(newReflectField);
                    }
                    if (fieldSerializerConfig.copyTransient) {
                        arrayList2.add(newReflectField);
                        return;
                    }
                    return;
                }
                arrayList.add(newReflectField);
                arrayList2.add(newReflectField);
            }
        }
    }

    private FieldSerializer.CachedField newUnsafeField(Field field, Class cls, Generics.GenericType genericType) {
        if (cls.isPrimitive()) {
            if (cls == Integer.TYPE) {
                return new UnsafeField.IntUnsafeField(field);
            }
            if (cls == Float.TYPE) {
                return new UnsafeField.FloatUnsafeField(field);
            }
            if (cls == Boolean.TYPE) {
                return new UnsafeField.BooleanUnsafeField(field);
            }
            if (cls == Long.TYPE) {
                return new UnsafeField.LongUnsafeField(field);
            }
            if (cls == Double.TYPE) {
                return new UnsafeField.DoubleUnsafeField(field);
            }
            if (cls == Short.TYPE) {
                return new UnsafeField.ShortUnsafeField(field);
            }
            if (cls == Character.TYPE) {
                return new UnsafeField.CharUnsafeField(field);
            }
            if (cls == Byte.TYPE) {
                return new UnsafeField.ByteUnsafeField(field);
            }
        }
        if (cls == String.class && (!this.serializer.kryo.getReferences() || !this.serializer.kryo.getReferenceResolver().useReferences(String.class))) {
            return new UnsafeField.StringUnsafeField(field);
        }
        return new UnsafeField(field, this.serializer, genericType);
    }

    private FieldSerializer.CachedField newAsmField(Field field, Class cls, Generics.GenericType genericType) {
        if (cls.isPrimitive()) {
            if (cls == Integer.TYPE) {
                return new AsmField.IntAsmField(field);
            }
            if (cls == Float.TYPE) {
                return new AsmField.FloatAsmField(field);
            }
            if (cls == Boolean.TYPE) {
                return new AsmField.BooleanAsmField(field);
            }
            if (cls == Long.TYPE) {
                return new AsmField.LongAsmField(field);
            }
            if (cls == Double.TYPE) {
                return new AsmField.DoubleAsmField(field);
            }
            if (cls == Short.TYPE) {
                return new AsmField.ShortAsmField(field);
            }
            if (cls == Character.TYPE) {
                return new AsmField.CharAsmField(field);
            }
            if (cls == Byte.TYPE) {
                return new AsmField.ByteAsmField(field);
            }
        }
        if (cls == String.class && (!this.serializer.kryo.getReferences() || !this.serializer.kryo.getReferenceResolver().useReferences(String.class))) {
            return new AsmField.StringAsmField(field);
        }
        return new AsmField(field, this.serializer, genericType);
    }

    private FieldSerializer.CachedField newReflectField(Field field, Class cls, Generics.GenericType genericType) {
        if (cls.isPrimitive()) {
            if (cls == Integer.TYPE) {
                return new ReflectField.IntReflectField(field);
            }
            if (cls == Float.TYPE) {
                return new ReflectField.FloatReflectField(field);
            }
            if (cls == Boolean.TYPE) {
                return new ReflectField.BooleanReflectField(field);
            }
            if (cls == Long.TYPE) {
                return new ReflectField.LongReflectField(field);
            }
            if (cls == Double.TYPE) {
                return new ReflectField.DoubleReflectField(field);
            }
            if (cls == Short.TYPE) {
                return new ReflectField.ShortReflectField(field);
            }
            if (cls == Character.TYPE) {
                return new ReflectField.CharReflectField(field);
            }
            if (cls == Byte.TYPE) {
                return new ReflectField.ByteReflectField(field);
            }
        }
        return new ReflectField(field, this.serializer, genericType);
    }

    @Override // java.util.Comparator
    public int compare(FieldSerializer.CachedField cachedField, FieldSerializer.CachedField cachedField2) {
        return cachedField.name.compareTo(cachedField2.name);
    }

    public void removeField(String str) {
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= this.fields.length) {
                break;
            }
            FieldSerializer.CachedField cachedField = this.fields[i];
            if (!cachedField.name.equals(str)) {
                i++;
            } else {
                FieldSerializer.CachedField[] cachedFieldArr = new FieldSerializer.CachedField[this.fields.length - 1];
                System.arraycopy(this.fields, 0, cachedFieldArr, 0, i);
                System.arraycopy(this.fields, i + 1, cachedFieldArr, i, cachedFieldArr.length - i);
                this.fields = cachedFieldArr;
                this.removedFields.add(cachedField.field);
                z = true;
                break;
            }
        }
        int i2 = 0;
        while (true) {
            if (i2 >= this.copyFields.length) {
                break;
            }
            FieldSerializer.CachedField cachedField2 = this.copyFields[i2];
            if (!cachedField2.name.equals(str)) {
                i2++;
            } else {
                FieldSerializer.CachedField[] cachedFieldArr2 = new FieldSerializer.CachedField[this.copyFields.length - 1];
                System.arraycopy(this.copyFields, 0, cachedFieldArr2, 0, i2);
                System.arraycopy(this.copyFields, i2 + 1, cachedFieldArr2, i2, cachedFieldArr2.length - i2);
                this.copyFields = cachedFieldArr2;
                this.removedFields.add(cachedField2.field);
                z = true;
                break;
            }
        }
        if (!z) {
            throw new IllegalArgumentException("Field \"" + str + "\" not found on class: " + this.serializer.type.getName());
        }
    }

    public void removeField(FieldSerializer.CachedField cachedField) {
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= this.fields.length) {
                break;
            }
            FieldSerializer.CachedField cachedField2 = this.fields[i];
            if (cachedField2 != cachedField) {
                i++;
            } else {
                FieldSerializer.CachedField[] cachedFieldArr = new FieldSerializer.CachedField[this.fields.length - 1];
                System.arraycopy(this.fields, 0, cachedFieldArr, 0, i);
                System.arraycopy(this.fields, i + 1, cachedFieldArr, i, cachedFieldArr.length - i);
                this.fields = cachedFieldArr;
                this.removedFields.add(cachedField2.field);
                z = true;
                break;
            }
        }
        int i2 = 0;
        while (true) {
            if (i2 >= this.copyFields.length) {
                break;
            }
            FieldSerializer.CachedField cachedField3 = this.copyFields[i2];
            if (cachedField3 != cachedField) {
                i2++;
            } else {
                FieldSerializer.CachedField[] cachedFieldArr2 = new FieldSerializer.CachedField[this.copyFields.length - 1];
                System.arraycopy(this.copyFields, 0, cachedFieldArr2, 0, i2);
                System.arraycopy(this.copyFields, i2 + 1, cachedFieldArr2, i2, cachedFieldArr2.length - i2);
                this.copyFields = cachedFieldArr2;
                this.removedFields.add(cachedField3.field);
                z = true;
                break;
            }
        }
        if (!z) {
            throw new IllegalArgumentException("Field \"" + cachedField + "\" not found on class: " + this.serializer.type.getName());
        }
    }

    private void applyAnnotations(FieldSerializer.CachedField cachedField) {
        Field field = cachedField.field;
        if (field.isAnnotationPresent(FieldSerializer.Bind.class)) {
            if (cachedField.serializer != null) {
                throw new KryoException("@Bind applied to a field that already has a serializer: " + cachedField.field.getDeclaringClass().getName() + "." + cachedField.field.getName());
            }
            FieldSerializer.Bind bind = (FieldSerializer.Bind) field.getAnnotation(FieldSerializer.Bind.class);
            Class valueClass = bind.valueClass();
            Class cls = valueClass;
            if (valueClass == Object.class) {
                cls = null;
            }
            if (cls != null) {
                cachedField.setValueClass(cls);
            }
            Serializer newSerializer = newSerializer(cls, bind.serializer(), bind.serializerFactory());
            if (newSerializer != null) {
                cachedField.setSerializer(newSerializer);
            }
            cachedField.setCanBeNull(bind.canBeNull());
            cachedField.setVariableLengthEncoding(bind.variableLengthEncoding());
            cachedField.setOptimizePositive(bind.optimizePositive());
        }
        if (field.isAnnotationPresent(CollectionSerializer.BindCollection.class)) {
            if (cachedField.serializer != null) {
                throw new KryoException("@BindCollection applied to a field that already has a serializer: " + cachedField.field.getDeclaringClass().getName() + "." + cachedField.field.getName());
            }
            if (!Collection.class.isAssignableFrom(field.getType())) {
                throw new KryoException("@BindCollection can only be used with a field implementing Collection: " + Util.className(field.getType()));
            }
            CollectionSerializer.BindCollection bindCollection = (CollectionSerializer.BindCollection) field.getAnnotation(CollectionSerializer.BindCollection.class);
            Class elementClass = bindCollection.elementClass();
            Class cls2 = elementClass;
            if (elementClass == Object.class) {
                cls2 = null;
            }
            Serializer newSerializer2 = newSerializer(cls2, bindCollection.elementSerializer(), bindCollection.elementSerializerFactory());
            CollectionSerializer collectionSerializer = new CollectionSerializer();
            collectionSerializer.setElementsCanBeNull(bindCollection.elementsCanBeNull());
            if (cls2 != null) {
                collectionSerializer.setElementClass(cls2);
            }
            if (newSerializer2 != null) {
                collectionSerializer.setElementSerializer(newSerializer2);
            }
            cachedField.setSerializer(collectionSerializer);
        }
        if (field.isAnnotationPresent(MapSerializer.BindMap.class)) {
            if (cachedField.serializer != null) {
                throw new KryoException("@BindMap applied to a field that already has a serializer: " + cachedField.field.getDeclaringClass().getName() + "." + cachedField.field.getName());
            }
            if (!Map.class.isAssignableFrom(field.getType())) {
                throw new KryoException("@BindMap can only be used with a field implementing Map: " + Util.className(field.getType()));
            }
            MapSerializer.BindMap bindMap = (MapSerializer.BindMap) field.getAnnotation(MapSerializer.BindMap.class);
            Class valueClass2 = bindMap.valueClass();
            Class cls3 = valueClass2;
            if (valueClass2 == Object.class) {
                cls3 = null;
            }
            Serializer newSerializer3 = newSerializer(cls3, bindMap.valueSerializer(), bindMap.valueSerializerFactory());
            Class keyClass = bindMap.keyClass();
            Class cls4 = keyClass;
            if (keyClass == Object.class) {
                cls4 = null;
            }
            Serializer newSerializer4 = newSerializer(cls4, bindMap.keySerializer(), bindMap.keySerializerFactory());
            MapSerializer mapSerializer = new MapSerializer();
            mapSerializer.setKeysCanBeNull(bindMap.keysCanBeNull());
            mapSerializer.setValuesCanBeNull(bindMap.valuesCanBeNull());
            if (cls4 != null) {
                mapSerializer.setKeyClass(cls4);
            }
            if (newSerializer4 != null) {
                mapSerializer.setKeySerializer(newSerializer4);
            }
            if (cls3 != null) {
                mapSerializer.setValueClass(cls3);
            }
            if (newSerializer3 != null) {
                mapSerializer.setValueSerializer(newSerializer3);
            }
            cachedField.setSerializer(mapSerializer);
        }
    }

    private Serializer newSerializer(Class cls, Class cls2, Class cls3) {
        if (cls2 == Serializer.class) {
            cls2 = null;
        }
        if (cls3 == SerializerFactory.class) {
            cls3 = null;
        }
        if (cls3 == null && cls2 != null) {
            cls3 = SerializerFactory.ReflectionSerializerFactory.class;
        }
        if (cls3 == null) {
            return null;
        }
        return Util.newFactory(cls3, cls2).newSerializer(this.serializer.kryo, cls);
    }
}
