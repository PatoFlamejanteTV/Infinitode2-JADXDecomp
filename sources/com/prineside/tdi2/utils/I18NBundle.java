package com.prineside.tdi2.utils;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.PropertiesUtils;
import com.badlogic.gdx.utils.StreamUtils;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.utils.logging.TLog;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.bytebuddy.utility.JavaConstant;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/I18NBundle.class */
public class I18NBundle {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3838a = TLog.forClass(I18NBundle.class);

    /* renamed from: b, reason: collision with root package name */
    private static final Locale f3839b = new Locale("", "", "");
    private static boolean c = false;
    private I18NBundle d;
    private Locale e;
    private ObjectMap<String, String> f;
    private I18NTextFormatter g;
    private final Pattern h = Pattern.compile("\\[@([a-zA-Z0-9_-]+)]");
    private final ObjectMap<String, String> i = new ObjectMap<>();
    private final ObjectMap<String, String> j = new ObjectMap<>();

    private String a(String str) {
        Matcher matcher = this.h.matcher(str);
        while (matcher.find()) {
            String trim = matcher.group(1).trim();
            str = str.replace("[@" + trim + "]", Game.i.localeManager.i18n.get(trim));
        }
        if (Game.i.assetManager != null) {
            str = Game.i.assetManager.replaceRegionAliasesWithChars(str).toString();
        }
        return str;
    }

    public void registerCustom(String str, String str2) {
        String str3 = this.f.get(str);
        String str4 = str3;
        if (str3 == null) {
            if (this.d != null) {
                str4 = this.d.get(str);
            }
            if (str4 == null || str4.startsWith("???")) {
                this.i.put(str, str2);
                this.j.remove(str);
                f3838a.i("registered custom: " + str + "=" + str2, new Object[0]);
                return;
            }
        }
        f3838a.e("failed to register custom i18n value: " + str + " - default i18n can't be changed for security purposes. Default: " + str4, new Object[0]);
    }

    public static boolean getSimpleFormatter() {
        return c;
    }

    public static void setSimpleFormatter(boolean z) {
        c = z;
    }

    public static I18NBundle createBundle(FileHandle fileHandle) {
        return a(fileHandle, Locale.getDefault(), "UTF-8");
    }

    public static I18NBundle createBundle(FileHandle fileHandle, Locale locale) {
        return a(fileHandle, locale, "UTF-8");
    }

    private static I18NBundle a(FileHandle fileHandle, Locale locale, String str) {
        I18NBundle i18NBundle;
        Locale b2;
        if (fileHandle == null || locale == null) {
            throw new NullPointerException();
        }
        I18NBundle i18NBundle2 = null;
        Locale locale2 = locale;
        do {
            List<Locale> a2 = a(locale2);
            I18NBundle a3 = a(fileHandle, str, a2, 0, i18NBundle2);
            i18NBundle = a3;
            if (a3 != null) {
                Locale locale3 = i18NBundle.getLocale();
                boolean equals = locale3.equals(f3839b);
                if (!equals || locale3.equals(locale) || (a2.size() == 1 && locale3.equals(a2.get(0)))) {
                    break;
                }
                if (equals && i18NBundle2 == null) {
                    i18NBundle2 = i18NBundle;
                }
            }
            b2 = b(locale2);
            locale2 = b2;
        } while (b2 != null);
        if (i18NBundle == null) {
            if (i18NBundle2 == null) {
                throw new MissingResourceException("Can't find bundle for base file handle " + fileHandle.path() + ", locale " + locale, fileHandle + JavaConstant.Dynamic.DEFAULT_NAME + locale, "");
            }
            i18NBundle = i18NBundle2;
        }
        return i18NBundle;
    }

    private static List<Locale> a(Locale locale) {
        String language = locale.getLanguage();
        String country = locale.getCountry();
        String variant = locale.getVariant();
        ArrayList arrayList = new ArrayList(4);
        if (variant.length() > 0) {
            arrayList.add(locale);
        }
        if (country.length() > 0) {
            arrayList.add(arrayList.isEmpty() ? locale : new Locale(language, country));
        }
        if (language.length() > 0) {
            arrayList.add(arrayList.isEmpty() ? locale : new Locale(language));
        }
        arrayList.add(f3839b);
        return arrayList;
    }

