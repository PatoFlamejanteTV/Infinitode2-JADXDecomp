package nonapi.io.github.classgraph.concurrency;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/concurrency/AutoCloseableExecutorService.class */
public class AutoCloseableExecutorService extends ThreadPoolExecutor implements AutoCloseable {
    public final InterruptionChecker interruptionChecker;

    public AutoCloseableExecutorService(int i) {
        super(i, i, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new SimpleThreadFactory("ClassGraph-worker-", true));
        this.interruptionChecker = new InterruptionChecker();
    }

    @Override // java.util.concurrent.ThreadPoolExecutor
    public void afterExecute(Runnable runnable, Throwable th) {
        super.afterExecute(runnable, th);
        if (th != null) {
            this.interruptionChecker.setExecutionException(new ExecutionException("Uncaught exception", th));
            this.interruptionChecker.interrupt();
        } else if (runnable instanceof Future) {
            try {
                ((Future) runnable).get();
            } catch (InterruptedException | CancellationException unused) {
                this.interruptionChecker.interrupt();
            } catch (ExecutionException e) {
                this.interruptionChecker.setExecutionException(e);
                this.interruptionChecker.interrupt();
            }
        }
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        try {
            shutdown();
        } catch (SecurityException unused) {
        }
        boolean z = false;
        try {
            z = awaitTermination(2500L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused2) {
            this.interruptionChecker.interrupt();
        }
        if (!z) {
            try {
                shutdownNow();
            } catch (SecurityException e) {
                throw new RuntimeException("Could not shut down ExecutorService -- need java.lang.RuntimePermission(\"modifyThread\"), or the security manager's checkAccess method denies access", e);
            }
        }
    }
}
