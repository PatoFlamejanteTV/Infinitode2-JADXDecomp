package com.badlogic.gdx.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Frustum;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/Camera.class */
public abstract class Camera {
    public final Vector3 position = new Vector3();
    public final Vector3 direction = new Vector3(0.0f, 0.0f, -1.0f);
    public final Vector3 up = new Vector3(0.0f, 1.0f, 0.0f);
    public final Matrix4 projection = new Matrix4();
    public final Matrix4 view = new Matrix4();
    public final Matrix4 combined = new Matrix4();
    public final Matrix4 invProjectionView = new Matrix4();
    public float near = 1.0f;
    public float far = 100.0f;
    public float viewportWidth = 0.0f;
    public float viewportHeight = 0.0f;
    public final Frustum frustum = new Frustum();
    private final Vector3 tmpVec = new Vector3();
    private final Ray ray = new Ray(new Vector3(), new Vector3());

    public abstract void update();

    public abstract void update(boolean z);

    public void lookAt(float f, float f2, float f3) {
        this.tmpVec.set(f, f2, f3).sub(this.position).nor();
        if (!this.tmpVec.isZero()) {
            float dot = this.tmpVec.dot(this.up);
            if (Math.abs(dot - 1.0f) < 1.0E-9f) {
                this.up.set(this.direction).scl(-1.0f);
            } else if (Math.abs(dot + 1.0f) < 1.0E-9f) {
                this.up.set(this.direction);
            }
            this.direction.set(this.tmpVec);
            normalizeUp();
        }
    }

    public void lookAt(Vector3 vector3) {
        lookAt(vector3.x, vector3.y, vector3.z);
    }

    public void normalizeUp() {
        this.tmpVec.set(this.direction).crs(this.up);
        this.up.set(this.tmpVec).crs(this.direction).nor();
    }

    public void rotate(float f, float f2, float f3, float f4) {
        this.direction.rotate(f, f2, f3, f4);
        this.up.rotate(f, f2, f3, f4);
    }

    public void rotate(Vector3 vector3, float f) {
        this.direction.rotate(vector3, f);
        this.up.rotate(vector3, f);
    }

    public void rotate(Matrix4 matrix4) {
        this.direction.rot(matrix4);
        this.up.rot(matrix4);
    }

    public void rotate(Quaternion quaternion) {
        quaternion.transform(this.direction);
        quaternion.transform(this.up);
    }

    public void rotateAround(Vector3 vector3, Vector3 vector32, float f) {
        this.tmpVec.set(vector3);
        this.tmpVec.sub(this.position);
        translate(this.tmpVec);
        rotate(vector32, f);
        this.tmpVec.rotate(vector32, f);
        translate(-this.tmpVec.x, -this.tmpVec.y, -this.tmpVec.z);
    }

    public void transform(Matrix4 matrix4) {
        this.position.mul(matrix4);
        rotate(matrix4);
    }

    public void translate(float f, float f2, float f3) {
        this.position.add(f, f2, f3);
    }

    public void translate(Vector3 vector3) {
        this.position.add(vector3);
    }

    public Vector3 unproject(Vector3 vector3, float f, float f2, float f3, float f4) {
        float f5 = vector3.x - f;
        float height = (Gdx.graphics.getHeight() - vector3.y) - f2;
        vector3.x = ((f5 * 2.0f) / f3) - 1.0f;
        vector3.y = ((height * 2.0f) / f4) - 1.0f;
        vector3.z = (2.0f * vector3.z) - 1.0f;
        vector3.prj(this.invProjectionView);
        return vector3;
    }

    public Vector3 unproject(Vector3 vector3) {
        unproject(vector3, 0.0f, 0.0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        return vector3;
    }

    public Vector3 project(Vector3 vector3) {
        project(vector3, 0.0f, 0.0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        return vector3;
    }

    public Vector3 project(Vector3 vector3, float f, float f2, float f3, float f4) {
        vector3.prj(this.combined);
        vector3.x = ((f3 * (vector3.x + 1.0f)) / 2.0f) + f;
        vector3.y = ((f4 * (vector3.y + 1.0f)) / 2.0f) + f2;
        vector3.z = (vector3.z + 1.0f) / 2.0f;
        return vector3;
    }

    public Ray getPickRay(float f, float f2, float f3, float f4, float f5, float f6) {
        unproject(this.ray.origin.set(f, f2, 0.0f), f3, f4, f5, f6);
        unproject(this.ray.direction.set(f, f2, 1.0f), f3, f4, f5, f6);
        this.ray.direction.sub(this.ray.origin).nor();
        return this.ray;
    }

    public Ray getPickRay(float f, float f2) {
        return getPickRay(f, f2, 0.0f, 0.0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }
}
