package com.prineside.tdi2.scene2d;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.prineside.tdi2.managers.RenderingManager;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.FocusListener;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/Stage.class */
public class Stage extends InputAdapter implements Disposable {

    /* renamed from: a, reason: collision with root package name */
    static boolean f2647a;
    private Viewport c;
    private final Batch d;
    private boolean e;
    private Group f;
    private final Vector2 g;
    private final Actor[] h;
    private final boolean[] i;
    private final int[] j;
    private final int[] k;
    private int l;
    private int m;

    @Null
    private Actor n;

    @Null
    private Actor o;

    @Null
    private Actor p;

    /* renamed from: b, reason: collision with root package name */
    final SnapshotArray<TouchFocus> f2648b;
    private boolean q;
    private ShapeRenderer r;
    private boolean s;
    private boolean t;
    private boolean u;
    private Table.Debug v;
    private final Color w;

    public Stage(Viewport viewport) {
        this(viewport, new SpriteBatch());
        this.e = true;
    }

    public Stage(Viewport viewport, Batch batch) {
        this.g = new Vector2();
        this.h = new Actor[20];
        this.i = new boolean[20];
        this.j = new int[20];
        this.k = new int[20];
        this.f2648b = new SnapshotArray<>(true, 4, TouchFocus.class);
        this.q = true;
        this.v = Table.Debug.none;
        this.w = new Color(0.0f, 1.0f, 0.0f, 0.85f);
        if (viewport == null) {
            throw new IllegalArgumentException("viewport cannot be null.");
        }
        if (batch == null) {
            throw new IllegalArgumentException("batch cannot be null.");
        }
        this.c = viewport;
        this.d = batch;
        this.f = new Group();
        this.f.a(this);
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
    }

    public void draw() {
        Camera camera = this.c.getCamera();
        camera.update();
        if (this.f.isVisible()) {
            Batch batch = this.d;
            batch.setProjectionMatrix(camera.combined);
            batch.begin();
            this.f.draw(batch, 1.0f);
            batch.end();
            if (f2647a) {
                a();
            }
        }
    }

    private void a() {
        if (this.r == null) {
            this.r = new ShapeRenderer(5000, RenderingManager.createDefaultShapeRendererShader());
            this.r.setAutoShapeType(true);
        }
        if (this.t || this.u || this.v != Table.Debug.none) {
            screenToStageCoordinates(this.g.set(Gdx.input.getX(), Gdx.input.getY()));
            Actor hit = hit(this.g.x, this.g.y, true);
            Actor actor = hit;
            if (hit == null) {
                return;
            }
            if (this.u && actor.f2639b != null) {
                actor = actor.f2639b;
            }
            if (this.v == Table.Debug.none) {
                actor.setDebug(true);
            } else {
                while (actor != null && !(actor instanceof Table)) {
                    actor = actor.f2639b;
                }
                if (actor == null) {
                    return;
                } else {
                    ((Table) actor).debug(this.v);
                }
            }
            if (this.s && (actor instanceof Group)) {
                ((Group) actor).debugAll();
            }
            a(this.f, actor);
        } else if (this.s) {
            this.f.debugAll();
        }
        Gdx.gl.glEnable(3042);
        this.r.setProjectionMatrix(this.c.getCamera().combined);
        this.r.begin();
        this.f.drawDebug(this.r);
        this.r.end();
        Gdx.gl.glDisable(3042);
    }

    private void a(Actor actor, Actor actor2) {
        if (actor == actor2) {
            return;
        }
        actor.setDebug(false);
        if (actor instanceof Group) {
            SnapshotArray<Actor> snapshotArray = ((Group) actor).j;
            int i = snapshotArray.size;
            for (int i2 = 0; i2 < i; i2++) {
                a(snapshotArray.get(i2), actor2);
            }
        }
    }

    public void act() {
        act(Math.min(Gdx.graphics.getDeltaTime(), 0.033333335f));
    }

