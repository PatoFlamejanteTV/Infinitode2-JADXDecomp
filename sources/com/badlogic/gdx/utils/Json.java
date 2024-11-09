package com.badlogic.gdx.utils;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.IntSet;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.LongMap;
import com.badlogic.gdx.utils.ObjectFloatMap;
import com.badlogic.gdx.utils.ObjectIntMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectSet;
import com.badlogic.gdx.utils.reflect.ArrayReflection;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Constructor;
import com.badlogic.gdx.utils.reflect.Field;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import com.vladsch.flexmark.util.html.Attribute;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.bytebuddy.utility.JavaConstant;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/Json.class */
public class Json {
    private static final boolean debug = false;
    private JsonWriter writer;
    private String typeName;
    private boolean usePrototypes;
    private JsonWriter.OutputType outputType;
    private boolean quoteLongValues;
    private boolean ignoreUnknownFields;
    private boolean ignoreDeprecated;
    private boolean readDeprecated;
    private boolean enumNames;
    private boolean sortFields;
    private Serializer defaultSerializer;
    private final ObjectMap<Class, OrderedMap<String, FieldMetadata>> typeToFields;
    private final ObjectMap<String, Class> tagToClass;
    private final ObjectMap<Class, String> classToTag;
    private final ObjectMap<Class, Serializer> classToSerializer;
    private final ObjectMap<Class, Object[]> classToDefaultValues;
    private final Object[] equals1;
    private final Object[] equals2;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/Json$Serializable.class */
    public interface Serializable {
        void write(Json json);

        void read(Json json, JsonValue jsonValue);
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/Json$Serializer.class */
    public interface Serializer<T> {
        void write(Json json, T t, Class cls);

        T read(Json json, JsonValue jsonValue, Class cls);
    }

    public Json() {
        this.typeName = Attribute.CLASS_ATTR;
        this.usePrototypes = true;
        this.enumNames = true;
        this.typeToFields = new ObjectMap<>();
        this.tagToClass = new ObjectMap<>();
        this.classToTag = new ObjectMap<>();
        this.classToSerializer = new ObjectMap<>();
        this.classToDefaultValues = new ObjectMap<>();
        this.equals1 = new Object[]{null};
        this.equals2 = new Object[]{null};
        this.outputType = JsonWriter.OutputType.minimal;
    }

    public Json(JsonWriter.OutputType outputType) {
        this.typeName = Attribute.CLASS_ATTR;
        this.usePrototypes = true;
        this.enumNames = true;
        this.typeToFields = new ObjectMap<>();
        this.tagToClass = new ObjectMap<>();
        this.classToTag = new ObjectMap<>();
        this.classToSerializer = new ObjectMap<>();
        this.classToDefaultValues = new ObjectMap<>();
        this.equals1 = new Object[]{null};
        this.equals2 = new Object[]{null};
        this.outputType = outputType;
    }

    public void setIgnoreUnknownFields(boolean z) {
        this.ignoreUnknownFields = z;
    }

    public boolean getIgnoreUnknownFields() {
        return this.ignoreUnknownFields;
    }

    public void setIgnoreDeprecated(boolean z) {
        this.ignoreDeprecated = z;
    }

    public void setReadDeprecated(boolean z) {
        this.readDeprecated = z;
    }

    public void setOutputType(JsonWriter.OutputType outputType) {
        this.outputType = outputType;
    }

    public void setQuoteLongValues(boolean z) {
        this.quoteLongValues = z;
    }

    public void setEnumNames(boolean z) {
        this.enumNames = z;
    }

    public void addClassTag(String str, Class cls) {
        this.tagToClass.put(str, cls);
        this.classToTag.put(cls, str);
    }

    @Null
    public Class getClass(String str) {
        return this.tagToClass.get(str);
    }

    @Null
    public String getTag(Class cls) {
        return this.classToTag.get(cls);
    }

    public void setTypeName(@Null String str) {
        this.typeName = str;
    }

    public void setDefaultSerializer(@Null Serializer serializer) {
        this.defaultSerializer = serializer;
    }

    public <T> void setSerializer(Class<T> cls, Serializer<T> serializer) {
        this.classToSerializer.put(cls, serializer);
    }

    public <T> Serializer<T> getSerializer(Class<T> cls) {
        return this.classToSerializer.get(cls);
    }

    public void setUsePrototypes(boolean z) {
        this.usePrototypes = z;
    }

    public void setElementType(Class cls, String str, Class cls2) {
        FieldMetadata fieldMetadata = getFields(cls).get(str);
        if (fieldMetadata == null) {
            throw new SerializationException("Field not found: " + str + " (" + cls.getName() + ")");
        }
        fieldMetadata.elementType = cls2;
    }

    public void setDeprecated(Class cls, String str, boolean z) {
        FieldMetadata fieldMetadata = getFields(cls).get(str);
        if (fieldMetadata == null) {
            throw new SerializationException("Field not found: " + str + " (" + cls.getName() + ")");
        }
        fieldMetadata.deprecated = z;
    }

