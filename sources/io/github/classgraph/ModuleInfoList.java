package io.github.classgraph;

import java.util.Collection;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:io/github/classgraph/ModuleInfoList.class */
public class ModuleInfoList extends MappableInfoList<ModuleInfo> {
    private static final long serialVersionUID = 1;

    @FunctionalInterface
    /* loaded from: infinitode-2.jar:io/github/classgraph/ModuleInfoList$ModuleInfoFilter.class */
    public interface ModuleInfoFilter {
        boolean accept(ModuleInfo moduleInfo);
    }

    ModuleInfoList() {
    }

    ModuleInfoList(int i) {
        super(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ModuleInfoList(Collection<ModuleInfo> collection) {
        super(collection);
    }

    public ModuleInfoList filter(ModuleInfoFilter moduleInfoFilter) {
        ModuleInfoList moduleInfoList = new ModuleInfoList();
        Iterator it = iterator();
        while (it.hasNext()) {
            ModuleInfo moduleInfo = (ModuleInfo) it.next();
            if (moduleInfoFilter.accept(moduleInfo)) {
                moduleInfoList.add(moduleInfo);
            }
        }
        return moduleInfoList;
    }
}
