package net.bytebuddy.dynamic.scaffold;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedExceptionAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.asm.AsmVisitorWrapper;
import net.bytebuddy.build.AccessControllerPlugin;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.annotation.AnnotationList;
import net.bytebuddy.description.annotation.AnnotationValue;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.method.ParameterList;
import net.bytebuddy.description.modifier.ModifierContributor;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.description.type.RecordComponentDescription;
import net.bytebuddy.description.type.RecordComponentList;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.TypeResolutionStrategy;
import net.bytebuddy.dynamic.scaffold.MethodRegistry;
import net.bytebuddy.dynamic.scaffold.TypeInitializer;
import net.bytebuddy.dynamic.scaffold.inline.MethodRebaseResolver;
import net.bytebuddy.dynamic.scaffold.inline.RebaseImplementationTarget;
import net.bytebuddy.dynamic.scaffold.subclass.SubclassImplementationTarget;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.LoadedTypeInitializer;
import net.bytebuddy.implementation.attribute.AnnotationAppender;
import net.bytebuddy.implementation.attribute.AnnotationRetention;
import net.bytebuddy.implementation.attribute.AnnotationValueFilter;
import net.bytebuddy.implementation.attribute.FieldAttributeAppender;
import net.bytebuddy.implementation.attribute.MethodAttributeAppender;
import net.bytebuddy.implementation.attribute.RecordComponentAttributeAppender;
import net.bytebuddy.implementation.attribute.TypeAttributeAppender;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;
import net.bytebuddy.implementation.bytecode.constant.DefaultValue;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.implementation.bytecode.member.MethodReturn;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import net.bytebuddy.jar.asm.AnnotationVisitor;
import net.bytebuddy.jar.asm.ClassReader;
import net.bytebuddy.jar.asm.ClassVisitor;
import net.bytebuddy.jar.asm.ClassWriter;
import net.bytebuddy.jar.asm.ConstantDynamic;
import net.bytebuddy.jar.asm.FieldVisitor;
import net.bytebuddy.jar.asm.Handle;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import net.bytebuddy.jar.asm.RecordComponentVisitor;
import net.bytebuddy.jar.asm.Type;
import net.bytebuddy.jar.asm.TypePath;
import net.bytebuddy.jar.asm.commons.ClassRemapper;
import net.bytebuddy.jar.asm.commons.Remapper;
import net.bytebuddy.jar.asm.commons.SimpleRemapper;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.pool.TypePool;
import net.bytebuddy.utility.CompoundList;
import net.bytebuddy.utility.OpenedClassReader;
import net.bytebuddy.utility.nullability.AlwaysNull;
import net.bytebuddy.utility.nullability.MaybeNull;
import net.bytebuddy.utility.nullability.UnknownNull;
import net.bytebuddy.utility.privilege.GetSystemPropertyAction;
import net.bytebuddy.utility.visitor.ContextClassVisitor;
import net.bytebuddy.utility.visitor.MetadataAwareClassVisitor;

/* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter.class */
public interface TypeWriter<T> {
    public static final String DUMP_PROPERTY = "net.bytebuddy.dump";

    DynamicType.Unloaded<T> make(TypeResolutionStrategy.Resolved resolved);

    ContextClassVisitor wrap(ClassVisitor classVisitor, int i, int i2);

    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$FieldPool.class */
    public interface FieldPool {
        Record target(FieldDescription fieldDescription);

        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$FieldPool$Record.class */
        public interface Record {
            boolean isImplicit();

            FieldDescription getField();

            FieldAttributeAppender getFieldAppender();

            @MaybeNull
            Object resolveDefault(@MaybeNull Object obj);

            void apply(ClassVisitor classVisitor, AnnotationValueFilter.Factory factory);

            void apply(FieldVisitor fieldVisitor, AnnotationValueFilter.Factory factory);

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$FieldPool$Record$ForImplicitField.class */
            public static class ForImplicitField implements Record {
                private final FieldDescription fieldDescription;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.fieldDescription.equals(((ForImplicitField) obj).fieldDescription);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.fieldDescription.hashCode();
                }

