package com.badlogic.gdx.graphics;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.IndexArray;
import com.badlogic.gdx.graphics.glutils.IndexBufferObject;
import com.badlogic.gdx.graphics.glutils.IndexBufferObjectSubData;
import com.badlogic.gdx.graphics.glutils.IndexData;
import com.badlogic.gdx.graphics.glutils.InstanceBufferObject;
import com.badlogic.gdx.graphics.glutils.InstanceData;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.VertexArray;
import com.badlogic.gdx.graphics.glutils.VertexBufferObject;
import com.badlogic.gdx.graphics.glutils.VertexBufferObjectSubData;
import com.badlogic.gdx.graphics.glutils.VertexBufferObjectWithVAO;
import com.badlogic.gdx.graphics.glutils.VertexData;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/Mesh.class */
public class Mesh implements Disposable {
    static final Map<Application, Array<Mesh>> meshes = new HashMap();
    final VertexData vertices;
    final IndexData indices;
    boolean autoBind;
    final boolean isVertexArray;
    InstanceData instances;
    boolean isInstanced;
    private final Vector3 tmpV;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/Mesh$VertexDataType.class */
    public enum VertexDataType {
        VertexArray,
        VertexBufferObject,
        VertexBufferObjectSubData,
        VertexBufferObjectWithVAO
    }

    protected Mesh(VertexData vertexData, IndexData indexData, boolean z) {
        this.autoBind = true;
        this.isInstanced = false;
        this.tmpV = new Vector3();
        this.vertices = vertexData;
        this.indices = indexData;
        this.isVertexArray = z;
        addManagedMesh(Gdx.app, this);
    }

    public Mesh(boolean z, int i, int i2, VertexAttribute... vertexAttributeArr) {
        this.autoBind = true;
        this.isInstanced = false;
        this.tmpV = new Vector3();
        this.vertices = makeVertexBuffer(z, i, new VertexAttributes(vertexAttributeArr));
        this.indices = new IndexBufferObject(z, i2);
        this.isVertexArray = false;
        addManagedMesh(Gdx.app, this);
    }

    public Mesh(boolean z, int i, int i2, VertexAttributes vertexAttributes) {
        this.autoBind = true;
        this.isInstanced = false;
        this.tmpV = new Vector3();
        this.vertices = makeVertexBuffer(z, i, vertexAttributes);
        this.indices = new IndexBufferObject(z, i2);
        this.isVertexArray = false;
        addManagedMesh(Gdx.app, this);
    }

    public Mesh(boolean z, boolean z2, int i, int i2, VertexAttributes vertexAttributes) {
        this.autoBind = true;
        this.isInstanced = false;
        this.tmpV = new Vector3();
        this.vertices = makeVertexBuffer(z, i, vertexAttributes);
        this.indices = new IndexBufferObject(z2, i2);
        this.isVertexArray = false;
        addManagedMesh(Gdx.app, this);
    }

    private VertexData makeVertexBuffer(boolean z, int i, VertexAttributes vertexAttributes) {
        if (Gdx.gl30 != null) {
            return new VertexBufferObjectWithVAO(z, i, vertexAttributes);
        }
        return new VertexBufferObject(z, i, vertexAttributes);
    }

    public Mesh(VertexDataType vertexDataType, boolean z, int i, int i2, VertexAttribute... vertexAttributeArr) {
        this(vertexDataType, z, i, i2, new VertexAttributes(vertexAttributeArr));
    }

    public Mesh(VertexDataType vertexDataType, boolean z, int i, int i2, VertexAttributes vertexAttributes) {
        this.autoBind = true;
        this.isInstanced = false;
        this.tmpV = new Vector3();
        switch (vertexDataType) {
            case VertexBufferObject:
                this.vertices = new VertexBufferObject(z, i, vertexAttributes);
                this.indices = new IndexBufferObject(z, i2);
                this.isVertexArray = false;
                break;
            case VertexBufferObjectSubData:
                this.vertices = new VertexBufferObjectSubData(z, i, vertexAttributes);
                this.indices = new IndexBufferObjectSubData(z, i2);
                this.isVertexArray = false;
                break;
            case VertexBufferObjectWithVAO:
                this.vertices = new VertexBufferObjectWithVAO(z, i, vertexAttributes);
                this.indices = new IndexBufferObjectSubData(z, i2);
                this.isVertexArray = false;
                break;
            default:
                this.vertices = new VertexArray(i, vertexAttributes);
                this.indices = new IndexArray(i2);
                this.isVertexArray = true;
                break;
        }
        addManagedMesh(Gdx.app, this);
    }

