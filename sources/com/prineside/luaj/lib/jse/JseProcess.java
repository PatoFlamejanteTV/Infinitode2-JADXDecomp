package com.prineside.luaj.lib.jse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: infinitode-2.jar:com/prineside/luaj/lib/jse/JseProcess.class */
public class JseProcess {

    /* renamed from: a, reason: collision with root package name */
    private Process f1632a;

    /* renamed from: b, reason: collision with root package name */
    private Thread f1633b;
    private Thread c;
    private Thread d;

    public JseProcess(String[] strArr, InputStream inputStream, OutputStream outputStream, OutputStream outputStream2) {
        this(Runtime.getRuntime().exec(strArr), inputStream, outputStream, outputStream2);
    }

    public JseProcess(String str, InputStream inputStream, OutputStream outputStream, OutputStream outputStream2) {
        this(Runtime.getRuntime().exec(str), inputStream, outputStream, outputStream2);
    }

    private JseProcess(Process process, InputStream inputStream, OutputStream outputStream, OutputStream outputStream2) {
        this.f1632a = process;
        this.f1633b = inputStream == null ? null : a(inputStream, process.getOutputStream(), null, process.getOutputStream());
        this.c = outputStream == null ? null : a(process.getInputStream(), outputStream, process.getInputStream(), null);
        this.d = outputStream2 == null ? null : a(process.getErrorStream(), outputStream2, process.getErrorStream(), null);
    }

    public int exitValue() {
        return this.f1632a.exitValue();
    }

    public int waitFor() {
        int waitFor = this.f1632a.waitFor();
        if (this.f1633b != null) {
            this.f1633b.join();
        }
        if (this.c != null) {
            this.c.join();
        }
        if (this.d != null) {
            this.d.join();
        }
        this.f1632a.destroy();
        return waitFor;
    }

    private static Thread a(InputStream inputStream, OutputStream outputStream, InputStream inputStream2, OutputStream outputStream2) {
        CopyThread copyThread = new CopyThread(outputStream, outputStream2, inputStream2, inputStream, (byte) 0);
        copyThread.setDaemon(true);
        copyThread.start();
        return copyThread;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/luaj/lib/jse/JseProcess$CopyThread.class */
    public static final class CopyThread extends Thread {

        /* renamed from: a, reason: collision with root package name */
        private final OutputStream f1634a;

        /* renamed from: b, reason: collision with root package name */
        private final OutputStream f1635b;
        private final InputStream c;
        private final InputStream d;

        /* synthetic */ CopyThread(OutputStream outputStream, OutputStream outputStream2, InputStream inputStream, InputStream inputStream2, byte b2) {
            this(outputStream, outputStream2, inputStream, inputStream2);
        }

        private CopyThread(OutputStream outputStream, OutputStream outputStream2, InputStream inputStream, InputStream inputStream2) {
            this.f1634a = outputStream;
            this.f1635b = outputStream2;
            this.c = inputStream;
            this.d = inputStream2;
        }

        /* JADX WARN: Finally extract failed */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v0, types: [java.io.IOException] */
        /* JADX WARN: Type inference failed for: r0v15, types: [int] */
        /* JADX WARN: Type inference failed for: r0v25, types: [java.io.OutputStream] */
        /* JADX WARN: Type inference failed for: r0v26 */
        /* JADX WARN: Type inference failed for: r0v27 */
        /* JADX WARN: Type inference failed for: r0v3 */
        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            ?? r0;
            try {
                byte[] bArr = new byte[1024];
                r0 = bArr;
                while (true) {
                    try {
                        r0 = this.d.read(bArr);
                        if (r0 < 0) {
                            break;
                        }
                        ?? r02 = this.f1634a;
                        r02.write(bArr, 0, r0);
                        r0 = r02;
                    } catch (Throwable th) {
                        if (this.c != null) {
                            this.c.close();
                        }
                        if (this.f1635b != null) {
                            this.f1635b.close();
                        }
                        throw th;
                    }
                }
                if (this.c != null) {
                    this.c.close();
                }
                if (this.f1635b != null) {
                    this.f1635b.close();
                }
            } catch (IOException e) {
                r0.printStackTrace();
            }
        }
    }
}
