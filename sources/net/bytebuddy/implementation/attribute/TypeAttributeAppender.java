package net.bytebuddy.implementation.attribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.annotation.AnnotationList;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.implementation.attribute.AnnotationAppender;
import net.bytebuddy.jar.asm.ClassVisitor;
import net.bytebuddy.utility.nullability.MaybeNull;

/* loaded from: infinitode-2.jar:net/bytebuddy/implementation/attribute/TypeAttributeAppender.class */
public interface TypeAttributeAppender {
    void apply(ClassVisitor classVisitor, TypeDescription typeDescription, AnnotationValueFilter annotationValueFilter);

    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/attribute/TypeAttributeAppender$NoOp.class */
    public enum NoOp implements TypeAttributeAppender {
        INSTANCE;

        @Override // net.bytebuddy.implementation.attribute.TypeAttributeAppender
        public final void apply(ClassVisitor classVisitor, TypeDescription typeDescription, AnnotationValueFilter annotationValueFilter) {
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/attribute/TypeAttributeAppender$ForInstrumentedType.class */
    public enum ForInstrumentedType implements TypeAttributeAppender {
        INSTANCE;

        @Override // net.bytebuddy.implementation.attribute.TypeAttributeAppender
        public final void apply(ClassVisitor classVisitor, TypeDescription typeDescription, AnnotationValueFilter annotationValueFilter) {
            AnnotationAppender ofTypeVariable = AnnotationAppender.ForTypeAnnotations.ofTypeVariable(new AnnotationAppender.Default(new AnnotationAppender.Target.OnType(classVisitor)), annotationValueFilter, true, typeDescription.getTypeVariables());
            TypeDescription.Generic superClass = typeDescription.getSuperClass();
            if (superClass != null) {
                ofTypeVariable = (AnnotationAppender) superClass.accept(AnnotationAppender.ForTypeAnnotations.ofSuperClass(ofTypeVariable, annotationValueFilter));
            }
            int i = 0;
            Iterator it = typeDescription.getInterfaces().iterator();
            while (it.hasNext()) {
                int i2 = i;
                i++;
                ofTypeVariable = (AnnotationAppender) ((TypeDescription.Generic) it.next()).accept(AnnotationAppender.ForTypeAnnotations.ofInterfaceType(ofTypeVariable, annotationValueFilter, i2));
            }
            Iterator it2 = typeDescription.getDeclaredAnnotations().iterator();
            while (it2.hasNext()) {
                ofTypeVariable = ofTypeVariable.append((AnnotationDescription) it2.next(), annotationValueFilter);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/attribute/TypeAttributeAppender$ForInstrumentedType$Differentiating.class */
        public static class Differentiating implements TypeAttributeAppender {
            private final int annotationIndex;
            private final int typeVariableIndex;
            private final int interfaceTypeIndex;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.annotationIndex == ((Differentiating) obj).annotationIndex && this.typeVariableIndex == ((Differentiating) obj).typeVariableIndex && this.interfaceTypeIndex == ((Differentiating) obj).interfaceTypeIndex;
            }

            public int hashCode() {
                return (((((getClass().hashCode() * 31) + this.annotationIndex) * 31) + this.typeVariableIndex) * 31) + this.interfaceTypeIndex;
            }

            public Differentiating(TypeDescription typeDescription) {
                this(typeDescription.getDeclaredAnnotations().size(), typeDescription.getTypeVariables().size(), typeDescription.getInterfaces().size());
            }

            protected Differentiating(int i, int i2, int i3) {
                this.annotationIndex = i;
                this.typeVariableIndex = i2;
                this.interfaceTypeIndex = i3;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r0v28, types: [net.bytebuddy.implementation.attribute.AnnotationAppender] */
            @Override // net.bytebuddy.implementation.attribute.TypeAttributeAppender
            public void apply(ClassVisitor classVisitor, TypeDescription typeDescription, AnnotationValueFilter annotationValueFilter) {
                AnnotationAppender.Default r0 = new AnnotationAppender.Default(new AnnotationAppender.Target.OnType(classVisitor));
                AnnotationAppender.Default r7 = r0;
                AnnotationAppender.ForTypeAnnotations.ofTypeVariable(r0, annotationValueFilter, true, this.typeVariableIndex, typeDescription.getTypeVariables());
                TypeList.Generic interfaces = typeDescription.getInterfaces();
                int i = this.interfaceTypeIndex;
                Iterator it = interfaces.subList(this.interfaceTypeIndex, interfaces.size()).iterator();
                while (it.hasNext()) {
                    int i2 = i;
                    i++;
                    r7 = (AnnotationAppender) ((TypeDescription.Generic) it.next()).accept(AnnotationAppender.ForTypeAnnotations.ofInterfaceType(r7, annotationValueFilter, i2));
                }
                AnnotationList declaredAnnotations = typeDescription.getDeclaredAnnotations();
                Iterator it2 = declaredAnnotations.subList(this.annotationIndex, declaredAnnotations.size()).iterator();
                while (it2.hasNext()) {
                    r7 = r7.append((AnnotationDescription) it2.next(), annotationValueFilter);
                }
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/attribute/TypeAttributeAppender$Explicit.class */
    public static class Explicit implements TypeAttributeAppender {
        private final List<? extends AnnotationDescription> annotations;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.annotations.equals(((Explicit) obj).annotations);
        }

        public int hashCode() {
            return (getClass().hashCode() * 31) + this.annotations.hashCode();
        }

        public Explicit(List<? extends AnnotationDescription> list) {
            this.annotations = list;
        }

        @Override // net.bytebuddy.implementation.attribute.TypeAttributeAppender
        public void apply(ClassVisitor classVisitor, TypeDescription typeDescription, AnnotationValueFilter annotationValueFilter) {
            AnnotationAppender.Default r7 = new AnnotationAppender.Default(new AnnotationAppender.Target.OnType(classVisitor));
            Iterator<? extends AnnotationDescription> it = this.annotations.iterator();
            while (it.hasNext()) {
                r7 = r7.append(it.next(), annotationValueFilter);
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/attribute/TypeAttributeAppender$Compound.class */
    public static class Compound implements TypeAttributeAppender {
        private final List<TypeAttributeAppender> typeAttributeAppenders;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.typeAttributeAppenders.equals(((Compound) obj).typeAttributeAppenders);
        }

        public int hashCode() {
            return (getClass().hashCode() * 31) + this.typeAttributeAppenders.hashCode();
        }

        public Compound(TypeAttributeAppender... typeAttributeAppenderArr) {
            this((List<? extends TypeAttributeAppender>) Arrays.asList(typeAttributeAppenderArr));
        }

        public Compound(List<? extends TypeAttributeAppender> list) {
            this.typeAttributeAppenders = new ArrayList();
            for (TypeAttributeAppender typeAttributeAppender : list) {
                if (typeAttributeAppender instanceof Compound) {
                    this.typeAttributeAppenders.addAll(((Compound) typeAttributeAppender).typeAttributeAppenders);
                } else if (!(typeAttributeAppender instanceof NoOp)) {
                    this.typeAttributeAppenders.add(typeAttributeAppender);
                }
            }
        }

        @Override // net.bytebuddy.implementation.attribute.TypeAttributeAppender
        public void apply(ClassVisitor classVisitor, TypeDescription typeDescription, AnnotationValueFilter annotationValueFilter) {
            Iterator<TypeAttributeAppender> it = this.typeAttributeAppenders.iterator();
            while (it.hasNext()) {
                it.next().apply(classVisitor, typeDescription, annotationValueFilter);
            }
        }
    }
}