    public Mesh enableInstancedRendering(boolean z, int i, VertexAttribute... vertexAttributeArr) {
        if (!this.isInstanced) {
            this.isInstanced = true;
            this.instances = new InstanceBufferObject(z, i, vertexAttributeArr);
            return this;
        }
        throw new GdxRuntimeException("Trying to enable InstancedRendering on same Mesh instance twice. Use disableInstancedRendering to clean up old InstanceData first");
    }

    public Mesh disableInstancedRendering() {
        if (this.isInstanced) {
            this.isInstanced = false;
            this.instances.dispose();
            this.instances = null;
        }
        return this;
    }

    public Mesh setInstanceData(float[] fArr, int i, int i2) {
        if (this.instances != null) {
            this.instances.setInstanceData(fArr, i, i2);
            return this;
        }
        throw new GdxRuntimeException("An InstanceBufferObject must be set before setting instance data!");
    }

    public Mesh setInstanceData(float[] fArr) {
        if (this.instances != null) {
            this.instances.setInstanceData(fArr, 0, fArr.length);
            return this;
        }
        throw new GdxRuntimeException("An InstanceBufferObject must be set before setting instance data!");
    }

    public Mesh setInstanceData(FloatBuffer floatBuffer, int i) {
        if (this.instances != null) {
            this.instances.setInstanceData(floatBuffer, i);
            return this;
        }
        throw new GdxRuntimeException("An InstanceBufferObject must be set before setting instance data!");
    }

    public Mesh setInstanceData(FloatBuffer floatBuffer) {
        if (this.instances != null) {
            this.instances.setInstanceData(floatBuffer, floatBuffer.limit());
            return this;
        }
        throw new GdxRuntimeException("An InstanceBufferObject must be set before setting instance data!");
    }

    public Mesh updateInstanceData(int i, float[] fArr) {
        return updateInstanceData(i, fArr, 0, fArr.length);
    }

    public Mesh updateInstanceData(int i, float[] fArr, int i2, int i3) {
        this.instances.updateInstanceData(i, fArr, i2, i3);
        return this;
    }

    public Mesh updateInstanceData(int i, FloatBuffer floatBuffer) {
        return updateInstanceData(i, floatBuffer, 0, floatBuffer.limit());
    }

    public Mesh updateInstanceData(int i, FloatBuffer floatBuffer, int i2, int i3) {
        this.instances.updateInstanceData(i, floatBuffer, i2, i3);
        return this;
    }

    public Mesh setVertices(float[] fArr) {
        this.vertices.setVertices(fArr, 0, fArr.length);
        return this;
    }

    public boolean isInstanced() {
        return this.isInstanced;
    }

    public Mesh setVertices(float[] fArr, int i, int i2) {
        this.vertices.setVertices(fArr, i, i2);
        return this;
    }

    public Mesh updateVertices(int i, float[] fArr) {
        return updateVertices(i, fArr, 0, fArr.length);
    }

    public Mesh updateVertices(int i, float[] fArr, int i2, int i3) {
        this.vertices.updateVertices(i, fArr, i2, i3);
        return this;
    }

    public float[] getVertices(float[] fArr) {
        return getVertices(0, -1, fArr);
    }

    public float[] getVertices(int i, float[] fArr) {
        return getVertices(i, -1, fArr);
    }

    public float[] getVertices(int i, int i2, float[] fArr) {
        return getVertices(i, i2, fArr, 0);
    }

