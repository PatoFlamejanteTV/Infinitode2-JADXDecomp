package com.google.common.b.a;

import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* loaded from: infinitode-2.jar:com/google/common/b/a/e.class */
public interface e extends d, ScheduledExecutorService {
    c<?> d();

    <V> c<V> e();

    c<?> f();

    c<?> g();

    @Override // java.util.concurrent.ScheduledExecutorService
    /* synthetic */ default ScheduledFuture scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        return g();
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    /* synthetic */ default ScheduledFuture scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        return f();
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    /* synthetic */ default ScheduledFuture schedule(Callable callable, long j, TimeUnit timeUnit) {
        return e();
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    /* synthetic */ default ScheduledFuture schedule(Runnable runnable, long j, TimeUnit timeUnit) {
        return d();
    }
}
