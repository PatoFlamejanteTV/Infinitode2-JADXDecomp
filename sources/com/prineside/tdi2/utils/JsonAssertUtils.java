package com.prineside.tdi2.utils;

import com.a.a.c.m;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.Null;
import java.util.Iterator;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/JsonAssertUtils.class */
public final class JsonAssertUtils {
    public static void checkJsonType(JsonValue jsonValue, JsonValue.ValueType valueType, CharSequence charSequence) {
        if (jsonValue == null) {
            throw new IllegalArgumentException(((Object) charSequence) + " - type must be " + valueType + ", null given");
        }
        if (jsonValue.type() != valueType) {
            throw new IllegalArgumentException(((Object) charSequence) + " - type must be " + valueType + ", " + jsonValue.type() + " given: " + jsonValue);
        }
    }

    public static void checkJsonType(m mVar, com.a.a.c.j.m mVar2, CharSequence charSequence) {
        if (mVar == null) {
            throw new IllegalArgumentException(((Object) charSequence) + " - type must be " + mVar2 + ", null given");
        }
        if (mVar.d() != mVar2) {
            throw new IllegalArgumentException(((Object) charSequence) + " - type must be " + mVar2 + ", " + mVar.d() + " given: " + mVar);
        }
    }

    public static void assertJsonValuesEqual(JsonValue jsonValue, JsonValue jsonValue2, @Null StringBuilder sb) {
        if (jsonValue == jsonValue2) {
            return;
        }
        if (sb == null) {
            StringBuilder sb2 = new StringBuilder();
            sb = sb2;
            sb2.append("root");
        }
        if (jsonValue == null || jsonValue2 == null) {
            throw new IllegalArgumentException("One value is null: " + jsonValue + ", " + jsonValue2 + " at " + ((Object) sb));
        }
        if (jsonValue.type() != jsonValue2.type()) {
            throw new IllegalArgumentException("Type mismatch (" + jsonValue.type() + ", " + jsonValue2.type() + "): " + jsonValue.toJson(JsonWriter.OutputType.json) + ", " + jsonValue2.toJson(JsonWriter.OutputType.json) + " at " + ((Object) sb));
        }
        switch (jsonValue.type()) {
            case booleanValue:
                if (jsonValue.asBoolean() != jsonValue2.asBoolean()) {
                    throw new IllegalArgumentException("Boolean values mismatch: " + jsonValue.toJson(JsonWriter.OutputType.json) + ", " + jsonValue2.toJson(JsonWriter.OutputType.json) + " at " + ((Object) sb));
                }
                return;
            case doubleValue:
                if (jsonValue.asDouble() != jsonValue2.asDouble()) {
                    throw new IllegalArgumentException("Double values mismatch: " + jsonValue.toJson(JsonWriter.OutputType.json) + ", " + jsonValue2.toJson(JsonWriter.OutputType.json) + " at " + ((Object) sb));
                }
                return;
            case longValue:
                if (jsonValue.asLong() != jsonValue2.asLong()) {
                    throw new IllegalArgumentException("Long values mismatch: " + jsonValue.toJson(JsonWriter.OutputType.json) + ", " + jsonValue2.toJson(JsonWriter.OutputType.json) + " at " + ((Object) sb));
                }
                return;
            case stringValue:
                if (jsonValue.asString() == jsonValue2.asString()) {
                    return;
                }
                if (jsonValue.asString() == null) {
                    throw new IllegalArgumentException("String values mismatch (first is null): " + jsonValue.toJson(JsonWriter.OutputType.json) + ", " + jsonValue2.toJson(JsonWriter.OutputType.json) + " at " + ((Object) sb));
                }
                if (jsonValue2.asString() == null) {
                    throw new IllegalArgumentException("String values mismatch (second is null): " + jsonValue.toJson(JsonWriter.OutputType.json) + ", " + jsonValue2.toJson(JsonWriter.OutputType.json) + " at " + ((Object) sb));
                }
                if (!jsonValue.asString().equals(jsonValue2.asString())) {
                    throw new IllegalArgumentException("String values mismatch: " + jsonValue.toJson(JsonWriter.OutputType.json) + ", " + jsonValue2.toJson(JsonWriter.OutputType.json) + " at " + ((Object) sb));
                }
                return;
            case array:
                if (jsonValue.size != jsonValue2.size) {
                    throw new IllegalArgumentException("Array length mismatch: " + jsonValue.toJson(JsonWriter.OutputType.json) + ", " + jsonValue2.toJson(JsonWriter.OutputType.json) + " at " + ((Object) sb));
                }
                int i = 0;
                Iterator<JsonValue> iterator2 = jsonValue.iterator2();
                while (iterator2.hasNext()) {
                    iterator2.next();
                    i++;
                }
                if (i != jsonValue.size) {
                    throw new IllegalArgumentException("Broken json (first, size does not match iteration count: real = " + i + ", size = " + jsonValue.size + "): " + jsonValue + ", " + jsonValue2 + " at " + ((Object) sb));
                }
                int i2 = 0;
                Iterator<JsonValue> iterator22 = jsonValue2.iterator2();
                while (iterator22.hasNext()) {
                    iterator22.next();
                    i2++;
                }
                if (i2 != jsonValue2.size) {
                    throw new IllegalArgumentException("Broken json (second, size does not match iteration count: real = " + i2 + ", size = " + jsonValue2.size + "): " + jsonValue + ", " + jsonValue2 + " at " + ((Object) sb));
                }
                Iterator<JsonValue> iterator23 = jsonValue2.iterator2();
                int i3 = 0;
                Iterator<JsonValue> iterator24 = jsonValue.iterator2();
                while (iterator24.hasNext()) {
                    JsonValue next = iterator24.next();
                    int length = sb.length();
                    sb.append('[').append(i3).append(']');
                    assertJsonValuesEqual(next, iterator23.next(), sb);
                    sb.setLength(length);
                    i3++;
                }
                return;
            case object:
                if (jsonValue.size != jsonValue2.size) {
                    throw new IllegalArgumentException("Objects field count mismatch: " + jsonValue.toJson(JsonWriter.OutputType.json) + ", " + jsonValue2.toJson(JsonWriter.OutputType.json) + " at " + ((Object) sb));
                }
                int i4 = 0;
                Iterator<JsonValue> iterator25 = jsonValue.iterator2();
                while (iterator25.hasNext()) {
                    iterator25.next();
                    i4++;
                }
                if (i4 != jsonValue.size) {
                    throw new IllegalArgumentException("Broken json (first, size does not match iteration count): " + jsonValue.toJson(JsonWriter.OutputType.json) + ", " + jsonValue2.toJson(JsonWriter.OutputType.json) + " at " + ((Object) sb));
                }
                int i5 = 0;
                Iterator<JsonValue> iterator26 = jsonValue2.iterator2();
                while (iterator26.hasNext()) {
                    iterator26.next();
                    i5++;
                }
                if (i5 != jsonValue2.size) {
                    throw new IllegalArgumentException("Broken json (second, size does not match iteration count): " + jsonValue.toJson(JsonWriter.OutputType.json) + ", " + jsonValue2.toJson(JsonWriter.OutputType.json) + " at " + ((Object) sb));
                }
                Iterator<JsonValue> iterator27 = jsonValue.iterator2();
                while (iterator27.hasNext()) {
                    JsonValue next2 = iterator27.next();
                    int length2 = sb.length();
                    sb.append('.').append(next2.name);
                    assertJsonValuesEqual(next2, jsonValue2.get(next2.name), sb);
                    sb.setLength(length2);
                }
                return;
            case nullValue:
                return;
            default:
                return;
        }
    }

