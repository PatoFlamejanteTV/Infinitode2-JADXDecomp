package org.lwjgl.system.macosx;

import java.nio.Buffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.CLongBuffer;
import org.lwjgl.system.APIUtil;
import org.lwjgl.system.Checks;
import org.lwjgl.system.CustomBuffer;
import org.lwjgl.system.JNI;
import org.lwjgl.system.Library;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.SharedLibrary;
import org.lwjgl.system.macosx.CGEventTapInformation;

/* loaded from: infinitode-2.jar:org/lwjgl/system/macosx/CoreGraphics.class */
public class CoreGraphics {
    private static final SharedLibrary COREGRAPHICS = Library.loadNative(CoreGraphics.class, "org.lwjgl", "/System/Library/Frameworks/CoreGraphics.framework");
    public static final int kCGErrorSuccess = 0;
    public static final int kCGErrorFailure = 1000;
    public static final int kCGErrorIllegalArgument = 1001;
    public static final int kCGErrorInvalidConnection = 1002;
    public static final int kCGErrorInvalidContext = 1003;
    public static final int kCGErrorCannotComplete = 1004;
    public static final int kCGErrorNotImplemented = 1006;
    public static final int kCGErrorRangeCheck = 1007;
    public static final int kCGErrorTypeCheck = 1008;
    public static final int kCGErrorInvalidOperation = 1010;
    public static final int kCGErrorNoneAvailable = 1011;
    public static final int kCGEventNull = 0;
    public static final int kCGEventLeftMouseDown = 1;
    public static final int kCGEventLeftMouseUp = 2;
    public static final int kCGEventRightMouseDown = 3;
    public static final int kCGEventRightMouseUp = 4;
    public static final int kCGEventMouseMoved = 5;
    public static final int kCGEventLeftMouseDragged = 6;
    public static final int kCGEventRightMouseDragged = 7;
    public static final int kCGEventKeyDown = 10;
    public static final int kCGEventKeyUp = 11;
    public static final int kCGEventFlagsChanged = 12;
    public static final int kCGEventScrollWheel = 22;
    public static final int kCGEventTabletPointer = 23;
    public static final int kCGEventTabletProximity = 24;
    public static final int kCGEventOtherMouseDown = 25;
    public static final int kCGEventOtherMouseUp = 26;
    public static final int kCGEventOtherMouseDragged = 27;
    public static final int kCGEventTapDisabledByTimeout = -2;
    public static final int kCGEventTapDisabledByUserInput = -1;
    public static final int kCGMouseButtonLeft = 0;
    public static final int kCGMouseButtonRight = 1;
    public static final int kCGMouseButtonCenter = 2;
    public static final int kCGHIDEventTap = 0;
    public static final int kCGSessionEventTap = 1;
    public static final int kCGAnnotatedSessionEventTap = 2;
    public static final int kCGScrollEventUnitPixel = 0;
    public static final int kCGScrollEventUnitLine = 1;
    public static final int kCGMouseEventNumber = 0;
    public static final int kCGMouseEventClickState = 1;
    public static final int kCGMouseEventPressure = 2;
    public static final int kCGMouseEventButtonNumber = 3;
    public static final int kCGMouseEventDeltaX = 4;
    public static final int kCGMouseEventDeltaY = 5;
    public static final int kCGMouseEventInstantMouser = 6;
    public static final int kCGMouseEventSubtype = 7;
    public static final int kCGKeyboardEventAutorepeat = 8;
    public static final int kCGKeyboardEventKeycode = 9;
    public static final int kCGKeyboardEventKeyboardType = 10;
    public static final int kCGScrollWheelEventDeltaAxis1 = 11;
    public static final int kCGScrollWheelEventDeltaAxis2 = 12;
    public static final int kCGScrollWheelEventDeltaAxis3 = 13;
    public static final int kCGScrollWheelEventFixedPtDeltaAxis1 = 93;
    public static final int kCGScrollWheelEventFixedPtDeltaAxis2 = 94;
    public static final int kCGScrollWheelEventFixedPtDeltaAxis3 = 95;
    public static final int kCGScrollWheelEventPointDeltaAxis1 = 96;
    public static final int kCGScrollWheelEventPointDeltaAxis2 = 97;
    public static final int kCGScrollWheelEventPointDeltaAxis3 = 98;
    public static final int kCGScrollWheelEventScrollPhase = 99;
    public static final int kCGScrollWheelEventScrollCount = 100;
    public static final int kCGScrollWheelEventMomentumPhase = 123;
    public static final int kCGScrollWheelEventInstantMouser = 14;
    public static final int kCGTabletEventPointX = 15;
    public static final int kCGTabletEventPointY = 16;
    public static final int kCGTabletEventPointZ = 17;
    public static final int kCGTabletEventPointButtons = 18;
    public static final int kCGTabletEventPointPressure = 19;
    public static final int kCGTabletEventTiltX = 20;
    public static final int kCGTabletEventTiltY = 21;
    public static final int kCGTabletEventRotation = 22;
    public static final int kCGTabletEventTangentialPressure = 23;
    public static final int kCGTabletEventDeviceID = 24;
    public static final int kCGTabletEventVendor1 = 25;
    public static final int kCGTabletEventVendor2 = 26;
    public static final int kCGTabletEventVendor3 = 27;
    public static final int kCGTabletProximityEventVendorID = 28;
    public static final int kCGTabletProximityEventTabletID = 29;
    public static final int kCGTabletProximityEventPointerID = 30;
    public static final int kCGTabletProximityEventDeviceID = 31;
    public static final int kCGTabletProximityEventSystemTabletID = 32;
    public static final int kCGTabletProximityEventVendorPointerType = 33;
    public static final int kCGTabletProximityEventVendorPointerSerialNumber = 34;
    public static final int kCGTabletProximityEventVendorUniqueID = 35;
    public static final int kCGTabletProximityEventCapabilityMask = 36;
    public static final int kCGTabletProximityEventPointerType = 37;
    public static final int kCGTabletProximityEventEnterProximity = 38;
    public static final int kCGEventTargetProcessSerialNumber = 39;
    public static final int kCGEventTargetUnixProcessID = 40;
    public static final int kCGEventSourceUnixProcessID = 41;
    public static final int kCGEventSourceUserData = 42;
    public static final int kCGEventSourceUserID = 43;
    public static final int kCGEventSourceGroupID = 44;
    public static final int kCGEventSourceStateID = 45;
    public static final int kCGScrollWheelEventIsContinuous = 88;
    public static final int kCGMouseEventWindowUnderMousePointer = 91;
    public static final int kCGMouseEventWindowUnderMousePointerThatCanHandleThisEvent = 92;
    public static final int kCGEventMouseSubtypeDefault = 0;
    public static final int kCGEventMouseSubtypeTabletPoint = 1;
    public static final int kCGEventMouseSubtypeTabletProximity = 2;

