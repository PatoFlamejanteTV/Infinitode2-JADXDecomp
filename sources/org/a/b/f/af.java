package org.a.b.f;

import java.util.Comparator;

/* loaded from: infinitode-2.jar:org/a/b/f/af.class */
class af implements Comparator<int[]> {
    @Override // java.util.Comparator
    public final /* synthetic */ int compare(int[] iArr, int[] iArr2) {
        return a(iArr, iArr2);
    }

    private static int a(int[] iArr, int[] iArr2) {
        if (iArr[0] < iArr2[0]) {
            return -1;
        }
        return iArr[0] == iArr2[0] ? 0 : 1;
    }
}
