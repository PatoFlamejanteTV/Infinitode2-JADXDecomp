package net.bytebuddy.implementation.attribute;

/* loaded from: infinitode-2.jar:net/bytebuddy/implementation/attribute/AnnotationRetention.class */
public enum AnnotationRetention {
    ENABLED(true),
    DISABLED(false);

    private final boolean enabled;

    AnnotationRetention(boolean z) {
        this.enabled = z;
    }

    public static AnnotationRetention of(boolean z) {
        return z ? ENABLED : DISABLED;
    }

    public final boolean isEnabled() {
        return this.enabled;
    }
}