    public static native long nCGEventCreateMouseEvent(long j, int i, long j2, int i2, long j3);

    public static native void nCGEventGetLocation(long j, long j2, long j3);

    public static native void nCGEventGetUnflippedLocation(long j, long j2, long j3);

    public static native void nCGEventSetLocation(long j, long j2, long j3);

    /* loaded from: infinitode-2.jar:org/lwjgl/system/macosx/CoreGraphics$Functions.class */
    public static final class Functions {
        public static final long EventGetTypeID = APIUtil.apiGetFunctionAddress(CoreGraphics.COREGRAPHICS, "CGEventGetTypeID");
        public static final long EventCreate = APIUtil.apiGetFunctionAddress(CoreGraphics.COREGRAPHICS, "CGEventCreate");
        public static final long EventCreateData = APIUtil.apiGetFunctionAddress(CoreGraphics.COREGRAPHICS, "CGEventCreateData");
        public static final long EventCreateFromData = APIUtil.apiGetFunctionAddress(CoreGraphics.COREGRAPHICS, "CGEventCreateFromData");
        public static final long EventCreateMouseEvent = APIUtil.apiGetFunctionAddress(CoreGraphics.COREGRAPHICS, "CGEventCreateMouseEvent");
        public static final long EventCreateKeyboardEvent = APIUtil.apiGetFunctionAddress(CoreGraphics.COREGRAPHICS, "CGEventCreateKeyboardEvent");
        public static final long EventCreateScrollWheelEvent = APIUtil.apiGetFunctionAddress(CoreGraphics.COREGRAPHICS, "CGEventCreateScrollWheelEvent");
        public static final long EventCreateScrollWheelEvent2 = APIUtil.apiGetFunctionAddressOptional(CoreGraphics.COREGRAPHICS, "CGEventCreateScrollWheelEvent2");
        public static final long EventCreateCopy = APIUtil.apiGetFunctionAddress(CoreGraphics.COREGRAPHICS, "CGEventCreateCopy");
        public static final long EventCreateSourceFromEvent = APIUtil.apiGetFunctionAddress(CoreGraphics.COREGRAPHICS, "CGEventCreateSourceFromEvent");
        public static final long EventSetSource = APIUtil.apiGetFunctionAddress(CoreGraphics.COREGRAPHICS, "CGEventSetSource");
        public static final long EventGetType = APIUtil.apiGetFunctionAddress(CoreGraphics.COREGRAPHICS, "CGEventGetType");
        public static final long EventSetType = APIUtil.apiGetFunctionAddress(CoreGraphics.COREGRAPHICS, "CGEventSetType");
        public static final long EventGetTimestamp = APIUtil.apiGetFunctionAddress(CoreGraphics.COREGRAPHICS, "CGEventGetTimestamp");
        public static final long EventSetTimestamp = APIUtil.apiGetFunctionAddress(CoreGraphics.COREGRAPHICS, "CGEventSetTimestamp");
        public static final long EventGetLocation = APIUtil.apiGetFunctionAddress(CoreGraphics.COREGRAPHICS, "CGEventGetLocation");
        public static final long EventGetUnflippedLocation = APIUtil.apiGetFunctionAddress(CoreGraphics.COREGRAPHICS, "CGEventGetUnflippedLocation");
        public static final long EventSetLocation = APIUtil.apiGetFunctionAddress(CoreGraphics.COREGRAPHICS, "CGEventSetLocation");
        public static final long EventGetFlags = APIUtil.apiGetFunctionAddress(CoreGraphics.COREGRAPHICS, "CGEventGetFlags");
        public static final long EventSetFlags = APIUtil.apiGetFunctionAddress(CoreGraphics.COREGRAPHICS, "CGEventSetFlags");
        public static final long EventKeyboardGetUnicodeString = APIUtil.apiGetFunctionAddress(CoreGraphics.COREGRAPHICS, "CGEventKeyboardGetUnicodeString");
        public static final long EventKeyboardSetUnicodeString = APIUtil.apiGetFunctionAddress(CoreGraphics.COREGRAPHICS, "CGEventKeyboardSetUnicodeString");
        public static final long EventGetIntegerValueField = APIUtil.apiGetFunctionAddress(CoreGraphics.COREGRAPHICS, "CGEventGetIntegerValueField");
        public static final long EventSetIntegerValueField = APIUtil.apiGetFunctionAddress(CoreGraphics.COREGRAPHICS, "CGEventSetIntegerValueField");
        public static final long EventGetDoubleValueField = APIUtil.apiGetFunctionAddress(CoreGraphics.COREGRAPHICS, "CGEventGetDoubleValueField");
        public static final long EventSetDoubleValueField = APIUtil.apiGetFunctionAddress(CoreGraphics.COREGRAPHICS, "CGEventSetDoubleValueField");
        public static final long EventTapCreate = APIUtil.apiGetFunctionAddress(CoreGraphics.COREGRAPHICS, "CGEventTapCreate");
        public static final long EventTapCreateForPid = APIUtil.apiGetFunctionAddressOptional(CoreGraphics.COREGRAPHICS, "CGEventTapCreateForPid");
        public static final long EventTapEnable = APIUtil.apiGetFunctionAddress(CoreGraphics.COREGRAPHICS, "CGEventTapEnable");
        public static final long EventTapIsEnabled = APIUtil.apiGetFunctionAddress(CoreGraphics.COREGRAPHICS, "CGEventTapIsEnabled");
        public static final long EventTapPostEvent = APIUtil.apiGetFunctionAddress(CoreGraphics.COREGRAPHICS, "CGEventTapPostEvent");
        public static final long EventPost = APIUtil.apiGetFunctionAddress(CoreGraphics.COREGRAPHICS, "CGEventPost");
        public static final long EventPostToPid = APIUtil.apiGetFunctionAddressOptional(CoreGraphics.COREGRAPHICS, "CGEventPostToPid");
        public static final long GetEventTapList = APIUtil.apiGetFunctionAddress(CoreGraphics.COREGRAPHICS, "CGGetEventTapList");

