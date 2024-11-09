package com.badlogic.gdx.input;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/input/TextInputWrapper.class */
public interface TextInputWrapper {
    String getText();

    int getSelectionStart();

    int getSelectionEnd();

    void setText(String str);

    void setPosition(int i);

    boolean shouldClose();
}
