package com.esotericsoftware.kryonet.util;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryonet/util/TcpIdleSender.class */
public abstract class TcpIdleSender implements Listener {
    boolean started;

    protected abstract Object next();

    @Override // com.esotericsoftware.kryonet.Listener
    public void idle(Connection connection) {
        if (!this.started) {
            this.started = true;
            start();
        }
        Object next = next();
        if (next == null) {
            connection.removeListener(this);
        } else {
            connection.sendTCP(next);
        }
    }

    protected void start() {
    }
}
