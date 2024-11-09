package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Null;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/TextButton.class */
public class TextButton extends Button {
    private Label label;
    private TextButtonStyle style;

    public TextButton(@Null String str, Skin skin) {
        this(str, (TextButtonStyle) skin.get(TextButtonStyle.class));
        setSkin(skin);
    }

    public TextButton(@Null String str, Skin skin, String str2) {
        this(str, (TextButtonStyle) skin.get(str2, TextButtonStyle.class));
        setSkin(skin);
    }

    public TextButton(@Null String str, TextButtonStyle textButtonStyle) {
        setStyle(textButtonStyle);
        this.label = newLabel(str, new Label.LabelStyle(textButtonStyle.font, textButtonStyle.fontColor));
        this.label.setAlignment(1);
        add((TextButton) this.label).expand().fill();
        setSize(getPrefWidth(), getPrefHeight());
    }

    protected Label newLabel(String str, Label.LabelStyle labelStyle) {
        return new Label(str, labelStyle);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Button
    public void setStyle(Button.ButtonStyle buttonStyle) {
        if (buttonStyle == null) {
            throw new NullPointerException("style cannot be null");
        }
        if (!(buttonStyle instanceof TextButtonStyle)) {
            throw new IllegalArgumentException("style must be a TextButtonStyle.");
        }
        this.style = (TextButtonStyle) buttonStyle;
        super.setStyle(buttonStyle);
        if (this.label != null) {
            TextButtonStyle textButtonStyle = (TextButtonStyle) buttonStyle;
            Label.LabelStyle style = this.label.getStyle();
            style.font = textButtonStyle.font;
            style.fontColor = textButtonStyle.fontColor;
            this.label.setStyle(style);
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Button
    public TextButtonStyle getStyle() {
        return this.style;
    }

    @Null
    protected Color getFontColor() {
        if (isDisabled() && this.style.disabledFontColor != null) {
            return this.style.disabledFontColor;
        }
        if (isPressed()) {
            if (isChecked() && this.style.checkedDownFontColor != null) {
                return this.style.checkedDownFontColor;
            }
            if (this.style.downFontColor != null) {
                return this.style.downFontColor;
            }
        }
        if (isOver()) {
            if (isChecked()) {
                if (this.style.checkedOverFontColor != null) {
                    return this.style.checkedOverFontColor;
                }
            } else if (this.style.overFontColor != null) {
                return this.style.overFontColor;
            }
        }
        boolean hasKeyboardFocus = hasKeyboardFocus();
        if (isChecked()) {
            if (hasKeyboardFocus && this.style.checkedFocusedFontColor != null) {
                return this.style.checkedFocusedFontColor;
            }
            if (this.style.checkedFontColor != null) {
                return this.style.checkedFontColor;
            }
            if (isOver() && this.style.overFontColor != null) {
                return this.style.overFontColor;
            }
        }
        return (!hasKeyboardFocus || this.style.focusedFontColor == null) ? this.style.fontColor : this.style.focusedFontColor;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Button, com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    public void draw(Batch batch, float f) {
        this.label.getStyle().fontColor = getFontColor();
        super.draw(batch, f);
    }

    public void setLabel(Label label) {
        if (label == null) {
            throw new IllegalArgumentException("label cannot be null.");
        }
        getLabelCell().setActor(label);
        this.label = label;
    }

    public Label getLabel() {
        return this.label;
    }

    public Cell<Label> getLabelCell() {
        return getCell(this.label);
    }

    public void setText(@Null String str) {
        this.label.setText(str);
    }

    public CharSequence getText() {
        return this.label.getText();
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
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
        return (str.indexOf(36) != -1 ? "TextButton " : "") + str + ": " + ((Object) this.label.getText());
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/TextButton$TextButtonStyle.class */
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
