package io.github.classgraph;

import java.lang.annotation.Annotation;
import java.lang.annotation.IncompleteAnnotationException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import net.bytebuddy.description.method.MethodDescription;
import nonapi.io.github.classgraph.reflection.ReflectionUtils;
import nonapi.io.github.classgraph.utils.LogNode;

/* loaded from: infinitode-2.jar:io/github/classgraph/AnnotationInfo.class */
public class AnnotationInfo extends ScanResultObject implements HasName, Comparable<AnnotationInfo> {
    private String name;
    private AnnotationParameterValueList annotationParamValues;
    private transient boolean annotationParamValuesHasBeenConvertedToPrimitive;
    private transient AnnotationParameterValueList annotationParamValuesWithDefaults;

    @Override // io.github.classgraph.ScanResultObject
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    @Override // io.github.classgraph.ScanResultObject
    public /* bridge */ /* synthetic */ String toStringWithSimpleNames() {
        return super.toStringWithSimpleNames();
    }

    AnnotationInfo() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AnnotationInfo(String str, AnnotationParameterValueList annotationParameterValueList) {
        this.name = str;
        this.annotationParamValues = annotationParameterValueList;
    }

    @Override // io.github.classgraph.HasName
    public String getName() {
        return this.name;
    }

    public boolean isInherited() {
        return getClassInfo().isInherited;
    }

