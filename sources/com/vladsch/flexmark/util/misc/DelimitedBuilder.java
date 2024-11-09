package com.vladsch.flexmark.util.misc;

import java.util.List;
import java.util.Stack;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/misc/DelimitedBuilder.class */
public class DelimitedBuilder {
    private String delimiter;
    private StringBuilder out;
    private boolean pending;
    private int lastLen;
    private Stack<String> delimiterStack;

    public DelimitedBuilder() {
        this(",", 0);
    }

    public DelimitedBuilder(String str) {
        this(str, 0);
    }

    public DelimitedBuilder(String str, int i) {
        this.pending = false;
        this.lastLen = 0;
        this.delimiterStack = null;
        this.delimiter = str;
        this.out = i == 0 ? null : new StringBuilder(i);
    }

    public String toString() {
        if (this.delimiterStack == null || this.delimiterStack.isEmpty()) {
            return this.out == null ? "" : this.out.toString();
        }
        throw new IllegalStateException("Delimiter stack is not empty");
    }

    public boolean isEmpty() {
        if (this.pending) {
            return false;
        }
        return this.out == null || this.out.length() == 0;
    }

    public String getAndClear() {
        if (this.delimiterStack != null && !this.delimiterStack.isEmpty()) {
            throw new IllegalStateException("Delimiter stack is not empty");
        }
        String sb = this.out == null ? "" : this.out.toString();
        clear();
        return sb;
    }

    public DelimitedBuilder clear() {
        this.out = null;
        unmark();
        return this;
    }

    public String toStringOrNull() {
        if (this.delimiterStack != null && !this.delimiterStack.isEmpty()) {
            throw new IllegalStateException("Delimiter stack is not empty");
        }
        if (this.out == null) {
            return null;
        }
        return this.out.toString();
    }

    public DelimitedBuilder mark() {
        int length = this.out != null ? this.out.length() : 0;
        if (this.lastLen != length) {
            this.pending = true;
        }
        this.lastLen = length;
        return this;
    }

    public DelimitedBuilder unmark() {
        this.pending = false;
        this.lastLen = this.out != null ? this.out.length() : 0;
        return this;
    }

    public DelimitedBuilder push() {
        return push(this.delimiter);
    }

    public DelimitedBuilder push(String str) {
        unmark();
        if (this.delimiterStack == null) {
            this.delimiterStack = new Stack<>();
        }
        this.delimiterStack.push(this.delimiter);
        this.delimiter = str;
        return this;
    }

    public DelimitedBuilder pop() {
        if (this.delimiterStack == null || this.delimiterStack.isEmpty()) {
            throw new IllegalStateException("Nothing on the delimiter stack");
        }
        this.delimiter = this.delimiterStack.pop();
        return this;
    }

    private void doPending() {
        if (this.out == null) {
            this.out = new StringBuilder();
        }
        if (this.pending) {
            this.out.append(this.delimiter);
            this.pending = false;
        }
    }

    public DelimitedBuilder append(char c) {
        doPending();
        this.out.append(c);
        return this;
    }

    public DelimitedBuilder append(int i) {
        doPending();
        this.out.append(i);
        return this;
    }

    public DelimitedBuilder append(boolean z) {
        doPending();
        this.out.append(z);
        return this;
    }

    public DelimitedBuilder append(long j) {
        doPending();
        this.out.append(j);
        return this;
    }

    public DelimitedBuilder append(float f) {
        doPending();
        this.out.append(f);
        return this;
    }

    public DelimitedBuilder append(double d) {
        doPending();
        this.out.append(d);
        return this;
    }

    public DelimitedBuilder append(String str) {
        if (str != null && !str.isEmpty()) {
            doPending();
            this.out.append(str);
        }
        return this;
    }

    public DelimitedBuilder append(String str, int i, int i2) {
        if (str != null && i < i2) {
            doPending();
            this.out.append((CharSequence) str, i, i2);
        }
        return this;
    }

    public DelimitedBuilder append(CharSequence charSequence) {
        if (charSequence != null && charSequence.length() > 0) {
            doPending();
            this.out.append(charSequence);
        }
        return this;
    }

    public DelimitedBuilder append(CharSequence charSequence, int i, int i2) {
        if (charSequence != null && i < i2) {
            doPending();
            this.out.append(charSequence, i, i2);
        }
        return this;
    }

    public DelimitedBuilder append(char[] cArr) {
        if (cArr.length > 0) {
            doPending();
            this.out.append(cArr);
        }
        return this;
    }

    public DelimitedBuilder append(char[] cArr, int i, int i2) {
        if (i < i2) {
            doPending();
            this.out.append(cArr, i, i2);
        }
        return this;
    }

    public DelimitedBuilder append(Object obj) {
        return append(obj.toString());
    }

    public DelimitedBuilder appendCodePoint(int i) {
        doPending();
        this.out.appendCodePoint(i);
        return this;
    }

    public <V> DelimitedBuilder appendAll(V[] vArr) {
        return appendAll(vArr, 0, vArr.length);
    }

    public <V> DelimitedBuilder appendAll(V[] vArr, int i, int i2) {
        for (int i3 = i; i3 < i2; i3++) {
            append(vArr[i3].toString());
            mark();
        }
        return this;
    }

    public <V> DelimitedBuilder appendAll(String str, V[] vArr) {
        return appendAll(str, vArr, 0, vArr.length);
    }

    public <V> DelimitedBuilder appendAll(String str, V[] vArr, int i, int i2) {
        int length = this.out != null ? this.out.length() : 0;
        push(str);
        appendAll(vArr, i, i2);
        pop();
        if (length != (this.out != null ? this.out.length() : 0)) {
            mark();
        } else {
            unmark();
        }
        return this;
    }

    public <V> DelimitedBuilder appendAll(List<? extends V> list) {
        return appendAll(list, 0, list.size());
    }

    public <V> DelimitedBuilder appendAll(List<? extends V> list, int i, int i2) {
        for (int i3 = i; i3 < i2; i3++) {
            append(list.get(i3).toString());
            mark();
        }
        return this;
    }

    public <V> DelimitedBuilder appendAll(String str, List<? extends V> list) {
        return appendAll(str, list, 0, list.size());
    }

    public <V> DelimitedBuilder appendAll(String str, List<? extends V> list, int i, int i2) {
        int length = this.out != null ? this.out.length() : 0;
        push(str);
        appendAll(list, i, i2);
        pop();
        if (length != (this.out != null ? this.out.length() : 0)) {
            mark();
        } else {
            unmark();
        }
        return this;
    }
}
