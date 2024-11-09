package com.prineside.tdi2.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Null;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Manager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.EventListener;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.InputListener;
import com.prineside.tdi2.scene2d.utils.Disableable;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.utils.ObjectPair;
import com.prineside.tdi2.utils.ObjectSupplier;
import com.prineside.tdi2.utils.logging.TLog;
import net.bytebuddy.utility.JavaConstant;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/CursorGraphicsManager.class */
public class CursorGraphicsManager extends Manager.ManagerAdapter {

    /* renamed from: a, reason: collision with root package name */
    protected Array<ObjectPair<String, ObjectSupplier<Cursor.SystemCursor>>> f2314a = new Array<>(true, 1, ObjectPair.class);

    /* renamed from: b, reason: collision with root package name */
    private Cursor.SystemCursor f2315b;

    static {
        TLog.forClass(CursorGraphicsManager.class);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/CursorGraphicsManager$Serializer.class */
    public static class Serializer extends SingletonSerializer<CursorGraphicsManager> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public CursorGraphicsManager read() {
            return Game.i.cursorGraphics;
        }
    }

    public void setActorCustomMouseCursor(Actor actor, Cursor.SystemCursor systemCursor) {
        setActorCustomMouseCursorConditional(actor, () -> {
            return systemCursor;
        });
    }

    public void setActorCustomMouseCursorConditional(Actor actor, ObjectSupplier<Cursor.SystemCursor> objectSupplier) {
        String str = actor.getClass().getSimpleName() + JavaConstant.Dynamic.DEFAULT_NAME + Integer.toHexString(actor.hashCode());
        DelayedRemovalArray<EventListener> captureListeners = actor.getCaptureListeners();
        captureListeners.begin();
        int i = captureListeners.size;
        for (int i2 = 0; i2 < i; i2++) {
            if (captureListeners.get(i2) instanceof CustomCursorActorListener) {
                captureListeners.removeIndex(i2);
            }
        }
        captureListeners.end();
        actor.addCaptureListener(new CustomCursorActorListener(str, objectSupplier));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, ObjectSupplier<Cursor.SystemCursor> objectSupplier) {
        Preconditions.checkNotNull(str, "Owner can not be null");
        Preconditions.checkNotNull(objectSupplier, "Type can not be null");
        a(str);
        this.f2314a.add(new ObjectPair().set(str, objectSupplier));
    }

    public boolean remove(String str) {
        return a(str);
    }

    public boolean contains(String str) {
        for (int i = 0; i < this.f2314a.size; i++) {
            if (this.f2314a.items[i].first.equals(str)) {
                return true;
            }
        }
        return false;
    }

    private boolean a(String str) {
        for (int i = 0; i < this.f2314a.size; i++) {
            if (this.f2314a.items[i].first.equals(str)) {
                this.f2314a.removeIndex(i);
                return true;
            }
        }
        return false;
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void preRender(float f) {
        a();
    }

    private void a() {
        Cursor.SystemCursor systemCursor = Cursor.SystemCursor.Arrow;
        if (this.f2314a.size != 0) {
            systemCursor = this.f2314a.items[this.f2314a.size - 1].second.get();
        }
        if (this.f2315b != systemCursor) {
            Gdx.graphics.setSystemCursor(systemCursor);
            this.f2315b = systemCursor;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/CursorGraphicsManager$CustomCursorActorListener.class */
    public static class CustomCursorActorListener extends InputListener {

        /* renamed from: a, reason: collision with root package name */
        private String f2316a;

        /* renamed from: b, reason: collision with root package name */
        private ObjectSupplier<Cursor.SystemCursor> f2317b;

        public CustomCursorActorListener(String str, ObjectSupplier<Cursor.SystemCursor> objectSupplier) {
            Preconditions.checkNotNull(str, "ownerId can not be null");
            Preconditions.checkNotNull(objectSupplier, "cursor can not be null");
            this.f2316a = str;
            this.f2317b = objectSupplier;
        }

        @Override // com.prineside.tdi2.scene2d.InputListener
        public void enter(InputEvent inputEvent, float f, float f2, int i, @Null Actor actor) {
            if (i != -1) {
                return;
            }
            if ((inputEvent.getListenerActor() instanceof Disableable) && ((Disableable) inputEvent.getListenerActor()).isDisabled()) {
                return;
            }
            Game.i.cursorGraphics.a(this.f2316a, this.f2317b);
        }

        @Override // com.prineside.tdi2.scene2d.InputListener
        public void exit(InputEvent inputEvent, float f, float f2, int i, @Null Actor actor) {
            if (i != -1) {
                return;
            }
            Game.i.cursorGraphics.remove(this.f2316a);
        }
    }
}
