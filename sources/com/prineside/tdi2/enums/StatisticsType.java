package com.prineside.tdi2.enums;

import com.prineside.tdi2.Game;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/enums/StatisticsType.class */
public enum StatisticsType {
    WIP,
    TMS,
    TDD,
    TDDNC,
    SDD,
    TB,
    TS,
    TU,
    TEK,
    TMS_B,
    TDD_B,
    TB_B,
    TS_B,
    TU_B,
    TEK_B,
    TMS_C,
    TDD_C,
    TB_C,
    TS_C,
    TU_C,
    TEK_C,
    TMS_S,
    TDD_S,
    TB_S,
    TS_S,
    TU_S,
    TEK_S,
    TMS_F,
    TDD_F,
    TB_F,
    TS_F,
    TU_F,
    TEK_F,
    TMS_V,
    TDD_V,
    TB_V,
    TS_V,
    TU_V,
    TEK_V,
    TMS_SP,
    TDD_SP,
    TB_SP,
    TS_SP,
    TU_SP,
    TEK_SP,
    TMS_BL,
    TDD_BL,
    TB_BL,
    TS_BL,
    TU_BL,
    TEK_BL,
    TMS_M,
    TDD_M,
    TB_M,
    TS_M,
    TU_M,
    TEK_M,
    TMS_MI,
    TDD_MI,
    TB_MI,
    TS_MI,
    TU_MI,
    TEK_MI,
    TMS_A,
    TDD_A,
    TB_A,
    TS_A,
    TU_A,
    TEK_A,
    TMS_T,
    TDD_T,
    TB_T,
    TS_T,
    TU_T,
    TEK_T,
    TMS_MS,
    TDD_MS,
    TB_MS,
    TS_MS,
    TU_MS,
    TEK_MS,
    TMS_FL,
    TDD_FL,
    TB_FL,
    TS_FL,
    TU_FL,
    TEK_FL,
    TMS_L,
    TDD_L,
    TB_L,
    TS_L,
    TU_L,
    TEK_L,
    TMS_G,
    TDD_G,
    TB_G,
    TS_G,
    TU_G,
    TEK_G,
    TMS_CR,
    TDD_CR,
    TB_CR,
    TS_CR,
    TU_CR,
    TEK_CR,
    MMS,
    MB,
    MU,
    MMS_S,
    MB_S,
    MU_S,
    MMS_V,
    MB_V,
    MU_V,
    MMS_M,
    MB_M,
    MU_M,
    MMS_T,
    MB_T,
    MU_T,
    MMS_I,
    MB_I,
    MU_I,
    RG,
    RG_S,
    RG_V,
    RG_M,
    RG_T,
    RG_I,
    RS,
    RS_S,
    RS_V,
    RS_M,
    RS_T,
    RS_I,
    CG,
    EK,
    EP,
    GPG,
    GPS,
    BDS,
    WD,
    SG,
    AFPTG,
    BDFTPG,
    GGIG,
    EQCG,
    GS,
    GSUM,
    PT,
    PTEMWD,
    PTCL,
    PRT,
    WC,
    WCST,
    WCGC,
    WCGS,
    SG_EK,
    SG_RM,
    SG_WCA,
    SG_WCL,
    EB,
    EB_P,
    EB_S,
    EB_I,
    EB_F,
    EB_TB,
    EB_BC,
    EB_CR,
    EB_BXP,
    EB_DE,
    EB_SL,
    EB_V,
    EB_INV,
    KEW_A,
    KEW_B,
    KEW_F,
    KEW_P,
    KEW_E,
    KEW_EL,
    KEW_L,
    MBS,
    TBS,
    RVV,
    RVW,
    RC,
    RCL,
    PQR,
    PMS,
    PPG,
    CG_B,
    CG_EK,
    CG_WC,
    CG_PG,
    CG_U,
    XPG_TG,
    XPG_EK,
    XPG_BB,
    XPG_EM,
    SOP;

    public static final StatisticsType[] values = values();

    @Override // java.lang.Enum
    public final String toString() {
        return Game.i.statisticsManager.getStatisticsTitle(this).toString() + " (" + name() + ")";
    }
}