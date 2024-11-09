package net.bytebuddy.utility;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.utility.nullability.MaybeNull;

@HashCodeAndEqualsPlugin.Enhance
/* loaded from: infinitode-2.jar:net/bytebuddy/utility/StreamDrainer.class */
public class StreamDrainer {
    public static final int DEFAULT_BUFFER_SIZE = 1024;
    public static final StreamDrainer DEFAULT = new StreamDrainer();
    private static final int END_OF_STREAM = -1;
    private static final int FROM_BEGINNING = 0;
    private final int bufferSize;

    public boolean equals(@MaybeNull Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.bufferSize == ((StreamDrainer) obj).bufferSize;
    }

    public int hashCode() {
        return (getClass().hashCode() * 31) + this.bufferSize;
    }

    public StreamDrainer() {
        this(1024);
    }

    public StreamDrainer(int i) {
        this.bufferSize = i;
    }

    public byte[] drain(InputStream inputStream) {
        int read;
        ArrayList arrayList = new ArrayList();
        byte[] bArr = new byte[this.bufferSize];
        int i = 0;
        do {
            read = inputStream.read(bArr, i, this.bufferSize - i);
            int max = i + Math.max(read, 0);
            i = max;
            if (max == this.bufferSize) {
                arrayList.add(bArr);
                bArr = new byte[this.bufferSize];
                i = 0;
            }
        } while (read != -1);
        byte[] bArr2 = new byte[(arrayList.size() * this.bufferSize) + i];
        int i2 = 0;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            int i3 = i2;
            i2++;
            System.arraycopy((byte[]) it.next(), 0, bArr2, i3 * this.bufferSize, this.bufferSize);
        }
        System.arraycopy(bArr, 0, bArr2, i2 * this.bufferSize, i);
        return bArr2;
    }
}
