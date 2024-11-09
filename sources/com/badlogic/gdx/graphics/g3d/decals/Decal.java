package com.badlogic.gdx.graphics.g3d.decals;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.NumberUtils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g3d/decals/Decal.class */
public class Decal {
    private static final int VERTEX_SIZE = 6;
    public static final int SIZE = 24;
    public int value;
    protected float[] vertices;
    protected Vector3 position;
    protected Quaternion rotation;
    protected Vector2 scale;
    protected Color color;
    public Vector2 transformationOffset;
    protected Vector2 dimensions;
    protected DecalMaterial material;
    protected boolean updated;
    public static final int X1 = 0;
    public static final int Y1 = 1;
    public static final int Z1 = 2;
    public static final int C1 = 3;
    public static final int U1 = 4;
    public static final int V1 = 5;
    public static final int X2 = 6;
    public static final int Y2 = 7;
    public static final int Z2 = 8;
    public static final int C2 = 9;
    public static final int U2 = 10;
    public static final int V2 = 11;
    public static final int X3 = 12;
    public static final int Y3 = 13;
    public static final int Z3 = 14;
    public static final int C3 = 15;
    public static final int U3 = 16;
    public static final int V3 = 17;
    public static final int X4 = 18;
    public static final int Y4 = 19;
    public static final int Z4 = 20;
    public static final int C4 = 21;
    public static final int U4 = 22;
    public static final int V4 = 23;
    private static Vector3 tmp = new Vector3();
    private static Vector3 tmp2 = new Vector3();
    static final Vector3 dir = new Vector3();
    protected static Quaternion rotator = new Quaternion(0.0f, 0.0f, 0.0f, 0.0f);

    public Decal() {
        this.vertices = new float[24];
        this.position = new Vector3();
        this.rotation = new Quaternion();
        this.scale = new Vector2(1.0f, 1.0f);
        this.color = new Color();
        this.transformationOffset = null;
        this.dimensions = new Vector2();
        this.updated = false;
        this.material = new DecalMaterial();
    }

    public Decal(DecalMaterial decalMaterial) {
        this.vertices = new float[24];
        this.position = new Vector3();
        this.rotation = new Quaternion();
        this.scale = new Vector2(1.0f, 1.0f);
        this.color = new Color();
        this.transformationOffset = null;
        this.dimensions = new Vector2();
        this.updated = false;
        this.material = decalMaterial;
    }

    public void setColor(float f, float f2, float f3, float f4) {
        this.color.set(f, f2, f3, f4);
        float intToFloatColor = NumberUtils.intToFloatColor((((int) (255.0f * f4)) << 24) | (((int) (255.0f * f3)) << 16) | (((int) (255.0f * f2)) << 8) | ((int) (255.0f * f)));
        this.vertices[3] = intToFloatColor;
        this.vertices[9] = intToFloatColor;
        this.vertices[15] = intToFloatColor;
        this.vertices[21] = intToFloatColor;
    }

    public void setColor(Color color) {
        this.color.set(color);
        float floatBits = color.toFloatBits();
        this.vertices[3] = floatBits;
        this.vertices[9] = floatBits;
        this.vertices[15] = floatBits;
        this.vertices[21] = floatBits;
    }

    public void setPackedColor(float f) {
        Color.abgr8888ToColor(this.color, f);
        this.vertices[3] = f;
        this.vertices[9] = f;
        this.vertices[15] = f;
        this.vertices[21] = f;
    }

    public void setRotationX(float f) {
        this.rotation.set(Vector3.X, f);
        this.updated = false;
    }

    public void setRotationY(float f) {
        this.rotation.set(Vector3.Y, f);
        this.updated = false;
    }

    public void setRotationZ(float f) {
        this.rotation.set(Vector3.Z, f);
        this.updated = false;
    }

    public void rotateX(float f) {
        rotator.set(Vector3.X, f);
        this.rotation.mul(rotator);
        this.updated = false;
    }

    public void rotateY(float f) {
        rotator.set(Vector3.Y, f);
        this.rotation.mul(rotator);
        this.updated = false;
    }

    public void rotateZ(float f) {
        rotator.set(Vector3.Z, f);
        this.rotation.mul(rotator);
        this.updated = false;
    }

    public void setRotation(float f, float f2, float f3) {
        this.rotation.setEulerAngles(f, f2, f3);
        this.updated = false;
    }

