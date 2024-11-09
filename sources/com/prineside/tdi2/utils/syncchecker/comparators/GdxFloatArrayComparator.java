package com.prineside.tdi2.utils.syncchecker.comparators;

import com.badlogic.gdx.utils.FloatArray;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparator;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparisonConfig;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/syncchecker/comparators/GdxFloatArrayComparator.class */
public final class GdxFloatArrayComparator extends DeepClassComparator<FloatArray> {
    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final Class<FloatArray> forClass() {
        return FloatArray.class;
    }

    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final void compare(FloatArray floatArray, FloatArray floatArray2, DeepClassComparisonConfig deepClassComparisonConfig) {
        if (floatArray.size != floatArray2.size) {
            deepClassComparisonConfig.appendPrefix().append(": sizes differ (").append(floatArray.size).append(", ").append(floatArray2.size).append(")\n");
            return;
        }
        for (int i = 0; i < floatArray.size; i++) {
            deepClassComparisonConfig.addPrefix("[", SyncChecker.toString(i), "]");
            SyncChecker.compareObjects(Float.valueOf(floatArray.get(i)), Float.valueOf(floatArray2.get(i)), deepClassComparisonConfig);
            deepClassComparisonConfig.popPrefix(3);
        }
    }
}
