package com.prineside.tdi2.pathfinding;

import com.badlogic.gdx.math.Vector2;
import com.prineside.tdi2.utils.PMath;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/pathfinding/SharpCornerSideFunction.class */
public final class SharpCornerSideFunction implements SideFunction {

    /* renamed from: a, reason: collision with root package name */
    private final float f2615a;

    /* renamed from: b, reason: collision with root package name */
    private final float f2616b;
    private final float c;
    private final float d;
    private final float e;
    private final float f;
    private final float g;
    private final float h;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SharpCornerSideFunction(float f, float f2, float f3, float f4, float f5, float f6) {
        this.f2615a = f * 128.0f;
        this.d = f2 * 128.0f;
        this.f2616b = f3 * 128.0f;
        this.e = f4 * 128.0f;
        this.c = f5 * 128.0f;
        this.f = f6 * 128.0f;
        this.g = PMath.getAngleBetweenPoints(f3, f4, f, f2);
        this.h = PMath.getAngleBetweenPoints(f5, f6, f3, f4);
    }

    @Override // com.prineside.tdi2.pathfinding.SideFunction
    public final void position(float f, Vector2 vector2) {
        if (f < 0.5f) {
            float f2 = f * 2.0f;
            vector2.set(this.f2615a + ((this.f2616b - this.f2615a) * f2), this.d + ((this.e - this.d) * f2));
        } else {
            float f3 = (f - 0.5f) * 2.0f;
            vector2.set(this.f2616b + ((this.c - this.f2616b) * f3), this.e + ((this.f - this.e) * f3));
        }
    }

    @Override // com.prineside.tdi2.pathfinding.SideFunction
    public final float rotation(float f) {
        return f < 0.5f ? this.g : this.h;
    }

    @Override // com.prineside.tdi2.pathfinding.SideFunction
    public final float speedMultiplier() {
        return 0.70710677f;
    }
}
