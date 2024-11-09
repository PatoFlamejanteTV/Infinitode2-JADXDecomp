package com.badlogic.gdx.graphics.g3d;

import com.badlogic.gdx.graphics.g3d.model.Animation;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.model.NodeAnimation;
import com.badlogic.gdx.graphics.g3d.model.NodeKeyframe;
import com.badlogic.gdx.graphics.g3d.model.NodePart;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.Pool;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/ModelInstance.class */
public class ModelInstance implements RenderableProvider {
    public static boolean defaultShareKeyframes = true;
    public final Array<Material> materials;
    public final Array<Node> nodes;
    public final Array<Animation> animations;
    public final Model model;
    public Matrix4 transform;
    public Object userData;

    public ModelInstance(Model model) {
        this(model, (String[]) null);
    }

    public ModelInstance(Model model, String str, boolean z) {
        this(model, null, str, false, false, z);
    }

    public ModelInstance(Model model, Matrix4 matrix4, String str, boolean z) {
        this(model, matrix4, str, false, false, z);
    }

    public ModelInstance(Model model, String str, boolean z, boolean z2) {
        this(model, null, str, true, z, z2);
    }

    public ModelInstance(Model model, Matrix4 matrix4, String str, boolean z, boolean z2) {
        this(model, matrix4, str, true, z, z2);
    }

    public ModelInstance(Model model, String str, boolean z, boolean z2, boolean z3) {
        this(model, null, str, z, z2, z3);
    }

    public ModelInstance(Model model, Matrix4 matrix4, String str, boolean z, boolean z2, boolean z3) {
        this(model, matrix4, str, z, z2, z3, defaultShareKeyframes);
    }

    public ModelInstance(Model model, Matrix4 matrix4, String str, boolean z, boolean z2, boolean z3, boolean z4) {
        this.materials = new Array<>();
        this.nodes = new Array<>();
        this.animations = new Array<>();
        this.model = model;
        this.transform = matrix4 == null ? new Matrix4() : matrix4;
        Node node = model.getNode(str, z);
        Array<Node> array = this.nodes;
        Node copy = node.copy();
        array.add(copy);
        if (z3) {
            this.transform.mul(z2 ? node.globalTransform : node.localTransform);
            copy.translation.set(0.0f, 0.0f, 0.0f);
            copy.rotation.idt();
            copy.scale.set(1.0f, 1.0f, 1.0f);
        } else if (z2 && copy.hasParent()) {
            this.transform.mul(node.getParent().globalTransform);
        }
        invalidate();
        copyAnimations(model.animations, z4);
        calculateTransforms();
    }

    public ModelInstance(Model model, String... strArr) {
        this(model, (Matrix4) null, strArr);
    }

    public ModelInstance(Model model, Matrix4 matrix4, String... strArr) {
        this.materials = new Array<>();
        this.nodes = new Array<>();
        this.animations = new Array<>();
        this.model = model;
        this.transform = matrix4 == null ? new Matrix4() : matrix4;
        if (strArr == null) {
            copyNodes(model.nodes);
        } else {
            copyNodes(model.nodes, strArr);
        }
        copyAnimations(model.animations, defaultShareKeyframes);
        calculateTransforms();
    }

    public ModelInstance(Model model, Array<String> array) {
        this(model, (Matrix4) null, array);
    }

    public ModelInstance(Model model, Matrix4 matrix4, Array<String> array) {
        this(model, matrix4, array, defaultShareKeyframes);
    }

    public ModelInstance(Model model, Matrix4 matrix4, Array<String> array, boolean z) {
        this.materials = new Array<>();
        this.nodes = new Array<>();
        this.animations = new Array<>();
        this.model = model;
        this.transform = matrix4 == null ? new Matrix4() : matrix4;
        copyNodes(model.nodes, array);
        copyAnimations(model.animations, z);
        calculateTransforms();
    }

    public ModelInstance(Model model, Vector3 vector3) {
        this(model);
        this.transform.setToTranslation(vector3);
    }

    public ModelInstance(Model model, float f, float f2, float f3) {
        this(model);
        this.transform.setToTranslation(f, f2, f3);
    }

    public ModelInstance(Model model, Matrix4 matrix4) {
        this(model, matrix4, (String[]) null);
    }

    public ModelInstance(ModelInstance modelInstance) {
        this(modelInstance, modelInstance.transform.cpy());
    }

    public ModelInstance(ModelInstance modelInstance, Matrix4 matrix4) {
        this(modelInstance, matrix4, defaultShareKeyframes);
    }

