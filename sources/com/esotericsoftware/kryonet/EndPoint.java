package com.esotericsoftware.kryonet;

import com.esotericsoftware.kryo.Kryo;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryonet/EndPoint.class */
public interface EndPoint extends Runnable {
    void addListener(Listener listener);

    void removeListener(Listener listener);

    @Override // java.lang.Runnable
    void run();

    void start();

    void stop();

    void close();

    void update(int i);

    Thread getUpdateThread();

    Kryo getKryo();
}
