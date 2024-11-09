package com.badlogic.gdx.ai.fma.patterns;

import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/fma/patterns/OffensiveCircleFormationPattern.class */
public class OffensiveCircleFormationPattern<T extends Vector<T>> extends DefensiveCircleFormationPattern<T> {
    public OffensiveCircleFormationPattern(float f) {
        super(f);
    }

    @Override // com.badlogic.gdx.ai.fma.patterns.DefensiveCircleFormationPattern, com.badlogic.gdx.ai.fma.FormationPattern
    public Location<T> calculateSlotLocation(Location<T> location, int i) {
        super.calculateSlotLocation(location, i);
        location.setOrientation(location.getOrientation() + 3.1415927f);
        return location;
    }
}
