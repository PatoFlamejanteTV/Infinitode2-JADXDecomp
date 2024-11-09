package nonapi.io.github.classgraph.recycler;

import java.lang.Exception;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/recycler/RecycleOnClose.class */
public class RecycleOnClose<T, E extends Exception> implements AutoCloseable {
    private final Recycler<T, E> recycler;
    private final T instance;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RecycleOnClose(Recycler<T, E> recycler, T t) {
        this.recycler = recycler;
        this.instance = t;
    }

    public T get() {
        return this.instance;
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        this.recycler.recycle(this.instance);
    }
}
