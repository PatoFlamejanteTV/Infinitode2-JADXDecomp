package nonapi.io.github.classgraph.fastzipfilereader;

import java.io.IOException;
import java.util.Calendar;
import java.util.TimeZone;
import nonapi.io.github.classgraph.fileslice.Slice;
import nonapi.io.github.classgraph.utils.VersionFinder;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/fastzipfilereader/FastZipEntry.class */
public class FastZipEntry implements Comparable<FastZipEntry> {
    final LogicalZipFile parentLogicalZipFile;
    private final long locHeaderPos;
    public final String entryName;
    final boolean isDeflated;
    public final long compressedSize;
    public final long uncompressedSize;
    private long lastModifiedTimeMillis;
    private final int lastModifiedTimeMSDOS;
    private final int lastModifiedDateMSDOS;
    public final int fileAttributes;
    private Slice slice;
    final int version;
    public final String entryNameUnversioned;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FastZipEntry(LogicalZipFile logicalZipFile, long j, String str, boolean z, long j2, long j3, long j4, int i, int i2, int i3, boolean z2) {
        int indexOf;
        int i4;
        this.parentLogicalZipFile = logicalZipFile;
        this.locHeaderPos = j;
        this.entryName = str;
        this.isDeflated = z;
        this.compressedSize = j2;
        this.uncompressedSize = (z || j3 >= 0) ? j3 : j2;
        this.lastModifiedTimeMillis = j4;
        this.lastModifiedTimeMSDOS = i;
        this.lastModifiedDateMSDOS = i2;
        this.fileAttributes = i3;
        int i5 = 8;
        String str2 = str;
        if (str.startsWith(LogicalZipFile.MULTI_RELEASE_PATH_PREFIX) && str.length() > 18 + 1 && (indexOf = str.indexOf(47, 18)) > 0) {
            String substring = str.substring(18, indexOf);
            int i6 = 0;
            if (substring.length() < 6 && !substring.isEmpty()) {
                for (int i7 = 0; i7 < substring.length(); i7++) {
                    char charAt = substring.charAt(i7);
                    if (charAt < '0' || charAt > '9') {
                        i6 = 0;
                        break;
                    }
                    if (i6 != 0) {
                        i4 = (i6 * 10) + charAt;
                    } else {
                        i4 = charAt;
                    }
                    i6 = i4 - 48;
                }
            }
            i5 = i6 != 0 ? i6 : i5;
            i5 = (i5 < 9 || i5 > VersionFinder.JAVA_MAJOR_VERSION) ? 8 : i5;
            if (!z2 && i5 > 8) {
                String substring2 = str.substring(indexOf + 1);
                str2 = substring2;
                if (substring2.startsWith("META-INF/")) {
                    i5 = 8;
                    str2 = str;
                }
            }
        }
        this.version = i5;
        this.entryNameUnversioned = str2;
    }

    public Slice getSlice() {
        if (this.slice == null) {
            if (this.parentLogicalZipFile.slice.randomAccessReader().readInt(this.locHeaderPos) != 67324752) {
                throw new IOException("Zip entry has bad LOC header: " + this.entryName);
            }
            long readShort = this.locHeaderPos + 30 + r0.readShort(this.locHeaderPos + 26) + r0.readShort(this.locHeaderPos + 28);
            if (readShort > this.parentLogicalZipFile.slice.sliceLength) {
                throw new IOException("Unexpected EOF when trying to read zip entry data: " + this.entryName);
            }
            this.slice = this.parentLogicalZipFile.slice.slice(readShort, this.compressedSize, this.isDeflated, this.uncompressedSize);
        }
        return this.slice;
    }

    public String getPath() {
        return this.parentLogicalZipFile.getPath() + "!/" + this.entryName;
    }

    public long getLastModifiedTimeMillis() {
        if (this.lastModifiedTimeMillis == 0 && (this.lastModifiedDateMSDOS != 0 || this.lastModifiedTimeMSDOS != 0)) {
            int i = (this.lastModifiedTimeMSDOS & 31) << 1;
            int i2 = (this.lastModifiedTimeMSDOS >> 5) & 63;
            int i3 = this.lastModifiedTimeMSDOS >> 11;
            int i4 = this.lastModifiedDateMSDOS & 31;
            int i5 = ((this.lastModifiedDateMSDOS >> 5) & 7) - 1;
            int i6 = (this.lastModifiedDateMSDOS >> 9) + 1980;
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            calendar.set(i6, i5, i4, i3, i2, i);
            calendar.set(14, 0);
            this.lastModifiedTimeMillis = calendar.getTimeInMillis();
        }
        return this.lastModifiedTimeMillis;
    }

    @Override // java.lang.Comparable
    public int compareTo(FastZipEntry fastZipEntry) {
        int i = fastZipEntry.version - this.version;
        if (i != 0) {
            return i;
        }
        int compareTo = this.entryNameUnversioned.compareTo(fastZipEntry.entryNameUnversioned);
        if (compareTo != 0) {
            return compareTo;
        }
        int compareTo2 = this.entryName.compareTo(fastZipEntry.entryName);
        if (compareTo2 != 0) {
            return compareTo2;
        }
        long j = this.locHeaderPos - fastZipEntry.locHeaderPos;
        if (j < 0) {
            return -1;
        }
        return j > 0 ? 1 : 0;
    }

    public int hashCode() {
        return ((this.parentLogicalZipFile.hashCode() ^ this.version) ^ this.entryName.hashCode()) ^ ((int) this.locHeaderPos);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FastZipEntry)) {
            return false;
        }
        FastZipEntry fastZipEntry = (FastZipEntry) obj;
        return this.parentLogicalZipFile.equals(fastZipEntry.parentLogicalZipFile) && compareTo(fastZipEntry) == 0;
    }

    public String toString() {
        return "jar:file:" + getPath();
    }
}
