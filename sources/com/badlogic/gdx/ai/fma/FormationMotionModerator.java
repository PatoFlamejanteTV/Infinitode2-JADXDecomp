package com.badlogic.gdx.ai.fma;

import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.utils.Array;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/fma/FormationMotionModerator.class */
public abstract class FormationMotionModerator<T extends Vector<T>> {
    private Location<T> tempLocation;

    public abstract void updateAnchorPoint(Location<T> location);

    public Location<T> calculateDriftOffset(Location<T> location, Array<SlotAssignment<T>> array, FormationPattern<T> formationPattern) {
        location.getPosition().setZero();
        float f = 0.0f;
        if (this.tempLocation == null) {
            this.tempLocation = location.newLocation();
        }
        T position = location.getPosition();
        T position2 = this.tempLocation.getPosition();
        float f2 = array.size;
        for (int i = 0; i < f2; i++) {
            formationPattern.calculateSlotLocation(this.tempLocation, array.get(i).slotNumber);
            position.add(position2);
            f += this.tempLocation.getOrientation();
        }
        position.scl(1.0f / f2);
        location.setOrientation(f / f2);
        return location;
    }
}
