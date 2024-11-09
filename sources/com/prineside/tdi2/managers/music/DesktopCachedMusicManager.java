package com.prineside.tdi2.managers.music;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.prineside.tdi2.ibxm.Module;
import com.prineside.tdi2.managers.MusicManager;
import com.prineside.tdi2.utils.REGS;

@REGS(serializer = MusicManager.Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/music/DesktopCachedMusicManager.class */
public class DesktopCachedMusicManager extends CachedMusicManager {
    private Music e;
    private Music f;

    @Override // com.prineside.tdi2.managers.music.CachedMusicManager
    protected final void a(Module module) {
        this.f2386b = module.getVolumeMultiplierFromInstrumentNames();
        String str = null;
        if (module.restartPos != 0) {
            str = a(module, false);
        }
        String a2 = a(module, true);
        if (str == null) {
            this.f = Gdx.audio.newMusic(Gdx.files.local(a2));
            this.f.setVolume(0.0f);
            this.f.setLooping(true);
            this.f.play();
            return;
        }
        this.e = Gdx.audio.newMusic(Gdx.files.local(str));
        this.e.setVolume(0.0f);
        this.f = Gdx.audio.newMusic(Gdx.files.local(a2));
        this.f.setVolume(0.0f);
        this.e.setOnCompletionListener(music -> {
            this.e.stop();
            this.e.dispose();
            this.e = null;
            this.f.setLooping(true);
            this.f.play();
        });
        this.f.play();
        this.f.pause();
        this.e.play();
    }

    @Override // com.prineside.tdi2.managers.music.CachedMusicManager, com.prineside.tdi2.managers.MusicManager
    public void stopMusic() {
        if (this.e != null) {
            this.e.stop();
            this.e.dispose();
            this.e = null;
        }
        if (this.f != null) {
            this.f.stop();
            this.f.dispose();
            this.f = null;
        }
        super.stopMusic();
    }

    @Override // com.prineside.tdi2.managers.MusicManager
    public void setBackendVolume(float f) {
        if (this.e != null) {
            this.e.setVolume(f);
        }
        if (this.f != null) {
            this.f.setVolume(f);
        }
    }
}
