package nonapi.io.github.classgraph.classpath;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import nonapi.io.github.classgraph.classloaderhandler.ClassLoaderHandlerRegistry;
import nonapi.io.github.classgraph.reflection.ReflectionUtils;
import nonapi.io.github.classgraph.utils.LogNode;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/classpath/ClassLoaderOrder.class */
public class ClassLoaderOrder {
    public ReflectionUtils reflectionUtils;
    private final List<Map.Entry<ClassLoader, ClassLoaderHandlerRegistry.ClassLoaderHandlerRegistryEntry>> classLoaderOrder = new ArrayList();
    private final Set<ClassLoader> added = Collections.newSetFromMap(new IdentityHashMap());
    private final Set<ClassLoader> delegatedTo = Collections.newSetFromMap(new IdentityHashMap());
    private final Set<ClassLoader> allParentClassLoaders = Collections.newSetFromMap(new IdentityHashMap());
    private final Map<ClassLoader, ClassLoaderHandlerRegistry.ClassLoaderHandlerRegistryEntry> classLoaderToClassLoaderHandlerRegistryEntry = new IdentityHashMap();

    public ClassLoaderOrder(ReflectionUtils reflectionUtils) {
        this.reflectionUtils = reflectionUtils;
    }

    public List<Map.Entry<ClassLoader, ClassLoaderHandlerRegistry.ClassLoaderHandlerRegistryEntry>> getClassLoaderOrder() {
        return this.classLoaderOrder;
    }

    public Set<ClassLoader> getAllParentClassLoaders() {
        return this.allParentClassLoaders;
    }

    private ClassLoaderHandlerRegistry.ClassLoaderHandlerRegistryEntry getRegistryEntry(ClassLoader classLoader, LogNode logNode) {
        ClassLoaderHandlerRegistry.ClassLoaderHandlerRegistryEntry classLoaderHandlerRegistryEntry = this.classLoaderToClassLoaderHandlerRegistryEntry.get(classLoader);
        ClassLoaderHandlerRegistry.ClassLoaderHandlerRegistryEntry classLoaderHandlerRegistryEntry2 = classLoaderHandlerRegistryEntry;
        if (classLoaderHandlerRegistryEntry == null) {
            Class<?> cls = classLoader.getClass();
            while (true) {
                Class<?> cls2 = cls;
                if (cls2 != Object.class && cls2 != null) {
                    Iterator<ClassLoaderHandlerRegistry.ClassLoaderHandlerRegistryEntry> it = ClassLoaderHandlerRegistry.CLASS_LOADER_HANDLERS.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        ClassLoaderHandlerRegistry.ClassLoaderHandlerRegistryEntry next = it.next();
                        if (next.canHandle(cls2, logNode)) {
                            classLoaderHandlerRegistryEntry2 = next;
                            break;
                        }
                    }
                    if (classLoaderHandlerRegistryEntry2 != null) {
                        break;
                    }
                    cls = cls2.getSuperclass();
                } else {
                    break;
                }
            }
            if (classLoaderHandlerRegistryEntry2 == null) {
                classLoaderHandlerRegistryEntry2 = ClassLoaderHandlerRegistry.FALLBACK_HANDLER;
            }
            this.classLoaderToClassLoaderHandlerRegistryEntry.put(classLoader, classLoaderHandlerRegistryEntry2);
        }
        return classLoaderHandlerRegistryEntry2;
    }

    public void add(ClassLoader classLoader, LogNode logNode) {
        ClassLoaderHandlerRegistry.ClassLoaderHandlerRegistryEntry registryEntry;
        if (classLoader != null && this.added.add(classLoader) && (registryEntry = getRegistryEntry(classLoader, logNode)) != null) {
            this.classLoaderOrder.add(new AbstractMap.SimpleEntry(classLoader, registryEntry));
        }
    }

    public void delegateTo(ClassLoader classLoader, boolean z, LogNode logNode) {
        if (classLoader == null) {
            return;
        }
        if (z) {
            this.allParentClassLoaders.add(classLoader);
        }
        if (this.delegatedTo.add(classLoader)) {
            getRegistryEntry(classLoader, logNode).findClassLoaderOrder(classLoader, this, logNode);
        }
    }
}
