package com.prineside.tdi2.ui.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelCache;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.GLFrameBuffer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.utils.logging.TLog;
import java.nio.Buffer;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/ModelView.class */
public class ModelView extends Actor implements Disposable {
    public PerspectiveCamera camera;
    public ModelInstance model;
    public Environment environment;
    private FrameBuffer k;
    private FrameBuffer l;
    public ModelCache modelCache;
    private TextureRegion m;
    private Transformer n;
    public boolean modelCacheUpdateRequired;
    private boolean o;
    private float p;
    private boolean q;
    private String r;
    public int width;
    public int height;
    private boolean s;
    private static final TLog j = TLog.forClass(ModelView.class);
    public static final Transformer rotateModelAround = new Transformer() { // from class: com.prineside.tdi2.ui.actors.ModelView.1
        @Override // com.prineside.tdi2.ui.actors.ModelView.Transformer
        public void transform(ModelView modelView, float f, float f2) {
            if (modelView.model == null) {
                return;
            }
            modelView.model.transform.rotate(Vector3.Y, f * 30.0f);
            modelView.model.calculateTransforms();
            modelView.requireModelCacheUpdate();
            modelView.requireRedraw();
        }
    };

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/ModelView$Transformer.class */
    public static abstract class Transformer {
        public abstract void transform(ModelView modelView, float f, float f2);
    }

