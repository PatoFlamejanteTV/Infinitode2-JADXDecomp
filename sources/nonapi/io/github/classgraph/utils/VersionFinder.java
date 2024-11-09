package nonapi.io.github.classgraph.utils;

import io.github.classgraph.ClassGraph;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import net.bytebuddy.ClassFileVersion;
import org.w3c.dom.Document;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/utils/VersionFinder.class */
public final class VersionFinder {
    private static final String MAVEN_PACKAGE = "io.github.classgraph";
    private static final String MAVEN_ARTIFACT = "classgraph";
    public static final OperatingSystem OS;
    public static final String JAVA_VERSION = getProperty(ClassFileVersion.VersionLocator.JAVA_VERSION);
    public static final int JAVA_MAJOR_VERSION;
    public static final int JAVA_MINOR_VERSION;
    public static final int JAVA_SUB_VERSION;
    public static final boolean JAVA_IS_EA_VERSION;

    /* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/utils/VersionFinder$OperatingSystem.class */
    public enum OperatingSystem {
        Windows,
        MacOSX,
        Linux,
        Solaris,
        BSD,
        Unix,
        Unknown
    }

    static {
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        ArrayList arrayList = new ArrayList();
        if (JAVA_VERSION != null) {
            for (String str : JAVA_VERSION.split("[^0-9]+")) {
                try {
                    arrayList.add(Integer.valueOf(Integer.parseInt(str)));
                } catch (NumberFormatException unused) {
                }
            }
            if (!arrayList.isEmpty() && ((Integer) arrayList.get(0)).intValue() == 1) {
                arrayList.remove(0);
            }
            if (arrayList.isEmpty()) {
                throw new RuntimeException("Could not determine Java version: " + JAVA_VERSION);
            }
            i = ((Integer) arrayList.get(0)).intValue();
            if (arrayList.size() > 1) {
                i2 = ((Integer) arrayList.get(1)).intValue();
            }
            if (arrayList.size() > 2) {
                i3 = ((Integer) arrayList.get(2)).intValue();
            }
        }
        JAVA_MAJOR_VERSION = i;
        JAVA_MINOR_VERSION = i2;
        JAVA_SUB_VERSION = i3;
        JAVA_IS_EA_VERSION = JAVA_VERSION != null && JAVA_VERSION.endsWith(ClassFileVersion.VersionLocator.EARLY_ACCESS);
        String lowerCase = getProperty("os.name", "unknown").toLowerCase(Locale.ENGLISH);
        if (File.separatorChar == '\\') {
            OS = OperatingSystem.Windows;
            return;
        }
        if (lowerCase != null) {
            if (lowerCase.contains("win")) {
                OS = OperatingSystem.Windows;
                return;
            }
            if (lowerCase.contains("mac") || lowerCase.contains("darwin")) {
                OS = OperatingSystem.MacOSX;
                return;
            }
            if (lowerCase.contains("nux")) {
                OS = OperatingSystem.Linux;
                return;
            }
            if (lowerCase.contains("sunos") || lowerCase.contains("solaris")) {
                OS = OperatingSystem.Solaris;
                return;
            } else if (lowerCase.contains("bsd")) {
                OS = OperatingSystem.Unix;
                return;
            } else if (lowerCase.contains("nix") || lowerCase.contains("aix")) {
                OS = OperatingSystem.Unix;
                return;
            }
        }
        OS = OperatingSystem.Unknown;
    }

    private VersionFinder() {
    }

    public static String getProperty(String str) {
        try {
            return System.getProperty(str);
        } catch (SecurityException unused) {
            return null;
        }
    }

