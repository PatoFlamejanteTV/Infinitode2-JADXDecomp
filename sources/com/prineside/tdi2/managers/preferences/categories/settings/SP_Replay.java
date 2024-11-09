package com.prineside.tdi2.managers.preferences.categories.settings;

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

/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/preferences/categories/settings/SP_Replay.class */
public final class SP_Replay implements PrefSubcategory {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2549a = TLog.forClass(SP_Replay.class);
    public final Array<String> sentReplayIds = new Array<>(false, 1, String.class);

    @Override // com.prineside.tdi2.managers.preferences.PrefSubcategory
    public final void load(PrefMap prefMap) {
        this.sentReplayIds.clear();
        String str = prefMap.get("sentGameReplaysToServer", null);
        if (str != null) {
            try {
                Iterator<JsonValue> iterator2 = new JsonReader().parse(str).iterator2();
                while (iterator2.hasNext()) {
                    this.sentReplayIds.add(iterator2.next().asString());
                }
                f2549a.i("loaded " + this.sentReplayIds.size + " replay IDs sent to the server", new Object[0]);
            } catch (Exception e) {
                f2549a.e("failed to parse json: " + str, e);
            }
        }
    }

    @Override // com.prineside.tdi2.managers.preferences.PrefSubcategory
    public final synchronized void save(PrefMap prefMap) {
        Json json = new Json(JsonWriter.OutputType.minimal);
        StringWriter stringWriter = new StringWriter();
        json.setWriter(stringWriter);
        json.writeArrayStart();
        for (int i = 0; i < this.sentReplayIds.size; i++) {
            json.writeValue(this.sentReplayIds.get(i));
        }
        json.writeArrayEnd();
        prefMap.set("sentGameReplaysToServer", stringWriter.toString());
    }
}
