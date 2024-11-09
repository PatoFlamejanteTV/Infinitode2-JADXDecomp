package com.esotericsoftware.kryonet;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryonet/Listener.class */
public interface Listener {
    default void connected(Connection connection) {
    }

    default void disconnected(Connection connection) {
    }

    default void received(Connection connection, Object obj) {
    }

    default void idle(Connection connection) {
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryonet/Listener$ConnectionListener.class */
    public static abstract class ConnectionListener implements Listener {
        @Override // com.esotericsoftware.kryonet.Listener
        public abstract void disconnected(Connection connection);

        @Override // com.esotericsoftware.kryonet.Listener
        public abstract void connected(Connection connection);

        @Override // com.esotericsoftware.kryonet.Listener
        public void received(Connection connection, Object obj) {
        }

        @Override // com.esotericsoftware.kryonet.Listener
        public void idle(Connection connection) {
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryonet/Listener$TypeListener.class */
    public static class TypeListener implements Listener {
        private final HashMap<Class<?>, BiConsumer> listeners = new HashMap<>();

        @Override // com.esotericsoftware.kryonet.Listener
        public void received(Connection connection, Object obj) {
            if (this.listeners.containsKey(obj.getClass())) {
                this.listeners.get(obj.getClass()).accept(connection, obj);
            }
        }

        public <T> void addTypeHandler(Class<T> cls, BiConsumer<? super Connection, ? super T> biConsumer) {
            this.listeners.put(cls, biConsumer);
        }

        public <T> void removeTypeHandler(Class<T> cls) {
            this.listeners.remove(cls);
        }

        public int size() {
            return this.listeners.size();
        }

        public void clear() {
            this.listeners.clear();
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryonet/Listener$QueuedListener.class */
    public static abstract class QueuedListener implements Listener {
        final Listener listener;

        protected abstract void queue(Runnable runnable);

        public QueuedListener(Listener listener) {
            if (listener == null) {
                throw new NullPointerException("listener cannot be null.");
            }
            this.listener = listener;
        }

        @Override // com.esotericsoftware.kryonet.Listener
        public void connected(final Connection connection) {
            queue(new Runnable() { // from class: com.esotericsoftware.kryonet.Listener.QueuedListener.1
                @Override // java.lang.Runnable
                public void run() {
                    QueuedListener.this.listener.connected(connection);
                }
            });
        }

        @Override // com.esotericsoftware.kryonet.Listener
        public void disconnected(final Connection connection) {
            queue(new Runnable() { // from class: com.esotericsoftware.kryonet.Listener.QueuedListener.2
                @Override // java.lang.Runnable
                public void run() {
                    QueuedListener.this.listener.disconnected(connection);
                }
            });
        }

        @Override // com.esotericsoftware.kryonet.Listener
        public void received(final Connection connection, final Object obj) {
            queue(new Runnable() { // from class: com.esotericsoftware.kryonet.Listener.QueuedListener.3
                @Override // java.lang.Runnable
                public void run() {
                    QueuedListener.this.listener.received(connection, obj);
                }
            });
        }

        @Override // com.esotericsoftware.kryonet.Listener
        public void idle(final Connection connection) {
            queue(new Runnable() { // from class: com.esotericsoftware.kryonet.Listener.QueuedListener.4
                @Override // java.lang.Runnable
                public void run() {
                    QueuedListener.this.listener.idle(connection);
                }
            });
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryonet/Listener$ThreadedListener.class */
    public static class ThreadedListener extends QueuedListener {
        protected final ExecutorService threadPool;

        public ThreadedListener(Listener listener) {
            this(listener, Executors.newFixedThreadPool(1));
        }

        public ThreadedListener(Listener listener, ExecutorService executorService) {
            super(listener);
            if (executorService == null) {
                throw new NullPointerException("threadPool cannot be null.");
            }
            this.threadPool = executorService;
        }

        @Override // com.esotericsoftware.kryonet.Listener.QueuedListener
        public void queue(Runnable runnable) {
            this.threadPool.execute(runnable);
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryonet/Listener$LagListener.class */
    public static class LagListener extends QueuedListener {
        private final ScheduledExecutorService threadPool;
        private final int lagMillisMin;
        private final int lagMillisMax;
        final LinkedList<Runnable> runnables;

        public LagListener(int i, int i2, Listener listener) {
            super(listener);
            this.runnables = new LinkedList<>();
            this.lagMillisMin = i;
            this.lagMillisMax = i2;
            this.threadPool = Executors.newScheduledThreadPool(1);
        }

        @Override // com.esotericsoftware.kryonet.Listener.QueuedListener
        public void queue(Runnable runnable) {
            synchronized (this.runnables) {
                this.runnables.addFirst(runnable);
            }
            this.threadPool.schedule(new Runnable() { // from class: com.esotericsoftware.kryonet.Listener.LagListener.1
                @Override // java.lang.Runnable
                public void run() {
                    Runnable removeLast;
                    synchronized (LagListener.this.runnables) {
                        removeLast = LagListener.this.runnables.removeLast();
                    }
                    removeLast.run();
                }
            }, this.lagMillisMin + ((int) (Math.random() * (this.lagMillisMax - this.lagMillisMin))), TimeUnit.MILLISECONDS);
        }
    }
}
