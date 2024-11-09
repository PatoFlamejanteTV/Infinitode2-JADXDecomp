package com.badlogic.gdx.graphics.g3d.particles.values;

import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.particles.values.MeshSpawnShapeValue;
import com.badlogic.gdx.math.CumulativeDistribution;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/values/WeightMeshSpawnShapeValue.class */
public final class WeightMeshSpawnShapeValue extends MeshSpawnShapeValue {
    private CumulativeDistribution<MeshSpawnShapeValue.Triangle> distribution;

    public WeightMeshSpawnShapeValue(WeightMeshSpawnShapeValue weightMeshSpawnShapeValue) {
        super(weightMeshSpawnShapeValue);
        this.distribution = new CumulativeDistribution<>();
        load(weightMeshSpawnShapeValue);
    }

    public WeightMeshSpawnShapeValue() {
        this.distribution = new CumulativeDistribution<>();
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public final void init() {
        calculateWeights();
    }

    public final void calculateWeights() {
        this.distribution.clear();
        VertexAttributes vertexAttributes = this.mesh.getVertexAttributes();
        int numIndices = this.mesh.getNumIndices();
        int numVertices = this.mesh.getNumVertices();
        short s = (short) (vertexAttributes.vertexSize / 4);
        short s2 = (short) (vertexAttributes.findByUsage(1).offset / 4);
        float[] fArr = new float[numVertices * s];
        this.mesh.getVertices(fArr);
        if (numIndices > 0) {
            short[] sArr = new short[numIndices];
            this.mesh.getIndices(sArr);
            for (int i = 0; i < numIndices; i += 3) {
                int i2 = (sArr[i] * s) + s2;
                int i3 = (sArr[i + 1] * s) + s2;
                int i4 = (sArr[i + 2] * s) + s2;
                float f = fArr[i2];
                float f2 = fArr[i2 + 1];
                float f3 = fArr[i2 + 2];
                float f4 = fArr[i3];
                float f5 = fArr[i3 + 1];
                float f6 = fArr[i3 + 2];
                float f7 = fArr[i4];
                float f8 = fArr[i4 + 1];
                this.distribution.add(new MeshSpawnShapeValue.Triangle(f, f2, f3, f4, f5, f6, f7, f8, fArr[i4 + 2]), Math.abs((((f * (f5 - f8)) + (f4 * (f8 - f2))) + (f7 * (f2 - f5))) / 2.0f));
            }
        } else {
            int i5 = 0;
            while (true) {
                int i6 = i5;
                if (i6 >= numVertices) {
                    break;
                }
                int i7 = i6 + s2;
                int i8 = i7 + s;
                int i9 = i8 + s;
                float f9 = fArr[i7];
                float f10 = fArr[i7 + 1];
                float f11 = fArr[i7 + 2];
                float f12 = fArr[i8];
                float f13 = fArr[i8 + 1];
                float f14 = fArr[i8 + 2];
                float f15 = fArr[i9];
                float f16 = fArr[i9 + 1];
                this.distribution.add(new MeshSpawnShapeValue.Triangle(f9, f10, f11, f12, f13, f14, f15, f16, fArr[i9 + 2]), Math.abs((((f9 * (f13 - f16)) + (f12 * (f16 - f10))) + (f15 * (f10 - f13))) / 2.0f));
                i5 = i6 + s;
            }
        }
        this.distribution.generateNormalized();
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public final void spawnAux(Vector3 vector3, float f) {
        MeshSpawnShapeValue.Triangle value = this.distribution.value();
        float random = MathUtils.random();
        float random2 = MathUtils.random();
        vector3.set(value.x1 + (random * (value.x2 - value.x1)) + (random2 * (value.x3 - value.x1)), value.y1 + (random * (value.y2 - value.y1)) + (random2 * (value.y3 - value.y1)), value.z1 + (random * (value.z2 - value.z1)) + (random2 * (value.z3 - value.z1)));
    }

    @Override // com.badlogic.gdx.graphics.g3d.particles.values.SpawnShapeValue
    public final SpawnShapeValue copy() {
        return new WeightMeshSpawnShapeValue(this);
    }
}
