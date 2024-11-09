package com.prineside.tdi2.ui.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Null;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.scene2d.utils.Drawable;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/PaddedImageButton.class */
public class PaddedImageButton extends Group {
    private final Image n;
    private final Image o;
    private Color p;
    private Color q;
    private Color r;

    @Null
    private Runnable t;
    public boolean disableClickThrough;
    private boolean k = false;
    private boolean l = false;
    private boolean m = true;
    private final Color s = new Color();

    public PaddedImageButton(Drawable drawable, Runnable runnable, Color color, Color color2, Color color3) {
        this.p = color;
        this.q = color2;
        this.r = color3;
        this.s.set(color);
        this.s.f889a *= 0.56f;
        this.t = runnable;
        setTransform(false);
        this.n = new Image();
        this.n.setVisible(false);
        addActor(this.n);
        this.o = new Image(drawable);
        addActor(this.o);
        setTouchable(Touchable.enabled);
        addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.actors.PaddedImageButton.1
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                if (PaddedImageButton.this.m && PaddedImageButton.this.t != null) {
                    PaddedImageButton.this.t.run();
                    Game.i.soundManager.playStatic(StaticSoundType.BUTTON);
                    if (PaddedImageButton.this.disableClickThrough) {
                        inputEvent.stop();
                        inputEvent.cancel();
                    }
                }
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f, float f2, int i, int i2) {
                PaddedImageButton.this.k = true;
                PaddedImageButton.this.updateColors();
                return super.touchDown(inputEvent, f, f2, i, i2) || PaddedImageButton.this.disableClickThrough;
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public void touchUp(InputEvent inputEvent, float f, float f2, int i, int i2) {
                PaddedImageButton.this.k = false;
                PaddedImageButton.this.updateColors();
                super.touchUp(inputEvent, f, f2, i, i2);
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public void enter(InputEvent inputEvent, float f, float f2, int i, Actor actor) {
                super.enter(inputEvent, f, f2, i, actor);
                if (i == -1) {
                    PaddedImageButton.this.l = true;
                    PaddedImageButton.this.updateColors();
                }
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public void exit(InputEvent inputEvent, float f, float f2, int i, Actor actor) {
                super.exit(inputEvent, f, f2, i, actor);
                if (i == -1) {
                    PaddedImageButton.this.l = false;
                    PaddedImageButton.this.updateColors();
                }
            }
        });
        updateColors();
    }

    public Runnable getClickHandler() {
        return this.t;
    }

    public void setClickHandler(Runnable runnable) {
        this.t = runnable;
    }

    public void setShadow(Drawable drawable, float f, float f2, float f3, float f4, Color color) {
        this.n.setDrawable(drawable);
        this.n.setPosition(f, f2);
        this.n.setSize(f3, f4);
        this.n.setColor(color);
        this.n.setVisible(true);
    }

    public void hideShadow() {
        this.n.setVisible(false);
    }

    public Color getDisabledColor() {
        return this.s;
    }

    public void setColors(Color color, Color color2, Color color3) {
        this.p = color;
        this.q = color2;
        this.r = color3;
        updateColors();
    }

    public void setDisabledColor(Color color) {
        this.s.set(color);
        updateColors();
    }

    public void setEnabled(boolean z) {
        this.m = z;
        updateColors();
    }

    public PaddedImageButton setIconSize(float f, float f2) {
        this.o.setSize(f, f2);
        return this;
    }

    public PaddedImageButton setIcon(Drawable drawable) {
        this.o.setDrawable(drawable);
        return this;
    }

    public PaddedImageButton setIconPosition(float f, float f2) {
        this.o.setPosition(f, f2);
        return this;
    }

    public Image getIcon() {
        return this.o;
    }

    public void updateColors() {
        if (this.m) {
            if (this.k) {
                this.o.setColor(this.r);
                return;
            } else if (this.l) {
                this.o.setColor(this.q);
                return;
            } else {
                this.o.setColor(this.p);
                return;
            }
        }
        this.o.setColor(this.s);
    }
}
