package org.a.b.b;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.CGL;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:org/a/b/b/h.class */
public final class h extends c {

    /* renamed from: a, reason: collision with root package name */
    private static final Object[][] f4203a = {new Object[]{0, ".notdef"}, new Object[]{1, "space"}, new Object[]{Integer.valueOf(User32.VK_PACKET), "dollaroldstyle"}, new Object[]{232, "dollarsuperior"}, new Object[]{235, "parenleftsuperior"}, new Object[]{236, "parenrightsuperior"}, new Object[]{Integer.valueOf(User32.VK_OEM_PA3), "twodotenleader"}, new Object[]{Integer.valueOf(User32.VK_OEM_WSCTRL), "onedotenleader"}, new Object[]{13, "comma"}, new Object[]{14, "hyphen"}, new Object[]{15, "period"}, new Object[]{99, "fraction"}, new Object[]{Integer.valueOf(User32.VK_OEM_CUSEL), "zerooldstyle"}, new Object[]{Integer.valueOf(User32.VK_OEM_ATTN), "oneoldstyle"}, new Object[]{Integer.valueOf(User32.VK_OEM_FINISH), "twooldstyle"}, new Object[]{Integer.valueOf(User32.VK_OEM_COPY), "threeoldstyle"}, new Object[]{243, "fouroldstyle"}, new Object[]{Integer.valueOf(User32.VK_OEM_ENLW), "fiveoldstyle"}, new Object[]{Integer.valueOf(User32.VK_OEM_BACKTAB), "sixoldstyle"}, new Object[]{Integer.valueOf(User32.VK_ATTN), "sevenoldstyle"}, new Object[]{Integer.valueOf(User32.VK_CRSEL), "eightoldstyle"}, new Object[]{Integer.valueOf(User32.VK_EXSEL), "nineoldstyle"}, new Object[]{27, "colon"}, new Object[]{28, "semicolon"}, new Object[]{Integer.valueOf(User32.VK_EREOF), "commasuperior"}, new Object[]{Integer.valueOf(User32.VK_PLAY), "threequartersemdash"}, new Object[]{Integer.valueOf(User32.VK_ZOOM), "periodsuperior"}, new Object[]{Integer.valueOf(User32.VK_PA1), "asuperior"}, new Object[]{254, "bsuperior"}, new Object[]{255, "centsuperior"}, new Object[]{256, "dsuperior"}, new Object[]{257, "esuperior"}, new Object[]{258, "isuperior"}, new Object[]{259, "lsuperior"}, new Object[]{260, "msuperior"}, new Object[]{261, "nsuperior"}, new Object[]{262, "osuperior"}, new Object[]{263, "rsuperior"}, new Object[]{Integer.valueOf(GLFW.GLFW_KEY_DOWN), "ssuperior"}, new Object[]{265, "tsuperior"}, new Object[]{Integer.valueOf(GLFW.GLFW_KEY_PAGE_UP), "ff"}, new Object[]{109, "fi"}, new Object[]{110, "fl"}, new Object[]{Integer.valueOf(GLFW.GLFW_KEY_PAGE_DOWN), "ffi"}, new Object[]{Integer.valueOf(GLFW.GLFW_KEY_HOME), "ffl"}, new Object[]{269, "parenleftinferior"}, new Object[]{Integer.valueOf(User32.WM_IME_ENDCOMPOSITION), "parenrightinferior"}, new Object[]{272, "hyphensuperior"}, new Object[]{300, "colonmonetary"}, new Object[]{301, "onefitted"}, new Object[]{302, "rupiah"}, new Object[]{305, "centoldstyle"}, new Object[]{314, "figuredash"}, new Object[]{Integer.valueOf(CGL.kCGLCPMPSwapsInFlight), "hypheninferior"}, new Object[]{158, "onequarter"}, new Object[]{155, "onehalf"}, new Object[]{163, "threequarters"}, new Object[]{Integer.valueOf(GLFW.GLFW_KEY_KP_0), "oneeighth"}, new Object[]{Integer.valueOf(GLFW.GLFW_KEY_KP_1), "threeeighths"}, new Object[]{Integer.valueOf(GLFW.GLFW_KEY_KP_2), "fiveeighths"}, new Object[]{Integer.valueOf(GLFW.GLFW_KEY_KP_3), "seveneighths"}, new Object[]{Integer.valueOf(GLFW.GLFW_KEY_KP_4), "onethird"}, new Object[]{Integer.valueOf(GLFW.GLFW_KEY_KP_5), "twothirds"}, new Object[]{Integer.valueOf(GLFW.GLFW_KEY_KP_6), "zerosuperior"}, new Object[]{150, "onesuperior"}, new Object[]{164, "twosuperior"}, new Object[]{169, "threesuperior"}, new Object[]{Integer.valueOf(GLFW.GLFW_KEY_KP_7), "foursuperior"}, new Object[]{Integer.valueOf(GLFW.GLFW_KEY_KP_8), "fivesuperior"}, new Object[]{Integer.valueOf(GLFW.GLFW_KEY_KP_9), "sixsuperior"}, new Object[]{Integer.valueOf(GLFW.GLFW_KEY_KP_DECIMAL), "sevensuperior"}, new Object[]{Integer.valueOf(GLFW.GLFW_KEY_KP_DIVIDE), "eightsuperior"}, new Object[]{Integer.valueOf(GLFW.GLFW_KEY_KP_MULTIPLY), "ninesuperior"}, new Object[]{Integer.valueOf(GLFW.GLFW_KEY_KP_SUBTRACT), "zeroinferior"}, new Object[]{Integer.valueOf(GLFW.GLFW_KEY_KP_ADD), "oneinferior"}, new Object[]{Integer.valueOf(GLFW.GLFW_KEY_KP_ENTER), "twoinferior"}, new Object[]{Integer.valueOf(GLFW.GLFW_KEY_KP_EQUAL), "threeinferior"}, new Object[]{337, "fourinferior"}, new Object[]{338, "fiveinferior"}, new Object[]{339, "sixinferior"}, new Object[]{Integer.valueOf(GLFW.GLFW_KEY_LEFT_SHIFT), "seveninferior"}, new Object[]{Integer.valueOf(GLFW.GLFW_KEY_LEFT_CONTROL), "eightinferior"}, new Object[]{Integer.valueOf(GLFW.GLFW_KEY_LEFT_ALT), "nineinferior"}, new Object[]{Integer.valueOf(GLFW.GLFW_KEY_LEFT_SUPER), "centinferior"}, new Object[]{Integer.valueOf(GLFW.GLFW_KEY_RIGHT_SHIFT), "dollarinferior"}, new Object[]{Integer.valueOf(GLFW.GLFW_KEY_RIGHT_CONTROL), "periodinferior"}, new Object[]{Integer.valueOf(GLFW.GLFW_KEY_RIGHT_ALT), "commainferior"}};

    /* renamed from: b, reason: collision with root package name */
    private static final h f4204b = new h();

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object[], java.lang.Object[][]] */
    static {
        int i = 0;
        for (Object[] objArr : f4203a) {
            int i2 = i;
            i++;
            f4204b.a(i2, ((Integer) objArr[0]).intValue(), objArr[1].toString());
        }
    }

    private h() {
        super(false);
    }

    public static h b() {
        return f4204b;
    }
}
