package com.prineside.tdi2.utils.syncchecker.comparators;

import com.prineside.tdi2.utils.CheatSafeLong;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparator;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparisonConfig;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/syncchecker/comparators/CheatSafeLongComparator.class */
public final class CheatSafeLongComparator extends DeepClassComparator<CheatSafeLong> {
    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final Class<CheatSafeLong> forClass() {
        return CheatSafeLong.class;
    }

    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final void compare(CheatSafeLong cheatSafeLong, CheatSafeLong cheatSafeLong2, DeepClassComparisonConfig deepClassComparisonConfig) {
        if (cheatSafeLong.get() != cheatSafeLong2.get()) {
            deepClassComparisonConfig.appendPrefix().append(": ").append(cheatSafeLong.get()).append(" != ").append(cheatSafeLong2.get()).append(SequenceUtils.EOL);
        }
    }
}
