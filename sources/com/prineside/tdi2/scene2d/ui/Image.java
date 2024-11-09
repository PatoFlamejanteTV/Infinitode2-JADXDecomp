package com.prineside.tdi2.scene2d.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Scaling;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.scene2d.utils.NinePatchDrawable;
import com.prineside.tdi2.scene2d.utils.TextureRegionDrawable;
import com.prineside.tdi2.scene2d.utils.TransformDrawable;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/Image.class */
public class Image extends Widget {
    private Scaling j;
    private int k;
    private float l;
    private float m;
    private float n;
    private float o;
    private Drawable p;

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

    public Image(@Null Drawable drawable) {
        this(drawable, Scaling.stretch, 1);
    }

    public Image(@Null Drawable drawable, Scaling scaling) {
        this(drawable, scaling, 1);
    }

    public Image(@Null Drawable drawable, Scaling scaling, int i) {
        this.k = 1;
        setDrawable(drawable);
        this.j = scaling;
        this.k = i;
        setSize(getPrefWidth(), getPrefHeight());
    }

    @Override // com.prineside.tdi2.scene2d.ui.Widget, com.prineside.tdi2.scene2d.utils.Layout
    public void layout() {
        if (this.p == null) {
            return;
        }
        Vector2 apply = this.j.apply(this.p.getMinWidth(), this.p.getMinHeight(), getWidth(), getHeight());
        this.n = apply.x;
        this.o = apply.y;
        if ((this.k & 8) != 0) {
            this.l = 0.0f;
        } else if ((this.k & 16) != 0) {
            this.l = (int) (r0 - this.n);
        } else {
            this.l = (int) ((r0 / 2.0f) - (this.n / 2.0f));
        }
        if ((this.k & 2) != 0) {
            this.m = (int) (r0 - this.o);
        } else if ((this.k & 4) != 0) {
            this.m = 0.0f;
        } else {
            this.m = (int) ((r0 / 2.0f) - (this.o / 2.0f));
        }
    }

    @Override // com.prineside.tdi2.scene2d.ui.Widget, com.prineside.tdi2.scene2d.Actor
    public void draw(Batch batch, float f) {
        validate();
        Color color = getColor();
        batch.setColor(color.r, color.g, color.f888b, color.f889a * f);
        float x = getX();
        float y = getY();
        float scaleX = getScaleX();
        float scaleY = getScaleY();
        if (this.p instanceof TransformDrawable) {
            float rotation = getRotation();
            if (scaleX != 1.0f || scaleY != 1.0f || rotation != 0.0f) {
                ((TransformDrawable) this.p).draw(batch, x + this.l, y + this.m, getOriginX() - this.l, getOriginY() - this.m, this.n, this.o, scaleX, scaleY, rotation);
                return;
            }
        }
        if (this.p != null) {
            this.p.draw(batch, x + this.l, y + this.m, this.n * scaleX, this.o * scaleY);
        }
    }

    public void setDrawable(@Null Drawable drawable) {
        if (this.p == drawable) {
            return;
        }
        if (drawable == null) {
            invalidateHierarchy();
        } else if (getPrefWidth() != drawable.getMinWidth() || getPrefHeight() != drawable.getMinHeight()) {
            invalidateHierarchy();
        }
        this.p = drawable;
    }

    @Null
    public Drawable getDrawable() {
        return this.p;
    }

    public void setScaling(Scaling scaling) {
        if (scaling == null) {
            throw new IllegalArgumentException("scaling cannot be null.");
        }
        this.j = scaling;
        invalidate();
    }

    public void setAlign(int i) {
        this.k = i;
        invalidate();
    }

    public int getAlign() {
        return this.k;
    }

    @Override // com.prineside.tdi2.scene2d.ui.Widget, com.prineside.tdi2.scene2d.utils.Layout
    public float getMinWidth() {
        return 0.0f;
    }

    @Override // com.prineside.tdi2.scene2d.ui.Widget, com.prineside.tdi2.scene2d.utils.Layout
    public float getMinHeight() {
        return 0.0f;
    }

    @Override // com.prineside.tdi2.scene2d.ui.Widget, com.prineside.tdi2.scene2d.utils.Layout
    public float getPrefWidth() {
        if (this.p != null) {
            return this.p.getMinWidth();
        }
        return 0.0f;
    }

    @Override // com.prineside.tdi2.scene2d.ui.Widget, com.prineside.tdi2.scene2d.utils.Layout
    public float getPrefHeight() {
        if (this.p != null) {
            return this.p.getMinHeight();
        }
        return 0.0f;
    }

    public float getImageX() {
        return this.l;
    }

    public float getImageY() {
        return this.m;
    }

    public float getImageWidth() {
        return this.n;
    }

    public float getImageHeight() {
        return this.o;
    }

    @Override // com.prineside.tdi2.scene2d.Actor
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
        return (str.indexOf(36) != -1 ? "Image " : "") + str + ": " + this.p;
    }
}
