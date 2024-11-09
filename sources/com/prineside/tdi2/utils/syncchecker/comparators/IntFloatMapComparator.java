package com.prineside.tdi2.utils.syncchecker.comparators;

import com.badlogic.gdx.utils.IntFloatMap;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparator;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparisonConfig;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/syncchecker/comparators/IntFloatMapComparator.class */
public final class IntFloatMapComparator extends DeepClassComparator<IntFloatMap> {
    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final Class<IntFloatMap> forClass() {
        return IntFloatMap.class;
    }

    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final void compare(IntFloatMap intFloatMap, IntFloatMap intFloatMap2, DeepClassComparisonConfig deepClassComparisonConfig) {
        if (intFloatMap.size != intFloatMap2.size) {
            deepClassComparisonConfig.appendPrefix().append(": sizes do not match").append(intFloatMap.size).append(" != ").append(intFloatMap2.size).append(SequenceUtils.EOL);
        }
        Iterator<IntFloatMap.Entry> it = intFloatMap.iterator();
        while (it.hasNext()) {
            IntFloatMap.Entry next = it.next();
            if (!intFloatMap2.containsKey(next.key)) {
                deepClassComparisonConfig.appendPrefix().append("[").append(next.key).append("]: key not exists\n");
            } else if (next.value != intFloatMap2.get(next.key, 0.0f)) {
                deepClassComparisonConfig.appendPrefix().append("[").append(next.key).append("]: ").append(next.value).append(" != ").append(intFloatMap2.get(next.key, 0.0f)).append(SequenceUtils.EOL);
            }
        }
    }
}
