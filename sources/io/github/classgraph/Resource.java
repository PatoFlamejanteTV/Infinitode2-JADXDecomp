package io.github.classgraph;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Set;
import nonapi.io.github.classgraph.fileslice.reader.ClassfileReader;
import nonapi.io.github.classgraph.utils.LogNode;
import nonapi.io.github.classgraph.utils.URLPathEncoder;

/* loaded from: infinitode-2.jar:io/github/classgraph/Resource.class */
public abstract class Resource implements Closeable, Comparable<Resource> {
    private final ClasspathElement classpathElement;
    protected InputStream inputStream;
    protected ByteBuffer byteBuffer;
    protected long length;
    private String toString;
    LogNode scanLog;

    public abstract String getPath();

    public abstract InputStream open();

    public abstract ByteBuffer read();

    public abstract byte[] load();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract ClassfileReader openClassfile();

    public abstract long getLastModified();

    public abstract Set<PosixFilePermission> getPosixFilePermissions();

    public Resource(ClasspathElement classpathElement, long j) {
        this.classpathElement = classpathElement;
        this.length = j;
    }

    private static URL uriToURL(URI uri) {
        try {
            return uri.toURL();
        } catch (IllegalArgumentException | MalformedURLException e) {
            if (uri.getScheme().equals("jrt")) {
                throw new IllegalArgumentException("Could not create URL from URI with \"jrt:\" scheme (\"jrt:\" is not supported by the URL class without a custom URL protocol handler): " + uri);
            }
            throw new IllegalArgumentException("Could not create URL from URI: " + uri + " -- " + e);
        }
    }

    public URI getURI() {
        String str;
        String uri = getClasspathElementURI().toString();
        String pathRelativeToClasspathElement = getPathRelativeToClasspathElement();
        boolean endsWith = uri.endsWith("/");
        try {
            StringBuilder append = new StringBuilder().append((endsWith || uri.startsWith("jar:") || uri.startsWith("jrt:")) ? "" : "jar:").append(uri);
            if (endsWith) {
                str = "";
            } else {
                str = uri.startsWith("jrt:") ? "/" : "!/";
            }
            return new URI(append.append(str).append(URLPathEncoder.encodePath(pathRelativeToClasspathElement)).toString());
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Could not form URL for classpath element: " + uri + " ; path: " + pathRelativeToClasspathElement + " : " + e);
        }
    }

    public URL getURL() {
        return uriToURL(getURI());
    }

    public URI getClasspathElementURI() {
        return this.classpathElement.getURI();
    }

    public URL getClasspathElementURL() {
        return uriToURL(getClasspathElementURI());
    }

    public File getClasspathElementFile() {
        return this.classpathElement.getFile();
    }

    public ModuleRef getModuleRef() {
        if (this.classpathElement instanceof ClasspathElementModule) {
            return ((ClasspathElementModule) this.classpathElement).moduleRef;
        }
        return null;
    }

    public String getContentAsString() {
        String str = new String(load(), StandardCharsets.UTF_8);
        close();
        return str;
    }

    public String getPathRelativeToClasspathElement() {
        return getPath();
    }

    public CloseableByteBuffer readCloseable() {
        return new CloseableByteBuffer(read(), new Runnable() { // from class: io.github.classgraph.Resource.1
            @Override // java.lang.Runnable
            public void run() {
                Resource.this.close();
            }
        });
    }

    public long getLength() {
        return this.length;
    }

    public String toString() {
        if (this.toString != null) {
            return this.toString;
        }
        String uri = getURI().toString();
        this.toString = uri;
        return uri;
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Resource)) {
            return false;
        }
        return toString().equals(obj.toString());
    }

    @Override // java.lang.Comparable
    public int compareTo(Resource resource) {
        return toString().compareTo(resource.toString());
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.inputStream != null) {
            try {
                this.inputStream.close();
            } catch (IOException unused) {
            }
            this.inputStream = null;
        }
    }
}
