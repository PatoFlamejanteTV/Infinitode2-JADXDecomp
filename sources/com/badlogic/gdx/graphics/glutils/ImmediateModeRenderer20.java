package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/glutils/ImmediateModeRenderer20.class */
public class ImmediateModeRenderer20 implements ImmediateModeRenderer {
    private int primitiveType;
    private int vertexIdx;
    private int numSetTexCoords;
    private final int maxVertices;
    private int numVertices;
    private final Mesh mesh;
    private ShaderProgram shader;
    private boolean ownsShader;
    private final int numTexCoords;
    private final int vertexSize;
    private final int normalOffset;
    private final int colorOffset;
    private final int texCoordOffset;
    private final Matrix4 projModelView;
    private final float[] vertices;
    private final String[] shaderUniformNames;

    public ImmediateModeRenderer20(boolean z, boolean z2, int i) {
        this(5000, z, z2, i, createDefaultShader(z, z2, i));
        this.ownsShader = true;
    }

    public ImmediateModeRenderer20(int i, boolean z, boolean z2, int i2) {
        this(i, z, z2, i2, createDefaultShader(z, z2, i2));
        this.ownsShader = true;
    }

    public ImmediateModeRenderer20(int i, boolean z, boolean z2, int i2, ShaderProgram shaderProgram) {
        int i3;
        this.projModelView = new Matrix4();
        this.maxVertices = i;
        this.numTexCoords = i2;
        this.shader = shaderProgram;
        this.mesh = new Mesh(false, i, 0, buildVertexAttributes(z, z2, i2));
        this.vertices = new float[i * (this.mesh.getVertexAttributes().vertexSize / 4)];
        this.vertexSize = this.mesh.getVertexAttributes().vertexSize / 4;
        this.normalOffset = this.mesh.getVertexAttribute(8) != null ? this.mesh.getVertexAttribute(8).offset / 4 : 0;
        this.colorOffset = this.mesh.getVertexAttribute(4) != null ? this.mesh.getVertexAttribute(4).offset / 4 : 0;
        if (this.mesh.getVertexAttribute(16) != null) {
            i3 = this.mesh.getVertexAttribute(16).offset / 4;
        } else {
            i3 = 0;
        }
        this.texCoordOffset = i3;
        this.shaderUniformNames = new String[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            this.shaderUniformNames[i4] = "u_sampler" + i4;
        }
    }

    private VertexAttribute[] buildVertexAttributes(boolean z, boolean z2, int i) {
        Array array = new Array();
        array.add(new VertexAttribute(1, 3, ShaderProgram.POSITION_ATTRIBUTE));
        if (z) {
            array.add(new VertexAttribute(8, 3, ShaderProgram.NORMAL_ATTRIBUTE));
        }
        if (z2) {
            array.add(new VertexAttribute(4, 4, ShaderProgram.COLOR_ATTRIBUTE));
        }
        for (int i2 = 0; i2 < i; i2++) {
            array.add(new VertexAttribute(16, 2, ShaderProgram.TEXCOORD_ATTRIBUTE + i2));
        }
        VertexAttribute[] vertexAttributeArr = new VertexAttribute[array.size];
        for (int i3 = 0; i3 < array.size; i3++) {
            vertexAttributeArr[i3] = (VertexAttribute) array.get(i3);
        }
        return vertexAttributeArr;
    }

    public void setShader(ShaderProgram shaderProgram) {
        if (this.ownsShader) {
            this.shader.dispose();
        }
        this.shader = shaderProgram;
        this.ownsShader = false;
    }

    public ShaderProgram getShader() {
        return this.shader;
    }

    @Override // com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer
    public void begin(Matrix4 matrix4, int i) {
        this.projModelView.set(matrix4);
        this.primitiveType = i;
    }

    @Override // com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer
    public void color(Color color) {
        this.vertices[this.vertexIdx + this.colorOffset] = color.toFloatBits();
    }

    @Override // com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer
    public void color(float f, float f2, float f3, float f4) {
        this.vertices[this.vertexIdx + this.colorOffset] = Color.toFloatBits(f, f2, f3, f4);
    }

    @Override // com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer
    public void color(float f) {
        this.vertices[this.vertexIdx + this.colorOffset] = f;
    }

    @Override // com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer
    public void texCoord(float f, float f2) {
        int i = this.vertexIdx + this.texCoordOffset;
        this.vertices[i + this.numSetTexCoords] = f;
        this.vertices[i + this.numSetTexCoords + 1] = f2;
        this.numSetTexCoords += 2;
    }

