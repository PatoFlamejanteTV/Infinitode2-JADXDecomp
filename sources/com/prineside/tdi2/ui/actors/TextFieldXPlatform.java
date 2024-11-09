package com.prineside.tdi2.ui.actors;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.prineside.tdi2.scene2d.ui.TextField;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/TextFieldXPlatform.class */
public class TextFieldXPlatform extends TextField {
    public TextFieldXPlatform(String str, TextField.TextFieldStyle textFieldStyle) {
        super(str, textFieldStyle);
        e();
    }

    private void e() {
        if (getStyle().font.getData().markupEnabled) {
            throw new IllegalArgumentException("font must have disabled markup");
        }
        Gdx.app.getType();
        Application.ApplicationType applicationType = Application.ApplicationType.Android;
    }
}
