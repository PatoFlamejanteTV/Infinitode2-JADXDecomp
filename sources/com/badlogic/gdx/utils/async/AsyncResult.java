package com.badlogic.gdx.utils.async;

import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/async/AsyncResult.class */
public class AsyncResult<T> {
    private final Future<T> future;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AsyncResult(Future<T> future) {
        this.future = future;
    }

    public boolean isDone() {
        return this.future.isDone();
    }

    public T get() {
        try {
            return this.future.get();
        } catch (InterruptedException unused) {
            return null;
        } catch (ExecutionException e) {
            throw new GdxRuntimeException(e.getCause());
        }
    }
}
