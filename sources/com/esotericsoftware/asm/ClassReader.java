package com.esotericsoftware.asm;

import java.io.InputStream;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:com/esotericsoftware/asm/ClassReader.class */
public class ClassReader {
    public static final int SKIP_CODE = 1;
    public static final int SKIP_DEBUG = 2;
    public static final int SKIP_FRAMES = 4;
    public static final int EXPAND_FRAMES = 8;

    /* renamed from: b, reason: collision with root package name */
    public final byte[] f1437b;

    /* renamed from: a, reason: collision with root package name */
    private final int[] f1438a;
    private final String[] c;
    private final int d;
    public final int header;

    public ClassReader(byte[] bArr) {
        this(bArr, 0, bArr.length);
    }

    public ClassReader(byte[] bArr, int i, int i2) {
        int i3;
        this.f1437b = bArr;
        if (readShort(i + 6) > 52) {
            throw new IllegalArgumentException();
        }
        this.f1438a = new int[readUnsignedShort(i + 8)];
        int length = this.f1438a.length;
        this.c = new String[length];
        int i4 = 0;
        int i5 = i + 10;
        int i6 = 1;
        while (i6 < length) {
            this.f1438a[i6] = i5 + 1;
            switch (bArr[i5]) {
                case 1:
                    int readUnsignedShort = 3 + readUnsignedShort(i5 + 1);
                    i3 = readUnsignedShort;
                    if (readUnsignedShort <= i4) {
                        break;
                    } else {
                        i4 = i3;
                        break;
                    }
                case 2:
                case 7:
                case 8:
                case 13:
                case 14:
                case 16:
                case 17:
                default:
                    i3 = 3;
                    break;
                case 3:
                case 4:
                case 9:
                case 10:
                case 11:
                case 12:
                case 18:
                    i3 = 5;
                    break;
                case 5:
                case 6:
                    i3 = 9;
                    i6++;
                    break;
                case 15:
                    i3 = 4;
                    break;
            }
            i5 += i3;
            i6++;
        }
        this.d = i4;
        this.header = i5;
    }

    public int getAccess() {
        return readUnsignedShort(this.header);
    }

    public String getClassName() {
        return readClass(this.header + 2, new char[this.d]);
    }

    public String getSuperName() {
        return readClass(this.header + 4, new char[this.d]);
    }

