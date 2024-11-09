package com.prineside.tdi2.scene2d.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/utils/TextureRegionDrawable.class */
public class TextureRegionDrawable extends BaseDrawable implements TransformDrawable {

    /* renamed from: a, reason: collision with root package name */
    private TextureRegion f2736a;

    public TextureRegionDrawable() {
    }

    public TextureRegionDrawable(Texture texture) {
        setRegion(new TextureRegion(texture));
    }

    public TextureRegionDrawable(TextureRegion textureRegion) {
        setRegion(textureRegion);
    }

    public TextureRegionDrawable(TextureRegionDrawable textureRegionDrawable) {
        super(textureRegionDrawable);
        setRegion(textureRegionDrawable.f2736a);
    }

    @Override // com.prineside.tdi2.scene2d.utils.BaseDrawable, com.prineside.tdi2.scene2d.utils.Drawable
    public void draw(Batch batch, float f, float f2, float f3, float f4) {
        batch.draw(this.f2736a, f, f2, f3, f4);
    }

    @Override // com.prineside.tdi2.scene2d.utils.TransformDrawable
    public void draw(Batch batch, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        batch.draw(this.f2736a, f, f2, f3, f4, f5, f6, f7, f8, f9);
    }

    public void setRegion(TextureRegion textureRegion) {
        this.f2736a = textureRegion;
        if (textureRegion != null) {
            setMinWidth(textureRegion.getRegionWidth());
            setMinHeight(textureRegion.getRegionHeight());
        }
    }

    public TextureRegion getRegion() {
        return this.f2736a;
    }

    public Drawable tint(Color color) {
        Sprite sprite;
        if (this.f2736a instanceof TextureAtlas.AtlasRegion) {
            sprite = new TextureAtlas.AtlasSprite((TextureAtlas.AtlasRegion) this.f2736a);
        } else {
            sprite = new Sprite(this.f2736a);
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