    public ModelView(int i, int i2, Transformer transformer, Environment environment, boolean z) {
        if (environment == null) {
            Environment environment2 = new Environment();
            environment = environment2;
            environment2.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.3f, 0.3f, 0.3f, 1.0f));
            environment.add(new DirectionalLight().set(0.75f, 0.75f, 0.75f, -0.5f, -1.0f, -0.2f));
        }
        this.environment = environment;
        this.n = transformer;
        this.s = z;
        this.modelCache = new ModelCache();
        setSize(i, i2);
        requireModelCacheUpdate();
        requireRedraw();
    }

    public void setSize(int i, int i2) {
        if (i < 32 || i2 < 24) {
            i = 32;
            i2 = 32;
        }
        if (this.width != i || this.height != i2) {
            if (this.l != null) {
                this.l.dispose();
            }
            if (this.k != null) {
                this.k.dispose();
            }
            this.height = i2;
            this.width = i;
            if (!this.s) {
                if (Gdx.graphics.isGL32Available() && Game.i.renderingManager.getMSAASampleCount() > 1) {
                    try {
                        this.k = new GLFrameBuffer.FrameBufferBuilder(i, i2, Game.i.renderingManager.getMSAASampleCount()).addColorRenderBuffer(32856).addBasicDepthRenderBuffer().build();
                    } catch (Exception e) {
                        j.w("Failed to create MSAA buffer, fall back to the regular buffer", e);
                        this.k = null;
                    }
                }
                this.l = new FrameBuffer(Pixmap.Format.RGBA8888, i, i2, true);
                this.m = new TextureRegion(this.l.getColorBufferTexture());
                this.m.flip(false, true);
                float f = 1.0f / i2;
                this.m.setU(this.m.getU() + f);
                this.m.setV(this.m.getV() - f);
                this.m.setU2(this.m.getU2() - f);
                this.m.setV2(this.m.getV2() + f);
                Texture texture = this.m.getTexture();
                Texture.TextureFilter textureFilter = Texture.TextureFilter.Linear;
                texture.setFilter(textureFilter, textureFilter);
            }
            this.camera = new PerspectiveCamera(67.0f, i, i2);
            this.camera.position.set(1.0f, 0.25f, 0.0f);
            this.camera.lookAt(0.0f, 0.0f, 0.0f);
            this.camera.near = 0.01f;
            this.camera.far = 300.0f;
            this.camera.update();
        }
    }

    public void requireRedraw() {
        this.o = true;
    }

    public void requireModelCacheUpdate() {
        this.modelCacheUpdateRequired = true;
    }

    public void setTransformer(Transformer transformer) {
        this.n = transformer;
    }

    public void setEnvironment(Environment environment) {
        if (environment == null) {
            throw new IllegalArgumentException("Environment can't be null");
        }
        this.environment = environment;
        requireRedraw();
    }

    public void setModel(ModelInstance modelInstance, float f) {
        this.model = modelInstance;
        modelInstance.transform.setToTranslationAndScaling(new Vector3(0.0f, 0.0f, 0.0f), new Vector3(f, f, f));
        requireModelCacheUpdate();
        requireRedraw();
    }

    public void setModelPart(Model model, String str, Material material, float f) {
        ModelInstance modelInstance = new ModelInstance(model, str, true, true, true);
        Node node = modelInstance.getNode(str);
        node.translation.set(0.0f, 0.0f, 0.0f);
        node.rotation.setFromAxis(Vector3.X, -90.0f);
        if (material != null) {
            for (int i = 0; i < node.parts.size; i++) {
                node.parts.get(i).material = material;
            }
        }
        modelInstance.calculateTransforms();
        setModel(modelInstance, f);
    }

    public void saveScreenshot(String str) {
        this.q = true;
        this.r = str;
    }

    public void updateModelCacheIfRequired() {
        if (!this.modelCacheUpdateRequired || this.model == null) {
            return;
        }
        try {
            this.modelCache.begin();
            this.modelCache.add(this.model);
            this.modelCache.end();
        } catch (Exception e) {
            j.e("failed to update model cache", e);
        }
        this.modelCacheUpdateRequired = false;
    }

    @Override // com.prineside.tdi2.scene2d.Actor
    public void act(float f) {
        super.act(f);
        if (isVisible()) {
            this.p += f;
            if (this.n != null) {
                this.n.transform(this, f, this.p);
            }
            if (this.s) {
                updateModelCacheIfRequired();
                this.o = false;
                return;
            }
            if ((this.o || this.q) && this.model != null) {
                Game.i.renderingManager.startFBO(this.k == null ? this.l : this.k, "ModelView");
                Gdx.gl.glClearColor(Config.BACKGROUND_COLOR.r, Config.BACKGROUND_COLOR.g, Config.BACKGROUND_COLOR.f888b, 0.0f);
                Gdx.gl.glClear(16640);
                Game.i.renderingManager.modelBatch.begin(this.camera);
                updateModelCacheIfRequired();
                Game.i.renderingManager.modelBatch.render(this.modelCache, this.environment);
                Game.i.renderingManager.modelBatch.end();
                if (this.k != null) {
                    this.k.transfer(this.l);
                }
                if (this.q) {
                    byte[] frameBufferPixels = ScreenUtils.getFrameBufferPixels(0, 0, this.l.getWidth(), this.l.getHeight(), true);
                    Pixmap pixmap = new Pixmap(this.l.getWidth(), this.l.getHeight(), Pixmap.Format.RGBA8888);
                    BufferUtils.copy(frameBufferPixels, 0, (Buffer) pixmap.getPixels(), frameBufferPixels.length);
                    PixmapIO.writePNG(Gdx.files.local(this.r), pixmap);
                    pixmap.dispose();
                    this.q = false;
                }
                Game.i.renderingManager.endFBO("ModelView");
                this.o = false;
            }
        }
    }

    @Override // com.prineside.tdi2.scene2d.Actor
    public void draw(Batch batch, float f) {
        super.draw(batch, f);
        if (this.model == null) {
            return;
        }
        if (this.s) {
            batch.end();
            Game.i.renderingManager.modelBatch.begin(this.camera);
            Game.i.renderingManager.modelBatch.render(this.modelCache, this.environment);
            Game.i.renderingManager.modelBatch.end();
            batch.begin();
            return;
        }
        Color color = getColor();
        batch.setColor(color.r, color.g, color.f888b, color.f889a * f);
        batch.draw(this.m, getX(), getY(), getWidth(), getHeight());
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        if (this.l != null) {
            this.l.dispose();
        }
        if (this.k != null) {
            this.k.dispose();
        }
        this.modelCache.dispose();
        this.l = null;
        this.k = null;
    }
}
