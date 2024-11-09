package net.bytebuddy.implementation;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.bytebuddy.build.AccessControllerPlugin;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.utility.JavaModule;
import net.bytebuddy.utility.nullability.MaybeNull;
import net.bytebuddy.utility.privilege.SetAccessibleAction;

/* loaded from: infinitode-2.jar:net/bytebuddy/implementation/LoadedTypeInitializer.class */
public interface LoadedTypeInitializer {
    void onLoad(Class<?> cls);

    boolean isAlive();

    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/LoadedTypeInitializer$NoOp.class */
    public enum NoOp implements LoadedTypeInitializer {
        INSTANCE;

        @Override // net.bytebuddy.implementation.LoadedTypeInitializer
        public final void onLoad(Class<?> cls) {
        }

        @Override // net.bytebuddy.implementation.LoadedTypeInitializer
        public final boolean isAlive() {
            return false;
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/LoadedTypeInitializer$ForStaticField.class */
    public static class ForStaticField implements Serializable, LoadedTypeInitializer {
        private static final long serialVersionUID = 1;
        private final String fieldName;
        private final Object value;

        @MaybeNull
        @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.IGNORE)
        private final transient Object accessControlContext = getContext();
        private static final boolean ACCESS_CONTROLLER;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.fieldName.equals(((ForStaticField) obj).fieldName) && this.value.equals(((ForStaticField) obj).value);
        }

        public int hashCode() {
            return (((getClass().hashCode() * 31) + this.fieldName.hashCode()) * 31) + this.value.hashCode();
        }

        static {
            try {
                Class.forName("java.security.AccessController", false, null);
                ACCESS_CONTROLLER = Boolean.parseBoolean(System.getProperty("net.bytebuddy.securitymanager", "true"));
            } catch (ClassNotFoundException unused) {
                ACCESS_CONTROLLER = false;
            } catch (SecurityException unused2) {
                ACCESS_CONTROLLER = true;
            }
        }

        public ForStaticField(String str, Object obj) {
            this.fieldName = str;
            this.value = obj;
        }

        @MaybeNull
        @AccessControllerPlugin.Enhance
        private static Object getContext() {
            if (ACCESS_CONTROLLER) {
                return AccessController.getContext();
            }
            return null;
        }

        @AccessControllerPlugin.Enhance
        private static <T> T doPrivileged(PrivilegedAction<T> privilegedAction, @MaybeNull Object obj) {
            return ACCESS_CONTROLLER ? (T) AccessController.doPrivileged(privilegedAction, (AccessControlContext) obj) : privilegedAction.run();
        }

        private Object readResolve() {
            return new ForStaticField(this.fieldName, this.value);
        }

        @Override // net.bytebuddy.implementation.LoadedTypeInitializer
        @SuppressFBWarnings(value = {"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"}, justification = "Modules are assumed available when module system is supported")
        public void onLoad(Class<?> cls) {
            try {
                Field declaredField = cls.getDeclaredField(this.fieldName);
                if (!Modifier.isPublic(declaredField.getModifiers()) || !Modifier.isPublic(declaredField.getDeclaringClass().getModifiers()) || (JavaModule.isSupported() && !JavaModule.ofType(cls).isExported(TypeDescription.ForLoadedType.of(cls).getPackage(), JavaModule.ofType(ForStaticField.class)))) {
                    doPrivileged(new SetAccessibleAction(declaredField), this.accessControlContext);
                }
                declaredField.set(null, this.value);
            } catch (IllegalAccessException e) {
                throw new IllegalArgumentException("Cannot access " + this.fieldName + " from " + cls, e);
            } catch (NoSuchFieldException e2) {
                throw new IllegalStateException("There is no field " + this.fieldName + " defined on " + cls, e2);
            }
        }

        @Override // net.bytebuddy.implementation.LoadedTypeInitializer
        public boolean isAlive() {
            return true;
        }
    }

    @SuppressFBWarnings(value = {"SE_BAD_FIELD"}, justification = "Serialization is considered opt-in for a rare use case")
    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/LoadedTypeInitializer$Compound.class */
    public static class Compound implements Serializable, LoadedTypeInitializer {
        private static final long serialVersionUID = 1;
        private final List<LoadedTypeInitializer> loadedTypeInitializers;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.loadedTypeInitializers.equals(((Compound) obj).loadedTypeInitializers);
        }

        public int hashCode() {
            return (getClass().hashCode() * 31) + this.loadedTypeInitializers.hashCode();
        }

        public Compound(LoadedTypeInitializer... loadedTypeInitializerArr) {
            this((List<? extends LoadedTypeInitializer>) Arrays.asList(loadedTypeInitializerArr));
        }

        public Compound(List<? extends LoadedTypeInitializer> list) {
            this.loadedTypeInitializers = new ArrayList();
            for (LoadedTypeInitializer loadedTypeInitializer : list) {
                if (loadedTypeInitializer instanceof Compound) {
                    this.loadedTypeInitializers.addAll(((Compound) loadedTypeInitializer).loadedTypeInitializers);
                } else if (!(loadedTypeInitializer instanceof NoOp)) {
                    this.loadedTypeInitializers.add(loadedTypeInitializer);
                }
            }
        }

        @Override // net.bytebuddy.implementation.LoadedTypeInitializer
        public void onLoad(Class<?> cls) {
            Iterator<LoadedTypeInitializer> it = this.loadedTypeInitializers.iterator();
            while (it.hasNext()) {
                it.next().onLoad(cls);
            }
        }

        @Override // net.bytebuddy.implementation.LoadedTypeInitializer
        public boolean isAlive() {
            Iterator<LoadedTypeInitializer> it = this.loadedTypeInitializers.iterator();
            while (it.hasNext()) {
                if (it.next().isAlive()) {
                    return true;
                }
            }
            return false;
        }
    }
}
