package com.prineside.tdi2.ui.actors;

import com.badlogic.gdx.graphics.Color;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.utils.QuadDrawable;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/RectButton.class */
public class RectButton extends ComplexButton {
    private final QuadDrawable l;

    public RectButton(CharSequence charSequence, Label.LabelStyle labelStyle, Runnable runnable) {
        super(charSequence, labelStyle, runnable);
        this.l = new QuadDrawable(new QuadActor(Color.WHITE, new float[]{0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f}));
    }

    private void d() {
        setBackground(this.l, 0.0f, 0.0f, getWidth(), getHeight());
        if (!this.k) {
            setLabel(0.0f, 0.0f, getWidth(), getHeight(), 1);
            this.k = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.prineside.tdi2.scene2d.Actor
    public void positionChanged() {
        super.positionChanged();
        d();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.prineside.tdi2.scene2d.Actor
    public void sizeChanged() {
        super.sizeChanged();
        d();
    }
}
