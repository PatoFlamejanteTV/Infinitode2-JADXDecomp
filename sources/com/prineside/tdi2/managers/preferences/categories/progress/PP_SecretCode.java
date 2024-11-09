package com.prineside.tdi2.managers.preferences.categories.progress;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.prineside.tdi2.managers.preferences.PrefMap;
import com.prineside.tdi2.managers.preferences.PrefSubcategory;
import com.prineside.tdi2.utils.logging.TLog;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Locale;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/preferences/categories/progress/PP_SecretCode.class */
public final class PP_SecretCode implements PrefSubcategory {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2539a = TLog.forClass(PP_SecretCode.class);

    /* renamed from: b, reason: collision with root package name */
    private final Array<String> f2540b = new Array<>();

    public final boolean isCodeApplied(String str) {
        return this.f2540b.contains(str, false);
    }

    public final synchronized void setCodeApplied(String str) {
        String lowerCase = str.toLowerCase(Locale.US);
        if (!this.f2540b.contains(lowerCase, false)) {
            this.f2540b.add(lowerCase);
        }
    }

    @Override // com.prineside.tdi2.managers.preferences.PrefSubcategory
    public final void load(PrefMap prefMap) {
        this.f2540b.clear();
        String str = prefMap.get("appliedSecretCodes", null);
        if (str != null) {
            try {
                Iterator<JsonValue> iterator2 = new JsonReader().parse(str).iterator2();
                while (iterator2.hasNext()) {
                    this.f2540b.add(iterator2.next().asString());
                }
            } catch (Exception e) {
                f2539a.e("failed to parse applied codes", e);
            }
        }
    }

    @Override // com.prineside.tdi2.managers.preferences.PrefSubcategory
    public final synchronized void save(PrefMap prefMap) {
        if (this.f2540b.size != 0) {
            Json json = new Json(JsonWriter.OutputType.minimal);
            StringWriter stringWriter = new StringWriter();
            json.setWriter(stringWriter);
            json.writeArrayStart();
            for (int i = 0; i < this.f2540b.size; i++) {
                json.writeValue(this.f2540b.get(i));
            }
            json.writeArrayEnd();
            prefMap.set("appliedSecretCodes", stringWriter.toString());
        }
    }
}
