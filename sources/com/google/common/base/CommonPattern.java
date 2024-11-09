package com.google.common.base;

/* JADX INFO: Access modifiers changed from: package-private */
@ElementTypesAreNonnullByDefault
/* loaded from: infinitode-2.jar:com/google/common/base/CommonPattern.class */
public abstract class CommonPattern {
    public abstract CommonMatcher matcher(CharSequence charSequence);

    public abstract String pattern();

    public abstract int flags();

    public abstract String toString();

    public static CommonPattern compile(String str) {
        return Platform.compilePattern(str);
    }

    public static boolean isPcreLike() {
        return Platform.patternCompilerIsPcreLike();
    }
}
