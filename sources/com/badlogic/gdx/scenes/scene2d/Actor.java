package com.badlogic.gdx.scenes.scene2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.reflect.ClassReflection;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/Actor.class */
public class Actor {

    @Null
    private Stage stage;

    @Null
    Group parent;

    @Null
    private String name;
    private boolean debug;
    float x;
    float y;
    float width;
    float height;
    float originX;
    float originY;
    float rotation;

    @Null
    private Object userObject;
    private final DelayedRemovalArray<EventListener> listeners = new DelayedRemovalArray<>(0);
    private final DelayedRemovalArray<EventListener> captureListeners = new DelayedRemovalArray<>(0);
    private final Array<Action> actions = new Array<>(0);
    private Touchable touchable = Touchable.enabled;
    private boolean visible = true;
    float scaleX = 1.0f;
    float scaleY = 1.0f;
    final Color color = new Color(1.0f, 1.0f, 1.0f, 1.0f);

    public void draw(Batch batch, float f) {
    }

    public void act(float f) {
        Array<Action> array = this.actions;
        if (array.size == 0) {
            return;
        }
        if (this.stage != null && this.stage.getActionsRequestRendering()) {
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
        Group group = this.parent;
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
            group = group2.parent;
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
        DelayedRemovalArray<EventListener> delayedRemovalArray = z ? this.captureListeners : this.listeners;
        DelayedRemovalArray<EventListener> delayedRemovalArray2 = delayedRemovalArray;
        if (delayedRemovalArray.size == 0) {
            return event.isCancelled();
        }
        event.setListenerActor(this);
        event.setCapture(z);
        if (event.getStage() == null) {
            event.setStage(this.stage);
        }
        try {
            delayedRemovalArray2.begin();
            int i = delayedRemovalArray2.size;
            for (int i2 = 0; i2 < i; i2++) {
                if (delayedRemovalArray2.get(i2).handle(event)) {
                    event.handle();
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
        if ((!z || this.touchable == Touchable.enabled) && isVisible() && f >= 0.0f && f < this.width && f2 >= 0.0f && f2 < this.height) {
            return this;
        }
        return null;
    }

    public boolean remove() {
        if (this.parent != null) {
            return this.parent.removeActor(this, true);
        }
        return false;
    }

    public boolean addListener(EventListener eventListener) {
        if (eventListener == null) {
            throw new IllegalArgumentException("listener cannot be null.");
        }
        if (!this.listeners.contains(eventListener, true)) {
            this.listeners.add(eventListener);
            return true;
        }
        return false;
    }

    public boolean removeListener(EventListener eventListener) {
        if (eventListener == null) {
            throw new IllegalArgumentException("listener cannot be null.");
        }
        return this.listeners.removeValue(eventListener, true);
    }

    public DelayedRemovalArray<EventListener> getListeners() {
        return this.listeners;
    }

    public boolean addCaptureListener(EventListener eventListener) {
        if (eventListener == null) {
            throw new IllegalArgumentException("listener cannot be null.");
        }
        if (!this.captureListeners.contains(eventListener, true)) {
            this.captureListeners.add(eventListener);
            return true;
        }
        return true;
    }

    public boolean removeCaptureListener(EventListener eventListener) {
        if (eventListener == null) {
            throw new IllegalArgumentException("listener cannot be null.");
        }
        return this.captureListeners.removeValue(eventListener, true);
    }

    public DelayedRemovalArray<EventListener> getCaptureListeners() {
        return this.captureListeners;
    }

    public void addAction(Action action) {
        action.setActor(this);
        this.actions.add(action);
        if (this.stage == null || !this.stage.getActionsRequestRendering()) {
            return;
        }
        Gdx.graphics.requestRendering();
    }

    public void removeAction(@Null Action action) {
        if (action == null || !this.actions.removeValue(action, true)) {
            return;
        }
        action.setActor(null);
    }

    public Array<Action> getActions() {
        return this.actions;
    }

    public boolean hasActions() {
        return this.actions.size > 0;
    }

    public void clearActions() {
        for (int i = this.actions.size - 1; i >= 0; i--) {
            this.actions.get(i).setActor(null);
        }
        this.actions.clear();
    }

    public void clearListeners() {
        this.listeners.clear();
        this.captureListeners.clear();
    }

    public void clear() {
        clearActions();
        clearListeners();
    }

    @Null
    public Stage getStage() {
        return this.stage;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public boolean isDescendantOf(Actor actor) {
        if (actor == null) {
            throw new IllegalArgumentException("actor cannot be null.");
        }
        Actor actor2 = this;
        while (actor2 != actor) {
            Group group = actor2.parent;
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
            Group group = actor.parent;
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
            Group group = actor.parent;
            actor = group;
            if (group == null) {
                return null;
            }
        }
        return (T) actor;
    }

    public boolean hasParent() {
        return this.parent != null;
    }

    @Null
    public Group getParent() {
        return this.parent;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setParent(@Null Group group) {
        this.parent = group;
    }

    public boolean isTouchable() {
        return this.touchable == Touchable.enabled;
    }

    public Touchable getTouchable() {
        return this.touchable;
    }

    public void setTouchable(Touchable touchable) {
        this.touchable = touchable;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public void setVisible(boolean z) {
        this.visible = z;
    }

    public boolean ascendantsVisible() {
        Actor actor = this;
        while (actor.isVisible()) {
            Group group = actor.parent;
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
        int i = stage.touchFocuses.size;
        for (int i2 = 0; i2 < i; i2++) {
            if (stage.touchFocuses.get(i2).target == this) {
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
        int i = stage.touchFocuses.size;
        for (int i2 = 0; i2 < i; i2++) {
            if (stage.touchFocuses.get(i2).listenerActor == this) {
                return true;
            }
        }
        return false;
    }

    @Null
    public Object getUserObject() {
        return this.userObject;
    }

    public void setUserObject(@Null Object obj) {
        this.userObject = obj;
    }

    public float getX() {
        return this.x;
    }

    public float getX(int i) {
        float f = this.x;
        if ((i & 16) != 0) {
            f += this.width;
        } else if ((i & 8) == 0) {
            f += this.width / 2.0f;
        }
        return f;
    }

    public void setX(float f) {
        if (this.x != f) {
            this.x = f;
            positionChanged();
        }
    }

    public void setX(float f, int i) {
        if ((i & 16) != 0) {
            f -= this.width;
        } else if ((i & 8) == 0) {
            f -= this.width / 2.0f;
        }
        if (this.x != f) {
            this.x = f;
            positionChanged();
        }
    }

    public float getY() {
        return this.y;
    }

    public void setY(float f) {
        if (this.y != f) {
            this.y = f;
            positionChanged();
        }
    }

    public void setY(float f, int i) {
        if ((i & 2) != 0) {
            f -= this.height;
        } else if ((i & 4) == 0) {
            f -= this.height / 2.0f;
        }
        if (this.y != f) {
            this.y = f;
            positionChanged();
        }
    }

    public float getY(int i) {
        float f = this.y;
        if ((i & 2) != 0) {
            f += this.height;
        } else if ((i & 4) == 0) {
            f += this.height / 2.0f;
        }
        return f;
    }

    public void setPosition(float f, float f2) {
        if (this.x != f || this.y != f2) {
            this.x = f;
            this.y = f2;
            positionChanged();
        }
    }

    public void setPosition(float f, float f2, int i) {
        if ((i & 16) != 0) {
            f -= this.width;
        } else if ((i & 8) == 0) {
            f -= this.width / 2.0f;
        }
        if ((i & 2) != 0) {
            f2 -= this.height;
        } else if ((i & 4) == 0) {
            f2 -= this.height / 2.0f;
        }
        if (this.x != f || this.y != f2) {
            this.x = f;
            this.y = f2;
            positionChanged();
        }
    }

    public void moveBy(float f, float f2) {
        if (f != 0.0f || f2 != 0.0f) {
            this.x += f;
            this.y += f2;
            positionChanged();
        }
    }

    public float getWidth() {
        return this.width;
    }

    public void setWidth(float f) {
        if (this.width != f) {
            this.width = f;
            sizeChanged();
        }
    }

    public float getHeight() {
        return this.height;
    }

    public void setHeight(float f) {
        if (this.height != f) {
            this.height = f;
            sizeChanged();
        }
    }

    public float getTop() {
        return this.y + this.height;
    }

    public float getRight() {
        return this.x + this.width;
    }

    protected void positionChanged() {
    }

    protected void sizeChanged() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void scaleChanged() {
    }

    protected void rotationChanged() {
    }

    public void setSize(float f, float f2) {
        if (this.width != f || this.height != f2) {
            this.width = f;
            this.height = f2;
            sizeChanged();
        }
    }

    public void sizeBy(float f) {
        if (f != 0.0f) {
            this.width += f;
            this.height += f;
            sizeChanged();
        }
    }

    public void sizeBy(float f, float f2) {
        if (f != 0.0f || f2 != 0.0f) {
            this.width += f;
            this.height += f2;
            sizeChanged();
        }
    }

    public void setBounds(float f, float f2, float f3, float f4) {
        if (this.x != f || this.y != f2) {
            this.x = f;
            this.y = f2;
            positionChanged();
        }
        if (this.width != f3 || this.height != f4) {
            this.width = f3;
            this.height = f4;
            sizeChanged();
        }
    }

    public float getOriginX() {
        return this.originX;
    }

    public void setOriginX(float f) {
        this.originX = f;
    }

    public float getOriginY() {
        return this.originY;
    }

    public void setOriginY(float f) {
        this.originY = f;
    }

    public void setOrigin(float f, float f2) {
        this.originX = f;
        this.originY = f2;
    }

    public void setOrigin(int i) {
        if ((i & 8) != 0) {
            this.originX = 0.0f;
        } else if ((i & 16) != 0) {
            this.originX = this.width;
        } else {
            this.originX = this.width / 2.0f;
        }
        if ((i & 4) != 0) {
            this.originY = 0.0f;
        } else if ((i & 2) != 0) {
            this.originY = this.height;
        } else {
            this.originY = this.height / 2.0f;
        }
    }

    public float getScaleX() {
        return this.scaleX;
    }

    public void setScaleX(float f) {
        if (this.scaleX != f) {
            this.scaleX = f;
            scaleChanged();
        }
    }

    public float getScaleY() {
        return this.scaleY;
    }

    public void setScaleY(float f) {
        if (this.scaleY != f) {
            this.scaleY = f;
            scaleChanged();
        }
    }

    public void setScale(float f) {
        if (this.scaleX != f || this.scaleY != f) {
            this.scaleX = f;
            this.scaleY = f;
            scaleChanged();
        }
    }

    public void setScale(float f, float f2) {
        if (this.scaleX != f || this.scaleY != f2) {
            this.scaleX = f;
            this.scaleY = f2;
            scaleChanged();
        }
    }

    public void scaleBy(float f) {
        if (f != 0.0f) {
            this.scaleX += f;
            this.scaleY += f;
            scaleChanged();
        }
    }

    public void scaleBy(float f, float f2) {
        if (f != 0.0f || f2 != 0.0f) {
            this.scaleX += f;
            this.scaleY += f2;
            scaleChanged();
        }
    }

    public float getRotation() {
        return this.rotation;
    }

    public void setRotation(float f) {
        if (this.rotation != f) {
            this.rotation = f;
            rotationChanged();
        }
    }

    public void rotateBy(float f) {
        if (f != 0.0f) {
            this.rotation = (this.rotation + f) % 360.0f;
            rotationChanged();
        }
    }

    public void setColor(Color color) {
        this.color.set(color);
    }

    public void setColor(float f, float f2, float f3, float f4) {
        this.color.set(f, f2, f3, f4);
    }

    public Color getColor() {
        return this.color;
    }

    @Null
    public String getName() {
        return this.name;
    }

    public void setName(@Null String str) {
        this.name = str;
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
        Group group = this.parent;
        if (group == null) {
            return false;
        }
        SnapshotArray<Actor> snapshotArray = group.children;
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
        Group group = this.parent;
        if (group == null) {
            return -1;
        }
        return group.children.indexOf(this, true);
    }

    public boolean clipBegin() {
        return clipBegin(this.x, this.y, this.width, this.height);
    }

    public boolean clipBegin(float f, float f2, float f3, float f4) {
        Stage stage;
        if (f3 <= 0.0f || f4 <= 0.0f || (stage = this.stage) == null) {
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
        Stage stage = this.stage;
        return stage == null ? vector2 : stageToLocalCoordinates(stage.screenToStageCoordinates(vector2));
    }

    public Vector2 stageToLocalCoordinates(Vector2 vector2) {
        if (this.parent != null) {
            this.parent.stageToLocalCoordinates(vector2);
        }
        parentToLocalCoordinates(vector2);
        return vector2;
    }

    public Vector2 parentToLocalCoordinates(Vector2 vector2) {
        float f = this.rotation;
        float f2 = this.scaleX;
        float f3 = this.scaleY;
        float f4 = this.x;
        float f5 = this.y;
        if (f != 0.0f) {
            float cos = (float) Math.cos(f * 0.017453292f);
            float sin = (float) Math.sin(f * 0.017453292f);
            float f6 = this.originX;
            float f7 = this.originY;
            float f8 = (vector2.x - f4) - f6;
            float f9 = (vector2.y - f5) - f7;
            vector2.x = (((f8 * cos) + (f9 * sin)) / f2) + f6;
            vector2.y = (((f8 * (-sin)) + (f9 * cos)) / f3) + f7;
        } else if (f2 == 1.0f && f3 == 1.0f) {
            vector2.x -= f4;
            vector2.y -= f5;
        } else {
            float f10 = this.originX;
            float f11 = this.originY;
            vector2.x = (((vector2.x - f4) - f10) / f2) + f10;
            vector2.y = (((vector2.y - f5) - f11) / f3) + f11;
        }
        return vector2;
    }

    public Vector2 localToScreenCoordinates(Vector2 vector2) {
        Stage stage = this.stage;
        return stage == null ? vector2 : stage.stageToScreenCoordinates(localToAscendantCoordinates(null, vector2));
    }

    public Vector2 localToStageCoordinates(Vector2 vector2) {
        return localToAscendantCoordinates(null, vector2);
    }

    public Vector2 localToParentCoordinates(Vector2 vector2) {
        float f = -this.rotation;
        float f2 = this.scaleX;
        float f3 = this.scaleY;
        float f4 = this.x;
        float f5 = this.y;
        if (f != 0.0f) {
            float cos = (float) Math.cos(f * 0.017453292f);
            float sin = (float) Math.sin(f * 0.017453292f);
            float f6 = this.originX;
            float f7 = this.originY;
            float f8 = (vector2.x - f6) * f2;
            float f9 = (vector2.y - f7) * f3;
            vector2.x = (f8 * cos) + (f9 * sin) + f6 + f4;
            vector2.y = (f8 * (-sin)) + (f9 * cos) + f7 + f5;
        } else if (f2 == 1.0f && f3 == 1.0f) {
            vector2.x += f4;
            vector2.y += f5;
        } else {
            float f10 = this.originX;
            float f11 = this.originY;
            vector2.x = ((vector2.x - f10) * f2) + f10 + f4;
            vector2.y = ((vector2.y - f11) * f3) + f11 + f5;
        }
        return vector2;
    }

    public Vector2 localToAscendantCoordinates(@Null Actor actor, Vector2 vector2) {
        Actor actor2 = this;
        do {
            actor2.localToParentCoordinates(vector2);
            Group group = actor2.parent;
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
        drawDebugBounds(shapeRenderer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void drawDebugBounds(ShapeRenderer shapeRenderer) {
        if (this.debug) {
            shapeRenderer.set(ShapeRenderer.ShapeType.Line);
            if (this.stage != null) {
                shapeRenderer.setColor(this.stage.getDebugColor());
            }
            shapeRenderer.rect(this.x, this.y, this.originX, this.originY, this.width, this.height, this.scaleX, this.scaleY, this.rotation);
        }
    }

    public void setDebug(boolean z) {
        this.debug = z;
        if (z) {
            Stage.debug = true;
        }
    }

    public boolean getDebug() {
        return this.debug;
    }

    public Actor debug() {
        setDebug(true);
        return this;
    }

    public String toString() {
        String str = this.name;
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
