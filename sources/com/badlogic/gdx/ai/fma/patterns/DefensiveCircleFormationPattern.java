package com.badlogic.gdx.ai.fma.patterns;

import com.badlogic.gdx.ai.fma.FormationPattern;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/fma/patterns/DefensiveCircleFormationPattern.class */
public class DefensiveCircleFormationPattern<T extends Vector<T>> implements FormationPattern<T> {
    int numberOfSlots;
    float memberRadius;

    public DefensiveCircleFormationPattern(float f) {
        this.memberRadius = f;
    }

    @Override // com.badlogic.gdx.ai.fma.FormationPattern
    public void setNumberOfSlots(int i) {
        this.numberOfSlots = i;
    }

    @Override // com.badlogic.gdx.ai.fma.FormationPattern
    public Location<T> calculateSlotLocation(Location<T> location, int i) {
        if (this.numberOfSlots > 1) {
            float f = (6.2831855f * i) / this.numberOfSlots;
            location.angleToVector(location.getPosition(), f).scl(this.memberRadius / ((float) Math.sin(3.141592653589793d / this.numberOfSlots)));
            location.setOrientation(f);
        } else {
            location.getPosition().setZero();
            location.setOrientation(6.2831855f * i);
        }
        return location;
    }

    @Override // com.badlogic.gdx.ai.fma.FormationPattern
    public boolean supportsSlots(int i) {
        return true;
    }
}
