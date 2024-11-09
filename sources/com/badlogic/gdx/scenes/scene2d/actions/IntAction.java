package com.badlogic.gdx.scenes.scene2d.actions;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Null;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/actions/IntAction.class */
public class IntAction extends TemporalAction {
    private int start;
    private int end;
    private int value;

    public IntAction() {
        this.start = 0;
        this.end = 1;
    }

    public IntAction(int i, int i2) {
        this.start = i;
        this.end = i2;
    }

    public IntAction(int i, int i2, float f) {
        super(f);
        this.start = i;
        this.end = i2;
    }

    public IntAction(int i, int i2, float f, @Null Interpolation interpolation) {
        super(f, interpolation);
        this.start = i;
        this.end = i2;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void begin() {
        this.value = this.start;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void update(float f) {
        if (f == 0.0f) {
            this.value = this.start;
        } else if (f == 1.0f) {
            this.value = this.end;
        } else {
            this.value = (int) (this.start + ((this.end - this.start) * f));
        }
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int i) {
        this.value = i;
    }

    public int getStart() {
        return this.start;
    }

    public void setStart(int i) {
        this.start = i;
    }

    public int getEnd() {
        return this.end;
    }

    public void setEnd(int i) {
        this.end = i;
    }
}
