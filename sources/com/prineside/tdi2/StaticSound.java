package com.prineside.tdi2;

import com.badlogic.gdx.audio.Sound;
import com.prineside.tdi2.enums.StaticSoundType;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/StaticSound.class */
public class StaticSound {
    public StaticSoundType type;
    public Sound sound;
    public int durationMs;

    public StaticSound(StaticSoundType staticSoundType, Sound sound, int i) {
        this.type = staticSoundType;
        this.sound = sound;
        this.durationMs = i;
    }
}
