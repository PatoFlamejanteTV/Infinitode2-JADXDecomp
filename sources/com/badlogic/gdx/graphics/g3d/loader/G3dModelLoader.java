package com.badlogic.gdx.graphics.g3d.loader;

import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.g3d.model.data.ModelAnimation;
import com.badlogic.gdx.graphics.g3d.model.data.ModelData;
import com.badlogic.gdx.graphics.g3d.model.data.ModelMesh;
import com.badlogic.gdx.graphics.g3d.model.data.ModelMeshPart;
import com.badlogic.gdx.graphics.g3d.model.data.ModelNode;
import com.badlogic.gdx.graphics.g3d.model.data.ModelNodeAnimation;
import com.badlogic.gdx.graphics.g3d.model.data.ModelNodeKeyframe;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.BaseJsonReader;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.JsonValue;
import com.vladsch.flexmark.util.html.Attribute;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/loader/G3dModelLoader.class */
public class G3dModelLoader extends ModelLoader<ModelLoader.ModelParameters> {
    public static final short VERSION_HI = 0;
    public static final short VERSION_LO = 1;
    protected final BaseJsonReader reader;
    protected final Quaternion tempQ;

    public G3dModelLoader(BaseJsonReader baseJsonReader) {
        this(baseJsonReader, null);
    }

    public G3dModelLoader(BaseJsonReader baseJsonReader, FileHandleResolver fileHandleResolver) {
        super(fileHandleResolver);
        this.tempQ = new Quaternion();
        this.reader = baseJsonReader;
    }

    @Override // com.badlogic.gdx.assets.loaders.ModelLoader
    public ModelData loadModelData(FileHandle fileHandle, ModelLoader.ModelParameters modelParameters) {
        return parseModel(fileHandle);
    }

