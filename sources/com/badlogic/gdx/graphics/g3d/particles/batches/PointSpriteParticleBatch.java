package com.badlogic.gdx.graphics.g3d.particles.batches;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.DepthTestAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.particles.ParallelArray;
import com.badlogic.gdx.graphics.g3d.particles.ParticleShader;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData;
import com.badlogic.gdx.graphics.g3d.particles.renderers.PointSpriteControllerRenderData;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/batches/PointSpriteParticleBatch.class */
public class PointSpriteParticleBatch extends BufferedParticleBatch<PointSpriteControllerRenderData> {
    private static boolean pointSpritesEnabled = false;
    protected static final Vector3 TMP_V1 = new Vector3();
    protected static final int sizeAndRotationUsage = 512;
    protected static final VertexAttributes CPU_ATTRIBUTES;
    protected static final int CPU_VERTEX_SIZE;
    protected static final int CPU_POSITION_OFFSET;
    protected static final int CPU_COLOR_OFFSET;
    protected static final int CPU_REGION_OFFSET;
    protected static final int CPU_SIZE_AND_ROTATION_OFFSET;
    private float[] vertices;
    Renderable renderable;
    protected BlendingAttribute blendingAttribute;
    protected DepthTestAttribute depthTestAttribute;

    static {
        VertexAttributes vertexAttributes = new VertexAttributes(new VertexAttribute(1, 3, ShaderProgram.POSITION_ATTRIBUTE), new VertexAttribute(2, 4, ShaderProgram.COLOR_ATTRIBUTE), new VertexAttribute(16, 4, "a_region"), new VertexAttribute(512, 3, "a_sizeAndRotation"));
        CPU_ATTRIBUTES = vertexAttributes;
        CPU_VERTEX_SIZE = (short) (vertexAttributes.vertexSize / 4);
        CPU_POSITION_OFFSET = (short) (CPU_ATTRIBUTES.findByUsage(1).offset / 4);
        CPU_COLOR_OFFSET = (short) (CPU_ATTRIBUTES.findByUsage(2).offset / 4);
        CPU_REGION_OFFSET = (short) (CPU_ATTRIBUTES.findByUsage(16).offset / 4);
        CPU_SIZE_AND_ROTATION_OFFSET = (short) (CPU_ATTRIBUTES.findByUsage(512).offset / 4);
    }

