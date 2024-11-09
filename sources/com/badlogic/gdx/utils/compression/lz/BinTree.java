package com.badlogic.gdx.utils.compression.lz;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/compression/lz/BinTree.class */
public class BinTree extends InWindow {
    int _cyclicBufferPos;
    int _matchMaxLen;
    int[] _son;
    int[] _hash;
    int _hashMask;
    static final int kHash2Size = 1024;
    static final int kHash3Size = 65536;
    static final int kBT2HashSize = 65536;
    static final int kStartMaxLen = 1;
    static final int kHash3Offset = 1024;
    static final int kEmptyHashValue = 0;
    static final int kMaxValForNormalize = 1073741823;
    private static final int[] CrcTable = new int[256];
    int _cyclicBufferSize = 0;
    int _cutValue = 255;
    int _hashSizeSum = 0;
    boolean HASH_ARRAY = true;
    int kNumHashDirectBytes = 0;
    int kMinMatchCheck = 4;
    int kFixHashSize = 66560;

    public void SetType(int i) {
        this.HASH_ARRAY = i > 2;
        if (this.HASH_ARRAY) {
            this.kNumHashDirectBytes = 0;
            this.kMinMatchCheck = 4;
            this.kFixHashSize = 66560;
        } else {
            this.kNumHashDirectBytes = 2;
            this.kMinMatchCheck = 3;
            this.kFixHashSize = 0;
        }
    }

    @Override // com.badlogic.gdx.utils.compression.lz.InWindow
    public void Init() {
        super.Init();
        for (int i = 0; i < this._hashSizeSum; i++) {
            this._hash[i] = 0;
        }
        this._cyclicBufferPos = 0;
        ReduceOffsets(-1);
    }

    @Override // com.badlogic.gdx.utils.compression.lz.InWindow
    public void MovePos() {
        int i = this._cyclicBufferPos + 1;
        this._cyclicBufferPos = i;
        if (i >= this._cyclicBufferSize) {
            this._cyclicBufferPos = 0;
        }
        super.MovePos();
        if (this._pos == 1073741823) {
            Normalize();
        }
    }

    public boolean Create(int i, int i2, int i3, int i4) {
        if (i > 1073741567) {
            return false;
        }
        this._cutValue = 16 + (i3 >> 1);
        super.Create(i + i2, i3 + i4, ((((i + i2) + i3) + i4) / 2) + 256);
        this._matchMaxLen = i3;
        int i5 = i + 1;
        if (this._cyclicBufferSize != i5) {
            this._cyclicBufferSize = i5;
            this._son = new int[i5 << 1];
        }
        int i6 = 65536;
        if (this.HASH_ARRAY) {
            int i7 = i - 1;
            int i8 = i7 | (i7 >> 1);
            int i9 = i8 | (i8 >> 2);
            int i10 = i9 | (i9 >> 4);
            int i11 = ((i10 | (i10 >> 8)) >> 1) | 65535;
            int i12 = i11;
            if (i11 > 16777216) {
                i12 >>= 1;
            }
            this._hashMask = i12;
            i6 = i12 + 1 + this.kFixHashSize;
        }
        if (i6 != this._hashSizeSum) {
            int i13 = i6;
            this._hashSizeSum = i13;
            this._hash = new int[i13];
            return true;
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x02a1, code lost:            if (r7._bufferBase[r0 + r23] == r7._bufferBase[r0 + r23]) goto L56;     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x02a4, code lost:            r23 = r23 + 1;     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x02aa, code lost:            if (r23 == r9) goto L80;     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x02c1, code lost:            if (r7._bufferBase[r0 + r23] == r7._bufferBase[r0 + r23]) goto L81;     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x02c8, code lost:            if (r13 >= r23) goto L65;     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x02cb, code lost:            r1 = r10;        r10 = r10 + 1;        r13 = r23;        r8[r1] = r23;        r10 = r10 + 1;        r8[r10] = r0 - 1;     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x02e3, code lost:            if (r23 != r9) goto L65;     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x02e6, code lost:            r1 = r7._son;        r1[r19] = r1[r0];        r1 = r7._son;        r1[r18] = r1[r0 + 1];     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x031d, code lost:            if ((r7._bufferBase[r0 + r23] & 255) >= (r7._bufferBase[r0 + r23] & 255)) goto L73;     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x033f, code lost:            r7._son[r18] = r17;        r18 = r0;        r17 = r7._son[r18];        r14 = r23;     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0320, code lost:            r7._son[r19] = r17;        r19 = r0 + 1;        r17 = r7._son[r19];        r15 = r23;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int GetMatches(int[] r8) {
        /*
            Method dump skipped, instructions count: 866
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.badlogic.gdx.utils.compression.lz.BinTree.GetMatches(int[]):int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x019c, code lost:            if (r7._bufferBase[r0 + r20] == r7._bufferBase[r0 + r20]) goto L27;     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x019f, code lost:            r20 = r20 + 1;     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x01a5, code lost:            if (r20 == r9) goto L51;     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x01bc, code lost:            if (r7._bufferBase[r0 + r20] == r7._bufferBase[r0 + r20]) goto L53;     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x01c2, code lost:            if (r20 != r9) goto L34;     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x01c5, code lost:            r1 = r7._son;        r1[r14] = r1[r0];        r1 = r7._son;        r1[r12] = r1[r0 + 1];     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x01fc, code lost:            if ((r7._bufferBase[r0 + r20] & 255) >= (r7._bufferBase[r0 + r20] & 255)) goto L46;     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x021e, code lost:            r7._son[r12] = r13;        r12 = r0;        r13 = r7._son[r12];        r15 = r20;     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x01ff, code lost:            r7._son[r14] = r13;        r14 = r0 + 1;        r13 = r7._son[r14];        r16 = r20;     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0027, code lost:            if (r0 >= r7.kMinMatchCheck) goto L7;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void Skip(int r8) {
        /*
            Method dump skipped, instructions count: 583
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.badlogic.gdx.utils.compression.lz.BinTree.Skip(int):void");
    }

    void NormalizeLinks(int[] iArr, int i, int i2) {
        int i3;
        for (int i4 = 0; i4 < i; i4++) {
            int i5 = iArr[i4];
            if (i5 <= i2) {
                i3 = 0;
            } else {
                i3 = i5 - i2;
            }
            iArr[i4] = i3;
        }
    }

    void Normalize() {
        int i = this._pos - this._cyclicBufferSize;
        NormalizeLinks(this._son, this._cyclicBufferSize << 1, i);
        NormalizeLinks(this._hash, this._hashSizeSum, i);
        ReduceOffsets(i);
    }

    public void SetCutValue(int i) {
        this._cutValue = i;
    }

    static {
        int i;
        for (int i2 = 0; i2 < 256; i2++) {
            int i3 = i2;
            for (int i4 = 0; i4 < 8; i4++) {
                if ((i3 & 1) != 0) {
                    i = (i3 >>> 1) ^ (-306674912);
                } else {
                    i = i3 >>> 1;
                }
                i3 = i;
            }
            CrcTable[i2] = i3;
        }
    }
}
