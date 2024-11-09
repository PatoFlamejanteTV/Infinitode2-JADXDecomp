package com.prineside.luaj.lib.jse;

import com.prineside.luaj.lib.StringLib;

/* loaded from: infinitode-2.jar:com/prineside/luaj/lib/jse/JseStringLib.class */
public final class JseStringLib extends StringLib {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.prineside.luaj.lib.StringLib
    public final String a(String str, double d) {
        String a2;
        try {
            a2 = String.format(str, Double.valueOf(d));
        } catch (Throwable unused) {
            a2 = super.a(str, d);
        }
        return a2;
    }
}
