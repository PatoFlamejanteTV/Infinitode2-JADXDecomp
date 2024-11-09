package net.bytebuddy.jar.asm;

/* loaded from: infinitode-2.jar:net/bytebuddy/jar/asm/ModuleVisitor.class */
public abstract class ModuleVisitor {
    protected final int api;
    protected ModuleVisitor mv;

    /* JADX INFO: Access modifiers changed from: protected */
    public ModuleVisitor(int i) {
        this(i, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ModuleVisitor(int i, ModuleVisitor moduleVisitor) {
        if (i != 589824 && i != 524288 && i != 458752 && i != 393216 && i != 327680 && i != 262144 && i != 17432576) {
            throw new IllegalArgumentException("Unsupported api " + i);
        }
        if (i == 17432576) {
            Constants.a(this);
        }
        this.api = i;
        this.mv = moduleVisitor;
    }

    public ModuleVisitor getDelegate() {
        return this.mv;
    }

    public void visitMainClass(String str) {
        if (this.mv != null) {
            this.mv.visitMainClass(str);
        }
    }

    public void visitPackage(String str) {
        if (this.mv != null) {
            this.mv.visitPackage(str);
        }
    }

    public void visitRequire(String str, int i, String str2) {
        if (this.mv != null) {
            this.mv.visitRequire(str, i, str2);
        }
    }

    public void visitExport(String str, int i, String... strArr) {
        if (this.mv != null) {
            this.mv.visitExport(str, i, strArr);
        }
    }

    public void visitOpen(String str, int i, String... strArr) {
        if (this.mv != null) {
            this.mv.visitOpen(str, i, strArr);
        }
    }

    public void visitUse(String str) {
        if (this.mv != null) {
            this.mv.visitUse(str);
        }
    }

    public void visitProvide(String str, String... strArr) {
        if (this.mv != null) {
            this.mv.visitProvide(str, strArr);
        }
    }

    public void visitEnd() {
        if (this.mv != null) {
            this.mv.visitEnd();
        }
    }
}
