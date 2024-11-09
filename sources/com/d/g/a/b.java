package com.d.g.a;

import com.a.a.c.f.w;
import com.d.d.i;
import com.d.d.p;
import com.d.g.a.b;
import com.d.g.a.b.a;
import com.d.l.c;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.w3c.dom.Document;

/* loaded from: infinitode-2.jar:com/d/g/a/b.class */
public abstract class b<TFinalClass extends b, TBaseRendererBuilderState extends a> {

    /* renamed from: a, reason: collision with root package name */
    protected final TBaseRendererBuilderState f1196a;

    /* loaded from: infinitode-2.jar:com/d/g/a/b$a.class */
    public static abstract class a {
        public i c;
        public String d;
        public String e;
        public Document f;
        public p g;
        public p h;
        public String i;
        public com.d.d.g j;
        public com.d.d.g k;
        public com.d.d.h l;
        public com.d.d.h m;
        public com.d.d.h n;
        public com.d.a.c o;
        public com.d.a.a p;
        public Float r;
        public Float s;
        public String t;
        public File u;
        public w.a w;

        /* renamed from: a, reason: collision with root package name */
        public final List<w.a> f1197a = new ArrayList();

        /* renamed from: b, reason: collision with root package name */
        public Map<String, com.d.d.e> f1198b = new HashMap();
        public boolean q = false;
        public boolean v = false;
        public String x = "com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl";
        public String y = "com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl";
        public boolean z = false;
    }

    /* renamed from: com.d.g.a.b$b, reason: collision with other inner class name */
    /* loaded from: infinitode-2.jar:com/d/g/a/b$b.class */
    public enum EnumC0025b {
        NORMAL,
        ITALIC,
        OBLIQUE
    }

    /* loaded from: infinitode-2.jar:com/d/g/a/b$c.class */
    public enum c {
        MM,
        INCHES
    }

    /* loaded from: infinitode-2.jar:com/d/g/a/b$d.class */
    public enum d {
        RTL,
        LTR
    }

    static {
        c cVar = c.INCHES;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public b(TBaseRendererBuilderState tbaserendererbuilderstate) {
        this.f1196a = tbaserendererbuilderstate;
        a(new c.b(), "http", "https");
    }

    public final TFinalClass a(d dVar) {
        this.f1196a.q = dVar == d.RTL;
        return this;
    }

    private TFinalClass a(com.d.d.e eVar, String... strArr) {
        for (int i = 0; i < 2; i++) {
            this.f1196a.f1198b.put(strArr[i], eVar);
        }
        return this;
    }

    public final TFinalClass a(com.d.a.c cVar) {
        this.f1196a.o = cVar;
        return this;
    }

    public final TFinalClass a(com.d.a.a aVar) {
        this.f1196a.p = aVar;
        return this;
    }

    public final TFinalClass a(Document document, String str) {
        this.f1196a.f = document;
        this.f1196a.e = str;
        return this;
    }
}
