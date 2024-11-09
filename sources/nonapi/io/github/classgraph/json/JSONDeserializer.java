package nonapi.io.github.classgraph.json;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import nonapi.io.github.classgraph.reflection.ReflectionUtils;
import nonapi.io.github.classgraph.types.ParseException;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/json/JSONDeserializer.class */
public class JSONDeserializer {
    private JSONDeserializer() {
    }

    private static Object jsonBasicValueToObject(Object obj, Type type, boolean z) {
        if (obj == null) {
            return null;
        }
        if ((obj instanceof JSONArray) || (obj instanceof JSONObject)) {
            throw new RuntimeException("Expected a basic value type");
        }
        if (type instanceof ParameterizedType) {
            if (((ParameterizedType) type).getRawType().getClass() == Class.class) {
                String obj2 = obj.toString();
                int indexOf = obj2.indexOf(60);
                try {
                    return Class.forName(obj2.substring(0, indexOf < 0 ? obj2.length() : indexOf));
                } catch (ClassNotFoundException e) {
                    throw new IllegalArgumentException("Could not deserialize class reference " + obj, e);
                }
            }
            throw new IllegalArgumentException("Got illegal ParameterizedType: " + type);
        }
        if (!(type instanceof Class)) {
            throw new IllegalArgumentException("Got illegal basic value type: " + type);
        }
        Class cls = (Class) type;
        if (cls == String.class) {
            if (!(obj instanceof CharSequence)) {
                throw new IllegalArgumentException("Expected string; got " + obj.getClass().getName());
            }
            return obj.toString();
        }
        if (cls == CharSequence.class) {
            if (!(obj instanceof CharSequence)) {
                throw new IllegalArgumentException("Expected CharSequence; got " + obj.getClass().getName());
            }
            return obj;
        }
        if (cls == Integer.class || cls == Integer.TYPE) {
            if (z && (obj instanceof CharSequence)) {
                return Integer.valueOf(Integer.parseInt(obj.toString()));
            }
            if (!(obj instanceof Integer)) {
                throw new IllegalArgumentException("Expected integer; got " + obj.getClass().getName());
            }
            return obj;
        }
        if (cls == Long.class || cls == Long.TYPE) {
            boolean z2 = obj instanceof Long;
            boolean z3 = obj instanceof Integer;
            if (z && (obj instanceof CharSequence)) {
                return Long.valueOf(z2 ? Long.parseLong(obj.toString()) : Integer.parseInt(obj.toString()));
            }
            if (!z2 && !z3) {
                throw new IllegalArgumentException("Expected long; got " + obj.getClass().getName());
            }
            if (z2) {
                return obj;
            }
            return Long.valueOf(((Integer) obj).intValue());
        }
        if (cls == Short.class || cls == Short.TYPE) {
            if (z && (obj instanceof CharSequence)) {
                return Short.valueOf(Short.parseShort(obj.toString()));
            }
            if (!(obj instanceof Integer)) {
                throw new IllegalArgumentException("Expected short; got " + obj.getClass().getName());
            }
            int intValue = ((Integer) obj).intValue();
            if (intValue < -32768 || intValue > 32767) {
                throw new IllegalArgumentException("Expected short; got out-of-range value " + intValue);
            }
            return Short.valueOf((short) intValue);
        }
        if (cls == Float.class || cls == Float.TYPE) {
            if (z && (obj instanceof CharSequence)) {
                return Float.valueOf(Float.parseFloat(obj.toString()));
            }
            if (!(obj instanceof Double)) {
                throw new IllegalArgumentException("Expected float; got " + obj.getClass().getName());
            }
            double doubleValue = ((Double) obj).doubleValue();
            if (doubleValue < -3.4028234663852886E38d || doubleValue > 3.4028234663852886E38d) {
                throw new IllegalArgumentException("Expected float; got out-of-range value " + doubleValue);
            }
            return Float.valueOf((float) doubleValue);
        }
        if (cls == Double.class || cls == Double.TYPE) {
            if (z && (obj instanceof CharSequence)) {
                return Double.valueOf(Double.parseDouble(obj.toString()));
            }
            if (!(obj instanceof Double)) {
                throw new IllegalArgumentException("Expected double; got " + obj.getClass().getName());
            }
            return obj;
        }
        if (cls == Byte.class || cls == Byte.TYPE) {
            if (z && (obj instanceof CharSequence)) {
                return Byte.valueOf(Byte.parseByte(obj.toString()));
            }
            if (!(obj instanceof Integer)) {
                throw new IllegalArgumentException("Expected byte; got " + obj.getClass().getName());
            }
            int intValue2 = ((Integer) obj).intValue();
            if (intValue2 < -128 || intValue2 > 127) {
                throw new IllegalArgumentException("Expected byte; got out-of-range value " + intValue2);
            }
            return Byte.valueOf((byte) intValue2);
        }
        if (cls == Character.class || cls == Character.TYPE) {
            if (!(obj instanceof CharSequence)) {
                throw new IllegalArgumentException("Expected character; got " + obj.getClass().getName());
            }
            CharSequence charSequence = (CharSequence) obj;
            if (charSequence.length() != 1) {
                throw new IllegalArgumentException("Expected single character; got string");
            }
            return Character.valueOf(charSequence.charAt(0));
        }
        if (cls != Boolean.class && cls != Boolean.TYPE) {
            if (Enum.class.isAssignableFrom(cls)) {
                if (!(obj instanceof CharSequence)) {
                    throw new IllegalArgumentException("Expected string for enum value; got " + obj.getClass().getName());
                }
                return Enum.valueOf(cls, obj.toString());
            }
            if (JSONUtils.getRawType(type).isAssignableFrom(obj.getClass())) {
                return obj;
            }
            throw new IllegalArgumentException("Got type " + obj.getClass() + "; expected " + type);
        }
        if (z && (obj instanceof CharSequence)) {
            return Boolean.valueOf(Boolean.parseBoolean(obj.toString()));
        }
        if (!(obj instanceof Boolean)) {
            throw new IllegalArgumentException("Expected boolean; got " + obj.getClass().getName());
        }
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/json/JSONDeserializer$ObjectInstantiation.class */
    public static class ObjectInstantiation {
        Object jsonVal;
        Object objectInstance;
        Type type;

        public ObjectInstantiation(Object obj, Type type, Object obj2) {
            this.jsonVal = obj2;
            this.objectInstance = obj;
            this.type = type;
        }
    }

    private static void populateObjectFromJsonObject(Object obj, Type type, Object obj2, ClassFieldCache classFieldCache, Map<CharSequence, Object> map, List<Runnable> list) {
        TypeResolutions typeResolutions;
        Type type2;
        Type type3;
        Type type4;
        boolean z;
        Class<?> cls;
        Constructor<?> constructor;
        Constructor<?> defaultConstructorForConcreteTypeOf;
        int size;
        String str;
        Object obj3;
        FieldTypeInfo fieldTypeInfo;
        Type type5;
        int size2;
        Object newInstance;
        Object obj4;
        if (obj2 == null) {
            return;
        }
        boolean z2 = obj2 instanceof JSONObject;
        boolean z3 = obj2 instanceof JSONArray;
        if (!z3 && !z2) {
            throw new IllegalArgumentException("Expected JSONObject or JSONArray, got " + obj2.getClass().getSimpleName());
        }
        JSONObject jSONObject = z2 ? (JSONObject) obj2 : null;
        JSONArray jSONArray = z3 ? (JSONArray) obj2 : null;
        Class<?> cls2 = obj.getClass();
        boolean isAssignableFrom = Map.class.isAssignableFrom(cls2);
        Map map2 = isAssignableFrom ? (Map) obj : null;
        boolean isAssignableFrom2 = Collection.class.isAssignableFrom(cls2);
        final Collection collection = isAssignableFrom2 ? (Collection) obj : null;
        boolean isArray = cls2.isArray();
        boolean z4 = (isAssignableFrom || isAssignableFrom2 || isArray) ? false : true;
        if ((isAssignableFrom || z4) == z2) {
            if ((isAssignableFrom2 || isArray) == z3) {
                Type type6 = type;
                if (type instanceof Class) {
                    Class cls3 = (Class) type;
                    if (Map.class.isAssignableFrom(cls3)) {
                        if (!isAssignableFrom) {
                            throw new IllegalArgumentException("Got an unexpected map type");
                        }
                        type6 = cls3.getGenericSuperclass();
                    } else if (Collection.class.isAssignableFrom(cls3)) {
                        if (!isAssignableFrom2) {
                            throw new IllegalArgumentException("Got an unexpected map type");
                        }
                        type6 = cls3.getGenericSuperclass();
                    }
                }
                if (type6 instanceof Class) {
                    typeResolutions = null;
                    type2 = null;
                    Class cls4 = (Class) type6;
                    if (isArray) {
                        Class<?> componentType = cls4.getComponentType();
                        cls = componentType;
                        z = !componentType.isArray();
                    } else {
                        cls = null;
                        z = false;
                    }
                    type4 = null;
                } else if (type6 instanceof ParameterizedType) {
                    TypeResolutions typeResolutions2 = new TypeResolutions((ParameterizedType) type6);
                    typeResolutions = typeResolutions2;
                    int length = typeResolutions2.resolvedTypeArguments.length;
                    if (isAssignableFrom && length != 2) {
                        throw new IllegalArgumentException("Wrong number of type arguments for Map: got " + length + "; expected 2");
                    }
                    if (isAssignableFrom2 && length != 1) {
                        throw new IllegalArgumentException("Wrong number of type arguments for Collection: got " + length + "; expected 1");
                    }
                    type2 = isAssignableFrom ? typeResolutions.resolvedTypeArguments[0] : null;
                    if (isAssignableFrom) {
                        type3 = typeResolutions.resolvedTypeArguments[1];
                    } else {
                        type3 = isAssignableFrom2 ? typeResolutions.resolvedTypeArguments[0] : null;
                    }
                    type4 = type3;
                    z = false;
                    cls = null;
                } else {
                    throw new IllegalArgumentException("Got illegal type: " + type6);
                }
                Class<?> rawType = type4 == null ? null : JSONUtils.getRawType(type4);
                if (isAssignableFrom || isAssignableFrom2 || (z && !JSONUtils.isBasicValueType(cls))) {
                    Constructor<?> constructorWithSizeHintForConcreteTypeOf = classFieldCache.getConstructorWithSizeHintForConcreteTypeOf(z ? cls : rawType);
                    constructor = constructorWithSizeHintForConcreteTypeOf;
                    if (constructorWithSizeHintForConcreteTypeOf != null) {
                        defaultConstructorForConcreteTypeOf = null;
                    } else {
                        defaultConstructorForConcreteTypeOf = classFieldCache.getDefaultConstructorForConcreteTypeOf(z ? cls : rawType);
                    }
                } else {
                    constructor = null;
                    defaultConstructorForConcreteTypeOf = null;
                }
                ClassFields classFields = z4 ? classFieldCache.get(cls2) : null;
                ArrayList arrayList = null;
                if (jSONObject != null) {
                    size = jSONObject.items.size();
                } else {
                    size = jSONArray != null ? jSONArray.items.size() : 0;
                }
                int i = size;
                for (int i2 = 0; i2 < i; i2++) {
                    if (jSONObject != null) {
                        Map.Entry<String, Object> entry = jSONObject.items.get(i2);
                        str = entry.getKey();
                        obj3 = entry.getValue();
                    } else if (jSONArray != null) {
                        str = null;
                        obj3 = jSONArray.items.get(i2);
                    } else {
                        throw new RuntimeException("This exception should not be thrown");
                    }
                    boolean z5 = obj3 instanceof JSONObject;
                    boolean z6 = obj3 instanceof JSONArray;
                    JSONObject jSONObject2 = z5 ? (JSONObject) obj3 : null;
                    JSONArray jSONArray2 = z6 ? (JSONArray) obj3 : null;
                    if (classFields != null) {
                        FieldTypeInfo fieldTypeInfo2 = classFields.fieldNameToFieldTypeInfo.get(str);
                        fieldTypeInfo = fieldTypeInfo2;
                        if (fieldTypeInfo2 == null) {
                            throw new IllegalArgumentException("Field " + cls2.getName() + "." + str + " does not exist or is not accessible, non-final, and non-transient");
                        }
                    } else {
                        fieldTypeInfo = null;
                    }
                    if (fieldTypeInfo != null) {
                        type5 = fieldTypeInfo.getFullyResolvedFieldType(typeResolutions);
                    } else {
                        type5 = isArray ? cls : type4;
                    }
                    Type type7 = type5;
                    if (obj3 == null) {
                        obj4 = null;
                    } else if (type7 == Object.class) {
                        if (z5) {
                            obj4 = new HashMap();
                            if (arrayList == null) {
                                arrayList = new ArrayList();
                            }
                            arrayList.add(new ObjectInstantiation(obj4, ParameterizedTypeImpl.MAP_OF_UNKNOWN_TYPE, obj3));
                        } else if (z6) {
                            obj4 = new ArrayList();
                            if (arrayList == null) {
                                arrayList = new ArrayList();
                            }
                            arrayList.add(new ObjectInstantiation(obj4, ParameterizedTypeImpl.LIST_OF_UNKNOWN_TYPE, obj3));
                        } else {
                            obj4 = jsonBasicValueToObject(obj3, type7, false);
                        }
                    } else if (JSONUtils.isBasicValueType(type7)) {
                        if (z5 || z6) {
                            throw new IllegalArgumentException("Got JSONObject or JSONArray type when expecting a simple value type");
                        }
                        obj4 = jsonBasicValueToObject(obj3, type7, false);
                    } else if (CharSequence.class.isAssignableFrom(obj3.getClass())) {
                        Object obj5 = map.get(obj3);
                        if (obj5 == null) {
                            throw new IllegalArgumentException("Object id not found: " + obj3);
                        }
                        obj4 = obj5;
                    } else {
                        if (!z5 && !z6) {
                            throw new IllegalArgumentException("Got simple value type when expecting a JSON object or JSON array");
                        }
                        if (jSONObject2 != null) {
                            try {
                                size2 = jSONObject2.items.size();
                            } catch (ReflectiveOperationException | SecurityException e) {
                                throw new IllegalArgumentException("Could not instantiate type " + type7, e);
                            }
                        } else {
                            size2 = jSONArray2 != null ? jSONArray2.items.size() : 0;
                        }
                        int i3 = size2;
                        if ((type7 instanceof Class) && ((Class) type7).isArray()) {
                            if (!z6) {
                                throw new IllegalArgumentException("Expected JSONArray, got " + obj3.getClass().getName());
                            }
                            obj4 = Array.newInstance(((Class) type7).getComponentType(), i3);
                        } else if (isAssignableFrom2 || isAssignableFrom || z) {
                            if (constructor != null) {
                                newInstance = constructor.newInstance(Integer.valueOf(i3));
                            } else {
                                newInstance = defaultConstructorForConcreteTypeOf != null ? defaultConstructorForConcreteTypeOf.newInstance(new Object[0]) : null;
                            }
                            obj4 = newInstance;
                        } else if (fieldTypeInfo != null) {
                            Constructor<?> constructorForFieldTypeWithSizeHint = fieldTypeInfo.getConstructorForFieldTypeWithSizeHint(type7, classFieldCache);
                            if (constructorForFieldTypeWithSizeHint != null) {
                                obj4 = constructorForFieldTypeWithSizeHint.newInstance(Integer.valueOf(i3));
                            } else {
                                obj4 = fieldTypeInfo.getDefaultConstructorForFieldType(type7, classFieldCache).newInstance(new Object[0]);
                            }
                        } else if (isArray && !z) {
                            obj4 = Array.newInstance(cls2.getComponentType(), i3);
                        } else {
                            throw new IllegalArgumentException("Got illegal type");
                        }
                        if (obj3 instanceof JSONObject) {
                            JSONObject jSONObject3 = (JSONObject) obj3;
                            if (jSONObject3.objectId != null) {
                                map.put(jSONObject3.objectId, obj4);
                            }
                        }
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(new ObjectInstantiation(obj4, type7, obj3));
                    }
                    if (fieldTypeInfo != null) {
                        fieldTypeInfo.setFieldValue(obj, obj4);
                    } else if (map2 != null) {
                        map2.put(jsonBasicValueToObject(str, type2, true), obj4);
                    } else if (isArray) {
                        Array.set(obj, i2, obj4);
                    } else if (collection != null) {
                        final Object obj6 = obj4;
                        list.add(new Runnable() { // from class: nonapi.io.github.classgraph.json.JSONDeserializer.1
                            @Override // java.lang.Runnable
                            public final void run() {
                                collection.add(obj6);
                            }
                        });
                    }
                }
                if (arrayList != null) {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        ObjectInstantiation objectInstantiation = (ObjectInstantiation) it.next();
                        populateObjectFromJsonObject(objectInstantiation.objectInstance, objectInstantiation.type, objectInstantiation.jsonVal, classFieldCache, map, list);
                    }
                    return;
                }
                return;
            }
        }
        throw new IllegalArgumentException("Wrong JSON type for class " + obj.getClass().getName());
    }

