package org.lwjgl.system.linux;

import com.badlogic.gdx.net.HttpResponseHeader;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.CLongBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.APIUtil;
import org.lwjgl.system.Checks;
import org.lwjgl.system.Configuration;
import org.lwjgl.system.CustomBuffer;
import org.lwjgl.system.JNI;
import org.lwjgl.system.Library;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.SharedLibrary;
import org.lwjgl.system.linux.XTimeCoord;

/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/X11.class */
public class X11 {
    private static final SharedLibrary X11 = Library.loadNative((Class<?>) X11.class, "org.lwjgl", (Configuration<String>) null, "libX11.so.6", "libX11.so");
    public static final int True = 1;
    public static final int False = 0;
    public static final int None = 0;
    public static final int ParentRelative = 1;
    public static final int CopyFromParent = 0;
    public static final int PointerWindow = 0;
    public static final int InputFocus = 1;
    public static final int PointerRoot = 1;
    public static final int AnyPropertyType = 0;
    public static final int AnyKey = 0;
    public static final int AnyButton = 0;
    public static final int AllTemporary = 0;
    public static final int CurrentTime = 0;
    public static final int NoSymbol = 0;
    public static final int Success = 0;
    public static final int BadRequest = 1;
    public static final int BadValue = 2;
    public static final int BadWindow = 3;
    public static final int BadPixmap = 4;
    public static final int BadAtom = 5;
    public static final int BadCursor = 6;
    public static final int BadFont = 7;
    public static final int BadMatch = 8;
    public static final int BadDrawable = 9;
    public static final int BadAccess = 10;
    public static final int BadAlloc = 11;
    public static final int BadColor = 12;
    public static final int BadGC = 13;
    public static final int BadIDChoice = 14;
    public static final int BadName = 15;
    public static final int BadLength = 16;
    public static final int BadImplementation = 17;
    public static final int FirstExtensionError = 128;
    public static final int LastExtensionError = 255;
    public static final int CWBackPixmap = 1;
    public static final int CWBackPixel = 2;
    public static final int CWBorderPixmap = 4;
    public static final int CWBorderPixel = 8;
    public static final int CWBitGravity = 16;
    public static final int CWWinGravity = 32;
    public static final int CWBackingStore = 64;
    public static final int CWBackingPlanes = 128;
    public static final int CWBackingPixel = 256;
    public static final int CWOverrideRedirect = 512;
    public static final int CWSaveUnder = 1024;
    public static final int CWEventMask = 2048;
    public static final int CWDontPropagate = 4096;
    public static final int CWColormap = 8192;
    public static final int CWCursor = 16384;
    public static final int NoEventMask = 0;
    public static final int KeyPressMask = 1;
    public static final int KeyReleaseMask = 2;
    public static final int ButtonPressMask = 4;
    public static final int ButtonReleaseMask = 8;
    public static final int EnterWindowMask = 16;
    public static final int LeaveWindowMask = 32;
    public static final int PointerMotionMask = 64;
    public static final int PointerMotionHintMask = 128;
    public static final int Button1MotionMask = 256;
    public static final int Button2MotionMask = 512;
    public static final int Button3MotionMask = 1024;
    public static final int Button4MotionMask = 2048;
    public static final int Button5MotionMask = 4096;
    public static final int ButtonMotionMask = 8192;
    public static final int KeymapStateMask = 16384;
    public static final int ExposureMask = 32768;
    public static final int VisibilityChangeMask = 65536;
    public static final int StructureNotifyMask = 131072;
    public static final int ResizeRedirectMask = 262144;
    public static final int SubstructureNotifyMask = 524288;
    public static final int SubstructureRedirectMask = 1048576;
    public static final int FocusChangeMask = 2097152;
    public static final int PropertyChangeMask = 4194304;
    public static final int ColormapChangeMask = 8388608;
    public static final int OwnerGrabButtonMask = 16777216;
    public static final int KeyPress = 2;
    public static final int KeyRelease = 3;
    public static final int ButtonPress = 4;
    public static final int ButtonRelease = 5;
    public static final int MotionNotify = 6;
    public static final int EnterNotify = 7;
    public static final int LeaveNotify = 8;
    public static final int FocusIn = 9;
    public static final int FocusOut = 10;
    public static final int KeymapNotify = 11;
    public static final int Expose = 12;
    public static final int GraphicsExpose = 13;
    public static final int NoExpose = 14;
    public static final int VisibilityNotify = 15;
    public static final int CreateNotify = 16;
    public static final int DestroyNotify = 17;
    public static final int UnmapNotify = 18;
    public static final int MapNotify = 19;
    public static final int MapRequest = 20;
    public static final int ReparentNotify = 21;
    public static final int ConfigureNotify = 22;
    public static final int ConfigureRequest = 23;
    public static final int GravityNotify = 24;
    public static final int ResizeRequest = 25;
    public static final int CirculateNotify = 26;
    public static final int CirculateRequest = 27;
    public static final int PropertyNotify = 28;
    public static final int SelectionClear = 29;
    public static final int SelectionRequest = 30;
    public static final int SelectionNotify = 31;
    public static final int ColormapNotify = 32;
    public static final int ClientMessage = 33;
    public static final int MappingNotify = 34;
    public static final int GenericEvent = 35;
    public static final int LASTEvent = 36;
    public static final int ShiftMask = 1;
    public static final int LockMask = 2;
    public static final int ControlMask = 4;
    public static final int Mod1Mask = 8;
    public static final int Mod2Mask = 16;
    public static final int Mod3Mask = 32;
    public static final int Mod4Mask = 64;
    public static final int Mod5Mask = 128;
    public static final int ShiftMapIndex = 0;
    public static final int LockMapIndex = 1;
    public static final int ControlMapIndex = 2;
    public static final int Mod1MapIndex = 3;
    public static final int Mod2MapIndex = 4;
    public static final int Mod3MapIndex = 5;
    public static final int Mod4MapIndex = 6;
    public static final int Mod5MapIndex = 7;
    public static final int Button1Mask = 256;
    public static final int Button2Mask = 512;
    public static final int Button3Mask = 1024;
    public static final int Button4Mask = 2048;
    public static final int Button5Mask = 4096;
    public static final int AnyModifier = 32768;
    public static final int Button1 = 1;
    public static final int Button2 = 2;
    public static final int Button3 = 3;
    public static final int Button4 = 4;
    public static final int Button5 = 5;
    public static final int NotifyNormal = 0;
    public static final int NotifyGrab = 1;
    public static final int NotifyUngrab = 2;
    public static final int NotifyWhileGrabbed = 3;
    public static final int NotifyHint = 1;
    public static final int NotifyAncestor = 0;
    public static final int NotifyVirtual = 1;
    public static final int NotifyInferior = 2;
    public static final int NotifyNonlinear = 3;
    public static final int NotifyNonlinearVirtual = 4;
    public static final int NotifyPointer = 5;
    public static final int NotifyPointerRoot = 6;
    public static final int NotifyDetailNone = 7;
    public static final int VisibilityUnobscured = 0;
    public static final int VisibilityPartiallyObscured = 1;
    public static final int VisibilityFullyObscured = 2;
    public static final int PlaceOnTop = 0;
    public static final int PlaceOnBottom = 1;
    public static final int PropertyNewValue = 0;
    public static final int PropertyDelete = 1;
    public static final int ColormapUninstalled = 0;
    public static final int ColormapInstalled = 1;
    public static final int GrabModeSync = 0;
    public static final int GrabModeAsync = 1;
    public static final int GrabSuccess = 0;
    public static final int AlreadyGrabbed = 1;
    public static final int GrabInvalidTime = 2;
    public static final int GrabNotViewable = 3;
    public static final int GrabFrozen = 4;
    public static final int AsyncPointer = 0;
    public static final int SyncPointer = 1;
    public static final int ReplayPointer = 2;
    public static final int AsyncKeyboard = 3;
    public static final int SyncKeyboard = 4;
    public static final int ReplayKeyboard = 5;
    public static final int AsyncBoth = 6;
    public static final int SyncBoth = 7;
    public static final int AllocNone = 0;
    public static final int AllocAll = 1;
    public static final int RevertToNone = 0;
    public static final int RevertToPointerRoot = 1;
    public static final int RevertToParent = 2;
    public static final int InputOutput = 1;
    public static final int InputOnly = 2;
    public static final int DontPreferBlanking = 0;
    public static final int PreferBlanking = 1;
    public static final int DefaultBlanking = 2;
    public static final int DisableScreenSaver = 0;
    public static final int DisableScreenInterval = 0;
    public static final int DontAllowExposures = 0;
    public static final int AllowExposures = 1;
    public static final int DefaultExposures = 2;
    public static final int ScreenSaverReset = 0;
    public static final int ScreenSaverActive = 1;
    public static final int PropModeReplace = 0;
    public static final int PropModePrepend = 1;
    public static final int PropModeAppend = 2;
    public static final int GXclear = 0;
    public static final int GXand = 1;
    public static final int GXandReverse = 2;
    public static final int GXcopy = 3;
    public static final int GXandInverted = 4;
    public static final int GXnoop = 5;
    public static final int GXxor = 6;
    public static final int GXor = 7;
    public static final int GXnor = 8;
    public static final int GXequiv = 9;
    public static final int GXinvert = 10;
    public static final int GXorReverse = 11;
    public static final int GXcopyInverted = 12;
    public static final int GXorInverted = 13;
    public static final int GXnand = 14;
    public static final int GXset = 15;
    public static final int LineSolid = 0;
    public static final int LineOnOffDash = 1;
    public static final int LineDoubleDash = 2;
    public static final int CapNotLast = 0;
    public static final int CapButt = 1;
    public static final int CapRound = 2;
    public static final int CapProjecting = 3;
    public static final int JoinMiter = 0;
    public static final int JoinRound = 1;
    public static final int JoinBevel = 2;
    public static final int FillSolid = 0;
    public static final int FillTiled = 1;
    public static final int FillStippled = 2;
    public static final int FillOpaqueStippled = 3;
    public static final int EvenOddRule = 0;
    public static final int WindingRule = 1;
    public static final int ClipByChildren = 0;
    public static final int IncludeInferiors = 1;
    public static final int Unsorted = 0;
    public static final int YSorted = 1;
    public static final int YXSorted = 2;
    public static final int YXBanded = 3;
    public static final int CoordModeOrigin = 0;
    public static final int CoordModePrevious = 1;
    public static final int Complex = 0;
    public static final int Nonconvex = 1;
    public static final int Convex = 2;
    public static final int ArcChord = 0;
    public static final int ArcPieSlice = 1;
    public static final int GCFunction = 1;
    public static final int GCPlaneMask = 2;
    public static final int GCForeground = 4;
    public static final int GCBackground = 8;
    public static final int GCLineWidth = 16;
    public static final int GCLineStyle = 32;
    public static final int GCCapStyle = 64;
    public static final int GCJoinStyle = 128;
    public static final int GCFillStyle = 256;
    public static final int GCFillRule = 512;
    public static final int GCTile = 1024;
    public static final int GCStipple = 2048;
    public static final int GCTileStipXOrigin = 4096;
    public static final int GCTileStipYOrigin = 8192;
    public static final int GCFont = 16384;
    public static final int GCSubwindowMode = 32768;
    public static final int GCGraphicsExposures = 65536;
    public static final int GCClipXOrigin = 131072;
    public static final int GCClipYOrigin = 262144;
    public static final int GCClipMask = 524288;
    public static final int GCDashOffset = 1048576;
    public static final int GCDashList = 2097152;
    public static final int GCArcMode = 4194304;
    public static final int GCLastBit = 22;
    public static final int Above = 0;
    public static final int Below = 1;
    public static final int TopIf = 2;
    public static final int BottomIf = 3;
    public static final int Opposite = 4;
    public static final int MappingModifier = 0;
    public static final int MappingKeyboard = 1;
    public static final int MappingPointer = 2;

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/X11$Functions.class */
    public static final class Functions {
        public static final long XOpenDisplay = APIUtil.apiGetFunctionAddress(X11.X11, "XOpenDisplay");
        public static final long XCloseDisplay = APIUtil.apiGetFunctionAddress(X11.X11, "XCloseDisplay");
        public static final long XDefaultScreen = APIUtil.apiGetFunctionAddress(X11.X11, "XDefaultScreen");
        public static final long XRootWindow = APIUtil.apiGetFunctionAddress(X11.X11, "XRootWindow");
        public static final long XCreateColormap = APIUtil.apiGetFunctionAddress(X11.X11, "XCreateColormap");
        public static final long XFreeColormap = APIUtil.apiGetFunctionAddress(X11.X11, "XFreeColormap");
        public static final long XCreateWindow = APIUtil.apiGetFunctionAddress(X11.X11, "XCreateWindow");
        public static final long XDestroyWindow = APIUtil.apiGetFunctionAddress(X11.X11, "XDestroyWindow");
        public static final long XFree = APIUtil.apiGetFunctionAddress(X11.X11, "XFree");
        public static final long XSendEvent = APIUtil.apiGetFunctionAddress(X11.X11, "XSendEvent");
        public static final long XDisplayMotionBufferSize = APIUtil.apiGetFunctionAddress(X11.X11, "XDisplayMotionBufferSize");
        public static final long XGetMotionEvents = APIUtil.apiGetFunctionAddress(X11.X11, "XGetMotionEvents");
        public static final long XTranslateCoordinates = APIUtil.apiGetFunctionAddress(X11.X11, "XTranslateCoordinates");

