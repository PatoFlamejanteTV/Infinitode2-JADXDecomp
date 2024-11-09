package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.APIUtil;
import org.lwjgl.system.Checks;
import org.lwjgl.system.CustomBuffer;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/CGL.class */
public class CGL {
    public static final int kCGLPFAAllRenderers = 1;
    public static final int kCGLPFATripleBuffer = 3;
    public static final int kCGLPFADoubleBuffer = 5;
    public static final int kCGLPFAStereo = 6;
    public static final int kCGLPFAColorSize = 8;
    public static final int kCGLPFAAlphaSize = 11;
    public static final int kCGLPFADepthSize = 12;
    public static final int kCGLPFAStencilSize = 13;
    public static final int kCGLPFAMinimumPolicy = 51;
    public static final int kCGLPFAMaximumPolicy = 52;
    public static final int kCGLPFASampleBuffers = 55;
    public static final int kCGLPFASamples = 56;
    public static final int kCGLPFAColorFloat = 58;
    public static final int kCGLPFAMultisample = 59;
    public static final int kCGLPFASupersample = 60;
    public static final int kCGLPFASampleAlpha = 61;
    public static final int kCGLPFARendererID = 70;
    public static final int kCGLPFASingleRenderer = 71;
    public static final int kCGLPFANoRecovery = 72;
    public static final int kCGLPFAAccelerated = 73;
    public static final int kCGLPFAClosestPolicy = 74;
    public static final int kCGLPFABackingStore = 76;
    public static final int kCGLPFABackingVolatile = 77;
    public static final int kCGLPFADisplayMask = 84;
    public static final int kCGLPFAAllowOfflineRenderers = 96;
    public static final int kCGLPFAAcceleratedCompute = 97;
    public static final int kCGLPFAOpenGLProfile = 99;
    public static final int kCGLPFASupportsAutomaticGraphicsSwitching = 101;
    public static final int kCGLPFAVirtualScreenCount = 128;
    public static final int kCGLPFAAuxBuffers = 7;
    public static final int kCGLPFAAccumSize = 14;
    public static final int kCGLPFAOffScreen = 53;
    public static final int kCGLPFAAuxDepthStencil = 57;
    public static final int kCGLPFAWindow = 80;
    public static final int kCGLPFACompliant = 83;
    public static final int kCGLPFAPBuffer = 90;
    public static final int kCGLPFARemotePBuffer = 91;
    public static final int kCGLPFARobust = 75;
    public static final int kCGLPFAMPSafe = 78;
    public static final int kCGLPFAMultiScreen = 81;
    public static final int kCGLPFAFullScreen = 54;
    public static final int kCGLRPOffScreen = 53;
    public static final int kCGLRPRendererID = 70;
    public static final int kCGLRPAccelerated = 73;
    public static final int kCGLRPBackingStore = 76;
    public static final int kCGLRPWindow = 80;
    public static final int kCGLRPCompliant = 83;
    public static final int kCGLRPDisplayMask = 84;
    public static final int kCGLRPBufferModes = 100;
    public static final int kCGLRPColorModes = 103;
    public static final int kCGLRPAccumModes = 104;
    public static final int kCGLRPDepthModes = 105;
    public static final int kCGLRPStencilModes = 106;
    public static final int kCGLRPMaxAuxBuffers = 107;
    public static final int kCGLRPMaxSampleBuffers = 108;
    public static final int kCGLRPMaxSamples = 109;
    public static final int kCGLRPSampleModes = 110;
    public static final int kCGLRPSampleAlpha = 111;
    public static final int kCGLRPVideoMemory = 120;
    public static final int kCGLRPTextureMemory = 121;
    public static final int kCGLRPGPUVertProcCapable = 122;
    public static final int kCGLRPGPUFragProcCapable = 123;
    public static final int kCGLRPRendererCount = 128;
    public static final int kCGLRPOnline = 129;
    public static final int kCGLRPAcceleratedCompute = 130;
    public static final int kCGLRPVideoMemoryMegabytes = 131;
    public static final int kCGLRPTextureMemoryMegabytes = 132;
    public static final int kCGLRPRobust = 75;
    public static final int kCGLRPMPSafe = 78;
    public static final int kCGLRPMultiScreen = 81;
    public static final int kCGLRPFullScreen = 54;
    public static final int kCGLCESwapRectangle = 201;
    public static final int kCGLCESwapLimit = 203;
    public static final int kCGLCERasterization = 221;
    public static final int kCGLCEStateValidation = 301;
    public static final int kCGLCESurfaceBackingSize = 305;
    public static final int kCGLCEDisplayListOptimization = 307;
    public static final int kCGLCEMPEngine = 313;
    public static final int kCGLCPSwapRectangle = 200;
    public static final int kCGLCPSwapInterval = 222;
    public static final int kCGLCPDispatchTableSize = 224;
    public static final int kCGLCPClientStorage = 226;
    public static final int kCGLCPSurfaceTexture = 228;
    public static final int kCGLCPSurfaceOrder = 235;
    public static final int kCGLCPSurfaceOpacity = 236;
    public static final int kCGLCPSurfaceBackingSize = 304;
    public static final int kCGLCPSurfaceSurfaceVolatile = 306;
    public static final int kCGLCPReclaimResources = 308;
    public static final int kCGLCPCurrentRendererID = 309;
    public static final int kCGLCPGPUVertexProcessing = 310;
    public static final int kCGLCPGPUFragmentProcessing = 311;
    public static final int kCGLCPHasDrawable = 314;
    public static final int kCGLCPMPSwapsInFlight = 315;
    public static final int kCGLGOFormatCacheSize = 501;
    public static final int kCGLGOClearFormatCache = 502;
    public static final int kCGLGORetainRenderers = 503;
    public static final int kCGLGOResetLibrary = 504;
    public static final int kCGLGOUseErrorHandler = 505;
    public static final int kCGLGOUseBuildCache = 506;
    public static final int kCGLOGLPVersion_Legacy = 4096;
    public static final int kCGLOGLPVersion_3_2_Core = 12800;
    public static final int kCGLNoError = 0;
    public static final int kCGLBadAttribute = 10000;
    public static final int kCGLBadProperty = 10001;
    public static final int kCGLBadPixelFormat = 10002;
    public static final int kCGLBadRendererInfo = 10003;
    public static final int kCGLBadContext = 10004;
    public static final int kCGLBadDrawable = 10005;
    public static final int kCGLBadDisplay = 10006;
    public static final int kCGLBadState = 10007;
    public static final int kCGLBadValue = 10008;
    public static final int kCGLBadMatch = 10009;
    public static final int kCGLBadEnumeration = 10010;
    public static final int kCGLBadOffScreen = 10011;
    public static final int kCGLBadFullScreen = 10012;
    public static final int kCGLBadWindow = 10013;
    public static final int kCGLBadAddress = 10014;
    public static final int kCGLBadCodeModule = 10015;
    public static final int kCGLBadAlloc = 10016;
    public static final int kCGLBadConnection = 10017;
    public static final int kCGLMonoscopicBit = 1;
    public static final int kCGLStereoscopicBit = 2;
    public static final int kCGLSingleBufferBit = 4;
    public static final int kCGLDoubleBufferBit = 8;
    public static final int kCGLTripleBufferBit = 16;
    public static final int kCGL0Bit = 1;
    public static final int kCGL1Bit = 2;
    public static final int kCGL2Bit = 4;
    public static final int kCGL3Bit = 8;
    public static final int kCGL4Bit = 16;
    public static final int kCGL5Bit = 32;
    public static final int kCGL6Bit = 64;
    public static final int kCGL8Bit = 128;
    public static final int kCGL10Bit = 256;
    public static final int kCGL12Bit = 512;
    public static final int kCGL16Bit = 1024;
    public static final int kCGL24Bit = 2048;
    public static final int kCGL32Bit = 4096;
    public static final int kCGL48Bit = 8192;
    public static final int kCGL64Bit = 16384;
    public static final int kCGL96Bit = 32768;
    public static final int kCGL128Bit = 65536;
    public static final int kCGLRGB444Bit = 64;
    public static final int kCGLARGB4444Bit = 128;
    public static final int kCGLRGB444A8Bit = 256;
    public static final int kCGLRGB555Bit = 512;
    public static final int kCGLARGB1555Bit = 1024;
    public static final int kCGLRGB555A8Bit = 2048;
    public static final int kCGLRGB565Bit = 4096;
    public static final int kCGLRGB565A8Bit = 8192;
    public static final int kCGLRGB888Bit = 16384;
    public static final int kCGLARGB8888Bit = 32768;
    public static final int kCGLRGB888A8Bit = 65536;
    public static final int kCGLRGB101010Bit = 131072;
    public static final int kCGLARGB2101010Bit = 262144;
    public static final int kCGLRGB101010_A8Bit = 524288;
    public static final int kCGLRGB121212Bit = 1048576;
    public static final int kCGLARGB12121212Bit = 2097152;
    public static final int kCGLRGB161616Bit = 4194304;
    public static final int kCGLRGBA16161616Bit = 8388608;
    public static final int kCGLRGBFloat64Bit = 16777216;
    public static final int kCGLRGBAFloat64Bit = 33554432;
    public static final int kCGLRGBFloat128Bit = 67108864;
    public static final int kCGLRGBAFloat128Bit = 134217728;
    public static final int kCGLRGBFloat256Bit = 268435456;
    public static final int kCGLRGBAFloat256Bit = 536870912;
    public static final int kCGLSupersampleBit = 1;
    public static final int kCGLMultisampleBit = 2;

