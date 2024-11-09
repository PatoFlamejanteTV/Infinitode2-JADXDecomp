package com.google.common.base;

/* JADX INFO: Access modifiers changed from: package-private */
@ElementTypesAreNonnullByDefault
/* loaded from: infinitode-2.jar:com/google/common/base/PatternCompiler.class */
public interface PatternCompiler {
    CommonPattern compile(String str);

    boolean isPcreLike();
}
