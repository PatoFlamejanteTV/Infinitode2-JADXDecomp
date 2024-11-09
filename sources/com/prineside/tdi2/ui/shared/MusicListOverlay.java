package com.prineside.tdi2.ui.shared;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.net.HttpParametersUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.IntSet;
import com.badlogic.gdx.utils.JsonReader;
import com.prineside.tdi2.BasicLevel;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.UserMap;
import com.prineside.tdi2.ibxm.Module;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.MusicManager;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.managers.music.LiveMusicManager;
import com.prineside.tdi2.managers.preferences.categories.SettingsPrefs;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.ScrollPane;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.screens.GameScreen;
import com.prineside.tdi2.tiles.EqualizerTile;
import com.prineside.tdi2.ui.actors.ComplexButton;
import com.prineside.tdi2.ui.actors.HorizontalSlider;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.LabelToggleButton;
import com.prineside.tdi2.ui.actors.PaddedImageButton;
import com.prineside.tdi2.ui.actors.QuadActor;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.QuadDrawable;
import com.prineside.tdi2.utils.UiUtils;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.html.Attribute;
import java.util.HashMap;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/MusicListOverlay.class */
public final class MusicListOverlay extends UiManager.UiComponent.Adapter {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3714a = TLog.forClass(MusicListOverlay.class);
    public static final float LIST_ITEM_HEIGHT = 64.0f;
    private final Group c;
    private final ScrollPane d;
    private final Table e;
    private final Label f;
    private final PaddedImageButton g;
    private final Table h;
    private final Image i;
    private final HorizontalSlider j;
    private final LabelToggleButton k;
    private int l;
    private int m;
    private int n;
    private int o;
    private boolean u;

    /* renamed from: b, reason: collision with root package name */
    private final UiManager.UiLayer f3715b = Game.i.uiManager.addLayer(UiManager.MainUiLayer.OVERLAY, User32.VK_EREOF, "MusicListOverlay main");
    private float p = 0.0f;
    private int q = 0;
    private byte r = 0;
    private Array<MusicItem> s = new Array<>(MusicItem.class);
    private boolean t = false;
    private final IntArray v = new IntArray(true, 8);

    public static MusicListOverlay i() {
        return (MusicListOverlay) Game.i.uiManager.getComponent(MusicListOverlay.class);
    }

