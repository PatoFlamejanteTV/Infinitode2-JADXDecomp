package org.a.d.b;

/* loaded from: infinitode-2.jar:org/a/d/b/ap.class */
public enum ap {
    Structured(false, null, null),
    DefinedType(false, null, null),
    Text(true, null, am.class),
    Date(true, null, n.class),
    Boolean(true, null, i.class),
    Integer(true, null, s.class),
    Real(true, null, ah.class),
    GPSCoordinate(true, Text, am.class),
    ProperName(true, Text, ad.class),
    Locale(true, Text, v.class),
    AgentName(true, Text, e.class),
    GUID(true, Text, r.class),
    XPath(true, Text, at.class),
    Part(true, Text, ac.class),
    URL(true, Text, ar.class),
    URI(true, Text, aq.class),
    Choice(true, Text, l.class),
    MIMEType(true, Text, w.class),
    LangAlt(true, Text, am.class),
    RenditionClass(true, Text, ai.class),
    Rational(true, Text, ag.class),
    Layer(false, Structured, u.class),
    Thumbnail(false, Structured, an.class),
    ResourceEvent(false, Structured, aj.class),
    ResourceRef(false, Structured, ak.class),
    Version(false, Structured, as.class),
    PDFASchema(false, Structured, aa.class),
    PDFAField(false, Structured, y.class),
    PDFAProperty(false, Structured, z.class),
    PDFAType(false, Structured, ab.class),
    Job(false, Structured, t.class),
    OECF(false, Structured, x.class),
    CFAPattern(false, Structured, j.class),
    DeviceSettings(false, Structured, o.class),
    Flash(false, Structured, q.class),
    Dimensions(false, Structured, p.class);

    private final ap K;
    private final Class<? extends b> L;

    ap(boolean z, ap apVar, Class cls) {
        this.K = apVar;
        this.L = cls;
    }

    public final boolean a() {
        return this.K == Structured;
    }

    public final Class<? extends b> b() {
        return this.L;
    }
}
