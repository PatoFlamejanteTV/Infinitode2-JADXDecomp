package com.prineside.tdi2.scene2d;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.SnapshotArray;
import com.prineside.tdi2.scene2d.utils.Cullable;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/Group.class */
public class Group extends Actor implements Cullable {
    private static final Vector2 k = new Vector2();
    protected final SnapshotArray<Actor> j = new SnapshotArray<>(true, 4, Actor.class);
    private Affine2 l = new Affine2();
    private Matrix4 m = new Matrix4();
    private Matrix4 n = new Matrix4();
    private boolean o = true;

    @Null
    private Rectangle p;

    @Override // com.prineside.tdi2.scene2d.Actor
    public void act(float f) {
        super.act(f);
        Actor[] begin = this.j.begin();
        int i = this.j.size;
        for (int i2 = 0; i2 < i; i2++) {
            begin[i2].act(f);
        }
        this.j.end();
    }

    @Override // com.prineside.tdi2.scene2d.Actor
    public void draw(Batch batch, float f) {
        if (this.o) {
            a(batch, b());
        }
        a(batch, f);
        if (this.o) {
            a(batch);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(Batch batch, float f) {
        float f2 = f * this.i.f889a;
        SnapshotArray<Actor> snapshotArray = this.j;
        Actor[] begin = snapshotArray.begin();
        Rectangle rectangle = this.p;
        if (rectangle != null) {
            float f3 = rectangle.x;
            float f4 = f3 + rectangle.width;
            float f5 = rectangle.y;
            float f6 = f5 + rectangle.height;
            if (this.o) {
                int i = snapshotArray.size;
                for (int i2 = 0; i2 < i; i2++) {
                    Actor actor = begin[i2];
                    if (actor.isVisible()) {
                        float x = actor.getX();
                        float y = actor.getY();
                        if (x <= f4 && y <= f6 && x + actor.getWidth() >= f3 && y + actor.getHeight() >= f5) {
                            actor.draw(batch, f2);
                        }
                    }
                }
            } else {
                float x2 = getX();
                float y2 = getY();
                setX(0.0f);
                setY(0.0f);
                int i3 = snapshotArray.size;
                for (int i4 = 0; i4 < i3; i4++) {
                    Actor actor2 = begin[i4];
                    if (actor2.isVisible()) {
                        float x3 = actor2.getX();
                        float y3 = actor2.getY();
                        if (x3 <= f4 && y3 <= f6 && x3 + actor2.getWidth() >= f3 && y3 + actor2.getHeight() >= f5) {
                            actor2.setX(x3 + x2);
                            actor2.setY(y3 + y2);
                            actor2.draw(batch, f2);
                            actor2.setX(x3);
                            actor2.setY(y3);
                        }
                    }
                }
                setX(x2);
                setY(y2);
            }
        } else if (this.o) {
            int i5 = snapshotArray.size;
            for (int i6 = 0; i6 < i5; i6++) {
                Actor actor3 = begin[i6];
                if (actor3.isVisible()) {
                    actor3.draw(batch, f2);
                }
            }
        } else {
            float x4 = getX();
            float y4 = getY();
            setX(0.0f);
            setY(0.0f);
            int i7 = snapshotArray.size;
            for (int i8 = 0; i8 < i7; i8++) {
                Actor actor4 = begin[i8];
                if (actor4.isVisible()) {
                    float x5 = actor4.getX();
                    float y5 = actor4.getY();
                    actor4.setX(x5 + x4);
                    actor4.setY(y5 + y4);
                    actor4.draw(batch, f2);
                    actor4.setX(x5);
                    actor4.setY(y5);
                }
            }
            setX(x4);
            setY(y4);
        }
        snapshotArray.end();
    }

    @Override // com.prineside.tdi2.scene2d.Actor
    public void drawDebug(ShapeRenderer shapeRenderer) {
        a(shapeRenderer);
        if (this.o) {
            a(shapeRenderer, b());
        }
        b(shapeRenderer);
        if (this.o) {
            c(shapeRenderer);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void b(ShapeRenderer shapeRenderer) {
        SnapshotArray<Actor> snapshotArray = this.j;
        Actor[] begin = snapshotArray.begin();
        if (this.o) {
            int i = snapshotArray.size;
            for (int i2 = 0; i2 < i; i2++) {
                Actor actor = begin[i2];
                if (actor.isVisible() && (actor.getDebug() || (actor instanceof Group))) {
                    actor.drawDebug(shapeRenderer);
                }
            }
            shapeRenderer.flush();
        } else {
            float x = getX();
            float y = getY();
            setX(0.0f);
            setY(0.0f);
            int i3 = snapshotArray.size;
            for (int i4 = 0; i4 < i3; i4++) {
                Actor actor2 = begin[i4];
                if (actor2.isVisible() && (actor2.getDebug() || (actor2 instanceof Group))) {
                    float x2 = actor2.getX();
                    float y2 = actor2.getY();
                    actor2.setX(x2 + x);
                    actor2.setY(y2 + y);
                    actor2.drawDebug(shapeRenderer);
                    actor2.setX(x2);
                    actor2.setY(y2);
                }
            }
            setX(x);
            setY(y);
        }
        snapshotArray.end();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Matrix4 b() {
        Group group;
        Affine2 affine2 = this.l;
        float f = this.d;
        float f2 = this.e;
        affine2.setToTrnRotScl(getX() + f, getY() + f2, this.h, this.f, this.g);
        if (f != 0.0f || f2 != 0.0f) {
            affine2.translate(-f, -f2);
        }
        Group group2 = this.f2639b;
        while (true) {
            group = group2;
            if (group == null || group.o) {
                break;
            }
            group2 = group.f2639b;
        }
        if (group != null) {
            affine2.preMul(group.l);
        }
        this.m.set(affine2);
        return this.m;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(Batch batch, Matrix4 matrix4) {
        this.n.set(batch.getTransformMatrix());
        batch.setTransformMatrix(matrix4);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(Batch batch) {
        batch.setTransformMatrix(this.n);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(ShapeRenderer shapeRenderer, Matrix4 matrix4) {
        this.n.set(shapeRenderer.getTransformMatrix());
        shapeRenderer.setTransformMatrix(matrix4);
        shapeRenderer.flush();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void c(ShapeRenderer shapeRenderer) {
        shapeRenderer.setTransformMatrix(this.n);
    }

    @Override // com.prineside.tdi2.scene2d.utils.Cullable
    public void setCullingArea(@Null Rectangle rectangle) {
        this.p = rectangle;
    }

    @Null
    public Rectangle getCullingArea() {
        return this.p;
    }

    @Override // com.prineside.tdi2.scene2d.Actor
    @Null
    public Actor hit(float f, float f2, boolean z) {
        if ((z && getTouchable() == Touchable.disabled) || !isVisible()) {
            return null;
        }
        Vector2 vector2 = k;
        Actor[] actorArr = this.j.items;
        for (int i = this.j.size - 1; i >= 0; i--) {
            Actor actor = actorArr[i];
            actor.parentToLocalCoordinates(vector2.set(f, f2));
            Actor hit = actor.hit(vector2.x, vector2.y, z);
            if (hit != null) {
                return hit;
            }
        }
        return super.hit(f, f2, z);
    }

    protected void c() {
    }

    public void addActor(Actor actor) {
        if (actor.f2639b != null) {
            if (actor.f2639b == this) {
                return;
            } else {
                actor.f2639b.removeActor(actor, false);
            }
        }
        this.j.add(actor);
        actor.a(this);
        actor.a(getStage());
        c();
    }

    public void addActorAt(int i, Actor actor) {
        if (actor.f2639b != null) {
            if (actor.f2639b == this) {
                return;
            } else {
                actor.f2639b.removeActor(actor, false);
            }
        }
        if (i >= this.j.size) {
            this.j.add(actor);
        } else {
            this.j.insert(i, actor);
        }
        actor.a(this);
        actor.a(getStage());
        c();
    }

    public void addActorBefore(Actor actor, Actor actor2) {
        if (actor2.f2639b != null) {
            if (actor2.f2639b == this) {
                return;
            } else {
                actor2.f2639b.removeActor(actor2, false);
            }
        }
        this.j.insert(this.j.indexOf(actor, true), actor2);
        actor2.a(this);
        actor2.a(getStage());
        c();
    }

    public void addActorAfter(Actor actor, Actor actor2) {
        if (actor2.f2639b != null) {
            if (actor2.f2639b == this) {
                return;
            } else {
                actor2.f2639b.removeActor(actor2, false);
            }
        }
        int indexOf = this.j.indexOf(actor, true);
        if (indexOf != this.j.size && indexOf != -1) {
            this.j.insert(indexOf + 1, actor2);
        } else {
            this.j.add(actor2);
        }
        actor2.a(this);
        actor2.a(getStage());
        c();
    }

    public boolean removeActor(Actor actor) {
        return removeActor(actor, true);
    }

    public boolean removeActor(Actor actor, boolean z) {
        int indexOf = this.j.indexOf(actor, true);
        if (indexOf == -1) {
            return false;
        }
        removeActorAt(indexOf, z);
        return true;
    }

    public Actor removeActorAt(int i, boolean z) {
        Actor removeIndex = this.j.removeIndex(i);
        Stage stage = getStage();
        if (stage != null) {
            if (z) {
                stage.unfocus(removeIndex);
            }
            stage.a(removeIndex);
        }
        removeIndex.a((Group) null);
        removeIndex.a((Stage) null);
        c();
        return removeIndex;
    }

    public void clearChildren() {
        clearChildren(true);
    }

    public void clearChildren(boolean z) {
        Stage stage;
        Actor[] begin = this.j.begin();
        int i = this.j.size;
        for (int i2 = 0; i2 < i; i2++) {
            Actor actor = begin[i2];
            if (z && (stage = getStage()) != null) {
                stage.unfocus(actor);
            }
            actor.a((Stage) null);
            actor.a((Group) null);
        }
        this.j.end();
        this.j.clear();
        c();
    }

    @Override // com.prineside.tdi2.scene2d.Actor
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
        SnapshotArray<Actor> snapshotArray = this.j;
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
    @Override // com.prineside.tdi2.scene2d.Actor
    public void a(Stage stage) {
        super.a(stage);
        Actor[] actorArr = this.j.items;
        int i = this.j.size;
        for (int i2 = 0; i2 < i; i2++) {
            actorArr[i2].a(stage);
        }
    }

    public boolean swapActor(int i, int i2) {
        int i3 = this.j.size;
        if (i < 0 || i >= i3 || i2 < 0 || i2 >= i3) {
            return false;
        }
        this.j.swap(i, i2);
        return true;
    }

    public boolean swapActor(Actor actor, Actor actor2) {
        int indexOf = this.j.indexOf(actor, true);
        int indexOf2 = this.j.indexOf(actor2, true);
        if (indexOf == -1 || indexOf2 == -1) {
            return false;
        }
        this.j.swap(indexOf, indexOf2);
        return true;
    }

    public Actor getChild(int i) {
        return this.j.get(i);
    }

    public SnapshotArray<Actor> getChildren() {
        return this.j;
    }

    public boolean hasChildren() {
        return this.j.size > 0;
    }

    public void setTransform(boolean z) {
        this.o = z;
    }

    public boolean isTransform() {
        return this.o;
    }

    public Vector2 localToDescendantCoordinates(Actor actor, Vector2 vector2) {
        Group group = actor.f2639b;
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
            Array.ArrayIterator<Actor> it = this.j.iterator();
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

    @Override // com.prineside.tdi2.scene2d.Actor
    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        a(sb, 1);
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    private void a(StringBuilder sb, int i) {
        sb.append(super.toString());
        sb.append('\n');
        Actor[] begin = this.j.begin();
        int i2 = this.j.size;
        for (int i3 = 0; i3 < i2; i3++) {
            for (int i4 = 0; i4 < i; i4++) {
                sb.append("|  ");
            }
            Actor actor = begin[i3];
            if (actor instanceof Group) {
                ((Group) actor).a(sb, i + 1);
            } else {
                sb.append(actor);
                sb.append('\n');
            }
        }
        this.j.end();
    }
}