    public AnnotationParameterValueList getDefaultParameterValues() {
        return getClassInfo().getAnnotationDefaultParameterValues();
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:62:0x014e. Please report as an issue. */
    public AnnotationParameterValueList getParameterValues(boolean z) {
        ClassInfo classInfo = getClassInfo();
        if (classInfo == null) {
            return this.annotationParamValues == null ? AnnotationParameterValueList.EMPTY_LIST : this.annotationParamValues;
        }
        if (this.annotationParamValues != null && !this.annotationParamValuesHasBeenConvertedToPrimitive) {
            this.annotationParamValues.convertWrapperArraysToPrimitiveArrays(classInfo);
            this.annotationParamValuesHasBeenConvertedToPrimitive = true;
        }
        if (!z) {
            return this.annotationParamValues == null ? AnnotationParameterValueList.EMPTY_LIST : this.annotationParamValues;
        }
        if (this.annotationParamValuesWithDefaults == null) {
            if (classInfo.annotationDefaultParamValues != null && !classInfo.annotationDefaultParamValuesHasBeenConvertedToPrimitive) {
                classInfo.annotationDefaultParamValues.convertWrapperArraysToPrimitiveArrays(classInfo);
                classInfo.annotationDefaultParamValuesHasBeenConvertedToPrimitive = true;
            }
            AnnotationParameterValueList annotationParameterValueList = classInfo.annotationDefaultParamValues;
            if (annotationParameterValueList == null && this.annotationParamValues == null) {
                return AnnotationParameterValueList.EMPTY_LIST;
            }
            if (annotationParameterValueList == null) {
                return this.annotationParamValues;
            }
            if (this.annotationParamValues == null) {
                return annotationParameterValueList;
            }
            HashMap hashMap = new HashMap();
            Iterator it = annotationParameterValueList.iterator();
            while (it.hasNext()) {
                AnnotationParameterValue annotationParameterValue = (AnnotationParameterValue) it.next();
                hashMap.put(annotationParameterValue.getName(), annotationParameterValue.getValue());
            }
            Iterator it2 = this.annotationParamValues.iterator();
            while (it2.hasNext()) {
                AnnotationParameterValue annotationParameterValue2 = (AnnotationParameterValue) it2.next();
                hashMap.put(annotationParameterValue2.getName(), annotationParameterValue2.getValue());
            }
            if (classInfo.methodInfo == null) {
                throw new IllegalArgumentException("Could not find methods for annotation " + classInfo.getName());
            }
            this.annotationParamValuesWithDefaults = new AnnotationParameterValueList();
            Iterator it3 = classInfo.methodInfo.iterator();
            while (it3.hasNext()) {
                String name = ((MethodInfo) it3.next()).getName();
                boolean z2 = -1;
                switch (name.hashCode()) {
                    case -1944711511:
                        if (name.equals(MethodDescription.TYPE_INITIALIZER_INTERNAL_NAME)) {
                            z2 = true;
                            break;
                        }
                        break;
                    case -1776922004:
                        if (name.equals("toString")) {
                            z2 = 4;
                            break;
                        }
                        break;
                    case -1295482945:
                        if (name.equals("equals")) {
                            z2 = 3;
                            break;
                        }
                        break;
                    case 147696667:
                        if (name.equals("hashCode")) {
                            z2 = 2;
                            break;
                        }
                        break;
                    case 1444986633:
                        if (name.equals("annotationType")) {
                            z2 = 5;
                            break;
                        }
                        break;
                    case 1818100338:
                        if (name.equals(MethodDescription.CONSTRUCTOR_INTERNAL_NAME)) {
                            z2 = false;
                            break;
                        }
                        break;
                }
                switch (z2) {
                    case false:
                    case true:
                    case true:
                    case true:
                    case true:
                    case true:
                        break;
                    default:
                        Object obj = hashMap.get(name);
                        if (obj == null) {
                            break;
                        } else {
                            this.annotationParamValuesWithDefaults.add(new AnnotationParameterValue(name, obj));
                            break;
                        }
                }
            }
        }
        return this.annotationParamValuesWithDefaults;
    }

    public AnnotationParameterValueList getParameterValues() {
        return getParameterValues(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.github.classgraph.ScanResultObject
    public String getClassName() {
        return this.name;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.github.classgraph.ScanResultObject
    public void setScanResult(ScanResult scanResult) {
        super.setScanResult(scanResult);
        if (this.annotationParamValues != null) {
            Iterator it = this.annotationParamValues.iterator();
            while (it.hasNext()) {
                ((AnnotationParameterValue) it.next()).setScanResult(scanResult);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.github.classgraph.ScanResultObject
    public void findReferencedClassInfo(Map<String, ClassInfo> map, Set<ClassInfo> set, LogNode logNode) {
        super.findReferencedClassInfo(map, set, logNode);
        if (this.annotationParamValues != null) {
            Iterator it = this.annotationParamValues.iterator();
            while (it.hasNext()) {
                ((AnnotationParameterValue) it.next()).findReferencedClassInfo(map, set, logNode);
            }
        }
    }

    @Override // io.github.classgraph.ScanResultObject
    public ClassInfo getClassInfo() {
        return super.getClassInfo();
    }

    public Annotation loadClassAndInstantiate() {
        Class loadClass = getClassInfo().loadClass(Annotation.class);
        return (Annotation) Proxy.newProxyInstance(loadClass.getClassLoader(), new Class[]{loadClass}, new AnnotationInvocationHandler(loadClass, this));
    }

    /* loaded from: infinitode-2.jar:io/github/classgraph/AnnotationInfo$AnnotationInvocationHandler.class */
    private static class AnnotationInvocationHandler implements InvocationHandler {
        private final Class<? extends Annotation> annotationClass;
        private final AnnotationInfo annotationInfo;
        private final Map<String, Object> annotationParameterValuesInstantiated = new HashMap();

        AnnotationInvocationHandler(Class<? extends Annotation> cls, AnnotationInfo annotationInfo) {
            this.annotationClass = cls;
            this.annotationInfo = annotationInfo;
            Iterator it = annotationInfo.getParameterValues().iterator();
            while (it.hasNext()) {
                AnnotationParameterValue annotationParameterValue = (AnnotationParameterValue) it.next();
                Object instantiate = annotationParameterValue.instantiate(annotationInfo.getClassInfo());
                if (instantiate != null) {
                    this.annotationParameterValuesInstantiated.put(annotationParameterValue.getName(), instantiate);
                } else {
                    throw new IllegalArgumentException("Got null value for annotation parameter " + annotationParameterValue.getName() + " of annotation " + annotationInfo.name);
                }
            }
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) {
            int hashCode;
            String name = method.getName();
            Class<?>[] parameterTypes = method.getParameterTypes();
            if ((objArr == null ? 0 : objArr.length) != parameterTypes.length) {
                throw new IllegalArgumentException("Wrong number of arguments for " + this.annotationClass.getName() + "." + name + ": got " + (objArr == null ? 0 : objArr.length) + ", expected " + parameterTypes.length);
            }
            if (objArr != null && parameterTypes.length == 1) {
                if ("equals".equals(name) && parameterTypes[0] == Object.class) {
                    if (this == objArr[0]) {
                        return Boolean.TRUE;
                    }
                    if (!this.annotationClass.isInstance(objArr[0])) {
                        return Boolean.FALSE;
                    }
                    ReflectionUtils reflectionUtils = this.annotationInfo.scanResult == null ? new ReflectionUtils() : this.annotationInfo.scanResult.reflectionUtils;
                    for (Map.Entry<String, Object> entry : this.annotationParameterValuesInstantiated.entrySet()) {
                        String key = entry.getKey();
                        Object value = entry.getValue();
                        Object invokeMethod = reflectionUtils.invokeMethod(false, objArr[0], key);
                        if ((value == null) != (invokeMethod == null)) {
                            return Boolean.FALSE;
                        }
                        if (value == null && invokeMethod == null) {
                            return Boolean.TRUE;
                        }
                        if (value == null || !value.equals(invokeMethod)) {
                            return Boolean.FALSE;
                        }
                    }
                    return Boolean.TRUE;
                }
                throw new IllegalArgumentException();
            }
            if (parameterTypes.length == 0) {
                boolean z = -1;
                switch (name.hashCode()) {
                    case -1776922004:
                        if (name.equals("toString")) {
                            z = false;
                            break;
                        }
                        break;
                    case 147696667:
                        if (name.equals("hashCode")) {
                            z = true;
                            break;
                        }
                        break;
                    case 1444986633:
                        if (name.equals("annotationType")) {
                            z = 2;
                            break;
                        }
                        break;
                }
                switch (z) {
                    case false:
                        return this.annotationInfo.toString();
                    case true:
                        int i = 0;
                        for (Map.Entry<String, Object> entry2 : this.annotationParameterValuesInstantiated.entrySet()) {
                            String key2 = entry2.getKey();
                            Object value2 = entry2.getValue();
                            if (value2 == null) {
                                hashCode = 0;
                            } else {
                                Class<?> cls = value2.getClass();
                                if (!cls.isArray()) {
                                    hashCode = value2.hashCode();
                                } else if (cls == byte[].class) {
                                    hashCode = Arrays.hashCode((byte[]) value2);
                                } else if (cls == char[].class) {
                                    hashCode = Arrays.hashCode((char[]) value2);
                                } else if (cls == double[].class) {
                                    hashCode = Arrays.hashCode((double[]) value2);
                                } else if (cls == float[].class) {
                                    hashCode = Arrays.hashCode((float[]) value2);
                                } else if (cls == int[].class) {
                                    hashCode = Arrays.hashCode((int[]) value2);
                                } else if (cls == long[].class) {
                                    hashCode = Arrays.hashCode((long[]) value2);
                                } else if (cls == short[].class) {
                                    hashCode = Arrays.hashCode((short[]) value2);
                                } else if (cls == boolean[].class) {
                                    hashCode = Arrays.hashCode((boolean[]) value2);
                                } else {
                                    hashCode = Arrays.hashCode((Object[]) value2);
                                }
                            }
                            i += (127 * key2.hashCode()) ^ hashCode;
                        }
                        return Integer.valueOf(i);
                    case true:
                        return this.annotationClass;
                    default:
                        Object obj2 = this.annotationParameterValuesInstantiated.get(name);
                        if (obj2 == null) {
                            throw new IncompleteAnnotationException(this.annotationClass, name);
                        }
                        Class<?> cls2 = obj2.getClass();
                        if (cls2.isArray()) {
                            if (cls2 == String[].class) {
                                return ((String[]) obj2).clone();
                            }
                            if (cls2 == byte[].class) {
                                return ((byte[]) obj2).clone();
                            }
                            if (cls2 == char[].class) {
                                return ((char[]) obj2).clone();
                            }
                            if (cls2 == double[].class) {
                                return ((double[]) obj2).clone();
                            }
                            if (cls2 == float[].class) {
                                return ((float[]) obj2).clone();
                            }
                            if (cls2 == int[].class) {
                                return ((int[]) obj2).clone();
                            }
                            if (cls2 == long[].class) {
                                return ((long[]) obj2).clone();
                            }
                            if (cls2 == short[].class) {
                                return ((short[]) obj2).clone();
                            }
                            if (cls2 == boolean[].class) {
                                return ((boolean[]) obj2).clone();
                            }
                            return ((Object[]) obj2).clone();
                        }
                        return obj2;
                }
            }
            throw new IllegalArgumentException();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void convertWrapperArraysToPrimitiveArrays() {
        if (this.annotationParamValues != null) {
            this.annotationParamValues.convertWrapperArraysToPrimitiveArrays(getClassInfo());
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(AnnotationInfo annotationInfo) {
        int compareTo = this.name.compareTo(annotationInfo.name);
        if (compareTo != 0) {
            return compareTo;
        }
        if (this.annotationParamValues == null && annotationInfo.annotationParamValues == null) {
            return 0;
        }
        if (this.annotationParamValues == null) {
            return -1;
        }
        if (annotationInfo.annotationParamValues == null) {
            return 1;
        }
        int max = Math.max(this.annotationParamValues.size(), annotationInfo.annotationParamValues.size());
        for (int i = 0; i < max; i++) {
            if (i >= this.annotationParamValues.size()) {
                return -1;
            }
            if (i >= annotationInfo.annotationParamValues.size()) {
                return 1;
            }
            int compareTo2 = ((AnnotationParameterValue) this.annotationParamValues.get(i)).compareTo((AnnotationParameterValue) annotationInfo.annotationParamValues.get(i));
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof AnnotationInfo) && compareTo((AnnotationInfo) obj) == 0;
    }

    public int hashCode() {
        int hashCode = this.name.hashCode();
        if (this.annotationParamValues != null) {
            Iterator it = this.annotationParamValues.iterator();
            while (it.hasNext()) {
                AnnotationParameterValue annotationParameterValue = (AnnotationParameterValue) it.next();
                hashCode = (hashCode * 7) + (annotationParameterValue.getName().hashCode() * 3) + annotationParameterValue.getValue().hashCode();
            }
        }
        return hashCode;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.github.classgraph.ScanResultObject
    public void toString(boolean z, StringBuilder sb) {
        sb.append('@').append(z ? ClassInfo.getSimpleName(this.name) : this.name);
        AnnotationParameterValueList parameterValues = getParameterValues();
        if (!parameterValues.isEmpty()) {
            sb.append('(');
            for (int i = 0; i < parameterValues.size(); i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                AnnotationParameterValue annotationParameterValue = (AnnotationParameterValue) parameterValues.get(i);
                if (parameterValues.size() > 1 || !"value".equals(annotationParameterValue.getName())) {
                    annotationParameterValue.toString(z, sb);
                } else {
                    annotationParameterValue.toStringParamValueOnly(z, sb);
                }
            }
            sb.append(')');
        }
    }
}