    /* loaded from: infinitode-2.jar:org/lwjgl/opengl/CGL$Functions.class */
    public static final class Functions {
        public static final long GetCurrentContext = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLGetCurrentContext");
        public static final long SetCurrentContext = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLSetCurrentContext");
        public static final long GetShareGroup = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLGetShareGroup");
        public static final long ChoosePixelFormat = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLChoosePixelFormat");
        public static final long DestroyPixelFormat = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLDestroyPixelFormat");
        public static final long DescribePixelFormat = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLDescribePixelFormat");
        public static final long ReleasePixelFormat = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLReleasePixelFormat");
        public static final long RetainPixelFormat = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLRetainPixelFormat");
        public static final long GetPixelFormatRetainCount = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLGetPixelFormatRetainCount");
        public static final long QueryRendererInfo = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLQueryRendererInfo");
        public static final long DestroyRendererInfo = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLDestroyRendererInfo");
        public static final long DescribeRenderer = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLDescribeRenderer");
        public static final long CreateContext = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLCreateContext");
        public static final long DestroyContext = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLDestroyContext");
        public static final long CopyContext = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLCopyContext");
        public static final long RetainContext = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLRetainContext");
        public static final long ReleaseContext = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLReleaseContext");
        public static final long GetContextRetainCount = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLGetContextRetainCount");
        public static final long GetPixelFormat = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLGetPixelFormat");
        public static final long CreatePBuffer = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLCreatePBuffer");
        public static final long DestroyPBuffer = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLDestroyPBuffer");
        public static final long DescribePBuffer = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLDescribePBuffer");
        public static final long TexImagePBuffer = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLTexImagePBuffer");
        public static final long RetainPBuffer = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLRetainPBuffer");
        public static final long ReleasePBuffer = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLReleasePBuffer");
        public static final long GetPBufferRetainCount = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLGetPBufferRetainCount");
        public static final long SetOffScreen = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLSetOffScreen");
        public static final long GetOffScreen = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLGetOffScreen");
        public static final long SetFullScreen = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLSetFullScreen");
        public static final long SetFullScreenOnDisplay = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLSetFullScreenOnDisplay");
        public static final long SetPBuffer = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLSetPBuffer");
        public static final long GetPBuffer = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLGetPBuffer");
        public static final long ClearDrawable = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLClearDrawable");
        public static final long FlushDrawable = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLFlushDrawable");
        public static final long Enable = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLEnable");
        public static final long Disable = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLDisable");
        public static final long IsEnabled = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLIsEnabled");
        public static final long SetParameter = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLSetParameter");
        public static final long GetParameter = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLGetParameter");
        public static final long SetVirtualScreen = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLSetVirtualScreen");
        public static final long GetVirtualScreen = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLGetVirtualScreen");
        public static final long UpdateContext = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLUpdateContext");
        public static final long SetGlobalOption = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLSetGlobalOption");
        public static final long GetGlobalOption = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLGetGlobalOption");
        public static final long LockContext = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLLockContext");
        public static final long UnlockContext = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLUnlockContext");
        public static final long GetVersion = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLGetVersion");
        public static final long ErrorString = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "CGLErrorString");

