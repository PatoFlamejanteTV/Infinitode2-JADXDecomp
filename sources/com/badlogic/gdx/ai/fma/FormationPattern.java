package com.badlogic.gdx.ai.fma;

import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/fma/FormationPattern.class */
public interface FormationPattern<T extends Vector<T>> {
    void setNumberOfSlots(int i);

    Location<T> calculateSlotLocation(Location<T> location, int i);

    boolean supportsSlots(int i);
}
