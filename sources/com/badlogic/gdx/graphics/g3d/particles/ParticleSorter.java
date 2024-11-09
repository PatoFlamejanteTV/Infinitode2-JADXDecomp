package com.badlogic.gdx.graphics.g3d.particles;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.particles.renderers.ParticleControllerRenderData;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/ParticleSorter.class */
public abstract class ParticleSorter {
    static final Vector3 TMP_V1 = new Vector3();
    protected Camera camera;

    public abstract <T extends ParticleControllerRenderData> int[] sort(Array<T> array);

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/ParticleSorter$None.class */
    public static class None extends ParticleSorter {
        int currentCapacity = 0;
        int[] indices;

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleSorter
        public void ensureCapacity(int i) {
            if (this.currentCapacity < i) {
                this.indices = new int[i];
                for (int i2 = 0; i2 < i; i2++) {
                    int i3 = i2;
                    this.indices[i3] = i3;
                }
                this.currentCapacity = i;
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleSorter
        public <T extends ParticleControllerRenderData> int[] sort(Array<T> array) {
            return this.indices;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/particles/ParticleSorter$Distance.class */
    public static class Distance extends ParticleSorter {
        private float[] distances;
        private int[] particleIndices;
        private int[] particleOffsets;
        private int currentSize = 0;

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleSorter
        public void ensureCapacity(int i) {
            if (this.currentSize < i) {
                this.distances = new float[i];
                this.particleIndices = new int[i];
                this.particleOffsets = new int[i];
                this.currentSize = i;
            }
        }

        @Override // com.badlogic.gdx.graphics.g3d.particles.ParticleSorter
        public <T extends ParticleControllerRenderData> int[] sort(Array<T> array) {
            float[] fArr = this.camera.view.val;
            float f = fArr[2];
            float f2 = fArr[6];
            float f3 = fArr[10];
            int i = 0;
            int i2 = 0;
            Array.ArrayIterator<T> it = array.iterator();
            while (it.hasNext()) {
                T next = it.next();
                int i3 = 0;
                int i4 = i2 + next.controller.particles.size;
                while (i2 < i4) {
                    this.distances[i2] = (f * next.positionChannel.data[i3]) + (f2 * next.positionChannel.data[i3 + 1]) + (f3 * next.positionChannel.data[i3 + 2]);
                    int i5 = i2;
                    this.particleIndices[i5] = i5;
                    i2++;
                    i3 += next.positionChannel.strideSize;
                }
                i += next.controller.particles.size;
            }
            qsort(0, i - 1);
            for (int i6 = 0; i6 < i; i6++) {
                this.particleOffsets[this.particleIndices[i6]] = i6;
            }
            return this.particleOffsets;
        }

        public void qsort(int i, int i2) {
            if (i < i2) {
                if (i2 - i <= 8) {
                    for (int i3 = i; i3 <= i2; i3++) {
                        for (int i4 = i3; i4 > i && this.distances[i4 - 1] > this.distances[i4]; i4--) {
                            float f = this.distances[i4];
                            float[] fArr = this.distances;
                            fArr[i4] = fArr[i4 - 1];
                            this.distances[i4 - 1] = f;
                            int i5 = this.particleIndices[i4];
                            int[] iArr = this.particleIndices;
                            iArr[i4] = iArr[i4 - 1];
                            this.particleIndices[i4 - 1] = i5;
                        }
                    }
                    return;
                }
                float f2 = this.distances[i];
                int i6 = i + 1;
                int i7 = this.particleIndices[i];
                for (int i8 = i6; i8 <= i2; i8++) {
                    if (f2 > this.distances[i8]) {
                        if (i8 > i6) {
                            float f3 = this.distances[i8];
                            float[] fArr2 = this.distances;
                            fArr2[i8] = fArr2[i6];
                            this.distances[i6] = f3;
                            int i9 = this.particleIndices[i8];
                            int[] iArr2 = this.particleIndices;
                            iArr2[i8] = iArr2[i6];
                            this.particleIndices[i6] = i9;
                        }
                        i6++;
                    }
                }
                float[] fArr3 = this.distances;
                fArr3[i] = fArr3[i6 - 1];
                this.distances[i6 - 1] = f2;
                int[] iArr3 = this.particleIndices;
                iArr3[i] = iArr3[i6 - 1];
                this.particleIndices[i6 - 1] = i7;
                qsort(i, i6 - 2);
                qsort(i6, i2);
            }
        }
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public void ensureCapacity(int i) {
    }
}
