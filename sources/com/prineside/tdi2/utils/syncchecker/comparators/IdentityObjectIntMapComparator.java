package com.prineside.tdi2.utils.syncchecker.comparators;

import com.esotericsoftware.kryo.util.IdentityObjectIntMap;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparator;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparisonConfig;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/syncchecker/comparators/IdentityObjectIntMapComparator.class */
public final class IdentityObjectIntMapComparator extends DeepClassComparator<IdentityObjectIntMap> {
    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final Class<IdentityObjectIntMap> forClass() {
        return IdentityObjectIntMap.class;
    }

    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final void compare(IdentityObjectIntMap identityObjectIntMap, IdentityObjectIntMap identityObjectIntMap2, DeepClassComparisonConfig deepClassComparisonConfig) {
        if (identityObjectIntMap.size != identityObjectIntMap2.size) {
            deepClassComparisonConfig.appendPrefix().append(": sizes do not match").append(identityObjectIntMap.size).append(" != ").append(identityObjectIntMap2.size).append(SequenceUtils.EOL);
        }
    }
}
