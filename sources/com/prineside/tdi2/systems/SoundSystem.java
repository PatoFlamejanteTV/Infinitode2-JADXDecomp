package com.prineside.tdi2.systems;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pool;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.GameSystem;
import com.prineside.tdi2.Map;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.configs.GameRenderingOrder;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.events.game.AbilityApply;
import com.prineside.tdi2.events.game.CoreTileUpgradeInstall;
import com.prineside.tdi2.events.game.EnemyDie;
import com.prineside.tdi2.events.game.EnemyReachTarget;
import com.prineside.tdi2.events.game.GamePaused;
import com.prineside.tdi2.events.game.GameResumed;
import com.prineside.tdi2.events.game.MinerBuild;
import com.prineside.tdi2.events.game.MinerUpgrade;
import com.prineside.tdi2.events.game.ModifierBuild;
import com.prineside.tdi2.events.game.TowerAbilityChange;
import com.prineside.tdi2.events.game.TowerBuild;
import com.prineside.tdi2.events.game.TowerUpgrade;
import com.prineside.tdi2.events.game.WaveStatusChange;
import com.prineside.tdi2.ibxm.Module;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.systems.RenderSystem;
import com.prineside.tdi2.systems.WaveSystem;
import com.prineside.tdi2.tiles.TargetTile;
import com.prineside.tdi2.ui.shared.MusicListOverlay;
import com.prineside.tdi2.utils.FastRandom;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.logging.TLog;
import java.util.Arrays;

@NAGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/SoundSystem.class */
public final class SoundSystem extends GameSystem {

    /* renamed from: b, reason: collision with root package name */
    private int f3057b;
    private final Array<QueuedSound> c = new Array<>(QueuedSound.class);
    private final boolean[] d = new boolean[StaticSoundType.values.length];
    private final float[] e;
    private final float[] f;

    @Null
    private Runnable g;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3056a = TLog.forClass(SoundSystem.class);
    private static final Vector2 h = new Vector2();

    public SoundSystem() {
        this.f3057b = 44;
        this.d[StaticSoundType.SHOT_GAUSS.ordinal()] = true;
        this.d[StaticSoundType.BUTTON.ordinal()] = true;
        this.d[StaticSoundType.UPGRADE.ordinal()] = true;
        this.d[StaticSoundType.ENEMY_REACHED.ordinal()] = true;
        this.d[StaticSoundType.FAIL.ordinal()] = true;
        this.d[StaticSoundType.GAME_OVER.ordinal()] = true;
        this.d[StaticSoundType.NOTIFICATION.ordinal()] = true;
        this.d[StaticSoundType.SUCCESS.ordinal()] = true;
        this.d[StaticSoundType.LOOT_EPIC.ordinal()] = true;
        this.d[StaticSoundType.LOOT_LEGENDARY.ordinal()] = true;
        this.d[StaticSoundType.BUILDING_BUILT.ordinal()] = true;
        this.d[StaticSoundType.AUTO_FORCE_WAVE.ordinal()] = true;
        this.d[StaticSoundType.WARNING.ordinal()] = true;
        this.d[StaticSoundType.ABILITY_NUKE.ordinal()] = true;
        this.d[StaticSoundType.ABILITY_BLIZZARD.ordinal()] = true;
        this.d[StaticSoundType.ABILITY_FIREBALL.ordinal()] = true;
        this.d[StaticSoundType.ABILITY_WINDSTORM.ordinal()] = true;
        this.d[StaticSoundType.ABILITY_THUNDER.ordinal()] = true;
        this.d[StaticSoundType.ABILITY_SMOKE_BOMB.ordinal()] = true;
        this.d[StaticSoundType.ABILITY_FIRESTORM.ordinal()] = true;
        this.d[StaticSoundType.ABILITY_MAGNET.ordinal()] = true;
        this.d[StaticSoundType.ABILITY_BULLET_WALL.ordinal()] = true;
        this.d[StaticSoundType.ABILITY_BALL_LIGHTNING.ordinal()] = true;
        this.d[StaticSoundType.ABILITY_LOIC.ordinal()] = true;
        this.d[StaticSoundType.ABILITY_OVERLOAD.ordinal()] = true;
        this.e = new float[StaticSoundType.values.length];
        Arrays.fill(this.e, 0.08f);
        this.e[StaticSoundType.EXPLOSION.ordinal()] = 0.16f;
        this.e[StaticSoundType.LOOT_COMMON.ordinal()] = 0.3f;
        this.e[StaticSoundType.LOOT_RARE.ordinal()] = 0.25f;
        this.e[StaticSoundType.LOOT_VERY_RARE.ordinal()] = 0.2f;
        this.e[StaticSoundType.LOOT_EPIC.ordinal()] = 0.15f;
        this.e[StaticSoundType.LOOT_LEGENDARY.ordinal()] = 0.12f;
        this.e[StaticSoundType.SHOT_BLAST.ordinal()] = 0.15f;
        this.f = new float[StaticSoundType.values.length];
        if (Gdx.app.getType() == Application.ApplicationType.Android) {
            this.f3057b = 29;
            for (int i = 0; i < this.e.length; i++) {
                float[] fArr = this.e;
                int i2 = i;
                fArr[i2] = fArr[i2] * 1.7f;
            }
        }
    }

