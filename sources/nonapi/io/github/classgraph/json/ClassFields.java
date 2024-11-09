package nonapi.io.github.classgraph.json;

import io.github.classgraph.ScanResult;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import nonapi.io.github.classgraph.reflection.ReflectionUtils;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/json/ClassFields.class */
class ClassFields {
    final List<FieldTypeInfo> fieldOrder = new ArrayList();
    final Map<String, FieldTypeInfo> fieldNameToFieldTypeInfo = new HashMap();
    Field idField;
    private static final Comparator<Field> FIELD_NAME_ORDER_COMPARATOR = new Comparator<Field>() { // from class: nonapi.io.github.classgraph.json.ClassFields.1
        @Override // java.util.Comparator
        public final int compare(Field field, Field field2) {
            return field.getName().compareTo(field2.getName());
        }
    };
    private static final Comparator<Field> SERIALIZATION_FORMAT_FIELD_NAME_ORDER_COMPARATOR = new Comparator<Field>() { // from class: nonapi.io.github.classgraph.json.ClassFields.2
        @Override // java.util.Comparator
        public final int compare(Field field, Field field2) {
            if (field.getName().equals("format")) {
                return -1;
            }
            if (field2.getName().equals("format")) {
                return 1;
            }
            return field.getName().compareTo(field2.getName());
        }
    };
    private static final String SERIALIZATION_FORMAT_CLASS_NAME = ScanResult.class.getName() + "$SerializationFormat";

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v36, types: [java.lang.reflect.Type, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v41, types: [nonapi.io.github.classgraph.json.TypeResolutions] */
    /* JADX WARN: Type inference failed for: r1v20 */
    /* JADX WARN: Type inference failed for: r2v8, types: [java.lang.StringBuilder] */
    public ClassFields(Class<?> cls, boolean z, boolean z2, ClassFieldCache classFieldCache, ReflectionUtils reflectionUtils) {
        Class<?> cls2;
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        TypeResolutions typeResolutions = null;
        Class<?> cls3 = cls;
        while (cls3 != Object.class && cls3 != null) {
            if (cls3 instanceof ParameterizedType) {
                cls2 = (Class) ((ParameterizedType) cls3).getRawType();
            } else if (cls3 instanceof Class) {
                cls2 = cls3;
            } else {
                throw new IllegalArgumentException("Illegal class type: " + cls3);
            }
            Field[] declaredFields = cls2.getDeclaredFields();
            Arrays.sort(declaredFields, cls.getName().equals(SERIALIZATION_FORMAT_CLASS_NAME) ? SERIALIZATION_FORMAT_FIELD_NAME_ORDER_COMPARATOR : FIELD_NAME_ORDER_COMPARATOR);
            ArrayList arrayList2 = new ArrayList();
            for (Field field : declaredFields) {
                if (hashSet.add(field.getName())) {
                    boolean isAnnotationPresent = field.isAnnotationPresent(Id.class);
                    if (isAnnotationPresent) {
                        if (this.idField != null) {
                            throw new IllegalArgumentException("More than one @Id annotation: " + this.idField.getDeclaringClass() + "." + this.idField + " ; " + cls2.getName() + "." + field.getName());
                        }
                        this.idField = field;
                    }
                    if (!JSONUtils.fieldIsSerializable(field, z2, reflectionUtils)) {
                        if (isAnnotationPresent) {
                            throw new IllegalArgumentException("@Id annotation field must be accessible, final, and non-transient: " + cls2.getName() + "." + field.getName());
                        }
                    } else {
                        Type genericType = field.getGenericType();
                        FieldTypeInfo fieldTypeInfo = new FieldTypeInfo(field, (typeResolutions == null || !z) ? genericType : typeResolutions.resolveTypeVariables(genericType), classFieldCache);
                        this.fieldNameToFieldTypeInfo.put(field.getName(), fieldTypeInfo);
                        arrayList2.add(fieldTypeInfo);
                    }
                }
            }
            arrayList.add(arrayList2);
            ?? genericSuperclass = cls2.getGenericSuperclass();
            if (z) {
                if (genericSuperclass instanceof ParameterizedType) {
                    Type resolveTypeVariables = typeResolutions == null ? genericSuperclass : typeResolutions.resolveTypeVariables(genericSuperclass);
                    ?? r1 = resolveTypeVariables;
                    typeResolutions = resolveTypeVariables instanceof ParameterizedType ? new TypeResolutions((ParameterizedType) r1) : null;
                    cls3 = r1;
                } else if (genericSuperclass instanceof Class) {
                    cls3 = genericSuperclass;
                    typeResolutions = null;
                } else {
                    throw new IllegalArgumentException("Got unexpected supertype " + genericSuperclass);
                }
            } else {
                cls3 = genericSuperclass;
            }
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            this.fieldOrder.addAll((List) arrayList.get(size));
        }
    }
}
