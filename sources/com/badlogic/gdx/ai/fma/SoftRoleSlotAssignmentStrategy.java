package com.badlogic.gdx.ai.fma;

import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.BooleanArray;
import com.badlogic.gdx.utils.GdxRuntimeException;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/fma/SoftRoleSlotAssignmentStrategy.class */
public class SoftRoleSlotAssignmentStrategy<T extends Vector<T>> extends BoundedSlotAssignmentStrategy<T> {
    protected SlotCostProvider<T> slotCostProvider;
    protected float costThreshold;
    private BooleanArray filledSlots;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/fma/SoftRoleSlotAssignmentStrategy$SlotCostProvider.class */
    public interface SlotCostProvider<T extends Vector<T>> {
        float getCost(FormationMember<T> formationMember, int i);
    }

    public SoftRoleSlotAssignmentStrategy(SlotCostProvider<T> slotCostProvider) {
        this(slotCostProvider, Float.POSITIVE_INFINITY);
    }

    public SoftRoleSlotAssignmentStrategy(SlotCostProvider<T> slotCostProvider, float f) {
        this.slotCostProvider = slotCostProvider;
        this.costThreshold = f;
        this.filledSlots = new BooleanArray();
    }

    @Override // com.badlogic.gdx.ai.fma.BoundedSlotAssignmentStrategy, com.badlogic.gdx.ai.fma.SlotAssignmentStrategy
    public void updateSlotAssignments(Array<SlotAssignment<T>> array) {
        Array array2 = new Array();
        int i = array.size;
        for (int i2 = 0; i2 < i; i2++) {
            SlotAssignment<T> slotAssignment = array.get(i2);
            MemberAndSlots memberAndSlots = new MemberAndSlots(slotAssignment.member);
            for (int i3 = 0; i3 < i; i3++) {
                float cost = this.slotCostProvider.getCost(slotAssignment.member, i3);
                if (cost < this.costThreshold) {
                    memberAndSlots.costAndSlots.add(new CostAndSlot<>(cost, array.get(i3).slotNumber));
                    memberAndSlots.assignmentEase += 1.0f / (cost + 1.0f);
                }
            }
            array2.add(memberAndSlots);
        }
        if (i > this.filledSlots.size) {
            BooleanArray booleanArray = this.filledSlots;
            booleanArray.ensureCapacity(i - booleanArray.size);
        }
        this.filledSlots.size = i;
        for (int i4 = 0; i4 < i; i4++) {
            this.filledSlots.set(i4, false);
        }
        array2.sort();
        for (int i5 = 0; i5 < array2.size; i5++) {
            MemberAndSlots memberAndSlots2 = (MemberAndSlots) array2.get(i5);
            memberAndSlots2.costAndSlots.sort();
            int i6 = memberAndSlots2.costAndSlots.size;
            for (int i7 = 0; i7 < i6; i7++) {
                int i8 = memberAndSlots2.costAndSlots.get(i7).slotNumber;
                if (!this.filledSlots.get(i8)) {
                    SlotAssignment<T> slotAssignment2 = array.get(i8);
                    slotAssignment2.member = memberAndSlots2.member;
                    slotAssignment2.slotNumber = i8;
                    this.filledSlots.set(i8, true);
                }
            }
            throw new GdxRuntimeException("SoftRoleSlotAssignmentStrategy cannot find valid slot assignment for member " + memberAndSlots2.member);
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/fma/SoftRoleSlotAssignmentStrategy$CostAndSlot.class */
    static class CostAndSlot<T extends Vector<T>> implements Comparable<CostAndSlot<T>> {
        float cost;
        int slotNumber;

        public CostAndSlot(float f, int i) {
            this.cost = f;
            this.slotNumber = i;
        }

        @Override // java.lang.Comparable
        public int compareTo(CostAndSlot<T> costAndSlot) {
            if (this.cost < costAndSlot.cost) {
                return -1;
            }
            return this.cost > costAndSlot.cost ? 1 : 0;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/fma/SoftRoleSlotAssignmentStrategy$MemberAndSlots.class */
    static class MemberAndSlots<T extends Vector<T>> implements Comparable<MemberAndSlots<T>> {
        FormationMember<T> member;
        float assignmentEase = 0.0f;
        Array<CostAndSlot<T>> costAndSlots = new Array<>();

        public MemberAndSlots(FormationMember<T> formationMember) {
            this.member = formationMember;
        }

        @Override // java.lang.Comparable
        public int compareTo(MemberAndSlots<T> memberAndSlots) {
            if (this.assignmentEase < memberAndSlots.assignmentEase) {
                return -1;
            }
            return this.assignmentEase > memberAndSlots.assignmentEase ? 1 : 0;
        }
    }
}
