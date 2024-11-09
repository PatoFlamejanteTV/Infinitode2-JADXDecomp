package io.github.classgraph;

import com.vladsch.flexmark.util.html.Attribute;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import nonapi.io.github.classgraph.reflection.ReflectionUtils;
import nonapi.io.github.classgraph.utils.CollectionUtils;

/* loaded from: infinitode-2.jar:io/github/classgraph/ModuleRef.class */
public class ModuleRef implements Comparable<ModuleRef> {
    private final String name;
    private final Object reference;
    private final Object layer;
    private final Object descriptor;
    private final List<String> packages;
    private final URI location;
    private String locationStr;
    private File locationFile;
    private String rawVersion;
    private final ClassLoader classLoader;
    ReflectionUtils reflectionUtils;

    public ModuleRef(Object obj, Object obj2, ReflectionUtils reflectionUtils) {
        Boolean bool;
        if (obj == null) {
            throw new IllegalArgumentException("moduleReference cannot be null");
        }
        if (obj2 == null) {
            throw new IllegalArgumentException("moduleLayer cannot be null");
        }
        this.reference = obj;
        this.layer = obj2;
        this.reflectionUtils = reflectionUtils;
        this.descriptor = reflectionUtils.invokeMethod(true, obj, "descriptor");
        if (this.descriptor == null) {
            throw new IllegalArgumentException("moduleReference.descriptor() should not return null");
        }
        this.name = (String) reflectionUtils.invokeMethod(true, this.descriptor, Attribute.NAME_ATTR);
        Set set = (Set) reflectionUtils.invokeMethod(true, this.descriptor, "packages");
        if (set == null) {
            throw new IllegalArgumentException("moduleReference.descriptor().packages() should not return null");
        }
        this.packages = new ArrayList(set);
        CollectionUtils.sortIfNotEmpty(this.packages);
        Object invokeMethod = reflectionUtils.invokeMethod(true, this.descriptor, "rawVersion");
        if (invokeMethod != null && (bool = (Boolean) reflectionUtils.invokeMethod(true, invokeMethod, "isPresent")) != null && bool.booleanValue()) {
            this.rawVersion = (String) reflectionUtils.invokeMethod(true, invokeMethod, "get");
        }
        Object invokeMethod2 = reflectionUtils.invokeMethod(true, obj, "location");
        if (invokeMethod2 == null) {
            throw new IllegalArgumentException("moduleReference.location() should not return null");
        }
        Object invokeMethod3 = reflectionUtils.invokeMethod(true, invokeMethod2, "isPresent");
        if (invokeMethod3 == null) {
            throw new IllegalArgumentException("moduleReference.location().isPresent() should not return null");
        }
        if (((Boolean) invokeMethod3).booleanValue()) {
            this.location = (URI) reflectionUtils.invokeMethod(true, invokeMethod2, "get");
            if (this.location == null) {
                throw new IllegalArgumentException("moduleReference.location().get() should not return null");
            }
        } else {
            this.location = null;
        }
        this.classLoader = (ClassLoader) reflectionUtils.invokeMethod(true, obj2, "findLoader", String.class, this.name);
    }

    public String getName() {
        return this.name;
    }

    public Object getReference() {
        return this.reference;
    }

    public Object getLayer() {
        return this.layer;
    }

    public Object getDescriptor() {
        return this.descriptor;
    }

    public List<String> getPackages() {
        return this.packages;
    }

    public URI getLocation() {
        return this.location;
    }

    public String getLocationStr() {
        if (this.locationStr == null && this.location != null) {
            this.locationStr = this.location.toString();
        }
        return this.locationStr;
    }

    public File getLocationFile() {
        if (this.locationFile == null && this.location != null && "file".equals(this.location.getScheme())) {
            this.locationFile = new File(this.location);
        }
        return this.locationFile;
    }

    public String getRawVersion() {
        return this.rawVersion;
    }

    public boolean isSystemModule() {
        if (this.name == null || this.name.isEmpty()) {
            return false;
        }
        return this.name.startsWith("java.") || this.name.startsWith("jdk.") || this.name.startsWith("javafx.") || this.name.startsWith("oracle.");
    }

    public ClassLoader getClassLoader() {
        return this.classLoader;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ModuleRef)) {
            return false;
        }
        ModuleRef moduleRef = (ModuleRef) obj;
        return moduleRef.reference.equals(this.reference) && moduleRef.layer.equals(this.layer);
    }

    public int hashCode() {
        return this.reference.hashCode() * this.layer.hashCode();
    }

    public String toString() {
        return this.reference.toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(ModuleRef moduleRef) {
        int compareTo = this.name.compareTo(moduleRef.name);
        return compareTo != 0 ? compareTo : hashCode() - moduleRef.hashCode();
    }

    public ModuleReaderProxy open() {
        return new ModuleReaderProxy(this);
    }
}
