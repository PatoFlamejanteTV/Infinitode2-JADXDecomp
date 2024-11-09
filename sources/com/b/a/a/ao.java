package com.b.a.a;

import com.b.a.a.ab;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:com/b/a/a/ao.class */
public class ao extends ab.e {

    /* renamed from: a, reason: collision with root package name */
    private /* synthetic */ ab f791a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ao(ab abVar, int i) {
        super(2);
        this.f791a = abVar;
    }

    @Override // com.b.a.a.ab.e
    final int a(int i) {
        int[] iArr;
        int[] iArr2;
        int a2 = (this.f791a.a(i, 2) & 992) >>> 5;
        iArr = ab.l;
        if (a2 < iArr.length) {
            iArr2 = ab.l;
            return iArr2[a2];
        }
        return 0;
    }
}
