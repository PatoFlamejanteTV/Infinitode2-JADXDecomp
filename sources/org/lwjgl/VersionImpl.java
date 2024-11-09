package org.lwjgl;

/* loaded from: infinitode-2.jar:org/lwjgl/VersionImpl.class */
final class VersionImpl {
    VersionImpl() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String find() {
        Package r0 = Version.class.getPackage();
        String specificationVersion = r0.getSpecificationVersion();
        String implementationVersion = r0.getImplementationVersion();
        if (specificationVersion != null && implementationVersion != null) {
            return Version.createImplementation(specificationVersion, implementationVersion);
        }
        String findImplementationFromManifest = Version.findImplementationFromManifest();
        if (findImplementationFromManifest != null) {
            return findImplementationFromManifest;
        }
        return "-snapshot";
    }
}
