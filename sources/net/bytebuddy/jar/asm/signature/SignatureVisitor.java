package net.bytebuddy.jar.asm.signature;

/* loaded from: infinitode-2.jar:net/bytebuddy/jar/asm/signature/SignatureVisitor.class */
public abstract class SignatureVisitor {
    public static final char EXTENDS = '+';
    public static final char SUPER = '-';
    public static final char INSTANCEOF = '=';
    protected final int api;

    /* JADX INFO: Access modifiers changed from: protected */
    public SignatureVisitor(int i) {
        if (i != 589824 && i != 524288 && i != 458752 && i != 393216 && i != 327680 && i != 262144 && i != 17432576) {
            throw new IllegalArgumentException("Unsupported api " + i);
        }
        this.api = i;
    }

    public void visitFormalTypeParameter(String str) {
    }

    public SignatureVisitor visitClassBound() {
        return this;
    }

    public SignatureVisitor visitInterfaceBound() {
        return this;
    }

    public SignatureVisitor visitSuperclass() {
        return this;
    }

    public SignatureVisitor visitInterface() {
        return this;
    }

    public SignatureVisitor visitParameterType() {
        return this;
    }

    public SignatureVisitor visitReturnType() {
        return this;
    }

    public SignatureVisitor visitExceptionType() {
        return this;
    }

    public void visitBaseType(char c) {
    }

    public void visitTypeVariable(String str) {
    }

    public SignatureVisitor visitArrayType() {
        return this;
    }

    public void visitClassType(String str) {
    }

    public void visitInnerClassType(String str) {
    }

    public void visitTypeArgument() {
    }

    public SignatureVisitor visitTypeArgument(char c) {
        return this;
    }

    public void visitEnd() {
    }
}
