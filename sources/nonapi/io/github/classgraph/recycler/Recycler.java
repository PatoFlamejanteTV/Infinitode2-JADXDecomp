package nonapi.io.github.classgraph.recycler;

import java.lang.Exception;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/recycler/Recycler.class */
public abstract class Recycler<T, E extends Exception> implements AutoCloseable {
    private final Set<T> usedInstances = Collections.newSetFromMap(new ConcurrentHashMap());
    private final Queue<T> unusedInstances = new ConcurrentLinkedQueue();

    public abstract T newInstance();

    public T acquire() {
        T t;
        T poll = this.unusedInstances.poll();
        if (poll == null) {
            T newInstance = newInstance();
            if (newInstance == null) {
                throw new NullPointerException("Failed to allocate a new recyclable instance");
            }
            t = newInstance;
        } else {
            t = poll;
        }
        this.usedInstances.add(t);
        return t;
    }

    public RecycleOnClose<T, E> acquireRecycleOnClose() {
        return new RecycleOnClose<>(this, acquire());
    }

    public final void recycle(T t) {
        if (t != null) {
            if (!this.usedInstances.remove(t)) {
                throw new IllegalArgumentException("Tried to recycle an instance that was not in use");
            }
            if (t instanceof Resettable) {
                ((Resettable) t).reset();
            }
            if (!this.unusedInstances.add(t)) {
                throw new IllegalArgumentException("Tried to recycle an instance twice");
            }
        }
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        while (true) {
            T poll = this.unusedInstances.poll();
            if (poll != null) {
                if (poll instanceof AutoCloseable) {
                    try {
                        ((AutoCloseable) poll).close();
                    } catch (Exception unused) {
                    }
                }
            } else {
                return;
            }
        }
    }

    public void forceClose() {
        Iterator it = new ArrayList(this.usedInstances).iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (this.usedInstances.remove(next)) {
                this.unusedInstances.add(next);
            }
        }
        close();
    }
}
