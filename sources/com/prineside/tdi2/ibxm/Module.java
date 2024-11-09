package com.prineside.tdi2.ibxm;

import com.badlogic.gdx.net.HttpStatus;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ByteArray;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.Config;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.utils.REGS;
import com.prineside.tdi2.utils.StringFormatter;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import net.bytebuddy.description.type.TypeDescription;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ibxm/Module.class */
public class Module {
    public String songName;
    public int numChannels;
    public int numInstruments;
    public int numPatterns;
    public int sequenceLength;
    public int restartPos;
    public int defaultGVol;
    public int defaultSpeed;
    public int defaultTempo;
    public int c2Rate;
    public int gain;
    public boolean linearPeriods;
    public boolean fastVolSlides;
    public int[] defaultPanning;
    public int[] sequence;
    public Pattern[] patterns;
    public Instrument[] instruments;

    /* renamed from: a, reason: collision with root package name */
    private String f2206a;

    /* renamed from: b, reason: collision with root package name */
    private Float f2207b;
    private static final byte[] c = new byte[4096];

    public Module() {
        this.songName = "Blank";
        this.numChannels = 4;
        this.numInstruments = 1;
        this.numPatterns = 1;
        this.sequenceLength = 1;
        this.restartPos = 0;
        this.defaultGVol = 64;
        this.defaultSpeed = 6;
        this.defaultTempo = 125;
        this.c2Rate = Sample.C2_PAL;
        this.gain = 64;
        this.linearPeriods = false;
        this.fastVolSlides = false;
        this.defaultPanning = new int[]{51, HttpStatus.SC_NO_CONTENT, HttpStatus.SC_NO_CONTENT, 51};
        this.sequence = new int[]{0};
        this.patterns = new Pattern[]{new Pattern(4, 64)};
        this.instruments = new Instrument[]{new Instrument(), new Instrument()};
        this.f2206a = "mod";
        this.f2207b = null;
    }

    public Module(Module module) {
        this.songName = "Blank";
        this.numChannels = 4;
        this.numInstruments = 1;
        this.numPatterns = 1;
        this.sequenceLength = 1;
        this.restartPos = 0;
        this.defaultGVol = 64;
        this.defaultSpeed = 6;
        this.defaultTempo = 125;
        this.c2Rate = Sample.C2_PAL;
        this.gain = 64;
        this.linearPeriods = false;
        this.fastVolSlides = false;
        this.defaultPanning = new int[]{51, HttpStatus.SC_NO_CONTENT, HttpStatus.SC_NO_CONTENT, 51};
        this.sequence = new int[]{0};
        this.patterns = new Pattern[]{new Pattern(4, 64)};
        this.instruments = new Instrument[]{new Instrument(), new Instrument()};
        this.f2206a = "mod";
        this.f2207b = null;
        this.songName = module.songName;
        this.numChannels = module.numChannels;
        this.numInstruments = module.numInstruments;
        this.numPatterns = module.numPatterns;
        this.sequenceLength = module.sequenceLength;
        this.restartPos = module.restartPos;
        this.defaultGVol = module.defaultGVol;
        this.defaultSpeed = module.defaultSpeed;
        this.defaultTempo = module.defaultTempo;
        this.c2Rate = module.c2Rate;
        this.gain = module.gain;
        this.linearPeriods = module.linearPeriods;
        this.fastVolSlides = module.fastVolSlides;
        this.defaultPanning = new int[module.defaultPanning.length];
        System.arraycopy(module.defaultPanning, 0, this.defaultPanning, 0, this.defaultPanning.length);
        this.sequence = new int[module.sequence.length];
        System.arraycopy(module.sequence, 0, this.sequence, 0, this.sequence.length);
        this.patterns = new Pattern[module.patterns.length];
        for (int i = 0; i < module.patterns.length; i++) {
            this.patterns[i] = new Pattern(module.patterns[i]);
        }
        this.instruments = new Instrument[module.instruments.length];
        for (int i2 = 0; i2 < module.instruments.length; i2++) {
            this.instruments[i2] = new Instrument(module.instruments[i2]);
        }
    }

