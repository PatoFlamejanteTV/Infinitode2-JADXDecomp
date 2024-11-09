package com.prineside.tdi2.utils.syncchecker.comparators;

import com.badlogic.gdx.utils.IdentityMap;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparator;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparisonConfig;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/syncchecker/comparators/IdentityMapComparator.class */
public final class IdentityMapComparator extends DeepClassComparator<IdentityMap> {
    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final Class<IdentityMap> forClass() {
        return IdentityMap.class;
    }

    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final void compare(IdentityMap identityMap, IdentityMap identityMap2, DeepClassComparisonConfig deepClassComparisonConfig) {
        if (identityMap.size != identityMap2.size) {
            deepClassComparisonConfig.appendPrefix().append(": sizes do not match").append(identityMap.size).append(" != ").append(identityMap2.size).append(SequenceUtils.EOL);
        }
    }
}
