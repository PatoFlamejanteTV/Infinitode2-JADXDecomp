package org.lwjgl.opengl;

import java.util.Set;
import org.lwjgl.system.Checks;
import org.lwjgl.system.FunctionProvider;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/WGLCapabilities.class */
public final class WGLCapabilities {
    public final long wglGetGPUIDsAMD;
    public final long wglGetGPUInfoAMD;
    public final long wglGetContextGPUIDAMD;
    public final long wglCreateAssociatedContextAMD;
    public final long wglCreateAssociatedContextAttribsAMD;
    public final long wglDeleteAssociatedContextAMD;
    public final long wglMakeAssociatedContextCurrentAMD;
    public final long wglGetCurrentAssociatedContextAMD;
    public final long wglBlitContextFramebufferAMD;
    public final long wglCreateBufferRegionARB;
    public final long wglDeleteBufferRegionARB;
    public final long wglSaveBufferRegionARB;
    public final long wglRestoreBufferRegionARB;
    public final long wglCreateContextAttribsARB;
    public final long wglGetExtensionsStringARB;
    public final long wglMakeContextCurrentARB;
    public final long wglGetCurrentReadDCARB;
    public final long wglCreatePbufferARB;
    public final long wglGetPbufferDCARB;
    public final long wglReleasePbufferDCARB;
    public final long wglDestroyPbufferARB;
    public final long wglQueryPbufferARB;
    public final long wglGetPixelFormatAttribivARB;
    public final long wglGetPixelFormatAttribfvARB;
    public final long wglChoosePixelFormatARB;
    public final long wglBindTexImageARB;
    public final long wglReleaseTexImageARB;
    public final long wglSetPbufferAttribARB;
    public final long wglGetExtensionsStringEXT;
    public final long wglSwapIntervalEXT;
    public final long wglGetSwapIntervalEXT;
    public final long wglCopyImageSubDataNV;
    public final long wglDelayBeforeSwapNV;
    public final long wglDXSetResourceShareHandleNV;
    public final long wglDXOpenDeviceNV;
    public final long wglDXCloseDeviceNV;
    public final long wglDXRegisterObjectNV;
    public final long wglDXUnregisterObjectNV;
    public final long wglDXObjectAccessNV;
    public final long wglDXLockObjectsNV;
    public final long wglDXUnlockObjectsNV;
    public final long wglEnumGpusNV;
    public final long wglEnumGpuDevicesNV;
    public final long wglCreateAffinityDCNV;
    public final long wglEnumGpusFromAffinityDCNV;
    public final long wglDeleteDCNV;
    public final long wglJoinSwapGroupNV;
    public final long wglBindSwapBarrierNV;
    public final long wglQuerySwapGroupNV;
    public final long wglQueryMaxSwapGroupsNV;
    public final long wglQueryFrameCountNV;
    public final long wglResetFrameCountNV;
    public final long wglAllocateMemoryNV;
    public final long wglFreeMemoryNV;
    public final boolean WGL_AMD_gpu_association;
    public final boolean WGL_ARB_buffer_region;
    public final boolean WGL_ARB_context_flush_control;
    public final boolean WGL_ARB_create_context;
    public final boolean WGL_ARB_create_context_no_error;
    public final boolean WGL_ARB_create_context_profile;
    public final boolean WGL_ARB_create_context_robustness;
    public final boolean WGL_ARB_extensions_string;
    public final boolean WGL_ARB_framebuffer_sRGB;
    public final boolean WGL_ARB_make_current_read;
    public final boolean WGL_ARB_multisample;
    public final boolean WGL_ARB_pbuffer;
    public final boolean WGL_ARB_pixel_format;
    public final boolean WGL_ARB_pixel_format_float;
    public final boolean WGL_ARB_render_texture;
    public final boolean WGL_ARB_robustness_application_isolation;
    public final boolean WGL_ARB_robustness_share_group_isolation;
    public final boolean WGL_ATI_pixel_format_float;
    public final boolean WGL_ATI_render_texture_rectangle;
    public final boolean WGL_EXT_colorspace;
    public final boolean WGL_EXT_create_context_es2_profile;
    public final boolean WGL_EXT_create_context_es_profile;
    public final boolean WGL_EXT_depth_float;
    public final boolean WGL_EXT_extensions_string;
    public final boolean WGL_EXT_framebuffer_sRGB;
    public final boolean WGL_EXT_pixel_format_packed_float;
    public final boolean WGL_EXT_swap_control;
    public final boolean WGL_EXT_swap_control_tear;
    public final boolean WGL_NV_copy_image;
    public final boolean WGL_NV_delay_before_swap;
    public final boolean WGL_NV_DX_interop;
    public final boolean WGL_NV_DX_interop2;
    public final boolean WGL_NV_float_buffer;
    public final boolean WGL_NV_gpu_affinity;
    public final boolean WGL_NV_multigpu_context;
    public final boolean WGL_NV_multisample_coverage;
    public final boolean WGL_NV_render_depth_texture;
    public final boolean WGL_NV_render_texture_rectangle;
    public final boolean WGL_NV_swap_group;
    public final boolean WGL_NV_vertex_array_range;

