package com.badlogic.gdx.graphics;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.glutils.FileTextureData;
import com.badlogic.gdx.graphics.glutils.PixmapTextureData;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/Texture.class */
public class Texture extends GLTexture {
    private static AssetManager assetManager;
    static final Map<Application, Array<Texture>> managedTextures = new HashMap();
    TextureData data;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/Texture$TextureFilter.class */
    public enum TextureFilter {
        Nearest(9728),
        Linear(9729),
        MipMap(9987),
        MipMapNearestNearest(9984),
        MipMapLinearNearest(9985),
        MipMapNearestLinear(9986),
        MipMapLinearLinear(9987);

        final int glEnum;

        TextureFilter(int i) {
            this.glEnum = i;
        }

        public final boolean isMipMap() {
            return (this.glEnum == 9728 || this.glEnum == 9729) ? false : true;
        }

        public final int getGLEnum() {
            return this.glEnum;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/Texture$TextureWrap.class */
    public enum TextureWrap {
        MirroredRepeat(33648),
        ClampToEdge(33071),
        Repeat(10497);

        final int glEnum;

        TextureWrap(int i) {
            this.glEnum = i;
        }

        public final int getGLEnum() {
            return this.glEnum;
        }
    }

    protected Texture() {
        super(0, 0);
    }

    public Texture(String str) {
        this(Gdx.files.internal(str));
    }

    public Texture(FileHandle fileHandle) {
        this(fileHandle, (Pixmap.Format) null, false);
    }

    public Texture(FileHandle fileHandle, boolean z) {
        this(fileHandle, (Pixmap.Format) null, z);
    }

    public Texture(FileHandle fileHandle, Pixmap.Format format, boolean z) {
        this(TextureData.Factory.loadFromFile(fileHandle, format, z));
    }

    public Texture(Pixmap pixmap) {
        this(new PixmapTextureData(pixmap, null, false, false));
    }

    public Texture(Pixmap pixmap, boolean z) {
        this(new PixmapTextureData(pixmap, null, z, false));
    }

    public Texture(Pixmap pixmap, Pixmap.Format format, boolean z) {
        this(new PixmapTextureData(pixmap, format, z, false));
    }

    public Texture(int i, int i2, Pixmap.Format format) {
        this(new PixmapTextureData(new Pixmap(i, i2, format), null, false, true));
    }

    public Texture(TextureData textureData) {
        this(3553, Gdx.gl.glGenTexture(), textureData);
    }

    protected Texture(int i, int i2, TextureData textureData) {
        super(i, i2);
        load(textureData);
        if (textureData.isManaged()) {
            addManagedTexture(Gdx.app, this);
        }
    }

    public void load(TextureData textureData) {
        if (this.data != null && textureData.isManaged() != this.data.isManaged()) {
            throw new GdxRuntimeException("New data must have the same managed status as the old data");
        }
        this.data = textureData;
        if (!textureData.isPrepared()) {
            textureData.prepare();
        }
        bind();
        uploadImageData(3553, textureData);
        unsafeSetFilter(this.minFilter, this.magFilter, true);
        unsafeSetWrap(this.uWrap, this.vWrap, true);
        unsafeSetAnisotropicFilter(this.anisotropicFilterLevel, true);
        Gdx.gl.glBindTexture(this.glTarget, 0);
    }

    @Override // com.badlogic.gdx.graphics.GLTexture
    protected void reload() {
        if (!isManaged()) {
            throw new GdxRuntimeException("Tried to reload unmanaged Texture");
        }
        this.glHandle = Gdx.gl.glGenTexture();
        load(this.data);
    }

    public void draw(Pixmap pixmap, int i, int i2) {
        if (this.data.isManaged()) {
            throw new GdxRuntimeException("can't draw to a managed texture");
        }
        bind();
        Gdx.gl.glTexSubImage2D(this.glTarget, 0, i, i2, pixmap.getWidth(), pixmap.getHeight(), pixmap.getGLFormat(), pixmap.getGLType(), pixmap.getPixels());
    }

    @Override // com.badlogic.gdx.graphics.GLTexture
    public int getWidth() {
        return this.data.getWidth();
    }

    @Override // com.badlogic.gdx.graphics.GLTexture
    public int getHeight() {
        return this.data.getHeight();
    }

    @Override // com.badlogic.gdx.graphics.GLTexture
    public int getDepth() {
        return 0;
    }

    public TextureData getTextureData() {
        return this.data;
    }

    @Override // com.badlogic.gdx.graphics.GLTexture
    public boolean isManaged() {
        return this.data.isManaged();
    }

    @Override // com.badlogic.gdx.graphics.GLTexture, com.badlogic.gdx.utils.Disposable
    public void dispose() {
        if (this.glHandle == 0) {
            return;
        }
        delete();
        if (!this.data.isManaged() || managedTextures.get(Gdx.app) == null) {
            return;
        }
        managedTextures.get(Gdx.app).removeValue(this, true);
    }

    public String toString() {
        return this.data instanceof FileTextureData ? this.data.toString() : super.toString();
    }

    private static void addManagedTexture(Application application, Texture texture) {
        Array<Texture> array = managedTextures.get(application);
        Array<Texture> array2 = array;
        if (array == null) {
            array2 = new Array<>();
        }
        array2.add(texture);
        managedTextures.put(application, array2);
    }

    public static void clearAllTextures(Application application) {
        managedTextures.remove(application);
    }

    public static void invalidateAllTextures(Application application) {
        Array<Texture> array = managedTextures.get(application);
        if (array == null) {
            return;
        }
        if (assetManager == null) {
            for (int i = 0; i < array.size; i++) {
                array.get(i).reload();
            }
            return;
        }
        assetManager.finishLoading();
        Array<? extends Texture> array2 = new Array<>(array);
        Array.ArrayIterator<? extends Texture> it = array2.iterator();
        while (it.hasNext()) {
            Texture next = it.next();
            String assetFileName = assetManager.getAssetFileName(next);
            if (assetFileName != null) {
                final int referenceCount = assetManager.getReferenceCount(assetFileName);
                assetManager.setReferenceCount(assetFileName, 0);
                next.glHandle = 0;
                TextureLoader.TextureParameter textureParameter = new TextureLoader.TextureParameter();
                textureParameter.textureData = next.getTextureData();
                textureParameter.minFilter = next.getMinFilter();
                textureParameter.magFilter = next.getMagFilter();
                textureParameter.wrapU = next.getUWrap();
                textureParameter.wrapV = next.getVWrap();
                textureParameter.genMipMaps = next.data.useMipMaps();
                textureParameter.texture = next;
                textureParameter.loadedCallback = new AssetLoaderParameters.LoadedCallback() { // from class: com.badlogic.gdx.graphics.Texture.1
                    @Override // com.badlogic.gdx.assets.AssetLoaderParameters.LoadedCallback
                    public void finishedLoading(AssetManager assetManager2, String str, Class cls) {
                        assetManager2.setReferenceCount(str, referenceCount);
                    }
                };
                assetManager.unload(assetFileName);
                next.glHandle = Gdx.gl.glGenTexture();
                assetManager.load(assetFileName, Texture.class, textureParameter);
            } else {
                next.reload();
            }
        }
        array.clear();
        array.addAll(array2);
    }

    public static void setAssetManager(AssetManager assetManager2) {
        assetManager = assetManager2;
    }

    public static String getManagedStatus() {
        StringBuilder sb = new StringBuilder();
        sb.append("Managed textures/app: { ");
        Iterator<Application> it = managedTextures.keySet().iterator();
        while (it.hasNext()) {
            sb.append(managedTextures.get(it.next()).size);
            sb.append(SequenceUtils.SPACE);
        }
        sb.append("}");
        return sb.toString();
    }

    public static int getNumManagedTextures() {
        return managedTextures.get(Gdx.app).size;
    }
}
