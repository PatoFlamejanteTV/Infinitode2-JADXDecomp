package com.badlogic.gdx.backends.lwjgl3.audio;

import com.badlogic.gdx.audio.AudioDevice;
import com.badlogic.gdx.audio.AudioRecorder;
import com.badlogic.gdx.backends.lwjgl3.audio.Mp3;
import com.badlogic.gdx.backends.lwjgl3.audio.Ogg;
import com.badlogic.gdx.backends.lwjgl3.audio.Wav;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.LongMap;
import com.badlogic.gdx.utils.ObjectMap;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.List;
import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.ALC;
import org.lwjgl.openal.ALC10;
import org.lwjgl.openal.ALCCapabilities;
import org.lwjgl.openal.ALUtil;
import org.lwjgl.openal.EXTDisconnect;
import org.lwjgl.openal.SOFTReopenDevice;
import org.lwjgl.openal.SOFTXHoldOnDisconnect;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/lwjgl3/audio/OpenALLwjgl3Audio.class */
public class OpenALLwjgl3Audio implements Lwjgl3Audio {
    private final int deviceBufferSize;
    private final int deviceBufferCount;
    private IntArray idleSources;
    private IntArray allSources;
    private LongMap<Integer> soundIdToSource;
    private IntMap<Long> sourceToSoundId;
    private long nextSoundId;
    private ObjectMap<String, Class<? extends OpenALSound>> extensionToSoundClass;
    private ObjectMap<String, Class<? extends OpenALMusic>> extensionToMusicClass;
    private OpenALSound[] recentSounds;
    private int mostRecetSound;
    private String preferredOutputDevice;
    private Thread observerThread;
    Array<OpenALMusic> music;
    long device;
    long context;
    boolean noDevice;

