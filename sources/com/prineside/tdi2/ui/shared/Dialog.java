package com.prineside.tdi2.ui.shared;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.managers.UiManager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.ui.actors.ItemCell;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.utils.InputVoid;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.logging.TLog;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/shared/Dialog.class */
public final class Dialog extends UiManager.UiComponent.Adapter {

    /* renamed from: a, reason: collision with root package name */
    private static final Color f3522a;

    /* renamed from: b, reason: collision with root package name */
    private static final Color f3523b;
    private static final Color c;
    private static final Color d;
    private final Label f;
    private final Group g;
    private final Image h;
    private final Image i;
    private final Label j;
    private Runnable k;
    private final Group l;
    private final Image m;
    private final Image n;
    private final Label o;
    private Runnable p;
    private final Image q;
    private final Image r;
    private final Image s;
    private final Table t;
    public boolean ignoreEscForFrame;
    private boolean w;
    private boolean x;
    private final UiManager.UiLayer e = Game.i.uiManager.addLayer(UiManager.MainUiLayer.OVERLAY, 201, "Dialog main");
    private String u = "default";
    private float v = 0.0f;
    private final Runnable y = () -> {
        hide();
    };

    static {
        TLog.forClass(Dialog.class);
        new Color(218959247);
        f3522a = MaterialColor.LIGHT_BLUE.P700;
        f3523b = MaterialColor.LIGHT_BLUE.P600;
        c = MaterialColor.LIGHT_BLUE.P800;
        d = MaterialColor.GREY.P800;
    }

    public static Dialog i() {
        return (Dialog) Game.i.uiManager.getComponent(Dialog.class);
    }

