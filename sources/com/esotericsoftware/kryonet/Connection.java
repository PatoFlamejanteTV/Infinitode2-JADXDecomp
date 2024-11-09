package com.esotericsoftware.kryonet;

import com.esotericsoftware.kryonet.FrameworkMessage;
import com.esotericsoftware.kryonet.serialization.Serialization;
import com.esotericsoftware.minlog.Log;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryonet/Connection.class */
public class Connection {
    private String name;
    EndPoint endPoint;
    TcpConnection tcp;
    UdpConnection udp;
    InetSocketAddress udpRemoteAddress;
    private int lastPingID;
    private long lastPingSendTime;
    private int returnTripTime;
    volatile boolean isConnected;
    volatile KryoNetException lastProtocolError;
    private Object arbitraryData;
    int id = -1;
    private Listener[] listeners = new Listener[0];
    private final Object listenerLock = new Object();

    /* JADX INFO: Access modifiers changed from: package-private */
    public void initialize(Serialization serialization, int i, int i2) {
        this.tcp = new TcpConnection(serialization, i, i2);
    }

    public int getID() {
        return this.id;
    }

    public boolean isConnected() {
        return this.isConnected;
    }

    public KryoNetException getLastProtocolError() {
        return this.lastProtocolError;
    }

    public int sendTCP(Object obj) {
        if (obj == null) {
            throw new NullPointerException("object to send cannot be null.");
        }
        try {
            int send = this.tcp.send(this, obj);
            if (send == 0) {
                if (Log.TRACE) {
                    Log.trace("kryonet", this + " TCP had nothing to send.");
                }
            } else if (Log.DEBUG) {
                String simpleName = obj == null ? "null" : obj.getClass().getSimpleName();
                if (!(obj instanceof FrameworkMessage)) {
                    Log.debug("kryonet", this + " sent TCP: " + simpleName + " (" + send + ")");
                } else if (Log.TRACE) {
                    Log.trace("kryonet", this + " sent TCP: " + simpleName + " (" + send + ")");
                }
            }
            return send;
        } catch (KryoNetException e) {
            if (Log.ERROR) {
                Log.error("kryonet", "Unable to send TCP with connection: " + this, e);
            }
            close();
            return 0;
        } catch (IOException e2) {
            if (Log.DEBUG) {
                Log.debug("kryonet", "Unable to send TCP with connection: " + this, e2);
            }
            close();
            return 0;
        }
    }

    public int sendUDP(Object obj) {
        if (obj == null) {
            throw new NullPointerException("object to send cannot be null.");
        }
        InetSocketAddress inetSocketAddress = this.udpRemoteAddress;
        InetSocketAddress inetSocketAddress2 = inetSocketAddress;
        if (inetSocketAddress == null && this.udp != null) {
            inetSocketAddress2 = this.udp.connectedAddress;
        }
        if (inetSocketAddress2 == null && this.isConnected) {
            throw new IllegalStateException("This connection is not connected via UDP.");
        }
        try {
            if (inetSocketAddress2 == null) {
                throw new SocketException("Connection is closed.");
            }
            int send = this.udp.send(this, obj, inetSocketAddress2);
            if (send == 0) {
                if (Log.TRACE) {
                    Log.trace("kryonet", this + " UDP had nothing to send.");
                }
            } else if (Log.DEBUG) {
                if (send != -1) {
                    String simpleName = obj == null ? "null" : obj.getClass().getSimpleName();
                    if (!(obj instanceof FrameworkMessage)) {
                        Log.debug("kryonet", this + " sent UDP: " + simpleName + " (" + send + ")");
                    } else if (Log.TRACE) {
                        Log.trace("kryonet", this + " sent UDP: " + simpleName + " (" + send + ")");
                    }
                } else {
                    Log.debug("kryonet", this + " was unable to send, UDP socket buffer full.");
                }
            }
            return send;
        } catch (KryoNetException e) {
            if (Log.ERROR) {
                Log.error("kryonet", "Unable to send UDP with connection: " + this, e);
            }
            close();
            return 0;
        } catch (IOException e2) {
            if (Log.DEBUG) {
                Log.debug("kryonet", "Unable to send UDP with connection: " + this, e2);
            }
            close();
            return 0;
        }
    }

    public void close() {
        boolean z = this.isConnected;
        this.isConnected = false;
        this.tcp.close();
        if (this.udp != null && this.udp.connectedAddress != null) {
            this.udp.close();
        }
        if (z) {
            notifyDisconnected();
            if (Log.INFO) {
                Log.info("kryonet", this + " disconnected.");
            }
        }
        setConnected(false);
    }

    public void updateReturnTripTime() {
        FrameworkMessage.Ping ping = new FrameworkMessage.Ping();
        int i = this.lastPingID;
        this.lastPingID = i + 1;
        ping.id = i;
        this.lastPingSendTime = System.currentTimeMillis();
        sendTCP(ping);
    }

