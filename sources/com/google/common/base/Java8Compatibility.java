package com.google.common.base;

import java.nio.Buffer;

@ElementTypesAreNonnullByDefault
/* loaded from: infinitode-2.jar:com/google/common/base/Java8Compatibility.class */
final class Java8Compatibility {
    static void clear(Buffer buffer) {
        buffer.clear();
    }

    static void flip(Buffer buffer) {
        buffer.flip();
    }

    static void limit(Buffer buffer, int i) {
        buffer.limit(i);
    }

    static void position(Buffer buffer, int i) {
        buffer.position(i);
    }

    private Java8Compatibility() {
    }
}
