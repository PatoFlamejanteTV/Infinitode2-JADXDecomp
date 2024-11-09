package net.bytebuddy.description.modifier;

import net.bytebuddy.description.modifier.ModifierContributor;

/* loaded from: infinitode-2.jar:net/bytebuddy/description/modifier/FieldManifestation.class */
public enum FieldManifestation implements ModifierContributor.ForField {
    PLAIN(0),
    FINAL(16),
    VOLATILE(64);

    private final int mask;

    FieldManifestation(int i) {
        this.mask = i;
    }

    @Override // net.bytebuddy.description.modifier.ModifierContributor
    public final int getMask() {
        return this.mask;
    }

    @Override // net.bytebuddy.description.modifier.ModifierContributor
    public final int getRange() {
        return 80;
    }

    @Override // net.bytebuddy.description.modifier.ModifierContributor
    public final boolean isDefault() {
        return this == PLAIN;
    }

    public final boolean isFinal() {
        return (this.mask & 16) != 0;
    }

    public final boolean isVolatile() {
        return (this.mask & 64) != 0;
    }

    public final boolean isPlain() {
        return (isFinal() || isVolatile()) ? false : true;
    }
}
