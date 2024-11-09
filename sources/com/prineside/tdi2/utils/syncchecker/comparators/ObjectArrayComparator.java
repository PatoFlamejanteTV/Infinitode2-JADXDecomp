package com.prineside.tdi2.utils.syncchecker.comparators;

import com.prineside.tdi2.utils.syncchecker.DeepClassComparator;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparisonConfig;
import com.prineside.tdi2.utils.syncchecker.SyncChecker;
import net.bytebuddy.description.type.TypeDescription;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/syncchecker/comparators/ObjectArrayComparator.class */
public final class ObjectArrayComparator extends DeepClassComparator<Object[]> {
    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final Class<Object[]> forClass() {
        return Object[].class;
    }

    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final void compare(Object[] objArr, Object[] objArr2, DeepClassComparisonConfig deepClassComparisonConfig) {
        String str;
        if (objArr.length != objArr2.length) {
            deepClassComparisonConfig.appendPrefix().append(": sizes differ (").append(objArr.length).append(", ").append(objArr2.length).append(")\n");
            return;
        }
        for (int i = 0; i < objArr.length; i++) {
            if (objArr[i] != null) {
                str = objArr[i].getClass().getSimpleName();
            } else if (objArr2[i] != null) {
                str = objArr2[i].getClass().getSimpleName();
            } else {
                str = TypeDescription.Generic.OfWildcardType.SYMBOL;
            }
            String str2 = str;
            String[] strArr = new String[5];
            strArr[0] = "[";
            strArr[1] = deepClassComparisonConfig.keyEnum == null ? SyncChecker.toString(i) : deepClassComparisonConfig.keyEnum[i].name();
            strArr[2] = ":";
            strArr[3] = str2;
            strArr[4] = "]";
            deepClassComparisonConfig.addPrefix(strArr);
            SyncChecker.compareObjects(objArr[i], objArr2[i], deepClassComparisonConfig);
            deepClassComparisonConfig.popPrefix(5);
        }
    }
}
