package com.badlogic.gdx.utils;

import java.io.DataInputStream;
import java.io.InputStream;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/DataInput.class */
public class DataInput extends DataInputStream {
    private char[] chars;

    public DataInput(InputStream inputStream) {
        super(inputStream);
        this.chars = new char[32];
    }

    public int readInt(boolean z) {
        byte readByte = readByte();
        int i = readByte & Byte.MAX_VALUE;
        if ((readByte & 128) != 0) {
            byte readByte2 = readByte();
            i |= (readByte2 & Byte.MAX_VALUE) << 7;
            if ((readByte2 & 128) != 0) {
                byte readByte3 = readByte();
                i |= (readByte3 & Byte.MAX_VALUE) << 14;
                if ((readByte3 & 128) != 0) {
                    byte readByte4 = readByte();
                    i |= (readByte4 & Byte.MAX_VALUE) << 21;
                    if ((readByte4 & 128) != 0) {
                        i |= (readByte() & Byte.MAX_VALUE) << 28;
                    }
                }
            }
        }
        return z ? i : (i >>> 1) ^ (-(i & 1));
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0062  */
    @com.badlogic.gdx.utils.Null
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String readString() {
        /*
            r6 = this;
            r0 = r6
            r1 = 1
            int r0 = r0.readInt(r1)
            r1 = r0
            r7 = r1
            switch(r0) {
                case 0: goto L20;
                case 1: goto L22;
                default: goto L25;
            }
        L20:
            r0 = 0
            return r0
        L22:
            java.lang.String r0 = ""
            return r0
        L25:
            int r7 = r7 + (-1)
            r0 = r6
            char[] r0 = r0.chars
            int r0 = r0.length
            r1 = r7
            if (r0 >= r1) goto L38
            r0 = r6
            r1 = r7
            char[] r1 = new char[r1]
            r0.chars = r1
        L38:
            r0 = r6
            char[] r0 = r0.chars
            r8 = r0
            r0 = 0
            r9 = r0
            r0 = 0
            r10 = r0
        L42:
            r0 = r9
            r1 = r7
            if (r0 >= r1) goto L5d
            r0 = r6
            byte r0 = r0.readByte()
            r1 = r0
            r10 = r1
            if (r0 < 0) goto L5d
            r0 = r8
            r1 = r9
            int r9 = r9 + 1
            r2 = r10
            char r2 = (char) r2
            r0[r1] = r2
            goto L42
        L5d:
            r0 = r9
            r1 = r7
            if (r0 >= r1) goto L6e
            r0 = r6
            r1 = r7
            r2 = r9
            r3 = r10
            r4 = 255(0xff, float:3.57E-43)
            r3 = r3 & r4
            r0.readUtf8_slow(r1, r2, r3)
        L6e:
            java.lang.String r0 = new java.lang.String
            r1 = r0
            r2 = r8
            r3 = 0
            r4 = r7
            r1.<init>(r2, r3, r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.badlogic.gdx.utils.DataInput.readString():java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x00a7 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x009b A[LOOP:0: B:2:0x0006->B:9:0x009b, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void readUtf8_slow(int r7, int r8, int r9) {
        /*
            r6 = this;
            r0 = r6
            char[] r0 = r0.chars
            r10 = r0
        L6:
            r0 = r9
            r1 = 4
            int r0 = r0 >> r1
            switch(r0) {
                case 0: goto L54;
                case 1: goto L54;
                case 2: goto L54;
                case 3: goto L54;
                case 4: goto L54;
                case 5: goto L54;
                case 6: goto L54;
                case 7: goto L54;
                case 8: goto L93;
                case 9: goto L93;
                case 10: goto L93;
                case 11: goto L93;
                case 12: goto L5d;
                case 13: goto L5d;
                case 14: goto L74;
                default: goto L93;
            }
        L54:
            r0 = r10
            r1 = r8
            r2 = r9
            char r2 = (char) r2
            r0[r1] = r2
            goto L93
        L5d:
            r0 = r10
            r1 = r8
            r2 = r9
            r3 = 31
            r2 = r2 & r3
            r3 = 6
            int r2 = r2 << r3
            r3 = r6
            byte r3 = r3.readByte()
            r4 = 63
            r3 = r3 & r4
            r2 = r2 | r3
            char r2 = (char) r2
            r0[r1] = r2
            goto L93
        L74:
            r0 = r10
            r1 = r8
            r2 = r9
            r3 = 15
            r2 = r2 & r3
            r3 = 12
            int r2 = r2 << r3
            r3 = r6
            byte r3 = r3.readByte()
            r4 = 63
            r3 = r3 & r4
            r4 = 6
            int r3 = r3 << r4
            r2 = r2 | r3
            r3 = r6
            byte r3 = r3.readByte()
            r4 = 63
            r3 = r3 & r4
            r2 = r2 | r3
            char r2 = (char) r2
            r0[r1] = r2
        L93:
            int r8 = r8 + 1
            r0 = r8
            r1 = r7
            if (r0 >= r1) goto La7
            r0 = r6
            byte r0 = r0.readByte()
            r1 = 255(0xff, float:3.57E-43)
            r0 = r0 & r1
            r9 = r0
            goto L6
        La7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.badlogic.gdx.utils.DataInput.readUtf8_slow(int, int, int):void");
    }
}
