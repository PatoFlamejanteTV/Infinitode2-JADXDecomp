package net.bytebuddy.utility;

import java.security.AccessController;
import java.security.PrivilegedAction;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.build.AccessControllerPlugin;
import net.bytebuddy.jar.asm.ClassReader;
import net.bytebuddy.utility.privilege.GetSystemPropertyAction;

/* loaded from: infinitode-2.jar:net/bytebuddy/utility/OpenedClassReader.class */
public class OpenedClassReader {
    public static final String EXPERIMENTAL_PROPERTY = "net.bytebuddy.experimental";
    public static final boolean EXPERIMENTAL;
    public static final int ASM_API;
    private static final boolean ACCESS_CONTROLLER;

    static {
        boolean z;
        try {
            Class.forName("java.security.AccessController", false, null);
            ACCESS_CONTROLLER = Boolean.parseBoolean(System.getProperty("net.bytebuddy.securitymanager", "true"));
        } catch (ClassNotFoundException unused) {
            ACCESS_CONTROLLER = false;
        } catch (SecurityException unused2) {
            ACCESS_CONTROLLER = true;
        }
        try {
            z = Boolean.parseBoolean((String) doPrivileged(new GetSystemPropertyAction(EXPERIMENTAL_PROPERTY)));
        } catch (Exception unused3) {
            z = false;
        }
        EXPERIMENTAL = z;
        ASM_API = 589824;
    }

    private OpenedClassReader() {
        throw new UnsupportedOperationException("This class is a utility class and not supposed to be instantiated");
    }

    @AccessControllerPlugin.Enhance
    private static <T> T doPrivileged(PrivilegedAction<T> privilegedAction) {
        return ACCESS_CONTROLLER ? (T) AccessController.doPrivileged(privilegedAction) : privilegedAction.run();
    }

    public static ClassReader of(byte[] bArr) {
        ClassFileVersion ofClassFile = ClassFileVersion.ofClassFile(bArr);
        ClassFileVersion latest = ClassFileVersion.latest();
        if (ofClassFile.isGreaterThan(latest)) {
            if (EXPERIMENTAL) {
                bArr[6] = (byte) (latest.getMajorVersion() >>> 8);
                bArr[7] = (byte) latest.getMajorVersion();
                ClassReader classReader = new ClassReader(bArr);
                bArr[6] = (byte) (ofClassFile.getMajorVersion() >>> 8);
                bArr[7] = (byte) ofClassFile.getMajorVersion();
                return classReader;
            }
            throw new IllegalArgumentException(ofClassFile + " is not supported by the current version of Byte Buddy which officially supports " + latest + " - update Byte Buddy or set net.bytebuddy.experimental as a VM property");
        }
        return new ClassReader(bArr);
    }
}
