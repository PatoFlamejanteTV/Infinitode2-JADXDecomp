package io.github.classgraph;

import io.github.classgraph.HasName;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: infinitode-2.jar:io/github/classgraph/MappableInfoList.class */
public class MappableInfoList<T extends HasName> extends InfoList<T> {
    private static final long serialVersionUID = 1;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MappableInfoList() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MappableInfoList(int i) {
        super(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MappableInfoList(Collection<T> collection) {
        super(collection);
    }

    public Map<String, T> asMap() {
        HashMap hashMap = new HashMap();
        Iterator it = iterator();
        while (it.hasNext()) {
            HasName hasName = (HasName) it.next();
            if (hasName != null) {
                hashMap.put(hasName.getName(), hasName);
            }
        }
        return hashMap;
    }

    public boolean containsName(String str) {
        Iterator it = iterator();
        while (it.hasNext()) {
            HasName hasName = (HasName) it.next();
            if (hasName != null && hasName.getName().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public T get(String str) {
        Iterator it = iterator();
        while (it.hasNext()) {
            T t = (T) it.next();
            if (t != null && t.getName().equals(str)) {
                return t;
            }
        }
        return null;
    }
}
