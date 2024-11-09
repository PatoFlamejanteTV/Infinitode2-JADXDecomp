package org.lwjgl.opengl;

import java.util.Set;
import org.lwjgl.system.Checks;
import org.lwjgl.system.FunctionProvider;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GLXCapabilities.class */
public final class GLXCapabilities {
    public final long glXQueryExtensionsString;
    public final long glXGetClientString;
    public final long glXQueryServerString;
    public final long glXGetCurrentDisplay;
    public final long glXGetFBConfigs;
    public final long glXChooseFBConfig;
    public final long glXGetFBConfigAttrib;
    public final long glXGetVisualFromFBConfig;
    public final long glXCreateWindow;
    public final long glXCreatePixmap;
    public final long glXDestroyPixmap;
    public final long glXCreatePbuffer;
    public final long glXDestroyPbuffer;
    public final long glXQueryDrawable;
    public final long glXCreateNewContext;
    public final long glXMakeContextCurrent;
    public final long glXGetCurrentReadDrawable;
    public final long glXQueryContext;
    public final long glXSelectEvent;
    public final long glXGetSelectedEvent;
    public final long glXGetProcAddress;
    public final long glXBlitContextFramebufferAMD;
    public final long glXCreateAssociatedContextAMD;
    public final long glXCreateAssociatedContextAttribsAMD;
    public final long glXDeleteAssociatedContextAMD;
    public final long glXGetContextGPUIDAMD;
    public final long glXGetCurrentAssociatedContextAMD;
    public final long glXGetGPUIDsAMD;
    public final long glXGetGPUInfoAMD;
    public final long glXMakeAssociatedContextCurrentAMD;
    public final long glXCreateContextAttribsARB;
    public final long glXGetProcAddressARB;
    public final long glXGetCurrentDisplayEXT;
    public final long glXQueryContextInfoEXT;
    public final long glXGetContextIDEXT;
    public final long glXImportContextEXT;
    public final long glXFreeContextEXT;
    public final long glXSwapIntervalEXT;
    public final long glXBindTexImageEXT;
    public final long glXReleaseTexImageEXT;
    public final long glXCopyBufferSubDataNV;
    public final long glXNamedCopyBufferSubDataNV;
    public final long glXCopyImageSubDataNV;
    public final long glXDelayBeforeSwapNV;
    public final long glXJoinSwapGroupNV;
    public final long glXBindSwapBarrierNV;
    public final long glXQuerySwapGroupNV;
    public final long glXQueryMaxSwapGroupsNV;
    public final long glXQueryFrameCountNV;
    public final long glXResetFrameCountNV;
    public final long glXMakeCurrentReadSGI;
    public final long glXGetCurrentReadDrawableSGI;
    public final long glXSwapIntervalSGI;
    public final long glXGetVideoSyncSGI;
    public final long glXWaitVideoSyncSGI;
    public final long glXGetFBConfigAttribSGIX;
    public final long glXChooseFBConfigSGIX;
    public final long glXCreateGLXPixmapWithConfigSGIX;
    public final long glXCreateContextWithConfigSGIX;
    public final long glXGetVisualFromFBConfigSGIX;
    public final long glXGetFBConfigFromVisualSGIX;
    public final long glXCreateGLXPbufferSGIX;
    public final long glXDestroyGLXPbufferSGIX;
    public final long glXQueryGLXPbufferSGIX;
    public final long glXSelectEventSGIX;
    public final long glXGetSelectedEventSGIX;
    public final long glXBindSwapBarrierSGIX;
    public final long glXQueryMaxSwapBarriersSGIX;
    public final long glXJoinSwapGroupSGIX;
    public final boolean GLX11;
    public final boolean GLX12;
    public final boolean GLX13;
    public final boolean GLX14;
    public final boolean GLX_AMD_gpu_association;
    public final boolean GLX_ARB_context_flush_control;
    public final boolean GLX_ARB_create_context;
    public final boolean GLX_ARB_create_context_no_error;
    public final boolean GLX_ARB_create_context_profile;
    public final boolean GLX_ARB_create_context_robustness;
    public final boolean GLX_ARB_fbconfig_float;
    public final boolean GLX_ARB_framebuffer_sRGB;
    public final boolean GLX_ARB_get_proc_address;
    public final boolean GLX_ARB_multisample;
    public final boolean GLX_ARB_robustness_application_isolation;
    public final boolean GLX_ARB_robustness_share_group_isolation;
    public final boolean GLX_ARB_vertex_buffer_object;
    public final boolean GLX_EXT_buffer_age;
    public final boolean GLX_EXT_context_priority;
    public final boolean GLX_EXT_create_context_es2_profile;
    public final boolean GLX_EXT_create_context_es_profile;
    public final boolean GLX_EXT_fbconfig_packed_float;
    public final boolean GLX_EXT_framebuffer_sRGB;
    public final boolean GLX_EXT_get_drawable_type;
    public final boolean GLX_EXT_import_context;
    public final boolean GLX_EXT_no_config_context;
    public final boolean GLX_EXT_stereo_tree;
    public final boolean GLX_EXT_swap_control;
    public final boolean GLX_EXT_swap_control_tear;
    public final boolean GLX_EXT_texture_from_pixmap;
    public final boolean GLX_EXT_visual_info;
    public final boolean GLX_EXT_visual_rating;
    public final boolean GLX_INTEL_swap_event;
    public final boolean GLX_NV_copy_buffer;
    public final boolean GLX_NV_copy_image;
    public final boolean GLX_NV_delay_before_swap;
    public final boolean GLX_NV_float_buffer;
    public final boolean GLX_NV_multigpu_context;
    public final boolean GLX_NV_multisample_coverage;
    public final boolean GLX_NV_robustness_video_memory_purge;
    public final boolean GLX_NV_swap_group;
    public final boolean GLX_SGI_make_current_read;
    public final boolean GLX_SGI_swap_control;
    public final boolean GLX_SGI_video_sync;
    public final boolean GLX_SGIX_fbconfig;
    public final boolean GLX_SGIX_pbuffer;
    public final boolean GLX_SGIX_swap_barrier;
    public final boolean GLX_SGIX_swap_group;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GLXCapabilities(FunctionProvider functionProvider, Set<String> set) {
        long[] jArr = new long[69];
        this.GLX11 = check_GLX11(functionProvider, jArr, set);
        this.GLX12 = check_GLX12(functionProvider, jArr, set);
        this.GLX13 = check_GLX13(functionProvider, jArr, set);
        this.GLX14 = check_GLX14(functionProvider, jArr, set);
        this.GLX_AMD_gpu_association = check_GLX_AMD_gpu_association(functionProvider, jArr, set);
        this.GLX_ARB_context_flush_control = set.contains("GLX_ARB_context_flush_control");
        this.GLX_ARB_create_context = check_GLX_ARB_create_context(functionProvider, jArr, set);
        this.GLX_ARB_create_context_no_error = set.contains("GLX_ARB_create_context_no_error");
        this.GLX_ARB_create_context_profile = set.contains("GLX_ARB_create_context_profile");
        this.GLX_ARB_create_context_robustness = set.contains("GLX_ARB_create_context_robustness");
        this.GLX_ARB_fbconfig_float = set.contains("GLX_ARB_fbconfig_float");
        this.GLX_ARB_framebuffer_sRGB = set.contains("GLX_ARB_framebuffer_sRGB");
        this.GLX_ARB_get_proc_address = check_GLX_ARB_get_proc_address(functionProvider, jArr, set);
        this.GLX_ARB_multisample = set.contains("GLX_ARB_multisample");
        this.GLX_ARB_robustness_application_isolation = set.contains("GLX_ARB_robustness_application_isolation");
        this.GLX_ARB_robustness_share_group_isolation = set.contains("GLX_ARB_robustness_share_group_isolation");
        this.GLX_ARB_vertex_buffer_object = set.contains("GLX_ARB_vertex_buffer_object");
        this.GLX_EXT_buffer_age = set.contains("GLX_EXT_buffer_age");
        this.GLX_EXT_context_priority = set.contains("GLX_EXT_context_priority");
        this.GLX_EXT_create_context_es2_profile = set.contains("GLX_EXT_create_context_es2_profile");
        this.GLX_EXT_create_context_es_profile = set.contains("GLX_EXT_create_context_es_profile");
        this.GLX_EXT_fbconfig_packed_float = set.contains("GLX_EXT_fbconfig_packed_float");
        this.GLX_EXT_framebuffer_sRGB = set.contains("GLX_EXT_framebuffer_sRGB");
        this.GLX_EXT_get_drawable_type = set.contains("GLX_EXT_get_drawable_type");
        this.GLX_EXT_import_context = check_GLX_EXT_import_context(functionProvider, jArr, set);
        this.GLX_EXT_no_config_context = set.contains("GLX_EXT_no_config_context");
        this.GLX_EXT_stereo_tree = set.contains("GLX_EXT_stereo_tree");
        this.GLX_EXT_swap_control = check_GLX_EXT_swap_control(functionProvider, jArr, set);
        this.GLX_EXT_swap_control_tear = set.contains("GLX_EXT_swap_control_tear");
        this.GLX_EXT_texture_from_pixmap = check_GLX_EXT_texture_from_pixmap(functionProvider, jArr, set);
        this.GLX_EXT_visual_info = set.contains("GLX_EXT_visual_info");
        this.GLX_EXT_visual_rating = set.contains("GLX_EXT_visual_rating");
        this.GLX_INTEL_swap_event = set.contains("GLX_INTEL_swap_event");
        this.GLX_NV_copy_buffer = check_GLX_NV_copy_buffer(functionProvider, jArr, set);
        this.GLX_NV_copy_image = check_GLX_NV_copy_image(functionProvider, jArr, set);
        this.GLX_NV_delay_before_swap = check_GLX_NV_delay_before_swap(functionProvider, jArr, set);
        this.GLX_NV_float_buffer = set.contains("GLX_NV_float_buffer");
        this.GLX_NV_multigpu_context = set.contains("GLX_NV_multigpu_context");
        this.GLX_NV_multisample_coverage = set.contains("GLX_NV_multisample_coverage");
        this.GLX_NV_robustness_video_memory_purge = set.contains("GLX_NV_robustness_video_memory_purge");
        this.GLX_NV_swap_group = check_GLX_NV_swap_group(functionProvider, jArr, set);
        this.GLX_SGI_make_current_read = check_GLX_SGI_make_current_read(functionProvider, jArr, set);
        this.GLX_SGI_swap_control = check_GLX_SGI_swap_control(functionProvider, jArr, set);
        this.GLX_SGI_video_sync = check_GLX_SGI_video_sync(functionProvider, jArr, set);
        this.GLX_SGIX_fbconfig = check_GLX_SGIX_fbconfig(functionProvider, jArr, set);
        this.GLX_SGIX_pbuffer = check_GLX_SGIX_pbuffer(functionProvider, jArr, set);
        this.GLX_SGIX_swap_barrier = check_GLX_SGIX_swap_barrier(functionProvider, jArr, set);
        this.GLX_SGIX_swap_group = check_GLX_SGIX_swap_group(functionProvider, jArr, set);
        this.glXQueryExtensionsString = jArr[0];
        this.glXGetClientString = jArr[1];
        this.glXQueryServerString = jArr[2];
        this.glXGetCurrentDisplay = jArr[3];
        this.glXGetFBConfigs = jArr[4];
        this.glXChooseFBConfig = jArr[5];
        this.glXGetFBConfigAttrib = jArr[6];
        this.glXGetVisualFromFBConfig = jArr[7];
        this.glXCreateWindow = jArr[8];
        this.glXCreatePixmap = jArr[9];
        this.glXDestroyPixmap = jArr[10];
        this.glXCreatePbuffer = jArr[11];
        this.glXDestroyPbuffer = jArr[12];
        this.glXQueryDrawable = jArr[13];
        this.glXCreateNewContext = jArr[14];
        this.glXMakeContextCurrent = jArr[15];
        this.glXGetCurrentReadDrawable = jArr[16];
        this.glXQueryContext = jArr[17];
        this.glXSelectEvent = jArr[18];
        this.glXGetSelectedEvent = jArr[19];
        this.glXGetProcAddress = jArr[20];
        this.glXBlitContextFramebufferAMD = jArr[21];
        this.glXCreateAssociatedContextAMD = jArr[22];
        this.glXCreateAssociatedContextAttribsAMD = jArr[23];
        this.glXDeleteAssociatedContextAMD = jArr[24];
        this.glXGetContextGPUIDAMD = jArr[25];
        this.glXGetCurrentAssociatedContextAMD = jArr[26];
        this.glXGetGPUIDsAMD = jArr[27];
        this.glXGetGPUInfoAMD = jArr[28];
        this.glXMakeAssociatedContextCurrentAMD = jArr[29];
        this.glXCreateContextAttribsARB = jArr[30];
        this.glXGetProcAddressARB = jArr[31];
        this.glXGetCurrentDisplayEXT = jArr[32];
        this.glXQueryContextInfoEXT = jArr[33];
        this.glXGetContextIDEXT = jArr[34];
        this.glXImportContextEXT = jArr[35];
        this.glXFreeContextEXT = jArr[36];
        this.glXSwapIntervalEXT = jArr[37];
        this.glXBindTexImageEXT = jArr[38];
        this.glXReleaseTexImageEXT = jArr[39];
        this.glXCopyBufferSubDataNV = jArr[40];
        this.glXNamedCopyBufferSubDataNV = jArr[41];
        this.glXCopyImageSubDataNV = jArr[42];
        this.glXDelayBeforeSwapNV = jArr[43];
        this.glXJoinSwapGroupNV = jArr[44];
        this.glXBindSwapBarrierNV = jArr[45];
        this.glXQuerySwapGroupNV = jArr[46];
        this.glXQueryMaxSwapGroupsNV = jArr[47];
        this.glXQueryFrameCountNV = jArr[48];
        this.glXResetFrameCountNV = jArr[49];
        this.glXMakeCurrentReadSGI = jArr[50];
        this.glXGetCurrentReadDrawableSGI = jArr[51];
        this.glXSwapIntervalSGI = jArr[52];
        this.glXGetVideoSyncSGI = jArr[53];
        this.glXWaitVideoSyncSGI = jArr[54];
        this.glXGetFBConfigAttribSGIX = jArr[55];
        this.glXChooseFBConfigSGIX = jArr[56];
        this.glXCreateGLXPixmapWithConfigSGIX = jArr[57];
        this.glXCreateContextWithConfigSGIX = jArr[58];
        this.glXGetVisualFromFBConfigSGIX = jArr[59];
        this.glXGetFBConfigFromVisualSGIX = jArr[60];
        this.glXCreateGLXPbufferSGIX = jArr[61];
        this.glXDestroyGLXPbufferSGIX = jArr[62];
        this.glXQueryGLXPbufferSGIX = jArr[63];
        this.glXSelectEventSGIX = jArr[64];
        this.glXGetSelectedEventSGIX = jArr[65];
        this.glXBindSwapBarrierSGIX = jArr[66];
        this.glXQueryMaxSwapBarriersSGIX = jArr[67];
        this.glXJoinSwapGroupSGIX = jArr[68];
    }

