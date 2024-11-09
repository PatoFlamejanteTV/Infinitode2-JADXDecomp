package org.lwjgl;

import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Objects;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

/* loaded from: infinitode-2.jar:org/lwjgl/Version.class */
public final class Version {
    public static final int VERSION_MAJOR = 3;
    public static final int VERSION_MINOR = 3;
    public static final int VERSION_REVISION = 4;
    public static final BuildType BUILD_TYPE = BuildType.STABLE;
    private static final String versionPlain = "3.3.4" + BUILD_TYPE.postfix;
    private static final String version = versionPlain + VersionImpl.find();

    private Version() {
    }

    public static void main(String[] strArr) {
        System.out.println(version);
        System.err.println(versionPlain);
    }

    public static String getVersion() {
        return version;
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/Version$BuildType.class */
    public enum BuildType {
        ALPHA(FlexmarkHtmlConverter.A_NODE),
        BETA(FlexmarkHtmlConverter.B_NODE),
        STABLE("");

        public final String postfix;

        BuildType(String str) {
            this.postfix = str;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String createImplementation(String str, String str2) {
        String str3 = "+" + ((!str2.startsWith("build ") || 6 >= str2.length()) ? str2 : str2.substring(6));
        if (str.contains("SNAPSHOT") || str.contains("snapshot")) {
            return "-snapshot" + str3;
        }
        return str3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String findImplementationFromManifest() {
        ClassLoader classLoader = Version.class.getClassLoader();
        URL resource = classLoader.getResource("org/lwjgl/Version.class");
        if (resource != null) {
            String url = resource.toString();
            try {
                if (url.startsWith("jar:")) {
                    String readImplementationFromManifest = readImplementationFromManifest((URL) Objects.requireNonNull(Version.class.getResource("/META-INF/MANIFEST.MF")));
                    if (readImplementationFromManifest != null) {
                        return readImplementationFromManifest;
                    }
                    return null;
                }
                if (url.startsWith("resource:")) {
                    Enumeration<URL> resources = classLoader.getResources("META-INF/MANIFEST.MF");
                    while (resources.hasMoreElements()) {
                        String readImplementationFromManifest2 = readImplementationFromManifest(resources.nextElement());
                        if (readImplementationFromManifest2 != null) {
                            return readImplementationFromManifest2;
                        }
                    }
                    return null;
                }
                return null;
            } catch (Exception unused) {
                return null;
            }
        }
        return null;
    }

    private static String readImplementationFromManifest(URL url) {
        try {
            InputStream openStream = url.openStream();
            Throwable th = null;
            try {
                Attributes mainAttributes = new Manifest(openStream).getMainAttributes();
                if (!"lwjgl".equals(mainAttributes.getValue(Attributes.Name.IMPLEMENTATION_TITLE))) {
                    return null;
                }
                if (!"lwjgl.org".equals(mainAttributes.getValue(Attributes.Name.IMPLEMENTATION_VENDOR))) {
                    if (openStream != null) {
                        if (0 != 0) {
                            try {
                                openStream.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                        } else {
                            openStream.close();
                        }
                    }
                    return null;
                }
                String value = mainAttributes.getValue(Attributes.Name.SPECIFICATION_VERSION);
                String value2 = mainAttributes.getValue(Attributes.Name.IMPLEMENTATION_VERSION);
                if (value == null || value2 == null) {
                    if (openStream != null) {
                        if (0 != 0) {
                            try {
                                openStream.close();
                            } catch (Throwable th3) {
                                th.addSuppressed(th3);
                            }
                        } else {
                            openStream.close();
                        }
                    }
                    return null;
                }
                String createImplementation = createImplementation(value, value2);
                if (openStream != null) {
                    if (0 != 0) {
                        try {
                            openStream.close();
                        } catch (Throwable th4) {
                            th.addSuppressed(th4);
                        }
                    } else {
                        openStream.close();
                    }
                }
                return createImplementation;
            } finally {
                if (openStream != null) {
                    if (0 != 0) {
                        try {
                            openStream.close();
                        } catch (Throwable th5) {
                            th.addSuppressed(th5);
                        }
                    } else {
                        openStream.close();
                    }
                }
            }
        } catch (Exception unused) {
            return null;
        }
        return null;
    }
}
