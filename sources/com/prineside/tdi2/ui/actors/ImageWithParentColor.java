package com.prineside.tdi2.ui.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.utils.Drawable;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/ImageWithParentColor.class */
public class ImageWithParentColor extends Image {
    private Color j;

    public ImageWithParentColor() {
        this((Drawable) null);
    }

    public ImageWithParentColor(Drawable drawable) {
        super(drawable);
        this.j = new Color();
    }

    public ImageWithParentColor(TextureRegion textureRegion) {
        super(textureRegion);
        this.j = new Color();
    }

    @Override // com.prineside.tdi2.scene2d.ui.Image, com.prineside.tdi2.scene2d.ui.Widget, com.prineside.tdi2.scene2d.Actor
    public void draw(Batch batch, float f) {
        this.j.set(getColor());
        Color color = getColor();
        Color color2 = getParent().getColor();
        setColor(color.r * color2.r, color.g * color2.g, color.f888b * color2.f888b, color.f889a);
        super.draw(batch, f);
        setColor(this.j);
    }
}