    private static boolean check_GLX11(FunctionProvider functionProvider, long[] jArr, Set<String> set) {
        if (set.contains("GLX11")) {
            return Checks.checkFunctions(functionProvider, jArr, new int[]{0, 1, 2}, "glXQueryExtensionsString", "glXGetClientString", "glXQueryServerString") || Checks.reportMissing("GLX", "GLX11");
        }
        return false;
    }

    private static boolean check_GLX12(FunctionProvider functionProvider, long[] jArr, Set<String> set) {
        if (set.contains("GLX12")) {
            return Checks.checkFunctions(functionProvider, jArr, new int[]{3}, "glXGetCurrentDisplay") || Checks.reportMissing("GLX", "GLX12");
        }
        return false;
    }

    private static boolean check_GLX13(FunctionProvider functionProvider, long[] jArr, Set<String> set) {
        if (set.contains("GLX13")) {
            return Checks.checkFunctions(functionProvider, jArr, new int[]{4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19}, "glXGetFBConfigs", "glXChooseFBConfig", "glXGetFBConfigAttrib", "glXGetVisualFromFBConfig", "glXCreateWindow", "glXCreatePixmap", "glXDestroyPixmap", "glXCreatePbuffer", "glXDestroyPbuffer", "glXQueryDrawable", "glXCreateNewContext", "glXMakeContextCurrent", "glXGetCurrentReadDrawable", "glXQueryContext", "glXSelectEvent", "glXGetSelectedEvent") || Checks.reportMissing("GLX", "GLX13");
        }
        return false;
    }

