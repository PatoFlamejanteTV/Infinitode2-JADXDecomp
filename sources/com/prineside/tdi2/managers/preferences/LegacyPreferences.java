package com.prineside.tdi2.managers.preferences;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Null;
import com.prineside.kryo.FixedInput;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.utils.ObjectPair;
import com.prineside.tdi2.utils.StringFormatter;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.html.Attribute;
import java.util.HashMap;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/preferences/LegacyPreferences.class */
public class LegacyPreferences {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2509a = TLog.forClass(LegacyPreferences.class);

    /* renamed from: b, reason: collision with root package name */
    private final byte[] f2510b;
    private final FixedInput c = new FixedInput();

    public LegacyPreferences() {
        String str = Game.i.actionResolver.getDeviceInfo().get(Attribute.ID_ATTR);
        String str2 = str;
        if (str != null && str2.length() >= 4) {
            char[] charArray = (str2.length() > 64 ? str2.substring(0, 64) : str2).toCharArray();
            byte[] bArr = new byte[charArray.length];
            for (int i = 0; i < charArray.length; i++) {
                bArr[i] = (byte) (charArray[i] - 'n');
            }
            this.f2510b = bArr;
            return;
        }
        this.f2510b = new byte[]{14, 1, 93, -7, -71, -29, 98, 39};
    }

    /* JADX WARN: Type inference failed for: r1v19, types: [S, java.util.HashMap] */
    @Null
    public Array<ObjectPair<String, HashMap<String, String>>> getLocallyStoredPrefs() {
        f2509a.i("searching for a legacy properties (" + Config.LEGACY_PREFERENCES_NAME_PREFIX + "*)", new Object[0]);
        Array<ObjectPair<String, HashMap<String, String>>> array = null;
        for (String str : Config.LEGACY_PREFERENCES_NAMES) {
            SafePreferences propertiesInstance = getPropertiesInstance(str);
            if (propertiesInstance.count() != 0) {
                if (array == null) {
                    array = new Array<>(true, 1, ObjectPair.class);
                }
                ObjectPair<String, HashMap<String, String>> objectPair = new ObjectPair<>();
                if (Config.LEGACY_PREFERENCES_NAME_PROGRESS.equals(str)) {
                    objectPair.first = "Progress";
                } else if (Config.LEGACY_PREFERENCES_NAME_SETTINGS.equals(str)) {
                    objectPair.first = "Settings";
                } else if (Config.LEGACY_PREFERENCES_NAME_STATISTICS.equals(str)) {
                    objectPair.first = "Statistics";
                } else if (Config.LEGACY_PREFERENCES_NAME_USER_MAPS.equals(str)) {
                    objectPair.first = "UserMaps";
                } else {
                    throw new IllegalArgumentException("Invalid preferences name: " + str);
                }
                objectPair.second = propertiesInstance.getAll();
                array.add(objectPair);
            }
        }
        return array;
    }

    public boolean has1dot8prefs() {
        Array<ObjectPair<String, HashMap<String, String>>> locallyStoredPrefs = getLocallyStoredPrefs();
        return (locallyStoredPrefs == null || locallyStoredPrefs.size == 0) ? false : true;
    }

