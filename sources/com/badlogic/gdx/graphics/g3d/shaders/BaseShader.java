package com.badlogic.gdx.graphics.g3d.shaders;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GLTexture;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Attributes;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;
import com.badlogic.gdx.graphics.g3d.utils.TextureDescriptor;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.IntIntMap;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/shaders/BaseShader.class */
public abstract class BaseShader implements Shader {
    private int[] locations;
    public ShaderProgram program;
    public RenderContext context;
    public Camera camera;
    private Mesh currentMesh;
    private final Array<String> uniforms = new Array<>();
    private final Array<Validator> validators = new Array<>();
    private final Array<Setter> setters = new Array<>();
    private final IntArray globalUniforms = new IntArray();
    private final IntArray localUniforms = new IntArray();
    private final IntIntMap attributes = new IntIntMap();
    private final IntIntMap instancedAttributes = new IntIntMap();
    private final IntArray tempArray = new IntArray();
    private final IntArray tempArray2 = new IntArray();
    private Attributes combinedAttributes = new Attributes();

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/shaders/BaseShader$Setter.class */
    public interface Setter {
        boolean isGlobal(BaseShader baseShader, int i);

        void set(BaseShader baseShader, int i, Renderable renderable, Attributes attributes);
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/shaders/BaseShader$Validator.class */
    public interface Validator {
        boolean validate(BaseShader baseShader, int i, Renderable renderable);
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/shaders/BaseShader$GlobalSetter.class */
    public static abstract class GlobalSetter implements Setter {
        @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
        public boolean isGlobal(BaseShader baseShader, int i) {
            return true;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/shaders/BaseShader$LocalSetter.class */
    public static abstract class LocalSetter implements Setter {
        @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Setter
        public boolean isGlobal(BaseShader baseShader, int i) {
            return false;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/shaders/BaseShader$Uniform.class */
    public static class Uniform implements Validator {
        public final String alias;
        public final long materialMask;
        public final long environmentMask;
        public final long overallMask;

        public Uniform(String str, long j, long j2, long j3) {
            this.alias = str;
            this.materialMask = j;
            this.environmentMask = j2;
            this.overallMask = j3;
        }

        public Uniform(String str, long j, long j2) {
            this(str, j, j2, 0L);
        }

        public Uniform(String str, long j) {
            this(str, 0L, 0L, j);
        }

        public Uniform(String str) {
            this(str, 0L, 0L);
        }

        @Override // com.badlogic.gdx.graphics.g3d.shaders.BaseShader.Validator
        public boolean validate(BaseShader baseShader, int i, Renderable renderable) {
            long mask = (renderable == null || renderable.material == null) ? 0L : renderable.material.getMask();
            long mask2 = (renderable == null || renderable.environment == null) ? 0L : renderable.environment.getMask();
            return (mask & this.materialMask) == this.materialMask && (mask2 & this.environmentMask) == this.environmentMask && ((mask | mask2) & this.overallMask) == this.overallMask;
        }
    }

    public int register(String str, Validator validator, Setter setter) {
        if (this.locations != null) {
            throw new GdxRuntimeException("Cannot register an uniform after initialization");
        }
        int uniformID = getUniformID(str);
        if (uniformID >= 0) {
            this.validators.set(uniformID, validator);
            this.setters.set(uniformID, setter);
            return uniformID;
        }
        this.uniforms.add(str);
        this.validators.add(validator);
        this.setters.add(setter);
        return this.uniforms.size - 1;
    }

    public int register(String str, Validator validator) {
        return register(str, validator, null);
    }

    public int register(String str, Setter setter) {
        return register(str, null, setter);
    }

    public int register(String str) {
        return register(str, null, null);
    }

    public int register(Uniform uniform, Setter setter) {
        return register(uniform.alias, uniform, setter);
    }

    public int register(Uniform uniform) {
        return register(uniform, (Setter) null);
    }

    public int getUniformID(String str) {
        int i = this.uniforms.size;
        for (int i2 = 0; i2 < i; i2++) {
            if (this.uniforms.get(i2).equals(str)) {
                return i2;
            }
        }
        return -1;
    }

    public String getUniformAlias(int i) {
        return this.uniforms.get(i);
    }

    public void init(ShaderProgram shaderProgram, Renderable renderable) {
        if (this.locations != null) {
            throw new GdxRuntimeException("Already initialized");
        }
        if (!shaderProgram.isCompiled()) {
            throw new GdxRuntimeException(shaderProgram.getLog());
        }
        this.program = shaderProgram;
        int i = this.uniforms.size;
        this.locations = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            String str = this.uniforms.get(i2);
            Validator validator = this.validators.get(i2);
            Setter setter = this.setters.get(i2);
            if (validator != null && !validator.validate(this, i2, renderable)) {
                this.locations[i2] = -1;
            } else {
                this.locations[i2] = shaderProgram.fetchUniformLocation(str, false);
                if (this.locations[i2] >= 0 && setter != null) {
                    if (setter.isGlobal(this, i2)) {
                        this.globalUniforms.add(i2);
                    } else {
                        this.localUniforms.add(i2);
                    }
                }
            }
            if (this.locations[i2] < 0) {
                this.validators.set(i2, null);
                this.setters.set(i2, null);
            }
        }
        if (renderable != null) {
            VertexAttributes vertexAttributes = renderable.meshPart.mesh.getVertexAttributes();
            int size = vertexAttributes.size();
            for (int i3 = 0; i3 < size; i3++) {
                VertexAttribute vertexAttribute = vertexAttributes.get(i3);
                int attributeLocation = shaderProgram.getAttributeLocation(vertexAttribute.alias);
                if (attributeLocation >= 0) {
                    this.attributes.put(vertexAttribute.getKey(), attributeLocation);
                }
            }
            VertexAttributes instancedAttributes = renderable.meshPart.mesh.getInstancedAttributes();
            if (instancedAttributes != null) {
                int size2 = instancedAttributes.size();
                for (int i4 = 0; i4 < size2; i4++) {
                    VertexAttribute vertexAttribute2 = instancedAttributes.get(i4);
                    int attributeLocation2 = shaderProgram.getAttributeLocation(vertexAttribute2.alias);
                    if (attributeLocation2 >= 0) {
                        this.instancedAttributes.put(vertexAttribute2.getKey(), attributeLocation2);
                    }
                }
            }
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.Shader
    public void begin(Camera camera, RenderContext renderContext) {
        this.camera = camera;
        this.context = renderContext;
        this.program.bind();
        this.currentMesh = null;
        for (int i = 0; i < this.globalUniforms.size; i++) {
            Array<Setter> array = this.setters;
            int i2 = this.globalUniforms.get(i);
            if (array.get(i2) != null) {
                this.setters.get(i2).set(this, i2, null, null);
            }
        }
    }

    private final int[] getAttributeLocations(VertexAttributes vertexAttributes) {
        this.tempArray.clear();
        int size = vertexAttributes.size();
        for (int i = 0; i < size; i++) {
            this.tempArray.add(this.attributes.get(vertexAttributes.get(i).getKey(), -1));
        }
        this.tempArray.shrink();
        return this.tempArray.items;
    }

    private final int[] getInstancedAttributeLocations(VertexAttributes vertexAttributes) {
        if (vertexAttributes == null) {
            return null;
        }
        this.tempArray2.clear();
        int size = vertexAttributes.size();
        for (int i = 0; i < size; i++) {
            this.tempArray2.add(this.instancedAttributes.get(vertexAttributes.get(i).getKey(), -1));
        }
        this.tempArray2.shrink();
        return this.tempArray2.items;
    }

    @Override // com.badlogic.gdx.graphics.g3d.Shader
    public void render(Renderable renderable) {
        if (renderable.worldTransform.det3x3() == 0.0f) {
            return;
        }
        this.combinedAttributes.clear();
        if (renderable.environment != null) {
            this.combinedAttributes.set(renderable.environment);
        }
        if (renderable.material != null) {
            this.combinedAttributes.set(renderable.material);
        }
        render(renderable, this.combinedAttributes);
    }

    public void render(Renderable renderable, Attributes attributes) {
        for (int i = 0; i < this.localUniforms.size; i++) {
            Array<Setter> array = this.setters;
            int i2 = this.localUniforms.get(i);
            if (array.get(i2) != null) {
                this.setters.get(i2).set(this, i2, renderable, attributes);
            }
        }
        if (this.currentMesh != renderable.meshPart.mesh) {
            if (this.currentMesh != null) {
                this.currentMesh.unbind(this.program, this.tempArray.items, this.tempArray2.items);
            }
            this.currentMesh = renderable.meshPart.mesh;
            this.currentMesh.bind(this.program, getAttributeLocations(renderable.meshPart.mesh.getVertexAttributes()), getInstancedAttributeLocations(renderable.meshPart.mesh.getInstancedAttributes()));
        }
        renderable.meshPart.render(this.program, false);
    }

    @Override // com.badlogic.gdx.graphics.g3d.Shader
    public void end() {
        if (this.currentMesh != null) {
            this.currentMesh.unbind(this.program, this.tempArray.items, this.tempArray2.items);
            this.currentMesh = null;
        }
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        this.program = null;
        this.uniforms.clear();
        this.validators.clear();
        this.setters.clear();
        this.localUniforms.clear();
        this.globalUniforms.clear();
        this.locations = null;
    }

    public final boolean has(int i) {
        return i >= 0 && i < this.locations.length && this.locations[i] >= 0;
    }

    public final int loc(int i) {
        if (i < 0 || i >= this.locations.length) {
            return -1;
        }
        return this.locations[i];
    }

    public final boolean set(int i, Matrix4 matrix4) {
        if (this.locations[i] < 0) {
            return false;
        }
        this.program.setUniformMatrix(this.locations[i], matrix4);
        return true;
    }

    public final boolean set(int i, Matrix3 matrix3) {
        if (this.locations[i] < 0) {
            return false;
        }
        this.program.setUniformMatrix(this.locations[i], matrix3);
        return true;
    }

    public final boolean set(int i, Vector3 vector3) {
        if (this.locations[i] < 0) {
            return false;
        }
        this.program.setUniformf(this.locations[i], vector3);
        return true;
    }

    public final boolean set(int i, Vector2 vector2) {
        if (this.locations[i] < 0) {
            return false;
        }
        this.program.setUniformf(this.locations[i], vector2);
        return true;
    }

    public final boolean set(int i, Color color) {
        if (this.locations[i] < 0) {
            return false;
        }
        this.program.setUniformf(this.locations[i], color);
        return true;
    }

    public final boolean set(int i, float f) {
        if (this.locations[i] < 0) {
            return false;
        }
        this.program.setUniformf(this.locations[i], f);
        return true;
    }

    public final boolean set(int i, float f, float f2) {
        if (this.locations[i] < 0) {
            return false;
        }
        this.program.setUniformf(this.locations[i], f, f2);
        return true;
    }

    public final boolean set(int i, float f, float f2, float f3) {
        if (this.locations[i] < 0) {
            return false;
        }
        this.program.setUniformf(this.locations[i], f, f2, f3);
        return true;
    }

    public final boolean set(int i, float f, float f2, float f3, float f4) {
        if (this.locations[i] < 0) {
            return false;
        }
        this.program.setUniformf(this.locations[i], f, f2, f3, f4);
        return true;
    }

    public final boolean set(int i, int i2) {
        if (this.locations[i] < 0) {
            return false;
        }
        this.program.setUniformi(this.locations[i], i2);
        return true;
    }

    public final boolean set(int i, int i2, int i3) {
        if (this.locations[i] < 0) {
            return false;
        }
        this.program.setUniformi(this.locations[i], i2, i3);
        return true;
    }

    public final boolean set(int i, int i2, int i3, int i4) {
        if (this.locations[i] < 0) {
            return false;
        }
        this.program.setUniformi(this.locations[i], i2, i3, i4);
        return true;
    }

    public final boolean set(int i, int i2, int i3, int i4, int i5) {
        if (this.locations[i] < 0) {
            return false;
        }
        this.program.setUniformi(this.locations[i], i2, i3, i4, i5);
        return true;
    }

    public final boolean set(int i, TextureDescriptor textureDescriptor) {
        if (this.locations[i] < 0) {
            return false;
        }
        this.program.setUniformi(this.locations[i], this.context.textureBinder.bind(textureDescriptor));
        return true;
    }

    public final boolean set(int i, GLTexture gLTexture) {
        if (this.locations[i] < 0) {
            return false;
        }
        this.program.setUniformi(this.locations[i], this.context.textureBinder.bind(gLTexture));
        return true;
    }
}