    private static boolean check_GLX14(FunctionProvider functionProvider, long[] jArr, Set<String> set) {
        if (set.contains("GLX14")) {
            return Checks.checkFunctions(functionProvider, jArr, new int[]{20}, "glXGetProcAddress") || Checks.reportMissing("GLX", "GLX14");
        }
        return false;
    }

    private static boolean check_GLX_AMD_gpu_association(FunctionProvider functionProvider, long[] jArr, Set<String> set) {
        if (set.contains("GLX_AMD_gpu_association")) {
            return Checks.checkFunctions(functionProvider, jArr, new int[]{21, 22, 23, 24, 25, 26, 27, 28, 29}, "glXBlitContextFramebufferAMD", "glXCreateAssociatedContextAMD", "glXCreateAssociatedContextAttribsAMD", "glXDeleteAssociatedContextAMD", "glXGetContextGPUIDAMD", "glXGetCurrentAssociatedContextAMD", "glXGetGPUIDsAMD", "glXGetGPUInfoAMD", "glXMakeAssociatedContextCurrentAMD") || Checks.reportMissing("GLX", "GLX_AMD_gpu_association");
        }
        return false;
    }

    private static boolean check_GLX_ARB_create_context(FunctionProvider functionProvider, long[] jArr, Set<String> set) {
        if (set.contains("GLX_ARB_create_context")) {
            return Checks.checkFunctions(functionProvider, jArr, new int[]{30}, "glXCreateContextAttribsARB") || Checks.reportMissing("GLX", "GLX_ARB_create_context");
        }
        return false;
    }

