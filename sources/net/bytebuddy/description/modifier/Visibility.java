package net.bytebuddy.description.modifier;

import net.bytebuddy.description.modifier.ModifierContributor;

/* loaded from: infinitode-2.jar:net/bytebuddy/description/modifier/Visibility.class */
public enum Visibility implements ModifierContributor.ForField, ModifierContributor.ForMethod, ModifierContributor.ForType {
    PUBLIC(1),
    PACKAGE_PRIVATE(0),
    PROTECTED(4),
    PRIVATE(2);

    private final int mask;

    Visibility(int i) {
        this.mask = i;
    }

    @Override // net.bytebuddy.description.modifier.ModifierContributor
    public final int getMask() {
        return this.mask;
    }

    @Override // net.bytebuddy.description.modifier.ModifierContributor
    public final int getRange() {
        return 7;
    }

    @Override // net.bytebuddy.description.modifier.ModifierContributor
    public final boolean isDefault() {
        return this == PACKAGE_PRIVATE;
    }

    public final boolean isPublic() {
        return (this.mask & 1) != 0;
    }

    public final boolean isProtected() {
        return (this.mask & 4) != 0;
    }

    public final boolean isPackagePrivate() {
        return (isPublic() || isPrivate() || isProtected()) ? false : true;
    }

    public final boolean isPrivate() {
        return (this.mask & 2) != 0;
    }

    public final Visibility expandTo(Visibility visibility) {
        switch (visibility) {
            case PUBLIC:
                return PUBLIC;
            case PROTECTED:
                return this == PUBLIC ? PUBLIC : visibility;
            case PACKAGE_PRIVATE:
                return this == PRIVATE ? PACKAGE_PRIVATE : this;
            case PRIVATE:
                return this;
            default:
                throw new IllegalStateException("Unexpected visibility: " + visibility);
        }
    }
}
