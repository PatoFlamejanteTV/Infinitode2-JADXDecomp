package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/graphics/g2d/Sprite.class */
public class Sprite extends TextureRegion {
    static final int VERTEX_SIZE = 5;
    static final int SPRITE_SIZE = 20;
    final float[] vertices;
    private final Color color;
    private float packedColor;
    private float x;
    private float y;
    float width;
    float height;
    private float originX;
    private float originY;
    private float rotation;
    private float scaleX;
    private float scaleY;
    private boolean dirty;
    private Rectangle bounds;

    public Sprite() {
        this.vertices = new float[20];
        this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.packedColor = Color.WHITE_FLOAT_BITS;
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.dirty = true;
        setColor(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public Sprite(Texture texture) {
        this(texture, 0, 0, texture.getWidth(), texture.getHeight());
    }

    public Sprite(Texture texture, int i, int i2) {
        this(texture, 0, 0, i, i2);
    }

    public Sprite(Texture texture, int i, int i2, int i3, int i4) {
        this.vertices = new float[20];
        this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.packedColor = Color.WHITE_FLOAT_BITS;
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.dirty = true;
        if (texture == null) {
            throw new IllegalArgumentException("texture cannot be null.");
        }
        this.texture = texture;
        setRegion(i, i2, i3, i4);
        setColor(1.0f, 1.0f, 1.0f, 1.0f);
        setSize(Math.abs(i3), Math.abs(i4));
        setOrigin(this.width / 2.0f, this.height / 2.0f);
    }

    public Sprite(TextureRegion textureRegion) {
        this.vertices = new float[20];
        this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.packedColor = Color.WHITE_FLOAT_BITS;
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.dirty = true;
        setRegion(textureRegion);
        setColor(1.0f, 1.0f, 1.0f, 1.0f);
        setSize(textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
        setOrigin(this.width / 2.0f, this.height / 2.0f);
    }

    public Sprite(TextureRegion textureRegion, int i, int i2, int i3, int i4) {
        this.vertices = new float[20];
        this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.packedColor = Color.WHITE_FLOAT_BITS;
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.dirty = true;
        setRegion(textureRegion, i, i2, i3, i4);
        setColor(1.0f, 1.0f, 1.0f, 1.0f);
        setSize(Math.abs(i3), Math.abs(i4));
        setOrigin(this.width / 2.0f, this.height / 2.0f);
    }

    public Sprite(Sprite sprite) {
        this.vertices = new float[20];
        this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        this.packedColor = Color.WHITE_FLOAT_BITS;
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.dirty = true;
        set(sprite);
    }

    public void set(Sprite sprite) {
        if (sprite == null) {
            throw new IllegalArgumentException("sprite cannot be null.");
        }
        System.arraycopy(sprite.vertices, 0, this.vertices, 0, 20);
        this.texture = sprite.texture;
        this.u = sprite.u;
        this.v = sprite.v;
        this.u2 = sprite.u2;
        this.v2 = sprite.v2;
        this.x = sprite.x;
        this.y = sprite.y;
        this.width = sprite.width;
        this.height = sprite.height;
        this.regionWidth = sprite.regionWidth;
        this.regionHeight = sprite.regionHeight;
        this.originX = sprite.originX;
        this.originY = sprite.originY;
        this.rotation = sprite.rotation;
        this.scaleX = sprite.scaleX;
        this.scaleY = sprite.scaleY;
        this.color.set(sprite.color);
        this.dirty = sprite.dirty;
    }

    public void setBounds(float f, float f2, float f3, float f4) {
        this.x = f;
        this.y = f2;
        this.width = f3;
        this.height = f4;
        if (this.dirty) {
            return;
        }
        if (this.rotation != 0.0f || this.scaleX != 1.0f || this.scaleY != 1.0f) {
            this.dirty = true;
            return;
        }
        float f5 = f + f3;
        float f6 = f2 + f4;
        float[] fArr = this.vertices;
        fArr[0] = f;
        fArr[1] = f2;
        fArr[5] = f;
        fArr[6] = f6;
        fArr[10] = f5;
        fArr[11] = f6;
        fArr[15] = f5;
        fArr[16] = f2;
    }

    public void setSize(float f, float f2) {
        this.width = f;
        this.height = f2;
        if (this.dirty) {
            return;
        }
        if (this.rotation != 0.0f || this.scaleX != 1.0f || this.scaleY != 1.0f) {
            this.dirty = true;
            return;
        }
        float f3 = this.x + f;
        float f4 = this.y + f2;
        float[] fArr = this.vertices;
        fArr[0] = this.x;
        fArr[1] = this.y;
        fArr[5] = this.x;
        fArr[6] = f4;
        fArr[10] = f3;
        fArr[11] = f4;
        fArr[15] = f3;
        fArr[16] = this.y;
    }

    public void setPosition(float f, float f2) {
        this.x = f;
        this.y = f2;
        if (this.dirty) {
            return;
        }
        if (this.rotation != 0.0f || this.scaleX != 1.0f || this.scaleY != 1.0f) {
            this.dirty = true;
            return;
        }
        float f3 = f + this.width;
        float f4 = f2 + this.height;
        float[] fArr = this.vertices;
        fArr[0] = f;
        fArr[1] = f2;
        fArr[5] = f;
        fArr[6] = f4;
        fArr[10] = f3;
        fArr[11] = f4;
        fArr[15] = f3;
        fArr[16] = f2;
    }

    public void setOriginBasedPosition(float f, float f2) {
        setPosition(f - this.originX, f2 - this.originY);
    }

    public void setX(float f) {
        this.x = f;
        if (this.dirty) {
            return;
        }
        if (this.rotation != 0.0f || this.scaleX != 1.0f || this.scaleY != 1.0f) {
            this.dirty = true;
            return;
        }
        float f2 = f + this.width;
        float[] fArr = this.vertices;
        fArr[0] = f;
        fArr[5] = f;
        fArr[10] = f2;
        fArr[15] = f2;
    }

    public void setY(float f) {
        this.y = f;
        if (this.dirty) {
            return;
        }
        if (this.rotation != 0.0f || this.scaleX != 1.0f || this.scaleY != 1.0f) {
            this.dirty = true;
            return;
        }
        float f2 = f + this.height;
        float[] fArr = this.vertices;
        fArr[1] = f;
        fArr[6] = f2;
        fArr[11] = f2;
        fArr[16] = f;
    }

    public void setCenterX(float f) {
        setX(f - (this.width / 2.0f));
    }

    public void setCenterY(float f) {
        setY(f - (this.height / 2.0f));
    }

    public void setCenter(float f, float f2) {
        setPosition(f - (this.width / 2.0f), f2 - (this.height / 2.0f));
    }

    public void translateX(float f) {
        this.x += f;
        if (this.dirty) {
            return;
        }
        if (this.rotation != 0.0f || this.scaleX != 1.0f || this.scaleY != 1.0f) {
            this.dirty = true;
            return;
        }
        float[] fArr = this.vertices;
        fArr[0] = fArr[0] + f;
        fArr[5] = fArr[5] + f;
        fArr[10] = fArr[10] + f;
        fArr[15] = fArr[15] + f;
    }

    public void translateY(float f) {
        this.y += f;
        if (this.dirty) {
            return;
        }
        if (this.rotation != 0.0f || this.scaleX != 1.0f || this.scaleY != 1.0f) {
            this.dirty = true;
            return;
        }
        float[] fArr = this.vertices;
        fArr[1] = fArr[1] + f;
        fArr[6] = fArr[6] + f;
        fArr[11] = fArr[11] + f;
        fArr[16] = fArr[16] + f;
    }

    public void translate(float f, float f2) {
        this.x += f;
        this.y += f2;
        if (this.dirty) {
            return;
        }
        if (this.rotation != 0.0f || this.scaleX != 1.0f || this.scaleY != 1.0f) {
            this.dirty = true;
            return;
        }
        float[] fArr = this.vertices;
        fArr[0] = fArr[0] + f;
        fArr[1] = fArr[1] + f2;
        fArr[5] = fArr[5] + f;
        fArr[6] = fArr[6] + f2;
        fArr[10] = fArr[10] + f;
        fArr[11] = fArr[11] + f2;
        fArr[15] = fArr[15] + f;
        fArr[16] = fArr[16] + f2;
    }

    public void setColor(Color color) {
        this.color.set(color);
        this.packedColor = color.toFloatBits();
        float[] fArr = this.vertices;
        fArr[2] = this.packedColor;
        fArr[7] = this.packedColor;
        fArr[12] = this.packedColor;
        fArr[17] = this.packedColor;
    }

    public void setAlpha(float f) {
        if (this.color.f889a != f) {
            this.color.f889a = f;
            this.packedColor = this.color.toFloatBits();
            this.vertices[2] = this.packedColor;
            this.vertices[7] = this.packedColor;
            this.vertices[12] = this.packedColor;
            this.vertices[17] = this.packedColor;
        }
    }

    public void setColor(float f, float f2, float f3, float f4) {
        this.color.set(f, f2, f3, f4);
        this.packedColor = this.color.toFloatBits();
        float[] fArr = this.vertices;
        fArr[2] = this.packedColor;
        fArr[7] = this.packedColor;
        fArr[12] = this.packedColor;
        fArr[17] = this.packedColor;
    }

    public void setPackedColor(float f) {
        if (f != this.packedColor || (f == 0.0f && this.packedColor == 0.0f && Float.floatToIntBits(f) != Float.floatToIntBits(this.packedColor))) {
            this.packedColor = f;
            Color.abgr8888ToColor(this.color, f);
            float[] fArr = this.vertices;
            fArr[2] = f;
            fArr[7] = f;
            fArr[12] = f;
            fArr[17] = f;
        }
    }

    public void setOrigin(float f, float f2) {
        this.originX = f;
        this.originY = f2;
        this.dirty = true;
    }

    public void setOriginCenter() {
        this.originX = this.width / 2.0f;
        this.originY = this.height / 2.0f;
        this.dirty = true;
    }

    public void setRotation(float f) {
        this.rotation = f;
        this.dirty = true;
    }

    public float getRotation() {
        return this.rotation;
    }

    public void rotate(float f) {
        if (f == 0.0f) {
            return;
        }
        this.rotation += f;
        this.dirty = true;
    }

    public void rotate90(boolean z) {
        float[] fArr = this.vertices;
        if (z) {
            float f = fArr[4];
            fArr[4] = fArr[19];
            fArr[19] = fArr[14];
            fArr[14] = fArr[9];
            fArr[9] = f;
            float f2 = fArr[3];
            fArr[3] = fArr[18];
            fArr[18] = fArr[13];
            fArr[13] = fArr[8];
            fArr[8] = f2;
            return;
        }
        float f3 = fArr[4];
        fArr[4] = fArr[9];
        fArr[9] = fArr[14];
        fArr[14] = fArr[19];
        fArr[19] = f3;
        float f4 = fArr[3];
        fArr[3] = fArr[8];
        fArr[8] = fArr[13];
        fArr[13] = fArr[18];
        fArr[18] = f4;
    }

    public void setScale(float f) {
        this.scaleX = f;
        this.scaleY = f;
        this.dirty = true;
    }

    public void setScale(float f, float f2) {
        this.scaleX = f;
        this.scaleY = f2;
        this.dirty = true;
    }

    public void scale(float f) {
        this.scaleX += f;
        this.scaleY += f;
        this.dirty = true;
    }

    public float[] getVertices() {
        if (this.dirty) {
            this.dirty = false;
            float[] fArr = this.vertices;
            float f = -this.originX;
            float f2 = -this.originY;
            float f3 = f + this.width;
            float f4 = f2 + this.height;
            float f5 = this.x - f;
            float f6 = this.y - f2;
            if (this.scaleX != 1.0f || this.scaleY != 1.0f) {
                f *= this.scaleX;
                f2 *= this.scaleY;
                f3 *= this.scaleX;
                f4 *= this.scaleY;
            }
            if (this.rotation != 0.0f) {
                float cosDeg = MathUtils.cosDeg(this.rotation);
                float sinDeg = MathUtils.sinDeg(this.rotation);
                float f7 = f * cosDeg;
                float f8 = f * sinDeg;
                float f9 = f2 * cosDeg;
                float f10 = f2 * sinDeg;
                float f11 = f3 * cosDeg;
                float f12 = f3 * sinDeg;
                float f13 = f4 * cosDeg;
                float f14 = f4 * sinDeg;
                float f15 = (f7 - f10) + f5;
                float f16 = f9 + f8 + f6;
                fArr[0] = f15;
                fArr[1] = f16;
                float f17 = (f7 - f14) + f5;
                float f18 = f13 + f8 + f6;
                fArr[5] = f17;
                fArr[6] = f18;
                float f19 = (f11 - f14) + f5;
                float f20 = f13 + f12 + f6;
                fArr[10] = f19;
                fArr[11] = f20;
                fArr[15] = f15 + (f19 - f17);
                fArr[16] = f20 - (f18 - f16);
            } else {
                float f21 = f + f5;
                float f22 = f2 + f6;
                float f23 = f3 + f5;
                float f24 = f4 + f6;
                fArr[0] = f21;
                fArr[1] = f22;
                fArr[5] = f21;
                fArr[6] = f24;
                fArr[10] = f23;
                fArr[11] = f24;
                fArr[15] = f23;
                fArr[16] = f22;
            }
        }
        return this.vertices;
    }

    public Rectangle getBoundingRectangle() {
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        float f7;
        float f8;
        float[] vertices = getVertices();
        float f9 = vertices[0];
        float f10 = vertices[1];
        float f11 = vertices[0];
        float f12 = vertices[1];
        float f13 = f9 > vertices[5] ? vertices[5] : f9;
        float f14 = f13;
        if (f13 > vertices[10]) {
            f = vertices[10];
        } else {
            f = f14;
        }
        float f15 = f;
        if (f > vertices[15]) {
            f2 = vertices[15];
        } else {
            f2 = f15;
        }
        float f16 = f2;
        float f17 = f11 < vertices[5] ? vertices[5] : f11;
        float f18 = f17;
        if (f17 < vertices[10]) {
            f3 = vertices[10];
        } else {
            f3 = f18;
        }
        float f19 = f3;
        if (f3 < vertices[15]) {
            f4 = vertices[15];
        } else {
            f4 = f19;
        }
        float f20 = f4;
        float f21 = f10 > vertices[6] ? vertices[6] : f10;
        float f22 = f21;
        if (f21 > vertices[11]) {
            f5 = vertices[11];
        } else {
            f5 = f22;
        }
        float f23 = f5;
        if (f5 > vertices[16]) {
            f6 = vertices[16];
        } else {
            f6 = f23;
        }
        float f24 = f6;
        float f25 = f12 < vertices[6] ? vertices[6] : f12;
        float f26 = f25;
        if (f25 < vertices[11]) {
            f7 = vertices[11];
        } else {
            f7 = f26;
        }
        float f27 = f7;
        if (f7 < vertices[16]) {
            f8 = vertices[16];
        } else {
            f8 = f27;
        }
        float f28 = f8;
        if (this.bounds == null) {
            this.bounds = new Rectangle();
        }
        this.bounds.x = f16;
        this.bounds.y = f24;
        this.bounds.width = f20 - f16;
        this.bounds.height = f28 - f24;
        return this.bounds;
    }

    public void draw(Batch batch) {
        batch.draw(this.texture, getVertices(), 0, 20);
    }

    public void draw(Batch batch, float f) {
        float f2 = getColor().f889a;
        setAlpha(f2 * f);
        draw(batch);
        setAlpha(f2);
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }

    public float getOriginX() {
        return this.originX;
    }

    public float getOriginY() {
        return this.originY;
    }

    public float getScaleX() {
        return this.scaleX;
    }

    public float getScaleY() {
        return this.scaleY;
    }

    public Color getColor() {
        return this.color;
    }

    public float getPackedColor() {
        return this.packedColor;
    }

    @Override // com.badlogic.gdx.graphics.g2d.TextureRegion
    public void setRegion(float f, float f2, float f3, float f4) {
        super.setRegion(f, f2, f3, f4);
        float[] fArr = this.vertices;
        fArr[3] = f;
        fArr[4] = f4;
        fArr[8] = f;
        fArr[9] = f2;
        fArr[13] = f3;
        fArr[14] = f2;
        fArr[18] = f3;
        fArr[19] = f4;
    }

    @Override // com.badlogic.gdx.graphics.g2d.TextureRegion
    public void setU(float f) {
        super.setU(f);
        this.vertices[3] = f;
        this.vertices[8] = f;
    }

    @Override // com.badlogic.gdx.graphics.g2d.TextureRegion
    public void setV(float f) {
        super.setV(f);
        this.vertices[9] = f;
        this.vertices[14] = f;
    }

    @Override // com.badlogic.gdx.graphics.g2d.TextureRegion
    public void setU2(float f) {
        super.setU2(f);
        this.vertices[13] = f;
        this.vertices[18] = f;
    }

    @Override // com.badlogic.gdx.graphics.g2d.TextureRegion
    public void setV2(float f) {
        super.setV2(f);
        this.vertices[4] = f;
        this.vertices[19] = f;
    }

    public void setFlip(boolean z, boolean z2) {
        boolean z3 = false;
        boolean z4 = false;
        if (isFlipX() != z) {
            z3 = true;
        }
        if (isFlipY() != z2) {
            z4 = true;
        }
        flip(z3, z4);
    }

    @Override // com.badlogic.gdx.graphics.g2d.TextureRegion
    public void flip(boolean z, boolean z2) {
        super.flip(z, z2);
        float[] fArr = this.vertices;
        if (z) {
            float f = fArr[3];
            fArr[3] = fArr[13];
            fArr[13] = f;
            float f2 = fArr[8];
            fArr[8] = fArr[18];
            fArr[18] = f2;
        }
        if (z2) {
            float f3 = fArr[4];
            fArr[4] = fArr[14];
            fArr[14] = f3;
            float f4 = fArr[9];
            fArr[9] = fArr[19];
            fArr[19] = f4;
        }
    }

    @Override // com.badlogic.gdx.graphics.g2d.TextureRegion
    public void scroll(float f, float f2) {
        float[] fArr = this.vertices;
        if (f != 0.0f) {
            float f3 = (fArr[3] + f) % 1.0f;
            float width = f3 + (this.width / this.texture.getWidth());
            this.u = f3;
            this.u2 = width;
            fArr[3] = f3;
            fArr[8] = f3;
            fArr[13] = width;
            fArr[18] = width;
        }
        if (f2 != 0.0f) {
            float f4 = (fArr[9] + f2) % 1.0f;
            float height = f4 + (this.height / this.texture.getHeight());
            this.v = f4;
            this.v2 = height;
            fArr[4] = height;
            fArr[9] = f4;
            fArr[14] = f4;
            fArr[19] = height;
        }
    }
}
