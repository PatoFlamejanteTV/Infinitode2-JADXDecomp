package com.badlogic.gdx.ai.fma;

import com.badlogic.gdx.math.Vector;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/fma/SlotAssignment.class */
public class SlotAssignment<T extends Vector<T>> {
    public FormationMember<T> member;
    public int slotNumber;

    public SlotAssignment(FormationMember<T> formationMember) {
        this(formationMember, 0);
    }

    public SlotAssignment(FormationMember<T> formationMember, int i) {
        this.member = formationMember;
        this.slotNumber = i;
    }
}
