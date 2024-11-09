package org.lwjgl.stb;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.CustomBuffer;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/stb/STBVorbis.class */
public class STBVorbis {
    public static final int VORBIS__no_error = 0;
    public static final int VORBIS_need_more_data = 1;
    public static final int VORBIS_invalid_api_mixing = 2;
    public static final int VORBIS_outofmem = 3;
    public static final int VORBIS_feature_not_supported = 4;
    public static final int VORBIS_too_many_channels = 5;
    public static final int VORBIS_file_open_failure = 6;
    public static final int VORBIS_seek_without_length = 7;
    public static final int VORBIS_unexpected_eof = 10;
    public static final int VORBIS_seek_invalid = 11;
    public static final int VORBIS_invalid_setup = 20;
    public static final int VORBIS_invalid_stream = 21;
    public static final int VORBIS_missing_capture_pattern = 30;
    public static final int VORBIS_invalid_stream_structure_version = 31;
    public static final int VORBIS_continued_packet_flag_invalid = 32;
    public static final int VORBIS_incorrect_stream_serial_number = 33;
    public static final int VORBIS_invalid_first_page = 34;
    public static final int VORBIS_bad_packet_type = 35;
    public static final int VORBIS_cant_find_last_page = 36;
    public static final int VORBIS_seek_failed = 37;
    public static final int VORBIS_ogg_skeleton_not_supported = 38;

    public static native void nstb_vorbis_get_info(long j, long j2);

    public static native void nstb_vorbis_get_comment(long j, long j2);

    public static native int nstb_vorbis_get_error(long j);

    public static native void nstb_vorbis_close(long j);

    public static native int nstb_vorbis_get_sample_offset(long j);

    public static native int nstb_vorbis_get_file_offset(long j);

    public static native long nstb_vorbis_open_pushdata(long j, int i, long j2, long j3, long j4);

    public static native int nstb_vorbis_decode_frame_pushdata(long j, long j2, int i, long j3, long j4, long j5);

    public static native void nstb_vorbis_flush_pushdata(long j);

    public static native int nstb_vorbis_decode_filename(long j, long j2, long j3, long j4);

    public static native int nstb_vorbis_decode_memory(long j, int i, long j2, long j3, long j4);

    public static native long nstb_vorbis_open_memory(long j, int i, long j2, long j3);

    public static native long nstb_vorbis_open_filename(long j, long j2, long j3);

    public static native int nstb_vorbis_seek_frame(long j, int i);

    public static native int nstb_vorbis_seek(long j, int i);

    public static native int nstb_vorbis_seek_start(long j);

    public static native int nstb_vorbis_stream_length_in_samples(long j);

    public static native float nstb_vorbis_stream_length_in_seconds(long j);

    public static native int nstb_vorbis_get_frame_float(long j, long j2, long j3);

    public static native int nstb_vorbis_get_frame_short(long j, int i, long j2, int i2);

    public static native int nstb_vorbis_get_frame_short_interleaved(long j, int i, long j2, int i2);

    public static native int nstb_vorbis_get_samples_float(long j, int i, long j2, int i2);

    public static native int nstb_vorbis_get_samples_float_interleaved(long j, int i, long j2, int i2);

    public static native int nstb_vorbis_get_samples_short(long j, int i, long j2, int i2);

    public static native int nstb_vorbis_get_samples_short_interleaved(long j, int i, long j2, int i2);

    public static native long nstb_vorbis_open_pushdata(long j, int i, int[] iArr, int[] iArr2, long j2);

    public static native int nstb_vorbis_decode_frame_pushdata(long j, long j2, int i, int[] iArr, long j3, int[] iArr2);

    public static native int nstb_vorbis_decode_filename(long j, int[] iArr, int[] iArr2, long j2);

    public static native int nstb_vorbis_decode_memory(long j, int i, int[] iArr, int[] iArr2, long j2);

