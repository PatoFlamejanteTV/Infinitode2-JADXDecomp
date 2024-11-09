package com.badlogic.gdx.scenes.scene2d.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectMap;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/utils/DragAndDrop.class */
public class DragAndDrop {
    static final Vector2 tmpVector = new Vector2();
    Source dragSource;
    Payload payload;
    Actor dragActor;
    boolean removeDragActor;
    Target target;
    boolean isValidTarget;
    private int button;
    float touchOffsetX;
    float touchOffsetY;
    long dragValidTime;
    final Array<Target> targets = new Array<>(8);
    final ObjectMap<Source, DragListener> sourceListeners = new ObjectMap<>(8);
    private float tapSquareSize = 8.0f;
    float dragActorX = 0.0f;
    float dragActorY = 0.0f;
    int dragTime = User32.VK_PLAY;
    int activePointer = -1;
    boolean cancelTouchFocus = true;
    boolean keepWithinStage = true;

    public void addSource(final Source source) {
        DragListener dragListener = new DragListener() { // from class: com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.1
            @Override // com.badlogic.gdx.scenes.scene2d.utils.DragListener
            public void dragStart(InputEvent inputEvent, float f, float f2, int i) {
                Stage stage;
                if (DragAndDrop.this.activePointer != -1) {
                    inputEvent.stop();
                    return;
                }
                DragAndDrop.this.activePointer = i;
                DragAndDrop.this.dragValidTime = System.currentTimeMillis() + DragAndDrop.this.dragTime;
                DragAndDrop.this.dragSource = source;
                DragAndDrop.this.payload = source.dragStart(inputEvent, getTouchDownX(), getTouchDownY(), i);
                inputEvent.stop();
                if (DragAndDrop.this.cancelTouchFocus && DragAndDrop.this.payload != null && (stage = source.getActor().getStage()) != null) {
                    stage.cancelTouchFocusExcept(this, source.getActor());
                }
            }

            @Override // com.badlogic.gdx.scenes.scene2d.utils.DragListener
            public void drag(InputEvent inputEvent, float f, float f2, int i) {
                if (DragAndDrop.this.payload != null && i == DragAndDrop.this.activePointer) {
                    source.drag(inputEvent, f, f2, i);
                    Stage stage = inputEvent.getStage();
                    Actor actor = DragAndDrop.this.dragActor;
                    float f3 = 0.0f;
                    float f4 = 0.0f;
                    if (actor != null) {
                        f3 = actor.getX();
                        f4 = actor.getY();
                        actor.setPosition(2.1474836E9f, 2.1474836E9f);
                    }
                    float stageX = inputEvent.getStageX() + DragAndDrop.this.touchOffsetX;
                    float stageY = inputEvent.getStageY() + DragAndDrop.this.touchOffsetY;
                    Actor hit = inputEvent.getStage().hit(stageX, stageY, true);
                    Actor actor2 = hit;
                    if (hit == null) {
                        actor2 = inputEvent.getStage().hit(stageX, stageY, false);
                    }
                    if (actor != null) {
                        actor.setPosition(f3, f4);
                    }
                    Target target = null;
                    DragAndDrop.this.isValidTarget = false;
                    if (actor2 != null) {
                        int i2 = 0;
                        int i3 = DragAndDrop.this.targets.size;
                        while (true) {
                            if (i2 >= i3) {
                                break;
                            }
                            Target target2 = DragAndDrop.this.targets.get(i2);
                            if (!target2.actor.isAscendantOf(actor2)) {
                                i2++;
                            } else {
                                target = target2;
                                target2.actor.stageToLocalCoordinates(DragAndDrop.tmpVector.set(stageX, stageY));
                                break;
                            }
                        }
                    }
                    if (target != DragAndDrop.this.target) {
                        if (DragAndDrop.this.target != null) {
                            DragAndDrop.this.target.reset(source, DragAndDrop.this.payload);
                        }
                        DragAndDrop.this.target = target;
                    }
                    if (target != null) {
                        DragAndDrop.this.isValidTarget = target.drag(source, DragAndDrop.this.payload, DragAndDrop.tmpVector.x, DragAndDrop.tmpVector.y, i);
                    }
                    Actor actor3 = null;
                    if (DragAndDrop.this.target != null) {
                        actor3 = DragAndDrop.this.isValidTarget ? DragAndDrop.this.payload.validDragActor : DragAndDrop.this.payload.invalidDragActor;
                    }
                    if (actor3 == null) {
                        actor3 = DragAndDrop.this.payload.dragActor;
                    }
                    if (actor3 != actor) {
                        if (actor != null && DragAndDrop.this.removeDragActor) {
                            actor.remove();
                        }
                        DragAndDrop.this.dragActor = actor3;
                        DragAndDrop.this.removeDragActor = actor3.getStage() == null;
                        if (DragAndDrop.this.removeDragActor) {
                            stage.addActor(actor3);
                        }
                    }
                    if (actor3 == null) {
                        return;
                    }
                    float stageX2 = (inputEvent.getStageX() - actor3.getWidth()) + DragAndDrop.this.dragActorX;
                    float stageY2 = inputEvent.getStageY() + DragAndDrop.this.dragActorY;
                    if (DragAndDrop.this.keepWithinStage) {
                        if (stageX2 < 0.0f) {
                            stageX2 = 0.0f;
                        }
                        if (stageY2 < 0.0f) {
                            stageY2 = 0.0f;
                        }
                        if (stageX2 + actor3.getWidth() > stage.getWidth()) {
                            stageX2 = stage.getWidth() - actor3.getWidth();
                        }
                        if (stageY2 + actor3.getHeight() > stage.getHeight()) {
                            stageY2 = stage.getHeight() - actor3.getHeight();
                        }
                    }
                    actor3.setPosition(stageX2, stageY2);
                }
            }

            @Override // com.badlogic.gdx.scenes.scene2d.utils.DragListener
            public void dragStop(InputEvent inputEvent, float f, float f2, int i) {
                if (i != DragAndDrop.this.activePointer) {
                    return;
                }
                DragAndDrop.this.activePointer = -1;
                if (DragAndDrop.this.payload == null) {
                    return;
                }
                if (System.currentTimeMillis() < DragAndDrop.this.dragValidTime) {
                    DragAndDrop.this.isValidTarget = false;
                } else if (!DragAndDrop.this.isValidTarget && DragAndDrop.this.target != null) {
                    DragAndDrop.this.target.actor.stageToLocalCoordinates(DragAndDrop.tmpVector.set(inputEvent.getStageX() + DragAndDrop.this.touchOffsetX, inputEvent.getStageY() + DragAndDrop.this.touchOffsetY));
                    DragAndDrop.this.isValidTarget = DragAndDrop.this.target.drag(source, DragAndDrop.this.payload, DragAndDrop.tmpVector.x, DragAndDrop.tmpVector.y, i);
                }
                if (DragAndDrop.this.dragActor != null && DragAndDrop.this.removeDragActor) {
                    DragAndDrop.this.dragActor.remove();
                }
                if (DragAndDrop.this.isValidTarget) {
                    DragAndDrop.this.target.actor.stageToLocalCoordinates(DragAndDrop.tmpVector.set(inputEvent.getStageX() + DragAndDrop.this.touchOffsetX, inputEvent.getStageY() + DragAndDrop.this.touchOffsetY));
                    DragAndDrop.this.target.drop(source, DragAndDrop.this.payload, DragAndDrop.tmpVector.x, DragAndDrop.tmpVector.y, i);
                }
                source.dragStop(inputEvent, f, f2, i, DragAndDrop.this.payload, DragAndDrop.this.isValidTarget ? DragAndDrop.this.target : null);
                if (DragAndDrop.this.target != null) {
                    DragAndDrop.this.target.reset(source, DragAndDrop.this.payload);
                }
                DragAndDrop.this.dragSource = null;
                DragAndDrop.this.payload = null;
                DragAndDrop.this.target = null;
                DragAndDrop.this.isValidTarget = false;
                DragAndDrop.this.dragActor = null;
            }
        };
        dragListener.setTapSquareSize(this.tapSquareSize);
        dragListener.setButton(this.button);
        source.actor.addCaptureListener(dragListener);
        this.sourceListeners.put(source, dragListener);
    }

