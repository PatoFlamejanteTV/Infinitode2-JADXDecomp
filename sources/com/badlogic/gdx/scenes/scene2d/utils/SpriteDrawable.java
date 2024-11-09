package com.badlogic.gdx.scenes.scene2d.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/utils/SpriteDrawable.class */
public class SpriteDrawable extends BaseDrawable implements TransformDrawable {
    private Sprite sprite;

    public SpriteDrawable() {
    }

    public SpriteDrawable(Sprite sprite) {
        setSprite(sprite);
    }

    public SpriteDrawable(SpriteDrawable spriteDrawable) {
        super(spriteDrawable);
        setSprite(spriteDrawable.sprite);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable, com.badlogic.gdx.scenes.scene2d.utils.Drawable
    public void draw(Batch batch, float f, float f2, float f3, float f4) {
        Color color = this.sprite.getColor();
        float packedColor = this.sprite.getPackedColor();
        this.sprite.setColor(color.mul(batch.getColor()));
        this.sprite.setRotation(0.0f);
        this.sprite.setScale(1.0f, 1.0f);
        this.sprite.setBounds(f, f2, f3, f4);
        this.sprite.draw(batch);
        this.sprite.setPackedColor(packedColor);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable
    public void draw(Batch batch, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        Color color = this.sprite.getColor();
        float packedColor = this.sprite.getPackedColor();
        this.sprite.setColor(color.mul(batch.getColor()));
        this.sprite.setOrigin(f3, f4);
        this.sprite.setRotation(f9);
        this.sprite.setScale(f7, f8);
        this.sprite.setBounds(f, f2, f5, f6);
        this.sprite.draw(batch);
        this.sprite.setPackedColor(packedColor);
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
        setMinWidth(sprite.getWidth());
        setMinHeight(sprite.getHeight());
    }

    public Sprite getSprite() {
        return this.sprite;
    }

    public SpriteDrawable tint(Color color) {
        Sprite sprite;
        if (this.sprite instanceof TextureAtlas.AtlasSprite) {
            sprite = new TextureAtlas.AtlasSprite((TextureAtlas.AtlasSprite) this.sprite);
        } else {
            sprite = new Sprite(this.sprite);
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
