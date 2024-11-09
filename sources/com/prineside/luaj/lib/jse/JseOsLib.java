package com.prineside.luaj.lib.jse;

import com.prineside.luaj.lib.OsLib;
import com.prineside.tdi2.utils.REGS;
import java.io.File;
import java.io.IOException;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/luaj/lib/jse/JseOsLib.class */
public class JseOsLib extends OsLib {
    public static final int EXEC_IOEXCEPTION = 1;
    public static final int EXEC_INTERRUPTED = -2;
    public static final int EXEC_ERROR = -3;

    @Override // com.prineside.luaj.lib.OsLib
    protected final String c(String str) {
        String str2 = System.getenv(str);
        return str2 != null ? str2 : System.getProperty(str);
    }

    @Override // com.prineside.luaj.lib.OsLib
    protected final void f() {
    }

    @Override // com.prineside.luaj.lib.OsLib
    protected final void g() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.prineside.luaj.lib.OsLib
    public final String i() {
        try {
            return File.createTempFile(OsLib.TMP_PREFIX, OsLib.TMP_SUFFIX).getAbsolutePath();
        } catch (IOException unused) {
            return super.i();
        }
    }
}
