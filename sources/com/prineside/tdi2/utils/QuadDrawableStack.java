package com.prineside.tdi2.utils;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Array;
import com.prineside.tdi2.scene2d.utils.BaseDrawable;
import com.prineside.tdi2.scene2d.utils.TransformDrawable;
import com.prineside.tdi2.ui.actors.QuadActor;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/QuadDrawableStack.class */
public final class QuadDrawableStack extends BaseDrawable implements TransformDrawable {

    /* renamed from: a, reason: collision with root package name */
    private Array<QuadActorConfig> f3892a;

    /* renamed from: b, reason: collision with root package name */
    private float f3893b;
    private float c;

    public QuadDrawableStack() {
        this.f3892a = new Array<>(QuadActorConfig.class);
    }

    public QuadDrawableStack(Array<QuadActorConfig> array) {
        this.f3892a = new Array<>(QuadActorConfig.class);
        setQuadActors(array);
    }

    public QuadDrawableStack(QuadDrawableStack quadDrawableStack) {
        super(quadDrawableStack);
        this.f3892a = new Array<>(QuadActorConfig.class);
        setQuadActors(quadDrawableStack.f3892a);
    }

    @Override // com.prineside.tdi2.scene2d.utils.BaseDrawable, com.prineside.tdi2.scene2d.utils.Drawable
    @IgnoreMethodOverloadLuaDefWarning
    public final void draw(Batch batch, float f, float f2, float f3, float f4) {
        float f5 = f3 / this.f3893b;
        float f6 = f4 / this.c;
        for (int i = 0; i < this.f3892a.size; i++) {
            QuadActorConfig quadActorConfig = this.f3892a.items[i];
            quadActorConfig.actor.setPosition(f + (quadActorConfig.x * f5), f2 + (quadActorConfig.y * f6));
            quadActorConfig.actor.setSize(quadActorConfig.width * f5, quadActorConfig.height * f6);
            quadActorConfig.actor.setColor(batch.getColor());
            quadActorConfig.actor.draw(batch, 1.0f);
        }
    }

    @Override // com.prineside.tdi2.scene2d.utils.TransformDrawable
    @IgnoreMethodOverloadLuaDefWarning
    public final void draw(Batch batch, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        draw(batch, f, f2, f5, f6);
    }

    public final void setQuadActors(Array<QuadActorConfig> array) {
        this.f3892a.clear();
        this.f3892a.addAll(array);
        float f = 0.0f;
        float f2 = 0.0f;
        for (int i = 0; i < this.f3892a.size; i++) {
            QuadActorConfig quadActorConfig = this.f3892a.items[i];
            float f3 = quadActorConfig.width + quadActorConfig.x;
            float f4 = quadActorConfig.height + quadActorConfig.y;
            if (f3 > f) {
                f = f3;
            }
            if (f4 > f2) {
                f2 = f4;
            }
        }
        setMinWidth(f);
        setMinHeight(f2);
        this.f3893b = f;
        this.c = f2;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/QuadDrawableStack$QuadActorConfig.class */
    public static class QuadActorConfig {
        public QuadActor actor;
        public float x;
        public float y;
        public float width;
        public float height;

        public QuadActorConfig(QuadActor quadActor, float f, float f2, float f3, float f4) {
            this.actor = quadActor;
            this.x = f;
            this.y = f2;
            this.width = f3;
            this.height = f4;
        }
    }
}
