package org.lwjgl.system.linux.liburing;

import org.lwjgl.system.Library;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/LibIOURing.class */
public class LibIOURing {
    public static final int IORING_FILE_INDEX_ALLOC = -1;
    public static final int IORING_MAX_ENTRIES = 4096;
    public static final int IOSQE_FIXED_FILE_BIT = 0;
    public static final int IOSQE_IO_DRAIN_BIT = 1;
    public static final int IOSQE_IO_LINK_BIT = 2;
    public static final int IOSQE_IO_HARDLINK_BIT = 3;
    public static final int IOSQE_ASYNC_BIT = 4;
    public static final int IOSQE_BUFFER_SELECT_BIT = 5;
    public static final int IOSQE_CQE_SKIP_SUCCESS_BIT = 6;
    public static final int IOSQE_FIXED_FILE = 1;
    public static final int IOSQE_IO_DRAIN = 2;
    public static final int IOSQE_IO_LINK = 4;
    public static final int IOSQE_IO_HARDLINK = 8;
    public static final int IOSQE_ASYNC = 16;
    public static final int IOSQE_BUFFER_SELECT = 32;
    public static final int IOSQE_CQE_SKIP_SUCCESS = 64;
    public static final int IORING_SETUP_IOPOLL = 1;
    public static final int IORING_SETUP_SQPOLL = 2;
    public static final int IORING_SETUP_SQ_AFF = 4;
    public static final int IORING_SETUP_CQSIZE = 8;
    public static final int IORING_SETUP_CLAMP = 16;
    public static final int IORING_SETUP_ATTACH_WQ = 32;
    public static final int IORING_SETUP_R_DISABLED = 64;
    public static final int IORING_SETUP_SUBMIT_ALL = 128;
    public static final int IORING_SETUP_COOP_TASKRUN = 256;
    public static final int IORING_SETUP_TASKRUN_FLAG = 512;
    public static final int IORING_SETUP_SQE128 = 1024;
    public static final int IORING_SETUP_CQE32 = 2048;
    public static final int IORING_SETUP_SINGLE_ISSUER = 4096;
    public static final int IORING_SETUP_DEFER_TASKRUN = 8192;
    public static final int IORING_SETUP_NO_MMAP = 16384;
    public static final int IORING_SETUP_REGISTERED_FD_ONLY = 32768;
    public static final int IORING_SETUP_NO_SQARRAY = 65536;
    public static final byte IORING_OP_NOP = 0;
    public static final byte IORING_OP_READV = 1;
    public static final byte IORING_OP_WRITEV = 2;
    public static final byte IORING_OP_FSYNC = 3;
    public static final byte IORING_OP_READ_FIXED = 4;
    public static final byte IORING_OP_WRITE_FIXED = 5;
    public static final byte IORING_OP_POLL_ADD = 6;
    public static final byte IORING_OP_POLL_REMOVE = 7;
    public static final byte IORING_OP_SYNC_FILE_RANGE = 8;
    public static final byte IORING_OP_SENDMSG = 9;
    public static final byte IORING_OP_RECVMSG = 10;
    public static final byte IORING_OP_TIMEOUT = 11;
    public static final byte IORING_OP_TIMEOUT_REMOVE = 12;
    public static final byte IORING_OP_ACCEPT = 13;
    public static final byte IORING_OP_ASYNC_CANCEL = 14;
    public static final byte IORING_OP_LINK_TIMEOUT = 15;
    public static final byte IORING_OP_CONNECT = 16;
    public static final byte IORING_OP_FALLOCATE = 17;
    public static final byte IORING_OP_OPENAT = 18;
    public static final byte IORING_OP_CLOSE = 19;
    public static final byte IORING_OP_FILES_UPDATE = 20;
    public static final byte IORING_OP_STATX = 21;
    public static final byte IORING_OP_READ = 22;
    public static final byte IORING_OP_WRITE = 23;
    public static final byte IORING_OP_FADVISE = 24;
    public static final byte IORING_OP_MADVISE = 25;
    public static final byte IORING_OP_SEND = 26;
    public static final byte IORING_OP_RECV = 27;
    public static final byte IORING_OP_OPENAT2 = 28;
    public static final byte IORING_OP_EPOLL_CTL = 29;
    public static final byte IORING_OP_SPLICE = 30;
    public static final byte IORING_OP_PROVIDE_BUFFERS = 31;
    public static final byte IORING_OP_REMOVE_BUFFERS = 32;
    public static final byte IORING_OP_TEE = 33;
    public static final byte IORING_OP_SHUTDOWN = 34;
    public static final byte IORING_OP_RENAMEAT = 35;
    public static final byte IORING_OP_UNLINKAT = 36;
    public static final byte IORING_OP_MKDIRAT = 37;
    public static final byte IORING_OP_SYMLINKAT = 38;
    public static final byte IORING_OP_LINKAT = 39;
    public static final byte IORING_OP_MSG_RING = 40;
    public static final byte IORING_OP_FSETXATTR = 41;
    public static final byte IORING_OP_SETXATTR = 42;
    public static final byte IORING_OP_FGETXATTR = 43;
    public static final byte IORING_OP_GETXATTR = 44;
    public static final byte IORING_OP_SOCKET = 45;
    public static final byte IORING_OP_URING_CMD = 46;
    public static final byte IORING_OP_SEND_ZC = 47;
    public static final byte IORING_OP_SENDMSG_ZC = 48;
    public static final byte IORING_OP_READ_MULTISHOT = 49;
    public static final byte IORING_OP_WAITID = 50;
    public static final byte IORING_OP_FUTEX_WAIT = 51;
    public static final byte IORING_OP_FUTEX_WAKE = 52;
    public static final byte IORING_OP_FUTEX_WAITV = 53;
    public static final byte IORING_OP_FIXED_FD_INSTALL = 54;
    public static final byte IORING_OP_FTRUNCATE = 55;
    public static final byte IORING_OP_LAST = 56;
    public static final int IORING_URING_CMD_FIXED = 1;
    public static final int IORING_FSYNC_DATASYNC = 1;
    public static final int IORING_TIMEOUT_ABS = 1;
    public static final int IORING_TIMEOUT_UPDATE = 2;
    public static final int IORING_TIMEOUT_BOOTTIME = 4;
    public static final int IORING_TIMEOUT_REALTIME = 8;
    public static final int IORING_LINK_TIMEOUT_UPDATE = 16;
    public static final int IORING_TIMEOUT_ETIME_SUCCESS = 32;
    public static final int IORING_TIMEOUT_MULTISHOT = 64;
    public static final int IORING_TIMEOUT_CLOCK_MASK = 12;
    public static final int IORING_TIMEOUT_UPDATE_MASK = 18;
    public static final int IORING_SPLICE_F_FD_IN_FIXED = Integer.MIN_VALUE;
    public static final int IORING_POLL_ADD_MULTI = 1;
    public static final int IORING_POLL_UPDATE_EVENTS = 2;
    public static final int IORING_POLL_UPDATE_USER_DATA = 4;
    public static final int IORING_POLL_ADD_LEVEL = 8;
    public static final int IORING_ASYNC_CANCEL_ALL = 1;
    public static final int IORING_ASYNC_CANCEL_FD = 2;
    public static final int IORING_ASYNC_CANCEL_ANY = 4;
    public static final int IORING_ASYNC_CANCEL_FD_FIXED = 8;
    public static final int IORING_RECVSEND_POLL_FIRST = 1;
    public static final int IORING_RECV_MULTISHOT = 2;
    public static final int IORING_RECVSEND_FIXED_BUF = 4;
    public static final int IORING_SEND_ZC_REPORT_USAGE = 8;
    public static final int IORING_NOTIF_USAGE_ZC_COPIED = Integer.MIN_VALUE;
    public static final int IORING_ACCEPT_MULTISHOT = 1;
    public static final int IORING_MSG_DATA = 0;
    public static final int IORING_MSG_SEND_FD = 1;
    public static final int IORING_MSG_RING_CQE_SKIP = 1;
    public static final int IORING_MSG_RING_FLAGS_PASS = 2;
    public static final int IORING_FIXED_FD_NO_CLOEXEC = 1;
    public static final int IORING_CQE_F_BUFFER = 1;
    public static final int IORING_CQE_F_MORE = 2;
    public static final int IORING_CQE_F_SOCK_NONEMPTY = 4;
    public static final int IORING_CQE_F_NOTIF = 8;
    public static final int IORING_CQE_BUFFER_SHIFT = 16;
    public static final long IORING_OFF_SQ_RING = 0;
    public static final long IORING_OFF_CQ_RING = 134217728;
    public static final long IORING_OFF_SQES = 268435456;
    public static final long IORING_OFF_PBUF_RING = 2147483648L;
    public static final long IORING_OFF_PBUF_SHIFT = 16;
    public static final long IORING_OFF_MMAP_MASK = 4160749568L;
    public static final int IORING_SQ_NEED_WAKEUP = 1;
    public static final int IORING_SQ_CQ_OVERFLOW = 2;
    public static final int IORING_SQ_TASKRUN = 4;
    public static final int IORING_CQ_EVENTFD_DISABLED = 1;
    public static final int IORING_ENTER_GETEVENTS = 1;
    public static final int IORING_ENTER_SQ_WAKEUP = 2;
    public static final int IORING_ENTER_SQ_WAIT = 4;
    public static final int IORING_ENTER_EXT_ARG = 8;
    public static final int IORING_ENTER_REGISTERED_RING = 16;
    public static final int IORING_FEAT_SINGLE_MMAP = 1;
    public static final int IORING_FEAT_NODROP = 2;
    public static final int IORING_FEAT_SUBMIT_STABLE = 4;
    public static final int IORING_FEAT_RW_CUR_POS = 8;
    public static final int IORING_FEAT_CUR_PERSONALITY = 16;
    public static final int IORING_FEAT_FAST_POLL = 32;
    public static final int IORING_FEAT_POLL_32BITS = 64;
    public static final int IORING_FEAT_SQPOLL_NONFIXED = 128;
    public static final int IORING_FEAT_EXT_ARG = 256;
    public static final int IORING_FEAT_NATIVE_WORKERS = 512;
    public static final int IORING_FEAT_RSRC_TAGS = 1024;
    public static final int IORING_FEAT_CQE_SKIP = 2048;
    public static final int IORING_FEAT_LINKED_FILE = 4096;
    public static final int IORING_FEAT_REG_REG_RING = 8192;
    public static final int IORING_REGISTER_BUFFERS = 0;
    public static final int IORING_UNREGISTER_BUFFERS = 1;
    public static final int IORING_REGISTER_FILES = 2;
    public static final int IORING_UNREGISTER_FILES = 3;
    public static final int IORING_REGISTER_EVENTFD = 4;
    public static final int IORING_UNREGISTER_EVENTFD = 5;
    public static final int IORING_REGISTER_FILES_UPDATE = 6;
    public static final int IORING_REGISTER_EVENTFD_ASYNC = 7;
    public static final int IORING_REGISTER_PROBE = 8;
    public static final int IORING_REGISTER_PERSONALITY = 9;
    public static final int IORING_UNREGISTER_PERSONALITY = 10;
    public static final int IORING_REGISTER_RESTRICTIONS = 11;
    public static final int IORING_REGISTER_ENABLE_RINGS = 12;
    public static final int IORING_REGISTER_FILES2 = 13;
    public static final int IORING_REGISTER_FILES_UPDATE2 = 14;
    public static final int IORING_REGISTER_BUFFERS2 = 15;
    public static final int IORING_REGISTER_BUFFERS_UPDATE = 16;
    public static final int IORING_REGISTER_IOWQ_AFF = 17;
    public static final int IORING_UNREGISTER_IOWQ_AFF = 18;
    public static final int IORING_REGISTER_IOWQ_MAX_WORKERS = 19;
    public static final int IORING_REGISTER_RING_FDS = 20;
    public static final int IORING_UNREGISTER_RING_FDS = 21;
    public static final int IORING_REGISTER_PBUF_RING = 22;
    public static final int IORING_UNREGISTER_PBUF_RING = 23;
    public static final int IORING_REGISTER_SYNC_CANCEL = 24;
    public static final int IORING_REGISTER_FILE_ALLOC_RANGE = 25;
    public static final int IORING_REGISTER_PBUF_STATUS = 26;
    public static final int IORING_REGISTER_NAPI = 27;
    public static final int IORING_UNREGISTER_NAPI = 28;
    public static final int IORING_REGISTER_LAST = 29;
    public static final int IORING_REGISTER_USE_REGISTERED_RING = Integer.MIN_VALUE;
    public static final int IORING_RSRC_REGISTER_SPARSE = 1;
    public static final int IO_WQ_BOUND = 0;
    public static final int IO_WQ_UNBOUND = 1;
    public static final int IORING_REGISTER_FILES_SKIP = -2;
    public static final int IO_URING_OP_SUPPORTED = 1;
    public static final int IOU_PBUF_RING_MMAP = 1;
    public static final int IORING_RESTRICTION_REGISTER_OP = 0;
    public static final int IORING_RESTRICTION_SQE_OP = 1;
    public static final int IORING_RESTRICTION_SQE_FLAGS_ALLOWED = 2;
    public static final int IORING_RESTRICTION_SQE_FLAGS_REQUIRED = 3;
    public static final int IORING_RESTRICTION_LAST = 4;
    public static final int SOCKET_URING_OP_SIOCINQ = 0;
    public static final int SOCKET_URING_OP_SIOCOUTQ = 1;
    public static final int SOCKET_URING_OP_GETSOCKOPT = 2;
    public static final int SOCKET_URING_OP_SETSOCKOPT = 3;

