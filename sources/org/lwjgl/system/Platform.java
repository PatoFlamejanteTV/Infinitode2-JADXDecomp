package org.lwjgl.system;

import com.badlogic.gdx.pay.PurchaseManagerConfig;
import java.util.function.Function;
import java.util.regex.Pattern;

/* loaded from: infinitode-2.jar:org/lwjgl/system/Platform.class */
public enum Platform {
    FREEBSD("FreeBSD", "freebsd") { // from class: org.lwjgl.system.Platform.1
        private final Pattern SO = Pattern.compile("(?:^|/)lib\\w+[.]so(?:[.]\\d+)*$");

        @Override // org.lwjgl.system.Platform
        final String mapLibraryName(String str) {
            if (this.SO.matcher(str).find()) {
                return str;
            }
            return System.mapLibraryName(str);
        }
    },
    LINUX("Linux", "linux") { // from class: org.lwjgl.system.Platform.2
        private final Pattern SO = Pattern.compile("(?:^|/)lib\\w+[.]so(?:[.]\\d+)*$");

        @Override // org.lwjgl.system.Platform
        final String mapLibraryName(String str) {
            if (this.SO.matcher(str).find()) {
                return str;
            }
            return System.mapLibraryName(str);
        }
    },
    MACOSX("macOS", "macos") { // from class: org.lwjgl.system.Platform.3
        private final Pattern DYLIB = Pattern.compile("(?:^|/)lib\\w+(?:[.]\\d+)*[.]dylib$");

        @Override // org.lwjgl.system.Platform
        final String mapLibraryName(String str) {
            if (this.DYLIB.matcher(str).find()) {
                return str;
            }
            return System.mapLibraryName(str);
        }
    },
    WINDOWS(PurchaseManagerConfig.STORE_NAME_DESKTOP_WINDOWS, "windows") { // from class: org.lwjgl.system.Platform.4
        @Override // org.lwjgl.system.Platform
        final String mapLibraryName(String str) {
            if (str.endsWith(".dll")) {
                return str;
            }
            return System.mapLibraryName(str);
        }
    };

    private static final Platform current;
    private static final Function<String, String> bundledLibraryNameMapper;
    private static final Function<String, String> bundledLibraryPathMapper;
    private final String name;
    private final String nativePath;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract String mapLibraryName(String str);

    static {
        String property = System.getProperty("os.name");
        if (property.startsWith(PurchaseManagerConfig.STORE_NAME_DESKTOP_WINDOWS)) {
            current = WINDOWS;
        } else if (property.startsWith("FreeBSD")) {
            current = FREEBSD;
        } else if (property.startsWith("Linux") || property.startsWith("SunOS") || property.startsWith("Unix")) {
            current = LINUX;
        } else if (property.startsWith("Mac OS X") || property.startsWith("Darwin")) {
            current = MACOSX;
        } else {
            throw new LinkageError("Unknown platform: " + property);
        }
        bundledLibraryNameMapper = getMapper(Configuration.BUNDLED_LIBRARY_NAME_MAPPER.get("default"), str -> {
            return str;
        }, str2 -> {
            return Architecture.current.is64Bit ? str2 : str2 + "32";
        });
        bundledLibraryPathMapper = getMapper(Configuration.BUNDLED_LIBRARY_PATH_MAPPER.get("default"), str3 -> {
            return current.nativePath + "/" + Architecture.current.name().toLowerCase() + "/" + str3;
        }, str4 -> {
            return str4.substring(str4.lastIndexOf(47));
        });
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/Platform$Architecture.class */
    public enum Architecture {
        X64(true),
        X86(false),
        ARM64(true),
        ARM32(false),
        PPC64LE(true),
        RISCV64(true);

        static final Architecture current;
        final boolean is64Bit;

        static {
            Architecture architecture;
            String property = System.getProperty("os.arch");
            boolean z = property.contains("64") || property.startsWith("armv8");
            if (property.startsWith("arm") || property.startsWith("aarch")) {
                current = z ? ARM64 : ARM32;
                return;
            }
            if (property.startsWith("ppc")) {
                if (!"ppc64le".equals(property)) {
                    throw new UnsupportedOperationException("Only PowerPC 64 LE is supported.");
                }
                architecture = PPC64LE;
            } else if (property.startsWith("riscv")) {
                if (!"riscv64".equals(property)) {
                    throw new UnsupportedOperationException("Only RISC-V 64 is supported.");
                }
                architecture = RISCV64;
            } else {
                architecture = z ? X64 : X86;
            }
            current = architecture;
        }

        Architecture(boolean z) {
            this.is64Bit = z;
        }
    }

    Platform(String str, String str2) {
        this.name = str;
        this.nativePath = str2;
    }

    public String getName() {
        return this.name;
    }

    public static Platform get() {
        return current;
    }

    public static Architecture getArchitecture() {
        return Architecture.current;
    }

    public static String mapLibraryNameBundled(String str) {
        return bundledLibraryNameMapper.apply(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String mapLibraryPathBundled(String str) {
        return bundledLibraryPathMapper.apply(str);
    }

    private static Function<String, String> getMapper(Object obj, Function<String, String> function, Function<String, String> function2) {
        if (obj == null || "default".equals(obj)) {
            return function;
        }
        if ("legacy".equals(obj)) {
            return function2;
        }
        if (obj instanceof Function) {
            return (Function) obj;
        }
        String obj2 = obj.toString();
        try {
            return (Function) Class.forName(obj2).getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Throwable th) {
            if (Checks.DEBUG) {
                th.printStackTrace(APIUtil.DEBUG_STREAM);
            }
            APIUtil.apiLog(String.format("Warning: Failed to instantiate bundled library mapper: %s. Using the default.", obj2));
            return function;
        }
    }
}
