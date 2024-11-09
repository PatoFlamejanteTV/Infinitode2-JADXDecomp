package com.badlogic.gdx.scenes.scene2d.actions;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Null;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/actions/AlphaAction.class */
public class AlphaAction extends TemporalAction {
    private float start;
    private float end;

    @Null
    private Color color;

    @Override // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void begin() {
        if (this.color == null) {
            this.color = this.target.getColor();
        }
        this.start = this.color.f889a;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void update(float f) {
        if (f == 0.0f) {
            this.color.f889a = this.start;
        } else if (f == 1.0f) {
            this.color.f889a = this.end;
        } else {
            this.color.f889a = this.start + ((this.end - this.start) * f);
        }
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

    public float getAlpha() {
        return this.end;
    }

    public void setAlpha(float f) {
        this.end = f;
    }
}