        private Functions() {
        }
    }

    protected CGL() {
        throw new UnsupportedOperationException();
    }

    @NativeType("CGLContextObj")
    public static long CGLGetCurrentContext() {
        return JNI.callP(Functions.GetCurrentContext);
    }

    @NativeType("CGLError")
    public static int CGLSetCurrentContext(@NativeType("CGLContextObj") long j) {
        return JNI.callPI(j, Functions.SetCurrentContext);
    }

    @NativeType("CGLShareGroupObj")
    public static long CGLGetShareGroup(@NativeType("CGLContextObj") long j) {
        long j2 = Functions.GetShareGroup;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callPP(j, j2);
    }

    public static int nCGLChoosePixelFormat(long j, long j2, long j3) {
        return JNI.callPPPI(j, j2, j3, Functions.ChoosePixelFormat);
    }

    @NativeType("CGLError")
    public static int CGLChoosePixelFormat(@NativeType("CGLPixelFormatAttribute const *") IntBuffer intBuffer, @NativeType("CGLPixelFormatObj *") PointerBuffer pointerBuffer, @NativeType("GLint *") IntBuffer intBuffer2) {
        if (Checks.CHECKS) {
            Checks.checkNT(intBuffer);
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
        }
        return nCGLChoosePixelFormat(MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(pointerBuffer), MemoryUtil.memAddress(intBuffer2));
    }

    @NativeType("CGLError")
    public static int CGLDestroyPixelFormat(@NativeType("CGLPixelFormatObj") long j) {
        long j2 = Functions.DestroyPixelFormat;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callPI(j, j2);
    }

    public static int nCGLDescribePixelFormat(long j, int i, int i2, long j2) {
        long j3 = Functions.DescribePixelFormat;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callPPI(j, i, i2, j2, j3);
    }

    @NativeType("CGLError")
    public static int CGLDescribePixelFormat(@NativeType("CGLPixelFormatObj") long j, @NativeType("GLint") int i, @NativeType("CGLPixelFormatAttribute") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        return nCGLDescribePixelFormat(j, i, i2, MemoryUtil.memAddress(intBuffer));
    }

    public static void CGLReleasePixelFormat(@NativeType("CGLPixelFormatObj") long j) {
        long j2 = Functions.ReleasePixelFormat;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(j, j2);
    }

    @NativeType("CGLPixelFormatObj")
    public static long CGLRetainPixelFormat(@NativeType("CGLPixelFormatObj") long j) {
        long j2 = Functions.RetainPixelFormat;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callPP(j, j2);
    }

    @NativeType("GLuint")
    public static int CGLGetPixelFormatRetainCount(@NativeType("CGLPixelFormatObj") long j) {
        long j2 = Functions.GetPixelFormatRetainCount;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callPI(j, j2);
    }

    public static int nCGLQueryRendererInfo(int i, long j, long j2) {
        return JNI.callPPI(i, j, j2, Functions.QueryRendererInfo);
    }

    @NativeType("CGLError")
    public static int CGLQueryRendererInfo(@NativeType("GLuint") int i, @NativeType("CGLRendererInfoObj *") PointerBuffer pointerBuffer, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
            Checks.check((Buffer) intBuffer, 1);
        }
        return nCGLQueryRendererInfo(i, MemoryUtil.memAddress(pointerBuffer), MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("CGLError")
    public static int CGLDestroyRendererInfo(@NativeType("CGLRendererInfoObj") long j) {
        long j2 = Functions.DestroyRendererInfo;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callPI(j, j2);
    }

    public static int nCGLDescribeRenderer(long j, int i, int i2, long j2) {
        long j3 = Functions.DescribeRenderer;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callPPI(j, i, i2, j2, j3);
    }

    @NativeType("CGLError")
    public static int CGLDescribeRenderer(@NativeType("CGLRendererInfoObj") long j, @NativeType("GLint") int i, @NativeType("CGLRendererProperty") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        return nCGLDescribeRenderer(j, i, i2, MemoryUtil.memAddress(intBuffer));
    }

    public static int nCGLCreateContext(long j, long j2, long j3) {
        long j4 = Functions.CreateContext;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callPPPI(j, j2, j3, j4);
    }

    @NativeType("CGLError")
    public static int CGLCreateContext(@NativeType("CGLPixelFormatObj") long j, @NativeType("CGLContextObj") long j2, @NativeType("CGLContextObj *") PointerBuffer pointerBuffer) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
        }
        return nCGLCreateContext(j, j2, MemoryUtil.memAddress(pointerBuffer));
    }

    @NativeType("CGLError")
    public static int CGLDestroyContext(@NativeType("CGLContextObj") long j) {
        long j2 = Functions.DestroyContext;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callPI(j, j2);
    }

    @NativeType("CGLError")
    public static int CGLCopyContext(@NativeType("CGLContextObj") long j, @NativeType("CGLContextObj") long j2, @NativeType("GLbitfield") int i) {
        long j3 = Functions.CopyContext;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(j2);
        }
        return JNI.callPPI(j, j2, i, j3);
    }

    @NativeType("CGLContextObj")
    public static long CGLRetainContext(@NativeType("CGLContextObj") long j) {
        long j2 = Functions.RetainContext;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callPP(j, j2);
    }

    public static void CGLReleaseContext(@NativeType("CGLContextObj") long j) {
        long j2 = Functions.ReleaseContext;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(j, j2);
    }

    @NativeType("GLuint")
    public static int CGLGetContextRetainCount(@NativeType("CGLContextObj") long j) {
        long j2 = Functions.GetContextRetainCount;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callPI(j, j2);
    }

    @NativeType("CGLPixelFormatObj")
    public static long CGLGetPixelFormat(@NativeType("CGLContextObj") long j) {
        long j2 = Functions.GetPixelFormat;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callPP(j, j2);
    }

    public static int nCGLCreatePBuffer(int i, int i2, int i3, int i4, int i5, long j) {
        return JNI.callPI(i, i2, i3, i4, i5, j, Functions.CreatePBuffer);
    }

    @NativeType("CGLError")
    public static int CGLCreatePBuffer(@NativeType("GLsizei") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("GLint") int i5, @NativeType("CGLPBufferObj *") PointerBuffer pointerBuffer) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
        }
        return nCGLCreatePBuffer(i, i2, i3, i4, i5, MemoryUtil.memAddress(pointerBuffer));
    }

    @NativeType("CGLError")
    public static int CGLDestroyPBuffer(@NativeType("CGLPBufferObj") long j) {
        long j2 = Functions.DestroyPBuffer;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callPI(j, j2);
    }

    public static int nCGLDescribePBuffer(long j, long j2, long j3, long j4, long j5, long j6) {
        long j7 = Functions.DescribePBuffer;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callPPPPPPI(j, j2, j3, j4, j5, j6, j7);
    }

    @NativeType("CGLError")
    public static int CGLDescribePBuffer(@NativeType("CGLPBufferObj") long j, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLsizei *") IntBuffer intBuffer2, @NativeType("GLenum *") IntBuffer intBuffer3, @NativeType("GLenum *") IntBuffer intBuffer4, @NativeType("GLint *") IntBuffer intBuffer5) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
            Checks.check((Buffer) intBuffer3, 1);
            Checks.check((Buffer) intBuffer4, 1);
            Checks.check((Buffer) intBuffer5, 1);
        }
        return nCGLDescribePBuffer(j, MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(intBuffer3), MemoryUtil.memAddress(intBuffer4), MemoryUtil.memAddress(intBuffer5));
    }

    @NativeType("CGLError")
    public static int CGLTexImagePBuffer(@NativeType("CGLContextObj") long j, @NativeType("CGLPBufferObj") long j2, @NativeType("GLenum") int i) {
        long j3 = Functions.TexImagePBuffer;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(j2);
        }
        return JNI.callPPI(j, j2, i, j3);
    }

    @NativeType("CGLPBufferObj")
    public static long CGLRetainPBuffer(@NativeType("CGLPBufferObj") long j) {
        long j2 = Functions.RetainPBuffer;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callPP(j, j2);
    }

    public static void CGLReleasePBuffer(@NativeType("CGLPBufferObj") long j) {
        long j2 = Functions.ReleasePBuffer;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(j, j2);
    }

    @NativeType("GLuint")
    public static int CGLGetPBufferRetainCount(@NativeType("CGLPBufferObj") long j) {
        long j2 = Functions.GetPBufferRetainCount;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callPI(j, j2);
    }

    public static int nCGLSetOffScreen(long j, int i, int i2, int i3, long j2) {
        long j3 = Functions.SetOffScreen;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callPPI(j, i, i2, i3, j2, j3);
    }

    @NativeType("CGLError")
    public static int CGLSetOffScreen(@NativeType("CGLContextObj") long j, @NativeType("GLsizei") int i, @NativeType("GLsizei") int i2, @NativeType("GLint") int i3, @NativeType("void *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, i3 * i2);
        }
        return nCGLSetOffScreen(j, i, i2, i3, MemoryUtil.memAddress(byteBuffer));
    }

    public static int nCGLGetOffScreen(long j, long j2, long j3, long j4, long j5) {
        long j6 = Functions.GetOffScreen;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callPPPPPI(j, j2, j3, j4, j5, j6);
    }

    @NativeType("CGLError")
    public static int CGLGetOffScreen(@NativeType("CGLContextObj") long j, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLsizei *") IntBuffer intBuffer2, @NativeType("GLint *") IntBuffer intBuffer3, @NativeType("void **") PointerBuffer pointerBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
            Checks.check((Buffer) intBuffer3, 1);
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
        }
        return nCGLGetOffScreen(j, MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(intBuffer3), MemoryUtil.memAddress(pointerBuffer));
    }

    @NativeType("CGLError")
    public static int CGLSetFullScreen(@NativeType("CGLContextObj") long j) {
        long j2 = Functions.SetFullScreen;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callPI(j, j2);
    }

    @NativeType("CGLError")
    public static int CGLSetFullScreenOnDisplay(@NativeType("CGLContextObj") long j, @NativeType("GLuint") int i) {
        long j2 = Functions.SetFullScreenOnDisplay;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callPI(j, i, j2);
    }

    @NativeType("CGLError")
    public static int CGLSetPBuffer(@NativeType("CGLContextObj") long j, @NativeType("CGLPBufferObj") long j2, @NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3) {
        long j3 = Functions.SetPBuffer;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(j2);
        }
        return JNI.callPPI(j, j2, i, i2, i3, j3);
    }

    public static int nCGLGetPBuffer(long j, long j2, long j3, long j4, long j5) {
        long j6 = Functions.GetPBuffer;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callPPPPPI(j, j2, j3, j4, j5, j6);
    }

    @NativeType("CGLError")
    public static int CGLGetPBuffer(@NativeType("CGLContextObj") long j, @NativeType("CGLPBufferObj *") PointerBuffer pointerBuffer, @NativeType("GLenum *") IntBuffer intBuffer, @NativeType("GLint *") IntBuffer intBuffer2, @NativeType("GLint *") IntBuffer intBuffer3) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
            Checks.check((Buffer) intBuffer3, 1);
        }
        return nCGLGetPBuffer(j, MemoryUtil.memAddress(pointerBuffer), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(intBuffer3));
    }

    @NativeType("CGLError")
    public static int CGLClearDrawable(@NativeType("CGLContextObj") long j) {
        long j2 = Functions.ClearDrawable;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callPI(j, j2);
    }

    @NativeType("CGLError")
    public static int CGLFlushDrawable(@NativeType("CGLContextObj") long j) {
        long j2 = Functions.FlushDrawable;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callPI(j, j2);
    }

    @NativeType("CGLError")
    public static int CGLEnable(@NativeType("CGLContextObj") long j, @NativeType("CGLContextEnable") int i) {
        long j2 = Functions.Enable;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callPI(j, i, j2);
    }

    @NativeType("CGLError")
    public static int CGLDisable(@NativeType("CGLContextObj") long j, @NativeType("CGLContextEnable") int i) {
        long j2 = Functions.Disable;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callPI(j, i, j2);
    }

    public static int nCGLIsEnabled(long j, int i, long j2) {
        long j3 = Functions.IsEnabled;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callPPI(j, i, j2, j3);
    }

    @NativeType("CGLError")
    public static int CGLIsEnabled(@NativeType("CGLContextObj") long j, @NativeType("CGLContextEnable") int i, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        return nCGLIsEnabled(j, i, MemoryUtil.memAddress(intBuffer));
    }

    public static int nCGLSetParameter(long j, int i, long j2) {
        long j3 = Functions.SetParameter;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callPPI(j, i, j2, j3);
    }

    @NativeType("CGLError")
    public static int CGLSetParameter(@NativeType("CGLContextObj") long j, @NativeType("CGLContextParameter") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        return nCGLSetParameter(j, i, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("CGLError")
    public static int CGLSetParameter(@NativeType("CGLContextObj") long j, @NativeType("CGLContextParameter") int i, @NativeType("GLint const *") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            return nCGLSetParameter(j, i, MemoryUtil.memAddress(stackGet.ints(i2)));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static int nCGLGetParameter(long j, int i, long j2) {
        long j3 = Functions.GetParameter;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callPPI(j, i, j2, j3);
    }

    @NativeType("CGLError")
    public static int CGLGetParameter(@NativeType("CGLContextObj") long j, @NativeType("CGLContextParameter") int i, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        return nCGLGetParameter(j, i, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("CGLError")
    public static int CGLSetVirtualScreen(@NativeType("CGLContextObj") long j, @NativeType("GLint") int i) {
        long j2 = Functions.SetVirtualScreen;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callPI(j, i, j2);
    }

    public static int nCGLGetVirtualScreen(long j, long j2) {
        long j3 = Functions.GetVirtualScreen;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callPPI(j, j2, j3);
    }

    @NativeType("CGLError")
    public static int CGLGetVirtualScreen(@NativeType("CGLContextObj") long j, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        return nCGLGetVirtualScreen(j, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("CGLError")
    public static int CGLUpdateContext(@NativeType("CGLContextObj") long j) {
        long j2 = Functions.UpdateContext;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callPI(j, j2);
    }

    public static int nCGLSetGlobalOption(int i, long j) {
        return JNI.callPI(i, j, Functions.SetGlobalOption);
    }

    @NativeType("CGLError")
    public static int CGLSetGlobalOption(@NativeType("CGLGlobalOption") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        return nCGLSetGlobalOption(i, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("CGLError")
    public static int CGLSetGlobalOption(@NativeType("CGLGlobalOption") int i, @NativeType("GLint const *") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            return nCGLSetGlobalOption(i, MemoryUtil.memAddress(stackGet.ints(i2)));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static int nCGLGetGlobalOption(int i, long j) {
        return JNI.callPI(i, j, Functions.GetGlobalOption);
    }

    @NativeType("CGLError")
    public static int CGLGetGlobalOption(@NativeType("CGLGlobalOption") int i, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        return nCGLGetGlobalOption(i, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("CGLError")
    public static int CGLLockContext(@NativeType("CGLContextObj") long j) {
        long j2 = Functions.LockContext;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callPI(j, j2);
    }

    @NativeType("CGLError")
    public static int CGLUnlockContext(@NativeType("CGLContextObj") long j) {
        long j2 = Functions.UnlockContext;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callPI(j, j2);
    }

    public static void nCGLGetVersion(long j, long j2) {
        JNI.callPPV(j, j2, Functions.GetVersion);
    }

    public static void CGLGetVersion(@NativeType("GLint *") IntBuffer intBuffer, @NativeType("GLint *") IntBuffer intBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
        }
        nCGLGetVersion(MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2));
    }

    public static long nCGLErrorString(int i) {
        return JNI.callP(i, Functions.ErrorString);
    }

    @NativeType("char const *")
    public static String CGLErrorString(@NativeType("CGLError") int i) {
        return MemoryUtil.memASCIISafe(nCGLErrorString(i));
    }

    @NativeType("CGLError")
    public static int CGLChoosePixelFormat(@NativeType("CGLPixelFormatAttribute const *") int[] iArr, @NativeType("CGLPixelFormatObj *") PointerBuffer pointerBuffer, @NativeType("GLint *") int[] iArr2) {
        long j = Functions.ChoosePixelFormat;
        if (Checks.CHECKS) {
            Checks.checkNT(iArr);
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
            Checks.check(iArr2, 1);
        }
        return JNI.callPPPI(iArr, MemoryUtil.memAddress(pointerBuffer), iArr2, j);
    }

    @NativeType("CGLError")
    public static int CGLDescribePixelFormat(@NativeType("CGLPixelFormatObj") long j, @NativeType("GLint") int i, @NativeType("CGLPixelFormatAttribute") int i2, @NativeType("GLint *") int[] iArr) {
        long j2 = Functions.DescribePixelFormat;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        return JNI.callPPI(j, i, i2, iArr, j2);
    }

    @NativeType("CGLError")
    public static int CGLQueryRendererInfo(@NativeType("GLuint") int i, @NativeType("CGLRendererInfoObj *") PointerBuffer pointerBuffer, @NativeType("GLint *") int[] iArr) {
        long j = Functions.QueryRendererInfo;
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
            Checks.check(iArr, 1);
        }
        return JNI.callPPI(i, MemoryUtil.memAddress(pointerBuffer), iArr, j);
    }

    @NativeType("CGLError")
    public static int CGLDescribeRenderer(@NativeType("CGLRendererInfoObj") long j, @NativeType("GLint") int i, @NativeType("CGLRendererProperty") int i2, @NativeType("GLint *") int[] iArr) {
        long j2 = Functions.DescribeRenderer;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        return JNI.callPPI(j, i, i2, iArr, j2);
    }

    @NativeType("CGLError")
    public static int CGLDescribePBuffer(@NativeType("CGLPBufferObj") long j, @NativeType("GLsizei *") int[] iArr, @NativeType("GLsizei *") int[] iArr2, @NativeType("GLenum *") int[] iArr3, @NativeType("GLenum *") int[] iArr4, @NativeType("GLint *") int[] iArr5) {
        long j2 = Functions.DescribePBuffer;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
            Checks.check(iArr2, 1);
            Checks.check(iArr3, 1);
            Checks.check(iArr4, 1);
            Checks.check(iArr5, 1);
        }
        return JNI.callPPPPPPI(j, iArr, iArr2, iArr3, iArr4, iArr5, j2);
    }

    @NativeType("CGLError")
    public static int CGLGetOffScreen(@NativeType("CGLContextObj") long j, @NativeType("GLsizei *") int[] iArr, @NativeType("GLsizei *") int[] iArr2, @NativeType("GLint *") int[] iArr3, @NativeType("void **") PointerBuffer pointerBuffer) {
        long j2 = Functions.GetOffScreen;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
            Checks.check(iArr2, 1);
            Checks.check(iArr3, 1);
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
        }
        return JNI.callPPPPPI(j, iArr, iArr2, iArr3, MemoryUtil.memAddress(pointerBuffer), j2);
    }

    @NativeType("CGLError")
    public static int CGLGetPBuffer(@NativeType("CGLContextObj") long j, @NativeType("CGLPBufferObj *") PointerBuffer pointerBuffer, @NativeType("GLenum *") int[] iArr, @NativeType("GLint *") int[] iArr2, @NativeType("GLint *") int[] iArr3) {
        long j2 = Functions.GetPBuffer;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
            Checks.check(iArr, 1);
            Checks.check(iArr2, 1);
            Checks.check(iArr3, 1);
        }
        return JNI.callPPPPPI(j, MemoryUtil.memAddress(pointerBuffer), iArr, iArr2, iArr3, j2);
    }

    @NativeType("CGLError")
    public static int CGLIsEnabled(@NativeType("CGLContextObj") long j, @NativeType("CGLContextEnable") int i, @NativeType("GLint *") int[] iArr) {
        long j2 = Functions.IsEnabled;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        return JNI.callPPI(j, i, iArr, j2);
    }

    @NativeType("CGLError")
    public static int CGLSetParameter(@NativeType("CGLContextObj") long j, @NativeType("CGLContextParameter") int i, @NativeType("GLint const *") int[] iArr) {
        long j2 = Functions.SetParameter;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        return JNI.callPPI(j, i, iArr, j2);
    }

    @NativeType("CGLError")
    public static int CGLGetParameter(@NativeType("CGLContextObj") long j, @NativeType("CGLContextParameter") int i, @NativeType("GLint *") int[] iArr) {
        long j2 = Functions.GetParameter;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        return JNI.callPPI(j, i, iArr, j2);
    }

    @NativeType("CGLError")
    public static int CGLGetVirtualScreen(@NativeType("CGLContextObj") long j, @NativeType("GLint *") int[] iArr) {
        long j2 = Functions.GetVirtualScreen;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        return JNI.callPPI(j, iArr, j2);
    }

    @NativeType("CGLError")
    public static int CGLSetGlobalOption(@NativeType("CGLGlobalOption") int i, @NativeType("GLint const *") int[] iArr) {
        long j = Functions.SetGlobalOption;
        if (Checks.CHECKS) {
            Checks.check(iArr, 1);
        }
        return JNI.callPI(i, iArr, j);
    }

    @NativeType("CGLError")
    public static int CGLGetGlobalOption(@NativeType("CGLGlobalOption") int i, @NativeType("GLint *") int[] iArr) {
        long j = Functions.GetGlobalOption;
        if (Checks.CHECKS) {
            Checks.check(iArr, 1);
        }
        return JNI.callPI(i, iArr, j);
    }

    public static void CGLGetVersion(@NativeType("GLint *") int[] iArr, @NativeType("GLint *") int[] iArr2) {
        long j = Functions.GetVersion;
        if (Checks.CHECKS) {
            Checks.check(iArr, 1);
            Checks.check(iArr2, 1);
        }
        JNI.callPPV(iArr, iArr2, j);
    }
}
