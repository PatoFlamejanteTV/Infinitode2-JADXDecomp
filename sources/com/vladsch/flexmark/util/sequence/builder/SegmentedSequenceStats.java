package com.vladsch.flexmark.util.sequence.builder;

import com.vladsch.flexmark.util.misc.MinMaxAvgLong;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/builder/SegmentedSequenceStats.class */
public class SegmentedSequenceStats {
    private ArrayList<StatsEntry> aggregatedStats;
    private final HashMap<StatsEntry, StatsEntry> stats = new HashMap<>();
    static final ArrayList<Integer> AGGR_STEPS;
    static final int MAX_BUCKETS;
    static final /* synthetic */ boolean $assertionsDisabled;

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/sequence/builder/SegmentedSequenceStats$StatsEntry.class */
    public static class StatsEntry implements Comparable<StatsEntry> {
        int segments;
        int count;
        final MinMaxAvgLong segStats = new MinMaxAvgLong();
        final MinMaxAvgLong length = new MinMaxAvgLong();
        final MinMaxAvgLong overhead = new MinMaxAvgLong();
        static final /* synthetic */ boolean $assertionsDisabled;

        static {
            $assertionsDisabled = !SegmentedSequenceStats.class.desiredAssertionStatus();
        }

        public StatsEntry(int i) {
            if (!$assertionsDisabled && i <= 0) {
                throw new AssertionError("segments: " + i + " < 1");
            }
            this.segments = i;
        }

        public void add(int i, int i2, int i3) {
            this.count++;
            this.segStats.add(i);
            this.length.add(i2);
            this.overhead.add(i3);
        }

        public void add(StatsEntry statsEntry) {
            this.count += statsEntry.count;
            this.segStats.add(statsEntry.segStats);
            this.length.add(statsEntry.length);
            this.overhead.add(statsEntry.overhead);
        }

        @Override // java.lang.Comparable
        public int compareTo(StatsEntry statsEntry) {
            int compare = Integer.compare(this.segments, statsEntry.segments);
            if (compare != 0) {
                return compare;
            }
            return Integer.compare(this.count, statsEntry.count);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.segments == ((StatsEntry) obj).segments;
        }

        public int hashCode() {
            return this.segments;
        }
    }

    static {
        $assertionsDisabled = !SegmentedSequenceStats.class.desiredAssertionStatus();
        ArrayList<Integer> arrayList = new ArrayList<>();
        AGGR_STEPS = arrayList;
        arrayList.add(1);
        AGGR_STEPS.add(2);
        AGGR_STEPS.add(3);
        AGGR_STEPS.add(4);
        AGGR_STEPS.add(5);
        AGGR_STEPS.add(6);
        AGGR_STEPS.add(7);
        AGGR_STEPS.add(8);
        AGGR_STEPS.add(15);
        AGGR_STEPS.add(16);
        AGGR_STEPS.add(256);
        int i = 65536;
        while (true) {
            int i2 = i;
            if (i2 >= 1048576) {
                MAX_BUCKETS = AGGR_STEPS.size();
                return;
            } else {
                AGGR_STEPS.add(Integer.valueOf(i2));
                i = i2 + 65536;
            }
        }
    }

    private SegmentedSequenceStats() {
    }

    public void addStats(int i, int i2, int i3) {
        this.stats.computeIfAbsent(new StatsEntry(i), statsEntry -> {
            return statsEntry;
        }).add(i, i2, i3);
    }

    public int getCount(int i) {
        StatsEntry statsEntry = new StatsEntry(i);
        if (this.stats.containsKey(statsEntry)) {
            return this.stats.get(statsEntry).count;
        }
        return 0;
    }

