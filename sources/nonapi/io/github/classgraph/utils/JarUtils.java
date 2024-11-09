package nonapi.io.github.classgraph.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import nonapi.io.github.classgraph.fastzipfilereader.NestedJarHandler;
import nonapi.io.github.classgraph.scanspec.ScanSpec;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/utils/JarUtils.class */
public final class JarUtils {
    public static final Pattern URL_SCHEME_PATTERN = Pattern.compile("[a-zA-Z][a-zA-Z0-9+-.]+[:].*");
    private static final Pattern DASH_VERSION = Pattern.compile("-(\\d+(\\.|$))");
    private static final Pattern NON_ALPHANUM = Pattern.compile("[^A-Za-z0-9]");
    private static final Pattern REPEATING_DOTS = Pattern.compile("(\\.)(\\1)+");
    private static final Pattern LEADING_DOTS = Pattern.compile("^\\.");
    private static final Pattern TRAILING_DOTS = Pattern.compile("\\.$");
    private static final String[] UNIX_NON_PATH_SEPARATORS;
    private static final int[] UNIX_NON_PATH_SEPARATOR_COLON_POSITIONS;

    static {
        String[] strArr = {"jar:", "file:", "http://", "https://", "\\:"};
        UNIX_NON_PATH_SEPARATORS = strArr;
        UNIX_NON_PATH_SEPARATOR_COLON_POSITIONS = new int[strArr.length];
        for (int i = 0; i < UNIX_NON_PATH_SEPARATORS.length; i++) {
            UNIX_NON_PATH_SEPARATOR_COLON_POSITIONS[i] = UNIX_NON_PATH_SEPARATORS[i].indexOf(58);
            if (UNIX_NON_PATH_SEPARATOR_COLON_POSITIONS[i] < 0) {
                throw new RuntimeException("Could not find ':' in \"" + UNIX_NON_PATH_SEPARATORS[i] + "\"");
            }
        }
    }

    private JarUtils() {
    }

    public static String[] smartPathSplit(String str, ScanSpec scanSpec) {
        return smartPathSplit(str, File.pathSeparatorChar, scanSpec);
    }

    public static String[] smartPathSplit(String str, char c, ScanSpec scanSpec) {
        int indexOf;
        if (str == null || str.isEmpty()) {
            return new String[0];
        }
        if (c != ':') {
            ArrayList arrayList = new ArrayList();
            for (String str2 : str.split(String.valueOf(c))) {
                String trim = str2.trim();
                if (!trim.isEmpty()) {
                    arrayList.add(trim);
                }
            }
            return (String[]) arrayList.toArray(new String[0]);
        }
        HashSet hashSet = new HashSet();
        int i = -1;
        do {
            boolean z = false;
            for (int i2 = 0; i2 < UNIX_NON_PATH_SEPARATORS.length; i2++) {
                int i3 = i - UNIX_NON_PATH_SEPARATOR_COLON_POSITIONS[i2];
                if (str.regionMatches(true, i3, UNIX_NON_PATH_SEPARATORS[i2], 0, UNIX_NON_PATH_SEPARATORS[i2].length()) && (i3 == 0 || str.charAt(i3 - 1) == ':')) {
                    z = true;
                    break;
                }
            }
            if (!z && scanSpec != null && scanSpec.allowedURLSchemes != null && !scanSpec.allowedURLSchemes.isEmpty()) {
                for (String str3 : scanSpec.allowedURLSchemes) {
                    if (!str3.equals("http") && !str3.equals("https") && !str3.equals("jar") && !str3.equals("file")) {
                        int length = str3.length();
                        int i4 = i - length;
                        if (str.regionMatches(true, i4, str3, 0, length) && (i4 == 0 || str.charAt(i4 - 1) == ':')) {
                            z = true;
                            break;
                        }
                    }
                }
            }
            if (!z) {
                hashSet.add(Integer.valueOf(i));
            }
            indexOf = str.indexOf(58, i + 1);
            i = indexOf;
        } while (indexOf >= 0);
        hashSet.add(Integer.valueOf(str.length()));
        ArrayList arrayList2 = new ArrayList(hashSet);
        CollectionUtils.sortIfNotEmpty(arrayList2);
        ArrayList arrayList3 = new ArrayList();
        for (int i5 = 1; i5 < arrayList2.size(); i5++) {
            String replaceAll = str.substring(((Integer) arrayList2.get(i5 - 1)).intValue() + 1, ((Integer) arrayList2.get(i5)).intValue()).trim().replaceAll("\\\\:", ":");
            if (!replaceAll.isEmpty()) {
                arrayList3.add(replaceAll);
            }
        }
        return (String[]) arrayList3.toArray(new String[0]);
    }

