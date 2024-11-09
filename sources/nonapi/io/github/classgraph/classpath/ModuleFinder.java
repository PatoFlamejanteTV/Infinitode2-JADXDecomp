package nonapi.io.github.classgraph.classpath;

import io.github.classgraph.ModuleRef;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import nonapi.io.github.classgraph.reflection.ReflectionUtils;
import nonapi.io.github.classgraph.scanspec.ScanSpec;
import nonapi.io.github.classgraph.utils.CollectionUtils;
import nonapi.io.github.classgraph.utils.LogNode;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/classpath/ModuleFinder.class */
public class ModuleFinder {
    private List<ModuleRef> systemModuleRefs;
    private List<ModuleRef> nonSystemModuleRefs;
    private boolean forceScanJavaClassPath;
    private final ReflectionUtils reflectionUtils;

    public List<ModuleRef> getSystemModuleRefs() {
        return this.systemModuleRefs;
    }

    public List<ModuleRef> getNonSystemModuleRefs() {
        return this.nonSystemModuleRefs;
    }

    public boolean forceScanJavaClassPath() {
        return this.forceScanJavaClassPath;
    }

    private void findLayerOrder(Object obj, Set<Object> set, Set<Object> set2, Deque<Object> deque) {
        if (set.add(obj)) {
            List list = (List) this.reflectionUtils.invokeMethod(true, obj, "parents");
            if (list != null) {
                set2.addAll(list);
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    findLayerOrder(it.next(), set, set2, deque);
                }
            }
            deque.push(obj);
        }
    }

    private List<ModuleRef> findModuleRefs(LinkedHashSet<Object> linkedHashSet, ScanSpec scanSpec, LogNode logNode) {
        ArrayList arrayList;
        Set set;
        if (linkedHashSet.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayDeque arrayDeque = new ArrayDeque();
        HashSet hashSet = new HashSet();
        Iterator<Object> it = linkedHashSet.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (next != null) {
                findLayerOrder(next, new HashSet(), hashSet, arrayDeque);
            }
        }
        if (scanSpec.addedModuleLayers != null) {
            for (Object obj : scanSpec.addedModuleLayers) {
                if (obj != null) {
                    findLayerOrder(obj, new HashSet(), hashSet, arrayDeque);
                }
            }
        }
        if (scanSpec.ignoreParentModuleLayers) {
            arrayList = new ArrayList();
            for (Object obj2 : arrayDeque) {
                if (!hashSet.contains(obj2)) {
                    arrayList.add(obj2);
                }
            }
        } else {
            arrayList = new ArrayList(arrayDeque);
        }
        HashSet hashSet2 = new HashSet();
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        for (Object obj3 : arrayList) {
            Object invokeMethod = this.reflectionUtils.invokeMethod(true, obj3, "configuration");
            if (invokeMethod != null && (set = (Set) this.reflectionUtils.invokeMethod(true, invokeMethod, "modules")) != null) {
                ArrayList arrayList2 = new ArrayList();
                Iterator it2 = set.iterator();
                while (it2.hasNext()) {
                    Object invokeMethod2 = this.reflectionUtils.invokeMethod(true, it2.next(), "reference");
                    if (invokeMethod2 != null && hashSet2.add(invokeMethod2)) {
                        try {
                            arrayList2.add(new ModuleRef(invokeMethod2, obj3, this.reflectionUtils));
                        } catch (IllegalArgumentException e) {
                            if (logNode != null) {
                                logNode.log("Exception while creating ModuleRef for module " + invokeMethod2, e);
                            }
                        }
                    }
                }
                CollectionUtils.sortIfNotEmpty(arrayList2);
                linkedHashSet2.addAll(arrayList2);
            }
        }
        return new ArrayList(linkedHashSet2);
    }

    private List<ModuleRef> findModuleRefsFromCallstack(Class<?>[] clsArr, ScanSpec scanSpec, boolean z, LogNode logNode) {
        LinkedHashSet<Object> linkedHashSet = new LinkedHashSet<>();
        if (clsArr != null) {
            for (Class<?> cls : clsArr) {
                Object invokeMethod = this.reflectionUtils.invokeMethod(false, cls, "getModule");
                if (invokeMethod != null) {
                    Object invokeMethod2 = this.reflectionUtils.invokeMethod(true, invokeMethod, "getLayer");
                    if (invokeMethod2 != null) {
                        linkedHashSet.add(invokeMethod2);
                    } else if (z) {
                        this.forceScanJavaClassPath = true;
                    }
                }
            }
        }
        Class<?> cls2 = null;
        try {
            cls2 = Class.forName("java.lang.ModuleLayer");
        } catch (ClassNotFoundException | LinkageError unused) {
        }
        if (cls2 != null) {
            Object invokeStaticMethod = this.reflectionUtils.invokeStaticMethod(false, cls2, "boot");
            if (invokeStaticMethod != null) {
                linkedHashSet.add(invokeStaticMethod);
            } else if (z) {
                this.forceScanJavaClassPath = true;
            }
        }
        return findModuleRefs(linkedHashSet, scanSpec, logNode);
    }

    public ModuleFinder(Class<?>[] clsArr, ScanSpec scanSpec, boolean z, boolean z2, ReflectionUtils reflectionUtils, LogNode logNode) {
        this.reflectionUtils = reflectionUtils;
        List<ModuleRef> list = null;
        if (scanSpec.overrideModuleLayers == null) {
            if (clsArr != null && clsArr.length > 0) {
                list = findModuleRefsFromCallstack(clsArr, scanSpec, z, logNode);
            }
        } else {
            if (logNode != null) {
                LogNode log = logNode.log("Overriding module layers");
                Iterator<Object> it = scanSpec.overrideModuleLayers.iterator();
                while (it.hasNext()) {
                    log.log(it.next().toString());
                }
            }
            list = findModuleRefs(new LinkedHashSet<>(scanSpec.overrideModuleLayers), scanSpec, logNode);
        }
        if (list != null) {
            this.systemModuleRefs = new ArrayList();
            this.nonSystemModuleRefs = new ArrayList();
            for (ModuleRef moduleRef : list) {
                if (moduleRef != null) {
                    boolean isSystemModule = moduleRef.isSystemModule();
                    if (isSystemModule && z2) {
                        this.systemModuleRefs.add(moduleRef);
                    } else if (!isSystemModule && z) {
                        this.nonSystemModuleRefs.add(moduleRef);
                    }
                }
            }
        }
        if (logNode != null) {
            if (z2) {
                LogNode log2 = logNode.log("System modules found:");
                if (this.systemModuleRefs != null && !this.systemModuleRefs.isEmpty()) {
                    Iterator<ModuleRef> it2 = this.systemModuleRefs.iterator();
                    while (it2.hasNext()) {
                        log2.log(it2.next().toString());
                    }
                } else {
                    log2.log("[None]");
                }
            } else {
                logNode.log("Scanning of system modules is not enabled");
            }
            if (z) {
                LogNode log3 = logNode.log("Non-system modules found:");
                if (this.nonSystemModuleRefs != null && !this.nonSystemModuleRefs.isEmpty()) {
                    Iterator<ModuleRef> it3 = this.nonSystemModuleRefs.iterator();
                    while (it3.hasNext()) {
                        log3.log(it3.next().toString());
                    }
                    return;
                }
                log3.log("[None]");
                return;
            }
            logNode.log("Scanning of non-system modules is not enabled");
        }
    }
}
