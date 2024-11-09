package com.prineside.tdi2.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.SpriteCache;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Bezier;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.google.common.base.Preconditions;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/DrawUtils.class */
public class DrawUtils {

    /* renamed from: a, reason: collision with root package name */
    private static final float f3821a = Color.WHITE.toFloatBits();

    /* renamed from: b, reason: collision with root package name */
    private static final float[] f3822b = new float[20];
    private static float[] c = new float[0];
    private static final Vector2 d = new Vector2();
    private static final Bezier<Vector2> e = new Bezier<>(new Vector2(), new Vector2());
    private static final Color f = new Color();

    private static void a(int i) {
        if (c.length < i) {
            float[] fArr = new float[MathUtils.nextPowerOfTwo(i)];
            System.arraycopy(c, 0, fArr, 0, c.length);
            c = fArr;
        }
    }

    public static void bakeVertices(float[] fArr, TextureRegion textureRegion, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10) {
        float f11;
        float f12;
        float f13;
        float f14;
        float f15;
        float f16;
        float f17;
        float f18;
        float f19 = f2 + f4;
        float f20 = f3 + f5;
        float f21 = -f4;
        float f22 = -f5;
        float f23 = f6 - f4;
        float f24 = f7 - f5;
        if (f8 != 1.0f || f9 != 1.0f) {
            f21 *= f8;
            f22 *= f9;
            f23 *= f8;
            f24 *= f9;
        }
        float f25 = f21;
        float f26 = f22;
        float f27 = f21;
        float f28 = f24;
        float f29 = f23;
        float f30 = f24;
        float f31 = f23;
        float f32 = f22;
        if (f10 != 0.0f) {
            float cosDeg = MathUtils.cosDeg(f10);
            float sinDeg = MathUtils.sinDeg(f10);
            f11 = (cosDeg * f25) - (sinDeg * f26);
            f12 = (sinDeg * f25) + (cosDeg * f26);
            f13 = (cosDeg * f27) - (sinDeg * f28);
            f14 = (sinDeg * f27) + (cosDeg * f28);
            f15 = (cosDeg * f29) - (sinDeg * f30);
            f16 = (sinDeg * f29) + (cosDeg * f30);
            f17 = f11 + (f15 - f13);
            f18 = f16 - (f14 - f12);
        } else {
            f11 = f25;
            f12 = f26;
            f13 = f27;
            f14 = f28;
            f15 = f29;
            f16 = f30;
            f17 = f31;
            f18 = f32;
        }
        float f33 = f11 + f19;
        float f34 = f12 + f20;
        float f35 = f13 + f19;
        float f36 = f14 + f20;
        float f37 = f15 + f19;
        float f38 = f16 + f20;
        float f39 = f17 + f19;
        float f40 = f18 + f20;
        float u = textureRegion.getU();
        float v2 = textureRegion.getV2();
        float u2 = textureRegion.getU2();
        float v = textureRegion.getV();
        float f41 = Color.WHITE_FLOAT_BITS;
        fArr[0] = f33;
        fArr[1] = f34;
        fArr[2] = f41;
        fArr[3] = u;
        fArr[4] = v2;
        fArr[5] = f35;
        fArr[6] = f36;
        fArr[7] = f41;
        fArr[8] = u;
        fArr[9] = v;
        fArr[10] = f37;
        fArr[11] = f38;
        fArr[12] = f41;
        fArr[13] = u2;
        fArr[14] = v;
        fArr[15] = f39;
        fArr[16] = f40;
        fArr[17] = f41;
        fArr[18] = u2;
        fArr[19] = v2;
    }

    public static void prepareBezierCurve(float[] fArr, Vector2[] vector2Arr, int i, Color color, Color color2) {
        Preconditions.checkArgument(fArr.length == (i + 1) * 3, "Array length must be equal %s, array of %s given", (i + 1) * 3, fArr.length);
        e.set(vector2Arr);
        for (int i2 = 0; i2 <= i; i2++) {
            float f2 = i2 / i;
            f.set(color).lerp(color2, f2);
            e.valueAt((Bezier<Vector2>) d, f2);
            fArr[i2 * 3] = d.x;
            fArr[(i2 * 3) + 1] = d.y;
            fArr[(i2 * 3) + 2] = f.toFloatBits();
        }
    }

