package com.badlogic.gdx.ai.sched;

import com.badlogic.gdx.ai.sched.SchedulerBase.SchedulableRecord;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/sched/SchedulerBase.class */
public abstract class SchedulerBase<T extends SchedulableRecord> implements Scheduler {
    protected Array<T> schedulableRecords = new Array<>();
    protected Array<T> runList = new Array<>();
    protected IntArray phaseCounters = new IntArray();
    protected int dryRunFrames;

    public SchedulerBase(int i) {
        this.dryRunFrames = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int calculatePhase(int i) {
        if (i > this.phaseCounters.size) {
            IntArray intArray = this.phaseCounters;
            intArray.ensureCapacity(i - intArray.size);
        }
        int[] iArr = this.phaseCounters.items;
        this.phaseCounters.size = i;
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = 0;
        }
        for (int i3 = 0; i3 < this.dryRunFrames; i3++) {
            int i4 = i3 % i;
            for (int i5 = 0; i5 < this.schedulableRecords.size; i5++) {
                T t = this.schedulableRecords.get(i5);
                if ((i3 - t.phase) % t.frequency == 0) {
                    iArr[i4] = iArr[i4] + 1;
                }
            }
        }
        int i6 = Integer.MAX_VALUE;
        int i7 = -1;
        for (int i8 = 0; i8 < i; i8++) {
            if (iArr[i8] < i6) {
                i6 = iArr[i8];
                i7 = i8;
            }
        }
        return i7;
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/sched/SchedulerBase$SchedulableRecord.class */
    protected static class SchedulableRecord {
        Schedulable schedulable;
        int frequency;
        int phase;

        /* JADX INFO: Access modifiers changed from: package-private */
        public SchedulableRecord(Schedulable schedulable, int i, int i2) {
            this.schedulable = schedulable;
            this.frequency = i;
            this.phase = i2;
        }
    }
}
