package com.prineside.tdi2.pathfinding;

import com.badlogic.gdx.math.Vector2;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/pathfinding/SideFunction.class */
public interface SideFunction {
    void position(float f, Vector2 vector2);

    float rotation(float f);

    float speedMultiplier();
}
