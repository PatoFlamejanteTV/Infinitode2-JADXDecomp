package net.bytebuddy.build;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.build.CachedReturnPlugin;
import net.bytebuddy.implementation.bytecode.assign.Assigner;

/* loaded from: infinitode-2.jar:net/bytebuddy/build/CachedReturnPlugin$Advice$Object.class */
class CachedReturnPlugin$Advice$Object {
    private CachedReturnPlugin$Advice$Object() {
        throw new UnsupportedOperationException("This class is merely an advice template and should not be instantiated");
    }

    @Advice.OnMethodEnter(skipOn = Advice.OnNonDefaultValue.class)
    protected static Object enter(@CachedReturnPlugin.CacheField Object obj) {
        return obj;
    }

    @Advice.OnMethodExit
    @SuppressFBWarnings(value = {"UC_USELESS_VOID_METHOD", "DLS_DEAD_LOCAL_STORE"}, justification = "Advice method serves as a template")
    protected static void exit(@Advice.Return(readOnly = false, typing = Assigner.Typing.DYNAMIC) Object obj, @CachedReturnPlugin.CacheField Object obj2) {
    }
}
