package org.lwjgl.system.linux.liburing;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.CustomBuffer;
import org.lwjgl.system.Library;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.linux.CMsghdr;
import org.lwjgl.system.linux.EpollEvent;
import org.lwjgl.system.linux.IOVec;
import org.lwjgl.system.linux.KernelTimespec;
import org.lwjgl.system.linux.Msghdr;
import org.lwjgl.system.linux.OpenHow;
import org.lwjgl.system.linux.Sockaddr;
import org.lwjgl.system.linux.Statx;
import org.lwjgl.system.linux.liburing.IOURingRestriction;

/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/LibURing.class */
public class LibURing {
    public static final long LIBURING_UDATA_TIMEOUT = -1;

    public static native long nio_uring_get_probe_ring(long j);

    public static native long nio_uring_get_probe();

    public static native void nio_uring_free_probe(long j);

    public static native int nio_uring_opcode_supported(long j, int i);

    public static native int nio_uring_queue_init_mem(int i, long j, long j2, long j3, long j4);

    public static native int nio_uring_queue_init_params(int i, long j, long j2);

    public static native int nio_uring_queue_init(int i, long j, int i2);

    public static native int nio_uring_queue_mmap(int i, long j, long j2);

    public static native int nio_uring_ring_dontfork(long j);

    public static native void nio_uring_queue_exit(long j);

    public static native int nio_uring_peek_batch_cqe(long j, long j2, int i);

    public static native int nio_uring_wait_cqes(long j, long j2, int i, long j3, long j4);

    public static native int nio_uring_wait_cqe_timeout(long j, long j2, long j3);

    public static native int nio_uring_submit(long j);

    public static native int nio_uring_submit_and_wait(long j, int i);

    public static native int nio_uring_submit_and_wait_timeout(long j, long j2, int i, long j3, long j4);

    public static native int nio_uring_register_buffers(long j, long j2, int i);

    public static native int nio_uring_register_buffers_tags(long j, long j2, long j3, int i);

    public static native int nio_uring_register_buffers_sparse(long j, int i);

    public static native int nio_uring_register_buffers_update_tag(long j, int i, long j2, long j3, int i2);

    public static native int nio_uring_unregister_buffers(long j);

    public static native int nio_uring_register_files(long j, long j2, int i);

    public static native int nio_uring_register_files_tags(long j, long j2, long j3, int i);

    public static native int nio_uring_register_files_sparse(long j, int i);

    public static native int nio_uring_register_files_update_tag(long j, int i, long j2, long j3, int i2);

    public static native int nio_uring_unregister_files(long j);

    public static native int nio_uring_register_files_update(long j, int i, long j2, int i2);

    public static native int nio_uring_register_eventfd(long j, int i);

    public static native int nio_uring_register_eventfd_async(long j, int i);

    public static native int nio_uring_unregister_eventfd(long j);

    public static native int nio_uring_register_probe(long j, long j2, int i);

    public static native int nio_uring_register_personality(long j);

    public static native int nio_uring_unregister_personality(long j, int i);

    public static native int nio_uring_register_restrictions(long j, long j2, int i);

    public static native int nio_uring_enable_rings(long j);

    public static native int n__io_uring_sqring_wait(long j);

    public static native int nio_uring_register_iowq_aff(long j, long j2, long j3);

    public static native int nio_uring_unregister_iowq_aff(long j);

    public static native int nio_uring_register_iowq_max_workers(long j, long j2);

    public static native int nio_uring_register_ring_fd(long j);

    public static native int nio_uring_unregister_ring_fd(long j);

    public static native int nio_uring_close_ring_fd(long j);

    public static native int nio_uring_register_buf_ring(long j, long j2, int i);

    public static native int nio_uring_unregister_buf_ring(long j, int i);

    public static native int nio_uring_buf_ring_head(long j, int i, long j2);

    public static native int nio_uring_register_sync_cancel(long j, long j2);

    public static native int nio_uring_register_file_alloc_range(long j, int i, int i2);

    public static native int nio_uring_register_napi(long j, long j2);

    public static native int nio_uring_unregister_napi(long j, long j2);

    public static native int nio_uring_get_events(long j);

    public static native int nio_uring_submit_and_get_events(long j);

    public static native int nio_uring_enter(int i, int i2, int i3, int i4, long j);

    public static native int nio_uring_enter2(int i, int i2, int i3, int i4, long j, long j2);

    public static native int nio_uring_setup(int i, long j);

    public static native int io_uring_register(@NativeType("unsigned int") int i, @NativeType("unsigned int") int i2, @NativeType("void *") long j, @NativeType("unsigned int") int i3);

    public static native long nio_uring_setup_buf_ring(long j, int i, int i2, int i3, long j2);

    public static native int nio_uring_free_buf_ring(long j, long j2, int i, int i2);

    public static native void nio_uring_cqe_seen(long j, long j2);

    public static native void nio_uring_sqe_set_data(long j, long j2);

    public static native long nio_uring_cqe_get_data(long j);

    public static native void nio_uring_sqe_set_data64(long j, long j2);

    public static native long nio_uring_cqe_get_data64(long j);

    public static native void nio_uring_sqe_set_flags(long j, int i);

    public static native void nio_uring_prep_splice(long j, int i, long j2, int i2, long j3, int i3, int i4);

    public static native void nio_uring_prep_tee(long j, int i, int i2, int i3, int i4);

    public static native void nio_uring_prep_readv(long j, int i, long j2, int i2, int i3);

    public static native void nio_uring_prep_readv2(long j, int i, long j2, int i2, int i3, int i4);

    public static native void nio_uring_prep_read_fixed(long j, int i, long j2, int i2, int i3, int i4);

    public static native void nio_uring_prep_writev(long j, int i, long j2, int i2, int i3);

    public static native void nio_uring_prep_writev2(long j, int i, long j2, int i2, int i3, int i4);

    public static native void nio_uring_prep_write_fixed(long j, int i, long j2, int i2, int i3, int i4);

    public static native void nio_uring_prep_recvmsg(long j, int i, long j2, int i2);

    public static native void nio_uring_prep_recvmsg_multishot(long j, int i, long j2, int i2);

    public static native void nio_uring_prep_sendmsg(long j, int i, long j2, int i2);

    public static native void nio_uring_prep_poll_add(long j, int i, int i2);

    public static native void nio_uring_prep_poll_multishot(long j, int i, int i2);

    public static native void nio_uring_prep_poll_remove(long j, long j2);

    public static native void nio_uring_prep_poll_update(long j, long j2, long j3, int i, int i2);

    public static native void nio_uring_prep_fsync(long j, int i, int i2);

    public static native void nio_uring_prep_nop(long j);

    public static native void nio_uring_prep_timeout(long j, long j2, int i, int i2);

    public static native void nio_uring_prep_timeout_remove(long j, long j2, int i);

    public static native void nio_uring_prep_timeout_update(long j, long j2, long j3, int i);

    public static native void nio_uring_prep_accept(long j, int i, long j2, long j3, int i2);

    public static native void nio_uring_prep_accept_direct(long j, int i, long j2, long j3, int i2, int i3);

    public static native void nio_uring_prep_multishot_accept(long j, int i, long j2, long j3, int i2);

    public static native void nio_uring_prep_multishot_accept_direct(long j, int i, long j2, long j3, int i2);

    public static native void nio_uring_prep_cancel64(long j, long j2, int i);

    public static native void nio_uring_prep_cancel(long j, long j2, int i);

    public static native void nio_uring_prep_cancel_fd(long j, int i, int i2);

    public static native void nio_uring_prep_link_timeout(long j, long j2, int i);

    public static native void nio_uring_prep_connect(long j, int i, long j2, int i2);

    public static native void nio_uring_prep_files_update(long j, long j2, int i, int i2);

    public static native void nio_uring_prep_fallocate(long j, int i, int i2, long j2, long j3);

    public static native void nio_uring_prep_openat(long j, int i, long j2, int i2, int i3);

    public static native void nio_uring_prep_openat_direct(long j, int i, long j2, int i2, int i3, int i4);

    public static native void nio_uring_prep_close(long j, int i);

    public static native void nio_uring_prep_close_direct(long j, int i);

    public static native void nio_uring_prep_read(long j, int i, long j2, int i2, int i3);

    public static native void nio_uring_prep_read_multishot(long j, int i, int i2, long j2, int i3);

    public static native void nio_uring_prep_write(long j, int i, long j2, int i2, int i3);

