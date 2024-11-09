package nonapi.io.github.classgraph.fastzipfilereader;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import nonapi.io.github.classgraph.utils.LogNode;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/fastzipfilereader/LogicalZipFile.class */
public class LogicalZipFile extends ZipFileSlice {
    public List<FastZipEntry> entries;
    private boolean isMultiReleaseJar;
    Set<String> classpathRoots;
    public String classPathManifestEntryValue;
    public String bundleClassPathManifestEntryValue;
    public String addExportsManifestEntryValue;
    public String addOpensManifestEntryValue;
    public String automaticModuleNameManifestEntryValue;
    public boolean isJREJar;
    private final boolean enableMultiReleaseVersions;
    static final String META_INF_PATH_PREFIX = "META-INF/";
    private static final String MANIFEST_PATH = "META-INF/MANIFEST.MF";
    public static final String MULTI_RELEASE_PATH_PREFIX = "META-INF/versions/";
    private static final byte[] IMPLEMENTATION_TITLE_KEY = manifestKeyToBytes("Implementation-Title");
    private static final byte[] SPECIFICATION_TITLE_KEY = manifestKeyToBytes("Specification-Title");
    private static final byte[] CLASS_PATH_KEY = manifestKeyToBytes("Class-Path");
    private static final byte[] BUNDLE_CLASSPATH_KEY = manifestKeyToBytes("Bundle-ClassPath");
    private static final byte[] SPRING_BOOT_CLASSES_KEY = manifestKeyToBytes("Spring-Boot-Classes");
    private static final byte[] SPRING_BOOT_LIB_KEY = manifestKeyToBytes("Spring-Boot-Lib");
    private static final byte[] MULTI_RELEASE_KEY = manifestKeyToBytes("Multi-Release");
    private static final byte[] ADD_EXPORTS_KEY = manifestKeyToBytes("Add-Exports");
    private static final byte[] ADD_OPENS_KEY = manifestKeyToBytes("Add-Opens");
    private static final byte[] AUTOMATIC_MODULE_NAME_KEY = manifestKeyToBytes("Automatic-Module-Name");
    private static byte[] toLowerCase = new byte[256];

