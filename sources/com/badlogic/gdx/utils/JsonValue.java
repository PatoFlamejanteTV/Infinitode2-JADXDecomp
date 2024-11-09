package com.badlogic.gdx.utils;

import com.badlogic.gdx.utils.JsonWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/JsonValue.class */
public class JsonValue implements Iterable<JsonValue> {
    private ValueType type;
    private String stringValue;
    private double doubleValue;
    private long longValue;
    public String name;
    public JsonValue child;
    public JsonValue parent;
    public JsonValue next;
    public JsonValue prev;
    public int size;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/JsonValue$PrettyPrintSettings.class */
    public static class PrettyPrintSettings {
        public JsonWriter.OutputType outputType;
        public int singleLineColumns;
        public boolean wrapNumericArrays;
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/JsonValue$ValueType.class */
    public enum ValueType {
        object,
        array,
        stringValue,
        doubleValue,
        longValue,
        booleanValue,
        nullValue
    }

    public JsonValue(ValueType valueType) {
        this.type = valueType;
    }

    public JsonValue(@Null String str) {
        set(str);
    }

    public JsonValue(double d) {
        set(d, (String) null);
    }

    public JsonValue(long j) {
        set(j, (String) null);
    }

    public JsonValue(double d, String str) {
        set(d, str);
    }

    public JsonValue(long j, String str) {
        set(j, str);
    }

    public JsonValue(boolean z) {
        set(z);
    }

    @Null
    public JsonValue get(int i) {
        JsonValue jsonValue;
        JsonValue jsonValue2 = this.child;
        while (true) {
            jsonValue = jsonValue2;
            if (jsonValue == null || i <= 0) {
                break;
            }
            i--;
            jsonValue2 = jsonValue.next;
        }
        return jsonValue;
    }

    @Null
    public JsonValue get(String str) {
        JsonValue jsonValue;
        JsonValue jsonValue2 = this.child;
        while (true) {
            jsonValue = jsonValue2;
            if (jsonValue == null || (jsonValue.name != null && jsonValue.name.equalsIgnoreCase(str))) {
                break;
            }
            jsonValue2 = jsonValue.next;
        }
        return jsonValue;
    }

