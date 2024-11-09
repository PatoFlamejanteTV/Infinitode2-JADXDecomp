package com.prineside.tdi2.utils.syncchecker.comparators;

import com.prineside.tdi2.utils.syncchecker.DeepClassComparator;
import com.prineside.tdi2.utils.syncchecker.DeepClassComparisonConfig;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/syncchecker/comparators/ByteArrayComparator.class */
public final class ByteArrayComparator extends DeepClassComparator<byte[]> {
    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final Class<byte[]> forClass() {
        return byte[].class;
    }

    @Override // com.prineside.tdi2.utils.syncchecker.DeepClassComparator
    public final void compare(byte[] bArr, byte[] bArr2, DeepClassComparisonConfig deepClassComparisonConfig) {
        if (bArr.length != bArr2.length) {
            deepClassComparisonConfig.appendPrefix().append(": sizes differ (").append(bArr.length).append(", ").append(bArr2.length).append(")\n");
            return;
        }
        for (int i = 0; i < bArr.length; i++) {
            if (bArr[i] != bArr2[i]) {
                deepClassComparisonConfig.appendPrefix().append("[").append(String.valueOf(deepClassComparisonConfig.keyEnum == null ? Integer.valueOf(i) : deepClassComparisonConfig.keyEnum[i].name())).append("] ").append((int) bArr[i]).append(" != ").append((int) bArr2[i]).append(SequenceUtils.EOL);
            }
        }
    }
}
