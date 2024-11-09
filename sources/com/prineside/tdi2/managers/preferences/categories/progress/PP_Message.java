package com.prineside.tdi2.managers.preferences.categories.progress;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectMap;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.managers.preferences.PrefMap;
import com.prineside.tdi2.managers.preferences.PrefSubcategory;
import com.prineside.tdi2.utils.logging.TLog;
import com.vladsch.flexmark.util.html.Attribute;
import java.io.StringWriter;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/preferences/categories/progress/PP_Message.class */
public final class PP_Message implements PrefSubcategory {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f2531a = TLog.forClass(PP_Message.class);

    /* renamed from: b, reason: collision with root package name */
    private final ObjectMap<String, Long> f2532b = new ObjectMap<>();
    private final ObjectMap<String, Long> c = new ObjectMap<>();
    private final ObjectMap<String, Long> d = new ObjectMap<>();

    public final boolean isMessageRead(String str) {
        return this.f2532b.containsKey(str);
    }

    public final synchronized void setMessageRead(String str) {
        this.f2532b.put(str, Long.valueOf(Game.getTimestampMillis()));
    }

    public final synchronized void removeMessageReadRecord(String str) {
        this.f2532b.remove(str);
    }

    @Null
    public final Long getMessageReadTimestamp(String str) {
        return this.f2532b.get(str, null);
    }

    public final boolean isMessageDeleted(String str) {
        return this.c.containsKey(str);
    }

    public final synchronized void setMessageDeleted(String str) {
        this.c.put(str, Long.valueOf(Game.getTimestampMillis()));
    }

    public final synchronized void removeMessageDeletedRecord(String str) {
        this.c.remove(str);
    }

    @Null
    public final Long getMessageDeletedTimestamp(String str) {
        return this.c.get(str, null);
    }

    public final boolean isMessageItemsReceived(String str) {
        return this.d.containsKey(str);
    }

    public final synchronized void setMessageItemsReceived(String str) {
        this.d.put(str, Long.valueOf(Game.getTimestampMillis()));
    }

    public final synchronized void removeMessageItemsReceivedRecord(String str) {
        this.d.remove(str);
    }

    @Null
    public final Long getMessageItemsReceivedTimestamp(String str) {
        return this.d.get(str, null);
    }

    @Override // com.prineside.tdi2.managers.preferences.PrefSubcategory
    public final synchronized void load(PrefMap prefMap) {
        this.f2532b.clear();
        this.c.clear();
        this.d.clear();
        String str = prefMap.get("messageManager", null);
        if (str != null) {
            try {
                JsonValue parse = new JsonReader().parse(str);
                JsonValue jsonValue = parse.get("readMessages");
                if (jsonValue != null) {
                    Iterator<JsonValue> iterator2 = jsonValue.iterator2();
                    while (iterator2.hasNext()) {
                        JsonValue next = iterator2.next();
                        this.f2532b.put(next.getString(Attribute.ID_ATTR), Long.valueOf(next.getLong("ts")));
                    }
                }
                JsonValue jsonValue2 = parse.get("deletedMessages");
                if (jsonValue2 != null) {
                    Iterator<JsonValue> iterator22 = jsonValue2.iterator2();
                    while (iterator22.hasNext()) {
                        JsonValue next2 = iterator22.next();
                        this.c.put(next2.getString(Attribute.ID_ATTR), Long.valueOf(next2.getLong("ts")));
                    }
                }
                JsonValue jsonValue3 = parse.get("itemsReceivedMessages");
                if (jsonValue3 != null) {
                    Iterator<JsonValue> iterator23 = jsonValue3.iterator2();
                    while (iterator23.hasNext()) {
                        JsonValue next3 = iterator23.next();
                        this.d.put(next3.getString(Attribute.ID_ATTR), Long.valueOf(next3.getLong("ts")));
                    }
                }
            } catch (Exception e) {
                f2531a.e("failed to read save data", e);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.prineside.tdi2.managers.preferences.PrefSubcategory
    public final synchronized void save(PrefMap prefMap) {
        Json json = new Json(JsonWriter.OutputType.minimal);
        StringWriter stringWriter = new StringWriter();
        json.setWriter(stringWriter);
        json.writeObjectStart();
        json.writeArrayStart("readMessages");
        ObjectMap.Entries<String, Long> it = this.f2532b.iterator();
        while (it.hasNext()) {
            ObjectMap.Entry next = it.next();
            if (Game.getTimestampMillis() - ((Long) next.value).longValue() < 3888000000L) {
                json.writeObjectStart();
                json.writeValue(Attribute.ID_ATTR, next.key);
                json.writeValue("ts", next.value);
                json.writeObjectEnd();
            }
        }
        json.writeArrayEnd();
        json.writeArrayStart("deletedMessages");
        ObjectMap.Entries<String, Long> it2 = this.c.iterator();
        while (it2.hasNext()) {
            ObjectMap.Entry next2 = it2.next();
            if (Game.getTimestampMillis() - ((Long) next2.value).longValue() < 3888000000L) {
                json.writeObjectStart();
                json.writeValue(Attribute.ID_ATTR, next2.key);
                json.writeValue("ts", next2.value);
                json.writeObjectEnd();
            }
        }
        json.writeArrayEnd();
        json.writeArrayStart("itemsReceivedMessages");
        ObjectMap.Entries<String, Long> it3 = this.d.iterator();
        while (it3.hasNext()) {
            ObjectMap.Entry next3 = it3.next();
            if (Game.getTimestampMillis() - ((Long) next3.value).longValue() < 3888000000L) {
                json.writeObjectStart();
                json.writeValue(Attribute.ID_ATTR, next3.key);
                json.writeValue("ts", next3.value);
                json.writeObjectEnd();
            }
        }
        json.writeArrayEnd();
        json.writeObjectEnd();
        prefMap.set("messageManager", stringWriter.toString());
    }
}