    public ModelInstance(ModelInstance modelInstance, Matrix4 matrix4, boolean z) {
        this.materials = new Array<>();
        this.nodes = new Array<>();
        this.animations = new Array<>();
        this.model = modelInstance.model;
        this.transform = matrix4 == null ? new Matrix4() : matrix4;
        copyNodes(modelInstance.nodes);
        copyAnimations(modelInstance.animations, z);
        calculateTransforms();
    }

    public ModelInstance copy() {
        return new ModelInstance(this);
    }

    private void copyNodes(Array<Node> array) {
        int i = array.size;
        for (int i2 = 0; i2 < i; i2++) {
            this.nodes.add(array.get(i2).copy());
        }
        invalidate();
    }

    private void copyNodes(Array<Node> array, String... strArr) {
        int i = array.size;
        for (int i2 = 0; i2 < i; i2++) {
            Node node = array.get(i2);
            int length = strArr.length;
            int i3 = 0;
            while (true) {
                if (i3 >= length) {
                    break;
                }
                if (!strArr[i3].equals(node.id)) {
                    i3++;
                } else {
                    this.nodes.add(node.copy());
                    break;
                }
            }
        }
        invalidate();
    }

    private void copyNodes(Array<Node> array, Array<String> array2) {
        int i = array.size;
        for (int i2 = 0; i2 < i; i2++) {
            Node node = array.get(i2);
            Array.ArrayIterator<String> it = array2.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().equals(node.id)) {
                        this.nodes.add(node.copy());
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        invalidate();
    }

    private void invalidate(Node node) {
        int i = node.parts.size;
        for (int i2 = 0; i2 < i; i2++) {
            NodePart nodePart = node.parts.get(i2);
            ArrayMap<Node, Matrix4> arrayMap = nodePart.invBoneBindTransforms;
            if (arrayMap != null) {
                for (int i3 = 0; i3 < arrayMap.size; i3++) {
                    arrayMap.keys[i3] = getNode(arrayMap.keys[i3].id);
                }
            }
            if (!this.materials.contains(nodePart.material, true)) {
                int indexOf = this.materials.indexOf(nodePart.material, false);
                if (indexOf < 0) {
                    Array<Material> array = this.materials;
                    Material copy = nodePart.material.copy();
                    nodePart.material = copy;
                    array.add(copy);
                } else {
                    nodePart.material = this.materials.get(indexOf);
                }
            }
        }
        int childCount = node.getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            invalidate(node.getChild(i4));
        }
    }

    private void invalidate() {
        int i = this.nodes.size;
        for (int i2 = 0; i2 < i; i2++) {
            invalidate(this.nodes.get(i2));
        }
    }

    public void copyAnimations(Iterable<Animation> iterable) {
        Iterator<Animation> it = iterable.iterator();
        while (it.hasNext()) {
            copyAnimation(it.next(), defaultShareKeyframes);
        }
    }

    public void copyAnimations(Iterable<Animation> iterable, boolean z) {
        Iterator<Animation> it = iterable.iterator();
        while (it.hasNext()) {
            copyAnimation(it.next(), z);
        }
    }

    public void copyAnimation(Animation animation) {
        copyAnimation(animation, defaultShareKeyframes);
    }

    public void copyAnimation(Animation animation, boolean z) {
        Animation animation2 = new Animation();
        animation2.id = animation.id;
        animation2.duration = animation.duration;
        Array.ArrayIterator<NodeAnimation> it = animation.nodeAnimations.iterator();
        while (it.hasNext()) {
            NodeAnimation next = it.next();
            Node node = getNode(next.node.id);
            if (node != null) {
                NodeAnimation nodeAnimation = new NodeAnimation();
                nodeAnimation.node = node;
                if (z) {
                    nodeAnimation.translation = next.translation;
                    nodeAnimation.rotation = next.rotation;
                    nodeAnimation.scaling = next.scaling;
                } else {
                    if (next.translation != null) {
                        nodeAnimation.translation = new Array<>();
                        Array.ArrayIterator<NodeKeyframe<Vector3>> it2 = next.translation.iterator();
                        while (it2.hasNext()) {
                            NodeKeyframe<Vector3> next2 = it2.next();
                            nodeAnimation.translation.add(new NodeKeyframe<>(next2.keytime, next2.value));
                        }
                    }
                    if (next.rotation != null) {
                        nodeAnimation.rotation = new Array<>();
                        Array.ArrayIterator<NodeKeyframe<Quaternion>> it3 = next.rotation.iterator();
                        while (it3.hasNext()) {
                            NodeKeyframe<Quaternion> next3 = it3.next();
                            nodeAnimation.rotation.add(new NodeKeyframe<>(next3.keytime, next3.value));
                        }
                    }
                    if (next.scaling != null) {
                        nodeAnimation.scaling = new Array<>();
                        Array.ArrayIterator<NodeKeyframe<Vector3>> it4 = next.scaling.iterator();
                        while (it4.hasNext()) {
                            NodeKeyframe<Vector3> next4 = it4.next();
                            nodeAnimation.scaling.add(new NodeKeyframe<>(next4.keytime, next4.value));
                        }
                    }
                }
                if (nodeAnimation.translation != null || nodeAnimation.rotation != null || nodeAnimation.scaling != null) {
                    animation2.nodeAnimations.add(nodeAnimation);
                }
            }
        }
        if (animation2.nodeAnimations.size > 0) {
            this.animations.add(animation2);
        }
    }

