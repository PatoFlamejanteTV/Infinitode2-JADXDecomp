package io.github.classgraph;

import io.github.classgraph.ClassInfo;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import nonapi.io.github.classgraph.utils.Assert;
import nonapi.io.github.classgraph.utils.CollectionUtils;
import nonapi.io.github.classgraph.utils.LogNode;

/* loaded from: infinitode-2.jar:io/github/classgraph/AnnotationInfoList.class */
public class AnnotationInfoList extends MappableInfoList<AnnotationInfo> {
    private AnnotationInfoList directlyRelatedAnnotations;
    private static final long serialVersionUID = 1;
    static final AnnotationInfoList EMPTY_LIST;

    @FunctionalInterface
    /* loaded from: infinitode-2.jar:io/github/classgraph/AnnotationInfoList$AnnotationInfoFilter.class */
    public interface AnnotationInfoFilter {
        boolean accept(AnnotationInfo annotationInfo);
    }

    static {
        AnnotationInfoList annotationInfoList = new AnnotationInfoList();
        EMPTY_LIST = annotationInfoList;
        annotationInfoList.makeUnmodifiable();
    }

    public static AnnotationInfoList emptyList() {
        return EMPTY_LIST;
    }

    public AnnotationInfoList() {
    }

    public AnnotationInfoList(int i) {
        super(i);
    }

    public AnnotationInfoList(AnnotationInfoList annotationInfoList) {
        this(annotationInfoList, annotationInfoList);
    }

    AnnotationInfoList(AnnotationInfoList annotationInfoList, AnnotationInfoList annotationInfoList2) {
        super(annotationInfoList);
        this.directlyRelatedAnnotations = annotationInfoList2;
    }

