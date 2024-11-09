package com.badlogic.gdx.utils;

import java.text.MessageFormat;
import java.util.Locale;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/TextFormatter.class */
class TextFormatter {
    private MessageFormat messageFormat;
    private StringBuilder buffer = new StringBuilder();

    public TextFormatter(Locale locale, boolean z) {
        if (z) {
            this.messageFormat = new MessageFormat("", locale);
        }
    }

    public String format(String str, Object... objArr) {
        if (this.messageFormat != null) {
            this.messageFormat.applyPattern(replaceEscapeChars(str));
            return this.messageFormat.format(objArr);
        }
        return simpleFormat(str, objArr);
    }

    private String replaceEscapeChars(String str) {
        this.buffer.setLength(0);
        boolean z = false;
        int length = str.length();
        int i = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            if (charAt == '\'') {
                z = true;
                this.buffer.append("''");
            } else if (charAt != '{') {
                this.buffer.append(charAt);
            } else {
                int i2 = i + 1;
                while (i2 < length && str.charAt(i2) == '{') {
                    i2++;
                }
                int i3 = (i2 - i) / 2;
                int i4 = i3;
                if (i3 > 0) {
                    z = true;
                    this.buffer.append('\'');
                    do {
                        this.buffer.append('{');
                        i4--;
                    } while (i4 > 0);
                    this.buffer.append('\'');
                }
                if ((i2 - i) % 2 != 0) {
                    this.buffer.append('{');
                }
                i = i2 - 1;
            }
            i++;
        }
        return z ? this.buffer.toString() : str;
    }

    private String simpleFormat(String str, Object... objArr) {
        this.buffer.setLength(0);
        boolean z = false;
        int i = -1;
        int length = str.length();
        int i2 = 0;
        while (i2 < length) {
            char charAt = str.charAt(i2);
            if (i < 0) {
                if (charAt == '{') {
                    z = true;
                    if (i2 + 1 < length && str.charAt(i2 + 1) == '{') {
                        this.buffer.append(charAt);
                        i2++;
                    } else {
                        i = 0;
                    }
                } else {
                    this.buffer.append(charAt);
                }
            } else if (charAt == '}') {
                if (i >= objArr.length) {
                    throw new IllegalArgumentException("Argument index out of bounds: " + i);
                }
                if (str.charAt(i2 - 1) == '{') {
                    throw new IllegalArgumentException("Missing argument index after a left curly brace");
                }
                if (objArr[i] == null) {
                    this.buffer.append("null");
                } else {
                    this.buffer.append(objArr[i].toString());
                }
                i = -1;
            } else {
                if (charAt < '0' || charAt > '9') {
                    throw new IllegalArgumentException("Unexpected '" + charAt + "' while parsing argument index");
                }
                i = (i * 10) + (charAt - '0');
            }
            i2++;
        }
        if (i >= 0) {
            throw new IllegalArgumentException("Unmatched braces in the pattern.");
        }
        return z ? this.buffer.toString() : str;
    }
}