    private static void enablePointSprites() {
        Gdx.gl.glEnable(34370);
        if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
            Gdx.gl.glEnable(34913);
        }
        pointSpritesEnabled = true;
    }

    public PointSpriteParticleBatch() {
        this(1000);
    }

    public PointSpriteParticleBatch(int i) {
        this(i, new ParticleShader.Config(ParticleShader.ParticleType.Point));
    }

    public PointSpriteParticleBatch(int i, ParticleShader.Config config) {
        this(i, config, null, null);
    }

    public PointSpriteParticleBatch(int i, ParticleShader.Config config, BlendingAttribute blendingAttribute, DepthTestAttribute depthTestAttribute) {
        super(PointSpriteControllerRenderData.class);
        if (!pointSpritesEnabled) {
            enablePointSprites();
        }
        this.blendingAttribute = blendingAttribute;
        this.depthTestAttribute = depthTestAttribute;
        if (this.blendingAttribute == null) {
            this.blendingAttribute = new BlendingAttribute(1, 771, 1.0f);
        }
        if (this.depthTestAttribute == null) {
            this.depthTestAttribute = new DepthTestAttribute(515, false);
        }
        allocRenderable();
        ensureCapacity(i);
        this.renderable.shader = new ParticleShader(this.renderable, config);
        this.renderable.shader.init();
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.batches.BufferedParticleBatch
    protected void allocParticlesData(int i) {
        this.vertices = new float[i * CPU_VERTEX_SIZE];
        if (this.renderable.meshPart.mesh != null) {
            this.renderable.meshPart.mesh.dispose();
        }
        this.renderable.meshPart.mesh = new Mesh(false, i, 0, CPU_ATTRIBUTES);
    }

    protected void allocRenderable() {
        this.renderable = new Renderable();
        this.renderable.meshPart.primitiveType = 0;
        this.renderable.meshPart.offset = 0;
        this.renderable.material = new Material(this.blendingAttribute, this.depthTestAttribute, TextureAttribute.createDiffuse((Texture) null));
    }

    public void setTexture(Texture texture) {
        ((TextureAttribute) this.renderable.material.get(TextureAttribute.Diffuse)).textureDescription.texture = texture;
    }

    public Texture getTexture() {
        return ((TextureAttribute) this.renderable.material.get(TextureAttribute.Diffuse)).textureDescription.texture;
    }

    public BlendingAttribute getBlendingAttribute() {
        return this.blendingAttribute;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.badlogic.gdx.graphics.g3d.particles.batches.BufferedParticleBatch
    protected void flush(int[] iArr) {
        int i = 0;
        Array.ArrayIterator it = this.renderData.iterator();
        while (it.hasNext()) {
            PointSpriteControllerRenderData pointSpriteControllerRenderData = (PointSpriteControllerRenderData) it.next();
            ParallelArray.FloatChannel floatChannel = pointSpriteControllerRenderData.scaleChannel;
            ParallelArray.FloatChannel floatChannel2 = pointSpriteControllerRenderData.regionChannel;
            ParallelArray.FloatChannel floatChannel3 = pointSpriteControllerRenderData.positionChannel;
            ParallelArray.FloatChannel floatChannel4 = pointSpriteControllerRenderData.colorChannel;
            ParallelArray.FloatChannel floatChannel5 = pointSpriteControllerRenderData.rotationChannel;
            int i2 = 0;
            while (i2 < pointSpriteControllerRenderData.controller.particles.size) {
                int i3 = iArr[i] * CPU_VERTEX_SIZE;
                int i4 = i2 * floatChannel2.strideSize;
                int i5 = i2 * floatChannel3.strideSize;
                int i6 = i2 * floatChannel4.strideSize;
                int i7 = i2 * floatChannel5.strideSize;
                this.vertices[i3 + CPU_POSITION_OFFSET] = floatChannel3.data[i5];
                this.vertices[i3 + CPU_POSITION_OFFSET + 1] = floatChannel3.data[i5 + 1];
                this.vertices[i3 + CPU_POSITION_OFFSET + 2] = floatChannel3.data[i5 + 2];
                this.vertices[i3 + CPU_COLOR_OFFSET] = floatChannel4.data[i6];
                this.vertices[i3 + CPU_COLOR_OFFSET + 1] = floatChannel4.data[i6 + 1];
                this.vertices[i3 + CPU_COLOR_OFFSET + 2] = floatChannel4.data[i6 + 2];
                this.vertices[i3 + CPU_COLOR_OFFSET + 3] = floatChannel4.data[i6 + 3];
                this.vertices[i3 + CPU_SIZE_AND_ROTATION_OFFSET] = floatChannel.data[i2 * floatChannel.strideSize];
                this.vertices[i3 + CPU_SIZE_AND_ROTATION_OFFSET + 1] = floatChannel5.data[i7];
                this.vertices[i3 + CPU_SIZE_AND_ROTATION_OFFSET + 2] = floatChannel5.data[i7 + 1];
                this.vertices[i3 + CPU_REGION_OFFSET] = floatChannel2.data[i4];
                this.vertices[i3 + CPU_REGION_OFFSET + 1] = floatChannel2.data[i4 + 1];
                this.vertices[i3 + CPU_REGION_OFFSET + 2] = floatChannel2.data[i4 + 2];
                this.vertices[i3 + CPU_REGION_OFFSET + 3] = floatChannel2.data[i4 + 3];
                i2++;
                i++;
            }
        }
        this.renderable.meshPart.size = this.bufferedParticlesCount;
        this.renderable.meshPart.mesh.setVertices(this.vertices, 0, this.bufferedParticlesCount * CPU_VERTEX_SIZE);
        this.renderable.meshPart.update();
    }

    @Override // com.badlogic.gdx.graphics.g3d.RenderableProvider
    public void getRenderables(Array<Renderable> array, Pool<Renderable> pool) {
        if (this.bufferedParticlesCount > 0) {
            array.add(pool.obtain().set(this.renderable));
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch, com.badlogic.gdx.graphics.g3d.particles.ResourceData.Configurable
    public void save(AssetManager assetManager, ResourceData resourceData) {
        resourceData.createSaveData("pointSpriteBatch").saveAsset(assetManager.getAssetFileName(getTexture()), Texture.class);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch, com.badlogic.gdx.graphics.g3d.particles.ResourceData.Configurable
    public void load(AssetManager assetManager, ResourceData resourceData) {
        ResourceData.SaveData saveData = resourceData.getSaveData("pointSpriteBatch");
        if (saveData != null) {
            setTexture((Texture) assetManager.get(saveData.loadAsset()));
        }
    }
}
