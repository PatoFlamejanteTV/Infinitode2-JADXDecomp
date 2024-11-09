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
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/ImageTextButton.class */
public class ImageTextButton extends Button {
    private final Image n;
    private Label o;
    private ImageTextButtonStyle p;

    public ImageTextButton(@Null String str, ImageTextButtonStyle imageTextButtonStyle) {
        super(imageTextButtonStyle);
        this.p = imageTextButtonStyle;
        defaults().space(3.0f);
        this.n = d();
        this.o = a(str, new Label.LabelStyle(imageTextButtonStyle.font, imageTextButtonStyle.fontColor));
        this.o.setAlignment(1);
        add((ImageTextButton) this.n);
        add((ImageTextButton) this.o);
        setStyle(imageTextButtonStyle);
        setSize(getPrefWidth(), getPrefHeight());
    }

    private static Image d() {
        return new Image((Drawable) null, Scaling.fit);
    }

    private static Label a(String str, Label.LabelStyle labelStyle) {
        return new Label(str, labelStyle);
    }

    @Override // com.prineside.tdi2.scene2d.ui.Button
    public void setStyle(Button.ButtonStyle buttonStyle) {
        if (!(buttonStyle instanceof ImageTextButtonStyle)) {
            throw new IllegalArgumentException("style must be a ImageTextButtonStyle.");
        }
        this.p = (ImageTextButtonStyle) buttonStyle;
        super.setStyle(buttonStyle);
        if (this.n != null) {
            f();
        }
        if (this.o != null) {
            Label.LabelStyle style = this.o.getStyle();
            style.font = ((ImageTextButtonStyle) buttonStyle).font;
            style.fontColor = g();
            this.o.setStyle(style);
        }
    }

    @Override // com.prineside.tdi2.scene2d.ui.Button
    public ImageTextButtonStyle getStyle() {
        return this.p;
    }

    @Null
    private Drawable e() {
        if (isDisabled() && this.p.imageDisabled != null) {
            return this.p.imageDisabled;
        }
        if (isPressed()) {
            if (isChecked() && this.p.imageCheckedDown != null) {
                return this.p.imageCheckedDown;
            }
            if (this.p.imageDown != null) {
                return this.p.imageDown;
            }
        }
        if (isOver()) {
            if (isChecked()) {
                if (this.p.imageCheckedOver != null) {
                    return this.p.imageCheckedOver;
                }
            } else if (this.p.imageOver != null) {
                return this.p.imageOver;
            }
        }
        if (isChecked()) {
            if (this.p.imageChecked != null) {
                return this.p.imageChecked;
            }
            if (isOver() && this.p.imageOver != null) {
                return this.p.imageOver;
            }
        }
        return this.p.imageUp;
    }

    private void f() {
        this.n.setDrawable(e());
    }

    @Null
    private Color g() {
        if (isDisabled() && this.p.disabledFontColor != null) {
            return this.p.disabledFontColor;
        }
        if (isPressed()) {
            if (isChecked() && this.p.checkedDownFontColor != null) {
                return this.p.checkedDownFontColor;
            }
            if (this.p.downFontColor != null) {
                return this.p.downFontColor;
            }
        }
        if (isOver()) {
            if (isChecked()) {
                if (this.p.checkedOverFontColor != null) {
                    return this.p.checkedOverFontColor;
                }
            } else if (this.p.overFontColor != null) {
                return this.p.overFontColor;
            }
        }
        boolean hasKeyboardFocus = hasKeyboardFocus();
        if (isChecked()) {
            if (hasKeyboardFocus && this.p.checkedFocusedFontColor != null) {
                return this.p.checkedFocusedFontColor;
            }
            if (this.p.checkedFontColor != null) {
                return this.p.checkedFontColor;
            }
            if (isOver() && this.p.overFontColor != null) {
                return this.p.overFontColor;
            }
        }
        return (!hasKeyboardFocus || this.p.focusedFontColor == null) ? this.p.fontColor : this.p.focusedFontColor;
    }

    @Override // com.prineside.tdi2.scene2d.ui.Button, com.prineside.tdi2.scene2d.ui.Table, com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.Group, com.prineside.tdi2.scene2d.Actor
    public void draw(Batch batch, float f) {
        f();
        this.o.getStyle().fontColor = g();
        super.draw(batch, f);
    }

    public Image getImage() {
        return this.n;
    }

    public Cell getImageCell() {
        return getCell(this.n);
    }

    public void setLabel(Label label) {
        getLabelCell().setActor(label);
        this.o = label;
    }

    public Label getLabel() {
        return this.o;
    }

    public Cell getLabelCell() {
        return getCell(this.o);
    }

    public void setText(CharSequence charSequence) {
        this.o.setText(charSequence);
    }

    public CharSequence getText() {
        return this.o.getText();
    }

    @Override // com.prineside.tdi2.scene2d.Group, com.prineside.tdi2.scene2d.Actor
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
        return (str.indexOf(36) != -1 ? "ImageTextButton " : "") + str + ": " + this.n.getDrawable() + SequenceUtils.SPACE + ((Object) this.o.getText());
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/ImageTextButton$ImageTextButtonStyle.class */
    public static class ImageTextButtonStyle extends TextButton.TextButtonStyle {

        @Null
        public Drawable imageUp;

        @Null
        public Drawable imageDown;

        @Null
        public Drawable imageOver;

        @Null
        public Drawable imageDisabled;

        @Null
        public Drawable imageChecked;

        @Null
        public Drawable imageCheckedDown;

        @Null
        public Drawable imageCheckedOver;

        public ImageTextButtonStyle() {
        }

        public ImageTextButtonStyle(@Null Drawable drawable, @Null Drawable drawable2, @Null Drawable drawable3, BitmapFont bitmapFont) {
            super(drawable, drawable2, drawable3, bitmapFont);
        }

        public ImageTextButtonStyle(ImageTextButtonStyle imageTextButtonStyle) {
            super(imageTextButtonStyle);
            this.imageUp = imageTextButtonStyle.imageUp;
            this.imageDown = imageTextButtonStyle.imageDown;
            this.imageOver = imageTextButtonStyle.imageOver;
            this.imageDisabled = imageTextButtonStyle.imageDisabled;
            this.imageChecked = imageTextButtonStyle.imageChecked;
            this.imageCheckedDown = imageTextButtonStyle.imageCheckedDown;
            this.imageCheckedOver = imageTextButtonStyle.imageCheckedOver;
        }

        public ImageTextButtonStyle(TextButton.TextButtonStyle textButtonStyle) {
            super(textButtonStyle);
        }
    }
}
