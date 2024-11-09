package com.badlogic.gdx.graphics.g3d.utils.shapebuilders;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.RenderableProvider;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FlushablePool;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/utils/shapebuilders/RenderableShapeBuilder.class */
public class RenderableShapeBuilder extends BaseShapeBuilder {
    private static short[] indices;
    private static float[] vertices;
    private static final RenderablePool renderablesPool = new RenderablePool();
    private static final Array<Renderable> renderables = new Array<>();
    private static final int FLOAT_BYTES = 4;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/utils/shapebuilders/RenderableShapeBuilder$RenderablePool.class */
    public static class RenderablePool extends FlushablePool<Renderable> {
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.badlogic.gdx.utils.Pool
        public Renderable newObject() {
            return new Renderable();
        }

        @Override // com.badlogic.gdx.utils.FlushablePool, com.badlogic.gdx.utils.Pool
        public Renderable obtain() {
            Renderable renderable = (Renderable) super.obtain();
            renderable.environment = null;
            renderable.material = null;
            renderable.meshPart.set("", null, 0, 0, 0);
            renderable.shader = null;
            renderable.userData = null;
            return renderable;
        }
    }

    public static void buildNormals(MeshPartBuilder meshPartBuilder, RenderableProvider renderableProvider, float f) {
        buildNormals(meshPartBuilder, renderableProvider, f, tmpColor0.set(0.0f, 0.0f, 1.0f, 1.0f), tmpColor1.set(1.0f, 0.0f, 0.0f, 1.0f), tmpColor2.set(0.0f, 1.0f, 0.0f, 1.0f));
    }

    public static void buildNormals(MeshPartBuilder meshPartBuilder, RenderableProvider renderableProvider, float f, Color color, Color color2, Color color3) {
        renderableProvider.getRenderables(renderables, renderablesPool);
        Array.ArrayIterator<Renderable> it = renderables.iterator();
        while (it.hasNext()) {
            buildNormals(meshPartBuilder, it.next(), f, color, color2, color3);
        }
        renderablesPool.flush();
        renderables.clear();
    }

    public static void buildNormals(MeshPartBuilder meshPartBuilder, Renderable renderable, float f, Color color, Color color2, Color color3) {
        int i;
        int i2;
        Mesh mesh = renderable.meshPart.mesh;
        int i3 = -1;
        if (mesh.getVertexAttribute(1) != null) {
            i3 = mesh.getVertexAttribute(1).offset / 4;
        }
        int i4 = -1;
        if (mesh.getVertexAttribute(8) != null) {
            i4 = mesh.getVertexAttribute(8).offset / 4;
        }
        int i5 = -1;
        if (mesh.getVertexAttribute(128) != null) {
            i5 = mesh.getVertexAttribute(128).offset / 4;
        }
        int i6 = -1;
        if (mesh.getVertexAttribute(256) != null) {
            i6 = mesh.getVertexAttribute(256).offset / 4;
        }
        int vertexSize = mesh.getVertexSize() / 4;
        if (mesh.getNumIndices() > 0) {
            ensureIndicesCapacity(mesh.getNumIndices());
            mesh.getIndices(renderable.meshPart.offset, renderable.meshPart.size, indices, 0);
            short minVerticeInIndices = minVerticeInIndices();
            i = minVerticeInIndices;
            i2 = maxVerticeInIndices() - minVerticeInIndices;
        } else {
            i = renderable.meshPart.offset;
            i2 = renderable.meshPart.size;
        }
        ensureVerticesCapacity(i2 * vertexSize);
        mesh.getVertices(i * vertexSize, i2 * vertexSize, vertices, 0);
        for (int i7 = i; i7 < i2; i7++) {
            int i8 = i7 * vertexSize;
            tmpV0.set(vertices[i8 + i3], vertices[i8 + i3 + 1], vertices[i8 + i3 + 2]);
            if (i4 != -1) {
                tmpV1.set(vertices[i8 + i4], vertices[i8 + i4 + 1], vertices[i8 + i4 + 2]);
                tmpV2.set(tmpV0).add(tmpV1.scl(f));
            }
            if (i5 != -1) {
                tmpV3.set(vertices[i8 + i5], vertices[i8 + i5 + 1], vertices[i8 + i5 + 2]);
                tmpV4.set(tmpV0).add(tmpV3.scl(f));
            }
            if (i6 != -1) {
                tmpV5.set(vertices[i8 + i6], vertices[i8 + i6 + 1], vertices[i8 + i6 + 2]);
                tmpV6.set(tmpV0).add(tmpV5.scl(f));
            }
            tmpV0.mul(renderable.worldTransform);
            tmpV2.mul(renderable.worldTransform);
            tmpV4.mul(renderable.worldTransform);
            tmpV6.mul(renderable.worldTransform);
            if (i4 != -1) {
                meshPartBuilder.setColor(color);
                meshPartBuilder.line(tmpV0, tmpV2);
            }
            if (i5 != -1) {
                meshPartBuilder.setColor(color2);
                meshPartBuilder.line(tmpV0, tmpV4);
            }
            if (i6 != -1) {
                meshPartBuilder.setColor(color3);
                meshPartBuilder.line(tmpV0, tmpV6);
            }
        }
    }

    private static void ensureVerticesCapacity(int i) {
        if (vertices == null || vertices.length < i) {
            vertices = new float[i];
        }
    }

    private static void ensureIndicesCapacity(int i) {
        if (indices == null || indices.length < i) {
            indices = new short[i];
        }
    }

    private static short minVerticeInIndices() {
        short s = Short.MAX_VALUE;
        for (int i = 0; i < indices.length; i++) {
            if (indices[i] < s) {
                s = indices[i];
            }
        }
        return s;
    }

    private static short maxVerticeInIndices() {
        short s = Short.MIN_VALUE;
        for (int i = 0; i < indices.length; i++) {
            if (indices[i] > s) {
                s = indices[i];
            }
        }
        return s;
    }
}
