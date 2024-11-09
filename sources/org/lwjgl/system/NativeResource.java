package org.lwjgl.system;

/* loaded from: infinitode-2.jar:org/lwjgl/system/NativeResource.class */
public interface NativeResource extends AutoCloseable {
    void free();

    @Override // java.lang.AutoCloseable
    default void close() {
        free();
    }
}
