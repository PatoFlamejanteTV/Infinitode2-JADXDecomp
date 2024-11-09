package io.github.classgraph;

import java.lang.annotation.Annotation;
import java.net.URI;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import nonapi.io.github.classgraph.utils.Assert;
import nonapi.io.github.classgraph.utils.CollectionUtils;

/* loaded from: infinitode-2.jar:io/github/classgraph/ModuleInfo.class */
public class ModuleInfo implements HasName, Comparable<ModuleInfo> {
    private String name;
    private transient ClasspathElement classpathElement;
    private transient ModuleRef moduleRef;
    private transient URI locationURI;
    private Set<AnnotationInfo> annotationInfoSet;
    private AnnotationInfoList annotationInfo;
    private Set<PackageInfo> packageInfoSet;
    private Set<ClassInfo> classInfoSet;

    ModuleInfo() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ModuleInfo(ModuleRef moduleRef, ClasspathElement classpathElement) {
        this.moduleRef = moduleRef;
        this.classpathElement = classpathElement;
        this.name = classpathElement.getModuleName();
    }

    @Override // io.github.classgraph.HasName
    public String getName() {
        return this.name;
    }

    public URI getLocation() {
        if (this.locationURI == null) {
            this.locationURI = this.moduleRef != null ? this.moduleRef.getLocation() : null;
            if (this.locationURI == null) {
                this.locationURI = this.classpathElement.getURI();
            }
        }
        return this.locationURI;
    }

    public ModuleRef getModuleRef() {
        return this.moduleRef;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addClassInfo(ClassInfo classInfo) {
        if (this.classInfoSet == null) {
            this.classInfoSet = new HashSet();
        }
        this.classInfoSet.add(classInfo);
    }

    public ClassInfo getClassInfo(String str) {
        for (ClassInfo classInfo : this.classInfoSet) {
            if (classInfo.getName().equals(str)) {
                return classInfo;
            }
        }
        return null;
    }

    public ClassInfoList getClassInfo() {
        return new ClassInfoList(this.classInfoSet, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addPackageInfo(PackageInfo packageInfo) {
        if (this.packageInfoSet == null) {
            this.packageInfoSet = new HashSet();
        }
        this.packageInfoSet.add(packageInfo);
    }

    public PackageInfo getPackageInfo(String str) {
        if (this.packageInfoSet == null) {
            return null;
        }
        for (PackageInfo packageInfo : this.packageInfoSet) {
            if (packageInfo.getName().equals(str)) {
                return packageInfo;
            }
        }
        return null;
    }

    public PackageInfoList getPackageInfo() {
        if (this.packageInfoSet == null) {
            return new PackageInfoList(1);
        }
        PackageInfoList packageInfoList = new PackageInfoList(this.packageInfoSet);
        CollectionUtils.sortIfNotEmpty(packageInfoList);
        return packageInfoList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addAnnotations(AnnotationInfoList annotationInfoList) {
        if (annotationInfoList != null && !annotationInfoList.isEmpty()) {
            if (this.annotationInfoSet == null) {
                this.annotationInfoSet = new LinkedHashSet();
            }
            this.annotationInfoSet.addAll(annotationInfoList);
        }
    }

    public AnnotationInfo getAnnotationInfo(Class<? extends Annotation> cls) {
        Assert.isAnnotation(cls);
        return getAnnotationInfo(cls.getName());
    }

    public AnnotationInfo getAnnotationInfo(String str) {
        return getAnnotationInfo().get(str);
    }

    public AnnotationInfoList getAnnotationInfo() {
        if (this.annotationInfo == null) {
            if (this.annotationInfoSet == null) {
                this.annotationInfo = AnnotationInfoList.EMPTY_LIST;
            } else {
                this.annotationInfo = new AnnotationInfoList();
                this.annotationInfo.addAll(this.annotationInfoSet);
            }
        }
        return this.annotationInfo;
    }

    public boolean hasAnnotation(Class<? extends Annotation> cls) {
        Assert.isAnnotation(cls);
        return hasAnnotation(cls.getName());
    }

    public boolean hasAnnotation(String str) {
        return getAnnotationInfo().containsName(str);
    }

    @Override // java.lang.Comparable
    public int compareTo(ModuleInfo moduleInfo) {
        int compareTo = this.name.compareTo(moduleInfo.name);
        if (compareTo != 0) {
            return compareTo;
        }
        URI location = getLocation();
        URI location2 = moduleInfo.getLocation();
        if (location != null && location2 != null) {
            return location.compareTo(location2);
        }
        return 0;
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof ModuleInfo) && compareTo((ModuleInfo) obj) == 0;
    }

    public String toString() {
        return this.name;
    }
}
