package com.badlogic.gdx.graphics.g3d.attributes;

import com.badlogic.gdx.graphics.g3d.Attribute;
import com.badlogic.gdx.graphics.g3d.environment.SpotLight;
import com.badlogic.gdx.utils.Array;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/attributes/SpotLightsAttribute.class */
public class SpotLightsAttribute extends Attribute {
    public static final String Alias = "spotLights";
    public static final long Type = register(Alias);
    public final Array<SpotLight> lights;

    public static final boolean is(long j) {
        return (j & Type) == j;
    }

    public SpotLightsAttribute() {
        super(Type);
        this.lights = new Array<>(1);
    }

    public SpotLightsAttribute(SpotLightsAttribute spotLightsAttribute) {
        this();
        this.lights.addAll(spotLightsAttribute.lights);
    }

    @Override // com.badlogic.gdx.graphics.g3d.Attribute
    public SpotLightsAttribute copy() {
        return new SpotLightsAttribute(this);
    }

    @Override // com.badlogic.gdx.graphics.g3d.Attribute
    public int hashCode() {
        int hashCode = super.hashCode();
        Array.ArrayIterator<SpotLight> it = this.lights.iterator();
        while (it.hasNext()) {
            SpotLight next = it.next();
            hashCode = (hashCode * 1237) + (next == null ? 0 : next.hashCode());
        }
        return hashCode;
    }

    @Override // java.lang.Comparable
    public int compareTo(Attribute attribute) {
        if (this.type != attribute.type) {
            return this.type < attribute.type ? -1 : 1;
        }
        return 0;
    }
}