    public int moduleHashCode() {
        return ((((((((31 + this.songName.hashCode()) * 31) + this.numChannels) * 31) + this.numInstruments) * 31) + this.numPatterns) * 31) + this.sequenceLength;
    }

    public String getFileExtension() {
        return this.f2206a;
    }

    public static Module fromZipInputStream(InputStream inputStream) {
        Module module = null;
        try {
            ZipInputStream zipInputStream = new ZipInputStream(inputStream);
            if (zipInputStream.getNextEntry() != null) {
                ByteArray byteArray = new ByteArray();
                synchronized (c) {
                    while (true) {
                        int read = zipInputStream.read(c);
                        if (read == -1) {
                            break;
                        }
                        byteArray.addAll(c, 0, read);
                    }
                }
                zipInputStream.closeEntry();
                module = new Module(byteArray.toArray());
            }
            zipInputStream.close();
            return module;
        } catch (Exception e) {
            throw new IllegalArgumentException("failed to load module from zip input stream", e);
        }
    }

    public Module(InputStream inputStream) {
        this(new Data(inputStream));
    }

    public Module(Data data) {
        this.songName = "Blank";
        this.numChannels = 4;
        this.numInstruments = 1;
        this.numPatterns = 1;
        this.sequenceLength = 1;
        this.restartPos = 0;
        this.defaultGVol = 64;
        this.defaultSpeed = 6;
        this.defaultTempo = 125;
        this.c2Rate = Sample.C2_PAL;
        this.gain = 64;
        this.linearPeriods = false;
        this.fastVolSlides = false;
        this.defaultPanning = new int[]{51, HttpStatus.SC_NO_CONTENT, HttpStatus.SC_NO_CONTENT, 51};
        this.sequence = new int[]{0};
        this.patterns = new Pattern[]{new Pattern(4, 64)};
        this.instruments = new Instrument[]{new Instrument(), new Instrument()};
        this.f2206a = "mod";
        this.f2207b = null;
        if (data.strLatin1(0, 17).equals("Extended Module: ")) {
            c(data);
        } else if (data.strLatin1(44, 4).equals("SCRM")) {
            b(data);
        } else {
            a(data);
        }
    }

    public Module(byte[] bArr) {
        this(new Data(bArr));
    }

