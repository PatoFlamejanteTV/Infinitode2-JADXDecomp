package io.github.classgraph;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import nonapi.io.github.classgraph.utils.LogNode;

/* loaded from: infinitode-2.jar:io/github/classgraph/AnnotationParameterValueList.class */
public class AnnotationParameterValueList extends MappableInfoList<AnnotationParameterValue> {
    private static final long serialVersionUID = 1;
    static final AnnotationParameterValueList EMPTY_LIST;

    static {
        AnnotationParameterValueList annotationParameterValueList = new AnnotationParameterValueList();
        EMPTY_LIST = annotationParameterValueList;
        annotationParameterValueList.makeUnmodifiable();
    }

    public static AnnotationParameterValueList emptyList() {
        return EMPTY_LIST;
    }

    public AnnotationParameterValueList() {
    }

    public AnnotationParameterValueList(int i) {
        super(i);
    }

    public AnnotationParameterValueList(Collection<AnnotationParameterValue> collection) {
        super(collection);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void findReferencedClassInfo(Map<String, ClassInfo> map, Set<ClassInfo> set, LogNode logNode) {
        Iterator it = iterator();
        while (it.hasNext()) {
            ((AnnotationParameterValue) it.next()).findReferencedClassInfo(map, set, logNode);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void convertWrapperArraysToPrimitiveArrays(ClassInfo classInfo) {
        Iterator it = iterator();
        while (it.hasNext()) {
            ((AnnotationParameterValue) it.next()).convertWrapperArraysToPrimitiveArrays(classInfo);
        }
    }

    public Object getValue(String str) {
        AnnotationParameterValue annotationParameterValue = get(str);
        if (annotationParameterValue == null) {
            return null;
        }
        return annotationParameterValue.getValue();
    }
}