    public static String getProperty(String str, String str2) {
        try {
            return System.getProperty(str, str2);
        } catch (SecurityException unused) {
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v28 */
    /* JADX WARN: Type inference failed for: r0v29 */
    /* JADX WARN: Type inference failed for: r0v35, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v81, types: [javax.xml.parsers.DocumentBuilder] */
    /* JADX WARN: Type inference failed for: r6v10, types: [int] */
    /* JADX WARN: Type inference failed for: r6v13 */
    /* JADX WARN: Type inference failed for: r6v5, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v6 */
    /* JADX WARN: Type inference failed for: r6v9 */
    /* JADX WARN: Type inference failed for: r7v12, types: [java.net.URL] */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v15, types: [java.nio.file.Path] */
    /* JADX WARN: Type inference failed for: r7v16 */
    /* JADX WARN: Type inference failed for: r7v17, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v20 */
    /* JADX WARN: Type inference failed for: r7v21 */
    /* JADX WARN: Type inference failed for: r9v2, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v4 */
    public static synchronized String getVersion() {
        boolean name;
        boolean z;
        try {
            name = ClassGraph.class.getName();
            URL resource = ClassGraph.class.getResource("/" + JarUtils.classNameToClassfilePath(name));
            z = resource;
            if (resource != null) {
                Path parent = Paths.get(z.toURI()).getParent();
                int length = name.length() - name.replace(".", "").length();
                Path path = parent;
                for (int i = 0; i < length && path != null; i++) {
                    path = path.getParent();
                }
                name = 0;
                z = z;
                while (name < 3 && path != null) {
                    ?? resolve = path.resolve("pom.xml");
                    try {
                        resolve = Files.newInputStream(resolve, new OpenOption[0]);
                        String str = null;
                        boolean z2 = 0;
                        try {
                            try {
                                Document parse = getSecureDocumentBuilderFactory().newDocumentBuilder().parse(resolve);
                                parse.getDocumentElement().normalize();
                                str = (String) getSecureXPathFactory().newXPath().compile("/project/version").evaluate(parse, XPathConstants.STRING);
                                if (str != null) {
                                    String trim = str.trim();
                                    if (!trim.isEmpty()) {
                                        if (resolve != 0) {
                                            if (0 != 0) {
                                                try {
                                                    resolve.close();
                                                } catch (Throwable th) {
                                                    z2.addSuppressed(th);
                                                }
                                            } else {
                                                resolve.close();
                                            }
                                        }
                                        return trim;
                                    }
                                }
                                if (resolve != 0) {
                                    if (0 != 0) {
                                        try {
                                            resolve.close();
                                        } catch (Throwable th2) {
                                            z2.addSuppressed(th2);
                                        }
                                    } else {
                                        resolve.close();
                                    }
                                }
                            } catch (Throwable th3) {
                                if (resolve != 0) {
                                    if (z2) {
                                        try {
                                            resolve.close();
                                        } catch (Throwable th4) {
                                            z2.addSuppressed(th4);
                                        }
                                    } else {
                                        resolve.close();
                                    }
                                }
                                throw th3;
                            }
                        } catch (Throwable th5) {
                            z2 = str;
                            throw th5;
                        }
                    } catch (IOException unused) {
                    }
                    path = path.getParent();
                    name++;
                    z = resolve;
                }
            }
        } catch (Exception unused2) {
        }
        try {
            try {
                InputStream resourceAsStream = ClassGraph.class.getResourceAsStream("/META-INF/maven/io.github.classgraph/classgraph/pom.properties");
                Throwable th6 = null;
                ?? r0 = resourceAsStream;
                if (r0 != 0) {
                    try {
                        Properties properties = new Properties();
                        properties.load(resourceAsStream);
                        String trim2 = properties.getProperty("version", "").trim();
                        r0 = trim2.isEmpty();
                        if (r0 == 0) {
                            if (resourceAsStream != null) {
                                if (0 != 0) {
                                    try {
                                        resourceAsStream.close();
                                    } catch (Throwable th7) {
                                        th6.addSuppressed(th7);
                                    }
                                } else {
                                    resourceAsStream.close();
                                }
                            }
                            return trim2;
                        }
                    } finally {
                        boolean z3 = r0;
                    }
                }
                if (resourceAsStream != null) {
                    if (0 != 0) {
                        try {
                            resourceAsStream.close();
                        } catch (Throwable th8) {
                            th6.addSuppressed(th8);
                        }
                    } else {
                        resourceAsStream.close();
                    }
                }
            } catch (Throwable th9) {
                if (name) {
                    if (z) {
                        try {
                            name.close();
                        } catch (Throwable th10) {
                            z.addSuppressed(th10);
                        }
                    } else {
                        name.close();
                    }
                }
                throw th9;
            }
        } catch (IOException unused3) {
        }
        Package r02 = ClassGraph.class.getPackage();
        if (r02 != null) {
            String implementationVersion = r02.getImplementationVersion();
            String str2 = implementationVersion;
            if (implementationVersion == null) {
                str2 = "";
            }
            String trim3 = str2.trim();
            String str3 = trim3;
            if (trim3.isEmpty()) {
                String specificationVersion = r02.getSpecificationVersion();
                String str4 = specificationVersion;
                if (specificationVersion == null) {
                    str4 = "";
                }
                str3 = str4.trim();
            }
            if (!str3.isEmpty()) {
                return str3;
            }
            return "unknown";
        }
        return "unknown";
    }

    private static DocumentBuilderFactory getSecureDocumentBuilderFactory() {
        DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
        newInstance.setXIncludeAware(false);
        newInstance.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
        newInstance.setAttribute("http://javax.xml.XMLConstants/property/accessExternalDTD", "");
        newInstance.setAttribute("http://javax.xml.XMLConstants/property/accessExternalSchema", "");
        newInstance.setExpandEntityReferences(false);
        newInstance.setNamespaceAware(true);
        newInstance.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        newInstance.setFeature("http://xml.org/sax/features/external-general-entities", false);
        newInstance.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        newInstance.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        return newInstance;
    }

    private static XPathFactory getSecureXPathFactory() {
        XPathFactory newInstance = XPathFactory.newInstance();
        newInstance.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
        return newInstance;
    }
}
