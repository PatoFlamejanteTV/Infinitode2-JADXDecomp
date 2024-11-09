package com.badlogic.gdx.ai.fma;

import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/fma/Formation.class */
public class Formation<T extends Vector<T>> {
    Array<SlotAssignment<T>> slotAssignments;
    protected Location<T> anchor;
    protected FormationPattern<T> pattern;
    protected SlotAssignmentStrategy<T> slotAssignmentStrategy;
    protected FormationMotionModerator<T> motionModerator;
    private final T positionOffset;
    private final Matrix3 orientationMatrix;
    private final Location<T> driftOffset;

    public Formation(Location<T> location, FormationPattern<T> formationPattern) {
        this(location, formationPattern, new FreeSlotAssignmentStrategy(), null);
    }

    public Formation(Location<T> location, FormationPattern<T> formationPattern, SlotAssignmentStrategy<T> slotAssignmentStrategy) {
        this(location, formationPattern, slotAssignmentStrategy, null);
    }

    public Formation(Location<T> location, FormationPattern<T> formationPattern, SlotAssignmentStrategy<T> slotAssignmentStrategy, FormationMotionModerator<T> formationMotionModerator) {
        this.orientationMatrix = new Matrix3();
        if (location == null) {
            throw new IllegalArgumentException("The anchor point cannot be null");
        }
        this.anchor = location;
        this.pattern = formationPattern;
        this.slotAssignmentStrategy = slotAssignmentStrategy;
        this.motionModerator = formationMotionModerator;
        this.slotAssignments = new Array<>();
        this.driftOffset = location.newLocation();
        this.positionOffset = (T) location.getPosition().cpy();
    }

    public Location<T> getAnchorPoint() {
        return this.anchor;
    }

    public void setAnchorPoint(Location<T> location) {
        this.anchor = location;
    }

    public FormationPattern<T> getPattern() {
        return this.pattern;
    }

    public void setPattern(FormationPattern<T> formationPattern) {
        this.pattern = formationPattern;
    }

    public SlotAssignmentStrategy<T> getSlotAssignmentStrategy() {
        return this.slotAssignmentStrategy;
    }

    public void setSlotAssignmentStrategy(SlotAssignmentStrategy<T> slotAssignmentStrategy) {
        this.slotAssignmentStrategy = slotAssignmentStrategy;
    }

    public FormationMotionModerator<T> getMotionModerator() {
        return this.motionModerator;
    }

    public void setMotionModerator(FormationMotionModerator<T> formationMotionModerator) {
        this.motionModerator = formationMotionModerator;
    }

    public void updateSlotAssignments() {
        this.slotAssignmentStrategy.updateSlotAssignments(this.slotAssignments);
        this.pattern.setNumberOfSlots(this.slotAssignmentStrategy.calculateNumberOfSlots(this.slotAssignments));
        if (this.motionModerator != null) {
            this.motionModerator.calculateDriftOffset(this.driftOffset, this.slotAssignments, this.pattern);
        }
    }

    public boolean changePattern(FormationPattern<T> formationPattern) {
        if (formationPattern.supportsSlots(this.slotAssignments.size)) {
            setPattern(formationPattern);
            updateSlotAssignments();
            return true;
        }
        return false;
    }

    public boolean addMember(FormationMember<T> formationMember) {
        int i = this.slotAssignments.size;
        if (this.pattern.supportsSlots(i + 1)) {
            this.slotAssignments.add(new SlotAssignment<>(formationMember, i));
            updateSlotAssignments();
            return true;
        }
        return false;
    }

    public void removeMember(FormationMember<T> formationMember) {
        int findMemberSlot = findMemberSlot(formationMember);
        if (findMemberSlot >= 0) {
            this.slotAssignmentStrategy.removeSlotAssignment(this.slotAssignments, findMemberSlot);
            updateSlotAssignments();
        }
    }

    private int findMemberSlot(FormationMember<T> formationMember) {
        for (int i = 0; i < this.slotAssignments.size; i++) {
            if (this.slotAssignments.get(i).member == formationMember) {
                return i;
            }
        }
        return -1;
    }

    public SlotAssignment<T> getSlotAssignmentAt(int i) {
        return this.slotAssignments.get(i);
    }

    public int getSlotAssignmentCount() {
        return this.slotAssignments.size;
    }

    public void updateSlots() {
        Location<T> anchorPoint = getAnchorPoint();
        this.positionOffset.set(anchorPoint.getPosition());
        float orientation = anchorPoint.getOrientation();
        if (this.motionModerator != null) {
            this.positionOffset.sub(this.driftOffset.getPosition());
            orientation -= this.driftOffset.getOrientation();
        }
        this.orientationMatrix.idt().rotateRad(anchorPoint.getOrientation());
        for (int i = 0; i < this.slotAssignments.size; i++) {
            SlotAssignment<T> slotAssignment = this.slotAssignments.get(i);
            Location<T> targetLocation = slotAssignment.member.getTargetLocation();
            this.pattern.calculateSlotLocation(targetLocation, slotAssignment.slotNumber);
            T position = targetLocation.getPosition();
            if (position instanceof Vector2) {
                ((Vector2) position).mul(this.orientationMatrix);
            } else if (position instanceof Vector3) {
                ((Vector3) position).mul(this.orientationMatrix);
            }
            position.add(this.positionOffset);
            targetLocation.setOrientation(targetLocation.getOrientation() + orientation);
        }
        if (this.motionModerator != null) {
            this.motionModerator.updateAnchorPoint(anchorPoint);
        }
    }
}