    public float[] getVertices(int i, int i2, float[] fArr, int i3) {
        int numVertices = (getNumVertices() * getVertexSize()) / 4;
        if (i2 == -1) {
            int i4 = numVertices - i;
            i2 = i4;
            if (i4 > fArr.length - i3) {
                i2 = fArr.length - i3;
            }
        }
        if (i < 0 || i2 <= 0 || i + i2 > numVertices || i3 < 0 || i3 >= fArr.length) {
            throw new IndexOutOfBoundsException();
        }
        if (fArr.length - i3 < i2) {
            throw new IllegalArgumentException("not enough room in vertices array, has " + fArr.length + " floats, needs " + i2);
        }
        FloatBuffer verticesBuffer = getVerticesBuffer(false);
        int position = verticesBuffer.position();
        verticesBuffer.position(i);
        verticesBuffer.get(fArr, i3, i2);
        verticesBuffer.position(position);
        return fArr;
    }

    public Mesh setIndices(short[] sArr) {
        this.indices.setIndices(sArr, 0, sArr.length);
        return this;
    }

    public Mesh setIndices(short[] sArr, int i, int i2) {
        this.indices.setIndices(sArr, i, i2);
        return this;
    }

    public void getIndices(short[] sArr) {
        getIndices(sArr, 0);
    }

    public void getIndices(short[] sArr, int i) {
        getIndices(0, sArr, i);
    }

    public void getIndices(int i, short[] sArr, int i2) {
        getIndices(i, -1, sArr, i2);
    }

    public void getIndices(int i, int i2, short[] sArr, int i3) {
        int numIndices = getNumIndices();
        if (i2 < 0) {
            i2 = numIndices - i;
        }
        if (i < 0 || i >= numIndices || i + i2 > numIndices) {
            throw new IllegalArgumentException("Invalid range specified, offset: " + i + ", count: " + i2 + ", max: " + numIndices);
        }
        if (sArr.length - i3 < i2) {
            throw new IllegalArgumentException("not enough room in indices array, has " + sArr.length + " shorts, needs " + i2);
        }
        ShortBuffer indicesBuffer = getIndicesBuffer(false);
        int position = indicesBuffer.position();
        indicesBuffer.position(i);
        indicesBuffer.get(sArr, i3, i2);
        indicesBuffer.position(position);
    }

    public int getNumIndices() {
        return this.indices.getNumIndices();
    }

    public int getNumVertices() {
        return this.vertices.getNumVertices();
    }

    public int getMaxVertices() {
        return this.vertices.getNumMaxVertices();
    }

    public int getMaxIndices() {
        return this.indices.getNumMaxIndices();
    }

    public int getVertexSize() {
        return this.vertices.getAttributes().vertexSize;
    }

    public void setAutoBind(boolean z) {
        this.autoBind = z;
    }

    public void bind(ShaderProgram shaderProgram) {
        bind(shaderProgram, null, null);
    }

    public void bind(ShaderProgram shaderProgram, int[] iArr, int[] iArr2) {
        this.vertices.bind(shaderProgram, iArr);
        if (this.instances != null && this.instances.getNumInstances() > 0) {
            this.instances.bind(shaderProgram, iArr2);
        }
        if (this.indices.getNumIndices() > 0) {
            this.indices.bind();
        }
    }

    public void unbind(ShaderProgram shaderProgram) {
        unbind(shaderProgram, null, null);
    }

    public void unbind(ShaderProgram shaderProgram, int[] iArr, int[] iArr2) {
        this.vertices.unbind(shaderProgram, iArr);
        if (this.instances != null && this.instances.getNumInstances() > 0) {
            this.instances.unbind(shaderProgram, iArr2);
        }
        if (this.indices.getNumIndices() > 0) {
            this.indices.unbind();
        }
    }

    public void render(ShaderProgram shaderProgram, int i) {
        render(shaderProgram, i, 0, this.indices.getNumMaxIndices() > 0 ? getNumIndices() : getNumVertices(), this.autoBind);
    }

    public void render(ShaderProgram shaderProgram, int i, int i2, int i3) {
        render(shaderProgram, i, i2, i3, this.autoBind);
    }

