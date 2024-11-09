package net.bytebuddy.jar.asm;

import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:net/bytebuddy/jar/asm/MethodTooLargeException.class */
public final class MethodTooLargeException extends IndexOutOfBoundsException {
    private static final long serialVersionUID = 6807380416709738314L;
    private final String className;
    private final String methodName;
    private final String descriptor;
    private final int codeSize;

    public MethodTooLargeException(String str, String str2, String str3, int i) {
        super("Method too large: " + str + "." + str2 + SequenceUtils.SPACE + str3);
        this.className = str;
        this.methodName = str2;
        this.descriptor = str3;
        this.codeSize = i;
    }

    public final String getClassName() {
        return this.className;
    }

    public final String getMethodName() {
        return this.methodName;
    }

    public final String getDescriptor() {
        return this.descriptor;
    }

    public final int getCodeSize() {
        return this.codeSize;
    }
}
