package com.badlogic.gdx.utils.compression.lz;

import java.io.InputStream;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/compression/lz/InWindow.class */
public class InWindow {
    public byte[] _bufferBase;
    InputStream _stream;
    int _posLimit;
    boolean _streamEndWasReached;
    int _pointerToLastSafePosition;
    public int _bufferOffset;
    public int _blockSize;
    public int _pos;
    int _keepSizeBefore;
    int _keepSizeAfter;
    public int _streamPos;

    public void MoveBlock() {
        int i = (this._bufferOffset + this._pos) - this._keepSizeBefore;
        int i2 = i;
        if (i > 0) {
            i2--;
        }
        int i3 = (this._bufferOffset + this._streamPos) - i2;
        for (int i4 = 0; i4 < i3; i4++) {
            byte[] bArr = this._bufferBase;
            bArr[i4] = bArr[i2 + i4];
        }
        this._bufferOffset -= i2;
    }

    public void ReadBlock() {
        if (this._streamEndWasReached) {
            return;
        }
        while (true) {
            int i = ((0 - this._bufferOffset) + this._blockSize) - this._streamPos;
            if (i == 0) {
                return;
            }
            int read = this._stream.read(this._bufferBase, this._bufferOffset + this._streamPos, i);
            if (read == -1) {
                this._posLimit = this._streamPos;
                if (this._bufferOffset + this._posLimit > this._pointerToLastSafePosition) {
                    this._posLimit = this._pointerToLastSafePosition - this._bufferOffset;
                }
                this._streamEndWasReached = true;
                return;
            }
            this._streamPos += read;
            if (this._streamPos >= this._pos + this._keepSizeAfter) {
                this._posLimit = this._streamPos - this._keepSizeAfter;
            }
        }
    }

    void Free() {
        this._bufferBase = null;
    }

    public void Create(int i, int i2, int i3) {
        this._keepSizeBefore = i;
        this._keepSizeAfter = i2;
        int i4 = i + i2 + i3;
        if (this._bufferBase == null || this._blockSize != i4) {
            Free();
            this._blockSize = i4;
            this._bufferBase = new byte[this._blockSize];
        }
        this._pointerToLastSafePosition = this._blockSize - i2;
    }

    public void SetStream(InputStream inputStream) {
        this._stream = inputStream;
    }

    public void ReleaseStream() {
        this._stream = null;
    }

    public void Init() {
        this._bufferOffset = 0;
        this._pos = 0;
        this._streamPos = 0;
        this._streamEndWasReached = false;
        ReadBlock();
    }

    public void MovePos() {
        this._pos++;
        if (this._pos > this._posLimit) {
            if (this._bufferOffset + this._pos > this._pointerToLastSafePosition) {
                MoveBlock();
            }
            ReadBlock();
        }
    }

    public byte GetIndexByte(int i) {
        return this._bufferBase[this._bufferOffset + this._pos + i];
    }

    public int GetMatchLen(int i, int i2, int i3) {
        if (this._streamEndWasReached && this._pos + i + i3 > this._streamPos) {
            i3 = this._streamPos - (this._pos + i);
        }
        int i4 = i2 + 1;
        int i5 = this._bufferOffset + this._pos + i;
        int i6 = 0;
        while (i6 < i3 && this._bufferBase[i5 + i6] == this._bufferBase[(i5 + i6) - i4]) {
            i6++;
        }
        return i6;
    }

    public int GetNumAvailableBytes() {
        return this._streamPos - this._pos;
    }

    public void ReduceOffsets(int i) {
        this._bufferOffset += i;
        this._posLimit -= i;
        this._pos -= i;
        this._streamPos -= i;
    }
}