    public void render(ShaderProgram shaderProgram, int i, int i2, int i3, boolean z) {
        if (i3 == 0) {
            return;
        }
        if (z) {
            bind(shaderProgram);
        }
        if (this.isVertexArray) {
            if (this.indices.getNumIndices() > 0) {
                ShortBuffer buffer = this.indices.getBuffer(false);
                int position = buffer.position();
                buffer.limit();
                buffer.position(i2);
                Gdx.gl20.glDrawElements(i, i3, 5123, buffer);
                buffer.position(position);
            } else {
                Gdx.gl20.glDrawArrays(i, i2, i3);
            }
        } else {
            int i4 = 0;
            if (this.isInstanced) {
                i4 = this.instances.getNumInstances();
            }
            if (this.indices.getNumIndices() > 0) {
                if (i3 + i2 > this.indices.getNumMaxIndices()) {
                    throw new GdxRuntimeException("Mesh attempting to access memory outside of the index buffer (count: " + i3 + ", offset: " + i2 + ", max: " + this.indices.getNumMaxIndices() + ")");
                }
                if (this.isInstanced && i4 > 0) {
                    Gdx.gl30.glDrawElementsInstanced(i, i3, 5123, i2 << 1, i4);
                } else {
                    Gdx.gl20.glDrawElements(i, i3, 5123, i2 << 1);
                }
            } else if (this.isInstanced && i4 > 0) {
                Gdx.gl30.glDrawArraysInstanced(i, i2, i3, i4);
            } else {
                Gdx.gl20.glDrawArrays(i, i2, i3);
            }
        }
        if (z) {
            unbind(shaderProgram);
        }
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        if (meshes.get(Gdx.app) != null) {
            meshes.get(Gdx.app).removeValue(this, true);
        }
        this.vertices.dispose();
        if (this.instances != null) {
            this.instances.dispose();
        }
        this.indices.dispose();
    }

