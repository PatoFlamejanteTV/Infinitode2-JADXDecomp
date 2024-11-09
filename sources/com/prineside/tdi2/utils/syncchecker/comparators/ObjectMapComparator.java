package com.prineside.tdi2.utils.syncchecker.comparators;

import com.badlogic.gdx.utils.ObjectMap;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparator;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparisonConfig;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/syncchecker/comparators/ObjectMapComparator.class */
public final class ObjectMapComparator extends DeepClassComparator<ObjectMap> {
    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final Class<ObjectMap> forClass() {
        return ObjectMap.class;
    }

    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final void compare(ObjectMap objectMap, ObjectMap objectMap2, DeepClassComparisonConfig deepClassComparisonConfig) {
        if (objectMap.size != objectMap2.size) {
            deepClassComparisonConfig.appendPrefix().append(": sizes do not match").append(objectMap.size).append(" != ").append(objectMap2.size).append(SequenceUtils.EOL);
        }
    }
}
