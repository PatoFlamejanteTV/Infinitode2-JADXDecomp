package com.prineside.tdi2.utils.syncchecker.comparators;

import com.badlogic.gdx.utils.IntSet;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparator;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparisonConfig;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/syncchecker/comparators/IntSetComparator.class */
public final class IntSetComparator extends DeepClassComparator<IntSet> {
    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final Class<IntSet> forClass() {
        return IntSet.class;
    }

    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final void compare(IntSet intSet, IntSet intSet2, DeepClassComparisonConfig deepClassComparisonConfig) {
        if (intSet.size != intSet2.size) {
            deepClassComparisonConfig.appendPrefix().append(": sizes do not match").append(intSet.size).append(" != ").append(intSet2.size).append(SequenceUtils.EOL);
        }
        IntSet.IntSetIterator it = intSet.iterator();
        while (it.hasNext) {
            int next = it.next();
            if (!intSet2.contains(next)) {
                deepClassComparisonConfig.appendPrefix().append("[").append(next).append("]: key not exists\n");
            }
        }
    }
}
