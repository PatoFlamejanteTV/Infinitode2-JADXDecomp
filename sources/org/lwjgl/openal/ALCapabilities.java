package org.lwjgl.openal;

import java.util.Set;
import java.util.function.IntFunction;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.FunctionProvider;
import org.lwjgl.system.ThreadLocalUtil;

/* loaded from: infinitode-2.jar:org/lwjgl/openal/ALCapabilities.class */
public final class ALCapabilities {
    public final long alGetError;
    public final long alEnable;
    public final long alDisable;
    public final long alIsEnabled;
    public final long alGetBoolean;
    public final long alGetInteger;
    public final long alGetFloat;
    public final long alGetDouble;
    public final long alGetBooleanv;
    public final long alGetIntegerv;
    public final long alGetFloatv;
    public final long alGetDoublev;
    public final long alGetString;
    public final long alDistanceModel;
    public final long alDopplerFactor;
    public final long alDopplerVelocity;
    public final long alListenerf;
    public final long alListeneri;
    public final long alListener3f;
    public final long alListenerfv;
    public final long alGetListenerf;
    public final long alGetListeneri;
    public final long alGetListener3f;
    public final long alGetListenerfv;
    public final long alGenSources;
    public final long alDeleteSources;
    public final long alIsSource;
    public final long alSourcef;
    public final long alSource3f;
    public final long alSourcefv;
    public final long alSourcei;
    public final long alGetSourcef;
    public final long alGetSource3f;
    public final long alGetSourcefv;
    public final long alGetSourcei;
    public final long alGetSourceiv;
    public final long alSourceQueueBuffers;
    public final long alSourceUnqueueBuffers;
    public final long alSourcePlay;
    public final long alSourcePause;
    public final long alSourceStop;
    public final long alSourceRewind;
    public final long alSourcePlayv;
    public final long alSourcePausev;
    public final long alSourceStopv;
    public final long alSourceRewindv;
    public final long alGenBuffers;
    public final long alDeleteBuffers;
    public final long alIsBuffer;
    public final long alGetBufferf;
    public final long alGetBufferi;
    public final long alBufferData;
    public final long alGetEnumValue;
    public final long alGetProcAddress;
    public final long alIsExtensionPresent;
    public final long alListener3i;
    public final long alGetListeneriv;
    public final long alSource3i;
    public final long alListeneriv;
    public final long alSourceiv;
    public final long alBufferf;
    public final long alBuffer3f;
    public final long alBufferfv;
    public final long alBufferi;
    public final long alBuffer3i;
    public final long alBufferiv;
    public final long alGetBufferiv;
    public final long alGetBufferfv;
    public final long alSpeedOfSound;
    public final long alGenEffects;
    public final long alDeleteEffects;
    public final long alIsEffect;
    public final long alEffecti;
    public final long alEffectiv;
    public final long alEffectf;
    public final long alEffectfv;
    public final long alGetEffecti;
    public final long alGetEffectiv;
    public final long alGetEffectf;
    public final long alGetEffectfv;
    public final long alGenFilters;
    public final long alDeleteFilters;
    public final long alIsFilter;
    public final long alFilteri;
    public final long alFilteriv;
    public final long alFilterf;
    public final long alFilterfv;
    public final long alGetFilteri;
    public final long alGetFilteriv;
    public final long alGetFilterf;
    public final long alGetFilterfv;
    public final long alGenAuxiliaryEffectSlots;
    public final long alDeleteAuxiliaryEffectSlots;
    public final long alIsAuxiliaryEffectSlot;
    public final long alAuxiliaryEffectSloti;
    public final long alAuxiliaryEffectSlotiv;
    public final long alAuxiliaryEffectSlotf;
    public final long alAuxiliaryEffectSlotfv;
    public final long alGetAuxiliaryEffectSloti;
    public final long alGetAuxiliaryEffectSlotiv;
    public final long alGetAuxiliaryEffectSlotf;
    public final long alGetAuxiliaryEffectSlotfv;
    public final long alBufferDataStatic;
    public final long alBufferSamplesSOFT;
    public final long alBufferSubSamplesSOFT;
    public final long alGetBufferSamplesSOFT;
    public final long alIsBufferFormatSupportedSOFT;
    public final long alBufferSubDataSOFT;
    public final long alBufferCallbackSOFT;
    public final long alGetBufferPtrSOFT;
    public final long alGetBuffer3PtrSOFT;
    public final long alGetBufferPtrvSOFT;
    public final long alDeferUpdatesSOFT;
    public final long alProcessUpdatesSOFT;
    public final long alEventControlSOFT;
    public final long alEventCallbackSOFT;
    public final long alGetPointerSOFT;
    public final long alGetPointervSOFT;
    public final long alSourcedSOFT;
    public final long alSource3dSOFT;
    public final long alSourcedvSOFT;
    public final long alGetSourcedSOFT;
    public final long alGetSource3dSOFT;
    public final long alGetSourcedvSOFT;
    public final long alSourcei64SOFT;
    public final long alSource3i64SOFT;
    public final long alSourcei64vSOFT;
    public final long alGetSourcei64SOFT;
    public final long alGetSource3i64SOFT;
    public final long alGetSourcei64vSOFT;
    public final long alGetStringiSOFT;
    public final long alSourcePlayAtTimeSOFT;
    public final long alSourcePlayAtTimevSOFT;
    public final boolean OpenAL10;
    public final boolean OpenAL11;
    public final boolean AL_EXT_ALAW;
    public final boolean AL_EXT_BFORMAT;
    public final boolean AL_EXT_DOUBLE;
    public final boolean ALC_EXT_EFX;
    public final boolean AL_EXT_EXPONENT_DISTANCE;
    public final boolean AL_EXT_FLOAT32;
    public final boolean AL_EXT_IMA4;
    public final boolean AL_EXT_LINEAR_DISTANCE;
    public final boolean AL_EXT_MCFORMATS;
    public final boolean AL_EXT_MULAW;
    public final boolean AL_EXT_MULAW_BFORMAT;
    public final boolean AL_EXT_MULAW_MCFORMATS;
    public final boolean AL_EXT_OFFSET;
    public final boolean AL_EXT_source_distance_model;
    public final boolean AL_EXT_SOURCE_RADIUS;
    public final boolean AL_EXT_STATIC_BUFFER;
    public final boolean AL_EXT_STEREO_ANGLES;
    public final boolean AL_EXT_vorbis;
    public final boolean AL_LOKI_IMA_ADPCM;
    public final boolean AL_LOKI_quadriphonic;
    public final boolean AL_LOKI_WAVE_format;
    public final boolean AL_SOFT_bformat_ex;
    public final boolean AL_SOFT_block_alignment;
    public final boolean AL_SOFT_buffer_length_query;
    public final boolean AL_SOFT_buffer_samples;
    public final boolean AL_SOFT_buffer_sub_data;
    public final boolean AL_SOFT_callback_buffer;
    public final boolean AL_SOFT_deferred_updates;
    public final boolean AL_SOFT_direct_channels;
    public final boolean AL_SOFT_direct_channels_remix;
    public final boolean AL_SOFT_effect_target;
    public final boolean AL_SOFT_events;
    public final boolean AL_SOFT_gain_clamp_ex;
    public final boolean AL_SOFT_loop_points;
    public final boolean AL_SOFT_MSADPCM;
    public final boolean AL_SOFT_source_latency;
    public final boolean AL_SOFT_source_length;
    public final boolean AL_SOFT_source_resampler;
    public final boolean AL_SOFT_source_spatialize;
    public final boolean AL_SOFT_source_start_delay;
    public final boolean AL_SOFT_UHJ;
    public final boolean AL_SOFT_UHJ_ex;
    public final boolean AL_SOFTX_hold_on_disconnect;
    final PointerBuffer addresses;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ALCapabilities(FunctionProvider functionProvider, Set<String> set, IntFunction<PointerBuffer> intFunction) {
        PointerBuffer apply = intFunction.apply(133);
        this.OpenAL10 = check_AL10(functionProvider, apply, set);
        this.OpenAL11 = check_AL11(functionProvider, apply, set);
        this.AL_EXT_ALAW = set.contains("AL_EXT_ALAW");
        this.AL_EXT_BFORMAT = set.contains("AL_EXT_BFORMAT");
        this.AL_EXT_DOUBLE = set.contains("AL_EXT_DOUBLE");
        this.ALC_EXT_EFX = check_EXT_EFX(functionProvider, apply, set);
        this.AL_EXT_EXPONENT_DISTANCE = set.contains("AL_EXT_EXPONENT_DISTANCE");
        this.AL_EXT_FLOAT32 = set.contains("AL_EXT_FLOAT32");
        this.AL_EXT_IMA4 = set.contains("AL_EXT_IMA4");
        this.AL_EXT_LINEAR_DISTANCE = set.contains("AL_EXT_LINEAR_DISTANCE");
        this.AL_EXT_MCFORMATS = set.contains("AL_EXT_MCFORMATS");
        this.AL_EXT_MULAW = set.contains("AL_EXT_MULAW");
        this.AL_EXT_MULAW_BFORMAT = set.contains("AL_EXT_MULAW_BFORMAT");
        this.AL_EXT_MULAW_MCFORMATS = set.contains("AL_EXT_MULAW_MCFORMATS");
        this.AL_EXT_OFFSET = set.contains("AL_EXT_OFFSET");
        this.AL_EXT_source_distance_model = set.contains("AL_EXT_source_distance_model");
        this.AL_EXT_SOURCE_RADIUS = set.contains("AL_EXT_SOURCE_RADIUS");
        this.AL_EXT_STATIC_BUFFER = check_EXT_STATIC_BUFFER(functionProvider, apply, set);
        this.AL_EXT_STEREO_ANGLES = set.contains("AL_EXT_STEREO_ANGLES");
        this.AL_EXT_vorbis = set.contains("AL_EXT_vorbis");
        this.AL_LOKI_IMA_ADPCM = set.contains("AL_LOKI_IMA_ADPCM");
        this.AL_LOKI_quadriphonic = set.contains("AL_LOKI_quadriphonic");
        this.AL_LOKI_WAVE_format = set.contains("AL_LOKI_WAVE_format");
        this.AL_SOFT_bformat_ex = set.contains("AL_SOFT_bformat_ex");
        this.AL_SOFT_block_alignment = set.contains("AL_SOFT_block_alignment");
        this.AL_SOFT_buffer_length_query = set.contains("AL_SOFT_buffer_length_query");
        this.AL_SOFT_buffer_samples = check_SOFT_buffer_samples(functionProvider, apply, set);
        this.AL_SOFT_buffer_sub_data = check_SOFT_buffer_sub_data(functionProvider, apply, set);
        this.AL_SOFT_callback_buffer = check_SOFT_callback_buffer(functionProvider, apply, set);
        this.AL_SOFT_deferred_updates = check_SOFT_deferred_updates(functionProvider, apply, set);
        this.AL_SOFT_direct_channels = set.contains("AL_SOFT_direct_channels");
        this.AL_SOFT_direct_channels_remix = set.contains("AL_SOFT_direct_channels_remix");
        this.AL_SOFT_effect_target = set.contains("AL_SOFT_effect_target");
        this.AL_SOFT_events = check_SOFT_events(functionProvider, apply, set);
        this.AL_SOFT_gain_clamp_ex = set.contains("AL_SOFT_gain_clamp_ex");
        this.AL_SOFT_loop_points = set.contains("AL_SOFT_loop_points");
        this.AL_SOFT_MSADPCM = set.contains("AL_SOFT_MSADPCM");
        this.AL_SOFT_source_latency = check_SOFT_source_latency(functionProvider, apply, set);
        this.AL_SOFT_source_length = set.contains("AL_SOFT_source_length");
        this.AL_SOFT_source_resampler = check_SOFT_source_resampler(functionProvider, apply, set);
        this.AL_SOFT_source_spatialize = set.contains("AL_SOFT_source_spatialize");
        this.AL_SOFT_source_start_delay = check_SOFT_source_start_delay(functionProvider, apply, set);
        this.AL_SOFT_UHJ = set.contains("AL_SOFT_UHJ");
        this.AL_SOFT_UHJ_ex = set.contains("AL_SOFT_UHJ_ex");
        this.AL_SOFTX_hold_on_disconnect = set.contains("AL_SOFTX_hold_on_disconnect");
        this.alGetError = apply.get(0);
        this.alEnable = apply.get(1);
        this.alDisable = apply.get(2);
        this.alIsEnabled = apply.get(3);
        this.alGetBoolean = apply.get(4);
        this.alGetInteger = apply.get(5);
        this.alGetFloat = apply.get(6);
        this.alGetDouble = apply.get(7);
        this.alGetBooleanv = apply.get(8);
        this.alGetIntegerv = apply.get(9);
        this.alGetFloatv = apply.get(10);
        this.alGetDoublev = apply.get(11);
        this.alGetString = apply.get(12);
        this.alDistanceModel = apply.get(13);
        this.alDopplerFactor = apply.get(14);
        this.alDopplerVelocity = apply.get(15);
        this.alListenerf = apply.get(16);
        this.alListeneri = apply.get(17);
        this.alListener3f = apply.get(18);
        this.alListenerfv = apply.get(19);
        this.alGetListenerf = apply.get(20);
        this.alGetListeneri = apply.get(21);
        this.alGetListener3f = apply.get(22);
        this.alGetListenerfv = apply.get(23);
        this.alGenSources = apply.get(24);
        this.alDeleteSources = apply.get(25);
        this.alIsSource = apply.get(26);
        this.alSourcef = apply.get(27);
        this.alSource3f = apply.get(28);
        this.alSourcefv = apply.get(29);
        this.alSourcei = apply.get(30);
        this.alGetSourcef = apply.get(31);
        this.alGetSource3f = apply.get(32);
        this.alGetSourcefv = apply.get(33);
        this.alGetSourcei = apply.get(34);
        this.alGetSourceiv = apply.get(35);
        this.alSourceQueueBuffers = apply.get(36);
        this.alSourceUnqueueBuffers = apply.get(37);
        this.alSourcePlay = apply.get(38);
        this.alSourcePause = apply.get(39);
        this.alSourceStop = apply.get(40);
        this.alSourceRewind = apply.get(41);
        this.alSourcePlayv = apply.get(42);
        this.alSourcePausev = apply.get(43);
        this.alSourceStopv = apply.get(44);
        this.alSourceRewindv = apply.get(45);
        this.alGenBuffers = apply.get(46);
        this.alDeleteBuffers = apply.get(47);
        this.alIsBuffer = apply.get(48);
        this.alGetBufferf = apply.get(49);
        this.alGetBufferi = apply.get(50);
        this.alBufferData = apply.get(51);
        this.alGetEnumValue = apply.get(52);
        this.alGetProcAddress = apply.get(53);
        this.alIsExtensionPresent = apply.get(54);
        this.alListener3i = apply.get(55);
        this.alGetListeneriv = apply.get(56);
        this.alSource3i = apply.get(57);
        this.alListeneriv = apply.get(58);
        this.alSourceiv = apply.get(59);
        this.alBufferf = apply.get(60);
        this.alBuffer3f = apply.get(61);
        this.alBufferfv = apply.get(62);
        this.alBufferi = apply.get(63);
        this.alBuffer3i = apply.get(64);
        this.alBufferiv = apply.get(65);
        this.alGetBufferiv = apply.get(66);
        this.alGetBufferfv = apply.get(67);
        this.alSpeedOfSound = apply.get(68);
        this.alGenEffects = apply.get(69);
        this.alDeleteEffects = apply.get(70);
        this.alIsEffect = apply.get(71);
        this.alEffecti = apply.get(72);
        this.alEffectiv = apply.get(73);
        this.alEffectf = apply.get(74);
        this.alEffectfv = apply.get(75);
        this.alGetEffecti = apply.get(76);
        this.alGetEffectiv = apply.get(77);
        this.alGetEffectf = apply.get(78);
        this.alGetEffectfv = apply.get(79);
        this.alGenFilters = apply.get(80);
        this.alDeleteFilters = apply.get(81);
        this.alIsFilter = apply.get(82);
        this.alFilteri = apply.get(83);
        this.alFilteriv = apply.get(84);
        this.alFilterf = apply.get(85);
        this.alFilterfv = apply.get(86);
        this.alGetFilteri = apply.get(87);
        this.alGetFilteriv = apply.get(88);
        this.alGetFilterf = apply.get(89);
        this.alGetFilterfv = apply.get(90);
        this.alGenAuxiliaryEffectSlots = apply.get(91);
        this.alDeleteAuxiliaryEffectSlots = apply.get(92);
        this.alIsAuxiliaryEffectSlot = apply.get(93);
        this.alAuxiliaryEffectSloti = apply.get(94);
        this.alAuxiliaryEffectSlotiv = apply.get(95);
        this.alAuxiliaryEffectSlotf = apply.get(96);
        this.alAuxiliaryEffectSlotfv = apply.get(97);
        this.alGetAuxiliaryEffectSloti = apply.get(98);
        this.alGetAuxiliaryEffectSlotiv = apply.get(99);
        this.alGetAuxiliaryEffectSlotf = apply.get(100);
        this.alGetAuxiliaryEffectSlotfv = apply.get(101);
        this.alBufferDataStatic = apply.get(102);
        this.alBufferSamplesSOFT = apply.get(103);
        this.alBufferSubSamplesSOFT = apply.get(104);
        this.alGetBufferSamplesSOFT = apply.get(105);
        this.alIsBufferFormatSupportedSOFT = apply.get(106);
        this.alBufferSubDataSOFT = apply.get(107);
        this.alBufferCallbackSOFT = apply.get(108);
        this.alGetBufferPtrSOFT = apply.get(109);
        this.alGetBuffer3PtrSOFT = apply.get(110);
        this.alGetBufferPtrvSOFT = apply.get(111);
        this.alDeferUpdatesSOFT = apply.get(112);
        this.alProcessUpdatesSOFT = apply.get(113);
        this.alEventControlSOFT = apply.get(114);
        this.alEventCallbackSOFT = apply.get(115);
        this.alGetPointerSOFT = apply.get(116);
        this.alGetPointervSOFT = apply.get(117);
        this.alSourcedSOFT = apply.get(118);
        this.alSource3dSOFT = apply.get(119);
        this.alSourcedvSOFT = apply.get(120);
        this.alGetSourcedSOFT = apply.get(121);
        this.alGetSource3dSOFT = apply.get(122);
        this.alGetSourcedvSOFT = apply.get(123);
        this.alSourcei64SOFT = apply.get(124);
        this.alSource3i64SOFT = apply.get(125);
        this.alSourcei64vSOFT = apply.get(126);
        this.alGetSourcei64SOFT = apply.get(127);
        this.alGetSource3i64SOFT = apply.get(128);
        this.alGetSourcei64vSOFT = apply.get(129);
        this.alGetStringiSOFT = apply.get(130);
        this.alSourcePlayAtTimeSOFT = apply.get(131);
        this.alSourcePlayAtTimevSOFT = apply.get(132);
        this.addresses = ThreadLocalUtil.setupAddressBuffer(apply);
    }

