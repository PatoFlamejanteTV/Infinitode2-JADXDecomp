package com.badlogic.gdx.ai.fma;

import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.utils.Array;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/fma/FreeSlotAssignmentStrategy.class */
public class FreeSlotAssignmentStrategy<T extends Vector<T>> implements SlotAssignmentStrategy<T> {
    @Override // com.badlogic.gdx.ai.fma.SlotAssignmentStrategy
    public void updateSlotAssignments(Array<SlotAssignment<T>> array) {
        for (int i = 0; i < array.size; i++) {
            array.get(i).slotNumber = i;
        }
    }

    @Override // com.badlogic.gdx.ai.fma.SlotAssignmentStrategy
    public int calculateNumberOfSlots(Array<SlotAssignment<T>> array) {
        return array.size;
    }

    @Override // com.badlogic.gdx.ai.fma.SlotAssignmentStrategy
    public void removeSlotAssignment(Array<SlotAssignment<T>> array, int i) {
        array.removeIndex(i);
    }
}