    private void a(Data data) {
        this.f2206a = "mod";
        this.songName = data.strLatin1(0, 20);
        this.sequenceLength = data.uByte(950) & 127;
        this.restartPos = data.uByte(951) & 127;
        if (this.restartPos >= this.sequenceLength) {
            this.restartPos = 0;
        }
        this.sequence = new int[128];
        for (int i = 0; i < 128; i++) {
            int uByte = data.uByte(i + 952) & 127;
            this.sequence[i] = uByte;
            if (uByte >= this.numPatterns) {
                this.numPatterns = uByte + 1;
            }
        }
        switch (data.ubeShort(1082)) {
            case 17224:
                this.numChannels = (data.uByte(Config.VIEWPORT_HEIGHT) - 48) * 10;
                this.numChannels += data.uByte(1081) - 48;
                this.c2Rate = 8363;
                this.gain = 32;
                break;
            case 18510:
                this.numChannels = data.uByte(Config.VIEWPORT_HEIGHT) - 48;
                this.c2Rate = 8363;
                this.gain = 32;
                break;
            case 19233:
            case 19246:
            case 21556:
                this.numChannels = 4;
                this.c2Rate = Sample.C2_PAL;
                this.gain = 64;
                break;
            default:
                throw new IllegalArgumentException("MOD Format not recognised!");
        }
        this.defaultGVol = 64;
        this.defaultSpeed = 6;
        this.defaultTempo = 125;
        this.defaultPanning = new int[this.numChannels];
        for (int i2 = 0; i2 < this.numChannels; i2++) {
            this.defaultPanning[i2] = 51;
            if ((i2 & 3) == 1 || (i2 & 3) == 2) {
                this.defaultPanning[i2] = 204;
            }
        }
        int i3 = 1084;
        this.patterns = new Pattern[this.numPatterns];
        for (int i4 = 0; i4 < this.numPatterns; i4++) {
            Pattern pattern = new Pattern(this.numChannels, 64);
            this.patterns[i4] = pattern;
            for (int i5 = 0; i5 < pattern.data.length; i5 += 5) {
                int uByte2 = (((data.uByte(i3) & 15) << 8) | data.uByte(i3 + 1)) << 2;
                if (uByte2 >= 112 && uByte2 <= 6848) {
                    int log2 = (-12) * Channel.log2((uByte2 << 15) / 29021);
                    pattern.data[i5] = (byte) ((log2 + (log2 & 16384)) >> 15);
                }
                pattern.data[i5 + 1] = (byte) (((data.uByte(i3 + 2) & User32.VK_OEM_ATTN) >> 4) | (data.uByte(i3) & 16));
                int uByte3 = data.uByte(i3 + 2) & 15;
                int uByte4 = data.uByte(i3 + 3);
                int i6 = uByte4;
                if (uByte4 == 0 && (uByte3 < 3 || uByte3 == 10)) {
                    uByte3 = 0;
                }
                if (i6 == 0 && (uByte3 == 5 || uByte3 == 6)) {
                    uByte3 -= 2;
                }
                if (uByte3 == 8 && this.numChannels == 4) {
                    i6 = 0;
                    uByte3 = 0;
                }
                pattern.data[i5 + 3] = (byte) uByte3;
                pattern.data[i5 + 4] = (byte) i6;
                i3 += 4;
            }
        }
        this.numInstruments = 31;
        this.instruments = new Instrument[this.numInstruments + 1];
        this.instruments[0] = new Instrument();
        for (int i7 = 1; i7 <= this.numInstruments; i7++) {
            Instrument instrument = new Instrument();
            this.instruments[i7] = instrument;
            Sample sample = instrument.samples[0];
            instrument.name = data.strLatin1((i7 * 30) - 10, 22);
            int ubeShort = data.ubeShort((i7 * 30) + 12) << 1;
            int uByte5 = (data.uByte((i7 * 30) + 14) & 15) << 4;
            sample.fineTune = uByte5 < 128 ? uByte5 : uByte5 - 256;
            int uByte6 = data.uByte((i7 * 30) + 15) & 127;
            sample.volume = uByte6 <= 64 ? uByte6 : 64;
            sample.panning = -1;
            int ubeShort2 = data.ubeShort((i7 * 30) + 16) << 1;
            int ubeShort3 = data.ubeShort((i7 * 30) + 18) << 1;
            if (ubeShort2 + ubeShort3 > ubeShort) {
                if ((ubeShort2 / 2) + ubeShort3 <= ubeShort) {
                    ubeShort2 /= 2;
                } else {
                    ubeShort3 = ubeShort - ubeShort2;
                }
            }
            if (ubeShort3 < 4) {
                ubeShort2 = ubeShort;
                ubeShort3 = 0;
            }
            sample.setSampleData(data.samS8(i3, ubeShort), ubeShort2, ubeShort3, false);
            i3 += ubeShort;
        }
    }

