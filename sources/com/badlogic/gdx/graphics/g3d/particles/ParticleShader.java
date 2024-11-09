package com.badlogic.gdx.graphics.g3d.particles;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.Attribute;
import com.badlogic.gdx.graphics.g3d.Attributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.DepthTestAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.IntAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.shaders.BaseShader;
import com.badlogic.gdx.graphics.g3d.shaders.DefaultShader;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/ParticleShader.class */
public class ParticleShader extends BaseShader {
    private Renderable renderable;
    private long materialMask;
    private long vertexMask;
    protected final Config config;
    Material currentMaterial;
    private static String defaultVertexShader = null;
    private static String defaultFragmentShader = null;
    protected static long implementedFlags = BlendingAttribute.Type | TextureAttribute.Diffuse;
    static final Vector3 TMP_VECTOR3 = new Vector3();
    private static final long optionalAttributes = IntAttribute.CullFace | DepthTestAttribute.Type;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/ParticleShader$AlignMode.class */
    public enum AlignMode {
        Screen,
        ViewPoint
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/ParticleShader$Inputs.class */
    public static class Inputs {
        public static final BaseShader.Uniform cameraRight = new BaseShader.Uniform("u_cameraRight");
        public static final BaseShader.Uniform cameraInvDirection = new BaseShader.Uniform("u_cameraInvDirection");
        public static final BaseShader.Uniform screenWidth = new BaseShader.Uniform("u_screenWidth");
        public static final BaseShader.Uniform regionSize = new BaseShader.Uniform("u_regionSize");
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/ParticleShader$ParticleType.class */
    public enum ParticleType {
        Billboard,
        Point
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/ParticleShader$Setters.class */
    public static class Setters {
        public static final BaseShader.Setter cameraRight = new BaseShader.Setter() { // from class: com.badlogic.gdx.graphics.g3d.particles.ParticleShader.Setters.1
            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public boolean isGlobal(BaseShader baseShader, int i) {
                return true;
            }

            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public void set(BaseShader baseShader, int i, Renderable renderable, Attributes attributes) {
                baseShader.set(i, ParticleShader.TMP_VECTOR3.set(baseShader.camera.direction).crs(baseShader.camera.up).nor());
            }
        };
        public static final BaseShader.Setter cameraUp = new BaseShader.Setter() { // from class: com.badlogic.gdx.graphics.g3d.particles.ParticleShader.Setters.2
            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public boolean isGlobal(BaseShader baseShader, int i) {
                return true;
            }

            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public void set(BaseShader baseShader, int i, Renderable renderable, Attributes attributes) {
                baseShader.set(i, ParticleShader.TMP_VECTOR3.set(baseShader.camera.up).nor());
            }
        };
        public static final BaseShader.Setter cameraInvDirection = new BaseShader.Setter() { // from class: com.badlogic.gdx.graphics.g3d.particles.ParticleShader.Setters.3
            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public boolean isGlobal(BaseShader baseShader, int i) {
                return true;
            }

            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public void set(BaseShader baseShader, int i, Renderable renderable, Attributes attributes) {
                baseShader.set(i, ParticleShader.TMP_VECTOR3.set(-baseShader.camera.direction.x, -baseShader.camera.direction.y, -baseShader.camera.direction.z).nor());
            }
        };
        public static final BaseShader.Setter cameraPosition = new BaseShader.Setter() { // from class: com.badlogic.gdx.graphics.g3d.particles.ParticleShader.Setters.4
            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public boolean isGlobal(BaseShader baseShader, int i) {
                return true;
            }

            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public void set(BaseShader baseShader, int i, Renderable renderable, Attributes attributes) {
                baseShader.set(i, baseShader.camera.position);
            }
        };
        public static final BaseShader.Setter screenWidth = new BaseShader.Setter() { // from class: com.badlogic.gdx.graphics.g3d.particles.ParticleShader.Setters.5
            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public boolean isGlobal(BaseShader baseShader, int i) {
                return true;
            }

            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public void set(BaseShader baseShader, int i, Renderable renderable, Attributes attributes) {
                baseShader.set(i, Gdx.graphics.getWidth());
            }
        };
        public static final BaseShader.Setter worldViewTrans = new BaseShader.Setter() { // from class: com.badlogic.gdx.graphics.g3d.particles.ParticleShader.Setters.6
            final Matrix4 temp = new Matrix4();

            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public boolean isGlobal(BaseShader baseShader, int i) {
                return false;
            }

            @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
            public void set(BaseShader baseShader, int i, Renderable renderable, Attributes attributes) {
                baseShader.set(i, this.temp.set(baseShader.camera.view).mul(renderable.worldTransform));
            }
        };
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/ParticleShader$Config.class */
    public static class Config {
        public String vertexShader;
        public String fragmentShader;
        public boolean ignoreUnimplemented;
        public int defaultCullFace;
        public int defaultDepthFunc;
        public AlignMode align;
        public ParticleType type;

