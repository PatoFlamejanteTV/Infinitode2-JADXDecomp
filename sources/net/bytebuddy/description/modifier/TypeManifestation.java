package net.bytebuddy.description.modifier;

import net.bytebuddy.description.modifier.ModifierContributor;

/* loaded from: infinitode-2.jar:net/bytebuddy/description/modifier/TypeManifestation.class */
public enum TypeManifestation implements ModifierContributor.ForType {
    PLAIN(0),
    FINAL(16),
    ABSTRACT(1024),
    INTERFACE(1536),
    ANNOTATION(9728);

    private final int mask;

    TypeManifestation(int i) {
        this.mask = i;
    }

    @Override // net.bytebuddy.description.modifier.ModifierContributor
    public final int getMask() {
        return this.mask;
    }

    @Override // net.bytebuddy.description.modifier.ModifierContributor
    public final int getRange() {
        return 9744;
    }

    @Override // net.bytebuddy.description.modifier.ModifierContributor
    public final boolean isDefault() {
        return this == PLAIN;
    }

    public final boolean isFinal() {
        return (this.mask & 16) != 0;
    }

    public final boolean isAbstract() {
        return ((this.mask & 1024) == 0 || isInterface()) ? false : true;
    }

    public final boolean isInterface() {
        return (this.mask & 512) != 0;
    }

    public final boolean isAnnotation() {
        return (this.mask & 8192) != 0;
    }
}
