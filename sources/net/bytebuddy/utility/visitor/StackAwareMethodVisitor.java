package net.bytebuddy.utility.visitor;

import com.badlogic.gdx.net.HttpStatus;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import net.bytebuddy.build.AccessControllerPlugin;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.jar.asm.Handle;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import net.bytebuddy.jar.asm.Type;
import net.bytebuddy.utility.CompoundList;
import net.bytebuddy.utility.OpenedClassReader;
import net.bytebuddy.utility.nullability.MaybeNull;
import net.bytebuddy.utility.privilege.GetSystemPropertyAction;

/* loaded from: infinitode-2.jar:net/bytebuddy/utility/visitor/StackAwareMethodVisitor.class */
public class StackAwareMethodVisitor extends MethodVisitor {
    public static final String UNADJUSTED_PROPERTY = "net.bytebuddy.unadjusted";
    public static final boolean UNADJUSTED;
    private static final int[] SIZE_CHANGE;
    private List<StackSize> current;
    private final Map<Label, List<StackSize>> sizes;
    private int freeIndex;
    private static final boolean ACCESS_CONTROLLER;

    static {
        boolean z;
        try {
            Class.forName("java.security.AccessController", false, null);
            ACCESS_CONTROLLER = Boolean.parseBoolean(System.getProperty("net.bytebuddy.securitymanager", "true"));
        } catch (ClassNotFoundException unused) {
            ACCESS_CONTROLLER = false;
        } catch (SecurityException unused2) {
            ACCESS_CONTROLLER = true;
        }
        try {
            z = Boolean.parseBoolean((String) doPrivileged(new GetSystemPropertyAction(UNADJUSTED_PROPERTY)));
        } catch (Exception unused3) {
            z = false;
        }
        UNADJUSTED = z;
        SIZE_CHANGE = new int[HttpStatus.SC_ACCEPTED];
        for (int i = 0; i < SIZE_CHANGE.length; i++) {
            SIZE_CHANGE[i] = "EFFFFFFFFGGFFFGGFFFEEFGFGFEEEEEEEEEEEEEEEEEEEEDEDEDDDDDCDCDEEEEEEEEEEEEEEEEEEEEBABABBBBDCFFFGGGEDCDCDCDCDCDCDCDCDCDCEEEEDDDDDDDCDCDCEFEFDDEEFFDEDEEEBDDBBDDDDDDCCCCCCCCEEEDDDCDCDEEEEEEEEEEFEEEEEEDDEEDDEE".charAt(i) - 'E';
        }
    }

    protected StackAwareMethodVisitor(MethodVisitor methodVisitor, MethodDescription methodDescription) {
        super(OpenedClassReader.ASM_API, methodVisitor);
        this.current = new ArrayList();
        this.sizes = new HashMap();
        this.freeIndex = methodDescription.getStackSize();
    }

    public static MethodVisitor of(MethodVisitor methodVisitor, MethodDescription methodDescription) {
        return UNADJUSTED ? methodVisitor : new StackAwareMethodVisitor(methodVisitor, methodDescription);
    }

    @AccessControllerPlugin.Enhance
    private static <T> T doPrivileged(PrivilegedAction<T> privilegedAction) {
        return ACCESS_CONTROLLER ? (T) AccessController.doPrivileged(privilegedAction) : privilegedAction.run();
    }

    private void adjustStack(int i) {
        adjustStack(i, 0);
    }

    private void adjustStack(int i, int i2) {
        if (i > 2) {
            throw new IllegalStateException("Cannot push multiple values onto the operand stack: " + i);
        }
        if (i > 0) {
            int size = this.current.size();
            while (i2 > 0 && size > 0) {
                size--;
                i2 -= this.current.get(size).getSize();
            }
            if (i2 < 0) {
                throw new IllegalStateException("Unexpected offset underflow: " + i2);
            }
            this.current.add(size, StackSize.of(i));
            return;
        }
        if (i2 != 0) {
            throw new IllegalStateException("Cannot specify non-zero offset " + i2 + " for non-incrementing value: " + i);
        }
        while (i < 0) {
            if (this.current.isEmpty()) {
                return;
            } else {
                i += this.current.remove(this.current.size() - 1).getSize();
            }
        }
        if (i == 1) {
            this.current.add(StackSize.SINGLE);
        } else if (i != 0) {
            throw new IllegalStateException("Unexpected remainder on the operand stack: " + i);
        }
    }

