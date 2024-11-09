package com.badlogic.gdx.math.collision;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import java.io.Serializable;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/math/collision/OrientedBoundingBox.class */
public class OrientedBoundingBox implements Serializable {
    private static final long serialVersionUID = 3864065514676250557L;
    private static final Vector3[] tempAxes = new Vector3[15];
    private static final Vector3[] tempVertices = new Vector3[8];
    private static final Vector3[] tmpVectors = new Vector3[9];
    private final BoundingBox bounds;
    public final Matrix4 transform;
    private final Matrix4 inverseTransform;
    private final Vector3[] axes;
    private final Vector3[] vertices;

    static {
        for (int i = 0; i < tmpVectors.length; i++) {
            tmpVectors[i] = new Vector3();
        }
        for (int i2 = 0; i2 < tempVertices.length; i2++) {
            tempVertices[i2] = new Vector3();
        }
    }

    public OrientedBoundingBox() {
        this.bounds = new BoundingBox();
        this.transform = new Matrix4();
        this.inverseTransform = new Matrix4();
        this.axes = new Vector3[3];
        this.vertices = new Vector3[8];
        this.bounds.clr();
        init();
    }

    public OrientedBoundingBox(BoundingBox boundingBox) {
        this.bounds = new BoundingBox();
        this.transform = new Matrix4();
        this.inverseTransform = new Matrix4();
        this.axes = new Vector3[3];
        this.vertices = new Vector3[8];
        this.bounds.set(boundingBox.min, boundingBox.max);
        init();
    }

    public OrientedBoundingBox(BoundingBox boundingBox, Matrix4 matrix4) {
        this.bounds = new BoundingBox();
        this.transform = new Matrix4();
        this.inverseTransform = new Matrix4();
        this.axes = new Vector3[3];
        this.vertices = new Vector3[8];
        this.bounds.set(boundingBox.min, boundingBox.max);
        this.transform.set(matrix4);
        init();
    }

    private void init() {
        for (int i = 0; i < this.axes.length; i++) {
            this.axes[i] = new Vector3();
        }
        for (int i2 = 0; i2 < this.vertices.length; i2++) {
            this.vertices[i2] = new Vector3();
        }
        update();
    }

    public Vector3[] getVertices() {
        return this.vertices;
    }

    public BoundingBox getBounds() {
        return this.bounds;
    }

    public void setBounds(BoundingBox boundingBox) {
        this.bounds.set(boundingBox);
        boundingBox.getCorner000(this.vertices[0]).mul(this.transform);
        boundingBox.getCorner001(this.vertices[1]).mul(this.transform);
        boundingBox.getCorner010(this.vertices[2]).mul(this.transform);
        boundingBox.getCorner011(this.vertices[3]).mul(this.transform);
        boundingBox.getCorner100(this.vertices[4]).mul(this.transform);
        boundingBox.getCorner101(this.vertices[5]).mul(this.transform);
        boundingBox.getCorner110(this.vertices[6]).mul(this.transform);
        boundingBox.getCorner111(this.vertices[7]).mul(this.transform);
    }

    public Matrix4 getTransform() {
        return this.transform;
    }

    public void setTransform(Matrix4 matrix4) {
        this.transform.set(matrix4);
        update();
    }

    public OrientedBoundingBox set(BoundingBox boundingBox, Matrix4 matrix4) {
        setBounds(boundingBox);
        setTransform(matrix4);
        return this;
    }

    public Vector3 getCorner000(Vector3 vector3) {
        return vector3.set(this.vertices[0]);
    }

    public Vector3 getCorner001(Vector3 vector3) {
        return vector3.set(this.vertices[1]);
    }

    public Vector3 getCorner010(Vector3 vector3) {
        return vector3.set(this.vertices[2]);
    }

    public Vector3 getCorner011(Vector3 vector3) {
        return vector3.set(this.vertices[3]);
    }

    public Vector3 getCorner100(Vector3 vector3) {
        return vector3.set(this.vertices[4]);
    }

    public Vector3 getCorner101(Vector3 vector3) {
        return vector3.set(this.vertices[5]);
    }

    public Vector3 getCorner110(Vector3 vector3) {
        return vector3.set(this.vertices[6]);
    }

    public Vector3 getCorner111(Vector3 vector3) {
        return vector3.set(this.vertices[7]);
    }

    public boolean contains(Vector3 vector3) {
        return contains(vector3, this.inverseTransform);
    }

    private boolean contains(Vector3 vector3, Matrix4 matrix4) {
        return this.bounds.contains(tmpVectors[0].set(vector3).mul(matrix4));
    }

    public boolean contains(BoundingBox boundingBox) {
        Vector3 vector3 = tmpVectors[0];
        return contains(boundingBox.getCorner000(vector3), this.inverseTransform) && contains(boundingBox.getCorner001(vector3), this.inverseTransform) && contains(boundingBox.getCorner010(vector3), this.inverseTransform) && contains(boundingBox.getCorner011(vector3), this.inverseTransform) && contains(boundingBox.getCorner100(vector3), this.inverseTransform) && contains(boundingBox.getCorner101(vector3), this.inverseTransform) && contains(boundingBox.getCorner110(vector3), this.inverseTransform) && contains(boundingBox.getCorner111(vector3), this.inverseTransform);
    }

