package com.google.common.b.a;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/* loaded from: infinitode-2.jar:com/google/common/b/a/d.class */
public interface d extends ExecutorService {
    <T> b<T> a();

    b<?> b();

    <T> b<T> c();

    @Override // java.util.concurrent.ExecutorService
    /* synthetic */ default Future submit(Runnable runnable) {
        return b();
    }

    @Override // java.util.concurrent.ExecutorService
    /* synthetic */ default Future submit(Runnable runnable, Object obj) {
        return c();
    }

    @Override // java.util.concurrent.ExecutorService
    /* synthetic */ default Future submit(Callable callable) {
        return a();
    }
}
