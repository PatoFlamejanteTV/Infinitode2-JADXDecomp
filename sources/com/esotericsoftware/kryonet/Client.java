package com.esotericsoftware.kryonet;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.FrameworkMessage;
import com.esotericsoftware.kryonet.serialization.KryoSerialization;
import com.esotericsoftware.kryonet.serialization.Serialization;
import com.esotericsoftware.minlog.Log;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryonet/Client.class */
public class Client extends Connection implements EndPoint {
    public static final int DEFAULT_WRITE_BUFFER_SIZE = 8192;
    public static final int DEFAULT_OBJECT_BUUFER_SIZE = 2048;
    private final Serialization serialization;
    private Selector selector;
    private int emptySelects;
    private volatile boolean tcpRegistered;
    private volatile boolean udpRegistered;
    private final Object tcpRegistrationLock;
    private final Object udpRegistrationLock;
    private volatile boolean shutdown;
    private final Object updateLock;
    private Thread updateThread;
    private int connectTimeout;
    private InetAddress connectHost;
    private int connectTcpPort;
    private int connectUdpPort;
    private boolean isClosed;
    private ClientDiscoveryHandler discoveryHandler;

    static {
        try {
            System.setProperty("java.net.preferIPv6Addresses", "false");
        } catch (AccessControlException unused) {
        }
    }

    public Client() {
        this(8192, 2048);
    }

    public Client(int i, int i2) {
        this(i, i2, new KryoSerialization());
    }

    public Client(int i, int i2, Serialization serialization) {
        this.tcpRegistrationLock = new Object();
        this.udpRegistrationLock = new Object();
        this.updateLock = new Object();
        this.endPoint = this;
        this.serialization = serialization;
        this.discoveryHandler = new ClientDiscoveryHandler() { // from class: com.esotericsoftware.kryonet.Client.1
        };
        initialize(serialization, i, i2);
        try {
            this.selector = Selector.open();
        } catch (IOException e) {
            throw new RuntimeException("Error opening selector.", e);
        }
    }

    public void setDiscoveryHandler(ClientDiscoveryHandler clientDiscoveryHandler) {
        this.discoveryHandler = clientDiscoveryHandler;
    }

    @Override // com.esotericsoftware.kryonet.EndPoint
    public Kryo getKryo() {
        if (this.serialization instanceof KryoSerialization) {
            return ((KryoSerialization) this.serialization).getKryo();
        }
        return null;
    }

    public void connect(String str, int i) {
        connect(500, InetAddress.getByName(str), i);
    }

    public void connect(int i, String str, int i2) {
        connect(i, InetAddress.getByName(str), i2, -1);
    }

    public void connect(int i, String str, int i2, int i3) {
        connect(i, InetAddress.getByName(str), i2, i3);
    }

    public void connect(int i, InetAddress inetAddress, int i2) {
        connect(i, inetAddress, i2, -1);
    }

    public void connect(int i, InetAddress inetAddress, int i2, int i3) {
        long currentTimeMillis;
        if (inetAddress == null) {
            throw new NullPointerException("host cannot be null.");
        }
        if (Thread.currentThread() == getUpdateThread()) {
            throw new IllegalStateException("Cannot connect on the connection's update thread.");
        }
        this.connectTimeout = i;
        this.connectHost = inetAddress;
        this.connectTcpPort = i2;
        this.connectUdpPort = i3;
        close();
        if (Log.INFO) {
            if (i3 != -1) {
                Log.info("kryonet", "Connecting: " + inetAddress + ":" + i2 + "/" + i3);
            } else {
                Log.info("kryonet", "Connecting: " + inetAddress + ":" + i2);
            }
        }
        this.id = -1;
        if (i3 != -1) {
            try {
                this.udp = new UdpConnection(this.serialization, this.tcp.readBuffer.capacity());
            } catch (IOException e) {
                close();
                throw e;
            }
        }
        synchronized (this.updateLock) {
            this.tcpRegistered = false;
            this.selector.wakeup();
            currentTimeMillis = System.currentTimeMillis() + i;
            this.tcp.connect(this.selector, new InetSocketAddress(inetAddress, i2), i);
        }
        synchronized (this.tcpRegistrationLock) {
            while (!this.tcpRegistered && System.currentTimeMillis() < currentTimeMillis) {
                try {
                    this.tcpRegistrationLock.wait(100L);
                } catch (InterruptedException unused) {
                }
            }
            if (!this.tcpRegistered) {
                throw new SocketTimeoutException("Connected, but timed out during TCP registration.\nNote: Client#update(int) must be called in a separate thread during connect.");
            }
        }
        if (i3 != -1) {
            InetSocketAddress inetSocketAddress = new InetSocketAddress(inetAddress, i3);
            synchronized (this.updateLock) {
                this.udpRegistered = false;
                this.selector.wakeup();
                this.udp.connect(this.selector, inetSocketAddress);
            }
            synchronized (this.udpRegistrationLock) {
                while (!this.udpRegistered && System.currentTimeMillis() < currentTimeMillis) {
                    FrameworkMessage.RegisterUDP registerUDP = new FrameworkMessage.RegisterUDP();
                    registerUDP.connectionID = this.id;
                    this.udp.send(this, registerUDP, inetSocketAddress);
                    try {
                        this.udpRegistrationLock.wait(100L);
                    } catch (InterruptedException unused2) {
                    }
                }
                if (!this.udpRegistered) {
                    throw new SocketTimeoutException("Connected, but timed out during UDP registration: " + inetAddress + ":" + i3);
                }
            }
        }
    }

