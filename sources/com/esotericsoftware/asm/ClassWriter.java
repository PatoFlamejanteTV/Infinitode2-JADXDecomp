package com.esotericsoftware.asm;

import net.bytebuddy.implementation.auxiliary.TypeProxy;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:com/esotericsoftware/asm/ClassWriter.class */
public class ClassWriter extends ClassVisitor {
    public static final int COMPUTE_MAXS = 1;
    public static final int COMPUTE_FRAMES = 2;

    /* renamed from: a, reason: collision with root package name */
    static final byte[] f1439a;
    ClassReader M;

    /* renamed from: b, reason: collision with root package name */
    int f1440b;
    int c;
    final ByteVector d;
    Item[] e;
    int f;
    final Item g;
    final Item h;
    final Item i;
    final Item j;
    Item[] H;
    private short G;
    private int k;
    private int l;
    String I;
    private int m;
    private int n;
    private int o;
    private int[] p;
    private int q;
    private ByteVector r;
    private int s;
    private int t;
    private AnnotationWriter u;
    private AnnotationWriter v;
    private AnnotationWriter N;
    private AnnotationWriter O;
    private Attribute w;
    private int x;
    private ByteVector y;
    int z;
    ByteVector A;
    FieldWriter B;
    FieldWriter C;
    MethodWriter D;
    MethodWriter E;
    private boolean K;
    private boolean J;
    boolean L;

    public ClassWriter(int i) {
        super(327680);
        this.c = 1;
        this.d = new ByteVector();
        this.e = new Item[256];
        this.f = (int) (0.75d * this.e.length);
        this.g = new Item();
        this.h = new Item();
        this.i = new Item();
        this.j = new Item();
        this.K = (i & 1) != 0;
        this.J = (i & 2) != 0;
    }

    public ClassWriter(ClassReader classReader, int i) {
        this(i);
        classReader.a(this);
        this.M = classReader;
    }

    @Override // com.esotericsoftware.asm.ClassVisitor
    public final void visit(int i, int i2, String str, String str2, String str3, String[] strArr) {
        this.f1440b = i;
        this.k = i2;
        this.l = newClass(str);
        this.I = str;
        if (str2 != null) {
            this.m = newUTF8(str2);
        }
        this.n = str3 == null ? 0 : newClass(str3);
        if (strArr == null || strArr.length <= 0) {
            return;
        }
        this.o = strArr.length;
        this.p = new int[this.o];
        for (int i3 = 0; i3 < this.o; i3++) {
            this.p[i3] = newClass(strArr[i3]);
        }
    }

    @Override // com.esotericsoftware.asm.ClassVisitor
    public final void visitSource(String str, String str2) {
        if (str != null) {
            this.q = newUTF8(str);
        }
        if (str2 != null) {
            this.r = new ByteVector().c(str2, 0, Integer.MAX_VALUE);
        }
    }

    @Override // com.esotericsoftware.asm.ClassVisitor
    public final void visitOuterClass(String str, String str2, String str3) {
        this.s = newClass(str);
        if (str2 == null || str3 == null) {
            return;
        }
        this.t = newNameType(str2, str3);
    }

    @Override // com.esotericsoftware.asm.ClassVisitor
    public final AnnotationVisitor visitAnnotation(String str, boolean z) {
        ByteVector byteVector = new ByteVector();
        byteVector.putShort(newUTF8(str)).putShort(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this, true, byteVector, byteVector, 2);
        if (z) {
            annotationWriter.g = this.u;
            this.u = annotationWriter;
        } else {
            annotationWriter.g = this.v;
            this.v = annotationWriter;
        }
        return annotationWriter;
    }

