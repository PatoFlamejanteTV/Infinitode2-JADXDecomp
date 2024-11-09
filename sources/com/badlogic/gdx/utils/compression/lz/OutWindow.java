package com.badlogic.gdx.utils.compression.lz;

import java.io.OutputStream;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/compression/lz/OutWindow.class */
public class OutWindow {
    byte[] _buffer;
    int _pos;
    int _windowSize = 0;
    int _streamPos;
    OutputStream _stream;

    public void Create(int i) {
        if (this._buffer == null || this._windowSize != i) {
            this._buffer = new byte[i];
        }
        this._windowSize = i;
        this._pos = 0;
        this._streamPos = 0;
    }

    public void SetStream(OutputStream outputStream) {
        ReleaseStream();
        this._stream = outputStream;
    }

    public void ReleaseStream() {
        Flush();
        this._stream = null;
    }

    public void Init(boolean z) {
        if (!z) {
            this._streamPos = 0;
            this._pos = 0;
        }
    }

    public void Flush() {
        int i = this._pos - this._streamPos;
        if (i == 0) {
            return;
        }
        this._stream.write(this._buffer, this._streamPos, i);
        if (this._pos >= this._windowSize) {
            this._pos = 0;
        }
        this._streamPos = this._pos;
    }

    public void CopyBlock(int i, int i2) {
        int i3 = (this._pos - i) - 1;
        int i4 = i3;
        if (i3 < 0) {
            i4 += this._windowSize;
        }
        while (i2 != 0) {
            if (i4 >= this._windowSize) {
                i4 = 0;
            }
            byte[] bArr = this._buffer;
            int i5 = this._pos;
            this._pos = i5 + 1;
            int i6 = i4;
            i4++;
            bArr[i5] = this._buffer[i6];
            if (this._pos >= this._windowSize) {
                Flush();
            }
            i2--;
        }
    }

    public void PutByte(byte b2) {
        byte[] bArr = this._buffer;
        int i = this._pos;
        this._pos = i + 1;
        bArr[i] = b2;
        if (this._pos >= this._windowSize) {
            Flush();
        }
    }

    public byte GetByte(int i) {
        int i2 = (this._pos - i) - 1;
        int i3 = i2;
        if (i2 < 0) {
            i3 += this._windowSize;
        }
        return this._buffer[i3];
    }
}
