package org.a.c.c;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.imageio.stream.MemoryCacheImageInputStream;
import javax.imageio.stream.MemoryCacheImageOutputStream;

/* loaded from: infinitode-2.jar:org/a/c/c/r.class */
public class r extends l {

    /* renamed from: a, reason: collision with root package name */
    private static final org.a.a.a.a f4410a = org.a.a.a.c.a(r.class);

    @Override // org.a.c.c.l
    public final k a(InputStream inputStream, OutputStream outputStream, org.a.c.b.d dVar, int i) {
        org.a.c.b.d a2 = a(dVar, i);
        int b2 = a2.b(org.a.c.b.j.aO, 1);
        int i2 = b2;
        if (b2 != 0 && i2 != 1) {
            i2 = 1;
        }
        a(inputStream, t.a(outputStream, a2), i2);
        return new k(dVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void a(InputStream inputStream, OutputStream outputStream, int i) {
        List arrayList = new ArrayList();
        int i2 = 9;
        MemoryCacheImageInputStream memoryCacheImageInputStream = new MemoryCacheImageInputStream(inputStream);
        long j = -1;
        while (true) {
            try {
                long readBits = memoryCacheImageInputStream.readBits(i2);
                if (readBits == 257) {
                    break;
                }
                if (readBits == 256) {
                    i2 = 9;
                    arrayList = b();
                    j = -1;
                } else {
                    if (readBits < arrayList.size()) {
                        byte[] bArr = (byte[]) arrayList.get((int) readBits);
                        byte b2 = bArr[0];
                        outputStream.write(bArr);
                        if (j != -1) {
                            a((List<byte[]>) arrayList, j, memoryCacheImageInputStream);
                            byte[] bArr2 = (byte[]) arrayList.get((int) j);
                            byte[] copyOf = Arrays.copyOf(bArr2, bArr2.length + 1);
                            copyOf[bArr2.length] = b2;
                            arrayList.add(copyOf);
                        }
                    } else {
                        a((List<byte[]>) arrayList, j, memoryCacheImageInputStream);
                        byte[] bArr3 = (byte[]) arrayList.get((int) j);
                        byte[] copyOf2 = Arrays.copyOf(bArr3, bArr3.length + 1);
                        copyOf2[bArr3.length] = bArr3[0];
                        outputStream.write(copyOf2);
                        arrayList.add(copyOf2);
                    }
                    i2 = a(arrayList.size(), i);
                    j = readBits;
                }
            } catch (EOFException unused) {
            }
        }
        outputStream.flush();
    }

    private static void a(List<byte[]> list, long j, MemoryCacheImageInputStream memoryCacheImageInputStream) {
        if (j < 0) {
            throw new IOException("negative array index: " + j + " near offset " + memoryCacheImageInputStream.getStreamPosition());
        }
        if (j >= list.size()) {
            throw new IOException("array index overflow: " + j + " >= " + list.size() + " near offset " + memoryCacheImageInputStream.getStreamPosition());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.a.c.c.l
    public final void a(InputStream inputStream, OutputStream outputStream, org.a.c.b.d dVar) {
        int i;
        List<byte[]> b2 = b();
        byte[] bArr = null;
        MemoryCacheImageOutputStream memoryCacheImageOutputStream = new MemoryCacheImageOutputStream(outputStream);
        memoryCacheImageOutputStream.writeBits(256L, 9);
        int i2 = -1;
        while (true) {
            i = i2;
            int read = inputStream.read();
            if (read == -1) {
                break;
            }
            byte b3 = (byte) read;
            if (bArr == null) {
                bArr = new byte[]{b3};
                i2 = b3 & 255;
            } else {
                byte[] bArr2 = bArr;
                byte[] copyOf = Arrays.copyOf(bArr2, bArr2.length + 1);
                bArr = copyOf;
                copyOf[bArr.length - 1] = b3;
                int a2 = a(b2, bArr);
                if (a2 == -1) {
                    int a3 = a(b2.size() - 1, 1);
                    memoryCacheImageOutputStream.writeBits(i, a3);
                    b2.add(bArr);
                    if (b2.size() == 4096) {
                        memoryCacheImageOutputStream.writeBits(256L, a3);
                        b2 = b();
                    }
                    bArr = new byte[]{b3};
                    i2 = b3 & 255;
                } else {
                    i2 = a2;
                }
            }
        }
        if (i != -1) {
            memoryCacheImageOutputStream.writeBits(i, a(b2.size() - 1, 1));
        }
        memoryCacheImageOutputStream.writeBits(257L, a(b2.size(), 1));
        memoryCacheImageOutputStream.writeBits(0L, 7);
        memoryCacheImageOutputStream.flush();
        memoryCacheImageOutputStream.close();
    }

    private static int a(List<byte[]> list, byte[] bArr) {
        int i = -1;
        int i2 = 0;
        for (int size = list.size() - 1; size >= 0; size--) {
            if (size <= 257) {
                if (i != -1) {
                    return i;
                }
                if (bArr.length > 1) {
                    return -1;
                }
            }
            byte[] bArr2 = list.get(size);
            if ((i != -1 || bArr2.length > i2) && Arrays.equals(bArr2, bArr)) {
                i = size;
                i2 = bArr2.length;
            }
        }
        return i;
    }

    private static List<byte[]> b() {
        ArrayList arrayList = new ArrayList(4096);
        for (int i = 0; i < 256; i++) {
            arrayList.add(new byte[]{(byte) i});
        }
        arrayList.add(null);
        arrayList.add(null);
        return arrayList;
    }

    private static int a(int i, int i2) {
        if (i >= 2048 - i2) {
            return 12;
        }
        if (i >= 1024 - i2) {
            return 11;
        }
        if (i >= 512 - i2) {
            return 10;
        }
        return 9;
    }
}
