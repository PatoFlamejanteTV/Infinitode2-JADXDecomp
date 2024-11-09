package com.prineside.tdi2.desktop.luadef;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectSet;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.utils.AEC;
import com.prineside.tdi2.utils.ReflectionUtils;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/desktop/luadef/LuaDefUtils.class */
public class LuaDefUtils {
    public static final ObjectSet<String> LUA_KEYWORDS;
    public static final ObjectMap<Class<?>, String> DEFAULT_LUA_CLASS_NAMES;
    public static final ObjectMap<Class<?>, String> DEFAULT_LUA_CLASS_NAMES_WITH_CLASS;
    public static final ObjectMap<Class<?>, String> LUA_PRIMITIVE_TYPE_EXTRA_INFO;

    /* renamed from: a, reason: collision with root package name */
    private static final HashMap<String, String> f1849a;

    /* renamed from: b, reason: collision with root package name */
    private static final Map<Class<?>, Array<Field>> f1850b;
    private static final Map<Class<?>, Array<Constructor<?>>> c;
    private static final Map<Class<?>, Array<Method>> d;

    static {
        ObjectSet<String> objectSet = new ObjectSet<>();
        LUA_KEYWORDS = objectSet;
        objectSet.addAll("and", "break", "do", "else", "elseif", "end", "false", "for", "function", "if", "in", "local", "nil", "not", "or", "repeat", "return", "then", "true", "until", "while");
        ObjectMap<Class<?>, String> objectMap = new ObjectMap<>();
        DEFAULT_LUA_CLASS_NAMES = objectMap;
        objectMap.put(Double.TYPE, "number");
        DEFAULT_LUA_CLASS_NAMES.put(Float.TYPE, "number");
        DEFAULT_LUA_CLASS_NAMES.put(Long.TYPE, "number");
        DEFAULT_LUA_CLASS_NAMES.put(Boolean.TYPE, "boolean");
        DEFAULT_LUA_CLASS_NAMES.put(Integer.TYPE, "number");
        DEFAULT_LUA_CLASS_NAMES.put(Short.TYPE, "number");
        DEFAULT_LUA_CLASS_NAMES.put(Void.TYPE, "nil");
        DEFAULT_LUA_CLASS_NAMES.put(Character.TYPE, "number");
        DEFAULT_LUA_CLASS_NAMES.put(Byte.TYPE, "number");
        ObjectMap<Class<?>, String> objectMap2 = new ObjectMap<>();
        DEFAULT_LUA_CLASS_NAMES_WITH_CLASS = objectMap2;
        objectMap2.put(Double.TYPE, "number");
        DEFAULT_LUA_CLASS_NAMES_WITH_CLASS.put(Double.class, "number");
        DEFAULT_LUA_CLASS_NAMES_WITH_CLASS.put(Float.TYPE, "number");
        DEFAULT_LUA_CLASS_NAMES_WITH_CLASS.put(Float.class, "number");
        DEFAULT_LUA_CLASS_NAMES_WITH_CLASS.put(Long.TYPE, "number");
        DEFAULT_LUA_CLASS_NAMES_WITH_CLASS.put(Long.class, "number");
        DEFAULT_LUA_CLASS_NAMES_WITH_CLASS.put(Boolean.TYPE, "boolean");
        DEFAULT_LUA_CLASS_NAMES_WITH_CLASS.put(Boolean.class, "boolean");
        DEFAULT_LUA_CLASS_NAMES_WITH_CLASS.put(String.class, "string");
        DEFAULT_LUA_CLASS_NAMES_WITH_CLASS.put(Integer.TYPE, "number");
        DEFAULT_LUA_CLASS_NAMES_WITH_CLASS.put(Integer.class, "number");
        DEFAULT_LUA_CLASS_NAMES_WITH_CLASS.put(Short.TYPE, "number");
        DEFAULT_LUA_CLASS_NAMES_WITH_CLASS.put(Short.class, "number");
        DEFAULT_LUA_CLASS_NAMES_WITH_CLASS.put(Void.TYPE, "nil");
        DEFAULT_LUA_CLASS_NAMES_WITH_CLASS.put(Void.class, "nil");
        DEFAULT_LUA_CLASS_NAMES_WITH_CLASS.put(Character.TYPE, "number");
        DEFAULT_LUA_CLASS_NAMES_WITH_CLASS.put(Character.class, "number");
        DEFAULT_LUA_CLASS_NAMES_WITH_CLASS.put(Byte.TYPE, "number");
        DEFAULT_LUA_CLASS_NAMES_WITH_CLASS.put(Byte.class, "number");
        ObjectMap<Class<?>, String> objectMap3 = new ObjectMap<>();
        LUA_PRIMITIVE_TYPE_EXTRA_INFO = objectMap3;
        objectMap3.put(Double.TYPE, "double");
        LUA_PRIMITIVE_TYPE_EXTRA_INFO.put(Double.class, "double");
        LUA_PRIMITIVE_TYPE_EXTRA_INFO.put(Float.TYPE, "float");
        LUA_PRIMITIVE_TYPE_EXTRA_INFO.put(Float.class, "float");
        LUA_PRIMITIVE_TYPE_EXTRA_INFO.put(Long.TYPE, "long");
        LUA_PRIMITIVE_TYPE_EXTRA_INFO.put(Long.class, "long");
        LUA_PRIMITIVE_TYPE_EXTRA_INFO.put(Short.TYPE, "short");
        LUA_PRIMITIVE_TYPE_EXTRA_INFO.put(Short.class, "short");
        LUA_PRIMITIVE_TYPE_EXTRA_INFO.put(Integer.TYPE, "int");
        LUA_PRIMITIVE_TYPE_EXTRA_INFO.put(Integer.class, "int");
        LUA_PRIMITIVE_TYPE_EXTRA_INFO.put(Character.TYPE, "char");
        LUA_PRIMITIVE_TYPE_EXTRA_INFO.put(Character.class, "char");
        LUA_PRIMITIVE_TYPE_EXTRA_INFO.put(Byte.TYPE, "byte");
        LUA_PRIMITIVE_TYPE_EXTRA_INFO.put(Byte.class, "byte");
        HashMap<String, String> hashMap = new HashMap<>();
        f1849a = hashMap;
        hashMap.put("00", "><(((('>");
        f1849a.put("10", "(-(-_(-_-)_-)-)");
        f1849a.put("20", "@( * O * )@");
        f1849a.put("30", "@('_')@");
        f1849a.put("40", "°º¤ø,¸¸,ø¤º°`°º¤ø,¸,ø¤°º¤ø,¸¸,ø¤º°`°º¤ø,¸");
        f1849a.put("50", "--------{---(@");
        f1849a.put("60", "<*_*>");
        f1849a.put("70", "(-.-)Zzz...");
        f1849a.put("80", "[{-_-}] ZZZzz zz z...");
        f1849a.put("90", ",.-~*´¨¯¨`*·~-.¸-(_FixIt_)-,.-~*´¨¯¨`*·~-.¸");
        f1849a.put("a0", ")xxxxx[;;;;;;;;;>");
        f1849a.put("b0", "c[_]");
        f1849a.put("c0", "d[ o_0 ]b");
        f1849a.put("d0", "^(*(oo)*)^");
        f1849a.put("e0", "=^..^=");
        f1849a.put("f0", "¸.·´¯`·.´¯`·.¸¸.·´¯`·.¸><(((º>");
        f1849a.put("01", "(===||:::::::::::::::>");
        f1849a.put("02", "\\,,/(^_^)\\,,/");
        f1849a.put("03", "'''⌐(ಠ۾ಠ)¬'''");
        f1849a.put("04", " ‛¯¯٭٭¯¯(▫▫)¯¯٭٭¯¯’");
        f1849a.put("05", "|[●▪▪●]|");
        f1849a.put("06", "∙،°.  ˘Ô≈ôﺣ   » » »");
        f1849a.put("07", " c[○┬●]כ ");
        f1849a.put("08", "(♥_♥)");
        f1849a.put("09", "龴ↀ◡ↀ龴");
        f1849a.put("0a", "☁ ▅▒░☼‿☼░▒▅ ☁");
        f1849a.put("0b", "^⨀ᴥ⨀^");
        f1849a.put("0c", "|'L'|");
        f1849a.put("0d", "<|º감º|>");
        f1849a.put("0e", "⎦˚◡˚⎣");
        f1849a.put("0f", "<:3 )~~~");
        f1849a.put("11", "¸¸♬·¯·♩¸¸♪·¯·♫¸¸♬·¯·♩¸¸♪·¯·♫¸¸");
        f1849a.put("12", "¯\\_(ツ)_/¯ ");
        f1849a.put("13", "(╯°□°）╯︵ ┻━┻");
        f1849a.put("14", ":Q___");
        f1849a.put("15", "~(‾▿‾)~");
        f1849a.put("16", "(òÓ,)_\\,,/");
        f1849a.put("17", "༼☉ɷ⊙༽");
        f1849a.put("18", "(<(<>(<>.(<>..<>).<>)<>)>)");
        f1849a.put("19", "( (8 ())");
        f1849a.put("1a", "~~(__^·>");
        f1849a.put("1b", "c====(=#O| ) ~~ ♬·¯·♩¸¸♪·¯·♫¸ ");
        f1849a.put("1c", "<(''<)  <( ' ' )>  (> '')>");
        f1849a.put("1d", "∙∙∙∙∙·▫▫ᵒᴼᵒ▫ₒₒ▫ᵒᴼᵒ▫ₒₒ▫ᵒᴼᵒ☼)===>");
        f1849a.put("1e", "‹’’›(Ͼ.Ͽ)‹’’›   ");
        f1849a.put("1f", "(⌒▽⌒)");
        f1849a.put("21", "|-o-| (-o-) |-o-|");
        f1849a.put("22", "(¯`·._.··¸.-~*´¨¯¨`*·~-.,-(_ignore this_)-,.-~*´¨¯¨`*·~-.¸··._.·´¯)");
        f1849a.put("23", "°j°m");
        f1849a.put("24", "(/.__.)/   \\(.__.\\)");
        f1849a.put("25", "(>'-')> <('_'<) ^('_')\\- \\m/(-_-)\\m/ <( '-')> \\_( .\")> <( ._.)-`");
        f1849a.put("26", "@_'-'");
        f1849a.put("27", "@}~}~~~");
        f1849a.put("28", "d[-_-]b");
        f1849a.put("29", "'(◣_◢)'");
        f1849a.put("2a", "^(;,;)^");
        f1849a.put("2b", "◖(◣☩◢)◗");
        f1849a.put("2c", " ლ(ಠ益ಠ)ლ");
        f1849a.put("2d", "/)^3^(\\");
        f1849a.put("2e", "(\\/)(Ö,,,,Ö)(\\/)");
        f1849a.put("2f", "✈");
        f1849a.put("31", "ε(๏_๏)з】");
        f1849a.put("32", "-_-");
        f1849a.put("33", "(ಠ_ಠ)");
        f1849a.put("34", "d(^o^)b");
        f1849a.put("35", "\\(^-^)/");
        f1849a.put("36", "(ノಠ益ಠ)ノ彡");
        f1849a.put("37", "┏(-_-)┛┗(-_-)┓┗(-_-)┛┏(-_-)┓");
        f1849a.put("38", "-^^,--,~");
        f1849a.put("39", "(,,,)=(^.^)=(,,,)");
        f1849a.put("3a", "♡");
        f1849a.put("3b", "O=('-'Q)");
        f1849a.put("3c", "d(^o^)b¸¸♬·¯·♩¸¸♪·¯·♫¸¸");
        f1849a.put("3d", "~~~~~~~~*\\o/~~~~~/\\*~~~~~~~");
        f1849a.put("3e", "ˁ(⦿ᴥ⦿)ˀ");
        f1849a.put("3f", "( •_•)O*¯`·.¸.·´¯`°Q(•_• )");
        f1849a.put("41", "┻━┻︵  \\(°□°)/ ︵ ┻━┻ ");
        f1849a.put("42", "/X\\('-')/X\\");
        f1849a.put("43", "[::]");
        f1849a.put("44", "[===]-'");
        f1849a.put("45", "༼ つ ◕_◕ ༽つ ");
        f1849a.put("46", "-=iii=<()");
        f1849a.put("47", "(,(,(,(,(,(,(,(, \")");
        f1849a.put("48", "┏━┓ ︵  /(^.^/)");
        f1849a.put("49", "/-.-\\");
        f1849a.put("4a", "┬┴┬┴┤･ω･)ﾉ├┬┴┬┴");
        f1849a.put("4b", "(\\_/) ");
        f1849a.put("4c", "\\m/_(>_<)_\\m/");
        f1849a.put("4d", "(ಠ_x) ༼☉");
        f1849a.put("4e", "ᒡ◯ᵔ◯ᒢ");
        f1849a.put("4f", "≧◔◡◔≦");
        f1849a.put("51", "xP");
        f1849a.put("52", "Made in Zeraco's basement");
        f1849a.put("53", "Don't fuck with the server");
        f1849a.put("54", "A great explanation of class: https://www.youtube.com/watch?v=dQw4w9WgXcQ");
        f1850b = Collections.synchronizedMap(new HashMap());
        c = Collections.synchronizedMap(new HashMap());
        d = Collections.synchronizedMap(new HashMap());
    }