        private Functions() {
        }
    }

    public static SharedLibrary getLibrary() {
        return X11;
    }

    protected X11() {
        throw new UnsupportedOperationException();
    }

    public static long nXOpenDisplay(long j) {
        return JNI.invokePP(j, Functions.XOpenDisplay);
    }

    @NativeType("Display *")
    public static long XOpenDisplay(@NativeType("char const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1Safe(byteBuffer);
        }
        return nXOpenDisplay(MemoryUtil.memAddressSafe(byteBuffer));
    }

    @NativeType("Display *")
    public static long XOpenDisplay(@NativeType("char const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCIISafe(charSequence, true);
            return nXOpenDisplay(charSequence == null ? 0L : stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void XCloseDisplay(@NativeType("Display *") long j) {
        long j2 = Functions.XCloseDisplay;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(j, j2);
    }

    public static int XDefaultScreen(@NativeType("Display *") long j) {
        long j2 = Functions.XDefaultScreen;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePI(j, j2);
    }

    @NativeType("Window")
    public static long XRootWindow(@NativeType("Display *") long j, int i) {
        long j2 = Functions.XRootWindow;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePN(j, i, j2);
    }

    public static long nXCreateColormap(long j, long j2, long j3, int i) {
        long j4 = Functions.XCreateColormap;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePNPN(j, j2, j3, i, j4);
    }

    @NativeType("Colormap")
    public static long XCreateColormap(@NativeType("Display *") long j, @NativeType("Window") long j2, @NativeType("Visual *") Visual visual, int i) {
        return nXCreateColormap(j, j2, visual.address(), i);
    }

    public static int XFreeColormap(@NativeType("Display *") long j, @NativeType("Colormap") long j2) {
        long j3 = Functions.XFreeColormap;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePNI(j, j2, j3);
    }

    public static long nXCreateWindow(long j, long j2, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j3, long j4, long j5) {
        long j6 = Functions.XCreateWindow;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePNPNPN(j, j2, i, i2, i3, i4, i5, i6, i7, j3, j4, j5, j6);
    }

    @NativeType("Window")
    public static long XCreateWindow(@NativeType("Display *") long j, @NativeType("Window") long j2, int i, int i2, @NativeType("unsigned int") int i3, @NativeType("unsigned int") int i4, @NativeType("unsigned int") int i5, int i6, @NativeType("unsigned int") int i7, @NativeType("Visual *") Visual visual, @NativeType("unsigned long") long j3, @NativeType("XSetWindowAttributes *") XSetWindowAttributes xSetWindowAttributes) {
        return nXCreateWindow(j, j2, i, i2, i3, i4, i5, i6, i7, visual.address(), j3, xSetWindowAttributes.address());
    }

    public static int XDestroyWindow(@NativeType("Display *") long j, @NativeType("Window") long j2) {
        long j3 = Functions.XDestroyWindow;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePNI(j, j2, j3);
    }

    public static int nXFree(long j) {
        return JNI.invokePI(j, Functions.XFree);
    }

    public static int XFree(@NativeType("void *") ByteBuffer byteBuffer) {
        return nXFree(MemoryUtil.memAddress(byteBuffer));
    }

    public static int XFree(@NativeType("void *") PointerBuffer pointerBuffer) {
        return nXFree(MemoryUtil.memAddress(pointerBuffer));
    }

    public static int nXSendEvent(long j, long j2, int i, long j3, long j4) {
        long j5 = Functions.XSendEvent;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePNNPI(j, j2, i, j3, j4, j5);
    }

    @NativeType(HttpResponseHeader.Status)
    public static int XSendEvent(@NativeType("Display *") long j, @NativeType("Window") long j2, @NativeType("Bool") boolean z, long j3, @NativeType("XEvent *") XEvent xEvent) {
        return nXSendEvent(j, j2, z ? 1 : 0, j3, xEvent.address());
    }

    @NativeType("unsigned long")
    public static long XDisplayMotionBufferSize(@NativeType("Display *") long j) {
        long j2 = Functions.XDisplayMotionBufferSize;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePN(j, j2);
    }

    public static long nXGetMotionEvents(long j, long j2, long j3, long j4, long j5) {
        long j6 = Functions.XGetMotionEvents;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePNNNPP(j, j2, j3, j4, j5, j6);
    }

    @NativeType("XTimeCoord *")
    public static XTimeCoord.Buffer XGetMotionEvents(@NativeType("Display *") long j, @NativeType("Window") long j2, @NativeType("Time") long j3, @NativeType("Time") long j4) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        IntBuffer callocInt = stackGet.callocInt(1);
        try {
            return XTimeCoord.createSafe(nXGetMotionEvents(j, j2, j3, j4, MemoryUtil.memAddress(callocInt)), callocInt.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static int nXTranslateCoordinates(long j, long j2, long j3, int i, int i2, long j4, long j5, long j6) {
        long j7 = Functions.XTranslateCoordinates;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePNNPPPI(j, j2, j3, i, i2, j4, j5, j6, j7);
    }

    @NativeType("Bool")
    public static boolean XTranslateCoordinates(@NativeType("Display *") long j, @NativeType("Window") long j2, @NativeType("Window") long j3, int i, int i2, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2, @NativeType("Window *") CLongBuffer cLongBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
            Checks.check((CustomBuffer<?>) cLongBuffer, 1);
        }
        return nXTranslateCoordinates(j, j2, j3, i, i2, MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(cLongBuffer)) != 0;
    }

    @NativeType("Bool")
    public static boolean XTranslateCoordinates(@NativeType("Display *") long j, @NativeType("Window") long j2, @NativeType("Window") long j3, int i, int i2, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2, @NativeType("Window *") CLongBuffer cLongBuffer) {
        long j4 = Functions.XTranslateCoordinates;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
            Checks.check(iArr2, 1);
            Checks.check((CustomBuffer<?>) cLongBuffer, 1);
        }
        return JNI.invokePNNPPPI(j, j2, j3, i, i2, iArr, iArr2, MemoryUtil.memAddress(cLongBuffer), j4) != 0;
    }
}
