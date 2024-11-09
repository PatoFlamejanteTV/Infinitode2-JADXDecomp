package com.badlogic.gdx.ai.sched;

import com.badlogic.gdx.ai.sched.SchedulerBase;
import com.badlogic.gdx.utils.TimeUtils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/sched/LoadBalancingScheduler.class */
public class LoadBalancingScheduler extends SchedulerBase<SchedulerBase.SchedulableRecord> {
    protected int frame;

    public LoadBalancingScheduler(int i) {
        super(i);
        this.frame = 0;
    }

    @Override // com.badlogic.gdx.ai.sched.Scheduler
    public void addWithAutomaticPhasing(Schedulable schedulable, int i) {
        add(schedulable, i, calculatePhase(i));
    }

    @Override // com.badlogic.gdx.ai.sched.Scheduler
    public void add(Schedulable schedulable, int i, int i2) {
        this.schedulableRecords.add(new SchedulerBase.SchedulableRecord(schedulable, i, i2));
    }

    @Override // com.badlogic.gdx.ai.sched.Schedulable
    public void run(long j) {
        this.frame++;
        this.runList.size = 0;
        for (int i = 0; i < this.schedulableRecords.size; i++) {
            SchedulerBase.SchedulableRecord schedulableRecord = (SchedulerBase.SchedulableRecord) this.schedulableRecords.get(i);
            if ((this.frame + schedulableRecord.phase) % schedulableRecord.frequency == 0) {
                this.runList.add(schedulableRecord);
            }
        }
        long nanoTime = TimeUtils.nanoTime();
        int i2 = this.runList.size;
        for (int i3 = 0; i3 < i2; i3++) {
            long nanoTime2 = TimeUtils.nanoTime();
            long j2 = j - (nanoTime2 - nanoTime);
            j = j2;
            ((SchedulerBase.SchedulableRecord) this.runList.get(i3)).schedulable.run(j2 / (i2 - i3));
            nanoTime = nanoTime2;
        }
    }
}