    private void b(Data data) {
        this.f2206a = "s3m";
        this.songName = data.strCp850(0, 28);
        this.sequenceLength = data.uleShort(32);
        this.numInstruments = data.uleShort(34);
        this.numPatterns = data.uleShort(36);
        this.fastVolSlides = (data.uleShort(38) & 64) == 64 || data.uleShort(40) == 4864;
        boolean z = data.uleShort(42) == 1;
        if (data.uleInt(44) != 1297236819) {
            throw new IllegalArgumentException("Not an S3M file!");
        }
        this.defaultGVol = data.uByte(48);
        this.defaultSpeed = data.uByte(49);
        this.defaultTempo = data.uByte(50);
        this.c2Rate = 8363;
        this.gain = data.uByte(51) & 127;
        boolean z2 = (data.uByte(51) & 128) == 128;
        boolean z3 = data.uByte(53) == 252;
        this.numChannels = 0;
        int[] iArr = new int[32];
        for (int i = 0; i < 32; i++) {
            iArr[i] = -1;
            if (data.uByte(i + 64) < 16) {
                int i2 = this.numChannels;
                this.numChannels = i2 + 1;
                iArr[i] = i2;
            }
        }
        this.sequence = new int[this.sequenceLength];
        for (int i3 = 0; i3 < this.sequenceLength; i3++) {
            this.sequence[i3] = data.uByte(i3 + 96);
        }
        int i4 = 96 + this.sequenceLength;
        this.instruments = new Instrument[this.numInstruments + 1];
        this.instruments[0] = new Instrument();
        for (int i5 = 1; i5 <= this.numInstruments; i5++) {
            Instrument instrument = new Instrument();
            this.instruments[i5] = instrument;
            Sample sample = instrument.samples[0];
            int uleShort = data.uleShort(i4) << 4;
            i4 += 2;
            instrument.name = data.strCp850(uleShort + 48, 28);
            if (data.uByte(uleShort) == 1 && data.uleShort(uleShort + 76) == 17235) {
                int uByte = (data.uByte(uleShort + 13) << 20) + (data.uleShort(uleShort + 14) << 4);
                int uleInt = data.uleInt(uleShort + 16);
                int uleInt2 = data.uleInt(uleShort + 20);
                int uleInt3 = data.uleInt(uleShort + 24) - uleInt2;
                sample.volume = data.uByte(uleShort + 28);
                sample.panning = -1;
                boolean z4 = data.uByte(uleShort + 30) != 0;
                boolean z5 = (data.uByte(uleShort + 31) & 1) == 1;
                if (uleInt2 + uleInt3 > uleInt) {
                    uleInt3 = uleInt - uleInt2;
                }
                if (uleInt3 <= 0 || !z5) {
                    uleInt2 = uleInt;
                    uleInt3 = 0;
                }
                data.uByte(uleShort + 31);
                boolean z6 = (data.uByte(uleShort + 31) & 4) == 4;
                if (z4) {
                    throw new IllegalArgumentException("Packed samples not supported!");
                }
                int log2 = (Channel.log2(data.uleInt(uleShort + 32)) - Channel.log2(this.c2Rate)) * 12;
                sample.relNote = log2 >> 15;
                sample.fineTune = (log2 & 32767) >> 8;
                if (z6) {
                    if (z) {
                        sample.setSampleData(data.samS16(uByte, uleInt), uleInt2, uleInt3, false);
                    } else {
                        sample.setSampleData(data.samU16(uByte, uleInt), uleInt2, uleInt3, false);
                    }
                } else if (z) {
                    sample.setSampleData(data.samS8(uByte, uleInt), uleInt2, uleInt3, false);
                } else {
                    sample.setSampleData(data.samU8(uByte, uleInt), uleInt2, uleInt3, false);
                }
            }
        }
        this.patterns = new Pattern[this.numPatterns];
        for (int i6 = 0; i6 < this.numPatterns; i6++) {
            Pattern pattern = new Pattern(this.numChannels, 64);
            this.patterns[i6] = pattern;
            int uleShort2 = (data.uleShort(i4) << 4) + 2;
            int i7 = 0;
            while (i7 < 64) {
                int i8 = uleShort2;
                uleShort2++;
                int uByte2 = data.uByte(i8);
                if (uByte2 == 0) {
                    i7++;
                } else {
                    int i9 = 0;
                    int i10 = 0;
                    if ((uByte2 & 32) == 32) {
                        int i11 = uleShort2 + 1;
                        i9 = data.uByte(uleShort2);
                        uleShort2 = i11 + 1;
                        i10 = data.uByte(i11);
                        if (i9 < 254) {
                            i9 = ((i9 >> 4) * 12) + (i9 & 15) + 1;
                        }
                        if (i9 == 255) {
                            i9 = 0;
                        }
                    }
                    int i12 = 0;
                    if ((uByte2 & 64) == 64) {
                        int i13 = uleShort2;
                        uleShort2++;
                        int uByte3 = (data.uByte(i13) & 127) + 16;
                        i12 = uByte3;
                        if (uByte3 > 80) {
                            i12 = 0;
                        }
                    }
                    int i14 = 0;
                    int i15 = 0;
                    if ((uByte2 & 128) == 128) {
                        int i16 = uleShort2;
                        int i17 = uleShort2 + 1;
                        i14 = data.uByte(i16);
                        uleShort2 = i17 + 1;
                        i15 = data.uByte(i17);
                        if (i14 <= 0 || i14 >= 64) {
                            i15 = 0;
                            i14 = 0;
                        }
                        if (i14 > 0) {
                            i14 += 128;
                        }
                    }
                    int i18 = iArr[uByte2 & 31];
                    if (i18 >= 0) {
                        int i19 = ((i7 * this.numChannels) + i18) * 5;
                        pattern.data[i19] = (byte) i9;
                        pattern.data[i19 + 1] = (byte) i10;
                        pattern.data[i19 + 2] = (byte) i12;
                        pattern.data[i19 + 3] = (byte) i14;
                        pattern.data[i19 + 4] = (byte) i15;
                    }
                }
            }
            i4 += 2;
        }
        this.defaultPanning = new int[this.numChannels];
        for (int i20 = 0; i20 < 32; i20++) {
            if (iArr[i20] >= 0) {
                int i21 = 7;
                if (z2) {
                    i21 = 12;
                    if (data.uByte(i20 + 64) < 8) {
                        i21 = 3;
                    }
                }
                if (z3) {
                    int uByte4 = data.uByte(i4 + i20);
                    if ((uByte4 & 32) == 32) {
                        i21 = uByte4 & 15;
                    }
                }
                this.defaultPanning[iArr[i20]] = i21 * 17;
            }
        }
    }

