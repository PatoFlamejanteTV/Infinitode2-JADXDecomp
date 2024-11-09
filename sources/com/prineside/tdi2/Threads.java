package com.prineside.tdi2;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Sort;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.utils.IntObjectConsumer;
import com.prineside.tdi2.utils.IntObjectPair;
import com.prineside.tdi2.utils.ObjectConsumer;
import com.prineside.tdi2.utils.errorhandling.CrashHandler;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/Threads.class */
public final class Threads {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f1766a = TLog.forClass(Threads.class);

    /* renamed from: b, reason: collision with root package name */
    private static final Threads f1767b = new Threads();
    private static final ThreadLocal<Sort> c = new ThreadLocal<Sort>() { // from class: com.prineside.tdi2.Threads.1
        @Override // java.lang.ThreadLocal
        protected /* synthetic */ Sort initialValue() {
            return a();
        }

        private static Sort a() {
            return new Sort();
        }
    };
    private final HashSet<Runnable> d = new HashSet<>();
    private final ExecutorService e = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors(), new DaemonThreadFactory("Shared Threads async executor", true));
    private ExecutorService f;

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/Threads$TestObject.class */
    private static final class TestObject {
        public int v;
    }

    public static Threads i() {
        return f1767b;
    }

    private Threads() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a() {
        this.d.clear();
    }

    public final void runOnMainThreadBlocking(Runnable runnable, float f) {
        if (Game.i.isInMainThread()) {
            runnable.run();
            return;
        }
        Thread currentThread = Thread.currentThread();
        boolean[] zArr = {false};
        boolean[] zArr2 = {false};
        Throwable[] thArr = {null};
        postRunnable(() -> {
            if (currentThread == Thread.currentThread()) {
                zArr2[0] = true;
                return;
            }
            try {
                runnable.run();
            } catch (Throwable th) {
                thArr[0] = th;
            }
            zArr[0] = true;
        });
        long timestampMillis = Game.getTimestampMillis();
        while (!zArr[0]) {
            try {
                if (zArr2[0]) {
                    throw new IllegalStateException("Multithreading.runOnMainThreadBlocking should not be called from the GDX thread");
                }
                Thread.sleep(1L);
                if (f > 0.0f && ((float) (Game.getTimestampMillis() - timestampMillis)) > f * 1000.0f) {
                    throw new IllegalStateException("Job takes too long");
                }
            } catch (Exception e) {
                throw new IllegalStateException("Failed runOnMainThreadBlocking", e);
            }
        }
        if (thArr[0] != null) {
            throw new IllegalStateException("Failed runOnMainThreadBlocking: exception occurred", thArr[0]);
        }
    }

    public final void runOnMainThread(Runnable runnable) {
        if (Game.i.isInMainThread()) {
            runnable.run();
        } else {
            postRunnable(runnable);
        }
    }

    public final void runAsync(Runnable runnable) {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        this.e.submit(() -> {
            try {
                runnable.run();
            } catch (Exception e) {
                f1766a.e("Failed to execute runnable async", e);
                throw new RuntimeException("Failed to execute runnable async, called from " + Arrays.toString(stackTrace), e);
            }
        });
    }

    public final void runAsync(Runnable runnable, ObjectConsumer<Exception> objectConsumer) {
        this.e.submit(() -> {
            try {
                runnable.run();
            } catch (Exception e) {
                objectConsumer.accept(e);
            }
        });
    }

    public static <T> void sortGdxArray(Array<T> array, Comparator<? super T> comparator) {
        c.get().sort(array.items, comparator, 0, array.size);
    }

    public static <T> void sort(T[] tArr, Comparator<? super T> comparator) {
        c.get().sort(tArr, comparator, 0, tArr.length);
    }

    public static <T> void sortArraySlice(T[] tArr, int i, int i2, Comparator<? super T> comparator) {
        c.get().sort(tArr, comparator, i, i2);
    }

    public final void postRunnable(Runnable runnable) {
        StackTraceElement[] stackTraceElementArr = null;
        if (Config.isHeadless()) {
            stackTraceElementArr = new Throwable().getStackTrace();
        } else if (Game.i == null || Game.i.settingsManager == null || Game.i.settingsManager.isInDebugDetailedMode()) {
            stackTraceElementArr = new Throwable().getStackTrace();
        }
        StackTraceElement[] stackTraceElementArr2 = stackTraceElementArr;
        Gdx.app.postRunnable(() -> {
            long realTickCount = Game.getRealTickCount();
            try {
                runnable.run();
                long realTickCount2 = Game.getRealTickCount() - realTickCount;
                if (realTickCount2 > 80000) {
                    f1766a.d("runnable '" + runnable + "' took " + (((float) realTickCount2) * 0.001f) + "ms to execute on the main thread: " + Arrays.toString(stackTraceElementArr2), new Object[0]);
                }
            } catch (Exception e) {
                throw new RuntimeException("Failed to execute runnable: " + Arrays.toString(stackTraceElementArr2), e);
            }
        });
    }

    public final void postRunnableOnce(Runnable runnable) {
        Preconditions.checkNotNull(runnable, "runnable can not be null");
        if (this.d.contains(runnable)) {
            return;
        }
        this.d.add(runnable);
        postRunnable(runnable);
    }

    public final Thread createThread(String str, Runnable runnable, boolean z) {
        Thread thread = new Thread(runnable, str);
        thread.setDaemon(true);
        if (z) {
            CrashHandler.handleThreadExceptions(thread);
        } else {
            CrashHandler.handleThreadExceptionsForgiving(thread);
        }
        return thread;
    }

    public final void synchronize(Object obj, Runnable runnable) {
        synchronized (obj) {
            runnable.run();
        }
    }

    public final void tryFinally(Runnable runnable, Runnable runnable2) {
        try {
            runnable.run();
        } finally {
            runnable2.run();
        }
    }

    public final void tryCatch(Runnable runnable, ObjectConsumer<Throwable> objectConsumer) {
        try {
            runnable.run();
        } catch (Throwable th) {
            objectConsumer.accept(th);
        }
    }

    private ExecutorService b() {
        if (this.f == null) {
            int availableProcessors = Runtime.getRuntime().availableProcessors();
            this.f = Executors.newFixedThreadPool(availableProcessors, new DaemonThreadFactory("Threads class loop executor", true));
            f1766a.i("created ExecutorService with " + availableProcessors + " threads for loops", new Object[0]);
        }
        return this.f;
    }

    private static <T> void a(T[] tArr, int i, int i2, IntObjectConsumer<T> intObjectConsumer) {
        ArrayList arrayList = new ArrayList();
        for (int i3 = i; i3 < i + i2; i3++) {
            arrayList.add(new IntObjectPair(i3, tArr[i3]));
        }
        arrayList.parallelStream().forEach(intObjectPair -> {
            intObjectConsumer.accept(intObjectPair.f3849a, intObjectPair.t);
        });
    }

    public final <T> void concurrentLoop(Array<T> array, IntObjectConsumer<T> intObjectConsumer) {
        concurrentLoop(array.items, 0, array.size, intObjectConsumer);
    }

    public final <T> void concurrentLoop(T[] tArr, int i, int i2, IntObjectConsumer<T> intObjectConsumer) {
        if (i2 == 0) {
            return;
        }
        if (i2 == 1) {
            intObjectConsumer.accept(0, tArr[0]);
            return;
        }
        if (Gdx.app != null && (Gdx.app.getType() == Application.ApplicationType.Desktop || (Gdx.app.getType() == Application.ApplicationType.Android && Gdx.app.getVersion() >= 24))) {
            a(tArr, i, i2, intObjectConsumer);
            return;
        }
        int min = Math.min(i2, Runtime.getRuntime().availableProcessors());
        int i3 = min;
        if (min > i2) {
            i3 = i2;
        }
        ExecutorService b2 = b();
        double d = i2 / i3;
        double d2 = i;
        ArrayList arrayList = new ArrayList();
        int i4 = 0;
        while (i4 < i3) {
            int i5 = (int) d2;
            d2 += d;
            int i6 = (i4 == i3 - 1 ? i + i2 : (int) d2) - 1;
            arrayList.add(() -> {
                for (int i7 = i5; i7 <= i6; i7++) {
                    intObjectConsumer.accept(i7, tArr[i7]);
                }
                return null;
            });
            i4++;
        }
        try {
            b2.invokeAll(arrayList);
        } catch (InterruptedException e) {
            throw new IllegalStateException("Failed to loop through the array", e);
        }
    }

    public final <T> void asyncConcurrentLoop(T[] tArr, int i, int i2, IntObjectConsumer<T> intObjectConsumer, Runnable runnable, ObjectConsumer<Exception> objectConsumer) {
        runAsync(() -> {
            concurrentLoop(tArr, i, i2, intObjectConsumer);
            runnable.run();
        }, objectConsumer);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/Threads$DaemonThreadFactory.class */
    public static class DaemonThreadFactory implements ThreadFactory {

        /* renamed from: a, reason: collision with root package name */
        private final String f1768a;

        /* renamed from: b, reason: collision with root package name */
        private int f1769b;
        private final boolean c;

        public DaemonThreadFactory(String str, boolean z) {
            this.f1768a = str;
            this.c = z;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Threads i = Threads.i();
            StringBuilder append = new StringBuilder().append(this.f1768a).append(SequenceUtils.SPACE);
            int i2 = this.f1769b;
            this.f1769b = i2 + 1;
            return i.createThread(append.append(i2).toString(), runnable, this.c);
        }
    }
}
