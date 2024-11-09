package com.badlogic.gdx.graphics.g3d.particles.batches;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.DepthTestAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.particles.ParallelArray;
import com.badlogic.gdx.graphics.g3d.particles.ParticleShader;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData;
import com.badlogic.gdx.graphics.g3d.particles.renderers.BillboardControllerRenderData;
import com.badlogic.gdx.graphics.g3d.shaders.DefaultShader;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/batches/BillboardParticleBatch.class */
public class BillboardParticleBatch extends BufferedParticleBatch<BillboardControllerRenderData> {
    protected static final int sizeAndRotationUsage = 512;
    protected static final int directionUsage = 1024;
    private static final int MAX_PARTICLES_PER_MESH = 8191;
    private static final int MAX_VERTICES_PER_MESH = 32764;
    private RenderablePool renderablePool;
    private Array<Renderable> renderables;
    private float[] vertices;
    private short[] indices;
    private int currentVertexSize;
    private VertexAttributes currentAttributes;
    protected boolean useGPU;
    protected ParticleShader.AlignMode mode;
    protected Texture texture;
    protected BlendingAttribute blendingAttribute;
    protected DepthTestAttribute depthTestAttribute;
    Shader shader;
    protected static final Vector3 TMP_V1 = new Vector3();
    protected static final Vector3 TMP_V2 = new Vector3();
    protected static final Vector3 TMP_V3 = new Vector3();
    protected static final Vector3 TMP_V4 = new Vector3();
    protected static final Vector3 TMP_V5 = new Vector3();
    protected static final Vector3 TMP_V6 = new Vector3();
    protected static final Matrix3 TMP_M3 = new Matrix3();
    private static final VertexAttributes GPU_ATTRIBUTES = new VertexAttributes(new VertexAttribute(1, 3, ShaderProgram.POSITION_ATTRIBUTE), new VertexAttribute(16, 2, "a_texCoord0"), new VertexAttribute(2, 4, ShaderProgram.COLOR_ATTRIBUTE), new VertexAttribute(512, 4, "a_sizeAndRotation"));
    private static final VertexAttributes CPU_ATTRIBUTES = new VertexAttributes(new VertexAttribute(1, 3, ShaderProgram.POSITION_ATTRIBUTE), new VertexAttribute(16, 2, "a_texCoord0"), new VertexAttribute(2, 4, ShaderProgram.COLOR_ATTRIBUTE));
    private static final int GPU_POSITION_OFFSET = (short) (GPU_ATTRIBUTES.findByUsage(1).offset / 4);
    private static final int GPU_UV_OFFSET = (short) (GPU_ATTRIBUTES.findByUsage(16).offset / 4);
    private static final int GPU_SIZE_ROTATION_OFFSET = (short) (GPU_ATTRIBUTES.findByUsage(512).offset / 4);
    private static final int GPU_COLOR_OFFSET = (short) (GPU_ATTRIBUTES.findByUsage(2).offset / 4);
    private static final int GPU_VERTEX_SIZE = GPU_ATTRIBUTES.vertexSize / 4;
    private static final int CPU_POSITION_OFFSET = (short) (CPU_ATTRIBUTES.findByUsage(1).offset / 4);
    private static final int CPU_UV_OFFSET = (short) (CPU_ATTRIBUTES.findByUsage(16).offset / 4);
    private static final int CPU_COLOR_OFFSET = (short) (CPU_ATTRIBUTES.findByUsage(2).offset / 4);
    private static final int CPU_VERTEX_SIZE = CPU_ATTRIBUTES.vertexSize / 4;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/batches/BillboardParticleBatch$RenderablePool.class */
    public class RenderablePool extends Pool<Renderable> {
        public RenderablePool() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.badlogic.gdx.utils.Pool
        public Renderable newObject() {
            return BillboardParticleBatch.this.allocRenderable();
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/batches/BillboardParticleBatch$Config.class */
    public static class Config {
        boolean useGPU;
        ParticleShader.AlignMode mode;

        public Config() {
        }

        public Config(boolean z, ParticleShader.AlignMode alignMode) {
            this.useGPU = z;
            this.mode = alignMode;
        }
    }

    public BillboardParticleBatch(ParticleShader.AlignMode alignMode, boolean z, int i, BlendingAttribute blendingAttribute, DepthTestAttribute depthTestAttribute) {
        super(BillboardControllerRenderData.class);
        this.currentVertexSize = 0;
        this.useGPU = false;
        this.mode = ParticleShader.AlignMode.Screen;
        this.renderables = new Array<>();
        this.renderablePool = new RenderablePool();
        this.blendingAttribute = blendingAttribute;
        this.depthTestAttribute = depthTestAttribute;
        if (this.blendingAttribute == null) {
            this.blendingAttribute = new BlendingAttribute(1, 771, 1.0f);
        }
        if (this.depthTestAttribute == null) {
            this.depthTestAttribute = new DepthTestAttribute(515, false);
        }
        allocIndices();
        initRenderData();
        ensureCapacity(i);
        setUseGpu(z);
        setAlignMode(alignMode);
    }

    public BillboardParticleBatch(ParticleShader.AlignMode alignMode, boolean z, int i) {
        this(alignMode, z, i, null, null);
    }

    public BillboardParticleBatch() {
        this(ParticleShader.AlignMode.Screen, false, 100);
    }

    public BillboardParticleBatch(int i) {
        this(ParticleShader.AlignMode.Screen, false, i);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.batches.BufferedParticleBatch
    public void allocParticlesData(int i) {
        this.vertices = new float[(this.currentVertexSize << 2) * i];
        allocRenderables(i);
    }

    protected Renderable allocRenderable() {
        Renderable renderable = new Renderable();
        renderable.meshPart.primitiveType = 4;
        renderable.meshPart.offset = 0;
        renderable.material = new Material(this.blendingAttribute, this.depthTestAttribute, TextureAttribute.createDiffuse(this.texture));
        renderable.meshPart.mesh = new Mesh(false, MAX_VERTICES_PER_MESH, 49146, this.currentAttributes);
        renderable.meshPart.mesh.setIndices(this.indices);
        renderable.shader = this.shader;
        return renderable;
    }

    private void allocIndices() {
        this.indices = new short[49146];
        int i = 0;
        int i2 = 0;
        while (i < 49146) {
            this.indices[i] = (short) i2;
            this.indices[i + 1] = (short) (i2 + 1);
            this.indices[i + 2] = (short) (i2 + 2);
            this.indices[i + 3] = (short) (i2 + 2);
            this.indices[i + 4] = (short) (i2 + 3);
            this.indices[i + 5] = (short) i2;
            i += 6;
            i2 += 4;
        }
    }

    private void allocRenderables(int i) {
        int ceil = MathUtils.ceil(i / MAX_PARTICLES_PER_MESH);
        int free = this.renderablePool.getFree();
        if (free < ceil) {
            int i2 = ceil - free;
            for (int i3 = 0; i3 < i2; i3++) {
                this.renderablePool.free(this.renderablePool.newObject());
            }
        }
    }

    protected Shader getShader(Renderable renderable) {
        Shader particleShader = this.useGPU ? new ParticleShader(renderable, new ParticleShader.Config(this.mode)) : new DefaultShader(renderable);
        Shader shader = particleShader;
        particleShader.init();
        return shader;
    }

    private void allocShader() {
        Renderable allocRenderable = allocRenderable();
        Shader shader = getShader(allocRenderable);
        allocRenderable.shader = shader;
        this.shader = shader;
        this.renderablePool.free(allocRenderable);
    }

    private void clearRenderablesPool() {
        this.renderablePool.freeAll(this.renderables);
        int free = this.renderablePool.getFree();
        for (int i = 0; i < free; i++) {
            this.renderablePool.obtain().meshPart.mesh.dispose();
        }
        this.renderables.clear();
    }

    public void setVertexData() {
        if (this.useGPU) {
            this.currentAttributes = GPU_ATTRIBUTES;
            this.currentVertexSize = GPU_VERTEX_SIZE;
        } else {
            this.currentAttributes = CPU_ATTRIBUTES;
            this.currentVertexSize = CPU_VERTEX_SIZE;
        }
    }

    private void initRenderData() {
        setVertexData();
        clearRenderablesPool();
        allocShader();
        resetCapacity();
    }

    public void setAlignMode(ParticleShader.AlignMode alignMode) {
        if (alignMode != this.mode) {
            this.mode = alignMode;
            if (this.useGPU) {
                initRenderData();
                allocRenderables(this.bufferedParticlesCount);
            }
        }
    }

    public ParticleShader.AlignMode getAlignMode() {
        return this.mode;
    }

    public void setUseGpu(boolean z) {
        if (this.useGPU != z) {
            this.useGPU = z;
            initRenderData();
            allocRenderables(this.bufferedParticlesCount);
        }
    }

    public boolean isUseGPU() {
        return this.useGPU;
    }

    public void setTexture(Texture texture) {
        this.renderablePool.freeAll(this.renderables);
        this.renderables.clear();
        int free = this.renderablePool.getFree();
        for (int i = 0; i < free; i++) {
            ((TextureAttribute) this.renderablePool.obtain().material.get(TextureAttribute.Diffuse)).textureDescription.texture = texture;
        }
        this.texture = texture;
    }

    public Texture getTexture() {
        return this.texture;
    }

    public BlendingAttribute getBlendingAttribute() {
        return this.blendingAttribute;
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.batches.BufferedParticleBatch, com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch
    public void begin() {
        super.begin();
        this.renderablePool.freeAll(this.renderables);
        this.renderables.clear();
    }

    private static void putVertex(float[] fArr, int i, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13) {
        fArr[i + GPU_POSITION_OFFSET] = f;
        fArr[i + GPU_POSITION_OFFSET + 1] = f2;
        fArr[i + GPU_POSITION_OFFSET + 2] = f3;
        fArr[i + GPU_UV_OFFSET] = f4;
        fArr[i + GPU_UV_OFFSET + 1] = f5;
        fArr[i + GPU_SIZE_ROTATION_OFFSET] = f6;
        fArr[i + GPU_SIZE_ROTATION_OFFSET + 1] = f7;
        fArr[i + GPU_SIZE_ROTATION_OFFSET + 2] = f8;
        fArr[i + GPU_SIZE_ROTATION_OFFSET + 3] = f9;
        fArr[i + GPU_COLOR_OFFSET] = f10;
        fArr[i + GPU_COLOR_OFFSET + 1] = f11;
        fArr[i + GPU_COLOR_OFFSET + 2] = f12;
        fArr[i + GPU_COLOR_OFFSET + 3] = f13;
    }

    private static void putVertex(float[] fArr, int i, Vector3 vector3, float f, float f2, float f3, float f4, float f5, float f6) {
        fArr[i + CPU_POSITION_OFFSET] = vector3.x;
        fArr[i + CPU_POSITION_OFFSET + 1] = vector3.y;
        fArr[i + CPU_POSITION_OFFSET + 2] = vector3.z;
        fArr[i + CPU_UV_OFFSET] = f;
        fArr[i + CPU_UV_OFFSET + 1] = f2;
        fArr[i + CPU_COLOR_OFFSET] = f3;
        fArr[i + CPU_COLOR_OFFSET + 1] = f4;
        fArr[i + CPU_COLOR_OFFSET + 2] = f5;
        fArr[i + CPU_COLOR_OFFSET + 3] = f6;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void fillVerticesGPU(int[] iArr) {
        int i = 0;
        Array.ArrayIterator it = this.renderData.iterator();
        while (it.hasNext()) {
            BillboardControllerRenderData billboardControllerRenderData = (BillboardControllerRenderData) it.next();
            ParallelArray.FloatChannel floatChannel = billboardControllerRenderData.scaleChannel;
            ParallelArray.FloatChannel floatChannel2 = billboardControllerRenderData.regionChannel;
            ParallelArray.FloatChannel floatChannel3 = billboardControllerRenderData.positionChannel;
            ParallelArray.FloatChannel floatChannel4 = billboardControllerRenderData.colorChannel;
            ParallelArray.FloatChannel floatChannel5 = billboardControllerRenderData.rotationChannel;
            int i2 = 0;
            int i3 = billboardControllerRenderData.controller.particles.size;
            while (i2 < i3) {
                int i4 = (iArr[i] * this.currentVertexSize) << 2;
                float f = floatChannel.data[i2 * floatChannel.strideSize];
                int i5 = i2 * floatChannel2.strideSize;
                int i6 = i2 * floatChannel3.strideSize;
                int i7 = i2 * floatChannel4.strideSize;
                int i8 = i2 * floatChannel5.strideSize;
                float f2 = floatChannel3.data[i6];
                float f3 = floatChannel3.data[i6 + 1];
                float f4 = floatChannel3.data[i6 + 2];
                float f5 = floatChannel2.data[i5];
                float f6 = floatChannel2.data[i5 + 1];
                float f7 = floatChannel2.data[i5 + 2];
                float f8 = floatChannel2.data[i5 + 3];
                float f9 = floatChannel2.data[i5 + 4] * f;
                float f10 = floatChannel2.data[i5 + 5] * f;
                float f11 = floatChannel4.data[i7];
                float f12 = floatChannel4.data[i7 + 1];
                float f13 = floatChannel4.data[i7 + 2];
                float f14 = floatChannel4.data[i7 + 3];
                float f15 = floatChannel5.data[i8];
                float f16 = floatChannel5.data[i8 + 1];
                putVertex(this.vertices, i4, f2, f3, f4, f5, f8, -f9, -f10, f15, f16, f11, f12, f13, f14);
                int i9 = i4 + this.currentVertexSize;
                putVertex(this.vertices, i9, f2, f3, f4, f7, f8, f9, -f10, f15, f16, f11, f12, f13, f14);
                int i10 = i9 + this.currentVertexSize;
                putVertex(this.vertices, i10, f2, f3, f4, f7, f6, f9, f10, f15, f16, f11, f12, f13, f14);
                putVertex(this.vertices, i10 + this.currentVertexSize, f2, f3, f4, f5, f6, -f9, f10, f15, f16, f11, f12, f13, f14);
                i2++;
                i++;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void fillVerticesToViewPointCPU(int[] iArr) {
        int i = 0;
        Array.ArrayIterator it = this.renderData.iterator();
        while (it.hasNext()) {
            BillboardControllerRenderData billboardControllerRenderData = (BillboardControllerRenderData) it.next();
            ParallelArray.FloatChannel floatChannel = billboardControllerRenderData.scaleChannel;
            ParallelArray.FloatChannel floatChannel2 = billboardControllerRenderData.regionChannel;
            ParallelArray.FloatChannel floatChannel3 = billboardControllerRenderData.positionChannel;
            ParallelArray.FloatChannel floatChannel4 = billboardControllerRenderData.colorChannel;
            ParallelArray.FloatChannel floatChannel5 = billboardControllerRenderData.rotationChannel;
            int i2 = 0;
            int i3 = billboardControllerRenderData.controller.particles.size;
            while (i2 < i3) {
                int i4 = (iArr[i] * this.currentVertexSize) << 2;
                float f = floatChannel.data[i2 * floatChannel.strideSize];
                int i5 = i2 * floatChannel2.strideSize;
                int i6 = i2 * floatChannel3.strideSize;
                int i7 = i2 * floatChannel4.strideSize;
                int i8 = i2 * floatChannel5.strideSize;
                float f2 = floatChannel3.data[i6];
                float f3 = floatChannel3.data[i6 + 1];
                float f4 = floatChannel3.data[i6 + 2];
                float f5 = floatChannel2.data[i5];
                float f6 = floatChannel2.data[i5 + 1];
                float f7 = floatChannel2.data[i5 + 2];
                float f8 = floatChannel2.data[i5 + 3];
                float f9 = floatChannel2.data[i5 + 4] * f;
                float f10 = floatChannel2.data[i5 + 5] * f;
                float f11 = floatChannel4.data[i7];
                float f12 = floatChannel4.data[i7 + 1];
                float f13 = floatChannel4.data[i7 + 2];
                float f14 = floatChannel4.data[i7 + 3];
                float f15 = floatChannel5.data[i8];
                float f16 = floatChannel5.data[i8 + 1];
                Vector3 nor = TMP_V3.set(this.camera.position).sub(f2, f3, f4).nor();
                Vector3 nor2 = TMP_V1.set(this.camera.up).crs(nor).nor();
                Vector3 crs = TMP_V2.set(nor).crs(nor2);
                nor2.scl(f9);
                crs.scl(f10);
                if (f15 != 1.0f) {
                    TMP_M3.setToRotation(nor, f15, f16);
                    putVertex(this.vertices, i4, TMP_V6.set((-TMP_V1.x) - TMP_V2.x, (-TMP_V1.y) - TMP_V2.y, (-TMP_V1.z) - TMP_V2.z).mul(TMP_M3).add(f2, f3, f4), f5, f8, f11, f12, f13, f14);
                    int i9 = i4 + this.currentVertexSize;
                    putVertex(this.vertices, i9, TMP_V6.set(TMP_V1.x - TMP_V2.x, TMP_V1.y - TMP_V2.y, TMP_V1.z - TMP_V2.z).mul(TMP_M3).add(f2, f3, f4), f7, f8, f11, f12, f13, f14);
                    int i10 = i9 + this.currentVertexSize;
                    putVertex(this.vertices, i10, TMP_V6.set(TMP_V1.x + TMP_V2.x, TMP_V1.y + TMP_V2.y, TMP_V1.z + TMP_V2.z).mul(TMP_M3).add(f2, f3, f4), f7, f6, f11, f12, f13, f14);
                    putVertex(this.vertices, i10 + this.currentVertexSize, TMP_V6.set((-TMP_V1.x) + TMP_V2.x, (-TMP_V1.y) + TMP_V2.y, (-TMP_V1.z) + TMP_V2.z).mul(TMP_M3).add(f2, f3, f4), f5, f6, f11, f12, f13, f14);
                } else {
                    putVertex(this.vertices, i4, TMP_V6.set(((-TMP_V1.x) - TMP_V2.x) + f2, ((-TMP_V1.y) - TMP_V2.y) + f3, ((-TMP_V1.z) - TMP_V2.z) + f4), f5, f8, f11, f12, f13, f14);
                    int i11 = i4 + this.currentVertexSize;
                    putVertex(this.vertices, i11, TMP_V6.set((TMP_V1.x - TMP_V2.x) + f2, (TMP_V1.y - TMP_V2.y) + f3, (TMP_V1.z - TMP_V2.z) + f4), f7, f8, f11, f12, f13, f14);
                    int i12 = i11 + this.currentVertexSize;
                    putVertex(this.vertices, i12, TMP_V6.set(TMP_V1.x + TMP_V2.x + f2, TMP_V1.y + TMP_V2.y + f3, TMP_V1.z + TMP_V2.z + f4), f7, f6, f11, f12, f13, f14);
                    putVertex(this.vertices, i12 + this.currentVertexSize, TMP_V6.set((-TMP_V1.x) + TMP_V2.x + f2, (-TMP_V1.y) + TMP_V2.y + f3, (-TMP_V1.z) + TMP_V2.z + f4), f5, f6, f11, f12, f13, f14);
                }
                i2++;
                i++;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void fillVerticesToScreenCPU(int[] iArr) {
        Vector3 scl = TMP_V3.set(this.camera.direction).scl(-1.0f);
        Vector3 nor = TMP_V4.set(this.camera.up).crs(scl).nor();
        Vector3 vector3 = this.camera.up;
        int i = 0;
        Array.ArrayIterator it = this.renderData.iterator();
        while (it.hasNext()) {
            BillboardControllerRenderData billboardControllerRenderData = (BillboardControllerRenderData) it.next();
            ParallelArray.FloatChannel floatChannel = billboardControllerRenderData.scaleChannel;
            ParallelArray.FloatChannel floatChannel2 = billboardControllerRenderData.regionChannel;
            ParallelArray.FloatChannel floatChannel3 = billboardControllerRenderData.positionChannel;
            ParallelArray.FloatChannel floatChannel4 = billboardControllerRenderData.colorChannel;
            ParallelArray.FloatChannel floatChannel5 = billboardControllerRenderData.rotationChannel;
            int i2 = 0;
            int i3 = billboardControllerRenderData.controller.particles.size;
            while (i2 < i3) {
                int i4 = (iArr[i] * this.currentVertexSize) << 2;
                float f = floatChannel.data[i2 * floatChannel.strideSize];
                int i5 = i2 * floatChannel2.strideSize;
                int i6 = i2 * floatChannel3.strideSize;
                int i7 = i2 * floatChannel4.strideSize;
                int i8 = i2 * floatChannel5.strideSize;
                float f2 = floatChannel3.data[i6];
                float f3 = floatChannel3.data[i6 + 1];
                float f4 = floatChannel3.data[i6 + 2];
                float f5 = floatChannel2.data[i5];
                float f6 = floatChannel2.data[i5 + 1];
                float f7 = floatChannel2.data[i5 + 2];
                float f8 = floatChannel2.data[i5 + 3];
                float f9 = floatChannel2.data[i5 + 4] * f;
                float f10 = floatChannel2.data[i5 + 5] * f;
                float f11 = floatChannel4.data[i7];
                float f12 = floatChannel4.data[i7 + 1];
                float f13 = floatChannel4.data[i7 + 2];
                float f14 = floatChannel4.data[i7 + 3];
                float f15 = floatChannel5.data[i8];
                float f16 = floatChannel5.data[i8 + 1];
                TMP_V1.set(nor).scl(f9);
                TMP_V2.set(vector3).scl(f10);
                if (f15 != 1.0f) {
                    TMP_M3.setToRotation(scl, f15, f16);
                    putVertex(this.vertices, i4, TMP_V6.set((-TMP_V1.x) - TMP_V2.x, (-TMP_V1.y) - TMP_V2.y, (-TMP_V1.z) - TMP_V2.z).mul(TMP_M3).add(f2, f3, f4), f5, f8, f11, f12, f13, f14);
                    int i9 = i4 + this.currentVertexSize;
                    putVertex(this.vertices, i9, TMP_V6.set(TMP_V1.x - TMP_V2.x, TMP_V1.y - TMP_V2.y, TMP_V1.z - TMP_V2.z).mul(TMP_M3).add(f2, f3, f4), f7, f8, f11, f12, f13, f14);
                    int i10 = i9 + this.currentVertexSize;
                    putVertex(this.vertices, i10, TMP_V6.set(TMP_V1.x + TMP_V2.x, TMP_V1.y + TMP_V2.y, TMP_V1.z + TMP_V2.z).mul(TMP_M3).add(f2, f3, f4), f7, f6, f11, f12, f13, f14);
                    putVertex(this.vertices, i10 + this.currentVertexSize, TMP_V6.set((-TMP_V1.x) + TMP_V2.x, (-TMP_V1.y) + TMP_V2.y, (-TMP_V1.z) + TMP_V2.z).mul(TMP_M3).add(f2, f3, f4), f5, f6, f11, f12, f13, f14);
                } else {
                    putVertex(this.vertices, i4, TMP_V6.set(((-TMP_V1.x) - TMP_V2.x) + f2, ((-TMP_V1.y) - TMP_V2.y) + f3, ((-TMP_V1.z) - TMP_V2.z) + f4), f5, f8, f11, f12, f13, f14);
                    int i11 = i4 + this.currentVertexSize;
                    putVertex(this.vertices, i11, TMP_V6.set((TMP_V1.x - TMP_V2.x) + f2, (TMP_V1.y - TMP_V2.y) + f3, (TMP_V1.z - TMP_V2.z) + f4), f7, f8, f11, f12, f13, f14);
                    int i12 = i11 + this.currentVertexSize;
                    putVertex(this.vertices, i12, TMP_V6.set(TMP_V1.x + TMP_V2.x + f2, TMP_V1.y + TMP_V2.y + f3, TMP_V1.z + TMP_V2.z + f4), f7, f6, f11, f12, f13, f14);
                    putVertex(this.vertices, i12 + this.currentVertexSize, TMP_V6.set((-TMP_V1.x) + TMP_V2.x + f2, (-TMP_V1.y) + TMP_V2.y + f3, (-TMP_V1.z) + TMP_V2.z + f4), f5, f6, f11, f12, f13, f14);
                }
                i2++;
                i++;
            }
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.batches.BufferedParticleBatch
    protected void flush(int[] iArr) {
        if (this.useGPU) {
            fillVerticesGPU(iArr);
        } else if (this.mode == ParticleShader.AlignMode.Screen) {
            fillVerticesToScreenCPU(iArr);
        } else if (this.mode == ParticleShader.AlignMode.ViewPoint) {
            fillVerticesToViewPointCPU(iArr);
        }
        int i = this.bufferedParticlesCount << 2;
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 < i) {
                int min = Math.min(i - i3, MAX_VERTICES_PER_MESH);
                Renderable obtain = this.renderablePool.obtain();
                obtain.meshPart.size = (min / 4) * 6;
                obtain.meshPart.mesh.setVertices(this.vertices, this.currentVertexSize * i3, this.currentVertexSize * min);
                obtain.meshPart.update();
                this.renderables.add(obtain);
                i2 = i3 + min;
            } else {
                return;
            }
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.RenderableProvider
    public void getRenderables(Array<Renderable> array, Pool<Renderable> pool) {
        Array.ArrayIterator<Renderable> it = this.renderables.iterator();
        while (it.hasNext()) {
            array.add(pool.obtain().set(it.next()));
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch, com.badlogic.gdx.graphics.g3d.particles.ResourceData.Configurable
    public void save(AssetManager assetManager, ResourceData resourceData) {
        ResourceData.SaveData createSaveData = resourceData.createSaveData("billboardBatch");
        createSaveData.save("cfg", new Config(this.useGPU, this.mode));
        createSaveData.saveAsset(assetManager.getAssetFileName(this.texture), Texture.class);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch, com.badlogic.gdx.graphics.g3d.particles.ResourceData.Configurable
    public void load(AssetManager assetManager, ResourceData resourceData) {
        ResourceData.SaveData saveData = resourceData.getSaveData("billboardBatch");
        if (saveData != null) {
            setTexture((Texture) assetManager.get(saveData.loadAsset()));
            Config config = (Config) saveData.load("cfg");
            setUseGpu(config.useGPU);
            setAlignMode(config.mode);
        }
    }
}
