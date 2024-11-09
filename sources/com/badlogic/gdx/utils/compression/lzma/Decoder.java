package com.badlogic.gdx.utils.compression.lzma;

import com.badlogic.gdx.utils.compression.lz.OutWindow;
import com.badlogic.gdx.utils.compression.rangecoder.BitTreeDecoder;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/compression/lzma/Decoder.class */
public class Decoder {
    OutWindow m_OutWindow = new OutWindow();
    com.badlogic.gdx.utils.compression.rangecoder.Decoder m_RangeDecoder = new com.badlogic.gdx.utils.compression.rangecoder.Decoder();
    short[] m_IsMatchDecoders = new short[192];
    short[] m_IsRepDecoders = new short[12];
    short[] m_IsRepG0Decoders = new short[12];
    short[] m_IsRepG1Decoders = new short[12];
    short[] m_IsRepG2Decoders = new short[12];
    short[] m_IsRep0LongDecoders = new short[192];
    BitTreeDecoder[] m_PosSlotDecoder = new BitTreeDecoder[4];
    short[] m_PosDecoders = new short[114];
    BitTreeDecoder m_PosAlignDecoder = new BitTreeDecoder(4);
    LenDecoder m_LenDecoder = new LenDecoder();
    LenDecoder m_RepLenDecoder = new LenDecoder();
    LiteralDecoder m_LiteralDecoder = new LiteralDecoder();
    int m_DictionarySize = -1;
    int m_DictionarySizeCheck = -1;
    int m_PosStateMask;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/compression/lzma/Decoder$LenDecoder.class */
    public class LenDecoder {
        short[] m_Choice = new short[2];
        BitTreeDecoder[] m_LowCoder = new BitTreeDecoder[16];
        BitTreeDecoder[] m_MidCoder = new BitTreeDecoder[16];
        BitTreeDecoder m_HighCoder = new BitTreeDecoder(8);
        int m_NumPosStates = 0;

        LenDecoder() {
        }

        public void Create(int i) {
            while (this.m_NumPosStates < i) {
                this.m_LowCoder[this.m_NumPosStates] = new BitTreeDecoder(3);
                this.m_MidCoder[this.m_NumPosStates] = new BitTreeDecoder(3);
                this.m_NumPosStates++;
            }
        }

        public void Init() {
            com.badlogic.gdx.utils.compression.rangecoder.Decoder.InitBitModels(this.m_Choice);
            for (int i = 0; i < this.m_NumPosStates; i++) {
                this.m_LowCoder[i].Init();
                this.m_MidCoder[i].Init();
            }
            this.m_HighCoder.Init();
        }

