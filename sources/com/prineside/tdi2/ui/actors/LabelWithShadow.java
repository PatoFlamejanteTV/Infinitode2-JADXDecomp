package com.prineside.tdi2.ui.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.prineside.tdi2.ui.actors.Label;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/LabelWithShadow.class */
public final class LabelWithShadow extends Label {
    public Color shadowColor;
    public float shiftX;
    public float shiftY;

    public LabelWithShadow(CharSequence charSequence, Label.LabelStyle labelStyle) {
        super(charSequence, labelStyle);
        this.shadowColor = new Color(0.0f, 0.0f, 0.0f, 0.56f);
        this.shiftX = 2.0f;
        this.shiftY = -2.0f;
    }

    public final LabelWithShadow setShadowColor(Color color) {
        this.shadowColor.set(color);
        return this;
    }

    public final LabelWithShadow setShadowShift(float f, float f2) {
        this.shiftX = f;
        this.shiftY = f2;
        return this;
    }

    @Override // com.prineside.tdi2.ui.actors.Label, com.prineside.tdi2.scene2d.ui.Widget, com.prineside.tdi2.scene2d.Actor
    public final void draw(Batch batch, float f) {
        validate();
        BitmapFontCache b2 = b();
        b2.tint(this.shadowColor);
        b2.setPosition(getX() + this.shiftX, getY() + this.shiftY);
        b2.draw(batch);
        super.draw(batch, f);
    }
}
