package net.bytebuddy.jar.asm;

/* loaded from: infinitode-2.jar:net/bytebuddy/jar/asm/TypePath.class */
public final class TypePath {
    public static final int ARRAY_ELEMENT = 0;
    public static final int INNER_TYPE = 1;
    public static final int WILDCARD_BOUND = 2;
    public static final int TYPE_ARGUMENT = 3;
    private final byte[] typePathContainer;
    private final int typePathOffset;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TypePath(byte[] bArr, int i) {
        this.typePathContainer = bArr;
        this.typePathOffset = i;
    }

    public final int getLength() {
        return this.typePathContainer[this.typePathOffset];
    }

    public final int getStep(int i) {
        return this.typePathContainer[this.typePathOffset + (2 * i) + 1];
    }

    public final int getStepArgument(int i) {
        return this.typePathContainer[this.typePathOffset + (2 * i) + 2];
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x00a9, code lost:            if (r0 == ';') goto L36;     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00b3, code lost:            throw new java.lang.IllegalArgumentException();     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static net.bytebuddy.jar.asm.TypePath fromString(java.lang.String r5) {
        /*
            Method dump skipped, instructions count: 225
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.jar.asm.TypePath.fromString(java.lang.String):net.bytebuddy.jar.asm.TypePath");
    }

    public final String toString() {
        int length = getLength();
        StringBuilder sb = new StringBuilder(length << 1);
        for (int i = 0; i < length; i++) {
            switch (getStep(i)) {
                case 0:
                    sb.append('[');
                    break;
                case 1:
                    sb.append('.');
                    break;
                case 2:
                    sb.append('*');
                    break;
                case 3:
                    sb.append(getStepArgument(i)).append(';');
                    break;
                default:
                    throw new AssertionError();
            }
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(TypePath typePath, ByteVector byteVector) {
        if (typePath == null) {
            byteVector.putByte(0);
        } else {
            byteVector.putByteArray(typePath.typePathContainer, typePath.typePathOffset, (typePath.typePathContainer[typePath.typePathOffset] << 1) + 1);
        }
    }
}
