package net.bytebuddy;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import net.bytebuddy.build.CachedReturnPlugin;
import net.bytebuddy.utility.CompoundList;
import net.bytebuddy.utility.nullability.AlwaysNull;
import net.bytebuddy.utility.nullability.MaybeNull;

/* loaded from: infinitode-2.jar:net/bytebuddy/TypeCache.class */
public class TypeCache<T> extends ReferenceQueue<ClassLoader> {

    @AlwaysNull
    private static final Class<?> NOT_FOUND = null;
    protected final Sort sort;
    protected final ConcurrentMap<StorageKey, ConcurrentMap<T, Object>> cache;

    public TypeCache() {
        this(Sort.STRONG);
    }

    public TypeCache(Sort sort) {
        this.sort = sort;
        this.cache = new ConcurrentHashMap();
    }

    @MaybeNull
    @SuppressFBWarnings(value = {"GC_UNRELATED_TYPES"}, justification = "Cross-comparison is intended.")
    public Class<?> find(@MaybeNull ClassLoader classLoader, T t) {
        ConcurrentMap<T, Object> concurrentMap = this.cache.get(new LookupKey(classLoader));
        if (concurrentMap == null) {
            return NOT_FOUND;
        }
        Object obj = concurrentMap.get(t);
        if (obj == null) {
            return NOT_FOUND;
        }
        if (obj instanceof Reference) {
            return (Class) ((Reference) obj).get();
        }
        return (Class) obj;
    }

    @SuppressFBWarnings(value = {"GC_UNRELATED_TYPES"}, justification = "Cross-comparison is intended.")
    public Class<?> insert(@MaybeNull ClassLoader classLoader, T t, Class<?> cls) {
        ConcurrentMap<T, Object> concurrentMap = this.cache.get(new LookupKey(classLoader));
        ConcurrentMap<T, Object> concurrentMap2 = concurrentMap;
        if (concurrentMap == null) {
            concurrentMap2 = new ConcurrentHashMap();
            ConcurrentMap<T, Object> putIfAbsent = this.cache.putIfAbsent(new StorageKey(classLoader, this), concurrentMap2);
            if (putIfAbsent != null) {
                concurrentMap2 = putIfAbsent;
            }
        }
        Object wrap = this.sort.wrap(cls);
        Object putIfAbsent2 = concurrentMap2.putIfAbsent(t, wrap);
        while (putIfAbsent2 != null) {
            Class<?> cls2 = (Class) (putIfAbsent2 instanceof Reference ? ((Reference) putIfAbsent2).get() : putIfAbsent2);
            if (cls2 != null) {
                return cls2;
            }
            if (concurrentMap2.remove(t, putIfAbsent2)) {
                putIfAbsent2 = concurrentMap2.putIfAbsent(t, wrap);
            } else {
                Object obj = concurrentMap2.get(t);
                putIfAbsent2 = obj;
                if (obj == null) {
                    putIfAbsent2 = concurrentMap2.putIfAbsent(t, wrap);
                }
            }
        }
        return cls;
    }

    public Class<?> findOrInsert(ClassLoader classLoader, T t, Callable<Class<?>> callable) {
        Class<?> find = find(classLoader, t);
        if (find != null) {
            return find;
        }
        try {
            return insert(classLoader, t, callable.call());
        } catch (Throwable th) {
            throw new IllegalArgumentException("Could not create type", th);
        }
    }

    public Class<?> findOrInsert(@MaybeNull ClassLoader classLoader, T t, Callable<Class<?>> callable, Object obj) {
        Class<?> findOrInsert;
        Class<?> find = find(classLoader, t);
        if (find != null) {
            return find;
        }
        synchronized (obj) {
            findOrInsert = findOrInsert(classLoader, t, callable);
        }
        return findOrInsert;
    }

    public void expungeStaleEntries() {
        while (true) {
            Reference<? extends T> poll = poll();
            if (poll != null) {
                this.cache.remove(poll);
            } else {
                return;
            }
        }
    }

