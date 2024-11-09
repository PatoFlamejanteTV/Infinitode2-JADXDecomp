package com.prineside.tdi2.utils;

import com.a.a.b.d.e;
import com.a.a.c.h.a;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Null;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/JsonHandler.class */
public final class JsonHandler {

    /* renamed from: a, reason: collision with root package name */
    private static final JsonHandler f3854a = new JsonHandler();
    public static final JsonValue EMPTY_JSON_OBJECT = new JsonValue(JsonValue.ValueType.object);

    /* renamed from: b, reason: collision with root package name */
    private a f3855b;

    public static JsonHandler i() {
        return f3854a;
    }

    private JsonHandler() {
    }

    public final a getMapper() {
        if (this.f3855b == null) {
            this.f3855b = a.b().a(e.ALLOW_JAVA_COMMENTS).a(e.ALLOW_SINGLE_QUOTES).a(e.ALLOW_UNQUOTED_FIELD_NAMES).a(e.ALLOW_TRAILING_COMMA).a(e.ALLOW_LEADING_ZEROS_FOR_NUMBERS).a(e.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS).a(e.ALLOW_LEADING_DECIMAL_POINT_FOR_NUMBERS).a(e.ALLOW_TRAILING_DECIMAL_POINT_FOR_NUMBERS).a(e.ALLOW_NON_NUMERIC_NUMBERS).a();
        }
        return this.f3855b;
    }

    public static JsonValue orEmptyObject(@Null JsonValue jsonValue) {
        if (jsonValue == null) {
            return EMPTY_JSON_OBJECT;
        }
        return jsonValue;
    }

    public static boolean isJsonFile(FileHandle fileHandle) {
        if (!fileHandle.exists() || fileHandle.isDirectory()) {
            return false;
        }
        String extension = fileHandle.extension();
        return "json".equals(extension) || "json5".equals(extension);
    }

    public static JsonValue cloneJsonValue(JsonValue jsonValue) {
        JsonValue jsonValue2 = new JsonValue(jsonValue.type());
        jsonValue2.name = jsonValue.name;
        switch (jsonValue.type()) {
            case object:
            case array:
                Iterator<JsonValue> iterator2 = jsonValue.iterator2();
                while (iterator2.hasNext()) {
                    jsonValue2.addChild(cloneJsonValue(iterator2.next()));
                }
                break;
            case stringValue:
                jsonValue2.set(jsonValue.asString());
                break;
            case longValue:
                jsonValue2.set(jsonValue.asLong(), (String) null);
                break;
            case doubleValue:
                jsonValue2.set(jsonValue.asDouble(), (String) null);
                break;
            case booleanValue:
                jsonValue2.set(jsonValue.asBoolean());
                break;
        }
        return jsonValue2;
    }

    public static void setJsonValue(JsonValue jsonValue, JsonValue jsonValue2) {
        jsonValue.size = 0;
        jsonValue.child = null;
        switch (jsonValue2.type()) {
            case object:
                jsonValue.setType(JsonValue.ValueType.object);
                Iterator<JsonValue> iterator2 = jsonValue2.iterator2();
                while (iterator2.hasNext()) {
                    jsonValue.addChild(cloneJsonValue(iterator2.next()));
                }
                return;
            case array:
                jsonValue.setType(JsonValue.ValueType.array);
                Iterator<JsonValue> iterator22 = jsonValue2.iterator2();
                while (iterator22.hasNext()) {
                    jsonValue.addChild(cloneJsonValue(iterator22.next()));
                }
                return;
            case stringValue:
                jsonValue.set(jsonValue2.asString());
                return;
            case longValue:
                jsonValue.set(jsonValue2.asLong(), (String) null);
                return;
            case doubleValue:
                jsonValue.set(jsonValue2.asDouble(), (String) null);
                return;
            case booleanValue:
                jsonValue.set(jsonValue2.asBoolean());
                return;
            case nullValue:
                jsonValue.setType(JsonValue.ValueType.nullValue);
                return;
            default:
                return;
        }
    }
}
