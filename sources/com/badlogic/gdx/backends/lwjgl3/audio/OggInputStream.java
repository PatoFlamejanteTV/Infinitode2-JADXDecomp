package com.badlogic.gdx.backends.lwjgl3.audio;

import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.StreamUtils;
import com.c.a.b;
import com.c.a.c;
import com.c.a.d;
import com.c.a.e;
import com.c.b.a;
import com.c.b.l;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.lwjgl.BufferUtils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/lwjgl3/audio/OggInputStream.class */
public class OggInputStream extends InputStream {
    private static final int BUFFER_SIZE = 512;
    private int convsize;
    private byte[] convbuffer;
    private InputStream input;
    private l oggInfo;
    private boolean endOfStream;
    private e syncState;
    private d streamState;
    private c page;
    private b packet;
    private com.c.b.c comment;
    private com.c.b.e dspState;
    private a vorbisBlock;
    byte[] buffer;
    int bytes;
    boolean bigEndian;
    boolean endOfBitStream;
    boolean inited;
    private int readIndex;
    private ByteBuffer pcmBuffer;
    private int total;

    public OggInputStream(InputStream inputStream) {
        this(inputStream, null);
    }

    public OggInputStream(InputStream inputStream, OggInputStream oggInputStream) {
        this.convsize = 2048;
        this.oggInfo = new l();
        this.syncState = new e();
        this.streamState = new d();
        this.page = new c();
        this.packet = new b();
        this.comment = new com.c.b.c();
        this.dspState = new com.c.b.e();
        this.vorbisBlock = new a(this.dspState);
        this.bytes = 0;
        this.bigEndian = ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN);
        this.endOfBitStream = true;
        this.inited = false;
        if (oggInputStream == null) {
            this.convbuffer = new byte[this.convsize];
            this.pcmBuffer = BufferUtils.createByteBuffer(2048000);
        } else {
            this.convbuffer = oggInputStream.convbuffer;
            this.pcmBuffer = oggInputStream.pcmBuffer;
        }
        this.input = inputStream;
        try {
            this.total = inputStream.available();
            init();
        } catch (IOException e) {
            throw new GdxRuntimeException(e);
        }
    }

    public int getLength() {
        return this.total;
    }

    public int getChannels() {
        return this.oggInfo.f929a;
    }

    public int getSampleRate() {
        return this.oggInfo.f930b;
    }

    private void init() {
        initVorbis();
        readPCM();
    }

    @Override // java.io.InputStream
    public int available() {
        return this.endOfStream ? 0 : 1;
    }

    private void initVorbis() {
    }

    private boolean getPageAndPacket() {
        int a2;
        int a3;
        int a4 = this.syncState.a(512);
        if (a4 == -1) {
            return false;
        }
        this.buffer = this.syncState.f902a;
        if (this.buffer == null) {
            this.endOfStream = true;
            return false;
        }
        try {
            this.bytes = this.input.read(this.buffer, a4, 512);
            this.syncState.b(this.bytes);
            if (this.syncState.a(this.page) != 1) {
                if (this.bytes < 512) {
                    return false;
                }
                throw new GdxRuntimeException("Input does not appear to be an Ogg bitstream.");
            }
            this.streamState.a(this.page.f());
            this.oggInfo.a();
            this.comment.a();
            if (this.streamState.a(this.page) < 0) {
                throw new GdxRuntimeException("Error reading first page of Ogg bitstream.");
            }
            if (this.streamState.a(this.packet) != 1) {
                throw new GdxRuntimeException("Error reading initial header packet.");
            }
            if (this.oggInfo.a(this.comment, this.packet) < 0) {
                throw new GdxRuntimeException("Ogg bitstream does not contain Vorbis audio data.");
            }
            int i = 0;
            while (i < 2) {
                while (i < 2 && (a2 = this.syncState.a(this.page)) != 0) {
                    if (a2 == 1) {
                        this.streamState.a(this.page);
                        while (i < 2 && (a3 = this.streamState.a(this.packet)) != 0) {
                            if (a3 == -1) {
                                throw new GdxRuntimeException("Corrupt secondary header.");
                            }
                            this.oggInfo.a(this.comment, this.packet);
                            i++;
                        }
                    }
                }
                int a5 = this.syncState.a(512);
                if (a5 == -1) {
                    return false;
                }
                this.buffer = this.syncState.f902a;
                try {
                    this.bytes = this.input.read(this.buffer, a5, 512);
                    if (this.bytes == 0 && i < 2) {
                        throw new GdxRuntimeException("End of file before finding all Vorbis headers.");
                    }
                    this.syncState.b(this.bytes);
                } catch (Exception e) {
                    throw new GdxRuntimeException("Failed to read Vorbis.", e);
                }
            }
            this.convsize = 512 / this.oggInfo.f929a;
            this.dspState.a(this.oggInfo);
            this.vorbisBlock.a(this.dspState);
            return true;
        } catch (Exception e2) {
            throw new GdxRuntimeException("Failure reading Vorbis.", e2);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:70:0x0071, code lost:            continue;     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v7, types: [float[][], float[][][]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void readPCM() {
        /*
            Method dump skipped, instructions count: 654
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.badlogic.gdx.backends.lwjgl3.audio.OggInputStream.readPCM():void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10, types: [int] */
    @Override // java.io.InputStream
    public int read() {
        if (this.readIndex >= this.pcmBuffer.position()) {
            this.pcmBuffer.clear();
            readPCM();
            this.readIndex = 0;
        }
        if (this.readIndex >= this.pcmBuffer.position()) {
            return -1;
        }
        byte b2 = this.pcmBuffer.get(this.readIndex);
        byte b3 = b2;
        if (b2 < 0) {
            b3 += 256;
        }
        this.readIndex++;
        return b3;
    }

    public boolean atEnd() {
        return this.endOfStream && this.readIndex >= this.pcmBuffer.position();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            int read = read();
            if (read >= 0) {
                bArr[i3] = (byte) read;
            } else {
                if (i3 == 0) {
                    return -1;
                }
                return i3;
            }
        }
        return i2;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        StreamUtils.closeQuietly(this.input);
    }
}
