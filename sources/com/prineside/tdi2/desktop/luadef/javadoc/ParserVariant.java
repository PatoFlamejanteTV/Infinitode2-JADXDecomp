package com.prineside.tdi2.desktop.luadef.javadoc;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.BooleanArray;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.desktop.luadef.JavadocHandler;
import com.prineside.tdi2.desktop.luadef.LuaDefUtils;
import com.prineside.tdi2.utils.AEC;
import com.prineside.tdi2.utils.ObjectPair;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/desktop/luadef/javadoc/ParserVariant.class */
public abstract class ParserVariant {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f1854a = TLog.forClass(ParserVariant.class);
    public static boolean verbose = false;
    public static boolean verboseGenerics = false;
    public static boolean verboseParamMatch = false;

    public abstract int getScore(Document document);

    @Null
    public abstract String getClassDescription(Document document);

    public abstract Array<JavadocHandler.ConstructorJD> getConstructors(Document document, Class<?> cls);

    public abstract Array<JavadocHandler.MethodJD> getMethods(Document document, Class<?> cls);

    public abstract Map<String, String> getClassGenerics(Document document, Class<?> cls);

    @Null
    public abstract String getClassGenericsString(Document document);

    public abstract Array<JavadocHandler.FieldJD> getFields(Document document, Class<?> cls);

    public String getName() {
        return getClass().getSimpleName();
    }

    public static String sanitizeHtmlString(String str) {
        return str.replaceAll("\\n", SequenceUtils.SPACE).replaceAll(" ", SequenceUtils.SPACE).replaceAll("\\s+", SequenceUtils.SPACE).trim();
    }

    public static Map<String, String> parseGenerics(String str, Class<?> cls) {
        HashMap hashMap = new HashMap();
        String sanitizeHtmlString = sanitizeHtmlString(str);
        if (sanitizeHtmlString.contains("<")) {
            if (verboseGenerics) {
                f1854a.i("//GEN// " + sanitizeHtmlString, new Object[0]);
            }
            int i = 0;
            StringBuilder stringBuilder = new StringBuilder();
            Array array = new Array();
            for (int i2 = 0; i2 < sanitizeHtmlString.length(); i2++) {
                char charAt = sanitizeHtmlString.charAt(i2);
                if (charAt == '<') {
                    i++;
                } else if (charAt == '>') {
                    if (i == 0) {
                        f1854a.w("incorrect generic declaration in page title \"" + sanitizeHtmlString + "\" of " + cls, new Object[0]);
                        return hashMap;
                    }
                    if (i == 1 && stringBuilder.length != 0) {
                        array.add(stringBuilder.toString());
                        stringBuilder.setLength(0);
                    }
                    i--;
                } else if (charAt == ',') {
                    if (i == 1 && stringBuilder.length != 0) {
                        array.add(stringBuilder.toString());
                        stringBuilder.setLength(0);
                    }
                } else if (i == 1) {
                    stringBuilder.append(charAt);
                }
            }
            if (verboseGenerics) {
                f1854a.i("parsed generics: " + array.toString("|"), new Object[0]);
            }
            splitParsedGenericsAndPutIntoMap(array, hashMap, cls);
        }
        return hashMap;
    }

    @Null
    public static String parseGenericsString(String str) {
        String sanitizeHtmlString = sanitizeHtmlString(str);
        if (sanitizeHtmlString.contains("<") && sanitizeHtmlString.contains(">")) {
            return sanitizeHtmlString.substring(sanitizeHtmlString.indexOf("<"), sanitizeHtmlString.lastIndexOf(">") + 1);
        }
        return null;
    }

