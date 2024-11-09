package com.badlogic.gdx.graphics.g3d.attributes;

import com.badlogic.gdx.graphics.g3d.Attribute;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.NumberUtils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/attributes/BlendingAttribute.class */
public class BlendingAttribute extends Attribute {
    public static final String Alias = "blended";
    public static final long Type = register(Alias);
    public boolean blended;
    public int sourceFunction;
    public int destFunction;
    public float opacity;

    public static final boolean is(long j) {
        return (j & Type) == j;
    }

    public BlendingAttribute() {
        this((BlendingAttribute) null);
    }

    public BlendingAttribute(boolean z, int i, int i2, float f) {
        super(Type);
        this.opacity = 1.0f;
        this.blended = z;
        this.sourceFunction = i;
        this.destFunction = i2;
        this.opacity = f;
    }

    public BlendingAttribute(int i, int i2, float f) {
        this(true, i, i2, f);
    }

    public BlendingAttribute(int i, int i2) {
        this(i, i2, 1.0f);
    }

    public BlendingAttribute(boolean z, float f) {
        this(z, 770, 771, f);
    }

    public BlendingAttribute(float f) {
        this(true, f);
    }

    public BlendingAttribute(BlendingAttribute blendingAttribute) {
        this(blendingAttribute == null || blendingAttribute.blended, blendingAttribute == null ? 770 : blendingAttribute.sourceFunction, blendingAttribute == null ? 771 : blendingAttribute.destFunction, blendingAttribute == null ? 1.0f : blendingAttribute.opacity);
    }

    @Override // com.badlogic.gdx.graphics.g3d.Attribute
    public BlendingAttribute copy() {
        return new BlendingAttribute(this);
    }

    @Override // com.badlogic.gdx.graphics.g3d.Attribute
    public int hashCode() {
        return (((((((super.hashCode() * 947) + (this.blended ? 1 : 0)) * 947) + this.sourceFunction) * 947) + this.destFunction) * 947) + NumberUtils.floatToRawIntBits(this.opacity);
    }

    @Override // java.lang.Comparable
    public int compareTo(Attribute attribute) {
        if (this.type != attribute.type) {
            return (int) (this.type - attribute.type);
        }
        BlendingAttribute blendingAttribute = (BlendingAttribute) attribute;
        if (this.blended != blendingAttribute.blended) {
            return this.blended ? 1 : -1;
        }
        if (this.sourceFunction != blendingAttribute.sourceFunction) {
            return this.sourceFunction - blendingAttribute.sourceFunction;
        }
        if (this.destFunction != blendingAttribute.destFunction) {
            return this.destFunction - blendingAttribute.destFunction;
        }
        if (MathUtils.isEqual(this.opacity, blendingAttribute.opacity)) {
            return 0;
        }
        return this.opacity < blendingAttribute.opacity ? 1 : -1;
    }
}
