package com.prineside.tdi2.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Array;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.utils.logging.TLog;
import java.util.Comparator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/ParallelBatchStack.class */
public class ParallelBatchStack {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3885a = TLog.forClass(ParallelBatchStack.class);

    /* renamed from: b, reason: collision with root package name */
    private Mesh f3886b;
    private final int c;
    private final ShaderProgram d;
    private final ExecutorService e;
    private Array<Future<?>> f = new Array<>(true, 1, Future.class);
    public Array<Entry> batchesOrdered = new Array<>(true, 1, Entry.class);

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/ParallelBatchStack$Entry.class */
    public static final class Entry {
        public ParallelBatch batch;
        public ObjectConsumer<ParallelBatch> job;
        public int priority;
        public Runnable threadRunnable;
        public Runnable postFlushRunnable;
    }

    public ParallelBatchStack(int i, ShaderProgram shaderProgram, int i2) {
        this.c = i;
        this.d = shaderProgram;
        this.f3886b = new Mesh(Gdx.gl30 != null ? Mesh.VertexDataType.VertexBufferObjectWithVAO : SpriteBatch.defaultVertexDataType, false, i << 2, i * 6, new VertexAttribute(1, 2, ShaderProgram.POSITION_ATTRIBUTE), new VertexAttribute(4, 4, ShaderProgram.COLOR_ATTRIBUTE), new VertexAttribute(16, 2, "a_texCoord0"));
        int i3 = i * 6;
        short[] sArr = new short[i3];
        short s = 0;
        int i4 = 0;
        while (i4 < i3) {
            sArr[i4] = s;
            sArr[i4 + 1] = (short) (s + 1);
            sArr[i4 + 2] = (short) (s + 2);
            sArr[i4 + 3] = (short) (s + 2);
            sArr[i4 + 4] = (short) (s + 3);
            sArr[i4 + 5] = s;
            i4 += 6;
            s = (short) (s + 4);
        }
        this.f3886b.setIndices(sArr);
        this.e = Executors.newFixedThreadPool(i2, new ThreadFactory(this) { // from class: com.prineside.tdi2.utils.ParallelBatchStack.1

            /* renamed from: a, reason: collision with root package name */
            private int f3887a = 0;

            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                StringBuilder sb = new StringBuilder("ParallelBatchStack_");
                int i5 = this.f3887a;
                this.f3887a = i5 + 1;
                Thread thread = new Thread(runnable, sb.append(i5).toString());
                thread.setDaemon(true);
                return thread;
            }
        });
    }

    public void registerBatch(int i, ObjectConsumer<ParallelBatch> objectConsumer) {
        registerBatchWithFlushCallback(i, objectConsumer, null);
    }

    public void registerBatchWithFlushCallback(int i, ObjectConsumer<ParallelBatch> objectConsumer, Runnable runnable) {
        Entry entry = new Entry();
        entry.priority = i;
        entry.batch = new ParallelBatch(this.c, this.d);
        entry.job = objectConsumer;
        entry.postFlushRunnable = runnable;
        entry.threadRunnable = () -> {
            entry.batch.reset();
            entry.job.accept(entry.batch);
            if (entry.batch.drawing) {
                entry.batch.end();
            }
        };
        this.batchesOrdered.add(entry);
        Threads.sortGdxArray(this.batchesOrdered, new Comparator<Entry>(this) { // from class: com.prineside.tdi2.utils.ParallelBatchStack.2
            @Override // java.util.Comparator
            public int compare(Entry entry2, Entry entry3) {
                return Integer.compare(entry2.priority, entry3.priority);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v23 */
    /* JADX WARN: Type inference failed for: r0v29, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v6 */
    public void execute() {
        this.f.clear();
        for (int i = 0; i < this.batchesOrdered.size; i++) {
            this.f.add(this.e.submit(this.batchesOrdered.items[i].threadRunnable));
        }
        Object obj = null;
        int i2 = 0;
        while (true) {
            ?? r0 = i2;
            if (r0 >= this.f.size) {
                break;
            }
            try {
                r0 = this.f.items[i2].get();
                i2++;
            } catch (Exception e) {
                obj = r0;
            }
        }
        if (obj == null) {
            for (int i3 = 0; i3 < this.batchesOrdered.size; i3++) {
                Entry entry = this.batchesOrdered.items[i3];
                entry.batch.render(this.f3886b);
                entry.batch.reset();
                if (entry.postFlushRunnable != null) {
                    entry.postFlushRunnable.run();
                }
            }
            return;
        }
        f3885a.i("timeout / exception", obj);
    }

    public void dispose() {
        this.f3886b.dispose();
        this.f3886b = null;
        try {
            if (!this.e.awaitTermination(800L, TimeUnit.MILLISECONDS)) {
                this.e.shutdownNow();
            }
        } catch (InterruptedException unused) {
            this.e.shutdownNow();
        }
    }
}
