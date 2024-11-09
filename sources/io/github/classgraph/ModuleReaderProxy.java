package io.github.classgraph;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.ByteBuffer;
import java.util.List;
import nonapi.io.github.classgraph.reflection.ReflectionUtils;

/* loaded from: infinitode-2.jar:io/github/classgraph/ModuleReaderProxy.class */
public class ModuleReaderProxy implements Closeable {
    private final AutoCloseable moduleReader;
    private static Class<?> collectorClass;
    private static Object collectorsToList;
    private ReflectionUtils reflectionUtils;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ModuleReaderProxy(ModuleRef moduleRef) {
        try {
            this.reflectionUtils = moduleRef.reflectionUtils;
            if (collectorClass == null || collectorsToList == null) {
                collectorClass = this.reflectionUtils.classForNameOrNull("java.util.stream.Collector");
                Class<?> classForNameOrNull = this.reflectionUtils.classForNameOrNull("java.util.stream.Collectors");
                if (classForNameOrNull != null) {
                    collectorsToList = this.reflectionUtils.invokeStaticMethod(true, classForNameOrNull, "toList");
                }
            }
            this.moduleReader = (AutoCloseable) this.reflectionUtils.invokeMethod(true, moduleRef.getReference(), "open");
            if (this.moduleReader == null) {
                throw new IllegalArgumentException("moduleReference.open() should not return null");
            }
        } catch (SecurityException e) {
            throw new IOException("Could not open module " + moduleRef.getName(), e);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        try {
            this.moduleReader.close();
        } catch (Exception unused) {
        }
    }

    public List<String> list() {
        if (collectorsToList == null) {
            throw new IllegalArgumentException("Could not call Collectors.toList()");
        }
        Object invokeMethod = this.reflectionUtils.invokeMethod(true, this.moduleReader, "list");
        if (invokeMethod != null) {
            Object invokeMethod2 = this.reflectionUtils.invokeMethod(true, invokeMethod, "collect", collectorClass, collectorsToList);
            if (invokeMethod2 == null) {
                throw new IllegalArgumentException("Could not call moduleReader.list().collect(Collectors.toList())");
            }
            return (List) invokeMethod2;
        }
        throw new IllegalArgumentException("Could not call moduleReader.list()");
    }

    public InputStream open(String str) {
        Object invokeMethod = this.reflectionUtils.invokeMethod(true, this.moduleReader, "open", String.class, str);
        if (invokeMethod != null) {
            InputStream inputStream = (InputStream) this.reflectionUtils.invokeMethod(true, invokeMethod, "get");
            if (inputStream == null) {
                throw new IllegalArgumentException("Got null result from ModuleReader#open(String)#get()");
            }
            return inputStream;
        }
        throw new IllegalArgumentException("Got null result from ModuleReader#open for path " + str);
    }

    public ByteBuffer read(String str) {
        Object invokeMethod = this.reflectionUtils.invokeMethod(true, this.moduleReader, "read", String.class, str);
        if (invokeMethod != null) {
            ByteBuffer byteBuffer = (ByteBuffer) this.reflectionUtils.invokeMethod(true, invokeMethod, "get");
            if (byteBuffer == null) {
                throw new IllegalArgumentException("Got null result from ModuleReader#read(String).get()");
            }
            return byteBuffer;
        }
        throw new IllegalArgumentException("Got null result from ModuleReader#read(String)");
    }

    public void release(ByteBuffer byteBuffer) {
        this.reflectionUtils.invokeMethod(true, this.moduleReader, "release", ByteBuffer.class, byteBuffer);
    }

    public URI find(String str) {
        Object invokeMethod = this.reflectionUtils.invokeMethod(true, this.moduleReader, "find", String.class, str);
        if (invokeMethod != null) {
            URI uri = (URI) this.reflectionUtils.invokeMethod(true, invokeMethod, "get");
            if (uri == null) {
                throw new IllegalArgumentException("Got null result from ModuleReader#find(String).get()");
            }
            return uri;
        }
        throw new IllegalArgumentException("Got null result from ModuleReader#find(String)");
    }
}