    private void c(Data data) {
        byte b2;
        byte b3;
        byte b4;
        byte b5;
        byte b6;
        this.f2206a = "xm";
        if (data.uleShort(58) != 260) {
            throw new IllegalArgumentException("XM format version must be 0x0104!");
        }
        this.songName = data.strCp850(17, 20);
        boolean startsWith = data.strLatin1(38, 20).startsWith("DigiBooster Pro");
        int uleInt = 60 + data.uleInt(60);
        this.sequenceLength = data.uleShort(64);
        this.restartPos = data.uleShort(66);
        this.numChannels = data.uleShort(68);
        this.numPatterns = data.uleShort(70);
        this.numInstruments = data.uleShort(72);
        this.linearPeriods = (data.uleShort(74) & 1) > 0;
        this.defaultGVol = 64;
        this.defaultSpeed = data.uleShort(76);
        this.defaultTempo = data.uleShort(78);
        this.c2Rate = 8363;
        this.gain = 64;
        this.defaultPanning = new int[this.numChannels];
        for (int i = 0; i < this.numChannels; i++) {
            this.defaultPanning[i] = 128;
        }
        this.sequence = new int[this.sequenceLength];
        for (int i2 = 0; i2 < this.sequenceLength; i2++) {
            int uByte = data.uByte(i2 + 80);
            this.sequence[i2] = uByte < this.numPatterns ? uByte : 0;
        }
        this.patterns = new Pattern[this.numPatterns];
        for (int i3 = 0; i3 < this.numPatterns; i3++) {
            if (data.uByte(uleInt + 4) != 0) {
                throw new IllegalArgumentException("Unknown pattern packing type!");
            }
            int uleShort = data.uleShort(uleInt + 5);
            int i4 = uleShort;
            if (uleShort <= 0) {
                i4 = 1;
            }
            int i5 = i4 * this.numChannels;
            Pattern pattern = new Pattern(this.numChannels, i4);
            this.patterns[i3] = pattern;
            int uleShort2 = data.uleShort(uleInt + 7);
            int uleInt2 = uleInt + data.uleInt(uleInt);
            int i6 = uleInt2;
            int i7 = uleInt2 + uleShort2;
            if (uleShort2 > 0) {
                int i8 = 0;
                for (int i9 = 0; i9 < i5; i9++) {
                    int uByte2 = data.uByte(i6);
                    int i10 = uByte2;
                    if ((uByte2 & 128) == 0) {
                        i10 = 31;
                    } else {
                        i6++;
                    }
                    if ((i10 & 1) > 0) {
                        int i11 = i6;
                        i6++;
                        b2 = data.sByte(i11);
                    } else {
                        b2 = 0;
                    }
                    int i12 = i8;
                    int i13 = i8 + 1;
                    pattern.data[i12] = b2;
                    if ((i10 & 2) > 0) {
                        int i14 = i6;
                        i6++;
                        b3 = data.sByte(i14);
                    } else {
                        b3 = 0;
                    }
                    int i15 = i13 + 1;
                    pattern.data[i13] = b3;
                    if ((i10 & 4) > 0) {
                        int i16 = i6;
                        i6++;
                        b4 = data.sByte(i16);
                    } else {
                        b4 = 0;
                    }
                    int i17 = i15 + 1;
                    pattern.data[i15] = b4;
                    if ((i10 & 8) > 0) {
                        int i18 = i6;
                        i6++;
                        b5 = data.sByte(i18);
                    } else {
                        b5 = 0;
                    }
                    byte b7 = b5;
                    if ((i10 & 16) > 0) {
                        int i19 = i6;
                        i6++;
                        b6 = data.sByte(i19);
                    } else {
                        b6 = 0;
                    }
                    byte b8 = b6;
                    if (b7 >= 64) {
                        b8 = 0;
                        b7 = 0;
                    }
                    int i20 = i17 + 1;
                    pattern.data[i17] = b7;
                    i8 = i20 + 1;
                    pattern.data[i20] = b8;
                }
            }
            uleInt = i7;
        }
        this.instruments = new Instrument[this.numInstruments + 1];
        this.instruments[0] = new Instrument();
        for (int i21 = 1; i21 <= this.numInstruments; i21++) {
            Instrument instrument = new Instrument();
            this.instruments[i21] = instrument;
            instrument.name = data.strCp850(uleInt + 4, 22);
            int uleShort3 = data.uleShort(uleInt + 27);
            if (uleShort3 > 0) {
                instrument.numSamples = uleShort3;
                instrument.samples = new Sample[uleShort3];
                for (int i22 = 0; i22 < 96; i22++) {
                    instrument.keyToSample[i22 + 1] = data.uByte(uleInt + 33 + i22);
                }
                Envelope envelope = new Envelope();
                instrument.volumeEnvelope = envelope;
                envelope.pointsTick = new int[16];
                envelope.pointsAmpl = new int[16];
                int i23 = 0;
                for (int i24 = 0; i24 < 12; i24++) {
                    int i25 = uleInt + 129 + (i24 << 2);
                    i23 = (startsWith ? i23 : 0) + data.uleShort(i25);
                    envelope.pointsTick[i24] = i23;
                    envelope.pointsAmpl[i24] = data.uleShort(i25 + 2);
                }
                Envelope envelope2 = new Envelope();
                instrument.panningEnvelope = envelope2;
                envelope2.pointsTick = new int[16];
                envelope2.pointsAmpl = new int[16];
                int i26 = 0;
                for (int i27 = 0; i27 < 12; i27++) {
                    int i28 = uleInt + 177 + (i27 << 2);
                    i26 = (startsWith ? i26 : 0) + data.uleShort(i28);
                    envelope2.pointsTick[i27] = i26;
                    envelope2.pointsAmpl[i27] = data.uleShort(i28 + 2);
                }
                envelope.numPoints = data.uByte(uleInt + User32.VK_OEM_AX);
                if (envelope.numPoints > 12) {
                    envelope.numPoints = 0;
                }
                envelope2.numPoints = data.uByte(uleInt + 226);
                if (envelope2.numPoints > 12) {
                    envelope2.numPoints = 0;
                }
                envelope.sustainTick = envelope.pointsTick[data.uByte(uleInt + User32.VK_ICO_HELP) & 15];
                envelope.loopStartTick = envelope.pointsTick[data.uByte(uleInt + 228) & 15];
                envelope.loopEndTick = envelope.pointsTick[data.uByte(uleInt + User32.VK_PROCESSKEY) & 15];
                envelope2.sustainTick = envelope2.pointsTick[data.uByte(uleInt + 230) & 15];
                envelope2.loopStartTick = envelope2.pointsTick[data.uByte(uleInt + User32.VK_PACKET) & 15];
                envelope2.loopEndTick = envelope2.pointsTick[data.uByte(uleInt + 232) & 15];
                envelope.enabled = envelope.numPoints > 0 && (data.uByte(uleInt + User32.VK_OEM_RESET) & 1) > 0;
                envelope.sustain = (data.uByte(uleInt + User32.VK_OEM_RESET) & 2) > 0;
                envelope.looped = (data.uByte(uleInt + User32.VK_OEM_RESET) & 4) > 0;
                envelope2.enabled = envelope2.numPoints > 0 && (data.uByte(uleInt + User32.VK_OEM_JUMP) & 1) > 0;
                envelope2.sustain = (data.uByte(uleInt + User32.VK_OEM_JUMP) & 2) > 0;
                envelope2.looped = (data.uByte(uleInt + User32.VK_OEM_JUMP) & 4) > 0;
                instrument.vibratoType = data.uByte(uleInt + 235);
                instrument.vibratoSweep = data.uByte(uleInt + 236);
                instrument.vibratoDepth = data.uByte(uleInt + User32.VK_OEM_PA3);
                instrument.vibratoRate = data.uByte(uleInt + User32.VK_OEM_WSCTRL);
                instrument.volumeFadeOut = data.uleShort(uleInt + User32.VK_OEM_CUSEL);
            }
            int uleInt3 = uleInt + data.uleInt(uleInt);
            int i29 = uleInt3;
            uleInt = uleInt3 + (uleShort3 * 40);
            for (int i30 = 0; i30 < uleShort3; i30++) {
                Sample sample = new Sample();
                instrument.samples[i30] = sample;
                int uleInt4 = data.uleInt(i29);
                int uleInt5 = data.uleInt(i29 + 4);
                int uleInt6 = data.uleInt(i29 + 8);
                sample.volume = data.sByte(i29 + 12);
                sample.fineTune = data.sByte(i29 + 13);
                boolean z = (data.uByte(i29 + 14) & 3) > 0;
                boolean z2 = (data.uByte(i29 + 14) & 2) > 0;
                boolean z3 = (data.uByte(i29 + 14) & 16) > 0;
                sample.panning = data.uByte(i29 + 15);
                sample.relNote = data.sByte(i29 + 16);
                sample.name = data.strCp850(i29 + 18, 22);
                i29 += 40;
                if (!z || uleInt5 + uleInt6 > uleInt4) {
                    uleInt5 = uleInt4;
                    uleInt6 = 0;
                }
                if (z3) {
                    sample.setSampleData(data.samS16D(uleInt, uleInt4 >> 1), uleInt5 >> 1, uleInt6 >> 1, z2);
                } else {
                    sample.setSampleData(data.samS8D(uleInt, uleInt4), uleInt5, uleInt6, z2);
                }
                uleInt += uleInt4;
            }
        }
    }

