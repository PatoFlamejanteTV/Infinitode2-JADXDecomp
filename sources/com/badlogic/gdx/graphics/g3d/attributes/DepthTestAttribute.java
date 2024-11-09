package com.badlogic.gdx.graphics.g3d.attributes;

import com.badlogic.gdx.graphics.g3d.Attribute;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.NumberUtils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/attributes/DepthTestAttribute.class */
public class DepthTestAttribute extends Attribute {
    public static final String Alias = "depthStencil";
    public static final long Type;
    protected static long Mask;
    public int depthFunc;
    public float depthRangeNear;
    public float depthRangeFar;
    public boolean depthMask;

    static {
        long register = register(Alias);
        Type = register;
        Mask = register;
    }

    public static final boolean is(long j) {
        return (j & Mask) != 0;
    }

    public DepthTestAttribute() {
        this(515);
    }

    public DepthTestAttribute(boolean z) {
        this(515, z);
    }

    public DepthTestAttribute(int i) {
        this(i, true);
    }

    public DepthTestAttribute(int i, boolean z) {
        this(i, 0.0f, 1.0f, z);
    }

    public DepthTestAttribute(int i, float f, float f2) {
        this(i, f, f2, true);
    }

    public DepthTestAttribute(int i, float f, float f2, boolean z) {
        this(Type, i, f, f2, z);
    }

    public DepthTestAttribute(long j, int i, float f, float f2, boolean z) {
        super(j);
        if (!is(j)) {
            throw new GdxRuntimeException("Invalid type specified");
        }
        this.depthFunc = i;
        this.depthRangeNear = f;
        this.depthRangeFar = f2;
        this.depthMask = z;
    }

    public DepthTestAttribute(DepthTestAttribute depthTestAttribute) {
        this(depthTestAttribute.type, depthTestAttribute.depthFunc, depthTestAttribute.depthRangeNear, depthTestAttribute.depthRangeFar, depthTestAttribute.depthMask);
    }

    @Override // com.badlogic.gdx.graphics.g3d.Attribute
    public Attribute copy() {
        return new DepthTestAttribute(this);
    }

    @Override // com.badlogic.gdx.graphics.g3d.Attribute
    public int hashCode() {
        return (((((((super.hashCode() * 971) + this.depthFunc) * 971) + NumberUtils.floatToRawIntBits(this.depthRangeNear)) * 971) + NumberUtils.floatToRawIntBits(this.depthRangeFar)) * 971) + (this.depthMask ? 1 : 0);
    }

    @Override // java.lang.Comparable
    public int compareTo(Attribute attribute) {
        if (this.type != attribute.type) {
            return (int) (this.type - attribute.type);
        }
        DepthTestAttribute depthTestAttribute = (DepthTestAttribute) attribute;
        if (this.depthFunc != depthTestAttribute.depthFunc) {
            return this.depthFunc - depthTestAttribute.depthFunc;
        }
        if (this.depthMask != depthTestAttribute.depthMask) {
            return this.depthMask ? -1 : 1;
        }
        if (!MathUtils.isEqual(this.depthRangeNear, depthTestAttribute.depthRangeNear)) {
            return this.depthRangeNear < depthTestAttribute.depthRangeNear ? -1 : 1;
        }
        if (MathUtils.isEqual(this.depthRangeFar, depthTestAttribute.depthRangeFar)) {
            return 0;
        }
        return this.depthRangeFar < depthTestAttribute.depthRangeFar ? -1 : 1;
    }
}
