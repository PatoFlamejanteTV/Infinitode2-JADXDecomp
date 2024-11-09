package com.prineside.tdi2.ui.actors;

import com.badlogic.gdx.graphics.Color;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.ObjectConsumer;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/LabelToggleButton.class */
public class LabelToggleButton extends Table {
    public Image toggleImage;
    public Label label;
    private boolean k;
    public Color normalColor;
    public Color hoverColor;
    private boolean l;
    public ObjectConsumer<Boolean> onToggle;

    public LabelToggleButton() {
        this.normalColor = MaterialColor.LIGHT_BLUE.P300.cpy();
        this.hoverColor = Color.WHITE.cpy();
    }

    public void updateColor() {
        if (this.l) {
            this.label.setColor(this.hoverColor);
        } else {
            this.label.setColor(this.normalColor);
        }
    }

    public void setup(String str, boolean z, int i, float f, boolean z2, ObjectConsumer<Boolean> objectConsumer) {
        this.onToggle = objectConsumer;
        setTouchable(Touchable.enabled);
        this.label = new Label(str, Game.i.assetManager.getLabelStyle(i));
        this.label.setColor(MaterialColor.LIGHT_BLUE.P300);
        this.label.setAlignment(8);
        this.toggleImage = new Image();
        if (z2) {
            add((LabelToggleButton) this.label).left().padRight(32.0f);
            add((LabelToggleButton) this.toggleImage).size(f * 2.0f, f);
            add().height(1.0f).expandX().fillX();
        } else {
            add((LabelToggleButton) this.label).left().padRight(16.0f).expand();
            add((LabelToggleButton) this.toggleImage).right().size(f * 2.0f, f);
        }
        addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.actors.LabelToggleButton.1
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f2, float f3) {
                LabelToggleButton.this.setEnabled(!LabelToggleButton.this.k);
                if (LabelToggleButton.this.onToggle != null) {
                    LabelToggleButton.this.onToggle.accept(Boolean.valueOf(LabelToggleButton.this.k));
                }
                Game.i.soundManager.playStatic(StaticSoundType.BUTTON);
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public void enter(InputEvent inputEvent, float f2, float f3, int i2, Actor actor) {
                super.enter(inputEvent, f2, f3, i2, actor);
                if (i2 == -1) {
                    LabelToggleButton.this.l = true;
                    LabelToggleButton.this.updateColor();
                }
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public void exit(InputEvent inputEvent, float f2, float f3, int i2, Actor actor) {
                super.exit(inputEvent, f2, f3, i2, actor);
                if (i2 == -1) {
                    LabelToggleButton.this.l = false;
                    LabelToggleButton.this.updateColor();
                }
            }
        });
        setEnabled(z);
    }

    public LabelToggleButton(String str, boolean z, int i, float f, boolean z2, ObjectConsumer<Boolean> objectConsumer) {
        this.normalColor = MaterialColor.LIGHT_BLUE.P300.cpy();
        this.hoverColor = Color.WHITE.cpy();
        setup(str, z, i, f, z2, objectConsumer);
    }

    public LabelToggleButton(String str, boolean z, int i, float f, ObjectConsumer<Boolean> objectConsumer) {
        this(str, z, i, f, false, objectConsumer);
    }

    public LabelToggleButton(String str, boolean z, ObjectConsumer<Boolean> objectConsumer) {
        this(str, z, 30, 48.0f, objectConsumer);
    }

    public boolean isEnabled() {
        return this.k;
    }

    public void setEnabled(boolean z) {
        this.k = z;
        if (z) {
            this.toggleImage.setDrawable(Game.i.assetManager.getDrawable("settings-toggle-on"));
        } else {
            this.toggleImage.setDrawable(Game.i.assetManager.getDrawable("settings-toggle-off"));
        }
    }

    public void setText(CharSequence charSequence) {
        this.label.setText(charSequence);
    }
}
