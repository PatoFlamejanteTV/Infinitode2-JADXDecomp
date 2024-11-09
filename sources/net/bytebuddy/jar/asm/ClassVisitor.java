package net.bytebuddy.jar.asm;

/* loaded from: infinitode-2.jar:net/bytebuddy/jar/asm/ClassVisitor.class */
public abstract class ClassVisitor {
    protected final int api;
    protected ClassVisitor cv;

    /* JADX INFO: Access modifiers changed from: protected */
    public ClassVisitor(int i) {
        this(i, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ClassVisitor(int i, ClassVisitor classVisitor) {
        if (i != 589824 && i != 524288 && i != 458752 && i != 393216 && i != 327680 && i != 262144 && i != 17432576) {
            throw new IllegalArgumentException("Unsupported api " + i);
        }
        if (i == 17432576) {
            Constants.a(this);
        }
        this.api = i;
        this.cv = classVisitor;
    }

    public ClassVisitor getDelegate() {
        return this.cv;
    }

    public void visit(int i, int i2, String str, String str2, String str3, String[] strArr) {
        if (this.api < 524288 && (i2 & 65536) != 0) {
            throw new UnsupportedOperationException("Records requires ASM8");
        }
        if (this.cv != null) {
            this.cv.visit(i, i2, str, str2, str3, strArr);
        }
    }

    public void visitSource(String str, String str2) {
        if (this.cv != null) {
            this.cv.visitSource(str, str2);
        }
    }

    public ModuleVisitor visitModule(String str, int i, String str2) {
        if (this.api < 393216) {
            throw new UnsupportedOperationException("Module requires ASM6");
        }
        if (this.cv != null) {
            return this.cv.visitModule(str, i, str2);
        }
        return null;
    }

    public void visitNestHost(String str) {
        if (this.api < 458752) {
            throw new UnsupportedOperationException("NestHost requires ASM7");
        }
        if (this.cv != null) {
            this.cv.visitNestHost(str);
        }
    }

    public void visitOuterClass(String str, String str2, String str3) {
        if (this.cv != null) {
            this.cv.visitOuterClass(str, str2, str3);
        }
    }

    public AnnotationVisitor visitAnnotation(String str, boolean z) {
        if (this.cv != null) {
            return this.cv.visitAnnotation(str, z);
        }
        return null;
    }

    public AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String str, boolean z) {
        if (this.api < 327680) {
            throw new UnsupportedOperationException("TypeAnnotation requires ASM5");
        }
        if (this.cv != null) {
            return this.cv.visitTypeAnnotation(i, typePath, str, z);
        }
        return null;
    }

    public void visitAttribute(Attribute attribute) {
        if (this.cv != null) {
            this.cv.visitAttribute(attribute);
        }
    }

    public void visitNestMember(String str) {
        if (this.api < 458752) {
            throw new UnsupportedOperationException("NestMember requires ASM7");
        }
        if (this.cv != null) {
            this.cv.visitNestMember(str);
        }
    }

    public void visitPermittedSubclass(String str) {
        if (this.api < 589824) {
            throw new UnsupportedOperationException("PermittedSubclasses requires ASM9");
        }
        if (this.cv != null) {
            this.cv.visitPermittedSubclass(str);
        }
    }

    public void visitInnerClass(String str, String str2, String str3, int i) {
        if (this.cv != null) {
            this.cv.visitInnerClass(str, str2, str3, i);
        }
    }

    public RecordComponentVisitor visitRecordComponent(String str, String str2, String str3) {
        if (this.api < 524288) {
            throw new UnsupportedOperationException("Record requires ASM8");
        }
        if (this.cv != null) {
            return this.cv.visitRecordComponent(str, str2, str3);
        }
        return null;
    }

    public FieldVisitor visitField(int i, String str, String str2, String str3, Object obj) {
        if (this.cv != null) {
            return this.cv.visitField(i, str, str2, str3, obj);
        }
        return null;
    }

    public MethodVisitor visitMethod(int i, String str, String str2, String str3, String[] strArr) {
        if (this.cv != null) {
            return this.cv.visitMethod(i, str, str2, str3, strArr);
        }
        return null;
    }

    public void visitEnd() {
        if (this.cv != null) {
            this.cv.visitEnd();
        }
    }
}
