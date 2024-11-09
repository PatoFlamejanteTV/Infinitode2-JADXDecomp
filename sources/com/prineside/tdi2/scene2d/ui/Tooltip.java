package com.prineside.tdi2.scene2d.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Null;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.InputListener;
import com.prineside.tdi2.scene2d.Stage;
import com.prineside.tdi2.scene2d.Touchable;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/Tooltip.class */
public class Tooltip<T extends Actor> extends InputListener {
    private static Vector2 e = new Vector2();
    private final TooltipManager f;

    /* renamed from: a, reason: collision with root package name */
    final Container<T> f2683a;

    /* renamed from: b, reason: collision with root package name */
    boolean f2684b;
    boolean c;
    private boolean g;
    Actor d;

    public Tooltip(@Null T t) {
        this(t, TooltipManager.getInstance());
    }

    public Tooltip(@Null T t, TooltipManager tooltipManager) {
        this.f = tooltipManager;
        this.f2683a = new Container(t) { // from class: com.prineside.tdi2.scene2d.ui.Tooltip.1
            @Override // com.prineside.tdi2.scene2d.Group, com.prineside.tdi2.scene2d.Actor
            public void act(float f) {
                super.act(f);
                if (Tooltip.this.d == null || Tooltip.this.d.getStage() != null) {
                    return;
                }
                remove();
            }
        };
        this.f2683a.setTouchable(Touchable.disabled);
    }

    public TooltipManager getManager() {
        return this.f;
    }

    public Container<T> getContainer() {
        return this.f2683a;
    }

    public void setActor(@Null T t) {
        this.f2683a.setActor(t);
    }

    @Null
    public T getActor() {
        return this.f2683a.getActor();
    }

    public void setInstant(boolean z) {
        this.f2684b = z;
    }

    public void setAlways(boolean z) {
        this.c = z;
    }

    public void setTouchIndependent(boolean z) {
        this.g = z;
    }

    @Override // com.prineside.tdi2.scene2d.InputListener
    public boolean touchDown(InputEvent inputEvent, float f, float f2, int i, int i2) {
        if (this.f2684b) {
            this.f2683a.toFront();
            return false;
        }
        this.f.touchDown(this);
        return false;
    }

    @Override // com.prineside.tdi2.scene2d.InputListener
    public boolean mouseMoved(InputEvent inputEvent, float f, float f2) {
        if (this.f2683a.hasParent()) {
            return false;
        }
        a(inputEvent.getListenerActor(), f, f2);
        return true;
    }

    private void a(Actor actor, float f, float f2) {
        this.d = actor;
        Stage stage = actor.getStage();
        if (stage == null) {
            return;
        }
        this.f2683a.setSize(this.f.maxWidth, 2.1474836E9f);
        this.f2683a.validate();
        this.f2683a.width(this.f2683a.getActor().getWidth());
        this.f2683a.pack();
        float f3 = this.f.offsetX;
        float f4 = this.f.offsetY;
        float f5 = this.f.edgeDistance;
        Vector2 localToStageCoordinates = actor.localToStageCoordinates(e.set(f + f3, (f2 - f4) - this.f2683a.getHeight()));
        Vector2 vector2 = localToStageCoordinates;
        if (localToStageCoordinates.y < f5) {
            vector2 = actor.localToStageCoordinates(e.set(f + f3, f2 + f4));
        }
        if (vector2.x < f5) {
            vector2.x = f5;
        }
        if (vector2.x + this.f2683a.getWidth() > stage.getWidth() - f5) {
            vector2.x = (stage.getWidth() - f5) - this.f2683a.getWidth();
        }
        if (vector2.y + this.f2683a.getHeight() > stage.getHeight() - f5) {
            vector2.y = (stage.getHeight() - f5) - this.f2683a.getHeight();
        }
        this.f2683a.setPosition(vector2.x, vector2.y);
        Vector2 localToStageCoordinates2 = actor.localToStageCoordinates(e.set(actor.getWidth() / 2.0f, actor.getHeight() / 2.0f));
        localToStageCoordinates2.sub(this.f2683a.getX(), this.f2683a.getY());
        this.f2683a.setOrigin(localToStageCoordinates2.x, localToStageCoordinates2.y);
    }

    @Override // com.prineside.tdi2.scene2d.InputListener
    public void enter(InputEvent inputEvent, float f, float f2, int i, @Null Actor actor) {
        if (i != -1) {
            return;
        }
        if (this.g && Gdx.input.isTouched()) {
            return;
        }
        Actor listenerActor = inputEvent.getListenerActor();
        if (actor == null || !actor.isDescendantOf(listenerActor)) {
            a(listenerActor, f, f2);
            this.f.enter(this);
        }
    }

    @Override // com.prineside.tdi2.scene2d.InputListener
    public void exit(InputEvent inputEvent, float f, float f2, int i, @Null Actor actor) {
        if (actor == null || !actor.isDescendantOf(inputEvent.getListenerActor())) {
            hide();
        }
    }

    public void hide() {
        this.f.hide(this);
    }
}