    @Override // com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer
    public void normal(float f, float f2, float f3) {
        int i = this.vertexIdx + this.normalOffset;
        this.vertices[i] = f;
        this.vertices[i + 1] = f2;
        this.vertices[i + 2] = f3;
    }

    @Override // com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer
    public void vertex(float f, float f2, float f3) {
        int i = this.vertexIdx;
        this.vertices[i] = f;
        this.vertices[i + 1] = f2;
        this.vertices[i + 2] = f3;
        this.numSetTexCoords = 0;
        this.vertexIdx += this.vertexSize;
        this.numVertices++;
    }

    @Override // com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer
    public void flush() {
        if (this.numVertices == 0) {
            return;
        }
        this.shader.bind();
        this.shader.setUniformMatrix("u_projModelView", this.projModelView);
        for (int i = 0; i < this.numTexCoords; i++) {
            this.shader.setUniformi(this.shaderUniformNames[i], i);
        }
        this.mesh.setVertices(this.vertices, 0, this.vertexIdx);
        this.mesh.render(this.shader, this.primitiveType);
        this.numSetTexCoords = 0;
        this.vertexIdx = 0;
        this.numVertices = 0;
    }

    @Override // com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer
    public void end() {
        flush();
    }

    @Override // com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer
    public int getNumVertices() {
        return this.numVertices;
    }

    @Override // com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer
    public int getMaxVertices() {
        return this.maxVertices;
    }

    @Override // com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer
    public void dispose() {
        if (this.ownsShader && this.shader != null) {
            this.shader.dispose();
        }
        this.mesh.dispose();
    }

    private static String createVertexShader(boolean z, boolean z2, int i) {
        String str = "attribute vec4 a_position;\n" + (z ? "attribute vec3 a_normal;\n" : "") + (z2 ? "attribute vec4 a_color;\n" : "");
        for (int i2 = 0; i2 < i; i2++) {
            str = str + "attribute vec2 a_texCoord" + i2 + ";\n";
        }
        String str2 = str + "uniform mat4 u_projModelView;\n" + (z2 ? "varying vec4 v_col;\n" : "");
        for (int i3 = 0; i3 < i; i3++) {
            str2 = str2 + "varying vec2 v_tex" + i3 + ";\n";
        }
        String str3 = str2 + "void main() {\n   gl_Position = u_projModelView * a_position;\n";
        if (z2) {
            str3 = str3 + "   v_col = a_color;\n   v_col.a *= 255.0 / 254.0;\n";
        }
        for (int i4 = 0; i4 < i; i4++) {
            str3 = str3 + "   v_tex" + i4 + " = a_texCoord" + i4 + ";\n";
        }
        return str3 + "   gl_PointSize = 1.0;\n}\n";
    }

    private static String createFragmentShader(boolean z, boolean z2, int i) {
        String str;
        String str2;
        str = "#ifdef GL_ES\nprecision mediump float;\n#endif\n";
        str = z2 ? str + "varying vec4 v_col;\n" : "#ifdef GL_ES\nprecision mediump float;\n#endif\n";
        for (int i2 = 0; i2 < i; i2++) {
            str = (str + "varying vec2 v_tex" + i2 + ";\n") + "uniform sampler2D u_sampler" + i2 + ";\n";
        }
        String str3 = str + "void main() {\n   gl_FragColor = " + (z2 ? "v_col" : "vec4(1, 1, 1, 1)");
        if (i > 0) {
            str3 = str3 + " * ";
        }
        for (int i3 = 0; i3 < i; i3++) {
            if (i3 == i - 1) {
                str2 = str3 + " texture2D(u_sampler" + i3 + ",  v_tex" + i3 + ")";
            } else {
                str2 = str3 + " texture2D(u_sampler" + i3 + ",  v_tex" + i3 + ") *";
            }
            str3 = str2;
        }
        return str3 + ";\n}";
    }

    public static ShaderProgram createDefaultShader(boolean z, boolean z2, int i) {
        ShaderProgram shaderProgram = new ShaderProgram(createVertexShader(z, z2, i), createFragmentShader(z, z2, i));
        if (!shaderProgram.isCompiled()) {
            throw new GdxRuntimeException("Error compiling shader: " + shaderProgram.getLog());
        }
        return shaderProgram;
    }
}