    public static int getTexturedMultiLineVertices(float[] fArr, float f2, TextureRegion textureRegion, float[] fArr2, int i, int i2) {
        int i3 = i2 / 3;
        if (i3 < 2) {
            throw new IllegalArgumentException("data array must contain at least 2 points (6 floats), " + i2 + " given");
        }
        Preconditions.checkArgument(fArr.length >= 20 * (i3 - 1), "Out array should be of size " + (20 * (i3 - 1)) + " or larger, " + fArr.length + " given");
        int i4 = 0;
        for (int i5 = 1; i5 < i3; i5++) {
            int i6 = i5 - 1;
            float f3 = fArr2[(i6 * 3) + i];
            float f4 = fArr2[(i6 * 3) + 1 + i];
            float f5 = fArr2[(i5 * 3) + i];
            float f6 = fArr2[(i5 * 3) + 1 + i];
            float f7 = fArr2[(i6 * 3) + 2 + i];
            float f8 = fArr2[(i5 * 3) + 2 + i];
            float angleRad = d.set(f5 - f3, f6 - f4).angleRad() - 1.5707964f;
            System.arraycopy(getTexturedLineVertices(textureRegion, f3, f4, f5, f6, f2, f2, angleRad, angleRad, f7, f8), 0, fArr, i4, 20);
            i4 += 20;
        }
        return i4;
    }

    public static float[] getTexturedLineVertices(TextureRegion textureRegion, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11) {
        float u = textureRegion.getU();
        float u2 = textureRegion.getU2();
        float v = textureRegion.getV();
        float v2 = textureRegion.getV2();
        float f12 = f6 * 0.5f;
        float f13 = f7 * 0.5f;
        f3822b[0] = f2 + ((-PMath.sin(f8 - 1.5707964f)) * f12);
        f3822b[1] = f3 + (PMath.cos(f8 - 1.5707964f) * f12);
        f3822b[2] = f10;
        f3822b[3] = u2;
        f3822b[4] = v2;
        f3822b[5] = f2 + ((-PMath.sin(f8 + 1.5707964f)) * f12);
        f3822b[6] = f3 + (PMath.cos(f8 + 1.5707964f) * f12);
        f3822b[7] = f10;
        f3822b[8] = u;
        f3822b[9] = v2;
        f3822b[10] = f4 + ((-PMath.sin(f9 + 1.5707964f)) * f13);
        f3822b[11] = f5 + (PMath.cos(f9 + 1.5707964f) * f13);
        f3822b[12] = f11;
        f3822b[13] = u;
        f3822b[14] = v;
        f3822b[15] = f4 + ((-PMath.sin(f9 - 1.5707964f)) * f13);
        f3822b[16] = f5 + (PMath.cos(f9 - 1.5707964f) * f13);
        f3822b[17] = f11;
        f3822b[18] = u2;
        f3822b[19] = v;
        return f3822b;
    }

    public static void texturedMultiLine(Batch batch, float f2, TextureRegion textureRegion, float[] fArr) {
        int length = fArr.length / 3;
        if (length < 2) {
            throw new IllegalArgumentException("data array must contain at least 2 points (6 floats), " + fArr.length + " given");
        }
        for (int i = 1; i < length; i++) {
            int i2 = i - 1;
            texturedLineC(batch, textureRegion, fArr[i2 * 3], fArr[(i2 * 3) + 1], fArr[i * 3], fArr[(i * 3) + 1], f2, fArr[(i2 * 3) + 2], fArr[(i * 3) + 2]);
        }
    }

    public static void texturedLineB(Batch batch, TextureRegion textureRegion, float f2, float f3, float f4, float f5, float f6) {
        float f7 = f3821a;
        texturedLineC(batch, textureRegion, f2, f3, f4, f5, f6, f7, f7);
    }

    public static void texturedLineC(Batch batch, TextureRegion textureRegion, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        float angleRad = d.set(f4 - f2, f5 - f3).angleRad() - 1.5707964f;
        texturedLineF(batch, textureRegion, f2, f3, f4, f5, f6, f6, angleRad, angleRad, f7, f8);
    }

    public static void texturedLineF(Batch batch, TextureRegion textureRegion, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11) {
        float u = textureRegion.getU();
        float u2 = textureRegion.getU2();
        float v = textureRegion.getV();
        float v2 = textureRegion.getV2();
        float f12 = f6 / 2.0f;
        float f13 = f7 / 2.0f;
        f3822b[0] = f2 + ((-PMath.sin(f8 - 1.5707964f)) * f12);
        f3822b[1] = f3 + (PMath.cos(f8 - 1.5707964f) * f12);
        f3822b[2] = f10;
        f3822b[3] = u2;
        f3822b[4] = v2;
        f3822b[5] = f2 + ((-PMath.sin(f8 + 1.5707964f)) * f12);
        f3822b[6] = f3 + (PMath.cos(f8 + 1.5707964f) * f12);
        f3822b[7] = f10;
        f3822b[8] = u;
        f3822b[9] = v2;
        f3822b[10] = f4 + ((-PMath.sin(f9 + 1.5707964f)) * f13);
        f3822b[11] = f5 + (PMath.cos(f9 + 1.5707964f) * f13);
        f3822b[12] = f11;
        f3822b[13] = u;
        f3822b[14] = v;
        f3822b[15] = f4 + ((-PMath.sin(f9 - 1.5707964f)) * f13);
        f3822b[16] = f5 + (PMath.cos(f9 - 1.5707964f) * f13);
        f3822b[17] = f11;
        f3822b[18] = u2;
        f3822b[19] = v;
        batch.draw(textureRegion.getTexture(), f3822b, 0, 20);
    }