    public void act(float f) {
        int length = this.h.length;
        for (int i = 0; i < length; i++) {
            Actor actor = this.h[i];
            if (this.i[i]) {
                this.h[i] = a(actor, this.j[i], this.k[i], i);
            } else if (actor != null) {
                this.h[i] = null;
                b(actor, this.j[i], this.k[i], i);
            }
        }
        Application.ApplicationType type = Gdx.app.getType();
        if (type == Application.ApplicationType.Desktop || type == Application.ApplicationType.Applet || type == Application.ApplicationType.WebGL) {
            this.n = a(this.n, this.l, this.m, -1);
        }
        this.f.act(f);
    }

    @Null
    private Actor a(@Null Actor actor, int i, int i2, int i3) {
        screenToStageCoordinates(this.g.set(i, i2));
        Actor hit = hit(this.g.x, this.g.y, true);
        if (hit == actor) {
            return actor;
        }
        if (actor != null) {
            InputEvent inputEvent = (InputEvent) Pools.obtain(InputEvent.class);
            inputEvent.setType(InputEvent.Type.exit);
            inputEvent.setStage(this);
            inputEvent.setStageX(this.g.x);
            inputEvent.setStageY(this.g.y);
            inputEvent.setPointer(i3);
            inputEvent.setRelatedActor(hit);
            actor.fire(inputEvent);
            Pools.free(inputEvent);
        }
        if (hit != null) {
            InputEvent inputEvent2 = (InputEvent) Pools.obtain(InputEvent.class);
            inputEvent2.setType(InputEvent.Type.enter);
            inputEvent2.setStage(this);
            inputEvent2.setStageX(this.g.x);
            inputEvent2.setStageY(this.g.y);
            inputEvent2.setPointer(i3);
            inputEvent2.setRelatedActor(actor);
            hit.fire(inputEvent2);
            Pools.free(inputEvent2);
        }
        return hit;
    }

    private void b(Actor actor, int i, int i2, int i3) {
        screenToStageCoordinates(this.g.set(i, i2));
        InputEvent inputEvent = (InputEvent) Pools.obtain(InputEvent.class);
        inputEvent.setType(InputEvent.Type.exit);
        inputEvent.setStage(this);
        inputEvent.setStageX(this.g.x);
        inputEvent.setStageY(this.g.y);
        inputEvent.setPointer(i3);
        inputEvent.setRelatedActor(actor);
        actor.fire(inputEvent);
        Pools.free(inputEvent);
    }

    @Override // com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
    public boolean touchDown(int i, int i2, int i3, int i4) {
        if (!a(i, i2)) {
            return false;
        }
        this.i[i3] = true;
        this.j[i3] = i;
        this.k[i3] = i2;
        screenToStageCoordinates(this.g.set(i, i2));
        InputEvent inputEvent = (InputEvent) Pools.obtain(InputEvent.class);
        inputEvent.setType(InputEvent.Type.touchDown);
        inputEvent.setStage(this);
        inputEvent.setStageX(this.g.x);
        inputEvent.setStageY(this.g.y);
        inputEvent.setPointer(i3);
        inputEvent.setButton(i4);
        Actor hit = hit(this.g.x, this.g.y, true);
        if (hit == null) {
            if (this.f.getTouchable() == Touchable.enabled) {
                this.f.fire(inputEvent);
            }
        } else {
            hit.fire(inputEvent);
        }
        boolean isHandled = inputEvent.isHandled();
        Pools.free(inputEvent);
        return isHandled;
    }

    @Override // com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
    public boolean touchDragged(int i, int i2, int i3) {
        this.j[i3] = i;
        this.k[i3] = i2;
        this.l = i;
        this.m = i2;
        if (this.f2648b.size == 0) {
            return false;
        }
        screenToStageCoordinates(this.g.set(i, i2));
        InputEvent inputEvent = (InputEvent) Pools.obtain(InputEvent.class);
        inputEvent.setType(InputEvent.Type.touchDragged);
        inputEvent.setStage(this);
        inputEvent.setStageX(this.g.x);
        inputEvent.setStageY(this.g.y);
        inputEvent.setPointer(i3);
        SnapshotArray<TouchFocus> snapshotArray = this.f2648b;
        TouchFocus[] begin = snapshotArray.begin();
        int i4 = snapshotArray.size;
        for (int i5 = 0; i5 < i4; i5++) {
            TouchFocus touchFocus = begin[i5];
            if (touchFocus.d == i3 && snapshotArray.contains(touchFocus, true)) {
                inputEvent.setTarget(touchFocus.c);
                inputEvent.setListenerActor(touchFocus.f2650b);
                if (touchFocus.f2649a.handle(inputEvent)) {
                    inputEvent.handle();
                }
            }
        }
        snapshotArray.end();
        boolean isHandled = inputEvent.isHandled();
        Pools.free(inputEvent);
        return isHandled;
    }