    public final PointerBuffer getAddressBuffer() {
        return this.addresses;
    }

    private static boolean check_AL10(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("OpenAL10")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54}, "alGetError", "alEnable", "alDisable", "alIsEnabled", "alGetBoolean", "alGetInteger", "alGetFloat", "alGetDouble", "alGetBooleanv", "alGetIntegerv", "alGetFloatv", "alGetDoublev", "alGetString", "alDistanceModel", "alDopplerFactor", "alDopplerVelocity", "alListenerf", "alListeneri", "alListener3f", "alListenerfv", "alGetListenerf", "alGetListeneri", "alGetListener3f", "alGetListenerfv", "alGenSources", "alDeleteSources", "alIsSource", "alSourcef", "alSource3f", "alSourcefv", "alSourcei", "alGetSourcef", "alGetSource3f", "alGetSourcefv", "alGetSourcei", "alGetSourceiv", "alSourceQueueBuffers", "alSourceUnqueueBuffers", "alSourcePlay", "alSourcePause", "alSourceStop", "alSourceRewind", "alSourcePlayv", "alSourcePausev", "alSourceStopv", "alSourceRewindv", "alGenBuffers", "alDeleteBuffers", "alIsBuffer", "alGetBufferf", "alGetBufferi", "alBufferData", "alGetEnumValue", "alGetProcAddress", "alIsExtensionPresent") || Checks.reportMissing("AL", "OpenAL10");
        }
        return false;
    }

    private static boolean check_AL11(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("OpenAL11")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68}, "alListener3i", "alGetListeneriv", "alSource3i", "alListeneriv", "alSourceiv", "alBufferf", "alBuffer3f", "alBufferfv", "alBufferi", "alBuffer3i", "alBufferiv", "alGetBufferiv", "alGetBufferfv", "alSpeedOfSound") || Checks.reportMissing("AL", "OpenAL11");
        }
        return false;
    }

    private static boolean check_EXT_EFX(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("ALC_EXT_EFX")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101}, "alGenEffects", "alDeleteEffects", "alIsEffect", "alEffecti", "alEffectiv", "alEffectf", "alEffectfv", "alGetEffecti", "alGetEffectiv", "alGetEffectf", "alGetEffectfv", "alGenFilters", "alDeleteFilters", "alIsFilter", "alFilteri", "alFilteriv", "alFilterf", "alFilterfv", "alGetFilteri", "alGetFilteriv", "alGetFilterf", "alGetFilterfv", "alGenAuxiliaryEffectSlots", "alDeleteAuxiliaryEffectSlots", "alIsAuxiliaryEffectSlot", "alAuxiliaryEffectSloti", "alAuxiliaryEffectSlotiv", "alAuxiliaryEffectSlotf", "alAuxiliaryEffectSlotfv", "alGetAuxiliaryEffectSloti", "alGetAuxiliaryEffectSlotiv", "alGetAuxiliaryEffectSlotf", "alGetAuxiliaryEffectSlotfv") || Checks.reportMissing("AL", "ALC_EXT_EFX");
        }
        return false;
    }

    private static boolean check_EXT_STATIC_BUFFER(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("AL_EXT_STATIC_BUFFER")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{102}, "alBufferDataStatic") || Checks.reportMissing("AL", "AL_EXT_STATIC_BUFFER");
        }
        return false;
    }

    private static boolean check_SOFT_buffer_samples(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("AL_SOFT_buffer_samples")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{103, 104, 105, 106}, "alBufferSamplesSOFT", "alBufferSubSamplesSOFT", "alGetBufferSamplesSOFT", "alIsBufferFormatSupportedSOFT") || Checks.reportMissing("AL", "AL_SOFT_buffer_samples");
        }
        return false;
    }

    private static boolean check_SOFT_buffer_sub_data(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("AL_SOFT_buffer_sub_data")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{107}, "alBufferSubDataSOFT") || Checks.reportMissing("AL", "AL_SOFT_buffer_sub_data");
        }
        return false;
    }

    private static boolean check_SOFT_callback_buffer(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("AL_SOFT_callback_buffer")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{108, 109, 110, 111}, "alBufferCallbackSOFT", "alGetBufferPtrSOFT", "alGetBuffer3PtrSOFT", "alGetBufferPtrvSOFT") || Checks.reportMissing("AL", "AL_SOFT_callback_buffer");
        }
        return false;
    }

    private static boolean check_SOFT_deferred_updates(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("AL_SOFT_deferred_updates")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{112, 113}, "alDeferUpdatesSOFT", "alProcessUpdatesSOFT") || Checks.reportMissing("AL", "AL_SOFT_deferred_updates");
        }
        return false;
    }

    private static boolean check_SOFT_events(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("AL_SOFT_events")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{114, 115, 116, 117}, "alEventControlSOFT", "alEventCallbackSOFT", "alGetPointerSOFT", "alGetPointervSOFT") || Checks.reportMissing("AL", "AL_SOFT_events");
        }
        return false;
    }

    private static boolean check_SOFT_source_latency(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("AL_SOFT_source_latency")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129}, "alSourcedSOFT", "alSource3dSOFT", "alSourcedvSOFT", "alGetSourcedSOFT", "alGetSource3dSOFT", "alGetSourcedvSOFT", "alSourcei64SOFT", "alSource3i64SOFT", "alSourcei64vSOFT", "alGetSourcei64SOFT", "alGetSource3i64SOFT", "alGetSourcei64vSOFT") || Checks.reportMissing("AL", "AL_SOFT_source_latency");
        }
        return false;
    }

    private static boolean check_SOFT_source_resampler(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("AL_SOFT_source_resampler")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{130}, "alGetStringiSOFT") || Checks.reportMissing("AL", "AL_SOFT_source_resampler");
        }
        return false;
    }

    private static boolean check_SOFT_source_start_delay(FunctionProvider functionProvider, PointerBuffer pointerBuffer, Set<String> set) {
        if (set.contains("AL_SOFT_source_start_delay")) {
            return Checks.checkFunctions(functionProvider, pointerBuffer, new int[]{131, 132}, "alSourcePlayAtTimeSOFT", "alSourcePlayAtTimevSOFT") || Checks.reportMissing("AL", "AL_SOFT_source_start_delay");
        }
        return false;
    }
}