        private Functions() {
        }
    }

    public static SharedLibrary getLibrary() {
        return COREGRAPHICS;
    }

    protected CoreGraphics() {
        throw new UnsupportedOperationException();
    }

    @NativeType("CFTypeID")
    public static long CGEventGetTypeID() {
        return JNI.invokeJ(Functions.EventGetTypeID);
    }

    @NativeType("CGEventRef")
    public static long CGEventCreate(@NativeType("CGEventSourceRef") long j) {
        return JNI.invokePP(j, Functions.EventCreate);
    }

    @NativeType("CFDataRef")
    public static long CGEventCreateData(@NativeType("CFAllocatorRef") long j, @NativeType("CGEventRef") long j2) {
        return JNI.invokePPP(j, j2, Functions.EventCreateData);
    }

    @NativeType("CGEventRef")
    public static long CGEventCreateFromData(@NativeType("CFAllocatorRef") long j, @NativeType("CFDataRef") long j2) {
        return JNI.invokePPP(j, j2, Functions.EventCreateFromData);
    }

    public static long nCGEventCreateMouseEvent(long j, int i, long j2, int i2) {
        return nCGEventCreateMouseEvent(j, i, j2, i2, Functions.EventCreateMouseEvent);
    }

    @NativeType("CGEventRef")
    public static long CGEventCreateMouseEvent(@NativeType("CGEventSourceRef") long j, @NativeType("CGEventType") int i, CGPoint cGPoint, @NativeType("CGMouseButton") int i2) {
        return nCGEventCreateMouseEvent(j, i, cGPoint.address(), i2);
    }

