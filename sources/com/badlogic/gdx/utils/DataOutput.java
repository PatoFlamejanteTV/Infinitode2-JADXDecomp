package com.badlogic.gdx.utils;

import java.io.DataOutputStream;
import java.io.OutputStream;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/DataOutput.class */
public class DataOutput extends DataOutputStream {
    public DataOutput(OutputStream outputStream) {
        super(outputStream);
    }

    public int writeInt(int i, boolean z) {
        if (!z) {
            i = (i << 1) ^ (i >> 31);
        }
        if ((i >>> 7) == 0) {
            write((byte) i);
            return 1;
        }
        write((byte) ((i & 127) | 128));
        if ((i >>> 14) == 0) {
            write((byte) (i >>> 7));
            return 2;
        }
        write((byte) ((i >>> 7) | 128));
        if ((i >>> 21) == 0) {
            write((byte) (i >>> 14));
            return 3;
        }
        write((byte) ((i >>> 14) | 128));
        if ((i >>> 28) == 0) {
            write((byte) (i >>> 21));
            return 4;
        }
        write((byte) ((i >>> 21) | 128));
        write((byte) (i >>> 28));
        return 5;
    }

    public void writeString(@Null String str) {
        char charAt;
        if (str == null) {
            write(0);
            return;
        }
        int length = str.length();
        if (length == 0) {
            writeByte(1);
            return;
        }
        writeInt(length + 1, true);
        int i = 0;
        while (i < length && (charAt = str.charAt(i)) <= 127) {
            write((byte) charAt);
            i++;
        }
        if (i < length) {
            writeString_slow(str, length, i);
        }
    }

    private void writeString_slow(String str, int i, int i2) {
        while (i2 < i) {
            char charAt = str.charAt(i2);
            if (charAt <= 127) {
                write((byte) charAt);
            } else if (charAt > 2047) {
                write((byte) (224 | ((charAt >> '\f') & 15)));
                write((byte) (128 | ((charAt >> 6) & 63)));
                write((byte) (128 | (charAt & '?')));
            } else {
                write((byte) (192 | ((charAt >> 6) & 31)));
                write((byte) (128 | (charAt & '?')));
            }
            i2++;
        }
    }
}
