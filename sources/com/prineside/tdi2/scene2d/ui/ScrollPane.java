package com.prineside.tdi2.scene2d.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Null;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Event;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.InputListener;
import com.prineside.tdi2.scene2d.Stage;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.utils.ActorGestureListener;
import com.prineside.tdi2.scene2d.utils.Cullable;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.scene2d.utils.Layout;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/ScrollPane.class */
public class ScrollPane extends WidgetGroup {
    private ScrollPaneStyle E;
    private Actor F;
    final Rectangle k;
    final Rectangle l;
    final Rectangle m;
    final Rectangle n;
    final Rectangle o;
    private final Rectangle G;
    private ActorGestureListener H;
    boolean p;
    boolean q;
    private boolean I;
    private boolean J;
    float r;
    float s;
    private float K;
    private float L;
    private float M;
    private float N;
    boolean t;
    boolean u;
    final Vector2 v;
    private boolean O;
    private boolean P;
    boolean w;
    float x;
    private float Q;
    private float R;
    private float S;
    boolean y;
    boolean z;
    float A;
    float B;
    private float T;
    private float U;
    private boolean V;
    private boolean W;
    private float X;
    private float Y;
    private float Z;
    private boolean aa;
    private boolean ab;
    private boolean ac;
    boolean C;
    private boolean ad;
    private boolean ae;
    private boolean af;
    int D;

    public ScrollPane(@Null Actor actor) {
        this(actor, new ScrollPaneStyle());
    }

    public ScrollPane(@Null Actor actor, ScrollPaneStyle scrollPaneStyle) {
        this.k = new Rectangle();
        this.l = new Rectangle();
        this.m = new Rectangle();
        this.n = new Rectangle();
        this.o = new Rectangle();
        this.G = new Rectangle();
        this.I = true;
        this.J = true;
        this.v = new Vector2();
        this.O = true;
        this.P = true;
        this.w = true;
        this.Q = 1.0f;
        this.S = 1.0f;
        this.y = true;
        this.z = true;
        this.A = 1.0f;
        this.V = true;
        this.W = true;
        this.X = 50.0f;
        this.Y = 30.0f;
        this.Z = 200.0f;
        this.ad = true;
        this.af = true;
        this.D = -1;
        if (scrollPaneStyle == null) {
            throw new IllegalArgumentException("style cannot be null.");
        }
        this.E = scrollPaneStyle;
        setActor(actor);
        setSize(150.0f, 150.0f);
        g();
        this.H = h();
        addListener(this.H);
        i();
    }

