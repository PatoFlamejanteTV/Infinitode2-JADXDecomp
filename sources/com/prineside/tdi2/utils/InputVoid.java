package com.prineside.tdi2.utils;

import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.InputListener;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/InputVoid.class */
public class InputVoid extends InputListener {
    @Override // com.prineside.tdi2.scene2d.InputListener
    public boolean touchDown(InputEvent inputEvent, float f, float f2, int i, int i2) {
        return true;
    }

    @Override // com.prineside.tdi2.scene2d.InputListener
    public boolean mouseMoved(InputEvent inputEvent, float f, float f2) {
        return true;
    }
}
