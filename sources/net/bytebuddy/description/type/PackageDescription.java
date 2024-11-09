package net.bytebuddy.description.type;

import net.bytebuddy.description.NamedElement;
import net.bytebuddy.description.annotation.AnnotationList;
import net.bytebuddy.description.annotation.AnnotationSource;
import net.bytebuddy.utility.nullability.AlwaysNull;
import net.bytebuddy.utility.nullability.MaybeNull;

/* loaded from: infinitode-2.jar:net/bytebuddy/description/type/PackageDescription.class */
public interface PackageDescription extends NamedElement.WithRuntimeName, AnnotationSource {
    public static final String PACKAGE_CLASS_NAME = "package-info";
    public static final int PACKAGE_MODIFIERS = 5632;
    public static final PackageDescription DEFAULT = new Simple("");

    @AlwaysNull
    public static final PackageDescription UNDEFINED = null;

    boolean contains(TypeDescription typeDescription);

    boolean isDefault();

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/type/PackageDescription$AbstractBase.class */
    public static abstract class AbstractBase implements PackageDescription {
        @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
        public String getInternalName() {
            return getName().replace('.', '/');
        }

        @Override // net.bytebuddy.description.NamedElement
        public String getActualName() {
            return getName();
        }

        @Override // net.bytebuddy.description.type.PackageDescription
        public boolean contains(TypeDescription typeDescription) {
            return equals(typeDescription.getPackage());
        }

        @Override // net.bytebuddy.description.type.PackageDescription
        public boolean isDefault() {
            return getName().equals("");
        }

        public int hashCode() {
            return getName().hashCode();
        }

        public boolean equals(@MaybeNull Object obj) {
            if (this != obj) {
                return (obj instanceof PackageDescription) && getName().equals(((PackageDescription) obj).getName());
            }
            return true;
        }

        public String toString() {
            return "package " + getName();
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/type/PackageDescription$Simple.class */
    public static class Simple extends AbstractBase {
        private final String name;

        public Simple(String str) {
            this.name = str;
        }

        @Override // net.bytebuddy.description.annotation.AnnotationSource
        public AnnotationList getDeclaredAnnotations() {
            return new AnnotationList.Empty();
        }

        @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
        public String getName() {
            return this.name;
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/description/type/PackageDescription$ForLoadedPackage.class */
    public static class ForLoadedPackage extends AbstractBase {
        private final Package aPackage;

        public ForLoadedPackage(Package r4) {
            this.aPackage = r4;
        }

        @Override // net.bytebuddy.description.annotation.AnnotationSource
        public AnnotationList getDeclaredAnnotations() {
            return new AnnotationList.ForLoadedAnnotations(this.aPackage.getDeclaredAnnotations());
        }

        @Override // net.bytebuddy.description.NamedElement.WithRuntimeName
        public String getName() {
            return this.aPackage.getName();
        }
    }
}
