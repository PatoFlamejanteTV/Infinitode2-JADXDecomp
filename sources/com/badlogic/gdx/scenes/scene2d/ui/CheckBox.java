package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Scaling;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/CheckBox.class */
public class CheckBox extends TextButton {
    private Image image;
    private Cell imageCell;
    private CheckBoxStyle style;

    public CheckBox(@Null String str, Skin skin) {
        this(str, (CheckBoxStyle) skin.get(CheckBoxStyle.class));
    }

    public CheckBox(@Null String str, Skin skin, String str2) {
        this(str, (CheckBoxStyle) skin.get(str2, CheckBoxStyle.class));
    }

    public CheckBox(@Null String str, CheckBoxStyle checkBoxStyle) {
        super(str, checkBoxStyle);
        Label label = getLabel();
        label.setAlignment(8);
        this.image = newImage();
        this.image.setDrawable(checkBoxStyle.checkboxOff);
        clearChildren();
        this.imageCell = add((CheckBox) this.image);
        add((CheckBox) label);
        setSize(getPrefWidth(), getPrefHeight());
    }

    protected Image newImage() {
        return new Image((Drawable) null, Scaling.none);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.TextButton, com.badlogic.gdx.scenes.scene2d.ui.Button
    public void setStyle(Button.ButtonStyle buttonStyle) {
        if (!(buttonStyle instanceof CheckBoxStyle)) {
            throw new IllegalArgumentException("style must be a CheckBoxStyle.");
        }
        this.style = (CheckBoxStyle) buttonStyle;
        super.setStyle(buttonStyle);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.TextButton, com.badlogic.gdx.scenes.scene2d.ui.Button
    public CheckBoxStyle getStyle() {
        return this.style;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.TextButton, com.badlogic.gdx.scenes.scene2d.ui.Button, com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    public void draw(Batch batch, float f) {
        this.image.setDrawable(getImageDrawable());
        super.draw(batch, f);
    }

    @Null
    protected Drawable getImageDrawable() {
        if (isDisabled()) {
            return (!this.isChecked || this.style.checkboxOnDisabled == null) ? this.style.checkboxOffDisabled : this.style.checkboxOnDisabled;
        }
        boolean z = isOver() && !isDisabled();
        return (!this.isChecked || this.style.checkboxOn == null) ? (!z || this.style.checkboxOver == null) ? this.style.checkboxOff : this.style.checkboxOver : (!z || this.style.checkboxOnOver == null) ? this.style.checkboxOn : this.style.checkboxOnOver;
    }

    public Image getImage() {
        return this.image;
    }

    public Cell getImageCell() {
        return this.imageCell;
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/CheckBox$CheckBoxStyle.class */
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
