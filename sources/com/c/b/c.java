package com.c.b;

import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/c/b/c.class */
public class c {

    /* renamed from: b, reason: collision with root package name */
    private byte[][] f910b;
    private int[] c;
    private int d;

    /* renamed from: a, reason: collision with root package name */
    public byte[] f911a;

    static {
        "vorbis".getBytes();
        "Xiphophorus libVorbis I 20000508".getBytes();
    }

    public final void a() {
        this.f910b = (byte[][]) null;
        this.d = 0;
        this.f911a = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Type inference failed for: r1v12, types: [byte[], byte[][]] */
    public final int a(com.c.a.a aVar) {
        int c = aVar.c(32);
        if (c < 0) {
            b();
            return -1;
        }
        this.f911a = new byte[c + 1];
        aVar.a(this.f911a, c);
        this.d = aVar.c(32);
        if (this.d < 0) {
            b();
            return -1;
        }
        this.f910b = new byte[this.d + 1];
        this.c = new int[this.d + 1];
        for (int i = 0; i < this.d; i++) {
            int c2 = aVar.c(32);
            if (c2 >= 0) {
                this.c[i] = c2;
                this.f910b[i] = new byte[c2 + 1];
                aVar.a(this.f910b[i], c2);
            } else {
                b();
                return -1;
            }
        }
        if (aVar.c(1) != 1) {
            b();
            return -1;
        }
        return 0;
    }

    private void b() {
        for (int i = 0; i < this.d; i++) {
            this.f910b[i] = null;
        }
        this.f910b = (byte[][]) null;
        this.f911a = null;
    }

    public String toString() {
        String str = "Vendor: " + new String(this.f911a, 0, this.f911a.length - 1);
        for (int i = 0; i < this.d; i++) {
            str = str + "\nComment: " + new String(this.f910b[i], 0, this.f910b[i].length - 1);
        }
        return str + SequenceUtils.EOL;
    }
}
