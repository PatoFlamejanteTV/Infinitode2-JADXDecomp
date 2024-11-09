package com.badlogic.gdx.utils.compression.lzma;

import com.badlogic.gdx.utils.compression.ICodeProgress;
import com.badlogic.gdx.utils.compression.lz.BinTree;
import com.badlogic.gdx.utils.compression.rangecoder.BitTreeEncoder;
import com.prineside.luaj.Lua;
import java.io.InputStream;
import java.io.OutputStream;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/compression/lzma/Encoder.class */
public class Encoder {
    public static final int EMatchFinderTypeBT2 = 0;
    public static final int EMatchFinderTypeBT4 = 1;
    static final int kIfinityPrice = 268435455;
    static byte[] g_FastPos = new byte[2048];
    byte _previousByte;
    static final int kDefaultDictionaryLogSize = 22;
    static final int kNumFastBytesDefault = 32;
    public static final int kNumLenSpecSymbols = 16;
    static final int kNumOpts = 4096;
    int _longestMatchLength;
    int _numDistancePairs;
    int _additionalOffset;
    int _optimumEndIndex;
    int _optimumCurrentIndex;
    boolean _longestMatchWasFound;
    int _alignPriceCount;
    long nowPos64;
    boolean _finished;
    InputStream _inStream;
    int backRes;
    public static final int kPropSize = 5;
    int _matchPriceCount;
    int _state = Base.StateInit();
    int[] _repDistances = new int[4];
    Optimal[] _optimum = new Optimal[4096];
    BinTree _matchFinder = null;
    com.badlogic.gdx.utils.compression.rangecoder.Encoder _rangeEncoder = new com.badlogic.gdx.utils.compression.rangecoder.Encoder();
    short[] _isMatch = new short[192];
    short[] _isRep = new short[12];
    short[] _isRepG0 = new short[12];
    short[] _isRepG1 = new short[12];
    short[] _isRepG2 = new short[12];
    short[] _isRep0Long = new short[192];
    BitTreeEncoder[] _posSlotEncoder = new BitTreeEncoder[4];
    short[] _posEncoders = new short[114];
    BitTreeEncoder _posAlignEncoder = new BitTreeEncoder(4);
    LenPriceTableEncoder _lenEncoder = new LenPriceTableEncoder();
    LenPriceTableEncoder _repMatchLenEncoder = new LenPriceTableEncoder();
    LiteralEncoder _literalEncoder = new LiteralEncoder();
    int[] _matchDistances = new int[User32.WM_MDINEXT];
    int _numFastBytes = 32;
    int[] _posSlotPrices = new int[256];
    int[] _distancesPrices = new int[512];
    int[] _alignPrices = new int[16];
    int _distTableSize = 44;
    int _posStateBits = 2;
    int _posStateMask = 3;
    int _numLiteralPosStateBits = 0;
    int _numLiteralContextBits = 3;
    int _dictionarySize = 4194304;
    int _dictionarySizePrev = -1;
    int _numFastBytesPrev = -1;
    int _matchFinderType = 1;
    boolean _writeEndMark = false;
    boolean _needReleaseMFStream = false;
    int[] reps = new int[4];
    int[] repLens = new int[4];
    long[] processedInSize = new long[1];
    long[] processedOutSize = new long[1];
    boolean[] finished = new boolean[1];
    byte[] properties = new byte[5];
    int[] tempPrices = new int[128];

    static {
        int i = 2;
        g_FastPos[0] = 0;
        g_FastPos[1] = 1;
        for (int i2 = 2; i2 < 22; i2++) {
            int i3 = 1 << ((i2 >> 1) - 1);
            int i4 = 0;
            while (i4 < i3) {
                g_FastPos[i] = (byte) i2;
                i4++;
                i++;
            }
        }
    }

    static int GetPosSlot(int i) {
        return i < 2048 ? g_FastPos[i] : i < 2097152 ? g_FastPos[i >> 10] + 20 : g_FastPos[i >> 20] + 40;
    }

    static int GetPosSlot2(int i) {
        return i < 131072 ? g_FastPos[i >> 6] + 12 : i < 134217728 ? g_FastPos[i >> 16] + 32 : g_FastPos[i >> 26] + 52;
    }

