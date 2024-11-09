package org.lwjgl.openal;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.function.IntFunction;
import org.lwjgl.BufferUtils;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.APIUtil;
import org.lwjgl.system.Checks;
import org.lwjgl.system.Configuration;
import org.lwjgl.system.FunctionProviderLocal;
import org.lwjgl.system.JNI;
import org.lwjgl.system.Library;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.Platform;
import org.lwjgl.system.SharedLibrary;
import org.lwjgl.system.ThreadLocalUtil;

/* loaded from: infinitode-2.jar:org/lwjgl/openal/ALC.class */
public final class ALC {
    private static FunctionProviderLocal functionProvider;
    private static ALCCapabilities router;
    private static final ThreadLocal<ALCCapabilities> capabilitiesTLS = new ThreadLocal<>();
    private static ICD icd;

    static {
        if (!Configuration.OPENAL_EXPLICIT_INIT.get(Boolean.FALSE).booleanValue()) {
            create();
        }
    }

    private ALC() {
    }

    public static void create() {
        String str;
        switch (Platform.get()) {
            case FREEBSD:
            case LINUX:
            case MACOSX:
                str = "openal";
                break;
            case WINDOWS:
                str = "OpenAL";
                break;
            default:
                throw new IllegalStateException();
        }
        create(Configuration.OPENAL_LIBRARY_NAME.get(Platform.mapLibraryNameBundled(str)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/openal/ALC$SharedLibraryAL.class */
    public static class SharedLibraryAL extends SharedLibrary.Delegate implements FunctionProviderLocal {
        private final long alcGetProcAddress;

        protected SharedLibraryAL(SharedLibrary sharedLibrary) {
            super(sharedLibrary);
            this.alcGetProcAddress = getFunctionAddress("alcGetProcAddress");
            if (this.alcGetProcAddress == 0) {
                throw new RuntimeException("A core ALC function is missing. Make sure that the OpenAL library has been loaded correctly.");
            }
        }

        @Override // org.lwjgl.system.FunctionProvider
        public long getFunctionAddress(ByteBuffer byteBuffer) {
            long functionAddress = this.library.getFunctionAddress(byteBuffer);
            if (functionAddress == 0 && Checks.DEBUG_FUNCTIONS) {
                APIUtil.apiLogMissing("ALC core", byteBuffer);
            }
            return functionAddress;
        }

        @Override // org.lwjgl.system.FunctionProviderLocal
        public long getFunctionAddress(long j, ByteBuffer byteBuffer) {
            long functionAddress = this.library.getFunctionAddress(byteBuffer);
            long j2 = functionAddress;
            if (functionAddress == 0 && j != 0) {
                j2 = JNI.invokePPP(j, MemoryUtil.memAddress(byteBuffer), this.alcGetProcAddress);
            }
            if (j2 == 0 && Checks.DEBUG_FUNCTIONS) {
                APIUtil.apiLogMissing("ALC", byteBuffer);
            }
            return j2;
        }
    }

    public static void create(String str) {
        SharedLibrary loadNative = Library.loadNative((Class<?>) ALC.class, "org.lwjgl.openal", str, true);
        try {
            create(new SharedLibraryAL(loadNative));
        } catch (RuntimeException e) {
            loadNative.free();
            throw e;
        }
    }

    public static void create(FunctionProviderLocal functionProviderLocal) {
        if (functionProvider != null) {
            throw new IllegalStateException("ALC has already been created.");
        }
        functionProvider = functionProviderLocal;
        router = createCapabilities(0L);
        AL.init();
    }

    public static void destroy() {
        if (functionProvider == null) {
            return;
        }
        AL.destroy();
        router = null;
        if (functionProvider instanceof NativeResource) {
            ((NativeResource) functionProvider).free();
        }
        functionProvider = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> T check(T t) {
        if (t == null) {
            throw new IllegalStateException("OpenAL library has not been loaded.");
        }
        return t;
    }

    public static FunctionProviderLocal getFunctionProvider() {
        return (FunctionProviderLocal) check(functionProvider);
    }

    public static void setCapabilities(ALCCapabilities aLCCapabilities) {
        capabilitiesTLS.set(aLCCapabilities);
        if (icd == null) {
            icd = new ICDStatic();
        }
        icd.set(aLCCapabilities);
    }

    public static ALCCapabilities getCapabilities() {
        ALCCapabilities aLCCapabilities = capabilitiesTLS.get();
        ALCCapabilities aLCCapabilities2 = aLCCapabilities;
        if (aLCCapabilities == null) {
            aLCCapabilities2 = router;
        }
        return checkCapabilities(aLCCapabilities2);
    }

    private static ALCCapabilities checkCapabilities(ALCCapabilities aLCCapabilities) {
        if (aLCCapabilities == null) {
            throw new IllegalStateException("No ALCCapabilities instance set");
        }
        return aLCCapabilities;
    }

    public static ALCCapabilities createCapabilities(long j) {
        return createCapabilities(j, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v33, types: [int[]] */
    /* JADX WARN: Type inference failed for: r0v71 */
    /* JADX WARN: Type inference failed for: r0v76, types: [int] */
    /* JADX WARN: Type inference failed for: r1v39, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r25v0, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r25v1 */
    /* JADX WARN: Type inference failed for: r25v2 */
    public static ALCCapabilities createCapabilities(long j, IntFunction<PointerBuffer> intFunction) {
        int i;
        FunctionProviderLocal functionProvider2 = getFunctionProvider();
        long functionAddress = functionProvider2.getFunctionAddress("alcGetIntegerv");
        long functionAddress2 = functionProvider2.getFunctionAddress("alcGetString");
        long functionAddress3 = functionProvider2.getFunctionAddress("alcIsExtensionPresent");
        if (functionAddress == 0 || functionAddress2 == 0 || functionAddress3 == 0) {
            throw new IllegalStateException("Core ALC functions could not be found. Make sure that OpenAL has been loaded.");
        }
        MemoryStack stackPush = MemoryStack.stackPush();
        try {
            try {
                IntBuffer mallocInt = stackPush.mallocInt(1);
                JNI.invokePPV(j, 4096, 1, MemoryUtil.memAddress(mallocInt), functionAddress);
                int i2 = mallocInt.get(0);
                JNI.invokePPV(j, 4097, 1, MemoryUtil.memAddress(mallocInt), functionAddress);
                int i3 = mallocInt.get(0);
                if (stackPush != null) {
                    if (r21 != null) {
                        try {
                            stackPush.close();
                        } catch (Throwable th) {
                            r21.addSuppressed(th);
                        }
                    } else {
                        stackPush.close();
                    }
                }
                ?? r0 = {new int[]{0, 1}};
                HashSet hashSet = new HashSet(16);
                for (int i4 = 1; i4 <= 1; i4++) {
                    for (?? r02 : r0[i4 - 1]) {
                        if (i4 < i2 || (i4 == i2 && r02 <= i3)) {
                            hashSet.add("OpenALC" + i4 + r02);
                        }
                    }
                }
                String memASCIISafe = MemoryUtil.memASCIISafe(JNI.invokePP(j, 4102, functionAddress2));
                if (memASCIISafe != null) {
                    StringTokenizer stringTokenizer = new StringTokenizer(memASCIISafe);
                    while (stringTokenizer.hasMoreTokens()) {
                        String nextToken = stringTokenizer.nextToken();
                        stackPush = MemoryStack.stackPush();
                        i = false;
                        try {
                            try {
                                i = JNI.invokePPZ(j, MemoryUtil.memAddress(stackPush.ASCII(nextToken, true)), functionAddress3);
                                if (i) {
                                    hashSet.add(nextToken);
                                }
                                if (stackPush != null) {
                                    if (r25 != 0) {
                                        try {
                                            stackPush.close();
                                        } catch (Throwable th2) {
                                            r25.addSuppressed(th2);
                                        }
                                    } else {
                                        stackPush.close();
                                    }
                                }
                            } finally {
                            }
                        } finally {
                            r25 = i;
                        }
                    }
                }
                APIUtil.apiFilterExtensions(hashSet, Configuration.OPENAL_EXTENSION_FILTER);
                ALCCapabilities aLCCapabilities = new ALCCapabilities(functionProvider2, j, hashSet, intFunction == null ? BufferUtils::createPointerBuffer : intFunction);
                if (j != 0) {
                    setCapabilities(aLCCapabilities);
                }
                return aLCCapabilities;
            } finally {
            }
        } finally {
            r21 = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ALCCapabilities getICD() {
        ALCCapabilities aLCCapabilities = icd == null ? null : icd.get();
        ALCCapabilities aLCCapabilities2 = aLCCapabilities;
        if (aLCCapabilities == null) {
            aLCCapabilities2 = router;
        }
        return (ALCCapabilities) check(aLCCapabilities2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/openal/ALC$ICD.class */
    public interface ICD {
        ALCCapabilities get();

        default void set(ALCCapabilities aLCCapabilities) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/openal/ALC$ICDStatic.class */
    public static class ICDStatic implements ICD {
        private static ALCCapabilities tempCaps;

        private ICDStatic() {
        }

        @Override // org.lwjgl.openal.ALC.ICD
        public void set(ALCCapabilities aLCCapabilities) {
            if (tempCaps == null) {
                tempCaps = aLCCapabilities;
            } else if (aLCCapabilities != null && aLCCapabilities != tempCaps && ThreadLocalUtil.areCapabilitiesDifferent(tempCaps.addresses, aLCCapabilities.addresses)) {
                APIUtil.apiLog("[WARNING] Incompatible context detected. Falling back to thread/process lookup for AL contexts.");
                ICD unused = ALC.icd = ALC::getCapabilities;
            }
        }

        @Override // org.lwjgl.openal.ALC.ICD
        public ALCCapabilities get() {
            return WriteOnce.caps;
        }

        /* loaded from: infinitode-2.jar:org/lwjgl/openal/ALC$ICDStatic$WriteOnce.class */
        private static final class WriteOnce {
            static final ALCCapabilities caps;

            private WriteOnce() {
            }

            static {
                ALCCapabilities aLCCapabilities = ICDStatic.tempCaps;
                if (aLCCapabilities == null) {
                    throw new IllegalStateException("No ALCCapabilities instance has been set");
                }
                caps = aLCCapabilities;
            }
        }
    }
}
