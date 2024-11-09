package com.esotericsoftware.kryonet.rmi;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.FieldSerializer;
import com.esotericsoftware.kryo.util.IntMap;
import com.esotericsoftware.kryo.util.Util;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.FrameworkMessage;
import com.esotericsoftware.kryonet.KryoNetException;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.util.ObjectIntMap;
import com.esotericsoftware.minlog.Log;
import com.esotericsoftware.reflectasm.MethodAccess;
import com.prineside.tdi2.events.EventListeners;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryonet/rmi/ObjectSpace.class */
public class ObjectSpace {
    private static final int returnValueMask = 128;
    private static final int returnExceptionMask = 64;
    private static final int responseIdMask = 63;
    private static final Object instancesLock = new Object();
    static ObjectSpace[] instances = new ObjectSpace[0];
    private static final HashMap<Class<?>, CachedMethod[]> methodCache = new HashMap<>();
    private static boolean useAsm = true;
    final IntMap<Object> idToObject;
    final ObjectIntMap<Object> objectToID;
    Connection[] connections;
    final Object connectionsLock;
    Executor executor;
    private final Listener invokeListener;

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryonet/rmi/ObjectSpace$InvokeMethodResult.class */
    public static class InvokeMethodResult implements FrameworkMessage {
        public int objectID;
        public byte responseID;
        public Object result;
    }

