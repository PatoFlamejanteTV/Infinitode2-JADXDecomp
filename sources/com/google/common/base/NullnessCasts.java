package com.google.common.base;

@ElementTypesAreNonnullByDefault
/* loaded from: infinitode-2.jar:com/google/common/base/NullnessCasts.class */
final class NullnessCasts {
    /* JADX INFO: Access modifiers changed from: package-private */
    @ParametricNullness
    public static <T> T uncheckedCastNullableTToT(T t) {
        return t;
    }

    private NullnessCasts() {
    }
}
