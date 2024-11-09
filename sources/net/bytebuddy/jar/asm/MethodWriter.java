package net.bytebuddy.jar.asm;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.jar.asm.Attribute;
import org.lwjgl.system.windows.User32;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:net/bytebuddy/jar/asm/MethodWriter.class */
public final class MethodWriter extends MethodVisitor {
    private static final int NA = 0;
    private static final int[] STACK_SIZE_DELTA = {0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 2, 2, 1, 1, 1, 0, 0, 1, 2, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, -1, 0, -1, -1, -1, -1, -1, -2, -1, -2, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -3, -4, -3, -4, -3, -3, -3, -3, -1, -2, 1, 1, 1, 2, 2, 2, 0, -1, -2, -1, -2, -1, -2, -1, -2, -1, -2, -1, -2, -1, -2, -1, -2, -1, -2, -1, -2, 0, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -2, -1, -2, -1, -2, 0, 1, 0, 1, -1, -1, 0, 0, 1, 1, -1, 0, -1, 0, 0, 0, -3, -1, -1, -3, -3, -1, -1, -1, -1, -1, -1, -2, -2, -2, -2, -2, -2, -2, -2, 0, 1, 0, -1, -1, -1, -2, -1, -2, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, -1, -1, 0, 0, -1, -1, 0, 0};
    private final SymbolTable symbolTable;
    private final int accessFlags;
    private final int nameIndex;
    private final String name;
    private final int descriptorIndex;
    private final String descriptor;
    private int maxStack;
    private int maxLocals;
    private final ByteVector code;
    private Handler firstHandler;
    private Handler lastHandler;
    private int lineNumberTableLength;
    private ByteVector lineNumberTable;
    private int localVariableTableLength;
    private ByteVector localVariableTable;
    private int localVariableTypeTableLength;
    private ByteVector localVariableTypeTable;
    private int stackMapTableNumberOfEntries;
    private ByteVector stackMapTableEntries;
    private AnnotationWriter lastCodeRuntimeVisibleTypeAnnotation;
    private AnnotationWriter lastCodeRuntimeInvisibleTypeAnnotation;
    private Attribute firstCodeAttribute;
    private final int numberOfExceptions;
    private final int[] exceptionIndexTable;
    private final int signatureIndex;
    private AnnotationWriter lastRuntimeVisibleAnnotation;
    private AnnotationWriter lastRuntimeInvisibleAnnotation;
    private int visibleAnnotableParameterCount;
    private AnnotationWriter[] lastRuntimeVisibleParameterAnnotations;
    private int invisibleAnnotableParameterCount;
    private AnnotationWriter[] lastRuntimeInvisibleParameterAnnotations;
    private AnnotationWriter lastRuntimeVisibleTypeAnnotation;
    private AnnotationWriter lastRuntimeInvisibleTypeAnnotation;
    private ByteVector defaultValue;
    private int parametersCount;
    private ByteVector parameters;
    private Attribute firstAttribute;
    private final int compute;
    private Label firstBasicBlock;
    private Label lastBasicBlock;
    private Label currentBasicBlock;
    private int relativeStackSize;
    private int maxRelativeStackSize;
    private int currentLocals;
    private int previousFrameOffset;
    private int[] previousFrame;
    private int[] currentFrame;
    private boolean hasSubroutines;
    private boolean hasAsmInstructions;
    private int lastBytecodeOffset;
    private int sourceOffset;
    private int sourceLength;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MethodWriter(SymbolTable symbolTable, int i, String str, String str2, String str3, String[] strArr, int i2) {
        super(589824);
        this.code = new ByteVector();
        this.symbolTable = symbolTable;
        this.accessFlags = MethodDescription.CONSTRUCTOR_INTERNAL_NAME.equals(str) ? i | 262144 : i;
        this.nameIndex = symbolTable.b(str);
        this.name = str;
        this.descriptorIndex = symbolTable.b(str2);
        this.descriptor = str2;
        this.signatureIndex = str3 == null ? 0 : symbolTable.b(str3);
        if (strArr != null && strArr.length > 0) {
            this.numberOfExceptions = strArr.length;
            this.exceptionIndexTable = new int[this.numberOfExceptions];
            for (int i3 = 0; i3 < this.numberOfExceptions; i3++) {
                this.exceptionIndexTable[i3] = symbolTable.a(strArr[i3]).f4146a;
            }
        } else {
            this.numberOfExceptions = 0;
            this.exceptionIndexTable = null;
        }
        this.compute = i2;
        if (i2 != 0) {
            int argumentsAndReturnSizes = Type.getArgumentsAndReturnSizes(str2) >> 2;
            argumentsAndReturnSizes = (i & 8) != 0 ? argumentsAndReturnSizes - 1 : argumentsAndReturnSizes;
            this.maxLocals = argumentsAndReturnSizes;
            this.currentLocals = argumentsAndReturnSizes;
            this.firstBasicBlock = new Label();
            visitLabel(this.firstBasicBlock);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean a() {
        return this.stackMapTableNumberOfEntries > 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean b() {
        return this.hasAsmInstructions;
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public final void visitParameter(String str, int i) {
        if (this.parameters == null) {
            this.parameters = new ByteVector();
        }
        this.parametersCount++;
        this.parameters.putShort(str == null ? 0 : this.symbolTable.b(str)).putShort(i);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public final AnnotationVisitor visitAnnotationDefault() {
        this.defaultValue = new ByteVector();
        return new AnnotationWriter(this.symbolTable, false, this.defaultValue, null);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
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

    @Override // net.bytebuddy.jar.asm.MethodVisitor
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

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public final void visitAnnotableParameterCount(int i, boolean z) {
        if (z) {
            this.visibleAnnotableParameterCount = i;
        } else {
            this.invisibleAnnotableParameterCount = i;
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public final AnnotationVisitor visitParameterAnnotation(int i, String str, boolean z) {
        if (z) {
            if (this.lastRuntimeVisibleParameterAnnotations == null) {
                this.lastRuntimeVisibleParameterAnnotations = new AnnotationWriter[Type.getArgumentTypes(this.descriptor).length];
            }
            AnnotationWriter[] annotationWriterArr = this.lastRuntimeVisibleParameterAnnotations;
            AnnotationWriter a2 = AnnotationWriter.a(this.symbolTable, str, this.lastRuntimeVisibleParameterAnnotations[i]);
            annotationWriterArr[i] = a2;
            return a2;
        }
        if (this.lastRuntimeInvisibleParameterAnnotations == null) {
            this.lastRuntimeInvisibleParameterAnnotations = new AnnotationWriter[Type.getArgumentTypes(this.descriptor).length];
        }
        AnnotationWriter[] annotationWriterArr2 = this.lastRuntimeInvisibleParameterAnnotations;
        AnnotationWriter a3 = AnnotationWriter.a(this.symbolTable, str, this.lastRuntimeInvisibleParameterAnnotations[i]);
        annotationWriterArr2[i] = a3;
        return a3;
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public final void visitAttribute(Attribute attribute) {
        if (attribute.isCodeAttribute()) {
            attribute.f4132a = this.firstCodeAttribute;
            this.firstCodeAttribute = attribute;
        } else {
            attribute.f4132a = this.firstAttribute;
            this.firstAttribute = attribute;
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public final void visitCode() {
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public final void visitFrame(int i, int i2, Object[] objArr, int i3, Object[] objArr2) {
        int i4;
        if (this.compute == 4) {
            return;
        }
        if (this.compute == 3) {
            if (this.currentBasicBlock.h == null) {
                this.currentBasicBlock.h = new CurrentFrame(this.currentBasicBlock);
                this.currentBasicBlock.h.a(this.symbolTable, this.accessFlags, this.descriptor, i2);
                this.currentBasicBlock.h.a(this);
            } else {
                if (i == -1) {
                    this.currentBasicBlock.h.a(this.symbolTable, i2, objArr, i3, objArr2);
                }
                this.currentBasicBlock.h.a(this);
            }
        } else if (i == -1) {
            if (this.previousFrame == null) {
                int argumentsAndReturnSizes = Type.getArgumentsAndReturnSizes(this.descriptor) >> 2;
                Frame frame = new Frame(new Label());
                frame.a(this.symbolTable, this.accessFlags, this.descriptor, argumentsAndReturnSizes);
                frame.a(this);
            }
            this.currentLocals = i2;
            int a2 = a(this.code.f4134b, i2, i3);
            for (int i5 = 0; i5 < i2; i5++) {
                int i6 = a2;
                a2++;
                this.currentFrame[i6] = Frame.a(this.symbolTable, objArr[i5]);
            }
            for (int i7 = 0; i7 < i3; i7++) {
                int i8 = a2;
                a2++;
                this.currentFrame[i8] = Frame.a(this.symbolTable, objArr2[i7]);
            }
            c();
        } else {
            if (this.symbolTable.b() < 50) {
                throw new IllegalArgumentException("Class versions V1_5 or less must use F_NEW frames.");
            }
            if (this.stackMapTableEntries == null) {
                this.stackMapTableEntries = new ByteVector();
                i4 = this.code.f4134b;
            } else {
                int i9 = (this.code.f4134b - this.previousFrameOffset) - 1;
                i4 = i9;
                if (i9 < 0) {
                    if (i == 3) {
                        return;
                    } else {
                        throw new IllegalStateException();
                    }
                }
            }
            switch (i) {
                case 0:
                    this.currentLocals = i2;
                    this.stackMapTableEntries.putByte(255).putShort(i4).putShort(i2);
                    for (int i10 = 0; i10 < i2; i10++) {
                        putFrameType(objArr[i10]);
                    }
                    this.stackMapTableEntries.putShort(i3);
                    for (int i11 = 0; i11 < i3; i11++) {
                        putFrameType(objArr2[i11]);
                    }
                    break;
                case 1:
                    this.currentLocals += i2;
                    this.stackMapTableEntries.putByte(i2 + User32.VK_ZOOM).putShort(i4);
                    for (int i12 = 0; i12 < i2; i12++) {
                        putFrameType(objArr[i12]);
                    }
                    break;
                case 2:
                    this.currentLocals -= i2;
                    this.stackMapTableEntries.putByte(User32.VK_ZOOM - i2).putShort(i4);
                    break;
                case 3:
                    if (i4 < 64) {
                        this.stackMapTableEntries.putByte(i4);
                        break;
                    } else {
                        this.stackMapTableEntries.putByte(User32.VK_ZOOM).putShort(i4);
                        break;
                    }
                case 4:
                    if (i4 < 64) {
                        this.stackMapTableEntries.putByte(i4 + 64);
                    } else {
                        this.stackMapTableEntries.putByte(User32.VK_CRSEL).putShort(i4);
                    }
                    putFrameType(objArr2[0]);
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            this.previousFrameOffset = this.code.f4134b;
            this.stackMapTableNumberOfEntries++;
        }
        if (this.compute == 2) {
            this.relativeStackSize = i3;
            for (int i13 = 0; i13 < i3; i13++) {
                if (objArr2[i13] == Opcodes.LONG || objArr2[i13] == Opcodes.DOUBLE) {
                    this.relativeStackSize++;
                }
            }
            if (this.relativeStackSize > this.maxRelativeStackSize) {
                this.maxRelativeStackSize = this.relativeStackSize;
            }
        }
        this.maxStack = Math.max(this.maxStack, i3);
        this.maxLocals = Math.max(this.maxLocals, this.currentLocals);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public final void visitInsn(int i) {
        this.lastBytecodeOffset = this.code.f4134b;
        this.code.putByte(i);
        if (this.currentBasicBlock != null) {
            if (this.compute == 4 || this.compute == 3) {
                this.currentBasicBlock.h.a(i, 0, (Symbol) null, (SymbolTable) null);
            } else {
                int i2 = this.relativeStackSize + STACK_SIZE_DELTA[i];
                if (i2 > this.maxRelativeStackSize) {
                    this.maxRelativeStackSize = i2;
                }
                this.relativeStackSize = i2;
            }
            if ((i >= 172 && i <= 177) || i == 191) {
                endCurrentBasicBlockWithNoSuccessor();
            }
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public final void visitIntInsn(int i, int i2) {
        this.lastBytecodeOffset = this.code.f4134b;
        if (i == 17) {
            this.code.b(i, i2);
        } else {
            this.code.a(i, i2);
        }
        if (this.currentBasicBlock != null) {
            if (this.compute == 4 || this.compute == 3) {
                this.currentBasicBlock.h.a(i, i2, (Symbol) null, (SymbolTable) null);
            } else if (i != 188) {
                int i3 = this.relativeStackSize + 1;
                if (i3 > this.maxRelativeStackSize) {
                    this.maxRelativeStackSize = i3;
                }
                this.relativeStackSize = i3;
            }
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public final void visitVarInsn(int i, int i2) {
        int i3;
        int i4;
        this.lastBytecodeOffset = this.code.f4134b;
        if (i2 < 4 && i != 169) {
            if (i < 54) {
                i4 = 26 + ((i - 21) << 2) + i2;
            } else {
                i4 = 59 + ((i - 54) << 2) + i2;
            }
            this.code.putByte(i4);
        } else if (i2 >= 256) {
            this.code.putByte(196).b(i, i2);
        } else {
            this.code.a(i, i2);
        }
        if (this.currentBasicBlock != null) {
            if (this.compute == 4 || this.compute == 3) {
                this.currentBasicBlock.h.a(i, i2, (Symbol) null, (SymbolTable) null);
            } else if (i == 169) {
                Label label = this.currentBasicBlock;
                label.f4145b = (short) (label.f4145b | 64);
                this.currentBasicBlock.e = (short) this.relativeStackSize;
                endCurrentBasicBlockWithNoSuccessor();
            } else {
                int i5 = this.relativeStackSize + STACK_SIZE_DELTA[i];
                if (i5 > this.maxRelativeStackSize) {
                    this.maxRelativeStackSize = i5;
                }
                this.relativeStackSize = i5;
            }
        }
        if (this.compute != 0) {
            if (i == 22 || i == 24 || i == 55 || i == 57) {
                i3 = i2 + 2;
            } else {
                i3 = i2 + 1;
            }
            if (i3 > this.maxLocals) {
                this.maxLocals = i3;
            }
        }
        if (i >= 54 && this.compute == 4 && this.firstHandler != null) {
            visitLabel(new Label());
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public final void visitTypeInsn(int i, String str) {
        this.lastBytecodeOffset = this.code.f4134b;
        Symbol a2 = this.symbolTable.a(str);
        this.code.b(i, a2.f4146a);
        if (this.currentBasicBlock != null) {
            if (this.compute == 4 || this.compute == 3) {
                this.currentBasicBlock.h.a(i, this.lastBytecodeOffset, a2, this.symbolTable);
            } else if (i == 187) {
                int i2 = this.relativeStackSize + 1;
                if (i2 > this.maxRelativeStackSize) {
                    this.maxRelativeStackSize = i2;
                }
                this.relativeStackSize = i2;
            }
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public final void visitFieldInsn(int i, String str, String str2, String str3) {
        int i2;
        this.lastBytecodeOffset = this.code.f4134b;
        Symbol a2 = this.symbolTable.a(str, str2, str3);
        this.code.b(i, a2.f4146a);
        if (this.currentBasicBlock != null) {
            if (this.compute == 4 || this.compute == 3) {
                this.currentBasicBlock.h.a(i, 0, a2, this.symbolTable);
                return;
            }
            char charAt = str3.charAt(0);
            switch (i) {
                case 178:
                    i2 = this.relativeStackSize + ((charAt == 'D' || charAt == 'J') ? 2 : 1);
                    break;
                case 179:
                    i2 = this.relativeStackSize + ((charAt == 'D' || charAt == 'J') ? -2 : -1);
                    break;
                case 180:
                    i2 = this.relativeStackSize + ((charAt == 'D' || charAt == 'J') ? 1 : 0);
                    break;
                default:
                    i2 = this.relativeStackSize + ((charAt == 'D' || charAt == 'J') ? -3 : -2);
                    break;
            }
            if (i2 > this.maxRelativeStackSize) {
                this.maxRelativeStackSize = i2;
            }
            this.relativeStackSize = i2;
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public final void visitMethodInsn(int i, String str, String str2, String str3, boolean z) {
        int i2;
        this.lastBytecodeOffset = this.code.f4134b;
        Symbol a2 = this.symbolTable.a(str, str2, str3, z);
        if (i == 185) {
            this.code.b(185, a2.f4146a).a(a2.a() >> 2, 0);
        } else {
            this.code.b(i, a2.f4146a);
        }
        if (this.currentBasicBlock != null) {
            if (this.compute == 4 || this.compute == 3) {
                this.currentBasicBlock.h.a(i, 0, a2, this.symbolTable);
                return;
            }
            int a3 = a2.a();
            int i3 = (a3 & 3) - (a3 >> 2);
            if (i == 184) {
                i2 = this.relativeStackSize + i3 + 1;
            } else {
                i2 = this.relativeStackSize + i3;
            }
            if (i2 > this.maxRelativeStackSize) {
                this.maxRelativeStackSize = i2;
            }
            this.relativeStackSize = i2;
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public final void visitInvokeDynamicInsn(String str, String str2, Handle handle, Object... objArr) {
        this.lastBytecodeOffset = this.code.f4134b;
        Symbol b2 = this.symbolTable.b(str, str2, handle, objArr);
        this.code.b(186, b2.f4146a);
        this.code.putShort(0);
        if (this.currentBasicBlock != null) {
            if (this.compute == 4 || this.compute == 3) {
                this.currentBasicBlock.h.a(186, 0, b2, this.symbolTable);
                return;
            }
            int a2 = b2.a();
            int i = this.relativeStackSize + ((a2 & 3) - (a2 >> 2)) + 1;
            if (i > this.maxRelativeStackSize) {
                this.maxRelativeStackSize = i;
            }
            this.relativeStackSize = i;
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public final void visitJumpInsn(int i, Label label) {
        this.lastBytecodeOffset = this.code.f4134b;
        int i2 = i >= 200 ? i - 33 : i;
        boolean z = false;
        if ((label.f4145b & 4) != 0 && label.c - this.code.f4134b < -32768) {
            if (i2 == 167) {
                this.code.putByte(200);
            } else if (i2 == 168) {
                this.code.putByte(201);
            } else {
                this.code.putByte(i2 >= 198 ? i2 ^ 1 : ((i2 + 1) ^ 1) - 1);
                this.code.putShort(8);
                this.code.putByte(User32.VK_OEM_5);
                this.hasAsmInstructions = true;
                z = true;
            }
            label.a(this.code, this.code.f4134b - 1, true);
        } else if (i2 != i) {
            this.code.putByte(i);
            label.a(this.code, this.code.f4134b - 1, true);
        } else {
            this.code.putByte(i2);
            label.a(this.code, this.code.f4134b - 1, false);
        }
        if (this.currentBasicBlock != null) {
            Label label2 = null;
            if (this.compute == 4) {
                this.currentBasicBlock.h.a(i2, 0, (Symbol) null, (SymbolTable) null);
                Label a2 = label.a();
                a2.f4145b = (short) (a2.f4145b | 2);
                addSuccessorToCurrentBasicBlock(0, label);
                if (i2 != 167) {
                    label2 = new Label();
                }
            } else if (this.compute == 3) {
                this.currentBasicBlock.h.a(i2, 0, (Symbol) null, (SymbolTable) null);
            } else if (this.compute == 2) {
                this.relativeStackSize += STACK_SIZE_DELTA[i2];
            } else if (i2 == 168) {
                if ((label.f4145b & 32) == 0) {
                    label.f4145b = (short) (label.f4145b | 32);
                    this.hasSubroutines = true;
                }
                Label label3 = this.currentBasicBlock;
                label3.f4145b = (short) (label3.f4145b | 16);
                addSuccessorToCurrentBasicBlock(this.relativeStackSize + 1, label);
                label2 = new Label();
            } else {
                this.relativeStackSize += STACK_SIZE_DELTA[i2];
                addSuccessorToCurrentBasicBlock(this.relativeStackSize, label);
            }
            if (label2 != null) {
                if (z) {
                    Label label4 = label2;
                    label4.f4145b = (short) (label4.f4145b | 2);
                }
                visitLabel(label2);
            }
            if (i2 == 167) {
                endCurrentBasicBlockWithNoSuccessor();
            }
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public final void visitLabel(Label label) {
        this.hasAsmInstructions |= label.a(this.code.f4133a, this.code.f4134b);
        if ((label.f4145b & 1) != 0) {
            return;
        }
        if (this.compute == 4) {
            if (this.currentBasicBlock != null) {
                if (label.c == this.currentBasicBlock.c) {
                    Label label2 = this.currentBasicBlock;
                    label2.f4145b = (short) (label2.f4145b | (label.f4145b & 2));
                    label.h = this.currentBasicBlock.h;
                    return;
                }
                addSuccessorToCurrentBasicBlock(0, label);
            }
            if (this.lastBasicBlock != null) {
                if (label.c == this.lastBasicBlock.c) {
                    Label label3 = this.lastBasicBlock;
                    label3.f4145b = (short) (label3.f4145b | (label.f4145b & 2));
                    label.h = this.lastBasicBlock.h;
                    this.currentBasicBlock = this.lastBasicBlock;
                    return;
                }
                this.lastBasicBlock.i = label;
            }
            this.lastBasicBlock = label;
            this.currentBasicBlock = label;
            label.h = new Frame(label);
            return;
        }
        if (this.compute == 3) {
            if (this.currentBasicBlock == null) {
                this.currentBasicBlock = label;
                return;
            } else {
                this.currentBasicBlock.h.f4141a = label;
                return;
            }
        }
        if (this.compute == 1) {
            if (this.currentBasicBlock != null) {
                this.currentBasicBlock.f = (short) this.maxRelativeStackSize;
                addSuccessorToCurrentBasicBlock(this.relativeStackSize, label);
            }
            this.currentBasicBlock = label;
            this.relativeStackSize = 0;
            this.maxRelativeStackSize = 0;
            if (this.lastBasicBlock != null) {
                this.lastBasicBlock.i = label;
            }
            this.lastBasicBlock = label;
            return;
        }
        if (this.compute == 2 && this.currentBasicBlock == null) {
            this.currentBasicBlock = label;
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public final void visitLdcInsn(Object obj) {
        char charAt;
        this.lastBytecodeOffset = this.code.f4134b;
        Symbol a2 = this.symbolTable.a(obj);
        int i = a2.f4146a;
        boolean z = a2.f4147b == 5 || a2.f4147b == 6 || (a2.f4147b == 17 && ((charAt = a2.e.charAt(0)) == 'J' || charAt == 'D'));
        boolean z2 = z;
        if (z) {
            this.code.b(20, i);
        } else if (i >= 256) {
            this.code.b(19, i);
        } else {
            this.code.a(18, i);
        }
        if (this.currentBasicBlock != null) {
            if (this.compute == 4 || this.compute == 3) {
                this.currentBasicBlock.h.a(18, 0, a2, this.symbolTable);
                return;
            }
            int i2 = this.relativeStackSize + (z2 ? 2 : 1);
            if (i2 > this.maxRelativeStackSize) {
                this.maxRelativeStackSize = i2;
            }
            this.relativeStackSize = i2;
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public final void visitIincInsn(int i, int i2) {
        int i3;
        this.lastBytecodeOffset = this.code.f4134b;
        if (i > 255 || i2 > 127 || i2 < -128) {
            this.code.putByte(196).b(132, i).putShort(i2);
        } else {
            this.code.putByte(132).a(i, i2);
        }
        if (this.currentBasicBlock != null && (this.compute == 4 || this.compute == 3)) {
            this.currentBasicBlock.h.a(132, i, (Symbol) null, (SymbolTable) null);
        }
        if (this.compute != 0 && (i3 = i + 1) > this.maxLocals) {
            this.maxLocals = i3;
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public final void visitTableSwitchInsn(int i, int i2, Label label, Label... labelArr) {
        this.lastBytecodeOffset = this.code.f4134b;
        this.code.putByte(170).putByteArray(null, 0, (4 - (this.code.f4134b % 4)) % 4);
        label.a(this.code, this.lastBytecodeOffset, true);
        this.code.putInt(i).putInt(i2);
        for (Label label2 : labelArr) {
            label2.a(this.code, this.lastBytecodeOffset, true);
        }
        visitSwitchInsn(label, labelArr);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public final void visitLookupSwitchInsn(Label label, int[] iArr, Label[] labelArr) {
        this.lastBytecodeOffset = this.code.f4134b;
        this.code.putByte(171).putByteArray(null, 0, (4 - (this.code.f4134b % 4)) % 4);
        label.a(this.code, this.lastBytecodeOffset, true);
        this.code.putInt(labelArr.length);
        for (int i = 0; i < labelArr.length; i++) {
            this.code.putInt(iArr[i]);
            labelArr[i].a(this.code, this.lastBytecodeOffset, true);
        }
        visitSwitchInsn(label, labelArr);
    }

    private void visitSwitchInsn(Label label, Label[] labelArr) {
        if (this.currentBasicBlock != null) {
            if (this.compute == 4) {
                this.currentBasicBlock.h.a(171, 0, (Symbol) null, (SymbolTable) null);
                addSuccessorToCurrentBasicBlock(0, label);
                Label a2 = label.a();
                a2.f4145b = (short) (a2.f4145b | 2);
                for (Label label2 : labelArr) {
                    addSuccessorToCurrentBasicBlock(0, label2);
                    Label a3 = label2.a();
                    a3.f4145b = (short) (a3.f4145b | 2);
                }
            } else if (this.compute == 1) {
                this.relativeStackSize--;
                addSuccessorToCurrentBasicBlock(this.relativeStackSize, label);
                for (Label label3 : labelArr) {
                    addSuccessorToCurrentBasicBlock(this.relativeStackSize, label3);
                }
            }
            endCurrentBasicBlockWithNoSuccessor();
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public final void visitMultiANewArrayInsn(String str, int i) {
        this.lastBytecodeOffset = this.code.f4134b;
        Symbol a2 = this.symbolTable.a(str);
        this.code.b(197, a2.f4146a).putByte(i);
        if (this.currentBasicBlock != null) {
            if (this.compute == 4 || this.compute == 3) {
                this.currentBasicBlock.h.a(197, i, a2, this.symbolTable);
            } else {
                this.relativeStackSize += 1 - i;
            }
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public final AnnotationVisitor visitInsnAnnotation(int i, TypePath typePath, String str, boolean z) {
        if (z) {
            AnnotationWriter a2 = AnnotationWriter.a(this.symbolTable, (i & (-16776961)) | (this.lastBytecodeOffset << 8), typePath, str, this.lastCodeRuntimeVisibleTypeAnnotation);
            this.lastCodeRuntimeVisibleTypeAnnotation = a2;
            return a2;
        }
        AnnotationWriter a3 = AnnotationWriter.a(this.symbolTable, (i & (-16776961)) | (this.lastBytecodeOffset << 8), typePath, str, this.lastCodeRuntimeInvisibleTypeAnnotation);
        this.lastCodeRuntimeInvisibleTypeAnnotation = a3;
        return a3;
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public final void visitTryCatchBlock(Label label, Label label2, Label label3, String str) {
        Handler handler = new Handler(label, label2, label3, str != null ? this.symbolTable.a(str).f4146a : 0, str);
        if (this.firstHandler == null) {
            this.firstHandler = handler;
        } else {
            this.lastHandler.e = handler;
        }
        this.lastHandler = handler;
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public final AnnotationVisitor visitTryCatchAnnotation(int i, TypePath typePath, String str, boolean z) {
        if (z) {
            AnnotationWriter a2 = AnnotationWriter.a(this.symbolTable, i, typePath, str, this.lastCodeRuntimeVisibleTypeAnnotation);
            this.lastCodeRuntimeVisibleTypeAnnotation = a2;
            return a2;
        }
        AnnotationWriter a3 = AnnotationWriter.a(this.symbolTable, i, typePath, str, this.lastCodeRuntimeInvisibleTypeAnnotation);
        this.lastCodeRuntimeInvisibleTypeAnnotation = a3;
        return a3;
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public final void visitLocalVariable(String str, String str2, String str3, Label label, Label label2, int i) {
        if (str3 != null) {
            if (this.localVariableTypeTable == null) {
                this.localVariableTypeTable = new ByteVector();
            }
            this.localVariableTypeTableLength++;
            this.localVariableTypeTable.putShort(label.c).putShort(label2.c - label.c).putShort(this.symbolTable.b(str)).putShort(this.symbolTable.b(str3)).putShort(i);
        }
        if (this.localVariableTable == null) {
            this.localVariableTable = new ByteVector();
        }
        this.localVariableTableLength++;
        this.localVariableTable.putShort(label.c).putShort(label2.c - label.c).putShort(this.symbolTable.b(str)).putShort(this.symbolTable.b(str2)).putShort(i);
        if (this.compute != 0) {
            char charAt = str2.charAt(0);
            int i2 = i + ((charAt == 'J' || charAt == 'D') ? 2 : 1);
            if (i2 > this.maxLocals) {
                this.maxLocals = i2;
            }
        }
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public final AnnotationVisitor visitLocalVariableAnnotation(int i, TypePath typePath, Label[] labelArr, Label[] labelArr2, int[] iArr, String str, boolean z) {
        ByteVector byteVector = new ByteVector();
        byteVector.putByte(i >>> 24).putShort(labelArr.length);
        for (int i2 = 0; i2 < labelArr.length; i2++) {
            byteVector.putShort(labelArr[i2].c).putShort(labelArr2[i2].c - labelArr[i2].c).putShort(iArr[i2]);
        }
        TypePath.a(typePath, byteVector);
        byteVector.putShort(this.symbolTable.b(str)).putShort(0);
        if (z) {
            AnnotationWriter annotationWriter = new AnnotationWriter(this.symbolTable, true, byteVector, this.lastCodeRuntimeVisibleTypeAnnotation);
            this.lastCodeRuntimeVisibleTypeAnnotation = annotationWriter;
            return annotationWriter;
        }
        AnnotationWriter annotationWriter2 = new AnnotationWriter(this.symbolTable, true, byteVector, this.lastCodeRuntimeInvisibleTypeAnnotation);
        this.lastCodeRuntimeInvisibleTypeAnnotation = annotationWriter2;
        return annotationWriter2;
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public final void visitLineNumber(int i, Label label) {
        if (this.lineNumberTable == null) {
            this.lineNumberTable = new ByteVector();
        }
        this.lineNumberTableLength++;
        this.lineNumberTable.putShort(label.c);
        this.lineNumberTable.putShort(i);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public final void visitMaxs(int i, int i2) {
        if (this.compute == 4) {
            computeAllFrames();
            return;
        }
        if (this.compute == 1) {
            computeMaxStackAndLocal();
        } else if (this.compute == 2) {
            this.maxStack = this.maxRelativeStackSize;
        } else {
            this.maxStack = i;
            this.maxLocals = i2;
        }
    }

    private void computeAllFrames() {
        Handler handler = this.firstHandler;
        while (true) {
            Handler handler2 = handler;
            if (handler2 == null) {
                break;
            }
            int a2 = Frame.a(this.symbolTable, handler2.d == null ? "java/lang/Throwable" : handler2.d);
            Label a3 = handler2.c.a();
            a3.f4145b = (short) (a3.f4145b | 2);
            Label a4 = handler2.f4143b.a();
            for (Label a5 = handler2.f4142a.a(); a5 != a4; a5 = a5.i) {
                a5.j = new Edge(a2, a3, a5.j);
            }
            handler = handler2.e;
        }
        Frame frame = this.firstBasicBlock.h;
        frame.a(this.symbolTable, this.accessFlags, this.descriptor, this.maxLocals);
        frame.a(this);
        Label label = this.firstBasicBlock;
        Label label2 = label;
        label.k = Label.f4144a;
        int i = 0;
        while (label2 != Label.f4144a) {
            Label label3 = label2;
            label2 = label2.k;
            label3.k = null;
            label3.f4145b = (short) (label3.f4145b | 8);
            int a6 = label3.h.a() + label3.f;
            if (a6 > i) {
                i = a6;
            }
            Edge edge = label3.j;
            while (true) {
                Edge edge2 = edge;
                if (edge2 != null) {
                    Label a7 = edge2.f4140b.a();
                    if (label3.h.a(this.symbolTable, a7.h, edge2.f4139a) && a7.k == null) {
                        a7.k = label2;
                        label2 = a7;
                    }
                    edge = edge2.c;
                }
            }
        }
        Label label4 = this.firstBasicBlock;
        while (true) {
            Label label5 = label4;
            if (label5 != null) {
                if ((label5.f4145b & 10) == 10) {
                    label5.h.a(this);
                }
                if ((label5.f4145b & 8) == 0) {
                    Label label6 = label5.i;
                    int i2 = label5.c;
                    int i3 = (label6 == null ? this.code.f4134b : label6.c) - 1;
                    if (i3 >= i2) {
                        for (int i4 = i2; i4 < i3; i4++) {
                            this.code.f4133a[i4] = 0;
                        }
                        this.code.f4133a[i3] = -65;
                        a(i2, 0, 1);
                        this.currentFrame[3] = Frame.a(this.symbolTable, "java/lang/Throwable");
                        c();
                        this.firstHandler = Handler.a(this.firstHandler, label5, label6);
                        i = Math.max(i, 1);
                    }
                }
                label4 = label5.i;
            } else {
                this.maxStack = i;
                return;
            }
        }
    }

    private void computeMaxStackAndLocal() {
        Handler handler = this.firstHandler;
        while (true) {
            Handler handler2 = handler;
            if (handler2 == null) {
                break;
            }
            Label label = handler2.c;
            Label label2 = handler2.f4143b;
            for (Label label3 = handler2.f4142a; label3 != label2; label3 = label3.i) {
                if ((label3.f4145b & 16) == 0) {
                    label3.j = new Edge(Integer.MAX_VALUE, label, label3.j);
                } else {
                    label3.j.c.c = new Edge(Integer.MAX_VALUE, label, label3.j.c.c);
                }
            }
            handler = handler2.e;
        }
        if (this.hasSubroutines) {
            short s = 1;
            this.firstBasicBlock.a((short) 1);
            short s2 = 1;
            while (true) {
                short s3 = s2;
                if (s3 > s) {
                    break;
                }
                Label label4 = this.firstBasicBlock;
                while (true) {
                    Label label5 = label4;
                    if (label5 != null) {
                        if ((label5.f4145b & 16) != 0 && label5.g == s3) {
                            Label label6 = label5.j.c.f4140b;
                            if (label6.g == 0) {
                                short s4 = (short) (s + 1);
                                s = s4;
                                label6.a(s4);
                            }
                        }
                        label4 = label5.i;
                    }
                }
                s2 = (short) (s3 + 1);
            }
            Label label7 = this.firstBasicBlock;
            while (true) {
                Label label8 = label7;
                if (label8 == null) {
                    break;
                }
                if ((label8.f4145b & 16) != 0) {
                    label8.j.c.f4140b.a(label8);
                }
                label7 = label8.i;
            }
        }
        Label label9 = this.firstBasicBlock;
        Label label10 = label9;
        label9.k = Label.f4144a;
        int i = this.maxStack;
        while (label10 != Label.f4144a) {
            Label label11 = label10;
            label10 = label10.k;
            short s5 = label11.d;
            int i2 = s5 + label11.f;
            if (i2 > i) {
                i = i2;
            }
            Edge edge = label11.j;
            if ((label11.f4145b & 16) != 0) {
                edge = edge.c;
            }
            while (edge != null) {
                Label label12 = edge.f4140b;
                if (label12.k == null) {
                    label12.d = (short) (edge.f4139a == Integer.MAX_VALUE ? 1 : s5 + edge.f4139a);
                    label12.k = label10;
                    label10 = label12;
                }
                edge = edge.c;
            }
        }
        this.maxStack = i;
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public final void visitEnd() {
    }

    private void addSuccessorToCurrentBasicBlock(int i, Label label) {
        this.currentBasicBlock.j = new Edge(i, label, this.currentBasicBlock.j);
    }

    private void endCurrentBasicBlockWithNoSuccessor() {
        if (this.compute != 4) {
            if (this.compute == 1) {
                this.currentBasicBlock.f = (short) this.maxRelativeStackSize;
                this.currentBasicBlock = null;
                return;
            }
            return;
        }
        Label label = new Label();
        label.h = new Frame(label);
        label.a(this.code.f4133a, this.code.f4134b);
        this.lastBasicBlock.i = label;
        this.lastBasicBlock = label;
        this.currentBasicBlock = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a(int i, int i2, int i3) {
        int i4 = i2 + 3 + i3;
        if (this.currentFrame == null || this.currentFrame.length < i4) {
            this.currentFrame = new int[i4];
        }
        this.currentFrame[0] = i;
        this.currentFrame[1] = i2;
        this.currentFrame[2] = i3;
        return 3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(int i, int i2) {
        this.currentFrame[i] = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void c() {
        if (this.previousFrame != null) {
            if (this.stackMapTableEntries == null) {
                this.stackMapTableEntries = new ByteVector();
            }
            putFrame();
            this.stackMapTableNumberOfEntries++;
        }
        this.previousFrame = this.currentFrame;
        this.currentFrame = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0181  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x01dc  */
    /* JADX WARN: Type inference failed for: r0v27 */
    /* JADX WARN: Type inference failed for: r0v28 */
    /* JADX WARN: Type inference failed for: r0v29 */
    /* JADX WARN: Type inference failed for: r0v74 */
    /* JADX WARN: Type inference failed for: r0v76 */
    /* JADX WARN: Type inference failed for: r0v78 */
    /* JADX WARN: Type inference failed for: r0v79 */
    /* JADX WARN: Type inference failed for: r0v80 */
    /* JADX WARN: Type inference failed for: r0v81 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void putFrame() {
        /*
            Method dump skipped, instructions count: 525
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.jar.asm.MethodWriter.putFrame():void");
    }

    private void putAbstractTypes(int i, int i2) {
        for (int i3 = i; i3 < i2; i3++) {
            Frame.a(this.symbolTable, this.currentFrame[i3], this.stackMapTableEntries);
        }
    }

    private void putFrameType(Object obj) {
        if (obj instanceof Integer) {
            this.stackMapTableEntries.putByte(((Integer) obj).intValue());
        } else if (obj instanceof String) {
            this.stackMapTableEntries.putByte(7).putShort(this.symbolTable.a((String) obj).f4146a);
        } else {
            this.stackMapTableEntries.putByte(8).putShort(((Label) obj).c);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean a(ClassReader classReader, boolean z, boolean z2, int i, int i2, int i3) {
        if (classReader != this.symbolTable.a() || i != this.descriptorIndex || i2 != this.signatureIndex) {
            return false;
        }
        if (z2 != ((this.accessFlags & 131072) != 0)) {
            return false;
        }
        if (z != (this.symbolTable.b() < 49 && (this.accessFlags & 4096) != 0)) {
            return false;
        }
        if (i3 == 0) {
            if (this.numberOfExceptions != 0) {
                return false;
            }
            return true;
        }
        if (classReader.readUnsignedShort(i3) == this.numberOfExceptions) {
            int i4 = i3 + 2;
            for (int i5 = 0; i5 < this.numberOfExceptions; i5++) {
                if (classReader.readUnsignedShort(i4) != this.exceptionIndexTable[i5]) {
                    return false;
                }
                i4 += 2;
            }
            return true;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void b(int i, int i2) {
        this.sourceOffset = i + 6;
        this.sourceLength = i2 - 6;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int d() {
        int i;
        int i2;
        if (this.sourceOffset != 0) {
            return 6 + this.sourceLength;
        }
        int i3 = 8;
        if (this.code.f4134b > 0) {
            if (this.code.f4134b > 65535) {
                throw new MethodTooLargeException(this.symbolTable.c(), this.name, this.descriptor, this.code.f4134b);
            }
            this.symbolTable.b("Code");
            i3 = 8 + 16 + this.code.f4134b + Handler.a(this.firstHandler);
            if (this.stackMapTableEntries != null) {
                this.symbolTable.b(this.symbolTable.b() >= 50 ? "StackMapTable" : "StackMap");
                i3 += 8 + this.stackMapTableEntries.f4134b;
            }
            if (this.lineNumberTable != null) {
                this.symbolTable.b("LineNumberTable");
                i3 += 8 + this.lineNumberTable.f4134b;
            }
            if (this.localVariableTable != null) {
                this.symbolTable.b("LocalVariableTable");
                i3 += 8 + this.localVariableTable.f4134b;
            }
            if (this.localVariableTypeTable != null) {
                this.symbolTable.b("LocalVariableTypeTable");
                i3 += 8 + this.localVariableTypeTable.f4134b;
            }
            if (this.lastCodeRuntimeVisibleTypeAnnotation != null) {
                i3 += this.lastCodeRuntimeVisibleTypeAnnotation.a("RuntimeVisibleTypeAnnotations");
            }
            if (this.lastCodeRuntimeInvisibleTypeAnnotation != null) {
                i3 += this.lastCodeRuntimeInvisibleTypeAnnotation.a("RuntimeInvisibleTypeAnnotations");
            }
            if (this.firstCodeAttribute != null) {
                i3 += this.firstCodeAttribute.a(this.symbolTable, this.code.f4133a, this.code.f4134b, this.maxStack, this.maxLocals);
            }
        }
        if (this.numberOfExceptions > 0) {
            this.symbolTable.b("Exceptions");
            i3 += 8 + (2 * this.numberOfExceptions);
        }
        int a2 = i3 + Attribute.a(this.symbolTable, this.accessFlags, this.signatureIndex) + AnnotationWriter.a(this.lastRuntimeVisibleAnnotation, this.lastRuntimeInvisibleAnnotation, this.lastRuntimeVisibleTypeAnnotation, this.lastRuntimeInvisibleTypeAnnotation);
        if (this.lastRuntimeVisibleParameterAnnotations != null) {
            AnnotationWriter[] annotationWriterArr = this.lastRuntimeVisibleParameterAnnotations;
            if (this.visibleAnnotableParameterCount == 0) {
                i2 = this.lastRuntimeVisibleParameterAnnotations.length;
            } else {
                i2 = this.visibleAnnotableParameterCount;
            }
            a2 += AnnotationWriter.a("RuntimeVisibleParameterAnnotations", annotationWriterArr, i2);
        }
        if (this.lastRuntimeInvisibleParameterAnnotations != null) {
            int i4 = a2;
            AnnotationWriter[] annotationWriterArr2 = this.lastRuntimeInvisibleParameterAnnotations;
            if (this.invisibleAnnotableParameterCount == 0) {
                i = this.lastRuntimeInvisibleParameterAnnotations.length;
            } else {
                i = this.invisibleAnnotableParameterCount;
            }
            a2 = i4 + AnnotationWriter.a("RuntimeInvisibleParameterAnnotations", annotationWriterArr2, i);
        }
        if (this.defaultValue != null) {
            this.symbolTable.b("AnnotationDefault");
            a2 += 6 + this.defaultValue.f4134b;
        }
        if (this.parameters != null) {
            this.symbolTable.b("MethodParameters");
            a2 += 7 + this.parameters.f4134b;
        }
        if (this.firstAttribute != null) {
            a2 += this.firstAttribute.a(this.symbolTable);
        }
        return a2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(ByteVector byteVector) {
        int i;
        int i2;
        boolean z = this.symbolTable.b() < 49;
        boolean z2 = z;
        byteVector.putShort(this.accessFlags & ((z ? 4096 : 0) ^ (-1))).putShort(this.nameIndex).putShort(this.descriptorIndex);
        if (this.sourceOffset != 0) {
            byteVector.putByteArray(this.symbolTable.a().f4136a, this.sourceOffset, this.sourceLength);
            return;
        }
        int i3 = 0;
        if (this.code.f4134b > 0) {
            i3 = 0 + 1;
        }
        if (this.numberOfExceptions > 0) {
            i3++;
        }
        if ((this.accessFlags & 4096) != 0 && z2) {
            i3++;
        }
        if (this.signatureIndex != 0) {
            i3++;
        }
        if ((this.accessFlags & 131072) != 0) {
            i3++;
        }
        if (this.lastRuntimeVisibleAnnotation != null) {
            i3++;
        }
        if (this.lastRuntimeInvisibleAnnotation != null) {
            i3++;
        }
        if (this.lastRuntimeVisibleParameterAnnotations != null) {
            i3++;
        }
        if (this.lastRuntimeInvisibleParameterAnnotations != null) {
            i3++;
        }
        if (this.lastRuntimeVisibleTypeAnnotation != null) {
            i3++;
        }
        if (this.lastRuntimeInvisibleTypeAnnotation != null) {
            i3++;
        }
        if (this.defaultValue != null) {
            i3++;
        }
        if (this.parameters != null) {
            i3++;
        }
        if (this.firstAttribute != null) {
            i3 += this.firstAttribute.a();
        }
        byteVector.putShort(i3);
        if (this.code.f4134b > 0) {
            int a2 = 10 + this.code.f4134b + Handler.a(this.firstHandler);
            int i4 = 0;
            if (this.stackMapTableEntries != null) {
                a2 += 8 + this.stackMapTableEntries.f4134b;
                i4 = 0 + 1;
            }
            if (this.lineNumberTable != null) {
                a2 += 8 + this.lineNumberTable.f4134b;
                i4++;
            }
            if (this.localVariableTable != null) {
                a2 += 8 + this.localVariableTable.f4134b;
                i4++;
            }
            if (this.localVariableTypeTable != null) {
                a2 += 8 + this.localVariableTypeTable.f4134b;
                i4++;
            }
            if (this.lastCodeRuntimeVisibleTypeAnnotation != null) {
                a2 += this.lastCodeRuntimeVisibleTypeAnnotation.a("RuntimeVisibleTypeAnnotations");
                i4++;
            }
            if (this.lastCodeRuntimeInvisibleTypeAnnotation != null) {
                a2 += this.lastCodeRuntimeInvisibleTypeAnnotation.a("RuntimeInvisibleTypeAnnotations");
                i4++;
            }
            if (this.firstCodeAttribute != null) {
                a2 += this.firstCodeAttribute.a(this.symbolTable, this.code.f4133a, this.code.f4134b, this.maxStack, this.maxLocals);
                i4 += this.firstCodeAttribute.a();
            }
            byteVector.putShort(this.symbolTable.b("Code")).putInt(a2).putShort(this.maxStack).putShort(this.maxLocals).putInt(this.code.f4134b).putByteArray(this.code.f4133a, 0, this.code.f4134b);
            Handler.a(this.firstHandler, byteVector);
            byteVector.putShort(i4);
            if (this.stackMapTableEntries != null) {
                byteVector.putShort(this.symbolTable.b(this.symbolTable.b() >= 50 ? "StackMapTable" : "StackMap")).putInt(2 + this.stackMapTableEntries.f4134b).putShort(this.stackMapTableNumberOfEntries).putByteArray(this.stackMapTableEntries.f4133a, 0, this.stackMapTableEntries.f4134b);
            }
            if (this.lineNumberTable != null) {
                byteVector.putShort(this.symbolTable.b("LineNumberTable")).putInt(2 + this.lineNumberTable.f4134b).putShort(this.lineNumberTableLength).putByteArray(this.lineNumberTable.f4133a, 0, this.lineNumberTable.f4134b);
            }
            if (this.localVariableTable != null) {
                byteVector.putShort(this.symbolTable.b("LocalVariableTable")).putInt(2 + this.localVariableTable.f4134b).putShort(this.localVariableTableLength).putByteArray(this.localVariableTable.f4133a, 0, this.localVariableTable.f4134b);
            }
            if (this.localVariableTypeTable != null) {
                byteVector.putShort(this.symbolTable.b("LocalVariableTypeTable")).putInt(2 + this.localVariableTypeTable.f4134b).putShort(this.localVariableTypeTableLength).putByteArray(this.localVariableTypeTable.f4133a, 0, this.localVariableTypeTable.f4134b);
            }
            if (this.lastCodeRuntimeVisibleTypeAnnotation != null) {
                this.lastCodeRuntimeVisibleTypeAnnotation.a(this.symbolTable.b("RuntimeVisibleTypeAnnotations"), byteVector);
            }
            if (this.lastCodeRuntimeInvisibleTypeAnnotation != null) {
                this.lastCodeRuntimeInvisibleTypeAnnotation.a(this.symbolTable.b("RuntimeInvisibleTypeAnnotations"), byteVector);
            }
            if (this.firstCodeAttribute != null) {
                this.firstCodeAttribute.a(this.symbolTable, this.code.f4133a, this.code.f4134b, this.maxStack, this.maxLocals, byteVector);
            }
        }
        if (this.numberOfExceptions > 0) {
            byteVector.putShort(this.symbolTable.b("Exceptions")).putInt(2 + (2 * this.numberOfExceptions)).putShort(this.numberOfExceptions);
            for (int i5 : this.exceptionIndexTable) {
                byteVector.putShort(i5);
            }
        }
        Attribute.a(this.symbolTable, this.accessFlags, this.signatureIndex, byteVector);
        AnnotationWriter.a(this.symbolTable, this.lastRuntimeVisibleAnnotation, this.lastRuntimeInvisibleAnnotation, this.lastRuntimeVisibleTypeAnnotation, this.lastRuntimeInvisibleTypeAnnotation, byteVector);
        if (this.lastRuntimeVisibleParameterAnnotations != null) {
            int b2 = this.symbolTable.b("RuntimeVisibleParameterAnnotations");
            AnnotationWriter[] annotationWriterArr = this.lastRuntimeVisibleParameterAnnotations;
            if (this.visibleAnnotableParameterCount == 0) {
                i2 = this.lastRuntimeVisibleParameterAnnotations.length;
            } else {
                i2 = this.visibleAnnotableParameterCount;
            }
            AnnotationWriter.a(b2, annotationWriterArr, i2, byteVector);
        }
        if (this.lastRuntimeInvisibleParameterAnnotations != null) {
            int b3 = this.symbolTable.b("RuntimeInvisibleParameterAnnotations");
            AnnotationWriter[] annotationWriterArr2 = this.lastRuntimeInvisibleParameterAnnotations;
            if (this.invisibleAnnotableParameterCount == 0) {
                i = this.lastRuntimeInvisibleParameterAnnotations.length;
            } else {
                i = this.invisibleAnnotableParameterCount;
            }
            AnnotationWriter.a(b3, annotationWriterArr2, i, byteVector);
        }
        if (this.defaultValue != null) {
            byteVector.putShort(this.symbolTable.b("AnnotationDefault")).putInt(this.defaultValue.f4134b).putByteArray(this.defaultValue.f4133a, 0, this.defaultValue.f4134b);
        }
        if (this.parameters != null) {
            byteVector.putShort(this.symbolTable.b("MethodParameters")).putInt(1 + this.parameters.f4134b).putByte(this.parametersCount).putByteArray(this.parameters.f4133a, 0, this.parameters.f4134b);
        }
        if (this.firstAttribute != null) {
            this.firstAttribute.a(this.symbolTable, byteVector);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(Attribute.Set set) {
        set.a(this.firstAttribute);
        set.a(this.firstCodeAttribute);
    }
}
