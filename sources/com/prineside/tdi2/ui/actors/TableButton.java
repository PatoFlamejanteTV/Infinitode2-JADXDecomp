package com.prineside.tdi2.ui.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.enums.StaticSoundType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.ui.Cell;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.utils.MaterialColor;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/TableButton.class */
public class TableButton extends Table {
    private Runnable k;
    private Runnable l;
    public static final Color DEFAULT_NORMAL_BG_COLOR = Color.WHITE.cpy();
    public static final Color DEFAULT_HOVER_BG_COLOR = Color.WHITE.cpy();
    public static final Color DEFAULT_ACTIVE_BG_COLOR = Color.WHITE.cpy();
    public static final Color DEFAULT_DISABLED_BG_COLOR = Color.WHITE.cpy();
    private boolean n;
    private boolean o;
    private boolean p;
    private boolean q;
    private final Image r;
    public boolean clickableWhenDisabled;
    private Drawable s;
    private Drawable t;
    private Drawable u;
    private Drawable v;
    private final Color w;
    private final Color x;
    private final Color y;
    private final Color z;
    private final Color A;
    private final Color B;
    private final Color C;
    private final Color D;
    private boolean E;

    public TableButton(Runnable runnable) {
        this(runnable, null);
    }

    public TableButton(Runnable runnable, Runnable runnable2) {
        this.n = false;
        this.o = false;
        this.p = true;
        this.q = false;
        this.w = DEFAULT_NORMAL_BG_COLOR.cpy();
        this.x = DEFAULT_HOVER_BG_COLOR.cpy();
        this.y = DEFAULT_ACTIVE_BG_COLOR.cpy();
        this.z = DEFAULT_DISABLED_BG_COLOR.cpy();
        this.A = Color.WHITE.cpy();
        this.B = Color.WHITE.cpy();
        this.C = Color.WHITE.cpy();
        this.D = MaterialColor.GREY.P500.cpy();
        this.k = runnable;
        this.l = runnable2;
        this.r = new Image();
        this.r.setFillParent(true);
        addActor(this.r);
        setTouchable(Touchable.enabled);
        addListener(new ClickListener() { // from class: com.prineside.tdi2.ui.actors.TableButton.1

            /* renamed from: b, reason: collision with root package name */
            private final Timer.Task f3239b = new Timer.Task() { // from class: com.prineside.tdi2.ui.actors.TableButton.1.1
                @Override // com.badlogic.gdx.utils.Timer.Task, java.lang.Runnable
                public void run() {
                    a();
                    if (TableButton.this.l != null) {
                        TableButton.this.l.run();
                        if (!TableButton.this.q) {
                            Game.i.soundManager.playStatic(StaticSoundType.BUTTON);
                        }
                    }
                }
            };
            private ButtonHoldHint c;

            /* JADX INFO: Access modifiers changed from: private */
            public void a() {
                if (this.c != null) {
                    this.c.disappearing = true;
                    this.c = null;
                }
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent, float f, float f2) {
                if ((TableButton.this.clickableWhenDisabled || TableButton.this.p) && TableButton.this.k != null) {
                    TableButton.this.k.run();
                    if (!TableButton.this.q) {
                        Game.i.soundManager.playStatic(StaticSoundType.BUTTON);
                    }
                }
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f, float f2, int i, int i2) {
                if (i2 == 0) {
                    TableButton.this.n = true;
                    TableButton.this.d();
                    if (this.f3239b.isScheduled()) {
                        this.f3239b.cancel();
                    }
                    if (TableButton.this.l != null) {
                        Timer.schedule(this.f3239b, 0.5f);
                        a();
                        this.c = new ButtonHoldHint(f, f2, 0.5f);
                        TableButton.this.addActor(this.c);
                    }
                    boolean z = super.touchDown(inputEvent, f, f2, i, i2);
                    if (z) {
                        inputEvent.halt();
                    }
                    return z;
                }
                if (i2 == 1 && TableButton.this.l != null) {
                    TableButton.this.l.run();
                    if (!TableButton.this.q) {
                        Game.i.soundManager.playStatic(StaticSoundType.BUTTON);
                        return false;
                    }
                    return false;
                }
                return false;
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public void touchUp(InputEvent inputEvent, float f, float f2, int i, int i2) {
                TableButton.this.n = false;
                TableButton.this.d();
                if (TableButton.this.l != null && !this.f3239b.isScheduled()) {
                    cancel();
                }
                this.f3239b.cancel();
                a();
                super.touchUp(inputEvent, f, f2, i, i2);
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public void enter(InputEvent inputEvent, float f, float f2, int i, Actor actor) {
                if (i == -1) {
                    TableButton.this.o = true;
                    TableButton.this.d();
                }
                super.enter(inputEvent, f, f2, i, actor);
            }

            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public void exit(InputEvent inputEvent, float f, float f2, int i, Actor actor) {
                if (i == -1) {
                    TableButton.this.o = false;
                    TableButton.this.d();
                }
                super.exit(inputEvent, f, f2, i, actor);
            }
        });
        d();
    }

    public void setEnabled(boolean z) {
        if (this.p != z) {
            this.p = z;
            d();
        }
    }

    public void setClickHandler(Runnable runnable) {
        this.k = runnable;
    }

    public void setHoldHandler(Runnable runnable) {
        this.l = runnable;
    }

    public TableButton setMuted(boolean z) {
        this.q = z;
        return this;
    }

    @Override // com.prineside.tdi2.scene2d.ui.Table, com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public void layout() {
        super.layout();
        if (!this.E) {
            this.E = true;
            d();
        }
    }

    public TableButton setBackgroundDrawable(Drawable drawable) {
        this.s = drawable;
        this.t = null;
        this.u = null;
        this.v = null;
        d();
        return this;
    }

    public TableButton setBackgroundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        this.s = drawable;
        this.t = drawable2;
        this.u = drawable3;
        this.v = drawable4;
        d();
        return this;
    }

