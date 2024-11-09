package com.prineside.tdi2.scene2d.actions;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Null;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/actions/ColorAction.class */
public class ColorAction extends TemporalAction {
    private float c;
    private float d;
    private float e;
    private float f;

    @Null
    private Color g;
    private final Color h = new Color();

    @Override // com.prineside.tdi2.scene2d.actions.TemporalAction
    protected final void a() {
        if (this.g == null) {
            this.g = this.f2637b.getColor();
        }
        this.c = this.g.r;
        this.d = this.g.g;
        this.e = this.g.f888b;
        this.f = this.g.f889a;
    }

    @Override // com.prineside.tdi2.scene2d.actions.TemporalAction
    protected final void a(float f) {
        if (f == 0.0f) {
            this.g.set(this.c, this.d, this.e, this.f);
            return;
        }
        if (f == 1.0f) {
            this.g.set(this.h);
            return;
        }
        this.g.set(this.c + ((this.h.r - this.c) * f), this.d + ((this.h.g - this.d) * f), this.e + ((this.h.f888b - this.e) * f), this.f + ((this.h.f889a - this.f) * f));
    }

    @Override // com.prineside.tdi2.scene2d.actions.TemporalAction, com.prineside.tdi2.scene2d.Action, com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
        super.reset();
        this.g = null;
    }

    @Null
    public Color getColor() {
        return this.g;
    }

    public void setColor(@Null Color color) {
        this.g = color;
    }

    public Color getEndColor() {
        return this.h;
    }

    public void setEndColor(Color color) {
        this.h.set(color);
    }
}
