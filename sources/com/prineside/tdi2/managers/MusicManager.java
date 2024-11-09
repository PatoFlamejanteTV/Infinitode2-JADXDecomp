package com.prineside.tdi2.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.IntIntMap;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.StringBuilder;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Manager;
import com.prineside.tdi2.ibxm.Module;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.managers.music.LiveMusicManager;
import com.prineside.tdi2.managers.music.RecordedSpectrum;
import com.prineside.tdi2.managers.preferences.categories.SettingsPrefs;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.InputListener;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.tiles.EqualizerTile;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.LimitedWidthLabel;
import com.prineside.tdi2.ui.shared.Notifications;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.html.Attribute;
import java.io.ByteArrayInputStream;
import java.lang.ref.SoftReference;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/MusicManager.class */
public abstract class MusicManager extends Manager.ManagerAdapter {
    public static final float DEFAULT_VOLUME_CHANGE_SPEED = 2.0f;

    /* renamed from: a, reason: collision with root package name */
    protected boolean f2385a;
    protected boolean c;
    private float k;
    public long lastSoundTimestamp;
    private RecordedSpectrum p;
    private static final TLog e = TLog.forClass(MusicManager.class);
    private static final Color f = new Color(255).mul(1.0f, 1.0f, 1.0f, 1.0f);
    private static final Color g = new Color(-508394241);
    private static final Array<ModuleCacheConfig> m = new Array<>(ModuleCacheConfig.class);
    private static final Comparator<ModuleCacheConfig> n = (moduleCacheConfig, moduleCacheConfig2) -> {
        return Integer.compare(moduleCacheConfig2.lastUsed, moduleCacheConfig.lastUsed);
    };
    private float h = 0.0f;
    private float i = 1.0f;
    private float j = 0.0f;

    /* renamed from: b, reason: collision with root package name */
    protected float f2386b = 1.0f;
    private final IntMap<SoftReference<ModuleCacheConfig>> l = new IntMap<>();
    private final IntIntMap o = new IntIntMap();
    protected final Array<SpectrumConfig> d = new Array<>(true, 1, SpectrumConfig.class);

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/MusicManager$ModuleCacheConfig.class */
    public static class ModuleCacheConfig {
        public int hash;
        public Module module;
        public int lastUsed;
        public int size;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/MusicManager$MusicSourceType.class */
    public enum MusicSourceType {
        DEFAULT,
        BASIC_LEVEL,
        USER_MAP;

        public static final MusicSourceType[] values = values();
    }

    public abstract void stopMusic();

    protected abstract void setBackendVolume(float f2);

    public abstract void playMusic(Module module);

    public abstract Module getPlayingMusic();