    public static String getLuaPrimitiveType(Class<?> cls) {
        String str = LUA_PRIMITIVE_TYPE_EXTRA_INFO.get(cls);
        if (str != null) {
            return " (" + str + ")";
        }
        return "";
    }

    public static void createDirs(String str) {
        String[] split = str.split("/");
        for (int i = 1; i < split.length; i++) {
            Array array = new Array(true, i, String.class);
            for (int i2 = 0; i2 < i; i2++) {
                array.add(split[i2]);
            }
            try {
                new File(array.toString("/")).mkdir();
            } catch (Exception unused) {
            }
        }
    }

    public static void writeFile(String str, String str2) {
        createDirs(str);
        try {
            PrintStream printStream = new PrintStream((OutputStream) new FileOutputStream(str), false, "UTF-8");
            try {
                printStream.print(str2);
                printStream.flush();
                printStream.close();
            } finally {
            }
        } catch (Exception e) {
            throw new IllegalStateException("Failed to write file " + str, e);
        }
    }

    public static void appendArt(String str, StringBuilder stringBuilder) {
        String str2;
        char charAt = str.charAt(5);
        if ((charAt == 'a' || charAt == '7' || charAt == '0' || charAt == 'd') && (str2 = f1849a.get(str.substring(0, 2).toLowerCase(Locale.US))) != null) {
            stringBuilder.append(SequenceUtils.EOL).append("-- ").append(str2).append(SequenceUtils.EOL);
        }
    }

