package org.lwjgl.openal;

import java.util.Set;
import java.util.function.IntFunction;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.FunctionProviderLocal;
import org.lwjgl.system.ThreadLocalUtil;

/* loaded from: infinitode-2.jar:org/lwjgl/openal/ALCCapabilities.class */
public final class ALCCapabilities {
    public final long alcOpenDevice;
    public final long alcCloseDevice;
    public final long alcCreateContext;
    public final long alcMakeContextCurrent;
    public final long alcProcessContext;
    public final long alcSuspendContext;
    public final long alcDestroyContext;
    public final long alcGetCurrentContext;
    public final long alcGetContextsDevice;
    public final long alcIsExtensionPresent;
    public final long alcGetProcAddress;
    public final long alcGetEnumValue;
    public final long alcGetError;
    public final long alcGetString;
    public final long alcGetIntegerv;
    public final long alcCaptureOpenDevice;
    public final long alcCaptureCloseDevice;
    public final long alcCaptureStart;
    public final long alcCaptureStop;
    public final long alcCaptureSamples;
    public final long alcSetThreadContext;
    public final long alcGetThreadContext;
    public final long alcGetInteger64vSOFT;
    public final long alcGetStringiSOFT;
    public final long alcResetDeviceSOFT;
    public final long alcLoopbackOpenDeviceSOFT;
    public final long alcIsRenderFormatSupportedSOFT;
    public final long alcRenderSamplesSOFT;
    public final long alcDevicePauseSOFT;
    public final long alcDeviceResumeSOFT;
    public final long alcReopenDeviceSOFT;
    public final boolean OpenALC10;
    public final boolean OpenALC11;
    public final boolean OpenALC_SOFT_loopback_bformat;
    public final boolean ALC_ENUMERATE_ALL_EXT;
    public final boolean ALC_ENUMERATION_EXT;
    public final boolean ALC_EXT_CAPTURE;
    public final boolean ALC_EXT_DEDICATED;
    public final boolean ALC_EXT_DEFAULT_FILTER_ORDER;
    public final boolean ALC_EXT_disconnect;
    public final boolean ALC_EXT_EFX;
    public final boolean ALC_EXT_thread_local_context;
    public final boolean ALC_LOKI_audio_channel;
    public final boolean ALC_SOFT_device_clock;
    public final boolean ALC_SOFT_HRTF;
    public final boolean ALC_SOFT_loopback;
    public final boolean ALC_SOFT_output_limiter;
    public final boolean ALC_SOFT_output_mode;
    public final boolean ALC_SOFT_pause_device;
    public final boolean ALC_SOFT_reopen_device;
    final long device;
    final PointerBuffer addresses;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ALCCapabilities(FunctionProviderLocal functionProviderLocal, long j, Set<String> set, IntFunction<PointerBuffer> intFunction) {
        this.device = j;
        PointerBuffer apply = intFunction.apply(31);
        this.OpenALC10 = check_ALC10(functionProviderLocal, j, apply, set);
        this.OpenALC11 = check_ALC11(functionProviderLocal, j, apply, set);
        this.OpenALC_SOFT_loopback_bformat = set.contains("OpenALC_SOFT_loopback_bformat");
        this.ALC_ENUMERATE_ALL_EXT = set.contains("ALC_ENUMERATE_ALL_EXT");
        this.ALC_ENUMERATION_EXT = set.contains("ALC_ENUMERATION_EXT");
        this.ALC_EXT_CAPTURE = check_EXT_CAPTURE(functionProviderLocal, j, apply, set);
        this.ALC_EXT_DEDICATED = set.contains("ALC_EXT_DEDICATED");
        this.ALC_EXT_DEFAULT_FILTER_ORDER = set.contains("ALC_EXT_DEFAULT_FILTER_ORDER");
        this.ALC_EXT_disconnect = set.contains("ALC_EXT_disconnect");
        this.ALC_EXT_EFX = set.contains("ALC_EXT_EFX");
        this.ALC_EXT_thread_local_context = check_EXT_thread_local_context(functionProviderLocal, j, apply, set);
        this.ALC_LOKI_audio_channel = set.contains("ALC_LOKI_audio_channel");
        this.ALC_SOFT_device_clock = check_SOFT_device_clock(functionProviderLocal, j, apply, set);
        this.ALC_SOFT_HRTF = check_SOFT_HRTF(functionProviderLocal, j, apply, set);
        this.ALC_SOFT_loopback = check_SOFT_loopback(functionProviderLocal, j, apply, set);
        this.ALC_SOFT_output_limiter = set.contains("ALC_SOFT_output_limiter");
        this.ALC_SOFT_output_mode = set.contains("ALC_SOFT_output_mode");
        this.ALC_SOFT_pause_device = check_SOFT_pause_device(functionProviderLocal, j, apply, set);
        this.ALC_SOFT_reopen_device = check_SOFT_reopen_device(functionProviderLocal, j, apply, set);
        this.alcOpenDevice = apply.get(0);
        this.alcCloseDevice = apply.get(1);
        this.alcCreateContext = apply.get(2);
        this.alcMakeContextCurrent = apply.get(3);
        this.alcProcessContext = apply.get(4);
        this.alcSuspendContext = apply.get(5);
        this.alcDestroyContext = apply.get(6);
        this.alcGetCurrentContext = apply.get(7);
        this.alcGetContextsDevice = apply.get(8);
        this.alcIsExtensionPresent = apply.get(9);
        this.alcGetProcAddress = apply.get(10);
        this.alcGetEnumValue = apply.get(11);
        this.alcGetError = apply.get(12);
        this.alcGetString = apply.get(13);
        this.alcGetIntegerv = apply.get(14);
        this.alcCaptureOpenDevice = apply.get(15);
        this.alcCaptureCloseDevice = apply.get(16);
        this.alcCaptureStart = apply.get(17);
        this.alcCaptureStop = apply.get(18);
        this.alcCaptureSamples = apply.get(19);
        this.alcSetThreadContext = apply.get(20);
        this.alcGetThreadContext = apply.get(21);
        this.alcGetInteger64vSOFT = apply.get(22);
        this.alcGetStringiSOFT = apply.get(23);
        this.alcResetDeviceSOFT = apply.get(24);
        this.alcLoopbackOpenDeviceSOFT = apply.get(25);
        this.alcIsRenderFormatSupportedSOFT = apply.get(26);
        this.alcRenderSamplesSOFT = apply.get(27);
        this.alcDevicePauseSOFT = apply.get(28);
        this.alcDeviceResumeSOFT = apply.get(29);
        this.alcReopenDeviceSOFT = apply.get(30);
        this.addresses = ThreadLocalUtil.setupAddressBuffer(apply);
    }