    public MusicManager() {
        this.d.add(SpectrumConfig.DEFAULT);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/MusicManager$Serializer.class */
    public static class Serializer extends SingletonSerializer<MusicManager> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public MusicManager read() {
            return Game.i.musicManager;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/MusicManager$MusicSource.class */
    public static class MusicSource {
        public static final MusicSource DEFAULT = new MusicSource(MusicSourceType.DEFAULT, null);
        public MusicSourceType type;
        public String id;
        public int repeats = 1;

        public MusicSource(MusicSourceType musicSourceType, String str) {
            this.type = musicSourceType;
            this.id = str;
        }

        public Module getModule() {
            if (this.type == MusicSourceType.BASIC_LEVEL) {
                try {
                    return Game.i.basicLevelManager.getLevel(this.id).getMap().getMusicTile().getModule();
                } catch (Exception unused) {
                    return null;
                }
            }
            if (this.type == MusicSourceType.USER_MAP) {
                try {
                    return Game.i.userMapManager.getUserMap(this.id).map.getMusicTile().getModule();
                } catch (Exception unused2) {
                    return null;
                }
            }
            if (this.type == MusicSourceType.DEFAULT) {
                try {
                    return Game.i.assetManager.getMenuXmSoundTrack();
                } catch (Exception unused3) {
                    return null;
                }
            }
            return null;
        }

        public String getBase64() {
            if (this.type == MusicSourceType.BASIC_LEVEL) {
                return Game.i.basicLevelManager.getLevel(this.id).getMap().getMusicTile().getTrackBase64();
            }
            if (this.type == MusicSourceType.USER_MAP) {
                return Game.i.userMapManager.getUserMap(this.id).map.getMusicTile().getTrackBase64();
            }
            return null;
        }

        public boolean sameAs(MusicSource musicSource) {
            if (this.type != musicSource.type) {
                return false;
            }
            return this.type == MusicSourceType.DEFAULT || this.id == null || this.id.equals(musicSource.id);
        }

        public void toJson(Json json) {
            json.writeValue("type", this.type.name());
            if (this.id != null) {
                json.writeValue(Attribute.ID_ATTR, this.id);
            }
            if (this.repeats > 1) {
                json.writeValue("repeats", Integer.valueOf(this.repeats));
            }
        }

        public static MusicSource fromJson(JsonValue jsonValue) {
            try {
                MusicSourceType valueOf = MusicSourceType.valueOf(jsonValue.getString("type", MusicSourceType.DEFAULT.name()));
                String string = jsonValue.getString(Attribute.ID_ATTR, null);
                int i = jsonValue.getInt("repeats", 1);
                MusicSource musicSource = new MusicSource(valueOf, string);
                musicSource.repeats = i;
                return musicSource;
            } catch (Exception e) {
                MusicManager.e.e("failed MusicSource.fromJson: " + jsonValue.toString(), e);
                return DEFAULT;
            }
        }

        public String toString() {
            return super.toString() + " (" + this.type + ":" + this.id + ")";
        }
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void setup() {
        this.c = true;
        Game.i.settingsManager.addListener(new SettingsManager.SettingsManagerListener.SettingsManagerListenerAdapter() { // from class: com.prineside.tdi2.managers.MusicManager.1
            @Override // com.prineside.tdi2.managers.SettingsManager.SettingsManagerListener.SettingsManagerListenerAdapter, com.prineside.tdi2.managers.SettingsManager.SettingsManagerListener
            public void customValueChanged(SettingsManager.CustomValueType customValueType, double d) {
                if (customValueType == SettingsManager.CustomValueType.MUSIC_VOLUME) {
                    MusicManager.this.setVolume(MusicManager.this.i, 0.0f, false);
                }
            }
        });
    }

    public SpectrumConfig getSpectrumConfig(SpectrumConfig spectrumConfig) {
        for (int i = 0; i < this.d.size; i++) {
            if (spectrumConfig.sameAs(this.d.items[i])) {
                return this.d.items[i];
            }
        }
        e.i("registering new spectrum config " + spectrumConfig, new Object[0]);
        this.d.add(spectrumConfig);
        return spectrumConfig;
    }

    public int getInterpolation() {
        if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.SMOOTH_MUSIC) == 0.0d) {
            return 0;
        }
        return 2;
    }

    public float getMainVolume() {
        return (float) Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.MUSIC_VOLUME);
    }

    public RecordedSpectrum getSpectrumSim() {
        if (this.p == null) {
            e.i("loading spectrum sim", new Object[0]);
            this.p = RecordedSpectrum.fromFile(AssetManager.localOrInternalFile("res/music-spectrum-sim.bin"));
        }
        return this.p;
    }

    public boolean isMenuMusicSourceEnabled(MusicSource musicSource) {
        Array<MusicSource> array = SettingsPrefs.i().music.menuThemeSources;
        for (int i = 0; i < array.size; i++) {
            if (array.items[i].sameAs(musicSource)) {
                return true;
            }
        }
        return false;
    }

    public void addMenuMusicSource(MusicSource musicSource) {
        if (isMenuMusicSourceEnabled(musicSource)) {
            return;
        }
        Array<MusicSource> array = SettingsPrefs.i().music.menuThemeSources;
        array.clear();
        array.add(musicSource);
        SettingsPrefs.i().requireSave();
    }

    public int getMenuMusicSourceRepeatCount(MusicSource musicSource) {
        Array<MusicSource> array = SettingsPrefs.i().music.menuThemeSources;
        for (int i = 0; i < array.size; i++) {
            MusicSource musicSource2 = array.items[i];
            if (musicSource2.sameAs(musicSource)) {
                return musicSource2.repeats;
            }
        }
        return 1;
    }

    public void removeMenuMusicSource(MusicSource musicSource) {
        Array<MusicSource> array = SettingsPrefs.i().music.menuThemeSources;
        for (int i = 0; i < array.size; i++) {
            if (musicSource.sameAs(array.items[i])) {
                array.removeIndex(i);
                if (array.size == 0) {
                    array.add(MusicSource.DEFAULT);
                }
                SettingsPrefs.i().requireSave();
                return;
            }
        }
    }

