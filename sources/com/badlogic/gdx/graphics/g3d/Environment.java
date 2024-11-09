package com.badlogic.gdx.graphics.g3d;

import com.badlogic.gdx.graphics.g3d.attributes.DirectionalLightsAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.PointLightsAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.SpotLightsAttribute;
import com.badlogic.gdx.graphics.g3d.environment.BaseLight;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.g3d.environment.ShadowMap;
import com.badlogic.gdx.graphics.g3d.environment.SpotLight;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/Environment.class */
public class Environment extends Attributes {
    public ShadowMap shadowMap;

    public Environment add(BaseLight... baseLightArr) {
        for (BaseLight baseLight : baseLightArr) {
            add(baseLight);
        }
        return this;
    }

    public Environment add(Array<BaseLight> array) {
        Array.ArrayIterator<BaseLight> it = array.iterator();
        while (it.hasNext()) {
            add(it.next());
        }
        return this;
    }

    public Environment add(BaseLight baseLight) {
        if (baseLight instanceof DirectionalLight) {
            add((DirectionalLight) baseLight);
        } else if (baseLight instanceof PointLight) {
            add((PointLight) baseLight);
        } else if (baseLight instanceof SpotLight) {
            add((SpotLight) baseLight);
        } else {
            throw new GdxRuntimeException("Unknown light type");
        }
        return this;
    }

    public Environment add(DirectionalLight directionalLight) {
        DirectionalLightsAttribute directionalLightsAttribute = (DirectionalLightsAttribute) get(DirectionalLightsAttribute.Type);
        DirectionalLightsAttribute directionalLightsAttribute2 = directionalLightsAttribute;
        if (directionalLightsAttribute == null) {
            DirectionalLightsAttribute directionalLightsAttribute3 = new DirectionalLightsAttribute();
            directionalLightsAttribute2 = directionalLightsAttribute3;
            set(directionalLightsAttribute3);
        }
        directionalLightsAttribute2.lights.add(directionalLight);
        return this;
    }

    public Environment add(PointLight pointLight) {
        PointLightsAttribute pointLightsAttribute = (PointLightsAttribute) get(PointLightsAttribute.Type);
        PointLightsAttribute pointLightsAttribute2 = pointLightsAttribute;
        if (pointLightsAttribute == null) {
            PointLightsAttribute pointLightsAttribute3 = new PointLightsAttribute();
            pointLightsAttribute2 = pointLightsAttribute3;
            set(pointLightsAttribute3);
        }
        pointLightsAttribute2.lights.add(pointLight);
        return this;
    }

    public Environment add(SpotLight spotLight) {
        SpotLightsAttribute spotLightsAttribute = (SpotLightsAttribute) get(SpotLightsAttribute.Type);
        SpotLightsAttribute spotLightsAttribute2 = spotLightsAttribute;
        if (spotLightsAttribute == null) {
            SpotLightsAttribute spotLightsAttribute3 = new SpotLightsAttribute();
            spotLightsAttribute2 = spotLightsAttribute3;
            set(spotLightsAttribute3);
        }
        spotLightsAttribute2.lights.add(spotLight);
        return this;
    }

    public Environment remove(BaseLight... baseLightArr) {
        for (BaseLight baseLight : baseLightArr) {
            remove(baseLight);
        }
        return this;
    }

    public Environment remove(Array<BaseLight> array) {
        Array.ArrayIterator<BaseLight> it = array.iterator();
        while (it.hasNext()) {
            remove(it.next());
        }
        return this;
    }

    public Environment remove(BaseLight baseLight) {
        if (baseLight instanceof DirectionalLight) {
            remove((DirectionalLight) baseLight);
        } else if (baseLight instanceof PointLight) {
            remove((PointLight) baseLight);
        } else if (baseLight instanceof SpotLight) {
            remove((SpotLight) baseLight);
        } else {
            throw new GdxRuntimeException("Unknown light type");
        }
        return this;
    }

    public Environment remove(DirectionalLight directionalLight) {
        if (has(DirectionalLightsAttribute.Type)) {
            DirectionalLightsAttribute directionalLightsAttribute = (DirectionalLightsAttribute) get(DirectionalLightsAttribute.Type);
            directionalLightsAttribute.lights.removeValue(directionalLight, false);
            if (directionalLightsAttribute.lights.size == 0) {
                remove(DirectionalLightsAttribute.Type);
            }
        }
        return this;
    }

    public Environment remove(PointLight pointLight) {
        if (has(PointLightsAttribute.Type)) {
            PointLightsAttribute pointLightsAttribute = (PointLightsAttribute) get(PointLightsAttribute.Type);
            pointLightsAttribute.lights.removeValue(pointLight, false);
            if (pointLightsAttribute.lights.size == 0) {
                remove(PointLightsAttribute.Type);
            }
        }
        return this;
    }

    public Environment remove(SpotLight spotLight) {
        if (has(SpotLightsAttribute.Type)) {
            SpotLightsAttribute spotLightsAttribute = (SpotLightsAttribute) get(SpotLightsAttribute.Type);
            spotLightsAttribute.lights.removeValue(spotLight, false);
            if (spotLightsAttribute.lights.size == 0) {
                remove(SpotLightsAttribute.Type);
            }
        }
        return this;
    }
}
