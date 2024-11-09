package com.badlogic.gdx.scenes.scene2d;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Cullable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.SnapshotArray;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/Group.class */
public class Group extends Actor implements Cullable {
    private static final Vector2 tmp = new Vector2();
    final SnapshotArray<Actor> children = new SnapshotArray<>(true, 4, Actor.class);
    private final Affine2 worldTransform = new Affine2();
    private final Matrix4 computedTransform = new Matrix4();
    private final Matrix4 oldTransform = new Matrix4();
    boolean transform = true;

    @Null
    private Rectangle cullingArea;

    @Override // com.badlogic.gdx.scenes.scene2d.Actor
    public void act(float f) {
        super.act(f);
        Actor[] begin = this.children.begin();
        int i = this.children.size;
        for (int i2 = 0; i2 < i; i2++) {
            begin[i2].act(f);
        }
        this.children.end();
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Actor
    public void draw(Batch batch, float f) {
        if (this.transform) {
            applyTransform(batch, computeTransform());
        }
        drawChildren(batch, f);
        if (this.transform) {
            resetTransform(batch);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void drawChildren(Batch batch, float f) {
        float f2 = f * this.color.f889a;
        SnapshotArray<Actor> snapshotArray = this.children;
        Actor[] begin = snapshotArray.begin();
        Rectangle rectangle = this.cullingArea;
        if (rectangle != null) {
            float f3 = rectangle.x;
            float f4 = f3 + rectangle.width;
            float f5 = rectangle.y;
            float f6 = f5 + rectangle.height;
            if (this.transform) {
                int i = snapshotArray.size;
                for (int i2 = 0; i2 < i; i2++) {
                    Actor actor = begin[i2];
                    if (actor.isVisible()) {
                        float f7 = actor.x;
                        float f8 = actor.y;
                        if (f7 <= f4 && f8 <= f6 && f7 + actor.width >= f3 && f8 + actor.height >= f5) {
                            actor.draw(batch, f2);
                        }
                    }
                }
            } else {
                float f9 = this.x;
                float f10 = this.y;
                this.x = 0.0f;
                this.y = 0.0f;
                int i3 = snapshotArray.size;
                for (int i4 = 0; i4 < i3; i4++) {
                    Actor actor2 = begin[i4];
                    if (actor2.isVisible()) {
                        float f11 = actor2.x;
                        float f12 = actor2.y;
                        if (f11 <= f4 && f12 <= f6 && f11 + actor2.width >= f3 && f12 + actor2.height >= f5) {
                            actor2.x = f11 + f9;
                            actor2.y = f12 + f10;
                            actor2.draw(batch, f2);
                            actor2.x = f11;
                            actor2.y = f12;
                        }
                    }
                }
                this.x = f9;
                this.y = f10;
            }
        } else if (this.transform) {
            int i5 = snapshotArray.size;
            for (int i6 = 0; i6 < i5; i6++) {
                Actor actor3 = begin[i6];
                if (actor3.isVisible()) {
                    actor3.draw(batch, f2);
                }
            }
        } else {
            float f13 = this.x;
            float f14 = this.y;
            this.x = 0.0f;
            this.y = 0.0f;
            int i7 = snapshotArray.size;
            for (int i8 = 0; i8 < i7; i8++) {
                Actor actor4 = begin[i8];
                if (actor4.isVisible()) {
                    float f15 = actor4.x;
                    float f16 = actor4.y;
                    actor4.x = f15 + f13;
                    actor4.y = f16 + f14;
                    actor4.draw(batch, f2);
                    actor4.x = f15;
                    actor4.y = f16;
                }
            }
            this.x = f13;
            this.y = f14;
        }
        snapshotArray.end();
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Actor
    public void drawDebug(ShapeRenderer shapeRenderer) {
        drawDebugBounds(shapeRenderer);
        if (this.transform) {
            applyTransform(shapeRenderer, computeTransform());
        }
        drawDebugChildren(shapeRenderer);
        if (this.transform) {
            resetTransform(shapeRenderer);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void drawDebugChildren(ShapeRenderer shapeRenderer) {
        SnapshotArray<Actor> snapshotArray = this.children;
        Actor[] begin = snapshotArray.begin();
        if (this.transform) {
            int i = snapshotArray.size;
            for (int i2 = 0; i2 < i; i2++) {
                Actor actor = begin[i2];
                if (actor.isVisible() && (actor.getDebug() || (actor instanceof Group))) {
                    actor.drawDebug(shapeRenderer);
                }
            }
            shapeRenderer.flush();
        } else {
            float f = this.x;
            float f2 = this.y;
            this.x = 0.0f;
            this.y = 0.0f;
            int i3 = snapshotArray.size;
            for (int i4 = 0; i4 < i3; i4++) {
                Actor actor2 = begin[i4];
                if (actor2.isVisible() && (actor2.getDebug() || (actor2 instanceof Group))) {
                    float f3 = actor2.x;
                    float f4 = actor2.y;
                    actor2.x = f3 + f;
                    actor2.y = f4 + f2;
                    actor2.drawDebug(shapeRenderer);
                    actor2.x = f3;
                    actor2.y = f4;
                }
            }
            this.x = f;
            this.y = f2;
        }
        snapshotArray.end();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Matrix4 computeTransform() {
        Group group;
        Affine2 affine2 = this.worldTransform;
        float f = this.originX;
        float f2 = this.originY;
        affine2.setToTrnRotScl(this.x + f, this.y + f2, this.rotation, this.scaleX, this.scaleY);
        if (f != 0.0f || f2 != 0.0f) {
            affine2.translate(-f, -f2);
        }
        Group group2 = this.parent;
        while (true) {
            group = group2;
            if (group == null || group.transform) {
                break;
            }
            group2 = group.parent;
        }
        if (group != null) {
            affine2.preMul(group.worldTransform);
        }
        this.computedTransform.set(affine2);
        return this.computedTransform;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void applyTransform(Batch batch, Matrix4 matrix4) {
        this.oldTransform.set(batch.getTransformMatrix());
        batch.setTransformMatrix(matrix4);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void resetTransform(Batch batch) {
        batch.setTransformMatrix(this.oldTransform);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void applyTransform(ShapeRenderer shapeRenderer, Matrix4 matrix4) {
        this.oldTransform.set(shapeRenderer.getTransformMatrix());
        shapeRenderer.setTransformMatrix(matrix4);
        shapeRenderer.flush();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void resetTransform(ShapeRenderer shapeRenderer) {
        shapeRenderer.setTransformMatrix(this.oldTransform);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.Cullable
    public void setCullingArea(@Null Rectangle rectangle) {
        this.cullingArea = rectangle;
    }

    @Null
    public Rectangle getCullingArea() {
        return this.cullingArea;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Actor
    @Null
    public Actor hit(float f, float f2, boolean z) {
        if ((z && getTouchable() == Touchable.disabled) || !isVisible()) {
            return null;
        }
        Vector2 vector2 = tmp;
        Actor[] actorArr = this.children.items;
        for (int i = this.children.size - 1; i >= 0; i--) {
            Actor actor = actorArr[i];
            actor.parentToLocalCoordinates(vector2.set(f, f2));
            Actor hit = actor.hit(vector2.x, vector2.y, z);
            if (hit != null) {
                return hit;
            }
        }
        return super.hit(f, f2, z);
    }

    protected void childrenChanged() {
    }

    public void addActor(Actor actor) {
        if (actor.parent != null) {
            if (actor.parent == this) {
                return;
            } else {
                actor.parent.removeActor(actor, false);
            }
        }
        this.children.add(actor);
        actor.setParent(this);
        actor.setStage(getStage());
        childrenChanged();
    }

    public void addActorAt(int i, Actor actor) {
        if (actor.parent != null) {
            if (actor.parent == this) {
                return;
            } else {
                actor.parent.removeActor(actor, false);
            }
        }
        if (i >= this.children.size) {
            this.children.add(actor);
        } else {
            this.children.insert(i, actor);
        }
        actor.setParent(this);
        actor.setStage(getStage());
        childrenChanged();
    }

    public void addActorBefore(Actor actor, Actor actor2) {
        if (actor2.parent != null) {
            if (actor2.parent == this) {
                return;
            } else {
                actor2.parent.removeActor(actor2, false);
            }
        }
        this.children.insert(this.children.indexOf(actor, true), actor2);
        actor2.setParent(this);
        actor2.setStage(getStage());
        childrenChanged();
    }

    public void addActorAfter(Actor actor, Actor actor2) {
        if (actor2.parent != null) {
            if (actor2.parent == this) {
                return;
            } else {
                actor2.parent.removeActor(actor2, false);
            }
        }
        int indexOf = this.children.indexOf(actor, true);
        if (indexOf != this.children.size && indexOf != -1) {
            this.children.insert(indexOf + 1, actor2);
        } else {
            this.children.add(actor2);
        }
        actor2.setParent(this);
        actor2.setStage(getStage());
        childrenChanged();
    }

    public boolean removeActor(Actor actor) {
        return removeActor(actor, true);
    }

    public boolean removeActor(Actor actor, boolean z) {
        int indexOf = this.children.indexOf(actor, true);
        if (indexOf == -1) {
            return false;
        }
        removeActorAt(indexOf, z);
        return true;
    }

    public Actor removeActorAt(int i, boolean z) {
        Actor removeIndex = this.children.removeIndex(i);
        Stage stage = getStage();
        if (stage != null) {
            if (z) {
                stage.unfocus(removeIndex);
            }
            stage.actorRemoved(removeIndex);
        }
        removeIndex.setParent(null);
        removeIndex.setStage(null);
        childrenChanged();
        return removeIndex;
    }

    public void clearChildren() {
        clearChildren(true);
    }

    public void clearChildren(boolean z) {
        Stage stage;
        Actor[] begin = this.children.begin();
        int i = this.children.size;
        for (int i2 = 0; i2 < i; i2++) {
            Actor actor = begin[i2];
            if (z && (stage = getStage()) != null) {
                stage.unfocus(actor);
            }
            actor.setStage(null);
            actor.setParent(null);
        }
        this.children.end();
        this.children.clear();
        childrenChanged();
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Actor
    public void clear() {
        super.clear();
        clearChildren(true);
    }

    public void clear(boolean z) {
        super.clear();
        clearChildren(z);
    }

    @Null
    public <T extends Actor> T findActor(String str) {
        T t;
        SnapshotArray<Actor> snapshotArray = this.children;
        int i = snapshotArray.size;
        for (int i2 = 0; i2 < i; i2++) {
            if (str.equals(snapshotArray.get(i2).getName())) {
                return (T) snapshotArray.get(i2);
            }
        }
        int i3 = snapshotArray.size;
        for (int i4 = 0; i4 < i3; i4++) {
            Actor actor = snapshotArray.get(i4);
            if ((actor instanceof Group) && (t = (T) ((Group) actor).findActor(str)) != null) {
                return t;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.badlogic.gdx.scenes.scene2d.Actor
    public void setStage(Stage stage) {
        super.setStage(stage);
        Actor[] actorArr = this.children.items;
        int i = this.children.size;
        for (int i2 = 0; i2 < i; i2++) {
            actorArr[i2].setStage(stage);
        }
    }

    public boolean swapActor(int i, int i2) {
        int i3 = this.children.size;
        if (i < 0 || i >= i3 || i2 < 0 || i2 >= i3) {
            return false;
        }
        this.children.swap(i, i2);
        return true;
    }

    public boolean swapActor(Actor actor, Actor actor2) {
        int indexOf = this.children.indexOf(actor, true);
        int indexOf2 = this.children.indexOf(actor2, true);
        if (indexOf == -1 || indexOf2 == -1) {
            return false;
        }
        this.children.swap(indexOf, indexOf2);
        return true;
    }

    public Actor getChild(int i) {
        return this.children.get(i);
    }

    public SnapshotArray<Actor> getChildren() {
        return this.children;
    }

    public boolean hasChildren() {
        return this.children.size > 0;
    }

    public void setTransform(boolean z) {
        this.transform = z;
    }

    public boolean isTransform() {
        return this.transform;
    }

    public Vector2 localToDescendantCoordinates(Actor actor, Vector2 vector2) {
        Group group = actor.parent;
        if (group == null) {
            throw new IllegalArgumentException("Actor is not a descendant: " + actor);
        }
        if (group != this) {
            localToDescendantCoordinates(group, vector2);
        }
        actor.parentToLocalCoordinates(vector2);
        return vector2;
    }

    public void setDebug(boolean z, boolean z2) {
        setDebug(z);
        if (z2) {
            Array.ArrayIterator<Actor> it = this.children.iterator();
            while (it.hasNext()) {
                Actor next = it.next();
                if (next instanceof Group) {
                    ((Group) next).setDebug(z, z2);
                } else {
                    next.setDebug(z);
                }
            }
        }
    }

    public Group debugAll() {
        setDebug(true, true);
        return this;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Actor
    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        toString(sb, 1);
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    void toString(StringBuilder sb, int i) {
        sb.append(super.toString());
        sb.append('\n');
        Actor[] begin = this.children.begin();
        int i2 = this.children.size;
        for (int i3 = 0; i3 < i2; i3++) {
            for (int i4 = 0; i4 < i; i4++) {
                sb.append("|  ");
            }
            Actor actor = begin[i3];
            if (actor instanceof Group) {
                ((Group) actor).toString(sb, i + 1);
            } else {
                sb.append(actor);
                sb.append('\n');
            }
        }
        this.children.end();
    }
}