    public VertexAttribute getVertexAttribute(int i) {
        VertexAttributes attributes = this.vertices.getAttributes();
        int size = attributes.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (attributes.get(i2).usage == i) {
                return attributes.get(i2);
            }
        }
        return null;
    }

    public VertexAttributes getVertexAttributes() {
        return this.vertices.getAttributes();
    }

    public VertexAttributes getInstancedAttributes() {
        if (this.instances != null) {
            return this.instances.getAttributes();
        }
        return null;
    }

    @Deprecated
    public FloatBuffer getVerticesBuffer() {
        return this.vertices.getBuffer(true);
    }

    public FloatBuffer getVerticesBuffer(boolean z) {
        return this.vertices.getBuffer(z);
    }

    public BoundingBox calculateBoundingBox() {
        BoundingBox boundingBox = new BoundingBox();
        calculateBoundingBox(boundingBox);
        return boundingBox;
    }

    public void calculateBoundingBox(BoundingBox boundingBox) {
        int numVertices = getNumVertices();
        if (numVertices == 0) {
            throw new GdxRuntimeException("No vertices defined");
        }
        FloatBuffer buffer = this.vertices.getBuffer(false);
        boundingBox.inf();
        VertexAttribute vertexAttribute = getVertexAttribute(1);
        int i = vertexAttribute.offset / 4;
        int i2 = this.vertices.getAttributes().vertexSize / 4;
        int i3 = i;
        switch (vertexAttribute.numComponents) {
            case 1:
                for (int i4 = 0; i4 < numVertices; i4++) {
                    boundingBox.ext(buffer.get(i3), 0.0f, 0.0f);
                    i3 += i2;
                }
                return;
            case 2:
                for (int i5 = 0; i5 < numVertices; i5++) {
                    boundingBox.ext(buffer.get(i3), buffer.get(i3 + 1), 0.0f);
                    i3 += i2;
                }
                return;
            case 3:
                for (int i6 = 0; i6 < numVertices; i6++) {
                    boundingBox.ext(buffer.get(i3), buffer.get(i3 + 1), buffer.get(i3 + 2));
                    i3 += i2;
                }
                return;
            default:
                return;
        }
    }

    public BoundingBox calculateBoundingBox(BoundingBox boundingBox, int i, int i2) {
        return extendBoundingBox(boundingBox.inf(), i, i2);
    }

    public BoundingBox calculateBoundingBox(BoundingBox boundingBox, int i, int i2, Matrix4 matrix4) {
        return extendBoundingBox(boundingBox.inf(), i, i2, matrix4);
    }

    public BoundingBox extendBoundingBox(BoundingBox boundingBox, int i, int i2) {
        return extendBoundingBox(boundingBox, i, i2, null);
    }

    public BoundingBox extendBoundingBox(BoundingBox boundingBox, int i, int i2, Matrix4 matrix4) {
        int numIndices = getNumIndices();
        int numVertices = numIndices == 0 ? getNumVertices() : numIndices;
        if (i < 0 || i2 <= 0 || i + i2 > numVertices) {
            throw new GdxRuntimeException("Invalid part specified ( offset=" + i + ", count=" + i2 + ", max=" + numVertices + " )");
        }
        FloatBuffer buffer = this.vertices.getBuffer(false);
        ShortBuffer buffer2 = this.indices.getBuffer(false);
        VertexAttribute vertexAttribute = getVertexAttribute(1);
        int i3 = vertexAttribute.offset / 4;
        int i4 = this.vertices.getAttributes().vertexSize / 4;
        int i5 = i + i2;
        switch (vertexAttribute.numComponents) {
            case 1:
                if (numIndices > 0) {
                    for (int i6 = i; i6 < i5; i6++) {
                        this.tmpV.set(buffer.get(((buffer2.get(i6) & 65535) * i4) + i3), 0.0f, 0.0f);
                        if (matrix4 != null) {
                            this.tmpV.mul(matrix4);
                        }
                        boundingBox.ext(this.tmpV);
                    }
                    break;
                } else {
                    for (int i7 = i; i7 < i5; i7++) {
                        this.tmpV.set(buffer.get((i7 * i4) + i3), 0.0f, 0.0f);
                        if (matrix4 != null) {
                            this.tmpV.mul(matrix4);
                        }
                        boundingBox.ext(this.tmpV);
                    }
                    break;
                }
            case 2:
                if (numIndices > 0) {
                    for (int i8 = i; i8 < i5; i8++) {
                        int i9 = ((buffer2.get(i8) & 65535) * i4) + i3;
                        this.tmpV.set(buffer.get(i9), buffer.get(i9 + 1), 0.0f);
                        if (matrix4 != null) {
                            this.tmpV.mul(matrix4);
                        }
                        boundingBox.ext(this.tmpV);
                    }
                    break;
                } else {
                    for (int i10 = i; i10 < i5; i10++) {
                        int i11 = (i10 * i4) + i3;
                        this.tmpV.set(buffer.get(i11), buffer.get(i11 + 1), 0.0f);
                        if (matrix4 != null) {
                            this.tmpV.mul(matrix4);
                        }
                        boundingBox.ext(this.tmpV);
                    }
                    break;
                }
            case 3:
                if (numIndices > 0) {
                    for (int i12 = i; i12 < i5; i12++) {
                        int i13 = ((buffer2.get(i12) & 65535) * i4) + i3;
                        this.tmpV.set(buffer.get(i13), buffer.get(i13 + 1), buffer.get(i13 + 2));
                        if (matrix4 != null) {
                            this.tmpV.mul(matrix4);
                        }
                        boundingBox.ext(this.tmpV);
                    }
                    break;
                } else {
                    for (int i14 = i; i14 < i5; i14++) {
                        int i15 = (i14 * i4) + i3;
                        this.tmpV.set(buffer.get(i15), buffer.get(i15 + 1), buffer.get(i15 + 2));
                        if (matrix4 != null) {
                            this.tmpV.mul(matrix4);
                        }
                        boundingBox.ext(this.tmpV);
                    }
                    break;
                }
        }
        return boundingBox;
    }

    public float calculateRadiusSquared(float f, float f2, float f3, int i, int i2, Matrix4 matrix4) {
        int numIndices = getNumIndices();
        if (i < 0 || i2 <= 0 || i + i2 > numIndices) {
            throw new GdxRuntimeException("Not enough indices");
        }
        FloatBuffer buffer = this.vertices.getBuffer(false);
        ShortBuffer buffer2 = this.indices.getBuffer(false);
        VertexAttribute vertexAttribute = getVertexAttribute(1);
        int i3 = vertexAttribute.offset / 4;
        int i4 = this.vertices.getAttributes().vertexSize / 4;
        int i5 = i + i2;
        float f4 = 0.0f;
        switch (vertexAttribute.numComponents) {
            case 1:
                for (int i6 = i; i6 < i5; i6++) {
                    this.tmpV.set(buffer.get(((buffer2.get(i6) & 65535) * i4) + i3), 0.0f, 0.0f);
                    if (matrix4 != null) {
                        this.tmpV.mul(matrix4);
                    }
                    float len2 = this.tmpV.sub(f, f2, f3).len2();
                    if (len2 > f4) {
                        f4 = len2;
                    }
                }
                break;
            case 2:
                for (int i7 = i; i7 < i5; i7++) {
                    int i8 = ((buffer2.get(i7) & 65535) * i4) + i3;
                    this.tmpV.set(buffer.get(i8), buffer.get(i8 + 1), 0.0f);
                    if (matrix4 != null) {
                        this.tmpV.mul(matrix4);
                    }
                    float len22 = this.tmpV.sub(f, f2, f3).len2();
                    if (len22 > f4) {
                        f4 = len22;
                    }
                }
                break;
            case 3:
                for (int i9 = i; i9 < i5; i9++) {
                    int i10 = ((buffer2.get(i9) & 65535) * i4) + i3;
                    this.tmpV.set(buffer.get(i10), buffer.get(i10 + 1), buffer.get(i10 + 2));
                    if (matrix4 != null) {
                        this.tmpV.mul(matrix4);
                    }
                    float len23 = this.tmpV.sub(f, f2, f3).len2();
                    if (len23 > f4) {
                        f4 = len23;
                    }
                }
                break;
        }
        return f4;
    }

    public float calculateRadius(float f, float f2, float f3, int i, int i2, Matrix4 matrix4) {
        return (float) Math.sqrt(calculateRadiusSquared(f, f2, f3, i, i2, matrix4));
    }

    public float calculateRadius(Vector3 vector3, int i, int i2, Matrix4 matrix4) {
        return calculateRadius(vector3.x, vector3.y, vector3.z, i, i2, matrix4);
    }

    public float calculateRadius(float f, float f2, float f3, int i, int i2) {
        return calculateRadius(f, f2, f3, i, i2, null);
    }

    public float calculateRadius(Vector3 vector3, int i, int i2) {
        return calculateRadius(vector3.x, vector3.y, vector3.z, i, i2, null);
    }

    public float calculateRadius(float f, float f2, float f3) {
        return calculateRadius(f, f2, f3, 0, getNumIndices(), null);
    }

    public float calculateRadius(Vector3 vector3) {
        return calculateRadius(vector3.x, vector3.y, vector3.z, 0, getNumIndices(), null);
    }

    @Deprecated
    public ShortBuffer getIndicesBuffer() {
        return this.indices.getBuffer(true);
    }

    public ShortBuffer getIndicesBuffer(boolean z) {
        return this.indices.getBuffer(z);
    }

    private static void addManagedMesh(Application application, Mesh mesh) {
        Array<Mesh> array = meshes.get(application);
        Array<Mesh> array2 = array;
        if (array == null) {
            array2 = new Array<>();
        }
        array2.add(mesh);
        meshes.put(application, array2);
    }

    public static void invalidateAllMeshes(Application application) {
        Array<Mesh> array = meshes.get(application);
        if (array == null) {
            return;
        }
        for (int i = 0; i < array.size; i++) {
            array.get(i).vertices.invalidate();
            array.get(i).indices.invalidate();
        }
    }

    public static void clearAllMeshes(Application application) {
        meshes.remove(application);
    }

    public static String getManagedStatus() {
        StringBuilder sb = new StringBuilder();
        sb.append("Managed meshes/app: { ");
        Iterator<Application> it = meshes.keySet().iterator();
        while (it.hasNext()) {
            sb.append(meshes.get(it.next()).size);
            sb.append(SequenceUtils.SPACE);
        }
        sb.append("}");
        return sb.toString();
    }

    public void scale(float f, float f2, float f3) {
        VertexAttribute vertexAttribute = getVertexAttribute(1);
        int i = vertexAttribute.offset / 4;
        int i2 = vertexAttribute.numComponents;
        int numVertices = getNumVertices();
        int vertexSize = getVertexSize() / 4;
        float[] fArr = new float[numVertices * vertexSize];
        getVertices(fArr);
        int i3 = i;
        switch (i2) {
            case 1:
                for (int i4 = 0; i4 < numVertices; i4++) {
                    int i5 = i3;
                    fArr[i5] = fArr[i5] * f;
                    i3 += vertexSize;
                }
                break;
            case 2:
                for (int i6 = 0; i6 < numVertices; i6++) {
                    int i7 = i3;
                    fArr[i7] = fArr[i7] * f;
                    int i8 = i3 + 1;
                    fArr[i8] = fArr[i8] * f2;
                    i3 += vertexSize;
                }
                break;
            case 3:
                for (int i9 = 0; i9 < numVertices; i9++) {
                    int i10 = i3;
                    fArr[i10] = fArr[i10] * f;
                    int i11 = i3 + 1;
                    fArr[i11] = fArr[i11] * f2;
                    int i12 = i3 + 2;
                    fArr[i12] = fArr[i12] * f3;
                    i3 += vertexSize;
                }
                break;
        }
        setVertices(fArr);
    }

    public void transform(Matrix4 matrix4) {
        transform(matrix4, 0, getNumVertices());
    }

    public void transform(Matrix4 matrix4, int i, int i2) {
        VertexAttribute vertexAttribute = getVertexAttribute(1);
        int i3 = vertexAttribute.offset / 4;
        int vertexSize = getVertexSize() / 4;
        int i4 = vertexAttribute.numComponents;
        getNumVertices();
        float[] fArr = new float[i2 * vertexSize];
        getVertices(i * vertexSize, i2 * vertexSize, fArr);
        transform(matrix4, fArr, vertexSize, i3, i4, 0, i2);
        updateVertices(i * vertexSize, fArr);
    }

    public static void transform(Matrix4 matrix4, float[] fArr, int i, int i2, int i3, int i4, int i5) {
        if (i2 < 0 || i3 <= 0 || i2 + i3 > i) {
            throw new IndexOutOfBoundsException();
        }
        if (i4 < 0 || i5 <= 0 || (i4 + i5) * i > fArr.length) {
            throw new IndexOutOfBoundsException("start = " + i4 + ", count = " + i5 + ", vertexSize = " + i + ", length = " + fArr.length);
        }
        Vector3 vector3 = new Vector3();
        int i6 = i2 + (i4 * i);
        switch (i3) {
            case 1:
                for (int i7 = 0; i7 < i5; i7++) {
                    vector3.set(fArr[i6], 0.0f, 0.0f).mul(matrix4);
                    fArr[i6] = vector3.x;
                    i6 += i;
                }
                return;
            case 2:
                for (int i8 = 0; i8 < i5; i8++) {
                    vector3.set(fArr[i6], fArr[i6 + 1], 0.0f).mul(matrix4);
                    fArr[i6] = vector3.x;
                    fArr[i6 + 1] = vector3.y;
                    i6 += i;
                }
                return;
            case 3:
                for (int i9 = 0; i9 < i5; i9++) {
                    vector3.set(fArr[i6], fArr[i6 + 1], fArr[i6 + 2]).mul(matrix4);
                    fArr[i6] = vector3.x;
                    fArr[i6 + 1] = vector3.y;
                    fArr[i6 + 2] = vector3.z;
                    i6 += i;
                }
                return;
            default:
                return;
        }
    }

    public void transformUV(Matrix3 matrix3) {
        transformUV(matrix3, 0, getNumVertices());
    }

    protected void transformUV(Matrix3 matrix3, int i, int i2) {
        int i3 = getVertexAttribute(16).offset / 4;
        int vertexSize = getVertexSize() / 4;
        float[] fArr = new float[getNumVertices() * vertexSize];
        getVertices(0, fArr.length, fArr);
        transformUV(matrix3, fArr, vertexSize, i3, i, i2);
        setVertices(fArr, 0, fArr.length);
    }

    public static void transformUV(Matrix3 matrix3, float[] fArr, int i, int i2, int i3, int i4) {
        if (i3 < 0 || i4 <= 0 || (i3 + i4) * i > fArr.length) {
            throw new IndexOutOfBoundsException("start = " + i3 + ", count = " + i4 + ", vertexSize = " + i + ", length = " + fArr.length);
        }
        Vector2 vector2 = new Vector2();
        int i5 = i2 + (i3 * i);
        for (int i6 = 0; i6 < i4; i6++) {
            vector2.set(fArr[i5], fArr[i5 + 1]).mul(matrix3);
            fArr[i5] = vector2.x;
            fArr[i5 + 1] = vector2.y;
            i5 += i;
        }
    }

    public Mesh copy(boolean z, boolean z2, int[] iArr) {
        Mesh mesh;
        int vertexSize = getVertexSize() / 4;
        int numVertices = getNumVertices();
        int i = numVertices;
        float[] fArr = new float[numVertices * vertexSize];
        getVertices(0, fArr.length, fArr);
        short[] sArr = null;
        VertexAttribute[] vertexAttributeArr = null;
        int i2 = 0;
        if (iArr != null) {
            int i3 = 0;
            int i4 = 0;
            for (int i5 = 0; i5 < iArr.length; i5++) {
                if (getVertexAttribute(iArr[i5]) != null) {
                    i3 += getVertexAttribute(iArr[i5]).numComponents;
                    i4++;
                }
            }
            if (i3 > 0) {
                vertexAttributeArr = new VertexAttribute[i4];
                sArr = new short[i3];
                int i6 = -1;
                int i7 = -1;
                for (int i8 : iArr) {
                    VertexAttribute vertexAttribute = getVertexAttribute(i8);
                    if (vertexAttribute != null) {
                        for (int i9 = 0; i9 < vertexAttribute.numComponents; i9++) {
                            i6++;
                            sArr[i6] = (short) (vertexAttribute.offset + i9);
                        }
                        i7++;
                        vertexAttributeArr[i7] = vertexAttribute.copy();
                        i2 += vertexAttribute.numComponents;
                    }
                }
            }
        }
        if (sArr == null) {
            sArr = new short[vertexSize];
            short s = 0;
            while (true) {
                short s2 = s;
                if (s2 >= vertexSize) {
                    break;
                }
                sArr[s2] = s2;
                s = (short) (s2 + 1);
            }
            i2 = vertexSize;
        }
        int numIndices = getNumIndices();
        short[] sArr2 = null;
        if (numIndices > 0) {
            sArr2 = new short[numIndices];
            getIndices(sArr2);
            if (z2 || i2 != vertexSize) {
                float[] fArr2 = new float[fArr.length];
                int i10 = 0;
                for (int i11 = 0; i11 < numIndices; i11++) {
                    int i12 = sArr2[i11] * vertexSize;
                    short s3 = -1;
                    if (z2) {
                        short s4 = 0;
                        while (true) {
                            short s5 = s4;
                            if (s5 >= i10 || s3 >= 0) {
                                break;
                            }
                            int i13 = s5 * i2;
                            boolean z3 = true;
                            for (int i14 = 0; i14 < sArr.length && z3; i14++) {
                                if (fArr2[i13 + i14] != fArr[i12 + sArr[i14]]) {
                                    z3 = false;
                                }
                            }
                            if (z3) {
                                s3 = s5;
                            }
                            s4 = (short) (s5 + 1);
                        }
                    }
                    if (s3 > 0) {
                        sArr2[i11] = s3;
                    } else {
                        int i15 = i10 * i2;
                        for (int i16 = 0; i16 < sArr.length; i16++) {
                            fArr2[i15 + i16] = fArr[i12 + sArr[i16]];
                        }
                        sArr2[i11] = (short) i10;
                        i10++;
                    }
                }
                fArr = fArr2;
                i = i10;
            }
        }
        if (vertexAttributeArr == null) {
            mesh = new Mesh(z, i, sArr2 == null ? 0 : sArr2.length, getVertexAttributes());
        } else {
            mesh = new Mesh(z, i, sArr2 == null ? 0 : sArr2.length, vertexAttributeArr);
        }
        mesh.setVertices(fArr, 0, i * i2);
        if (sArr2 != null) {
            mesh.setIndices(sArr2);
        }
        return mesh;
    }

    public Mesh copy(boolean z) {
        return copy(z, false, null);
    }
}
