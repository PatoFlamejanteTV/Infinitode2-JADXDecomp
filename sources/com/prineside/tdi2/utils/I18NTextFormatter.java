package com.prineside.tdi2.utils;

import com.badlogic.gdx.utils.StringBuilder;
import java.text.MessageFormat;
import java.util.Locale;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/I18NTextFormatter.class */
class I18NTextFormatter {

    /* renamed from: a, reason: collision with root package name */
    private MessageFormat f3840a;

    /* renamed from: b, reason: collision with root package name */
    private StringBuilder f3841b = new StringBuilder();

    public I18NTextFormatter(Locale locale, boolean z) {
        if (z) {
            this.f3840a = new MessageFormat("", locale);
        }
    }

    public String format(String str, Object... objArr) {
        if (this.f3840a != null) {
            this.f3840a.applyPattern(a(str));
            return this.f3840a.format(objArr);
        }
        return a(str, objArr);
    }

    private String a(String str) {
        this.f3841b.setLength(0);
        boolean z = false;
        int length = str.length();
        int i = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            if (charAt == '\'') {
                z = true;
                this.f3841b.append("''");
            } else if (charAt != '{') {
                this.f3841b.append(charAt);
            } else {
                int i2 = i + 1;
                while (i2 < length && str.charAt(i2) == '{') {
                    i2++;
                }
                int i3 = (i2 - i) / 2;
                int i4 = i3;
                if (i3 > 0) {
                    z = true;
                    this.f3841b.append('\'');
                    do {
                        this.f3841b.append('{');
                        i4--;
                    } while (i4 > 0);
                    this.f3841b.append('\'');
                }
                if ((i2 - i) % 2 != 0) {
                    this.f3841b.append('{');
                }
                i = i2 - 1;
            }
            i++;
        }
        return z ? this.f3841b.toString() : str;
    }

    private String a(String str, Object... objArr) {
        this.f3841b.setLength(0);
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
                        this.f3841b.append(charAt);
                        i2++;
                    } else {
                        i = 0;
                    }
                } else {
                    this.f3841b.append(charAt);
                }
            } else if (charAt == '}') {
                if (i >= objArr.length) {
                    throw new IllegalArgumentException("Argument index out of bounds: " + i);
                }
                if (str.charAt(i2 - 1) == '{') {
                    throw new IllegalArgumentException("Missing argument index after a left curly brace");
                }
                if (objArr[i] == null) {
                    this.f3841b.append("null");
                } else {
                    this.f3841b.append(objArr[i].toString());
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
        return z ? this.f3841b.toString() : str;
    }
}
