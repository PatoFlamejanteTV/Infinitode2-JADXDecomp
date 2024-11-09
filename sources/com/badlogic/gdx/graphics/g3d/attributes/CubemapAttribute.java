package com.badlogic.gdx.graphics.g3d.attributes;

import com.badlogic.gdx.graphics.Cubemap;
import com.badlogic.gdx.graphics.g3d.Attribute;
import com.badlogic.gdx.graphics.g3d.utils.TextureDescriptor;
import com.badlogic.gdx.utils.GdxRuntimeException;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/attributes/CubemapAttribute.class */
public class CubemapAttribute extends Attribute {
    public static final String EnvironmentMapAlias = "environmentCubemap";
    public static final long EnvironmentMap;
    protected static long Mask;
    public final TextureDescriptor<Cubemap> textureDescription;

    static {
        long register = register(EnvironmentMapAlias);
        EnvironmentMap = register;
        Mask = register;
    }

    public static final boolean is(long j) {
        return (j & Mask) != 0;
    }

    public CubemapAttribute(long j) {
        super(j);
        if (!is(j)) {
            throw new GdxRuntimeException("Invalid type specified");
        }
        this.textureDescription = new TextureDescriptor<>();
    }

    public <T extends Cubemap> CubemapAttribute(long j, TextureDescriptor<T> textureDescriptor) {
        this(j);
        this.textureDescription.set(textureDescriptor);
    }

    public CubemapAttribute(long j, Cubemap cubemap) {
        this(j);
        this.textureDescription.texture = cubemap;
    }

    public CubemapAttribute(CubemapAttribute cubemapAttribute) {
        this(cubemapAttribute.type, cubemapAttribute.textureDescription);
    }

    @Override // com.badlogic.gdx.graphics.g3d.Attribute
    public Attribute copy() {
        return new CubemapAttribute(this);
    }

    @Override // com.badlogic.gdx.graphics.g3d.Attribute
    public int hashCode() {
        return (super.hashCode() * 967) + this.textureDescription.hashCode();
    }

    @Override // java.lang.Comparable
    public int compareTo(Attribute attribute) {
        return this.type != attribute.type ? (int) (this.type - attribute.type) : this.textureDescription.compareTo(((CubemapAttribute) attribute).textureDescription);
    }
}
