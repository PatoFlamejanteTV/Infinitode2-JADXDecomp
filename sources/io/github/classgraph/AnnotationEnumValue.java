package io.github.classgraph;

import java.lang.reflect.Field;

/* loaded from: infinitode-2.jar:io/github/classgraph/AnnotationEnumValue.class */
public class AnnotationEnumValue extends ScanResultObject implements Comparable<AnnotationEnumValue> {
    private String className;
    private String valueName;

    @Override // io.github.classgraph.ScanResultObject
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    @Override // io.github.classgraph.ScanResultObject
    public /* bridge */ /* synthetic */ String toStringWithSimpleNames() {
        return super.toStringWithSimpleNames();
    }

    AnnotationEnumValue() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AnnotationEnumValue(String str, String str2) {
        this.className = str;
        this.valueName = str2;
    }

    @Override // io.github.classgraph.ScanResultObject
    public String getClassName() {
        return this.className;
    }

    public String getValueName() {
        return this.valueName;
    }

    public String getName() {
        return this.className + "." + this.valueName;
    }

    public Object loadClassAndReturnEnumValue(boolean z) {
        Class<?> loadClass = super.loadClass(z);
        if (loadClass == null) {
            if (z) {
                return null;
            }
            throw new IllegalArgumentException("Enum class " + this.className + " could not be loaded");
        }
        if (!loadClass.isEnum()) {
            throw new IllegalArgumentException("Class " + this.className + " is not an enum");
        }
        try {
            Field declaredField = loadClass.getDeclaredField(this.valueName);
            if (!declaredField.isEnumConstant()) {
                throw new IllegalArgumentException("Field " + this + " is not an enum constant");
            }
            try {
                return declaredField.get(null);
            } catch (ReflectiveOperationException | SecurityException e) {
                throw new IllegalArgumentException("Field " + this + " is not accessible", e);
            }
        } catch (ReflectiveOperationException | SecurityException e2) {
            throw new IllegalArgumentException("Could not find enum constant " + this, e2);
        }
    }

    public Object loadClassAndReturnEnumValue() {
        return loadClassAndReturnEnumValue(false);
    }

    @Override // java.lang.Comparable
    public int compareTo(AnnotationEnumValue annotationEnumValue) {
        int compareTo = this.className.compareTo(annotationEnumValue.className);
        return compareTo == 0 ? this.valueName.compareTo(annotationEnumValue.valueName) : compareTo;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof AnnotationEnumValue) && compareTo((AnnotationEnumValue) obj) == 0;
    }

    public int hashCode() {
        return (this.className.hashCode() * 11) + this.valueName.hashCode();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.github.classgraph.ScanResultObject
    public void toString(boolean z, StringBuilder sb) {
        sb.append(z ? ClassInfo.getSimpleName(this.className) : this.className);
        sb.append('.');
        sb.append(this.valueName);
    }
}