    private static Map<CharSequence, Object> getInitialIdToObjectMap(Object obj, Object obj2) {
        Object value;
        HashMap hashMap = new HashMap();
        if (obj2 instanceof JSONObject) {
            JSONObject jSONObject = (JSONObject) obj2;
            if (!jSONObject.items.isEmpty()) {
                Map.Entry<String, Object> entry = jSONObject.items.get(0);
                if (entry.getKey().equals("__ID") && ((value = entry.getValue()) == null || !CharSequence.class.isAssignableFrom(value.getClass()))) {
                    hashMap.put((CharSequence) value, obj);
                }
            }
        }
        return hashMap;
    }

    private static <T> T deserializeObject(Class<T> cls, String str, ClassFieldCache classFieldCache) {
        try {
            Object parseJSON = JSONParser.parseJSON(str);
            try {
                T t = (T) classFieldCache.getDefaultConstructorForConcreteTypeOf(cls).newInstance(new Object[0]);
                ArrayList arrayList = new ArrayList();
                populateObjectFromJsonObject(t, cls, parseJSON, classFieldCache, getInitialIdToObjectMap(t, parseJSON), arrayList);
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((Runnable) it.next()).run();
                }
                return t;
            } catch (ReflectiveOperationException | SecurityException e) {
                throw new IllegalArgumentException("Could not construct object of type " + cls.getName(), e);
            }
        } catch (ParseException e2) {
            throw new IllegalArgumentException("Could not parse JSON", e2);
        }
    }

    public static <T> T deserializeObject(Class<T> cls, String str, ReflectionUtils reflectionUtils) {
        return (T) deserializeObject(cls, str, new ClassFieldCache(true, false, reflectionUtils));
    }

    public static <T> T deserializeObject(Class<T> cls, String str) {
        return (T) deserializeObject(cls, str, new ReflectionUtils());
    }

    public static void deserializeToField(Object obj, String str, String str2, ClassFieldCache classFieldCache) {
        if (obj == null) {
            throw new IllegalArgumentException("Cannot deserialize to a field of a null object");
        }
        try {
            Object parseJSON = JSONParser.parseJSON(str2);
            JSONObject jSONObject = new JSONObject(1);
            jSONObject.items.add(new AbstractMap.SimpleEntry(str, parseJSON));
            ArrayList arrayList = new ArrayList();
            populateObjectFromJsonObject(obj, obj.getClass(), jSONObject, classFieldCache, new HashMap(), arrayList);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((Runnable) it.next()).run();
            }
        } catch (ParseException e) {
            throw new IllegalArgumentException("Could not parse JSON", e);
        }
    }

    public static void deserializeToField(Object obj, String str, String str2, ReflectionUtils reflectionUtils) {
        deserializeToField(obj, str, str2, new ClassFieldCache(true, false, reflectionUtils));
    }

    public static void deserializeToField(Object obj, String str, String str2) {
        deserializeToField(obj, str, str2, new ReflectionUtils());
    }
}
