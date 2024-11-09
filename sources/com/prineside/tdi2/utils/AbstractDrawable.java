package com.prineside.tdi2.utils;

import com.prineside.tdi2.scene2d.utils.Drawable;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/AbstractDrawable.class */
public abstract class AbstractDrawable implements Drawable {
    public static final int DRAWABLE_PARAM_LEFT_WIDTH = 0;
    public static final int DRAWABLE_PARAM_RIGHT_WIDTH = 1;
    public static final int DRAWABLE_PARAM_TOP_HEIGHT = 2;
    public static final int DRAWABLE_PARAM_BOTTOM_HEIGHT = 3;
    public static final int DRAWABLE_PARAM_MIN_WIDTH = 4;
    public static final int DRAWABLE_PARAM_MIN_HEIGHT = 5;

    /* renamed from: a, reason: collision with root package name */
    protected float[] f3812a;

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a() {
        if (this.f3812a == null) {
            this.f3812a = new float[6];
        }
    }

    @Override // com.prineside.tdi2.scene2d.utils.Drawable
    public float getLeftWidth() {
        if (this.f3812a == null) {
            return 0.0f;
        }
        return this.f3812a[0];
    }

    @Override // com.prineside.tdi2.scene2d.utils.Drawable
    public void setLeftWidth(float f) {
        a();
        this.f3812a[0] = f;
    }

    @Override // com.prineside.tdi2.scene2d.utils.Drawable
    public float getRightWidth() {
        if (this.f3812a == null) {
            return 0.0f;
        }
        return this.f3812a[1];
    }

    @Override // com.prineside.tdi2.scene2d.utils.Drawable
    public void setRightWidth(float f) {
        a();
        this.f3812a[1] = f;
    }

    @Override // com.prineside.tdi2.scene2d.utils.Drawable
    public float getTopHeight() {
        if (this.f3812a == null) {
            return 0.0f;
        }
        return this.f3812a[2];
    }

    @Override // com.prineside.tdi2.scene2d.utils.Drawable
    public void setTopHeight(float f) {
        a();
        this.f3812a[2] = f;
    }

    @Override // com.prineside.tdi2.scene2d.utils.Drawable
    public float getBottomHeight() {
        if (this.f3812a == null) {
            return 0.0f;
        }
        return this.f3812a[3];
    }

    @Override // com.prineside.tdi2.scene2d.utils.Drawable
    public void setBottomHeight(float f) {
        a();
        this.f3812a[3] = f;
    }

    @Override // com.prineside.tdi2.scene2d.utils.Drawable
    public float getMinWidth() {
        if (this.f3812a == null) {
            return 0.0f;
        }
        return this.f3812a[4];
    }

    @Override // com.prineside.tdi2.scene2d.utils.Drawable
    public void setMinWidth(float f) {
        a();
        this.f3812a[4] = f;
    }

    @Override // com.prineside.tdi2.scene2d.utils.Drawable
    public float getMinHeight() {
        if (this.f3812a == null) {
            return 0.0f;
        }
        return this.f3812a[5];
    }

    @Override // com.prineside.tdi2.scene2d.utils.Drawable
    public void setMinHeight(float f) {
        a();
        this.f3812a[5] = f;
    }
}