    public MusicListOverlay() {
        Group group = new Group();
        group.setTransform(false);
        group.setOrigin(320.0f, 477.0f);
        this.f3715b.getTable().add((Table) group).size(640.0f, 954.0f);
        this.c = new Group();
        this.c.setTransform(false);
        this.c.setOrigin(320.0f, 477.0f);
        this.c.setSize(640.0f, 954.0f);
        group.addActor(this.c);
        QuadActor quadActor = new QuadActor(new Color(724249599), new float[]{0.0f, 0.0f, 0.0f, 11.0f, 640.0f, 0.0f, 640.0f, 0.0f});
        quadActor.setPosition(0.0f, 943.0f);
        this.c.addActor(quadActor);
        Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
        image.setColor(new Color(724249599));
        image.setSize(640.0f, 932.0f);
        image.setPosition(0.0f, 11.0f);
        this.c.addActor(image);
        this.c.addActor(new QuadActor(new Color(724249599), new float[]{0.0f, 0.0f, 0.0f, 11.0f, 640.0f, 11.0f, 640.0f, 11.0f}));
        this.e = new Table();
        this.e.setTouchable(Touchable.childrenOnly);
        this.d = new ScrollPane(this.e);
        UiUtils.enableMouseMoveScrollFocus(this.d);
        this.d.setTransform(true);
        this.d.setSize(760.0f, 930.0f);
        this.d.setPosition(0.0f, 13.0f);
        this.d.setTouchable(Touchable.enabled);
        this.c.addActor(this.d);
        Image image2 = new Image(Game.i.assetManager.getDrawable("gradient-top"));
        image2.setColor(new Color(724249599));
        image2.setSize(640.0f, 16.0f);
        image2.setPosition(0.0f, 928.0f);
        image2.setTouchable(Touchable.disabled);
        this.c.addActor(image2);
        Image image3 = new Image(Game.i.assetManager.getDrawable("gradient-bottom"));
        image3.setColor(0.0f, 0.0f, 0.0f, 0.14f);
        image3.setSize(640.0f, 16.0f);
        image3.setPosition(0.0f, 183.0f);
        image3.setTouchable(Touchable.disabled);
        this.c.addActor(image3);
        QuadActor quadActor2 = new QuadActor(new Color(858993663), new float[]{0.0f, 0.0f, 0.0f, 183.0f, 640.0f, 183.0f, 640.0f, 11.0f});
        quadActor2.setTouchable(Touchable.enabled);
        this.c.addActor(quadActor2);
        this.f = new Label("", Game.i.assetManager.getLabelStyle(24));
        this.f.setPosition(40.0f, 131.0f);
        this.f.setColor(MaterialColor.LIGHT_GREEN.P500);
        this.f.setSize(100.0f, 20.0f);
        this.c.addActor(this.f);
        this.g = new PaddedImageButton(Game.i.assetManager.getDrawable("icon-thumbs-up"), () -> {
            MusicItem b2 = b();
            if (b2 != null) {
                boolean z = !Game.i.musicManager.isMusicThumbsUp(b2.hash);
                Game.i.musicManager.voteThumbsUp(b2.hash, z);
                h();
                b2.updateUi();
                try {
                    Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
                    httpRequest.setUrl(Config.VOTE_MUSIC_URL);
                    HashMap hashMap = new HashMap();
                    final long j = b2.hash & 4294967295L;
                    hashMap.put("hash", new StringBuilder().append(j).toString());
                    hashMap.put("vote", z ? "up" : "down");
                    hashMap.put("playerid", Game.i.authManager.getPlayerId());
                    if (Game.i.authManager.isSignedIn()) {
                        hashMap.put("sessionid", Game.i.authManager.getSessionId());
                    }
                    httpRequest.setContent(HttpParametersUtils.convertHttpParameters(hashMap));
                    final String base64 = b2.getBase64();
                    final String str = b2.name;
                    Gdx.f881net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() { // from class: com.prineside.tdi2.ui.shared.MusicListOverlay.1
                        @Override // com.badlogic.gdx.Net.HttpResponseListener
                        public void handleHttpResponse(Net.HttpResponse httpResponse) {
                            if (new JsonReader().parse(httpResponse.getResultAsString()).has("infoRequired")) {
                                MusicListOverlay.f3714a.i("server asks for music " + j, new Object[0]);
                                if (MusicListOverlay.this.t) {
                                    MusicListOverlay.f3714a.i("already sending music, abort", new Object[0]);
                                    return;
                                }
                                Threads i = Threads.i();
                                String str2 = base64;
                                long j2 = j;
                                String str3 = str;
                                i.runOnMainThread(() -> {
                                    if (Game.i.authManager.isSignedIn() && str2 != null) {
                                        MusicListOverlay.f3714a.i("sending music to server", new Object[0]);
                                        MusicListOverlay.this.t = true;
                                        Net.HttpRequest httpRequest2 = new Net.HttpRequest(Net.HttpMethods.POST);
                                        httpRequest2.setUrl(Config.SUBMIT_MUSIC_URL);
                                        HashMap hashMap2 = new HashMap();
                                        hashMap2.put("hash", new StringBuilder().append(j2).toString());
                                        hashMap2.put("sessionid", Game.i.authManager.getSessionId());
                                        hashMap2.put("file", str2);
                                        hashMap2.put(Attribute.TITLE_ATTR, str3);
                                        httpRequest2.setContent(HttpParametersUtils.convertHttpParameters(hashMap2));
                                        Gdx.f881net.sendHttpRequest(httpRequest2, new Net.HttpResponseListener() { // from class: com.prineside.tdi2.ui.shared.MusicListOverlay.1.1
                                            @Override // com.badlogic.gdx.Net.HttpResponseListener
                                            public void handleHttpResponse(Net.HttpResponse httpResponse2) {
                                                MusicListOverlay.this.t = false;
                                            }

                                            @Override // com.badlogic.gdx.Net.HttpResponseListener
                                            public void failed(Throwable th) {
                                                MusicListOverlay.this.t = false;
                                            }

                                            @Override // com.badlogic.gdx.Net.HttpResponseListener
                                            public void cancelled() {
                                                MusicListOverlay.this.t = false;
                                            }
                                        });
                                    }
                                });
                            }
                        }

                        @Override // com.badlogic.gdx.Net.HttpResponseListener
                        public void failed(Throwable th) {
                            MusicListOverlay.f3714a.e("Failed", th);
                        }

                        @Override // com.badlogic.gdx.Net.HttpResponseListener
                        public void cancelled() {
                        }
                    });
                } catch (Exception unused) {
                }
            }
        }, MaterialColor.LIGHT_GREEN.P500, MaterialColor.LIGHT_GREEN.P400, MaterialColor.LIGHT_GREEN.P600);
        this.g.setSize(96.0f, 96.0f);
        this.g.setIconSize(64.0f, 64.0f);
        this.g.setPosition(520.0f, 74.0f);
        this.g.setIconPosition(16.0f, 16.0f);
        this.c.addActor(this.g);
        this.h = new Table();
        ScrollPane scrollPane = new ScrollPane(this.h, Game.i.assetManager.getScrollPaneStyle(4.0f));
        UiUtils.enableMouseMoveScrollFocus(scrollPane);
        scrollPane.setScrollingDisabled(false, true);
        scrollPane.setSize(500.0f, 52.0f);
        scrollPane.setPosition(20.0f, 78.0f);
        this.c.addActor(scrollPane);
        Image image4 = new Image(Game.i.assetManager.getDrawable("gradient-left"));
        image4.setColor(new Color(858993663));
        image4.setSize(20.0f, 52.0f);
        image4.setPosition(19.0f, 78.0f);
        image4.setTouchable(Touchable.disabled);
        this.c.addActor(image4);
        Image image5 = new Image(Game.i.assetManager.getDrawable("gradient-right"));
        image5.setColor(new Color(858993663));
        image5.setSize(20.0f, 52.0f);
        image5.setPosition(501.0f, 78.0f);
        image5.setTouchable(Touchable.disabled);
        this.c.addActor(image5);
        Table table = new Table();
        table.setSize(640.0f, 32.0f);
        table.setPosition(0.0f, 32.0f);
        this.c.addActor(table);
        this.i = new Image(Game.i.assetManager.getDrawable("icon-speaker"));
        table.add((Table) this.i).size(32.0f).padRight(8.0f).padLeft(40.0f);
        this.j = new HorizontalSlider(150.0f, Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.MUSIC_VOLUME), 0.0d, 1.0d, d -> {
            double customValue = Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.MUSIC_VOLUME);
            Game.i.settingsManager.setMusicVolume(d.doubleValue());
            if (customValue == 0.0d && d.doubleValue() > 0.0d) {
                MusicItem b2 = b();
                if (b2 != null) {
                    b2.play();
                    this.o = b2.hash;
                }
            } else if (customValue > 0.0d && d.doubleValue() == 0.0d) {
                Game.i.musicManager.stopMusic();
            }
            e();
            h();
            f();
        });
        this.j.setNotifyOnDrag(true);
        table.add((Table) this.j).size(150.0f, 48.0f).padRight(16.0f);
        table.add().expand().fill();
        this.k = new LabelToggleButton("", Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.IGNORE_MAP_MUSIC) != 0.0d, 24, 32.0f, true, bool -> {
            Game.i.settingsManager.setCustomValue(SettingsManager.CustomValueType.IGNORE_MAP_MUSIC, bool.booleanValue() ? 1.0d : 0.0d);
        });
        table.add(this.k).padRight(40.0f);
        final EqualizerTile equalizerTile = (EqualizerTile) Game.i.tileManager.createTileFromJsonString("{\"type\":\"EQUALIZER\",\"y\":5,\"d\":{\"bi\":0,\"sd\":0.08,\"c\":0,\"co\":0,\"d\":2,\"cl\":\"ad1457FF\",\"ch\":\"6a1b9aFF\",\"bw\":7.45,\"bh\":2,\"rb\":false,\"pe\":true,\"f\":[[10.8,34.65],[32.7,55],[55,77.78],[77.78,98],[98,123.47],[123.47,146.83],[146.83,164.81],[164.81,185],[185,207.65],[207.65,233.08],[233.08,261.63],[261.63,277.18],[277.18,293.66],[293.66,329.63],[329.63,349.23],[349.23,369.99],[369.99,392],[392,415.3],[415.3,440],[440,466.16],[466.16,493.88],[493.88,523.25],[523.25,554.37],[554.37,587.33],[587.33,622.25],[622.25,659.26],[659.26,698.46],[698.46,739.99],[739.99,783.99],[783.99,830.61],[830.61,880],[880,932.33],[932.33,987.77],[987.77,1046.5],[1046.5,1108.73],[1108.73,1174.66],[1174.66,1244.51],[1244.51,1318.51],[1318.51,1396.91],[1396.91,1479.98],[1479.98,1567.98],[1567.98,1661.22],[1661.22,1760],[1760,1864.66],[1864.66,1975.53],[1975.53,2093],[2093,2217.46],[2217.46,2349.32],[2349.32,2489.02],[2489.02,2637.02],[2637.02,2793.83],[2793.83,2959.96],[2959.96,3135.96],[3135.96,3322.44],[3322.44,3520],[3520,3729.31],[3729.31,3951.07],[3951.07,4186.01],[4186.01,4434.92],[4434.92,4698.64],[4698.64,4978.03],[4978.03,5274.04],[5274.04,5587.65],[5587.65,5919.91],[5919.91,6271.93],[6271.93,6644.88],[6644.88,7040],[7040,7458.62],[7458.62,7902.13],[7902.13,8372.02],[8372.02,8869.84],[8869.84,9397.27],[9397.27,9956.06],[9956.06,10548],[10548,11175],[11175,11839],[11839,12543],[12543,13289],[13289,14080],[14080,14917],[14917,15804],[15804,16744],[16744,17739],[17739,18794],[18794,19912]],\"mve\":0.99,\"fmv\":0,\"bs\":0.2,\"sx\":0,\"sy\":0}}");
        Image image6 = new Image(this) { // from class: com.prineside.tdi2.ui.shared.MusicListOverlay.2
            @Override // com.prineside.tdi2.scene2d.ui.Image, com.prineside.tdi2.scene2d.ui.Widget, com.prineside.tdi2.scene2d.Actor
            public void draw(Batch batch, float f) {
                validate();
                if (Game.i.musicManager.getMainVolume() != 0.0f) {
                    equalizerTile.drawFancy(batch, Gdx.graphics.getDeltaTime(), getX(), getY(), 128.0f);
                }
            }
        };
        image6.setPosition(-128.0f, 0.0f);
        this.c.addActor(image6);
        Image image7 = new Image(Game.i.assetManager.getDrawable("gradient-right"));
        image7.setColor(0.0f, 0.0f, 0.0f, 0.28f);
        image7.setSize(32.0f, 954.0f);
        image7.setPosition(-32.0f, 0.0f);
        this.c.addActor(image7);
        Image image8 = new Image(Game.i.assetManager.getDrawable("gradient-right"));
        image8.setColor(0.0f, 0.0f, 0.0f, 0.28f);
        image8.setSize(16.0f, 954.0f);
        image8.setPosition(-16.0f, 0.0f);
        this.c.addActor(image8);
        final EqualizerTile equalizerTile2 = (EqualizerTile) Game.i.tileManager.createTileFromJsonString("{\"type\":\"EQUALIZER\",\"y\":5,\"d\":{\"bi\":0,\"sd\":0.08,\"c\":0,\"co\":0,\"d\":2,\"cl\":\"ad1457FF\",\"ch\":\"6a1b9aFF\",\"bw\":7.29,\"bh\":2,\"rb\":false,\"pe\":true,\"f\":[[10.8,34.65],[32.7,55],[55,77.78],[77.78,98],[98,123.47],[123.47,146.83],[146.83,164.81],[164.81,185],[185,207.65],[207.65,233.08],[233.08,261.63],[261.63,277.18],[277.18,293.66],[293.66,329.63],[329.63,349.23],[349.23,369.99],[369.99,392],[392,415.3],[415.3,440],[440,466.16],[466.16,493.88],[493.88,523.25],[523.25,554.37],[554.37,587.33],[587.33,622.25],[622.25,659.26],[659.26,698.46],[698.46,739.99],[739.99,783.99],[783.99,830.61],[830.61,880],[880,932.33],[932.33,987.77],[987.77,1046.5],[1046.5,1108.73],[1108.73,1174.66],[1174.66,1244.51],[1244.51,1318.51],[1318.51,1396.91],[1396.91,1479.98],[1479.98,1567.98],[1567.98,1661.22],[1661.22,1760],[1760,1864.66],[1864.66,1975.53],[1975.53,2093],[2093,2217.46],[2217.46,2349.32],[2349.32,2489.02],[2489.02,2637.02],[2637.02,2793.83],[2793.83,2959.96],[2959.96,3135.96],[3135.96,3322.44],[3322.44,3520],[3520,3729.31],[3729.31,3951.07],[3951.07,4186.01],[4186.01,4434.92],[4434.92,4698.64],[4698.64,4978.03],[4978.03,5274.04],[5274.04,5587.65],[5587.65,5919.91],[5919.91,6271.93],[6271.93,6644.88],[6644.88,7040],[7040,7458.62],[7458.62,7902.13],[7902.13,8372.02],[8372.02,8869.84],[8869.84,9397.27],[9397.27,9956.06],[9956.06,10548],[10548,11175],[11175,11839],[11839,12543],[12543,13289],[13289,14080],[14080,14917],[14917,15804],[15804,16744],[16744,17739],[17739,18794],[18794,19912]],\"mve\":0.99,\"fmv\":0,\"bs\":0.2,\"sx\":0,\"sy\":0}}");
        Image image9 = new Image(this) { // from class: com.prineside.tdi2.ui.shared.MusicListOverlay.3
            @Override // com.prineside.tdi2.scene2d.ui.Image, com.prineside.tdi2.scene2d.ui.Widget, com.prineside.tdi2.scene2d.Actor
            public void draw(Batch batch, float f) {
                validate();
                if (Game.i.musicManager.getMainVolume() != 0.0f) {
                    equalizerTile2.drawFancy(batch, Gdx.graphics.getDeltaTime(), getX(), getY(), 128.0f);
                }
            }
        };
        equalizerTile2.channel = 1;
        equalizerTile2.direction = 3;
        image9.setPosition(640.0f, 11.0f);
        this.c.addActor(image9);
        Image image10 = new Image(Game.i.assetManager.getDrawable("gradient-left"));
        image10.setColor(0.0f, 0.0f, 0.0f, 0.28f);
        image10.setSize(32.0f, 932.0f);
        image10.setPosition(640.0f, 11.0f);
        this.c.addActor(image10);
        Image image11 = new Image(Game.i.assetManager.getDrawable("gradient-left"));
        image11.setColor(0.0f, 0.0f, 0.0f, 0.28f);
        image11.setSize(16.0f, 932.0f);
        image11.setPosition(640.0f, 11.0f);
        this.c.addActor(image11);
        PaddedImageButton paddedImageButton = new PaddedImageButton(Game.i.assetManager.getDrawable("icon-times"), () -> {
            hide();
        }, MaterialColor.ORANGE.P500, MaterialColor.ORANGE.P400, MaterialColor.ORANGE.P600);
        paddedImageButton.setIconSize(48.0f, 48.0f);
        paddedImageButton.setIconPosition(16.0f, 16.0f);
        paddedImageButton.setSize(64.0f, 64.0f);
        paddedImageButton.setIconPosition(16.0f, 16.0f);
        paddedImageButton.setPosition(592.0f, 892.0f);
        paddedImageButton.setName("music_list_overlay_close_button");
        this.c.addActor(paddedImageButton);
        this.f3715b.getTable().setVisible(false);
    }

    private MusicItem b() {
        for (int i = 0; i < this.s.size; i++) {
            if (this.s.items[i].hash == this.o) {
                return this.s.items[i];
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int c() {
        if (Game.i.musicManager.getPlayingMusic() == null) {
            return -1;
        }
        return this.o;
    }

    private void d() {
        new Thread(() -> {
            if (this.r == 1) {
                f3714a.e("another thread in progress", new Object[0]);
                return;
            }
            this.r = (byte) 1;
            try {
                int i = 7;
                Array<UserMap> userMaps = Game.i.userMapManager.getUserMaps();
                for (int i2 = 0; i2 < userMaps.size; i2++) {
                    UserMap userMap = userMaps.items[i2];
                    if (userMap.map.getMusicTile() != null && userMap.map.getMusicTile().getModule() != null) {
                        i = (((i * 31) + userMap.id.hashCode()) * 31) + Game.i.musicManager.getMusicB64hash(userMap.map.getMusicTile().getTrackBase64());
                    }
                }
                if (i != this.q) {
                    this.q = i;
                    this.s.clear();
                    Module menuXmSoundTrack = Game.i.assetManager.getMenuXmSoundTrack();
                    if (menuXmSoundTrack != null) {
                        MusicItem musicItem = new MusicItem(this, (byte) 0);
                        musicItem.source = MusicManager.MusicSource.DEFAULT;
                        musicItem.name = menuXmSoundTrack.songName;
                        this.s.add(musicItem);
                    }
                    IntSet intSet = new IntSet();
                    for (int i3 = 0; i3 < Game.i.basicLevelManager.levelsOrdered.size; i3++) {
                        BasicLevel basicLevel = Game.i.basicLevelManager.levelsOrdered.items[i3];
                        if (basicLevel.getMap().getMusicTile() != null && basicLevel.getMap().getMusicTile().getModule() != null) {
                            int musicB64hash = Game.i.musicManager.getMusicB64hash(basicLevel.getMap().getMusicTile().getTrackBase64());
                            if (!intSet.contains(musicB64hash)) {
                                intSet.add(musicB64hash);
                                MusicItem musicItem2 = new MusicItem(this, (byte) 0);
                                musicItem2.source = new MusicManager.MusicSource(MusicManager.MusicSourceType.BASIC_LEVEL, basicLevel.name);
                                musicItem2.hash = musicB64hash;
                                musicItem2.name = basicLevel.getMap().getMusicTile().getModule().songName;
                                this.s.add(musicItem2);
                            }
                        }
                    }
                    for (int i4 = 0; i4 < userMaps.size; i4++) {
                        UserMap userMap2 = userMaps.items[i4];
                        if (userMap2.map.getMusicTile() != null && userMap2.map.getMusicTile().getModule() != null) {
                            int musicB64hash2 = Game.i.musicManager.getMusicB64hash(userMap2.map.getMusicTile().getTrackBase64());
                            if (!intSet.contains(musicB64hash2)) {
                                intSet.add(musicB64hash2);
                                MusicItem musicItem3 = new MusicItem(this, (byte) 0);
                                musicItem3.source = new MusicManager.MusicSource(MusicManager.MusicSourceType.USER_MAP, userMap2.id);
                                musicItem3.hash = musicB64hash2;
                                musicItem3.name = userMap2.map.getMusicTile().getModule().songName;
                                this.s.add(musicItem3);
                            }
                        }
                    }
                }
                this.r = (byte) 2;
                Threads.i().postRunnable(this::j);
            } catch (Exception e) {
                f3714a.e("failed to update music list", e);
                this.r = (byte) 3;
                Threads.i().postRunnable(this::j);
            }
        }, "MusicListOverlay update").start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.r == 2) {
            for (int i = 0; i < this.s.size; i++) {
                this.s.items[i].updateUi();
            }
            return;
        }
        f3714a.e("music list is not prepared yet", new Object[0]);
    }

    private void f() {
        if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.MUSIC_VOLUME) == 0.0d) {
            this.i.setDrawable(Game.i.assetManager.getDrawable("icon-speaker-crossed"));
        } else {
            this.i.setDrawable(Game.i.assetManager.getDrawable("icon-speaker"));
        }
        this.j.setValue(Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.MUSIC_VOLUME), false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        while (this.v.size > 3) {
            this.v.removeIndex(0);
        }
        if (this.v.size == 3 && (this.v.get(0) * 7151) + (this.v.get(1) * 5717) + this.v.get(2) == 217451) {
            Game.i.screenManager.startNewBasicLevel(Game.i.basicLevelManager.getLevel("music_spectrum"), null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        Module playingMusic = Game.i.musicManager.getPlayingMusic();
        if (playingMusic != null) {
            this.f.setText(playingMusic.songName);
        } else {
            this.f.setText("");
        }
        if ((Game.i.musicManager instanceof LiveMusicManager) && playingMusic != null) {
            final LiveMusicManager liveMusicManager = (LiveMusicManager) Game.i.musicManager;
            if (liveMusicManager.ibxm != null) {
                int sequencePos = liveMusicManager.ibxm.getSequencePos();
                int i = playingMusic.sequenceLength;
                int i2 = playingMusic.restartPos;
                if (this.m != sequencePos || this.l != i || this.n != i2) {
                    this.m = sequencePos;
                    this.l = i;
                    this.n = i2;
                    this.h.clear();
                    this.h.add().height(1.0f).width(20.0f);
                    for (int i3 = 0; i3 < i; i3++) {
                        Group group = new Group();
                        group.setTransform(false);
                        group.setTouchable(Touchable.enabled);
                        final int i4 = i3;
                        group.addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.shared.MusicListOverlay.4
                            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                            public void clicked(InputEvent inputEvent, float f, float f2) {
                                try {
                                    liveMusicManager.ibxm.lastSeqPos = 0;
                                    liveMusicManager.ibxm.setSequencePosApplyEffects(i4);
                                    MusicListOverlay.this.v.add(i4);
                                    MusicListOverlay.this.g();
                                    MusicListOverlay.this.h();
                                } catch (Exception unused) {
                                }
                            }
                        });
                        this.h.add((Table) group).size(32.0f, 32.0f).padRight(4.0f);
                        Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                        if (i3 == sequencePos) {
                            image.setSize(32.0f, 32.0f);
                            image.setColor(MaterialColor.LIGHT_GREEN.P500);
                        } else {
                            image.setSize(32.0f, 20.0f);
                            image.setPosition(0.0f, 6.0f);
                            image.setColor(MaterialColor.LIGHT_GREEN.P800);
                        }
                        group.addActor(image);
                        if (i2 == i3) {
                            Image image2 = new Image(Game.i.assetManager.getDrawable("icon-triangle-right-hollow"));
                            image2.setSize(16.0f, 16.0f);
                            image2.setColor(0.0f, 0.0f, 0.0f, 0.56f);
                            image2.setPosition(8.0f, 8.0f);
                            group.addActor(image2);
                        } else {
                            Label label = new Label(String.valueOf(i3), Game.i.assetManager.getLabelStyle(21));
                            label.setColor(0.0f, 0.0f, 0.0f, 0.56f);
                            label.setSize(32.0f, 20.0f);
                            label.setPosition(0.0f, 6.0f);
                            label.setAlignment(1);
                            group.addActor(label);
                        }
                    }
                    this.h.add().height(1.0f).width(20.0f);
                    this.h.add().height(1.0f).expandX().fillX();
                }
            }
        }
        MusicItem b2 = b();
        if (b2 != null && playingMusic != null) {
            this.g.setVisible(true);
            if (Game.i.musicManager.isMusicThumbsUp(b2.hash)) {
                this.g.setColors(MaterialColor.LIGHT_GREEN.P500, MaterialColor.LIGHT_GREEN.P400, MaterialColor.LIGHT_GREEN.P600);
                return;
            } else {
                this.g.setColors(MaterialColor.GREY.P700, MaterialColor.GREY.P500, MaterialColor.GREY.P800);
                return;
            }
        }
        this.g.setVisible(false);
    }

    public final void show() {
        long realTickCount = Game.getRealTickCount();
        this.j.setVisible(false);
        this.k.setText(Game.i.localeManager.i18n.get("settings_ignore_map_music"));
        this.e.clear();
        if (this.r == 2) {
            Threads.i().postRunnable(this::j);
        } else if (this.r != 1) {
            d();
        }
        if (Game.i.musicManager.getCurrentlyPlayingMenuThemeSource().type != MusicManager.MusicSourceType.DEFAULT) {
            for (int i = 0; i < this.s.size; i++) {
                if (this.s.items[i].source.equals(Game.i.musicManager.getCurrentlyPlayingMenuThemeSource())) {
                    this.o = this.s.items[i].hash;
                }
            }
        } else {
            this.o = 0;
        }
        Image image = new Image(Game.i.assetManager.getDrawable("loading-icon"));
        image.setOrigin(32.0f, 32.0f);
        image.addAction(Actions.forever(Actions.rotateBy(90.0f, 1.0f)));
        this.e.add((Table) image).size(64.0f).padRight(120.0f).row();
        a(true);
        Game.i.uiManager.stage.setScrollFocus(this.d);
        if (Game.i.debugManager != null) {
            Game.i.debugManager.registerFrameJob("MusicListOverlay-show", Game.getRealTickCount() - realTickCount);
        }
    }

    private void j() {
        if (this.u) {
            long realTickCount = Game.getRealTickCount();
            this.e.clear();
            this.e.add().width(1.0f).height(32.0f).row();
            Table table = new Table();
            this.e.add(table).width(760.0f).row();
            Group group = new Group();
            group.setTransform(false);
            table.add((Table) group).size(760.0f, 46.0f).row();
            Label label = new Label(Game.i.localeManager.i18n.get(Attribute.TITLE_ATTR), Game.i.assetManager.getLabelStyle(21));
            label.setColor(1.0f, 1.0f, 1.0f, 0.28f);
            label.setSize(100.0f, 46.0f);
            label.setPosition(40.0f, 0.0f);
            group.addActor(label);
            Label label2 = new Label(Game.i.localeManager.i18n.get("level"), Game.i.assetManager.getLabelStyle(21));
            label2.setColor(1.0f, 1.0f, 1.0f, 0.28f);
            label2.setAlignment(16);
            label2.setSize(100.0f, 46.0f);
            label2.setPosition(320.0f, 0.0f);
            group.addActor(label2);
            Label label3 = new Label(Game.i.localeManager.i18n.get("music_list_header_menu_theme"), Game.i.assetManager.getLabelStyle(21));
            label3.setColor(1.0f, 1.0f, 1.0f, 0.28f);
            label3.setSize(100.0f, 46.0f);
            label3.setPosition(460.0f, 0.0f);
            group.addActor(label3);
            for (int i = 0; i < this.s.size; i++) {
                MusicItem musicItem = this.s.items[i];
                if (musicItem.container == null) {
                    Group group2 = new Group();
                    group2.setTransform(false);
                    table.add((Table) group2).size(760.0f, 64.0f).padBottom(4.0f).row();
                    Image image = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
                    image.setColor(new Color(623191551));
                    image.setSize(640.0f, 64.0f);
                    group2.addActor(image);
                    musicItem.container = group2;
                } else {
                    table.add((Table) musicItem.container).size(760.0f, 64.0f).padBottom(4.0f).row();
                }
            }
            this.e.add().width(1.0f).height(185.0f).row();
            this.j.setVisible(true);
            h();
            e();
            f();
            if (Game.i.debugManager != null) {
                Game.i.debugManager.registerFrameJob("MusicListOverlay-rebuildMusicListUI", Game.getRealTickCount() - realTickCount);
            }
        }
    }

    public final boolean isVisible() {
        return this.u;
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public final void hide() {
        if (this.u) {
            a(false);
            if (Game.i.screenManager.getCurrentScreen() instanceof GameScreen) {
                try {
                    ((GameScreen) Game.i.screenManager.getCurrentScreen()).S._sound.updateMusicPlayback();
                    return;
                } catch (Exception unused) {
                }
            }
            Game.i.musicManager.continuePlayingMenuMusicTrack();
        }
    }

    private void a(boolean z) {
        if (z) {
            DarkOverlay.i().addCallerOverlayLayer("MusicListOverlay", this.f3715b.zIndex - 1, () -> {
                hide();
                return true;
            });
            UiUtils.bouncyShowOverlay(null, this.f3715b.getTable(), this.c);
        } else {
            DarkOverlay.i().removeCaller("MusicListOverlay");
            UiUtils.bouncyHideOverlay(null, this.f3715b.getTable(), this.c);
        }
        this.u = z;
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent.Adapter, com.prineside.tdi2.managers.UiManager.UiComponent
    public final void postRender(float f) {
        if (this.u) {
            this.p += f;
            if (this.p > 0.33f) {
                this.p = 0.0f;
                h();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/MusicListOverlay$MusicItem.class */
    public class MusicItem {
        public static final int AMPLITUDES_PREVIEW_WIDTH = 460;
        public int hash;
        public MusicManager.MusicSource source;
        public String name;
        public Group container;
        public Label nameLabel;
        public Label authorLabel;
        public ComplexButton playButton;
        public PaddedImageButton menuThemeButton;
        public Image thumbsUpIcon;
        public Group repeatsGroupScrollDependable;
        public Label repeatsLabel;
        public PaddedImageButton lessRepeatsButton;
        public PaddedImageButton moreRepeatsButton;

        private MusicItem() {
        }

        /* synthetic */ MusicItem(MusicListOverlay musicListOverlay, byte b2) {
            this();
        }

        public String getLevelName() {
            return this.source.type == MusicManager.MusicSourceType.BASIC_LEVEL ? this.source.id : this.source.type == MusicManager.MusicSourceType.USER_MAP ? Game.i.userMapManager.getUserMap(this.source.id).name : "";
        }

        public void play() {
            try {
                Game.i.musicManager.playMusic(getModule());
                Game.i.musicManager.setVolumeToStartNewTrack();
            } catch (Exception e) {
                MusicListOverlay.f3714a.e("failed to play " + this.source, e);
            }
        }

        public String getBase64() {
            return this.source.getBase64();
        }

        public Module getModule() {
            Module module = this.source.getModule();
            Module module2 = module;
            if (module == null) {
                module2 = Game.i.assetManager.getMenuXmSoundTrack();
            }
            return module2;
        }

        public boolean isAvailable() {
            if (this.source.type == MusicManager.MusicSourceType.BASIC_LEVEL) {
                return Game.i.basicLevelManager.isOpened(Game.i.basicLevelManager.getLevel(this.source.id));
            }
            return true;
        }

        public void toggleAsMenuTheme() {
            if (Game.i.musicManager.isMenuMusicSourceEnabled(this.source)) {
                Game.i.musicManager.removeMenuMusicSource(this.source);
            } else {
                Game.i.musicManager.addMenuMusicSource(this.source);
            }
        }

        public void updateUi() {
            boolean isAvailable = isAvailable();
            if (this.thumbsUpIcon == null) {
                Module module = this.source.getModule();
                this.thumbsUpIcon = new Image(Game.i.assetManager.getDrawable("icon-thumbs-up"));
                this.thumbsUpIcon.setColor(MaterialColor.LIGHT_GREEN.P500);
                this.thumbsUpIcon.setSize(24.0f, 24.0f);
                this.thumbsUpIcon.setPosition(6.0f, 20.0f);
                this.container.addActor(this.thumbsUpIcon);
                Label label = new Label(getLevelName(), Game.i.assetManager.getLabelStyle(21));
                label.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                label.setSize(100.0f, 64.0f);
                label.setAlignment(16);
                label.setPosition(320.0f, 0.0f);
                this.container.addActor(label);
                this.nameLabel = new Label(this.name, Game.i.assetManager.getLabelStyle(24));
                this.nameLabel.setSize(100.0f, 48.0f);
                this.nameLabel.setPosition(40.0f, 16.0f);
                this.container.addActor(this.nameLabel);
                Array<Module.TrackInfoEntry> infoFromInstrumentNames = module.getInfoFromInstrumentNames();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < infoFromInstrumentNames.size; i++) {
                    Module.TrackInfoEntry trackInfoEntry = infoFromInstrumentNames.get(i);
                    if (trackInfoEntry.type == Module.TrackInfoEntry.EntryType.AUTHOR) {
                        if (sb.length() != 0) {
                            sb.append(", ");
                        }
                        sb.append(trackInfoEntry.value);
                    }
                }
                this.authorLabel = new Label(sb.toString(), Game.i.assetManager.getLabelStyle(18));
                this.authorLabel.setSize(100.0f, 14.0f);
                this.authorLabel.setPosition(40.0f, 10.0f);
                this.authorLabel.setColor(1.0f, 1.0f, 1.0f, 0.4f);
                this.container.addActor(this.authorLabel);
                TextureRegionDrawable drawable = Game.i.assetManager.getDrawable("settings-toggle-on");
                Runnable runnable = () -> {
                    toggleAsMenuTheme();
                    MusicListOverlay.this.e();
                };
                Color color = Color.WHITE;
                this.menuThemeButton = new PaddedImageButton(drawable, runnable, color, color, Color.WHITE);
                this.menuThemeButton.setSize(64.0f, 64.0f);
                this.menuThemeButton.setPosition(460.0f, 0.0f);
                this.menuThemeButton.setIconSize(64.0f, 32.0f);
                this.menuThemeButton.setIconPosition(0.0f, 14.0f);
                this.container.addActor(this.menuThemeButton);
                this.playButton = new ComplexButton("", Game.i.assetManager.getLabelStyle(21), () -> {
                    play();
                    MusicListOverlay.this.o = this.hash;
                    MusicListOverlay.this.e();
                    MusicListOverlay.this.h();
                });
                this.playButton.setBackground(new QuadDrawable(new QuadActor(Color.WHITE, new float[]{0.0f, 0.0f, 8.0f, 64.0f, 100.0f, 64.0f, 100.0f, 0.0f})), 0.0f, 0.0f, 100.0f, 64.0f);
                this.playButton.setIconPositioned(Game.i.assetManager.getDrawable("icon-triangle-right"), 36.0f, 16.0f, 32.0f, 32.0f);
                this.playButton.setPosition(540.0f, 0.0f);
                this.container.addActor(this.playButton);
                this.repeatsGroupScrollDependable = new Group();
                this.repeatsGroupScrollDependable.setTransform(false);
                this.repeatsGroupScrollDependable.setSize(120.0f, 52.0f);
                this.repeatsGroupScrollDependable.setPosition(640.0f, 0.0f);
                this.container.addActor(this.repeatsGroupScrollDependable);
                this.repeatsLabel = new Label("x1", Game.i.assetManager.getLabelStyle(24));
                this.repeatsLabel.setSize(120.0f, 52.0f);
                this.repeatsLabel.setAlignment(1);
                this.repeatsLabel.setPosition(0.0f, 0.0f);
                this.repeatsGroupScrollDependable.addActor(this.repeatsLabel);
                this.repeatsGroupScrollDependable.setVisible(false);
                this.lessRepeatsButton = new PaddedImageButton(Game.i.assetManager.getDrawable("icon-triangle-bottom"), () -> {
                    Array<MusicManager.MusicSource> menuThemeSources = Game.i.musicManager.getMenuThemeSources();
                    for (int i2 = 0; i2 < menuThemeSources.size; i2++) {
                        if (menuThemeSources.items[i2].sameAs(this.source)) {
                            if (menuThemeSources.items[i2].repeats > 1) {
                                if (menuThemeSources.items[i2].repeats > 4) {
                                    menuThemeSources.items[i2].repeats = 4;
                                } else if (menuThemeSources.items[i2].repeats > 2) {
                                    menuThemeSources.items[i2].repeats = 2;
                                } else {
                                    menuThemeSources.items[i2].repeats = 1;
                                }
                                SettingsPrefs.i().requireSave();
                                updateUi();
                                return;
                            }
                            return;
                        }
                    }
                }, MaterialColor.LIGHT_BLUE.P300, MaterialColor.LIGHT_BLUE.P200, MaterialColor.LIGHT_BLUE.P500);
                this.lessRepeatsButton.setPosition(0.0f, 0.0f);
                this.lessRepeatsButton.setSize(60.0f, 52.0f);
                this.lessRepeatsButton.setIconPosition(18.0f, 14.0f);
                this.lessRepeatsButton.setIconSize(24.0f, 24.0f);
                this.repeatsGroupScrollDependable.addActor(this.lessRepeatsButton);
                this.moreRepeatsButton = new PaddedImageButton(Game.i.assetManager.getDrawable("icon-triangle-top"), () -> {
                    Array<MusicManager.MusicSource> menuThemeSources = Game.i.musicManager.getMenuThemeSources();
                    for (int i2 = 0; i2 < menuThemeSources.size; i2++) {
                        if (menuThemeSources.items[i2].sameAs(this.source)) {
                            if (menuThemeSources.items[i2].repeats < 8) {
                                if (menuThemeSources.items[i2].repeats < 2) {
                                    menuThemeSources.items[i2].repeats = 2;
                                } else if (menuThemeSources.items[i2].repeats < 4) {
                                    menuThemeSources.items[i2].repeats = 4;
                                } else {
                                    menuThemeSources.items[i2].repeats = 8;
                                }
                                SettingsPrefs.i().requireSave();
                                updateUi();
                                return;
                            }
                            return;
                        }
                    }
                }, MaterialColor.LIGHT_BLUE.P300, MaterialColor.LIGHT_BLUE.P200, MaterialColor.LIGHT_BLUE.P500);
                this.moreRepeatsButton.setPosition(60.0f, 0.0f);
                this.moreRepeatsButton.setSize(60.0f, 52.0f);
                this.moreRepeatsButton.setIconPosition(18.0f, 14.0f);
                this.moreRepeatsButton.setIconSize(24.0f, 24.0f);
                this.repeatsGroupScrollDependable.addActor(this.moreRepeatsButton);
            }
            this.thumbsUpIcon.setVisible(Game.i.musicManager.isMusicThumbsUp(this.hash));
            if (MusicListOverlay.this.c() == this.hash) {
                this.nameLabel.setColor(MaterialColor.LIGHT_GREEN.P500);
                this.playButton.setIcon(Game.i.assetManager.getDrawable("icon-pause"));
                this.playButton.setEnabled(true);
            } else {
                this.playButton.setIcon(Game.i.assetManager.getDrawable("icon-triangle-right"));
                if (isAvailable) {
                    this.playButton.setEnabled(true);
                    this.nameLabel.setColor(Color.WHITE);
                } else {
                    this.playButton.setEnabled(false);
                    this.nameLabel.setColor(1.0f, 1.0f, 1.0f, 0.28f);
                }
            }
            this.menuThemeButton.setVisible(isAvailable);
            if (Game.i.musicManager.isMenuMusicSourceEnabled(this.source)) {
                this.menuThemeButton.setIcon(Game.i.assetManager.getDrawable("settings-toggle-on"));
                int menuMusicSourceRepeatCount = Game.i.musicManager.getMenuMusicSourceRepeatCount(this.source);
                this.repeatsLabel.setText("x" + menuMusicSourceRepeatCount);
                this.lessRepeatsButton.setVisible(menuMusicSourceRepeatCount > 1);
                this.moreRepeatsButton.setVisible(menuMusicSourceRepeatCount < 8);
                this.repeatsLabel.setVisible(true);
                return;
            }
            this.menuThemeButton.setIcon(Game.i.assetManager.getDrawable("settings-toggle-off"));
            this.moreRepeatsButton.setVisible(false);
            this.lessRepeatsButton.setVisible(false);
            this.repeatsLabel.setVisible(false);
        }
    }
}
