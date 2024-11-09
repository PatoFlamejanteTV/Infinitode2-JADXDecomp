package io.github.classgraph;

import nonapi.io.github.classgraph.types.ParseException;

/* loaded from: infinitode-2.jar:io/github/classgraph/AnnotationClassRef.class */
public class AnnotationClassRef extends ScanResultObject {
    private String typeDescriptorStr;
    private transient TypeSignature typeSignature;
    private transient String className;

    @Override // io.github.classgraph.ScanResultObject
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    @Override // io.github.classgraph.ScanResultObject
    public /* bridge */ /* synthetic */ String toStringWithSimpleNames() {
        return super.toStringWithSimpleNames();
    }

    AnnotationClassRef() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AnnotationClassRef(String str) {
        this.typeDescriptorStr = str;
    }

    public String getName() {
        return getClassName();
    }

    private TypeSignature getTypeSignature() {
        if (this.typeSignature == null) {
            try {
                this.typeSignature = TypeSignature.parse(this.typeDescriptorStr, (String) null);
                this.typeSignature.setScanResult(this.scanResult);
            } catch (ParseException e) {
                throw new IllegalArgumentException(e);
            }
        }
        return this.typeSignature;
    }

    @Override // io.github.classgraph.ScanResultObject
    public Class<?> loadClass(boolean z) {
        getTypeSignature();
        if (this.typeSignature instanceof BaseTypeSignature) {
            return ((BaseTypeSignature) this.typeSignature).getType();
        }
        if (this.typeSignature instanceof ClassRefTypeSignature) {
            return this.typeSignature.loadClass(z);
        }
        if (this.typeSignature instanceof ArrayTypeSignature) {
            return this.typeSignature.loadClass(z);
        }
        throw new IllegalArgumentException("Got unexpected type " + this.typeSignature.getClass().getName() + " for ref type signature: " + this.typeDescriptorStr);
    }

    @Override // io.github.classgraph.ScanResultObject
    public Class<?> loadClass() {
        return loadClass(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.github.classgraph.ScanResultObject
    public String getClassName() {
        if (this.className == null) {
            getTypeSignature();
            if (this.typeSignature instanceof BaseTypeSignature) {
                this.className = ((BaseTypeSignature) this.typeSignature).getTypeStr();
            } else if (this.typeSignature instanceof ClassRefTypeSignature) {
                this.className = ((ClassRefTypeSignature) this.typeSignature).getFullyQualifiedClassName();
            } else if (this.typeSignature instanceof ArrayTypeSignature) {
                this.className = this.typeSignature.getClassName();
            } else {
                throw new IllegalArgumentException("Got unexpected type " + this.typeSignature.getClass().getName() + " for ref type signature: " + this.typeDescriptorStr);
            }
        }
        return this.className;
    }

    @Override // io.github.classgraph.ScanResultObject
    public ClassInfo getClassInfo() {
        getTypeSignature();
        return this.typeSignature.getClassInfo();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.github.classgraph.ScanResultObject
    public void setScanResult(ScanResult scanResult) {
        super.setScanResult(scanResult);
        if (this.typeSignature != null) {
            this.typeSignature.setScanResult(scanResult);
        }
    }

    public int hashCode() {
        return getTypeSignature().hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AnnotationClassRef)) {
            return false;
        }
        return getTypeSignature().equals(((AnnotationClassRef) obj).getTypeSignature());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.github.classgraph.ScanResultObject
    public void toString(boolean z, StringBuilder sb) {
        sb.append(getTypeSignature().toString(z)).append(".class");
    }
}