    private void g() {
        addCaptureListener(new InputListener() { // from class: com.prineside.tdi2.scene2d.ui.ScrollPane.1

            /* renamed from: a, reason: collision with root package name */
            private float f2661a;

            @Override // com.prineside.tdi2.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f, float f2, int i, int i2) {
                if (ScrollPane.this.D != -1) {
                    return false;
                }
                if (i == 0 && i2 != 0) {
                    return false;
                }
                if (ScrollPane.this.getStage() != null) {
                    ScrollPane.this.getStage().setScrollFocus(ScrollPane.this);
                }
                if (!ScrollPane.this.z) {
                    ScrollPane.this.setScrollbarsVisible(true);
                }
                if (ScrollPane.this.x == 0.0f) {
                    return false;
                }
                if (ScrollPane.this.w && ScrollPane.this.p && ScrollPane.this.l.contains(f, f2)) {
                    inputEvent.stop();
                    ScrollPane.this.setScrollbarsVisible(true);
                    if (ScrollPane.this.m.contains(f, f2)) {
                        ScrollPane.this.v.set(f, f2);
                        this.f2661a = ScrollPane.this.m.x;
                        ScrollPane.this.t = true;
                        ScrollPane.this.D = i;
                        return true;
                    }
                    ScrollPane.this.setScrollX(ScrollPane.this.r + (ScrollPane.this.k.width * (f < ScrollPane.this.m.x ? -1 : 1)));
                    return true;
                }
                if (ScrollPane.this.w && ScrollPane.this.q && ScrollPane.this.n.contains(f, f2)) {
                    inputEvent.stop();
                    ScrollPane.this.setScrollbarsVisible(true);
                    if (ScrollPane.this.o.contains(f, f2)) {
                        ScrollPane.this.v.set(f, f2);
                        this.f2661a = ScrollPane.this.o.y;
                        ScrollPane.this.u = true;
                        ScrollPane.this.D = i;
                        return true;
                    }
                    ScrollPane.this.setScrollY(ScrollPane.this.s + (ScrollPane.this.k.height * (f2 < ScrollPane.this.o.y ? 1 : -1)));
                    return true;
                }
                return false;
            }

            @Override // com.prineside.tdi2.scene2d.InputListener
            public void touchUp(InputEvent inputEvent, float f, float f2, int i, int i2) {
                if (i != ScrollPane.this.D) {
                    return;
                }
                ScrollPane.this.cancel();
            }

            @Override // com.prineside.tdi2.scene2d.InputListener
            public void touchDragged(InputEvent inputEvent, float f, float f2, int i) {
                if (i != ScrollPane.this.D) {
                    return;
                }
                if (ScrollPane.this.t) {
                    float f3 = this.f2661a + (f - ScrollPane.this.v.x);
                    this.f2661a = f3;
                    float min = Math.min((ScrollPane.this.l.x + ScrollPane.this.l.width) - ScrollPane.this.m.width, Math.max(ScrollPane.this.l.x, f3));
                    float f4 = ScrollPane.this.l.width - ScrollPane.this.m.width;
                    if (f4 != 0.0f) {
                        ScrollPane.this.setScrollPercentX((min - ScrollPane.this.l.x) / f4);
                    }
                    ScrollPane.this.v.set(f, f2);
                    return;
                }
                if (ScrollPane.this.u) {
                    float f5 = this.f2661a + (f2 - ScrollPane.this.v.y);
                    this.f2661a = f5;
                    float min2 = Math.min((ScrollPane.this.n.y + ScrollPane.this.n.height) - ScrollPane.this.o.height, Math.max(ScrollPane.this.n.y, f5));
                    float f6 = ScrollPane.this.n.height - ScrollPane.this.o.height;
                    if (f6 != 0.0f) {
                        ScrollPane.this.setScrollPercentY(1.0f - ((min2 - ScrollPane.this.n.y) / f6));
                    }
                    ScrollPane.this.v.set(f, f2);
                }
            }

            @Override // com.prineside.tdi2.scene2d.InputListener
            public boolean mouseMoved(InputEvent inputEvent, float f, float f2) {
                if (!ScrollPane.this.z) {
                    ScrollPane.this.setScrollbarsVisible(true);
                    return false;
                }
                return false;
            }
        });
    }

    private ActorGestureListener h() {
        return new ActorGestureListener() { // from class: com.prineside.tdi2.scene2d.ui.ScrollPane.2
            @Override // com.prineside.tdi2.scene2d.utils.ActorGestureListener
            public void pan(InputEvent inputEvent, float f, float f2, float f3, float f4) {
                ScrollPane.this.setScrollbarsVisible(true);
                if (!ScrollPane.this.p) {
                    f3 = 0.0f;
                }
                if (!ScrollPane.this.q) {
                    f4 = 0.0f;
                }
                ScrollPane.this.r -= f3;
                ScrollPane.this.s += f4;
                ScrollPane.this.d();
                if (ScrollPane.this.y) {
                    if (f3 != 0.0f || f4 != 0.0f) {
                        ScrollPane.this.cancelTouchFocus();
                    }
                }
            }

            @Override // com.prineside.tdi2.scene2d.utils.ActorGestureListener
            public void fling(InputEvent inputEvent, float f, float f2, int i) {
                float f3 = (Math.abs(f) <= 150.0f || !ScrollPane.this.p) ? 0.0f : f;
                float f4 = (Math.abs(f2) <= 150.0f || !ScrollPane.this.q) ? 0.0f : -f2;
                if (f3 != 0.0f || f4 != 0.0f) {
                    if (ScrollPane.this.y) {
                        ScrollPane.this.cancelTouchFocus();
                    }
                    ScrollPane.this.fling(ScrollPane.this.A, f3, f4);
                }
            }

            @Override // com.prineside.tdi2.scene2d.utils.ActorGestureListener, com.prineside.tdi2.scene2d.EventListener
            public boolean handle(Event event) {
                if (super.handle(event)) {
                    if (((InputEvent) event).getType() == InputEvent.Type.touchDown) {
                        ScrollPane.this.B = 0.0f;
                        return true;
                    }
                    return true;
                }
                if ((event instanceof InputEvent) && ((InputEvent) event).isTouchFocusCancel()) {
                    ScrollPane.this.cancel();
                    return false;
                }
                return false;
            }
        };
    }

    private void i() {
        addListener(new InputListener() { // from class: com.prineside.tdi2.scene2d.ui.ScrollPane.3
            @Override // com.prineside.tdi2.scene2d.InputListener
            public boolean scrolled(InputEvent inputEvent, float f, float f2, float f3, float f4) {
                inputEvent.cancel();
                ScrollPane.this.setScrollbarsVisible(true);
                if (ScrollPane.this.q || ScrollPane.this.p) {
                    if (ScrollPane.this.q) {
                        if (!ScrollPane.this.p && f4 == 0.0f) {
                            f4 = f3;
                        }
                    } else if (ScrollPane.this.p && f3 == 0.0f) {
                        f3 = f4;
                    }
                    ScrollPane.this.setScrollY(ScrollPane.this.s + (ScrollPane.this.f() * f4));
                    ScrollPane.this.setScrollX(ScrollPane.this.r + (ScrollPane.this.e() * f3));
                    return true;
                }
                return false;
            }
        });
    }

    public void setScrollbarsVisible(boolean z) {
        if (z) {
            this.x = this.Q;
            this.R = this.S;
        } else {
            this.x = 0.0f;
            this.R = 0.0f;
        }
    }

    public void cancelTouchFocus() {
        Stage stage = getStage();
        if (stage != null) {
            stage.cancelTouchFocusExcept(this.H, this);
        }
    }

    public void cancel() {
        this.D = -1;
        this.t = false;
        this.u = false;
        this.H.getGestureDetector().cancel();
    }

    final void d() {
        if (this.ad) {
            a(this.V ? MathUtils.clamp(this.r, -this.X, this.M + this.X) : MathUtils.clamp(this.r, 0.0f, this.M));
            b(this.W ? MathUtils.clamp(this.s, -this.X, this.N + this.X) : MathUtils.clamp(this.s, 0.0f, this.N));
        }
    }

    public void setStyle(ScrollPaneStyle scrollPaneStyle) {
        if (scrollPaneStyle == null) {
            throw new IllegalArgumentException("style cannot be null.");
        }
        this.E = scrollPaneStyle;
        invalidateHierarchy();
    }

    public ScrollPaneStyle getStyle() {
        return this.E;
    }

    @Override // com.prineside.tdi2.scene2d.Group, com.prineside.tdi2.scene2d.Actor
    public void act(float f) {
        Stage stage;
        super.act(f);
        boolean isPanning = this.H.getGestureDetector().isPanning();
        boolean z = false;
        if (this.x > 0.0f && this.O && !isPanning && !this.t && !this.u) {
            this.R -= f;
            if (this.R <= 0.0f) {
                this.x = Math.max(0.0f, this.x - f);
            }
            z = true;
        }
        if (this.B > 0.0f) {
            setScrollbarsVisible(true);
            float f2 = this.B / this.A;
            this.r -= (this.T * f2) * f;
            this.s -= (this.U * f2) * f;
            d();
            if (this.r == (-this.X)) {
                this.T = 0.0f;
            }
            if (this.r >= this.M + this.X) {
                this.T = 0.0f;
            }
            if (this.s == (-this.X)) {
                this.U = 0.0f;
            }
            if (this.s >= this.N + this.X) {
                this.U = 0.0f;
            }
            this.B -= f;
            if (this.B <= 0.0f) {
                this.T = 0.0f;
                this.U = 0.0f;
            }
            z = true;
        }
        if (!this.P || this.B > 0.0f || isPanning || ((this.t && (!this.p || this.M / (this.l.width - this.m.width) <= this.k.width * 0.1f)) || (this.u && (!this.q || this.N / (this.n.height - this.o.height) <= this.k.height * 0.1f)))) {
            if (this.K != this.r) {
                c(this.r);
            }
            if (this.L != this.s) {
                d(this.s);
            }
        } else {
            if (this.K != this.r) {
                if (this.K < this.r) {
                    c(Math.min(this.r, this.K + Math.max(200.0f * f, (this.r - this.K) * 7.0f * f)));
                } else {
                    c(Math.max(this.r, this.K - Math.max(200.0f * f, ((this.K - this.r) * 7.0f) * f)));
                }
                z = true;
            }
            if (this.L != this.s) {
                if (this.L < this.s) {
                    d(Math.min(this.s, this.L + Math.max(200.0f * f, (this.s - this.L) * 7.0f * f)));
                } else {
                    d(Math.max(this.s, this.L - Math.max(200.0f * f, ((this.L - this.s) * 7.0f) * f)));
                }
                z = true;
            }
        }
        if (!isPanning) {
            if (this.V && this.p) {
                if (this.r < 0.0f) {
                    setScrollbarsVisible(true);
                    this.r += (this.Y + (((this.Z - this.Y) * (-this.r)) / this.X)) * f;
                    if (this.r > 0.0f) {
                        a(0.0f);
                    }
                    z = true;
                } else if (this.r > this.M) {
                    setScrollbarsVisible(true);
                    this.r -= (this.Y + (((this.Z - this.Y) * (-(this.M - this.r))) / this.X)) * f;
                    if (this.r < this.M) {
                        a(this.M);
                    }
                    z = true;
                }
            }
            if (this.W && this.q) {
                if (this.s < 0.0f) {
                    setScrollbarsVisible(true);
                    this.s += (this.Y + (((this.Z - this.Y) * (-this.s)) / this.X)) * f;
                    if (this.s > 0.0f) {
                        b(0.0f);
                    }
                    z = true;
                } else if (this.s > this.N) {
                    setScrollbarsVisible(true);
                    this.s -= (this.Y + (((this.Z - this.Y) * (-(this.N - this.s))) / this.X)) * f;
                    if (this.s < this.N) {
                        b(this.N);
                    }
                    z = true;
                }
            }
        }
        if (!z || (stage = getStage()) == null || !stage.getActionsRequestRendering()) {
            return;
        }
        Gdx.graphics.requestRendering();
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public void layout() {
        float width;
        float height;
        Drawable drawable = this.E.background;
        Drawable drawable2 = this.E.hScrollKnob;
        Drawable drawable3 = this.E.vScrollKnob;
        float f = 0.0f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        float f4 = 0.0f;
        if (drawable != null) {
            f = drawable.getLeftWidth();
            f2 = drawable.getRightWidth();
            f3 = drawable.getTopHeight();
            f4 = drawable.getBottomHeight();
        }
        float width2 = getWidth();
        float height2 = getHeight();
        this.k.set(f, f4, (width2 - f) - f2, (height2 - f3) - f4);
        if (this.F == null) {
            return;
        }
        float f5 = 0.0f;
        float f6 = 0.0f;
        if (drawable2 != null) {
            f5 = drawable2.getMinHeight();
        }
        if (this.E.hScroll != null) {
            f5 = Math.max(f5, this.E.hScroll.getMinHeight());
        }
        if (drawable3 != null) {
            f6 = drawable3.getMinWidth();
        }
        if (this.E.vScroll != null) {
            f6 = Math.max(f6, this.E.vScroll.getMinWidth());
        }
        if (this.F instanceof Layout) {
            Layout layout = (Layout) this.F;
            width = layout.getPrefWidth();
            height = layout.getPrefHeight();
        } else {
            width = this.F.getWidth();
            height = this.F.getHeight();
        }
        this.p = this.aa || (width > this.k.width && !this.ac);
        this.q = this.ab || (height > this.k.height && !this.C);
        if (!this.ae) {
            if (this.q) {
                this.k.width -= f6;
                if (!this.I) {
                    this.k.x += f6;
                }
                if (!this.p && width > this.k.width && !this.ac) {
                    this.p = true;
                }
            }
            if (this.p) {
                this.k.height -= f5;
                if (this.J) {
                    this.k.y += f5;
                }
                if (!this.q && height > this.k.height && !this.C) {
                    this.q = true;
                    this.k.width -= f6;
                    if (!this.I) {
                        this.k.x += f6;
                    }
                }
            }
        }
        float max = this.ac ? this.k.width : Math.max(this.k.width, width);
        float max2 = this.C ? this.k.height : Math.max(this.k.height, height);
        this.M = max - this.k.width;
        this.N = max2 - this.k.height;
        a(MathUtils.clamp(this.r, 0.0f, this.M));
        b(MathUtils.clamp(this.s, 0.0f, this.N));
        if (this.p) {
            if (drawable2 != null) {
                this.l.set(this.ae ? f : this.k.x, this.J ? f4 : (height2 - f3) - f5, this.k.width, f5);
                if (this.q && this.ae) {
                    this.l.width -= f6;
                    if (!this.I) {
                        this.l.x += f6;
                    }
                }
                if (this.af) {
                    this.m.width = Math.max(drawable2.getMinWidth(), (int) ((this.l.width * this.k.width) / max));
                } else {
                    this.m.width = drawable2.getMinWidth();
                }
                if (this.m.width > max) {
                    this.m.width = 0.0f;
                }
                this.m.height = drawable2.getMinHeight();
                this.m.x = this.l.x + ((int) ((this.l.width - this.m.width) * getScrollPercentX()));
                this.m.y = this.l.y;
            } else {
                this.l.set(0.0f, 0.0f, 0.0f, 0.0f);
                this.m.set(0.0f, 0.0f, 0.0f, 0.0f);
            }
        }
        if (this.q) {
            if (drawable3 != null) {
                this.n.set(this.I ? (width2 - f2) - f6 : f, this.ae ? f4 : this.k.y, f6, this.k.height);
                if (this.p && this.ae) {
                    this.n.height -= f5;
                    if (this.J) {
                        this.n.y += f5;
                    }
                }
                this.o.width = drawable3.getMinWidth();
                if (this.af) {
                    this.o.height = Math.max(drawable3.getMinHeight(), (int) ((this.n.height * this.k.height) / max2));
                } else {
                    this.o.height = drawable3.getMinHeight();
                }
                if (this.o.height > max2) {
                    this.o.height = 0.0f;
                }
                this.o.x = this.I ? (width2 - f2) - drawable3.getMinWidth() : f;
                this.o.y = this.n.y + ((int) ((this.n.height - this.o.height) * (1.0f - getScrollPercentY())));
            } else {
                this.n.set(0.0f, 0.0f, 0.0f, 0.0f);
                this.o.set(0.0f, 0.0f, 0.0f, 0.0f);
            }
        }
        j();
        if (this.F instanceof Layout) {
            this.F.setSize(max, max2);
            ((Layout) this.F).validate();
        }
    }

    private void j() {
        float f = this.k.x - (this.p ? (int) this.K : 0);
        float f2 = this.k.y - ((int) (this.q ? this.N - this.L : this.N));
        this.F.setPosition(f, f2);
        if (this.F instanceof Cullable) {
            this.G.x = this.k.x - f;
            this.G.y = this.k.y - f2;
            this.G.width = this.k.width;
            this.G.height = this.k.height;
            ((Cullable) this.F).setCullingArea(this.G);
        }
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.Group, com.prineside.tdi2.scene2d.Actor
    public void draw(Batch batch, float f) {
        if (this.F == null) {
            return;
        }
        validate();
        a(batch, b());
        if (this.p) {
            this.m.x = this.l.x + ((int) ((this.l.width - this.m.width) * getVisualScrollPercentX()));
        }
        if (this.q) {
            this.o.y = this.n.y + ((int) ((this.n.height - this.o.height) * (1.0f - getVisualScrollPercentY())));
        }
        j();
        Color color = getColor();
        float f2 = color.f889a * f;
        if (this.E.background != null) {
            batch.setColor(color.r, color.g, color.f888b, f2);
            this.E.background.draw(batch, 0.0f, 0.0f, getWidth(), getHeight());
        }
        batch.flush();
        if (clipBegin(this.k.x, this.k.y, this.k.width, this.k.height)) {
            a(batch, f);
            batch.flush();
            clipEnd();
        }
        batch.setColor(color.r, color.g, color.f888b, f2);
        if (this.O) {
            f2 *= Interpolation.fade.apply(this.x / this.Q);
        }
        a(batch, color.r, color.g, color.f888b, f2);
        a(batch);
    }

    private void a(Batch batch, float f, float f2, float f3, float f4) {
        if (f4 <= 0.0f) {
            return;
        }
        batch.setColor(f, f2, f3, f4);
        boolean z = this.p && this.m.width > 0.0f;
        boolean z2 = this.q && this.o.height > 0.0f;
        if (z) {
            if (z2 && this.E.corner != null) {
                this.E.corner.draw(batch, this.l.x + this.l.width, this.l.y, this.n.width, this.n.y);
            }
            if (this.E.hScroll != null) {
                this.E.hScroll.draw(batch, this.l.x, this.l.y, this.l.width, this.l.height);
            }
            if (this.E.hScrollKnob != null) {
                this.E.hScrollKnob.draw(batch, this.m.x, this.m.y, this.m.width, this.m.height);
            }
        }
        if (z2) {
            if (this.E.vScroll != null) {
                this.E.vScroll.draw(batch, this.n.x, this.n.y, this.n.width, this.n.height);
            }
            if (this.E.vScrollKnob != null) {
                this.E.vScrollKnob.draw(batch, this.o.x, this.o.y, this.o.width, this.o.height);
            }
        }
    }

    public void fling(float f, float f2, float f3) {
        this.B = f;
        this.T = f2;
        this.U = f3;
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public float getPrefWidth() {
        float f = 0.0f;
        if (this.F instanceof Layout) {
            f = ((Layout) this.F).getPrefWidth();
        } else if (this.F != null) {
            f = this.F.getWidth();
        }
        Drawable drawable = this.E.background;
        if (drawable != null) {
            f = Math.max(f + drawable.getLeftWidth() + drawable.getRightWidth(), drawable.getMinWidth());
        }
        if (this.q) {
            float f2 = 0.0f;
            if (this.E.vScrollKnob != null) {
                f2 = this.E.vScrollKnob.getMinWidth();
            }
            if (this.E.vScroll != null) {
                f2 = Math.max(f2, this.E.vScroll.getMinWidth());
            }
            f += f2;
        }
        return f;
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public float getPrefHeight() {
        float f = 0.0f;
        if (this.F instanceof Layout) {
            f = ((Layout) this.F).getPrefHeight();
        } else if (this.F != null) {
            f = this.F.getHeight();
        }
        Drawable drawable = this.E.background;
        if (drawable != null) {
            f = Math.max(f + drawable.getTopHeight() + drawable.getBottomHeight(), drawable.getMinHeight());
        }
        if (this.p) {
            float f2 = 0.0f;
            if (this.E.hScrollKnob != null) {
                f2 = this.E.hScrollKnob.getMinHeight();
            }
            if (this.E.hScroll != null) {
                f2 = Math.max(f2, this.E.hScroll.getMinHeight());
            }
            f += f2;
        }
        return f;
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public float getMinWidth() {
        return 0.0f;
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public float getMinHeight() {
        return 0.0f;
    }

    public void setActor(@Null Actor actor) {
        if (this.F == this) {
            throw new IllegalArgumentException("actor cannot be the ScrollPane.");
        }
        if (this.F != null) {
            super.removeActor(this.F);
        }
        this.F = actor;
        if (actor != null) {
            super.addActor(actor);
        }
    }

    @Null
    public Actor getActor() {
        return this.F;
    }

    @Deprecated
    public void setWidget(@Null Actor actor) {
        setActor(actor);
    }

    @Deprecated
    @Null
    public Actor getWidget() {
        return this.F;
    }

    @Override // com.prineside.tdi2.scene2d.Group
    @Deprecated
    public void addActor(Actor actor) {
        throw new UnsupportedOperationException("Use ScrollPane#setActor.");
    }

    @Override // com.prineside.tdi2.scene2d.Group
    @Deprecated
    public void addActorAt(int i, Actor actor) {
        throw new UnsupportedOperationException("Use ScrollPane#setActor.");
    }

    @Override // com.prineside.tdi2.scene2d.Group
    @Deprecated
    public void addActorBefore(Actor actor, Actor actor2) {
        throw new UnsupportedOperationException("Use ScrollPane#setActor.");
    }

    @Override // com.prineside.tdi2.scene2d.Group
    @Deprecated
    public void addActorAfter(Actor actor, Actor actor2) {
        throw new UnsupportedOperationException("Use ScrollPane#setActor.");
    }

    @Override // com.prineside.tdi2.scene2d.Group
    public boolean removeActor(Actor actor) {
        if (actor == null) {
            throw new IllegalArgumentException("actor cannot be null.");
        }
        if (actor != this.F) {
            return false;
        }
        setActor(null);
        return true;
    }

    @Override // com.prineside.tdi2.scene2d.Group
    public boolean removeActor(Actor actor, boolean z) {
        if (actor == null) {
            throw new IllegalArgumentException("actor cannot be null.");
        }
        if (actor != this.F) {
            return false;
        }
        this.F = null;
        return super.removeActor(actor, z);
    }

    @Override // com.prineside.tdi2.scene2d.Group
    public Actor removeActorAt(int i, boolean z) {
        Actor removeActorAt = super.removeActorAt(i, z);
        if (removeActorAt == this.F) {
            this.F = null;
        }
        return removeActorAt;
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.Group, com.prineside.tdi2.scene2d.Actor
    @Null
    public Actor hit(float f, float f2, boolean z) {
        if (f < 0.0f || f >= getWidth() || f2 < 0.0f || f2 >= getHeight()) {
            return null;
        }
        if (z && getTouchable() == Touchable.enabled && isVisible()) {
            if (this.p && this.t && this.l.contains(f, f2)) {
                return this;
            }
            if (this.q && this.u && this.n.contains(f, f2)) {
                return this;
            }
        }
        return super.hit(f, f2, z);
    }

    private void a(float f) {
        this.r = f;
    }

    private void b(float f) {
        this.s = f;
    }

    private void c(float f) {
        this.K = f;
    }

    private void d(float f) {
        this.L = f;
    }

    protected final float e() {
        return Math.min(this.k.width, Math.max(this.k.width * 0.9f, this.M * 0.1f) / 4.0f);
    }

    protected final float f() {
        return Math.min(this.k.height, Math.max(this.k.height * 0.9f, this.N * 0.1f) / 4.0f);
    }

    public void setScrollX(float f) {
        a(MathUtils.clamp(f, 0.0f, this.M));
    }

    public float getScrollX() {
        return this.r;
    }

    public void setScrollY(float f) {
        b(MathUtils.clamp(f, 0.0f, this.N));
    }

    public float getScrollY() {
        return this.s;
    }

    public void updateVisualScroll() {
        this.K = this.r;
        this.L = this.s;
    }

    public float getVisualScrollX() {
        if (this.p) {
            return this.K;
        }
        return 0.0f;
    }

    public float getVisualScrollY() {
        if (this.q) {
            return this.L;
        }
        return 0.0f;
    }

    public float getVisualScrollPercentX() {
        if (this.M == 0.0f) {
            return 0.0f;
        }
        return MathUtils.clamp(this.K / this.M, 0.0f, 1.0f);
    }

    public float getVisualScrollPercentY() {
        if (this.N == 0.0f) {
            return 0.0f;
        }
        return MathUtils.clamp(this.L / this.N, 0.0f, 1.0f);
    }

    public float getScrollPercentX() {
        if (this.M == 0.0f) {
            return 0.0f;
        }
        return MathUtils.clamp(this.r / this.M, 0.0f, 1.0f);
    }

    public void setScrollPercentX(float f) {
        a(this.M * MathUtils.clamp(f, 0.0f, 1.0f));
    }

    public float getScrollPercentY() {
        if (this.N == 0.0f) {
            return 0.0f;
        }
        return MathUtils.clamp(this.s / this.N, 0.0f, 1.0f);
    }

    public void setScrollPercentY(float f) {
        b(this.N * MathUtils.clamp(f, 0.0f, 1.0f));
    }

    public void setFlickScroll(boolean z) {
        if (this.z == z) {
            return;
        }
        this.z = z;
        if (z) {
            addListener(this.H);
        } else {
            removeListener(this.H);
        }
        invalidate();
    }

    public void setFlickScrollTapSquareSize(float f) {
        this.H.getGestureDetector().setTapSquareSize(f);
    }

    public void scrollTo(float f, float f2, float f3, float f4) {
        scrollTo(f, f2, f3, f4, false, false);
    }

    public void scrollTo(float f, float f2, float f3, float f4, boolean z, boolean z2) {
        float clamp;
        float clamp2;
        validate();
        float f5 = this.r;
        if (z) {
            clamp = f + ((f3 - this.k.width) / 2.0f);
        } else {
            clamp = MathUtils.clamp(f5, f, (f + f3) - this.k.width);
        }
        a(MathUtils.clamp(clamp, 0.0f, this.M));
        float f6 = this.s;
        float f7 = this.N - f2;
        if (z2) {
            clamp2 = f7 + ((this.k.height + f4) / 2.0f);
        } else {
            clamp2 = MathUtils.clamp(f6, f7 + f4, f7 + this.k.height);
        }
        b(MathUtils.clamp(clamp2, 0.0f, this.N));
    }

    public float getMaxX() {
        return this.M;
    }

    public float getMaxY() {
        return this.N;
    }

    public float getScrollBarHeight() {
        if (!this.p) {
            return 0.0f;
        }
        float f = 0.0f;
        if (this.E.hScrollKnob != null) {
            f = this.E.hScrollKnob.getMinHeight();
        }
        if (this.E.hScroll != null) {
            f = Math.max(f, this.E.hScroll.getMinHeight());
        }
        return f;
    }

    public float getScrollBarWidth() {
        if (!this.q) {
            return 0.0f;
        }
        float f = 0.0f;
        if (this.E.vScrollKnob != null) {
            f = this.E.vScrollKnob.getMinWidth();
        }
        if (this.E.vScroll != null) {
            f = Math.max(f, this.E.vScroll.getMinWidth());
        }
        return f;
    }

    public float getScrollWidth() {
        return this.k.width;
    }

    public float getScrollHeight() {
        return this.k.height;
    }

    public boolean isScrollX() {
        return this.p;
    }

    public boolean isScrollY() {
        return this.q;
    }

    public void setScrollingDisabled(boolean z, boolean z2) {
        if (z == this.ac && z2 == this.C) {
            return;
        }
        this.ac = z;
        this.C = z2;
        invalidate();
    }

    public boolean isScrollingDisabledX() {
        return this.ac;
    }

    public boolean isScrollingDisabledY() {
        return this.C;
    }

    public boolean isLeftEdge() {
        return !this.p || this.r <= 0.0f;
    }

    public boolean isRightEdge() {
        return !this.p || this.r >= this.M;
    }

    public boolean isTopEdge() {
        return !this.q || this.s <= 0.0f;
    }

    public boolean isBottomEdge() {
        return !this.q || this.s >= this.N;
    }

    public boolean isDragging() {
        return this.D != -1;
    }

    public boolean isPanning() {
        return this.H.getGestureDetector().isPanning();
    }

    public boolean isFlinging() {
        return this.B > 0.0f;
    }

    public void setVelocityX(float f) {
        this.T = f;
    }

    public float getVelocityX() {
        return this.T;
    }

    public void setVelocityY(float f) {
        this.U = f;
    }

    public float getVelocityY() {
        return this.U;
    }

    public void setOverscroll(boolean z, boolean z2) {
        this.V = z;
        this.W = z2;
    }

    public void setupOverscroll(float f, float f2, float f3) {
        this.X = f;
        this.Y = f2;
        this.Z = f3;
    }

    public float getOverscrollDistance() {
        return this.X;
    }

    public void setForceScroll(boolean z, boolean z2) {
        this.aa = z;
        this.ab = z2;
    }

    public boolean isForceScrollX() {
        return this.aa;
    }

    public boolean isForceScrollY() {
        return this.ab;
    }

    public void setFlingTime(float f) {
        this.A = f;
    }

    public void setClamp(boolean z) {
        this.ad = z;
    }

    public void setScrollBarPositions(boolean z, boolean z2) {
        this.J = z;
        this.I = z2;
    }

    public void setFadeScrollBars(boolean z) {
        if (this.O == z) {
            return;
        }
        this.O = z;
        if (!z) {
            this.x = this.Q;
        }
        invalidate();
    }

    public void setupFadeScrollBars(float f, float f2) {
        this.Q = f;
        this.S = f2;
    }

    public boolean getFadeScrollBars() {
        return this.O;
    }

    public void setScrollBarTouch(boolean z) {
        this.w = z;
    }

    public void setSmoothScrolling(boolean z) {
        this.P = z;
    }

    public void setScrollbarsOnTop(boolean z) {
        this.ae = z;
        invalidate();
    }

    public boolean getVariableSizeKnobs() {
        return this.af;
    }

    public void setVariableSizeKnobs(boolean z) {
        this.af = z;
    }

    public void setCancelTouchFocus(boolean z) {
        this.y = z;
    }

    @Override // com.prineside.tdi2.scene2d.Group, com.prineside.tdi2.scene2d.Actor
    public void drawDebug(ShapeRenderer shapeRenderer) {
        a(shapeRenderer);
        a(shapeRenderer, b());
        if (clipBegin(this.k.x, this.k.y, this.k.width, this.k.height)) {
            b(shapeRenderer);
            shapeRenderer.flush();
            clipEnd();
        }
        c(shapeRenderer);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/ScrollPane$ScrollPaneStyle.class */
    public static class ScrollPaneStyle {

        @Null
        public Drawable background;

        @Null
        public Drawable corner;

        @Null
        public Drawable hScroll;

        @Null
        public Drawable hScrollKnob;

        @Null
        public Drawable vScroll;

        @Null
        public Drawable vScrollKnob;

        public ScrollPaneStyle() {
        }

        public ScrollPaneStyle(@Null Drawable drawable, @Null Drawable drawable2, @Null Drawable drawable3, @Null Drawable drawable4, @Null Drawable drawable5) {
            this.background = drawable;
            this.hScroll = drawable2;
            this.hScrollKnob = drawable3;
            this.vScroll = drawable4;
            this.vScrollKnob = drawable5;
        }

        public ScrollPaneStyle(ScrollPaneStyle scrollPaneStyle) {
            this.background = scrollPaneStyle.background;
            this.corner = scrollPaneStyle.corner;
            this.hScroll = scrollPaneStyle.hScroll;
            this.hScrollKnob = scrollPaneStyle.hScrollKnob;
            this.vScroll = scrollPaneStyle.vScroll;
            this.vScrollKnob = scrollPaneStyle.vScrollKnob;
        }
    }
}
