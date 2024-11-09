package io.github.classgraph;

import java.io.Closeable;
import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import nonapi.io.github.classgraph.classpath.ClasspathFinder;
import nonapi.io.github.classgraph.concurrency.AutoCloseableExecutorService;
import nonapi.io.github.classgraph.fastzipfilereader.NestedJarHandler;
import nonapi.io.github.classgraph.json.JSONDeserializer;
import nonapi.io.github.classgraph.json.JSONSerializer;
import nonapi.io.github.classgraph.reflection.ReflectionUtils;
import nonapi.io.github.classgraph.scanspec.AcceptReject;
import nonapi.io.github.classgraph.scanspec.ScanSpec;
import nonapi.io.github.classgraph.utils.Assert;
import nonapi.io.github.classgraph.utils.CollectionUtils;
import nonapi.io.github.classgraph.utils.FileUtils;
import nonapi.io.github.classgraph.utils.JarUtils;
import nonapi.io.github.classgraph.utils.LogNode;

/* loaded from: infinitode-2.jar:io/github/classgraph/ScanResult.class */
public final class ScanResult implements Closeable {
    private List<String> rawClasspathEltOrderStrs;
    private List<ClasspathElement> classpathOrder;
    private ResourceList allAcceptedResourcesCached;
    private Map<String, ResourceList> pathToAcceptedResourcesCached;
    Map<String, ClassInfo> classNameToClassInfo;
    private Map<String, PackageInfo> packageNameToPackageInfo;
    private Map<String, ModuleInfo> moduleNameToModuleInfo;
    private Map<File, Long> fileToLastModified;
    private boolean isObtainedFromDeserialization;
    private ClassGraphClassLoader classGraphClassLoader;
    ClasspathFinder classpathFinder;
    private NestedJarHandler nestedJarHandler;
    ScanSpec scanSpec;
    protected ReflectionUtils reflectionUtils;
    private final LogNode topLevelLog;
    private final WeakReference<ScanResult> weakReference;
    private static Set<WeakReference<ScanResult>> nonClosedWeakReferences = Collections.newSetFromMap(new ConcurrentHashMap());
    private static final AtomicBoolean initialized = new AtomicBoolean(false);
    private static final String CURRENT_SERIALIZATION_FORMAT = "10";
    private final AtomicInteger getResourcesWithPathCallCount = new AtomicInteger();
    private final AtomicBoolean closed = new AtomicBoolean(false);

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:io/github/classgraph/ScanResult$SerializationFormat.class */
    public static class SerializationFormat {
        public String format;
        public ScanSpec scanSpec;
        public List<String> classpath;
        public List<ClassInfo> classInfo;
        public List<PackageInfo> packageInfo;
        public List<ModuleInfo> moduleInfo;

        public SerializationFormat() {
        }