    public boolean contains(OrientedBoundingBox orientedBoundingBox) {
        return contains(orientedBoundingBox.getCorner000(tmpVectors[0]), this.inverseTransform) && contains(orientedBoundingBox.getCorner001(tmpVectors[0]), this.inverseTransform) && contains(orientedBoundingBox.getCorner010(tmpVectors[0]), this.inverseTransform) && contains(orientedBoundingBox.getCorner011(tmpVectors[0]), this.inverseTransform) && contains(orientedBoundingBox.getCorner100(tmpVectors[0]), this.inverseTransform) && contains(orientedBoundingBox.getCorner101(tmpVectors[0]), this.inverseTransform) && contains(orientedBoundingBox.getCorner110(tmpVectors[0]), this.inverseTransform) && contains(orientedBoundingBox.getCorner111(tmpVectors[0]), this.inverseTransform);
    }

    public boolean intersects(BoundingBox boundingBox) {
        Vector3[] vector3Arr = this.axes;
        tempAxes[0] = vector3Arr[0];
        tempAxes[1] = vector3Arr[1];
        tempAxes[2] = vector3Arr[2];
        tempAxes[3] = Vector3.X;
        tempAxes[4] = Vector3.Y;
        tempAxes[5] = Vector3.Z;
        tempAxes[6] = tmpVectors[0].set(vector3Arr[0]).crs(Vector3.X);
        tempAxes[7] = tmpVectors[1].set(vector3Arr[0]).crs(Vector3.Y);
        tempAxes[8] = tmpVectors[2].set(vector3Arr[0]).crs(Vector3.Z);
        tempAxes[9] = tmpVectors[3].set(vector3Arr[1]).crs(Vector3.X);
        tempAxes[10] = tmpVectors[4].set(vector3Arr[1]).crs(Vector3.Y);
        tempAxes[11] = tmpVectors[5].set(vector3Arr[1]).crs(Vector3.Z);
        tempAxes[12] = tmpVectors[6].set(vector3Arr[2]).crs(Vector3.X);
        tempAxes[13] = tmpVectors[7].set(vector3Arr[2]).crs(Vector3.Y);
        tempAxes[14] = tmpVectors[8].set(vector3Arr[2]).crs(Vector3.Z);
        return Intersector.hasOverlap(tempAxes, getVertices(), getVertices(boundingBox));
    }

    public boolean intersects(OrientedBoundingBox orientedBoundingBox) {
        Vector3[] vector3Arr = this.axes;
        Vector3[] vector3Arr2 = orientedBoundingBox.axes;
        tempAxes[0] = vector3Arr[0];
        tempAxes[1] = vector3Arr[1];
        tempAxes[2] = vector3Arr[2];
        tempAxes[3] = vector3Arr2[0];
        tempAxes[4] = vector3Arr2[1];
        tempAxes[5] = vector3Arr2[2];
        tempAxes[6] = tmpVectors[0].set(vector3Arr[0]).crs(vector3Arr2[0]);
        tempAxes[7] = tmpVectors[1].set(vector3Arr[0]).crs(vector3Arr2[1]);
        tempAxes[8] = tmpVectors[2].set(vector3Arr[0]).crs(vector3Arr2[2]);
        tempAxes[9] = tmpVectors[3].set(vector3Arr[1]).crs(vector3Arr2[0]);
        tempAxes[10] = tmpVectors[4].set(vector3Arr[1]).crs(vector3Arr2[1]);
        tempAxes[11] = tmpVectors[5].set(vector3Arr[1]).crs(vector3Arr2[2]);
        tempAxes[12] = tmpVectors[6].set(vector3Arr[2]).crs(vector3Arr2[0]);
        tempAxes[13] = tmpVectors[7].set(vector3Arr[2]).crs(vector3Arr2[1]);
        tempAxes[14] = tmpVectors[8].set(vector3Arr[2]).crs(vector3Arr2[2]);
        return Intersector.hasOverlap(tempAxes, this.vertices, orientedBoundingBox.vertices);
    }

    private Vector3[] getVertices(BoundingBox boundingBox) {
        boundingBox.getCorner000(tempVertices[0]);
        boundingBox.getCorner001(tempVertices[1]);
        boundingBox.getCorner010(tempVertices[2]);
        boundingBox.getCorner011(tempVertices[3]);
        boundingBox.getCorner100(tempVertices[4]);
        boundingBox.getCorner101(tempVertices[5]);
        boundingBox.getCorner110(tempVertices[6]);
        boundingBox.getCorner111(tempVertices[7]);
        return tempVertices;
    }

    public void mul(Matrix4 matrix4) {
        this.transform.mul(matrix4);
        update();
    }

    private void update() {
        this.bounds.getCorner000(this.vertices[0]).mul(this.transform);
        this.bounds.getCorner001(this.vertices[1]).mul(this.transform);
        this.bounds.getCorner010(this.vertices[2]).mul(this.transform);
        this.bounds.getCorner011(this.vertices[3]).mul(this.transform);
        this.bounds.getCorner100(this.vertices[4]).mul(this.transform);
        this.bounds.getCorner101(this.vertices[5]).mul(this.transform);
        this.bounds.getCorner110(this.vertices[6]).mul(this.transform);
        this.bounds.getCorner111(this.vertices[7]).mul(this.transform);
        this.axes[0].set(this.transform.val[0], this.transform.val[1], this.transform.val[2]).nor();
        this.axes[1].set(this.transform.val[4], this.transform.val[5], this.transform.val[6]).nor();
        this.axes[2].set(this.transform.val[8], this.transform.val[9], this.transform.val[10]).nor();
        this.inverseTransform.set(this.transform).inv();
    }
}
