package com.badlogic.gdx.graphics.g3d.model;

import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.ArrayMap;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/model/NodePart.class */
public class NodePart {
    public MeshPart meshPart;
    public Material material;
    public ArrayMap<Node, Matrix4> invBoneBindTransforms;
    public Matrix4[] bones;
    public boolean enabled = true;

    public NodePart() {
    }

    public NodePart(MeshPart meshPart, Material material) {
        this.meshPart = meshPart;
        this.material = material;
    }

    public Renderable setRenderable(Renderable renderable) {
        renderable.material = this.material;
        renderable.meshPart.set(this.meshPart);
        renderable.bones = this.bones;
        return renderable;
    }

    public NodePart copy() {
        return new NodePart().set(this);
    }

    protected NodePart set(NodePart nodePart) {
        this.meshPart = new MeshPart(nodePart.meshPart);
        this.material = nodePart.material;
        this.enabled = nodePart.enabled;
        if (nodePart.invBoneBindTransforms == null) {
            this.invBoneBindTransforms = null;
            this.bones = null;
        } else {
            if (this.invBoneBindTransforms == null) {
                this.invBoneBindTransforms = new ArrayMap<>(true, nodePart.invBoneBindTransforms.size, Node.class, Matrix4.class);
            } else {
                this.invBoneBindTransforms.clear();
            }
            this.invBoneBindTransforms.putAll(nodePart.invBoneBindTransforms);
            if (this.bones == null || this.bones.length != this.invBoneBindTransforms.size) {
                this.bones = new Matrix4[this.invBoneBindTransforms.size];
            }
            for (int i = 0; i < this.bones.length; i++) {
                if (this.bones[i] == null) {
                    this.bones[i] = new Matrix4();
                }
            }
        }
        return this;
    }
}
