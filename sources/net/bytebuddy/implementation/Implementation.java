package net.bytebuddy.implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.build.CachedReturnPlugin;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.annotation.AnnotationList;
import net.bytebuddy.description.annotation.AnnotationValue;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.method.ParameterList;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import net.bytebuddy.dynamic.scaffold.TypeInitializer;
import net.bytebuddy.dynamic.scaffold.TypeWriter;
import net.bytebuddy.implementation.MethodAccessorFactory;
import net.bytebuddy.implementation.attribute.AnnotationValueFilter;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.member.FieldAccess;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.implementation.bytecode.member.MethodReturn;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import net.bytebuddy.jar.asm.ClassVisitor;
import net.bytebuddy.jar.asm.FieldVisitor;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import net.bytebuddy.utility.CompoundList;
import net.bytebuddy.utility.RandomString;
import net.bytebuddy.utility.nullability.MaybeNull;

/* loaded from: infinitode-2.jar:net/bytebuddy/implementation/Implementation.class */
public interface Implementation extends InstrumentedType.Prepareable {

    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/Implementation$Composable.class */
    public interface Composable extends Implementation {
        Implementation andThen(Implementation implementation);

        Composable andThen(Composable composable);
    }

    ByteCodeAppender appender(Target target);

    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/Implementation$SpecialMethodInvocation.class */
    public interface SpecialMethodInvocation extends StackManipulation {
        MethodDescription getMethodDescription();

        TypeDescription getTypeDescription();

