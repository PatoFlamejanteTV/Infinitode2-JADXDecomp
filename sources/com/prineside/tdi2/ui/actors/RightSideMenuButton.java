package com.prineside.tdi2.ui.actors;

import com.badlogic.gdx.graphics.Color;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.utils.MaterialColor;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/RightSideMenuButton.class */
public class RightSideMenuButton extends Group {
    private static final Color k = new Color(1.0f, 1.0f, 1.0f, 0.56f);
    public static final float WIDTH = 388.0f;
    public static final float HEIGHT = 108.0f;
    private Image o;
    private Image p;
    private Label q;
    private Runnable w;
    private boolean l = true;
    private boolean m = false;
    private boolean n = false;
    private Color r = MaterialColor.LIGHT_BLUE.P800.cpy();
    private Color s = MaterialColor.LIGHT_BLUE.P700.cpy();
    private Color t = MaterialColor.LIGHT_BLUE.P900.cpy();
    private Color u = MaterialColor.GREY.P800.cpy();
    private Color v = Color.WHITE.cpy();

    public RightSideMenuButton(String str, String str2, Runnable runnable) {
        Label.LabelStyle labelStyle = Game.i.assetManager.getLabelStyle(30);
        this.w = runnable;
        setTransform(false);
        setSize(388.0f, 108.0f);
        this.o = new Image(Game.i.assetManager.getDrawable("ui-right-menu-button"));
        this.o.setSize(488.0f, 108.0f);
        addActor(this.o);
        this.p = new Image(Game.i.assetManager.getDrawable(str2));
        this.p.setSize(40.0f, 40.0f);
        this.p.setPosition(32.0f, 40.0f);
        addActor(this.p);
        this.q = new Label(str, labelStyle);
        this.q.setSize(300.0f, 96.0f);
        this.q.setPosition(88.0f, 12.0f);
        addActor(this.q);
        setTouchable(Touchable.enabled);
        addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.actors.RightSideMenuButton.1
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                if (RightSideMenuButton.this.w != null) {
                    RightSideMenuButton.this.w.run();
                    Game.i.soundManager.playStatic(StaticSoundType.BUTTON);
                }
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f, float f2, int i, int i2) {
                RightSideMenuButton.this.m = true;
                RightSideMenuButton.this.d();
                return super.touchDown(inputEvent, f, f2, i, i2);
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public void touchUp(InputEvent inputEvent, float f, float f2, int i, int i2) {
                super.touchUp(inputEvent, f, f2, i, i2);
                RightSideMenuButton.this.m = false;
                RightSideMenuButton.this.d();
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public void enter(InputEvent inputEvent, float f, float f2, int i, Actor actor) {
                super.enter(inputEvent, f, f2, i, actor);
                if (i == -1) {
                    RightSideMenuButton.this.n = true;
                    RightSideMenuButton.this.d();
                }
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public void exit(InputEvent inputEvent, float f, float f2, int i, Actor actor) {
                super.exit(inputEvent, f, f2, i, actor);
                if (i == -1) {
                    RightSideMenuButton.this.n = false;
                    RightSideMenuButton.this.d();
                }
            }
        });
        d();
    }

    public void setClickHandler(Runnable runnable) {
        this.w = runnable;
    }

    public void setColors(Color color, Color color2, Color color3, Color color4) {
        this.r.set(color);
        this.s.set(color2);
        this.t.set(color3);
        this.v.set(color4);
        d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (this.l) {
            if (this.m) {
                this.o.setColor(this.t);
            } else if (this.n) {
                this.o.setColor(this.s);
            } else {
                this.o.setColor(this.r);
            }
            this.p.setColor(this.v);
            this.q.setColor(this.v);
            return;
        }
        this.p.setColor(k);
        this.q.setColor(k);
        this.o.setColor(this.u);
    }

    public void setEnabled(boolean z) {
        if (z != this.l) {
            this.l = z;
            d();
        }
    }

    public void setText(CharSequence charSequence) {
        this.q.setText(charSequence);
    }
}
