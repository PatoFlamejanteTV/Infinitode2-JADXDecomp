package com.prineside.tdi2.pathfinding;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/pathfinding/BezierSideFunction.class */
public final class BezierSideFunction implements SideFunction {

    /* renamed from: a, reason: collision with root package name */
    private final float f2602a;

    /* renamed from: b, reason: collision with root package name */
    private final float f2603b;
    private final float c;
    private final float d;
    private final float e;
    private final float f;
    private final float g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BezierSideFunction(float f, float f2, float f3, float f4, float f5, float f6, float f7) {
        this.f2602a = f * 128.0f;
        this.d = f2 * 128.0f;
        this.f2603b = f3 * 128.0f;
        this.e = f4 * 128.0f;
        this.c = f5 * 128.0f;
        this.f = f6 * 128.0f;
        this.g = (float) (1.0d / ((0.5d + f7) * 1.5707963267948966d));
    }

    @Override // com.prineside.tdi2.pathfinding.SideFunction
    public final void position(float f, Vector2 vector2) {
        float f2 = 1.0f - f;
        vector2.x = (this.f2602a * f2 * f2) + (2.0f * this.f2603b * f2 * f) + (this.c * f * f);
        vector2.y = (this.d * f2 * f2) + (2.0f * this.e * f2 * f) + (this.f * f * f);
    }

    @Override // com.prineside.tdi2.pathfinding.SideFunction
    public final float rotation(float f) {
        float f2 = f - 1.0f;
        return (57.295776f * MathUtils.atan2(2.0f * (((this.d * f2) - (this.e * ((f * 2.0f) - 1.0f))) + (this.f * f)), 2.0f * (((this.f2602a * f2) - (this.f2603b * ((f * 2.0f) - 1.0f))) + (this.c * f)))) - 90.0f;
    }

    @Override // com.prineside.tdi2.pathfinding.SideFunction
    public final float speedMultiplier() {
        return this.g;
    }
}