    public void voteThumbsUp(int i, boolean z) {
        IntArray intArray = SettingsPrefs.i().music.thumbsUpMusicHashes;
        if (z) {
            if (!intArray.contains(i)) {
                intArray.add(i);
                SettingsPrefs.i().requireSave();
                return;
            }
            return;
        }
        if (intArray.removeValue(i)) {
            SettingsPrefs.i().requireSave();
        }
    }

    public boolean isMusicThumbsUp(int i) {
        return SettingsPrefs.i().music.thumbsUpMusicHashes.contains(i);
    }

    public Array<MusicSource> getMenuThemeSources() {
        return SettingsPrefs.i().music.menuThemeSources;
    }

    public MusicSource getCurrentlyPlayingMenuThemeSource() {
        return SettingsPrefs.i().music.menuThemeSources.first();
    }

    public int getMusicB64hash(String str) {
        int i;
        int hashCode = str.hashCode();
        synchronized (this.o) {
            int i2 = this.o.get(hashCode, 0);
            i = i2;
            if (i2 == 0) {
                i = 1;
                int length = str.length();
                for (int i3 = 0; i3 < length; i3++) {
                    i = (i * 31) + str.charAt(i3);
                }
                this.o.put(hashCode, i);
            }
        }
        return i;
    }

    public Module getModule(String str) {
        ModuleCacheConfig moduleCacheConfig;
        int musicB64hash = getMusicB64hash(str);
        synchronized (this.l) {
            if (this.l.containsKey(musicB64hash) && (moduleCacheConfig = this.l.get(musicB64hash).get()) != null) {
                return moduleCacheConfig.module;
            }
            try {
                Module fromZipInputStream = Module.fromZipInputStream(new ByteArrayInputStream(Base64Coder.decode(str)));
                ModuleCacheConfig moduleCacheConfig2 = new ModuleCacheConfig();
                moduleCacheConfig2.hash = musicB64hash;
                moduleCacheConfig2.module = fromZipInputStream;
                moduleCacheConfig2.lastUsed = Game.getTimestampSeconds();
                moduleCacheConfig2.size = str.length();
                synchronized (this.l) {
                    this.l.put(musicB64hash, new SoftReference<>(moduleCacheConfig2));
                }
                b();
                return fromZipInputStream;
            } catch (Exception e2) {
                throw new IllegalStateException("failed to read module from base64", e2);
            }
        }
    }

    private void b() {
        IntArray intArray = null;
        m.clear();
        int timestampSeconds = Game.getTimestampSeconds();
        Iterator<IntMap.Entry<SoftReference<ModuleCacheConfig>>> it = this.l.iterator();
        while (it.hasNext()) {
            IntMap.Entry<SoftReference<ModuleCacheConfig>> next = it.next();
            ModuleCacheConfig moduleCacheConfig = next.value.get();
            if (moduleCacheConfig != null) {
                if (timestampSeconds - moduleCacheConfig.lastUsed <= 300) {
                    m.add(moduleCacheConfig);
                } else {
                    if (intArray == null) {
                        intArray = new IntArray();
                    }
                    intArray.add(next.key);
                }
            } else {
                if (intArray == null) {
                    intArray = new IntArray();
                }
                intArray.add(next.key);
            }
        }
        if (m.size > 5) {
            m.sort(n);
            for (int i = 5; i < m.size; i++) {
                if (intArray == null) {
                    intArray = new IntArray();
                }
                intArray.add(m.items[i].hash);
            }
        }
        if (intArray != null) {
            for (int i2 = 0; i2 < intArray.size; i2++) {
                this.l.remove(intArray.items[i2]);
            }
        }
        m.clear();
    }

    public void setVolumeToStartNewTrack() {
        setVolume(0.0f, 0.0f, false);
        setVolume(1.0f, 1.0f, false);
    }

    public void setVolume(float f2, float f3, boolean z) {
        if (Config.isHeadless()) {
            return;
        }
        this.i = f2;
        if (f3 > 0.0f) {
            this.j = f3;
            this.f2385a = z;
        } else {
            if (f2 <= 0.0f) {
                if (z) {
                    this.f2385a = false;
                    stopMusic();
                    return;
                } else {
                    this.h = 0.0f;
                    setBackendVolume(0.0f);
                    return;
                }
            }
            this.h = f2;
            setBackendVolume(getFactVolume(f2));
        }
    }

