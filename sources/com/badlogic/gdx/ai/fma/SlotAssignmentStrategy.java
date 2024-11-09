package com.badlogic.gdx.ai.fma;

import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.utils.Array;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/fma/SlotAssignmentStrategy.class */
public interface SlotAssignmentStrategy<T extends Vector<T>> {
    void updateSlotAssignments(Array<SlotAssignment<T>> array);

    int calculateNumberOfSlots(Array<SlotAssignment<T>> array);

    void removeSlotAssignment(Array<SlotAssignment<T>> array, int i);
}
