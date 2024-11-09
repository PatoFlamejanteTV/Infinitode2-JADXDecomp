package net.bytebuddy.jar.asm;

import com.badlogic.gdx.net.HttpStatus;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:net/bytebuddy/jar/asm/ClassReader.class */
public class ClassReader {
    public static final int SKIP_CODE = 1;
    public static final int SKIP_DEBUG = 2;
    public static final int SKIP_FRAMES = 4;
    public static final int EXPAND_FRAMES = 8;
    private static final int MAX_BUFFER_SIZE = 1048576;
    private static final int INPUT_STREAM_DATA_CHUNK_SIZE = 4096;

    /* renamed from: b, reason: collision with root package name */
    @Deprecated
    public final byte[] f4135b;
    public final int header;

    /* renamed from: a, reason: collision with root package name */
    final byte[] f4136a;
    private final int[] cpInfoOffsets;
    private final String[] constantUtf8Values;
    private final ConstantDynamic[] constantDynamicValues;
    private final int[] bootstrapMethodOffsets;
    private final int maxStringLength;

    public ClassReader(byte[] bArr) {
        this(bArr, 0, bArr.length);
    }

    public ClassReader(byte[] bArr, int i, int i2) {
        this(bArr, i, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClassReader(byte[] bArr, int i, boolean z) {
        int i2;
        this.f4136a = bArr;
        this.f4135b = bArr;
        if (z && readShort(i + 6) > 64) {
            throw new IllegalArgumentException("Unsupported class file major version " + ((int) readShort(i + 6)));
        }
        int readUnsignedShort = readUnsignedShort(i + 8);
        this.cpInfoOffsets = new int[readUnsignedShort];
        this.constantUtf8Values = new String[readUnsignedShort];
        int i3 = 1;
        int i4 = i + 10;
        int i5 = 0;
        boolean z2 = false;
        boolean z3 = false;
        while (i3 < readUnsignedShort) {
            int i6 = i3;
            i3++;
            this.cpInfoOffsets[i6] = i4 + 1;
            switch (bArr[i4]) {
                case 1:
                    int readUnsignedShort2 = 3 + readUnsignedShort(i4 + 1);
                    i2 = readUnsignedShort2;
                    if (readUnsignedShort2 <= i5) {
                        break;
                    } else {
                        i5 = i2;
                        break;
                    }
                case 2:
                case 13:
                case 14:
                default:
                    throw new IllegalArgumentException();
                case 3:
                case 4:
                case 9:
                case 10:
                case 11:
                case 12:
                    i2 = 5;
                    break;
                case 5:
                case 6:
                    i2 = 9;
                    i3++;
                    break;
                case 7:
                case 8:
                case 16:
                case 19:
                case 20:
                    i2 = 3;
                    break;
                case 15:
                    i2 = 4;
                    break;
                case 17:
                    i2 = 5;
                    z2 = true;
                    z3 = true;
                    break;
                case 18:
                    i2 = 5;
                    z2 = true;
                    break;
            }
            i4 += i2;
        }
        this.maxStringLength = i5;
        this.header = i4;
        this.constantDynamicValues = z3 ? new ConstantDynamic[readUnsignedShort] : null;
        this.bootstrapMethodOffsets = z2 ? readBootstrapMethodsAttribute(i5) : null;
    }

    public ClassReader(InputStream inputStream) {
        this(readStream(inputStream, false));
    }

    public ClassReader(String str) {
        this(readStream(ClassLoader.getSystemResourceAsStream(str.replace('.', '/') + ".class"), true));
    }

    private static byte[] readStream(InputStream inputStream, boolean z) {
        if (inputStream == null) {
            throw new IOException("Class not found");
        }
        int computeBufferSize = computeBufferSize(inputStream);
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                byte[] bArr = new byte[computeBufferSize];
                int i = 0;
                while (true) {
                    int read = inputStream.read(bArr, 0, computeBufferSize);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                    i++;
                }
                byteArrayOutputStream.flush();
                if (i != 1) {
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                    if (z) {
                        inputStream.close();
                    }
                    return byteArray;
                }
                byteArrayOutputStream.close();
                if (z) {
                    inputStream.close();
                }
                return bArr;
            } catch (Throwable th) {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable unused) {
                }
                throw th;
            }
        } catch (Throwable th2) {
            if (z) {
                inputStream.close();
            }
            throw th2;
        }
    }

    private static int computeBufferSize(InputStream inputStream) {
        int available = inputStream.available();
        if (available < 256) {
            return 4096;
        }
        return Math.min(available, 1048576);
    }

    public int getAccess() {
        return readUnsignedShort(this.header);
    }

    public String getClassName() {
        return readClass(this.header + 2, new char[this.maxStringLength]);
    }

    public String getSuperName() {
        return readClass(this.header + 4, new char[this.maxStringLength]);
    }

    public String[] getInterfaces() {
        int i = this.header + 6;
        int readUnsignedShort = readUnsignedShort(i);
        String[] strArr = new String[readUnsignedShort];
        if (readUnsignedShort > 0) {
            char[] cArr = new char[this.maxStringLength];
            for (int i2 = 0; i2 < readUnsignedShort; i2++) {
                i += 2;
                strArr[i2] = readClass(i, cArr);
            }
        }
        return strArr;
    }

    public void accept(ClassVisitor classVisitor, int i) {
        accept(classVisitor, new Attribute[0], i);
    }

    public void accept(ClassVisitor classVisitor, Attribute[] attributeArr, int i) {
        Context context = new Context();
        context.f4137a = attributeArr;
        context.f4138b = i;
        context.c = new char[this.maxStringLength];
        char[] cArr = context.c;
        int i2 = this.header;
        int readUnsignedShort = readUnsignedShort(i2);
        String readClass = readClass(i2 + 2, cArr);
        String readClass2 = readClass(i2 + 4, cArr);
        String[] strArr = new String[readUnsignedShort(i2 + 6)];
        int i3 = i2 + 8;
        for (int i4 = 0; i4 < strArr.length; i4++) {
            strArr[i4] = readClass(i3, cArr);
            i3 += 2;
        }
        int i5 = 0;
        int i6 = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        String str4 = null;
        String str5 = null;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        Attribute attribute = null;
        int a2 = a();
        for (int readUnsignedShort2 = readUnsignedShort(a2 - 2); readUnsignedShort2 > 0; readUnsignedShort2--) {
            String readUTF8 = readUTF8(a2, cArr);
            int readInt = readInt(a2 + 2);
            int i16 = a2 + 6;
            if ("SourceFile".equals(readUTF8)) {
                str2 = readUTF8(i16, cArr);
            } else if ("InnerClasses".equals(readUTF8)) {
                i5 = i16;
            } else if ("EnclosingMethod".equals(readUTF8)) {
                i6 = i16;
            } else if ("NestHost".equals(readUTF8)) {
                str5 = readClass(i16, cArr);
            } else if ("NestMembers".equals(readUTF8)) {
                i13 = i16;
            } else if ("PermittedSubclasses".equals(readUTF8)) {
                i14 = i16;
            } else if ("Signature".equals(readUTF8)) {
                str = readUTF8(i16, cArr);
            } else if ("RuntimeVisibleAnnotations".equals(readUTF8)) {
                i7 = i16;
            } else if ("RuntimeVisibleTypeAnnotations".equals(readUTF8)) {
                i9 = i16;
            } else if ("Deprecated".equals(readUTF8)) {
                readUnsignedShort |= 131072;
            } else if ("Synthetic".equals(readUTF8)) {
                readUnsignedShort |= 4096;
            } else if ("SourceDebugExtension".equals(readUTF8)) {
                if (readInt > this.f4136a.length - i16) {
                    throw new IllegalArgumentException();
                }
                str3 = readUtf(i16, readInt, new char[readInt]);
            } else if ("RuntimeInvisibleAnnotations".equals(readUTF8)) {
                i8 = i16;
            } else if ("RuntimeInvisibleTypeAnnotations".equals(readUTF8)) {
                i10 = i16;
            } else if ("Record".equals(readUTF8)) {
                i15 = i16;
                readUnsignedShort |= 65536;
            } else if ("Module".equals(readUTF8)) {
                i11 = i16;
            } else if ("ModuleMainClass".equals(readUTF8)) {
                str4 = readClass(i16, cArr);
            } else if ("ModulePackages".equals(readUTF8)) {
                i12 = i16;
            } else if (!"BootstrapMethods".equals(readUTF8)) {
                Attribute readAttribute = readAttribute(attributeArr, readUTF8, i16, readInt, cArr, -1, null);
                readAttribute.f4132a = attribute;
                attribute = readAttribute;
            }
            a2 = i16 + readInt;
        }
        classVisitor.visit(readInt(this.cpInfoOffsets[1] - 7), readUnsignedShort, readClass, str, readClass2, strArr);
        if ((i & 2) == 0 && (str2 != null || str3 != null)) {
            classVisitor.visitSource(str2, str3);
        }
        if (i11 != 0) {
            readModuleAttributes(classVisitor, context, i11, i12, str4);
        }
        if (str5 != null) {
            classVisitor.visitNestHost(str5);
        }
        if (i6 != 0) {
            String readClass3 = readClass(i6, cArr);
            int readUnsignedShort3 = readUnsignedShort(i6 + 2);
            classVisitor.visitOuterClass(readClass3, readUnsignedShort3 == 0 ? null : readUTF8(this.cpInfoOffsets[readUnsignedShort3], cArr), readUnsignedShort3 == 0 ? null : readUTF8(this.cpInfoOffsets[readUnsignedShort3] + 2, cArr));
        }
        if (i7 != 0) {
            int readUnsignedShort4 = readUnsignedShort(i7);
            int i17 = i7 + 2;
            while (true) {
                int i18 = i17;
                int i19 = readUnsignedShort4;
                readUnsignedShort4--;
                if (i19 <= 0) {
                    break;
                } else {
                    i17 = readElementValues(classVisitor.visitAnnotation(readUTF8(i18, cArr), true), i18 + 2, true, cArr);
                }
            }
        }
        if (i8 != 0) {
            int readUnsignedShort5 = readUnsignedShort(i8);
            int i20 = i8 + 2;
            while (true) {
                int i21 = i20;
                int i22 = readUnsignedShort5;
                readUnsignedShort5--;
                if (i22 <= 0) {
                    break;
                } else {
                    i20 = readElementValues(classVisitor.visitAnnotation(readUTF8(i21, cArr), false), i21 + 2, true, cArr);
                }
            }
        }
        if (i9 != 0) {
            int readUnsignedShort6 = readUnsignedShort(i9);
            int i23 = i9 + 2;
            while (true) {
                int i24 = i23;
                int i25 = readUnsignedShort6;
                readUnsignedShort6--;
                if (i25 <= 0) {
                    break;
                }
                int readTypeAnnotationTarget = readTypeAnnotationTarget(context, i24);
                i23 = readElementValues(classVisitor.visitTypeAnnotation(context.h, context.i, readUTF8(readTypeAnnotationTarget, cArr), true), readTypeAnnotationTarget + 2, true, cArr);
            }
        }
        if (i10 != 0) {
            int readUnsignedShort7 = readUnsignedShort(i10);
            int i26 = i10 + 2;
            while (true) {
                int i27 = i26;
                int i28 = readUnsignedShort7;
                readUnsignedShort7--;
                if (i28 <= 0) {
                    break;
                }
                int readTypeAnnotationTarget2 = readTypeAnnotationTarget(context, i27);
                i26 = readElementValues(classVisitor.visitTypeAnnotation(context.h, context.i, readUTF8(readTypeAnnotationTarget2, cArr), false), readTypeAnnotationTarget2 + 2, true, cArr);
            }
        }
        while (attribute != null) {
            Attribute attribute2 = attribute.f4132a;
            attribute.f4132a = null;
            classVisitor.visitAttribute(attribute);
            attribute = attribute2;
        }
        if (i13 != 0) {
            int readUnsignedShort8 = readUnsignedShort(i13);
            int i29 = i13 + 2;
            while (true) {
                int i30 = readUnsignedShort8;
                readUnsignedShort8--;
                if (i30 <= 0) {
                    break;
                }
                classVisitor.visitNestMember(readClass(i29, cArr));
                i29 += 2;
            }
        }
        if (i14 != 0) {
            int readUnsignedShort9 = readUnsignedShort(i14);
            int i31 = i14 + 2;
            while (true) {
                int i32 = readUnsignedShort9;
                readUnsignedShort9--;
                if (i32 <= 0) {
                    break;
                }
                classVisitor.visitPermittedSubclass(readClass(i31, cArr));
                i31 += 2;
            }
        }
        if (i5 != 0) {
            int readUnsignedShort10 = readUnsignedShort(i5);
            int i33 = i5 + 2;
            while (true) {
                int i34 = readUnsignedShort10;
                readUnsignedShort10--;
                if (i34 <= 0) {
                    break;
                }
                classVisitor.visitInnerClass(readClass(i33, cArr), readClass(i33 + 2, cArr), readUTF8(i33 + 4, cArr), readUnsignedShort(i33 + 6));
                i33 += 8;
            }
        }
        if (i15 != 0) {
            int readUnsignedShort11 = readUnsignedShort(i15);
            int i35 = i15 + 2;
            while (true) {
                int i36 = readUnsignedShort11;
                readUnsignedShort11--;
                if (i36 <= 0) {
                    break;
                } else {
                    i35 = readRecordComponent(classVisitor, context, i35);
                }
            }
        }
        int readUnsignedShort12 = readUnsignedShort(i3);
        int i37 = i3 + 2;
        while (true) {
            int i38 = readUnsignedShort12;
            readUnsignedShort12--;
            if (i38 <= 0) {
                break;
            } else {
                i37 = readField(classVisitor, context, i37);
            }
        }
        int readUnsignedShort13 = readUnsignedShort(i37);
        int i39 = i37 + 2;
        while (true) {
            int i40 = readUnsignedShort13;
            readUnsignedShort13--;
            if (i40 > 0) {
                i39 = readMethod(classVisitor, context, i39);
            } else {
                classVisitor.visitEnd();
                return;
            }
        }
    }

    private void readModuleAttributes(ClassVisitor classVisitor, Context context, int i, int i2, String str) {
        char[] cArr = context.c;
        int i3 = i + 6;
        ModuleVisitor visitModule = classVisitor.visitModule(readModule(i, cArr), readUnsignedShort(i + 2), readUTF8(i + 4, cArr));
        if (visitModule == null) {
            return;
        }
        if (str != null) {
            visitModule.visitMainClass(str);
        }
        if (i2 != 0) {
            int readUnsignedShort = readUnsignedShort(i2);
            int i4 = i2 + 2;
            while (true) {
                int i5 = readUnsignedShort;
                readUnsignedShort--;
                if (i5 <= 0) {
                    break;
                }
                visitModule.visitPackage(readPackage(i4, cArr));
                i4 += 2;
            }
        }
        int readUnsignedShort2 = readUnsignedShort(i3);
        int i6 = i3 + 2;
        while (true) {
            int i7 = readUnsignedShort2;
            readUnsignedShort2--;
            if (i7 <= 0) {
                break;
            }
            String readModule = readModule(i6, cArr);
            int readUnsignedShort3 = readUnsignedShort(i6 + 2);
            String readUTF8 = readUTF8(i6 + 4, cArr);
            i6 += 6;
            visitModule.visitRequire(readModule, readUnsignedShort3, readUTF8);
        }
        int readUnsignedShort4 = readUnsignedShort(i6);
        int i8 = i6 + 2;
        while (true) {
            int i9 = readUnsignedShort4;
            readUnsignedShort4--;
            if (i9 <= 0) {
                break;
            }
            String readPackage = readPackage(i8, cArr);
            int readUnsignedShort5 = readUnsignedShort(i8 + 2);
            int readUnsignedShort6 = readUnsignedShort(i8 + 4);
            i8 += 6;
            String[] strArr = null;
            if (readUnsignedShort6 != 0) {
                strArr = new String[readUnsignedShort6];
                for (int i10 = 0; i10 < readUnsignedShort6; i10++) {
                    strArr[i10] = readModule(i8, cArr);
                    i8 += 2;
                }
            }
            visitModule.visitExport(readPackage, readUnsignedShort5, strArr);
        }
        int readUnsignedShort7 = readUnsignedShort(i8);
        int i11 = i8 + 2;
        while (true) {
            int i12 = readUnsignedShort7;
            readUnsignedShort7--;
            if (i12 <= 0) {
                break;
            }
            String readPackage2 = readPackage(i11, cArr);
            int readUnsignedShort8 = readUnsignedShort(i11 + 2);
            int readUnsignedShort9 = readUnsignedShort(i11 + 4);
            i11 += 6;
            String[] strArr2 = null;
            if (readUnsignedShort9 != 0) {
                strArr2 = new String[readUnsignedShort9];
                for (int i13 = 0; i13 < readUnsignedShort9; i13++) {
                    strArr2[i13] = readModule(i11, cArr);
                    i11 += 2;
                }
            }
            visitModule.visitOpen(readPackage2, readUnsignedShort8, strArr2);
        }
        int readUnsignedShort10 = readUnsignedShort(i11);
        while (true) {
            i11 += 2;
            int i14 = readUnsignedShort10;
            readUnsignedShort10--;
            if (i14 <= 0) {
                break;
            } else {
                visitModule.visitUse(readClass(i11, cArr));
            }
        }
        int readUnsignedShort11 = readUnsignedShort(i11);
        int i15 = i11 + 2;
        while (true) {
            int i16 = readUnsignedShort11;
            readUnsignedShort11--;
            if (i16 > 0) {
                String readClass = readClass(i15, cArr);
                int readUnsignedShort12 = readUnsignedShort(i15 + 2);
                i15 += 4;
                String[] strArr3 = new String[readUnsignedShort12];
                for (int i17 = 0; i17 < readUnsignedShort12; i17++) {
                    strArr3[i17] = readClass(i15, cArr);
                    i15 += 2;
                }
                visitModule.visitProvide(readClass, strArr3);
            } else {
                visitModule.visitEnd();
                return;
            }
        }
    }

    private int readRecordComponent(ClassVisitor classVisitor, Context context, int i) {
        char[] cArr = context.c;
        String readUTF8 = readUTF8(i, cArr);
        String readUTF82 = readUTF8(i + 2, cArr);
        int i2 = i + 4;
        String str = null;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        Attribute attribute = null;
        int readUnsignedShort = readUnsignedShort(i2);
        int i7 = i2 + 2;
        while (true) {
            int i8 = readUnsignedShort;
            readUnsignedShort--;
            if (i8 <= 0) {
                break;
            }
            String readUTF83 = readUTF8(i7, cArr);
            int readInt = readInt(i7 + 2);
            int i9 = i7 + 6;
            if ("Signature".equals(readUTF83)) {
                str = readUTF8(i9, cArr);
            } else if ("RuntimeVisibleAnnotations".equals(readUTF83)) {
                i3 = i9;
            } else if ("RuntimeVisibleTypeAnnotations".equals(readUTF83)) {
                i5 = i9;
            } else if ("RuntimeInvisibleAnnotations".equals(readUTF83)) {
                i4 = i9;
            } else if ("RuntimeInvisibleTypeAnnotations".equals(readUTF83)) {
                i6 = i9;
            } else {
                Attribute readAttribute = readAttribute(context.f4137a, readUTF83, i9, readInt, cArr, -1, null);
                readAttribute.f4132a = attribute;
                attribute = readAttribute;
            }
            i7 = i9 + readInt;
        }
        RecordComponentVisitor visitRecordComponent = classVisitor.visitRecordComponent(readUTF8, readUTF82, str);
        if (visitRecordComponent == null) {
            return i7;
        }
        if (i3 != 0) {
            int readUnsignedShort2 = readUnsignedShort(i3);
            int i10 = i3 + 2;
            while (true) {
                int i11 = i10;
                int i12 = readUnsignedShort2;
                readUnsignedShort2--;
                if (i12 <= 0) {
                    break;
                }
                i10 = readElementValues(visitRecordComponent.visitAnnotation(readUTF8(i11, cArr), true), i11 + 2, true, cArr);
            }
        }
        if (i4 != 0) {
            int readUnsignedShort3 = readUnsignedShort(i4);
            int i13 = i4 + 2;
            while (true) {
                int i14 = i13;
                int i15 = readUnsignedShort3;
                readUnsignedShort3--;
                if (i15 <= 0) {
                    break;
                }
                i13 = readElementValues(visitRecordComponent.visitAnnotation(readUTF8(i14, cArr), false), i14 + 2, true, cArr);
            }
        }
        if (i5 != 0) {
            int readUnsignedShort4 = readUnsignedShort(i5);
            int i16 = i5 + 2;
            while (true) {
                int i17 = i16;
                int i18 = readUnsignedShort4;
                readUnsignedShort4--;
                if (i18 <= 0) {
                    break;
                }
                int readTypeAnnotationTarget = readTypeAnnotationTarget(context, i17);
                i16 = readElementValues(visitRecordComponent.visitTypeAnnotation(context.h, context.i, readUTF8(readTypeAnnotationTarget, cArr), true), readTypeAnnotationTarget + 2, true, cArr);
            }
        }
        if (i6 != 0) {
            int readUnsignedShort5 = readUnsignedShort(i6);
            int i19 = i6 + 2;
            while (true) {
                int i20 = i19;
                int i21 = readUnsignedShort5;
                readUnsignedShort5--;
                if (i21 <= 0) {
                    break;
                }
                int readTypeAnnotationTarget2 = readTypeAnnotationTarget(context, i20);
                i19 = readElementValues(visitRecordComponent.visitTypeAnnotation(context.h, context.i, readUTF8(readTypeAnnotationTarget2, cArr), false), readTypeAnnotationTarget2 + 2, true, cArr);
            }
        }
        while (attribute != null) {
            Attribute attribute2 = attribute.f4132a;
            attribute.f4132a = null;
            visitRecordComponent.visitAttribute(attribute);
            attribute = attribute2;
        }
        visitRecordComponent.visitEnd();
        return i7;
    }

    private int readField(ClassVisitor classVisitor, Context context, int i) {
        char[] cArr = context.c;
        int readUnsignedShort = readUnsignedShort(i);
        String readUTF8 = readUTF8(i + 2, cArr);
        String readUTF82 = readUTF8(i + 4, cArr);
        int i2 = i + 6;
        Object obj = null;
        String str = null;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        Attribute attribute = null;
        int readUnsignedShort2 = readUnsignedShort(i2);
        int i7 = i2 + 2;
        while (true) {
            int i8 = readUnsignedShort2;
            readUnsignedShort2--;
            if (i8 <= 0) {
                break;
            }
            String readUTF83 = readUTF8(i7, cArr);
            int readInt = readInt(i7 + 2);
            int i9 = i7 + 6;
            if ("ConstantValue".equals(readUTF83)) {
                int readUnsignedShort3 = readUnsignedShort(i9);
                obj = readUnsignedShort3 == 0 ? null : readConst(readUnsignedShort3, cArr);
            } else if ("Signature".equals(readUTF83)) {
                str = readUTF8(i9, cArr);
            } else if ("Deprecated".equals(readUTF83)) {
                readUnsignedShort |= 131072;
            } else if ("Synthetic".equals(readUTF83)) {
                readUnsignedShort |= 4096;
            } else if ("RuntimeVisibleAnnotations".equals(readUTF83)) {
                i3 = i9;
            } else if ("RuntimeVisibleTypeAnnotations".equals(readUTF83)) {
                i5 = i9;
            } else if ("RuntimeInvisibleAnnotations".equals(readUTF83)) {
                i4 = i9;
            } else if ("RuntimeInvisibleTypeAnnotations".equals(readUTF83)) {
                i6 = i9;
            } else {
                Attribute readAttribute = readAttribute(context.f4137a, readUTF83, i9, readInt, cArr, -1, null);
                readAttribute.f4132a = attribute;
                attribute = readAttribute;
            }
            i7 = i9 + readInt;
        }
        FieldVisitor visitField = classVisitor.visitField(readUnsignedShort, readUTF8, readUTF82, str, obj);
        if (visitField == null) {
            return i7;
        }
        if (i3 != 0) {
            int readUnsignedShort4 = readUnsignedShort(i3);
            int i10 = i3 + 2;
            while (true) {
                int i11 = i10;
                int i12 = readUnsignedShort4;
                readUnsignedShort4--;
                if (i12 <= 0) {
                    break;
                }
                i10 = readElementValues(visitField.visitAnnotation(readUTF8(i11, cArr), true), i11 + 2, true, cArr);
            }
        }
        if (i4 != 0) {
            int readUnsignedShort5 = readUnsignedShort(i4);
            int i13 = i4 + 2;
            while (true) {
                int i14 = i13;
                int i15 = readUnsignedShort5;
                readUnsignedShort5--;
                if (i15 <= 0) {
                    break;
                }
                i13 = readElementValues(visitField.visitAnnotation(readUTF8(i14, cArr), false), i14 + 2, true, cArr);
            }
        }
        if (i5 != 0) {
            int readUnsignedShort6 = readUnsignedShort(i5);
            int i16 = i5 + 2;
            while (true) {
                int i17 = i16;
                int i18 = readUnsignedShort6;
                readUnsignedShort6--;
                if (i18 <= 0) {
                    break;
                }
                int readTypeAnnotationTarget = readTypeAnnotationTarget(context, i17);
                i16 = readElementValues(visitField.visitTypeAnnotation(context.h, context.i, readUTF8(readTypeAnnotationTarget, cArr), true), readTypeAnnotationTarget + 2, true, cArr);
            }
        }
        if (i6 != 0) {
            int readUnsignedShort7 = readUnsignedShort(i6);
            int i19 = i6 + 2;
            while (true) {
                int i20 = i19;
                int i21 = readUnsignedShort7;
                readUnsignedShort7--;
                if (i21 <= 0) {
                    break;
                }
                int readTypeAnnotationTarget2 = readTypeAnnotationTarget(context, i20);
                i19 = readElementValues(visitField.visitTypeAnnotation(context.h, context.i, readUTF8(readTypeAnnotationTarget2, cArr), false), readTypeAnnotationTarget2 + 2, true, cArr);
            }
        }
        while (attribute != null) {
            Attribute attribute2 = attribute.f4132a;
            attribute.f4132a = null;
            visitField.visitAttribute(attribute);
            attribute = attribute2;
        }
        visitField.visitEnd();
        return i7;
    }

    private int readMethod(ClassVisitor classVisitor, Context context, int i) {
        char[] cArr = context.c;
        context.d = readUnsignedShort(i);
        context.e = readUTF8(i + 2, cArr);
        context.f = readUTF8(i + 4, cArr);
        int i2 = i + 6;
        int i3 = 0;
        int i4 = 0;
        String[] strArr = null;
        boolean z = false;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        Attribute attribute = null;
        int readUnsignedShort = readUnsignedShort(i2);
        int i14 = i2 + 2;
        while (true) {
            int i15 = readUnsignedShort;
            readUnsignedShort--;
            if (i15 <= 0) {
                break;
            }
            String readUTF8 = readUTF8(i14, cArr);
            int readInt = readInt(i14 + 2);
            int i16 = i14 + 6;
            if ("Code".equals(readUTF8)) {
                if ((context.f4138b & 1) == 0) {
                    i3 = i16;
                }
            } else if ("Exceptions".equals(readUTF8)) {
                i4 = i16;
                strArr = new String[readUnsignedShort(i4)];
                int i17 = i4 + 2;
                for (int i18 = 0; i18 < strArr.length; i18++) {
                    strArr[i18] = readClass(i17, cArr);
                    i17 += 2;
                }
            } else if ("Signature".equals(readUTF8)) {
                i5 = readUnsignedShort(i16);
            } else if ("Deprecated".equals(readUTF8)) {
                context.d |= 131072;
            } else if ("RuntimeVisibleAnnotations".equals(readUTF8)) {
                i6 = i16;
            } else if ("RuntimeVisibleTypeAnnotations".equals(readUTF8)) {
                i10 = i16;
            } else if ("AnnotationDefault".equals(readUTF8)) {
                i12 = i16;
            } else if ("Synthetic".equals(readUTF8)) {
                z = true;
                context.d |= 4096;
            } else if ("RuntimeInvisibleAnnotations".equals(readUTF8)) {
                i7 = i16;
            } else if ("RuntimeInvisibleTypeAnnotations".equals(readUTF8)) {
                i11 = i16;
            } else if ("RuntimeVisibleParameterAnnotations".equals(readUTF8)) {
                i8 = i16;
            } else if ("RuntimeInvisibleParameterAnnotations".equals(readUTF8)) {
                i9 = i16;
            } else if ("MethodParameters".equals(readUTF8)) {
                i13 = i16;
            } else {
                Attribute readAttribute = readAttribute(context.f4137a, readUTF8, i16, readInt, cArr, -1, null);
                readAttribute.f4132a = attribute;
                attribute = readAttribute;
            }
            i14 = i16 + readInt;
        }
        MethodVisitor visitMethod = classVisitor.visitMethod(context.d, context.e, context.f, i5 == 0 ? null : a(i5, cArr), strArr);
        if (visitMethod == null) {
            return i14;
        }
        if (visitMethod instanceof MethodWriter) {
            MethodWriter methodWriter = (MethodWriter) visitMethod;
            if (methodWriter.a(this, z, (context.d & 131072) != 0, readUnsignedShort(i + 4), i5, i4)) {
                methodWriter.b(i, i14 - i);
                return i14;
            }
        }
        if (i13 != 0 && (context.f4138b & 2) == 0) {
            int readByte = readByte(i13);
            int i19 = i13 + 1;
            while (true) {
                int i20 = readByte;
                readByte--;
                if (i20 <= 0) {
                    break;
                }
                visitMethod.visitParameter(readUTF8(i19, cArr), readUnsignedShort(i19 + 2));
                i19 += 4;
            }
        }
        if (i12 != 0) {
            AnnotationVisitor visitAnnotationDefault = visitMethod.visitAnnotationDefault();
            readElementValue(visitAnnotationDefault, i12, null, cArr);
            if (visitAnnotationDefault != null) {
                visitAnnotationDefault.visitEnd();
            }
        }
        if (i6 != 0) {
            int readUnsignedShort2 = readUnsignedShort(i6);
            int i21 = i6 + 2;
            while (true) {
                int i22 = i21;
                int i23 = readUnsignedShort2;
                readUnsignedShort2--;
                if (i23 <= 0) {
                    break;
                }
                i21 = readElementValues(visitMethod.visitAnnotation(readUTF8(i22, cArr), true), i22 + 2, true, cArr);
            }
        }
        if (i7 != 0) {
            int readUnsignedShort3 = readUnsignedShort(i7);
            int i24 = i7 + 2;
            while (true) {
                int i25 = i24;
                int i26 = readUnsignedShort3;
                readUnsignedShort3--;
                if (i26 <= 0) {
                    break;
                }
                i24 = readElementValues(visitMethod.visitAnnotation(readUTF8(i25, cArr), false), i25 + 2, true, cArr);
            }
        }
        if (i10 != 0) {
            int readUnsignedShort4 = readUnsignedShort(i10);
            int i27 = i10 + 2;
            while (true) {
                int i28 = i27;
                int i29 = readUnsignedShort4;
                readUnsignedShort4--;
                if (i29 <= 0) {
                    break;
                }
                int readTypeAnnotationTarget = readTypeAnnotationTarget(context, i28);
                i27 = readElementValues(visitMethod.visitTypeAnnotation(context.h, context.i, readUTF8(readTypeAnnotationTarget, cArr), true), readTypeAnnotationTarget + 2, true, cArr);
            }
        }
        if (i11 != 0) {
            int readUnsignedShort5 = readUnsignedShort(i11);
            int i30 = i11 + 2;
            while (true) {
                int i31 = i30;
                int i32 = readUnsignedShort5;
                readUnsignedShort5--;
                if (i32 <= 0) {
                    break;
                }
                int readTypeAnnotationTarget2 = readTypeAnnotationTarget(context, i31);
                i30 = readElementValues(visitMethod.visitTypeAnnotation(context.h, context.i, readUTF8(readTypeAnnotationTarget2, cArr), false), readTypeAnnotationTarget2 + 2, true, cArr);
            }
        }
        if (i8 != 0) {
            readParameterAnnotations(visitMethod, context, i8, true);
        }
        if (i9 != 0) {
            readParameterAnnotations(visitMethod, context, i9, false);
        }
        while (attribute != null) {
            Attribute attribute2 = attribute.f4132a;
            attribute.f4132a = null;
            visitMethod.visitAttribute(attribute);
            attribute = attribute2;
        }
        if (i3 != 0) {
            visitMethod.visitCode();
            readCode(visitMethod, context, i3);
        }
        visitMethod.visitEnd();
        return i14;
    }

    private void readCode(MethodVisitor methodVisitor, Context context, int i) {
        int i2;
        int readUnsignedShort;
        byte[] bArr = this.f4136a;
        char[] cArr = context.c;
        int readUnsignedShort2 = readUnsignedShort(i);
        int readUnsignedShort3 = readUnsignedShort(i + 2);
        int readInt = readInt(i + 4);
        int i3 = i + 8;
        if (readInt > this.f4136a.length - i3) {
            throw new IllegalArgumentException();
        }
        int i4 = i3 + readInt;
        Label[] labelArr = new Label[readInt + 1];
        context.g = labelArr;
        while (i3 < i4) {
            int i5 = i3 - i3;
            switch (bArr[i3] & 255) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                case 50:
                case 51:
                case 52:
                case 53:
                case 59:
                case 60:
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                case 68:
                case 69:
                case 70:
                case 71:
                case 72:
                case 73:
                case 74:
                case 75:
                case 76:
                case 77:
                case 78:
                case 79:
                case 80:
                case 81:
                case 82:
                case 83:
                case 84:
                case 85:
                case 86:
                case 87:
                case 88:
                case 89:
                case 90:
                case 91:
                case 92:
                case 93:
                case 94:
                case 95:
                case 96:
                case 97:
                case 98:
                case 99:
                case 100:
                case 101:
                case 102:
                case 103:
                case 104:
                case 105:
                case 106:
                case 107:
                case 108:
                case 109:
                case 110:
                case 111:
                case 112:
                case 113:
                case 114:
                case 115:
                case 116:
                case 117:
                case 118:
                case 119:
                case 120:
                case 121:
                case 122:
                case 123:
                case 124:
                case 125:
                case 126:
                case 127:
                case 128:
                case 129:
                case 130:
                case 131:
                case 133:
                case 134:
                case 135:
                case 136:
                case 137:
                case 138:
                case 139:
                case 140:
                case 141:
                case 142:
                case 143:
                case 144:
                case 145:
                case 146:
                case 147:
                case 148:
                case 149:
                case 150:
                case 151:
                case 152:
                case 172:
                case 173:
                case 174:
                case 175:
                case 176:
                case 177:
                case 190:
                case 191:
                case 194:
                case 195:
                    i3++;
                    break;
                case 16:
                case 18:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 169:
                case 188:
                    i3 += 2;
                    break;
                case 17:
                case 19:
                case 20:
                case 132:
                case 178:
                case 179:
                case 180:
                case 181:
                case 182:
                case 183:
                case 184:
                case 187:
                case 189:
                case 192:
                case 193:
                    i3 += 3;
                    break;
                case 153:
                case 154:
                case 155:
                case 156:
                case 157:
                case 158:
                case 159:
                case 160:
                case 161:
                case 162:
                case 163:
                case 164:
                case 165:
                case 166:
                case 167:
                case 168:
                case 198:
                case 199:
                    createLabel(i5 + readShort(i3 + 1), labelArr);
                    i3 += 3;
                    break;
                case 170:
                    int i6 = i3 + (4 - (i5 & 3));
                    createLabel(i5 + readInt(i6), labelArr);
                    int readInt2 = (readInt(i6 + 8) - readInt(i6 + 4)) + 1;
                    i3 = i6 + 12;
                    while (true) {
                        int i7 = readInt2;
                        readInt2--;
                        if (i7 > 0) {
                            createLabel(i5 + readInt(i3), labelArr);
                            i3 += 4;
                        }
                    }
                    break;
                case 171:
                    i3 += 4 - (i5 & 3);
                    createLabel(i5 + readInt(i3), labelArr);
                    int readInt3 = readInt(i3 + 4);
                    while (true) {
                        i3 += 8;
                        int i8 = readInt3;
                        readInt3--;
                        if (i8 > 0) {
                            createLabel(i5 + readInt(i3 + 4), labelArr);
                        }
                    }
                    break;
                case 185:
                case 186:
                    i3 += 5;
                    break;
                case 196:
                    switch (bArr[i3 + 1] & 255) {
                        case 21:
                        case 22:
                        case 23:
                        case 24:
                        case 25:
                        case 54:
                        case 55:
                        case 56:
                        case 57:
                        case 58:
                        case 169:
                            i3 += 4;
                            break;
                        case 132:
                            i3 += 6;
                            break;
                        default:
                            throw new IllegalArgumentException();
                    }
                case 197:
                    i3 += 4;
                    break;
                case 200:
                case 201:
                case User32.VK_OEM_5 /* 220 */:
                    createLabel(i5 + readInt(i3 + 1), labelArr);
                    i3 += 5;
                    break;
                case HttpStatus.SC_ACCEPTED /* 202 */:
                case 203:
                case HttpStatus.SC_NO_CONTENT /* 204 */:
                case HttpStatus.SC_RESET_CONTENT /* 205 */:
                case HttpStatus.SC_PARTIAL_CONTENT /* 206 */:
                case HttpStatus.SC_MULTI_STATUS /* 207 */:
                case 208:
                case 209:
                case 210:
                case 211:
                case 212:
                case 213:
                case 214:
                case 215:
                case 216:
                case 217:
                case 218:
                case User32.VK_OEM_4 /* 219 */:
                    createLabel(i5 + readUnsignedShort(i3 + 1), labelArr);
                    i3 += 3;
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }
        int readUnsignedShort4 = readUnsignedShort(i3);
        int i9 = i3 + 2;
        while (true) {
            int i10 = readUnsignedShort4;
            readUnsignedShort4--;
            if (i10 > 0) {
                Label createLabel = createLabel(readUnsignedShort(i9), labelArr);
                Label createLabel2 = createLabel(readUnsignedShort(i9 + 2), labelArr);
                Label createLabel3 = createLabel(readUnsignedShort(i9 + 4), labelArr);
                String readUTF8 = readUTF8(this.cpInfoOffsets[readUnsignedShort(i9 + 6)], cArr);
                i9 += 8;
                methodVisitor.visitTryCatchBlock(createLabel, createLabel2, createLabel3, readUTF8);
            } else {
                int i11 = 0;
                int i12 = 0;
                boolean z = true;
                int i13 = 0;
                int i14 = 0;
                int[] iArr = null;
                int[] iArr2 = null;
                Attribute attribute = null;
                int readUnsignedShort5 = readUnsignedShort(i9);
                int i15 = i9 + 2;
                while (true) {
                    int i16 = readUnsignedShort5;
                    readUnsignedShort5--;
                    if (i16 > 0) {
                        String readUTF82 = readUTF8(i15, cArr);
                        int readInt4 = readInt(i15 + 2);
                        int i17 = i15 + 6;
                        if ("LocalVariableTable".equals(readUTF82)) {
                            if ((context.f4138b & 2) == 0) {
                                i13 = i17;
                                int readUnsignedShort6 = readUnsignedShort(i17);
                                int i18 = i17 + 2;
                                while (true) {
                                    int i19 = readUnsignedShort6;
                                    readUnsignedShort6--;
                                    if (i19 > 0) {
                                        int readUnsignedShort7 = readUnsignedShort(i18);
                                        createDebugLabel(readUnsignedShort7, labelArr);
                                        createDebugLabel(readUnsignedShort7 + readUnsignedShort(i18 + 2), labelArr);
                                        i18 += 10;
                                    }
                                }
                            }
                        } else if ("LocalVariableTypeTable".equals(readUTF82)) {
                            i14 = i17;
                        } else if ("LineNumberTable".equals(readUTF82)) {
                            if ((context.f4138b & 2) == 0) {
                                int readUnsignedShort8 = readUnsignedShort(i17);
                                int i20 = i17 + 2;
                                while (true) {
                                    int i21 = readUnsignedShort8;
                                    readUnsignedShort8--;
                                    if (i21 > 0) {
                                        int readUnsignedShort9 = readUnsignedShort(i20);
                                        int readUnsignedShort10 = readUnsignedShort(i20 + 2);
                                        i20 += 4;
                                        createDebugLabel(readUnsignedShort9, labelArr);
                                        labelArr[readUnsignedShort9].a(readUnsignedShort10);
                                    }
                                }
                            }
                        } else if ("RuntimeVisibleTypeAnnotations".equals(readUTF82)) {
                            iArr = readTypeAnnotations(methodVisitor, context, i17, true);
                        } else if ("RuntimeInvisibleTypeAnnotations".equals(readUTF82)) {
                            iArr2 = readTypeAnnotations(methodVisitor, context, i17, false);
                        } else if ("StackMapTable".equals(readUTF82)) {
                            if ((context.f4138b & 4) == 0) {
                                i11 = i17 + 2;
                                i12 = i17 + readInt4;
                            }
                        } else if ("StackMap".equals(readUTF82)) {
                            if ((context.f4138b & 4) == 0) {
                                i11 = i17 + 2;
                                i12 = i17 + readInt4;
                                z = false;
                            }
                        } else {
                            Attribute readAttribute = readAttribute(context.f4137a, readUTF82, i17, readInt4, cArr, i, labelArr);
                            readAttribute.f4132a = attribute;
                            attribute = readAttribute;
                        }
                        i15 = i17 + readInt4;
                    } else {
                        boolean z2 = (context.f4138b & 8) != 0;
                        if (i11 != 0) {
                            context.m = -1;
                            context.n = 0;
                            context.o = 0;
                            context.p = 0;
                            context.q = new Object[readUnsignedShort3];
                            context.r = 0;
                            context.s = new Object[readUnsignedShort2];
                            if (z2) {
                                computeImplicitFrame(context);
                            }
                            for (int i22 = i11; i22 < i12 - 2; i22++) {
                                if (bArr[i22] == 8 && (readUnsignedShort = readUnsignedShort(i22 + 1)) >= 0 && readUnsignedShort < readInt && (bArr[i3 + readUnsignedShort] & 255) == 187) {
                                    createLabel(readUnsignedShort, labelArr);
                                }
                            }
                        }
                        if (z2 && (context.f4138b & 256) != 0) {
                            methodVisitor.visitFrame(-1, readUnsignedShort3, null, 0, null);
                        }
                        int i23 = 0;
                        int typeAnnotationBytecodeOffset = getTypeAnnotationBytecodeOffset(iArr, 0);
                        int i24 = 0;
                        int typeAnnotationBytecodeOffset2 = getTypeAnnotationBytecodeOffset(iArr2, 0);
                        boolean z3 = false;
                        int i25 = (context.f4138b & 256) == 0 ? 33 : 0;
                        int i26 = i3;
                        while (i26 < i4) {
                            int i27 = i26 - i3;
                            Label label = labelArr[i27];
                            if (label != null) {
                                label.a(methodVisitor, (context.f4138b & 2) == 0);
                            }
                            while (i11 != 0 && (context.m == i27 || context.m == -1)) {
                                if (context.m != -1) {
                                    if (!z || z2) {
                                        methodVisitor.visitFrame(-1, context.o, context.q, context.r, context.s);
                                    } else {
                                        methodVisitor.visitFrame(context.n, context.p, context.q, context.r, context.s);
                                    }
                                    z3 = false;
                                }
                                if (i11 < i12) {
                                    i11 = readStackMapFrame(i11, z, z2, context);
                                } else {
                                    i11 = 0;
                                }
                            }
                            if (z3) {
                                if ((context.f4138b & 8) != 0) {
                                    methodVisitor.visitFrame(256, 0, null, 0, null);
                                }
                                z3 = false;
                            }
                            int i28 = bArr[i26] & 255;
                            switch (i28) {
                                case 0:
                                case 1:
                                case 2:
                                case 3:
                                case 4:
                                case 5:
                                case 6:
                                case 7:
                                case 8:
                                case 9:
                                case 10:
                                case 11:
                                case 12:
                                case 13:
                                case 14:
                                case 15:
                                case 46:
                                case 47:
                                case 48:
                                case 49:
                                case 50:
                                case 51:
                                case 52:
                                case 53:
                                case 79:
                                case 80:
                                case 81:
                                case 82:
                                case 83:
                                case 84:
                                case 85:
                                case 86:
                                case 87:
                                case 88:
                                case 89:
                                case 90:
                                case 91:
                                case 92:
                                case 93:
                                case 94:
                                case 95:
                                case 96:
                                case 97:
                                case 98:
                                case 99:
                                case 100:
                                case 101:
                                case 102:
                                case 103:
                                case 104:
                                case 105:
                                case 106:
                                case 107:
                                case 108:
                                case 109:
                                case 110:
                                case 111:
                                case 112:
                                case 113:
                                case 114:
                                case 115:
                                case 116:
                                case 117:
                                case 118:
                                case 119:
                                case 120:
                                case 121:
                                case 122:
                                case 123:
                                case 124:
                                case 125:
                                case 126:
                                case 127:
                                case 128:
                                case 129:
                                case 130:
                                case 131:
                                case 133:
                                case 134:
                                case 135:
                                case 136:
                                case 137:
                                case 138:
                                case 139:
                                case 140:
                                case 141:
                                case 142:
                                case 143:
                                case 144:
                                case 145:
                                case 146:
                                case 147:
                                case 148:
                                case 149:
                                case 150:
                                case 151:
                                case 152:
                                case 172:
                                case 173:
                                case 174:
                                case 175:
                                case 176:
                                case 177:
                                case 190:
                                case 191:
                                case 194:
                                case 195:
                                    methodVisitor.visitInsn(i28);
                                    i26++;
                                    break;
                                case 16:
                                case 188:
                                    methodVisitor.visitIntInsn(i28, bArr[i26 + 1]);
                                    i26 += 2;
                                    break;
                                case 17:
                                    methodVisitor.visitIntInsn(i28, readShort(i26 + 1));
                                    i26 += 3;
                                    break;
                                case 18:
                                    methodVisitor.visitLdcInsn(readConst(bArr[i26 + 1] & 255, cArr));
                                    i26 += 2;
                                    break;
                                case 19:
                                case 20:
                                    methodVisitor.visitLdcInsn(readConst(readUnsignedShort(i26 + 1), cArr));
                                    i26 += 3;
                                    break;
                                case 21:
                                case 22:
                                case 23:
                                case 24:
                                case 25:
                                case 54:
                                case 55:
                                case 56:
                                case 57:
                                case 58:
                                case 169:
                                    methodVisitor.visitVarInsn(i28, bArr[i26 + 1] & 255);
                                    i26 += 2;
                                    break;
                                case 26:
                                case 27:
                                case 28:
                                case 29:
                                case 30:
                                case 31:
                                case 32:
                                case 33:
                                case 34:
                                case 35:
                                case 36:
                                case 37:
                                case 38:
                                case 39:
                                case 40:
                                case 41:
                                case 42:
                                case 43:
                                case 44:
                                case 45:
                                    int i29 = i28 - 26;
                                    methodVisitor.visitVarInsn(21 + (i29 >> 2), i29 & 3);
                                    i26++;
                                    break;
                                case 59:
                                case 60:
                                case 61:
                                case 62:
                                case 63:
                                case 64:
                                case 65:
                                case 66:
                                case 67:
                                case 68:
                                case 69:
                                case 70:
                                case 71:
                                case 72:
                                case 73:
                                case 74:
                                case 75:
                                case 76:
                                case 77:
                                case 78:
                                    int i30 = i28 - 59;
                                    methodVisitor.visitVarInsn(54 + (i30 >> 2), i30 & 3);
                                    i26++;
                                    break;
                                case 132:
                                    methodVisitor.visitIincInsn(bArr[i26 + 1] & 255, bArr[i26 + 2]);
                                    i26 += 3;
                                    break;
                                case 153:
                                case 154:
                                case 155:
                                case 156:
                                case 157:
                                case 158:
                                case 159:
                                case 160:
                                case 161:
                                case 162:
                                case 163:
                                case 164:
                                case 165:
                                case 166:
                                case 167:
                                case 168:
                                case 198:
                                case 199:
                                    methodVisitor.visitJumpInsn(i28, labelArr[i27 + readShort(i26 + 1)]);
                                    i26 += 3;
                                    break;
                                case 170:
                                    int i31 = i26 + (4 - (i27 & 3));
                                    Label label2 = labelArr[i27 + readInt(i31)];
                                    int readInt5 = readInt(i31 + 4);
                                    int readInt6 = readInt(i31 + 8);
                                    i26 = i31 + 12;
                                    Label[] labelArr2 = new Label[(readInt6 - readInt5) + 1];
                                    for (int i32 = 0; i32 < labelArr2.length; i32++) {
                                        labelArr2[i32] = labelArr[i27 + readInt(i26)];
                                        i26 += 4;
                                    }
                                    methodVisitor.visitTableSwitchInsn(readInt5, readInt6, label2, labelArr2);
                                    break;
                                case 171:
                                    int i33 = i26 + (4 - (i27 & 3));
                                    Label label3 = labelArr[i27 + readInt(i33)];
                                    int readInt7 = readInt(i33 + 4);
                                    i26 = i33 + 8;
                                    int[] iArr3 = new int[readInt7];
                                    Label[] labelArr3 = new Label[readInt7];
                                    for (int i34 = 0; i34 < readInt7; i34++) {
                                        iArr3[i34] = readInt(i26);
                                        labelArr3[i34] = labelArr[i27 + readInt(i26 + 4)];
                                        i26 += 8;
                                    }
                                    methodVisitor.visitLookupSwitchInsn(label3, iArr3, labelArr3);
                                    break;
                                case 178:
                                case 179:
                                case 180:
                                case 181:
                                case 182:
                                case 183:
                                case 184:
                                case 185:
                                    int i35 = this.cpInfoOffsets[readUnsignedShort(i26 + 1)];
                                    int i36 = this.cpInfoOffsets[readUnsignedShort(i35 + 2)];
                                    String readClass = readClass(i35, cArr);
                                    String readUTF83 = readUTF8(i36, cArr);
                                    String readUTF84 = readUTF8(i36 + 2, cArr);
                                    if (i28 < 182) {
                                        methodVisitor.visitFieldInsn(i28, readClass, readUTF83, readUTF84);
                                    } else {
                                        methodVisitor.visitMethodInsn(i28, readClass, readUTF83, readUTF84, bArr[i35 - 1] == 11);
                                    }
                                    if (i28 == 185) {
                                        i26 += 5;
                                        break;
                                    } else {
                                        i26 += 3;
                                        break;
                                    }
                                case 186:
                                    int i37 = this.cpInfoOffsets[readUnsignedShort(i26 + 1)];
                                    int i38 = this.cpInfoOffsets[readUnsignedShort(i37 + 2)];
                                    String readUTF85 = readUTF8(i38, cArr);
                                    String readUTF86 = readUTF8(i38 + 2, cArr);
                                    int i39 = this.bootstrapMethodOffsets[readUnsignedShort(i37)];
                                    Handle handle = (Handle) readConst(readUnsignedShort(i39), cArr);
                                    Object[] objArr = new Object[readUnsignedShort(i39 + 2)];
                                    int i40 = i39 + 4;
                                    for (int i41 = 0; i41 < objArr.length; i41++) {
                                        objArr[i41] = readConst(readUnsignedShort(i40), cArr);
                                        i40 += 2;
                                    }
                                    methodVisitor.visitInvokeDynamicInsn(readUTF85, readUTF86, handle, objArr);
                                    i26 += 5;
                                    break;
                                case 187:
                                case 189:
                                case 192:
                                case 193:
                                    methodVisitor.visitTypeInsn(i28, readClass(i26 + 1, cArr));
                                    i26 += 3;
                                    break;
                                case 196:
                                    int i42 = bArr[i26 + 1] & 255;
                                    if (i42 == 132) {
                                        methodVisitor.visitIincInsn(readUnsignedShort(i26 + 2), readShort(i26 + 4));
                                        i26 += 6;
                                        break;
                                    } else {
                                        methodVisitor.visitVarInsn(i42, readUnsignedShort(i26 + 2));
                                        i26 += 4;
                                        break;
                                    }
                                case 197:
                                    methodVisitor.visitMultiANewArrayInsn(readClass(i26 + 1, cArr), bArr[i26 + 3] & 255);
                                    i26 += 4;
                                    break;
                                case 200:
                                case 201:
                                    methodVisitor.visitJumpInsn(i28 - i25, labelArr[i27 + readInt(i26 + 1)]);
                                    i26 += 5;
                                    break;
                                case HttpStatus.SC_ACCEPTED /* 202 */:
                                case 203:
                                case HttpStatus.SC_NO_CONTENT /* 204 */:
                                case HttpStatus.SC_RESET_CONTENT /* 205 */:
                                case HttpStatus.SC_PARTIAL_CONTENT /* 206 */:
                                case HttpStatus.SC_MULTI_STATUS /* 207 */:
                                case 208:
                                case 209:
                                case 210:
                                case 211:
                                case 212:
                                case 213:
                                case 214:
                                case 215:
                                case 216:
                                case 217:
                                case 218:
                                case User32.VK_OEM_4 /* 219 */:
                                    if (i28 < 218) {
                                        i2 = i28 - 49;
                                    } else {
                                        i2 = i28 - 20;
                                    }
                                    int i43 = i2;
                                    Label label4 = labelArr[i27 + readUnsignedShort(i26 + 1)];
                                    if (i43 == 167 || i43 == 168) {
                                        methodVisitor.visitJumpInsn(i43 + 33, label4);
                                    } else {
                                        methodVisitor.visitJumpInsn(i43 < 167 ? ((i43 + 1) ^ 1) - 1 : i43 ^ 1, createLabel(i27 + 3, labelArr));
                                        methodVisitor.visitJumpInsn(200, label4);
                                        z3 = true;
                                    }
                                    i26 += 3;
                                    break;
                                case User32.VK_OEM_5 /* 220 */:
                                    methodVisitor.visitJumpInsn(200, labelArr[i27 + readInt(i26 + 1)]);
                                    z3 = true;
                                    i26 += 5;
                                    break;
                                default:
                                    throw new AssertionError();
                            }
                            while (iArr != null && i23 < iArr.length && typeAnnotationBytecodeOffset <= i27) {
                                if (typeAnnotationBytecodeOffset == i27) {
                                    int readTypeAnnotationTarget = readTypeAnnotationTarget(context, iArr[i23]);
                                    readElementValues(methodVisitor.visitInsnAnnotation(context.h, context.i, readUTF8(readTypeAnnotationTarget, cArr), true), readTypeAnnotationTarget + 2, true, cArr);
                                }
                                i23++;
                                typeAnnotationBytecodeOffset = getTypeAnnotationBytecodeOffset(iArr, i23);
                            }
                            while (iArr2 != null && i24 < iArr2.length && typeAnnotationBytecodeOffset2 <= i27) {
                                if (typeAnnotationBytecodeOffset2 == i27) {
                                    int readTypeAnnotationTarget2 = readTypeAnnotationTarget(context, iArr2[i24]);
                                    readElementValues(methodVisitor.visitInsnAnnotation(context.h, context.i, readUTF8(readTypeAnnotationTarget2, cArr), false), readTypeAnnotationTarget2 + 2, true, cArr);
                                }
                                i24++;
                                typeAnnotationBytecodeOffset2 = getTypeAnnotationBytecodeOffset(iArr2, i24);
                            }
                        }
                        if (labelArr[readInt] != null) {
                            methodVisitor.visitLabel(labelArr[readInt]);
                        }
                        if (i13 != 0 && (context.f4138b & 2) == 0) {
                            int[] iArr4 = null;
                            if (i14 != 0) {
                                iArr4 = new int[readUnsignedShort(i14) * 3];
                                int i44 = i14 + 2;
                                int length = iArr4.length;
                                while (length > 0) {
                                    int i45 = length - 1;
                                    iArr4[i45] = i44 + 6;
                                    int i46 = i45 - 1;
                                    iArr4[i46] = readUnsignedShort(i44 + 8);
                                    length = i46 - 1;
                                    iArr4[length] = readUnsignedShort(i44);
                                    i44 += 10;
                                }
                            }
                            int readUnsignedShort11 = readUnsignedShort(i13);
                            int i47 = i13 + 2;
                            while (true) {
                                int i48 = readUnsignedShort11;
                                readUnsignedShort11--;
                                if (i48 > 0) {
                                    int readUnsignedShort12 = readUnsignedShort(i47);
                                    int readUnsignedShort13 = readUnsignedShort(i47 + 2);
                                    String readUTF87 = readUTF8(i47 + 4, cArr);
                                    String readUTF88 = readUTF8(i47 + 6, cArr);
                                    int readUnsignedShort14 = readUnsignedShort(i47 + 8);
                                    i47 += 10;
                                    String str = null;
                                    if (iArr4 != null) {
                                        int i49 = 0;
                                        while (true) {
                                            if (i49 >= iArr4.length) {
                                                break;
                                            }
                                            if (iArr4[i49] != readUnsignedShort12 || iArr4[i49 + 1] != readUnsignedShort14) {
                                                i49 += 3;
                                            } else {
                                                str = readUTF8(iArr4[i49 + 2], cArr);
                                            }
                                        }
                                    }
                                    methodVisitor.visitLocalVariable(readUTF87, readUTF88, str, labelArr[readUnsignedShort12], labelArr[readUnsignedShort12 + readUnsignedShort13], readUnsignedShort14);
                                }
                            }
                        }
                        if (iArr != null) {
                            for (int i50 : iArr) {
                                int readByte = readByte(i50);
                                if (readByte == 64 || readByte == 65) {
                                    int readTypeAnnotationTarget3 = readTypeAnnotationTarget(context, i50);
                                    readElementValues(methodVisitor.visitLocalVariableAnnotation(context.h, context.i, context.j, context.k, context.l, readUTF8(readTypeAnnotationTarget3, cArr), true), readTypeAnnotationTarget3 + 2, true, cArr);
                                }
                            }
                        }
                        if (iArr2 != null) {
                            for (int i51 : iArr2) {
                                int readByte2 = readByte(i51);
                                if (readByte2 == 64 || readByte2 == 65) {
                                    int readTypeAnnotationTarget4 = readTypeAnnotationTarget(context, i51);
                                    readElementValues(methodVisitor.visitLocalVariableAnnotation(context.h, context.i, context.j, context.k, context.l, readUTF8(readTypeAnnotationTarget4, cArr), false), readTypeAnnotationTarget4 + 2, true, cArr);
                                }
                            }
                        }
                        while (attribute != null) {
                            Attribute attribute2 = attribute.f4132a;
                            attribute.f4132a = null;
                            methodVisitor.visitAttribute(attribute);
                            attribute = attribute2;
                        }
                        methodVisitor.visitMaxs(readUnsignedShort2, readUnsignedShort3);
                        return;
                    }
                }
            }
        }
    }

    protected Label readLabel(int i, Label[] labelArr) {
        if (labelArr[i] == null) {
            labelArr[i] = new Label();
        }
        return labelArr[i];
    }

    private Label createLabel(int i, Label[] labelArr) {
        Label readLabel = readLabel(i, labelArr);
        readLabel.f4145b = (short) (readLabel.f4145b & (-2));
        return readLabel;
    }

    private void createDebugLabel(int i, Label[] labelArr) {
        if (labelArr[i] == null) {
            Label readLabel = readLabel(i, labelArr);
            readLabel.f4145b = (short) (readLabel.f4145b | 1);
        }
    }

    private int[] readTypeAnnotations(MethodVisitor methodVisitor, Context context, int i, boolean z) {
        int i2;
        int readElementValues;
        char[] cArr = context.c;
        int[] iArr = new int[readUnsignedShort(i)];
        int i3 = i + 2;
        for (int i4 = 0; i4 < iArr.length; i4++) {
            iArr[i4] = i3;
            int readInt = readInt(i3);
            switch (readInt >>> 24) {
                case 16:
                case 17:
                case 18:
                case 23:
                case 66:
                case 67:
                case 68:
                case 69:
                case 70:
                    i2 = i3 + 3;
                    break;
                case 19:
                case 20:
                case 21:
                case 22:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                case 50:
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                case 60:
                case 61:
                case 62:
                case 63:
                default:
                    throw new IllegalArgumentException();
                case 64:
                case 65:
                    int readUnsignedShort = readUnsignedShort(i3 + 1);
                    i2 = i3 + 3;
                    while (true) {
                        int i5 = readUnsignedShort;
                        readUnsignedShort--;
                        if (i5 <= 0) {
                            break;
                        } else {
                            int readUnsignedShort2 = readUnsignedShort(i2);
                            int readUnsignedShort3 = readUnsignedShort(i2 + 2);
                            i2 += 6;
                            createLabel(readUnsignedShort2, context.g);
                            createLabel(readUnsignedShort2 + readUnsignedShort3, context.g);
                        }
                    }
                case 71:
                case 72:
                case 73:
                case 74:
                case 75:
                    i2 = i3 + 4;
                    break;
            }
            int readByte = readByte(i2);
            if ((readInt >>> 24) == 66) {
                TypePath typePath = readByte == 0 ? null : new TypePath(this.f4136a, i2);
                int i6 = i2 + 1 + (2 * readByte);
                readElementValues = readElementValues(methodVisitor.visitTryCatchAnnotation(readInt & (-256), typePath, readUTF8(i6, cArr), z), i6 + 2, true, cArr);
            } else {
                readElementValues = readElementValues(null, i2 + 3 + (2 * readByte), true, cArr);
            }
            i3 = readElementValues;
        }
        return iArr;
    }

    private int getTypeAnnotationBytecodeOffset(int[] iArr, int i) {
        if (iArr == null || i >= iArr.length || readByte(iArr[i]) < 67) {
            return -1;
        }
        return readUnsignedShort(iArr[i] + 1);
    }

    private int readTypeAnnotationTarget(Context context, int i) {
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
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            default:
                throw new IllegalArgumentException();
            case 16:
            case 17:
            case 18:
            case 23:
            case 66:
                i2 = readInt & (-256);
                i3 = i + 3;
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
                i3 = i + 3;
                context.j = new Label[readUnsignedShort];
                context.k = new Label[readUnsignedShort];
                context.l = new int[readUnsignedShort];
                for (int i4 = 0; i4 < readUnsignedShort; i4++) {
                    int readUnsignedShort2 = readUnsignedShort(i3);
                    int readUnsignedShort3 = readUnsignedShort(i3 + 2);
                    int readUnsignedShort4 = readUnsignedShort(i3 + 4);
                    i3 += 6;
                    context.j[i4] = createLabel(readUnsignedShort2, context.g);
                    context.k[i4] = createLabel(readUnsignedShort2 + readUnsignedShort3, context.g);
                    context.l[i4] = readUnsignedShort4;
                }
                break;
            case 67:
            case 68:
            case 69:
            case 70:
                i2 = readInt & (-16777216);
                i3 = i + 3;
                break;
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
                i2 = readInt & (-16776961);
                i3 = i + 4;
                break;
        }
        context.h = i2;
        int readByte = readByte(i3);
        context.i = readByte == 0 ? null : new TypePath(this.f4136a, i3);
        return i3 + 1 + (2 * readByte);
    }

    private void readParameterAnnotations(MethodVisitor methodVisitor, Context context, int i, boolean z) {
        int i2 = i + 1;
        int i3 = this.f4136a[i] & 255;
        methodVisitor.visitAnnotableParameterCount(i3, z);
        char[] cArr = context.c;
        for (int i4 = 0; i4 < i3; i4++) {
            int readUnsignedShort = readUnsignedShort(i2);
            i2 += 2;
            while (true) {
                int i5 = readUnsignedShort;
                readUnsignedShort--;
                if (i5 > 0) {
                    i2 = readElementValues(methodVisitor.visitParameterAnnotation(i4, readUTF8(i2, cArr), z), i2 + 2, true, cArr);
                }
            }
        }
    }

    private int readElementValues(AnnotationVisitor annotationVisitor, int i, boolean z, char[] cArr) {
        int readUnsignedShort = readUnsignedShort(i);
        int i2 = i + 2;
        if (!z) {
            while (true) {
                int i3 = readUnsignedShort;
                readUnsignedShort--;
                if (i3 <= 0) {
                    break;
                }
                i2 = readElementValue(annotationVisitor, i2, null, cArr);
            }
        } else {
            while (true) {
                int i4 = readUnsignedShort;
                readUnsignedShort--;
                if (i4 <= 0) {
                    break;
                }
                i2 = readElementValue(annotationVisitor, i2 + 2, readUTF8(i2, cArr), cArr);
            }
        }
        if (annotationVisitor != null) {
            annotationVisitor.visitEnd();
        }
        return i2;
    }

    private int readElementValue(AnnotationVisitor annotationVisitor, int i, String str, char[] cArr) {
        int i2;
        Object obj;
        if (annotationVisitor != null) {
            int i3 = i + 1;
            switch (this.f4136a[i] & 255) {
                case 64:
                    i2 = readElementValues(annotationVisitor.visitAnnotation(str, readUTF8(i3, cArr)), i3 + 2, true, cArr);
                    break;
                case 65:
                case 69:
                case 71:
                case 72:
                case 75:
                case 76:
                case 77:
                case 78:
                case 79:
                case 80:
                case 81:
                case 82:
                case 84:
                case 85:
                case 86:
                case 87:
                case 88:
                case 89:
                case 92:
                case 93:
                case 94:
                case 95:
                case 96:
                case 97:
                case 98:
                case 100:
                case 102:
                case 103:
                case 104:
                case 105:
                case 106:
                case 107:
                case 108:
                case 109:
                case 110:
                case 111:
                case 112:
                case 113:
                case 114:
                default:
                    throw new IllegalArgumentException();
                case 66:
                    annotationVisitor.visit(str, Byte.valueOf((byte) readInt(this.cpInfoOffsets[readUnsignedShort(i3)])));
                    i2 = i3 + 2;
                    break;
                case 67:
                    annotationVisitor.visit(str, Character.valueOf((char) readInt(this.cpInfoOffsets[readUnsignedShort(i3)])));
                    i2 = i3 + 2;
                    break;
                case 68:
                case 70:
                case 73:
                case 74:
                    annotationVisitor.visit(str, readConst(readUnsignedShort(i3), cArr));
                    i2 = i3 + 2;
                    break;
                case 83:
                    annotationVisitor.visit(str, Short.valueOf((short) readInt(this.cpInfoOffsets[readUnsignedShort(i3)])));
                    i2 = i3 + 2;
                    break;
                case 90:
                    if (readInt(this.cpInfoOffsets[readUnsignedShort(i3)]) == 0) {
                        obj = Boolean.FALSE;
                    } else {
                        obj = Boolean.TRUE;
                    }
                    annotationVisitor.visit(str, obj);
                    i2 = i3 + 2;
                    break;
                case 91:
                    int readUnsignedShort = readUnsignedShort(i3);
                    i2 = i3 + 2;
                    if (readUnsignedShort == 0) {
                        return readElementValues(annotationVisitor.visitArray(str), i2 - 2, false, cArr);
                    }
                    switch (this.f4136a[i2] & 255) {
                        case 66:
                            byte[] bArr = new byte[readUnsignedShort];
                            for (int i4 = 0; i4 < readUnsignedShort; i4++) {
                                bArr[i4] = (byte) readInt(this.cpInfoOffsets[readUnsignedShort(i2 + 1)]);
                                i2 += 3;
                            }
                            annotationVisitor.visit(str, bArr);
                            break;
                        case 67:
                            char[] cArr2 = new char[readUnsignedShort];
                            for (int i5 = 0; i5 < readUnsignedShort; i5++) {
                                cArr2[i5] = (char) readInt(this.cpInfoOffsets[readUnsignedShort(i2 + 1)]);
                                i2 += 3;
                            }
                            annotationVisitor.visit(str, cArr2);
                            break;
                        case 68:
                            double[] dArr = new double[readUnsignedShort];
                            for (int i6 = 0; i6 < readUnsignedShort; i6++) {
                                dArr[i6] = Double.longBitsToDouble(readLong(this.cpInfoOffsets[readUnsignedShort(i2 + 1)]));
                                i2 += 3;
                            }
                            annotationVisitor.visit(str, dArr);
                            break;
                        case 69:
                        case 71:
                        case 72:
                        case 75:
                        case 76:
                        case 77:
                        case 78:
                        case 79:
                        case 80:
                        case 81:
                        case 82:
                        case 84:
                        case 85:
                        case 86:
                        case 87:
                        case 88:
                        case 89:
                        default:
                            i2 = readElementValues(annotationVisitor.visitArray(str), i2 - 2, false, cArr);
                            break;
                        case 70:
                            float[] fArr = new float[readUnsignedShort];
                            for (int i7 = 0; i7 < readUnsignedShort; i7++) {
                                fArr[i7] = Float.intBitsToFloat(readInt(this.cpInfoOffsets[readUnsignedShort(i2 + 1)]));
                                i2 += 3;
                            }
                            annotationVisitor.visit(str, fArr);
                            break;
                        case 73:
                            int[] iArr = new int[readUnsignedShort];
                            for (int i8 = 0; i8 < readUnsignedShort; i8++) {
                                iArr[i8] = readInt(this.cpInfoOffsets[readUnsignedShort(i2 + 1)]);
                                i2 += 3;
                            }
                            annotationVisitor.visit(str, iArr);
                            break;
                        case 74:
                            long[] jArr = new long[readUnsignedShort];
                            for (int i9 = 0; i9 < readUnsignedShort; i9++) {
                                jArr[i9] = readLong(this.cpInfoOffsets[readUnsignedShort(i2 + 1)]);
                                i2 += 3;
                            }
                            annotationVisitor.visit(str, jArr);
                            break;
                        case 83:
                            short[] sArr = new short[readUnsignedShort];
                            for (int i10 = 0; i10 < readUnsignedShort; i10++) {
                                sArr[i10] = (short) readInt(this.cpInfoOffsets[readUnsignedShort(i2 + 1)]);
                                i2 += 3;
                            }
                            annotationVisitor.visit(str, sArr);
                            break;
                        case 90:
                            boolean[] zArr = new boolean[readUnsignedShort];
                            for (int i11 = 0; i11 < readUnsignedShort; i11++) {
                                zArr[i11] = readInt(this.cpInfoOffsets[readUnsignedShort(i2 + 1)]) != 0;
                                i2 += 3;
                            }
                            annotationVisitor.visit(str, zArr);
                            break;
                    }
                case 99:
                    annotationVisitor.visit(str, Type.getType(readUTF8(i3, cArr)));
                    i2 = i3 + 2;
                    break;
                case 101:
                    annotationVisitor.visitEnum(str, readUTF8(i3, cArr), readUTF8(i3 + 2, cArr));
                    i2 = i3 + 4;
                    break;
                case 115:
                    annotationVisitor.visit(str, readUTF8(i3, cArr));
                    i2 = i3 + 2;
                    break;
            }
            return i2;
        }
        switch (this.f4136a[i] & 255) {
            case 64:
                return readElementValues(null, i + 3, true, cArr);
            case 91:
                return readElementValues(null, i + 1, false, cArr);
            case 101:
                return i + 5;
            default:
                return i + 3;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x0169, code lost:            r7.o = r10;     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x016f, code lost:            return;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void computeImplicitFrame(net.bytebuddy.jar.asm.Context r7) {
        /*
            Method dump skipped, instructions count: 368
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.jar.asm.ClassReader.computeImplicitFrame(net.bytebuddy.jar.asm.Context):void");
    }

    private int readStackMapFrame(int i, boolean z, boolean z2, Context context) {
        int i2;
        int readUnsignedShort;
        int i3 = i;
        char[] cArr = context.c;
        Label[] labelArr = context.g;
        if (z) {
            i3++;
            i2 = this.f4136a[i] & 255;
        } else {
            i2 = 255;
            context.m = -1;
        }
        context.p = 0;
        if (i2 < 64) {
            readUnsignedShort = i2;
            context.n = 3;
            context.r = 0;
        } else if (i2 < 128) {
            readUnsignedShort = i2 - 64;
            i3 = readVerificationTypeInfo(i3, context.s, 0, cArr, labelArr);
            context.n = 4;
            context.r = 1;
        } else if (i2 >= 247) {
            readUnsignedShort = readUnsignedShort(i3);
            i3 += 2;
            if (i2 == 247) {
                i3 = readVerificationTypeInfo(i3, context.s, 0, cArr, labelArr);
                context.n = 4;
                context.r = 1;
            } else if (i2 >= 248 && i2 < 251) {
                context.n = 2;
                context.p = User32.VK_ZOOM - i2;
                context.o -= context.p;
                context.r = 0;
            } else if (i2 == 251) {
                context.n = 3;
                context.r = 0;
            } else if (i2 < 255) {
                int i4 = z2 ? context.o : 0;
                for (int i5 = i2 - User32.VK_ZOOM; i5 > 0; i5--) {
                    int i6 = i4;
                    i4++;
                    i3 = readVerificationTypeInfo(i3, context.q, i6, cArr, labelArr);
                }
                context.n = 1;
                context.p = i2 - User32.VK_ZOOM;
                context.o += context.p;
                context.r = 0;
            } else {
                int readUnsignedShort2 = readUnsignedShort(i3);
                int i7 = i3 + 2;
                context.n = 0;
                context.p = readUnsignedShort2;
                context.o = readUnsignedShort2;
                for (int i8 = 0; i8 < readUnsignedShort2; i8++) {
                    i7 = readVerificationTypeInfo(i7, context.q, i8, cArr, labelArr);
                }
                int readUnsignedShort3 = readUnsignedShort(i7);
                i3 = i7 + 2;
                context.r = readUnsignedShort3;
                for (int i9 = 0; i9 < readUnsignedShort3; i9++) {
                    i3 = readVerificationTypeInfo(i3, context.s, i9, cArr, labelArr);
                }
            }
        } else {
            throw new IllegalArgumentException();
        }
        context.m += readUnsignedShort + 1;
        createLabel(context.m, labelArr);
        return i3;
    }

    private int readVerificationTypeInfo(int i, Object[] objArr, int i2, char[] cArr, Label[] labelArr) {
        int i3 = i + 1;
        switch (this.f4136a[i] & 255) {
            case 0:
                objArr[i2] = Opcodes.TOP;
                break;
            case 1:
                objArr[i2] = Opcodes.INTEGER;
                break;
            case 2:
                objArr[i2] = Opcodes.FLOAT;
                break;
            case 3:
                objArr[i2] = Opcodes.DOUBLE;
                break;
            case 4:
                objArr[i2] = Opcodes.LONG;
                break;
            case 5:
                objArr[i2] = Opcodes.NULL;
                break;
            case 6:
                objArr[i2] = Opcodes.UNINITIALIZED_THIS;
                break;
            case 7:
                objArr[i2] = readClass(i3, cArr);
                i3 += 2;
                break;
            case 8:
                objArr[i2] = createLabel(readUnsignedShort(i3), labelArr);
                i3 += 2;
                break;
            default:
                throw new IllegalArgumentException();
        }
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a() {
        int readUnsignedShort = this.header + 8 + (readUnsignedShort(this.header + 6) << 1);
        int readUnsignedShort2 = readUnsignedShort(readUnsignedShort);
        int i = readUnsignedShort + 2;
        while (true) {
            int i2 = readUnsignedShort2;
            readUnsignedShort2--;
            if (i2 <= 0) {
                break;
            }
            int readUnsignedShort3 = readUnsignedShort(i + 6);
            i += 8;
            while (true) {
                int i3 = readUnsignedShort3;
                readUnsignedShort3--;
                if (i3 > 0) {
                    i += 6 + readInt(i + 2);
                }
            }
        }
        int readUnsignedShort4 = readUnsignedShort(i);
        int i4 = i + 2;
        while (true) {
            int i5 = readUnsignedShort4;
            readUnsignedShort4--;
            if (i5 > 0) {
                int readUnsignedShort5 = readUnsignedShort(i4 + 6);
                i4 += 8;
                while (true) {
                    int i6 = readUnsignedShort5;
                    readUnsignedShort5--;
                    if (i6 > 0) {
                        i4 += 6 + readInt(i4 + 2);
                    }
                }
            } else {
                return i4 + 2;
            }
        }
    }

    private int[] readBootstrapMethodsAttribute(int i) {
        char[] cArr = new char[i];
        int a2 = a();
        for (int readUnsignedShort = readUnsignedShort(a2 - 2); readUnsignedShort > 0; readUnsignedShort--) {
            String readUTF8 = readUTF8(a2, cArr);
            int readInt = readInt(a2 + 2);
            int i2 = a2 + 6;
            if ("BootstrapMethods".equals(readUTF8)) {
                int[] iArr = new int[readUnsignedShort(i2)];
                int i3 = i2 + 2;
                for (int i4 = 0; i4 < iArr.length; i4++) {
                    iArr[i4] = i3;
                    i3 += 4 + (readUnsignedShort(i3 + 2) << 1);
                }
                return iArr;
            }
            a2 = i2 + readInt;
        }
        throw new IllegalArgumentException();
    }

    private Attribute readAttribute(Attribute[] attributeArr, String str, int i, int i2, char[] cArr, int i3, Label[] labelArr) {
        for (Attribute attribute : attributeArr) {
            if (attribute.type.equals(str)) {
                return attribute.read(this, i, i2, cArr, i3, labelArr);
            }
        }
        return new Attribute(str).read(this, i, i2, null, -1, null);
    }

    public int getItemCount() {
        return this.cpInfoOffsets.length;
    }

    public int getItem(int i) {
        return this.cpInfoOffsets[i];
    }

    public int getMaxStringLength() {
        return this.maxStringLength;
    }

    public int readByte(int i) {
        return this.f4136a[i] & 255;
    }

    public int readUnsignedShort(int i) {
        byte[] bArr = this.f4136a;
        return ((bArr[i] & 255) << 8) | (bArr[i + 1] & 255);
    }

    public short readShort(int i) {
        byte[] bArr = this.f4136a;
        return (short) (((bArr[i] & 255) << 8) | (bArr[i + 1] & 255));
    }

    public int readInt(int i) {
        byte[] bArr = this.f4136a;
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
        return a(readUnsignedShort, cArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String a(int i, char[] cArr) {
        String str = this.constantUtf8Values[i];
        if (str != null) {
            return str;
        }
        int i2 = this.cpInfoOffsets[i];
        String[] strArr = this.constantUtf8Values;
        String readUtf = readUtf(i2 + 2, readUnsignedShort(i2), cArr);
        strArr[i] = readUtf;
        return readUtf;
    }

    private String readUtf(int i, int i2, char[] cArr) {
        int i3 = i;
        int i4 = i + i2;
        int i5 = 0;
        byte[] bArr = this.f4136a;
        while (i3 < i4) {
            int i6 = i3;
            i3++;
            byte b2 = bArr[i6];
            if ((b2 & 128) == 0) {
                int i7 = i5;
                i5++;
                cArr[i7] = (char) (b2 & Byte.MAX_VALUE);
            } else if ((b2 & 224) != 192) {
                int i8 = i5;
                i5++;
                int i9 = i3 + 1;
                int i10 = ((b2 & 15) << 12) + ((bArr[i3] & 63) << 6);
                i3 = i9 + 1;
                cArr[i8] = (char) (i10 + (bArr[i9] & 63));
            } else {
                int i11 = i5;
                i5++;
                i3++;
                cArr[i11] = (char) (((b2 & 31) << 6) + (bArr[i3] & 63));
            }
        }
        return new String(cArr, 0, i5);
    }

    private String readStringish(int i, char[] cArr) {
        return readUTF8(this.cpInfoOffsets[readUnsignedShort(i)], cArr);
    }

    public String readClass(int i, char[] cArr) {
        return readStringish(i, cArr);
    }

    public String readModule(int i, char[] cArr) {
        return readStringish(i, cArr);
    }

    public String readPackage(int i, char[] cArr) {
        return readStringish(i, cArr);
    }

    private ConstantDynamic readConstantDynamic(int i, char[] cArr) {
        ConstantDynamic constantDynamic = this.constantDynamicValues[i];
        if (constantDynamic != null) {
            return constantDynamic;
        }
        int i2 = this.cpInfoOffsets[i];
        int i3 = this.cpInfoOffsets[readUnsignedShort(i2 + 2)];
        String readUTF8 = readUTF8(i3, cArr);
        String readUTF82 = readUTF8(i3 + 2, cArr);
        int i4 = this.bootstrapMethodOffsets[readUnsignedShort(i2)];
        Handle handle = (Handle) readConst(readUnsignedShort(i4), cArr);
        Object[] objArr = new Object[readUnsignedShort(i4 + 2)];
        int i5 = i4 + 4;
        for (int i6 = 0; i6 < objArr.length; i6++) {
            objArr[i6] = readConst(readUnsignedShort(i5), cArr);
            i5 += 2;
        }
        ConstantDynamic[] constantDynamicArr = this.constantDynamicValues;
        ConstantDynamic constantDynamic2 = new ConstantDynamic(readUTF8, readUTF82, handle, objArr);
        constantDynamicArr[i] = constantDynamic2;
        return constantDynamic2;
    }

    public Object readConst(int i, char[] cArr) {
        int i2 = this.cpInfoOffsets[i];
        switch (this.f4136a[i2 - 1]) {
            case 3:
                return Integer.valueOf(readInt(i2));
            case 4:
                return Float.valueOf(Float.intBitsToFloat(readInt(i2)));
            case 5:
                return Long.valueOf(readLong(i2));
            case 6:
                return Double.valueOf(Double.longBitsToDouble(readLong(i2)));
            case 7:
                return Type.getObjectType(readUTF8(i2, cArr));
            case 8:
                return readUTF8(i2, cArr);
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            default:
                throw new IllegalArgumentException();
            case 15:
                int readByte = readByte(i2);
                int i3 = this.cpInfoOffsets[readUnsignedShort(i2 + 1)];
                int i4 = this.cpInfoOffsets[readUnsignedShort(i3 + 2)];
                return new Handle(readByte, readClass(i3, cArr), readUTF8(i4, cArr), readUTF8(i4 + 2, cArr), this.f4136a[i3 - 1] == 11);
            case 16:
                return Type.getMethodType(readUTF8(i2, cArr));
            case 17:
                return readConstantDynamic(i, cArr);
        }
    }
}
