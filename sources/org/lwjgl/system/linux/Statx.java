package org.lwjgl.system.linux;

import java.nio.ByteBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import java.util.function.Consumer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.Checks;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct statx")
/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/Statx.class */
public class Statx extends Struct<Statx> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int STX_MASK;
    public static final int STX_BLKSIZE;
    public static final int STX_ATTRIBUTES;
    public static final int STX_NLINK;
    public static final int STX_UID;
    public static final int STX_GID;
    public static final int STX_MODE;
    public static final int __SPARE0;
    public static final int STX_INO;
    public static final int STX_SIZE;
    public static final int STX_BLOCKS;
    public static final int STX_ATTRIBUTES_MASK;
    public static final int STX_ATIME;
    public static final int STX_BTIME;
    public static final int STX_CTIME;
    public static final int STX_MTIME;
    public static final int STX_RDEV_MAJOR;
    public static final int STX_RDEV_MINOR;
    public static final int STX_DEV_MAJOR;
    public static final int STX_DEV_MINOR;
    public static final int STX_MNT_ID;
    public static final int __SPARE2;
    public static final int __SPARE3;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(4), __member(8), __member(4), __member(4), __member(4), __member(2), __array(2, 1), __member(8), __member(8), __member(8), __member(8), __member(StatxTimestamp.SIZEOF, StatxTimestamp.ALIGNOF), __member(StatxTimestamp.SIZEOF, StatxTimestamp.ALIGNOF), __member(StatxTimestamp.SIZEOF, StatxTimestamp.ALIGNOF), __member(StatxTimestamp.SIZEOF, StatxTimestamp.ALIGNOF), __member(4), __member(4), __member(4), __member(4), __member(8), __member(8), __array(8, 12));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        STX_MASK = __struct.offsetof(0);
        STX_BLKSIZE = __struct.offsetof(1);
        STX_ATTRIBUTES = __struct.offsetof(2);
        STX_NLINK = __struct.offsetof(3);
        STX_UID = __struct.offsetof(4);
        STX_GID = __struct.offsetof(5);
        STX_MODE = __struct.offsetof(6);
        __SPARE0 = __struct.offsetof(7);
        STX_INO = __struct.offsetof(8);
        STX_SIZE = __struct.offsetof(9);
        STX_BLOCKS = __struct.offsetof(10);
        STX_ATTRIBUTES_MASK = __struct.offsetof(11);
        STX_ATIME = __struct.offsetof(12);
        STX_BTIME = __struct.offsetof(13);
        STX_CTIME = __struct.offsetof(14);
        STX_MTIME = __struct.offsetof(15);
        STX_RDEV_MAJOR = __struct.offsetof(16);
        STX_RDEV_MINOR = __struct.offsetof(17);
        STX_DEV_MAJOR = __struct.offsetof(18);
        STX_DEV_MINOR = __struct.offsetof(19);
        STX_MNT_ID = __struct.offsetof(20);
        __SPARE2 = __struct.offsetof(21);
        __SPARE3 = __struct.offsetof(22);
    }

    protected Statx(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public Statx create(long j, ByteBuffer byteBuffer) {
        return new Statx(j, byteBuffer);
    }

    public Statx(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("__u32")
    public int stx_mask() {
        return nstx_mask(address());
    }

    @NativeType("__u32")
    public int stx_blksize() {
        return nstx_blksize(address());
    }

    @NativeType("__u64")
    public long stx_attributes() {
        return nstx_attributes(address());
    }

    @NativeType("__u32")
    public int stx_nlink() {
        return nstx_nlink(address());
    }

    @NativeType("__u32")
    public int stx_uid() {
        return nstx_uid(address());
    }

    @NativeType("__u32")
    public int stx_gid() {
        return nstx_gid(address());
    }

    @NativeType("__u16")
    public short stx_mode() {
        return nstx_mode(address());
    }

    @NativeType("__u64")
    public long stx_ino() {
        return nstx_ino(address());
    }

    @NativeType("__u64")
    public long stx_size() {
        return nstx_size(address());
    }

    @NativeType("__u64")
    public long stx_blocks() {
        return nstx_blocks(address());
    }

    @NativeType("__u64")
    public long stx_attributes_mask() {
        return nstx_attributes_mask(address());
    }

    @NativeType("struct statx_timestamp")
    public StatxTimestamp stx_atime() {
        return nstx_atime(address());
    }

    @NativeType("struct statx_timestamp")
    public StatxTimestamp stx_btime() {
        return nstx_btime(address());
    }

    @NativeType("struct statx_timestamp")
    public StatxTimestamp stx_ctime() {
        return nstx_ctime(address());
    }

    @NativeType("struct statx_timestamp")
    public StatxTimestamp stx_mtime() {
        return nstx_mtime(address());
    }

    @NativeType("__u32")
    public int stx_rdev_major() {
        return nstx_rdev_major(address());
    }

    @NativeType("__u32")
    public int stx_rdev_minor() {
        return nstx_rdev_minor(address());
    }

    @NativeType("__u32")
    public int stx_dev_major() {
        return nstx_dev_major(address());
    }

    @NativeType("__u32")
    public int stx_dev_minor() {
        return nstx_dev_minor(address());
    }

    @NativeType("__u64")
    public long stx_mnt_id() {
        return nstx_mnt_id(address());
    }

    public Statx stx_mask(@NativeType("__u32") int i) {
        nstx_mask(address(), i);
        return this;
    }

    public Statx stx_blksize(@NativeType("__u32") int i) {
        nstx_blksize(address(), i);
        return this;
    }

    public Statx stx_attributes(@NativeType("__u64") long j) {
        nstx_attributes(address(), j);
        return this;
    }

    public Statx stx_nlink(@NativeType("__u32") int i) {
        nstx_nlink(address(), i);
        return this;
    }

    public Statx stx_uid(@NativeType("__u32") int i) {
        nstx_uid(address(), i);
        return this;
    }

    public Statx stx_gid(@NativeType("__u32") int i) {
        nstx_gid(address(), i);
        return this;
    }

    public Statx stx_mode(@NativeType("__u16") short s) {
        nstx_mode(address(), s);
        return this;
    }

    public Statx stx_ino(@NativeType("__u64") long j) {
        nstx_ino(address(), j);
        return this;
    }

    public Statx stx_size(@NativeType("__u64") long j) {
        nstx_size(address(), j);
        return this;
    }

    public Statx stx_blocks(@NativeType("__u64") long j) {
        nstx_blocks(address(), j);
        return this;
    }

    public Statx stx_attributes_mask(@NativeType("__u64") long j) {
        nstx_attributes_mask(address(), j);
        return this;
    }

    public Statx stx_atime(@NativeType("struct statx_timestamp") StatxTimestamp statxTimestamp) {
        nstx_atime(address(), statxTimestamp);
        return this;
    }

    public Statx stx_atime(Consumer<StatxTimestamp> consumer) {
        consumer.accept(stx_atime());
        return this;
    }

    public Statx stx_btime(@NativeType("struct statx_timestamp") StatxTimestamp statxTimestamp) {
        nstx_btime(address(), statxTimestamp);
        return this;
    }

    public Statx stx_btime(Consumer<StatxTimestamp> consumer) {
        consumer.accept(stx_btime());
        return this;
    }

    public Statx stx_ctime(@NativeType("struct statx_timestamp") StatxTimestamp statxTimestamp) {
        nstx_ctime(address(), statxTimestamp);
        return this;
    }

    public Statx stx_ctime(Consumer<StatxTimestamp> consumer) {
        consumer.accept(stx_ctime());
        return this;
    }

    public Statx stx_mtime(@NativeType("struct statx_timestamp") StatxTimestamp statxTimestamp) {
        nstx_mtime(address(), statxTimestamp);
        return this;
    }

    public Statx stx_mtime(Consumer<StatxTimestamp> consumer) {
        consumer.accept(stx_mtime());
        return this;
    }

    public Statx stx_rdev_major(@NativeType("__u32") int i) {
        nstx_rdev_major(address(), i);
        return this;
    }

    public Statx stx_rdev_minor(@NativeType("__u32") int i) {
        nstx_rdev_minor(address(), i);
        return this;
    }

    public Statx stx_dev_major(@NativeType("__u32") int i) {
        nstx_dev_major(address(), i);
        return this;
    }

    public Statx stx_dev_minor(@NativeType("__u32") int i) {
        nstx_dev_minor(address(), i);
        return this;
    }

    public Statx stx_mnt_id(@NativeType("__u64") long j) {
        nstx_mnt_id(address(), j);
        return this;
    }

    public Statx set(int i, int i2, long j, int i3, int i4, int i5, short s, long j2, long j3, long j4, long j5, StatxTimestamp statxTimestamp, StatxTimestamp statxTimestamp2, StatxTimestamp statxTimestamp3, StatxTimestamp statxTimestamp4, int i6, int i7, int i8, int i9, long j6) {
        stx_mask(i);
        stx_blksize(i2);
        stx_attributes(j);
        stx_nlink(i3);
        stx_uid(i4);
        stx_gid(i5);
        stx_mode(s);
        stx_ino(j2);
        stx_size(j3);
        stx_blocks(j4);
        stx_attributes_mask(j5);
        stx_atime(statxTimestamp);
        stx_btime(statxTimestamp2);
        stx_ctime(statxTimestamp3);
        stx_mtime(statxTimestamp4);
        stx_rdev_major(i6);
        stx_rdev_minor(i7);
        stx_dev_major(i8);
        stx_dev_minor(i9);
        stx_mnt_id(j6);
        return this;
    }

    public Statx set(Statx statx) {
        MemoryUtil.memCopy(statx.address(), address(), SIZEOF);
        return this;
    }

    public static Statx malloc() {
        return new Statx(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static Statx calloc() {
        return new Statx(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static Statx create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new Statx(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static Statx create(long j) {
        return new Statx(j, null);
    }

    public static Statx createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new Statx(j, null);
    }

    public static Buffer malloc(int i) {
        return new Buffer(MemoryUtil.nmemAllocChecked(__checkMalloc(i, SIZEOF)), i);
    }

    public static Buffer calloc(int i) {
        return new Buffer(MemoryUtil.nmemCallocChecked(i, SIZEOF), i);
    }

    public static Buffer create(int i) {
        ByteBuffer __create = __create(i, SIZEOF);
        return new Buffer(MemoryUtil.memAddress(__create), __create, -1, 0, i, i);
    }

    public static Buffer create(long j, int i) {
        return new Buffer(j, i);
    }

    public static Buffer createSafe(long j, int i) {
        if (j == 0) {
            return null;
        }
        return new Buffer(j, i);
    }

    public static Statx malloc(MemoryStack memoryStack) {
        return new Statx(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static Statx calloc(MemoryStack memoryStack) {
        return new Statx(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static int nstx_mask(long j) {
        return UNSAFE.getInt((Object) null, j + STX_MASK);
    }

    public static int nstx_blksize(long j) {
        return UNSAFE.getInt((Object) null, j + STX_BLKSIZE);
    }

    public static long nstx_attributes(long j) {
        return UNSAFE.getLong((Object) null, j + STX_ATTRIBUTES);
    }

    public static int nstx_nlink(long j) {
        return UNSAFE.getInt((Object) null, j + STX_NLINK);
    }

    public static int nstx_uid(long j) {
        return UNSAFE.getInt((Object) null, j + STX_UID);
    }

    public static int nstx_gid(long j) {
        return UNSAFE.getInt((Object) null, j + STX_GID);
    }

    public static short nstx_mode(long j) {
        return UNSAFE.getShort((Object) null, j + STX_MODE);
    }

    public static ShortBuffer n__spare0(long j) {
        return MemoryUtil.memShortBuffer(j + __SPARE0, 1);
    }

    public static short n__spare0(long j, int i) {
        return UNSAFE.getShort((Object) null, j + __SPARE0 + (Checks.check(i, 1) << 1));
    }

    public static long nstx_ino(long j) {
        return UNSAFE.getLong((Object) null, j + STX_INO);
    }

    public static long nstx_size(long j) {
        return UNSAFE.getLong((Object) null, j + STX_SIZE);
    }

    public static long nstx_blocks(long j) {
        return UNSAFE.getLong((Object) null, j + STX_BLOCKS);
    }

    public static long nstx_attributes_mask(long j) {
        return UNSAFE.getLong((Object) null, j + STX_ATTRIBUTES_MASK);
    }

    public static StatxTimestamp nstx_atime(long j) {
        return StatxTimestamp.create(j + STX_ATIME);
    }

    public static StatxTimestamp nstx_btime(long j) {
        return StatxTimestamp.create(j + STX_BTIME);
    }

    public static StatxTimestamp nstx_ctime(long j) {
        return StatxTimestamp.create(j + STX_CTIME);
    }

    public static StatxTimestamp nstx_mtime(long j) {
        return StatxTimestamp.create(j + STX_MTIME);
    }

    public static int nstx_rdev_major(long j) {
        return UNSAFE.getInt((Object) null, j + STX_RDEV_MAJOR);
    }

    public static int nstx_rdev_minor(long j) {
        return UNSAFE.getInt((Object) null, j + STX_RDEV_MINOR);
    }

    public static int nstx_dev_major(long j) {
        return UNSAFE.getInt((Object) null, j + STX_DEV_MAJOR);
    }

    public static int nstx_dev_minor(long j) {
        return UNSAFE.getInt((Object) null, j + STX_DEV_MINOR);
    }

    public static long nstx_mnt_id(long j) {
        return UNSAFE.getLong((Object) null, j + STX_MNT_ID);
    }

    public static long n__spare2(long j) {
        return UNSAFE.getLong((Object) null, j + __SPARE2);
    }

    public static LongBuffer n__spare3(long j) {
        return MemoryUtil.memLongBuffer(j + __SPARE3, 12);
    }

    public static long n__spare3(long j, int i) {
        return UNSAFE.getLong((Object) null, j + __SPARE3 + (Checks.check(i, 12) << 3));
    }

    public static void nstx_mask(long j, int i) {
        UNSAFE.putInt((Object) null, j + STX_MASK, i);
    }

    public static void nstx_blksize(long j, int i) {
        UNSAFE.putInt((Object) null, j + STX_BLKSIZE, i);
    }

    public static void nstx_attributes(long j, long j2) {
        UNSAFE.putLong((Object) null, j + STX_ATTRIBUTES, j2);
    }

    public static void nstx_nlink(long j, int i) {
        UNSAFE.putInt((Object) null, j + STX_NLINK, i);
    }

    public static void nstx_uid(long j, int i) {
        UNSAFE.putInt((Object) null, j + STX_UID, i);
    }

    public static void nstx_gid(long j, int i) {
        UNSAFE.putInt((Object) null, j + STX_GID, i);
    }

    public static void nstx_mode(long j, short s) {
        UNSAFE.putShort((Object) null, j + STX_MODE, s);
    }

    public static void n__spare0(long j, ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.checkGT(shortBuffer, 1);
        }
        MemoryUtil.memCopy(MemoryUtil.memAddress(shortBuffer), j + __SPARE0, shortBuffer.remaining() << 1);
    }

    public static void n__spare0(long j, int i, short s) {
        UNSAFE.putShort((Object) null, j + __SPARE0 + (Checks.check(i, 1) << 1), s);
    }

    public static void nstx_ino(long j, long j2) {
        UNSAFE.putLong((Object) null, j + STX_INO, j2);
    }

    public static void nstx_size(long j, long j2) {
        UNSAFE.putLong((Object) null, j + STX_SIZE, j2);
    }

    public static void nstx_blocks(long j, long j2) {
        UNSAFE.putLong((Object) null, j + STX_BLOCKS, j2);
    }

    public static void nstx_attributes_mask(long j, long j2) {
        UNSAFE.putLong((Object) null, j + STX_ATTRIBUTES_MASK, j2);
    }

    public static void nstx_atime(long j, StatxTimestamp statxTimestamp) {
        MemoryUtil.memCopy(statxTimestamp.address(), j + STX_ATIME, StatxTimestamp.SIZEOF);
    }

    public static void nstx_btime(long j, StatxTimestamp statxTimestamp) {
        MemoryUtil.memCopy(statxTimestamp.address(), j + STX_BTIME, StatxTimestamp.SIZEOF);
    }

    public static void nstx_ctime(long j, StatxTimestamp statxTimestamp) {
        MemoryUtil.memCopy(statxTimestamp.address(), j + STX_CTIME, StatxTimestamp.SIZEOF);
    }

    public static void nstx_mtime(long j, StatxTimestamp statxTimestamp) {
        MemoryUtil.memCopy(statxTimestamp.address(), j + STX_MTIME, StatxTimestamp.SIZEOF);
    }

    public static void nstx_rdev_major(long j, int i) {
        UNSAFE.putInt((Object) null, j + STX_RDEV_MAJOR, i);
    }

    public static void nstx_rdev_minor(long j, int i) {
        UNSAFE.putInt((Object) null, j + STX_RDEV_MINOR, i);
    }

    public static void nstx_dev_major(long j, int i) {
        UNSAFE.putInt((Object) null, j + STX_DEV_MAJOR, i);
    }

    public static void nstx_dev_minor(long j, int i) {
        UNSAFE.putInt((Object) null, j + STX_DEV_MINOR, i);
    }

    public static void nstx_mnt_id(long j, long j2) {
        UNSAFE.putLong((Object) null, j + STX_MNT_ID, j2);
    }

    public static void n__spare2(long j, long j2) {
        UNSAFE.putLong((Object) null, j + __SPARE2, j2);
    }

    public static void n__spare3(long j, LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.checkGT(longBuffer, 12);
        }
        MemoryUtil.memCopy(MemoryUtil.memAddress(longBuffer), j + __SPARE3, longBuffer.remaining() << 3);
    }

    public static void n__spare3(long j, int i, long j2) {
        UNSAFE.putLong((Object) null, j + __SPARE3 + (Checks.check(i, 12) << 3), j2);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/Statx$Buffer.class */
    public static class Buffer extends StructBuffer<Statx, Buffer> implements NativeResource {
        private static final Statx ELEMENT_FACTORY = Statx.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / Statx.SIZEOF);
        }

        public Buffer(long j, int i) {
            super(j, null, -1, 0, i, i);
        }

        Buffer(long j, ByteBuffer byteBuffer, int i, int i2, int i3, int i4) {
            super(j, byteBuffer, i, i2, i3, i4);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.lwjgl.system.CustomBuffer
        public Buffer self() {
            return this;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.lwjgl.system.StructBuffer
        public Statx getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("__u32")
        public int stx_mask() {
            return Statx.nstx_mask(address());
        }

        @NativeType("__u32")
        public int stx_blksize() {
            return Statx.nstx_blksize(address());
        }

        @NativeType("__u64")
        public long stx_attributes() {
            return Statx.nstx_attributes(address());
        }

        @NativeType("__u32")
        public int stx_nlink() {
            return Statx.nstx_nlink(address());
        }

        @NativeType("__u32")
        public int stx_uid() {
            return Statx.nstx_uid(address());
        }

        @NativeType("__u32")
        public int stx_gid() {
            return Statx.nstx_gid(address());
        }

        @NativeType("__u16")
        public short stx_mode() {
            return Statx.nstx_mode(address());
        }

        @NativeType("__u64")
        public long stx_ino() {
            return Statx.nstx_ino(address());
        }

        @NativeType("__u64")
        public long stx_size() {
            return Statx.nstx_size(address());
        }

        @NativeType("__u64")
        public long stx_blocks() {
            return Statx.nstx_blocks(address());
        }

        @NativeType("__u64")
        public long stx_attributes_mask() {
            return Statx.nstx_attributes_mask(address());
        }

        @NativeType("struct statx_timestamp")
        public StatxTimestamp stx_atime() {
            return Statx.nstx_atime(address());
        }

        @NativeType("struct statx_timestamp")
        public StatxTimestamp stx_btime() {
            return Statx.nstx_btime(address());
        }

        @NativeType("struct statx_timestamp")
        public StatxTimestamp stx_ctime() {
            return Statx.nstx_ctime(address());
        }

        @NativeType("struct statx_timestamp")
        public StatxTimestamp stx_mtime() {
            return Statx.nstx_mtime(address());
        }

        @NativeType("__u32")
        public int stx_rdev_major() {
            return Statx.nstx_rdev_major(address());
        }

        @NativeType("__u32")
        public int stx_rdev_minor() {
            return Statx.nstx_rdev_minor(address());
        }

        @NativeType("__u32")
        public int stx_dev_major() {
            return Statx.nstx_dev_major(address());
        }

        @NativeType("__u32")
        public int stx_dev_minor() {
            return Statx.nstx_dev_minor(address());
        }

        @NativeType("__u64")
        public long stx_mnt_id() {
            return Statx.nstx_mnt_id(address());
        }

        public Buffer stx_mask(@NativeType("__u32") int i) {
            Statx.nstx_mask(address(), i);
            return this;
        }

        public Buffer stx_blksize(@NativeType("__u32") int i) {
            Statx.nstx_blksize(address(), i);
            return this;
        }

        public Buffer stx_attributes(@NativeType("__u64") long j) {
            Statx.nstx_attributes(address(), j);
            return this;
        }

        public Buffer stx_nlink(@NativeType("__u32") int i) {
            Statx.nstx_nlink(address(), i);
            return this;
        }

        public Buffer stx_uid(@NativeType("__u32") int i) {
            Statx.nstx_uid(address(), i);
            return this;
        }

        public Buffer stx_gid(@NativeType("__u32") int i) {
            Statx.nstx_gid(address(), i);
            return this;
        }

        public Buffer stx_mode(@NativeType("__u16") short s) {
            Statx.nstx_mode(address(), s);
            return this;
        }

        public Buffer stx_ino(@NativeType("__u64") long j) {
            Statx.nstx_ino(address(), j);
            return this;
        }

        public Buffer stx_size(@NativeType("__u64") long j) {
            Statx.nstx_size(address(), j);
            return this;
        }

        public Buffer stx_blocks(@NativeType("__u64") long j) {
            Statx.nstx_blocks(address(), j);
            return this;
        }

        public Buffer stx_attributes_mask(@NativeType("__u64") long j) {
            Statx.nstx_attributes_mask(address(), j);
            return this;
        }

        public Buffer stx_atime(@NativeType("struct statx_timestamp") StatxTimestamp statxTimestamp) {
            Statx.nstx_atime(address(), statxTimestamp);
            return this;
        }

        public Buffer stx_atime(Consumer<StatxTimestamp> consumer) {
            consumer.accept(stx_atime());
            return this;
        }

        public Buffer stx_btime(@NativeType("struct statx_timestamp") StatxTimestamp statxTimestamp) {
            Statx.nstx_btime(address(), statxTimestamp);
            return this;
        }

        public Buffer stx_btime(Consumer<StatxTimestamp> consumer) {
            consumer.accept(stx_btime());
            return this;
        }

        public Buffer stx_ctime(@NativeType("struct statx_timestamp") StatxTimestamp statxTimestamp) {
            Statx.nstx_ctime(address(), statxTimestamp);
            return this;
        }

        public Buffer stx_ctime(Consumer<StatxTimestamp> consumer) {
            consumer.accept(stx_ctime());
            return this;
        }

        public Buffer stx_mtime(@NativeType("struct statx_timestamp") StatxTimestamp statxTimestamp) {
            Statx.nstx_mtime(address(), statxTimestamp);
            return this;
        }

        public Buffer stx_mtime(Consumer<StatxTimestamp> consumer) {
            consumer.accept(stx_mtime());
            return this;
        }

        public Buffer stx_rdev_major(@NativeType("__u32") int i) {
            Statx.nstx_rdev_major(address(), i);
            return this;
        }

        public Buffer stx_rdev_minor(@NativeType("__u32") int i) {
            Statx.nstx_rdev_minor(address(), i);
            return this;
        }

        public Buffer stx_dev_major(@NativeType("__u32") int i) {
            Statx.nstx_dev_major(address(), i);
            return this;
        }

        public Buffer stx_dev_minor(@NativeType("__u32") int i) {
            Statx.nstx_dev_minor(address(), i);
            return this;
        }

        public Buffer stx_mnt_id(@NativeType("__u64") long j) {
            Statx.nstx_mnt_id(address(), j);
            return this;
        }
    }
}