    public ObjectSpace() {
        this.idToObject = new IntMap<>();
        this.objectToID = new ObjectIntMap<>();
        this.connections = new Connection[0];
        this.connectionsLock = new Object();
        this.invokeListener = new Listener() { // from class: com.esotericsoftware.kryonet.rmi.ObjectSpace.1
            @Override // com.esotericsoftware.kryonet.Listener
            public void received(final Connection connection, Object obj) {
                if (!(obj instanceof InvokeMethod)) {
                    return;
                }
                if (ObjectSpace.this.connections != null) {
                    int i = 0;
                    int length = ObjectSpace.this.connections.length;
                    while (i < length && connection != ObjectSpace.this.connections[i]) {
                        i++;
                    }
                    if (i == length) {
                        return;
                    }
                }
                final InvokeMethod invokeMethod = (InvokeMethod) obj;
                final Object obj2 = ObjectSpace.this.idToObject.get(invokeMethod.objectID);
                if (obj2 == null) {
                    if (Log.WARN) {
                        Log.warn("kryonet", "Ignoring remote invocation request for unknown object ID: " + invokeMethod.objectID);
                    }
                } else if (ObjectSpace.this.executor == null) {
                    ObjectSpace.this.invoke(connection, obj2, invokeMethod);
                } else {
                    ObjectSpace.this.executor.execute(new Runnable() { // from class: com.esotericsoftware.kryonet.rmi.ObjectSpace.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            ObjectSpace.this.invoke(connection, obj2, invokeMethod);
                        }
                    });
                }
            }

            @Override // com.esotericsoftware.kryonet.Listener
            public void disconnected(Connection connection) {
                ObjectSpace.this.removeConnection(connection);
            }
        };
        synchronized (instancesLock) {
            ObjectSpace[] objectSpaceArr = instances;
            ObjectSpace[] objectSpaceArr2 = new ObjectSpace[objectSpaceArr.length + 1];
            objectSpaceArr2[0] = this;
            System.arraycopy(objectSpaceArr, 0, objectSpaceArr2, 1, objectSpaceArr.length);
            instances = objectSpaceArr2;
        }
    }

    public ObjectSpace(Connection connection) {
        this();
        addConnection(connection);
    }

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    public void register(int i, Object obj) {
        if (i == Integer.MAX_VALUE) {
            throw new IllegalArgumentException("objectID cannot be Integer.MAX_VALUE.");
        }
        if (obj == null) {
            throw new NullPointerException("object to register cannot be null.");
        }
        this.idToObject.put(i, obj);
        this.objectToID.put(obj, i);
        if (Log.TRACE) {
            Log.trace("kryonet", "Object registered with ObjectSpace as " + i + ": " + obj);
        }
    }

    public void remove(int i) {
        Object remove = this.idToObject.remove(i);
        if (remove != null) {
            this.objectToID.remove(remove, 0);
        }
        if (Log.TRACE) {
            Log.trace("kryonet", "Object " + i + " removed from ObjectSpace: " + remove);
        }
    }

    public void remove(Object obj) {
        if (!this.idToObject.containsValue(obj, true)) {
            return;
        }
        int findKey = this.idToObject.findKey(obj, true, -1);
        this.idToObject.remove(findKey);
        this.objectToID.remove(obj, 0);
        if (Log.TRACE) {
            Log.trace("kryonet", "Object " + findKey + " removed from ObjectSpace: " + obj);
        }
    }

    public void close() {
        for (Connection connection : this.connections) {
            connection.removeListener(this.invokeListener);
        }
        synchronized (instancesLock) {
            ArrayList arrayList = new ArrayList(Arrays.asList(instances));
            arrayList.remove(this);
            instances = (ObjectSpace[]) arrayList.toArray(new ObjectSpace[arrayList.size()]);
        }
        if (Log.TRACE) {
            Log.trace("kryonet", "Closed ObjectSpace.");
        }
    }

    public void addConnection(Connection connection) {
        if (connection == null) {
            throw new NullPointerException("connection cannot be null.");
        }
        synchronized (this.connectionsLock) {
            Connection[] connectionArr = new Connection[this.connections.length + 1];
            connectionArr[0] = connection;
            System.arraycopy(this.connections, 0, connectionArr, 1, this.connections.length);
            this.connections = connectionArr;
        }
        connection.addListener(this.invokeListener);
        if (Log.TRACE) {
            Log.trace("kryonet", "Added connection to ObjectSpace: " + connection);
        }
    }

    public void removeConnection(Connection connection) {
        if (connection == null) {
            throw new NullPointerException("connection cannot be null.");
        }
        connection.removeListener(this.invokeListener);
        synchronized (this.connectionsLock) {
            ArrayList arrayList = new ArrayList(Arrays.asList(this.connections));
            arrayList.remove(connection);
            this.connections = (Connection[]) arrayList.toArray(new Connection[arrayList.size()]);
        }
        if (Log.TRACE) {
            Log.trace("kryonet", "Removed connection from ObjectSpace: " + connection);
        }
    }

    protected void invoke(Connection connection, Object obj, InvokeMethod invokeMethod) {
        Object cause;
        if (Log.DEBUG) {
            String str = "";
            if (invokeMethod.args != null) {
                String deepToString = Arrays.deepToString(invokeMethod.args);
                str = deepToString.substring(1, deepToString.length() - 1);
            }
            Log.debug("kryonet", connection + " received: " + obj.getClass().getSimpleName() + "#" + invokeMethod.cachedMethod.method.getName() + "(" + str + ")");
        }
        byte b2 = invokeMethod.responseData;
        boolean z = (b2 & 128) == 128;
        boolean z2 = (b2 & 64) == 64;
        int i = b2 & 63;
        CachedMethod cachedMethod = invokeMethod.cachedMethod;
        try {
            cause = cachedMethod.invoke(obj, invokeMethod.args);
        } catch (InvocationTargetException e) {
            if (z2) {
                cause = e.getCause();
            } else {
                throw new KryoNetException("Error invoking method: " + cachedMethod.method.getDeclaringClass().getName() + "." + cachedMethod.method.getName(), e);
            }
        } catch (Exception e2) {
            throw new KryoNetException("Error invoking method: " + cachedMethod.method.getDeclaringClass().getName() + "." + cachedMethod.method.getName(), e2);
        }
        if (i == 0) {
            return;
        }
        InvokeMethodResult invokeMethodResult = new InvokeMethodResult();
        invokeMethodResult.objectID = invokeMethod.objectID;
        invokeMethodResult.responseID = (byte) i;
        if (!z && !invokeMethod.cachedMethod.method.getReturnType().isPrimitive()) {
            invokeMethodResult.result = null;
        } else {
            invokeMethodResult.result = cause;
        }
        int sendTCP = connection.sendTCP(invokeMethodResult);
        if (Log.DEBUG) {
            Log.debug("kryonet", connection + " sent TCP: " + cause + " (" + sendTCP + ")");
        }
    }

    public static <T> T getRemoteObject(Connection connection, int i, Class<T> cls) {
        return (T) getRemoteObject(connection, i, (Class<?>[]) new Class[]{cls});
    }

    public static RemoteObject getRemoteObject(Connection connection, int i, Class<?>... clsArr) {
        if (connection == null) {
            throw new NullPointerException("connection cannot be null.");
        }
        if (clsArr == null) {
            throw new NullPointerException("ifaces cannot be null.");
        }
        Class[] clsArr2 = new Class[clsArr.length + 1];
        clsArr2[0] = RemoteObject.class;
        System.arraycopy(clsArr, 0, clsArr2, 1, clsArr.length);
        return (RemoteObject) Proxy.newProxyInstance(ObjectSpace.class.getClassLoader(), clsArr2, new RemoteInvocationHandler(connection, i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryonet/rmi/ObjectSpace$RemoteInvocationHandler.class */
    public static class RemoteInvocationHandler implements InvocationHandler {
        private final Connection connection;
        final int objectID;
        private boolean nonBlocking;
        private boolean remoteToString;
        private boolean udp;
        private Byte lastResponseID;
        private final Listener responseListener;
        private int timeoutMillis = EventListeners.PRIORITY_HIGHEST;
        private boolean transmitReturnValue = true;
        private boolean transmitExceptions = true;
        private byte nextResponseId = 1;
        final ReentrantLock lock = new ReentrantLock();
        final Condition responseCondition = this.lock.newCondition();
        final InvokeMethodResult[] responseTable = new InvokeMethodResult[64];
        final boolean[] pendingResponses = new boolean[64];

        public RemoteInvocationHandler(Connection connection, final int i) {
            this.connection = connection;
            this.objectID = i;
            this.responseListener = new Listener() { // from class: com.esotericsoftware.kryonet.rmi.ObjectSpace.RemoteInvocationHandler.1
                @Override // com.esotericsoftware.kryonet.Listener
                public void received(Connection connection2, Object obj) {
                    if (!(obj instanceof InvokeMethodResult)) {
                        return;
                    }
                    InvokeMethodResult invokeMethodResult = (InvokeMethodResult) obj;
                    if (invokeMethodResult.objectID != i) {
                        return;
                    }
                    byte b2 = invokeMethodResult.responseID;
                    synchronized (this) {
                        if (RemoteInvocationHandler.this.pendingResponses[b2]) {
                            RemoteInvocationHandler.this.responseTable[b2] = invokeMethodResult;
                        }
                    }
                    RemoteInvocationHandler.this.lock.lock();
                    try {
                        RemoteInvocationHandler.this.responseCondition.signalAll();
                    } finally {
                        RemoteInvocationHandler.this.lock.unlock();
                    }
                }

                @Override // com.esotericsoftware.kryonet.Listener
                public void disconnected(Connection connection2) {
                    RemoteInvocationHandler.this.close();
                }

                @Override // com.esotericsoftware.kryonet.Listener
                public void connected(Connection connection2) {
                }

                @Override // com.esotericsoftware.kryonet.Listener
                public void idle(Connection connection2) {
                }
            };
            connection.addListener(this.responseListener);
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) {
            int sendTCP;
            Boolean valueOf;
            Boolean valueOf2;
            Class<?> declaringClass = method.getDeclaringClass();
            if (declaringClass == RemoteObject.class) {
                String name = method.getName();
                boolean z = -1;
                switch (name.hashCode()) {
                    case -1992740650:
                        if (name.equals("setTransmitExceptions")) {
                            z = 5;
                            break;
                        }
                        break;
                    case -1760833285:
                        if (name.equals("hasResponse")) {
                            z = 11;
                            break;
                        }
                        break;
                    case -1481146837:
                        if (name.equals("waitForLastResponse")) {
                            z = 7;
                            break;
                        }
                        break;
                    case -1073400108:
                        if (name.equals("getConnection")) {
                            z = 12;
                            break;
                        }
                        break;
                    case -1053686385:
                        if (name.equals("setTransmitReturnValue")) {
                            z = 3;
                            break;
                        }
                        break;
                    case -906042191:
                        if (name.equals("hasLastResponse")) {
                            z = 8;
                            break;
                        }
                        break;
                    case -905799681:
                        if (name.equals("setUDP")) {
                            z = 4;
                            break;
                        }
                        break;
                    case -716076728:
                        if (name.equals("getLastResponseID")) {
                            z = 9;
                            break;
                        }
                        break;
                    case -402778016:
                        if (name.equals("setNonBlocking")) {
                            z = 2;
                            break;
                        }
                        break;
                    case -264822498:
                        if (name.equals("setResponseTimeout")) {
                            z = true;
                            break;
                        }
                        break;
                    case 84426196:
                        if (name.equals("setRemoteToString")) {
                            z = 6;
                            break;
                        }
                        break;
                    case 94756344:
                        if (name.equals("close")) {
                            z = false;
                            break;
                        }
                        break;
                    case 1118844789:
                        if (name.equals("waitForResponse")) {
                            z = 10;
                            break;
                        }
                        break;
                }
                switch (z) {
                    case false:
                        close();
                        return null;
                    case true:
                        this.timeoutMillis = ((Integer) objArr[0]).intValue();
                        return null;
                    case true:
                        this.nonBlocking = ((Boolean) objArr[0]).booleanValue();
                        return null;
                    case true:
                        this.transmitReturnValue = ((Boolean) objArr[0]).booleanValue();
                        return null;
                    case true:
                        this.udp = ((Boolean) objArr[0]).booleanValue();
                        return null;
                    case true:
                        this.transmitExceptions = ((Boolean) objArr[0]).booleanValue();
                        return null;
                    case true:
                        this.remoteToString = ((Boolean) objArr[0]).booleanValue();
                        return null;
                    case true:
                        if (this.lastResponseID == null) {
                            throw new IllegalStateException("There is no last response to wait for.");
                        }
                        return waitForResponse(this.lastResponseID.byteValue());
                    case true:
                        if (this.lastResponseID == null) {
                            throw new IllegalStateException("There is no last response.");
                        }
                        synchronized (this) {
                            valueOf2 = Boolean.valueOf(this.responseTable[this.lastResponseID.byteValue()] != null);
                        }
                        return valueOf2;
                    case true:
                        if (this.lastResponseID == null) {
                            throw new IllegalStateException("There is no last response ID.");
                        }
                        return this.lastResponseID;
                    case true:
                        if (!this.transmitReturnValue && !this.transmitExceptions && this.nonBlocking) {
                            throw new IllegalStateException("This RemoteObject is currently set to ignore all responses.");
                        }
                        return waitForResponse(((Byte) objArr[0]).byteValue());
                    case true:
                        synchronized (this) {
                            valueOf = Boolean.valueOf(this.responseTable[((Byte) objArr[0]).byteValue()] != null);
                        }
                        return valueOf;
                    case true:
                        return this.connection;
                    default:
                        throw new KryoNetException("Invocation handler could not find RemoteObject method. Check ObjectSpace.java");
                }
            }
            if (!this.remoteToString && declaringClass == Object.class && method.getName().equals("toString")) {
                return "<proxy>";
            }
            InvokeMethod invokeMethod = new InvokeMethod();
            invokeMethod.objectID = this.objectID;
            invokeMethod.args = objArr;
            CachedMethod[] methods = ObjectSpace.getMethods(this.connection.getEndPoint().getKryo(), method.getDeclaringClass());
            int i = 0;
            int length = methods.length;
            while (true) {
                if (i >= length) {
                    break;
                }
                CachedMethod cachedMethod = methods[i];
                if (!cachedMethod.method.equals(method)) {
                    i++;
                } else {
                    invokeMethod.cachedMethod = cachedMethod;
                    break;
                }
            }
            if (invokeMethod.cachedMethod == null) {
                throw new KryoNetException("Method not found: " + method);
            }
            byte b2 = 0;
            if (!this.udp && (this.transmitReturnValue || this.transmitExceptions || !this.nonBlocking)) {
                synchronized (this) {
                    byte b3 = this.nextResponseId;
                    this.nextResponseId = (byte) (b3 + 1);
                    b2 = b3;
                    if (this.nextResponseId > 63) {
                        this.nextResponseId = (byte) 1;
                    }
                    this.pendingResponses[b2] = true;
                }
                byte b4 = b2;
                if (this.transmitReturnValue) {
                    b4 = (byte) (b4 | 128);
                }
                if (this.transmitExceptions) {
                    b4 = (byte) (b4 | 64);
                }
                invokeMethod.responseData = b4;
            } else {
                invokeMethod.responseData = (byte) 0;
            }
            if (this.udp) {
                sendTCP = this.connection.sendUDP(invokeMethod);
            } else {
                sendTCP = this.connection.sendTCP(invokeMethod);
            }
            int i2 = sendTCP;
            if (Log.DEBUG) {
                String str = "";
                if (objArr != null) {
                    String deepToString = Arrays.deepToString(objArr);
                    str = deepToString.substring(1, deepToString.length() - 1);
                }
                Log.debug("kryonet", this.connection + " sent " + (this.udp ? "UDP" : "TCP") + ": " + method.getDeclaringClass().getSimpleName() + "#" + method.getName() + "(" + str + ") (" + i2 + ")");
            }
            this.lastResponseID = Byte.valueOf((byte) (invokeMethod.responseData & 63));
            if (!this.nonBlocking) {
                try {
                    if (!this.udp) {
                        try {
                            Object waitForResponse = waitForResponse(this.lastResponseID.byteValue());
                            if (waitForResponse instanceof Exception) {
                                throw ((Exception) waitForResponse);
                            }
                            synchronized (this) {
                                this.pendingResponses[b2] = false;
                                this.responseTable[b2] = null;
                            }
                            return waitForResponse;
                        } catch (TimeoutException unused) {
                            throw new TimeoutException("Response timed out: " + method.getDeclaringClass().getName() + "." + method.getName());
                        }
                    }
                } catch (Throwable th) {
                    synchronized (this) {
                        this.pendingResponses[b2] = false;
                        this.responseTable[b2] = null;
                        throw th;
                    }
                }
            }
            Class<?> returnType = method.getReturnType();
            if (returnType.isPrimitive()) {
                if (returnType == Integer.TYPE) {
                    return 0;
                }
                if (returnType == Boolean.TYPE) {
                    return Boolean.FALSE;
                }
                if (returnType == Float.TYPE) {
                    return Float.valueOf(0.0f);
                }
                if (returnType == Character.TYPE) {
                    return (char) 0;
                }
                if (returnType == Long.TYPE) {
                    return 0L;
                }
                if (returnType == Short.TYPE) {
                    return (short) 0;
                }
                if (returnType == Byte.TYPE) {
                    return (byte) 0;
                }
                if (returnType == Double.TYPE) {
                    return Double.valueOf(0.0d);
                }
                return null;
            }
            return null;
        }

        private Object waitForResponse(byte b2) {
            InvokeMethodResult invokeMethodResult;
            if (this.connection.getEndPoint().getUpdateThread() == Thread.currentThread()) {
                throw new IllegalStateException("Cannot wait for a RMI response on the connection's update thread.");
            }
            long currentTimeMillis = System.currentTimeMillis() + this.timeoutMillis;
            while (true) {
                long currentTimeMillis2 = currentTimeMillis - System.currentTimeMillis();
                synchronized (this) {
                    invokeMethodResult = this.responseTable[b2];
                }
                if (invokeMethodResult != null) {
                    this.lastResponseID = null;
                    return invokeMethodResult.result;
                }
                if (currentTimeMillis2 <= 0) {
                    throw new TimeoutException("Response timed out.");
                }
                this.lock.lock();
                try {
                    try {
                        this.responseCondition.await(currentTimeMillis2, TimeUnit.MILLISECONDS);
                        this.lock.unlock();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new KryoNetException(e);
                    }
                } catch (Throwable th) {
                    this.lock.unlock();
                    throw th;
                }
            }
        }

        void close() {
            this.connection.removeListener(this.responseListener);
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryonet/rmi/ObjectSpace$InvokeMethod.class */
    public static class InvokeMethod implements KryoSerializable, FrameworkMessage {
        public int objectID;
        public CachedMethod cachedMethod;
        public Object[] args;
        public byte responseData;

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void write(Kryo kryo, Output output) {
            output.writeInt(this.objectID, true);
            output.writeInt(this.cachedMethod.methodClassID, true);
            output.writeByte(this.cachedMethod.methodIndex);
            Serializer<?>[] serializerArr = this.cachedMethod.serializers;
            Object[] objArr = this.args;
            int length = serializerArr.length;
            for (int i = 0; i < length; i++) {
                Serializer<?> serializer = serializerArr[i];
                if (serializer != null) {
                    kryo.writeObjectOrNull(output, objArr[i], serializer);
                } else {
                    kryo.writeClassAndObject(output, objArr[i]);
                }
            }
            output.writeByte(this.responseData);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public void read(Kryo kryo, Input input) {
            this.objectID = input.readInt(true);
            Class type = kryo.getRegistration(input.readInt(true)).getType();
            byte readByte = input.readByte();
            try {
                this.cachedMethod = ObjectSpace.getMethods(kryo, type)[readByte];
                Serializer<?>[] serializerArr = this.cachedMethod.serializers;
                Class<?>[] parameterTypes = this.cachedMethod.method.getParameterTypes();
                Object[] objArr = new Object[serializerArr.length];
                this.args = objArr;
                int length = objArr.length;
                for (int i = 0; i < length; i++) {
                    Serializer<?> serializer = serializerArr[i];
                    if (serializer != null) {
                        objArr[i] = kryo.readObjectOrNull(input, parameterTypes[i], serializer);
                    } else {
                        objArr[i] = kryo.readClassAndObject(input);
                    }
                }
                this.responseData = input.readByte();
            } catch (IndexOutOfBoundsException unused) {
                throw new KryoException("Invalid method index " + ((int) readByte) + " for class: " + type.getName());
            }
        }
    }

    static CachedMethod[] getMethods(Kryo kryo, Class<?> cls) {
        CachedMethod[] cachedMethodArr = methodCache.get(cls);
        if (cachedMethodArr != null) {
            return cachedMethodArr;
        }
        ArrayList arrayList = new ArrayList();
        Class<?> cls2 = cls;
        while (cls2 != null) {
            Collections.addAll(arrayList, cls2.getDeclaredMethods());
            Class<? super Object> superclass = cls2.getSuperclass();
            cls2 = superclass;
            if (superclass == Object.class) {
                break;
            }
        }
        ArrayList arrayList2 = new ArrayList(Math.max(1, arrayList.size()));
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            Method method = (Method) arrayList.get(i);
            int modifiers = method.getModifiers();
            if (!Modifier.isStatic(modifiers) && !Modifier.isPrivate(modifiers) && !method.isSynthetic()) {
                arrayList2.add(method);
            }
        }
        Collections.sort(arrayList2, new Comparator<Method>() { // from class: com.esotericsoftware.kryonet.rmi.ObjectSpace.2
            @Override // java.util.Comparator
            public final int compare(Method method2, Method method3) {
                int compareTo = method2.getName().compareTo(method3.getName());
                if (compareTo != 0) {
                    return compareTo;
                }
                Class<?>[] parameterTypes = method2.getParameterTypes();
                Class<?>[] parameterTypes2 = method3.getParameterTypes();
                if (parameterTypes.length > parameterTypes2.length) {
                    return 1;
                }
                if (parameterTypes.length < parameterTypes2.length) {
                    return -1;
                }
                for (int i2 = 0; i2 < parameterTypes.length; i2++) {
                    int compareTo2 = parameterTypes[i2].getName().compareTo(parameterTypes2[i2].getName());
                    if (compareTo2 != 0) {
                        return compareTo2;
                    }
                }
                throw new RuntimeException("Two methods with same signature!");
            }
        });
        MethodAccess methodAccess = null;
        if (useAsm && !Util.isAndroid && Modifier.isPublic(cls.getModifiers())) {
            methodAccess = MethodAccess.get(cls);
        }
        int size2 = arrayList2.size();
        CachedMethod[] cachedMethodArr2 = new CachedMethod[size2];
        for (int i2 = 0; i2 < size2; i2++) {
            Method method2 = (Method) arrayList2.get(i2);
            Class<?>[] parameterTypes = method2.getParameterTypes();
            CachedMethod cachedMethod = null;
            if (methodAccess != null) {
                try {
                    AsmCachedMethod asmCachedMethod = new AsmCachedMethod();
                    asmCachedMethod.methodAccessIndex = methodAccess.getIndex(method2.getName(), parameterTypes);
                    asmCachedMethod.methodAccess = methodAccess;
                    cachedMethod = asmCachedMethod;
                } catch (RuntimeException unused) {
                }
            }
            if (cachedMethod == null) {
                cachedMethod = new CachedMethod();
            }
            cachedMethod.method = method2;
            cachedMethod.methodClassID = kryo.getRegistration(method2.getDeclaringClass()).getId();
            cachedMethod.methodIndex = i2;
            cachedMethod.serializers = new Serializer[parameterTypes.length];
            int length = parameterTypes.length;
            for (int i3 = 0; i3 < length; i3++) {
                if (kryo.isFinal(parameterTypes[i3])) {
                    cachedMethod.serializers[i3] = kryo.getSerializer(parameterTypes[i3]);
                }
            }
            cachedMethodArr2[i2] = cachedMethod;
        }
        methodCache.put(cls, cachedMethodArr2);
        return cachedMethodArr2;
    }

    static Object getRegisteredObject(Connection connection, int i) {
        Object obj;
        for (ObjectSpace objectSpace : instances) {
            for (Connection connection2 : objectSpace.connections) {
                if (connection2 == connection && (obj = objectSpace.idToObject.get(i)) != null) {
                    return obj;
                }
            }
        }
        return null;
    }

    static int getRegisteredID(Connection connection, Object obj) {
        int i;
        for (ObjectSpace objectSpace : instances) {
            for (Connection connection2 : objectSpace.connections) {
                if (connection2 == connection && (i = objectSpace.objectToID.get(obj, Integer.MAX_VALUE)) != Integer.MAX_VALUE) {
                    return i;
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    public static void registerClasses(Kryo kryo) {
        kryo.register(Object[].class);
        kryo.register(InvokeMethod.class);
        FieldSerializer<InvokeMethodResult> fieldSerializer = new FieldSerializer<InvokeMethodResult>(kryo, InvokeMethodResult.class) { // from class: com.esotericsoftware.kryonet.rmi.ObjectSpace.3
            @Override // com.esotericsoftware.kryo.serializers.FieldSerializer, com.esotericsoftware.kryo.Serializer
            /* renamed from: read */
            public final /* bridge */ /* synthetic */ Object read2(Kryo kryo2, Input input, Class cls) {
                return read2(kryo2, input, (Class<? extends InvokeMethodResult>) cls);
            }

            @Override // com.esotericsoftware.kryo.serializers.FieldSerializer, com.esotericsoftware.kryo.Serializer
            public final void write(Kryo kryo2, Output output, InvokeMethodResult invokeMethodResult) {
                super.write(kryo2, output, (Output) invokeMethodResult);
                output.writeInt(invokeMethodResult.objectID, true);
            }

            @Override // com.esotericsoftware.kryo.serializers.FieldSerializer, com.esotericsoftware.kryo.Serializer
            /* renamed from: read */
            public final InvokeMethodResult read2(Kryo kryo2, Input input, Class<? extends InvokeMethodResult> cls) {
                InvokeMethodResult invokeMethodResult = (InvokeMethodResult) super.read2(kryo2, input, (Class) cls);
                invokeMethodResult.objectID = input.readInt(true);
                return invokeMethodResult;
            }
        };
        fieldSerializer.removeField("objectID");
        kryo.register(InvokeMethodResult.class, fieldSerializer);
        kryo.register(InvocationHandler.class, new Serializer() { // from class: com.esotericsoftware.kryonet.rmi.ObjectSpace.4
            @Override // com.esotericsoftware.kryo.Serializer
            public final void write(Kryo kryo2, Output output, Object obj) {
                output.writeInt(((RemoteInvocationHandler) Proxy.getInvocationHandler(obj)).objectID, true);
            }

            @Override // com.esotericsoftware.kryo.Serializer
            /* renamed from: read */
            public final Object read2(Kryo kryo2, Input input, Class cls) {
                int readInt = input.readInt(true);
                Connection connection = (Connection) kryo2.getContext().get("connection");
                if (connection == null) {
                    throw new KryoException("Connection in kryo context cannot be null", new NullPointerException());
                }
                Object registeredObject = ObjectSpace.getRegisteredObject(connection, readInt);
                if (Log.WARN && registeredObject == null) {
                    Log.warn("kryonet", "Unknown object ID " + readInt + " for connection: " + connection);
                }
                return registeredObject;
            }
        });
    }

    public static void setAsm(boolean z) {
        useAsm = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryonet/rmi/ObjectSpace$CachedMethod.class */
    public static class CachedMethod {
        Method method;
        int methodClassID;
        int methodIndex;
        Serializer<?>[] serializers;

        CachedMethod() {
        }

        public Object invoke(Object obj, Object[] objArr) {
            return this.method.invoke(obj, objArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryonet/rmi/ObjectSpace$AsmCachedMethod.class */
    public static class AsmCachedMethod extends CachedMethod {
        MethodAccess methodAccess;
        int methodAccessIndex = -1;

        AsmCachedMethod() {
        }

        @Override // com.esotericsoftware.kryonet.rmi.ObjectSpace.CachedMethod
        public Object invoke(Object obj, Object[] objArr) {
            try {
                return this.methodAccess.invoke(obj, this.methodAccessIndex, objArr);
            } catch (Exception e) {
                throw new InvocationTargetException(e);
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryonet/rmi/ObjectSpace$RemoteObjectSerializer.class */
    public static class RemoteObjectSerializer extends Serializer {
        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Object obj) {
            Connection connection = (Connection) kryo.getContext().get("connection");
            if (connection == null) {
                throw new KryoException("Connection in kryo context cannot be null", new NullPointerException());
            }
            int registeredID = ObjectSpace.getRegisteredID(connection, obj);
            if (registeredID == Integer.MAX_VALUE) {
                throw new KryoNetException("Object not found in an ObjectSpace: " + obj);
            }
            output.writeInt(registeredID, true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public Object read2(Kryo kryo, Input input, Class cls) {
            int readInt = input.readInt(true);
            Connection connection = (Connection) kryo.getContext().get("connection");
            if (connection == null) {
                throw new KryoException("Connection in kryo context cannot be null", new NullPointerException());
            }
            return ObjectSpace.getRemoteObject(connection, readInt, cls);
        }
    }
}
