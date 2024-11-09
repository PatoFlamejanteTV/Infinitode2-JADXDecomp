package com.prineside.tdi2.events.game;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.systems.BonusSystem;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/BonusStageRequirementMet.class */
public final class BonusStageRequirementMet extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private final BonusSystem.BonusStage f1976a;

    public BonusStageRequirementMet(BonusSystem.BonusStage bonusStage) {
        Preconditions.checkNotNull(bonusStage);
        this.f1976a = bonusStage;
    }

    public final BonusSystem.BonusStage getStage() {
        return this.f1976a;
    }
}
