package nonapi.io.github.classgraph.fastzipfilereader;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Objects;
import nonapi.io.github.classgraph.fileslice.ArraySlice;
import nonapi.io.github.classgraph.fileslice.FileSlice;
import nonapi.io.github.classgraph.fileslice.PathSlice;
import nonapi.io.github.classgraph.fileslice.Slice;
import nonapi.io.github.classgraph.utils.FastPathResolver;
import nonapi.io.github.classgraph.utils.FileUtils;
import nonapi.io.github.classgraph.utils.LogNode;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/fastzipfilereader/PhysicalZipFile.class */
public class PhysicalZipFile {
    private Path path;
    private File file;
    private final String pathStr;
    Slice slice;
    NestedJarHandler nestedJarHandler;
    private int hashCode;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PhysicalZipFile(File file, NestedJarHandler nestedJarHandler, LogNode logNode) {
        this.nestedJarHandler = nestedJarHandler;
        this.file = file;
        this.pathStr = FastPathResolver.resolve(FileUtils.currDirPath(), file.getPath());
        this.slice = new FileSlice(file, nestedJarHandler, logNode);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PhysicalZipFile(Path path, NestedJarHandler nestedJarHandler, LogNode logNode) {
        this.nestedJarHandler = nestedJarHandler;
        this.path = path;
        this.pathStr = FastPathResolver.resolve(FileUtils.currDirPath(), path.toString());
        this.slice = new PathSlice(path, nestedJarHandler);
    }

    PhysicalZipFile(byte[] bArr, File file, String str, NestedJarHandler nestedJarHandler) {
        this.nestedJarHandler = nestedJarHandler;
        this.file = file;
        this.pathStr = str;
        this.slice = new ArraySlice(bArr, false, 0L, nestedJarHandler);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PhysicalZipFile(InputStream inputStream, long j, String str, NestedJarHandler nestedJarHandler, LogNode logNode) {
        this.nestedJarHandler = nestedJarHandler;
        this.pathStr = str;
        this.slice = nestedJarHandler.readAllBytesWithSpilloverToDisk(inputStream, str, j, logNode);
        this.file = this.slice instanceof FileSlice ? ((FileSlice) this.slice).file : null;
    }

    public Path getPath() {
        return this.path;
    }

    public File getFile() {
        return this.file;
    }

    public String getPathStr() {
        return this.pathStr;
    }

    public long length() {
        return this.slice.sliceLength;
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = this.file == null ? 0 : this.file.hashCode();
            if (this.hashCode == 0) {
                this.hashCode = 1;
            }
        }
        return this.hashCode;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PhysicalZipFile)) {
            return false;
        }
        return Objects.equals(this.file, ((PhysicalZipFile) obj).file);
    }

    public String toString() {
        return this.pathStr;
    }
}
