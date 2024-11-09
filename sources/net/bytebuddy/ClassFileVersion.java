package net.bytebuddy;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import net.bytebuddy.build.AccessControllerPlugin;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.utility.OpenedClassReader;
import net.bytebuddy.utility.nullability.MaybeNull;

@HashCodeAndEqualsPlugin.Enhance
/* loaded from: infinitode-2.jar:net/bytebuddy/ClassFileVersion.class */
public class ClassFileVersion implements Serializable, Comparable<ClassFileVersion> {
    private static final long serialVersionUID = 1;
    protected static final int BASE_VERSION = 44;
    public static final ClassFileVersion JAVA_V1;
    public static final ClassFileVersion JAVA_V2;
    public static final ClassFileVersion JAVA_V3;
    public static final ClassFileVersion JAVA_V4;
    public static final ClassFileVersion JAVA_V5;
    public static final ClassFileVersion JAVA_V6;
    public static final ClassFileVersion JAVA_V7;
    public static final ClassFileVersion JAVA_V8;
    public static final ClassFileVersion JAVA_V9;
    public static final ClassFileVersion JAVA_V10;
    public static final ClassFileVersion JAVA_V11;
    public static final ClassFileVersion JAVA_V12;
    public static final ClassFileVersion JAVA_V13;
    public static final ClassFileVersion JAVA_V14;
    public static final ClassFileVersion JAVA_V15;
    public static final ClassFileVersion JAVA_V16;
    public static final ClassFileVersion JAVA_V17;
    public static final ClassFileVersion JAVA_V18;
    public static final ClassFileVersion JAVA_V19;
    public static final ClassFileVersion JAVA_V20;
    private static final VersionLocator VERSION_LOCATOR;
    private final int versionNumber;
    private static final boolean ACCESS_CONTROLLER;

    public boolean equals(@MaybeNull Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.versionNumber == ((ClassFileVersion) obj).versionNumber;
    }

    public int hashCode() {
        return (getClass().hashCode() * 31) + this.versionNumber;
    }

    static {
        try {
            Class.forName("java.security.AccessController", false, null);
            ACCESS_CONTROLLER = Boolean.parseBoolean(System.getProperty("net.bytebuddy.securitymanager", "true"));
        } catch (ClassNotFoundException unused) {
            ACCESS_CONTROLLER = false;
        } catch (SecurityException unused2) {
            ACCESS_CONTROLLER = true;
        }
        JAVA_V1 = new ClassFileVersion(196653);
        JAVA_V2 = new ClassFileVersion(46);
        JAVA_V3 = new ClassFileVersion(47);
        JAVA_V4 = new ClassFileVersion(48);
        JAVA_V5 = new ClassFileVersion(49);
        JAVA_V6 = new ClassFileVersion(50);
        JAVA_V7 = new ClassFileVersion(51);
        JAVA_V8 = new ClassFileVersion(52);
        JAVA_V9 = new ClassFileVersion(53);
        JAVA_V10 = new ClassFileVersion(54);
        JAVA_V11 = new ClassFileVersion(55);
        JAVA_V12 = new ClassFileVersion(56);
        JAVA_V13 = new ClassFileVersion(57);
        JAVA_V14 = new ClassFileVersion(58);
        JAVA_V15 = new ClassFileVersion(59);
        JAVA_V16 = new ClassFileVersion(60);
        JAVA_V17 = new ClassFileVersion(61);
        JAVA_V18 = new ClassFileVersion(62);
        JAVA_V19 = new ClassFileVersion(63);
        JAVA_V20 = new ClassFileVersion(64);
        VERSION_LOCATOR = (VersionLocator) doPrivileged(VersionLocator.Resolver.INSTANCE);
    }

    protected ClassFileVersion(int i) {
        this.versionNumber = i;
    }

    @AccessControllerPlugin.Enhance
    private static <T> T doPrivileged(PrivilegedAction<T> privilegedAction) {
        return ACCESS_CONTROLLER ? (T) AccessController.doPrivileged(privilegedAction) : privilegedAction.run();
    }

    public static ClassFileVersion ofMinorMajor(int i) {
        ClassFileVersion classFileVersion = new ClassFileVersion(i);
        if (classFileVersion.getMajorVersion() <= 44) {
            throw new IllegalArgumentException("Class version " + i + " is not valid");
        }
        return classFileVersion;
    }

