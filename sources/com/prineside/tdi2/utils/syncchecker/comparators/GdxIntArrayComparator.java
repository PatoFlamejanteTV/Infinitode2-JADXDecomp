package com.prineside.tdi2.utils.syncchecker.comparators;

import com.badlogic.gdx.utils.IntArray;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparator;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparisonConfig;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/syncchecker/comparators/GdxIntArrayComparator.class */
public final class GdxIntArrayComparator extends DeepClassComparator<IntArray> {
    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final Class<IntArray> forClass() {
        return IntArray.class;
    }

    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final void compare(IntArray intArray, IntArray intArray2, DeepClassComparisonConfig deepClassComparisonConfig) {
        if (intArray.size != intArray2.size) {
            deepClassComparisonConfig.appendPrefix().append(": sizes differ (").append(intArray.size).append(", ").append(intArray2.size).append(")\n");
            return;
        }
        for (int i = 0; i < intArray.size; i++) {
            deepClassComparisonConfig.addPrefix("[", SyncChecker.toString(i), "]");
            SyncChecker.compareObjects(Integer.valueOf(intArray.get(i)), Integer.valueOf(intArray2.get(i)), deepClassComparisonConfig);
            deepClassComparisonConfig.popPrefix(3);
        }
    }
}
