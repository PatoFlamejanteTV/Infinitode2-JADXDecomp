package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Null;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/Tooltip.class */
public class Tooltip<T extends Actor> extends InputListener {
    static Vector2 tmp = new Vector2();
    private final TooltipManager manager;
    final Container<T> container;
    boolean instant;
    boolean always;
    boolean touchIndependent;
    Actor targetActor;

    public Tooltip(@Null T t) {
        this(t, TooltipManager.getInstance());
    }

    public Tooltip(@Null T t, TooltipManager tooltipManager) {
        this.manager = tooltipManager;
        this.container = new Container(t) { // from class: com.badlogic.gdx.scenes.scene2d.ui.Tooltip.1
            @Override // com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
            public void act(float f) {
                super.act(f);
                if (Tooltip.this.targetActor == null || Tooltip.this.targetActor.getStage() != null) {
                    return;
                }
                remove();
            }
        };
        this.container.setTouchable(Touchable.disabled);
    }

    public TooltipManager getManager() {
        return this.manager;
    }

    public Container<T> getContainer() {
        return this.container;
    }

    public void setActor(@Null T t) {
        this.container.setActor(t);
    }

    @Null
    public T getActor() {
        return this.container.getActor();
    }

    public void setInstant(boolean z) {
        this.instant = z;
    }

    public void setAlways(boolean z) {
        this.always = z;
    }

    public void setTouchIndependent(boolean z) {
        this.touchIndependent = z;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.InputListener
    public boolean touchDown(InputEvent inputEvent, float f, float f2, int i, int i2) {
        if (this.instant) {
            this.container.toFront();
            return false;
        }
        this.manager.touchDown(this);
        return false;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.InputListener
    public boolean mouseMoved(InputEvent inputEvent, float f, float f2) {
        if (this.container.hasParent()) {
            return false;
        }
        setContainerPosition(inputEvent.getListenerActor(), f, f2);
        return true;
    }

    private void setContainerPosition(Actor actor, float f, float f2) {
        this.targetActor = actor;
        Stage stage = actor.getStage();
        if (stage == null) {
            return;
        }
        this.container.setSize(this.manager.maxWidth, 2.1474836E9f);
        this.container.validate();
        this.container.width(this.container.getActor().getWidth());
        this.container.pack();
        float f3 = this.manager.offsetX;
        float f4 = this.manager.offsetY;
        float f5 = this.manager.edgeDistance;
        Vector2 localToStageCoordinates = actor.localToStageCoordinates(tmp.set(f + f3, (f2 - f4) - this.container.getHeight()));
        Vector2 vector2 = localToStageCoordinates;
        if (localToStageCoordinates.y < f5) {
            vector2 = actor.localToStageCoordinates(tmp.set(f + f3, f2 + f4));
        }
        if (vector2.x < f5) {
            vector2.x = f5;
        }
        if (vector2.x + this.container.getWidth() > stage.getWidth() - f5) {
            vector2.x = (stage.getWidth() - f5) - this.container.getWidth();
        }
        if (vector2.y + this.container.getHeight() > stage.getHeight() - f5) {
            vector2.y = (stage.getHeight() - f5) - this.container.getHeight();
        }
        this.container.setPosition(vector2.x, vector2.y);
        Vector2 localToStageCoordinates2 = actor.localToStageCoordinates(tmp.set(actor.getWidth() / 2.0f, actor.getHeight() / 2.0f));
        localToStageCoordinates2.sub(this.container.getX(), this.container.getY());
        this.container.setOrigin(localToStageCoordinates2.x, localToStageCoordinates2.y);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.InputListener
    public void enter(InputEvent inputEvent, float f, float f2, int i, @Null Actor actor) {
        if (i != -1) {
            return;
        }
        if (this.touchIndependent && Gdx.input.isTouched()) {
            return;
        }
        Actor listenerActor = inputEvent.getListenerActor();
        if (actor == null || !actor.isDescendantOf(listenerActor)) {
            setContainerPosition(listenerActor, f, f2);
            this.manager.enter(this);
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.InputListener
    public void exit(InputEvent inputEvent, float f, float f2, int i, @Null Actor actor) {
        if (actor == null || !actor.isDescendantOf(inputEvent.getListenerActor())) {
            hide();
        }
    }

    public void hide() {
        this.manager.hide(this);
    }
}