    public float getFactVolume(float f2) {
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        if (f2 > 1.0f) {
            f2 = 1.0f;
        }
        return f2 * getMainVolume() * this.f2386b;
    }

    public void simulateSpectrums() {
        for (int i = 0; i < this.d.size; i++) {
            this.d.get(i).a();
        }
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void preRender(float f2) {
        if (this.c) {
            simulateSpectrums();
            float f3 = this.h;
            if (this.j == 0.0f) {
                setBackendVolume(getFactVolume(this.i));
            } else if (f3 != this.i) {
                float f4 = f2 * this.j;
                float f5 = this.i - f3;
                float abs = f4 / StrictMath.abs(f5);
                if (abs >= 1.0f) {
                    this.h = this.i;
                } else {
                    this.h = f3 + (f5 * abs);
                }
                setBackendVolume(getFactVolume(this.h));
            } else {
                this.j = 0.0f;
                setBackendVolume(getFactVolume(this.h));
            }
            if (this.f2385a && f3 == 0.0f) {
                stopMusic();
                this.f2385a = false;
            }
            this.k += f2;
            if (this.k > 60.0f) {
                this.k = 0.0f;
                b();
            }
            StringBuilder registerValue = Game.i.debugManager.registerValue("Music");
            if (registerValue != null) {
                registerValue.append("v:").append((int) (this.h * 100.0f)).append("% ").append(getPlayingMusic() == null ? "NP" : "P");
            }
        }
    }

    public static MusicManager createSelfSetuppingDummy() {
        return new MusicManager() { // from class: com.prineside.tdi2.managers.MusicManager.2
            @Override // com.prineside.tdi2.managers.MusicManager, com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
            public void setup() {
                Game.i.musicManager = new LiveMusicManager();
                int indexOf = Game.i.managers.indexOf(this, true);
                Game.i.managers.removeIndex(indexOf);
                Game.i.managers.insert(indexOf, Game.i.musicManager);
                Game.i.musicManager.setup();
                MusicManager.e.i("music manager replaced", new Object[0]);
            }

            @Override // com.prineside.tdi2.managers.MusicManager
            public void stopMusic() {
                MusicManager.e.i("music manager is not set up yet", new Object[0]);
            }

            @Override // com.prineside.tdi2.managers.MusicManager
            public void setBackendVolume(float f2) {
                MusicManager.e.i("music manager is not set up yet", new Object[0]);
            }

            @Override // com.prineside.tdi2.managers.MusicManager
            public void playMusic(Module module) {
                MusicManager.e.i("music manager is not set up yet", new Object[0]);
            }

            @Override // com.prineside.tdi2.managers.MusicManager
            public Module getPlayingMusic() {
                MusicManager.e.i("music manager is not set up yet", new Object[0]);
                return null;
            }
        };
    }

    public void continuePlayingMenuMusicTrack() {
        if (!Game.i.settingsManager.isMusicEnabled()) {
            stopMusic();
            return;
        }
        Module module = getCurrentlyPlayingMenuThemeSource().getModule();
        Module module2 = module;
        if (module == null) {
            module2 = Game.i.assetManager.getMenuXmSoundTrack();
        }
        if (module2 == null) {
            stopMusic();
        } else if (getPlayingMusic() == null || !getPlayingMusic().songName.equals(module2.songName)) {
            setVolumeToStartNewTrack();
            playMusic(module2);
            e.i("started menu music", new Object[0]);
        }
    }

    public Notifications.Notification showSongNotification(Module module, float f2) {
        Notifications.i().hideNotification("MusicManager_CurrentlyPlaying");
        Table table = new Table();
        table.setTouchable(Touchable.childrenOnly);
        table.setWidth(340.0f);
        Label label = new Label(module.songName, Game.i.assetManager.getLabelStyle(24));
        label.setColor(g);
        table.add((Table) label).growX().row();
        Array.ArrayIterator<Module.TrackInfoEntry> it = module.getInfoFromInstrumentNames().iterator();
        while (it.hasNext()) {
            final Module.TrackInfoEntry next = it.next();
            final LimitedWidthLabel limitedWidthLabel = new LimitedWidthLabel(next.value, 24, 18, 340.0f);
            switch (next.type) {
                case AUTHOR:
                    Label label2 = new Label(Game.i.localeManager.i18n.get("music_track_author") + ":", Game.i.assetManager.getLabelStyle(18));
                    label2.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                    table.add((Table) label2).growX().padTop(8.0f).row();
                    break;
                case GROUP:
                    Label label3 = new Label(Game.i.localeManager.i18n.get("music_track_group") + ":", Game.i.assetManager.getLabelStyle(18));
                    label3.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                    table.add((Table) label3).growX().padTop(8.0f).row();
                    break;
                case LINK:
                    limitedWidthLabel.setTouchable(Touchable.enabled);
                    limitedWidthLabel.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.managers.MusicManager.3
                        @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                        public void clicked(InputEvent inputEvent, float f3, float f4) {
                            Gdx.f881net.openURI(next.getCompleteLink());
                        }
                    });
                    limitedWidthLabel.addListener(new InputListener(this) { // from class: com.prineside.tdi2.managers.MusicManager.4
                        @Override // com.prineside.tdi2.scene2d.InputListener
                        public void enter(InputEvent inputEvent, float f3, float f4, int i, @Null Actor actor) {
                            limitedWidthLabel.setColor(MaterialColor.LIGHT_BLUE.P200);
                        }

                        @Override // com.prineside.tdi2.scene2d.InputListener
                        public void exit(InputEvent inputEvent, float f3, float f4, int i, @Null Actor actor) {
                            limitedWidthLabel.setColor(MaterialColor.LIGHT_BLUE.P500);
                        }
                    });
                    limitedWidthLabel.setText(((Object) limitedWidthLabel.getText()) + Game.i.assetManager.replaceRegionAliasesWithChars("<@icon-link-out>").toString());
                    limitedWidthLabel.setColor(MaterialColor.LIGHT_BLUE.P500);
                    break;
            }
            limitedWidthLabel.setAlignment(8);
            table.add((Table) limitedWidthLabel).growX().row();
        }
        Notifications.Notification addWithContents = Notifications.i().addWithContents(table, Game.i.assetManager.getDrawable("icon-note"), f, null, f2);
        addWithContents.iconImage.setColor(g);
        addWithContents.setTouchable(Touchable.childrenOnly);
        final EqualizerTile equalizerTile = new EqualizerTile();
        equalizerTile.barsWidth = 1.0f;
        equalizerTile.barsHeight = 1.0f;
        equalizerTile.shiftX = 0.0f;
        equalizerTile.shiftY = 0.0f;
        equalizerTile.drawAlways = true;
        equalizerTile.colorHigh = new Color(g.cpy().mul(1.0f, 1.0f, 1.0f, 0.0f));
        equalizerTile.colorLow = g.cpy().mul(1.0f, 1.0f, 1.0f, 0.56f);
        equalizerTile.spectrumFrequencies = getSpectrumConfig(SpectrumConfig.DEFAULT).frequencies;
        Image image = new Image(this) { // from class: com.prineside.tdi2.managers.MusicManager.5
            @Override // com.prineside.tdi2.scene2d.ui.Image, com.prineside.tdi2.scene2d.ui.Widget, com.prineside.tdi2.scene2d.Actor
            public void draw(Batch batch, float f3) {
                validate();
                equalizerTile.drawFancy(batch, Gdx.graphics.getDeltaTime(), getX(), getY(), 128.0f);
            }
        };
        image.setTouchable(Touchable.disabled);
        image.setSize(340.0f, 80.0f);
        image.setPosition(-106.0f, -20.0f);
        table.addActor(image);
        addWithContents.id = "MusicManager_CurrentlyPlaying";
        return addWithContents;
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/MusicManager$SpectrumConfig.class */
    public static final class SpectrumConfig implements KryoSerializable {
        public static final SpectrumConfig DEFAULT = new SpectrumConfig(new Array(new FrequencyRange[]{new FrequencyRange(0.0f, 20.0f), new FrequencyRange(20.0f, 55.0f), new FrequencyRange(55.0f, 110.0f), new FrequencyRange(110.0f, 220.0f), new FrequencyRange(220.0f, 440.0f), new FrequencyRange(440.0f, 880.0f), new FrequencyRange(880.0f, 1760.0f), new FrequencyRange(1760.0f, 3520.0f), new FrequencyRange(3520.0f, 7040.0f), new FrequencyRange(7040.0f, 14080.0f), new FrequencyRange(14080.0f, 28160.0f)}));
        public Array<FrequencyRange> frequencies = new Array<>(true, 1, FrequencyRange.class);
        public float fixedMaxValue = 0.0f;
        public float maxValueEasing = 0.998f;

