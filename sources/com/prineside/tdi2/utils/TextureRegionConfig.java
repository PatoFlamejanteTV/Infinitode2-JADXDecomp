package com.prineside.tdi2.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteCache;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.ResourcePack;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/TextureRegionConfig.class */
public final class TextureRegionConfig {
    public TextureRegion textureRegion;

    /* renamed from: a, reason: collision with root package name */
    @NAGS
    private TextureRegionDrawable f3913a;
    public float x;
    public float y;
    public float width;
    public float height;
    public float originX;
    public float originY;
    public Color color;

    /* renamed from: b, reason: collision with root package name */
    private static final Color f3914b = new Color();
    private static final Color c = new Color();

    public static TextureRegionConfig fromJson(JsonValue jsonValue) {
        String string = jsonValue.getString("alias");
        float f = jsonValue.getFloat("x", 0.0f);
        float f2 = jsonValue.getFloat("y", 0.0f);
        float f3 = jsonValue.getFloat("w", 1.0f);
        float f4 = jsonValue.getFloat("h", 1.0f);
        float f5 = jsonValue.getFloat("ox", 0.5f);
        float f6 = jsonValue.getFloat("oy", 0.5f);
        float f7 = jsonValue.getFloat("rs", 0.0f);
        if (f7 != 0.0f) {
            float f8 = 1.0f / f7;
            f *= f8;
            f2 *= f8;
            f3 *= f8;
            f4 *= f8;
            f5 *= f8;
            f6 *= f8;
        }
        String string2 = jsonValue.getString("c", null);
        Color color = Color.WHITE;
        if (string2 != null) {
            color = Color.valueOf(string2);
        }
        TextureRegionConfig textureRegionConfig = new TextureRegionConfig(Game.i.assetManager.getTextureRegion(string));
        textureRegionConfig.color.set(color);
        textureRegionConfig.x = f;
        textureRegionConfig.y = f2;
        textureRegionConfig.originX = f5;
        textureRegionConfig.originY = f6;
        textureRegionConfig.width = f3;
        textureRegionConfig.height = f4;
        return textureRegionConfig;
    }

    public TextureRegionConfig(TextureRegionConfig textureRegionConfig) {
        this.color = Color.WHITE.cpy();
        this.textureRegion = textureRegionConfig.textureRegion;
        this.x = textureRegionConfig.x;
        this.y = textureRegionConfig.y;
        this.width = textureRegionConfig.width;
        this.height = textureRegionConfig.height;
        this.originX = textureRegionConfig.originX;
        this.originY = textureRegionConfig.originY;
        this.color.set(textureRegionConfig.color);
    }

    public TextureRegionConfig(TextureRegion textureRegion) {
        this.color = Color.WHITE.cpy();
        this.textureRegion = textureRegion;
        this.x = 0.0f;
        this.y = 0.0f;
        this.width = 1.0f;
        this.height = 1.0f;
        this.originX = 0.5f;
        this.originY = 0.5f;
    }

    public TextureRegionConfig(TextureRegion textureRegion, float f, float f2, float f3) {
        this.color = Color.WHITE.cpy();
        this.textureRegion = textureRegion;
        this.x = f / f3;
        this.y = f2 / f3;
        this.width = textureRegion.getRegionWidth() / f3;
        this.height = textureRegion.getRegionHeight() / f3;
        this.originX = this.width * 0.5f;
        this.originY = this.height * 0.5f;
    }

    public TextureRegionConfig(TextureRegion textureRegion, float f, float f2, float f3, float f4, float f5, float f6) {
        this.color = Color.WHITE.cpy();
        this.textureRegion = textureRegion;
        this.x = f;
        this.y = f2;
        this.width = f5;
        this.height = f6;
        this.originX = f3;
        this.originY = f4;
    }

    public TextureRegionConfig(TextureRegion textureRegion, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
        this.color = Color.WHITE.cpy();
        this.textureRegion = textureRegion;
        float f8 = 1.0f / f7;
        this.x = f * f8;
        this.y = f2 * f8;
        this.width = f5 * f8;
        this.height = f6 * f8;
        this.originX = f3 * f8;
        this.originY = f4 * f8;
    }

