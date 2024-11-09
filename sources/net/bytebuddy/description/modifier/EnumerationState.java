package net.bytebuddy.description.modifier;

import net.bytebuddy.description.modifier.ModifierContributor;

/* loaded from: infinitode-2.jar:net/bytebuddy/description/modifier/EnumerationState.class */
public enum EnumerationState implements ModifierContributor.ForField, ModifierContributor.ForType {
    PLAIN(0),
    ENUMERATION(16384);

    private final int mask;

    EnumerationState(int i) {
        this.mask = i;
    }

    @Override // net.bytebuddy.description.modifier.ModifierContributor
    public final int getMask() {
        return this.mask;
    }

    @Override // net.bytebuddy.description.modifier.ModifierContributor
    public final int getRange() {
        return 16384;
    }

    @Override // net.bytebuddy.description.modifier.ModifierContributor
    public final boolean isDefault() {
        return this == PLAIN;
    }

    public final boolean isEnumeration() {
        return this == ENUMERATION;
    }
}