    /* JADX INFO: Access modifiers changed from: package-private */
    public WGLCapabilities(FunctionProvider functionProvider, Set<String> set) {
        long[] jArr = new long[54];
        this.WGL_AMD_gpu_association = check_WGL_AMD_gpu_association(functionProvider, jArr, set);
        this.WGL_ARB_buffer_region = check_WGL_ARB_buffer_region(functionProvider, jArr, set);
        this.WGL_ARB_context_flush_control = set.contains("WGL_ARB_context_flush_control");
        this.WGL_ARB_create_context = check_WGL_ARB_create_context(functionProvider, jArr, set);
        this.WGL_ARB_create_context_no_error = set.contains("WGL_ARB_create_context_no_error");
        this.WGL_ARB_create_context_profile = set.contains("WGL_ARB_create_context_profile");
        this.WGL_ARB_create_context_robustness = set.contains("WGL_ARB_create_context_robustness");
        this.WGL_ARB_extensions_string = check_WGL_ARB_extensions_string(functionProvider, jArr, set);
        this.WGL_ARB_framebuffer_sRGB = set.contains("WGL_ARB_framebuffer_sRGB");
        this.WGL_ARB_make_current_read = check_WGL_ARB_make_current_read(functionProvider, jArr, set);
        this.WGL_ARB_multisample = set.contains("WGL_ARB_multisample");
        this.WGL_ARB_pbuffer = check_WGL_ARB_pbuffer(functionProvider, jArr, set);
        this.WGL_ARB_pixel_format = check_WGL_ARB_pixel_format(functionProvider, jArr, set);
        this.WGL_ARB_pixel_format_float = set.contains("WGL_ARB_pixel_format_float");
        this.WGL_ARB_render_texture = check_WGL_ARB_render_texture(functionProvider, jArr, set);
        this.WGL_ARB_robustness_application_isolation = set.contains("WGL_ARB_robustness_application_isolation");
        this.WGL_ARB_robustness_share_group_isolation = set.contains("WGL_ARB_robustness_share_group_isolation");
        this.WGL_ATI_pixel_format_float = set.contains("WGL_ATI_pixel_format_float");
        this.WGL_ATI_render_texture_rectangle = set.contains("WGL_ATI_render_texture_rectangle");
        this.WGL_EXT_colorspace = set.contains("WGL_EXT_colorspace");
        this.WGL_EXT_create_context_es2_profile = set.contains("WGL_EXT_create_context_es2_profile");
        this.WGL_EXT_create_context_es_profile = set.contains("WGL_EXT_create_context_es_profile");
        this.WGL_EXT_depth_float = set.contains("WGL_EXT_depth_float");
        this.WGL_EXT_extensions_string = check_WGL_EXT_extensions_string(functionProvider, jArr, set);
        this.WGL_EXT_framebuffer_sRGB = set.contains("WGL_EXT_framebuffer_sRGB");
        this.WGL_EXT_pixel_format_packed_float = set.contains("WGL_EXT_pixel_format_packed_float");
        this.WGL_EXT_swap_control = check_WGL_EXT_swap_control(functionProvider, jArr, set);
        this.WGL_EXT_swap_control_tear = set.contains("WGL_EXT_swap_control_tear");
        this.WGL_NV_copy_image = check_WGL_NV_copy_image(functionProvider, jArr, set);
        this.WGL_NV_delay_before_swap = check_WGL_NV_delay_before_swap(functionProvider, jArr, set);
        this.WGL_NV_DX_interop = check_WGL_NV_DX_interop(functionProvider, jArr, set);
        this.WGL_NV_DX_interop2 = set.contains("WGL_NV_DX_interop2");
        this.WGL_NV_float_buffer = set.contains("WGL_NV_float_buffer");
        this.WGL_NV_gpu_affinity = check_WGL_NV_gpu_affinity(functionProvider, jArr, set);
        this.WGL_NV_multigpu_context = set.contains("WGL_NV_multigpu_context");
        this.WGL_NV_multisample_coverage = set.contains("WGL_NV_multisample_coverage");
        this.WGL_NV_render_depth_texture = set.contains("WGL_NV_render_depth_texture");
        this.WGL_NV_render_texture_rectangle = set.contains("WGL_NV_render_texture_rectangle");
        this.WGL_NV_swap_group = check_WGL_NV_swap_group(functionProvider, jArr, set);
        this.WGL_NV_vertex_array_range = check_WGL_NV_vertex_array_range(functionProvider, jArr, set);
        this.wglGetGPUIDsAMD = jArr[0];
        this.wglGetGPUInfoAMD = jArr[1];
        this.wglGetContextGPUIDAMD = jArr[2];
        this.wglCreateAssociatedContextAMD = jArr[3];
        this.wglCreateAssociatedContextAttribsAMD = jArr[4];
        this.wglDeleteAssociatedContextAMD = jArr[5];
        this.wglMakeAssociatedContextCurrentAMD = jArr[6];
        this.wglGetCurrentAssociatedContextAMD = jArr[7];
        this.wglBlitContextFramebufferAMD = jArr[8];
        this.wglCreateBufferRegionARB = jArr[9];
        this.wglDeleteBufferRegionARB = jArr[10];
        this.wglSaveBufferRegionARB = jArr[11];
        this.wglRestoreBufferRegionARB = jArr[12];
        this.wglCreateContextAttribsARB = jArr[13];
        this.wglGetExtensionsStringARB = jArr[14];
        this.wglMakeContextCurrentARB = jArr[15];
        this.wglGetCurrentReadDCARB = jArr[16];
        this.wglCreatePbufferARB = jArr[17];
        this.wglGetPbufferDCARB = jArr[18];
        this.wglReleasePbufferDCARB = jArr[19];
        this.wglDestroyPbufferARB = jArr[20];
        this.wglQueryPbufferARB = jArr[21];
        this.wglGetPixelFormatAttribivARB = jArr[22];
        this.wglGetPixelFormatAttribfvARB = jArr[23];
        this.wglChoosePixelFormatARB = jArr[24];
        this.wglBindTexImageARB = jArr[25];
        this.wglReleaseTexImageARB = jArr[26];
        this.wglSetPbufferAttribARB = jArr[27];
        this.wglGetExtensionsStringEXT = jArr[28];
        this.wglSwapIntervalEXT = jArr[29];
        this.wglGetSwapIntervalEXT = jArr[30];
        this.wglCopyImageSubDataNV = jArr[31];
        this.wglDelayBeforeSwapNV = jArr[32];
        this.wglDXSetResourceShareHandleNV = jArr[33];
        this.wglDXOpenDeviceNV = jArr[34];
        this.wglDXCloseDeviceNV = jArr[35];
        this.wglDXRegisterObjectNV = jArr[36];
        this.wglDXUnregisterObjectNV = jArr[37];
        this.wglDXObjectAccessNV = jArr[38];
        this.wglDXLockObjectsNV = jArr[39];
        this.wglDXUnlockObjectsNV = jArr[40];
        this.wglEnumGpusNV = jArr[41];
        this.wglEnumGpuDevicesNV = jArr[42];
        this.wglCreateAffinityDCNV = jArr[43];
        this.wglEnumGpusFromAffinityDCNV = jArr[44];
        this.wglDeleteDCNV = jArr[45];
        this.wglJoinSwapGroupNV = jArr[46];
        this.wglBindSwapBarrierNV = jArr[47];
        this.wglQuerySwapGroupNV = jArr[48];
        this.wglQueryMaxSwapGroupsNV = jArr[49];
        this.wglQueryFrameCountNV = jArr[50];
        this.wglResetFrameCountNV = jArr[51];
        this.wglAllocateMemoryNV = jArr[52];
        this.wglFreeMemoryNV = jArr[53];
    }

