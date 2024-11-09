package nonapi.io.github.classgraph.concurrency;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/concurrency/SimpleThreadFactory.class */
public class SimpleThreadFactory implements ThreadFactory {
    private final String threadNamePrefix;
    private static final AtomicInteger threadIdx = new AtomicInteger();
    private final boolean daemon;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SimpleThreadFactory(String str, boolean z) {
        this.threadNamePrefix = str;
        this.daemon = z;
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        ThreadGroup threadGroup = null;
        try {
            Object invoke = System.class.getDeclaredMethod("getSecurityManager", new Class[0]).invoke(null, new Object[0]);
            if (invoke != null) {
                threadGroup = (ThreadGroup) invoke.getClass().getDeclaredMethod("getThreadGroup", new Class[0]).invoke(invoke, new Object[0]);
            }
        } catch (Throwable unused) {
        }
        Thread thread = new Thread(threadGroup != null ? threadGroup : new ThreadGroup("ClassGraph-thread-group"), runnable, this.threadNamePrefix + threadIdx.getAndIncrement());
        thread.setDaemon(this.daemon);
        return thread;
    }
}
