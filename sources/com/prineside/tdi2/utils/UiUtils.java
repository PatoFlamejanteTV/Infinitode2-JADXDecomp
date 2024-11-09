package com.prineside.tdi2.utils;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.InputListener;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.ScrollPane;
import com.prineside.tdi2.scene2d.utils.Layout;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.events.MoveToFrontListener;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/UiUtils.class */
public class UiUtils {
    public static void finishActions(Actor actor, float f) {
        float f2 = 0.0f;
        while (true) {
            float f3 = f2;
            if (f3 < f && actor.hasActions()) {
                actor.act(0.033333335f);
                f2 = f3 + 0.033333335f;
            } else {
                return;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void bringToFront(Actor actor) {
        Group parent = actor.getParent();
        if (parent == 0) {
            return;
        }
        Array array = null;
        SnapshotArray<Actor> children = parent.getChildren();
        boolean z = false;
        for (int i = 0; i < children.size; i++) {
            if (children.items[i] == actor) {
                z = true;
            } else if (z) {
                if (array == null) {
                    array = (Array) Pools.obtain(Array.class);
                }
                array.add(children.items[i]);
            }
        }
        if (array != null) {
            array.add(actor);
            MoveToFrontListener.MoveToFrontEvent moveToFrontEvent = (MoveToFrontListener.MoveToFrontEvent) Pools.obtain(MoveToFrontListener.MoveToFrontEvent.class);
            moveToFrontEvent.setStage(actor.getStage());
            moveToFrontEvent.setTarget(actor);
            Array.ArrayIterator it = array.iterator();
            while (it.hasNext()) {
                ((Actor) it.next()).notify(moveToFrontEvent, false);
            }
            array.clear();
            Pools.free(array);
            boolean isCancelled = moveToFrontEvent.isCancelled();
            Pools.free(moveToFrontEvent);
            if (!isCancelled) {
                boolean z2 = false;
                for (int i2 = 0; i2 < children.size; i2++) {
                    if (children.items[i2] == actor) {
                        z2 = true;
                    } else if (z2) {
                        children.items[i2 - 1] = children.items[i2];
                    }
                }
                children.items[children.size - 1] = actor;
                if (parent instanceof Layout) {
                    ((Layout) parent).invalidateHierarchy();
                }
            }
        }
    }

    public static boolean hasParent(Actor actor, Group group) {
        if (actor == null) {
            throw new IllegalArgumentException("Actor is null");
        }
        if (group == null) {
            throw new IllegalArgumentException("Patent is null");
        }
        Actor actor2 = actor;
        while (actor2.hasParent()) {
            Group parent = actor2.getParent();
            actor2 = parent;
            if (parent == group) {
                return true;
            }
        }
        return false;
    }

    private static void a(Actor actor, StringBuilder stringBuilder) {
        if (actor == null) {
            throw new IllegalArgumentException("Actor is null");
        }
        if (stringBuilder == null) {
            throw new IllegalArgumentException("sb is null");
        }
        stringBuilder.append("- ").append(actor.getName()).append(" (").append(actor.getClass().getSimpleName()).append(")");
        if (actor instanceof Label) {
            stringBuilder.append(", text: ").append(((Label) actor).getText());
        }
        stringBuilder.append(SequenceUtils.EOL);
        if (actor.hasParent()) {
            a(actor.getParent(), stringBuilder);
        }
    }

    public static StringBuilder getFullPathToStage(Actor actor) {
        if (actor == null) {
            throw new IllegalArgumentException("Actor is null");
        }
        StringBuilder stringBuilder = new StringBuilder();
        a(actor, stringBuilder);
        return stringBuilder;
    }

    public static void enableMouseMoveScrollFocus(final ScrollPane scrollPane) {
        scrollPane.addListener(new InputListener() { // from class: com.prineside.tdi2.utils.UiUtils.1
            @Override // com.prineside.tdi2.scene2d.InputListener
            public void enter(InputEvent inputEvent, float f, float f2, int i, @Null Actor actor) {
                if (ScrollPane.this.getStage() != null) {
                    ScrollPane.this.getStage().setScrollFocus(ScrollPane.this);
                }
            }

            @Override // com.prineside.tdi2.scene2d.InputListener
            public void exit(InputEvent inputEvent, float f, float f2, int i, @Null Actor actor) {
                if (ScrollPane.this.getStage() != null && ScrollPane.this.getStage().getScrollFocus() == ScrollPane.this) {
                    ScrollPane.this.getStage().setScrollFocus(null);
                }
            }
        });
    }

    public static boolean isVisibleRecursive(Actor actor) {
        if (actor == null) {
            throw new IllegalArgumentException("Actor is null");
        }
        if (!actor.isVisible() || actor.getStage() == null) {
            return false;
        }
        Group parent = actor.getParent();
        if (parent != null) {
            return isVisibleRecursive(parent);
        }
        return true;
    }

    public static void bouncyShowOverlay(Actor actor, Actor actor2, Group group) {
        bouncyShowOverlayWithCallback(actor, actor2, group, null);
    }

    public static void bouncyShowOverlayWithCallback(Actor actor, Actor actor2, Group group, Runnable runnable) {
        float f = Game.i.settingsManager.isUiAnimationsEnabled() ? 1.5f : 0.0f;
        if (actor != null) {
            actor.setTouchable(Touchable.enabled);
            actor.clearActions();
            actor.addAction(Actions.sequence(Actions.show(), Actions.fadeIn(0.15f * f)));
        }
        if (actor2 != null) {
            actor2.setVisible(true);
        }
        group.clearActions();
        group.setTouchable(Touchable.enabled);
        group.setTransform(true);
        group.addAction(Actions.sequence(Actions.scaleTo(0.0f, 1.1f), Actions.moveTo(0.0f, -Game.i.settingsManager.getScaledViewportHeight()), Actions.parallel(Actions.sequence(Actions.moveTo(0.0f, Game.i.settingsManager.getScaledViewportHeight() * 0.05f, 0.15f * f, Interpolation.pow4Out), Actions.moveTo(0.0f, 0.0f, 0.07f * f, Interpolation.pow2)), Actions.sequence(Actions.scaleBy(1.1f, 0.0f, 0.15f * f, Interpolation.pow2Out), Actions.scaleBy(-0.1f, 0.0f, 0.07f * f, Interpolation.pow2)), Actions.sequence(Actions.scaleBy(0.0f, -0.2f, 0.15f * f, Interpolation.pow2Out), Actions.scaleBy(0.0f, 0.1f, 0.07f * f, Interpolation.pow2))), Actions.run(() -> {
            group.setTransform(false);
            if (runnable != null) {
                runnable.run();
            }
        })));
    }

    public static void bouncyHideOverlay(Actor actor, Actor actor2, Group group) {
        bouncyHideOverlayWithCallback(actor, actor2, group, null);
    }

    public static void bouncyHideOverlayWithCallback(Actor actor, Actor actor2, Group group, Runnable runnable) {
        float f = Game.i.settingsManager.isUiAnimationsEnabled() ? 1.5f : 0.0f;
        if (actor != null) {
            actor.setTouchable(Touchable.disabled);
            actor.clearActions();
            actor.addAction(Actions.sequence(Actions.fadeOut(0.15f * f), Actions.hide()));
        }
        group.clearActions();
        group.setTouchable(Touchable.disabled);
        group.setTransform(true);
        group.addAction(Actions.sequence(Actions.parallel(Actions.sequence(Actions.delay(0.12f * f), Actions.moveTo(0.0f, -Game.i.settingsManager.getScaledViewportHeight(), 0.16f * f, Interpolation.sineIn)), Actions.sequence(Actions.scaleBy(-group.getScaleX(), 0.0f, 0.17f * f, Interpolation.swingIn)), Actions.sequence(Actions.scaleBy(0.0f, (-group.getScaleY()) * 0.1f, 0.05f * f, Interpolation.pow2), Actions.scaleBy(0.0f, group.getScaleY() * 0.2f, 0.11f * f, Interpolation.pow2), Actions.scaleBy(0.0f, (-group.getScaleY()) * 1.1f, 0.11f * f, Interpolation.pow2In))), Actions.run(() -> {
            if (actor2 != null) {
                actor2.setVisible(false);
            }
            if (runnable != null) {
                runnable.run();
            }
        })));
    }
}
