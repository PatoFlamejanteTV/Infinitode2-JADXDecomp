package com.prineside.tdi2.ui.shared;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Null;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Action;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.actions.DelayAction;
import com.prineside.tdi2.scene2d.actions.SequenceAction;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.actors.QuadActor;
import com.prineside.tdi2.utils.MaterialColor;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/Notifications.class */
public class Notifications extends Group implements UiManager.UiComponent {
    public static final float DEFAULT_SHOW_DURATION = 5.0f;
    public static final float CONTENT_WIDTH = 340.0f;
    public static final float CONTENT_MARGIN_LEFT = 106.0f;
    public static final float CONTENT_MARGIN_BOTTOM = 20.0f;
    private final Group m;
    private static Color o = new Color();
    private final UiManager.UiLayer k = Game.i.uiManager.addLayer(UiManager.MainUiLayer.OVERLAY, 300, "Notifications");
    private final Label.LabelStyle l = new Label.LabelStyle(Game.i.assetManager.getFont(24), Color.WHITE);
    private final DelayedRemovalArray<Notification> n = new DelayedRemovalArray<>(true, 1, Notification.class);

    public static Notifications i() {
        return (Notifications) Game.i.uiManager.getComponent(Notifications.class);
    }

    public Notifications() {
        Table table = this.k.getTable();
        table.setTouchable(Touchable.childrenOnly);
        this.m = new Group();
        this.m.setTouchable(Touchable.childrenOnly);
        table.add((Table) this.m).expand().top().left().padTop(160.0f).width(460.0f);
    }

    private Notification a(Notification notification, float f) {
        Game.i.assertInMainThread();
        this.n.add(notification);
        this.m.addActor(notification);
        notification.status = 1;
        notification.setPosition(0.0f, getHeight());
        notification.setVisible(true);
        notification.addAction(Actions.sequence(Actions.alpha(0.0f), Actions.parallel(Actions.alpha(1.0f, 0.3f), Actions.sizeTo(notification.getWidth(), notification.notificationHeight, 0.3f), Actions.moveBy(0.0f, -notification.notificationHeight, 0.3f)), Actions.run(() -> {
            notification.status = 2;
        }), Actions.delay(f), Actions.run(() -> {
            a(notification);
        })));
        for (int i = 0; i < this.n.size - 1; i++) {
            this.n.get(i).addAction(Actions.moveBy(0.0f, -notification.notificationHeight, 0.3f));
        }
        for (int i2 = 0; i2 < (this.n.size - 1) - 7; i2++) {
            a(this.n.get(i2));
        }
        return notification;
    }

    public Notification addWithContents(Table table, Drawable drawable, Color color, StaticSoundType staticSoundType, float f) {
        if (color == null) {
            color = MaterialColor.BLUE_GREY.P800;
        }
        o.set(color);
        if (o.f889a > 0.85f) {
            o.f889a = 0.85f;
        }
        if (drawable == null) {
            drawable = Game.i.assetManager.getDrawable("icon-info");
        }
        Notification notification = new Notification(this, table, drawable, o, (byte) 0);
        a(notification, f);
        if (staticSoundType != null) {
            Game.i.soundManager.playStatic(staticSoundType);
        }
        return notification;
    }

    public Notification addInfo(CharSequence charSequence) {
        return add(charSequence, null, null, null);
    }

    public Notification add(CharSequence charSequence, Drawable drawable, Color color, StaticSoundType staticSoundType) {
        return addForDuration(charSequence, drawable, color, staticSoundType, 5.0f);
    }

    public Notification addFailure(CharSequence charSequence) {
        return add(charSequence, Game.i.assetManager.getDrawable("icon-times"), MaterialColor.RED.P800, StaticSoundType.FAIL);
    }

    public Notification addSuccess(CharSequence charSequence) {
        return add(charSequence, Game.i.assetManager.getDrawable("icon-check"), MaterialColor.GREEN.P800, StaticSoundType.SUCCESS);
    }

    public Notification addForDuration(CharSequence charSequence, Drawable drawable, Color color, StaticSoundType staticSoundType, float f) {
        if (color == null) {
            color = MaterialColor.BLUE_GREY.P800;
        }
        o.set(color);
        if (o.f889a > 0.85f) {
            o.f889a = 0.85f;
        }
        if (drawable == null) {
            drawable = Game.i.assetManager.getDrawable("icon-info");
        }
        Notification notification = new Notification(this, charSequence, drawable, o, (byte) 0);
        a(notification, f);
        if (staticSoundType != null) {
            Game.i.soundManager.playStatic(staticSoundType);
        }
        return notification;
    }

