package com.badlogic.gdx.graphics.g3d.loader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.model.data.ModelData;
import com.badlogic.gdx.graphics.g3d.model.data.ModelMesh;
import com.badlogic.gdx.graphics.g3d.model.data.ModelMeshPart;
import com.badlogic.gdx.graphics.g3d.model.data.ModelNode;
import com.badlogic.gdx.graphics.g3d.model.data.ModelNodePart;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FloatArray;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/loader/ObjLoader.class */
public class ObjLoader extends ModelLoader<ObjLoaderParameters> {
    public static boolean logWarning = false;
    final FloatArray verts;
    final FloatArray norms;
    final FloatArray uvs;
    final Array<Group> groups;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/loader/ObjLoader$ObjLoaderParameters.class */
    public static class ObjLoaderParameters extends ModelLoader.ModelParameters {
        public boolean flipV;

        public ObjLoaderParameters() {
        }

        public ObjLoaderParameters(boolean z) {
            this.flipV = z;
        }
    }

    public ObjLoader() {
        this(null);
    }

    public ObjLoader(FileHandleResolver fileHandleResolver) {
        super(fileHandleResolver);
        this.verts = new FloatArray(300);
        this.norms = new FloatArray(300);
        this.uvs = new FloatArray(200);
        this.groups = new Array<>(10);
    }

    public Model loadModel(FileHandle fileHandle, boolean z) {
        return loadModel(fileHandle, (FileHandle) new ObjLoaderParameters(z));
    }

    @Override // com.badlogic.gdx.assets.loaders.ModelLoader
    public ModelData loadModelData(FileHandle fileHandle, ObjLoaderParameters objLoaderParameters) {
        return loadModelData(fileHandle, objLoaderParameters != null && objLoaderParameters.flipV);
    }