        public SerializationFormat(String str, ScanSpec scanSpec, List<ClassInfo> list, List<PackageInfo> list2, List<ModuleInfo> list3, List<String> list4) {
            this.format = str;
            this.scanSpec = scanSpec;
            this.classpath = list4;
            this.classInfo = list;
            this.packageInfo = list2;
            this.moduleInfo = list3;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void init(ReflectionUtils reflectionUtils) {
        if (!initialized.getAndSet(true)) {
            FileUtils.closeDirectByteBuffer(ByteBuffer.allocateDirect(32), reflectionUtils, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ScanResult(ScanSpec scanSpec, List<ClasspathElement> list, List<String> list2, ClasspathFinder classpathFinder, Map<String, ClassInfo> map, Map<String, PackageInfo> map2, Map<String, ModuleInfo> map3, Map<File, Long> map4, NestedJarHandler nestedJarHandler, LogNode logNode) {
        AnnotationInfo annotationInfo;
        String name;
        this.scanSpec = scanSpec;
        this.rawClasspathEltOrderStrs = list2;
        this.classpathOrder = list;
        this.classpathFinder = classpathFinder;
        this.fileToLastModified = map4;
        this.classNameToClassInfo = map;
        this.packageNameToPackageInfo = map2;
        this.moduleNameToModuleInfo = map3;
        this.nestedJarHandler = nestedJarHandler;
        this.reflectionUtils = nestedJarHandler.reflectionUtils;
        this.topLevelLog = logNode;
        if (map != null) {
            indexResourcesAndClassInfo(logNode);
        }
        if (map != null) {
            HashSet hashSet = new HashSet();
            for (ClassInfo classInfo : map.values()) {
                if (classInfo.isAnnotation() && classInfo.annotationInfo != null && (annotationInfo = classInfo.annotationInfo.get("java.lang.annotation.Repeatable")) != null) {
                    AnnotationParameterValueList parameterValues = annotationInfo.getParameterValues();
                    if (!parameterValues.isEmpty()) {
                        Object value = parameterValues.getValue("value");
                        if ((value instanceof AnnotationClassRef) && (name = ((AnnotationClassRef) value).getName()) != null) {
                            hashSet.add(name);
                        }
                    }
                }
            }
            if (!hashSet.isEmpty()) {
                Iterator<ClassInfo> it = map.values().iterator();
                while (it.hasNext()) {
                    it.next().handleRepeatableAnnotations(hashSet);
                }
            }
        }
        this.classGraphClassLoader = new ClassGraphClassLoader(this);
        this.weakReference = new WeakReference<>(this);
        nonClosedWeakReferences.add(this.weakReference);
    }

    private void indexResourcesAndClassInfo(LogNode logNode) {
        Iterator<ClassInfo> it = this.classNameToClassInfo.values().iterator();
        while (it.hasNext()) {
            it.next().setScanResult(this);
        }
        if (this.scanSpec.enableInterClassDependencies) {
            Iterator it2 = new ArrayList(this.classNameToClassInfo.values()).iterator();
            while (it2.hasNext()) {
                ClassInfo classInfo = (ClassInfo) it2.next();
                HashSet hashSet = new HashSet();
                for (ClassInfo classInfo2 : classInfo.findReferencedClassInfo(logNode)) {
                    if (classInfo2 != null && !classInfo.equals(classInfo2) && !classInfo2.getName().equals("java.lang.Object") && (!classInfo2.isExternalClass() || this.scanSpec.enableExternalClasses)) {
                        classInfo2.setScanResult(this);
                        hashSet.add(classInfo2);
                    }
                }
                classInfo.setReferencedClasses(new ClassInfoList((Set<ClassInfo>) hashSet, true));
            }
        }
    }

    public final List<File> getClasspathFiles() {
        if (this.closed.get()) {
            throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
        }
        ArrayList arrayList = new ArrayList();
        Iterator<ClasspathElement> it = this.classpathOrder.iterator();
        while (it.hasNext()) {
            File file = it.next().getFile();
            if (file != null) {
                arrayList.add(file);
            }
        }
        return arrayList;
    }

    public final String getClasspath() {
        if (this.closed.get()) {
            throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
        }
        return JarUtils.pathElementsToPathStr(getClasspathFiles());
    }

    public final List<URI> getClasspathURIs() {
        if (this.closed.get()) {
            throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
        }
        ArrayList arrayList = new ArrayList();
        Iterator<ClasspathElement> it = this.classpathOrder.iterator();
        while (it.hasNext()) {
            try {
                for (URI uri : it.next().getAllURIs()) {
                    if (uri != null) {
                        arrayList.add(uri);
                    }
                }
            } catch (IllegalArgumentException unused) {
            }
        }
        return arrayList;
    }

    public final List<URL> getClasspathURLs() {
        if (this.closed.get()) {
            throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
        }
        ArrayList arrayList = new ArrayList();
        Iterator<URI> it = getClasspathURIs().iterator();
        while (it.hasNext()) {
            try {
                arrayList.add(it.next().toURL());
            } catch (IllegalArgumentException | MalformedURLException unused) {
            }
        }
        return arrayList;
    }

    public final List<ModuleRef> getModules() {
        if (this.closed.get()) {
            throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
        }
        ArrayList arrayList = new ArrayList();
        for (ClasspathElement classpathElement : this.classpathOrder) {
            if (classpathElement instanceof ClasspathElementModule) {
                arrayList.add(((ClasspathElementModule) classpathElement).getModuleRef());
            }
        }
        return arrayList;
    }

    public final ModulePathInfo getModulePathInfo() {
        this.scanSpec.modulePathInfo.getRuntimeInfo(this.reflectionUtils);
        return this.scanSpec.modulePathInfo;
    }

    public final ResourceList getAllResources() {
        if (this.allAcceptedResourcesCached == null) {
            ResourceList resourceList = new ResourceList();
            Iterator<ClasspathElement> it = this.classpathOrder.iterator();
            while (it.hasNext()) {
                resourceList.addAll(it.next().acceptedResources);
            }
            this.allAcceptedResourcesCached = resourceList;
        }
        return this.allAcceptedResourcesCached;
    }

    public final Map<String, ResourceList> getAllResourcesAsMap() {
        if (this.pathToAcceptedResourcesCached == null) {
            HashMap hashMap = new HashMap();
            Iterator it = getAllResources().iterator();
            while (it.hasNext()) {
                Resource resource = (Resource) it.next();
                ResourceList resourceList = (ResourceList) hashMap.get(resource.getPath());
                ResourceList resourceList2 = resourceList;
                if (resourceList == null) {
                    String path = resource.getPath();
                    ResourceList resourceList3 = new ResourceList();
                    resourceList2 = resourceList3;
                    hashMap.put(path, resourceList3);
                }
                resourceList2.add(resource);
            }
            this.pathToAcceptedResourcesCached = hashMap;
        }
        return this.pathToAcceptedResourcesCached;
    }

    public final ResourceList getResourcesWithPath(String str) {
        if (this.closed.get()) {
            throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
        }
        String sanitizeEntryPath = FileUtils.sanitizeEntryPath(str, true, true);
        ResourceList resourceList = null;
        if (this.getResourcesWithPathCallCount.incrementAndGet() > 3) {
            resourceList = getAllResourcesAsMap().get(sanitizeEntryPath);
        } else {
            Iterator<ClasspathElement> it = this.classpathOrder.iterator();
            while (it.hasNext()) {
                for (Resource resource : it.next().acceptedResources) {
                    if (resource.getPath().equals(sanitizeEntryPath)) {
                        if (resourceList == null) {
                            resourceList = new ResourceList();
                        }
                        resourceList.add(resource);
                    }
                }
            }
        }
        return resourceList == null ? ResourceList.EMPTY_LIST : resourceList;
    }

    public final ResourceList getResourcesWithPathIgnoringAccept(String str) {
        if (this.closed.get()) {
            throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
        }
        String sanitizeEntryPath = FileUtils.sanitizeEntryPath(str, true, true);
        ResourceList resourceList = new ResourceList();
        Iterator<ClasspathElement> it = this.classpathOrder.iterator();
        while (it.hasNext()) {
            Resource resource = it.next().getResource(sanitizeEntryPath);
            if (resource != null) {
                resourceList.add(resource);
            }
        }
        return resourceList;
    }

    @Deprecated
    public final ResourceList getResourcesWithPathIgnoringWhitelist(String str) {
        return getResourcesWithPathIgnoringAccept(str);
    }

    public final ResourceList getResourcesWithLeafName(String str) {
        if (this.closed.get()) {
            throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
        }
        ResourceList allResources = getAllResources();
        if (allResources.isEmpty()) {
            return ResourceList.EMPTY_LIST;
        }
        ResourceList resourceList = new ResourceList();
        Iterator it = allResources.iterator();
        while (it.hasNext()) {
            Resource resource = (Resource) it.next();
            String path = resource.getPath();
            if (path.substring(path.lastIndexOf(47) + 1).equals(str)) {
                resourceList.add(resource);
            }
        }
        return resourceList;
    }

    public final ResourceList getResourcesWithExtension(String str) {
        String str2;
        if (this.closed.get()) {
            throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
        }
        ResourceList allResources = getAllResources();
        if (allResources.isEmpty()) {
            return ResourceList.EMPTY_LIST;
        }
        String str3 = str;
        while (true) {
            str2 = str3;
            if (!str2.startsWith(".")) {
                break;
            }
            str3 = str2.substring(1);
        }
        ResourceList resourceList = new ResourceList();
        Iterator it = allResources.iterator();
        while (it.hasNext()) {
            Resource resource = (Resource) it.next();
            String path = resource.getPath();
            int lastIndexOf = path.lastIndexOf(47);
            int lastIndexOf2 = path.lastIndexOf(46);
            if (lastIndexOf2 > lastIndexOf && path.substring(lastIndexOf2 + 1).equalsIgnoreCase(str2)) {
                resourceList.add(resource);
            }
        }
        return resourceList;
    }

    public final ResourceList getResourcesMatchingPattern(Pattern pattern) {
        if (this.closed.get()) {
            throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
        }
        ResourceList allResources = getAllResources();
        if (allResources.isEmpty()) {
            return ResourceList.EMPTY_LIST;
        }
        ResourceList resourceList = new ResourceList();
        Iterator it = allResources.iterator();
        while (it.hasNext()) {
            Resource resource = (Resource) it.next();
            if (pattern.matcher(resource.getPath()).matches()) {
                resourceList.add(resource);
            }
        }
        return resourceList;
    }

    public final ResourceList getResourcesMatchingWildcard(String str) {
        if (this.closed.get()) {
            throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
        }
        return getResourcesMatchingPattern(AcceptReject.globToPattern(str, false));
    }

    public final ModuleInfo getModuleInfo(String str) {
        if (this.closed.get()) {
            throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
        }
        if (!this.scanSpec.enableClassInfo) {
            throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo() before #scan()");
        }
        return this.moduleNameToModuleInfo.get(str);
    }

    public final ModuleInfoList getModuleInfo() {
        if (this.closed.get()) {
            throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
        }
        if (!this.scanSpec.enableClassInfo) {
            throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo() before #scan()");
        }
        return new ModuleInfoList(this.moduleNameToModuleInfo.values());
    }

    public final PackageInfo getPackageInfo(String str) {
        if (this.closed.get()) {
            throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
        }
        if (!this.scanSpec.enableClassInfo) {
            throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo() before #scan()");
        }
        return this.packageNameToPackageInfo.get(str);
    }

    public final PackageInfoList getPackageInfo() {
        if (this.closed.get()) {
            throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
        }
        if (!this.scanSpec.enableClassInfo) {
            throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo() before #scan()");
        }
        return new PackageInfoList(this.packageNameToPackageInfo.values());
    }

    public final Map<ClassInfo, ClassInfoList> getClassDependencyMap() {
        HashMap hashMap = new HashMap();
        Iterator it = getAllClasses().iterator();
        while (it.hasNext()) {
            ClassInfo classInfo = (ClassInfo) it.next();
            hashMap.put(classInfo, classInfo.getClassDependencies());
        }
        return hashMap;
    }

    public final Map<ClassInfo, ClassInfoList> getReverseClassDependencyMap() {
        HashMap hashMap = new HashMap();
        Iterator it = getAllClasses().iterator();
        while (it.hasNext()) {
            ClassInfo classInfo = (ClassInfo) it.next();
            Iterator it2 = classInfo.getClassDependencies().iterator();
            while (it2.hasNext()) {
                ClassInfo classInfo2 = (ClassInfo) it2.next();
                Set set = (Set) hashMap.get(classInfo2);
                Set set2 = set;
                if (set == null) {
                    HashSet hashSet = new HashSet();
                    set2 = hashSet;
                    hashMap.put(classInfo2, hashSet);
                }
                set2.add(classInfo);
            }
        }
        HashMap hashMap2 = new HashMap();
        for (Map.Entry entry : hashMap.entrySet()) {
            hashMap2.put(entry.getKey(), new ClassInfoList((Set<ClassInfo>) entry.getValue(), true));
        }
        return hashMap2;
    }

    public final ClassInfo getClassInfo(String str) {
        if (this.closed.get()) {
            throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
        }
        if (!this.scanSpec.enableClassInfo) {
            throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo() before #scan()");
        }
        return this.classNameToClassInfo.get(str);
    }

    public final ClassInfoList getAllClasses() {
        if (this.closed.get()) {
            throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
        }
        if (!this.scanSpec.enableClassInfo) {
            throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo() before #scan()");
        }
        return ClassInfo.getAllClasses(this.classNameToClassInfo.values(), this.scanSpec);
    }

    public final ClassInfoList getAllEnums() {
        if (this.closed.get()) {
            throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
        }
        if (!this.scanSpec.enableClassInfo) {
            throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo() before #scan()");
        }
        return ClassInfo.getAllEnums(this.classNameToClassInfo.values(), this.scanSpec);
    }

    public final ClassInfoList getAllRecords() {
        if (this.closed.get()) {
            throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
        }
        if (!this.scanSpec.enableClassInfo) {
            throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo() before #scan()");
        }
        return ClassInfo.getAllRecords(this.classNameToClassInfo.values(), this.scanSpec);
    }

    public final Map<String, ClassInfo> getAllClassesAsMap() {
        if (this.closed.get()) {
            throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
        }
        if (!this.scanSpec.enableClassInfo) {
            throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo() before #scan()");
        }
        return this.classNameToClassInfo;
    }

    public final ClassInfoList getAllStandardClasses() {
        if (this.closed.get()) {
            throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
        }
        if (!this.scanSpec.enableClassInfo) {
            throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo() before #scan()");
        }
        return ClassInfo.getAllStandardClasses(this.classNameToClassInfo.values(), this.scanSpec);
    }

    public final ClassInfoList getSubclasses(Class<?> cls) {
        return getSubclasses(cls.getName());
    }

    public final ClassInfoList getSubclasses(String str) {
        if (this.closed.get()) {
            throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
        }
        if (!this.scanSpec.enableClassInfo) {
            throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo() before #scan()");
        }
        if (str.equals("java.lang.Object")) {
            return getAllStandardClasses();
        }
        ClassInfo classInfo = this.classNameToClassInfo.get(str);
        return classInfo == null ? ClassInfoList.EMPTY_LIST : classInfo.getSubclasses();
    }

    public final ClassInfoList getSuperclasses(String str) {
        if (this.closed.get()) {
            throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
        }
        if (!this.scanSpec.enableClassInfo) {
            throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo() before #scan()");
        }
        ClassInfo classInfo = this.classNameToClassInfo.get(str);
        return classInfo == null ? ClassInfoList.EMPTY_LIST : classInfo.getSuperclasses();
    }

    public final ClassInfoList getSuperclasses(Class<?> cls) {
        return getSuperclasses(cls.getName());
    }

    public final ClassInfoList getClassesWithMethodAnnotation(Class<? extends Annotation> cls) {
        Assert.isAnnotation(cls);
        return getClassesWithMethodAnnotation(cls.getName());
    }

    public final ClassInfoList getClassesWithMethodAnnotation(String str) {
        if (this.closed.get()) {
            throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
        }
        if (!this.scanSpec.enableClassInfo || !this.scanSpec.enableMethodInfo || !this.scanSpec.enableAnnotationInfo) {
            throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo(), #enableMethodInfo(), and #enableAnnotationInfo() before #scan()");
        }
        ClassInfo classInfo = this.classNameToClassInfo.get(str);
        return classInfo == null ? ClassInfoList.EMPTY_LIST : classInfo.getClassesWithMethodAnnotation();
    }

    public final ClassInfoList getClassesWithMethodParameterAnnotation(Class<? extends Annotation> cls) {
        Assert.isAnnotation(cls);
        return getClassesWithMethodParameterAnnotation(cls.getName());
    }

    public final ClassInfoList getClassesWithMethodParameterAnnotation(String str) {
        if (this.closed.get()) {
            throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
        }
        if (!this.scanSpec.enableClassInfo || !this.scanSpec.enableMethodInfo || !this.scanSpec.enableAnnotationInfo) {
            throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo(), #enableMethodInfo(), and #enableAnnotationInfo() before #scan()");
        }
        ClassInfo classInfo = this.classNameToClassInfo.get(str);
        return classInfo == null ? ClassInfoList.EMPTY_LIST : classInfo.getClassesWithMethodParameterAnnotation();
    }

    public final ClassInfoList getClassesWithFieldAnnotation(Class<? extends Annotation> cls) {
        Assert.isAnnotation(cls);
        return getClassesWithFieldAnnotation(cls.getName());
    }

    public final ClassInfoList getClassesWithFieldAnnotation(String str) {
        if (this.closed.get()) {
            throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
        }
        if (!this.scanSpec.enableClassInfo || !this.scanSpec.enableFieldInfo || !this.scanSpec.enableAnnotationInfo) {
            throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo(), #enableFieldInfo(), and #enableAnnotationInfo() before #scan()");
        }
        ClassInfo classInfo = this.classNameToClassInfo.get(str);
        return classInfo == null ? ClassInfoList.EMPTY_LIST : classInfo.getClassesWithFieldAnnotation();
    }

    public final ClassInfoList getAllInterfaces() {
        if (this.closed.get()) {
            throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
        }
        if (!this.scanSpec.enableClassInfo) {
            throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo() before #scan()");
        }
        return ClassInfo.getAllImplementedInterfaceClasses(this.classNameToClassInfo.values(), this.scanSpec);
    }

    public final ClassInfoList getInterfaces(String str) {
        if (this.closed.get()) {
            throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
        }
        if (!this.scanSpec.enableClassInfo) {
            throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo() before #scan()");
        }
        ClassInfo classInfo = this.classNameToClassInfo.get(str);
        return classInfo == null ? ClassInfoList.EMPTY_LIST : classInfo.getInterfaces();
    }

    public final ClassInfoList getInterfaces(Class<?> cls) {
        return getInterfaces(cls.getName());
    }

    public final ClassInfoList getClassesImplementing(Class<?> cls) {
        Assert.isInterface(cls);
        return getClassesImplementing(cls.getName());
    }

    public final ClassInfoList getClassesImplementing(String str) {
        if (this.closed.get()) {
            throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
        }
        if (!this.scanSpec.enableClassInfo) {
            throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo() before #scan()");
        }
        ClassInfo classInfo = this.classNameToClassInfo.get(str);
        return classInfo == null ? ClassInfoList.EMPTY_LIST : classInfo.getClassesImplementing();
    }

    public final ClassInfoList getAllAnnotations() {
        if (this.closed.get()) {
            throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
        }
        if (!this.scanSpec.enableClassInfo || !this.scanSpec.enableAnnotationInfo) {
            throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo() and #enableAnnotationInfo() before #scan()");
        }
        return ClassInfo.getAllAnnotationClasses(this.classNameToClassInfo.values(), this.scanSpec);
    }

    public final ClassInfoList getAllInterfacesAndAnnotations() {
        if (this.closed.get()) {
            throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
        }
        if (!this.scanSpec.enableClassInfo || !this.scanSpec.enableAnnotationInfo) {
            throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo() and #enableAnnotationInfo() before #scan()");
        }
        return ClassInfo.getAllInterfacesOrAnnotationClasses(this.classNameToClassInfo.values(), this.scanSpec);
    }

    public final ClassInfoList getClassesWithAnnotation(Class<? extends Annotation> cls) {
        Assert.isAnnotation(cls);
        return getClassesWithAnnotation(cls.getName());
    }

    public final ClassInfoList getClassesWithAllAnnotations(Class<? extends Annotation>... clsArr) {
        ArrayList arrayList = new ArrayList();
        for (Class<? extends Annotation> cls : clsArr) {
            Assert.isAnnotation(cls);
            arrayList.add(cls.getName());
        }
        return getClassesWithAllAnnotations((String[]) arrayList.toArray(new String[0]));
    }

    public final ClassInfoList getClassesWithAnyAnnotation(Class<? extends Annotation>... clsArr) {
        ArrayList arrayList = new ArrayList();
        for (Class<? extends Annotation> cls : clsArr) {
            Assert.isAnnotation(cls);
            arrayList.add(cls.getName());
        }
        return getClassesWithAnyAnnotation((String[]) arrayList.toArray(new String[0]));
    }

    public final ClassInfoList getClassesWithAnnotation(String str) {
        if (this.closed.get()) {
            throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
        }
        if (!this.scanSpec.enableClassInfo || !this.scanSpec.enableAnnotationInfo) {
            throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo() and #enableAnnotationInfo() before #scan()");
        }
        ClassInfo classInfo = this.classNameToClassInfo.get(str);
        return classInfo == null ? ClassInfoList.EMPTY_LIST : classInfo.getClassesWithAnnotation();
    }

    public final ClassInfoList getClassesWithAllAnnotations(String... strArr) {
        ClassInfoList intersect;
        ClassInfoList classInfoList = null;
        for (String str : strArr) {
            ClassInfoList classesWithAnnotation = getClassesWithAnnotation(str);
            if (classInfoList == null) {
                intersect = classesWithAnnotation;
            } else {
                intersect = classInfoList.intersect(classesWithAnnotation);
            }
            classInfoList = intersect;
        }
        CollectionUtils.sortIfNotEmpty(classInfoList);
        return classInfoList == null ? ClassInfoList.EMPTY_LIST : classInfoList;
    }

    public final ClassInfoList getClassesWithAnyAnnotation(String... strArr) {
        ClassInfoList union;
        ClassInfoList classInfoList = null;
        for (String str : strArr) {
            ClassInfoList classesWithAnnotation = getClassesWithAnnotation(str);
            if (classInfoList == null) {
                union = classesWithAnnotation;
            } else {
                union = classInfoList.union(classesWithAnnotation);
            }
            classInfoList = union;
        }
        CollectionUtils.sortIfNotEmpty(classInfoList);
        return classInfoList == null ? ClassInfoList.EMPTY_LIST : classInfoList;
    }

    public final ClassInfoList getAnnotationsOnClass(String str) {
        if (this.closed.get()) {
            throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
        }
        if (!this.scanSpec.enableClassInfo || !this.scanSpec.enableAnnotationInfo) {
            throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo() and #enableAnnotationInfo() before #scan()");
        }
        ClassInfo classInfo = this.classNameToClassInfo.get(str);
        return classInfo == null ? ClassInfoList.EMPTY_LIST : classInfo.getAnnotations();
    }

    public final boolean classpathContentsModifiedSinceScan() {
        if (this.closed.get()) {
            throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
        }
        if (this.fileToLastModified == null) {
            return true;
        }
        for (Map.Entry<File, Long> entry : this.fileToLastModified.entrySet()) {
            if (entry.getKey().lastModified() != entry.getValue().longValue()) {
                return true;
            }
        }
        return false;
    }

    public final long classpathContentsLastModifiedTime() {
        if (this.closed.get()) {
            throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
        }
        long j = 0;
        if (this.fileToLastModified != null) {
            long currentTimeMillis = System.currentTimeMillis();
            Iterator<Long> it = this.fileToLastModified.values().iterator();
            while (it.hasNext()) {
                long longValue = it.next().longValue();
                if (longValue > j && longValue < currentTimeMillis) {
                    j = longValue;
                }
            }
        }
        return j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final ClassLoader[] getClassLoaderOrderRespectingParentDelegation() {
        return this.classpathFinder.getClassLoaderOrderRespectingParentDelegation();
    }

    public final Class<?> loadClass(String str, boolean z) {
        if (this.closed.get()) {
            throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
        }
        if (str == null || str.isEmpty()) {
            throw new NullPointerException("className cannot be null or empty");
        }
        try {
            return Class.forName(str, this.scanSpec.initializeLoadedClasses, this.classGraphClassLoader);
        } catch (ClassNotFoundException | LinkageError e) {
            if (z) {
                return null;
            }
            throw new IllegalArgumentException("Could not load class " + str + " : " + e, e);
        }
    }

    public final <T> Class<T> loadClass(String str, Class<T> cls, boolean z) {
        if (this.closed.get()) {
            throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
        }
        if (str == null || str.isEmpty()) {
            throw new NullPointerException("className cannot be null or empty");
        }
        if (cls == null) {
            throw new NullPointerException("superclassOrInterfaceType parameter cannot be null");
        }
        try {
            Class<T> cls2 = (Class<T>) Class.forName(str, this.scanSpec.initializeLoadedClasses, this.classGraphClassLoader);
            if (cls2 != null && !cls.isAssignableFrom(cls2)) {
                if (z) {
                    return null;
                }
                throw new IllegalArgumentException("Loaded class " + cls2.getName() + " cannot be cast to " + cls.getName());
            }
            return cls2;
        } catch (ClassNotFoundException | LinkageError e) {
            if (z) {
                return null;
            }
            throw new IllegalArgumentException("Could not load class " + str + " : " + e);
        }
    }

    public static ScanResult fromJSON(String str) {
        Matcher matcher = Pattern.compile("\\{[\\n\\r ]*\"format\"[ ]?:[ ]?\"([^\"]+)\"").matcher(str);
        if (!matcher.find()) {
            throw new IllegalArgumentException("JSON is not in correct format");
        }
        if (!CURRENT_SERIALIZATION_FORMAT.equals(matcher.group(1))) {
            throw new IllegalArgumentException("JSON was serialized in a different format from the format used by the current version of ClassGraph -- please serialize and deserialize your ScanResult using the same version of ClassGraph");
        }
        SerializationFormat serializationFormat = (SerializationFormat) JSONDeserializer.deserializeObject(SerializationFormat.class, str);
        if (serializationFormat == null || !serializationFormat.format.equals(CURRENT_SERIALIZATION_FORMAT)) {
            throw new IllegalArgumentException("JSON was serialized by newer version of ClassGraph");
        }
        ClassGraph classGraph = new ClassGraph();
        classGraph.scanSpec = serializationFormat.scanSpec;
        AutoCloseableExecutorService autoCloseableExecutorService = new AutoCloseableExecutorService(ClassGraph.DEFAULT_NUM_WORKER_THREADS);
        Throwable th = null;
        try {
            ScanResult classpathScanResult = classGraph.getClasspathScanResult(autoCloseableExecutorService);
            if (0 != 0) {
                try {
                    autoCloseableExecutorService.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            } else {
                autoCloseableExecutorService.close();
            }
            classpathScanResult.rawClasspathEltOrderStrs = serializationFormat.classpath;
            classpathScanResult.scanSpec = serializationFormat.scanSpec;
            classpathScanResult.classNameToClassInfo = new HashMap();
            if (serializationFormat.classInfo != null) {
                for (ClassInfo classInfo : serializationFormat.classInfo) {
                    classpathScanResult.classNameToClassInfo.put(classInfo.getName(), classInfo);
                    classInfo.setScanResult(classpathScanResult);
                }
            }
            classpathScanResult.moduleNameToModuleInfo = new HashMap();
            if (serializationFormat.moduleInfo != null) {
                for (ModuleInfo moduleInfo : serializationFormat.moduleInfo) {
                    classpathScanResult.moduleNameToModuleInfo.put(moduleInfo.getName(), moduleInfo);
                }
            }
            classpathScanResult.packageNameToPackageInfo = new HashMap();
            if (serializationFormat.packageInfo != null) {
                for (PackageInfo packageInfo : serializationFormat.packageInfo) {
                    classpathScanResult.packageNameToPackageInfo.put(packageInfo.getName(), packageInfo);
                }
            }
            classpathScanResult.indexResourcesAndClassInfo(null);
            classpathScanResult.isObtainedFromDeserialization = true;
            return classpathScanResult;
        } catch (Throwable th3) {
            if (0 != 0) {
                try {
                    autoCloseableExecutorService.close();
                } catch (Throwable th4) {
                    th.addSuppressed(th4);
                }
            } else {
                autoCloseableExecutorService.close();
            }
            throw th3;
        }
    }

    public final String toJSON(int i) {
        if (this.closed.get()) {
            throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
        }
        if (!this.scanSpec.enableClassInfo) {
            throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo() before #scan()");
        }
        ArrayList arrayList = new ArrayList(this.classNameToClassInfo.values());
        CollectionUtils.sortIfNotEmpty(arrayList);
        ArrayList arrayList2 = new ArrayList(this.packageNameToPackageInfo.values());
        CollectionUtils.sortIfNotEmpty(arrayList2);
        ArrayList arrayList3 = new ArrayList(this.moduleNameToModuleInfo.values());
        CollectionUtils.sortIfNotEmpty(arrayList3);
        return JSONSerializer.serializeObject(new SerializationFormat(CURRENT_SERIALIZATION_FORMAT, this.scanSpec, arrayList, arrayList2, arrayList3, this.rawClasspathEltOrderStrs), i, false);
    }

    public final String toJSON() {
        return toJSON(0);
    }

    public final boolean isObtainedFromDeserialization() {
        return this.isObtainedFromDeserialization;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (!this.closed.getAndSet(true)) {
            nonClosedWeakReferences.remove(this.weakReference);
            if (this.classpathOrder != null) {
                this.classpathOrder.clear();
                this.classpathOrder = null;
            }
            if (this.allAcceptedResourcesCached != null) {
                Iterator it = this.allAcceptedResourcesCached.iterator();
                while (it.hasNext()) {
                    ((Resource) it.next()).close();
                }
                this.allAcceptedResourcesCached.clear();
                this.allAcceptedResourcesCached = null;
            }
            if (this.pathToAcceptedResourcesCached != null) {
                this.pathToAcceptedResourcesCached.clear();
                this.pathToAcceptedResourcesCached = null;
            }
            this.classGraphClassLoader = null;
            if (this.packageNameToPackageInfo != null) {
                this.packageNameToPackageInfo.clear();
                this.packageNameToPackageInfo = null;
            }
            if (this.moduleNameToModuleInfo != null) {
                this.moduleNameToModuleInfo.clear();
                this.moduleNameToModuleInfo = null;
            }
            if (this.fileToLastModified != null) {
                this.fileToLastModified.clear();
                this.fileToLastModified = null;
            }
            if (this.nestedJarHandler != null) {
                this.nestedJarHandler.close(this.topLevelLog);
                this.nestedJarHandler = null;
            }
            this.classGraphClassLoader = null;
            this.classpathFinder = null;
            this.reflectionUtils = null;
            if (this.topLevelLog != null) {
                this.topLevelLog.flush();
            }
        }
    }

    public static void closeAll() {
        Iterator it = new ArrayList(nonClosedWeakReferences).iterator();
        while (it.hasNext()) {
            ScanResult scanResult = (ScanResult) ((WeakReference) it.next()).get();
            if (scanResult != null) {
                scanResult.close();
            }
        }
    }
}