    public static Array<Field> gatherFieldsCached(Class<?> cls) {
        Array<Field> array = f1850b.get(cls);
        if (array != null) {
            return array;
        }
        Array<Field> gatherFields = ReflectionUtils.LuaRelated.gatherFields(cls);
        f1850b.put(cls, gatherFields);
        return gatherFields;
    }

    public static Array<Constructor<?>> gatherConstructorsCached(Class<?> cls) {
        Array<Constructor<?>> array = c.get(cls);
        if (array != null) {
            return array;
        }
        Array<Constructor<?>> gatherConstructors = ReflectionUtils.LuaRelated.gatherConstructors(cls);
        c.put(cls, gatherConstructors);
        return gatherConstructors;
    }

    public static Array<Method> gatherMethodsCached(Class<?> cls) {
        Array<Method> array = d.get(cls);
        if (array != null) {
            return array;
        }
        Array<Method> gatherMethods = ReflectionUtils.LuaRelated.gatherMethods(cls);
        d.put(cls, gatherMethods);
        return gatherMethods;
    }

    public static void fancyWarning(String str, String str2, Throwable th) {
        String str3 = "\u001b[33m/!\\ " + str2 + AEC.RESET;
        if (th == null) {
            TLog.forTag(str).i(str, str3);
        } else {
            TLog.forTag(str).i(str, str3, th);
        }
    }
}
