package com.esotericsoftware.kryonet;

import com.badlogic.gdx.net.HttpResponseHeader;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.util.IntMap;
import com.esotericsoftware.kryonet.FrameworkMessage;
import com.esotericsoftware.kryonet.serialization.KryoSerialization;
import com.esotericsoftware.kryonet.serialization.Serialization;
import com.esotericsoftware.minlog.Log;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryonet/Server.class */
public class Server implements EndPoint {
    public static final int DEFAULT_WRITE_BUFFER_SIZE = 16384;
    public static final int DEFAULT_OBJECT_BUFFER_SIZE = 2048;
    private final Serialization serialization;
    private final int writeBufferSize;
    private final int objectBufferSize;
    private final Selector selector;
    private int emptySelects;
    private ServerSocketChannel serverChannel;
    private UdpConnection udp;
    private Connection[] connections;
    private final IntMap<Connection> pendingConnections;
    Listener[] listeners;
    private final Object listenerLock;
    private int nextConnectionID;
    private volatile boolean shutdown;
    private final Object updateLock;
    private Thread updateThread;
    private ServerDiscoveryHandler discoveryHandler;
    private final Listener dispatchListener;

    public Server() {
        this(16384, 2048);
    }

    public Server(int i, int i2) {
        this(i, i2, new KryoSerialization());
    }

    public Server(int i, int i2, Serialization serialization) {
        this.connections = new Connection[0];
        this.pendingConnections = new IntMap<>();
        this.listeners = new Listener[0];
        this.listenerLock = new Object();
        this.nextConnectionID = 1;
        this.updateLock = new Object();
        this.dispatchListener = new Listener() { // from class: com.esotericsoftware.kryonet.Server.1
            @Override // com.esotericsoftware.kryonet.Listener
            public void connected(Connection connection) {
                for (Listener listener : Server.this.listeners) {
                    listener.connected(connection);
                }
            }

            @Override // com.esotericsoftware.kryonet.Listener
            public void disconnected(Connection connection) {
                Server.this.removeConnection(connection);
                for (Listener listener : Server.this.listeners) {
                    listener.disconnected(connection);
                }
            }

            @Override // com.esotericsoftware.kryonet.Listener
            public void received(Connection connection, Object obj) {
                for (Listener listener : Server.this.listeners) {
                    listener.received(connection, obj);
                }
            }

            @Override // com.esotericsoftware.kryonet.Listener
            public void idle(Connection connection) {
                for (Listener listener : Server.this.listeners) {
                    listener.idle(connection);
                }
            }
        };
        this.writeBufferSize = i;
        this.objectBufferSize = i2;
        this.serialization = serialization;
        this.discoveryHandler = new ServerDiscoveryHandler() { // from class: com.esotericsoftware.kryonet.Server.2
        };
        try {
            this.selector = Selector.open();
        } catch (IOException e) {
            throw new RuntimeException("Error opening the selector.", e);
        }
    }

    public void setDiscoveryHandler(ServerDiscoveryHandler serverDiscoveryHandler) {
        this.discoveryHandler = serverDiscoveryHandler;
    }

    public Serialization getSerialization() {
        return this.serialization;
    }

    @Override // com.esotericsoftware.kryonet.EndPoint
    public Kryo getKryo() {
        if (this.serialization instanceof KryoSerialization) {
            return ((KryoSerialization) this.serialization).getKryo();
        }
        return null;
    }

    public void bind(int i) {
        bind(new InetSocketAddress(i), (InetSocketAddress) null);
    }

    public void bind(int i, int i2) {
        bind(new InetSocketAddress(i), new InetSocketAddress(i2));
    }

    public void bind(InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2) {
        close();
        synchronized (this.updateLock) {
            this.selector.wakeup();
            try {
                this.serverChannel = this.selector.provider().openServerSocketChannel();
                this.serverChannel.socket().bind(inetSocketAddress);
                this.serverChannel.configureBlocking(false);
                this.serverChannel.register(this.selector, 16);
                if (Log.DEBUG) {
                    Log.debug("kryonet", "Accepting connections on port: " + inetSocketAddress + "/TCP");
                }
                if (inetSocketAddress2 != null) {
                    this.udp = new UdpConnection(this.serialization, this.objectBufferSize);
                    this.udp.bind(this.selector, inetSocketAddress2);
                    if (Log.DEBUG) {
                        Log.debug("kryonet", "Accepting connections on port: " + inetSocketAddress2 + "/UDP");
                    }
                }
            } catch (IOException e) {
                close();
                throw e;
            }
        }
        if (Log.INFO) {
            Log.info("kryonet", "Server opened.");
        }
    }

