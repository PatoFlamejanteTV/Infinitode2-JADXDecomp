package com.vladsch.flexmark.util.misc;

import com.vladsch.flexmark.util.ast.Node;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/misc/Utils.class */
public class Utils {
    public static <T> T ifNull(T t, T t2) {
        return t == null ? t2 : t;
    }

    public static <T> T ifNullOr(T t, boolean z, T t2) {
        return (t == null || z) ? t2 : t;
    }

    public static <T> T ifNullOrNot(T t, boolean z, T t2) {
        return (t == null || !z) ? t2 : t;
    }

    public static <T> T ifNullOr(T t, Function<T, Boolean> function, T t2) {
        return (t == null || function.apply(t).booleanValue()) ? t2 : t;
    }

    public static <T> T ifNullOrNot(T t, Function<T, Boolean> function, T t2) {
        return (t == null || !function.apply(t).booleanValue()) ? t2 : t;
    }

    public static String ifNullOrEmpty(String str, String str2) {
        return (str == null || str.isEmpty()) ? str2 : str;
    }

    public static String ifNullOrBlank(String str, String str2) {
        return (str == null || isBlank(str)) ? str2 : str;
    }

    public static String ifEmpty(String str, String str2) {
        return (str == null || str.isEmpty()) ? str2 : str;
    }

    public static String ifEmpty(String str, String str2, String str3) {
        return (str == null || str.isEmpty()) ? str2 : str3;
    }

    public static String ifEmptyNullArgs(String str, String str2, String str3) {
        return (str == null || str.isEmpty()) ? str2 : str3;
    }

    public static String ifEmpty(String str, Supplier<String> supplier) {
        return (str == null || str.isEmpty()) ? supplier.get() : str;
    }