    @Override // com.prineside.tdi2.GameSystem
    public final boolean profileUpdate() {
        return false;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final String getSystemName() {
        return "Sound";
    }

    @Override // com.prineside.tdi2.GameSystem
    public final boolean affectsGameState() {
        return false;
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void setup() {
        this.S.events.getListeners(GamePaused.class).add(gamePaused -> {
            updateMusicPlayback();
        }).setDescription("SoundSystem - updates music playback");
        this.S.events.getListeners(GameResumed.class).add(gameResumed -> {
            updateMusicPlayback();
        }).setDescription("SoundSystem - updates music playback");
        this.S.events.getListeners(AbilityApply.class).add(abilityApply -> {
            switch (abilityApply.getAbility().getType()) {
                case NUKE:
                    playStatic(StaticSoundType.ABILITY_NUKE);
                    return;
                case BLIZZARD:
                    playStatic(StaticSoundType.ABILITY_BLIZZARD);
                    return;
                case FIREBALL:
                    playStatic(StaticSoundType.ABILITY_FIREBALL);
                    return;
                case WINDSTORM:
                    playStatic(StaticSoundType.ABILITY_WINDSTORM);
                    return;
                case THUNDER:
                    playStatic(StaticSoundType.ABILITY_THUNDER);
                    return;
                case SMOKE_BOMB:
                    playStatic(StaticSoundType.ABILITY_SMOKE_BOMB);
                    return;
                case FIRESTORM:
                    playStatic(StaticSoundType.ABILITY_FIRESTORM);
                    return;
                case MAGNET:
                    playStatic(StaticSoundType.ABILITY_MAGNET);
                    return;
                case BULLET_WALL:
                    playStatic(StaticSoundType.ABILITY_BULLET_WALL);
                    return;
                case BALL_LIGHTNING:
                    playStatic(StaticSoundType.ABILITY_BALL_LIGHTNING);
                    return;
                case LOIC:
                    playStatic(StaticSoundType.ABILITY_LOIC);
                    return;
                case OVERLOAD:
                    playStatic(StaticSoundType.ABILITY_OVERLOAD);
                    return;
                case LOOP:
                    playStatic(StaticSoundType.ABILITY_LOOP);
                    return;
                default:
                    return;
            }
        }).setDescription("SoundSystem - plays ability sound");
        this.S.events.getListeners(EnemyDie.class).add(enemyDie -> {
            float a2 = a(enemyDie.getLastDamage().getEnemy().getPosition().x);
            float b2 = (float) (b(a2) * Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.SHOOTING_SOUNDS_VOLUME));
            if (b2 > 0.01f) {
                float f = 1.0f;
                float f2 = FastRandom.getFloat();
                if (f2 < 0.25f) {
                    f = 1.12246f;
                } else if (f2 < 0.5f) {
                    f = 1.059465f;
                } else if (f2 < 0.75f) {
                    f = 0.943876f;
                }
                playStaticParameterized(StaticSoundType.ENEMY_DIE, b2 * 0.7f, f, a2);
            }
        });
        this.S.events.getListeners(EnemyReachTarget.class).add(enemyReachTarget -> {
            if (enemyReachTarget.getEnemy().getCurrentTile() instanceof TargetTile) {
                playStatic(StaticSoundType.ENEMY_REACHED);
            }
        });
        this.S.events.getListeners(CoreTileUpgradeInstall.class).add(coreTileUpgradeInstall -> {
            playStatic(StaticSoundType.UPGRADE);
        });
        this.S.events.getListeners(MinerUpgrade.class).add(minerUpgrade -> {
            playStatic(StaticSoundType.UPGRADE);
        });
        this.S.events.getListeners(MinerBuild.class).add(minerBuild -> {
            playStatic(StaticSoundType.BUILDING_BUILT);
        });
        this.S.events.getListeners(ModifierBuild.class).add(modifierBuild -> {
            playStatic(StaticSoundType.BUILDING_BUILT);
        });
        this.S.events.getListeners(TowerBuild.class).add(towerBuild -> {
            playStatic(StaticSoundType.BUILDING_BUILT);
        });
        this.S.events.getListeners(TowerUpgrade.class).add(towerUpgrade -> {
            playStatic(StaticSoundType.UPGRADE);
        });
        this.S.events.getListeners(TowerAbilityChange.class).add(towerAbilityChange -> {
            if (towerAbilityChange.isInstalled()) {
                playStatic(StaticSoundType.UPGRADE);
            }
        });
        this.S.events.getListeners(WaveStatusChange.class).add(waveStatusChange -> {
            if (waveStatusChange.getOldStatus() == WaveSystem.Status.NOT_STARTED) {
                updateMusicPlayback();
            }
        });
        this.S._render.addLayer(new RenderSystem.Layer(GameRenderingOrder.SOUND_DRAW, false, (batch, f, f2, f3) -> {
            draw(f);
        }).setName("Sound-draw"));
    }

    @Override // com.prineside.tdi2.GameSystem
    public final void postSetup() {
        super.postSetup();
        updateMusicPlayback();
    }

    public final void setMusicPlaybackHandler(@Null Runnable runnable) {
        this.g = runnable;
    }

    public final void updateMusicPlayback() {
        if (this.g != null) {
            f3056a.i("updateMusicPlayback - using custom handler", new Object[0]);
            this.g.run();
            return;
        }
        f3056a.i("updateMusicPlayback - using default handler", new Object[0]);
        if (this.S.wave.status == null || this.S.wave.status == WaveSystem.Status.NOT_STARTED || !Game.i.settingsManager.isMusicEnabled()) {
            f3056a.i("- not started - stopping music", new Object[0]);
            Game.i.musicManager.stopMusic();
            return;
        }
        if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.IGNORE_MAP_MUSIC) == 1.0d) {
            f3056a.i("- map music ignored, playing menu music", new Object[0]);
            Game.i.musicManager.continuePlayingMenuMusicTrack();
        } else {
            Map map = null;
            if (this.S.gameState.basicLevelName != null) {
                map = Game.i.basicLevelManager.getLevel(this.S.gameState.basicLevelName).getMap();
            } else if (this.S.gameState.userMapId != null) {
                map = Game.i.userMapManager.getUserMap(this.S.gameState.userMapId).map;
            }
            if (map != null && map.getMusicTile() != null && map.getMusicTile().getModule() != null) {
                f3056a.i("- found a map with a music tile", new Object[0]);
                Module module = map.getMusicTile().getModule();
                Module playingMusic = Game.i.musicManager.getPlayingMusic();
                int moduleHashCode = module == null ? -1 : module.moduleHashCode();
                int moduleHashCode2 = playingMusic == null ? -1 : playingMusic.moduleHashCode();
                if (moduleHashCode != moduleHashCode2) {
                    f3056a.i("- playing different music from the map " + module + " (" + moduleHashCode + ") / " + playingMusic + " (" + moduleHashCode2 + ")", new Object[0]);
                    Game.i.musicManager.playMusic(map.getMusicTile().getModule());
                    Game.i.musicManager.setVolumeToStartNewTrack();
                } else {
                    f3056a.i("- playing same music from the map", new Object[0]);
                    Game.i.musicManager.setVolume(1.0f, 2.0f, false);
                }
            } else {
                f3056a.i("- no map / music tile, playing menu music", new Object[0]);
                Game.i.musicManager.continuePlayingMenuMusicTrack();
            }
        }
        if (this.S.gameState.isPaused() && !MusicListOverlay.i().isVisible()) {
            f3056a.i("- fading out the volume", new Object[0]);
            Game.i.musicManager.setVolume(0.25f, 2.0f, false);
        } else {
            f3056a.i("- fading in the volume", new Object[0]);
            Game.i.musicManager.setVolume(1.0f, 2.0f, false);
        }
    }

    public final void draw(float f) {
        if (this.c.size != 0) {
            for (int i = this.c.size - 1; i >= 0; i--) {
                QueuedSound queuedSound = this.c.items[i];
                Game.i.soundManager.playStaticParameterized(queuedSound.f3059a, queuedSound.c, queuedSound.f3060b, queuedSound.d, false);
            }
            this.c.clear();
        }
        for (int i2 = 0; i2 < this.f.length; i2++) {
            float[] fArr = this.f;
            int i3 = i2;
            fArr[i3] = fArr[i3] - f;
            if (this.f[i2] < 0.0f) {
                this.f[i2] = 0.0f;
            }
        }
    }

    private float a(float f) {
        h.set(f, 0.0f);
        this.S._input.cameraController.mapToViewport(h);
        return (-0.5f) + (h.x / this.S._input.cameraController.camera.viewportWidth);
    }

    private float b(float f) {
        float f2 = 1.0f - (((float) (this.S._input.cameraController.zoom - 0.5d)) * 0.25f);
        if (f < -0.5f) {
            f2 *= 1.0f + ((f + 0.5f) * 2.0f);
        } else if (f > 0.5f) {
            f2 *= 1.0f - ((f - 0.5f) * 2.0f);
        }
        return f2;
    }

    public final void playShotSound(StaticSoundType staticSoundType, Tower tower) {
        float a2 = a(tower.getTile().center.x);
        float b2 = (float) (b(a2) * Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.SHOOTING_SOUNDS_VOLUME));
        if (b2 > 0.05f) {
            playStaticParameterized(staticSoundType, b2 * 0.6f, 1.0f, a2);
        }
    }

    public final void playStatic(StaticSoundType staticSoundType) {
        playStaticParameterized(staticSoundType, 1.0f, 1.0f, 0.0f);
    }

    public final void playExplosionSound(float f) {
        float a2 = a(f);
        float b2 = (float) (b(a2) * Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.SHOOTING_SOUNDS_VOLUME));
        if (b2 > 0.05f) {
            playStaticParameterized(StaticSoundType.EXPLOSION, b2 * 0.6f, 0.9f + (FastRandom.getFloat() * 0.2f), a2);
        }
    }

    public final void playStaticParameterized(StaticSoundType staticSoundType, float f, float f2, float f3) {
        if (this.S.CFG.headless || this.S.gameState.isFastForwarding() || this.f[staticSoundType.ordinal()] != 0.0f) {
            return;
        }
        if (Game.i.soundManager.playingSoundStats.size + Game.i.soundManager.soundsToPlay.size >= this.f3057b && !this.d[staticSoundType.ordinal()]) {
            return;
        }
        for (int i = 0; i < this.c.size; i++) {
            if (this.c.items[i].f3059a == staticSoundType) {
                return;
            }
        }
        QueuedSound queuedSound = new QueuedSound((byte) 0);
        queuedSound.f3059a = staticSoundType;
        queuedSound.d = f3;
        queuedSound.c = f;
        queuedSound.f3060b = f2;
        this.c.add(queuedSound);
        float f4 = this.e[staticSoundType.ordinal()];
        int i2 = Game.i.soundManager.playingSoundStats.size + Game.i.soundManager.soundsToPlay.size;
        if (i2 >= this.f3057b * 0.9f) {
            f4 *= 2.0f;
        } else if (i2 >= this.f3057b * 0.75f) {
            f4 *= 1.5f;
        } else if (i2 >= this.f3057b * 0.5f) {
            f4 *= 1.25f;
        }
        this.f[staticSoundType.ordinal()] = f4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/systems/SoundSystem$QueuedSound.class */
    public static class QueuedSound implements Pool.Poolable {

        /* renamed from: a, reason: collision with root package name */
        StaticSoundType f3059a;

        /* renamed from: b, reason: collision with root package name */
        float f3060b;
        float c;
        float d;

        private QueuedSound() {
        }

        /* synthetic */ QueuedSound(byte b2) {
            this();
        }

        @Override // com.badlogic.gdx.utils.Pool.Poolable
        public void reset() {
        }
    }
}
