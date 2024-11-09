package com.prineside.tdi2.managers.preferences;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.ByteArray;
import com.badlogic.gdx.utils.Null;
import com.prineside.kryo.FixedInput;
import com.prineside.kryo.FixedOutput;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.utils.ObjectPair;
import java.io.ByteArrayOutputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/preferences/RegularPrefMap.class */
public final class RegularPrefMap implements PrefMap {
    public static final int BYTE_FORMAT_HEADER = 1988092089;
    public static final byte TYPE_PROGRESS = 1;
    public static final byte TYPE_SETTINGS = 2;
    public static final byte CURRENT_VERSION = 1;

    /* renamed from: b, reason: collision with root package name */
    private final byte f2515b;

    /* renamed from: a, reason: collision with root package name */
    private final ConcurrentHashMap<String, String> f2514a = new ConcurrentHashMap<>();
    private final Deflater c = new Deflater(1, true);
    private final Inflater d = new Inflater(true);
    private final ByteArrayOutputStream e = new ByteArrayOutputStream();
    private final byte[] f = new byte[2048];
    private final FixedInput g = new FixedInput();
    private final FixedOutput h = new FixedOutput(1024, -1);
    private byte[] i = new byte[8];
    private final ByteArray j = new ByteArray(8);

    public RegularPrefMap(byte b2) {
        if (b2 != 1 && b2 != 2) {
            throw new IllegalArgumentException("Invalid type: " + ((int) b2));
        }
        this.f2515b = b2;
    }

    @Override // com.prineside.tdi2.managers.preferences.PrefMap
    public final String get(String str, String str2) {
        String str3 = this.f2514a.get(str);
        if (str3 == null) {
            return str2;
        }
        return str3;
    }

    @Null
    public final String get(String str) {
        return this.f2514a.get(str);
    }

    @Override // com.prineside.tdi2.managers.preferences.PrefMap
    public final void set(String str, String str2) {
        if (str2 == null) {
            this.f2514a.remove(str);
        } else {
            this.f2514a.put(str, str2);
        }
    }

    public final void clear() {
        this.f2514a.clear();
    }

    public final ConcurrentHashMap<String, String> getMap() {
        return this.f2514a;
    }

    public final ObjectPair<String, String>[] toOrderedKeyValuePairs() {
        Array array = new Array(true, this.f2514a.size(), ObjectPair.class);
        for (Map.Entry<String, String> entry : this.f2514a.entrySet()) {
            array.add(new ObjectPair(entry.getKey(), entry.getValue()));
        }
        array.sort((objectPair, objectPair2) -> {
            return ((String) objectPair.first).compareTo((String) objectPair2.first);
        });
        return (ObjectPair[]) array.toArray();
    }

    public static BinarySaveInfo getBinarySaveInfo(byte[] bArr, int i, int i2) {
        int inflate;
        BinarySaveInfo binarySaveInfo = new BinarySaveInfo();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Inflater inflater = new Inflater(true);
        byte[] bArr2 = new byte[64];
        FixedInput fixedInput = new FixedInput();
        try {
            int i3 = 0;
            byteArrayOutputStream.reset();
            inflater.setInput(bArr, i, i2);
            while (!inflater.finished() && (inflate = inflater.inflate(bArr2)) != 0) {
                byteArrayOutputStream.write(bArr2, 0, inflate);
                int i4 = i3 + inflate;
                i3 = i4;
                if (i4 >= 64) {
                    break;
                }
            }
            fixedInput.setBuffer(byteArrayOutputStream.toByteArray());
            binarySaveInfo.canBeInflated = true;
            if (fixedInput.readInt() == 1988092089) {
                binarySaveInfo.version = fixedInput.readByte();
                if (binarySaveInfo.version >= 0 && binarySaveInfo.version <= 1) {
                    binarySaveInfo.type = fixedInput.readByte();
                    if (binarySaveInfo.type == 1 || binarySaveInfo.type == 2) {
                        binarySaveInfo.saveTimestamp = fixedInput.readLong();
                        binarySaveInfo.valid = binarySaveInfo.saveTimestamp > 0;
                    } else {
                        binarySaveInfo.type = (byte) -1;
                    }
                } else {
                    binarySaveInfo.version = (byte) -1;
                }
            } else {
                binarySaveInfo.valid = false;
            }
            return binarySaveInfo;
        } catch (DataFormatException unused) {
            binarySaveInfo.valid = false;
            binarySaveInfo.canBeInflated = false;
            return binarySaveInfo;
        }
    }

    public final void fromBytes(byte[] bArr, int i, int i2) {
        int inflate;
        try {
            this.e.reset();
            this.d.setInput(bArr, i, i2);
            while (!this.d.finished() && (inflate = this.d.inflate(this.f)) != 0) {
                this.e.write(this.f, 0, inflate);
            }
            this.d.reset();
            this.g.setBuffer(this.e.toByteArray());
            int readInt = this.g.readInt();
            if (readInt == 1988092089) {
                this.g.readByte();
                byte readByte = this.g.readByte();
                if (readByte != this.f2515b) {
                    throw new IllegalArgumentException("Invalid type of preferences: " + ((int) readByte) + ", expecting " + ((int) this.f2515b));
                }
                this.g.readLong();
                int readInt2 = this.g.readInt();
                for (int i3 = 0; i3 < readInt2; i3++) {
                    set(this.g.readString(), this.g.readString());
                }
                return;
            }
            throw new IllegalArgumentException("Unrecognized header: " + Integer.toHexString(readInt));
        } catch (DataFormatException e) {
            throw new IllegalArgumentException("Failed to read deflated bytes", e);
        }
    }

    public final void fromBase64(String str) {
        byte[] decode = Base64Coder.decode(str);
        fromBytes(decode, 0, decode.length);
    }

    public final ByteArray toBytes() {
        this.h.clear();
        this.h.writeInt(BYTE_FORMAT_HEADER);
        this.h.writeByte(1);
        this.h.writeByte(this.f2515b);
        this.h.writeLong(Game.getTimestampMillis());
        this.h.writeInt(this.f2514a.size());
        for (Map.Entry<String, String> entry : this.f2514a.entrySet()) {
            this.h.writeString(entry.getKey());
            this.h.writeString(entry.getValue());
        }
        if (this.i.length < this.h.position()) {
            this.i = new byte[MathUtils.nextPowerOfTwo(this.h.position())];
        }
        this.c.setInput(this.h.getBuffer(), 0, this.h.position());
        this.c.finish();
        int deflate = this.c.deflate(this.i);
        this.c.reset();
        this.j.clear();
        this.j.addAll(this.i, 0, deflate);
        return this.j;
    }

    public final String toBase64() {
        ByteArray bytes = toBytes();
        return new String(Base64Coder.encode(bytes.items, bytes.size));
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/managers/preferences/RegularPrefMap$BinarySaveInfo.class */
    public static final class BinarySaveInfo {
        public boolean valid;
        public boolean canBeInflated;
        public byte type = -1;
        public byte version = -1;
        public long saveTimestamp = -1;

        public final String toString() {
            return "BinarySaveInfo (valid: " + this.valid + ", canBeInflated: " + this.canBeInflated + ", type: " + ((int) this.type) + ", version: " + ((int) this.version) + ", save timestamp: " + this.saveTimestamp + ")";
        }
    }
}
