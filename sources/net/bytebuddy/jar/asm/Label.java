package net.bytebuddy.jar.asm;

/* loaded from: infinitode-2.jar:net/bytebuddy/jar/asm/Label.class */
public class Label {

    /* renamed from: a, reason: collision with root package name */
    static final Label f4144a = new Label();
    public Object info;

    /* renamed from: b, reason: collision with root package name */
    short f4145b;
    private short lineNumber;
    private int[] otherLineNumbers;
    int c;
    private int[] forwardReferences;
    short d;
    short e;
    short f;
    short g;
    Frame h;
    Label i;
    Edge j;
    Label k;

    public int getOffset() {
        if ((this.f4145b & 4) == 0) {
            throw new IllegalStateException("Label offset position has not been resolved yet");
        }
        return this.c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Label a() {
        return this.h == null ? this : this.h.f4141a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(int i) {
        if (this.lineNumber == 0) {
            this.lineNumber = (short) i;
            return;
        }
        if (this.otherLineNumbers == null) {
            this.otherLineNumbers = new int[4];
        }
        int[] iArr = this.otherLineNumbers;
        int i2 = iArr[0] + 1;
        iArr[0] = i2;
        if (i2 >= this.otherLineNumbers.length) {
            int[] iArr2 = new int[this.otherLineNumbers.length + 4];
            System.arraycopy(this.otherLineNumbers, 0, iArr2, 0, this.otherLineNumbers.length);
            this.otherLineNumbers = iArr2;
        }
        this.otherLineNumbers[i2] = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(MethodVisitor methodVisitor, boolean z) {
        methodVisitor.visitLabel(this);
        if (z && this.lineNumber != 0) {
            methodVisitor.visitLineNumber(this.lineNumber & 65535, this);
            if (this.otherLineNumbers != null) {
                for (int i = 1; i <= this.otherLineNumbers[0]; i++) {
                    methodVisitor.visitLineNumber(this.otherLineNumbers[i], this);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(ByteVector byteVector, int i, boolean z) {
        if ((this.f4145b & 4) == 0) {
            if (z) {
                addForwardReference(i, 536870912, byteVector.f4134b);
                byteVector.putInt(-1);
                return;
            } else {
                addForwardReference(i, 268435456, byteVector.f4134b);
                byteVector.putShort(-1);
                return;
            }
        }
        if (z) {
            byteVector.putInt(this.c - i);
        } else {
            byteVector.putShort(this.c - i);
        }
    }

    private void addForwardReference(int i, int i2, int i3) {
        if (this.forwardReferences == null) {
            this.forwardReferences = new int[6];
        }
        int i4 = this.forwardReferences[0];
        if (i4 + 2 >= this.forwardReferences.length) {
            int[] iArr = new int[this.forwardReferences.length + 6];
            System.arraycopy(this.forwardReferences, 0, iArr, 0, this.forwardReferences.length);
            this.forwardReferences = iArr;
        }
        int i5 = i4 + 1;
        this.forwardReferences[i5] = i;
        int i6 = i5 + 1;
        this.forwardReferences[i6] = i2 | i3;
        this.forwardReferences[0] = i6;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean a(byte[] bArr, int i) {
        this.f4145b = (short) (this.f4145b | 4);
        this.c = i;
        if (this.forwardReferences == null) {
            return false;
        }
        boolean z = false;
        for (int i2 = this.forwardReferences[0]; i2 > 0; i2 -= 2) {
            int i3 = this.forwardReferences[i2 - 1];
            int i4 = this.forwardReferences[i2];
            int i5 = i - i3;
            int i6 = i4 & 268435455;
            if ((i4 & (-268435456)) == 268435456) {
                if (i5 < -32768 || i5 > 32767) {
                    int i7 = bArr[i3] & 255;
                    if (i7 < 198) {
                        bArr[i3] = (byte) (i7 + 49);
                    } else {
                        bArr[i3] = (byte) (i7 + 20);
                    }
                    z = true;
                }
                bArr[i6] = (byte) (i5 >>> 8);
                bArr[i6 + 1] = (byte) i5;
            } else {
                int i8 = i6 + 1;
                bArr[i6] = (byte) (i5 >>> 24);
                int i9 = i8 + 1;
                bArr[i8] = (byte) (i5 >>> 16);
                bArr[i9] = (byte) (i5 >>> 8);
                bArr[i9 + 1] = (byte) i5;
            }
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(short s) {
        Label label = this;
        this.k = f4144a;
        while (label != f4144a) {
            Label label2 = label;
            label = label.k;
            label2.k = null;
            if (label2.g == 0) {
                label2.g = s;
                label = label2.pushSuccessors(label);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(Label label) {
        Label label2 = f4144a;
        Label label3 = this;
        this.k = f4144a;
        while (label3 != f4144a) {
            Label label4 = label3;
            Label label5 = label4.k;
            label4.k = label2;
            label2 = label4;
            if ((label4.f4145b & 64) != 0 && label4.g != label.g) {
                label4.j = new Edge(label4.e, label.j.f4140b, label4.j);
            }
            label3 = label4.pushSuccessors(label5);
        }
        while (label2 != f4144a) {
            Label label6 = label2.k;
            label2.k = null;
            label2 = label6;
        }
    }

    private Label pushSuccessors(Label label) {
        Label label2 = label;
        Edge edge = this.j;
        while (true) {
            Edge edge2 = edge;
            if (edge2 != null) {
                if (!((this.f4145b & 16) != 0 && edge2 == this.j.c) && edge2.f4140b.k == null) {
                    edge2.f4140b.k = label2;
                    label2 = edge2.f4140b;
                }
                edge = edge2.c;
            } else {
                return label2;
            }
        }
    }

    public String toString() {
        return "L" + System.identityHashCode(this);
    }
}