        public Config() {
            this.vertexShader = null;
            this.fragmentShader = null;
            this.ignoreUnimplemented = true;
            this.defaultCullFace = -1;
            this.defaultDepthFunc = -1;
            this.align = AlignMode.Screen;
            this.type = ParticleType.Billboard;
        }

        public Config(AlignMode alignMode, ParticleType particleType) {
            this.vertexShader = null;
            this.fragmentShader = null;
            this.ignoreUnimplemented = true;
            this.defaultCullFace = -1;
            this.defaultDepthFunc = -1;
            this.align = AlignMode.Screen;
            this.type = ParticleType.Billboard;
            this.align = alignMode;
            this.type = particleType;
        }

        public Config(AlignMode alignMode) {
            this.vertexShader = null;
            this.fragmentShader = null;
            this.ignoreUnimplemented = true;
            this.defaultCullFace = -1;
            this.defaultDepthFunc = -1;
            this.align = AlignMode.Screen;
            this.type = ParticleType.Billboard;
            this.align = alignMode;
        }

        public Config(ParticleType particleType) {
            this.vertexShader = null;
            this.fragmentShader = null;
            this.ignoreUnimplemented = true;
            this.defaultCullFace = -1;
            this.defaultDepthFunc = -1;
            this.align = AlignMode.Screen;
            this.type = ParticleType.Billboard;
            this.type = particleType;
        }

        public Config(String str, String str2) {
            this.vertexShader = null;
            this.fragmentShader = null;
            this.ignoreUnimplemented = true;
            this.defaultCullFace = -1;
            this.defaultDepthFunc = -1;
            this.align = AlignMode.Screen;
            this.type = ParticleType.Billboard;
            this.vertexShader = str;
            this.fragmentShader = str2;
        }
    }

    public static String getDefaultVertexShader() {
        if (defaultVertexShader == null) {
            defaultVertexShader = Gdx.files.classpath("com/badlogic/gdx/graphics/g3d/particles/particles.vertex.glsl").readString();
        }
        return defaultVertexShader;
    }

    public static String getDefaultFragmentShader() {
        if (defaultFragmentShader == null) {
            defaultFragmentShader = Gdx.files.classpath("com/badlogic/gdx/graphics/g3d/particles/particles.fragment.glsl").readString();
        }
        return defaultFragmentShader;
    }

    public ParticleShader(Renderable renderable) {
        this(renderable, new Config());
    }

    public ParticleShader(Renderable renderable, Config config) {
        this(renderable, config, createPrefix(renderable, config));
    }

    public ParticleShader(Renderable renderable, Config config, String str) {
        this(renderable, config, str, config.vertexShader != null ? config.vertexShader : getDefaultVertexShader(), config.fragmentShader != null ? config.fragmentShader : getDefaultFragmentShader());
    }

    public ParticleShader(Renderable renderable, Config config, String str, String str2, String str3) {
        this(renderable, config, new ShaderProgram(str + str2, str + str3));
    }

    public ParticleShader(Renderable renderable, Config config, ShaderProgram shaderProgram) {
        this.config = config;
        this.program = shaderProgram;
        this.renderable = renderable;
        this.materialMask = renderable.material.getMask() | optionalAttributes;
        this.vertexMask = renderable.meshPart.mesh.getVertexAttributes().getMask();
        if (!config.ignoreUnimplemented && (implementedFlags & this.materialMask) != this.materialMask) {
            throw new GdxRuntimeException("Some attributes not implemented yet (" + this.materialMask + ")");
        }
        register(DefaultShader.Inputs.viewTrans, DefaultShader.Setters.viewTrans);
        register(DefaultShader.Inputs.projViewTrans, DefaultShader.Setters.projViewTrans);
        register(DefaultShader.Inputs.projTrans, DefaultShader.Setters.projTrans);
        register(Inputs.screenWidth, Setters.screenWidth);
        register(DefaultShader.Inputs.cameraUp, Setters.cameraUp);
        register(Inputs.cameraRight, Setters.cameraRight);
        register(Inputs.cameraInvDirection, Setters.cameraInvDirection);
        register(DefaultShader.Inputs.cameraPosition, Setters.cameraPosition);
        register(DefaultShader.Inputs.diffuseTexture, DefaultShader.Setters.diffuseTexture);
    }

