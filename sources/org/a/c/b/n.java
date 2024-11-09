package org.a.c.b;

import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:org/a/c/b/n.class */
public final class n implements Comparable<n> {

    /* renamed from: a, reason: collision with root package name */
    private final long f4374a;

    /* renamed from: b, reason: collision with root package name */
    private int f4375b;

    public n(m mVar) {
        this(mVar.b(), mVar.d());
    }

    public n(long j, int i) {
        this.f4374a = j;
        this.f4375b = i;
    }

    public final boolean equals(Object obj) {
        n nVar = obj instanceof n ? (n) obj : null;
        n nVar2 = nVar;
        return nVar != null && nVar2.b() == b() && nVar2.a() == a();
    }

    public final int a() {
        return this.f4375b;
    }

    public final void a(int i) {
        this.f4375b = i;
    }

    public final long b() {
        return this.f4374a;
    }

    public final int hashCode() {
        return Long.valueOf(this.f4374a + this.f4375b).hashCode();
    }

    public final String toString() {
        return Long.toString(this.f4374a) + SequenceUtils.SPACE + Integer.toString(this.f4375b) + " R";
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(n nVar) {
        if (b() < nVar.b()) {
            return -1;
        }
        if (b() > nVar.b()) {
            return 1;
        }
        if (a() < nVar.a()) {
            return -1;
        }
        if (a() > nVar.a()) {
            return 1;
        }
        return 0;
    }
}
