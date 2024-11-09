package nonapi.io.github.classgraph.json;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import nonapi.io.github.classgraph.reflection.ReflectionUtils;
import nonapi.io.github.classgraph.utils.CollectionUtils;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/json/JSONSerializer.class */
public final class JSONSerializer {
    private static final Comparator<Object> SET_COMPARATOR = new Comparator<Object>() { // from class: nonapi.io.github.classgraph.json.JSONSerializer.1
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            if (obj == null || obj2 == null) {
                return (obj == null ? 0 : 1) - (obj2 == null ? 0 : 1);
            }
            if (Comparable.class.isAssignableFrom(obj.getClass()) && Comparable.class.isAssignableFrom(obj2.getClass())) {
                return ((Comparable) obj).compareTo(obj2);
            }
            return obj.toString().compareTo(obj2.toString());
        }
    };

    private JSONSerializer() {
    }

    private static void assignObjectIds(Object obj, Map<ReferenceEqualityKey<Object>, JSONObject> map, ClassFieldCache classFieldCache, Map<ReferenceEqualityKey<JSONReference>, CharSequence> map2, AtomicInteger atomicInteger, boolean z) {
        if (obj instanceof JSONObject) {
            Iterator<Map.Entry<String, Object>> it = ((JSONObject) obj).items.iterator();
            while (it.hasNext()) {
                assignObjectIds(it.next().getValue(), map, classFieldCache, map2, atomicInteger, z);
            }
            return;
        }
        if (obj instanceof JSONArray) {
            Iterator<Object> it2 = ((JSONArray) obj).items.iterator();
            while (it2.hasNext()) {
                assignObjectIds(it2.next(), map, classFieldCache, map2, atomicInteger, z);
            }
            return;
        }
        if (obj instanceof JSONReference) {
            Object obj2 = ((JSONReference) obj).idObject;
            if (obj2 == null) {
                throw new RuntimeException("Internal inconsistency");
            }
            JSONObject jSONObject = map.get(new ReferenceEqualityKey(obj2));
            if (jSONObject == null) {
                throw new RuntimeException("Internal inconsistency");
            }
            Field field = classFieldCache.get(obj2.getClass()).idField;
            CharSequence charSequence = null;
            if (field != null) {
                try {
                    Object obj3 = field.get(obj2);
                    if (obj3 != null) {
                        charSequence = obj3.toString();
                        jSONObject.objectId = charSequence;
                    }
                } catch (IllegalAccessException | IllegalArgumentException e) {
                    throw new IllegalArgumentException("Could not access @Id-annotated field " + field, e);
                }
            }
            if (charSequence == null) {
                if (jSONObject.objectId == null) {
                    charSequence = "[#" + atomicInteger.getAndIncrement() + "]";
                    jSONObject.objectId = charSequence;
                } else {
                    charSequence = jSONObject.objectId;
                }
            }
            map2.put(new ReferenceEqualityKey<>((JSONReference) obj), charSequence);
        }
    }

    private static void convertVals(Object[] objArr, Set<ReferenceEqualityKey<Object>> set, Set<ReferenceEqualityKey<Object>> set2, ClassFieldCache classFieldCache, Map<ReferenceEqualityKey<Object>, JSONObject> map, boolean z) {
        ReferenceEqualityKey<Object>[] referenceEqualityKeyArr = new ReferenceEqualityKey[objArr.length];
        boolean[] zArr = new boolean[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            Object obj = objArr[i];
            zArr[i] = !JSONUtils.isBasicValueType(obj);
            if (zArr[i] && !JSONUtils.isCollectionOrArray(obj)) {
                ReferenceEqualityKey<Object> referenceEqualityKey = new ReferenceEqualityKey<>(obj);
                referenceEqualityKeyArr[i] = referenceEqualityKey;
                if (!set2.add(referenceEqualityKey)) {
                    objArr[i] = new JSONReference(obj);
                    zArr[i] = false;
                }
            }
            if (obj instanceof Class) {
                objArr[i] = ((Class) obj).getName();
            }
        }
        for (int i2 = 0; i2 < objArr.length; i2++) {
            if (zArr[i2]) {
                Object obj2 = objArr[i2];
                objArr[i2] = toJSONGraph(obj2, set, set2, classFieldCache, map, z);
                if (!JSONUtils.isCollectionOrArray(obj2)) {
                    map.put(referenceEqualityKeyArr[i2], (JSONObject) objArr[i2]);
                }
            }
        }
    }

    private static Object toJSONGraph(Object obj, Set<ReferenceEqualityKey<Object>> set, Set<ReferenceEqualityKey<Object>> set2, ClassFieldCache classFieldCache, Map<ReferenceEqualityKey<Object>, JSONObject> map, boolean z) {
        int length;
        Object jSONArray;
        Object obj2;
        if (obj instanceof Class) {
            return ((Class) obj).getName();
        }
        if (JSONUtils.isBasicValueType(obj)) {
            return obj;
        }
        ReferenceEqualityKey<Object> referenceEqualityKey = new ReferenceEqualityKey<>(obj);
        if (!set.add(referenceEqualityKey)) {
            if (JSONUtils.isCollectionOrArray(obj)) {
                throw new IllegalArgumentException("Cycles involving collections cannot be serialized, since collections are not assigned object ids. Reached cycle at: " + obj);
            }
            return new JSONReference(obj);
        }
        Class<?> cls = obj.getClass();
        boolean isArray = cls.isArray();
        if (!Map.class.isAssignableFrom(cls)) {
            if (isArray || List.class.isAssignableFrom(cls)) {
                List list = List.class.isAssignableFrom(cls) ? (List) obj : null;
                List list2 = list;
                if (list != null) {
                    length = list2.size();
                } else {
                    length = isArray ? Array.getLength(obj) : 0;
                }
                int i = length;
                Object[] objArr = new Object[length];
                for (int i2 = 0; i2 < i; i2++) {
                    int i3 = i2;
                    if (list2 != null) {
                        obj2 = list2.get(i2);
                    } else {
                        obj2 = isArray ? Array.get(obj, i2) : 0;
                    }
                    objArr[i3] = obj2;
                }
                convertVals(objArr, set, set2, classFieldCache, map, z);
                jSONArray = new JSONArray(Arrays.asList(objArr));
            } else if (Collection.class.isAssignableFrom(cls)) {
                ArrayList arrayList = new ArrayList((Collection) obj);
                if (Set.class.isAssignableFrom(cls)) {
                    CollectionUtils.sortIfNotEmpty(arrayList, SET_COMPARATOR);
                }
                Object[] array = arrayList.toArray();
                convertVals(array, set, set2, classFieldCache, map, z);
                jSONArray = new JSONArray(Arrays.asList(array));
            } else {
                List<FieldTypeInfo> list3 = classFieldCache.get(cls).fieldOrder;
                int size = list3.size();
                String[] strArr = new String[size];
                Object[] objArr2 = new Object[size];
                for (int i4 = 0; i4 < size; i4++) {
                    Field field = list3.get(i4).field;
                    strArr[i4] = field.getName();
                    try {
                        objArr2[i4] = JSONUtils.getFieldValue(obj, field);
                    } catch (IllegalAccessException | IllegalArgumentException e) {
                        throw new RuntimeException("Could not get value of field \"" + strArr[i4] + "\" in object of class " + obj.getClass().getName(), e);
                    }
                }
                convertVals(objArr2, set, set2, classFieldCache, map, z);
                ArrayList arrayList2 = new ArrayList(size);
                for (int i5 = 0; i5 < size; i5++) {
                    arrayList2.add(new AbstractMap.SimpleEntry(strArr[i5], objArr2[i5]));
                }
                jSONArray = new JSONObject(arrayList2);
            }
        } else {
            Map map2 = (Map) obj;
            ArrayList arrayList3 = new ArrayList(map2.keySet());
            int size2 = arrayList3.size();
            boolean z2 = false;
            Object obj3 = null;
            for (int i6 = 0; i6 < size2 && obj3 == null; i6++) {
                obj3 = arrayList3.get(i6);
            }
            if (obj3 != null && Comparable.class.isAssignableFrom(obj3.getClass())) {
                CollectionUtils.sortIfNotEmpty(arrayList3);
                z2 = true;
            }
            String[] strArr2 = new String[size2];
            for (int i7 = 0; i7 < size2; i7++) {
                Object obj4 = arrayList3.get(i7);
                if (obj4 != null && !JSONUtils.isBasicValueType(obj4)) {
                    throw new IllegalArgumentException("Map key of type " + obj4.getClass().getName() + " is not a basic type (String, Integer, etc.), so can't be easily serialized as a JSON associative array key");
                }
                strArr2[i7] = JSONUtils.escapeJSONString(obj4 == null ? "null" : obj4.toString());
            }
            if (!z2) {
                Arrays.sort(strArr2);
            }
            Object[] objArr3 = new Object[size2];
            for (int i8 = 0; i8 < size2; i8++) {
                objArr3[i8] = map2.get(arrayList3.get(i8));
            }
            convertVals(objArr3, set, set2, classFieldCache, map, z);
            ArrayList arrayList4 = new ArrayList(size2);
            for (int i9 = 0; i9 < size2; i9++) {
                arrayList4.add(new AbstractMap.SimpleEntry(strArr2[i9], objArr3[i9]));
            }
            jSONArray = new JSONObject(arrayList4);
        }
        set.remove(referenceEqualityKey);
        return jSONArray;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void jsonValToJSONString(Object obj, Map<ReferenceEqualityKey<JSONReference>, CharSequence> map, boolean z, int i, int i2, StringBuilder sb) {
        if (obj == null) {
            sb.append("null");
            return;
        }
        if (obj instanceof JSONObject) {
            ((JSONObject) obj).toJSONString(map, z, i, i2, sb);
            return;
        }
        if (obj instanceof JSONArray) {
            ((JSONArray) obj).toJSONString(map, z, i, i2, sb);
            return;
        }
        if (obj instanceof JSONReference) {
            jsonValToJSONString(map.get(new ReferenceEqualityKey((JSONReference) obj)), map, z, i, i2, sb);
            return;
        }
        if ((obj instanceof CharSequence) || (obj instanceof Character) || obj.getClass().isEnum()) {
            sb.append('\"');
            JSONUtils.escapeJSONString(obj.toString(), sb);
            sb.append('\"');
            return;
        }
        sb.append(obj);
    }

    public static String serializeObject(Object obj, int i, boolean z, ClassFieldCache classFieldCache) {
        HashMap hashMap = new HashMap();
        Object jSONGraph = toJSONGraph(obj, new HashSet(), new HashSet(), classFieldCache, hashMap, z);
        HashMap hashMap2 = new HashMap();
        assignObjectIds(jSONGraph, hashMap, classFieldCache, hashMap2, new AtomicInteger(0), z);
        StringBuilder sb = new StringBuilder(32768);
        jsonValToJSONString(jSONGraph, hashMap2, false, 0, i, sb);
        return sb.toString();
    }

    public static String serializeObject(Object obj, int i, boolean z, ReflectionUtils reflectionUtils) {
        return serializeObject(obj, i, z, new ClassFieldCache(false, false, reflectionUtils));
    }

    public static String serializeObject(Object obj, int i, boolean z) {
        return serializeObject(obj, i, z, new ReflectionUtils());
    }

    public static String serializeObject(Object obj) {
        return serializeObject(obj, 0, false);
    }

    public static String serializeFromField(Object obj, String str, int i, boolean z, ClassFieldCache classFieldCache) {
        FieldTypeInfo fieldTypeInfo = classFieldCache.get(obj.getClass()).fieldNameToFieldTypeInfo.get(str);
        if (fieldTypeInfo == null) {
            throw new IllegalArgumentException("Class " + obj.getClass().getName() + " does not have a field named \"" + str + "\"");
        }
        Field field = fieldTypeInfo.field;
        if (!JSONUtils.fieldIsSerializable(field, false, classFieldCache.reflectionUtils)) {
            throw new IllegalArgumentException("Field " + obj.getClass().getName() + "." + str + " needs to be accessible, non-transient, and non-final");
        }
        try {
            return serializeObject(JSONUtils.getFieldValue(obj, field), i, z, classFieldCache);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Could get value of field " + str, e);
        }
    }

    public static String serializeFromField(Object obj, String str, int i, boolean z, ReflectionUtils reflectionUtils) {
        return serializeFromField(obj, str, i, z, new ClassFieldCache(false, z, reflectionUtils));
    }

    public static String serializeFromField(Object obj, String str, int i, boolean z) {
        return serializeFromField(obj, str, i, z, new ReflectionUtils());
    }
}
