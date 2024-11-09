package com.c.b;

/* loaded from: infinitode-2.jar:com/c/b/d.class */
final class d {

    /* renamed from: a, reason: collision with root package name */
    private float[] f912a;

    /* renamed from: b, reason: collision with root package name */
    private int[] f913b;
    private static int[] c = {4, 2, 3, 5};
    private static float d = 6.2831855f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(int i) {
        this.f912a = new float[3 * i];
        this.f913b = new int[32];
        a(i, this.f912a, this.f913b);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x0012. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0067 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0060 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void a(int r5, float[] r6, int r7, int[] r8) {
        /*
            Method dump skipped, instructions count: 390
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.c.b.d.a(int, float[], int, int[]):void");
    }

    private static void a(int i, float[] fArr, int[] iArr) {
        if (i == 1) {
            return;
        }
        a(i, fArr, i, iArr);
    }
}