    public static void assertJsonValuesEqual(m mVar, m mVar2, @Null StringBuilder sb) {
        if (mVar == mVar2) {
            return;
        }
        if (sb == null) {
            StringBuilder sb2 = new StringBuilder();
            sb = sb2;
            sb2.append("root");
        }
        if (mVar == null || mVar2 == null) {
            throw new IllegalArgumentException("One value is null: " + mVar + ", " + mVar2 + " at " + ((Object) sb));
        }
        if (mVar.d() != mVar2.d()) {
            throw new IllegalArgumentException("Type mismatch (" + mVar.d() + ", " + mVar2.d() + "): " + mVar + ", " + mVar2 + " at " + ((Object) sb));
        }
        switch (mVar.d()) {
            case BOOLEAN:
                if (mVar.l() != mVar2.l()) {
                    throw new IllegalArgumentException("Boolean values mismatch: " + mVar + ", " + mVar2 + " at " + ((Object) sb));
                }
                return;
            case NUMBER:
                if (mVar.k() != mVar2.k()) {
                    throw new IllegalArgumentException("Double values mismatch: " + mVar + ", " + mVar2 + " at " + ((Object) sb));
                }
                return;
            case STRING:
                if (mVar.i().equals(mVar2.i())) {
                    return;
                } else {
                    throw new IllegalArgumentException("String values mismatch: " + mVar + ", " + mVar2 + " at " + ((Object) sb));
                }
            case ARRAY:
                if (mVar.a() != mVar2.a()) {
                    throw new IllegalArgumentException("Array length mismatch: " + mVar + ", " + mVar2 + " at " + ((Object) sb));
                }
                int i = 0;
                Iterator<m> it = mVar.iterator();
                while (it.hasNext()) {
                    it.next();
                    i++;
                }
                if (i != mVar.a()) {
                    throw new IllegalArgumentException("Broken json (first, size does not match iteration count: real = " + i + ", size = " + mVar.a() + "): " + mVar + ", " + mVar2 + " at " + ((Object) sb));
                }
                int i2 = 0;
                Iterator<m> it2 = mVar2.iterator();
                while (it2.hasNext()) {
                    it2.next();
                    i2++;
                }
                if (i2 != mVar2.a()) {
                    throw new IllegalArgumentException("Broken json (second, size does not match iteration count: real = " + i2 + ", size = " + mVar2.a() + "): " + mVar + ", " + mVar2 + " at " + ((Object) sb));
                }
                Iterator<m> it3 = mVar2.iterator();
                int i3 = 0;
                Iterator<m> it4 = mVar.iterator();
                while (it4.hasNext()) {
                    m next = it4.next();
                    int length = sb.length();
                    sb.append('[').append(i3).append(']');
                    assertJsonValuesEqual(next, it3.next(), sb);
                    sb.setLength(length);
                    i3++;
                }
                return;
            case OBJECT:
                if (mVar.a() != mVar2.a()) {
                    throw new IllegalArgumentException("Objects field count mismatch: " + mVar + ", " + mVar2 + " at " + ((Object) sb));
                }
                int i4 = 0;
                Iterator<m> it5 = mVar.iterator();
                while (it5.hasNext()) {
                    it5.next();
                    i4++;
                }
                if (i4 != mVar.a()) {
                    throw new IllegalArgumentException("Broken json (first, size does not match iteration count): " + mVar + ", " + mVar2 + " at " + ((Object) sb));
                }
                int i5 = 0;
                Iterator<m> it6 = mVar2.iterator();
                while (it6.hasNext()) {
                    it6.next();
                    i5++;
                }
                if (i5 != mVar2.a()) {
                    throw new IllegalArgumentException("Broken json (second, size does not match iteration count): " + mVar + ", " + mVar2 + " at " + ((Object) sb));
                }
                Iterator<Map.Entry<String, m>> n = mVar.n();
                while (n.hasNext()) {
                    Map.Entry<String, m> next2 = n.next();
                    int length2 = sb.length();
                    sb.append('.').append(next2.getKey());
                    assertJsonValuesEqual(next2.getValue(), mVar2.a(next2.getKey()), sb);
                    sb.setLength(length2);
                }
                return;
            default:
                return;
        }
    }
}