    @NativeType("CGEventRef")
    public static long CGEventCreateKeyboardEvent(@NativeType("CGEventSourceRef") long j, @NativeType("CGKeyCode") short s, @NativeType("bool") boolean z) {
        return JNI.invokePCP(j, s, z, Functions.EventCreateKeyboardEvent);
    }

    @NativeType("CGEventRef")
    public static long CGEventCreateScrollWheelEvent(@NativeType("CGEventSourceRef") long j, @NativeType("CGScrollEventUnit") int i, @NativeType("uint32_t") int i2, @NativeType("int32_t") int i3) {
        return JNI.invokePP(j, i, i2, i3, Functions.EventCreateScrollWheelEvent);
    }

    @NativeType("CGEventRef")
    public static long CGEventCreateScrollWheelEvent(@NativeType("CGEventSourceRef") long j, @NativeType("CGScrollEventUnit") int i, @NativeType("int32_t") int i2) {
        return JNI.invokePP(j, i, 1, i2, Functions.EventCreateScrollWheelEvent);
    }

    @NativeType("CGEventRef")
    public static long CGEventCreateScrollWheelEvent2(@NativeType("CGEventSourceRef") long j, @NativeType("CGScrollEventUnit") int i, @NativeType("uint32_t") int i2, @NativeType("int32_t") int i3, @NativeType("int32_t") int i4, @NativeType("int32_t") int i5) {
        long j2 = Functions.EventCreateScrollWheelEvent2;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        return JNI.invokePP(j, i, i2, i3, i4, i5, j2);
    }

    @NativeType("CGEventRef")
    public static long CGEventCreateCopy(@NativeType("CGEventRef") long j) {
        return JNI.invokePP(j, Functions.EventCreateCopy);
    }

    @NativeType("CGEventSourceRef")
    public static long CGEventCreateSourceFromEvent(@NativeType("CGEventRef") long j) {
        return JNI.invokePP(j, Functions.EventCreateSourceFromEvent);
    }

    public static void CGEventSetSource(@NativeType("CGEventRef") long j, @NativeType("CGEventSourceRef") long j2) {
        JNI.invokePPV(j, j2, Functions.EventSetSource);
    }

    @NativeType("CGEventType")
    public static int CGEventGetType(@NativeType("CGEventRef") long j) {
        return JNI.invokePI(j, Functions.EventGetType);
    }

    public static void CGEventSetType(@NativeType("CGEventRef") long j, @NativeType("CGEventType") int i) {
        JNI.invokePV(j, i, Functions.EventSetType);
    }

    @NativeType("CGEventTimestamp")
    public static long CGEventGetTimestamp(@NativeType("CGEventRef") long j) {
        return JNI.invokePJ(j, Functions.EventGetTimestamp);
    }