    protected ModelData loadModelData(FileHandle fileHandle, boolean z) {
        char charAt;
        if (logWarning) {
            Gdx.app.error("ObjLoader", "Wavefront (OBJ) is not fully supported, consult the documentation for more information");
        }
        MtlLoader mtlLoader = new MtlLoader();
        Group group = new Group("default");
        this.groups.add(group);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileHandle.read()), 4096);
        int i = 0;
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                String[] split = readLine.split("\\s+");
                if (split.length <= 0) {
                    break;
                }
                if (split[0].length() != 0 && (charAt = split[0].toLowerCase().charAt(0)) != '#') {
                    if (charAt == 'v') {
                        if (split[0].length() == 1) {
                            this.verts.add(Float.parseFloat(split[1]));
                            this.verts.add(Float.parseFloat(split[2]));
                            this.verts.add(Float.parseFloat(split[3]));
                        } else if (split[0].charAt(1) == 'n') {
                            this.norms.add(Float.parseFloat(split[1]));
                            this.norms.add(Float.parseFloat(split[2]));
                            this.norms.add(Float.parseFloat(split[3]));
                        } else if (split[0].charAt(1) == 't') {
                            this.uvs.add(Float.parseFloat(split[1]));
                            this.uvs.add(z ? 1.0f - Float.parseFloat(split[2]) : Float.parseFloat(split[2]));
                        }
                    } else if (charAt == 'f') {
                        Array<Integer> array = group.faces;
                        int i2 = 1;
                        while (i2 < split.length - 2) {
                            String[] split2 = split[1].split("/");
                            array.add(Integer.valueOf(getIndex(split2[0], this.verts.size)));
                            if (split2.length > 2) {
                                if (i2 == 1) {
                                    group.hasNorms = true;
                                }
                                array.add(Integer.valueOf(getIndex(split2[2], this.norms.size)));
                            }
                            if (split2.length > 1 && split2[1].length() > 0) {
                                if (i2 == 1) {
                                    group.hasUVs = true;
                                }
                                array.add(Integer.valueOf(getIndex(split2[1], this.uvs.size)));
                            }
                            int i3 = i2 + 1;
                            String[] split3 = split[i3].split("/");
                            array.add(Integer.valueOf(getIndex(split3[0], this.verts.size)));
                            if (split3.length > 2) {
                                array.add(Integer.valueOf(getIndex(split3[2], this.norms.size)));
                            }
                            if (split3.length > 1 && split3[1].length() > 0) {
                                array.add(Integer.valueOf(getIndex(split3[1], this.uvs.size)));
                            }
                            int i4 = i3 + 1;
                            String[] split4 = split[i4].split("/");
                            array.add(Integer.valueOf(getIndex(split4[0], this.verts.size)));
                            if (split4.length > 2) {
                                array.add(Integer.valueOf(getIndex(split4[2], this.norms.size)));
                            }
                            if (split4.length > 1 && split4[1].length() > 0) {
                                array.add(Integer.valueOf(getIndex(split4[1], this.uvs.size)));
                            }
                            group.numFaces++;
                            i2 = i4 - 1;
                        }
                    } else if (charAt == 'o' || charAt == 'g') {
                        if (split.length > 1) {
                            group = setActiveGroup(split[1]);
                        } else {
                            group = setActiveGroup("default");
                        }
                    } else if (split[0].equals("mtllib")) {
                        mtlLoader.load(fileHandle.parent().child(split[1]));
                    } else if (split[0].equals("usemtl")) {
                        if (split.length == 1) {
                            group.materialName = "default";
                        } else {
                            group.materialName = split[1].replace('.', '_');
                        }
                    }
                }
            } catch (IOException unused) {
                return null;
            }
        }
        bufferedReader.close();
        int i5 = 0;
        while (i5 < this.groups.size) {
            if (this.groups.get(i5).numFaces <= 0) {
                this.groups.removeIndex(i5);
                i5--;
            }
            i5++;
        }
        if (this.groups.size <= 0) {
            return null;
        }
        int i6 = this.groups.size;
        ModelData modelData = new ModelData();
        for (int i7 = 0; i7 < i6; i7++) {
            Group group2 = this.groups.get(i7);
            Array<Integer> array2 = group2.faces;
            int i8 = array2.size;
            int i9 = group2.numFaces;
            boolean z2 = group2.hasNorms;
            boolean z3 = group2.hasUVs;
            float[] fArr = new float[i9 * 3 * (3 + (z2 ? 3 : 0) + (z3 ? 2 : 0))];
            int i10 = 0;
            int i11 = 0;
            while (i10 < i8) {
                int i12 = i10;
                i10++;
                int intValue = array2.get(i12).intValue() * 3;
                int i13 = i11;
                int i14 = i11 + 1;
                int i15 = intValue + 1;
                fArr[i13] = this.verts.get(intValue);
                int i16 = i14 + 1;
                fArr[i14] = this.verts.get(i15);
                i11 = i16 + 1;
                fArr[i16] = this.verts.get(i15 + 1);
                if (z2) {
                    i10++;
                    int intValue2 = array2.get(i10).intValue() * 3;
                    int i17 = i11 + 1;
                    int i18 = intValue2 + 1;
                    fArr[i11] = this.norms.get(intValue2);
                    int i19 = i17 + 1;
                    fArr[i17] = this.norms.get(i18);
                    i11 = i19 + 1;
                    fArr[i19] = this.norms.get(i18 + 1);
                }
                if (z3) {
                    int i20 = i10;
                    i10++;
                    int intValue3 = array2.get(i20).intValue() << 1;
                    int i21 = i11;
                    int i22 = i11 + 1;
                    fArr[i21] = this.uvs.get(intValue3);
                    i11 = i22 + 1;
                    fArr[i22] = this.uvs.get(intValue3 + 1);
                }
            }
            int i23 = i9 * 3 >= 32767 ? 0 : i9 * 3;
            int i24 = i23;
            short[] sArr = new short[i23];
            if (i24 > 0) {
                for (int i25 = 0; i25 < i24; i25++) {
                    int i26 = i25;
                    sArr[i26] = (short) i26;
                }
            }
            Array array3 = new Array();
            array3.add(new VertexAttribute(1, 3, ShaderProgram.POSITION_ATTRIBUTE));
            if (z2) {
                array3.add(new VertexAttribute(8, 3, ShaderProgram.NORMAL_ATTRIBUTE));
            }
            if (z3) {
                array3.add(new VertexAttribute(16, 2, "a_texCoord0"));
            }
            i++;
            String num = Integer.toString(i);
            String str = "default".equals(group2.name) ? "node" + num : group2.name;
            String str2 = "default".equals(group2.name) ? "mesh" + num : group2.name;
            String str3 = "default".equals(group2.name) ? "part" + num : group2.name;
            ModelNode modelNode = new ModelNode();
            modelNode.id = str;
            modelNode.meshId = str2;
            modelNode.scale = new Vector3(1.0f, 1.0f, 1.0f);
            modelNode.translation = new Vector3();
            modelNode.rotation = new Quaternion();
            ModelNodePart modelNodePart = new ModelNodePart();
            modelNodePart.meshPartId = str3;
            modelNodePart.materialId = group2.materialName;
            modelNode.parts = new ModelNodePart[]{modelNodePart};
            ModelMeshPart modelMeshPart = new ModelMeshPart();
            modelMeshPart.id = str3;
            modelMeshPart.indices = sArr;
            modelMeshPart.primitiveType = 4;
            ModelMesh modelMesh = new ModelMesh();
            modelMesh.id = str2;
            modelMesh.attributes = (VertexAttribute[]) array3.toArray(VertexAttribute.class);
            modelMesh.vertices = fArr;
            modelMesh.parts = new ModelMeshPart[]{modelMeshPart};
            modelData.nodes.add(modelNode);
            modelData.meshes.add(modelMesh);
            modelData.materials.add(mtlLoader.getMaterial(group2.materialName));
        }
        if (this.verts.size > 0) {
            this.verts.clear();
        }
        if (this.norms.size > 0) {
            this.norms.clear();
        }
        if (this.uvs.size > 0) {
            this.uvs.clear();
        }
        if (this.groups.size > 0) {
            this.groups.clear();
        }
        return modelData;
    }

    private Group setActiveGroup(String str) {
        Array.ArrayIterator<Group> it = this.groups.iterator();
        while (it.hasNext()) {
            Group next = it.next();
            if (next.name.equals(str)) {
                return next;
            }
        }
        Group group = new Group(str);
        this.groups.add(group);
        return group;
    }

    private int getIndex(String str, int i) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int parseInt = Integer.parseInt(str);
        if (parseInt < 0) {
            return i + parseInt;
        }
        return parseInt - 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/loader/ObjLoader$Group.class */
    public static class Group {
        final String name;
        boolean hasNorms;
        boolean hasUVs;
        Array<Integer> faces = new Array<>(200);
        int numFaces = 0;
        Material mat = new Material("");
        String materialName = "default";

        Group(String str) {
            this.name = str;
        }
    }
}
