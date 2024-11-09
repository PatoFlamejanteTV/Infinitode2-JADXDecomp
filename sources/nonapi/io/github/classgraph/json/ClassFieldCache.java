package nonapi.io.github.classgraph.json;

import java.lang.reflect.Constructor;
import java.util.AbstractList;
import java.util.AbstractMap;
import java.util.AbstractQueue;
import java.util.AbstractSequentialList;
import java.util.AbstractSet;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;
import nonapi.io.github.classgraph.reflection.ReflectionUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/json/ClassFieldCache.class */
public class ClassFieldCache {
    private final boolean resolveTypes;
    private final boolean onlySerializePublicFields;
    private static final Constructor<?> NO_CONSTRUCTOR;
    ReflectionUtils reflectionUtils;
    private final Map<Class<?>, ClassFields> classToClassFields = new HashMap();
    private final Map<Class<?>, Constructor<?>> defaultConstructorForConcreteType = new HashMap();
    private final Map<Class<?>, Constructor<?>> constructorForConcreteTypeWithSizeHint = new HashMap();

    /* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/json/ClassFieldCache$NoConstructor.class */
    private static class NoConstructor {
    }

    static {
        try {
            NO_CONSTRUCTOR = NoConstructor.class.getDeclaredConstructor(new Class[0]);
        } catch (NoSuchMethodException | SecurityException e) {
            throw new RuntimeException("Could not find or access constructor for " + NoConstructor.class.getName(), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClassFieldCache(boolean z, boolean z2, ReflectionUtils reflectionUtils) {
        this.resolveTypes = z;
        this.onlySerializePublicFields = !z && z2;
        this.reflectionUtils = reflectionUtils;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClassFields get(Class<?> cls) {
        ClassFields classFields = this.classToClassFields.get(cls);
        ClassFields classFields2 = classFields;
        if (classFields == null) {
            Map<Class<?>, ClassFields> map = this.classToClassFields;
            ClassFields classFields3 = new ClassFields(cls, this.resolveTypes, this.onlySerializePublicFields, this, this.reflectionUtils);
            classFields2 = classFields3;
            map.put(cls, classFields3);
        }
        return classFields2;
    }

    private static Class<?> getConcreteType(Class<?> cls, boolean z) {
        if (cls == Map.class || cls == AbstractMap.class || cls == HashMap.class) {
            return HashMap.class;
        }
        if (cls == ConcurrentMap.class || cls == ConcurrentHashMap.class) {
            return ConcurrentHashMap.class;
        }
        if (cls == SortedMap.class || cls == NavigableMap.class || cls == TreeMap.class) {
            return TreeMap.class;
        }
        if (cls == ConcurrentNavigableMap.class || cls == ConcurrentSkipListMap.class) {
            return ConcurrentSkipListMap.class;
        }
        if (cls == List.class || cls == AbstractList.class || cls == ArrayList.class || cls == Collection.class) {
            return ArrayList.class;
        }
        if (cls == AbstractSequentialList.class || cls == LinkedList.class) {
            return LinkedList.class;
        }
        if (cls == Set.class || cls == AbstractSet.class || cls == HashSet.class) {
            return HashSet.class;
        }
        if (cls == SortedSet.class || cls == TreeSet.class) {
            return TreeSet.class;
        }
        if (cls == Queue.class || cls == AbstractQueue.class || cls == Deque.class || cls == ArrayDeque.class) {
            return ArrayDeque.class;
        }
        if (cls == BlockingQueue.class || cls == LinkedBlockingQueue.class) {
            return LinkedBlockingQueue.class;
        }
        if (cls == BlockingDeque.class || cls == LinkedBlockingDeque.class) {
            return LinkedBlockingDeque.class;
        }
        if (cls == TransferQueue.class || cls == LinkedTransferQueue.class) {
            return LinkedTransferQueue.class;
        }
        if (z) {
            return null;
        }
        return cls;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Constructor<?> getDefaultConstructorForConcreteTypeOf(Class<?> cls) {
        if (cls == null) {
            throw new IllegalArgumentException("Class reference cannot be null");
        }
        Constructor<?> constructor = this.defaultConstructorForConcreteType.get(cls);
        if (constructor != null) {
            return constructor;
        }
        Class<?> concreteType = getConcreteType(cls, false);
        while (true) {
            Class<?> cls2 = concreteType;
            if (cls2 == null || (cls2 == Object.class && cls != Object.class)) {
                break;
            }
            try {
                Constructor<?> declaredConstructor = cls2.getDeclaredConstructor(new Class[0]);
                JSONUtils.makeAccessible(declaredConstructor, this.reflectionUtils);
                this.defaultConstructorForConcreteType.put(cls, declaredConstructor);
                return declaredConstructor;
            } catch (ReflectiveOperationException | SecurityException unused) {
                concreteType = cls2.getSuperclass();
            }
        }
        throw new IllegalArgumentException("Class " + cls.getName() + " does not have an accessible default (no-arg) constructor");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Constructor<?> getConstructorWithSizeHintForConcreteTypeOf(Class<?> cls) {
        Constructor<?> constructor = this.constructorForConcreteTypeWithSizeHint.get(cls);
        if (constructor == NO_CONSTRUCTOR) {
            return null;
        }
        if (constructor != null) {
            return constructor;
        }
        Class<?> concreteType = getConcreteType(cls, true);
        if (concreteType != null) {
            Class<?> cls2 = concreteType;
            while (true) {
                Class<?> cls3 = cls2;
                if (cls3 == null || (cls3 == Object.class && cls != Object.class)) {
                    break;
                }
                try {
                    Constructor<?> declaredConstructor = cls3.getDeclaredConstructor(Integer.TYPE);
                    JSONUtils.makeAccessible(declaredConstructor, this.reflectionUtils);
                    this.constructorForConcreteTypeWithSizeHint.put(cls, declaredConstructor);
                    return declaredConstructor;
                } catch (ReflectiveOperationException | SecurityException unused) {
                    cls2 = cls3.getSuperclass();
                }
            }
        }
        this.constructorForConcreteTypeWithSizeHint.put(cls, NO_CONSTRUCTOR);
        return null;
    }
}
