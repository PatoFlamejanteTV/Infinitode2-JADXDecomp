package com.badlogic.gdx.graphics.g3d.attributes;

import com.badlogic.gdx.graphics.g3d.Attribute;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.NumberUtils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/attributes/FloatAttribute.class */
public class FloatAttribute extends Attribute {
    public static final String ShininessAlias = "shininess";
    public static final long Shininess = register(ShininessAlias);
    public static final String AlphaTestAlias = "alphaTest";
    public static final long AlphaTest = register(AlphaTestAlias);
    public float value;

    public static FloatAttribute createShininess(float f) {
        return new FloatAttribute(Shininess, f);
    }

    public static FloatAttribute createAlphaTest(float f) {
        return new FloatAttribute(AlphaTest, f);
    }

    public FloatAttribute(long j) {
        super(j);
    }

    public FloatAttribute(long j, float f) {
        super(j);
        this.value = f;
    }

    @Override // com.badlogic.gdx.graphics.g3d.Attribute
    public Attribute copy() {
        return new FloatAttribute(this.type, this.value);
    }

    @Override // com.badlogic.gdx.graphics.g3d.Attribute
    public int hashCode() {
        return (super.hashCode() * 977) + NumberUtils.floatToRawIntBits(this.value);
    }

    @Override // java.lang.Comparable
    public int compareTo(Attribute attribute) {
        if (this.type != attribute.type) {
            return (int) (this.type - attribute.type);
        }
        float f = ((FloatAttribute) attribute).value;
        if (MathUtils.isEqual(this.value, f)) {
            return 0;
        }
        return this.value < f ? -1 : 1;
    }
}
