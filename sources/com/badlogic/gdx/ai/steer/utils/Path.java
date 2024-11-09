package com.badlogic.gdx.ai.steer.utils;

import com.badlogic.gdx.ai.steer.utils.Path.PathParam;
import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/utils/Path.class */
public interface Path<T extends Vector<T>, P extends PathParam> {

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/utils/Path$PathParam.class */
    public interface PathParam {
        float getDistance();

        void setDistance(float f);
    }

    P createParam();

    boolean isOpen();

    float getLength();

    T getStartPoint();

    T getEndPoint();

    float calculateDistance(T t, P p);

    void calculateTargetPosition(T t, P p, float f);
}
