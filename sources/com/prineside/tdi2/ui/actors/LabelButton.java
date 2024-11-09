package com.prineside.tdi2.ui.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Null;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.utils.MaterialColor;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/LabelButton.class */
public class LabelButton extends Label {
    public Color normalColor;
    public Color hoverColor;
    private boolean j;

    @Null
    private Runnable k;

    public LabelButton(CharSequence charSequence, Label.LabelStyle labelStyle, @Null Runnable runnable) {
        super(charSequence, labelStyle);
        this.normalColor = MaterialColor.LIGHT_BLUE.P300;
        this.hoverColor = Color.WHITE;
        this.k = runnable;
        setColor(MaterialColor.LIGHT_BLUE.P300);
        setTouchable(Touchable.enabled);
        addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.actors.LabelButton.1
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                if (LabelButton.this.k != null) {
                    LabelButton.this.k.run();
                    Game.i.soundManager.playStatic(StaticSoundType.BUTTON);
                }
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public void enter(InputEvent inputEvent, float f, float f2, int i, Actor actor) {
                super.enter(inputEvent, f, f2, i, actor);
                if (i == -1) {
                    LabelButton.this.j = true;
                    LabelButton.this.c();
                }
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public void exit(InputEvent inputEvent, float f, float f2, int i, Actor actor) {
                super.exit(inputEvent, f, f2, i, actor);
                if (i == -1) {
                    LabelButton.this.j = false;
                    LabelButton.this.c();
                }
            }
        });
    }

    public void setClickHandler(@Null Runnable runnable) {
        this.k = runnable;
    }

    public void setColors(Color color, Color color2) {
        this.normalColor = color;
        this.hoverColor = color2;
        c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (this.j) {
            setColor(this.hoverColor);
        } else {
            setColor(this.normalColor);
        }
    }
}
