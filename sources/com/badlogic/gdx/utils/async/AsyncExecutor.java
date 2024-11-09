package com.badlogic.gdx.utils.async;

import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/async/AsyncExecutor.class */
public class AsyncExecutor implements Disposable {
    private final ExecutorService executor;

    public AsyncExecutor(int i) {
        this(i, "AsynchExecutor-Thread");
    }

    public AsyncExecutor(int i, final String str) {
        this.executor = Executors.newFixedThreadPool(i, new ThreadFactory() { // from class: com.badlogic.gdx.utils.async.AsyncExecutor.1
            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable, str);
                thread.setDaemon(true);
                return thread;
            }
        });
    }

    public <T> AsyncResult<T> submit(final AsyncTask<T> asyncTask) {
        if (this.executor.isShutdown()) {
            throw new GdxRuntimeException("Cannot run tasks on an executor that has been shutdown (disposed)");
        }
        return new AsyncResult<>(this.executor.submit(new Callable<T>() { // from class: com.badlogic.gdx.utils.async.AsyncExecutor.2
            @Override // java.util.concurrent.Callable
            public T call() {
                return (T) asyncTask.call();
            }
        }));
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        this.executor.shutdown();
        try {
            this.executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new GdxRuntimeException("Couldn't shutdown loading thread", e);
        }
    }
}
