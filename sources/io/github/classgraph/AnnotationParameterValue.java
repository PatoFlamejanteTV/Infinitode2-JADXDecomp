package io.github.classgraph;

import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.lang.reflect.Array;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import nonapi.io.github.classgraph.utils.LogNode;

/* loaded from: infinitode-2.jar:io/github/classgraph/AnnotationParameterValue.class */
public class AnnotationParameterValue extends ScanResultObject implements HasName, Comparable<AnnotationParameterValue> {
    private String name;
    private ObjectTypedValueWrapper value;

    @Override // io.github.classgraph.ScanResultObject
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    @Override // io.github.classgraph.ScanResultObject
    public /* bridge */ /* synthetic */ String toStringWithSimpleNames() {
        return super.toStringWithSimpleNames();
    }

    AnnotationParameterValue() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AnnotationParameterValue(String str, Object obj) {
        this.name = str;
        this.value = new ObjectTypedValueWrapper(obj);
    }

    @Override // io.github.classgraph.HasName
    public String getName() {
        return this.name;
    }

    public Object getValue() {
        if (this.value == null) {
            return null;
        }
        return this.value.get();
    }

    void setValue(Object obj) {
        this.value = new ObjectTypedValueWrapper(obj);
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
        if (this.value != null) {
            this.value.setScanResult(scanResult);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.github.classgraph.ScanResultObject
    public void findReferencedClassInfo(Map<String, ClassInfo> map, Set<ClassInfo> set, LogNode logNode) {
        if (this.value != null) {
            this.value.findReferencedClassInfo(map, set, logNode);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void convertWrapperArraysToPrimitiveArrays(ClassInfo classInfo) {
        if (this.value != null) {
            this.value.convertWrapperArraysToPrimitiveArrays(classInfo, this.name);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object instantiate(ClassInfo classInfo) {
        return this.value.instantiateOrGet(classInfo, this.name);
    }

    @Override // java.lang.Comparable
    public int compareTo(AnnotationParameterValue annotationParameterValue) {
        if (annotationParameterValue == this) {
            return 0;
        }
        int compareTo = this.name.compareTo(annotationParameterValue.getName());
        if (compareTo != 0) {
            return compareTo;
        }
        if (this.value.equals(annotationParameterValue.value)) {
            return 0;
        }
        Object value = getValue();
        Object value2 = annotationParameterValue.getValue();
        if (value == null || value2 == null) {
            return (value == null ? 0 : 1) - (value2 == null ? 0 : 1);
        }
        return toStringParamValueOnly().compareTo(annotationParameterValue.toStringParamValueOnly());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AnnotationParameterValue)) {
            return false;
        }
        AnnotationParameterValue annotationParameterValue = (AnnotationParameterValue) obj;
        if (!this.name.equals(annotationParameterValue.name)) {
            return false;
        }
        if ((this.value == null) == (annotationParameterValue.value == null)) {
            return this.value == null || this.value.equals(annotationParameterValue.value);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.name, this.value);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.github.classgraph.ScanResultObject
    public void toString(boolean z, StringBuilder sb) {
        sb.append(this.name);
        sb.append("=");
        toStringParamValueOnly(z, sb);
    }

    private static void toString(Object obj, boolean z, StringBuilder sb) {
        if (obj == null) {
            sb.append("null");
        } else if (obj instanceof ScanResultObject) {
            ((ScanResultObject) obj).toString(z, sb);
        } else {
            sb.append(obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void toStringParamValueOnly(boolean z, StringBuilder sb) {
        if (this.value == null) {
            sb.append("null");
            return;
        }
        Object obj = this.value.get();
        if (obj.getClass().isArray()) {
            sb.append('{');
            int length = Array.getLength(obj);
            for (int i = 0; i < length; i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                toString(Array.get(obj, i), z, sb);
            }
            sb.append('}');
            return;
        }
        if (obj instanceof String) {
            sb.append('\"');
            sb.append(obj.toString().replace("\"", "\\\"").replace(SequenceUtils.EOL, "\\n").replace("\r", "\\r"));
            sb.append('\"');
        } else {
            if (obj instanceof Character) {
                sb.append('\'');
                sb.append(obj.toString().replace("'", "\\'").replace(SequenceUtils.EOL, "\\n").replace("\r", "\\r"));
                sb.append('\'');
                return;
            }
            toString(obj, z, sb);
        }
    }

    private String toStringParamValueOnly() {
        StringBuilder sb = new StringBuilder();
        toStringParamValueOnly(false, sb);
        return sb.toString();
    }
}
