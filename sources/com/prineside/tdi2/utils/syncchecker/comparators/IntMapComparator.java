package com.prineside.tdi2.utils.syncchecker.comparators;

import com.badlogic.gdx.utils.IntMap;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparator;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparisonConfig;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/syncchecker/comparators/IntMapComparator.class */
public final class IntMapComparator extends DeepClassComparator<IntMap> {
    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final Class<IntMap> forClass() {
        return IntMap.class;
    }

    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final void compare(IntMap intMap, IntMap intMap2, DeepClassComparisonConfig deepClassComparisonConfig) {
        if (intMap.size != intMap2.size) {
            deepClassComparisonConfig.appendPrefix().append(": sizes do not match").append(intMap.size).append(" != ").append(intMap2.size).append(SequenceUtils.EOL);
        }
        IntMap.Keys keys = intMap.keys();
        while (keys.hasNext) {
            int next = keys.next();
            if (!intMap2.containsKey(next)) {
                deepClassComparisonConfig.appendPrefix().append("[").append(next).append("]: key not exists (object: ").append(intMap.get(next).getClass().getName()).append(")\n");
            } else {
                Object obj = intMap.get(next);
                Object obj2 = intMap2.get(next);
                deepClassComparisonConfig.addPrefix("[", SyncChecker.toString(next), SequenceUtils.SPACE, obj.getClass().getName(), "]");
                SyncChecker.compareObjects(obj, obj2, deepClassComparisonConfig);
                deepClassComparisonConfig.popPrefix(5);
            }
        }
    }
}
