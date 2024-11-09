package net.bytebuddy.jar.asm.commons;

import net.bytebuddy.jar.asm.AnnotationVisitor;

/* loaded from: infinitode-2.jar:net/bytebuddy/jar/asm/commons/AnnotationRemapper.class */
public class AnnotationRemapper extends AnnotationVisitor {
    protected final String descriptor;
    protected final Remapper remapper;

    @Deprecated
    public AnnotationRemapper(AnnotationVisitor annotationVisitor, Remapper remapper) {
        this((String) null, annotationVisitor, remapper);
    }

    public AnnotationRemapper(String str, AnnotationVisitor annotationVisitor, Remapper remapper) {
        this(589824, str, annotationVisitor, remapper);
    }

    @Deprecated
    protected AnnotationRemapper(int i, AnnotationVisitor annotationVisitor, Remapper remapper) {
        this(i, null, annotationVisitor, remapper);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AnnotationRemapper(int i, String str, AnnotationVisitor annotationVisitor, Remapper remapper) {
        super(i, annotationVisitor);
        this.descriptor = str;
        this.remapper = remapper;
    }

    @Override // net.bytebuddy.jar.asm.AnnotationVisitor
    public void visit(String str, Object obj) {
        super.visit(mapAnnotationAttributeName(str), this.remapper.mapValue(obj));
    }

    @Override // net.bytebuddy.jar.asm.AnnotationVisitor
    public void visitEnum(String str, String str2, String str3) {
        super.visitEnum(mapAnnotationAttributeName(str), this.remapper.mapDesc(str2), str3);
    }

    @Override // net.bytebuddy.jar.asm.AnnotationVisitor
    public AnnotationVisitor visitAnnotation(String str, String str2) {
        AnnotationVisitor visitAnnotation = super.visitAnnotation(mapAnnotationAttributeName(str), this.remapper.mapDesc(str2));
        if (visitAnnotation == null) {
            return null;
        }
        if (visitAnnotation == this.av) {
            return this;
        }
        return createAnnotationRemapper(str2, visitAnnotation);
    }

    @Override // net.bytebuddy.jar.asm.AnnotationVisitor
    public AnnotationVisitor visitArray(String str) {
        AnnotationVisitor visitArray = super.visitArray(mapAnnotationAttributeName(str));
        if (visitArray == null) {
            return null;
        }
        if (visitArray == this.av) {
            return this;
        }
        return createAnnotationRemapper(null, visitArray);
    }

    @Deprecated
    protected AnnotationVisitor createAnnotationRemapper(AnnotationVisitor annotationVisitor) {
        return new AnnotationRemapper(this.api, null, annotationVisitor, this.remapper);
    }

    protected AnnotationVisitor createAnnotationRemapper(String str, AnnotationVisitor annotationVisitor) {
        return new AnnotationRemapper(this.api, str, annotationVisitor, this.remapper).a(createAnnotationRemapper(annotationVisitor));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final AnnotationVisitor a(AnnotationVisitor annotationVisitor) {
        if (annotationVisitor.getClass() == getClass()) {
            AnnotationRemapper annotationRemapper = (AnnotationRemapper) annotationVisitor;
            if (annotationRemapper.api == this.api && annotationRemapper.av == this.av && annotationRemapper.remapper == this.remapper) {
                return this;
            }
        }
        return annotationVisitor;
    }

    private String mapAnnotationAttributeName(String str) {
        if (this.descriptor == null) {
            return str;
        }
        return this.remapper.mapAnnotationAttributeName(this.descriptor, str);
    }
}
