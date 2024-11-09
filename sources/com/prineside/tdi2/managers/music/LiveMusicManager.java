package com.prineside.tdi2.managers.music;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.LifecycleListener;
import com.badlogic.gdx.audio.AudioDevice;
import com.badlogic.gdx.audio.analysis.FFT;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.StringBuilder;
import com.badlogic.gdx.utils.Timer;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.ibxm.IBXM;
import com.prineside.tdi2.ibxm.Module;
import com.prineside.tdi2.managers.MusicManager;
import com.prineside.tdi2.ui.shared.Notifications;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.MovingAverageFloat;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.errorhandling.CrashHandler;
import com.prineside.tdi2.utils.logging.TLog;
import java.util.Arrays;

@REGS(serializer = MusicManager.Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/music/LiveMusicManager.class */
public final class LiveMusicManager extends MusicManager {
    private static final TLog e = TLog.forClass(LiveMusicManager.class);
    private boolean k;
    private AudioDevice l;
    private boolean m;
    public IBXM ibxm;
    private boolean n;
    private boolean o;
    private Thread r;
    private Thread s;
    private final Array<PcmBuffer> f = new Array<>(true, 3, PcmBuffer.class);
    private final PcmBuffer[] g = new PcmBuffer[3];
    private SynthesizerStatus h = SynthesizerStatus.NOT_ACTIVE;
    private PlaybackStatus i = PlaybackStatus.STARTING;
    private final Array<SpectrumGenerator> j = new Array<>(true, 1, SpectrumGenerator.class);
    private int[] p = new int[1];
    private long q = -1;

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/music/LiveMusicManager$PlaybackStatus.class */
    public enum PlaybackStatus {
        STARTING,
        ACTIVE,
        STOPPED,
        SLEEP_NO_BUFFER,
        SLEEP_NO_AUDIO_DEVICE,
        SLEEP_APP_INACTIVE,
        SLEEP_NOT_PLAYING
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/music/LiveMusicManager$RestartableAudioDevice.class */
    public interface RestartableAudioDevice {
        void restart();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/music/LiveMusicManager$SynthesizerStatus.class */
    public enum SynthesizerStatus {
        NOT_ACTIVE,
        SLEEP_APP_INACTIVE,
        SLEEP_NOT_PLAYING,
        SLEEP_NO_IBXM,
        SLEEP_NO_FREE_BUFFER,
        SLEEP_NO_AUDIO_DATA,
        RESTART_PLAYBACK,
        ACTIVE
    }

    public LiveMusicManager() {
        new MovingAverageFloat(60);
        if (Config.isHeadless()) {
            return;
        }
        e.i("initializing", new Object[0]);
        for (int i = 0; i < 3; i++) {
            this.g[i] = new PcmBuffer();
        }
        this.r = new Thread(() -> {
            while (true) {
                try {
                    PcmBuffer pcmBuffer = null;
                    if (!this.o || !this.n) {
                        if (!this.n) {
                            this.h = SynthesizerStatus.SLEEP_APP_INACTIVE;
                        } else {
                            this.h = SynthesizerStatus.SLEEP_NOT_PLAYING;
                        }
                        Thread.sleep(200L);
                    } else {
                        IBXM ibxm = this.ibxm;
                        if (ibxm == null) {
                            this.h = SynthesizerStatus.SLEEP_NO_IBXM;
                            Thread.sleep(100L);
                        } else {
                            while (pcmBuffer == null) {
                                int i2 = 0;
                                while (true) {
                                    if (i2 >= this.g.length) {
                                        break;
                                    }
                                    if (this.g[i2] == null) {
                                        i2++;
                                    } else {
                                        pcmBuffer = this.g[i2];
                                        this.g[i2] = null;
                                        break;
                                    }
                                }
                                if (pcmBuffer == null) {
                                    if (this.n && this.o) {
                                        this.h = SynthesizerStatus.SLEEP_NO_FREE_BUFFER;
                                        Thread.sleep(50L);
                                    } else {
                                        if (!this.n) {
                                            this.h = SynthesizerStatus.SLEEP_APP_INACTIVE;
                                        } else {
                                            this.h = SynthesizerStatus.SLEEP_NOT_PLAYING;
                                        }
                                        Thread.sleep(200L);
                                    }
                                }
                            }
                            pcmBuffer.a((this.p.length << 1) << 1);
                            int i3 = 0;
                            for (int i4 = 0; i4 < 2; i4++) {
                                int audio = ibxm.getAudio(this.p) << 1;
                                for (int i5 = 0; i5 < audio; i5++) {
                                    int i6 = this.p[i5];
                                    int i7 = i6;
                                    if (i6 > 32767) {
                                        i7 = 32767;
                                    }
                                    if (i7 < -32768) {
                                        i7 = -32768;
                                    }
                                    int i8 = i3;
                                    i3++;
                                    pcmBuffer.data[i8] = (short) i7;
                                }
                                if (ibxm.getModule().restartPos != 0 && ibxm.getSequencePos() < ibxm.lastSeqPos) {
                                    ibxm.setSequencePosApplyEffects(ibxm.getModule().restartPos);
                                }
                                ibxm.lastSeqPos = ibxm.getSequencePos();
                            }
                            pcmBuffer.length = i3;
                            Arrays.fill(pcmBuffer.data, i3, MathUtils.nextPowerOfTwo(pcmBuffer.length - 1) - 1, (short) 0);
                            if (i3 == 0) {
                                this.h = SynthesizerStatus.SLEEP_NO_AUDIO_DATA;
                                boolean z = false;
                                synchronized (this.g) {
                                    int i9 = 0;
                                    while (true) {
                                        if (i9 >= this.g.length) {
                                            break;
                                        }
                                        if (this.g[i9] != null) {
                                            i9++;
                                        } else {
                                            this.g[i9] = pcmBuffer;
                                            z = true;
                                            break;
                                        }
                                    }
                                }
                                if (!z) {
                                    e.e("buffer not freed - no space", new Object[0]);
                                }
                                Thread.sleep(5L);
                            } else {
                                synchronized (this.f) {
                                    this.f.add(pcmBuffer);
                                }
                                this.h = SynthesizerStatus.ACTIVE;
                            }
                        }
                    }
                } catch (InterruptedException e2) {
                    e.i("synthesizer stopped: " + e2.getMessage(), new Object[0]);
                    return;
                } catch (Exception e3) {
                    throw new RuntimeException(e3);
                }
            }
        }, "IBXM synthesizer");
        this.r.setDaemon(true);
        CrashHandler.handleThreadExceptionsForgiving(this.r);
        this.r.start();
        b();
        Gdx.app.addLifecycleListener(new LifecycleListener() { // from class: com.prineside.tdi2.managers.music.LiveMusicManager.1
            @Override // com.badlogic.gdx.LifecycleListener
            public void pause() {
                LiveMusicManager.this.n = false;
            }

            @Override // com.badlogic.gdx.LifecycleListener
            public void resume() {
                LiveMusicManager.this.n = true;
            }

            @Override // com.badlogic.gdx.LifecycleListener
            public void dispose() {
            }
        });
    }

    @Override // com.prineside.tdi2.managers.MusicManager
    public final void simulateSpectrums() {
        if (getMainVolume() <= 0.0f) {
            super.simulateSpectrums();
        }
    }

    public final SynthesizerStatus getSynthesizerStatus() {
        return this.h;
    }

    public final PlaybackStatus getPlaybackStatus() {
        return this.i;
    }

    private void a(PcmBuffer pcmBuffer) {
        int i = this.d.size;
        int i2 = this.j.size;
        if (i > i2) {
            synchronized (this.j) {
                for (int i3 = i2; i3 < i; i3++) {
                    e.i("creating new spectrum generator " + this.d.items[i3], new Object[0]);
                    this.j.add(new SpectrumGenerator(this.d.items[i3], (byte) 0));
                }
            }
        }
        int i4 = this.j.size;
        for (int i5 = 0; i5 < i4; i5++) {
            this.j.items[i5].a(pcmBuffer, getSampleRate());
        }
    }

    private void b() {
        this.k = false;
        e.i("restartPlaybackThread", new Object[0]);
        if (this.s != null) {
            this.s.interrupt();
            e.i("restartPlaybackThread - playbackThread interrupted", new Object[0]);
        }
        c();
        this.s = new Thread(() -> {
            boolean z;
            this.i = PlaybackStatus.STARTING;
            int hashCode = hashCode() % 1000;
            e.i("started playback thread " + hashCode, new Object[0]);
            do {
                z = false;
                PcmBuffer pcmBuffer = null;
                while (pcmBuffer == null) {
                    try {
                        if (this.f.size != 0) {
                            synchronized (this.f) {
                                pcmBuffer = this.f.removeIndex(0);
                            }
                        }
                        if (pcmBuffer == null) {
                            this.i = PlaybackStatus.SLEEP_NO_BUFFER;
                            Thread.sleep(20L);
                        }
                    } catch (InterruptedException unused) {
                    } catch (Exception e2) {
                        e.i("exception in playback thread " + hashCode, e2);
                    }
                }
                AudioDevice audioDevice = this.l;
                if (this.o && this.n && audioDevice != null && this.m) {
                    this.i = PlaybackStatus.ACTIVE;
                    try {
                        this.q = Game.getTimestampMillis();
                        audioDevice.writeSamples(pcmBuffer.data, 0, pcmBuffer.length);
                        this.lastSoundTimestamp = Game.getTimestampMillis();
                        if (this.q == -2) {
                            e.e("playback thread " + hashCode + " stops because someone has requested that due to large writeSamples delay", new Object[0]);
                            z = true;
                            this.k = true;
                        }
                        this.q = -1L;
                    } catch (GdxRuntimeException e3) {
                        e.e("writeSamples failed - GdxRuntimeException " + hashCode, e3);
                        z = true;
                        this.k = true;
                    } catch (UnsatisfiedLinkError e4) {
                        e.e("writeSamples failed - UnsatisfiedLinkError " + hashCode, e4);
                        z = true;
                        this.k = true;
                    }
                } else {
                    if (audioDevice == null || !this.m) {
                        this.i = PlaybackStatus.SLEEP_NO_AUDIO_DEVICE;
                    } else if (!this.n) {
                        this.i = PlaybackStatus.SLEEP_APP_INACTIVE;
                    } else {
                        this.i = PlaybackStatus.SLEEP_NOT_PLAYING;
                    }
                    Thread.sleep(50L);
                }
                a(pcmBuffer);
                boolean z2 = false;
                synchronized (this.g) {
                    int i = 0;
                    while (true) {
                        if (i >= this.g.length) {
                            break;
                        }
                        if (this.g[i] != null) {
                            i++;
                        } else {
                            this.g[i] = pcmBuffer;
                            z2 = true;
                            break;
                        }
                    }
                }
                if (!z2) {
                    e.e("buffer not freed after playback - no space", new Object[0]);
                }
            } while (!z);
            e.i("stopped playback thread " + hashCode, new Object[0]);
            this.i = PlaybackStatus.STOPPED;
        }, "IBXM playback");
        this.s.setDaemon(true);
        CrashHandler.handleThreadExceptionsForgiving(this.s);
        this.s.start();
    }

    public final int getSampleRate() {
        return 44100;
    }

    private void c() {
        if (this.l != null) {
            if (this.l instanceof RestartableAudioDevice) {
                e.i("restarting old audioDevice", new Object[0]);
                ((RestartableAudioDevice) this.l).restart();
                e.i("old audioDevice restarted", new Object[0]);
            } else {
                e.i("disposing old audioDevice", new Object[0]);
                this.m = false;
                this.l.dispose();
                this.l = null;
                e.i("old audioDevice disposed", new Object[0]);
            }
        }
        if (this.ibxm != null) {
            this.ibxm.setSampleRate(getSampleRate());
        }
        if (this.l != null) {
            return;
        }
        try {
            e.i("creating new audio device...", new Object[0]);
            this.l = Game.i.actionResolver.newAudioDevice(getSampleRate(), false);
            this.m = true;
            e.i("set up new audio device", new Object[0]);
        } catch (Exception e2) {
            e.e("failed to setup audio device", e2);
            Timer.schedule(new Timer.Task(this) { // from class: com.prineside.tdi2.managers.music.LiveMusicManager.2
                @Override // com.badlogic.gdx.utils.Timer.Task, java.lang.Runnable
                public void run() {
                    Threads.i().runOnMainThread(() -> {
                        if (Game.isLoaded()) {
                            Notifications.i().add("Failed to setup audio device", null, MaterialColor.ORANGE.P800, StaticSoundType.FAIL);
                        }
                    });
                }
            }, 5.0f);
        }
    }

    @Override // com.prineside.tdi2.managers.MusicManager
    public final void setBackendVolume(float f) {
        if (this.l != null) {
            if (Game.i != null && Game.i.debugManager != null && Game.i.debugManager.isEnabled()) {
                Game.i.debugManager.registerValue("Music backend vol").append(f);
            }
            this.l.setVolume(f);
        }
    }

    @Override // com.prineside.tdi2.managers.MusicManager, com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public final void setup() {
        this.c = true;
        this.n = true;
        super.setup();
    }

    @Override // com.prineside.tdi2.managers.MusicManager, com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public final void preRender(float f) {
        StringBuilder registerValue;
        super.preRender(f);
        if (this.k) {
            b();
        }
        if (this.c && (registerValue = Game.i.debugManager.registerValue("XM music playback queue")) != null) {
            registerValue.append(d());
            Game.i.debugManager.registerValue("XM music free buffers").append(e());
            Game.i.debugManager.registerValue("XM synthesizer").append(this.h.name());
            Game.i.debugManager.registerValue("XM playback").append(this.i.name());
        }
    }

    private int d() {
        return this.f.size;
    }

    private int e() {
        int i = 0;
        for (PcmBuffer pcmBuffer : this.g) {
            if (pcmBuffer != null) {
                i++;
            }
        }
        return i;
    }

    @Override // com.prineside.tdi2.managers.MusicManager
    public final void stopMusic() {
        a(false);
        this.ibxm = null;
        Threads.i().runOnMainThread(() -> {
            synchronized (this.j) {
                for (int i = 0; i < this.j.size; i++) {
                    this.j.get(i).f2504a.zeroSpectrums();
                }
            }
        });
    }

    @Override // com.prineside.tdi2.managers.MusicManager
    public final void playMusic(Module module) {
        if (module == null) {
            throw new IllegalArgumentException("Module is null");
        }
        stopMusic();
        this.ibxm = new IBXM(module, getSampleRate());
        this.ibxm.setInterpolation(getInterpolation());
        int mixBufferLength = this.ibxm.getMixBufferLength();
        if (this.p.length < mixBufferLength) {
            this.p = new int[mixBufferLength];
        }
        this.f2386b = module.getVolumeMultiplierFromInstrumentNames();
        a(true);
        synchronized (this.j) {
            for (int i = 0; i < this.j.size; i++) {
                SpectrumGenerator.a(this.j.get(i), 10.0f);
            }
        }
        showSongNotification(this.ibxm.getModule(), 7.0f);
    }

    @Override // com.prineside.tdi2.managers.MusicManager
    public final Module getPlayingMusic() {
        if (this.ibxm == null) {
            return null;
        }
        return this.ibxm.getModule();
    }

    private void a(boolean z) {
        this.o = z;
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.badlogic.gdx.utils.Disposable
    public final void dispose() {
        try {
            this.s.interrupt();
        } catch (Exception unused) {
        }
        try {
            this.r.interrupt();
        } catch (Exception unused2) {
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/music/LiveMusicManager$PcmBuffer.class */
    public static final class PcmBuffer {
        public short[] data = new short[4096];
        public int length;

        final void a(int i) {
            if (this.data.length < i) {
                this.data = new short[MathUtils.nextPowerOfTwo(i)];
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/music/LiveMusicManager$SpectrumGenerator.class */
    public static final class SpectrumGenerator {

        /* renamed from: a, reason: collision with root package name */
        private final MusicManager.SpectrumConfig f2504a;

        /* renamed from: b, reason: collision with root package name */
        private FFT f2505b;
        private float[] c;
        private final float[] d;
        private float e;
        private float f;

        /* synthetic */ SpectrumGenerator(MusicManager.SpectrumConfig spectrumConfig, byte b2) {
            this(spectrumConfig);
        }

        static /* synthetic */ float a(SpectrumGenerator spectrumGenerator, float f) {
            spectrumGenerator.e = 10.0f;
            return 10.0f;
        }

        private SpectrumGenerator(MusicManager.SpectrumConfig spectrumConfig) {
            this.e = 10.0f;
            this.f2504a = spectrumConfig;
            this.d = new float[spectrumConfig.getSpectrumSize()];
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(PcmBuffer pcmBuffer, int i) {
            int nextPowerOfTwo = MathUtils.nextPowerOfTwo(pcmBuffer.length - 1) / 2;
            if (this.f2505b == null || this.f2505b.getTimeSize() != nextPowerOfTwo || this.f != this.f2504a.fixedMaxValue) {
                this.f2505b = new FFT(nextPowerOfTwo, i);
                this.c = new float[nextPowerOfTwo];
                this.f = this.f2504a.fixedMaxValue;
            }
            int i2 = 0;
            while (i2 < 2) {
                for (int i3 = 3; i3 < nextPowerOfTwo; i3++) {
                    this.c[i3] = pcmBuffer.data[(i3 << 1) + i2] * 3.051757E-5f;
                }
                this.f2505b.forward(this.c);
                float[] spectrum = this.f2505b.getSpectrum();
                if (this.f2504a.fixedMaxValue > 0.0f) {
                    this.e = this.f2504a.fixedMaxValue;
                }
                for (int i4 = 0; i4 < this.d.length; i4++) {
                    int freqToIndex = this.f2505b.freqToIndex(this.f2504a.frequencies.get(i4).min);
                    int freqToIndex2 = this.f2505b.freqToIndex(this.f2504a.frequencies.get(i4).max);
                    if (freqToIndex < freqToIndex2) {
                        freqToIndex2--;
                    }
                    float f = 0.0f;
                    for (int i5 = freqToIndex; i5 <= freqToIndex2; i5++) {
                        f += spectrum[i5];
                    }
                    if (this.e < f) {
                        this.e = f;
                    }
                    this.d[i4] = MathUtils.clamp(f / this.e, 0.0f, 1.0f);
                }
                if (this.f2504a.fixedMaxValue <= 0.0f) {
                    this.e *= this.f2504a.maxValueEasing;
                }
                synchronized (this.f2504a.spectrumLock) {
                    System.arraycopy(this.d, 0, i2 == 0 ? this.f2504a.spectrumLeft : this.f2504a.spectrumRight, 0, this.d.length);
                }
                i2++;
            }
        }
    }
}
