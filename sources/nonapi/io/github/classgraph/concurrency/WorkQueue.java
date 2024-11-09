package nonapi.io.github.classgraph.concurrency;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import nonapi.io.github.classgraph.utils.LogNode;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/concurrency/WorkQueue.class */
public class WorkQueue<T> implements AutoCloseable {
    private final WorkUnitProcessor<T> workUnitProcessor;
    private final int numWorkers;
    private final InterruptionChecker interruptionChecker;
    private final LogNode log;
    private final BlockingQueue<WorkUnitWrapper<T>> workUnits = new LinkedBlockingQueue();
    private final AtomicInteger numIncompleteWorkUnits = new AtomicInteger();
    private final ConcurrentLinkedQueue<Future<?>> workerFutures = new ConcurrentLinkedQueue<>();

    /* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/concurrency/WorkQueue$WorkUnitProcessor.class */
    public interface WorkUnitProcessor<T> {
        void processWorkUnit(T t, WorkQueue<T> workQueue, LogNode logNode);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/concurrency/WorkQueue$WorkUnitWrapper.class */
    public static class WorkUnitWrapper<T> {
        final T workUnit;

        public WorkUnitWrapper(T t) {
            this.workUnit = t;
        }
    }

    public static <U> void runWorkQueue(Collection<U> collection, ExecutorService executorService, InterruptionChecker interruptionChecker, int i, LogNode logNode, WorkUnitProcessor<U> workUnitProcessor) {
        if (collection.isEmpty()) {
            return;
        }
        WorkQueue workQueue = new WorkQueue(collection, workUnitProcessor, i, interruptionChecker, logNode);
        Throwable th = null;
        try {
            workQueue.startWorkers(executorService, i - 1);
            workQueue.runWorkLoop();
            if (0 == 0) {
                workQueue.close();
                return;
            }
            try {
                workQueue.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        } catch (Throwable th3) {
            if (0 != 0) {
                try {
                    workQueue.close();
                } catch (Throwable th4) {
                    th.addSuppressed(th4);
                }
            } else {
                workQueue.close();
            }
            throw th3;
        }
    }

    private WorkQueue(Collection<T> collection, WorkUnitProcessor<T> workUnitProcessor, int i, InterruptionChecker interruptionChecker, LogNode logNode) {
        this.workUnitProcessor = workUnitProcessor;
        this.numWorkers = i;
        this.interruptionChecker = interruptionChecker;
        this.log = logNode;
        addWorkUnits(collection);
    }

    private void startWorkers(ExecutorService executorService, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            this.workerFutures.add(executorService.submit(new Callable<Void>() { // from class: nonapi.io.github.classgraph.concurrency.WorkQueue.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.concurrent.Callable
                public Void call() {
                    WorkQueue.this.runWorkLoop();
                    return null;
                }
            }));
        }
    }

    private void sendPoisonPills() {
        for (int i = 0; i < this.numWorkers; i++) {
            this.workUnits.add(new WorkUnitWrapper<>(null));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void runWorkLoop() {
        while (true) {
            try {
                try {
                    this.interruptionChecker.check();
                    WorkUnitWrapper<T> take = this.workUnits.take();
                    if (take.workUnit != null) {
                        this.workUnitProcessor.processWorkUnit(take.workUnit, this, this.log);
                        if (this.numIncompleteWorkUnits.decrementAndGet() == 0) {
                            sendPoisonPills();
                        }
                    } else {
                        return;
                    }
                } catch (RuntimeException e) {
                    this.workUnits.clear();
                    this.numIncompleteWorkUnits.set(0);
                    sendPoisonPills();
                    throw new ExecutionException("Worker thread threw unchecked exception", e);
                }
            } catch (Error | InterruptedException e2) {
                this.workUnits.clear();
                this.numIncompleteWorkUnits.set(0);
                sendPoisonPills();
                throw e2;
            }
        }
    }

    public void addWorkUnit(T t) {
        if (t == null) {
            throw new NullPointerException("workUnit cannot be null");
        }
        this.numIncompleteWorkUnits.incrementAndGet();
        this.workUnits.add(new WorkUnitWrapper<>(t));
    }

    public void addWorkUnits(Collection<T> collection) {
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            addWorkUnit(it.next());
        }
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        while (true) {
            Future<?> poll = this.workerFutures.poll();
            if (poll == null) {
                return;
            }
            try {
                poll.get();
            } catch (InterruptedException unused) {
                if (this.log != null) {
                    this.log.log("~", "Worker thread was interrupted");
                }
                this.interruptionChecker.interrupt();
            } catch (CancellationException unused2) {
                if (this.log != null) {
                    this.log.log("~", "Worker thread was cancelled");
                }
            } catch (ExecutionException e) {
                this.interruptionChecker.setExecutionException(e);
                this.interruptionChecker.interrupt();
            }
        }
    }
}
