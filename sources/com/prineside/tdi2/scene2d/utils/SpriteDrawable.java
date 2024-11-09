package com.prineside.tdi2.scene2d.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/utils/SpriteDrawable.class */
public class SpriteDrawable extends BaseDrawable implements TransformDrawable {

    /* renamed from: a, reason: collision with root package name */
    private Sprite f2735a;

    public SpriteDrawable() {
    }

    public SpriteDrawable(Sprite sprite) {
        setSprite(sprite);
    }

    public SpriteDrawable(SpriteDrawable spriteDrawable) {
        super(spriteDrawable);
        setSprite(spriteDrawable.f2735a);
    }

    @Override // com.prineside.tdi2.scene2d.utils.BaseDrawable, com.prineside.tdi2.scene2d.utils.Drawable
    public void draw(Batch batch, float f, float f2, float f3, float f4) {
        Color color = this.f2735a.getColor();
        float floatBits = color.toFloatBits();
        this.f2735a.setColor(color.mul(batch.getColor()));
        this.f2735a.setRotation(0.0f);
        this.f2735a.setScale(1.0f, 1.0f);
        this.f2735a.setBounds(f, f2, f3, f4);
        this.f2735a.draw(batch);
        this.f2735a.setPackedColor(floatBits);
    }

    @Override // com.prineside.tdi2.scene2d.utils.TransformDrawable
    public void draw(Batch batch, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        Color color = this.f2735a.getColor();
        float floatBits = color.toFloatBits();
        this.f2735a.setColor(color.mul(batch.getColor()));
        this.f2735a.setOrigin(f3, f4);
        this.f2735a.setRotation(f9);
        this.f2735a.setScale(f7, f8);
        this.f2735a.setBounds(f, f2, f5, f6);
        this.f2735a.draw(batch);
        this.f2735a.setPackedColor(floatBits);
    }

    public void setSprite(Sprite sprite) {
        this.f2735a = sprite;
        setMinWidth(sprite.getWidth());
        setMinHeight(sprite.getHeight());
    }

    public Sprite getSprite() {
        return this.f2735a;
    }

    public SpriteDrawable tint(Color color) {
        Sprite sprite;
        if (this.f2735a instanceof TextureAtlas.AtlasSprite) {
            sprite = new TextureAtlas.AtlasSprite((TextureAtlas.AtlasSprite) this.f2735a);
        } else {
            sprite = new Sprite(this.f2735a);
        }
        sprite.setColor(color);
        sprite.setSize(getMinWidth(), getMinHeight());
        SpriteDrawable spriteDrawable = new SpriteDrawable(sprite);
        spriteDrawable.setLeftWidth(getLeftWidth());
        spriteDrawable.setRightWidth(getRightWidth());
        spriteDrawable.setTopHeight(getTopHeight());
        spriteDrawable.setBottomHeight(getBottomHeight());
        return spriteDrawable;
    }
}