    public int getReturnTripTime() {
        return this.returnTripTime;
    }

    public void setKeepAliveTCP(int i) {
        this.tcp.keepAliveMillis = i;
    }

    public void setTimeout(int i) {
        this.tcp.timeoutMillis = i;
    }

    public void addListener(Listener listener) {
        if (listener == null) {
            throw new NullPointerException("listener cannot be null.");
        }
        synchronized (this.listenerLock) {
            Listener[] listenerArr = this.listeners;
            int length = listenerArr.length;
            for (Listener listener2 : listenerArr) {
                if (listener == listener2) {
                    return;
                }
            }
            Listener[] listenerArr2 = new Listener[length + 1];
            listenerArr2[0] = listener;
            System.arraycopy(listenerArr, 0, listenerArr2, 1, length);
            this.listeners = listenerArr2;
            if (Log.TRACE) {
                Log.trace("kryonet", "Connection listener added: " + listener.getClass().getName());
            }
        }
    }

    public void removeListener(Listener listener) {
        if (listener == null) {
            throw new NullPointerException("listener cannot be null.");
        }
        synchronized (this.listenerLock) {
            Listener[] listenerArr = this.listeners;
            int length = listenerArr.length;
            if (length == 0) {
                return;
            }
            Listener[] listenerArr2 = new Listener[length - 1];
            int i = 0;
            for (Listener listener2 : listenerArr) {
                if (listener != listener2) {
                    if (i == length - 1) {
                        return;
                    }
                    int i2 = i;
                    i++;
                    listenerArr2[i2] = listener2;
                }
            }
            this.listeners = listenerArr2;
            if (Log.TRACE) {
                Log.trace("kryonet", "Connection listener removed: " + listener.getClass().getName());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyConnected() {
        Socket socket;
        InetSocketAddress inetSocketAddress;
        if (Log.INFO && this.tcp.socketChannel != null && (socket = this.tcp.socketChannel.socket()) != null && (inetSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress()) != null) {
            Log.info("kryonet", this + " connected: " + inetSocketAddress.getAddress());
        }
        for (Listener listener : this.listeners) {
            listener.connected(this);
        }
    }

    void notifyDisconnected() {
        for (Listener listener : this.listeners) {
            listener.disconnected(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyIdle() {
        for (Listener listener : this.listeners) {
            listener.idle(this);
            if (!isIdle()) {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyReceived(Object obj) {
        if (obj instanceof FrameworkMessage.Ping) {
            FrameworkMessage.Ping ping = (FrameworkMessage.Ping) obj;
            if (ping.isReply) {
                if (ping.id == this.lastPingID - 1) {
                    this.returnTripTime = (int) (System.currentTimeMillis() - this.lastPingSendTime);
                    if (Log.TRACE) {
                        Log.trace("kryonet", this + " return trip time: " + this.returnTripTime);
                    }
                }
            } else {
                ping.isReply = true;
                sendTCP(ping);
            }
        }
        for (Listener listener : this.listeners) {
            listener.received(this, obj);
        }
    }

    public EndPoint getEndPoint() {
        return this.endPoint;
    }

    public InetSocketAddress getRemoteAddressTCP() {
        Socket socket;
        if (this.tcp.socketChannel != null && (socket = this.tcp.socketChannel.socket()) != null) {
            return (InetSocketAddress) socket.getRemoteSocketAddress();
        }
        return null;
    }

    public InetSocketAddress getRemoteAddressUDP() {
        InetSocketAddress inetSocketAddress = this.udp.connectedAddress;
        if (inetSocketAddress != null) {
            return inetSocketAddress;
        }
        return this.udpRemoteAddress;
    }

    public void setBufferPositionFix(boolean z) {
        this.tcp.bufferPositionFix = z;
    }

    public void setName(String str) {
        this.name = str;
    }

    public int getTcpWriteBufferSize() {
        return this.tcp.writeBuffer.position();
    }

    public boolean isIdle() {
        return ((float) this.tcp.writeBuffer.position()) / ((float) this.tcp.writeBuffer.capacity()) < this.tcp.idleThreshold;
    }

    public void setIdleThreshold(float f) {
        this.tcp.idleThreshold = f;
    }

    public String toString() {
        if (this.name != null) {
            return this.name;
        }
        return "Connection " + this.id;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setConnected(boolean z) {
        this.isConnected = z;
        if (z && this.name == null) {
            this.name = "Connection " + this.id;
        }
    }

    public Object getArbitraryData() {
        return this.arbitraryData;
    }

    public void setArbitraryData(Object obj) {
        this.arbitraryData = obj;
    }

    public int hashCode() {
        return 31 + this.id;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.id == ((Connection) obj).id;
    }
}
