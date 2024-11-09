package com.badlogic.gdx.controllers.desktop.support;

import com.badlogic.gdx.controllers.ControllerMapping;
import com.studiohartman.jamepad.b;
import com.studiohartman.jamepad.c;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/controllers/desktop/support/JamepadMapping.class */
public class JamepadMapping extends ControllerMapping {
    private static JamepadMapping instance;

    JamepadMapping() {
        super(b.LEFTX.ordinal(), b.LEFTY.ordinal(), b.RIGHTX.ordinal(), b.RIGHTY.ordinal(), c.A.ordinal(), c.B.ordinal(), c.X.ordinal(), c.Y.ordinal(), c.BACK.ordinal(), c.START.ordinal(), c.LEFTBUMPER.ordinal(), -1, c.RIGHTBUMPER.ordinal(), -1, c.LEFTSTICK.ordinal(), c.RIGHTSTICK.ordinal(), c.DPAD_UP.ordinal(), c.DPAD_DOWN.ordinal(), c.DPAD_LEFT.ordinal(), c.DPAD_RIGHT.ordinal());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static JamepadMapping getInstance() {
        if (instance == null) {
            instance = new JamepadMapping();
        }
        return instance;
    }
}
