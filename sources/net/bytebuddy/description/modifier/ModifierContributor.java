package net.bytebuddy.description.modifier;

import java.util.Arrays;
import java.util.Collection;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.utility.nullability.MaybeNull;

/* loaded from: infinitode-2.jar:net/bytebuddy/description/modifier/ModifierContributor.class */
public interface ModifierContributor {
    public static final int EMPTY_MASK = 0;

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/modifier/ModifierContributor$ForField.class */
    public interface ForField extends ModifierContributor {
        public static final int MASK = 151775;
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/modifier/ModifierContributor$ForMethod.class */
    public interface ForMethod extends ModifierContributor {
        public static final int MASK = 7679;
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/modifier/ModifierContributor$ForParameter.class */
    public interface ForParameter extends ModifierContributor {
        public static final int MASK = 36880;
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/modifier/ModifierContributor$ForType.class */
    public interface ForType extends ModifierContributor {
        public static final int MASK = 161311;
    }

    int getMask();

    int getRange();

    boolean isDefault();

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/description/modifier/ModifierContributor$Resolver.class */
    public static class Resolver<T extends ModifierContributor> {
        private final Collection<? extends T> modifierContributors;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.modifierContributors.equals(((Resolver) obj).modifierContributors);
        }

        public int hashCode() {
            return (getClass().hashCode() * 31) + this.modifierContributors.hashCode();
        }

        protected Resolver(Collection<? extends T> collection) {
            this.modifierContributors = collection;
        }

        public static Resolver<ForType> of(ForType... forTypeArr) {
            return of(Arrays.asList(forTypeArr));
        }

        public static Resolver<ForField> of(ForField... forFieldArr) {
            return of(Arrays.asList(forFieldArr));
        }

        public static Resolver<ForMethod> of(ForMethod... forMethodArr) {
            return of(Arrays.asList(forMethodArr));
        }

        public static Resolver<ForParameter> of(ForParameter... forParameterArr) {
            return of(Arrays.asList(forParameterArr));
        }

        public static <S extends ModifierContributor> Resolver<S> of(Collection<? extends S> collection) {
            return new Resolver<>(collection);
        }

        public int resolve() {
            return resolve(0);
        }

        public int resolve(int i) {
            for (T t : this.modifierContributors) {
                i = (i & (t.getRange() ^ (-1))) | t.getMask();
            }
            return i;
        }
    }
}
