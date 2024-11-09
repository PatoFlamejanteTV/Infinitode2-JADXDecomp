package com.badlogic.gdx.utils;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.LifecycleListener;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/Timer.class */
public class Timer {
    static final Object threadLock = new Object();
    static TimerThread thread;
    final Array<Task> tasks = new Array<>(false, 8);
    long stopTimeMillis;

    static /* synthetic */ TimerThread access$000() {
        return thread();
    }

    public static Timer instance() {
        Timer timer;
        synchronized (threadLock) {
            TimerThread thread2 = thread();
            if (thread2.instance == null) {
                thread2.instance = new Timer();
            }
            timer = thread2.instance;
        }
        return timer;
    }

    private static TimerThread thread() {
        TimerThread timerThread;
        synchronized (threadLock) {
            if (thread == null || thread.files != Gdx.files) {
                if (thread != null) {
                    thread.dispose();
                }
                thread = new TimerThread();
            }
            timerThread = thread;
        }
        return timerThread;
    }

    public Timer() {
        start();
    }

    public Task postTask(Task task) {
        return scheduleTask(task, 0.0f, 0.0f, 0);
    }

    public Task scheduleTask(Task task, float f) {
        return scheduleTask(task, f, 0.0f, 0);
    }

    public Task scheduleTask(Task task, float f, float f2) {
        return scheduleTask(task, f, f2, -1);
    }

    public Task scheduleTask(Task task, float f, float f2, int i) {
        synchronized (threadLock) {
            synchronized (this) {
                synchronized (task) {
                    if (task.timer != null) {
                        throw new IllegalArgumentException("The same task may not be scheduled twice.");
                    }
                    task.timer = this;
                    long nanoTime = System.nanoTime() / 1000000;
                    long j = nanoTime + (f * 1000.0f);
                    if (thread.pauseTimeMillis > 0) {
                        j -= nanoTime - thread.pauseTimeMillis;
                    }
                    task.executeTimeMillis = j;
                    task.intervalMillis = f2 * 1000.0f;
                    task.repeatCount = i;
                    this.tasks.add(task);
                }
            }
            threadLock.notifyAll();
        }
        return task;
    }

    public void stop() {
        synchronized (threadLock) {
            if (thread().instances.removeValue(this, true)) {
                this.stopTimeMillis = System.nanoTime() / 1000000;
            }
        }
    }

    public void start() {
        synchronized (threadLock) {
            Array<Timer> array = thread().instances;
            if (array.contains(this, true)) {
                return;
            }
            array.add(this);
            if (this.stopTimeMillis > 0) {
                delay((System.nanoTime() / 1000000) - this.stopTimeMillis);
                this.stopTimeMillis = 0L;
            }
            threadLock.notifyAll();
        }
    }

    public void clear() {
        synchronized (threadLock) {
            TimerThread thread2 = thread();
            synchronized (this) {
                synchronized (thread2.postedTasks) {
                    int i = this.tasks.size;
                    for (int i2 = 0; i2 < i; i2++) {
                        Task task = this.tasks.get(i2);
                        thread2.removePostedTask(task);
                        task.reset();
                    }
                }
                this.tasks.clear();
            }
        }
    }

    public synchronized boolean isEmpty() {
        return this.tasks.size == 0;
    }

    synchronized long update(TimerThread timerThread, long j, long j2) {
        int i = 0;
        int i2 = this.tasks.size;
        while (i < i2) {
            Task task = this.tasks.get(i);
            synchronized (task) {
                if (task.executeTimeMillis > j) {
                    j2 = Math.min(j2, task.executeTimeMillis - j);
                } else {
                    if (task.repeatCount == 0) {
                        task.timer = null;
                        this.tasks.removeIndex(i);
                        i--;
                        i2--;
                    } else {
                        task.executeTimeMillis = j + task.intervalMillis;
                        j2 = Math.min(j2, task.intervalMillis);
                        if (task.repeatCount > 0) {
                            task.repeatCount--;
                        }
                    }
                    timerThread.addPostedTask(task);
                }
            }
            i++;
        }
        return j2;
    }

    public synchronized void delay(long j) {
        int i = this.tasks.size;
        for (int i2 = 0; i2 < i; i2++) {
            Task task = this.tasks.get(i2);
            synchronized (task) {
                task.executeTimeMillis += j;
            }
        }
    }

    public static Task post(Task task) {
        return instance().postTask(task);
    }

    public static Task schedule(Task task, float f) {
        return instance().scheduleTask(task, f);
    }

    public static Task schedule(Task task, float f, float f2) {
        return instance().scheduleTask(task, f, f2);
    }

