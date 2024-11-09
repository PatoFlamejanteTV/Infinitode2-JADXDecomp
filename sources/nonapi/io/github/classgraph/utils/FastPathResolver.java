package nonapi.io.github.classgraph.utils;

import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import nonapi.io.github.classgraph.utils.VersionFinder;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/utils/FastPathResolver.class */
public final class FastPathResolver {
    private static final Pattern percentMatcher = Pattern.compile("([%][0-9a-fA-F][0-9a-fA-F])+");
    private static final Pattern schemeTwoSlashMatcher = Pattern.compile("^[a-zA-Z+\\-.]+://");
    private static final Pattern schemeOneSlashMatcher = Pattern.compile("^[a-zA-Z+\\-.]+:/");

    private FastPathResolver() {
    }

    private static void translateSeparator(String str, int i, int i2, boolean z, StringBuilder sb) {
        for (int i3 = i; i3 < i2; i3++) {
            char charAt = str.charAt(i3);
            if (charAt == '\\' || charAt == '/') {
                if (i3 < i2 - 1 || !z) {
                    if ((sb.length() == 0 ? (char) 0 : sb.charAt(sb.length() - 1)) != '/') {
                        sb.append('/');
                    }
                }
            } else {
                sb.append(charAt);
            }
        }
    }

    private static int hexCharToInt(char c) {
        return (c < '0' || c > '9') ? (c < 'a' || c > 'f') ? (c - 'A') + 10 : (c - 'a') + 10 : c - '0';
    }

    private static void unescapePercentEncoding(String str, int i, int i2, StringBuilder sb) {
        if (i2 - i == 3 && str.charAt(i + 1) == '2' && str.charAt(i + 2) == '0') {
            sb.append(' ');
            return;
        }
        byte[] bArr = new byte[(i2 - i) / 3];
        int i3 = i;
        int i4 = 0;
        while (i3 < i2) {
            bArr[i4] = (byte) ((hexCharToInt(str.charAt(i3 + 1)) << 4) | hexCharToInt(str.charAt(i3 + 2)));
            i3 += 3;
            i4++;
        }
        sb.append(new String(bArr, StandardCharsets.UTF_8).replace("/", "%2F").replace("\\", "%5C"));
    }

    public static String normalizePath(String str, boolean z) {
        boolean z2 = str.indexOf(37) >= 0;
        boolean z3 = z2;
        if (!z2 && str.indexOf(92) < 0 && !str.endsWith("/")) {
            return str;
        }
        int length = str.length();
        StringBuilder sb = new StringBuilder();
        if (z3 && z) {
            int i = 0;
            Matcher matcher = percentMatcher.matcher(str);
            while (matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();
                translateSeparator(str, i, start, false, sb);
                unescapePercentEncoding(str, start, end, sb);
                i = end;
            }
            translateSeparator(str, i, length, true, sb);
            return sb.toString();
        }
        translateSeparator(str, 0, length, true, sb);
        return sb.toString();
    }

    public static String resolve(String str, String str2) {
        boolean z;
        String sanitizeEntryPath;
        if (str2 == null || str2.isEmpty()) {
            return str == null ? "" : str;
        }
        String str3 = "";
        boolean z2 = false;
        boolean z3 = false;
        int i = 0;
        do {
            z = false;
            if (str2.regionMatches(true, i, "jar:", 0, 4)) {
                z = true;
                i = 4;
                z3 = true;
            } else if (str2.regionMatches(true, i, "http://", 0, 7)) {
                z = true;
                i += 7;
                str3 = str3 + "http://";
                z2 = true;
            } else if (str2.regionMatches(true, i, "https://", 0, 8)) {
                z = true;
                i += 8;
                str3 = str3 + "https://";
                z2 = true;
            } else if (str2.regionMatches(true, i, "jrt:", 0, 5)) {
                z = true;
                i += 4;
                str3 = str3 + "jrt:";
                z2 = true;
            } else if (str2.regionMatches(true, i, "file:", 0, 5)) {
                z = true;
                i += 5;
                z3 = true;
            } else {
                String substring = i == 0 ? str2 : str2.substring(i);
                Matcher matcher = schemeTwoSlashMatcher.matcher(substring);
                if (matcher.find()) {
                    z = true;
                    String group = matcher.group();
                    i += group.length();
                    str3 = str3 + group;
                    z2 = true;
                } else {
                    Matcher matcher2 = schemeOneSlashMatcher.matcher(substring);
                    if (matcher2.find()) {
                        z = true;
                        String group2 = matcher2.group();
                        i += group2.length();
                        str3 = str3 + group2;
                        z2 = true;
                    }
                }
            }
        } while (z);
        if (VersionFinder.OS == VersionFinder.OperatingSystem.Windows) {
            if (str2.startsWith("//", i) || str2.startsWith("\\\\", i)) {
                i += 2;
                str3 = str3 + "//";
                z2 = true;
            } else if (str2.length() - i > 2 && Character.isLetter(str2.charAt(i)) && str2.charAt(i + 1) == ':') {
                z2 = true;
            } else if (str2.length() - i > 3 && ((str2.charAt(i) == '/' || str2.charAt(i) == '\\') && Character.isLetter(str2.charAt(i + 1)) && str2.charAt(i + 2) == ':')) {
                z2 = true;
                i++;
            }
        }
        if (str2.length() - i > 1 && (str2.charAt(i) == '/' || str2.charAt(i) == '\\')) {
            z2 = true;
        }
        String normalizePath = normalizePath(i == 0 ? str2 : str2.substring(i), z3);
        String str4 = normalizePath;
        if (!normalizePath.equals("/")) {
            if (str4.endsWith("/")) {
                str4 = str4.substring(0, str4.length() - 1);
            }
            if (str4.endsWith("!")) {
                str4 = str4.substring(0, str4.length() - 1);
            }
            if (str4.endsWith("/")) {
                str4 = str4.substring(0, str4.length() - 1);
            }
            if (str4.isEmpty()) {
                str4 = "/";
            }
        }
        if (z2 || str == null || str.isEmpty()) {
            sanitizeEntryPath = FileUtils.sanitizeEntryPath(str4, false, true);
        } else {
            sanitizeEntryPath = FileUtils.sanitizeEntryPath(str + (str.endsWith("/") ? "" : "/") + str4, false, true);
        }
        return str3.isEmpty() ? sanitizeEntryPath : str3 + sanitizeEntryPath;
    }

    public static String resolve(String str) {
        return resolve(null, str);
    }
}