    public void setSortFields(boolean z) {
        this.sortFields = z;
    }

    protected void sortFields(Class cls, Array<String> array) {
        if (this.sortFields) {
            array.sort();
        }
    }

    private OrderedMap<String, FieldMetadata> getFields(Class cls) {
        OrderedMap<String, FieldMetadata> orderedMap = this.typeToFields.get(cls);
        if (orderedMap != null) {
            return orderedMap;
        }
        Array array = new Array();
        Class cls2 = cls;
        while (true) {
            Class cls3 = cls2;
            if (cls3 == Object.class) {
                break;
            }
            array.add(cls3);
            cls2 = cls3.getSuperclass();
        }
        ArrayList arrayList = new ArrayList();
        for (int i = array.size - 1; i >= 0; i--) {
            java.util.Collections.addAll(arrayList, ClassReflection.getDeclaredFields((Class) array.get(i)));
        }
        OrderedMap<String, FieldMetadata> orderedMap2 = new OrderedMap<>(arrayList.size());
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            Field field = (Field) arrayList.get(i2);
            if (!field.isTransient() && !field.isStatic() && !field.isSynthetic()) {
                if (!field.isAccessible()) {
                    try {
                        field.setAccessible(true);
                    } catch (RuntimeException unused) {
                    }
                }
                orderedMap2.put(field.getName(), new FieldMetadata(field));
            }
        }
        sortFields(cls, orderedMap2.keys);
        this.typeToFields.put(cls, orderedMap2);
        return orderedMap2;
    }

    public String toJson(@Null Object obj) {
        return toJson(obj, obj == null ? null : obj.getClass(), (Class) null);
    }

    public String toJson(@Null Object obj, @Null Class cls) {
        return toJson(obj, cls, (Class) null);
    }

    public String toJson(@Null Object obj, @Null Class cls, @Null Class cls2) {
        StringWriter stringWriter = new StringWriter();
        toJson(obj, cls, cls2, stringWriter);
        return stringWriter.toString();
    }

    public void toJson(@Null Object obj, FileHandle fileHandle) {
        toJson(obj, obj == null ? null : obj.getClass(), (Class) null, fileHandle);
    }

    public void toJson(@Null Object obj, @Null Class cls, FileHandle fileHandle) {
        toJson(obj, cls, (Class) null, fileHandle);
    }

    public void toJson(@Null Object obj, @Null Class cls, @Null Class cls2, FileHandle fileHandle) {
        Writer writer = null;
        try {
            try {
                writer = fileHandle.writer(false, "UTF-8");
                toJson(obj, cls, cls2, writer);
                StreamUtils.closeQuietly(writer);
            } catch (Exception e) {
                throw new SerializationException("Error writing file: " + fileHandle, e);
            }
        } catch (Throwable th) {
            StreamUtils.closeQuietly(writer);
            throw th;
        }
    }

    public void toJson(@Null Object obj, Writer writer) {
        toJson(obj, obj == null ? null : obj.getClass(), (Class) null, writer);
    }

    public void toJson(@Null Object obj, @Null Class cls, Writer writer) {
        toJson(obj, cls, (Class) null, writer);
    }

    public void toJson(@Null Object obj, @Null Class cls, @Null Class cls2, Writer writer) {
        setWriter(writer);
        try {
            writeValue(obj, cls, cls2);
        } finally {
            StreamUtils.closeQuietly(this.writer);
            this.writer = null;
        }
    }

    public void setWriter(Writer writer) {
        if (!(writer instanceof JsonWriter)) {
            writer = new JsonWriter(writer);
        }
        this.writer = (JsonWriter) writer;
        this.writer.setOutputType(this.outputType);
        this.writer.setQuoteLongValues(this.quoteLongValues);
    }