    public static Task schedule(Task task, float f, float f2, int i) {
        return instance().scheduleTask(task, f, f2, i);
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/Timer$Task.class */
    public static abstract class Task implements Runnable {
        final Application app = Gdx.app;
        long executeTimeMillis;
        long intervalMillis;
        int repeatCount;
        volatile Timer timer;

        @Override // java.lang.Runnable
        public abstract void run();

        public Task() {
            if (this.app == null) {
                throw new IllegalStateException("Gdx.app not available.");
            }
        }

        public void cancel() {
            synchronized (Timer.threadLock) {
                Timer.access$000().removePostedTask(this);
                Timer timer = this.timer;
                if (timer != null) {
                    synchronized (timer) {
                        timer.tasks.removeValue(this, true);
                        reset();
                    }
                } else {
                    reset();
                }
            }
        }

        synchronized void reset() {
            this.executeTimeMillis = 0L;
            this.timer = null;
        }

        public boolean isScheduled() {
            return this.timer != null;
        }

        public synchronized long getExecuteTimeMillis() {
            return this.executeTimeMillis;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/Timer$TimerThread.class */
    public static class TimerThread implements LifecycleListener, Runnable {
        Timer instance;
        long pauseTimeMillis;
        final Array<Timer> instances = new Array<>(1);
        final Array<Task> postedTasks = new Array<>(2);
        final Array<Task> runTasks = new Array<>(2);
        private final Runnable runPostedTasks = new Runnable() { // from class: com.badlogic.gdx.utils.Timer.TimerThread.1
            @Override // java.lang.Runnable
            public void run() {
                TimerThread.this.runPostedTasks();
            }
        };
        final Files files = Gdx.files;
        final Application app = Gdx.app;

        public TimerThread() {
            this.app.addLifecycleListener(this);
            resume();
            Thread thread = new Thread(this, "Timer");
            thread.setDaemon(true);
            thread.start();
        }

        @Override // java.lang.Runnable
        public void run() {
            while (true) {
                synchronized (Timer.threadLock) {
                    if (Timer.thread != this || this.files != Gdx.files) {
                        break;
                    }
                    long j = 5000;
                    if (this.pauseTimeMillis == 0) {
                        long nanoTime = System.nanoTime() / 1000000;
                        int i = this.instances.size;
                        for (int i2 = 0; i2 < i; i2++) {
                            try {
                                j = this.instances.get(i2).update(this, nanoTime, j);
                            } catch (Throwable th) {
                                throw new GdxRuntimeException("Task failed: " + this.instances.get(i2).getClass().getName(), th);
                            }
                        }
                    }
                    if (Timer.thread != this || this.files != Gdx.files) {
                        break;
                    } else if (j > 0) {
                        try {
                            Timer.threadLock.wait(j);
                        } catch (InterruptedException unused) {
                        }
                    }
                    dispose();
                }
                dispose();
            }
            dispose();
        }

        void runPostedTasks() {
            synchronized (this.postedTasks) {
                this.runTasks.addAll(this.postedTasks);
                this.postedTasks.clear();
            }
            Task[] taskArr = this.runTasks.items;
            int i = this.runTasks.size;
            for (int i2 = 0; i2 < i; i2++) {
                taskArr[i2].run();
            }
            this.runTasks.clear();
        }

        void addPostedTask(Task task) {
            synchronized (this.postedTasks) {
                if (this.postedTasks.isEmpty()) {
                    task.app.postRunnable(this.runPostedTasks);
                }
                this.postedTasks.add(task);
            }
        }

        void removePostedTask(Task task) {
            synchronized (this.postedTasks) {
                Task[] taskArr = this.postedTasks.items;
                for (int i = this.postedTasks.size - 1; i >= 0; i--) {
                    if (taskArr[i] == task) {
                        this.postedTasks.removeIndex(i);
                    }
                }
            }
        }

        @Override // com.badlogic.gdx.LifecycleListener
        public void resume() {
            synchronized (Timer.threadLock) {
                long nanoTime = (System.nanoTime() / 1000000) - this.pauseTimeMillis;
                int i = this.instances.size;
                for (int i2 = 0; i2 < i; i2++) {
                    this.instances.get(i2).delay(nanoTime);
                }
                this.pauseTimeMillis = 0L;
                Timer.threadLock.notifyAll();
            }
        }

        @Override // com.badlogic.gdx.LifecycleListener
        public void pause() {
            synchronized (Timer.threadLock) {
                this.pauseTimeMillis = System.nanoTime() / 1000000;
                Timer.threadLock.notifyAll();
            }
        }

        @Override // com.badlogic.gdx.LifecycleListener
        public void dispose() {
            synchronized (Timer.threadLock) {
                synchronized (this.postedTasks) {
                    this.postedTasks.clear();
                }
                if (Timer.thread == this) {
                    Timer.thread = null;
                }
                this.instances.clear();
                Timer.threadLock.notifyAll();
            }
            this.app.removeLifecycleListener(this);
        }
    }
}
