package com.badlogic.gdx.ai.steer.utils;

import com.badlogic.gdx.ai.utils.Ray;
import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/steer/utils/RayConfiguration.class */
public interface RayConfiguration<T extends Vector<T>> {
    Ray<T>[] updateRays();
}
