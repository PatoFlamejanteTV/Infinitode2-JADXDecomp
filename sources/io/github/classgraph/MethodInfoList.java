package io.github.classgraph;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import nonapi.io.github.classgraph.utils.LogNode;

/* loaded from: infinitode-2.jar:io/github/classgraph/MethodInfoList.class */
public class MethodInfoList extends InfoList<MethodInfo> {
    private static final long serialVersionUID = 1;
    static final MethodInfoList EMPTY_LIST;

    @FunctionalInterface
    /* loaded from: infinitode-2.jar:io/github/classgraph/MethodInfoList$MethodInfoFilter.class */
    public interface MethodInfoFilter {
        boolean accept(MethodInfo methodInfo);
    }

    static {
        MethodInfoList methodInfoList = new MethodInfoList();
        EMPTY_LIST = methodInfoList;
        methodInfoList.makeUnmodifiable();
    }

    public static MethodInfoList emptyList() {
        return EMPTY_LIST;
    }

    public MethodInfoList() {
    }

    public MethodInfoList(int i) {
        super(i);
    }

    public MethodInfoList(Collection<MethodInfo> collection) {
        super(collection);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void findReferencedClassInfo(Map<String, ClassInfo> map, Set<ClassInfo> set, LogNode logNode) {
        Iterator it = iterator();
        while (it.hasNext()) {
            ((MethodInfo) it.next()).findReferencedClassInfo(map, set, logNode);
        }
    }

    public Map<String, MethodInfoList> asMap() {
        HashMap hashMap = new HashMap();
        Iterator it = iterator();
        while (it.hasNext()) {
            MethodInfo methodInfo = (MethodInfo) it.next();
            String name = methodInfo.getName();
            MethodInfoList methodInfoList = (MethodInfoList) hashMap.get(name);
            MethodInfoList methodInfoList2 = methodInfoList;
            if (methodInfoList == null) {
                methodInfoList2 = new MethodInfoList(1);
                hashMap.put(name, methodInfoList2);
            }
            methodInfoList2.add(methodInfo);
        }
        return hashMap;
    }

    public boolean containsName(String str) {
        Iterator it = iterator();
        while (it.hasNext()) {
            if (((MethodInfo) it.next()).getName().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public MethodInfoList get(String str) {
        boolean z = false;
        Iterator it = iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            if (((MethodInfo) it.next()).getName().equals(str)) {
                z = true;
                break;
            }
        }
        if (!z) {
            return EMPTY_LIST;
        }
        MethodInfoList methodInfoList = new MethodInfoList(2);
        Iterator it2 = iterator();
        while (it2.hasNext()) {
            MethodInfo methodInfo = (MethodInfo) it2.next();
            if (methodInfo.getName().equals(str)) {
                methodInfoList.add(methodInfo);
            }
        }
        return methodInfoList;
    }

    public MethodInfo getSingleMethod(String str) {
        int i = 0;
        MethodInfo methodInfo = null;
        Iterator it = iterator();
        while (it.hasNext()) {
            MethodInfo methodInfo2 = (MethodInfo) it.next();
            if (methodInfo2.getName().equals(str)) {
                i++;
                methodInfo = methodInfo2;
            }
        }
        if (i == 0) {
            return null;
        }
        if (i == 1) {
            return methodInfo;
        }
        throw new IllegalArgumentException("There are multiple methods named \"" + str + "\" in class " + ((MethodInfo) iterator().next()).getClassInfo().getName());
    }

    public MethodInfoList filter(MethodInfoFilter methodInfoFilter) {
        MethodInfoList methodInfoList = new MethodInfoList();
        Iterator it = iterator();
        while (it.hasNext()) {
            MethodInfo methodInfo = (MethodInfo) it.next();
            if (methodInfoFilter.accept(methodInfo)) {
                methodInfoList.add(methodInfo);
            }
        }
        return methodInfoList;
    }
}
