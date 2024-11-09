package nonapi.io.github.classgraph.fastzipfilereader;

import java.io.File;
import java.nio.file.Path;
import java.util.Objects;
import nonapi.io.github.classgraph.fileslice.Slice;
import nonapi.io.github.classgraph.scanspec.AcceptReject;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/fastzipfilereader/ZipFileSlice.class */
public class ZipFileSlice {
    private final ZipFileSlice parentZipFileSlice;
    protected final PhysicalZipFile physicalZipFile;
    private final String pathWithinParentZipFileSlice;
    public Slice slice;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ZipFileSlice(PhysicalZipFile physicalZipFile) {
        this.parentZipFileSlice = null;
        this.physicalZipFile = physicalZipFile;
        this.slice = physicalZipFile.slice;
        this.pathWithinParentZipFileSlice = physicalZipFile.getPathStr();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ZipFileSlice(PhysicalZipFile physicalZipFile, FastZipEntry fastZipEntry) {
        this.parentZipFileSlice = fastZipEntry.parentLogicalZipFile;
        this.physicalZipFile = physicalZipFile;
        this.slice = physicalZipFile.slice;
        this.pathWithinParentZipFileSlice = fastZipEntry.entryName;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ZipFileSlice(FastZipEntry fastZipEntry) {
        this.parentZipFileSlice = fastZipEntry.parentLogicalZipFile;
        this.physicalZipFile = fastZipEntry.parentLogicalZipFile.physicalZipFile;
        this.slice = fastZipEntry.getSlice();
        this.pathWithinParentZipFileSlice = fastZipEntry.entryName;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ZipFileSlice(ZipFileSlice zipFileSlice) {
        this.parentZipFileSlice = zipFileSlice.parentZipFileSlice;
        this.physicalZipFile = zipFileSlice.physicalZipFile;
        this.slice = zipFileSlice.slice;
        this.pathWithinParentZipFileSlice = zipFileSlice.pathWithinParentZipFileSlice;
    }

    public boolean isAcceptedAndNotRejected(AcceptReject.AcceptRejectLeafname acceptRejectLeafname) {
        if (acceptRejectLeafname.isAcceptedAndNotRejected(this.pathWithinParentZipFileSlice)) {
            return this.parentZipFileSlice == null || this.parentZipFileSlice.isAcceptedAndNotRejected(acceptRejectLeafname);
        }
        return false;
    }

    public ZipFileSlice getParentZipFileSlice() {
        return this.parentZipFileSlice;
    }

    public String getPathWithinParentZipFileSlice() {
        return this.pathWithinParentZipFileSlice;
    }

    private void appendPath(StringBuilder sb) {
        if (this.parentZipFileSlice != null) {
            this.parentZipFileSlice.appendPath(sb);
            if (sb.length() > 0) {
                sb.append("!/");
            }
        }
        sb.append(this.pathWithinParentZipFileSlice);
    }

    public String getPath() {
        StringBuilder sb = new StringBuilder();
        appendPath(sb);
        return sb.toString();
    }

    public File getPhysicalFile() {
        Path path = this.physicalZipFile.getPath();
        if (path != null) {
            try {
                return path.toFile();
            } catch (UnsupportedOperationException unused) {
                return null;
            }
        }
        return this.physicalZipFile.getFile();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ZipFileSlice)) {
            return false;
        }
        ZipFileSlice zipFileSlice = (ZipFileSlice) obj;
        return Objects.equals(this.physicalZipFile, zipFileSlice.physicalZipFile) && Objects.equals(this.slice, zipFileSlice.slice) && Objects.equals(this.pathWithinParentZipFileSlice, zipFileSlice.pathWithinParentZipFileSlice);
    }

    public int hashCode() {
        return Objects.hash(this.physicalZipFile, this.slice, this.pathWithinParentZipFileSlice);
    }

    public String toString() {
        String path = getPath();
        String path2 = this.physicalZipFile.getPath() == null ? null : this.physicalZipFile.getPath().toString();
        String str = path2;
        if (path2 == null) {
            str = this.physicalZipFile.getFile() == null ? null : this.physicalZipFile.getFile().toString();
        }
        return "[" + ((str == null || str.equals(path)) ? path : path + " -> " + str) + " ; byte range: " + this.slice.sliceStartPos + ".." + (this.slice.sliceStartPos + this.slice.sliceLength) + " / " + this.physicalZipFile.length() + "]";
    }
}
