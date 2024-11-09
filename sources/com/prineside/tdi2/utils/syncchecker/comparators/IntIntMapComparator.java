package com.prineside.tdi2.utils.syncchecker.comparators;

import com.badlogic.gdx.utils.IntIntMap;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparator;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparisonConfig;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/syncchecker/comparators/IntIntMapComparator.class */
public final class IntIntMapComparator extends DeepClassComparator<IntIntMap> {
    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final Class<IntIntMap> forClass() {
        return IntIntMap.class;
    }

    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final void compare(IntIntMap intIntMap, IntIntMap intIntMap2, DeepClassComparisonConfig deepClassComparisonConfig) {
        if (intIntMap.size != intIntMap2.size) {
            deepClassComparisonConfig.appendPrefix().append(": sizes do not match").append(intIntMap.size).append(" != ").append(intIntMap2.size).append(SequenceUtils.EOL);
        }
        Iterator<IntIntMap.Entry> it = intIntMap.iterator();
        while (it.hasNext()) {
            IntIntMap.Entry next = it.next();
            if (!intIntMap2.containsKey(next.key)) {
                deepClassComparisonConfig.appendPrefix().append("[").append(next.key).append("]: key not exists\n");
            } else if (next.value != intIntMap2.get(next.key, 0)) {
                deepClassComparisonConfig.appendPrefix().append("[").append(next.key).append("]: ").append(next.value).append(" != ").append(intIntMap2.get(next.key, 0)).append(SequenceUtils.EOL);
            }
        }
    }
}
