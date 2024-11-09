package net.bytebuddy.implementation.attribute;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.List;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.enumeration.EnumerationDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.implementation.attribute.AnnotationValueFilter;
import net.bytebuddy.jar.asm.AnnotationVisitor;
import net.bytebuddy.jar.asm.ClassVisitor;
import net.bytebuddy.jar.asm.FieldVisitor;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.RecordComponentVisitor;
import net.bytebuddy.jar.asm.Type;
import net.bytebuddy.jar.asm.TypePath;
import net.bytebuddy.jar.asm.TypeReference;
import net.bytebuddy.utility.nullability.AlwaysNull;
import net.bytebuddy.utility.nullability.MaybeNull;

/* loaded from: infinitode-2.jar:net/bytebuddy/implementation/attribute/AnnotationAppender.class */
public interface AnnotationAppender {

    @AlwaysNull
    public static final String NO_NAME = null;

    AnnotationAppender append(AnnotationDescription annotationDescription, AnnotationValueFilter annotationValueFilter);

    AnnotationAppender append(AnnotationDescription annotationDescription, AnnotationValueFilter annotationValueFilter, int i, String str);

    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/attribute/AnnotationAppender$Target.class */
    public interface Target {
        @MaybeNull
        AnnotationVisitor visit(String str, boolean z);

        @MaybeNull
        AnnotationVisitor visit(String str, boolean z, int i, String str2);

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/attribute/AnnotationAppender$Target$OnType.class */
        public static class OnType implements Target {
            private final ClassVisitor classVisitor;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.classVisitor.equals(((OnType) obj).classVisitor);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.classVisitor.hashCode();
            }

            public OnType(ClassVisitor classVisitor) {
                this.classVisitor = classVisitor;
            }

            @Override // net.bytebuddy.implementation.attribute.AnnotationAppender.Target
            @MaybeNull
            public AnnotationVisitor visit(String str, boolean z) {
                return this.classVisitor.visitAnnotation(str, z);
            }

