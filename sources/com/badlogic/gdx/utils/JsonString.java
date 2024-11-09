package com.badlogic.gdx.utils;

import com.badlogic.gdx.utils.JsonWriter;
import java.math.BigDecimal;
import java.math.BigInteger;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/JsonString.class */
public class JsonString {
    private JsonObject current;
    private boolean named;
    private final Array<JsonObject> stack = new Array<>();
    private JsonWriter.OutputType outputType = JsonWriter.OutputType.json;
    private boolean quoteLongValues = false;
    final StringBuilder buffer = new StringBuilder();

    public StringBuilder getBuffer() {
        return this.buffer;
    }

    public void setOutputType(JsonWriter.OutputType outputType) {
        this.outputType = outputType;
    }

    public void setQuoteLongValues(boolean z) {
        this.quoteLongValues = z;
    }

    public JsonString name(String str) {
        if (this.current == null || this.current.array) {
            throw new IllegalStateException("Current item must be an object.");
        }
        if (!this.current.needsComma) {
            this.current.needsComma = true;
        } else {
            this.buffer.append(',');
        }
        this.buffer.append(this.outputType.quoteName(str));
        this.buffer.append(':');
        this.named = true;
        return this;
    }

    public JsonString object() {
        requireCommaOrName();
        Array<JsonObject> array = this.stack;
        JsonObject jsonObject = new JsonObject(false);
        this.current = jsonObject;
        array.add(jsonObject);
        return this;
    }

    public JsonString array() {
        requireCommaOrName();
        Array<JsonObject> array = this.stack;
        JsonObject jsonObject = new JsonObject(true);
        this.current = jsonObject;
        array.add(jsonObject);
        return this;
    }

    public JsonString value(@Null Object obj) {
        if (this.quoteLongValues && ((obj instanceof Long) || (obj instanceof Double) || (obj instanceof BigDecimal) || (obj instanceof BigInteger))) {
            obj = obj.toString();
        } else if (obj instanceof Number) {
            Number number = (Number) obj;
            long longValue = number.longValue();
            if (number.doubleValue() == longValue) {
                obj = Long.valueOf(longValue);
            }
        }
        requireCommaOrName();
        this.buffer.append(this.outputType.quoteValue(obj));
        return this;
    }

    public JsonString json(String str) {
        requireCommaOrName();
        this.buffer.append(str);
        return this;
    }

    private void requireCommaOrName() {
        if (this.current == null) {
            return;
        }
        if (this.current.array) {
            if (!this.current.needsComma) {
                this.current.needsComma = true;
                return;
            } else {
                this.buffer.append(',');
                return;
            }
        }
        if (!this.named) {
            throw new IllegalStateException("Name must be set.");
        }
        this.named = false;
    }

    public JsonString object(String str) {
        return name(str).object();
    }

    public JsonString array(String str) {
        return name(str).array();
    }

    public JsonString set(String str, Object obj) {
        return name(str).value(obj);
    }

    public JsonString json(String str, String str2) {
        return name(str).json(str2);
    }

    public JsonString pop() {
        if (this.named) {
            throw new IllegalStateException("Expected an object, array, or value since a name was set.");
        }
        this.stack.pop().close();
        this.current = this.stack.size == 0 ? null : this.stack.peek();
        return this;
    }

    public JsonString close() {
        while (this.stack.size > 0) {
            pop();
        }
        return this;
    }

    public void reset() {
        this.buffer.clear();
        this.stack.clear();
        this.current = null;
        this.named = false;
    }

    public String toString() {
        return this.buffer.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/JsonString$JsonObject.class */
    public class JsonObject {
        final boolean array;
        boolean needsComma;

        JsonObject(boolean z) {
            this.array = z;
            JsonString.this.buffer.append(z ? '[' : '{');
        }

        void close() {
            JsonString.this.buffer.append(this.array ? ']' : '}');
        }
    }
}