    public static native long nstb_vorbis_open_memory(long j, int i, int[] iArr, long j2);

    public static native long nstb_vorbis_open_filename(long j, int[] iArr, long j2);

    public static native int nstb_vorbis_get_frame_float(long j, int[] iArr, long j2);

    public static native int nstb_vorbis_get_frame_short_interleaved(long j, int i, short[] sArr, int i2);

    public static native int nstb_vorbis_get_samples_float_interleaved(long j, int i, float[] fArr, int i2);

    public static native int nstb_vorbis_get_samples_short_interleaved(long j, int i, short[] sArr, int i2);

    static {
        LibSTB.initialize();
    }

    protected STBVorbis() {
        throw new UnsupportedOperationException();
    }

    @NativeType("stb_vorbis_info")
    public static STBVorbisInfo stb_vorbis_get_info(@NativeType("stb_vorbis *") long j, @NativeType("stb_vorbis_info") STBVorbisInfo sTBVorbisInfo) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        nstb_vorbis_get_info(j, sTBVorbisInfo.address());
        return sTBVorbisInfo;
    }

    @NativeType("stb_vorbis_comment")
    public static STBVorbisComment stb_vorbis_get_comment(@NativeType("stb_vorbis *") long j, @NativeType("stb_vorbis_comment") STBVorbisComment sTBVorbisComment) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        nstb_vorbis_get_comment(j, sTBVorbisComment.address());
        return sTBVorbisComment;
    }

    public static int stb_vorbis_get_error(@NativeType("stb_vorbis *") long j) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return nstb_vorbis_get_error(j);
    }

    public static void stb_vorbis_close(@NativeType("stb_vorbis *") long j) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        nstb_vorbis_close(j);
    }

    public static int stb_vorbis_get_sample_offset(@NativeType("stb_vorbis *") long j) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return nstb_vorbis_get_sample_offset(j);
    }

    @NativeType("unsigned int")
    public static int stb_vorbis_get_file_offset(@NativeType("stb_vorbis *") long j) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return nstb_vorbis_get_file_offset(j);
    }

    @NativeType("stb_vorbis *")
    public static long stb_vorbis_open_pushdata(@NativeType("unsigned char const *") ByteBuffer byteBuffer, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2, @NativeType("stb_vorbis_alloc const *") STBVorbisAlloc sTBVorbisAlloc) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
            if (sTBVorbisAlloc != null) {
                STBVorbisAlloc.validate(sTBVorbisAlloc.address());
            }
        }
        return nstb_vorbis_open_pushdata(MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining(), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddressSafe(sTBVorbisAlloc));
    }

    public static int stb_vorbis_decode_frame_pushdata(@NativeType("stb_vorbis *") long j, @NativeType("unsigned char const *") ByteBuffer byteBuffer, @NativeType("int *") IntBuffer intBuffer, @NativeType("float ***") PointerBuffer pointerBuffer, @NativeType("int *") IntBuffer intBuffer2) {
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe((Buffer) intBuffer, 1);
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
        }
        return nstb_vorbis_decode_frame_pushdata(j, MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining(), MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddress(pointerBuffer), MemoryUtil.memAddress(intBuffer2));
    }

    public static void stb_vorbis_flush_pushdata(@NativeType("stb_vorbis *") long j) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        nstb_vorbis_flush_pushdata(j);
    }

    public static int stb_vorbis_decode_filename(@NativeType("char const *") ByteBuffer byteBuffer, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2, @NativeType("short **") PointerBuffer pointerBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
        }
        return nstb_vorbis_decode_filename(MemoryUtil.memAddress(byteBuffer), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(pointerBuffer));
    }

    public static int stb_vorbis_decode_filename(@NativeType("char const *") CharSequence charSequence, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2, @NativeType("short **") PointerBuffer pointerBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            return nstb_vorbis_decode_filename(stackGet.getPointerAddress(), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(pointerBuffer));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("int")
    public static ShortBuffer stb_vorbis_decode_filename(@NativeType("char const *") CharSequence charSequence, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            long pointerAddress = stackGet.getPointerAddress();
            PointerBuffer pointers = stackGet.pointers(0L);
            return MemoryUtil.memShortBufferSafe(pointers.get(0), nstb_vorbis_decode_filename(pointerAddress, MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(pointers)) * intBuffer.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static int stb_vorbis_decode_memory(@NativeType("unsigned char const *") ByteBuffer byteBuffer, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2, @NativeType("short **") PointerBuffer pointerBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
        }
        return nstb_vorbis_decode_memory(MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining(), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(pointerBuffer));
    }

    @NativeType("int")
    public static ShortBuffer stb_vorbis_decode_memory(@NativeType("unsigned char const *") ByteBuffer byteBuffer, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            PointerBuffer pointers = stackGet.pointers(0L);
            return MemoryUtil.memShortBufferSafe(pointers.get(0), nstb_vorbis_decode_memory(MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining(), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(pointers)) * intBuffer.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("stb_vorbis *")
    public static long stb_vorbis_open_memory(@NativeType("unsigned char const *") ByteBuffer byteBuffer, @NativeType("int *") IntBuffer intBuffer, @NativeType("stb_vorbis_alloc const *") STBVorbisAlloc sTBVorbisAlloc) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            if (sTBVorbisAlloc != null) {
                STBVorbisAlloc.validate(sTBVorbisAlloc.address());
            }
        }
        return nstb_vorbis_open_memory(MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining(), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddressSafe(sTBVorbisAlloc));
    }

    @NativeType("stb_vorbis *")
    public static long stb_vorbis_open_filename(@NativeType("char const *") ByteBuffer byteBuffer, @NativeType("int *") IntBuffer intBuffer, @NativeType("stb_vorbis_alloc const *") STBVorbisAlloc sTBVorbisAlloc) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
            Checks.check((Buffer) intBuffer, 1);
            if (sTBVorbisAlloc != null) {
                STBVorbisAlloc.validate(sTBVorbisAlloc.address());
            }
        }
        return nstb_vorbis_open_filename(MemoryUtil.memAddress(byteBuffer), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddressSafe(sTBVorbisAlloc));
    }

    @NativeType("stb_vorbis *")
    public static long stb_vorbis_open_filename(@NativeType("char const *") CharSequence charSequence, @NativeType("int *") IntBuffer intBuffer, @NativeType("stb_vorbis_alloc const *") STBVorbisAlloc sTBVorbisAlloc) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            if (sTBVorbisAlloc != null) {
                STBVorbisAlloc.validate(sTBVorbisAlloc.address());
            }
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            return nstb_vorbis_open_filename(stackGet.getPointerAddress(), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddressSafe(sTBVorbisAlloc));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("int")
    public static boolean stb_vorbis_seek_frame(@NativeType("stb_vorbis *") long j, @NativeType("unsigned int") int i) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return nstb_vorbis_seek_frame(j, i) != 0;
    }

    @NativeType("int")
    public static boolean stb_vorbis_seek(@NativeType("stb_vorbis *") long j, @NativeType("unsigned int") int i) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return nstb_vorbis_seek(j, i) != 0;
    }

    @NativeType("int")
    public static boolean stb_vorbis_seek_start(@NativeType("stb_vorbis *") long j) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return nstb_vorbis_seek_start(j) != 0;
    }

    @NativeType("unsigned int")
    public static int stb_vorbis_stream_length_in_samples(@NativeType("stb_vorbis *") long j) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return nstb_vorbis_stream_length_in_samples(j);
    }

    public static float stb_vorbis_stream_length_in_seconds(@NativeType("stb_vorbis *") long j) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return nstb_vorbis_stream_length_in_seconds(j);
    }

    public static int stb_vorbis_get_frame_float(@NativeType("stb_vorbis *") long j, @NativeType("int *") IntBuffer intBuffer, @NativeType("float ***") PointerBuffer pointerBuffer) {
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe((Buffer) intBuffer, 1);
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
        }
        return nstb_vorbis_get_frame_float(j, MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddress(pointerBuffer));
    }

    public static int stb_vorbis_get_frame_short(@NativeType("stb_vorbis *") long j, @NativeType("short **") PointerBuffer pointerBuffer, int i) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return nstb_vorbis_get_frame_short(j, pointerBuffer.remaining(), MemoryUtil.memAddress(pointerBuffer), i);
    }

    public static int stb_vorbis_get_frame_short_interleaved(@NativeType("stb_vorbis *") long j, int i, @NativeType("short *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return nstb_vorbis_get_frame_short_interleaved(j, i, MemoryUtil.memAddress(shortBuffer), shortBuffer.remaining());
    }

    public static int stb_vorbis_get_samples_float(@NativeType("stb_vorbis *") long j, @NativeType("float **") PointerBuffer pointerBuffer, int i) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return nstb_vorbis_get_samples_float(j, pointerBuffer.remaining(), MemoryUtil.memAddress(pointerBuffer), i);
    }

    public static int stb_vorbis_get_samples_float_interleaved(@NativeType("stb_vorbis *") long j, int i, @NativeType("float *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return nstb_vorbis_get_samples_float_interleaved(j, i, MemoryUtil.memAddress(floatBuffer), floatBuffer.remaining());
    }

    public static int stb_vorbis_get_samples_short(@NativeType("stb_vorbis *") long j, @NativeType("short **") PointerBuffer pointerBuffer, int i) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return nstb_vorbis_get_samples_short(j, pointerBuffer.remaining(), MemoryUtil.memAddress(pointerBuffer), i);
    }

    public static int stb_vorbis_get_samples_short_interleaved(@NativeType("stb_vorbis *") long j, int i, @NativeType("short *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return nstb_vorbis_get_samples_short_interleaved(j, i, MemoryUtil.memAddress(shortBuffer), shortBuffer.remaining());
    }

    @NativeType("stb_vorbis *")
    public static long stb_vorbis_open_pushdata(@NativeType("unsigned char const *") ByteBuffer byteBuffer, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2, @NativeType("stb_vorbis_alloc const *") STBVorbisAlloc sTBVorbisAlloc) {
        if (Checks.CHECKS) {
            Checks.check(iArr, 1);
            Checks.check(iArr2, 1);
            if (sTBVorbisAlloc != null) {
                STBVorbisAlloc.validate(sTBVorbisAlloc.address());
            }
        }
        return nstb_vorbis_open_pushdata(MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining(), iArr, iArr2, MemoryUtil.memAddressSafe(sTBVorbisAlloc));
    }

    public static int stb_vorbis_decode_frame_pushdata(@NativeType("stb_vorbis *") long j, @NativeType("unsigned char const *") ByteBuffer byteBuffer, @NativeType("int *") int[] iArr, @NativeType("float ***") PointerBuffer pointerBuffer, @NativeType("int *") int[] iArr2) {
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr, 1);
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
            Checks.check(iArr2, 1);
        }
        return nstb_vorbis_decode_frame_pushdata(j, MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining(), iArr, MemoryUtil.memAddress(pointerBuffer), iArr2);
    }

    public static int stb_vorbis_decode_filename(@NativeType("char const *") ByteBuffer byteBuffer, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2, @NativeType("short **") PointerBuffer pointerBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
            Checks.check(iArr, 1);
            Checks.check(iArr2, 1);
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
        }
        return nstb_vorbis_decode_filename(MemoryUtil.memAddress(byteBuffer), iArr, iArr2, MemoryUtil.memAddress(pointerBuffer));
    }

    public static int stb_vorbis_decode_filename(@NativeType("char const *") CharSequence charSequence, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2, @NativeType("short **") PointerBuffer pointerBuffer) {
        if (Checks.CHECKS) {
            Checks.check(iArr, 1);
            Checks.check(iArr2, 1);
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            return nstb_vorbis_decode_filename(stackGet.getPointerAddress(), iArr, iArr2, MemoryUtil.memAddress(pointerBuffer));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static int stb_vorbis_decode_memory(@NativeType("unsigned char const *") ByteBuffer byteBuffer, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2, @NativeType("short **") PointerBuffer pointerBuffer) {
        if (Checks.CHECKS) {
            Checks.check(iArr, 1);
            Checks.check(iArr2, 1);
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
        }
        return nstb_vorbis_decode_memory(MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining(), iArr, iArr2, MemoryUtil.memAddress(pointerBuffer));
    }

    @NativeType("stb_vorbis *")
    public static long stb_vorbis_open_memory(@NativeType("unsigned char const *") ByteBuffer byteBuffer, @NativeType("int *") int[] iArr, @NativeType("stb_vorbis_alloc const *") STBVorbisAlloc sTBVorbisAlloc) {
        if (Checks.CHECKS) {
            Checks.check(iArr, 1);
            if (sTBVorbisAlloc != null) {
                STBVorbisAlloc.validate(sTBVorbisAlloc.address());
            }
        }
        return nstb_vorbis_open_memory(MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining(), iArr, MemoryUtil.memAddressSafe(sTBVorbisAlloc));
    }

    @NativeType("stb_vorbis *")
    public static long stb_vorbis_open_filename(@NativeType("char const *") ByteBuffer byteBuffer, @NativeType("int *") int[] iArr, @NativeType("stb_vorbis_alloc const *") STBVorbisAlloc sTBVorbisAlloc) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
            Checks.check(iArr, 1);
            if (sTBVorbisAlloc != null) {
                STBVorbisAlloc.validate(sTBVorbisAlloc.address());
            }
        }
        return nstb_vorbis_open_filename(MemoryUtil.memAddress(byteBuffer), iArr, MemoryUtil.memAddressSafe(sTBVorbisAlloc));
    }

    @NativeType("stb_vorbis *")
    public static long stb_vorbis_open_filename(@NativeType("char const *") CharSequence charSequence, @NativeType("int *") int[] iArr, @NativeType("stb_vorbis_alloc const *") STBVorbisAlloc sTBVorbisAlloc) {
        if (Checks.CHECKS) {
            Checks.check(iArr, 1);
            if (sTBVorbisAlloc != null) {
                STBVorbisAlloc.validate(sTBVorbisAlloc.address());
            }
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            return nstb_vorbis_open_filename(stackGet.getPointerAddress(), iArr, MemoryUtil.memAddressSafe(sTBVorbisAlloc));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static int stb_vorbis_get_frame_float(@NativeType("stb_vorbis *") long j, @NativeType("int *") int[] iArr, @NativeType("float ***") PointerBuffer pointerBuffer) {
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr, 1);
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
        }
        return nstb_vorbis_get_frame_float(j, iArr, MemoryUtil.memAddress(pointerBuffer));
    }

    public static int stb_vorbis_get_frame_short_interleaved(@NativeType("stb_vorbis *") long j, int i, @NativeType("short *") short[] sArr) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return nstb_vorbis_get_frame_short_interleaved(j, i, sArr, sArr.length);
    }

    public static int stb_vorbis_get_samples_float_interleaved(@NativeType("stb_vorbis *") long j, int i, @NativeType("float *") float[] fArr) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return nstb_vorbis_get_samples_float_interleaved(j, i, fArr, fArr.length);
    }

    public static int stb_vorbis_get_samples_short_interleaved(@NativeType("stb_vorbis *") long j, int i, @NativeType("short *") short[] sArr) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return nstb_vorbis_get_samples_short_interleaved(j, i, sArr, sArr.length);
    }
}
