package com.prineside.tdi2.scene2d.utils;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.ui.ScrollPane;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/utils/DragScrollListener.class */
public class DragScrollListener extends DragListener {

    /* renamed from: a, reason: collision with root package name */
    private static Vector2 f2720a = new Vector2();

    /* renamed from: b, reason: collision with root package name */
    private ScrollPane f2721b;
    private Timer.Task c;
    private Timer.Task d;
    private long i;
    private float k;
    private float l;
    private Interpolation e = Interpolation.exp5In;
    private float f = 15.0f;
    private float g = 75.0f;
    private float h = 0.05f;
    private long j = 1750;

    public DragScrollListener(final ScrollPane scrollPane) {
        this.f2721b = scrollPane;
        this.c = new Timer.Task() { // from class: com.prineside.tdi2.scene2d.utils.DragScrollListener.1
            @Override // com.badlogic.gdx.utils.Timer.Task, java.lang.Runnable
            public void run() {
                DragScrollListener.this.a(scrollPane.getScrollY() - DragScrollListener.this.a());
            }
        };
        this.d = new Timer.Task() { // from class: com.prineside.tdi2.scene2d.utils.DragScrollListener.2
            @Override // com.badlogic.gdx.utils.Timer.Task, java.lang.Runnable
            public void run() {
                DragScrollListener.this.a(scrollPane.getScrollY() + DragScrollListener.this.a());
            }
        };
    }

    public void setup(float f, float f2, float f3, float f4) {
        this.f = f;
        this.g = f2;
        this.h = f3;
        this.j = f4 * 1000.0f;
    }

    final float a() {
        return this.e.apply(this.f, this.g, Math.min(1.0f, ((float) (System.currentTimeMillis() - this.i)) / ((float) this.j)));
    }

    @Override // com.prineside.tdi2.scene2d.utils.DragListener
    public void drag(InputEvent inputEvent, float f, float f2, int i) {
        inputEvent.getListenerActor().localToActorCoordinates(this.f2721b, f2720a.set(f, f2));
        if (b(f2720a.y)) {
            this.d.cancel();
            if (!this.c.isScheduled()) {
                this.i = System.currentTimeMillis();
                Timer.schedule(this.c, this.h, this.h);
                return;
            }
            return;
        }
        if (c(f2720a.y)) {
            this.c.cancel();
            if (!this.d.isScheduled()) {
                this.i = System.currentTimeMillis();
                Timer.schedule(this.d, this.h, this.h);
                return;
            }
            return;
        }
        this.c.cancel();
        this.d.cancel();
    }

    @Override // com.prineside.tdi2.scene2d.utils.DragListener
    public void dragStop(InputEvent inputEvent, float f, float f2, int i) {
        this.c.cancel();
        this.d.cancel();
    }

    private boolean b(float f) {
        return f >= this.f2721b.getHeight() - this.k;
    }

    private boolean c(float f) {
        return f < this.l;
    }

    protected final void a(float f) {
        this.f2721b.setScrollY(f);
    }

    public void setPadding(float f, float f2) {
        this.k = f;
        this.l = f2;
    }
}