    public static String ifEmpty(String str, Supplier<String> supplier, Supplier<String> supplier2) {
        return (str == null || str.isEmpty()) ? supplier.get() : supplier2.get();
    }

    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean isWhiteSpaceNoEOL(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt != ' ' && charAt != '\t') {
                return false;
            }
        }
        return true;
    }

    public static String orEmpty(String str) {
        return str == null ? "" : str;
    }

    public static String wrapWith(String str, char c) {
        return wrapWith(str, c, c);
    }

    public static String wrapWith(String str, char c, char c2) {
        return (str == null || str.isEmpty()) ? "" : c + str + c2;
    }

    public static String wrapWith(String str, String str2) {
        return wrapWith(str, str2, str2);
    }

    public static String wrapWith(String str, String str2, String str3) {
        return (str == null || str.isEmpty()) ? "" : prefixWith(suffixWith(str, str3), str2);
    }

    public static String suffixWith(String str, char c) {
        return suffixWith(str, c, false);
    }

    public static String suffixWithEol(String str) {
        return suffixWith(str, '\n', false);
    }

    public static String suffixWith(String str, char c, boolean z) {
        if (str != null && !str.isEmpty() && !endsWith(str, String.valueOf(c), z)) {
            return str + c;
        }
        return orEmpty(str);
    }

    public static String suffixWith(String str, String str2) {
        return suffixWith(str, str2, false);
    }

    public static String suffixWith(String str, String str2, boolean z) {
        if (str != null && !str.isEmpty() && str2 != null && !str2.isEmpty() && !endsWith(str, str2, z)) {
            return str + str2;
        }
        return orEmpty(str);
    }

    public static String prefixWith(String str, char c) {
        return prefixWith(str, c, false);
    }

    public static String prefixWith(String str, char c, boolean z) {
        if (str != null && !str.isEmpty() && !startsWith(str, String.valueOf(c), z)) {
            return c + str;
        }
        return orEmpty(str);
    }

    public static String prefixWith(String str, String str2) {
        return prefixWith(str, str2, false);
    }

    public static String prefixWith(String str, String str2, boolean z) {
        if (str != null && !str.isEmpty() && str2 != null && !str2.isEmpty() && !startsWith(str, str2, z)) {
            return str2 + str;
        }
        return orEmpty(str);
    }

    public static boolean isIn(String str, String... strArr) {
        if (str == null) {
            return false;
        }
        for (String str2 : strArr) {
            if (str.equals(str2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean endsWith(String str, String... strArr) {
        return endsWith(str, false, strArr);
    }

    public static boolean endsWith(String str, boolean z, String... strArr) {
        if (str == null) {
            return false;
        }
        if (z) {
            for (String str2 : strArr) {
                if (str.length() >= str2.length() && str.substring(str.length() - str2.length()).equalsIgnoreCase(str2)) {
                    return true;
                }
            }
            return false;
        }
        for (String str3 : strArr) {
            if (str.endsWith(str3)) {
                return true;
            }
        }
        return false;
    }

    public static boolean startsWith(String str, String... strArr) {
        return startsWith(str, false, strArr);
    }

    public static boolean startsWith(String str, boolean z, String... strArr) {
        if (str == null) {
            return false;
        }
        if (z) {
            for (String str2 : strArr) {
                if (str.length() >= str2.length() && str.substring(0, str2.length()).equalsIgnoreCase(str2)) {
                    return true;
                }
            }
            return false;
        }
        for (String str3 : strArr) {
            if (str.startsWith(str3)) {
                return true;
            }
        }
        return false;
    }

    public static int count(String str, char c, int i, int i2) {
        int indexOf;
        if (str == null) {
            return 0;
        }
        int i3 = 0;
        int i4 = i;
        int min = Math.min(str.length(), i2);
        while (i4 >= 0 && i4 <= min && (indexOf = str.indexOf(c, i4)) >= 0) {
            i3++;
            i4 = indexOf + 1;
        }
        return i3;
    }

    public static int count(String str, String str2, int i, int i2) {
        int indexOf;
        if (str == null) {
            return 0;
        }
        int i3 = 0;
        int i4 = i;
        int min = Math.min(str.length(), i2);
        while (i4 >= 0 && i4 <= min && (indexOf = str.indexOf(str2, i4)) >= 0 && indexOf <= min) {
            i3++;
            i4 = indexOf + 1;
        }
        return i3;
    }

    public static String urlDecode(String str, String str2) {
        try {
            return URLDecoder.decode(str, str2 != null ? str2 : "UTF-8");
        } catch (UnsupportedEncodingException | IllegalArgumentException unused) {
            return orEmpty(str);
        }
    }

    public static String urlEncode(String str, String str2) {
        try {
            return URLEncoder.encode(str, str2 != null ? str2 : "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return orEmpty(str);
        }
    }

    public static String removePrefix(String str, char c) {
        if (str != null) {
            if (str.startsWith(String.valueOf(c))) {
                return str.substring(1);
            }
            return str;
        }
        return "";
    }

    public static String removePrefix(String str, String str2) {
        if (str != null) {
            if (str.startsWith(String.valueOf(str2))) {
                return str.substring(str2.length());
            }
            return str;
        }
        return "";
    }

    public static String removeAnyPrefix(String str, String... strArr) {
        if (str != null) {
            for (String str2 : strArr) {
                if (str.startsWith(String.valueOf(str2))) {
                    return str.substring(str2.length());
                }
            }
            return str;
        }
        return "";
    }

    public static String removePrefixIncluding(String str, String str2) {
        if (str != null) {
            int indexOf = str.indexOf(str2);
            if (indexOf != -1) {
                return str.substring(indexOf + str2.length());
            }
            return str;
        }
        return "";
    }

    public static String removeSuffix(String str, char c) {
        if (str != null) {
            if (str.endsWith(String.valueOf(c))) {
                return str.substring(0, str.length() - 1);
            }
            return str;
        }
        return "";
    }

    public static String removeSuffix(String str, String str2) {
        if (str != null) {
            if (str.endsWith(String.valueOf(str2))) {
                return str.substring(0, str.length() - str2.length());
            }
            return str;
        }
        return "";
    }

    public static String removeAnySuffix(String str, String... strArr) {
        if (str != null) {
            for (String str2 : strArr) {
                if (str.endsWith(String.valueOf(str2))) {
                    return str.substring(0, str.length() - str2.length());
                }
            }
            return str;
        }
        return "";
    }

    public static <T> List<? extends T> stringSorted(Collection<? extends T> collection, Function<T, String> function) {
        ArrayList arrayList = new ArrayList(collection);
        arrayList.sort(Comparator.comparing(function));
        return arrayList;
    }

    public static String regexGroup(String str) {
        return "(?:" + orEmpty(str) + ")";
    }

    public static boolean regionMatches(CharSequence charSequence, int i, String str, int i2, int i3, boolean z) {
        if (z) {
            for (int i4 = 0; i4 < i3; i4++) {
                if (Character.toLowerCase(charSequence.charAt(i4 + i)) != Character.toLowerCase(str.charAt(i4 + i2))) {
                    return false;
                }
            }
            return true;
        }
        for (int i5 = 0; i5 < i3; i5++) {
            if (charSequence.charAt(i5 + i) != str.charAt(i5 + i2)) {
                return false;
            }
        }
        return true;
    }

    public static boolean endsWith(CharSequence charSequence, String str, boolean z) {
        return charSequence.length() >= str.length() && regionMatches(charSequence, charSequence.length() - str.length(), str, 0, str.length(), z);
    }

    public static boolean startsWith(CharSequence charSequence, String str, boolean z) {
        return charSequence.length() >= str.length() && regionMatches(charSequence, 0, str, 0, str.length(), z);
    }

    public static String splice(String[] strArr, String str) {
        StringBuilder sb = new StringBuilder(strArr.length * (str.length() + 10));
        String str2 = "";
        for (String str3 : strArr) {
            sb.append(str2);
            str2 = str;
            sb.append(str3);
        }
        return sb.toString();
    }

    public static String getLongestCommonPrefix(String... strArr) {
        if (strArr == null || strArr.length == 0) {
            return "";
        }
        if (strArr.length == 1) {
            return strArr[0];
        }
        String str = strArr[0];
        int length = str.length();
        int length2 = strArr.length;
        for (int i = 1; i < length2; i++) {
            length = Math.min(strArr[i].length(), length);
        }
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            for (int i3 = 1; i3 < length2; i3++) {
                if (strArr[i3].charAt(i2) != charAt) {
                    return str.substring(0, i2);
                }
            }
        }
        return str.substring(0, length);
    }

    public static String getAbbreviatedText(String str, int i) {
        if (str == null) {
            return "";
        }
        if (str.length() <= i || i < 6) {
            return str;
        }
        int i2 = i / 2;
        return str.substring(0, i2) + Node.SPLICE + str.substring(str.length() - ((i - 3) - i2));
    }

    public static String splice(Collection<String> collection, String str, boolean z) {
        StringBuilder sb = new StringBuilder(collection.size() * (str.length() + 10));
        String str2 = "";
        for (String str3 : collection) {
            if ((str3 != null && !str3.isEmpty()) || !z) {
                if (!z || (!str3.startsWith(str) && !endsWith(sb.toString(), str))) {
                    sb.append(str2);
                }
                str2 = str;
                sb.append(orEmpty(str3));
            }
        }
        return sb.toString();
    }

    public static String join(String[] strArr, String str, String str2, String str3, String str4) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        for (String str5 : strArr) {
            sb.append(str3).append(str5).append(str4);
        }
        sb.append(str2);
        return sb.toString();
    }

    public static String join(Collection<String> collection, String str, String str2, String str3, String str4) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        Iterator<String> it = collection.iterator();
        while (it.hasNext()) {
            sb.append(str3).append(it.next()).append(str4);
        }
        sb.append(str2);
        return sb.toString();
    }

    public static String repeat(String str, int i) {
        if (i > 0) {
            StringBuilder sb = new StringBuilder(str.length() * i);
            while (true) {
                int i2 = i;
                i--;
                if (i2 > 0) {
                    sb.append(str);
                } else {
                    return sb.toString();
                }
            }
        } else {
            return "";
        }
    }

    public static int max(int i, int... iArr) {
        int i2 = i;
        for (int i3 : iArr) {
            if (i2 < i3) {
                i2 = i3;
            }
        }
        return i2;
    }

    public static int min(int i, int... iArr) {
        int i2 = i;
        for (int i3 : iArr) {
            if (i2 > i3) {
                i2 = i3;
            }
        }
        return i2;
    }

    public static int minLimit(int i, int... iArr) {
        return max(i, iArr);
    }

    public static int maxLimit(int i, int... iArr) {
        return min(i, iArr);
    }

    public static int rangeLimit(int i, int i2, int i3) {
        return Math.min(Math.max(i, i2), i3);
    }

    public static float max(float f, float... fArr) {
        float f2 = f;
        for (float f3 : fArr) {
            if (f2 < f3) {
                f2 = f3;
            }
        }
        return f2;
    }

    public static float min(float f, float... fArr) {
        float f2 = f;
        for (float f3 : fArr) {
            if (f2 > f3) {
                f2 = f3;
            }
        }
        return f2;
    }

    public static float minLimit(float f, float... fArr) {
        return max(f, fArr);
    }

    public static float maxLimit(float f, float... fArr) {
        return min(f, fArr);
    }

    public static float rangeLimit(float f, float f2, float f3) {
        return Math.min(Math.max(f, f2), f3);
    }

    public static int compare(Number number, Number number2) {
        if (number == null && number2 == null) {
            return 0;
        }
        if (number == null) {
            return -1;
        }
        if (number2 == null) {
            return 1;
        }
        return ((number instanceof Double) || (number2 instanceof Double) || (number instanceof Float) || (number2 instanceof Float)) ? Double.compare(number.doubleValue(), number2.doubleValue()) : Long.compare(number.longValue(), number2.longValue());
    }

    public static <T extends Comparable<T>> int compareNullable(T t, T t2) {
        if (t == null || t2 == null) {
            return 0;
        }
        return t.compareTo(t2);
    }

    public static <K, V> V putIfMissing(Map<K, V> map, K k, Supplier<V> supplier) {
        V v = map.get(k);
        V v2 = v;
        if (v == null) {
            v2 = supplier.get();
            map.put(k, v2);
        }
        return v2;
    }

    public static <K, V> Map<K, V> withDefaults(Map<K, V> map, Map<K, V> map2) {
        HashMap hashMap = new HashMap(map);
        for (Map.Entry<K, V> entry : map2.entrySet()) {
            K key = entry.getKey();
            entry.getClass();
            putIfMissing(hashMap, key, entry::getValue);
        }
        return hashMap;
    }

    public static <K, V> void removeIf(Map<K, V> map, Function<Map.Entry<K, V>, Boolean> function) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (function.apply(entry).booleanValue()) {
                arrayList.add(entry.getKey());
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            map.remove(it.next());
        }
    }

    public static <K, V> void removeIf(Map<K, V> map, BiFunction<K, V, Boolean> biFunction) {
        removeIf(map, entry -> {
            return (Boolean) biFunction.apply(entry.getKey(), entry.getValue());
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.io.IOException, java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.io.IOException] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.io.IOException] */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v0, types: [java.lang.StringBuilder] */
    public static void streamAppend(StringBuilder sb, InputStream inputStream) {
        ?? bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        ?? r0 = bufferedReader;
        while (true) {
            try {
                try {
                    r0 = bufferedReader.readLine();
                    if (r0 != 0) {
                        r0 = sb.append(r0).append('\n');
                    } else {
                        try {
                            bufferedReader.close();
                            return;
                        } catch (IOException e) {
                            bufferedReader.printStackTrace();
                            return;
                        }
                    }
                } catch (IOException e2) {
                    r0.printStackTrace();
                    try {
                        bufferedReader.close();
                        return;
                    } catch (IOException e3) {
                        bufferedReader.printStackTrace();
                        return;
                    }
                }
            } catch (Throwable th) {
                try {
                    r0 = bufferedReader;
                    r0.close();
                } catch (IOException e4) {
                    r0.printStackTrace();
                }
                throw th;
            }
        }
    }

    public static String getResourceAsString(Class<?> cls, String str) {
        InputStream resourceAsStream = cls.getResourceAsStream(str);
        StringBuilder sb = new StringBuilder();
        streamAppend(sb, resourceAsStream);
        return sb.toString();
    }

    public static String escapeJavaString(CharSequence charSequence) {
        if (charSequence == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        escapeJavaString(sb, charSequence);
        return sb.toString();
    }

    public static String quoteJavaString(CharSequence charSequence) {
        if (charSequence == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\"");
        escapeJavaString(sb, charSequence);
        sb.append("\"");
        return sb.toString();
    }

    public static void escapeJavaString(StringBuilder sb, CharSequence charSequence) {
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            char charAt = charSequence.charAt(i);
            switch (charAt) {
                case 0:
                    sb.append("\\0");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\"':
                    sb.append("\\\"");
                    break;
                default:
                    if (charAt < ' ') {
                        sb.append('%').append(String.format("%02x", Integer.valueOf(charAt)));
                        break;
                    } else {
                        sb.append(charAt);
                        break;
                    }
            }
        }
    }

    public static <T> T getOrNull(List<T> list, int i) {
        if (i >= 0 && i < list.size()) {
            return list.get(i);
        }
        return null;
    }

    public static <T, S extends T> S getOrNull(List<T> list, int i, Class<S> cls) {
        if (i >= 0 && i < list.size()) {
            T t = list.get(i);
            if (cls.isInstance(t)) {
                return t;
            }
            return null;
        }
        return null;
    }

    public static <T> T setOrAdd(List<T> list, int i, T t) {
        if (i == list.size()) {
            list.add(t);
            return null;
        }
        return list.set(i, t);
    }
}