    @Override // com.esotericsoftware.asm.ClassVisitor
    public final AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String str, boolean z) {
        ByteVector byteVector = new ByteVector();
        AnnotationWriter.a(i, typePath, byteVector);
        byteVector.putShort(newUTF8(str)).putShort(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this, true, byteVector, byteVector, byteVector.f1436b - 2);
        if (z) {
            annotationWriter.g = this.N;
            this.N = annotationWriter;
        } else {
            annotationWriter.g = this.O;
            this.O = annotationWriter;
        }
        return annotationWriter;
    }

    @Override // com.esotericsoftware.asm.ClassVisitor
    public final void visitAttribute(Attribute attribute) {
        attribute.f1434a = this.w;
        this.w = attribute;
    }

    @Override // com.esotericsoftware.asm.ClassVisitor
    public final void visitInnerClass(String str, String str2, String str3, int i) {
        if (this.y == null) {
            this.y = new ByteVector();
        }
        Item a2 = a(str);
        if (a2.c == 0) {
            this.x++;
            this.y.putShort(a2.f1452a);
            this.y.putShort(str2 == null ? 0 : newClass(str2));
            this.y.putShort(str3 == null ? 0 : newUTF8(str3));
            this.y.putShort(i);
            a2.c = this.x;
        }
    }

    @Override // com.esotericsoftware.asm.ClassVisitor
    public final FieldVisitor visitField(int i, String str, String str2, String str3, Object obj) {
        return new FieldWriter(this, i, str, str2, str3, obj);
    }

    @Override // com.esotericsoftware.asm.ClassVisitor
    public final MethodVisitor visitMethod(int i, String str, String str2, String str3, String[] strArr) {
        return new MethodWriter(this, i, str, str2, str3, strArr, this.K, this.J);
    }

    @Override // com.esotericsoftware.asm.ClassVisitor
    public final void visitEnd() {
    }

    public byte[] toByteArray() {
        if (this.c > 65535) {
            throw new RuntimeException("Class file too large!");
        }
        int i = 24 + (2 * this.o);
        int i2 = 0;
        FieldWriter fieldWriter = this.B;
        while (true) {
            FieldWriter fieldWriter2 = fieldWriter;
            if (fieldWriter2 == null) {
                break;
            }
            i2++;
            i += fieldWriter2.a();
            fieldWriter = (FieldWriter) fieldWriter2.fv;
        }
        int i3 = 0;
        MethodWriter methodWriter = this.D;
        while (true) {
            MethodWriter methodWriter2 = methodWriter;
            if (methodWriter2 == null) {
                break;
            }
            i3++;
            i += methodWriter2.a();
            methodWriter = (MethodWriter) methodWriter2.mv;
        }
        int i4 = 0;
        if (this.A != null) {
            i4 = 0 + 1;
            i += 8 + this.A.f1436b;
            newUTF8("BootstrapMethods");
        }
        if (this.m != 0) {
            i4++;
            i += 8;
            newUTF8("Signature");
        }
        if (this.q != 0) {
            i4++;
            i += 8;
            newUTF8("SourceFile");
        }
        if (this.r != null) {
            i4++;
            i += this.r.f1436b + 6;
            newUTF8("SourceDebugExtension");
        }
        if (this.s != 0) {
            i4++;
            i += 10;
            newUTF8("EnclosingMethod");
        }
        if ((this.k & 131072) != 0) {
            i4++;
            i += 6;
            newUTF8("Deprecated");
        }
        if ((this.k & 4096) != 0 && ((this.f1440b & 65535) < 49 || (this.k & 262144) != 0)) {
            i4++;
            i += 6;
            newUTF8("Synthetic");
        }
        if (this.y != null) {
            i4++;
            i += 8 + this.y.f1436b;
            newUTF8("InnerClasses");
        }
        if (this.u != null) {
            i4++;
            i += 8 + this.u.a();
            newUTF8("RuntimeVisibleAnnotations");
        }
        if (this.v != null) {
            i4++;
            i += 8 + this.v.a();
            newUTF8("RuntimeInvisibleAnnotations");
        }
        if (this.N != null) {
            i4++;
            i += 8 + this.N.a();
            newUTF8("RuntimeVisibleTypeAnnotations");
        }
        if (this.O != null) {
            i4++;
            i += 8 + this.O.a();
            newUTF8("RuntimeInvisibleTypeAnnotations");
        }
        if (this.w != null) {
            i4 += this.w.a();
            i += this.w.a(this, null, 0, -1, -1);
        }
        ByteVector byteVector = new ByteVector(i + this.d.f1436b);
        byteVector.putInt(-889275714).putInt(this.f1440b);
        byteVector.putShort(this.c).putByteArray(this.d.f1435a, 0, this.d.f1436b);
        byteVector.putShort(this.k & ((393216 | ((this.k & 262144) / 64)) ^ (-1))).putShort(this.l).putShort(this.n);
        byteVector.putShort(this.o);
        for (int i5 = 0; i5 < this.o; i5++) {
            byteVector.putShort(this.p[i5]);
        }
        byteVector.putShort(i2);
        FieldWriter fieldWriter3 = this.B;
        while (true) {
            FieldWriter fieldWriter4 = fieldWriter3;
            if (fieldWriter4 == null) {
                break;
            }
            fieldWriter4.a(byteVector);
            fieldWriter3 = (FieldWriter) fieldWriter4.fv;
        }
        byteVector.putShort(i3);
        MethodWriter methodWriter3 = this.D;
        while (true) {
            MethodWriter methodWriter4 = methodWriter3;
            if (methodWriter4 == null) {
                break;
            }
            methodWriter4.a(byteVector);
            methodWriter3 = (MethodWriter) methodWriter4.mv;
        }
        byteVector.putShort(i4);
        if (this.A != null) {
            byteVector.putShort(newUTF8("BootstrapMethods"));
            byteVector.putInt(this.A.f1436b + 2).putShort(this.z);
            byteVector.putByteArray(this.A.f1435a, 0, this.A.f1436b);
        }
        if (this.m != 0) {
            byteVector.putShort(newUTF8("Signature")).putInt(2).putShort(this.m);
        }
        if (this.q != 0) {
            byteVector.putShort(newUTF8("SourceFile")).putInt(2).putShort(this.q);
        }
        if (this.r != null) {
            int i6 = this.r.f1436b;
            byteVector.putShort(newUTF8("SourceDebugExtension")).putInt(i6);
            byteVector.putByteArray(this.r.f1435a, 0, i6);
        }
        if (this.s != 0) {
            byteVector.putShort(newUTF8("EnclosingMethod")).putInt(4);
            byteVector.putShort(this.s).putShort(this.t);
        }
        if ((this.k & 131072) != 0) {
            byteVector.putShort(newUTF8("Deprecated")).putInt(0);
        }
        if ((this.k & 4096) != 0 && ((this.f1440b & 65535) < 49 || (this.k & 262144) != 0)) {
            byteVector.putShort(newUTF8("Synthetic")).putInt(0);
        }
        if (this.y != null) {
            byteVector.putShort(newUTF8("InnerClasses"));
            byteVector.putInt(this.y.f1436b + 2).putShort(this.x);
            byteVector.putByteArray(this.y.f1435a, 0, this.y.f1436b);
        }
        if (this.u != null) {
            byteVector.putShort(newUTF8("RuntimeVisibleAnnotations"));
            this.u.a(byteVector);
        }
        if (this.v != null) {
            byteVector.putShort(newUTF8("RuntimeInvisibleAnnotations"));
            this.v.a(byteVector);
        }
        if (this.N != null) {
            byteVector.putShort(newUTF8("RuntimeVisibleTypeAnnotations"));
            this.N.a(byteVector);
        }
        if (this.O != null) {
            byteVector.putShort(newUTF8("RuntimeInvisibleTypeAnnotations"));
            this.O.a(byteVector);
        }
        if (this.w != null) {
            this.w.a(this, null, 0, -1, -1, byteVector);
        }
        if (!this.L) {
            return byteVector.f1435a;
        }
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = 0;
        this.y = null;
        this.z = 0;
        this.A = null;
        this.B = null;
        this.C = null;
        this.D = null;
        this.E = null;
        this.K = false;
        this.J = true;
        this.L = false;
        new ClassReader(byteVector.f1435a).accept(this, 4);
        return toByteArray();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Item a(Object obj) {
        if (obj instanceof Integer) {
            return a(((Integer) obj).intValue());
        }
        if (obj instanceof Byte) {
            return a(((Byte) obj).intValue());
        }
        if (obj instanceof Character) {
            return a((int) ((Character) obj).charValue());
        }
        if (obj instanceof Short) {
            return a(((Short) obj).intValue());
        }
        if (obj instanceof Boolean) {
            return a(((Boolean) obj).booleanValue() ? 1 : 0);
        }
        if (obj instanceof Float) {
            return a(((Float) obj).floatValue());
        }
        if (obj instanceof Long) {
            return a(((Long) obj).longValue());
        }
        if (obj instanceof Double) {
            return a(((Double) obj).doubleValue());
        }
        if (obj instanceof String) {
            return b((String) obj);
        }
        if (obj instanceof Type) {
            Type type = (Type) obj;
            int sort = type.getSort();
            return sort == 10 ? a(type.getInternalName()) : sort == 11 ? c(type.getDescriptor()) : a(type.getDescriptor());
        }
        if (!(obj instanceof Handle)) {
            throw new IllegalArgumentException(new StringBuffer("value ").append(obj).toString());
        }
        Handle handle = (Handle) obj;
        return a(handle.f1448a, handle.f1449b, handle.c, handle.d, handle.e);
    }

    public int newConst(Object obj) {
        return a(obj).f1452a;
    }

    public int newUTF8(String str) {
        this.g.a(1, str, null, null);
        Item a2 = a(this.g);
        Item item = a2;
        if (a2 == null) {
            this.d.putByte(1).putUTF8(str);
            int i = this.c;
            this.c = i + 1;
            item = new Item(i, this.g);
            b(item);
        }
        return item.f1452a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Item a(String str) {
        this.h.a(7, str, null, null);
        Item a2 = a(this.h);
        Item item = a2;
        if (a2 == null) {
            this.d.b(7, newUTF8(str));
            int i = this.c;
            this.c = i + 1;
            item = new Item(i, this.h);
            b(item);
        }
        return item;
    }

    public int newClass(String str) {
        return a(str).f1452a;
    }

    Item c(String str) {
        this.h.a(16, str, null, null);
        Item a2 = a(this.h);
        Item item = a2;
        if (a2 == null) {
            this.d.b(16, newUTF8(str));
            int i = this.c;
            this.c = i + 1;
            item = new Item(i, this.h);
            b(item);
        }
        return item;
    }

    public int newMethodType(String str) {
        return c(str).f1452a;
    }

    Item a(int i, String str, String str2, String str3, boolean z) {
        this.j.a(i + 20, str, str2, str3);
        Item a2 = a(this.j);
        Item item = a2;
        if (a2 == null) {
            if (i <= 4) {
                b(15, i, newField(str, str2, str3));
            } else {
                b(15, i, newMethod(str, str2, str3, z));
            }
            int i2 = this.c;
            this.c = i2 + 1;
            item = new Item(i2, this.j);
            b(item);
        }
        return item;
    }

    public int newHandle(int i, String str, String str2, String str3) {
        return newHandle(i, str, str2, str3, i == 9);
    }

    public int newHandle(int i, String str, String str2, String str3, boolean z) {
        return a(i, str, str2, str3, z).f1452a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Item a(String str, String str2, Handle handle, Object... objArr) {
        Item item;
        int i;
        ByteVector byteVector = this.A;
        ByteVector byteVector2 = byteVector;
        if (byteVector == null) {
            ByteVector byteVector3 = new ByteVector();
            this.A = byteVector3;
            byteVector2 = byteVector3;
        }
        int i2 = byteVector2.f1436b;
        int hashCode = handle.hashCode();
        byteVector2.putShort(newHandle(handle.f1448a, handle.f1449b, handle.c, handle.d, handle.isInterface()));
        int length = objArr.length;
        byteVector2.putShort(length);
        for (Object obj : objArr) {
            hashCode ^= obj.hashCode();
            byteVector2.putShort(newConst(obj));
        }
        byte[] bArr = byteVector2.f1435a;
        int i3 = (length + 2) << 1;
        int i4 = hashCode & Integer.MAX_VALUE;
        Item[] itemArr = this.e;
        Item item2 = itemArr[i4 % itemArr.length];
        loop1: while (true) {
            item = item2;
            if (item == null) {
                break;
            }
            if (item.f1453b == 33 && item.j == i4) {
                int i5 = item.c;
                for (int i6 = 0; i6 < i3; i6++) {
                    if (bArr[i2 + i6] != bArr[i5 + i6]) {
                        item2 = item.k;
                    }
                }
                break loop1;
            }
            item2 = item.k;
        }
        if (item != null) {
            i = item.f1452a;
            byteVector2.f1436b = i2;
        } else {
            int i7 = this.z;
            this.z = i7 + 1;
            i = i7;
            Item item3 = new Item(i);
            item3.a(i2, i4);
            b(item3);
        }
        this.i.a(str, str2, i);
        Item a2 = a(this.i);
        Item item4 = a2;
        if (a2 == null) {
            a(18, i, newNameType(str, str2));
            int i8 = this.c;
            this.c = i8 + 1;
            item4 = new Item(i8, this.i);
            b(item4);
        }
        return item4;
    }

    public int newInvokeDynamic(String str, String str2, Handle handle, Object... objArr) {
        return a(str, str2, handle, objArr).f1452a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Item a(String str, String str2, String str3) {
        this.i.a(9, str, str2, str3);
        Item a2 = a(this.i);
        Item item = a2;
        if (a2 == null) {
            a(9, newClass(str), newNameType(str2, str3));
            int i = this.c;
            this.c = i + 1;
            item = new Item(i, this.i);
            b(item);
        }
        return item;
    }

    public int newField(String str, String str2, String str3) {
        return a(str, str2, str3).f1452a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Item a(String str, String str2, String str3, boolean z) {
        int i = z ? 11 : 10;
        this.i.a(i, str, str2, str3);
        Item a2 = a(this.i);
        Item item = a2;
        if (a2 == null) {
            a(i, newClass(str), newNameType(str2, str3));
            int i2 = this.c;
            this.c = i2 + 1;
            item = new Item(i2, this.i);
            b(item);
        }
        return item;
    }

    public int newMethod(String str, String str2, String str3, boolean z) {
        return a(str, str2, str3, z).f1452a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Item a(int i) {
        this.g.a(i);
        Item a2 = a(this.g);
        Item item = a2;
        if (a2 == null) {
            this.d.putByte(3).putInt(i);
            int i2 = this.c;
            this.c = i2 + 1;
            item = new Item(i2, this.g);
            b(item);
        }
        return item;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Item a(float f) {
        this.g.a(f);
        Item a2 = a(this.g);
        Item item = a2;
        if (a2 == null) {
            this.d.putByte(4).putInt(this.g.c);
            int i = this.c;
            this.c = i + 1;
            item = new Item(i, this.g);
            b(item);
        }
        return item;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Item a(long j) {
        this.g.a(j);
        Item a2 = a(this.g);
        Item item = a2;
        if (a2 == null) {
            this.d.putByte(5).putLong(j);
            item = new Item(this.c, this.g);
            this.c += 2;
            b(item);
        }
        return item;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Item a(double d) {
        this.g.a(d);
        Item a2 = a(this.g);
        Item item = a2;
        if (a2 == null) {
            this.d.putByte(6).putLong(this.g.d);
            item = new Item(this.c, this.g);
            this.c += 2;
            b(item);
        }
        return item;
    }

    private Item b(String str) {
        this.h.a(8, str, null, null);
        Item a2 = a(this.h);
        Item item = a2;
        if (a2 == null) {
            this.d.b(8, newUTF8(str));
            int i = this.c;
            this.c = i + 1;
            item = new Item(i, this.h);
            b(item);
        }
        return item;
    }

    public int newNameType(String str, String str2) {
        return a(str, str2).f1452a;
    }

    Item a(String str, String str2) {
        this.h.a(12, str, str2, null);
        Item a2 = a(this.h);
        Item item = a2;
        if (a2 == null) {
            a(12, newUTF8(str), newUTF8(str2));
            int i = this.c;
            this.c = i + 1;
            item = new Item(i, this.h);
            b(item);
        }
        return item;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c, reason: collision with other method in class */
    public int m699c(String str) {
        this.g.a(30, str, null, null);
        Item a2 = a(this.g);
        Item item = a2;
        if (a2 == null) {
            item = c(this.g);
        }
        return item.f1452a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int a(String str, int i) {
        this.g.f1453b = 31;
        this.g.c = i;
        this.g.g = str;
        this.g.j = Integer.MAX_VALUE & (31 + str.hashCode() + i);
        Item a2 = a(this.g);
        Item item = a2;
        if (a2 == null) {
            item = c(this.g);
        }
        return item.f1452a;
    }

    private Item c(Item item) {
        this.G = (short) (this.G + 1);
        Item item2 = new Item(this.G, this.g);
        b(item2);
        if (this.H == null) {
            this.H = new Item[16];
        }
        if (this.G == this.H.length) {
            Item[] itemArr = new Item[2 * this.H.length];
            System.arraycopy(this.H, 0, itemArr, 0, this.H.length);
            this.H = itemArr;
        }
        this.H[this.G] = item2;
        return item2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int a(int i, int i2) {
        this.h.f1453b = 32;
        this.h.d = i | (i2 << 32);
        this.h.j = Integer.MAX_VALUE & (i + 32 + i2);
        Item a2 = a(this.h);
        Item item = a2;
        if (a2 == null) {
            this.h.c = m699c(getCommonSuperClass(this.H[i].g, this.H[i2].g));
            item = new Item(0, this.h);
            b(item);
        }
        return item.c;
    }

    protected String getCommonSuperClass(String str, String str2) {
        Class<? super Object> superclass;
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            Class<?> cls = Class.forName(str.replace('/', '.'), false, classLoader);
            Class<?> cls2 = Class.forName(str2.replace('/', '.'), false, classLoader);
            if (cls.isAssignableFrom(cls2)) {
                return str;
            }
            if (cls2.isAssignableFrom(cls)) {
                return str2;
            }
            if (cls.isInterface() || cls2.isInterface()) {
                return TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_INTERNAL_NAME;
            }
            do {
                superclass = cls.getSuperclass();
                cls = superclass;
            } while (!superclass.isAssignableFrom(cls2));
            return cls.getName().replace('.', '/');
        } catch (Exception e) {
            throw new RuntimeException(e.toString());
        }
    }

    private Item a(Item item) {
        Item item2;
        Item item3 = this.e[item.j % this.e.length];
        while (true) {
            item2 = item3;
            if (item2 == null || (item2.f1453b == item.f1453b && item.a(item2))) {
                break;
            }
            item3 = item2.k;
        }
        return item2;
    }

    private void b(Item item) {
        if (this.c + this.G > this.f) {
            int length = this.e.length;
            int i = (length << 1) + 1;
            Item[] itemArr = new Item[i];
            for (int i2 = length - 1; i2 >= 0; i2--) {
                Item item2 = this.e[i2];
                while (true) {
                    Item item3 = item2;
                    if (item3 != null) {
                        int i3 = item3.j % i;
                        Item item4 = item3.k;
                        item3.k = itemArr[i3];
                        itemArr[i3] = item3;
                        item2 = item4;
                    }
                }
            }
            this.e = itemArr;
            this.f = (int) (i * 0.75d);
        }
        int length2 = item.j % this.e.length;
        item.k = this.e[length2];
        this.e[length2] = item;
    }

    private void a(int i, int i2, int i3) {
        this.d.b(i, i2).putShort(i3);
    }

    private void b(int i, int i2, int i3) {
        this.d.a(i, i2).putShort(i3);
    }

    static {
        _clinit_();
        byte[] bArr = new byte[User32.VK_OEM_5];
        for (int i = 0; i < 220; i++) {
            bArr[i] = (byte) ("AAAAAAAAAAAAAAAABCLMMDDDDDEEEEEEEEEEEEEEEEEEEEAAAAAAAADDDDDEEEEEEEEEEEEEEEEEEEEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAANAAAAAAAAAAAAAAAAAAAAJJJJJJJJJJJJJJJJDOPAAAAAAGGGGGGGHIFBFAAFFAARQJJKKJJJJJJJJJJJJJJJJJJ".charAt(i) - 'A');
        }
        f1439a = bArr;
    }

    static void _clinit_() {
    }
}