        @NAGS
        public final Object spectrumLock = new Object();

        @NAGS
        public float[] spectrumLeft;

        @NAGS
        public float[] spectrumRight;

        /* renamed from: a, reason: collision with root package name */
        @NAGS
        private float f2392a;

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public final void write(Kryo kryo, Output output) {
            kryo.writeObject(output, this.frequencies);
            output.writeFloat(this.maxValueEasing);
            output.writeFloat(this.fixedMaxValue);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public final void read(Kryo kryo, Input input) {
            this.frequencies = (Array) kryo.readObject(input, Array.class);
            this.maxValueEasing = input.readFloat();
            this.fixedMaxValue = input.readFloat();
        }

        public SpectrumConfig() {
        }

        public SpectrumConfig(Array<FrequencyRange> array) {
            this.frequencies.addAll(array);
            this.spectrumLeft = new float[getSpectrumSize()];
            this.spectrumRight = new float[getSpectrumSize()];
        }

        public final int getSpectrumSize() {
            return this.frequencies.size;
        }

        public final void copySpectrum(float[] fArr, boolean z) {
            synchronized (this.spectrumLock) {
                System.arraycopy(z ? this.spectrumLeft : this.spectrumRight, 0, fArr, 0, this.spectrumLeft.length);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a() {
            this.f2392a += Gdx.graphics.getDeltaTime();
            Game.i.musicManager.getSpectrumSim().generateSpectrum(this.f2392a, this.spectrumLeft, this.spectrumRight);
        }

        public final void zeroSpectrums() {
            Arrays.fill(this.spectrumLeft, 0.0f);
            Arrays.fill(this.spectrumRight, 0.0f);
        }

        public final boolean sameAs(SpectrumConfig spectrumConfig) {
            if (this.frequencies.size != spectrumConfig.frequencies.size) {
                return false;
            }
            for (int i = 0; i < this.frequencies.size; i++) {
                if (this.frequencies.get(i).min != spectrumConfig.frequencies.get(i).min || this.frequencies.get(i).max != spectrumConfig.frequencies.get(i).max) {
                    return false;
                }
            }
            return this.maxValueEasing == spectrumConfig.maxValueEasing && this.fixedMaxValue == spectrumConfig.fixedMaxValue;
        }

        public final String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(" { frequencies (").append(this.frequencies.size).append("): [");
            for (int i = 0; i < this.frequencies.size; i++) {
                if (i != 0) {
                    stringBuilder.append(",");
                }
                stringBuilder.append("[").append(this.frequencies.items[i].min).append(",").append(this.frequencies.items[i].max).append("]");
            }
            stringBuilder.append("], fixedMaxValue: ").append(this.fixedMaxValue);
            stringBuilder.append(", maxValueEasing: ").append(this.maxValueEasing);
            stringBuilder.append(" }");
            return super.toString() + ((Object) stringBuilder);
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/MusicManager$FrequencyRange.class */
    public static final class FrequencyRange implements KryoSerializable {
        public float min;
        public float max;
        public float multiplier = 1.0f;

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public final void write(Kryo kryo, Output output) {
            output.writeFloat(this.min);
            output.writeFloat(this.max);
            output.writeFloat(this.multiplier);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public final void read(Kryo kryo, Input input) {
            this.min = input.readFloat();
            this.max = input.readFloat();
            this.multiplier = input.readFloat();
        }

        public final boolean sameAs(FrequencyRange frequencyRange) {
            return frequencyRange.min == this.min && frequencyRange.max == this.max && frequencyRange.multiplier == this.multiplier;
        }

        public FrequencyRange() {
        }

        public FrequencyRange(float f, float f2) {
            Preconditions.checkArgument(f < f2, "Min must be smaller than max, %s and %s given", Float.valueOf(f), Float.valueOf(f2));
            this.min = f;
            this.max = f2;
        }

        public final FrequencyRange setMultiplier(float f) {
            this.multiplier = f;
            return this;
        }
    }
}