    public void clear() {
        this.cache.clear();
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/TypeCache$Sort.class */
    public enum Sort {
        WEAK { // from class: net.bytebuddy.TypeCache.Sort.1
            @Override // net.bytebuddy.TypeCache.Sort
            protected final /* bridge */ /* synthetic */ Object wrap(Class cls) {
                return wrap((Class<?>) cls);
            }

            @Override // net.bytebuddy.TypeCache.Sort
            protected final Reference<Class<?>> wrap(Class<?> cls) {
                return new WeakReference(cls);
            }
        },
        SOFT { // from class: net.bytebuddy.TypeCache.Sort.2
            @Override // net.bytebuddy.TypeCache.Sort
            protected final /* bridge */ /* synthetic */ Object wrap(Class cls) {
                return wrap((Class<?>) cls);
            }

            @Override // net.bytebuddy.TypeCache.Sort
            protected final Reference<Class<?>> wrap(Class<?> cls) {
                return new SoftReference(cls);
            }
        },
        STRONG { // from class: net.bytebuddy.TypeCache.Sort.3
            @Override // net.bytebuddy.TypeCache.Sort
            protected final /* bridge */ /* synthetic */ Object wrap(Class cls) {
                return wrap((Class<?>) cls);
            }

            @Override // net.bytebuddy.TypeCache.Sort
            protected final Class<?> wrap(Class<?> cls) {
                return cls;
            }
        };

        protected abstract Object wrap(Class<?> cls);

        /* synthetic */ Sort(byte b2) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: infinitode-2.jar:net/bytebuddy/TypeCache$LookupKey.class */
    public static class LookupKey {

        @MaybeNull
        private final ClassLoader classLoader;
        private final int hashCode;

        protected LookupKey(@MaybeNull ClassLoader classLoader) {
            this.classLoader = classLoader;
            this.hashCode = System.identityHashCode(classLoader);
        }

        public int hashCode() {
            return this.hashCode;
        }

        @SuppressFBWarnings(value = {"EQ_CHECK_FOR_OPERAND_NOT_COMPATIBLE_WITH_THIS"}, justification = "Cross-comparison is intended.")
        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof LookupKey) {
                return this.classLoader == ((LookupKey) obj).classLoader;
            }
            if (obj instanceof StorageKey) {
                StorageKey storageKey = (StorageKey) obj;
                return this.hashCode == storageKey.hashCode && this.classLoader == storageKey.get();
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: infinitode-2.jar:net/bytebuddy/TypeCache$StorageKey.class */
    public static class StorageKey extends WeakReference<ClassLoader> {
        private final int hashCode;

        protected StorageKey(@MaybeNull ClassLoader classLoader, ReferenceQueue<? super ClassLoader> referenceQueue) {
            super(classLoader, referenceQueue);
            this.hashCode = System.identityHashCode(classLoader);
        }

        public int hashCode() {
            return this.hashCode;
        }

        @SuppressFBWarnings(value = {"EQ_CHECK_FOR_OPERAND_NOT_COMPATIBLE_WITH_THIS"}, justification = "Cross-comparison is intended.")
        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof LookupKey) {
                LookupKey lookupKey = (LookupKey) obj;
                return this.hashCode == lookupKey.hashCode && get() == lookupKey.classLoader;
            }
            if (obj instanceof StorageKey) {
                StorageKey storageKey = (StorageKey) obj;
                return this.hashCode == storageKey.hashCode && get() == storageKey.get();
            }
            return false;
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/TypeCache$WithInlineExpunction.class */
    public static class WithInlineExpunction<S> extends TypeCache<S> {
        public WithInlineExpunction() {
            this(Sort.STRONG);
        }

        public WithInlineExpunction(Sort sort) {
            super(sort);
        }

        @Override // net.bytebuddy.TypeCache
        public Class<?> find(@MaybeNull ClassLoader classLoader, S s) {
            try {
                return super.find(classLoader, s);
            } finally {
                expungeStaleEntries();
            }
        }

        @Override // net.bytebuddy.TypeCache
        public Class<?> insert(@MaybeNull ClassLoader classLoader, S s, Class<?> cls) {
            try {
                return super.insert(classLoader, s, cls);
            } finally {
                expungeStaleEntries();
            }
        }

        @Override // net.bytebuddy.TypeCache
        public Class<?> findOrInsert(ClassLoader classLoader, S s, Callable<Class<?>> callable) {
            try {
                return super.findOrInsert(classLoader, s, callable);
            } finally {
                expungeStaleEntries();
            }
        }

        @Override // net.bytebuddy.TypeCache
        public Class<?> findOrInsert(@MaybeNull ClassLoader classLoader, S s, Callable<Class<?>> callable, Object obj) {
            try {
                return super.findOrInsert(classLoader, s, callable, obj);
            } finally {
                expungeStaleEntries();
            }
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/TypeCache$SimpleKey.class */
    public static class SimpleKey {
        private final Set<String> types;
        private transient /* synthetic */ int hashCode;

        public SimpleKey(Class<?> cls, Class<?>... clsArr) {
            this(cls, Arrays.asList(clsArr));
        }

        public SimpleKey(Class<?> cls, Collection<? extends Class<?>> collection) {
            this(CompoundList.of(cls, new ArrayList(collection)));
        }

        public SimpleKey(Collection<? extends Class<?>> collection) {
            this.types = new HashSet();
            Iterator<? extends Class<?>> it = collection.iterator();
            while (it.hasNext()) {
                this.types.add(it.next().getName());
            }
        }

        @CachedReturnPlugin.Enhance("hashCode")
        public int hashCode() {
            int hashCode = this.hashCode != 0 ? 0 : this.types.hashCode();
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
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.types.equals(((SimpleKey) obj).types);
        }
    }
}
