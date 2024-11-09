package net.bytebuddy.jar.asm;

import net.bytebuddy.implementation.auxiliary.TypeProxy;
import net.bytebuddy.jar.asm.Attribute;

/* loaded from: infinitode-2.jar:net/bytebuddy/jar/asm/ClassWriter.class */
public class ClassWriter extends ClassVisitor {
    public static final int COMPUTE_MAXS = 1;
    public static final int COMPUTE_FRAMES = 2;
    private final int flags;
    private int version;
    private final SymbolTable symbolTable;
    private int accessFlags;
    private int thisClass;
    private int superClass;
    private int interfaceCount;
    private int[] interfaces;
    private FieldWriter firstField;
    private FieldWriter lastField;
    private MethodWriter firstMethod;
    private MethodWriter lastMethod;
    private int numberOfInnerClasses;
    private ByteVector innerClasses;
    private int enclosingClassIndex;
    private int enclosingMethodIndex;
    private int signatureIndex;
    private int sourceFileIndex;
    private ByteVector debugExtension;
    private AnnotationWriter lastRuntimeVisibleAnnotation;
    private AnnotationWriter lastRuntimeInvisibleAnnotation;
    private AnnotationWriter lastRuntimeVisibleTypeAnnotation;
    private AnnotationWriter lastRuntimeInvisibleTypeAnnotation;
    private ModuleWriter moduleWriter;
    private int nestHostClassIndex;
    private int numberOfNestMemberClasses;
    private ByteVector nestMemberClasses;
    private int numberOfPermittedSubclasses;
    private ByteVector permittedSubclasses;
    private RecordComponentWriter firstRecordComponent;
    private RecordComponentWriter lastRecordComponent;
    private Attribute firstAttribute;
    private int compute;

    public ClassWriter(int i) {
        this(null, i);
    }

    public ClassWriter(ClassReader classReader, int i) {
        super(589824);
        this.flags = i;
        this.symbolTable = classReader == null ? new SymbolTable(this) : new SymbolTable(this, classReader);
        if ((i & 2) != 0) {
            this.compute = 4;
        } else if ((i & 1) != 0) {
            this.compute = 1;
        } else {
            this.compute = 0;
        }
    }

