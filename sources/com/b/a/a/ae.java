package com.b.a.a;

import com.b.a.a.ab;

/* loaded from: infinitode-2.jar:com/b/a/a/ae.class */
class ae extends ab.b {
    /* JADX INFO: Access modifiers changed from: package-private */
    public ae(ab abVar, int i) {
        super(7);
    }

    @Override // com.b.a.a.ab.b
    final boolean a(int i) {
        String h = m.a().f811a.h(i);
        if (h != null) {
            int codePointAt = h.codePointAt(0);
            i = codePointAt;
            if (Character.charCount(codePointAt) != h.length()) {
                i = -1;
            }
        } else if (i < 0) {
            return false;
        }
        if (i < 0) {
            return !com.b.a.b.a.a(h, true).equals(h);
        }
        aa aaVar = aa.f779b;
        aa.f778a.setLength(0);
        return aaVar.a(i, aa.f778a, 0) >= 0;
    }
}
