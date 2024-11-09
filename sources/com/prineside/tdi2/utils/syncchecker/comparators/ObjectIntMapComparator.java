package com.prineside.tdi2.utils.syncchecker.comparators;

import com.badlogic.gdx.utils.ObjectIntMap;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparator;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparisonConfig;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/syncchecker/comparators/ObjectIntMapComparator.class */
public final class ObjectIntMapComparator extends DeepClassComparator<ObjectIntMap> {
    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final Class<ObjectIntMap> forClass() {
        return ObjectIntMap.class;
    }

    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final void compare(ObjectIntMap objectIntMap, ObjectIntMap objectIntMap2, DeepClassComparisonConfig deepClassComparisonConfig) {
        if (objectIntMap.size != objectIntMap2.size) {
            deepClassComparisonConfig.appendPrefix().append(": sizes do not match").append(objectIntMap.size).append(" != ").append(objectIntMap2.size).append(SequenceUtils.EOL);
        }
    }
}
