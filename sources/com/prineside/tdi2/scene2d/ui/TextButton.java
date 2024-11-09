package com.prineside.tdi2.scene2d.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Null;
import com.prineside.tdi2.scene2d.ui.Button;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.ui.actors.Label;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/TextButton.class */
public class TextButton extends Button {
    private Label n;
    private TextButtonStyle o;

    public TextButton(@Null String str, TextButtonStyle textButtonStyle) {
        setStyle(textButtonStyle);
        this.n = a(str, new Label.LabelStyle(textButtonStyle.font, textButtonStyle.fontColor));
        this.n.setAlignment(1);
        add((TextButton) this.n).expand().fill();
        setSize(getPrefWidth(), getPrefHeight());
    }

    private static Label a(String str, Label.LabelStyle labelStyle) {
        return new Label(str, labelStyle);
    }

    @Override // com.prineside.tdi2.scene2d.ui.Button
    public void setStyle(Button.ButtonStyle buttonStyle) {
        if (buttonStyle == null) {
            throw new NullPointerException("style cannot be null");
        }
        if (!(buttonStyle instanceof TextButtonStyle)) {
            throw new IllegalArgumentException("style must be a TextButtonStyle.");
        }
        this.o = (TextButtonStyle) buttonStyle;
        super.setStyle(buttonStyle);
        if (this.n != null) {
            TextButtonStyle textButtonStyle = (TextButtonStyle) buttonStyle;
            Label.LabelStyle style = this.n.getStyle();
            style.font = textButtonStyle.font;
            style.fontColor = textButtonStyle.fontColor;
            this.n.setStyle(style);
        }
    }

    @Override // com.prineside.tdi2.scene2d.ui.Button
    public TextButtonStyle getStyle() {
        return this.o;
    }

    @Null
    private Color d() {
        if (isDisabled() && this.o.disabledFontColor != null) {
            return this.o.disabledFontColor;
        }
        if (isPressed()) {
            if (isChecked() && this.o.checkedDownFontColor != null) {
                return this.o.checkedDownFontColor;
            }
            if (this.o.downFontColor != null) {
                return this.o.downFontColor;
            }
        }
        if (isOver()) {
            if (isChecked()) {
                if (this.o.checkedOverFontColor != null) {
                    return this.o.checkedOverFontColor;
                }
            } else if (this.o.overFontColor != null) {
                return this.o.overFontColor;
            }
        }
        boolean hasKeyboardFocus = hasKeyboardFocus();
        if (isChecked()) {
            if (hasKeyboardFocus && this.o.checkedFocusedFontColor != null) {
                return this.o.checkedFocusedFontColor;
            }
            if (this.o.checkedFontColor != null) {
                return this.o.checkedFontColor;
            }
            if (isOver() && this.o.overFontColor != null) {
                return this.o.overFontColor;
            }
        }
        return (!hasKeyboardFocus || this.o.focusedFontColor == null) ? this.o.fontColor : this.o.focusedFontColor;
    }

    @Override // com.prineside.tdi2.scene2d.ui.Button, com.prineside.tdi2.scene2d.ui.Table, com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.Group, com.prineside.tdi2.scene2d.Actor
    public void draw(Batch batch, float f) {
        this.n.getStyle().fontColor = d();
        super.draw(batch, f);
    }

    public void setLabel(Label label) {
        if (label == null) {
            throw new IllegalArgumentException("label cannot be null.");
        }
        getLabelCell().setActor(label);
        this.n = label;
    }

    public Label getLabel() {
        return this.n;
    }

    public Cell<Label> getLabelCell() {
        return getCell(this.n);
    }

    public void setText(@Null String str) {
        this.n.setText(str);
    }

    public CharSequence getText() {
        return this.n.getText();
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
        return (str.indexOf(36) != -1 ? "TextButton " : "") + str + ": " + ((Object) this.n.getText());
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/TextButton$TextButtonStyle.class */
    public static class TextButtonStyle extends Button.ButtonStyle {
        public BitmapFont font;

        @Null
        public Color fontColor;

        @Null
        public Color downFontColor;

        @Null
        public Color overFontColor;

        @Null
        public Color focusedFontColor;

        @Null
        public Color disabledFontColor;

        @Null
        public Color checkedFontColor;

        @Null
        public Color checkedDownFontColor;

        @Null
        public Color checkedOverFontColor;

        @Null
        public Color checkedFocusedFontColor;

        public TextButtonStyle() {
        }

        public TextButtonStyle(@Null Drawable drawable, @Null Drawable drawable2, @Null Drawable drawable3, @Null BitmapFont bitmapFont) {
            super(drawable, drawable2, drawable3);
            this.font = bitmapFont;
        }

        public TextButtonStyle(TextButtonStyle textButtonStyle) {
            super(textButtonStyle);
            this.font = textButtonStyle.font;
            if (textButtonStyle.fontColor != null) {
                this.fontColor = new Color(textButtonStyle.fontColor);
            }
            if (textButtonStyle.downFontColor != null) {
                this.downFontColor = new Color(textButtonStyle.downFontColor);
            }
            if (textButtonStyle.overFontColor != null) {
                this.overFontColor = new Color(textButtonStyle.overFontColor);
            }
            if (textButtonStyle.focusedFontColor != null) {
                this.focusedFontColor = new Color(textButtonStyle.focusedFontColor);
            }
            if (textButtonStyle.disabledFontColor != null) {
                this.disabledFontColor = new Color(textButtonStyle.disabledFontColor);
            }
            if (textButtonStyle.checkedFontColor != null) {
                this.checkedFontColor = new Color(textButtonStyle.checkedFontColor);
            }
            if (textButtonStyle.checkedDownFontColor != null) {
                this.checkedDownFontColor = new Color(textButtonStyle.checkedDownFontColor);
            }
            if (textButtonStyle.checkedOverFontColor != null) {
                this.checkedOverFontColor = new Color(textButtonStyle.checkedOverFontColor);
            }
            if (textButtonStyle.checkedFocusedFontColor != null) {
                this.checkedFocusedFontColor = new Color(textButtonStyle.checkedFocusedFontColor);
            }
        }
    }
}
