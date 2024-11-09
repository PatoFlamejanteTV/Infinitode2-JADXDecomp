package com.prineside.tdi2.pathfinding;

import com.badlogic.gdx.math.Vector2;
import com.prineside.tdi2.utils.PMath;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/pathfinding/LinearSideFunction.class */
public final class LinearSideFunction implements SideFunction {

    /* renamed from: a, reason: collision with root package name */
    private final float f2604a;

    /* renamed from: b, reason: collision with root package name */
    private final float f2605b;
    private final float c;
    private final float d;
    private final float e;
    private final float f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LinearSideFunction(float f, float f2, float f3, float f4) {
        this.f2604a = f * 128.0f;
        this.c = f2 * 128.0f;
        this.f2605b = f3 * 128.0f;
        this.d = f4 * 128.0f;
        this.f = PMath.getAngleBetweenPoints(f, f2, f3, f4);
        this.e = 1.0f / PMath.getDistanceBetweenPoints(f, f2, f3, f4);
    }

    @Override // com.prineside.tdi2.pathfinding.SideFunction
    public final void position(float f, Vector2 vector2) {
        vector2.x = this.f2604a + ((this.f2605b - this.f2604a) * f);
        vector2.y = this.c + ((this.d - this.c) * f);
    }

    @Override // com.prineside.tdi2.pathfinding.SideFunction
    public final float rotation(float f) {
        return this.f;
    }

    @Override // com.prineside.tdi2.pathfinding.SideFunction
    public final float speedMultiplier() {
        return this.e;
    }
}
