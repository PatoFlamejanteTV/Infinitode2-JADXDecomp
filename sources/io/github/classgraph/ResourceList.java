package io.github.classgraph;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import nonapi.io.github.classgraph.utils.CollectionUtils;

/* loaded from: infinitode-2.jar:io/github/classgraph/ResourceList.class */
public class ResourceList extends PotentiallyUnmodifiableList<Resource> implements AutoCloseable {
    static final long serialVersionUID = 1;
    static final ResourceList EMPTY_LIST;
    private static final ResourceFilter CLASSFILE_FILTER;

    @FunctionalInterface
    /* loaded from: infinitode-2.jar:io/github/classgraph/ResourceList$ByteArrayConsumer.class */
    public interface ByteArrayConsumer {
        void accept(Resource resource, byte[] bArr);
    }

    @FunctionalInterface
    /* loaded from: infinitode-2.jar:io/github/classgraph/ResourceList$ByteArrayConsumerThrowsIOException.class */
    public interface ByteArrayConsumerThrowsIOException {
        void accept(Resource resource, byte[] bArr);
    }

    @FunctionalInterface
    /* loaded from: infinitode-2.jar:io/github/classgraph/ResourceList$ByteBufferConsumer.class */
    public interface ByteBufferConsumer {
        void accept(Resource resource, ByteBuffer byteBuffer);
    }

    @FunctionalInterface
    /* loaded from: infinitode-2.jar:io/github/classgraph/ResourceList$ByteBufferConsumerThrowsIOException.class */
    public interface ByteBufferConsumerThrowsIOException {
        void accept(Resource resource, ByteBuffer byteBuffer);
    }

    @FunctionalInterface
    /* loaded from: infinitode-2.jar:io/github/classgraph/ResourceList$InputStreamConsumer.class */
    public interface InputStreamConsumer {
        void accept(Resource resource, InputStream inputStream);
    }

    @FunctionalInterface
    /* loaded from: infinitode-2.jar:io/github/classgraph/ResourceList$InputStreamConsumerThrowsIOException.class */
    public interface InputStreamConsumerThrowsIOException {
        void accept(Resource resource, InputStream inputStream);
    }

    @FunctionalInterface
    /* loaded from: infinitode-2.jar:io/github/classgraph/ResourceList$ResourceFilter.class */
    public interface ResourceFilter {
        boolean accept(Resource resource);
    }

    @Override // io.github.classgraph.PotentiallyUnmodifiableList, java.util.ArrayList, java.util.AbstractList, java.util.List
    public /* bridge */ /* synthetic */ ListIterator listIterator() {
        return super.listIterator();
    }