    /*  JADX ERROR: Failed to decode insn: 0x006C: MOVE_MULTI, method: com.badlogic.gdx.backends.lwjgl3.audio.OpenALLwjgl3Audio.obtainSource(boolean):int
        java.lang.ArrayIndexOutOfBoundsException: arraycopy: source index -1 out of bounds for object array[8]
        	at java.base/java.lang.System.arraycopy(Native Method)
        	at jadx.plugins.input.java.data.code.StackState.insert(StackState.java:49)
        	at jadx.plugins.input.java.data.code.CodeDecodeState.insert(CodeDecodeState.java:118)
        	at jadx.plugins.input.java.data.code.JavaInsnsRegister.dup2x1(JavaInsnsRegister.java:313)
        	at jadx.plugins.input.java.data.code.JavaInsnData.decode(JavaInsnData.java:46)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:54)
        	at jadx.plugins.input.java.data.code.JavaCodeReader.visitInstructions(JavaCodeReader.java:81)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:50)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:156)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:443)
        	at jadx.core.ProcessClass.process(ProcessClass.java:70)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:118)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:400)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:388)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:338)
        */
    int obtainSource(boolean r9) {
        /*
            r8 = this;
            r0 = r8
            boolean r0 = r0.noDevice
            if (r0 == 0) goto L9
            r0 = 0
            return r0
            r0 = 0
            r10 = r0
            r0 = r8
            com.badlogic.gdx.utils.IntArray r0 = r0.idleSources
            int r0 = r0.size
            r11 = r0
            r0 = r10
            r1 = r11
            if (r0 >= r1) goto Lcf
            r0 = r8
            com.badlogic.gdx.utils.IntArray r0 = r0.idleSources
            r1 = r10
            int r0 = r0.get(r1)
            r1 = r0
            r12 = r1
            r1 = 4112(0x1010, float:5.762E-42)
            int r0 = org.lwjgl.openal.AL10.alGetSourcei(r0, r1)
            r1 = r0
            r13 = r1
            r1 = 4114(0x1012, float:5.765E-42)
            if (r0 == r1) goto Lc9
            r0 = r13
            r1 = 4115(0x1013, float:5.766E-42)
            if (r0 == r1) goto Lc9
            r0 = r8
            com.badlogic.gdx.utils.IntMap<java.lang.Long> r0 = r0.sourceToSoundId
            r1 = r12
            java.lang.Object r0 = r0.remove(r1)
            java.lang.Long r0 = (java.lang.Long) r0
            r1 = r0
            r11 = r1
            if (r0 == 0) goto L57
            r0 = r8
            com.badlogic.gdx.utils.LongMap<java.lang.Integer> r0 = r0.soundIdToSource
            r1 = r11
            long r1 = r1.longValue()
            java.lang.Object r0 = r0.remove(r1)
            r0 = r9
            if (r0 == 0) goto L67
            r0 = r8
            com.badlogic.gdx.utils.IntArray r0 = r0.idleSources
            r1 = r10
            int r0 = r0.removeIndex(r1)
            goto L92
            r0 = r8
            r1 = r0
            long r1 = r1.nextSoundId
            // decode failed: arraycopy: source index -1 out of bounds for object array[8]
            r2 = 1
            long r1 = r1 + r2
            r0.nextSoundId = r1
            r15 = r-1
            r-1 = r8
            com.badlogic.gdx.utils.IntMap<java.lang.Long> r-1 = r-1.sourceToSoundId
            r0 = r12
            r1 = r15
            java.lang.Long r1 = java.lang.Long.valueOf(r1)
            r-1.put(r0, r1)
            r-1 = r8
            com.badlogic.gdx.utils.LongMap<java.lang.Integer> r-1 = r-1.soundIdToSource
            r0 = r15
            r1 = r12
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r-1.put(r0, r1)
            r0 = r12
            org.lwjgl.openal.AL10.alSourceStop(r0)
            r0 = r12
            r1 = 4105(0x1009, float:5.752E-42)
            r2 = 0
            org.lwjgl.openal.AL10.alSourcei(r0, r1, r2)
            r0 = r12
            r1 = 4106(0x100a, float:5.754E-42)
            r2 = 1065353216(0x3f800000, float:1.0)
            org.lwjgl.openal.AL10.alSourcef(r0, r1, r2)
            r0 = r12
            r1 = 4099(0x1003, float:5.744E-42)
            r2 = 1065353216(0x3f800000, float:1.0)
            org.lwjgl.openal.AL10.alSourcef(r0, r1, r2)
            r0 = r12
            r1 = 4100(0x1004, float:5.745E-42)
            r2 = 0
            r3 = 0
            r4 = 1065353216(0x3f800000, float:1.0)
            org.lwjgl.openal.AL10.alSource3f(r0, r1, r2, r3, r4)
            r0 = r12
            r1 = 4147(0x1033, float:5.811E-42)
            r2 = 2
            org.lwjgl.openal.AL10.alSourcei(r0, r1, r2)
            r0 = r12
            return r0
            int r10 = r10 + 1
            goto L13
            r0 = -1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.badlogic.gdx.backends.lwjgl3.audio.OpenALLwjgl3Audio.obtainSource(boolean):int");
    }

    public OpenALLwjgl3Audio() {
        this(16, 9, 512);
    }

    public OpenALLwjgl3Audio(int i, int i2, int i3) {
        this.nextSoundId = 0L;
        this.extensionToSoundClass = new ObjectMap<>();
        this.extensionToMusicClass = new ObjectMap<>();
        this.mostRecetSound = -1;
        this.preferredOutputDevice = null;
        this.music = new Array<>(false, 1, OpenALMusic.class);
        this.noDevice = false;
        this.deviceBufferSize = i3;
        this.deviceBufferCount = i2;
        registerSound("ogg", Ogg.Sound.class);
        registerMusic("ogg", Ogg.Music.class);
        registerSound("wav", Wav.Sound.class);
        registerMusic("wav", Wav.Music.class);
        registerSound("mp3", Mp3.Sound.class);
        registerMusic("mp3", Mp3.Music.class);
        this.device = ALC10.alcOpenDevice((ByteBuffer) null);
        if (this.device == 0) {
            this.noDevice = true;
            return;
        }
        ALCCapabilities createCapabilities = ALC.createCapabilities(this.device);
        this.context = ALC10.alcCreateContext(this.device, (IntBuffer) null);
        if (this.context == 0) {
            ALC10.alcCloseDevice(this.device);
            this.noDevice = true;
            return;
        }
        if (!ALC10.alcMakeContextCurrent(this.context)) {
            this.noDevice = true;
            return;
        }
        AL.createCapabilities(createCapabilities);
        AL10.alGetError();
        this.allSources = new IntArray(false, i);
        for (int i4 = 0; i4 < i; i4++) {
            int alGenSources = AL10.alGenSources();
            if (AL10.alGetError() != 0) {
                break;
            }
            this.allSources.add(alGenSources);
        }
        this.idleSources = new IntArray(this.allSources);
        this.soundIdToSource = new LongMap<>();
        this.sourceToSoundId = new IntMap<>();
        FloatBuffer put = BufferUtils.createFloatBuffer(6).put(new float[]{0.0f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f});
        put.flip();
        AL10.alListenerfv(AL10.AL_ORIENTATION, put);
        FloatBuffer put2 = BufferUtils.createFloatBuffer(3).put(new float[]{0.0f, 0.0f, 0.0f});
        put2.flip();
        AL10.alListenerfv(4102, put2);
        FloatBuffer put3 = BufferUtils.createFloatBuffer(3).put(new float[]{0.0f, 0.0f, 0.0f});
        put3.flip();
        AL10.alListenerfv(4100, put3);
        AL10.alDisable(SOFTXHoldOnDisconnect.AL_STOP_SOURCES_ON_DISCONNECT_SOFT);
        this.observerThread = new Thread(new Runnable() { // from class: com.badlogic.gdx.backends.lwjgl3.audio.OpenALLwjgl3Audio.1
            private String[] lastAvailableDevices = new String[0];

            @Override // java.lang.Runnable
            public void run() {
                while (true) {
                    if (!(ALC10.alcGetInteger(OpenALLwjgl3Audio.this.device, EXTDisconnect.ALC_CONNECTED) != 0)) {
                        OpenALLwjgl3Audio.this.switchOutputDevice(null, false);
                    } else {
                        if (OpenALLwjgl3Audio.this.preferredOutputDevice != null) {
                            if (Arrays.asList(OpenALLwjgl3Audio.this.getAvailableOutputDevices()).contains(OpenALLwjgl3Audio.this.preferredOutputDevice)) {
                                if (!OpenALLwjgl3Audio.this.preferredOutputDevice.equals(ALC10.alcGetString(OpenALLwjgl3Audio.this.device, 4115))) {
                                    OpenALLwjgl3Audio.this.switchOutputDevice(OpenALLwjgl3Audio.this.preferredOutputDevice);
                                }
                            } else if (OpenALLwjgl3Audio.this.preferredOutputDevice.equals(ALC10.alcGetString(OpenALLwjgl3Audio.this.device, 4115))) {
                                OpenALLwjgl3Audio.this.switchOutputDevice(null, false);
                            }
                        } else {
                            String[] availableOutputDevices = OpenALLwjgl3Audio.this.getAvailableOutputDevices();
                            if (!Arrays.equals(availableOutputDevices, this.lastAvailableDevices)) {
                                OpenALLwjgl3Audio.this.switchOutputDevice(null);
                            }
                            this.lastAvailableDevices = availableOutputDevices;
                        }
                        try {
                            Thread.sleep(1000L);
                        } catch (InterruptedException unused) {
                            return;
                        }
                    }
                }
            }
        });
        this.observerThread.setDaemon(true);
        this.observerThread.start();
        this.recentSounds = new OpenALSound[i];
    }

    public void registerSound(String str, Class<? extends OpenALSound> cls) {
        if (str == null) {
            throw new IllegalArgumentException("extension cannot be null.");
        }
        if (cls == null) {
            throw new IllegalArgumentException("soundClass cannot be null.");
        }
        this.extensionToSoundClass.put(str, cls);
    }

    public void registerMusic(String str, Class<? extends OpenALMusic> cls) {
        if (str == null) {
            throw new IllegalArgumentException("extension cannot be null.");
        }
        if (cls == null) {
            throw new IllegalArgumentException("musicClass cannot be null.");
        }
        this.extensionToMusicClass.put(str, cls);
    }

    @Override // com.badlogic.gdx.Audio
    public OpenALSound newSound(FileHandle fileHandle) {
        return newSound(fileHandle, fileHandle.extension().toLowerCase());
    }

    public OpenALSound newSound(FileHandle fileHandle, String str) {
        if (fileHandle == null) {
            throw new IllegalArgumentException("file cannot be null.");
        }
        Class<? extends OpenALSound> cls = this.extensionToSoundClass.get(str);
        if (cls == null) {
            throw new GdxRuntimeException("Unknown file extension for sound: " + fileHandle);
        }
        try {
            OpenALSound newInstance = cls.getConstructor(OpenALLwjgl3Audio.class, FileHandle.class).newInstance(this, fileHandle);
            if (newInstance.getType() != null && !newInstance.getType().equals(str)) {
                return newSound(fileHandle, newInstance.getType());
            }
            return newInstance;
        } catch (Exception e) {
            throw new GdxRuntimeException("Error creating sound " + cls.getName() + " for file: " + fileHandle, e);
        }
    }

    @Override // com.badlogic.gdx.Audio
    public OpenALMusic newMusic(FileHandle fileHandle) {
        if (fileHandle == null) {
            throw new IllegalArgumentException("file cannot be null.");
        }
        Class<? extends OpenALMusic> cls = this.extensionToMusicClass.get(fileHandle.extension().toLowerCase());
        if (cls == null) {
            throw new GdxRuntimeException("Unknown file extension for music: " + fileHandle);
        }
        try {
            return cls.getConstructor(OpenALLwjgl3Audio.class, FileHandle.class).newInstance(this, fileHandle);
        } catch (Exception e) {
            throw new GdxRuntimeException("Error creating music " + cls.getName() + " for file: " + fileHandle, e);
        }
    }

    @Override // com.badlogic.gdx.Audio
    public boolean switchOutputDevice(String str) {
        return switchOutputDevice(str, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean switchOutputDevice(String str, boolean z) {
        if (z) {
            this.preferredOutputDevice = str;
        }
        return SOFTReopenDevice.alcReopenDeviceSOFT(this.device, str, (IntBuffer) null);
    }

    @Override // com.badlogic.gdx.Audio
    public String[] getAvailableOutputDevices() {
        List<String> stringList = ALUtil.getStringList(0L, 4115);
        return stringList == null ? new String[0] : (String[]) stringList.toArray(new String[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void freeSource(int i) {
        if (this.noDevice) {
            return;
        }
        AL10.alSourceStop(i);
        AL10.alSourcei(i, 4105, 0);
        Long remove = this.sourceToSoundId.remove(i);
        if (remove != null) {
            this.soundIdToSource.remove(remove.longValue());
        }
        this.idleSources.add(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void freeBuffer(int i) {
        if (this.noDevice) {
            return;
        }
        int i2 = this.idleSources.size;
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = this.idleSources.get(i3);
            if (AL10.alGetSourcei(i4, 4105) == i) {
                Long remove = this.sourceToSoundId.remove(i4);
                if (remove != null) {
                    this.soundIdToSource.remove(remove.longValue());
                }
                AL10.alSourceStop(i4);
                AL10.alSourcei(i4, 4105, 0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void stopSourcesWithBuffer(int i) {
        if (this.noDevice) {
            return;
        }
        int i2 = this.idleSources.size;
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = this.idleSources.get(i3);
            if (AL10.alGetSourcei(i4, 4105) == i) {
                Long remove = this.sourceToSoundId.remove(i4);
                if (remove != null) {
                    this.soundIdToSource.remove(remove.longValue());
                }
                AL10.alSourceStop(i4);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void pauseSourcesWithBuffer(int i) {
        if (this.noDevice) {
            return;
        }
        int i2 = this.idleSources.size;
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = this.idleSources.get(i3);
            if (AL10.alGetSourcei(i4, 4105) == i) {
                AL10.alSourcePause(i4);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void resumeSourcesWithBuffer(int i) {
        if (this.noDevice) {
            return;
        }
        int i2 = this.idleSources.size;
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = this.idleSources.get(i3);
            if (AL10.alGetSourcei(i4, 4105) == i && AL10.alGetSourcei(i4, 4112) == 4115) {
                AL10.alSourcePlay(i4);
            }
        }
    }

    @Override // com.badlogic.gdx.backends.lwjgl3.audio.Lwjgl3Audio
    public void update() {
        if (this.noDevice) {
            return;
        }
        for (int i = 0; i < this.music.size; i++) {
            this.music.items[i].update();
        }
    }

    public long getSoundId(int i) {
        Long l = this.sourceToSoundId.get(i);
        if (l != null) {
            return l.longValue();
        }
        return -1L;
    }

    public int getSourceId(long j) {
        Integer num = this.soundIdToSource.get(j);
        if (num != null) {
            return num.intValue();
        }
        return -1;
    }

    public void stopSound(long j) {
        Integer num = this.soundIdToSource.get(j);
        if (num != null) {
            AL10.alSourceStop(num.intValue());
        }
    }

    public void pauseSound(long j) {
        Integer num = this.soundIdToSource.get(j);
        if (num != null) {
            AL10.alSourcePause(num.intValue());
        }
    }

    public void resumeSound(long j) {
        int intValue = this.soundIdToSource.get(j, -1).intValue();
        if (intValue != -1 && AL10.alGetSourcei(intValue, 4112) == 4115) {
            AL10.alSourcePlay(intValue);
        }
    }

    public void setSoundGain(long j, float f) {
        Integer num = this.soundIdToSource.get(j);
        if (num != null) {
            AL10.alSourcef(num.intValue(), AL10.AL_GAIN, f);
        }
    }

    public void setSoundLooping(long j, boolean z) {
        Integer num = this.soundIdToSource.get(j);
        if (num != null) {
            AL10.alSourcei(num.intValue(), 4103, z ? 1 : 0);
        }
    }

    public void setSoundPitch(long j, float f) {
        Integer num = this.soundIdToSource.get(j);
        if (num != null) {
            AL10.alSourcef(num.intValue(), 4099, f);
        }
    }

    public void setSoundPan(long j, float f, float f2) {
        int intValue = this.soundIdToSource.get(j, -1).intValue();
        if (intValue != -1) {
            AL10.alSource3f(intValue, 4100, MathUtils.cos((f - 1.0f) * 1.5707964f), 0.0f, MathUtils.sin((f + 1.0f) * 1.5707964f));
            AL10.alSourcef(intValue, AL10.AL_GAIN, f2);
        }
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        if (this.noDevice) {
            return;
        }
        this.observerThread.interrupt();
        int i = this.allSources.size;
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = this.allSources.get(i2);
            if (AL10.alGetSourcei(i3, 4112) != 4116) {
                AL10.alSourceStop(i3);
            }
            AL10.alDeleteSources(i3);
        }
        this.sourceToSoundId = null;
        this.soundIdToSource = null;
        ALC10.alcDestroyContext(this.context);
        ALC10.alcCloseDevice(this.device);
    }

    @Override // com.badlogic.gdx.Audio
    public AudioDevice newAudioDevice(int i, final boolean z) {
        return this.noDevice ? new AudioDevice() { // from class: com.badlogic.gdx.backends.lwjgl3.audio.OpenALLwjgl3Audio.2
            @Override // com.badlogic.gdx.audio.AudioDevice
            public void writeSamples(float[] fArr, int i2, int i3) {
            }

            @Override // com.badlogic.gdx.audio.AudioDevice
            public void writeSamples(short[] sArr, int i2, int i3) {
            }

            @Override // com.badlogic.gdx.audio.AudioDevice
            public void setVolume(float f) {
            }

            @Override // com.badlogic.gdx.audio.AudioDevice
            public boolean isMono() {
                return z;
            }

            @Override // com.badlogic.gdx.audio.AudioDevice
            public int getLatency() {
                return 0;
            }

            @Override // com.badlogic.gdx.audio.AudioDevice, com.badlogic.gdx.utils.Disposable
            public void dispose() {
            }

            @Override // com.badlogic.gdx.audio.AudioDevice
            public void pause() {
            }

            @Override // com.badlogic.gdx.audio.AudioDevice
            public void resume() {
            }
        } : new OpenALAudioDevice(this, i, z, this.deviceBufferSize, this.deviceBufferCount);
    }

    @Override // com.badlogic.gdx.Audio
    public AudioRecorder newAudioRecorder(int i, boolean z) {
        return this.noDevice ? new AudioRecorder() { // from class: com.badlogic.gdx.backends.lwjgl3.audio.OpenALLwjgl3Audio.3
            @Override // com.badlogic.gdx.audio.AudioRecorder
            public void read(short[] sArr, int i2, int i3) {
            }

            @Override // com.badlogic.gdx.audio.AudioRecorder, com.badlogic.gdx.utils.Disposable
            public void dispose() {
            }
        } : new JavaSoundAudioRecorder(i, z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void retain(OpenALSound openALSound, boolean z) {
        this.mostRecetSound++;
        this.mostRecetSound %= this.recentSounds.length;
        if (z && this.recentSounds[this.mostRecetSound] != null) {
            this.recentSounds[this.mostRecetSound].stop();
        }
        this.recentSounds[this.mostRecetSound] = openALSound;
    }

    public void forget(OpenALSound openALSound) {
        for (int i = 0; i < this.recentSounds.length; i++) {
            if (this.recentSounds[i] == openALSound) {
                this.recentSounds[i] = null;
            }
        }
    }
}
