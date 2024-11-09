package org.lwjgl.openal;

import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.function.IntFunction;
import org.lwjgl.BufferUtils;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.APIUtil;
import org.lwjgl.system.Checks;
import org.lwjgl.system.Configuration;
import org.lwjgl.system.FunctionProvider;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.ThreadLocalUtil;

/* loaded from: infinitode-2.jar:org/lwjgl/openal/AL.class */
public final class AL {
    private static ALCapabilities processCaps;
    private static final ThreadLocal<ALCapabilities> capabilitiesTLS = new ThreadLocal<>();
    private static ICD icd = new ICDStatic();

    private AL() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void init() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void destroy() {
        setCurrentProcess(null);
    }

    public static void setCurrentProcess(ALCapabilities aLCapabilities) {
        processCaps = aLCapabilities;
        capabilitiesTLS.set(null);
        icd.set(aLCapabilities);
    }

    public static void setCurrentThread(ALCapabilities aLCapabilities) {
        capabilitiesTLS.set(aLCapabilities);
        icd.set(aLCapabilities);
    }

    public static ALCapabilities getCapabilities() {
        ALCapabilities aLCapabilities = capabilitiesTLS.get();
        ALCapabilities aLCapabilities2 = aLCapabilities;
        if (aLCapabilities == null) {
            aLCapabilities2 = processCaps;
        }
        return checkCapabilities(aLCapabilities2);
    }

    private static ALCapabilities checkCapabilities(ALCapabilities aLCapabilities) {
        if (aLCapabilities == null) {
            throw new IllegalStateException("No ALCapabilities instance set for the current thread or process. Possible solutions:\n\ta) Call AL.createCapabilities() after making a context current.\n\tb) Call AL.setCurrentProcess() or AL.setCurrentThread() if an ALCapabilities instance already exists.");
        }
        return aLCapabilities;
    }