    private static boolean check_WGL_AMD_gpu_association(FunctionProvider functionProvider, long[] jArr, Set<String> set) {
        if (set.contains("WGL_AMD_gpu_association")) {
            return Checks.checkFunctions(functionProvider, jArr, new int[]{0, 1, 2, 3, 4, 5, 6, 7}, "wglGetGPUIDsAMD", "wglGetGPUInfoAMD", "wglGetContextGPUIDAMD", "wglCreateAssociatedContextAMD", "wglCreateAssociatedContextAttribsAMD", "wglDeleteAssociatedContextAMD", "wglMakeAssociatedContextCurrentAMD", "wglGetCurrentAssociatedContextAMD") || Checks.reportMissing("WGL", "WGL_AMD_gpu_association");
        }
        return false;
    }

    private static boolean check_WGL_ARB_buffer_region(FunctionProvider functionProvider, long[] jArr, Set<String> set) {
        if (set.contains("WGL_ARB_buffer_region")) {
            return Checks.checkFunctions(functionProvider, jArr, new int[]{9, 10, 11, 12}, "wglCreateBufferRegionARB", "wglDeleteBufferRegionARB", "wglSaveBufferRegionARB", "wglRestoreBufferRegionARB") || Checks.reportMissing("WGL", "WGL_ARB_buffer_region");
        }
        return false;
    }

