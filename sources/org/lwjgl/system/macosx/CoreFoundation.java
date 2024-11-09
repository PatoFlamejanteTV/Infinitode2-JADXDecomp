package org.lwjgl.system.macosx;

import java.nio.ByteBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.Library;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/system/macosx/CoreFoundation.class */
public class CoreFoundation {
    public static final byte TRUE = 1;
    public static final byte FALSE = 0;
    public static final int kCFStringEncodingMacRoman = 0;
    public static final int kCFStringEncodingWindowsLatin1 = 1280;
    public static final int kCFStringEncodingISOLatin1 = 513;
    public static final int kCFStringEncodingNextStepLatin = 2817;
    public static final int kCFStringEncodingASCII = 1536;
    public static final int kCFStringEncodingUnicode = 256;
    public static final int kCFStringEncodingUTF8 = 134217984;
    public static final int kCFStringEncodingNonLossyASCII = 3071;
    public static final int kCFStringEncodingUTF16 = 256;
    public static final int kCFStringEncodingUTF16BE = 268435712;
    public static final int kCFStringEncodingUTF16LE = 335544576;
    public static final int kCFStringEncodingUTF32 = 201326848;
    public static final int kCFStringEncodingUTF32BE = 402653440;
    public static final int kCFStringEncodingUTF32LE = 469762304;
    public static final int kCFURLPOSIXPathStyle = 0;
    public static final int kCFURLHFSPathStyle = 1;
    public static final int kCFURLWindowsPathStyle = 2;
    public static final long kCFAllocatorDefault;
    public static final long kCFAllocatorSystemDefault;
    public static final long kCFAllocatorMalloc;
    public static final long kCFAllocatorMallocZone;
    public static final long kCFAllocatorNull;
    public static final long kCFAllocatorUseContext;

    @NativeType("CFAllocatorRef")
    private static native long kCFAllocatorDefault();

    @NativeType("CFAllocatorRef")
    private static native long kCFAllocatorSystemDefault();

    @NativeType("CFAllocatorRef")
    private static native long kCFAllocatorMalloc();

    @NativeType("CFAllocatorRef")
    private static native long kCFAllocatorMallocZone();

    @NativeType("CFAllocatorRef")
    private static native long kCFAllocatorNull();

    @NativeType("CFAllocatorRef")
    private static native long kCFAllocatorUseContext();

    public static native long nCFRetain(long j);

    public static native void nCFRelease(long j);

    public static native long nCFBundleCreate(long j, long j2);

    public static native long nCFBundleGetBundleWithIdentifier(long j);

    public static native long nCFBundleGetFunctionPointerForName(long j, long j2);

    public static native long nCFStringCreateWithCString(long j, long j2, int i);

    public static native long nCFStringCreateWithCStringNoCopy(long j, long j2, int i, long j3);

    public static native long nCFURLCreateWithFileSystemPath(long j, long j2, long j3, boolean z);

    static {
        Library.initialize();
        kCFAllocatorDefault = kCFAllocatorDefault();
        kCFAllocatorSystemDefault = kCFAllocatorSystemDefault();
        kCFAllocatorMalloc = kCFAllocatorMalloc();
        kCFAllocatorMallocZone = kCFAllocatorMallocZone();
        kCFAllocatorNull = kCFAllocatorNull();
        kCFAllocatorUseContext = kCFAllocatorUseContext();
    }

    protected CoreFoundation() {
        throw new UnsupportedOperationException();
    }

    @NativeType("CFTypeRef")
    public static long CFRetain(@NativeType("CFTypeRef") long j) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return nCFRetain(j);
    }

    public static void CFRelease(@NativeType("CFTypeRef") long j) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        nCFRelease(j);
    }

    @NativeType("CFBundleRef")
    public static long CFBundleCreate(@NativeType("CFAllocatorRef") long j, @NativeType("CFURLRef") long j2) {
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        return nCFBundleCreate(j, j2);
    }

    @NativeType("CFBundleRef")
    public static long CFBundleGetBundleWithIdentifier(@NativeType("CFStringRef") long j) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return nCFBundleGetBundleWithIdentifier(j);
    }

    @NativeType("void *")
    public static long CFBundleGetFunctionPointerForName(@NativeType("CFBundleRef") long j, @NativeType("CFStringRef") long j2) {
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(j2);
        }
        return nCFBundleGetFunctionPointerForName(j, j2);
    }

    @NativeType("CFStringRef")
    public static long CFStringCreateWithCString(@NativeType("CFAllocatorRef") long j, @NativeType("char const *") ByteBuffer byteBuffer, @NativeType("CFStringEncoding") int i) {
        return nCFStringCreateWithCString(j, MemoryUtil.memAddress(byteBuffer), i);
    }

    @NativeType("CFStringRef")
    public static long CFStringCreateWithCStringNoCopy(@NativeType("CFAllocatorRef") long j, @NativeType("char const *") ByteBuffer byteBuffer, @NativeType("CFStringEncoding") int i, @NativeType("CFAllocatorRef") long j2) {
        return nCFStringCreateWithCStringNoCopy(j, MemoryUtil.memAddress(byteBuffer), i, j2);
    }

    @NativeType("CFURLRef")
    public static long CFURLCreateWithFileSystemPath(@NativeType("CFAllocatorRef") long j, @NativeType("CFStringRef") long j2, @NativeType("CFURLPathStyle") long j3, @NativeType("Boolean") boolean z) {
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        return nCFURLCreateWithFileSystemPath(j, j2, j3, z);
    }
}
