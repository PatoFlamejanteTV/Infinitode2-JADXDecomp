package com.prineside.luaj.compiler;

/* loaded from: infinitode-2.jar:com/prineside/luaj/compiler/InstructionPtr.class */
class InstructionPtr {

    /* renamed from: a, reason: collision with root package name */
    final int[] f1532a;

    /* renamed from: b, reason: collision with root package name */
    final int f1533b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public InstructionPtr(int[] iArr, int i) {
        this.f1532a = iArr;
        this.f1533b = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a() {
        return this.f1532a[this.f1533b];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(int i) {
        this.f1532a[this.f1533b] = i;
    }
}
