package net.bytebuddy.dynamic.loading;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.utility.nullability.AlwaysNull;
import net.bytebuddy.utility.nullability.MaybeNull;

/* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/PackageDefinitionStrategy.class */
public interface PackageDefinitionStrategy {
    Definition define(ClassLoader classLoader, String str, String str2);

    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/PackageDefinitionStrategy$NoOp.class */
    public enum NoOp implements PackageDefinitionStrategy {
        INSTANCE;

        @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy
        public final Definition define(ClassLoader classLoader, String str, String str2) {
            return Definition.Undefined.INSTANCE;
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/PackageDefinitionStrategy$Trivial.class */
    public enum Trivial implements PackageDefinitionStrategy {
        INSTANCE;

        @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy
        public final Definition define(ClassLoader classLoader, String str, String str2) {
            return Definition.Trivial.INSTANCE;
        }
    }

    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/PackageDefinitionStrategy$Definition.class */
    public interface Definition {
        boolean isDefined();

        @MaybeNull
        String getSpecificationTitle();

        @MaybeNull
        String getSpecificationVersion();

        @MaybeNull
        String getSpecificationVendor();

        @MaybeNull
        String getImplementationTitle();

        @MaybeNull
        String getImplementationVersion();

        @MaybeNull
        String getImplementationVendor();

        @MaybeNull
        URL getSealBase();

        boolean isCompatibleTo(Package r1);

        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/PackageDefinitionStrategy$Definition$Undefined.class */
        public enum Undefined implements Definition {
            INSTANCE;

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public final boolean isDefined() {
                return false;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public final String getSpecificationTitle() {
                throw new IllegalStateException("Cannot read property of undefined package");
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public final String getSpecificationVersion() {
                throw new IllegalStateException("Cannot read property of undefined package");
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public final String getSpecificationVendor() {
                throw new IllegalStateException("Cannot read property of undefined package");
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public final String getImplementationTitle() {
                throw new IllegalStateException("Cannot read property of undefined package");
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public final String getImplementationVersion() {
                throw new IllegalStateException("Cannot read property of undefined package");
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public final String getImplementationVendor() {
                throw new IllegalStateException("Cannot read property of undefined package");
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public final URL getSealBase() {
                throw new IllegalStateException("Cannot read property of undefined package");
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public final boolean isCompatibleTo(Package r5) {
                throw new IllegalStateException("Cannot check compatibility to undefined package");
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/PackageDefinitionStrategy$Definition$Trivial.class */
        public enum Trivial implements Definition {
            INSTANCE;


            @AlwaysNull
            private static final String NO_VALUE = null;

            @AlwaysNull
            private static final URL NOT_SEALED = null;

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public final boolean isDefined() {
                return true;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            @MaybeNull
            public final String getSpecificationTitle() {
                return NO_VALUE;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            @MaybeNull
            public final String getSpecificationVersion() {
                return NO_VALUE;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            @MaybeNull
            public final String getSpecificationVendor() {
                return NO_VALUE;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            @MaybeNull
            public final String getImplementationTitle() {
                return NO_VALUE;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            @MaybeNull
            public final String getImplementationVersion() {
                return NO_VALUE;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public final String getImplementationVendor() {
                return NO_VALUE;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            @MaybeNull
            public final URL getSealBase() {
                return NOT_SEALED;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public final boolean isCompatibleTo(Package r3) {
                return true;
            }
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/PackageDefinitionStrategy$Definition$Simple.class */
        public static class Simple implements Definition {

            @MaybeNull
            protected final URL sealBase;

            @MaybeNull
            private final String specificationTitle;

            @MaybeNull
            private final String specificationVersion;

            @MaybeNull
            private final String specificationVendor;

            @MaybeNull
            private final String implementationTitle;

            @MaybeNull
            private final String implementationVersion;

            @MaybeNull
            private final String implementationVendor;

            public Simple(@MaybeNull String str, @MaybeNull String str2, @MaybeNull String str3, @MaybeNull String str4, @MaybeNull String str5, @MaybeNull String str6, @MaybeNull URL url) {
                this.specificationTitle = str;
                this.specificationVersion = str2;
                this.specificationVendor = str3;
                this.implementationTitle = str4;
                this.implementationVersion = str5;
                this.implementationVendor = str6;
                this.sealBase = url;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public boolean isDefined() {
                return true;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            @MaybeNull
            public String getSpecificationTitle() {
                return this.specificationTitle;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            @MaybeNull
            public String getSpecificationVersion() {
                return this.specificationVersion;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            @MaybeNull
            public String getSpecificationVendor() {
                return this.specificationVendor;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            @MaybeNull
            public String getImplementationTitle() {
                return this.implementationTitle;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            @MaybeNull
            public String getImplementationVersion() {
                return this.implementationVersion;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            @MaybeNull
            public String getImplementationVendor() {
                return this.implementationVendor;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            @MaybeNull
            public URL getSealBase() {
                return this.sealBase;
            }

            @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.Definition
            public boolean isCompatibleTo(Package r4) {
                if (this.sealBase == null) {
                    return !r4.isSealed();
                }
                return r4.isSealed(this.sealBase);
            }

            @SuppressFBWarnings(value = {"DMI_BLOCKING_METHODS_ON_URL"}, justification = "Package sealing relies on URL equality.")
            public int hashCode() {
                return ((((((((((((this.specificationTitle != null ? this.specificationTitle.hashCode() : 0) * 31) + (this.specificationVersion != null ? this.specificationVersion.hashCode() : 0)) * 31) + (this.specificationVendor != null ? this.specificationVendor.hashCode() : 0)) * 31) + (this.implementationTitle != null ? this.implementationTitle.hashCode() : 0)) * 31) + (this.implementationVersion != null ? this.implementationVersion.hashCode() : 0)) * 31) + (this.implementationVendor != null ? this.implementationVendor.hashCode() : 0)) * 31) + (this.sealBase != null ? this.sealBase.hashCode() : 0);
            }

            @SuppressFBWarnings(value = {"DMI_BLOCKING_METHODS_ON_URL"}, justification = "Package sealing relies on URL equality.")
            public boolean equals(@MaybeNull Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                Simple simple = (Simple) obj;
                if (this.specificationTitle != null) {
                    if (!this.specificationTitle.equals(simple.specificationTitle)) {
                        return false;
                    }
                } else if (simple.specificationTitle != null) {
                    return false;
                }
                if (this.specificationVersion != null) {
                    if (!this.specificationVersion.equals(simple.specificationVersion)) {
                        return false;
                    }
                } else if (simple.specificationVersion != null) {
                    return false;
                }
                if (this.specificationVendor != null) {
                    if (!this.specificationVendor.equals(simple.specificationVendor)) {
                        return false;
                    }
                } else if (simple.specificationVendor != null) {
                    return false;
                }
                if (this.implementationTitle != null) {
                    if (!this.implementationTitle.equals(simple.implementationTitle)) {
                        return false;
                    }
                } else if (simple.implementationTitle != null) {
                    return false;
                }
                if (this.implementationVersion != null) {
                    if (!this.implementationVersion.equals(simple.implementationVersion)) {
                        return false;
                    }
                } else if (simple.implementationVersion != null) {
                    return false;
                }
                if (this.implementationVendor != null) {
                    if (!this.implementationVendor.equals(simple.implementationVendor)) {
                        return false;
                    }
                } else if (simple.implementationVendor != null) {
                    return false;
                }
                return this.sealBase != null ? this.sealBase.equals(simple.sealBase) : simple.sealBase == null;
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/PackageDefinitionStrategy$ManifestReading.class */
    public static class ManifestReading implements PackageDefinitionStrategy {

        @AlwaysNull
        private static final URL NOT_SEALED = null;
        private static final Attributes.Name[] ATTRIBUTE_NAMES = {Attributes.Name.SPECIFICATION_TITLE, Attributes.Name.SPECIFICATION_VERSION, Attributes.Name.SPECIFICATION_VENDOR, Attributes.Name.IMPLEMENTATION_TITLE, Attributes.Name.IMPLEMENTATION_VERSION, Attributes.Name.IMPLEMENTATION_VENDOR, Attributes.Name.SEALED};
        private final SealBaseLocator sealBaseLocator;

        public boolean equals(@MaybeNull Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.sealBaseLocator.equals(((ManifestReading) obj).sealBaseLocator);
        }

        public int hashCode() {
            return (getClass().hashCode() * 31) + this.sealBaseLocator.hashCode();
        }

        public ManifestReading() {
            this(new SealBaseLocator.ForTypeResourceUrl());
        }

        public ManifestReading(SealBaseLocator sealBaseLocator) {
            this.sealBaseLocator = sealBaseLocator;
        }

        @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy
        public Definition define(ClassLoader classLoader, String str, String str2) {
            InputStream resourceAsStream = classLoader.getResourceAsStream("META-INF/MANIFEST.MF");
            if (resourceAsStream != null) {
                try {
                    try {
                        Manifest manifest = new Manifest(resourceAsStream);
                        HashMap hashMap = new HashMap();
                        Attributes mainAttributes = manifest.getMainAttributes();
                        if (mainAttributes != null) {
                            for (Attributes.Name name : ATTRIBUTE_NAMES) {
                                hashMap.put(name, mainAttributes.getValue(name));
                            }
                        }
                        Attributes attributes = manifest.getAttributes(str.replace('.', '/').concat("/"));
                        if (attributes != null) {
                            for (Attributes.Name name2 : ATTRIBUTE_NAMES) {
                                String value = attributes.getValue(name2);
                                if (value != null) {
                                    hashMap.put(name2, value);
                                }
                            }
                        }
                        return new Definition.Simple((String) hashMap.get(Attributes.Name.SPECIFICATION_TITLE), (String) hashMap.get(Attributes.Name.SPECIFICATION_VERSION), (String) hashMap.get(Attributes.Name.SPECIFICATION_VENDOR), (String) hashMap.get(Attributes.Name.IMPLEMENTATION_TITLE), (String) hashMap.get(Attributes.Name.IMPLEMENTATION_VERSION), (String) hashMap.get(Attributes.Name.IMPLEMENTATION_VENDOR), Boolean.parseBoolean((String) hashMap.get(Attributes.Name.SEALED)) ? this.sealBaseLocator.findSealBase(classLoader, str2) : NOT_SEALED);
                    } finally {
                        resourceAsStream.close();
                    }
                } catch (IOException e) {
                    throw new IllegalStateException("Error while reading manifest file", e);
                }
            }
            return Definition.Trivial.INSTANCE;
        }

        /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/PackageDefinitionStrategy$ManifestReading$SealBaseLocator.class */
        public interface SealBaseLocator {
            @MaybeNull
            URL findSealBase(ClassLoader classLoader, String str);

            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/PackageDefinitionStrategy$ManifestReading$SealBaseLocator$NonSealing.class */
            public enum NonSealing implements SealBaseLocator {
                INSTANCE;

                @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.ManifestReading.SealBaseLocator
                @MaybeNull
                public final URL findSealBase(ClassLoader classLoader, String str) {
                    return ManifestReading.NOT_SEALED;
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/PackageDefinitionStrategy$ManifestReading$SealBaseLocator$ForFixedValue.class */
            public static class ForFixedValue implements SealBaseLocator {

                @MaybeNull
                @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.REVERSE_NULLABILITY)
                private final URL sealBase;

                public ForFixedValue(@MaybeNull URL url) {
                    this.sealBase = url;
                }

                @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.ManifestReading.SealBaseLocator
                @MaybeNull
                public URL findSealBase(ClassLoader classLoader, String str) {
                    return this.sealBase;
                }

                @SuppressFBWarnings(value = {"DMI_BLOCKING_METHODS_ON_URL"}, justification = "Package sealing relies on URL equality.")
                public int hashCode() {
                    if (this.sealBase == null) {
                        return 17;
                    }
                    return this.sealBase.hashCode();
                }

                @SuppressFBWarnings(value = {"DMI_BLOCKING_METHODS_ON_URL"}, justification = "Package sealing relies on URL equality.")
                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    ForFixedValue forFixedValue = (ForFixedValue) obj;
                    if (this.sealBase == null) {
                        return forFixedValue.sealBase == null;
                    }
                    return this.sealBase.equals(forFixedValue.sealBase);
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            /* loaded from: infinitode-2.jar:net/bytebuddy/dynamic/loading/PackageDefinitionStrategy$ManifestReading$SealBaseLocator$ForTypeResourceUrl.class */
            public static class ForTypeResourceUrl implements SealBaseLocator {
                private static final int EXCLUDE_INITIAL_SLASH = 1;
                private static final String CLASS_FILE_EXTENSION = ".class";
                private static final String JAR_FILE = "jar";
                private static final String FILE_SYSTEM = "file";
                private static final String RUNTIME_IMAGE = "jrt";
                private final SealBaseLocator fallback;

                public boolean equals(@MaybeNull Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.fallback.equals(((ForTypeResourceUrl) obj).fallback);
                }

                public int hashCode() {
                    return (getClass().hashCode() * 31) + this.fallback.hashCode();
                }

                public ForTypeResourceUrl() {
                    this(NonSealing.INSTANCE);
                }

                public ForTypeResourceUrl(SealBaseLocator sealBaseLocator) {
                    this.fallback = sealBaseLocator;
                }

                @Override // net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.ManifestReading.SealBaseLocator
                @MaybeNull
                public URL findSealBase(ClassLoader classLoader, String str) {
                    URL resource = classLoader.getResource(str.replace('.', '/') + ".class");
                    if (resource != null) {
                        try {
                            if (resource.getProtocol().equals(JAR_FILE)) {
                                return new URL(resource.getPath().substring(0, resource.getPath().indexOf(33)));
                            }
                            if (resource.getProtocol().equals(FILE_SYSTEM)) {
                                return resource;
                            }
                            if (resource.getProtocol().equals(RUNTIME_IMAGE)) {
                                String path = resource.getPath();
                                int indexOf = path.indexOf(47, 1);
                                if (indexOf == -1) {
                                    return resource;
                                }
                                return new URL("jrt:" + path.substring(0, indexOf));
                            }
                        } catch (MalformedURLException e) {
                            throw new IllegalStateException("Unexpected URL: " + resource, e);
                        }
                    }
                    return this.fallback.findSealBase(classLoader, str);
                }
            }
        }
    }
}
