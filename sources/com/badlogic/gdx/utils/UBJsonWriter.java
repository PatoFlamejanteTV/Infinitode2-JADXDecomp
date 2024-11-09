package com.badlogic.gdx.utils;

import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/UBJsonWriter.class */
public class UBJsonWriter implements Closeable {
    final DataOutputStream out;
    private JsonObject current;
    private boolean named;
    private final Array<JsonObject> stack = new Array<>();

    public UBJsonWriter(OutputStream outputStream) {
        this.out = (DataOutputStream) (outputStream instanceof DataOutputStream ? outputStream : new DataOutputStream(outputStream));
    }

    public UBJsonWriter object() {
        if (this.current != null && !this.current.array) {
            if (!this.named) {
                throw new IllegalStateException("Name must be set.");
            }
            this.named = false;
        }
        Array<JsonObject> array = this.stack;
        JsonObject jsonObject = new JsonObject(false);
        this.current = jsonObject;
        array.add(jsonObject);
        return this;
    }

    public UBJsonWriter object(String str) {
        name(str).object();
        return this;
    }

    public UBJsonWriter array() {
        if (this.current != null && !this.current.array) {
            if (!this.named) {
                throw new IllegalStateException("Name must be set.");
            }
            this.named = false;
        }
        Array<JsonObject> array = this.stack;
        JsonObject jsonObject = new JsonObject(true);
        this.current = jsonObject;
        array.add(jsonObject);
        return this;
    }

    public UBJsonWriter array(String str) {
        name(str).array();
        return this;
    }

    public UBJsonWriter name(String str) {
        if (this.current == null || this.current.array) {
            throw new IllegalStateException("Current item must be an object.");
        }
        byte[] bytes = str.getBytes("UTF-8");
        if (bytes.length <= 127) {
            this.out.writeByte(105);
            this.out.writeByte(bytes.length);
        } else if (bytes.length <= 32767) {
            this.out.writeByte(73);
            this.out.writeShort(bytes.length);
        } else {
            this.out.writeByte(108);
            this.out.writeInt(bytes.length);
        }
        this.out.write(bytes);
        this.named = true;
        return this;
    }

    public UBJsonWriter value(byte b2) {
        checkName();
        this.out.writeByte(105);
        this.out.writeByte(b2);
        return this;
    }

    public UBJsonWriter value(short s) {
        checkName();
        this.out.writeByte(73);
        this.out.writeShort(s);
        return this;
    }

    public UBJsonWriter value(int i) {
        checkName();
        this.out.writeByte(108);
        this.out.writeInt(i);
        return this;
    }

    public UBJsonWriter value(long j) {
        checkName();
        this.out.writeByte(76);
        this.out.writeLong(j);
        return this;
    }

    public UBJsonWriter value(float f) {
        checkName();
        this.out.writeByte(100);
        this.out.writeFloat(f);
        return this;
    }

    public UBJsonWriter value(double d) {
        checkName();
        this.out.writeByte(68);
        this.out.writeDouble(d);
        return this;
    }

    public UBJsonWriter value(boolean z) {
        checkName();
        this.out.writeByte(z ? 84 : 70);
        return this;
    }

    public UBJsonWriter value(char c) {
        checkName();
        this.out.writeByte(73);
        this.out.writeChar(c);
        return this;
    }

    public UBJsonWriter value(String str) {
        checkName();
        byte[] bytes = str.getBytes("UTF-8");
        this.out.writeByte(83);
        if (bytes.length <= 127) {
            this.out.writeByte(105);
            this.out.writeByte(bytes.length);
        } else if (bytes.length <= 32767) {
            this.out.writeByte(73);
            this.out.writeShort(bytes.length);
        } else {
            this.out.writeByte(108);
            this.out.writeInt(bytes.length);
        }
        this.out.write(bytes);
        return this;
    }

    public UBJsonWriter value(byte[] bArr) {
        array();
        this.out.writeByte(36);
        this.out.writeByte(105);
        this.out.writeByte(35);
        value(bArr.length);
        for (byte b2 : bArr) {
            this.out.writeByte(b2);
        }
        pop(true);
        return this;
    }

