package net.bytebuddy.description.modifier;

import net.bytebuddy.description.modifier.ModifierContributor;

/* loaded from: infinitode-2.jar:net/bytebuddy/description/modifier/Ownership.class */
public enum Ownership implements ModifierContributor.ForField, ModifierContributor.ForMethod, ModifierContributor.ForType {
    MEMBER(0),
    STATIC(8);

    private final int mask;

    Ownership(int i) {
        this.mask = i;
    }

    @Override // net.bytebuddy.description.modifier.ModifierContributor
    public final int getMask() {
        return this.mask;
    }

    @Override // net.bytebuddy.description.modifier.ModifierContributor
    public final int getRange() {
        return 8;
    }

    @Override // net.bytebuddy.description.modifier.ModifierContributor
    public final boolean isDefault() {
        return this == MEMBER;
    }

    public final boolean isStatic() {
        return this == STATIC;
    }
}
