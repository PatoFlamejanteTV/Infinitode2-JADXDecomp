package com.prineside.tdi2.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.DepthTestAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.IntAttribute;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.model.data.ModelData;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.StringBuilder;
import com.badlogic.gdx.utils.UBJsonReader;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Manager;
import com.prineside.tdi2.ResourcePack;
import com.prineside.tdi2.StaticSound;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.ibxm.Module;
import com.prineside.tdi2.managers.SettingsManager;
import com.prineside.tdi2.scene2d.ui.List;
import com.prineside.tdi2.scene2d.ui.ScrollPane;
import com.prineside.tdi2.scene2d.ui.SelectBox;
import com.prineside.tdi2.scene2d.ui.TextField;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.Window;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.ObjectConsumer;
import com.prineside.tdi2.utils.PackColor;
import com.prineside.tdi2.utils.Quad;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.errorhandling.CrashHandler;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.lang.ref.SoftReference;
import java.util.concurrent.ConcurrentHashMap;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/AssetManager.class */
public class AssetManager extends Manager.ManagerAdapter {
    public static final String BLANK_REGION_NAME = "blank";
    private Label.LabelStyle e;
    private Label.LabelStyle f;
    private Model g;
    public Material normalMaterial;
    public Material blendedMaterial;
    private BitmapFont h;
    private BitmapFont i;
    private BitmapFont j;
    private BitmapFont k;
    private BitmapFont l;
    private BitmapFont m;
    private BitmapFont n;
    private BitmapFont o;
    private BitmapFont p;
    private Texture q;
    private final RegionAliasCharPair[] s;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2278a = TLog.forClass(AssetManager.class);
    private static final StringBuilder w = new StringBuilder();

    /* renamed from: b, reason: collision with root package name */
    private final Array<ResourcePack> f2279b = new Array<>(ResourcePack.class);
    private final ObjectMap<String, TextureRegionDrawable> c = new ObjectMap<>();
    private final IntMap<Label.LabelStyle> d = new IntMap<>();
    private final ConcurrentHashMap<String, Model> r = new ConcurrentHashMap<>();
    private final IntMap<RegionAliasCharPair> t = new IntMap<>();
    private final ObjectMap<String, ParticleEffectPool> u = new ObjectMap<>();
    private final ObjectMap<String, SoftReference<WebTextureRegion>> v = new ObjectMap<>();

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/AssetManager$Serializer.class */
    public static class Serializer extends SingletonSerializer<AssetManager> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public AssetManager read() {
            return Game.i.assetManager;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/AssetManager$TextureRegions.class */
    public static final class TextureRegions {

