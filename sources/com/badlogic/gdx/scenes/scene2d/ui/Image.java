package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Scaling;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/Image.class */
public class Image extends Widget {
    private Scaling scaling;
    private int align;
    private float imageX;
    private float imageY;
    private float imageWidth;
    private float imageHeight;
    private Drawable drawable;

    public Image() {
        this((Drawable) null);
    }

    public Image(@Null NinePatch ninePatch) {
        this(new NinePatchDrawable(ninePatch), Scaling.stretch, 1);
    }

    public Image(@Null TextureRegion textureRegion) {
        this(new TextureRegionDrawable(textureRegion), Scaling.stretch, 1);
    }

    public Image(Texture texture) {
        this(new TextureRegionDrawable(new TextureRegion(texture)));
    }

    public Image(Skin skin, String str) {
        this(skin.getDrawable(str), Scaling.stretch, 1);
    }

    public Image(@Null Drawable drawable) {
        this(drawable, Scaling.stretch, 1);
    }

    public Image(@Null Drawable drawable, Scaling scaling) {
        this(drawable, scaling, 1);
    }

    public Image(@Null Drawable drawable, Scaling scaling, int i) {
        this.align = 1;
        setDrawable(drawable);
        this.scaling = scaling;
        this.align = i;
        setSize(getPrefWidth(), getPrefHeight());
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public void layout() {
        if (this.drawable == null) {
            return;
        }
        Vector2 apply = this.scaling.apply(this.drawable.getMinWidth(), this.drawable.getMinHeight(), getWidth(), getHeight());
        this.imageWidth = apply.x;
        this.imageHeight = apply.y;
        if ((this.align & 8) != 0) {
            this.imageX = 0.0f;
        } else if ((this.align & 16) != 0) {
            this.imageX = (int) (r0 - this.imageWidth);
        } else {
            this.imageX = (int) ((r0 / 2.0f) - (this.imageWidth / 2.0f));
        }
        if ((this.align & 2) != 0) {
            this.imageY = (int) (r0 - this.imageHeight);
        } else if ((this.align & 4) != 0) {
            this.imageY = 0.0f;
        } else {
            this.imageY = (int) ((r0 / 2.0f) - (this.imageHeight / 2.0f));
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.Actor
    public void draw(Batch batch, float f) {
        validate();
        Color color = getColor();
        batch.setColor(color.r, color.g, color.f888b, color.f889a * f);
        float x = getX();
        float y = getY();
        float scaleX = getScaleX();
        float scaleY = getScaleY();
        if (this.drawable instanceof TransformDrawable) {
            float rotation = getRotation();
            if (scaleX != 1.0f || scaleY != 1.0f || rotation != 0.0f) {
                ((TransformDrawable) this.drawable).draw(batch, x + this.imageX, y + this.imageY, getOriginX() - this.imageX, getOriginY() - this.imageY, this.imageWidth, this.imageHeight, scaleX, scaleY, rotation);
                return;
            }
        }
        if (this.drawable != null) {
            this.drawable.draw(batch, x + this.imageX, y + this.imageY, this.imageWidth * scaleX, this.imageHeight * scaleY);
        }
    }

    public void setDrawable(Skin skin, String str) {
        setDrawable(skin.getDrawable(str));
    }

    public void setDrawable(@Null Drawable drawable) {
        if (this.drawable == drawable) {
            return;
        }
        if (drawable == null) {
            invalidateHierarchy();
        } else if (getPrefWidth() != drawable.getMinWidth() || getPrefHeight() != drawable.getMinHeight()) {
            invalidateHierarchy();
        }
        this.drawable = drawable;
    }

    @Null
    public Drawable getDrawable() {
        return this.drawable;
    }

    public void setScaling(Scaling scaling) {
        if (scaling == null) {
            throw new IllegalArgumentException("scaling cannot be null.");
        }
        this.scaling = scaling;
        invalidate();
    }

    public void setAlign(int i) {
        this.align = i;
        invalidate();
    }

    public int getAlign() {
        return this.align;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getMinWidth() {
        return 0.0f;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getMinHeight() {
        return 0.0f;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefWidth() {
        if (this.drawable != null) {
            return this.drawable.getMinWidth();
        }
        return 0.0f;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefHeight() {
        if (this.drawable != null) {
            return this.drawable.getMinHeight();
        }
        return 0.0f;
    }

    public float getImageX() {
        return this.imageX;
    }

    public float getImageY() {
        return this.imageY;
    }

    public float getImageWidth() {
        return this.imageWidth;
    }

    public float getImageHeight() {
        return this.imageHeight;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Actor
    public String toString() {
        String name = getName();
        if (name != null) {
            return name;
        }
        String name2 = getClass().getName();
        String str = name2;
        int lastIndexOf = name2.lastIndexOf(46);
        if (lastIndexOf != -1) {
            str = str.substring(lastIndexOf + 1);
        }
        return (str.indexOf(36) != -1 ? "Image " : "") + str + ": " + this.drawable;
    }
}
