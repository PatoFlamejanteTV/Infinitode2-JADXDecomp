package com.badlogic.gdx.graphics.g3d.particles.values;

import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.particles.values.MeshSpawnShapeValue;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/values/UnweightedMeshSpawnShapeValue.class */
public final class UnweightedMeshSpawnShapeValue extends MeshSpawnShapeValue {
    private float[] vertices;
    private short[] indices;
    private int positionOffset;
    private int vertexSize;
    private int vertexCount;
    private int triangleCount;

    public UnweightedMeshSpawnShapeValue(UnweightedMeshSpawnShapeValue unweightedMeshSpawnShapeValue) {
        super(unweightedMeshSpawnShapeValue);
        load(unweightedMeshSpawnShapeValue);
    }

    public UnweightedMeshSpawnShapeValue() {
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.MeshSpawnShapeValue
    public final void setMesh(Mesh mesh, Model model) {
        super.setMesh(mesh, model);
        this.vertexSize = mesh.getVertexSize() / 4;
        this.positionOffset = mesh.getVertexAttribute(1).offset / 4;
        int numIndices = mesh.getNumIndices();
        if (numIndices > 0) {
            this.indices = new short[numIndices];
            mesh.getIndices(this.indices);
            this.triangleCount = this.indices.length / 3;
        } else {
            this.indices = null;
        }
        this.vertexCount = mesh.getNumVertices();
        this.vertices = new float[this.vertexCount * this.vertexSize];
        mesh.getVertices(this.vertices);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public final void spawnAux(Vector3 vector3, float f) {
        if (this.indices == null) {
            int random = (MathUtils.random(this.vertexCount - 3) * this.vertexSize) + this.positionOffset;
            int i = random + this.vertexSize;
            int i2 = i + this.vertexSize;
            MeshSpawnShapeValue.Triangle.pick(this.vertices[random], this.vertices[random + 1], this.vertices[random + 2], this.vertices[i], this.vertices[i + 1], this.vertices[i + 2], this.vertices[i2], this.vertices[i2 + 1], this.vertices[i2 + 2], vector3);
            return;
        }
        int random2 = MathUtils.random(this.triangleCount - 1) * 3;
        int i3 = (this.indices[random2] * this.vertexSize) + this.positionOffset;
        int i4 = (this.indices[random2 + 1] * this.vertexSize) + this.positionOffset;
        int i5 = (this.indices[random2 + 2] * this.vertexSize) + this.positionOffset;
        MeshSpawnShapeValue.Triangle.pick(this.vertices[i3], this.vertices[i3 + 1], this.vertices[i3 + 2], this.vertices[i4], this.vertices[i4 + 1], this.vertices[i4 + 2], this.vertices[i5], this.vertices[i5 + 1], this.vertices[i5 + 2], vector3);
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public final SpawnShapeValue copy() {
        return new UnweightedMeshSpawnShapeValue(this);
    }
}
