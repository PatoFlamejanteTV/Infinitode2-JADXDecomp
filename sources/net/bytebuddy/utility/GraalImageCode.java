package net.bytebuddy.utility;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import net.bytebuddy.build.AccessControllerPlugin;
import net.bytebuddy.utility.nullability.MaybeNull;
import net.bytebuddy.utility.privilege.GetSystemPropertyAction;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unexpected branching in enum static init block */
/* loaded from: infinitode-2.jar:net/bytebuddy/utility/GraalImageCode.class */
public final class GraalImageCode {
    public static final GraalImageCode AGENT;
    public static final GraalImageCode BUILD;
    public static final GraalImageCode RUNTIME;
    public static final GraalImageCode UNKNOWN;
    public static final GraalImageCode NONE;

    @MaybeNull
    private static GraalImageCode current;
    private final boolean defined;
    private final boolean nativeImageExecution;
    private static final /* synthetic */ GraalImageCode[] $VALUES;
    private static final boolean ACCESS_CONTROLLER;

    public static GraalImageCode[] values() {
        return (GraalImageCode[]) $VALUES.clone();
    }

    public static GraalImageCode valueOf(String str) {
        return (GraalImageCode) Enum.valueOf(GraalImageCode.class, str);
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
        AGENT = new GraalImageCode("AGENT", 0, true, false);
        BUILD = new GraalImageCode("BUILD", 1, true, false);
        RUNTIME = new GraalImageCode("RUNTIME", 2, true, true);
        UNKNOWN = new GraalImageCode("UNKNOWN", 3, false, false);
        NONE = new GraalImageCode("NONE", 4, false, false);
        $VALUES = new GraalImageCode[]{AGENT, BUILD, RUNTIME, UNKNOWN, NONE};
    }

    @SuppressFBWarnings(value = {"LI_LAZY_INIT_STATIC", "NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"}, justification = "This behaviour is intended to avoid early binding in native images.")
    public static GraalImageCode getCurrent() {
        GraalImageCode graalImageCode = current;
        GraalImageCode graalImageCode2 = graalImageCode;
        if (graalImageCode == null) {
            String str = (String) doPrivileged(new GetSystemPropertyAction("org.graalvm.nativeimage.imagecode"));
            if (str != null) {
                if (str.equalsIgnoreCase("agent")) {
                    graalImageCode2 = AGENT;
                } else if (str.equalsIgnoreCase("runtime")) {
                    graalImageCode2 = RUNTIME;
                } else if (str.equalsIgnoreCase("buildtime")) {
                    graalImageCode2 = BUILD;
                } else {
                    graalImageCode2 = UNKNOWN;
                }
            } else {
                String str2 = (String) doPrivileged(new GetSystemPropertyAction("java.vm.vendor"));
                graalImageCode2 = (str2 == null || !str2.toLowerCase(Locale.US).contains("graalvm")) ? NONE : (GraalImageCode) doPrivileged(ImageCodeContextAction.INSTANCE);
            }
            current = graalImageCode2;
        }
        return graalImageCode2;
    }

    public final <T> T[] sorted(T[] tArr, Comparator<? super T> comparator) {
        if (this.defined) {
            Arrays.sort(tArr, comparator);
        }
        return tArr;
    }

    @MaybeNull
    @AccessControllerPlugin.Enhance
    private static <T> T doPrivileged(PrivilegedAction<T> privilegedAction) {
        return ACCESS_CONTROLLER ? (T) AccessController.doPrivileged(privilegedAction) : privilegedAction.run();
    }

    private GraalImageCode(String str, int i, boolean z, boolean z2) {
        this.defined = z;
        this.nativeImageExecution = z2;
    }

    public final boolean isDefined() {
        return this.defined;
    }

    public final boolean isNativeImageExecution() {
        return this.nativeImageExecution;
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/utility/GraalImageCode$ImageCodeContextAction.class */
    protected enum ImageCodeContextAction implements PrivilegedAction<GraalImageCode> {
        INSTANCE;

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.security.PrivilegedAction
        public final GraalImageCode run() {
            try {
                Method method = Class.forName("java.lang.management.ManagementFactory").getMethod("getRuntimeMXBean", new Class[0]);
                Iterator it = ((List) method.getReturnType().getMethod("getInputArguments", new Class[0]).invoke(method.invoke(null, new Object[0]), new Object[0])).iterator();
                while (it.hasNext()) {
                    if (((String) it.next()).startsWith("-agentlib:native-image-agent")) {
                        return GraalImageCode.AGENT;
                    }
                }
            } catch (Throwable unused) {
            }
            return GraalImageCode.NONE;
        }
    }
}