        /* renamed from: a, reason: collision with root package name */
        private static TextureRegions f2283a;
        public ResourcePack.AtlasTextureRegion blank;
        public ResourcePack.AtlasTextureRegion tileOutlineActive;
        public ResourcePack.AtlasTextureRegion tileOutlineHover;
        public ResourcePack.AtlasTextureRegion gateOutlineVerticalActive;
        public ResourcePack.AtlasTextureRegion gateOutlineVerticalHover;
        public ResourcePack.AtlasTextureRegion gateOutlineHorizontalActive;
        public ResourcePack.AtlasTextureRegion gateOutlineHorizontalHover;
        public ResourcePack.AtlasTextureRegion crosshairSmall;
        public ResourcePack.AtlasTextureRegion muzzleFlash1;
        public ResourcePack.AtlasTextureRegion muzzleFlash2;
        public ResourcePack.AtlasTextureRegion muzzleFlashCompensator1;
        public ResourcePack.AtlasTextureRegion muzzleFlashCompensator2;
        public ResourcePack.AtlasTextureRegion muzzleFlashSmall;
        public ResourcePack.AtlasTextureRegion flyingPaper10000_1;
        public ResourcePack.AtlasTextureRegion flyingPaper5000_1;
        public ResourcePack.AtlasTextureRegion flyingPaper1000_1;
        public ResourcePack.AtlasTextureRegion flyingPaper500_1;
        public ResourcePack.AtlasTextureRegion flyingPaper200_1;
        public ResourcePack.AtlasTextureRegion flyingPaper100_1;
        public ResourcePack.AtlasTextureRegion flyingPaper20_1;
        public ResourcePack.AtlasTextureRegion flyingPaper5_1;
        public ResourcePack.AtlasTextureRegion flyingPaper1_1;
        public ResourcePack.AtlasTextureRegion flyingPaper1_2;
        public ResourcePack.AtlasTextureRegion flyingPaper1_3;
        public ResourcePack.AtlasTextureRegion flyingPaper1_4;
        public ResourcePack.AtlasTextureRegion circle;
        public ResourcePack.AtlasTextureRegion smallCircle;
        public ResourcePack.AtlasTextureRegion roundedSmallRect;
        public ResourcePack.AtlasTextureRegion particlePentagon;
        public ResourcePack.AtlasTextureRegion iconSmokeBomb;
        public ResourcePack.AtlasTextureRegion enemyDialog;
        public ResourcePack.AtlasTextureRegion iconOrgan;
        public ResourcePack.AtlasTextureRegion iconPickaxe;
        public ResourcePack.AtlasTextureRegion iconLoop;
        public ResourcePack.AtlasTextureRegion bulletTraceThin;
        public ResourcePack.AtlasTextureRegion buffHealthBarIconPoison;
        public ResourcePack.AtlasTextureRegion buffHealthBarIconPoisonTwo;
        public ResourcePack.AtlasTextureRegion buffHealthBarIconPoisonThree;
        public ResourcePack.AtlasTextureRegion buffHealthBarIconFreezing;
        public ResourcePack.AtlasTextureRegion buffHealthBarIconBurn;
        public ResourcePack.AtlasTextureRegion buffHealthBarIconBlizzard;
        public ResourcePack.AtlasTextureRegion buffHealthBarIconArmor;
        public ResourcePack.AtlasTextureRegion buffHealthBarIconSnowball;
        public ResourcePack.AtlasTextureRegion buffHealthBarIconRegeneration;
        public ResourcePack.AtlasTextureRegion buffHealthBarIconBlastThrowBack;
        public ResourcePack.AtlasTextureRegion buffHealthBarIconStun;
        public ResourcePack.AtlasTextureRegion buffHealthBarIconBonusCoins;
        public ResourcePack.AtlasTextureRegion buffHealthBarIconBonusXp;
        public ResourcePack.AtlasTextureRegion buffHealthBarIconDeathExplosion;
        public ResourcePack.AtlasTextureRegion buffHealthBarIconChainReaction;
        public ResourcePack.AtlasTextureRegion buffHealthBarIconVulnerability;
        public ResourcePack.AtlasTextureRegion buffHealthBarIconInvulnerability;
        public ResourcePack.AtlasTextureRegion buffHealthBarIconSlipping;
        public ResourcePack.AtlasTextureRegion buffHealthBarIconNoDamage;
        public ResourcePack.AtlasTextureRegion buffHealthBarIconNoBonusSystemPoints;
        public ResourcePack.AtlasTextureRegion overloadImpulseA;
        public ResourcePack.AtlasTextureRegion overloadImpulseB;
        public ResourcePack.AtlasTextureRegion overloadImpulseC;
        public ResourcePack.AtlasTextureRegion overloadImpulseD;
        public ResourcePack.AtlasTextureRegion encounterBirdWingLeft;
        public ResourcePack.AtlasTextureRegion encounterBirdWingRight;

