package com.prineside.tdi2.utils.syncchecker.comparators;

import com.prineside.tdi2.utils.syncchecker.DeepClassComparator;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparisonConfig;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;
import java.lang.ref.WeakReference;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/syncchecker/comparators/WeakReferenceComparator.class */
public final class WeakReferenceComparator extends DeepClassComparator<WeakReference> {
    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final Class<WeakReference> forClass() {
        return WeakReference.class;
    }

    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final void compare(WeakReference weakReference, WeakReference weakReference2, DeepClassComparisonConfig deepClassComparisonConfig) {
        deepClassComparisonConfig.addPrefix(".get()");
        SyncChecker.compareObjects(weakReference.get(), weakReference2.get(), deepClassComparisonConfig);
        deepClassComparisonConfig.popPrefix(1);
    }
}