    public String[] getInterfaces() {
        int i = this.header + 6;
        int readUnsignedShort = readUnsignedShort(i);
        String[] strArr = new String[readUnsignedShort];
        if (readUnsignedShort > 0) {
            char[] cArr = new char[this.d];
            for (int i2 = 0; i2 < readUnsignedShort; i2++) {
                i += 2;
                strArr[i2] = readClass(i, cArr);
            }
        }
        return strArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(ClassWriter classWriter) {
        char[] cArr = new char[this.d];
        int length = this.f1438a.length;
        Item[] itemArr = new Item[length];
        int i = 1;
        while (i < length) {
            int i2 = this.f1438a[i];
            byte b2 = this.f1437b[i2 - 1];
            Item item = new Item(i);
            switch (b2) {
                case 1:
                    String str = this.c[i];
                    String str2 = str;
                    if (str == null) {
                        int i3 = this.f1438a[i];
                        String a2 = a(i3 + 2, readUnsignedShort(i3), cArr);
                        this.c[i] = a2;
                        str2 = a2;
                    }
                    item.a(b2, str2, null, null);
                    break;
                case 2:
                case 7:
                case 8:
                case 13:
                case 14:
                case 16:
                case 17:
                default:
                    item.a(b2, readUTF8(i2, cArr), null, null);
                    break;
                case 3:
                    item.a(readInt(i2));
                    break;
                case 4:
                    item.a(Float.intBitsToFloat(readInt(i2)));
                    break;
                case 5:
                    item.a(readLong(i2));
                    i++;
                    break;
                case 6:
                    item.a(Double.longBitsToDouble(readLong(i2)));
                    i++;
                    break;
                case 9:
                case 10:
                case 11:
                    int i4 = this.f1438a[readUnsignedShort(i2 + 2)];
                    item.a(b2, readClass(i2, cArr), readUTF8(i4, cArr), readUTF8(i4 + 2, cArr));
                    break;
                case 12:
                    item.a(b2, readUTF8(i2, cArr), readUTF8(i2 + 2, cArr), null);
                    break;
                case 15:
                    int i5 = this.f1438a[readUnsignedShort(i2 + 1)];
                    int i6 = this.f1438a[readUnsignedShort(i5 + 2)];
                    item.a(20 + readByte(i2), readClass(i5, cArr), readUTF8(i6, cArr), readUTF8(i6 + 2, cArr));
                    break;
                case 18:
                    if (classWriter.A == null) {
                        a(classWriter, itemArr, cArr);
                    }
                    int i7 = this.f1438a[readUnsignedShort(i2 + 2)];
                    item.a(readUTF8(i7, cArr), readUTF8(i7 + 2, cArr), readUnsignedShort(i2));
                    break;
            }
            int i8 = item.j % length;
            item.k = itemArr[i8];
            itemArr[i8] = item;
            i++;
        }
        int i9 = this.f1438a[1] - 1;
        classWriter.d.putByteArray(this.f1437b, i9, this.header - i9);
        classWriter.e = itemArr;
        classWriter.f = (int) (0.75d * length);
        classWriter.c = length;
    }

    private void a(ClassWriter classWriter, Item[] itemArr, char[] cArr) {
        int a2 = a();
        boolean z = false;
        int readUnsignedShort = readUnsignedShort(a2);
        while (true) {
            if (readUnsignedShort <= 0) {
                break;
            }
            if ("BootstrapMethods".equals(readUTF8(a2 + 2, cArr))) {
                z = true;
                break;
            } else {
                a2 += 6 + readInt(a2 + 4);
                readUnsignedShort--;
            }
        }
        if (z) {
            int readUnsignedShort2 = readUnsignedShort(a2 + 8);
            int i = a2 + 10;
            for (int i2 = 0; i2 < readUnsignedShort2; i2++) {
                int i3 = (i - a2) - 10;
                int hashCode = readConst(readUnsignedShort(i), cArr).hashCode();
                for (int readUnsignedShort3 = readUnsignedShort(i + 2); readUnsignedShort3 > 0; readUnsignedShort3--) {
                    hashCode ^= readConst(readUnsignedShort(i + 4), cArr).hashCode();
                    i += 2;
                }
                i += 4;
                Item item = new Item(i2);
                item.a(i3, hashCode & Integer.MAX_VALUE);
                int length = item.j % itemArr.length;
                item.k = itemArr[length];
                itemArr[length] = item;
            }
            int readInt = readInt(a2 + 4);
            ByteVector byteVector = new ByteVector(readInt + 62);
            byteVector.putByteArray(this.f1437b, a2 + 10, readInt - 2);
            classWriter.z = readUnsignedShort2;
            classWriter.A = byteVector;
        }
    }

    public ClassReader(InputStream inputStream) {
        this(a(inputStream, false));
    }

    public ClassReader(String str) {
        this(a(ClassLoader.getSystemResourceAsStream(new StringBuffer().append(str.replace('.', '/')).append(".class").toString()), true));
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x002b, code lost:            if (r9 >= r8.length) goto L12;     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x002e, code lost:            r0 = new byte[r9];        java.lang.System.arraycopy(r8, 0, r0, 0, r9);        r8 = r0;     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x004c, code lost:            return r8;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static byte[] a(java.io.InputStream r6, boolean r7) {
        /*
            r0 = r6
            if (r0 != 0) goto Le
            java.io.IOException r0 = new java.io.IOException
            r1 = r0
            java.lang.String r2 = "Class not found"
            r1.<init>(r2)
            throw r0
        Le:
            r0 = r6
            int r0 = r0.available()     // Catch: java.lang.Throwable -> L93
            byte[] r0 = new byte[r0]     // Catch: java.lang.Throwable -> L93
            r8 = r0
            r0 = 0
            r9 = r0
        L17:
            r0 = r6
            r1 = r8
            r2 = r9
            r3 = r8
            int r3 = r3.length     // Catch: java.lang.Throwable -> L93
            r4 = r9
            int r3 = r3 - r4
            int r0 = r0.read(r1, r2, r3)     // Catch: java.lang.Throwable -> L93
            r1 = r0
            r10 = r1
            r1 = -1
            if (r0 != r1) goto L4d
            r0 = r9
            r1 = r8
            int r1 = r1.length     // Catch: java.lang.Throwable -> L93
            if (r0 >= r1) goto L3f
            r0 = r9
            byte[] r0 = new byte[r0]     // Catch: java.lang.Throwable -> L93
            r10 = r0
            r0 = r8
            r1 = 0
            r2 = r10
            r3 = 0
            r4 = r9
            java.lang.System.arraycopy(r0, r1, r2, r3, r4)     // Catch: java.lang.Throwable -> L93
            r0 = r10
            r8 = r0
        L3f:
            r0 = r8
            r10 = r0
            r0 = r7
            if (r0 == 0) goto L4a
            r0 = r6
            r0.close()
        L4a:
            r0 = r10
            return r0
        L4d:
            r0 = r9
            r1 = r10
            int r0 = r0 + r1
            r1 = r0
            r9 = r1
            r1 = r8
            int r1 = r1.length     // Catch: java.lang.Throwable -> L93
            if (r0 != r1) goto L90
            r0 = r6
            int r0 = r0.read()     // Catch: java.lang.Throwable -> L93
            r1 = r0
            r10 = r1
            if (r0 >= 0) goto L70
            r0 = r8
            r11 = r0
            r0 = r7
            if (r0 == 0) goto L6d
            r0 = r6
            r0.close()
        L6d:
            r0 = r11
            return r0
        L70:
            r0 = r8
            int r0 = r0.length     // Catch: java.lang.Throwable -> L93
            r1 = 1000(0x3e8, float:1.401E-42)
            int r0 = r0 + r1
            byte[] r0 = new byte[r0]     // Catch: java.lang.Throwable -> L93
            r11 = r0
            r0 = r8
            r1 = 0
            r2 = r11
            r3 = 0
            r4 = r9
            java.lang.System.arraycopy(r0, r1, r2, r3, r4)     // Catch: java.lang.Throwable -> L93
            r0 = r11
            r1 = r9
            int r9 = r9 + 1
            r2 = r10
            byte r2 = (byte) r2     // Catch: java.lang.Throwable -> L93
            r0[r1] = r2     // Catch: java.lang.Throwable -> L93
            r0 = r11
            r8 = r0
        L90:
            goto L17
        L93:
            r8 = move-exception
            r0 = r7
            if (r0 == 0) goto L9c
            r0 = r6
            r0.close()
        L9c:
            r0 = r8
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.esotericsoftware.asm.ClassReader.a(java.io.InputStream, boolean):byte[]");
    }

    public void accept(ClassVisitor classVisitor, int i) {
        accept(classVisitor, new Attribute[0], i);
    }

    public void accept(ClassVisitor classVisitor, Attribute[] attributeArr, int i) {
        int i2 = this.header;
        char[] cArr = new char[this.d];
        Context context = new Context();
        context.f1441a = attributeArr;
        context.f1442b = i;
        context.c = cArr;
        int readUnsignedShort = readUnsignedShort(i2);
        String readClass = readClass(i2 + 2, cArr);
        String readClass2 = readClass(i2 + 4, cArr);
        String[] strArr = new String[readUnsignedShort(i2 + 6)];
        int i3 = i2 + 8;
        for (int i4 = 0; i4 < strArr.length; i4++) {
            strArr[i4] = readClass(i3, cArr);
            i3 += 2;
        }
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        Attribute attribute = null;
        int a2 = a();
        for (int readUnsignedShort2 = readUnsignedShort(a2); readUnsignedShort2 > 0; readUnsignedShort2--) {
            String readUTF8 = readUTF8(a2 + 2, cArr);
            if ("SourceFile".equals(readUTF8)) {
                str2 = readUTF8(a2 + 8, cArr);
            } else if ("InnerClasses".equals(readUTF8)) {
                i9 = a2 + 8;
            } else if ("EnclosingMethod".equals(readUTF8)) {
                str4 = readClass(a2 + 8, cArr);
                int readUnsignedShort3 = readUnsignedShort(a2 + 10);
                if (readUnsignedShort3 != 0) {
                    str5 = readUTF8(this.f1438a[readUnsignedShort3], cArr);
                    str6 = readUTF8(this.f1438a[readUnsignedShort3] + 2, cArr);
                }
            } else if ("Signature".equals(readUTF8)) {
                str = readUTF8(a2 + 8, cArr);
            } else if ("RuntimeVisibleAnnotations".equals(readUTF8)) {
                i5 = a2 + 8;
            } else if ("RuntimeVisibleTypeAnnotations".equals(readUTF8)) {
                i7 = a2 + 8;
            } else if ("Deprecated".equals(readUTF8)) {
                readUnsignedShort |= 131072;
            } else if ("Synthetic".equals(readUTF8)) {
                readUnsignedShort |= 266240;
            } else if ("SourceDebugExtension".equals(readUTF8)) {
                int readInt = readInt(a2 + 4);
                str3 = a(a2 + 8, readInt, new char[readInt]);
            } else if ("RuntimeInvisibleAnnotations".equals(readUTF8)) {
                i6 = a2 + 8;
            } else if ("RuntimeInvisibleTypeAnnotations".equals(readUTF8)) {
                i8 = a2 + 8;
            } else if ("BootstrapMethods".equals(readUTF8)) {
                int[] iArr = new int[readUnsignedShort(a2 + 8)];
                int i10 = a2 + 10;
                for (int i11 = 0; i11 < iArr.length; i11++) {
                    iArr[i11] = i10;
                    i10 += (2 + readUnsignedShort(i10 + 2)) << 1;
                }
                context.d = iArr;
            } else {
                Attribute a3 = a(attributeArr, readUTF8, a2 + 8, readInt(a2 + 4), cArr, -1, null);
                if (a3 != null) {
                    a3.f1434a = attribute;
                    attribute = a3;
                }
            }
            a2 += 6 + readInt(a2 + 4);
        }
        classVisitor.visit(readInt(this.f1438a[1] - 7), readUnsignedShort, readClass, str, readClass2, strArr);
        if ((i & 2) == 0 && (str2 != null || str3 != null)) {
            classVisitor.visitSource(str2, str3);
        }
        if (str4 != null) {
            classVisitor.visitOuterClass(str4, str5, str6);
        }
        if (i5 != 0) {
            int i12 = i5 + 2;
            for (int readUnsignedShort4 = readUnsignedShort(i5); readUnsignedShort4 > 0; readUnsignedShort4--) {
                i12 = a(i12 + 2, cArr, true, classVisitor.visitAnnotation(readUTF8(i12, cArr), true));
            }
        }
        if (i6 != 0) {
            int i13 = i6 + 2;
            for (int readUnsignedShort5 = readUnsignedShort(i6); readUnsignedShort5 > 0; readUnsignedShort5--) {
                i13 = a(i13 + 2, cArr, true, classVisitor.visitAnnotation(readUTF8(i13, cArr), false));
            }
        }
        if (i7 != 0) {
            int i14 = i7 + 2;
            for (int readUnsignedShort6 = readUnsignedShort(i7); readUnsignedShort6 > 0; readUnsignedShort6--) {
                int a4 = a(context, i14);
                i14 = a(a4 + 2, cArr, true, classVisitor.visitTypeAnnotation(context.i, context.j, readUTF8(a4, cArr), true));
            }
        }
        if (i8 != 0) {
            int i15 = i8 + 2;
            for (int readUnsignedShort7 = readUnsignedShort(i8); readUnsignedShort7 > 0; readUnsignedShort7--) {
                int a5 = a(context, i15);
                i15 = a(a5 + 2, cArr, true, classVisitor.visitTypeAnnotation(context.i, context.j, readUTF8(a5, cArr), false));
            }
        }
        while (attribute != null) {
            Attribute attribute2 = attribute.f1434a;
            attribute.f1434a = null;
            classVisitor.visitAttribute(attribute);
            attribute = attribute2;
        }
        if (i9 != 0) {
            int i16 = i9 + 2;
            for (int readUnsignedShort8 = readUnsignedShort(i9); readUnsignedShort8 > 0; readUnsignedShort8--) {
                classVisitor.visitInnerClass(readClass(i16, cArr), readClass(i16 + 2, cArr), readUTF8(i16 + 4, cArr), readUnsignedShort(i16 + 6));
                i16 += 8;
            }
        }
        int length = this.header + 10 + (2 * strArr.length);
        for (int readUnsignedShort9 = readUnsignedShort(length - 2); readUnsignedShort9 > 0; readUnsignedShort9--) {
            length = a(classVisitor, context, length);
        }
        int i17 = length + 2;
        for (int readUnsignedShort10 = readUnsignedShort(i17 - 2); readUnsignedShort10 > 0; readUnsignedShort10--) {
            i17 = b(classVisitor, context, i17);
        }
        classVisitor.visitEnd();
    }

    private int a(ClassVisitor classVisitor, Context context, int i) {
        char[] cArr = context.c;
        int readUnsignedShort = readUnsignedShort(i);
        String readUTF8 = readUTF8(i + 2, cArr);
        String readUTF82 = readUTF8(i + 4, cArr);
        int i2 = i + 6;
        String str = null;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        Object obj = null;
        Attribute attribute = null;
        for (int readUnsignedShort2 = readUnsignedShort(i2); readUnsignedShort2 > 0; readUnsignedShort2--) {
            String readUTF83 = readUTF8(i2 + 2, cArr);
            if ("ConstantValue".equals(readUTF83)) {
                int readUnsignedShort3 = readUnsignedShort(i2 + 8);
                obj = readUnsignedShort3 == 0 ? null : readConst(readUnsignedShort3, cArr);
            } else if ("Signature".equals(readUTF83)) {
                str = readUTF8(i2 + 8, cArr);
            } else if ("Deprecated".equals(readUTF83)) {
                readUnsignedShort |= 131072;
            } else if ("Synthetic".equals(readUTF83)) {
                readUnsignedShort |= 266240;
            } else if ("RuntimeVisibleAnnotations".equals(readUTF83)) {
                i3 = i2 + 8;
            } else if ("RuntimeVisibleTypeAnnotations".equals(readUTF83)) {
                i5 = i2 + 8;
            } else if ("RuntimeInvisibleAnnotations".equals(readUTF83)) {
                i4 = i2 + 8;
            } else if ("RuntimeInvisibleTypeAnnotations".equals(readUTF83)) {
                i6 = i2 + 8;
            } else {
                Attribute a2 = a(context.f1441a, readUTF83, i2 + 8, readInt(i2 + 4), cArr, -1, null);
                if (a2 != null) {
                    a2.f1434a = attribute;
                    attribute = a2;
                }
            }
            i2 += 6 + readInt(i2 + 4);
        }
        int i7 = i2 + 2;
        FieldVisitor visitField = classVisitor.visitField(readUnsignedShort, readUTF8, readUTF82, str, obj);
        if (visitField == null) {
            return i7;
        }
        if (i3 != 0) {
            int i8 = i3 + 2;
            for (int readUnsignedShort4 = readUnsignedShort(i3); readUnsignedShort4 > 0; readUnsignedShort4--) {
                i8 = a(i8 + 2, cArr, true, visitField.visitAnnotation(readUTF8(i8, cArr), true));
            }
        }
        if (i4 != 0) {
            int i9 = i4 + 2;
            for (int readUnsignedShort5 = readUnsignedShort(i4); readUnsignedShort5 > 0; readUnsignedShort5--) {
                i9 = a(i9 + 2, cArr, true, visitField.visitAnnotation(readUTF8(i9, cArr), false));
            }
        }
        if (i5 != 0) {
            int i10 = i5 + 2;
            for (int readUnsignedShort6 = readUnsignedShort(i5); readUnsignedShort6 > 0; readUnsignedShort6--) {
                int a3 = a(context, i10);
                i10 = a(a3 + 2, cArr, true, visitField.visitTypeAnnotation(context.i, context.j, readUTF8(a3, cArr), true));
            }
        }
        if (i6 != 0) {
            int i11 = i6 + 2;
            for (int readUnsignedShort7 = readUnsignedShort(i6); readUnsignedShort7 > 0; readUnsignedShort7--) {
                int a4 = a(context, i11);
                i11 = a(a4 + 2, cArr, true, visitField.visitTypeAnnotation(context.i, context.j, readUTF8(a4, cArr), false));
            }
        }
        while (attribute != null) {
            Attribute attribute2 = attribute.f1434a;
            attribute.f1434a = null;
            visitField.visitAttribute(attribute);
            attribute = attribute2;
        }
        visitField.visitEnd();
        return i7;
    }

    private int b(ClassVisitor classVisitor, Context context, int i) {
        char[] cArr = context.c;
        context.e = readUnsignedShort(i);
        context.f = readUTF8(i + 2, cArr);
        context.g = readUTF8(i + 4, cArr);
        int i2 = i + 6;
        int i3 = 0;
        int i4 = 0;
        String[] strArr = null;
        String str = null;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        Attribute attribute = null;
        for (int readUnsignedShort = readUnsignedShort(i2); readUnsignedShort > 0; readUnsignedShort--) {
            String readUTF8 = readUTF8(i2 + 2, cArr);
            if ("Code".equals(readUTF8)) {
                if ((context.f1442b & 1) == 0) {
                    i3 = i2 + 8;
                }
            } else if ("Exceptions".equals(readUTF8)) {
                strArr = new String[readUnsignedShort(i2 + 8)];
                i4 = i2 + 10;
                for (int i13 = 0; i13 < strArr.length; i13++) {
                    strArr[i13] = readClass(i4, cArr);
                    i4 += 2;
                }
            } else if ("Signature".equals(readUTF8)) {
                str = readUTF8(i2 + 8, cArr);
            } else if ("Deprecated".equals(readUTF8)) {
                context.e |= 131072;
            } else if ("RuntimeVisibleAnnotations".equals(readUTF8)) {
                i6 = i2 + 8;
            } else if ("RuntimeVisibleTypeAnnotations".equals(readUTF8)) {
                i8 = i2 + 8;
            } else if ("AnnotationDefault".equals(readUTF8)) {
                i10 = i2 + 8;
            } else if ("Synthetic".equals(readUTF8)) {
                context.e |= 266240;
            } else if ("RuntimeInvisibleAnnotations".equals(readUTF8)) {
                i7 = i2 + 8;
            } else if ("RuntimeInvisibleTypeAnnotations".equals(readUTF8)) {
                i9 = i2 + 8;
            } else if ("RuntimeVisibleParameterAnnotations".equals(readUTF8)) {
                i11 = i2 + 8;
            } else if ("RuntimeInvisibleParameterAnnotations".equals(readUTF8)) {
                i12 = i2 + 8;
            } else if ("MethodParameters".equals(readUTF8)) {
                i5 = i2 + 8;
            } else {
                Attribute a2 = a(context.f1441a, readUTF8, i2 + 8, readInt(i2 + 4), cArr, -1, null);
                if (a2 != null) {
                    a2.f1434a = attribute;
                    attribute = a2;
                }
            }
            i2 += 6 + readInt(i2 + 4);
        }
        int i14 = i2 + 2;
        MethodVisitor visitMethod = classVisitor.visitMethod(context.e, context.f, context.g, str, strArr);
        if (visitMethod == null) {
            return i14;
        }
        if (visitMethod instanceof MethodWriter) {
            MethodWriter methodWriter = (MethodWriter) visitMethod;
            if (methodWriter.f1456b.M == this && str == methodWriter.g) {
                boolean z = false;
                if (strArr == null) {
                    z = methodWriter.j == 0;
                } else if (strArr.length == methodWriter.j) {
                    z = true;
                    int length = strArr.length - 1;
                    while (true) {
                        if (length < 0) {
                            break;
                        }
                        i4 -= 2;
                        if (methodWriter.k[length] != readUnsignedShort(i4)) {
                            z = false;
                            break;
                        }
                        length--;
                    }
                }
                if (z) {
                    methodWriter.h = i2;
                    methodWriter.i = i14 - i2;
                    return i14;
                }
            }
        }
        if (i5 != 0) {
            int i15 = this.f1437b[i5] & 255;
            int i16 = i5;
            int i17 = 1;
            while (true) {
                int i18 = i16 + i17;
                if (i15 <= 0) {
                    break;
                }
                visitMethod.visitParameter(readUTF8(i18, cArr), readUnsignedShort(i18 + 2));
                i15--;
                i16 = i18;
                i17 = 4;
            }
        }
        if (i10 != 0) {
            AnnotationVisitor visitAnnotationDefault = visitMethod.visitAnnotationDefault();
            a(i10, cArr, (String) null, visitAnnotationDefault);
            if (visitAnnotationDefault != null) {
                visitAnnotationDefault.visitEnd();
            }
        }
        if (i6 != 0) {
            int i19 = i6 + 2;
            for (int readUnsignedShort2 = readUnsignedShort(i6); readUnsignedShort2 > 0; readUnsignedShort2--) {
                i19 = a(i19 + 2, cArr, true, visitMethod.visitAnnotation(readUTF8(i19, cArr), true));
            }
        }
        if (i7 != 0) {
            int i20 = i7 + 2;
            for (int readUnsignedShort3 = readUnsignedShort(i7); readUnsignedShort3 > 0; readUnsignedShort3--) {
                i20 = a(i20 + 2, cArr, true, visitMethod.visitAnnotation(readUTF8(i20, cArr), false));
            }
        }
        if (i8 != 0) {
            int i21 = i8 + 2;
            for (int readUnsignedShort4 = readUnsignedShort(i8); readUnsignedShort4 > 0; readUnsignedShort4--) {
                int a3 = a(context, i21);
                i21 = a(a3 + 2, cArr, true, visitMethod.visitTypeAnnotation(context.i, context.j, readUTF8(a3, cArr), true));
            }
        }
        if (i9 != 0) {
            int i22 = i9 + 2;
            for (int readUnsignedShort5 = readUnsignedShort(i9); readUnsignedShort5 > 0; readUnsignedShort5--) {
                int a4 = a(context, i22);
                i22 = a(a4 + 2, cArr, true, visitMethod.visitTypeAnnotation(context.i, context.j, readUTF8(a4, cArr), false));
            }
        }
        if (i11 != 0) {
            b(visitMethod, context, i11, true);
        }
        if (i12 != 0) {
            b(visitMethod, context, i12, false);
        }
        while (attribute != null) {
            Attribute attribute2 = attribute.f1434a;
            attribute.f1434a = null;
            visitMethod.visitAttribute(attribute);
            attribute = attribute2;
        }
        if (i3 != 0) {
            visitMethod.visitCode();
            a(visitMethod, context, i3);
        }
        visitMethod.visitEnd();
        return i14;
    }

    private void a(MethodVisitor methodVisitor, Context context, int i) {
        int readUnsignedShort;
        Attribute read;
        Label label;
        byte[] bArr = this.f1437b;
        char[] cArr = context.c;
        int readUnsignedShort2 = readUnsignedShort(i);
        int readUnsignedShort3 = readUnsignedShort(i + 2);
        int readInt = readInt(i + 4);
        int i2 = i + 8;
        int i3 = i2 + readInt;
        Label[] labelArr = new Label[readInt + 2];
        context.h = labelArr;
        readLabel(readInt + 1, labelArr);
        while (i2 < i3) {
            int i4 = i2 - i2;
            switch (ClassWriter.f1439a[bArr[i2] & 255]) {
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
                    readLabel(i4 + readShort(i2 + 1), labelArr);
                    i2 += 3;
                    break;
                case 10:
                    readLabel(i4 + readInt(i2 + 1), labelArr);
                    i2 += 5;
                    break;
                case 14:
                    int i5 = (i2 + 4) - (i4 & 3);
                    readLabel(i4 + readInt(i5), labelArr);
                    for (int readInt2 = (readInt(i5 + 8) - readInt(i5 + 4)) + 1; readInt2 > 0; readInt2--) {
                        readLabel(i4 + readInt(i5 + 12), labelArr);
                        i5 += 4;
                    }
                    i2 = i5 + 12;
                    break;
                case 15:
                    int i6 = (i2 + 4) - (i4 & 3);
                    readLabel(i4 + readInt(i6), labelArr);
                    for (int readInt3 = readInt(i6 + 4); readInt3 > 0; readInt3--) {
                        readLabel(i4 + readInt(i6 + 12), labelArr);
                        i6 += 8;
                    }
                    i2 = i6 + 8;
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
        }
        for (int readUnsignedShort4 = readUnsignedShort(i2); readUnsignedShort4 > 0; readUnsignedShort4--) {
            methodVisitor.visitTryCatchBlock(readLabel(readUnsignedShort(i2 + 2), labelArr), readLabel(readUnsignedShort(i2 + 4), labelArr), readLabel(readUnsignedShort(i2 + 6), labelArr), readUTF8(this.f1438a[readUnsignedShort(i2 + 8)], cArr));
            i2 += 8;
        }
        int i7 = i2 + 2;
        int[] iArr = null;
        int[] iArr2 = null;
        int i8 = 0;
        int i9 = 0;
        int i10 = -1;
        int i11 = -1;
        int i12 = 0;
        int i13 = 0;
        boolean z = true;
        boolean z2 = (context.f1442b & 8) != 0;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        Context context2 = null;
        Attribute attribute = null;
        for (int readUnsignedShort5 = readUnsignedShort(i7); readUnsignedShort5 > 0; readUnsignedShort5--) {
            String readUTF8 = readUTF8(i7 + 2, cArr);
            if ("LocalVariableTable".equals(readUTF8)) {
                if ((context.f1442b & 2) == 0) {
                    i12 = i7 + 8;
                    int i17 = i7;
                    for (int readUnsignedShort6 = readUnsignedShort(i7 + 8); readUnsignedShort6 > 0; readUnsignedShort6--) {
                        int readUnsignedShort7 = readUnsignedShort(i17 + 10);
                        if (labelArr[readUnsignedShort7] == null) {
                            readLabel(readUnsignedShort7, labelArr).f1454a |= 1;
                        }
                        int readUnsignedShort8 = readUnsignedShort7 + readUnsignedShort(i17 + 12);
                        if (labelArr[readUnsignedShort8] == null) {
                            readLabel(readUnsignedShort8, labelArr).f1454a |= 1;
                        }
                        i17 += 10;
                    }
                }
            } else if ("LocalVariableTypeTable".equals(readUTF8)) {
                i13 = i7 + 8;
            } else if ("LineNumberTable".equals(readUTF8)) {
                if ((context.f1442b & 2) == 0) {
                    int i18 = i7;
                    for (int readUnsignedShort9 = readUnsignedShort(i7 + 8); readUnsignedShort9 > 0; readUnsignedShort9--) {
                        int readUnsignedShort10 = readUnsignedShort(i18 + 10);
                        if (labelArr[readUnsignedShort10] == null) {
                            readLabel(readUnsignedShort10, labelArr).f1454a |= 1;
                        }
                        Label label2 = labelArr[readUnsignedShort10];
                        while (true) {
                            label = label2;
                            if (label.f1455b > 0) {
                                if (label.k == null) {
                                    label.k = new Label();
                                }
                                label2 = label.k;
                            }
                        }
                        label.f1455b = readUnsignedShort(i18 + 12);
                        i18 += 4;
                    }
                }
            } else if ("RuntimeVisibleTypeAnnotations".equals(readUTF8)) {
                int[] a2 = a(methodVisitor, context, i7 + 8, true);
                iArr = a2;
                i10 = (a2.length == 0 || readByte(iArr[0]) < 67) ? -1 : readUnsignedShort(iArr[0] + 1);
            } else if ("RuntimeInvisibleTypeAnnotations".equals(readUTF8)) {
                int[] a3 = a(methodVisitor, context, i7 + 8, false);
                iArr2 = a3;
                i11 = (a3.length == 0 || readByte(iArr2[0]) < 67) ? -1 : readUnsignedShort(iArr2[0] + 1);
            } else if ("StackMapTable".equals(readUTF8)) {
                if ((context.f1442b & 4) == 0) {
                    i14 = i7 + 10;
                    i15 = readInt(i7 + 4);
                    i16 = readUnsignedShort(i7 + 8);
                }
            } else if (!"StackMap".equals(readUTF8)) {
                for (int i19 = 0; i19 < context.f1441a.length; i19++) {
                    if (context.f1441a[i19].type.equals(readUTF8) && (read = context.f1441a[i19].read(this, i7 + 8, readInt(i7 + 4), cArr, i2 - 8, labelArr)) != null) {
                        read.f1434a = attribute;
                        attribute = read;
                    }
                }
            } else if ((context.f1442b & 4) == 0) {
                z = false;
                i14 = i7 + 10;
                i15 = readInt(i7 + 4);
                i16 = readUnsignedShort(i7 + 8);
            }
            i7 += 6 + readInt(i7 + 4);
        }
        if (i14 != 0) {
            context2 = context;
            context.o = -1;
            context2.p = 0;
            context2.q = 0;
            context2.r = 0;
            context2.t = 0;
            context2.s = new Object[readUnsignedShort3];
            context2.u = new Object[readUnsignedShort2];
            if (z2) {
                a(context);
            }
            for (int i20 = i14; i20 < (i14 + i15) - 2; i20++) {
                if (bArr[i20] == 8 && (readUnsignedShort = readUnsignedShort(i20 + 1)) >= 0 && readUnsignedShort < readInt && (bArr[i2 + readUnsignedShort] & 255) == 187) {
                    readLabel(readUnsignedShort, labelArr);
                }
            }
        }
        int i21 = i2;
        while (i21 < i3) {
            int i22 = i21 - i2;
            Label label3 = labelArr[i22];
            if (label3 != null) {
                label3.k = null;
                methodVisitor.visitLabel(label3);
                if ((context.f1442b & 2) == 0 && label3.f1455b > 0) {
                    methodVisitor.visitLineNumber(label3.f1455b, label3);
                    for (Label label4 = label3.k; label4 != null; label4 = label4.k) {
                        methodVisitor.visitLineNumber(label4.f1455b, label3);
                    }
                }
            }
            while (context2 != null && (context2.o == i22 || context2.o == -1)) {
                if (context2.o != -1) {
                    if (!z || z2) {
                        methodVisitor.visitFrame(-1, context2.q, context2.s, context2.t, context2.u);
                    } else {
                        methodVisitor.visitFrame(context2.p, context2.r, context2.s, context2.t, context2.u);
                    }
                }
                if (i16 > 0) {
                    i14 = a(i14, z, z2, context2);
                    i16--;
                } else {
                    context2 = null;
                }
            }
            int i23 = bArr[i21] & 255;
            switch (ClassWriter.f1439a[i23]) {
                case 0:
                    methodVisitor.visitInsn(i23);
                    i21++;
                    break;
                case 1:
                    methodVisitor.visitIntInsn(i23, bArr[i21 + 1]);
                    i21 += 2;
                    break;
                case 2:
                    methodVisitor.visitIntInsn(i23, readShort(i21 + 1));
                    i21 += 3;
                    break;
                case 3:
                    methodVisitor.visitVarInsn(i23, bArr[i21 + 1] & 255);
                    i21 += 2;
                    break;
                case 4:
                    if (i23 > 54) {
                        int i24 = i23 - 59;
                        methodVisitor.visitVarInsn(54 + (i24 >> 2), i24 & 3);
                    } else {
                        int i25 = i23 - 26;
                        methodVisitor.visitVarInsn(21 + (i25 >> 2), i25 & 3);
                    }
                    i21++;
                    break;
                case 5:
                    methodVisitor.visitTypeInsn(i23, readClass(i21 + 1, cArr));
                    i21 += 3;
                    break;
                case 6:
                case 7:
                    int i26 = this.f1438a[readUnsignedShort(i21 + 1)];
                    boolean z3 = bArr[i26 - 1] == 11;
                    String readClass = readClass(i26, cArr);
                    int i27 = this.f1438a[readUnsignedShort(i26 + 2)];
                    String readUTF82 = readUTF8(i27, cArr);
                    String readUTF83 = readUTF8(i27 + 2, cArr);
                    if (i23 < 182) {
                        methodVisitor.visitFieldInsn(i23, readClass, readUTF82, readUTF83);
                    } else {
                        methodVisitor.visitMethodInsn(i23, readClass, readUTF82, readUTF83, z3);
                    }
                    if (i23 == 185) {
                        i21 += 5;
                        break;
                    } else {
                        i21 += 3;
                        break;
                    }
                case 8:
                    int i28 = this.f1438a[readUnsignedShort(i21 + 1)];
                    int i29 = context.d[readUnsignedShort(i28)];
                    Handle handle = (Handle) readConst(readUnsignedShort(i29), cArr);
                    int readUnsignedShort11 = readUnsignedShort(i29 + 2);
                    Object[] objArr = new Object[readUnsignedShort11];
                    int i30 = i29 + 4;
                    for (int i31 = 0; i31 < readUnsignedShort11; i31++) {
                        objArr[i31] = readConst(readUnsignedShort(i30), cArr);
                        i30 += 2;
                    }
                    int i32 = this.f1438a[readUnsignedShort(i28 + 2)];
                    methodVisitor.visitInvokeDynamicInsn(readUTF8(i32, cArr), readUTF8(i32 + 2, cArr), handle, objArr);
                    i21 += 5;
                    break;
                case 9:
                    methodVisitor.visitJumpInsn(i23, labelArr[i22 + readShort(i21 + 1)]);
                    i21 += 3;
                    break;
                case 10:
                    methodVisitor.visitJumpInsn(i23 - 33, labelArr[i22 + readInt(i21 + 1)]);
                    i21 += 5;
                    break;
                case 11:
                    methodVisitor.visitLdcInsn(readConst(bArr[i21 + 1] & 255, cArr));
                    i21 += 2;
                    break;
                case 12:
                    methodVisitor.visitLdcInsn(readConst(readUnsignedShort(i21 + 1), cArr));
                    i21 += 3;
                    break;
                case 13:
                    methodVisitor.visitIincInsn(bArr[i21 + 1] & 255, bArr[i21 + 2]);
                    i21 += 3;
                    break;
                case 14:
                    int i33 = (i21 + 4) - (i22 & 3);
                    int readInt4 = i22 + readInt(i33);
                    int readInt5 = readInt(i33 + 4);
                    int readInt6 = readInt(i33 + 8);
                    Label[] labelArr2 = new Label[(readInt6 - readInt5) + 1];
                    i21 = i33 + 12;
                    for (int i34 = 0; i34 < labelArr2.length; i34++) {
                        labelArr2[i34] = labelArr[i22 + readInt(i21)];
                        i21 += 4;
                    }
                    methodVisitor.visitTableSwitchInsn(readInt5, readInt6, labelArr[readInt4], labelArr2);
                    break;
                case 15:
                    int i35 = (i21 + 4) - (i22 & 3);
                    int readInt7 = i22 + readInt(i35);
                    int readInt8 = readInt(i35 + 4);
                    int[] iArr3 = new int[readInt8];
                    Label[] labelArr3 = new Label[readInt8];
                    i21 = i35 + 8;
                    for (int i36 = 0; i36 < readInt8; i36++) {
                        iArr3[i36] = readInt(i21);
                        labelArr3[i36] = labelArr[i22 + readInt(i21 + 4)];
                        i21 += 8;
                    }
                    methodVisitor.visitLookupSwitchInsn(labelArr[readInt7], iArr3, labelArr3);
                    break;
                case 16:
                default:
                    methodVisitor.visitMultiANewArrayInsn(readClass(i21 + 1, cArr), bArr[i21 + 3] & 255);
                    i21 += 4;
                    break;
                case 17:
                    int i37 = bArr[i21 + 1] & 255;
                    if (i37 == 132) {
                        methodVisitor.visitIincInsn(readUnsignedShort(i21 + 2), readShort(i21 + 4));
                        i21 += 6;
                        break;
                    } else {
                        methodVisitor.visitVarInsn(i37, readUnsignedShort(i21 + 2));
                        i21 += 4;
                        break;
                    }
            }
            while (iArr != null && i8 < iArr.length && i10 <= i22) {
                if (i10 == i22) {
                    int a4 = a(context, iArr[i8]);
                    a(a4 + 2, cArr, true, methodVisitor.visitInsnAnnotation(context.i, context.j, readUTF8(a4, cArr), true));
                }
                i8++;
                i10 = (i8 >= iArr.length || readByte(iArr[i8]) < 67) ? -1 : readUnsignedShort(iArr[i8] + 1);
            }
            while (iArr2 != null && i9 < iArr2.length && i11 <= i22) {
                if (i11 == i22) {
                    int a5 = a(context, iArr2[i9]);
                    a(a5 + 2, cArr, true, methodVisitor.visitInsnAnnotation(context.i, context.j, readUTF8(a5, cArr), false));
                }
                i9++;
                i11 = (i9 >= iArr2.length || readByte(iArr2[i9]) < 67) ? -1 : readUnsignedShort(iArr2[i9] + 1);
            }
        }
        if (labelArr[readInt] != null) {
            methodVisitor.visitLabel(labelArr[readInt]);
        }
        if ((context.f1442b & 2) == 0 && i12 != 0) {
            int[] iArr4 = null;
            if (i13 != 0) {
                int i38 = i13 + 2;
                int[] iArr5 = new int[readUnsignedShort(i13) * 3];
                iArr4 = iArr5;
                int length = iArr5.length;
                while (length > 0) {
                    int i39 = length - 1;
                    iArr4[i39] = i38 + 6;
                    int i40 = i39 - 1;
                    iArr4[i40] = readUnsignedShort(i38 + 8);
                    length = i40 - 1;
                    iArr4[length] = readUnsignedShort(i38);
                    i38 += 10;
                }
            }
            int i41 = i12 + 2;
            for (int readUnsignedShort12 = readUnsignedShort(i12); readUnsignedShort12 > 0; readUnsignedShort12--) {
                int readUnsignedShort13 = readUnsignedShort(i41);
                int readUnsignedShort14 = readUnsignedShort(i41 + 2);
                int readUnsignedShort15 = readUnsignedShort(i41 + 8);
                String str = null;
                if (iArr4 != null) {
                    int i42 = 0;
                    while (true) {
                        if (i42 >= iArr4.length) {
                            break;
                        }
                        if (iArr4[i42] == readUnsignedShort13 && iArr4[i42 + 1] == readUnsignedShort15) {
                            str = readUTF8(iArr4[i42 + 2], cArr);
                        } else {
                            i42 += 3;
                        }
                    }
                }
                methodVisitor.visitLocalVariable(readUTF8(i41 + 4, cArr), readUTF8(i41 + 6, cArr), str, labelArr[readUnsignedShort13], labelArr[readUnsignedShort13 + readUnsignedShort14], readUnsignedShort15);
                i41 += 10;
            }
        }
        if (iArr != null) {
            for (int i43 = 0; i43 < iArr.length; i43++) {
                if ((readByte(iArr[i43]) >> 1) == 32) {
                    int a6 = a(context, iArr[i43]);
                    a(a6 + 2, cArr, true, methodVisitor.visitLocalVariableAnnotation(context.i, context.j, context.l, context.m, context.n, readUTF8(a6, cArr), true));
                }
            }
        }
        if (iArr2 != null) {
            for (int i44 = 0; i44 < iArr2.length; i44++) {
                if ((readByte(iArr2[i44]) >> 1) == 32) {
                    int a7 = a(context, iArr2[i44]);
                    a(a7 + 2, cArr, true, methodVisitor.visitLocalVariableAnnotation(context.i, context.j, context.l, context.m, context.n, readUTF8(a7, cArr), false));
                }
            }
        }
        while (attribute != null) {
            Attribute attribute2 = attribute.f1434a;
            attribute.f1434a = null;
            methodVisitor.visitAttribute(attribute);
            attribute = attribute2;
        }
        methodVisitor.visitMaxs(readUnsignedShort2, readUnsignedShort3);
    }

    private int[] a(MethodVisitor methodVisitor, Context context, int i, boolean z) {
        int i2;
        int a2;
        char[] cArr = context.c;
        int[] iArr = new int[readUnsignedShort(i)];
        int i3 = i + 2;
        for (int i4 = 0; i4 < iArr.length; i4++) {
            iArr[i4] = i3;
            int readInt = readInt(i3);
            switch (readInt >>> 24) {
                case 0:
                case 1:
                case 22:
                    i2 = i3 + 2;
                    break;
                case 19:
                case 20:
                case 21:
                    i2 = i3 + 1;
                    break;
                case 64:
                case 65:
                    for (int readUnsignedShort = readUnsignedShort(i3 + 1); readUnsignedShort > 0; readUnsignedShort--) {
                        int readUnsignedShort2 = readUnsignedShort(i3 + 3);
                        int readUnsignedShort3 = readUnsignedShort(i3 + 5);
                        readLabel(readUnsignedShort2, context.h);
                        readLabel(readUnsignedShort2 + readUnsignedShort3, context.h);
                        i3 += 6;
                    }
                    i2 = i3 + 3;
                    break;
                case 71:
                case 72:
                case 73:
                case 74:
                case 75:
                    i2 = i3 + 4;
                    break;
                default:
                    i2 = i3 + 3;
                    break;
            }
            int readByte = readByte(i2);
            if ((readInt >>> 24) == 66) {
                TypePath typePath = readByte == 0 ? null : new TypePath(this.f1437b, i2);
                int i5 = i2 + 1 + (2 * readByte);
                a2 = a(i5 + 2, cArr, true, methodVisitor.visitTryCatchAnnotation(readInt, typePath, readUTF8(i5, cArr), z));
            } else {
                a2 = a(i2 + 3 + (2 * readByte), cArr, true, (AnnotationVisitor) null);
            }
            i3 = a2;
        }
        return iArr;
    }

    private int a(Context context, int i) {
        int i2;
        int i3;
        int readInt = readInt(i);
        switch (readInt >>> 24) {
            case 0:
            case 1:
            case 22:
                i2 = readInt & (-65536);
                i3 = i + 2;
                break;
            case 19:
            case 20:
            case 21:
                i2 = readInt & (-16777216);
                i3 = i + 1;
                break;
            case 64:
            case 65:
                i2 = readInt & (-16777216);
                int readUnsignedShort = readUnsignedShort(i + 1);
                context.l = new Label[readUnsignedShort];
                context.m = new Label[readUnsignedShort];
                context.n = new int[readUnsignedShort];
                i3 = i + 3;
                for (int i4 = 0; i4 < readUnsignedShort; i4++) {
                    int readUnsignedShort2 = readUnsignedShort(i3);
                    int readUnsignedShort3 = readUnsignedShort(i3 + 2);
                    context.l[i4] = readLabel(readUnsignedShort2, context.h);
                    context.m[i4] = readLabel(readUnsignedShort2 + readUnsignedShort3, context.h);
                    context.n[i4] = readUnsignedShort(i3 + 4);
                    i3 += 6;
                }
                break;
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
                i2 = readInt & (-16776961);
                i3 = i + 4;
                break;
            default:
                i2 = readInt & ((readInt >>> 24) < 67 ? -256 : -16777216);
                i3 = i + 3;
                break;
        }
        int readByte = readByte(i3);
        context.i = i2;
        context.j = readByte == 0 ? null : new TypePath(this.f1437b, i3);
        return i3 + 1 + (2 * readByte);
    }

    private void b(MethodVisitor methodVisitor, Context context, int i, boolean z) {
        int i2 = i + 1;
        int i3 = this.f1437b[i] & 255;
        int length = Type.getArgumentTypes(context.g).length - i3;
        int i4 = 0;
        while (i4 < length) {
            AnnotationVisitor visitParameterAnnotation = methodVisitor.visitParameterAnnotation(i4, "Ljava/lang/Synthetic;", false);
            if (visitParameterAnnotation != null) {
                visitParameterAnnotation.visitEnd();
            }
            i4++;
        }
        char[] cArr = context.c;
        while (i4 < i3 + length) {
            i2 += 2;
            for (int readUnsignedShort = readUnsignedShort(i2); readUnsignedShort > 0; readUnsignedShort--) {
                i2 = a(i2 + 2, cArr, true, methodVisitor.visitParameterAnnotation(i4, readUTF8(i2, cArr), z));
            }
            i4++;
        }
    }

    private int a(int i, char[] cArr, boolean z, AnnotationVisitor annotationVisitor) {
        int readUnsignedShort = readUnsignedShort(i);
        int i2 = i + 2;
        if (z) {
            while (readUnsignedShort > 0) {
                i2 = a(i2 + 2, cArr, readUTF8(i2, cArr), annotationVisitor);
                readUnsignedShort--;
            }
        } else {
            while (readUnsignedShort > 0) {
                i2 = a(i2, cArr, (String) null, annotationVisitor);
                readUnsignedShort--;
            }
        }
        if (annotationVisitor != null) {
            annotationVisitor.visitEnd();
        }
        return i2;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r3v48 ??, still in use, count: 3, list:
          (r3v48 ?? I:java.lang.Object) from 0x0169: INVOKE (r13v0 java.lang.String), (r3v48 ?? I:java.lang.String), (r3v48 ?? I:java.lang.Object) VIRTUAL call: com.esotericsoftware.asm.AnnotationVisitor.visit(java.lang.String, java.lang.Object):void A[MD:(java.lang.String, java.lang.Object):void (m)]
          (r3v48 ?? I:java.lang.String) from 0x0169: INVOKE (r13v0 java.lang.String), (r3v48 ?? I:java.lang.String), (r3v48 ?? I:java.lang.Object) VIRTUAL call: com.esotericsoftware.asm.AnnotationVisitor.visit(java.lang.String, java.lang.Object):void A[MD:(java.lang.String, java.lang.Object):void (m)]
          (r3v48 ?? I:byte) from 0x0166: CONSTRUCTOR (r2v60 java.lang.Byte) = (r3v48 ?? I:byte) A[MD:(byte):void (c)] call: java.lang.Byte.<init>(byte):void type: CONSTRUCTOR
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:151)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:116)
        	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:80)
        	at jadx.core.utils.InsnRemover.addAndUnbind(InsnRemover.java:56)
        	at jadx.core.dex.visitors.ModVisitor.removeStep(ModVisitor.java:447)
        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:96)
        */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v35, types: [java.lang.Character, char, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v40, types: [short, java.lang.Short, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v48, types: [java.lang.Object, java.lang.String, byte, java.lang.Byte] */
    private int a(int r11, char[] r12, java.lang.String r13, com.esotericsoftware.asm.AnnotationVisitor r14) {
        /*
            Method dump skipped, instructions count: 1203
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.esotericsoftware.asm.ClassReader.a(int, char[], java.lang.String, com.esotericsoftware.asm.AnnotationVisitor):int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x016c, code lost:            r7.q = r10;     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0172, code lost:            return;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(com.esotericsoftware.asm.Context r7) {
        /*
            Method dump skipped, instructions count: 371
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.esotericsoftware.asm.ClassReader.a(com.esotericsoftware.asm.Context):void");
    }

    private int a(int i, boolean z, boolean z2, Context context) {
        int i2;
        int readUnsignedShort;
        char[] cArr = context.c;
        Label[] labelArr = context.h;
        if (z) {
            i++;
            i2 = this.f1437b[i] & 255;
        } else {
            i2 = 255;
            context.o = -1;
        }
        context.r = 0;
        if (i2 < 64) {
            readUnsignedShort = i2;
            context.p = 3;
            context.t = 0;
        } else if (i2 < 128) {
            readUnsignedShort = i2 - 64;
            i = a(context.u, 0, i, cArr, labelArr);
            context.p = 4;
            context.t = 1;
        } else {
            readUnsignedShort = readUnsignedShort(i);
            i += 2;
            if (i2 == 247) {
                i = a(context.u, 0, i, cArr, labelArr);
                context.p = 4;
                context.t = 1;
            } else if (i2 >= 248 && i2 < 251) {
                context.p = 2;
                context.r = User32.VK_ZOOM - i2;
                context.q -= context.r;
                context.t = 0;
            } else if (i2 == 251) {
                context.p = 3;
                context.t = 0;
            } else if (i2 < 255) {
                int i3 = z2 ? context.q : 0;
                for (int i4 = i2 - User32.VK_ZOOM; i4 > 0; i4--) {
                    int i5 = i3;
                    i3++;
                    i = a(context.s, i5, i, cArr, labelArr);
                }
                context.p = 1;
                context.r = i2 - User32.VK_ZOOM;
                context.q += context.r;
                context.t = 0;
            } else {
                context.p = 0;
                int readUnsignedShort2 = readUnsignedShort(i);
                int i6 = i + 2;
                context.r = readUnsignedShort2;
                context.q = readUnsignedShort2;
                int i7 = 0;
                while (readUnsignedShort2 > 0) {
                    int i8 = i7;
                    i7++;
                    i6 = a(context.s, i8, i6, cArr, labelArr);
                    readUnsignedShort2--;
                }
                int readUnsignedShort3 = readUnsignedShort(i6);
                i = i6 + 2;
                context.t = readUnsignedShort3;
                int i9 = 0;
                while (readUnsignedShort3 > 0) {
                    int i10 = i9;
                    i9++;
                    i = a(context.u, i10, i, cArr, labelArr);
                    readUnsignedShort3--;
                }
            }
        }
        context.o += readUnsignedShort + 1;
        readLabel(context.o, labelArr);
        return i;
    }

    private int a(Object[] objArr, int i, int i2, char[] cArr, Label[] labelArr) {
        int i3 = i2 + 1;
        switch (this.f1437b[i2] & 255) {
            case 0:
                objArr[i] = Opcodes.TOP;
                break;
            case 1:
                objArr[i] = Opcodes.INTEGER;
                break;
            case 2:
                objArr[i] = Opcodes.FLOAT;
                break;
            case 3:
                objArr[i] = Opcodes.DOUBLE;
                break;
            case 4:
                objArr[i] = Opcodes.LONG;
                break;
            case 5:
                objArr[i] = Opcodes.NULL;
                break;
            case 6:
                objArr[i] = Opcodes.UNINITIALIZED_THIS;
                break;
            case 7:
                objArr[i] = readClass(i3, cArr);
                i3 += 2;
                break;
            default:
                objArr[i] = readLabel(readUnsignedShort(i3), labelArr);
                i3 += 2;
                break;
        }
        return i3;
    }

    protected Label readLabel(int i, Label[] labelArr) {
        if (labelArr[i] == null) {
            labelArr[i] = new Label();
        }
        return labelArr[i];
    }

    private int a() {
        int readUnsignedShort = this.header + 8 + (readUnsignedShort(this.header + 6) << 1);
        for (int readUnsignedShort2 = readUnsignedShort(readUnsignedShort); readUnsignedShort2 > 0; readUnsignedShort2--) {
            for (int readUnsignedShort3 = readUnsignedShort(readUnsignedShort + 8); readUnsignedShort3 > 0; readUnsignedShort3--) {
                readUnsignedShort += 6 + readInt(readUnsignedShort + 12);
            }
            readUnsignedShort += 8;
        }
        int i = readUnsignedShort + 2;
        for (int readUnsignedShort4 = readUnsignedShort(i); readUnsignedShort4 > 0; readUnsignedShort4--) {
            for (int readUnsignedShort5 = readUnsignedShort(i + 8); readUnsignedShort5 > 0; readUnsignedShort5--) {
                i += 6 + readInt(i + 12);
            }
            i += 8;
        }
        return i + 2;
    }

    private Attribute a(Attribute[] attributeArr, String str, int i, int i2, char[] cArr, int i3, Label[] labelArr) {
        for (int i4 = 0; i4 < attributeArr.length; i4++) {
            if (attributeArr[i4].type.equals(str)) {
                return attributeArr[i4].read(this, i, i2, cArr, i3, labelArr);
            }
        }
        return new Attribute(str).read(this, i, i2, null, -1, null);
    }

    public int getItemCount() {
        return this.f1438a.length;
    }

    public int getItem(int i) {
        return this.f1438a[i];
    }

    public int getMaxStringLength() {
        return this.d;
    }

    public int readByte(int i) {
        return this.f1437b[i] & 255;
    }

    public int readUnsignedShort(int i) {
        byte[] bArr = this.f1437b;
        return ((bArr[i] & 255) << 8) | (bArr[i + 1] & 255);
    }

    public short readShort(int i) {
        byte[] bArr = this.f1437b;
        return (short) (((bArr[i] & 255) << 8) | (bArr[i + 1] & 255));
    }

    public int readInt(int i) {
        byte[] bArr = this.f1437b;
        return ((bArr[i] & 255) << 24) | ((bArr[i + 1] & 255) << 16) | ((bArr[i + 2] & 255) << 8) | (bArr[i + 3] & 255);
    }

    public long readLong(int i) {
        return (readInt(i) << 32) | (readInt(i + 4) & 4294967295L);
    }

    public String readUTF8(int i, char[] cArr) {
        int readUnsignedShort = readUnsignedShort(i);
        if (i == 0 || readUnsignedShort == 0) {
            return null;
        }
        String str = this.c[readUnsignedShort];
        if (str != null) {
            return str;
        }
        int i2 = this.f1438a[readUnsignedShort];
        String[] strArr = this.c;
        String a2 = a(i2 + 2, readUnsignedShort(i2), cArr);
        strArr[readUnsignedShort] = a2;
        return a2;
    }

    private String a(int i, int i2, char[] cArr) {
        int i3 = i + i2;
        byte[] bArr = this.f1437b;
        int i4 = 0;
        boolean z = false;
        char c = 0;
        while (i < i3) {
            int i5 = i;
            i++;
            byte b2 = bArr[i5];
            switch (z) {
                case false:
                    int i6 = b2 & 255;
                    if (i6 >= 128) {
                        if (i6 < 224 && i6 > 191) {
                            c = (char) (i6 & 31);
                            z = true;
                            break;
                        } else {
                            c = (char) (i6 & 15);
                            z = 2;
                            break;
                        }
                    } else {
                        int i7 = i4;
                        i4++;
                        cArr[i7] = (char) i6;
                        break;
                    }
                case true:
                    int i8 = i4;
                    i4++;
                    cArr[i8] = (char) ((c << 6) | (b2 & 63));
                    z = false;
                    break;
                case true:
                    c = (char) ((c << 6) | (b2 & 63));
                    z = true;
                    break;
            }
        }
        return new String(cArr, 0, i4);
    }

    public String readClass(int i, char[] cArr) {
        return readUTF8(this.f1438a[readUnsignedShort(i)], cArr);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r1v30 ??, still in use, count: 2, list:
          (r1v30 ?? I:java.lang.Object) from 0x0062: RETURN (r1v30 ?? I:java.lang.Object)
          (r1v30 ?? I:int) from 0x005f: CONSTRUCTOR (r0v46 ?? I:java.lang.Integer) = (r1v30 ?? I:int) A[MD:(int):void (c)] call: java.lang.Integer.<init>(int):void type: CONSTRUCTOR
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:151)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:116)
        	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:80)
        	at jadx.core.utils.InsnRemover.addAndUnbind(InsnRemover.java:56)
        	at jadx.core.dex.visitors.ModVisitor.removeStep(ModVisitor.java:447)
        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:96)
        */
    /* JADX WARN: Type inference failed for: r1v21, types: [double, java.lang.Double, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v24, types: [long, java.lang.Long, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v27, types: [java.lang.Float, float, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v30, types: [java.lang.Object, int, java.lang.Integer] */
    public java.lang.Object readConst(int r9, char[] r10) {
        /*
            Method dump skipped, instructions count: 271
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.esotericsoftware.asm.ClassReader.readConst(int, char[]):java.lang.Object");
    }
}