            @Override // net.bytebuddy.implementation.attribute.AnnotationAppender.Target
            @MaybeNull
            public AnnotationVisitor visit(String str, boolean z, int i, String str2) {
                return this.classVisitor.visitTypeAnnotation(i, TypePath.fromString(str2), str, z);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/attribute/AnnotationAppender$Target$OnField.class */
        public static class OnField implements Target {
            private final FieldVisitor fieldVisitor;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.fieldVisitor.equals(((OnField) obj).fieldVisitor);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.fieldVisitor.hashCode();
            }

            public OnField(FieldVisitor fieldVisitor) {
                this.fieldVisitor = fieldVisitor;
            }

            @Override // net.bytebuddy.implementation.attribute.AnnotationAppender.Target
            @MaybeNull
            public AnnotationVisitor visit(String str, boolean z) {
                return this.fieldVisitor.visitAnnotation(str, z);
            }

            @Override // net.bytebuddy.implementation.attribute.AnnotationAppender.Target
            @MaybeNull
            public AnnotationVisitor visit(String str, boolean z, int i, String str2) {
                return this.fieldVisitor.visitTypeAnnotation(i, TypePath.fromString(str2), str, z);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/attribute/AnnotationAppender$Target$OnMethod.class */
        public static class OnMethod implements Target {
            private final MethodVisitor methodVisitor;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.methodVisitor.equals(((OnMethod) obj).methodVisitor);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.methodVisitor.hashCode();
            }

            public OnMethod(MethodVisitor methodVisitor) {
                this.methodVisitor = methodVisitor;
            }

            @Override // net.bytebuddy.implementation.attribute.AnnotationAppender.Target
            @MaybeNull
            public AnnotationVisitor visit(String str, boolean z) {
                return this.methodVisitor.visitAnnotation(str, z);
            }

            @Override // net.bytebuddy.implementation.attribute.AnnotationAppender.Target
            @MaybeNull
            public AnnotationVisitor visit(String str, boolean z, int i, String str2) {
                return this.methodVisitor.visitTypeAnnotation(i, TypePath.fromString(str2), str, z);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/attribute/AnnotationAppender$Target$OnMethodParameter.class */
        public static class OnMethodParameter implements Target {
            private final MethodVisitor methodVisitor;
            private final int parameterIndex;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.parameterIndex == ((OnMethodParameter) obj).parameterIndex && this.methodVisitor.equals(((OnMethodParameter) obj).methodVisitor);
            }

            public int hashCode() {
                return (((getClass().hashCode() * 31) + this.methodVisitor.hashCode()) * 31) + this.parameterIndex;
            }

            public OnMethodParameter(MethodVisitor methodVisitor, int i) {
                this.methodVisitor = methodVisitor;
                this.parameterIndex = i;
            }

            @Override // net.bytebuddy.implementation.attribute.AnnotationAppender.Target
            @MaybeNull
            public AnnotationVisitor visit(String str, boolean z) {
                return this.methodVisitor.visitParameterAnnotation(this.parameterIndex, str, z);
            }

            @Override // net.bytebuddy.implementation.attribute.AnnotationAppender.Target
            @MaybeNull
            public AnnotationVisitor visit(String str, boolean z, int i, String str2) {
                return this.methodVisitor.visitTypeAnnotation(i, TypePath.fromString(str2), str, z);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/attribute/AnnotationAppender$Target$OnRecordComponent.class */
        public static class OnRecordComponent implements Target {
            private final RecordComponentVisitor recordComponentVisitor;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.recordComponentVisitor.equals(((OnRecordComponent) obj).recordComponentVisitor);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.recordComponentVisitor.hashCode();
            }

            public OnRecordComponent(RecordComponentVisitor recordComponentVisitor) {
                this.recordComponentVisitor = recordComponentVisitor;
            }

            @Override // net.bytebuddy.implementation.attribute.AnnotationAppender.Target
            @MaybeNull
            public AnnotationVisitor visit(String str, boolean z) {
                return this.recordComponentVisitor.visitAnnotation(str, z);
            }

            @Override // net.bytebuddy.implementation.attribute.AnnotationAppender.Target
            @MaybeNull
            public AnnotationVisitor visit(String str, boolean z, int i, String str2) {
                return this.recordComponentVisitor.visitTypeAnnotation(i, TypePath.fromString(str2), str, z);
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/attribute/AnnotationAppender$Default.class */
    public static class Default implements AnnotationAppender {
        private final Target target;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.target.equals(((Default) obj).target);
        }

        public int hashCode() {
            return (getClass().hashCode() * 31) + this.target.hashCode();
        }

        public Default(Target target) {
            this.target = target;
        }

        private static void handle(AnnotationVisitor annotationVisitor, AnnotationDescription annotationDescription, AnnotationValueFilter annotationValueFilter) {
            for (MethodDescription.InDefinedShape inDefinedShape : annotationDescription.getAnnotationType().getDeclaredMethods()) {
                if (annotationValueFilter.isRelevant(annotationDescription, inDefinedShape)) {
                    apply(annotationVisitor, inDefinedShape.getReturnType().asErasure(), inDefinedShape.getName(), annotationDescription.getValue(inDefinedShape).resolve());
                }
            }
            annotationVisitor.visitEnd();
        }

        public static void apply(AnnotationVisitor annotationVisitor, TypeDescription typeDescription, @MaybeNull String str, Object obj) {
            if (typeDescription.isArray()) {
                AnnotationVisitor visitArray = annotationVisitor.visitArray(str);
                int length = Array.getLength(obj);
                TypeDescription componentType = typeDescription.getComponentType();
                for (int i = 0; i < length; i++) {
                    apply(visitArray, componentType, NO_NAME, Array.get(obj, i));
                }
                visitArray.visitEnd();
                return;
            }
            if (typeDescription.isAnnotation()) {
                handle(annotationVisitor.visitAnnotation(str, typeDescription.getDescriptor()), (AnnotationDescription) obj, AnnotationValueFilter.Default.APPEND_DEFAULTS);
                return;
            }
            if (typeDescription.isEnum()) {
                annotationVisitor.visitEnum(str, typeDescription.getDescriptor(), ((EnumerationDescription) obj).getValue());
            } else if (typeDescription.represents(Class.class)) {
                annotationVisitor.visit(str, Type.getType(((TypeDescription) obj).getDescriptor()));
            } else {
                annotationVisitor.visit(str, obj);
            }
        }

        @Override // net.bytebuddy.implementation.attribute.AnnotationAppender
        public AnnotationAppender append(AnnotationDescription annotationDescription, AnnotationValueFilter annotationValueFilter) {
            switch (AnonymousClass1.f4107a[annotationDescription.getRetention().ordinal()]) {
                case 1:
                    doAppend(annotationDescription, true, annotationValueFilter);
                    break;
                case 2:
                    doAppend(annotationDescription, false, annotationValueFilter);
                    break;
                case 3:
                    break;
                default:
                    throw new IllegalStateException("Unexpected retention policy: " + annotationDescription.getRetention());
            }
            return this;
        }

        private void doAppend(AnnotationDescription annotationDescription, boolean z, AnnotationValueFilter annotationValueFilter) {
            AnnotationVisitor visit = this.target.visit(annotationDescription.getAnnotationType().getDescriptor(), z);
            if (visit != null) {
                handle(visit, annotationDescription, annotationValueFilter);
            }
        }

        @Override // net.bytebuddy.implementation.attribute.AnnotationAppender
        public AnnotationAppender append(AnnotationDescription annotationDescription, AnnotationValueFilter annotationValueFilter, int i, String str) {
            switch (AnonymousClass1.f4107a[annotationDescription.getRetention().ordinal()]) {
                case 1:
                    doAppend(annotationDescription, true, annotationValueFilter, i, str);
                    break;
                case 2:
                    doAppend(annotationDescription, false, annotationValueFilter, i, str);
                    break;
                case 3:
                    break;
                default:
                    throw new IllegalStateException("Unexpected retention policy: " + annotationDescription.getRetention());
            }
            return this;
        }

        private void doAppend(AnnotationDescription annotationDescription, boolean z, AnnotationValueFilter annotationValueFilter, int i, String str) {
            AnnotationVisitor visit = this.target.visit(annotationDescription.getAnnotationType().getDescriptor(), z, i, str);
            if (visit != null) {
                handle(visit, annotationDescription, annotationValueFilter);
            }
        }
    }

    /* renamed from: net.bytebuddy.implementation.attribute.AnnotationAppender$1, reason: invalid class name */
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/attribute/AnnotationAppender$1.class */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f4107a = new int[RetentionPolicy.values().length];

        static {
            try {
                f4107a[RetentionPolicy.RUNTIME.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f4107a[RetentionPolicy.CLASS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f4107a[RetentionPolicy.SOURCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/attribute/AnnotationAppender$ForTypeAnnotations.class */
    public static class ForTypeAnnotations implements TypeDescription.Generic.Visitor<AnnotationAppender> {
        public static final boolean VARIABLE_ON_TYPE = true;
        public static final boolean VARIABLE_ON_INVOKEABLE = false;
        private static final String EMPTY_TYPE_PATH = "";
        private static final char COMPONENT_TYPE_PATH = '[';
        private static final char WILDCARD_TYPE_PATH = '*';
        private static final char INNER_CLASS_PATH = '.';
        private static final char INDEXED_TYPE_DELIMITER = ';';
        private static final int SUPER_CLASS_INDEX = -1;
        private final AnnotationAppender annotationAppender;
        private final AnnotationValueFilter annotationValueFilter;
        private final int typeReference;
        private final String typePath;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.typeReference == ((ForTypeAnnotations) obj).typeReference && this.typePath.equals(((ForTypeAnnotations) obj).typePath) && this.annotationAppender.equals(((ForTypeAnnotations) obj).annotationAppender) && this.annotationValueFilter.equals(((ForTypeAnnotations) obj).annotationValueFilter);
        }

        public int hashCode() {
            return (((((((getClass().hashCode() * 31) + this.annotationAppender.hashCode()) * 31) + this.annotationValueFilter.hashCode()) * 31) + this.typeReference) * 31) + this.typePath.hashCode();
        }

        protected ForTypeAnnotations(AnnotationAppender annotationAppender, AnnotationValueFilter annotationValueFilter, TypeReference typeReference) {
            this(annotationAppender, annotationValueFilter, typeReference.getValue(), "");
        }

        protected ForTypeAnnotations(AnnotationAppender annotationAppender, AnnotationValueFilter annotationValueFilter, int i, String str) {
            this.annotationAppender = annotationAppender;
            this.annotationValueFilter = annotationValueFilter;
            this.typeReference = i;
            this.typePath = str;
        }

        public static TypeDescription.Generic.Visitor<AnnotationAppender> ofSuperClass(AnnotationAppender annotationAppender, AnnotationValueFilter annotationValueFilter) {
            return new ForTypeAnnotations(annotationAppender, annotationValueFilter, TypeReference.newSuperTypeReference(-1));
        }

        public static TypeDescription.Generic.Visitor<AnnotationAppender> ofInterfaceType(AnnotationAppender annotationAppender, AnnotationValueFilter annotationValueFilter, int i) {
            return new ForTypeAnnotations(annotationAppender, annotationValueFilter, TypeReference.newSuperTypeReference(i));
        }

        public static TypeDescription.Generic.Visitor<AnnotationAppender> ofFieldType(AnnotationAppender annotationAppender, AnnotationValueFilter annotationValueFilter) {
            return new ForTypeAnnotations(annotationAppender, annotationValueFilter, TypeReference.newTypeReference(19));
        }

        public static TypeDescription.Generic.Visitor<AnnotationAppender> ofMethodReturnType(AnnotationAppender annotationAppender, AnnotationValueFilter annotationValueFilter) {
            return new ForTypeAnnotations(annotationAppender, annotationValueFilter, TypeReference.newTypeReference(20));
        }

        public static TypeDescription.Generic.Visitor<AnnotationAppender> ofMethodParameterType(AnnotationAppender annotationAppender, AnnotationValueFilter annotationValueFilter, int i) {
            return new ForTypeAnnotations(annotationAppender, annotationValueFilter, TypeReference.newFormalParameterReference(i));
        }

        public static TypeDescription.Generic.Visitor<AnnotationAppender> ofExceptionType(AnnotationAppender annotationAppender, AnnotationValueFilter annotationValueFilter, int i) {
            return new ForTypeAnnotations(annotationAppender, annotationValueFilter, TypeReference.newExceptionReference(i));
        }

        public static TypeDescription.Generic.Visitor<AnnotationAppender> ofReceiverType(AnnotationAppender annotationAppender, AnnotationValueFilter annotationValueFilter) {
            return new ForTypeAnnotations(annotationAppender, annotationValueFilter, TypeReference.newTypeReference(21));
        }

        public static AnnotationAppender ofTypeVariable(AnnotationAppender annotationAppender, AnnotationValueFilter annotationValueFilter, boolean z, List<? extends TypeDescription.Generic> list) {
            return ofTypeVariable(annotationAppender, annotationValueFilter, z, 0, list);
        }

        public static AnnotationAppender ofTypeVariable(AnnotationAppender annotationAppender, AnnotationValueFilter annotationValueFilter, boolean z, int i, List<? extends TypeDescription.Generic> list) {
            int i2;
            int i3;
            int i4 = i;
            if (z) {
                i2 = 0;
                i3 = 17;
            } else {
                i2 = 1;
                i3 = 18;
            }
            for (TypeDescription.Generic generic : list.subList(i, list.size())) {
                int value = TypeReference.newTypeParameterReference(i2, i4).getValue();
                Iterator it = generic.getDeclaredAnnotations().iterator();
                while (it.hasNext()) {
                    annotationAppender = annotationAppender.append((AnnotationDescription) it.next(), annotationValueFilter, value, "");
                }
                int i5 = (((TypeDescription.Generic) generic.getUpperBounds().get(0)).getSort().isTypeVariable() || !((TypeDescription.Generic) generic.getUpperBounds().get(0)).isInterface()) ? 0 : 1;
                Iterator it2 = generic.getUpperBounds().iterator();
                while (it2.hasNext()) {
                    int i6 = i5;
                    i5++;
                    annotationAppender = (AnnotationAppender) ((TypeDescription.Generic) it2.next()).accept(new ForTypeAnnotations(annotationAppender, annotationValueFilter, TypeReference.newTypeParameterBoundReference(i3, i4, i6)));
                }
                i4++;
            }
            return annotationAppender;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
        @SuppressFBWarnings(value = {"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"}, justification = "Assuming component type for array type.")
        public AnnotationAppender onGenericArray(TypeDescription.Generic generic) {
            return (AnnotationAppender) generic.getComponentType().accept(new ForTypeAnnotations(apply(generic, this.typePath), this.annotationValueFilter, this.typeReference, this.typePath + '['));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
        public AnnotationAppender onWildcard(TypeDescription.Generic generic) {
            TypeDescription.Generic only;
            TypeList.Generic lowerBounds = generic.getLowerBounds();
            if (lowerBounds.isEmpty()) {
                only = generic.getUpperBounds().getOnly();
            } else {
                only = lowerBounds.getOnly();
            }
            return (AnnotationAppender) only.accept(new ForTypeAnnotations(apply(generic, this.typePath), this.annotationValueFilter, this.typeReference, this.typePath + '*'));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
        public AnnotationAppender onParameterizedType(TypeDescription.Generic generic) {
            StringBuilder sb = new StringBuilder(this.typePath);
            for (int i = 0; i < generic.asErasure().getInnerClassCount(); i++) {
                sb = sb.append('.');
            }
            AnnotationAppender apply = apply(generic, sb.toString());
            TypeDescription.Generic ownerType = generic.getOwnerType();
            if (ownerType != null) {
                apply = (AnnotationAppender) ownerType.accept(new ForTypeAnnotations(apply, this.annotationValueFilter, this.typeReference, this.typePath));
            }
            int i2 = 0;
            Iterator it = generic.getTypeArguments().iterator();
            while (it.hasNext()) {
                int i3 = i2;
                i2++;
                apply = (AnnotationAppender) ((TypeDescription.Generic) it.next()).accept(new ForTypeAnnotations(apply, this.annotationValueFilter, this.typeReference, sb.toString() + i3 + ';'));
            }
            return apply;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
        public AnnotationAppender onTypeVariable(TypeDescription.Generic generic) {
            return apply(generic, this.typePath);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // net.bytebuddy.description.type.TypeDescription.Generic.Visitor
        public AnnotationAppender onNonGenericType(TypeDescription.Generic generic) {
            StringBuilder sb = new StringBuilder(this.typePath);
            for (int i = 0; i < generic.asErasure().getInnerClassCount(); i++) {
                sb = sb.append('.');
            }
            AnnotationAppender apply = apply(generic, sb.toString());
            TypeDescription.Generic componentType = generic.getComponentType();
            if (componentType != null) {
                apply = (AnnotationAppender) componentType.accept(new ForTypeAnnotations(apply, this.annotationValueFilter, this.typeReference, this.typePath + '['));
            }
            return apply;
        }

        private AnnotationAppender apply(TypeDescription.Generic generic, String str) {
            AnnotationAppender annotationAppender = this.annotationAppender;
            Iterator it = generic.getDeclaredAnnotations().iterator();
            while (it.hasNext()) {
                annotationAppender = annotationAppender.append((AnnotationDescription) it.next(), this.annotationValueFilter, this.typeReference, str);
            }
            return annotationAppender;
        }
    }
}
