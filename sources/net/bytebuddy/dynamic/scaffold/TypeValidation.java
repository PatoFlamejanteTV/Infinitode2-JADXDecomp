package net.bytebuddy.dynamic.scaffold;

/* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/scaffold/TypeValidation.class */
public enum TypeValidation {
    ENABLED(true),
    DISABLED(false);

    private final boolean enabled;

    TypeValidation(boolean z) {
        this.enabled = z;
    }

    public static TypeValidation of(boolean z) {
        return z ? ENABLED : DISABLED;
    }

    public final boolean isEnabled() {
        return this.enabled;
    }
}