    private static boolean check_GLX_ARB_get_proc_address(FunctionProvider functionProvider, long[] jArr, Set<String> set) {
        if (set.contains("GLX_ARB_get_proc_address")) {
            return Checks.checkFunctions(functionProvider, jArr, new int[]{31}, "glXGetProcAddressARB") || Checks.reportMissing("GLX", "GLX_ARB_get_proc_address");
        }
        return false;
    }

    private static boolean check_GLX_EXT_import_context(FunctionProvider functionProvider, long[] jArr, Set<String> set) {
        if (set.contains("GLX_EXT_import_context")) {
            return Checks.checkFunctions(functionProvider, jArr, new int[]{32, 33, 34, 35, 36}, "glXGetCurrentDisplayEXT", "glXQueryContextInfoEXT", "glXGetContextIDEXT", "glXImportContextEXT", "glXFreeContextEXT") || Checks.reportMissing("GLX", "GLX_EXT_import_context");
        }
        return false;
    }

    private static boolean check_GLX_EXT_swap_control(FunctionProvider functionProvider, long[] jArr, Set<String> set) {
        if (set.contains("GLX_EXT_swap_control")) {
            return Checks.checkFunctions(functionProvider, jArr, new int[]{37}, "glXSwapIntervalEXT") || Checks.reportMissing("GLX", "GLX_EXT_swap_control");
        }
        return false;
    }

