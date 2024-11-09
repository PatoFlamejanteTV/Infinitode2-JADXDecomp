package com.d.e;

/* loaded from: infinitode-2.jar:com/d/e/k.class */
public final class k implements Comparable<k> {

    /* renamed from: a, reason: collision with root package name */
    private com.d.f.f f1141a;

    /* renamed from: b, reason: collision with root package name */
    private int f1142b;

    public k(com.d.f.f fVar, int i) {
        this.f1142b = i;
        this.f1141a = fVar;
    }

    public final com.d.f.f a() {
        return this.f1141a;
    }

    public final int b() {
        return this.f1142b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(k kVar) {
        com.d.f.a aVar = null;
        com.d.f.a aVar2 = null;
        switch (this.f1142b) {
            case 1:
                aVar = this.f1141a.t();
                break;
            case 2:
                aVar = this.f1141a.r();
                break;
            case 4:
                aVar = this.f1141a.q();
                break;
            case 8:
                aVar = this.f1141a.s();
                break;
        }
        switch (kVar.f1142b) {
            case 1:
                aVar2 = kVar.f1141a.t();
                break;
            case 2:
                aVar2 = kVar.f1141a.r();
                break;
            case 4:
                aVar2 = kVar.f1141a.q();
                break;
            case 8:
                aVar2 = kVar.f1141a.s();
                break;
        }
        com.d.f.a a2 = com.d.f.f.a(aVar, aVar2, true);
        if (a2 == null) {
            return 0;
        }
        return a2 == aVar ? 1 : -1;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof k)) {
            return false;
        }
        k kVar = (k) obj;
        return this.f1142b == kVar.f1142b && this.f1141a.equals(kVar.f1141a);
    }

    public final int hashCode() {
        return (this.f1141a.hashCode() * 31) + this.f1142b;
    }
}
