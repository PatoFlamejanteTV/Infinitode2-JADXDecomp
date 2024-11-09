package com.badlogic.gdx.utils;

import java.io.Writer;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/XmlWriter.class */
public class XmlWriter extends Writer {
    private final Writer writer;
    private final Array<String> stack = new Array<>();
    private String currentElement;
    private boolean indentNextClose;
    public int indent;

    public XmlWriter(Writer writer) {
        this.writer = writer;
    }

    private void indent() {
        int i = this.indent;
        if (this.currentElement != null) {
            i++;
        }
        for (int i2 = 0; i2 < i; i2++) {
            this.writer.write(9);
        }
    }

    public XmlWriter element(String str) {
        if (startElementContent()) {
            this.writer.write(10);
        }
        indent();
        this.writer.write(60);
        this.writer.write(str);
        this.currentElement = str;
        return this;
    }

    public XmlWriter element(String str, Object obj) {
        return element(str).text(obj).pop();
    }

    private boolean startElementContent() {
        if (this.currentElement == null) {
            return false;
        }
        this.indent++;
        this.stack.add(this.currentElement);
        this.currentElement = null;
        this.writer.write(">");
        return true;
    }

    public XmlWriter attribute(String str, Object obj) {
        if (this.currentElement == null) {
            throw new IllegalStateException();
        }
        this.writer.write(32);
        this.writer.write(str);
        this.writer.write("=\"");
        this.writer.write(obj == null ? "null" : obj.toString());
        this.writer.write(34);
        return this;
    }

    public XmlWriter text(Object obj) {
        startElementContent();
        String obj2 = obj == null ? "null" : obj.toString();
        this.indentNextClose = obj2.length() > 64;
        if (this.indentNextClose) {
            this.writer.write(10);
            indent();
        }
        this.writer.write(obj2);
        if (this.indentNextClose) {
            this.writer.write(10);
        }
        return this;
    }

    public XmlWriter pop() {
        if (this.currentElement != null) {
            this.writer.write("/>\n");
            this.currentElement = null;
        } else {
            this.indent = Math.max(this.indent - 1, 0);
            if (this.indentNextClose) {
                indent();
            }
            this.writer.write("</");
            this.writer.write(this.stack.pop());
            this.writer.write(">\n");
        }
        this.indentNextClose = true;
        return this;
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        while (this.stack.size != 0) {
            pop();
        }
        this.writer.close();
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i, int i2) {
        startElementContent();
        this.writer.write(cArr, i, i2);
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() {
        this.writer.flush();
    }
}