    private static boolean check_GLX_EXT_texture_from_pixmap(FunctionProvider functionProvider, long[] jArr, Set<String> set) {
        if (set.contains("GLX_EXT_texture_from_pixmap")) {
            return Checks.checkFunctions(functionProvider, jArr, new int[]{38, 39}, "glXBindTexImageEXT", "glXReleaseTexImageEXT") || Checks.reportMissing("GLX", "GLX_EXT_texture_from_pixmap");
        }
        return false;
    }

    private static boolean check_GLX_NV_copy_buffer(FunctionProvider functionProvider, long[] jArr, Set<String> set) {
        if (set.contains("GLX_NV_copy_buffer")) {
            return Checks.checkFunctions(functionProvider, jArr, new int[]{40, 41}, "glXCopyBufferSubDataNV", "glXNamedCopyBufferSubDataNV") || Checks.reportMissing("GLX", "GLX_NV_copy_buffer");
        }
        return false;
    }

    private static boolean check_GLX_NV_copy_image(FunctionProvider functionProvider, long[] jArr, Set<String> set) {
        if (set.contains("GLX_NV_copy_image")) {
            return Checks.checkFunctions(functionProvider, jArr, new int[]{42}, "glXCopyImageSubDataNV") || Checks.reportMissing("GLX", "GLX_NV_copy_image");
        }
        return false;
    }

    private static boolean check_GLX_NV_delay_before_swap(FunctionProvider functionProvider, long[] jArr, Set<String> set) {
        if (set.contains("GLX_NV_delay_before_swap")) {
            return Checks.checkFunctions(functionProvider, jArr, new int[]{43}, "glXDelayBeforeSwapNV") || Checks.reportMissing("GLX", "GLX_NV_delay_before_swap");
        }
        return false;
    }