    public TextureRegionConfig(TextureRegion textureRegion, float f, float f2, float f3, float f4, float f5, float f6, Color color) {
        this(textureRegion, f, f2, f3, f4, f5, f6);
        this.color.set(color);
    }

    public TextureRegionConfig(TextureRegion textureRegion, float f, float f2, float f3, float f4, float f5, float f6, float f7, Color color) {
        this(textureRegion, f, f2, f3, f4, f5, f6, f7);
        this.color.set(color);
    }

    public static Quad toQuad(Array<TextureRegionConfig> array) {
        float f = 0.0f;
        float f2 = 0.0f;
        for (int i = 0; i < array.size; i++) {
            TextureRegionConfig textureRegionConfig = array.get(i);
            f = Math.max(f, textureRegionConfig.x + textureRegionConfig.width);
            f2 = Math.max(f2, textureRegionConfig.y + textureRegionConfig.height);
        }
        return toQuadWithSize(array, f, f2);
    }

    public static Quad toQuadWithSize(Array<TextureRegionConfig> array, float f, float f2) {
        Quad quad = new Quad(f, f2);
        for (int i = 0; i < array.size; i++) {
            TextureRegionConfig textureRegionConfig = array.get(i);
            quad.addRegion(new QuadRegion((ResourcePack.AtlasTextureRegion) textureRegionConfig.textureRegion, textureRegionConfig.x * f, textureRegionConfig.y * f2, textureRegionConfig.width * f, textureRegionConfig.height * f2, textureRegionConfig.color));
        }
        quad.setPivot(f * 0.5f, f2 * 0.5f);
        return quad;
    }

    public final Sprite createSprite(float f) {
        Sprite sprite = new Sprite(this.textureRegion);
        sprite.setSize(f * this.width, f * this.height);
        return sprite;
    }

    public final Image createImage(float f, float f2, float f3) {
        if (this.f3913a == null) {
            this.f3913a = new TextureRegionDrawable(this.textureRegion);
        }
        Image image = new Image(this.f3913a);
        image.setPosition(f + (this.x * f3), f2 + (this.y * f3));
        image.setSize(this.width * f3, this.height * f3);
        image.setColor(this.color);
        return image;
    }

    public final void drawCache(SpriteCache spriteCache, float f, float f2, float f3) {
        spriteCache.add(this.textureRegion, f + (this.x * f3), f2 + (this.y * f3), this.width * f3, this.height * f3);
    }

    @IgnoreMethodOverloadLuaDefWarning
    public final void drawBatch(Batch batch, float f, float f2, float f3, float f4, float f5) {
        c.set(batch.getColor());
        f3914b.set(batch.getColor());
        f3914b.mul(this.color);
        batch.setColor(f3914b);
        if (f5 == 0.0f && f4 == 1.0f) {
            batch.draw(this.textureRegion, f + (this.x * f3), f2 + (this.y * f3), this.width * f3, this.height * f3);
        } else {
            batch.draw(this.textureRegion, f + (this.x * f3), f2 + (this.y * f3), this.originX * f3, this.originY * f3, this.width * f3, this.height * f3, f4, f4, f5);
        }
        batch.setColor(c);
    }

    @IgnoreMethodOverloadLuaDefWarning
    public final void drawBatch(Batch batch, float f, float f2, float f3) {
        drawBatch(batch, f, f2, f3, 1.0f, 0.0f);
    }

    public static void drawCache(Array<TextureRegionConfig> array, SpriteCache spriteCache, float f, float f2, float f3) {
        for (int i = 0; i < array.size; i++) {
            array.items[i].drawCache(spriteCache, f, f2, f3);
        }
    }

    @IgnoreMethodOverloadLuaDefWarning
    public static void drawBatch(Array<TextureRegionConfig> array, Batch batch, float f, float f2, float f3) {
        for (int i = 0; i < array.size; i++) {
            array.items[i].drawBatch(batch, f, f2, f3);
        }
    }

    @IgnoreMethodOverloadLuaDefWarning
    public static void drawBatch(Array<TextureRegionConfig> array, Batch batch, float f, float f2, float f3, float f4, float f5) {
        for (int i = 0; i < array.size; i++) {
            array.items[i].drawBatch(batch, f, f2, f3, f4, f5);
        }
    }
}
