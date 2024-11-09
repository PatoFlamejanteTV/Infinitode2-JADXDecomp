package com.prineside.tdi2.utils.syncchecker.comparators;

import com.badlogic.gdx.utils.Array;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparator;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparisonConfig;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import net.bytebuddy.description.type.TypeDescription;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/syncchecker/comparators/GdxArrayComparator.class */
public final class GdxArrayComparator extends DeepClassComparator<Array> {
    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final Class<Array> forClass() {
        return Array.class;
    }

    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final void compare(Array array, Array array2, DeepClassComparisonConfig deepClassComparisonConfig) {
        String simpleName;
        if (array.size != array2.size) {
            deepClassComparisonConfig.appendPrefix().append(": sizes differ (").append(array.size).append(", ").append(array2.size).append(")\n");
            return;
        }
        for (int i = 0; i < array.size; i++) {
            if (array.get(i) != null) {
                simpleName = array.get(i).getClass().getSimpleName();
            } else {
                simpleName = array2.get(i) != null ? array2.get(i).getClass().getSimpleName() : TypeDescription.Generic.OfWildcardType.SYMBOL;
            }
            deepClassComparisonConfig.addPrefix("[", SyncChecker.toString(i), SequenceUtils.SPACE, simpleName, "]");
            SyncChecker.compareObjects(array.get(i), array2.get(i), deepClassComparisonConfig);
            deepClassComparisonConfig.popPrefix(5);
        }
    }
}
