package com.badlogic.gdx.math;

import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/math/Vector.class */
public interface Vector<T extends Vector<T>> {
    T cpy();

    float len();

    float len2();

    T limit(float f);

    T limit2(float f);

    T setLength(float f);

    T setLength2(float f);

    T clamp(float f, float f2);

    T set(T t);

    T sub(T t);

    T nor();

    T add(T t);

    float dot(T t);

    T scl(float f);

    T scl(T t);

    float dst(T t);

    float dst2(T t);

    T lerp(T t, float f);

    T interpolate(T t, float f, Interpolation interpolation);

    T setToRandomDirection();

    boolean isUnit();

    boolean isUnit(float f);

    boolean isZero();

    boolean isZero(float f);

    boolean isOnLine(T t, float f);

    boolean isOnLine(T t);

    boolean isCollinear(T t, float f);

    boolean isCollinear(T t);

    boolean isCollinearOpposite(T t, float f);

    boolean isCollinearOpposite(T t);

    boolean isPerpendicular(T t);

    boolean isPerpendicular(T t, float f);

    boolean hasSameDirection(T t);

    boolean hasOppositeDirection(T t);

    boolean epsilonEquals(T t, float f);

    T mulAdd(T t, float f);

    T mulAdd(T t, T t2);

    T setZero();
}