    public JsonWriter getWriter() {
        return this.writer;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v19, types: [com.badlogic.gdx.utils.reflect.Field, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v27 */
    public void writeFields(Object obj) {
        Class<?> cls = obj.getClass();
        Object[] defaultValues = getDefaultValues(cls);
        OrderedMap<String, FieldMetadata> fields = getFields(cls);
        int i = 0;
        Array<String> orderedKeys = fields.orderedKeys();
        int i2 = orderedKeys.size;
        for (int i3 = 0; i3 < i2; i3++) {
            FieldMetadata fieldMetadata = fields.get(orderedKeys.get(i3));
            if (!this.ignoreDeprecated || !fieldMetadata.deprecated) {
                SerializationException serializationException = fieldMetadata.field;
                try {
                    Object obj2 = serializationException.get(obj);
                    serializationException = defaultValues;
                    if (serializationException != 0) {
                        int i4 = i;
                        i++;
                        Object obj3 = defaultValues[i4];
                        if (obj2 != null || obj3 != null) {
                            if (obj2 != null && obj3 != null) {
                                if (!obj2.equals(obj3)) {
                                    if (obj2.getClass().isArray() && obj3.getClass().isArray()) {
                                        this.equals1[0] = obj2;
                                        this.equals2[0] = obj3;
                                        if (Arrays.deepEquals(this.equals1, this.equals2)) {
                                        }
                                    }
                                }
                            }
                        }
                    }
                    this.writer.name(serializationException.getName());
                    writeValue(obj2, serializationException.getType(), fieldMetadata.elementType);
                } catch (SerializationException e) {
                    serializationException.addTrace(((Object) serializationException) + " (" + cls.getName() + ")");
                    throw e;
                } catch (ReflectionException e2) {
                    throw new SerializationException("Error accessing field: " + serializationException.getName() + " (" + cls.getName() + ")", e2);
                } catch (Exception e3) {
                    SerializationException serializationException2 = new SerializationException(e3);
                    serializationException2.addTrace(((Object) serializationException) + " (" + cls.getName() + ")");
                    throw serializationException2;
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v32, types: [com.badlogic.gdx.utils.reflect.Field, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v38 */
    @Null
    private Object[] getDefaultValues(Class cls) {
        if (!this.usePrototypes) {
            return null;
        }
        if (this.classToDefaultValues.containsKey(cls)) {
            return this.classToDefaultValues.get(cls);
        }
        try {
            Object newInstance = newInstance(cls);
            OrderedMap<String, FieldMetadata> fields = getFields(cls);
            Object[] objArr = new Object[fields.size];
            this.classToDefaultValues.put(cls, objArr);
            int i = 0;
            Array<String> orderedKeys = fields.orderedKeys();
            int i2 = orderedKeys.size;
            for (int i3 = 0; i3 < i2; i3++) {
                FieldMetadata fieldMetadata = fields.get(orderedKeys.get(i3));
                if (!this.ignoreDeprecated || !fieldMetadata.deprecated) {
                    SerializationException serializationException = fieldMetadata.field;
                    try {
                        serializationException = objArr;
                        int i4 = i;
                        i++;
                        serializationException[i4] = serializationException.get(newInstance);
                    } catch (SerializationException e) {
                        serializationException.addTrace(((Object) serializationException) + " (" + cls.getName() + ")");
                        throw e;
                    } catch (ReflectionException e2) {
                        throw new SerializationException("Error accessing field: " + serializationException.getName() + " (" + cls.getName() + ")", e2);
                    } catch (RuntimeException e3) {
                        SerializationException serializationException2 = new SerializationException(e3);
                        serializationException2.addTrace(((Object) serializationException) + " (" + cls.getName() + ")");
                        throw serializationException2;
                    }
                }
            }
            return objArr;
        } catch (Exception unused) {
            this.classToDefaultValues.put(cls, null);
            return null;
        }
    }

    public void writeField(Object obj, String str) {
        writeField(obj, str, str, null);
    }

    public void writeField(Object obj, String str, @Null Class cls) {
        writeField(obj, str, str, cls);
    }

    public void writeField(Object obj, String str, String str2) {
        writeField(obj, str, str2, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v18, types: [com.badlogic.gdx.utils.Json] */
    /* JADX WARN: Type inference failed for: r0v22 */
    /* JADX WARN: Type inference failed for: r0v23 */
    /* JADX WARN: Type inference failed for: r0v9 */
    public void writeField(Object obj, String str, String str2, @Null Class cls) {
        Class<?> cls2 = obj.getClass();
        FieldMetadata fieldMetadata = getFields(cls2).get(str);
        if (fieldMetadata == null) {
            throw new SerializationException("Field not found: " + str + " (" + cls2.getName() + ")");
        }
        Field field = fieldMetadata.field;
        Class cls3 = cls;
        SerializationException serializationException = cls3;
        if (cls3 == null) {
            Class cls4 = fieldMetadata.elementType;
            cls = cls4;
            serializationException = cls4;
        }
        try {
            this.writer.name(str2);
            serializationException = this;
            serializationException.writeValue(field.get(obj), field.getType(), cls);
        } catch (SerializationException e) {
            serializationException.addTrace(field + " (" + cls2.getName() + ")");
            throw e;
        } catch (ReflectionException e2) {
            throw new SerializationException("Error accessing field: " + field.getName() + " (" + cls2.getName() + ")", e2);
        } catch (Exception e3) {
            SerializationException serializationException2 = new SerializationException(e3);
            serializationException2.addTrace(field + " (" + cls2.getName() + ")");
            throw serializationException2;
        }
    }

    public void writeValue(String str, @Null Object obj) {
        try {
            this.writer.name(str);
            if (obj == null) {
                writeValue(obj, (Class) null, (Class) null);
            } else {
                writeValue(obj, obj.getClass(), (Class) null);
            }
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }

    public void writeValue(String str, @Null Object obj, @Null Class cls) {
        try {
            this.writer.name(str);
            writeValue(obj, cls, (Class) null);
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }

    public void writeValue(String str, @Null Object obj, @Null Class cls, @Null Class cls2) {
        try {
            this.writer.name(str);
            writeValue(obj, cls, cls2);
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }

    public void writeValue(@Null Object obj) {
        if (obj == null) {
            writeValue(obj, (Class) null, (Class) null);
        } else {
            writeValue(obj, obj.getClass(), (Class) null);
        }
    }

    public void writeValue(@Null Object obj, @Null Class cls) {
        writeValue(obj, cls, (Class) null);
    }

    public void writeValue(@Null Object obj, @Null Class cls, @Null Class cls2) {
        try {
            if (obj == null) {
                this.writer.value(null);
                return;
            }
            if ((cls != null && cls.isPrimitive()) || cls == String.class || cls == Integer.class || cls == Boolean.class || cls == Float.class || cls == Long.class || cls == Double.class || cls == Short.class || cls == Byte.class || cls == Character.class) {
                this.writer.value(obj);
                return;
            }
            Class<?> cls3 = obj.getClass();
            Class<?> cls4 = cls3;
            if (cls3.isPrimitive() || cls4 == String.class || cls4 == Integer.class || cls4 == Boolean.class || cls4 == Float.class || cls4 == Long.class || cls4 == Double.class || cls4 == Short.class || cls4 == Byte.class || cls4 == Character.class) {
                writeObjectStart(cls4, null);
                writeValue("value", obj);
                writeObjectEnd();
                return;
            }
            if (obj instanceof Serializable) {
                writeObjectStart(cls4, cls);
                ((Serializable) obj).write(this);
                writeObjectEnd();
                return;
            }
            Serializer serializer = this.classToSerializer.get(cls4);
            if (serializer != null) {
                serializer.write(this, obj, cls);
                return;
            }
            if (obj instanceof Array) {
                if (cls != null && cls4 != cls && cls4 != Array.class) {
                    throw new SerializationException("Serialization of an Array other than the known type is not supported.\nKnown type: " + cls + "\nActual type: " + cls4);
                }
                writeArrayStart();
                Array array = (Array) obj;
                int i = array.size;
                for (int i2 = 0; i2 < i; i2++) {
                    writeValue(array.get(i2), cls2, (Class) null);
                }
                writeArrayEnd();
                return;
            }
            if (obj instanceof Queue) {
                if (cls != null && cls4 != cls && cls4 != Queue.class) {
                    throw new SerializationException("Serialization of a Queue other than the known type is not supported.\nKnown type: " + cls + "\nActual type: " + cls4);
                }
                writeArrayStart();
                Queue queue = (Queue) obj;
                int i3 = queue.size;
                for (int i4 = 0; i4 < i3; i4++) {
                    writeValue(queue.get(i4), cls2, (Class) null);
                }
                writeArrayEnd();
                return;
            }
            if (obj instanceof Collection) {
                if (this.typeName != null && cls4 != ArrayList.class && (cls == null || cls != cls4)) {
                    writeObjectStart(cls4, cls);
                    writeArrayStart("items");
                    Iterator it = ((Collection) obj).iterator();
                    while (it.hasNext()) {
                        writeValue(it.next(), cls2, (Class) null);
                    }
                    writeArrayEnd();
                    writeObjectEnd();
                    return;
                }
                writeArrayStart();
                Iterator it2 = ((Collection) obj).iterator();
                while (it2.hasNext()) {
                    writeValue(it2.next(), cls2, (Class) null);
                }
                writeArrayEnd();
                return;
            }
            if (cls4.isArray()) {
                if (cls2 == null) {
                    cls2 = cls4.getComponentType();
                }
                int length = ArrayReflection.getLength(obj);
                writeArrayStart();
                for (int i5 = 0; i5 < length; i5++) {
                    writeValue(ArrayReflection.get(obj, i5), cls2, (Class) null);
                }
                writeArrayEnd();
                return;
            }
            if (obj instanceof ObjectMap) {
                if (cls == null) {
                    cls = ObjectMap.class;
                }
                writeObjectStart(cls4, cls);
                ObjectMap.Entries it3 = ((ObjectMap) obj).entries().iterator();
                while (it3.hasNext()) {
                    ObjectMap.Entry next = it3.next();
                    this.writer.name(convertToString(next.key));
                    writeValue(next.value, cls2, (Class) null);
                }
                writeObjectEnd();
                return;
            }
            if (obj instanceof ObjectIntMap) {
                if (cls == null) {
                    cls = ObjectIntMap.class;
                }
                writeObjectStart(cls4, cls);
                ObjectIntMap.Entries it4 = ((ObjectIntMap) obj).entries().iterator();
                while (it4.hasNext()) {
                    ObjectIntMap.Entry next2 = it4.next();
                    this.writer.name(convertToString(next2.key));
                    writeValue(Integer.valueOf(next2.value), Integer.class);
                }
                writeObjectEnd();
                return;
            }
            if (obj instanceof ObjectFloatMap) {
                if (cls == null) {
                    cls = ObjectFloatMap.class;
                }
                writeObjectStart(cls4, cls);
                ObjectFloatMap.Entries it5 = ((ObjectFloatMap) obj).entries().iterator();
                while (it5.hasNext()) {
                    ObjectFloatMap.Entry next3 = it5.next();
                    this.writer.name(convertToString(next3.key));
                    writeValue(Float.valueOf(next3.value), Float.class);
                }
                writeObjectEnd();
                return;
            }
            if (obj instanceof ObjectSet) {
                if (cls == null) {
                    cls = ObjectSet.class;
                }
                writeObjectStart(cls4, cls);
                this.writer.name("values");
                writeArrayStart();
                ObjectSet.ObjectSetIterator it6 = ((ObjectSet) obj).iterator();
                while (it6.hasNext()) {
                    writeValue(it6.next(), cls2, (Class) null);
                }
                writeArrayEnd();
                writeObjectEnd();
                return;
            }
            if (obj instanceof IntMap) {
                if (cls == null) {
                    cls = IntMap.class;
                }
                writeObjectStart(cls4, cls);
                Iterator it7 = ((IntMap) obj).entries().iterator();
                while (it7.hasNext()) {
                    IntMap.Entry entry = (IntMap.Entry) it7.next();
                    this.writer.name(String.valueOf(entry.key));
                    writeValue(entry.value, cls2, (Class) null);
                }
                writeObjectEnd();
                return;
            }
            if (obj instanceof LongMap) {
                if (cls == null) {
                    cls = LongMap.class;
                }
                writeObjectStart(cls4, cls);
                Iterator it8 = ((LongMap) obj).entries().iterator();
                while (it8.hasNext()) {
                    LongMap.Entry entry2 = (LongMap.Entry) it8.next();
                    this.writer.name(String.valueOf(entry2.key));
                    writeValue(entry2.value, cls2, (Class) null);
                }
                writeObjectEnd();
                return;
            }
            if (obj instanceof IntSet) {
                if (cls == null) {
                    cls = IntSet.class;
                }
                writeObjectStart(cls4, cls);
                this.writer.name("values");
                writeArrayStart();
                IntSet.IntSetIterator it9 = ((IntSet) obj).iterator();
                while (it9.hasNext) {
                    writeValue(Integer.valueOf(it9.next()), Integer.class, (Class) null);
                }
                writeArrayEnd();
                writeObjectEnd();
                return;
            }
            if (obj instanceof ArrayMap) {
                if (cls == null) {
                    cls = ArrayMap.class;
                }
                writeObjectStart(cls4, cls);
                ArrayMap arrayMap = (ArrayMap) obj;
                int i6 = arrayMap.size;
                for (int i7 = 0; i7 < i6; i7++) {
                    this.writer.name(convertToString(arrayMap.keys[i7]));
                    writeValue(arrayMap.values[i7], cls2, (Class) null);
                }
                writeObjectEnd();
                return;
            }
            if (obj instanceof Map) {
                if (cls == null) {
                    cls = HashMap.class;
                }
                writeObjectStart(cls4, cls);
                for (Map.Entry entry3 : ((Map) obj).entrySet()) {
                    this.writer.name(convertToString(entry3.getKey()));
                    writeValue(entry3.getValue(), cls2, (Class) null);
                }
                writeObjectEnd();
                return;
            }
            if (ClassReflection.isAssignableFrom(Enum.class, cls4)) {
                if (cls4.getEnumConstants() == null) {
                    cls4 = cls4.getSuperclass();
                }
                if (this.typeName != null && (cls == null || cls != cls4)) {
                    writeObjectStart(cls4, null);
                    this.writer.name("value");
                    this.writer.value(convertToString((Enum) obj));
                    writeObjectEnd();
                    return;
                }
                this.writer.value(convertToString((Enum) obj));
                return;
            }
            writeObjectStart(cls4, cls);
            writeFields(obj);
            writeObjectEnd();
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }

    public void writeObjectStart(String str) {
        try {
            this.writer.name(str);
            writeObjectStart();
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }

    public void writeObjectStart(String str, Class cls, @Null Class cls2) {
        try {
            this.writer.name(str);
            writeObjectStart(cls, cls2);
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }

    public void writeObjectStart() {
        try {
            this.writer.object();
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }

    public void writeObjectStart(Class cls, @Null Class cls2) {
        try {
            this.writer.object();
            if (cls2 == null || cls2 != cls) {
                writeType(cls);
            }
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }

    public void writeObjectEnd() {
        try {
            this.writer.pop();
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }

    public void writeArrayStart(String str) {
        try {
            this.writer.name(str);
            this.writer.array();
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }

    public void writeArrayStart() {
        try {
            this.writer.array();
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }

    public void writeArrayEnd() {
        try {
            this.writer.pop();
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }

    public void writeType(Class cls) {
        if (this.typeName == null) {
            return;
        }
        String tag = getTag(cls);
        String str = tag;
        if (tag == null) {
            str = cls.getName();
        }
        try {
            this.writer.set(this.typeName, str);
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }

    @Null
    public <T> T fromJson(Class<T> cls, Reader reader) {
        return (T) readValue(cls, (Class) null, new JsonReader().parse(reader));
    }

    @Null
    public <T> T fromJson(Class<T> cls, Class cls2, Reader reader) {
        return (T) readValue(cls, cls2, new JsonReader().parse(reader));
    }

    @Null
    public <T> T fromJson(Class<T> cls, InputStream inputStream) {
        return (T) readValue(cls, (Class) null, new JsonReader().parse(inputStream));
    }

    @Null
    public <T> T fromJson(Class<T> cls, Class cls2, InputStream inputStream) {
        return (T) readValue(cls, cls2, new JsonReader().parse(inputStream));
    }

    @Null
    public <T> T fromJson(Class<T> cls, FileHandle fileHandle) {
        try {
            return (T) readValue(cls, (Class) null, new JsonReader().parse(fileHandle));
        } catch (Exception e) {
            throw new SerializationException("Error reading file: " + fileHandle, e);
        }
    }

    @Null
    public <T> T fromJson(Class<T> cls, Class cls2, FileHandle fileHandle) {
        try {
            return (T) readValue(cls, cls2, new JsonReader().parse(fileHandle));
        } catch (Exception e) {
            throw new SerializationException("Error reading file: " + fileHandle, e);
        }
    }

    @Null
    public <T> T fromJson(Class<T> cls, char[] cArr, int i, int i2) {
        return (T) readValue(cls, (Class) null, new JsonReader().parse(cArr, i, i2));
    }

    @Null
    public <T> T fromJson(Class<T> cls, Class cls2, char[] cArr, int i, int i2) {
        return (T) readValue(cls, cls2, new JsonReader().parse(cArr, i, i2));
    }

    @Null
    public <T> T fromJson(Class<T> cls, String str) {
        return (T) readValue(cls, (Class) null, new JsonReader().parse(str));
    }

    @Null
    public <T> T fromJson(Class<T> cls, Class cls2, String str) {
        return (T) readValue(cls, cls2, new JsonReader().parse(str));
    }

    public void readField(Object obj, String str, JsonValue jsonValue) {
        readField(obj, str, str, (Class) null, jsonValue);
    }

    public void readField(Object obj, String str, @Null Class cls, JsonValue jsonValue) {
        readField(obj, str, str, cls, jsonValue);
    }

    public void readField(Object obj, String str, String str2, JsonValue jsonValue) {
        readField(obj, str, str2, (Class) null, jsonValue);
    }

    public void readField(Object obj, String str, String str2, @Null Class cls, JsonValue jsonValue) {
        Class<?> cls2 = obj.getClass();
        FieldMetadata fieldMetadata = getFields(cls2).get(str);
        if (fieldMetadata == null) {
            throw new SerializationException("Field not found: " + str + " (" + cls2.getName() + ")");
        }
        Field field = fieldMetadata.field;
        if (cls == null) {
            cls = fieldMetadata.elementType;
        }
        readField(obj, field, str2, cls, jsonValue);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [com.badlogic.gdx.utils.JsonValue] */
    /* JADX WARN: Type inference failed for: r0v8, types: [com.badlogic.gdx.utils.reflect.Field] */
    public void readField(@Null Object obj, Field field, String str, @Null Class cls, JsonValue jsonValue) {
        SerializationException serializationException = jsonValue.get(str);
        if (serializationException == 0) {
            return;
        }
        try {
            serializationException = field;
            serializationException.set(obj, readValue(field.getType(), cls, (JsonValue) serializationException));
        } catch (SerializationException e) {
            serializationException.addTrace(field.getName() + " (" + field.getDeclaringClass().getName() + ")");
            throw e;
        } catch (ReflectionException e2) {
            throw new SerializationException("Error accessing field: " + field.getName() + " (" + field.getDeclaringClass().getName() + ")", e2);
        } catch (RuntimeException e3) {
            SerializationException serializationException2 = new SerializationException(e3);
            serializationException2.addTrace(serializationException.trace());
            serializationException2.addTrace(field.getName() + " (" + field.getDeclaringClass().getName() + ")");
            throw serializationException2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v14, types: [com.badlogic.gdx.utils.reflect.Field] */
    /* JADX WARN: Type inference failed for: r0v21, types: [com.badlogic.gdx.utils.reflect.Field] */
    public void readFields(Object obj, JsonValue jsonValue) {
        Class<?> cls = obj.getClass();
        OrderedMap<String, FieldMetadata> fields = getFields(cls);
        JsonValue jsonValue2 = jsonValue.child;
        while (true) {
            JsonValue jsonValue3 = jsonValue2;
            if (jsonValue3 != null) {
                FieldMetadata fieldMetadata = fields.get(jsonValue3.name().replace(SequenceUtils.SPACE, JavaConstant.Dynamic.DEFAULT_NAME));
                if (fieldMetadata == null) {
                    if (!jsonValue3.name.equals(this.typeName) && !this.ignoreUnknownFields && !ignoreUnknownField(cls, jsonValue3.name)) {
                        SerializationException serializationException = new SerializationException("Field not found: " + jsonValue3.name + " (" + cls.getName() + ")");
                        serializationException.addTrace(jsonValue3.trace());
                        throw serializationException;
                    }
                } else if (!this.ignoreDeprecated || this.readDeprecated || !fieldMetadata.deprecated) {
                    SerializationException serializationException2 = fieldMetadata.field;
                    try {
                        serializationException2 = serializationException2;
                        serializationException2.set(obj, readValue(serializationException2.getType(), fieldMetadata.elementType, jsonValue3));
                    } catch (SerializationException e) {
                        serializationException2.addTrace(serializationException2.getName() + " (" + cls.getName() + ")");
                        throw e;
                    } catch (ReflectionException e2) {
                        throw new SerializationException("Error accessing field: " + serializationException2.getName() + " (" + cls.getName() + ")", e2);
                    } catch (RuntimeException e3) {
                        SerializationException serializationException3 = new SerializationException(e3);
                        serializationException3.addTrace(jsonValue3.trace());
                        serializationException3.addTrace(serializationException2.getName() + " (" + cls.getName() + ")");
                        throw serializationException3;
                    }
                }
                jsonValue2 = jsonValue3.next;
            } else {
                return;
            }
        }
    }

    protected boolean ignoreUnknownField(Class cls, String str) {
        return false;
    }

    @Null
    public <T> T readValue(String str, @Null Class<T> cls, JsonValue jsonValue) {
        return (T) readValue(cls, (Class) null, jsonValue.get(str));
    }

    @Null
    public <T> T readValue(String str, @Null Class<T> cls, T t, JsonValue jsonValue) {
        JsonValue jsonValue2 = jsonValue.get(str);
        return jsonValue2 == null ? t : (T) readValue(cls, (Class) null, jsonValue2);
    }

    @Null
    public <T> T readValue(String str, @Null Class<T> cls, @Null Class cls2, JsonValue jsonValue) {
        return (T) readValue(cls, cls2, jsonValue.get(str));
    }

    @Null
    public <T> T readValue(String str, @Null Class<T> cls, @Null Class cls2, T t, JsonValue jsonValue) {
        return (T) readValue((Class<Class>) cls, cls2, (Class) t, jsonValue.get(str));
    }

    @Null
    public <T> T readValue(@Null Class<T> cls, @Null Class cls2, T t, JsonValue jsonValue) {
        return jsonValue == null ? t : (T) readValue(cls, cls2, jsonValue);
    }

    @Null
    public <T> T readValue(@Null Class<T> cls, JsonValue jsonValue) {
        return (T) readValue(cls, (Class) null, jsonValue);
    }

    /* JADX WARN: Code restructure failed: missing block: B:285:0x05b0, code lost:            if (r9 != java.lang.Boolean.class) goto L268;     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:288:0x05d2  */
    /* JADX WARN: Removed duplicated region for block: B:362:0x06f6 A[RETURN] */
    /* JADX WARN: Type inference failed for: r0v14, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v219, types: [T, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v242, types: [T, java.util.Map] */
    /* JADX WARN: Type inference failed for: r0v256, types: [T, com.badlogic.gdx.utils.ArrayMap] */
    /* JADX WARN: Type inference failed for: r0v267, types: [T, com.badlogic.gdx.utils.IntSet] */
    /* JADX WARN: Type inference failed for: r0v278, types: [com.badlogic.gdx.utils.LongMap, T] */
    /* JADX WARN: Type inference failed for: r0v289, types: [T, com.badlogic.gdx.utils.IntMap] */
    /* JADX WARN: Type inference failed for: r0v300, types: [T, com.badlogic.gdx.utils.ObjectSet] */
    /* JADX WARN: Type inference failed for: r0v311, types: [T, com.badlogic.gdx.utils.ObjectFloatMap] */
    /* JADX WARN: Type inference failed for: r0v321, types: [T, com.badlogic.gdx.utils.ObjectIntMap] */
    /* JADX WARN: Type inference failed for: r0v331, types: [T, com.badlogic.gdx.utils.ObjectMap] */
    /* JADX WARN: Type inference failed for: r0v40, types: [java.lang.Enum, T] */
    /* JADX WARN: Type inference failed for: r11v0, types: [com.badlogic.gdx.utils.JsonValue, T] */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v2 */
    /* JADX WARN: Type inference failed for: r11v29 */
    /* JADX WARN: Type inference failed for: r11v30 */
    /* JADX WARN: Type inference failed for: r11v31 */
    /* JADX WARN: Type inference failed for: r11v5 */
    /* JADX WARN: Type inference failed for: r12v2, types: [T, java.util.Collection] */
    /* JADX WARN: Type inference failed for: r12v3, types: [T, com.badlogic.gdx.utils.Queue] */
    /* JADX WARN: Type inference failed for: r12v4, types: [T, com.badlogic.gdx.utils.Array] */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.badlogic.gdx.utils.Json] */
    @com.badlogic.gdx.utils.Null
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public <T> T readValue(@com.badlogic.gdx.utils.Null java.lang.Class<T> r9, @com.badlogic.gdx.utils.Null java.lang.Class r10, com.badlogic.gdx.utils.JsonValue r11) {
        /*
            Method dump skipped, instructions count: 1784
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.badlogic.gdx.utils.Json.readValue(java.lang.Class, java.lang.Class, com.badlogic.gdx.utils.JsonValue):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void copyFields(Object obj, Object obj2) {
        OrderedMap<String, FieldMetadata> fields = getFields(obj2.getClass());
        ObjectMap.Entries<String, FieldMetadata> it = getFields(obj.getClass()).iterator();
        while (it.hasNext()) {
            ObjectMap.Entry next = it.next();
            FieldMetadata fieldMetadata = fields.get(next.key);
            Field field = ((FieldMetadata) next.value).field;
            if (fieldMetadata == null) {
                throw new SerializationException("To object is missing field: " + ((String) next.key));
            }
            try {
                fieldMetadata.field.set(obj2, field.get(obj));
            } catch (ReflectionException e) {
                throw new SerializationException("Error copying field: " + field.getName(), e);
            }
        }
    }

    private String convertToString(Enum r3) {
        return this.enumNames ? r3.name() : r3.toString();
    }

    private String convertToString(Object obj) {
        return obj instanceof Enum ? convertToString((Enum) obj) : obj instanceof Class ? ((Class) obj).getName() : String.valueOf(obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v24, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v26, types: [java.lang.Object] */
    protected Object newInstance(Class cls) {
        ?? r0;
        try {
            r0 = ClassReflection.newInstance(cls);
            return r0;
        } catch (Exception e) {
            e = e;
            try {
                Constructor declaredConstructor = ClassReflection.getDeclaredConstructor(cls, new Class[0]);
                declaredConstructor.setAccessible(true);
                r0 = declaredConstructor.newInstance(new Object[0]);
                return r0;
            } catch (ReflectionException unused) {
                if (ClassReflection.isAssignableFrom(Enum.class, cls)) {
                    if (cls.getEnumConstants() == null) {
                        cls = cls.getSuperclass();
                    }
                    return cls.getEnumConstants()[0];
                }
                if (cls.isArray()) {
                    throw new SerializationException("Encountered JSON object when expected array of type: " + cls.getName(), e);
                }
                if (ClassReflection.isMemberClass(cls) && !ClassReflection.isStaticClass(cls)) {
                    throw new SerializationException("Class cannot be created (non-static member class): " + cls.getName(), e);
                }
                throw new SerializationException("Class cannot be created (missing no-arg constructor): " + cls.getName(), e);
            } catch (SecurityException unused2) {
                throw new SerializationException("Error constructing instance of class: " + cls.getName(), e);
            } catch (Exception e2) {
                e = r0;
                throw new SerializationException("Error constructing instance of class: " + cls.getName(), e);
            }
        }
    }

    public String prettyPrint(@Null Object obj) {
        return prettyPrint(obj, 0);
    }

    public String prettyPrint(String str) {
        return prettyPrint(str, 0);
    }

    public String prettyPrint(@Null Object obj, int i) {
        return prettyPrint(toJson(obj), i);
    }

    public String prettyPrint(String str, int i) {
        return new JsonReader().parse(str).prettyPrint(this.outputType, i);
    }

    public String prettyPrint(@Null Object obj, JsonValue.PrettyPrintSettings prettyPrintSettings) {
        return prettyPrint(toJson(obj), prettyPrintSettings);
    }

    public String prettyPrint(String str, JsonValue.PrettyPrintSettings prettyPrintSettings) {
        return new JsonReader().parse(str).prettyPrint(prettyPrintSettings);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/Json$FieldMetadata.class */
    public static class FieldMetadata {
        final Field field;
        Class elementType;
        boolean deprecated;

        public FieldMetadata(Field field) {
            this.field = field;
            this.elementType = field.getElementType((ClassReflection.isAssignableFrom(ObjectMap.class, field.getType()) || ClassReflection.isAssignableFrom(Map.class, field.getType())) ? 1 : 0);
            this.deprecated = field.isAnnotationPresent(Deprecated.class);
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/Json$ReadOnlySerializer.class */
    public static abstract class ReadOnlySerializer<T> implements Serializer<T> {
        @Override // com.badlogic.gdx.utils.Json.Serializer
        public abstract T read(Json json, JsonValue jsonValue, Class cls);

        @Override // com.badlogic.gdx.utils.Json.Serializer
        public void write(Json json, T t, Class cls) {
        }
    }
}
