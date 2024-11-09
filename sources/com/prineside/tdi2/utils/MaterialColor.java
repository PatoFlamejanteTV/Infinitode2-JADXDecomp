package com.prineside.tdi2.utils;

import com.badlogic.gdx.graphics.Color;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/MaterialColor.class */
public class MaterialColor {
    public static final int BYTE_PALETTE_COLOR_COUNT = 18;
    public static final int BYTE_PALETTE_VARIANT_COUNT = 5;
    public static final Color[] BYTE_PALETTE = {RED.P200, RED.P300, RED.P500, RED.P700, RED.P900, PINK.P200, PINK.P300, PINK.P500, PINK.P700, PINK.P900, PURPLE.P200, PURPLE.P300, PURPLE.P500, PURPLE.P700, PURPLE.P900, DEEP_PURPLE.P200, DEEP_PURPLE.P300, DEEP_PURPLE.P500, DEEP_PURPLE.P700, DEEP_PURPLE.P900, INDIGO.P200, INDIGO.P300, INDIGO.P500, INDIGO.P700, INDIGO.P900, BLUE.P200, BLUE.P300, BLUE.P500, BLUE.P700, BLUE.P900, CYAN.P200, CYAN.P300, CYAN.P500, CYAN.P700, CYAN.P900, TEAL.P200, TEAL.P300, TEAL.P500, TEAL.P700, TEAL.P900, GREEN.P200, GREEN.P300, GREEN.P500, GREEN.P700, GREEN.P900, LIGHT_GREEN.P200, LIGHT_GREEN.P300, LIGHT_GREEN.P500, LIGHT_GREEN.P700, LIGHT_GREEN.P900, LIME.P200, LIME.P300, LIME.P500, LIME.P700, LIME.P900, YELLOW.P200, YELLOW.P300, YELLOW.P500, YELLOW.P700, YELLOW.P900, AMBER.P200, AMBER.P300, AMBER.P500, AMBER.P700, AMBER.P900, ORANGE.P200, ORANGE.P300, ORANGE.P500, ORANGE.P700, ORANGE.P900, DEEP_ORANGE.P200, DEEP_ORANGE.P300, DEEP_ORANGE.P500, DEEP_ORANGE.P700, DEEP_ORANGE.P900, BROWN.P200, BROWN.P300, BROWN.P500, BROWN.P700, BROWN.P900, GREY.P200, GREY.P300, GREY.P500, GREY.P700, GREY.P900, BLUE_GREY.P200, BLUE_GREY.P300, BLUE_GREY.P500, BLUE_GREY.P700, BLUE_GREY.P900, Color.BLACK, Color.WHITE};
    public static final Color[][] allColors = {RED.values, PINK.values, PURPLE.values, DEEP_PURPLE.values, INDIGO.values, BLUE.values, LIGHT_BLUE.values, CYAN.values, TEAL.values, GREEN.values, LIGHT_GREEN.values, LIME.values, YELLOW.values, AMBER.values, ORANGE.values, DEEP_ORANGE.values, BROWN.values, GREY.values, BLUE_GREY.values};

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/MaterialColor$Colors.class */
    public enum Colors {
        RED,
        PINK,
        PURPLE,
        DEEP_PURPLE,
        INDIGO,
        BLUE,
        LIGHT_BLUE,
        CYAN,
        TEAL,
        GREEN,
        LIGHT_GREEN,
        LIME,
        YELLOW,
        AMBER,
        ORANGE,
        DEEP_ORANGE,
        BROWN,
        GREY,
        BLUE_GREY;

        public static final Colors[] values = values();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/MaterialColor$Variants.class */
    public enum Variants {
        P50,
        P100,
        P200,
        P300,
        P400,
        P500,
        P600,
        P700,
        P800,
        P900,
        A100,
        A200,
        A400,
        A700;

