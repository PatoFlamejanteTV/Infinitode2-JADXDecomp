package nonapi.io.github.classgraph.concurrency;

import java.lang.Exception;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import nonapi.io.github.classgraph.utils.LogNode;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/concurrency/SingletonMap.class */
public abstract class SingletonMap<K, V, E extends Exception> {
    private final ConcurrentMap<K, SingletonHolder<V>> map = new ConcurrentHashMap();

    @FunctionalInterface
    /* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/concurrency/SingletonMap$NewInstanceFactory.class */
    public interface NewInstanceFactory<V, E extends Exception> {
        V newInstance();
    }

    public abstract V newInstance(K k, LogNode logNode);

    /* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/concurrency/SingletonMap$NullSingletonException.class */
    public static class NullSingletonException extends Exception {
        static final long serialVersionUID = 1;

        public <K> NullSingletonException(K k) {
            super("newInstance returned null for key " + k);
        }
    }

    /* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/concurrency/SingletonMap$NewInstanceException.class */
    public static class NewInstanceException extends Exception {
        static final long serialVersionUID = 1;

        public <K> NewInstanceException(K k, Throwable th) {
            super("newInstance threw an exception for key " + k + " : " + th, th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/concurrency/SingletonMap$SingletonHolder.class */
    public static class SingletonHolder<V> {
        private volatile V singleton;
        private final CountDownLatch initialized;

        private SingletonHolder() {
            this.initialized = new CountDownLatch(1);
        }

        void set(V v) {
            if (this.initialized.getCount() < 1) {
                throw new IllegalArgumentException("Singleton already initialized");
            }
            this.singleton = v;
            this.initialized.countDown();
            if (this.initialized.getCount() != 0) {
                throw new IllegalArgumentException("Singleton initialized more than once");
            }
        }

        V get() {
            this.initialized.await();
            return this.singleton;
        }
    }

    public V get(K k, LogNode logNode, NewInstanceFactory<V, E> newInstanceFactory) {
        SingletonHolder<V> singletonHolder = this.map.get(k);
        V v = null;
        if (singletonHolder != null) {
            v = singletonHolder.get();
        } else {
            SingletonHolder<V> singletonHolder2 = new SingletonHolder<>();
            SingletonHolder<V> putIfAbsent = this.map.putIfAbsent(k, singletonHolder2);
            if (putIfAbsent != null) {
                v = putIfAbsent.get();
            } else {
                try {
                    if (newInstanceFactory != null) {
                        v = newInstanceFactory.newInstance();
                    } else {
                        v = newInstance(k, logNode);
                    }
                    singletonHolder2.set(v);
                } catch (Throwable th) {
                    singletonHolder2.set(v);
                    throw new NewInstanceException(k, th);
                }
            }
        }
        if (v == null) {
            throw new NullSingletonException(k);
        }
        return v;
    }

    public V get(K k, LogNode logNode) {
        return get(k, logNode, null);
    }

    public List<V> values() {
        ArrayList arrayList = new ArrayList(this.map.size());
        Iterator<Map.Entry<K, SingletonHolder<V>>> it = this.map.entrySet().iterator();
        while (it.hasNext()) {
            V v = it.next().getValue().get();
            if (v != null) {
                arrayList.add(v);
            }
        }
        return arrayList;
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public List<Map.Entry<K, V>> entries() {
        ArrayList arrayList = new ArrayList(this.map.size());
        for (Map.Entry<K, SingletonHolder<V>> entry : this.map.entrySet()) {
            arrayList.add(new AbstractMap.SimpleEntry(entry.getKey(), entry.getValue().get()));
        }
        return arrayList;
    }

    public V remove(K k) {
        SingletonHolder<V> remove = this.map.remove(k);
        if (remove == null) {
            return null;
        }
        return remove.get();
    }

    public void clear() {
        this.map.clear();
    }
}