    public void setRotation(Vector3 vector3, Vector3 vector32) {
        tmp.set(vector32).crs(vector3).nor();
        tmp2.set(vector3).crs(tmp).nor();
        this.rotation.setFromAxes(tmp.x, tmp2.x, vector3.x, tmp.y, tmp2.y, vector3.y, tmp.z, tmp2.z, vector3.z);
        this.updated = false;
    }

    public void setRotation(Quaternion quaternion) {
        this.rotation.set(quaternion);
        this.updated = false;
    }

    public Quaternion getRotation() {
        return this.rotation;
    }

    public void translateX(float f) {
        this.position.x += f;
        this.updated = false;
    }

    public void setX(float f) {
        this.position.x = f;
        this.updated = false;
    }

    public float getX() {
        return this.position.x;
    }

    public void translateY(float f) {
        this.position.y += f;
        this.updated = false;
    }

    public void setY(float f) {
        this.position.y = f;
        this.updated = false;
    }

    public float getY() {
        return this.position.y;
    }

    public void translateZ(float f) {
        this.position.z += f;
        this.updated = false;
    }

    public void setZ(float f) {
        this.position.z = f;
        this.updated = false;
    }

    public float getZ() {
        return this.position.z;
    }

    public void translate(float f, float f2, float f3) {
        this.position.add(f, f2, f3);
        this.updated = false;
    }

    public void translate(Vector3 vector3) {
        this.position.add(vector3);
        this.updated = false;
    }

    public void setPosition(float f, float f2, float f3) {
        this.position.set(f, f2, f3);
        this.updated = false;
    }

    public void setPosition(Vector3 vector3) {
        this.position.set(vector3);
        this.updated = false;
    }

    public Color getColor() {
        return this.color;
    }

    public Vector3 getPosition() {
        return this.position;
    }

    public void setScaleX(float f) {
        this.scale.x = f;
        this.updated = false;
    }

    public float getScaleX() {
        return this.scale.x;
    }

    public void setScaleY(float f) {
        this.scale.y = f;
        this.updated = false;
    }

    public float getScaleY() {
        return this.scale.y;
    }

    public void setScale(float f, float f2) {
        this.scale.set(f, f2);
        this.updated = false;
    }

    public void setScale(float f) {
        this.scale.set(f, f);
        this.updated = false;
    }

    public void setWidth(float f) {
        this.dimensions.x = f;
        this.updated = false;
    }

    public float getWidth() {
        return this.dimensions.x;
    }

    public void setHeight(float f) {
        this.dimensions.y = f;
        this.updated = false;
    }

    public float getHeight() {
        return this.dimensions.y;
    }

    public void setDimensions(float f, float f2) {
        this.dimensions.set(f, f2);
        this.updated = false;
    }

