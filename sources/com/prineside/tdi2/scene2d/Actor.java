package com.prineside.tdi2.scene2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.prineside.tdi2.scene2d.utils.ScissorStack;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/Actor.class */
public class Actor {

    /* renamed from: a, reason: collision with root package name */
    @Null
    protected Stage f2638a;

    /* renamed from: b, reason: collision with root package name */
    @Null
    protected Group f2639b;

    @Null
    private String m;
    private boolean o;
    private float p;
    private float q;
    private float r;
    private float s;
    protected float d;
    protected float e;
    protected float h;

    @Null
    private Object t;
    private final DelayedRemovalArray<EventListener> j = new DelayedRemovalArray<>(0);
    private DelayedRemovalArray<EventListener> k = new DelayedRemovalArray<>(0);
    private Array<Action> l = new Array<>(0);
    private Touchable n = Touchable.enabled;
    protected boolean c = true;
    protected float f = 1.0f;
    protected float g = 1.0f;
    protected final Color i = new Color(1.0f, 1.0f, 1.0f, 1.0f);

    public void draw(Batch batch, float f) {
    }

    public void act(float f) {
        Array<Action> array = this.l;
        if (array.size == 0) {
            return;
        }
        if (this.f2638a != null && this.f2638a.getActionsRequestRendering()) {
            Gdx.graphics.requestRendering();
        }
        int i = 0;
        while (i < array.size) {
            try {
                Action action = array.get(i);
                if (action.act(f) && i < array.size) {
                    int indexOf = array.get(i) == action ? i : array.indexOf(action, true);
                    int i2 = indexOf;
                    if (indexOf != -1) {
                        array.removeIndex(i2);
                        action.setActor(null);
                        i--;
                    }
                }
                i++;
            } catch (RuntimeException e) {
                String actor = toString();
                throw new RuntimeException("Actor: " + actor.substring(0, Math.min(actor.length(), 128)), e);
            }
        }
    }

    public boolean fire(Event event) {
        if (event.getStage() == null) {
            event.setStage(getStage());
        }
        event.setTarget(this);
        Array array = (Array) Pools.obtain(Array.class);
        Group group = this.f2639b;
        while (true) {
            Group group2 = group;
            if (group2 == null) {
                try {
                    break;
                } finally {
                    array.clear();
                    Pools.free(array);
                }
            }
            array.add(group2);
            group = group2.f2639b;
        }
        Object[] objArr = array.items;
        for (int i = array.size - 1; i >= 0; i--) {
            ((Group) objArr[i]).notify(event, true);
            if (event.isStopped()) {
                return event.isCancelled();
            }
        }
        notify(event, true);
        if (event.isStopped()) {
            return event.isCancelled();
        }
        notify(event, false);
        if (!event.getBubbles()) {
            return event.isCancelled();
        }
        if (event.isStopped()) {
            return event.isCancelled();
        }
        int i2 = array.size;
        for (int i3 = 0; i3 < i2; i3++) {
            ((Group) objArr[i3]).notify(event, false);
            if (event.isStopped()) {
                return event.isCancelled();
            }
        }
        return event.isCancelled();
    }

    public boolean notify(Event event, boolean z) {
        if (event.getTarget() == null) {
            throw new IllegalArgumentException("The event target cannot be null.");
        }
        DelayedRemovalArray<EventListener> delayedRemovalArray = z ? this.k : this.j;
        DelayedRemovalArray<EventListener> delayedRemovalArray2 = delayedRemovalArray;
        if (delayedRemovalArray.size == 0) {
            return event.isCancelled();
        }
        event.setListenerActor(this);
        event.setCapture(z);
        if (event.getStage() == null) {
            event.setStage(this.f2638a);
        }
        try {
            delayedRemovalArray2.begin();
            int i = delayedRemovalArray2.size;
            for (int i2 = 0; i2 < i; i2++) {
                if (delayedRemovalArray2.get(i2).handle(event)) {
                    event.handle();
                }
                if (event.isHalted()) {
                    break;
                }
            }
            delayedRemovalArray2.end();
            return event.isCancelled();
        } catch (RuntimeException e) {
            String actor = toString();
            throw new RuntimeException("Actor: " + actor.substring(0, Math.min(actor.length(), 128)), e);
        }
    }

