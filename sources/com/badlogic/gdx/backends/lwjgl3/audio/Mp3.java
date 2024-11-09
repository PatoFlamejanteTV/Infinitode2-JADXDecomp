package com.badlogic.gdx.backends.lwjgl3.audio;

import b.a.a.b;
import b.a.a.c;
import b.a.a.g;
import b.a.a.l;
import b.a.a.m;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.ByteArrayOutputStream;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/lwjgl3/audio/Mp3.class */
public class Mp3 {

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/lwjgl3/audio/Mp3$Music.class */
    public static class Music extends OpenALMusic {
        private b bitstream;
        private m outputBuffer;
        private l decoder;

        public Music(OpenALLwjgl3Audio openALLwjgl3Audio, FileHandle fileHandle) {
            super(openALLwjgl3Audio, fileHandle);
            if (openALLwjgl3Audio.noDevice) {
                return;
            }
            this.bitstream = new b(fileHandle.read());
            this.decoder = new l();
            try {
                g b2 = this.bitstream.b();
                if (b2 == null) {
                    throw new GdxRuntimeException("Empty MP3");
                }
                int i = b2.f() == 3 ? 1 : 2;
                this.outputBuffer = new m(i, false);
                this.decoder.a(this.outputBuffer);
                setup(i, 16, b2.j());
            } catch (c e) {
                throw new GdxRuntimeException("Error while preloading MP3", e);
            }
        }

        @Override // com.badlogic.gdx.backends.lwjgl3.audio.OpenALMusic
        public int read(byte[] bArr) {
            try {
                boolean z = this.bitstream == null;
                boolean z2 = z;
                if (z) {
                    this.bitstream = new b(this.file.read());
                    this.decoder = new l();
                }
                int i = 0;
                int length = bArr.length - 4608;
                while (i <= length) {
                    g b2 = this.bitstream.b();
                    if (b2 == null) {
                        break;
                    }
                    if (z2) {
                        int i2 = b2.f() == 3 ? 1 : 2;
                        this.outputBuffer = new m(i2, false);
                        this.decoder.a(this.outputBuffer);
                        setup(i2, 16, b2.j());
                        z2 = false;
                    }
                    try {
                        this.decoder.a(b2, this.bitstream);
                    } catch (Exception unused) {
                    }
                    this.bitstream.d();
                    int b3 = this.outputBuffer.b();
                    System.arraycopy(this.outputBuffer.a(), 0, bArr, i, b3);
                    i += b3;
                }
                return i;
            } catch (Throwable th) {
                reset();
                throw new GdxRuntimeException("Error reading audio data.", th);
            }
        }

        @Override // com.badlogic.gdx.backends.lwjgl3.audio.OpenALMusic
        public void reset() {
            if (this.bitstream == null) {
                return;
            }
            try {
                this.bitstream.a();
            } catch (c unused) {
            }
            this.bitstream = null;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/lwjgl3/audio/Mp3$Sound.class */
    public static class Sound extends OpenALSound {
        public Sound(OpenALLwjgl3Audio openALLwjgl3Audio, FileHandle fileHandle) {
            super(openALLwjgl3Audio);
            if (openALLwjgl3Audio.noDevice) {
                return;
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4096);
            b bVar = new b(fileHandle.read());
            l lVar = new l();
            m mVar = null;
            int i = -1;
            int i2 = -1;
            while (true) {
                try {
                    g b2 = bVar.b();
                    if (b2 != null) {
                        if (mVar == null) {
                            i2 = b2.f() == 3 ? 1 : 2;
                            mVar = new m(i2, false);
                            lVar.a(mVar);
                            i = b2.j();
                        }
                        try {
                            lVar.a(b2, bVar);
                        } catch (Exception unused) {
                        }
                        bVar.d();
                        byteArrayOutputStream.write(mVar.a(), 0, mVar.b());
                    } else {
                        bVar.a();
                        setup(byteArrayOutputStream.toByteArray(), i2, 16, i);
                        return;
                    }
                } catch (Throwable th) {
                    throw new GdxRuntimeException("Error reading audio data.", th);
                }
            }
        }
    }
}