    public static void texturedLineCacheA(SpriteCache spriteCache, TextureRegion textureRegion, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        float angleRad = d.set(f4 - f2, f5 - f3).angleRad() - 1.5707964f;
        texturedLineCacheB(spriteCache, textureRegion, f2, f3, f4, f5, f6, f6, angleRad, angleRad, f7, f8);
    }

    public static void texturedLineCacheB(SpriteCache spriteCache, TextureRegion textureRegion, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11) {
        float u = textureRegion.getU();
        float u2 = textureRegion.getU2();
        float v = textureRegion.getV();
        float v2 = textureRegion.getV2();
        float f12 = f6 / 2.0f;
        float f13 = f7 / 2.0f;
        f3822b[0] = f2 + ((-PMath.sin(f8 - 1.5707964f)) * f12);
        f3822b[1] = f3 + (PMath.cos(f8 - 1.5707964f) * f12);
        f3822b[2] = f10;
        f3822b[3] = u2;
        f3822b[4] = v2;
        f3822b[5] = f2 + ((-PMath.sin(f8 + 1.5707964f)) * f12);
        f3822b[6] = f3 + (PMath.cos(f8 + 1.5707964f) * f12);
        f3822b[7] = f10;
        f3822b[8] = u;
        f3822b[9] = v2;
        f3822b[10] = f4 + ((-PMath.sin(f9 + 1.5707964f)) * f13);
        f3822b[11] = f5 + (PMath.cos(f9 + 1.5707964f) * f13);
        f3822b[12] = f11;
        f3822b[13] = u;
        f3822b[14] = v;
        f3822b[15] = f4 + ((-PMath.sin(f9 - 1.5707964f)) * f13);
        f3822b[16] = f5 + (PMath.cos(f9 - 1.5707964f) * f13);
        f3822b[17] = f11;
        f3822b[18] = u2;
        f3822b[19] = v;
        spriteCache.add(textureRegion.getTexture(), f3822b, 0, 20);
    }

    public static void texturedLineE(Batch batch, TextureRegion textureRegion, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, Color color, Color color2) {
        texturedLineF(batch, textureRegion, f2, f3, f4, f5, f6, f7, f8, f9, color.toFloatBits(), color2.toFloatBits());
    }

    public static void texturedLineA(Batch batch, Texture texture, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15) {
        float f16 = f10 / 2.0f;
        float f17 = f11 / 2.0f;
        f3822b[0] = f6 + ((-PMath.sin(f12 - 1.5707964f)) * f16);
        f3822b[1] = f7 + (PMath.cos(f12 - 1.5707964f) * f16);
        f3822b[2] = f14;
        f3822b[3] = f4;
        f3822b[4] = f5;
        f3822b[15] = f8 + ((-PMath.sin(f13 - 1.5707964f)) * f17);
        f3822b[16] = f9 + (PMath.cos(f13 - 1.5707964f) * f17);
        f3822b[17] = f15;
        f3822b[18] = f4;
        f3822b[19] = f3;
        f3822b[5] = f6 + ((-PMath.sin(f12 + 1.5707964f)) * f16);
        f3822b[6] = f7 + (PMath.cos(f12 + 1.5707964f) * f16);
        f3822b[7] = f14;
        f3822b[8] = f2;
        f3822b[9] = f5;
        f3822b[10] = f8 + ((-PMath.sin(f13 + 1.5707964f)) * f17);
        f3822b[11] = f9 + (PMath.cos(f13 + 1.5707964f) * f17);
        f3822b[12] = f15;
        f3822b[13] = f2;
        f3822b[14] = f3;
        batch.draw(texture, f3822b, 0, 20);
    }

