package com.badlogic.gdx.ai.sched;

import com.badlogic.gdx.ai.sched.SchedulerBase;
import com.badlogic.gdx.utils.TimeUtils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/sched/PriorityScheduler.class */
public class PriorityScheduler extends SchedulerBase<PrioritySchedulableRecord> {
    protected int frame;

    public PriorityScheduler(int i) {
        super(i);
        this.frame = 0;
    }

    @Override // com.badlogic.gdx.ai.sched.Schedulable
    public void run(long j) {
        this.frame++;
        this.runList.size = 0;
        float f = 0.0f;
        for (int i = 0; i < this.schedulableRecords.size; i++) {
            PrioritySchedulableRecord prioritySchedulableRecord = (PrioritySchedulableRecord) this.schedulableRecords.get(i);
            if ((this.frame + prioritySchedulableRecord.phase) % prioritySchedulableRecord.frequency == 0) {
                this.runList.add(prioritySchedulableRecord);
                f += prioritySchedulableRecord.priority;
            }
        }
        long nanoTime = TimeUtils.nanoTime();
        int i2 = this.runList.size;
        for (int i3 = 0; i3 < i2; i3++) {
            long nanoTime2 = TimeUtils.nanoTime();
            j -= nanoTime2 - nanoTime;
            ((PrioritySchedulableRecord) this.runList.get(i3)).schedulable.run((((float) j) * r0.priority) / f);
            nanoTime = nanoTime2;
        }
    }

    @Override // com.badlogic.gdx.ai.sched.Scheduler
    public void addWithAutomaticPhasing(Schedulable schedulable, int i) {
        addWithAutomaticPhasing(schedulable, i, 1.0f);
    }

    public void addWithAutomaticPhasing(Schedulable schedulable, int i, float f) {
        add(schedulable, i, calculatePhase(i), f);
    }

    @Override // com.badlogic.gdx.ai.sched.Scheduler
    public void add(Schedulable schedulable, int i, int i2) {
        add(schedulable, i, i2, 1.0f);
    }

    public void add(Schedulable schedulable, int i, int i2, float f) {
        this.schedulableRecords.add(new PrioritySchedulableRecord(schedulable, i, i2, f));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/sched/PriorityScheduler$PrioritySchedulableRecord.class */
    public static class PrioritySchedulableRecord extends SchedulerBase.SchedulableRecord {
        float priority;

        PrioritySchedulableRecord(Schedulable schedulable, int i, int i2, float f) {
            super(schedulable, i, i2);
            this.priority = f;
        }
    }
}
