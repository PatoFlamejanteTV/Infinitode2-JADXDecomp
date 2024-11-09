package com.prineside.tdi2.ui.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Null;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.scene2d.ui.Image;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/FancyButton.class */
public final class FancyButton extends TableButton {

    @Null
    public LabelWithShadow label;

    public FancyButton(String str, Runnable runnable) {
        super(runnable);
        setFlavor(str);
    }

    public FancyButton(String str, Runnable runnable, Runnable runnable2) {
        super(runnable, runnable2);
        setFlavor(str);
        if (runnable2 != null) {
            Image image = new Image(Game.i.assetManager.getDrawable("button-hold-mark"));
            image.setSize(20.0f, 20.0f);
            image.setPosition(12.0f, 12.0f);
            addActor(image);
        }
    }

    public final FancyButton setFlavor(String str) {
        setBackgroundDrawables(Game.i.assetManager.getQuad("ui." + str + ".normal"), Game.i.assetManager.getQuad("ui." + str + ".active"), Game.i.assetManager.getQuad("ui." + str + ".hover"), Game.i.assetManager.getQuad("ui." + str + ".disabled"));
        return this;
    }

    public final FancyButton withLabel(int i, CharSequence charSequence) {
        this.label = new LabelWithShadow(charSequence, Game.i.assetManager.getLabelStyle(i)).setShadowShift(0.0f, 1.0f).setShadowColor(new Color(0.0f, 0.0f, 0.0f, 0.28f));
        add((FancyButton) this.label);
        return this;
    }
}
