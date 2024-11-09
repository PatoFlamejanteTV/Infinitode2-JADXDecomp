package com.badlogic.gdx.graphics.g3d.model;

import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/model/MeshPart.class */
public class MeshPart {
    public String id;
    public int primitiveType;
    public int offset;
    public int size;
    public Mesh mesh;
    public final Vector3 center = new Vector3();
    public final Vector3 halfExtents = new Vector3();
    public float radius = -1.0f;
    private static final BoundingBox bounds = new BoundingBox();

    public MeshPart() {
    }

    public MeshPart(String str, Mesh mesh, int i, int i2, int i3) {
        set(str, mesh, i, i2, i3);
    }

    public MeshPart(MeshPart meshPart) {
        set(meshPart);
    }

    public MeshPart set(MeshPart meshPart) {
        this.id = meshPart.id;
        this.mesh = meshPart.mesh;
        this.offset = meshPart.offset;
        this.size = meshPart.size;
        this.primitiveType = meshPart.primitiveType;
        this.center.set(meshPart.center);
        this.halfExtents.set(meshPart.halfExtents);
        this.radius = meshPart.radius;
        return this;
    }

    public MeshPart set(String str, Mesh mesh, int i, int i2, int i3) {
        this.id = str;
        this.mesh = mesh;
        this.offset = i;
        this.size = i2;
        this.primitiveType = i3;
        this.center.set(0.0f, 0.0f, 0.0f);
        this.halfExtents.set(0.0f, 0.0f, 0.0f);
        this.radius = -1.0f;
        return this;
    }

    public void update() {
        this.mesh.calculateBoundingBox(bounds, this.offset, this.size);
        bounds.getCenter(this.center);
        bounds.getDimensions(this.halfExtents).scl(0.5f);
        this.radius = this.halfExtents.len();
    }

    public boolean equals(MeshPart meshPart) {
        if (meshPart != this) {
            return meshPart != null && meshPart.mesh == this.mesh && meshPart.primitiveType == this.primitiveType && meshPart.offset == this.offset && meshPart.size == this.size;
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof MeshPart) {
            return equals((MeshPart) obj);
        }
        return false;
    }

    public void render(ShaderProgram shaderProgram, boolean z) {
        this.mesh.render(shaderProgram, this.primitiveType, this.offset, this.size, z);
    }

    public void render(ShaderProgram shaderProgram) {
        this.mesh.render(shaderProgram, this.primitiveType, this.offset, this.size);
    }
}
