package net.bytebuddy.description.enumeration;

import java.util.ArrayList;
import java.util.List;
import net.bytebuddy.build.CachedReturnPlugin;
import net.bytebuddy.description.NamedElement;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.utility.nullability.MaybeNull;

/* loaded from: infinitode-2.jar:net/bytebuddy/description/enumeration/EnumerationDescription.class */
public interface EnumerationDescription extends NamedElement {
    String getValue();

    TypeDescription getEnumerationType();

    <T extends Enum<T>> T load(Class<T> cls);

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/enumeration/EnumerationDescription$AbstractBase.class */
    public static abstract class AbstractBase implements EnumerationDescription {
        private transient /* synthetic */ int hashCode;

        @Override // net.bytebuddy.description.NamedElement
        public String getActualName() {
            return getValue();
        }

        @CachedReturnPlugin.Enhance("hashCode")
        public int hashCode() {
            int hashCode = this.hashCode != 0 ? 0 : getValue().hashCode() + (31 * getEnumerationType().hashCode());
            int i = hashCode;
            if (hashCode == 0) {
                i = this.hashCode;
            } else {
                this.hashCode = i;
            }
            return i;
        }

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof EnumerationDescription)) {
                return false;
            }
            EnumerationDescription enumerationDescription = (EnumerationDescription) obj;
            return getEnumerationType().equals(enumerationDescription.getEnumerationType()) && getValue().equals(enumerationDescription.getValue());
        }

        public String toString() {
            return getValue();
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/enumeration/EnumerationDescription$ForLoadedEnumeration.class */
    public static class ForLoadedEnumeration extends AbstractBase {
        private final Enum<?> value;

        public ForLoadedEnumeration(Enum<?> r4) {
            this.value = r4;
        }

        public static List<EnumerationDescription> asList(Enum<?>[] enumArr) {
            ArrayList arrayList = new ArrayList(enumArr.length);
            for (Enum<?> r0 : enumArr) {
                arrayList.add(new ForLoadedEnumeration(r0));
            }
            return arrayList;
        }

        @Override // net.bytebuddy.description.enumeration.EnumerationDescription
        public String getValue() {
            return this.value.name();
        }

        @Override // net.bytebuddy.description.enumeration.EnumerationDescription
        public TypeDescription getEnumerationType() {
            return TypeDescription.ForLoadedType.of(this.value.getDeclaringClass());
        }

        @Override // net.bytebuddy.description.enumeration.EnumerationDescription
        public <T extends Enum<T>> T load(Class<T> cls) {
            return this.value.getDeclaringClass() == cls ? (T) this.value : (T) Enum.valueOf(cls, this.value.name());
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/enumeration/EnumerationDescription$Latent.class */
    public static class Latent extends AbstractBase {
        private final TypeDescription enumerationType;
        private final String value;

        public Latent(TypeDescription typeDescription, String str) {
            this.enumerationType = typeDescription;
            this.value = str;
        }

        @Override // net.bytebuddy.description.enumeration.EnumerationDescription
        public String getValue() {
            return this.value;
        }

        @Override // net.bytebuddy.description.enumeration.EnumerationDescription
        public TypeDescription getEnumerationType() {
            return this.enumerationType;
        }

        @Override // net.bytebuddy.description.enumeration.EnumerationDescription
        public <T extends Enum<T>> T load(Class<T> cls) {
            if (!this.enumerationType.represents(cls)) {
                throw new IllegalArgumentException(cls + " does not represent " + this.enumerationType);
            }
            return (T) Enum.valueOf(cls, this.value);
        }
    }
}