    public TableButton setRectBackground() {
        return setBackgroundDrawable(Game.i.assetManager.getDrawable(AssetManager.BLANK_REGION_NAME));
    }

    public TableButton setContentColors(Color color, Color color2, Color color3, Color color4) {
        if (color != null) {
            this.A.set(color);
        }
        if (color2 != null) {
            this.C.set(color3);
        }
        if (color3 != null) {
            this.B.set(color2);
        }
        if (color4 != null) {
            this.D.set(color4);
        }
        d();
        return this;
    }

    public TableButton setBackgroundColors(Color color, Color color2, Color color3, Color color4) {
        this.w.set(color == null ? DEFAULT_NORMAL_BG_COLOR : color);
        this.y.set(color3 == null ? DEFAULT_ACTIVE_BG_COLOR : color3);
        this.x.set(color2 == null ? DEFAULT_HOVER_BG_COLOR : color2);
        this.z.set(color4 == null ? DEFAULT_DISABLED_BG_COLOR : color4);
        d();
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (this.p) {
            if (this.n) {
                this.r.setDrawable(this.t != null ? this.t : this.s);
                this.r.setColor(this.y);
            } else if (this.o) {
                this.r.setDrawable(this.u != null ? this.u : this.s);
                this.r.setColor(this.x);
            } else {
                this.r.setDrawable(this.s);
                this.r.setColor(this.w);
            }
        } else {
            this.r.setDrawable(this.v != null ? this.v : this.s);
            this.r.setColor(this.z);
        }
        Array.ArrayIterator<Cell> it = getCells().iterator();
        while (it.hasNext()) {
            Actor actor = it.next().getActor();
            if (actor != null) {
                if (this.p) {
                    if (this.n) {
                        actor.setColor(this.C);
                    } else if (this.o) {
                        actor.setColor(this.B);
                    } else {
                        actor.setColor(this.A);
                    }
                } else {
                    actor.setColor(this.D);
                }
            }
        }
    }
}
