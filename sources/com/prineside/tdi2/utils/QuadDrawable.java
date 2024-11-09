package com.prineside.tdi2.utils;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.prineside.tdi2.scene2d.utils.BaseDrawable;
import com.prineside.tdi2.scene2d.utils.TransformDrawable;
import com.prineside.tdi2.ui.actors.QuadActor;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/QuadDrawable.class */
public final class QuadDrawable extends BaseDrawable implements TransformDrawable {

    /* renamed from: a, reason: collision with root package name */
    private QuadActor f3891a;

    public QuadDrawable() {
    }

    public QuadDrawable(QuadActor quadActor) {
        setQuadActor(quadActor);
    }

    public QuadDrawable(QuadDrawable quadDrawable) {
        super(quadDrawable);
        setQuadActor(quadDrawable.f3891a);
    }

    @Override // com.prineside.tdi2.scene2d.utils.BaseDrawable, com.prineside.tdi2.scene2d.utils.Drawable
    @IgnoreMethodOverloadLuaDefWarning
    public final void draw(Batch batch, float f, float f2, float f3, float f4) {
        this.f3891a.setPosition(f, f2);
        this.f3891a.setSize(f3, f4);
        this.f3891a.setColor(batch.getColor());
        this.f3891a.draw(batch, 1.0f);
    }

    @Override // com.prineside.tdi2.scene2d.utils.TransformDrawable
    @IgnoreMethodOverloadLuaDefWarning
    public final void draw(Batch batch, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        this.f3891a.setPosition(f, f2);
        this.f3891a.setSize(f5, f6);
        this.f3891a.setColor(batch.getColor());
        this.f3891a.draw(batch, 1.0f);
    }

    public final void setQuadActor(QuadActor quadActor) {
        this.f3891a = quadActor;
        if (quadActor != null) {
            setMinWidth(quadActor.getMinWidth());
            setMinHeight(quadActor.getMinHeight());
        }
    }

    public final QuadActor getQuadActor() {
        return this.f3891a;
    }
}