    public void reconnect() {
        reconnect(this.connectTimeout);
    }

    public void reconnect(int i) {
        if (this.connectHost == null) {
            throw new IllegalStateException("This client has never been connected.");
        }
        connect(i, this.connectHost, this.connectTcpPort, this.connectUdpPort);
    }

    @Override // com.esotericsoftware.kryonet.EndPoint
    public void update(int i) {
        int selectNow;
        int readyOps;
        Object readObject;
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
            this.isClosed = false;
            Set<SelectionKey> selectedKeys = this.selector.selectedKeys();
            synchronized (selectedKeys) {
                Iterator<SelectionKey> it = selectedKeys.iterator();
                while (it.hasNext()) {
                    keepAlive();
                    SelectionKey next = it.next();
                    it.remove();
                    try {
                        readyOps = next.readyOps();
                    } catch (CancelledKeyException unused2) {
                    }
                    if ((readyOps & 1) == 1) {
                        if (next.attachment() == this.tcp) {
                            while (true) {
                                Object readObject2 = this.tcp.readObject(this);
                                if (readObject2 == null) {
                                    break;
                                }
                                if (!this.tcpRegistered) {
                                    if (readObject2 instanceof FrameworkMessage.RegisterTCP) {
                                        this.id = ((FrameworkMessage.RegisterTCP) readObject2).connectionID;
                                        synchronized (this.tcpRegistrationLock) {
                                            this.tcpRegistered = true;
                                            this.tcpRegistrationLock.notifyAll();
                                            if (Log.TRACE) {
                                                Log.trace("kryonet", this + " received TCP: RegisterTCP");
                                            }
                                            if (this.udp == null) {
                                                setConnected(true);
                                            }
                                        }
                                        if (this.udp == null) {
                                            notifyConnected();
                                        }
                                    } else {
                                        continue;
                                    }
                                } else if (this.udp != null && !this.udpRegistered) {
                                    if (readObject2 instanceof FrameworkMessage.RegisterUDP) {
                                        synchronized (this.udpRegistrationLock) {
                                            this.udpRegistered = true;
                                            this.udpRegistrationLock.notifyAll();
                                            if (Log.TRACE) {
                                                Log.trace("kryonet", this + " received UDP: RegisterUDP");
                                            }
                                            if (Log.DEBUG) {
                                                Log.debug("kryonet", "Port " + this.udp.datagramChannel.socket().getLocalPort() + "/UDP connected to: " + this.udp.connectedAddress);
                                            }
                                            setConnected(true);
                                        }
                                        notifyConnected();
                                    } else {
                                        continue;
                                    }
                                } else if (this.isConnected) {
                                    if (Log.DEBUG) {
                                        String simpleName = readObject2 == null ? "null" : readObject2.getClass().getSimpleName();
                                        if (!(readObject2 instanceof FrameworkMessage)) {
                                            Log.debug("kryonet", this + " received TCP: " + simpleName);
                                        } else if (Log.TRACE) {
                                            Log.trace("kryonet", this + " received TCP: " + simpleName);
                                        }
                                    }
                                    notifyReceived(readObject2);
                                }
                            }
                        } else if (this.udp.readFromAddress() != null && (readObject = this.udp.readObject(this)) != null) {
                            if (Log.DEBUG) {
                                Log.debug("kryonet", this + " received UDP: " + (readObject == null ? "null" : readObject.getClass().getSimpleName()));
                            }
                            notifyReceived(readObject);
                        }
                    }
                    if ((readyOps & 4) == 4) {
                        this.tcp.writeOperation();
                    }
                }
            }
        }
        if (this.isConnected) {
            if (this.tcp.isTimedOut(System.currentTimeMillis())) {
                if (Log.DEBUG) {
                    Log.debug("kryonet", this + " timed out.");
                }
                close();
            } else {
                keepAlive();
            }
            if (isIdle()) {
                notifyIdle();
            }
        }
    }

    void keepAlive() {
        if (!this.isConnected) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (this.tcp.needsKeepAlive(currentTimeMillis)) {
            sendTCP(FrameworkMessage.keepAlive);
        }
        if (this.udp != null && this.udpRegistered && this.udp.needsKeepAlive(currentTimeMillis)) {
            sendUDP(FrameworkMessage.keepAlive);
        }
    }

    @Override // com.esotericsoftware.kryonet.EndPoint, java.lang.Runnable
    public void run() {
        if (Log.TRACE) {
            Log.trace("kryonet", "Client thread started.");
        }
        this.shutdown = false;
        while (!this.shutdown) {
            try {
                update(User32.VK_PLAY);
            } catch (KryoNetException e) {
                this.lastProtocolError = e;
                if (Log.ERROR) {
                    if (this.isConnected) {
                        Log.error("kryonet", "Error updating connection: " + this, e);
                    } else {
                        Log.error("kryonet", "Error updating connection.", e);
                    }
                }
                close();
                throw e;
            } catch (IOException e2) {
                if (Log.TRACE) {
                    if (this.isConnected) {
                        Log.trace("kryonet", "Unable to update connection: " + this, e2);
                    } else {
                        Log.trace("kryonet", "Unable to update connection.", e2);
                    }
                } else if (Log.DEBUG) {
                    if (this.isConnected) {
                        Log.debug("kryonet", this + " update: " + e2.getMessage());
                    } else {
                        Log.debug("kryonet", "Unable to update connection: " + e2.getMessage());
                    }
                }
                close();
            }
        }
        if (Log.TRACE) {
            Log.trace("kryonet", "Client thread stopped.");
        }
    }

    @Override // com.esotericsoftware.kryonet.EndPoint
    public void start() {
        if (this.updateThread != null) {
            this.shutdown = true;
            try {
                this.updateThread.join(5000L);
            } catch (InterruptedException unused) {
            }
        }
        this.updateThread = new Thread(this, "Client");
        this.updateThread.setDaemon(true);
        this.updateThread.start();
    }

    @Override // com.esotericsoftware.kryonet.EndPoint
    public void stop() {
        if (this.shutdown) {
            return;
        }
        close();
        if (Log.TRACE) {
            Log.trace("kryonet", "Client thread stopping.");
        }
        this.shutdown = true;
        this.selector.wakeup();
    }

    @Override // com.esotericsoftware.kryonet.Connection, com.esotericsoftware.kryonet.EndPoint
    public void close() {
        super.close();
        synchronized (this.updateLock) {
        }
        if (!this.isClosed) {
            this.isClosed = true;
            this.selector.wakeup();
            try {
                this.selector.selectNow();
            } catch (IOException unused) {
            }
        }
    }

    public void dispose() {
        close();
        this.selector.close();
    }

    @Override // com.esotericsoftware.kryonet.Connection, com.esotericsoftware.kryonet.EndPoint
    public void addListener(Listener listener) {
        super.addListener(listener);
        if (Log.TRACE) {
            Log.trace("kryonet", "Client listener added.");
        }
    }

    @Override // com.esotericsoftware.kryonet.Connection, com.esotericsoftware.kryonet.EndPoint
    public void removeListener(Listener listener) {
        super.removeListener(listener);
        if (Log.TRACE) {
            Log.trace("kryonet", "Client listener removed.");
        }
    }

    public void setKeepAliveUDP(int i) {
        if (this.udp == null) {
            throw new IllegalStateException("Not connected via UDP.");
        }
        this.udp.keepAliveMillis = i;
    }

    @Override // com.esotericsoftware.kryonet.EndPoint
    public Thread getUpdateThread() {
        return this.updateThread;
    }

    public Serialization getSerialization() {
        return this.serialization;
    }

    private void broadcast(int i, DatagramSocket datagramSocket) {
        ByteBuffer allocate = ByteBuffer.allocate(64);
        this.serialization.write(null, allocate, new FrameworkMessage.DiscoverHost());
        allocate.flip();
        byte[] bArr = new byte[allocate.limit()];
        allocate.get(bArr);
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface nextElement = networkInterfaces.nextElement();
            if (!nextElement.isLoopback() && nextElement.isUp()) {
                Iterator<InterfaceAddress> it = nextElement.getInterfaceAddresses().iterator();
                while (it.hasNext()) {
                    InetAddress broadcast = it.next().getBroadcast();
                    if (broadcast != null) {
                        datagramSocket.send(new DatagramPacket(bArr, bArr.length, broadcast, i));
                    }
                }
            }
        }
        if (Log.DEBUG) {
            Log.debug("kryonet", "Broadcasted host discovery on port: " + i);
        }
    }

    public InetAddress discoverHost(int i, int i2) {
        DatagramSocket datagramSocket = null;
        try {
            try {
                DatagramSocket datagramSocket2 = new DatagramSocket();
                broadcast(i, datagramSocket2);
                datagramSocket2.setSoTimeout(i2);
                DatagramPacket onRequestNewDatagramPacket = this.discoveryHandler.onRequestNewDatagramPacket();
                try {
                    datagramSocket2.receive(onRequestNewDatagramPacket);
                    if (Log.INFO) {
                        Log.info("kryonet", "Discovered server: " + onRequestNewDatagramPacket.getAddress());
                    }
                    this.discoveryHandler.onDiscoveredHost(onRequestNewDatagramPacket);
                    InetAddress address = onRequestNewDatagramPacket.getAddress();
                    datagramSocket2.close();
                    this.discoveryHandler.onFinally();
                    return address;
                } catch (SocketTimeoutException unused) {
                    if (Log.INFO) {
                        Log.info("kryonet", "Host discovery timed out.");
                    }
                    datagramSocket2.close();
                    this.discoveryHandler.onFinally();
                    return null;
                }
            } catch (IOException e) {
                if (Log.ERROR) {
                    Log.error("kryonet", "Host discovery failed.", e);
                }
                if (0 != 0) {
                    datagramSocket.close();
                }
                this.discoveryHandler.onFinally();
                return null;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                datagramSocket.close();
            }
            this.discoveryHandler.onFinally();
            throw th;
        }
    }

    public List<InetAddress> discoverHosts(int i, int i2) {
        ArrayList arrayList = new ArrayList();
        DatagramSocket datagramSocket = null;
        try {
            try {
                datagramSocket = new DatagramSocket();
                broadcast(i, datagramSocket);
                datagramSocket.setSoTimeout(i2);
                while (true) {
                    DatagramPacket onRequestNewDatagramPacket = this.discoveryHandler.onRequestNewDatagramPacket();
                    try {
                        datagramSocket.receive(onRequestNewDatagramPacket);
                        if (Log.INFO) {
                            Log.info("kryonet", "Discovered server: " + onRequestNewDatagramPacket.getAddress());
                        }
                        this.discoveryHandler.onDiscoveredHost(onRequestNewDatagramPacket);
                        arrayList.add(onRequestNewDatagramPacket.getAddress());
                    } catch (SocketTimeoutException unused) {
                        if (Log.INFO) {
                            Log.info("kryonet", "Host discovery timed out.");
                        }
                        datagramSocket.close();
                        this.discoveryHandler.onFinally();
                        return arrayList;
                    }
                }
            } catch (IOException e) {
                if (Log.ERROR) {
                    Log.error("kryonet", "Host discovery failed.", e);
                }
                if (datagramSocket != null) {
                    datagramSocket.close();
                }
                this.discoveryHandler.onFinally();
                return arrayList;
            }
        } catch (Throwable th) {
            if (datagramSocket != null) {
                datagramSocket.close();
            }
            this.discoveryHandler.onFinally();
            throw th;
        }
    }
}