    public void drainStack() {
        doDrain(this.current);
    }

    public int drainStack(int i, int i2, StackSize stackSize) {
        if (this.current.isEmpty()) {
            return 0;
        }
        int size = this.current.get(this.current.size() - 1).getSize() - stackSize.getSize();
        if (this.current.size() == 1 && size == 0) {
            return 0;
        }
        super.visitVarInsn(i, this.freeIndex);
        if (size == 1) {
            super.visitInsn(87);
        } else if (size != 0) {
            throw new IllegalStateException("Unexpected remainder on the operand stack: " + size);
        }
        doDrain(this.current.subList(0, this.current.size() - 1));
        super.visitVarInsn(i2, this.freeIndex);
        return this.freeIndex + stackSize.getSize();
    }

    private void doDrain(List<StackSize> list) {
        ListIterator<StackSize> listIterator = list.listIterator(list.size());
        while (listIterator.hasPrevious()) {
            StackSize previous = listIterator.previous();
            switch (previous) {
                case SINGLE:
                    super.visitInsn(87);
                    break;
                case DOUBLE:
                    super.visitInsn(88);
                    break;
                default:
                    throw new IllegalStateException("Unexpected stack size: " + previous);
            }
        }
    }

    public void register(Label label, List<StackSize> list) {
        this.sizes.put(label, list);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitInsn(int i) {
        switch (i) {
            case 47:
            case 49:
                adjustStack(-2);
                adjustStack(2);
                break;
            case 90:
            case 93:
                adjustStack(SIZE_CHANGE[i], SIZE_CHANGE[i] + 1);
                break;
            case 91:
            case 94:
                adjustStack(SIZE_CHANGE[i], SIZE_CHANGE[i] + 2);
                break;
            case 133:
            case 135:
            case 140:
            case 141:
                adjustStack(-1);
                adjustStack(2);
                break;
            case 136:
            case 137:
            case 142:
            case 144:
                adjustStack(-2);
                adjustStack(1);
                break;
            case 172:
            case 173:
            case 174:
            case 175:
            case 176:
            case 177:
            case 191:
                this.current.clear();
                break;
            default:
                adjustStack(SIZE_CHANGE[i]);
                break;
        }
        super.visitInsn(i);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitIntInsn(int i, int i2) {
        adjustStack(SIZE_CHANGE[i]);
        super.visitIntInsn(i, i2);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    @SuppressFBWarnings(value = {"SF_SWITCH_NO_DEFAULT"}, justification = "No action required on default option.")
    public void visitVarInsn(int i, int i2) {
        switch (i) {
            case 54:
            case 56:
            case 58:
                this.freeIndex = Math.max(this.freeIndex, i2 + 1);
                break;
            case 55:
            case 57:
                this.freeIndex = Math.max(this.freeIndex, i2 + 2);
                break;
            case 169:
                this.current.clear();
                break;
        }
        adjustStack(SIZE_CHANGE[i]);
        super.visitVarInsn(i, i2);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitTypeInsn(int i, String str) {
        adjustStack(SIZE_CHANGE[i]);
        super.visitTypeInsn(i, str);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitFieldInsn(int i, String str, String str2, String str3) {
        int size = Type.getType(str3).getSize();
        switch (i) {
            case 178:
                adjustStack(size);
                break;
            case 179:
                adjustStack(-size);
                break;
            case 180:
                adjustStack(-1);
                adjustStack(size);
                break;
            case 181:
                adjustStack((-size) - 1);
                break;
            default:
                throw new IllegalStateException("Unexpected opcode: " + i);
        }
        super.visitFieldInsn(i, str, str2, str3);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitMethodInsn(int i, String str, String str2, String str3, boolean z) {
        int argumentsAndReturnSizes = Type.getArgumentsAndReturnSizes(str3);
        adjustStack((-(argumentsAndReturnSizes >> 2)) + (i == 184 ? 1 : 0));
        adjustStack(argumentsAndReturnSizes & 3);
        super.visitMethodInsn(i, str, str2, str3, z);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitInvokeDynamicInsn(String str, String str2, Handle handle, Object... objArr) {
        int argumentsAndReturnSizes = Type.getArgumentsAndReturnSizes(str2);
        adjustStack((-(argumentsAndReturnSizes >> 2)) + 1);
        adjustStack(argumentsAndReturnSizes & 3);
        super.visitInvokeDynamicInsn(str, str2, handle, objArr);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitLdcInsn(Object obj) {
        adjustStack(((obj instanceof Long) || (obj instanceof Double)) ? 2 : 1);
        super.visitLdcInsn(obj);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitMultiANewArrayInsn(String str, int i) {
        adjustStack(1 - i);
        super.visitMultiANewArrayInsn(str, i);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitJumpInsn(int i, Label label) {
        adjustStack(SIZE_CHANGE[i]);
        this.sizes.put(label, new ArrayList(i == 168 ? CompoundList.of(this.current, StackSize.SINGLE) : this.current));
        if (i == 167) {
            this.current.clear();
        }
        super.visitJumpInsn(i, label);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitLabel(Label label) {
        List<StackSize> list = this.sizes.get(label);
        if (list != null) {
            this.current = new ArrayList(list);
        }
        super.visitLabel(label);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitLineNumber(int i, Label label) {
        super.visitLineNumber(i, label);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitTableSwitchInsn(int i, int i2, Label label, Label... labelArr) {
        adjustStack(-1);
        ArrayList arrayList = new ArrayList(this.current);
        this.sizes.put(label, arrayList);
        for (Label label2 : labelArr) {
            this.sizes.put(label2, arrayList);
        }
        super.visitTableSwitchInsn(i, i2, label, labelArr);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitLookupSwitchInsn(Label label, int[] iArr, Label[] labelArr) {
        adjustStack(-1);
        ArrayList arrayList = new ArrayList(this.current);
        this.sizes.put(label, arrayList);
        for (Label label2 : labelArr) {
            this.sizes.put(label2, arrayList);
        }
        super.visitLookupSwitchInsn(label, iArr, labelArr);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    public void visitTryCatchBlock(Label label, Label label2, Label label3, @MaybeNull String str) {
        this.sizes.put(label3, Collections.singletonList(StackSize.SINGLE));
        super.visitTryCatchBlock(label, label2, label3, str);
    }

    @Override // net.bytebuddy.jar.asm.MethodVisitor
    @SuppressFBWarnings(value = {"RC_REF_COMPARISON_BAD_PRACTICE"}, justification = "ASM models frames by reference identity.")
    public void visitFrame(int i, int i2, @MaybeNull Object[] objArr, int i3, @MaybeNull Object[] objArr2) {
        switch (i) {
            case -1:
            case 0:
                this.current.clear();
                for (int i4 = 0; i4 < i3; i4++) {
                    if (objArr2[i4] == Opcodes.LONG || objArr2[i4] == Opcodes.DOUBLE) {
                        this.current.add(StackSize.DOUBLE);
                    } else {
                        this.current.add(StackSize.SINGLE);
                    }
                }
                break;
            case 1:
            case 2:
            case 3:
                this.current.clear();
                break;
            case 4:
                this.current.clear();
                if (objArr2[0] == Opcodes.LONG || objArr2[0] == Opcodes.DOUBLE) {
                    this.current.add(StackSize.DOUBLE);
                    break;
                } else {
                    this.current.add(StackSize.SINGLE);
                    break;
                }
            default:
                throw new IllegalStateException("Unknown frame type: " + i);
        }
        super.visitFrame(i, i2, objArr, i3, objArr2);
    }
}
