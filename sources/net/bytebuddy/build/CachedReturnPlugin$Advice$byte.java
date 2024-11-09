package net.bytebuddy.build;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.build.CachedReturnPlugin;

@SuppressFBWarnings(value = {"NM_CLASS_NAMING_CONVENTION"}, justification = "Name is chosen to optimize for simple lookup")
/* loaded from: infinitode-2.jar:net/bytebuddy/build/CachedReturnPlugin$Advice$byte.class */
class CachedReturnPlugin$Advice$byte {
    private CachedReturnPlugin$Advice$byte() {
        throw new UnsupportedOperationException("This class is merely an advice template and should not be instantiated");
    }

    @Advice.OnMethodEnter(skipOn = Advice.OnNonDefaultValue.class)
    protected static byte enter(@CachedReturnPlugin.CacheField byte b2) {
        return b2;
    }

    @Advice.OnMethodExit
    @SuppressFBWarnings(value = {"UC_USELESS_VOID_METHOD", "DLS_DEAD_LOCAL_STORE"}, justification = "Advice method serves as a template")
    protected static void exit(@Advice.Return(readOnly = false) byte b2, @CachedReturnPlugin.CacheField byte b3) {
    }
}
