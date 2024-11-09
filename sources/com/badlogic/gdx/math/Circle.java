package com.badlogic.gdx.math;

import com.badlogic.gdx.utils.NumberUtils;
import java.io.Serializable;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/math/Circle.class */
public class Circle implements Shape2D, Serializable {
    public float x;
    public float y;
    public float radius;

    public Circle() {
    }

    public Circle(float f, float f2, float f3) {
        this.x = f;
        this.y = f2;
        this.radius = f3;
    }

    public Circle(Vector2 vector2, float f) {
        this.x = vector2.x;
        this.y = vector2.y;
        this.radius = f;
    }

    public Circle(Circle circle) {
        this.x = circle.x;
        this.y = circle.y;
        this.radius = circle.radius;
    }

    public Circle(Vector2 vector2, Vector2 vector22) {
        this.x = vector2.x;
        this.y = vector2.y;
        this.radius = Vector2.len(vector2.x - vector22.x, vector2.y - vector22.y);
    }

    public void set(float f, float f2, float f3) {
        this.x = f;
        this.y = f2;
        this.radius = f3;
    }

    public void set(Vector2 vector2, float f) {
        this.x = vector2.x;
        this.y = vector2.y;
        this.radius = f;
    }

    public void set(Circle circle) {
        this.x = circle.x;
        this.y = circle.y;
        this.radius = circle.radius;
    }

    public void set(Vector2 vector2, Vector2 vector22) {
        this.x = vector2.x;
        this.y = vector2.y;
        this.radius = Vector2.len(vector2.x - vector22.x, vector2.y - vector22.y);
    }

    public void setPosition(Vector2 vector2) {
        this.x = vector2.x;
        this.y = vector2.y;
    }

    public void setPosition(float f, float f2) {
        this.x = f;
        this.y = f2;
    }

    public void setX(float f) {
        this.x = f;
    }

    public void setY(float f) {
        this.y = f;
    }

    public void setRadius(float f) {
        this.radius = f;
    }

    @Override // com.badlogic.gdx.math.Shape2D
    public boolean contains(float f, float f2) {
        float f3 = this.x - f;
        float f4 = this.y - f2;
        return (f3 * f3) + (f4 * f4) <= this.radius * this.radius;
    }

    @Override // com.badlogic.gdx.math.Shape2D
    public boolean contains(Vector2 vector2) {
        float f = this.x - vector2.x;
        float f2 = this.y - vector2.y;
        return (f * f) + (f2 * f2) <= this.radius * this.radius;
    }

    public boolean contains(Circle circle) {
        float f = this.radius - circle.radius;
        if (f < 0.0f) {
            return false;
        }
        float f2 = this.x - circle.x;
        float f3 = this.y - circle.y;
        float f4 = (f2 * f2) + (f3 * f3);
        float f5 = this.radius + circle.radius;
        return f * f >= f4 && f4 < f5 * f5;
    }

    public boolean overlaps(Circle circle) {
        float f = this.x - circle.x;
        float f2 = this.y - circle.y;
        float f3 = (f * f) + (f2 * f2);
        float f4 = this.radius + circle.radius;
        return f3 < f4 * f4;
    }

    public String toString() {
        return this.x + "," + this.y + "," + this.radius;
    }

    public float circumference() {
        return this.radius * 6.2831855f;
    }

    public float area() {
        return this.radius * this.radius * 3.1415927f;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        Circle circle = (Circle) obj;
        return this.x == circle.x && this.y == circle.y && this.radius == circle.radius;
    }

    public int hashCode() {
        return ((((41 + NumberUtils.floatToRawIntBits(this.radius)) * 41) + NumberUtils.floatToRawIntBits(this.x)) * 41) + NumberUtils.floatToRawIntBits(this.y);
    }
}