        public static final Variants[] values = values();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/MaterialColor$RED.class */
    public static class RED {
        public static final Color P50 = new Color(-1315073);
        public static final Color P100 = new Color(-3288321);
        public static final Color P200 = new Color(-275080449);
        public static final Color P300 = new Color(-445418497);
        public static final Color P400 = new Color(-279752449);
        public static final Color P500 = new Color(-196921601);
        public static final Color P600 = new Color(-449235457);
        public static final Color P700 = new Color(-751882241);
        public static final Color P800 = new Color(-970446593);
        public static final Color P900 = new Color(-1222894337);
        public static final Color A100 = new Color(-7700225);
        public static final Color A200 = new Color(-11382017);
        public static final Color A400 = new Color(-15252225);
        public static final Color A700 = new Color(-721420033);
        public static final Color[] values = {P50, P100, P200, P300, P400, P500, P600, P700, P800, P900, A100, A200, A400, A700};

        private RED() {
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/MaterialColor$PINK.class */
    public static class PINK {
        public static final Color P50 = new Color(-52105985);
        public static final Color P100 = new Color(-121908993);
        public static final Color P200 = new Color(-191909377);
        public static final Color P300 = new Color(-261975297);
        public static final Color P400 = new Color(-331318529);
        public static final Color P500 = new Color(-383884289);
        public static final Color P600 = new Color(-669294337);
        public static final Color P700 = new Color(-1038590977);
        public static final Color P800 = new Color(-1391175681);
        public static final Color P900 = new Color(-2012327937);
        public static final Color A100 = new Color(-8344577);
        public static final Color A200 = new Color(-12549633);
        public static final Color A400 = new Color(-184526849);
        public static final Color A700 = new Color(-988716289);
        public static final Color[] values = {P50, P100, P200, P300, P400, P500, P600, P700, P800, P900, A100, A200, A400, A700};

        private PINK() {
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/MaterialColor$PURPLE.class */
    public static class PURPLE {
        public static final Color P50 = new Color(-203033089);
        public static final Color P100 = new Color(-507582465);
        public static final Color P200 = new Color(-829171457);
        public static final Color P300 = new Color(-1167537921);
        public static final Color P400 = new Color(-1421361921);
        public static final Color P500 = new Color(-1675120385);
        public static final Color P600 = new Color(-1910199553);
        public static final Color P700 = new Color(2065670911);
        public static final Color P800 = new Color(1780194047);
        public static final Color P900 = new Color(1242860799);
        public static final Color A100 = new Color(-360645377);
        public static final Color A200 = new Color(-532612097);
        public static final Color A400 = new Color(-721356289);
        public static final Color A700 = new Color(-1442775041);
        public static final Color[] values = {P50, P100, P200, P300, P400, P500, P600, P700, P800, P900, A100, A200, A400, A700};

        private PURPLE() {
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/MaterialColor$DEEP_PURPLE.class */
    public static class DEEP_PURPLE {
        public static final Color P50 = new Color(-303565057);
        public static final Color P100 = new Color(-775624193);
        public static final Color P200 = new Color(-1281500161);
        public static final Color P300 = new Color(-1787441665);
        public static final Color P400 = new Color(2119680767);
        public static final Color P500 = new Color(1731901439);
        public static final Color P600 = new Color(1580577279);
        public static final Color P700 = new Color(1361946879);
        public static final Color P800 = new Color(1160225023);
        public static final Color P900 = new Color(823890687);
        public static final Color A100 = new Color(-1282867201);
        public static final Color A200 = new Color(2085486591);
        public static final Color A400 = new Color(1696595967);
        public static final Color A700 = new Color(1644227327);
        public static final Color[] values = {P50, P100, P200, P300, P400, P500, P600, P700, P800, P900, A100, A200, A400, A700};

        private DEEP_PURPLE() {
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/MaterialColor$INDIGO.class */
    public static class INDIGO {
        public static final Color P50 = new Color(-387254529);
        public static final Color P100 = new Color(-976557569);
        public static final Color P200 = new Color(-1616323841);
        public static final Color P300 = new Color(2038877183);
        public static final Color P400 = new Color(1550565631);
        public static final Color P500 = new Color(1062319615);
        public static final Color P600 = new Color(961129471);
        public static final Color P700 = new Color(809476095);
        public static final Color P800 = new Color(674599935);
        public static final Color P900 = new Color(438533887);
        public static final Color A100 = new Color(-1935736833);
        public static final Color A200 = new Color(1399717631);
        public static final Color A400 = new Color(1029373695);
        public static final Color A700 = new Color(810548991);
        public static final Color[] values = {P50, P100, P200, P300, P400, P500, P600, P700, P800, P900, A100, A200, A400, A700};

        private INDIGO() {
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/MaterialColor$BLUE.class */
    public static class BLUE {
        public static final Color P50 = new Color(-470614529);
        public static final Color P100 = new Color(-1143014401);
        public static final Color P200 = new Color(-1865745921);
        public static final Color P300 = new Color(1689646847);
        public static final Color P400 = new Color(1118172671);
        public static final Color P500 = new Color(563540991);
        public static final Color P600 = new Color(512288255);
        public static final Color P700 = new Color(427217663);
        public static final Color P800 = new Color(358990079);
        public static final Color P900 = new Color(222798335);
        public static final Color A100 = new Color(-2102263809);
        public static final Color A200 = new Color(1149960191);
        public static final Color A400 = new Color(695861247);
        public static final Color A700 = new Color(694353919);
        public static final Color[] values = {P50, P100, P200, P300, P400, P500, P600, P700, P800, P900, A100, A200, A400, A700};

        private BLUE() {
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/MaterialColor$LIGHT_BLUE.class */
    public static class LIGHT_BLUE {
        public static final Color P50 = new Color(-503972097);
        public static final Color P100 = new Color(-1276773121);
        public static final Color P200 = new Color(-2116748545);
        public static final Color P300 = new Color(1338243071);
        public static final Color P400 = new Color(699856639);
        public static final Color P500 = new Color(61469951);
        public static final Color P600 = new Color(60548607);
        public static final Color P700 = new Color(42521087);
        public static final Color P800 = new Color(41401855);
        public static final Color P900 = new Color(22518783);
        public static final Color A100 = new Color(-2133262337);
        public static final Color A200 = new Color(1086652415);
        public static final Color A400 = new Color(11599871);
        public static final Color A700 = new Color(9562879);
        public static final Color[] values = {P50, P100, P200, P300, P400, P500, P600, P700, P800, P900, A100, A200, A400, A700};

        private LIGHT_BLUE() {
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/MaterialColor$CYAN.class */
    public static class CYAN {
        public static final Color P50 = new Color(-520619265);
        public static final Color P100 = new Color(-1293159681);
        public static final Color P200 = new Color(-2132874497);
        public static final Color P300 = new Color(1305534975);
        public static final Color P400 = new Color(650566399);
        public static final Color P500 = new Color(12375295);
        public static final Color P600 = new Color(11321855);
        public static final Color P700 = new Color(9938943);
        public static final Color P800 = new Color(8622079);
        public static final Color P900 = new Color(6317311);
        public static final Color A100 = new Color(-2063597569);
        public static final Color A200 = new Color(419430399);
        public static final Color A400 = new Color(15073279);
        public static final Color A700 = new Color(12113151);
        public static final Color[] values = {P50, P100, P200, P300, P400, P500, P600, P700, P800, P900, A100, A200, A400, A700};

        private CYAN() {
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/MaterialColor$TEAL.class */
    public static class TEAL {
        public static final Color P50 = new Color(-520949249);
        public static final Color P100 = new Color(-1293952001);
        public static final Color P200 = new Color(-2134129409);
        public static final Color P300 = new Color(1303817471);
        public static final Color P400 = new Color(648452863);
        public static final Color P500 = new Color(9865471);
        public static final Color P600 = new Color(9010175);
        public static final Color P700 = new Color(7957503);
        public static final Color P800 = new Color(6905087);
        public static final Color P900 = new Color(5062911);
        public static final Color A100 = new Color(-1476400129);
        public static final Color A200 = new Color(1694489343);
        public static final Color A400 = new Color(501855999);
        public static final Color A700 = new Color(12559871);
        public static final Color[] values = {P50, P100, P200, P300, P400, P500, P600, P700, P800, P900, A100, A200, A400, A700};

        private TEAL() {
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/MaterialColor$GREEN.class */
    public static class GREEN {
        public static final Color P50 = new Color(-386536961);
        public static final Color P100 = new Color(-924399105);
        public static final Color P200 = new Color(-1512658945);
        public static final Color P300 = new Color(-2117630721);
        public static final Color P400 = new Color(1723558655);
        public static final Color P500 = new Color(1286557951);
        public static final Color P600 = new Color(1134577663);
        public static final Color P700 = new Color(948845823);
        public static final Color P800 = new Color(779956991);
        public static final Color P900 = new Color(459153663);
        public static final Color A100 = new Color(-1175008513);
        public static final Color A200 = new Color(1777381119);
        public static final Color A400 = new Color(15103743);
        public static final Color A700 = new Color(13128703);
        public static final Color[] values = {P50, P100, P200, P300, P400, P500, P600, P700, P800, P900, A100, A200, A400, A700};

        private GREEN() {
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/MaterialColor$LIGHT_GREEN.class */
    public static class LIGHT_GREEN {
        public static final Color P50 = new Color(-235345409);
        public static final Color P100 = new Color(-588396289);
        public static final Color P200 = new Color(-975067649);
        public static final Color P300 = new Color(-1361739265);
        public static final Color P400 = new Color(-1664326145);
        public static final Color P500 = new Color(-1950135553);
        public static final Color P600 = new Color(2092122879);
        public static final Color P700 = new Color(1755265279);
        public static final Color P800 = new Color(1435185151);
        public static final Color P900 = new Color(862527231);
        public static final Color A100 = new Color(-855666433);
        public static final Color A200 = new Color(-1291888129);
        public static final Color A400 = new Color(1996424191);
        public static final Color A700 = new Color(1692211199);
        public static final Color[] values = {P50, P100, P200, P300, P400, P500, P600, P700, P800, P900, A100, A200, A400, A700};

        private LIGHT_GREEN() {
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/MaterialColor$LIME.class */
    public static class LIME {
        public static final Color P50 = new Color(-100931585);
        public static final Color P100 = new Color(-252394497);
        public static final Color P200 = new Color(-420569857);
        public static final Color P300 = new Color(-588810753);
        public static final Color P400 = new Color(-723429377);
        public static final Color P500 = new Color(-841205249);
        public static final Color P600 = new Color(-1060490241);
        public static final Color P700 = new Color(-1347146753);
        public static final Color P800 = new Color(-1633868545);
        public static final Color P900 = new Color(-2106124289);
        public static final Color A100 = new Color(-184581633);
        public static final Color A200 = new Color(-285261313);
        public static final Color A400 = new Color(-956366593);
        public static final Color A700 = new Color(-1360396033);
        public static final Color[] values = {P50, P100, P200, P300, P400, P500, P600, P700, P800, P900, A100, A200, A400, A700};

        private LIME() {
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/MaterialColor$YELLOW.class */
    public static class YELLOW {
        public static final Color P50 = new Color(-137217);
        public static final Color P100 = new Color(-408321);
        public static final Color P200 = new Color(-680449);
        public static final Color P300 = new Color(-952577);
        public static final Color P400 = new Color(-1156865);
        public static final Color P500 = new Color(-1360897);
        public static final Color P600 = new Color(-36162049);
        public static final Color P700 = new Color(-71291393);
        public static final Color P800 = new Color(-106420737);
        public static final Color P900 = new Color(-176220161);
        public static final Color A100 = new Color(-29185);
        public static final Color A200 = new Color(-65281);
        public static final Color A400 = new Color(-1441537);
        public static final Color A700 = new Color(-2752257);
        public static final Color[] values = {P50, P100, P200, P300, P400, P500, P600, P700, P800, P900, A100, A200, A400, A700};

        private YELLOW() {
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/MaterialColor$AMBER.class */
    public static class AMBER {
        public static final Color P50 = new Color(-466433);
        public static final Color P100 = new Color(-1264641);
        public static final Color P200 = new Color(-2063617);
        public static final Color P300 = new Color(-2797569);
        public static final Color P400 = new Color(-3528449);
        public static final Color P500 = new Color(-4126721);
        public static final Color P600 = new Color(-5046017);
        public static final Color P700 = new Color(-6291201);
        public static final Color P800 = new Color(-7405313);
        public static final Color P900 = new Color(-9502465);
        public static final Color A100 = new Color(-1736705);
        public static final Color A200 = new Color(-2670337);
        public static final Color A400 = new Color(-3931905);
        public static final Color A700 = new Color(-5570305);
        public static final Color[] values = {P50, P100, P200, P300, P400, P500, P600, P700, P800, P900, A100, A200, A400, A700};

        private AMBER() {
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/MaterialColor$ORANGE.class */
    public static class ORANGE {
        public static final Color P50 = new Color(-794369);
        public static final Color P100 = new Color(-2051329);
        public static final Color P200 = new Color(-3374849);
        public static final Color P300 = new Color(-4764161);
        public static final Color P400 = new Color(-5822721);
        public static final Color P500 = new Color(-6815489);
        public static final Color P600 = new Color(-74710785);
        public static final Color P700 = new Color(-176422657);
        public static final Color P800 = new Color(-278134529);
        public static final Color P900 = new Color(-430898945);
        public static final Color A100 = new Color(-3047169);
        public static final Color A200 = new Color(-5553921);
        public static final Color A400 = new Color(-7274241);
        public static final Color A700 = new Color(-9633537);
        public static final Color[] values = {P50, P100, P200, P300, P400, P500, P600, P700, P800, P900, A100, A200, A400, A700};

        private ORANGE() {
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/MaterialColor$DEEP_ORANGE.class */
    public static class DEEP_ORANGE {
        public static final Color P50 = new Color(-68556801);
        public static final Color P100 = new Color(-3359489);
        public static final Color P200 = new Color(-5533185);
        public static final Color P300 = new Color(-7707137);
        public static final Color P400 = new Color(-9419777);
        public static final Color P500 = new Color(-11066625);
        public static final Color P600 = new Color(-196010241);
        public static final Color P700 = new Color(-431351297);
        public static final Color P800 = new Color(-666692097);
        public static final Color P900 = new Color(-1086976769);
        public static final Color A100 = new Color(-6389505);
        public static final Color A200 = new Color(-9551617);
        public static final Color A400 = new Color(-12779265);
        public static final Color A700 = new Color(-584318721);
        public static final Color[] values = {P50, P100, P200, P300, P400, P500, P600, P700, P800, P900, A100, A200, A400, A700};

        private DEEP_ORANGE() {
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/MaterialColor$BROWN.class */
    public static class BROWN {
        public static final Color P50 = new Color(-269751809);
        public static final Color P100 = new Color(-674445057);
        public static final Color P200 = new Color(-1129667329);
        public static final Color P300 = new Color(-1584889857);
        public static final Color P400 = new Color(-1922145281);
        public static final Color P500 = new Color(2035632383);
        public static final Color P600 = new Color(1833714175);
        public static final Color P700 = new Color(1564489727);
        public static final Color P800 = new Color(1312042751);
        public static final Color P900 = new Color(1042752511);
        public static final Color[] values = {P50, P100, P200, P300, P400, P500, P600, P700, P800, P900};

        private BROWN() {
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/MaterialColor$GREY.class */
    public static class GREY {
        public static final Color P50 = new Color(-84215041);
        public static final Color P100 = new Color(-168430081);
        public static final Color P200 = new Color(-286331137);
        public static final Color P300 = new Color(-522133249);
        public static final Color P400 = new Color(-1111638529);
        public static final Color P500 = new Color(-1633771777);
        public static final Color P600 = new Color(1970632191);
        public static final Color P700 = new Color(1633772031);
        public static final Color P800 = new Color(1111638783);
        public static final Color P900 = new Color(555819519);
        public static final Color[] values = {P50, P100, P200, P300, P400, P500, P600, P700, P800, P900};

        private GREY() {
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/MaterialColor$BLUE_GREY.class */
    public static class BLUE_GREY {
        public static final Color P50 = new Color(-319819265);
        public static final Color P100 = new Color(-807871233);
        public static final Color P200 = new Color(-1329674753);
        public static final Color P300 = new Color(-1868255489);
        public static final Color P400 = new Color(2022743295);
        public static final Color P500 = new Color(1618840575);
        public static final Color P600 = new Color(1416526591);
        public static final Color P700 = new Color(1163551999);
        public static final Color P800 = new Color(927420415);
        public static final Color P900 = new Color(640825599);
        public static final Color[] values = {P50, P100, P200, P300, P400, P500, P600, P700, P800, P900};

        private BLUE_GREY() {
        }
    }
}
