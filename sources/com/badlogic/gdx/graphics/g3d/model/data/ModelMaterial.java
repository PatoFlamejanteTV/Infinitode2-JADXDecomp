package com.badlogic.gdx.graphics.g3d.model.data;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/model/data/ModelMaterial.class */
public class ModelMaterial {
    public String id;
    public MaterialType type;
    public Color ambient;
    public Color diffuse;
    public Color specular;
    public Color emissive;
    public Color reflection;
    public float shininess;
    public float opacity = 1.0f;
    public Array<ModelTexture> textures;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/model/data/ModelMaterial$MaterialType.class */
    public enum MaterialType {
        Lambert,
        Phong
    }
}