    @Null
    public Actor hit(float f, float f2, boolean z) {
        if ((!z || this.n == Touchable.enabled) && isVisible() && f >= 0.0f && f < this.r && f2 >= 0.0f && f2 < this.s) {
            return this;
        }
        return null;
    }

    public boolean remove() {
        if (this.f2639b != null) {
            return this.f2639b.removeActor(this, true);
        }
        return false;
    }

    public boolean addListener(EventListener eventListener) {
        if (eventListener == null) {
            throw new IllegalArgumentException("listener cannot be null.");
        }
        if (!this.j.contains(eventListener, true)) {
            this.j.add(eventListener);
            return true;
        }
        return false;
    }

    public boolean removeListener(EventListener eventListener) {
        if (eventListener == null) {
            throw new IllegalArgumentException("listener cannot be null.");
        }
        return this.j.removeValue(eventListener, true);
    }

    public DelayedRemovalArray<EventListener> getListeners() {
        return this.j;
    }

    public boolean addCaptureListener(EventListener eventListener) {
        if (eventListener == null) {
            throw new IllegalArgumentException("listener cannot be null.");
        }
        if (!this.k.contains(eventListener, true)) {
            this.k.add(eventListener);
            return true;
        }
        return true;
    }

    public boolean removeCaptureListener(EventListener eventListener) {
        if (eventListener == null) {
            throw new IllegalArgumentException("listener cannot be null.");
        }
        return this.k.removeValue(eventListener, true);
    }

    public DelayedRemovalArray<EventListener> getCaptureListeners() {
        return this.k;
    }

    public void addAction(Action action) {
        action.setActor(this);
        this.l.add(action);
        if (this.f2638a == null || !this.f2638a.getActionsRequestRendering()) {
            return;
        }
        Gdx.graphics.requestRendering();
    }

    public void removeAction(@Null Action action) {
        if (action == null || !this.l.removeValue(action, true)) {
            return;
        }
        action.setActor(null);
    }

    public Array<Action> getActions() {
        return this.l;
    }

    public boolean hasActions() {
        return this.l.size > 0;
    }

    public void clearActions() {
        for (int i = this.l.size - 1; i >= 0; i--) {
            this.l.get(i).setActor(null);
        }
        this.l.clear();
    }

    public void clearListeners() {
        this.j.clear();
        this.k.clear();
    }

    public void clear() {
        clearActions();
        clearListeners();
    }

