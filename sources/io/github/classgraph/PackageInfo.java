package io.github.classgraph;

import java.lang.annotation.Annotation;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import nonapi.io.github.classgraph.scanspec.ScanSpec;
import nonapi.io.github.classgraph.utils.Assert;
import nonapi.io.github.classgraph.utils.CollectionUtils;

/* loaded from: infinitode-2.jar:io/github/classgraph/PackageInfo.class */
public class PackageInfo implements HasName, Comparable<PackageInfo> {
    private String name;
    private Set<AnnotationInfo> annotationInfoSet;
    private AnnotationInfoList annotationInfo;
    private PackageInfo parent;
    private Set<PackageInfo> children;
    private Map<String, ClassInfo> memberClassNameToClassInfo;

    PackageInfo() {
    }

    PackageInfo(String str) {
        this.name = str;
    }

    @Override // io.github.classgraph.HasName
    public String getName() {
        return this.name;
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

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addClassInfo(ClassInfo classInfo) {
        if (this.memberClassNameToClassInfo == null) {
            this.memberClassNameToClassInfo = new HashMap();
        }
        this.memberClassNameToClassInfo.put(classInfo.getName(), classInfo);
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

    public PackageInfo getParent() {
        return this.parent;
    }

    public PackageInfoList getChildren() {
        if (this.children == null) {
            return PackageInfoList.EMPTY_LIST;
        }
        PackageInfoList packageInfoList = new PackageInfoList(this.children);
        CollectionUtils.sortIfNotEmpty(packageInfoList, new Comparator<PackageInfo>() { // from class: io.github.classgraph.PackageInfo.1
            @Override // java.util.Comparator
            public int compare(PackageInfo packageInfo, PackageInfo packageInfo2) {
                return packageInfo.name.compareTo(packageInfo2.name);
            }
        });
        return packageInfoList;
    }

    public ClassInfo getClassInfo(String str) {
        if (this.memberClassNameToClassInfo == null) {
            return null;
        }
        return this.memberClassNameToClassInfo.get(str);
    }

    public ClassInfoList getClassInfo() {
        return this.memberClassNameToClassInfo == null ? ClassInfoList.EMPTY_LIST : new ClassInfoList((Set<ClassInfo>) new HashSet(this.memberClassNameToClassInfo.values()), true);
    }

    private void obtainClassInfoRecursive(Set<ClassInfo> set) {
        if (this.memberClassNameToClassInfo != null) {
            set.addAll(this.memberClassNameToClassInfo.values());
        }
        Iterator it = getChildren().iterator();
        while (it.hasNext()) {
            ((PackageInfo) it.next()).obtainClassInfoRecursive(set);
        }
    }

    public ClassInfoList getClassInfoRecursive() {
        HashSet hashSet = new HashSet();
        obtainClassInfoRecursive(hashSet);
        return new ClassInfoList((Set<ClassInfo>) hashSet, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getParentPackageName(String str) {
        if (str.isEmpty()) {
            return null;
        }
        int lastIndexOf = str.lastIndexOf(46);
        return lastIndexOf < 0 ? "" : str.substring(0, lastIndexOf);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static PackageInfo getOrCreatePackage(String str, Map<String, PackageInfo> map, ScanSpec scanSpec) {
        PackageInfo orCreatePackage;
        PackageInfo packageInfo = map.get(str);
        if (packageInfo != null) {
            return packageInfo;
        }
        PackageInfo packageInfo2 = new PackageInfo(str);
        map.put(str, packageInfo2);
        if (!str.isEmpty()) {
            String parentPackageName = getParentPackageName(packageInfo2.name);
            if ((scanSpec.packageAcceptReject.isAcceptedAndNotRejected(parentPackageName) || scanSpec.packagePrefixAcceptReject.isAcceptedAndNotRejected(parentPackageName)) && (orCreatePackage = getOrCreatePackage(parentPackageName, map, scanSpec)) != null) {
                if (orCreatePackage.children == null) {
                    orCreatePackage.children = new HashSet();
                }
                orCreatePackage.children.add(packageInfo2);
                packageInfo2.parent = orCreatePackage;
            }
        }
        return packageInfo2;
    }

    @Override // java.lang.Comparable
    public int compareTo(PackageInfo packageInfo) {
        return this.name.compareTo(packageInfo.name);
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PackageInfo)) {
            return false;
        }
        return this.name.equals(((PackageInfo) obj).name);
    }

    public String toString() {
        return this.name;
    }
}