    @Override // io.github.classgraph.PotentiallyUnmodifiableList, java.util.ArrayList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public /* bridge */ /* synthetic */ Iterator iterator() {
        return super.iterator();
    }

    @Override // io.github.classgraph.PotentiallyUnmodifiableList, java.util.ArrayList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public /* bridge */ /* synthetic */ void clear() {
        super.clear();
    }

    @Override // io.github.classgraph.PotentiallyUnmodifiableList, java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public /* bridge */ /* synthetic */ boolean retainAll(Collection collection) {
        return super.retainAll(collection);
    }

    @Override // io.github.classgraph.PotentiallyUnmodifiableList, java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public /* bridge */ /* synthetic */ boolean removeAll(Collection collection) {
        return super.removeAll(collection);
    }

    @Override // io.github.classgraph.PotentiallyUnmodifiableList, java.util.ArrayList, java.util.AbstractList, java.util.List
    public /* bridge */ /* synthetic */ boolean addAll(int i, Collection collection) {
        return super.addAll(i, collection);
    }

    @Override // io.github.classgraph.PotentiallyUnmodifiableList, java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public /* bridge */ /* synthetic */ boolean addAll(Collection collection) {
        return super.addAll(collection);
    }

    @Override // io.github.classgraph.PotentiallyUnmodifiableList, java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public /* bridge */ /* synthetic */ boolean remove(Object obj) {
        return super.remove(obj);
    }

    @Override // io.github.classgraph.PotentiallyUnmodifiableList, java.util.ArrayList, java.util.AbstractList, java.util.Collection, java.util.List
    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // io.github.classgraph.PotentiallyUnmodifiableList, java.util.ArrayList, java.util.AbstractList, java.util.Collection, java.util.List
    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    static {
        ResourceList resourceList = new ResourceList();
        EMPTY_LIST = resourceList;
        resourceList.makeUnmodifiable();
        CLASSFILE_FILTER = new ResourceFilter() { // from class: io.github.classgraph.ResourceList.1
            @Override // io.github.classgraph.ResourceList.ResourceFilter
            public final boolean accept(Resource resource) {
                char charAt;
                String path = resource.getPath();
                return path.endsWith(".class") && path.length() >= 7 && (charAt = path.charAt(path.length() - 7)) != '/' && charAt != '.';
            }
        };
    }

    public static ResourceList emptyList() {
        return EMPTY_LIST;
    }

    public ResourceList() {
    }

    public ResourceList(int i) {
        super(i);
    }

    public ResourceList(Collection<Resource> collection) {
        super(collection);
    }

    public ResourceList get(String str) {
        boolean z = false;
        Iterator it = iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            if (((Resource) it.next()).getPath().equals(str)) {
                z = true;
                break;
            }
        }
        if (!z) {
            return EMPTY_LIST;
        }
        ResourceList resourceList = new ResourceList(2);
        Iterator it2 = iterator();
        while (it2.hasNext()) {
            Resource resource = (Resource) it2.next();
            if (resource.getPath().equals(str)) {
                resourceList.add(resource);
            }
        }
        return resourceList;
    }

    public List<String> getPaths() {
        ArrayList arrayList = new ArrayList(size());
        Iterator it = iterator();
        while (it.hasNext()) {
            arrayList.add(((Resource) it.next()).getPath());
        }
        return arrayList;
    }

    public List<String> getPathsRelativeToClasspathElement() {
        ArrayList arrayList = new ArrayList(size());
        Iterator it = iterator();
        while (it.hasNext()) {
            arrayList.add(((Resource) it.next()).getPath());
        }
        return arrayList;
    }

    public List<URL> getURLs() {
        ArrayList arrayList = new ArrayList(size());
        Iterator it = iterator();
        while (it.hasNext()) {
            arrayList.add(((Resource) it.next()).getURL());
        }
        return arrayList;
    }

    public List<URI> getURIs() {
        ArrayList arrayList = new ArrayList(size());
        Iterator it = iterator();
        while (it.hasNext()) {
            arrayList.add(((Resource) it.next()).getURI());
        }
        return arrayList;
    }

    public ResourceList classFilesOnly() {
        return filter(CLASSFILE_FILTER);
    }

    public ResourceList nonClassFilesOnly() {
        return filter(new ResourceFilter() { // from class: io.github.classgraph.ResourceList.2
            @Override // io.github.classgraph.ResourceList.ResourceFilter
            public boolean accept(Resource resource) {
                return !ResourceList.CLASSFILE_FILTER.accept(resource);
            }
        });
    }

    public Map<String, ResourceList> asMap() {
        HashMap hashMap = new HashMap();
        Iterator it = iterator();
        while (it.hasNext()) {
            Resource resource = (Resource) it.next();
            String path = resource.getPath();
            ResourceList resourceList = (ResourceList) hashMap.get(path);
            ResourceList resourceList2 = resourceList;
            if (resourceList == null) {
                resourceList2 = new ResourceList(1);
                hashMap.put(path, resourceList2);
            }
            resourceList2.add(resource);
        }
        return hashMap;
    }

    public List<Map.Entry<String, ResourceList>> findDuplicatePaths() {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, ResourceList> entry : asMap().entrySet()) {
            if (entry.getValue().size() > 1) {
                arrayList.add(new AbstractMap.SimpleEntry(entry.getKey(), entry.getValue()));
            }
        }
        CollectionUtils.sortIfNotEmpty(arrayList, new Comparator<Map.Entry<String, ResourceList>>() { // from class: io.github.classgraph.ResourceList.3
            @Override // java.util.Comparator
            public int compare(Map.Entry<String, ResourceList> entry2, Map.Entry<String, ResourceList> entry3) {
                return entry2.getKey().compareTo(entry3.getKey());
            }
        });
        return arrayList;
    }

    public ResourceList filter(ResourceFilter resourceFilter) {
        ResourceList resourceList = new ResourceList();
        Iterator it = iterator();
        while (it.hasNext()) {
            Resource resource = (Resource) it.next();
            if (resourceFilter.accept(resource)) {
                resourceList.add(resource);
            }
        }
        return resourceList;
    }

    @Deprecated
    public void forEachByteArray(ByteArrayConsumer byteArrayConsumer, boolean z) {
        Iterator it = iterator();
        while (it.hasNext()) {
            Resource resource = (Resource) it.next();
            Throwable th = null;
            try {
                try {
                    try {
                        byteArrayConsumer.accept(resource, resource.load());
                        if (resource != null) {
                            if (0 != 0) {
                                try {
                                    resource.close();
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                            } else {
                                resource.close();
                            }
                        }
                    } finally {
                    }
                } catch (Throwable th3) {
                    th = null;
                    throw th3;
                    break;
                }
            } catch (IOException e) {
                if (!z) {
                    throw new IllegalArgumentException("Could not load resource " + resource, e);
                }
            }
        }
    }

    @Deprecated
    public void forEachByteArray(ByteArrayConsumer byteArrayConsumer) {
        forEachByteArray(byteArrayConsumer, false);
    }

    public void forEachByteArrayIgnoringIOException(ByteArrayConsumer byteArrayConsumer) {
        Iterator it = iterator();
        while (it.hasNext()) {
            Resource resource = (Resource) it.next();
            Throwable th = null;
            try {
                try {
                    try {
                        byteArrayConsumer.accept(resource, resource.load());
                        if (resource != null) {
                            if (0 != 0) {
                                try {
                                    resource.close();
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                            } else {
                                resource.close();
                            }
                        }
                    } finally {
                    }
                } catch (Throwable th3) {
                    th = null;
                    throw th3;
                    break;
                }
            } catch (IOException unused) {
            }
        }
    }

    public void forEachByteArrayThrowingIOException(ByteArrayConsumerThrowsIOException byteArrayConsumerThrowsIOException) {
        Iterator it = iterator();
        while (it.hasNext()) {
            Resource resource = (Resource) it.next();
            try {
                try {
                    byteArrayConsumerThrowsIOException.accept(resource, resource.load());
                    if (resource != null) {
                        if (r8 != null) {
                            try {
                                resource.close();
                            } catch (Throwable th) {
                                r8.addSuppressed(th);
                            }
                        } else {
                            resource.close();
                        }
                    }
                } finally {
                    r8 = null;
                }
            } catch (Throwable th2) {
                if (resource != null) {
                    if (r8 != null) {
                        try {
                            resource.close();
                        } catch (Throwable th3) {
                            r8.addSuppressed(th3);
                        }
                    } else {
                        resource.close();
                    }
                }
                throw th2;
            }
        }
    }

    @Deprecated
    public void forEachInputStream(InputStreamConsumer inputStreamConsumer, boolean z) {
        Iterator it = iterator();
        while (it.hasNext()) {
            Resource resource = (Resource) it.next();
            Throwable th = null;
            try {
                try {
                    try {
                        inputStreamConsumer.accept(resource, resource.open());
                        if (resource != null) {
                            if (0 != 0) {
                                try {
                                    resource.close();
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                            } else {
                                resource.close();
                            }
                        }
                    } finally {
                    }
                } catch (Throwable th3) {
                    th = null;
                    throw th3;
                    break;
                }
            } catch (IOException e) {
                if (!z) {
                    throw new IllegalArgumentException("Could not load resource " + resource, e);
                }
            }
        }
    }

    @Deprecated
    public void forEachInputStream(InputStreamConsumer inputStreamConsumer) {
        forEachInputStream(inputStreamConsumer, false);
    }

    public void forEachInputStreamIgnoringIOException(InputStreamConsumer inputStreamConsumer) {
        Iterator it = iterator();
        while (it.hasNext()) {
            Resource resource = (Resource) it.next();
            Throwable th = null;
            try {
                try {
                    try {
                        inputStreamConsumer.accept(resource, resource.open());
                        if (resource != null) {
                            if (0 != 0) {
                                try {
                                    resource.close();
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                            } else {
                                resource.close();
                            }
                        }
                    } finally {
                    }
                } catch (Throwable th3) {
                    th = null;
                    throw th3;
                    break;
                }
            } catch (IOException unused) {
            }
        }
    }

    public void forEachInputStreamThrowingIOException(InputStreamConsumerThrowsIOException inputStreamConsumerThrowsIOException) {
        Iterator it = iterator();
        while (it.hasNext()) {
            Resource resource = (Resource) it.next();
            try {
                try {
                    inputStreamConsumerThrowsIOException.accept(resource, resource.open());
                    if (resource != null) {
                        if (r8 != null) {
                            try {
                                resource.close();
                            } catch (Throwable th) {
                                r8.addSuppressed(th);
                            }
                        } else {
                            resource.close();
                        }
                    }
                } finally {
                    r8 = null;
                }
            } catch (Throwable th2) {
                if (resource != null) {
                    if (r8 != null) {
                        try {
                            resource.close();
                        } catch (Throwable th3) {
                            r8.addSuppressed(th3);
                        }
                    } else {
                        resource.close();
                    }
                }
                throw th2;
            }
        }
    }

    @Deprecated
    public void forEachByteBuffer(ByteBufferConsumer byteBufferConsumer, boolean z) {
        Iterator it = iterator();
        while (it.hasNext()) {
            Resource resource = (Resource) it.next();
            Throwable th = null;
            try {
                try {
                    try {
                        byteBufferConsumer.accept(resource, resource.read());
                        if (resource != null) {
                            if (0 != 0) {
                                try {
                                    resource.close();
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                            } else {
                                resource.close();
                            }
                        }
                    } finally {
                    }
                } catch (Throwable th3) {
                    th = null;
                    throw th3;
                    break;
                }
            } catch (IOException e) {
                if (!z) {
                    throw new IllegalArgumentException("Could not load resource " + resource, e);
                }
            }
        }
    }

    @Deprecated
    public void forEachByteBuffer(ByteBufferConsumer byteBufferConsumer) {
        forEachByteBuffer(byteBufferConsumer, false);
    }

    public void forEachByteBufferIgnoringIOException(ByteBufferConsumer byteBufferConsumer) {
        Iterator it = iterator();
        while (it.hasNext()) {
            Resource resource = (Resource) it.next();
            Throwable th = null;
            try {
                try {
                    try {
                        byteBufferConsumer.accept(resource, resource.read());
                        if (resource != null) {
                            if (0 != 0) {
                                try {
                                    resource.close();
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                            } else {
                                resource.close();
                            }
                        }
                    } finally {
                    }
                } catch (Throwable th3) {
                    th = null;
                    throw th3;
                    break;
                }
            } catch (IOException unused) {
            }
        }
    }

    public void forEachByteBufferThrowingIOException(ByteBufferConsumerThrowsIOException byteBufferConsumerThrowsIOException) {
        Iterator it = iterator();
        while (it.hasNext()) {
            Resource resource = (Resource) it.next();
            try {
                try {
                    byteBufferConsumerThrowsIOException.accept(resource, resource.read());
                    if (resource != null) {
                        if (r8 != null) {
                            try {
                                resource.close();
                            } catch (Throwable th) {
                                r8.addSuppressed(th);
                            }
                        } else {
                            resource.close();
                        }
                    }
                } finally {
                    r8 = null;
                }
            } catch (Throwable th2) {
                if (resource != null) {
                    if (r8 != null) {
                        try {
                            resource.close();
                        } catch (Throwable th3) {
                            r8.addSuppressed(th3);
                        }
                    } else {
                        resource.close();
                    }
                }
                throw th2;
            }
        }
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        Iterator it = iterator();
        while (it.hasNext()) {
            ((Resource) it.next()).close();
        }
    }
}
