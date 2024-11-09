package net.bytebuddy.jar.asm;

import java.util.Arrays;

/* loaded from: infinitode-2.jar:net/bytebuddy/jar/asm/ConstantDynamic.class */
public final class ConstantDynamic {
    private final String name;
    private final String descriptor;
    private final Handle bootstrapMethod;
    private final Object[] bootstrapMethodArguments;

    public ConstantDynamic(String str, String str2, Handle handle, Object... objArr) {
        this.name = str;
        this.descriptor = str2;
        this.bootstrapMethod = handle;
        this.bootstrapMethodArguments = objArr;
    }

    public final String getName() {
        return this.name;
    }

    public final String getDescriptor() {
        return this.descriptor;
    }

    public final Handle getBootstrapMethod() {
        return this.bootstrapMethod;
    }

    public final int getBootstrapMethodArgumentCount() {
        return this.bootstrapMethodArguments.length;
    }

    public final Object getBootstrapMethodArgument(int i) {
        return this.bootstrapMethodArguments[i];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Object[] a() {
        return this.bootstrapMethodArguments;
    }

    public final int getSize() {
        char charAt = this.descriptor.charAt(0);
        return (charAt == 'J' || charAt == 'D') ? 2 : 1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ConstantDynamic)) {
            return false;
        }
        ConstantDynamic constantDynamic = (ConstantDynamic) obj;
        return this.name.equals(constantDynamic.name) && this.descriptor.equals(constantDynamic.descriptor) && this.bootstrapMethod.equals(constantDynamic.bootstrapMethod) && Arrays.equals(this.bootstrapMethodArguments, constantDynamic.bootstrapMethodArguments);
    }

    public final int hashCode() {
        return ((this.name.hashCode() ^ Integer.rotateLeft(this.descriptor.hashCode(), 8)) ^ Integer.rotateLeft(this.bootstrapMethod.hashCode(), 16)) ^ Integer.rotateLeft(Arrays.hashCode(this.bootstrapMethodArguments), 24);
    }

    public final String toString() {
        return this.name + " : " + this.descriptor + ' ' + this.bootstrapMethod + ' ' + Arrays.toString(this.bootstrapMethodArguments);
    }
}