    public void toStringBuffer(StringBuffer stringBuffer) {
        stringBuffer.append("Song Name: " + this.songName + "\nNum Channels: " + this.numChannels + "\nNum Instruments: " + this.numInstruments + "\nNum Patterns: " + this.numPatterns + "\nSequence Length: " + this.sequenceLength + "\nRestart Pos: " + this.restartPos + "\nDefault Speed: " + this.defaultSpeed + "\nDefault Tempo: " + this.defaultTempo + "\nLinear Periods: " + this.linearPeriods + '\n');
        stringBuffer.append("Sequence: ");
        for (int i = 0; i < this.sequence.length; i++) {
            stringBuffer.append(this.sequence[i] + ", ");
        }
        stringBuffer.append('\n');
        for (int i2 = 0; i2 < this.patterns.length; i2++) {
            stringBuffer.append("Pattern " + i2 + ":\n");
            this.patterns[i2].toStringBuffer(stringBuffer);
        }
        for (int i3 = 1; i3 < this.instruments.length; i3++) {
            stringBuffer.append("Instrument " + i3 + ":\n");
            this.instruments[i3].toStringBuffer(stringBuffer);
        }
    }

    @Null
    public static String toZippedBase64(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
        ZipEntry zipEntry = new ZipEntry("module");
        zipEntry.setSize(bArr.length);
        zipOutputStream.putNextEntry(zipEntry);
        zipOutputStream.write(bArr);
        zipOutputStream.close();
        byteArrayOutputStream.close();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String base64 = StringFormatter.toBase64(byteArray, 0, byteArray.length);
        Game.i.musicManager.getModule(base64);
        return base64;
    }

