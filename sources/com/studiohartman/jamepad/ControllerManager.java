package com.studiohartman.jamepad;

import com.badlogic.gdx.utils.SharedLibraryLoader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;

/* loaded from: infinitode-2.jar:com/studiohartman/jamepad/ControllerManager.class */
public class ControllerManager {

    /* renamed from: a, reason: collision with root package name */
    private final a f4021a;

    /* renamed from: b, reason: collision with root package name */
    private String f4022b;
    private boolean c;
    private ControllerIndex[] d;

    private native boolean nativeInitSDLGamepad(boolean z);

    private native void nativeCloseSDLGamepad();

    private native boolean nativeControllerConnectedOrDisconnected();

    private native boolean nativeAddMappingsFromFile(String str);

    private native boolean nativeAddMappingsFromBuffer(byte[] bArr, int i);

    public native String getLastNativeError();

    public ControllerManager() {
        this(new a(), "/gamecontrollerdb.txt");
    }

    public ControllerManager(a aVar) {
        this(aVar, "/gamecontrollerdb.txt");
    }

    private ControllerManager(a aVar, String str) {
        this.f4021a = aVar;
        this.f4022b = str;
        this.c = false;
        this.d = new ControllerIndex[aVar.f4023a];
        if (aVar.c) {
            new SharedLibraryLoader().load("jamepad");
        }
    }

    public final void a() {
        if (this.c) {
            throw new IllegalStateException("SDL is already initialized!");
        }
        if (!nativeInitSDLGamepad(true)) {
            throw new IllegalStateException("Failed to initialize SDL in native method!");
        }
        this.c = true;
        try {
            a(this.f4022b);
        } catch (IOException | IllegalStateException e) {
            System.err.println("Failed to load mapping with original location \"" + this.f4022b + "\", Falling back of SDL's built in mappings");
            e.printStackTrace();
        }
        for (int i = 0; i < this.d.length; i++) {
            this.d[i] = new ControllerIndex(i);
        }
    }

    public final void b() {
        for (ControllerIndex controllerIndex : this.d) {
            controllerIndex.a();
        }
        nativeCloseSDLGamepad();
        this.d = new ControllerIndex[0];
        this.c = false;
    }

    public final ControllerIndex a(int i) {
        d();
        return this.d[i];
    }

    public final void c() {
        d();
        if (nativeControllerConnectedOrDisconnected()) {
            for (int i = 0; i < this.d.length; i++) {
                this.d[i].b();
            }
        }
    }

    public final void a(String str) {
        InputStream resourceAsStream = getClass().getResourceAsStream(str);
        InputStream inputStream = resourceAsStream;
        if (resourceAsStream == null) {
            inputStream = ClassLoader.getSystemResourceAsStream(str);
        }
        if (inputStream == null) {
            throw new IOException("Cannot open resource from classpath " + str);
        }
        if (this.f4021a.d) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[4096];
            while (true) {
                int read = inputStream.read(bArr, 0, 4096);
                if (read == -1) {
                    break;
                } else {
                    byteArrayOutputStream.write(bArr, 0, read);
                }
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            if (!nativeAddMappingsFromBuffer(byteArray, byteArray.length)) {
                throw new IllegalStateException("Failed to set SDL controller mappings! Falling back to build in SDL mappings.");
            }
            return;
        }
        Path absolutePath = Files.createTempFile(null, null, new FileAttribute[0]).toAbsolutePath();
        Files.copy(inputStream, absolutePath, StandardCopyOption.REPLACE_EXISTING);
        if (!nativeAddMappingsFromFile(absolutePath.toString())) {
            throw new IllegalStateException("Failed to set SDL controller mappings! Falling back to build in SDL mappings.");
        }
        Files.delete(absolutePath);
    }

    private boolean d() {
        if (!this.c) {
            throw new IllegalStateException("SDL_GameController is not initialized!");
        }
        return true;
    }
}
