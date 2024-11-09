package com.prineside.tdi2.ibxm;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ibxm/Pattern.class */
public class Pattern {
    public int numRows;
    public byte[] data;

    public Pattern(Pattern pattern) {
        this.numRows = pattern.numRows;
        this.data = new byte[pattern.data.length];
        System.arraycopy(pattern.data, 0, this.data, 0, this.data.length);
    }

    public Pattern(int i, int i2) {
        this.numRows = i2;
        this.data = new byte[i * i2 * 5];
    }

    public Note getNote(int i, Note note) {
        int i2 = i * 5;
        note.key = this.data[i2] & 255;
        note.instrument = this.data[i2 + 1] & 255;
        note.volume = this.data[i2 + 2] & 255;
        note.effect = this.data[i2 + 3] & 255;
        note.param = this.data[i2 + 4] & 255;
        return note;
    }

    public void toStringBuffer(StringBuffer stringBuffer) {
        Note note = new Note();
        char[] cArr = new char[10];
        int length = this.data.length / (this.numRows * 5);
        for (int i = 0; i < this.numRows; i++) {
            for (int i2 = 0; i2 < length; i2++) {
                getNote((length * i) + i2, note);
                note.toChars(cArr);
                stringBuffer.append(cArr);
                stringBuffer.append(' ');
            }
            stringBuffer.append('\n');
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer((this.numRows * (this.data.length / (this.numRows * 5)) * 11) + this.numRows);
        toStringBuffer(stringBuffer);
        return stringBuffer.toString();
    }
}