    public static ClassFileVersion ofJavaVersionString(String str) {
        if (str.equals("1.1")) {
            return JAVA_V1;
        }
        if (str.equals("1.2")) {
            return JAVA_V2;
        }
        if (str.equals("1.3")) {
            return JAVA_V3;
        }
        if (str.equals("1.4")) {
            return JAVA_V4;
        }
        if (str.equals("1.5") || str.equals("5")) {
            return JAVA_V5;
        }
        if (str.equals("1.6") || str.equals("6")) {
            return JAVA_V6;
        }
        if (str.equals("1.7") || str.equals("7")) {
            return JAVA_V7;
        }
        if (str.equals("1.8") || str.equals("8")) {
            return JAVA_V8;
        }
        if (str.equals("1.9") || str.equals("9")) {
            return JAVA_V9;
        }
        if (str.equals("1.10") || str.equals("10")) {
            return JAVA_V10;
        }
        if (str.equals("1.11") || str.equals("11")) {
            return JAVA_V11;
        }
        if (str.equals("1.12") || str.equals("12")) {
            return JAVA_V12;
        }
        if (str.equals("1.13") || str.equals("13")) {
            return JAVA_V13;
        }
        if (str.equals("1.14") || str.equals("14")) {
            return JAVA_V14;
        }
        if (str.equals("1.15") || str.equals("15")) {
            return JAVA_V15;
        }
        if (str.equals("1.16") || str.equals("16")) {
            return JAVA_V16;
        }
        if (str.equals("1.17") || str.equals("17")) {
            return JAVA_V17;
        }
        if (str.equals("1.18") || str.equals("18")) {
            return JAVA_V18;
        }
        if (str.equals("1.19") || str.equals("19")) {
            return JAVA_V19;
        }
        if (str.equals("1.20") || str.equals("20")) {
            return JAVA_V20;
        }
        if (OpenedClassReader.EXPERIMENTAL) {
            try {
                int parseInt = Integer.parseInt(str.startsWith("1.") ? str.substring(2) : str);
                if (parseInt > 0) {
                    return new ClassFileVersion(parseInt + 44);
                }
            } catch (NumberFormatException unused) {
            }
        }
        throw new IllegalArgumentException("Unknown Java version string: " + str);
    }

    public static ClassFileVersion ofJavaVersion(int i) {
        switch (i) {
            case 1:
                return JAVA_V1;
            case 2:
                return JAVA_V2;
            case 3:
                return JAVA_V3;
            case 4:
                return JAVA_V4;
            case 5:
                return JAVA_V5;
            case 6:
                return JAVA_V6;
            case 7:
                return JAVA_V7;
            case 8:
                return JAVA_V8;
            case 9:
                return JAVA_V9;
            case 10:
                return JAVA_V10;
            case 11:
                return JAVA_V11;
            case 12:
                return JAVA_V12;
            case 13:
                return JAVA_V13;
            case 14:
                return JAVA_V14;
            case 15:
                return JAVA_V15;
            case 16:
                return JAVA_V16;
            case 17:
                return JAVA_V17;
            case 18:
                return JAVA_V18;
            case 19:
                return JAVA_V19;
            case 20:
                return JAVA_V20;
            default:
                if (OpenedClassReader.EXPERIMENTAL && i > 0) {
                    return new ClassFileVersion(i + 44);
                }
                throw new IllegalArgumentException("Unknown Java version: " + i);
        }
    }

    public static ClassFileVersion latest() {
        return JAVA_V20;
    }

    public static ClassFileVersion ofThisVm() {
        return VERSION_LOCATOR.resolve();
    }

    @SuppressFBWarnings(value = {"REC_CATCH_EXCEPTION"}, justification = "Exception should not be rethrown but trigger a fallback.")
    public static ClassFileVersion ofThisVm(ClassFileVersion classFileVersion) {
        try {
            return ofThisVm();
        } catch (Exception unused) {
            return classFileVersion;
        }
    }

    public static ClassFileVersion of(Class<?> cls) {
        return of(cls, ClassFileLocator.ForClassLoader.of(cls.getClassLoader()));
    }

    public static ClassFileVersion of(Class<?> cls, ClassFileLocator classFileLocator) {
        return of(TypeDescription.ForLoadedType.of(cls), classFileLocator);
    }

    public static ClassFileVersion of(TypeDescription typeDescription, ClassFileLocator classFileLocator) {
        return ofClassFile(classFileLocator.locate(typeDescription.getName()).resolve());
    }

    public static ClassFileVersion ofClassFile(byte[] bArr) {
        if (bArr.length < 7) {
            throw new IllegalArgumentException("Supplied byte array is too short to be a class file with " + bArr.length + " byte");
        }
        return ofMinorMajor((bArr[6] << 8) | (bArr[7] & 255));
    }

    public int getMinorMajorVersion() {
        return this.versionNumber;
    }

    public short getMajorVersion() {
        return (short) (this.versionNumber & 255);
    }

    public short getMinorVersion() {
        return (short) (this.versionNumber >> 16);
    }

    public int getJavaVersion() {
        return getMajorVersion() - 44;
    }

