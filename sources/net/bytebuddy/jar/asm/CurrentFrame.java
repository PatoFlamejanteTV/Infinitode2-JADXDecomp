package net.bytebuddy.jar.asm;

/* loaded from: infinitode-2.jar:net/bytebuddy/jar/asm/CurrentFrame.class */
final class CurrentFrame extends Frame {
    /* JADX INFO: Access modifiers changed from: package-private */
    public CurrentFrame(Label label) {
        super(label);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // net.bytebuddy.jar.asm.Frame
    public final void a(int i, int i2, Symbol symbol, SymbolTable symbolTable) {
        super.a(i, i2, symbol, symbolTable);
        Frame frame = new Frame(null);
        a(symbolTable, frame, 0);
        a(frame);
    }
}
