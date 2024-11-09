package net.bytebuddy.asm;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import net.bytebuddy.asm.AsmVisitorWrapper;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.annotation.AnnotationList;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.attribute.AnnotationValueFilter;
import net.bytebuddy.implementation.attribute.FieldAttributeAppender;
import net.bytebuddy.implementation.attribute.MethodAttributeAppender;
import net.bytebuddy.jar.asm.FieldVisitor;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.pool.TypePool;
import net.bytebuddy.utility.OpenedClassReader;
import net.bytebuddy.utility.nullability.MaybeNull;

@HashCodeAndEqualsPlugin.Enhance
/* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberAttributeExtension.class */
public abstract class MemberAttributeExtension<T> {
    protected final AnnotationValueFilter.Factory annotationValueFilterFactory;
    protected final T attributeAppenderFactory;

    public boolean equals(@MaybeNull Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.annotationValueFilterFactory.equals(((MemberAttributeExtension) obj).annotationValueFilterFactory) && this.attributeAppenderFactory.equals(((MemberAttributeExtension) obj).attributeAppenderFactory);
    }

    public int hashCode() {
        return (((getClass().hashCode() * 31) + this.annotationValueFilterFactory.hashCode()) * 31) + this.attributeAppenderFactory.hashCode();
    }

    protected MemberAttributeExtension(AnnotationValueFilter.Factory factory, T t) {
        this.annotationValueFilterFactory = factory;
        this.attributeAppenderFactory = t;
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberAttributeExtension$ForField.class */
    public static class ForField extends MemberAttributeExtension<FieldAttributeAppender.Factory> implements AsmVisitorWrapper.ForDeclaredFields.FieldVisitorWrapper {
        public ForField() {
            this(AnnotationValueFilter.Default.APPEND_DEFAULTS);
        }

        public ForField(AnnotationValueFilter.Factory factory) {
            this(factory, FieldAttributeAppender.NoOp.INSTANCE);
        }

        protected ForField(AnnotationValueFilter.Factory factory, FieldAttributeAppender.Factory factory2) {
            super(factory, factory2);
        }

        public ForField annotate(Annotation... annotationArr) {
            return annotate(Arrays.asList(annotationArr));
        }

        public ForField annotate(List<? extends Annotation> list) {
            return annotate((Collection<? extends AnnotationDescription>) new AnnotationList.ForLoadedAnnotations(list));
        }

        public ForField annotate(AnnotationDescription... annotationDescriptionArr) {
            return annotate((Collection<? extends AnnotationDescription>) Arrays.asList(annotationDescriptionArr));
        }

        public ForField annotate(Collection<? extends AnnotationDescription> collection) {
            return attribute(new FieldAttributeAppender.Explicit(new ArrayList(collection)));
        }

        public ForField attribute(FieldAttributeAppender.Factory factory) {
            return new ForField(this.annotationValueFilterFactory, new FieldAttributeAppender.Factory.Compound((FieldAttributeAppender.Factory) this.attributeAppenderFactory, factory));
        }

        @Override // net.bytebuddy.asm.AsmVisitorWrapper.ForDeclaredFields.FieldVisitorWrapper
        public FieldVisitor wrap(TypeDescription typeDescription, FieldDescription.InDefinedShape inDefinedShape, FieldVisitor fieldVisitor) {
            return new FieldAttributeVisitor(fieldVisitor, inDefinedShape, ((FieldAttributeAppender.Factory) this.attributeAppenderFactory).make(typeDescription), this.annotationValueFilterFactory.on(inDefinedShape), (byte) 0);
        }

        public AsmVisitorWrapper on(ElementMatcher<? super FieldDescription.InDefinedShape> elementMatcher) {
            return new AsmVisitorWrapper.ForDeclaredFields().field(elementMatcher, this);
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberAttributeExtension$ForField$FieldAttributeVisitor.class */
        private static class FieldAttributeVisitor extends FieldVisitor {
            private final FieldDescription fieldDescription;
            private final FieldAttributeAppender fieldAttributeAppender;
            private final AnnotationValueFilter annotationValueFilter;

            /* synthetic */ FieldAttributeVisitor(FieldVisitor fieldVisitor, FieldDescription fieldDescription, FieldAttributeAppender fieldAttributeAppender, AnnotationValueFilter annotationValueFilter, byte b2) {
                this(fieldVisitor, fieldDescription, fieldAttributeAppender, annotationValueFilter);
            }

            private FieldAttributeVisitor(FieldVisitor fieldVisitor, FieldDescription fieldDescription, FieldAttributeAppender fieldAttributeAppender, AnnotationValueFilter annotationValueFilter) {
                super(OpenedClassReader.ASM_API, fieldVisitor);
                this.fieldDescription = fieldDescription;
                this.fieldAttributeAppender = fieldAttributeAppender;
                this.annotationValueFilter = annotationValueFilter;
            }

            @Override // net.bytebuddy.jar.asm.FieldVisitor
            public void visitEnd() {
                this.fieldAttributeAppender.apply(this.fv, this.fieldDescription, this.annotationValueFilter);
                super.visitEnd();
            }
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberAttributeExtension$ForMethod.class */
    public static class ForMethod extends MemberAttributeExtension<MethodAttributeAppender.Factory> implements AsmVisitorWrapper.ForDeclaredMethods.MethodVisitorWrapper {
        public ForMethod() {
            this(AnnotationValueFilter.Default.APPEND_DEFAULTS);
        }

        public ForMethod(AnnotationValueFilter.Factory factory) {
            this(factory, MethodAttributeAppender.NoOp.INSTANCE);
        }

        protected ForMethod(AnnotationValueFilter.Factory factory, MethodAttributeAppender.Factory factory2) {
            super(factory, factory2);
        }

        public ForMethod annotateMethod(Annotation... annotationArr) {
            return annotateMethod(Arrays.asList(annotationArr));
        }

        public ForMethod annotateMethod(List<? extends Annotation> list) {
            return annotateMethod((Collection<? extends AnnotationDescription>) new AnnotationList.ForLoadedAnnotations(list));
        }

        public ForMethod annotateMethod(AnnotationDescription... annotationDescriptionArr) {
            return annotateMethod((Collection<? extends AnnotationDescription>) Arrays.asList(annotationDescriptionArr));
        }

        public ForMethod annotateMethod(Collection<? extends AnnotationDescription> collection) {
            return attribute(new MethodAttributeAppender.Explicit(new ArrayList(collection)));
        }

        public ForMethod annotateParameter(int i, Annotation... annotationArr) {
            return annotateParameter(i, Arrays.asList(annotationArr));
        }

        public ForMethod annotateParameter(int i, List<? extends Annotation> list) {
            return annotateParameter(i, (Collection<? extends AnnotationDescription>) new AnnotationList.ForLoadedAnnotations(list));
        }

        public ForMethod annotateParameter(int i, AnnotationDescription... annotationDescriptionArr) {
            return annotateParameter(i, (Collection<? extends AnnotationDescription>) Arrays.asList(annotationDescriptionArr));
        }

        public ForMethod annotateParameter(int i, Collection<? extends AnnotationDescription> collection) {
            if (i < 0) {
                throw new IllegalArgumentException("Parameter index cannot be negative: " + i);
            }
            return attribute(new MethodAttributeAppender.Explicit(i, new ArrayList(collection)));
        }

        public ForMethod attribute(MethodAttributeAppender.Factory factory) {
            return new ForMethod(this.annotationValueFilterFactory, new MethodAttributeAppender.Factory.Compound((MethodAttributeAppender.Factory) this.attributeAppenderFactory, factory));
        }

        @Override // net.bytebuddy.asm.AsmVisitorWrapper.ForDeclaredMethods.MethodVisitorWrapper
        public MethodVisitor wrap(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, TypePool typePool, int i, int i2) {
            return new AttributeAppendingMethodVisitor(methodVisitor, methodDescription, ((MethodAttributeAppender.Factory) this.attributeAppenderFactory).make(typeDescription), this.annotationValueFilterFactory.on(methodDescription), (byte) 0);
        }

        public AsmVisitorWrapper on(ElementMatcher<? super MethodDescription> elementMatcher) {
            return new AsmVisitorWrapper.ForDeclaredMethods().invokable(elementMatcher, this);
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/asm/MemberAttributeExtension$ForMethod$AttributeAppendingMethodVisitor.class */
        private static class AttributeAppendingMethodVisitor extends MethodVisitor {
            private final MethodDescription methodDescription;
            private final MethodAttributeAppender methodAttributeAppender;
            private final AnnotationValueFilter annotationValueFilter;
            private boolean applicable;

            /* synthetic */ AttributeAppendingMethodVisitor(MethodVisitor methodVisitor, MethodDescription methodDescription, MethodAttributeAppender methodAttributeAppender, AnnotationValueFilter annotationValueFilter, byte b2) {
                this(methodVisitor, methodDescription, methodAttributeAppender, annotationValueFilter);
            }

            private AttributeAppendingMethodVisitor(MethodVisitor methodVisitor, MethodDescription methodDescription, MethodAttributeAppender methodAttributeAppender, AnnotationValueFilter annotationValueFilter) {
                super(OpenedClassReader.ASM_API, methodVisitor);
                this.methodDescription = methodDescription;
                this.methodAttributeAppender = methodAttributeAppender;
                this.annotationValueFilter = annotationValueFilter;
                this.applicable = true;
            }

            @Override // net.bytebuddy.jar.asm.MethodVisitor
            public void visitCode() {
                if (this.applicable) {
                    this.methodAttributeAppender.apply(this.mv, this.methodDescription, this.annotationValueFilter);
                    this.applicable = false;
                }
                super.visitCode();
            }

            @Override // net.bytebuddy.jar.asm.MethodVisitor
            public void visitEnd() {
                if (this.applicable) {
                    this.methodAttributeAppender.apply(this.mv, this.methodDescription, this.annotationValueFilter);
                    this.applicable = false;
                }
                super.visitEnd();
            }
        }
    }
}