    private static boolean check_WGL_ARB_create_context(FunctionProvider functionProvider, long[] jArr, Set<String> set) {
        if (set.contains("WGL_ARB_create_context")) {
            return Checks.checkFunctions(functionProvider, jArr, new int[]{13}, "wglCreateContextAttribsARB") || Checks.reportMissing("WGL", "WGL_ARB_create_context");
        }
        return false;
    }

    private static boolean check_WGL_ARB_extensions_string(FunctionProvider functionProvider, long[] jArr, Set<String> set) {
        if (set.contains("WGL_ARB_extensions_string")) {
            return Checks.checkFunctions(functionProvider, jArr, new int[]{14}, "wglGetExtensionsStringARB") || Checks.reportMissing("WGL", "WGL_ARB_extensions_string");
        }
        return false;
    }

    private static boolean check_WGL_ARB_make_current_read(FunctionProvider functionProvider, long[] jArr, Set<String> set) {
        if (set.contains("WGL_ARB_make_current_read")) {
            return Checks.checkFunctions(functionProvider, jArr, new int[]{15, 16}, "wglMakeContextCurrentARB", "wglGetCurrentReadDCARB") || Checks.reportMissing("WGL", "WGL_ARB_make_current_read");
        }
        return false;
    }

    private static boolean check_WGL_ARB_pbuffer(FunctionProvider functionProvider, long[] jArr, Set<String> set) {
        if (set.contains("WGL_ARB_pbuffer")) {
            return Checks.checkFunctions(functionProvider, jArr, new int[]{17, 18, 19, 20, 21}, "wglCreatePbufferARB", "wglGetPbufferDCARB", "wglReleasePbufferDCARB", "wglDestroyPbufferARB", "wglQueryPbufferARB") || Checks.reportMissing("WGL", "WGL_ARB_pbuffer");
        }
        return false;
    }

    private static boolean check_WGL_ARB_pixel_format(FunctionProvider functionProvider, long[] jArr, Set<String> set) {
        if (set.contains("WGL_ARB_pixel_format")) {
            return Checks.checkFunctions(functionProvider, jArr, new int[]{22, 23, 24}, "wglGetPixelFormatAttribivARB", "wglGetPixelFormatAttribfvARB", "wglChoosePixelFormatARB") || Checks.reportMissing("WGL", "WGL_ARB_pixel_format");
        }
        return false;
    }

    private static boolean check_WGL_ARB_render_texture(FunctionProvider functionProvider, long[] jArr, Set<String> set) {
        if (set.contains("WGL_ARB_render_texture")) {
            return Checks.checkFunctions(functionProvider, jArr, new int[]{25, 26, 27}, "wglBindTexImageARB", "wglReleaseTexImageARB", "wglSetPbufferAttribARB") || Checks.reportMissing("WGL", "WGL_ARB_render_texture");
        }
        return false;
    }

