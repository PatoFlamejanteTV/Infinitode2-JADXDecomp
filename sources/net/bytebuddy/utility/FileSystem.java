package net.bytebuddy.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import net.bytebuddy.build.AccessControllerPlugin;
import net.bytebuddy.build.CachedReturnPlugin;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import net.bytebuddy.utility.nullability.MaybeNull;

/* loaded from: infinitode-2.jar:net/bytebuddy/utility/FileSystem.class */
public abstract class FileSystem {
    private static /* synthetic */ FileSystem INSTANCE;
    private static final boolean ACCESS_CONTROLLER;

    public abstract void copy(File file, File file2);

    public abstract void move(File file, File file2);

    static {
        try {
            Class.forName("java.security.AccessController", false, null);
            ACCESS_CONTROLLER = Boolean.parseBoolean(System.getProperty("net.bytebuddy.securitymanager", "true"));
        } catch (ClassNotFoundException unused) {
            ACCESS_CONTROLLER = false;
        } catch (SecurityException unused2) {
            ACCESS_CONTROLLER = true;
        }
    }

    @CachedReturnPlugin.Enhance("INSTANCE")
    public static FileSystem getInstance() {
        FileSystem forLegacyVm;
        if (INSTANCE != null) {
            forLegacyVm = null;
        } else {
            try {
                Class.forName("java.nio.file.Files", false, ClassLoadingStrategy.BOOTSTRAP_LOADER);
                forLegacyVm = new ForNio2CapableVm();
            } catch (ClassNotFoundException unused) {
                forLegacyVm = new ForLegacyVm();
            }
        }
        FileSystem fileSystem = forLegacyVm;
        if (forLegacyVm == null) {
            fileSystem = INSTANCE;
        } else {
            INSTANCE = fileSystem;
        }
        return fileSystem;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @AccessControllerPlugin.Enhance
    public static <T> T doPrivileged(PrivilegedAction<T> privilegedAction) {
        return ACCESS_CONTROLLER ? (T) AccessController.doPrivileged(privilegedAction) : privilegedAction.run();
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/utility/FileSystem$ForLegacyVm.class */
    protected static class ForLegacyVm extends FileSystem {
        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass();
        }

        public int hashCode() {
            return getClass().hashCode();
        }

        protected ForLegacyVm() {
        }

        @Override // net.bytebuddy.utility.FileSystem
        public void copy(File file, File file2) {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read != -1) {
                            fileOutputStream.write(bArr, 0, read);
                        } else {
                            return;
                        }
                    }
                } finally {
                    fileOutputStream.close();
                }
            } finally {
                fileInputStream.close();
            }
        }

        @Override // net.bytebuddy.utility.FileSystem
        public void move(File file, File file2) {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        } else {
                            fileOutputStream.write(bArr, 0, read);
                        }
                    }
                    if (!file.delete()) {
                        file.deleteOnExit();
                    }
                } finally {
                    fileOutputStream.close();
                }
            } finally {
                fileInputStream.close();
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/utility/FileSystem$ForNio2CapableVm.class */
    protected static class ForNio2CapableVm extends FileSystem {
        private static final Dispatcher DISPATCHER = (Dispatcher) FileSystem.doPrivileged(JavaDispatcher.of(Dispatcher.class));
        private static final Files FILES = (Files) FileSystem.doPrivileged(JavaDispatcher.of(Files.class));
        private static final StandardCopyOption STANDARD_COPY_OPTION = (StandardCopyOption) FileSystem.doPrivileged(JavaDispatcher.of(StandardCopyOption.class));

        @JavaDispatcher.Proxied("java.io.File")
        /* loaded from: infinitode-2.jar:net/bytebuddy/utility/FileSystem$ForNio2CapableVm$Dispatcher.class */
        protected interface Dispatcher {
            @JavaDispatcher.Proxied("toPath")
            Object toPath(File file);
        }

        @JavaDispatcher.Proxied("java.nio.file.Files")
        /* loaded from: infinitode-2.jar:net/bytebuddy/utility/FileSystem$ForNio2CapableVm$Files.class */
        protected interface Files {
            @JavaDispatcher.IsStatic
            @JavaDispatcher.Proxied("copy")
            Object copy(@JavaDispatcher.Proxied("java.nio.file.Path") Object obj, @JavaDispatcher.Proxied("java.nio.file.Path") Object obj2, @JavaDispatcher.Proxied("java.nio.file.CopyOption") Object[] objArr);

            @JavaDispatcher.IsStatic
            @JavaDispatcher.Proxied("move")
            Object move(@JavaDispatcher.Proxied("java.nio.file.Path") Object obj, @JavaDispatcher.Proxied("java.nio.file.Path") Object obj2, @JavaDispatcher.Proxied("java.nio.file.CopyOption") Object[] objArr);
        }

        @JavaDispatcher.Proxied("java.nio.file.StandardCopyOption")
        /* loaded from: infinitode-2.jar:net/bytebuddy/utility/FileSystem$ForNio2CapableVm$StandardCopyOption.class */
        protected interface StandardCopyOption {
            @JavaDispatcher.Container
            @JavaDispatcher.Proxied("toArray")
            Object[] toArray(int i);

            @JavaDispatcher.IsStatic
            @JavaDispatcher.Proxied("valueOf")
            Object valueOf(String str);
        }

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass();
        }

        public int hashCode() {
            return getClass().hashCode();
        }

        protected ForNio2CapableVm() {
        }

        @Override // net.bytebuddy.utility.FileSystem
        public void copy(File file, File file2) {
            Object[] array = STANDARD_COPY_OPTION.toArray(1);
            array[0] = STANDARD_COPY_OPTION.valueOf("REPLACE_EXISTING");
            FILES.copy(DISPATCHER.toPath(file), DISPATCHER.toPath(file2), array);
        }

        @Override // net.bytebuddy.utility.FileSystem
        public void move(File file, File file2) {
            Object[] array = STANDARD_COPY_OPTION.toArray(1);
            array[0] = STANDARD_COPY_OPTION.valueOf("REPLACE_EXISTING");
            FILES.move(DISPATCHER.toPath(file), DISPATCHER.toPath(file2), array);
        }
    }
}