    public final PointerBuffer getAddressBuffer() {
        return this.addresses;
    }

    private static boolean check_ALC10(FunctionProviderLocal functionProviderLocal, long j, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("OpenALC10")) {
            return Checks.checkFunctions(functionProviderLocal, j, pointerBuffer, new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14}, "alcOpenDevice", "alcCloseDevice", "alcCreateContext", "alcMakeContextCurrent", "alcProcessContext", "alcSuspendContext", "alcDestroyContext", "alcGetCurrentContext", "alcGetContextsDevice", "alcIsExtensionPresent", "alcGetProcAddress", "alcGetEnumValue", "alcGetError", "alcGetString", "alcGetIntegerv") || Checks.reportMissing("ALC", "OpenALC10");
        }
        return false;
    }

    private static boolean check_ALC11(FunctionProviderLocal functionProviderLocal, long j, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("OpenALC11")) {
            return Checks.checkFunctions(functionProviderLocal, j, pointerBuffer, new int[]{15, 16, 17, 18, 19}, "alcCaptureOpenDevice", "alcCaptureCloseDevice", "alcCaptureStart", "alcCaptureStop", "alcCaptureSamples") || Checks.reportMissing("ALC", "OpenALC11");
        }
        return false;
    }

    private static boolean check_EXT_CAPTURE(FunctionProviderLocal functionProviderLocal, long j, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("ALC_EXT_CAPTURE")) {
            return Checks.checkFunctions(functionProviderLocal, j, pointerBuffer, new int[]{15, 16, 17, 18, 19}, "alcCaptureOpenDevice", "alcCaptureCloseDevice", "alcCaptureStart", "alcCaptureStop", "alcCaptureSamples") || Checks.reportMissing("ALC", "ALC_EXT_CAPTURE");
        }
        return false;
    }

    private static boolean check_EXT_thread_local_context(FunctionProviderLocal functionProviderLocal, long j, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("ALC_EXT_thread_local_context")) {
            return Checks.checkFunctions(functionProviderLocal, j, pointerBuffer, new int[]{20, 21}, "alcSetThreadContext", "alcGetThreadContext") || Checks.reportMissing("ALC", "ALC_EXT_thread_local_context");
        }
        return false;
    }

    private static boolean check_SOFT_device_clock(FunctionProviderLocal functionProviderLocal, long j, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("ALC_SOFT_device_clock")) {
            return Checks.checkFunctions(functionProviderLocal, j, pointerBuffer, new int[]{22}, "alcGetInteger64vSOFT") || Checks.reportMissing("ALC", "ALC_SOFT_device_clock");
        }
        return false;
    }

    private static boolean check_SOFT_HRTF(FunctionProviderLocal functionProviderLocal, long j, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("ALC_SOFT_HRTF")) {
            return Checks.checkFunctions(functionProviderLocal, j, pointerBuffer, new int[]{23, 24}, "alcGetStringiSOFT", "alcResetDeviceSOFT") || Checks.reportMissing("ALC", "ALC_SOFT_HRTF");
        }
        return false;
    }

    private static boolean check_SOFT_loopback(FunctionProviderLocal functionProviderLocal, long j, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("ALC_SOFT_loopback")) {
            return Checks.checkFunctions(functionProviderLocal, j, pointerBuffer, new int[]{25, 26, 27}, "alcLoopbackOpenDeviceSOFT", "alcIsRenderFormatSupportedSOFT", "alcRenderSamplesSOFT") || Checks.reportMissing("ALC", "ALC_SOFT_loopback");
        }
        return false;
    }

    private static boolean check_SOFT_pause_device(FunctionProviderLocal functionProviderLocal, long j, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("ALC_SOFT_pause_device")) {
            return Checks.checkFunctions(functionProviderLocal, j, pointerBuffer, new int[]{28, 29}, "alcDevicePauseSOFT", "alcDeviceResumeSOFT") || Checks.reportMissing("ALC", "ALC_SOFT_pause_device");
        }
        return false;
    }

    private static boolean check_SOFT_reopen_device(FunctionProviderLocal functionProviderLocal, long j, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("ALC_SOFT_reopen_device")) {
            return Checks.checkFunctions(functionProviderLocal, j, pointerBuffer, new int[]{30}, "alcReopenDeviceSOFT") || Checks.reportMissing("ALC", "ALC_SOFT_reopen_device");
        }
        return false;
    }
}