                public ForImplicitField(FieldDescription fieldDescription) {
                    this.fieldDescription = fieldDescription;
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.FieldPool.Record
                public boolean isImplicit() {
                    return true;
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.FieldPool.Record
                public FieldDescription getField() {
                    return this.fieldDescription;
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.FieldPool.Record
                public FieldAttributeAppender getFieldAppender() {
                    throw new IllegalStateException("An implicit field record does not expose a field appender: " + this);
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.FieldPool.Record
                public Object resolveDefault(@MaybeNull Object obj) {
                    throw new IllegalStateException("An implicit field record does not expose a default value: " + this);
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.FieldPool.Record
                public void apply(ClassVisitor classVisitor, AnnotationValueFilter.Factory factory) {
                    FieldVisitor visitField = classVisitor.visitField(this.fieldDescription.getActualModifiers(), this.fieldDescription.getInternalName(), this.fieldDescription.getDescriptor(), this.fieldDescription.getGenericSignature(), FieldDescription.NO_DEFAULT_VALUE);
                    if (visitField != null) {
                        FieldAttributeAppender.ForInstrumentedField.INSTANCE.apply(visitField, this.fieldDescription, factory.on(this.fieldDescription));
                        visitField.visitEnd();
                    }
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.FieldPool.Record
                public void apply(FieldVisitor fieldVisitor, AnnotationValueFilter.Factory factory) {
                    throw new IllegalStateException("An implicit field record is not intended for partial application: " + this);
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$FieldPool$Record$ForExplicitField.class */
            public static class ForExplicitField implements Record {
                private final FieldAttributeAppender attributeAppender;

                @MaybeNull
                @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.REVERSE_NULLABILITY)
                private final Object defaultValue;
                private final FieldDescription fieldDescription;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass() || !this.attributeAppender.equals(((ForExplicitField) obj).attributeAppender)) {
                        return false;
                    }
                    Object obj2 = this.defaultValue;
                    Object obj3 = ((ForExplicitField) obj).defaultValue;
                    if (obj3 != null) {
                        if (obj2 == null || !obj2.equals(obj3)) {
                            return false;
                        }
                    } else if (obj2 != null) {
                        return false;
                    }
                    return this.fieldDescription.equals(((ForExplicitField) obj).fieldDescription);
                }

                public int hashCode() {
                    int hashCode = ((getClass().hashCode() * 31) + this.attributeAppender.hashCode()) * 31;
                    Object obj = this.defaultValue;
                    if (obj != null) {
                        hashCode += obj.hashCode();
                    }
                    return (hashCode * 31) + this.fieldDescription.hashCode();
                }

                public ForExplicitField(FieldAttributeAppender fieldAttributeAppender, @MaybeNull Object obj, FieldDescription fieldDescription) {
                    this.attributeAppender = fieldAttributeAppender;
                    this.defaultValue = obj;
                    this.fieldDescription = fieldDescription;
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.FieldPool.Record
                public boolean isImplicit() {
                    return false;
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.FieldPool.Record
                public FieldDescription getField() {
                    return this.fieldDescription;
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.FieldPool.Record
                public FieldAttributeAppender getFieldAppender() {
                    return this.attributeAppender;
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.FieldPool.Record
                @MaybeNull
                public Object resolveDefault(@MaybeNull Object obj) {
                    return this.defaultValue == null ? obj : this.defaultValue;
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.FieldPool.Record
                public void apply(ClassVisitor classVisitor, AnnotationValueFilter.Factory factory) {
                    FieldVisitor visitField = classVisitor.visitField(this.fieldDescription.getActualModifiers(), this.fieldDescription.getInternalName(), this.fieldDescription.getDescriptor(), this.fieldDescription.getGenericSignature(), resolveDefault(FieldDescription.NO_DEFAULT_VALUE));
                    if (visitField != null) {
                        this.attributeAppender.apply(visitField, this.fieldDescription, factory.on(this.fieldDescription));
                        visitField.visitEnd();
                    }
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.FieldPool.Record
                public void apply(FieldVisitor fieldVisitor, AnnotationValueFilter.Factory factory) {
                    this.attributeAppender.apply(fieldVisitor, this.fieldDescription, factory.on(this.fieldDescription));
                }
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$FieldPool$Disabled.class */
        public enum Disabled implements FieldPool {
            INSTANCE;

            @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.FieldPool
            public final Record target(FieldDescription fieldDescription) {
                throw new IllegalStateException("Cannot look up field from disabled pool");
            }
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$MethodPool.class */
    public interface MethodPool {
        Record target(MethodDescription methodDescription);

        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$MethodPool$Record.class */
        public interface Record {
            Sort getSort();

            MethodDescription getMethod();

            Visibility getVisibility();

            Record prepend(ByteCodeAppender byteCodeAppender);

            void apply(ClassVisitor classVisitor, Implementation.Context context, AnnotationValueFilter.Factory factory);

            void applyHead(MethodVisitor methodVisitor);

            void applyBody(MethodVisitor methodVisitor, Implementation.Context context, AnnotationValueFilter.Factory factory);

            void applyAttributes(MethodVisitor methodVisitor, AnnotationValueFilter.Factory factory);

            ByteCodeAppender.Size applyCode(MethodVisitor methodVisitor, Implementation.Context context);

            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$MethodPool$Record$Sort.class */
            public enum Sort {
                SKIPPED(false, false),
                DEFINED(true, false),
                IMPLEMENTED(true, true);

                private final boolean define;
                private final boolean implement;

                Sort(boolean z, boolean z2) {
                    this.define = z;
                    this.implement = z2;
                }

                public final boolean isDefined() {
                    return this.define;
                }

                public final boolean isImplemented() {
                    return this.implement;
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$MethodPool$Record$ForNonImplementedMethod.class */
            public static class ForNonImplementedMethod implements Record {
                private final MethodDescription methodDescription;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.methodDescription.equals(((ForNonImplementedMethod) obj).methodDescription);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.methodDescription.hashCode();
                }

                public ForNonImplementedMethod(MethodDescription methodDescription) {
                    this.methodDescription = methodDescription;
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public void apply(ClassVisitor classVisitor, Implementation.Context context, AnnotationValueFilter.Factory factory) {
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public void applyBody(MethodVisitor methodVisitor, Implementation.Context context, AnnotationValueFilter.Factory factory) {
                    throw new IllegalStateException("Cannot apply body for non-implemented method on " + this.methodDescription);
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public void applyAttributes(MethodVisitor methodVisitor, AnnotationValueFilter.Factory factory) {
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public ByteCodeAppender.Size applyCode(MethodVisitor methodVisitor, Implementation.Context context) {
                    throw new IllegalStateException("Cannot apply code for non-implemented method on " + this.methodDescription);
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public void applyHead(MethodVisitor methodVisitor) {
                    throw new IllegalStateException("Cannot apply head for non-implemented method on " + this.methodDescription);
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public MethodDescription getMethod() {
                    return this.methodDescription;
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public Visibility getVisibility() {
                    return this.methodDescription.getVisibility();
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public Sort getSort() {
                    return Sort.SKIPPED;
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public Record prepend(ByteCodeAppender byteCodeAppender) {
                    return new ForDefinedMethod.WithBody(this.methodDescription, new ByteCodeAppender.Compound(byteCodeAppender, new ByteCodeAppender.Simple(DefaultValue.of(this.methodDescription.getReturnType()), MethodReturn.of(this.methodDescription.getReturnType()))));
                }
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$MethodPool$Record$ForDefinedMethod.class */
            public static abstract class ForDefinedMethod implements Record {
                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public void apply(ClassVisitor classVisitor, Implementation.Context context, AnnotationValueFilter.Factory factory) {
                    MethodVisitor visitMethod = classVisitor.visitMethod(getMethod().getActualModifiers(getSort().isImplemented(), getVisibility()), getMethod().getInternalName(), getMethod().getDescriptor(), getMethod().getGenericSignature(), getMethod().getExceptionTypes().asErasures().toInternalNames());
                    if (visitMethod != null) {
                        ParameterList<?> parameters = getMethod().getParameters();
                        if (parameters.hasExplicitMetaData()) {
                            Iterator it = parameters.iterator();
                            while (it.hasNext()) {
                                ParameterDescription parameterDescription = (ParameterDescription) it.next();
                                visitMethod.visitParameter(parameterDescription.getName(), parameterDescription.getModifiers());
                            }
                        }
                        applyHead(visitMethod);
                        applyBody(visitMethod, context, factory);
                        visitMethod.visitEnd();
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$MethodPool$Record$ForDefinedMethod$WithBody.class */
                public static class WithBody extends ForDefinedMethod {
                    private final MethodDescription methodDescription;
                    private final ByteCodeAppender byteCodeAppender;
                    private final MethodAttributeAppender methodAttributeAppender;
                    private final Visibility visibility;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.visibility.equals(((WithBody) obj).visibility) && this.methodDescription.equals(((WithBody) obj).methodDescription) && this.byteCodeAppender.equals(((WithBody) obj).byteCodeAppender) && this.methodAttributeAppender.equals(((WithBody) obj).methodAttributeAppender);
                    }

                    public int hashCode() {
                        return (((((((getClass().hashCode() * 31) + this.methodDescription.hashCode()) * 31) + this.byteCodeAppender.hashCode()) * 31) + this.methodAttributeAppender.hashCode()) * 31) + this.visibility.hashCode();
                    }

                    public WithBody(MethodDescription methodDescription, ByteCodeAppender byteCodeAppender) {
                        this(methodDescription, byteCodeAppender, MethodAttributeAppender.NoOp.INSTANCE, methodDescription.getVisibility());
                    }

                    public WithBody(MethodDescription methodDescription, ByteCodeAppender byteCodeAppender, MethodAttributeAppender methodAttributeAppender, Visibility visibility) {
                        this.methodDescription = methodDescription;
                        this.byteCodeAppender = byteCodeAppender;
                        this.methodAttributeAppender = methodAttributeAppender;
                        this.visibility = visibility;
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public MethodDescription getMethod() {
                        return this.methodDescription;
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public Sort getSort() {
                        return Sort.IMPLEMENTED;
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public Visibility getVisibility() {
                        return this.visibility;
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public void applyHead(MethodVisitor methodVisitor) {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public void applyBody(MethodVisitor methodVisitor, Implementation.Context context, AnnotationValueFilter.Factory factory) {
                        applyAttributes(methodVisitor, factory);
                        methodVisitor.visitCode();
                        ByteCodeAppender.Size applyCode = applyCode(methodVisitor, context);
                        methodVisitor.visitMaxs(applyCode.getOperandStackSize(), applyCode.getLocalVariableSize());
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public void applyAttributes(MethodVisitor methodVisitor, AnnotationValueFilter.Factory factory) {
                        this.methodAttributeAppender.apply(methodVisitor, this.methodDescription, factory.on(this.methodDescription));
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public ByteCodeAppender.Size applyCode(MethodVisitor methodVisitor, Implementation.Context context) {
                        return this.byteCodeAppender.apply(methodVisitor, context, this.methodDescription);
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public Record prepend(ByteCodeAppender byteCodeAppender) {
                        return new WithBody(this.methodDescription, new ByteCodeAppender.Compound(byteCodeAppender, this.byteCodeAppender), this.methodAttributeAppender, this.visibility);
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$MethodPool$Record$ForDefinedMethod$WithoutBody.class */
                public static class WithoutBody extends ForDefinedMethod {
                    private final MethodDescription methodDescription;
                    private final MethodAttributeAppender methodAttributeAppender;
                    private final Visibility visibility;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.visibility.equals(((WithoutBody) obj).visibility) && this.methodDescription.equals(((WithoutBody) obj).methodDescription) && this.methodAttributeAppender.equals(((WithoutBody) obj).methodAttributeAppender);
                    }

                    public int hashCode() {
                        return (((((getClass().hashCode() * 31) + this.methodDescription.hashCode()) * 31) + this.methodAttributeAppender.hashCode()) * 31) + this.visibility.hashCode();
                    }

                    public WithoutBody(MethodDescription methodDescription, MethodAttributeAppender methodAttributeAppender, Visibility visibility) {
                        this.methodDescription = methodDescription;
                        this.methodAttributeAppender = methodAttributeAppender;
                        this.visibility = visibility;
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public MethodDescription getMethod() {
                        return this.methodDescription;
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public Sort getSort() {
                        return Sort.DEFINED;
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public Visibility getVisibility() {
                        return this.visibility;
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public void applyHead(MethodVisitor methodVisitor) {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public void applyBody(MethodVisitor methodVisitor, Implementation.Context context, AnnotationValueFilter.Factory factory) {
                        applyAttributes(methodVisitor, factory);
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public void applyAttributes(MethodVisitor methodVisitor, AnnotationValueFilter.Factory factory) {
                        this.methodAttributeAppender.apply(methodVisitor, this.methodDescription, factory.on(this.methodDescription));
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public ByteCodeAppender.Size applyCode(MethodVisitor methodVisitor, Implementation.Context context) {
                        throw new IllegalStateException("Cannot apply code for abstract method on " + this.methodDescription);
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public Record prepend(ByteCodeAppender byteCodeAppender) {
                        throw new IllegalStateException("Cannot prepend code for abstract method on " + this.methodDescription);
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$MethodPool$Record$ForDefinedMethod$WithAnnotationDefaultValue.class */
                public static class WithAnnotationDefaultValue extends ForDefinedMethod {
                    private final MethodDescription methodDescription;
                    private final AnnotationValue<?, ?> annotationValue;
                    private final MethodAttributeAppender methodAttributeAppender;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.methodDescription.equals(((WithAnnotationDefaultValue) obj).methodDescription) && this.annotationValue.equals(((WithAnnotationDefaultValue) obj).annotationValue) && this.methodAttributeAppender.equals(((WithAnnotationDefaultValue) obj).methodAttributeAppender);
                    }

                    public int hashCode() {
                        return (((((getClass().hashCode() * 31) + this.methodDescription.hashCode()) * 31) + this.annotationValue.hashCode()) * 31) + this.methodAttributeAppender.hashCode();
                    }

                    public WithAnnotationDefaultValue(MethodDescription methodDescription, AnnotationValue<?, ?> annotationValue, MethodAttributeAppender methodAttributeAppender) {
                        this.methodDescription = methodDescription;
                        this.annotationValue = annotationValue;
                        this.methodAttributeAppender = methodAttributeAppender;
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public MethodDescription getMethod() {
                        return this.methodDescription;
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public Sort getSort() {
                        return Sort.DEFINED;
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public Visibility getVisibility() {
                        return this.methodDescription.getVisibility();
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public void applyHead(MethodVisitor methodVisitor) {
                        if (!this.methodDescription.isDefaultValue(this.annotationValue)) {
                            throw new IllegalStateException("Cannot set " + this.annotationValue + " as default for " + this.methodDescription);
                        }
                        AnnotationVisitor visitAnnotationDefault = methodVisitor.visitAnnotationDefault();
                        AnnotationAppender.Default.apply(visitAnnotationDefault, this.methodDescription.getReturnType().asErasure(), AnnotationAppender.NO_NAME, this.annotationValue.resolve());
                        visitAnnotationDefault.visitEnd();
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public void applyBody(MethodVisitor methodVisitor, Implementation.Context context, AnnotationValueFilter.Factory factory) {
                        this.methodAttributeAppender.apply(methodVisitor, this.methodDescription, factory.on(this.methodDescription));
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public void applyAttributes(MethodVisitor methodVisitor, AnnotationValueFilter.Factory factory) {
                        throw new IllegalStateException("Cannot apply attributes for default value on " + this.methodDescription);
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public ByteCodeAppender.Size applyCode(MethodVisitor methodVisitor, Implementation.Context context) {
                        throw new IllegalStateException("Cannot apply code for default value on " + this.methodDescription);
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public Record prepend(ByteCodeAppender byteCodeAppender) {
                        throw new IllegalStateException("Cannot prepend code for default value on " + this.methodDescription);
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$MethodPool$Record$ForDefinedMethod$OfVisibilityBridge.class */
                public static class OfVisibilityBridge extends ForDefinedMethod implements ByteCodeAppender {
                    private final MethodDescription visibilityBridge;
                    private final MethodDescription bridgeTarget;
                    private final TypeDescription bridgeType;
                    private final MethodAttributeAppender attributeAppender;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.visibilityBridge.equals(((OfVisibilityBridge) obj).visibilityBridge) && this.bridgeTarget.equals(((OfVisibilityBridge) obj).bridgeTarget) && this.bridgeType.equals(((OfVisibilityBridge) obj).bridgeType) && this.attributeAppender.equals(((OfVisibilityBridge) obj).attributeAppender);
                    }

                    public int hashCode() {
                        return (((((((getClass().hashCode() * 31) + this.visibilityBridge.hashCode()) * 31) + this.bridgeTarget.hashCode()) * 31) + this.bridgeType.hashCode()) * 31) + this.attributeAppender.hashCode();
                    }

                    protected OfVisibilityBridge(MethodDescription methodDescription, MethodDescription methodDescription2, TypeDescription typeDescription, MethodAttributeAppender methodAttributeAppender) {
                        this.visibilityBridge = methodDescription;
                        this.bridgeTarget = methodDescription2;
                        this.bridgeType = typeDescription;
                        this.attributeAppender = methodAttributeAppender;
                    }

                    public static Record of(TypeDescription typeDescription, MethodDescription methodDescription, MethodAttributeAppender methodAttributeAppender) {
                        TypeDefinition typeDefinition = null;
                        if (methodDescription.isDefaultMethod()) {
                            TypeDescription asErasure = methodDescription.getDeclaringType().asErasure();
                            for (TypeDefinition typeDefinition2 : typeDescription.getInterfaces().asErasures().filter(ElementMatchers.isSubTypeOf(asErasure))) {
                                if (typeDefinition == null || asErasure.isAssignableTo(typeDefinition.asErasure())) {
                                    typeDefinition = typeDefinition2;
                                }
                            }
                        }
                        if (typeDefinition == null) {
                            TypeDefinition superClass = typeDescription.getSuperClass();
                            typeDefinition = superClass;
                            if (superClass == null) {
                                typeDefinition = TypeDescription.ForLoadedType.of(Object.class);
                            }
                        }
                        return new OfVisibilityBridge(new VisibilityBridge(typeDescription, methodDescription), methodDescription, typeDefinition.asErasure(), methodAttributeAppender);
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public MethodDescription getMethod() {
                        return this.visibilityBridge;
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public Sort getSort() {
                        return Sort.IMPLEMENTED;
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public Visibility getVisibility() {
                        return this.bridgeTarget.getVisibility();
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public Record prepend(ByteCodeAppender byteCodeAppender) {
                        return new WithBody(this.visibilityBridge, new ByteCodeAppender.Compound(this, byteCodeAppender), this.attributeAppender, this.bridgeTarget.getVisibility());
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public void applyHead(MethodVisitor methodVisitor) {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public void applyBody(MethodVisitor methodVisitor, Implementation.Context context, AnnotationValueFilter.Factory factory) {
                        applyAttributes(methodVisitor, factory);
                        methodVisitor.visitCode();
                        ByteCodeAppender.Size applyCode = applyCode(methodVisitor, context);
                        methodVisitor.visitMaxs(applyCode.getOperandStackSize(), applyCode.getLocalVariableSize());
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public void applyAttributes(MethodVisitor methodVisitor, AnnotationValueFilter.Factory factory) {
                        this.attributeAppender.apply(methodVisitor, this.visibilityBridge, factory.on(this.visibilityBridge));
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                    public ByteCodeAppender.Size applyCode(MethodVisitor methodVisitor, Implementation.Context context) {
                        return apply(methodVisitor, context, this.visibilityBridge);
                    }

                    @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
                    public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                        return new ByteCodeAppender.Simple(MethodVariableAccess.allArgumentsOf(methodDescription).prependThisReference(), MethodInvocation.invoke(this.bridgeTarget).special(this.bridgeType), MethodReturn.of(methodDescription.getReturnType())).apply(methodVisitor, context, methodDescription);
                    }

                    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$MethodPool$Record$ForDefinedMethod$OfVisibilityBridge$VisibilityBridge.class */
                    protected static class VisibilityBridge extends MethodDescription.InDefinedShape.AbstractBase {
                        private final TypeDescription instrumentedType;
                        private final MethodDescription bridgeTarget;

                        protected VisibilityBridge(TypeDescription typeDescription, MethodDescription methodDescription) {
                            this.instrumentedType = typeDescription;
                            this.bridgeTarget = methodDescription;
                        }

                        @Override // net.bytebuddy.description.DeclaredByType
                        public TypeDescription getDeclaringType() {
                            return this.instrumentedType;
                        }

                        @Override // net.bytebuddy.description.method.MethodDescription, net.bytebuddy.description.method.MethodDescription.InDefinedShape
                        public ParameterList<ParameterDescription.InDefinedShape> getParameters() {
                            return new ParameterList.Explicit.ForTypes(this, this.bridgeTarget.getParameters().asTypeList().asRawTypes());
                        }

                        @Override // net.bytebuddy.description.method.MethodDescription
                        public TypeDescription.Generic getReturnType() {
                            return this.bridgeTarget.getReturnType().asRawType();
                        }

                        @Override // net.bytebuddy.description.method.MethodDescription
                        public TypeList.Generic getExceptionTypes() {
                            return this.bridgeTarget.getExceptionTypes().asRawTypes();
                        }

                        @Override // net.bytebuddy.description.method.MethodDescription
                        @MaybeNull
                        public AnnotationValue<?, ?> getDefaultValue() {
                            return AnnotationValue.UNDEFINED;
                        }

                        @Override // net.bytebuddy.description.TypeVariableSource
                        public TypeList.Generic getTypeVariables() {
                            return new TypeList.Generic.Empty();
                        }

                        @Override // net.bytebuddy.description.annotation.AnnotationSource
                        public AnnotationList getDeclaredAnnotations() {
                            return this.bridgeTarget.getDeclaredAnnotations();
                        }

                        @Override // net.bytebuddy.description.ModifierReviewable
                        public int getModifiers() {
                            return (this.bridgeTarget.getModifiers() | 4096 | 64) & (-257);
                        }

                        @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
                        public String getInternalName() {
                            return this.bridgeTarget.getName();
                        }
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$MethodPool$Record$AccessBridgeWrapper.class */
            public static class AccessBridgeWrapper implements Record {
                private final Record delegate;
                private final TypeDescription instrumentedType;
                private final MethodDescription bridgeTarget;
                private final Set<MethodDescription.TypeToken> bridgeTypes;
                private final MethodAttributeAppender attributeAppender;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.delegate.equals(((AccessBridgeWrapper) obj).delegate) && this.instrumentedType.equals(((AccessBridgeWrapper) obj).instrumentedType) && this.bridgeTarget.equals(((AccessBridgeWrapper) obj).bridgeTarget) && this.bridgeTypes.equals(((AccessBridgeWrapper) obj).bridgeTypes) && this.attributeAppender.equals(((AccessBridgeWrapper) obj).attributeAppender);
                }

                public int hashCode() {
                    return (((((((((getClass().hashCode() * 31) + this.delegate.hashCode()) * 31) + this.instrumentedType.hashCode()) * 31) + this.bridgeTarget.hashCode()) * 31) + this.bridgeTypes.hashCode()) * 31) + this.attributeAppender.hashCode();
                }

                protected AccessBridgeWrapper(Record record, TypeDescription typeDescription, MethodDescription methodDescription, Set<MethodDescription.TypeToken> set, MethodAttributeAppender methodAttributeAppender) {
                    this.delegate = record;
                    this.instrumentedType = typeDescription;
                    this.bridgeTarget = methodDescription;
                    this.bridgeTypes = set;
                    this.attributeAppender = methodAttributeAppender;
                }

                public static Record of(Record record, TypeDescription typeDescription, MethodDescription methodDescription, Set<MethodDescription.TypeToken> set, MethodAttributeAppender methodAttributeAppender) {
                    HashSet hashSet = new HashSet();
                    for (MethodDescription.TypeToken typeToken : set) {
                        if (methodDescription.isBridgeCompatible(typeToken)) {
                            hashSet.add(typeToken);
                        }
                    }
                    return (hashSet.isEmpty() || (typeDescription.isInterface() && !record.getSort().isImplemented())) ? record : new AccessBridgeWrapper(record, typeDescription, methodDescription, hashSet, methodAttributeAppender);
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public Sort getSort() {
                    return this.delegate.getSort();
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public MethodDescription getMethod() {
                    return this.bridgeTarget;
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public Visibility getVisibility() {
                    return this.delegate.getVisibility();
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public Record prepend(ByteCodeAppender byteCodeAppender) {
                    return new AccessBridgeWrapper(this.delegate.prepend(byteCodeAppender), this.instrumentedType, this.bridgeTarget, this.bridgeTypes, this.attributeAppender);
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public void apply(ClassVisitor classVisitor, Implementation.Context context, AnnotationValueFilter.Factory factory) {
                    this.delegate.apply(classVisitor, context, factory);
                    Iterator<MethodDescription.TypeToken> it = this.bridgeTypes.iterator();
                    while (it.hasNext()) {
                        AccessorBridge accessorBridge = new AccessorBridge(this.bridgeTarget, it.next(), this.instrumentedType);
                        BridgeTarget bridgeTarget = new BridgeTarget(this.bridgeTarget, this.instrumentedType);
                        MethodVisitor visitMethod = classVisitor.visitMethod(accessorBridge.getActualModifiers(true, getVisibility()), accessorBridge.getInternalName(), accessorBridge.getDescriptor(), MethodDescription.NON_GENERIC_SIGNATURE, accessorBridge.getExceptionTypes().asErasures().toInternalNames());
                        if (visitMethod != null) {
                            this.attributeAppender.apply(visitMethod, accessorBridge, factory.on(this.instrumentedType));
                            visitMethod.visitCode();
                            StackManipulation[] stackManipulationArr = new StackManipulation[4];
                            stackManipulationArr[0] = MethodVariableAccess.allArgumentsOf(accessorBridge).asBridgeOf(bridgeTarget).prependThisReference();
                            stackManipulationArr[1] = MethodInvocation.invoke((MethodDescription.InDefinedShape) bridgeTarget).virtual(this.instrumentedType);
                            stackManipulationArr[2] = bridgeTarget.getReturnType().asErasure().isAssignableTo(accessorBridge.getReturnType().asErasure()) ? StackManipulation.Trivial.INSTANCE : TypeCasting.to(accessorBridge.getReturnType().asErasure());
                            stackManipulationArr[3] = MethodReturn.of(accessorBridge.getReturnType());
                            ByteCodeAppender.Size apply = new ByteCodeAppender.Simple(stackManipulationArr).apply(visitMethod, context, accessorBridge);
                            visitMethod.visitMaxs(apply.getOperandStackSize(), apply.getLocalVariableSize());
                            visitMethod.visitEnd();
                        }
                    }
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public void applyHead(MethodVisitor methodVisitor) {
                    this.delegate.applyHead(methodVisitor);
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public void applyBody(MethodVisitor methodVisitor, Implementation.Context context, AnnotationValueFilter.Factory factory) {
                    this.delegate.applyBody(methodVisitor, context, factory);
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public void applyAttributes(MethodVisitor methodVisitor, AnnotationValueFilter.Factory factory) {
                    this.delegate.applyAttributes(methodVisitor, factory);
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public ByteCodeAppender.Size applyCode(MethodVisitor methodVisitor, Implementation.Context context) {
                    return this.delegate.applyCode(methodVisitor, context);
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$MethodPool$Record$AccessBridgeWrapper$AccessorBridge.class */
                protected static class AccessorBridge extends MethodDescription.InDefinedShape.AbstractBase {
                    private final MethodDescription bridgeTarget;
                    private final MethodDescription.TypeToken bridgeType;
                    private final TypeDescription instrumentedType;

                    protected AccessorBridge(MethodDescription methodDescription, MethodDescription.TypeToken typeToken, TypeDescription typeDescription) {
                        this.bridgeTarget = methodDescription;
                        this.bridgeType = typeToken;
                        this.instrumentedType = typeDescription;
                    }

                    @Override // net.bytebuddy.description.DeclaredByType
                    public TypeDescription getDeclaringType() {
                        return this.instrumentedType;
                    }

                    @Override // net.bytebuddy.description.method.MethodDescription, net.bytebuddy.description.method.MethodDescription.InDefinedShape
                    public ParameterList<ParameterDescription.InDefinedShape> getParameters() {
                        return new ParameterList.Explicit.ForTypes(this, this.bridgeType.getParameterTypes());
                    }

                    @Override // net.bytebuddy.description.method.MethodDescription
                    public TypeDescription.Generic getReturnType() {
                        return this.bridgeType.getReturnType().asGenericType();
                    }

                    @Override // net.bytebuddy.description.method.MethodDescription
                    public TypeList.Generic getExceptionTypes() {
                        return this.bridgeTarget.getExceptionTypes().accept(TypeDescription.Generic.Visitor.TypeErasing.INSTANCE);
                    }

                    @Override // net.bytebuddy.description.method.MethodDescription
                    @MaybeNull
                    public AnnotationValue<?, ?> getDefaultValue() {
                        return AnnotationValue.UNDEFINED;
                    }

                    @Override // net.bytebuddy.description.TypeVariableSource
                    public TypeList.Generic getTypeVariables() {
                        return new TypeList.Generic.Empty();
                    }

                    @Override // net.bytebuddy.description.annotation.AnnotationSource
                    public AnnotationList getDeclaredAnnotations() {
                        return new AnnotationList.Empty();
                    }

                    @Override // net.bytebuddy.description.ModifierReviewable
                    public int getModifiers() {
                        return (this.bridgeTarget.getModifiers() | 64 | 4096) & (-1281);
                    }

                    @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
                    public String getInternalName() {
                        return this.bridgeTarget.getInternalName();
                    }
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$MethodPool$Record$AccessBridgeWrapper$BridgeTarget.class */
                protected static class BridgeTarget extends MethodDescription.InDefinedShape.AbstractBase {
                    private final MethodDescription bridgeTarget;
                    private final TypeDescription instrumentedType;

                    protected BridgeTarget(MethodDescription methodDescription, TypeDescription typeDescription) {
                        this.bridgeTarget = methodDescription;
                        this.instrumentedType = typeDescription;
                    }

                    @Override // net.bytebuddy.description.DeclaredByType
                    public TypeDescription getDeclaringType() {
                        return this.instrumentedType;
                    }

                    @Override // net.bytebuddy.description.method.MethodDescription, net.bytebuddy.description.method.MethodDescription.InDefinedShape
                    public ParameterList<ParameterDescription.InDefinedShape> getParameters() {
                        return new ParameterList.ForTokens(this, this.bridgeTarget.getParameters().asTokenList(ElementMatchers.is(this.instrumentedType)));
                    }

                    @Override // net.bytebuddy.description.method.MethodDescription
                    public TypeDescription.Generic getReturnType() {
                        return this.bridgeTarget.getReturnType();
                    }

                    @Override // net.bytebuddy.description.method.MethodDescription
                    public TypeList.Generic getExceptionTypes() {
                        return this.bridgeTarget.getExceptionTypes();
                    }

                    @Override // net.bytebuddy.description.method.MethodDescription
                    @MaybeNull
                    public AnnotationValue<?, ?> getDefaultValue() {
                        return this.bridgeTarget.getDefaultValue();
                    }

                    @Override // net.bytebuddy.description.TypeVariableSource
                    public TypeList.Generic getTypeVariables() {
                        return this.bridgeTarget.getTypeVariables();
                    }

                    @Override // net.bytebuddy.description.annotation.AnnotationSource
                    public AnnotationList getDeclaredAnnotations() {
                        return this.bridgeTarget.getDeclaredAnnotations();
                    }

                    @Override // net.bytebuddy.description.ModifierReviewable
                    public int getModifiers() {
                        return this.bridgeTarget.getModifiers();
                    }

                    @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
                    public String getInternalName() {
                        return this.bridgeTarget.getInternalName();
                    }
                }
            }
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$RecordComponentPool.class */
    public interface RecordComponentPool {
        Record target(RecordComponentDescription recordComponentDescription);

        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$RecordComponentPool$Record.class */
        public interface Record {
            boolean isImplicit();

            RecordComponentDescription getRecordComponent();

            RecordComponentAttributeAppender getRecordComponentAppender();

            void apply(ClassVisitor classVisitor, AnnotationValueFilter.Factory factory);

            void apply(RecordComponentVisitor recordComponentVisitor, AnnotationValueFilter.Factory factory);

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$RecordComponentPool$Record$ForImplicitRecordComponent.class */
            public static class ForImplicitRecordComponent implements Record {
                private final RecordComponentDescription recordComponentDescription;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.recordComponentDescription.equals(((ForImplicitRecordComponent) obj).recordComponentDescription);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.recordComponentDescription.hashCode();
                }

                public ForImplicitRecordComponent(RecordComponentDescription recordComponentDescription) {
                    this.recordComponentDescription = recordComponentDescription;
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.RecordComponentPool.Record
                public boolean isImplicit() {
                    return true;
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.RecordComponentPool.Record
                public RecordComponentDescription getRecordComponent() {
                    return this.recordComponentDescription;
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.RecordComponentPool.Record
                public RecordComponentAttributeAppender getRecordComponentAppender() {
                    throw new IllegalStateException("An implicit field record does not expose a field appender: " + this);
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.RecordComponentPool.Record
                public void apply(ClassVisitor classVisitor, AnnotationValueFilter.Factory factory) {
                    RecordComponentVisitor visitRecordComponent = classVisitor.visitRecordComponent(this.recordComponentDescription.getActualName(), this.recordComponentDescription.getDescriptor(), this.recordComponentDescription.getGenericSignature());
                    if (visitRecordComponent != null) {
                        RecordComponentAttributeAppender.ForInstrumentedRecordComponent.INSTANCE.apply(visitRecordComponent, this.recordComponentDescription, factory.on(this.recordComponentDescription));
                        visitRecordComponent.visitEnd();
                    }
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.RecordComponentPool.Record
                public void apply(RecordComponentVisitor recordComponentVisitor, AnnotationValueFilter.Factory factory) {
                    throw new IllegalStateException("An implicit field record is not intended for partial application: " + this);
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$RecordComponentPool$Record$ForExplicitRecordComponent.class */
            public static class ForExplicitRecordComponent implements Record {
                private final RecordComponentAttributeAppender attributeAppender;
                private final RecordComponentDescription recordComponentDescription;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.attributeAppender.equals(((ForExplicitRecordComponent) obj).attributeAppender) && this.recordComponentDescription.equals(((ForExplicitRecordComponent) obj).recordComponentDescription);
                }

                public int hashCode() {
                    return (((getClass().hashCode() * 31) + this.attributeAppender.hashCode()) * 31) + this.recordComponentDescription.hashCode();
                }

                public ForExplicitRecordComponent(RecordComponentAttributeAppender recordComponentAttributeAppender, RecordComponentDescription recordComponentDescription) {
                    this.attributeAppender = recordComponentAttributeAppender;
                    this.recordComponentDescription = recordComponentDescription;
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.RecordComponentPool.Record
                public boolean isImplicit() {
                    return false;
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.RecordComponentPool.Record
                public RecordComponentDescription getRecordComponent() {
                    return this.recordComponentDescription;
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.RecordComponentPool.Record
                public RecordComponentAttributeAppender getRecordComponentAppender() {
                    return this.attributeAppender;
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.RecordComponentPool.Record
                public void apply(ClassVisitor classVisitor, AnnotationValueFilter.Factory factory) {
                    RecordComponentVisitor visitRecordComponent = classVisitor.visitRecordComponent(this.recordComponentDescription.getActualName(), this.recordComponentDescription.getDescriptor(), this.recordComponentDescription.getGenericSignature());
                    if (visitRecordComponent != null) {
                        this.attributeAppender.apply(visitRecordComponent, this.recordComponentDescription, factory.on(this.recordComponentDescription));
                        visitRecordComponent.visitEnd();
                    }
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.RecordComponentPool.Record
                public void apply(RecordComponentVisitor recordComponentVisitor, AnnotationValueFilter.Factory factory) {
                    this.attributeAppender.apply(recordComponentVisitor, this.recordComponentDescription, factory.on(this.recordComponentDescription));
                }
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$RecordComponentPool$Disabled.class */
        public enum Disabled implements RecordComponentPool {
            INSTANCE;

            @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.RecordComponentPool
            public final Record target(RecordComponentDescription recordComponentDescription) {
                throw new IllegalStateException("Cannot look up record component from disabled pool");
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default.class */
    public static abstract class Default<S> implements TypeWriter<S> {

        @AlwaysNull
        private static final String NO_REFERENCE;

        @MaybeNull
        protected static final String DUMP_FOLDER;
        protected final TypeDescription instrumentedType;
        protected final ClassFileVersion classFileVersion;
        protected final FieldPool fieldPool;
        protected final RecordComponentPool recordComponentPool;
        protected final List<? extends DynamicType> auxiliaryTypes;
        protected final FieldList<FieldDescription.InDefinedShape> fields;
        protected final MethodList<?> methods;
        protected final MethodList<?> instrumentedMethods;
        protected final RecordComponentList<RecordComponentDescription.InDefinedShape> recordComponents;
        protected final LoadedTypeInitializer loadedTypeInitializer;
        protected final TypeInitializer typeInitializer;
        protected final TypeAttributeAppender typeAttributeAppender;
        protected final AsmVisitorWrapper asmVisitorWrapper;
        protected final AnnotationValueFilter.Factory annotationValueFilterFactory;
        protected final AnnotationRetention annotationRetention;
        protected final AuxiliaryType.NamingStrategy auxiliaryTypeNamingStrategy;
        protected final Implementation.Context.Factory implementationContextFactory;
        protected final TypeValidation typeValidation;
        protected final ClassWriterStrategy classWriterStrategy;
        protected final TypePool typePool;
        private static final boolean ACCESS_CONTROLLER;

        protected abstract Default<S>.UnresolvedType create(TypeInitializer typeInitializer, ClassDumpAction.Dispatcher dispatcher);

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.annotationRetention.equals(((Default) obj).annotationRetention) && this.typeValidation.equals(((Default) obj).typeValidation) && this.instrumentedType.equals(((Default) obj).instrumentedType) && this.classFileVersion.equals(((Default) obj).classFileVersion) && this.fieldPool.equals(((Default) obj).fieldPool) && this.recordComponentPool.equals(((Default) obj).recordComponentPool) && this.auxiliaryTypes.equals(((Default) obj).auxiliaryTypes) && this.fields.equals(((Default) obj).fields) && this.methods.equals(((Default) obj).methods) && this.instrumentedMethods.equals(((Default) obj).instrumentedMethods) && this.recordComponents.equals(((Default) obj).recordComponents) && this.loadedTypeInitializer.equals(((Default) obj).loadedTypeInitializer) && this.typeInitializer.equals(((Default) obj).typeInitializer) && this.typeAttributeAppender.equals(((Default) obj).typeAttributeAppender) && this.asmVisitorWrapper.equals(((Default) obj).asmVisitorWrapper) && this.annotationValueFilterFactory.equals(((Default) obj).annotationValueFilterFactory) && this.auxiliaryTypeNamingStrategy.equals(((Default) obj).auxiliaryTypeNamingStrategy) && this.implementationContextFactory.equals(((Default) obj).implementationContextFactory) && this.classWriterStrategy.equals(((Default) obj).classWriterStrategy) && this.typePool.equals(((Default) obj).typePool);
        }

        public int hashCode() {
            return (((((((((((((((((((((((((((((((((((((((getClass().hashCode() * 31) + this.instrumentedType.hashCode()) * 31) + this.classFileVersion.hashCode()) * 31) + this.fieldPool.hashCode()) * 31) + this.recordComponentPool.hashCode()) * 31) + this.auxiliaryTypes.hashCode()) * 31) + this.fields.hashCode()) * 31) + this.methods.hashCode()) * 31) + this.instrumentedMethods.hashCode()) * 31) + this.recordComponents.hashCode()) * 31) + this.loadedTypeInitializer.hashCode()) * 31) + this.typeInitializer.hashCode()) * 31) + this.typeAttributeAppender.hashCode()) * 31) + this.asmVisitorWrapper.hashCode()) * 31) + this.annotationValueFilterFactory.hashCode()) * 31) + this.annotationRetention.hashCode()) * 31) + this.auxiliaryTypeNamingStrategy.hashCode()) * 31) + this.implementationContextFactory.hashCode()) * 31) + this.typeValidation.hashCode()) * 31) + this.classWriterStrategy.hashCode()) * 31) + this.typePool.hashCode();
        }

        static {
            String str;
            try {
                Class.forName("java.security.AccessController", false, null);
                ACCESS_CONTROLLER = Boolean.parseBoolean(System.getProperty("net.bytebuddy.securitymanager", "true"));
            } catch (ClassNotFoundException unused) {
                ACCESS_CONTROLLER = false;
            } catch (SecurityException unused2) {
                ACCESS_CONTROLLER = true;
            }
            NO_REFERENCE = null;
            try {
                str = (String) doPrivileged(new GetSystemPropertyAction(TypeWriter.DUMP_PROPERTY));
            } catch (RuntimeException unused3) {
                str = null;
            }
            DUMP_FOLDER = str;
        }

        protected Default(TypeDescription typeDescription, ClassFileVersion classFileVersion, FieldPool fieldPool, RecordComponentPool recordComponentPool, List<? extends DynamicType> list, FieldList<FieldDescription.InDefinedShape> fieldList, MethodList<?> methodList, MethodList<?> methodList2, RecordComponentList<RecordComponentDescription.InDefinedShape> recordComponentList, LoadedTypeInitializer loadedTypeInitializer, TypeInitializer typeInitializer, TypeAttributeAppender typeAttributeAppender, AsmVisitorWrapper asmVisitorWrapper, AnnotationValueFilter.Factory factory, AnnotationRetention annotationRetention, AuxiliaryType.NamingStrategy namingStrategy, Implementation.Context.Factory factory2, TypeValidation typeValidation, ClassWriterStrategy classWriterStrategy, TypePool typePool) {
            this.instrumentedType = typeDescription;
            this.classFileVersion = classFileVersion;
            this.fieldPool = fieldPool;
            this.recordComponentPool = recordComponentPool;
            this.auxiliaryTypes = list;
            this.fields = fieldList;
            this.methods = methodList;
            this.instrumentedMethods = methodList2;
            this.recordComponents = recordComponentList;
            this.loadedTypeInitializer = loadedTypeInitializer;
            this.typeInitializer = typeInitializer;
            this.typeAttributeAppender = typeAttributeAppender;
            this.asmVisitorWrapper = asmVisitorWrapper;
            this.auxiliaryTypeNamingStrategy = namingStrategy;
            this.annotationValueFilterFactory = factory;
            this.annotationRetention = annotationRetention;
            this.implementationContextFactory = factory2;
            this.typeValidation = typeValidation;
            this.classWriterStrategy = classWriterStrategy;
            this.typePool = typePool;
        }

        @AccessControllerPlugin.Enhance
        private static <T> T doPrivileged(PrivilegedAction<T> privilegedAction) {
            return ACCESS_CONTROLLER ? (T) AccessController.doPrivileged(privilegedAction) : privilegedAction.run();
        }

        /* JADX INFO: Access modifiers changed from: private */
        @AccessControllerPlugin.Enhance
        public static <T> T doPrivileged(PrivilegedExceptionAction<T> privilegedExceptionAction) {
            return ACCESS_CONTROLLER ? (T) AccessController.doPrivileged(privilegedExceptionAction) : privilegedExceptionAction.run();
        }

        public static <U> TypeWriter<U> forCreation(MethodRegistry.Compiled compiled, List<? extends DynamicType> list, FieldPool fieldPool, RecordComponentPool recordComponentPool, TypeAttributeAppender typeAttributeAppender, AsmVisitorWrapper asmVisitorWrapper, ClassFileVersion classFileVersion, AnnotationValueFilter.Factory factory, AnnotationRetention annotationRetention, AuxiliaryType.NamingStrategy namingStrategy, Implementation.Context.Factory factory2, TypeValidation typeValidation, ClassWriterStrategy classWriterStrategy, TypePool typePool) {
            return new ForCreation(compiled.getInstrumentedType(), classFileVersion, fieldPool, compiled, recordComponentPool, list, compiled.getInstrumentedType().getDeclaredFields(), compiled.getMethods(), compiled.getInstrumentedMethods(), compiled.getInstrumentedType().getRecordComponents(), compiled.getLoadedTypeInitializer(), compiled.getTypeInitializer(), typeAttributeAppender, asmVisitorWrapper, factory, annotationRetention, namingStrategy, factory2, typeValidation, classWriterStrategy, typePool);
        }

        public static <U> TypeWriter<U> forRedefinition(MethodRegistry.Prepared prepared, List<? extends DynamicType> list, FieldPool fieldPool, RecordComponentPool recordComponentPool, TypeAttributeAppender typeAttributeAppender, AsmVisitorWrapper asmVisitorWrapper, ClassFileVersion classFileVersion, AnnotationValueFilter.Factory factory, AnnotationRetention annotationRetention, AuxiliaryType.NamingStrategy namingStrategy, Implementation.Context.Factory factory2, TypeValidation typeValidation, ClassWriterStrategy classWriterStrategy, TypePool typePool, TypeDescription typeDescription, ClassFileLocator classFileLocator) {
            return new ForInlining.WithFullProcessing(prepared.getInstrumentedType(), classFileVersion, fieldPool, recordComponentPool, list, prepared.getInstrumentedType().getDeclaredFields(), prepared.getMethods(), prepared.getInstrumentedMethods(), prepared.getInstrumentedType().getRecordComponents(), prepared.getLoadedTypeInitializer(), prepared.getTypeInitializer(), typeAttributeAppender, asmVisitorWrapper, factory, annotationRetention, namingStrategy, factory2, typeValidation, classWriterStrategy, typePool, typeDescription, classFileLocator, prepared, SubclassImplementationTarget.Factory.LEVEL_TYPE, MethodRebaseResolver.Disabled.INSTANCE);
        }

        public static <U> TypeWriter<U> forRebasing(MethodRegistry.Prepared prepared, List<? extends DynamicType> list, FieldPool fieldPool, RecordComponentPool recordComponentPool, TypeAttributeAppender typeAttributeAppender, AsmVisitorWrapper asmVisitorWrapper, ClassFileVersion classFileVersion, AnnotationValueFilter.Factory factory, AnnotationRetention annotationRetention, AuxiliaryType.NamingStrategy namingStrategy, Implementation.Context.Factory factory2, TypeValidation typeValidation, ClassWriterStrategy classWriterStrategy, TypePool typePool, TypeDescription typeDescription, ClassFileLocator classFileLocator, MethodRebaseResolver methodRebaseResolver) {
            return new ForInlining.WithFullProcessing(prepared.getInstrumentedType(), classFileVersion, fieldPool, recordComponentPool, CompoundList.of((List) list, (List) methodRebaseResolver.getAuxiliaryTypes()), prepared.getInstrumentedType().getDeclaredFields(), prepared.getMethods(), prepared.getInstrumentedMethods(), prepared.getInstrumentedType().getRecordComponents(), prepared.getLoadedTypeInitializer(), prepared.getTypeInitializer(), typeAttributeAppender, asmVisitorWrapper, factory, annotationRetention, namingStrategy, factory2, typeValidation, classWriterStrategy, typePool, typeDescription, classFileLocator, prepared, new RebaseImplementationTarget.Factory(methodRebaseResolver), methodRebaseResolver);
        }

        public static <U> TypeWriter<U> forDecoration(TypeDescription typeDescription, ClassFileVersion classFileVersion, List<? extends DynamicType> list, List<? extends MethodDescription> list2, TypeAttributeAppender typeAttributeAppender, AsmVisitorWrapper asmVisitorWrapper, AnnotationValueFilter.Factory factory, AnnotationRetention annotationRetention, AuxiliaryType.NamingStrategy namingStrategy, Implementation.Context.Factory factory2, TypeValidation typeValidation, ClassWriterStrategy classWriterStrategy, TypePool typePool, ClassFileLocator classFileLocator) {
            return new ForInlining.WithDecorationOnly(typeDescription, classFileVersion, list, new MethodList.Explicit(list2), typeAttributeAppender, asmVisitorWrapper, factory, annotationRetention, namingStrategy, factory2, typeValidation, classWriterStrategy, typePool, classFileLocator);
        }

        @Override // net.bytebuddy.dynamic.scaffold.TypeWriter
        @SuppressFBWarnings(value = {"REC_CATCH_EXCEPTION"}, justification = "Setting a debugging property should never change the program outcome.")
        public DynamicType.Unloaded<S> make(TypeResolutionStrategy.Resolved resolved) {
            ClassDumpAction.Dispatcher enabled = DUMP_FOLDER == null ? ClassDumpAction.Dispatcher.Disabled.INSTANCE : new ClassDumpAction.Dispatcher.Enabled(DUMP_FOLDER, System.currentTimeMillis());
            Default<S>.UnresolvedType create = create(resolved.injectedInto(this.typeInitializer), enabled);
            enabled.dump(this.instrumentedType, false, create.getBinaryRepresentation());
            return create.toDynamicType(resolved);
        }

        @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default$UnresolvedType.class */
        protected class UnresolvedType {
            private final byte[] binaryRepresentation;
            private final List<? extends DynamicType> auxiliaryTypes;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && Arrays.equals(this.binaryRepresentation, ((UnresolvedType) obj).binaryRepresentation) && this.auxiliaryTypes.equals(((UnresolvedType) obj).auxiliaryTypes) && Default.this.equals(Default.this);
            }

            public int hashCode() {
                return (((((getClass().hashCode() * 31) + Arrays.hashCode(this.binaryRepresentation)) * 31) + this.auxiliaryTypes.hashCode()) * 31) + Default.this.hashCode();
            }

            protected UnresolvedType(byte[] bArr, List<? extends DynamicType> list) {
                this.binaryRepresentation = bArr;
                this.auxiliaryTypes = list;
            }

            protected DynamicType.Unloaded<S> toDynamicType(TypeResolutionStrategy.Resolved resolved) {
                return new DynamicType.Default.Unloaded(Default.this.instrumentedType, this.binaryRepresentation, Default.this.loadedTypeInitializer, CompoundList.of((List) Default.this.auxiliaryTypes, (List) this.auxiliaryTypes), resolved);
            }

            protected byte[] getBinaryRepresentation() {
                return this.binaryRepresentation;
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default$SignatureKey.class */
        protected static class SignatureKey {
            private final String internalName;
            private final String descriptor;

            public SignatureKey(String str, String str2) {
                this.internalName = str;
                this.descriptor = str2;
            }

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                SignatureKey signatureKey = (SignatureKey) obj;
                return this.internalName.equals(signatureKey.internalName) && this.descriptor.equals(signatureKey.descriptor);
            }

            public int hashCode() {
                return 17 + this.internalName.hashCode() + (31 * this.descriptor.hashCode());
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default$ValidatingClassVisitor.class */
        protected static class ValidatingClassVisitor extends ClassVisitor {
            private static final String NO_PARAMETERS = "()";
            private static final String RETURNS_VOID = "V";
            private static final String STRING_DESCRIPTOR = "Ljava/lang/String;";

            @AlwaysNull
            private static final FieldVisitor IGNORE_FIELD = null;

            @AlwaysNull
            private static final MethodVisitor IGNORE_METHOD = null;

            @UnknownNull
            private Constraint constraint;

            protected ValidatingClassVisitor(ClassVisitor classVisitor) {
                super(OpenedClassReader.ASM_API, classVisitor);
            }

            protected static ClassVisitor of(ClassVisitor classVisitor, TypeValidation typeValidation) {
                return typeValidation.isEnabled() ? new ValidatingClassVisitor(classVisitor) : classVisitor;
            }

            @Override // net.bytebuddy.jar.asm.ClassVisitor
            public void visit(int i, int i2, String str, @MaybeNull String str2, @MaybeNull String str3, @MaybeNull String[] strArr) {
                boolean z;
                ClassFileVersion ofMinorMajor = ClassFileVersion.ofMinorMajor(i);
                ArrayList arrayList = new ArrayList();
                arrayList.add(new Constraint.ForClassFileVersion(ofMinorMajor));
                if (str.endsWith("/package-info")) {
                    arrayList.add(Constraint.ForPackageType.INSTANCE);
                } else if ((i2 & 8192) != 0) {
                    if (!ofMinorMajor.isAtLeast(ClassFileVersion.JAVA_V5)) {
                        throw new IllegalStateException("Cannot define an annotation type for class file version " + ofMinorMajor);
                    }
                    arrayList.add(ofMinorMajor.isAtLeast(ClassFileVersion.JAVA_V8) ? Constraint.ForAnnotation.JAVA_8 : Constraint.ForAnnotation.CLASSIC);
                } else if ((i2 & 512) != 0) {
                    arrayList.add(ofMinorMajor.isAtLeast(ClassFileVersion.JAVA_V8) ? Constraint.ForInterface.JAVA_8 : Constraint.ForInterface.CLASSIC);
                } else if ((i2 & 1024) != 0) {
                    arrayList.add(Constraint.ForClass.ABSTRACT);
                } else {
                    arrayList.add(Constraint.ForClass.MANIFEST);
                }
                if ((i2 & 65536) != 0) {
                    arrayList.add(Constraint.ForRecord.INSTANCE);
                    z = true;
                } else {
                    z = false;
                }
                this.constraint = new Constraint.Compound(arrayList);
                this.constraint.assertType(i2, strArr != null, str2 != null);
                if (z) {
                    this.constraint.assertRecord();
                }
                super.visit(i, i2, str, str2, str3, strArr);
            }

            @Override // net.bytebuddy.jar.asm.ClassVisitor
            public void visitPermittedSubclass(String str) {
                this.constraint.assertPermittedSubclass();
                super.visitPermittedSubclass(str);
            }

            @Override // net.bytebuddy.jar.asm.ClassVisitor
            @MaybeNull
            public AnnotationVisitor visitAnnotation(String str, boolean z) {
                this.constraint.assertAnnotation();
                return super.visitAnnotation(str, z);
            }

            @Override // net.bytebuddy.jar.asm.ClassVisitor
            @MaybeNull
            public AnnotationVisitor visitTypeAnnotation(int i, @MaybeNull TypePath typePath, String str, boolean z) {
                this.constraint.assertTypeAnnotation();
                return super.visitTypeAnnotation(i, typePath, str, z);
            }

            @Override // net.bytebuddy.jar.asm.ClassVisitor
            public void visitNestHost(String str) {
                this.constraint.assertNestMate();
                super.visitNestHost(str);
            }

            @Override // net.bytebuddy.jar.asm.ClassVisitor
            public void visitNestMember(String str) {
                this.constraint.assertNestMate();
                super.visitNestMember(str);
            }

            @Override // net.bytebuddy.jar.asm.ClassVisitor
            @MaybeNull
            public FieldVisitor visitField(int i, String str, String str2, @MaybeNull String str3, @MaybeNull Object obj) {
                Class cls;
                int i2;
                int i3;
                if (obj != null) {
                    switch (str2.charAt(0)) {
                        case 'B':
                        case 'C':
                        case 'I':
                        case 'S':
                        case 'Z':
                            cls = Integer.class;
                            break;
                        case 'D':
                            cls = Double.class;
                            break;
                        case 'E':
                        case 'G':
                        case 'H':
                        case 'K':
                        case 'L':
                        case 'M':
                        case 'N':
                        case 'O':
                        case 'P':
                        case 'Q':
                        case 'R':
                        case 'T':
                        case 'U':
                        case 'V':
                        case 'W':
                        case 'X':
                        case 'Y':
                        default:
                            if (!str2.equals(STRING_DESCRIPTOR)) {
                                throw new IllegalStateException("Cannot define a default value for type of field " + str);
                            }
                            cls = String.class;
                            break;
                        case 'F':
                            cls = Float.class;
                            break;
                        case 'J':
                            cls = Long.class;
                            break;
                    }
                    if (!cls.isInstance(obj)) {
                        throw new IllegalStateException("Field " + str + " defines an incompatible default value " + obj);
                    }
                    if (cls == Integer.class) {
                        switch (str2.charAt(0)) {
                            case 'B':
                                i2 = -128;
                                i3 = 127;
                                break;
                            case 'C':
                                i2 = 0;
                                i3 = 65535;
                                break;
                            case 'S':
                                i2 = -32768;
                                i3 = 32767;
                                break;
                            case 'Z':
                                i2 = 0;
                                i3 = 1;
                                break;
                            default:
                                i2 = Integer.MIN_VALUE;
                                i3 = Integer.MAX_VALUE;
                                break;
                        }
                        if (((Integer) obj).intValue() < i2 || ((Integer) obj).intValue() > i3) {
                            throw new IllegalStateException("Field " + str + " defines an incompatible default value " + obj);
                        }
                    }
                }
                this.constraint.assertField(str, (i & 1) != 0, (i & 8) != 0, (i & 16) != 0, str3 != null);
                FieldVisitor visitField = super.visitField(i, str, str2, str3, obj);
                return visitField == null ? IGNORE_FIELD : new ValidatingFieldVisitor(visitField);
            }

            @Override // net.bytebuddy.jar.asm.ClassVisitor
            @MaybeNull
            public MethodVisitor visitMethod(int i, String str, String str2, @MaybeNull String str3, @MaybeNull String[] strArr) {
                this.constraint.assertMethod(str, (i & 1024) != 0, (i & 1) != 0, (i & 2) != 0, (i & 8) != 0, (str.equals(MethodDescription.CONSTRUCTOR_INTERNAL_NAME) || str.equals(MethodDescription.TYPE_INITIALIZER_INTERNAL_NAME) || (i & 10) != 0) ? false : true, str.equals(MethodDescription.CONSTRUCTOR_INTERNAL_NAME), !str2.startsWith(NO_PARAMETERS) || str2.endsWith(RETURNS_VOID), str3 != null);
                MethodVisitor visitMethod = super.visitMethod(i, str, str2, str3, strArr);
                return visitMethod == null ? IGNORE_METHOD : new ValidatingMethodVisitor(visitMethod, str);
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default$ValidatingClassVisitor$Constraint.class */
            protected interface Constraint {
                void assertType(int i, boolean z, boolean z2);

                void assertField(String str, boolean z, boolean z2, boolean z3, boolean z4);

                void assertMethod(String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8);

                void assertAnnotation();

                void assertTypeAnnotation();

                void assertDefaultValue(String str);

                void assertDefaultMethodCall();

                void assertTypeInConstantPool();

                void assertMethodTypeInConstantPool();

                void assertHandleInConstantPool();

                void assertInvokeDynamic();

                void assertSubRoutine();

                void assertDynamicValueInConstantPool();

                void assertNestMate();

                void assertRecord();

                void assertPermittedSubclass();

                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default$ValidatingClassVisitor$Constraint$ForClass.class */
                public enum ForClass implements Constraint {
                    MANIFEST(true),
                    ABSTRACT(false);

                    private final boolean manifestType;

                    ForClass(boolean z) {
                        this.manifestType = z;
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertType(int i, boolean z, boolean z2) {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertField(String str, boolean z, boolean z2, boolean z3, boolean z4) {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertMethod(String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8) {
                        if (z && this.manifestType) {
                            throw new IllegalStateException("Cannot define abstract method '" + str + "' for non-abstract class");
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertAnnotation() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertTypeAnnotation() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertDefaultValue(String str) {
                        throw new IllegalStateException("Cannot define default value for '" + str + "' for non-annotation type");
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertDefaultMethodCall() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertTypeInConstantPool() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertMethodTypeInConstantPool() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertHandleInConstantPool() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertInvokeDynamic() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertSubRoutine() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertDynamicValueInConstantPool() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertNestMate() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertRecord() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertPermittedSubclass() {
                    }
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default$ValidatingClassVisitor$Constraint$ForPackageType.class */
                public enum ForPackageType implements Constraint {
                    INSTANCE;

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertField(String str, boolean z, boolean z2, boolean z3, boolean z4) {
                        throw new IllegalStateException("Cannot define a field for a package description type");
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertMethod(String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8) {
                        throw new IllegalStateException("Cannot define a method for a package description type");
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertAnnotation() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertTypeAnnotation() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertDefaultValue(String str) {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertDefaultMethodCall() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertTypeInConstantPool() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertMethodTypeInConstantPool() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertHandleInConstantPool() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertInvokeDynamic() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertSubRoutine() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertType(int i, boolean z, boolean z2) {
                        if (i != 5632) {
                            throw new IllegalStateException("A package description type must define 5632 as modifier");
                        }
                        if (z) {
                            throw new IllegalStateException("Cannot implement interface for package type");
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertDynamicValueInConstantPool() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertNestMate() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertRecord() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertPermittedSubclass() {
                    }
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default$ValidatingClassVisitor$Constraint$ForInterface.class */
                public enum ForInterface implements Constraint {
                    CLASSIC(true),
                    JAVA_8(false);

                    private final boolean classic;

                    ForInterface(boolean z) {
                        this.classic = z;
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertField(String str, boolean z, boolean z2, boolean z3, boolean z4) {
                        if (!z2 || !z || !z3) {
                            throw new IllegalStateException("Cannot only define public, static, final field '" + str + "' for interface type");
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertMethod(String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8) {
                        if (!str.equals(MethodDescription.TYPE_INITIALIZER_INTERNAL_NAME)) {
                            if (z6) {
                                throw new IllegalStateException("Cannot define constructor for interface type");
                            }
                            if (this.classic && !z2) {
                                throw new IllegalStateException("Cannot define non-public method '" + str + "' for interface type");
                            }
                            if (this.classic && !z5) {
                                throw new IllegalStateException("Cannot define non-virtual method '" + str + "' for a pre-Java 8 interface type");
                            }
                            if (this.classic && !z) {
                                throw new IllegalStateException("Cannot define default method '" + str + "' for pre-Java 8 interface type");
                            }
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertAnnotation() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertTypeAnnotation() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertDefaultValue(String str) {
                        throw new IllegalStateException("Cannot define default value for '" + str + "' for non-annotation type");
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertDefaultMethodCall() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertType(int i, boolean z, boolean z2) {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertTypeInConstantPool() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertMethodTypeInConstantPool() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertHandleInConstantPool() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertInvokeDynamic() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertSubRoutine() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertDynamicValueInConstantPool() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertNestMate() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertRecord() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertPermittedSubclass() {
                    }
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default$ValidatingClassVisitor$Constraint$ForRecord.class */
                public enum ForRecord implements Constraint {
                    INSTANCE;

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertField(String str, boolean z, boolean z2, boolean z3, boolean z4) {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertMethod(String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8) {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertAnnotation() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertTypeAnnotation() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertDefaultValue(String str) {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertDefaultMethodCall() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertType(int i, boolean z, boolean z2) {
                        if ((i & 1024) != 0) {
                            throw new IllegalStateException("Cannot define a record class as abstract");
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertTypeInConstantPool() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertMethodTypeInConstantPool() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertHandleInConstantPool() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertInvokeDynamic() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertSubRoutine() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertDynamicValueInConstantPool() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertNestMate() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertRecord() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertPermittedSubclass() {
                    }
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default$ValidatingClassVisitor$Constraint$ForAnnotation.class */
                public enum ForAnnotation implements Constraint {
                    CLASSIC(true),
                    JAVA_8(false);

                    private final boolean classic;

                    ForAnnotation(boolean z) {
                        this.classic = z;
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertField(String str, boolean z, boolean z2, boolean z3, boolean z4) {
                        if (!z2 || !z || !z3) {
                            throw new IllegalStateException("Cannot only define public, static, final field '" + str + "' for interface type");
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertMethod(String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8) {
                        if (!str.equals(MethodDescription.TYPE_INITIALIZER_INTERNAL_NAME)) {
                            if (z6) {
                                throw new IllegalStateException("Cannot define constructor for interface type");
                            }
                            if (this.classic && !z5) {
                                throw new IllegalStateException("Cannot define non-virtual method '" + str + "' for a pre-Java 8 annotation type");
                            }
                            if (!z4 && z7) {
                                throw new IllegalStateException("Cannot define method '" + str + "' with the given signature as an annotation type method");
                            }
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertAnnotation() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertTypeAnnotation() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertDefaultValue(String str) {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertDefaultMethodCall() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertType(int i, boolean z, boolean z2) {
                        if ((i & 512) == 0) {
                            throw new IllegalStateException("Cannot define annotation type without interface modifier");
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertTypeInConstantPool() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertMethodTypeInConstantPool() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertHandleInConstantPool() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertInvokeDynamic() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertSubRoutine() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertDynamicValueInConstantPool() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertNestMate() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertRecord() {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public final void assertPermittedSubclass() {
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default$ValidatingClassVisitor$Constraint$ForClassFileVersion.class */
                public static class ForClassFileVersion implements Constraint {
                    private final ClassFileVersion classFileVersion;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.classFileVersion.equals(((ForClassFileVersion) obj).classFileVersion);
                    }

                    public int hashCode() {
                        return (getClass().hashCode() * 31) + this.classFileVersion.hashCode();
                    }

                    protected ForClassFileVersion(ClassFileVersion classFileVersion) {
                        this.classFileVersion = classFileVersion;
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertType(int i, boolean z, boolean z2) {
                        if ((i & 8192) != 0 && !this.classFileVersion.isAtLeast(ClassFileVersion.JAVA_V5)) {
                            throw new IllegalStateException("Cannot define annotation type for class file version " + this.classFileVersion);
                        }
                        if (z2 && !this.classFileVersion.isAtLeast(ClassFileVersion.JAVA_V4)) {
                            throw new IllegalStateException("Cannot define a generic type for class file version " + this.classFileVersion);
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertField(String str, boolean z, boolean z2, boolean z3, boolean z4) {
                        if (z4 && !this.classFileVersion.isAtLeast(ClassFileVersion.JAVA_V4)) {
                            throw new IllegalStateException("Cannot define generic field '" + str + "' for class file version " + this.classFileVersion);
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertMethod(String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8) {
                        if (z8 && !this.classFileVersion.isAtLeast(ClassFileVersion.JAVA_V4)) {
                            throw new IllegalStateException("Cannot define generic method '" + str + "' for class file version " + this.classFileVersion);
                        }
                        if (!z5 && z) {
                            throw new IllegalStateException("Cannot define static or non-virtual method '" + str + "' to be abstract");
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertAnnotation() {
                        if (this.classFileVersion.isLessThan(ClassFileVersion.JAVA_V5)) {
                            throw new IllegalStateException("Cannot write annotations for class file version " + this.classFileVersion);
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertTypeAnnotation() {
                        if (this.classFileVersion.isLessThan(ClassFileVersion.JAVA_V5)) {
                            throw new IllegalStateException("Cannot write type annotations for class file version " + this.classFileVersion);
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertDefaultValue(String str) {
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertDefaultMethodCall() {
                        if (this.classFileVersion.isLessThan(ClassFileVersion.JAVA_V8)) {
                            throw new IllegalStateException("Cannot invoke default method for class file version " + this.classFileVersion);
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertTypeInConstantPool() {
                        if (this.classFileVersion.isLessThan(ClassFileVersion.JAVA_V5)) {
                            throw new IllegalStateException("Cannot write type to constant pool for class file version " + this.classFileVersion);
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertMethodTypeInConstantPool() {
                        if (this.classFileVersion.isLessThan(ClassFileVersion.JAVA_V7)) {
                            throw new IllegalStateException("Cannot write method type to constant pool for class file version " + this.classFileVersion);
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertHandleInConstantPool() {
                        if (this.classFileVersion.isLessThan(ClassFileVersion.JAVA_V7)) {
                            throw new IllegalStateException("Cannot write method handle to constant pool for class file version " + this.classFileVersion);
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertInvokeDynamic() {
                        if (this.classFileVersion.isLessThan(ClassFileVersion.JAVA_V7)) {
                            throw new IllegalStateException("Cannot write invoke dynamic instruction for class file version " + this.classFileVersion);
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertSubRoutine() {
                        if (this.classFileVersion.isGreaterThan(ClassFileVersion.JAVA_V5)) {
                            throw new IllegalStateException("Cannot write subroutine for class file version " + this.classFileVersion);
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertDynamicValueInConstantPool() {
                        if (this.classFileVersion.isLessThan(ClassFileVersion.JAVA_V11)) {
                            throw new IllegalStateException("Cannot write dynamic constant for class file version " + this.classFileVersion);
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertNestMate() {
                        if (this.classFileVersion.isLessThan(ClassFileVersion.JAVA_V11)) {
                            throw new IllegalStateException("Cannot define nest mate for class file version " + this.classFileVersion);
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertRecord() {
                        if (this.classFileVersion.isLessThan(ClassFileVersion.JAVA_V14)) {
                            throw new IllegalStateException("Cannot define record for class file version " + this.classFileVersion);
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertPermittedSubclass() {
                        if (this.classFileVersion.isLessThan(ClassFileVersion.JAVA_V17)) {
                            throw new IllegalStateException("Cannot define permitted subclasses for class file version " + this.classFileVersion);
                        }
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default$ValidatingClassVisitor$Constraint$Compound.class */
                public static class Compound implements Constraint {
                    private final List<Constraint> constraints = new ArrayList();

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.constraints.equals(((Compound) obj).constraints);
                    }

                    public int hashCode() {
                        return (getClass().hashCode() * 31) + this.constraints.hashCode();
                    }

                    public Compound(List<? extends Constraint> list) {
                        for (Constraint constraint : list) {
                            if (constraint instanceof Compound) {
                                this.constraints.addAll(((Compound) constraint).constraints);
                            } else {
                                this.constraints.add(constraint);
                            }
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertType(int i, boolean z, boolean z2) {
                        Iterator<Constraint> it = this.constraints.iterator();
                        while (it.hasNext()) {
                            it.next().assertType(i, z, z2);
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertField(String str, boolean z, boolean z2, boolean z3, boolean z4) {
                        Iterator<Constraint> it = this.constraints.iterator();
                        while (it.hasNext()) {
                            it.next().assertField(str, z, z2, z3, z4);
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertMethod(String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8) {
                        Iterator<Constraint> it = this.constraints.iterator();
                        while (it.hasNext()) {
                            it.next().assertMethod(str, z, z2, z3, z4, z5, z6, z7, z8);
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertDefaultValue(String str) {
                        Iterator<Constraint> it = this.constraints.iterator();
                        while (it.hasNext()) {
                            it.next().assertDefaultValue(str);
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertDefaultMethodCall() {
                        Iterator<Constraint> it = this.constraints.iterator();
                        while (it.hasNext()) {
                            it.next().assertDefaultMethodCall();
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertAnnotation() {
                        Iterator<Constraint> it = this.constraints.iterator();
                        while (it.hasNext()) {
                            it.next().assertAnnotation();
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertTypeAnnotation() {
                        Iterator<Constraint> it = this.constraints.iterator();
                        while (it.hasNext()) {
                            it.next().assertTypeAnnotation();
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertTypeInConstantPool() {
                        Iterator<Constraint> it = this.constraints.iterator();
                        while (it.hasNext()) {
                            it.next().assertTypeInConstantPool();
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertMethodTypeInConstantPool() {
                        Iterator<Constraint> it = this.constraints.iterator();
                        while (it.hasNext()) {
                            it.next().assertMethodTypeInConstantPool();
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertHandleInConstantPool() {
                        Iterator<Constraint> it = this.constraints.iterator();
                        while (it.hasNext()) {
                            it.next().assertHandleInConstantPool();
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertInvokeDynamic() {
                        Iterator<Constraint> it = this.constraints.iterator();
                        while (it.hasNext()) {
                            it.next().assertInvokeDynamic();
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertSubRoutine() {
                        Iterator<Constraint> it = this.constraints.iterator();
                        while (it.hasNext()) {
                            it.next().assertSubRoutine();
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertDynamicValueInConstantPool() {
                        Iterator<Constraint> it = this.constraints.iterator();
                        while (it.hasNext()) {
                            it.next().assertDynamicValueInConstantPool();
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertNestMate() {
                        Iterator<Constraint> it = this.constraints.iterator();
                        while (it.hasNext()) {
                            it.next().assertNestMate();
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertRecord() {
                        Iterator<Constraint> it = this.constraints.iterator();
                        while (it.hasNext()) {
                            it.next().assertRecord();
                        }
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ValidatingClassVisitor.Constraint
                    public void assertPermittedSubclass() {
                        Iterator<Constraint> it = this.constraints.iterator();
                        while (it.hasNext()) {
                            it.next().assertPermittedSubclass();
                        }
                    }
                }
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default$ValidatingClassVisitor$ValidatingFieldVisitor.class */
            protected class ValidatingFieldVisitor extends FieldVisitor {
                protected ValidatingFieldVisitor(FieldVisitor fieldVisitor) {
                    super(OpenedClassReader.ASM_API, fieldVisitor);
                }

                @Override // net.bytebuddy.jar.asm.FieldVisitor
                public AnnotationVisitor visitAnnotation(String str, boolean z) {
                    ValidatingClassVisitor.this.constraint.assertAnnotation();
                    return super.visitAnnotation(str, z);
                }
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default$ValidatingClassVisitor$ValidatingMethodVisitor.class */
            protected class ValidatingMethodVisitor extends MethodVisitor {
                private final String name;

                protected ValidatingMethodVisitor(MethodVisitor methodVisitor, String str) {
                    super(OpenedClassReader.ASM_API, methodVisitor);
                    this.name = str;
                }

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                @MaybeNull
                public AnnotationVisitor visitAnnotation(String str, boolean z) {
                    ValidatingClassVisitor.this.constraint.assertAnnotation();
                    return super.visitAnnotation(str, z);
                }

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                @MaybeNull
                public AnnotationVisitor visitAnnotationDefault() {
                    ValidatingClassVisitor.this.constraint.assertDefaultValue(this.name);
                    return super.visitAnnotationDefault();
                }

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                @SuppressFBWarnings(value = {"SF_SWITCH_NO_DEFAULT"}, justification = "Fall through to default case is intentional.")
                public void visitLdcInsn(Object obj) {
                    if (obj instanceof Type) {
                        switch (((Type) obj).getSort()) {
                            case 9:
                            case 10:
                                ValidatingClassVisitor.this.constraint.assertTypeInConstantPool();
                                break;
                            case 11:
                                ValidatingClassVisitor.this.constraint.assertMethodTypeInConstantPool();
                                break;
                        }
                    } else if (obj instanceof Handle) {
                        ValidatingClassVisitor.this.constraint.assertHandleInConstantPool();
                    } else if (obj instanceof ConstantDynamic) {
                        ValidatingClassVisitor.this.constraint.assertDynamicValueInConstantPool();
                    }
                    super.visitLdcInsn(obj);
                }

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                public void visitMethodInsn(int i, String str, String str2, String str3, boolean z) {
                    if (z && i == 183) {
                        ValidatingClassVisitor.this.constraint.assertDefaultMethodCall();
                    }
                    super.visitMethodInsn(i, str, str2, str3, z);
                }

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                public void visitInvokeDynamicInsn(String str, String str2, Handle handle, Object... objArr) {
                    ValidatingClassVisitor.this.constraint.assertInvokeDynamic();
                    for (Object obj : objArr) {
                        if (obj instanceof ConstantDynamic) {
                            ValidatingClassVisitor.this.constraint.assertDynamicValueInConstantPool();
                        }
                    }
                    super.visitInvokeDynamicInsn(str, str2, handle, objArr);
                }

                @Override // net.bytebuddy.jar.asm.MethodVisitor
                public void visitJumpInsn(int i, Label label) {
                    if (i == 168) {
                        ValidatingClassVisitor.this.constraint.assertSubRoutine();
                    }
                    super.visitJumpInsn(i, label);
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default$ForInlining.class */
        public static abstract class ForInlining<U> extends Default<U> {

            @AlwaysNull
            private static final FieldVisitor IGNORE_FIELD = null;

            @AlwaysNull
            private static final MethodVisitor IGNORE_METHOD = null;

            @AlwaysNull
            private static final RecordComponentVisitor IGNORE_RECORD_COMPONENT = null;

            @AlwaysNull
            private static final AnnotationVisitor IGNORE_ANNOTATION = null;
            protected final TypeDescription originalType;
            protected final ClassFileLocator classFileLocator;

            protected abstract ClassVisitor writeTo(ClassVisitor classVisitor, TypeInitializer typeInitializer, ContextRegistry contextRegistry, int i, int i2);

            @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default
            public boolean equals(@MaybeNull Object obj) {
                if (!super.equals(obj)) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.originalType.equals(((ForInlining) obj).originalType) && this.classFileLocator.equals(((ForInlining) obj).classFileLocator);
            }

            @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default
            public int hashCode() {
                return (((super.hashCode() * 31) + this.originalType.hashCode()) * 31) + this.classFileLocator.hashCode();
            }

            protected ForInlining(TypeDescription typeDescription, ClassFileVersion classFileVersion, FieldPool fieldPool, RecordComponentPool recordComponentPool, List<? extends DynamicType> list, FieldList<FieldDescription.InDefinedShape> fieldList, MethodList<?> methodList, MethodList<?> methodList2, RecordComponentList<RecordComponentDescription.InDefinedShape> recordComponentList, LoadedTypeInitializer loadedTypeInitializer, TypeInitializer typeInitializer, TypeAttributeAppender typeAttributeAppender, AsmVisitorWrapper asmVisitorWrapper, AnnotationValueFilter.Factory factory, AnnotationRetention annotationRetention, AuxiliaryType.NamingStrategy namingStrategy, Implementation.Context.Factory factory2, TypeValidation typeValidation, ClassWriterStrategy classWriterStrategy, TypePool typePool, TypeDescription typeDescription2, ClassFileLocator classFileLocator) {
                super(typeDescription, classFileVersion, fieldPool, recordComponentPool, list, fieldList, methodList, methodList2, recordComponentList, loadedTypeInitializer, typeInitializer, typeAttributeAppender, asmVisitorWrapper, factory, annotationRetention, namingStrategy, factory2, typeValidation, classWriterStrategy, typePool);
                this.originalType = typeDescription2;
                this.classFileLocator = classFileLocator;
            }

            @Override // net.bytebuddy.dynamic.scaffold.TypeWriter
            public ContextClassVisitor wrap(ClassVisitor classVisitor, int i, int i2) {
                ContextRegistry contextRegistry = new ContextRegistry();
                return new RegistryContextClassVisitor(writeTo(ValidatingClassVisitor.of(classVisitor, this.typeValidation), this.typeInitializer, contextRegistry, this.asmVisitorWrapper.mergeWriter(i), this.asmVisitorWrapper.mergeReader(i2)), contextRegistry);
            }

            @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default
            protected Default<U>.UnresolvedType create(TypeInitializer typeInitializer, ClassDumpAction.Dispatcher dispatcher) {
                try {
                    int mergeWriter = this.asmVisitorWrapper.mergeWriter(0);
                    int mergeReader = this.asmVisitorWrapper.mergeReader(0);
                    byte[] resolve = this.classFileLocator.locate(this.originalType.getName()).resolve();
                    dispatcher.dump(this.instrumentedType, true, resolve);
                    ClassReader of = OpenedClassReader.of(resolve);
                    ClassWriter resolve2 = this.classWriterStrategy.resolve(mergeWriter, this.typePool, of);
                    ContextRegistry contextRegistry = new ContextRegistry();
                    of.accept(writeTo(ValidatingClassVisitor.of(resolve2, this.typeValidation), typeInitializer, contextRegistry, mergeWriter, mergeReader), mergeReader);
                    return new UnresolvedType(resolve2.toByteArray(), contextRegistry.getAuxiliaryTypes());
                } catch (IOException e) {
                    throw new RuntimeException("The class file could not be written", e);
                }
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default$ForInlining$RegistryContextClassVisitor.class */
            protected class RegistryContextClassVisitor extends ContextClassVisitor {
                private final ContextRegistry contextRegistry;

                protected RegistryContextClassVisitor(ClassVisitor classVisitor, ContextRegistry contextRegistry) {
                    super(classVisitor);
                    this.contextRegistry = contextRegistry;
                }

                @Override // net.bytebuddy.utility.visitor.ContextClassVisitor
                public List<DynamicType> getAuxiliaryTypes() {
                    return CompoundList.of((List) ForInlining.this.auxiliaryTypes, (List) this.contextRegistry.getAuxiliaryTypes());
                }

                @Override // net.bytebuddy.utility.visitor.ContextClassVisitor
                public LoadedTypeInitializer getLoadedTypeInitializer() {
                    return ForInlining.this.loadedTypeInitializer;
                }
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default$ForInlining$ContextRegistry.class */
            protected static class ContextRegistry {

                @UnknownNull
                private Implementation.Context.ExtractableView implementationContext;

                protected ContextRegistry() {
                }

                public void setImplementationContext(Implementation.Context.ExtractableView extractableView) {
                    this.implementationContext = extractableView;
                }

                @SuppressFBWarnings(value = {"UWF_FIELD_NOT_INITIALIZED_IN_CONSTRUCTOR"}, justification = "Lazy value definition is intended.")
                public List<DynamicType> getAuxiliaryTypes() {
                    return this.implementationContext.getAuxiliaryTypes();
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default$ForInlining$WithFullProcessing.class */
            public static class WithFullProcessing<V> extends ForInlining<V> {
                private static final Object[] EMPTY = new Object[0];
                private final MethodRegistry.Prepared methodRegistry;
                private final Implementation.Target.Factory implementationTargetFactory;
                private final MethodRebaseResolver methodRebaseResolver;

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ForInlining, net.bytebuddy.dynamic.scaffold.TypeWriter.Default
                public boolean equals(@MaybeNull Object obj) {
                    if (!super.equals(obj)) {
                        return false;
                    }
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.methodRegistry.equals(((WithFullProcessing) obj).methodRegistry) && this.implementationTargetFactory.equals(((WithFullProcessing) obj).implementationTargetFactory) && this.methodRebaseResolver.equals(((WithFullProcessing) obj).methodRebaseResolver);
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ForInlining, net.bytebuddy.dynamic.scaffold.TypeWriter.Default
                public int hashCode() {
                    return (((((super.hashCode() * 31) + this.methodRegistry.hashCode()) * 31) + this.implementationTargetFactory.hashCode()) * 31) + this.methodRebaseResolver.hashCode();
                }

                protected WithFullProcessing(TypeDescription typeDescription, ClassFileVersion classFileVersion, FieldPool fieldPool, RecordComponentPool recordComponentPool, List<? extends DynamicType> list, FieldList<FieldDescription.InDefinedShape> fieldList, MethodList<?> methodList, MethodList<?> methodList2, RecordComponentList<RecordComponentDescription.InDefinedShape> recordComponentList, LoadedTypeInitializer loadedTypeInitializer, TypeInitializer typeInitializer, TypeAttributeAppender typeAttributeAppender, AsmVisitorWrapper asmVisitorWrapper, AnnotationValueFilter.Factory factory, AnnotationRetention annotationRetention, AuxiliaryType.NamingStrategy namingStrategy, Implementation.Context.Factory factory2, TypeValidation typeValidation, ClassWriterStrategy classWriterStrategy, TypePool typePool, TypeDescription typeDescription2, ClassFileLocator classFileLocator, MethodRegistry.Prepared prepared, Implementation.Target.Factory factory3, MethodRebaseResolver methodRebaseResolver) {
                    super(typeDescription, classFileVersion, fieldPool, recordComponentPool, list, fieldList, methodList, methodList2, recordComponentList, loadedTypeInitializer, typeInitializer, typeAttributeAppender, asmVisitorWrapper, factory, annotationRetention, namingStrategy, factory2, typeValidation, classWriterStrategy, typePool, typeDescription2, classFileLocator);
                    this.methodRegistry = prepared;
                    this.implementationTargetFactory = factory3;
                    this.methodRebaseResolver = methodRebaseResolver;
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ForInlining
                protected ClassVisitor writeTo(ClassVisitor classVisitor, TypeInitializer typeInitializer, ContextRegistry contextRegistry, int i, int i2) {
                    RedefinitionClassVisitor redefinitionClassVisitor = new RedefinitionClassVisitor(classVisitor, typeInitializer, contextRegistry, i, i2);
                    return this.originalType.getName().equals(this.instrumentedType.getName()) ? redefinitionClassVisitor : new OpenedClassRemapper(redefinitionClassVisitor, new SimpleRemapper(this.originalType.getInternalName(), this.instrumentedType.getInternalName()));
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default$ForInlining$WithFullProcessing$OpenedClassRemapper.class */
                protected static class OpenedClassRemapper extends ClassRemapper {
                    protected OpenedClassRemapper(ClassVisitor classVisitor, Remapper remapper) {
                        super(OpenedClassReader.ASM_API, classVisitor, remapper);
                    }
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default$ForInlining$WithFullProcessing$InitializationHandler.class */
                protected interface InitializationHandler {
                    void complete(ClassVisitor classVisitor, Implementation.Context.ExtractableView extractableView);

                    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default$ForInlining$WithFullProcessing$InitializationHandler$Creating.class */
                    public static class Creating extends TypeInitializer.Drain.Default implements InitializationHandler {
                        protected Creating(TypeDescription typeDescription, MethodPool methodPool, AnnotationValueFilter.Factory factory) {
                            super(typeDescription, methodPool, factory);
                        }

                        @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ForInlining.WithFullProcessing.InitializationHandler
                        public void complete(ClassVisitor classVisitor, Implementation.Context.ExtractableView extractableView) {
                            extractableView.drain(this, classVisitor, this.annotationValueFilterFactory);
                        }
                    }

                    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default$ForInlining$WithFullProcessing$InitializationHandler$Appending.class */
                    public static abstract class Appending extends MethodVisitor implements TypeInitializer.Drain, InitializationHandler {
                        protected final TypeDescription instrumentedType;
                        protected final MethodPool.Record record;
                        protected final AnnotationValueFilter.Factory annotationValueFilterFactory;
                        protected final FrameWriter frameWriter;
                        protected int stackSize;
                        protected int localVariableLength;

                        protected abstract void onStart();

                        @Override // net.bytebuddy.jar.asm.MethodVisitor
                        public abstract void visitEnd();

                        protected abstract void onComplete(Implementation.Context context);

                        protected Appending(MethodVisitor methodVisitor, TypeDescription typeDescription, MethodPool.Record record, AnnotationValueFilter.Factory factory, boolean z, boolean z2) {
                            super(OpenedClassReader.ASM_API, methodVisitor);
                            this.instrumentedType = typeDescription;
                            this.record = record;
                            this.annotationValueFilterFactory = factory;
                            if (!z) {
                                this.frameWriter = FrameWriter.NoOp.INSTANCE;
                            } else if (z2) {
                                this.frameWriter = FrameWriter.Expanding.INSTANCE;
                            } else {
                                this.frameWriter = new FrameWriter.Active();
                            }
                        }

                        protected static InitializationHandler of(boolean z, MethodVisitor methodVisitor, TypeDescription typeDescription, MethodPool methodPool, AnnotationValueFilter.Factory factory, boolean z2, boolean z3) {
                            if (z) {
                                return withDrain(methodVisitor, typeDescription, methodPool, factory, z2, z3);
                            }
                            return withoutDrain(methodVisitor, typeDescription, methodPool, factory, z2, z3);
                        }

                        private static WithDrain withDrain(MethodVisitor methodVisitor, TypeDescription typeDescription, MethodPool methodPool, AnnotationValueFilter.Factory factory, boolean z, boolean z2) {
                            MethodPool.Record target = methodPool.target(new MethodDescription.Latent.TypeInitializer(typeDescription));
                            return target.getSort().isImplemented() ? new WithDrain.WithActiveRecord(methodVisitor, typeDescription, target, factory, z, z2) : new WithDrain.WithoutActiveRecord(methodVisitor, typeDescription, target, factory, z, z2);
                        }

                        private static WithoutDrain withoutDrain(MethodVisitor methodVisitor, TypeDescription typeDescription, MethodPool methodPool, AnnotationValueFilter.Factory factory, boolean z, boolean z2) {
                            MethodPool.Record target = methodPool.target(new MethodDescription.Latent.TypeInitializer(typeDescription));
                            return target.getSort().isImplemented() ? new WithoutDrain.WithActiveRecord(methodVisitor, typeDescription, target, factory, z, z2) : new WithoutDrain.WithoutActiveRecord(methodVisitor, typeDescription, target, factory);
                        }

                        @Override // net.bytebuddy.jar.asm.MethodVisitor
                        public void visitCode() {
                            this.record.applyAttributes(this.mv, this.annotationValueFilterFactory);
                            super.visitCode();
                            onStart();
                        }

                        @Override // net.bytebuddy.jar.asm.MethodVisitor
                        public void visitFrame(int i, int i2, @MaybeNull Object[] objArr, int i3, @MaybeNull Object[] objArr2) {
                            super.visitFrame(i, i2, objArr, i3, objArr2);
                            this.frameWriter.onFrame(i, i2);
                        }

                        @Override // net.bytebuddy.jar.asm.MethodVisitor
                        public void visitMaxs(int i, int i2) {
                            this.stackSize = i;
                            this.localVariableLength = i2;
                        }

                        @Override // net.bytebuddy.dynamic.scaffold.TypeInitializer.Drain
                        public void apply(ClassVisitor classVisitor, TypeInitializer typeInitializer, Implementation.Context context) {
                            ByteCodeAppender.Size apply = typeInitializer.apply(this.mv, context, new MethodDescription.Latent.TypeInitializer(this.instrumentedType));
                            this.stackSize = Math.max(this.stackSize, apply.getOperandStackSize());
                            this.localVariableLength = Math.max(this.localVariableLength, apply.getLocalVariableSize());
                            onComplete(context);
                        }

                        @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ForInlining.WithFullProcessing.InitializationHandler
                        public void complete(ClassVisitor classVisitor, Implementation.Context.ExtractableView extractableView) {
                            extractableView.drain(this, classVisitor, this.annotationValueFilterFactory);
                            this.mv.visitMaxs(this.stackSize, this.localVariableLength);
                            this.mv.visitEnd();
                        }

                        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default$ForInlining$WithFullProcessing$InitializationHandler$Appending$FrameWriter.class */
                        protected interface FrameWriter {
                            public static final Object[] EMPTY = new Object[0];

                            void onFrame(int i, int i2);

                            void emitFrame(MethodVisitor methodVisitor);

                            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default$ForInlining$WithFullProcessing$InitializationHandler$Appending$FrameWriter$NoOp.class */
                            public enum NoOp implements FrameWriter {
                                INSTANCE;

                                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ForInlining.WithFullProcessing.InitializationHandler.Appending.FrameWriter
                                public final void onFrame(int i, int i2) {
                                }

                                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ForInlining.WithFullProcessing.InitializationHandler.Appending.FrameWriter
                                public final void emitFrame(MethodVisitor methodVisitor) {
                                }
                            }

                            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default$ForInlining$WithFullProcessing$InitializationHandler$Appending$FrameWriter$Expanding.class */
                            public enum Expanding implements FrameWriter {
                                INSTANCE;

                                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ForInlining.WithFullProcessing.InitializationHandler.Appending.FrameWriter
                                public final void onFrame(int i, int i2) {
                                }

                                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ForInlining.WithFullProcessing.InitializationHandler.Appending.FrameWriter
                                public final void emitFrame(MethodVisitor methodVisitor) {
                                    int length = EMPTY.length;
                                    Object[] objArr = EMPTY;
                                    methodVisitor.visitFrame(-1, length, objArr, objArr.length, EMPTY);
                                    methodVisitor.visitInsn(0);
                                }
                            }

                            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default$ForInlining$WithFullProcessing$InitializationHandler$Appending$FrameWriter$Active.class */
                            public static class Active implements FrameWriter {
                                private int currentLocalVariableLength;

                                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ForInlining.WithFullProcessing.InitializationHandler.Appending.FrameWriter
                                public void onFrame(int i, int i2) {
                                    switch (i) {
                                        case -1:
                                        case 0:
                                            this.currentLocalVariableLength = i2;
                                            return;
                                        case 1:
                                            this.currentLocalVariableLength += i2;
                                            return;
                                        case 2:
                                            this.currentLocalVariableLength -= i2;
                                            return;
                                        case 3:
                                        case 4:
                                            return;
                                        default:
                                            throw new IllegalStateException("Unexpected frame type: " + i);
                                    }
                                }

                                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ForInlining.WithFullProcessing.InitializationHandler.Appending.FrameWriter
                                public void emitFrame(MethodVisitor methodVisitor) {
                                    if (this.currentLocalVariableLength == 0) {
                                        int length = EMPTY.length;
                                        Object[] objArr = EMPTY;
                                        methodVisitor.visitFrame(3, length, objArr, objArr.length, EMPTY);
                                    } else if (this.currentLocalVariableLength > 3) {
                                        int length2 = EMPTY.length;
                                        Object[] objArr2 = EMPTY;
                                        methodVisitor.visitFrame(0, length2, objArr2, objArr2.length, EMPTY);
                                    } else {
                                        int i = this.currentLocalVariableLength;
                                        Object[] objArr3 = EMPTY;
                                        methodVisitor.visitFrame(2, i, objArr3, objArr3.length, EMPTY);
                                    }
                                    methodVisitor.visitInsn(0);
                                    this.currentLocalVariableLength = 0;
                                }
                            }
                        }

                        /* JADX INFO: Access modifiers changed from: protected */
                        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default$ForInlining$WithFullProcessing$InitializationHandler$Appending$WithoutDrain.class */
                        public static abstract class WithoutDrain extends Appending {
                            protected WithoutDrain(MethodVisitor methodVisitor, TypeDescription typeDescription, MethodPool.Record record, AnnotationValueFilter.Factory factory, boolean z, boolean z2) {
                                super(methodVisitor, typeDescription, record, factory, z, z2);
                            }

                            @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ForInlining.WithFullProcessing.InitializationHandler.Appending
                            protected void onStart() {
                            }

                            @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ForInlining.WithFullProcessing.InitializationHandler.Appending, net.bytebuddy.jar.asm.MethodVisitor
                            public void visitEnd() {
                            }

                            /* JADX INFO: Access modifiers changed from: protected */
                            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default$ForInlining$WithFullProcessing$InitializationHandler$Appending$WithoutDrain$WithoutActiveRecord.class */
                            public static class WithoutActiveRecord extends WithoutDrain {
                                protected WithoutActiveRecord(MethodVisitor methodVisitor, TypeDescription typeDescription, MethodPool.Record record, AnnotationValueFilter.Factory factory) {
                                    super(methodVisitor, typeDescription, record, factory, false, false);
                                }

                                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ForInlining.WithFullProcessing.InitializationHandler.Appending
                                protected void onComplete(Implementation.Context context) {
                                }
                            }

                            /* JADX INFO: Access modifiers changed from: protected */
                            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default$ForInlining$WithFullProcessing$InitializationHandler$Appending$WithoutDrain$WithActiveRecord.class */
                            public static class WithActiveRecord extends WithoutDrain {
                                private final Label label;

                                protected WithActiveRecord(MethodVisitor methodVisitor, TypeDescription typeDescription, MethodPool.Record record, AnnotationValueFilter.Factory factory, boolean z, boolean z2) {
                                    super(methodVisitor, typeDescription, record, factory, z, z2);
                                    this.label = new Label();
                                }

                                @Override // net.bytebuddy.jar.asm.MethodVisitor
                                public void visitInsn(int i) {
                                    if (i == 177) {
                                        this.mv.visitJumpInsn(167, this.label);
                                    } else {
                                        super.visitInsn(i);
                                    }
                                }

                                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ForInlining.WithFullProcessing.InitializationHandler.Appending
                                protected void onComplete(Implementation.Context context) {
                                    this.mv.visitLabel(this.label);
                                    this.frameWriter.emitFrame(this.mv);
                                    ByteCodeAppender.Size applyCode = this.record.applyCode(this.mv, context);
                                    this.stackSize = Math.max(this.stackSize, applyCode.getOperandStackSize());
                                    this.localVariableLength = Math.max(this.localVariableLength, applyCode.getLocalVariableSize());
                                }
                            }
                        }

                        /* JADX INFO: Access modifiers changed from: protected */
                        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default$ForInlining$WithFullProcessing$InitializationHandler$Appending$WithDrain.class */
                        public static abstract class WithDrain extends Appending {
                            protected final Label appended;
                            protected final Label original;

                            protected abstract void onAfterComplete(Implementation.Context context);

                            protected WithDrain(MethodVisitor methodVisitor, TypeDescription typeDescription, MethodPool.Record record, AnnotationValueFilter.Factory factory, boolean z, boolean z2) {
                                super(methodVisitor, typeDescription, record, factory, z, z2);
                                this.appended = new Label();
                                this.original = new Label();
                            }

                            @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ForInlining.WithFullProcessing.InitializationHandler.Appending
                            protected void onStart() {
                                this.mv.visitJumpInsn(167, this.appended);
                                this.mv.visitLabel(this.original);
                                this.frameWriter.emitFrame(this.mv);
                            }

                            @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ForInlining.WithFullProcessing.InitializationHandler.Appending, net.bytebuddy.jar.asm.MethodVisitor
                            public void visitEnd() {
                                this.mv.visitLabel(this.appended);
                                this.frameWriter.emitFrame(this.mv);
                            }

                            @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ForInlining.WithFullProcessing.InitializationHandler.Appending
                            protected void onComplete(Implementation.Context context) {
                                this.mv.visitJumpInsn(167, this.original);
                                onAfterComplete(context);
                            }

                            /* JADX INFO: Access modifiers changed from: protected */
                            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default$ForInlining$WithFullProcessing$InitializationHandler$Appending$WithDrain$WithoutActiveRecord.class */
                            public static class WithoutActiveRecord extends WithDrain {
                                protected WithoutActiveRecord(MethodVisitor methodVisitor, TypeDescription typeDescription, MethodPool.Record record, AnnotationValueFilter.Factory factory, boolean z, boolean z2) {
                                    super(methodVisitor, typeDescription, record, factory, z, z2);
                                }

                                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ForInlining.WithFullProcessing.InitializationHandler.Appending.WithDrain
                                protected void onAfterComplete(Implementation.Context context) {
                                }
                            }

                            /* JADX INFO: Access modifiers changed from: protected */
                            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default$ForInlining$WithFullProcessing$InitializationHandler$Appending$WithDrain$WithActiveRecord.class */
                            public static class WithActiveRecord extends WithDrain {
                                private final Label label;

                                protected WithActiveRecord(MethodVisitor methodVisitor, TypeDescription typeDescription, MethodPool.Record record, AnnotationValueFilter.Factory factory, boolean z, boolean z2) {
                                    super(methodVisitor, typeDescription, record, factory, z, z2);
                                    this.label = new Label();
                                }

                                @Override // net.bytebuddy.jar.asm.MethodVisitor
                                public void visitInsn(int i) {
                                    if (i == 177) {
                                        this.mv.visitJumpInsn(167, this.label);
                                    } else {
                                        super.visitInsn(i);
                                    }
                                }

                                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ForInlining.WithFullProcessing.InitializationHandler.Appending.WithDrain
                                protected void onAfterComplete(Implementation.Context context) {
                                    this.mv.visitLabel(this.label);
                                    this.frameWriter.emitFrame(this.mv);
                                    ByteCodeAppender.Size applyCode = this.record.applyCode(this.mv, context);
                                    this.stackSize = Math.max(this.stackSize, applyCode.getOperandStackSize());
                                    this.localVariableLength = Math.max(this.localVariableLength, applyCode.getLocalVariableSize());
                                }
                            }
                        }
                    }
                }

                @SuppressFBWarnings(value = {"UWF_FIELD_NOT_INITIALIZED_IN_CONSTRUCTOR"}, justification = "Field access order is implied by ASM.")
                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default$ForInlining$WithFullProcessing$RedefinitionClassVisitor.class */
                protected class RedefinitionClassVisitor extends MetadataAwareClassVisitor {
                    private final TypeInitializer typeInitializer;
                    private final ContextRegistry contextRegistry;
                    private final int writerFlags;
                    private final int readerFlags;
                    private final LinkedHashMap<SignatureKey, FieldDescription> declarableFields;
                    private final LinkedHashMap<SignatureKey, MethodDescription> declarableMethods;
                    private final LinkedHashMap<String, RecordComponentDescription> declarableRecordComponents;
                    private final Set<String> nestMembers;
                    private final LinkedHashMap<String, TypeDescription> declaredTypes;

                    @MaybeNull
                    private final Set<String> permittedSubclasses;

                    @UnknownNull
                    private MethodPool methodPool;

                    @UnknownNull
                    private InitializationHandler initializationHandler;

                    @UnknownNull
                    private Implementation.Context.ExtractableView implementationContext;
                    private boolean retainDeprecationModifiers;

                    protected RedefinitionClassVisitor(ClassVisitor classVisitor, TypeInitializer typeInitializer, ContextRegistry contextRegistry, int i, int i2) {
                        super(OpenedClassReader.ASM_API, classVisitor);
                        this.typeInitializer = typeInitializer;
                        this.contextRegistry = contextRegistry;
                        this.writerFlags = i;
                        this.readerFlags = i2;
                        this.declarableFields = new LinkedHashMap<>((int) Math.ceil(WithFullProcessing.this.fields.size() / 0.75d));
                        for (FieldDescription fieldDescription : WithFullProcessing.this.fields) {
                            this.declarableFields.put(new SignatureKey(fieldDescription.getInternalName(), fieldDescription.getDescriptor()), fieldDescription);
                        }
                        this.declarableMethods = new LinkedHashMap<>((int) Math.ceil(WithFullProcessing.this.instrumentedMethods.size() / 0.75d));
                        Iterator it = WithFullProcessing.this.instrumentedMethods.iterator();
                        while (it.hasNext()) {
                            MethodDescription methodDescription = (MethodDescription) it.next();
                            this.declarableMethods.put(new SignatureKey(methodDescription.getInternalName(), methodDescription.getDescriptor()), methodDescription);
                        }
                        this.declarableRecordComponents = new LinkedHashMap<>((int) Math.ceil(WithFullProcessing.this.recordComponents.size() / 0.75d));
                        for (RecordComponentDescription recordComponentDescription : WithFullProcessing.this.recordComponents) {
                            this.declarableRecordComponents.put(recordComponentDescription.getActualName(), recordComponentDescription);
                        }
                        if (WithFullProcessing.this.instrumentedType.isNestHost()) {
                            this.nestMembers = new LinkedHashSet((int) Math.ceil(WithFullProcessing.this.instrumentedType.getNestMembers().size() / 0.75d));
                            Iterator it2 = WithFullProcessing.this.instrumentedType.getNestMembers().filter(ElementMatchers.not(ElementMatchers.is(WithFullProcessing.this.instrumentedType))).iterator();
                            while (it2.hasNext()) {
                                this.nestMembers.add(((TypeDescription) it2.next()).getInternalName());
                            }
                        } else {
                            this.nestMembers = Collections.emptySet();
                        }
                        this.declaredTypes = new LinkedHashMap<>((int) Math.ceil(WithFullProcessing.this.instrumentedType.getDeclaredTypes().size() / 0.75d));
                        for (TypeDescription typeDescription : WithFullProcessing.this.instrumentedType.getDeclaredTypes()) {
                            this.declaredTypes.put(typeDescription.getInternalName(), typeDescription);
                        }
                        if (WithFullProcessing.this.instrumentedType.isSealed()) {
                            this.permittedSubclasses = new LinkedHashSet((int) Math.ceil(WithFullProcessing.this.instrumentedType.getPermittedSubtypes().size() / 0.75d));
                            Iterator it3 = WithFullProcessing.this.instrumentedType.getPermittedSubtypes().iterator();
                            while (it3.hasNext()) {
                                this.permittedSubclasses.add(((TypeDescription) it3.next()).getInternalName());
                            }
                            return;
                        }
                        this.permittedSubclasses = null;
                    }

                    @Override // net.bytebuddy.jar.asm.ClassVisitor
                    @SuppressFBWarnings(value = {"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"}, justification = "Relying on correlated type properties.")
                    public void visit(int i, int i2, String str, String str2, String str3, String[] strArr) {
                        String internalName;
                        ClassFileVersion ofMinorMajor = ClassFileVersion.ofMinorMajor(i);
                        this.methodPool = WithFullProcessing.this.methodRegistry.compile(WithFullProcessing.this.implementationTargetFactory, ofMinorMajor);
                        this.initializationHandler = new InitializationHandler.Creating(WithFullProcessing.this.instrumentedType, this.methodPool, WithFullProcessing.this.annotationValueFilterFactory);
                        this.implementationContext = WithFullProcessing.this.implementationContextFactory.make(WithFullProcessing.this.instrumentedType, WithFullProcessing.this.auxiliaryTypeNamingStrategy, this.typeInitializer, ofMinorMajor, WithFullProcessing.this.classFileVersion, ((this.writerFlags & 2) == 0 && ofMinorMajor.isAtLeast(ClassFileVersion.JAVA_V6)) ? (this.readerFlags & 8) == 0 ? Implementation.Context.FrameGeneration.GENERATE : Implementation.Context.FrameGeneration.EXPAND : Implementation.Context.FrameGeneration.DISABLED);
                        this.retainDeprecationModifiers = ofMinorMajor.isLessThan(ClassFileVersion.JAVA_V5);
                        this.contextRegistry.setImplementationContext(this.implementationContext);
                        this.cv = WithFullProcessing.this.asmVisitorWrapper.wrap(WithFullProcessing.this.instrumentedType, this.cv, this.implementationContext, WithFullProcessing.this.typePool, WithFullProcessing.this.fields, WithFullProcessing.this.methods, this.writerFlags, this.readerFlags);
                        ClassVisitor classVisitor = this.cv;
                        int actualModifiers = WithFullProcessing.this.instrumentedType.getActualModifiers(((i2 & 32) == 0 || WithFullProcessing.this.instrumentedType.isInterface()) ? false : true) | resolveDeprecationModifiers(i2) | (((i2 & 16) == 0 || !WithFullProcessing.this.instrumentedType.isAnonymousType()) ? 0 : 16);
                        String internalName2 = WithFullProcessing.this.instrumentedType.getInternalName();
                        String genericSignature = TypeDescription.AbstractBase.RAW_TYPES ? str2 : WithFullProcessing.this.instrumentedType.getGenericSignature();
                        if (WithFullProcessing.this.instrumentedType.getSuperClass() == null) {
                            internalName = WithFullProcessing.this.instrumentedType.isInterface() ? TypeDescription.ForLoadedType.of(Object.class).getInternalName() : Default.NO_REFERENCE;
                        } else {
                            internalName = WithFullProcessing.this.instrumentedType.getSuperClass().asErasure().getInternalName();
                        }
                        classVisitor.visit(i, actualModifiers, internalName2, genericSignature, internalName, WithFullProcessing.this.instrumentedType.getInterfaces().asErasures().toInternalNames());
                    }

                    @Override // net.bytebuddy.utility.visitor.MetadataAwareClassVisitor
                    protected void onVisitNestHost(String str) {
                        onNestHost();
                    }

                    @Override // net.bytebuddy.utility.visitor.MetadataAwareClassVisitor
                    protected void onNestHost() {
                        if (!WithFullProcessing.this.instrumentedType.isNestHost()) {
                            this.cv.visitNestHost(WithFullProcessing.this.instrumentedType.getNestHost().getInternalName());
                        }
                    }

                    @Override // net.bytebuddy.utility.visitor.MetadataAwareClassVisitor
                    protected void onVisitPermittedSubclass(String str) {
                        if (this.permittedSubclasses != null && this.permittedSubclasses.remove(str)) {
                            this.cv.visitPermittedSubclass(str);
                        }
                    }

                    @Override // net.bytebuddy.utility.visitor.MetadataAwareClassVisitor
                    protected void onVisitOuterClass(String str, @MaybeNull String str2, @MaybeNull String str3) {
                        try {
                            onOuterType();
                        } catch (Throwable unused) {
                            this.cv.visitOuterClass(str, str2, str3);
                        }
                    }

                    @Override // net.bytebuddy.utility.visitor.MetadataAwareClassVisitor
                    @SuppressFBWarnings(value = {"NP_NULL_ON_SOME_PATH"}, justification = "Relying on correlated type properties.")
                    protected void onOuterType() {
                        MethodDescription.InDefinedShape enclosingMethod = WithFullProcessing.this.instrumentedType.getEnclosingMethod();
                        if (enclosingMethod != null) {
                            this.cv.visitOuterClass(enclosingMethod.getDeclaringType().getInternalName(), enclosingMethod.getInternalName(), enclosingMethod.getDescriptor());
                        } else if (WithFullProcessing.this.instrumentedType.isLocalType() || WithFullProcessing.this.instrumentedType.isAnonymousType()) {
                            this.cv.visitOuterClass(WithFullProcessing.this.instrumentedType.getEnclosingType().getInternalName(), Default.NO_REFERENCE, Default.NO_REFERENCE);
                        }
                    }

                    @Override // net.bytebuddy.utility.visitor.MetadataAwareClassVisitor
                    protected void onAfterAttributes() {
                        WithFullProcessing.this.typeAttributeAppender.apply(this.cv, WithFullProcessing.this.instrumentedType, WithFullProcessing.this.annotationValueFilterFactory.on(WithFullProcessing.this.instrumentedType));
                    }

                    @Override // net.bytebuddy.utility.visitor.MetadataAwareClassVisitor
                    @MaybeNull
                    protected AnnotationVisitor onVisitTypeAnnotation(int i, TypePath typePath, String str, boolean z) {
                        if (!WithFullProcessing.this.annotationRetention.isEnabled()) {
                            return ForInlining.IGNORE_ANNOTATION;
                        }
                        return this.cv.visitTypeAnnotation(i, typePath, str, z);
                    }

                    @Override // net.bytebuddy.utility.visitor.MetadataAwareClassVisitor
                    @MaybeNull
                    protected AnnotationVisitor onVisitAnnotation(String str, boolean z) {
                        if (!WithFullProcessing.this.annotationRetention.isEnabled()) {
                            return ForInlining.IGNORE_ANNOTATION;
                        }
                        return this.cv.visitAnnotation(str, z);
                    }

                    @Override // net.bytebuddy.utility.visitor.MetadataAwareClassVisitor
                    @MaybeNull
                    protected RecordComponentVisitor onVisitRecordComponent(String str, String str2, @MaybeNull String str3) {
                        RecordComponentDescription remove = this.declarableRecordComponents.remove(str);
                        if (remove != null) {
                            RecordComponentPool.Record target = WithFullProcessing.this.recordComponentPool.target(remove);
                            if (!target.isImplicit()) {
                                return redefine(target, str3);
                            }
                        }
                        return this.cv.visitRecordComponent(str, str2, str3);
                    }

                    @MaybeNull
                    protected RecordComponentVisitor redefine(RecordComponentPool.Record record, @MaybeNull String str) {
                        RecordComponentDescription recordComponent = record.getRecordComponent();
                        RecordComponentVisitor visitRecordComponent = this.cv.visitRecordComponent(recordComponent.getActualName(), recordComponent.getDescriptor(), TypeDescription.AbstractBase.RAW_TYPES ? str : recordComponent.getGenericSignature());
                        if (visitRecordComponent == null) {
                            return ForInlining.IGNORE_RECORD_COMPONENT;
                        }
                        return new AttributeObtainingRecordComponentVisitor(visitRecordComponent, record);
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // net.bytebuddy.utility.visitor.MetadataAwareClassVisitor
                    @MaybeNull
                    public FieldVisitor onVisitField(int i, String str, String str2, @MaybeNull String str3, @MaybeNull Object obj) {
                        FieldDescription remove = this.declarableFields.remove(new SignatureKey(str, str2));
                        if (remove != null) {
                            FieldPool.Record target = WithFullProcessing.this.fieldPool.target(remove);
                            if (!target.isImplicit()) {
                                return redefine(target, obj, i, str3);
                            }
                        }
                        return this.cv.visitField(i, str, str2, str3, obj);
                    }

                    @MaybeNull
                    protected FieldVisitor redefine(FieldPool.Record record, @MaybeNull Object obj, int i, @MaybeNull String str) {
                        FieldDescription field = record.getField();
                        FieldVisitor visitField = this.cv.visitField(field.getActualModifiers() | resolveDeprecationModifiers(i), field.getInternalName(), field.getDescriptor(), TypeDescription.AbstractBase.RAW_TYPES ? str : field.getGenericSignature(), record.resolveDefault(obj));
                        if (visitField == null) {
                            return ForInlining.IGNORE_FIELD;
                        }
                        return new AttributeObtainingFieldVisitor(visitField, record);
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // net.bytebuddy.utility.visitor.MetadataAwareClassVisitor
                    @MaybeNull
                    public MethodVisitor onVisitMethod(int i, String str, String str2, @MaybeNull String str3, @MaybeNull String[] strArr) {
                        if (str.equals(MethodDescription.TYPE_INITIALIZER_INTERNAL_NAME)) {
                            MethodVisitor visitMethod = this.cv.visitMethod(i, str, str2, str3, strArr);
                            if (visitMethod == null) {
                                return ForInlining.IGNORE_METHOD;
                            }
                            InitializationHandler of = InitializationHandler.Appending.of(this.implementationContext.isEnabled(), visitMethod, WithFullProcessing.this.instrumentedType, this.methodPool, WithFullProcessing.this.annotationValueFilterFactory, (this.writerFlags & 2) == 0 && this.implementationContext.getClassFileVersion().isAtLeast(ClassFileVersion.JAVA_V6), (this.readerFlags & 8) != 0);
                            this.initializationHandler = of;
                            return (MethodVisitor) of;
                        }
                        MethodDescription remove = this.declarableMethods.remove(new SignatureKey(str, str2));
                        if (remove == null) {
                            return this.cv.visitMethod(i, str, str2, str3, strArr);
                        }
                        return redefine(remove, (i & 1024) != 0, i, str3);
                    }

                    @MaybeNull
                    protected MethodVisitor redefine(MethodDescription methodDescription, boolean z, int i, @MaybeNull String str) {
                        MethodPool.Record target = this.methodPool.target(methodDescription);
                        if (!target.getSort().isDefined()) {
                            return this.cv.visitMethod(methodDescription.getActualModifiers() | resolveDeprecationModifiers(i), methodDescription.getInternalName(), methodDescription.getDescriptor(), TypeDescription.AbstractBase.RAW_TYPES ? str : methodDescription.getGenericSignature(), methodDescription.getExceptionTypes().asErasures().toInternalNames());
                        }
                        MethodDescription method = target.getMethod();
                        MethodVisitor visitMethod = this.cv.visitMethod(ModifierContributor.Resolver.of(Collections.singleton(target.getVisibility())).resolve(method.getActualModifiers(target.getSort().isImplemented())) | resolveDeprecationModifiers(i), method.getInternalName(), method.getDescriptor(), TypeDescription.AbstractBase.RAW_TYPES ? str : method.getGenericSignature(), method.getExceptionTypes().asErasures().toInternalNames());
                        if (visitMethod == null) {
                            return ForInlining.IGNORE_METHOD;
                        }
                        if (z) {
                            return new AttributeObtainingMethodVisitor(visitMethod, target);
                        }
                        if (methodDescription.isNative()) {
                            MethodRebaseResolver.Resolution resolve = WithFullProcessing.this.methodRebaseResolver.resolve(method.asDefined());
                            if (resolve.isRebased()) {
                                MethodVisitor visitMethod2 = super.visitMethod(resolve.getResolvedMethod().getActualModifiers() | resolveDeprecationModifiers(i), resolve.getResolvedMethod().getInternalName(), resolve.getResolvedMethod().getDescriptor(), TypeDescription.AbstractBase.RAW_TYPES ? str : method.getGenericSignature(), resolve.getResolvedMethod().getExceptionTypes().asErasures().toInternalNames());
                                if (visitMethod2 != null) {
                                    visitMethod2.visitEnd();
                                }
                            }
                            return new AttributeObtainingMethodVisitor(visitMethod, target);
                        }
                        return new CodePreservingMethodVisitor(visitMethod, target, WithFullProcessing.this.methodRebaseResolver.resolve(method.asDefined()));
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // net.bytebuddy.utility.visitor.MetadataAwareClassVisitor
                    public void onVisitInnerClass(String str, @MaybeNull String str2, @MaybeNull String str3, int i) {
                        String internalName;
                        String simpleName;
                        if (!str.equals(WithFullProcessing.this.instrumentedType.getInternalName())) {
                            TypeDescription remove = this.declaredTypes.remove(str);
                            if (remove == null) {
                                this.cv.visitInnerClass(str, str2, str3, i);
                                return;
                            }
                            ClassVisitor classVisitor = this.cv;
                            if (!remove.isMemberType() && (str2 == null || str3 != null || !remove.isAnonymousType())) {
                                internalName = Default.NO_REFERENCE;
                            } else {
                                internalName = WithFullProcessing.this.instrumentedType.getInternalName();
                            }
                            if (remove.isAnonymousType()) {
                                simpleName = Default.NO_REFERENCE;
                            } else {
                                simpleName = remove.getSimpleName();
                            }
                            classVisitor.visitInnerClass(str, internalName, simpleName, remove.getModifiers());
                        }
                    }

                    @Override // net.bytebuddy.utility.visitor.MetadataAwareClassVisitor
                    protected void onVisitNestMember(String str) {
                        if (WithFullProcessing.this.instrumentedType.isNestHost() && this.nestMembers.remove(str)) {
                            this.cv.visitNestMember(str);
                        }
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // net.bytebuddy.utility.visitor.MetadataAwareClassVisitor
                    public void onVisitEnd() {
                        String str;
                        String simpleName;
                        Iterator<String> it = this.nestMembers.iterator();
                        while (it.hasNext()) {
                            this.cv.visitNestMember(it.next());
                        }
                        if (this.permittedSubclasses != null) {
                            Iterator<String> it2 = this.permittedSubclasses.iterator();
                            while (it2.hasNext()) {
                                this.cv.visitPermittedSubclass(it2.next());
                            }
                        }
                        TypeDescription declaringType = WithFullProcessing.this.instrumentedType.getDeclaringType();
                        if (declaringType != null) {
                            this.cv.visitInnerClass(WithFullProcessing.this.instrumentedType.getInternalName(), declaringType.getInternalName(), WithFullProcessing.this.instrumentedType.getSimpleName(), WithFullProcessing.this.instrumentedType.getModifiers());
                        } else if (WithFullProcessing.this.instrumentedType.isLocalType()) {
                            this.cv.visitInnerClass(WithFullProcessing.this.instrumentedType.getInternalName(), Default.NO_REFERENCE, WithFullProcessing.this.instrumentedType.getSimpleName(), WithFullProcessing.this.instrumentedType.getModifiers());
                        } else if (WithFullProcessing.this.instrumentedType.isAnonymousType()) {
                            this.cv.visitInnerClass(WithFullProcessing.this.instrumentedType.getInternalName(), Default.NO_REFERENCE, Default.NO_REFERENCE, WithFullProcessing.this.instrumentedType.getModifiers());
                        }
                        for (TypeDescription typeDescription : this.declaredTypes.values()) {
                            ClassVisitor classVisitor = this.cv;
                            String internalName = typeDescription.getInternalName();
                            if (!typeDescription.isMemberType()) {
                                str = Default.NO_REFERENCE;
                            } else {
                                str = WithFullProcessing.this.instrumentedType.getInternalName();
                            }
                            if (typeDescription.isAnonymousType()) {
                                simpleName = Default.NO_REFERENCE;
                            } else {
                                simpleName = typeDescription.getSimpleName();
                            }
                            classVisitor.visitInnerClass(internalName, str, simpleName, typeDescription.getModifiers());
                        }
                        Iterator<RecordComponentDescription> it3 = this.declarableRecordComponents.values().iterator();
                        while (it3.hasNext()) {
                            WithFullProcessing.this.recordComponentPool.target(it3.next()).apply(this.cv, WithFullProcessing.this.annotationValueFilterFactory);
                        }
                        Iterator<FieldDescription> it4 = this.declarableFields.values().iterator();
                        while (it4.hasNext()) {
                            WithFullProcessing.this.fieldPool.target(it4.next()).apply(this.cv, WithFullProcessing.this.annotationValueFilterFactory);
                        }
                        Iterator<MethodDescription> it5 = this.declarableMethods.values().iterator();
                        while (it5.hasNext()) {
                            this.methodPool.target(it5.next()).apply(this.cv, this.implementationContext, WithFullProcessing.this.annotationValueFilterFactory);
                        }
                        this.initializationHandler.complete(this.cv, this.implementationContext);
                        this.cv.visitEnd();
                    }

                    private int resolveDeprecationModifiers(int i) {
                        return (!this.retainDeprecationModifiers || (i & 131072) == 0) ? 0 : 131072;
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default$ForInlining$WithFullProcessing$RedefinitionClassVisitor$AttributeObtainingFieldVisitor.class */
                    public class AttributeObtainingFieldVisitor extends FieldVisitor {
                        private final FieldPool.Record record;

                        protected AttributeObtainingFieldVisitor(FieldVisitor fieldVisitor, FieldPool.Record record) {
                            super(OpenedClassReader.ASM_API, fieldVisitor);
                            this.record = record;
                        }

                        @Override // net.bytebuddy.jar.asm.FieldVisitor
                        @MaybeNull
                        public AnnotationVisitor visitTypeAnnotation(int i, @MaybeNull TypePath typePath, String str, boolean z) {
                            if (!WithFullProcessing.this.annotationRetention.isEnabled()) {
                                return ForInlining.IGNORE_ANNOTATION;
                            }
                            return super.visitTypeAnnotation(i, typePath, str, z);
                        }

                        @Override // net.bytebuddy.jar.asm.FieldVisitor
                        @MaybeNull
                        public AnnotationVisitor visitAnnotation(String str, boolean z) {
                            if (!WithFullProcessing.this.annotationRetention.isEnabled()) {
                                return ForInlining.IGNORE_ANNOTATION;
                            }
                            return super.visitAnnotation(str, z);
                        }

                        @Override // net.bytebuddy.jar.asm.FieldVisitor
                        public void visitEnd() {
                            this.record.apply(this.fv, WithFullProcessing.this.annotationValueFilterFactory);
                            super.visitEnd();
                        }
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default$ForInlining$WithFullProcessing$RedefinitionClassVisitor$AttributeObtainingRecordComponentVisitor.class */
                    public class AttributeObtainingRecordComponentVisitor extends RecordComponentVisitor {
                        private final RecordComponentPool.Record record;

                        protected AttributeObtainingRecordComponentVisitor(RecordComponentVisitor recordComponentVisitor, RecordComponentPool.Record record) {
                            super(OpenedClassReader.ASM_API, recordComponentVisitor);
                            this.record = record;
                        }

                        @Override // net.bytebuddy.jar.asm.RecordComponentVisitor
                        public AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String str, boolean z) {
                            if (!WithFullProcessing.this.annotationRetention.isEnabled()) {
                                return ForInlining.IGNORE_ANNOTATION;
                            }
                            return super.visitTypeAnnotation(i, typePath, str, z);
                        }

                        @Override // net.bytebuddy.jar.asm.RecordComponentVisitor
                        public AnnotationVisitor visitAnnotation(String str, boolean z) {
                            if (!WithFullProcessing.this.annotationRetention.isEnabled()) {
                                return ForInlining.IGNORE_ANNOTATION;
                            }
                            return super.visitAnnotation(str, z);
                        }

                        @Override // net.bytebuddy.jar.asm.RecordComponentVisitor
                        public void visitEnd() {
                            this.record.apply(getDelegate(), WithFullProcessing.this.annotationValueFilterFactory);
                            super.visitEnd();
                        }
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default$ForInlining$WithFullProcessing$RedefinitionClassVisitor$CodePreservingMethodVisitor.class */
                    public class CodePreservingMethodVisitor extends MethodVisitor {
                        private final MethodVisitor actualMethodVisitor;
                        private final MethodPool.Record record;
                        private final MethodRebaseResolver.Resolution resolution;

                        protected CodePreservingMethodVisitor(MethodVisitor methodVisitor, MethodPool.Record record, MethodRebaseResolver.Resolution resolution) {
                            super(OpenedClassReader.ASM_API, methodVisitor);
                            this.actualMethodVisitor = methodVisitor;
                            this.record = record;
                            this.resolution = resolution;
                            record.applyHead(methodVisitor);
                        }

                        @Override // net.bytebuddy.jar.asm.MethodVisitor
                        @MaybeNull
                        public AnnotationVisitor visitAnnotationDefault() {
                            return ForInlining.IGNORE_ANNOTATION;
                        }

                        @Override // net.bytebuddy.jar.asm.MethodVisitor
                        @MaybeNull
                        public AnnotationVisitor visitTypeAnnotation(int i, @MaybeNull TypePath typePath, String str, boolean z) {
                            if (!WithFullProcessing.this.annotationRetention.isEnabled()) {
                                return ForInlining.IGNORE_ANNOTATION;
                            }
                            return super.visitTypeAnnotation(i, typePath, str, z);
                        }

                        @Override // net.bytebuddy.jar.asm.MethodVisitor
                        @MaybeNull
                        public AnnotationVisitor visitAnnotation(String str, boolean z) {
                            if (!WithFullProcessing.this.annotationRetention.isEnabled()) {
                                return ForInlining.IGNORE_ANNOTATION;
                            }
                            return super.visitAnnotation(str, z);
                        }

                        @Override // net.bytebuddy.jar.asm.MethodVisitor
                        public void visitAnnotableParameterCount(int i, boolean z) {
                            if (WithFullProcessing.this.annotationRetention.isEnabled()) {
                                super.visitAnnotableParameterCount(i, z);
                            }
                        }

                        @Override // net.bytebuddy.jar.asm.MethodVisitor
                        @MaybeNull
                        public AnnotationVisitor visitParameterAnnotation(int i, String str, boolean z) {
                            if (!WithFullProcessing.this.annotationRetention.isEnabled()) {
                                return ForInlining.IGNORE_ANNOTATION;
                            }
                            return super.visitParameterAnnotation(i, str, z);
                        }

                        @Override // net.bytebuddy.jar.asm.MethodVisitor
                        public void visitCode() {
                            this.record.applyBody(this.actualMethodVisitor, RedefinitionClassVisitor.this.implementationContext, WithFullProcessing.this.annotationValueFilterFactory);
                            this.actualMethodVisitor.visitEnd();
                            if (this.resolution.isRebased()) {
                                this.mv = RedefinitionClassVisitor.this.cv.visitMethod(this.resolution.getResolvedMethod().getActualModifiers(), this.resolution.getResolvedMethod().getInternalName(), this.resolution.getResolvedMethod().getDescriptor(), this.resolution.getResolvedMethod().getGenericSignature(), this.resolution.getResolvedMethod().getExceptionTypes().asErasures().toInternalNames());
                                super.visitCode();
                                if (!this.resolution.getAppendedParameters().isEmpty() && RedefinitionClassVisitor.this.implementationContext.getFrameGeneration().isActive()) {
                                    if (RedefinitionClassVisitor.this.implementationContext.getFrameGeneration() == Implementation.Context.FrameGeneration.GENERATE && this.resolution.getAppendedParameters().size() < 4) {
                                        super.visitFrame(2, this.resolution.getAppendedParameters().size(), WithFullProcessing.EMPTY, WithFullProcessing.EMPTY.length, WithFullProcessing.EMPTY);
                                    } else {
                                        Object[] objArr = new Object[(this.resolution.getResolvedMethod().getParameters().size() - this.resolution.getAppendedParameters().size()) + 1];
                                        objArr[0] = Opcodes.UNINITIALIZED_THIS;
                                        for (int i = 1; i < objArr.length; i++) {
                                            TypeDescription.Generic type = ((ParameterDescription.InDefinedShape) this.resolution.getResolvedMethod().getParameters().get(i - 1)).getType();
                                            if (type.represents(Boolean.TYPE) || type.represents(Byte.TYPE) || type.represents(Short.TYPE) || type.represents(Character.TYPE) || type.represents(Integer.TYPE)) {
                                                objArr[i] = Opcodes.INTEGER;
                                            } else if (type.represents(Long.TYPE)) {
                                                objArr[i] = Opcodes.LONG;
                                            } else if (type.represents(Float.TYPE)) {
                                                objArr[i] = Opcodes.FLOAT;
                                            } else if (type.represents(Double.TYPE)) {
                                                objArr[i] = Opcodes.DOUBLE;
                                            } else {
                                                objArr[i] = type.asErasure().getInternalName();
                                            }
                                        }
                                        super.visitFrame((RedefinitionClassVisitor.this.readerFlags & 8) == 0 ? 0 : -1, objArr.length, objArr, WithFullProcessing.EMPTY.length, WithFullProcessing.EMPTY);
                                    }
                                    super.visitInsn(0);
                                    return;
                                }
                                return;
                            }
                            this.mv = ForInlining.IGNORE_METHOD;
                            super.visitCode();
                        }

                        @Override // net.bytebuddy.jar.asm.MethodVisitor
                        public void visitMaxs(int i, int i2) {
                            super.visitMaxs(i, Math.max(i2, this.resolution.getResolvedMethod().getStackSize()));
                        }
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default$ForInlining$WithFullProcessing$RedefinitionClassVisitor$AttributeObtainingMethodVisitor.class */
                    public class AttributeObtainingMethodVisitor extends MethodVisitor {
                        private final MethodVisitor actualMethodVisitor;
                        private final MethodPool.Record record;

                        protected AttributeObtainingMethodVisitor(MethodVisitor methodVisitor, MethodPool.Record record) {
                            super(OpenedClassReader.ASM_API, methodVisitor);
                            this.actualMethodVisitor = methodVisitor;
                            this.record = record;
                            record.applyHead(methodVisitor);
                        }

                        @Override // net.bytebuddy.jar.asm.MethodVisitor
                        @MaybeNull
                        public AnnotationVisitor visitAnnotationDefault() {
                            return ForInlining.IGNORE_ANNOTATION;
                        }

                        @Override // net.bytebuddy.jar.asm.MethodVisitor
                        @MaybeNull
                        public AnnotationVisitor visitTypeAnnotation(int i, @MaybeNull TypePath typePath, String str, boolean z) {
                            if (!WithFullProcessing.this.annotationRetention.isEnabled()) {
                                return ForInlining.IGNORE_ANNOTATION;
                            }
                            return super.visitTypeAnnotation(i, typePath, str, z);
                        }

                        @Override // net.bytebuddy.jar.asm.MethodVisitor
                        @MaybeNull
                        public AnnotationVisitor visitAnnotation(String str, boolean z) {
                            if (!WithFullProcessing.this.annotationRetention.isEnabled()) {
                                return ForInlining.IGNORE_ANNOTATION;
                            }
                            return super.visitAnnotation(str, z);
                        }

                        @Override // net.bytebuddy.jar.asm.MethodVisitor
                        public void visitAnnotableParameterCount(int i, boolean z) {
                            if (WithFullProcessing.this.annotationRetention.isEnabled()) {
                                super.visitAnnotableParameterCount(i, z);
                            }
                        }

                        @Override // net.bytebuddy.jar.asm.MethodVisitor
                        @MaybeNull
                        public AnnotationVisitor visitParameterAnnotation(int i, String str, boolean z) {
                            if (!WithFullProcessing.this.annotationRetention.isEnabled()) {
                                return ForInlining.IGNORE_ANNOTATION;
                            }
                            return super.visitParameterAnnotation(i, str, z);
                        }

                        @Override // net.bytebuddy.jar.asm.MethodVisitor
                        public void visitCode() {
                            this.mv = ForInlining.IGNORE_METHOD;
                        }

                        @Override // net.bytebuddy.jar.asm.MethodVisitor
                        public void visitEnd() {
                            this.record.applyBody(this.actualMethodVisitor, RedefinitionClassVisitor.this.implementationContext, WithFullProcessing.this.annotationValueFilterFactory);
                            this.actualMethodVisitor.visitEnd();
                        }
                    }
                }
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default$ForInlining$WithDecorationOnly.class */
            protected static class WithDecorationOnly<V> extends ForInlining<V> {
                protected WithDecorationOnly(TypeDescription typeDescription, ClassFileVersion classFileVersion, List<? extends DynamicType> list, MethodList<?> methodList, TypeAttributeAppender typeAttributeAppender, AsmVisitorWrapper asmVisitorWrapper, AnnotationValueFilter.Factory factory, AnnotationRetention annotationRetention, AuxiliaryType.NamingStrategy namingStrategy, Implementation.Context.Factory factory2, TypeValidation typeValidation, ClassWriterStrategy classWriterStrategy, TypePool typePool, ClassFileLocator classFileLocator) {
                    super(typeDescription, classFileVersion, FieldPool.Disabled.INSTANCE, RecordComponentPool.Disabled.INSTANCE, list, new LazyFieldList(typeDescription), methodList, new MethodList.Empty(), new RecordComponentList.Empty(), LoadedTypeInitializer.NoOp.INSTANCE, TypeInitializer.None.INSTANCE, typeAttributeAppender, asmVisitorWrapper, factory, annotationRetention, namingStrategy, factory2, typeValidation, classWriterStrategy, typePool, typeDescription, classFileLocator);
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ForInlining
                protected ClassVisitor writeTo(ClassVisitor classVisitor, TypeInitializer typeInitializer, ContextRegistry contextRegistry, int i, int i2) {
                    if (typeInitializer.isDefined()) {
                        throw new UnsupportedOperationException("Cannot apply a type initializer for a decoration");
                    }
                    return new DecorationClassVisitor(classVisitor, contextRegistry, i, i2);
                }

                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default$ForInlining$WithDecorationOnly$LazyFieldList.class */
                protected static class LazyFieldList extends FieldList.AbstractBase<FieldDescription.InDefinedShape> {
                    private final TypeDescription instrumentedType;

                    protected LazyFieldList(TypeDescription typeDescription) {
                        this.instrumentedType = typeDescription;
                    }

                    @Override // java.util.AbstractList, java.util.List
                    public FieldDescription.InDefinedShape get(int i) {
                        return (FieldDescription.InDefinedShape) this.instrumentedType.getDeclaredFields().get(i);
                    }

                    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
                    public int size() {
                        return this.instrumentedType.getDeclaredFields().size();
                    }
                }

                @SuppressFBWarnings(value = {"UWF_FIELD_NOT_INITIALIZED_IN_CONSTRUCTOR"}, justification = "Field access order is implied by ASM.")
                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default$ForInlining$WithDecorationOnly$DecorationClassVisitor.class */
                protected class DecorationClassVisitor extends MetadataAwareClassVisitor implements TypeInitializer.Drain {
                    private final ContextRegistry contextRegistry;
                    private final int writerFlags;
                    private final int readerFlags;

                    @UnknownNull
                    private Implementation.Context.ExtractableView implementationContext;

                    protected DecorationClassVisitor(ClassVisitor classVisitor, ContextRegistry contextRegistry, int i, int i2) {
                        super(OpenedClassReader.ASM_API, classVisitor);
                        this.contextRegistry = contextRegistry;
                        this.writerFlags = i;
                        this.readerFlags = i2;
                    }

                    @Override // net.bytebuddy.jar.asm.ClassVisitor
                    public void visit(int i, int i2, String str, String str2, String str3, String[] strArr) {
                        ClassFileVersion ofMinorMajor = ClassFileVersion.ofMinorMajor(i);
                        this.implementationContext = WithDecorationOnly.this.implementationContextFactory.make(WithDecorationOnly.this.instrumentedType, WithDecorationOnly.this.auxiliaryTypeNamingStrategy, WithDecorationOnly.this.typeInitializer, ofMinorMajor, WithDecorationOnly.this.classFileVersion, ((this.writerFlags & 2) == 0 && ofMinorMajor.isAtLeast(ClassFileVersion.JAVA_V6)) ? (this.readerFlags & 8) == 0 ? Implementation.Context.FrameGeneration.GENERATE : Implementation.Context.FrameGeneration.EXPAND : Implementation.Context.FrameGeneration.DISABLED);
                        this.contextRegistry.setImplementationContext(this.implementationContext);
                        this.cv = WithDecorationOnly.this.asmVisitorWrapper.wrap(WithDecorationOnly.this.instrumentedType, this.cv, this.implementationContext, WithDecorationOnly.this.typePool, WithDecorationOnly.this.fields, WithDecorationOnly.this.methods, this.writerFlags, this.readerFlags);
                        this.cv.visit(i, i2, str, str2, str3, strArr);
                    }

                    @Override // net.bytebuddy.utility.visitor.MetadataAwareClassVisitor
                    @MaybeNull
                    protected AnnotationVisitor onVisitTypeAnnotation(int i, TypePath typePath, String str, boolean z) {
                        if (!WithDecorationOnly.this.annotationRetention.isEnabled()) {
                            return ForInlining.IGNORE_ANNOTATION;
                        }
                        return this.cv.visitTypeAnnotation(i, typePath, str, z);
                    }

                    @Override // net.bytebuddy.utility.visitor.MetadataAwareClassVisitor
                    @MaybeNull
                    protected AnnotationVisitor onVisitAnnotation(String str, boolean z) {
                        if (!WithDecorationOnly.this.annotationRetention.isEnabled()) {
                            return ForInlining.IGNORE_ANNOTATION;
                        }
                        return this.cv.visitAnnotation(str, z);
                    }

                    @Override // net.bytebuddy.utility.visitor.MetadataAwareClassVisitor
                    protected void onAfterAttributes() {
                        WithDecorationOnly.this.typeAttributeAppender.apply(this.cv, WithDecorationOnly.this.instrumentedType, WithDecorationOnly.this.annotationValueFilterFactory.on(WithDecorationOnly.this.instrumentedType));
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // net.bytebuddy.utility.visitor.MetadataAwareClassVisitor
                    public void onVisitEnd() {
                        this.implementationContext.drain(this, this.cv, WithDecorationOnly.this.annotationValueFilterFactory);
                        this.cv.visitEnd();
                    }

                    @Override // net.bytebuddy.dynamic.scaffold.TypeInitializer.Drain
                    public void apply(ClassVisitor classVisitor, TypeInitializer typeInitializer, Implementation.Context context) {
                    }
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default$ForCreation.class */
        public static class ForCreation<U> extends Default<U> {
            private final MethodPool methodPool;

            @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default
            public boolean equals(@MaybeNull Object obj) {
                if (!super.equals(obj)) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.methodPool.equals(((ForCreation) obj).methodPool);
            }

            @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default
            public int hashCode() {
                return (super.hashCode() * 31) + this.methodPool.hashCode();
            }

            protected ForCreation(TypeDescription typeDescription, ClassFileVersion classFileVersion, FieldPool fieldPool, MethodPool methodPool, RecordComponentPool recordComponentPool, List<? extends DynamicType> list, FieldList<FieldDescription.InDefinedShape> fieldList, MethodList<?> methodList, MethodList<?> methodList2, RecordComponentList<RecordComponentDescription.InDefinedShape> recordComponentList, LoadedTypeInitializer loadedTypeInitializer, TypeInitializer typeInitializer, TypeAttributeAppender typeAttributeAppender, AsmVisitorWrapper asmVisitorWrapper, AnnotationValueFilter.Factory factory, AnnotationRetention annotationRetention, AuxiliaryType.NamingStrategy namingStrategy, Implementation.Context.Factory factory2, TypeValidation typeValidation, ClassWriterStrategy classWriterStrategy, TypePool typePool) {
                super(typeDescription, classFileVersion, fieldPool, recordComponentPool, list, fieldList, methodList, methodList2, recordComponentList, loadedTypeInitializer, typeInitializer, typeAttributeAppender, asmVisitorWrapper, factory, annotationRetention, namingStrategy, factory2, typeValidation, classWriterStrategy, typePool);
                this.methodPool = methodPool;
            }

            @Override // net.bytebuddy.dynamic.scaffold.TypeWriter
            public ContextClassVisitor wrap(ClassVisitor classVisitor, int i, int i2) {
                Implementation.Context.ExtractableView make = this.implementationContextFactory.make(this.instrumentedType, this.auxiliaryTypeNamingStrategy, this.typeInitializer, this.classFileVersion, this.classFileVersion, ((i & 2) == 0 && this.classFileVersion.isAtLeast(ClassFileVersion.JAVA_V6)) ? (i2 & 8) == 0 ? Implementation.Context.FrameGeneration.GENERATE : Implementation.Context.FrameGeneration.EXPAND : Implementation.Context.FrameGeneration.DISABLED);
                return new ImplementationContextClassVisitor(new CreationClassVisitor(this.asmVisitorWrapper.wrap(this.instrumentedType, ValidatingClassVisitor.of(classVisitor, this.typeValidation), make, this.typePool, this.fields, this.methods, this.asmVisitorWrapper.mergeWriter(i), this.asmVisitorWrapper.mergeReader(i2)), make), make);
            }

            @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default
            @SuppressFBWarnings(value = {"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"}, justification = "Relying on correlated type properties.")
            protected Default<U>.UnresolvedType create(TypeInitializer typeInitializer, ClassDumpAction.Dispatcher dispatcher) {
                TypeDescription asErasure;
                String str;
                String simpleName;
                int mergeWriter = this.asmVisitorWrapper.mergeWriter(0);
                int mergeReader = this.asmVisitorWrapper.mergeReader(0);
                ClassWriter resolve = this.classWriterStrategy.resolve(mergeWriter, this.typePool);
                Implementation.Context.ExtractableView make = this.implementationContextFactory.make(this.instrumentedType, this.auxiliaryTypeNamingStrategy, typeInitializer, this.classFileVersion, this.classFileVersion, ((mergeWriter & 2) == 0 && this.classFileVersion.isAtLeast(ClassFileVersion.JAVA_V6)) ? (mergeReader & 8) == 0 ? Implementation.Context.FrameGeneration.GENERATE : Implementation.Context.FrameGeneration.EXPAND : Implementation.Context.FrameGeneration.DISABLED);
                ClassVisitor wrap = this.asmVisitorWrapper.wrap(this.instrumentedType, ValidatingClassVisitor.of(resolve, this.typeValidation), make, this.typePool, this.fields, this.methods, mergeWriter, mergeReader);
                int minorMajorVersion = this.classFileVersion.getMinorMajorVersion();
                int actualModifiers = this.instrumentedType.getActualModifiers(!this.instrumentedType.isInterface());
                String internalName = this.instrumentedType.getInternalName();
                String genericSignature = this.instrumentedType.getGenericSignature();
                if (this.instrumentedType.getSuperClass() == null) {
                    asErasure = TypeDescription.ForLoadedType.of(Object.class);
                } else {
                    asErasure = this.instrumentedType.getSuperClass().asErasure();
                }
                wrap.visit(minorMajorVersion, actualModifiers, internalName, genericSignature, asErasure.getInternalName(), this.instrumentedType.getInterfaces().asErasures().toInternalNames());
                if (!this.instrumentedType.isNestHost()) {
                    wrap.visitNestHost(this.instrumentedType.getNestHost().getInternalName());
                }
                MethodDescription.InDefinedShape enclosingMethod = this.instrumentedType.getEnclosingMethod();
                if (enclosingMethod != null) {
                    wrap.visitOuterClass(enclosingMethod.getDeclaringType().getInternalName(), enclosingMethod.getInternalName(), enclosingMethod.getDescriptor());
                } else if (this.instrumentedType.isLocalType() || this.instrumentedType.isAnonymousType()) {
                    wrap.visitOuterClass(this.instrumentedType.getEnclosingType().getInternalName(), Default.NO_REFERENCE, Default.NO_REFERENCE);
                }
                this.typeAttributeAppender.apply(wrap, this.instrumentedType, this.annotationValueFilterFactory.on(this.instrumentedType));
                if (this.instrumentedType.isNestHost()) {
                    Iterator it = this.instrumentedType.getNestMembers().filter(ElementMatchers.not(ElementMatchers.is(this.instrumentedType))).iterator();
                    while (it.hasNext()) {
                        wrap.visitNestMember(((TypeDescription) it.next()).getInternalName());
                    }
                }
                Iterator it2 = this.instrumentedType.getPermittedSubtypes().iterator();
                while (it2.hasNext()) {
                    wrap.visitPermittedSubclass(((TypeDescription) it2.next()).getInternalName());
                }
                TypeDescription declaringType = this.instrumentedType.getDeclaringType();
                if (declaringType != null) {
                    wrap.visitInnerClass(this.instrumentedType.getInternalName(), declaringType.getInternalName(), this.instrumentedType.getSimpleName(), this.instrumentedType.getModifiers());
                } else if (this.instrumentedType.isLocalType()) {
                    wrap.visitInnerClass(this.instrumentedType.getInternalName(), Default.NO_REFERENCE, this.instrumentedType.getSimpleName(), this.instrumentedType.getModifiers());
                } else if (this.instrumentedType.isAnonymousType()) {
                    wrap.visitInnerClass(this.instrumentedType.getInternalName(), Default.NO_REFERENCE, Default.NO_REFERENCE, this.instrumentedType.getModifiers());
                }
                for (TypeDescription typeDescription : this.instrumentedType.getDeclaredTypes()) {
                    String internalName2 = typeDescription.getInternalName();
                    if (!typeDescription.isMemberType()) {
                        str = Default.NO_REFERENCE;
                    } else {
                        str = this.instrumentedType.getInternalName();
                    }
                    if (typeDescription.isAnonymousType()) {
                        simpleName = Default.NO_REFERENCE;
                    } else {
                        simpleName = typeDescription.getSimpleName();
                    }
                    wrap.visitInnerClass(internalName2, str, simpleName, typeDescription.getModifiers());
                }
                Iterator it3 = this.recordComponents.iterator();
                while (it3.hasNext()) {
                    this.recordComponentPool.target((RecordComponentDescription) it3.next()).apply(wrap, this.annotationValueFilterFactory);
                }
                Iterator it4 = this.fields.iterator();
                while (it4.hasNext()) {
                    this.fieldPool.target((FieldDescription) it4.next()).apply(wrap, this.annotationValueFilterFactory);
                }
                Iterator it5 = this.instrumentedMethods.iterator();
                while (it5.hasNext()) {
                    this.methodPool.target((MethodDescription) it5.next()).apply(wrap, make, this.annotationValueFilterFactory);
                }
                make.drain(new TypeInitializer.Drain.Default(this.instrumentedType, this.methodPool, this.annotationValueFilterFactory), wrap, this.annotationValueFilterFactory);
                wrap.visitEnd();
                return new UnresolvedType(resolve.toByteArray(), make.getAuxiliaryTypes());
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default$ForCreation$CreationClassVisitor.class */
            protected class CreationClassVisitor extends MetadataAwareClassVisitor {
                private final Implementation.Context.ExtractableView implementationContext;
                private final Set<String> declaredTypes;
                private final Set<SignatureKey> visitedFields;
                private final Set<SignatureKey> visitedMethods;

                protected CreationClassVisitor(ClassVisitor classVisitor, Implementation.Context.ExtractableView extractableView) {
                    super(OpenedClassReader.ASM_API, classVisitor);
                    this.declaredTypes = new HashSet();
                    this.visitedFields = new HashSet();
                    this.visitedMethods = new HashSet();
                    this.implementationContext = extractableView;
                }

                @Override // net.bytebuddy.utility.visitor.MetadataAwareClassVisitor
                protected void onAfterAttributes() {
                    ForCreation.this.typeAttributeAppender.apply(this.cv, ForCreation.this.instrumentedType, ForCreation.this.annotationValueFilterFactory.on(ForCreation.this.instrumentedType));
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // net.bytebuddy.utility.visitor.MetadataAwareClassVisitor
                public void onVisitInnerClass(String str, @MaybeNull String str2, @MaybeNull String str3, int i) {
                    this.declaredTypes.add(str);
                    super.onVisitInnerClass(str, str2, str3, i);
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // net.bytebuddy.utility.visitor.MetadataAwareClassVisitor
                public FieldVisitor onVisitField(int i, String str, String str2, @MaybeNull String str3, @MaybeNull Object obj) {
                    this.visitedFields.add(new SignatureKey(str, str2));
                    return super.onVisitField(i, str, str2, str3, obj);
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // net.bytebuddy.utility.visitor.MetadataAwareClassVisitor
                public MethodVisitor onVisitMethod(int i, String str, String str2, @MaybeNull String str3, @MaybeNull String[] strArr) {
                    this.visitedMethods.add(new SignatureKey(str, str2));
                    return super.onVisitMethod(i, str, str2, str3, strArr);
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // net.bytebuddy.utility.visitor.MetadataAwareClassVisitor
                public void onVisitEnd() {
                    String str;
                    String simpleName;
                    for (TypeDescription typeDescription : ForCreation.this.instrumentedType.getDeclaredTypes()) {
                        if (!this.declaredTypes.contains(typeDescription.getInternalName())) {
                            ClassVisitor classVisitor = this.cv;
                            String internalName = typeDescription.getInternalName();
                            if (!typeDescription.isMemberType()) {
                                str = Default.NO_REFERENCE;
                            } else {
                                str = ForCreation.this.instrumentedType.getInternalName();
                            }
                            if (typeDescription.isAnonymousType()) {
                                simpleName = Default.NO_REFERENCE;
                            } else {
                                simpleName = typeDescription.getSimpleName();
                            }
                            classVisitor.visitInnerClass(internalName, str, simpleName, typeDescription.getModifiers());
                        }
                    }
                    for (FieldDescription fieldDescription : ForCreation.this.fields) {
                        if (!this.visitedFields.contains(new SignatureKey(fieldDescription.getName(), fieldDescription.getDescriptor()))) {
                            ForCreation.this.fieldPool.target(fieldDescription).apply(this.cv, ForCreation.this.annotationValueFilterFactory);
                        }
                    }
                    Iterator it = ForCreation.this.instrumentedMethods.iterator();
                    while (it.hasNext()) {
                        MethodDescription methodDescription = (MethodDescription) it.next();
                        if (!this.visitedMethods.contains(new SignatureKey(methodDescription.getInternalName(), methodDescription.getDescriptor()))) {
                            ForCreation.this.methodPool.target(methodDescription).apply(this.cv, this.implementationContext, ForCreation.this.annotationValueFilterFactory);
                        }
                    }
                    this.implementationContext.drain(new TypeInitializer.Drain.Default(ForCreation.this.instrumentedType, ForCreation.this.methodPool, ForCreation.this.annotationValueFilterFactory), this.cv, ForCreation.this.annotationValueFilterFactory);
                    super.onVisitEnd();
                }
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default$ForCreation$ImplementationContextClassVisitor.class */
            protected class ImplementationContextClassVisitor extends ContextClassVisitor {
                private final Implementation.Context.ExtractableView implementationContext;

                protected ImplementationContextClassVisitor(ClassVisitor classVisitor, Implementation.Context.ExtractableView extractableView) {
                    super(classVisitor);
                    this.implementationContext = extractableView;
                }

                @Override // net.bytebuddy.utility.visitor.ContextClassVisitor
                public List<DynamicType> getAuxiliaryTypes() {
                    return CompoundList.of((List) ForCreation.this.auxiliaryTypes, (List) this.implementationContext.getAuxiliaryTypes());
                }

                @Override // net.bytebuddy.utility.visitor.ContextClassVisitor
                public LoadedTypeInitializer getLoadedTypeInitializer() {
                    return ForCreation.this.loadedTypeInitializer;
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default$ClassDumpAction.class */
        protected static class ClassDumpAction implements PrivilegedExceptionAction<Void> {

            @AlwaysNull
            private static final Void NOTHING = null;
            private final String target;
            private final TypeDescription instrumentedType;
            private final boolean original;
            private final long suffix;
            private final byte[] binaryRepresentation;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.original == ((ClassDumpAction) obj).original && this.suffix == ((ClassDumpAction) obj).suffix && this.target.equals(((ClassDumpAction) obj).target) && this.instrumentedType.equals(((ClassDumpAction) obj).instrumentedType) && Arrays.equals(this.binaryRepresentation, ((ClassDumpAction) obj).binaryRepresentation);
            }

            public int hashCode() {
                int hashCode = ((((((getClass().hashCode() * 31) + this.target.hashCode()) * 31) + this.instrumentedType.hashCode()) * 31) + (this.original ? 1 : 0)) * 31;
                return ((hashCode + ((int) (hashCode ^ (this.suffix >>> 32)))) * 31) + Arrays.hashCode(this.binaryRepresentation);
            }

            protected ClassDumpAction(String str, TypeDescription typeDescription, boolean z, long j, byte[] bArr) {
                this.target = str;
                this.instrumentedType = typeDescription;
                this.original = z;
                this.suffix = j;
                this.binaryRepresentation = bArr;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.security.PrivilegedExceptionAction
            public Void run() {
                FileOutputStream fileOutputStream = new FileOutputStream(new File(this.target, this.instrumentedType.getName() + (this.original ? "-original." : ".") + this.suffix + ".class"));
                try {
                    fileOutputStream.write(this.binaryRepresentation);
                    return NOTHING;
                } finally {
                    fileOutputStream.close();
                }
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default$ClassDumpAction$Dispatcher.class */
            protected interface Dispatcher {
                void dump(TypeDescription typeDescription, boolean z, byte[] bArr);

                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default$ClassDumpAction$Dispatcher$Disabled.class */
                public enum Disabled implements Dispatcher {
                    INSTANCE;

                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ClassDumpAction.Dispatcher
                    public final void dump(TypeDescription typeDescription, boolean z, byte[] bArr) {
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeWriter$Default$ClassDumpAction$Dispatcher$Enabled.class */
                public static class Enabled implements Dispatcher {
                    private final String folder;
                    private final long timestamp;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.timestamp == ((Enabled) obj).timestamp && this.folder.equals(((Enabled) obj).folder);
                    }

                    public int hashCode() {
                        int hashCode = ((getClass().hashCode() * 31) + this.folder.hashCode()) * 31;
                        return hashCode + ((int) (hashCode ^ (this.timestamp >>> 32)));
                    }

                    protected Enabled(String str, long j) {
                        this.folder = str;
                        this.timestamp = j;
                    }

                    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Object, java.lang.Exception] */
                    @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ClassDumpAction.Dispatcher
                    public void dump(TypeDescription typeDescription, boolean z, byte[] bArr) {
                        ?? doPrivileged;
                        try {
                            doPrivileged = Default.doPrivileged(new ClassDumpAction(this.folder, typeDescription, z, this.timestamp, bArr));
                        } catch (Exception e) {
                            doPrivileged.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