    public UBJsonWriter value(short[] sArr) {
        array();
        this.out.writeByte(36);
        this.out.writeByte(73);
        this.out.writeByte(35);
        value(sArr.length);
        for (short s : sArr) {
            this.out.writeShort(s);
        }
        pop(true);
        return this;
    }

    public UBJsonWriter value(int[] iArr) {
        array();
        this.out.writeByte(36);
        this.out.writeByte(108);
        this.out.writeByte(35);
        value(iArr.length);
        for (int i : iArr) {
            this.out.writeInt(i);
        }
        pop(true);
        return this;
    }

    public UBJsonWriter value(long[] jArr) {
        array();
        this.out.writeByte(36);
        this.out.writeByte(76);
        this.out.writeByte(35);
        value(jArr.length);
        for (long j : jArr) {
            this.out.writeLong(j);
        }
        pop(true);
        return this;
    }

    public UBJsonWriter value(float[] fArr) {
        array();
        this.out.writeByte(36);
        this.out.writeByte(100);
        this.out.writeByte(35);
        value(fArr.length);
        for (float f : fArr) {
            this.out.writeFloat(f);
        }
        pop(true);
        return this;
    }

    public UBJsonWriter value(double[] dArr) {
        array();
        this.out.writeByte(36);
        this.out.writeByte(68);
        this.out.writeByte(35);
        value(dArr.length);
        for (double d : dArr) {
            this.out.writeDouble(d);
        }
        pop(true);
        return this;
    }

    public UBJsonWriter value(boolean[] zArr) {
        array();
        for (boolean z : zArr) {
            this.out.writeByte(z ? 84 : 70);
        }
        pop();
        return this;
    }

    public UBJsonWriter value(char[] cArr) {
        array();
        this.out.writeByte(36);
        this.out.writeByte(67);
        this.out.writeByte(35);
        value(cArr.length);
        for (char c : cArr) {
            this.out.writeChar(c);
        }
        pop(true);
        return this;
    }

    public UBJsonWriter value(String[] strArr) {
        array();
        this.out.writeByte(36);
        this.out.writeByte(83);
        this.out.writeByte(35);
        value(strArr.length);
        for (String str : strArr) {
            byte[] bytes = str.getBytes("UTF-8");
            if (bytes.length <= 127) {
                this.out.writeByte(105);
                this.out.writeByte(bytes.length);
            } else if (bytes.length <= 32767) {
                this.out.writeByte(73);
                this.out.writeShort(bytes.length);
            } else {
                this.out.writeByte(108);
                this.out.writeInt(bytes.length);
            }
            this.out.write(bytes);
        }
        pop(true);
        return this;
    }

    public UBJsonWriter value(JsonValue jsonValue) {
        if (jsonValue.isObject()) {
            if (jsonValue.name != null) {
                object(jsonValue.name);
            } else {
                object();
            }
            JsonValue jsonValue2 = jsonValue.child;
            while (true) {
                JsonValue jsonValue3 = jsonValue2;
                if (jsonValue3 == null) {
                    break;
                }
                value(jsonValue3);
                jsonValue2 = jsonValue3.next;
            }
            pop();
        } else if (jsonValue.isArray()) {
            if (jsonValue.name != null) {
                array(jsonValue.name);
            } else {
                array();
            }
            JsonValue jsonValue4 = jsonValue.child;
            while (true) {
                JsonValue jsonValue5 = jsonValue4;
                if (jsonValue5 == null) {
                    break;
                }
                value(jsonValue5);
                jsonValue4 = jsonValue5.next;
            }
            pop();
        } else if (jsonValue.isBoolean()) {
            if (jsonValue.name != null) {
                name(jsonValue.name);
            }
            value(jsonValue.asBoolean());
        } else if (jsonValue.isDouble()) {
            if (jsonValue.name != null) {
                name(jsonValue.name);
            }
            value(jsonValue.asDouble());
        } else if (jsonValue.isLong()) {
            if (jsonValue.name != null) {
                name(jsonValue.name);
            }
            value(jsonValue.asLong());
        } else if (jsonValue.isString()) {
            if (jsonValue.name != null) {
                name(jsonValue.name);
            }
            value(jsonValue.asString());
        } else if (jsonValue.isNull()) {
            if (jsonValue.name != null) {
                name(jsonValue.name);
            }
            value();
        } else {
            throw new IOException("Unhandled JsonValue type");
        }
        return this;
    }

