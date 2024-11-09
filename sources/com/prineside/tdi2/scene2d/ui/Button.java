package com.prineside.tdi2.scene2d.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.SnapshotArray;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Stage;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.utils.ChangeListener;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.scene2d.utils.Disableable;
import com.prineside.tdi2.scene2d.utils.Drawable;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/Button.class */
public class Button extends Table implements Disableable {
    private ButtonStyle n;
    boolean k;
    private boolean o;
    ButtonGroup l;
    private ClickListener p;
    private boolean q;

    public Button(Actor actor, ButtonStyle buttonStyle) {
        this.q = true;
        d();
        add((Button) actor);
        setStyle(buttonStyle);
        setSize(getPrefWidth(), getPrefHeight());
    }

    public Button(ButtonStyle buttonStyle) {
        this.q = true;
        d();
        setStyle(buttonStyle);
        setSize(getPrefWidth(), getPrefHeight());
    }

    public Button() {
        this.q = true;
        d();
    }

    private void d() {
        setTouchable(Touchable.enabled);
        ClickListener clickListener = new ClickListener() { // from class: com.prineside.tdi2.scene2d.ui.Button.1
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                if (Button.this.isDisabled()) {
                    return;
                }
                Button.this.a(!Button.this.k, true);
            }
        };
        this.p = clickListener;
        addListener(clickListener);
    }

    public Button(@Null Drawable drawable) {
        this(new ButtonStyle(drawable, null, null));
    }

    public Button(@Null Drawable drawable, @Null Drawable drawable2) {
        this(new ButtonStyle(drawable, drawable2, null));
    }

    public Button(@Null Drawable drawable, @Null Drawable drawable2, @Null Drawable drawable3) {
        this(new ButtonStyle(drawable, drawable2, drawable3));
    }

    public void setChecked(boolean z) {
        a(z, this.q);
    }

    final void a(boolean z, boolean z2) {
        if (this.k == z) {
            return;
        }
        if (this.l == null || this.l.a(this, z)) {
            this.k = z;
            if (z2) {
                ChangeListener.ChangeEvent changeEvent = (ChangeListener.ChangeEvent) Pools.obtain(ChangeListener.ChangeEvent.class);
                if (fire(changeEvent)) {
                    this.k = !z;
                }
                Pools.free(changeEvent);
            }
        }
    }

    public void toggle() {
        setChecked(!this.k);
    }

    public boolean isChecked() {
        return this.k;
    }

    public boolean isPressed() {
        return this.p.isVisualPressed();
    }

    public boolean isOver() {
        return this.p.isOver();
    }

    public ClickListener getClickListener() {
        return this.p;
    }

    @Override // com.prineside.tdi2.scene2d.utils.Disableable
    public boolean isDisabled() {
        return this.o;
    }

    @Override // com.prineside.tdi2.scene2d.utils.Disableable
    public void setDisabled(boolean z) {
        this.o = z;
    }

    public void setProgrammaticChangeEvents(boolean z) {
        this.q = z;
    }

    public void setStyle(ButtonStyle buttonStyle) {
        if (buttonStyle == null) {
            throw new IllegalArgumentException("style cannot be null.");
        }
        this.n = buttonStyle;
        setBackground(e());
    }

    public ButtonStyle getStyle() {
        return this.n;
    }

    @Null
    public ButtonGroup getButtonGroup() {
        return this.l;
    }

    @Null
    private Drawable e() {
        if (isDisabled() && this.n.disabled != null) {
            return this.n.disabled;
        }
        if (isPressed()) {
            if (isChecked() && this.n.checkedDown != null) {
                return this.n.checkedDown;
            }
            if (this.n.down != null) {
                return this.n.down;
            }
        }
        if (isOver()) {
            if (isChecked()) {
                if (this.n.checkedOver != null) {
                    return this.n.checkedOver;
                }
            } else if (this.n.over != null) {
                return this.n.over;
            }
        }
        boolean hasKeyboardFocus = hasKeyboardFocus();
        if (isChecked()) {
            if (hasKeyboardFocus && this.n.checkedFocused != null) {
                return this.n.checkedFocused;
            }
            if (this.n.checked != null) {
                return this.n.checked;
            }
            if (isOver() && this.n.over != null) {
                return this.n.over;
            }
        }
        return (!hasKeyboardFocus || this.n.focused == null) ? this.n.up : this.n.focused;
    }

    @Override // com.prineside.tdi2.scene2d.ui.Table, com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.Group, com.prineside.tdi2.scene2d.Actor
    public void draw(Batch batch, float f) {
        float f2;
        float f3;
        validate();
        setBackground(e());
        if (isPressed() && !isDisabled()) {
            f2 = this.n.pressedOffsetX;
            f3 = this.n.pressedOffsetY;
        } else if (isChecked() && !isDisabled()) {
            f2 = this.n.checkedOffsetX;
            f3 = this.n.checkedOffsetY;
        } else {
            f2 = this.n.unpressedOffsetX;
            f3 = this.n.unpressedOffsetY;
        }
        boolean z = (f2 == 0.0f && f3 == 0.0f) ? false : true;
        SnapshotArray<Actor> children = getChildren();
        if (z) {
            for (int i = 0; i < children.size; i++) {
                children.get(i).moveBy(f2, f3);
            }
        }
        super.draw(batch, f);
        if (z) {
            for (int i2 = 0; i2 < children.size; i2++) {
                children.get(i2).moveBy(-f2, -f3);
            }
        }
        Stage stage = getStage();
        if (stage != null && stage.getActionsRequestRendering() && isPressed() != this.p.isPressed()) {
            Gdx.graphics.requestRendering();
        }
    }

    @Override // com.prineside.tdi2.scene2d.ui.Table, com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public float getPrefWidth() {
        float prefWidth = super.getPrefWidth();
        if (this.n.up != null) {
            prefWidth = Math.max(prefWidth, this.n.up.getMinWidth());
        }
        if (this.n.down != null) {
            prefWidth = Math.max(prefWidth, this.n.down.getMinWidth());
        }
        if (this.n.checked != null) {
            prefWidth = Math.max(prefWidth, this.n.checked.getMinWidth());
        }
        return prefWidth;
    }

    @Override // com.prineside.tdi2.scene2d.ui.Table, com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public float getPrefHeight() {
        float prefHeight = super.getPrefHeight();
        if (this.n.up != null) {
            prefHeight = Math.max(prefHeight, this.n.up.getMinHeight());
        }
        if (this.n.down != null) {
            prefHeight = Math.max(prefHeight, this.n.down.getMinHeight());
        }
        if (this.n.checked != null) {
            prefHeight = Math.max(prefHeight, this.n.checked.getMinHeight());
        }
        return prefHeight;
    }

    @Override // com.prineside.tdi2.scene2d.ui.Table, com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public float getMinWidth() {
        return getPrefWidth();
    }

    @Override // com.prineside.tdi2.scene2d.ui.Table, com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public float getMinHeight() {
        return getPrefHeight();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/Button$ButtonStyle.class */
    public static class ButtonStyle {

        @Null
        public Drawable up;

        @Null
        public Drawable down;

        @Null
        public Drawable over;

        @Null
        public Drawable focused;

        @Null
        public Drawable disabled;

        @Null
        public Drawable checked;

        @Null
        public Drawable checkedOver;

        @Null
        public Drawable checkedDown;

        @Null
        public Drawable checkedFocused;
        public float pressedOffsetX;
        public float pressedOffsetY;
        public float unpressedOffsetX;
        public float unpressedOffsetY;
        public float checkedOffsetX;
        public float checkedOffsetY;

        public ButtonStyle() {
        }

        public ButtonStyle(@Null Drawable drawable, @Null Drawable drawable2, @Null Drawable drawable3) {
            this.up = drawable;
            this.down = drawable2;
            this.checked = drawable3;
        }

        public ButtonStyle(ButtonStyle buttonStyle) {
            this.up = buttonStyle.up;
            this.down = buttonStyle.down;
            this.over = buttonStyle.over;
            this.focused = buttonStyle.focused;
            this.disabled = buttonStyle.disabled;
            this.checked = buttonStyle.checked;
            this.checkedOver = buttonStyle.checkedOver;
            this.checkedDown = buttonStyle.checkedDown;
            this.checkedFocused = buttonStyle.checkedFocused;
            this.pressedOffsetX = buttonStyle.pressedOffsetX;
            this.pressedOffsetY = buttonStyle.pressedOffsetY;
            this.unpressedOffsetX = buttonStyle.unpressedOffsetX;
            this.unpressedOffsetY = buttonStyle.unpressedOffsetY;
            this.checkedOffsetX = buttonStyle.checkedOffsetX;
            this.checkedOffsetY = buttonStyle.checkedOffsetY;
        }
    }
}