    public Dialog() {
        Table table = this.e.getTable();
        Group group = new Group();
        group.setTransform(false);
        group.setTouchable(Touchable.enabled);
        group.addListener(new InputVoid());
        table.add((Table) group).expand().bottom().right().padBottom(293.0f).size(651.0f, 456.0f);
        this.q = new Image(Game.i.assetManager.getDrawable("ui-dialog-background-1"));
        this.q.setPosition(115.0f, 87.0f);
        this.q.setSize(566.0f, 179.0f);
        group.addActor(this.q);
        this.r = new Image(Game.i.assetManager.getDrawable("ui-dialog-background-2"));
        this.r.setPosition(115.0f, 106.0f);
        this.r.setSize(514.0f, 320.0f);
        group.addActor(this.r);
        this.s = new Image(Game.i.assetManager.getDrawable("ui-dialog-background-3"));
        this.s.setPosition(0.0f, 136.0f);
        this.s.setSize(611.0f, 320.0f);
        group.addActor(this.s);
        this.t = new Table();
        this.t.setPosition(-691.0f, 0.0f);
        this.t.setSize(651.0f, 456.0f);
        group.addActor(this.t);
        this.f = new Label("", new Label.LabelStyle(Game.i.assetManager.getFont(30), Color.WHITE));
        this.f.setWrap(true);
        this.f.setAlignment(1);
        this.f.setPosition(78.0f, 185.0f);
        this.f.setSize(489.0f, 240.0f);
        group.addActor(this.f);
        this.g = new Group();
        this.g.setName("dialog_left_button");
        this.g.setTransform(false);
        this.g.setPosition(65.0f, 0.0f);
        this.g.setSize(265.0f, 139.0f);
        group.addActor(this.g);
        this.h = new Image(Game.i.assetManager.getDrawable("ui-dialog-button-left"));
        this.h.setColor(f3522a);
        this.h.setSize(265.0f, 139.0f);
        this.g.addActor(this.h);
        this.g.setTouchable(Touchable.enabled);
        this.g.addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.shared.Dialog.1

            /* renamed from: a, reason: collision with root package name */
            private boolean f3524a = false;

            /* renamed from: b, reason: collision with root package name */
            private boolean f3525b = false;

            private void a() {
                if (Dialog.this.w) {
                    Dialog.this.h.setColor(Dialog.d);
                    return;
                }
                if (this.f3524a) {
                    Dialog.this.h.setColor(Dialog.c);
                } else if (this.f3525b) {
                    Dialog.this.h.setColor(Dialog.f3523b);
                } else {
                    Dialog.this.h.setColor(Dialog.f3522a);
                }
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                if (Dialog.this.w) {
                    return;
                }
                Runnable runnable = Dialog.this.k;
                Dialog.this.hide();
                if (runnable != null) {
                    runnable.run();
                    Game.i.soundManager.playStatic(StaticSoundType.BUTTON);
                }
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f, float f2, int i, int i2) {
                this.f3524a = true;
                a();
                return super.touchDown(inputEvent, f, f2, i, i2);
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public void touchUp(InputEvent inputEvent, float f, float f2, int i, int i2) {
                this.f3524a = false;
                a();
                super.touchUp(inputEvent, f, f2, i, i2);
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public void enter(InputEvent inputEvent, float f, float f2, int i, Actor actor) {
                if (i == -1) {
                    this.f3525b = true;
                    a();
                }
                super.enter(inputEvent, f, f2, i, actor);
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public void exit(InputEvent inputEvent, float f, float f2, int i, Actor actor) {
                if (i == -1) {
                    this.f3525b = false;
                    a();
                }
                super.exit(inputEvent, f, f2, i, actor);
            }
        });
        this.i = new Image(Game.i.assetManager.getDrawable("icon-times"));
        this.i.setPosition(100.0f, 46.0f);
        this.i.setSize(64.0f, 64.0f);
        this.g.addActor(this.i);
        this.j = new Label("", Game.i.assetManager.getLabelStyle(30));
        this.j.setPosition(100.0f, 46.0f);
        this.j.setSize(64.0f, 64.0f);
        this.j.setAlignment(1);
        this.j.setVisible(false);
        this.j.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        this.g.addActor(this.j);
        this.l = new Group();
        this.l.setName("dialog_right_button");
        this.l.setPosition(344.0f, 14.0f);
        this.l.setSize(259.0f, 141.0f);
        group.addActor(this.l);
        this.m = new Image(Game.i.assetManager.getDrawable("ui-dialog-button-right"));
        this.m.setColor(f3522a);
        this.m.setSize(259.0f, 141.0f);
        this.l.addActor(this.m);
        this.l.setTouchable(Touchable.enabled);
        this.l.addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.shared.Dialog.2

            /* renamed from: a, reason: collision with root package name */
            private boolean f3526a = false;

            /* renamed from: b, reason: collision with root package name */
            private boolean f3527b = false;

            private void a() {
                if (Dialog.this.x) {
                    Dialog.this.m.setColor(Dialog.d);
                    return;
                }
                if (this.f3526a) {
                    Dialog.this.m.setColor(Dialog.c);
                } else if (this.f3527b) {
                    Dialog.this.m.setColor(Dialog.f3523b);
                } else {
                    Dialog.this.m.setColor(Dialog.f3522a);
                }
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                if (Dialog.this.x) {
                    return;
                }
                Runnable runnable = Dialog.this.p;
                Dialog.this.hide();
                if (runnable != null) {
                    runnable.run();
                    Game.i.soundManager.playStatic(StaticSoundType.BUTTON);
                }
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f, float f2, int i, int i2) {
                this.f3526a = true;
                a();
                return super.touchDown(inputEvent, f, f2, i, i2);
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public void touchUp(InputEvent inputEvent, float f, float f2, int i, int i2) {
                this.f3526a = false;
                a();
                super.touchUp(inputEvent, f, f2, i, i2);
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public void enter(InputEvent inputEvent, float f, float f2, int i, Actor actor) {
                if (i == -1) {
                    this.f3527b = true;
                    a();
                }
                super.enter(inputEvent, f, f2, i, actor);
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public void exit(InputEvent inputEvent, float f, float f2, int i, Actor actor) {
                if (i == -1) {
                    this.f3527b = false;
                    a();
                }
                super.exit(inputEvent, f, f2, i, actor);
            }
        });
        this.n = new Image(Game.i.assetManager.getDrawable("icon-times"));
        this.n.setPosition(81.0f, 42.0f);
        this.n.setSize(64.0f, 64.0f);
        this.l.addActor(this.n);
        this.o = new Label("", Game.i.assetManager.getLabelStyle(30));
        this.o.setPosition(81.0f, 42.0f);
        this.o.setSize(64.0f, 64.0f);
        this.o.setAlignment(1);
        this.o.setColor(1.0f, 1.0f, 1.0f, 0.56f);
        this.o.setVisible(false);
        this.l.addActor(this.o);
        this.e.getTable().setVisible(false);
    }

    public final void setItemsHintForVisibleDialog(Array<ItemStack> array) {
        this.t.clear();
        int i = 0;
        int i2 = 0;
        Array.ArrayIterator<ItemStack> it = array.iterator();
        while (it.hasNext()) {
            final ItemStack next = it.next();
            ItemCell itemCell = new ItemCell();
            itemCell.setItemStack(next);
            itemCell.setColRow(i, i2);
            itemCell.addListener(new ClickListener(this) { // from class: com.prineside.tdi2.ui.shared.Dialog.3
                @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                public void clicked(InputEvent inputEvent, float f, float f2) {
                    ItemDescriptionDialog.i().showWithCount(next.getItem(), next.getCount());
                }
            });
            this.t.add((Table) itemCell);
            i++;
            if (i == 5) {
                i = 0;
                i2++;
                this.t.row();
            }
        }
    }

    private void e() {
        this.q.clearActions();
        this.r.clearActions();
        this.s.clearActions();
        this.f.clearActions();
        this.g.clearActions();
        this.l.clearActions();
        this.e.getTable().clearActions();
    }

    private void f() {
        this.q.setVisible(false);
        this.r.setVisible(false);
        this.s.setVisible(false);
        this.f.setVisible(false);
        this.g.setVisible(false);
        this.l.setVisible(false);
    }

    private void g() {
        e();
        f();
        this.q.setVisible(true);
        if (Game.i.settingsManager.isUiAnimationsEnabled()) {
            this.r.addAction(Actions.sequence(Actions.delay(0.05f), Actions.visible(true)));
            this.s.addAction(Actions.sequence(Actions.delay(0.1f), Actions.visible(true)));
            this.f.addAction(Actions.sequence(Actions.delay(0.1f), Actions.visible(true)));
            if (this.k != null) {
                this.g.addAction(Actions.sequence(Actions.delay(0.1f), Actions.visible(true)));
            }
            if (this.p != null) {
                this.l.addAction(Actions.sequence(Actions.delay(0.1f), Actions.visible(true)));
            }
        } else {
            this.r.setVisible(true);
            this.s.setVisible(true);
            this.f.setVisible(true);
            if (this.k != null) {
                this.g.setVisible(true);
            }
            if (this.p != null) {
                this.l.setVisible(true);
            }
        }
        DarkOverlay.i().addCallerOverlayLayer("Dialog", this.e.zIndex - 1, () -> {
            return false;
        });
        this.e.getTable().setVisible(true);
        j();
    }

    public final boolean isVisible() {
        return this.e.getTable().isVisible();
    }

    public final String getLastConfirmId() {
        return this.u;
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent.Adapter, com.prineside.tdi2.managers.UiManager.UiComponent
    public final void postRender(float f) {
        if (isVisible()) {
            if (this.v != 0.0f) {
                this.v -= f;
                if (this.v < 0.0f) {
                    this.v = 0.0f;
                }
                j();
            }
            if (!this.ignoreEscForFrame && (Gdx.input.isKeyJustPressed(4) || Gdx.input.isKeyJustPressed(111))) {
                Runnable runnable = this.p;
                hide();
                if (runnable != null) {
                    runnable.run();
                    Game.i.soundManager.playStatic(StaticSoundType.BUTTON);
                }
            } else if ((Gdx.input.isKeyJustPressed(66) || Gdx.input.isKeyJustPressed(160)) && !h()) {
                Runnable runnable2 = this.k;
                hide();
                if (runnable2 != null) {
                    runnable2.run();
                    Game.i.soundManager.playStatic(StaticSoundType.BUTTON);
                }
            }
        }
        this.ignoreEscForFrame = false;
    }

    public final Table getHintTable() {
        return this.t;
    }

    @Override // com.prineside.tdi2.managers.UiManager.UiComponent
    public final void hide() {
        e();
        this.k = null;
        this.p = null;
        this.t.clear();
        this.v = 0.0f;
        this.g.setVisible(false);
        this.l.setVisible(false);
        this.f.setVisible(false);
        this.s.setVisible(false);
        if (Game.i.settingsManager.isUiAnimationsEnabled()) {
            this.r.addAction(Actions.sequence(Actions.delay(0.05f), Actions.visible(false)));
            this.q.addAction(Actions.sequence(Actions.delay(0.1f), Actions.visible(false)));
            this.e.getTable().addAction(Actions.sequence(Actions.delay(0.1f), Actions.visible(false)));
        } else {
            this.r.setVisible(false);
            this.q.setVisible(false);
            this.e.getTable().setVisible(false);
        }
        DarkOverlay.i().removeCaller("Dialog");
    }

    public final void showConfirm(CharSequence charSequence, Runnable runnable) {
        showConfirmWithId(charSequence, runnable, "default");
    }

    public final void showConfirmWithId(CharSequence charSequence, Runnable runnable, String str) {
        this.f.setText(charSequence);
        this.i.setDrawable(Game.i.assetManager.getDrawable("icon-check"));
        this.k = runnable;
        this.n.setDrawable(Game.i.assetManager.getDrawable("icon-times"));
        this.p = this.y;
        this.u = str;
        g();
    }

    public final void showConfirmWithCallbacks(CharSequence charSequence, Runnable runnable, Runnable runnable2) {
        showConfirmWithCallbacksAndId(charSequence, runnable, runnable2, "default");
    }

    public final void showConfirmWithCallbacksAndId(CharSequence charSequence, Runnable runnable, Runnable runnable2, String str) {
        this.f.setText(charSequence);
        this.i.setDrawable(Game.i.assetManager.getDrawable("icon-check"));
        this.k = runnable;
        this.n.setDrawable(Game.i.assetManager.getDrawable("icon-times"));
        this.p = runnable2;
        this.u = str;
        g();
    }

    public final void showAlert(CharSequence charSequence) {
        this.f.setText(charSequence);
        this.k = null;
        this.n.setDrawable(Game.i.assetManager.getDrawable("icon-check"));
        this.p = this.y;
        g();
    }

    public final void showAlertWithConfirmCallback(CharSequence charSequence, Runnable runnable) {
        this.f.setText(charSequence);
        this.k = null;
        this.n.setDrawable(Game.i.assetManager.getDrawable("icon-check"));
        this.p = runnable;
        g();
    }

    private boolean h() {
        return this.v > 0.0f;
    }

    private void j() {
        if (h()) {
            String str = MathUtils.floor(this.v) + "." + (MathUtils.ceil(this.v * 10.0f) % 10) + Game.i.localeManager.i18n.get("TIME_CHAR_SECOND");
            if (this.k == null) {
                this.w = false;
                this.x = true;
                this.o.setText(str);
            } else {
                this.w = true;
                this.x = false;
                this.j.setText(str);
            }
        } else {
            this.w = false;
            this.x = false;
        }
        if (!this.w) {
            this.h.setColor(f3522a);
            this.j.setVisible(false);
            this.i.setVisible(true);
        } else {
            this.h.setColor(d);
            this.j.setVisible(true);
            this.i.setVisible(false);
        }
        if (!this.x) {
            this.m.setColor(f3522a);
            this.o.setVisible(false);
            this.n.setVisible(true);
        } else {
            this.m.setColor(d);
            this.o.setVisible(true);
            this.n.setVisible(false);
        }
    }

    public final void makeConfirmButtonDisabled(int i) {
        if (i < 0) {
            i = 0;
        }
        this.v = i;
        j();
    }
}
