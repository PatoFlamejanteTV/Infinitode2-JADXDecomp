package com.prineside.tdi2.events.game;

import com.badlogic.gdx.utils.Null;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/ScoreChange.class */
public final class ScoreChange extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private long f2040a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f2041b;

    @Null
    private StatisticsType c;

    public ScoreChange(long j, boolean z, @Null StatisticsType statisticsType) {
        setOldValue(j);
        setGained(z);
        setReason(statisticsType);
    }

    public final long getOldValue() {
        return this.f2040a;
    }

    public final ScoreChange setOldValue(long j) {
        this.f2040a = j;
        return this;
    }

    public final boolean isGained() {
        return this.f2041b;
    }

    public final ScoreChange setGained(boolean z) {
        this.f2041b = z;
        return this;
    }

    @Null
    public final StatisticsType getReason() {
        return this.c;
    }

    public final ScoreChange setReason(@Null StatisticsType statisticsType) {
        this.c = statisticsType;
        return this;
    }
}