    public static ALCapabilities createCapabilities(ALCCapabilities aLCCapabilities) {
        return createCapabilities(aLCCapabilities, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v30, types: [int[]] */
    /* JADX WARN: Type inference failed for: r0v79 */
    /* JADX WARN: Type inference failed for: r0v84, types: [int] */
    /* JADX WARN: Type inference failed for: r1v39, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r24v0, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r24v1 */
    /* JADX WARN: Type inference failed for: r24v2 */
    public static ALCapabilities createCapabilities(ALCCapabilities aLCCapabilities, IntFunction<PointerBuffer> intFunction) {
        int i;
        long functionAddress = ALC.getFunctionProvider().getFunctionAddress(0L, "alGetProcAddress");
        if ("alGetProcAddress" == 0) {
            throw new RuntimeException("A core AL function is missing. Make sure that the OpenAL library has been loaded correctly.");
        }
        FunctionProvider functionProvider = byteBuffer -> {
            long invokePP = JNI.invokePP(MemoryUtil.memAddress(byteBuffer), functionAddress);
            if (invokePP == 0 && Checks.DEBUG_FUNCTIONS) {
                APIUtil.apiLogMissing("AL", byteBuffer);
            }
            return invokePP;
        };
        long functionAddress2 = functionProvider.getFunctionAddress("alGetString");
        long functionAddress3 = functionProvider.getFunctionAddress("alGetError");
        long functionAddress4 = functionProvider.getFunctionAddress("alIsExtensionPresent");
        if (functionAddress2 == 0 || functionAddress3 == 0 || functionAddress4 == 0) {
            throw new IllegalStateException("Core OpenAL functions could not be found. Make sure that the OpenAL library has been loaded correctly.");
        }
        String memASCIISafe = MemoryUtil.memASCIISafe(JNI.invokeP(AL10.AL_VERSION, functionAddress2));
        if (memASCIISafe == null || JNI.invokeI(functionAddress3) != 0) {
            throw new IllegalStateException("There is no OpenAL context current in the current thread or process.");
        }
        APIUtil.APIVersion apiParseVersion = APIUtil.apiParseVersion(memASCIISafe);
        int i2 = apiParseVersion.major;
        int i3 = apiParseVersion.minor;
        ?? r0 = {new int[]{0, 1}};
        HashSet hashSet = new HashSet(32);
        for (int i4 = 1; i4 <= 1; i4++) {
            for (?? r02 : r0[i4 - 1]) {
                if (i4 < i2 || (i4 == i2 && r02 <= i3)) {
                    hashSet.add("OpenAL" + i4 + r02);
                }
            }
        }
        String memASCIISafe2 = MemoryUtil.memASCIISafe(JNI.invokeP(AL10.AL_EXTENSIONS, functionAddress2));
        if (memASCIISafe2 != null) {
            MemoryStack stackGet = MemoryStack.stackGet();
            StringTokenizer stringTokenizer = new StringTokenizer(memASCIISafe2);
            while (stringTokenizer.hasMoreTokens()) {
                String nextToken = stringTokenizer.nextToken();
                MemoryStack push = stackGet.push();
                i = false;
                try {
                    try {
                        i = JNI.invokePZ(MemoryUtil.memAddress(push.ASCII(nextToken, true)), functionAddress4);
                        if (i) {
                            hashSet.add(nextToken);
                        }
                        if (push != null) {
                            if (r24 != 0) {
                                try {
                                    push.close();
                                } catch (Throwable th) {
                                    r24.addSuppressed(th);
                                }
                            } else {
                                push.close();
                            }
                        }
                    } finally {
                        r24 = i;
                    }
                } catch (Throwable th2) {
                    if (push != null) {
                        if (r24) {
                            try {
                                push.close();
                            } catch (Throwable th3) {
                                r24.addSuppressed(th3);
                            }
                        } else {
                            push.close();
                        }
                    }
                    throw th2;
                }
            }
        }
        if (aLCCapabilities.ALC_EXT_EFX) {
            hashSet.add("ALC_EXT_EFX");
        }
        APIUtil.apiFilterExtensions(hashSet, Configuration.OPENAL_EXTENSION_FILTER);
        ALCapabilities aLCapabilities = new ALCapabilities(functionProvider, hashSet, intFunction == null ? BufferUtils::createPointerBuffer : intFunction);
        if (aLCCapabilities.ALC_EXT_thread_local_context && EXTThreadLocalContext.alcGetThreadContext() != 0) {
            setCurrentThread(aLCapabilities);
        } else {
            setCurrentProcess(aLCapabilities);
        }
        return aLCapabilities;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ALCapabilities getICD() {
        return (ALCapabilities) ALC.check(icd.get());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/openal/AL$ICD.class */
    public interface ICD {
        ALCapabilities get();

        default void set(ALCapabilities aLCapabilities) {
        }
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/openal/AL$ICDStatic.class */
    private static class ICDStatic implements ICD {
        private static ALCapabilities tempCaps;

        private ICDStatic() {
        }

        @Override // org.lwjgl.openal.AL.ICD
        public void set(ALCapabilities aLCapabilities) {
            if (tempCaps == null) {
                tempCaps = aLCapabilities;
            } else if (aLCapabilities != null && aLCapabilities != tempCaps && ThreadLocalUtil.areCapabilitiesDifferent(tempCaps.addresses, aLCapabilities.addresses)) {
                APIUtil.apiLog("[WARNING] Incompatible context detected. Falling back to thread/process lookup for AL contexts.");
                ICD unused = AL.icd = AL::getCapabilities;
            }
        }

        @Override // org.lwjgl.openal.AL.ICD
        public ALCapabilities get() {
            return WriteOnce.caps;
        }

        /* loaded from: infinitode-2.jar:org/lwjgl/openal/AL$ICDStatic$WriteOnce.class */
        private static final class WriteOnce {
            static final ALCapabilities caps;

            private WriteOnce() {
            }

            static {
                ALCapabilities aLCapabilities = ICDStatic.tempCaps;
                if (aLCapabilities == null) {
                    throw new IllegalStateException("No ALCapabilities instance has been set");
                }
                caps = aLCapabilities;
            }
        }
    }
}