    public boolean hasFlags(int i) {
        return (this.flags & i) == i;
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public final void visit(int i, int i2, String str, String str2, String str3, String[] strArr) {
        this.version = i;
        this.accessFlags = i2;
        this.thisClass = this.symbolTable.a(i & 65535, str);
        if (str2 != null) {
            this.signatureIndex = this.symbolTable.b(str2);
        }
        this.superClass = str3 == null ? 0 : this.symbolTable.a(str3).f4146a;
        if (strArr != null && strArr.length > 0) {
            this.interfaceCount = strArr.length;
            this.interfaces = new int[this.interfaceCount];
            for (int i3 = 0; i3 < this.interfaceCount; i3++) {
                this.interfaces[i3] = this.symbolTable.a(strArr[i3]).f4146a;
            }
        }
        if (this.compute == 1 && (i & 65535) >= 51) {
            this.compute = 2;
        }
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public final void visitSource(String str, String str2) {
        if (str != null) {
            this.sourceFileIndex = this.symbolTable.b(str);
        }
        if (str2 != null) {
            this.debugExtension = new ByteVector().a(str2, 0, Integer.MAX_VALUE);
        }
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public final ModuleVisitor visitModule(String str, int i, String str2) {
        ModuleWriter moduleWriter = new ModuleWriter(this.symbolTable, this.symbolTable.d(str).f4146a, i, str2 == null ? 0 : this.symbolTable.b(str2));
        this.moduleWriter = moduleWriter;
        return moduleWriter;
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public final void visitNestHost(String str) {
        this.nestHostClassIndex = this.symbolTable.a(str).f4146a;
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public final void visitOuterClass(String str, String str2, String str3) {
        this.enclosingClassIndex = this.symbolTable.a(str).f4146a;
        if (str2 != null && str3 != null) {
            this.enclosingMethodIndex = this.symbolTable.a(str2, str3);
        }
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public final AnnotationVisitor visitAnnotation(String str, boolean z) {
        if (z) {
            AnnotationWriter a2 = AnnotationWriter.a(this.symbolTable, str, this.lastRuntimeVisibleAnnotation);
            this.lastRuntimeVisibleAnnotation = a2;
            return a2;
        }
        AnnotationWriter a3 = AnnotationWriter.a(this.symbolTable, str, this.lastRuntimeInvisibleAnnotation);
        this.lastRuntimeInvisibleAnnotation = a3;
        return a3;
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public final AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String str, boolean z) {
        if (z) {
            AnnotationWriter a2 = AnnotationWriter.a(this.symbolTable, i, typePath, str, this.lastRuntimeVisibleTypeAnnotation);
            this.lastRuntimeVisibleTypeAnnotation = a2;
            return a2;
        }
        AnnotationWriter a3 = AnnotationWriter.a(this.symbolTable, i, typePath, str, this.lastRuntimeInvisibleTypeAnnotation);
        this.lastRuntimeInvisibleTypeAnnotation = a3;
        return a3;
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public final void visitAttribute(Attribute attribute) {
        attribute.f4132a = this.firstAttribute;
        this.firstAttribute = attribute;
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public final void visitNestMember(String str) {
        if (this.nestMemberClasses == null) {
            this.nestMemberClasses = new ByteVector();
        }
        this.numberOfNestMemberClasses++;
        this.nestMemberClasses.putShort(this.symbolTable.a(str).f4146a);
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public final void visitPermittedSubclass(String str) {
        if (this.permittedSubclasses == null) {
            this.permittedSubclasses = new ByteVector();
        }
        this.numberOfPermittedSubclasses++;
        this.permittedSubclasses.putShort(this.symbolTable.a(str).f4146a);
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public final void visitInnerClass(String str, String str2, String str3, int i) {
        if (this.innerClasses == null) {
            this.innerClasses = new ByteVector();
        }
        Symbol a2 = this.symbolTable.a(str);
        if (a2.g == 0) {
            this.numberOfInnerClasses++;
            this.innerClasses.putShort(a2.f4146a);
            this.innerClasses.putShort(str2 == null ? 0 : this.symbolTable.a(str2).f4146a);
            this.innerClasses.putShort(str3 == null ? 0 : this.symbolTable.b(str3));
            this.innerClasses.putShort(i);
            a2.g = this.numberOfInnerClasses;
        }
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public final RecordComponentVisitor visitRecordComponent(String str, String str2, String str3) {
        RecordComponentWriter recordComponentWriter = new RecordComponentWriter(this.symbolTable, str, str2, str3);
        if (this.firstRecordComponent == null) {
            this.firstRecordComponent = recordComponentWriter;
        } else {
            this.lastRecordComponent.delegate = recordComponentWriter;
        }
        this.lastRecordComponent = recordComponentWriter;
        return recordComponentWriter;
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public final FieldVisitor visitField(int i, String str, String str2, String str3, Object obj) {
        FieldWriter fieldWriter = new FieldWriter(this.symbolTable, i, str, str2, str3, obj);
        if (this.firstField == null) {
            this.firstField = fieldWriter;
        } else {
            this.lastField.fv = fieldWriter;
        }
        this.lastField = fieldWriter;
        return fieldWriter;
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public final MethodVisitor visitMethod(int i, String str, String str2, String str3, String[] strArr) {
        MethodWriter methodWriter = new MethodWriter(this.symbolTable, i, str, str2, str3, strArr, this.compute);
        if (this.firstMethod == null) {
            this.firstMethod = methodWriter;
        } else {
            this.lastMethod.mv = methodWriter;
        }
        this.lastMethod = methodWriter;
        return methodWriter;
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public final void visitEnd() {
    }

    public byte[] toByteArray() {
        int i = 24 + (2 * this.interfaceCount);
        int i2 = 0;
        FieldWriter fieldWriter = this.firstField;
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
        MethodWriter methodWriter = this.firstMethod;
        while (true) {
            MethodWriter methodWriter2 = methodWriter;
            if (methodWriter2 == null) {
                break;
            }
            i3++;
            i += methodWriter2.d();
            methodWriter = (MethodWriter) methodWriter2.mv;
        }
        int i4 = 0;
        if (this.innerClasses != null) {
            i4 = 0 + 1;
            i += 8 + this.innerClasses.f4134b;
            this.symbolTable.b("InnerClasses");
        }
        if (this.enclosingClassIndex != 0) {
            i4++;
            i += 10;
            this.symbolTable.b("EnclosingMethod");
        }
        if ((this.accessFlags & 4096) != 0 && (this.version & 65535) < 49) {
            i4++;
            i += 6;
            this.symbolTable.b("Synthetic");
        }
        if (this.signatureIndex != 0) {
            i4++;
            i += 8;
            this.symbolTable.b("Signature");
        }
        if (this.sourceFileIndex != 0) {
            i4++;
            i += 8;
            this.symbolTable.b("SourceFile");
        }
        if (this.debugExtension != null) {
            i4++;
            i += 6 + this.debugExtension.f4134b;
            this.symbolTable.b("SourceDebugExtension");
        }
        if ((this.accessFlags & 131072) != 0) {
            i4++;
            i += 6;
            this.symbolTable.b("Deprecated");
        }
        if (this.lastRuntimeVisibleAnnotation != null) {
            i4++;
            i += this.lastRuntimeVisibleAnnotation.a("RuntimeVisibleAnnotations");
        }
        if (this.lastRuntimeInvisibleAnnotation != null) {
            i4++;
            i += this.lastRuntimeInvisibleAnnotation.a("RuntimeInvisibleAnnotations");
        }
        if (this.lastRuntimeVisibleTypeAnnotation != null) {
            i4++;
            i += this.lastRuntimeVisibleTypeAnnotation.a("RuntimeVisibleTypeAnnotations");
        }
        if (this.lastRuntimeInvisibleTypeAnnotation != null) {
            i4++;
            i += this.lastRuntimeInvisibleTypeAnnotation.a("RuntimeInvisibleTypeAnnotations");
        }
        if (this.symbolTable.f() > 0) {
            i4++;
            i += this.symbolTable.f();
        }
        if (this.moduleWriter != null) {
            i4 += this.moduleWriter.a();
            i += this.moduleWriter.b();
        }
        if (this.nestHostClassIndex != 0) {
            i4++;
            i += 8;
            this.symbolTable.b("NestHost");
        }
        if (this.nestMemberClasses != null) {
            i4++;
            i += 8 + this.nestMemberClasses.f4134b;
            this.symbolTable.b("NestMembers");
        }
        if (this.permittedSubclasses != null) {
            i4++;
            i += 8 + this.permittedSubclasses.f4134b;
            this.symbolTable.b("PermittedSubclasses");
        }
        int i5 = 0;
        int i6 = 0;
        if ((this.accessFlags & 65536) != 0 || this.firstRecordComponent != null) {
            RecordComponentWriter recordComponentWriter = this.firstRecordComponent;
            while (true) {
                RecordComponentWriter recordComponentWriter2 = recordComponentWriter;
                if (recordComponentWriter2 == null) {
                    break;
                }
                i5++;
                i6 += recordComponentWriter2.a();
                recordComponentWriter = (RecordComponentWriter) recordComponentWriter2.delegate;
            }
            i4++;
            i += i6 + 8;
            this.symbolTable.b("Record");
        }
        if (this.firstAttribute != null) {
            i4 += this.firstAttribute.a();
            i += this.firstAttribute.a(this.symbolTable);
        }
        int e = i + this.symbolTable.e();
        int d = this.symbolTable.d();
        if (d > 65535) {
            throw new ClassTooLargeException(this.symbolTable.c(), d);
        }
        ByteVector byteVector = new ByteVector(e);
        byteVector.putInt(-889275714).putInt(this.version);
        this.symbolTable.a(byteVector);
        byteVector.putShort(this.accessFlags & (((this.version & 65535) < 49 ? 4096 : 0) ^ (-1))).putShort(this.thisClass).putShort(this.superClass);
        byteVector.putShort(this.interfaceCount);
        for (int i7 = 0; i7 < this.interfaceCount; i7++) {
            byteVector.putShort(this.interfaces[i7]);
        }
        byteVector.putShort(i2);
        FieldWriter fieldWriter3 = this.firstField;
        while (true) {
            FieldWriter fieldWriter4 = fieldWriter3;
            if (fieldWriter4 == null) {
                break;
            }
            fieldWriter4.a(byteVector);
            fieldWriter3 = (FieldWriter) fieldWriter4.fv;
        }
        byteVector.putShort(i3);
        boolean z = false;
        boolean z2 = false;
        MethodWriter methodWriter3 = this.firstMethod;
        while (true) {
            MethodWriter methodWriter4 = methodWriter3;
            if (methodWriter4 == null) {
                break;
            }
            z |= methodWriter4.a();
            z2 |= methodWriter4.b();
            methodWriter4.a(byteVector);
            methodWriter3 = (MethodWriter) methodWriter4.mv;
        }
        byteVector.putShort(i4);
        if (this.innerClasses != null) {
            byteVector.putShort(this.symbolTable.b("InnerClasses")).putInt(this.innerClasses.f4134b + 2).putShort(this.numberOfInnerClasses).putByteArray(this.innerClasses.f4133a, 0, this.innerClasses.f4134b);
        }
        if (this.enclosingClassIndex != 0) {
            byteVector.putShort(this.symbolTable.b("EnclosingMethod")).putInt(4).putShort(this.enclosingClassIndex).putShort(this.enclosingMethodIndex);
        }
        if ((this.accessFlags & 4096) != 0 && (this.version & 65535) < 49) {
            byteVector.putShort(this.symbolTable.b("Synthetic")).putInt(0);
        }
        if (this.signatureIndex != 0) {
            byteVector.putShort(this.symbolTable.b("Signature")).putInt(2).putShort(this.signatureIndex);
        }
        if (this.sourceFileIndex != 0) {
            byteVector.putShort(this.symbolTable.b("SourceFile")).putInt(2).putShort(this.sourceFileIndex);
        }
        if (this.debugExtension != null) {
            int i8 = this.debugExtension.f4134b;
            byteVector.putShort(this.symbolTable.b("SourceDebugExtension")).putInt(i8).putByteArray(this.debugExtension.f4133a, 0, i8);
        }
        if ((this.accessFlags & 131072) != 0) {
            byteVector.putShort(this.symbolTable.b("Deprecated")).putInt(0);
        }
        AnnotationWriter.a(this.symbolTable, this.lastRuntimeVisibleAnnotation, this.lastRuntimeInvisibleAnnotation, this.lastRuntimeVisibleTypeAnnotation, this.lastRuntimeInvisibleTypeAnnotation, byteVector);
        this.symbolTable.b(byteVector);
        if (this.moduleWriter != null) {
            this.moduleWriter.a(byteVector);
        }
        if (this.nestHostClassIndex != 0) {
            byteVector.putShort(this.symbolTable.b("NestHost")).putInt(2).putShort(this.nestHostClassIndex);
        }
        if (this.nestMemberClasses != null) {
            byteVector.putShort(this.symbolTable.b("NestMembers")).putInt(this.nestMemberClasses.f4134b + 2).putShort(this.numberOfNestMemberClasses).putByteArray(this.nestMemberClasses.f4133a, 0, this.nestMemberClasses.f4134b);
        }
        if (this.permittedSubclasses != null) {
            byteVector.putShort(this.symbolTable.b("PermittedSubclasses")).putInt(this.permittedSubclasses.f4134b + 2).putShort(this.numberOfPermittedSubclasses).putByteArray(this.permittedSubclasses.f4133a, 0, this.permittedSubclasses.f4134b);
        }
        if ((this.accessFlags & 65536) != 0 || this.firstRecordComponent != null) {
            byteVector.putShort(this.symbolTable.b("Record")).putInt(i6 + 2).putShort(i5);
            RecordComponentWriter recordComponentWriter3 = this.firstRecordComponent;
            while (true) {
                RecordComponentWriter recordComponentWriter4 = recordComponentWriter3;
                if (recordComponentWriter4 == null) {
                    break;
                }
                recordComponentWriter4.a(byteVector);
                recordComponentWriter3 = (RecordComponentWriter) recordComponentWriter4.delegate;
            }
        }
        if (this.firstAttribute != null) {
            this.firstAttribute.a(this.symbolTable, byteVector);
        }
        if (z2) {
            return replaceAsmInstructions(byteVector.f4133a, z);
        }
        return byteVector.f4133a;
    }

    private byte[] replaceAsmInstructions(byte[] bArr, boolean z) {
        Attribute[] attributePrototypes = getAttributePrototypes();
        this.firstField = null;
        this.lastField = null;
        this.firstMethod = null;
        this.lastMethod = null;
        this.lastRuntimeVisibleAnnotation = null;
        this.lastRuntimeInvisibleAnnotation = null;
        this.lastRuntimeVisibleTypeAnnotation = null;
        this.lastRuntimeInvisibleTypeAnnotation = null;
        this.moduleWriter = null;
        this.nestHostClassIndex = 0;
        this.numberOfNestMemberClasses = 0;
        this.nestMemberClasses = null;
        this.numberOfPermittedSubclasses = 0;
        this.permittedSubclasses = null;
        this.firstRecordComponent = null;
        this.lastRecordComponent = null;
        this.firstAttribute = null;
        this.compute = z ? 3 : 0;
        new ClassReader(bArr, 0, false).accept(this, attributePrototypes, (z ? 8 : 0) | 256);
        return toByteArray();
    }

    private Attribute[] getAttributePrototypes() {
        Attribute.Set set = new Attribute.Set();
        set.a(this.firstAttribute);
        FieldWriter fieldWriter = this.firstField;
        while (true) {
            FieldWriter fieldWriter2 = fieldWriter;
            if (fieldWriter2 == null) {
                break;
            }
            fieldWriter2.a(set);
            fieldWriter = (FieldWriter) fieldWriter2.fv;
        }
        MethodWriter methodWriter = this.firstMethod;
        while (true) {
            MethodWriter methodWriter2 = methodWriter;
            if (methodWriter2 == null) {
                break;
            }
            methodWriter2.a(set);
            methodWriter = (MethodWriter) methodWriter2.mv;
        }
        RecordComponentWriter recordComponentWriter = this.firstRecordComponent;
        while (true) {
            RecordComponentWriter recordComponentWriter2 = recordComponentWriter;
            if (recordComponentWriter2 != null) {
                recordComponentWriter2.a(set);
                recordComponentWriter = (RecordComponentWriter) recordComponentWriter2.delegate;
            } else {
                return set.a();
            }
        }
    }

    public int newConst(Object obj) {
        return this.symbolTable.a(obj).f4146a;
    }

    public int newUTF8(String str) {
        return this.symbolTable.b(str);
    }

    public int newClass(String str) {
        return this.symbolTable.a(str).f4146a;
    }

    public int newMethodType(String str) {
        return this.symbolTable.c(str).f4146a;
    }

    public int newModule(String str) {
        return this.symbolTable.d(str).f4146a;
    }

    public int newPackage(String str) {
        return this.symbolTable.e(str).f4146a;
    }

    @Deprecated
    public int newHandle(int i, String str, String str2, String str3) {
        return newHandle(i, str, str2, str3, i == 9);
    }

    public int newHandle(int i, String str, String str2, String str3, boolean z) {
        return this.symbolTable.a(i, str, str2, str3, z).f4146a;
    }

    public int newConstantDynamic(String str, String str2, Handle handle, Object... objArr) {
        return this.symbolTable.a(str, str2, handle, objArr).f4146a;
    }

    public int newInvokeDynamic(String str, String str2, Handle handle, Object... objArr) {
        return this.symbolTable.b(str, str2, handle, objArr).f4146a;
    }

    public int newField(String str, String str2, String str3) {
        return this.symbolTable.a(str, str2, str3).f4146a;
    }

    public int newMethod(String str, String str2, String str3, boolean z) {
        return this.symbolTable.a(str, str2, str3, z).f4146a;
    }

    public int newNameType(String str, String str2) {
        return this.symbolTable.a(str, str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getCommonSuperClass(String str, String str2) {
        Class<? super Object> superclass;
        ClassLoader classLoader = getClassLoader();
        try {
            Class<?> cls = Class.forName(str.replace('/', '.'), false, classLoader);
            try {
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
            } catch (ClassNotFoundException e) {
                throw new TypeNotPresentException(str2, e);
            }
        } catch (ClassNotFoundException e2) {
            throw new TypeNotPresentException(str, e2);
        }
    }

    protected ClassLoader getClassLoader() {
        return getClass().getClassLoader();
    }
}
