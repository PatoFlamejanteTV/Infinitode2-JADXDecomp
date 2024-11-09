package com.prineside.tdi2.utils.syncchecker.comparators;

import com.prineside.tdi2.utils.CheatSafeInt;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparator;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparisonConfig;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/syncchecker/comparators/CheatSafeIntComparator.class */
public final class CheatSafeIntComparator extends DeepClassComparator<CheatSafeInt> {
    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final Class<CheatSafeInt> forClass() {
        return CheatSafeInt.class;
    }

    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final void compare(CheatSafeInt cheatSafeInt, CheatSafeInt cheatSafeInt2, DeepClassComparisonConfig deepClassComparisonConfig) {
        if (cheatSafeInt.get() != cheatSafeInt2.get()) {
            deepClassComparisonConfig.appendPrefix().append(": ").append(cheatSafeInt.get()).append(" != ").append(cheatSafeInt2.get()).append(SequenceUtils.EOL);
        }
    }
}