    public float[] getVertices() {
        update();
        return this.vertices;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void update() {
        if (!this.updated) {
            resetVertices();
            transformVertices();
        }
    }

    protected void transformVertices() {
        float f;
        float f2;
        if (this.transformationOffset != null) {
            f2 = -this.transformationOffset.x;
            f = -this.transformationOffset.y;
        } else {
            f = 0.0f;
            f2 = 0.0f;
        }
        float f3 = (this.vertices[0] + f2) * this.scale.x;
        float f4 = (this.vertices[1] + f) * this.scale.y;
        float f5 = this.vertices[2];
        this.vertices[0] = ((this.rotation.w * f3) + (this.rotation.y * f5)) - (this.rotation.z * f4);
        this.vertices[1] = ((this.rotation.w * f4) + (this.rotation.z * f3)) - (this.rotation.x * f5);
        this.vertices[2] = ((this.rotation.w * f5) + (this.rotation.x * f4)) - (this.rotation.y * f3);
        float f6 = (((-this.rotation.x) * f3) - (this.rotation.y * f4)) - (this.rotation.z * f5);
        this.rotation.conjugate();
        float f7 = this.vertices[0];
        float f8 = this.vertices[1];
        float f9 = this.vertices[2];
        this.vertices[0] = (((f6 * this.rotation.x) + (f7 * this.rotation.w)) + (f8 * this.rotation.z)) - (f9 * this.rotation.y);
        this.vertices[1] = (((f6 * this.rotation.y) + (f8 * this.rotation.w)) + (f9 * this.rotation.x)) - (f7 * this.rotation.z);
        this.vertices[2] = (((f6 * this.rotation.z) + (f9 * this.rotation.w)) + (f7 * this.rotation.y)) - (f8 * this.rotation.x);
        this.rotation.conjugate();
        float[] fArr = this.vertices;
        fArr[0] = fArr[0] + (this.position.x - f2);
        float[] fArr2 = this.vertices;
        fArr2[1] = fArr2[1] + (this.position.y - f);
        float[] fArr3 = this.vertices;
        fArr3[2] = fArr3[2] + this.position.z;
        float f10 = (this.vertices[6] + f2) * this.scale.x;
        float f11 = (this.vertices[7] + f) * this.scale.y;
        float f12 = this.vertices[8];
        this.vertices[6] = ((this.rotation.w * f10) + (this.rotation.y * f12)) - (this.rotation.z * f11);
        this.vertices[7] = ((this.rotation.w * f11) + (this.rotation.z * f10)) - (this.rotation.x * f12);
        this.vertices[8] = ((this.rotation.w * f12) + (this.rotation.x * f11)) - (this.rotation.y * f10);
        float f13 = (((-this.rotation.x) * f10) - (this.rotation.y * f11)) - (this.rotation.z * f12);
        this.rotation.conjugate();
        float f14 = this.vertices[6];
        float f15 = this.vertices[7];
        float f16 = this.vertices[8];
        this.vertices[6] = (((f13 * this.rotation.x) + (f14 * this.rotation.w)) + (f15 * this.rotation.z)) - (f16 * this.rotation.y);
        this.vertices[7] = (((f13 * this.rotation.y) + (f15 * this.rotation.w)) + (f16 * this.rotation.x)) - (f14 * this.rotation.z);
        this.vertices[8] = (((f13 * this.rotation.z) + (f16 * this.rotation.w)) + (f14 * this.rotation.y)) - (f15 * this.rotation.x);
        this.rotation.conjugate();
        float[] fArr4 = this.vertices;
        fArr4[6] = fArr4[6] + (this.position.x - f2);
        float[] fArr5 = this.vertices;
        fArr5[7] = fArr5[7] + (this.position.y - f);
        float[] fArr6 = this.vertices;
        fArr6[8] = fArr6[8] + this.position.z;
        float f17 = (this.vertices[12] + f2) * this.scale.x;
        float f18 = (this.vertices[13] + f) * this.scale.y;
        float f19 = this.vertices[14];
        this.vertices[12] = ((this.rotation.w * f17) + (this.rotation.y * f19)) - (this.rotation.z * f18);
        this.vertices[13] = ((this.rotation.w * f18) + (this.rotation.z * f17)) - (this.rotation.x * f19);
        this.vertices[14] = ((this.rotation.w * f19) + (this.rotation.x * f18)) - (this.rotation.y * f17);
        float f20 = (((-this.rotation.x) * f17) - (this.rotation.y * f18)) - (this.rotation.z * f19);
        this.rotation.conjugate();
        float f21 = this.vertices[12];
        float f22 = this.vertices[13];
        float f23 = this.vertices[14];
        this.vertices[12] = (((f20 * this.rotation.x) + (f21 * this.rotation.w)) + (f22 * this.rotation.z)) - (f23 * this.rotation.y);
        this.vertices[13] = (((f20 * this.rotation.y) + (f22 * this.rotation.w)) + (f23 * this.rotation.x)) - (f21 * this.rotation.z);
        this.vertices[14] = (((f20 * this.rotation.z) + (f23 * this.rotation.w)) + (f21 * this.rotation.y)) - (f22 * this.rotation.x);
        this.rotation.conjugate();
        float[] fArr7 = this.vertices;
        fArr7[12] = fArr7[12] + (this.position.x - f2);
        float[] fArr8 = this.vertices;
        fArr8[13] = fArr8[13] + (this.position.y - f);
        float[] fArr9 = this.vertices;
        fArr9[14] = fArr9[14] + this.position.z;
        float f24 = (this.vertices[18] + f2) * this.scale.x;
        float f25 = (this.vertices[19] + f) * this.scale.y;
        float f26 = this.vertices[20];
        this.vertices[18] = ((this.rotation.w * f24) + (this.rotation.y * f26)) - (this.rotation.z * f25);
        this.vertices[19] = ((this.rotation.w * f25) + (this.rotation.z * f24)) - (this.rotation.x * f26);
        this.vertices[20] = ((this.rotation.w * f26) + (this.rotation.x * f25)) - (this.rotation.y * f24);
        float f27 = (((-this.rotation.x) * f24) - (this.rotation.y * f25)) - (this.rotation.z * f26);
        this.rotation.conjugate();
        float f28 = this.vertices[18];
        float f29 = this.vertices[19];
        float f30 = this.vertices[20];
        this.vertices[18] = (((f27 * this.rotation.x) + (f28 * this.rotation.w)) + (f29 * this.rotation.z)) - (f30 * this.rotation.y);
        this.vertices[19] = (((f27 * this.rotation.y) + (f29 * this.rotation.w)) + (f30 * this.rotation.x)) - (f28 * this.rotation.z);
        this.vertices[20] = (((f27 * this.rotation.z) + (f30 * this.rotation.w)) + (f28 * this.rotation.y)) - (f29 * this.rotation.x);
        this.rotation.conjugate();
        float[] fArr10 = this.vertices;
        fArr10[18] = fArr10[18] + (this.position.x - f2);
        float[] fArr11 = this.vertices;
        fArr11[19] = fArr11[19] + (this.position.y - f);
        float[] fArr12 = this.vertices;
        fArr12[20] = fArr12[20] + this.position.z;
        this.updated = true;
    }

    protected void resetVertices() {
        float f = (-this.dimensions.x) / 2.0f;
        float f2 = f + this.dimensions.x;
        float f3 = this.dimensions.y / 2.0f;
        float f4 = f3 - this.dimensions.y;
        this.vertices[0] = f;
        this.vertices[1] = f3;
        this.vertices[2] = 0.0f;
        this.vertices[6] = f2;
        this.vertices[7] = f3;
        this.vertices[8] = 0.0f;
        this.vertices[12] = f;
        this.vertices[13] = f4;
        this.vertices[14] = 0.0f;
        this.vertices[18] = f2;
        this.vertices[19] = f4;
        this.vertices[20] = 0.0f;
        this.updated = false;
    }

    protected void updateUVs() {
        TextureRegion textureRegion = this.material.textureRegion;
        this.vertices[4] = textureRegion.getU();
        this.vertices[5] = textureRegion.getV();
        this.vertices[10] = textureRegion.getU2();
        this.vertices[11] = textureRegion.getV();
        this.vertices[16] = textureRegion.getU();
        this.vertices[17] = textureRegion.getV2();
        this.vertices[22] = textureRegion.getU2();
        this.vertices[23] = textureRegion.getV2();
    }

    public void setTextureRegion(TextureRegion textureRegion) {
        this.material.textureRegion = textureRegion;
        updateUVs();
    }

    public TextureRegion getTextureRegion() {
        return this.material.textureRegion;
    }

    public void setBlending(int i, int i2) {
        this.material.srcBlendFactor = i;
        this.material.dstBlendFactor = i2;
    }

    public DecalMaterial getMaterial() {
        return this.material;
    }

    public void setMaterial(DecalMaterial decalMaterial) {
        this.material = decalMaterial;
    }

    public void lookAt(Vector3 vector3, Vector3 vector32) {
        dir.set(vector3).sub(this.position).nor();
        setRotation(dir, vector32);
    }

    public static Decal newDecal(TextureRegion textureRegion) {
        return newDecal(textureRegion.getRegionWidth(), textureRegion.getRegionHeight(), textureRegion, -1, -1);
    }

    public static Decal newDecal(TextureRegion textureRegion, boolean z) {
        return newDecal(textureRegion.getRegionWidth(), textureRegion.getRegionHeight(), textureRegion, z ? 770 : -1, z ? 771 : -1);
    }

    public static Decal newDecal(float f, float f2, TextureRegion textureRegion) {
        return newDecal(f, f2, textureRegion, -1, -1);
    }

    public static Decal newDecal(float f, float f2, TextureRegion textureRegion, boolean z) {
        return newDecal(f, f2, textureRegion, z ? 770 : -1, z ? 771 : -1);
    }

    public static Decal newDecal(float f, float f2, TextureRegion textureRegion, int i, int i2) {
        Decal decal = new Decal();
        decal.setTextureRegion(textureRegion);
        decal.setBlending(i, i2);
        decal.dimensions.x = f;
        decal.dimensions.y = f2;
        decal.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        return decal;
    }

    public static Decal newDecal(float f, float f2, TextureRegion textureRegion, int i, int i2, DecalMaterial decalMaterial) {
        Decal decal = new Decal(decalMaterial);
        decal.setTextureRegion(textureRegion);
        decal.setBlending(i, i2);
        decal.dimensions.x = f;
        decal.dimensions.y = f2;
        decal.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        return decal;
    }
}