    public static native void nio_uring_prep_statx(long j, int i, long j2, int i2, int i3, long j3);

    public static native void nio_uring_prep_fadvise(long j, int i, int i2, long j2, int i3);

    public static native void nio_uring_prep_madvise(long j, long j2, long j3, int i);

    public static native void nio_uring_prep_send(long j, int i, long j2, long j3, int i2);

    public static native void nio_uring_prep_send_set_addr(long j, long j2, short s);

    public static native void nio_uring_prep_sendto(long j, int i, long j2, long j3, int i2, long j4, int i3);

    public static native void nio_uring_prep_send_zc(long j, int i, long j2, long j3, int i2, int i3);

    public static native void nio_uring_prep_send_zc_fixed(long j, int i, long j2, long j3, int i2, int i3, int i4);

    public static native void nio_uring_prep_sendmsg_zc(long j, int i, long j2, int i2);

    public static native void nio_uring_prep_recv(long j, int i, long j2, long j3, int i2);

    public static native void nio_uring_prep_recv_multishot(long j, int i, long j2, long j3, int i2);

    public static native long nio_uring_recvmsg_validate(long j, int i, long j2);

    public static native long nio_uring_recvmsg_name(long j);

    public static native long nio_uring_recvmsg_cmsg_firsthdr(long j, long j2);

    public static native long nio_uring_recvmsg_cmsg_nexthdr(long j, long j2, long j3);

    public static native long nio_uring_recvmsg_payload(long j, long j2);

    public static native int nio_uring_recvmsg_payload_length(long j, int i, long j2);

    public static native void nio_uring_prep_openat2(long j, int i, long j2, long j3);

    public static native void nio_uring_prep_openat2_direct(long j, int i, long j2, long j3, int i2);

    public static native void nio_uring_prep_epoll_ctl(long j, int i, int i2, int i3, long j2);

    public static native void nio_uring_prep_provide_buffers(long j, long j2, int i, int i2, int i3, int i4);

    public static native void nio_uring_prep_remove_buffers(long j, int i, int i2);

    public static native void nio_uring_prep_shutdown(long j, int i, int i2);

    public static native void nio_uring_prep_unlinkat(long j, int i, long j2, int i2);

    public static native void nio_uring_prep_unlink(long j, long j2, int i);

    public static native void nio_uring_prep_renameat(long j, int i, long j2, int i2, long j3, int i3);

    public static native void nio_uring_prep_rename(long j, long j2, long j3);

    public static native void nio_uring_prep_sync_file_range(long j, int i, int i2, int i3, int i4);

    public static native void nio_uring_prep_mkdirat(long j, int i, long j2, int i2);

    public static native void nio_uring_prep_mkdir(long j, long j2, int i);

    public static native void nio_uring_prep_symlinkat(long j, long j2, int i, long j3);

    public static native void nio_uring_prep_symlink(long j, long j2, long j3);

    public static native void nio_uring_prep_linkat(long j, int i, long j2, int i2, long j3, int i3);

    public static native void nio_uring_prep_link(long j, long j2, long j3, int i);

    public static native void nio_uring_prep_msg_ring_cqe_flags(long j, int i, int i2, long j2, int i3, int i4);

    public static native void nio_uring_prep_msg_ring(long j, int i, int i2, long j2, int i3);

    public static native void nio_uring_prep_msg_ring_fd(long j, int i, int i2, int i3, long j2, int i4);

    public static native void nio_uring_prep_msg_ring_fd_alloc(long j, int i, int i2, long j2, int i3);

    public static native void nio_uring_prep_getxattr(long j, long j2, long j3, long j4, int i);

    public static native void nio_uring_prep_setxattr(long j, long j2, long j3, long j4, int i, int i2);

    public static native void nio_uring_prep_fgetxattr(long j, int i, long j2, long j3, int i2);

    public static native void nio_uring_prep_fsetxattr(long j, int i, long j2, long j3, int i2, int i3);

    public static native void nio_uring_prep_socket(long j, int i, int i2, int i3, int i4);

    public static native void nio_uring_prep_socket_direct(long j, int i, int i2, int i3, int i4, int i5);

    public static native void nio_uring_prep_socket_direct_alloc(long j, int i, int i2, int i3, int i4);

    public static native void nio_uring_prep_cmd_sock(long j, int i, int i2, int i3, int i4, long j2, int i5);

    public static native void nio_uring_prep_waitid(long j, int i, int i2, long j2, int i3, int i4);

    public static native void nio_uring_prep_futex_wake(long j, long j2, long j3, long j4, int i, int i2);

    public static native void nio_uring_prep_futex_wait(long j, long j2, long j3, long j4, int i, int i2);

    public static native void nio_uring_prep_futex_waitv(long j, long j2, int i, int i2);

    public static native void nio_uring_prep_fixed_fd_install(long j, int i, int i2);

    public static native void nio_uring_prep_ftruncate(long j, int i, long j2);

    public static native int nio_uring_sq_ready(long j);

    public static native int nio_uring_sq_space_left(long j);

    public static native int nio_uring_sqring_wait(long j);

    public static native int nio_uring_cq_ready(long j);

    public static native boolean nio_uring_cq_has_overflow(long j);

    public static native boolean nio_uring_cq_eventfd_enabled(long j);

    public static native int nio_uring_cq_eventfd_toggle(long j, boolean z);

    public static native int nio_uring_wait_cqe_nr(long j, long j2, int i);

    public static native int nio_uring_peek_cqe(long j, long j2);

    public static native int nio_uring_wait_cqe(long j, long j2);

    public static native void nio_uring_buf_ring_advance(long j, int i);

    public static native void nio_uring_buf_ring_cq_advance(long j, long j2, int i);

    public static native int nio_uring_buf_ring_available(long j, long j2, short s);

    public static native long nio_uring_get_sqe(long j);

    public static native int io_uring_mlock_size(@NativeType("unsigned") int i, @NativeType("unsigned") int i2);

    public static native int nio_uring_mlock_size_params(int i, long j);

    public static native int io_uring_major_version();

    public static native int io_uring_minor_version();

    @NativeType("bool")
    public static native boolean io_uring_check_version(int i, int i2);

    static {
        Library.initialize();
    }

    protected LibURing() {
        throw new UnsupportedOperationException();
    }

    @NativeType("struct io_uring_probe *")
    public static IOURingProbe io_uring_get_probe_ring(@NativeType("struct io_uring *") IOURing iOURing) {
        return IOURingProbe.createSafe(nio_uring_get_probe_ring(iOURing.address()));
    }

    @NativeType("struct io_uring_probe *")
    public static IOURingProbe io_uring_get_probe() {
        return IOURingProbe.createSafe(nio_uring_get_probe());
    }

    public static void io_uring_free_probe(@NativeType("struct io_uring_probe *") IOURingProbe iOURingProbe) {
        nio_uring_free_probe(iOURingProbe.address());
    }

    public static int io_uring_opcode_supported(@NativeType("struct io_uring_probe const *") IOURingProbe iOURingProbe, int i) {
        return nio_uring_opcode_supported(iOURingProbe.address(), i);
    }

    public static int io_uring_queue_init_mem(@NativeType("unsigned") int i, @NativeType("struct io_uring *") IOURing iOURing, @NativeType("struct io_uring_params *") IOURingParams iOURingParams, @NativeType("void *") ByteBuffer byteBuffer) {
        return nio_uring_queue_init_mem(i, iOURing.address(), iOURingParams.address(), MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining());
    }

    public static int io_uring_queue_init_params(@NativeType("unsigned") int i, @NativeType("struct io_uring *") IOURing iOURing, @NativeType("struct io_uring_params *") IOURingParams iOURingParams) {
        return nio_uring_queue_init_params(i, iOURing.address(), iOURingParams.address());
    }

    public static int io_uring_queue_init(@NativeType("unsigned") int i, @NativeType("struct io_uring *") IOURing iOURing, @NativeType("unsigned") int i2) {
        return nio_uring_queue_init(i, iOURing.address(), i2);
    }

    public static int io_uring_queue_mmap(int i, @NativeType("struct io_uring_params *") IOURingParams iOURingParams, @NativeType("struct io_uring *") IOURing iOURing) {
        return nio_uring_queue_mmap(i, iOURingParams.address(), iOURing.address());
    }

    public static int io_uring_ring_dontfork(@NativeType("struct io_uring *") IOURing iOURing) {
        return nio_uring_ring_dontfork(iOURing.address());
    }

