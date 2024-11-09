package nonapi.io.github.classgraph.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import nonapi.io.github.classgraph.utils.VersionFinder;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/utils/URLPathEncoder.class */
public final class URLPathEncoder {
    private static boolean[] safe = new boolean[256];
    private static final char[] HEXADECIMAL;
    private static final String[] SCHEME_PREFIXES;

    static {
        for (int i = 97; i <= 122; i++) {
            safe[i] = true;
        }
        for (int i2 = 65; i2 <= 90; i2++) {
            safe[i2] = true;
        }
        for (int i3 = 48; i3 <= 57; i3++) {
            safe[i3] = true;
        }
        boolean[] zArr = safe;
        boolean[] zArr2 = safe;
        boolean[] zArr3 = safe;
        boolean[] zArr4 = safe;
        safe[43] = true;
        zArr4[46] = true;
        zArr3[95] = true;
        zArr2[45] = true;
        zArr[36] = true;
        boolean[] zArr5 = safe;
        boolean[] zArr6 = safe;
        boolean[] zArr7 = safe;
        boolean[] zArr8 = safe;
        boolean[] zArr9 = safe;
        safe[44] = true;
        zArr9[41] = true;
        zArr8[40] = true;
        zArr7[39] = true;
        zArr6[42] = true;
        zArr5[33] = true;
        safe[47] = true;
        HEXADECIMAL = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        SCHEME_PREFIXES = new String[]{"jrt:", "file:", "jar:file:", "jar:", "http:", "https:"};
    }

    private URLPathEncoder() {
    }

    private static void unescapeChars(String str, boolean z, ByteArrayOutputStream byteArrayOutputStream) {
        if (str.isEmpty()) {
            return;
        }
        int i = 0;
        int length = str.length();
        while (i < length) {
            char charAt = str.charAt(i);
            if (charAt == '%') {
                if (i <= length - 3) {
                    int i2 = i + 1;
                    char charAt2 = str.charAt(i2);
                    int i3 = (charAt2 < '0' || charAt2 > '9') ? (charAt2 < 'a' || charAt2 > 'f') ? (charAt2 < 'A' || charAt2 > 'F') ? -1 : (charAt2 - 'A') + 10 : (charAt2 - 'a') + 10 : charAt2 - '0';
                    i = i2 + 1;
                    char charAt3 = str.charAt(i);
                    int i4 = (charAt3 < '0' || charAt3 > '9') ? (charAt3 < 'a' || charAt3 > 'f') ? (charAt3 < 'A' || charAt3 > 'F') ? -1 : (charAt3 - 'A') + 10 : (charAt3 - 'a') + 10 : charAt3 - '0';
                    if (i3 < 0 || i4 < 0) {
                        try {
                            byteArrayOutputStream.write(str.substring(i - 2, i + 1).getBytes(StandardCharsets.UTF_8));
                        } catch (IOException unused) {
                        }
                    } else {
                        byteArrayOutputStream.write((byte) ((i3 << 4) | i4));
                    }
                }
            } else if (z && charAt == '+') {
                byteArrayOutputStream.write(32);
            } else if (charAt <= 127) {
                byteArrayOutputStream.write((byte) charAt);
            } else {
                try {
                    byteArrayOutputStream.write(Character.toString(charAt).getBytes(StandardCharsets.UTF_8));
                } catch (IOException unused2) {
                }
            }
            i++;
        }
    }

    public static String decodePath(String str) {
        int indexOf = str.indexOf(63);
        String substring = indexOf < 0 ? str : str.substring(0, indexOf);
        String substring2 = indexOf < 0 ? "" : str.substring(indexOf);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        unescapeChars(substring, false, byteArrayOutputStream);
        unescapeChars(substring2, true, byteArrayOutputStream);
        return new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8);
    }

    public static String encodePath(String str) {
        int i = 0;
        String[] strArr = SCHEME_PREFIXES;
        int length = strArr.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            }
            String str2 = strArr[i2];
            if (!str.startsWith(str2)) {
                i2++;
            } else {
                i = str2.length();
                break;
            }
        }
        if (VersionFinder.OS == VersionFinder.OperatingSystem.Windows) {
            int i3 = i;
            int i4 = i3;
            if (i3 < str.length() && str.charAt(i4) == '/') {
                i4++;
            }
            if (i4 < str.length() - 1 && Character.isLetter(str.charAt(i4)) && str.charAt(i4 + 1) == ':') {
                i = i4 + 2;
            }
        }
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        StringBuilder sb = new StringBuilder(bytes.length * 3);
        for (int i5 = 0; i5 < bytes.length; i5++) {
            int i6 = bytes[i5] & 255;
            if (safe[i6] || (i6 == 58 && i5 < i)) {
                sb.append((char) i6);
            } else {
                sb.append('%');
                sb.append(HEXADECIMAL[(i6 & User32.VK_OEM_ATTN) >> 4]);
                sb.append(HEXADECIMAL[i6 & 15]);
            }
        }
        return sb.toString();
    }

    public static String normalizeURLPath(String str) {
        String str2 = str;
        if (!str.startsWith("jrt:") && !str2.startsWith("http://") && !str2.startsWith("https://")) {
            if (str2.startsWith("jar:")) {
                str2 = str2.substring(4);
            }
            if (str2.startsWith("file:")) {
                str2 = str2.substring(4);
            }
            String str3 = "";
            if (VersionFinder.OS == VersionFinder.OperatingSystem.Windows) {
                if (str2.length() >= 2 && Character.isLetter(str2.charAt(0)) && str2.charAt(1) == ':') {
                    str3 = str2.substring(0, 2);
                    str2 = str2.substring(2);
                } else if (str2.length() >= 3 && str2.charAt(0) == '/' && Character.isLetter(str2.charAt(1)) && str2.charAt(2) == ':') {
                    str3 = str2.substring(1, 3);
                    str2 = str2.substring(3);
                }
            }
            String replace = str2.replace("/!", "!").replace("!/", "!").replace("!", "!/");
            if (str3.isEmpty()) {
                str2 = replace.startsWith("/") ? "file:" + replace : "file:/" + replace;
            } else {
                str2 = "file:/" + str3 + (replace.startsWith("/") ? replace : "/" + replace);
            }
            if (str2.contains("!") && !str2.startsWith("jar:")) {
                str2 = "jar:" + str2;
            }
        }
        return encodePath(str2);
    }
}
