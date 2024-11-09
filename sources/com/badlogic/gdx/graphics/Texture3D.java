package com.badlogic.gdx.graphics;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.CustomTexture3DData;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/Texture3D.class */
public class Texture3D extends GLTexture {
    static final Map<Application, Array<Texture3D>> managedTexture3Ds = new HashMap();
    private Texture3DData data;
    protected Texture.TextureWrap rWrap;

    public Texture3D(int i, int i2, int i3, int i4, int i5, int i6) {
        this(new CustomTexture3DData(i, i2, i3, 0, i4, i5, i6));
    }

    public Texture3D(Texture3DData texture3DData) {
        super(32879, Gdx.gl.glGenTexture());
        this.rWrap = Texture.TextureWrap.ClampToEdge;
        if (Gdx.gl30 == null) {
            throw new GdxRuntimeException("Texture3D requires a device running with GLES 3.0 compatibilty");
        }
        load(texture3DData);
        if (texture3DData.isManaged()) {
            addManagedTexture(Gdx.app, this);
        }
    }

    private void load(Texture3DData texture3DData) {
        if (this.data != null && texture3DData.isManaged() != this.data.isManaged()) {
            throw new GdxRuntimeException("New data must have the same managed status as the old data");
        }
        this.data = texture3DData;
        bind();
        if (!texture3DData.isPrepared()) {
            texture3DData.prepare();
        }
        texture3DData.consume3DData();
        setFilter(this.minFilter, this.magFilter);
        setWrap(this.uWrap, this.vWrap, this.rWrap);
        Gdx.gl.glBindTexture(this.glTarget, 0);
    }

    public Texture3DData getData() {
        return this.data;
    }

    public void upload() {
        bind();
        this.data.consume3DData();
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
        return this.data.getDepth();
    }

    @Override // com.badlogic.gdx.graphics.GLTexture
    public boolean isManaged() {
        return this.data.isManaged();
    }

    @Override // com.badlogic.gdx.graphics.GLTexture
    protected void reload() {
        if (!isManaged()) {
            throw new GdxRuntimeException("Tried to reload an unmanaged TextureArray");
        }
        this.glHandle = Gdx.gl.glGenTexture();
        load(this.data);
    }

    private static void addManagedTexture(Application application, Texture3D texture3D) {
        Array<Texture3D> array = managedTexture3Ds.get(application);
        Array<Texture3D> array2 = array;
        if (array == null) {
            array2 = new Array<>();
        }
        array2.add(texture3D);
        managedTexture3Ds.put(application, array2);
    }

    public static void clearAllTextureArrays(Application application) {
        managedTexture3Ds.remove(application);
    }

    public static void invalidateAllTextureArrays(Application application) {
        Array<Texture3D> array = managedTexture3Ds.get(application);
        if (array == null) {
            return;
        }
        for (int i = 0; i < array.size; i++) {
            array.get(i).reload();
        }
    }

    public static String getManagedStatus() {
        StringBuilder sb = new StringBuilder();
        sb.append("Managed TextureArrays/app: { ");
        Iterator<Application> it = managedTexture3Ds.keySet().iterator();
        while (it.hasNext()) {
            sb.append(managedTexture3Ds.get(it.next()).size);
            sb.append(SequenceUtils.SPACE);
        }
        sb.append("}");
        return sb.toString();
    }

    public static int getNumManagedTextures3D() {
        return managedTexture3Ds.get(Gdx.app).size;
    }

    public void setWrap(Texture.TextureWrap textureWrap, Texture.TextureWrap textureWrap2, Texture.TextureWrap textureWrap3) {
        this.rWrap = textureWrap3;
        super.setWrap(textureWrap, textureWrap2);
        Gdx.gl.glTexParameteri(this.glTarget, 32882, textureWrap3.getGLEnum());
    }

    public void unsafeSetWrap(Texture.TextureWrap textureWrap, Texture.TextureWrap textureWrap2, Texture.TextureWrap textureWrap3, boolean z) {
        unsafeSetWrap(textureWrap, textureWrap2, z);
        if (textureWrap3 != null) {
            if (z || this.rWrap != textureWrap3) {
                Gdx.gl.glTexParameteri(this.glTarget, 32882, textureWrap.getGLEnum());
                this.rWrap = textureWrap3;
            }
        }
    }

    public void unsafeSetWrap(Texture.TextureWrap textureWrap, Texture.TextureWrap textureWrap2, Texture.TextureWrap textureWrap3) {
        unsafeSetWrap(textureWrap, textureWrap2, textureWrap3, false);
    }
}
