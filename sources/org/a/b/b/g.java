package org.a.b.b;

import com.badlogic.gdx.net.HttpStatus;
import com.prineside.tdi2.ui.components.MapEditorItemInfoMenu;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.CGL;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:org/a/b/b/g.class */
public final class g extends e {

    /* renamed from: a, reason: collision with root package name */
    private static final int[][] f4201a = {new int[]{0, 0}, new int[]{1, 0}, new int[]{2, 0}, new int[]{3, 0}, new int[]{4, 0}, new int[]{5, 0}, new int[]{6, 0}, new int[]{7, 0}, new int[]{8, 0}, new int[]{9, 0}, new int[]{10, 0}, new int[]{11, 0}, new int[]{12, 0}, new int[]{13, 0}, new int[]{14, 0}, new int[]{15, 0}, new int[]{16, 0}, new int[]{17, 0}, new int[]{18, 0}, new int[]{19, 0}, new int[]{20, 0}, new int[]{21, 0}, new int[]{22, 0}, new int[]{23, 0}, new int[]{24, 0}, new int[]{25, 0}, new int[]{26, 0}, new int[]{27, 0}, new int[]{28, 0}, new int[]{29, 0}, new int[]{30, 0}, new int[]{31, 0}, new int[]{32, 1}, new int[]{33, User32.VK_PROCESSKEY}, new int[]{34, 230}, new int[]{35, 0}, new int[]{36, User32.VK_PACKET}, new int[]{37, 232}, new int[]{38, User32.VK_OEM_RESET}, new int[]{39, User32.VK_OEM_JUMP}, new int[]{40, 235}, new int[]{41, 236}, new int[]{42, User32.VK_OEM_PA3}, new int[]{43, User32.VK_OEM_WSCTRL}, new int[]{44, 13}, new int[]{45, 14}, new int[]{46, 15}, new int[]{47, 99}, new int[]{48, User32.VK_OEM_CUSEL}, new int[]{49, User32.VK_OEM_ATTN}, new int[]{50, User32.VK_OEM_FINISH}, new int[]{51, User32.VK_OEM_COPY}, new int[]{52, 243}, new int[]{53, User32.VK_OEM_ENLW}, new int[]{54, User32.VK_OEM_BACKTAB}, new int[]{55, User32.VK_ATTN}, new int[]{56, User32.VK_CRSEL}, new int[]{57, User32.VK_EXSEL}, new int[]{58, 27}, new int[]{59, 28}, new int[]{60, User32.VK_EREOF}, new int[]{61, User32.VK_PLAY}, new int[]{62, User32.VK_ZOOM}, new int[]{63, User32.VK_NONAME}, new int[]{64, 0}, new int[]{65, User32.VK_PA1}, new int[]{66, 254}, new int[]{67, 255}, new int[]{68, 256}, new int[]{69, 257}, new int[]{70, 0}, new int[]{71, 0}, new int[]{72, 0}, new int[]{73, 258}, new int[]{74, 0}, new int[]{75, 0}, new int[]{76, 259}, new int[]{77, 260}, new int[]{78, 261}, new int[]{79, 262}, new int[]{80, 0}, new int[]{81, 0}, new int[]{82, 263}, new int[]{83, GLFW.GLFW_KEY_DOWN}, new int[]{84, 265}, new int[]{85, 0}, new int[]{86, GLFW.GLFW_KEY_PAGE_UP}, new int[]{87, 109}, new int[]{88, 110}, new int[]{89, GLFW.GLFW_KEY_PAGE_DOWN}, new int[]{90, GLFW.GLFW_KEY_HOME}, new int[]{91, 269}, new int[]{92, 0}, new int[]{93, User32.WM_IME_ENDCOMPOSITION}, new int[]{94, 271}, new int[]{95, 272}, new int[]{96, 273}, new int[]{97, User32.WM_SYSCOMMAND}, new int[]{98, User32.WM_TIMER}, new int[]{99, User32.WM_HSCROLL}, new int[]{100, User32.WM_VSCROLL}, new int[]{101, User32.WM_INITMENU}, new int[]{102, User32.WM_INITMENUPOPUP}, new int[]{103, GLFW.GLFW_KEY_CAPS_LOCK}, new int[]{104, 281}, new int[]{105, 282}, new int[]{106, GLFW.GLFW_KEY_PRINT_SCREEN}, new int[]{107, GLFW.GLFW_KEY_PAUSE}, new int[]{108, 285}, new int[]{109, 286}, new int[]{110, User32.WM_MENUSELECT}, new int[]{111, User32.WM_MENUCHAR}, new int[]{112, User32.WM_ENTERIDLE}, new int[]{113, 290}, new int[]{114, 291}, new int[]{115, 292}, new int[]{116, 293}, new int[]{117, 294}, new int[]{118, 295}, new int[]{119, 296}, new int[]{120, 297}, new int[]{121, GLFW.GLFW_KEY_F9}, new int[]{122, GLFW.GLFW_KEY_F10}, new int[]{123, 300}, new int[]{124, 301}, new int[]{125, 302}, new int[]{126, 303}, new int[]{127, 0}, new int[]{128, 0}, new int[]{129, 0}, new int[]{130, 0}, new int[]{131, 0}, new int[]{132, 0}, new int[]{133, 0}, new int[]{134, 0}, new int[]{135, 0}, new int[]{136, 0}, new int[]{137, 0}, new int[]{138, 0}, new int[]{139, 0}, new int[]{140, 0}, new int[]{141, 0}, new int[]{142, 0}, new int[]{143, 0}, new int[]{144, 0}, new int[]{145, 0}, new int[]{146, 0}, new int[]{147, 0}, new int[]{148, 0}, new int[]{149, 0}, new int[]{150, 0}, new int[]{151, 0}, new int[]{152, 0}, new int[]{153, 0}, new int[]{154, 0}, new int[]{155, 0}, new int[]{156, 0}, new int[]{157, 0}, new int[]{158, 0}, new int[]{159, 0}, new int[]{160, 0}, new int[]{161, 304}, new int[]{162, 305}, new int[]{163, 306}, new int[]{164, 0}, new int[]{165, 0}, new int[]{166, 307}, new int[]{167, 308}, new int[]{168, 309}, new int[]{169, 310}, new int[]{170, 311}, new int[]{171, 0}, new int[]{172, 312}, new int[]{173, 0}, new int[]{174, 0}, new int[]{175, 313}, new int[]{176, 0}, new int[]{177, 0}, new int[]{178, 314}, new int[]{179, CGL.kCGLCPMPSwapsInFlight}, new int[]{180, 0}, new int[]{181, 0}, new int[]{182, 316}, new int[]{183, 317}, new int[]{184, 318}, new int[]{185, 0}, new int[]{186, 0}, new int[]{187, 0}, new int[]{188, 158}, new int[]{189, 155}, new int[]{190, 163}, new int[]{191, 319}, new int[]{192, GLFW.GLFW_KEY_KP_0}, new int[]{193, GLFW.GLFW_KEY_KP_1}, new int[]{194, GLFW.GLFW_KEY_KP_2}, new int[]{195, GLFW.GLFW_KEY_KP_3}, new int[]{196, GLFW.GLFW_KEY_KP_4}, new int[]{197, GLFW.GLFW_KEY_KP_5}, new int[]{198, 0}, new int[]{199, 0}, new int[]{200, GLFW.GLFW_KEY_KP_6}, new int[]{201, 150}, new int[]{HttpStatus.SC_ACCEPTED, 164}, new int[]{203, 169}, new int[]{HttpStatus.SC_NO_CONTENT, GLFW.GLFW_KEY_KP_7}, new int[]{HttpStatus.SC_RESET_CONTENT, GLFW.GLFW_KEY_KP_8}, new int[]{HttpStatus.SC_PARTIAL_CONTENT, GLFW.GLFW_KEY_KP_9}, new int[]{HttpStatus.SC_MULTI_STATUS, GLFW.GLFW_KEY_KP_DECIMAL}, new int[]{208, GLFW.GLFW_KEY_KP_DIVIDE}, new int[]{209, GLFW.GLFW_KEY_KP_MULTIPLY}, new int[]{210, GLFW.GLFW_KEY_KP_SUBTRACT}, new int[]{211, GLFW.GLFW_KEY_KP_ADD}, new int[]{212, GLFW.GLFW_KEY_KP_ENTER}, new int[]{213, GLFW.GLFW_KEY_KP_EQUAL}, new int[]{214, 337}, new int[]{215, 338}, new int[]{216, 339}, new int[]{217, GLFW.GLFW_KEY_LEFT_SHIFT}, new int[]{218, GLFW.GLFW_KEY_LEFT_CONTROL}, new int[]{User32.VK_OEM_4, GLFW.GLFW_KEY_LEFT_ALT}, new int[]{User32.VK_OEM_5, GLFW.GLFW_KEY_LEFT_SUPER}, new int[]{221, GLFW.GLFW_KEY_RIGHT_SHIFT}, new int[]{222, GLFW.GLFW_KEY_RIGHT_CONTROL}, new int[]{User32.VK_OEM_8, GLFW.GLFW_KEY_RIGHT_ALT}, new int[]{CGL.kCGLCPDispatchTableSize, GLFW.GLFW_KEY_RIGHT_SUPER}, new int[]{User32.VK_OEM_AX, 348}, new int[]{226, 349}, new int[]{User32.VK_ICO_HELP, 350}, new int[]{228, 351}, new int[]{User32.VK_PROCESSKEY, MapEditorItemInfoMenu.MENU_CONTENT_WIDTH}, new int[]{230, 353}, new int[]{User32.VK_PACKET, 354}, new int[]{232, 355}, new int[]{User32.VK_OEM_RESET, 356}, new int[]{User32.VK_OEM_JUMP, 357}, new int[]{235, 358}, new int[]{236, 359}, new int[]{User32.VK_OEM_PA3, 360}, new int[]{User32.VK_OEM_WSCTRL, 361}, new int[]{User32.VK_OEM_CUSEL, 362}, new int[]{User32.VK_OEM_ATTN, 363}, new int[]{User32.VK_OEM_FINISH, 364}, new int[]{User32.VK_OEM_COPY, 365}, new int[]{243, 366}, new int[]{User32.VK_OEM_ENLW, 367}, new int[]{User32.VK_OEM_BACKTAB, 368}, new int[]{User32.VK_ATTN, 369}, new int[]{User32.VK_CRSEL, 370}, new int[]{User32.VK_EXSEL, 371}, new int[]{User32.VK_EREOF, 372}, new int[]{User32.VK_PLAY, 373}, new int[]{User32.VK_ZOOM, 374}, new int[]{User32.VK_NONAME, 375}, new int[]{User32.VK_PA1, 376}, new int[]{254, 377}, new int[]{255, 378}};

    /* renamed from: b, reason: collision with root package name */
    private static final g f4202b = new g();

    /* JADX WARN: Type inference failed for: r0v1, types: [int[], int[][]] */
    static {
        for (int[] iArr : f4201a) {
            f4202b.a(iArr[0], iArr[1]);
        }
    }

    private g() {
    }

    public static g a() {
        return f4202b;
    }
}