    public static native int nio_uring_setup(int i, long j);

    public static native int nio_uring_register(int i, int i2, long j, int i3);

    public static native int nio_uring_enter2(int i, int i2, int i3, int i4, long j, int i5);

    public static native int nio_uring_enter(int i, int i2, int i3, int i4, long j);

    static {
        Library.initialize();
    }

    protected LibIOURing() {
        throw new UnsupportedOperationException();
    }

    public static int io_uring_setup(@NativeType("unsigned") int i, @NativeType("struct io_uring_params *") IOURingParams iOURingParams) {
        return nio_uring_setup(i, iOURingParams.address());
    }

    public static int io_uring_register(int i, @NativeType("unsigned") int i2, @NativeType("void *") long j, @NativeType("unsigned") int i3) {
        return nio_uring_register(i, i2, j, i3);
    }

    public static int io_uring_enter2(int i, @NativeType("unsigned") int i2, @NativeType("unsigned") int i3, @NativeType("unsigned") int i4, @NativeType("sigset_t *") long j, int i5) {
        return nio_uring_enter2(i, i2, i3, i4, j, i5);
    }

    public static int io_uring_enter(int i, @NativeType("unsigned") int i2, @NativeType("unsigned") int i3, @NativeType("unsigned") int i4, @NativeType("sigset_t *") long j) {
        return nio_uring_enter(i, i2, i3, i4, j);
    }
}