    static {
        for (int i = 32; i < 127; i++) {
            int i2 = i;
            toLowerCase[i2] = (byte) Character.toLowerCase((char) i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LogicalZipFile(ZipFileSlice zipFileSlice, NestedJarHandler nestedJarHandler, LogNode logNode, boolean z) {
        super(zipFileSlice);
        this.classpathRoots = Collections.newSetFromMap(new ConcurrentHashMap());
        this.enableMultiReleaseVersions = z;
        readCentralDirectory(nestedJarHandler, logNode);
    }

    /* JADX WARN: Code restructure failed: missing block: B:72:0x0067, code lost:            if (r8 >= (r0 - 1)) goto L32;     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0071, code lost:            if (r7[r8 + 1] != 32) goto L32;     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0074, code lost:            r11 = true;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.util.Map.Entry<java.lang.String, java.lang.Integer> getManifestValue(byte[] r7, int r8) {
        /*
            Method dump skipped, instructions count: 311
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: nonapi.io.github.classgraph.fastzipfilereader.LogicalZipFile.getManifestValue(byte[], int):java.util.Map$Entry");
    }

    private static byte[] manifestKeyToBytes(String str) {
        byte[] bArr = new byte[str.length()];
        for (int i = 0; i < str.length(); i++) {
            bArr[i] = (byte) Character.toLowerCase(str.charAt(i));
        }
        return bArr;
    }

    private static boolean keyMatchesAtPosition(byte[] bArr, byte[] bArr2, int i) {
        if (i + bArr2.length + 1 > bArr.length || bArr[i + bArr2.length] != 58) {
            return false;
        }
        for (int i2 = 0; i2 < bArr2.length; i2++) {
            if (toLowerCase[bArr[i2 + i]] != bArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    private void parseManifest(FastZipEntry fastZipEntry, LogNode logNode) {
        byte[] load = fastZipEntry.getSlice().load();
        int i = 0;
        while (i < load.length) {
            boolean z = false;
            if (load[i] == 10 || load[i] == 13) {
                z = true;
            } else if (keyMatchesAtPosition(load, IMPLEMENTATION_TITLE_KEY, i)) {
                Map.Entry<String, Integer> manifestValue = getManifestValue(load, i + IMPLEMENTATION_TITLE_KEY.length + 1);
                if (manifestValue.getKey().equalsIgnoreCase("Java Runtime Environment")) {
                    this.isJREJar = true;
                }
                i = manifestValue.getValue().intValue();
            } else if (keyMatchesAtPosition(load, SPECIFICATION_TITLE_KEY, i)) {
                Map.Entry<String, Integer> manifestValue2 = getManifestValue(load, i + SPECIFICATION_TITLE_KEY.length + 1);
                if (manifestValue2.getKey().equalsIgnoreCase("Java Platform API Specification")) {
                    this.isJREJar = true;
                }
                i = manifestValue2.getValue().intValue();
            } else if (keyMatchesAtPosition(load, CLASS_PATH_KEY, i)) {
                Map.Entry<String, Integer> manifestValue3 = getManifestValue(load, i + CLASS_PATH_KEY.length + 1);
                this.classPathManifestEntryValue = manifestValue3.getKey();
                if (logNode != null) {
                    logNode.log("Found Class-Path entry in manifest file: " + this.classPathManifestEntryValue);
                }
                i = manifestValue3.getValue().intValue();
            } else if (keyMatchesAtPosition(load, BUNDLE_CLASSPATH_KEY, i)) {
                Map.Entry<String, Integer> manifestValue4 = getManifestValue(load, i + BUNDLE_CLASSPATH_KEY.length + 1);
                this.bundleClassPathManifestEntryValue = manifestValue4.getKey();
                if (logNode != null) {
                    logNode.log("Found Bundle-ClassPath entry in manifest file: " + this.bundleClassPathManifestEntryValue);
                }
                i = manifestValue4.getValue().intValue();
            } else if (keyMatchesAtPosition(load, SPRING_BOOT_CLASSES_KEY, i)) {
                Map.Entry<String, Integer> manifestValue5 = getManifestValue(load, i + SPRING_BOOT_CLASSES_KEY.length + 1);
                String key = manifestValue5.getKey();
                if (!key.equals("BOOT-INF/classes") && !key.equals("BOOT-INF/classes/") && !key.equals("WEB-INF/classes") && !key.equals("WEB-INF/classes/")) {
                    throw new IOException("Spring boot classes are at \"" + key + "\" rather than the standard location \"BOOT-INF/classes/\" or \"WEB-INF/classes/\" -- please report this at https://github.com/classgraph/classgraph/issues");
                }
                i = manifestValue5.getValue().intValue();
            } else if (keyMatchesAtPosition(load, SPRING_BOOT_LIB_KEY, i)) {
                Map.Entry<String, Integer> manifestValue6 = getManifestValue(load, i + SPRING_BOOT_LIB_KEY.length + 1);
                String key2 = manifestValue6.getKey();
                if (!key2.equals("BOOT-INF/lib") && !key2.equals("BOOT-INF/lib/") && !key2.equals("WEB-INF/lib") && !key2.equals("WEB-INF/lib/")) {
                    throw new IOException("Spring boot lib jars are at \"" + key2 + "\" rather than the standard location \"BOOT-INF/lib/\" or \"WEB-INF/lib/\" -- please report this at https://github.com/classgraph/classgraph/issues");
                }
                i = manifestValue6.getValue().intValue();
            } else if (keyMatchesAtPosition(load, MULTI_RELEASE_KEY, i)) {
                Map.Entry<String, Integer> manifestValue7 = getManifestValue(load, i + MULTI_RELEASE_KEY.length + 1);
                if (manifestValue7.getKey().equalsIgnoreCase("true")) {
                    this.isMultiReleaseJar = true;
                }
                i = manifestValue7.getValue().intValue();
            } else if (keyMatchesAtPosition(load, ADD_EXPORTS_KEY, i)) {
                Map.Entry<String, Integer> manifestValue8 = getManifestValue(load, i + ADD_EXPORTS_KEY.length + 1);
                this.addExportsManifestEntryValue = manifestValue8.getKey();
                if (logNode != null) {
                    logNode.log("Found Add-Exports entry in manifest file: " + this.addExportsManifestEntryValue);
                }
                i = manifestValue8.getValue().intValue();
            } else if (keyMatchesAtPosition(load, ADD_OPENS_KEY, i)) {
                Map.Entry<String, Integer> manifestValue9 = getManifestValue(load, i + ADD_OPENS_KEY.length + 1);
                this.addExportsManifestEntryValue = manifestValue9.getKey();
                if (logNode != null) {
                    logNode.log("Found Add-Opens entry in manifest file: " + this.addOpensManifestEntryValue);
                }
                i = manifestValue9.getValue().intValue();
            } else if (keyMatchesAtPosition(load, AUTOMATIC_MODULE_NAME_KEY, i)) {
                Map.Entry<String, Integer> manifestValue10 = getManifestValue(load, i + AUTOMATIC_MODULE_NAME_KEY.length + 1);
                this.automaticModuleNameManifestEntryValue = manifestValue10.getKey();
                if (logNode != null) {
                    logNode.log("Found Automatic-Module-Name entry in manifest file: " + this.automaticModuleNameManifestEntryValue);
                }
                i = manifestValue10.getValue().intValue();
            } else {
                z = true;
            }
            if (z) {
                while (true) {
                    if (i < load.length - 2) {
                        if (load[i] == 13 && load[i + 1] == 10 && load[i + 2] != 32) {
                            i += 2;
                            break;
                        } else if ((load[i] != 13 && load[i] != 10) || load[i + 1] == 32) {
                            i++;
                        } else {
                            i++;
                            break;
                        }
                    } else {
                        break;
                    }
                }
                if (i >= load.length - 2) {
                    return;
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:308:0x0b37, code lost:            if (r37 == null) goto L280;     */
    /* JADX WARN: Code restructure failed: missing block: B:309:0x0b3a, code lost:            parseManifest(r37, r20);     */
    /* JADX WARN: Code restructure failed: missing block: B:311:0x0b45, code lost:            if (r18.isMultiReleaseJar == false) goto L374;     */
    /* JADX WARN: Code restructure failed: missing block: B:313:0x0b4d, code lost:            if (nonapi.io.github.classgraph.utils.VersionFinder.JAVA_MAJOR_VERSION >= 9) goto L288;     */
    /* JADX WARN: Code restructure failed: missing block: B:315:0x0b51, code lost:            if (r20 == null) goto L375;     */
    /* JADX WARN: Code restructure failed: missing block: B:316:0x0b54, code lost:            r20.log("This is a multi-release jar, but JRE version " + nonapi.io.github.classgraph.utils.VersionFinder.JAVA_MAJOR_VERSION + " does not support multi-release jars");     */
    /* JADX WARN: Code restructure failed: missing block: B:317:0x0b70, code lost:            return;     */
    /* JADX WARN: Code restructure failed: missing block: B:318:?, code lost:            return;     */
    /* JADX WARN: Code restructure failed: missing block: B:320:0x0b72, code lost:            if (r20 == null) goto L298;     */
    /* JADX WARN: Code restructure failed: missing block: B:321:0x0b75, code lost:            r0 = new java.util.HashSet();        r0 = r18.entries.iterator();     */
    /* JADX WARN: Code restructure failed: missing block: B:323:0x0b90, code lost:            if (r0.hasNext() == false) goto L361;     */
    /* JADX WARN: Code restructure failed: missing block: B:324:0x0b93, code lost:            r0 = r0.next();     */
    /* JADX WARN: Code restructure failed: missing block: B:325:0x0ba5, code lost:            if (r0.version <= 8) goto L364;     */
    /* JADX WARN: Code restructure failed: missing block: B:327:0x0ba8, code lost:            r0.add(java.lang.Integer.valueOf(r0.version));     */
    /* JADX WARN: Code restructure failed: missing block: B:332:0x0bbb, code lost:            r0 = new java.util.ArrayList(r0);        nonapi.io.github.classgraph.utils.CollectionUtils.sortIfNotEmpty(r0);        r20.log("This is a multi-release jar, with versions: " + nonapi.io.github.classgraph.utils.StringUtils.join(", ", r0));     */
    /* JADX WARN: Code restructure failed: missing block: B:333:0x0be5, code lost:            nonapi.io.github.classgraph.utils.CollectionUtils.sortIfNotEmpty(r18.entries);        r0 = new java.util.ArrayList(r18.entries.size());        r0 = new java.util.HashMap();        r0 = r18.entries.iterator();     */
    /* JADX WARN: Code restructure failed: missing block: B:335:0x0c19, code lost:            if (r0.hasNext() == false) goto L367;     */
    /* JADX WARN: Code restructure failed: missing block: B:336:0x0c1c, code lost:            r0 = r0.next();     */
    /* JADX WARN: Code restructure failed: missing block: B:337:0x0c34, code lost:            if (r0.containsKey(r0.entryNameUnversioned) != false) goto L366;     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00fd, code lost:            r0 = r31 + r0;        r22 = r0;     */
    /* JADX WARN: Code restructure failed: missing block: B:340:0x0c57, code lost:            if (r20 == null) goto L371;     */
    /* JADX WARN: Code restructure failed: missing block: B:342:0x0c5a, code lost:            r20.log(((java.lang.String) r0.get(r0.entryNameUnversioned)) + " masks " + r0.entryName);     */
    /* JADX WARN: Code restructure failed: missing block: B:347:0x0c37, code lost:            r0.put(r0.entryNameUnversioned, r0.entryName);        r0.add(r0);     */
    /* JADX WARN: Code restructure failed: missing block: B:350:0x0c8b, code lost:            r18.entries = r0;     */
    /* JADX WARN: Code restructure failed: missing block: B:351:0x0c91, code lost:            return;     */
    /* JADX WARN: Code restructure failed: missing block: B:352:?, code lost:            return;     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v122, types: [long] */
    /* JADX WARN: Type inference failed for: r0v383 */
    /* JADX WARN: Type inference failed for: r0v389 */
    /* JADX WARN: Type inference failed for: r0v407, types: [long] */
    /* JADX WARN: Type inference failed for: r1v175, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r2v103 */
    /* JADX WARN: Type inference failed for: r2v116 */
    /* JADX WARN: Type inference failed for: r2v125 */
    /* JADX WARN: Type inference failed for: r2v210 */
    /* JADX WARN: Type inference failed for: r2v211 */
    /* JADX WARN: Type inference failed for: r2v61 */
    /* JADX WARN: Type inference failed for: r2v62 */
    /* JADX WARN: Type inference failed for: r2v85 */
    /* JADX WARN: Type inference failed for: r2v86 */
    /* JADX WARN: Type inference failed for: r2v88, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v26 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void readCentralDirectory(nonapi.io.github.classgraph.fastzipfilereader.NestedJarHandler r19, nonapi.io.github.classgraph.utils.LogNode r20) {
        /*
            Method dump skipped, instructions count: 3218
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: nonapi.io.github.classgraph.fastzipfilereader.LogicalZipFile.readCentralDirectory(nonapi.io.github.classgraph.fastzipfilereader.NestedJarHandler, nonapi.io.github.classgraph.utils.LogNode):void");
    }

    @Override // nonapi.io.github.classgraph.fastzipfilereader.ZipFileSlice
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // nonapi.io.github.classgraph.fastzipfilereader.ZipFileSlice
    public int hashCode() {
        return super.hashCode();
    }

    @Override // nonapi.io.github.classgraph.fastzipfilereader.ZipFileSlice
    public String toString() {
        return getPath();
    }
}
