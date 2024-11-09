package com.prineside.tdi2.managers;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Pool;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Manager;
import com.prineside.tdi2.StaticSound;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.errorhandling.CrashHandler;
import com.prineside.tdi2.utils.logging.TLog;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/SoundManager.class */
public class SoundManager extends Manager.ManagerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2464a = TLog.forClass(SoundManager.class);

    /* renamed from: b, reason: collision with root package name */
    private Thread f2465b;
    public final Array<SoundCfg> soundsToPlay = new Array<>(SoundCfg.class);
    private final Array<SoundCfg> c = new Array<>(SoundCfg.class);
    private final Array<SoundCfg> d = new Array<>(SoundCfg.class);
    public final DelayedRemovalArray<PlayingSoundStat> playingSoundStats = new DelayedRemovalArray<>(false, 64, PlayingSoundStat.class);

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/SoundManager$PlayingSoundStat.class */
    public static class PlayingSoundStat {
        public StaticSoundType type;
        public int durationLeft;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/SoundManager$Serializer.class */
    public static class Serializer extends SingletonSerializer<SoundManager> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public SoundManager read() {
            return Game.i.soundManager;
        }
    }

    public SoundManager() {
        b();
    }

    private void b() {
        this.f2465b = new Thread(new Runnable() { // from class: com.prineside.tdi2.managers.SoundManager.1

            /* renamed from: a, reason: collision with root package name */
            private long f2466a = -1;

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r0v18, types: [int] */
            /* JADX WARN: Type inference failed for: r0v49 */
            /* JADX WARN: Type inference failed for: r0v51 */
            /* JADX WARN: Type inference failed for: r0v79, types: [com.prineside.tdi2.utils.logging.TLog] */
            /* JADX WARN: Type inference failed for: r0v91 */
            /* JADX WARN: Type inference failed for: r0v92 */
            @Override // java.lang.Runnable
            public void run() {
                while (true) {
                    long timestampMillis = Game.getTimestampMillis();
                    long j = timestampMillis - this.f2466a;
                    if (this.f2466a == -1) {
                        j = 0;
                    }
                    this.f2466a = timestampMillis;
                    SoundManager.this.playingSoundStats.begin();
                    for (int i = 0; i < SoundManager.this.playingSoundStats.size; i++) {
                        PlayingSoundStat playingSoundStat = SoundManager.this.playingSoundStats.items[i];
                        playingSoundStat.durationLeft = (int) (playingSoundStat.durationLeft - j);
                        if (playingSoundStat.durationLeft <= 0) {
                            SoundManager.this.playingSoundStats.removeIndex(i);
                        }
                    }
                    SoundManager.this.playingSoundStats.end();
                    InterruptedException interruptedException = SoundManager.this.soundsToPlay.size;
                    if (interruptedException != 0) {
                        SoundManager.this.c.clear();
                        synchronized (SoundManager.this.soundsToPlay) {
                            SoundManager.this.c.addAll(SoundManager.this.soundsToPlay);
                            SoundManager.this.soundsToPlay.clear();
                        }
                        for (int i2 = 0; i2 < SoundManager.this.c.size; i2++) {
                            SoundCfg soundCfg = ((SoundCfg[]) SoundManager.this.c.items)[i2];
                            PlayingSoundStat playingSoundStat2 = new PlayingSoundStat();
                            playingSoundStat2.type = soundCfg.f2469a.type;
                            playingSoundStat2.durationLeft = (int) (soundCfg.f2469a.durationMs / soundCfg.c);
                            SoundManager.this.playingSoundStats.add(playingSoundStat2);
                            try {
                                soundCfg.f2469a.sound.play(soundCfg.f2470b, soundCfg.c, soundCfg.d);
                            } catch (Exception unused) {
                            } finally {
                                soundCfg.reset();
                            }
                        }
                        synchronized (SoundManager.this.d) {
                            SoundManager.this.d.addAll(SoundManager.this.c);
                        }
                        SoundManager.this.c.clear();
                        Game game = Game.i;
                        InterruptedException interruptedException2 = game;
                        if (game != null) {
                            boolean isDisposed = Game.i.isDisposed();
                            interruptedException2 = isDisposed;
                            if (isDisposed) {
                                SoundManager.f2464a.i("game is disposed - stopping Sound thread (2)", new Object[0]);
                                return;
                            }
                        }
                        try {
                            interruptedException2 = 10;
                            Thread.sleep(10L);
                        } catch (InterruptedException e) {
                            interruptedException2.printStackTrace();
                            return;
                        }
                    } else {
                        try {
                            if (Game.isLoaded() && Game.i.isDisposed()) {
                                interruptedException = SoundManager.f2464a;
                                interruptedException.i("game is disposed - stopping Sound thread (1)", new Object[0]);
                                return;
                            }
                            Thread.sleep(16L);
                        } catch (InterruptedException e2) {
                            interruptedException.printStackTrace();
                            return;
                        }
                    }
                }
            }
        }, "Sounds");
        this.f2465b.setDaemon(true);
        this.f2465b.start();
        CrashHandler.handleThreadExceptionsForgiving(this.f2465b);
    }

    private void a(StaticSound staticSound, float f, float f2, float f3, boolean z) {
        if (staticSound == null) {
            throw new IllegalArgumentException("sound is nul");
        }
        if (Game.i.settingsManager.isSoundEnabled()) {
            float customValue = f * ((float) Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.SOUND_VOLUME));
            if (z) {
                staticSound.sound.loop(customValue, f2, f3);
                return;
            }
            synchronized (this.soundsToPlay) {
                for (int i = 0; i < this.soundsToPlay.size; i++) {
                    SoundCfg soundCfg = this.soundsToPlay.items[i];
                    if (soundCfg.f2469a == staticSound) {
                        soundCfg.f2470b = StrictMath.max(soundCfg.f2470b, customValue);
                        soundCfg.d = (soundCfg.d + f3) * 0.5f;
                        return;
                    }
                }
                SoundCfg soundCfg2 = null;
                synchronized (this.d) {
                    if (this.d.size > 0) {
                        soundCfg2 = this.d.pop();
                    }
                }
                if (soundCfg2 == null) {
                    soundCfg2 = new SoundCfg((byte) 0);
                }
                soundCfg2.f2469a = staticSound;
                soundCfg2.f2470b = customValue;
                soundCfg2.d = f3;
                soundCfg2.c = f2;
                this.soundsToPlay.add(soundCfg2);
            }
        }
    }

    public void playStatic(StaticSoundType staticSoundType) {
        playStaticParameterized(staticSoundType, 1.0f, 1.0f, 0.0f, false);
    }

    public void playStaticParameterized(StaticSoundType staticSoundType, float f, float f2, float f3, boolean z) {
        StaticSound sound;
        if (Game.i.settingsManager.isSoundEnabled() && (sound = Game.i.assetManager.getSound(staticSoundType)) != null) {
            a(sound, f, f2, f3, z);
        }
    }

    public StaticSoundType getRarity(RarityType rarityType) {
        switch (rarityType) {
            case COMMON:
                return StaticSoundType.LOOT_COMMON;
            case RARE:
                return StaticSoundType.LOOT_RARE;
            case VERY_RARE:
                return StaticSoundType.LOOT_VERY_RARE;
            case EPIC:
                return StaticSoundType.LOOT_EPIC;
            case LEGENDARY:
                return StaticSoundType.LOOT_LEGENDARY;
            default:
                return null;
        }
    }

    public void playRarity(RarityType rarityType) {
        playStatic(getRarity(rarityType));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/SoundManager$SoundCfg.class */
    public static class SoundCfg implements Pool.Poolable {

        /* renamed from: a, reason: collision with root package name */
        StaticSound f2469a;

        /* renamed from: b, reason: collision with root package name */
        float f2470b;
        float c;
        float d;

        private SoundCfg() {
        }

        /* synthetic */ SoundCfg(byte b2) {
            this();
        }

        @Override // com.badlogic.gdx.utils.Pool.Poolable
        public void reset() {
            this.f2469a = null;
        }
    }
}