        public int Decode(com.badlogic.gdx.utils.compression.rangecoder.Decoder decoder, int i) {
            int Decode;
            if (decoder.DecodeBit(this.m_Choice, 0) == 0) {
                return this.m_LowCoder[i].Decode(decoder);
            }
            if (decoder.DecodeBit(this.m_Choice, 1) == 0) {
                Decode = 8 + this.m_MidCoder[i].Decode(decoder);
            } else {
                Decode = 8 + 8 + this.m_HighCoder.Decode(decoder);
            }
            return Decode;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/compression/lzma/Decoder$LiteralDecoder.class */
    public class LiteralDecoder {
        Decoder2[] m_Coders;
        int m_NumPrevBits;
        int m_NumPosBits;
        int m_PosMask;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/compression/lzma/Decoder$LiteralDecoder$Decoder2.class */
        public class Decoder2 {
            short[] m_Decoders = new short[768];

            Decoder2() {
            }

            public void Init() {
                com.badlogic.gdx.utils.compression.rangecoder.Decoder.InitBitModels(this.m_Decoders);
            }

            public byte DecodeNormal(com.badlogic.gdx.utils.compression.rangecoder.Decoder decoder) {
                int DecodeBit;
                int i = 1;
                do {
                    DecodeBit = (i << 1) | decoder.DecodeBit(this.m_Decoders, i);
                    i = DecodeBit;
                } while (DecodeBit < 256);
                return (byte) i;
            }

            public byte DecodeWithMatchByte(com.badlogic.gdx.utils.compression.rangecoder.Decoder decoder, byte b2) {
                int i = 1;
                while (true) {
                    int i2 = (b2 >> 7) & 1;
                    b2 = (byte) (b2 << 1);
                    int DecodeBit = decoder.DecodeBit(this.m_Decoders, ((i2 + 1) << 8) + i);
                    i = (i << 1) | DecodeBit;
                    if (i2 != DecodeBit) {
                        while (i < 256) {
                            i = (i << 1) | decoder.DecodeBit(this.m_Decoders, i);
                        }
                    } else if (i >= 256) {
                        break;
                    }
                }
                return (byte) i;
            }
        }

        LiteralDecoder() {
        }

        public void Create(int i, int i2) {
            if (this.m_Coders != null && this.m_NumPrevBits == i2 && this.m_NumPosBits == i) {
                return;
            }
            this.m_NumPosBits = i;
            this.m_PosMask = (1 << i) - 1;
            this.m_NumPrevBits = i2;
            int i3 = 1 << (this.m_NumPrevBits + this.m_NumPosBits);
            this.m_Coders = new Decoder2[i3];
            for (int i4 = 0; i4 < i3; i4++) {
                this.m_Coders[i4] = new Decoder2();
            }
        }

        public void Init() {
            int i = 1 << (this.m_NumPrevBits + this.m_NumPosBits);
            for (int i2 = 0; i2 < i; i2++) {
                this.m_Coders[i2].Init();
            }
        }

        Decoder2 GetDecoder(int i, byte b2) {
            return this.m_Coders[((i & this.m_PosMask) << this.m_NumPrevBits) + ((b2 & 255) >>> (8 - this.m_NumPrevBits))];
        }
    }

    public Decoder() {
        for (int i = 0; i < 4; i++) {
            this.m_PosSlotDecoder[i] = new BitTreeDecoder(6);
        }
    }

    boolean SetDictionarySize(int i) {
        if (i < 0) {
            return false;
        }
        if (this.m_DictionarySize != i) {
            this.m_DictionarySize = i;
            this.m_DictionarySizeCheck = Math.max(this.m_DictionarySize, 1);
            this.m_OutWindow.Create(Math.max(this.m_DictionarySizeCheck, 4096));
            return true;
        }
        return true;
    }

    boolean SetLcLpPb(int i, int i2, int i3) {
        if (i > 8 || i2 > 4 || i3 > 4) {
            return false;
        }
        this.m_LiteralDecoder.Create(i2, i);
        int i4 = 1 << i3;
        this.m_LenDecoder.Create(i4);
        this.m_RepLenDecoder.Create(i4);
        this.m_PosStateMask = i4 - 1;
        return true;
    }

    void Init() {
        this.m_OutWindow.Init(false);
        com.badlogic.gdx.utils.compression.rangecoder.Decoder.InitBitModels(this.m_IsMatchDecoders);
        com.badlogic.gdx.utils.compression.rangecoder.Decoder.InitBitModels(this.m_IsRep0LongDecoders);
        com.badlogic.gdx.utils.compression.rangecoder.Decoder.InitBitModels(this.m_IsRepDecoders);
        com.badlogic.gdx.utils.compression.rangecoder.Decoder.InitBitModels(this.m_IsRepG0Decoders);
        com.badlogic.gdx.utils.compression.rangecoder.Decoder.InitBitModels(this.m_IsRepG1Decoders);
        com.badlogic.gdx.utils.compression.rangecoder.Decoder.InitBitModels(this.m_IsRepG2Decoders);
        com.badlogic.gdx.utils.compression.rangecoder.Decoder.InitBitModels(this.m_PosDecoders);
        this.m_LiteralDecoder.Init();
        for (int i = 0; i < 4; i++) {
            this.m_PosSlotDecoder[i].Init();
        }
        this.m_LenDecoder.Init();
        this.m_RepLenDecoder.Init();
        this.m_PosAlignDecoder.Init();
        this.m_RangeDecoder.Init();
    }

    public boolean Code(InputStream inputStream, OutputStream outputStream, long j) {
        byte DecodeNormal;
        int Decode;
        int i;
        this.m_RangeDecoder.SetStream(inputStream);
        this.m_OutWindow.SetStream(outputStream);
        Init();
        int StateInit = Base.StateInit();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        long j2 = 0;
        byte b2 = 0;
        while (true) {
            if (j >= 0 && j2 >= j) {
                break;
            }
            int i6 = ((int) j2) & this.m_PosStateMask;
            if (this.m_RangeDecoder.DecodeBit(this.m_IsMatchDecoders, (StateInit << 4) + i6) == 0) {
                LiteralDecoder.Decoder2 GetDecoder = this.m_LiteralDecoder.GetDecoder((int) j2, b2);
                if (!Base.StateIsCharState(StateInit)) {
                    DecodeNormal = GetDecoder.DecodeWithMatchByte(this.m_RangeDecoder, this.m_OutWindow.GetByte(i2));
                } else {
                    DecodeNormal = GetDecoder.DecodeNormal(this.m_RangeDecoder);
                }
                b2 = DecodeNormal;
                this.m_OutWindow.PutByte(b2);
                StateInit = Base.StateUpdateChar(StateInit);
                j2++;
            } else {
                if (this.m_RangeDecoder.DecodeBit(this.m_IsRepDecoders, StateInit) == 1) {
                    Decode = 0;
                    if (this.m_RangeDecoder.DecodeBit(this.m_IsRepG0Decoders, StateInit) == 0) {
                        if (this.m_RangeDecoder.DecodeBit(this.m_IsRep0LongDecoders, (StateInit << 4) + i6) == 0) {
                            StateInit = Base.StateUpdateShortRep(StateInit);
                            Decode = 1;
                        }
                    } else {
                        if (this.m_RangeDecoder.DecodeBit(this.m_IsRepG1Decoders, StateInit) == 0) {
                            i = i3;
                        } else {
                            if (this.m_RangeDecoder.DecodeBit(this.m_IsRepG2Decoders, StateInit) == 0) {
                                i = i4;
                            } else {
                                i = i5;
                                i5 = i4;
                            }
                            i4 = i3;
                        }
                        i3 = i2;
                        i2 = i;
                    }
                    if (Decode == 0) {
                        Decode = this.m_RepLenDecoder.Decode(this.m_RangeDecoder, i6) + 2;
                        StateInit = Base.StateUpdateRep(StateInit);
                    }
                } else {
                    i5 = i4;
                    i4 = i3;
                    i3 = i2;
                    Decode = 2 + this.m_LenDecoder.Decode(this.m_RangeDecoder, i6);
                    StateInit = Base.StateUpdateMatch(StateInit);
                    int Decode2 = this.m_PosSlotDecoder[Base.GetLenToPosState(Decode)].Decode(this.m_RangeDecoder);
                    if (Decode2 >= 4) {
                        int i7 = (Decode2 >> 1) - 1;
                        int i8 = (2 | (Decode2 & 1)) << i7;
                        if (Decode2 < 14) {
                            i2 = i8 + BitTreeDecoder.ReverseDecode(this.m_PosDecoders, (i8 - Decode2) - 1, this.m_RangeDecoder, i7);
                        } else {
                            int DecodeDirectBits = i8 + (this.m_RangeDecoder.DecodeDirectBits(i7 - 4) << 4) + this.m_PosAlignDecoder.ReverseDecode(this.m_RangeDecoder);
                            i2 = DecodeDirectBits;
                            if (DecodeDirectBits < 0) {
                                if (i2 != -1) {
                                    return false;
                                }
                            }
                        }
                    } else {
                        i2 = Decode2;
                    }
                }
                if (i2 >= j2 || i2 >= this.m_DictionarySizeCheck) {
                    return false;
                }
                this.m_OutWindow.CopyBlock(i2, Decode);
                j2 += Decode;
                b2 = this.m_OutWindow.GetByte(0);
            }
        }
        this.m_OutWindow.Flush();
        this.m_OutWindow.ReleaseStream();
        this.m_RangeDecoder.ReleaseStream();
        return true;
    }

    public boolean SetDecoderProperties(byte[] bArr) {
        if (bArr.length < 5) {
            return false;
        }
        int i = bArr[0] & 255;
        int i2 = i % 9;
        int i3 = i / 9;
        int i4 = i3 % 5;
        int i5 = i3 / 5;
        int i6 = 0;
        for (int i7 = 0; i7 < 4; i7++) {
            i6 += (bArr[i7 + 1] & 255) << (i7 << 3);
        }
        if (SetLcLpPb(i2, i4, i5)) {
            return SetDictionarySize(i6);
        }
        return false;
    }
}
