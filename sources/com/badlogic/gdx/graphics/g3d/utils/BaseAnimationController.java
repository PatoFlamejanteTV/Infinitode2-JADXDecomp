package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.model.Animation;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.model.NodeAnimation;
import com.badlogic.gdx.graphics.g3d.model.NodeKeyframe;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.Pool;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/utils/BaseAnimationController.class */
public class BaseAnimationController {
    private final Pool<Transform> transformPool = new Pool<Transform>() { // from class: com.badlogic.gdx.graphics.g3d.utils.BaseAnimationController.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.badlogic.gdx.utils.Pool
        public Transform newObject() {
            return new Transform();
        }
    };
    private boolean applying = false;
    public final ModelInstance target;
    private static final ObjectMap<Node, Transform> transforms = new ObjectMap<>();
    private static final Transform tmpT = new Transform();

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/utils/BaseAnimationController$Transform.class */
    public static final class Transform implements Pool.Poolable {
        public final Vector3 translation = new Vector3();
        public final Quaternion rotation = new Quaternion();
        public final Vector3 scale = new Vector3(1.0f, 1.0f, 1.0f);

        public final Transform idt() {
            this.translation.set(0.0f, 0.0f, 0.0f);
            this.rotation.idt();
            this.scale.set(1.0f, 1.0f, 1.0f);
            return this;
        }

        public final Transform set(Vector3 vector3, Quaternion quaternion, Vector3 vector32) {
            this.translation.set(vector3);
            this.rotation.set(quaternion);
            this.scale.set(vector32);
            return this;
        }

        public final Transform set(Transform transform) {
            return set(transform.translation, transform.rotation, transform.scale);
        }

        public final Transform lerp(Transform transform, float f) {
            return lerp(transform.translation, transform.rotation, transform.scale, f);
        }

        public final Transform lerp(Vector3 vector3, Quaternion quaternion, Vector3 vector32, float f) {
            this.translation.lerp(vector3, f);
            this.rotation.slerp(quaternion, f);
            this.scale.lerp(vector32, f);
            return this;
        }

        public final Matrix4 toMatrix4(Matrix4 matrix4) {
            return matrix4.set(this.translation, this.rotation, this.scale);
        }

        @Override // com.badlogic.gdx.utils.Pool.Poolable
        public final void reset() {
            idt();
        }

        public final String toString() {
            return this.translation.toString() + " - " + this.rotation.toString() + " - " + this.scale.toString();
        }
    }

    public BaseAnimationController(ModelInstance modelInstance) {
        this.target = modelInstance;
    }

    protected void begin() {
        if (this.applying) {
            throw new GdxRuntimeException("You must call end() after each call to being()");
        }
        this.applying = true;
    }

