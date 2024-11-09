package org.lwjgl.openal;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/openal/AL10.class */
public class AL10 {
    public static final int AL_INVALID = -1;
    public static final int AL_NONE = 0;
    public static final int AL_FALSE = 0;
    public static final int AL_TRUE = 1;
    public static final int AL_NO_ERROR = 0;
    public static final int AL_INVALID_NAME = 40961;
    public static final int AL_INVALID_ENUM = 40962;
    public static final int AL_INVALID_VALUE = 40963;
    public static final int AL_INVALID_OPERATION = 40964;
    public static final int AL_OUT_OF_MEMORY = 40965;
    public static final int AL_DOPPLER_FACTOR = 49152;
    public static final int AL_DISTANCE_MODEL = 53248;
    public static final int AL_VENDOR = 45057;
    public static final int AL_VERSION = 45058;
    public static final int AL_RENDERER = 45059;
    public static final int AL_EXTENSIONS = 45060;
    public static final int AL_INVERSE_DISTANCE = 53249;
    public static final int AL_INVERSE_DISTANCE_CLAMPED = 53250;
    public static final int AL_SOURCE_ABSOLUTE = 513;
    public static final int AL_SOURCE_RELATIVE = 514;
    public static final int AL_POSITION = 4100;
    public static final int AL_VELOCITY = 4102;
    public static final int AL_GAIN = 4106;
    public static final int AL_CONE_INNER_ANGLE = 4097;
    public static final int AL_CONE_OUTER_ANGLE = 4098;
    public static final int AL_PITCH = 4099;
    public static final int AL_DIRECTION = 4101;
    public static final int AL_LOOPING = 4103;
    public static final int AL_BUFFER = 4105;
    public static final int AL_SOURCE_STATE = 4112;
    public static final int AL_CONE_OUTER_GAIN = 4130;
    public static final int AL_SOURCE_TYPE = 4135;
    public static final int AL_INITIAL = 4113;
    public static final int AL_PLAYING = 4114;
    public static final int AL_PAUSED = 4115;
    public static final int AL_STOPPED = 4116;
    public static final int AL_ORIENTATION = 4111;
    public static final int AL_BUFFERS_QUEUED = 4117;
    public static final int AL_BUFFERS_PROCESSED = 4118;
    public static final int AL_MIN_GAIN = 4109;
    public static final int AL_MAX_GAIN = 4110;
    public static final int AL_REFERENCE_DISTANCE = 4128;
    public static final int AL_ROLLOFF_FACTOR = 4129;
    public static final int AL_MAX_DISTANCE = 4131;
    public static final int AL_FREQUENCY = 8193;
    public static final int AL_BITS = 8194;
    public static final int AL_CHANNELS = 8195;
    public static final int AL_SIZE = 8196;
    public static final int AL_FORMAT_MONO8 = 4352;
    public static final int AL_FORMAT_MONO16 = 4353;
    public static final int AL_FORMAT_STEREO8 = 4354;
    public static final int AL_FORMAT_STEREO16 = 4355;
    public static final int AL_UNUSED = 8208;
    public static final int AL_PENDING = 8209;
    public static final int AL_PROCESSED = 8210;

    /* JADX INFO: Access modifiers changed from: protected */
    public AL10() {
        throw new UnsupportedOperationException();
    }

    @NativeType("ALenum")
    public static int alGetError() {
        return JNI.invokeI(AL.getICD().alGetError);
    }

    @NativeType("ALvoid")
    public static void alEnable(@NativeType("ALenum") int i) {
        JNI.invokeV(i, AL.getICD().alEnable);
    }

    @NativeType("ALvoid")
    public static void alDisable(@NativeType("ALenum") int i) {
        JNI.invokeV(i, AL.getICD().alDisable);
    }

    @NativeType("ALboolean")
    public static boolean alIsEnabled(@NativeType("ALenum") int i) {
        return JNI.invokeZ(i, AL.getICD().alIsEnabled);
    }

    @NativeType("ALboolean")
    public static boolean alGetBoolean(@NativeType("ALenum") int i) {
        return JNI.invokeZ(i, AL.getICD().alGetBoolean);
    }

    @NativeType("ALint")
    public static int alGetInteger(@NativeType("ALenum") int i) {
        return JNI.invokeI(i, AL.getICD().alGetInteger);
    }

