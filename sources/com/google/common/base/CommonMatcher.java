package com.google.common.base;

@ElementTypesAreNonnullByDefault
/* loaded from: infinitode-2.jar:com/google/common/base/CommonMatcher.class */
abstract class CommonMatcher {
    public abstract boolean matches();

    public abstract boolean find();

    public abstract boolean find(int i);

    public abstract String replaceAll(String str);

    public abstract int end();

    public abstract int start();
}
