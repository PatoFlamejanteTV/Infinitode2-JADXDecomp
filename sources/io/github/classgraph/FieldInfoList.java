package io.github.classgraph;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import nonapi.io.github.classgraph.utils.LogNode;

/* loaded from: infinitode-2.jar:io/github/classgraph/FieldInfoList.class */
public class FieldInfoList extends MappableInfoList<FieldInfo> {
    private static final long serialVersionUID = 1;
    static final FieldInfoList EMPTY_LIST;

    @FunctionalInterface
    /* loaded from: infinitode-2.jar:io/github/classgraph/FieldInfoList$FieldInfoFilter.class */
    public interface FieldInfoFilter {
        boolean accept(FieldInfo fieldInfo);
    }

    static {
        FieldInfoList fieldInfoList = new FieldInfoList();
        EMPTY_LIST = fieldInfoList;
        fieldInfoList.makeUnmodifiable();
    }

    public static FieldInfoList emptyList() {
        return EMPTY_LIST;
    }

    public FieldInfoList() {
    }

    public FieldInfoList(int i) {
        super(i);
    }

    public FieldInfoList(Collection<FieldInfo> collection) {
        super(collection);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void findReferencedClassInfo(Map<String, ClassInfo> map, Set<ClassInfo> set, LogNode logNode) {
        Iterator it = iterator();
        while (it.hasNext()) {
            ((FieldInfo) it.next()).findReferencedClassInfo(map, set, logNode);
        }
    }

    public FieldInfoList filter(FieldInfoFilter fieldInfoFilter) {
        FieldInfoList fieldInfoList = new FieldInfoList();
        Iterator it = iterator();
        while (it.hasNext()) {
            FieldInfo fieldInfo = (FieldInfo) it.next();
            if (fieldInfoFilter.accept(fieldInfo)) {
                fieldInfoList.add(fieldInfo);
            }
        }
        return fieldInfoList;
    }
}
