package com.prineside.tdi2.utils.syncchecker.comparators;

import com.badlogic.gdx.utils.ObjectSet;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparator;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparisonConfig;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/syncchecker/comparators/ObjectSetComparator.class */
public final class ObjectSetComparator extends DeepClassComparator<ObjectSet> {
    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final Class<ObjectSet> forClass() {
        return ObjectSet.class;
    }

    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final void compare(ObjectSet objectSet, ObjectSet objectSet2, DeepClassComparisonConfig deepClassComparisonConfig) {
        if (objectSet.size != objectSet2.size) {
            deepClassComparisonConfig.appendPrefix().append(": sizes do not match").append(objectSet.size).append(" != ").append(objectSet2.size).append(SequenceUtils.EOL);
        }
        ObjectSet.ObjectSetIterator it = objectSet.iterator();
        while (it.hasNext) {
            Object next = it.next();
            if (next.getClass().isEnum() && !objectSet2.contains(next)) {
                deepClassComparisonConfig.appendPrefix().append("[").append(next).append("]: key not exists\n");
            }
        }
    }
}