        public static TextureRegions i() {
            return f2283a;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/AssetManager$RegionAliasCharPair.class */
    private static class RegionAliasCharPair {

        /* renamed from: a, reason: collision with root package name */
        char f2281a;

        /* renamed from: b, reason: collision with root package name */
        TextureRegion f2282b;

        private RegionAliasCharPair() {
        }

        /* synthetic */ RegionAliasCharPair(byte b2) {
            this();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0326  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x04cc  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x05ab A[LOOP:8: B:86:0x059f->B:88:0x05ab, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public AssetManager() {
        /*
            Method dump skipped, instructions count: 2411
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.managers.AssetManager.<init>():void");
    }

    public ParticleEffectPool getParticleEffectPool(String str) {
        ParticleEffectPool particleEffectPool = this.u.get(str, null);
        ParticleEffectPool particleEffectPool2 = particleEffectPool;
        if (particleEffectPool == null) {
            ParticleEffect particleEffect = new ParticleEffect();
            particleEffect.load(Gdx.files.internal("particles/" + str), getBlankWhiteTextureRegion().getAtlas());
            particleEffect.setEmittersCleanUpBlendFunction(false);
            particleEffectPool2 = new ParticleEffectPool(particleEffect, 1, 512);
            this.u.put(str, particleEffectPool2);
        }
        return particleEffectPool2;
    }

    public ParticleEffectPool getParticleEffectPoolWithTemplate(String str, ParticleEffect particleEffect) {
        ParticleEffectPool particleEffectPool = this.u.get(str, null);
        ParticleEffectPool particleEffectPool2 = particleEffectPool;
        if (particleEffectPool == null) {
            particleEffect.setEmittersCleanUpBlendFunction(false);
            particleEffectPool2 = new ParticleEffectPool(particleEffect, 1, 512);
            this.u.put(str, particleEffectPool2);
        }
        return particleEffectPool2;
    }

    public static void clearCacheDir() {
        FileHandle local = Gdx.files.local("cache");
        if (local.isDirectory()) {
            local.deleteDirectory();
            f2278a.i("cache dir removed", new Object[0]);
        }
    }

    public static FileHandle localOrInternalFile(String str) {
        FileHandle local = Gdx.files.local(str);
        if (local.exists()) {
            return local;
        }
        return Gdx.files.internal(str);
    }

    private static void a(WebTextureRegion webTextureRegion, byte[] bArr) {
        if (bArr.length != 0) {
            f2278a.i("got response " + bArr.length + SequenceUtils.SPACE + webTextureRegion.src, new Object[0]);
            Pixmap pixmap = new Pixmap(bArr, 0, bArr.length);
            Pixmap pixmap2 = pixmap;
            int width = pixmap.getWidth();
            int height = pixmap2.getHeight();
            int width2 = pixmap2.getWidth();
            int height2 = pixmap2.getHeight();
            if (!MathUtils.isPowerOfTwo(width2)) {
                width2 = MathUtils.nextPowerOfTwo(width2);
            }
            if (!MathUtils.isPowerOfTwo(height2)) {
                height2 = MathUtils.nextPowerOfTwo(height2);
            }
            if (width2 != pixmap2.getWidth() || height2 != pixmap2.getHeight()) {
                f2278a.i("texture is not pot", new Object[0]);
                Pixmap pixmap3 = new Pixmap(width2, height2, Pixmap.Format.RGBA8888);
                pixmap3.drawPixmap(pixmap2, 0, 0, pixmap2.getWidth(), pixmap2.getHeight(), 0, 0, pixmap2.getWidth(), pixmap2.getHeight());
                pixmap2.dispose();
                pixmap2 = pixmap3;
            }
            Pixmap pixmap4 = pixmap2;
            Threads.i().runOnMainThread(() -> {
                long realTickCount = Game.getRealTickCount();
                Texture texture = new Texture(pixmap4, false);
                webTextureRegion.setTexture(texture);
                webTextureRegion.textureLoaded = true;
                webTextureRegion.setRegion(0, 0, width, height);
                pixmap4.dispose();
                Texture.TextureFilter textureFilter = Texture.TextureFilter.Linear;
                texture.setFilter(textureFilter, textureFilter);
                if (Game.i.debugManager != null) {
                    Game.i.debugManager.registerFrameJob("WebTextureConstruct", Game.getRealTickCount() - realTickCount);
                }
            });
        }
    }

    public WebTextureRegion loadWebTexture(String str) {
        return loadWebTexture(str, false);
    }

    public WebTextureRegion loadWebTexture(String str, boolean z) {
        long j;
        WebTextureRegion webTextureRegion;
        SoftReference<WebTextureRegion> softReference = this.v.get(str, null);
        if (softReference != null && (webTextureRegion = softReference.get()) != null) {
            return webTextureRegion;
        }
        WebTextureRegion webTextureRegion2 = new WebTextureRegion();
        webTextureRegion2.src = str;
        webTextureRegion2.setRegion(getTextureRegion("placeholder"));
        this.v.put(str, new SoftReference<>(webTextureRegion2));
        long j2 = 0;
        String str2 = "cache/webtxt/" + StringFormatter.md5Hash(str);
        if (!z) {
            try {
                if (Gdx.files.local(str2).exists()) {
                    j = Gdx.files.local(str2).file().lastModified();
                } else {
                    j = 0;
                }
                j2 = j;
            } catch (Exception unused) {
            }
        }
        if (Game.getTimestampMillis() - j2 < 172800000) {
            Thread thread = new Thread(() -> {
                try {
                    a(webTextureRegion2, Gdx.files.local(str2).readBytes());
                } catch (Exception e) {
                    f2278a.e("failed to load web texture from local cache - " + str2 + ", " + str + ", re-downloading", e);
                    try {
                        Gdx.files.local(str2).delete();
                        loadWebTexture(str);
                    } catch (Exception unused2) {
                        f2278a.e("failed to delete local cache file - " + str2, e);
                    }
                }
            });
            thread.setDaemon(true);
            thread.start();
            CrashHandler.handleThreadExceptionsForgiving(thread);
        } else {
            Throwable th = new Throwable();
            Game.i.httpManager.get(str).listener((z2, httpResponse, z3, th2) -> {
                if (z2) {
                    byte[] result = httpResponse.getResult();
                    try {
                        a(webTextureRegion2, result);
                        Gdx.files.local(str2).writeBytes(result, false);
                        return;
                    } catch (Exception e) {
                        f2278a.e("failed to create web texture from " + str, e);
                        f2278a.e("Request stacktrace for " + str, th);
                        return;
                    }
                }
                if (th2 != null) {
                    f2278a.e("request error", th2);
                }
                f2278a.e("failed to load web texture " + str, th);
            }).send();
        }
        return webTextureRegion2;
    }

    public void getModel(String str, ObjectConsumer<Model> objectConsumer) {
        Model model = this.r.get(str);
        if (model != null) {
            objectConsumer.accept(model);
        }
        Threads.i().runAsync(() -> {
            f2278a.i("loading model " + str + " on " + Thread.currentThread().getName(), new Object[0]);
            try {
                ModelData loadModelData = new G3dModelLoader(new UBJsonReader()).loadModelData(Gdx.files.internal(str), null);
                Threads.i().runOnMainThread(() -> {
                    long realTickCount = Game.getRealTickCount();
                    Model model2 = new Model(loadModelData);
                    Model putIfAbsent = this.r.putIfAbsent(str, model2);
                    if (putIfAbsent == null) {
                        objectConsumer.accept(model2);
                    } else {
                        objectConsumer.accept(putIfAbsent);
                    }
                    if (Game.i.debugManager != null) {
                        Game.i.debugManager.registerFrameJob("AssetManager-getModel", Game.getRealTickCount() - realTickCount);
                    }
                });
            } catch (Exception e) {
                f2278a.e("failed loading " + str, e);
                objectConsumer.accept(null);
            }
        });
    }

    public Model getSceneModelIfLoaded() {
        return this.g;
    }

    public void getSceneModel(ObjectConsumer<Model> objectConsumer) {
        if (this.g == null) {
            if (!Game.i.settingsManager.isThreeDeeModelsEnabled()) {
                objectConsumer.accept(null);
                return;
            } else {
                getModel("models/scene.g3db", model -> {
                    for (int i = 0; i < model.materials.size; i++) {
                        Material material = model.materials.get(i);
                        if (material.id.equals("normal")) {
                            this.normalMaterial = material;
                        } else if (material.id.equals(BlendingAttribute.Alias)) {
                            this.blendedMaterial = material;
                        }
                        material.remove(ColorAttribute.Emissive);
                    }
                    if (this.normalMaterial != null) {
                        this.normalMaterial.set(new IntAttribute(IntAttribute.CullFace, 0));
                    }
                    this.blendedMaterial.set(new BlendingAttribute(770, 1));
                    this.blendedMaterial.set(new DepthTestAttribute(false));
                    this.blendedMaterial.set(new IntAttribute(IntAttribute.CullFace, 0));
                    this.blendedMaterial.set(new ColorAttribute(ColorAttribute.Specular, 0.0f, 0.0f, 0.0f, 0.0f));
                    this.blendedMaterial.set(new ColorAttribute(ColorAttribute.Emissive, 0.1f, 0.1f, 0.1f, 1.0f));
                    this.blendedMaterial.set(ColorAttribute.createAmbient(0.0f, 1.0f, 0.0f, 1.0f));
                    this.g = model;
                    objectConsumer.accept(this.g);
                });
                return;
            }
        }
        objectConsumer.accept(this.g);
    }

    private int a(CharSequence charSequence) {
        return a(charSequence, 0, charSequence.length());
    }

    private static int a(CharSequence charSequence, int i, int i2) {
        int i3 = 1;
        for (int i4 = i; i4 < i2; i4++) {
            i3 = (i3 * 31) + charSequence.charAt(i4);
        }
        return i3;
    }

    public CharSequence replaceRegionAliasesWithChars(CharSequence charSequence) {
        boolean z;
        boolean z2 = false;
        w.setLength(0);
        boolean z3 = false;
        int i = 0;
        int length = charSequence.length();
        while (i < length) {
            char charAt = charSequence.charAt(i);
            if (charAt == '<') {
                z = true;
            } else {
                if (!z3) {
                    w.append(charAt);
                } else if (charAt != '@') {
                    w.append('<').append(charAt);
                } else {
                    int i2 = i + 1;
                    int i3 = -1;
                    int i4 = i2;
                    while (true) {
                        if (i4 >= length) {
                            break;
                        }
                        if (charSequence.charAt(i4) != '>') {
                            i4++;
                        } else {
                            i3 = i4;
                            break;
                        }
                    }
                    if (i3 != -1) {
                        RegionAliasCharPair regionAliasCharPair = this.t.get(a(charSequence, i2, i3));
                        if (regionAliasCharPair != null) {
                            w.append(regionAliasCharPair.f2281a);
                            i = i3;
                            z2 = true;
                        } else {
                            w.append("<@");
                        }
                    } else {
                        w.append("<@");
                    }
                }
                z = false;
            }
            z3 = z;
            i++;
        }
        return !z2 ? charSequence : w;
    }

    public void addRegionCharsToFont(BitmapFont bitmapFont, int i) {
        for (RegionAliasCharPair regionAliasCharPair : this.s) {
            BitmapFont.Glyph glyph = new BitmapFont.Glyph();
            glyph.id = regionAliasCharPair.f2281a;
            glyph.srcX = 0;
            glyph.srcY = 0;
            glyph.width = regionAliasCharPair.f2282b.getRegionWidth();
            glyph.height = regionAliasCharPair.f2282b.getRegionHeight();
            bitmapFont.getData().setGlyph(regionAliasCharPair.f2281a, glyph);
            bitmapFont.getData().setGlyphRegion(glyph, regionAliasCharPair.f2282b);
            glyph.width = i;
            glyph.height = i;
            glyph.xadvance = i + 2 + 4;
            glyph.yoffset = bitmapFont.getData().getFirstGlyph().yoffset;
            glyph.xoffset = 2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        IntArray intArray = new IntArray();
        intArray.add(21, 24, 30, 36);
        intArray.add(60);
        for (int i = 0; i < intArray.size; i++) {
            int i2 = intArray.items[i];
            ResourcePack.ResourcePackBitmapFont font = getFont(i2);
            float fontScaleMultiplier = (i2 / font.resourcePack.fontResolution) * Game.i.assetManager.getFontScaleMultiplier(i2);
            if (font.getData().scaleX != fontScaleMultiplier) {
                f2278a.i("=== fixing font scale of size " + i2 + ", changing font scale from " + font.getData().scaleX + " to " + fontScaleMultiplier, new Object[0]);
                font.getData().setScale(fontScaleMultiplier);
            }
        }
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void setup() {
        Game.i.settingsManager.addListener(new SettingsManager.SettingsManagerListener.SettingsManagerListenerAdapter() { // from class: com.prineside.tdi2.managers.AssetManager.1
            @Override // com.prineside.tdi2.managers.SettingsManager.SettingsManagerListener.SettingsManagerListenerAdapter, com.prineside.tdi2.managers.SettingsManager.SettingsManagerListener
            public void settingsChanged() {
                AssetManager.this.b();
            }
        });
        b();
    }

    public float getFontScaleMultiplier(int i) {
        if (Game.i.settingsManager.isLargeFontsEnabled()) {
            if (i <= 21) {
                return 1.33f;
            }
            if (i <= 24) {
                return 1.2f;
            }
            return 1.1f;
        }
        return 1.0f;
    }

    public Color getColor(String str) {
        for (int i = this.f2279b.size - 1; i >= 0; i--) {
            Color color = this.f2279b.items[i].getColor(str);
            if (color != null) {
                return color;
            }
        }
        throw new IllegalArgumentException("Color '" + str + "' was not found in any of loaded resource packs");
    }

    public Quad getQuad(String str) {
        for (int i = this.f2279b.size - 1; i >= 0; i--) {
            Quad quad = this.f2279b.items[i].getQuad(str);
            if (quad != null) {
                return quad;
            }
        }
        return Quad.getNoQuad();
    }

    @Null
    public Quad getQuadOrNull(String str) {
        Quad quad = getQuad(str);
        if (quad == Quad.getNoQuad()) {
            return null;
        }
        return quad;
    }

    public ResourcePack.AtlasTextureRegion getTextureRegion(String str) {
        return getTextureRegionSetThrowing(str, true);
    }

    public ResourcePack.AtlasTextureRegion getTextureRegionSetThrowing(String str, boolean z) {
        ResourcePack.AtlasTextureRegion textureRegion = this.f2279b.first().getTextureRegion(str);
        if (textureRegion != null) {
            return textureRegion;
        }
        if (z) {
            throw new IllegalArgumentException("Texture region '" + str + "' was not found in any of loaded resource packs");
        }
        return null;
    }

    public Array<ResourcePack.AtlasTextureRegion> getTextureRegions(String str) {
        Array<ResourcePack.AtlasTextureRegion> textureRegionSet = this.f2279b.first().getTextureRegionSet(str);
        if (textureRegionSet != null) {
            return textureRegionSet;
        }
        throw new IllegalArgumentException("Texture region '" + str + "' was not found in any of loaded resource packs");
    }

    public Label.LabelStyle getLabelStyle(int i) {
        if (this.d.containsKey(i)) {
            return this.d.get(i);
        }
        Label.LabelStyle labelStyle = new Label.LabelStyle(getFont(i), Color.WHITE);
        this.d.put(i, labelStyle);
        return labelStyle;
    }

    public Window.WindowStyle createDefaultWindowStyle() {
        Window.WindowStyle windowStyle = new Window.WindowStyle();
        windowStyle.catchAllTouches = true;
        windowStyle.background = getQuad("ui.window.default.background");
        windowStyle.headerBackground = getQuad("ui.window.default.header");
        windowStyle.scrollPaneStyle = null;
        Label.LabelStyle labelStyle = new Label.LabelStyle(getLabelStyle(21));
        labelStyle.fontColor = new Color(1.0f, 1.0f, 1.0f, 0.78f);
        windowStyle.titleLabelStyle = labelStyle;
        windowStyle.resizeable = false;
        windowStyle.draggable = true;
        windowStyle.resizeHasMinSize = true;
        windowStyle.alwaysOnTop = false;
        windowStyle.inheritWidgetMinSize = true;
        windowStyle.closeButton = getQuad("ui.window.default.closeButton");
        windowStyle.closeButtonColor = PackColor.fromColors(Color.WHITE, MaterialColor.RED.P500, MaterialColor.RED.P700);
        Drawable tint = new TextureRegionDrawable(Game.i.assetManager.getBlankWhiteTextureRegion()).tint(new Color(0.0f, 0.0f, 0.0f, 0.28f));
        tint.setMinWidth(12.0f);
        tint.setMinHeight(12.0f);
        Drawable tint2 = new TextureRegionDrawable(Game.i.assetManager.getBlankWhiteTextureRegion()).tint(new Color(1044266751));
        tint2.setMinWidth(12.0f);
        tint2.setMinHeight(12.0f);
        windowStyle.scrollPaneStyle = new ScrollPane.ScrollPaneStyle(null, tint, tint2, tint, tint2);
        return windowStyle;
    }

    public TextField.TextFieldStyle getTextFieldStyle(int i) {
        return getTextFieldStyleWithFontAndVariant(getFontWithMarkup(i, false), true);
    }

    public TextField.TextFieldStyle getTextFieldStyleWithVariant(int i, boolean z) {
        return getTextFieldStyleWithFontAndVariant(getFontWithMarkup(i, false), z);
    }

    public TextField.TextFieldStyle getTextFieldStyleWithFontAndVariant(BitmapFont bitmapFont, boolean z) {
        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle(bitmapFont, Color.BLACK, new TextureRegionDrawable(Game.i.assetManager.getBlankWhiteTextureRegion()).tint(Color.BLACK), new TextureRegionDrawable(Game.i.assetManager.getBlankWhiteTextureRegion()).tint(MaterialColor.BLUE.P400), new Quad(getQuad(z ? "ui.textField.lowPolyA.background" : "ui.textField.lowPolyB.background"), true));
        textFieldStyle.cursor.setMinWidth(2.0f);
        textFieldStyle.background.setLeftWidth(textFieldStyle.background.getLeftWidth() + 14.0f);
        textFieldStyle.background.setRightWidth(textFieldStyle.background.getRightWidth() + 14.0f);
        textFieldStyle.background.setBottomHeight(textFieldStyle.background.getBottomHeight() + 8.0f);
        textFieldStyle.background.setTopHeight(textFieldStyle.background.getTopHeight() + 8.0f);
        return textFieldStyle;
    }

    public SelectBox.SelectBoxStyle getSelectBoxStyle(BitmapFont bitmapFont, boolean z) {
        List.ListStyle listStyle = new List.ListStyle(bitmapFont, Color.WHITE, new Color(1.0f, 1.0f, 1.0f, 0.78f), new Quad(Game.i.assetManager.getQuad("ui.selectBox.selection"), true));
        listStyle.down = new Quad(Game.i.assetManager.getQuad("ui.selectBox.down"), true);
        listStyle.over = new Quad(Game.i.assetManager.getQuad("ui.selectBox.over"), true);
        listStyle.background = new Quad(Game.i.assetManager.getQuad("ui.selectBox.listBackground"), true);
        return new SelectBox.SelectBoxStyle(bitmapFont, Color.BLACK, new Quad(getQuad(z ? "ui.selectBox.lowPolyA.background" : "ui.selectBox.lowPolyB.background"), true), new ScrollPane.ScrollPaneStyle(new Quad(Game.i.assetManager.getQuad("ui.selectBox.background"), true), new Quad(Game.i.assetManager.getQuad("ui.selectBox.hScroll"), true), new Quad(Game.i.assetManager.getQuad("ui.selectBox.hScrollKnob"), true), new Quad(Game.i.assetManager.getQuad("ui.selectBox.vScroll"), true), new Quad(Game.i.assetManager.getQuad("ui.selectBox.vScrollKnob"), true)), listStyle);
    }

    public Label.LabelStyle getDebugLabelStyle() {
        if (this.e == null) {
            this.e = new Label.LabelStyle(getDebugFont(true), Color.WHITE);
        }
        return this.e;
    }

    public Label.LabelStyle getSmallDebugLabelStyle() {
        if (this.f == null) {
            this.f = new Label.LabelStyle(getSmallDebugFont(), Color.WHITE);
        }
        return this.f;
    }

    public ScrollPane.ScrollPaneStyle getScrollPaneStyle(float f) {
        Drawable tint = new TextureRegionDrawable(Game.i.assetManager.getBlankWhiteTextureRegion()).tint(new Color(0.0f, 0.0f, 0.0f, 0.28f));
        tint.setMinWidth(f);
        Drawable tint2 = new TextureRegionDrawable(Game.i.assetManager.getBlankWhiteTextureRegion()).tint(new Color(1044266751));
        tint2.setMinWidth(f);
        return new ScrollPane.ScrollPaneStyle(null, null, null, tint, tint2);
    }

    public TextureRegionDrawable getDrawable(String str) {
        if (this.c.containsKey(str)) {
            return this.c.get(str);
        }
        TextureRegionDrawable textureRegionDrawable = new TextureRegionDrawable(getTextureRegion(str));
        this.c.put(str, textureRegionDrawable);
        return textureRegionDrawable;
    }

    public Drawable getOverlayBackground() {
        return new TextureRegionDrawable(getBlankWhiteTextureRegion()).tint(new Color(387389354));
    }

    public ResourcePack.AtlasTextureRegion getBlankWhiteTextureRegion() {
        for (int i = this.f2279b.size - 1; i >= 0; i--) {
            ResourcePack.AtlasTextureRegion blankWhiteTextureRegion = this.f2279b.items[i].getBlankWhiteTextureRegion();
            if (blankWhiteTextureRegion != null) {
                return blankWhiteTextureRegion;
            }
        }
        throw new IllegalArgumentException("Blank texture was not found in any of loaded resource packs");
    }

    public BitmapFont getDamageNumbersOverTimeFont() {
        if (this.o == null) {
            this.o = new BitmapFont(Gdx.files.internal("resourcepacks/default/font-damage-over-time.fnt"), getTextureRegion("font-damage-over-time"));
        }
        return this.o;
    }

    public BitmapFont getDamageNumbersEspeciallyEffectiveFont() {
        if (this.p == null) {
            this.p = new BitmapFont(Gdx.files.internal("resourcepacks/default/font-damage-especially-effective.fnt"), getTextureRegion("font-damage-especially-effective"));
        }
        return this.p;
    }

    public BitmapFont getDamageNumbersFont() {
        if (this.n == null) {
            this.n = new BitmapFont(Gdx.files.internal("resourcepacks/default/font-damage-regular.fnt"), getTextureRegion("font-damage-regular"));
        }
        return this.n;
    }

    public BitmapFont getDebugFont(boolean z) {
        if (z) {
            if (this.h == null) {
                this.h = new BitmapFont(Gdx.files.internal("resourcepacks/default/debug.fnt"), getTextureRegion("font-debug"));
                this.h.getData().markupEnabled = true;
                this.h.setFixedWidthGlyphs("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_-=+[]\\/?.,`:;'\"<>| ");
            }
            return this.h;
        }
        if (this.i == null) {
            this.i = new BitmapFont(Gdx.files.internal("resourcepacks/default/debug.fnt"), getTextureRegion("font-debug"));
            this.i.getData().markupEnabled = false;
            this.i.setFixedWidthGlyphs("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_-=+[]\\/?.,`:;'\"<>| ");
        }
        return this.i;
    }

    public BitmapFont getLargeDebugFont(boolean z) {
        if (z) {
            if (this.j == null) {
                this.j = new BitmapFont(Gdx.files.internal("resourcepacks/default/debug.fnt"), getTextureRegion("font-debug"));
                this.j.getData().markupEnabled = true;
                this.j.setFixedWidthGlyphs("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_-=+[]\\/?.,`:;'\"<>| ");
                this.j.getData().setScale(1.15f);
                this.j.setUseIntegerPositions(false);
            }
            return this.j;
        }
        if (this.k == null) {
            this.k = new BitmapFont(Gdx.files.internal("resourcepacks/default/debug.fnt"), getTextureRegion("font-debug"));
            this.k.getData().markupEnabled = false;
            this.k.setFixedWidthGlyphs("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_-=+[]\\/?.,`:;'\"<>| ");
            this.k.getData().setScale(1.15f);
            this.k.setUseIntegerPositions(false);
        }
        return this.k;
    }

    public BitmapFont getSmallDebugFont() {
        if (this.l == null) {
            BitmapFont debugFont = getDebugFont(true);
            this.l = new BitmapFont(Gdx.files.internal("resourcepacks/default/debug.fnt"), getTextureRegion("font-debug"));
            this.l.getData().setScale(0.6667f);
            this.l.getData().markupEnabled = true;
            this.l.setUseIntegerPositions(false);
            for (int i = 0; i < debugFont.getData().glyphs.length; i++) {
                this.l.getData().glyphs[i] = debugFont.getData().glyphs[i];
            }
        }
        return this.l;
    }

    public BitmapFont getSmallDebugFontNoMarkup() {
        if (this.m == null) {
            BitmapFont debugFont = getDebugFont(false);
            this.m = new BitmapFont(Gdx.files.internal("resourcepacks/default/debug.fnt"), getTextureRegion("font-debug"));
            this.m.getData().setScale(0.6667f);
            this.m.getData().markupEnabled = false;
            this.m.setUseIntegerPositions(false);
            for (int i = 0; i < debugFont.getData().glyphs.length; i++) {
                this.m.getData().glyphs[i] = debugFont.getData().glyphs[i];
            }
        }
        return this.m;
    }

    public Texture getBannerTexture() {
        if (this.q == null) {
            this.q = new Texture(Gdx.files.internal("res/get-banner.png"), Pixmap.Format.RGBA8888, false);
            Texture texture = this.q;
            Texture.TextureFilter textureFilter = Texture.TextureFilter.Linear;
            texture.setFilter(textureFilter, textureFilter);
        }
        return this.q;
    }

    public ResourcePack.ResourcePackBitmapFont getFont(int i) {
        return getFontWithMarkup(i, true);
    }

    public ResourcePack.ResourcePackBitmapFont getFontWithMarkup(int i, boolean z) {
        for (int i2 = this.f2279b.size - 1; i2 >= 0; i2--) {
            ResourcePack.ResourcePackBitmapFont fontWithMarkup = this.f2279b.items[i2].getFontWithMarkup(i, z);
            if (fontWithMarkup != null) {
                return fontWithMarkup;
            }
        }
        throw new IllegalArgumentException("Font with size " + i + " was not found in any of loaded resource packs");
    }

    @Null
    public StaticSound getSound(StaticSoundType staticSoundType) {
        for (int i = this.f2279b.size - 1; i >= 0; i--) {
            ResourcePack resourcePack = this.f2279b.items[i];
            if (resourcePack.hasSounds()) {
                return resourcePack.getSound(staticSoundType);
            }
        }
        return null;
    }

    public Module getMenuXmSoundTrack() {
        for (int i = this.f2279b.size - 1; i >= 0; i--) {
            Module menuXmSoundTrack = this.f2279b.items[i].getMenuXmSoundTrack();
            if (menuXmSoundTrack != null) {
                return menuXmSoundTrack;
            }
        }
        return null;
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void test() {
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.badlogic.gdx.utils.Disposable
    public void dispose() {
        Array.ArrayIterator<ResourcePack> it = this.f2279b.iterator();
        while (it.hasNext()) {
            it.next().dispose();
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/AssetManager$WebTextureRegion.class */
    public static class WebTextureRegion extends TextureRegion {
        public String src;
        public boolean textureLoaded;

        public void finalize() {
            super.finalize();
            if (this.textureLoaded) {
                try {
                    AssetManager.f2278a.i("finalizing web texture", new Object[0]);
                    if (Game.i.isInMainThread()) {
                        AssetManager.f2278a.i("disposing texture on main thread", new Object[0]);
                        getTexture().dispose();
                    } else {
                        AssetManager.f2278a.i("pushing runnable to dispose texture on main thread", new Object[0]);
                        Texture texture = getTexture();
                        Threads.i().runOnMainThread(() -> {
                            try {
                                texture.dispose();
                                AssetManager.f2278a.i("disposed web texture", new Object[0]);
                            } catch (Exception e) {
                                AssetManager.f2278a.e("failed to dispose texture in runnable", e);
                            }
                        });
                    }
                } catch (Exception e) {
                    AssetManager.f2278a.e("failed to finalize web texture", e);
                }
            }
        }
    }
}