    public static void texturedLineD(Batch batch, TextureRegion textureRegion, float f2, float f3, float f4, float f5, float f6, float f7, Color color, Color color2) {
        float atan2 = MathUtils.atan2(f5 - f3, f4 - f2) - 1.5707964f;
        texturedLineE(batch, textureRegion, f2, f3, f4, f5, f6, f7, atan2, atan2, color, color2);
    }

    public static void drawFontToCache(SpriteCache spriteCache, CharSequence charSequence, BitmapFont bitmapFont, float f2, float f3, float f4, float f5, int i, boolean z) {
        drawFontToCacheScaled(spriteCache, charSequence, bitmapFont, f2, f3, f4, f5, i, z, 1.0f);
    }

    public static void drawFontToCacheScaled(SpriteCache spriteCache, CharSequence charSequence, BitmapFont bitmapFont, float f2, float f3, float f4, float f5, int i, boolean z, float f6) {
        BitmapFontCache cache = bitmapFont.getCache();
        cache.clear();
        cache.addText(charSequence, f3, f4, f5, i, z);
        Array<TextureRegion> regions = bitmapFont.getRegions();
        int i2 = bitmapFont.getRegions().size;
        for (int i3 = 0; i3 < i2; i3++) {
            if (cache.getVertexCount(i3) > 0) {
                float[] vertices = cache.getVertices(i3);
                int vertexCount = cache.getVertexCount(i3);
                a((int) (vertexCount * 1.51f));
                int i4 = 0;
                for (int i5 = 0; i5 < vertexCount; i5 += 20) {
                    float f7 = vertices[i5];
                    float f8 = vertices[i5 + 1];
                    float f9 = vertices[i5 + 10];
                    float f10 = vertices[i5 + 6];
                    float f11 = vertices[i5 + 3];
                    float f12 = vertices[i5 + 4];
                    float f13 = vertices[i5 + 13];
                    float f14 = vertices[i5 + 14];
                    System.arraycopy(vertices, i5, c, i4, 15);
                    int i6 = i4 + 15;
                    int i7 = i6 + 1;
                    c[i6] = f9;
                    c[i7] = f10;
                    int i8 = i7 + 1 + 1;
                    int i9 = i8 + 1;
                    c[i8] = f13;
                    int i10 = i9 + 1;
                    c[i9] = f14;
                    int i11 = i10 + 1;
                    c[i10] = f9;
                    c[i11] = f8;
                    int i12 = i11 + 1 + 1;
                    int i13 = i12 + 1;
                    c[i12] = f13;
                    int i14 = i13 + 1;
                    c[i13] = f12;
                    int i15 = i14 + 1;
                    c[i14] = f7;
                    c[i15] = f8;
                    int i16 = i15 + 1 + 1;
                    int i17 = i16 + 1;
                    c[i16] = f11;
                    i4 = i17 + 1;
                    c[i17] = f12;
                }
                for (int i18 = 2; i18 < i4; i18 += 5) {
                    c[i18] = f2;
                }
                if (f6 != 1.0f) {
                    for (int i19 = 0; i19 < i4; i19 += 5) {
                        c[i19] = f3 + ((c[i19] - f3) * f6);
                        c[i19 + 1] = f4 + ((c[i19 + 1] - f4) * f6);
                    }
                }
                spriteCache.add(regions.get(i3).getTexture(), c, 0, i4);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v56, types: [int] */
    public static Mesh mergeMeshes(Array<Mesh> array, Array<Matrix4> array2) {
        if (array.size == 0) {
            return null;
        }
        int i = 0;
        int i2 = 0;
        VertexAttributes vertexAttributes = array.get(0).getVertexAttributes();
        int[] iArr = new int[vertexAttributes.size()];
        for (int i3 = 0; i3 < vertexAttributes.size(); i3++) {
            iArr[i3] = vertexAttributes.get(i3).usage;
        }
        for (int i4 = 0; i4 < array.size; i4++) {
            Mesh mesh = array.get(i4);
            if (mesh.getVertexAttributes().size() != vertexAttributes.size()) {
                array.set(i4, copyMesh(mesh, true, false, iArr));
            }
            i += (mesh.getNumVertices() * mesh.getVertexSize()) / 4;
            i2 += mesh.getNumIndices();
        }
        float[] fArr = new float[i];
        short[] sArr = new short[i2];
        int i5 = 0;
        short s = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < array.size; i7++) {
            Mesh mesh2 = array.get(i7);
            int numIndices = mesh2.getNumIndices();
            int numVertices = mesh2.getNumVertices();
            int vertexSize = mesh2.getVertexSize() / 4;
            int i8 = numVertices * vertexSize;
            VertexAttribute vertexAttribute = mesh2.getVertexAttribute(1);
            int i9 = vertexAttribute.offset / 4;
            int i10 = vertexAttribute.numComponents;
            mesh2.getIndices(sArr, i5);
            for (int i11 = i5; i11 < i5 + numIndices; i11++) {
                int i12 = i11;
                sArr[i12] = (short) (sArr[i12] + s);
            }
            i5 += numIndices;
            mesh2.getVertices(0, i8, fArr, i6);
            Mesh.transform(array2.get(i7), fArr, vertexSize, i9, i10, s, numVertices);
            s += numVertices;
            i6 += i8;
        }
        Mesh mesh3 = new Mesh(true, (int) s, sArr.length, array.get(0).getVertexAttributes());
        mesh3.setVertices(fArr);
        mesh3.setIndices(sArr);
        return mesh3;
    }

    public static Mesh copyMesh(Mesh mesh, boolean z, boolean z2, int[] iArr) {
        Mesh mesh2;
        int vertexSize = mesh.getVertexSize() / 4;
        int numVertices = mesh.getNumVertices();
        int i = numVertices;
        float[] fArr = new float[numVertices * vertexSize];
        mesh.getVertices(0, fArr.length, fArr);
        short[] sArr = null;
        VertexAttribute[] vertexAttributeArr = null;
        int i2 = 0;
        if (iArr != null) {
            int i3 = 0;
            int i4 = 0;
            for (int i5 : iArr) {
                if (mesh.getVertexAttribute(i5) != null) {
                    i3 += mesh.getVertexAttribute(i5).numComponents;
                    i4++;
                }
            }
            if (i3 > 0) {
                vertexAttributeArr = new VertexAttribute[i4];
                sArr = new short[i3];
                int i6 = -1;
                int i7 = -1;
                for (int i8 : iArr) {
                    VertexAttribute vertexAttribute = mesh.getVertexAttribute(i8);
                    if (vertexAttribute != null) {
                        for (int i9 = 0; i9 < vertexAttribute.numComponents; i9++) {
                            i6++;
                            sArr[i6] = (short) ((vertexAttribute.offset / 4) + i9);
                        }
                        i7++;
                        vertexAttributeArr[i7] = new VertexAttribute(vertexAttribute.usage, vertexAttribute.numComponents, vertexAttribute.alias);
                        i2 += vertexAttribute.numComponents;
                    }
                }
            }
        }
        if (sArr == null) {
            sArr = new short[vertexSize];
            short s = 0;
            while (true) {
                short s2 = s;
                if (s2 >= vertexSize) {
                    break;
                }
                sArr[s2] = s2;
                s = (short) (s2 + 1);
            }
            i2 = vertexSize;
        }
        int numIndices = mesh.getNumIndices();
        short[] sArr2 = null;
        if (numIndices > 0) {
            sArr2 = new short[numIndices];
            mesh.getIndices(sArr2);
            if (z2 || i2 != vertexSize) {
                float[] fArr2 = new float[fArr.length];
                int i10 = 0;
                for (int i11 = 0; i11 < numIndices; i11++) {
                    int i12 = sArr2[i11] * vertexSize;
                    short s3 = -1;
                    if (z2) {
                        short s4 = 0;
                        while (true) {
                            short s5 = s4;
                            if (s5 >= i10 || s3 >= 0) {
                                break;
                            }
                            int i13 = s5 * i2;
                            boolean z3 = true;
                            for (int i14 = 0; i14 < sArr.length && z3; i14++) {
                                if (fArr2[i13 + i14] != fArr[i12 + sArr[i14]]) {
                                    z3 = false;
                                }
                            }
                            if (z3) {
                                s3 = s5;
                            }
                            s4 = (short) (s5 + 1);
                        }
                    }
                    if (s3 > 0) {
                        sArr2[i11] = s3;
                    } else {
                        int i15 = i10 * i2;
                        for (int i16 = 0; i16 < sArr.length; i16++) {
                            fArr2[i15 + i16] = fArr[i12 + sArr[i16]];
                        }
                        sArr2[i11] = (short) i10;
                        i10++;
                    }
                }
                fArr = fArr2;
                i = i10;
            }
        }
        if (vertexAttributeArr == null) {
            mesh2 = new Mesh(z, i, sArr2 == null ? 0 : sArr2.length, mesh.getVertexAttributes());
        } else {
            mesh2 = new Mesh(z, i, sArr2 == null ? 0 : sArr2.length, vertexAttributeArr);
        }
        mesh2.setVertices(fArr, 0, i * i2);
        if (sArr2 != null) {
            mesh2.setIndices(sArr2);
        }
        return mesh2;
    }
}