    @Override // com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
    public boolean touchUp(int i, int i2, int i3, int i4) {
        this.i[i3] = false;
        this.j[i3] = i;
        this.k[i3] = i2;
        if (this.f2648b.size == 0) {
            return false;
        }
        screenToStageCoordinates(this.g.set(i, i2));
        InputEvent inputEvent = (InputEvent) Pools.obtain(InputEvent.class);
        inputEvent.setType(InputEvent.Type.touchUp);
        inputEvent.setStage(this);
        inputEvent.setStageX(this.g.x);
        inputEvent.setStageY(this.g.y);
        inputEvent.setPointer(i3);
        inputEvent.setButton(i4);
        SnapshotArray<TouchFocus> snapshotArray = this.f2648b;
        TouchFocus[] begin = snapshotArray.begin();
        int i5 = snapshotArray.size;
        for (int i6 = 0; i6 < i5; i6++) {
            TouchFocus touchFocus = begin[i6];
            if (touchFocus.d == i3 && touchFocus.e == i4 && snapshotArray.removeValue(touchFocus, true)) {
                inputEvent.setTarget(touchFocus.c);
                inputEvent.setListenerActor(touchFocus.f2650b);
                if (touchFocus.f2649a.handle(inputEvent)) {
                    inputEvent.handle();
                }
                Pools.free(touchFocus);
            }
        }
        snapshotArray.end();
        boolean isHandled = inputEvent.isHandled();
        Pools.free(inputEvent);
        return isHandled;
    }

    @Override // com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
    public boolean touchCancelled(int i, int i2, int i3, int i4) {
        cancelTouchFocus();
        return false;
    }

    @Override // com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
    public boolean mouseMoved(int i, int i2) {
        this.l = i;
        this.m = i2;
        if (!a(i, i2)) {
            return false;
        }
        screenToStageCoordinates(this.g.set(i, i2));
        InputEvent inputEvent = (InputEvent) Pools.obtain(InputEvent.class);
        inputEvent.setType(InputEvent.Type.mouseMoved);
        inputEvent.setStage(this);
        inputEvent.setStageX(this.g.x);
        inputEvent.setStageY(this.g.y);
        Actor hit = hit(this.g.x, this.g.y, true);
        Actor actor = hit;
        if (hit == null) {
            actor = this.f;
        }
        actor.fire(inputEvent);
        boolean isHandled = inputEvent.isHandled();
        Pools.free(inputEvent);
        return isHandled;
    }

    @Override // com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
    public boolean scrolled(float f, float f2) {
        Actor actor = this.p == null ? this.f : this.p;
        screenToStageCoordinates(this.g.set(this.l, this.m));
        InputEvent inputEvent = (InputEvent) Pools.obtain(InputEvent.class);
        inputEvent.setType(InputEvent.Type.scrolled);
        inputEvent.setStage(this);
        inputEvent.setStageX(this.g.x);
        inputEvent.setStageY(this.g.y);
        inputEvent.setScrollAmountX(f);
        inputEvent.setScrollAmountY(f2);
        actor.fire(inputEvent);
        boolean isHandled = inputEvent.isHandled();
        Pools.free(inputEvent);
        return isHandled;
    }

    @Override // com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
    public boolean keyDown(int i) {
        Actor actor = this.o == null ? this.f : this.o;
        InputEvent inputEvent = (InputEvent) Pools.obtain(InputEvent.class);
        inputEvent.setType(InputEvent.Type.keyDown);
        inputEvent.setStage(this);
        inputEvent.setKeyCode(i);
        actor.fire(inputEvent);
        boolean isHandled = inputEvent.isHandled();
        Pools.free(inputEvent);
        return isHandled;
    }

