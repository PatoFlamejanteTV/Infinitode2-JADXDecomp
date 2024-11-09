package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Disableable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.SnapshotArray;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/Button.class */
public class Button extends Table implements Disableable {
    private ButtonStyle style;
    boolean isChecked;
    boolean isDisabled;
    ButtonGroup buttonGroup;
    private ClickListener clickListener;
    private boolean programmaticChangeEvents;

    public Button(Skin skin) {
        super(skin);
        this.programmaticChangeEvents = true;
        initialize();
        setStyle((ButtonStyle) skin.get(ButtonStyle.class));
        setSize(getPrefWidth(), getPrefHeight());
    }

    public Button(Skin skin, String str) {
        super(skin);
        this.programmaticChangeEvents = true;
        initialize();
        setStyle((ButtonStyle) skin.get(str, ButtonStyle.class));
        setSize(getPrefWidth(), getPrefHeight());
    }

    public Button(Actor actor, Skin skin, String str) {
        this(actor, (ButtonStyle) skin.get(str, ButtonStyle.class));
        setSkin(skin);
    }

    public Button(Actor actor, ButtonStyle buttonStyle) {
        this.programmaticChangeEvents = true;
        initialize();
        add((Button) actor);
        setStyle(buttonStyle);
        setSize(getPrefWidth(), getPrefHeight());
    }

    public Button(ButtonStyle buttonStyle) {
        this.programmaticChangeEvents = true;
        initialize();
        setStyle(buttonStyle);
        setSize(getPrefWidth(), getPrefHeight());
    }

    public Button() {
        this.programmaticChangeEvents = true;
        initialize();
    }

    private void initialize() {
        setTouchable(Touchable.enabled);
        ClickListener clickListener = new ClickListener() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Button.1
            @Override // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                if (Button.this.isDisabled()) {
                    return;
                }
                Button.this.setChecked(!Button.this.isChecked, true);
            }
        };
        this.clickListener = clickListener;
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

    public Button(Actor actor, Skin skin) {
        this(actor, (ButtonStyle) skin.get(ButtonStyle.class));
    }

    public void setChecked(boolean z) {
        setChecked(z, this.programmaticChangeEvents);
    }

    void setChecked(boolean z, boolean z2) {
        if (this.isChecked == z) {
            return;
        }
        if (this.buttonGroup == null || this.buttonGroup.canCheck(this, z)) {
            this.isChecked = z;
            if (z2) {
                ChangeListener.ChangeEvent changeEvent = (ChangeListener.ChangeEvent) Pools.obtain(ChangeListener.ChangeEvent.class);
                if (fire(changeEvent)) {
                    this.isChecked = !z;
                }
                Pools.free(changeEvent);
            }
        }
    }

    public void toggle() {
        setChecked(!this.isChecked);
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public boolean isPressed() {
        return this.clickListener.isVisualPressed();
    }

    public boolean isOver() {
        return this.clickListener.isOver();
    }

    public ClickListener getClickListener() {
        return this.clickListener;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.Disableable
    public boolean isDisabled() {
        return this.isDisabled;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.Disableable
    public void setDisabled(boolean z) {
        this.isDisabled = z;
    }

    public void setProgrammaticChangeEvents(boolean z) {
        this.programmaticChangeEvents = z;
    }

    public void setStyle(ButtonStyle buttonStyle) {
        if (buttonStyle == null) {
            throw new IllegalArgumentException("style cannot be null.");
        }
        this.style = buttonStyle;
        setBackground(getBackgroundDrawable());
    }

    public ButtonStyle getStyle() {
        return this.style;
    }

    @Null
    public ButtonGroup getButtonGroup() {
        return this.buttonGroup;
    }

    @Null
    protected Drawable getBackgroundDrawable() {
        if (isDisabled() && this.style.disabled != null) {
            return this.style.disabled;
        }
        if (isPressed()) {
            if (isChecked() && this.style.checkedDown != null) {
                return this.style.checkedDown;
            }
            if (this.style.down != null) {
                return this.style.down;
            }
        }
        if (isOver()) {
            if (isChecked()) {
                if (this.style.checkedOver != null) {
                    return this.style.checkedOver;
                }
            } else if (this.style.over != null) {
                return this.style.over;
            }
        }
        boolean hasKeyboardFocus = hasKeyboardFocus();
        if (isChecked()) {
            if (hasKeyboardFocus && this.style.checkedFocused != null) {
                return this.style.checkedFocused;
            }
            if (this.style.checked != null) {
                return this.style.checked;
            }
            if (isOver() && this.style.over != null) {
                return this.style.over;
            }
        }
        return (!hasKeyboardFocus || this.style.focused == null) ? this.style.up : this.style.focused;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    public void draw(Batch batch, float f) {
        float f2;
        float f3;
        validate();
        setBackground(getBackgroundDrawable());
        if (isPressed() && !isDisabled()) {
            f2 = this.style.pressedOffsetX;
            f3 = this.style.pressedOffsetY;
        } else if (isChecked() && !isDisabled()) {
            f2 = this.style.checkedOffsetX;
            f3 = this.style.checkedOffsetY;
        } else {
            f2 = this.style.unpressedOffsetX;
            f3 = this.style.unpressedOffsetY;
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
        if (stage != null && stage.getActionsRequestRendering() && isPressed() != this.clickListener.isPressed()) {
            Gdx.graphics.requestRendering();
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefWidth() {
        float prefWidth = super.getPrefWidth();
        if (this.style.up != null) {
            prefWidth = Math.max(prefWidth, this.style.up.getMinWidth());
        }
        if (this.style.down != null) {
            prefWidth = Math.max(prefWidth, this.style.down.getMinWidth());
        }
        if (this.style.checked != null) {
            prefWidth = Math.max(prefWidth, this.style.checked.getMinWidth());
        }
        return prefWidth;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefHeight() {
        float prefHeight = super.getPrefHeight();
        if (this.style.up != null) {
            prefHeight = Math.max(prefHeight, this.style.up.getMinHeight());
        }
        if (this.style.down != null) {
            prefHeight = Math.max(prefHeight, this.style.down.getMinHeight());
        }
        if (this.style.checked != null) {
            prefHeight = Math.max(prefHeight, this.style.checked.getMinHeight());
        }
        return prefHeight;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getMinWidth() {
        return getPrefWidth();
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getMinHeight() {
        return getPrefHeight();
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/Button$ButtonStyle.class */
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
