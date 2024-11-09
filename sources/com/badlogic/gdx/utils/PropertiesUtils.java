package com.badlogic.gdx.utils;

import com.badlogic.gdx.utils.ObjectMap;
import java.io.Writer;
import java.util.Date;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/PropertiesUtils.class */
public final class PropertiesUtils {
    private static final int NONE = 0;
    private static final int SLASH = 1;
    private static final int UNICODE = 2;
    private static final int CONTINUE = 3;
    private static final int KEY_DONE = 4;
    private static final int IGNORE = 5;
    private static final String LINE_SEPARATOR = "\n";

    private PropertiesUtils() {
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:30:0x013a. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:46:0x01c2  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0215  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x023d  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x024c  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0238 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void load(com.badlogic.gdx.utils.ObjectMap<java.lang.String, java.lang.String> r6, java.io.Reader r7) {
        /*
            Method dump skipped, instructions count: 710
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.badlogic.gdx.utils.PropertiesUtils.load(com.badlogic.gdx.utils.ObjectMap, java.io.Reader):void");
    }

    public static void store(ObjectMap<String, String> objectMap, Writer writer, String str) {
        storeImpl(objectMap, writer, str, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static void storeImpl(ObjectMap<String, String> objectMap, Writer writer, String str, boolean z) {
        if (str != null) {
            writeComment(writer, str);
        }
        writer.write("#");
        writer.write(new Date().toString());
        writer.write("\n");
        StringBuilder stringBuilder = new StringBuilder(200);
        ObjectMap.Entries<String, String> it = objectMap.entries().iterator();
        while (it.hasNext()) {
            ObjectMap.Entry next = it.next();
            dumpString(stringBuilder, (String) next.key, true, z);
            stringBuilder.append('=');
            dumpString(stringBuilder, (String) next.value, false, z);
            writer.write("\n");
            writer.write(stringBuilder.toString());
            stringBuilder.setLength(0);
        }
        writer.flush();
    }

    private static void dumpString(StringBuilder stringBuilder, String str, boolean z, boolean z2) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt > '=' && charAt < 127) {
                stringBuilder.append(charAt == '\\' ? "\\\\" : Character.valueOf(charAt));
            } else {
                switch (charAt) {
                    case '\t':
                        stringBuilder.append("\\t");
                        break;
                    case '\n':
                        stringBuilder.append("\\n");
                        break;
                    case '\f':
                        stringBuilder.append("\\f");
                        break;
                    case '\r':
                        stringBuilder.append("\\r");
                        break;
                    case ' ':
                        if (i == 0 || z) {
                            stringBuilder.append("\\ ");
                            break;
                        } else {
                            stringBuilder.append(charAt);
                            break;
                        }
                        break;
                    case '!':
                    case '#':
                    case ':':
                    case '=':
                        stringBuilder.append('\\').append(charAt);
                        break;
                    default:
                        if ((charAt < ' ' || charAt > '~') & z2) {
                            String hexString = Integer.toHexString(charAt);
                            stringBuilder.append("\\u");
                            for (int i2 = 0; i2 < 4 - hexString.length(); i2++) {
                                stringBuilder.append('0');
                            }
                            stringBuilder.append(hexString);
                            break;
                        } else {
                            stringBuilder.append(charAt);
                            break;
                        }
                        break;
                }
            }
        }
    }

    private static void writeComment(Writer writer, String str) {
        writer.write("#");
        int length = str.length();
        int i = 0;
        int i2 = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            if (charAt > 255 || charAt == '\n' || charAt == '\r') {
                if (i2 != i) {
                    writer.write(str.substring(i2, i));
                }
                if (charAt > 255) {
                    String hexString = Integer.toHexString(charAt);
                    writer.write("\\u");
                    for (int i3 = 0; i3 < 4 - hexString.length(); i3++) {
                        writer.write(48);
                    }
                    writer.write(hexString);
                } else {
                    writer.write("\n");
                    if (charAt == '\r' && i != length - 1 && str.charAt(i + 1) == '\n') {
                        i++;
                    }
                    if (i == length - 1 || (str.charAt(i + 1) != '#' && str.charAt(i + 1) != '!')) {
                        writer.write("#");
                    }
                }
                i2 = i + 1;
            }
            i++;
        }
        if (i2 != i) {
            writer.write(str.substring(i2, i));
        }
        writer.write("\n");
    }
}
