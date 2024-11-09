package com.prineside.tdi2.shapes;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Shape;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/shapes/CountdownPolygon.class */
public class CountdownPolygon extends Shape {

    /* renamed from: a, reason: collision with root package name */
    private float[] f2907a;

    /* renamed from: b, reason: collision with root package name */
    private final TextureRegion f2908b;

    /* synthetic */ CountdownPolygon(byte b2) {
        this();
    }

    private CountdownPolygon() {
        this.f2907a = new float[0];
        this.f2908b = Game.i.assetManager.getBlankWhiteTextureRegion();
        this.f2908b.getU2();
        this.f2908b.getU();
        this.f2908b.getV2();
        this.f2908b.getV();
        this.f2908b.getU();
        this.f2908b.getV();
    }

    public void setup(float[] fArr) {
        if (fArr.length % 2 == 1) {
            throw new IllegalArgumentException("points must be %2 == 0");
        }
        if (fArr.length < 6) {
            throw new IllegalArgumentException("points min length is 6");
        }
        int length = ((fArr.length / 2) + 1) / 2;
        if (this.f2907a.length < length * 20) {
            this.f2907a = new float[length * 20];
        }
    }

    @Override // com.prineside.tdi2.Shape, com.prineside.tdi2.ProjectileTrail
    public void draw(Batch batch) {
        batch.draw(this.f2908b.getTexture(), this.f2907a, 0, 0);
    }

    @Override // com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
    }

    public void free() {
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/shapes/CountdownPolygon$CountdownPolygonFactory.class */
    public static class CountdownPolygonFactory extends Shape.Factory<CountdownPolygon> {
        @Override // com.prineside.tdi2.Shape.Factory
        protected final /* synthetic */ CountdownPolygon a() {
            return b();
        }

        @Override // com.prineside.tdi2.Shape.Factory
        public void setup() {
        }

        private static CountdownPolygon b() {
            return new CountdownPolygon((byte) 0);
        }
    }
}