    @NativeType("ALfloat")
    public static float alGetFloat(@NativeType("ALenum") int i) {
        return JNI.invokeF(i, AL.getICD().alGetFloat);
    }

    @NativeType("ALdouble")
    public static double alGetDouble(@NativeType("ALenum") int i) {
        return JNI.invokeD(i, AL.getICD().alGetDouble);
    }

    public static void nalGetBooleanv(int i, long j) {
        JNI.invokePV(i, j, AL.getICD().alGetBooleanv);
    }

    @NativeType("ALvoid")
    public static void alGetBooleanv(@NativeType("ALenum") int i, @NativeType("ALboolean *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, 1);
        }
        nalGetBooleanv(i, MemoryUtil.memAddress(byteBuffer));
    }

    public static void nalGetIntegerv(int i, long j) {
        JNI.invokePV(i, j, AL.getICD().alGetIntegerv);
    }

    @NativeType("ALvoid")
    public static void alGetIntegerv(@NativeType("ALenum") int i, @NativeType("ALint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nalGetIntegerv(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void nalGetFloatv(int i, long j) {
        JNI.invokePV(i, j, AL.getICD().alGetFloatv);
    }

    @NativeType("ALvoid")
    public static void alGetFloatv(@NativeType("ALenum") int i, @NativeType("ALfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nalGetFloatv(i, MemoryUtil.memAddress(floatBuffer));
    }

    public static void nalGetDoublev(int i, long j) {
        JNI.invokePV(i, j, AL.getICD().alGetDoublev);
    }

    @NativeType("ALvoid")
    public static void alGetDoublev(@NativeType("ALenum") int i, @NativeType("ALdouble *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 1);
        }
        nalGetDoublev(i, MemoryUtil.memAddress(doubleBuffer));
    }

    public static long nalGetString(int i) {
        return JNI.invokeP(i, AL.getICD().alGetString);
    }

    @NativeType("ALchar const *")
    public static String alGetString(@NativeType("ALenum") int i) {
        return MemoryUtil.memUTF8Safe(nalGetString(i));
    }

    @NativeType("ALvoid")
    public static void alDistanceModel(@NativeType("ALenum") int i) {
        JNI.invokeV(i, AL.getICD().alDistanceModel);
    }

    @NativeType("ALvoid")
    public static void alDopplerFactor(@NativeType("ALfloat") float f) {
        JNI.invokeV(f, AL.getICD().alDopplerFactor);
    }

    @NativeType("ALvoid")
    public static void alDopplerVelocity(@NativeType("ALfloat") float f) {
        JNI.invokeV(f, AL.getICD().alDopplerVelocity);
    }

    @NativeType("ALvoid")
    public static void alListenerf(@NativeType("ALenum") int i, @NativeType("ALfloat") float f) {
        JNI.invokeV(i, f, AL.getICD().alListenerf);
    }

    @NativeType("ALvoid")
    public static void alListeneri(@NativeType("ALenum") int i, @NativeType("ALint") int i2) {
        JNI.invokeV(i, i2, AL.getICD().alListeneri);
    }

    @NativeType("ALvoid")
    public static void alListener3f(@NativeType("ALenum") int i, @NativeType("ALfloat") float f, @NativeType("ALfloat") float f2, @NativeType("ALfloat") float f3) {
        JNI.invokeV(i, f, f2, f3, AL.getICD().alListener3f);
    }

    public static void nalListenerfv(int i, long j) {
        JNI.invokePV(i, j, AL.getICD().alListenerfv);
    }

    @NativeType("ALvoid")
    public static void alListenerfv(@NativeType("ALenum") int i, @NativeType("ALfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nalListenerfv(i, MemoryUtil.memAddress(floatBuffer));
    }

    public static void nalGetListenerf(int i, long j) {
        JNI.invokePV(i, j, AL.getICD().alGetListenerf);
    }

    @NativeType("ALvoid")
    public static void alGetListenerf(@NativeType("ALenum") int i, @NativeType("ALfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nalGetListenerf(i, MemoryUtil.memAddress(floatBuffer));
    }

    @NativeType("ALvoid")
    public static float alGetListenerf(@NativeType("ALenum") int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            FloatBuffer callocFloat = stackGet.callocFloat(1);
            nalGetListenerf(i, MemoryUtil.memAddress(callocFloat));
            return callocFloat.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void nalGetListeneri(int i, long j) {
        JNI.invokePV(i, j, AL.getICD().alGetListeneri);
    }

    @NativeType("ALvoid")
    public static void alGetListeneri(@NativeType("ALenum") int i, @NativeType("ALint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nalGetListeneri(i, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("ALvoid")
    public static int alGetListeneri(@NativeType("ALenum") int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nalGetListeneri(i, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void nalGetListener3f(int i, long j, long j2, long j3) {
        JNI.invokePPPV(i, j, j2, j3, AL.getICD().alGetListener3f);
    }

    @NativeType("ALvoid")
    public static void alGetListener3f(@NativeType("ALenum") int i, @NativeType("ALfloat *") FloatBuffer floatBuffer, @NativeType("ALfloat *") FloatBuffer floatBuffer2, @NativeType("ALfloat *") FloatBuffer floatBuffer3) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
            Checks.check((Buffer) floatBuffer2, 1);
            Checks.check((Buffer) floatBuffer3, 1);
        }
        nalGetListener3f(i, MemoryUtil.memAddress(floatBuffer), MemoryUtil.memAddress(floatBuffer2), MemoryUtil.memAddress(floatBuffer3));
    }

    public static void nalGetListenerfv(int i, long j) {
        JNI.invokePV(i, j, AL.getICD().alGetListenerfv);
    }

    @NativeType("ALvoid")
    public static void alGetListenerfv(@NativeType("ALenum") int i, @NativeType("ALfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nalGetListenerfv(i, MemoryUtil.memAddress(floatBuffer));
    }

    public static void nalGenSources(int i, long j) {
        JNI.invokePV(i, j, AL.getICD().alGenSources);
    }

    @NativeType("ALvoid")
    public static void alGenSources(@NativeType("ALuint *") IntBuffer intBuffer) {
        nalGenSources(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("ALvoid")
    public static int alGenSources() {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nalGenSources(1, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void nalDeleteSources(int i, long j) {
        JNI.invokePV(i, j, AL.getICD().alDeleteSources);
    }

    @NativeType("ALvoid")
    public static void alDeleteSources(@NativeType("ALuint *") IntBuffer intBuffer) {
        nalDeleteSources(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("ALvoid")
    public static void alDeleteSources(@NativeType("ALuint *") int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nalDeleteSources(1, MemoryUtil.memAddress(stackGet.ints(i)));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("ALboolean")
    public static boolean alIsSource(@NativeType("ALuint") int i) {
        return JNI.invokeZ(i, AL.getICD().alIsSource);
    }

    @NativeType("ALvoid")
    public static void alSourcef(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALfloat") float f) {
        JNI.invokeV(i, i2, f, AL.getICD().alSourcef);
    }

    @NativeType("ALvoid")
    public static void alSource3f(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALfloat") float f, @NativeType("ALfloat") float f2, @NativeType("ALfloat") float f3) {
        JNI.invokeV(i, i2, f, f2, f3, AL.getICD().alSource3f);
    }

    public static void nalSourcefv(int i, int i2, long j) {
        JNI.invokePV(i, i2, j, AL.getICD().alSourcefv);
    }

    @NativeType("ALvoid")
    public static void alSourcefv(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nalSourcefv(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    @NativeType("ALvoid")
    public static void alSourcei(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALint") int i3) {
        JNI.invokeV(i, i2, i3, AL.getICD().alSourcei);
    }

    public static void nalGetSourcef(int i, int i2, long j) {
        JNI.invokePV(i, i2, j, AL.getICD().alGetSourcef);
    }

    @NativeType("ALvoid")
    public static void alGetSourcef(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nalGetSourcef(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    @NativeType("ALvoid")
    public static float alGetSourcef(@NativeType("ALuint") int i, @NativeType("ALenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            FloatBuffer callocFloat = stackGet.callocFloat(1);
            nalGetSourcef(i, i2, MemoryUtil.memAddress(callocFloat));
            return callocFloat.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void nalGetSource3f(int i, int i2, long j, long j2, long j3) {
        JNI.invokePPPV(i, i2, j, j2, j3, AL.getICD().alGetSource3f);
    }

    @NativeType("ALvoid")
    public static void alGetSource3f(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALfloat *") FloatBuffer floatBuffer, @NativeType("ALfloat *") FloatBuffer floatBuffer2, @NativeType("ALfloat *") FloatBuffer floatBuffer3) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
            Checks.check((Buffer) floatBuffer2, 1);
            Checks.check((Buffer) floatBuffer3, 1);
        }
        nalGetSource3f(i, i2, MemoryUtil.memAddress(floatBuffer), MemoryUtil.memAddress(floatBuffer2), MemoryUtil.memAddress(floatBuffer3));
    }

    public static void nalGetSourcefv(int i, int i2, long j) {
        JNI.invokePV(i, i2, j, AL.getICD().alGetSourcefv);
    }

    @NativeType("ALvoid")
    public static void alGetSourcefv(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nalGetSourcefv(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void nalGetSourcei(int i, int i2, long j) {
        JNI.invokePV(i, i2, j, AL.getICD().alGetSourcei);
    }

    @NativeType("ALvoid")
    public static void alGetSourcei(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nalGetSourcei(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("ALvoid")
    public static int alGetSourcei(@NativeType("ALuint") int i, @NativeType("ALenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nalGetSourcei(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void nalGetSourceiv(int i, int i2, long j) {
        JNI.invokePV(i, i2, j, AL.getICD().alGetSourceiv);
    }

    @NativeType("ALvoid")
    public static void alGetSourceiv(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nalGetSourceiv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    public static void nalSourceQueueBuffers(int i, int i2, long j) {
        JNI.invokePV(i, i2, j, AL.getICD().alSourceQueueBuffers);
    }

    @NativeType("ALvoid")
    public static void alSourceQueueBuffers(@NativeType("ALuint") int i, @NativeType("ALuint *") IntBuffer intBuffer) {
        nalSourceQueueBuffers(i, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("ALvoid")
    public static void alSourceQueueBuffers(@NativeType("ALuint") int i, @NativeType("ALuint *") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nalSourceQueueBuffers(i, 1, MemoryUtil.memAddress(stackGet.ints(i2)));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void nalSourceUnqueueBuffers(int i, int i2, long j) {
        JNI.invokePV(i, i2, j, AL.getICD().alSourceUnqueueBuffers);
    }

    @NativeType("ALvoid")
    public static void alSourceUnqueueBuffers(@NativeType("ALuint") int i, @NativeType("ALuint *") IntBuffer intBuffer) {
        nalSourceUnqueueBuffers(i, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("ALvoid")
    public static int alSourceUnqueueBuffers(@NativeType("ALuint") int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nalSourceUnqueueBuffers(i, 1, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("ALvoid")
    public static void alSourcePlay(@NativeType("ALuint") int i) {
        JNI.invokeV(i, AL.getICD().alSourcePlay);
    }

    @NativeType("ALvoid")
    public static void alSourcePause(@NativeType("ALuint") int i) {
        JNI.invokeV(i, AL.getICD().alSourcePause);
    }

    @NativeType("ALvoid")
    public static void alSourceStop(@NativeType("ALuint") int i) {
        JNI.invokeV(i, AL.getICD().alSourceStop);
    }

    @NativeType("ALvoid")
    public static void alSourceRewind(@NativeType("ALuint") int i) {
        JNI.invokeV(i, AL.getICD().alSourceRewind);
    }

    public static void nalSourcePlayv(int i, long j) {
        JNI.invokePV(i, j, AL.getICD().alSourcePlayv);
    }

    @NativeType("ALvoid")
    public static void alSourcePlayv(@NativeType("ALuint const *") IntBuffer intBuffer) {
        nalSourcePlayv(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void nalSourcePausev(int i, long j) {
        JNI.invokePV(i, j, AL.getICD().alSourcePausev);
    }

    @NativeType("ALvoid")
    public static void alSourcePausev(@NativeType("ALuint const *") IntBuffer intBuffer) {
        nalSourcePausev(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void nalSourceStopv(int i, long j) {
        JNI.invokePV(i, j, AL.getICD().alSourceStopv);
    }

    @NativeType("ALvoid")
    public static void alSourceStopv(@NativeType("ALuint const *") IntBuffer intBuffer) {
        nalSourceStopv(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void nalSourceRewindv(int i, long j) {
        JNI.invokePV(i, j, AL.getICD().alSourceRewindv);
    }

    @NativeType("ALvoid")
    public static void alSourceRewindv(@NativeType("ALuint const *") IntBuffer intBuffer) {
        nalSourceRewindv(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void nalGenBuffers(int i, long j) {
        JNI.invokePV(i, j, AL.getICD().alGenBuffers);
    }

    @NativeType("ALvoid")
    public static void alGenBuffers(@NativeType("ALuint *") IntBuffer intBuffer) {
        nalGenBuffers(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("ALvoid")
    public static int alGenBuffers() {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nalGenBuffers(1, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void nalDeleteBuffers(int i, long j) {
        JNI.invokePV(i, j, AL.getICD().alDeleteBuffers);
    }

    @NativeType("ALvoid")
    public static void alDeleteBuffers(@NativeType("ALuint const *") IntBuffer intBuffer) {
        nalDeleteBuffers(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("ALvoid")
    public static void alDeleteBuffers(@NativeType("ALuint const *") int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nalDeleteBuffers(1, MemoryUtil.memAddress(stackGet.ints(i)));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("ALboolean")
    public static boolean alIsBuffer(@NativeType("ALuint") int i) {
        return JNI.invokeZ(i, AL.getICD().alIsBuffer);
    }

    public static void nalGetBufferf(int i, int i2, long j) {
        JNI.invokePV(i, i2, j, AL.getICD().alGetBufferf);
    }

    @NativeType("ALvoid")
    public static void alGetBufferf(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nalGetBufferf(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    @NativeType("ALvoid")
    public static float alGetBufferf(@NativeType("ALuint") int i, @NativeType("ALenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            FloatBuffer callocFloat = stackGet.callocFloat(1);
            nalGetBufferf(i, i2, MemoryUtil.memAddress(callocFloat));
            return callocFloat.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void nalGetBufferi(int i, int i2, long j) {
        JNI.invokePV(i, i2, j, AL.getICD().alGetBufferi);
    }

    @NativeType("ALvoid")
    public static void alGetBufferi(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nalGetBufferi(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("ALvoid")
    public static int alGetBufferi(@NativeType("ALuint") int i, @NativeType("ALenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nalGetBufferi(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void nalBufferData(int i, int i2, long j, int i3, int i4) {
        JNI.invokePV(i, i2, j, i3, i4, AL.getICD().alBufferData);
    }

    @NativeType("ALvoid")
    public static void alBufferData(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALvoid const *") ByteBuffer byteBuffer, @NativeType("ALsizei") int i3) {
        nalBufferData(i, i2, MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining(), i3);
    }

    @NativeType("ALvoid")
    public static void alBufferData(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALvoid const *") ShortBuffer shortBuffer, @NativeType("ALsizei") int i3) {
        nalBufferData(i, i2, MemoryUtil.memAddress(shortBuffer), shortBuffer.remaining() << 1, i3);
    }

    @NativeType("ALvoid")
    public static void alBufferData(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALvoid const *") IntBuffer intBuffer, @NativeType("ALsizei") int i3) {
        nalBufferData(i, i2, MemoryUtil.memAddress(intBuffer), intBuffer.remaining() << 2, i3);
    }

    @NativeType("ALvoid")
    public static void alBufferData(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALvoid const *") FloatBuffer floatBuffer, @NativeType("ALsizei") int i3) {
        nalBufferData(i, i2, MemoryUtil.memAddress(floatBuffer), floatBuffer.remaining() << 2, i3);
    }

    public static int nalGetEnumValue(long j) {
        return JNI.invokePI(j, AL.getICD().alGetEnumValue);
    }

    @NativeType("ALuint")
    public static int alGetEnumValue(@NativeType("ALchar const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nalGetEnumValue(MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("ALuint")
    public static int alGetEnumValue(@NativeType("ALchar const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            return nalGetEnumValue(stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static long nalGetProcAddress(long j) {
        return JNI.invokePP(j, AL.getICD().alGetProcAddress);
    }

    @NativeType("void *")
    public static long alGetProcAddress(@NativeType("ALchar const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nalGetProcAddress(MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("void *")
    public static long alGetProcAddress(@NativeType("ALchar const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            return nalGetProcAddress(stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static boolean nalIsExtensionPresent(long j) {
        return JNI.invokePZ(j, AL.getICD().alIsExtensionPresent);
    }

    @NativeType("ALCboolean")
    public static boolean alIsExtensionPresent(@NativeType("ALchar const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nalIsExtensionPresent(MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("ALCboolean")
    public static boolean alIsExtensionPresent(@NativeType("ALchar const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            return nalIsExtensionPresent(stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("ALvoid")
    public static void alGetIntegerv(@NativeType("ALenum") int i, @NativeType("ALint *") int[] iArr) {
        long j = AL.getICD().alGetIntegerv;
        if (Checks.CHECKS) {
            Checks.check(iArr, 1);
        }
        JNI.invokePV(i, iArr, j);
    }

    @NativeType("ALvoid")
    public static void alGetFloatv(@NativeType("ALenum") int i, @NativeType("ALfloat *") float[] fArr) {
        long j = AL.getICD().alGetFloatv;
        if (Checks.CHECKS) {
            Checks.check(fArr, 1);
        }
        JNI.invokePV(i, fArr, j);
    }

    @NativeType("ALvoid")
    public static void alGetDoublev(@NativeType("ALenum") int i, @NativeType("ALdouble *") double[] dArr) {
        long j = AL.getICD().alGetDoublev;
        if (Checks.CHECKS) {
            Checks.check(dArr, 1);
        }
        JNI.invokePV(i, dArr, j);
    }

    @NativeType("ALvoid")
    public static void alListenerfv(@NativeType("ALenum") int i, @NativeType("ALfloat const *") float[] fArr) {
        long j = AL.getICD().alListenerfv;
        if (Checks.CHECKS) {
            Checks.check(fArr, 1);
        }
        JNI.invokePV(i, fArr, j);
    }

    @NativeType("ALvoid")
    public static void alGetListenerf(@NativeType("ALenum") int i, @NativeType("ALfloat *") float[] fArr) {
        long j = AL.getICD().alGetListenerf;
        if (Checks.CHECKS) {
            Checks.check(fArr, 1);
        }
        JNI.invokePV(i, fArr, j);
    }

    @NativeType("ALvoid")
    public static void alGetListeneri(@NativeType("ALenum") int i, @NativeType("ALint *") int[] iArr) {
        long j = AL.getICD().alGetListeneri;
        if (Checks.CHECKS) {
            Checks.check(iArr, 1);
        }
        JNI.invokePV(i, iArr, j);
    }

    @NativeType("ALvoid")
    public static void alGetListener3f(@NativeType("ALenum") int i, @NativeType("ALfloat *") float[] fArr, @NativeType("ALfloat *") float[] fArr2, @NativeType("ALfloat *") float[] fArr3) {
        long j = AL.getICD().alGetListener3f;
        if (Checks.CHECKS) {
            Checks.check(fArr, 1);
            Checks.check(fArr2, 1);
            Checks.check(fArr3, 1);
        }
        JNI.invokePPPV(i, fArr, fArr2, fArr3, j);
    }

    @NativeType("ALvoid")
    public static void alGetListenerfv(@NativeType("ALenum") int i, @NativeType("ALfloat *") float[] fArr) {
        long j = AL.getICD().alGetListenerfv;
        if (Checks.CHECKS) {
            Checks.check(fArr, 1);
        }
        JNI.invokePV(i, fArr, j);
    }

    @NativeType("ALvoid")
    public static void alGenSources(@NativeType("ALuint *") int[] iArr) {
        JNI.invokePV(iArr.length, iArr, AL.getICD().alGenSources);
    }

    @NativeType("ALvoid")
    public static void alDeleteSources(@NativeType("ALuint *") int[] iArr) {
        JNI.invokePV(iArr.length, iArr, AL.getICD().alDeleteSources);
    }

    @NativeType("ALvoid")
    public static void alSourcefv(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALfloat const *") float[] fArr) {
        long j = AL.getICD().alSourcefv;
        if (Checks.CHECKS) {
            Checks.check(fArr, 1);
        }
        JNI.invokePV(i, i2, fArr, j);
    }

    @NativeType("ALvoid")
    public static void alGetSourcef(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALfloat *") float[] fArr) {
        long j = AL.getICD().alGetSourcef;
        if (Checks.CHECKS) {
            Checks.check(fArr, 1);
        }
        JNI.invokePV(i, i2, fArr, j);
    }

    @NativeType("ALvoid")
    public static void alGetSource3f(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALfloat *") float[] fArr, @NativeType("ALfloat *") float[] fArr2, @NativeType("ALfloat *") float[] fArr3) {
        long j = AL.getICD().alGetSource3f;
        if (Checks.CHECKS) {
            Checks.check(fArr, 1);
            Checks.check(fArr2, 1);
            Checks.check(fArr3, 1);
        }
        JNI.invokePPPV(i, i2, fArr, fArr2, fArr3, j);
    }

    @NativeType("ALvoid")
    public static void alGetSourcefv(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALfloat *") float[] fArr) {
        long j = AL.getICD().alGetSourcefv;
        if (Checks.CHECKS) {
            Checks.check(fArr, 1);
        }
        JNI.invokePV(i, i2, fArr, j);
    }

    @NativeType("ALvoid")
    public static void alGetSourcei(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALint *") int[] iArr) {
        long j = AL.getICD().alGetSourcei;
        if (Checks.CHECKS) {
            Checks.check(iArr, 1);
        }
        JNI.invokePV(i, i2, iArr, j);
    }

    @NativeType("ALvoid")
    public static void alGetSourceiv(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALint *") int[] iArr) {
        long j = AL.getICD().alGetSourceiv;
        if (Checks.CHECKS) {
            Checks.check(iArr, 1);
        }
        JNI.invokePV(i, i2, iArr, j);
    }

    @NativeType("ALvoid")
    public static void alSourceQueueBuffers(@NativeType("ALuint") int i, @NativeType("ALuint *") int[] iArr) {
        JNI.invokePV(i, iArr.length, iArr, AL.getICD().alSourceQueueBuffers);
    }

    @NativeType("ALvoid")
    public static void alSourceUnqueueBuffers(@NativeType("ALuint") int i, @NativeType("ALuint *") int[] iArr) {
        JNI.invokePV(i, iArr.length, iArr, AL.getICD().alSourceUnqueueBuffers);
    }

    @NativeType("ALvoid")
    public static void alSourcePlayv(@NativeType("ALuint const *") int[] iArr) {
        JNI.invokePV(iArr.length, iArr, AL.getICD().alSourcePlayv);
    }

    @NativeType("ALvoid")
    public static void alSourcePausev(@NativeType("ALuint const *") int[] iArr) {
        JNI.invokePV(iArr.length, iArr, AL.getICD().alSourcePausev);
    }

    @NativeType("ALvoid")
    public static void alSourceStopv(@NativeType("ALuint const *") int[] iArr) {
        JNI.invokePV(iArr.length, iArr, AL.getICD().alSourceStopv);
    }

    @NativeType("ALvoid")
    public static void alSourceRewindv(@NativeType("ALuint const *") int[] iArr) {
        JNI.invokePV(iArr.length, iArr, AL.getICD().alSourceRewindv);
    }

    @NativeType("ALvoid")
    public static void alGenBuffers(@NativeType("ALuint *") int[] iArr) {
        JNI.invokePV(iArr.length, iArr, AL.getICD().alGenBuffers);
    }

    @NativeType("ALvoid")
    public static void alDeleteBuffers(@NativeType("ALuint const *") int[] iArr) {
        JNI.invokePV(iArr.length, iArr, AL.getICD().alDeleteBuffers);
    }

    @NativeType("ALvoid")
    public static void alGetBufferf(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALfloat *") float[] fArr) {
        long j = AL.getICD().alGetBufferf;
        if (Checks.CHECKS) {
            Checks.check(fArr, 1);
        }
        JNI.invokePV(i, i2, fArr, j);
    }

    @NativeType("ALvoid")
    public static void alGetBufferi(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALint *") int[] iArr) {
        long j = AL.getICD().alGetBufferi;
        if (Checks.CHECKS) {
            Checks.check(iArr, 1);
        }
        JNI.invokePV(i, i2, iArr, j);
    }

    @NativeType("ALvoid")
    public static void alBufferData(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALvoid const *") short[] sArr, @NativeType("ALsizei") int i3) {
        JNI.invokePV(i, i2, sArr, sArr.length << 1, i3, AL.getICD().alBufferData);
    }

    @NativeType("ALvoid")
    public static void alBufferData(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALvoid const *") int[] iArr, @NativeType("ALsizei") int i3) {
        JNI.invokePV(i, i2, iArr, iArr.length << 2, i3, AL.getICD().alBufferData);
    }

    @NativeType("ALvoid")
    public static void alBufferData(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALvoid const *") float[] fArr, @NativeType("ALsizei") int i3) {
        JNI.invokePV(i, i2, fArr, fArr.length << 2, i3, AL.getICD().alBufferData);
    }
}