    public boolean has(String str) {
        return get(str) != null;
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [com.badlogic.gdx.utils.JsonValue$JsonIterator] */
    public JsonIterator iterator(String str) {
        JsonValue jsonValue = get(str);
        if (jsonValue == null) {
            JsonIterator jsonIterator = new JsonIterator();
            jsonIterator.entry = null;
            return jsonIterator;
        }
        return jsonValue.iterator2();
    }

    public JsonValue require(int i) {
        JsonValue jsonValue = get(i);
        if (jsonValue == null) {
            throw new IllegalArgumentException("Child not found with index: " + i);
        }
        return jsonValue;
    }

    public JsonValue require(String str) {
        JsonValue jsonValue = get(str);
        if (jsonValue == null) {
            throw new IllegalArgumentException("Child not found with name: " + str);
        }
        return jsonValue;
    }

    @Null
    public JsonValue remove(int i) {
        JsonValue jsonValue = get(i);
        if (jsonValue == null) {
            return null;
        }
        if (jsonValue.prev == null) {
            this.child = jsonValue.next;
            if (this.child != null) {
                this.child.prev = null;
            }
        } else {
            jsonValue.prev.next = jsonValue.next;
            if (jsonValue.next != null) {
                jsonValue.next.prev = jsonValue.prev;
            }
        }
        this.size--;
        return jsonValue;
    }

    @Null
    public JsonValue remove(String str) {
        JsonValue jsonValue = get(str);
        if (jsonValue == null) {
            return null;
        }
        if (jsonValue.prev == null) {
            this.child = jsonValue.next;
            if (this.child != null) {
                this.child.prev = null;
            }
        } else {
            jsonValue.prev.next = jsonValue.next;
            if (jsonValue.next != null) {
                jsonValue.next.prev = jsonValue.prev;
            }
        }
        this.size--;
        return jsonValue;
    }

    public void remove() {
        if (this.parent == null) {
            throw new IllegalStateException();
        }
        if (this.prev == null) {
            this.parent.child = this.next;
            if (this.parent.child != null) {
                this.parent.child.prev = null;
            }
        } else {
            this.prev.next = this.next;
            if (this.next != null) {
                this.next.prev = this.prev;
            }
        }
        this.parent.size--;
    }

    public boolean notEmpty() {
        return this.size > 0;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    @Deprecated
    public int size() {
        return this.size;
    }

    @Null
    public String asString() {
        switch (this.type) {
            case stringValue:
                return this.stringValue;
            case doubleValue:
                return this.stringValue != null ? this.stringValue : Double.toString(this.doubleValue);
            case longValue:
                return this.stringValue != null ? this.stringValue : Long.toString(this.longValue);
            case booleanValue:
                return this.longValue != 0 ? "true" : "false";
            case nullValue:
                return null;
            default:
                throw new IllegalStateException("Value cannot be converted to string: " + this.type);
        }
    }

    public float asFloat() {
        switch (this.type) {
            case stringValue:
                return Float.parseFloat(this.stringValue);
            case doubleValue:
                return (float) this.doubleValue;
            case longValue:
                return (float) this.longValue;
            case booleanValue:
                return this.longValue != 0 ? 1.0f : 0.0f;
            default:
                throw new IllegalStateException("Value cannot be converted to float: " + this.type);
        }
    }

    public double asDouble() {
        switch (this.type) {
            case stringValue:
                return Double.parseDouble(this.stringValue);
            case doubleValue:
                return this.doubleValue;
            case longValue:
                return this.longValue;
            case booleanValue:
                return this.longValue != 0 ? 1.0d : 0.0d;
            default:
                throw new IllegalStateException("Value cannot be converted to double: " + this.type);
        }
    }

    public long asLong() {
        switch (this.type) {
            case stringValue:
                return Long.parseLong(this.stringValue);
            case doubleValue:
                return (long) this.doubleValue;
            case longValue:
                return this.longValue;
            case booleanValue:
                return this.longValue != 0 ? 1L : 0L;
            default:
                throw new IllegalStateException("Value cannot be converted to long: " + this.type);
        }
    }

    public int asInt() {
        switch (this.type) {
            case stringValue:
                return Integer.parseInt(this.stringValue);
            case doubleValue:
                return (int) this.doubleValue;
            case longValue:
                return (int) this.longValue;
            case booleanValue:
                return this.longValue != 0 ? 1 : 0;
            default:
                throw new IllegalStateException("Value cannot be converted to int: " + this.type);
        }
    }

    public boolean asBoolean() {
        switch (this.type) {
            case stringValue:
                return this.stringValue.equalsIgnoreCase("true");
            case doubleValue:
                return this.doubleValue != 0.0d;
            case longValue:
                return this.longValue != 0;
            case booleanValue:
                return this.longValue != 0;
            default:
                throw new IllegalStateException("Value cannot be converted to boolean: " + this.type);
        }
    }

    public byte asByte() {
        switch (this.type) {
            case stringValue:
                return Byte.parseByte(this.stringValue);
            case doubleValue:
                return (byte) this.doubleValue;
            case longValue:
                return (byte) this.longValue;
            case booleanValue:
                return this.longValue != 0 ? (byte) 1 : (byte) 0;
            default:
                throw new IllegalStateException("Value cannot be converted to byte: " + this.type);
        }
    }

    public short asShort() {
        switch (this.type) {
            case stringValue:
                return Short.parseShort(this.stringValue);
            case doubleValue:
                return (short) this.doubleValue;
            case longValue:
                return (short) this.longValue;
            case booleanValue:
                return this.longValue != 0 ? (short) 1 : (short) 0;
            default:
                throw new IllegalStateException("Value cannot be converted to short: " + this.type);
        }
    }

    public char asChar() {
        switch (this.type) {
            case stringValue:
                if (this.stringValue.length() == 0) {
                    return (char) 0;
                }
                return this.stringValue.charAt(0);
            case doubleValue:
                return (char) this.doubleValue;
            case longValue:
                return (char) this.longValue;
            case booleanValue:
                return this.longValue != 0 ? (char) 1 : (char) 0;
            default:
                throw new IllegalStateException("Value cannot be converted to char: " + this.type);
        }
    }

    public String[] asStringArray() {
        String str;
        if (this.type != ValueType.array) {
            throw new IllegalStateException("Value is not an array: " + this.type);
        }
        String[] strArr = new String[this.size];
        int i = 0;
        JsonValue jsonValue = this.child;
        while (jsonValue != null) {
            switch (jsonValue.type) {
                case stringValue:
                    str = jsonValue.stringValue;
                    break;
                case doubleValue:
                    if (this.stringValue == null) {
                        str = Double.toString(jsonValue.doubleValue);
                        break;
                    } else {
                        str = this.stringValue;
                        break;
                    }
                case longValue:
                    if (this.stringValue == null) {
                        str = Long.toString(jsonValue.longValue);
                        break;
                    } else {
                        str = this.stringValue;
                        break;
                    }
                case booleanValue:
                    if (jsonValue.longValue == 0) {
                        str = "false";
                        break;
                    } else {
                        str = "true";
                        break;
                    }
                case nullValue:
                    str = null;
                    break;
                default:
                    throw new IllegalStateException("Value cannot be converted to string: " + jsonValue.type);
            }
            strArr[i] = str;
            jsonValue = jsonValue.next;
            i++;
        }
        return strArr;
    }

    public float[] asFloatArray() {
        float f;
        if (this.type != ValueType.array) {
            throw new IllegalStateException("Value is not an array: " + this.type);
        }
        float[] fArr = new float[this.size];
        int i = 0;
        JsonValue jsonValue = this.child;
        while (jsonValue != null) {
            switch (jsonValue.type) {
                case stringValue:
                    f = Float.parseFloat(jsonValue.stringValue);
                    break;
                case doubleValue:
                    f = (float) jsonValue.doubleValue;
                    break;
                case longValue:
                    f = (float) jsonValue.longValue;
                    break;
                case booleanValue:
                    if (jsonValue.longValue == 0) {
                        f = 0.0f;
                        break;
                    } else {
                        f = 1.0f;
                        break;
                    }
                default:
                    throw new IllegalStateException("Value cannot be converted to float: " + jsonValue.type);
            }
            fArr[i] = f;
            jsonValue = jsonValue.next;
            i++;
        }
        return fArr;
    }

    public double[] asDoubleArray() {
        double d;
        if (this.type != ValueType.array) {
            throw new IllegalStateException("Value is not an array: " + this.type);
        }
        double[] dArr = new double[this.size];
        int i = 0;
        JsonValue jsonValue = this.child;
        while (jsonValue != null) {
            switch (jsonValue.type) {
                case stringValue:
                    d = Double.parseDouble(jsonValue.stringValue);
                    break;
                case doubleValue:
                    d = jsonValue.doubleValue;
                    break;
                case longValue:
                    d = jsonValue.longValue;
                    break;
                case booleanValue:
                    if (jsonValue.longValue == 0) {
                        d = 0.0d;
                        break;
                    } else {
                        d = 1.0d;
                        break;
                    }
                default:
                    throw new IllegalStateException("Value cannot be converted to double: " + jsonValue.type);
            }
            dArr[i] = d;
            jsonValue = jsonValue.next;
            i++;
        }
        return dArr;
    }

    public long[] asLongArray() {
        long j;
        if (this.type != ValueType.array) {
            throw new IllegalStateException("Value is not an array: " + this.type);
        }
        long[] jArr = new long[this.size];
        int i = 0;
        JsonValue jsonValue = this.child;
        while (jsonValue != null) {
            switch (jsonValue.type) {
                case stringValue:
                    j = Long.parseLong(jsonValue.stringValue);
                    break;
                case doubleValue:
                    j = (long) jsonValue.doubleValue;
                    break;
                case longValue:
                    j = jsonValue.longValue;
                    break;
                case booleanValue:
                    if (jsonValue.longValue == 0) {
                        j = 0;
                        break;
                    } else {
                        j = 1;
                        break;
                    }
                default:
                    throw new IllegalStateException("Value cannot be converted to long: " + jsonValue.type);
            }
            jArr[i] = j;
            jsonValue = jsonValue.next;
            i++;
        }
        return jArr;
    }

    public int[] asIntArray() {
        int i;
        if (this.type != ValueType.array) {
            throw new IllegalStateException("Value is not an array: " + this.type);
        }
        int[] iArr = new int[this.size];
        int i2 = 0;
        JsonValue jsonValue = this.child;
        while (jsonValue != null) {
            switch (jsonValue.type) {
                case stringValue:
                    i = Integer.parseInt(jsonValue.stringValue);
                    break;
                case doubleValue:
                    i = (int) jsonValue.doubleValue;
                    break;
                case longValue:
                    i = (int) jsonValue.longValue;
                    break;
                case booleanValue:
                    if (jsonValue.longValue == 0) {
                        i = 0;
                        break;
                    } else {
                        i = 1;
                        break;
                    }
                default:
                    throw new IllegalStateException("Value cannot be converted to int: " + jsonValue.type);
            }
            iArr[i2] = i;
            jsonValue = jsonValue.next;
            i2++;
        }
        return iArr;
    }

    public boolean[] asBooleanArray() {
        boolean z;
        if (this.type != ValueType.array) {
            throw new IllegalStateException("Value is not an array: " + this.type);
        }
        boolean[] zArr = new boolean[this.size];
        int i = 0;
        JsonValue jsonValue = this.child;
        while (jsonValue != null) {
            switch (jsonValue.type) {
                case stringValue:
                    z = Boolean.parseBoolean(jsonValue.stringValue);
                    break;
                case doubleValue:
                    if (jsonValue.doubleValue != 0.0d) {
                        z = false;
                        break;
                    } else {
                        z = true;
                        break;
                    }
                case longValue:
                    if (jsonValue.longValue != 0) {
                        z = false;
                        break;
                    } else {
                        z = true;
                        break;
                    }
                case booleanValue:
                    if (jsonValue.longValue == 0) {
                        z = false;
                        break;
                    } else {
                        z = true;
                        break;
                    }
                default:
                    throw new IllegalStateException("Value cannot be converted to boolean: " + jsonValue.type);
            }
            zArr[i] = z;
            jsonValue = jsonValue.next;
            i++;
        }
        return zArr;
    }

    public byte[] asByteArray() {
        byte b2;
        if (this.type != ValueType.array) {
            throw new IllegalStateException("Value is not an array: " + this.type);
        }
        byte[] bArr = new byte[this.size];
        int i = 0;
        JsonValue jsonValue = this.child;
        while (jsonValue != null) {
            switch (jsonValue.type) {
                case stringValue:
                    b2 = Byte.parseByte(jsonValue.stringValue);
                    break;
                case doubleValue:
                    b2 = (byte) jsonValue.doubleValue;
                    break;
                case longValue:
                    b2 = (byte) jsonValue.longValue;
                    break;
                case booleanValue:
                    if (jsonValue.longValue == 0) {
                        b2 = 0;
                        break;
                    } else {
                        b2 = 1;
                        break;
                    }
                default:
                    throw new IllegalStateException("Value cannot be converted to byte: " + jsonValue.type);
            }
            bArr[i] = b2;
            jsonValue = jsonValue.next;
            i++;
        }
        return bArr;
    }

    public short[] asShortArray() {
        short s;
        if (this.type != ValueType.array) {
            throw new IllegalStateException("Value is not an array: " + this.type);
        }
        short[] sArr = new short[this.size];
        int i = 0;
        JsonValue jsonValue = this.child;
        while (jsonValue != null) {
            switch (jsonValue.type) {
                case stringValue:
                    s = Short.parseShort(jsonValue.stringValue);
                    break;
                case doubleValue:
                    s = (short) jsonValue.doubleValue;
                    break;
                case longValue:
                    s = (short) jsonValue.longValue;
                    break;
                case booleanValue:
                    if (jsonValue.longValue == 0) {
                        s = 0;
                        break;
                    } else {
                        s = 1;
                        break;
                    }
                default:
                    throw new IllegalStateException("Value cannot be converted to short: " + jsonValue.type);
            }
            sArr[i] = s;
            jsonValue = jsonValue.next;
            i++;
        }
        return sArr;
    }

    public char[] asCharArray() {
        char c;
        if (this.type != ValueType.array) {
            throw new IllegalStateException("Value is not an array: " + this.type);
        }
        char[] cArr = new char[this.size];
        int i = 0;
        JsonValue jsonValue = this.child;
        while (jsonValue != null) {
            switch (jsonValue.type) {
                case stringValue:
                    if (jsonValue.stringValue.length() != 0) {
                        c = jsonValue.stringValue.charAt(0);
                        break;
                    } else {
                        c = 0;
                        break;
                    }
                case doubleValue:
                    c = (char) jsonValue.doubleValue;
                    break;
                case longValue:
                    c = (char) jsonValue.longValue;
                    break;
                case booleanValue:
                    if (jsonValue.longValue == 0) {
                        c = 0;
                        break;
                    } else {
                        c = 1;
                        break;
                    }
                default:
                    throw new IllegalStateException("Value cannot be converted to char: " + jsonValue.type);
            }
            cArr[i] = c;
            jsonValue = jsonValue.next;
            i++;
        }
        return cArr;
    }

    public boolean hasChild(String str) {
        return getChild(str) != null;
    }

    @Null
    public JsonValue getChild(String str) {
        JsonValue jsonValue = get(str);
        if (jsonValue == null) {
            return null;
        }
        return jsonValue.child;
    }

    public String getString(String str, @Null String str2) {
        JsonValue jsonValue = get(str);
        return (jsonValue == null || !jsonValue.isValue() || jsonValue.isNull()) ? str2 : jsonValue.asString();
    }

    public float getFloat(String str, float f) {
        JsonValue jsonValue = get(str);
        return (jsonValue == null || !jsonValue.isValue() || jsonValue.isNull()) ? f : jsonValue.asFloat();
    }

    public double getDouble(String str, double d) {
        JsonValue jsonValue = get(str);
        return (jsonValue == null || !jsonValue.isValue() || jsonValue.isNull()) ? d : jsonValue.asDouble();
    }

    public long getLong(String str, long j) {
        JsonValue jsonValue = get(str);
        return (jsonValue == null || !jsonValue.isValue() || jsonValue.isNull()) ? j : jsonValue.asLong();
    }

    public int getInt(String str, int i) {
        JsonValue jsonValue = get(str);
        return (jsonValue == null || !jsonValue.isValue() || jsonValue.isNull()) ? i : jsonValue.asInt();
    }

    public boolean getBoolean(String str, boolean z) {
        JsonValue jsonValue = get(str);
        return (jsonValue == null || !jsonValue.isValue() || jsonValue.isNull()) ? z : jsonValue.asBoolean();
    }

    public byte getByte(String str, byte b2) {
        JsonValue jsonValue = get(str);
        return (jsonValue == null || !jsonValue.isValue() || jsonValue.isNull()) ? b2 : jsonValue.asByte();
    }

    public short getShort(String str, short s) {
        JsonValue jsonValue = get(str);
        return (jsonValue == null || !jsonValue.isValue() || jsonValue.isNull()) ? s : jsonValue.asShort();
    }

    public char getChar(String str, char c) {
        JsonValue jsonValue = get(str);
        return (jsonValue == null || !jsonValue.isValue() || jsonValue.isNull()) ? c : jsonValue.asChar();
    }

    public String getString(String str) {
        JsonValue jsonValue = get(str);
        if (jsonValue == null) {
            throw new IllegalArgumentException("Named value not found: " + str);
        }
        return jsonValue.asString();
    }

    public float getFloat(String str) {
        JsonValue jsonValue = get(str);
        if (jsonValue == null) {
            throw new IllegalArgumentException("Named value not found: " + str);
        }
        return jsonValue.asFloat();
    }

    public double getDouble(String str) {
        JsonValue jsonValue = get(str);
        if (jsonValue == null) {
            throw new IllegalArgumentException("Named value not found: " + str);
        }
        return jsonValue.asDouble();
    }

    public long getLong(String str) {
        JsonValue jsonValue = get(str);
        if (jsonValue == null) {
            throw new IllegalArgumentException("Named value not found: " + str);
        }
        return jsonValue.asLong();
    }

    public int getInt(String str) {
        JsonValue jsonValue = get(str);
        if (jsonValue == null) {
            throw new IllegalArgumentException("Named value not found: " + str);
        }
        return jsonValue.asInt();
    }

    public boolean getBoolean(String str) {
        JsonValue jsonValue = get(str);
        if (jsonValue == null) {
            throw new IllegalArgumentException("Named value not found: " + str);
        }
        return jsonValue.asBoolean();
    }

    public byte getByte(String str) {
        JsonValue jsonValue = get(str);
        if (jsonValue == null) {
            throw new IllegalArgumentException("Named value not found: " + str);
        }
        return jsonValue.asByte();
    }

    public short getShort(String str) {
        JsonValue jsonValue = get(str);
        if (jsonValue == null) {
            throw new IllegalArgumentException("Named value not found: " + str);
        }
        return jsonValue.asShort();
    }

    public char getChar(String str) {
        JsonValue jsonValue = get(str);
        if (jsonValue == null) {
            throw new IllegalArgumentException("Named value not found: " + str);
        }
        return jsonValue.asChar();
    }

    public String getString(int i) {
        JsonValue jsonValue = get(i);
        if (jsonValue == null) {
            throw new IllegalArgumentException("Indexed value not found: " + this.name);
        }
        return jsonValue.asString();
    }

    public float getFloat(int i) {
        JsonValue jsonValue = get(i);
        if (jsonValue == null) {
            throw new IllegalArgumentException("Indexed value not found: " + this.name);
        }
        return jsonValue.asFloat();
    }

    public double getDouble(int i) {
        JsonValue jsonValue = get(i);
        if (jsonValue == null) {
            throw new IllegalArgumentException("Indexed value not found: " + this.name);
        }
        return jsonValue.asDouble();
    }

    public long getLong(int i) {
        JsonValue jsonValue = get(i);
        if (jsonValue == null) {
            throw new IllegalArgumentException("Indexed value not found: " + this.name);
        }
        return jsonValue.asLong();
    }

    public int getInt(int i) {
        JsonValue jsonValue = get(i);
        if (jsonValue == null) {
            throw new IllegalArgumentException("Indexed value not found: " + this.name);
        }
        return jsonValue.asInt();
    }

    public boolean getBoolean(int i) {
        JsonValue jsonValue = get(i);
        if (jsonValue == null) {
            throw new IllegalArgumentException("Indexed value not found: " + this.name);
        }
        return jsonValue.asBoolean();
    }

    public byte getByte(int i) {
        JsonValue jsonValue = get(i);
        if (jsonValue == null) {
            throw new IllegalArgumentException("Indexed value not found: " + this.name);
        }
        return jsonValue.asByte();
    }

    public short getShort(int i) {
        JsonValue jsonValue = get(i);
        if (jsonValue == null) {
            throw new IllegalArgumentException("Indexed value not found: " + this.name);
        }
        return jsonValue.asShort();
    }

    public char getChar(int i) {
        JsonValue jsonValue = get(i);
        if (jsonValue == null) {
            throw new IllegalArgumentException("Indexed value not found: " + this.name);
        }
        return jsonValue.asChar();
    }

    public ValueType type() {
        return this.type;
    }

    public void setType(ValueType valueType) {
        if (valueType == null) {
            throw new IllegalArgumentException("type cannot be null.");
        }
        this.type = valueType;
    }

    public boolean isArray() {
        return this.type == ValueType.array;
    }

    public boolean isObject() {
        return this.type == ValueType.object;
    }

    public boolean isString() {
        return this.type == ValueType.stringValue;
    }

    public boolean isNumber() {
        return this.type == ValueType.doubleValue || this.type == ValueType.longValue;
    }

    public boolean isDouble() {
        return this.type == ValueType.doubleValue;
    }

    public boolean isLong() {
        return this.type == ValueType.longValue;
    }

    public boolean isBoolean() {
        return this.type == ValueType.booleanValue;
    }

    public boolean isNull() {
        return this.type == ValueType.nullValue;
    }

    public boolean isValue() {
        switch (this.type) {
            case stringValue:
            case doubleValue:
            case longValue:
            case booleanValue:
            case nullValue:
                return true;
            default:
                return false;
        }
    }

    @Null
    public String name() {
        return this.name;
    }

    public void setName(@Null String str) {
        this.name = str;
    }

    @Null
    public JsonValue parent() {
        return this.parent;
    }

    @Null
    public JsonValue child() {
        return this.child;
    }

    public void addChild(String str, JsonValue jsonValue) {
        if (str == null) {
            throw new IllegalArgumentException("name cannot be null.");
        }
        jsonValue.name = str;
        addChild(jsonValue);
    }

    public void addChild(JsonValue jsonValue) {
        if (this.type == ValueType.object && jsonValue.name == null) {
            throw new IllegalStateException("An object child requires a name: " + jsonValue);
        }
        jsonValue.parent = this;
        jsonValue.next = null;
        this.size++;
        JsonValue jsonValue2 = this.child;
        JsonValue jsonValue3 = jsonValue2;
        if (jsonValue2 == null) {
            jsonValue.prev = null;
            this.child = jsonValue;
        } else {
            while (jsonValue3.next != null) {
                jsonValue3 = jsonValue3.next;
            }
            jsonValue3.next = jsonValue;
            jsonValue.prev = jsonValue3;
        }
    }

    @Null
    public JsonValue next() {
        return this.next;
    }

    public void setNext(@Null JsonValue jsonValue) {
        this.next = jsonValue;
    }

    @Null
    public JsonValue prev() {
        return this.prev;
    }

    public void setPrev(@Null JsonValue jsonValue) {
        this.prev = jsonValue;
    }

    public void set(@Null String str) {
        this.stringValue = str;
        this.type = str == null ? ValueType.nullValue : ValueType.stringValue;
    }

    public void set(double d, @Null String str) {
        this.doubleValue = d;
        this.longValue = (long) d;
        this.stringValue = str;
        this.type = ValueType.doubleValue;
    }

    public void set(long j, @Null String str) {
        this.longValue = j;
        this.doubleValue = j;
        this.stringValue = str;
        this.type = ValueType.longValue;
    }

    public void set(boolean z) {
        this.longValue = z ? 1L : 0L;
        this.type = ValueType.booleanValue;
    }

    public String toJson(JsonWriter.OutputType outputType) {
        if (isValue()) {
            return asString();
        }
        StringBuilder stringBuilder = new StringBuilder(512);
        json(this, stringBuilder, outputType);
        return stringBuilder.toString();
    }

    private void json(JsonValue jsonValue, StringBuilder stringBuilder, JsonWriter.OutputType outputType) {
        if (jsonValue.isObject()) {
            if (jsonValue.child == null) {
                stringBuilder.append("{}");
                return;
            }
            stringBuilder.length();
            stringBuilder.append('{');
            JsonValue jsonValue2 = jsonValue.child;
            while (true) {
                JsonValue jsonValue3 = jsonValue2;
                if (jsonValue3 != null) {
                    stringBuilder.append(outputType.quoteName(jsonValue3.name));
                    stringBuilder.append(':');
                    json(jsonValue3, stringBuilder, outputType);
                    if (jsonValue3.next != null) {
                        stringBuilder.append(',');
                    }
                    jsonValue2 = jsonValue3.next;
                } else {
                    stringBuilder.append('}');
                    return;
                }
            }
        } else if (jsonValue.isArray()) {
            if (jsonValue.child == null) {
                stringBuilder.append("[]");
                return;
            }
            stringBuilder.length();
            stringBuilder.append('[');
            JsonValue jsonValue4 = jsonValue.child;
            while (true) {
                JsonValue jsonValue5 = jsonValue4;
                if (jsonValue5 != null) {
                    json(jsonValue5, stringBuilder, outputType);
                    if (jsonValue5.next != null) {
                        stringBuilder.append(',');
                    }
                    jsonValue4 = jsonValue5.next;
                } else {
                    stringBuilder.append(']');
                    return;
                }
            }
        } else {
            if (jsonValue.isString()) {
                stringBuilder.append(outputType.quoteValue(jsonValue.asString()));
                return;
            }
            if (jsonValue.isDouble()) {
                double asDouble = jsonValue.asDouble();
                long asLong = jsonValue.asLong();
                stringBuilder.append(asDouble == ((double) asLong) ? asLong : asDouble);
            } else if (jsonValue.isLong()) {
                stringBuilder.append(jsonValue.asLong());
            } else if (jsonValue.isBoolean()) {
                stringBuilder.append(jsonValue.asBoolean());
            } else {
                if (jsonValue.isNull()) {
                    stringBuilder.append("null");
                    return;
                }
                throw new SerializationException("Unknown object type: " + jsonValue);
            }
        }
    }

    @Override // java.lang.Iterable
    /* renamed from: iterator, reason: merged with bridge method [inline-methods] */
    public Iterator<JsonValue> iterator2() {
        return new JsonIterator();
    }

    public String toString() {
        if (isValue()) {
            return this.name == null ? asString() : this.name + ": " + asString();
        }
        return (this.name == null ? "" : this.name + ": ") + prettyPrint(JsonWriter.OutputType.minimal, 0);
    }

    public String trace() {
        String str;
        if (this.parent == null) {
            return this.type == ValueType.array ? "[]" : this.type == ValueType.object ? "{}" : "";
        }
        if (this.parent.type == ValueType.array) {
            str = "[]";
            int i = 0;
            JsonValue jsonValue = this.parent.child;
            while (true) {
                if (jsonValue == null) {
                    break;
                }
                if (jsonValue != this) {
                    jsonValue = jsonValue.next;
                    i++;
                } else {
                    str = "[" + i + "]";
                    break;
                }
            }
        } else if (this.name.indexOf(46) != -1) {
            str = ".\"" + this.name.replace("\"", "\\\"") + "\"";
        } else {
            str = "." + this.name;
        }
        return this.parent.trace() + str;
    }

    public String prettyPrint(JsonWriter.OutputType outputType, int i) {
        PrettyPrintSettings prettyPrintSettings = new PrettyPrintSettings();
        prettyPrintSettings.outputType = outputType;
        prettyPrintSettings.singleLineColumns = i;
        return prettyPrint(prettyPrintSettings);
    }

    public String prettyPrint(PrettyPrintSettings prettyPrintSettings) {
        StringBuilder stringBuilder = new StringBuilder(512);
        prettyPrint(this, stringBuilder, 0, prettyPrintSettings);
        return stringBuilder.toString();
    }

    private void prettyPrint(JsonValue jsonValue, StringBuilder stringBuilder, int i, PrettyPrintSettings prettyPrintSettings) {
        JsonWriter.OutputType outputType = prettyPrintSettings.outputType;
        if (jsonValue.isObject()) {
            if (jsonValue.child == null) {
                stringBuilder.append("{}");
                return;
            }
            boolean z = !isFlat(jsonValue);
            int length = stringBuilder.length();
            loop0: while (true) {
                stringBuilder.append(z ? "{\n" : "{ ");
                JsonValue jsonValue2 = jsonValue.child;
                while (true) {
                    JsonValue jsonValue3 = jsonValue2;
                    if (jsonValue3 == null) {
                        break loop0;
                    }
                    if (z) {
                        indent(i, stringBuilder);
                    }
                    stringBuilder.append(outputType.quoteName(jsonValue3.name));
                    stringBuilder.append(": ");
                    prettyPrint(jsonValue3, stringBuilder, i + 1, prettyPrintSettings);
                    if ((!z || outputType != JsonWriter.OutputType.minimal) && jsonValue3.next != null) {
                        stringBuilder.append(',');
                    }
                    stringBuilder.append(z ? '\n' : ' ');
                    if (z || stringBuilder.length() - length <= prettyPrintSettings.singleLineColumns) {
                        jsonValue2 = jsonValue3.next;
                    }
                }
                stringBuilder.setLength(length);
                z = true;
            }
            if (z) {
                indent(i - 1, stringBuilder);
            }
            stringBuilder.append('}');
            return;
        }
        if (jsonValue.isArray()) {
            if (jsonValue.child == null) {
                stringBuilder.append("[]");
                return;
            }
            boolean z2 = !isFlat(jsonValue);
            boolean z3 = prettyPrintSettings.wrapNumericArrays || !isNumeric(jsonValue);
            int length2 = stringBuilder.length();
            loop2: while (true) {
                stringBuilder.append(z2 ? "[\n" : "[ ");
                JsonValue jsonValue4 = jsonValue.child;
                while (true) {
                    JsonValue jsonValue5 = jsonValue4;
                    if (jsonValue5 == null) {
                        break loop2;
                    }
                    if (z2) {
                        indent(i, stringBuilder);
                    }
                    prettyPrint(jsonValue5, stringBuilder, i + 1, prettyPrintSettings);
                    if ((!z2 || outputType != JsonWriter.OutputType.minimal) && jsonValue5.next != null) {
                        stringBuilder.append(',');
                    }
                    stringBuilder.append(z2 ? '\n' : ' ');
                    if (!z3 || z2 || stringBuilder.length() - length2 <= prettyPrintSettings.singleLineColumns) {
                        jsonValue4 = jsonValue5.next;
                    }
                }
                stringBuilder.setLength(length2);
                z2 = true;
            }
            if (z2) {
                indent(i - 1, stringBuilder);
            }
            stringBuilder.append(']');
            return;
        }
        if (jsonValue.isString()) {
            stringBuilder.append(outputType.quoteValue(jsonValue.asString()));
            return;
        }
        if (jsonValue.isDouble()) {
            double asDouble = jsonValue.asDouble();
            long asLong = jsonValue.asLong();
            stringBuilder.append(asDouble == ((double) asLong) ? asLong : asDouble);
        } else if (jsonValue.isLong()) {
            stringBuilder.append(jsonValue.asLong());
        } else if (jsonValue.isBoolean()) {
            stringBuilder.append(jsonValue.asBoolean());
        } else {
            if (jsonValue.isNull()) {
                stringBuilder.append("null");
                return;
            }
            throw new SerializationException("Unknown object type: " + jsonValue);
        }
    }

    public void prettyPrint(JsonWriter.OutputType outputType, Writer writer) {
        PrettyPrintSettings prettyPrintSettings = new PrettyPrintSettings();
        prettyPrintSettings.outputType = outputType;
        prettyPrint(this, writer, 0, prettyPrintSettings);
    }

    private void prettyPrint(JsonValue jsonValue, Writer writer, int i, PrettyPrintSettings prettyPrintSettings) {
        JsonWriter.OutputType outputType = prettyPrintSettings.outputType;
        if (jsonValue.isObject()) {
            if (jsonValue.child == null) {
                writer.append("{}");
                return;
            }
            boolean z = !isFlat(jsonValue) || jsonValue.size > 6;
            writer.append((CharSequence) (z ? "{\n" : "{ "));
            JsonValue jsonValue2 = jsonValue.child;
            while (true) {
                JsonValue jsonValue3 = jsonValue2;
                if (jsonValue3 == null) {
                    break;
                }
                if (z) {
                    indent(i, writer);
                }
                writer.append((CharSequence) outputType.quoteName(jsonValue3.name));
                writer.append(": ");
                prettyPrint(jsonValue3, writer, i + 1, prettyPrintSettings);
                if ((!z || outputType != JsonWriter.OutputType.minimal) && jsonValue3.next != null) {
                    writer.append(',');
                }
                writer.append(z ? '\n' : ' ');
                jsonValue2 = jsonValue3.next;
            }
            if (z) {
                indent(i - 1, writer);
            }
            writer.append('}');
            return;
        }
        if (jsonValue.isArray()) {
            if (jsonValue.child == null) {
                writer.append("[]");
                return;
            }
            boolean z2 = !isFlat(jsonValue);
            writer.append((CharSequence) (z2 ? "[\n" : "[ "));
            JsonValue jsonValue4 = jsonValue.child;
            while (true) {
                JsonValue jsonValue5 = jsonValue4;
                if (jsonValue5 == null) {
                    break;
                }
                if (z2) {
                    indent(i, writer);
                }
                prettyPrint(jsonValue5, writer, i + 1, prettyPrintSettings);
                if ((!z2 || outputType != JsonWriter.OutputType.minimal) && jsonValue5.next != null) {
                    writer.append(',');
                }
                writer.append(z2 ? '\n' : ' ');
                jsonValue4 = jsonValue5.next;
            }
            if (z2) {
                indent(i - 1, writer);
            }
            writer.append(']');
            return;
        }
        if (jsonValue.isString()) {
            writer.append((CharSequence) outputType.quoteValue(jsonValue.asString()));
            return;
        }
        if (jsonValue.isDouble()) {
            double asDouble = jsonValue.asDouble();
            long asLong = jsonValue.asLong();
            writer.append((CharSequence) Double.toString(asDouble == ((double) asLong) ? asLong : asDouble));
        } else if (jsonValue.isLong()) {
            writer.append((CharSequence) Long.toString(jsonValue.asLong()));
        } else if (jsonValue.isBoolean()) {
            writer.append((CharSequence) Boolean.toString(jsonValue.asBoolean()));
        } else {
            if (jsonValue.isNull()) {
                writer.append("null");
                return;
            }
            throw new SerializationException("Unknown object type: " + jsonValue);
        }
    }

    private static boolean isFlat(JsonValue jsonValue) {
        JsonValue jsonValue2 = jsonValue.child;
        while (true) {
            JsonValue jsonValue3 = jsonValue2;
            if (jsonValue3 != null) {
                if (jsonValue3.isObject() || jsonValue3.isArray()) {
                    return false;
                }
                jsonValue2 = jsonValue3.next;
            } else {
                return true;
            }
        }
    }

    private static boolean isNumeric(JsonValue jsonValue) {
        JsonValue jsonValue2 = jsonValue.child;
        while (true) {
            JsonValue jsonValue3 = jsonValue2;
            if (jsonValue3 != null) {
                if (!jsonValue3.isNumber()) {
                    return false;
                }
                jsonValue2 = jsonValue3.next;
            } else {
                return true;
            }
        }
    }

    private static void indent(int i, StringBuilder stringBuilder) {
        for (int i2 = 0; i2 < i; i2++) {
            stringBuilder.append('\t');
        }
    }

    private static void indent(int i, Writer writer) {
        for (int i2 = 0; i2 < i; i2++) {
            writer.append('\t');
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/JsonValue$JsonIterator.class */
    public class JsonIterator implements Iterable<JsonValue>, Iterator<JsonValue> {
        JsonValue entry;
        JsonValue current;

        public JsonIterator() {
            this.entry = JsonValue.this.child;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.entry != null;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public JsonValue next() {
            this.current = this.entry;
            if (this.current == null) {
                throw new NoSuchElementException();
            }
            this.entry = this.current.next;
            return this.current;
        }

        @Override // java.util.Iterator
        public void remove() {
            if (this.current.prev == null) {
                JsonValue.this.child = this.current.next;
                if (JsonValue.this.child != null) {
                    JsonValue.this.child.prev = null;
                }
            } else {
                this.current.prev.next = this.current.next;
                if (this.current.next != null) {
                    this.current.next.prev = this.current.prev;
                }
            }
            JsonValue.this.size--;
        }

        @Override // java.lang.Iterable
        public Iterator<JsonValue> iterator() {
            return this;
        }
    }
}
