package org.lwjgl.system;

import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.freebsd.FreeBSDLibrary;
import org.lwjgl.system.libffi.FFICIF;
import org.lwjgl.system.libffi.FFIType;
import org.lwjgl.system.libffi.LibFFI;
import org.lwjgl.system.linux.LinuxLibrary;
import org.lwjgl.system.macosx.MacOSXLibrary;
import org.lwjgl.system.windows.User32;
import org.lwjgl.system.windows.WindowsLibrary;

/* loaded from: infinitode-2.jar:org/lwjgl/system/APIUtil.class */
public final class APIUtil {
    public static final PrintStream DEBUG_STREAM = getDebugStream();
    private static final Pattern API_VERSION_PATTERN = Pattern.compile("^[^\\d\\n\\r]*(\\d+)[.](\\d+)(?:[.](\\S+))?(?:\\s+(.+?))?\\s*$", 32);

    /* loaded from: infinitode-2.jar:org/lwjgl/system/APIUtil$Encoder.class */
    public interface Encoder {
        ByteBuffer encode(CharSequence charSequence, boolean z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v12, types: [java.io.PrintStream] */
    /* JADX WARN: Type inference failed for: r0v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Exception] */
    private static PrintStream getDebugStream() {
        PrintStream printStream = System.err;
        Object obj = Configuration.DEBUG_STREAM.get();
        ?? r0 = obj instanceof String;
        if (r0 != 0) {
            try {
                r0 = (PrintStream) ((Supplier) Class.forName((String) obj).getConstructor(new Class[0]).newInstance(new Object[0])).get();
                printStream = r0;
            } catch (Exception e) {
                r0.printStackTrace();
            }
        } else if (obj instanceof Supplier) {
            printStream = (PrintStream) ((Supplier) obj).get();
        } else if (obj instanceof PrintStream) {
            printStream = (PrintStream) obj;
        }
        return printStream;
    }

    private APIUtil() {
    }

    public static void apiLog(CharSequence charSequence) {
        if (Checks.DEBUG) {
            DEBUG_STREAM.print("[LWJGL] " + ((Object) charSequence) + SequenceUtils.EOL);
        }
    }

    public static void apiLogMore(CharSequence charSequence) {
        if (Checks.DEBUG) {
            DEBUG_STREAM.print("\t" + ((Object) charSequence) + SequenceUtils.EOL);
        }
    }

    public static void apiLogMissing(String str, ByteBuffer byteBuffer) {
        if (Checks.DEBUG) {
            DEBUG_STREAM.print("[LWJGL] Failed to locate address for " + str + " function " + MemoryUtil.memASCII(byteBuffer, byteBuffer.remaining() - 1) + SequenceUtils.EOL);
        }
    }

    public static String apiFindLibrary(String str, String str2) {
        String mapLibraryName = Platform.get().mapLibraryName(str2);
        try {
            Stream<Path> find = Files.find(Paths.get(str, new String[0]).toAbsolutePath(), Integer.MAX_VALUE, (path, basicFileAttributes) -> {
                return basicFileAttributes.isRegularFile() && path.getFileName().toString().equals(mapLibraryName);
            }, new FileVisitOption[0]);
            Throwable th = null;
            try {
                String str3 = (String) find.findFirst().map((v0) -> {
                    return v0.toString();
                }).orElse(str2);
                if (find != null) {
                    if (0 != 0) {
                        try {
                            find.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    } else {
                        find.close();
                    }
                }
                return str3;
            } finally {
            }
        } catch (IOException unused) {
            return str2;
        }
    }

    public static SharedLibrary apiCreateLibrary(String str) {
        switch (Platform.get()) {
            case FREEBSD:
                return new FreeBSDLibrary(str);
            case LINUX:
                return new LinuxLibrary(str);
            case MACOSX:
                return MacOSXLibrary.create(str);
            case WINDOWS:
                return new WindowsLibrary(str);
            default:
                throw new IllegalStateException();
        }
    }

    public static long apiGetFunctionAddress(FunctionProvider functionProvider, String str) {
        long functionAddress = functionProvider.getFunctionAddress(str);
        if (functionAddress == 0) {
            requiredFunctionMissing(str);
        }
        return functionAddress;
    }

    private static void requiredFunctionMissing(String str) {
        if (!Configuration.DISABLE_FUNCTION_CHECKS.get(Boolean.FALSE).booleanValue()) {
            throw new NullPointerException("A required function is missing: " + str);
        }
    }

    public static long apiGetFunctionAddressOptional(SharedLibrary sharedLibrary, String str) {
        long functionAddress = sharedLibrary.getFunctionAddress(str);
        if (Checks.DEBUG_FUNCTIONS && functionAddress == 0) {
            optionalFunctionMissing(sharedLibrary, str);
        }
        return functionAddress;
    }

    private static void optionalFunctionMissing(SharedLibrary sharedLibrary, String str) {
        if (Checks.DEBUG) {
            DEBUG_STREAM.print("[LWJGL] Failed to locate address for " + sharedLibrary.getName() + " function " + str + SequenceUtils.EOL);
        }
    }

    public static ByteBuffer apiGetMappedBuffer(ByteBuffer byteBuffer, long j, int i) {
        if (byteBuffer != null && MemoryUtil.memAddress(byteBuffer) == j && byteBuffer.capacity() == i) {
            return byteBuffer;
        }
        if (j == 0) {
            return null;
        }
        return ((ByteBuffer) MemoryUtil.wrap(MemoryUtil.BUFFER_BYTE, j, i)).order(MemoryUtil.NATIVE_ORDER);
    }

    public static long apiGetBytes(int i, int i2) {
        return (i & 4294967295L) << i2;
    }

    public static long apiCheckAllocation(int i, long j, long j2) {
        if (Checks.DEBUG) {
            if (i < 0) {
                throw new IllegalArgumentException("Invalid number of elements");
            }
            if (j2 - Long.MIN_VALUE < j - Long.MIN_VALUE) {
                throw new IllegalArgumentException("The request allocation is too large");
            }
        }
        return j;
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/APIUtil$APIVersion.class */
    public static class APIVersion implements Comparable<APIVersion> {
        public final int major;
        public final int minor;
        public final String revision;
        public final String implementation;

        public APIVersion(int i, int i2) {
            this(i, i2, null, null);
        }

        public APIVersion(int i, int i2, String str, String str2) {
            this.major = i;
            this.minor = i2;
            this.revision = str;
            this.implementation = str2;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(16);
            sb.append(this.major).append('.').append(this.minor);
            if (this.revision != null) {
                sb.append('.').append(this.revision);
            }
            if (this.implementation != null) {
                sb.append(" (").append(this.implementation).append(')');
            }
            return sb.toString();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof APIVersion)) {
                return false;
            }
            APIVersion aPIVersion = (APIVersion) obj;
            return this.major == aPIVersion.major && this.minor == aPIVersion.major && Objects.equals(this.revision, aPIVersion.revision) && Objects.equals(this.implementation, aPIVersion.implementation);
        }

        public int hashCode() {
            return (((((this.major * 31) + this.minor) * 31) + (this.revision != null ? this.revision.hashCode() : 0)) * 31) + (this.implementation != null ? this.implementation.hashCode() : 0);
        }

        @Override // java.lang.Comparable
        public int compareTo(APIVersion aPIVersion) {
            if (this.major != aPIVersion.major) {
                return Integer.compare(this.major, aPIVersion.major);
            }
            if (this.minor != aPIVersion.minor) {
                return Integer.compare(this.minor, aPIVersion.minor);
            }
            return 0;
        }
    }

    public static APIVersion apiParseVersion(Configuration<?> configuration) {
        APIVersion aPIVersion;
        Object obj = configuration.get();
        if (obj instanceof String) {
            aPIVersion = apiParseVersion((String) obj);
        } else if (obj instanceof APIVersion) {
            aPIVersion = (APIVersion) obj;
        } else {
            aPIVersion = null;
        }
        return aPIVersion;
    }

    public static APIVersion apiParseVersion(String str) {
        Matcher matcher = API_VERSION_PATTERN.matcher(str);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(String.format("Malformed API version string [%s]", str));
        }
        return new APIVersion(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)), matcher.group(3), matcher.group(4));
    }

    public static void apiFilterExtensions(Set<String> set, Configuration<Object> configuration) {
        Object obj = configuration.get();
        if (obj == null) {
            return;
        }
        if (!(obj instanceof String)) {
            if (!(obj instanceof List)) {
                if (obj instanceof Predicate) {
                    set.removeIf((Predicate) obj);
                    return;
                }
                throw new IllegalStateException("Unsupported " + configuration.getProperty() + " value specified.");
            }
            set.removeAll((List) obj);
            return;
        }
        String str = (String) obj;
        if (str.indexOf(46) != -1) {
            try {
                set.removeIf((Predicate) Class.forName(str).newInstance());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            for (String str2 : str.split(",")) {
                set.remove(str2);
            }
        }
    }

    public static String apiUnknownToken(int i) {
        return apiUnknownToken("Unknown", i);
    }

    public static String apiUnknownToken(String str, int i) {
        return String.format("%s [0x%X]", str, Integer.valueOf(i));
    }

    public static Map<Integer, String> apiClassTokens(BiPredicate<Field, Integer> biPredicate, Map<Integer, String> map, Class<?>... clsArr) {
        if (map == null) {
            map = new HashMap(64);
        }
        for (Class<?> cls : clsArr) {
            if (cls != null) {
                for (Field field : cls.getDeclaredFields()) {
                    if ((field.getModifiers() & 25) == 25 && field.getType() == Integer.TYPE) {
                        try {
                            Integer valueOf = Integer.valueOf(field.getInt(null));
                            if (biPredicate == null || biPredicate.test(field, valueOf)) {
                                String str = map.get(valueOf);
                                map.put(valueOf, str == null ? field.getName() : str + "|" + field.getName());
                            }
                        } catch (IllegalAccessException unused) {
                        }
                    }
                }
            }
        }
        return map;
    }

    public static long apiArray(MemoryStack memoryStack, long... jArr) {
        PointerBuffer memPointerBuffer = MemoryUtil.memPointerBuffer(memoryStack.nmalloc(MemoryStack.POINTER_SIZE, jArr.length << MemoryStack.POINTER_SHIFT), jArr.length);
        for (long j : jArr) {
            memPointerBuffer.put(j);
        }
        return memPointerBuffer.address;
    }

    public static long apiArray(MemoryStack memoryStack, ByteBuffer... byteBufferArr) {
        PointerBuffer memPointerBuffer = MemoryUtil.memPointerBuffer(memoryStack.nmalloc(MemoryStack.POINTER_SIZE, byteBufferArr.length << MemoryStack.POINTER_SHIFT), byteBufferArr.length);
        for (ByteBuffer byteBuffer : byteBufferArr) {
            memPointerBuffer.put(byteBuffer);
        }
        return memPointerBuffer.address;
    }

    public static long apiArrayp(MemoryStack memoryStack, ByteBuffer... byteBufferArr) {
        long apiArray = apiArray(memoryStack, byteBufferArr);
        PointerBuffer mallocPointer = memoryStack.mallocPointer(byteBufferArr.length);
        for (ByteBuffer byteBuffer : byteBufferArr) {
            mallocPointer.put(byteBuffer.remaining());
        }
        return apiArray;
    }

    public static long apiArray(MemoryStack memoryStack, Encoder encoder, CharSequence... charSequenceArr) {
        PointerBuffer mallocPointer = memoryStack.mallocPointer(charSequenceArr.length);
        for (CharSequence charSequence : charSequenceArr) {
            mallocPointer.put(encoder.encode(charSequence, true));
        }
        return mallocPointer.address;
    }

    public static long apiArrayi(MemoryStack memoryStack, Encoder encoder, CharSequence... charSequenceArr) {
        PointerBuffer mallocPointer = memoryStack.mallocPointer(charSequenceArr.length);
        IntBuffer mallocInt = memoryStack.mallocInt(charSequenceArr.length);
        for (CharSequence charSequence : charSequenceArr) {
            ByteBuffer encode = encoder.encode(charSequence, false);
            mallocPointer.put(encode);
            mallocInt.put(encode.capacity());
        }
        return mallocPointer.address;
    }

    public static long apiArrayp(MemoryStack memoryStack, Encoder encoder, CharSequence... charSequenceArr) {
        PointerBuffer mallocPointer = memoryStack.mallocPointer(charSequenceArr.length);
        PointerBuffer mallocPointer2 = memoryStack.mallocPointer(charSequenceArr.length);
        for (CharSequence charSequence : charSequenceArr) {
            mallocPointer.put(encoder.encode(charSequence, false));
            mallocPointer2.put(r0.capacity());
        }
        return mallocPointer.address;
    }

    public static void apiArrayFree(long j, int i) {
        int i2 = i;
        while (true) {
            i2--;
            if (i2 >= 0) {
                MemoryUtil.nmemFree(MemoryUtil.memGetAddress(j + (Integer.toUnsignedLong(i2) * MemoryStack.POINTER_SIZE)));
            } else {
                return;
            }
        }
    }

    public static FFIType apiCreateStruct(FFIType... fFITypeArr) {
        MemoryUtil.MemoryAllocator allocator = MemoryUtil.getAllocator();
        PointerBuffer create = PointerBuffer.create(allocator.malloc((fFITypeArr.length + 1) * MemoryStack.POINTER_SIZE), fFITypeArr.length + 1);
        for (int i = 0; i < fFITypeArr.length; i++) {
            create.put(i, fFITypeArr[i]);
        }
        create.put(fFITypeArr.length, 0L);
        return FFIType.create(allocator.calloc(1L, FFIType.SIZEOF)).type((short) 13).elements(create);
    }

    private static FFIType prep(FFIType fFIType) {
        MemoryStack stackPush = MemoryStack.stackPush();
        IllegalStateException illegalStateException = null;
        try {
            try {
                if (LibFFI.ffi_prep_cif(FFICIF.calloc(stackPush), LibFFI.FFI_DEFAULT_ABI, fFIType, null) != 0) {
                    illegalStateException = new IllegalStateException("Failed to prepare LibFFI type.");
                    throw illegalStateException;
                }
                return fFIType;
            } catch (Throwable th) {
                throw th;
            }
        } finally {
            if (stackPush != null) {
                if (0 != 0) {
                    try {
                        stackPush.close();
                    } catch (Throwable th2) {
                        illegalStateException.addSuppressed(th2);
                    }
                } else {
                    stackPush.close();
                }
            }
        }
    }

    public static FFIType apiCreateUnion(FFIType... fFITypeArr) {
        MemoryUtil.MemoryAllocator allocator = MemoryUtil.getAllocator();
        FFIType prep = prep(fFITypeArr[0]);
        short alignment = fFITypeArr[0].alignment();
        for (int i = 1; i < fFITypeArr.length; i++) {
            FFIType prep2 = prep(fFITypeArr[i]);
            if (prep.size() < prep2.size()) {
                prep = prep2;
            }
            if (alignment < prep2.alignment()) {
                alignment = prep2.alignment();
            }
        }
        return FFIType.create(allocator.malloc(FFIType.SIZEOF)).size(prep.size()).alignment(alignment).type((short) 13).elements(PointerBuffer.create(allocator.malloc(2 * MemoryStack.POINTER_SIZE), 2).put(0, prep).put(1, 0L));
    }

    public static FFIType apiCreateArray(FFIType fFIType, int i) {
        MemoryUtil.MemoryAllocator allocator = MemoryUtil.getAllocator();
        PointerBuffer create = PointerBuffer.create(allocator.malloc((i + 1) * MemoryStack.POINTER_SIZE), i + 1);
        for (int i2 = 0; i2 < i; i2++) {
            create.put(i2, fFIType);
        }
        create.put(i, 0L);
        return FFIType.create(allocator.calloc(1L, FFIType.SIZEOF)).type((short) 13).elements(create);
    }

    public static FFICIF apiCreateCIF(int i, FFIType fFIType, FFIType... fFITypeArr) {
        MemoryUtil.MemoryAllocator allocator = MemoryUtil.getAllocator();
        PointerBuffer create = PointerBuffer.create(allocator.malloc(fFITypeArr.length * MemoryStack.POINTER_SIZE), fFITypeArr.length);
        for (int i2 = 0; i2 < fFITypeArr.length; i2++) {
            create.put(i2, fFITypeArr[i2]);
        }
        FFICIF create2 = FFICIF.create(allocator.malloc(FFICIF.SIZEOF));
        int ffi_prep_cif = LibFFI.ffi_prep_cif(create2, i, fFIType, create);
        if (ffi_prep_cif != 0) {
            throw new IllegalStateException("Failed to prepare libffi CIF: " + ffi_prep_cif);
        }
        return create2;
    }

    public static FFICIF apiCreateCIFVar(int i, int i2, FFIType fFIType, FFIType... fFITypeArr) {
        MemoryUtil.MemoryAllocator allocator = MemoryUtil.getAllocator();
        PointerBuffer create = PointerBuffer.create(allocator.malloc(fFITypeArr.length * MemoryStack.POINTER_SIZE), fFITypeArr.length);
        for (int i3 = 0; i3 < fFITypeArr.length; i3++) {
            create.put(i3, fFITypeArr[i3]);
        }
        FFICIF create2 = FFICIF.create(allocator.malloc(FFICIF.SIZEOF));
        int ffi_prep_cif_var = LibFFI.ffi_prep_cif_var(create2, i, i2, fFIType, create);
        if (ffi_prep_cif_var != 0) {
            throw new IllegalStateException("Failed to prepare libffi var CIF: " + ffi_prep_cif_var);
        }
        return create2;
    }

    public static int apiStdcall() {
        return (Platform.get() == Platform.WINDOWS && Pointer.BITS32) ? LibFFI.FFI_STDCALL : LibFFI.FFI_DEFAULT_ABI;
    }

    public static void apiClosureRet(long j, boolean z) {
        MemoryUtil.memPutAddress(j, z ? 1L : 0L);
    }

    public static void apiClosureRet(long j, byte b2) {
        MemoryUtil.memPutAddress(j, b2 & 255);
    }

    public static void apiClosureRet(long j, short s) {
        MemoryUtil.memPutAddress(j, s & User32.HWND_BROADCAST);
    }

    public static void apiClosureRet(long j, int i) {
        MemoryUtil.memPutAddress(j, i & 4294967295L);
    }

    public static void apiClosureRetL(long j, long j2) {
        MemoryUtil.memPutLong(j, j2);
    }

    public static void apiClosureRetP(long j, long j2) {
        MemoryUtil.memPutAddress(j, j2);
    }

    public static void apiClosureRet(long j, float f) {
        MemoryUtil.memPutFloat(j, f);
    }

    public static void apiClosureRet(long j, double d) {
        MemoryUtil.memPutDouble(j, d);
    }
}
