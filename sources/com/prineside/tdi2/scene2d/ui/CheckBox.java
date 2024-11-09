package com.prineside.tdi2.scene2d.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Scaling;
import com.prineside.tdi2.scene2d.ui.Button;
import com.prineside.tdi2.scene2d.ui.TextButton;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.ui.actors.Label;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/CheckBox.class */
public class CheckBox extends TextButton {
    private Image n;
    private Cell o;
    private CheckBoxStyle p;

    public CheckBox(@Null String str, CheckBoxStyle checkBoxStyle) {
        super(str, checkBoxStyle);
        Label label = getLabel();
        label.setAlignment(8);
        this.n = d();
        this.n.setDrawable(checkBoxStyle.checkboxOff);
        clearChildren();
        this.o = add((CheckBox) this.n);
        add((CheckBox) label);
        setSize(getPrefWidth(), getPrefHeight());
    }

    private static Image d() {
        return new Image((Drawable) null, Scaling.none);
    }

    @Override // com.prineside.tdi2.scene2d.ui.TextButton, com.prineside.tdi2.scene2d.ui.Button
    public void setStyle(Button.ButtonStyle buttonStyle) {
        if (!(buttonStyle instanceof CheckBoxStyle)) {
            throw new IllegalArgumentException("style must be a CheckBoxStyle.");
        }
        this.p = (CheckBoxStyle) buttonStyle;
        super.setStyle(buttonStyle);
    }

    @Override // com.prineside.tdi2.scene2d.ui.TextButton, com.prineside.tdi2.scene2d.ui.Button
    public CheckBoxStyle getStyle() {
        return this.p;
    }

    @Override // com.prineside.tdi2.scene2d.ui.TextButton, com.prineside.tdi2.scene2d.ui.Button, com.prineside.tdi2.scene2d.ui.Table, com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.Group, com.prineside.tdi2.scene2d.Actor
    public void draw(Batch batch, float f) {
        this.n.setDrawable(e());
        super.draw(batch, f);
    }

    @Null
    private Drawable e() {
        if (isDisabled()) {
            return (!this.k || this.p.checkboxOnDisabled == null) ? this.p.checkboxOffDisabled : this.p.checkboxOnDisabled;
        }
        boolean z = isOver() && !isDisabled();
        return (!this.k || this.p.checkboxOn == null) ? (!z || this.p.checkboxOver == null) ? this.p.checkboxOff : this.p.checkboxOver : (!z || this.p.checkboxOnOver == null) ? this.p.checkboxOn : this.p.checkboxOnOver;
    }

    public Image getImage() {
        return this.n;
    }

    public Cell getImageCell() {
        return this.o;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/CheckBox$CheckBoxStyle.class */
    public static class CheckBoxStyle extends TextButton.TextButtonStyle {
        public Drawable checkboxOn;
        public Drawable checkboxOff;

        @Null
        public Drawable checkboxOnOver;

        @Null
        public Drawable checkboxOver;

        @Null
        public Drawable checkboxOnDisabled;

        @Null
        public Drawable checkboxOffDisabled;

        public CheckBoxStyle() {
        }

        public CheckBoxStyle(Drawable drawable, Drawable drawable2, BitmapFont bitmapFont, @Null Color color) {
            this.checkboxOff = drawable;
            this.checkboxOn = drawable2;
            this.font = bitmapFont;
            this.fontColor = color;
        }

        public CheckBoxStyle(CheckBoxStyle checkBoxStyle) {
            super(checkBoxStyle);
            this.checkboxOff = checkBoxStyle.checkboxOff;
            this.checkboxOn = checkBoxStyle.checkboxOn;
            this.checkboxOnOver = checkBoxStyle.checkboxOnOver;
            this.checkboxOver = checkBoxStyle.checkboxOver;
            this.checkboxOnDisabled = checkBoxStyle.checkboxOnDisabled;
            this.checkboxOffDisabled = checkBoxStyle.checkboxOffDisabled;
        }
    }
}
