package com.prineside.tdi2.ibxm;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ibxm/Note.class */
public class Note {
    public int key;
    public int instrument;
    public int volume;
    public int effect;
    public int param;

    public String toString() {
        return new String(toChars(new char[10]));
    }

    public char[] toChars(char[] cArr) {
        a(this.key, cArr);
        cArr[3] = (this.instrument <= 15 || this.instrument >= 255) ? '-' : "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt((this.instrument >> 4) & 15);
        cArr[4] = (this.instrument <= 0 || this.instrument >= 255) ? '-' : "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(this.instrument & 15);
        cArr[5] = (this.volume <= 15 || this.volume >= 255) ? '-' : "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt((this.volume >> 4) & 15);
        cArr[6] = (this.volume <= 0 || this.volume >= 255) ? '-' : "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(this.volume & 15);
        if ((this.effect > 0 || this.param > 0) && this.effect < 36) {
            cArr[7] = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(this.effect);
        } else if (this.effect > 128 && this.effect < 159) {
            cArr[7] = (char) (96 + (this.effect & 31));
        } else {
            cArr[7] = '-';
        }
        cArr[8] = (this.effect > 0 || this.param > 0) ? "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt((this.param >> 4) & 15) : '-';
        cArr[9] = (this.effect > 0 || this.param > 0) ? "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(this.param & 15) : '-';
        return cArr;
    }

    private static void a(int i, char[] cArr) {
        cArr[0] = (i <= 0 || i >= 118) ? '-' : "A-A#B-C-C#D-D#E-F-F#G-G#".charAt(((i + 2) % 12) << 1);
        cArr[1] = (i <= 0 || i >= 118) ? '-' : "A-A#B-C-C#D-D#E-F-F#G-G#".charAt((((i + 2) % 12) << 1) + 1);
        cArr[2] = (i <= 0 || i >= 118) ? '-' : (char) (48 + ((i + 2) / 12));
    }
}