    @Override // com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
    public boolean keyUp(int i) {
        Actor actor = this.o == null ? this.f : this.o;
        InputEvent inputEvent = (InputEvent) Pools.obtain(InputEvent.class);
        inputEvent.setType(InputEvent.Type.keyUp);
        inputEvent.setStage(this);
        inputEvent.setKeyCode(i);
        actor.fire(inputEvent);
        boolean isHandled = inputEvent.isHandled();
        Pools.free(inputEvent);
        return isHandled;
    }

    @Override // com.badlogic.gdx.InputAdapter, com.badlogic.gdx.InputProcessor
    public boolean keyTyped(char c) {
        Actor actor = this.o == null ? this.f : this.o;
        InputEvent inputEvent = (InputEvent) Pools.obtain(InputEvent.class);
        inputEvent.setType(InputEvent.Type.keyTyped);
        inputEvent.setStage(this);
        inputEvent.setCharacter(c);
        actor.fire(inputEvent);
        boolean isHandled = inputEvent.isHandled();
        Pools.free(inputEvent);
        return isHandled;
    }

    public void addTouchFocus(EventListener eventListener, Actor actor, Actor actor2, int i, int i2) {
        TouchFocus touchFocus = (TouchFocus) Pools.obtain(TouchFocus.class);
        touchFocus.f2650b = actor;
        touchFocus.c = actor2;
        touchFocus.f2649a = eventListener;
        touchFocus.d = i;
        touchFocus.e = i2;
        this.f2648b.add(touchFocus);
    }

    public void removeTouchFocus(EventListener eventListener, Actor actor, Actor actor2, int i, int i2) {
        SnapshotArray<TouchFocus> snapshotArray = this.f2648b;
        for (int i3 = snapshotArray.size - 1; i3 >= 0; i3--) {
            TouchFocus touchFocus = snapshotArray.get(i3);
            if (touchFocus.f2649a == eventListener && touchFocus.f2650b == actor && touchFocus.c == actor2 && touchFocus.d == i && touchFocus.e == i2) {
                snapshotArray.removeIndex(i3);
                Pools.free(touchFocus);
            }
        }
    }

    public void cancelTouchFocus(Actor actor) {
        InputEvent inputEvent = null;
        SnapshotArray<TouchFocus> snapshotArray = this.f2648b;
        TouchFocus[] begin = snapshotArray.begin();
        int i = snapshotArray.size;
        for (int i2 = 0; i2 < i; i2++) {
            TouchFocus touchFocus = begin[i2];
            if (touchFocus.f2650b == actor && snapshotArray.removeValue(touchFocus, true)) {
                if (inputEvent == null) {
                    InputEvent inputEvent2 = (InputEvent) Pools.obtain(InputEvent.class);
                    inputEvent = inputEvent2;
                    inputEvent2.setType(InputEvent.Type.touchUp);
                    inputEvent.setStage(this);
                    inputEvent.setStageX(-2.1474836E9f);
                    inputEvent.setStageY(-2.1474836E9f);
                }
                inputEvent.setTarget(touchFocus.c);
                inputEvent.setListenerActor(touchFocus.f2650b);
                inputEvent.setPointer(touchFocus.d);
                inputEvent.setButton(touchFocus.e);
                touchFocus.f2649a.handle(inputEvent);
            }
        }
        snapshotArray.end();
        if (inputEvent != null) {
            Pools.free(inputEvent);
        }
    }

    public void cancelTouchFocus() {
        cancelTouchFocusExcept(null, null);
    }

    public void cancelTouchFocusExcept(@Null EventListener eventListener, @Null Actor actor) {
        InputEvent inputEvent = (InputEvent) Pools.obtain(InputEvent.class);
        inputEvent.setType(InputEvent.Type.touchUp);
        inputEvent.setStage(this);
        inputEvent.setStageX(-2.1474836E9f);
        inputEvent.setStageY(-2.1474836E9f);
        SnapshotArray<TouchFocus> snapshotArray = this.f2648b;
        TouchFocus[] begin = snapshotArray.begin();
        int i = snapshotArray.size;
        for (int i2 = 0; i2 < i; i2++) {
            TouchFocus touchFocus = begin[i2];
            if ((touchFocus.f2649a != eventListener || touchFocus.f2650b != actor) && snapshotArray.removeValue(touchFocus, true)) {
                inputEvent.setTarget(touchFocus.c);
                inputEvent.setListenerActor(touchFocus.f2650b);
                inputEvent.setPointer(touchFocus.d);
                inputEvent.setButton(touchFocus.e);
                touchFocus.f2649a.handle(inputEvent);
            }
        }
        snapshotArray.end();
        Pools.free(inputEvent);
    }