        SpecialMethodInvocation withCheckedCompatibilityTo(MethodDescription.TypeToken typeToken);

        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/Implementation$SpecialMethodInvocation$Illegal.class */
        public enum Illegal implements SpecialMethodInvocation {
            INSTANCE;

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public final boolean isValid() {
                return false;
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public final StackManipulation.Size apply(MethodVisitor methodVisitor, Context context) {
                throw new IllegalStateException("Cannot implement an undefined method");
            }

            @Override // net.bytebuddy.implementation.Implementation.SpecialMethodInvocation
            public final MethodDescription getMethodDescription() {
                throw new IllegalStateException("An illegal special method invocation must not be applied");
            }

            @Override // net.bytebuddy.implementation.Implementation.SpecialMethodInvocation
            public final TypeDescription getTypeDescription() {
                throw new IllegalStateException("An illegal special method invocation must not be applied");
            }

            @Override // net.bytebuddy.implementation.Implementation.SpecialMethodInvocation
            public final SpecialMethodInvocation withCheckedCompatibilityTo(MethodDescription.TypeToken typeToken) {
                return this;
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/Implementation$SpecialMethodInvocation$AbstractBase.class */
        public static abstract class AbstractBase extends StackManipulation.AbstractBase implements SpecialMethodInvocation {
            private transient /* synthetic */ int hashCode;

            @CachedReturnPlugin.Enhance("hashCode")
            public int hashCode() {
                int hashCode = this.hashCode != 0 ? 0 : (31 * getMethodDescription().asSignatureToken().hashCode()) + getTypeDescription().hashCode();
                int i = hashCode;
                if (hashCode == 0) {
                    i = this.hashCode;
                } else {
                    this.hashCode = i;
                }
                return i;
            }

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof SpecialMethodInvocation)) {
                    return false;
                }
                SpecialMethodInvocation specialMethodInvocation = (SpecialMethodInvocation) obj;
                return getMethodDescription().asSignatureToken().equals(specialMethodInvocation.getMethodDescription().asSignatureToken()) && getTypeDescription().equals(specialMethodInvocation.getTypeDescription());
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/Implementation$SpecialMethodInvocation$Simple.class */
        public static class Simple extends AbstractBase {
            private final MethodDescription methodDescription;
            private final TypeDescription typeDescription;
            private final StackManipulation stackManipulation;

            protected Simple(MethodDescription methodDescription, TypeDescription typeDescription, StackManipulation stackManipulation) {
                this.methodDescription = methodDescription;
                this.typeDescription = typeDescription;
                this.stackManipulation = stackManipulation;
            }

            public static SpecialMethodInvocation of(MethodDescription methodDescription, TypeDescription typeDescription) {
                StackManipulation special = MethodInvocation.invoke(methodDescription).special(typeDescription);
                return special.isValid() ? new Simple(methodDescription, typeDescription, special) : Illegal.INSTANCE;
            }

            @Override // net.bytebuddy.implementation.Implementation.SpecialMethodInvocation
            public MethodDescription getMethodDescription() {
                return this.methodDescription;
            }

            @Override // net.bytebuddy.implementation.Implementation.SpecialMethodInvocation
            public TypeDescription getTypeDescription() {
                return this.typeDescription;
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Context context) {
                return this.stackManipulation.apply(methodVisitor, context);
            }

            @Override // net.bytebuddy.implementation.Implementation.SpecialMethodInvocation
            public SpecialMethodInvocation withCheckedCompatibilityTo(MethodDescription.TypeToken typeToken) {
                if (this.methodDescription.asTypeToken().equals(typeToken)) {
                    return this;
                }
                return Illegal.INSTANCE;
            }
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/Implementation$Target.class */
    public interface Target {

        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/Implementation$Target$Factory.class */
        public interface Factory {
            Target make(TypeDescription typeDescription, MethodGraph.Linked linked, ClassFileVersion classFileVersion);
        }

        TypeDescription getInstrumentedType();

        TypeDefinition getOriginType();

        SpecialMethodInvocation invokeSuper(MethodDescription.SignatureToken signatureToken);

        SpecialMethodInvocation invokeDefault(MethodDescription.SignatureToken signatureToken);

        SpecialMethodInvocation invokeDefault(MethodDescription.SignatureToken signatureToken, TypeDescription typeDescription);

        SpecialMethodInvocation invokeDominant(MethodDescription.SignatureToken signatureToken);

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/Implementation$Target$AbstractBase.class */
        public static abstract class AbstractBase implements Target {
            protected final TypeDescription instrumentedType;
            protected final MethodGraph.Linked methodGraph;
            protected final DefaultMethodInvocation defaultMethodInvocation;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.defaultMethodInvocation.equals(((AbstractBase) obj).defaultMethodInvocation) && this.instrumentedType.equals(((AbstractBase) obj).instrumentedType) && this.methodGraph.equals(((AbstractBase) obj).methodGraph);
            }

            public int hashCode() {
                return (((((getClass().hashCode() * 31) + this.instrumentedType.hashCode()) * 31) + this.methodGraph.hashCode()) * 31) + this.defaultMethodInvocation.hashCode();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            public AbstractBase(TypeDescription typeDescription, MethodGraph.Linked linked, DefaultMethodInvocation defaultMethodInvocation) {
                this.instrumentedType = typeDescription;
                this.methodGraph = linked;
                this.defaultMethodInvocation = defaultMethodInvocation;
            }

            @Override // net.bytebuddy.implementation.Implementation.Target
            public TypeDescription getInstrumentedType() {
                return this.instrumentedType;
            }

            @Override // net.bytebuddy.implementation.Implementation.Target
            public SpecialMethodInvocation invokeDefault(MethodDescription.SignatureToken signatureToken) {
                SpecialMethodInvocation specialMethodInvocation = SpecialMethodInvocation.Illegal.INSTANCE;
                Iterator it = this.instrumentedType.getInterfaces().asErasures().iterator();
                while (it.hasNext()) {
                    SpecialMethodInvocation withCheckedCompatibilityTo = invokeDefault(signatureToken, (TypeDescription) it.next()).withCheckedCompatibilityTo(signatureToken.asTypeToken());
                    if (withCheckedCompatibilityTo.isValid()) {
                        if (specialMethodInvocation.isValid()) {
                            return SpecialMethodInvocation.Illegal.INSTANCE;
                        }
                        specialMethodInvocation = withCheckedCompatibilityTo;
                    }
                }
                return specialMethodInvocation;
            }

            @Override // net.bytebuddy.implementation.Implementation.Target
            public SpecialMethodInvocation invokeDefault(MethodDescription.SignatureToken signatureToken, TypeDescription typeDescription) {
                return this.defaultMethodInvocation.apply(this.methodGraph.getInterfaceGraph(typeDescription).locate(signatureToken), typeDescription);
            }

            @Override // net.bytebuddy.implementation.Implementation.Target
            public SpecialMethodInvocation invokeDominant(MethodDescription.SignatureToken signatureToken) {
                SpecialMethodInvocation invokeSuper = invokeSuper(signatureToken);
                if (invokeSuper.isValid()) {
                    return invokeSuper;
                }
                return invokeDefault(signatureToken);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/Implementation$Target$AbstractBase$DefaultMethodInvocation.class */
            public enum DefaultMethodInvocation {
                ENABLED { // from class: net.bytebuddy.implementation.Implementation.Target.AbstractBase.DefaultMethodInvocation.1
                    @Override // net.bytebuddy.implementation.Implementation.Target.AbstractBase.DefaultMethodInvocation
                    protected final SpecialMethodInvocation apply(MethodGraph.Node node, TypeDescription typeDescription) {
                        return node.getSort().isUnique() ? SpecialMethodInvocation.Simple.of(node.getRepresentative(), typeDescription) : SpecialMethodInvocation.Illegal.INSTANCE;
                    }
                },
                DISABLED { // from class: net.bytebuddy.implementation.Implementation.Target.AbstractBase.DefaultMethodInvocation.2
                    @Override // net.bytebuddy.implementation.Implementation.Target.AbstractBase.DefaultMethodInvocation
                    protected final SpecialMethodInvocation apply(MethodGraph.Node node, TypeDescription typeDescription) {
                        return SpecialMethodInvocation.Illegal.INSTANCE;
                    }
                };

                protected abstract SpecialMethodInvocation apply(MethodGraph.Node node, TypeDescription typeDescription);

                /* synthetic */ DefaultMethodInvocation(byte b2) {
                    this();
                }

                public static DefaultMethodInvocation of(ClassFileVersion classFileVersion) {
                    return classFileVersion.isAtLeast(ClassFileVersion.JAVA_V8) ? ENABLED : DISABLED;
                }
            }
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/Implementation$Context.class */
    public interface Context extends MethodAccessorFactory {

        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/Implementation$Context$Factory.class */
        public interface Factory {
            @Deprecated
            ExtractableView make(TypeDescription typeDescription, AuxiliaryType.NamingStrategy namingStrategy, TypeInitializer typeInitializer, ClassFileVersion classFileVersion, ClassFileVersion classFileVersion2);

            ExtractableView make(TypeDescription typeDescription, AuxiliaryType.NamingStrategy namingStrategy, TypeInitializer typeInitializer, ClassFileVersion classFileVersion, ClassFileVersion classFileVersion2, FrameGeneration frameGeneration);
        }

        TypeDescription register(AuxiliaryType auxiliaryType);

        FieldDescription.InDefinedShape cache(StackManipulation stackManipulation, TypeDescription typeDescription);

        TypeDescription getInstrumentedType();

        ClassFileVersion getClassFileVersion();

        FrameGeneration getFrameGeneration();

        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/Implementation$Context$FrameGeneration.class */
        public enum FrameGeneration {
            GENERATE(true) { // from class: net.bytebuddy.implementation.Implementation.Context.FrameGeneration.1
                @Override // net.bytebuddy.implementation.Implementation.Context.FrameGeneration
                public final void generate(MethodVisitor methodVisitor, int i, int i2, @MaybeNull Object[] objArr, int i3, @MaybeNull Object[] objArr2, int i4, @MaybeNull Object[] objArr3) {
                    methodVisitor.visitFrame(i, i3, objArr2, i2, objArr);
                }
            },
            EXPAND(true) { // from class: net.bytebuddy.implementation.Implementation.Context.FrameGeneration.2
                @Override // net.bytebuddy.implementation.Implementation.Context.FrameGeneration
                public final void generate(MethodVisitor methodVisitor, int i, int i2, @MaybeNull Object[] objArr, int i3, @MaybeNull Object[] objArr2, int i4, @MaybeNull Object[] objArr3) {
                    methodVisitor.visitFrame(-1, i4, objArr3, i2, objArr);
                }
            },
            DISABLED(false) { // from class: net.bytebuddy.implementation.Implementation.Context.FrameGeneration.3
                @Override // net.bytebuddy.implementation.Implementation.Context.FrameGeneration
                public final void generate(MethodVisitor methodVisitor, int i, int i2, @MaybeNull Object[] objArr, int i3, @MaybeNull Object[] objArr2, int i4, @MaybeNull Object[] objArr3) {
                }
            };

            private static final Object[] EMPTY = new Object[0];
            private final boolean active;

            protected abstract void generate(MethodVisitor methodVisitor, int i, int i2, @MaybeNull Object[] objArr, int i3, @MaybeNull Object[] objArr2, int i4, @MaybeNull Object[] objArr3);

            /* synthetic */ FrameGeneration(boolean z, byte b2) {
                this(z);
            }

            FrameGeneration(boolean z) {
                this.active = z;
            }

            public boolean isActive() {
                return this.active;
            }

            public void same(MethodVisitor methodVisitor, List<? extends TypeDefinition> list) {
                int length = EMPTY.length;
                Object[] objArr = EMPTY;
                generate(methodVisitor, 3, length, objArr, objArr.length, EMPTY, list.size(), toStackMapFrames(list));
            }

            public void same1(MethodVisitor methodVisitor, TypeDefinition typeDefinition, List<? extends TypeDefinition> list) {
                generate(methodVisitor, 4, 1, new Object[]{toStackMapFrame(typeDefinition)}, EMPTY.length, EMPTY, list.size(), toStackMapFrames(list));
            }

            public void append(MethodVisitor methodVisitor, List<? extends TypeDefinition> list, List<? extends TypeDefinition> list2) {
                generate(methodVisitor, 1, EMPTY.length, EMPTY, list.size(), toStackMapFrames(list), list2.size() + list.size(), toStackMapFrames(CompoundList.of((List) list2, (List) list)));
            }

            public void chop(MethodVisitor methodVisitor, int i, List<? extends TypeDefinition> list) {
                int length = EMPTY.length;
                Object[] objArr = EMPTY;
                generate(methodVisitor, 2, length, objArr, i, objArr, list.size(), toStackMapFrames(list));
            }

            public void full(MethodVisitor methodVisitor, List<? extends TypeDefinition> list, List<? extends TypeDefinition> list2) {
                generate(methodVisitor, 0, list.size(), toStackMapFrames(list), list2.size(), toStackMapFrames(list2), list2.size(), toStackMapFrames(list2));
            }

            private static Object[] toStackMapFrames(List<? extends TypeDefinition> list) {
                Object[] objArr = list.isEmpty() ? EMPTY : new Object[list.size()];
                for (int i = 0; i < list.size(); i++) {
                    objArr[i] = toStackMapFrame(list.get(i));
                }
                return objArr;
            }

            private static Object toStackMapFrame(TypeDefinition typeDefinition) {
                if (typeDefinition.represents(Boolean.TYPE) || typeDefinition.represents(Byte.TYPE) || typeDefinition.represents(Short.TYPE) || typeDefinition.represents(Character.TYPE) || typeDefinition.represents(Integer.TYPE)) {
                    return Opcodes.INTEGER;
                }
                if (typeDefinition.represents(Long.TYPE)) {
                    return Opcodes.LONG;
                }
                if (typeDefinition.represents(Float.TYPE)) {
                    return Opcodes.FLOAT;
                }
                if (typeDefinition.represents(Double.TYPE)) {
                    return Opcodes.DOUBLE;
                }
                return typeDefinition.asErasure().getInternalName();
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/Implementation$Context$ExtractableView.class */
        public interface ExtractableView extends Context {
            boolean isEnabled();

            List<DynamicType> getAuxiliaryTypes();

            void drain(TypeInitializer.Drain drain, ClassVisitor classVisitor, AnnotationValueFilter.Factory factory);

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/Implementation$Context$ExtractableView$AbstractBase.class */
            public static abstract class AbstractBase implements ExtractableView {
                protected final TypeDescription instrumentedType;
                protected final ClassFileVersion classFileVersion;
                protected final FrameGeneration frameGeneration;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.frameGeneration.equals(((AbstractBase) obj).frameGeneration) && this.instrumentedType.equals(((AbstractBase) obj).instrumentedType) && this.classFileVersion.equals(((AbstractBase) obj).classFileVersion);
                }

                public int hashCode() {
                    return (((((getClass().hashCode() * 31) + this.instrumentedType.hashCode()) * 31) + this.classFileVersion.hashCode()) * 31) + this.frameGeneration.hashCode();
                }

                protected AbstractBase(TypeDescription typeDescription, ClassFileVersion classFileVersion, FrameGeneration frameGeneration) {
                    this.instrumentedType = typeDescription;
                    this.classFileVersion = classFileVersion;
                    this.frameGeneration = frameGeneration;
                }

                @Override // net.bytebuddy.implementation.Implementation.Context
                public TypeDescription getInstrumentedType() {
                    return this.instrumentedType;
                }

                @Override // net.bytebuddy.implementation.Implementation.Context
                public ClassFileVersion getClassFileVersion() {
                    return this.classFileVersion;
                }

                @Override // net.bytebuddy.implementation.Implementation.Context
                public FrameGeneration getFrameGeneration() {
                    return this.frameGeneration;
                }
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/Implementation$Context$Disabled.class */
        public static class Disabled extends ExtractableView.AbstractBase {
            protected Disabled(TypeDescription typeDescription, ClassFileVersion classFileVersion, FrameGeneration frameGeneration) {
                super(typeDescription, classFileVersion, frameGeneration);
            }

            @Override // net.bytebuddy.implementation.Implementation.Context.ExtractableView
            public boolean isEnabled() {
                return false;
            }

            @Override // net.bytebuddy.implementation.Implementation.Context.ExtractableView
            public List<DynamicType> getAuxiliaryTypes() {
                return Collections.emptyList();
            }

            @Override // net.bytebuddy.implementation.Implementation.Context.ExtractableView
            public void drain(TypeInitializer.Drain drain, ClassVisitor classVisitor, AnnotationValueFilter.Factory factory) {
                drain.apply(classVisitor, TypeInitializer.None.INSTANCE, this);
            }

            @Override // net.bytebuddy.implementation.Implementation.Context
            public TypeDescription register(AuxiliaryType auxiliaryType) {
                throw new IllegalStateException("Registration of auxiliary types was disabled: " + auxiliaryType);
            }

            @Override // net.bytebuddy.implementation.MethodAccessorFactory
            public MethodDescription.InDefinedShape registerAccessorFor(SpecialMethodInvocation specialMethodInvocation, MethodAccessorFactory.AccessType accessType) {
                throw new IllegalStateException("Registration of method accessors was disabled: " + specialMethodInvocation.getMethodDescription());
            }

            @Override // net.bytebuddy.implementation.MethodAccessorFactory
            public MethodDescription.InDefinedShape registerGetterFor(FieldDescription fieldDescription, MethodAccessorFactory.AccessType accessType) {
                throw new IllegalStateException("Registration of field accessor was disabled: " + fieldDescription);
            }

            @Override // net.bytebuddy.implementation.MethodAccessorFactory
            public MethodDescription.InDefinedShape registerSetterFor(FieldDescription fieldDescription, MethodAccessorFactory.AccessType accessType) {
                throw new IllegalStateException("Registration of field accessor was disabled: " + fieldDescription);
            }

            @Override // net.bytebuddy.implementation.Implementation.Context
            public FieldDescription.InDefinedShape cache(StackManipulation stackManipulation, TypeDescription typeDescription) {
                throw new IllegalStateException("Field values caching was disabled: " + typeDescription);
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/Implementation$Context$Disabled$Factory.class */
            public enum Factory implements Factory {
                INSTANCE;

                @Override // net.bytebuddy.implementation.Implementation.Context.Factory
                @Deprecated
                public final ExtractableView make(TypeDescription typeDescription, AuxiliaryType.NamingStrategy namingStrategy, TypeInitializer typeInitializer, ClassFileVersion classFileVersion, ClassFileVersion classFileVersion2) {
                    return make(typeDescription, namingStrategy, typeInitializer, classFileVersion, classFileVersion2, classFileVersion.isAtLeast(ClassFileVersion.JAVA_V6) ? FrameGeneration.GENERATE : FrameGeneration.DISABLED);
                }

                @Override // net.bytebuddy.implementation.Implementation.Context.Factory
                public final ExtractableView make(TypeDescription typeDescription, AuxiliaryType.NamingStrategy namingStrategy, TypeInitializer typeInitializer, ClassFileVersion classFileVersion, ClassFileVersion classFileVersion2, FrameGeneration frameGeneration) {
                    if (typeInitializer.isDefined()) {
                        throw new IllegalStateException("Cannot define type initializer which was explicitly disabled: " + typeInitializer);
                    }
                    return new Disabled(typeDescription, classFileVersion, frameGeneration);
                }
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/Implementation$Context$Default.class */
        public static class Default extends ExtractableView.AbstractBase {
            public static final String ACCESSOR_METHOD_SUFFIX = "accessor";
            public static final String FIELD_CACHE_PREFIX = "cachedValue";
            private final AuxiliaryType.NamingStrategy auxiliaryTypeNamingStrategy;
            private final TypeInitializer typeInitializer;
            private final ClassFileVersion auxiliaryClassFileVersion;
            private final Map<SpecialMethodInvocation, DelegationRecord> registeredAccessorMethods;
            private final Map<FieldDescription, DelegationRecord> registeredGetters;
            private final Map<FieldDescription, DelegationRecord> registeredSetters;
            private final Map<AuxiliaryType, DynamicType> auxiliaryTypes;
            private final Map<FieldCacheEntry, FieldDescription.InDefinedShape> registeredFieldCacheEntries;
            private final Set<FieldDescription.InDefinedShape> registeredFieldCacheFields;
            private final String suffix;
            private boolean fieldCacheCanAppendEntries;

            protected Default(TypeDescription typeDescription, ClassFileVersion classFileVersion, AuxiliaryType.NamingStrategy namingStrategy, TypeInitializer typeInitializer, ClassFileVersion classFileVersion2, FrameGeneration frameGeneration, String str) {
                super(typeDescription, classFileVersion, frameGeneration);
                this.auxiliaryTypeNamingStrategy = namingStrategy;
                this.typeInitializer = typeInitializer;
                this.auxiliaryClassFileVersion = classFileVersion2;
                this.suffix = str;
                this.registeredAccessorMethods = new HashMap();
                this.registeredGetters = new HashMap();
                this.registeredSetters = new HashMap();
                this.auxiliaryTypes = new HashMap();
                this.registeredFieldCacheEntries = new HashMap();
                this.registeredFieldCacheFields = new HashSet();
                this.fieldCacheCanAppendEntries = true;
            }

            @Override // net.bytebuddy.implementation.Implementation.Context.ExtractableView
            public boolean isEnabled() {
                return true;
            }

            @Override // net.bytebuddy.implementation.MethodAccessorFactory
            public MethodDescription.InDefinedShape registerAccessorFor(SpecialMethodInvocation specialMethodInvocation, MethodAccessorFactory.AccessType accessType) {
                DelegationRecord delegationRecord = this.registeredAccessorMethods.get(specialMethodInvocation);
                DelegationRecord accessorMethodDelegation = delegationRecord == null ? new AccessorMethodDelegation(this.instrumentedType, this.suffix, accessType, specialMethodInvocation) : delegationRecord.with(accessType);
                this.registeredAccessorMethods.put(specialMethodInvocation, accessorMethodDelegation);
                return accessorMethodDelegation.getMethod();
            }

            @Override // net.bytebuddy.implementation.MethodAccessorFactory
            public MethodDescription.InDefinedShape registerGetterFor(FieldDescription fieldDescription, MethodAccessorFactory.AccessType accessType) {
                DelegationRecord delegationRecord = this.registeredGetters.get(fieldDescription);
                DelegationRecord fieldGetterDelegation = delegationRecord == null ? new FieldGetterDelegation(this.instrumentedType, this.suffix, accessType, fieldDescription) : delegationRecord.with(accessType);
                this.registeredGetters.put(fieldDescription, fieldGetterDelegation);
                return fieldGetterDelegation.getMethod();
            }

            @Override // net.bytebuddy.implementation.MethodAccessorFactory
            public MethodDescription.InDefinedShape registerSetterFor(FieldDescription fieldDescription, MethodAccessorFactory.AccessType accessType) {
                DelegationRecord delegationRecord = this.registeredSetters.get(fieldDescription);
                DelegationRecord fieldSetterDelegation = delegationRecord == null ? new FieldSetterDelegation(this.instrumentedType, this.suffix, accessType, fieldDescription) : delegationRecord.with(accessType);
                this.registeredSetters.put(fieldDescription, fieldSetterDelegation);
                return fieldSetterDelegation.getMethod();
            }

            @Override // net.bytebuddy.implementation.Implementation.Context
            public TypeDescription register(AuxiliaryType auxiliaryType) {
                DynamicType dynamicType = this.auxiliaryTypes.get(auxiliaryType);
                DynamicType dynamicType2 = dynamicType;
                if (dynamicType == null) {
                    dynamicType2 = auxiliaryType.make(this.auxiliaryTypeNamingStrategy.name(this.instrumentedType, auxiliaryType), this.auxiliaryClassFileVersion, this);
                    this.auxiliaryTypes.put(auxiliaryType, dynamicType2);
                }
                return dynamicType2.getTypeDescription();
            }

            @Override // net.bytebuddy.implementation.Implementation.Context.ExtractableView
            public List<DynamicType> getAuxiliaryTypes() {
                return new ArrayList(this.auxiliaryTypes.values());
            }

            @Override // net.bytebuddy.implementation.Implementation.Context
            public FieldDescription.InDefinedShape cache(StackManipulation stackManipulation, TypeDescription typeDescription) {
                CacheValueField cacheValueField;
                FieldCacheEntry fieldCacheEntry = new FieldCacheEntry(stackManipulation, typeDescription);
                FieldDescription.InDefinedShape inDefinedShape = this.registeredFieldCacheEntries.get(fieldCacheEntry);
                if (inDefinedShape != null) {
                    return inDefinedShape;
                }
                if (!this.fieldCacheCanAppendEntries) {
                    throw new IllegalStateException("Cached values cannot be registered after defining the type initializer for " + this.instrumentedType);
                }
                int hashCode = stackManipulation.hashCode();
                do {
                    int i = hashCode;
                    hashCode++;
                    cacheValueField = new CacheValueField(this.instrumentedType, typeDescription.asGenericType(), this.suffix, i);
                } while (!this.registeredFieldCacheFields.add(cacheValueField));
                this.registeredFieldCacheEntries.put(fieldCacheEntry, cacheValueField);
                return cacheValueField;
            }

            @Override // net.bytebuddy.implementation.Implementation.Context.ExtractableView
            public void drain(TypeInitializer.Drain drain, ClassVisitor classVisitor, AnnotationValueFilter.Factory factory) {
                this.fieldCacheCanAppendEntries = false;
                TypeInitializer typeInitializer = this.typeInitializer;
                for (Map.Entry<FieldCacheEntry, FieldDescription.InDefinedShape> entry : this.registeredFieldCacheEntries.entrySet()) {
                    FieldVisitor visitField = classVisitor.visitField(entry.getValue().getModifiers(), entry.getValue().getInternalName(), entry.getValue().getDescriptor(), entry.getValue().getGenericSignature(), FieldDescription.NO_DEFAULT_VALUE);
                    if (visitField != null) {
                        visitField.visitEnd();
                        typeInitializer = typeInitializer.expandWith(entry.getKey().storeIn(entry.getValue()));
                    }
                }
                drain.apply(classVisitor, typeInitializer, this);
                Iterator<DelegationRecord> it = this.registeredAccessorMethods.values().iterator();
                while (it.hasNext()) {
                    it.next().apply(classVisitor, this, factory);
                }
                Iterator<DelegationRecord> it2 = this.registeredGetters.values().iterator();
                while (it2.hasNext()) {
                    it2.next().apply(classVisitor, this, factory);
                }
                Iterator<DelegationRecord> it3 = this.registeredSetters.values().iterator();
                while (it3.hasNext()) {
                    it3.next().apply(classVisitor, this, factory);
                }
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/Implementation$Context$Default$CacheValueField.class */
            protected static class CacheValueField extends FieldDescription.InDefinedShape.AbstractBase {
                private final TypeDescription instrumentedType;
                private final TypeDescription.Generic fieldType;
                private final String name;

                protected CacheValueField(TypeDescription typeDescription, TypeDescription.Generic generic, String str, int i) {
                    this.instrumentedType = typeDescription;
                    this.fieldType = generic;
                    this.name = "cachedValue$" + str + "$" + RandomString.hashOf(i);
                }

                @Override // net.bytebuddy.description.field.FieldDescription
                public TypeDescription.Generic getType() {
                    return this.fieldType;
                }

                @Override // net.bytebuddy.description.annotation.AnnotationSource
                public AnnotationList getDeclaredAnnotations() {
                    return new AnnotationList.Empty();
                }

                @Override // net.bytebuddy.description.DeclaredByType
                public TypeDescription getDeclaringType() {
                    return this.instrumentedType;
                }

                @Override // net.bytebuddy.description.ModifierReviewable
                public int getModifiers() {
                    return 4120 | (this.instrumentedType.isInterface() ? 1 : 2);
                }

                @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
                public String getName() {
                    return this.name;
                }
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/Implementation$Context$Default$FieldCacheEntry.class */
            protected static class FieldCacheEntry implements StackManipulation {
                private final StackManipulation fieldValue;
                private final TypeDescription fieldType;

                protected FieldCacheEntry(StackManipulation stackManipulation, TypeDescription typeDescription) {
                    this.fieldValue = stackManipulation;
                    this.fieldType = typeDescription;
                }

                protected ByteCodeAppender storeIn(FieldDescription fieldDescription) {
                    return new ByteCodeAppender.Simple(this, FieldAccess.forField(fieldDescription).write());
                }

                protected TypeDescription getFieldType() {
                    return this.fieldType;
                }

                @Override // net.bytebuddy.implementation.bytecode.StackManipulation
                public boolean isValid() {
                    return this.fieldValue.isValid();
                }

                @Override // net.bytebuddy.implementation.bytecode.StackManipulation
                public StackManipulation.Size apply(MethodVisitor methodVisitor, Context context) {
                    return this.fieldValue.apply(methodVisitor, context);
                }

                public int hashCode() {
                    return (this.fieldValue.hashCode() * 31) + this.fieldType.hashCode();
                }

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    FieldCacheEntry fieldCacheEntry = (FieldCacheEntry) obj;
                    return this.fieldValue.equals(fieldCacheEntry.fieldValue) && this.fieldType.equals(fieldCacheEntry.fieldType);
                }
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/Implementation$Context$Default$AbstractPropertyAccessorMethod.class */
            protected static abstract class AbstractPropertyAccessorMethod extends MethodDescription.InDefinedShape.AbstractBase {
                protected abstract int getBaseModifiers();

                protected AbstractPropertyAccessorMethod() {
                }

                @Override // net.bytebuddy.description.ModifierReviewable
                public int getModifiers() {
                    return 4096 | getBaseModifiers() | (getDeclaringType().isInterface() ? 1 : 16);
                }
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/Implementation$Context$Default$AccessorMethod.class */
            protected static class AccessorMethod extends AbstractPropertyAccessorMethod {
                private final TypeDescription instrumentedType;
                private final MethodDescription methodDescription;
                private final String name;

                protected AccessorMethod(TypeDescription typeDescription, MethodDescription methodDescription, TypeDescription typeDescription2, String str) {
                    this.instrumentedType = typeDescription;
                    this.methodDescription = methodDescription;
                    this.name = methodDescription.getInternalName() + "$accessor$" + str + (typeDescription2.isInterface() ? "$" + RandomString.hashOf(typeDescription2.hashCode()) : "");
                }

                @Override // net.bytebuddy.description.method.MethodDescription
                public TypeDescription.Generic getReturnType() {
                    return this.methodDescription.getReturnType().asRawType();
                }

                @Override // net.bytebuddy.description.method.MethodDescription, net.bytebuddy.description.method.MethodDescription.InDefinedShape
                public ParameterList<ParameterDescription.InDefinedShape> getParameters() {
                    return new ParameterList.Explicit.ForTypes(this, this.methodDescription.getParameters().asTypeList().asRawTypes());
                }

                @Override // net.bytebuddy.description.method.MethodDescription
                public TypeList.Generic getExceptionTypes() {
                    return this.methodDescription.getExceptionTypes().asRawTypes();
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

                @Override // net.bytebuddy.description.DeclaredByType
                public TypeDescription getDeclaringType() {
                    return this.instrumentedType;
                }

                @Override // net.bytebuddy.implementation.Implementation.Context.Default.AbstractPropertyAccessorMethod
                protected int getBaseModifiers() {
                    return this.methodDescription.isStatic() ? 8 : 0;
                }

                @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
                public String getInternalName() {
                    return this.name;
                }
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/Implementation$Context$Default$FieldGetter.class */
            protected static class FieldGetter extends AbstractPropertyAccessorMethod {
                private final TypeDescription instrumentedType;
                private final FieldDescription fieldDescription;
                private final String name;

                protected FieldGetter(TypeDescription typeDescription, FieldDescription fieldDescription, String str) {
                    this.instrumentedType = typeDescription;
                    this.fieldDescription = fieldDescription;
                    this.name = fieldDescription.getName() + "$accessor$" + str;
                }

                @Override // net.bytebuddy.description.method.MethodDescription
                public TypeDescription.Generic getReturnType() {
                    return this.fieldDescription.getType().asRawType();
                }

                @Override // net.bytebuddy.description.method.MethodDescription, net.bytebuddy.description.method.MethodDescription.InDefinedShape
                public ParameterList<ParameterDescription.InDefinedShape> getParameters() {
                    return new ParameterList.Empty();
                }

                @Override // net.bytebuddy.description.method.MethodDescription
                public TypeList.Generic getExceptionTypes() {
                    return new TypeList.Generic.Empty();
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

                @Override // net.bytebuddy.description.DeclaredByType
                public TypeDescription getDeclaringType() {
                    return this.instrumentedType;
                }

                @Override // net.bytebuddy.implementation.Implementation.Context.Default.AbstractPropertyAccessorMethod
                protected int getBaseModifiers() {
                    return this.fieldDescription.isStatic() ? 8 : 0;
                }

                @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
                public String getInternalName() {
                    return this.name;
                }
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/Implementation$Context$Default$FieldSetter.class */
            protected static class FieldSetter extends AbstractPropertyAccessorMethod {
                private final TypeDescription instrumentedType;
                private final FieldDescription fieldDescription;
                private final String name;

                protected FieldSetter(TypeDescription typeDescription, FieldDescription fieldDescription, String str) {
                    this.instrumentedType = typeDescription;
                    this.fieldDescription = fieldDescription;
                    this.name = fieldDescription.getName() + "$accessor$" + str;
                }

                @Override // net.bytebuddy.description.method.MethodDescription
                public TypeDescription.Generic getReturnType() {
                    return TypeDescription.Generic.OfNonGenericType.ForLoadedType.of(Void.TYPE);
                }

                @Override // net.bytebuddy.description.method.MethodDescription, net.bytebuddy.description.method.MethodDescription.InDefinedShape
                public ParameterList<ParameterDescription.InDefinedShape> getParameters() {
                    return new ParameterList.Explicit.ForTypes(this, (List<? extends TypeDefinition>) Collections.singletonList(this.fieldDescription.getType().asRawType()));
                }

                @Override // net.bytebuddy.description.method.MethodDescription
                public TypeList.Generic getExceptionTypes() {
                    return new TypeList.Generic.Empty();
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

                @Override // net.bytebuddy.description.DeclaredByType
                public TypeDescription getDeclaringType() {
                    return this.instrumentedType;
                }

                @Override // net.bytebuddy.implementation.Implementation.Context.Default.AbstractPropertyAccessorMethod
                protected int getBaseModifiers() {
                    return this.fieldDescription.isStatic() ? 8 : 0;
                }

                @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
                public String getInternalName() {
                    return this.name;
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/Implementation$Context$Default$DelegationRecord.class */
            protected static abstract class DelegationRecord extends TypeWriter.MethodPool.Record.ForDefinedMethod implements ByteCodeAppender {
                protected final MethodDescription.InDefinedShape methodDescription;
                protected final Visibility visibility;

                protected abstract DelegationRecord with(MethodAccessorFactory.AccessType accessType);

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.visibility.equals(((DelegationRecord) obj).visibility) && this.methodDescription.equals(((DelegationRecord) obj).methodDescription);
                }

                public int hashCode() {
                    return (((getClass().hashCode() * 31) + this.methodDescription.hashCode()) * 31) + this.visibility.hashCode();
                }

                protected DelegationRecord(MethodDescription.InDefinedShape inDefinedShape, Visibility visibility) {
                    this.methodDescription = inDefinedShape;
                    this.visibility = visibility;
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public MethodDescription.InDefinedShape getMethod() {
                    return this.methodDescription;
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public TypeWriter.MethodPool.Record.Sort getSort() {
                    return TypeWriter.MethodPool.Record.Sort.IMPLEMENTED;
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public Visibility getVisibility() {
                    return this.visibility;
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public void applyHead(MethodVisitor methodVisitor) {
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public void applyBody(MethodVisitor methodVisitor, Context context, AnnotationValueFilter.Factory factory) {
                    methodVisitor.visitCode();
                    ByteCodeAppender.Size applyCode = applyCode(methodVisitor, context);
                    methodVisitor.visitMaxs(applyCode.getOperandStackSize(), applyCode.getLocalVariableSize());
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public void applyAttributes(MethodVisitor methodVisitor, AnnotationValueFilter.Factory factory) {
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public ByteCodeAppender.Size applyCode(MethodVisitor methodVisitor, Context context) {
                    return apply(methodVisitor, context, getMethod());
                }

                @Override // net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record
                public TypeWriter.MethodPool.Record prepend(ByteCodeAppender byteCodeAppender) {
                    throw new UnsupportedOperationException("Cannot prepend code to a delegation for " + this.methodDescription);
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/Implementation$Context$Default$AccessorMethodDelegation.class */
            protected static class AccessorMethodDelegation extends DelegationRecord {
                private final StackManipulation accessorMethodInvocation;

                @Override // net.bytebuddy.implementation.Implementation.Context.Default.DelegationRecord
                public boolean equals(@MaybeNull Object obj) {
                    if (!super.equals(obj)) {
                        return false;
                    }
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.accessorMethodInvocation.equals(((AccessorMethodDelegation) obj).accessorMethodInvocation);
                }

                @Override // net.bytebuddy.implementation.Implementation.Context.Default.DelegationRecord
                public int hashCode() {
                    return (super.hashCode() * 31) + this.accessorMethodInvocation.hashCode();
                }

                protected AccessorMethodDelegation(TypeDescription typeDescription, String str, MethodAccessorFactory.AccessType accessType, SpecialMethodInvocation specialMethodInvocation) {
                    this(new AccessorMethod(typeDescription, specialMethodInvocation.getMethodDescription(), specialMethodInvocation.getTypeDescription(), str), accessType.getVisibility(), specialMethodInvocation);
                }

                private AccessorMethodDelegation(MethodDescription.InDefinedShape inDefinedShape, Visibility visibility, StackManipulation stackManipulation) {
                    super(inDefinedShape, visibility);
                    this.accessorMethodInvocation = stackManipulation;
                }

                @Override // net.bytebuddy.implementation.Implementation.Context.Default.DelegationRecord
                protected DelegationRecord with(MethodAccessorFactory.AccessType accessType) {
                    return new AccessorMethodDelegation(this.methodDescription, this.visibility.expandTo(accessType.getVisibility()), this.accessorMethodInvocation);
                }

                @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
                public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Context context, MethodDescription methodDescription) {
                    return new ByteCodeAppender.Size(new StackManipulation.Compound(MethodVariableAccess.allArgumentsOf(methodDescription).prependThisReference(), this.accessorMethodInvocation, MethodReturn.of(methodDescription.getReturnType())).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/Implementation$Context$Default$FieldGetterDelegation.class */
            protected static class FieldGetterDelegation extends DelegationRecord {
                private final FieldDescription fieldDescription;

                @Override // net.bytebuddy.implementation.Implementation.Context.Default.DelegationRecord
                public boolean equals(@MaybeNull Object obj) {
                    if (!super.equals(obj)) {
                        return false;
                    }
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.fieldDescription.equals(((FieldGetterDelegation) obj).fieldDescription);
                }

                @Override // net.bytebuddy.implementation.Implementation.Context.Default.DelegationRecord
                public int hashCode() {
                    return (super.hashCode() * 31) + this.fieldDescription.hashCode();
                }

                protected FieldGetterDelegation(TypeDescription typeDescription, String str, MethodAccessorFactory.AccessType accessType, FieldDescription fieldDescription) {
                    this(new FieldGetter(typeDescription, fieldDescription, str), accessType.getVisibility(), fieldDescription);
                }

                private FieldGetterDelegation(MethodDescription.InDefinedShape inDefinedShape, Visibility visibility, FieldDescription fieldDescription) {
                    super(inDefinedShape, visibility);
                    this.fieldDescription = fieldDescription;
                }

                @Override // net.bytebuddy.implementation.Implementation.Context.Default.DelegationRecord
                protected DelegationRecord with(MethodAccessorFactory.AccessType accessType) {
                    return new FieldGetterDelegation(this.methodDescription, this.visibility.expandTo(accessType.getVisibility()), this.fieldDescription);
                }

                @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
                public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Context context, MethodDescription methodDescription) {
                    StackManipulation[] stackManipulationArr = new StackManipulation[3];
                    stackManipulationArr[0] = this.fieldDescription.isStatic() ? StackManipulation.Trivial.INSTANCE : MethodVariableAccess.loadThis();
                    stackManipulationArr[1] = FieldAccess.forField(this.fieldDescription).read();
                    stackManipulationArr[2] = MethodReturn.of(this.fieldDescription.getType());
                    return new ByteCodeAppender.Size(new StackManipulation.Compound(stackManipulationArr).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/Implementation$Context$Default$FieldSetterDelegation.class */
            protected static class FieldSetterDelegation extends DelegationRecord {
                private final FieldDescription fieldDescription;

                @Override // net.bytebuddy.implementation.Implementation.Context.Default.DelegationRecord
                public boolean equals(@MaybeNull Object obj) {
                    if (!super.equals(obj)) {
                        return false;
                    }
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.fieldDescription.equals(((FieldSetterDelegation) obj).fieldDescription);
                }

                @Override // net.bytebuddy.implementation.Implementation.Context.Default.DelegationRecord
                public int hashCode() {
                    return (super.hashCode() * 31) + this.fieldDescription.hashCode();
                }

                protected FieldSetterDelegation(TypeDescription typeDescription, String str, MethodAccessorFactory.AccessType accessType, FieldDescription fieldDescription) {
                    this(new FieldSetter(typeDescription, fieldDescription, str), accessType.getVisibility(), fieldDescription);
                }

                private FieldSetterDelegation(MethodDescription.InDefinedShape inDefinedShape, Visibility visibility, FieldDescription fieldDescription) {
                    super(inDefinedShape, visibility);
                    this.fieldDescription = fieldDescription;
                }

                @Override // net.bytebuddy.implementation.Implementation.Context.Default.DelegationRecord
                protected DelegationRecord with(MethodAccessorFactory.AccessType accessType) {
                    return new FieldSetterDelegation(this.methodDescription, this.visibility.expandTo(accessType.getVisibility()), this.fieldDescription);
                }

                @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
                public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Context context, MethodDescription methodDescription) {
                    return new ByteCodeAppender.Size(new StackManipulation.Compound(MethodVariableAccess.allArgumentsOf(methodDescription).prependThisReference(), FieldAccess.forField(this.fieldDescription).write(), MethodReturn.VOID).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
                }
            }

            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/Implementation$Context$Default$Factory.class */
            public enum Factory implements Factory {
                INSTANCE;

                @Override // net.bytebuddy.implementation.Implementation.Context.Factory
                @Deprecated
                public final ExtractableView make(TypeDescription typeDescription, AuxiliaryType.NamingStrategy namingStrategy, TypeInitializer typeInitializer, ClassFileVersion classFileVersion, ClassFileVersion classFileVersion2) {
                    return make(typeDescription, namingStrategy, typeInitializer, classFileVersion, classFileVersion2, classFileVersion.isAtLeast(ClassFileVersion.JAVA_V6) ? FrameGeneration.GENERATE : FrameGeneration.DISABLED);
                }

                @Override // net.bytebuddy.implementation.Implementation.Context.Factory
                public final ExtractableView make(TypeDescription typeDescription, AuxiliaryType.NamingStrategy namingStrategy, TypeInitializer typeInitializer, ClassFileVersion classFileVersion, ClassFileVersion classFileVersion2, FrameGeneration frameGeneration) {
                    return new Default(typeDescription, classFileVersion, namingStrategy, typeInitializer, classFileVersion2, frameGeneration, RandomString.make());
                }

                @HashCodeAndEqualsPlugin.Enhance
                /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/Implementation$Context$Default$Factory$WithFixedSuffix.class */
                public static class WithFixedSuffix implements Factory {
                    private final String suffix;

                    public boolean equals(@MaybeNull Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.suffix.equals(((WithFixedSuffix) obj).suffix);
                    }

                    public int hashCode() {
                        return (getClass().hashCode() * 31) + this.suffix.hashCode();
                    }

                    public WithFixedSuffix(String str) {
                        this.suffix = str;
                    }

                    @Override // net.bytebuddy.implementation.Implementation.Context.Factory
                    @Deprecated
                    public ExtractableView make(TypeDescription typeDescription, AuxiliaryType.NamingStrategy namingStrategy, TypeInitializer typeInitializer, ClassFileVersion classFileVersion, ClassFileVersion classFileVersion2) {
                        return make(typeDescription, namingStrategy, typeInitializer, classFileVersion, classFileVersion2, classFileVersion.isAtLeast(ClassFileVersion.JAVA_V6) ? FrameGeneration.GENERATE : FrameGeneration.DISABLED);
                    }

                    @Override // net.bytebuddy.implementation.Implementation.Context.Factory
                    public ExtractableView make(TypeDescription typeDescription, AuxiliaryType.NamingStrategy namingStrategy, TypeInitializer typeInitializer, ClassFileVersion classFileVersion, ClassFileVersion classFileVersion2, FrameGeneration frameGeneration) {
                        return new Default(typeDescription, classFileVersion, namingStrategy, typeInitializer, classFileVersion2, frameGeneration, this.suffix);
                    }
                }
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/Implementation$Compound.class */
    public static class Compound implements Implementation {
        private final List<Implementation> implementations;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.implementations.equals(((Compound) obj).implementations);
        }

        public int hashCode() {
            return (getClass().hashCode() * 31) + this.implementations.hashCode();
        }

        public Compound(Implementation... implementationArr) {
            this((List<? extends Implementation>) Arrays.asList(implementationArr));
        }

        public Compound(List<? extends Implementation> list) {
            this.implementations = new ArrayList();
            for (Implementation implementation : list) {
                if (!(implementation instanceof Composable)) {
                    if (implementation instanceof Compound) {
                        this.implementations.addAll(((Compound) implementation).implementations);
                    } else {
                        this.implementations.add(implementation);
                    }
                } else {
                    this.implementations.addAll(((Composable) implementation).implementations);
                    this.implementations.add(((Composable) implementation).composable);
                }
            }
        }

        @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
        public InstrumentedType prepare(InstrumentedType instrumentedType) {
            Iterator<Implementation> it = this.implementations.iterator();
            while (it.hasNext()) {
                instrumentedType = it.next().prepare(instrumentedType);
            }
            return instrumentedType;
        }

        @Override // net.bytebuddy.implementation.Implementation
        public ByteCodeAppender appender(Target target) {
            ByteCodeAppender[] byteCodeAppenderArr = new ByteCodeAppender[this.implementations.size()];
            int i = 0;
            Iterator<Implementation> it = this.implementations.iterator();
            while (it.hasNext()) {
                int i2 = i;
                i++;
                byteCodeAppenderArr[i2] = it.next().appender(target);
            }
            return new ByteCodeAppender.Compound(byteCodeAppenderArr);
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/Implementation$Compound$Composable.class */
        public static class Composable implements Composable {
            private final Composable composable;
            private final List<Implementation> implementations;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.composable.equals(((Composable) obj).composable) && this.implementations.equals(((Composable) obj).implementations);
            }

            public int hashCode() {
                return (((getClass().hashCode() * 31) + this.composable.hashCode()) * 31) + this.implementations.hashCode();
            }

            public Composable(Implementation implementation, Composable composable) {
                this((List<? extends Implementation>) Collections.singletonList(implementation), composable);
            }

            public Composable(List<? extends Implementation> list, Composable composable) {
                this.implementations = new ArrayList();
                for (Implementation implementation : list) {
                    if (implementation instanceof Composable) {
                        this.implementations.addAll(((Composable) implementation).implementations);
                        this.implementations.add(((Composable) implementation).composable);
                    } else if (implementation instanceof Compound) {
                        this.implementations.addAll(((Compound) implementation).implementations);
                    } else {
                        this.implementations.add(implementation);
                    }
                }
                if (composable instanceof Composable) {
                    this.implementations.addAll(((Composable) composable).implementations);
                    this.composable = ((Composable) composable).composable;
                } else {
                    this.composable = composable;
                }
            }

            @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
            public InstrumentedType prepare(InstrumentedType instrumentedType) {
                Iterator<Implementation> it = this.implementations.iterator();
                while (it.hasNext()) {
                    instrumentedType = it.next().prepare(instrumentedType);
                }
                return this.composable.prepare(instrumentedType);
            }

            @Override // net.bytebuddy.implementation.Implementation
            public ByteCodeAppender appender(Target target) {
                ByteCodeAppender[] byteCodeAppenderArr = new ByteCodeAppender[this.implementations.size() + 1];
                int i = 0;
                Iterator<Implementation> it = this.implementations.iterator();
                while (it.hasNext()) {
                    int i2 = i;
                    i++;
                    byteCodeAppenderArr[i2] = it.next().appender(target);
                }
                byteCodeAppenderArr[i] = this.composable.appender(target);
                return new ByteCodeAppender.Compound(byteCodeAppenderArr);
            }

            @Override // net.bytebuddy.implementation.Implementation.Composable
            public Implementation andThen(Implementation implementation) {
                return new Compound((List<? extends Implementation>) CompoundList.of(this.implementations, this.composable.andThen(implementation)));
            }

            @Override // net.bytebuddy.implementation.Implementation.Composable
            public Composable andThen(Composable composable) {
                return new Composable(this.implementations, this.composable.andThen(composable));
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/Implementation$Simple.class */
    public static class Simple implements Implementation {
        private static final int NO_ADDITIONAL_VARIABLES = 0;
        private final ByteCodeAppender byteCodeAppender;

        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/Implementation$Simple$Dispatcher.class */
        public interface Dispatcher {
            StackManipulation apply(Target target, MethodDescription methodDescription);
        }

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.byteCodeAppender.equals(((Simple) obj).byteCodeAppender);
        }

        public int hashCode() {
            return (getClass().hashCode() * 31) + this.byteCodeAppender.hashCode();
        }

        public Simple(ByteCodeAppender... byteCodeAppenderArr) {
            this.byteCodeAppender = new ByteCodeAppender.Compound(byteCodeAppenderArr);
        }

        public Simple(StackManipulation... stackManipulationArr) {
            this.byteCodeAppender = new ByteCodeAppender.Simple(stackManipulationArr);
        }

        public static Implementation of(Dispatcher dispatcher) {
            return of(dispatcher, 0);
        }

        public static Implementation of(Dispatcher dispatcher, int i) {
            return of(dispatcher, InstrumentedType.Prepareable.NoOp.INSTANCE, i);
        }

        public static Implementation of(Dispatcher dispatcher, InstrumentedType.Prepareable prepareable) {
            return of(dispatcher, prepareable, 0);
        }

        public static Implementation of(Dispatcher dispatcher, InstrumentedType.Prepareable prepareable, int i) {
            if (i < 0) {
                throw new IllegalArgumentException("Additional variable length cannot be negative: " + i);
            }
            return new ForDispatcher(dispatcher, prepareable, i);
        }

        @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
        public InstrumentedType prepare(InstrumentedType instrumentedType) {
            return instrumentedType;
        }

        @Override // net.bytebuddy.implementation.Implementation
        public ByteCodeAppender appender(Target target) {
            return this.byteCodeAppender;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/Implementation$Simple$ForDispatcher.class */
        public static class ForDispatcher implements Implementation {
            private final Dispatcher dispatcher;
            private final InstrumentedType.Prepareable prepareable;
            private final int additionalVariableLength;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.additionalVariableLength == ((ForDispatcher) obj).additionalVariableLength && this.dispatcher.equals(((ForDispatcher) obj).dispatcher) && this.prepareable.equals(((ForDispatcher) obj).prepareable);
            }

            public int hashCode() {
                return (((((getClass().hashCode() * 31) + this.dispatcher.hashCode()) * 31) + this.prepareable.hashCode()) * 31) + this.additionalVariableLength;
            }

            protected ForDispatcher(Dispatcher dispatcher, InstrumentedType.Prepareable prepareable, int i) {
                this.dispatcher = dispatcher;
                this.prepareable = prepareable;
                this.additionalVariableLength = i;
            }

            @Override // net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable
            public InstrumentedType prepare(InstrumentedType instrumentedType) {
                return this.prepareable.prepare(instrumentedType);
            }

            @Override // net.bytebuddy.implementation.Implementation
            public ByteCodeAppender appender(Target target) {
                return new Appender(target);
            }

            @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
            /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/Implementation$Simple$ForDispatcher$Appender.class */
            protected class Appender implements ByteCodeAppender {
                private final Target implementationTarget;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.implementationTarget.equals(((Appender) obj).implementationTarget) && ForDispatcher.this.equals(ForDispatcher.this);
                }

                public int hashCode() {
                    return (((getClass().hashCode() * 31) + this.implementationTarget.hashCode()) * 31) + ForDispatcher.this.hashCode();
                }

                protected Appender(Target target) {
                    this.implementationTarget = target;
                }

                @Override // net.bytebuddy.implementation.bytecode.ByteCodeAppender
                public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Context context, MethodDescription methodDescription) {
                    return new ByteCodeAppender.Size(ForDispatcher.this.dispatcher.apply(this.implementationTarget, methodDescription).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize() + ForDispatcher.this.additionalVariableLength);
                }
            }
        }
    }
}
