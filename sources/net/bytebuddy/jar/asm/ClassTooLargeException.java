package net.bytebuddy.jar.asm;

/* loaded from: infinitode-2.jar:net/bytebuddy/jar/asm/ClassTooLargeException.class */
public final class ClassTooLargeException extends IndexOutOfBoundsException {
    private static final long serialVersionUID = 160715609518896765L;
    private final String className;
    private final int constantPoolCount;

    public ClassTooLargeException(String str, int i) {
        super("Class too large: " + str);
        this.className = str;
        this.constantPoolCount = i;
    }

    public final String getClassName() {
        return this.className;
    }

    public final int getConstantPoolCount() {
        return this.constantPoolCount;
    }
}