    public boolean isAtLeast(ClassFileVersion classFileVersion) {
        return compareTo(classFileVersion) >= 0;
    }

    public boolean isGreaterThan(ClassFileVersion classFileVersion) {
        return compareTo(classFileVersion) > 0;
    }

    public boolean isAtMost(ClassFileVersion classFileVersion) {
        return compareTo(classFileVersion) <= 0;
    }

    public boolean isLessThan(ClassFileVersion classFileVersion) {
        return compareTo(classFileVersion) < 0;
    }

    public ClassFileVersion asPreviewVersion() {
        return new ClassFileVersion(this.versionNumber | (-65536));
    }

    public boolean isPreviewVersion() {
        return (this.versionNumber & (-65536)) == -65536;
    }

    @Override // java.lang.Comparable
    public int compareTo(ClassFileVersion classFileVersion) {
        int majorVersion;
        if (getMajorVersion() == classFileVersion.getMajorVersion()) {
            majorVersion = getMinorVersion() - classFileVersion.getMinorVersion();
        } else {
            majorVersion = getMajorVersion() - classFileVersion.getMajorVersion();
        }
        return Integer.signum(majorVersion);
    }

    public String toString() {
        return "Java " + getJavaVersion() + " (" + getMinorMajorVersion() + ")";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: infinitode-2.jar:net/bytebuddy/ClassFileVersion$VersionLocator.class */
    public interface VersionLocator {
        public static final String EARLY_ACCESS = "-ea";
        public static final String JAVA_VERSION = "java.version";

        ClassFileVersion resolve();

        /* loaded from: infinitode-2.jar:net/bytebuddy/ClassFileVersion$VersionLocator$Resolver.class */
        public enum Resolver implements PrivilegedAction<VersionLocator> {
            INSTANCE;

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.security.PrivilegedAction
            @SuppressFBWarnings(value = {"REC_CATCH_EXCEPTION"}, justification = "Exception should not be rethrown but trigger a fallback.")
            public final VersionLocator run() {
                Method method;
                try {
                    Class<?> cls = Class.forName(Runtime.class.getName() + "$Version");
                    try {
                        method = cls.getMethod("feature", new Class[0]);
                    } catch (NoSuchMethodException unused) {
                        method = cls.getMethod("major", new Class[0]);
                    }
                    return new Resolved(ClassFileVersion.ofJavaVersion(((Integer) method.invoke(Runtime.class.getMethod("version", new Class[0]).invoke(null, new Object[0]), new Object[0])).intValue()));
                } catch (Throwable unused2) {
                    try {
                        String property = System.getProperty(VersionLocator.JAVA_VERSION);
                        String str = property;
                        if (property == null) {
                            throw new IllegalStateException("Java version property is not set");
                        }
                        if (str.equals("0")) {
                            return new Resolved(ClassFileVersion.JAVA_V6);
                        }
                        if (str.endsWith(VersionLocator.EARLY_ACCESS)) {
                            str = str.substring(0, str.length() - 3);
                        }
                        int[] iArr = new int[3];
                        iArr[0] = -1;
                        iArr[1] = 0;
                        iArr[2] = 0;
                        for (int i = 1; i < 3; i++) {
                            iArr[i] = str.indexOf(46, iArr[i - 1] + 1);
                            if (iArr[i] == -1) {
                                throw new IllegalStateException("This JVM's version string does not seem to be valid: " + str);
                            }
                        }
                        return new Resolved(ClassFileVersion.ofJavaVersion(Integer.parseInt(str.substring(iArr[1] + 1, iArr[2]))));
                    } catch (Throwable th) {
                        return new Unresolved(th.getMessage());
                    }
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/ClassFileVersion$VersionLocator$Resolved.class */
        public static class Resolved implements VersionLocator {
            private final ClassFileVersion classFileVersion;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.classFileVersion.equals(((Resolved) obj).classFileVersion);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.classFileVersion.hashCode();
            }

            protected Resolved(ClassFileVersion classFileVersion) {
                this.classFileVersion = classFileVersion;
            }

            @Override // net.bytebuddy.ClassFileVersion.VersionLocator
            public ClassFileVersion resolve() {
                return this.classFileVersion;
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/ClassFileVersion$VersionLocator$Unresolved.class */
        public static class Unresolved implements VersionLocator {
            private final String message;

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.message.equals(((Unresolved) obj).message);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.message.hashCode();
            }

            protected Unresolved(String str) {
                this.message = str;
            }

            @Override // net.bytebuddy.ClassFileVersion.VersionLocator
            public ClassFileVersion resolve() {
                throw new IllegalStateException("Failed to resolve the class file version of the current VM: " + this.message);
            }
        }
    }
}