    public void addActor(Actor actor) {
        this.f.addActor(actor);
    }

    public void addAction(Action action) {
        this.f.addAction(action);
    }

    public Array<Actor> getActors() {
        return this.f.j;
    }

    public boolean addListener(EventListener eventListener) {
        return this.f.addListener(eventListener);
    }

    public boolean removeListener(EventListener eventListener) {
        return this.f.removeListener(eventListener);
    }

    public boolean addCaptureListener(EventListener eventListener) {
        return this.f.addCaptureListener(eventListener);
    }

    public boolean removeCaptureListener(EventListener eventListener) {
        return this.f.removeCaptureListener(eventListener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(Actor actor) {
        int length = this.h.length;
        for (int i = 0; i < length; i++) {
            if (actor == this.h[i]) {
                this.h[i] = null;
                b(actor, this.j[i], this.k[i], i);
            }
        }
        if (actor == this.n) {
            this.n = null;
            b(actor, this.l, this.m, -1);
        }
    }

    public void clear() {
        unfocusAll();
        this.f.clear();
    }

    public void unfocusAll() {
        setScrollFocus(null);
        setKeyboardFocus(null);
        cancelTouchFocus();
    }

    public void unfocus(Actor actor) {
        cancelTouchFocus(actor);
        if (this.p != null && this.p.isDescendantOf(actor)) {
            setScrollFocus(null);
        }
        if (this.o == null || !this.o.isDescendantOf(actor)) {
            return;
        }
        setKeyboardFocus(null);
    }

    public boolean setKeyboardFocus(@Null Actor actor) {
        if (this.o == actor) {
            return true;
        }
        FocusListener.FocusEvent focusEvent = (FocusListener.FocusEvent) Pools.obtain(FocusListener.FocusEvent.class);
        focusEvent.setStage(this);
        focusEvent.setType(FocusListener.FocusEvent.Type.keyboard);
        Actor actor2 = this.o;
        if (actor2 != null) {
            focusEvent.setFocused(false);
            focusEvent.setRelatedActor(actor);
            actor2.fire(focusEvent);
        }
        boolean z = !focusEvent.isCancelled();
        boolean z2 = z;
        if (z) {
            this.o = actor;
            if (actor != null) {
                focusEvent.setFocused(true);
                focusEvent.setRelatedActor(actor2);
                actor.fire(focusEvent);
                boolean z3 = !focusEvent.isCancelled();
                z2 = z3;
                if (!z3) {
                    this.o = actor2;
                }
            }
        }
        Pools.free(focusEvent);
        return z2;
    }

    @Null
    public Actor getKeyboardFocus() {
        return this.o;
    }

    public boolean setScrollFocus(@Null Actor actor) {
        if (this.p == actor) {
            return true;
        }
        FocusListener.FocusEvent focusEvent = (FocusListener.FocusEvent) Pools.obtain(FocusListener.FocusEvent.class);
        focusEvent.setStage(this);
        focusEvent.setType(FocusListener.FocusEvent.Type.scroll);
        Actor actor2 = this.p;
        if (actor2 != null) {
            focusEvent.setFocused(false);
            focusEvent.setRelatedActor(actor);
            actor2.fire(focusEvent);
        }
        boolean z = !focusEvent.isCancelled();
        boolean z2 = z;
        if (z) {
            this.p = actor;
            if (actor != null) {
                focusEvent.setFocused(true);
                focusEvent.setRelatedActor(actor2);
                actor.fire(focusEvent);
                boolean z3 = !focusEvent.isCancelled();
                z2 = z3;
                if (!z3) {
                    this.p = actor2;
                }
            }
        }
        Pools.free(focusEvent);
        return z2;
    }

    @Null
    public Actor getScrollFocus() {
        return this.p;
    }

    public Batch getBatch() {
        return this.d;
    }

    public Viewport getViewport() {
        return this.c;
    }

    public void setViewport(Viewport viewport) {
        this.c = viewport;
    }

    public float getWidth() {
        return this.c.getWorldWidth();
    }

    public float getHeight() {
        return this.c.getWorldHeight();
    }

    public Camera getCamera() {
        return this.c.getCamera();
    }

    public Group getRoot() {
        return this.f;
    }

    public void setRoot(Group group) {
        if (group.f2639b != null) {
            group.f2639b.removeActor(group, false);
        }
        this.f = group;
        group.a((Group) null);
        group.a(this);
    }

    @Null
    public Actor hit(float f, float f2, boolean z) {
        this.f.parentToLocalCoordinates(this.g.set(f, f2));
        return this.f.hit(this.g.x, this.g.y, z);
    }

    public Vector2 screenToStageCoordinates(Vector2 vector2) {
        this.c.unproject(vector2);
        return vector2;
    }

    public Vector2 stageToScreenCoordinates(Vector2 vector2) {
        this.c.project(vector2);
        vector2.y = Gdx.graphics.getHeight() - vector2.y;
        return vector2;
    }

    public Vector2 toScreenCoordinates(Vector2 vector2, Matrix4 matrix4) {
        return this.c.toScreenCoordinates(vector2, matrix4);
    }

    public void calculateScissors(Rectangle rectangle, Rectangle rectangle2) {
        Matrix4 transformMatrix;
        if (this.r != null && this.r.isDrawing()) {
            transformMatrix = this.r.getTransformMatrix();
        } else {
            transformMatrix = this.d.getTransformMatrix();
        }
        this.c.calculateScissors(transformMatrix, rectangle, rectangle2);
    }

    public void setActionsRequestRendering(boolean z) {
        this.q = z;
    }

    public boolean getActionsRequestRendering() {
        return this.q;
    }

    public Color getDebugColor() {
        return this.w;
    }

    public void setDebugInvisible(boolean z) {
    }

    public void setDebugAll(boolean z) {
        if (this.s == z) {
            return;
        }
        this.s = z;
        if (z) {
            f2647a = true;
        } else {
            this.f.setDebug(false, true);
        }
    }

    public boolean isDebugAll() {
        return this.s;
    }

    public void setDebugUnderMouse(boolean z) {
        if (this.t == z) {
            return;
        }
        this.t = z;
        if (z) {
            f2647a = true;
        } else {
            this.f.setDebug(false, true);
        }
    }

    public void setDebugParentUnderMouse(boolean z) {
        if (this.u == z) {
            return;
        }
        this.u = z;
        if (z) {
            f2647a = true;
        } else {
            this.f.setDebug(false, true);
        }
    }

    public void setDebugTableUnderMouse(@Null Table.Debug debug) {
        if (debug == null) {
            debug = Table.Debug.none;
        }
        if (this.v == debug) {
            return;
        }
        this.v = debug;
        if (debug != Table.Debug.none) {
            f2647a = true;
        } else {
            this.f.setDebug(false, true);
        }
    }

    public void setDebugTableUnderMouse(boolean z) {
        setDebugTableUnderMouse(z ? Table.Debug.all : Table.Debug.none);
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        clear();
        if (this.e) {
            this.d.dispose();
        }
        if (this.r != null) {
            this.r.dispose();
        }
    }

    private boolean a(int i, int i2) {
        int screenX = this.c.getScreenX();
        int screenWidth = screenX + this.c.getScreenWidth();
        int screenY = this.c.getScreenY();
        int screenHeight = screenY + this.c.getScreenHeight();
        int height = (Gdx.graphics.getHeight() - 1) - i2;
        return i >= screenX && i < screenWidth && height >= screenY && height < screenHeight;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/Stage$TouchFocus.class */
    public static final class TouchFocus implements Pool.Poolable {

        /* renamed from: a, reason: collision with root package name */
        EventListener f2649a;

        /* renamed from: b, reason: collision with root package name */
        Actor f2650b;
        Actor c;
        int d;
        int e;

        @Override // com.badlogic.gdx.utils.Pool.Poolable
        public final void reset() {
            this.f2650b = null;
            this.f2649a = null;
            this.c = null;
        }
    }
}
