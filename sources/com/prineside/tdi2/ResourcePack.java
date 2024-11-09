package com.prineside.tdi2;

import com.a.a.c.j.a;
import com.a.a.c.m;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectMap;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.ibxm.Module;
import com.prineside.tdi2.utils.AssetProvider;
import com.prineside.tdi2.utils.Quad;
import com.prineside.tdi2.utils.logging.TLog;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import org.lwjgl.opengl.CGL;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ResourcePack.class */
public class ResourcePack implements Disposable {
    public static final String RESOURCE_PACKS_DIR = "resourcepacks";

    /* renamed from: b, reason: collision with root package name */
    private boolean f1757b;
    public final String name;
    public int version;
    private boolean c;
    private String d;
    private Array<String> e;
    public float fontResolution;
    private ResourcePackBitmapFont g;
    private boolean h;
    private Module j;
    private boolean k;
    private AtlasTextureRegion o;
    private boolean p;
    private boolean r;

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f1756a = TLog.forClass(ResourcePack.class);
    private static final Comparator<AtlasTextureRegion> t = (atlasTextureRegion, atlasTextureRegion2) -> {
        if (atlasTextureRegion.index == atlasTextureRegion2.index) {
            return 0;
        }
        return atlasTextureRegion.index > atlasTextureRegion2.index ? 1 : -1;
    };
    private final IntMap<ResourcePackBitmapFont> f = new IntMap<>();
    private StaticSound[] i = new StaticSound[StaticSoundType.values.length];
    private final ObjectMap<String, AtlasTextureRegion> l = new ObjectMap<>();
    private final ObjectMap<String, Array<AtlasTextureRegion>> m = new ObjectMap<>();
    private final Array<TextureAtlas> n = new Array<>(TextureAtlas.class);
    private final ObjectMap<String, Color> q = new ObjectMap<>();
    private final ObjectMap<String, Quad> s = new ObjectMap<>();