    private static boolean check_GLX_NV_swap_group(FunctionProvider functionProvider, long[] jArr, Set<String> set) {
        if (set.contains("GLX_NV_swap_group")) {
            return Checks.checkFunctions(functionProvider, jArr, new int[]{44, 45, 46, 47, 48, 49}, "glXJoinSwapGroupNV", "glXBindSwapBarrierNV", "glXQuerySwapGroupNV", "glXQueryMaxSwapGroupsNV", "glXQueryFrameCountNV", "glXResetFrameCountNV") || Checks.reportMissing("GLX", "GLX_NV_swap_group");
        }
        return false;
    }

    private static boolean check_GLX_SGI_make_current_read(FunctionProvider functionProvider, long[] jArr, Set<String> set) {
        if (set.contains("GLX_SGI_make_current_read")) {
            return Checks.checkFunctions(functionProvider, jArr, new int[]{50, 51}, "glXMakeCurrentReadSGI", "glXGetCurrentReadDrawableSGI") || Checks.reportMissing("GLX", "GLX_SGI_make_current_read");
        }
        return false;
    }

    private static boolean check_GLX_SGI_swap_control(FunctionProvider functionProvider, long[] jArr, Set<String> set) {
        if (set.contains("GLX_SGI_swap_control")) {
            return Checks.checkFunctions(functionProvider, jArr, new int[]{52}, "glXSwapIntervalSGI") || Checks.reportMissing("GLX", "GLX_SGI_swap_control");
        }
        return false;
    }

    private static boolean check_GLX_SGI_video_sync(FunctionProvider functionProvider, long[] jArr, Set<String> set) {
        if (set.contains("GLX_SGI_video_sync")) {
            return Checks.checkFunctions(functionProvider, jArr, new int[]{53, 54}, "glXGetVideoSyncSGI", "glXWaitVideoSyncSGI") || Checks.reportMissing("GLX", "GLX_SGI_video_sync");
        }
        return false;
    }

    private static boolean check_GLX_SGIX_fbconfig(FunctionProvider functionProvider, long[] jArr, Set<String> set) {
        if (set.contains("GLX_SGIX_fbconfig")) {
            return Checks.checkFunctions(functionProvider, jArr, new int[]{55, 56, 57, 58, 59, 60}, "glXGetFBConfigAttribSGIX", "glXChooseFBConfigSGIX", "glXCreateGLXPixmapWithConfigSGIX", "glXCreateContextWithConfigSGIX", "glXGetVisualFromFBConfigSGIX", "glXGetFBConfigFromVisualSGIX") || Checks.reportMissing("GLX", "GLX_SGIX_fbconfig");
        }
        return false;
    }

    private static boolean check_GLX_SGIX_pbuffer(FunctionProvider functionProvider, long[] jArr, Set<String> set) {
        if (set.contains("GLX_SGIX_pbuffer")) {
            return Checks.checkFunctions(functionProvider, jArr, new int[]{61, 62, 63, 64, 65}, "glXCreateGLXPbufferSGIX", "glXDestroyGLXPbufferSGIX", "glXQueryGLXPbufferSGIX", "glXSelectEventSGIX", "glXGetSelectedEventSGIX") || Checks.reportMissing("GLX", "GLX_SGIX_pbuffer");
        }
        return false;
    }

    private static boolean check_GLX_SGIX_swap_barrier(FunctionProvider functionProvider, long[] jArr, Set<String> set) {
        if (set.contains("GLX_SGIX_swap_barrier")) {
            return Checks.checkFunctions(functionProvider, jArr, new int[]{66, 67}, "glXBindSwapBarrierSGIX", "glXQueryMaxSwapBarriersSGIX") || Checks.reportMissing("GLX", "GLX_SGIX_swap_barrier");
        }
        return false;
    }

    private static boolean check_GLX_SGIX_swap_group(FunctionProvider functionProvider, long[] jArr, Set<String> set) {
        if (set.contains("GLX_SGIX_swap_group")) {
            return Checks.checkFunctions(functionProvider, jArr, new int[]{68}, "glXJoinSwapGroupSGIX") || Checks.reportMissing("GLX", "GLX_SGIX_swap_group");
        }
        return false;
    }
}
