package com.badlogic.gdx.scenes.scene2d.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/utils/NinePatchDrawable.class */
public class NinePatchDrawable extends BaseDrawable implements TransformDrawable {
    private NinePatch patch;

    public NinePatchDrawable() {
    }

    public NinePatchDrawable(NinePatch ninePatch) {
        setPatch(ninePatch);
    }

    public NinePatchDrawable(NinePatchDrawable ninePatchDrawable) {
        super(ninePatchDrawable);
        this.patch = ninePatchDrawable.patch;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable, com.badlogic.gdx.scenes.scene2d.utils.Drawable
    public void draw(Batch batch, float f, float f2, float f3, float f4) {
        this.patch.draw(batch, f, f2, f3, f4);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable
    public void draw(Batch batch, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        this.patch.draw(batch, f, f2, f3, f4, f5, f6, f7, f8, f9);
    }

    public void setPatch(NinePatch ninePatch) {
        this.patch = ninePatch;
        if (ninePatch != null) {
            setMinWidth(ninePatch.getTotalWidth());
            setMinHeight(ninePatch.getTotalHeight());
            setTopHeight(ninePatch.getPadTop());
            setRightWidth(ninePatch.getPadRight());
            setBottomHeight(ninePatch.getPadBottom());
            setLeftWidth(ninePatch.getPadLeft());
        }
    }

    public NinePatch getPatch() {
        return this.patch;
    }

    public NinePatchDrawable tint(Color color) {
        NinePatchDrawable ninePatchDrawable = new NinePatchDrawable(this);
        ninePatchDrawable.patch = new NinePatch(ninePatchDrawable.getPatch(), color);
        return ninePatchDrawable;
    }
}
