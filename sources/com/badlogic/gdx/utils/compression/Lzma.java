package com.badlogic.gdx.utils.compression;

import com.badlogic.gdx.utils.compression.lzma.Decoder;
import com.badlogic.gdx.utils.compression.lzma.Encoder;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/compression/Lzma.class */
public class Lzma {

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/compression/Lzma$CommandLine.class */
    static class CommandLine {
        public static final int kEncode = 0;
        public static final int kDecode = 1;
        public static final int kBenchmak = 2;
        public int Command = -1;
        public int NumBenchmarkPasses = 10;
        public int DictionarySize = 8388608;
        public boolean DictionarySizeIsDefined = false;
        public int Lc = 3;
        public int Lp = 0;
        public int Pb = 2;
        public int Fb = 128;
        public boolean FbIsDefined = false;
        public boolean Eos = false;
        public int Algorithm = 2;
        public int MatchFinder = 1;
        public String InFile;
        public String OutFile;

        CommandLine() {
        }
    }

    public static void compress(InputStream inputStream, OutputStream outputStream) {
        long j;
        CommandLine commandLine = new CommandLine();
        boolean z = commandLine.Eos;
        Encoder encoder = new Encoder();
        if (!encoder.SetAlgorithm(commandLine.Algorithm)) {
            throw new RuntimeException("Incorrect compression mode");
        }
        if (!encoder.SetDictionarySize(commandLine.DictionarySize)) {
            throw new RuntimeException("Incorrect dictionary size");
        }
        if (!encoder.SetNumFastBytes(commandLine.Fb)) {
            throw new RuntimeException("Incorrect -fb value");
        }
        if (!encoder.SetMatchFinder(commandLine.MatchFinder)) {
            throw new RuntimeException("Incorrect -mf value");
        }
        if (!encoder.SetLcLpPb(commandLine.Lc, commandLine.Lp, commandLine.Pb)) {
            throw new RuntimeException("Incorrect -lc or -lp or -pb value");
        }
        encoder.SetEndMarkerMode(z);
        encoder.WriteCoderProperties(outputStream);
        if (z) {
            j = -1;
        } else {
            long available = inputStream.available();
            j = available;
            if (available == 0) {
                j = -1;
            }
        }
        for (int i = 0; i < 8; i++) {
            outputStream.write(((int) (j >>> (i * 8))) & 255);
        }
        encoder.Code(inputStream, outputStream, -1L, -1L, null);
    }

    public static void decompress(InputStream inputStream, OutputStream outputStream) {
        byte[] bArr = new byte[5];
        if (inputStream.read(bArr, 0, 5) != 5) {
            throw new RuntimeException("input .lzma file is too short");
        }
        Decoder decoder = new Decoder();
        if (!decoder.SetDecoderProperties(bArr)) {
            throw new RuntimeException("Incorrect stream properties");
        }
        long j = 0;
        for (int i = 0; i < 8; i++) {
            int read = inputStream.read();
            if (read >= 0) {
                j |= read << (i * 8);
            } else {
                throw new RuntimeException("Can't read stream size");
            }
        }
        if (!decoder.Code(inputStream, outputStream, j)) {
            throw new RuntimeException("Error in data stream");
        }
    }
}
