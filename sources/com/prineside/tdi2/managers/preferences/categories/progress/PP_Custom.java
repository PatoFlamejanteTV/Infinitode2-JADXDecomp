package com.prineside.tdi2.managers.preferences.categories.progress;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.Null;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.managers.preferences.PrefMap;
import com.prineside.tdi2.managers.preferences.PrefSubcategory;
import com.prineside.tdi2.utils.logging.TLog;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/preferences/categories/progress/PP_Custom.class */
public final class PP_Custom implements PrefSubcategory {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2523a = TLog.forClass(PP_Custom.class);
    public static final int MAX_ENTRIES = 256;
    public static final int MAX_ENTRY_LENGTH = 131072;

    /* renamed from: b, reason: collision with root package name */
    private final ConcurrentHashMap<String, String> f2524b = new ConcurrentHashMap<>();

    public final synchronized void setValue(String str, @Null String str2) {
        Preconditions.checkNotNull(str, "key can not be null");
        if (str2 == null) {
            this.f2524b.remove(str);
        } else {
            if (this.f2524b.size() >= 256 && !this.f2524b.contains(str)) {
                throw new IllegalStateException("Can't add a new entry - too many custom entries are stored, remove some other value first");
            }
            if (str2.length() > 131072) {
                throw new IllegalArgumentException("Value is too long - max string length is defined as MAX_ENTRY_LENGTH. Split your data or reduce its size somehow");
            }
            this.f2524b.put(str, str2);
        }
    }

    @Null
    public final String getValue(String str, @Null String str2) {
        Preconditions.checkNotNull(str, "key can not be null");
        String str3 = this.f2524b.get(str);
        if (str3 != null) {
            return str3;
        }
        return str2;
    }

    public final synchronized String[] getKeys() {
        String[] strArr = new String[this.f2524b.size()];
        int i = 0;
        Iterator<Map.Entry<String, String>> it = this.f2524b.entrySet().iterator();
        while (it.hasNext()) {
            int i2 = i;
            i++;
            strArr[i2] = it.next().getKey();
        }
        return strArr;
    }

    @Override // com.prineside.tdi2.managers.preferences.PrefSubcategory
    public final void load(PrefMap prefMap) {
        this.f2524b.clear();
        String str = prefMap.get("customData", null);
        if (str != null) {
            try {
                Iterator<JsonValue> iterator2 = new JsonReader().parse(str).iterator2();
                while (iterator2.hasNext()) {
                    JsonValue next = iterator2.next();
                    this.f2524b.put(next.name, next.asString());
                }
            } catch (Exception e) {
                f2523a.e("failed to parse customData", e);
            }
        }
    }

    @Override // com.prineside.tdi2.managers.preferences.PrefSubcategory
    public final synchronized void save(PrefMap prefMap) {
        if (this.f2524b.size() != 0) {
            Json json = new Json(JsonWriter.OutputType.minimal);
            StringWriter stringWriter = new StringWriter();
            json.setWriter(stringWriter);
            json.writeObjectStart();
            for (Map.Entry<String, String> entry : this.f2524b.entrySet()) {
                json.writeValue(entry.getKey(), entry.getValue());
            }
            json.writeObjectEnd();
            prefMap.set("customData", stringWriter.toString());
        }
    }
}