    public static void io_uring_queue_exit(@NativeType("struct io_uring *") IOURing iOURing) {
        nio_uring_queue_exit(iOURing.address());
    }

    @NativeType("unsigned")
    public static int io_uring_peek_batch_cqe(@NativeType("struct io_uring *") IOURing iOURing, @NativeType("struct io_uring_cqe **") PointerBuffer pointerBuffer) {
        return nio_uring_peek_batch_cqe(iOURing.address(), MemoryUtil.memAddress(pointerBuffer), pointerBuffer.remaining());
    }

    public static int io_uring_wait_cqes(@NativeType("struct io_uring *") IOURing iOURing, @NativeType("struct io_uring_cqe **") PointerBuffer pointerBuffer, @NativeType("struct __kernel_timespec *") KernelTimespec kernelTimespec, @NativeType("sigset_t *") long j) {
        return nio_uring_wait_cqes(iOURing.address(), MemoryUtil.memAddress(pointerBuffer), pointerBuffer.remaining(), MemoryUtil.memAddressSafe(kernelTimespec), j);
    }

    public static int io_uring_wait_cqe_timeout(@NativeType("struct io_uring *") IOURing iOURing, @NativeType("struct io_uring_cqe **") PointerBuffer pointerBuffer, @NativeType("struct __kernel_timespec *") KernelTimespec kernelTimespec) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
        }
        return nio_uring_wait_cqe_timeout(iOURing.address(), MemoryUtil.memAddress(pointerBuffer), MemoryUtil.memAddressSafe(kernelTimespec));
    }

    public static int io_uring_submit(@NativeType("struct io_uring *") IOURing iOURing) {
        return nio_uring_submit(iOURing.address());
    }

    public static int io_uring_submit_and_wait(@NativeType("struct io_uring *") IOURing iOURing, @NativeType("unsigned") int i) {
        return nio_uring_submit_and_wait(iOURing.address(), i);
    }

    public static int io_uring_submit_and_wait_timeout(@NativeType("struct io_uring *") IOURing iOURing, @NativeType("struct io_uring_cqe **") PointerBuffer pointerBuffer, @NativeType("struct __kernel_timespec *") KernelTimespec kernelTimespec, @NativeType("sigset_t *") long j) {
        return nio_uring_submit_and_wait_timeout(iOURing.address(), MemoryUtil.memAddress(pointerBuffer), pointerBuffer.remaining(), MemoryUtil.memAddressSafe(kernelTimespec), j);
    }

    public static int io_uring_register_buffers(@NativeType("struct io_uring *") IOURing iOURing, @NativeType("struct iovec const *") IOVec.Buffer buffer) {
        return nio_uring_register_buffers(iOURing.address(), buffer.address(), buffer.remaining());
    }

    public static int io_uring_register_buffers_tags(@NativeType("struct io_uring *") IOURing iOURing, @NativeType("struct iovec const *") IOVec.Buffer buffer, @NativeType("__u64 const *") LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, buffer.remaining());
        }
        return nio_uring_register_buffers_tags(iOURing.address(), buffer.address(), MemoryUtil.memAddress(longBuffer), buffer.remaining());
    }

    public static int io_uring_register_buffers_sparse(@NativeType("struct io_uring *") IOURing iOURing, @NativeType("unsigned") int i) {
        return nio_uring_register_buffers_sparse(iOURing.address(), i);
    }

    public static int io_uring_register_buffers_update_tag(@NativeType("struct io_uring *") IOURing iOURing, @NativeType("unsigned") int i, @NativeType("struct iovec const *") IOVec.Buffer buffer, @NativeType("__u64 const *") LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, buffer.remaining());
        }
        return nio_uring_register_buffers_update_tag(iOURing.address(), i, buffer.address(), MemoryUtil.memAddress(longBuffer), buffer.remaining());
    }

    public static int io_uring_unregister_buffers(@NativeType("struct io_uring *") IOURing iOURing) {
        return nio_uring_unregister_buffers(iOURing.address());
    }

    public static int io_uring_register_files(@NativeType("struct io_uring *") IOURing iOURing, @NativeType("int const *") IntBuffer intBuffer) {
        return nio_uring_register_files(iOURing.address(), MemoryUtil.memAddress(intBuffer), intBuffer.remaining());
    }

    public static int io_uring_register_files_tags(@NativeType("struct io_uring *") IOURing iOURing, @NativeType("int const *") IntBuffer intBuffer, @NativeType("__u64 const *") LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, intBuffer.remaining());
        }
        return nio_uring_register_files_tags(iOURing.address(), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(longBuffer), intBuffer.remaining());
    }

    public static int io_uring_register_files_sparse(@NativeType("struct io_uring *") IOURing iOURing, @NativeType("unsigned") int i) {
        return nio_uring_register_files_sparse(iOURing.address(), i);
    }

    public static int io_uring_register_files_update_tag(@NativeType("struct io_uring *") IOURing iOURing, @NativeType("unsigned") int i, @NativeType("int const *") IntBuffer intBuffer, @NativeType("__u64 const *") LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, intBuffer.remaining());
        }
        return nio_uring_register_files_update_tag(iOURing.address(), i, MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(longBuffer), intBuffer.remaining());
    }

    public static int io_uring_unregister_files(@NativeType("struct io_uring *") IOURing iOURing) {
        return nio_uring_unregister_files(iOURing.address());
    }

    public static int io_uring_register_files_update(@NativeType("struct io_uring *") IOURing iOURing, @NativeType("unsigned") int i, @NativeType("int const *") IntBuffer intBuffer) {
        return nio_uring_register_files_update(iOURing.address(), i, MemoryUtil.memAddress(intBuffer), intBuffer.remaining());
    }

    public static int io_uring_register_eventfd(@NativeType("struct io_uring *") IOURing iOURing, int i) {
        return nio_uring_register_eventfd(iOURing.address(), i);
    }

    public static int io_uring_register_eventfd_async(@NativeType("struct io_uring *") IOURing iOURing, int i) {
        return nio_uring_register_eventfd_async(iOURing.address(), i);
    }

    public static int io_uring_unregister_eventfd(@NativeType("struct io_uring *") IOURing iOURing) {
        return nio_uring_unregister_eventfd(iOURing.address());
    }

    public static int io_uring_register_probe(@NativeType("struct io_uring *") IOURing iOURing, @NativeType("struct io_uring_probe *") IOURingProbe iOURingProbe, @NativeType("unsigned") int i) {
        return nio_uring_register_probe(iOURing.address(), iOURingProbe.address(), i);
    }

    public static int io_uring_register_personality(@NativeType("struct io_uring *") IOURing iOURing) {
        return nio_uring_register_personality(iOURing.address());
    }

    public static int io_uring_unregister_personality(@NativeType("struct io_uring *") IOURing iOURing, int i) {
        return nio_uring_unregister_personality(iOURing.address(), i);
    }

    public static int io_uring_register_restrictions(@NativeType("struct io_uring *") IOURing iOURing, @NativeType("struct io_uring_restriction *") IOURingRestriction.Buffer buffer) {
        return nio_uring_register_restrictions(iOURing.address(), buffer.address(), buffer.remaining());
    }

    public static int io_uring_enable_rings(@NativeType("struct io_uring *") IOURing iOURing) {
        return nio_uring_enable_rings(iOURing.address());
    }

    public static int __io_uring_sqring_wait(@NativeType("struct io_uring *") IOURing iOURing) {
        return n__io_uring_sqring_wait(iOURing.address());
    }

    public static int io_uring_register_iowq_aff(@NativeType("struct io_uring *") IOURing iOURing, @NativeType("size_t") long j, @NativeType("cpu_set_t const *") long j2) {
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        return nio_uring_register_iowq_aff(iOURing.address(), j, j2);
    }

    public static int io_uring_unregister_iowq_aff(@NativeType("struct io_uring *") IOURing iOURing) {
        return nio_uring_unregister_iowq_aff(iOURing.address());
    }

    public static int io_uring_register_iowq_max_workers(@NativeType("struct io_uring *") IOURing iOURing, @NativeType("unsigned int *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 2);
        }
        return nio_uring_register_iowq_max_workers(iOURing.address(), MemoryUtil.memAddress(intBuffer));
    }

    public static int io_uring_register_ring_fd(@NativeType("struct io_uring *") IOURing iOURing) {
        return nio_uring_register_ring_fd(iOURing.address());
    }

    public static int io_uring_unregister_ring_fd(@NativeType("struct io_uring *") IOURing iOURing) {
        return nio_uring_unregister_ring_fd(iOURing.address());
    }

    public static int io_uring_close_ring_fd(@NativeType("struct io_uring *") IOURing iOURing) {
        return nio_uring_close_ring_fd(iOURing.address());
    }

    public static int io_uring_register_buf_ring(@NativeType("struct io_uring *") IOURing iOURing, @NativeType("struct io_uring_buf_reg *") IOURingBufReg iOURingBufReg, @NativeType("unsigned int") int i) {
        return nio_uring_register_buf_ring(iOURing.address(), iOURingBufReg.address(), i);
    }

    public static int io_uring_unregister_buf_ring(@NativeType("struct io_uring *") IOURing iOURing, int i) {
        return nio_uring_unregister_buf_ring(iOURing.address(), i);
    }

    public static int io_uring_buf_ring_head(@NativeType("struct io_uring *") IOURing iOURing, int i, @NativeType("unsigned short *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 1);
        }
        return nio_uring_buf_ring_head(iOURing.address(), i, MemoryUtil.memAddress(shortBuffer));
    }

    public static int io_uring_register_sync_cancel(@NativeType("struct io_uring *") IOURing iOURing, @NativeType("struct io_uring_sync_cancel_reg *") IOURingSyncCancelReg iOURingSyncCancelReg) {
        return nio_uring_register_sync_cancel(iOURing.address(), iOURingSyncCancelReg.address());
    }

    public static int io_uring_register_file_alloc_range(@NativeType("struct io_uring *") IOURing iOURing, @NativeType("unsigned") int i, @NativeType("unsigned") int i2) {
        return nio_uring_register_file_alloc_range(iOURing.address(), i, i2);
    }

    public static int io_uring_register_napi(@NativeType("struct io_uring *") IOURing iOURing, @NativeType("struct io_uring_napi *") IOURingNAPI iOURingNAPI) {
        return nio_uring_register_napi(iOURing.address(), iOURingNAPI.address());
    }

    public static int io_uring_unregister_napi(@NativeType("struct io_uring *") IOURing iOURing, @NativeType("struct io_uring_napi *") IOURingNAPI iOURingNAPI) {
        return nio_uring_unregister_napi(iOURing.address(), iOURingNAPI.address());
    }

    public static int io_uring_get_events(@NativeType("struct io_uring *") IOURing iOURing) {
        return nio_uring_get_events(iOURing.address());
    }

    public static int io_uring_submit_and_get_events(@NativeType("struct io_uring *") IOURing iOURing) {
        return nio_uring_submit_and_get_events(iOURing.address());
    }

    public static int io_uring_enter(@NativeType("unsigned int") int i, @NativeType("unsigned int") int i2, @NativeType("unsigned int") int i3, @NativeType("unsigned int") int i4, @NativeType("sigset_t *") long j) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return nio_uring_enter(i, i2, i3, i4, j);
    }

    public static int io_uring_enter2(@NativeType("unsigned int") int i, @NativeType("unsigned int") int i2, @NativeType("unsigned int") int i3, @NativeType("unsigned int") int i4, @NativeType("sigset_t *") long j, @NativeType("size_t") long j2) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return nio_uring_enter2(i, i2, i3, i4, j, j2);
    }

    public static int io_uring_setup(@NativeType("unsigned int") int i, @NativeType("struct io_uring_params *") IOURingParams iOURingParams) {
        return nio_uring_setup(i, iOURingParams.address());
    }

    @NativeType("struct io_uring_buf_ring *")
    public static IOURingBufRing io_uring_setup_buf_ring(@NativeType("struct io_uring *") IOURing iOURing, @NativeType("unsigned int") int i, int i2, @NativeType("unsigned int") int i3, @NativeType("int *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        return IOURingBufRing.createSafe(nio_uring_setup_buf_ring(iOURing.address(), i, i2, i3, MemoryUtil.memAddress(intBuffer)));
    }

    public static int io_uring_free_buf_ring(@NativeType("struct io_uring *") IOURing iOURing, @NativeType("struct io_uring_buf_ring *") IOURingBufRing iOURingBufRing, @NativeType("unsigned int") int i, int i2) {
        return nio_uring_free_buf_ring(iOURing.address(), iOURingBufRing.address(), i, i2);
    }

    public static void io_uring_cqe_seen(@NativeType("struct io_uring *") IOURing iOURing, @NativeType("struct io_uring_cqe *") IOURingCQE iOURingCQE) {
        nio_uring_cqe_seen(iOURing.address(), iOURingCQE.address());
    }

    public static void io_uring_sqe_set_data(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, @NativeType("void *") long j) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        nio_uring_sqe_set_data(iOURingSQE.address(), j);
    }

    @NativeType("void *")
    public static long io_uring_cqe_get_data(@NativeType("struct io_uring_cqe const *") IOURingCQE iOURingCQE) {
        return nio_uring_cqe_get_data(iOURingCQE.address());
    }

    public static void io_uring_sqe_set_data64(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, @NativeType("__u64") long j) {
        nio_uring_sqe_set_data64(iOURingSQE.address(), j);
    }

    @NativeType("__u64")
    public static long io_uring_cqe_get_data64(@NativeType("struct io_uring_cqe const *") IOURingCQE iOURingCQE) {
        return nio_uring_cqe_get_data64(iOURingCQE.address());
    }

    public static void io_uring_sqe_set_flags(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, @NativeType("unsigned int") int i) {
        nio_uring_sqe_set_flags(iOURingSQE.address(), i);
    }

    public static void io_uring_prep_splice(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("int64_t") long j, int i2, @NativeType("int64_t") long j2, @NativeType("unsigned int") int i3, @NativeType("unsigned int") int i4) {
        nio_uring_prep_splice(iOURingSQE.address(), i, j, i2, j2, i3, i4);
    }

    public static void io_uring_prep_tee(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, int i2, @NativeType("unsigned int") int i3, @NativeType("unsigned int") int i4) {
        nio_uring_prep_tee(iOURingSQE.address(), i, i2, i3, i4);
    }

    public static void io_uring_prep_readv(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("struct iovec const *") IOVec.Buffer buffer, int i2) {
        nio_uring_prep_readv(iOURingSQE.address(), i, buffer.address(), buffer.remaining(), i2);
    }

    public static void io_uring_prep_readv2(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("struct iovec const *") IOVec.Buffer buffer, int i2, int i3) {
        nio_uring_prep_readv2(iOURingSQE.address(), i, buffer.address(), buffer.remaining(), i2, i3);
    }

    public static void io_uring_prep_read_fixed(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("void *") ByteBuffer byteBuffer, int i2, int i3) {
        nio_uring_prep_read_fixed(iOURingSQE.address(), i, MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining(), i2, i3);
    }

    public static void io_uring_prep_writev(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("struct iovec const *") IOVec.Buffer buffer, int i2) {
        nio_uring_prep_writev(iOURingSQE.address(), i, buffer.address(), buffer.remaining(), i2);
    }

    public static void io_uring_prep_writev2(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("struct iovec const *") IOVec.Buffer buffer, int i2, int i3) {
        nio_uring_prep_writev2(iOURingSQE.address(), i, buffer.address(), buffer.remaining(), i2, i3);
    }

    public static void io_uring_prep_write_fixed(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("void const *") ByteBuffer byteBuffer, int i2, int i3) {
        nio_uring_prep_write_fixed(iOURingSQE.address(), i, MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining(), i2, i3);
    }

    public static void io_uring_prep_recvmsg(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("struct msghdr *") Msghdr msghdr, @NativeType("unsigned int") int i2) {
        nio_uring_prep_recvmsg(iOURingSQE.address(), i, msghdr.address(), i2);
    }

    public static void io_uring_prep_recvmsg_multishot(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("struct msghdr *") Msghdr msghdr, @NativeType("unsigned") int i2) {
        nio_uring_prep_recvmsg_multishot(iOURingSQE.address(), i, msghdr.address(), i2);
    }

    public static void io_uring_prep_sendmsg(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("struct msghdr const *") Msghdr msghdr, @NativeType("unsigned int") int i2) {
        if (Checks.CHECKS) {
            Msghdr.validate(msghdr.address());
        }
        nio_uring_prep_sendmsg(iOURingSQE.address(), i, msghdr.address(), i2);
    }

    public static void io_uring_prep_poll_add(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("unsigned int") int i2) {
        nio_uring_prep_poll_add(iOURingSQE.address(), i, i2);
    }

    public static void io_uring_prep_poll_multishot(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("unsigned int") int i2) {
        nio_uring_prep_poll_multishot(iOURingSQE.address(), i, i2);
    }

    public static void io_uring_prep_poll_remove(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, @NativeType("__u64") long j) {
        nio_uring_prep_poll_remove(iOURingSQE.address(), j);
    }

    public static void io_uring_prep_poll_update(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, @NativeType("__u64") long j, @NativeType("__u64") long j2, @NativeType("unsigned int") int i, @NativeType("unsigned int") int i2) {
        nio_uring_prep_poll_update(iOURingSQE.address(), j, j2, i, i2);
    }

    public static void io_uring_prep_fsync(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("unsigned int") int i2) {
        nio_uring_prep_fsync(iOURingSQE.address(), i, i2);
    }

    public static void io_uring_prep_nop(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE) {
        nio_uring_prep_nop(iOURingSQE.address());
    }

    public static void io_uring_prep_timeout(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, @NativeType("struct __kernel_timespec *") KernelTimespec kernelTimespec, @NativeType("unsigned int") int i, @NativeType("unsigned int") int i2) {
        nio_uring_prep_timeout(iOURingSQE.address(), kernelTimespec.address(), i, i2);
    }

    public static void io_uring_prep_timeout_remove(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, @NativeType("__u64") long j, @NativeType("unsigned int") int i) {
        nio_uring_prep_timeout_remove(iOURingSQE.address(), j, i);
    }

    public static void io_uring_prep_timeout_update(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, @NativeType("struct __kernel_timespec *") KernelTimespec kernelTimespec, @NativeType("__u64") long j, @NativeType("unsigned int") int i) {
        nio_uring_prep_timeout_update(iOURingSQE.address(), kernelTimespec.address(), j, i);
    }

    public static void io_uring_prep_accept(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("struct sockaddr *") Sockaddr sockaddr, @NativeType("socklen_t *") IntBuffer intBuffer, int i2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nio_uring_prep_accept(iOURingSQE.address(), i, sockaddr.address(), MemoryUtil.memAddress(intBuffer), i2);
    }

    public static void io_uring_prep_accept_direct(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("struct sockaddr *") Sockaddr sockaddr, @NativeType("socklen_t *") IntBuffer intBuffer, int i2, @NativeType("unsigned int") int i3) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nio_uring_prep_accept_direct(iOURingSQE.address(), i, sockaddr.address(), MemoryUtil.memAddress(intBuffer), i2, i3);
    }

    public static void io_uring_prep_multishot_accept(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("struct sockaddr *") Sockaddr sockaddr, @NativeType("socklen_t *") IntBuffer intBuffer, int i2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nio_uring_prep_multishot_accept(iOURingSQE.address(), i, sockaddr.address(), MemoryUtil.memAddress(intBuffer), i2);
    }

    public static void io_uring_prep_multishot_accept_direct(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("struct sockaddr *") Sockaddr sockaddr, @NativeType("socklen_t *") IntBuffer intBuffer, int i2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nio_uring_prep_multishot_accept_direct(iOURingSQE.address(), i, sockaddr.address(), MemoryUtil.memAddress(intBuffer), i2);
    }

    public static void io_uring_prep_cancel64(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, @NativeType("__u64") long j, int i) {
        nio_uring_prep_cancel64(iOURingSQE.address(), j, i);
    }

    public static void io_uring_prep_cancel(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, @NativeType("void *") long j, int i) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        nio_uring_prep_cancel(iOURingSQE.address(), j, i);
    }

    public static void io_uring_prep_cancel_fd(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("unsigned int") int i2) {
        nio_uring_prep_cancel_fd(iOURingSQE.address(), i, i2);
    }

    public static void io_uring_prep_link_timeout(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, @NativeType("struct __kernel_timespec *") KernelTimespec kernelTimespec, @NativeType("unsigned int") int i) {
        nio_uring_prep_link_timeout(iOURingSQE.address(), kernelTimespec.address(), i);
    }

    public static void io_uring_prep_connect(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("struct sockaddr const *") Sockaddr sockaddr, @NativeType("socklen_t") int i2) {
        nio_uring_prep_connect(iOURingSQE.address(), i, sockaddr.address(), i2);
    }

    public static void io_uring_prep_files_update(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, @NativeType("int *") IntBuffer intBuffer, int i) {
        nio_uring_prep_files_update(iOURingSQE.address(), MemoryUtil.memAddress(intBuffer), intBuffer.remaining(), i);
    }

    public static void io_uring_prep_fallocate(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, int i2, @NativeType("off_t") long j, @NativeType("off_t") long j2) {
        nio_uring_prep_fallocate(iOURingSQE.address(), i, i2, j, j2);
    }

    public static void io_uring_prep_openat(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("char const *") ByteBuffer byteBuffer, int i2, int i3) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        nio_uring_prep_openat(iOURingSQE.address(), i, MemoryUtil.memAddress(byteBuffer), i2, i3);
    }

    public static void io_uring_prep_openat(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("char const *") CharSequence charSequence, int i2, int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            nio_uring_prep_openat(iOURingSQE.address(), i, stackGet.getPointerAddress(), i2, i3);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void io_uring_prep_openat_direct(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("char const *") ByteBuffer byteBuffer, int i2, int i3, @NativeType("unsigned int") int i4) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        nio_uring_prep_openat_direct(iOURingSQE.address(), i, MemoryUtil.memAddress(byteBuffer), i2, i3, i4);
    }

    public static void io_uring_prep_openat_direct(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("char const *") CharSequence charSequence, int i2, int i3, @NativeType("unsigned int") int i4) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            nio_uring_prep_openat_direct(iOURingSQE.address(), i, stackGet.getPointerAddress(), i2, i3, i4);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void io_uring_prep_close(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i) {
        nio_uring_prep_close(iOURingSQE.address(), i);
    }

    public static void io_uring_prep_close_direct(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, @NativeType("unsigned int") int i) {
        nio_uring_prep_close_direct(iOURingSQE.address(), i);
    }

    public static void io_uring_prep_read(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("void *") ByteBuffer byteBuffer, int i2) {
        nio_uring_prep_read(iOURingSQE.address(), i, MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining(), i2);
    }

    public static void io_uring_prep_read_multishot(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("unsigned int") int i2, @NativeType("__u64") long j, int i3) {
        nio_uring_prep_read_multishot(iOURingSQE.address(), i, i2, j, i3);
    }

    public static void io_uring_prep_write(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("void const *") ByteBuffer byteBuffer, int i2) {
        nio_uring_prep_write(iOURingSQE.address(), i, MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining(), i2);
    }

    public static void io_uring_prep_statx(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("char const *") ByteBuffer byteBuffer, int i2, @NativeType("unsigned int") int i3, @NativeType("struct statx *") Statx statx) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        nio_uring_prep_statx(iOURingSQE.address(), i, MemoryUtil.memAddress(byteBuffer), i2, i3, statx.address());
    }

    public static void io_uring_prep_statx(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("char const *") CharSequence charSequence, int i2, @NativeType("unsigned int") int i3, @NativeType("struct statx *") Statx statx) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            nio_uring_prep_statx(iOURingSQE.address(), i, stackGet.getPointerAddress(), i2, i3, statx.address());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void io_uring_prep_fadvise(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, int i2, @NativeType("off_t") long j, int i3) {
        nio_uring_prep_fadvise(iOURingSQE.address(), i, i2, j, i3);
    }

    public static void io_uring_prep_madvise(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, @NativeType("void *") ByteBuffer byteBuffer, int i) {
        nio_uring_prep_madvise(iOURingSQE.address(), MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining(), i);
    }

    public static void io_uring_prep_send(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("void const *") ByteBuffer byteBuffer, int i2) {
        nio_uring_prep_send(iOURingSQE.address(), i, MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining(), i2);
    }

    public static void io_uring_prep_send_set_addr(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, @NativeType("struct sockaddr const *") Sockaddr sockaddr, @NativeType("__u16") short s) {
        nio_uring_prep_send_set_addr(iOURingSQE.address(), sockaddr.address(), s);
    }

    public static void io_uring_prep_sendto(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("void const *") ByteBuffer byteBuffer, int i2, @NativeType("struct sockaddr const *") Sockaddr sockaddr, @NativeType("socklen_t") int i3) {
        nio_uring_prep_sendto(iOURingSQE.address(), i, MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining(), i2, sockaddr.address(), i3);
    }

    public static void io_uring_prep_send_zc(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("void const *") ByteBuffer byteBuffer, int i2, @NativeType("unsigned") int i3) {
        nio_uring_prep_send_zc(iOURingSQE.address(), i, MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining(), i2, i3);
    }

    public static void io_uring_prep_send_zc_fixed(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("void const *") ByteBuffer byteBuffer, int i2, @NativeType("unsigned") int i3, @NativeType("unsigned") int i4) {
        nio_uring_prep_send_zc_fixed(iOURingSQE.address(), i, MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining(), i2, i3, i4);
    }

    public static void io_uring_prep_sendmsg_zc(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("struct msghdr const *") Msghdr msghdr, @NativeType("unsigned") int i2) {
        if (Checks.CHECKS) {
            Msghdr.validate(msghdr.address());
        }
        nio_uring_prep_sendmsg_zc(iOURingSQE.address(), i, msghdr.address(), i2);
    }

    public static void io_uring_prep_recv(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("void *") ByteBuffer byteBuffer, int i2) {
        nio_uring_prep_recv(iOURingSQE.address(), i, MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining(), i2);
    }

    public static void io_uring_prep_recv_multishot(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("void *") ByteBuffer byteBuffer, int i2) {
        nio_uring_prep_recv_multishot(iOURingSQE.address(), i, MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining(), i2);
    }

    @NativeType("struct io_uring_recvmsg_out *")
    public static IOURingRecvmsgOut io_uring_recvmsg_validate(@NativeType("void *") ByteBuffer byteBuffer, @NativeType("struct msghdr *") Msghdr msghdr) {
        return IOURingRecvmsgOut.createSafe(nio_uring_recvmsg_validate(MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining(), msghdr.address()));
    }

    @NativeType("void *")
    public static long io_uring_recvmsg_name(@NativeType("struct io_uring_recvmsg_out *") IOURingRecvmsgOut iOURingRecvmsgOut) {
        return nio_uring_recvmsg_name(iOURingRecvmsgOut.address());
    }

    @NativeType("struct cmsghdr *")
    public static CMsghdr io_uring_recvmsg_cmsg_firsthdr(@NativeType("struct io_uring_recvmsg_out *") IOURingRecvmsgOut iOURingRecvmsgOut, @NativeType("struct msghdr *") Msghdr msghdr) {
        return CMsghdr.createSafe(nio_uring_recvmsg_cmsg_firsthdr(iOURingRecvmsgOut.address(), msghdr.address()));
    }

    @NativeType("struct cmsghdr *")
    public static CMsghdr io_uring_recvmsg_cmsg_nexthdr(@NativeType("struct io_uring_recvmsg_out *") IOURingRecvmsgOut iOURingRecvmsgOut, @NativeType("struct msghdr *") Msghdr msghdr, @NativeType("struct cmsghdr *") CMsghdr cMsghdr) {
        return CMsghdr.createSafe(nio_uring_recvmsg_cmsg_nexthdr(iOURingRecvmsgOut.address(), msghdr.address(), cMsghdr.address()));
    }

    @NativeType("void *")
    public static long io_uring_recvmsg_payload(@NativeType("struct io_uring_recvmsg_out *") IOURingRecvmsgOut iOURingRecvmsgOut, @NativeType("struct msghdr *") Msghdr msghdr) {
        return nio_uring_recvmsg_payload(iOURingRecvmsgOut.address(), msghdr.address());
    }

    @NativeType("unsigned int")
    public static int io_uring_recvmsg_payload_length(@NativeType("struct io_uring_recvmsg_out *") IOURingRecvmsgOut iOURingRecvmsgOut, int i, @NativeType("struct msghdr *") Msghdr msghdr) {
        return nio_uring_recvmsg_payload_length(iOURingRecvmsgOut.address(), i, msghdr.address());
    }

    public static void io_uring_prep_openat2(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("char const *") ByteBuffer byteBuffer, @NativeType("struct open_how *") OpenHow openHow) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        nio_uring_prep_openat2(iOURingSQE.address(), i, MemoryUtil.memAddress(byteBuffer), openHow.address());
    }

    public static void io_uring_prep_openat2(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("char const *") CharSequence charSequence, @NativeType("struct open_how *") OpenHow openHow) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            nio_uring_prep_openat2(iOURingSQE.address(), i, stackGet.getPointerAddress(), openHow.address());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void io_uring_prep_openat2_direct(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("char const *") ByteBuffer byteBuffer, @NativeType("struct open_how *") OpenHow openHow, @NativeType("unsigned int") int i2) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        nio_uring_prep_openat2_direct(iOURingSQE.address(), i, MemoryUtil.memAddress(byteBuffer), openHow.address(), i2);
    }

    public static void io_uring_prep_openat2_direct(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("char const *") CharSequence charSequence, @NativeType("struct open_how *") OpenHow openHow, @NativeType("unsigned int") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            nio_uring_prep_openat2_direct(iOURingSQE.address(), i, stackGet.getPointerAddress(), openHow.address(), i2);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void io_uring_prep_epoll_ctl(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, int i2, int i3, @NativeType("struct epoll_event *") EpollEvent epollEvent) {
        nio_uring_prep_epoll_ctl(iOURingSQE.address(), i, i2, i3, epollEvent.address());
    }

    public static void io_uring_prep_provide_buffers(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, @NativeType("void *") ByteBuffer byteBuffer, int i, int i2, int i3) {
        nio_uring_prep_provide_buffers(iOURingSQE.address(), MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining(), i, i2, i3);
    }

    public static void io_uring_prep_remove_buffers(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, int i2) {
        nio_uring_prep_remove_buffers(iOURingSQE.address(), i, i2);
    }

    public static void io_uring_prep_shutdown(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, int i2) {
        nio_uring_prep_shutdown(iOURingSQE.address(), i, i2);
    }

    public static void io_uring_prep_unlinkat(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("char const *") ByteBuffer byteBuffer, int i2) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        nio_uring_prep_unlinkat(iOURingSQE.address(), i, MemoryUtil.memAddress(byteBuffer), i2);
    }

    public static void io_uring_prep_unlinkat(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("char const *") CharSequence charSequence, int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            nio_uring_prep_unlinkat(iOURingSQE.address(), i, stackGet.getPointerAddress(), i2);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void io_uring_prep_unlink(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, @NativeType("char const *") ByteBuffer byteBuffer, int i) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        nio_uring_prep_unlink(iOURingSQE.address(), MemoryUtil.memAddress(byteBuffer), i);
    }

    public static void io_uring_prep_unlink(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, @NativeType("char const *") CharSequence charSequence, int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            nio_uring_prep_unlink(iOURingSQE.address(), stackGet.getPointerAddress(), i);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void io_uring_prep_renameat(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("char const *") ByteBuffer byteBuffer, int i2, @NativeType("char const *") ByteBuffer byteBuffer2, @NativeType("unsigned int") int i3) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
            Checks.checkNT1(byteBuffer2);
        }
        nio_uring_prep_renameat(iOURingSQE.address(), i, MemoryUtil.memAddress(byteBuffer), i2, MemoryUtil.memAddress(byteBuffer2), i3);
    }

    public static void io_uring_prep_renameat(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("char const *") CharSequence charSequence, int i2, @NativeType("char const *") CharSequence charSequence2, @NativeType("unsigned int") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            long pointerAddress = stackGet.getPointerAddress();
            stackGet.nUTF8(charSequence2, true);
            nio_uring_prep_renameat(iOURingSQE.address(), i, pointerAddress, i2, stackGet.getPointerAddress(), i3);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void io_uring_prep_rename(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, @NativeType("char const *") ByteBuffer byteBuffer, @NativeType("char const *") ByteBuffer byteBuffer2) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
            Checks.checkNT1(byteBuffer2);
        }
        nio_uring_prep_rename(iOURingSQE.address(), MemoryUtil.memAddress(byteBuffer), MemoryUtil.memAddress(byteBuffer2));
    }

    public static void io_uring_prep_rename(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, @NativeType("char const *") CharSequence charSequence, @NativeType("char const *") CharSequence charSequence2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            long pointerAddress = stackGet.getPointerAddress();
            stackGet.nUTF8(charSequence2, true);
            nio_uring_prep_rename(iOURingSQE.address(), pointerAddress, stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void io_uring_prep_sync_file_range(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("unsigned int") int i2, int i3, int i4) {
        nio_uring_prep_sync_file_range(iOURingSQE.address(), i, i2, i3, i4);
    }

    public static void io_uring_prep_mkdirat(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("char const *") ByteBuffer byteBuffer, int i2) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        nio_uring_prep_mkdirat(iOURingSQE.address(), i, MemoryUtil.memAddress(byteBuffer), i2);
    }

    public static void io_uring_prep_mkdirat(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("char const *") CharSequence charSequence, int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            nio_uring_prep_mkdirat(iOURingSQE.address(), i, stackGet.getPointerAddress(), i2);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void io_uring_prep_mkdir(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, @NativeType("char const *") ByteBuffer byteBuffer, int i) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        nio_uring_prep_mkdir(iOURingSQE.address(), MemoryUtil.memAddress(byteBuffer), i);
    }

    public static void io_uring_prep_mkdir(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, @NativeType("char const *") CharSequence charSequence, int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            nio_uring_prep_mkdir(iOURingSQE.address(), stackGet.getPointerAddress(), i);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void io_uring_prep_symlinkat(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, @NativeType("char const *") ByteBuffer byteBuffer, int i, @NativeType("char const *") ByteBuffer byteBuffer2) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
            Checks.checkNT1(byteBuffer2);
        }
        nio_uring_prep_symlinkat(iOURingSQE.address(), MemoryUtil.memAddress(byteBuffer), i, MemoryUtil.memAddress(byteBuffer2));
    }

    public static void io_uring_prep_symlinkat(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, @NativeType("char const *") CharSequence charSequence, int i, @NativeType("char const *") CharSequence charSequence2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            long pointerAddress = stackGet.getPointerAddress();
            stackGet.nUTF8(charSequence2, true);
            nio_uring_prep_symlinkat(iOURingSQE.address(), pointerAddress, i, stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void io_uring_prep_symlink(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, @NativeType("char const *") ByteBuffer byteBuffer, @NativeType("char const *") ByteBuffer byteBuffer2) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
            Checks.checkNT1(byteBuffer2);
        }
        nio_uring_prep_symlink(iOURingSQE.address(), MemoryUtil.memAddress(byteBuffer), MemoryUtil.memAddress(byteBuffer2));
    }

    public static void io_uring_prep_symlink(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, @NativeType("char const *") CharSequence charSequence, @NativeType("char const *") CharSequence charSequence2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            long pointerAddress = stackGet.getPointerAddress();
            stackGet.nUTF8(charSequence2, true);
            nio_uring_prep_symlink(iOURingSQE.address(), pointerAddress, stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void io_uring_prep_linkat(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("char const *") ByteBuffer byteBuffer, int i2, @NativeType("char const *") ByteBuffer byteBuffer2, int i3) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
            Checks.checkNT1(byteBuffer2);
        }
        nio_uring_prep_linkat(iOURingSQE.address(), i, MemoryUtil.memAddress(byteBuffer), i2, MemoryUtil.memAddress(byteBuffer2), i3);
    }

    public static void io_uring_prep_linkat(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("char const *") CharSequence charSequence, int i2, @NativeType("char const *") CharSequence charSequence2, int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            long pointerAddress = stackGet.getPointerAddress();
            stackGet.nUTF8(charSequence2, true);
            nio_uring_prep_linkat(iOURingSQE.address(), i, pointerAddress, i2, stackGet.getPointerAddress(), i3);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void io_uring_prep_link(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, @NativeType("char const *") ByteBuffer byteBuffer, @NativeType("char const *") ByteBuffer byteBuffer2, int i) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
            Checks.checkNT1(byteBuffer2);
        }
        nio_uring_prep_link(iOURingSQE.address(), MemoryUtil.memAddress(byteBuffer), MemoryUtil.memAddress(byteBuffer2), i);
    }

    public static void io_uring_prep_link(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, @NativeType("char const *") CharSequence charSequence, @NativeType("char const *") CharSequence charSequence2, int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            long pointerAddress = stackGet.getPointerAddress();
            stackGet.nUTF8(charSequence2, true);
            nio_uring_prep_link(iOURingSQE.address(), pointerAddress, stackGet.getPointerAddress(), i);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void io_uring_prep_msg_ring_cqe_flags(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("unsigned int") int i2, @NativeType("__u64") long j, @NativeType("unsigned int") int i3, @NativeType("unsigned int") int i4) {
        nio_uring_prep_msg_ring_cqe_flags(iOURingSQE.address(), i, i2, j, i3, i4);
    }

    public static void io_uring_prep_msg_ring(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("unsigned int") int i2, @NativeType("__u64") long j, @NativeType("unsigned int") int i3) {
        nio_uring_prep_msg_ring(iOURingSQE.address(), i, i2, j, i3);
    }

    public static void io_uring_prep_msg_ring_fd(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, int i2, int i3, @NativeType("__u64") long j, @NativeType("unsigned int") int i4) {
        nio_uring_prep_msg_ring_fd(iOURingSQE.address(), i, i2, i3, j, i4);
    }

    public static void io_uring_prep_msg_ring_fd_alloc(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, int i2, @NativeType("__u64") long j, @NativeType("unsigned int") int i3) {
        nio_uring_prep_msg_ring_fd_alloc(iOURingSQE.address(), i, i2, j, i3);
    }

    public static void io_uring_prep_getxattr(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, @NativeType("char const *") ByteBuffer byteBuffer, @NativeType("char *") ByteBuffer byteBuffer2, @NativeType("char const *") ByteBuffer byteBuffer3) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
            Checks.checkNT1(byteBuffer3);
        }
        nio_uring_prep_getxattr(iOURingSQE.address(), MemoryUtil.memAddress(byteBuffer), MemoryUtil.memAddress(byteBuffer2), MemoryUtil.memAddress(byteBuffer3), byteBuffer2.remaining());
    }

    public static void io_uring_prep_getxattr(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, @NativeType("char const *") CharSequence charSequence, @NativeType("char *") ByteBuffer byteBuffer, @NativeType("char const *") CharSequence charSequence2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            long pointerAddress = stackGet.getPointerAddress();
            stackGet.nUTF8(charSequence2, true);
            nio_uring_prep_getxattr(iOURingSQE.address(), pointerAddress, MemoryUtil.memAddress(byteBuffer), stackGet.getPointerAddress(), byteBuffer.remaining());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void io_uring_prep_setxattr(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, @NativeType("char const *") ByteBuffer byteBuffer, @NativeType("char const *") ByteBuffer byteBuffer2, @NativeType("char const *") ByteBuffer byteBuffer3, int i) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
            Checks.checkNT1(byteBuffer3);
        }
        nio_uring_prep_setxattr(iOURingSQE.address(), MemoryUtil.memAddress(byteBuffer), MemoryUtil.memAddress(byteBuffer2), MemoryUtil.memAddress(byteBuffer3), i, byteBuffer2.remaining());
    }

    public static void io_uring_prep_setxattr(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, @NativeType("char const *") CharSequence charSequence, @NativeType("char const *") ByteBuffer byteBuffer, @NativeType("char const *") CharSequence charSequence2, int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            long pointerAddress = stackGet.getPointerAddress();
            stackGet.nUTF8(charSequence2, true);
            nio_uring_prep_setxattr(iOURingSQE.address(), pointerAddress, MemoryUtil.memAddress(byteBuffer), stackGet.getPointerAddress(), i, byteBuffer.remaining());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void io_uring_prep_fgetxattr(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("char const *") ByteBuffer byteBuffer, @NativeType("char *") ByteBuffer byteBuffer2) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        nio_uring_prep_fgetxattr(iOURingSQE.address(), i, MemoryUtil.memAddress(byteBuffer), MemoryUtil.memAddress(byteBuffer2), byteBuffer2.remaining());
    }

    public static void io_uring_prep_fgetxattr(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("char const *") CharSequence charSequence, @NativeType("char *") ByteBuffer byteBuffer) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            nio_uring_prep_fgetxattr(iOURingSQE.address(), i, stackGet.getPointerAddress(), MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void io_uring_prep_fsetxattr(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("char const *") ByteBuffer byteBuffer, @NativeType("char const *") ByteBuffer byteBuffer2, int i2) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        nio_uring_prep_fsetxattr(iOURingSQE.address(), i, MemoryUtil.memAddress(byteBuffer), MemoryUtil.memAddress(byteBuffer2), i2, byteBuffer2.remaining());
    }

    public static void io_uring_prep_fsetxattr(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("char const *") CharSequence charSequence, @NativeType("char const *") ByteBuffer byteBuffer, int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            nio_uring_prep_fsetxattr(iOURingSQE.address(), i, stackGet.getPointerAddress(), MemoryUtil.memAddress(byteBuffer), i2, byteBuffer.remaining());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void io_uring_prep_socket(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, int i2, int i3, @NativeType("unsigned int") int i4) {
        nio_uring_prep_socket(iOURingSQE.address(), i, i2, i3, i4);
    }

    public static void io_uring_prep_socket_direct(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, int i2, int i3, @NativeType("unsigned int") int i4, @NativeType("unsigned int") int i5) {
        nio_uring_prep_socket_direct(iOURingSQE.address(), i, i2, i3, i4, i5);
    }

    public static void io_uring_prep_socket_direct_alloc(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, int i2, int i3, @NativeType("unsigned int") int i4) {
        nio_uring_prep_socket_direct_alloc(iOURingSQE.address(), i, i2, i3, i4);
    }

    public static void io_uring_prep_cmd_sock(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, int i2, int i3, int i4, @NativeType("void *") ByteBuffer byteBuffer) {
        nio_uring_prep_cmd_sock(iOURingSQE.address(), i, i2, i3, i4, MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining());
    }

    public static void io_uring_prep_waitid(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, @NativeType("idtype_t") int i, @NativeType("id_t") int i2, @NativeType("siginfo_t *") long j, int i3, @NativeType("unsigned int") int i4) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        nio_uring_prep_waitid(iOURingSQE.address(), i, i2, j, i3, i4);
    }

    public static void io_uring_prep_futex_wake(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, @NativeType("uint32_t *") IntBuffer intBuffer, @NativeType("uint64_t") long j, @NativeType("uint64_t") long j2, @NativeType("uint32_t") int i, @NativeType("unsigned int") int i2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nio_uring_prep_futex_wake(iOURingSQE.address(), MemoryUtil.memAddress(intBuffer), j, j2, i, i2);
    }

    public static void io_uring_prep_futex_wait(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, @NativeType("uint32_t *") IntBuffer intBuffer, @NativeType("uint64_t") long j, @NativeType("uint64_t") long j2, @NativeType("uint32_t") int i, @NativeType("unsigned int") int i2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nio_uring_prep_futex_wait(iOURingSQE.address(), MemoryUtil.memAddress(intBuffer), j, j2, i, i2);
    }

    public static void io_uring_prep_futex_waitv(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, @NativeType("struct futex_waitv *") PointerBuffer pointerBuffer, @NativeType("unsigned int") int i) {
        nio_uring_prep_futex_waitv(iOURingSQE.address(), MemoryUtil.memAddress(pointerBuffer), pointerBuffer.remaining(), i);
    }

    public static void io_uring_prep_fixed_fd_install(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("unsigned int") int i2) {
        nio_uring_prep_fixed_fd_install(iOURingSQE.address(), i, i2);
    }

    public static void io_uring_prep_ftruncate(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE, int i, @NativeType("loff_t") long j) {
        nio_uring_prep_ftruncate(iOURingSQE.address(), i, j);
    }

    @NativeType("unsigned int")
    public static int io_uring_sq_ready(@NativeType("struct io_uring const *") IOURing iOURing) {
        if (Checks.CHECKS) {
            IOURing.validate(iOURing.address());
        }
        return nio_uring_sq_ready(iOURing.address());
    }

    @NativeType("unsigned int")
    public static int io_uring_sq_space_left(@NativeType("struct io_uring const *") IOURing iOURing) {
        if (Checks.CHECKS) {
            IOURing.validate(iOURing.address());
        }
        return nio_uring_sq_space_left(iOURing.address());
    }

    public static int io_uring_sqring_wait(@NativeType("struct io_uring *") IOURing iOURing) {
        return nio_uring_sqring_wait(iOURing.address());
    }

    @NativeType("unsigned int")
    public static int io_uring_cq_ready(@NativeType("struct io_uring const *") IOURing iOURing) {
        if (Checks.CHECKS) {
            IOURing.validate(iOURing.address());
        }
        return nio_uring_cq_ready(iOURing.address());
    }

    @NativeType("bool")
    public static boolean io_uring_cq_has_overflow(@NativeType("struct io_uring const *") IOURing iOURing) {
        if (Checks.CHECKS) {
            IOURing.validate(iOURing.address());
        }
        return nio_uring_cq_has_overflow(iOURing.address());
    }

    @NativeType("bool")
    public static boolean io_uring_cq_eventfd_enabled(@NativeType("struct io_uring const *") IOURing iOURing) {
        if (Checks.CHECKS) {
            IOURing.validate(iOURing.address());
        }
        return nio_uring_cq_eventfd_enabled(iOURing.address());
    }

    public static int io_uring_cq_eventfd_toggle(@NativeType("struct io_uring *") IOURing iOURing, @NativeType("bool") boolean z) {
        return nio_uring_cq_eventfd_toggle(iOURing.address(), z);
    }

    public static int io_uring_wait_cqe_nr(@NativeType("struct io_uring *") IOURing iOURing, @NativeType("struct io_uring_cqe **") PointerBuffer pointerBuffer) {
        return nio_uring_wait_cqe_nr(iOURing.address(), MemoryUtil.memAddress(pointerBuffer), pointerBuffer.remaining());
    }

    public static int io_uring_peek_cqe(@NativeType("struct io_uring *") IOURing iOURing, @NativeType("struct io_uring_cqe **") PointerBuffer pointerBuffer) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
        }
        return nio_uring_peek_cqe(iOURing.address(), MemoryUtil.memAddress(pointerBuffer));
    }

    public static int io_uring_wait_cqe(@NativeType("struct io_uring *") IOURing iOURing, @NativeType("struct io_uring_cqe **") PointerBuffer pointerBuffer) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
        }
        return nio_uring_wait_cqe(iOURing.address(), MemoryUtil.memAddress(pointerBuffer));
    }

    public static void io_uring_buf_ring_advance(@NativeType("struct io_uring_buf_ring *") IOURingBufRing iOURingBufRing, int i) {
        nio_uring_buf_ring_advance(iOURingBufRing.address(), i);
    }

    public static void io_uring_buf_ring_cq_advance(@NativeType("struct io_uring *") IOURing iOURing, @NativeType("struct io_uring_buf_ring *") IOURingBufRing iOURingBufRing, int i) {
        nio_uring_buf_ring_cq_advance(iOURing.address(), iOURingBufRing.address(), i);
    }

    public static int io_uring_buf_ring_available(@NativeType("struct io_uring *") IOURing iOURing, @NativeType("struct io_uring_buf_ring *") IOURingBufRing iOURingBufRing, @NativeType("unsigned short") short s) {
        return nio_uring_buf_ring_available(iOURing.address(), iOURingBufRing.address(), s);
    }

    @NativeType("struct io_uring_sqe *")
    public static IOURingSQE io_uring_get_sqe(@NativeType("struct io_uring *") IOURing iOURing) {
        return IOURingSQE.createSafe(nio_uring_get_sqe(iOURing.address()));
    }

    public static int io_uring_mlock_size_params(@NativeType("unsigned") int i, @NativeType("struct io_uring_params *") IOURingParams iOURingParams) {
        return nio_uring_mlock_size_params(i, iOURingParams.address());
    }

    public static int io_uring_buf_ring_mask(@NativeType("__u32") int i) {
        return i - 1;
    }

    public static void io_uring_buf_ring_init(@NativeType("struct io_uring_buf_ring *") IOURingBufRing iOURingBufRing) {
        iOURingBufRing.tail((short) 0);
    }

    public static void io_uring_buf_ring_add(@NativeType("struct io_uring_buf_ring *") IOURingBufRing iOURingBufRing, @NativeType("void *") ByteBuffer byteBuffer, @NativeType("unsigned short") short s, int i, int i2) {
        IOURingBuf bufs = iOURingBufRing.bufs((iOURingBufRing.tail() + i2) & i);
        bufs.addr(MemoryUtil.memAddress(byteBuffer));
        bufs.len(byteBuffer.remaining());
        bufs.bid(s);
    }
}