    public void removeSource(Source source) {
        source.actor.removeCaptureListener(this.sourceListeners.remove(source));
    }

    public void addTarget(Target target) {
        this.targets.add(target);
    }

    public void removeTarget(Target target) {
        this.targets.removeValue(target, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void clear() {
        this.targets.clear();
        ObjectMap.Entries<Source, DragListener> it = this.sourceListeners.entries().iterator();
        while (it.hasNext()) {
            ObjectMap.Entry next = it.next();
            ((Source) next.key).actor.removeCaptureListener((EventListener) next.value);
        }
        this.sourceListeners.clear(8);
    }

    public void cancelTouchFocusExcept(Source source) {
        Stage stage;
        DragListener dragListener = this.sourceListeners.get(source);
        if (dragListener != null && (stage = source.getActor().getStage()) != null) {
            stage.cancelTouchFocusExcept(dragListener, source.getActor());
        }
    }

    public void setTapSquareSize(float f) {
        this.tapSquareSize = f;
    }

    public void setButton(int i) {
        this.button = i;
    }

    public void setDragActorPosition(float f, float f2) {
        this.dragActorX = f;
        this.dragActorY = f2;
    }

    public void setTouchOffset(float f, float f2) {
        this.touchOffsetX = f;
        this.touchOffsetY = f2;
    }

    public boolean isDragging() {
        return this.payload != null;
    }

    @Null
    public Actor getDragActor() {
        return this.dragActor;
    }

    @Null
    public Payload getDragPayload() {
        return this.payload;
    }

    @Null
    public Source getDragSource() {
        return this.dragSource;
    }

    public void setDragTime(int i) {
        this.dragTime = i;
    }

    public int getDragTime() {
        return this.dragTime;
    }

    public boolean isDragValid() {
        return this.payload != null && System.currentTimeMillis() >= this.dragValidTime;
    }

    public void setCancelTouchFocus(boolean z) {
        this.cancelTouchFocus = z;
    }

    public void setKeepWithinStage(boolean z) {
        this.keepWithinStage = z;
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/utils/DragAndDrop$Source.class */
    public static abstract class Source {
        final Actor actor;

        @Null
        public abstract Payload dragStart(InputEvent inputEvent, float f, float f2, int i);

        public Source(Actor actor) {
            if (actor == null) {
                throw new IllegalArgumentException("actor cannot be null.");
            }
            this.actor = actor;
        }

        public void drag(InputEvent inputEvent, float f, float f2, int i) {
        }

        public void dragStop(InputEvent inputEvent, float f, float f2, int i, @Null Payload payload, @Null Target target) {
        }

        public Actor getActor() {
            return this.actor;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/utils/DragAndDrop$Target.class */
    public static abstract class Target {
        final Actor actor;

        public abstract boolean drag(Source source, Payload payload, float f, float f2, int i);

        public abstract void drop(Source source, Payload payload, float f, float f2, int i);

        public Target(Actor actor) {
            if (actor == null) {
                throw new IllegalArgumentException("actor cannot be null.");
            }
            this.actor = actor;
            Stage stage = actor.getStage();
            if (stage != null && actor == stage.getRoot()) {
                throw new IllegalArgumentException("The stage root cannot be a drag and drop target.");
            }
        }

        public void reset(Source source, Payload payload) {
        }

        public Actor getActor() {
            return this.actor;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/utils/DragAndDrop$Payload.class */
    public static class Payload {

        @Null
        Actor dragActor;

        @Null
        Actor validDragActor;

        @Null
        Actor invalidDragActor;

        @Null
        Object object;

        public void setDragActor(@Null Actor actor) {
            this.dragActor = actor;
        }

        @Null
        public Actor getDragActor() {
            return this.dragActor;
        }

        public void setValidDragActor(@Null Actor actor) {
            this.validDragActor = actor;
        }

        @Null
        public Actor getValidDragActor() {
            return this.validDragActor;
        }

        public void setInvalidDragActor(@Null Actor actor) {
            this.invalidDragActor = actor;
        }

        @Null
        public Actor getInvalidDragActor() {
            return this.invalidDragActor;
        }

        @Null
        public Object getObject() {
            return this.object;
        }

        public void setObject(@Null Object obj) {
            this.object = obj;
        }
    }
}
