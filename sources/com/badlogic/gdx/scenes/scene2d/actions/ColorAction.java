package com.badlogic.gdx.scenes.scene2d.actions;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Null;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/actions/ColorAction.class */
public class ColorAction extends TemporalAction {
    private float startR;
    private float startG;
    private float startB;
    private float startA;

    @Null
    private Color color;
    private final Color end = new Color();

    @Override // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void begin() {
        if (this.color == null) {
            this.color = this.target.getColor();
        }
        this.startR = this.color.r;
        this.startG = this.color.g;
        this.startB = this.color.f888b;
        this.startA = this.color.f889a;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void update(float f) {
        if (f == 0.0f) {
            this.color.set(this.startR, this.startG, this.startB, this.startA);
            return;
        }
        if (f == 1.0f) {
            this.color.set(this.end);
            return;
        }
        this.color.set(this.startR + ((this.end.r - this.startR) * f), this.startG + ((this.end.g - this.startG) * f), this.startB + ((this.end.f888b - this.startB) * f), this.startA + ((this.end.f889a - this.startA) * f));
    }

    @Override // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction, com.badlogic.gdx.scenes.scene2d.Action, com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
        super.reset();
        this.color = null;
    }

    @Null
    public Color getColor() {
        return this.color;
    }

    public void setColor(@Null Color color) {
        this.color = color;
    }

    public Color getEndColor() {
        return this.end;
    }

    public void setEndColor(Color color) {
        this.end.set(color);
    }
}