    public static void CGEventSetTimestamp(@NativeType("CGEventRef") long j, @NativeType("CGEventTimestamp") long j2) {
        JNI.invokePJV(j, j2, Functions.EventSetTimestamp);
    }

    public static void nCGEventGetLocation(long j, long j2) {
        nCGEventGetLocation(j, Functions.EventGetLocation, j2);
    }

    public static CGPoint CGEventGetLocation(@NativeType("CGEventRef") long j, CGPoint cGPoint) {
        nCGEventGetLocation(j, cGPoint.address());
        return cGPoint;
    }

    public static void nCGEventGetUnflippedLocation(long j, long j2) {
        nCGEventGetUnflippedLocation(j, Functions.EventGetUnflippedLocation, j2);
    }

    public static CGPoint CGEventGetUnflippedLocation(@NativeType("CGEventRef") long j, CGPoint cGPoint) {
        nCGEventGetUnflippedLocation(j, cGPoint.address());
        return cGPoint;
    }

    public static void nCGEventSetLocation(long j, long j2) {
        nCGEventSetLocation(j, j2, Functions.EventSetLocation);
    }

    public static void CGEventSetLocation(@NativeType("CGEventRef") long j, CGPoint cGPoint) {
        nCGEventSetLocation(j, cGPoint.address());
    }

    @NativeType("CGEventFlags")
    public static long CGEventGetFlags(@NativeType("CGEventRef") long j) {
        return JNI.invokePJ(j, Functions.EventGetFlags);
    }

    public static void CGEventSetFlags(@NativeType("CGEventRef") long j, @NativeType("CGEventFlags") long j2) {
        JNI.invokePJV(j, j2, Functions.EventSetFlags);
    }

    public static void nCGEventKeyboardGetUnicodeString(long j, long j2, long j3, long j4) {
        JNI.invokePNPPV(j, j2, j3, j4, Functions.EventKeyboardGetUnicodeString);
    }

    public static void CGEventKeyboardGetUnicodeString(@NativeType("CGEventRef") long j, @NativeType("UniCharCount *") CLongBuffer cLongBuffer, @NativeType("UniChar *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.checkSafe((CustomBuffer<?>) cLongBuffer, 1);
        }
        nCGEventKeyboardGetUnicodeString(j, Checks.remainingSafe(shortBuffer), MemoryUtil.memAddressSafe(cLongBuffer), MemoryUtil.memAddressSafe(shortBuffer));
    }

    public static void nCGEventKeyboardSetUnicodeString(long j, long j2, long j3) {
        JNI.invokePNPV(j, j2, j3, Functions.EventKeyboardSetUnicodeString);
    }

    public static void CGEventKeyboardSetUnicodeString(@NativeType("CGEventRef") long j, @NativeType("UniChar const *") ShortBuffer shortBuffer) {
        nCGEventKeyboardSetUnicodeString(j, shortBuffer.remaining(), MemoryUtil.memAddress(shortBuffer));
    }

    @NativeType("int64_t")
    public static long CGEventGetIntegerValueField(@NativeType("CGEventRef") long j, @NativeType("CGEventField") int i) {
        return JNI.invokePJ(j, i, Functions.EventGetIntegerValueField);
    }

    public static void CGEventSetIntegerValueField(@NativeType("CGEventRef") long j, @NativeType("CGEventField") int i, @NativeType("int64_t") long j2) {
        JNI.invokePJV(j, i, j2, Functions.EventSetIntegerValueField);
    }

    public static double CGEventGetDoubleValueField(@NativeType("CGEventRef") long j, @NativeType("CGEventField") int i) {
        return JNI.invokePD(j, i, Functions.EventGetDoubleValueField);
    }

    public static void CGEventSetDoubleValueField(@NativeType("CGEventRef") long j, @NativeType("CGEventField") int i, double d) {
        JNI.invokePV(j, i, d, Functions.EventSetDoubleValueField);
    }

    public static long nCGEventTapCreate(int i, int i2, int i3, long j, long j2, long j3) {
        return JNI.invokeJPPP(i, i2, i3, j, j2, j3, Functions.EventTapCreate);
    }

