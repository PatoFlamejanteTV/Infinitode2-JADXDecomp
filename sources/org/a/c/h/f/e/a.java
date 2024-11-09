package org.a.c.h.f.e;

/* loaded from: infinitode-2.jar:org/a/c/h/f/e/a.class */
public enum a {
    FILL(0),
    STROKE(1),
    FILL_STROKE(2),
    NEITHER(3),
    FILL_CLIP(4),
    STROKE_CLIP(5),
    FILL_STROKE_CLIP(6),
    NEITHER_CLIP(7);

    private final int i;

    static {
        values();
    }

    a(int i) {
        this.i = i;
    }

    public final int a() {
        return this.i;
    }
}