    public AnnotationInfoList filter(AnnotationInfoFilter annotationInfoFilter) {
        AnnotationInfoList annotationInfoList = new AnnotationInfoList();
        Iterator it = iterator();
        while (it.hasNext()) {
            AnnotationInfo annotationInfo = (AnnotationInfo) it.next();
            if (annotationInfoFilter.accept(annotationInfo)) {
                annotationInfoList.add(annotationInfo);
            }
        }
        return annotationInfoList;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void findReferencedClassInfo(Map<String, ClassInfo> map, Set<ClassInfo> set, LogNode logNode) {
        Iterator it = iterator();
        while (it.hasNext()) {
            ((AnnotationInfo) it.next()).findReferencedClassInfo(map, set, logNode);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void handleRepeatableAnnotations(Set<String> set, ClassInfo classInfo, ClassInfo.RelType relType, ClassInfo.RelType relType2, ClassInfo.RelType relType3) {
        AnnotationParameterValue annotationParameterValue;
        ClassInfo classInfo2;
        ArrayList arrayList = null;
        for (int size = size() - 1; size >= 0; size--) {
            AnnotationInfo annotationInfo = (AnnotationInfo) get(size);
            if (set.contains(annotationInfo.getName())) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(annotationInfo);
                remove(size);
            }
        }
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                AnnotationParameterValueList parameterValues = ((AnnotationInfo) it.next()).getParameterValues();
                if (!parameterValues.isEmpty() && (annotationParameterValue = parameterValues.get("value")) != null) {
                    Object value = annotationParameterValue.getValue();
                    if (value instanceof Object[]) {
                        for (Object obj : (Object[]) value) {
                            if (obj instanceof AnnotationInfo) {
                                AnnotationInfo annotationInfo2 = (AnnotationInfo) obj;
                                add(annotationInfo2);
                                if (relType != null && ((relType2 != null || relType3 != null) && (classInfo2 = annotationInfo2.getClassInfo()) != null)) {
                                    classInfo.addRelatedClass(relType, classInfo2);
                                    if (relType2 != null) {
                                        classInfo2.addRelatedClass(relType2, classInfo);
                                    }
                                    if (relType3 != null) {
                                        classInfo2.addRelatedClass(relType3, classInfo);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static void findMetaAnnotations(AnnotationInfo annotationInfo, AnnotationInfoList annotationInfoList, Set<ClassInfo> set) {
        ClassInfo classInfo = annotationInfo.getClassInfo();
        if (classInfo != null && classInfo.annotationInfo != null && set.add(classInfo)) {
            Iterator it = classInfo.annotationInfo.iterator();
            while (it.hasNext()) {
                AnnotationInfo annotationInfo2 = (AnnotationInfo) it.next();
                if (!annotationInfo2.getClassInfo().getName().startsWith("java.lang.annotation.")) {
                    annotationInfoList.add(annotationInfo2);
                    findMetaAnnotations(annotationInfo2, annotationInfoList, set);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AnnotationInfoList getIndirectAnnotations(AnnotationInfoList annotationInfoList, ClassInfo classInfo) {
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        AnnotationInfoList annotationInfoList2 = new AnnotationInfoList(annotationInfoList == null ? 2 : annotationInfoList.size());
        if (annotationInfoList != null) {
            Iterator it = annotationInfoList.iterator();
            while (it.hasNext()) {
                AnnotationInfo annotationInfo = (AnnotationInfo) it.next();
                hashSet.add(annotationInfo.getClassInfo());
                annotationInfoList2.add(annotationInfo);
                findMetaAnnotations(annotationInfo, annotationInfoList2, hashSet2);
            }
        }
        if (classInfo != null) {
            Iterator it2 = classInfo.getSuperclasses().iterator();
            while (it2.hasNext()) {
                ClassInfo classInfo2 = (ClassInfo) it2.next();
                if (classInfo2.annotationInfo != null) {
                    Iterator it3 = classInfo2.annotationInfo.iterator();
                    while (it3.hasNext()) {
                        AnnotationInfo annotationInfo2 = (AnnotationInfo) it3.next();
                        if (annotationInfo2.isInherited() && hashSet.add(annotationInfo2.getClassInfo())) {
                            annotationInfoList2.add(annotationInfo2);
                            AnnotationInfoList annotationInfoList3 = new AnnotationInfoList(2);
                            findMetaAnnotations(annotationInfo2, annotationInfoList3, hashSet2);
                            Iterator it4 = annotationInfoList3.iterator();
                            while (it4.hasNext()) {
                                AnnotationInfo annotationInfo3 = (AnnotationInfo) it4.next();
                                if (annotationInfo3.isInherited()) {
                                    annotationInfoList2.add(annotationInfo3);
                                }
                            }
                        }
                    }
                }
            }
        }
        AnnotationInfoList annotationInfoList4 = annotationInfoList == null ? EMPTY_LIST : new AnnotationInfoList(annotationInfoList);
        CollectionUtils.sortIfNotEmpty(annotationInfoList4);
        AnnotationInfoList annotationInfoList5 = new AnnotationInfoList(annotationInfoList2, annotationInfoList4);
        CollectionUtils.sortIfNotEmpty(annotationInfoList5);
        return annotationInfoList5;
    }

    public AnnotationInfoList directOnly() {
        return this.directlyRelatedAnnotations == null ? this : new AnnotationInfoList(this.directlyRelatedAnnotations, null);
    }

    public AnnotationInfoList getRepeatable(Class<? extends Annotation> cls) {
        Assert.isAnnotation(cls);
        return getRepeatable(cls.getName());
    }

    public AnnotationInfoList getRepeatable(String str) {
        boolean z = false;
        Iterator it = iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            if (((AnnotationInfo) it.next()).getName().equals(str)) {
                z = true;
                break;
            }
        }
        if (!z) {
            return EMPTY_LIST;
        }
        AnnotationInfoList annotationInfoList = new AnnotationInfoList(size());
        Iterator it2 = iterator();
        while (it2.hasNext()) {
            AnnotationInfo annotationInfo = (AnnotationInfo) it2.next();
            if (annotationInfo.getName().equals(str)) {
                annotationInfoList.add(annotationInfo);
            }
        }
        return annotationInfoList;
    }

    @Override // io.github.classgraph.InfoList, io.github.classgraph.PotentiallyUnmodifiableList, java.util.ArrayList, java.util.AbstractList, java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AnnotationInfoList)) {
            return false;
        }
        AnnotationInfoList annotationInfoList = (AnnotationInfoList) obj;
        if ((this.directlyRelatedAnnotations == null) != (annotationInfoList.directlyRelatedAnnotations == null)) {
            return false;
        }
        if (this.directlyRelatedAnnotations == null) {
            return super.equals(annotationInfoList);
        }
        return super.equals(annotationInfoList) && this.directlyRelatedAnnotations.equals(annotationInfoList.directlyRelatedAnnotations);
    }

    @Override // io.github.classgraph.InfoList, io.github.classgraph.PotentiallyUnmodifiableList, java.util.ArrayList, java.util.AbstractList, java.util.Collection, java.util.List
    public int hashCode() {
        return super.hashCode() ^ (this.directlyRelatedAnnotations == null ? 0 : this.directlyRelatedAnnotations.hashCode());
    }
}
