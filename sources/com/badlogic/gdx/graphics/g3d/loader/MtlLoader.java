package com.badlogic.gdx.graphics.g3d.loader;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.model.data.ModelMaterial;
import com.badlogic.gdx.graphics.g3d.model.data.ModelTexture;
import com.badlogic.gdx.utils.Array;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/loader/MtlLoader.class */
class MtlLoader {
    public Array<ModelMaterial> materials = new Array<>();

    public void load(FileHandle fileHandle) {
        ObjMaterial objMaterial = new ObjMaterial();
        if (fileHandle == null || !fileHandle.exists()) {
            return;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileHandle.read()), 4096);
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                String str = readLine;
                if (readLine != null) {
                    if (str.length() > 0 && str.charAt(0) == '\t') {
                        str = str.substring(1).trim();
                    }
                    String[] split = str.split("\\s+");
                    if (split[0].length() != 0 && split[0].charAt(0) != '#') {
                        String lowerCase = split[0].toLowerCase();
                        if (!lowerCase.equals("newmtl")) {
                            if (lowerCase.equals("ka")) {
                                objMaterial.ambientColor = parseColor(split);
                            } else if (lowerCase.equals("kd")) {
                                objMaterial.diffuseColor = parseColor(split);
                            } else if (lowerCase.equals("ks")) {
                                objMaterial.specularColor = parseColor(split);
                            } else if (lowerCase.equals(FlexmarkHtmlConverter.TR_NODE) || lowerCase.equals("d")) {
                                objMaterial.opacity = Float.parseFloat(split[1]);
                            } else if (lowerCase.equals("ns")) {
                                objMaterial.shininess = Float.parseFloat(split[1]);
                            } else if (lowerCase.equals("map_d")) {
                                objMaterial.alphaTexFilename = fileHandle.parent().child(split[1]).path();
                            } else if (lowerCase.equals("map_ka")) {
                                objMaterial.ambientTexFilename = fileHandle.parent().child(split[1]).path();
                            } else if (lowerCase.equals("map_kd")) {
                                objMaterial.diffuseTexFilename = fileHandle.parent().child(split[1]).path();
                            } else if (lowerCase.equals("map_ks")) {
                                objMaterial.specularTexFilename = fileHandle.parent().child(split[1]).path();
                            } else if (lowerCase.equals("map_ns")) {
                                objMaterial.shininessTexFilename = fileHandle.parent().child(split[1]).path();
                            }
                        } else {
                            this.materials.add(objMaterial.build());
                            if (split.length > 1) {
                                objMaterial.materialName = split[1];
                                objMaterial.materialName = objMaterial.materialName.replace('.', '_');
                            } else {
                                objMaterial.materialName = "default";
                            }
                            objMaterial.reset();
                        }
                    }
                } else {
                    bufferedReader.close();
                    this.materials.add(objMaterial.build());
                    return;
                }
            } catch (IOException unused) {
                return;
            }
        }
    }

    private Color parseColor(String[] strArr) {
        float parseFloat = Float.parseFloat(strArr[1]);
        float parseFloat2 = Float.parseFloat(strArr[2]);
        float parseFloat3 = Float.parseFloat(strArr[3]);
        float f = 1.0f;
        if (strArr.length > 4) {
            f = Float.parseFloat(strArr[4]);
        }
        return new Color(parseFloat, parseFloat2, parseFloat3, f);
    }

    public ModelMaterial getMaterial(String str) {
        Array.ArrayIterator<ModelMaterial> it = this.materials.iterator();
        while (it.hasNext()) {
            ModelMaterial next = it.next();
            if (next.id.equals(str)) {
                return next;
            }
        }
        ModelMaterial modelMaterial = new ModelMaterial();
        modelMaterial.id = str;
        modelMaterial.diffuse = new Color(Color.WHITE);
        this.materials.add(modelMaterial);
        return modelMaterial;
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/loader/MtlLoader$ObjMaterial.class */
    private static class ObjMaterial {
        String materialName = "default";
        Color ambientColor;
        Color diffuseColor;
        Color specularColor;
        float opacity;
        float shininess;
        String alphaTexFilename;
        String ambientTexFilename;
        String diffuseTexFilename;
        String shininessTexFilename;
        String specularTexFilename;

        public ObjMaterial() {
            reset();
        }

        public ModelMaterial build() {
            ModelMaterial modelMaterial = new ModelMaterial();
            modelMaterial.id = this.materialName;
            modelMaterial.ambient = this.ambientColor == null ? null : new Color(this.ambientColor);
            modelMaterial.diffuse = new Color(this.diffuseColor);
            modelMaterial.specular = new Color(this.specularColor);
            modelMaterial.opacity = this.opacity;
            modelMaterial.shininess = this.shininess;
            addTexture(modelMaterial, this.alphaTexFilename, 9);
            addTexture(modelMaterial, this.ambientTexFilename, 4);
            addTexture(modelMaterial, this.diffuseTexFilename, 2);
            addTexture(modelMaterial, this.specularTexFilename, 5);
            addTexture(modelMaterial, this.shininessTexFilename, 6);
            return modelMaterial;
        }

        private void addTexture(ModelMaterial modelMaterial, String str, int i) {
            if (str != null) {
                ModelTexture modelTexture = new ModelTexture();
                modelTexture.usage = i;
                modelTexture.fileName = str;
                if (modelMaterial.textures == null) {
                    modelMaterial.textures = new Array<>(1);
                }
                modelMaterial.textures.add(modelTexture);
            }
        }

        public void reset() {
            this.ambientColor = null;
            this.diffuseColor = Color.WHITE;
            this.specularColor = Color.WHITE;
            this.opacity = 1.0f;
            this.shininess = 0.0f;
            this.alphaTexFilename = null;
            this.ambientTexFilename = null;
            this.diffuseTexFilename = null;
            this.shininessTexFilename = null;
            this.specularTexFilename = null;
        }
    }
}
