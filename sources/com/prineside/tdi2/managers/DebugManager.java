package com.prineside.tdi2.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.profiling.GLProfiler;
import com.badlogic.gdx.net.HttpParametersUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.LongArray;
import com.badlogic.gdx.utils.ObjectIntMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.StringBuilder;
import com.badlogic.gdx.utils.TimeUtils;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Manager;
import com.prineside.tdi2.ResourcePack;
import com.prineside.tdi2.configs.HeadlessConfig;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.events.global.GameLoad;
import com.prineside.tdi2.events.global.ScreenResize;
import com.prineside.tdi2.managers.AuthManager;
import com.prineside.tdi2.managers.MusicManager;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.managers.SoundManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.shared.VisibleDisplayFrameDebugFeature;
import com.prineside.tdi2.utils.FastRandom;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.MovingAverageInt;
import com.prineside.tdi2.utils.ObjectPair;
import com.prineside.tdi2.utils.ObjectSupplier;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.system.windows.User32;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/DebugManager.class */
public class DebugManager extends Manager.ManagerAdapter {

    /* renamed from: b, reason: collision with root package name */
    private UiManager.UiLayer f2329b;
    private Label e;
    private long f;
    private long g;
    private SettingsManager.SettingsManagerListener j;
    private int p;
    private float r;
    private long y;
    private long C;
    private String I;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2328a = TLog.forClass(DebugManager.class);
    public static final Color[] RANDOM_COLORS = {MaterialColor.RED.P500, MaterialColor.YELLOW.P500, MaterialColor.BLUE.P500, MaterialColor.LIGHT_GREEN.P500, MaterialColor.BLUE_GREY.P500, MaterialColor.ORANGE.P500, MaterialColor.BROWN.P500, MaterialColor.DEEP_ORANGE.P500, MaterialColor.CYAN.P500, MaterialColor.DEEP_PURPLE.P500, MaterialColor.GREEN.P500, MaterialColor.INDIGO.P500, MaterialColor.GREY.P500, MaterialColor.PURPLE.P500, MaterialColor.LIGHT_BLUE.P500, MaterialColor.LIME.P500, MaterialColor.PINK.P500, MaterialColor.TEAL.P500, MaterialColor.AMBER.P500};
    private static final int D = MusicManager.SpectrumConfig.DEFAULT.getSpectrumSize();
    private boolean c = false;
    private boolean d = false;
    private StringBuilder h = new StringBuilder();
    private final Array<String> i = new Array<>(false, 1, String.class);
    private final ObjectMap<String, StringBuilder> k = new ObjectMap<>();
    private final ObjectMap<String, long[]> l = new ObjectMap<>();
    private final ObjectIntMap<String> m = new ObjectIntMap<>();
    private final ObjectIntMap<String> n = new ObjectIntMap<>();
    private final LongArray o = new LongArray(false, 30);
    private final MovingAverageInt q = new MovingAverageInt(60);
    private int s = HeadlessConfig.REPORT_INTERVAL;
    private int t = 400;
    private final long[] u = new long[User32.VK_OEM_ATTN];
    private final int[] v = new int[720];
    private int w = 0;
    private int x = 1;
    private final long[] z = new long[60];
    private int A = 0;
    private float B = 16.0f;
    private final float[] E = new float[D];
    private final float[] F = new float[D];
    private final float[] G = new float[D];
    private final float[] H = new float[D];
    private final int[] K = new int[StaticSoundType.values.length];
    public final GLProfiler glProfiler = new GLProfiler(Gdx.graphics);
    private OrthographicCamera J = new OrthographicCamera();

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/DebugManager$Serializer.class */
    public static class Serializer extends SingletonSerializer<DebugManager> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public DebugManager read() {
            return Game.i.debugManager;
        }
    }

    public DebugManager() {
        this.J.setToOrtho(false, Game.i.uiManager.getScreenWidth() * 1.5f, Game.i.uiManager.getScreenHeight() * 1.5f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        try {
            StringBuilder sb = new StringBuilder();
            String str = Config.PACKAGE;
            this.I = sb.append(str.substring(str.length() - 4)).append(".208").append(".").append(Game.i.actionResolver.isAppModified() ? "M" : "V").append(Config.isModdingMode() ? "(" + Config.getModId() + ")" : "").toString();
            if (Game.i.authManager != null) {
                this.I += "." + Game.i.authManager.getPlayerId();
            }
        } catch (Exception unused) {
            this.I = "ERR";
        }
        this.J.setToOrtho(false, Game.i.uiManager.getScreenWidth() * 1.5f, Game.i.uiManager.getScreenHeight() * 1.5f);
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void setup() {
        if (Config.isHeadless()) {
            return;
        }
        Game.i.authManager.addListener(new AuthManager.AuthManagerListener.AuthManagerListenerAdapter() { // from class: com.prineside.tdi2.managers.DebugManager.1
            @Override // com.prineside.tdi2.managers.AuthManager.AuthManagerListener.AuthManagerListenerAdapter, com.prineside.tdi2.managers.AuthManager.AuthManagerListener
            public void signInStatusUpdated() {
                DebugManager.this.b();
            }
        });
        Game.EVENTS.getListeners(GameLoad.class).add(gameLoad -> {
            b();
            try {
                Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
                httpRequest.setUrl(Config.GAME_START_LOG_URL);
                HashMap hashMap = new HashMap();
                hashMap.put("os", Gdx.app == null ? "unknown" : Gdx.app.getType().name());
                if (Game.isLoaded()) {
                    String playerIdCached = Game.i.authManager.getPlayerIdCached();
                    String str = playerIdCached;
                    if (playerIdCached == null) {
                        str = "G-0000-0000-000000";
                    }
                    hashMap.put("playerid", str);
                    if (Game.i.authManager.getSessionId() != null) {
                        hashMap.put("sessionid", Game.i.authManager.getSessionId());
                    }
                } else {
                    hashMap.put("playerid", "G-0000-0000-000000");
                }
                ObjectMap<String, String> deviceInfo = Game.i.actionResolver.getDeviceInfo();
                Json json = new Json(JsonWriter.OutputType.json);
                StringWriter stringWriter = new StringWriter();
                json.setWriter(stringWriter);
                json.writeObjectStart();
                if (Game.i.localeManager != null) {
                    try {
                        json.writeValue("g.locale", Charset.defaultCharset() + "," + Game.i.localeManager.getLocale() + "," + Game.i.localeManager.i18n.getLocale());
                    } catch (Exception unused) {
                    }
                }
                ObjectMap.Entries<String, String> it = deviceInfo.iterator();
                while (it.hasNext()) {
                    ObjectMap.Entry next = it.next();
                    json.writeValue((String) next.key, next.value);
                }
                ?? r0 = json;
                r0.writeObjectStart("s.properties");
                try {
                    Properties properties = System.getProperties();
                    Enumeration<?> propertyNames = properties.propertyNames();
                    while (true) {
                        r0 = propertyNames.hasMoreElements();
                        if (r0 == 0) {
                            break;
                        }
                        Object nextElement = propertyNames.nextElement();
                        Object obj = properties.get(nextElement);
                        if (obj != null) {
                            json.writeValue(nextElement.toString(), ((String) obj).trim());
                        }
                    }
                } catch (Exception e) {
                    r0.printStackTrace();
                }
                json.writeObjectEnd();
                ?? r02 = json;
                r02.writeObjectStart("s.runtime");
                try {
                    Runtime runtime = Runtime.getRuntime();
                    json.writeValue("proc_av", Integer.valueOf(runtime.availableProcessors()));
                    json.writeValue("mem_free", Long.valueOf(runtime.freeMemory()));
                    json.writeValue("mem_max", Long.valueOf(runtime.maxMemory()));
                    r02 = json;
                    r02.writeValue("mem_total", Long.valueOf(runtime.totalMemory()));
                } catch (Exception e2) {
                    r02.printStackTrace();
                }
                json.writeObjectEnd();
                ?? r03 = json;
                r03.writeObjectStart("s.graphics");
                try {
                    json.writeValue("type", Gdx.graphics.getGLVersion().getType().toString());
                    json.writeValue("version", Gdx.graphics.getGLVersion().getMajorVersion() + "." + Gdx.graphics.getGLVersion().getMinorVersion() + "." + Gdx.graphics.getGLVersion().getReleaseVersion());
                    json.writeValue("renderer", Gdx.graphics.getGLVersion().getRendererString());
                    json.writeValue("vendor", Gdx.graphics.getGLVersion().getVendorString());
                    json.writeValue("bb_size", Gdx.graphics.getBackBufferWidth() + "x" + Gdx.graphics.getBackBufferHeight());
                    json.writeValue("density", Float.valueOf(Gdx.graphics.getDensity()));
                    r03 = json;
                    r03.writeValue("max_txt_size", Integer.valueOf(Config.getMaxTextureSize()));
                } catch (Exception e3) {
                    r03.printStackTrace();
                }
                json.writeObjectEnd();
                json.writeObjectEnd();
                hashMap.put("device", stringWriter.toString());
                try {
                    httpRequest.setContent(HttpParametersUtils.convertHttpParameters(hashMap));
                    Gdx.f881net.sendHttpRequest(httpRequest, new Net.HttpResponseListener(this) { // from class: com.prineside.tdi2.managers.DebugManager.2
                        @Override // com.badlogic.gdx.Net.HttpResponseListener
                        public void handleHttpResponse(Net.HttpResponse httpResponse) {
                            try {
                                DebugManager.f2328a.d("logGameStart: %s", httpResponse.getResultAsString());
                            } catch (Exception e4) {
                                DebugManager.f2328a.w("logGameStart", e4);
                            }
                        }

                        @Override // com.badlogic.gdx.Net.HttpResponseListener
                        public void failed(Throwable th) {
                            DebugManager.f2328a.w("logGameStart failed", th);
                        }

                        @Override // com.badlogic.gdx.Net.HttpResponseListener
                        public void cancelled() {
                            DebugManager.f2328a.d("logGameStart: cancelled", new Object[0]);
                        }
                    });
                } catch (Exception e4) {
                    for (String str2 : hashMap.keySet()) {
                        f2328a.i(str2 + ": " + ((String) hashMap.get(str2)), new Object[0]);
                    }
                    throw e4;
                }
            } catch (Exception e5) {
                f2328a.e("failed to log game start", e5);
            }
        });
        Game.EVENTS.getListeners(ScreenResize.class).add(screenResize -> {
            b();
        });
        this.f2329b = Game.i.uiManager.addLayer(UiManager.MainUiLayer.OVERLAY, 19001, "DebugManager main");
        Table table = this.f2329b.getTable();
        table.setTouchable(Touchable.disabled);
        table.setDebug(false);
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = Game.i.assetManager.getFont(16);
        labelStyle.background = Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME).tint(new Color(0.0f, 0.0f, 0.0f, 0.28f));
        this.e = new Label("Debug", labelStyle);
        this.e.setAlignment(16);
        table.add((Table) this.e).pad(5.0f).expand().top().right();
        this.j = new SettingsManager.SettingsManagerListener.SettingsManagerListenerAdapter() { // from class: com.prineside.tdi2.managers.DebugManager.3
            @Override // com.prineside.tdi2.managers.SettingsManager.SettingsManagerListener.SettingsManagerListenerAdapter, com.prineside.tdi2.managers.SettingsManager.SettingsManagerListener
            public void settingsChanged() {
                DebugManager.this.d();
            }

            @Override // com.prineside.tdi2.managers.SettingsManager.SettingsManagerListener.SettingsManagerListenerAdapter, com.prineside.tdi2.managers.SettingsManager.SettingsManagerListener
            public void customValueChanged(SettingsManager.CustomValueType customValueType, double d) {
                if (customValueType == SettingsManager.CustomValueType.DBG_SIMULATE_VISIBLE_DISPLAY_FRAME) {
                    DebugManager debugManager = DebugManager.this;
                    DebugManager.c();
                }
            }
        };
        Game.i.settingsManager.addListener(this.j);
        d();
        c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c() {
        if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DBG_SIMULATE_VISIBLE_DISPLAY_FRAME) == 0.0d) {
            VisibleDisplayFrameDebugFeature.i().hide();
        } else {
            VisibleDisplayFrameDebugFeature.i().show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        this.c = Game.i.settingsManager.isInDebugMode();
        this.d = Game.i.settingsManager.isInDebugDetailedMode();
        if (this.c) {
            this.f2329b.getTable().setVisible(true);
            if (this.d) {
                this.glProfiler.enable();
                return;
            } else {
                this.glProfiler.disable();
                return;
            }
        }
        this.f2329b.getTable().setVisible(false);
        this.glProfiler.disable();
    }

    public StringBuilder registerValue(String str) {
        if (!this.c) {
            return null;
        }
        if (!this.k.containsKey(str)) {
            this.k.put(str, new StringBuilder());
        }
        StringBuilder stringBuilder = this.k.get(str);
        stringBuilder.setLength(0);
        return stringBuilder;
    }

    public boolean isEnabled() {
        return this.c;
    }

    public void unregisterValue(String str) {
        this.k.remove(str);
    }

    public void registerFrameJob(String str, long j) {
        if (this.c) {
            if (!this.l.containsKey(str)) {
                this.l.put(str, new long[User32.VK_OEM_ATTN]);
            }
            long[] jArr = this.l.get(str);
            int i = this.w;
            jArr[i] = jArr[i] + j;
        }
    }

    public void registerFrameDrawTimeAndMemory(long j) {
        if (this.c) {
            long nanoTime = TimeUtils.nanoTime();
            this.z[this.A] = nanoTime - this.y;
            this.y = nanoTime;
            if (this.d) {
                Runtime runtime = Runtime.getRuntime();
                int freeMemory = (int) (runtime.freeMemory() / 1024);
                int i = (int) (runtime.totalMemory() / 1024);
                int maxMemory = (int) ((runtime.maxMemory() <= 0 ? 536870912L : runtime.maxMemory()) / 1024);
                this.u[this.w] = j;
                this.v[this.w * 3] = freeMemory;
                this.v[(this.w * 3) + 1] = i;
                this.v[(this.w * 3) + 2] = maxMemory;
                if (this.x < maxMemory) {
                    this.x = maxMemory;
                }
            }
        }
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void preRender(float f) {
        this.C = Game.getRealTickCount();
    }

    public void registerGameStateUpdate() {
        if (!Config.isHeadless() && Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DBG_SHOW_FPS) != 0.0d) {
            this.o.add(Game.getTimestampMillis());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void postRender(float f) {
        StaticSoundType staticSoundType;
        float f2;
        float f3;
        if (Config.isHeadless() || this.f2329b == null) {
            return;
        }
        Game.getRealTickCount();
        registerFrameDrawTimeAndMemory(Game.getRealTickCount() - this.C);
        SpriteBatch spriteBatch = Game.i.renderingManager.batch;
        ShapeRenderer shapeRenderer = Game.i.renderingManager.shapeRenderer;
        Camera camera = this.f2329b.getTable().getStage().getCamera();
        ResourcePack.ResourcePackBitmapFont font = Game.i.assetManager.getFont(24);
        int screenSafeMargin = Game.i.uiManager.getScreenSafeMargin();
        if (this.I == null) {
            b();
        }
        spriteBatch.setProjectionMatrix(this.J.combined);
        spriteBatch.begin();
        Gdx.gl.glEnable(3042);
        Gdx.gl.glBlendFunc(770, 771);
        Game.i.assetManager.getDebugFont(false).setColor(0.0f, 1.0f, 0.0f, 0.03f);
        float f4 = 1.0f;
        Game.i.assetManager.getDebugFont(false).draw(spriteBatch, this.I, 1.0f, 17.0f);
        spriteBatch.end();
        Game.i.assetManager.getDebugFont(false).setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
        spriteBatch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);
        if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DBG_SHOW_FPS) != 0.0d) {
            this.r += f;
            if (this.r > 0.2f) {
                this.p = 0;
                long timestampMillis = Game.getTimestampMillis();
                for (int i = this.o.size - 1; i >= 0; i--) {
                    if (timestampMillis - this.o.items[i] > 1000) {
                        this.o.removeIndex(i);
                    } else {
                        this.p++;
                    }
                }
                this.r = 0.0f;
            }
            spriteBatch.begin();
            try {
                this.h.setLength(0);
                this.q.push(this.p);
                this.h.append(Gdx.graphics.getFramesPerSecond()).append(" FPS / ").append(this.p).append(" (").append(this.q.getAverage()).append(") UPS");
                font.setColor(0.0f, 0.0f, 0.0f, 0.56f);
                font.draw(spriteBatch, this.h, screenSafeMargin + 17, Game.i.settingsManager.getScaledViewportHeight() - 17);
                font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
                f4 = screenSafeMargin + 15;
                font.draw(spriteBatch, this.h, f4, Game.i.settingsManager.getScaledViewportHeight() - 15);
            } catch (Exception unused) {
                f2328a.e("failed to draw FPS", new Object[0]);
            }
            spriteBatch.end();
        }
        spriteBatch.setColor(Config.WHITE_COLOR_CACHED_FLOAT_BITS);
        if (Game.i.settingsManager.getCustomValue(SettingsManager.CustomValueType.DBG_VIEWPORT_CULLING) != 0.0d) {
            Gdx.gl.glEnable(3042);
            Gdx.gl.glBlendFunc(770, 771);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(0.0f, 1.0f, 0.0f, 0.28f);
            float f5 = camera.viewportWidth / 2.0f;
            f4 = camera.viewportWidth / 2.0f;
            shapeRenderer.rectLine(f5, 0.0f, f4, Game.i.settingsManager.getScaledViewportHeight(), 2.0f);
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.end();
        }
        if (this.c) {
            long realTickCount = Game.getRealTickCount();
            if (realTickCount - this.g > 100000) {
                if (this.c) {
                    registerValue("GL calls").append(this.glProfiler.getCalls());
                    registerValue("Draw calls").append(this.glProfiler.getDrawCalls());
                    registerValue("Texture bindings").append(this.glProfiler.getTextureBindings());
                    registerValue("Max sprites / batch").append(Game.i.renderingManager.batch.maxSpritesInBatch);
                    registerValue("Resolution").append(Game.i.uiManager.getScreenWidth()).append('x').append(Game.i.uiManager.getScreenHeight());
                    registerValue("Sounds").append(Game.i.soundManager.playingSoundStats.size).append(" / 48");
                    if (Game.i.cursorGraphics.f2314a.size != 0) {
                        StringBuilder registerValue = registerValue("Cursors");
                        for (int i2 = 0; i2 < Game.i.cursorGraphics.f2314a.size; i2++) {
                            ObjectPair<String, ObjectSupplier<Cursor.SystemCursor>> objectPair = Game.i.cursorGraphics.f2314a.get(i2);
                            registerValue.append(SequenceUtils.EOL).append(objectPair.first).append(": ").append(objectPair.second.get()).append("    ");
                        }
                    } else {
                        unregisterValue("Cursors");
                    }
                }
                this.g = realTickCount;
            }
            if (Game.getRealTickCount() - this.f > 300000) {
                this.f = Game.getRealTickCount();
                this.h.setLength(0);
                this.i.clear();
                ObjectMap.Entries<String, StringBuilder> it = this.k.iterator();
                while (it.hasNext()) {
                    this.i.add((String) it.next().key);
                }
                this.i.sort();
                Array.ArrayIterator<String> it2 = this.i.iterator();
                while (it2.hasNext()) {
                    String next = it2.next();
                    this.h.append(next);
                    this.h.append(" = ");
                    this.h.append(this.k.get(next));
                    this.h.append(SequenceUtils.EOL);
                }
                this.e.setText(this.h);
            }
            this.A++;
            if (this.A == 60) {
                this.A = 0;
            }
            float f6 = ((camera.viewportWidth - 240.0f) - 5.0f) - screenSafeMargin;
            Gdx.gl.glEnable(3042);
            Gdx.gl.glBlendFunc(770, 771);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            int i3 = 0;
            for (int i4 = this.A; i4 < 60; i4++) {
                double d = 1.0E9d / this.z[i4];
                float f7 = f6 + (4.0f * i3);
                float f8 = (float) d;
                if (d > 50.0d) {
                    shapeRenderer.setColor(0.0f, 1.0f, 0.0f, 0.3f);
                } else if (d > 20.0d) {
                    shapeRenderer.setColor(0.5f, 0.5f, 0.0f, 0.5f);
                } else {
                    shapeRenderer.setColor(1.0f, 0.0f, 0.0f, 0.5f);
                }
                shapeRenderer.rect(f7 - 4.0f, 5.0f, 4.0f, f8);
                i3++;
            }
            for (int i5 = 0; i5 < this.A; i5++) {
                double d2 = 1.0E9d / this.z[i5];
                float f9 = f6 + (4.0f * i3);
                float f10 = (float) d2;
                if (d2 > 50.0d) {
                    shapeRenderer.setColor(0.0f, 1.0f, 0.0f, 0.3f);
                } else if (d2 > 20.0d) {
                    shapeRenderer.setColor(0.5f, 0.5f, 0.0f, 0.5f);
                } else {
                    shapeRenderer.setColor(1.0f, 0.0f, 0.0f, 0.5f);
                }
                shapeRenderer.rect(f9 - 4.0f, 5.0f, 4.0f, f10);
                i3++;
            }
            shapeRenderer.setColor(0.0f, 1.0f, 0.0f, 0.5f);
            float regularLayerWidth = Game.i.uiManager.getRegularLayerWidth() * 0.5f;
            MusicManager.SpectrumConfig spectrumConfig = Game.i.musicManager.getSpectrumConfig(MusicManager.SpectrumConfig.DEFAULT);
            float[] fArr = new float[D];
            spectrumConfig.copySpectrum(fArr, true);
            for (int i6 = 0; i6 < fArr.length; i6++) {
                float f11 = fArr[i6];
                if (f11 >= this.E[i6]) {
                    this.E[i6] = f11;
                    f3 = f11;
                    this.G[i6] = 0.0f;
                } else {
                    f3 = this.E[i6] - this.G[i6];
                    float[] fArr2 = this.G;
                    int i7 = i6;
                    fArr2[i7] = fArr2[i7] + (f * 0.35f);
                    if (f3 < f11) {
                        f3 = f11;
                    }
                }
                this.E[i6] = f3;
                float f12 = f3 * 200.0f;
                shapeRenderer.rect(regularLayerWidth - f12, 5.0f + (i6 * 10.0f), f12, 8.0f);
            }
            float f13 = 1.0f;
            shapeRenderer.setColor(0.0f, 0.5f, 1.0f, 0.5f);
            spectrumConfig.copySpectrum(fArr, false);
            for (int i8 = 0; i8 < fArr.length; i8++) {
                float f14 = fArr[i8];
                if (f14 >= this.F[i8]) {
                    this.F[i8] = f14;
                    f2 = f14;
                    this.H[i8] = 0.0f;
                } else {
                    f2 = this.F[i8] - this.H[i8];
                    float[] fArr3 = this.H;
                    int i9 = i8;
                    fArr3[i9] = fArr3[i9] + (f * 0.35f);
                    if (f2 < f14) {
                        f2 = f14;
                    }
                }
                this.F[i8] = f2;
                f13 = f2 * 200.0f;
                shapeRenderer.rect(regularLayerWidth, 5.0f + (i8 * 10.0f), f13, 8.0f);
            }
            shapeRenderer.end();
            if (!this.d) {
                Runtime runtime = Runtime.getRuntime();
                registerValue("Memory F/T/M").append(StringFormatter.commaSeparatedNumber((int) (runtime.freeMemory() / 1024))).append(" / ").append(StringFormatter.commaSeparatedNumber((int) (runtime.totalMemory() / 1024))).append(" / ").append(StringFormatter.commaSeparatedNumber((int) (runtime.maxMemory() / 1024)));
            } else {
                unregisterValue("Memory");
                this.i.clear();
                ObjectMap.Entries<String, long[]> it3 = this.l.iterator();
                while (it3.hasNext()) {
                    this.i.add((String) it3.next().key);
                }
                long j = 1000;
                for (long j2 : this.u) {
                    long j3 = f13;
                    if (j2 > j) {
                        j = j3;
                    }
                }
                if (j > 50000) {
                    j = 50000;
                }
                float f15 = (int) (((float) j) / 1000.0f);
                float f16 = 1.0f / f15;
                float f17 = this.B - f15;
                float f18 = f * 10.0f;
                if (StrictMath.abs(f17) > f18) {
                    f15 = this.B - (f17 * (f18 / StrictMath.abs(f17)));
                }
                this.B = f15;
                int regularLayerWidth2 = ((((int) Game.i.uiManager.getRegularLayerWidth()) - 5) - User32.WM_MDISETMENU) - User32.VK_OEM_ATTN;
                int i10 = (int) (camera.viewportHeight / 2.0f);
                float f19 = regularLayerWidth2 / 240.0f;
                Gdx.gl.glEnable(3042);
                Gdx.gl.glBlendFunc(770, 771);
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.setColor(0.0f, 0.0f, 0.0f, 0.3f);
                shapeRenderer.rect(0.0f, 5.0f, camera.viewportWidth, camera.viewportHeight);
                int i11 = this.B < 10.0f ? 1 : 2;
                shapeRenderer.setColor(1.0f, 1.0f, 1.0f, 0.14f);
                int i12 = i11;
                while (true) {
                    int i13 = i12;
                    if (i13 > f15) {
                        break;
                    }
                    float f20 = i13 * i10 * f16;
                    shapeRenderer.rectLine(screenSafeMargin + User32.WM_MDISETMENU, f20, camera.viewportWidth - 5.0f, f20, 2.0f);
                    i12 = i13 + i11;
                }
                shapeRenderer.end();
                ResourcePack.ResourcePackBitmapFont font2 = Game.i.assetManager.getFont(21);
                spriteBatch.begin();
                font2.setColor(1.0f, 1.0f, 1.0f, 0.28f);
                int i14 = i11;
                while (true) {
                    int i15 = i14;
                    if (i15 > f15) {
                        break;
                    }
                    this.h.setLength(0);
                    this.h.append(i15).append(" ms");
                    font2.draw(spriteBatch, this.h, screenSafeMargin + User32.WM_MDISETMENU, (i15 * i10 * f16) + 24.0f);
                    i14 = i15 + i11;
                }
                font2.setColor(Color.WHITE);
                spriteBatch.end();
                spriteBatch.begin();
                font2.setColor(Color.WHITE);
                font2.draw(spriteBatch, "min", screenSafeMargin + GLFW.GLFW_KEY_KP_0, 32.0f);
                font2.draw(spriteBatch, "avg", screenSafeMargin + 400, 32.0f);
                font2.draw(spriteBatch, "max", screenSafeMargin + 480, 32.0f);
                long j4 = 1;
                long j5 = 1;
                long j6 = 1;
                int i16 = 0;
                for (int i17 = this.i.size - 1; i17 >= 0; i17--) {
                    String str = this.i.items[i17];
                    long j7 = 0;
                    long j8 = Long.MAX_VALUE;
                    long j9 = Long.MIN_VALUE;
                    for (long j10 : this.l.get(str)) {
                        j7 += j10;
                        if (j8 > j10) {
                            j8 = j10;
                        }
                        if (j9 < j10) {
                            j9 = j10;
                        }
                    }
                    long length = j7 / r0.length;
                    boolean z = j9 > ((long) this.s) || length > ((long) this.t);
                    if (length > this.t) {
                        i16++;
                    }
                    if (z) {
                        this.n.put(str, 60);
                    }
                    int i18 = this.n.get(str, 0);
                    if (i18 > 0) {
                        this.n.put(str, i18 - 1);
                    } else if (!z) {
                        this.i.removeIndex(i17);
                    }
                    if (j4 < j8) {
                        j4 = j8;
                    }
                    if (j5 < length) {
                        j5 = length;
                    }
                    if (j6 < j9) {
                        j6 = j9;
                    }
                }
                if (i16 > 5) {
                    this.t = (int) (this.t * 1.07d);
                    f2328a.i("increasing avg time threshold to " + this.t, new Object[0]);
                } else if (i16 <= 2 && this.t != 400) {
                    this.t = (int) (this.t * 0.95d);
                    if (this.t < 400) {
                        this.t = 400;
                    }
                    f2328a.i("decreasing avg time threshold to " + this.t, new Object[0]);
                }
                this.i.sort();
                this.i.reverse();
                int i19 = this.m.size;
                Array array = new Array(true, i19, String.class);
                ObjectIntMap.Keys<String> it4 = this.m.keys().iterator();
                while (it4.hasNext()) {
                    array.add(it4.next());
                }
                for (int i20 = 0; i20 < array.size; i20++) {
                    String str2 = (String) array.get(i20);
                    if (!this.i.contains(str2, false)) {
                        this.m.remove(str2, 0);
                    }
                }
                int i21 = 0;
                Array.ArrayIterator<String> it5 = this.i.iterator();
                float f21 = i19;
                while (it5.hasNext()) {
                    String next2 = it5.next();
                    long j11 = Long.MAX_VALUE;
                    long j12 = Long.MIN_VALUE;
                    long j13 = 0;
                    for (long j14 : this.l.get(next2)) {
                        j13 += j14;
                        if (j11 > j14) {
                            j11 = j14;
                        }
                        if (j12 < j14) {
                            j12 = j14;
                        }
                    }
                    long length2 = j13 / r0.length;
                    int i22 = this.m.get(next2, -1);
                    int i23 = i22;
                    if (i22 == -1) {
                        int i24 = 0;
                        while (true) {
                            if (i24 >= RANDOM_COLORS.length) {
                                break;
                            }
                            if (this.m.containsValue(i24)) {
                                i24++;
                            } else {
                                i23 = i24;
                                break;
                            }
                        }
                        if (i23 == -1) {
                            i23 = 0;
                        } else {
                            this.m.put(next2, i23);
                        }
                    }
                    font2.setColor(RANDOM_COLORS[i23]);
                    font2.draw(spriteBatch, next2, screenSafeMargin + 5, (i21 + 2) * 24);
                    font2.setColor(1.0f, 1.0f, 1.0f, 0.5f + ((0.5f * ((float) j11)) / ((float) j4)));
                    this.h.setLength(0);
                    this.h.append(j11);
                    font2.draw(spriteBatch, this.h, screenSafeMargin + GLFW.GLFW_KEY_KP_0, (i21 + 2) * 24);
                    font2.setColor(1.0f, 1.0f, 1.0f, 0.5f + ((0.5f * ((float) length2)) / ((float) j5)));
                    this.h.setLength(0);
                    this.h.append(length2);
                    font2.draw(spriteBatch, this.h, screenSafeMargin + 400, (i21 + 2) * 24);
                    font2.setColor(1.0f, 1.0f, 1.0f, 0.5f + ((0.5f * ((float) j12)) / ((float) j6)));
                    this.h.setLength(0);
                    this.h.append(j12);
                    float f22 = screenSafeMargin + 480;
                    font2.draw(spriteBatch, this.h, f22, (i21 + 2) * 24);
                    i21++;
                    f21 = f22;
                }
                font2.setColor(Color.WHITE);
                spriteBatch.end();
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                Gdx.gl.glEnable(3042);
                Gdx.gl.glBlendFunc(770, 771);
                Array.ArrayIterator<String> it6 = this.i.iterator();
                while (it6.hasNext()) {
                    String next3 = it6.next();
                    long[] jArr = this.l.get(next3);
                    shapeRenderer.setColor(RANDOM_COLORS[this.m.get(next3, 0)]);
                    float f23 = -1.0f;
                    int i25 = 0;
                    int i26 = 0;
                    f21 = f21;
                    while (i26 < 240) {
                        long j15 = jArr[(this.w + i26) % User32.VK_OEM_ATTN];
                        boolean z2 = f21 == true ? 1 : 0;
                        float f24 = ((float) j15) / 1000.0f;
                        float f25 = 560.0f + (f19 * i25) + screenSafeMargin;
                        float f26 = 5.0f + (f24 * f16 * i10);
                        if (i25 != 0) {
                            f21 = f25;
                            shapeRenderer.rectLine(f25 - f19, f23, f21 == true ? 1.0f : 0.0f, f26, 2.0f);
                        }
                        f23 = f26;
                        i25++;
                        i26++;
                        f21 = f21;
                    }
                }
                float f27 = -1.0f;
                int i27 = 0;
                float f28 = 1.0f;
                shapeRenderer.setColor(1.0f, 1.0f, 1.0f, 0.21f);
                for (int i28 = 0; i28 < 240; i28++) {
                    float f29 = 560.0f + (f19 * i27) + screenSafeMargin;
                    float f30 = 5.0f + ((((float) this.u[(this.w + i28) % User32.VK_OEM_ATTN]) / 1000.0f) * f16 * i10);
                    if (i27 != 0) {
                        f28 = f29;
                        shapeRenderer.rectLine(f29 - f19, f27, f28, f30, 4.0f);
                    }
                    f27 = f30;
                    i27++;
                }
                shapeRenderer.end();
                float f31 = i10 + 5 + 50.0f;
                float f32 = i10 * 0.5f;
                float f33 = 1.0f / this.x;
                Gdx.gl.glEnable(3042);
                Gdx.gl.glBlendFunc(770, 771);
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.setColor(0.0f, 0.0f, 0.0f, 0.56f);
                shapeRenderer.rect(360.0f, f31 - 16.0f, regularLayerWidth2 + 200.0f, f32 + 32.0f + 16.0f);
                int i29 = 0;
                shapeRenderer.setColor(0.0f, 0.7f, 1.0f, 0.21f);
                for (int i30 = this.w; i30 < 240; i30++) {
                    float f34 = 560.0f + (f19 * i29) + screenSafeMargin;
                    float f35 = f33 * (this.v[(i30 * 3) + 1] - this.v[i30 * 3]) * f32;
                    if (i29 != 0) {
                        shapeRenderer.rect(f34 - f19, f31, f19, f35);
                    }
                    i29++;
                }
                for (int i31 = 0; i31 < this.w; i31++) {
                    float f36 = 560.0f + (f19 * i29) + screenSafeMargin;
                    float f37 = f33 * (this.v[(i31 * 3) + 1] - this.v[i31 * 3]) * f32;
                    if (i29 != 0) {
                        shapeRenderer.rect(f36 - f19, f31, f19, f37);
                    }
                    i29++;
                }
                int i32 = 0;
                int i33 = Integer.MAX_VALUE;
                shapeRenderer.setColor(0.0f, 1.0f, 0.0f, 0.21f);
                for (int i34 = this.w; i34 < 240; i34++) {
                    int i35 = this.v[(i34 * 3) + 1] - this.v[i34 * 3];
                    int i36 = this.v[i34 * 3];
                    float f38 = 560.0f + (f19 * i32) + screenSafeMargin;
                    float f39 = f31 + (f33 * i35 * f32);
                    float f40 = f33 * i36 * f32;
                    if (i32 != 0) {
                        shapeRenderer.rect(f38 - f19, f39, f19, f40);
                    }
                    if (i33 < i36) {
                        shapeRenderer.circle(f38, f39, 4.0f, 8);
                        shapeRenderer.circle(f38, f39, 4.0f, 8);
                        shapeRenderer.end();
                        spriteBatch.begin();
                        font2.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                        this.h.setLength(0);
                        this.h.append(StringFormatter.commaSeparatedNumber(i36)).append("kb");
                        font2.draw(spriteBatch, this.h, f38 + 8.0f, f39);
                        font2.setColor(Color.WHITE);
                        spriteBatch.end();
                        Gdx.gl.glEnable(3042);
                        Gdx.gl.glBlendFunc(770, 771);
                        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                        shapeRenderer.setColor(0.0f, 1.0f, 0.0f, 0.21f);
                    }
                    i33 = i36;
                    i32++;
                }
                for (int i37 = 0; i37 < this.w; i37++) {
                    int i38 = this.v[(i37 * 3) + 1] - this.v[i37 * 3];
                    int i39 = this.v[i37 * 3];
                    float f41 = 560.0f + (f19 * i32) + screenSafeMargin;
                    float f42 = f31 + (f33 * i38 * f32);
                    float f43 = f33 * i39 * f32;
                    if (i32 != 0) {
                        shapeRenderer.rect(f41 - f19, f42, f19, f43);
                    }
                    if (i33 < i39) {
                        shapeRenderer.circle(f41, f42, 4.0f, 8);
                        shapeRenderer.circle(f41, f42, 4.0f, 8);
                        shapeRenderer.end();
                        spriteBatch.begin();
                        font2.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                        this.h.setLength(0);
                        this.h.append(String.valueOf(StringFormatter.commaSeparatedNumber(i39))).append("kb");
                        font2.draw(spriteBatch, this.h, f41 + 8.0f, f42);
                        font2.setColor(Color.WHITE);
                        spriteBatch.end();
                        Gdx.gl.glEnable(3042);
                        Gdx.gl.glBlendFunc(770, 771);
                        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                        shapeRenderer.setColor(0.0f, 1.0f, 0.0f, 0.21f);
                    }
                    i33 = i39;
                    i32++;
                }
                float f44 = -1.0f;
                int i40 = 0;
                shapeRenderer.setColor(1.0f, 0.8f, 0.0f, 0.21f);
                for (int i41 = this.w; i41 < 240; i41++) {
                    float f45 = 560.0f + (f19 * i40) + screenSafeMargin;
                    float f46 = f31 + (this.v[(i41 * 3) + 2] * f33 * f32);
                    if (i40 != 0) {
                        shapeRenderer.rectLine(f45 - f19, f44, f45, f46, 3.0f);
                    }
                    f44 = f46;
                    i40++;
                }
                for (int i42 = 0; i42 < this.w; i42++) {
                    float f47 = 560.0f + (f19 * i40) + screenSafeMargin;
                    float f48 = f31 + (this.v[(i42 * 3) + 2] * f33 * f32);
                    if (i40 != 0) {
                        shapeRenderer.rectLine(f47 - f19, f44, f47, f48, 3.0f);
                    }
                    f44 = f48;
                    i40++;
                }
                shapeRenderer.setColor(Color.WHITE);
                shapeRenderer.end();
                spriteBatch.begin();
                font2.setColor(0.0f, 0.7f, 1.0f, 0.56f);
                this.h.setLength(0);
                this.h.append("Total: ").append(StringFormatter.commaSeparatedNumber(this.v[(this.w * 3) + 1])).append("kb");
                font2.draw(spriteBatch, this.h, 550.0f, f31 + (this.v[(this.w * 3) + 1] * f33 * f32), 1.0f, 16, false);
                font2.setColor(0.0f, 1.0f, 0.0f, 0.56f);
                this.h.setLength(0);
                this.h.append("Free: ").append(StringFormatter.commaSeparatedNumber(this.v[this.w * 3])).append("kb");
                font2.draw(spriteBatch, this.h, 550.0f, f31 + (this.v[this.w * 3] * f33 * f32), 1.0f, 16, false);
                font2.setColor(1.0f, 0.7f, 0.0f, 0.56f);
                this.h.setLength(0);
                this.h.append("Max: ").append(StringFormatter.commaSeparatedNumber(this.v[(this.w * 3) + 2])).append("kb");
                font2.draw(spriteBatch, this.h, 550.0f, f31 + (this.v[(this.w * 3) + 2] * f33 * f32) + 16.0f, 1.0f, 16, false);
                font2.setColor(Color.WHITE);
                spriteBatch.end();
                this.w++;
                if (this.w == 240) {
                    this.w = 0;
                }
                Array.ArrayIterator<String> it7 = this.i.iterator();
                while (it7.hasNext()) {
                    String next4 = it7.next();
                    long[] jArr2 = this.l.get(next4);
                    jArr2[this.w] = 0;
                    boolean z3 = true;
                    int length3 = jArr2.length;
                    int i43 = 0;
                    while (true) {
                        if (i43 >= length3) {
                            break;
                        }
                        if (jArr2[i43] == 0) {
                            i43++;
                        } else {
                            z3 = false;
                            break;
                        }
                    }
                    if (z3) {
                        this.l.remove(next4);
                    }
                }
                Arrays.fill(this.K, 0);
                for (int i44 = Game.i.soundManager.playingSoundStats.size - 1; i44 >= 0; i44--) {
                    SoundManager.PlayingSoundStat playingSoundStat = Game.i.soundManager.playingSoundStats.items[i44];
                    if (playingSoundStat != null && (staticSoundType = playingSoundStat.type) != null) {
                        int[] iArr = this.K;
                        int ordinal = staticSoundType.ordinal();
                        iArr[ordinal] = iArr[ordinal] + 1;
                    }
                }
                float f49 = 0.0f;
                this.h.setLength(0);
                for (int i45 = 0; i45 < this.K.length; i45++) {
                    if (this.K[i45] != 0) {
                        if (this.K[i45] >= 8) {
                            this.h.append("[#FFFF00]");
                        }
                        this.h.append(StaticSoundType.values[i45].name()).append(": ").append(this.K[i45]).append(SequenceUtils.EOL);
                        if (this.K[i45] >= 8) {
                            this.h.append("[]");
                        }
                        f49 += 1.0f;
                    }
                }
                if (this.h.length != 0) {
                    font2.setColor(1.0f, 1.0f, 1.0f, 0.56f);
                    spriteBatch.begin();
                    font2.draw(spriteBatch, this.h, ((camera.viewportWidth - 100.0f) - screenSafeMargin) - 5.0f, 80.0f + (f49 * font2.getLineHeight()), 100.0f, 16, false);
                    spriteBatch.end();
                    font2.setColor(Color.WHITE);
                }
            }
            this.glProfiler.reset();
        }
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void test() {
        for (int i = 0; i < 100; i++) {
            float nextFloat = FastRandom.random.nextFloat() - FastRandom.random.nextFloat();
            if (nextFloat < -1.0f || nextFloat > 1.0f) {
                throw new RuntimeException("Manual triangular random failed: " + nextFloat);
            }
        }
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.badlogic.gdx.utils.Disposable
    public void dispose() {
        if (Game.i.settingsManager != null && this.j != null) {
            Game.i.settingsManager.removeListener(this.j);
        }
    }
}
