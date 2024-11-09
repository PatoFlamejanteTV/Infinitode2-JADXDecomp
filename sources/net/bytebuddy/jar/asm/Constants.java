package net.bytebuddy.jar.asm;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;

/* loaded from: infinitode-2.jar:net/bytebuddy/jar/asm/Constants.class */
final class Constants {
    private Constants() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(Object obj) {
        Class<?> cls = obj.getClass();
        String replace = cls.getName().replace('.', '/');
        if (!a(replace)) {
            a(cls.getClassLoader().getResourceAsStream(replace + ".class"));
        }
    }

    private static boolean a(String str) {
        if (str.startsWith("net/bytebuddy/jar/asm/")) {
            return str.contains("Test$") || Pattern.matches(new StringBuilder("net/bytebuddy/jar/asm/util/Trace").append("(Annotation|Class|Field|Method|Module|RecordComponent|Signature)").append("Visitor(\\$.*)?").toString(), str) || Pattern.matches(new StringBuilder("net/bytebuddy/jar/asm/util/Check").append("(Annotation|Class|Field|Method|Module|RecordComponent|Signature)").append("Adapter(\\$.*)?").toString(), str);
        }
        return false;
    }

    private static void a(InputStream inputStream) {
        if (inputStream == null) {
            throw new IllegalStateException("Bytecode not available, can't check class version");
        }
        try {
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            try {
                dataInputStream.readInt();
                int readUnsignedShort = dataInputStream.readUnsignedShort();
                dataInputStream.close();
                if (readUnsignedShort != 65535) {
                    throw new IllegalStateException("ASM9_EXPERIMENTAL can only be used by classes compiled with --enable-preview");
                }
            } catch (Throwable th) {
                try {
                    dataInputStream.close();
                } catch (Throwable unused) {
                }
                throw th;
            }
        } catch (IOException e) {
            throw new IllegalStateException("I/O error, can't check class version", e);
        }
    }
}
