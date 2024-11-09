package com.prineside.tdi2.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.net.HttpParametersUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.CharArray;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.IntSet;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.PropertiesUtils;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.Manager;
import com.prineside.tdi2.Threads;
import com.prineside.tdi2.managers.PreferencesManager;
import com.prineside.tdi2.managers.preferences.categories.SettingsPrefs;
import com.prineside.tdi2.serializers.SingletonSerializer;
import com.prineside.tdi2.utils.I18NBundle;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.errorhandling.CrashHandler;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import net.bytebuddy.utility.JavaConstant;

@REGS(serializer = Serializer.class)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/LocaleManager.class */
public class LocaleManager extends Manager.ManagerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2366a = TLog.forClass(LocaleManager.class);
    public I18NBundle i18n;

    /* renamed from: b, reason: collision with root package name */
    private final Array<Locale> f2367b;
    private final Object c = new Object();
    private final DelayedRemovalArray<LocaleManagerListener> d = new DelayedRemovalArray<>();

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/LocaleManager$LocaleManagerListener.class */
    public interface LocaleManagerListener {
        void localeChanged();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/LocaleManager$Serializer.class */
    public static class Serializer extends SingletonSerializer<LocaleManager> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.prineside.tdi2.serializers.SingletonSerializer
        public LocaleManager read() {
            return Game.i.localeManager;
        }
    }

    public LocaleManager() {
        java.util.Locale locale;
        boolean z = false;
        if (!Gdx.files.local("i18n/MainBundle.properties").exists()) {
            f2366a.i("main file not found in local directory", new Object[0]);
            z = true;
        } else {
            try {
                String readLine = Gdx.files.local("i18n/MainBundle.properties").reader(1024, "UTF-8").readLine();
                if (readLine.startsWith("#B")) {
                    int parseInt = Integer.parseInt(readLine.substring(2).trim());
                    if (208 != parseInt) {
                        f2366a.i("main local file build does not match: " + parseInt + " != 208", new Object[0]);
                        z = true;
                    } else {
                        f2366a.i("main local file is good", new Object[0]);
                    }
                } else {
                    f2366a.i("main local file doesn't start with a '#B'", new Object[0]);
                    z = true;
                }
            } catch (Exception e) {
                f2366a.e("failed to read first line of local main file", e);
                z = true;
            }
        }
        if (z) {
            Iterator<JsonValue> iterator2 = new JsonReader().parse(Gdx.files.internal("res/locales.json")).iterator2();
            while (iterator2.hasNext()) {
                String asString = iterator2.next().asString();
                if (asString.startsWith("MainBundle")) {
                    try {
                        String readLine2 = Gdx.files.internal("i18n/" + asString).reader(1024, "UTF-8").readLine();
                        String readString = Gdx.files.internal("i18n/" + asString).readString("UTF-8");
                        readString = readLine2.startsWith("#B") ? readString.substring(readString.indexOf(SequenceUtils.EOL) + 1) : readString;
                        synchronized (this.c) {
                            Gdx.files.local("i18n/" + asString).writeString("#B208\n" + readString, false, "UTF-8");
                            if (asString.equals("MainBundle_id_ID.properties")) {
                                Gdx.files.local("i18n/MainBundle_in_ID.properties").writeString("#B208\n" + readString, false, "UTF-8");
                            }
                        }
                        f2366a.i("copied " + asString + " to the local directory", new Object[0]);
                    } catch (Exception unused) {
                        f2366a.e("failed to copy " + asString, new Object[0]);
                    }
                }
            }
        }
        this.f2367b = new Array<>();
        if (Config.isHeadless()) {
            this.f2367b.add(new Locale("en_US", "English"));
        } else {
            Iterator<JsonValue> iterator22 = new JsonReader().parse(Gdx.files.internal("res/locales.json")).iterator2();
            while (iterator22.hasNext()) {
                String asString2 = iterator22.next().asString();
                if (asString2.startsWith("MainBundle")) {
                    String substring = asString2.substring(10, asString2.length() - 11);
                    String str = substring;
                    if (substring.length() != 0) {
                        str = str.charAt(0) == '_' ? str.substring(1) : str;
                        String[] split = str.split(JavaConstant.Dynamic.DEFAULT_NAME);
                        if (split.length == 1) {
                            locale = new java.util.Locale(split[0]);
                        } else {
                            locale = new java.util.Locale(split[0], split[1]);
                        }
                        String str2 = locale.getDisplayLanguage() + " (" + str + ")";
                        String[] split2 = Gdx.files.local("i18n/" + asString2).readString("UTF-8").split(SequenceUtils.EOL);
                        int length = split2.length;
                        int i = 0;
                        while (true) {
                            if (i >= length) {
                                break;
                            }
                            String[] split3 = split2[i].split("=");
                            if (split3.length <= 1 || !split3[0].equals("NAME_OF_THE_LANGUAGE")) {
                                i++;
                            } else {
                                str2 = split3[1].trim();
                                break;
                            }
                        }
                        this.f2367b.add(new Locale(str, str2));
                    }
                }
            }
        }
        f2366a.i("loaded", new Object[0]);
    }

    public void downloadFreshTranslationsAsync() {
        if (Config.isHeadless()) {
            return;
        }
        Thread thread = new Thread(() -> {
            final String locale = getLocale();
            f2366a.i("locale: " + locale, new Object[0]);
            String md5Hash = StringFormatter.md5Hash(Gdx.files.local("i18n/MainBundle.properties").readString("UTF-8"));
            String str = null;
            if (!locale.equals("en_US")) {
                str = StringFormatter.md5Hash(Gdx.files.local("i18n/MainBundle_" + locale + ".properties").readString("UTF-8"));
            }
            Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
            httpRequest.setUrl(Config.SITE_URL + "/?m=api&a=downloadTranslations&v=208");
            HashMap hashMap = new HashMap();
            hashMap.put("build", "208");
            hashMap.put("locale", locale);
            hashMap.put("mainHash", md5Hash);
            if (str != null) {
                hashMap.put("selectedLocaleHash", str);
            }
            httpRequest.setContent(HttpParametersUtils.convertHttpParameters(hashMap));
            f2366a.i("downloading fresh translations for " + locale, new Object[0]);
            Gdx.f881net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() { // from class: com.prineside.tdi2.managers.LocaleManager.1
                @Override // com.badlogic.gdx.Net.HttpResponseListener
                public void handleHttpResponse(Net.HttpResponse httpResponse) {
                    String str2;
                    String resultAsString = httpResponse.getResultAsString();
                    try {
                        boolean z = false;
                        JsonValue parse = new JsonReader().parse(resultAsString);
                        if (!parse.getString("status").equals("success")) {
                            LocaleManager.f2366a.e(resultAsString, new Object[0]);
                        } else {
                            Iterator<JsonValue> iterator2 = parse.get("files").iterator2();
                            while (iterator2.hasNext()) {
                                JsonValue next = iterator2.next();
                                String string = next.getString("locale");
                                String string2 = next.getString("contents");
                                if (string.equals("en_US")) {
                                    str2 = "i18n/MainBundle.properties";
                                } else {
                                    str2 = "i18n/MainBundle_" + string + ".properties";
                                }
                                synchronized (LocaleManager.this.c) {
                                    Gdx.files.local(str2).writeString(string2, false, "UTF-8");
                                    LocaleManager.f2366a.i("updated translations for " + string, new Object[0]);
                                    z = true;
                                }
                            }
                        }
                        if (!z) {
                            LocaleManager.f2366a.i("locales are up to date", new Object[0]);
                        } else {
                            Threads.i().runOnMainThread(() -> {
                                LocaleManager.this.setLocale(LocaleManager.this.getLocale(), false);
                            });
                        }
                    } catch (Exception e) {
                        LocaleManager.f2366a.e("Failed to download fresh translations", e);
                    }
                }

                @Override // com.badlogic.gdx.Net.HttpResponseListener
                public void failed(Throwable th) {
                    LocaleManager.f2366a.e("Failed to download fresh translations for " + locale, th);
                }

                @Override // com.badlogic.gdx.Net.HttpResponseListener
                public void cancelled() {
                    LocaleManager.f2366a.e("Failed to download fresh translations for " + locale + " - canceled", new Object[0]);
                }
            });
        });
        thread.setDaemon(true);
        thread.start();
        CrashHandler.handleThreadExceptionsForgiving(thread);
    }

    public String formatNthEnemy(int i) {
        return this.i18n.format("every_nth_enemy", Integer.valueOf(i));
    }

    public String formatNthShot(int i) {
        switch (i) {
            case 2:
                return this.i18n.get("second_shot");
            case 3:
                return this.i18n.get("third_shot");
            case 4:
                return this.i18n.get("fourth_shot");
            case 5:
                return this.i18n.get("fifth_shot");
            case 6:
                return this.i18n.get("sixth_shot");
            case 7:
                return this.i18n.get("seventh_shot");
            case 8:
                return this.i18n.get("eighth_shot");
            case 9:
                return this.i18n.get("ninth_shot");
            case 10:
                return this.i18n.get("tenth_shot");
            default:
                return this.i18n.format("nth", Integer.valueOf(i));
        }
    }

    public Array<Locale> getAvailableLocales() {
        Array<Locale> array = new Array<>(Locale.class);
        array.addAll(this.f2367b);
        return array;
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void setup() {
        reload();
        Game.i.preferencesManager.addListener(new PreferencesManager.PreferencesManagerListener.PreferencesManagerListenerAdapter() { // from class: com.prineside.tdi2.managers.LocaleManager.2
            @Override // com.prineside.tdi2.managers.PreferencesManager.PreferencesManagerListener.PreferencesManagerListenerAdapter, com.prineside.tdi2.managers.PreferencesManager.PreferencesManagerListener
            public void reloaded() {
                LocaleManager.this.reload();
            }
        });
    }

    public void reload() {
        java.util.Locale locale = new java.util.Locale("en", "US");
        if (SettingsPrefs.i().locale.localeName != null) {
            String[] split = SettingsPrefs.i().locale.localeName.split(JavaConstant.Dynamic.DEFAULT_NAME);
            if (split.length == 2 && split[0].length() == 2 && split[1].length() == 2) {
                locale = new java.util.Locale(split[0], split[1]);
            } else {
                SettingsPrefs.i().locale.localeName = null;
                SettingsPrefs.i().requireSave();
                f2366a.e("invalid locale in preferences: " + Arrays.toString(split), new Object[0]);
            }
        }
        this.i18n = I18NBundle.createBundle(Gdx.files.local("i18n/MainBundle"), locale);
        f2366a.i("Locale set to: " + locale.getLanguage() + JavaConstant.Dynamic.DEFAULT_NAME + locale.getCountry() + ", real locale: " + (this.i18n.getLocale().getLanguage() + JavaConstant.Dynamic.DEFAULT_NAME + this.i18n.getLocale().getCountry()), new Object[0]);
        downloadFreshTranslationsAsync();
        c();
    }

    public void addListener(LocaleManagerListener localeManagerListener) {
        if (localeManagerListener == null) {
            throw new IllegalArgumentException("listener is null");
        }
        if (!this.d.contains(localeManagerListener, true)) {
            this.d.add(localeManagerListener);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CharArray getAllLocalesChars() {
        IntSet intSet = new IntSet();
        intSet.add(10);
        intSet.add(32);
        CharArray charArray = new CharArray();
        Array array = new Array();
        array.add("i18n/MainBundle.properties");
        for (int i = 0; i < this.f2367b.size; i++) {
            array.add("i18n/MainBundle_" + this.f2367b.get(i).alias + ".properties");
        }
        for (int i2 = 0; i2 < array.size; i2++) {
            try {
                f2366a.i("getting chars from " + ((String) array.get(i2)), new Object[0]);
                FileHandle local = Gdx.files.local((String) array.get(i2));
                ObjectMap objectMap = new ObjectMap();
                PropertiesUtils.load(objectMap, local.reader("UTF-8"));
                int i3 = 0;
                CharArray charArray2 = new CharArray();
                ObjectMap.Values it = objectMap.values().iterator();
                while (it.hasNext()) {
                    String str = (String) it.next();
                    for (char c : str.toCharArray()) {
                        if (!intSet.contains(c)) {
                            intSet.add(c);
                            charArray.add(c);
                            charArray2.add(c);
                            i3++;
                        }
                    }
                    for (char c2 : str.toUpperCase().toCharArray()) {
                        if (!intSet.contains(c2)) {
                            intSet.add(c2);
                            charArray.add(c2);
                            charArray2.add(c2);
                            i3++;
                        }
                    }
                }
                f2366a.i("added " + i3 + " chars (" + charArray2.toString("") + ")", new Object[0]);
            } catch (Exception e) {
                f2366a.e("failed to get all chars", e);
            }
        }
        charArray.sort();
        return charArray;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ObjectMap<String, CharArray> getAllLocalesCharsPerFile() {
        String substring;
        Array array = new Array();
        array.add("i18n/MainBundle.properties");
        for (int i = 0; i < this.f2367b.size; i++) {
            array.add("i18n/MainBundle_" + this.f2367b.get(i).alias + ".properties");
        }
        ObjectMap<String, CharArray> objectMap = new ObjectMap<>();
        for (int i2 = 0; i2 < array.size; i2++) {
            try {
                IntSet intSet = new IntSet();
                intSet.add(10);
                intSet.add(32);
                CharArray charArray = new CharArray();
                f2366a.i("getting chars from " + ((String) array.get(i2)), new Object[0]);
                FileHandle local = Gdx.files.local((String) array.get(i2));
                ObjectMap objectMap2 = new ObjectMap();
                PropertiesUtils.load(objectMap2, local.reader("UTF-8"));
                int i3 = 0;
                CharArray charArray2 = new CharArray();
                ObjectMap.Values it = objectMap2.values().iterator();
                while (it.hasNext()) {
                    String str = (String) it.next();
                    for (char c : str.toCharArray()) {
                        if (!intSet.contains(c)) {
                            intSet.add(c);
                            charArray.add(c);
                            charArray2.add(c);
                            i3++;
                        }
                    }
                    for (char c2 : str.toUpperCase().toCharArray()) {
                        if (!intSet.contains(c2)) {
                            intSet.add(c2);
                            charArray.add(c2);
                            charArray2.add(c2);
                            i3++;
                        }
                    }
                }
                charArray.sort();
                f2366a.i("added " + i3 + " chars (" + charArray2.toString("") + ")", new Object[0]);
                String substring2 = ((String) array.get(i2)).substring(5);
                String substring3 = substring2.substring(0, substring2.length() - 11);
                if (substring3.equals("MainBundle")) {
                    substring = "main";
                } else {
                    substring = substring3.substring(11);
                }
                objectMap.put(substring, charArray);
            } catch (Exception e) {
                f2366a.e("failed to get all chars", e);
            }
        }
        return objectMap;
    }

    private void c() {
        this.d.begin();
        int i = this.d.size;
        for (int i2 = 0; i2 < i; i2++) {
            this.d.get(i2).localeChanged();
        }
        this.d.end();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a() {
        return SettingsPrefs.i().locale.localeName != null;
    }

    public void setLocale(String str, boolean z) {
        f2366a.i("setLocale " + str, new Object[0]);
        boolean z2 = false;
        Array.ArrayIterator<Locale> it = this.f2367b.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            } else if (it.next().alias.equals(str)) {
                z2 = true;
                break;
            }
        }
        if (!z2) {
            f2366a.e("Invalid locale: " + str, new Object[0]);
            return;
        }
        SettingsPrefs.i().locale.localeName = str;
        SettingsPrefs.i().requireSave();
        String[] split = str.split(JavaConstant.Dynamic.DEFAULT_NAME);
        this.i18n = I18NBundle.createBundle(Gdx.files.local("i18n/MainBundle"), new java.util.Locale(split[0], split[1]));
        f2366a.i("Locale set to '" + str + "'", new Object[0]);
        c();
        if (z) {
            downloadFreshTranslationsAsync();
        }
    }

    @Null
    public String getLocale() {
        return SettingsPrefs.i().locale.localeName == null ? "en_US" : SettingsPrefs.i().locale.localeName;
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.prineside.tdi2.Manager
    public void test() {
        Array.ArrayIterator<Locale> it = this.f2367b.iterator();
        while (it.hasNext()) {
            Locale next = it.next();
            String[] split = next.alias.split(JavaConstant.Dynamic.DEFAULT_NAME);
            I18NBundle createBundle = I18NBundle.createBundle(Gdx.files.local("i18n/MainBundle"), new java.util.Locale(split[0], split[1]));
            try {
                FileInputStream fileInputStream = new FileInputStream(new File("i18n/MainBundle.properties"));
                Properties properties = new Properties();
                properties.load(fileInputStream);
                fileInputStream.close();
                Enumeration keys = properties.keys();
                while (keys.hasMoreElements()) {
                    String str = (String) keys.nextElement();
                    try {
                        createBundle.get(str);
                    } catch (Exception e) {
                        f2366a.e("failed to get key '" + str + "' from locale " + next.alias + " - " + e.getMessage(), new Object[0]);
                    }
                }
            } catch (Exception e2) {
                f2366a.e("failed to test locale properties", e2);
            }
        }
    }

    @Override // com.prineside.tdi2.Manager.ManagerAdapter, com.badlogic.gdx.utils.Disposable
    public void dispose() {
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/LocaleManager$Locale.class */
    public static class Locale {
        public final String name;
        public final String alias;
        public final String localeLanguage;

        public Locale(String str, String str2) {
            this.alias = str;
            this.name = str2;
            this.localeLanguage = str.split(JavaConstant.Dynamic.DEFAULT_NAME)[0];
            LocaleManager.f2366a.i("Registered '" + this.localeLanguage + "' " + str + SequenceUtils.SPACE + str2, new Object[0]);
        }
    }
}