    public String getStatsText(List<StatsEntry> list) {
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        sb.append(String.format("%10s,%10s,%10s,%10s,%10s,%10s,%10s,%10s,%10s,%10s,%10s,%10s,%10s,%8s", "count", "min-seg", "avg-seg", "max-seg", "min-len", "avg-len", "max-len", "min-ovr", "avg-ovr", "max-ovr", "tot-len", "tot-chr", "tot-ovr", "ovr %")).append(SequenceUtils.EOL);
        int i = size;
        while (true) {
            int i2 = i;
            i--;
            if (i2 > 0) {
                StatsEntry statsEntry = list.get(i);
                Object[] objArr = new Object[14];
                objArr[0] = Integer.valueOf(statsEntry.count);
                objArr[1] = Long.valueOf(statsEntry.count == 1 ? statsEntry.segments : statsEntry.segStats.getMin());
                objArr[2] = Long.valueOf(statsEntry.count == 1 ? statsEntry.segments : statsEntry.segStats.getAvg(statsEntry.count));
                objArr[3] = Long.valueOf(statsEntry.count == 1 ? statsEntry.segments : statsEntry.segStats.getMax());
                objArr[4] = Long.valueOf(statsEntry.length.getMin());
                objArr[5] = Long.valueOf(statsEntry.length.getAvg(statsEntry.count));
                objArr[6] = Long.valueOf(statsEntry.length.getMax());
                objArr[7] = Long.valueOf(statsEntry.overhead.getMin());
                objArr[8] = Long.valueOf(statsEntry.overhead.getAvg(statsEntry.count));
                objArr[9] = Long.valueOf(statsEntry.overhead.getMax());
                objArr[10] = Long.valueOf(statsEntry.length.getTotal());
                objArr[11] = Long.valueOf(statsEntry.length.getTotal() << 1);
                objArr[12] = Long.valueOf(statsEntry.overhead.getTotal());
                objArr[13] = Double.valueOf(statsEntry.length.getTotal() == 0 ? 0.0d : ((100.0d * statsEntry.overhead.getTotal()) / statsEntry.length.getTotal()) / 2.0d);
                sb.append(String.format("%10d,%10d,%10d,%10d,%10d,%10d,%10d,%10d,%10d,%10d,%10d,%10d,%10d,%8.3f", objArr)).append(SequenceUtils.EOL);
            } else {
                return sb.toString();
            }
        }
    }

    public String getAggregatedStatsText() {
        return getStatsText(getAggregatedStats());
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x006e, code lost:            if (r0.segments < r8) goto L13;     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0072, code lost:            if (r7 <= 0) goto L42;     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0075, code lost:            r7 = r7 - 1;        r8 = com.vladsch.flexmark.util.sequence.builder.SegmentedSequenceStats.AGGR_STEPS.get(r7).intValue();     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x008c, code lost:            if (r0.segments < r8) goto L44;     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0092, code lost:            if (com.vladsch.flexmark.util.sequence.builder.SegmentedSequenceStats.$assertionsDisabled != false) goto L23;     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0096, code lost:            if (r7 >= 0) goto L23;     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00a0, code lost:            throw new java.lang.AssertionError();     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00a4, code lost:            if (com.vladsch.flexmark.util.sequence.builder.SegmentedSequenceStats.$assertionsDisabled != false) goto L29;     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00ad, code lost:            if (r0.segments >= r8) goto L29;     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00b7, code lost:            throw new java.lang.AssertionError();     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00b8, code lost:            r0 = r5.aggregatedStats.get(r7);        r11 = r0;     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00c6, code lost:            if (r0 != null) goto L41;     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00c9, code lost:            r11 = new com.vladsch.flexmark.util.sequence.builder.SegmentedSequenceStats.StatsEntry(r8);        r5.aggregatedStats.set(r7, r11);     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.List<com.vladsch.flexmark.util.sequence.builder.SegmentedSequenceStats.StatsEntry> getAggregatedStats() {
        /*
            Method dump skipped, instructions count: 250
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vladsch.flexmark.util.sequence.builder.SegmentedSequenceStats.getAggregatedStats():java.util.List");
    }

    public String getStatsText() {
        return getStatsText(getStats());
    }

    public void clear() {
        this.stats.clear();
    }

    public List<StatsEntry> getStats() {
        ArrayList arrayList = new ArrayList(this.stats.keySet());
        arrayList.sort((v0, v1) -> {
            return v0.compareTo(v1);
        });
        return arrayList;
    }

    public static SegmentedSequenceStats getInstance() {
        return new SegmentedSequenceStats();
    }
}
