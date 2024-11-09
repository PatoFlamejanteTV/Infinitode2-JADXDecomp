package nonapi.io.github.classgraph.concurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/concurrency/InterruptionChecker.class */
public class InterruptionChecker {
    private final AtomicBoolean interrupted = new AtomicBoolean(false);
    private final AtomicReference<ExecutionException> thrownExecutionException = new AtomicReference<>();

    public void interrupt() {
        this.interrupted.set(true);
        Thread.currentThread().interrupt();
    }

    public void setExecutionException(ExecutionException executionException) {
        if (executionException != null && this.thrownExecutionException.get() == null) {
            this.thrownExecutionException.compareAndSet(null, executionException);
        }
    }

    public ExecutionException getExecutionException() {
        return this.thrownExecutionException.get();
    }

    public static Throwable getCause(Throwable th) {
        Throwable th2;
        Throwable th3 = th;
        while (true) {
            th2 = th3;
            if (!(th2 instanceof ExecutionException)) {
                break;
            }
            th3 = th2.getCause();
        }
        return th2 != null ? th2 : new ExecutionException("ExecutionException with unknown cause", null);
    }

    public boolean checkAndReturn() {
        if (this.interrupted.get()) {
            interrupt();
            return true;
        }
        if (Thread.currentThread().isInterrupted()) {
            this.interrupted.set(true);
            return true;
        }
        return false;
    }

    public void check() {
        ExecutionException executionException = getExecutionException();
        if (executionException != null) {
            throw executionException;
        }
        if (checkAndReturn()) {
            throw new InterruptedException();
        }
    }
}
