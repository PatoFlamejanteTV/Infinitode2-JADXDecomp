package com.prineside.tdi2.utils;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.utils.logging.TLog;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/GameSyncLoader.class */
public class GameSyncLoader {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3835a = TLog.forClass(GameSyncLoader.class);

    /* renamed from: b, reason: collision with root package name */
    private final Array<Task> f3836b = new Array<>();
    private final DelayedRemovalArray<SyncExecutionListener> c = new DelayedRemovalArray<>(false, 1);
    private int d = 0;

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/GameSyncLoader$SyncExecutionListener.class */
    public interface SyncExecutionListener {
        void startedTask(Task task, Task task2);

        void done();
    }

    public void addTask(Task task) {
        if (this.d != 0) {
            throw new IllegalStateException("Can't registerDelta new tasks, already executing");
        }
        this.f3836b.add(task);
    }

    public void addListener(SyncExecutionListener syncExecutionListener) {
        if (syncExecutionListener == null) {
            throw new IllegalArgumentException("listener is null");
        }
        if (!this.c.contains(syncExecutionListener, true)) {
            this.c.add(syncExecutionListener);
        }
    }

    public boolean iterateWithTimeout(long j) {
        long timestampMillis = Game.getTimestampMillis();
        while (Game.getTimestampMillis() - timestampMillis < j) {
            if (!iterate()) {
                return false;
            }
        }
        return true;
    }

    public boolean iterate() {
        if (this.d == this.f3836b.size) {
            return false;
        }
        if (this.d < this.f3836b.size) {
            Task task = this.f3836b.get(this.d);
            this.c.begin();
            int i = this.c.size;
            for (int i2 = 0; i2 < i; i2++) {
                this.c.get(i2).startedTask(task, this.d == 0 ? null : this.f3836b.get(this.d - 1));
            }
            this.c.end();
            long realTickCount = Game.getRealTickCount();
            f3835a.i("starting \"" + task.title + "\"", new Object[0]);
            task.f3837a.run();
            f3835a.i("done \"" + task.title + "\" in " + (((float) (Game.getRealTickCount() - realTickCount)) * 0.001f) + "ms", new Object[0]);
            this.d++;
            if (this.d == this.f3836b.size) {
                this.c.begin();
                int i3 = this.c.size;
                for (int i4 = 0; i4 < i3; i4++) {
                    this.c.get(i4).done();
                }
                this.c.end();
                return true;
            }
            return true;
        }
        return true;
    }

    public float getProgress() {
        return this.d / this.f3836b.size;
    }

    public boolean isDone() {
        return this.d == this.f3836b.size;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/GameSyncLoader$Task.class */
    public static class Task {

        /* renamed from: a, reason: collision with root package name */
        final Runnable f3837a;
        public final String title;

        public Task(String str, Runnable runnable) {
            if (runnable == null) {
                throw new IllegalArgumentException("runnable is null for task " + str);
            }
            this.f3837a = runnable;
            this.title = str;
        }
    }
}