    @NativeType("CFMachPortRef")
    public static long CGEventTapCreate(@NativeType("CGEventTapLocation") int i, @NativeType("CGEventTapPlacement") int i2, @NativeType("CGEventTapOptions") int i3, @NativeType("CGEventMask") long j, @NativeType("CGEventRef (*) (CGEventTapProxy, CGEventType, CGEventRef, void *)") CGEventTapCallBackI cGEventTapCallBackI, @NativeType("void *") long j2) {
        return nCGEventTapCreate(i, i2, i3, j, cGEventTapCallBackI.address(), j2);
    }

    public static long nCGEventTapCreateForPid(long j, int i, int i2, long j2, long j3, long j4) {
        long j5 = Functions.EventTapCreateForPid;
        if (Checks.CHECKS) {
            Checks.check(j5);
            Checks.check(j);
        }
        return JNI.invokePJPPP(j, i, i2, j2, j3, j4, j5);
    }

    @NativeType("CFMachPortRef")
    public static long CGEventTapCreateForPid(@NativeType("pid_t") long j, @NativeType("CGEventTapPlacement") int i, @NativeType("CGEventTapOptions") int i2, @NativeType("CGEventMask") long j2, @NativeType("CGEventRef (*) (CGEventTapProxy, CGEventType, CGEventRef, void *)") CGEventTapCallBackI cGEventTapCallBackI, @NativeType("void *") long j3) {
        return nCGEventTapCreateForPid(j, i, i2, j2, cGEventTapCallBackI.address(), j3);
    }

    public static void CGEventTapEnable(@NativeType("CFMachPortRef") long j, @NativeType("bool") boolean z) {
        long j2 = Functions.EventTapEnable;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(j, z, j2);
    }

    @NativeType("bool")
    public static boolean CGEventTapIsEnabled(@NativeType("CFMachPortRef") long j) {
        long j2 = Functions.EventTapIsEnabled;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePZ(j, j2);
    }

    public static void CGEventTapPostEvent(@NativeType("CGEventTapProxy") long j, @NativeType("CGEventRef") long j2) {
        JNI.invokePPV(j, j2, Functions.EventTapPostEvent);
    }

    public static void CGEventPost(@NativeType("CGEventTapLocation") int i, @NativeType("CGEventRef") long j) {
        long j2 = Functions.EventPost;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(i, j, j2);
    }

    public static void CGEventPostToPid(@NativeType("pid_t") long j, @NativeType("CGEventRef") long j2) {
        long j3 = Functions.EventPostToPid;
        if (Checks.CHECKS) {
            Checks.check(j3);
            Checks.check(j);
        }
        JNI.invokePPV(j, j2, j3);
    }

    public static int nCGGetEventTapList(int i, long j, long j2) {
        return JNI.invokePPI(i, j, j2, Functions.GetEventTapList);
    }

    @NativeType("CGError")
    public static int CGGetEventTapList(@NativeType("CGEventTapInformation *") CGEventTapInformation.Buffer buffer, @NativeType("uint32_t *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
        }
        return nCGGetEventTapList(Checks.remainingSafe(buffer), MemoryUtil.memAddressSafe(buffer), MemoryUtil.memAddressSafe(intBuffer));
    }

    public static void CGEventKeyboardGetUnicodeString(@NativeType("CGEventRef") long j, @NativeType("UniCharCount *") CLongBuffer cLongBuffer, @NativeType("UniChar *") short[] sArr) {
        long j2 = Functions.EventKeyboardGetUnicodeString;
        if (Checks.CHECKS) {
            Checks.checkSafe((CustomBuffer<?>) cLongBuffer, 1);
        }
        JNI.invokePNPPV(j, Checks.lengthSafe(sArr), MemoryUtil.memAddressSafe(cLongBuffer), sArr, j2);
    }

    public static void CGEventKeyboardSetUnicodeString(@NativeType("CGEventRef") long j, @NativeType("UniChar const *") short[] sArr) {
        JNI.invokePNPV(j, sArr.length, sArr, Functions.EventKeyboardSetUnicodeString);
    }

    @NativeType("CGError")
    public static int CGGetEventTapList(@NativeType("CGEventTapInformation *") CGEventTapInformation.Buffer buffer, @NativeType("uint32_t *") int[] iArr) {
        long j = Functions.GetEventTapList;
        if (Checks.CHECKS) {
            Checks.checkSafe(iArr, 1);
        }
        return JNI.invokePPI(Checks.remainingSafe(buffer), MemoryUtil.memAddressSafe(buffer), iArr, j);
    }
}
