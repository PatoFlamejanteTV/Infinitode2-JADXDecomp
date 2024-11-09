package net.bytebuddy.build;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.build.CachedReturnPlugin;

@SuppressFBWarnings(value = {"NM_CLASS_NAMING_CONVENTION"}, justification = "Name is chosen to optimize for simple lookup")
/* loaded from: infinitode-2.jar:net/bytebuddy/build/CachedReturnPlugin$Advice$float.class */
class CachedReturnPlugin$Advice$float {
    private CachedReturnPlugin$Advice$float() {
        throw new UnsupportedOperationException("This class is merely an advice template and should not be instantiated");
    }

    @Advice.OnMethodEnter(skipOn = Advice.OnNonDefaultValue.class)
    protected static float enter(@CachedReturnPlugin.CacheField float f) {
        return f;
    }

    @Advice.OnMethodExit
    @SuppressFBWarnings(value = {"UC_USELESS_VOID_METHOD", "DLS_DEAD_LOCAL_STORE"}, justification = "Advice method serves as a template")
    protected static void exit(@Advice.Return(readOnly = false) float f, @CachedReturnPlugin.CacheField float f2) {
    }
}
