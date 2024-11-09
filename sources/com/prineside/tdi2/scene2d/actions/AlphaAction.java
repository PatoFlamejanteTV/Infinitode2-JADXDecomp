package com.prineside.tdi2.scene2d.actions;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Null;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/actions/AlphaAction.class */
public class AlphaAction extends TemporalAction {
    private float c;
    private float d;

    @Null
    private Color e;

    @Override // com.prineside.tdi2.scene2d.actions.TemporalAction
    protected final void a() {
        if (this.e == null) {
            this.e = this.f2637b.getColor();
        }
        this.c = this.e.f889a;
    }

    @Override // com.prineside.tdi2.scene2d.actions.TemporalAction
    protected final void a(float f) {
        if (f == 0.0f) {
            this.e.f889a = this.c;
        } else if (f == 1.0f) {
            this.e.f889a = this.d;
        } else {
            this.e.f889a = this.c + ((this.d - this.c) * f);
        }
    }

    @Override // com.prineside.tdi2.scene2d.actions.TemporalAction, com.prineside.tdi2.scene2d.Action, com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
        super.reset();
        this.e = null;
    }

    @Null
    public Color getColor() {
        return this.e;
    }

    public void setColor(@Null Color color) {
        this.e = color;
    }

    public float getAlpha() {
        return this.d;
    }

    public void setAlpha(float f) {
        this.d = f;
    }
}
