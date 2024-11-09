package com.badlogic.gdx.scenes.scene2d;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Null;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/InputListener.class */
public class InputListener implements EventListener {
    private static final Vector2 tmpCoords = new Vector2();

    @Override // com.badlogic.gdx.scenes.scene2d.EventListener
    public boolean handle(Event event) {
        if (!(event instanceof InputEvent)) {
            return false;
        }
        InputEvent inputEvent = (InputEvent) event;
        switch (inputEvent.getType()) {
            case keyDown:
                return keyDown(inputEvent, inputEvent.getKeyCode());
            case keyUp:
                return keyUp(inputEvent, inputEvent.getKeyCode());
            case keyTyped:
                return keyTyped(inputEvent, inputEvent.getCharacter());
            default:
                inputEvent.toCoordinates(inputEvent.getListenerActor(), tmpCoords);
                switch (inputEvent.getType()) {
                    case touchDown:
                        boolean z = touchDown(inputEvent, tmpCoords.x, tmpCoords.y, inputEvent.getPointer(), inputEvent.getButton());
                        if (z && inputEvent.getTouchFocus()) {
                            inputEvent.getStage().addTouchFocus(this, inputEvent.getListenerActor(), inputEvent.getTarget(), inputEvent.getPointer(), inputEvent.getButton());
                        }
                        return z;
                    case touchUp:
                        touchUp(inputEvent, tmpCoords.x, tmpCoords.y, inputEvent.getPointer(), inputEvent.getButton());
                        return true;
                    case touchDragged:
                        touchDragged(inputEvent, tmpCoords.x, tmpCoords.y, inputEvent.getPointer());
                        return true;
                    case mouseMoved:
                        return mouseMoved(inputEvent, tmpCoords.x, tmpCoords.y);
                    case scrolled:
                        return scrolled(inputEvent, tmpCoords.x, tmpCoords.y, inputEvent.getScrollAmountX(), inputEvent.getScrollAmountY());
                    case enter:
                        enter(inputEvent, tmpCoords.x, tmpCoords.y, inputEvent.getPointer(), inputEvent.getRelatedActor());
                        return false;
                    case exit:
                        exit(inputEvent, tmpCoords.x, tmpCoords.y, inputEvent.getPointer(), inputEvent.getRelatedActor());
                        return false;
                    default:
                        return false;
                }
        }
    }

    public boolean touchDown(InputEvent inputEvent, float f, float f2, int i, int i2) {
        return false;
    }

    public void touchUp(InputEvent inputEvent, float f, float f2, int i, int i2) {
    }

    public void touchDragged(InputEvent inputEvent, float f, float f2, int i) {
    }

    public boolean mouseMoved(InputEvent inputEvent, float f, float f2) {
        return false;
    }

    public void enter(InputEvent inputEvent, float f, float f2, int i, @Null Actor actor) {
    }

    public void exit(InputEvent inputEvent, float f, float f2, int i, @Null Actor actor) {
    }

    public boolean scrolled(InputEvent inputEvent, float f, float f2, float f3, float f4) {
        return false;
    }

    public boolean keyDown(InputEvent inputEvent, int i) {
        return false;
    }

    public boolean keyUp(InputEvent inputEvent, int i) {
        return false;
    }

    public boolean keyTyped(InputEvent inputEvent, char c) {
        return false;
    }
}
