package io.github.classgraph;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import nonapi.io.github.classgraph.utils.LogNode;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:io/github/classgraph/ObjectTypedValueWrapper.class */
public class ObjectTypedValueWrapper extends ScanResultObject {
    private AnnotationEnumValue annotationEnumValue;
    private AnnotationClassRef annotationClassRef;
    private AnnotationInfo annotationInfo;
    private String stringValue;
    private Integer integerValue;
    private Long longValue;
    private Short shortValue;
    private Boolean booleanValue;
    private Character characterValue;
    private Float floatValue;
    private Double doubleValue;
    private Byte byteValue;
    private String[] stringArrayValue;
    private int[] intArrayValue;
    private long[] longArrayValue;
    private short[] shortArrayValue;
    private boolean[] booleanArrayValue;
    private char[] charArrayValue;
    private float[] floatArrayValue;
    private double[] doubleArrayValue;
    private byte[] byteArrayValue;
    private ObjectTypedValueWrapper[] objectArrayValue;

    public ObjectTypedValueWrapper() {
    }

    public ObjectTypedValueWrapper(Object obj) {
        if (obj != null) {
            Class<?> cls = obj.getClass();
            if (cls.isArray()) {
                if (cls == String[].class) {
                    this.stringArrayValue = (String[]) obj;
                    return;
                }
                if (cls == int[].class) {
                    this.intArrayValue = (int[]) obj;
                    return;
                }
                if (cls == long[].class) {
                    this.longArrayValue = (long[]) obj;
                    return;
                }
                if (cls == short[].class) {
                    this.shortArrayValue = (short[]) obj;
                    return;
                }
                if (cls == boolean[].class) {
                    this.booleanArrayValue = (boolean[]) obj;
                    return;
                }
                if (cls == char[].class) {
                    this.charArrayValue = (char[]) obj;
                    return;
                }
                if (cls == float[].class) {
                    this.floatArrayValue = (float[]) obj;
                    return;
                }
                if (cls == double[].class) {
                    this.doubleArrayValue = (double[]) obj;
                    return;
                }
                if (cls == byte[].class) {
                    this.byteArrayValue = (byte[]) obj;
                    return;
                }
                int length = Array.getLength(obj);
                this.objectArrayValue = new ObjectTypedValueWrapper[length];
                for (int i = 0; i < length; i++) {
                    this.objectArrayValue[i] = new ObjectTypedValueWrapper(Array.get(obj, i));
                }
                return;
            }
            if (obj instanceof AnnotationEnumValue) {
                this.annotationEnumValue = (AnnotationEnumValue) obj;
                return;
            }
            if (obj instanceof AnnotationClassRef) {
                this.annotationClassRef = (AnnotationClassRef) obj;
                return;
            }
            if (obj instanceof AnnotationInfo) {
                this.annotationInfo = (AnnotationInfo) obj;
                return;
            }
            if (obj instanceof String) {
                this.stringValue = (String) obj;
                return;
            }
            if (obj instanceof Integer) {
                this.integerValue = (Integer) obj;
                return;
            }
            if (obj instanceof Long) {
                this.longValue = (Long) obj;
                return;
            }
            if (obj instanceof Short) {
                this.shortValue = (Short) obj;
                return;
            }
            if (obj instanceof Boolean) {
                this.booleanValue = (Boolean) obj;
                return;
            }
            if (obj instanceof Character) {
                this.characterValue = (Character) obj;
                return;
            }
            if (obj instanceof Float) {
                this.floatValue = (Float) obj;
            } else if (obj instanceof Double) {
                this.doubleValue = (Double) obj;
            } else {
                if (obj instanceof Byte) {
                    this.byteValue = (Byte) obj;
                    return;
                }
                throw new IllegalArgumentException("Unsupported annotation parameter value type: " + cls.getName());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object instantiateOrGet(ClassInfo classInfo, String str) {
        boolean z = classInfo != null;
        if (this.annotationEnumValue != null) {
            return z ? this.annotationEnumValue.loadClassAndReturnEnumValue() : this.annotationEnumValue;
        }
        if (this.annotationClassRef != null) {
            return z ? this.annotationClassRef.loadClass() : this.annotationClassRef;
        }
        if (this.annotationInfo != null) {
            return z ? this.annotationInfo.loadClassAndInstantiate() : this.annotationInfo;
        }
        if (this.stringValue != null) {
            return this.stringValue;
        }
        if (this.integerValue != null) {
            return this.integerValue;
        }
        if (this.longValue != null) {
            return this.longValue;
        }
        if (this.shortValue != null) {
            return this.shortValue;
        }
        if (this.booleanValue != null) {
            return this.booleanValue;
        }
        if (this.characterValue != null) {
            return this.characterValue;
        }
        if (this.floatValue != null) {
            return this.floatValue;
        }
        if (this.doubleValue != null) {
            return this.doubleValue;
        }
        if (this.byteValue != null) {
            return this.byteValue;
        }
        if (this.stringArrayValue != null) {
            return this.stringArrayValue;
        }
        if (this.intArrayValue != null) {
            return this.intArrayValue;
        }
        if (this.longArrayValue != null) {
            return this.longArrayValue;
        }
        if (this.shortArrayValue != null) {
            return this.shortArrayValue;
        }
        if (this.booleanArrayValue != null) {
            return this.booleanArrayValue;
        }
        if (this.charArrayValue != null) {
            return this.charArrayValue;
        }
        if (this.floatArrayValue != null) {
            return this.floatArrayValue;
        }
        if (this.doubleArrayValue != null) {
            return this.doubleArrayValue;
        }
        if (this.byteArrayValue != null) {
            return this.byteArrayValue;
        }
        if (this.objectArrayValue != null) {
            Class cls = z ? (Class) getArrayValueClassOrName(classInfo, str, true) : null;
            Object newInstance = cls == null ? new Object[this.objectArrayValue.length] : Array.newInstance((Class<?>) cls, this.objectArrayValue.length);
            for (int i = 0; i < this.objectArrayValue.length; i++) {
                if (this.objectArrayValue[i] != null) {
                    Array.set(newInstance, i, this.objectArrayValue[i].instantiateOrGet(classInfo, str));
                }
            }
            return newInstance;
        }
        return null;
    }

    public Object get() {
        return instantiateOrGet(null, null);
    }

    private Object getArrayValueClassOrName(ClassInfo classInfo, String str, boolean z) {
        MethodInfoList methodInfoList = (classInfo == null || classInfo.methodInfo == null) ? null : classInfo.methodInfo.get(str);
        if (classInfo != null && methodInfoList != null && !methodInfoList.isEmpty()) {
            if (methodInfoList.size() > 1) {
                throw new IllegalArgumentException("Duplicated annotation parameter method " + str + "() in annotation class " + classInfo.getName());
            }
            TypeSignature resultType = ((MethodInfo) methodInfoList.get(0)).getTypeSignatureOrTypeDescriptor().getResultType();
            if (!(resultType instanceof ArrayTypeSignature)) {
                throw new IllegalArgumentException("Annotation parameter " + str + " in annotation class " + classInfo.getName() + " holds an array, but does not have an array type signature");
            }
            ArrayTypeSignature arrayTypeSignature = (ArrayTypeSignature) resultType;
            if (arrayTypeSignature.getNumDimensions() != 1) {
                throw new IllegalArgumentException("Annotations only support 1-dimensional arrays");
            }
            TypeSignature elementTypeSignature = arrayTypeSignature.getElementTypeSignature();
            if (elementTypeSignature instanceof ClassRefTypeSignature) {
                ClassRefTypeSignature classRefTypeSignature = (ClassRefTypeSignature) elementTypeSignature;
                return z ? classRefTypeSignature.loadClass() : classRefTypeSignature.getClassName();
            }
            if (elementTypeSignature instanceof BaseTypeSignature) {
                BaseTypeSignature baseTypeSignature = (BaseTypeSignature) elementTypeSignature;
                return z ? baseTypeSignature.getType() : baseTypeSignature.getTypeStr();
            }
        } else {
            for (ObjectTypedValueWrapper objectTypedValueWrapper : this.objectArrayValue) {
                if (objectTypedValueWrapper != null) {
                    if (objectTypedValueWrapper.integerValue != null) {
                        return z ? Integer.class : "int";
                    }
                    if (objectTypedValueWrapper.longValue != null) {
                        return z ? Long.class : "long";
                    }
                    if (objectTypedValueWrapper.shortValue != null) {
                        return z ? Short.class : "short";
                    }
                    if (objectTypedValueWrapper.characterValue != null) {
                        return z ? Character.class : "char";
                    }
                    if (objectTypedValueWrapper.byteValue != null) {
                        return z ? Byte.class : "byte";
                    }
                    if (objectTypedValueWrapper.booleanValue != null) {
                        return z ? Boolean.class : "boolean";
                    }
                    if (objectTypedValueWrapper.doubleValue != null) {
                        return z ? Double.class : "double";
                    }
                    if (objectTypedValueWrapper.floatValue != null) {
                        return z ? Float.class : "float";
                    }
                    if (z) {
                        return objectTypedValueWrapper.getClass();
                    }
                    return objectTypedValueWrapper.getClass().getName();
                }
            }
        }
        return z ? Object.class : "java.lang.Object";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void convertWrapperArraysToPrimitiveArrays(ClassInfo classInfo, String str) {
        if (this.annotationInfo != null) {
            this.annotationInfo.convertWrapperArraysToPrimitiveArrays();
            return;
        }
        if (this.objectArrayValue != null) {
            for (ObjectTypedValueWrapper objectTypedValueWrapper : this.objectArrayValue) {
                if (objectTypedValueWrapper.annotationInfo != null) {
                    objectTypedValueWrapper.annotationInfo.convertWrapperArraysToPrimitiveArrays();
                }
            }
            if (this.objectArrayValue.getClass().getComponentType().isArray()) {
                return;
            }
            String str2 = (String) getArrayValueClassOrName(classInfo, str, false);
            boolean z = -1;
            switch (str2.hashCode()) {
                case -1325958191:
                    if (str2.equals("double")) {
                        z = 6;
                        break;
                    }
                    break;
                case 104431:
                    if (str2.equals("int")) {
                        z = true;
                        break;
                    }
                    break;
                case 3039496:
                    if (str2.equals("byte")) {
                        z = 8;
                        break;
                    }
                    break;
                case 3052374:
                    if (str2.equals("char")) {
                        z = 4;
                        break;
                    }
                    break;
                case 3327612:
                    if (str2.equals("long")) {
                        z = 2;
                        break;
                    }
                    break;
                case 64711720:
                    if (str2.equals("boolean")) {
                        z = 7;
                        break;
                    }
                    break;
                case 97526364:
                    if (str2.equals("float")) {
                        z = 5;
                        break;
                    }
                    break;
                case 109413500:
                    if (str2.equals("short")) {
                        z = 3;
                        break;
                    }
                    break;
                case 1195259493:
                    if (str2.equals("java.lang.String")) {
                        z = false;
                        break;
                    }
                    break;
            }
            switch (z) {
                case false:
                    this.stringArrayValue = new String[this.objectArrayValue.length];
                    for (int i = 0; i < this.objectArrayValue.length; i++) {
                        this.stringArrayValue[i] = this.objectArrayValue[i].stringValue;
                    }
                    this.objectArrayValue = null;
                    return;
                case true:
                    this.intArrayValue = new int[this.objectArrayValue.length];
                    for (int i2 = 0; i2 < this.objectArrayValue.length; i2++) {
                        if (this.objectArrayValue[i2] == null) {
                            throw new IllegalArgumentException("Illegal null value for array of element type " + str2 + " in parameter " + str + " of annotation class " + (classInfo == null ? "<class outside accept>" : classInfo.getName()));
                        }
                        this.intArrayValue[i2] = this.objectArrayValue[i2].integerValue.intValue();
                    }
                    this.objectArrayValue = null;
                    return;
                case true:
                    this.longArrayValue = new long[this.objectArrayValue.length];
                    for (int i3 = 0; i3 < this.objectArrayValue.length; i3++) {
                        if (this.objectArrayValue[i3] == null) {
                            throw new IllegalArgumentException("Illegal null value for array of element type " + str2 + " in parameter " + str + " of annotation class " + (classInfo == null ? "<class outside accept>" : classInfo.getName()));
                        }
                        this.longArrayValue[i3] = this.objectArrayValue[i3].longValue.longValue();
                    }
                    this.objectArrayValue = null;
                    return;
                case true:
                    this.shortArrayValue = new short[this.objectArrayValue.length];
                    for (int i4 = 0; i4 < this.objectArrayValue.length; i4++) {
                        if (this.objectArrayValue[i4] == null) {
                            throw new IllegalArgumentException("Illegal null value for array of element type " + str2 + " in parameter " + str + " of annotation class " + (classInfo == null ? "<class outside accept>" : classInfo.getName()));
                        }
                        this.shortArrayValue[i4] = this.objectArrayValue[i4].shortValue.shortValue();
                    }
                    this.objectArrayValue = null;
                    return;
                case true:
                    this.charArrayValue = new char[this.objectArrayValue.length];
                    for (int i5 = 0; i5 < this.objectArrayValue.length; i5++) {
                        if (this.objectArrayValue[i5] == null) {
                            throw new IllegalArgumentException("Illegal null value for array of element type " + str2 + " in parameter " + str + " of annotation class " + (classInfo == null ? "<class outside accept>" : classInfo.getName()));
                        }
                        this.charArrayValue[i5] = this.objectArrayValue[i5].characterValue.charValue();
                    }
                    this.objectArrayValue = null;
                    return;
                case true:
                    this.floatArrayValue = new float[this.objectArrayValue.length];
                    for (int i6 = 0; i6 < this.objectArrayValue.length; i6++) {
                        if (this.objectArrayValue[i6] == null) {
                            throw new IllegalArgumentException("Illegal null value for array of element type " + str2 + " in parameter " + str + " of annotation class " + (classInfo == null ? "<class outside accept>" : classInfo.getName()));
                        }
                        this.floatArrayValue[i6] = this.objectArrayValue[i6].floatValue.floatValue();
                    }
                    this.objectArrayValue = null;
                    return;
                case true:
                    this.doubleArrayValue = new double[this.objectArrayValue.length];
                    for (int i7 = 0; i7 < this.objectArrayValue.length; i7++) {
                        if (this.objectArrayValue[i7] == null) {
                            throw new IllegalArgumentException("Illegal null value for array of element type " + str2 + " in parameter " + str + " of annotation class " + (classInfo == null ? "<class outside accept>" : classInfo.getName()));
                        }
                        this.doubleArrayValue[i7] = this.objectArrayValue[i7].doubleValue.doubleValue();
                    }
                    this.objectArrayValue = null;
                    return;
                case true:
                    this.booleanArrayValue = new boolean[this.objectArrayValue.length];
                    for (int i8 = 0; i8 < this.objectArrayValue.length; i8++) {
                        if (this.objectArrayValue[i8] == null) {
                            throw new IllegalArgumentException("Illegal null value for array of element type " + str2 + " in parameter " + str + " of annotation class " + (classInfo == null ? "<class outside accept>" : classInfo.getName()));
                        }
                        this.booleanArrayValue[i8] = this.objectArrayValue[i8].booleanValue.booleanValue();
                    }
                    this.objectArrayValue = null;
                    return;
                case true:
                    this.byteArrayValue = new byte[this.objectArrayValue.length];
                    for (int i9 = 0; i9 < this.objectArrayValue.length; i9++) {
                        if (this.objectArrayValue[i9] == null) {
                            throw new IllegalArgumentException("Illegal null value for array of element type " + str2 + " in parameter " + str + " of annotation class " + (classInfo == null ? "<class outside accept>" : classInfo.getName()));
                        }
                        this.byteArrayValue[i9] = this.objectArrayValue[i9].byteValue.byteValue();
                    }
                    this.objectArrayValue = null;
                    return;
                default:
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.github.classgraph.ScanResultObject
    public String getClassName() {
        throw new IllegalArgumentException("getClassName() cannot be called here");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.github.classgraph.ScanResultObject
    public ClassInfo getClassInfo() {
        throw new IllegalArgumentException("getClassInfo() cannot be called here");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.github.classgraph.ScanResultObject
    public void setScanResult(ScanResult scanResult) {
        super.setScanResult(scanResult);
        if (this.annotationEnumValue != null) {
            this.annotationEnumValue.setScanResult(scanResult);
            return;
        }
        if (this.annotationClassRef != null) {
            this.annotationClassRef.setScanResult(scanResult);
            return;
        }
        if (this.annotationInfo != null) {
            this.annotationInfo.setScanResult(scanResult);
            return;
        }
        if (this.objectArrayValue != null) {
            for (ObjectTypedValueWrapper objectTypedValueWrapper : this.objectArrayValue) {
                if (objectTypedValueWrapper != null) {
                    objectTypedValueWrapper.setScanResult(scanResult);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.github.classgraph.ScanResultObject
    public void findReferencedClassInfo(Map<String, ClassInfo> map, Set<ClassInfo> set, LogNode logNode) {
        if (this.annotationEnumValue != null) {
            this.annotationEnumValue.findReferencedClassInfo(map, set, logNode);
            return;
        }
        if (this.annotationClassRef != null) {
            ClassInfo classInfo = this.annotationClassRef.getClassInfo();
            if (classInfo != null) {
                set.add(classInfo);
                return;
            }
            return;
        }
        if (this.annotationInfo != null) {
            this.annotationInfo.findReferencedClassInfo(map, set, logNode);
            return;
        }
        if (this.objectArrayValue != null) {
            for (ObjectTypedValueWrapper objectTypedValueWrapper : this.objectArrayValue) {
                objectTypedValueWrapper.findReferencedClassInfo(map, set, logNode);
            }
        }
    }

    public int hashCode() {
        return Objects.hash(this.annotationEnumValue, this.annotationClassRef, this.annotationInfo, this.stringValue, this.integerValue, this.longValue, this.shortValue, this.booleanValue, this.characterValue, this.floatValue, this.doubleValue, this.byteValue, Integer.valueOf(Arrays.hashCode(this.stringArrayValue)), Integer.valueOf(Arrays.hashCode(this.intArrayValue)), Integer.valueOf(Arrays.hashCode(this.longArrayValue)), Integer.valueOf(Arrays.hashCode(this.shortArrayValue)), Integer.valueOf(Arrays.hashCode(this.booleanArrayValue)), Integer.valueOf(Arrays.hashCode(this.charArrayValue)), Integer.valueOf(Arrays.hashCode(this.floatArrayValue)), Integer.valueOf(Arrays.hashCode(this.doubleArrayValue)), Integer.valueOf(Arrays.hashCode(this.byteArrayValue)), Integer.valueOf(Arrays.hashCode(this.objectArrayValue)));
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ObjectTypedValueWrapper)) {
            return false;
        }
        ObjectTypedValueWrapper objectTypedValueWrapper = (ObjectTypedValueWrapper) obj;
        return Objects.equals(this.annotationEnumValue, objectTypedValueWrapper.annotationEnumValue) && Objects.equals(this.annotationClassRef, objectTypedValueWrapper.annotationClassRef) && Objects.equals(this.annotationInfo, objectTypedValueWrapper.annotationInfo) && Objects.equals(this.stringValue, objectTypedValueWrapper.stringValue) && Objects.equals(this.integerValue, objectTypedValueWrapper.integerValue) && Objects.equals(this.longValue, objectTypedValueWrapper.longValue) && Objects.equals(this.shortValue, objectTypedValueWrapper.shortValue) && Objects.equals(this.booleanValue, objectTypedValueWrapper.booleanValue) && Objects.equals(this.characterValue, objectTypedValueWrapper.characterValue) && Objects.equals(this.floatValue, objectTypedValueWrapper.floatValue) && Objects.equals(this.doubleValue, objectTypedValueWrapper.doubleValue) && Objects.equals(this.byteValue, objectTypedValueWrapper.byteValue) && Arrays.equals(this.stringArrayValue, objectTypedValueWrapper.stringArrayValue) && Arrays.equals(this.intArrayValue, objectTypedValueWrapper.intArrayValue) && Arrays.equals(this.longArrayValue, objectTypedValueWrapper.longArrayValue) && Arrays.equals(this.shortArrayValue, objectTypedValueWrapper.shortArrayValue) && Arrays.equals(this.floatArrayValue, objectTypedValueWrapper.floatArrayValue) && Arrays.equals(this.byteArrayValue, objectTypedValueWrapper.byteArrayValue) && Arrays.deepEquals(this.objectArrayValue, objectTypedValueWrapper.objectArrayValue);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.github.classgraph.ScanResultObject
    public void toString(boolean z, StringBuilder sb) {
        if (this.annotationEnumValue != null) {
            this.annotationEnumValue.toString(z, sb);
            return;
        }
        if (this.annotationClassRef != null) {
            this.annotationClassRef.toString(z, sb);
            return;
        }
        if (this.annotationInfo != null) {
            this.annotationInfo.toString(z, sb);
            return;
        }
        if (this.stringValue != null) {
            sb.append(this.stringValue);
            return;
        }
        if (this.integerValue != null) {
            sb.append(this.integerValue);
            return;
        }
        if (this.longValue != null) {
            sb.append(this.longValue);
            return;
        }
        if (this.shortValue != null) {
            sb.append(this.shortValue);
            return;
        }
        if (this.booleanValue != null) {
            sb.append(this.booleanValue);
            return;
        }
        if (this.characterValue != null) {
            sb.append(this.characterValue);
            return;
        }
        if (this.floatValue != null) {
            sb.append(this.floatValue);
            return;
        }
        if (this.doubleValue != null) {
            sb.append(this.doubleValue);
            return;
        }
        if (this.byteValue != null) {
            sb.append(this.byteValue);
            return;
        }
        if (this.stringArrayValue != null) {
            sb.append(Arrays.toString(this.stringArrayValue));
            return;
        }
        if (this.intArrayValue != null) {
            sb.append(Arrays.toString(this.intArrayValue));
            return;
        }
        if (this.longArrayValue != null) {
            sb.append(Arrays.toString(this.longArrayValue));
            return;
        }
        if (this.shortArrayValue != null) {
            sb.append(Arrays.toString(this.shortArrayValue));
            return;
        }
        if (this.booleanArrayValue != null) {
            sb.append(Arrays.toString(this.booleanArrayValue));
            return;
        }
        if (this.charArrayValue != null) {
            sb.append(Arrays.toString(this.charArrayValue));
            return;
        }
        if (this.floatArrayValue != null) {
            sb.append(Arrays.toString(this.floatArrayValue));
            return;
        }
        if (this.doubleArrayValue != null) {
            sb.append(Arrays.toString(this.doubleArrayValue));
        } else if (this.byteArrayValue != null) {
            sb.append(Arrays.toString(this.byteArrayValue));
        } else if (this.objectArrayValue != null) {
            sb.append(Arrays.toString(this.objectArrayValue));
        }
    }
}
