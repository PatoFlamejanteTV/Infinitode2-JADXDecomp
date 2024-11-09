package com.badlogic.gdx.graphics.g3d.environment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.utils.TextureDescriptor;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/environment/DirectionalShadowLight.class */
public class DirectionalShadowLight extends DirectionalLight implements ShadowMap, Disposable {
    protected FrameBuffer fbo;
    protected Camera cam;
    protected float halfDepth;
    protected float halfHeight;
    protected final Vector3 tmpV = new Vector3();
    protected final TextureDescriptor textureDesc;

    public DirectionalShadowLight(int i, int i2, float f, float f2, float f3, float f4) {
        this.fbo = new FrameBuffer(Pixmap.Format.RGBA8888, i, i2, true);
        this.cam = new OrthographicCamera(f, f2);
        this.cam.near = f3;
        this.cam.far = f4;
        this.halfHeight = f2 * 0.5f;
        this.halfDepth = f3 + (0.5f * (f4 - f3));
        this.textureDesc = new TextureDescriptor();
        TextureDescriptor textureDescriptor = this.textureDesc;
        TextureDescriptor textureDescriptor2 = this.textureDesc;
        Texture.TextureFilter textureFilter = Texture.TextureFilter.Nearest;
        textureDescriptor2.magFilter = textureFilter;
        textureDescriptor.minFilter = textureFilter;
        TextureDescriptor textureDescriptor3 = this.textureDesc;
        TextureDescriptor textureDescriptor4 = this.textureDesc;
        Texture.TextureWrap textureWrap = Texture.TextureWrap.ClampToEdge;
        textureDescriptor4.vWrap = textureWrap;
        textureDescriptor3.uWrap = textureWrap;
    }

    public void update(Camera camera) {
        update(this.tmpV.set(camera.direction).scl(this.halfHeight), camera.direction);
    }

    public void update(Vector3 vector3, Vector3 vector32) {
        this.cam.position.set(this.direction).scl(-this.halfDepth).add(vector3);
        this.cam.direction.set(this.direction).nor();
        this.cam.normalizeUp();
        this.cam.update();
    }

    public void begin(Camera camera) {
        update(camera);
        begin();
    }

    public void begin(Vector3 vector3, Vector3 vector32) {
        update(vector3, vector32);
        begin();
    }

    public void begin() {
        int width = this.fbo.getWidth();
        int height = this.fbo.getHeight();
        this.fbo.begin();
        Gdx.gl.glViewport(0, 0, width, height);
        Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        Gdx.gl.glClear(16640);
        Gdx.gl.glEnable(3089);
        Gdx.gl.glScissor(1, 1, width - 2, height - 2);
    }

    public void end() {
        Gdx.gl.glDisable(3089);
        this.fbo.end();
    }

    public FrameBuffer getFrameBuffer() {
        return this.fbo;
    }

    public Camera getCamera() {
        return this.cam;
    }

    @Override // com.badlogic.gdx.graphics.g3d.environment.ShadowMap
    public Matrix4 getProjViewTrans() {
        return this.cam.combined;
    }

    @Override // com.badlogic.gdx.graphics.g3d.environment.ShadowMap
    public TextureDescriptor getDepthMap() {
        this.textureDesc.texture = this.fbo.getColorBufferTexture();
        return this.textureDesc;
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        if (this.fbo != null) {
            this.fbo.dispose();
        }
        this.fbo = null;
    }
}
