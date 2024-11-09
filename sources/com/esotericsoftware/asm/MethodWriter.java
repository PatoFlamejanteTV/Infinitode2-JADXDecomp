package com.esotericsoftware.asm;

import net.bytebuddy.description.method.MethodDescription;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:com/esotericsoftware/asm/MethodWriter.class */
class MethodWriter extends MethodVisitor {

    /* renamed from: b, reason: collision with root package name */
    final ClassWriter f1456b;
    private int c;
    private final int d;
    private final int e;
    private final String f;
    String g;
    int h;
    int i;
    int j;
    int[] k;
    private ByteVector l;
    private AnnotationWriter m;
    private AnnotationWriter n;
    private AnnotationWriter U;
    private AnnotationWriter V;
    private AnnotationWriter[] o;
    private AnnotationWriter[] p;
    private int S;
    private Attribute q;
    private ByteVector r;
    private int s;
    private int t;
    private int T;
    private int u;
    private ByteVector v;
    private int w;
    private int[] x;
    private int[] z;
    private int A;
    private Handler B;
    private Handler C;
    private int Z;
    private ByteVector $;
    private int D;
    private ByteVector E;
    private int F;
    private ByteVector G;
    private int H;
    private ByteVector I;
    private int Y;
    private AnnotationWriter W;
    private AnnotationWriter X;
    private Attribute J;
    private boolean K;
    private int L;
    private final int M;
    private Label N;
    private Label O;
    private Label P;
    private int Q;
    private int R;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MethodWriter(ClassWriter classWriter, int i, String str, String str2, String str3, String[] strArr, boolean z, boolean z2) {
        super(327680);
        this.r = new ByteVector();
        if (classWriter.D == null) {
            classWriter.D = this;
        } else {
            classWriter.E.mv = this;
        }
        classWriter.E = this;
        this.f1456b = classWriter;
        this.c = i;
        if (MethodDescription.CONSTRUCTOR_INTERNAL_NAME.equals(str)) {
            this.c |= 524288;
        }
        this.d = classWriter.newUTF8(str);
        this.e = classWriter.newUTF8(str2);
        this.f = str2;
        this.g = str3;
        if (strArr != null && strArr.length > 0) {
            this.j = strArr.length;
            this.k = new int[this.j];
            for (int i2 = 0; i2 < this.j; i2++) {
                this.k[i2] = classWriter.newClass(strArr[i2]);
            }
        }
        this.M = z2 ? 0 : z ? 1 : 2;
        if (z || z2) {
            int argumentsAndReturnSizes = Type.getArgumentsAndReturnSizes(this.f) >> 2;
            argumentsAndReturnSizes = (i & 8) != 0 ? argumentsAndReturnSizes - 1 : argumentsAndReturnSizes;
            this.t = argumentsAndReturnSizes;
            this.T = argumentsAndReturnSizes;
            this.N = new Label();
            this.N.f1454a |= 8;
            visitLabel(this.N);
        }
    }

    @Override // com.esotericsoftware.asm.MethodVisitor
    public void visitParameter(String str, int i) {
        if (this.$ == null) {
            this.$ = new ByteVector();
        }
        this.Z++;
        this.$.putShort(str == null ? 0 : this.f1456b.newUTF8(str)).putShort(i);
    }

    @Override // com.esotericsoftware.asm.MethodVisitor
    public AnnotationVisitor visitAnnotationDefault() {
        this.l = new ByteVector();
        return new AnnotationWriter(this.f1456b, false, this.l, null, 0);
    }

    @Override // com.esotericsoftware.asm.MethodVisitor
    public AnnotationVisitor visitAnnotation(String str, boolean z) {
        ByteVector byteVector = new ByteVector();
        byteVector.putShort(this.f1456b.newUTF8(str)).putShort(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this.f1456b, true, byteVector, byteVector, 2);
        if (z) {
            annotationWriter.g = this.m;
            this.m = annotationWriter;
        } else {
            annotationWriter.g = this.n;
            this.n = annotationWriter;
        }
        return annotationWriter;
    }