    @Override // com.esotericsoftware.kryonet.EndPoint
    public void update(int i) {
        int selectNow;
        this.updateThread = Thread.currentThread();
        synchronized (this.updateLock) {
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (i > 0) {
            selectNow = this.selector.select(i);
        } else {
            selectNow = this.selector.selectNow();
        }
        if (selectNow == 0) {
            this.emptySelects++;
            if (this.emptySelects == 100) {
                this.emptySelects = 0;
                long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                if (currentTimeMillis2 < 25) {
                    try {
                        Thread.sleep(25 - currentTimeMillis2);
                    } catch (InterruptedException unused) {
                    }
                }
            }
        } else {
            this.emptySelects = 0;
            Set<SelectionKey> selectedKeys = this.selector.selectedKeys();
            synchronized (selectedKeys) {
                UdpConnection udpConnection = this.udp;
                Iterator<SelectionKey> it = selectedKeys.iterator();
                while (it.hasNext()) {
                    keepAlive();
                    SelectionKey next = it.next();
                    it.remove();
                    Connection connection = (Connection) next.attachment();
                    try {
                        int readyOps = next.readyOps();
                        if (connection != null) {
                            if (udpConnection != null && connection.udpRemoteAddress == null) {
                                connection.close();
                            } else {
                                if ((readyOps & 1) == 1) {
                                    while (true) {
                                        try {
                                            Object readObject = connection.tcp.readObject(connection);
                                            if (readObject == null) {
                                                break;
                                            }
                                            if (Log.DEBUG) {
                                                String simpleName = readObject == null ? "null" : readObject.getClass().getSimpleName();
                                                if (!(readObject instanceof FrameworkMessage)) {
                                                    Log.debug("kryonet", connection + " received TCP: " + simpleName);
                                                } else if (Log.TRACE) {
                                                    Log.trace("kryonet", connection + " received TCP: " + simpleName);
                                                }
                                            }
                                            connection.notifyReceived(readObject);
                                        } catch (KryoNetException e) {
                                            if (Log.ERROR) {
                                                Log.error("kryonet", "Error reading TCP from connection: " + connection, e);
                                            }
                                            connection.close();
                                        } catch (IOException e2) {
                                            if (Log.TRACE) {
                                                Log.trace("kryonet", "Unable to read TCP from: " + connection, e2);
                                            } else if (Log.DEBUG) {
                                                Log.debug("kryonet", connection + " update: " + e2.getMessage());
                                            }
                                            connection.close();
                                        }
                                    }
                                }
                                if ((readyOps & 4) == 4) {
                                    try {
                                        connection.tcp.writeOperation();
                                    } catch (IOException e3) {
                                        if (Log.TRACE) {
                                            Log.trace("kryonet", "Unable to write TCP to connection: " + connection, e3);
                                        } else if (Log.DEBUG) {
                                            Log.debug("kryonet", connection + " update: " + e3.getMessage());
                                        }
                                        connection.close();
                                    }
                                }
                            }
                        } else if ((readyOps & 16) == 16) {
                            ServerSocketChannel serverSocketChannel = this.serverChannel;
                            if (serverSocketChannel != null) {
                                try {
                                    SocketChannel accept = serverSocketChannel.accept();
                                    if (accept != null) {
                                        acceptOperation(accept);
                                    }
                                } catch (IOException e4) {
                                    if (Log.DEBUG) {
                                        Log.debug("kryonet", "Unable to accept new connection.", e4);
                                    }
                                }
                            }
                        } else if (udpConnection == null) {
                            next.channel().close();
                        } else {
                            try {
                                InetSocketAddress readFromAddress = udpConnection.readFromAddress();
                                if (readFromAddress != null) {
                                    for (Connection connection2 : this.connections) {
                                        if (readFromAddress.equals(connection2.udpRemoteAddress)) {
                                            connection = connection2;
                                            break;
                                        }
                                    }
                                    try {
                                        Object readObject2 = udpConnection.readObject(connection);
                                        if (readObject2 instanceof FrameworkMessage) {
                                            if (readObject2 instanceof FrameworkMessage.RegisterUDP) {
                                                int i2 = ((FrameworkMessage.RegisterUDP) readObject2).connectionID;
                                                Connection remove = this.pendingConnections.remove(i2);
                                                if (remove != null) {
                                                    if (remove.udpRemoteAddress == null) {
                                                        remove.udpRemoteAddress = readFromAddress;
                                                        addConnection(remove);
                                                        remove.sendTCP(new FrameworkMessage.RegisterUDP());
                                                        if (Log.DEBUG) {
                                                            Log.debug("kryonet", "Port " + udpConnection.datagramChannel.socket().getLocalPort() + "/UDP connected to: " + readFromAddress);
                                                        }
                                                        remove.notifyConnected();
                                                    }
                                                } else if (Log.DEBUG) {
                                                    Log.debug("kryonet", "Ignoring incoming RegisterUDP with invalid connection ID: " + i2);
                                                }
                                            } else if (readObject2 instanceof FrameworkMessage.DiscoverHost) {
                                                try {
                                                    boolean onDiscoverHost = this.discoveryHandler.onDiscoverHost(udpConnection.datagramChannel, readFromAddress);
                                                    if (Log.DEBUG && onDiscoverHost) {
                                                        Log.debug("kryonet", "Responded to host discovery from: " + readFromAddress);
                                                    }
                                                } catch (IOException e5) {
                                                    if (Log.WARN) {
                                                        Log.warn("kryonet", "Error replying to host discovery from: " + readFromAddress, e5);
                                                    }
                                                }
                                            }
                                        }
                                        if (connection != null) {
                                            if (Log.DEBUG) {
                                                String simpleName2 = readObject2 == null ? "null" : readObject2.getClass().getSimpleName();
                                                if (readObject2 instanceof FrameworkMessage) {
                                                    if (Log.TRACE) {
                                                        Log.trace("kryonet", connection + " received UDP: " + simpleName2);
                                                    }
                                                } else {
                                                    Log.debug("kryonet", connection + " received UDP: " + simpleName2);
                                                }
                                            }
                                            connection.notifyReceived(readObject2);
                                        } else if (Log.DEBUG) {
                                            Log.debug("kryonet", "Ignoring UDP from unregistered address: " + readFromAddress);
                                        }
                                    } catch (KryoNetException e6) {
                                        if (Log.WARN) {
                                            if (connection != null) {
                                                if (Log.ERROR) {
                                                    Log.error("kryonet", "Error reading UDP from connection: " + connection, e6);
                                                }
                                            } else {
                                                Log.warn("kryonet", "Error reading UDP from unregistered address: " + readFromAddress, e6);
                                            }
                                        }
                                    }
                                }
                            } catch (IOException e7) {
                                if (Log.WARN) {
                                    Log.warn("kryonet", "Error reading UDP data.", e7);
                                }
                            }
                        }
                    } catch (CancelledKeyException unused2) {
                        if (connection != null) {
                            connection.close();
                        } else {
                            next.channel().close();
                        }
                    }
                }
            }
        }
        long currentTimeMillis3 = System.currentTimeMillis();
        for (Connection connection3 : this.connections) {
            if (connection3.tcp.isTimedOut(currentTimeMillis3)) {
                if (Log.DEBUG) {
                    Log.debug("kryonet", connection3 + " timed out.");
                }
                connection3.close();
            } else if (connection3.tcp.needsKeepAlive(currentTimeMillis3)) {
                connection3.sendTCP(FrameworkMessage.keepAlive);
            }
            if (connection3.isIdle()) {
                connection3.notifyIdle();
            }
        }
    }

    private void keepAlive() {
        long currentTimeMillis = System.currentTimeMillis();
        for (Connection connection : this.connections) {
            if (connection.tcp.needsKeepAlive(currentTimeMillis)) {
                connection.sendTCP(FrameworkMessage.keepAlive);
            }
        }
    }

    @Override // com.esotericsoftware.kryonet.EndPoint, java.lang.Runnable
    public void run() {
        if (Log.TRACE) {
            Log.trace("kryonet", "Server thread started.");
        }
        this.shutdown = false;
        while (!this.shutdown) {
            try {
                update(User32.VK_PLAY);
            } catch (IOException e) {
                if (Log.ERROR) {
                    Log.error("kryonet", "Error updating server connections.", e);
                }
                close();
            }
        }
        if (Log.TRACE) {
            Log.trace("kryonet", "Server thread stopped.");
        }
    }

    @Override // com.esotericsoftware.kryonet.EndPoint
    public void start() {
        new Thread(this, HttpResponseHeader.Server).start();
    }

    @Override // com.esotericsoftware.kryonet.EndPoint
    public void stop() {
        if (this.shutdown) {
            return;
        }
        this.shutdown = true;
        close();
        if (Log.TRACE) {
            Log.trace("kryonet", "Server thread stopping.");
        }
    }

    private void acceptOperation(SocketChannel socketChannel) {
        Connection newConnection = newConnection();
        newConnection.initialize(this.serialization, this.writeBufferSize, this.objectBufferSize);
        newConnection.endPoint = this;
        UdpConnection udpConnection = this.udp;
        if (udpConnection != null) {
            newConnection.udp = udpConnection;
        }
        try {
            newConnection.tcp.accept(this.selector, socketChannel).attach(newConnection);
            int i = this.nextConnectionID;
            this.nextConnectionID = i + 1;
            if (this.nextConnectionID == -1) {
                this.nextConnectionID = 1;
            }
            newConnection.id = i;
            newConnection.setConnected(true);
            newConnection.addListener(this.dispatchListener);
            if (udpConnection != null) {
                this.pendingConnections.put(i, newConnection);
            } else {
                addConnection(newConnection);
            }
            FrameworkMessage.RegisterTCP registerTCP = new FrameworkMessage.RegisterTCP();
            registerTCP.connectionID = i;
            newConnection.sendTCP(registerTCP);
            if (udpConnection == null) {
                newConnection.notifyConnected();
            }
        } catch (IOException e) {
            newConnection.close();
            if (Log.DEBUG) {
                Log.debug("kryonet", "Unable to accept TCP connection.", e);
            }
        }
    }

    protected Connection newConnection() {
        return new Connection();
    }

    private void addConnection(Connection connection) {
        Connection[] connectionArr = new Connection[this.connections.length + 1];
        connectionArr[0] = connection;
        System.arraycopy(this.connections, 0, connectionArr, 1, this.connections.length);
        this.connections = connectionArr;
    }

    void removeConnection(Connection connection) {
        ArrayList arrayList = new ArrayList(Arrays.asList(this.connections));
        arrayList.remove(connection);
        this.connections = (Connection[]) arrayList.toArray(new Connection[arrayList.size()]);
        this.pendingConnections.remove(connection.id);
    }

    public void sendToAllTCP(Object obj) {
        for (Connection connection : this.connections) {
            connection.sendTCP(obj);
        }
    }

    public void sendToAllExceptTCP(int i, Object obj) {
        for (Connection connection : this.connections) {
            if (connection.id != i) {
                connection.sendTCP(obj);
            }
        }
    }

    public void sendToTCP(int i, Object obj) {
        for (Connection connection : this.connections) {
            if (connection.id == i) {
                connection.sendTCP(obj);
                return;
            }
        }
    }

    public void sendToAllUDP(Object obj) {
        for (Connection connection : this.connections) {
            connection.sendUDP(obj);
        }
    }

    public void sendToAllExceptUDP(int i, Object obj) {
        for (Connection connection : this.connections) {
            if (connection.id != i) {
                connection.sendUDP(obj);
            }
        }
    }

    public void sendToUDP(int i, Object obj) {
        for (Connection connection : this.connections) {
            if (connection.id == i) {
                connection.sendUDP(obj);
                return;
            }
        }
    }

    @Override // com.esotericsoftware.kryonet.EndPoint
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
                Log.trace("kryonet", "Server listener added: " + listener.getClass().getName());
            }
        }
    }

    @Override // com.esotericsoftware.kryonet.EndPoint
    public void removeListener(Listener listener) {
        if (listener == null) {
            throw new NullPointerException("listener cannot be null.");
        }
        synchronized (this.listenerLock) {
            Listener[] listenerArr = this.listeners;
            int length = listenerArr.length;
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
                Log.trace("kryonet", "Server listener removed: " + listener.getClass().getName());
            }
        }
    }

    @Override // com.esotericsoftware.kryonet.EndPoint
    public void close() {
        Connection[] connectionArr = this.connections;
        if (Log.INFO && connectionArr.length > 0) {
            Log.info("kryonet", "Closing server connections...");
        }
        for (Connection connection : connectionArr) {
            connection.close();
        }
        Connection[] connectionArr2 = new Connection[0];
        ServerSocketChannel serverSocketChannel = this.serverChannel;
        if (serverSocketChannel != null) {
            try {
                serverSocketChannel.close();
                if (Log.INFO) {
                    Log.info("kryonet", "Server closed.");
                }
            } catch (IOException e) {
                if (Log.DEBUG) {
                    Log.debug("kryonet", "Unable to close server.", e);
                }
            }
            this.serverChannel = null;
        }
        UdpConnection udpConnection = this.udp;
        if (udpConnection != null) {
            udpConnection.close();
            this.udp = null;
        }
        synchronized (this.updateLock) {
        }
        this.selector.wakeup();
        try {
            this.selector.selectNow();
        } catch (IOException unused) {
        }
    }

    public void dispose() {
        close();
        this.selector.close();
    }

    @Override // com.esotericsoftware.kryonet.EndPoint
    public Thread getUpdateThread() {
        return this.updateThread;
    }

    public Collection<Connection> getConnections() {
        return Collections.unmodifiableCollection(Arrays.asList(this.connections));
    }
}