    @Override // com.badlogic.gdx.graphics.g3d.Shader
    public void init() {
        ShaderProgram shaderProgram = this.program;
        this.program = null;
        init(shaderProgram, this.renderable);
        this.renderable = null;
    }

    public static String createPrefix(Renderable renderable, Config config) {
        String str = Gdx.app.getType() == Application.ApplicationType.Desktop ? "#version 120\n" : "#version 100\n";
        if (config.type == ParticleType.Billboard) {
            str = str + "#define billboard\n";
            if (config.align == AlignMode.Screen) {
                str = str + "#define screenFacing\n";
            } else if (config.align == AlignMode.ViewPoint) {
                str = str + "#define viewPointFacing\n";
            }
        }
        return str;
    }

    @Override // com.badlogic.gdx.graphics.g3d.Shader
    public boolean canRender(Renderable renderable) {
        return this.materialMask == (renderable.material.getMask() | optionalAttributes) && this.vertexMask == renderable.meshPart.mesh.getVertexAttributes().getMask();
    }

    @Override // com.badlogic.gdx.graphics.g3d.Shader
    public int compareTo(Shader shader) {
        if (shader == null) {
            return -1;
        }
        return shader == this ? 0 : 0;
    }

    public boolean equals(Object obj) {
        return (obj instanceof ParticleShader) && equals((ParticleShader) obj);
    }

    public boolean equals(ParticleShader particleShader) {
        return particleShader == this;
    }

    @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader, com.badlogic.gdx.graphics.g3d.Shader
    public void begin(Camera camera, RenderContext renderContext) {
        super.begin(camera, renderContext);
    }

    @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader, com.badlogic.gdx.graphics.g3d.Shader
    public void render(Renderable renderable) {
        if (!renderable.material.has(BlendingAttribute.Type)) {
            this.context.setBlending(false, 770, 771);
        }
        bindMaterial(renderable);
        super.render(renderable);
    }

    @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader, com.badlogic.gdx.graphics.g3d.Shader
    public void end() {
        this.currentMaterial = null;
        super.end();
    }

    protected void bindMaterial(Renderable renderable) {
        if (this.currentMaterial == renderable.material) {
            return;
        }
        int i = this.config.defaultCullFace == -1 ? 1029 : this.config.defaultCullFace;
        int i2 = this.config.defaultDepthFunc == -1 ? 515 : this.config.defaultDepthFunc;
        float f = 0.0f;
        float f2 = 1.0f;
        boolean z = true;
        this.currentMaterial = renderable.material;
        Iterator<Attribute> it = this.currentMaterial.iterator();
        while (it.hasNext()) {
            Attribute next = it.next();
            long j = next.type;
            if (BlendingAttribute.is(j)) {
                this.context.setBlending(true, ((BlendingAttribute) next).sourceFunction, ((BlendingAttribute) next).destFunction);
            } else if ((j & DepthTestAttribute.Type) == DepthTestAttribute.Type) {
                DepthTestAttribute depthTestAttribute = (DepthTestAttribute) next;
                i2 = depthTestAttribute.depthFunc;
                f = depthTestAttribute.depthRangeNear;
                f2 = depthTestAttribute.depthRangeFar;
                z = depthTestAttribute.depthMask;
            } else if (!this.config.ignoreUnimplemented) {
                throw new GdxRuntimeException("Unknown material attribute: " + next.toString());
            }
        }
        this.context.setCullFace(i);
        this.context.setDepthTest(i2, f, f2);
        this.context.setDepthMask(z);
    }

    @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader, com.badlogic.gdx.utils.Disposable
    public void dispose() {
        this.program.dispose();
        super.dispose();
    }

    public int getDefaultCullFace() {
        if (this.config.defaultCullFace == -1) {
            return 1029;
        }
        return this.config.defaultCullFace;
    }

    public void setDefaultCullFace(int i) {
        this.config.defaultCullFace = i;
    }

    public int getDefaultDepthFunc() {
        if (this.config.defaultDepthFunc == -1) {
            return 515;
        }
        return this.config.defaultDepthFunc;
    }

    public void setDefaultDepthFunc(int i) {
        this.config.defaultDepthFunc = i;
    }
}
