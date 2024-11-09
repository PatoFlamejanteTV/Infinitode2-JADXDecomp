package net.bytebuddy.utility;

import java.io.Serializable;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.util.List;
import net.bytebuddy.build.CachedReturnPlugin;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.utility.nullability.MaybeNull;
import org.lwjgl.opengl.GL11;

/* loaded from: infinitode-2.jar:net/bytebuddy/utility/JavaType.class */
public enum JavaType {
    CONSTABLE("java.lang.constant.Constable", GL11.GL_3D, TypeDescription.UNDEFINED, new TypeDefinition[0]),
    TYPE_DESCRIPTOR("java.lang.invoke.TypeDescriptor", GL11.GL_3D, TypeDescription.UNDEFINED, new TypeDefinition[0]),
    TYPE_DESCRIPTOR_OF_FIELD("java.lang.invoke.TypeDescriptor$OfField", GL11.GL_3D, TypeDescription.UNDEFINED, TYPE_DESCRIPTOR.getTypeStub()),
    TYPE_DESCRIPTOR_OF_METHOD("java.lang.invoke.TypeDescriptor$OfMethod", GL11.GL_3D, TypeDescription.UNDEFINED, TYPE_DESCRIPTOR.getTypeStub()),
    CONSTANT_DESCRIPTION("java.lang.constant.ConstantDesc", GL11.GL_3D, TypeDescription.UNDEFINED, new TypeDefinition[0]),
    DYNAMIC_CONSTANT_DESCRIPTION("java.lang.constant.DynamicConstantDesc", 1025, TypeDescription.ForLoadedType.of(Object.class), CONSTANT_DESCRIPTION.getTypeStub()),
    CLASS_DESCRIPTION("java.lang.constant.ClassDesc", GL11.GL_3D, TypeDescription.UNDEFINED, CONSTANT_DESCRIPTION.getTypeStub(), TYPE_DESCRIPTOR_OF_FIELD.getTypeStub()),
    METHOD_TYPE_DESCRIPTION("java.lang.constant.MethodTypeDesc", GL11.GL_3D, TypeDescription.UNDEFINED, CONSTANT_DESCRIPTION.getTypeStub(), TYPE_DESCRIPTOR_OF_METHOD.getTypeStub()),
    METHOD_HANDLE_DESCRIPTION("java.lang.constant.MethodHandleDesc", GL11.GL_3D, TypeDescription.UNDEFINED, CONSTANT_DESCRIPTION.getTypeStub()),
    DIRECT_METHOD_HANDLE_DESCRIPTION("java.lang.constant.DirectMethodHandleDesc", GL11.GL_3D, TypeDescription.UNDEFINED, METHOD_HANDLE_DESCRIPTION.getTypeStub()),
    METHOD_HANDLE("java.lang.invoke.MethodHandle", 1025, TypeDescription.ForLoadedType.of(Object.class), CONSTABLE.getTypeStub()),
    METHOD_HANDLES("java.lang.invoke.MethodHandles", 1, Object.class, new Type[0]),
    METHOD_TYPE("java.lang.invoke.MethodType", 17, TypeDescription.ForLoadedType.of(Object.class), CONSTABLE.getTypeStub(), TYPE_DESCRIPTOR_OF_METHOD.getTypeStub(), TypeDescription.ForLoadedType.of(Serializable.class)),
    METHOD_HANDLES_LOOKUP("java.lang.invoke.MethodHandles$Lookup", 25, Object.class, new Type[0]),
    CALL_SITE("java.lang.invoke.CallSite", 1025, Object.class, new Type[0]),
    VAR_HANDLE("java.lang.invoke.VarHandle", 1025, TypeDescription.Generic.OfNonGenericType.ForLoadedType.of(Object.class), CONSTABLE.getTypeStub()),
    PARAMETER("java.lang.reflect.Parameter", 17, Object.class, AnnotatedElement.class),
    EXECUTABLE("java.lang.reflect.Executable", 1025, AccessibleObject.class, Member.class, GenericDeclaration.class),
    MODULE("java.lang.Module", 17, Object.class, AnnotatedElement.class),
    CONSTANT_BOOTSTRAPS("java.lang.invoke.ConstantBootstraps", 17, Object.class, new Type[0]),
    RECORD("java.lang.Record", 1025, Object.class, new Type[0]),
    OBJECT_METHODS("java.lang.runtime.ObjectMethods", 1, Object.class, new Type[0]),
    ACCESS_CONTROL_CONTEXT("java.security.AccessControlContext", 17, TypeDescription.UNDEFINED, new TypeDefinition[0]);

    private final TypeDescription typeDescription;
    private transient /* synthetic */ Class loaded;
    private transient /* synthetic */ Boolean available;

    JavaType(String str, int i, @MaybeNull Type type, Type... typeArr) {
        this(str, i, type == null ? TypeDescription.Generic.UNDEFINED : TypeDefinition.Sort.describe(type), new TypeList.Generic.ForLoadedTypes(typeArr));
    }

    JavaType(String str, int i, @MaybeNull TypeDefinition typeDefinition, TypeDefinition... typeDefinitionArr) {
        this(str, i, typeDefinition == null ? TypeDescription.Generic.UNDEFINED : typeDefinition.asGenericType(), new TypeList.Generic.Explicit(typeDefinitionArr));
    }

    JavaType(String str, int i, @MaybeNull TypeDescription.Generic generic, TypeList.Generic generic2) {
        this.typeDescription = new LatentTypeWithSimpleName(str, i, generic, generic2);
    }

    public final TypeDescription getTypeStub() {
        return this.typeDescription;
    }

    @CachedReturnPlugin.Enhance("loaded")
    public final Class<?> load() {
        Class<?> cls = this.loaded != null ? null : Class.forName(this.typeDescription.getName(), false, ClassLoadingStrategy.BOOTSTRAP_LOADER);
        Class<?> cls2 = cls;
        if (cls == null) {
            cls2 = this.loaded;
        } else {
            this.loaded = cls2;
        }
        return cls2;
    }

    public final TypeDescription loadAsDescription() {
        return TypeDescription.ForLoadedType.of(load());
    }

    public final boolean isAvailable() {
        return doIsAvailable().booleanValue();
    }

    @CachedReturnPlugin.Enhance("available")
    private Boolean doIsAvailable() {
        Boolean bool;
        if (this.available != null) {
            bool = null;
        } else {
            try {
                load();
                bool = Boolean.TRUE;
            } catch (ClassNotFoundException unused) {
                bool = Boolean.FALSE;
            }
        }
        Boolean bool2 = bool;
        if (bool == null) {
            bool2 = this.available;
        } else {
            this.available = bool2;
        }
        return bool2;
    }

    public final boolean isInstance(Object obj) {
        if (!isAvailable()) {
            return false;
        }
        try {
            return load().isInstance(obj);
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/utility/JavaType$LatentTypeWithSimpleName.class */
    protected static class LatentTypeWithSimpleName extends TypeDescription.Latent {
        protected LatentTypeWithSimpleName(String str, int i, @MaybeNull TypeDescription.Generic generic, List<? extends TypeDescription.Generic> list) {
            super(str, i, generic, list);
        }

        @Override // net.bytebuddy.description.type.TypeDescription.AbstractBase.OfSimpleType, net.bytebuddy.description.type.TypeDescription
        public String getSimpleName() {
            String name = getName();
            int max = Math.max(name.lastIndexOf(36), name.lastIndexOf(46));
            return max == -1 ? name : name.substring(max + 1);
        }
    }
}
