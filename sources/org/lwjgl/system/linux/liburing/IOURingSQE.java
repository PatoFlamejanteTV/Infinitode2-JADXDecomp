package org.lwjgl.system.linux.liburing;

import java.nio.ByteBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.Checks;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct io_uring_sqe")
/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/IOURingSQE.class */
public class IOURingSQE extends Struct<IOURingSQE> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int OPCODE;
    public static final int FLAGS;
    public static final int IOPRIO;
    public static final int FD;
    public static final int OFF;
    public static final int ADDR2;
    public static final int CMD_OP;
    public static final int __PAD1;
    public static final int ADDR;
    public static final int SPLICE_OFF_IN;
    public static final int LEVEL;
    public static final int OPTNAME;
    public static final int LEN;
    public static final int RW_FLAGS;
    public static final int FSYNC_FLAGS;
    public static final int POLL_EVENTS;
    public static final int POLL32_EVENTS;
    public static final int SYNC_RANGE_FLAGS;
    public static final int MSG_FLAGS;
    public static final int TIMEOUT_FLAGS;
    public static final int ACCEPT_FLAGS;
    public static final int CANCEL_FLAGS;
    public static final int OPEN_FLAGS;
    public static final int STATX_FLAGS;
    public static final int FADVISE_ADVICE;
    public static final int SPLICE_FLAGS;
    public static final int RENAME_FLAGS;
    public static final int UNLINK_FLAGS;
    public static final int HARDLINK_FLAGS;
    public static final int XATTR_FLAGS;
    public static final int MSG_RING_FLAGS;
    public static final int URING_CMD_FLAGS;
    public static final int WAITID_FLAGS;
    public static final int FUTEX_FLAGS;
    public static final int INSTALL_FD_FLAGS;
    public static final int USER_DATA;
    public static final int BUF_INDEX;
    public static final int BUF_GROUP;
    public static final int PERSONALITY;
    public static final int SPLICE_FD_IN;
    public static final int FILE_INDEX;
    public static final int OPTLEN;
    public static final int ADDR_LEN;
    public static final int __PAD3;
    public static final int ADDR3;
    public static final int __PAD2;
    public static final int OPTVAL;
    public static final int CMD;

    static {
        Struct.Layout __struct = __struct(__member(1), __member(1), __member(2), __member(4), __union(__member(8), __member(8), __struct(__member(4), __member(4))), __union(__member(8), __member(8), __struct(__member(4), __member(4))), __member(4), __union(__member(4), __member(4), __member(2), __member(4), __member(4), __member(4), __member(4), __member(4), __member(4), __member(4), __member(4), __member(4), __member(4), __member(4), __member(4), __member(4), __member(4), __member(4), __member(4), __member(4), __member(4), __member(4)), __member(8), __union(__member(2), __member(2)), __member(2), __union(__member(4), __member(4), __member(4), __struct(__member(2), __array(2, 1))), __union(__struct(__member(8), __array(8, 1)), __member(8), __array(1, 0)));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        OPCODE = __struct.offsetof(0);
        FLAGS = __struct.offsetof(1);
        IOPRIO = __struct.offsetof(2);
        FD = __struct.offsetof(3);
        OFF = __struct.offsetof(5);
        ADDR2 = __struct.offsetof(6);
        CMD_OP = __struct.offsetof(8);
        __PAD1 = __struct.offsetof(9);
        ADDR = __struct.offsetof(11);
        SPLICE_OFF_IN = __struct.offsetof(12);
        LEVEL = __struct.offsetof(14);
        OPTNAME = __struct.offsetof(15);
        LEN = __struct.offsetof(16);
        RW_FLAGS = __struct.offsetof(18);
        FSYNC_FLAGS = __struct.offsetof(19);
        POLL_EVENTS = __struct.offsetof(20);
        POLL32_EVENTS = __struct.offsetof(21);
        SYNC_RANGE_FLAGS = __struct.offsetof(22);
        MSG_FLAGS = __struct.offsetof(23);
        TIMEOUT_FLAGS = __struct.offsetof(24);
        ACCEPT_FLAGS = __struct.offsetof(25);
        CANCEL_FLAGS = __struct.offsetof(26);
        OPEN_FLAGS = __struct.offsetof(27);
        STATX_FLAGS = __struct.offsetof(28);
        FADVISE_ADVICE = __struct.offsetof(29);
        SPLICE_FLAGS = __struct.offsetof(30);
        RENAME_FLAGS = __struct.offsetof(31);
        UNLINK_FLAGS = __struct.offsetof(32);
        HARDLINK_FLAGS = __struct.offsetof(33);
        XATTR_FLAGS = __struct.offsetof(34);
        MSG_RING_FLAGS = __struct.offsetof(35);
        URING_CMD_FLAGS = __struct.offsetof(36);
        WAITID_FLAGS = __struct.offsetof(37);
        FUTEX_FLAGS = __struct.offsetof(38);
        INSTALL_FD_FLAGS = __struct.offsetof(39);
        USER_DATA = __struct.offsetof(40);
        BUF_INDEX = __struct.offsetof(42);
        BUF_GROUP = __struct.offsetof(43);
        PERSONALITY = __struct.offsetof(44);
        SPLICE_FD_IN = __struct.offsetof(46);
        FILE_INDEX = __struct.offsetof(47);
        OPTLEN = __struct.offsetof(48);
        ADDR_LEN = __struct.offsetof(50);
        __PAD3 = __struct.offsetof(51);
        ADDR3 = __struct.offsetof(54);
        __PAD2 = __struct.offsetof(55);
        OPTVAL = __struct.offsetof(56);
        CMD = __struct.offsetof(57);
    }

    protected IOURingSQE(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public IOURingSQE create(long j, ByteBuffer byteBuffer) {
        return new IOURingSQE(j, byteBuffer);
    }

    public IOURingSQE(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("__u8")
    public byte opcode() {
        return nopcode(address());
    }

    @NativeType("__u8")
    public byte flags() {
        return nflags(address());
    }

    @NativeType("__u16")
    public short ioprio() {
        return nioprio(address());
    }

    @NativeType("__s32")
    public int fd() {
        return nfd(address());
    }

    @NativeType("__u64")
    public long off() {
        return noff(address());
    }

    @NativeType("__u64")
    public long addr2() {
        return naddr2(address());
    }

    @NativeType("__u32")
    public int cmd_op() {
        return ncmd_op(address());
    }

    @NativeType("__u32")
    public int __pad1() {
        return n__pad1(address());
    }

    @NativeType("__u64")
    public long addr() {
        return naddr(address());
    }

    @NativeType("__u64")
    public long splice_off_in() {
        return nsplice_off_in(address());
    }

    @NativeType("__u32")
    public int level() {
        return nlevel(address());
    }

    @NativeType("__u32")
    public int optname() {
        return noptname(address());
    }

    @NativeType("__u32")
    public int len() {
        return nlen(address());
    }

    @NativeType("__kernel_rwf_t")
    public int rw_flags() {
        return nrw_flags(address());
    }

    @NativeType("__u32")
    public int fsync_flags() {
        return nfsync_flags(address());
    }

    @NativeType("__u16")
    public short poll_events() {
        return npoll_events(address());
    }

    @NativeType("__u32")
    public int poll32_events() {
        return npoll32_events(address());
    }

    @NativeType("__u32")
    public int sync_range_flags() {
        return nsync_range_flags(address());
    }

    @NativeType("__u32")
    public int msg_flags() {
        return nmsg_flags(address());
    }

    @NativeType("__u32")
    public int timeout_flags() {
        return ntimeout_flags(address());
    }

    @NativeType("__u32")
    public int accept_flags() {
        return naccept_flags(address());
    }

    @NativeType("__u32")
    public int cancel_flags() {
        return ncancel_flags(address());
    }

    @NativeType("__u32")
    public int open_flags() {
        return nopen_flags(address());
    }

    @NativeType("__u32")
    public int statx_flags() {
        return nstatx_flags(address());
    }

    @NativeType("__u32")
    public int fadvise_advice() {
        return nfadvise_advice(address());
    }

    @NativeType("__u32")
    public int splice_flags() {
        return nsplice_flags(address());
    }

    @NativeType("__u32")
    public int rename_flags() {
        return nrename_flags(address());
    }

    @NativeType("__u32")
    public int unlink_flags() {
        return nunlink_flags(address());
    }

    @NativeType("__u32")
    public int hardlink_flags() {
        return nhardlink_flags(address());
    }

    @NativeType("__u32")
    public int xattr_flags() {
        return nxattr_flags(address());
    }

    @NativeType("__u32")
    public int msg_ring_flags() {
        return nmsg_ring_flags(address());
    }

    @NativeType("__u32")
    public int uring_cmd_flags() {
        return nuring_cmd_flags(address());
    }

    @NativeType("__u32")
    public int waitid_flags() {
        return nwaitid_flags(address());
    }

    @NativeType("__u32")
    public int futex_flags() {
        return nfutex_flags(address());
    }

    @NativeType("__u32")
    public int install_fd_flags() {
        return ninstall_fd_flags(address());
    }

    @NativeType("__u64")
    public long user_data() {
        return nuser_data(address());
    }

    @NativeType("__u16")
    public short buf_index() {
        return nbuf_index(address());
    }

    @NativeType("__u16")
    public short buf_group() {
        return nbuf_group(address());
    }

    @NativeType("__u16")
    public short personality() {
        return npersonality(address());
    }

    @NativeType("__s32")
    public int splice_fd_in() {
        return nsplice_fd_in(address());
    }

    @NativeType("__u32")
    public int file_index() {
        return nfile_index(address());
    }

    @NativeType("__u32")
    public int optlen() {
        return noptlen(address());
    }

    @NativeType("__u16")
    public short addr_len() {
        return naddr_len(address());
    }

    @NativeType("__u16[1]")
    public ShortBuffer __pad3() {
        return n__pad3(address());
    }

    @NativeType("__u16")
    public short __pad3(int i) {
        return n__pad3(address(), i);
    }

    @NativeType("__u64")
    public long addr3() {
        return naddr3(address());
    }

    @NativeType("__u64[1]")
    public LongBuffer __pad2() {
        return n__pad2(address());
    }

    @NativeType("__u64")
    public long __pad2(int i) {
        return n__pad2(address(), i);
    }

    @NativeType("__u64")
    public long optval() {
        return noptval(address());
    }

    @NativeType("__u8[0]")
    public ByteBuffer cmd() {
        return ncmd(address());
    }

    @NativeType("__u8")
    public byte cmd(int i) {
        return ncmd(address(), i);
    }

    public IOURingSQE opcode(@NativeType("__u8") byte b2) {
        nopcode(address(), b2);
        return this;
    }

    public IOURingSQE flags(@NativeType("__u8") byte b2) {
        nflags(address(), b2);
        return this;
    }

    public IOURingSQE ioprio(@NativeType("__u16") short s) {
        nioprio(address(), s);
        return this;
    }

    public IOURingSQE fd(@NativeType("__s32") int i) {
        nfd(address(), i);
        return this;
    }

    public IOURingSQE off(@NativeType("__u64") long j) {
        noff(address(), j);
        return this;
    }

    public IOURingSQE addr2(@NativeType("__u64") long j) {
        naddr2(address(), j);
        return this;
    }

    public IOURingSQE cmd_op(@NativeType("__u32") int i) {
        ncmd_op(address(), i);
        return this;
    }

    public IOURingSQE __pad1(@NativeType("__u32") int i) {
        n__pad1(address(), i);
        return this;
    }

    public IOURingSQE addr(@NativeType("__u64") long j) {
        naddr(address(), j);
        return this;
    }

    public IOURingSQE splice_off_in(@NativeType("__u64") long j) {
        nsplice_off_in(address(), j);
        return this;
    }

    public IOURingSQE level(@NativeType("__u32") int i) {
        nlevel(address(), i);
        return this;
    }

    public IOURingSQE optname(@NativeType("__u32") int i) {
        noptname(address(), i);
        return this;
    }

    public IOURingSQE len(@NativeType("__u32") int i) {
        nlen(address(), i);
        return this;
    }

    public IOURingSQE rw_flags(@NativeType("__kernel_rwf_t") int i) {
        nrw_flags(address(), i);
        return this;
    }

    public IOURingSQE fsync_flags(@NativeType("__u32") int i) {
        nfsync_flags(address(), i);
        return this;
    }

    public IOURingSQE poll_events(@NativeType("__u16") short s) {
        npoll_events(address(), s);
        return this;
    }

    public IOURingSQE poll32_events(@NativeType("__u32") int i) {
        npoll32_events(address(), i);
        return this;
    }

    public IOURingSQE sync_range_flags(@NativeType("__u32") int i) {
        nsync_range_flags(address(), i);
        return this;
    }

    public IOURingSQE msg_flags(@NativeType("__u32") int i) {
        nmsg_flags(address(), i);
        return this;
    }

    public IOURingSQE timeout_flags(@NativeType("__u32") int i) {
        ntimeout_flags(address(), i);
        return this;
    }

    public IOURingSQE accept_flags(@NativeType("__u32") int i) {
        naccept_flags(address(), i);
        return this;
    }

    public IOURingSQE cancel_flags(@NativeType("__u32") int i) {
        ncancel_flags(address(), i);
        return this;
    }

    public IOURingSQE open_flags(@NativeType("__u32") int i) {
        nopen_flags(address(), i);
        return this;
    }

    public IOURingSQE statx_flags(@NativeType("__u32") int i) {
        nstatx_flags(address(), i);
        return this;
    }

    public IOURingSQE fadvise_advice(@NativeType("__u32") int i) {
        nfadvise_advice(address(), i);
        return this;
    }

    public IOURingSQE splice_flags(@NativeType("__u32") int i) {
        nsplice_flags(address(), i);
        return this;
    }

    public IOURingSQE rename_flags(@NativeType("__u32") int i) {
        nrename_flags(address(), i);
        return this;
    }

    public IOURingSQE unlink_flags(@NativeType("__u32") int i) {
        nunlink_flags(address(), i);
        return this;
    }

    public IOURingSQE hardlink_flags(@NativeType("__u32") int i) {
        nhardlink_flags(address(), i);
        return this;
    }

    public IOURingSQE xattr_flags(@NativeType("__u32") int i) {
        nxattr_flags(address(), i);
        return this;
    }

    public IOURingSQE msg_ring_flags(@NativeType("__u32") int i) {
        nmsg_ring_flags(address(), i);
        return this;
    }

    public IOURingSQE uring_cmd_flags(@NativeType("__u32") int i) {
        nuring_cmd_flags(address(), i);
        return this;
    }

    public IOURingSQE waitid_flags(@NativeType("__u32") int i) {
        nwaitid_flags(address(), i);
        return this;
    }

    public IOURingSQE futex_flags(@NativeType("__u32") int i) {
        nfutex_flags(address(), i);
        return this;
    }

    public IOURingSQE install_fd_flags(@NativeType("__u32") int i) {
        ninstall_fd_flags(address(), i);
        return this;
    }

    public IOURingSQE user_data(@NativeType("__u64") long j) {
        nuser_data(address(), j);
        return this;
    }

    public IOURingSQE buf_index(@NativeType("__u16") short s) {
        nbuf_index(address(), s);
        return this;
    }

    public IOURingSQE buf_group(@NativeType("__u16") short s) {
        nbuf_group(address(), s);
        return this;
    }

    public IOURingSQE personality(@NativeType("__u16") short s) {
        npersonality(address(), s);
        return this;
    }

    public IOURingSQE splice_fd_in(@NativeType("__s32") int i) {
        nsplice_fd_in(address(), i);
        return this;
    }

    public IOURingSQE file_index(@NativeType("__u32") int i) {
        nfile_index(address(), i);
        return this;
    }

    public IOURingSQE optlen(@NativeType("__u32") int i) {
        noptlen(address(), i);
        return this;
    }

    public IOURingSQE addr_len(@NativeType("__u16") short s) {
        naddr_len(address(), s);
        return this;
    }

    public IOURingSQE __pad3(@NativeType("__u16[1]") ShortBuffer shortBuffer) {
        n__pad3(address(), shortBuffer);
        return this;
    }

    public IOURingSQE __pad3(int i, @NativeType("__u16") short s) {
        n__pad3(address(), i, s);
        return this;
    }

    public IOURingSQE addr3(@NativeType("__u64") long j) {
        naddr3(address(), j);
        return this;
    }

    public IOURingSQE __pad2(@NativeType("__u64[1]") LongBuffer longBuffer) {
        n__pad2(address(), longBuffer);
        return this;
    }

    public IOURingSQE __pad2(int i, @NativeType("__u64") long j) {
        n__pad2(address(), i, j);
        return this;
    }

    public IOURingSQE optval(@NativeType("__u64") long j) {
        noptval(address(), j);
        return this;
    }

    public IOURingSQE cmd(@NativeType("__u8[0]") ByteBuffer byteBuffer) {
        ncmd(address(), byteBuffer);
        return this;
    }

    public IOURingSQE cmd(int i, @NativeType("__u8") byte b2) {
        ncmd(address(), i, b2);
        return this;
    }

    public IOURingSQE set(IOURingSQE iOURingSQE) {
        MemoryUtil.memCopy(iOURingSQE.address(), address(), SIZEOF);
        return this;
    }

    public static IOURingSQE malloc() {
        return new IOURingSQE(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static IOURingSQE calloc() {
        return new IOURingSQE(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static IOURingSQE create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new IOURingSQE(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static IOURingSQE create(long j) {
        return new IOURingSQE(j, null);
    }

    public static IOURingSQE createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new IOURingSQE(j, null);
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

    public static IOURingSQE malloc(MemoryStack memoryStack) {
        return new IOURingSQE(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static IOURingSQE calloc(MemoryStack memoryStack) {
        return new IOURingSQE(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static byte nopcode(long j) {
        return UNSAFE.getByte((Object) null, j + OPCODE);
    }

    public static byte nflags(long j) {
        return UNSAFE.getByte((Object) null, j + FLAGS);
    }

    public static short nioprio(long j) {
        return UNSAFE.getShort((Object) null, j + IOPRIO);
    }

    public static int nfd(long j) {
        return UNSAFE.getInt((Object) null, j + FD);
    }

    public static long noff(long j) {
        return UNSAFE.getLong((Object) null, j + OFF);
    }

    public static long naddr2(long j) {
        return UNSAFE.getLong((Object) null, j + ADDR2);
    }

    public static int ncmd_op(long j) {
        return UNSAFE.getInt((Object) null, j + CMD_OP);
    }

    public static int n__pad1(long j) {
        return UNSAFE.getInt((Object) null, j + __PAD1);
    }

    public static long naddr(long j) {
        return UNSAFE.getLong((Object) null, j + ADDR);
    }

    public static long nsplice_off_in(long j) {
        return UNSAFE.getLong((Object) null, j + SPLICE_OFF_IN);
    }

    public static int nlevel(long j) {
        return UNSAFE.getInt((Object) null, j + LEVEL);
    }

    public static int noptname(long j) {
        return UNSAFE.getInt((Object) null, j + OPTNAME);
    }

    public static int nlen(long j) {
        return UNSAFE.getInt((Object) null, j + LEN);
    }

    public static int nrw_flags(long j) {
        return UNSAFE.getInt((Object) null, j + RW_FLAGS);
    }

    public static int nfsync_flags(long j) {
        return UNSAFE.getInt((Object) null, j + FSYNC_FLAGS);
    }

    public static short npoll_events(long j) {
        return UNSAFE.getShort((Object) null, j + POLL_EVENTS);
    }

    public static int npoll32_events(long j) {
        return UNSAFE.getInt((Object) null, j + POLL32_EVENTS);
    }

    public static int nsync_range_flags(long j) {
        return UNSAFE.getInt((Object) null, j + SYNC_RANGE_FLAGS);
    }

    public static int nmsg_flags(long j) {
        return UNSAFE.getInt((Object) null, j + MSG_FLAGS);
    }

    public static int ntimeout_flags(long j) {
        return UNSAFE.getInt((Object) null, j + TIMEOUT_FLAGS);
    }

    public static int naccept_flags(long j) {
        return UNSAFE.getInt((Object) null, j + ACCEPT_FLAGS);
    }

    public static int ncancel_flags(long j) {
        return UNSAFE.getInt((Object) null, j + CANCEL_FLAGS);
    }

    public static int nopen_flags(long j) {
        return UNSAFE.getInt((Object) null, j + OPEN_FLAGS);
    }

    public static int nstatx_flags(long j) {
        return UNSAFE.getInt((Object) null, j + STATX_FLAGS);
    }

    public static int nfadvise_advice(long j) {
        return UNSAFE.getInt((Object) null, j + FADVISE_ADVICE);
    }

    public static int nsplice_flags(long j) {
        return UNSAFE.getInt((Object) null, j + SPLICE_FLAGS);
    }

    public static int nrename_flags(long j) {
        return UNSAFE.getInt((Object) null, j + RENAME_FLAGS);
    }

    public static int nunlink_flags(long j) {
        return UNSAFE.getInt((Object) null, j + UNLINK_FLAGS);
    }

    public static int nhardlink_flags(long j) {
        return UNSAFE.getInt((Object) null, j + HARDLINK_FLAGS);
    }

    public static int nxattr_flags(long j) {
        return UNSAFE.getInt((Object) null, j + XATTR_FLAGS);
    }

    public static int nmsg_ring_flags(long j) {
        return UNSAFE.getInt((Object) null, j + MSG_RING_FLAGS);
    }

    public static int nuring_cmd_flags(long j) {
        return UNSAFE.getInt((Object) null, j + URING_CMD_FLAGS);
    }

    public static int nwaitid_flags(long j) {
        return UNSAFE.getInt((Object) null, j + WAITID_FLAGS);
    }

    public static int nfutex_flags(long j) {
        return UNSAFE.getInt((Object) null, j + FUTEX_FLAGS);
    }

    public static int ninstall_fd_flags(long j) {
        return UNSAFE.getInt((Object) null, j + INSTALL_FD_FLAGS);
    }

    public static long nuser_data(long j) {
        return UNSAFE.getLong((Object) null, j + USER_DATA);
    }

    public static short nbuf_index(long j) {
        return UNSAFE.getShort((Object) null, j + BUF_INDEX);
    }

    public static short nbuf_group(long j) {
        return UNSAFE.getShort((Object) null, j + BUF_GROUP);
    }

    public static short npersonality(long j) {
        return UNSAFE.getShort((Object) null, j + PERSONALITY);
    }

    public static int nsplice_fd_in(long j) {
        return UNSAFE.getInt((Object) null, j + SPLICE_FD_IN);
    }

    public static int nfile_index(long j) {
        return UNSAFE.getInt((Object) null, j + FILE_INDEX);
    }

    public static int noptlen(long j) {
        return UNSAFE.getInt((Object) null, j + OPTLEN);
    }

    public static short naddr_len(long j) {
        return UNSAFE.getShort((Object) null, j + ADDR_LEN);
    }

    public static ShortBuffer n__pad3(long j) {
        return MemoryUtil.memShortBuffer(j + __PAD3, 1);
    }

    public static short n__pad3(long j, int i) {
        return UNSAFE.getShort((Object) null, j + __PAD3 + (Checks.check(i, 1) << 1));
    }

    public static long naddr3(long j) {
        return UNSAFE.getLong((Object) null, j + ADDR3);
    }

    public static LongBuffer n__pad2(long j) {
        return MemoryUtil.memLongBuffer(j + __PAD2, 1);
    }

    public static long n__pad2(long j, int i) {
        return UNSAFE.getLong((Object) null, j + __PAD2 + (Checks.check(i, 1) << 3));
    }

    public static long noptval(long j) {
        return UNSAFE.getLong((Object) null, j + OPTVAL);
    }

    public static ByteBuffer ncmd(long j) {
        return MemoryUtil.memByteBuffer(j + CMD, 0);
    }

    public static byte ncmd(long j, int i) {
        return UNSAFE.getByte((Object) null, j + CMD + Checks.check(i, 0));
    }

    public static void nopcode(long j, byte b2) {
        UNSAFE.putByte((Object) null, j + OPCODE, b2);
    }

    public static void nflags(long j, byte b2) {
        UNSAFE.putByte((Object) null, j + FLAGS, b2);
    }

    public static void nioprio(long j, short s) {
        UNSAFE.putShort((Object) null, j + IOPRIO, s);
    }

    public static void nfd(long j, int i) {
        UNSAFE.putInt((Object) null, j + FD, i);
    }

    public static void noff(long j, long j2) {
        UNSAFE.putLong((Object) null, j + OFF, j2);
    }

    public static void naddr2(long j, long j2) {
        UNSAFE.putLong((Object) null, j + ADDR2, j2);
    }

    public static void ncmd_op(long j, int i) {
        UNSAFE.putInt((Object) null, j + CMD_OP, i);
    }

    public static void n__pad1(long j, int i) {
        UNSAFE.putInt((Object) null, j + __PAD1, i);
    }

    public static void naddr(long j, long j2) {
        UNSAFE.putLong((Object) null, j + ADDR, j2);
    }

    public static void nsplice_off_in(long j, long j2) {
        UNSAFE.putLong((Object) null, j + SPLICE_OFF_IN, j2);
    }

    public static void nlevel(long j, int i) {
        UNSAFE.putInt((Object) null, j + LEVEL, i);
    }

    public static void noptname(long j, int i) {
        UNSAFE.putInt((Object) null, j + OPTNAME, i);
    }

    public static void nlen(long j, int i) {
        UNSAFE.putInt((Object) null, j + LEN, i);
    }

    public static void nrw_flags(long j, int i) {
        UNSAFE.putInt((Object) null, j + RW_FLAGS, i);
    }

    public static void nfsync_flags(long j, int i) {
        UNSAFE.putInt((Object) null, j + FSYNC_FLAGS, i);
    }

    public static void npoll_events(long j, short s) {
        UNSAFE.putShort((Object) null, j + POLL_EVENTS, s);
    }

    public static void npoll32_events(long j, int i) {
        UNSAFE.putInt((Object) null, j + POLL32_EVENTS, i);
    }

    public static void nsync_range_flags(long j, int i) {
        UNSAFE.putInt((Object) null, j + SYNC_RANGE_FLAGS, i);
    }

    public static void nmsg_flags(long j, int i) {
        UNSAFE.putInt((Object) null, j + MSG_FLAGS, i);
    }

    public static void ntimeout_flags(long j, int i) {
        UNSAFE.putInt((Object) null, j + TIMEOUT_FLAGS, i);
    }

    public static void naccept_flags(long j, int i) {
        UNSAFE.putInt((Object) null, j + ACCEPT_FLAGS, i);
    }

    public static void ncancel_flags(long j, int i) {
        UNSAFE.putInt((Object) null, j + CANCEL_FLAGS, i);
    }

    public static void nopen_flags(long j, int i) {
        UNSAFE.putInt((Object) null, j + OPEN_FLAGS, i);
    }

    public static void nstatx_flags(long j, int i) {
        UNSAFE.putInt((Object) null, j + STATX_FLAGS, i);
    }

    public static void nfadvise_advice(long j, int i) {
        UNSAFE.putInt((Object) null, j + FADVISE_ADVICE, i);
    }

    public static void nsplice_flags(long j, int i) {
        UNSAFE.putInt((Object) null, j + SPLICE_FLAGS, i);
    }

    public static void nrename_flags(long j, int i) {
        UNSAFE.putInt((Object) null, j + RENAME_FLAGS, i);
    }

    public static void nunlink_flags(long j, int i) {
        UNSAFE.putInt((Object) null, j + UNLINK_FLAGS, i);
    }

    public static void nhardlink_flags(long j, int i) {
        UNSAFE.putInt((Object) null, j + HARDLINK_FLAGS, i);
    }

    public static void nxattr_flags(long j, int i) {
        UNSAFE.putInt((Object) null, j + XATTR_FLAGS, i);
    }

    public static void nmsg_ring_flags(long j, int i) {
        UNSAFE.putInt((Object) null, j + MSG_RING_FLAGS, i);
    }

    public static void nuring_cmd_flags(long j, int i) {
        UNSAFE.putInt((Object) null, j + URING_CMD_FLAGS, i);
    }

    public static void nwaitid_flags(long j, int i) {
        UNSAFE.putInt((Object) null, j + WAITID_FLAGS, i);
    }

    public static void nfutex_flags(long j, int i) {
        UNSAFE.putInt((Object) null, j + FUTEX_FLAGS, i);
    }

    public static void ninstall_fd_flags(long j, int i) {
        UNSAFE.putInt((Object) null, j + INSTALL_FD_FLAGS, i);
    }

    public static void nuser_data(long j, long j2) {
        UNSAFE.putLong((Object) null, j + USER_DATA, j2);
    }

    public static void nbuf_index(long j, short s) {
        UNSAFE.putShort((Object) null, j + BUF_INDEX, s);
    }

    public static void nbuf_group(long j, short s) {
        UNSAFE.putShort((Object) null, j + BUF_GROUP, s);
    }

    public static void npersonality(long j, short s) {
        UNSAFE.putShort((Object) null, j + PERSONALITY, s);
    }

    public static void nsplice_fd_in(long j, int i) {
        UNSAFE.putInt((Object) null, j + SPLICE_FD_IN, i);
    }

    public static void nfile_index(long j, int i) {
        UNSAFE.putInt((Object) null, j + FILE_INDEX, i);
    }

    public static void noptlen(long j, int i) {
        UNSAFE.putInt((Object) null, j + OPTLEN, i);
    }

    public static void naddr_len(long j, short s) {
        UNSAFE.putShort((Object) null, j + ADDR_LEN, s);
    }

    public static void n__pad3(long j, ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.checkGT(shortBuffer, 1);
        }
        MemoryUtil.memCopy(MemoryUtil.memAddress(shortBuffer), j + __PAD3, shortBuffer.remaining() << 1);
    }

    public static void n__pad3(long j, int i, short s) {
        UNSAFE.putShort((Object) null, j + __PAD3 + (Checks.check(i, 1) << 1), s);
    }

    public static void naddr3(long j, long j2) {
        UNSAFE.putLong((Object) null, j + ADDR3, j2);
    }

    public static void n__pad2(long j, LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.checkGT(longBuffer, 1);
        }
        MemoryUtil.memCopy(MemoryUtil.memAddress(longBuffer), j + __PAD2, longBuffer.remaining() << 3);
    }

    public static void n__pad2(long j, int i, long j2) {
        UNSAFE.putLong((Object) null, j + __PAD2 + (Checks.check(i, 1) << 3), j2);
    }

    public static void noptval(long j, long j2) {
        UNSAFE.putLong((Object) null, j + OPTVAL, j2);
    }

    public static void ncmd(long j, ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkGT(byteBuffer, 0);
        }
        MemoryUtil.memCopy(MemoryUtil.memAddress(byteBuffer), j + CMD, byteBuffer.remaining());
    }

    public static void ncmd(long j, int i, byte b2) {
        UNSAFE.putByte((Object) null, j + CMD + Checks.check(i, 0), b2);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/IOURingSQE$Buffer.class */
    public static class Buffer extends StructBuffer<IOURingSQE, Buffer> implements NativeResource {
        private static final IOURingSQE ELEMENT_FACTORY = IOURingSQE.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / IOURingSQE.SIZEOF);
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
        public IOURingSQE getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("__u8")
        public byte opcode() {
            return IOURingSQE.nopcode(address());
        }

        @NativeType("__u8")
        public byte flags() {
            return IOURingSQE.nflags(address());
        }

        @NativeType("__u16")
        public short ioprio() {
            return IOURingSQE.nioprio(address());
        }

        @NativeType("__s32")
        public int fd() {
            return IOURingSQE.nfd(address());
        }

        @NativeType("__u64")
        public long off() {
            return IOURingSQE.noff(address());
        }

        @NativeType("__u64")
        public long addr2() {
            return IOURingSQE.naddr2(address());
        }

        @NativeType("__u32")
        public int cmd_op() {
            return IOURingSQE.ncmd_op(address());
        }

        @NativeType("__u32")
        public int __pad1() {
            return IOURingSQE.n__pad1(address());
        }

        @NativeType("__u64")
        public long addr() {
            return IOURingSQE.naddr(address());
        }

        @NativeType("__u64")
        public long splice_off_in() {
            return IOURingSQE.nsplice_off_in(address());
        }

        @NativeType("__u32")
        public int level() {
            return IOURingSQE.nlevel(address());
        }

        @NativeType("__u32")
        public int optname() {
            return IOURingSQE.noptname(address());
        }

        @NativeType("__u32")
        public int len() {
            return IOURingSQE.nlen(address());
        }

        @NativeType("__kernel_rwf_t")
        public int rw_flags() {
            return IOURingSQE.nrw_flags(address());
        }

        @NativeType("__u32")
        public int fsync_flags() {
            return IOURingSQE.nfsync_flags(address());
        }

        @NativeType("__u16")
        public short poll_events() {
            return IOURingSQE.npoll_events(address());
        }

        @NativeType("__u32")
        public int poll32_events() {
            return IOURingSQE.npoll32_events(address());
        }

        @NativeType("__u32")
        public int sync_range_flags() {
            return IOURingSQE.nsync_range_flags(address());
        }

        @NativeType("__u32")
        public int msg_flags() {
            return IOURingSQE.nmsg_flags(address());
        }

        @NativeType("__u32")
        public int timeout_flags() {
            return IOURingSQE.ntimeout_flags(address());
        }

        @NativeType("__u32")
        public int accept_flags() {
            return IOURingSQE.naccept_flags(address());
        }

        @NativeType("__u32")
        public int cancel_flags() {
            return IOURingSQE.ncancel_flags(address());
        }

        @NativeType("__u32")
        public int open_flags() {
            return IOURingSQE.nopen_flags(address());
        }

        @NativeType("__u32")
        public int statx_flags() {
            return IOURingSQE.nstatx_flags(address());
        }

        @NativeType("__u32")
        public int fadvise_advice() {
            return IOURingSQE.nfadvise_advice(address());
        }

        @NativeType("__u32")
        public int splice_flags() {
            return IOURingSQE.nsplice_flags(address());
        }

        @NativeType("__u32")
        public int rename_flags() {
            return IOURingSQE.nrename_flags(address());
        }

        @NativeType("__u32")
        public int unlink_flags() {
            return IOURingSQE.nunlink_flags(address());
        }

        @NativeType("__u32")
        public int hardlink_flags() {
            return IOURingSQE.nhardlink_flags(address());
        }

        @NativeType("__u32")
        public int xattr_flags() {
            return IOURingSQE.nxattr_flags(address());
        }

        @NativeType("__u32")
        public int msg_ring_flags() {
            return IOURingSQE.nmsg_ring_flags(address());
        }

        @NativeType("__u32")
        public int uring_cmd_flags() {
            return IOURingSQE.nuring_cmd_flags(address());
        }

        @NativeType("__u32")
        public int waitid_flags() {
            return IOURingSQE.nwaitid_flags(address());
        }

        @NativeType("__u32")
        public int futex_flags() {
            return IOURingSQE.nfutex_flags(address());
        }

        @NativeType("__u32")
        public int install_fd_flags() {
            return IOURingSQE.ninstall_fd_flags(address());
        }

        @NativeType("__u64")
        public long user_data() {
            return IOURingSQE.nuser_data(address());
        }

        @NativeType("__u16")
        public short buf_index() {
            return IOURingSQE.nbuf_index(address());
        }

        @NativeType("__u16")
        public short buf_group() {
            return IOURingSQE.nbuf_group(address());
        }

        @NativeType("__u16")
        public short personality() {
            return IOURingSQE.npersonality(address());
        }

        @NativeType("__s32")
        public int splice_fd_in() {
            return IOURingSQE.nsplice_fd_in(address());
        }

        @NativeType("__u32")
        public int file_index() {
            return IOURingSQE.nfile_index(address());
        }

        @NativeType("__u32")
        public int optlen() {
            return IOURingSQE.noptlen(address());
        }

        @NativeType("__u16")
        public short addr_len() {
            return IOURingSQE.naddr_len(address());
        }

        @NativeType("__u16[1]")
        public ShortBuffer __pad3() {
            return IOURingSQE.n__pad3(address());
        }

        @NativeType("__u16")
        public short __pad3(int i) {
            return IOURingSQE.n__pad3(address(), i);
        }

        @NativeType("__u64")
        public long addr3() {
            return IOURingSQE.naddr3(address());
        }

        @NativeType("__u64[1]")
        public LongBuffer __pad2() {
            return IOURingSQE.n__pad2(address());
        }

        @NativeType("__u64")
        public long __pad2(int i) {
            return IOURingSQE.n__pad2(address(), i);
        }

        @NativeType("__u64")
        public long optval() {
            return IOURingSQE.noptval(address());
        }

        @NativeType("__u8[0]")
        public ByteBuffer cmd() {
            return IOURingSQE.ncmd(address());
        }

        @NativeType("__u8")
        public byte cmd(int i) {
            return IOURingSQE.ncmd(address(), i);
        }

        public Buffer opcode(@NativeType("__u8") byte b2) {
            IOURingSQE.nopcode(address(), b2);
            return this;
        }

        public Buffer flags(@NativeType("__u8") byte b2) {
            IOURingSQE.nflags(address(), b2);
            return this;
        }

        public Buffer ioprio(@NativeType("__u16") short s) {
            IOURingSQE.nioprio(address(), s);
            return this;
        }

        public Buffer fd(@NativeType("__s32") int i) {
            IOURingSQE.nfd(address(), i);
            return this;
        }

        public Buffer off(@NativeType("__u64") long j) {
            IOURingSQE.noff(address(), j);
            return this;
        }

        public Buffer addr2(@NativeType("__u64") long j) {
            IOURingSQE.naddr2(address(), j);
            return this;
        }

        public Buffer cmd_op(@NativeType("__u32") int i) {
            IOURingSQE.ncmd_op(address(), i);
            return this;
        }

        public Buffer __pad1(@NativeType("__u32") int i) {
            IOURingSQE.n__pad1(address(), i);
            return this;
        }

        public Buffer addr(@NativeType("__u64") long j) {
            IOURingSQE.naddr(address(), j);
            return this;
        }

        public Buffer splice_off_in(@NativeType("__u64") long j) {
            IOURingSQE.nsplice_off_in(address(), j);
            return this;
        }

        public Buffer level(@NativeType("__u32") int i) {
            IOURingSQE.nlevel(address(), i);
            return this;
        }

        public Buffer optname(@NativeType("__u32") int i) {
            IOURingSQE.noptname(address(), i);
            return this;
        }

        public Buffer len(@NativeType("__u32") int i) {
            IOURingSQE.nlen(address(), i);
            return this;
        }

        public Buffer rw_flags(@NativeType("__kernel_rwf_t") int i) {
            IOURingSQE.nrw_flags(address(), i);
            return this;
        }

        public Buffer fsync_flags(@NativeType("__u32") int i) {
            IOURingSQE.nfsync_flags(address(), i);
            return this;
        }

        public Buffer poll_events(@NativeType("__u16") short s) {
            IOURingSQE.npoll_events(address(), s);
            return this;
        }

        public Buffer poll32_events(@NativeType("__u32") int i) {
            IOURingSQE.npoll32_events(address(), i);
            return this;
        }

        public Buffer sync_range_flags(@NativeType("__u32") int i) {
            IOURingSQE.nsync_range_flags(address(), i);
            return this;
        }

        public Buffer msg_flags(@NativeType("__u32") int i) {
            IOURingSQE.nmsg_flags(address(), i);
            return this;
        }

        public Buffer timeout_flags(@NativeType("__u32") int i) {
            IOURingSQE.ntimeout_flags(address(), i);
            return this;
        }

        public Buffer accept_flags(@NativeType("__u32") int i) {
            IOURingSQE.naccept_flags(address(), i);
            return this;
        }

        public Buffer cancel_flags(@NativeType("__u32") int i) {
            IOURingSQE.ncancel_flags(address(), i);
            return this;
        }

        public Buffer open_flags(@NativeType("__u32") int i) {
            IOURingSQE.nopen_flags(address(), i);
            return this;
        }

        public Buffer statx_flags(@NativeType("__u32") int i) {
            IOURingSQE.nstatx_flags(address(), i);
            return this;
        }

        public Buffer fadvise_advice(@NativeType("__u32") int i) {
            IOURingSQE.nfadvise_advice(address(), i);
            return this;
        }

        public Buffer splice_flags(@NativeType("__u32") int i) {
            IOURingSQE.nsplice_flags(address(), i);
            return this;
        }

        public Buffer rename_flags(@NativeType("__u32") int i) {
            IOURingSQE.nrename_flags(address(), i);
            return this;
        }

        public Buffer unlink_flags(@NativeType("__u32") int i) {
            IOURingSQE.nunlink_flags(address(), i);
            return this;
        }

        public Buffer hardlink_flags(@NativeType("__u32") int i) {
            IOURingSQE.nhardlink_flags(address(), i);
            return this;
        }

        public Buffer xattr_flags(@NativeType("__u32") int i) {
            IOURingSQE.nxattr_flags(address(), i);
            return this;
        }

        public Buffer msg_ring_flags(@NativeType("__u32") int i) {
            IOURingSQE.nmsg_ring_flags(address(), i);
            return this;
        }

        public Buffer uring_cmd_flags(@NativeType("__u32") int i) {
            IOURingSQE.nuring_cmd_flags(address(), i);
            return this;
        }

        public Buffer waitid_flags(@NativeType("__u32") int i) {
            IOURingSQE.nwaitid_flags(address(), i);
            return this;
        }

        public Buffer futex_flags(@NativeType("__u32") int i) {
            IOURingSQE.nfutex_flags(address(), i);
            return this;
        }

        public Buffer install_fd_flags(@NativeType("__u32") int i) {
            IOURingSQE.ninstall_fd_flags(address(), i);
            return this;
        }

        public Buffer user_data(@NativeType("__u64") long j) {
            IOURingSQE.nuser_data(address(), j);
            return this;
        }

        public Buffer buf_index(@NativeType("__u16") short s) {
            IOURingSQE.nbuf_index(address(), s);
            return this;
        }

        public Buffer buf_group(@NativeType("__u16") short s) {
            IOURingSQE.nbuf_group(address(), s);
            return this;
        }

        public Buffer personality(@NativeType("__u16") short s) {
            IOURingSQE.npersonality(address(), s);
            return this;
        }

        public Buffer splice_fd_in(@NativeType("__s32") int i) {
            IOURingSQE.nsplice_fd_in(address(), i);
            return this;
        }

        public Buffer file_index(@NativeType("__u32") int i) {
            IOURingSQE.nfile_index(address(), i);
            return this;
        }

        public Buffer optlen(@NativeType("__u32") int i) {
            IOURingSQE.noptlen(address(), i);
            return this;
        }

        public Buffer addr_len(@NativeType("__u16") short s) {
            IOURingSQE.naddr_len(address(), s);
            return this;
        }

        public Buffer __pad3(@NativeType("__u16[1]") ShortBuffer shortBuffer) {
            IOURingSQE.n__pad3(address(), shortBuffer);
            return this;
        }

        public Buffer __pad3(int i, @NativeType("__u16") short s) {
            IOURingSQE.n__pad3(address(), i, s);
            return this;
        }

        public Buffer addr3(@NativeType("__u64") long j) {
            IOURingSQE.naddr3(address(), j);
            return this;
        }

        public Buffer __pad2(@NativeType("__u64[1]") LongBuffer longBuffer) {
            IOURingSQE.n__pad2(address(), longBuffer);
            return this;
        }

        public Buffer __pad2(int i, @NativeType("__u64") long j) {
            IOURingSQE.n__pad2(address(), i, j);
            return this;
        }

        public Buffer optval(@NativeType("__u64") long j) {
            IOURingSQE.noptval(address(), j);
            return this;
        }

        public Buffer cmd(@NativeType("__u8[0]") ByteBuffer byteBuffer) {
            IOURingSQE.ncmd(address(), byteBuffer);
            return this;
        }

        public Buffer cmd(int i, @NativeType("__u8") byte b2) {
            IOURingSQE.ncmd(address(), i, b2);
            return this;
        }
    }
}
