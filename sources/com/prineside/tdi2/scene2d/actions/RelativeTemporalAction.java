package com.prineside.tdi2.scene2d.actions;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/actions/RelativeTemporalAction.class */
public abstract class RelativeTemporalAction extends TemporalAction {
    private float c;

    protected abstract void b(float f);

    @Override // com.prineside.tdi2.scene2d.actions.TemporalAction
    protected final void a() {
        this.c = 0.0f;
    }

    @Override // com.prineside.tdi2.scene2d.actions.TemporalAction
    protected final void a(float f) {
        b(f - this.c);
        this.c = f;
    }
}