    public UBJsonWriter value(Object obj) {
        if (obj == null) {
            return value();
        }
        if (obj instanceof Number) {
            Number number = (Number) obj;
            return obj instanceof Byte ? value(number.byteValue()) : obj instanceof Short ? value(number.shortValue()) : obj instanceof Integer ? value(number.intValue()) : obj instanceof Long ? value(number.longValue()) : obj instanceof Float ? value(number.floatValue()) : obj instanceof Double ? value(number.doubleValue()) : this;
        }
        if (obj instanceof Character) {
            return value(((Character) obj).charValue());
        }
        if (obj instanceof CharSequence) {
            return value(obj.toString());
        }
        throw new IOException("Unknown object type.");
    }

    public UBJsonWriter value() {
        checkName();
        this.out.writeByte(90);
        return this;
    }

    public UBJsonWriter set(String str, byte b2) {
        return name(str).value(b2);
    }

    public UBJsonWriter set(String str, short s) {
        return name(str).value(s);
    }

    public UBJsonWriter set(String str, int i) {
        return name(str).value(i);
    }

    public UBJsonWriter set(String str, long j) {
        return name(str).value(j);
    }

    public UBJsonWriter set(String str, float f) {
        return name(str).value(f);
    }

    public UBJsonWriter set(String str, double d) {
        return name(str).value(d);
    }

    public UBJsonWriter set(String str, boolean z) {
        return name(str).value(z);
    }

    public UBJsonWriter set(String str, char c) {
        return name(str).value(c);
    }

    public UBJsonWriter set(String str, String str2) {
        return name(str).value(str2);
    }

    public UBJsonWriter set(String str, byte[] bArr) {
        return name(str).value(bArr);
    }

    public UBJsonWriter set(String str, short[] sArr) {
        return name(str).value(sArr);
    }

    public UBJsonWriter set(String str, int[] iArr) {
        return name(str).value(iArr);
    }

    public UBJsonWriter set(String str, long[] jArr) {
        return name(str).value(jArr);
    }

    public UBJsonWriter set(String str, float[] fArr) {
        return name(str).value(fArr);
    }

    public UBJsonWriter set(String str, double[] dArr) {
        return name(str).value(dArr);
    }

    public UBJsonWriter set(String str, boolean[] zArr) {
        return name(str).value(zArr);
    }

    public UBJsonWriter set(String str, char[] cArr) {
        return name(str).value(cArr);
    }

    public UBJsonWriter set(String str, String[] strArr) {
        return name(str).value(strArr);
    }

    public UBJsonWriter set(String str) {
        return name(str).value();
    }

    private void checkName() {
        if (this.current != null && !this.current.array) {
            if (!this.named) {
                throw new IllegalStateException("Name must be set.");
            }
            this.named = false;
        }
    }

    public UBJsonWriter pop() {
        return pop(false);
    }

    protected UBJsonWriter pop(boolean z) {
        if (this.named) {
            throw new IllegalStateException("Expected an object, array, or value since a name was set.");
        }
        if (z) {
            this.stack.pop();
        } else {
            this.stack.pop().close();
        }
        this.current = this.stack.size == 0 ? null : this.stack.peek();
        return this;
    }

    public void flush() {
        this.out.flush();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        while (this.stack.size > 0) {
            pop();
        }
        this.out.close();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/UBJsonWriter$JsonObject.class */
    public class JsonObject {
        final boolean array;

        JsonObject(boolean z) {
            this.array = z;
            UBJsonWriter.this.out.writeByte(z ? 91 : 123);
        }

        void close() {
            UBJsonWriter.this.out.writeByte(this.array ? 93 : 125);
        }
    }
}
