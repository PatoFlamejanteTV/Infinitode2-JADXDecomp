package com.badlogic.gdx.utils;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.ObjectMap;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Set;
import net.bytebuddy.utility.JavaConstant;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/I18NBundle.class */
public class I18NBundle {
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final Locale ROOT_LOCALE = new Locale("", "", "");
    private static boolean simpleFormatter = false;
    private static boolean exceptionOnMissingKey = true;
    private I18NBundle parent;
    private Locale locale;
    private ObjectMap<String, String> properties;
    private TextFormatter formatter;

    public static boolean getSimpleFormatter() {
        return simpleFormatter;
    }

    public static void setSimpleFormatter(boolean z) {
        simpleFormatter = z;
    }

    public static boolean getExceptionOnMissingKey() {
        return exceptionOnMissingKey;
    }

    public static void setExceptionOnMissingKey(boolean z) {
        exceptionOnMissingKey = z;
    }

    public static I18NBundle createBundle(FileHandle fileHandle) {
        return createBundleImpl(fileHandle, Locale.getDefault(), DEFAULT_ENCODING);
    }

    public static I18NBundle createBundle(FileHandle fileHandle, Locale locale) {
        return createBundleImpl(fileHandle, locale, DEFAULT_ENCODING);
    }

    public static I18NBundle createBundle(FileHandle fileHandle, String str) {
        return createBundleImpl(fileHandle, Locale.getDefault(), str);
    }

    public static I18NBundle createBundle(FileHandle fileHandle, Locale locale, String str) {
        return createBundleImpl(fileHandle, locale, str);
    }

    private static I18NBundle createBundleImpl(FileHandle fileHandle, Locale locale, String str) {
        I18NBundle i18NBundle;
        Locale fallbackLocale;
        if (fileHandle == null || locale == null || str == null) {
            throw new NullPointerException();
        }
        I18NBundle i18NBundle2 = null;
        Locale locale2 = locale;
        do {
            List<Locale> candidateLocales = getCandidateLocales(locale2);
            I18NBundle loadBundleChain = loadBundleChain(fileHandle, str, candidateLocales, 0, i18NBundle2);
            i18NBundle = loadBundleChain;
            if (loadBundleChain != null) {
                Locale locale3 = i18NBundle.getLocale();
                boolean equals = locale3.equals(ROOT_LOCALE);
                if (!equals || locale3.equals(locale) || (candidateLocales.size() == 1 && locale3.equals(candidateLocales.get(0)))) {
                    break;
                }
                if (equals && i18NBundle2 == null) {
                    i18NBundle2 = i18NBundle;
                }
            }
            fallbackLocale = getFallbackLocale(locale2);
            locale2 = fallbackLocale;
        } while (fallbackLocale != null);
        if (i18NBundle == null) {
            if (i18NBundle2 == null) {
                throw new MissingResourceException("Can't find bundle for base file handle " + fileHandle.path() + ", locale " + locale, fileHandle + JavaConstant.Dynamic.DEFAULT_NAME + locale, "");
            }
            i18NBundle = i18NBundle2;
        }
        return i18NBundle;
    }

    private static List<Locale> getCandidateLocales(Locale locale) {
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
        arrayList.add(ROOT_LOCALE);
        return arrayList;
    }

    private static Locale getFallbackLocale(Locale locale) {
        Locale locale2 = Locale.getDefault();
        if (locale.equals(locale2)) {
            return null;
        }
        return locale2;
    }

    private static I18NBundle loadBundleChain(FileHandle fileHandle, String str, List<Locale> list, int i, I18NBundle i18NBundle) {
        Locale locale = list.get(i);
        I18NBundle i18NBundle2 = null;
        if (i != list.size() - 1) {
            i18NBundle2 = loadBundleChain(fileHandle, str, list, i + 1, i18NBundle);
        } else if (i18NBundle != null && locale.equals(ROOT_LOCALE)) {
            return i18NBundle;
        }
        I18NBundle loadBundle = loadBundle(fileHandle, str, locale);
        if (loadBundle != null) {
            loadBundle.parent = i18NBundle2;
            return loadBundle;
        }
        return i18NBundle2;
    }

    private static I18NBundle loadBundle(FileHandle fileHandle, String str, Locale locale) {
        I18NBundle i18NBundle = null;
        Reader reader = null;
        try {
            try {
                FileHandle fileHandle2 = toFileHandle(fileHandle, locale);
                if (checkFileExistence(fileHandle2)) {
                    i18NBundle = new I18NBundle();
                    reader = fileHandle2.reader(str);
                    i18NBundle.load(reader);
                }
                if (i18NBundle != null) {
                    i18NBundle.setLocale(locale);
                }
                return i18NBundle;
            } catch (IOException e) {
                throw new GdxRuntimeException(e);
            }
        } finally {
            StreamUtils.closeQuietly(reader);
        }
    }

    private static boolean checkFileExistence(FileHandle fileHandle) {
        try {
            fileHandle.read().close();
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    protected void load(Reader reader) {
        this.properties = new ObjectMap<>();
        PropertiesUtils.load(this.properties, reader);
    }

    private static FileHandle toFileHandle(FileHandle fileHandle, Locale locale) {
        StringBuilder stringBuilder = new StringBuilder(fileHandle.name());
        if (!locale.equals(ROOT_LOCALE)) {
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
        return this.locale;
    }

    private void setLocale(Locale locale) {
        this.locale = locale;
        this.formatter = new TextFormatter(locale, !simpleFormatter);
    }

    public String get(String str) {
        String str2 = this.properties.get(str);
        String str3 = str2;
        if (str2 == null) {
            if (this.parent != null) {
                str3 = this.parent.get(str);
            }
            if (str3 == null) {
                if (exceptionOnMissingKey) {
                    throw new MissingResourceException("Can't find bundle key " + str, getClass().getName(), str);
                }
                return "???" + str + "???";
            }
        }
        return str3;
    }

    public Set<String> keys() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        ObjectMap.Keys<String> keys = this.properties.keys();
        if (keys != null) {
            ObjectMap.Keys<String> it = keys.iterator();
            while (it.hasNext()) {
                linkedHashSet.add(it.next());
            }
        }
        return linkedHashSet;
    }

    public String format(String str, Object... objArr) {
        return this.formatter.format(get(str), objArr);
    }

    public void debug(String str) {
        ObjectMap.Keys<String> keys = this.properties.keys();
        if (keys == null) {
            return;
        }
        ObjectMap.Keys<String> it = keys.iterator();
        while (it.hasNext()) {
            this.properties.put(it.next(), str);
        }
    }
}
