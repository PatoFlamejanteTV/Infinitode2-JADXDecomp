package io.github.classgraph;

import io.github.classgraph.ClassInfo;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import nonapi.io.github.classgraph.scanspec.ScanSpec;
import nonapi.io.github.classgraph.utils.CollectionUtils;

/* loaded from: infinitode-2.jar:io/github/classgraph/ClassInfoList.class */
public class ClassInfoList extends MappableInfoList<ClassInfo> {
    private final transient Set<ClassInfo> directlyRelatedClasses;
    private final boolean sortByName;
    private static final long serialVersionUID = 1;
    static final ClassInfoList EMPTY_LIST;

    @FunctionalInterface
    /* loaded from: infinitode-2.jar:io/github/classgraph/ClassInfoList$ClassInfoFilter.class */
    public interface ClassInfoFilter {
        boolean accept(ClassInfo classInfo);
    }

    static {
        ClassInfoList classInfoList = new ClassInfoList();
        EMPTY_LIST = classInfoList;
        classInfoList.makeUnmodifiable();
    }

    public static ClassInfoList emptyList() {
        return EMPTY_LIST;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClassInfoList(Set<ClassInfo> set, Set<ClassInfo> set2, boolean z) {
        super(set);
        this.sortByName = z;
        if (z) {
            CollectionUtils.sortIfNotEmpty(this);
        }
        this.directlyRelatedClasses = set2 == null ? set : set2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClassInfoList(ClassInfo.ReachableAndDirectlyRelatedClasses reachableAndDirectlyRelatedClasses, boolean z) {
        this(reachableAndDirectlyRelatedClasses.reachableClasses, reachableAndDirectlyRelatedClasses.directlyRelatedClasses, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClassInfoList(Set<ClassInfo> set, boolean z) {
        this(set, null, z);
    }

    public ClassInfoList() {
        super(1);
        this.sortByName = false;
        this.directlyRelatedClasses = new HashSet(2);
    }

    public ClassInfoList(int i) {
        super(i);
        this.sortByName = false;
        this.directlyRelatedClasses = new HashSet(2);
    }

    public ClassInfoList(Collection<ClassInfo> collection) {
        this(collection instanceof Set ? (Set) collection : new HashSet(collection), null, true);
    }

    public <T> List<Class<T>> loadClasses(Class<T> cls, boolean z) {
        if (isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = iterator();
        while (it.hasNext()) {
            Class<T> loadClass = ((ClassInfo) it.next()).loadClass(cls, z);
            if (loadClass != null) {
                arrayList.add(loadClass);
            }
        }
        return arrayList.isEmpty() ? Collections.emptyList() : arrayList;
    }

    public <T> List<Class<T>> loadClasses(Class<T> cls) {
        return loadClasses(cls, false);
    }

    public List<Class<?>> loadClasses(boolean z) {
        if (isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = iterator();
        while (it.hasNext()) {
            Class<?> loadClass = ((ClassInfo) it.next()).loadClass(z);
            if (loadClass != null) {
                arrayList.add(loadClass);
            }
        }
        return arrayList.isEmpty() ? Collections.emptyList() : arrayList;
    }

    public List<Class<?>> loadClasses() {
        return loadClasses(false);
    }

    public ClassInfoList directOnly() {
        return new ClassInfoList(this.directlyRelatedClasses, this.directlyRelatedClasses, this.sortByName);
    }

    public ClassInfoList union(ClassInfoList... classInfoListArr) {
        LinkedHashSet linkedHashSet = new LinkedHashSet(this);
        LinkedHashSet linkedHashSet2 = new LinkedHashSet(this.directlyRelatedClasses);
        for (ClassInfoList classInfoList : classInfoListArr) {
            linkedHashSet.addAll(classInfoList);
            linkedHashSet2.addAll(classInfoList.directlyRelatedClasses);
        }
        return new ClassInfoList(linkedHashSet, linkedHashSet2, this.sortByName);
    }

    public ClassInfoList intersect(ClassInfoList... classInfoListArr) {
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.add(this);
        boolean z = false;
        for (ClassInfoList classInfoList : classInfoListArr) {
            if (classInfoList.sortByName) {
                arrayDeque.add(classInfoList);
            } else if (!z) {
                z = true;
                arrayDeque.push(classInfoList);
            } else {
                arrayDeque.add(classInfoList);
            }
        }
        ClassInfoList classInfoList2 = (ClassInfoList) arrayDeque.remove();
        LinkedHashSet linkedHashSet = new LinkedHashSet(classInfoList2);
        while (!arrayDeque.isEmpty()) {
            linkedHashSet.retainAll((Collection) arrayDeque.remove());
        }
        LinkedHashSet linkedHashSet2 = new LinkedHashSet(this.directlyRelatedClasses);
        for (ClassInfoList classInfoList3 : classInfoListArr) {
            linkedHashSet2.retainAll(classInfoList3.directlyRelatedClasses);
        }
        return new ClassInfoList(linkedHashSet, linkedHashSet2, classInfoList2.sortByName);
    }

    public ClassInfoList exclude(ClassInfoList classInfoList) {
        LinkedHashSet linkedHashSet = new LinkedHashSet(this);
        LinkedHashSet linkedHashSet2 = new LinkedHashSet(this.directlyRelatedClasses);
        linkedHashSet.removeAll(classInfoList);
        linkedHashSet2.removeAll(classInfoList.directlyRelatedClasses);
        return new ClassInfoList(linkedHashSet, linkedHashSet2, this.sortByName);
    }

    public ClassInfoList filter(ClassInfoFilter classInfoFilter) {
        LinkedHashSet linkedHashSet = new LinkedHashSet(size());
        LinkedHashSet linkedHashSet2 = new LinkedHashSet(this.directlyRelatedClasses.size());
        Iterator it = iterator();
        while (it.hasNext()) {
            ClassInfo classInfo = (ClassInfo) it.next();
            if (classInfoFilter.accept(classInfo)) {
                linkedHashSet.add(classInfo);
                if (this.directlyRelatedClasses.contains(classInfo)) {
                    linkedHashSet2.add(classInfo);
                }
            }
        }
        return new ClassInfoList(linkedHashSet, linkedHashSet2, this.sortByName);
    }

    public ClassInfoList getStandardClasses() {
        return filter(new ClassInfoFilter() { // from class: io.github.classgraph.ClassInfoList.1
            @Override // io.github.classgraph.ClassInfoList.ClassInfoFilter
            public boolean accept(ClassInfo classInfo) {
                return classInfo.isStandardClass();
            }
        });
    }

    public ClassInfoList getInterfaces() {
        return filter(new ClassInfoFilter() { // from class: io.github.classgraph.ClassInfoList.2
            @Override // io.github.classgraph.ClassInfoList.ClassInfoFilter
            public boolean accept(ClassInfo classInfo) {
                return classInfo.isInterface();
            }
        });
    }

    public ClassInfoList getInterfacesAndAnnotations() {
        return filter(new ClassInfoFilter() { // from class: io.github.classgraph.ClassInfoList.3
            @Override // io.github.classgraph.ClassInfoList.ClassInfoFilter
            public boolean accept(ClassInfo classInfo) {
                return classInfo.isInterfaceOrAnnotation();
            }
        });
    }

    public ClassInfoList getImplementedInterfaces() {
        return filter(new ClassInfoFilter() { // from class: io.github.classgraph.ClassInfoList.4
            @Override // io.github.classgraph.ClassInfoList.ClassInfoFilter
            public boolean accept(ClassInfo classInfo) {
                return classInfo.isImplementedInterface();
            }
        });
    }

    public ClassInfoList getAnnotations() {
        return filter(new ClassInfoFilter() { // from class: io.github.classgraph.ClassInfoList.5
            @Override // io.github.classgraph.ClassInfoList.ClassInfoFilter
            public boolean accept(ClassInfo classInfo) {
                return classInfo.isAnnotation();
            }
        });
    }

    public ClassInfoList getEnums() {
        return filter(new ClassInfoFilter() { // from class: io.github.classgraph.ClassInfoList.6
            @Override // io.github.classgraph.ClassInfoList.ClassInfoFilter
            public boolean accept(ClassInfo classInfo) {
                return classInfo.isEnum();
            }
        });
    }

    public ClassInfoList getRecords() {
        return filter(new ClassInfoFilter() { // from class: io.github.classgraph.ClassInfoList.7
            @Override // io.github.classgraph.ClassInfoList.ClassInfoFilter
            public boolean accept(ClassInfo classInfo) {
                return classInfo.isRecord();
            }
        });
    }

    public ClassInfoList getAssignableTo(ClassInfo classInfo) {
        if (classInfo == null) {
            throw new IllegalArgumentException("assignableToClass parameter cannot be null");
        }
        final HashSet hashSet = new HashSet();
        if (classInfo.isStandardClass()) {
            hashSet.addAll(classInfo.getSubclasses());
        } else if (classInfo.isInterfaceOrAnnotation()) {
            hashSet.addAll(classInfo.getClassesImplementing());
        }
        hashSet.add(classInfo);
        return filter(new ClassInfoFilter() { // from class: io.github.classgraph.ClassInfoList.8
            @Override // io.github.classgraph.ClassInfoList.ClassInfoFilter
            public boolean accept(ClassInfo classInfo2) {
                return hashSet.contains(classInfo2);
            }
        });
    }

    public String generateGraphVizDotFileFromInterClassDependencies(float f, float f2, boolean z) {
        if (isEmpty()) {
            throw new IllegalArgumentException("List is empty");
        }
        if (!((ClassInfo) get(0)).scanResult.scanSpec.enableInterClassDependencies) {
            throw new IllegalArgumentException("Please call ClassGraph#enableInterClassDependencies() before #scan()");
        }
        return GraphvizDotfileGenerator.generateGraphVizDotFileFromInterClassDependencies(this, f, f2, z);
    }

    public String generateGraphVizDotFileFromInterClassDependencies(float f, float f2) {
        if (isEmpty()) {
            throw new IllegalArgumentException("List is empty");
        }
        ScanSpec scanSpec = ((ClassInfo) get(0)).scanResult.scanSpec;
        if (!scanSpec.enableInterClassDependencies) {
            throw new IllegalArgumentException("Please call ClassGraph#enableInterClassDependencies() before #scan()");
        }
        return GraphvizDotfileGenerator.generateGraphVizDotFileFromInterClassDependencies(this, f, f2, scanSpec.enableExternalClasses);
    }

    public String generateGraphVizDotFileFromInterClassDependencies() {
        if (isEmpty()) {
            throw new IllegalArgumentException("List is empty");
        }
        ScanSpec scanSpec = ((ClassInfo) get(0)).scanResult.scanSpec;
        if (!scanSpec.enableInterClassDependencies) {
            throw new IllegalArgumentException("Please call ClassGraph#enableInterClassDependencies() before #scan()");
        }
        return GraphvizDotfileGenerator.generateGraphVizDotFileFromInterClassDependencies(this, 10.5f, 8.0f, scanSpec.enableExternalClasses);
    }

    @Deprecated
    public String generateGraphVizDotFileFromClassDependencies() {
        return generateGraphVizDotFileFromInterClassDependencies();
    }

    public String generateGraphVizDotFile(float f, float f2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        if (isEmpty()) {
            throw new IllegalArgumentException("List is empty");
        }
        ScanSpec scanSpec = ((ClassInfo) get(0)).scanResult.scanSpec;
        if (!scanSpec.enableClassInfo) {
            throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo() before #scan()");
        }
        return GraphvizDotfileGenerator.generateGraphVizDotFile(this, f, f2, z, z2, z3, z4, z5, z6, scanSpec);
    }

    public String generateGraphVizDotFile(float f, float f2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        return generateGraphVizDotFile(f, f2, z, z2, z3, z4, z5, true);
    }

    public String generateGraphVizDotFile(float f, float f2) {
        return generateGraphVizDotFile(f, f2, true, true, true, true, true);
    }

    public String generateGraphVizDotFile() {
        return generateGraphVizDotFile(10.5f, 8.0f, true, true, true, true, true);
    }

    public void generateGraphVizDotFile(File file) {
        PrintWriter printWriter = new PrintWriter(file);
        Throwable th = null;
        try {
            printWriter.print(generateGraphVizDotFile());
            if (0 == 0) {
                printWriter.close();
                return;
            }
            try {
                printWriter.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        } catch (Throwable th3) {
            if (0 != 0) {
                try {
                    printWriter.close();
                } catch (Throwable th4) {
                    th.addSuppressed(th4);
                }
            } else {
                printWriter.close();
            }
            throw th3;
        }
    }

    @Override // io.github.classgraph.InfoList, io.github.classgraph.PotentiallyUnmodifiableList, java.util.ArrayList, java.util.AbstractList, java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ClassInfoList)) {
            return false;
        }
        ClassInfoList classInfoList = (ClassInfoList) obj;
        if ((this.directlyRelatedClasses == null) != (classInfoList.directlyRelatedClasses == null)) {
            return false;
        }
        if (this.directlyRelatedClasses == null) {
            return super.equals(classInfoList);
        }
        return super.equals(classInfoList) && this.directlyRelatedClasses.equals(classInfoList.directlyRelatedClasses);
    }

    @Override // io.github.classgraph.InfoList, io.github.classgraph.PotentiallyUnmodifiableList, java.util.ArrayList, java.util.AbstractList, java.util.Collection, java.util.List
    public int hashCode() {
        return super.hashCode() ^ (this.directlyRelatedClasses == null ? 0 : this.directlyRelatedClasses.hashCode());
    }
}
