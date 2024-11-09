package com.prineside.tdi2.scene2d.actions;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Null;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/actions/IntAction.class */
public class IntAction extends TemporalAction {
    private int c;
    private int d;
    private int e;

    public IntAction() {
        this.c = 0;
        this.d = 1;
    }

    public IntAction(int i, int i2) {
        this.c = i;
        this.d = i2;
    }

    public IntAction(int i, int i2, float f) {
        super(f);
        this.c = i;
        this.d = i2;
    }

    public IntAction(int i, int i2, float f, @Null Interpolation interpolation) {
        super(f, interpolation);
        this.c = i;
        this.d = i2;
    }

    @Override // com.prineside.tdi2.scene2d.actions.TemporalAction
    protected final void a() {
        this.e = this.c;
    }

    @Override // com.prineside.tdi2.scene2d.actions.TemporalAction
    protected final void a(float f) {
        if (f == 0.0f) {
            this.e = this.c;
        } else if (f == 1.0f) {
            this.e = this.d;
        } else {
            this.e = (int) (this.c + ((this.d - this.c) * f));
        }
    }

    public int getValue() {
        return this.e;
    }

    public void setValue(int i) {
        this.e = i;
    }

    public int getStart() {
        return this.c;
    }

    public void setStart(int i) {
        this.c = i;
    }

    public int getEnd() {
        return this.d;
    }

    public void setEnd(int i) {
        this.d = i;
    }
}
