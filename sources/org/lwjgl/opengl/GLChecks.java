package org.lwjgl.opengl;

import org.lwjgl.system.APIUtil;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GLChecks.class */
public final class GLChecks {
    private GLChecks() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int typeToBytes(int i) {
        switch (i) {
            case 5120:
            case 5121:
                return 1;
            case 5122:
            case 5123:
            case 5127:
            case 5131:
                return 2;
            case 5124:
            case 5125:
            case 5126:
            case 5129:
            case 5132:
                return 4;
            case 5128:
                return 3;
            case 5130:
            case 5134:
            case 5135:
                return 8;
            case 5133:
            default:
                throw new IllegalArgumentException(APIUtil.apiUnknownToken("Unsupported OpenGL type", i));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int typeToByteShift(int i) {
        switch (i) {
            case 5120:
            case 5121:
                return 0;
            case 5122:
            case 5123:
            case 5127:
            case 5131:
                return 1;
            case 5124:
            case 5125:
            case 5126:
            case 5129:
            case 5132:
                return 2;
            case 5128:
            case 5133:
            default:
                throw new IllegalArgumentException(APIUtil.apiUnknownToken("Unsupported OpenGL type", i));
            case 5130:
            case 5134:
            case 5135:
                return 3;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getTexLevelParameteri(int i, int i2, int i3, int i4) {
        GLCapabilities capabilities = GL.getCapabilities();
        if (capabilities.OpenGL45) {
            return GL45.glGetTextureLevelParameteri(i, i3, i4);
        }
        if (capabilities.GL_ARB_direct_state_access) {
            return ARBDirectStateAccess.glGetTextureLevelParameteri(i, i3, i4);
        }
        if (capabilities.GL_EXT_direct_state_access) {
            return EXTDirectStateAccess.glGetTextureLevelParameteriEXT(i, i2, i3, i4);
        }
        return GL41.glGetTexLevelParameteri(i2, i3, i4);
    }
}
