package com.prineside.tdi2.managers.preferences.categories.progress;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.prineside.tdi2.UserMap;
import com.prineside.tdi2.managers.preferences.PrefMap;
import com.prineside.tdi2.managers.preferences.PrefSubcategory;
import com.prineside.tdi2.utils.logging.TLog;
import java.io.StringWriter;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/preferences/categories/progress/PP_UserMaps.class */
public class PP_UserMaps implements PrefSubcategory {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2543a = TLog.forClass(PP_UserMaps.class);

    /* renamed from: b, reason: collision with root package name */
    private final Array<UserMap> f2544b = new Array<>(UserMap.class);

    public Array<UserMap> getUserMapsOrdered() {
        return this.f2544b;
    }

    public synchronized void addUserMap(UserMap userMap) {
        this.f2544b.add(userMap);
    }

    public synchronized boolean removeUserMap(String str) {
        for (int i = 0; i < this.f2544b.size; i++) {
            if (this.f2544b.items[i].id.equals(str)) {
                this.f2544b.removeIndex(i);
                return true;
            }
        }
        return false;
    }

    @Override // com.prineside.tdi2.managers.preferences.PrefSubcategory
    public void load(PrefMap prefMap) {
        this.f2544b.clear();
        String str = prefMap.get("userMaps", null);
        if (str != null) {
            try {
                Iterator<JsonValue> iterator2 = new JsonReader().parse(str).iterator2();
                while (iterator2.hasNext()) {
                    try {
                        this.f2544b.add(UserMap.fromJson(iterator2.next()));
                    } catch (Exception e) {
                        f2543a.e("failed to parse user map", e);
                    }
                }
            } catch (Exception e2) {
                f2543a.e("failed to parse json: " + str, e2);
            }
        }
        this.f2544b.sort((userMap, userMap2) -> {
            if (userMap.creationTimestamp == userMap2.creationTimestamp) {
                return 0;
            }
            return userMap.creationTimestamp > userMap2.creationTimestamp ? 1 : -1;
        });
    }

    @Override // com.prineside.tdi2.managers.preferences.PrefSubcategory
    public synchronized void save(PrefMap prefMap) {
        if (this.f2544b.size != 0) {
            Json json = new Json(JsonWriter.OutputType.minimal);
            StringWriter stringWriter = new StringWriter();
            json.setWriter(stringWriter);
            json.writeArrayStart();
            for (int i = 0; i < this.f2544b.size; i++) {
                json.writeObjectStart();
                this.f2544b.items[i].toJson(json);
                json.writeObjectEnd();
            }
            json.writeArrayEnd();
            prefMap.set("userMaps", stringWriter.toString());
        }
    }
}
