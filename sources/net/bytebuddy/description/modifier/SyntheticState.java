package net.bytebuddy.description.modifier;

import net.bytebuddy.description.modifier.ModifierContributor;

/* loaded from: infinitode-2.jar:net/bytebuddy/description/modifier/SyntheticState.class */
public enum SyntheticState implements ModifierContributor.ForField, ModifierContributor.ForMethod, ModifierContributor.ForParameter, ModifierContributor.ForType {
    PLAIN(0),
    SYNTHETIC(4096);

    private final int mask;

    SyntheticState(int i) {
        this.mask = i;
    }

    @Override // net.bytebuddy.description.modifier.ModifierContributor
    public final int getMask() {
        return this.mask;
    }

    @Override // net.bytebuddy.description.modifier.ModifierContributor
    public final int getRange() {
        return 4096;
    }

    @Override // net.bytebuddy.description.modifier.ModifierContributor
    public final boolean isDefault() {
        return this == PLAIN;
    }

    public final boolean isSynthetic() {
        return this == SYNTHETIC;
    }
}
