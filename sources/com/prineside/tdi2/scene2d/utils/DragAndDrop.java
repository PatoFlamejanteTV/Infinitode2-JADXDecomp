package com.prineside.tdi2.scene2d.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectMap;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.EventListener;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Stage;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/utils/DragAndDrop.class */
public class DragAndDrop {

    /* renamed from: a, reason: collision with root package name */
    static final Vector2 f2710a = new Vector2();

    /* renamed from: b, reason: collision with root package name */
    Source f2711b;
    Payload c;
    Actor d;
    boolean e;
    Target f;
    boolean g;
    private int t;
    float k;
    float l;
    long m;
    final Array<Target> h = new Array<>(8);
    private ObjectMap<Source, DragListener> r = new ObjectMap<>(8);
    private float s = 8.0f;
    float i = 0.0f;
    float j = 0.0f;
    int n = User32.VK_PLAY;
    int o = -1;
    boolean p = true;
    boolean q = true;

    public void addSource(final Source source) {
        DragListener dragListener = new DragListener() { // from class: com.prineside.tdi2.scene2d.utils.DragAndDrop.1
            @Override // com.prineside.tdi2.scene2d.utils.DragListener
            public void dragStart(InputEvent inputEvent, float f, float f2, int i) {
                Stage stage;
                if (DragAndDrop.this.o != -1) {
                    inputEvent.stop();
                    return;
                }
                DragAndDrop.this.o = i;
                DragAndDrop.this.m = System.currentTimeMillis() + DragAndDrop.this.n;
                DragAndDrop.this.f2711b = source;
                DragAndDrop.this.c = source.dragStart(inputEvent, getTouchDownX(), getTouchDownY(), i);
                inputEvent.stop();
                if (DragAndDrop.this.p && DragAndDrop.this.c != null && (stage = source.getActor().getStage()) != null) {
                    stage.cancelTouchFocusExcept(this, source.getActor());
                }
            }

            @Override // com.prineside.tdi2.scene2d.utils.DragListener
            public void drag(InputEvent inputEvent, float f, float f2, int i) {
                if (DragAndDrop.this.c != null && i == DragAndDrop.this.o) {
                    source.drag(inputEvent, f, f2, i);
                    Stage stage = inputEvent.getStage();
                    Actor actor = DragAndDrop.this.d;
                    float f3 = 0.0f;
                    float f4 = 0.0f;
                    if (actor != null) {
                        f3 = actor.getX();
                        f4 = actor.getY();
                        actor.setPosition(2.1474836E9f, 2.1474836E9f);
                    }
                    float stageX = inputEvent.getStageX() + DragAndDrop.this.k;
                    float stageY = inputEvent.getStageY() + DragAndDrop.this.l;
                    Actor hit = inputEvent.getStage().hit(stageX, stageY, true);
                    Actor actor2 = hit;
                    if (hit == null) {
                        actor2 = inputEvent.getStage().hit(stageX, stageY, false);
                    }
                    if (actor != null) {
                        actor.setPosition(f3, f4);
                    }
                    Target target = null;
                    DragAndDrop.this.g = false;
                    if (actor2 != null) {
                        int i2 = 0;
                        int i3 = DragAndDrop.this.h.size;
                        while (true) {
                            if (i2 >= i3) {
                                break;
                            }
                            Target target2 = DragAndDrop.this.h.get(i2);
                            if (!target2.f2717a.isAscendantOf(actor2)) {
                                i2++;
                            } else {
                                target = target2;
                                target2.f2717a.stageToLocalCoordinates(DragAndDrop.f2710a.set(stageX, stageY));
                                break;
                            }
                        }
                    }
                    if (target != DragAndDrop.this.f) {
                        if (DragAndDrop.this.f != null) {
                            DragAndDrop.this.f.reset(source, DragAndDrop.this.c);
                        }
                        DragAndDrop.this.f = target;
                    }
                    if (target != null) {
                        DragAndDrop.this.g = target.drag(source, DragAndDrop.this.c, DragAndDrop.f2710a.x, DragAndDrop.f2710a.y, i);
                    }
                    Actor actor3 = null;
                    if (DragAndDrop.this.f != null) {
                        actor3 = DragAndDrop.this.g ? DragAndDrop.this.c.f2715b : DragAndDrop.this.c.c;
                    }
                    if (actor3 == null) {
                        actor3 = DragAndDrop.this.c.f2714a;
                    }
                    if (actor3 != actor) {
                        if (actor != null && DragAndDrop.this.e) {
                            actor.remove();
                        }
                        DragAndDrop.this.d = actor3;
                        DragAndDrop.this.e = actor3.getStage() == null;
                        if (DragAndDrop.this.e) {
                            stage.addActor(actor3);
                        }
                    }
                    if (actor3 == null) {
                        return;
                    }
                    float stageX2 = (inputEvent.getStageX() - actor3.getWidth()) + DragAndDrop.this.i;
                    float stageY2 = inputEvent.getStageY() + DragAndDrop.this.j;
                    if (DragAndDrop.this.q) {
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

            @Override // com.prineside.tdi2.scene2d.utils.DragListener
            public void dragStop(InputEvent inputEvent, float f, float f2, int i) {
                if (i != DragAndDrop.this.o) {
                    return;
                }
                DragAndDrop.this.o = -1;
                if (DragAndDrop.this.c == null) {
                    return;
                }
                if (System.currentTimeMillis() < DragAndDrop.this.m) {
                    DragAndDrop.this.g = false;
                } else if (!DragAndDrop.this.g && DragAndDrop.this.f != null) {
                    DragAndDrop.this.f.f2717a.stageToLocalCoordinates(DragAndDrop.f2710a.set(inputEvent.getStageX() + DragAndDrop.this.k, inputEvent.getStageY() + DragAndDrop.this.l));
                    DragAndDrop.this.g = DragAndDrop.this.f.drag(source, DragAndDrop.this.c, DragAndDrop.f2710a.x, DragAndDrop.f2710a.y, i);
                }
                if (DragAndDrop.this.d != null && DragAndDrop.this.e) {
                    DragAndDrop.this.d.remove();
                }
                if (DragAndDrop.this.g) {
                    DragAndDrop.this.f.f2717a.stageToLocalCoordinates(DragAndDrop.f2710a.set(inputEvent.getStageX() + DragAndDrop.this.k, inputEvent.getStageY() + DragAndDrop.this.l));
                    DragAndDrop.this.f.drop(source, DragAndDrop.this.c, DragAndDrop.f2710a.x, DragAndDrop.f2710a.y, i);
                }
                source.dragStop(inputEvent, f, f2, i, DragAndDrop.this.c, DragAndDrop.this.g ? DragAndDrop.this.f : null);
                if (DragAndDrop.this.f != null) {
                    DragAndDrop.this.f.reset(source, DragAndDrop.this.c);
                }
                DragAndDrop.this.f2711b = null;
                DragAndDrop.this.c = null;
                DragAndDrop.this.f = null;
                DragAndDrop.this.g = false;
                DragAndDrop.this.d = null;
            }
        };
        dragListener.setTapSquareSize(this.s);
        dragListener.setButton(this.t);
        source.f2716a.addCaptureListener(dragListener);
        this.r.put(source, dragListener);
    }

    public void removeSource(Source source) {
        source.f2716a.removeCaptureListener(this.r.remove(source));
    }

    public void addTarget(Target target) {
        this.h.add(target);
    }

    public void removeTarget(Target target) {
        this.h.removeValue(target, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void clear() {
        this.h.clear();
        ObjectMap.Entries<Source, DragListener> it = this.r.entries().iterator();
        while (it.hasNext()) {
            ObjectMap.Entry next = it.next();
            ((Source) next.key).f2716a.removeCaptureListener((EventListener) next.value);
        }
        this.r.clear(8);
    }

    public void cancelTouchFocusExcept(Source source) {
        Stage stage;
        DragListener dragListener = this.r.get(source);
        if (dragListener != null && (stage = source.getActor().getStage()) != null) {
            stage.cancelTouchFocusExcept(dragListener, source.getActor());
        }
    }

    public void setTapSquareSize(float f) {
        this.s = f;
    }

    public void setButton(int i) {
        this.t = i;
    }

    public void setDragActorPosition(float f, float f2) {
        this.i = f;
        this.j = f2;
    }

    public void setTouchOffset(float f, float f2) {
        this.k = f;
        this.l = f2;
    }

    public boolean isDragging() {
        return this.c != null;
    }

    @Null
    public Actor getDragActor() {
        return this.d;
    }

    @Null
    public Payload getDragPayload() {
        return this.c;
    }

    @Null
    public Source getDragSource() {
        return this.f2711b;
    }

    public void setDragTime(int i) {
        this.n = i;
    }

    public int getDragTime() {
        return this.n;
    }

    public boolean isDragValid() {
        return this.c != null && System.currentTimeMillis() >= this.m;
    }

    public void setCancelTouchFocus(boolean z) {
        this.p = z;
    }

    public void setKeepWithinStage(boolean z) {
        this.q = z;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/utils/DragAndDrop$Source.class */
    public static abstract class Source {

        /* renamed from: a, reason: collision with root package name */
        final Actor f2716a;

        @Null
        public abstract Payload dragStart(InputEvent inputEvent, float f, float f2, int i);

        public Source(Actor actor) {
            if (actor == null) {
                throw new IllegalArgumentException("actor cannot be null.");
            }
            this.f2716a = actor;
        }

        public void drag(InputEvent inputEvent, float f, float f2, int i) {
        }

        public void dragStop(InputEvent inputEvent, float f, float f2, int i, @Null Payload payload, @Null Target target) {
        }

        public Actor getActor() {
            return this.f2716a;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/utils/DragAndDrop$Target.class */
    public static abstract class Target {

        /* renamed from: a, reason: collision with root package name */
        final Actor f2717a;

        public abstract boolean drag(Source source, Payload payload, float f, float f2, int i);

        public abstract void drop(Source source, Payload payload, float f, float f2, int i);

        public Target(Actor actor) {
            if (actor == null) {
                throw new IllegalArgumentException("actor cannot be null.");
            }
            this.f2717a = actor;
            Stage stage = actor.getStage();
            if (stage != null && actor == stage.getRoot()) {
                throw new IllegalArgumentException("The stage root cannot be a drag and drop target.");
            }
        }

        public void reset(Source source, Payload payload) {
        }

        public Actor getActor() {
            return this.f2717a;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/utils/DragAndDrop$Payload.class */
    public static class Payload {

        /* renamed from: a, reason: collision with root package name */
        @Null
        Actor f2714a;

        /* renamed from: b, reason: collision with root package name */
        @Null
        Actor f2715b;

        @Null
        Actor c;

        @Null
        private Object d;

        public void setDragActor(@Null Actor actor) {
            this.f2714a = actor;
        }

        @Null
        public Actor getDragActor() {
            return this.f2714a;
        }

        public void setValidDragActor(@Null Actor actor) {
            this.f2715b = actor;
        }

        @Null
        public Actor getValidDragActor() {
            return this.f2715b;
        }

        public void setInvalidDragActor(@Null Actor actor) {
            this.c = actor;
        }

        @Null
        public Actor getInvalidDragActor() {
            return this.c;
        }

        @Null
        public Object getObject() {
            return this.d;
        }

        public void setObject(@Null Object obj) {
            this.d = obj;
        }
    }
}