    public ModelData parseModel(FileHandle fileHandle) {
        JsonValue parse = this.reader.parse(fileHandle);
        ModelData modelData = new ModelData();
        JsonValue require = parse.require("version");
        modelData.version[0] = require.getShort(0);
        modelData.version[1] = require.getShort(1);
        if (modelData.version[0] != 0 || modelData.version[1] != 1) {
            throw new GdxRuntimeException("Model version not supported");
        }
        modelData.id = parse.getString(Attribute.ID_ATTR, "");
        parseMeshes(modelData, parse);
        parseMaterials(modelData, parse, fileHandle.parent().path());
        parseNodes(modelData, parse);
        parseAnimations(modelData, parse);
        return modelData;
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void parseMeshes(ModelData modelData, JsonValue jsonValue) {
        ModelMesh modelMesh;
        Array array;
        JsonValue jsonValue2 = jsonValue.get("meshes");
        if (jsonValue2 != null) {
            modelData.meshes.ensureCapacity(jsonValue2.size);
            JsonValue jsonValue3 = jsonValue2.child;
            while (true) {
                JsonValue jsonValue4 = jsonValue3;
                if (jsonValue4 != null) {
                    modelMesh = new ModelMesh();
                    modelMesh.id = jsonValue4.getString(Attribute.ID_ATTR, "");
                    modelMesh.attributes = parseAttributes(jsonValue4.require("attributes"));
                    modelMesh.vertices = jsonValue4.require("vertices").asFloatArray();
                    JsonValue require = jsonValue4.require("parts");
                    array = new Array();
                    JsonValue jsonValue5 = require.child;
                    while (true) {
                        JsonValue jsonValue6 = jsonValue5;
                        if (jsonValue6 != null) {
                            ModelMeshPart modelMeshPart = new ModelMeshPart();
                            String string = jsonValue6.getString(Attribute.ID_ATTR, null);
                            if (string == null) {
                                throw new GdxRuntimeException("Not id given for mesh part");
                            }
                            Array.ArrayIterator it = array.iterator();
                            while (it.hasNext()) {
                                if (((ModelMeshPart) it.next()).id.equals(string)) {
                                    throw new GdxRuntimeException("Mesh part with id '" + string + "' already in defined");
                                }
                            }
                            modelMeshPart.id = string;
                            String string2 = jsonValue6.getString("type", null);
                            if (string2 == null) {
                                throw new GdxRuntimeException("No primitive type given for mesh part '" + string + "'");
                            }
                            modelMeshPart.primitiveType = parseType(string2);
                            modelMeshPart.indices = jsonValue6.require("indices").asShortArray();
                            array.add(modelMeshPart);
                            jsonValue5 = jsonValue6.next;
                        }
                    }
                } else {
                    return;
                }
                modelMesh.parts = (ModelMeshPart[]) array.toArray(ModelMeshPart.class);
                modelData.meshes.add(modelMesh);
                jsonValue3 = jsonValue4.next;
            }
        }
    }

    protected int parseType(String str) {
        if (str.equals("TRIANGLES")) {
            return 4;
        }
        if (str.equals("LINES")) {
            return 1;
        }
        if (str.equals("POINTS")) {
            return 0;
        }
        if (str.equals("TRIANGLE_STRIP")) {
            return 5;
        }
        if (str.equals("LINE_STRIP")) {
            return 3;
        }
        throw new GdxRuntimeException("Unknown primitive type '" + str + "', should be one of triangle, trianglestrip, line, linestrip or point");
    }

    protected VertexAttribute[] parseAttributes(JsonValue jsonValue) {
        Array array = new Array();
        int i = 0;
        int i2 = 0;
        JsonValue jsonValue2 = jsonValue.child;
        while (true) {
            JsonValue jsonValue3 = jsonValue2;
            if (jsonValue3 != null) {
                String asString = jsonValue3.asString();
                if (asString.equals("POSITION")) {
                    array.add(VertexAttribute.Position());
                } else if (asString.equals("NORMAL")) {
                    array.add(VertexAttribute.Normal());
                } else if (asString.equals("COLOR")) {
                    array.add(VertexAttribute.ColorUnpacked());
                } else if (asString.equals("COLORPACKED")) {
                    array.add(VertexAttribute.ColorPacked());
                } else if (asString.equals("TANGENT")) {
                    array.add(VertexAttribute.Tangent());
                } else if (asString.equals("BINORMAL")) {
                    array.add(VertexAttribute.Binormal());
                } else if (asString.startsWith("TEXCOORD")) {
                    int i3 = i;
                    i++;
                    array.add(VertexAttribute.TexCoords(i3));
                } else if (asString.startsWith("BLENDWEIGHT")) {
                    int i4 = i2;
                    i2++;
                    array.add(VertexAttribute.BoneWeight(i4));
                } else {
                    throw new GdxRuntimeException("Unknown vertex attribute '" + asString + "', should be one of position, normal, uv, tangent or binormal");
                }
                jsonValue2 = jsonValue3.next;
            } else {
                return (VertexAttribute[]) array.toArray(VertexAttribute.class);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:56:0x01cb, code lost:            continue;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void parseMaterials(com.badlogic.gdx.graphics.g3d.model.data.ModelData r7, com.badlogic.gdx.utils.JsonValue r8, java.lang.String r9) {
        /*
            Method dump skipped, instructions count: 477
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader.parseMaterials(com.badlogic.gdx.graphics.g3d.model.data.ModelData, com.badlogic.gdx.utils.JsonValue, java.lang.String):void");
    }

    protected int parseTextureUsage(String str) {
        if (str.equalsIgnoreCase("AMBIENT")) {
            return 4;
        }
        if (str.equalsIgnoreCase("BUMP")) {
            return 8;
        }
        if (str.equalsIgnoreCase("DIFFUSE")) {
            return 2;
        }
        if (str.equalsIgnoreCase("EMISSIVE")) {
            return 3;
        }
        if (str.equalsIgnoreCase("NONE")) {
            return 1;
        }
        if (str.equalsIgnoreCase("NORMAL")) {
            return 7;
        }
        if (str.equalsIgnoreCase("REFLECTION")) {
            return 10;
        }
        if (str.equalsIgnoreCase("SHININESS")) {
            return 6;
        }
        if (str.equalsIgnoreCase("SPECULAR")) {
            return 5;
        }
        return str.equalsIgnoreCase("TRANSPARENCY") ? 9 : 0;
    }

    protected Color parseColor(JsonValue jsonValue) {
        if (jsonValue.size >= 3) {
            return new Color(jsonValue.getFloat(0), jsonValue.getFloat(1), jsonValue.getFloat(2), 1.0f);
        }
        throw new GdxRuntimeException("Expected Color values <> than three.");
    }

    protected Vector2 readVector2(JsonValue jsonValue, float f, float f2) {
        if (jsonValue == null) {
            return new Vector2(f, f2);
        }
        if (jsonValue.size == 2) {
            return new Vector2(jsonValue.getFloat(0), jsonValue.getFloat(1));
        }
        throw new GdxRuntimeException("Expected Vector2 values <> than two.");
    }

    protected Array<ModelNode> parseNodes(ModelData modelData, JsonValue jsonValue) {
        JsonValue jsonValue2 = jsonValue.get("nodes");
        if (jsonValue2 != null) {
            modelData.nodes.ensureCapacity(jsonValue2.size);
            JsonValue jsonValue3 = jsonValue2.child;
            while (true) {
                JsonValue jsonValue4 = jsonValue3;
                if (jsonValue4 == null) {
                    break;
                }
                modelData.nodes.add(parseNodesRecursively(jsonValue4));
                jsonValue3 = jsonValue4.next;
            }
        }
        return modelData.nodes;
    }

    /* JADX WARN: Code restructure failed: missing block: B:74:0x0282, code lost:            continue;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected com.badlogic.gdx.graphics.g3d.model.data.ModelNode parseNodesRecursively(com.badlogic.gdx.utils.JsonValue r10) {
        /*
            Method dump skipped, instructions count: 731
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader.parseNodesRecursively(com.badlogic.gdx.utils.JsonValue):com.badlogic.gdx.graphics.g3d.model.data.ModelNode");
    }

    /* JADX WARN: Type inference failed for: r1v35, types: [com.badlogic.gdx.math.Vector3, T] */
    /* JADX WARN: Type inference failed for: r1v47, types: [T, com.badlogic.gdx.math.Quaternion] */
    /* JADX WARN: Type inference failed for: r1v59, types: [com.badlogic.gdx.math.Vector3, T] */
    /* JADX WARN: Type inference failed for: r1v72, types: [com.badlogic.gdx.math.Vector3, T] */
    /* JADX WARN: Type inference failed for: r1v79, types: [T, com.badlogic.gdx.math.Quaternion] */
    /* JADX WARN: Type inference failed for: r1v86, types: [com.badlogic.gdx.math.Vector3, T] */
    protected void parseAnimations(ModelData modelData, JsonValue jsonValue) {
        JsonValue jsonValue2 = jsonValue.get("animations");
        if (jsonValue2 == null) {
            return;
        }
        modelData.animations.ensureCapacity(jsonValue2.size);
        JsonValue jsonValue3 = jsonValue2.child;
        while (true) {
            JsonValue jsonValue4 = jsonValue3;
            if (jsonValue4 != null) {
                JsonValue jsonValue5 = jsonValue4.get("bones");
                if (jsonValue5 != null) {
                    ModelAnimation modelAnimation = new ModelAnimation();
                    modelData.animations.add(modelAnimation);
                    modelAnimation.nodeAnimations.ensureCapacity(jsonValue5.size);
                    modelAnimation.id = jsonValue4.getString(Attribute.ID_ATTR);
                    JsonValue jsonValue6 = jsonValue5.child;
                    while (true) {
                        JsonValue jsonValue7 = jsonValue6;
                        if (jsonValue7 != null) {
                            ModelNodeAnimation modelNodeAnimation = new ModelNodeAnimation();
                            modelAnimation.nodeAnimations.add(modelNodeAnimation);
                            modelNodeAnimation.nodeId = jsonValue7.getString("boneId");
                            JsonValue jsonValue8 = jsonValue7.get("keyframes");
                            if (jsonValue8 != null && jsonValue8.isArray()) {
                                JsonValue jsonValue9 = jsonValue8.child;
                                while (true) {
                                    JsonValue jsonValue10 = jsonValue9;
                                    if (jsonValue10 != null) {
                                        float f = jsonValue10.getFloat("keytime", 0.0f) / 1000.0f;
                                        JsonValue jsonValue11 = jsonValue10.get("translation");
                                        if (jsonValue11 != null && jsonValue11.size == 3) {
                                            if (modelNodeAnimation.translation == null) {
                                                modelNodeAnimation.translation = new Array<>();
                                            }
                                            ModelNodeKeyframe<Vector3> modelNodeKeyframe = new ModelNodeKeyframe<>();
                                            modelNodeKeyframe.keytime = f;
                                            modelNodeKeyframe.value = new Vector3(jsonValue11.getFloat(0), jsonValue11.getFloat(1), jsonValue11.getFloat(2));
                                            modelNodeAnimation.translation.add(modelNodeKeyframe);
                                        }
                                        JsonValue jsonValue12 = jsonValue10.get("rotation");
                                        if (jsonValue12 != null && jsonValue12.size == 4) {
                                            if (modelNodeAnimation.rotation == null) {
                                                modelNodeAnimation.rotation = new Array<>();
                                            }
                                            ModelNodeKeyframe<Quaternion> modelNodeKeyframe2 = new ModelNodeKeyframe<>();
                                            modelNodeKeyframe2.keytime = f;
                                            modelNodeKeyframe2.value = new Quaternion(jsonValue12.getFloat(0), jsonValue12.getFloat(1), jsonValue12.getFloat(2), jsonValue12.getFloat(3));
                                            modelNodeAnimation.rotation.add(modelNodeKeyframe2);
                                        }
                                        JsonValue jsonValue13 = jsonValue10.get("scale");
                                        if (jsonValue13 != null && jsonValue13.size == 3) {
                                            if (modelNodeAnimation.scaling == null) {
                                                modelNodeAnimation.scaling = new Array<>();
                                            }
                                            ModelNodeKeyframe<Vector3> modelNodeKeyframe3 = new ModelNodeKeyframe<>();
                                            modelNodeKeyframe3.keytime = f;
                                            modelNodeKeyframe3.value = new Vector3(jsonValue13.getFloat(0), jsonValue13.getFloat(1), jsonValue13.getFloat(2));
                                            modelNodeAnimation.scaling.add(modelNodeKeyframe3);
                                        }
                                        jsonValue9 = jsonValue10.next;
                                    }
                                }
                            } else {
                                JsonValue jsonValue14 = jsonValue7.get("translation");
                                if (jsonValue14 != null && jsonValue14.isArray()) {
                                    modelNodeAnimation.translation = new Array<>();
                                    modelNodeAnimation.translation.ensureCapacity(jsonValue14.size);
                                    JsonValue jsonValue15 = jsonValue14.child;
                                    while (true) {
                                        JsonValue jsonValue16 = jsonValue15;
                                        if (jsonValue16 == null) {
                                            break;
                                        }
                                        ModelNodeKeyframe<Vector3> modelNodeKeyframe4 = new ModelNodeKeyframe<>();
                                        modelNodeAnimation.translation.add(modelNodeKeyframe4);
                                        modelNodeKeyframe4.keytime = jsonValue16.getFloat("keytime", 0.0f) / 1000.0f;
                                        JsonValue jsonValue17 = jsonValue16.get("value");
                                        if (jsonValue17 != null && jsonValue17.size >= 3) {
                                            modelNodeKeyframe4.value = new Vector3(jsonValue17.getFloat(0), jsonValue17.getFloat(1), jsonValue17.getFloat(2));
                                        }
                                        jsonValue15 = jsonValue16.next;
                                    }
                                }
                                JsonValue jsonValue18 = jsonValue7.get("rotation");
                                if (jsonValue18 != null && jsonValue18.isArray()) {
                                    modelNodeAnimation.rotation = new Array<>();
                                    modelNodeAnimation.rotation.ensureCapacity(jsonValue18.size);
                                    JsonValue jsonValue19 = jsonValue18.child;
                                    while (true) {
                                        JsonValue jsonValue20 = jsonValue19;
                                        if (jsonValue20 == null) {
                                            break;
                                        }
                                        ModelNodeKeyframe<Quaternion> modelNodeKeyframe5 = new ModelNodeKeyframe<>();
                                        modelNodeAnimation.rotation.add(modelNodeKeyframe5);
                                        modelNodeKeyframe5.keytime = jsonValue20.getFloat("keytime", 0.0f) / 1000.0f;
                                        JsonValue jsonValue21 = jsonValue20.get("value");
                                        if (jsonValue21 != null && jsonValue21.size >= 4) {
                                            modelNodeKeyframe5.value = new Quaternion(jsonValue21.getFloat(0), jsonValue21.getFloat(1), jsonValue21.getFloat(2), jsonValue21.getFloat(3));
                                        }
                                        jsonValue19 = jsonValue20.next;
                                    }
                                }
                                JsonValue jsonValue22 = jsonValue7.get("scaling");
                                if (jsonValue22 != null && jsonValue22.isArray()) {
                                    modelNodeAnimation.scaling = new Array<>();
                                    modelNodeAnimation.scaling.ensureCapacity(jsonValue22.size);
                                    JsonValue jsonValue23 = jsonValue22.child;
                                    while (true) {
                                        JsonValue jsonValue24 = jsonValue23;
                                        if (jsonValue24 != null) {
                                            ModelNodeKeyframe<Vector3> modelNodeKeyframe6 = new ModelNodeKeyframe<>();
                                            modelNodeAnimation.scaling.add(modelNodeKeyframe6);
                                            modelNodeKeyframe6.keytime = jsonValue24.getFloat("keytime", 0.0f) / 1000.0f;
                                            JsonValue jsonValue25 = jsonValue24.get("value");
                                            if (jsonValue25 != null && jsonValue25.size >= 3) {
                                                modelNodeKeyframe6.value = new Vector3(jsonValue25.getFloat(0), jsonValue25.getFloat(1), jsonValue25.getFloat(2));
                                            }
                                            jsonValue23 = jsonValue24.next;
                                        }
                                    }
                                }
                            }
                            jsonValue6 = jsonValue7.next;
                        }
                    }
                }
                jsonValue3 = jsonValue4.next;
            } else {
                return;
            }
        }
    }
}
