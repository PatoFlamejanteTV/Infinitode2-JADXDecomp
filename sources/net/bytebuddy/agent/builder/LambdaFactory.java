package net.bytebuddy.agent.builder;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.lang.instrument.ClassFileTransformer;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.loading.ClassInjector;
import net.bytebuddy.implementation.auxiliary.TypeProxy;
import net.bytebuddy.utility.nullability.MaybeNull;

@HashCodeAndEqualsPlugin.Enhance
/* loaded from: infinitode-2.jar:net/bytebuddy/agent/builder/LambdaFactory.class */
public class LambdaFactory {
    private static final String FIELD_NAME = "CLASS_FILE_TRANSFORMERS";

    @SuppressFBWarnings(value = {"MS_MUTABLE_COLLECTION_PKGPROTECT"}, justification = "The field must be accessible by different class loader instances.")
    public static final Map<ClassFileTransformer, LambdaFactory> CLASS_FILE_TRANSFORMERS = new ConcurrentHashMap();
    private final Object target;
    private final Method dispatcher;

    public boolean equals(@MaybeNull Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.target.equals(((LambdaFactory) obj).target) && this.dispatcher.equals(((LambdaFactory) obj).dispatcher);
    }

    public int hashCode() {
        return (((getClass().hashCode() * 31) + this.target.hashCode()) * 31) + this.dispatcher.hashCode();
    }

    public LambdaFactory(Object obj, Method method) {
        this.target = obj;
        this.dispatcher = method;
    }

    /* JADX WARN: Type inference failed for: r0v10, types: [java.lang.Throwable, java.util.Map] */
    public static boolean register(ClassFileTransformer classFileTransformer, Object obj) {
        ?? r0;
        boolean isEmpty;
        try {
            TypeDescription of = TypeDescription.ForLoadedType.of(LambdaFactory.class);
            Class<?> cls = ClassInjector.UsingReflection.ofSystemClassLoader().inject(Collections.singletonMap(of, ClassFileLocator.ForClassLoader.read((Class<?>) LambdaFactory.class))).get(of);
            r0 = (Map) cls.getField(FIELD_NAME).get(null);
            synchronized (r0) {
                try {
                    isEmpty = r0.isEmpty();
                } finally {
                    r0.put(classFileTransformer, cls.getConstructor(Object.class, Method.class).newInstance(obj, obj.getClass().getMethod(TypeProxy.REFLECTION_METHOD, Object.class, String.class, Object.class, Object.class, Object.class, Object.class, Boolean.TYPE, List.class, List.class, Collection.class)));
                }
            }
            return isEmpty;
        } catch (RuntimeException e) {
            throw r0;
        } catch (Exception e2) {
            throw new IllegalStateException("Could not register class file transformer", e2);
        }
    }

    public static boolean release(ClassFileTransformer classFileTransformer) {
        boolean z;
        try {
            Map map = (Map) ClassLoader.getSystemClassLoader().loadClass(LambdaFactory.class.getName()).getField(FIELD_NAME).get(null);
            synchronized (map) {
                z = map.remove(classFileTransformer) != null && map.isEmpty();
            }
            return z;
        } catch (RuntimeException e) {
            throw th;
        } catch (Exception e2) {
            throw new IllegalStateException("Could not release class file transformer", e2);
        }
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.Throwable, byte[]] */
    private byte[] invoke(Object obj, String str, Object obj2, Object obj3, Object obj4, Object obj5, boolean z, List<Class<?>> list, List<?> list2, Collection<ClassFileTransformer> collection) {
        ?? r0;
        try {
            r0 = (byte[]) this.dispatcher.invoke(this.target, obj, str, obj2, obj3, obj4, obj5, Boolean.valueOf(z), list, list2, collection);
            return r0;
        } catch (RuntimeException e) {
            throw r0;
        } catch (Exception e2) {
            throw new IllegalStateException("Cannot create class for lambda expression", e2);
        }
    }

    public static byte[] make(Object obj, String str, Object obj2, Object obj3, Object obj4, Object obj5, boolean z, List<Class<?>> list, List<?> list2) {
        return CLASS_FILE_TRANSFORMERS.values().iterator().next().invoke(obj, str, obj2, obj3, obj4, obj5, z, list, list2, CLASS_FILE_TRANSFORMERS.keySet());
    }
}
