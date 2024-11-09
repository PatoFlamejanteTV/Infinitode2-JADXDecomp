package com.prineside.tdi2.shapes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.prineside.tdi2.ProjectileTrail;
import com.prineside.tdi2.Shape;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/shapes/FlyingParticlesLine.class */
public class FlyingParticlesLine extends Shape implements ProjectileTrail {

    /* renamed from: a, reason: collision with root package name */
    private float f2909a;

    /* renamed from: b, reason: collision with root package name */
    private float f2910b;

    /* synthetic */ FlyingParticlesLine(byte b2) {
        this();
    }

    private FlyingParticlesLine() {
        reset();
    }

    public void setup(float f, float f2, float f3, float f4, TextureRegion textureRegion, float f5, float f6, float f7, float f8, Color color, Color color2, float f9, float f10, float f11, ParticleEmitter.ScaledNumericValue scaledNumericValue) {
        this.f2909a = f10;
        this.f2910b = 0.0f;
    }

    @Override // com.prineside.tdi2.ProjectileTrail
    public void update(float f) {
    }

    @Override // com.prineside.tdi2.ProjectileTrail
    public boolean isFinished() {
        return 0.0f >= this.f2909a;
    }

    @Override // com.prineside.tdi2.Shape, com.prineside.tdi2.ProjectileTrail
    public void draw(Batch batch) {
    }

    @Override // com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
    }

    @Override // com.prineside.tdi2.ProjectileTrail
    public void free() {
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/shapes/FlyingParticlesLine$FlyingParticlesLineFactory.class */
    public static class FlyingParticlesLineFactory extends Shape.Factory<FlyingParticlesLine> {
        @Override // com.prineside.tdi2.Shape.Factory
        protected final /* synthetic */ FlyingParticlesLine a() {
            return b();
        }

        @Override // com.prineside.tdi2.Shape.Factory
        public void setup() {
        }

        private static FlyingParticlesLine b() {
            return new FlyingParticlesLine((byte) 0);
        }
    }
}
