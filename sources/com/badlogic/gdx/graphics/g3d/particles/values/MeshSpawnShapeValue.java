package com.badlogic.gdx.graphics.g3d.particles.values;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.particles.ResourceData;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.GdxRuntimeException;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/values/MeshSpawnShapeValue.class */
public abstract class MeshSpawnShapeValue extends SpawnShapeValue {
    protected Mesh mesh;
    protected Model model;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/values/MeshSpawnShapeValue$Triangle.class */
    public static class Triangle {
        float x1;
        float y1;
        float z1;
        float x2;
        float y2;
        float z2;
        float x3;
        float y3;
        float z3;

        public Triangle(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
            this.x1 = f;
            this.y1 = f2;
            this.z1 = f3;
            this.x2 = f4;
            this.y2 = f5;
            this.z2 = f6;
            this.x3 = f7;
            this.y3 = f8;
            this.z3 = f9;
        }

        public static Vector3 pick(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, Vector3 vector3) {
            float random = MathUtils.random();
            float random2 = MathUtils.random();
            return vector3.set(f + (random * (f4 - f)) + (random2 * (f7 - f)), f2 + (random * (f5 - f2)) + (random2 * (f8 - f2)), f3 + (random * (f6 - f3)) + (random2 * (f9 - f3)));
        }

        public Vector3 pick(Vector3 vector3) {
            float random = MathUtils.random();
            float random2 = MathUtils.random();
            return vector3.set(this.x1 + (random * (this.x2 - this.x1)) + (random2 * (this.x3 - this.x1)), this.y1 + (random * (this.y2 - this.y1)) + (random2 * (this.y3 - this.y1)), this.z1 + (random * (this.z2 - this.z1)) + (random2 * (this.z3 - this.z1)));
        }
    }

    public MeshSpawnShapeValue(MeshSpawnShapeValue meshSpawnShapeValue) {
        super(meshSpawnShapeValue);
    }

    public MeshSpawnShapeValue() {
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue, com.badlogic.gdx.graphics.g3d.particles.values.ParticleValue
    public void load(ParticleValue particleValue) {
        super.load(particleValue);
        MeshSpawnShapeValue meshSpawnShapeValue = (MeshSpawnShapeValue) particleValue;
        setMesh(meshSpawnShapeValue.mesh, meshSpawnShapeValue.model);
    }

    public void setMesh(Mesh mesh, Model model) {
        if (mesh.getVertexAttribute(1) == null) {
            throw new GdxRuntimeException("Mesh vertices must have Usage.Position");
        }
        this.model = model;
        this.mesh = mesh;
    }

    public void setMesh(Mesh mesh) {
        setMesh(mesh, null);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue, com.badlogic.gdx.graphics.g3d.particles.ResourceData.Configurable
    public void save(AssetManager assetManager, ResourceData resourceData) {
        if (this.model != null) {
            ResourceData.SaveData createSaveData = resourceData.createSaveData();
            createSaveData.saveAsset(assetManager.getAssetFileName(this.model), Model.class);
            createSaveData.save("index", Integer.valueOf(this.model.meshes.indexOf(this.mesh, true)));
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue, com.badlogic.gdx.graphics.g3d.particles.ResourceData.Configurable
    public void load(AssetManager assetManager, ResourceData resourceData) {
        ResourceData.SaveData saveData = resourceData.getSaveData();
        AssetDescriptor loadAsset = saveData.loadAsset();
        if (loadAsset != null) {
            Model model = (Model) assetManager.get(loadAsset);
            setMesh(model.meshes.get(((Integer) saveData.load("index")).intValue()), model);
        }
    }
}