    public float getVolumeMultiplierFromInstrumentNames() {
        if (this.f2207b == null) {
            this.f2207b = Float.valueOf(1.0f);
            for (Instrument instrument : this.instruments) {
                String trim = instrument.name.trim();
                if (trim.length() != 0 && trim.startsWith("[volume:") && trim.endsWith("]")) {
                    try {
                        this.f2207b = Float.valueOf(Integer.parseInt(trim.substring(8, trim.length() - 1)) * 0.01f);
                        break;
                    } catch (Exception unused) {
                    }
                }
            }
        }
        return this.f2207b.floatValue();
    }

    public Array<TrackInfoEntry> getInfoFromInstrumentNames() {
        TrackInfoEntry.EntryType entryType;
        Array<TrackInfoEntry> array = new Array<>(true, 1, TrackInfoEntry.class);
        boolean z = false;
        Instrument[] instrumentArr = this.instruments;
        int length = instrumentArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            String trim = instrumentArr[i].name.trim();
            if (trim.length() == 0 || !trim.startsWith("[") || !trim.endsWith("]")) {
                i++;
            } else {
                z = true;
                break;
            }
        }
        if (z) {
            TrackInfoEntry.EntryType entryType2 = TrackInfoEntry.EntryType.TEXT;
            StringBuilder sb = new StringBuilder();
            for (Instrument instrument : this.instruments) {
                String trim2 = instrument.name.trim();
                if (trim2.length() != 0) {
                    if (trim2.startsWith("[") && trim2.endsWith("]")) {
                        try {
                            entryType = TrackInfoEntry.EntryType.valueOf(trim2.substring(1, trim2.length() - 1).toUpperCase(Locale.US));
                        } catch (Exception unused) {
                            entryType = TrackInfoEntry.EntryType.TEXT;
                        }
                        if (sb.length() != 0) {
                            array.add(new TrackInfoEntry(entryType2, sb.toString()));
                            sb.setLength(0);
                        }
                        entryType2 = entryType;
                    } else {
                        sb.append(trim2);
                    }
                }
            }
            if (sb.length() != 0) {
                array.add(new TrackInfoEntry(entryType2, sb.toString()));
            }
        } else {
            for (Instrument instrument2 : this.instruments) {
                String trim3 = instrument2.name.trim();
                if (trim3.length() != 0) {
                    array.add(new TrackInfoEntry(TrackInfoEntry.EntryType.TEXT, trim3));
                }
            }
        }
        return array;
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ibxm/Module$TrackInfoEntry.class */
    public static final class TrackInfoEntry implements KryoSerializable {
        public EntryType type;
        public String value;

        @REGS
        /* loaded from: infinitode-2.jar:com/prineside/tdi2/ibxm/Module$TrackInfoEntry$EntryType.class */
        public enum EntryType {
            TEXT,
            AUTHOR,
            GROUP,
            LINK;

            public static final EntryType[] values = values();
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public final void write(Kryo kryo, Output output) {
            kryo.writeObject(output, this.type);
            kryo.writeObject(output, this.value);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public final void read(Kryo kryo, Input input) {
            this.type = (EntryType) kryo.readObject(input, EntryType.class);
            this.value = (String) kryo.readObject(input, String.class);
        }

        public TrackInfoEntry() {
        }

        public TrackInfoEntry(EntryType entryType, String str) {
            Preconditions.checkNotNull(entryType, "type");
            Preconditions.checkNotNull(str, "value");
            this.type = entryType;
            this.value = str;
        }

        public final String getCompleteLink() {
            String str;
            String str2 = "https://" + this.value;
            if (this.value.contains(TypeDescription.Generic.OfWildcardType.SYMBOL)) {
                str = str2 + "&";
            } else {
                str = str2 + TypeDescription.Generic.OfWildcardType.SYMBOL;
            }
            return str + "utm_source=Infinitode_2_game";
        }
    }
}