    private static void appendPathElt(Object obj, StringBuilder sb) {
        if (sb.length() > 0) {
            sb.append(File.pathSeparatorChar);
        }
        sb.append(File.separatorChar == '\\' ? obj.toString() : obj.toString().replaceAll(File.pathSeparator, "\\" + File.pathSeparator));
    }

    public static String pathElementsToPathStr(Object... objArr) {
        StringBuilder sb = new StringBuilder();
        for (Object obj : objArr) {
            appendPathElt(obj, sb);
        }
        return sb.toString();
    }

    public static String pathElementsToPathStr(Iterable<?> iterable) {
        StringBuilder sb = new StringBuilder();
        Iterator<?> it = iterable.iterator();
        while (it.hasNext()) {
            appendPathElt(it.next(), sb);
        }
        return sb.toString();
    }

    public static String leafName(String str) {
        int indexOf = str.indexOf(33);
        int length = indexOf >= 0 ? indexOf : str.length();
        int lastIndexOf = 1 + (File.separatorChar == '/' ? str.lastIndexOf(47, length) : Math.max(str.lastIndexOf(47, length), str.lastIndexOf(File.separatorChar, length)));
        int indexOf2 = str.indexOf(NestedJarHandler.TEMP_FILENAME_LEAF_SEPARATOR);
        int i = indexOf2;
        if (indexOf2 >= 0) {
            i += 3;
        }
        return str.substring(Math.min(Math.max(lastIndexOf, i), length), length);
    }

    public static String classfilePathToClassName(String str) {
        if (!str.endsWith(".class")) {
            throw new IllegalArgumentException("Classfile path does not end with \".class\": " + str);
        }
        return str.substring(0, str.length() - 6).replace('/', '.');
    }

    public static String classNameToClassfilePath(String str) {
        return str.replace('.', '/') + ".class";
    }

    public static String derivedAutomaticModuleName(String str) {
        int length = str.length();
        int lastIndexOf = str.lastIndexOf(33);
        if (lastIndexOf > 0 && str.lastIndexOf(46) <= Math.max(lastIndexOf, str.lastIndexOf(47))) {
            length = lastIndexOf;
        }
        int max = Math.max(length == 0 ? -1 : str.lastIndexOf("!", length - 1), str.lastIndexOf(47, length - 1)) + 1;
        int lastIndexOf2 = str.lastIndexOf(46, length - 1);
        if (lastIndexOf2 > max) {
            length = lastIndexOf2;
        }
        String substring = str.substring(max, length);
        Matcher matcher = DASH_VERSION.matcher(substring);
        if (matcher.find()) {
            substring = substring.substring(0, matcher.start());
        }
        String replaceAll = REPEATING_DOTS.matcher(NON_ALPHANUM.matcher(substring).replaceAll(".")).replaceAll(".");
        String str2 = replaceAll;
        if (replaceAll.length() > 0 && str2.charAt(0) == '.') {
            str2 = LEADING_DOTS.matcher(str2).replaceAll("");
        }
        int length2 = str2.length();
        if (length2 > 0 && str2.charAt(length2 - 1) == '.') {
            str2 = TRAILING_DOTS.matcher(str2).replaceAll("");
        }
        return str2;
    }
}
