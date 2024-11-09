package com.badlogic.gdx.graphics.g3d;

import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.model.Animation;
import com.badlogic.gdx.graphics.g3d.model.MeshPart;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.model.NodeAnimation;
import com.badlogic.gdx.graphics.g3d.model.NodeKeyframe;
import com.badlogic.gdx.graphics.g3d.model.NodePart;
import com.badlogic.gdx.graphics.g3d.model.data.ModelAnimation;
import com.badlogic.gdx.graphics.g3d.model.data.ModelData;
import com.badlogic.gdx.graphics.g3d.model.data.ModelMaterial;
import com.badlogic.gdx.graphics.g3d.model.data.ModelMesh;
import com.badlogic.gdx.graphics.g3d.model.data.ModelMeshPart;
import com.badlogic.gdx.graphics.g3d.model.data.ModelNode;
import com.badlogic.gdx.graphics.g3d.model.data.ModelNodeAnimation;
import com.badlogic.gdx.graphics.g3d.model.data.ModelNodeKeyframe;
import com.badlogic.gdx.graphics.g3d.model.data.ModelNodePart;
import com.badlogic.gdx.graphics.g3d.model.data.ModelTexture;
import com.badlogic.gdx.graphics.g3d.utils.TextureDescriptor;
import com.badlogic.gdx.graphics.g3d.utils.TextureProvider;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.ObjectMap;
import java.nio.ShortBuffer;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/Model.class */
public class Model implements Disposable {
    public final Array<Material> materials;
    public final Array<Node> nodes;
    public final Array<Animation> animations;
    public final Array<Mesh> meshes;
    public final Array<MeshPart> meshParts;
    protected final Array<Disposable> disposables;
    private ObjectMap<NodePart, ArrayMap<String, Matrix4>> nodePartBones;

    public Model() {
        this.materials = new Array<>();
        this.nodes = new Array<>();
        this.animations = new Array<>();
        this.meshes = new Array<>();
        this.meshParts = new Array<>();
        this.disposables = new Array<>();
        this.nodePartBones = new ObjectMap<>();
    }

    public Model(ModelData modelData) {
        this(modelData, new TextureProvider.FileTextureProvider());
    }

    public Model(ModelData modelData, TextureProvider textureProvider) {
        this.materials = new Array<>();
        this.nodes = new Array<>();
        this.animations = new Array<>();
        this.meshes = new Array<>();
        this.meshParts = new Array<>();
        this.disposables = new Array<>();
        this.nodePartBones = new ObjectMap<>();
        load(modelData, textureProvider);
    }

    protected void load(ModelData modelData, TextureProvider textureProvider) {
        loadMeshes(modelData.meshes);
        loadMaterials(modelData.materials, textureProvider);
        loadNodes(modelData.nodes);
        loadAnimations(modelData.animations);
        calculateTransforms();
    }

