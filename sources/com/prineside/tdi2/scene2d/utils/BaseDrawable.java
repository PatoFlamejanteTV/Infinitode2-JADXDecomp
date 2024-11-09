package com.prineside.tdi2.scene2d.utils;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.reflect.ClassReflection;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/utils/BaseDrawable.class */
public class BaseDrawable implements Drawable {

    /* renamed from: a, reason: collision with root package name */
    @Null
    private String f2706a;

    /* renamed from: b, reason: collision with root package name */
    private float f2707b;
    private float c;
    private float d;
    private float e;
    private float f;
    private float g;

    public BaseDrawable() {
    }

    public BaseDrawable(Drawable drawable) {
        if (drawable instanceof BaseDrawable) {
            this.f2706a = ((BaseDrawable) drawable).getName();
        }
        this.f2707b = drawable.getLeftWidth();
        this.c = drawable.getRightWidth();
        this.d = drawable.getTopHeight();
        this.e = drawable.getBottomHeight();
        this.f = drawable.getMinWidth();
        this.g = drawable.getMinHeight();
    }

    @Override // com.prineside.tdi2.scene2d.utils.Drawable
    public void draw(Batch batch, float f, float f2, float f3, float f4) {
    }

    @Override // com.prineside.tdi2.scene2d.utils.Drawable
    public float getLeftWidth() {
        return this.f2707b;
    }

    @Override // com.prineside.tdi2.scene2d.utils.Drawable
    public void setLeftWidth(float f) {
        this.f2707b = f;
    }

    @Override // com.prineside.tdi2.scene2d.utils.Drawable
    public float getRightWidth() {
        return this.c;
    }

    @Override // com.prineside.tdi2.scene2d.utils.Drawable
    public void setRightWidth(float f) {
        this.c = f;
    }

    @Override // com.prineside.tdi2.scene2d.utils.Drawable
    public float getTopHeight() {
        return this.d;
    }

    @Override // com.prineside.tdi2.scene2d.utils.Drawable
    public void setTopHeight(float f) {
        this.d = f;
    }

    @Override // com.prineside.tdi2.scene2d.utils.Drawable
    public float getBottomHeight() {
        return this.e;
    }

    @Override // com.prineside.tdi2.scene2d.utils.Drawable
    public void setBottomHeight(float f) {
        this.e = f;
    }

    public void setPadding(float f, float f2, float f3, float f4) {
        setTopHeight(f);
        setLeftWidth(f2);
        setBottomHeight(f3);
        setRightWidth(f4);
    }

    @Override // com.prineside.tdi2.scene2d.utils.Drawable
    public float getMinWidth() {
        return this.f;
    }

    @Override // com.prineside.tdi2.scene2d.utils.Drawable
    public void setMinWidth(float f) {
        this.f = f;
    }

    @Override // com.prineside.tdi2.scene2d.utils.Drawable
    public float getMinHeight() {
        return this.g;
    }

    @Override // com.prineside.tdi2.scene2d.utils.Drawable
    public void setMinHeight(float f) {
        this.g = f;
    }

    public void setMinSize(float f, float f2) {
        setMinWidth(f);
        setMinHeight(f2);
    }

    @Null
    public String getName() {
        return this.f2706a;
    }

    public void setName(@Null String str) {
        this.f2706a = str;
    }

    @Null
    public String toString() {
        return this.f2706a == null ? ClassReflection.getSimpleName(getClass()) : this.f2706a;
    }
}