    /* JADX WARN: Code restructure failed: missing block: B:91:0x0503, code lost:            if (r9.l.get(r0.name).index != 0) goto L83;     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:104:0x05a5  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x05a8 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:120:0x06a2 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:123:? A[LOOP:0: B:18:0x010c->B:123:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x052a  */
    /* JADX WARN: Type inference failed for: r40v1, types: [java.lang.Throwable, java.lang.Exception] */
    /* JADX WARN: Type inference failed for: r5v14, types: [boolean] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public ResourcePack(java.lang.String r10, boolean r11) {
        /*
            Method dump skipped, instructions count: 3185
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.prineside.tdi2.ResourcePack.<init>(java.lang.String, boolean):void");
    }

    public static void preloadQuadRegionSetRecursive(m mVar, String str, String str2, ObjectMap<String, Quad> objectMap, AssetProvider<TextureRegion> assetProvider) {
        if (mVar.c()) {
            Iterator<Map.Entry<String, m>> n = mVar.n();
            while (n.hasNext()) {
                Map.Entry<String, m> next = n.next();
                preloadQuadRegionSetRecursive(next.getValue(), str == null ? next.getKey() : str + "." + next.getKey(), str2, objectMap, assetProvider);
            }
            return;
        }
        if (str != null && objectMap.containsKey(str)) {
            f1756a.i("skipped quad '" + str + "' from " + str2, new Object[0]);
            return;
        }
        try {
            if (str == null) {
                throw new IllegalArgumentException("No prefix for " + mVar);
            }
            objectMap.put(str, Quad.fromJson((a) mVar, assetProvider));
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to load quad '" + str + "' from pack '" + str2 + "'", e);
        }
    }

    public AtlasTextureRegion getBlankWhiteTextureRegion() {
        return this.o;
    }

    public AtlasTextureRegion getTextureRegion(String str) {
        if (!this.k) {
            return null;
        }
        AtlasTextureRegion atlasTextureRegion = this.l.get(str, null);
        if (atlasTextureRegion == null && str.contains("@")) {
            String[] split = str.split("@");
            AtlasTextureRegion atlasTextureRegion2 = this.l.get(split[0], null);
            if (atlasTextureRegion2 != null && split.length >= 2) {
                AtlasTextureRegion atlasTextureRegion3 = new AtlasTextureRegion(atlasTextureRegion2, atlasTextureRegion2.getAtlas());
                String str2 = split[1];
                boolean z = -1;
                switch (str2.hashCode()) {
                    case -1271816424:
                        if (str2.equals("flip-x")) {
                            z = false;
                            break;
                        }
                        break;
                    case -1271816423:
                        if (str2.equals("flip-y")) {
                            z = true;
                            break;
                        }
                        break;
                    case -771603359:
                        if (str2.equals("flip-xy")) {
                            z = 2;
                            break;
                        }
                        break;
                    case -79959177:
                        if (str2.equals("rotate-180")) {
                            z = 3;
                            break;
                        }
                        break;
                }
                switch (z) {
                    case false:
                        atlasTextureRegion3.flip(true, false);
                        break;
                    case true:
                        atlasTextureRegion3.flip(false, true);
                        break;
                    case true:
                    case true:
                        atlasTextureRegion3.flip(true, true);
                        break;
                    default:
                        f1756a.e("region modifier \"" + split[1] + "\" is invalid", new Object[0]);
                        return atlasTextureRegion2;
                }
                f1756a.i("stored modified region: " + str, new Object[0]);
                this.l.put(str, atlasTextureRegion3);
                return atlasTextureRegion3;
            }
            return null;
        }
        return atlasTextureRegion;
    }

    public Array<AtlasTextureRegion> getTextureRegionSet(String str) {
        if (this.k) {
            return this.m.get(str, null);
        }
        return null;
    }

    public ObjectMap<String, AtlasTextureRegion> getTextureRegions() {
        return this.l;
    }

    public ObjectMap<String, Array<AtlasTextureRegion>> getTextureRegionSets() {
        return this.m;
    }

    public Array<TextureAtlas> getLoadedAtlases() {
        return this.n;
    }

    public Color getColor(String str) {
        if (this.p) {
            return this.q.get(str, null);
        }
        return null;
    }

    public Quad getQuad(String str) {
        if (this.r) {
            return this.s.get(str, null);
        }
        return null;
    }

    public ResourcePackBitmapFont getFont(int i) {
        return getFontWithMarkup(i, true);
    }

    public ResourcePackBitmapFont getFontWithMarkup(int i, boolean z) {
        if (!this.c) {
            return null;
        }
        int i2 = i;
        if (z) {
            i2 += CGL.kCGLBadAttribute;
        }
        if (this.f.containsKey(i2)) {
            return this.f.get(i2);
        }
        Array array = new Array();
        Array.ArrayIterator<String> it = this.e.iterator();
        while (it.hasNext()) {
            array.add(getTextureRegion(it.next()));
        }
        ResourcePackBitmapFont resourcePackBitmapFont = new ResourcePackBitmapFont(this.f1757b ? Gdx.files.internal(this.d) : Gdx.files.local(this.d), array, false);
        float fontScaleMultiplier = (i / this.fontResolution) * Game.i.assetManager.getFontScaleMultiplier(i);
        f1756a.i("=== creating new font, size: " + i + ", resolution: " + this.fontResolution + ", multiplier: " + Game.i.assetManager.getFontScaleMultiplier(i) + ", scale: " + fontScaleMultiplier, new Object[0]);
        resourcePackBitmapFont.getData().setScale(fontScaleMultiplier);
        resourcePackBitmapFont.getData().markupEnabled = z;
        resourcePackBitmapFont.setFixedWidthGlyphs("0123456789");
        Game.i.assetManager.addRegionCharsToFont(resourcePackBitmapFont, (int) this.fontResolution);
        if (this.g == null) {
            this.g = resourcePackBitmapFont;
        } else {
            for (int i3 = 0; i3 < resourcePackBitmapFont.getData().glyphs.length; i3++) {
                resourcePackBitmapFont.getData().glyphs[i3] = this.g.getData().glyphs[i3];
            }
        }
        this.f.put(i2, resourcePackBitmapFont);
        resourcePackBitmapFont.resourcePack = this;
        return resourcePackBitmapFont;
    }

    public boolean hasSounds() {
        return this.h;
    }

    @Null
    public StaticSound getSound(StaticSoundType staticSoundType) {
        if (this.h) {
            return this.i[staticSoundType.ordinal()];
        }
        return null;
    }

    public Module getMenuXmSoundTrack() {
        return this.j;
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        Array.ArrayIterator<TextureAtlas> it = this.n.iterator();
        while (it.hasNext()) {
            it.next().dispose();
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ResourcePack$AtlasTextureRegion.class */
    public static class AtlasTextureRegion extends TextureAtlas.AtlasRegion {

        /* renamed from: a, reason: collision with root package name */
        private final TextureAtlas f1758a;

        AtlasTextureRegion(TextureAtlas.AtlasRegion atlasRegion, TextureAtlas textureAtlas) {
            super(atlasRegion);
            this.f1758a = textureAtlas;
        }

        public TextureAtlas getAtlas() {
            return this.f1758a;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ResourcePack$ResourcePackBitmapFont.class */
    public static class ResourcePackBitmapFont extends BitmapFont {
        public ResourcePack resourcePack;

        public ResourcePackBitmapFont(FileHandle fileHandle, Array<TextureRegion> array, boolean z) {
            super(new BitmapFont.BitmapFontData(fileHandle, z), array, false);
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ResourcePack$ResourcePackLoadingException.class */
    public static class ResourcePackLoadingException extends Exception {
        ResourcePackLoadingException(String str) {
            super(str);
        }

        ResourcePackLoadingException(String str, Throwable th) {
            super(str, th);
        }
    }
}
