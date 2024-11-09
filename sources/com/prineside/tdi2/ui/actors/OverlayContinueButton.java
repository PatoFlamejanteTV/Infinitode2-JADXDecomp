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

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/OverlayContinueButton.class */
public class OverlayContinueButton extends Group {
    private Image k;
    private boolean l;
    private boolean m;
    public Label label;
    private Color n;
    private Color o;
    private Color p;

    public OverlayContinueButton(String str, String str2, Color color, Color color2, Color color3, final Runnable runnable) {
        this.n = color;
        this.o = color2;
        this.p = color3;
        setTransform(false);
        setSize(408.0f, 127.0f);
        this.k = new Image(Game.i.assetManager.getDrawable("ui-level-selection-overlay-button"));
        this.k.setSize(408.0f, 127.0f);
        addActor(this.k);
        this.label = new Label(str, new Label.LabelStyle(Game.i.assetManager.getFont(30), Color.WHITE));
        this.label.setSize(305.0f, 20.0f);
        this.label.setPosition(0.0f, 45.0f);
        this.label.setAlignment(20);
        addActor(this.label);
        Image image = new Image(Game.i.assetManager.getDrawable(str2));
        image.setSize(64.0f, 64.0f);
        image.setPosition(313.0f, 30.0f);
        addActor(image);
        setTouchable(Touchable.enabled);
        addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.actors.OverlayContinueButton.1
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                runnable.run();
                Game.i.soundManager.playStatic(StaticSoundType.BUTTON);
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f, float f2, int i, int i2) {
                OverlayContinueButton.this.l = true;
                OverlayContinueButton.this.d();
                return super.touchDown(inputEvent, f, f2, i, i2);
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public void touchUp(InputEvent inputEvent, float f, float f2, int i, int i2) {
                OverlayContinueButton.this.l = false;
                OverlayContinueButton.this.d();
                super.touchUp(inputEvent, f, f2, i, i2);
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public void enter(InputEvent inputEvent, float f, float f2, int i, Actor actor) {
                if (i == -1) {
                    OverlayContinueButton.this.m = true;
                    OverlayContinueButton.this.d();
                }
                super.enter(inputEvent, f, f2, i, actor);
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public void exit(InputEvent inputEvent, float f, float f2, int i, Actor actor) {
                if (i == -1) {
                    OverlayContinueButton.this.m = false;
                    OverlayContinueButton.this.d();
                }
                super.exit(inputEvent, f, f2, i, actor);
            }
        });
        d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (this.l) {
            this.k.setColor(this.o);
        } else if (this.m) {
            this.k.setColor(this.p);
        } else {
            this.k.setColor(this.n);
        }
    }
}
