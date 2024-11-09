package com.badlogic.gdx.ai.fma;

import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.utils.Array;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/fma/BoundedSlotAssignmentStrategy.class */
public abstract class BoundedSlotAssignmentStrategy<T extends Vector<T>> implements SlotAssignmentStrategy<T> {
    @Override // com.badlogic.gdx.ai.fma.SlotAssignmentStrategy
    public abstract void updateSlotAssignments(Array<SlotAssignment<T>> array);

    @Override // com.badlogic.gdx.ai.fma.SlotAssignmentStrategy
    public int calculateNumberOfSlots(Array<SlotAssignment<T>> array) {
        int i = -1;
        for (int i2 = 0; i2 < array.size; i2++) {
            SlotAssignment<T> slotAssignment = array.get(i2);
            if (slotAssignment.slotNumber >= i) {
                i = slotAssignment.slotNumber;
            }
        }
        return i + 1;
    }

    @Override // com.badlogic.gdx.ai.fma.SlotAssignmentStrategy
    public void removeSlotAssignment(Array<SlotAssignment<T>> array, int i) {
        int i2 = array.get(i).slotNumber;
        for (int i3 = 0; i3 < array.size; i3++) {
            SlotAssignment<T> slotAssignment = array.get(i3);
            if (slotAssignment.slotNumber >= i2) {
                slotAssignment.slotNumber--;
            }
        }
        array.removeIndex(i);
    }
}
