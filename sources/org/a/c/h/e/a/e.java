package org.a.c.h.e.a;

import com.badlogic.gdx.net.HttpStatus;
import org.lwjgl.opengl.CGL;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:org/a/c/h/e/a/e.class */
public final class e extends c {
    private static final Object[][] d = {new Object[]{190, "AEsmall"}, new Object[]{135, "Aacutesmall"}, new Object[]{137, "Acircumflexsmall"}, new Object[]{39, "Acutesmall"}, new Object[]{138, "Adieresissmall"}, new Object[]{136, "Agravesmall"}, new Object[]{140, "Aringsmall"}, new Object[]{97, "Asmall"}, new Object[]{139, "Atildesmall"}, new Object[]{243, "Brevesmall"}, new Object[]{98, "Bsmall"}, new Object[]{174, "Caronsmall"}, new Object[]{141, "Ccedillasmall"}, new Object[]{201, "Cedillasmall"}, new Object[]{94, "Circumflexsmall"}, new Object[]{99, "Csmall"}, new Object[]{172, "Dieresissmall"}, new Object[]{Integer.valueOf(User32.VK_PLAY), "Dotaccentsmall"}, new Object[]{100, "Dsmall"}, new Object[]{142, "Eacutesmall"}, new Object[]{144, "Ecircumflexsmall"}, new Object[]{145, "Edieresissmall"}, new Object[]{143, "Egravesmall"}, new Object[]{101, "Esmall"}, new Object[]{68, "Ethsmall"}, new Object[]{102, "Fsmall"}, new Object[]{96, "Gravesmall"}, new Object[]{103, "Gsmall"}, new Object[]{104, "Hsmall"}, new Object[]{34, "Hungarumlautsmall"}, new Object[]{146, "Iacutesmall"}, new Object[]{148, "Icircumflexsmall"}, new Object[]{149, "Idieresissmall"}, new Object[]{147, "Igravesmall"}, new Object[]{105, "Ismall"}, new Object[]{106, "Jsmall"}, new Object[]{107, "Ksmall"}, new Object[]{194, "Lslashsmall"}, new Object[]{108, "Lsmall"}, new Object[]{Integer.valueOf(User32.VK_OEM_ENLW), "Macronsmall"}, new Object[]{109, "Msmall"}, new Object[]{110, "Nsmall"}, new Object[]{150, "Ntildesmall"}, new Object[]{Integer.valueOf(HttpStatus.SC_MULTI_STATUS), "OEsmall"}, new Object[]{151, "Oacutesmall"}, new Object[]{153, "Ocircumflexsmall"}, new Object[]{154, "Odieresissmall"}, new Object[]{Integer.valueOf(User32.VK_OEM_COPY), "Ogoneksmall"}, new Object[]{152, "Ogravesmall"}, new Object[]{191, "Oslashsmall"}, new Object[]{111, "Osmall"}, new Object[]{155, "Otildesmall"}, new Object[]{112, "Psmall"}, new Object[]{113, "Qsmall"}, new Object[]{Integer.valueOf(User32.VK_ZOOM), "Ringsmall"}, new Object[]{114, "Rsmall"}, new Object[]{167, "Scaronsmall"}, new Object[]{115, "Ssmall"}, new Object[]{185, "Thornsmall"}, new Object[]{126, "Tildesmall"}, new Object[]{116, "Tsmall"}, new Object[]{156, "Uacutesmall"}, new Object[]{158, "Ucircumflexsmall"}, new Object[]{159, "Udieresissmall"}, new Object[]{157, "Ugravesmall"}, new Object[]{117, "Usmall"}, new Object[]{118, "Vsmall"}, new Object[]{119, "Wsmall"}, new Object[]{120, "Xsmall"}, new Object[]{180, "Yacutesmall"}, new Object[]{216, "Ydieresissmall"}, new Object[]{121, "Ysmall"}, new Object[]{189, "Zcaronsmall"}, new Object[]{122, "Zsmall"}, new Object[]{38, "ampersandsmall"}, new Object[]{129, "asuperior"}, new Object[]{Integer.valueOf(User32.VK_OEM_BACKTAB), "bsuperior"}, new Object[]{169, "centinferior"}, new Object[]{35, "centoldstyle"}, new Object[]{130, "centsuperior"}, new Object[]{58, "colon"}, new Object[]{123, "colonmonetary"}, new Object[]{44, "comma"}, new Object[]{178, "commainferior"}, new Object[]{Integer.valueOf(User32.VK_EXSEL), "commasuperior"}, new Object[]{182, "dollarinferior"}, new Object[]{36, "dollaroldstyle"}, new Object[]{37, "dollarsuperior"}, new Object[]{235, "dsuperior"}, new Object[]{165, "eightinferior"}, new Object[]{56, "eightoldstyle"}, new Object[]{161, "eightsuperior"}, new Object[]{228, "esuperior"}, new Object[]{214, "exclamdownsmall"}, new Object[]{33, "exclamsmall"}, new Object[]{86, "ff"}, new Object[]{89, "ffi"}, new Object[]{90, "ffl"}, new Object[]{87, "fi"}, new Object[]{208, "figuredash"}, new Object[]{76, "fiveeighths"}, new Object[]{176, "fiveinferior"}, new Object[]{53, "fiveoldstyle"}, new Object[]{222, "fivesuperior"}, new Object[]{88, "fl"}, new Object[]{162, "fourinferior"}, new Object[]{52, "fouroldstyle"}, new Object[]{221, "foursuperior"}, new Object[]{47, "fraction"}, new Object[]{45, "hyphen"}, new Object[]{95, "hypheninferior"}, new Object[]{209, "hyphensuperior"}, new Object[]{Integer.valueOf(User32.VK_OEM_RESET), "isuperior"}, new Object[]{Integer.valueOf(User32.VK_OEM_FINISH), "lsuperior"}, new Object[]{Integer.valueOf(User32.VK_CRSEL), "msuperior"}, new Object[]{187, "nineinferior"}, new Object[]{57, "nineoldstyle"}, new Object[]{Integer.valueOf(User32.VK_OEM_AX), "ninesuperior"}, new Object[]{Integer.valueOf(User32.VK_ATTN), "nsuperior"}, new Object[]{43, "onedotenleader"}, new Object[]{74, "oneeighth"}, new Object[]{124, "onefitted"}, new Object[]{72, "onehalf"}, new Object[]{193, "oneinferior"}, new Object[]{49, "oneoldstyle"}, new Object[]{71, "onequarter"}, new Object[]{218, "onesuperior"}, new Object[]{78, "onethird"}, new Object[]{175, "osuperior"}, new Object[]{91, "parenleftinferior"}, new Object[]{40, "parenleftsuperior"}, new Object[]{93, "parenrightinferior"}, new Object[]{41, "parenrightsuperior"}, new Object[]{46, "period"}, new Object[]{179, "periodinferior"}, new Object[]{Integer.valueOf(User32.VK_EREOF), "periodsuperior"}, new Object[]{192, "questiondownsmall"}, new Object[]{63, "questionsmall"}, new Object[]{Integer.valueOf(User32.VK_PROCESSKEY), "rsuperior"}, new Object[]{125, "rupiah"}, new Object[]{59, "semicolon"}, new Object[]{77, "seveneighths"}, new Object[]{166, "seveninferior"}, new Object[]{55, "sevenoldstyle"}, new Object[]{Integer.valueOf(CGL.kCGLCPDispatchTableSize), "sevensuperior"}, new Object[]{164, "sixinferior"}, new Object[]{54, "sixoldstyle"}, new Object[]{Integer.valueOf(User32.VK_OEM_8), "sixsuperior"}, new Object[]{32, "space"}, new Object[]{Integer.valueOf(User32.VK_OEM_JUMP), "ssuperior"}, new Object[]{75, "threeeighths"}, new Object[]{163, "threeinferior"}, new Object[]{51, "threeoldstyle"}, new Object[]{73, "threequarters"}, new Object[]{61, "threequartersemdash"}, new Object[]{Integer.valueOf(User32.VK_OEM_5), "threesuperior"}, new Object[]{230, "tsuperior"}, new Object[]{42, "twodotenleader"}, new Object[]{170, "twoinferior"}, new Object[]{50, "twooldstyle"}, new Object[]{Integer.valueOf(User32.VK_OEM_4), "twosuperior"}, new Object[]{79, "twothirds"}, new Object[]{188, "zeroinferior"}, new Object[]{48, "zerooldstyle"}, new Object[]{226, "zerosuperior"}};
    public static final e c = new e();

    public e() {
        for (Object[] objArr : d) {
            a(((Integer) objArr[0]).intValue(), objArr[1].toString());
        }
    }

    @Override // org.a.c.h.a.c
    public final org.a.c.b.b f() {
        return org.a.c.b.j.cc;
    }

    @Override // org.a.c.h.e.a.c
    public final String a() {
        return "MacExpertEncoding";
    }
}