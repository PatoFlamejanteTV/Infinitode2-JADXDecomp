package com.badlogic.gdx.graphics.g3d.attributes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Attribute;
import com.badlogic.gdx.graphics.g3d.utils.TextureDescriptor;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.NumberUtils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/attributes/TextureAttribute.class */
public class TextureAttribute extends Attribute {
    public static final String DiffuseAlias = "diffuseTexture";
    public static final long Diffuse = register(DiffuseAlias);
    public static final String SpecularAlias = "specularTexture";
    public static final long Specular = register(SpecularAlias);
    public static final String BumpAlias = "bumpTexture";
    public static final long Bump = register(BumpAlias);
    public static final String NormalAlias = "normalTexture";
    public static final long Normal = register(NormalAlias);
    public static final String AmbientAlias = "ambientTexture";
    public static final long Ambient = register(AmbientAlias);
    public static final String EmissiveAlias = "emissiveTexture";
    public static final long Emissive = register(EmissiveAlias);
    public static final String ReflectionAlias = "reflectionTexture";
    public static final long Reflection = register(ReflectionAlias);
    protected static long Mask = (((((Diffuse | Specular) | Bump) | Normal) | Ambient) | Emissive) | Reflection;
    public final TextureDescriptor<Texture> textureDescription;
    public float offsetU;
    public float offsetV;
    public float scaleU;
    public float scaleV;
    public int uvIndex;

    public static final boolean is(long j) {
        return (j & Mask) != 0;
    }

    public static TextureAttribute createDiffuse(Texture texture) {
        return new TextureAttribute(Diffuse, texture);
    }

    public static TextureAttribute createDiffuse(TextureRegion textureRegion) {
        return new TextureAttribute(Diffuse, textureRegion);
    }

    public static TextureAttribute createSpecular(Texture texture) {
        return new TextureAttribute(Specular, texture);
    }

    public static TextureAttribute createSpecular(TextureRegion textureRegion) {
        return new TextureAttribute(Specular, textureRegion);
    }

    public static TextureAttribute createNormal(Texture texture) {
        return new TextureAttribute(Normal, texture);
    }

    public static TextureAttribute createNormal(TextureRegion textureRegion) {
        return new TextureAttribute(Normal, textureRegion);
    }

    public static TextureAttribute createBump(Texture texture) {
        return new TextureAttribute(Bump, texture);
    }

    public static TextureAttribute createBump(TextureRegion textureRegion) {
        return new TextureAttribute(Bump, textureRegion);
    }

    public static TextureAttribute createAmbient(Texture texture) {
        return new TextureAttribute(Ambient, texture);
    }

    public static TextureAttribute createAmbient(TextureRegion textureRegion) {
        return new TextureAttribute(Ambient, textureRegion);
    }

    public static TextureAttribute createEmissive(Texture texture) {
        return new TextureAttribute(Emissive, texture);
    }

    public static TextureAttribute createEmissive(TextureRegion textureRegion) {
        return new TextureAttribute(Emissive, textureRegion);
    }

    public static TextureAttribute createReflection(Texture texture) {
        return new TextureAttribute(Reflection, texture);
    }

    public static TextureAttribute createReflection(TextureRegion textureRegion) {
        return new TextureAttribute(Reflection, textureRegion);
    }

    public TextureAttribute(long j) {
        super(j);
        this.offsetU = 0.0f;
        this.offsetV = 0.0f;
        this.scaleU = 1.0f;
        this.scaleV = 1.0f;
        this.uvIndex = 0;
        if (!is(j)) {
            throw new GdxRuntimeException("Invalid type specified");
        }
        this.textureDescription = new TextureDescriptor<>();
    }

    public <T extends Texture> TextureAttribute(long j, TextureDescriptor<T> textureDescriptor) {
        this(j);
        this.textureDescription.set(textureDescriptor);
    }

    public <T extends Texture> TextureAttribute(long j, TextureDescriptor<T> textureDescriptor, float f, float f2, float f3, float f4, int i) {
        this(j, textureDescriptor);
        this.offsetU = f;
        this.offsetV = f2;
        this.scaleU = f3;
        this.scaleV = f4;
        this.uvIndex = i;
    }

    public <T extends Texture> TextureAttribute(long j, TextureDescriptor<T> textureDescriptor, float f, float f2, float f3, float f4) {
        this(j, textureDescriptor, f, f2, f3, f4, 0);
    }

    public TextureAttribute(long j, Texture texture) {
        this(j);
        this.textureDescription.texture = texture;
    }

    public TextureAttribute(long j, TextureRegion textureRegion) {
        this(j);
        set(textureRegion);
    }

    public TextureAttribute(TextureAttribute textureAttribute) {
        this(textureAttribute.type, textureAttribute.textureDescription, textureAttribute.offsetU, textureAttribute.offsetV, textureAttribute.scaleU, textureAttribute.scaleV, textureAttribute.uvIndex);
    }

    public void set(TextureRegion textureRegion) {
        this.textureDescription.texture = textureRegion.getTexture();
        this.offsetU = textureRegion.getU();
        this.offsetV = textureRegion.getV();
        this.scaleU = textureRegion.getU2() - this.offsetU;
        this.scaleV = textureRegion.getV2() - this.offsetV;
    }

    @Override // com.badlogic.gdx.graphics.g3d.Attribute
    public Attribute copy() {
        return new TextureAttribute(this);
    }

    @Override // com.badlogic.gdx.graphics.g3d.Attribute
    public int hashCode() {
        return (((((((((((super.hashCode() * 991) + this.textureDescription.hashCode()) * 991) + NumberUtils.floatToRawIntBits(this.offsetU)) * 991) + NumberUtils.floatToRawIntBits(this.offsetV)) * 991) + NumberUtils.floatToRawIntBits(this.scaleU)) * 991) + NumberUtils.floatToRawIntBits(this.scaleV)) * 991) + this.uvIndex;
    }

    @Override // java.lang.Comparable
    public int compareTo(Attribute attribute) {
        if (this.type != attribute.type) {
            return this.type < attribute.type ? -1 : 1;
        }
        TextureAttribute textureAttribute = (TextureAttribute) attribute;
        int compareTo = this.textureDescription.compareTo(textureAttribute.textureDescription);
        if (compareTo != 0) {
            return compareTo;
        }
        if (this.uvIndex != textureAttribute.uvIndex) {
            return this.uvIndex - textureAttribute.uvIndex;
        }
        if (!MathUtils.isEqual(this.scaleU, textureAttribute.scaleU)) {
            return this.scaleU > textureAttribute.scaleU ? 1 : -1;
        }
        if (!MathUtils.isEqual(this.scaleV, textureAttribute.scaleV)) {
            return this.scaleV > textureAttribute.scaleV ? 1 : -1;
        }
        if (!MathUtils.isEqual(this.offsetU, textureAttribute.offsetU)) {
            return this.offsetU > textureAttribute.offsetU ? 1 : -1;
        }
        if (MathUtils.isEqual(this.offsetV, textureAttribute.offsetV)) {
            return 0;
        }
        return this.offsetV > textureAttribute.offsetV ? 1 : -1;
    }
}
