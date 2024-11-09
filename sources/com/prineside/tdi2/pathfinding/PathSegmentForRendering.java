package com.prineside.tdi2.pathfinding;

import com.prineside.tdi2.utils.PMath;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/pathfinding/PathSegmentForRendering.class */
public final class PathSegmentForRendering {
    public float x1;
    public float y1;
    public float x2;
    public float y2;
    public float distanceFromStart;
    public float length;
    public Direction direction;

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/pathfinding/PathSegmentForRendering$Direction.class */
    public enum Direction {
        TOP,
        LEFT,
        RIGHT,
        BOTTOM
    }

    public static Direction getDirection(float f, float f2, float f3, float f4) {
        float normalizeAngle = PMath.normalizeAngle(PMath.getAngleBetweenPoints(f, f2, f3, f4));
        if (normalizeAngle < 45.0f || normalizeAngle > 315.0f) {
            return Direction.TOP;
        }
        if (normalizeAngle >= 45.0f && normalizeAngle < 135.0f) {
            return Direction.LEFT;
        }
        if (normalizeAngle >= 135.0f && normalizeAngle < 225.0f) {
            return Direction.BOTTOM;
        }
        return Direction.RIGHT;
    }
}
