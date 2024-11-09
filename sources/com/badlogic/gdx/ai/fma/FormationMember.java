package com.badlogic.gdx.ai.fma;

import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/fma/FormationMember.class */
public interface FormationMember<T extends Vector<T>> {
    Location<T> getTargetLocation();
}