    private static boolean check_WGL_EXT_extensions_string(FunctionProvider functionProvider, long[] jArr, Set<String> set) {
        if (set.contains("WGL_EXT_extensions_string")) {
            return Checks.checkFunctions(functionProvider, jArr, new int[]{28}, "wglGetExtensionsStringEXT") || Checks.reportMissing("WGL", "WGL_EXT_extensions_string");
        }
        return false;
    }

    private static boolean check_WGL_EXT_swap_control(FunctionProvider functionProvider, long[] jArr, Set<String> set) {
        if (set.contains("WGL_EXT_swap_control")) {
            return Checks.checkFunctions(functionProvider, jArr, new int[]{29, 30}, "wglSwapIntervalEXT", "wglGetSwapIntervalEXT") || Checks.reportMissing("WGL", "WGL_EXT_swap_control");
        }
        return false;
    }

    private static boolean check_WGL_NV_copy_image(FunctionProvider functionProvider, long[] jArr, Set<String> set) {
        if (set.contains("WGL_NV_copy_image")) {
            return Checks.checkFunctions(functionProvider, jArr, new int[]{31}, "wglCopyImageSubDataNV") || Checks.reportMissing("WGL", "WGL_NV_copy_image");
        }
        return false;
    }

    private static boolean check_WGL_NV_delay_before_swap(FunctionProvider functionProvider, long[] jArr, Set<String> set) {
        if (set.contains("WGL_NV_delay_before_swap")) {
            return Checks.checkFunctions(functionProvider, jArr, new int[]{32}, "wglDelayBeforeSwapNV") || Checks.reportMissing("WGL", "WGL_NV_delay_before_swap");
        }
        return false;
    }

    private static boolean check_WGL_NV_DX_interop(FunctionProvider functionProvider, long[] jArr, Set<String> set) {
        if (set.contains("WGL_NV_DX_interop")) {
            return Checks.checkFunctions(functionProvider, jArr, new int[]{33, 34, 35, 36, 37, 38, 39, 40}, "wglDXSetResourceShareHandleNV", "wglDXOpenDeviceNV", "wglDXCloseDeviceNV", "wglDXRegisterObjectNV", "wglDXUnregisterObjectNV", "wglDXObjectAccessNV", "wglDXLockObjectsNV", "wglDXUnlockObjectsNV") || Checks.reportMissing("WGL", "WGL_NV_DX_interop");
        }
        return false;
    }

    private static boolean check_WGL_NV_gpu_affinity(FunctionProvider functionProvider, long[] jArr, Set<String> set) {
        if (set.contains("WGL_NV_gpu_affinity")) {
            return Checks.checkFunctions(functionProvider, jArr, new int[]{41, 42, 43, 44, 45}, "wglEnumGpusNV", "wglEnumGpuDevicesNV", "wglCreateAffinityDCNV", "wglEnumGpusFromAffinityDCNV", "wglDeleteDCNV") || Checks.reportMissing("WGL", "WGL_NV_gpu_affinity");
        }
        return false;
    }

    private static boolean check_WGL_NV_swap_group(FunctionProvider functionProvider, long[] jArr, Set<String> set) {
        if (set.contains("WGL_NV_swap_group")) {
            return Checks.checkFunctions(functionProvider, jArr, new int[]{46, 47, 48, 49, 50, 51}, "wglJoinSwapGroupNV", "wglBindSwapBarrierNV", "wglQuerySwapGroupNV", "wglQueryMaxSwapGroupsNV", "wglQueryFrameCountNV", "wglResetFrameCountNV") || Checks.reportMissing("WGL", "WGL_NV_swap_group");
        }
        return false;
    }

    private static boolean check_WGL_NV_vertex_array_range(FunctionProvider functionProvider, long[] jArr, Set<String> set) {
        if (set.contains("WGL_NV_vertex_array_range")) {
            return Checks.checkFunctions(functionProvider, jArr, new int[]{52, 53}, "wglAllocateMemoryNV", "wglFreeMemoryNV") || Checks.reportMissing("WGL", "WGL_NV_vertex_array_range");
        }
        return false;
    }
}