    protected void loadAnimations(Iterable<ModelAnimation> iterable) {
        Vector3 vector3;
        Quaternion quaternion;
        Vector3 vector32;
        for (ModelAnimation modelAnimation : iterable) {
            Animation animation = new Animation();
            animation.id = modelAnimation.id;
            Array.ArrayIterator<ModelNodeAnimation> it = modelAnimation.nodeAnimations.iterator();
            while (it.hasNext()) {
                ModelNodeAnimation next = it.next();
                Node node = getNode(next.nodeId);
                if (node != null) {
                    NodeAnimation nodeAnimation = new NodeAnimation();
                    nodeAnimation.node = node;
                    if (next.translation != null) {
                        nodeAnimation.translation = new Array<>();
                        nodeAnimation.translation.ensureCapacity(next.translation.size);
                        Array.ArrayIterator<ModelNodeKeyframe<Vector3>> it2 = next.translation.iterator();
                        while (it2.hasNext()) {
                            ModelNodeKeyframe<Vector3> next2 = it2.next();
                            if (next2.keytime > animation.duration) {
                                animation.duration = next2.keytime;
                            }
                            Array<NodeKeyframe<Vector3>> array = nodeAnimation.translation;
                            float f = next2.keytime;
                            if (next2.value == null) {
                                vector32 = node.translation;
                            } else {
                                vector32 = next2.value;
                            }
                            array.add(new NodeKeyframe<>(f, new Vector3(vector32)));
                        }
                    }
                    if (next.rotation != null) {
                        nodeAnimation.rotation = new Array<>();
                        nodeAnimation.rotation.ensureCapacity(next.rotation.size);
                        Array.ArrayIterator<ModelNodeKeyframe<Quaternion>> it3 = next.rotation.iterator();
                        while (it3.hasNext()) {
                            ModelNodeKeyframe<Quaternion> next3 = it3.next();
                            if (next3.keytime > animation.duration) {
                                animation.duration = next3.keytime;
                            }
                            Array<NodeKeyframe<Quaternion>> array2 = nodeAnimation.rotation;
                            float f2 = next3.keytime;
                            if (next3.value == null) {
                                quaternion = node.rotation;
                            } else {
                                quaternion = next3.value;
                            }
                            array2.add(new NodeKeyframe<>(f2, new Quaternion(quaternion)));
                        }
                    }
                    if (next.scaling != null) {
                        nodeAnimation.scaling = new Array<>();
                        nodeAnimation.scaling.ensureCapacity(next.scaling.size);
                        Array.ArrayIterator<ModelNodeKeyframe<Vector3>> it4 = next.scaling.iterator();
                        while (it4.hasNext()) {
                            ModelNodeKeyframe<Vector3> next4 = it4.next();
                            if (next4.keytime > animation.duration) {
                                animation.duration = next4.keytime;
                            }
                            Array<NodeKeyframe<Vector3>> array3 = nodeAnimation.scaling;
                            float f3 = next4.keytime;
                            if (next4.value == null) {
                                vector3 = node.scale;
                            } else {
                                vector3 = next4.value;
                            }
                            array3.add(new NodeKeyframe<>(f3, new Vector3(vector3)));
                        }
                    }
                    if ((nodeAnimation.translation != null && nodeAnimation.translation.size > 0) || ((nodeAnimation.rotation != null && nodeAnimation.rotation.size > 0) || (nodeAnimation.scaling != null && nodeAnimation.scaling.size > 0))) {
                        animation.nodeAnimations.add(nodeAnimation);
                    }
                }
            }
            if (animation.nodeAnimations.size > 0) {
                this.animations.add(animation);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void loadNodes(Iterable<ModelNode> iterable) {
        this.nodePartBones.clear();
        Iterator<ModelNode> it = iterable.iterator();
        while (it.hasNext()) {
            this.nodes.add(loadNode(it.next()));
        }
        ObjectMap.Entries<NodePart, ArrayMap<String, Matrix4>> it2 = this.nodePartBones.entries().iterator();
        while (it2.hasNext()) {
            ObjectMap.Entry next = it2.next();
            if (((NodePart) next.key).invBoneBindTransforms == null) {
                ((NodePart) next.key).invBoneBindTransforms = new ArrayMap<>(Node.class, Matrix4.class);
            }
            ((NodePart) next.key).invBoneBindTransforms.clear();
            Iterator it3 = ((ArrayMap) next.value).entries().iterator();
            while (it3.hasNext()) {
                ObjectMap.Entry entry = (ObjectMap.Entry) it3.next();
                ((NodePart) next.key).invBoneBindTransforms.put(getNode((String) entry.key), new Matrix4((Matrix4) entry.value).inv());
            }
        }
    }

    protected Node loadNode(ModelNode modelNode) {
        Node node = new Node();
        node.id = modelNode.id;
        if (modelNode.translation != null) {
            node.translation.set(modelNode.translation);
        }
        if (modelNode.rotation != null) {
            node.rotation.set(modelNode.rotation);
        }
        if (modelNode.scale != null) {
            node.scale.set(modelNode.scale);
        }
        if (modelNode.parts != null) {
            for (ModelNodePart modelNodePart : modelNode.parts) {
                MeshPart meshPart = null;
                Material material = null;
                if (modelNodePart.meshPartId != null) {
                    Array.ArrayIterator<MeshPart> it = this.meshParts.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        MeshPart next = it.next();
                        if (modelNodePart.meshPartId.equals(next.id)) {
                            meshPart = next;
                            break;
                        }
                    }
                }
                if (modelNodePart.materialId != null) {
                    Array.ArrayIterator<Material> it2 = this.materials.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        Material next2 = it2.next();
                        if (modelNodePart.materialId.equals(next2.id)) {
                            material = next2;
                            break;
                        }
                    }
                }
                if (meshPart == null || material == null) {
                    throw new GdxRuntimeException("Invalid node: " + node.id);
                }
                NodePart nodePart = new NodePart();
                nodePart.meshPart = meshPart;
                nodePart.material = material;
                node.parts.add(nodePart);
                if (modelNodePart.bones != null) {
                    this.nodePartBones.put(nodePart, modelNodePart.bones);
                }
            }
        }
        if (modelNode.children != null) {
            for (ModelNode modelNode2 : modelNode.children) {
                node.addChild(loadNode(modelNode2));
            }
        }
        return node;
    }

    protected void loadMeshes(Iterable<ModelMesh> iterable) {
        Iterator<ModelMesh> it = iterable.iterator();
        while (it.hasNext()) {
            convertMesh(it.next());
        }
    }

    protected void convertMesh(ModelMesh modelMesh) {
        int i = 0;
        for (ModelMeshPart modelMeshPart : modelMesh.parts) {
            i += modelMeshPart.indices.length;
        }
        boolean z = i > 0;
        VertexAttributes vertexAttributes = new VertexAttributes(modelMesh.attributes);
        int length = modelMesh.vertices.length / (vertexAttributes.vertexSize / 4);
        Mesh mesh = new Mesh(true, length, i, vertexAttributes);
        this.meshes.add(mesh);
        this.disposables.add(mesh);
        BufferUtils.copy(modelMesh.vertices, mesh.getVerticesBuffer(true), modelMesh.vertices.length, 0);
        int i2 = 0;
        ShortBuffer indicesBuffer = mesh.getIndicesBuffer(true);
        indicesBuffer.clear();
        for (ModelMeshPart modelMeshPart2 : modelMesh.parts) {
            MeshPart meshPart = new MeshPart();
            meshPart.id = modelMeshPart2.id;
            meshPart.primitiveType = modelMeshPart2.primitiveType;
            meshPart.offset = i2;
            meshPart.size = z ? modelMeshPart2.indices.length : length;
            meshPart.mesh = mesh;
            if (z) {
                indicesBuffer.put(modelMeshPart2.indices);
            }
            i2 += meshPart.size;
            this.meshParts.add(meshPart);
        }
        indicesBuffer.position(0);
        Array.ArrayIterator<MeshPart> it = this.meshParts.iterator();
        while (it.hasNext()) {
            it.next().update();
        }
    }

    protected void loadMaterials(Iterable<ModelMaterial> iterable, TextureProvider textureProvider) {
        Iterator<ModelMaterial> it = iterable.iterator();
        while (it.hasNext()) {
            this.materials.add(convertMaterial(it.next(), textureProvider));
        }
    }

    protected Material convertMaterial(ModelMaterial modelMaterial, TextureProvider textureProvider) {
        Texture load;
        Material material = new Material();
        material.id = modelMaterial.id;
        if (modelMaterial.ambient != null) {
            material.set(new ColorAttribute(ColorAttribute.Ambient, modelMaterial.ambient));
        }
        if (modelMaterial.diffuse != null) {
            material.set(new ColorAttribute(ColorAttribute.Diffuse, modelMaterial.diffuse));
        }
        if (modelMaterial.specular != null) {
            material.set(new ColorAttribute(ColorAttribute.Specular, modelMaterial.specular));
        }
        if (modelMaterial.emissive != null) {
            material.set(new ColorAttribute(ColorAttribute.Emissive, modelMaterial.emissive));
        }
        if (modelMaterial.reflection != null) {
            material.set(new ColorAttribute(ColorAttribute.Reflection, modelMaterial.reflection));
        }
        if (modelMaterial.shininess > 0.0f) {
            material.set(new FloatAttribute(FloatAttribute.Shininess, modelMaterial.shininess));
        }
        if (modelMaterial.opacity != 1.0f) {
            material.set(new BlendingAttribute(770, 771, modelMaterial.opacity));
        }
        ObjectMap objectMap = new ObjectMap();
        if (modelMaterial.textures != null) {
            Array.ArrayIterator<ModelTexture> it = modelMaterial.textures.iterator();
            while (it.hasNext()) {
                ModelTexture next = it.next();
                if (objectMap.containsKey(next.fileName)) {
                    load = (Texture) objectMap.get(next.fileName);
                } else {
                    load = textureProvider.load(next.fileName);
                    objectMap.put(next.fileName, load);
                    this.disposables.add(load);
                }
                TextureDescriptor textureDescriptor = new TextureDescriptor(load);
                textureDescriptor.minFilter = load.getMinFilter();
                textureDescriptor.magFilter = load.getMagFilter();
                textureDescriptor.uWrap = load.getUWrap();
                textureDescriptor.vWrap = load.getVWrap();
                float f = next.uvTranslation == null ? 0.0f : next.uvTranslation.x;
                float f2 = next.uvTranslation == null ? 0.0f : next.uvTranslation.y;
                float f3 = next.uvScaling == null ? 1.0f : next.uvScaling.x;
                float f4 = next.uvScaling == null ? 1.0f : next.uvScaling.y;
                switch (next.usage) {
                    case 2:
                        material.set(new TextureAttribute(TextureAttribute.Diffuse, textureDescriptor, f, f2, f3, f4));
                        break;
                    case 3:
                        material.set(new TextureAttribute(TextureAttribute.Emissive, textureDescriptor, f, f2, f3, f4));
                        break;
                    case 4:
                        material.set(new TextureAttribute(TextureAttribute.Ambient, textureDescriptor, f, f2, f3, f4));
                        break;
                    case 5:
                        material.set(new TextureAttribute(TextureAttribute.Specular, textureDescriptor, f, f2, f3, f4));
                        break;
                    case 7:
                        material.set(new TextureAttribute(TextureAttribute.Normal, textureDescriptor, f, f2, f3, f4));
                        break;
                    case 8:
                        material.set(new TextureAttribute(TextureAttribute.Bump, textureDescriptor, f, f2, f3, f4));
                        break;
                    case 10:
                        material.set(new TextureAttribute(TextureAttribute.Reflection, textureDescriptor, f, f2, f3, f4));
                        break;
                }
            }
        }
        return material;
    }

    public void manageDisposable(Disposable disposable) {
        if (!this.disposables.contains(disposable, true)) {
            this.disposables.add(disposable);
        }
    }

    public Iterable<Disposable> getManagedDisposables() {
        return this.disposables;
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        Array.ArrayIterator<Disposable> it = this.disposables.iterator();
        while (it.hasNext()) {
            it.next().dispose();
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
        return getAnimation(str, true);
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