    void BaseInit() {
        this._state = Base.StateInit();
        this._previousByte = (byte) 0;
        for (int i = 0; i < 4; i++) {
            this._repDistances[i] = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/compression/lzma/Encoder$LiteralEncoder.class */
    public class LiteralEncoder {
        Encoder2[] m_Coders;
        int m_NumPrevBits;
        int m_NumPosBits;
        int m_PosMask;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/compression/lzma/Encoder$LiteralEncoder$Encoder2.class */
        public class Encoder2 {
            short[] m_Encoders = new short[768];

            Encoder2() {
            }

            public void Init() {
                com.badlogic.gdx.utils.compression.rangecoder.Encoder.InitBitModels(this.m_Encoders);
            }

            public void Encode(com.badlogic.gdx.utils.compression.rangecoder.Encoder encoder, byte b2) {
                int i = 1;
                for (int i2 = 7; i2 >= 0; i2--) {
                    int i3 = (b2 >> i2) & 1;
                    encoder.Encode(this.m_Encoders, i, i3);
                    i = (i << 1) | i3;
                }
            }

            public void EncodeMatched(com.badlogic.gdx.utils.compression.rangecoder.Encoder encoder, byte b2, byte b3) {
                int i = 1;
                boolean z = true;
                for (int i2 = 7; i2 >= 0; i2--) {
                    int i3 = (b3 >> i2) & 1;
                    int i4 = i;
                    if (z) {
                        int i5 = (b2 >> i2) & 1;
                        i4 += (i5 + 1) << 8;
                        z = i5 == i3;
                    }
                    encoder.Encode(this.m_Encoders, i4, i3);
                    i = (i << 1) | i3;
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r11v0 */
            /* JADX WARN: Type inference failed for: r11v1 */
            /* JADX WARN: Type inference failed for: r11v2, types: [int] */
            /* JADX WARN: Type inference failed for: r11v3, types: [int] */
            /* JADX WARN: Type inference failed for: r11v4, types: [int] */
            /* JADX WARN: Type inference failed for: r11v5, types: [int] */
            /* JADX WARN: Type inference failed for: r11v7 */
            /* JADX WARN: Type inference failed for: r11v8 */
            public int GetPrice(boolean z, byte b2, byte b3) {
                int i = 0;
                int i2 = 1;
                char c = 7;
                c = c;
                if (z) {
                    while (true) {
                        if (c < 0) {
                            break;
                        }
                        int i3 = (b2 >> c) & 1;
                        int i4 = (b3 >> c) & 1;
                        i += com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice(this.m_Encoders[((i3 + 1) << 8) + i2], i4);
                        i2 = (i2 << 1) | i4;
                        if (i3 == i4) {
                            c--;
                        } else {
                            c--;
                            break;
                        }
                    }
                }
                while (c >= 0) {
                    int i5 = (b3 >> c) & 1;
                    i += com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice(this.m_Encoders[i2], i5);
                    i2 = (i2 << 1) | i5;
                    c--;
                }
                return i;
            }
        }

        LiteralEncoder() {
        }

        public void Create(int i, int i2) {
            if (this.m_Coders != null && this.m_NumPrevBits == i2 && this.m_NumPosBits == i) {
                return;
            }
            this.m_NumPosBits = i;
            this.m_PosMask = (1 << i) - 1;
            this.m_NumPrevBits = i2;
            int i3 = 1 << (this.m_NumPrevBits + this.m_NumPosBits);
            this.m_Coders = new Encoder2[i3];
            for (int i4 = 0; i4 < i3; i4++) {
                this.m_Coders[i4] = new Encoder2();
            }
        }

        public void Init() {
            int i = 1 << (this.m_NumPrevBits + this.m_NumPosBits);
            for (int i2 = 0; i2 < i; i2++) {
                this.m_Coders[i2].Init();
            }
        }

        public Encoder2 GetSubCoder(int i, byte b2) {
            return this.m_Coders[((i & this.m_PosMask) << this.m_NumPrevBits) + ((b2 & 255) >>> (8 - this.m_NumPrevBits))];
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/compression/lzma/Encoder$LenEncoder.class */
    public class LenEncoder {
        short[] _choice = new short[2];
        BitTreeEncoder[] _lowCoder = new BitTreeEncoder[16];
        BitTreeEncoder[] _midCoder = new BitTreeEncoder[16];
        BitTreeEncoder _highCoder = new BitTreeEncoder(8);

        public LenEncoder() {
            for (int i = 0; i < 16; i++) {
                this._lowCoder[i] = new BitTreeEncoder(3);
                this._midCoder[i] = new BitTreeEncoder(3);
            }
        }

        public void Init(int i) {
            com.badlogic.gdx.utils.compression.rangecoder.Encoder.InitBitModels(this._choice);
            for (int i2 = 0; i2 < i; i2++) {
                this._lowCoder[i2].Init();
                this._midCoder[i2].Init();
            }
            this._highCoder.Init();
        }

        public void Encode(com.badlogic.gdx.utils.compression.rangecoder.Encoder encoder, int i, int i2) {
            if (i < 8) {
                encoder.Encode(this._choice, 0, 0);
                this._lowCoder[i2].Encode(encoder, i);
                return;
            }
            int i3 = i - 8;
            encoder.Encode(this._choice, 0, 1);
            if (i3 < 8) {
                encoder.Encode(this._choice, 1, 0);
                this._midCoder[i2].Encode(encoder, i3);
            } else {
                encoder.Encode(this._choice, 1, 1);
                this._highCoder.Encode(encoder, i3 - 8);
            }
        }

        public void SetPrices(int i, int i2, int[] iArr, int i3) {
            int GetPrice0 = com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice0(this._choice[0]);
            int GetPrice1 = com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice1(this._choice[0]);
            int GetPrice02 = GetPrice1 + com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice0(this._choice[1]);
            int GetPrice12 = GetPrice1 + com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice1(this._choice[1]);
            int i4 = 0;
            while (i4 < 8) {
                if (i4 >= i2) {
                    return;
                }
                iArr[i3 + i4] = GetPrice0 + this._lowCoder[i].GetPrice(i4);
                i4++;
            }
            while (i4 < 16) {
                if (i4 >= i2) {
                    return;
                }
                iArr[i3 + i4] = GetPrice02 + this._midCoder[i].GetPrice(i4 - 8);
                i4++;
            }
            while (i4 < i2) {
                iArr[i3 + i4] = GetPrice12 + this._highCoder.GetPrice((i4 - 8) - 8);
                i4++;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/compression/lzma/Encoder$LenPriceTableEncoder.class */
    public class LenPriceTableEncoder extends LenEncoder {
        int[] _prices;
        int _tableSize;
        int[] _counters;

        LenPriceTableEncoder() {
            super();
            this._prices = new int[4352];
            this._counters = new int[16];
        }

        public void SetTableSize(int i) {
            this._tableSize = i;
        }

        public int GetPrice(int i, int i2) {
            return this._prices[(i2 * 272) + i];
        }

        void UpdateTable(int i) {
            SetPrices(i, this._tableSize, this._prices, i * 272);
            this._counters[i] = this._tableSize;
        }

        public void UpdateTables(int i) {
            for (int i2 = 0; i2 < i; i2++) {
                UpdateTable(i2);
            }
        }

        @Override // com.badlogic.gdx.utils.compression.lzma.Encoder.LenEncoder
        public void Encode(com.badlogic.gdx.utils.compression.rangecoder.Encoder encoder, int i, int i2) {
            super.Encode(encoder, i, i2);
            int[] iArr = this._counters;
            int i3 = iArr[i2] - 1;
            iArr[i2] = i3;
            if (i3 == 0) {
                UpdateTable(i2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/compression/lzma/Encoder$Optimal.class */
    public class Optimal {
        public int State;
        public boolean Prev1IsChar;
        public boolean Prev2;
        public int PosPrev2;
        public int BackPrev2;
        public int Price;
        public int PosPrev;
        public int BackPrev;
        public int Backs0;
        public int Backs1;
        public int Backs2;
        public int Backs3;

        Optimal() {
        }

        public void MakeAsChar() {
            this.BackPrev = -1;
            this.Prev1IsChar = false;
        }

        public void MakeAsShortRep() {
            this.BackPrev = 0;
            this.Prev1IsChar = false;
        }

        public boolean IsShortRep() {
            return this.BackPrev == 0;
        }
    }

    void Create() {
        if (this._matchFinder == null) {
            BinTree binTree = new BinTree();
            int i = 4;
            if (this._matchFinderType == 0) {
                i = 2;
            }
            binTree.SetType(i);
            this._matchFinder = binTree;
        }
        this._literalEncoder.Create(this._numLiteralPosStateBits, this._numLiteralContextBits);
        if (this._dictionarySize == this._dictionarySizePrev && this._numFastBytesPrev == this._numFastBytes) {
            return;
        }
        this._matchFinder.Create(this._dictionarySize, 4096, this._numFastBytes, User32.WM_SYSCOMMAND);
        this._dictionarySizePrev = this._dictionarySize;
        this._numFastBytesPrev = this._numFastBytes;
    }

    public Encoder() {
        for (int i = 0; i < 4096; i++) {
            this._optimum[i] = new Optimal();
        }
        for (int i2 = 0; i2 < 4; i2++) {
            this._posSlotEncoder[i2] = new BitTreeEncoder(6);
        }
    }

    void SetWriteEndMarkerMode(boolean z) {
        this._writeEndMark = z;
    }

    void Init() {
        BaseInit();
        this._rangeEncoder.Init();
        com.badlogic.gdx.utils.compression.rangecoder.Encoder.InitBitModels(this._isMatch);
        com.badlogic.gdx.utils.compression.rangecoder.Encoder.InitBitModels(this._isRep0Long);
        com.badlogic.gdx.utils.compression.rangecoder.Encoder.InitBitModels(this._isRep);
        com.badlogic.gdx.utils.compression.rangecoder.Encoder.InitBitModels(this._isRepG0);
        com.badlogic.gdx.utils.compression.rangecoder.Encoder.InitBitModels(this._isRepG1);
        com.badlogic.gdx.utils.compression.rangecoder.Encoder.InitBitModels(this._isRepG2);
        com.badlogic.gdx.utils.compression.rangecoder.Encoder.InitBitModels(this._posEncoders);
        this._literalEncoder.Init();
        for (int i = 0; i < 4; i++) {
            this._posSlotEncoder[i].Init();
        }
        this._lenEncoder.Init(1 << this._posStateBits);
        this._repMatchLenEncoder.Init(1 << this._posStateBits);
        this._posAlignEncoder.Init();
        this._longestMatchWasFound = false;
        this._optimumEndIndex = 0;
        this._optimumCurrentIndex = 0;
        this._additionalOffset = 0;
    }

    int ReadMatchDistances() {
        int i = 0;
        this._numDistancePairs = this._matchFinder.GetMatches(this._matchDistances);
        if (this._numDistancePairs > 0) {
            int i2 = this._matchDistances[this._numDistancePairs - 2];
            i = i2;
            if (i2 == this._numFastBytes) {
                i += this._matchFinder.GetMatchLen(i - 1, this._matchDistances[this._numDistancePairs - 1], 273 - i);
            }
        }
        this._additionalOffset++;
        return i;
    }

    void MovePos(int i) {
        if (i > 0) {
            this._matchFinder.Skip(i);
            this._additionalOffset += i;
        }
    }

    int GetRepLen1Price(int i, int i2) {
        return com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice0(this._isRepG0[i]) + com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice0(this._isRep0Long[(i << 4) + i2]);
    }

    int GetPureRepPrice(int i, int i2, int i3) {
        int GetPrice1;
        if (i == 0) {
            GetPrice1 = com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice0(this._isRepG0[i2]) + com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice1(this._isRep0Long[(i2 << 4) + i3]);
        } else {
            int GetPrice12 = com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice1(this._isRepG0[i2]);
            if (i == 1) {
                GetPrice1 = GetPrice12 + com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice0(this._isRepG1[i2]);
            } else {
                GetPrice1 = GetPrice12 + com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice1(this._isRepG1[i2]) + com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice(this._isRepG2[i2], i - 2);
            }
        }
        return GetPrice1;
    }

    int GetRepPrice(int i, int i2, int i3, int i4) {
        return this._repMatchLenEncoder.GetPrice(i2 - 2, i4) + GetPureRepPrice(i, i3, i4);
    }

    int GetPosLenPrice(int i, int i2, int i3) {
        int i4;
        int GetLenToPosState = Base.GetLenToPosState(i2);
        if (i < 128) {
            i4 = this._distancesPrices[(GetLenToPosState << 7) + i];
        } else {
            i4 = this._posSlotPrices[(GetLenToPosState << 6) + GetPosSlot2(i)] + this._alignPrices[i & 15];
        }
        return i4 + this._lenEncoder.GetPrice(i2 - 2, i3);
    }

    int Backward(int i) {
        int i2;
        this._optimumEndIndex = i;
        int i3 = this._optimum[i].PosPrev;
        int i4 = this._optimum[i].BackPrev;
        do {
            if (this._optimum[i].Prev1IsChar) {
                this._optimum[i3].MakeAsChar();
                this._optimum[i3].PosPrev = i3 - 1;
                if (this._optimum[i].Prev2) {
                    this._optimum[i3 - 1].Prev1IsChar = false;
                    this._optimum[i3 - 1].PosPrev = this._optimum[i].PosPrev2;
                    this._optimum[i3 - 1].BackPrev = this._optimum[i].BackPrev2;
                }
            }
            i2 = i3;
            int i5 = i4;
            i4 = this._optimum[i2].BackPrev;
            i3 = this._optimum[i2].PosPrev;
            this._optimum[i2].BackPrev = i5;
            this._optimum[i2].PosPrev = i;
            i = i2;
        } while (i2 > 0);
        this.backRes = this._optimum[0].BackPrev;
        this._optimumCurrentIndex = this._optimum[0].PosPrev;
        return this._optimumCurrentIndex;
    }

    int GetOptimum(int i) {
        int i2;
        int i3;
        int i4;
        int StateUpdateMatch;
        int GetRepLen1Price;
        int i5;
        int GetRepLen1Price2;
        if (this._optimumEndIndex != this._optimumCurrentIndex) {
            int i6 = this._optimum[this._optimumCurrentIndex].PosPrev - this._optimumCurrentIndex;
            this.backRes = this._optimum[this._optimumCurrentIndex].BackPrev;
            this._optimumCurrentIndex = this._optimum[this._optimumCurrentIndex].PosPrev;
            return i6;
        }
        this._optimumEndIndex = 0;
        this._optimumCurrentIndex = 0;
        if (!this._longestMatchWasFound) {
            i2 = ReadMatchDistances();
        } else {
            i2 = this._longestMatchLength;
            this._longestMatchWasFound = false;
        }
        int i7 = this._numDistancePairs;
        if (this._matchFinder.GetNumAvailableBytes() + 1 < 2) {
            this.backRes = -1;
            return 1;
        }
        int i8 = 0;
        for (int i9 = 0; i9 < 4; i9++) {
            this.reps[i9] = this._repDistances[i9];
            this.repLens[i9] = this._matchFinder.GetMatchLen(-1, this.reps[i9], 273);
            if (this.repLens[i9] > this.repLens[i8]) {
                i8 = i9;
            }
        }
        if (this.repLens[i8] >= this._numFastBytes) {
            this.backRes = i8;
            int i10 = this.repLens[i8];
            MovePos(i10 - 1);
            return i10;
        }
        if (i2 >= this._numFastBytes) {
            this.backRes = this._matchDistances[i7 - 1] + 4;
            MovePos(i2 - 1);
            return i2;
        }
        byte GetIndexByte = this._matchFinder.GetIndexByte(-1);
        byte GetIndexByte2 = this._matchFinder.GetIndexByte(((0 - this._repDistances[0]) - 1) - 1);
        if (i2 < 2 && GetIndexByte != GetIndexByte2 && this.repLens[i8] < 2) {
            this.backRes = -1;
            return 1;
        }
        this._optimum[0].State = this._state;
        int i11 = i & this._posStateMask;
        this._optimum[1].Price = com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice0(this._isMatch[(this._state << 4) + i11]) + this._literalEncoder.GetSubCoder(i, this._previousByte).GetPrice(!Base.StateIsCharState(this._state), GetIndexByte2, GetIndexByte);
        this._optimum[1].MakeAsChar();
        int GetPrice1 = com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice1(this._isMatch[(this._state << 4) + i11]);
        int GetPrice12 = GetPrice1 + com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice1(this._isRep[this._state]);
        if (GetIndexByte2 == GetIndexByte && (GetRepLen1Price2 = GetPrice12 + GetRepLen1Price(this._state, i11)) < this._optimum[1].Price) {
            this._optimum[1].Price = GetRepLen1Price2;
            this._optimum[1].MakeAsShortRep();
        }
        int i12 = i2 >= this.repLens[i8] ? i2 : this.repLens[i8];
        int i13 = i12;
        if (i12 < 2) {
            this.backRes = this._optimum[1].BackPrev;
            return 1;
        }
        this._optimum[1].PosPrev = 0;
        this._optimum[0].Backs0 = this.reps[0];
        this._optimum[0].Backs1 = this.reps[1];
        this._optimum[0].Backs2 = this.reps[2];
        this._optimum[0].Backs3 = this.reps[3];
        int i14 = i13;
        do {
            int i15 = i14;
            i14--;
            this._optimum[i15].Price = kIfinityPrice;
        } while (i14 >= 2);
        for (int i16 = 0; i16 < 4; i16++) {
            int i17 = this.repLens[i16];
            int i18 = i17;
            if (i17 >= 2) {
                int GetPureRepPrice = GetPrice12 + GetPureRepPrice(i16, this._state, i11);
                do {
                    int GetPrice = GetPureRepPrice + this._repMatchLenEncoder.GetPrice(i18 - 2, i11);
                    Optimal optimal = this._optimum[i18];
                    if (GetPrice < optimal.Price) {
                        optimal.Price = GetPrice;
                        optimal.PosPrev = 0;
                        optimal.BackPrev = i16;
                        optimal.Prev1IsChar = false;
                    }
                    i18--;
                } while (i18 >= 2);
            }
        }
        int GetPrice0 = GetPrice1 + com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice0(this._isRep[this._state]);
        int i19 = this.repLens[0] >= 2 ? this.repLens[0] + 1 : 2;
        int i20 = i19;
        if (i19 <= i2) {
            int i21 = 0;
            while (i20 > this._matchDistances[i21]) {
                i21 += 2;
            }
            while (true) {
                int i22 = this._matchDistances[i21 + 1];
                int GetPosLenPrice = GetPrice0 + GetPosLenPrice(i22, i20, i11);
                Optimal optimal2 = this._optimum[i20];
                if (GetPosLenPrice < optimal2.Price) {
                    optimal2.Price = GetPosLenPrice;
                    optimal2.PosPrev = 0;
                    optimal2.BackPrev = i22 + 4;
                    optimal2.Prev1IsChar = false;
                }
                if (i20 == this._matchDistances[i21]) {
                    i21 += 2;
                    if (i21 == i7) {
                        break;
                    }
                }
                i20++;
            }
        }
        int i23 = 0;
        while (true) {
            i23++;
            if (i23 == i13) {
                return Backward(i23);
            }
            int ReadMatchDistances = ReadMatchDistances();
            int i24 = this._numDistancePairs;
            if (ReadMatchDistances >= this._numFastBytes) {
                this._longestMatchLength = ReadMatchDistances;
                this._longestMatchWasFound = true;
                return Backward(i23);
            }
            i++;
            int i25 = this._optimum[i23].PosPrev;
            if (this._optimum[i23].Prev1IsChar) {
                i25--;
                if (this._optimum[i23].Prev2) {
                    int i26 = this._optimum[this._optimum[i23].PosPrev2].State;
                    if (this._optimum[i23].BackPrev2 < 4) {
                        i5 = Base.StateUpdateRep(i26);
                    } else {
                        i5 = Base.StateUpdateMatch(i26);
                    }
                } else {
                    i5 = this._optimum[i25].State;
                }
                i3 = Base.StateUpdateChar(i5);
            } else {
                i3 = this._optimum[i25].State;
            }
            if (i25 == i23 - 1) {
                if (this._optimum[i23].IsShortRep()) {
                    StateUpdateMatch = Base.StateUpdateShortRep(i3);
                } else {
                    StateUpdateMatch = Base.StateUpdateChar(i3);
                }
            } else {
                if (this._optimum[i23].Prev1IsChar && this._optimum[i23].Prev2) {
                    i25 = this._optimum[i23].PosPrev2;
                    i4 = this._optimum[i23].BackPrev2;
                    StateUpdateMatch = Base.StateUpdateRep(i3);
                } else {
                    int i27 = this._optimum[i23].BackPrev;
                    i4 = i27;
                    if (i27 < 4) {
                        StateUpdateMatch = Base.StateUpdateRep(i3);
                    } else {
                        StateUpdateMatch = Base.StateUpdateMatch(i3);
                    }
                }
                Optimal optimal3 = this._optimum[i25];
                if (i4 < 4) {
                    if (i4 == 0) {
                        this.reps[0] = optimal3.Backs0;
                        this.reps[1] = optimal3.Backs1;
                        this.reps[2] = optimal3.Backs2;
                        this.reps[3] = optimal3.Backs3;
                    } else if (i4 == 1) {
                        this.reps[0] = optimal3.Backs1;
                        this.reps[1] = optimal3.Backs0;
                        this.reps[2] = optimal3.Backs2;
                        this.reps[3] = optimal3.Backs3;
                    } else if (i4 == 2) {
                        this.reps[0] = optimal3.Backs2;
                        this.reps[1] = optimal3.Backs0;
                        this.reps[2] = optimal3.Backs1;
                        this.reps[3] = optimal3.Backs3;
                    } else {
                        this.reps[0] = optimal3.Backs3;
                        this.reps[1] = optimal3.Backs0;
                        this.reps[2] = optimal3.Backs1;
                        this.reps[3] = optimal3.Backs2;
                    }
                } else {
                    this.reps[0] = i4 - 4;
                    this.reps[1] = optimal3.Backs0;
                    this.reps[2] = optimal3.Backs1;
                    this.reps[3] = optimal3.Backs2;
                }
            }
            this._optimum[i23].State = StateUpdateMatch;
            this._optimum[i23].Backs0 = this.reps[0];
            this._optimum[i23].Backs1 = this.reps[1];
            this._optimum[i23].Backs2 = this.reps[2];
            this._optimum[i23].Backs3 = this.reps[3];
            int i28 = this._optimum[i23].Price;
            byte GetIndexByte3 = this._matchFinder.GetIndexByte(-1);
            byte GetIndexByte4 = this._matchFinder.GetIndexByte(((0 - this.reps[0]) - 1) - 1);
            int i29 = i & this._posStateMask;
            int GetPrice02 = i28 + com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice0(this._isMatch[(StateUpdateMatch << 4) + i29]) + this._literalEncoder.GetSubCoder(i, this._matchFinder.GetIndexByte(-2)).GetPrice(!Base.StateIsCharState(StateUpdateMatch), GetIndexByte4, GetIndexByte3);
            Optimal optimal4 = this._optimum[i23 + 1];
            boolean z = false;
            if (GetPrice02 < optimal4.Price) {
                optimal4.Price = GetPrice02;
                optimal4.PosPrev = i23;
                optimal4.MakeAsChar();
                z = true;
            }
            int GetPrice13 = i28 + com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice1(this._isMatch[(StateUpdateMatch << 4) + i29]);
            int GetPrice14 = GetPrice13 + com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice1(this._isRep[StateUpdateMatch]);
            if (GetIndexByte4 == GetIndexByte3 && ((optimal4.PosPrev >= i23 || optimal4.BackPrev != 0) && (GetRepLen1Price = GetPrice14 + GetRepLen1Price(StateUpdateMatch, i29)) <= optimal4.Price)) {
                optimal4.Price = GetRepLen1Price;
                optimal4.PosPrev = i23;
                optimal4.MakeAsShortRep();
                z = true;
            }
            int min = Math.min(4095 - i23, this._matchFinder.GetNumAvailableBytes() + 1);
            int i30 = min;
            if (min >= 2) {
                if (i30 > this._numFastBytes) {
                    i30 = this._numFastBytes;
                }
                if (!z && GetIndexByte4 != GetIndexByte3) {
                    int GetMatchLen = this._matchFinder.GetMatchLen(0, this.reps[0], Math.min(min - 1, this._numFastBytes));
                    if (GetMatchLen >= 2) {
                        int StateUpdateChar = Base.StateUpdateChar(StateUpdateMatch);
                        int i31 = (i + 1) & this._posStateMask;
                        int GetPrice15 = GetPrice02 + com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice1(this._isMatch[(StateUpdateChar << 4) + i31]) + com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice1(this._isRep[StateUpdateChar]);
                        int i32 = i23 + 1 + GetMatchLen;
                        while (i13 < i32) {
                            i13++;
                            this._optimum[i13].Price = kIfinityPrice;
                        }
                        int GetRepPrice = GetPrice15 + GetRepPrice(0, GetMatchLen, StateUpdateChar, i31);
                        Optimal optimal5 = this._optimum[i32];
                        if (GetRepPrice < optimal5.Price) {
                            optimal5.Price = GetRepPrice;
                            optimal5.PosPrev = i23 + 1;
                            optimal5.BackPrev = 0;
                            optimal5.Prev1IsChar = true;
                            optimal5.Prev2 = false;
                        }
                    }
                }
                int i33 = 2;
                for (int i34 = 0; i34 < 4; i34++) {
                    int GetMatchLen2 = this._matchFinder.GetMatchLen(-1, this.reps[i34], i30);
                    int i35 = GetMatchLen2;
                    if (GetMatchLen2 >= 2) {
                        while (true) {
                            if (i13 < i23 + i35) {
                                i13++;
                                this._optimum[i13].Price = kIfinityPrice;
                            } else {
                                int GetRepPrice2 = GetPrice14 + GetRepPrice(i34, i35, StateUpdateMatch, i29);
                                Optimal optimal6 = this._optimum[i23 + i35];
                                if (GetRepPrice2 < optimal6.Price) {
                                    optimal6.Price = GetRepPrice2;
                                    optimal6.PosPrev = i23;
                                    optimal6.BackPrev = i34;
                                    optimal6.Prev1IsChar = false;
                                }
                                i35--;
                                if (i35 < 2) {
                                    break;
                                }
                            }
                        }
                        if (i34 == 0) {
                            i33 = i35 + 1;
                        }
                        if (i35 < min) {
                            int GetMatchLen3 = this._matchFinder.GetMatchLen(i35, this.reps[i34], Math.min((min - 1) - i35, this._numFastBytes));
                            if (GetMatchLen3 >= 2) {
                                int StateUpdateRep = Base.StateUpdateRep(StateUpdateMatch);
                                int GetRepPrice3 = GetPrice14 + GetRepPrice(i34, i35, StateUpdateMatch, i29) + com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice0(this._isMatch[(StateUpdateRep << 4) + ((i + i35) & this._posStateMask)]) + this._literalEncoder.GetSubCoder(i + i35, this._matchFinder.GetIndexByte((i35 - 1) - 1)).GetPrice(true, this._matchFinder.GetIndexByte((i35 - 1) - (this.reps[i34] + 1)), this._matchFinder.GetIndexByte(i35 - 1));
                                int StateUpdateChar2 = Base.StateUpdateChar(StateUpdateRep);
                                int i36 = (i + i35 + 1) & this._posStateMask;
                                int GetPrice16 = GetRepPrice3 + com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice1(this._isMatch[(StateUpdateChar2 << 4) + i36]) + com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice1(this._isRep[StateUpdateChar2]);
                                int i37 = i35 + 1 + GetMatchLen3;
                                while (i13 < i23 + i37) {
                                    i13++;
                                    this._optimum[i13].Price = kIfinityPrice;
                                }
                                int GetRepPrice4 = GetPrice16 + GetRepPrice(0, GetMatchLen3, StateUpdateChar2, i36);
                                Optimal optimal7 = this._optimum[i23 + i37];
                                if (GetRepPrice4 < optimal7.Price) {
                                    optimal7.Price = GetRepPrice4;
                                    optimal7.PosPrev = i23 + i35 + 1;
                                    optimal7.BackPrev = 0;
                                    optimal7.Prev1IsChar = true;
                                    optimal7.Prev2 = true;
                                    optimal7.PosPrev2 = i23;
                                    optimal7.BackPrev2 = i34;
                                }
                            }
                        }
                    }
                }
                if (ReadMatchDistances > i30) {
                    ReadMatchDistances = i30;
                    int i38 = 0;
                    while (ReadMatchDistances > this._matchDistances[i38]) {
                        i38 += 2;
                    }
                    this._matchDistances[i38] = ReadMatchDistances;
                    i24 = i38 + 2;
                }
                if (ReadMatchDistances >= i33) {
                    int GetPrice03 = GetPrice13 + com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice0(this._isRep[StateUpdateMatch]);
                    while (i13 < i23 + ReadMatchDistances) {
                        i13++;
                        this._optimum[i13].Price = kIfinityPrice;
                    }
                    int i39 = 0;
                    while (i33 > this._matchDistances[i39]) {
                        i39 += 2;
                    }
                    int i40 = i33;
                    while (true) {
                        int i41 = this._matchDistances[i39 + 1];
                        int GetPosLenPrice2 = GetPrice03 + GetPosLenPrice(i41, i40, i29);
                        Optimal optimal8 = this._optimum[i23 + i40];
                        if (GetPosLenPrice2 < optimal8.Price) {
                            optimal8.Price = GetPosLenPrice2;
                            optimal8.PosPrev = i23;
                            optimal8.BackPrev = i41 + 4;
                            optimal8.Prev1IsChar = false;
                        }
                        if (i40 == this._matchDistances[i39]) {
                            if (i40 < min) {
                                int GetMatchLen4 = this._matchFinder.GetMatchLen(i40, i41, Math.min((min - 1) - i40, this._numFastBytes));
                                if (GetMatchLen4 >= 2) {
                                    int StateUpdateMatch2 = Base.StateUpdateMatch(StateUpdateMatch);
                                    int GetPrice04 = GetPosLenPrice2 + com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice0(this._isMatch[(StateUpdateMatch2 << 4) + ((i + i40) & this._posStateMask)]) + this._literalEncoder.GetSubCoder(i + i40, this._matchFinder.GetIndexByte((i40 - 1) - 1)).GetPrice(true, this._matchFinder.GetIndexByte((i40 - (i41 + 1)) - 1), this._matchFinder.GetIndexByte(i40 - 1));
                                    int StateUpdateChar3 = Base.StateUpdateChar(StateUpdateMatch2);
                                    int i42 = (i + i40 + 1) & this._posStateMask;
                                    int GetPrice17 = GetPrice04 + com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice1(this._isMatch[(StateUpdateChar3 << 4) + i42]) + com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice1(this._isRep[StateUpdateChar3]);
                                    int i43 = i40 + 1 + GetMatchLen4;
                                    while (i13 < i23 + i43) {
                                        i13++;
                                        this._optimum[i13].Price = kIfinityPrice;
                                    }
                                    int GetRepPrice5 = GetPrice17 + GetRepPrice(0, GetMatchLen4, StateUpdateChar3, i42);
                                    Optimal optimal9 = this._optimum[i23 + i43];
                                    if (GetRepPrice5 < optimal9.Price) {
                                        optimal9.Price = GetRepPrice5;
                                        optimal9.PosPrev = i23 + i40 + 1;
                                        optimal9.BackPrev = 0;
                                        optimal9.Prev1IsChar = true;
                                        optimal9.Prev2 = true;
                                        optimal9.PosPrev2 = i23;
                                        optimal9.BackPrev2 = i41 + 4;
                                    }
                                }
                            }
                            i39 += 2;
                            if (i39 != i24) {
                            }
                        }
                        i40++;
                    }
                }
            }
        }
    }

    boolean ChangePair(int i, int i2) {
        return i < 33554432 && i2 >= (i << 7);
    }

    void WriteEndMarker(int i) {
        if (this._writeEndMark) {
            this._rangeEncoder.Encode(this._isMatch, (this._state << 4) + i, 1);
            this._rangeEncoder.Encode(this._isRep, this._state, 0);
            this._state = Base.StateUpdateMatch(this._state);
            this._lenEncoder.Encode(this._rangeEncoder, 0, i);
            this._posSlotEncoder[Base.GetLenToPosState(2)].Encode(this._rangeEncoder, 63);
            this._rangeEncoder.EncodeDirectBits(Lua.MAXARG_Ax, 26);
            this._posAlignEncoder.ReverseEncode(this._rangeEncoder, 15);
        }
    }

    void Flush(int i) {
        ReleaseMFStream();
        WriteEndMarker(i & this._posStateMask);
        this._rangeEncoder.FlushData();
        this._rangeEncoder.FlushStream();
    }

    public void CodeOneBlock(long[] jArr, long[] jArr2, boolean[] zArr) {
        jArr[0] = 0;
        jArr2[0] = 0;
        zArr[0] = true;
        if (this._inStream != null) {
            this._matchFinder.SetStream(this._inStream);
            this._matchFinder.Init();
            this._needReleaseMFStream = true;
            this._inStream = null;
        }
        if (this._finished) {
            return;
        }
        this._finished = true;
        long j = this.nowPos64;
        if (this.nowPos64 == 0) {
            if (this._matchFinder.GetNumAvailableBytes() == 0) {
                Flush((int) this.nowPos64);
                return;
            }
            ReadMatchDistances();
            this._rangeEncoder.Encode(this._isMatch, (this._state << 4) + (((int) this.nowPos64) & this._posStateMask), 0);
            this._state = Base.StateUpdateChar(this._state);
            byte GetIndexByte = this._matchFinder.GetIndexByte(0 - this._additionalOffset);
            this._literalEncoder.GetSubCoder((int) this.nowPos64, this._previousByte).Encode(this._rangeEncoder, GetIndexByte);
            this._previousByte = GetIndexByte;
            this._additionalOffset--;
            this.nowPos64++;
        }
        if (this._matchFinder.GetNumAvailableBytes() == 0) {
            Flush((int) this.nowPos64);
            return;
        }
        while (true) {
            int GetOptimum = GetOptimum((int) this.nowPos64);
            int i = this.backRes;
            int i2 = ((int) this.nowPos64) & this._posStateMask;
            int i3 = (this._state << 4) + i2;
            if (GetOptimum == 1 && i == -1) {
                this._rangeEncoder.Encode(this._isMatch, i3, 0);
                byte GetIndexByte2 = this._matchFinder.GetIndexByte(0 - this._additionalOffset);
                LiteralEncoder.Encoder2 GetSubCoder = this._literalEncoder.GetSubCoder((int) this.nowPos64, this._previousByte);
                if (!Base.StateIsCharState(this._state)) {
                    GetSubCoder.EncodeMatched(this._rangeEncoder, this._matchFinder.GetIndexByte(((0 - this._repDistances[0]) - 1) - this._additionalOffset), GetIndexByte2);
                } else {
                    GetSubCoder.Encode(this._rangeEncoder, GetIndexByte2);
                }
                this._previousByte = GetIndexByte2;
                this._state = Base.StateUpdateChar(this._state);
            } else {
                this._rangeEncoder.Encode(this._isMatch, i3, 1);
                if (i < 4) {
                    this._rangeEncoder.Encode(this._isRep, this._state, 1);
                    if (i == 0) {
                        this._rangeEncoder.Encode(this._isRepG0, this._state, 0);
                        if (GetOptimum == 1) {
                            this._rangeEncoder.Encode(this._isRep0Long, i3, 0);
                        } else {
                            this._rangeEncoder.Encode(this._isRep0Long, i3, 1);
                        }
                    } else {
                        this._rangeEncoder.Encode(this._isRepG0, this._state, 1);
                        if (i == 1) {
                            this._rangeEncoder.Encode(this._isRepG1, this._state, 0);
                        } else {
                            this._rangeEncoder.Encode(this._isRepG1, this._state, 1);
                            this._rangeEncoder.Encode(this._isRepG2, this._state, i - 2);
                        }
                    }
                    if (GetOptimum == 1) {
                        this._state = Base.StateUpdateShortRep(this._state);
                    } else {
                        this._repMatchLenEncoder.Encode(this._rangeEncoder, GetOptimum - 2, i2);
                        this._state = Base.StateUpdateRep(this._state);
                    }
                    int i4 = this._repDistances[i];
                    if (i != 0) {
                        for (int i5 = i; i5 > 0; i5--) {
                            int[] iArr = this._repDistances;
                            iArr[i5] = iArr[i5 - 1];
                        }
                        this._repDistances[0] = i4;
                    }
                } else {
                    this._rangeEncoder.Encode(this._isRep, this._state, 0);
                    this._state = Base.StateUpdateMatch(this._state);
                    this._lenEncoder.Encode(this._rangeEncoder, GetOptimum - 2, i2);
                    int i6 = i - 4;
                    int GetPosSlot = GetPosSlot(i6);
                    this._posSlotEncoder[Base.GetLenToPosState(GetOptimum)].Encode(this._rangeEncoder, GetPosSlot);
                    if (GetPosSlot >= 4) {
                        int i7 = (GetPosSlot >> 1) - 1;
                        int i8 = (2 | (GetPosSlot & 1)) << i7;
                        int i9 = i6 - i8;
                        if (GetPosSlot < 14) {
                            BitTreeEncoder.ReverseEncode(this._posEncoders, (i8 - GetPosSlot) - 1, this._rangeEncoder, i7, i9);
                        } else {
                            this._rangeEncoder.EncodeDirectBits(i9 >> 4, i7 - 4);
                            this._posAlignEncoder.ReverseEncode(this._rangeEncoder, i9 & 15);
                            this._alignPriceCount++;
                        }
                    }
                    for (int i10 = 3; i10 > 0; i10--) {
                        int[] iArr2 = this._repDistances;
                        iArr2[i10] = iArr2[i10 - 1];
                    }
                    this._repDistances[0] = i6;
                    this._matchPriceCount++;
                }
                this._previousByte = this._matchFinder.GetIndexByte((GetOptimum - 1) - this._additionalOffset);
            }
            this._additionalOffset -= GetOptimum;
            this.nowPos64 += GetOptimum;
            if (this._additionalOffset == 0) {
                if (this._matchPriceCount >= 128) {
                    FillDistancesPrices();
                }
                if (this._alignPriceCount >= 16) {
                    FillAlignPrices();
                }
                jArr[0] = this.nowPos64;
                jArr2[0] = this._rangeEncoder.GetProcessedSizeAdd();
                if (this._matchFinder.GetNumAvailableBytes() == 0) {
                    Flush((int) this.nowPos64);
                    return;
                } else if (this.nowPos64 - j >= 4096) {
                    this._finished = false;
                    zArr[0] = false;
                    return;
                }
            }
        }
    }

    void ReleaseMFStream() {
        if (this._matchFinder != null && this._needReleaseMFStream) {
            this._matchFinder.ReleaseStream();
            this._needReleaseMFStream = false;
        }
    }

    void SetOutStream(OutputStream outputStream) {
        this._rangeEncoder.SetStream(outputStream);
    }

    void ReleaseOutStream() {
        this._rangeEncoder.ReleaseStream();
    }

    void ReleaseStreams() {
        ReleaseMFStream();
        ReleaseOutStream();
    }

    void SetStreams(InputStream inputStream, OutputStream outputStream, long j, long j2) {
        this._inStream = inputStream;
        this._finished = false;
        Create();
        SetOutStream(outputStream);
        Init();
        FillDistancesPrices();
        FillAlignPrices();
        this._lenEncoder.SetTableSize((this._numFastBytes + 1) - 2);
        this._lenEncoder.UpdateTables(1 << this._posStateBits);
        this._repMatchLenEncoder.SetTableSize((this._numFastBytes + 1) - 2);
        this._repMatchLenEncoder.UpdateTables(1 << this._posStateBits);
        this.nowPos64 = 0L;
    }

    public void Code(InputStream inputStream, OutputStream outputStream, long j, long j2, ICodeProgress iCodeProgress) {
        this._needReleaseMFStream = false;
        try {
            SetStreams(inputStream, outputStream, j, j2);
            while (true) {
                CodeOneBlock(this.processedInSize, this.processedOutSize, this.finished);
                if (this.finished[0]) {
                    return;
                }
                if (iCodeProgress != null) {
                    iCodeProgress.SetProgress(this.processedInSize[0], this.processedOutSize[0]);
                }
            }
        } finally {
            ReleaseStreams();
        }
    }

    public void WriteCoderProperties(OutputStream outputStream) {
        this.properties[0] = (byte) ((((this._posStateBits * 5) + this._numLiteralPosStateBits) * 9) + this._numLiteralContextBits);
        for (int i = 0; i < 4; i++) {
            this.properties[i + 1] = (byte) (this._dictionarySize >> (i * 8));
        }
        outputStream.write(this.properties, 0, 5);
    }

    void FillDistancesPrices() {
        for (int i = 4; i < 128; i++) {
            int GetPosSlot = GetPosSlot(i);
            int i2 = (GetPosSlot >> 1) - 1;
            int i3 = (2 | (GetPosSlot & 1)) << i2;
            this.tempPrices[i] = BitTreeEncoder.ReverseGetPrice(this._posEncoders, (i3 - GetPosSlot) - 1, i2, i - i3);
        }
        for (int i4 = 0; i4 < 4; i4++) {
            BitTreeEncoder bitTreeEncoder = this._posSlotEncoder[i4];
            int i5 = i4 << 6;
            for (int i6 = 0; i6 < this._distTableSize; i6++) {
                this._posSlotPrices[i5 + i6] = bitTreeEncoder.GetPrice(i6);
            }
            for (int i7 = 14; i7 < this._distTableSize; i7++) {
                int[] iArr = this._posSlotPrices;
                int i8 = i5 + i7;
                iArr[i8] = iArr[i8] + ((((i7 >> 1) - 1) - 4) << 6);
            }
            int i9 = i4 << 7;
            int i10 = 0;
            while (i10 < 4) {
                this._distancesPrices[i9 + i10] = this._posSlotPrices[i5 + i10];
                i10++;
            }
            while (i10 < 128) {
                this._distancesPrices[i9 + i10] = this._posSlotPrices[i5 + GetPosSlot(i10)] + this.tempPrices[i10];
                i10++;
            }
        }
        this._matchPriceCount = 0;
    }

    void FillAlignPrices() {
        for (int i = 0; i < 16; i++) {
            this._alignPrices[i] = this._posAlignEncoder.ReverseGetPrice(i);
        }
        this._alignPriceCount = 0;
    }

    public boolean SetAlgorithm(int i) {
        return true;
    }

    public boolean SetDictionarySize(int i) {
        if (i <= 0 || i > 536870912) {
            return false;
        }
        this._dictionarySize = i;
        int i2 = 0;
        while (i > (1 << i2)) {
            i2++;
        }
        this._distTableSize = i2 << 1;
        return true;
    }

    public boolean SetNumFastBytes(int i) {
        if (i < 5 || i > 273) {
            return false;
        }
        this._numFastBytes = i;
        return true;
    }

    public boolean SetMatchFinder(int i) {
        if (i < 0 || i > 2) {
            return false;
        }
        int i2 = this._matchFinderType;
        this._matchFinderType = i;
        if (this._matchFinder != null && i2 != this._matchFinderType) {
            this._dictionarySizePrev = -1;
            this._matchFinder = null;
            return true;
        }
        return true;
    }

    public boolean SetLcLpPb(int i, int i2, int i3) {
        if (i2 < 0 || i2 > 4 || i < 0 || i > 8 || i3 < 0 || i3 > 4) {
            return false;
        }
        this._numLiteralPosStateBits = i2;
        this._numLiteralContextBits = i;
        this._posStateBits = i3;
        this._posStateMask = (1 << this._posStateBits) - 1;
        return true;
    }

    public void SetEndMarkerMode(boolean z) {
        this._writeEndMark = z;
    }
}