    @Override // com.badlogic.gdx.graphics.g3d.RenderableProvider
    public void getRenderables(Array<Renderable> array, Pool<Renderable> pool) {
        Array.ArrayIterator<Node> it = this.nodes.iterator();
        while (it.hasNext()) {
            getRenderables(it.next(), array, pool);
        }
    }

    public Renderable getRenderable(Renderable renderable) {
        return getRenderable(renderable, this.nodes.get(0));
    }

    public Renderable getRenderable(Renderable renderable, Node node) {
        return getRenderable(renderable, node, node.parts.get(0));
    }

    public Renderable getRenderable(Renderable renderable, Node node, NodePart nodePart) {
        nodePart.setRenderable(renderable);
        if (nodePart.bones == null && this.transform != null) {
            renderable.worldTransform.set(this.transform).mul(node.globalTransform);
        } else if (this.transform != null) {
            renderable.worldTransform.set(this.transform);
        } else {
            renderable.worldTransform.idt();
        }
        renderable.userData = this.userData;
        return renderable;
    }

    protected void getRenderables(Node node, Array<Renderable> array, Pool<Renderable> pool) {
        if (node.parts.size > 0) {
            Array.ArrayIterator<NodePart> it = node.parts.iterator();
            while (it.hasNext()) {
                NodePart next = it.next();
                if (next.enabled) {
                    array.add(getRenderable(pool.obtain(), node, next));
                }
            }
        }
        Iterator<Node> it2 = node.getChildren().iterator();
        while (it2.hasNext()) {
            getRenderables(it2.next(), array, pool);
        }
    }

    public void calculateTransforms() {
        int i = this.nodes.size;
        for (int i2 = 0; i2 < i; i2++) {
            this.nodes.get(i2).calculateTransforms(true);
        }
        for (int i3 = 0; i3 < i; i3++) {
            this.nodes.get(i3).calculateBoneTransforms(true);
        }
    }

    public BoundingBox calculateBoundingBox(BoundingBox boundingBox) {
        boundingBox.inf();
        return extendBoundingBox(boundingBox);
    }

    public BoundingBox extendBoundingBox(BoundingBox boundingBox) {
        int i = this.nodes.size;
        for (int i2 = 0; i2 < i; i2++) {
            this.nodes.get(i2).extendBoundingBox(boundingBox);
        }
        return boundingBox;
    }

    public Animation getAnimation(String str) {
        return getAnimation(str, false);
    }

    public Animation getAnimation(String str, boolean z) {
        int i = this.animations.size;
        if (z) {
            for (int i2 = 0; i2 < i; i2++) {
                Animation animation = this.animations.get(i2);
                if (animation.id.equalsIgnoreCase(str)) {
                    return animation;
                }
            }
            return null;
        }
        for (int i3 = 0; i3 < i; i3++) {
            Animation animation2 = this.animations.get(i3);
            if (animation2.id.equals(str)) {
                return animation2;
            }
        }
        return null;
    }

    public Material getMaterial(String str) {
        return getMaterial(str, true);
    }

    public Material getMaterial(String str, boolean z) {
        int i = this.materials.size;
        if (z) {
            for (int i2 = 0; i2 < i; i2++) {
                Material material = this.materials.get(i2);
                if (material.id.equalsIgnoreCase(str)) {
                    return material;
                }
            }
            return null;
        }
        for (int i3 = 0; i3 < i; i3++) {
            Material material2 = this.materials.get(i3);
            if (material2.id.equals(str)) {
                return material2;
            }
        }
        return null;
    }

    public Node getNode(String str) {
        return getNode(str, true);
    }

    public Node getNode(String str, boolean z) {
        return getNode(str, z, false);
    }

    public Node getNode(String str, boolean z, boolean z2) {
        return Node.getNode(this.nodes, str, z, z2);
    }
}
