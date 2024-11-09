package net.bytebuddy.implementation.auxiliary;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.modifier.ModifierContributor;
import net.bytebuddy.description.modifier.SyntheticState;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodAccessorFactory;
import net.bytebuddy.utility.RandomString;
import net.bytebuddy.utility.nullability.MaybeNull;

/* loaded from: infinitode-2.jar:net/bytebuddy/implementation/auxiliary/AuxiliaryType.class */
public interface AuxiliaryType {

    @SuppressFBWarnings(value = {"MS_MUTABLE_ARRAY", "MS_OOI_PKGPROTECT"}, justification = "The array is not modified by class contract.")
    public static final ModifierContributor.ForType[] DEFAULT_TYPE_MODIFIER = {SyntheticState.SYNTHETIC};

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.CLASS)
    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/auxiliary/AuxiliaryType$SignatureRelevant.class */
    public @interface SignatureRelevant {
    }

    DynamicType make(String str, ClassFileVersion classFileVersion, MethodAccessorFactory methodAccessorFactory);

    String getSuffix();

    /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/auxiliary/AuxiliaryType$NamingStrategy.class */
    public interface NamingStrategy {
        String name(TypeDescription typeDescription, AuxiliaryType auxiliaryType);

        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/auxiliary/AuxiliaryType$NamingStrategy$Enumerating.class */
        public static class Enumerating implements NamingStrategy {
            private final String suffix;

            public Enumerating(String str) {
                this.suffix = str;
            }

            @Override // net.bytebuddy.implementation.auxiliary.AuxiliaryType.NamingStrategy
            public String name(TypeDescription typeDescription, AuxiliaryType auxiliaryType) {
                return typeDescription.getName() + "$" + this.suffix + "$" + RandomString.hashOf(auxiliaryType);
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/auxiliary/AuxiliaryType$NamingStrategy$Suffixing.class */
        public static class Suffixing implements NamingStrategy {
            private final String suffix;

            public Suffixing(String str) {
                this.suffix = str;
            }

            @Override // net.bytebuddy.implementation.auxiliary.AuxiliaryType.NamingStrategy
            public String name(TypeDescription typeDescription, AuxiliaryType auxiliaryType) {
                return typeDescription.getName() + "$" + this.suffix + "$" + auxiliaryType.getSuffix();
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        /* loaded from: infinitode-2.jar:net/bytebuddy/implementation/auxiliary/AuxiliaryType$NamingStrategy$SuffixingRandom.class */
        public static class SuffixingRandom implements NamingStrategy {
            private final String suffix;

            @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.IGNORE)
            private final RandomString randomString = new RandomString();

            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.suffix.equals(((SuffixingRandom) obj).suffix);
            }

            public int hashCode() {
                return (getClass().hashCode() * 31) + this.suffix.hashCode();
            }

            public SuffixingRandom(String str) {
                this.suffix = str;
            }

            @Override // net.bytebuddy.implementation.auxiliary.AuxiliaryType.NamingStrategy
            public String name(TypeDescription typeDescription, AuxiliaryType auxiliaryType) {
                return typeDescription.getName() + "$" + this.suffix + "$" + this.randomString.nextString();
            }
        }
    }
}