    @Null
    public Stage getStage() {
        return this.f2638a;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(Stage stage) {
        this.f2638a = stage;
    }

    public boolean isDescendantOf(Actor actor) {
        if (actor == null) {
            throw new IllegalArgumentException("actor cannot be null.");
        }
        Actor actor2 = this;
        while (actor2 != actor) {
            Group group = actor2.f2639b;
            actor2 = group;
            if (group == null) {
                return false;
            }
        }
        return true;
    }

    public boolean isAscendantOf(Actor actor) {
        if (actor == null) {
            throw new IllegalArgumentException("actor cannot be null.");
        }
        while (actor != this) {
            Group group = actor.f2639b;
            actor = group;
            if (group == null) {
                return false;
            }
        }
        return true;
    }

    @Null
    public <T extends Actor> T firstAscendant(Class<T> cls) {
        if (cls == null) {
            throw new IllegalArgumentException("actor cannot be null.");
        }
        Actor actor = this;
        while (!ClassReflection.isInstance(cls, actor)) {
            Group group = actor.f2639b;
            actor = group;
            if (group == null) {
                return null;
            }
        }
        return (T) actor;
    }

    public boolean hasParent() {
        return this.f2639b != null;
    }

    @Null
    public Group getParent() {
        return this.f2639b;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(@Null Group group) {
        this.f2639b = group;
    }

    public boolean isTouchable() {
        return this.n == Touchable.enabled;
    }

    public Touchable getTouchable() {
        return this.n;
    }

    public void setTouchable(Touchable touchable) {
        this.n = touchable;
    }

    public boolean isVisible() {
        return this.c;
    }

    public void setVisible(boolean z) {
        this.c = z;
    }

    public boolean ascendantsVisible() {
        Actor actor = this;
        while (actor.isVisible()) {
            Group group = actor.f2639b;
            actor = group;
            if (group == null) {
                return true;
            }
        }
        return false;
    }

    @Deprecated
    public boolean ancestorsVisible() {
        return ascendantsVisible();
    }

    public boolean hasKeyboardFocus() {
        Stage stage = getStage();
        return stage != null && stage.getKeyboardFocus() == this;
    }

    public boolean hasScrollFocus() {
        Stage stage = getStage();
        return stage != null && stage.getScrollFocus() == this;
    }

    public boolean isTouchFocusTarget() {
        Stage stage = getStage();
        if (stage == null) {
            return false;
        }
        int i = stage.f2648b.size;
        for (int i2 = 0; i2 < i; i2++) {
            if (stage.f2648b.get(i2).c == this) {
                return true;
            }
        }
        return false;
    }

    public boolean isTouchFocusListener() {
        Stage stage = getStage();
        if (stage == null) {
            return false;
        }
        int i = stage.f2648b.size;
        for (int i2 = 0; i2 < i; i2++) {
            if (stage.f2648b.get(i2).f2650b == this) {
                return true;
            }
        }
        return false;
    }

    @Null
    public Object getUserObject() {
        return this.t;
    }

    public void setUserObject(@Null Object obj) {
        this.t = obj;
    }

    public float getX() {
        return this.p;
    }

    public float getX(int i) {
        float f = this.p;
        if ((i & 16) != 0) {
            f += this.r;
        } else if ((i & 8) == 0) {
            f += this.r / 2.0f;
        }
        return f;
    }

    public void setX(float f) {
        if (this.p != f) {
            this.p = f;
            positionChanged();
        }
    }

    public void setX(float f, int i) {
        if ((i & 16) != 0) {
            f -= this.r;
        } else if ((i & 8) == 0) {
            f -= this.r / 2.0f;
        }
        if (this.p != f) {
            this.p = f;
            positionChanged();
        }
    }

    public float getY() {
        return this.q;
    }

    public void setY(float f) {
        if (this.q != f) {
            this.q = f;
            positionChanged();
        }
    }

    public void setY(float f, int i) {
        if ((i & 2) != 0) {
            f -= this.s;
        } else if ((i & 4) == 0) {
            f -= this.s / 2.0f;
        }
        if (this.q != f) {
            this.q = f;
            positionChanged();
        }
    }

    public float getY(int i) {
        float f = this.q;
        if ((i & 2) != 0) {
            f += this.s;
        } else if ((i & 4) == 0) {
            f += this.s / 2.0f;
        }
        return f;
    }

    public void setPosition(float f, float f2) {
        if (this.p != f || this.q != f2) {
            this.p = f;
            this.q = f2;
            positionChanged();
        }
    }

    public void setPosition(float f, float f2, int i) {
        if ((i & 16) != 0) {
            f -= this.r;
        } else if ((i & 8) == 0) {
            f -= this.r / 2.0f;
        }
        if ((i & 2) != 0) {
            f2 -= this.s;
        } else if ((i & 4) == 0) {
            f2 -= this.s / 2.0f;
        }
        if (this.p != f || this.q != f2) {
            this.p = f;
            this.q = f2;
            positionChanged();
        }
    }

    public void moveBy(float f, float f2) {
        if (f != 0.0f || f2 != 0.0f) {
            this.p += f;
            this.q += f2;
            positionChanged();
        }
    }

    public float getWidth() {
        return this.r;
    }

    public void setWidth(float f) {
        if (this.r != f) {
            this.r = f;
            sizeChanged();
        }
    }

    public float getHeight() {
        return this.s;
    }

    public void setHeight(float f) {
        if (this.s != f) {
            this.s = f;
            sizeChanged();
        }
    }

    public float getTop() {
        return this.q + this.s;
    }

    public float getRight() {
        return this.p + this.r;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void positionChanged() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void sizeChanged() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a() {
    }

    public void setSize(float f, float f2) {
        if (this.r != f || this.s != f2) {
            this.r = f;
            this.s = f2;
            sizeChanged();
        }
    }

    public void sizeBy(float f) {
        if (f != 0.0f) {
            this.r += f;
            this.s += f;
            sizeChanged();
        }
    }

    public void sizeBy(float f, float f2) {
        if (f != 0.0f || f2 != 0.0f) {
            this.r += f;
            this.s += f2;
            sizeChanged();
        }
    }

    public void setBounds(float f, float f2, float f3, float f4) {
        if (this.p != f || this.q != f2) {
            this.p = f;
            this.q = f2;
            positionChanged();
        }
        if (this.r != f3 || this.s != f4) {
            this.r = f3;
            this.s = f4;
            sizeChanged();
        }
    }

    public float getOriginX() {
        return this.d;
    }

    public void setOriginX(float f) {
        this.d = f;
    }

    public float getOriginY() {
        return this.e;
    }

    public void setOriginY(float f) {
        this.e = f;
    }

    public void setOrigin(float f, float f2) {
        this.d = f;
        this.e = f2;
    }

    public void setOrigin(int i) {
        if ((i & 8) != 0) {
            this.d = 0.0f;
        } else if ((i & 16) != 0) {
            this.d = this.r;
        } else {
            this.d = this.r / 2.0f;
        }
        if ((i & 4) != 0) {
            this.e = 0.0f;
        } else if ((i & 2) != 0) {
            this.e = this.s;
        } else {
            this.e = this.s / 2.0f;
        }
    }

    public float getScaleX() {
        return this.f;
    }

    public void setScaleX(float f) {
        if (this.f != f) {
            this.f = f;
            a();
        }
    }

    public float getScaleY() {
        return this.g;
    }

    public void setScaleY(float f) {
        if (this.g != f) {
            this.g = f;
            a();
        }
    }

    public void setScale(float f) {
        if (this.f != f || this.g != f) {
            this.f = f;
            this.g = f;
            a();
        }
    }

    public void setScale(float f, float f2) {
        if (this.f != f || this.g != f2) {
            this.f = f;
            this.g = f2;
            a();
        }
    }

    public void scaleBy(float f) {
        if (f != 0.0f) {
            this.f += f;
            this.g += f;
            a();
        }
    }

    public void scaleBy(float f, float f2) {
        if (f != 0.0f || f2 != 0.0f) {
            this.f += f;
            this.g += f2;
            a();
        }
    }

    public float getRotation() {
        return this.h;
    }

    public void setRotation(float f) {
        if (this.h != f) {
            this.h = f;
        }
    }

    public void rotateBy(float f) {
        if (f != 0.0f) {
            this.h = (this.h + f) % 360.0f;
        }
    }

    public void setColor(Color color) {
        this.i.set(color);
    }

    public void setColor(float f, float f2, float f3, float f4) {
        this.i.set(f, f2, f3, f4);
    }

    public Color getColor() {
        return this.i;
    }

    @Null
    public String getName() {
        return this.m;
    }

    public void setName(@Null String str) {
        this.m = str;
    }

    public void toFront() {
        setZIndex(Integer.MAX_VALUE);
    }

    public void toBack() {
        setZIndex(0);
    }

    public boolean setZIndex(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("ZIndex cannot be < 0.");
        }
        Group group = this.f2639b;
        if (group == null) {
            return false;
        }
        SnapshotArray<Actor> snapshotArray = group.j;
        if (snapshotArray.size <= 1) {
            return false;
        }
        int min = Math.min(i, snapshotArray.size - 1);
        if (snapshotArray.get(min) == this || !snapshotArray.removeValue(this, true)) {
            return false;
        }
        snapshotArray.insert(min, this);
        return true;
    }

    public int getZIndex() {
        Group group = this.f2639b;
        if (group == null) {
            return -1;
        }
        return group.j.indexOf(this, true);
    }

    public boolean clipBegin() {
        return clipBegin(this.p, this.q, this.r, this.s);
    }

    public boolean clipBegin(float f, float f2, float f3, float f4) {
        Stage stage;
        if (f3 <= 0.0f || f4 <= 0.0f || (stage = this.f2638a) == null) {
            return false;
        }
        Rectangle rectangle = Rectangle.tmp;
        rectangle.x = f;
        rectangle.y = f2;
        rectangle.width = f3;
        rectangle.height = f4;
        Rectangle rectangle2 = (Rectangle) Pools.obtain(Rectangle.class);
        stage.calculateScissors(rectangle, rectangle2);
        if (ScissorStack.pushScissors(rectangle2)) {
            return true;
        }
        Pools.free(rectangle2);
        return false;
    }

    public void clipEnd() {
        Pools.free(ScissorStack.popScissors());
    }

    public Vector2 screenToLocalCoordinates(Vector2 vector2) {
        Stage stage = this.f2638a;
        return stage == null ? vector2 : stageToLocalCoordinates(stage.screenToStageCoordinates(vector2));
    }

    public Vector2 stageToLocalCoordinates(Vector2 vector2) {
        if (this.f2639b != null) {
            this.f2639b.stageToLocalCoordinates(vector2);
        }
        parentToLocalCoordinates(vector2);
        return vector2;
    }

    public Vector2 parentToLocalCoordinates(Vector2 vector2) {
        float f = this.h;
        float f2 = this.f;
        float f3 = this.g;
        float f4 = this.p;
        float f5 = this.q;
        if (f != 0.0f) {
            float cos = (float) Math.cos(f * 0.017453292f);
            float sin = (float) Math.sin(f * 0.017453292f);
            float f6 = this.d;
            float f7 = this.e;
            float f8 = (vector2.x - f4) - f6;
            float f9 = (vector2.y - f5) - f7;
            vector2.x = (((f8 * cos) + (f9 * sin)) / f2) + f6;
            vector2.y = (((f8 * (-sin)) + (f9 * cos)) / f3) + f7;
        } else if (f2 == 1.0f && f3 == 1.0f) {
            vector2.x -= f4;
            vector2.y -= f5;
        } else {
            float f10 = this.d;
            float f11 = this.e;
            vector2.x = (((vector2.x - f4) - f10) / f2) + f10;
            vector2.y = (((vector2.y - f5) - f11) / f3) + f11;
        }
        return vector2;
    }

    public Vector2 localToScreenCoordinates(Vector2 vector2) {
        Stage stage = this.f2638a;
        return stage == null ? vector2 : stage.stageToScreenCoordinates(localToAscendantCoordinates(null, vector2));
    }

    public Vector2 localToStageCoordinates(Vector2 vector2) {
        return localToAscendantCoordinates(null, vector2);
    }

    public Vector2 localToParentCoordinates(Vector2 vector2) {
        float f = -this.h;
        float f2 = this.f;
        float f3 = this.g;
        float f4 = this.p;
        float f5 = this.q;
        if (f != 0.0f) {
            float cos = (float) Math.cos(f * 0.017453292f);
            float sin = (float) Math.sin(f * 0.017453292f);
            float f6 = this.d;
            float f7 = this.e;
            float f8 = (vector2.x - f6) * f2;
            float f9 = (vector2.y - f7) * f3;
            vector2.x = (f8 * cos) + (f9 * sin) + f6 + f4;
            vector2.y = (f8 * (-sin)) + (f9 * cos) + f7 + f5;
        } else if (f2 == 1.0f && f3 == 1.0f) {
            vector2.x += f4;
            vector2.y += f5;
        } else {
            float f10 = this.d;
            float f11 = this.e;
            vector2.x = ((vector2.x - f10) * f2) + f10 + f4;
            vector2.y = ((vector2.y - f11) * f3) + f11 + f5;
        }
        return vector2;
    }

    public Vector2 localToAscendantCoordinates(@Null Actor actor, Vector2 vector2) {
        Actor actor2 = this;
        do {
            actor2.localToParentCoordinates(vector2);
            Group group = actor2.f2639b;
            actor2 = group;
            if (group == actor) {
                return vector2;
            }
        } while (actor2 != null);
        throw new IllegalArgumentException("Actor is not an ascendant: " + actor);
    }

    public Vector2 localToActorCoordinates(Actor actor, Vector2 vector2) {
        localToStageCoordinates(vector2);
        return actor.stageToLocalCoordinates(vector2);
    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        a(shapeRenderer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(ShapeRenderer shapeRenderer) {
        if (this.o) {
            shapeRenderer.set(ShapeRenderer.ShapeType.Line);
            if (this.f2638a != null) {
                shapeRenderer.setColor(this.f2638a.getDebugColor());
            }
            shapeRenderer.rect(this.p, this.q, this.d, this.e, this.r, this.s, this.f, this.g, this.h);
        }
    }

    public void setDebug(boolean z) {
        this.o = z;
        if (z) {
            Stage.f2647a = true;
        }
    }

    public boolean getDebug() {
        return this.o;
    }

    public Actor debug() {
        setDebug(true);
        return this;
    }

    public String toString() {
        String str = this.m;
        String str2 = str;
        if (str == null) {
            String name = getClass().getName();
            str2 = name;
            int lastIndexOf = name.lastIndexOf(46);
            if (lastIndexOf != -1) {
                str2 = str2.substring(lastIndexOf + 1);
            }
        }
        return str2;
    }
}
