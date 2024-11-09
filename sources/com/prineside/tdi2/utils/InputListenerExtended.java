package com.prineside.tdi2.utils;

import com.badlogic.gdx.math.Vector2;
import com.prineside.tdi2.scene2d.Event;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.InputListener;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/InputListenerExtended.class */
public class InputListenerExtended extends InputListener {

    /* renamed from: a, reason: collision with root package name */
    private static final Vector2 f3844a = new Vector2();
    public static final int MODE_AUTO = 0;
    public static final int MODE_ALWAYS_TRUE = 1;
    public static final int MODE_ALWAYS_FALSE = 2;

    /* renamed from: b, reason: collision with root package name */
    private final int[] f3845b = new int[InputEvent.Type.values().length];

    public InputListenerExtended setMode(InputEvent.Type type, int i) {
        this.f3845b[type.ordinal()] = i;
        return this;
    }

    public int getMode(InputEvent.Type type) {
        return this.f3845b[type.ordinal()];
    }

    private boolean a(InputEvent.Type type, boolean z) {
        switch (getMode(type)) {
            case 1:
                return true;
            case 2:
                return false;
            default:
                return z;
        }
    }

    @Override // com.prineside.tdi2.scene2d.InputListener, com.prineside.tdi2.scene2d.EventListener
    public boolean handle(Event event) {
        if (!(event instanceof InputEvent)) {
            return false;
        }
        InputEvent inputEvent = (InputEvent) event;
        switch (inputEvent.getType()) {
            case keyDown:
                return a(InputEvent.Type.keyDown, keyDown(inputEvent, inputEvent.getKeyCode()));
            case keyUp:
                return a(InputEvent.Type.keyUp, keyUp(inputEvent, inputEvent.getKeyCode()));
            case keyTyped:
                return a(InputEvent.Type.keyTyped, keyTyped(inputEvent, inputEvent.getCharacter()));
            default:
                inputEvent.toCoordinates(inputEvent.getListenerActor(), f3844a);
                switch (inputEvent.getType()) {
                    case touchDown:
                        boolean z = touchDown(inputEvent, f3844a.x, f3844a.y, inputEvent.getPointer(), inputEvent.getButton());
                        if (z && inputEvent.getTouchFocus()) {
                            inputEvent.getStage().addTouchFocus(this, inputEvent.getListenerActor(), inputEvent.getTarget(), inputEvent.getPointer(), inputEvent.getButton());
                        }
                        return a(InputEvent.Type.touchDown, z);
                    case touchUp:
                        touchUp(inputEvent, f3844a.x, f3844a.y, inputEvent.getPointer(), inputEvent.getButton());
                        return a(InputEvent.Type.touchUp, true);
                    case touchDragged:
                        touchDragged(inputEvent, f3844a.x, f3844a.y, inputEvent.getPointer());
                        return a(InputEvent.Type.touchDragged, true);
                    case mouseMoved:
                        return a(InputEvent.Type.mouseMoved, mouseMoved(inputEvent, f3844a.x, f3844a.y));
                    case scrolled:
                        return a(InputEvent.Type.scrolled, scrolled(inputEvent, f3844a.x, f3844a.y, inputEvent.getScrollAmountX(), inputEvent.getScrollAmountY()));
                    case enter:
                        enter(inputEvent, f3844a.x, f3844a.y, inputEvent.getPointer(), inputEvent.getRelatedActor());
                        return a(InputEvent.Type.enter, false);
                    case exit:
                        exit(inputEvent, f3844a.x, f3844a.y, inputEvent.getPointer(), inputEvent.getRelatedActor());
                        return a(InputEvent.Type.exit, false);
                    default:
                        return false;
                }
        }
    }
}
