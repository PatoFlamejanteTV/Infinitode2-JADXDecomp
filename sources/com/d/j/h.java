package com.d.j;

import com.d.j.g;
import com.d.m.l;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:com/d/j/h.class */
public class h implements ErrorHandler {
    /* JADX INFO: Access modifiers changed from: package-private */
    public h(g.a aVar) {
    }

    @Override // org.xml.sax.ErrorHandler
    public final void error(SAXParseException sAXParseException) {
        l.e(sAXParseException.getMessage());
    }

    @Override // org.xml.sax.ErrorHandler
    public final void fatalError(SAXParseException sAXParseException) {
        l.e(sAXParseException.getMessage());
    }

    @Override // org.xml.sax.ErrorHandler
    public final void warning(SAXParseException sAXParseException) {
        l.e(sAXParseException.getMessage());
    }
}