    protected void apply(Animation animation, float f, float f2) {
        if (!this.applying) {
            throw new GdxRuntimeException("You must call begin() before adding an animation");
        }
        applyAnimation(transforms, this.transformPool, f2, animation, f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void end() {
        if (!this.applying) {
            throw new GdxRuntimeException("You must call begin() first");
        }
        ObjectMap.Entries<Node, Transform> it = transforms.entries().iterator();
        while (it.hasNext()) {
            ObjectMap.Entry next = it.next();
            ((Transform) next.value).toMatrix4(((Node) next.key).localTransform);
            this.transformPool.free(next.value);
        }
        transforms.clear();
        this.target.calculateTransforms();
        this.applying = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void applyAnimation(Animation animation, float f) {
        if (this.applying) {
            throw new GdxRuntimeException("Call end() first");
        }
        applyAnimation(null, null, 1.0f, animation, f);
        this.target.calculateTransforms();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void applyAnimations(Animation animation, float f, Animation animation2, float f2, float f3) {
        if (animation2 == null || f3 == 0.0f) {
            applyAnimation(animation, f);
            return;
        }
        if (animation == null || f3 == 1.0f) {
            applyAnimation(animation2, f2);
        } else {
            if (this.applying) {
                throw new GdxRuntimeException("Call end() first");
            }
            begin();
            apply(animation, f, 1.0f);
            apply(animation2, f2, f3);
            end();
        }
    }

    static final <T> int getFirstKeyframeIndexAtTime(Array<NodeKeyframe<T>> array, float f) {
        int i = array.size - 1;
        if (i <= 0 || f < array.get(0).keytime || f > array.get(i).keytime) {
            return 0;
        }
        int i2 = 0;
        int i3 = i;
        while (i2 < i3) {
            int i4 = (i2 + i3) / 2;
            if (f > array.get(i4 + 1).keytime) {
                i2 = i4 + 1;
            } else if (f < array.get(i4).keytime) {
                i3 = i4 - 1;
            } else {
                return i4;
            }
        }
        return i2;
    }

    private static final Vector3 getTranslationAtTime(NodeAnimation nodeAnimation, float f, Vector3 vector3) {
        if (nodeAnimation.translation == null) {
            return vector3.set(nodeAnimation.node.translation);
        }
        if (nodeAnimation.translation.size == 1) {
            return vector3.set(nodeAnimation.translation.get(0).value);
        }
        int firstKeyframeIndexAtTime = getFirstKeyframeIndexAtTime(nodeAnimation.translation, f);
        NodeKeyframe<Vector3> nodeKeyframe = nodeAnimation.translation.get(firstKeyframeIndexAtTime);
        vector3.set(nodeKeyframe.value);
        int i = firstKeyframeIndexAtTime + 1;
        if (i < nodeAnimation.translation.size) {
            NodeKeyframe<Vector3> nodeKeyframe2 = nodeAnimation.translation.get(i);
            vector3.lerp(nodeKeyframe2.value, (f - nodeKeyframe.keytime) / (nodeKeyframe2.keytime - nodeKeyframe.keytime));
        }
        return vector3;
    }

    private static final Quaternion getRotationAtTime(NodeAnimation nodeAnimation, float f, Quaternion quaternion) {
        if (nodeAnimation.rotation == null) {
            return quaternion.set(nodeAnimation.node.rotation);
        }
        if (nodeAnimation.rotation.size == 1) {
            return quaternion.set(nodeAnimation.rotation.get(0).value);
        }
        int firstKeyframeIndexAtTime = getFirstKeyframeIndexAtTime(nodeAnimation.rotation, f);
        NodeKeyframe<Quaternion> nodeKeyframe = nodeAnimation.rotation.get(firstKeyframeIndexAtTime);
        quaternion.set(nodeKeyframe.value);
        int i = firstKeyframeIndexAtTime + 1;
        if (i < nodeAnimation.rotation.size) {
            NodeKeyframe<Quaternion> nodeKeyframe2 = nodeAnimation.rotation.get(i);
            quaternion.slerp(nodeKeyframe2.value, (f - nodeKeyframe.keytime) / (nodeKeyframe2.keytime - nodeKeyframe.keytime));
        }
        return quaternion;
    }

    private static final Vector3 getScalingAtTime(NodeAnimation nodeAnimation, float f, Vector3 vector3) {
        if (nodeAnimation.scaling == null) {
            return vector3.set(nodeAnimation.node.scale);
        }
        if (nodeAnimation.scaling.size == 1) {
            return vector3.set(nodeAnimation.scaling.get(0).value);
        }
        int firstKeyframeIndexAtTime = getFirstKeyframeIndexAtTime(nodeAnimation.scaling, f);
        NodeKeyframe<Vector3> nodeKeyframe = nodeAnimation.scaling.get(firstKeyframeIndexAtTime);
        vector3.set(nodeKeyframe.value);
        int i = firstKeyframeIndexAtTime + 1;
        if (i < nodeAnimation.scaling.size) {
            NodeKeyframe<Vector3> nodeKeyframe2 = nodeAnimation.scaling.get(i);
            vector3.lerp(nodeKeyframe2.value, (f - nodeKeyframe.keytime) / (nodeKeyframe2.keytime - nodeKeyframe.keytime));
        }
        return vector3;
    }

    private static final Transform getNodeAnimationTransform(NodeAnimation nodeAnimation, float f) {
        Transform transform = tmpT;
        getTranslationAtTime(nodeAnimation, f, transform.translation);
        getRotationAtTime(nodeAnimation, f, transform.rotation);
        getScalingAtTime(nodeAnimation, f, transform.scale);
        return transform;
    }

    private static final void applyNodeAnimationDirectly(NodeAnimation nodeAnimation, float f) {
        Node node = nodeAnimation.node;
        node.isAnimated = true;
        getNodeAnimationTransform(nodeAnimation, f).toMatrix4(node.localTransform);
    }

    private static final void applyNodeAnimationBlending(NodeAnimation nodeAnimation, ObjectMap<Node, Transform> objectMap, Pool<Transform> pool, float f, float f2) {
        Node node = nodeAnimation.node;
        node.isAnimated = true;
        Transform nodeAnimationTransform = getNodeAnimationTransform(nodeAnimation, f2);
        Transform transform = objectMap.get(node, null);
        if (transform != null) {
            if (f > 0.999999f) {
                transform.set(nodeAnimationTransform);
                return;
            } else {
                transform.lerp(nodeAnimationTransform, f);
                return;
            }
        }
        if (f > 0.999999f) {
            objectMap.put(node, pool.obtain().set(nodeAnimationTransform));
        } else {
            objectMap.put(node, pool.obtain().set(node.translation, node.rotation, node.scale).lerp(nodeAnimationTransform, f));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected static void applyAnimation(ObjectMap<Node, Transform> objectMap, Pool<Transform> pool, float f, Animation animation, float f2) {
        if (objectMap == null) {
            Array.ArrayIterator<NodeAnimation> it = animation.nodeAnimations.iterator();
            while (it.hasNext()) {
                applyNodeAnimationDirectly(it.next(), f2);
            }
            return;
        }
        ObjectMap.Keys<Node> it2 = objectMap.keys().iterator();
        while (it2.hasNext()) {
            it2.next().isAnimated = false;
        }
        Array.ArrayIterator<NodeAnimation> it3 = animation.nodeAnimations.iterator();
        while (it3.hasNext()) {
            applyNodeAnimationBlending(it3.next(), objectMap, pool, f, f2);
        }
        ObjectMap.Entries<Node, Transform> it4 = objectMap.entries().iterator();
        while (it4.hasNext()) {
            ObjectMap.Entry next = it4.next();
            if (!((Node) next.key).isAnimated) {
                ((Node) next.key).isAnimated = true;
                ((Transform) next.value).lerp(((Node) next.key).translation, ((Node) next.key).rotation, ((Node) next.key).scale, f);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void removeAnimation(Animation animation) {
        Array.ArrayIterator<NodeAnimation> it = animation.nodeAnimations.iterator();
        while (it.hasNext()) {
            it.next().node.isAnimated = false;
        }
    }
}