    @Override // com.esotericsoftware.asm.MethodVisitor
    public AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String str, boolean z) {
        ByteVector byteVector = new ByteVector();
        AnnotationWriter.a(i, typePath, byteVector);
        byteVector.putShort(this.f1456b.newUTF8(str)).putShort(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this.f1456b, true, byteVector, byteVector, byteVector.f1436b - 2);
        if (z) {
            annotationWriter.g = this.U;
            this.U = annotationWriter;
        } else {
            annotationWriter.g = this.V;
            this.V = annotationWriter;
        }
        return annotationWriter;
    }

    @Override // com.esotericsoftware.asm.MethodVisitor
    public AnnotationVisitor visitParameterAnnotation(int i, String str, boolean z) {
        ByteVector byteVector = new ByteVector();
        if ("Ljava/lang/Synthetic;".equals(str)) {
            this.S = Math.max(this.S, i + 1);
            return new AnnotationWriter(this.f1456b, false, byteVector, null, 0);
        }
        byteVector.putShort(this.f1456b.newUTF8(str)).putShort(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this.f1456b, true, byteVector, byteVector, 2);
        if (z) {
            if (this.o == null) {
                this.o = new AnnotationWriter[Type.getArgumentTypes(this.f).length];
            }
            annotationWriter.g = this.o[i];
            this.o[i] = annotationWriter;
        } else {
            if (this.p == null) {
                this.p = new AnnotationWriter[Type.getArgumentTypes(this.f).length];
            }
            annotationWriter.g = this.p[i];
            this.p[i] = annotationWriter;
        }
        return annotationWriter;
    }

    @Override // com.esotericsoftware.asm.MethodVisitor
    public void visitAttribute(Attribute attribute) {
        if (attribute.isCodeAttribute()) {
            attribute.f1434a = this.J;
            this.J = attribute;
        } else {
            attribute.f1434a = this.q;
            this.q = attribute;
        }
    }

    @Override // com.esotericsoftware.asm.MethodVisitor
    public void visitCode() {
    }

    @Override // com.esotericsoftware.asm.MethodVisitor
    public void visitFrame(int i, int i2, Object[] objArr, int i3, Object[] objArr2) {
        int i4;
        if (this.M == 0) {
            return;
        }
        if (i == -1) {
            if (this.x == null) {
                f();
            }
            this.T = i2;
            int a2 = a(this.r.f1436b, i2, i3);
            for (int i5 = 0; i5 < i2; i5++) {
                if (objArr[i5] instanceof String) {
                    int i6 = a2;
                    a2++;
                    this.z[i6] = 24117248 | this.f1456b.m699c((String) objArr[i5]);
                } else if (objArr[i5] instanceof Integer) {
                    int i7 = a2;
                    a2++;
                    this.z[i7] = ((Integer) objArr[i5]).intValue();
                } else {
                    int i8 = a2;
                    a2++;
                    this.z[i8] = 25165824 | this.f1456b.a("", ((Label) objArr[i5]).c);
                }
            }
            for (int i9 = 0; i9 < i3; i9++) {
                if (objArr2[i9] instanceof String) {
                    int i10 = a2;
                    a2++;
                    this.z[i10] = 24117248 | this.f1456b.m699c((String) objArr2[i9]);
                } else if (objArr2[i9] instanceof Integer) {
                    int i11 = a2;
                    a2++;
                    this.z[i11] = ((Integer) objArr2[i9]).intValue();
                } else {
                    int i12 = a2;
                    a2++;
                    this.z[i12] = 25165824 | this.f1456b.a("", ((Label) objArr2[i9]).c);
                }
            }
            b();
        } else {
            if (this.v == null) {
                this.v = new ByteVector();
                i4 = this.r.f1436b;
            } else {
                int i13 = (this.r.f1436b - this.w) - 1;
                i4 = i13;
                if (i13 < 0) {
                    if (i != 3) {
                        throw new IllegalStateException();
                    }
                    return;
                }
            }
            switch (i) {
                case 0:
                    this.T = i2;
                    this.v.putByte(255).putShort(i4).putShort(i2);
                    for (int i14 = 0; i14 < i2; i14++) {
                        a(objArr[i14]);
                    }
                    this.v.putShort(i3);
                    for (int i15 = 0; i15 < i3; i15++) {
                        a(objArr2[i15]);
                    }
                    break;
                case 1:
                    this.T += i2;
                    this.v.putByte(i2 + User32.VK_ZOOM).putShort(i4);
                    for (int i16 = 0; i16 < i2; i16++) {
                        a(objArr[i16]);
                    }
                    break;
                case 2:
                    this.T -= i2;
                    this.v.putByte(User32.VK_ZOOM - i2).putShort(i4);
                    break;
                case 3:
                    if (i4 < 64) {
                        this.v.putByte(i4);
                        break;
                    } else {
                        this.v.putByte(User32.VK_ZOOM).putShort(i4);
                        break;
                    }
                case 4:
                    if (i4 < 64) {
                        this.v.putByte(i4 + 64);
                    } else {
                        this.v.putByte(User32.VK_CRSEL).putShort(i4);
                    }
                    a(objArr2[0]);
                    break;
            }
            this.w = this.r.f1436b;
            this.u++;
        }
        this.s = Math.max(this.s, i3);
        this.t = Math.max(this.t, this.T);
    }

    @Override // com.esotericsoftware.asm.MethodVisitor
    public void visitInsn(int i) {
        this.Y = this.r.f1436b;
        this.r.putByte(i);
        if (this.P != null) {
            if (this.M == 0) {
                this.P.h.a(i, 0, (ClassWriter) null, (Item) null);
            } else {
                int i2 = this.Q + Frame.f1446a[i];
                if (i2 > this.R) {
                    this.R = i2;
                }
                this.Q = i2;
            }
            if ((i < 172 || i > 177) && i != 191) {
                return;
            }
            e();
        }
    }

    @Override // com.esotericsoftware.asm.MethodVisitor
    public void visitIntInsn(int i, int i2) {
        this.Y = this.r.f1436b;
        if (this.P != null) {
            if (this.M == 0) {
                this.P.h.a(i, i2, (ClassWriter) null, (Item) null);
            } else if (i != 188) {
                int i3 = this.Q + 1;
                if (i3 > this.R) {
                    this.R = i3;
                }
                this.Q = i3;
            }
        }
        if (i == 17) {
            this.r.b(i, i2);
        } else {
            this.r.a(i, i2);
        }
    }

    @Override // com.esotericsoftware.asm.MethodVisitor
    public void visitVarInsn(int i, int i2) {
        this.Y = this.r.f1436b;
        if (this.P != null) {
            if (this.M == 0) {
                this.P.h.a(i, i2, (ClassWriter) null, (Item) null);
            } else if (i == 169) {
                this.P.f1454a |= 256;
                this.P.f = this.Q;
                e();
            } else {
                int i3 = this.Q + Frame.f1446a[i];
                if (i3 > this.R) {
                    this.R = i3;
                }
                this.Q = i3;
            }
        }
        if (this.M != 2) {
            int i4 = (i == 22 || i == 24 || i == 55 || i == 57) ? i2 + 2 : i2 + 1;
            if (i4 > this.t) {
                this.t = i4;
            }
        }
        if (i2 < 4 && i != 169) {
            this.r.putByte(i < 54 ? 26 + ((i - 21) << 2) + i2 : 59 + ((i - 54) << 2) + i2);
        } else if (i2 >= 256) {
            this.r.putByte(196).b(i, i2);
        } else {
            this.r.a(i, i2);
        }
        if (i < 54 || this.M != 0 || this.A <= 0) {
            return;
        }
        visitLabel(new Label());
    }

    @Override // com.esotericsoftware.asm.MethodVisitor
    public void visitTypeInsn(int i, String str) {
        this.Y = this.r.f1436b;
        Item a2 = this.f1456b.a(str);
        if (this.P != null) {
            if (this.M == 0) {
                this.P.h.a(i, this.r.f1436b, this.f1456b, a2);
            } else if (i == 187) {
                int i2 = this.Q + 1;
                if (i2 > this.R) {
                    this.R = i2;
                }
                this.Q = i2;
            }
        }
        this.r.b(i, a2.f1452a);
    }

    @Override // com.esotericsoftware.asm.MethodVisitor
    public void visitFieldInsn(int i, String str, String str2, String str3) {
        int i2;
        this.Y = this.r.f1436b;
        Item a2 = this.f1456b.a(str, str2, str3);
        if (this.P != null) {
            if (this.M == 0) {
                this.P.h.a(i, 0, this.f1456b, a2);
            } else {
                char charAt = str3.charAt(0);
                switch (i) {
                    case 178:
                        i2 = this.Q + ((charAt == 'D' || charAt == 'J') ? 2 : 1);
                        break;
                    case 179:
                        i2 = this.Q + ((charAt == 'D' || charAt == 'J') ? -2 : -1);
                        break;
                    case 180:
                        i2 = this.Q + ((charAt == 'D' || charAt == 'J') ? 1 : 0);
                        break;
                    default:
                        i2 = this.Q + ((charAt == 'D' || charAt == 'J') ? -3 : -2);
                        break;
                }
                if (i2 > this.R) {
                    this.R = i2;
                }
                this.Q = i2;
            }
        }
        this.r.b(i, a2.f1452a);
    }

    @Override // com.esotericsoftware.asm.MethodVisitor
    public void visitMethodInsn(int i, String str, String str2, String str3, boolean z) {
        this.Y = this.r.f1436b;
        Item a2 = this.f1456b.a(str, str2, str3, z);
        int i2 = a2.c;
        if (this.P != null) {
            if (this.M == 0) {
                this.P.h.a(i, 0, this.f1456b, a2);
            } else {
                if (i2 == 0) {
                    i2 = Type.getArgumentsAndReturnSizes(str3);
                    a2.c = i2;
                }
                int i3 = i == 184 ? (this.Q - (i2 >> 2)) + (i2 & 3) + 1 : (this.Q - (i2 >> 2)) + (i2 & 3);
                if (i3 > this.R) {
                    this.R = i3;
                }
                this.Q = i3;
            }
        }
        if (i != 185) {
            this.r.b(i, a2.f1452a);
            return;
        }
        if (i2 == 0) {
            i2 = Type.getArgumentsAndReturnSizes(str3);
            a2.c = i2;
        }
        this.r.b(185, a2.f1452a).a(i2 >> 2, 0);
    }

    @Override // com.esotericsoftware.asm.MethodVisitor
    public void visitInvokeDynamicInsn(String str, String str2, Handle handle, Object... objArr) {
        this.Y = this.r.f1436b;
        Item a2 = this.f1456b.a(str, str2, handle, objArr);
        int i = a2.c;
        if (this.P != null) {
            if (this.M == 0) {
                this.P.h.a(186, 0, this.f1456b, a2);
            } else {
                if (i == 0) {
                    i = Type.getArgumentsAndReturnSizes(str2);
                    a2.c = i;
                }
                int i2 = (this.Q - (i >> 2)) + (i & 3) + 1;
                if (i2 > this.R) {
                    this.R = i2;
                }
                this.Q = i2;
            }
        }
        this.r.b(186, a2.f1452a);
        this.r.putShort(0);
    }

    @Override // com.esotericsoftware.asm.MethodVisitor
    public void visitJumpInsn(int i, Label label) {
        this.Y = this.r.f1436b;
        Label label2 = null;
        if (this.P != null) {
            if (this.M == 0) {
                this.P.h.a(i, 0, (ClassWriter) null, (Item) null);
                label.a().f1454a |= 16;
                a(0, label);
                if (i != 167) {
                    label2 = new Label();
                }
            } else if (i == 168) {
                if ((label.f1454a & 512) == 0) {
                    label.f1454a |= 512;
                    this.L++;
                }
                this.P.f1454a |= 128;
                a(this.Q + 1, label);
                label2 = new Label();
            } else {
                this.Q += Frame.f1446a[i];
                a(this.Q, label);
            }
        }
        if ((label.f1454a & 2) == 0 || label.c - this.r.f1436b >= -32768) {
            this.r.putByte(i);
            label.a(this, this.r, this.r.f1436b - 1, false);
        } else {
            if (i == 167) {
                this.r.putByte(200);
            } else if (i == 168) {
                this.r.putByte(201);
            } else {
                if (label2 != null) {
                    label2.f1454a |= 16;
                }
                this.r.putByte(i <= 166 ? ((i + 1) ^ 1) - 1 : i ^ 1);
                this.r.putShort(8);
                this.r.putByte(200);
            }
            label.a(this, this.r, this.r.f1436b - 1, true);
        }
        if (this.P != null) {
            if (label2 != null) {
                visitLabel(label2);
            }
            if (i == 167) {
                e();
            }
        }
    }

    @Override // com.esotericsoftware.asm.MethodVisitor
    public void visitLabel(Label label) {
        this.K |= label.a(this, this.r.f1436b, this.r.f1435a);
        if ((label.f1454a & 1) != 0) {
            return;
        }
        if (this.M != 0) {
            if (this.M == 1) {
                if (this.P != null) {
                    this.P.g = this.R;
                    a(this.Q, label);
                }
                this.P = label;
                this.Q = 0;
                this.R = 0;
                if (this.O != null) {
                    this.O.i = label;
                }
                this.O = label;
                return;
            }
            return;
        }
        if (this.P != null) {
            if (label.c == this.P.c) {
                this.P.f1454a |= label.f1454a & 16;
                label.h = this.P.h;
                return;
            }
            a(0, label);
        }
        this.P = label;
        if (label.h == null) {
            label.h = new Frame();
            label.h.f1447b = label;
        }
        if (this.O != null) {
            if (label.c == this.O.c) {
                this.O.f1454a |= label.f1454a & 16;
                label.h = this.O.h;
                this.P = this.O;
                return;
            }
            this.O.i = label;
        }
        this.O = label;
    }

    @Override // com.esotericsoftware.asm.MethodVisitor
    public void visitLdcInsn(Object obj) {
        this.Y = this.r.f1436b;
        Item a2 = this.f1456b.a(obj);
        if (this.P != null) {
            if (this.M == 0) {
                this.P.h.a(18, 0, this.f1456b, a2);
            } else {
                int i = (a2.f1453b == 5 || a2.f1453b == 6) ? this.Q + 2 : this.Q + 1;
                if (i > this.R) {
                    this.R = i;
                }
                this.Q = i;
            }
        }
        int i2 = a2.f1452a;
        if (a2.f1453b == 5 || a2.f1453b == 6) {
            this.r.b(20, i2);
        } else if (i2 >= 256) {
            this.r.b(19, i2);
        } else {
            this.r.a(18, i2);
        }
    }

    @Override // com.esotericsoftware.asm.MethodVisitor
    public void visitIincInsn(int i, int i2) {
        int i3;
        this.Y = this.r.f1436b;
        if (this.P != null && this.M == 0) {
            this.P.h.a(132, i, (ClassWriter) null, (Item) null);
        }
        if (this.M != 2 && (i3 = i + 1) > this.t) {
            this.t = i3;
        }
        if (i > 255 || i2 > 127 || i2 < -128) {
            this.r.putByte(196).b(132, i).putShort(i2);
        } else {
            this.r.putByte(132).a(i, i2);
        }
    }

    @Override // com.esotericsoftware.asm.MethodVisitor
    public void visitTableSwitchInsn(int i, int i2, Label label, Label... labelArr) {
        this.Y = this.r.f1436b;
        int i3 = this.r.f1436b;
        this.r.putByte(170);
        this.r.putByteArray(null, 0, (4 - (this.r.f1436b % 4)) % 4);
        label.a(this, this.r, i3, true);
        this.r.putInt(i).putInt(i2);
        for (Label label2 : labelArr) {
            label2.a(this, this.r, i3, true);
        }
        a(label, labelArr);
    }

    @Override // com.esotericsoftware.asm.MethodVisitor
    public void visitLookupSwitchInsn(Label label, int[] iArr, Label[] labelArr) {
        this.Y = this.r.f1436b;
        int i = this.r.f1436b;
        this.r.putByte(171);
        this.r.putByteArray(null, 0, (4 - (this.r.f1436b % 4)) % 4);
        label.a(this, this.r, i, true);
        this.r.putInt(labelArr.length);
        for (int i2 = 0; i2 < labelArr.length; i2++) {
            this.r.putInt(iArr[i2]);
            labelArr[i2].a(this, this.r, i, true);
        }
        a(label, labelArr);
    }

    private void a(Label label, Label[] labelArr) {
        if (this.P != null) {
            if (this.M == 0) {
                this.P.h.a(171, 0, (ClassWriter) null, (Item) null);
                a(0, label);
                label.a().f1454a |= 16;
                for (int i = 0; i < labelArr.length; i++) {
                    a(0, labelArr[i]);
                    labelArr[i].a().f1454a |= 16;
                }
            } else {
                this.Q--;
                a(this.Q, label);
                for (Label label2 : labelArr) {
                    a(this.Q, label2);
                }
            }
            e();
        }
    }

    @Override // com.esotericsoftware.asm.MethodVisitor
    public void visitMultiANewArrayInsn(String str, int i) {
        this.Y = this.r.f1436b;
        Item a2 = this.f1456b.a(str);
        if (this.P != null) {
            if (this.M == 0) {
                this.P.h.a(197, i, this.f1456b, a2);
            } else {
                this.Q += 1 - i;
            }
        }
        this.r.b(197, a2.f1452a).putByte(i);
    }

    @Override // com.esotericsoftware.asm.MethodVisitor
    public AnnotationVisitor visitInsnAnnotation(int i, TypePath typePath, String str, boolean z) {
        ByteVector byteVector = new ByteVector();
        AnnotationWriter.a((i & (-16776961)) | (this.Y << 8), typePath, byteVector);
        byteVector.putShort(this.f1456b.newUTF8(str)).putShort(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this.f1456b, true, byteVector, byteVector, byteVector.f1436b - 2);
        if (z) {
            annotationWriter.g = this.W;
            this.W = annotationWriter;
        } else {
            annotationWriter.g = this.X;
            this.X = annotationWriter;
        }
        return annotationWriter;
    }

    @Override // com.esotericsoftware.asm.MethodVisitor
    public void visitTryCatchBlock(Label label, Label label2, Label label3, String str) {
        this.A++;
        Handler handler = new Handler();
        handler.f1450a = label;
        handler.f1451b = label2;
        handler.c = label3;
        handler.d = str;
        handler.e = str != null ? this.f1456b.newClass(str) : 0;
        if (this.C == null) {
            this.B = handler;
        } else {
            this.C.f = handler;
        }
        this.C = handler;
    }

    @Override // com.esotericsoftware.asm.MethodVisitor
    public AnnotationVisitor visitTryCatchAnnotation(int i, TypePath typePath, String str, boolean z) {
        ByteVector byteVector = new ByteVector();
        AnnotationWriter.a(i, typePath, byteVector);
        byteVector.putShort(this.f1456b.newUTF8(str)).putShort(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this.f1456b, true, byteVector, byteVector, byteVector.f1436b - 2);
        if (z) {
            annotationWriter.g = this.W;
            this.W = annotationWriter;
        } else {
            annotationWriter.g = this.X;
            this.X = annotationWriter;
        }
        return annotationWriter;
    }

    @Override // com.esotericsoftware.asm.MethodVisitor
    public void visitLocalVariable(String str, String str2, String str3, Label label, Label label2, int i) {
        if (str3 != null) {
            if (this.G == null) {
                this.G = new ByteVector();
            }
            this.F++;
            this.G.putShort(label.c).putShort(label2.c - label.c).putShort(this.f1456b.newUTF8(str)).putShort(this.f1456b.newUTF8(str3)).putShort(i);
        }
        if (this.E == null) {
            this.E = new ByteVector();
        }
        this.D++;
        this.E.putShort(label.c).putShort(label2.c - label.c).putShort(this.f1456b.newUTF8(str)).putShort(this.f1456b.newUTF8(str2)).putShort(i);
        if (this.M != 2) {
            char charAt = str2.charAt(0);
            int i2 = i + ((charAt == 'J' || charAt == 'D') ? 2 : 1);
            if (i2 > this.t) {
                this.t = i2;
            }
        }
    }

    @Override // com.esotericsoftware.asm.MethodVisitor
    public AnnotationVisitor visitLocalVariableAnnotation(int i, TypePath typePath, Label[] labelArr, Label[] labelArr2, int[] iArr, String str, boolean z) {
        ByteVector byteVector = new ByteVector();
        byteVector.putByte(i >>> 24).putShort(labelArr.length);
        for (int i2 = 0; i2 < labelArr.length; i2++) {
            byteVector.putShort(labelArr[i2].c).putShort(labelArr2[i2].c - labelArr[i2].c).putShort(iArr[i2]);
        }
        if (typePath == null) {
            byteVector.putByte(0);
        } else {
            byteVector.putByteArray(typePath.f1459a, typePath.f1460b, (typePath.f1459a[typePath.f1460b] << 1) + 1);
        }
        byteVector.putShort(this.f1456b.newUTF8(str)).putShort(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this.f1456b, true, byteVector, byteVector, byteVector.f1436b - 2);
        if (z) {
            annotationWriter.g = this.W;
            this.W = annotationWriter;
        } else {
            annotationWriter.g = this.X;
            this.X = annotationWriter;
        }
        return annotationWriter;
    }

    @Override // com.esotericsoftware.asm.MethodVisitor
    public void visitLineNumber(int i, Label label) {
        if (this.I == null) {
            this.I = new ByteVector();
        }
        this.H++;
        this.I.putShort(label.c);
        this.I.putShort(i);
    }

    @Override // com.esotericsoftware.asm.MethodVisitor
    public void visitMaxs(int i, int i2) {
        if (this.K) {
            d();
        }
        if (this.M != 0) {
            if (this.M != 1) {
                this.s = i;
                this.t = i2;
                return;
            }
            Handler handler = this.B;
            while (true) {
                Handler handler2 = handler;
                if (handler2 == null) {
                    break;
                }
                Label label = handler2.c;
                Label label2 = handler2.f1451b;
                for (Label label3 = handler2.f1450a; label3 != label2; label3 = label3.i) {
                    Edge edge = new Edge();
                    edge.f1443a = Integer.MAX_VALUE;
                    edge.f1444b = label;
                    if ((label3.f1454a & 128) == 0) {
                        edge.c = label3.j;
                        label3.j = edge;
                    } else {
                        edge.c = label3.j.c.c;
                        label3.j.c.c = edge;
                    }
                }
                handler = handler2.f;
            }
            if (this.L > 0) {
                int i3 = 0;
                this.N.b(null, 1L, this.L);
                Label label4 = this.N;
                while (true) {
                    Label label5 = label4;
                    if (label5 == null) {
                        break;
                    }
                    if ((label5.f1454a & 128) != 0) {
                        Label label6 = label5.j.c.f1444b;
                        if ((label6.f1454a & 1024) == 0) {
                            i3++;
                            label6.b(null, ((i3 / 32) << 32) | (1 << (i3 % 32)), this.L);
                        }
                    }
                    label4 = label5.i;
                }
                Label label7 = this.N;
                while (true) {
                    Label label8 = label7;
                    if (label8 == null) {
                        break;
                    }
                    if ((label8.f1454a & 128) != 0) {
                        Label label9 = this.N;
                        while (true) {
                            Label label10 = label9;
                            if (label10 == null) {
                                break;
                            }
                            label10.f1454a &= -2049;
                            label9 = label10.i;
                        }
                        label8.j.c.f1444b.b(label8, 0L, this.L);
                    }
                    label7 = label8.i;
                }
            }
            int i4 = 0;
            Label label11 = this.N;
            while (label11 != null) {
                Label label12 = label11;
                label11 = label11.k;
                int i5 = label12.f;
                int i6 = i5 + label12.g;
                if (i6 > i4) {
                    i4 = i6;
                }
                Edge edge2 = label12.j;
                if ((label12.f1454a & 128) != 0) {
                    edge2 = edge2.c;
                }
                while (edge2 != null) {
                    Label label13 = edge2.f1444b;
                    if ((label13.f1454a & 8) == 0) {
                        label13.f = edge2.f1443a == Integer.MAX_VALUE ? 1 : i5 + edge2.f1443a;
                        label13.f1454a |= 8;
                        label13.k = label11;
                        label11 = label13;
                    }
                    edge2 = edge2.c;
                }
            }
            this.s = Math.max(i, i4);
            return;
        }
        Handler handler3 = this.B;
        while (true) {
            Handler handler4 = handler3;
            if (handler4 == null) {
                break;
            }
            Label a2 = handler4.c.a();
            Label a3 = handler4.f1451b.a();
            int m699c = 24117248 | this.f1456b.m699c(handler4.d == null ? "java/lang/Throwable" : handler4.d);
            a2.f1454a |= 16;
            for (Label a4 = handler4.f1450a.a(); a4 != a3; a4 = a4.i) {
                Edge edge3 = new Edge();
                edge3.f1443a = m699c;
                edge3.f1444b = a2;
                edge3.c = a4.j;
                a4.j = edge3;
            }
            handler3 = handler4.f;
        }
        Frame frame = this.N.h;
        frame.a(this.f1456b, this.c, Type.getArgumentTypes(this.f), this.t);
        b(frame);
        int i7 = 0;
        Label label14 = this.N;
        while (label14 != null) {
            Label label15 = label14;
            label14 = label14.k;
            label15.k = null;
            Frame frame2 = label15.h;
            if ((label15.f1454a & 16) != 0) {
                label15.f1454a |= 32;
            }
            label15.f1454a |= 64;
            int length = frame2.d.length + label15.g;
            if (length > i7) {
                i7 = length;
            }
            Edge edge4 = label15.j;
            while (true) {
                Edge edge5 = edge4;
                if (edge5 != null) {
                    Label a5 = edge5.f1444b.a();
                    if (frame2.a(this.f1456b, a5.h, edge5.f1443a) && a5.k == null) {
                        a5.k = label14;
                        label14 = a5;
                    }
                    edge4 = edge5.c;
                }
            }
        }
        Label label16 = this.N;
        while (true) {
            Label label17 = label16;
            if (label17 == null) {
                break;
            }
            Frame frame3 = label17.h;
            if ((label17.f1454a & 32) != 0) {
                b(frame3);
            }
            if ((label17.f1454a & 64) == 0) {
                Label label18 = label17.i;
                int i8 = label17.c;
                int i9 = (label18 == null ? this.r.f1436b : label18.c) - 1;
                if (i9 >= i8) {
                    i7 = Math.max(i7, 1);
                    for (int i10 = i8; i10 < i9; i10++) {
                        this.r.f1435a[i10] = 0;
                    }
                    this.r.f1435a[i9] = -65;
                    this.z[a(i8, 0, 1)] = 24117248 | this.f1456b.m699c("java/lang/Throwable");
                    b();
                    this.B = Handler.a(this.B, label17, label18);
                }
            }
            label16 = label17.i;
        }
        this.A = 0;
        for (Handler handler5 = this.B; handler5 != null; handler5 = handler5.f) {
            this.A++;
        }
        this.s = i7;
    }

    @Override // com.esotericsoftware.asm.MethodVisitor
    public void visitEnd() {
    }

    private void a(int i, Label label) {
        Edge edge = new Edge();
        edge.f1443a = i;
        edge.f1444b = label;
        edge.c = this.P.j;
        this.P.j = edge;
    }

    private void e() {
        if (this.M == 0) {
            Label label = new Label();
            label.h = new Frame();
            label.h.f1447b = label;
            label.a(this, this.r.f1436b, this.r.f1435a);
            this.O.i = label;
            this.O = label;
        } else {
            this.P.g = this.R;
        }
        this.P = null;
    }

    private void b(Frame frame) {
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int[] iArr = frame.c;
        int[] iArr2 = frame.d;
        int i4 = 0;
        while (i4 < iArr.length) {
            int i5 = iArr[i4];
            if (i5 == 16777216) {
                i++;
            } else {
                i2 += i + 1;
                i = 0;
            }
            if (i5 == 16777220 || i5 == 16777219) {
                i4++;
            }
            i4++;
        }
        int i6 = 0;
        while (i6 < iArr2.length) {
            int i7 = iArr2[i6];
            i3++;
            if (i7 == 16777220 || i7 == 16777219) {
                i6++;
            }
            i6++;
        }
        int a2 = a(frame.f1447b.c, i2, i3);
        int i8 = 0;
        while (i2 > 0) {
            int i9 = iArr[i8];
            int i10 = a2;
            a2++;
            this.z[i10] = i9;
            if (i9 == 16777220 || i9 == 16777219) {
                i8++;
            }
            i8++;
            i2--;
        }
        int i11 = 0;
        while (i11 < iArr2.length) {
            int i12 = iArr2[i11];
            int i13 = a2;
            a2++;
            this.z[i13] = i12;
            if (i12 == 16777220 || i12 == 16777219) {
                i11++;
            }
            i11++;
        }
        b();
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x0196, code lost:            r8.z[1] = r9 - 3;        b();     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x01a3, code lost:            return;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void f() {
        /*
            Method dump skipped, instructions count: 420
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.esotericsoftware.asm.MethodWriter.f():void");
    }

    private int a(int i, int i2, int i3) {
        int i4 = i2 + 3 + i3;
        if (this.z == null || this.z.length < i4) {
            this.z = new int[i4];
        }
        this.z[0] = i;
        this.z[1] = i2;
        this.z[2] = i3;
        return 3;
    }

    private void b() {
        if (this.x != null) {
            if (this.v == null) {
                this.v = new ByteVector();
            }
            c();
            this.u++;
        }
        this.x = this.z;
        this.z = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v26 */
    /* JADX WARN: Type inference failed for: r0v27 */
    /* JADX WARN: Type inference failed for: r0v28 */
    /* JADX WARN: Type inference failed for: r0v29 */
    /* JADX WARN: Type inference failed for: r0v77 */
    /* JADX WARN: Type inference failed for: r0v78 */
    /* JADX WARN: Type inference failed for: r0v79 */
    private void c() {
        int i = this.z[1];
        int i2 = this.z[2];
        if ((this.f1456b.f1440b & 65535) < 50) {
            this.v.putShort(this.z[0]).putShort(i);
            a(3, i + 3);
            this.v.putShort(i2);
            a(i + 3, i + 3 + i2);
            return;
        }
        int i3 = this.x[1];
        boolean z = 255;
        int i4 = 0;
        int i5 = this.u == 0 ? this.z[0] : (this.z[0] - this.x[0]) - 1;
        if (i2 == 0) {
            int i6 = i - i3;
            i4 = i6;
            switch (i6) {
                case -3:
                case -2:
                case -1:
                    z = 248;
                    i3 = i;
                    break;
                case 0:
                    z = i5 < 64 ? 0 : 251;
                    break;
                case 1:
                case 2:
                case 3:
                    z = 252;
                    break;
            }
        } else if (i == i3 && i2 == 1) {
            z = i5 < 63 ? 64 : 247;
        }
        if (z != 255) {
            int i7 = 3;
            int i8 = 0;
            while (true) {
                if (i8 < i3) {
                    if (this.z[i7] != this.x[i7]) {
                        z = 255;
                    } else {
                        i7++;
                        i8++;
                    }
                }
            }
        }
        switch (z) {
            case false:
                this.v.putByte(i5);
                return;
            case true:
                this.v.putByte(i5 + 64);
                a(i + 3, i + 4);
                return;
            case User32.VK_CRSEL /* 247 */:
                this.v.putByte(User32.VK_CRSEL).putShort(i5);
                a(i + 3, i + 4);
                return;
            case User32.VK_EXSEL /* 248 */:
                this.v.putByte(i4 + User32.VK_ZOOM).putShort(i5);
                return;
            case User32.VK_ZOOM /* 251 */:
                this.v.putByte(User32.VK_ZOOM).putShort(i5);
                return;
            case User32.VK_NONAME /* 252 */:
                this.v.putByte(i4 + User32.VK_ZOOM).putShort(i5);
                a(i3 + 3, i + 3);
                return;
            default:
                this.v.putByte(255).putShort(i5).putShort(i);
                a(3, i + 3);
                this.v.putShort(i2);
                a(i + 3, i + 3 + i2);
                return;
        }
    }

    private void a(int i, int i2) {
        for (int i3 = i; i3 < i2; i3++) {
            int i4 = this.z[i3];
            int i5 = i4 & (-268435456);
            if (i5 == 0) {
                int i6 = i4 & GL11.GL_ALL_ATTRIB_BITS;
                switch (i4 & 267386880) {
                    case 24117248:
                        this.v.putByte(7).putShort(this.f1456b.newClass(this.f1456b.H[i6].g));
                        break;
                    case 25165824:
                        this.v.putByte(8).putShort(this.f1456b.H[i6].c);
                        break;
                    default:
                        this.v.putByte(i6);
                        break;
                }
            } else {
                StringBuffer stringBuffer = new StringBuffer();
                int i7 = i5 >> 28;
                while (true) {
                    int i8 = i7;
                    i7--;
                    if (i8 > 0) {
                        stringBuffer.append('[');
                    } else {
                        if ((i4 & 267386880) != 24117248) {
                            switch (i4 & 15) {
                                case 1:
                                    stringBuffer.append('I');
                                    break;
                                case 2:
                                    stringBuffer.append('F');
                                    break;
                                case 3:
                                    stringBuffer.append('D');
                                    break;
                                case 4:
                                case 5:
                                case 6:
                                case 7:
                                case 8:
                                default:
                                    stringBuffer.append('J');
                                    break;
                                case 9:
                                    stringBuffer.append('Z');
                                    break;
                                case 10:
                                    stringBuffer.append('B');
                                    break;
                                case 11:
                                    stringBuffer.append('C');
                                    break;
                                case 12:
                                    stringBuffer.append('S');
                                    break;
                            }
                        } else {
                            stringBuffer.append('L');
                            stringBuffer.append(this.f1456b.H[i4 & GL11.GL_ALL_ATTRIB_BITS].g);
                            stringBuffer.append(';');
                        }
                        this.v.putByte(7).putShort(this.f1456b.newClass(stringBuffer.toString()));
                    }
                }
            }
        }
    }

    private void a(Object obj) {
        if (obj instanceof String) {
            this.v.putByte(7).putShort(this.f1456b.newClass((String) obj));
        } else if (obj instanceof Integer) {
            this.v.putByte(((Integer) obj).intValue());
        } else {
            this.v.putByte(8).putShort(((Label) obj).c);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a() {
        if (this.h != 0) {
            return 6 + this.i;
        }
        int i = 8;
        if (this.r.f1436b > 0) {
            if (this.r.f1436b > 65535) {
                throw new RuntimeException("Method code too large!");
            }
            this.f1456b.newUTF8("Code");
            i = 8 + 18 + this.r.f1436b + (8 * this.A);
            if (this.E != null) {
                this.f1456b.newUTF8("LocalVariableTable");
                i += 8 + this.E.f1436b;
            }
            if (this.G != null) {
                this.f1456b.newUTF8("LocalVariableTypeTable");
                i += 8 + this.G.f1436b;
            }
            if (this.I != null) {
                this.f1456b.newUTF8("LineNumberTable");
                i += 8 + this.I.f1436b;
            }
            if (this.v != null) {
                this.f1456b.newUTF8((this.f1456b.f1440b & 65535) >= 50 ? "StackMapTable" : "StackMap");
                i += 8 + this.v.f1436b;
            }
            if (this.W != null) {
                this.f1456b.newUTF8("RuntimeVisibleTypeAnnotations");
                i += 8 + this.W.a();
            }
            if (this.X != null) {
                this.f1456b.newUTF8("RuntimeInvisibleTypeAnnotations");
                i += 8 + this.X.a();
            }
            if (this.J != null) {
                i += this.J.a(this.f1456b, this.r.f1435a, this.r.f1436b, this.s, this.t);
            }
        }
        if (this.j > 0) {
            this.f1456b.newUTF8("Exceptions");
            i += 8 + (2 * this.j);
        }
        if ((this.c & 4096) != 0 && ((this.f1456b.f1440b & 65535) < 49 || (this.c & 262144) != 0)) {
            this.f1456b.newUTF8("Synthetic");
            i += 6;
        }
        if ((this.c & 131072) != 0) {
            this.f1456b.newUTF8("Deprecated");
            i += 6;
        }
        if (this.g != null) {
            this.f1456b.newUTF8("Signature");
            this.f1456b.newUTF8(this.g);
            i += 8;
        }
        if (this.$ != null) {
            this.f1456b.newUTF8("MethodParameters");
            i += 7 + this.$.f1436b;
        }
        if (this.l != null) {
            this.f1456b.newUTF8("AnnotationDefault");
            i += 6 + this.l.f1436b;
        }
        if (this.m != null) {
            this.f1456b.newUTF8("RuntimeVisibleAnnotations");
            i += 8 + this.m.a();
        }
        if (this.n != null) {
            this.f1456b.newUTF8("RuntimeInvisibleAnnotations");
            i += 8 + this.n.a();
        }
        if (this.U != null) {
            this.f1456b.newUTF8("RuntimeVisibleTypeAnnotations");
            i += 8 + this.U.a();
        }
        if (this.V != null) {
            this.f1456b.newUTF8("RuntimeInvisibleTypeAnnotations");
            i += 8 + this.V.a();
        }
        if (this.o != null) {
            this.f1456b.newUTF8("RuntimeVisibleParameterAnnotations");
            i += 7 + (2 * (this.o.length - this.S));
            for (int length = this.o.length - 1; length >= this.S; length--) {
                i += this.o[length] == null ? 0 : this.o[length].a();
            }
        }
        if (this.p != null) {
            this.f1456b.newUTF8("RuntimeInvisibleParameterAnnotations");
            i += 7 + (2 * (this.p.length - this.S));
            for (int length2 = this.p.length - 1; length2 >= this.S; length2--) {
                i += this.p[length2] == null ? 0 : this.p[length2].a();
            }
        }
        if (this.q != null) {
            i += this.q.a(this.f1456b, null, 0, -1, -1);
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(ByteVector byteVector) {
        byteVector.putShort(this.c & ((917504 | ((this.c & 262144) / 64)) ^ (-1))).putShort(this.d).putShort(this.e);
        if (this.h != 0) {
            byteVector.putByteArray(this.f1456b.M.f1437b, this.h, this.i);
            return;
        }
        int i = this.r.f1436b > 0 ? 0 + 1 : 0;
        if (this.j > 0) {
            i++;
        }
        if ((this.c & 4096) != 0 && ((this.f1456b.f1440b & 65535) < 49 || (this.c & 262144) != 0)) {
            i++;
        }
        if ((this.c & 131072) != 0) {
            i++;
        }
        if (this.g != null) {
            i++;
        }
        if (this.$ != null) {
            i++;
        }
        if (this.l != null) {
            i++;
        }
        if (this.m != null) {
            i++;
        }
        if (this.n != null) {
            i++;
        }
        if (this.U != null) {
            i++;
        }
        if (this.V != null) {
            i++;
        }
        if (this.o != null) {
            i++;
        }
        if (this.p != null) {
            i++;
        }
        if (this.q != null) {
            i += this.q.a();
        }
        byteVector.putShort(i);
        if (this.r.f1436b > 0) {
            int i2 = 12 + this.r.f1436b + (8 * this.A);
            if (this.E != null) {
                i2 += 8 + this.E.f1436b;
            }
            if (this.G != null) {
                i2 += 8 + this.G.f1436b;
            }
            if (this.I != null) {
                i2 += 8 + this.I.f1436b;
            }
            if (this.v != null) {
                i2 += 8 + this.v.f1436b;
            }
            if (this.W != null) {
                i2 += 8 + this.W.a();
            }
            if (this.X != null) {
                i2 += 8 + this.X.a();
            }
            if (this.J != null) {
                i2 += this.J.a(this.f1456b, this.r.f1435a, this.r.f1436b, this.s, this.t);
            }
            byteVector.putShort(this.f1456b.newUTF8("Code")).putInt(i2);
            byteVector.putShort(this.s).putShort(this.t);
            byteVector.putInt(this.r.f1436b).putByteArray(this.r.f1435a, 0, this.r.f1436b);
            byteVector.putShort(this.A);
            if (this.A > 0) {
                Handler handler = this.B;
                while (true) {
                    Handler handler2 = handler;
                    if (handler2 == null) {
                        break;
                    }
                    byteVector.putShort(handler2.f1450a.c).putShort(handler2.f1451b.c).putShort(handler2.c.c).putShort(handler2.e);
                    handler = handler2.f;
                }
            }
            int i3 = this.E != null ? 0 + 1 : 0;
            if (this.G != null) {
                i3++;
            }
            if (this.I != null) {
                i3++;
            }
            if (this.v != null) {
                i3++;
            }
            if (this.W != null) {
                i3++;
            }
            if (this.X != null) {
                i3++;
            }
            if (this.J != null) {
                i3 += this.J.a();
            }
            byteVector.putShort(i3);
            if (this.E != null) {
                byteVector.putShort(this.f1456b.newUTF8("LocalVariableTable"));
                byteVector.putInt(this.E.f1436b + 2).putShort(this.D);
                byteVector.putByteArray(this.E.f1435a, 0, this.E.f1436b);
            }
            if (this.G != null) {
                byteVector.putShort(this.f1456b.newUTF8("LocalVariableTypeTable"));
                byteVector.putInt(this.G.f1436b + 2).putShort(this.F);
                byteVector.putByteArray(this.G.f1435a, 0, this.G.f1436b);
            }
            if (this.I != null) {
                byteVector.putShort(this.f1456b.newUTF8("LineNumberTable"));
                byteVector.putInt(this.I.f1436b + 2).putShort(this.H);
                byteVector.putByteArray(this.I.f1435a, 0, this.I.f1436b);
            }
            if (this.v != null) {
                byteVector.putShort(this.f1456b.newUTF8((this.f1456b.f1440b & 65535) >= 50 ? "StackMapTable" : "StackMap"));
                byteVector.putInt(this.v.f1436b + 2).putShort(this.u);
                byteVector.putByteArray(this.v.f1435a, 0, this.v.f1436b);
            }
            if (this.W != null) {
                byteVector.putShort(this.f1456b.newUTF8("RuntimeVisibleTypeAnnotations"));
                this.W.a(byteVector);
            }
            if (this.X != null) {
                byteVector.putShort(this.f1456b.newUTF8("RuntimeInvisibleTypeAnnotations"));
                this.X.a(byteVector);
            }
            if (this.J != null) {
                this.J.a(this.f1456b, this.r.f1435a, this.r.f1436b, this.t, this.s, byteVector);
            }
        }
        if (this.j > 0) {
            byteVector.putShort(this.f1456b.newUTF8("Exceptions")).putInt((2 * this.j) + 2);
            byteVector.putShort(this.j);
            for (int i4 = 0; i4 < this.j; i4++) {
                byteVector.putShort(this.k[i4]);
            }
        }
        if ((this.c & 4096) != 0 && ((this.f1456b.f1440b & 65535) < 49 || (this.c & 262144) != 0)) {
            byteVector.putShort(this.f1456b.newUTF8("Synthetic")).putInt(0);
        }
        if ((this.c & 131072) != 0) {
            byteVector.putShort(this.f1456b.newUTF8("Deprecated")).putInt(0);
        }
        if (this.g != null) {
            byteVector.putShort(this.f1456b.newUTF8("Signature")).putInt(2).putShort(this.f1456b.newUTF8(this.g));
        }
        if (this.$ != null) {
            byteVector.putShort(this.f1456b.newUTF8("MethodParameters"));
            byteVector.putInt(this.$.f1436b + 1).putByte(this.Z);
            byteVector.putByteArray(this.$.f1435a, 0, this.$.f1436b);
        }
        if (this.l != null) {
            byteVector.putShort(this.f1456b.newUTF8("AnnotationDefault"));
            byteVector.putInt(this.l.f1436b);
            byteVector.putByteArray(this.l.f1435a, 0, this.l.f1436b);
        }
        if (this.m != null) {
            byteVector.putShort(this.f1456b.newUTF8("RuntimeVisibleAnnotations"));
            this.m.a(byteVector);
        }
        if (this.n != null) {
            byteVector.putShort(this.f1456b.newUTF8("RuntimeInvisibleAnnotations"));
            this.n.a(byteVector);
        }
        if (this.U != null) {
            byteVector.putShort(this.f1456b.newUTF8("RuntimeVisibleTypeAnnotations"));
            this.U.a(byteVector);
        }
        if (this.V != null) {
            byteVector.putShort(this.f1456b.newUTF8("RuntimeInvisibleTypeAnnotations"));
            this.V.a(byteVector);
        }
        if (this.o != null) {
            byteVector.putShort(this.f1456b.newUTF8("RuntimeVisibleParameterAnnotations"));
            AnnotationWriter.a(this.o, this.S, byteVector);
        }
        if (this.p != null) {
            byteVector.putShort(this.f1456b.newUTF8("RuntimeInvisibleParameterAnnotations"));
            AnnotationWriter.a(this.p, this.S, byteVector);
        }
        if (this.q != null) {
            this.q.a(this.f1456b, null, 0, -1, -1, byteVector);
        }
    }

    private void d() {
        int b2;
        int b3;
        byte[] bArr = this.r.f1435a;
        int[] iArr = new int[0];
        int[] iArr2 = new int[0];
        boolean[] zArr = new boolean[this.r.f1436b];
        int i = 3;
        do {
            if (i == 3) {
                i = 2;
            }
            int i2 = 0;
            while (i2 < bArr.length) {
                int i3 = bArr[i2] & 255;
                int i4 = 0;
                switch (ClassWriter.f1439a[i3]) {
                    case 0:
                    case 4:
                        i2++;
                        break;
                    case 1:
                    case 3:
                    case 11:
                        i2 += 2;
                        break;
                    case 2:
                    case 5:
                    case 6:
                    case 12:
                    case 13:
                        i2 += 3;
                        break;
                    case 7:
                    case 8:
                        i2 += 5;
                        break;
                    case 9:
                        if (i3 > 201) {
                            i3 = i3 < 218 ? i3 - 49 : i3 - 20;
                            b3 = i2 + c(bArr, i2 + 1);
                        } else {
                            b3 = i2 + b(bArr, i2 + 1);
                        }
                        int a2 = a(iArr, iArr2, i2, b3);
                        if ((a2 < -32768 || a2 > 32767) && !zArr[i2]) {
                            i4 = (i3 == 167 || i3 == 168) ? 2 : 5;
                            zArr[i2] = true;
                        }
                        i2 += 3;
                        break;
                    case 10:
                        i2 += 5;
                        break;
                    case 14:
                        if (i == 1) {
                            i4 = -(a(iArr, iArr2, 0, i2) & 3);
                        } else if (!zArr[i2]) {
                            i4 = i2 & 3;
                            zArr[i2] = true;
                        }
                        int i5 = (i2 + 4) - (i2 & 3);
                        i2 = i5 + (4 * ((a(bArr, i5 + 8) - a(bArr, i5 + 4)) + 1)) + 12;
                        break;
                    case 15:
                        if (i == 1) {
                            i4 = -(a(iArr, iArr2, 0, i2) & 3);
                        } else if (!zArr[i2]) {
                            i4 = i2 & 3;
                            zArr[i2] = true;
                        }
                        int i6 = (i2 + 4) - (i2 & 3);
                        i2 = i6 + (8 * a(bArr, i6 + 4)) + 8;
                        break;
                    case 16:
                    default:
                        i2 += 4;
                        break;
                    case 17:
                        if ((bArr[i2 + 1] & 255) == 132) {
                            i2 += 6;
                            break;
                        } else {
                            i2 += 4;
                            break;
                        }
                }
                if (i4 != 0) {
                    int[] iArr3 = new int[iArr.length + 1];
                    int[] iArr4 = new int[iArr2.length + 1];
                    System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
                    System.arraycopy(iArr2, 0, iArr4, 0, iArr2.length);
                    iArr3[iArr.length] = i2;
                    iArr4[iArr2.length] = i4;
                    iArr = iArr3;
                    iArr2 = iArr4;
                    if (i4 > 0) {
                        i = 3;
                    }
                }
            }
            if (i < 3) {
                i--;
            }
        } while (i != 0);
        ByteVector byteVector = new ByteVector(this.r.f1436b);
        int i7 = 0;
        while (i7 < this.r.f1436b) {
            int i8 = bArr[i7] & 255;
            switch (ClassWriter.f1439a[i8]) {
                case 0:
                case 4:
                    byteVector.putByte(i8);
                    i7++;
                    break;
                case 1:
                case 3:
                case 11:
                    byteVector.putByteArray(bArr, i7, 2);
                    i7 += 2;
                    break;
                case 2:
                case 5:
                case 6:
                case 12:
                case 13:
                    byteVector.putByteArray(bArr, i7, 3);
                    i7 += 3;
                    break;
                case 7:
                case 8:
                    byteVector.putByteArray(bArr, i7, 5);
                    i7 += 5;
                    break;
                case 9:
                    if (i8 > 201) {
                        i8 = i8 < 218 ? i8 - 49 : i8 - 20;
                        b2 = i7 + c(bArr, i7 + 1);
                    } else {
                        b2 = i7 + b(bArr, i7 + 1);
                    }
                    int a3 = a(iArr, iArr2, i7, b2);
                    if (zArr[i7]) {
                        if (i8 == 167) {
                            byteVector.putByte(200);
                        } else if (i8 == 168) {
                            byteVector.putByte(201);
                        } else {
                            byteVector.putByte(i8 <= 166 ? ((i8 + 1) ^ 1) - 1 : i8 ^ 1);
                            byteVector.putShort(8);
                            byteVector.putByte(200);
                            a3 -= 3;
                        }
                        byteVector.putInt(a3);
                    } else {
                        byteVector.putByte(i8);
                        byteVector.putShort(a3);
                    }
                    i7 += 3;
                    break;
                case 10:
                    int a4 = a(iArr, iArr2, i7, i7 + a(bArr, i7 + 1));
                    byteVector.putByte(i8);
                    byteVector.putInt(a4);
                    i7 += 5;
                    break;
                case 14:
                    int i9 = i7;
                    int i10 = (i7 + 4) - (i9 & 3);
                    byteVector.putByte(170);
                    byteVector.putByteArray(null, 0, (4 - (byteVector.f1436b % 4)) % 4);
                    int i11 = i10 + 4;
                    byteVector.putInt(a(iArr, iArr2, i9, i9 + a(bArr, i10)));
                    int a5 = a(bArr, i11);
                    int i12 = i11 + 4;
                    byteVector.putInt(a5);
                    i7 = i12 + 4;
                    byteVector.putInt(a(bArr, i7 - 4));
                    for (int a6 = (a(bArr, i12) - a5) + 1; a6 > 0; a6--) {
                        int a7 = i9 + a(bArr, i7);
                        i7 += 4;
                        byteVector.putInt(a(iArr, iArr2, i9, a7));
                    }
                    break;
                case 15:
                    int i13 = i7;
                    int i14 = (i7 + 4) - (i13 & 3);
                    byteVector.putByte(171);
                    byteVector.putByteArray(null, 0, (4 - (byteVector.f1436b % 4)) % 4);
                    int i15 = i14 + 4;
                    byteVector.putInt(a(iArr, iArr2, i13, i13 + a(bArr, i14)));
                    int a8 = a(bArr, i15);
                    i7 = i15 + 4;
                    byteVector.putInt(a8);
                    while (a8 > 0) {
                        byteVector.putInt(a(bArr, i7));
                        int i16 = i7 + 4;
                        int a9 = i13 + a(bArr, i16);
                        i7 = i16 + 4;
                        byteVector.putInt(a(iArr, iArr2, i13, a9));
                        a8--;
                    }
                    break;
                case 16:
                default:
                    byteVector.putByteArray(bArr, i7, 4);
                    i7 += 4;
                    break;
                case 17:
                    if ((bArr[i7 + 1] & 255) == 132) {
                        byteVector.putByteArray(bArr, i7, 6);
                        i7 += 6;
                        break;
                    } else {
                        byteVector.putByteArray(bArr, i7, 4);
                        i7 += 4;
                        break;
                    }
            }
        }
        if (this.M == 0) {
            Label label = this.N;
            while (true) {
                Label label2 = label;
                if (label2 != null) {
                    int i17 = label2.c - 3;
                    if (i17 >= 0 && zArr[i17]) {
                        label2.f1454a |= 16;
                    }
                    a(iArr, iArr2, label2);
                    label = label2.i;
                } else if (this.f1456b.H != null) {
                    for (int i18 = 0; i18 < this.f1456b.H.length; i18++) {
                        Item item = this.f1456b.H[i18];
                        if (item != null && item.f1453b == 31) {
                            item.c = a(iArr, iArr2, 0, item.c);
                        }
                    }
                }
            }
        } else if (this.u > 0) {
            this.f1456b.L = true;
        }
        Handler handler = this.B;
        while (true) {
            Handler handler2 = handler;
            if (handler2 != null) {
                a(iArr, iArr2, handler2.f1450a);
                a(iArr, iArr2, handler2.f1451b);
                a(iArr, iArr2, handler2.c);
                handler = handler2.f;
            } else {
                int i19 = 0;
                while (i19 < 2) {
                    ByteVector byteVector2 = i19 == 0 ? this.E : this.G;
                    ByteVector byteVector3 = byteVector2;
                    if (byteVector2 != null) {
                        byte[] bArr2 = byteVector3.f1435a;
                        for (int i20 = 0; i20 < byteVector3.f1436b; i20 += 10) {
                            int c = c(bArr2, i20);
                            int a10 = a(iArr, iArr2, 0, c);
                            a(bArr2, i20, a10);
                            a(bArr2, i20 + 2, a(iArr, iArr2, 0, c + c(bArr2, i20 + 2)) - a10);
                        }
                    }
                    i19++;
                }
                if (this.I != null) {
                    byte[] bArr3 = this.I.f1435a;
                    for (int i21 = 0; i21 < this.I.f1436b; i21 += 4) {
                        a(bArr3, i21, a(iArr, iArr2, 0, c(bArr3, i21)));
                    }
                }
                Attribute attribute = this.J;
                while (true) {
                    Attribute attribute2 = attribute;
                    if (attribute2 == null) {
                        this.r = byteVector;
                        return;
                    }
                    Label[] labels = attribute2.getLabels();
                    if (labels != null) {
                        for (int length = labels.length - 1; length >= 0; length--) {
                            a(iArr, iArr2, labels[length]);
                        }
                    }
                    attribute = attribute2.f1434a;
                }
            }
        }
    }

    static int c(byte[] bArr, int i) {
        return ((bArr[i] & 255) << 8) | (bArr[i + 1] & 255);
    }

    static short b(byte[] bArr, int i) {
        return (short) (((bArr[i] & 255) << 8) | (bArr[i + 1] & 255));
    }

    static int a(byte[] bArr, int i) {
        return ((bArr[i] & 255) << 24) | ((bArr[i + 1] & 255) << 16) | ((bArr[i + 2] & 255) << 8) | (bArr[i + 3] & 255);
    }

    static void a(byte[] bArr, int i, int i2) {
        bArr[i] = (byte) (i2 >>> 8);
        bArr[i + 1] = (byte) i2;
    }

    static int a(int[] iArr, int[] iArr2, int i, int i2) {
        int i3 = i2 - i;
        for (int i4 = 0; i4 < iArr.length; i4++) {
            if (i < iArr[i4] && iArr[i4] <= i2) {
                i3 += iArr2[i4];
            } else if (i2 < iArr[i4] && iArr[i4] <= i) {
                i3 -= iArr2[i4];
            }
        }
        return i3;
    }

    static void a(int[] iArr, int[] iArr2, Label label) {
        if ((label.f1454a & 4) == 0) {
            label.c = a(iArr, iArr2, 0, label.c);
            label.f1454a |= 4;
        }
    }
}