    public boolean has1dot9migrationFlag() {
        Array<ObjectPair<String, HashMap<String, String>>> locallyStoredPrefs = getLocallyStoredPrefs();
        if (locallyStoredPrefs == null || locallyStoredPrefs.size == 0) {
            return false;
        }
        for (int i = 0; i < locallyStoredPrefs.size; i++) {
            ObjectPair<String, HashMap<String, String>> objectPair = locallyStoredPrefs.get(i);
            if (objectPair.first.equals("Progress")) {
                if ("true".equals(objectPair.second.get("_migrated_1_9_0"))) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    /* JADX WARN: Type inference failed for: r0v27, types: [S, java.util.HashMap] */
    public Array<ObjectPair<String, HashMap<String, String>>> fromBytes(byte[] bArr, int i, int i2) {
        Array<ObjectPair<String, HashMap<String, String>>> array = new Array<>(true, 1, ObjectPair.class);
        this.c.setBuffer(StringFormatter.fromCompactBytes(bArr, i, i2).toByteArray());
        int readInt = this.c.readInt();
        for (int i3 = 0; i3 < readInt; i3++) {
            String readString = this.c.readString();
            ObjectPair<String, HashMap<String, String>> objectPair = new ObjectPair<>();
            if (Config.LEGACY_PREFERENCES_NAME_PROGRESS.equals(readString)) {
                objectPair.first = "Progress";
            } else if (Config.LEGACY_PREFERENCES_NAME_SETTINGS.equals(readString)) {
                objectPair.first = "Settings";
            } else if (Config.LEGACY_PREFERENCES_NAME_STATISTICS.equals(readString)) {
                objectPair.first = "Statistics";
            } else if (Config.LEGACY_PREFERENCES_NAME_USER_MAPS.equals(readString)) {
                objectPair.first = "UserMaps";
            } else {
                f2509a.e("unrecognized legacy properties type, skipped: " + readString, new Object[0]);
            }
            ?? hashMap = new HashMap();
            int readInt2 = this.c.readInt();
            for (int i4 = 0; i4 < readInt2; i4++) {
                String readString2 = this.c.readString();
                String readString3 = this.c.readString();
                if (readString3 != null) {
                    hashMap.put(readString2, readString3);
                }
            }
            objectPair.second = hashMap;
            array.add(objectPair);
        }
        return array;
    }

    public Array<ObjectPair<String, HashMap<String, String>>> fromCompactBase64(String str) {
        byte[] fromBase64 = StringFormatter.fromBase64(str);
        return fromBytes(fromBase64, 0, fromBase64.length);
    }

    public SafePreferences getPropertiesInstance(String str) {
        if (Config.isModdingMode()) {
            str = str + ".mod-" + Config.getModId();
        }
        return new SafePreferences(str, this.f2510b);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/preferences/LegacyPreferences$SafePreferences.class */
    public static class SafePreferences {

        /* renamed from: a, reason: collision with root package name */
        private final Preferences f2511a;

        /* renamed from: b, reason: collision with root package name */
        private final byte[] f2512b;

        protected SafePreferences(String str, byte[] bArr) {
            this.f2511a = Gdx.app.getPreferences(str);
            this.f2512b = bArr;
        }

        public int count() {
            return this.f2511a.get().size();
        }

        public HashMap<String, String> getAll() {
            HashMap<String, String> hashMap = new HashMap<>();
            synchronized (this.f2511a) {
                for (Map.Entry<String, ?> entry : this.f2511a.get().entrySet()) {
                    if (!(entry.getValue() instanceof String)) {
                        LegacyPreferences.f2509a.i("getAll - invalid data type for key '" + entry.getKey() + "' : " + entry.getValue().getClass().getName(), new Object[0]);
                    } else {
                        hashMap.put(entry.getKey(), get(entry.getKey(), ""));
                    }
                }
            }
            return hashMap;
        }

        public void set(String str, String str2) {
            synchronized (this.f2511a) {
                if (Config.preferencesEncryptionEnabled()) {
                    this.f2511a.putString(str, StringFormatter.stringToCompactBase64(str2));
                } else {
                    this.f2511a.putString(str, str2);
                }
            }
        }

        public String get(String str, String str2) {
            synchronized (this.f2511a) {
                if (this.f2511a.contains(str)) {
                    if (Config.preferencesEncryptionEnabled()) {
                        String string = this.f2511a.getString(str);
                        try {
                            return StringFormatter.stringFromCompactBase64(string);
                        } catch (Exception unused) {
                            String str3 = str2;
                            try {
                                str3 = new String(Base64Coder.decode(a(string, this.f2512b)));
                            } catch (Exception e) {
                                LegacyPreferences.f2509a.e("Unable to decode Base64 for key '" + str + "': " + e.getMessage() + " (" + string + ")", e);
                                this.f2511a.remove(str);
                            }
                            return str3;
                        }
                    }
                    return this.f2511a.getString(str);
                }
                return str2;
            }
        }

        public void clear() {
            this.f2511a.clear();
            this.f2511a.flush();
        }

        public void flush() {
            this.f2511a.flush();
        }

        private static char a(char c, int i) {
            return (char) ((((((c - 32) + i) % 94) + 94) % 94) + 32);
        }

        private static String a(String str, byte[] bArr) {
            if (Gdx.app.getType() == Application.ApplicationType.iOS) {
                return str;
            }
            char[] charArray = str.toCharArray();
            int i = 0;
            for (int i2 = 0; i2 < charArray.length; i2++) {
                int i3 = i2;
                charArray[i2] = a(charArray[i2], -(((i3 + i3) + charArray.length) - bArr[i]));
                i++;
                if (i == bArr.length) {
                    i = 0;
                }
            }
            return new String(charArray);
        }
    }
}