    private static Locale b(Locale locale) {
        Locale locale2 = Locale.getDefault();
        if (locale.equals(locale2)) {
            return null;
        }
        return locale2;
    }

    private static I18NBundle a(FileHandle fileHandle, String str, List<Locale> list, int i, I18NBundle i18NBundle) {
        Locale locale = list.get(i);
        I18NBundle i18NBundle2 = null;
        if (i != list.size() - 1) {
            i18NBundle2 = a(fileHandle, str, list, i + 1, i18NBundle);
        } else if (i18NBundle != null && locale.equals(f3839b)) {
            return i18NBundle;
        }
        I18NBundle a2 = a(fileHandle, str, locale);
        if (a2 != null) {
            a2.d = i18NBundle2;
            return a2;
        }
        return i18NBundle2;
    }

    private static I18NBundle a(FileHandle fileHandle, String str, Locale locale) {
        I18NBundle i18NBundle = null;
        Reader reader = null;
        try {
            try {
                FileHandle a2 = a(fileHandle, locale);
                if (a(a2)) {
                    i18NBundle = new I18NBundle();
                    reader = a2.reader(str);
                    i18NBundle.a(reader);
                }
                if (i18NBundle != null) {
                    i18NBundle.c(locale);
                }
                return i18NBundle;
            } catch (IOException e) {
                throw new GdxRuntimeException(e);
            }
        } finally {
            StreamUtils.closeQuietly(reader);
        }
    }

    private static boolean a(FileHandle fileHandle) {
        try {
            fileHandle.read().close();
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private void a(Reader reader) {
        this.f = new ObjectMap<>();
        PropertiesUtils.load(this.f, reader);
    }

    private static FileHandle a(FileHandle fileHandle, Locale locale) {
        StringBuilder stringBuilder = new StringBuilder(fileHandle.name());
        if (!locale.equals(f3839b)) {
            String language = locale.getLanguage();
            String country = locale.getCountry();
            String variant = locale.getVariant();
            boolean equals = "".equals(language);
            boolean equals2 = "".equals(country);
            boolean equals3 = "".equals(variant);
            if (!equals || !equals2 || !equals3) {
                stringBuilder.append('_');
                if (!equals3) {
                    stringBuilder.append(language).append('_').append(country).append('_').append(variant);
                } else if (!equals2) {
                    stringBuilder.append(language).append('_').append(country);
                } else {
                    stringBuilder.append(language);
                }
            }
        }
        return fileHandle.sibling(stringBuilder.append(".properties").toString());
    }

    public Locale getLocale() {
        return this.e;
    }

    private void c(Locale locale) {
        this.e = locale;
        this.g = new I18NTextFormatter(locale, !c);
    }

    public boolean has(String str) {
        if (str == null) {
            throw new IllegalArgumentException("key is null");
        }
        if (this.i.containsKey(str) || this.f.get(str) != null) {
            return true;
        }
        if (this.d != null) {
            return this.d.has(str);
        }
        return false;
    }

    public String get(String str) {
        String str2;
        if (str == null) {
            throw new IllegalArgumentException("key is null");
        }
        if (this.j.containsKey(str)) {
            return this.j.get(str);
        }
        if (this.i.containsKey(str)) {
            str2 = this.i.get(str);
        } else {
            String str3 = this.f.get(str);
            str2 = str3;
            if (str3 == null && this.d != null) {
                str2 = this.d.get(str);
            }
        }
        if (str2 == null) {
            return "???" + str + "???";
        }
        String a2 = a(str2);
        this.j.put(str, a2);
        return a2;
    }

    public String formatStr(String str, Object... objArr) {
        int i = 0;
        while (str.contains("{")) {
            try {
                str = a(this.g.format(str, objArr));
            } catch (Exception e) {
                f3838a.e("failed to format the string: " + str, e);
                str = "[#FF0000]" + str + "[]";
            }
            i++;
            if (i == 3) {
                break;
            }
        }
        return str;
    }

    public String format(String str, Object... objArr) {
        return formatStr(get(str), objArr);
    }

    public void debug(String str) {
        ObjectMap.Keys<String> keys = this.f.keys();
        if (keys == null) {
            return;
        }
        ObjectMap.Keys<String> it = keys.iterator();
        while (it.hasNext()) {
            this.f.put(it.next(), str);
        }
    }
}
