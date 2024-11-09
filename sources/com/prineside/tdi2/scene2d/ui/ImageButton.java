package com.prineside.tdi2.scene2d.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Scaling;
import com.prineside.tdi2.scene2d.ui.Button;
import com.prineside.tdi2.scene2d.utils.Drawable;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/ImageButton.class */
public class ImageButton extends Button {
    private final Image n;
    private ImageButtonStyle o;

    public ImageButton(ImageButtonStyle imageButtonStyle) {
        super(imageButtonStyle);
        this.n = d();
        add((ImageButton) this.n);
        setStyle(imageButtonStyle);
        setSize(getPrefWidth(), getPrefHeight());
    }

    public ImageButton(@Null Drawable drawable) {
        this(new ImageButtonStyle(null, null, null, drawable, null, null));
    }

    public ImageButton(@Null Drawable drawable, @Null Drawable drawable2) {
        this(new ImageButtonStyle(null, null, null, drawable, drawable2, null));
    }

    public ImageButton(@Null Drawable drawable, @Null Drawable drawable2, @Null Drawable drawable3) {
        this(new ImageButtonStyle(null, null, null, drawable, drawable2, drawable3));
    }

    private static Image d() {
        return new Image((Drawable) null, Scaling.fit);
    }

    @Override // com.prineside.tdi2.scene2d.ui.Button
    public void setStyle(Button.ButtonStyle buttonStyle) {
        if (!(buttonStyle instanceof ImageButtonStyle)) {
            throw new IllegalArgumentException("style must be an ImageButtonStyle.");
        }
        this.o = (ImageButtonStyle) buttonStyle;
        super.setStyle(buttonStyle);
        if (this.n != null) {
            f();
        }
    }

    @Override // com.prineside.tdi2.scene2d.ui.Button
    public ImageButtonStyle getStyle() {
        return this.o;
    }

    @Null
    private Drawable e() {
        if (isDisabled() && this.o.imageDisabled != null) {
            return this.o.imageDisabled;
        }
        if (isPressed()) {
            if (isChecked() && this.o.imageCheckedDown != null) {
                return this.o.imageCheckedDown;
            }
            if (this.o.imageDown != null) {
                return this.o.imageDown;
            }
        }
        if (isOver()) {
            if (isChecked()) {
                if (this.o.imageCheckedOver != null) {
                    return this.o.imageCheckedOver;
                }
            } else if (this.o.imageOver != null) {
                return this.o.imageOver;
            }
        }
        if (isChecked()) {
            if (this.o.imageChecked != null) {
                return this.o.imageChecked;
            }
            if (isOver() && this.o.imageOver != null) {
                return this.o.imageOver;
            }
        }
        return this.o.imageUp;
    }

    private void f() {
        this.n.setDrawable(e());
    }

    @Override // com.prineside.tdi2.scene2d.ui.Button, com.prineside.tdi2.scene2d.ui.Table, com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.Group, com.prineside.tdi2.scene2d.Actor
    public void draw(Batch batch, float f) {
        f();
        super.draw(batch, f);
    }

    public Image getImage() {
        return this.n;
    }

    public Cell getImageCell() {
        return getCell(this.n);
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
        return (str.indexOf(36) != -1 ? "ImageButton " : "") + str + ": " + this.n.getDrawable();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/ImageButton$ImageButtonStyle.class */
    public static class ImageButtonStyle extends Button.ButtonStyle {

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

        public ImageButtonStyle() {
        }

        public ImageButtonStyle(@Null Drawable drawable, @Null Drawable drawable2, @Null Drawable drawable3, @Null Drawable drawable4, @Null Drawable drawable5, @Null Drawable drawable6) {
            super(drawable, drawable2, drawable3);
            this.imageUp = drawable4;
            this.imageDown = drawable5;
            this.imageChecked = drawable6;
        }

        public ImageButtonStyle(ImageButtonStyle imageButtonStyle) {
            super(imageButtonStyle);
            this.imageUp = imageButtonStyle.imageUp;
            this.imageDown = imageButtonStyle.imageDown;
            this.imageOver = imageButtonStyle.imageOver;
            this.imageDisabled = imageButtonStyle.imageDisabled;
            this.imageChecked = imageButtonStyle.imageChecked;
            this.imageCheckedDown = imageButtonStyle.imageCheckedDown;
            this.imageCheckedOver = imageButtonStyle.imageCheckedOver;
        }

        public ImageButtonStyle(Button.ButtonStyle buttonStyle) {
            super(buttonStyle);
        }
    }
}
