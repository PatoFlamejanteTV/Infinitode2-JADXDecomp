package net.bytebuddy.build;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.build.CachedReturnPlugin;

@SuppressFBWarnings(value = {"NM_CLASS_NAMING_CONVENTION"}, justification = "Name is chosen to optimize for simple lookup")
/* loaded from: infinitode-2.jar:net/bytebuddy/build/CachedReturnPlugin$Advice$int.class */
class CachedReturnPlugin$Advice$int {
    private CachedReturnPlugin$Advice$int() {
        throw new UnsupportedOperationException("This class is merely an advice template and should not be instantiated");
    }

    @Advice.OnMethodEnter(skipOn = Advice.OnNonDefaultValue.class)
    protected static int enter(@CachedReturnPlugin.CacheField int i) {
        return i;
    }

    @Advice.OnMethodExit
    @SuppressFBWarnings(value = {"UC_USELESS_VOID_METHOD", "DLS_DEAD_LOCAL_STORE"}, justification = "Advice method serves as a template")
    protected static void exit(@Advice.Return(readOnly = false) int i, @CachedReturnPlugin.CacheField int i2) {
    }
}