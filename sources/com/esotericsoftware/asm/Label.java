package com.esotericsoftware.asm;

/* loaded from: infinitode-2.jar:com/esotericsoftware/asm/Label.class */
public class Label {
    public Object info;

    /* renamed from: a, reason: collision with root package name */
    int f1454a;

    /* renamed from: b, reason: collision with root package name */
    int f1455b;
    int c;
    private int d;
    private int[] e;
    int f;
    int g;
    Frame h;
    Label i;
    Edge j;
    Label k;

    public int getOffset() {
        if ((this.f1454a & 2) == 0) {
            throw new IllegalStateException("Label offset position has not been resolved yet");
        }
        return this.c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(MethodWriter methodWriter, ByteVector byteVector, int i, boolean z) {
        if ((this.f1454a & 2) != 0) {
            if (z) {
                byteVector.putInt(this.c - i);
                return;
            } else {
                byteVector.putShort(this.c - i);
                return;
            }
        }
        if (z) {
            a((-1) - i, byteVector.f1436b);
            byteVector.putInt(-1);
        } else {
            a(i, byteVector.f1436b);
            byteVector.putShort(-1);
        }
    }

    private void a(int i, int i2) {
        if (this.e == null) {
            this.e = new int[6];
        }
        if (this.d >= this.e.length) {
            int[] iArr = new int[this.e.length + 6];
            System.arraycopy(this.e, 0, iArr, 0, this.e.length);
            this.e = iArr;
        }
        int[] iArr2 = this.e;
        int i3 = this.d;
        this.d = i3 + 1;
        iArr2[i3] = i;
        int[] iArr3 = this.e;
        int i4 = this.d;
        this.d = i4 + 1;
        iArr3[i4] = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(MethodWriter methodWriter, int i, byte[] bArr) {
        boolean z = false;
        this.f1454a |= 2;
        this.c = i;
        int i2 = 0;
        while (i2 < this.d) {
            int i3 = i2;
            int i4 = i2 + 1;
            int i5 = this.e[i3];
            i2 = i4 + 1;
            int i6 = this.e[i4];
            if (i5 >= 0) {
                int i7 = i - i5;
                if (i7 < -32768 || i7 > 32767) {
                    int i8 = bArr[i6 - 1] & 255;
                    if (i8 <= 168) {
                        bArr[i6 - 1] = (byte) (i8 + 49);
                    } else {
                        bArr[i6 - 1] = (byte) (i8 + 20);
                    }
                    z = true;
                }
                bArr[i6] = (byte) (i7 >>> 8);
                bArr[i6 + 1] = (byte) i7;
            } else {
                int i9 = i + i5 + 1;
                int i10 = i6 + 1;
                bArr[i6] = (byte) (i9 >>> 24);
                int i11 = i10 + 1;
                bArr[i10] = (byte) (i9 >>> 16);
                bArr[i11] = (byte) (i9 >>> 8);
                bArr[i11 + 1] = (byte) i9;
            }
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Label a() {
        return this.h == null ? this : this.h.f1447b;
    }

    boolean a(long j) {
        return ((this.f1454a & 1024) == 0 || (this.e[(int) (j >>> 32)] & ((int) j)) == 0) ? false : true;
    }

    boolean a(Label label) {
        if ((this.f1454a & 1024) == 0 || (label.f1454a & 1024) == 0) {
            return false;
        }
        for (int i = 0; i < this.e.length; i++) {
            if ((this.e[i] & label.e[i]) != 0) {
                return true;
            }
        }
        return false;
    }

    void a(long j, int i) {
        if ((this.f1454a & 1024) == 0) {
            this.f1454a |= 1024;
            this.e = new int[(i / 32) + 1];
        }
        int[] iArr = this.e;
        int i2 = (int) (j >>> 32);
        iArr[i2] = iArr[i2] | ((int) j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0099  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void b(com.esotericsoftware.asm.Label r6, long r7, int r9) {
        /*
            r5 = this;
            r0 = r5
            r10 = r0
        L3:
            r0 = r10
            if (r0 == 0) goto Ldb
            r0 = r10
            r1 = r0
            r11 = r1
            com.esotericsoftware.asm.Label r0 = r0.k
            r10 = r0
            r0 = r11
            r1 = 0
            r0.k = r1
            r0 = r6
            if (r0 == 0) goto L7c
            r0 = r11
            int r0 = r0.f1454a
            r1 = 2048(0x800, float:2.87E-42)
            r0 = r0 & r1
            if (r0 != 0) goto L3
            r0 = r11
            r1 = r0
            int r1 = r1.f1454a
            r2 = 2048(0x800, float:2.87E-42)
            r1 = r1 | r2
            r0.f1454a = r1
            r0 = r11
            int r0 = r0.f1454a
            r1 = 256(0x100, float:3.59E-43)
            r0 = r0 & r1
            if (r0 == 0) goto L8d
            r0 = r11
            r1 = r6
            boolean r0 = r0.a(r1)
            if (r0 != 0) goto L8d
            com.esotericsoftware.asm.Edge r0 = new com.esotericsoftware.asm.Edge
            r1 = r0
            r1.<init>()
            r1 = r0
            r12 = r1
            r1 = r11
            int r1 = r1.f
            r0.f1443a = r1
            r0 = r12
            r1 = r6
            com.esotericsoftware.asm.Edge r1 = r1.j
            com.esotericsoftware.asm.Label r1 = r1.f1444b
            r0.f1444b = r1
            r0 = r12
            r1 = r11
            com.esotericsoftware.asm.Edge r1 = r1.j
            r0.c = r1
            r0 = r11
            r1 = r12
            r0.j = r1
            goto L8d
        L7c:
            r0 = r11
            r1 = r7
            boolean r0 = r0.a(r1)
            if (r0 != 0) goto L3
            r0 = r11
            r1 = r7
            r2 = r9
            r0.a(r1, r2)
        L8d:
            r0 = r11
            com.esotericsoftware.asm.Edge r0 = r0.j
            r12 = r0
        L94:
            r0 = r12
            if (r0 == 0) goto Ld8
            r0 = r11
            int r0 = r0.f1454a
            r1 = 128(0x80, float:1.8E-43)
            r0 = r0 & r1
            if (r0 == 0) goto Lb2
            r0 = r12
            r1 = r11
            com.esotericsoftware.asm.Edge r1 = r1.j
            com.esotericsoftware.asm.Edge r1 = r1.c
            if (r0 == r1) goto Lce
        Lb2:
            r0 = r12
            com.esotericsoftware.asm.Label r0 = r0.f1444b
            com.esotericsoftware.asm.Label r0 = r0.k
            if (r0 != 0) goto Lce
            r0 = r12
            com.esotericsoftware.asm.Label r0 = r0.f1444b
            r1 = r10
            r0.k = r1
            r0 = r12
            com.esotericsoftware.asm.Label r0 = r0.f1444b
            r10 = r0
        Lce:
            r0 = r12
            com.esotericsoftware.asm.Edge r0 = r0.c
            r12 = r0
            goto L94
        Ld8:
            goto L3
        Ldb:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.esotericsoftware.asm.Label.b(com.esotericsoftware.asm.Label, long, int):void");
    }

    public String toString() {
        return new StringBuffer("L").append(System.identityHashCode(this)).toString();
    }
}
