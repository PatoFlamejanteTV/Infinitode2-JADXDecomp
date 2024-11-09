package net.bytebuddy.jar.asm;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:net/bytebuddy/jar/asm/ModuleWriter.class */
public final class ModuleWriter extends ModuleVisitor {
    private final SymbolTable symbolTable;
    private final int moduleNameIndex;
    private final int moduleFlags;
    private final int moduleVersionIndex;
    private int requiresCount;
    private final ByteVector requires;
    private int exportsCount;
    private final ByteVector exports;
    private int opensCount;
    private final ByteVector opens;
    private int usesCount;
    private final ByteVector usesIndex;
    private int providesCount;
    private final ByteVector provides;
    private int packageCount;
    private final ByteVector packageIndex;
    private int mainClassIndex;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ModuleWriter(SymbolTable symbolTable, int i, int i2, int i3) {
        super(589824);
        this.symbolTable = symbolTable;
        this.moduleNameIndex = i;
        this.moduleFlags = i2;
        this.moduleVersionIndex = i3;
        this.requires = new ByteVector();
        this.exports = new ByteVector();
        this.opens = new ByteVector();
        this.usesIndex = new ByteVector();
        this.provides = new ByteVector();
        this.packageIndex = new ByteVector();
    }

    @Override // net.bytebuddy.jar.asm.ModuleVisitor
    public final void visitMainClass(String str) {
        this.mainClassIndex = this.symbolTable.a(str).f4146a;
    }

    @Override // net.bytebuddy.jar.asm.ModuleVisitor
    public final void visitPackage(String str) {
        this.packageIndex.putShort(this.symbolTable.e(str).f4146a);
        this.packageCount++;
    }

    @Override // net.bytebuddy.jar.asm.ModuleVisitor
    public final void visitRequire(String str, int i, String str2) {
        this.requires.putShort(this.symbolTable.d(str).f4146a).putShort(i).putShort(str2 == null ? 0 : this.symbolTable.b(str2));
        this.requiresCount++;
    }

    @Override // net.bytebuddy.jar.asm.ModuleVisitor
    public final void visitExport(String str, int i, String... strArr) {
        this.exports.putShort(this.symbolTable.e(str).f4146a).putShort(i);
        if (strArr == null) {
            this.exports.putShort(0);
        } else {
            this.exports.putShort(strArr.length);
            for (String str2 : strArr) {
                this.exports.putShort(this.symbolTable.d(str2).f4146a);
            }
        }
        this.exportsCount++;
    }

    @Override // net.bytebuddy.jar.asm.ModuleVisitor
    public final void visitOpen(String str, int i, String... strArr) {
        this.opens.putShort(this.symbolTable.e(str).f4146a).putShort(i);
        if (strArr == null) {
            this.opens.putShort(0);
        } else {
            this.opens.putShort(strArr.length);
            for (String str2 : strArr) {
                this.opens.putShort(this.symbolTable.d(str2).f4146a);
            }
        }
        this.opensCount++;
    }

    @Override // net.bytebuddy.jar.asm.ModuleVisitor
    public final void visitUse(String str) {
        this.usesIndex.putShort(this.symbolTable.a(str).f4146a);
        this.usesCount++;
    }

    @Override // net.bytebuddy.jar.asm.ModuleVisitor
    public final void visitProvide(String str, String... strArr) {
        this.provides.putShort(this.symbolTable.a(str).f4146a);
        this.provides.putShort(strArr.length);
        for (String str2 : strArr) {
            this.provides.putShort(this.symbolTable.a(str2).f4146a);
        }
        this.providesCount++;
    }

    @Override // net.bytebuddy.jar.asm.ModuleVisitor
    public final void visitEnd() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a() {
        return 1 + (this.packageCount > 0 ? 1 : 0) + (this.mainClassIndex > 0 ? 1 : 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int b() {
        this.symbolTable.b("Module");
        int i = 22 + this.requires.f4134b + this.exports.f4134b + this.opens.f4134b + this.usesIndex.f4134b + this.provides.f4134b;
        if (this.packageCount > 0) {
            this.symbolTable.b("ModulePackages");
            i += 8 + this.packageIndex.f4134b;
        }
        if (this.mainClassIndex > 0) {
            this.symbolTable.b("ModuleMainClass");
            i += 8;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(ByteVector byteVector) {
        byteVector.putShort(this.symbolTable.b("Module")).putInt(16 + this.requires.f4134b + this.exports.f4134b + this.opens.f4134b + this.usesIndex.f4134b + this.provides.f4134b).putShort(this.moduleNameIndex).putShort(this.moduleFlags).putShort(this.moduleVersionIndex).putShort(this.requiresCount).putByteArray(this.requires.f4133a, 0, this.requires.f4134b).putShort(this.exportsCount).putByteArray(this.exports.f4133a, 0, this.exports.f4134b).putShort(this.opensCount).putByteArray(this.opens.f4133a, 0, this.opens.f4134b).putShort(this.usesCount).putByteArray(this.usesIndex.f4133a, 0, this.usesIndex.f4134b).putShort(this.providesCount).putByteArray(this.provides.f4133a, 0, this.provides.f4134b);
        if (this.packageCount > 0) {
            byteVector.putShort(this.symbolTable.b("ModulePackages")).putInt(2 + this.packageIndex.f4134b).putShort(this.packageCount).putByteArray(this.packageIndex.f4133a, 0, this.packageIndex.f4134b);
        }
        if (this.mainClassIndex > 0) {
            byteVector.putShort(this.symbolTable.b("ModuleMainClass")).putInt(2).putShort(this.mainClassIndex);
        }
    }
}
