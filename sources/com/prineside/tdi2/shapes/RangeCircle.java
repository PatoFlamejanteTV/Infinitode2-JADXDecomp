package com.prineside.tdi2.shapes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Shape;
import com.prineside.tdi2.enums.ShapeType;
import com.prineside.tdi2.shapes.Circle;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/shapes/RangeCircle.class */
public class RangeCircle extends Shape {

    /* renamed from: a, reason: collision with root package name */
    private float f2917a;

    /* renamed from: b, reason: collision with root package name */
    private float f2918b;
    private float c;
    private float d;
    private final Color e;
    private float f;
    private boolean g;
    private boolean h;
    private final Circle i;
    private final Circle j;
    private final Circle k;
    private final Circle l;
    private final Color m;

    /* synthetic */ RangeCircle(byte b2) {
        this();
    }

    private RangeCircle() {
        this.e = new Color();
        this.g = false;
        this.h = false;
        this.m = new Color();
        Circle.CircleFactory circleFactory = (Circle.CircleFactory) Game.i.shapeManager.getFactory(ShapeType.CIRCLE);
        this.i = circleFactory.obtain();
        this.j = circleFactory.obtain();
        this.k = circleFactory.obtain();
        this.l = circleFactory.obtain();
    }

    public void setup(float f, float f2, float f3, float f4, Color color) {
        float floatBits = color.toFloatBits();
        this.e.set(color);
        this.m.set(color);
        this.m.f889a = 0.0f;
        float floatBits2 = this.m.toFloatBits();
        if (f3 > 0.0f) {
            this.g = true;
            if (!this.h || this.c != f3 || this.d != f4) {
                this.j.setup(f, f2, f3, f4 - ((f4 - f3) * 0.5f), 32, floatBits, floatBits2);
                this.i.setup(f, f2, f3, f3 + 8.0f, 32, floatBits, floatBits);
            } else if (this.f2917a != f || this.f2918b != f2) {
                this.j.setPosition(f, f2);
                this.i.setPosition(f, f2);
                if (this.f != floatBits) {
                    this.j.setColor(floatBits, floatBits);
                    this.i.setColor(floatBits, floatBits);
                }
            } else if (this.f != floatBits) {
                this.j.setColor(floatBits, floatBits2);
                this.i.setColor(floatBits, floatBits);
            }
        } else {
            this.g = false;
        }
        if (!this.h || this.c != f3 || this.d != f4) {
            this.l.setup(f, f2, f3 + ((f4 - f3) * 0.5f), f4, 32, floatBits2, floatBits);
            this.k.setup(f, f2, f4 - 8.0f, f4, 32, floatBits, floatBits);
        } else if (this.f2917a != f || this.f2918b != f2) {
            this.l.setPosition(f, f2);
            this.k.setPosition(f, f2);
            if (this.f != floatBits) {
                this.l.setColor(floatBits2, floatBits);
                this.k.setColor(floatBits, floatBits);
            }
        } else if (this.f != floatBits) {
            this.l.setColor(floatBits2, floatBits);
            this.k.setColor(floatBits, floatBits);
        }
        this.f = floatBits;
        this.c = f3;
        this.d = f4;
        this.f2917a = f;
        this.f2918b = f2;
        this.h = true;
    }

    public float getX() {
        return this.f2917a;
    }

    public float getY() {
        return this.f2918b;
    }

    public float getMinRadius() {
        return this.c;
    }

    public float getMaxRadius() {
        return this.d;
    }

    public Color getColor() {
        return this.e;
    }

    @Override // com.prineside.tdi2.Shape, com.prineside.tdi2.ProjectileTrail
    public void draw(Batch batch) {
        if (!this.h) {
            throw new IllegalStateException("Circle is not set up yet");
        }
        if (this.g) {
            this.j.draw(batch);
            this.i.draw(batch);
        }
        this.l.draw(batch);
        this.k.draw(batch);
    }

    @Override // com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
        this.h = false;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/shapes/RangeCircle$RangeCircleFactory.class */
    public static class RangeCircleFactory extends Shape.Factory<RangeCircle> {
        @Override // com.prineside.tdi2.Shape.Factory
        protected final /* synthetic */ RangeCircle a() {
            return b();
        }

        @Override // com.prineside.tdi2.Shape.Factory
        public void setup() {
        }

        private static RangeCircle b() {
            return new RangeCircle((byte) 0);
        }
    }
}