    public void hideNotification(String str) {
        Preconditions.checkNotNull(str);
        Game.i.assertInMainThread();
        for (int i = 0; i < this.n.size; i++) {
            if (str.equals(this.n.get(i).id) && this.n.get(i).status != 3) {
                a(this.n.get(i));
                return;
            }
        }
    }

    private void a(Notification notification) {
        Game.i.assertInMainThread();
        if (notification.status != 3) {
            notification.status = 3;
            notification.clearActions();
            notification.addAction(Actions.sequence(Actions.parallel(Actions.moveBy(-500.0f, 0.0f, 0.2f), Actions.alpha(0.0f, 0.2f)), Actions.removeActor(), Actions.run(() -> {
                this.n.removeValue(notification, true);
                Game.i.assertInMainThread();
            })));
        }
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public void hide() {
        setVisible(false);
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public boolean isPersistent() {
        return true;
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public void preRender(float f) {
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public void postRender(float f) {
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/Notifications$Notification.class */
    public class Notification extends Group {
        public static final int STATUS_QUEUED = 0;
        public static final int STATUS_SHOWING = 1;
        public static final int STATUS_VISIBLE = 2;
        public static final int STATUS_HIDING = 3;
        public final float notificationHeight;
        public int status;

        @Null
        public String id;
        private Image k;
        public Image iconImage;

        /* synthetic */ Notification(Notifications notifications, Table table, Drawable drawable, Color color, byte b2) {
            this(notifications, table, drawable, color);
        }

        /* synthetic */ Notification(Notifications notifications, CharSequence charSequence, Drawable drawable, Color color, byte b2) {
            this(notifications, charSequence, drawable, color);
        }

        private Notification(Notifications notifications, Table table, Drawable drawable, Color color) {
            this.status = 0;
            setWidth(460.0f);
            setTouchable(Touchable.disabled);
            table.setWidth(340.0f);
            table.layout();
            table.pack();
            table.setWidth(340.0f);
            table.setPosition(106.0f, 20.0f);
            if (table.getHeight() < 48.0f) {
                table.setHeight(48.0f);
            }
            float height = table.getHeight() + 40.0f;
            this.iconImage = new Image(drawable);
            this.iconImage.setSize(48.0f, 48.0f);
            this.iconImage.setPosition(40.0f, 20.0f + (table.getHeight() - 48.0f));
            this.iconImage.setTouchable(Touchable.disabled);
            QuadActor quadActor = new QuadActor(color, new float[]{0.0f, 0.0f, 0.0f, height, 460.0f, height, 451.0f, 0.0f});
            quadActor.setTouchable(Touchable.disabled);
            this.notificationHeight = height + 2.0f;
            setHeight(this.notificationHeight);
            addActor(quadActor);
            addActor(this.iconImage);
            addActor(table);
            this.k = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
            this.k.setTouchable(Touchable.disabled);
            addActor(this.k);
            this.k.setVisible(false);
            setVisible(false);
        }

        private Notification(Notifications notifications, CharSequence charSequence, Drawable drawable, Color color) {
            this.status = 0;
            setWidth(460.0f);
            setTouchable(Touchable.disabled);
            Label label = new Label(charSequence, notifications.l);
            label.setWrap(true);
            label.setWidth(340.0f);
            label.setAlignment(10);
            label.layout();
            label.pack();
            label.setWidth(340.0f);
            label.setPosition(106.0f, 20.0f);
            if (label.getHeight() < 48.0f) {
                label.setHeight(48.0f);
                label.setAlignment(8);
            }
            float height = label.getHeight() + 40.0f;
            Actor image = new Image(drawable);
            image.setSize(48.0f, 48.0f);
            image.setPosition(40.0f, 20.0f + (label.getHeight() - 48.0f));
            Actor quadActor = new QuadActor(color, new float[]{0.0f, 0.0f, 0.0f, height, 460.0f, height, 451.0f, 0.0f});
            this.notificationHeight = height + 2.0f;
            setHeight(this.notificationHeight);
            addActor(quadActor);
            addActor(image);
            addActor(label);
            this.k = new Image(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
            addActor(this.k);
            this.k.setVisible(false);
            setVisible(false);
        }

        public void showProgress(float f, Color color) {
            if (f <= 0.0f) {
                this.k.setVisible(false);
                return;
            }
            this.k.setColor(color);
            this.k.setSize(f * 451.0f, 6.0f);
            this.k.setVisible(true);
        }

        public void hide(float f) {
            Array<Action> actions = getActions();
            if (actions.size != 0) {
                Array.ArrayIterator<Action> it = ((SequenceAction) actions.first()).getActions().iterator();
                while (it.hasNext()) {
                    Action next = it.next();
                    if (next instanceof DelayAction) {
                        ((DelayAction) next).setDuration(f);
                        ((DelayAction) next).setTime(0.0f);
                    }
                }
            }
        }
    }
}
