package com.badlogic.gdx.backends.lwjgl3.audio;

import com.badlogic.gdx.utils.GdxRuntimeException;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/backends/lwjgl3/audio/OpenALUtils.class */
public class OpenALUtils {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static int determineFormat(int i, int i2) {
        int i3;
        switch (i) {
            case 1:
                switch (i2) {
                    case 8:
                        i3 = 4352;
                        break;
                    case 16:
                        i3 = 4353;
                        break;
                    case 32:
                        i3 = 65552;
                        break;
                    case 64:
                        i3 = 65554;
                        break;
                    default:
                        throw new GdxRuntimeException("Audio: Bit depth must be 8, 16, 32 or 64.");
                }
            case 2:
                switch (i2) {
                    case 8:
                        i3 = 4354;
                        break;
                    case 16:
                        i3 = 4355;
                        break;
                    case 32:
                        i3 = 65553;
                        break;
                    case 64:
                        i3 = 65555;
                        break;
                    default:
                        throw new GdxRuntimeException("Audio: Bit depth must be 8, 16, 32 or 64.");
                }
            case 3:
            case 5:
            default:
                throw new GdxRuntimeException("Audio: Invalid number of channels. Must be mono, stereo, quad, 5.1, 6.1 or 7.1.");
            case 4:
                i3 = 4613;
                break;
            case 6:
                i3 = 4619;
                break;
            case 7:
                i3 = 4622;
                break;
            case 8:
                i3 = 4625;
                break;
        }
        if (i >= 4) {
            if (i2 == 8) {
                i3--;
            } else if (i2 == 32) {
                i3++;
            } else if (i2 != 16) {
                throw new GdxRuntimeException("Audio: Bit depth must be 8, 16 or 32 when 4+ channels are present.");
            }
        }
        return i3;
    }
}