    public static void splitParsedGenericsAndPutIntoMap(Array<String> array, Map<String, String> map, Class<?> cls) {
        Array.ArrayIterator<String> it = array.iterator();
        while (it.hasNext()) {
            String trim = it.next().trim();
            if (trim.contains(" extends ")) {
                String[] split = trim.split(" extends ");
                if (split.length != 2) {
                    f1854a.w("- skip generic " + trim + " - can't split, in " + cls, new Object[0]);
                } else {
                    if (verboseGenerics) {
                        f1854a.i("- add generic " + Arrays.toString(split), new Object[0]);
                    }
                    map.put(split[0].trim(), split[1].trim());
                }
            } else if (trim.length() != 1) {
                f1854a.w("- skip generic " + trim + " - not 1 char long, in " + cls, new Object[0]);
            } else {
                map.put(trim, "Object");
                map.put(trim, "java.lang.Object");
                if (verboseGenerics) {
                    f1854a.i("- add generic " + trim + " - just an Object", new Object[0]);
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Null
    public static JavadocHandler.MethodJD parseMethodSignature(String str, String str2, Class<?> cls, Map<String, String> map) {
        String sanitizeHtmlString = sanitizeHtmlString(str2);
        if (verbose) {
            f1854a.i("parseMethodSignature " + str + " | " + sanitizeHtmlString + " | " + cls, new Object[0]);
        }
        if (sanitizeHtmlString.startsWith("@Deprecated")) {
            if (verbose) {
                f1854a.i("- skip - deprecated", new Object[0]);
                return null;
            }
            return null;
        }
        if (sanitizeHtmlString.startsWith("private")) {
            if (verbose) {
                f1854a.i("- skip - private", new Object[0]);
                return null;
            }
            return null;
        }
        if (sanitizeHtmlString.startsWith("protected")) {
            if (verbose) {
                f1854a.i("- skip - protected", new Object[0]);
                return null;
            }
            return null;
        }
        Array<Method> gatherMethodsCached = LuaDefUtils.gatherMethodsCached(cls);
        if (gatherMethodsCached.size == 0) {
            if (verbose) {
                f1854a.i("- skip - no methods in this class are open to Lua", new Object[0]);
                return null;
            }
            return null;
        }
        Array array = new Array();
        for (int i = 0; i < gatherMethodsCached.size; i++) {
            Method method = gatherMethodsCached.items[i];
            if (method.getName().equals(str)) {
                array.add(method);
            }
        }
        if (array.size == 0) {
            if (verbose) {
                f1854a.i("- skip - no methods named '" + str + "' are open to Lua", new Object[0]);
                return null;
            }
            return null;
        }
        String str3 = sanitizeHtmlString.split("\\(")[0];
        BooleanArray booleanArray = new BooleanArray();
        Array array2 = new Array();
        StringBuilder stringBuilder = new StringBuilder();
        int i2 = 0;
        while (i2 < str3.length()) {
            char charAt = str3.charAt(i2);
            switch (charAt) {
                case '<':
                    booleanArray.add(i2 == 0 || str3.charAt(i2 - 1) == ' ');
                    if (stringBuilder.length != 0) {
                        array2.add(stringBuilder.toString());
                        stringBuilder.setLength(0);
                        break;
                    } else {
                        break;
                    }
                    break;
                case '>':
                    boolean pop = booleanArray.pop();
                    if (stringBuilder.length != 0) {
                        array2.add(stringBuilder.toString());
                        stringBuilder.setLength(0);
                    }
                    boolean z = false;
                    int i3 = 0;
                    while (true) {
                        if (i3 < booleanArray.size) {
                            if (!booleanArray.items[i3]) {
                                i3++;
                            } else {
                                z = true;
                            }
                        }
                    }
                    if (pop && !z) {
                        break;
                    }
                    break;
                default:
                    if (booleanArray.size != 0 && booleanArray.peek()) {
                        if (charAt == ',') {
                            if (stringBuilder.length != 0) {
                                array2.add(stringBuilder.toString());
                                stringBuilder.setLength(0);
                                break;
                            } else {
                                break;
                            }
                        } else {
                            stringBuilder.append(charAt);
                            break;
                        }
                    }
                    break;
            }
            i2++;
        }
        HashMap hashMap = new HashMap(map);
        if (array2.size != 0) {
            if (verbose) {
                f1854a.i("found " + array2.size + " method generics:", new Object[0]);
            }
            splitParsedGenericsAndPutIntoMap(array2, hashMap, cls);
        }
        JavadocHandler.MethodJD methodJD = new JavadocHandler.MethodJD();
        Array<ObjectPair<String, String>> parseMethodParams = parseMethodParams(sanitizeHtmlString);
        StringBuilder stringBuilder2 = new StringBuilder();
        Array.ArrayIterator<ObjectPair<String, String>> it = parseMethodParams.iterator();
        while (it.hasNext()) {
            ObjectPair<String, String> next = it.next();
            if (stringBuilder2.length != 0) {
                stringBuilder2.append(",");
            }
            stringBuilder2.append(next.first);
            JavadocHandler.ParamJD paramJD = new JavadocHandler.ParamJD();
            paramJD.name = next.second;
            methodJD.params.add(paramJD);
        }
        if (verbose) {
            f1854a.i("\u001b[35m- searching for: \"" + ((Object) stringBuilder2) + "\"\u001b[0m in a list of " + array.size + " methods", new Object[0]);
        }
        Array array3 = new Array();
        Array.ArrayIterator it2 = array.iterator();
        while (it2.hasNext()) {
            Method method2 = (Method) it2.next();
            Class<?>[] parameterTypes = method2.getParameterTypes();
            if (parameterTypes.length != parseMethodParams.size) {
                if (verbose) {
                    f1854a.i("- skip \u001b[34m" + method2 + "\u001b[0m - parameter count mismatch (" + parameterTypes.length + ", " + parseMethodParams.size + ")", new Object[0]);
                }
            } else {
                boolean z2 = true;
                int i4 = 0;
                while (true) {
                    if (i4 < parameterTypes.length) {
                        Class<?> cls2 = parameterTypes[i4];
                        ObjectPair<String, String> objectPair = parseMethodParams.get(i4);
                        if (a(cls2, objectPair.first, hashMap)) {
                            i4++;
                        } else {
                            if (verbose) {
                                f1854a.i("- skip \u001b[34m" + method2 + "\u001b[0m - parameter " + (i4 + 1) + " mismatch (\u001b[33m" + cls2.getSimpleName() + "\u001b[0m, " + AEC.F_CYAN + objectPair.first + "\u001b[0m)", new Object[0]);
                            }
                            z2 = false;
                        }
                    }
                }
                if (z2) {
                    if (verbose) {
                        f1854a.i("\u001b[32m- found exact match: " + method2 + " | " + ((Object) stringBuilder2) + AEC.RESET, new Object[0]);
                    }
                    array3.add(method2);
                }
            }
        }
        if (array3.size == 0) {
            f1854a.w("- none of the methods match, sig: \"" + sanitizeHtmlString + "\" in " + cls, new Object[0]);
            return null;
        }
        if (array3.size != 1) {
            f1854a.w("- multiple (" + array3.size + ") methods match, sig: \"" + sanitizeHtmlString + "\" in " + cls + ":", new Object[0]);
            Array.ArrayIterator it3 = array3.iterator();
            while (it3.hasNext()) {
                f1854a.w("- " + ((Method) it3.next()), new Object[0]);
            }
        }
        methodJD.method = (Method) array3.first();
        return methodJD;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Null
    public static JavadocHandler.ConstructorJD parseConstructorSignature(String str, Class<?> cls, Map<String, String> map) {
        String sanitizeHtmlString = sanitizeHtmlString(str);
        if (verbose) {
            f1854a.i("parseConstructorSignature " + sanitizeHtmlString + " | " + cls, new Object[0]);
        }
        if (sanitizeHtmlString.startsWith("@Deprecated")) {
            if (verbose) {
                f1854a.i("- skip - deprecated", new Object[0]);
                return null;
            }
            return null;
        }
        if (sanitizeHtmlString.startsWith("private")) {
            if (verbose) {
                f1854a.i("- skip - private", new Object[0]);
                return null;
            }
            return null;
        }
        if (sanitizeHtmlString.startsWith("protected")) {
            if (verbose) {
                f1854a.i("- skip - protected", new Object[0]);
                return null;
            }
            return null;
        }
        Array<Constructor<?>> gatherConstructorsCached = LuaDefUtils.gatherConstructorsCached(cls);
        if (gatherConstructorsCached.size == 0) {
            if (verbose) {
                f1854a.i("- skip - no constructors in this class are open to Lua", new Object[0]);
                return null;
            }
            return null;
        }
        JavadocHandler.ConstructorJD constructorJD = new JavadocHandler.ConstructorJD();
        Array<ObjectPair<String, String>> parseMethodParams = parseMethodParams(sanitizeHtmlString);
        StringBuilder stringBuilder = new StringBuilder();
        Array.ArrayIterator<ObjectPair<String, String>> it = parseMethodParams.iterator();
        while (it.hasNext()) {
            ObjectPair<String, String> next = it.next();
            if (stringBuilder.length != 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(next.first);
            JavadocHandler.ParamJD paramJD = new JavadocHandler.ParamJD();
            paramJD.name = next.second;
            constructorJD.params.add(paramJD);
        }
        if (verbose) {
            f1854a.i("\u001b[35m- searching for: \"" + ((Object) stringBuilder) + "\"\u001b[0m in a list of " + gatherConstructorsCached.size + " constructors", new Object[0]);
        }
        Array array = new Array();
        for (int i = 0; i < gatherConstructorsCached.size; i++) {
            Constructor<?> constructor = gatherConstructorsCached.items[i];
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            if (parameterTypes.length != parseMethodParams.size) {
                if (verbose) {
                    f1854a.i("- skip \u001b[34m" + constructor + "\u001b[0m - parameter count mismatch (" + parameterTypes.length + ", " + parseMethodParams.size + ")", new Object[0]);
                }
            } else {
                boolean z = true;
                int i2 = 0;
                while (true) {
                    if (i2 >= parameterTypes.length) {
                        break;
                    }
                    Class<?> cls2 = parameterTypes[i2];
                    ObjectPair<String, String> objectPair = parseMethodParams.get(i2);
                    if (a(cls2, objectPair.first, map)) {
                        i2++;
                    } else {
                        if (verbose) {
                            f1854a.i("- skip \u001b[34m" + constructor + "\u001b[0m - parameter " + (i2 + 1) + " mismatch (\u001b[33m" + cls2.getSimpleName() + "\u001b[0m, " + AEC.F_CYAN + objectPair.first + AEC.RESET, new Object[0]);
                        }
                        z = false;
                    }
                }
                if (z) {
                    if (verbose) {
                        f1854a.i("\u001b[32m- found exact match: " + constructor + " | " + ((Object) stringBuilder) + AEC.RESET, new Object[0]);
                    }
                    array.add(constructor);
                }
            }
        }
        if (array.size == 0) {
            f1854a.w("- none of the constructors match, sig: \"" + sanitizeHtmlString + "\" in " + cls, new Object[0]);
            return null;
        }
        if (array.size != 1) {
            f1854a.w("- multiple (" + array.size + ") constructors match, sig: \"" + sanitizeHtmlString + "\" in " + cls + ":", new Object[0]);
            Array.ArrayIterator it2 = array.iterator();
            while (it2.hasNext()) {
                f1854a.w("- " + ((Constructor) it2.next()), new Object[0]);
            }
        }
        constructorJD.ctor = (Constructor) array.first();
        return constructorJD;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean a(Class<?> cls, String str, Map<String, String> map) {
        if (verboseParamMatch) {
            f1854a.i("\u001b[30;1m  paramMatch " + cls.getSimpleName() + " <=> " + str + AEC.RESET, new Object[0]);
        }
        if (str.endsWith("...")) {
            if (verboseParamMatch) {
                f1854a.i("  - turn ... into array and call recursively", new Object[0]);
            }
            if (a(cls, str.substring(0, str.length() - 3) + "[]", map)) {
                return true;
            }
        }
        Array.ArrayIterator it = new Array((String[]) map.keySet().toArray(new String[0])).iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            if (str.equals(str2) || str.equals(str2 + "[]") || str.equals(str2 + "[][]") || str.equals(str2 + "[][][]")) {
                if (verboseParamMatch) {
                    f1854a.i("  - turn generic '" + str2 + "' into class name and call recursively", new Object[0]);
                }
                if (a(cls, str.replace(str2, map.get(str2)), map)) {
                    return true;
                }
            }
        }
        if (verboseParamMatch) {
            f1854a.i("\u001b[30;1m    ...check " + cls.getSimpleName() + " <=> " + str + AEC.RESET, new Object[0]);
        }
        if (cls.getSimpleName().equals(str)) {
            return true;
        }
        if (verboseParamMatch) {
            f1854a.i("\u001b[30;1m    ...check " + cls.getName() + " <=> " + str + AEC.RESET, new Object[0]);
        }
        if (cls.getName().equals(str) || cls.getName().endsWith("." + str)) {
            return true;
        }
        if (verboseParamMatch) {
            f1854a.i("\u001b[30;1m    ...check " + cls.getCanonicalName() + " <=> " + str + AEC.RESET, new Object[0]);
        }
        if (cls.getCanonicalName().equals(str) || cls.getCanonicalName().endsWith("." + str)) {
            return true;
        }
        Class<?> enclosingClass = cls.getEnclosingClass();
        if (enclosingClass != null) {
            String str3 = enclosingClass.getSimpleName() + "." + cls.getSimpleName();
            if (verboseParamMatch) {
                f1854a.i("\u001b[30;1m    ...check " + str3 + " <=> " + str + AEC.RESET, new Object[0]);
            }
            if (!str3.equals(str)) {
                String str4 = enclosingClass.getName() + "." + cls.getSimpleName();
                if (verboseParamMatch) {
                    f1854a.i("\u001b[30;1m    ...check " + str4 + " <=> " + str + AEC.RESET, new Object[0]);
                }
                if (!str4.equals(str) && !str4.endsWith("." + str)) {
                    String str5 = enclosingClass.getCanonicalName() + "." + cls.getSimpleName();
                    if (verboseParamMatch) {
                        f1854a.i("\u001b[30;1m    ...check " + str5 + " <=> " + str + AEC.RESET, new Object[0]);
                    }
                    if (str5.equals(str) || str5.endsWith("." + str)) {
                        return true;
                    }
                } else {
                    return true;
                }
            } else {
                return true;
            }
        }
        if (verboseParamMatch) {
            f1854a.i("\u001b[30;1m    no match\u001b[0m", new Object[0]);
            return false;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Array<ObjectPair<String, String>> parseMethodParams(String str) {
        String sanitizeHtmlString = sanitizeHtmlString(str);
        boolean z = false;
        int i = 0;
        StringBuilder stringBuilder = new StringBuilder();
        Array array = new Array();
        for (int i2 = 0; i2 < sanitizeHtmlString.length(); i2++) {
            char charAt = sanitizeHtmlString.charAt(i2);
            if (charAt == '(') {
                if (z) {
                    throw new IllegalArgumentException("invalid sig, two (: \"" + sanitizeHtmlString + "\"");
                }
                z = true;
            } else if (charAt == ')') {
                if (!z) {
                    throw new IllegalArgumentException("invalid sig, ) closes nothing: \"" + sanitizeHtmlString + "\"");
                }
                z = false;
                if (stringBuilder.length != 0) {
                    String trim = stringBuilder.toString().trim();
                    if (trim.length() != 0) {
                        array.add(trim);
                    }
                    stringBuilder.setLength(0);
                }
            } else if (charAt == '<') {
                i++;
            } else if (charAt == '>') {
                if (i == 0) {
                    throw new IllegalArgumentException("invalid sig, > closes no <: \"" + sanitizeHtmlString + "\"");
                }
                i--;
            } else if (charAt == ',') {
                if (i == 0 && stringBuilder.length != 0) {
                    String trim2 = stringBuilder.toString().trim();
                    if (trim2.length() != 0) {
                        array.add(trim2);
                    }
                    stringBuilder.setLength(0);
                }
            } else if (i == 0 && z) {
                stringBuilder.append(charAt);
            }
        }
        Array<ObjectPair<String, String>> array2 = new Array<>();
        Array.ArrayIterator it = array.iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            if (str2.length() == 0) {
                array2.add(new ObjectPair<>("", ""));
            } else {
                String[] split = str2.split("\\s| ");
                if (split.length < 2) {
                    throw new IllegalArgumentException("invalid param sig, single, param sig: \"" + str2 + "\", full sig: \"" + sanitizeHtmlString + "\"");
                }
                Array array3 = new Array();
                for (String str3 : split) {
                    if (!str3.startsWith("@")) {
                        array3.add(str3);
                    }
                }
                if (array3.size != 2) {
                    throw new IllegalArgumentException("invalid param sig, " + array3.size + " parts, param sig: \"" + str2 + "\", full sig: \"" + sanitizeHtmlString + "\"");
                }
                array2.add(new ObjectPair<>((String) array3.get(0), (String) array3.get(1)));
            }
        }
        return array2;
    }

    public static void parseConstructorParamDD(Element element, JavadocHandler.ConstructorJD constructorJD) {
        Element first = element.select(FlexmarkHtmlConverter.CODE_NODE).first();
        if (first != null) {
            String trim = first.text().trim();
            JavadocHandler.ParamJD paramJD = null;
            int i = 0;
            while (true) {
                if (i >= constructorJD.params.size) {
                    break;
                }
                JavadocHandler.ParamJD paramJD2 = constructorJD.params.get(i);
                if (!paramJD2.name.equals(trim)) {
                    i++;
                } else {
                    paramJD = paramJD2;
                    break;
                }
            }
            if (paramJD == null) {
                f1854a.w("documented ctor param \"" + trim + "\" not found in a list of " + constructorJD.ctor + ":", new Object[0]);
                for (int i2 = 0; i2 < constructorJD.params.size; i2++) {
                    f1854a.w("- " + constructorJD.params.get(i2).name, new Object[0]);
                }
                return;
            }
            String sanitizeHtmlString = sanitizeHtmlString(element.text().trim());
            String str = sanitizeHtmlString;
            if (sanitizeHtmlString.startsWith(trim + " -")) {
                str = str.substring(trim.length() + 2);
            } else {
                f1854a.w("can't remove ctor param name suffix from its description: \"" + str + "\" in " + constructorJD.ctor, new Object[0]);
            }
            if (str.startsWith("-")) {
                str = str.substring(1).trim();
            }
            paramJD.description = sanitizeHtmlString(str);
            if (verbose) {
                f1854a.i("/+\\ add ctor param descr", new Object[0]);
                return;
            }
            return;
        }
        f1854a.w("code block with parameter name not found in \"" + element + "\" of " + constructorJD.ctor, new Object[0]);
    }

    public static void parseMethodParamDD(Element element, JavadocHandler.MethodJD methodJD) {
        Element first = element.select(FlexmarkHtmlConverter.CODE_NODE).first();
        if (first != null) {
            String trim = first.text().trim();
            JavadocHandler.ParamJD paramJD = null;
            int i = 0;
            while (true) {
                if (i >= methodJD.params.size) {
                    break;
                }
                JavadocHandler.ParamJD paramJD2 = methodJD.params.get(i);
                if (!paramJD2.name.equals(trim)) {
                    i++;
                } else {
                    paramJD = paramJD2;
                    break;
                }
            }
            if (paramJD == null) {
                f1854a.w("documented method param \"" + trim + "\" not found in a list of " + methodJD.method + ":", new Object[0]);
                for (int i2 = 0; i2 < methodJD.params.size; i2++) {
                    f1854a.w("- " + methodJD.params.get(i2).name, new Object[0]);
                }
                return;
            }
            String sanitizeHtmlString = sanitizeHtmlString(element.text().trim());
            String str = sanitizeHtmlString;
            if (sanitizeHtmlString.startsWith(trim + " -")) {
                str = str.substring(trim.length() + 2);
            } else {
                f1854a.w("can't remove method param name suffix from its description: \"" + str + "\" in " + methodJD.method, new Object[0]);
            }
            if (str.startsWith("-")) {
                str = str.substring(1).trim();
            }
            paramJD.description = sanitizeHtmlString(str);
            if (verbose) {
                f1854a.i("/+\\ add method param descr", new Object[0]);
                return;
            }
            return;
        }
        f1854a.w("code block with parameter name not found in \"" + element + "\" of " + methodJD.method, new Object[0]);
    }

    public static JavadocHandler.MethodJD getMethodOldImpl(Element element, Map<String, String> map, Class<?> cls) {
        Element first = element.select(FlexmarkHtmlConverter.PRE_NODE).first();
        if (first == null) {
            f1854a.w("ctor sig (pre) not found in \"" + sanitizeHtmlString(element.outerHtml()) + "\" of " + cls, new Object[0]);
            return null;
        }
        String text = first.text();
        try {
            JavadocHandler.MethodJD parseMethodSignature = parseMethodSignature(sanitizeHtmlString(element.select(FlexmarkHtmlConverter.H4_NODE).first().text()), text, cls, map);
            if (parseMethodSignature != null) {
                Iterator<Element> it = element.select(".block").iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Element next = it.next();
                    if (!sanitizeHtmlString(next.text()).contains("Description copied from")) {
                        parseMethodSignature.description = JavadocHandler.formatDocumentation(next.outerHtml(), 4);
                        break;
                    }
                }
                Element first2 = element.select(FlexmarkHtmlConverter.DL_NODE).first();
                if (first2 != null) {
                    boolean z = false;
                    boolean z2 = false;
                    Iterator<Element> it2 = first2.children().iterator();
                    while (it2.hasNext()) {
                        Element next2 = it2.next();
                        if (!next2.tagName().equals(FlexmarkHtmlConverter.DT_NODE)) {
                            if (next2.tagName().equals(FlexmarkHtmlConverter.DD_NODE)) {
                                if (z) {
                                    parseMethodParamDD(next2, parseMethodSignature);
                                } else if (z2) {
                                    if (parseMethodSignature.returnDescription != null) {
                                        f1854a.w("multiple \"Returns:\" <dd> elements in " + parseMethodSignature.method, new Object[0]);
                                    } else {
                                        parseMethodSignature.returnDescription = sanitizeHtmlString(next2.text());
                                    }
                                }
                            }
                        } else {
                            Element first3 = next2.select(".paramLabel").first();
                            z = first3 != null && sanitizeHtmlString(first3.text()).equals("Parameters:");
                            z2 = next2.select(".returnLabel").first() != null;
                            Element first4 = next2.select(".overrideSpecifyLabel").first();
                            if (first4 != null) {
                                String sanitizeHtmlString = sanitizeHtmlString(first4.text());
                                if (sanitizeHtmlString.equals("Specified by:")) {
                                    parseMethodSignature.specifiedByInterface = true;
                                } else if (sanitizeHtmlString.equals("Overrides:")) {
                                    parseMethodSignature.overridesSuperMethod = true;
                                }
                            }
                        }
                    }
                } else if (verbose) {
                    f1854a.i("<dl> not found for " + parseMethodSignature.method + " - probably no parameter descriptions, will be skipped", new Object[0]);
                }
                return parseMethodSignature;
            }
            return null;
        } catch (Exception e) {
            f1854a.w("Exception while parsing method signature \"" + text + "\" of " + cls, e);
            return null;
        }
    }
}
