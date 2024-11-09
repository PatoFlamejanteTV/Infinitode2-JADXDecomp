package com.badlogic.gdx.graphics;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/VertexAttribute.class */
public final class VertexAttribute {
    public final int usage;
    public final int numComponents;
    public final boolean normalized;
    public final int type;
    public int offset;
    public String alias;
    public int unit;
    private final int usageIndex;

    public VertexAttribute(int i, int i2, String str) {
        this(i, i2, str, 0);
    }

    public VertexAttribute(int i, int i2, String str, int i3) {
        this(i, i2, i == 4 ? 5121 : 5126, i == 4, str, i3);
    }

    public VertexAttribute(int i, int i2, int i3, boolean z, String str) {
        this(i, i2, i3, z, str, 0);
    }

    public VertexAttribute(int i, int i2, int i3, boolean z, String str, int i4) {
        this.usage = i;
        this.numComponents = i2;
        this.type = i3;
        this.normalized = z;
        this.alias = str;
        this.unit = i4;
        this.usageIndex = Integer.numberOfTrailingZeros(i);
    }

    public final VertexAttribute copy() {
        return new VertexAttribute(this.usage, this.numComponents, this.type, this.normalized, this.alias, this.unit);
    }

    public static VertexAttribute Position() {
        return new VertexAttribute(1, 3, ShaderProgram.POSITION_ATTRIBUTE);
    }

    public static VertexAttribute TexCoords(int i) {
        return new VertexAttribute(16, 2, ShaderProgram.TEXCOORD_ATTRIBUTE + i, i);
    }

    public static VertexAttribute Normal() {
        return new VertexAttribute(8, 3, ShaderProgram.NORMAL_ATTRIBUTE);
    }

    public static VertexAttribute ColorPacked() {
        return new VertexAttribute(4, 4, 5121, true, ShaderProgram.COLOR_ATTRIBUTE);
    }

    public static VertexAttribute ColorUnpacked() {
        return new VertexAttribute(2, 4, 5126, false, ShaderProgram.COLOR_ATTRIBUTE);
    }

    public static VertexAttribute Tangent() {
        return new VertexAttribute(128, 3, ShaderProgram.TANGENT_ATTRIBUTE);
    }

    public static VertexAttribute Binormal() {
        return new VertexAttribute(256, 3, ShaderProgram.BINORMAL_ATTRIBUTE);
    }

    public static VertexAttribute BoneWeight(int i) {
        return new VertexAttribute(64, 2, ShaderProgram.BONEWEIGHT_ATTRIBUTE + i, i);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof VertexAttribute)) {
            return false;
        }
        return equals((VertexAttribute) obj);
    }

    public final boolean equals(VertexAttribute vertexAttribute) {
        return vertexAttribute != null && this.usage == vertexAttribute.usage && this.numComponents == vertexAttribute.numComponents && this.type == vertexAttribute.type && this.normalized == vertexAttribute.normalized && this.alias.equals(vertexAttribute.alias) && this.unit == vertexAttribute.unit;
    }

    public final int getKey() {
        return (this.usageIndex << 8) + (this.unit & 255);
    }

    public final int getSizeInBytes() {
        switch (this.type) {
            case 5120:
            case 5121:
                return this.numComponents;
            case 5122:
            case 5123:
                return 2 * this.numComponents;
            case 5124:
            case 5125:
            case 5127:
            case 5128:
            case 5129:
            case 5130:
            case 5131:
            default:
                return 0;
            case 5126:
            case 5132:
                return 4 * this.numComponents;
        }
    }

    public final int hashCode() {
        return (((getKey() * 541) + this.numComponents) * 541) + this.alias.hashCode();
    }
}
