package com.d.c.d.a;

import com.badlogic.gdx.net.HttpStatus;
import java.util.HashMap;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/d/c/d/a/f.class */
public final class f {

    /* renamed from: a, reason: collision with root package name */
    private static final Map f995a = new HashMap();

    /* renamed from: b, reason: collision with root package name */
    private static final Map f996b = new HashMap();
    private static final Map c = new HashMap();

    static {
        f995a.put("cyan", new com.d.c.d.h(65535));
        f995a.put("magenta", new com.d.c.d.h(16711935));
        f995a.put("black", new com.d.c.d.h(0));
        f995a.put("gray", new com.d.c.d.h(8421504));
        f995a.put("grey", new com.d.c.d.h(8421504));
        f995a.put("maroon", new com.d.c.d.h(8388608));
        f995a.put("red", new com.d.c.d.h(16711680));
        f995a.put("green", new com.d.c.d.h(32768));
        f995a.put("lime", new com.d.c.d.h(65280));
        f995a.put("olive", new com.d.c.d.h(8421376));
        f995a.put("yellow", new com.d.c.d.h(16776960));
        f995a.put("navy", new com.d.c.d.h(128));
        f995a.put("blue", new com.d.c.d.h(255));
        f995a.put("purple", new com.d.c.d.h(8388736));
        f995a.put("fuchsia", new com.d.c.d.h(16711935));
        f995a.put("teal", new com.d.c.d.h(32896));
        f995a.put("aqua", new com.d.c.d.h(65535));
        f995a.put("silver", new com.d.c.d.h(12632256));
        f995a.put("white", new com.d.c.d.h(16777215));
        f995a.put("aliceblue", new com.d.c.d.h(15792383));
        f995a.put("antiquewhite", new com.d.c.d.h(16444375));
        f995a.put("aquamarine", new com.d.c.d.h(8388564));
        f995a.put("azure", new com.d.c.d.h(15794175));
        f995a.put("beige", new com.d.c.d.h(16119260));
        f995a.put("blueviolet", new com.d.c.d.h(9055202));
        f995a.put("brown", new com.d.c.d.h(10824234));
        f995a.put("burlywood", new com.d.c.d.h(14596231));
        f995a.put("cadetblue", new com.d.c.d.h(6266528));
        f995a.put("chartreuse", new com.d.c.d.h(8388352));
        f995a.put("chocolate", new com.d.c.d.h(13789470));
        f995a.put("coral", new com.d.c.d.h(16744272));
        f995a.put("cornflowerblue", new com.d.c.d.h(6591981));
        f995a.put("cornsilk", new com.d.c.d.h(16775388));
        f995a.put("crimson", new com.d.c.d.h(14423100));
        f995a.put("darkblue", new com.d.c.d.h(139));
        f995a.put("darkcyan", new com.d.c.d.h(35723));
        f995a.put("darkgoldenrod", new com.d.c.d.h(12092939));
        f995a.put("darkgray", new com.d.c.d.h(11119017));
        f995a.put("darkgreen", new com.d.c.d.h(25600));
        f995a.put("darkkhaki", new com.d.c.d.h(12433259));
        f995a.put("darkmagenta", new com.d.c.d.h(9109643));
        f995a.put("darkolivegreen", new com.d.c.d.h(5597999));
        f995a.put("darkorange", new com.d.c.d.h(16747520));
        f995a.put("darkorchid", new com.d.c.d.h(10040012));
        f995a.put("darkred", new com.d.c.d.h(9109504));
        f995a.put("darksalmon", new com.d.c.d.h(15308410));
        f995a.put("darkseagreen", new com.d.c.d.h(9419919));
        f995a.put("darkslateblue", new com.d.c.d.h(4734347));
        f995a.put("darkslategray", new com.d.c.d.h(3100495));
        f995a.put("darkturquoise", new com.d.c.d.h(52945));
        f995a.put("darkviolet", new com.d.c.d.h(9699539));
        f995a.put("deeppink", new com.d.c.d.h(16716947));
        f995a.put("deepskyblue", new com.d.c.d.h(49151));
        f995a.put("dimgray", new com.d.c.d.h(6908265));
        f995a.put("dodgerblue", new com.d.c.d.h(2003199));
        f995a.put("firebrick", new com.d.c.d.h(11674146));
        f995a.put("floralwhite", new com.d.c.d.h(16775920));
        f995a.put("forestgreen", new com.d.c.d.h(2263842));
        f995a.put("gainsboro", new com.d.c.d.h(14474460));
        f995a.put("ghostwhite", new com.d.c.d.h(16316671));
        f995a.put("gold", new com.d.c.d.h(16766720));
        f995a.put("goldenrod", new com.d.c.d.h(14329120));
        f995a.put("greenyellow", new com.d.c.d.h(11403055));
        f995a.put("honeydew", new com.d.c.d.h(15794160));
        f995a.put("hotpink", new com.d.c.d.h(16738740));
        f995a.put("indianred", new com.d.c.d.h(13458524));
        f995a.put("indigo", new com.d.c.d.h(4915330));
        f995a.put("ivory", new com.d.c.d.h(16777200));
        f995a.put("khaki", new com.d.c.d.h(15787660));
        f995a.put("lavender", new com.d.c.d.h(15132410));
        f995a.put("lavenderblush", new com.d.c.d.h(16773365));
        f995a.put("lawngreen", new com.d.c.d.h(8190976));
        f995a.put("lemonchiffon", new com.d.c.d.h(16775885));
        f995a.put("lightblue", new com.d.c.d.h(11393254));
        f995a.put("lightcoral", new com.d.c.d.h(15761536));
        f995a.put("lightcyan", new com.d.c.d.h(14745599));
        f995a.put("lightgoldenrodyellow", new com.d.c.d.h(16448210));
        f995a.put("lightgreen", new com.d.c.d.h(9498256));
        f995a.put("lightgrey", new com.d.c.d.h(13882323));
        f995a.put("lightpink", new com.d.c.d.h(16758465));
        f995a.put("lightsalmon", new com.d.c.d.h(16752762));
        f995a.put("lightseagreen", new com.d.c.d.h(2142890));
        f995a.put("lightskyblue", new com.d.c.d.h(8900346));
        f995a.put("lightslategray", new com.d.c.d.h(7833753));
        f995a.put("lightsteelblue", new com.d.c.d.h(11584734));
        f995a.put("lightyellow", new com.d.c.d.h(16777184));
        f995a.put("limegreen", new com.d.c.d.h(3329330));
        f995a.put("linen", new com.d.c.d.h(16445670));
        f995a.put("mediumaquamarine", new com.d.c.d.h(6737322));
        f995a.put("mediumblue", new com.d.c.d.h(HttpStatus.SC_RESET_CONTENT));
        f995a.put("mediumorchid", new com.d.c.d.h(12211667));
        f995a.put("mediumpurple", new com.d.c.d.h(9662683));
        f995a.put("mediumseagreen", new com.d.c.d.h(3978097));
        f995a.put("mediumslateblue", new com.d.c.d.h(8087790));
        f995a.put("mediumspringgreen", new com.d.c.d.h(64154));
        f995a.put("mediumturquoise", new com.d.c.d.h(4772300));
        f995a.put("mediumvioletred", new com.d.c.d.h(13047173));
        f995a.put("midnightblue", new com.d.c.d.h(1644912));
        f995a.put("mintcream", new com.d.c.d.h(16121850));
        f995a.put("mistyrose", new com.d.c.d.h(16770273));
        f995a.put("moccasin", new com.d.c.d.h(16770229));
        f995a.put("navajowhite", new com.d.c.d.h(16768685));
        f995a.put("oldlace", new com.d.c.d.h(16643558));
        f995a.put("olivedrab", new com.d.c.d.h(7048739));
        f995a.put("orange", new com.d.c.d.h(16753920));
        f995a.put("orangered", new com.d.c.d.h(16729344));
        f995a.put("orchid", new com.d.c.d.h(14315734));
        f995a.put("palegoldenrod", new com.d.c.d.h(15657130));
        f995a.put("palegreen", new com.d.c.d.h(10025880));
        f995a.put("paleturquoise", new com.d.c.d.h(11529966));
        f995a.put("palevioletred", new com.d.c.d.h(14381203));
        f995a.put("papayawhip", new com.d.c.d.h(16773077));
        f995a.put("peachpuff", new com.d.c.d.h(16767673));
        f995a.put("peru", new com.d.c.d.h(13468991));
        f995a.put("pink", new com.d.c.d.h(16761035));
        f995a.put("plum", new com.d.c.d.h(14524637));
        f995a.put("powderblue", new com.d.c.d.h(11591910));
        f995a.put("rosybrown", new com.d.c.d.h(12357519));
        f995a.put("royalblue", new com.d.c.d.h(4286945));
        f995a.put("saddlebrown", new com.d.c.d.h(9127187));
        f995a.put("salmon", new com.d.c.d.h(16416882));
        f995a.put("sandybrown", new com.d.c.d.h(16032864));
        f995a.put("seagreen", new com.d.c.d.h(3050327));
        f995a.put("seashell", new com.d.c.d.h(16774638));
        f995a.put("sienna", new com.d.c.d.h(10506797));
        f995a.put("skyblue", new com.d.c.d.h(8900331));
        f995a.put("slateblue", new com.d.c.d.h(6970061));
        f995a.put("slategray", new com.d.c.d.h(7372944));
        f995a.put("snow", new com.d.c.d.h(16775930));
        f995a.put("springgreen", new com.d.c.d.h(65407));
        f995a.put("steelblue", new com.d.c.d.h(4620980));
        f995a.put("tan", new com.d.c.d.h(13808780));
        f995a.put("thistle", new com.d.c.d.h(14204888));
        f995a.put("tomato", new com.d.c.d.h(16737095));
        f995a.put("turquoise", new com.d.c.d.h(4251856));
        f995a.put("violet", new com.d.c.d.h(976942));
        f995a.put("wheat", new com.d.c.d.h(16113331));
        f995a.put("whitesmoke", new com.d.c.d.h(16119285));
        f995a.put("yellowgreen", new com.d.c.d.h(10145074));
        f996b.put(Float.valueOf(100.0f), com.d.c.a.c.C);
        f996b.put(Float.valueOf(200.0f), com.d.c.a.c.D);
        f996b.put(Float.valueOf(300.0f), com.d.c.a.c.E);
        f996b.put(Float.valueOf(400.0f), com.d.c.a.c.F);
        f996b.put(Float.valueOf(500.0f), com.d.c.a.c.G);
        f996b.put(Float.valueOf(600.0f), com.d.c.a.c.H);
        f996b.put(Float.valueOf(700.0f), com.d.c.a.c.I);
        f996b.put(Float.valueOf(800.0f), com.d.c.a.c.J);
        f996b.put(Float.valueOf(900.0f), com.d.c.a.c.K);
        c.put("thin", new com.d.c.d.j((short) 5, 1.0f, "1px"));
        c.put("medium", new com.d.c.d.j((short) 5, 2.0f, "2px"));
        c.put("thick", new com.d.c.d.j((short) 5, 3.0f, "3px"));
    }

    public static com.d.c.d.h a(String str) {
        return (com.d.c.d.h) f995a.get(str);
    }

    public static com.d.c.a.c a(float f) {
        return (com.d.c.a.c) f996b.get(Float.valueOf(f));
    }

    public static com.d.c.d.j b(String str) {
        return (com.d.c.d.j) c.get(str);
    }
}
