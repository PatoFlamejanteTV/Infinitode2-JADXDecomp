package com.badlogic.gdx.utils;

import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.regex.Pattern;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/JsonWriter.class */
public class JsonWriter extends Writer {
    final Writer writer;
    private JsonObject current;
    private boolean named;
    private final Array<JsonObject> stack = new Array<>();
    private OutputType outputType = OutputType.json;
    private boolean quoteLongValues = false;

    public JsonWriter(Writer writer) {
        this.writer = writer;
    }

    public Writer getWriter() {
        return this.writer;
    }

    public void setOutputType(OutputType outputType) {
        this.outputType = outputType;
    }

    public void setQuoteLongValues(boolean z) {
        this.quoteLongValues = z;
    }

    public JsonWriter name(String str) {
        if (this.current == null || this.current.array) {
            throw new IllegalStateException("Current item must be an object.");
        }
        if (!this.current.needsComma) {
            this.current.needsComma = true;
        } else {
            this.writer.write(44);
        }
        this.writer.write(this.outputType.quoteName(str));
        this.writer.write(58);
        this.named = true;
        return this;
    }

    public JsonWriter object() {
        requireCommaOrName();
        Array<JsonObject> array = this.stack;
        JsonObject jsonObject = new JsonObject(false);
        this.current = jsonObject;
        array.add(jsonObject);
        return this;
    }

    public JsonWriter array() {
        requireCommaOrName();
        Array<JsonObject> array = this.stack;
        JsonObject jsonObject = new JsonObject(true);
        this.current = jsonObject;
        array.add(jsonObject);
        return this;
    }

    public JsonWriter value(@Null Object obj) {
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
        this.writer.write(this.outputType.quoteValue(obj));
        return this;
    }

    public JsonWriter json(String str) {
        requireCommaOrName();
        this.writer.write(str);
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
                this.writer.write(44);
                return;
            }
        }
        if (!this.named) {
            throw new IllegalStateException("Name must be set.");
        }
        this.named = false;
    }

    public JsonWriter object(String str) {
        return name(str).object();
    }

    public JsonWriter array(String str) {
        return name(str).array();
    }

    public JsonWriter set(String str, Object obj) {
        return name(str).value(obj);
    }

    public JsonWriter json(String str, String str2) {
        return name(str).json(str2);
    }

    public JsonWriter pop() {
        if (this.named) {
            throw new IllegalStateException("Expected an object, array, or value since a name was set.");
        }
        this.stack.pop().close();
        this.current = this.stack.size == 0 ? null : this.stack.peek();
        return this;
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i, int i2) {
        this.writer.write(cArr, i, i2);
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() {
        this.writer.flush();
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        while (this.stack.size > 0) {
            pop();
        }
        this.writer.close();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/JsonWriter$JsonObject.class */
    public class JsonObject {
        final boolean array;
        boolean needsComma;

        JsonObject(boolean z) {
            this.array = z;
            JsonWriter.this.writer.write(z ? 91 : 123);
        }

        void close() {
            JsonWriter.this.writer.write(this.array ? 93 : 125);
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/JsonWriter$OutputType.class */
    public enum OutputType {
        json,
        javascript,
        minimal;

        private static Pattern javascriptPattern = Pattern.compile("^[a-zA-Z_$][a-zA-Z_$0-9]*$");
        private static Pattern minimalNamePattern = Pattern.compile("^[^\":,}/ ][^:]*$");
        private static Pattern minimalValuePattern = Pattern.compile("^[^\":,{\\[\\]/ ][^}\\],]*$");

        public final String quoteValue(@Null Object obj) {
            int length;
            if (obj == null) {
                return "null";
            }
            String obj2 = obj.toString();
            if ((obj instanceof Number) || (obj instanceof Boolean)) {
                return obj2;
            }
            StringBuilder stringBuilder = new StringBuilder(obj2);
            stringBuilder.replace('\\', "\\\\").replace('\r', "\\r").replace('\n', "\\n").replace('\t', "\\t");
            if (this != minimal || obj2.equals("true") || obj2.equals("false") || obj2.equals("null") || obj2.contains("//") || obj2.contains("/*") || (length = stringBuilder.length()) <= 0 || stringBuilder.charAt(length - 1) == ' ' || !minimalValuePattern.matcher(stringBuilder).matches()) {
                return "\"" + stringBuilder.replace('\"', "\\\"").toString() + '\"';
            }
            return stringBuilder.toString();
        }

        /* JADX WARN: Failed to find 'out' block for switch in B:2:0x002f. Please report as an issue. */
        public final String quoteName(String str) {
            StringBuilder stringBuilder = new StringBuilder(str);
            stringBuilder.replace('\\', "\\\\").replace('\r', "\\r").replace('\n', "\\n").replace('\t', "\\t");
            switch (this) {
                case minimal:
                    if (!str.contains("//") && !str.contains("/*") && minimalNamePattern.matcher(stringBuilder).matches()) {
                        return stringBuilder.toString();
                    }
                    break;
                case javascript:
                    if (javascriptPattern.matcher(stringBuilder).matches()) {
                        return stringBuilder.toString();
                    }
                default:
                    return "\"" + stringBuilder.replace('\"', "\\\"").toString() + '\"';
            }
        }
    }
}
